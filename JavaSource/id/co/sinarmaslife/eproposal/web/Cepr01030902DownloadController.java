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
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000StringUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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

import id.co.sinarmaslife.eproposal.business.Cepr01030901Business;

import com.lowagie.text.pdf.PdfWriter;

public class Cepr01030902DownloadController extends StandardParent implements Controller
{
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    { 
    	 Cepr01030901Business cepr01030901Business;
    	 cepr01030901Business = new Cepr01030901Business( eproposalManager, editorUtil); 
    	
    	List<Map<String, String>> listMapDPLK;
    	listMapDPLK = cepr01030901Business.listMapDPLK(request, response);
 	      
    	 Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030901Form cepr01030901Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030901Form();
  	   	 	  	   	
         AbstractJasperReportsView jasperViewer = new JasperReportsPdfView();
         Map<String, Object> params = new HashMap<String, Object>();
         params.put( "format", "pdf" );

         Map<JRExporterParameter, Object> exporterParam = new HashMap<JRExporterParameter, Object>();
         exporterParam = JasperUtils.exporterParam(exporterParam);

         jasperViewer.setExporterParameters( exporterParam );
         jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01030901_dplk.jasper" );

         jasperViewer.setReportDataKey( "dataSource" );
         params.put( "dataSource", listMapDPLK );
         params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.dplk" ) );
         params.put( "customerName", cepr01030901Form.getCustomerName().toUpperCase() );
         params.put( "customerCount", String.valueOf(cepr01030901Form.getCustomerCount()) );
         params.put( "customerAge", String.valueOf(cepr01030901Form.getCustomerAge()) );               
         params.put( "normalAgePension", String.valueOf(cepr01030901Form.getNormalAgePension()) );
         params.put( "income", editorUtil.convertToTwoDigitNoRound(new BigDecimal(cepr01030901Form.getIncome())));
         params.put( "pastWorkingPeriod",  editorUtil.convertToTwoDigitNoRound(cepr01030901Form.getPastWorkingPeriod()));
         Integer nextWorkingPeriod = (cepr01030901Form.getNormalAgePension() - cepr01030901Form.getCustomerAge())*12;
         params.put( "nextWorkingPeriod",  String.valueOf(nextWorkingPeriod));
         params.put( "percentPremiumFirstMonth", editorUtil.convertToTwoDigitNoRound(cepr01030901Form.getPercentPremiumFirstMonth()));
      
         params.put( "percentPremiumCompany",  editorUtil.convertToTwoDigitNoRound(cepr01030901Form.getPercentPremiumCompany()));
         params.put( "percentPremiumEmployee", editorUtil.convertToTwoDigitNoRound(cepr01030901Form.getPercentPremiumEmployee()));
         double amountPremiumFirstMonth = (LazyConverter.toDouble( cepr01030901Form.getPercentPremiumFirstMonth())*0.01)*cepr01030901Form.getIncome();
         params.put( "amountPremiumFirstMonth",  editorUtil.convertToTwoDigitNoRound(new BigDecimal(amountPremiumFirstMonth)));
         params.put( "amountFundTransfer",  editorUtil.convertToTwoDigitNoRound(new BigDecimal(cepr01030901Form.getAmountFundTransfer())));
         params.put( "percentIncomeIncreasePerYear", editorUtil.convertToTwoDigitNoRound(cepr01030901Form.getPercentIncomeIncreasePerYear()));
         params.put( "percentInterestInvestPerYear", editorUtil.convertToTwoDigitNoRound(cepr01030901Form.getPercentInterestInvestPerYear()));
         
         jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
         return new ModelAndView( jasperViewer, params );
    	
    	
    }
}

