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

public class OptionVO implements Serializable
{
    protected final transient Log logger = LogFactory.getLog( getClass() );

    private String value;
    private String label;

    public OptionVO()
    {
        logger.info( "---------- OptionVO constructor is called ..." );
    }

    public OptionVO( String value, String label )
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
}
