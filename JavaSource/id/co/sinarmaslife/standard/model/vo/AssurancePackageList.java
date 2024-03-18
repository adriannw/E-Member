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

public class AssurancePackageList implements Serializable
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
	 * Creation Date    	: Jan 22, 2013 4:02:58 PM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = 6506388597125988252L;

	protected final transient Log logger = LogFactory.getLog( getClass() );

    private String value;
    private String label;
    private Integer jenis_paket;

    public AssurancePackageList()
    {
    }

    public AssurancePackageList( String value, String label, Integer jenis_paket)
    {
        this.value = value;
        this.label = label;
        this.jenis_paket = jenis_paket;
    }
    
    public AssurancePackageList( String value, String label )
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

	public Integer getJenis_paket() {
		return jenis_paket;
	}

	public void setJenis_paket(Integer jenis_paket) {
		this.jenis_paket = jenis_paket;
	}

    
}
