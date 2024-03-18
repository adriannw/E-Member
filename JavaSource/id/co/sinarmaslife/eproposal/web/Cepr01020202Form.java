package id.co.sinarmaslife.eproposal.web;

import java.io.Serializable;

public class Cepr01020202Form implements Serializable{
	/**********************************************************************
	 * Program History
	 *
	 * Project Name      	: E-Proposal
	 * Function Id         	: id.co.sinarmaslife.eproposal.web
	 * Program Name   		: 
	 * Description         	:
	 * Environment      	: Java  1.5.0_06
	 * Author               : Fadly Mathendra
	 * Version              : 1.0
	 * Creation Date    	: Nov 28, 2012 10:37:44 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = -7178354165497816785L;
	private String lsbsId;
	private String lsdbsId;

	public String getLsbsId() {
		return lsbsId;
	}

	public void setLsbsId(String lsbsId) {
		this.lsbsId = lsbsId;
	}

	public String getLsdbsId() {
		return lsdbsId;
	}

	public void setLsdbsId(String lsdbsId) {
		this.lsdbsId = lsdbsId;
	}
}
