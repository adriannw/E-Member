package id.co.sinarmaslife.eproposal.common.parent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: EproposalDao
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Apr 9, 2008 9:06:50 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.standard.parent.ParentDao;

public class EproposalDao extends ParentDao
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public EproposalDao()
    {
    	// Do nothing 
    }

    /**
     * Scope dari dao ini hanyalah perintah2 SQL yang ada di namespace "id.co.sinarmaslife.eproposal."
     */
    protected void initDao() throws Exception
    {
        this.statementNameSpace = "id.co.sinarmaslife.eproposal.";
        super.initDao();
    }
  
}
