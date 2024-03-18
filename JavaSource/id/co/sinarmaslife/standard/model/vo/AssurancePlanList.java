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

public class AssurancePlanList implements Serializable
{
    /**********************************************************************
	 * Program History
	 *
	 * Project Name      	: E-Proposal
	 * Function Id         	: id.co.sinarmaslife.standard.model.vo
	 * Program Name   		: 
	 * Description         	:
	 * Environment      	: Java  1.5.0_06
	 * Author               : Fadly Mathendra
	 * Version              : 1.0
	 * Creation Date    	: Jan 22, 2013 10:53:28 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = 9128249777663547769L;

	protected final transient Log logger = LogFactory.getLog( getClass() );

    private String value;
    private String label;
    private Integer flag_paket;
    private String flag_aktif;
    
    public AssurancePlanList()
    {
        logger.info( "---------- OptionVO constructor is called ..." );
    }

    public AssurancePlanList( String value, String label, Integer flag_paket)
    {
        this.value = value;
        this.label = label;
        this.flag_paket = flag_paket;
    }
    
    public AssurancePlanList( String value, String label )
    {
        this.value = value;
        this.label = label;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue( String value )
    {
        this.value = value;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel( String label )
    {
        this.label = label;
    }

	public Integer getFlag_paket() {
		return flag_paket;
	}

	public void setFlag_paket(Integer flag_paket) {
		this.flag_paket = flag_paket;
	}

	public String getFlag_aktif() {
		return flag_aktif;
	}

	public void setFlag_aktif(String flag_aktif) {
		this.flag_aktif = flag_aktif;
	}
    
    
}
