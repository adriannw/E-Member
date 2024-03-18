package id.co.sinarmaslife.eproposal.web;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01030402
 * Program Name   		: Cepr01030402DownloadController
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 11, 2007 10:28:51 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.jasperreports.AbstractJasperReportsView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.lowagie.text.pdf.PdfWriter;

public class Cepr01030402DownloadController extends StandardParent implements Controller
{
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01030402DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030401Form cepr01030401Form = cepr01030000HoldingForm.getCepr01030401Form();

        AbstractJasperReportsView jasperViewer = new JasperReportsPdfView();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "format", "pdf" );

        Map<JRExporterParameter, Object> exporterParam = new HashMap<JRExporterParameter, Object>();
        exporterParam = JasperUtils.exporterParam(exporterParam);

        jasperViewer.setExporterParameters( exporterParam );
        jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01030402_cover.jasper" );

        jasperViewer.setReportDataKey( "dataSource" );
        params.put( "dataSource", eproposalManager.selectTest() );
        params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
        params.put( "customerName", cepr01030401Form.getCustomerName() );
        params.put( "agentName", cepr01030401Form.getAgentName() );
        params.put( "branchName", cepr01030401Form.getBranchName() );
        params.put( "homePhoneNo", cepr01030401Form.getHomePhoneNo() );
        params.put( "mobilePhoneNo", cepr01030401Form.getMobilePhoneNo() );

        jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
        
        return new ModelAndView( jasperViewer, params );
    }
}

