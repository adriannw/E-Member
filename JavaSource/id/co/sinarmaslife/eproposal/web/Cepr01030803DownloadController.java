package id.co.sinarmaslife.eproposal.web;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01030801DownloadController
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : fadly
 * Version              : 1.0
 * Creation Date    	: Jul 23, 2008 11:30:38 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.standard.util.DownloadUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class Cepr01030803DownloadController extends StandardParent implements Controller
{

    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException
    {
        logger.info( "*-*-*-* Cepr01030803DownloadController.handleRequest" );

//        String dirName = (request.getRequestURL().toString()).replace("/wepr01030801.pdf", "");
        String dirName = request.getSession().getServletContext().getRealPath( "" )+"\\include\\brochures";
//        String dirName = "C:\\ebserver\\pdfind\\E-Proposal";
//        String dirName = "d:\\EproposalLite\\pdf";
        String fileName = "PEDOMAN_FORM_PROFIL_RISIKO_NASABAH.pdf";

        DownloadUtil.downloadInline( request, response, dirName, fileName );
        logger.info( "*-*-*-* Redirect to message" );

        return new ModelAndView( "CEPR00000000ErrorJSP" );
    }
}
