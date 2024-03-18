package id.co.sinarmaslife.eproposal.common.util;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr00000000StringUtil
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 27, 2008 3:24:46 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import id.co.sinarmaslife.standard.util.StringUtil;

public class Cepr00000000StringUtil
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public static String getLeftPartOfBusinessCd( String businessCd )
    {
        return  StringUtil.getLeftPart( businessCd, "~X" );
    }

    public static String getRightPartOfBusinessCd( String businessCd )
    {
        return  StringUtil.getRightPart( businessCd, "~X" );
    }

    public static String getLeftPartOfAssurancePlanCd( String assurancePlanCd )
    {
        return  StringUtil.getLeftPart( assurancePlanCd, "==>" );
    }

    public static String getRightPartOfAssurancePlanCd( String assurancePlanCd )
    {
        return  StringUtil.getRightPart( assurancePlanCd, "==>" );
    }
    
    public static String toString(BigDecimal value){
    	return value.toPlainString();
    }
    
    public static String toString(Double value){
    	return new BigDecimal(value).toPlainString();
    }
    
    public static String toString(Integer value){
    	return value.toString();
    }

}
