package id.co.sinarmaslife.eproposal.web;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cepr01020101Form implements Serializable {

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
	 * Creation Date    	: Nov 28, 2012 10:37:19 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = 4577552810912432342L;

	protected final transient Log logger = LogFactory.getLog( getClass() );

    private String msagId;
    private String groupId;
    private String groupName;

    public Cepr01020101Form()
    {
        logger.info( "---------- UserGroup constructor is called ..." );
    }

	public Cepr01020101Form(String msagId, String groupId, String groupName) {
		super();
		this.msagId = msagId;
		this.groupId = groupId;
		this.groupName = groupName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getMsagId() {
		return msagId;
	}

	public void setMsagId(String msagId) {
		this.msagId = msagId;
	}
	
}
