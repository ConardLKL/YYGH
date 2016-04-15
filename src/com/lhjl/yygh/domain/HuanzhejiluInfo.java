package com.lhjl.yygh.domain;

import java.io.Serializable;

public class HuanzhejiluInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String medicalAccountNo;//诊疗账号
	private String bindCardNo;//银联卡号
	private String orderCode;//预约单号
	private String scheduleItemCode;//门诊排班记录标识
	private String applyDate;//申请日期
	private String orderState;//预约单状态
	private String applicant;//预约单申请人
	private String orderDate;//预约就诊日期
	private String departmentItem;//就诊科室
	private String doctorName;//医生姓名
	private String registryFee;//总费用
	private String serialNo;//就诊序号
	private String orderContent;//预约单内容
	private String contactTel;//联系电话
	private String telephone;//手机
	private String cancelFlag;//允许退号标志
	private String paymentState;//支付状态
	private String hospitalName;
	
	
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getMedicalAccountNo() {
		return medicalAccountNo;
	}
	public void setMedicalAccountNo(String medicalAccountNo) {
		this.medicalAccountNo = medicalAccountNo;
	}
	public String getBindCardNo() {
		return bindCardNo;
	}
	public void setBindCardNo(String bindCardNo) {
		this.bindCardNo = bindCardNo;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getScheduleItemCode() {
		return scheduleItemCode;
	}
	public void setScheduleItemCode(String scheduleItemCode) {
		this.scheduleItemCode = scheduleItemCode;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getDepartmentItem() {
		return departmentItem;
	}
	public void setDepartmentItem(String departmentItem) {
		this.departmentItem = departmentItem;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getRegistryFee() {
		return registryFee;
	}
	public void setRegistryFee(String registryFee) {
		this.registryFee = registryFee;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getOrderContent() {
		return orderContent;
	}
	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public String getPaymentState() {
		return paymentState;
	}
	public void setPaymentState(String paymentState) {
		this.paymentState = paymentState;
	}
	
	
}
