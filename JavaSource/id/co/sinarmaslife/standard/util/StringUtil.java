package id.co.sinarmaslife.standard.util;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: StringUtil
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 18, 2007 2:35:51 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringUtil
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public StringUtil()
    {
        logger.info( "*-*-*-* StringUtil constructor is called ..." );
    }

    public static boolean isEmpty(Object cek) {
		if(cek==null) return true;
		else	if(cek instanceof String) {
			String tmp = (String) cek;
				if(tmp.trim().equals("")) return true;
				else return false;
		}else if(cek instanceof List) {
			List tmp = (List) cek;
			return tmp.isEmpty();
		}else if(cek instanceof Map){
			return ((Map) cek).isEmpty();
		}else if(cek instanceof Integer || cek instanceof Long|| cek instanceof Double|| cek instanceof Float|| cek instanceof BigDecimal || cek instanceof Date){
			return false;
		}
		return true;
	}

    public static String getLeftPart( String str, String separator )
    {
        String result = null;
        String[] partArr = str.split( separator );
        if( partArr != null && partArr.length > 0 ) result = partArr[ 0 ];
        return result;
    }

    public static String getRightPart( String str, String separator )
    {
        String result = null;
        String[] partArr = str.split( separator );
        if( partArr != null && partArr.length > 1 ) result = partArr[ 1 ];
        return result;
    }

    public static String camelHumpAndTrim( String words )
    {
        StringBuffer result = new StringBuffer( "" );
        String ch;
        boolean isCap;
        words = words.trim();
        int size = words.length();

        for( int i = 0; i < size; i++ )
        {
            ch = words.substring( i, i + 1 );
            if( i == 0 )
            {
                isCap = true;
            }
            else if( ch.equals( " " ) )
            {
                result = result.append( " " );
                isCap = true;
                i++;
                ch = words.substring( i, i + 1 );
            }
            else
            {
                isCap = false;
            }

            ch = isCap? ch.toUpperCase() : ch.toLowerCase();
            result = result.append( ch );
        }

        return result.toString();
    }
}
