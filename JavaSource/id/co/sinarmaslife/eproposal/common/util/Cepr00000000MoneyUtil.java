package id.co.sinarmaslife.eproposal.common.util;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr00000000MoneyUtil
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 6, 2007 4:29:48 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.List;

import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.standard.propertyeditors.CurrencyFormatEditor;
import id.co.sinarmaslife.standard.util.LazyConverter;

public class Cepr00000000MoneyUtil
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public static BigDecimal sum( List<Map<String, Object>> mapList, String keywordToSum )
    {
        // TODO: this setBusinessCdAndEntryInstance should be done by injection
        CurrencyFormatEditor editor = new CurrencyFormatEditor( "###,###,###,###,##0.00" );
        BigDecimal sumResult = new BigDecimal( "0" );
        BigDecimal amount;
        String moneyStr;

        for( Map<String, Object> map : mapList )
        {
            moneyStr = ( String ) map.get( keywordToSum );
            if( moneyStr != null )
            {
                if( !"".equals( moneyStr ) )
                {
                    if( moneyStr.length() > 3 )
                    {
                    	String tandaBaca = moneyStr.substring( moneyStr.length() - 3, moneyStr.length() - 2 );
                    	if( !tandaBaca.equals( "." ) && !tandaBaca.equals( "," ) )
                        {
                            moneyStr = moneyStr + tandaBaca + "00";
                        }
                    }
                    else
                    { 
                        moneyStr = moneyStr + ".00";
                    }
                }
            }

            editor.setAsText( moneyStr );
            amount = ( BigDecimal ) editor.getValue();
            sumResult = sumResult.add( amount );
        }
        return sumResult;
    }
    
    
    public static BigDecimal sumList( List<Map<String, Object>> mapList, String keywordToSum )
    {
        // TODO: this setBusinessCdAndEntryInstance should be done by injection
        CurrencyFormatEditor editor = new CurrencyFormatEditor( "###,###,###,###,##0.00" );
        BigDecimal sumResult = new BigDecimal( "0" );
        BigDecimal amount;
        String moneyStr;

        for( Map<String, Object> map : mapList )
        {
            moneyStr = ( String ) map.get( keywordToSum );
            if( moneyStr != null )
            {
                if( !"".equals( moneyStr ) )
                {
                    if( moneyStr.length() > 3 )
                    {
                    	String tandaBaca = moneyStr.substring( moneyStr.length() - 3, moneyStr.length() - 2 );
                    	if( !tandaBaca.equals( "." ) && !tandaBaca.equals( "," ) )
                        {
                    		 moneyStr = moneyStr + ".00";
                        }
                    }
                    else
                    { 
                        moneyStr = moneyStr + ".00";
                    }
                }
            }

            editor.setAsText( moneyStr );
            amount = ( BigDecimal ) editor.getValue();
            sumResult = sumResult.add( amount );
        }
        return sumResult;
    }

    public static BigDecimal rounding( BigDecimal amount )
    {
        if( amount != null )
        {
            amount = amount.setScale( 0, RoundingMode.HALF_UP );
        }
        return amount;
    }
    

    public static BigDecimal rounding( BigDecimal amount, String currency )
    {
    	if( currency.equals( Hardcode.CUR_USD_CD ) ){
    		
    	}else{
    		   if( amount != null )
    	        {
    	            amount = amount.setScale( 0, RoundingMode.HALF_UP );
    	        }
    	}
        return amount;
    }
    
    public static BigDecimal roundingOneDecimal ( BigDecimal amount )
    {
        String amountStr = amount.toString();
        String temp = amountStr.substring( amountStr.indexOf(".") +1, amountStr.length());
        if( !amountStr.substring( amountStr.indexOf(".") +1 , amountStr.indexOf(".") + 3 ).equals( "00" ) )
        {
        	temp = temp.substring(0 , 1)+"."+ temp.substring(1 , temp.length());
        	BigDecimal temp3 = new BigDecimal(temp).setScale(0,BigDecimal.ROUND_HALF_UP);
        	if( temp3.toString().contains(".") )
        	{
        		temp = temp3.toString().substring(0,1) + temp3.toString().substring(temp3.toString().indexOf(".")+1, temp3.toString().length());
        	}
        	else
        	{
        		temp = temp3.toString();
        	}
        	amountStr = amountStr.substring(0,amountStr.indexOf(".")+1) + temp;
        	amount = new BigDecimal( amountStr );
        }
        return amount;
    }

    public static double rounding( double amount )
    {
        return LazyConverter.toDouble( rounding( new BigDecimal( amount ) ) );
    }
}
