package id.co.sinarmaslife.testing.junit.numeric;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: TestDividing
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Sep 20, 2007 10:14:29 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import junit.framework.TestCase;
import junit.framework.Assert;

public class TestDividing extends TestCase
{
    public static void test()
    {
        Assert.assertEquals( NumberUtil.divideByDouble(), NumberUtil.divideByInteger() );
    }
}
