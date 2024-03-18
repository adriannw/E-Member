package id.co.sinarmaslife.eproposal.product.product01040206;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040204DownloadController
 * Description         	: Excel Link 80 / Eka Link 80 (115)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 8, 2008 2:26:26 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import com.lowagie.text.pdf.PdfWriter;
import id.co.sinarmaslife.eproposal.business.Cepr00000000RiderBusiness;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.standard.util.*;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.jasperreports.AbstractJasperReportsView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Cepr01040206DownloadController extends StandardParent implements Controller
{
    static Integer currentIdx;
    protected final Log logger = LogFactory.getLog( getClass() );

    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040206DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
        Cepr01040206Business cepr01040206Business = new Cepr01040206Business( eproposalManager, editorUtil, command );

        ModelAndView result;
        if( request.getSession().getAttribute( "messageEkaSehatList" ) == null )
        {
        	List<String> messageEkaSehatList = cepr01040206Business.getEkaSehatMsgBox();
        	request.getSession().setAttribute( "messageEkaSehatList", messageEkaSehatList );
        	result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        
        if( request.getSession().getAttribute( "messageBoxList" ) == null )
        {
            List<String> messageBoxList = cepr01040206Business.getMedicalMsgBox();
            request.getSession().setAttribute( "messageBoxList", messageBoxList );
            result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        else
        {
            AbstractJasperReportsView jasperViewer = new JasperReportsPdfView();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put( "format", "pdf" );

            Map<JRExporterParameter, Object> exporterParam = new HashMap<JRExporterParameter, Object>();
            exporterParam = JasperUtils.exporterParam(exporterParam);

            jasperViewer.setExporterParameters( exporterParam );
            jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040206_product.jasper" );

            params.put( "title", "EKA LINK 80 SYARIAH" );
            params.put( "currencySymbol", currencySymbol );
            // disclaimer =========================================================================================================
          //  params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            //=====================================================================================================================
            
            params.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
            logger.info( "*-*-*-* cepr01030102Form.getCurrencyCd() = " + cepr01030102Form.getCurrencyCd() );

            jasperViewer.setReportDataKey( "dataSource" );
            jasperViewer.setJdbcDataSource( eproposalManager.getDataSource() );

            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
            IllustrationResultVO illustrationResultVO = cepr01040206Business.getIllustrationResult();
            params.put( "validityMsg", illustrationResultVO.getValidityMsg() );
            params.put( "dsEkalinkIllustration", JasperReportsUtils.convertReportData( illustrationResultVO.getIllustrationList() ) );


            logger.info( "*-*-*-* cepr01040206Business.getInvestmentYield() = " + cepr01040206Business.getInvestmentYield() );
            params.put( "totalInvestmentAllocation", cepr01040206Business.getInvestmentYield().getTotalInvestmentAllocation() );
            params.put( "totalAssumptionLow", cepr01040206Business.getInvestmentYield().getTotalAssumptionLow() );
            params.put( "totalAssumptionMid", cepr01040206Business.getInvestmentYield().getTotalAssumptionMid() );
            params.put( "totalAssumptionHi", cepr01040206Business.getInvestmentYield().getTotalAssumptionHi() );

            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( cepr01040206Business.getCommonHeaderRowList() ) );
            params.put( "dsCommonHeaderSmall", JasperReportsUtils.convertReportData( cepr01040206Business.getCommonHeaderSmallRowList() ) );
            params.put( "dsInvestmentChoice", JasperReportsUtils.convertReportData( cepr01040206Business.getInvestmentChoiceRowList() ) );
            params.put( "dsInvestmentChoiceSmall", JasperReportsUtils.convertReportData( cepr01040206Business.getInvestmentChoiceSmallRowList() ) );
            params.put( "dsMonthlyPremium", JasperReportsUtils.convertReportData( cepr01040206Business.getMonthlyPremiumList( cepr01030102Form ) ) );
            params.put( "dsInvestmentPerformance", JasperReportsUtils.convertReportData( cepr01040206Business.getInvestmentPerformanceSyariahList() ) );
            params.put( "dsInvestmentYield", JasperReportsUtils.convertReportData( cepr01040206Business.getInvestmentYield().getYieldList() ) );
            params.put( "dsOneRow", JasperReportsUtils.convertReportData( cepr01040206Business.getOneRowList() ) );

            currentIdx = 0;
            Cepr00000000RiderBusiness riderBusiness = new Cepr00000000RiderBusiness( eproposalManager, editorUtil, command );
            putRiderParam( params, riderBusiness.getRiderPaList(), "dsRiderPa", "riderPaIndex" );
            params.put( "riderPaRisk", riderBusiness.getRiderPaRisk() );
            params.put( "dsRiderPaClone", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderPaList() ) );
            putRiderParam( params, riderBusiness.getRiderHcpList(), "dsRiderHcp", "riderHcpIndex" );
            putRiderParam( params, riderBusiness.getRiderHcpFamilyList(), "dsRiderHcpFamily", "riderHcpFamilyIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorTpdDeathList(), "dsRiderPayorTpdDeath", "riderPayorTpdDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorCiDeathList(), "dsRiderPayorCiDeath", "riderPayorCiDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorCiList(), "dsRiderPayorCi", "riderPayorCiIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorSpouseTpdDeathList(), "dsRiderPayorSpouseTpdDeath", "riderPayorSpouseTpdDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderWaiverTpdList(), "dsRiderWaiverTpd", "riderWaiverTpdIndex" );
            putRiderParam( params, riderBusiness.getRiderWaiverCiList(), "dsRiderWaiverCi", "riderWaiverCiIndex" );
            putRiderParam( params, riderBusiness.getRiderTpdList(), "dsRiderTpd", "riderTpdIndex" );
            putRiderParam( params, riderBusiness.getRiderCiList(), "dsRiderCi", "riderCiIndex" );
            putRiderParam( params, riderBusiness.getRiderTermRiderList(), "dsRiderTermRider", "riderTermRiderIndex" );
            params.put( "dsRiderHcpFamilyParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderHcpFamilyParticipantsList() ) );
            params.put( "baseBenefitHcpFam", riderBusiness.computeBaseBenefitHcpFam() );

            params.put( "dataSource", cepr01040206Business.getPageList( currentIdx ) );

            // Save E-Proposal PDF ke EBServer dan PopUp Download
            // ** Adrian@28/11/2013            
            String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
            String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
            Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();
          //  String fileName="testx.pdf";
            String fileName = msag_id+"_"+no_proposal+".pdf";
            params.put( "no_proposal",  no_proposal );
            
            List temp = cepr01040206Business.getPageList( currentIdx );
            String rootDir_EProp="";
            if(flag_test==1){
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP_TEST;
            }else{
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP;
            }
           String dirName = rootDir_EProp+"\\"+msag_id;    
            
            File sourceFile = new File( dirName + "\\" + fileName );
           try{      
	        	   //if(cekDataApprovedOrNot.get(0).getPosisi()<3){
			            JasperUtils.exportReportToPdf(
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040206_product.jasper",
			            		dirName,
			                fileName,
							params,
							temp,
							PdfWriter.AllowPrinting,
			                null,
			                null
			            );
			            JasperUtils.downloadReportPDF(response, dirName, fileName);        	   
		    	   //}
	    	}catch( Exception e )
	        {
	    		
	            logger.error("ERROR", e);	
	        } 
            
            jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
            request.getSession().setAttribute( "messageBoxList", null );
            request.getSession().setAttribute( "messageEkaSehatList", null );
            result = new ModelAndView( jasperViewer, params );
        }

        return result;
    }

    public void putRiderParam( Map<String, Object> params, List contentList, String dsParamName, String idxParamName )
    {
        JRDataSource source = JrUtil.convertToDsAndSetNullIfEmpty( contentList );
        if( source != null ) currentIdx = currentIdx + 1;

        params.put( dsParamName, source );
        params.put( idxParamName, currentIdx.toString() + "." );
    }
}
