package id.co.sinarmaslife.eproposal.common.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr00000000TextFileReader
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 1, 2007 5:25:36 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cepr00000000TextFileReader
{
    protected static Log logger = LogFactory.getLog( Cepr00000000TextFileReader.class );

    public static String loadReportContentFromTextFile( String realPath, String fileName ) throws IOException
    {

        String ctx = realPath + "/WEB-INF/classes/";
        //logger.info( "*-*-*-* ctx = " + ctx );
        logger.debug( "*-*-*-* ctx = " + ctx );
        String filePath = ctx + "id/co/sinarmaslife/eproposal/source/report/jasper/" + fileName;
       // logger.info( "*-*-*-* filePath = " + filePath );
        logger.debug( "*-*-*-* filePath = " + filePath );
        BufferedReader bufferedReader = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
	        bufferedReader = new BufferedReader( new FileReader( filePath ) );
	        String eachLine = bufferedReader.readLine();
	
	        while( eachLine != null )
	        {
	            stringBuffer.append( eachLine );
	            stringBuffer.append( "\n" );
	            eachLine = bufferedReader.readLine();
	        }
        }finally {
        	try {
        		if (bufferedReader != null) {
        			bufferedReader.close();
        		}
        	}catch(Exception e) {
        		logger.error("bufferedReader cannot be closed", e);
        	}
        }
        return stringBuffer.toString();
    }

//    public static Cepr01040000ManfaatVO loadReportContentFromTextFileToManfaatVO( HttpServletRequest request, String fileName ) throws IOException
//    {
//        Cepr01040000ManfaatVO manfaatVO = new Cepr01040000ManfaatVO();
//        String ctx = request.getSession().getServletContext().getRealPath( "" ) + "\\WEB-INF\\classes\\";
//        String filePath = ctx + "id\\co\\sinarmaslife\\eproposal\\pb\\jasper\\" + fileName;
//        logger.info( "*-*-*-* filePath = " + filePath );
//        BufferedReader bufferedReader = new BufferedReader( new FileReader( filePath ) );
//        StringBuffer stringBuffer = new StringBuffer();
//        String eachLine = bufferedReader.readLine();
//
//        // read first line to store to title
//        if( eachLine != null )
//        {
//            manfaatVO.setTitle( eachLine );
//            eachLine = bufferedReader.readLine();
//        }
//
//        // read other paragraphs to content
//        while( eachLine != null )
//        {
//            stringBuffer.append( eachLine );
//            stringBuffer.append( "\n" );
//            eachLine = bufferedReader.readLine();
//        }
//        manfaatVO.setContent( stringBuffer.toString() );
//        return manfaatVO;
//    }

}
