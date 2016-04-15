package com.lhjl.yygh.domain;

import java.io.Serializable;

public class DAkeshiListInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String MainDepartmentName;
	private String MainDepartmentId;
	public String getMainDepartmentName() {
		return MainDepartmentName;
	}
	public void setMainDepartmentName(String mainDepartmentName) {
		MainDepartmentName = mainDepartmentName;
	}
	public String getMainDepartmentId() {
		return MainDepartmentId;
	}
	public void setMainDepartmentId(String mainDepartmentId) {
		MainDepartmentId = mainDepartmentId;
	}
	

}
