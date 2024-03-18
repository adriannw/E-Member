package id.co.sinarmaslife.standard.model.vo;

import java.io.Serializable;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
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

public class ViewSelectedListBusiness implements Serializable
{
    protected final transient Log logger = LogFactory.getLog( getClass() );

    private String groupId;
    private String lsbsId;
    private String lsbsName;
    private String lsdbsNumber;
    private String lsdbsName;
    private String hardcodedFlag;
    private String lsdbsAktif;
    
    public ViewSelectedListBusiness()
    {
        logger.info( "---------- ViewSelectedListBusiness constructor is called ..." );
    }
    
    public ViewSelectedListBusiness(String groupId, String lsbsId, String lsbsName, String lsdbsNumber, String lsdbsName, String hardcodedFlag) {
		super();
		this.groupId = groupId;
		this.lsbsId = lsbsId;
		this.lsbsName = lsbsName;
		this.lsdbsNumber = lsdbsNumber;
		this.lsdbsName = lsdbsName;
		this.hardcodedFlag = hardcodedFlag;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getHardcodedFlag() {
		return hardcodedFlag;
	}

	public void setHardcodedFlag(String hardcodedFlag) {
		this.hardcodedFlag = hardcodedFlag;
	}

	public String getLsbsId() {
		return lsbsId;
	}

	public void setLsbsId(String lsbsId) {
		this.lsbsId = lsbsId;
	}

	public String getLsbsName() {
		return lsbsName;
	}

	public void setLsbsName(String lsbsName) {
		this.lsbsName = lsbsName;
	}

	public String getLsdbsName() {
		return lsdbsName;
	}

	public void setLsdbsName(String lsdbsName) {
		this.lsdbsName = lsdbsName;
	}

	public String getLsdbsNumber() {
		return lsdbsNumber;
	}

	public void setLsdbsNumber(String lsdbsNumber) {
		this.lsdbsNumber = lsdbsNumber;
	}

	public String getLsdbsAktif() {
		return lsdbsAktif;
	}

	public void setLsdbsAktif(String lsdbsAktif) {
		this.lsdbsAktif = lsdbsAktif;
	}

	

	
	
    
}
