package id.co.sinarmaslife.eproposal.common.util;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr00000000CurrencyUtil
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 3, 2007 8:40:44 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;

import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.standard.util.AngkaTerbilang;
import id.co.sinarmaslife.standard.util.FormatNumber;

public class Cepr00000000CurrencyUtil
{
    protected final Log logger = LogFactory.getLog( getClass() );

    // added by Samuel
    public static String sayForMoney( BigDecimal jumlah )
    {
        String amount = jumlah.toBigIntegerExact().toString();
        amount = AngkaTerbilang.indonesian( amount );
        return amount;
    }

    public static String sayForMoney( Double jumlah )
    {
        return sayForMoney( new BigDecimal( jumlah ) );
    }

    public static String sayForMoney( BigDecimal jumlah, String currencyCd )
    {
//    	jumlah = new BigDecimal ( FormatNumber.round(jumlah.doubleValue(), 0) );
    	jumlah = jumlah.setScale(0, BigDecimal.ROUND_HALF_UP );
        String amount = jumlah.toBigIntegerExact().toString();
        amount = AngkaTerbilang.indonesian( amount );
        String currencySay;
        if( Hardcode.CUR_IDR_CD.equals( currencyCd ) )
        {
            currencySay = "rupiah";
        }
        else if( Hardcode.CUR_USD_CD.equals( currencyCd ) )
        {
            currencySay = "dollar";
        }
        else
        {
            currencySay = "";
        }
        return amount.concat( " " ).concat( currencySay );
    }

    public static String sayForMoney( Double jumlah, String currencyCd )
    {
        return sayForMoney( new BigDecimal( jumlah ), currencyCd );
    }
}
