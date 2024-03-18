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

public class ViewListHardcodedBusiness implements Serializable
{
    protected final transient Log logger = LogFactory.getLog( getClass() );

    private String lsbsId;
    private String lsdbsNumber;
    private String hardcodedFlag;
    
    public ViewListHardcodedBusiness()
    {
        logger.info( "---------- ViewListHardcodedBusiness constructor is called ..." );
    }
    
    public ViewListHardcodedBusiness(String lsbsId, String lsdbsNumber, String hardcodedFlag) {
		super();
		this.lsbsId = lsbsId;
		this.lsdbsNumber = lsdbsNumber;
		this.hardcodedFlag = hardcodedFlag;
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

	public String getLsdbsNumber() {
		return lsdbsNumber;
	}

	public void setLsdbsNumber(String lsdbsNumber) {
		this.lsdbsNumber = lsdbsNumber;
	}
    
}
