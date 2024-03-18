package id.co.sinarmaslife.testing.junit.numeric;

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
 * Creation Date    	: Sep 20, 2007 10:08:18 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;

public class NumberUtil
{
    protected final static Log logger = LogFactory.getLog( NumberUtil.class );

    public NumberUtil()
    {
        logger.info( "*-*-*-* NumberUtil constructor is called ..." );
    }

    public static double divideByInteger()
    {
       // logger.info( "*-*-*-* NumberUtil.divideByInteger" );
        logger.info( "*-*-*-* NumberUtil.divideByInteger"  );
        double result = ( ( double ) 1 / 12 ) * 12;
       // logger.info( "*-*-*-* result = " + result );
        logger.debug( "*-*-*-* result = " + result  );
        return result;
    }

    public static double divideByDouble()
    {
        //logger.info( "*-*-*-* NumberUtil.divideByDouble" );
        logger.info( "*-*-*-* NumberUtil.divideByDouble"  );
        double num = 1;
        double result = ( num / ( double ) 12 ) * 12;
        //logger.info( "*-*-*-* result = " + result );
        logger.debug( "*-*-*-* result = " + result);
        return result;
    }

    public static double distract()
    {
       // logger.info( "*-*-*-* NumberUtil.distract" );
        logger.info(  "*-*-*-* NumberUtil.distract" );
        Double num = new Double( "0.9" );
        Double result =  ( ( double ) 1.0 - num );
       // logger.info( "*-*-*-* result = " + result );
        logger.debug(  "*-*-*-* result = " + result );
        return result;
    }

    public static BigDecimal distractBigDecimal()
    {
        //logger.info( "*-*-*-* NumberUtil.distractBigDecimal" );
        logger.info(  "*-*-*-* NumberUtil.distractBigDecimal" );
        BigDecimal a = new BigDecimal(1);
        BigDecimal b = new BigDecimal(0.9);
        return a.subtract(b);
    }

}
