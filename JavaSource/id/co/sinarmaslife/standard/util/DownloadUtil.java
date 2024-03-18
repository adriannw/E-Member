package id.co.sinarmaslife.standard.util;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Agency
 * Function Id         	: 
 * Program Name   		: DownloadUtil
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jun 12, 2008 8:25:07 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DownloadUtil
{
	protected final static Log logger = LogFactory.getLog( DownloadUtil.class);
	
    private static void download( HttpServletRequest request, HttpServletResponse response, String dirName, String fileName, String downloadType )
    {
       // logger.info( "*-*-*-* DownloadUtil.download" );
        logger.info( "*-*-*-* DownloadUtil.download"  );
        try
        {
            // downloadType = "inline;" or "attachment;"
            FileUtils.downloadFile( downloadType, dirName, fileName, response );
        }
        catch( FileNotFoundException e )
        {
            logger.error("ERROR", e);
            ErrMsgUtil.showAnError( request, "File '" + fileName + "' tidak ditemukan, silakan hubungi Dept. IT Asuransi Jiwa Sinarmas" );
        }
        catch( IOException e )
        {
            logger.error("ERROR", e);
            ErrMsgUtil.showAnError( request, "Ada masalah pembacaan file, silakan hubungi Administrator Asuransi Jiwa Sinarmas" );
        }
    }

    public static void downloadAttachment( HttpServletRequest request, HttpServletResponse response, String dirName, String fileName )
    {
       // logger.info( "*-*-*-* DownloadUtil.downloadAttachment" );
        logger.info(  "*-*-*-* DownloadUtil.downloadAttachment" );
        download( request, response, dirName, fileName, "attachment;" );
    }

    public static void downloadInline( HttpServletRequest request, HttpServletResponse response, String dirName, String fileName )
    {
       // logger.info( "*-*-*-* DownloadUtil.downloadInline" );
        logger.info( "*-*-*-* DownloadUtil.downloadInline" );
        download( request, response, dirName, fileName, "inline;" );
    }
}
