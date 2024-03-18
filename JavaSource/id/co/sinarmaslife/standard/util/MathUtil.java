package id.co.sinarmaslife.standard.util;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: MathUtil
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 2, 2008 3:56:49 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MathUtil
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public static double min( double value1, double value2 )
    {
        return value1 < value2? value1 : value2;
    }

    public static double max( double value1, double value2 )
    {
        return value1 > value2? value1 : value2;
    }

    public static int max( int[] arr )
    {
        return ArrUtil.max( arr );
    }

    public static int min( int[] arr )
    {
        return ArrUtil.min( arr );
    }

    public static double max( double[] arr )
    {
        return ArrUtil.max( arr );
    }

    public static double min( double[] arr )
    {
        return ArrUtil.min( arr );
    }
}
