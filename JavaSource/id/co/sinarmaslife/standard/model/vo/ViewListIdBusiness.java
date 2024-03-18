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

public class ViewListIdBusiness implements Serializable
{
    protected final transient Log logger = LogFactory.getLog( getClass() );

    private String Id;

    public ViewListIdBusiness(String id) {
		super();
		Id = id;
	}

	public ViewListIdBusiness()
    {
        logger.info( "---------- ViewListIdBusiness constructor is called ..." );
    }

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}
	
	

   
    
}
