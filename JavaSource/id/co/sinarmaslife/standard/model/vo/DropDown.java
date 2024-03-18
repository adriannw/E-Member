package id.co.sinarmaslife.standard.model.vo;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: DropDown
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 8, 2008 10:45:07 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.io.Serializable;

public class DropDown implements Serializable {

	private static final long serialVersionUID = -1443071895352555541L;

	protected String key;
	protected String value;
	protected String desc;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public DropDown() {

	}

	public DropDown(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public DropDown(String key, String value, String desc) {
		this.key = key;
		this.value = value;
		this.desc = desc;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}