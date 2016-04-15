package com.lhjl.yygh.domain;

import java.io.Serializable;

public class PaiBanlistInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String departmentName;//科室名称
	private String departmentId;//科室代码
	private String doctorName;//医生姓名
	private String doctorId;//医生代码
	private String admitTime;//应诊时间
	private String admitSeg;//时间段
	private String roomName;//诊室名称
	private String roomCode;//诊室代码
	private String doctorTitle;//医生职称
	private String doctorTitleCode;//医生职称代码
	private String totalFee;//预约挂号总费用
	private String availableNum;//可挂号源
	private String scheduleItemCode;//门诊排班项记录标识
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getAdmitTime() {
		return admitTime;
	}
	public void setAdmitTime(String admitTime) {
		this.admitTime = admitTime;
	}
	public String getAdmitSeg() {
		return admitSeg;
	}
	public void setAdmitSeg(String admitSeg) {
		this.admitSeg = admitSeg;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public String getDoctorTitle() {
		return doctorTitle;
	}
	public void setDoctorTitle(String doctorTitle) {
		this.doctorTitle = doctorTitle;
	}
	public String getDoctorTitleCode() {
		return doctorTitleCode;
	}
	public void setDoctorTitleCode(String doctorTitleCode) {
		this.doctorTitleCode = doctorTitleCode;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getAvailableNum() {
		return availableNum;
	}
	public void setAvailableNum(String availableNum) {
		this.availableNum = availableNum;
	}
	public String getScheduleItemCode() {
		return scheduleItemCode;
	}
	public void setScheduleItemCode(String scheduleItemCode) {
		this.scheduleItemCode = scheduleItemCode;
	}
	

}
