package com.lhjl.yygh.domain;

import java.io.Serializable;

public class YiShengListInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String doctorName; //医生名字
	private String doctorId;//医生代码
	private String SessionId;//职称ID
	private String SessionType;//医生职称
	private String Fee;//挂号费
	private String doctorDetail;//医生信息
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
	public String getSessionId() {
		return SessionId;
	}
	public void setSessionId(String sessionId) {
		SessionId = sessionId;
	}
	public String getSessionType() {
		return SessionType;
	}
	public void setSessionType(String sessionType) {
		SessionType = sessionType;
	}
	public String getFee() {
		return Fee;
	}
	public void setFee(String fee) {
		Fee = fee;
	}
	public String getDoctorDetail() {
		return doctorDetail;
	}
	public void setDoctorDetail(String doctorDetail) {
		this.doctorDetail = doctorDetail;
	}
}
