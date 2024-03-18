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

public class ViewListProvider implements Serializable
{
    protected final transient Log logger = LogFactory.getLog( getClass() );
    
    private String rsId;
    private String rsNama;
    private String rsAlamat;

    public ViewListProvider()
    {
        logger.info( "---------- ViewListProvider constructor is called ..." );
    }

	public ViewListProvider(String rsId, String rsNama, String rsAlamat) {
		super();
		this.rsId = rsId;
		this.rsNama = rsNama;
		this.rsAlamat = rsAlamat;
	}

	public String getRsNama() {
		return rsNama;
	}

	public void setRsNama(String rsNama) {
		this.rsNama = rsNama;
	}

	public String getRsAlamat() {
		return rsAlamat;
	}

	public void setRsAlamat(String rsAlamat) {
		this.rsAlamat = rsAlamat;
	}

	public String getRsId() {
		return rsId;
	}

	public void setRsId(String rsId) {
		this.rsId = rsId;
	}

	
    
}
