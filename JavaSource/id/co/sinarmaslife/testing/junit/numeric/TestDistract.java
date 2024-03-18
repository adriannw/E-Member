package id.co.sinarmaslife.testing.junit.numeric;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: TestDistract
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Sep 21, 2007 2:10:36 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import junit.framework.Assert;
import junit.framework.TestCase;

public class TestDistract extends TestCase
{
    public static void test()
    {
//        Assert.assertEquals( NumberUtil.distract(), 0.1 );
        Assert.assertEquals( NumberUtil.distractBigDecimal(), 0.1 );
    }
}
