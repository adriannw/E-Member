package id.co.sinarmaslife.standard.util;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	:
 * Program Name   		: NumberUtil
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 28, 2007 3:24:07 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class NumberUtil
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public NumberUtil()
    {
        logger.info( "*-*-*-* NumberUtil constructor is called ..." );
    }

    public static boolean isNumber( String strNumber )
    {
        Pattern objNotNumberPattern = Pattern.compile( "[^0-9.-]" );
        Pattern objTwoDotPattern = Pattern.compile( "[0-9]*[.][0-9]*[.][0-9]*" );
        Pattern objTwoMinusPattern = Pattern.compile( "[0-9]*[-][0-9]*[-][0-9]*" );
        String strValidRealPattern = "^([-]|[.]|[-.]|[0-9])[0-9]*[.]*[0-9]+$";
        String strValidIntegerPattern = "^([-]|[0-9])[0-9]*$";
        Pattern objNumberPattern = Pattern.compile( "(" + strValidRealPattern + ")|(" + strValidIntegerPattern + ")" );

        return !objNotNumberPattern.matcher( strNumber ).matches() &&
                !objTwoDotPattern.matcher( strNumber ).matches() &&
                !objTwoMinusPattern.matcher( strNumber ).matches() &&
                objNumberPattern.matcher( strNumber ).matches();
    }

    public static boolean isRoundNumber( String strNumber )
    {
        String strValidIntegerPattern = "^([-]|[0-9])[0-9]*$";
        Pattern objNumberPattern = Pattern.compile( strValidIntegerPattern );

        return objNumberPattern.matcher( strNumber ).matches();
    }

    public static BigDecimal toZeroIfEmpty( BigDecimal value )
    {
        return value == null? new BigDecimal( "0" ) : value;
    }

    public static Double toZeroIfEmpty( Double value )
    {
        return value == null? 0 : value;
    }
}