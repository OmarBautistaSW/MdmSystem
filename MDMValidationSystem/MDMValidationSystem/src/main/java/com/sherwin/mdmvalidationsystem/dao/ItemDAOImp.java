package com.sherwin.mdmvalidationsystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
	public List <Item> listItems(String items){
		jdbcTemplate.execute(ALTER_SCHEMA);
		jdbcTemplate.execute(ALTER_LANGUAGE);
		String sql = generateQuery(items);
		List <Item> listItems = jdbcTemplate.query(sql, new RowMapper<Item>() {
			
			@Override
			public Item mapRow(ResultSet rs, int rowNum) throws SQLException{
				Item item = new Item();
				item.setOrg(rs.getString(1));
				item.setOrgName(rs.getString(2));
				item.setNumber(rs.getString(3));
				item.setDescription(rs.getString(4));
				item.setIcc(rs.getString(5));
				item.setTemplate(rs.getString(6));
				item.setStatus(rs.getString(7));
				item.setType(rs.getString(8));
				item.setCreatedBy(rs.getString(9));
				item.setCreationDate(rs.getDate(10).toString());
				item.setChangeOrder(rs.getString(11));
				item.setChangeOrderStatus(rs.getString(12));
				item.setWorkflowStatus(rs.getString(13));
				item.setWorkflowPhase(rs.getString(14));
				item.setStepNumber(rs.getInt(15));
				item.setGroupName(rs.getString(16));
				item.setDateRecived(rs.getDate(17).toString());
				item.setRepliedDate(rs.getDate(18).toString());
				item.setDaysInGroup(rs.getDouble(19));
				item.setWorkflowTotalDays(rs.getDouble(20));
				        
				return item;
			}
		});
		
		return listItems;
	}
	
	private String generateQuery(String items){
		String parameter = mdmValidationUtils.formatParameter(items);
	    String sql = "SELECT DISTINCT xechv.organization_code, ood.organization_name, item, xechv.description, " 
	    	    + "ICC, item_template, xechv.inventory_item_status_code, xechv.item_type, (select fu.description from apps.fnd_user fu where msi.created_by = fu.user_id), "
	    		+ "xechv.creation_date, change_order, change_order_status, workflow_status, (select min( ecr.status_code) from eng_change_routes ecr,  eng_engineering_changes_v eecv "
	    	    + "where ecr.object_id1 = eecv.change_id and xechv.change_order = eecv.change_notice and  ecr.object_id1 = eecv.change_id and xechv.change_order = eecv.change_notice "
	    		+ "and eecv.change_notice =  change_order and ecr.classification_code = decode (workflow_status, 'Open', '1', '8') ), step_seq_num, assigned_group_name, step_start_date, "
	    	    + "step_reply_date, step_processed_days, workflow_processed_days FROM XXSW_EGO_CHANGEORDER_HISTORY_V xechv, org_organization_definitions ood, apps.mtl_system_items msi "
	    		+ "WHERE 1=1 and xechv.organization_code = ood.organization_code and ood.organization_id = msi.organization_id and ood.organization_code = xechv.organization_code and msi.segment1  = xechv.item "
	    	    + "and assigned_group_name IS NOT NULL and xechv.item in (" + parameter + ") ORDER BY xechv.item, xechv.organization_code, (select min( ecr.status_code) from eng_change_routes ecr, "
	    		+ "eng_engineering_changes_v eecv where ecr.object_id1 = eecv.change_id and xechv.change_order = eecv.change_notice and eecv.change_notice =  change_order and ecr.classification_code in ('1')), "
	    	    + "xechv.workflow_status desc, xechv.step_seq_num ";
	    return sql;
	}
	
}
