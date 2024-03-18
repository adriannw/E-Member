package id.co.sinarmaslife.standard.model.vo;

import java.io.Serializable;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Medical
 * Function Id         	: 
 * Program Name   		: OptionVO
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 14, 2007 1:38:45 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserGroup implements Serializable
{
    protected final transient Log logger = LogFactory.getLog( getClass() );

    private String msagId;
    private String groupId;
    private String groupName;

    public UserGroup()
    {
        logger.info( "---------- UserGroup constructor is called ..." );
    }

	public UserGroup(String msagId, String groupId, String groupName) {
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