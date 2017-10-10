package com.sherwin.mdmvalidationsystem.model;

public class Item {

	private String org;
	private String orgName;
	private String number;
	private String description;
	private String icc;
	private String template;
	private String status;
	private String type;
	private String createdBy;
	private String creationDate;
	private String changeOrder;
	private String changeOrderStatus;
	private String workflowPhase;
	private String workflowStatus;
	private String stepNumber;
	private String groupName;
	private String dateRecived;
	private String repliedDate;
	private String daysInGroup;
	private String workflowTotalDays;
	
	public Item(){
	}

	public Item(String org, String orgName, String number, String description,
			String icc, String template, String status, String type,
			String createdBy, String creationDate, String changeOrder,
			String changeOrderStatus, String workflowPhase,
			String workflowStatus, String stepNumber, String groupName,
			String dateRecived, String repliedDate, String daysInGroup,
			String workflowTotalDays) {
		super();
		this.org = org;
		this.orgName = orgName;
		this.number = number;
		this.description = description;
		this.icc = icc;
		this.template = template;
		this.status = status;
		this.type = type;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.changeOrder = changeOrder;
		this.changeOrderStatus = changeOrderStatus;
		this.workflowPhase = workflowPhase;
		this.workflowStatus = workflowStatus;
		this.stepNumber = stepNumber;
		this.groupName = groupName;
		this.dateRecived = dateRecived;
		this.repliedDate = repliedDate;
		this.daysInGroup = daysInGroup;
		this.workflowTotalDays = workflowTotalDays;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcc() {
		return icc;
	}

	public void setIcc(String icc) {
		this.icc = icc;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getChangeOrder() {
		return changeOrder;
	}

	public void setChangeOrder(String changeOrder) {
		this.changeOrder = changeOrder;
	}

	public String getChangeOrderStatus() {
		return changeOrderStatus;
	}

	public void setChangeOrderStatus(String changeOrderStatus) {
		this.changeOrderStatus = changeOrderStatus;
	}

	public String getWorkflowPhase() {
		return workflowPhase;
	}

	public void setWorkflowPhase(String workflowPhase) {
		this.workflowPhase = workflowPhase;
	}

	public String getWorkflowStatus() {
		return workflowStatus;
	}

	public void setWorkflowStatus(String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}

	public String getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(String stepNumber) {
		this.stepNumber = stepNumber;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDateRecived() {
		return dateRecived;
	}

	public void setDateRecived(String dateRecived) {
		this.dateRecived = dateRecived;
	}

	public String getRepliedDate() {
		return repliedDate;
	}

	public void setRepliedDate(String repliedDate) {
		this.repliedDate = repliedDate;
	}

	public String getDaysInGroup() {
		return daysInGroup;
	}

	public void setDaysInGroup(String daysInGroup) {
		this.daysInGroup = daysInGroup;
	}

	public String getWorkflowTotalDays() {
		return workflowTotalDays;
	}

	public void setWorkflowTotalDays(String workflowTotalDays) {
		this.workflowTotalDays = workflowTotalDays;
	}
}
