package com.lhjl.yygh.domain;

import java.io.Serializable;

public class CardInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accno;
	private String alias;
	private String usrid;
	private String probank;
	private String lmtamt;

	public CardInfo(String accno, String alias, String usrid, String probank,
			String lmtamt) {
		this.accno = accno;
		this.alias = alias;
		this.usrid = usrid;
		this.probank = probank;
		this.lmtamt = lmtamt;
	}

	public String getAccno() {
		return accno;
	}

	public String getAlias() {
		return alias;
	}

	public String getUsrid() {
		return usrid;
	}

	public String getProbank() {
		return probank;
	}

	public String getLmtamt() {
		return lmtamt;

	}
}
