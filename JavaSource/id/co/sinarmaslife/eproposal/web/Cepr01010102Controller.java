package id.co.sinarmaslife.eproposal.web;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01010102Controller
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 12, 2008 11:04:06 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.standard.StandardConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

public class Cepr01010102Controller implements Controller
{
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ModelAndView result;
        request.getSession().setAttribute( StandardConst.CREDENTIALS_SES, null );
        
        // hapus session waktu logout
        request.getSession().invalidate();
        
        result = new ModelAndView( new RedirectView( "/wepr01010101.htm", true ) );
        return result;
    }
}
