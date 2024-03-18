package id.co.sinarmaslife.eproposal.web;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01030401Form
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 13, 2007 3:17:29 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cepr01030401Form implements Serializable
{
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
	 * Creation Date    	: Nov 28, 2012 10:39:11 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = -1958210839048585709L;

	protected final transient Log logger = LogFactory.getLog( getClass() );

    private String customerName;
    private String agentName;
    private String branchName;
    private String homePhoneNo;
    private String mobilePhoneNo;

    private String downloadFlag;

    public Cepr01030401Form()
    {
        logger.info( "*-*-*-* Cepr01030401Form constructor is called ..." );
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName( String customerName )
    {
        this.customerName = customerName;
    }

    public String getAgentName()
    {
        return agentName;
    }

    public void setAgentName( String agentName )
    {
        this.agentName = agentName;
    }

    public String getBranchName()
    {
        return branchName;
    }

    public void setBranchName( String branchName )
    {
        this.branchName = branchName;
    }

    public String getHomePhoneNo()
    {
        return homePhoneNo;
    }

    public void setHomePhoneNo( String homePhoneNo )
    {
        this.homePhoneNo = homePhoneNo;
    }

    public String getMobilePhoneNo()
    {
        return mobilePhoneNo;
    }

    public void setMobilePhoneNo( String mobilePhoneNo )
    {
        this.mobilePhoneNo = mobilePhoneNo;
    }

    public String getDownloadFlag()
    {
        return downloadFlag;
    }

    public void setDownloadFlag( String downloadFlag )
    {
        this.downloadFlag = downloadFlag;
    }
}
