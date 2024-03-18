package id.co.sinarmaslife.standard.util;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: JrUtil
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Dec 24, 2007 2:10:56 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.jasperreports.JasperReportsUtils;
import net.sf.jasperreports.engine.JRDataSource;

import java.util.List;

public class JrUtil
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public static JRDataSource convertToDsAndSetNullIfEmpty( List list )
    {
        return ( list != null && list.size() > 0)? JasperReportsUtils.convertReportData( list ) : null;
    }
}
