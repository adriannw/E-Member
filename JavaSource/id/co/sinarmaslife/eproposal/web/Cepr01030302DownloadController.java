package id.co.sinarmaslife.eproposal.web;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01030302DownloadController
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 12, 2007 4:33:49 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000StringUtil;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.FormatDate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

public class Cepr01030302DownloadController extends StandardParent implements Controller
{

    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01030302DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;

//        Cepr01030301Form cepr01030301Form = cepr01030000HoldingForm.getCepr01030301Form();
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();

        AbstractJasperReportsView jasperViewer = new JasperReportsPdfView();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "format", "pdf" );

        Map<JRExporterParameter, Object> exporterParam = new HashMap<JRExporterParameter, Object>();
        exporterParam = JasperUtils.exporterParam(exporterParam);

        jasperViewer.setExporterParameters( exporterParam );
        jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01030302_letter.jasper" );

        jasperViewer.setReportDataKey( "dataSource" );
        params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
        params.put( "policyHolderName", cepr01030101Form.getPolicyHolderName() );
        params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
//        params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate()) );
        params.put( "proposalUser", cepr01030101Form.getProposalUser() );

        String[] productNameArr = new String[]
                {
                        Cepr00000000StringUtil.getRightPartOfAssurancePlanCd( cepr01030101Form.getAssurancePlanCd1() ),
                        Cepr00000000StringUtil.getRightPartOfAssurancePlanCd( cepr01030101Form.getAssurancePlanCd2() ),
                        Cepr00000000StringUtil.getRightPartOfAssurancePlanCd( cepr01030101Form.getAssurancePlanCd3() ),
                        Cepr00000000StringUtil.getRightPartOfAssurancePlanCd( cepr01030101Form.getAssurancePlanCd4() ),
                };

        List<String> productNameList = ArrUtil.toListFromArrayDisregardEmpty( productNameArr );
        List<Map<String, String>> productNameMapList = new ArrayList<Map<String, String>>();

        Map<String, String> productNameMap;
        for( String productName : productNameList )
        {
            productNameMap = new HashMap<String, String>();
            productNameMap.put( "productName", productName.replace("(DMTM)", "").replace("(new)", "").replace("(NEW)", "") );
            productNameMapList.add( productNameMap );
        }
        
        params.put( "dataSource", productNameMapList );

        jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
        return new ModelAndView( jasperViewer, params );
    }
}
