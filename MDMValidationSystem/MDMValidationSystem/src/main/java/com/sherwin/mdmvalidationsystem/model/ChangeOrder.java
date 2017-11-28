package com.sherwin.mdmvalidationsystem.model;

import java.util.Date;
import java.util.Map;

public class ChangeOrder {
	
	private String name;
	private String orgCode;
	private String orgName;
	private Map<Integer, ChangeOrderDetail> mapDetail;
	private String orgComplete;
	private String createdBy;
	private Date creationDate;
	
	public ChangeOrder(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<Integer, ChangeOrderDetail> getMapDetail() {
		return mapDetail;
	}

	public void setMapDetail(Map<Integer, ChangeOrderDetail> mapDetail) {
		this.mapDetail = mapDetail;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getOrgComplete() {
		return orgComplete;
	}

	public void setOrgComplete(String orgComplete) {
		this.orgComplete = orgComplete;
	}
	
	
	
}
