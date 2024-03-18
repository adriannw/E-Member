package id.co.sinarmaslife.eproposal.common.util;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr00000000EditorUtil
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 23, 2007 3:30:32 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.standard.propertyeditors.CurrencyFormatEditor;
import id.co.sinarmaslife.standard.util.LazyConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

public class Cepr00000000EditorUtil
{
    protected final Log logger = LogFactory.getLog( getClass() );

    protected CustomDateEditor dateEditor;
    protected CustomNumberEditor integerEditor;
    protected CurrencyFormatEditor currencyEditor;
    protected DateFormat dateFormat;

    public void setDateEditor( CustomDateEditor dateEditor )
    {
        this.dateEditor = dateEditor;
    }

    public void setIntegerEditor( CustomNumberEditor integerEditor )
    {
        this.integerEditor = integerEditor;
    }

    public void setCurrencyEditor( CurrencyFormatEditor currencyEditor )
    {
        this.currencyEditor = currencyEditor;
    }

    public void setDateFormat( DateFormat dateFormat )
    {
        this.dateFormat = dateFormat;
    }
    
    public static Integer getAge(Date sysdate, Date birthdate) {
		int n=7;
		DateTime start = new DateTime(birthdate);
		DateTime end = new DateTime(sysdate);
		Months months = Months.monthsBetween(start, end);
		int bln = months.getMonths();
		
		if((bln % 12) < n) return (bln/12);
		else return (bln/12)+1;
	}

    public String convertToString( Object param )
    {
        String result;
        if( param instanceof Date )
        {
            dateEditor.setValue( param );
            result = dateEditor.getAsText();
        }
        else if( param instanceof Integer )
        {
            integerEditor.setValue( param );
            result = integerEditor.getAsText();
        }
        else if( param instanceof BigDecimal )
        {
            currencyEditor.setValue( param );
            result = currencyEditor.getAsText();
        }
        else if( param instanceof Double )
        {
            BigDecimal bdValue = new BigDecimal( ( Double ) param );
            currencyEditor.setValue( bdValue );
            result = currencyEditor.getAsText();
        }
        else if( param instanceof String )
        {
            result = ( String ) param;
        }
        else
        {
            if( param == null )
            {
                result = "";
            }
            else
            {
                result = param.toString();
            }
        }

        return result;
    }

    public String convertToStringWithoutCent( Object param )
    {
        String result = convertToString( param );
        if( !"".equals( result ) )
        {
            result = result.substring( 0, result.length() - 3 );
        }
        return result;
    }

    public String convertToStringWithoutCentAndNillIfNegative( Object param )
    {

//        String result = "";
//        if ( param != null ){
//	        BigDecimal value = ( BigDecimal ) param;
//	        if( value.compareTo( new BigDecimal( "0" ) ) < 0 )
//	        {
//	            result = "nil";
//	        }
//	        else
//	        {
//	            result = convertToStringWithoutCent( param );
//	        }
//        }

        String result = param.toString();
        if ( result != null && !result.equals("")){
	        BigDecimal value = new BigDecimal( result );
	        if( value.compareTo( new BigDecimal( "0" ) ) < 0 )
	        {
	            result = "nil";
	        }
	        else
	        {
	            result = convertToStringWithoutCent( value );
	        }
        }

        return result;
    }

    public String convertToStringRoundPercent( Object param )
    {
        BigDecimal rounded = ( BigDecimal ) param;
        rounded = rounded.setScale( 0, BigDecimal.ROUND_HALF_UP );
        String result = convertToString( rounded );
        if( !"".equals( result ) )
        {
            result = result.substring( 0, result.length() - 3 );
        }
        result = result.concat( "%" );
        return result;
    }

    // Primitive Data Type
    public String convertToString( double param )
    {
        return convertToString( new BigDecimal( param ) );
    }

    public String convertToString( int param )
    {
        return convertToString( new Integer( param ) );
    }

    public String convertToTwoDigit( BigDecimal value )
    {
        DecimalFormat decimalFormat = new DecimalFormat( "###,###,###,###,##0.00" );
        return decimalFormat.format( value );
    }

    public String convertToTwoDigit( double value )
    {
        return convertToTwoDigit( new BigDecimal( value ) );
    }

    public String convertToRoundedTwoDigits( BigDecimal amount )
    {
        String result;
        if(amount == null)amount = new BigDecimal(0);
        amount = amount.setScale( 2, RoundingMode.HALF_UP );
        DecimalFormat decimalFormat = new DecimalFormat( "###,###,###,###,##0.00" );        
        result = decimalFormat.format( amount );

        return result;
    }

    public double convertToRoundedTwoDigitsDouble( BigDecimal amount )
    {
        double result;
        amount = amount.setScale( 2, RoundingMode.FLOOR );
        DecimalFormat decimalFormat = new DecimalFormat( "##.00" );
        String temp = decimalFormat.format( amount );
        result = LazyConverter.toDouble( new BigDecimal( temp ));

        return result;
    }
    
    public double convertToRoundedTwoDigitsDouble( double amount )
    {
    	   return convertToRoundedTwoDigitsDouble( new BigDecimal( amount ) );
    }
    
    public String convertToRoundedTwoDigits( double amount )
    {
        return convertToRoundedTwoDigits( new BigDecimal( amount ) );
    }

    public String convertToRoundedTwoDigits( BigDecimal amount, Cepr01030102Form cepr01030102Form )
    {
        return convertToRoundedTwoDigits( amount );
    }

    public String convertToMoney( BigDecimal value, Cepr01030102Form cepr01030102Form )
    {
        String result = "";
        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
           // result = convertToRoundedNoDigit( value );
            result = convertToRoundedTwoDigits( value );
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            result = convertToRoundedTwoDigits( value );
        }

        return result;
    }
    
    public String convertToMoneyNoRound( BigDecimal value, Cepr01030102Form cepr01030102Form )
    {
        String result = "";
        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            result =   convertToTwoDigitNoRound ( value ) ;
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            result = convertToTwoDigitNoRound ( value );
        }

        return result;
    }
 
    public String convertToMoney( double value, Cepr01030102Form cepr01030102Form )
    {
        return convertToMoney( new BigDecimal( value ), cepr01030102Form );
    }

    public String convertToRoundedNoDigit( BigDecimal amount )
    {
        String result;
        amount = amount.setScale( 0, RoundingMode.HALF_UP );
        DecimalFormat decimalFormat = new DecimalFormat( "###,###,###,###,##0" );
        result = decimalFormat.format( amount );

        return result;
    }
    
    public String convertToTwoDigitTruncate( BigDecimal amount )
    {
        String result;
        amount = amount.setScale( 2, RoundingMode.DOWN );
        DecimalFormat decimalFormat = new DecimalFormat( "###,###,###,###,##0.00" );
        result = decimalFormat.format( amount );

        return result;
    }
    
    public String convertToTwoDigitNoRound( BigDecimal amount )
    {
        String result;
        DecimalFormat decimalFormat = new DecimalFormat( "###,###,###,###,##0.00" );
        result = decimalFormat.format( amount );

        return result;
    }
    
    public String convertToTwoDigitNoRound2( Double amount )
    {
        String result;
        DecimalFormat decimalFormat = new DecimalFormat( "###,###,###,###,##0.00" );
        result = decimalFormat.format( amount );

        return result;
    }
    
}
