package com.lhjl.yygh.domain;

import java.io.Serializable;

public class AppUpdateInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msgcde;
	private String rtnmsg;
	private String clientkey;
	private String appurl;
	private String version;
	private String appversion;
	private String need_update;
	private String new_function;

	public String getMsgcde() {
		return msgcde;
	}

	public void setMsgcde(String msgcde) {
		this.msgcde = msgcde;
	}

	public String getRtnmsg() {
		return rtnmsg;
	}

	public void setRtnmsg(String rtnmsg) {
		this.rtnmsg = rtnmsg;
	}

	public String getClientkey() {
		return clientkey;
	}

	public void setClientkey(String clientkey) {
		this.clientkey = clientkey;
	}

	public String getAppurl() {
		return appurl;
	}

	public void setAppurl(String appurl) {
		this.appurl = appurl;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAppversion() {
		return appversion;
	}

	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}

	public String getNeed_update() {
		return need_update;
	}

	public void setNeed_update(String need_update) {
		this.need_update = need_update;
	}

	public String getNew_function() {
		return new_function;
	}

	public void setNew_function(String new_function) {
		this.new_function = new_function;
	}

}
