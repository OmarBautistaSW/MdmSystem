package com.sherwin.mdmvalidationsystem.model;

import java.util.Date;
import java.util.List;

public class ChangeOrderDetail {

	private int seqNum;
	private String wokflowStatus;
	private String groupAssigned;
	private Date stepStartDate;
	private Date stepReplyDate;
	private float stepProcessedDays;
	private float workflowProcessedDays;
	private List<String> canReplyWorkflow;
	private String orgCode;
	private String approveNames;
	
	public ChangeOrderDetail(){
		
	}

	public int getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}

	public String getGroupAssigned() {
		return groupAssigned;
	}

	public void setGroupAssigned(String groupAssigned) {
		this.groupAssigned = groupAssigned;
	}

	public Date getStepStartDate() {
		return stepStartDate;
	}

	public void setStepStartDate(Date stepStartDate) {
		this.stepStartDate = stepStartDate;
	}

	public Date getStepReplyDate() {
		return stepReplyDate;
	}

	public void setStepReplyDate(Date stepReplyDate) {
		this.stepReplyDate = stepReplyDate;
	}

	public float getStepProcessedDays() {
		return stepProcessedDays;
	}

	public void setStepProcessedDays(float stepProcessedDays) {
		this.stepProcessedDays = stepProcessedDays;
	}

	public float getWorkflowProcessedDays() {
		return workflowProcessedDays;
	}

	public void setWorkflowProcessedDays(float workflowProcessedDays) {
		this.workflowProcessedDays = workflowProcessedDays;
	}

	public String getWokflowStatus() {
		return wokflowStatus;
	}

	public void setWokflowStatus(String wokflowStatus) {
		this.wokflowStatus = wokflowStatus;
	}

	public List<String> getCanReplyWorkflow() {
		return canReplyWorkflow;
	}

	public void setCanReplyWorkflow(List<String> canReplyWorkflow) {
		this.canReplyWorkflow = canReplyWorkflow;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getApproveNames() {
		return approveNames;
	}

	public void setApproveNames(String approveNames) {
		this.approveNames = approveNames;
	}


	
	
}
