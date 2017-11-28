package com.sherwin.mdmvalidationsystem.model;

import java.sql.Date;
import java.util.Map;

public class Item {
	
	private String item;
	private String description;
	private String ICC;
	private String template;
	private String status;
	private String type;
	private Date creationDate;
	private String createdBy;
	private String isComplete;
	private Map<Integer, ChangeOrder> orgMap;
	private Map<String,String> approveGroup;

	public Item(){
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getICC() {
		return ICC;
	}

	public void setICC(String iCC) {
		ICC = iCC;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Map<Integer, ChangeOrder> getOrgMap() {
		return orgMap;
	}

	public void setOrgMap(Map<Integer, ChangeOrder> orgMap) {
		this.orgMap = orgMap;
	}

	public String getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(String isComplete) {
		this.isComplete = isComplete;
	}

	public Map<String, String> getApproveGroup() {
		return approveGroup;
	}

	public void setApproveGroup(Map<String, String> approveGroup) {
		this.approveGroup = approveGroup;
	}
	
}
