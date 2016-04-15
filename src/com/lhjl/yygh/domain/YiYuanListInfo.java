package com.lhjl.yygh.domain;

import java.io.Serializable;

public class YiYuanListInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String hospitalName; //医院名称
	private String hospital;//医院代码
	private String hospital_addr;//医院地址
	private String hospital_tel;//医院电话
	private String hospital_dengji;//医院级别
	
	public String getHospital_dengji() {
		return hospital_dengji;
	}
	public void setHospital_dengji(String hospital_dengji) {
		this.hospital_dengji = hospital_dengji;
	}
	public String getHospital_addr() {
		return hospital_addr;
	}
	public void setHospital_addr(String hospital_addr) {
		this.hospital_addr = hospital_addr;
	}
	public String getHospital_tel() {
		return hospital_tel;
	}
	public void setHospital_tel(String hospital_tel) {
		this.hospital_tel = hospital_tel;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	
}
