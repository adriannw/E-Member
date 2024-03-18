package id.co.sinarmaslife.standard.util;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: LazyConverter
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jan 14, 2008 3:09:46 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;

public class LazyConverter
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public LazyConverter()
    {
        logger.info( "*-*-*-* LazyConverter constructor is called ..." );
    }

    /**
     * if null return defaultValueIfNull otherwise convert BigDecimal to double
     * @param value: bigdecimal value
     * @param defaultValueIfNull: double value
     * @return double
     */
    public static double toDouble( BigDecimal value, double defaultValueIfNull )
    {
        double result;
        if( value == null )
        {
            result = defaultValueIfNull;
        }
        else
        {
            result = value.doubleValue();
        }
        return result;
    }

    /**
     * if null return 0 otherwise convert BigDecimal to double
     * @param value: bigdecimal value
     * @return double
     */
    public static double toDouble( BigDecimal value )
    {

        return toDouble( value, 0 );
    }

      /**
     * if null return defaultValueIfNull otherwise convert BigDecimal to int
     * @param value: bigdecimal value
     * @param defaultValueIfNull: int value
     * @return int
     */
    public static int toInt( Integer value, int defaultValueIfNull )
    {
        int result;
        if( value == null )
        {
            result = defaultValueIfNull;
        }
        else
        {
            result = value;
        }
        return result;
    }

        /**
     * if null return 0 otherwise convert BigDecimal to int
     * @param value: bigdecimal value
     * @return int
     */
    public static int toInt( Integer value )
    {
        return toInt( value, 0 );
    }

    /**
     * if null or convertion error return defaultValueIfNull otherwise convert String to int
     * @param value: String value
     * @param defaultValueIfNull: int value
     * @return int
     */
    public static int toInt( String value, int defaultValueIfNull )
    {
        int result;
        if( value == null )
        {
            result = defaultValueIfNull;
        }
        else
        {
            value = value.trim();
            try
            {
                result = Integer.parseInt( value );
            }
            catch( NumberFormatException e )
            {
                result = defaultValueIfNull;
            }
        }
        return result;
    }

    /**
     * if null or convertion error return 0 otherwise convert String to int
     * @param value: String value
     * @return int
     */
    public static int toInt( String value )
    {
        return toInt( value, 0 );
    }

    /**
     * if null or convertion error return 0 otherwise convert String to int
     * @param value: String value
     * @return int
     */
    public static String toString( int value )
    {
        return Integer.toString( value );
    }
}
