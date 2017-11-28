package com.sherwin.mdmvalidationsystem.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sherwin.mdmvalidationsystem.model.ChangeOrder;
import com.sherwin.mdmvalidationsystem.model.ChangeOrderDetail;
import com.sherwin.mdmvalidationsystem.model.Item;
import com.sherwin.mdmvalidationsystem.utils.MDMValidationUtils;

public class ItemDAOImp implements ItemDAO{
	private JdbcTemplate jdbcTemplate;
	private static final String ALTER_SCHEMA = "alter session set current_schema = APPS";
	private static final String ALTER_LANGUAGE = "alter session set nls_language='AMERICAN'";
	MDMValidationUtils mdmValidationUtils = new MDMValidationUtils();
	
	public ItemDAOImp (DataSource datasource){
		jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public Boolean itemExist(String item){
		String sql = createExistItemQuery(item);
		Boolean isValid = false;
		Connection conn = null;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
			Statement st = conn.createStatement();
			st.executeQuery(ALTER_SCHEMA);
			st.executeQuery(ALTER_LANGUAGE);
			ResultSet rs = st.executeQuery(sql); 
			while(rs.next()){
				//Se cambia a true porque el item existe en la ORG 000
				isValid = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(null!=conn)
					conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return isValid;
	}
	
	@Override
	public List<Item> listItems(List<String> items){
		Connection conn = null;
		List<Item> listItemsFilled = new ArrayList<Item>();
		String sql = "";
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
			Statement st = conn.createStatement();
			st.executeQuery(ALTER_SCHEMA);
			st.execute(ALTER_LANGUAGE);
			//Rutina para llenar información general
			for(String itStr : items){
				sql = createGetGeneralInformationQuery(itStr.trim());
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()){
					Item item = new Item();
					item.setItem(rs.getString(1));
					item.setDescription(rs.getString(2));
					item.setStatus(rs.getString(3));
					item.setType(rs.getString(4));
					item.setCreationDate(rs.getDate(5));
					item.setICC(rs.getString(6));
					item.setTemplate(rs.getString(7));
					item.setCreatedBy(rs.getString(8));
					item.setIsComplete("S");
					item.setOrgMap(new HashMap<Integer,ChangeOrder>());
					item.setApproveGroup(new HashMap<String,String>());
					listItemsFilled.add(item);
				}
			}
			//Rutina para llenar organizaciones
			for(Item item : listItemsFilled){
				sql = createGetOrgsQuery(item.getItem());
				ResultSet rs = st.executeQuery(sql);
				int row = 0;
				while(rs.next()){
					ChangeOrder co = new ChangeOrder();
					co.setOrgCode(rs.getString(1));
					co.setOrgName(rs.getString(2));
					co.setName("WITHOUT CHANGE ORDER");
					item.getOrgMap().put(row, co);
					row++;
				}
				//Rutina para llenar CO's
				sql = createGetChangeOrderInfoQuery(item.getItem());
				rs = st.executeQuery(sql);
				while(rs.next()){
					for(int x = 0; x < row;x++){
						if(item.getOrgMap().get(x).getOrgCode().equals(rs.getString(4))){
							item.getOrgMap().get(x).setName(rs.getString(1));
							item.getOrgMap().get(x).setCreatedBy(rs.getString(2));
							item.getOrgMap().get(x).setCreationDate(rs.getDate(3));
							item.getOrgMap().get(x).setOrgComplete("S");
							item.getOrgMap().get(x).setMapDetail(new HashMap<Integer,ChangeOrderDetail>());
						}
					}
				}
//				//Rutina para llenar CO's Details
				sql = createGetCODetailQuery(item.getItem());
				rs = st.executeQuery(sql);
				List<ChangeOrderDetail> listCOD = new ArrayList<ChangeOrderDetail>();
				while(rs.next()){
					ChangeOrderDetail cod = new ChangeOrderDetail();
					cod.setSeqNum(rs.getInt(1));
					cod.setGroupAssigned(rs.getString(2));
					cod.setStepStartDate(rs.getDate(3));
					cod.setStepReplyDate(rs.getDate(4));
					cod.setStepProcessedDays(rs.getFloat(5));
					cod.setWorkflowProcessedDays(rs.getFloat(6));
					cod.setWokflowStatus(rs.getString(7));
					cod.setOrgCode(rs.getString(8));
					listCOD.add(cod);
				}
				for(int x = 0; x < row; x++){
					int rowCOD = 0;
					int timeOut = 0;
					for(ChangeOrderDetail cod : listCOD){
						if(cod.getOrgCode().equals(item.getOrgMap().get(x).getOrgCode())){
							item.getOrgMap().get(x).getMapDetail().put(rowCOD, cod);
							if(cod.getGroupAssigned().equals("SW LA MDM Group")){
								if(!cod.getWokflowStatus().equals("APPROVED") && cod.getStepReplyDate() == null){
									if(!item.getApproveGroup().containsKey("SW LA MDM Group"))
										item.getApproveGroup().put("SW LA MDM Group", "");
									item.getOrgMap().get(x).setOrgComplete("N");
									item.setIsComplete("N");
								}
							}else{
								if(cod.getGroupAssigned().equals("SW BR MDM Group")){
									if(!cod.getWokflowStatus().equals("APPROVED") && cod.getStepReplyDate() == null){
										if(!item.getApproveGroup().containsKey("SW BR MDM Group"))
											item.getApproveGroup().put("SW BR MDM Group", "");
										item.getOrgMap().get(x).setOrgComplete("N");
										item.setIsComplete("N");
									}
								}else{									
									if(!cod.getWokflowStatus().equals("COMPLETED")){
										if(!item.getApproveGroup().containsKey(cod.getGroupAssigned()))
											item.getApproveGroup().put(cod.getGroupAssigned().trim(), "");									
										item.getOrgMap().get(x).setOrgComplete("N");
										item.setIsComplete("N");
									}
								}
							}
							if(cod.getWokflowStatus().equals("TIME_OUT") && cod.getStepReplyDate() == null)
								timeOut = 1;
							rowCOD++;
						}
					}
					//Rutina para reordenar el mapa si hay elementos en timeOut
					if(timeOut == 1){
						List<ChangeOrderDetail> listNotTimeOut = new ArrayList<ChangeOrderDetail>();
						List<ChangeOrderDetail> listTimeOut = new ArrayList<ChangeOrderDetail>();
						List<ChangeOrderDetail> listMDM = new ArrayList<ChangeOrderDetail>();
						for(int i = 0; i<rowCOD; i ++){
							if(item.getOrgMap().get(x).getMapDetail().get(i).getGroupAssigned().equals("SW LA MDM Group") || item.getOrgMap().get(x).getMapDetail().get(i).getGroupAssigned().equals("SW BR MDM Group")){
								listMDM.add(item.getOrgMap().get(x).getMapDetail().get(i));
							}else{
								if(item.getOrgMap().get(x).getMapDetail().get(i).getWokflowStatus().equals("TIME_OUT"))
									listTimeOut.add(item.getOrgMap().get(x).getMapDetail().get(i));
								else
									listNotTimeOut.add(item.getOrgMap().get(x).getMapDetail().get(i));
							}
						}
						item.getOrgMap().get(x).setMapDetail(new HashMap<Integer,ChangeOrderDetail>());
						int currentRow = 0;
						for(ChangeOrderDetail codMDM : listMDM){
							for(ChangeOrderDetail codTO : listTimeOut){
								item.getOrgMap().get(x).getMapDetail().put(currentRow, codTO);
								currentRow++;
							}
							for(ChangeOrderDetail codNTO : listNotTimeOut){
								item.getOrgMap().get(x).getMapDetail().put(currentRow, codNTO);
								currentRow++;
							}
							item.getOrgMap().get(x).getMapDetail().put(currentRow, codMDM);
							currentRow++;
						}
					}else{
						List<ChangeOrderDetail> listNotMDM = new ArrayList<ChangeOrderDetail>();
						List<ChangeOrderDetail> listMDM = new ArrayList<ChangeOrderDetail>();
						for(int i = 0; i<rowCOD; i++){
							if(item.getOrgMap().get(x).getMapDetail().get(i).getGroupAssigned().equals("SW LA MDM Group") || item.getOrgMap().get(x).getMapDetail().get(i).getGroupAssigned().equals("SW BR MDM Group") )
								listMDM.add(item.getOrgMap().get(x).getMapDetail().get(i));
							else
								listNotMDM.add(item.getOrgMap().get(x).getMapDetail().get(i));
						}
						item.getOrgMap().get(x).setMapDetail(new HashMap<Integer,ChangeOrderDetail>());
						int currentRow = 0;
						for(ChangeOrderDetail codMDM : listMDM){							
							for(ChangeOrderDetail codNMDM : listNotMDM){
								item.getOrgMap().get(x).getMapDetail().put(currentRow, codNMDM);
								currentRow++;
							}
							item.getOrgMap().get(x).getMapDetail().put(currentRow, codMDM);
							currentRow++;
						}
					}
				}
				//Rutina para obtener los nombres de los grupos que deben aprobar
				if(item.getIsComplete().equals("N")){
					for(Map.Entry<String, String> entry : item.getApproveGroup().entrySet()){
						sql = createGetApproveGroupInfo(entry.getKey());
						rs = st.executeQuery(sql);
						String group = "<br>";
						while(rs.next()){
							if(!rs.getString(1).equals("SW MDM User"))
								group += rs.getString(1) + " - " + rs.getString(2) + "<br>";
						}
						item.getApproveGroup().put(entry.getKey().trim(), group.trim());
					}
				}
				for(Map.Entry<Integer, ChangeOrder> entry : item.getOrgMap().entrySet()){
					for(Map.Entry<Integer, ChangeOrderDetail> ent : entry.getValue().getMapDetail().entrySet()){
						if(ent.getValue().getStepReplyDate() == null){
							ent.getValue().setApproveNames(item.getApproveGroup().get(ent.getValue().getGroupAssigned()));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(null!=conn)
					conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return listItemsFilled;
	}
	
	private String createExistItemQuery(String item){
		String sql = "SELECT msi.SEGMENT1 FROM apps.mtl_system_items msi, apps.org_organization_definitions ood "
				+"WHERE msi.organization_id = ood.organization_id AND msi.SEGMENT1 = '" + item + "' AND ood.organization_id = 101";
		return sql;
	}	
	
	private String createGetGeneralInformationQuery(String item){
		String sql = "SELECT msi.SEGMENT1, msi.DESCRIPTION, msi.INVENTORY_ITEM_STATUS_CODE, msi.ITEM_TYPE, MSI.CREATION_DATE, ecgv.CATALOG_GROUP, esita.SW_APPLIED_ITEM_TEMPLATE, "
				+ "(select fu.description from apps.fnd_user fu where msi.created_by = fu.user_id) "
				+ "FROM apps.mtl_system_items msi, apps.ego_catalog_groups_v ecgv, apps.ego_sw_item_template_agv esita "
				+ "WHERE msi.SEGMENT1 = '" + item + "' AND msi.ORGANIZATION_ID = 101 AND msi.ITEM_CATALOG_GROUP_ID = ecgv.CATALOG_GROUP_ID "
				+ "AND msi.INVENTORY_ITEM_ID = esita.INVENTORY_ITEM_ID AND msi.ORGANIZATION_ID = esita.ORGANIZATION_ID";
		return sql;
	}
	
	private String createGetChangeOrderInfoQuery(String item){
		String sql = "SELECT DISTINCT xechv.CHANGE_ORDER, xechv.CREATED_BY, xechv.CREATION_DATE, xechv.ORGANIZATION_CODE FROM XXSW_EGO_CHANGEORDER_HISTORY_V xechv WHERE xechv.item = '" + item + "'";
		return sql;
	}
	
	private String createGetOrgsQuery(String item){
		String sql ="SELECT ood.organization_code, ood.organization_name FROM apps.mtl_system_items msi, org_organization_definitions ood "
				+ "WHERE msi.SEGMENT1 = '" + item + "' AND msi.organization_id = ood.organization_id ORDER BY ood.organization_code";
		return sql;
	}
	
	private String createGetCODetailQuery(String item){
		String sql = "SELECT DISTINCT xechv.STEP_SEQ_NUM, xechv.ASSIGNED_GROUP_NAME, xechv.STEP_START_DATE, xechv.STEP_REPLY_DATE, xechv.STEP_PROCESSED_DAYS, xechv.WORKFLOW_PROCESSED_DAYS, ecr.status_code,xechv.ORGANIZATION_CODE "
				+ "FROM XXSW_EGO_CHANGEORDER_HISTORY_V xechv, eng_change_routes ecr,  eng_engineering_changes_v eecv "
				+ "WHERE xechv.ITEM = '" + item + "' AND xechv.CHANGE_ORDER = eecv.change_notice AND ecr.object_id1 = eecv.change_id "
				+ "AND ecr.classification_code = decode (xechv.workflow_status, 'Open', '1', '8') AND xechv.STEP_SEQ_NUM is not null "
				+ "ORDER BY xechv.ORGANIZATION_CODE, xechv.STEP_SEQ_NUM";
		return sql;
	}
	
	private String createGetApproveGroupInfo(String group){
		String sql="SELECT member_person_name,member_user_name from apps.ego_group_members_v where group_name = '" + group +"'";
		return sql;
	}
}
