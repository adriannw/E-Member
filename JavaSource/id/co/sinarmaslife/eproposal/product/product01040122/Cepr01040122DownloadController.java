package id.co.sinarmaslife.eproposal.product.product01040122;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040122DownloadController
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 4, 2008 9:21:45 AM
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
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Cepr01040122DownloadController extends StandardParent implements Controller
{
    static Integer currentIdx;
    protected final Log logger = LogFactory.getLog( getClass() );

    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040122DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
        Cepr01040122Business cepr01040122Business = new Cepr01040122Business( eproposalManager, editorUtil, command );

        ModelAndView result;
        if( request.getSession().getAttribute( "messageEkaSehatList" ) == null )
        {
        	List<String> messageEkaSehatList = cepr01040122Business.getEkaSehatMsgBox();
        	request.getSession().setAttribute( "messageEkaSehatList", messageEkaSehatList );
        	result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        
        if( request.getSession().getAttribute( "messageBoxList" ) == null )
        {
            List<String> messageBoxList = cepr01040122Business.getMedicalMsgBox();
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
            jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040122_product.jasper" );
            
            if(cepr01030102Form.getRightPartOfBusinessCd() == 2){
            	params.put( "title", "INVESTIMAX" );
            }
            params.put( "currencySymbol", currencySymbol );
           // params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            params.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
            
            BigDecimal premiTopUpBerkala = cepr01030102Form.getTopUpPremium();
            
            params.put( "namaTertanggung", cepr01030101Form.getInsuredName() );
            params.put( "usiaTertanggung", cepr01030101Form.getInsuredAge() + " Tahun" );
            params.put( "masaPertanggungan", cepr01030102Form.getTermOfContract() + " Tahun");
            params.put("premiPokok", currencySymbol + " " + editorUtil.convertToTwoDigit(cepr01030102Form.getBasePremium()) );
            params.put("premiTopUp", currencySymbol + " " + editorUtil.convertToTwoDigit(premiTopUpBerkala) );
            params.put("totalPremi", currencySymbol + " " + editorUtil.convertToTwoDigit(cepr01030102Form.getBasePremium().add(premiTopUpBerkala)) );
            params.put("alokasiPremi", currencySymbol + " " + editorUtil.convertToTwoDigit(cepr01030102Form.getBasePremium().add(premiTopUpBerkala)) );
            params.put( "uangPertanggungan", currencySymbol + " " + editorUtil.convertToTwoDigit(cepr01030102Form.getTotalSumInsured()) );

            jasperViewer.setReportDataKey( "dataSource" );
            jasperViewer.setJdbcDataSource( eproposalManager.getDataSource() );

            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
            IllustrationResultVO illustrationResultVO = cepr01040122Business.getIllustrationResult();
            params.put( "validityMsg", illustrationResultVO.getValidityMsg() );
            params.put( "dsEkalinkIllustration", JasperReportsUtils.convertReportData( illustrationResultVO.getIllustrationList() ) );


            logger.info( "*-*-*-* cepr01040122Business.getInvestmentYield() = " + cepr01040122Business.getInvestmentYield() );
            params.put( "totalInvestmentAllocation", cepr01040122Business.getInvestmentYield().getTotalInvestmentAllocation() );
            params.put( "totalAssumptionLow", cepr01040122Business.getInvestmentYield().getTotalAssumptionLow() );
            params.put( "totalAssumptionMid", cepr01040122Business.getInvestmentYield().getTotalAssumptionMid() );
            params.put( "totalAssumptionHi", cepr01040122Business.getInvestmentYield().getTotalAssumptionHi() );

            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( cepr01040122Business.getCommonHeaderRowList() ) );
            params.put( "dsCommonHeaderSmall", JasperReportsUtils.convertReportData( cepr01040122Business.getCommonHeaderSmallRowList() ) );
            params.put( "dsCommonHeaderSmall2", JasperReportsUtils.convertReportData( cepr01040122Business.getCommonHeaderSmallRowList() ) );
            params.put( "dsInvestmentChoice", JasperReportsUtils.convertReportData( cepr01040122Business.getInvestmentChoiceRowList() ) );
            params.put( "dsInvestmentChoiceSmall", JasperReportsUtils.convertReportData( cepr01040122Business.getInvestmentChoiceSmallRowList() ) );
            params.put( "dsMonthlyPremium", JasperReportsUtils.convertReportData( cepr01040122Business.getMonthlyPremiumList( cepr01030102Form ) ) );
            params.put( "dsInvestmentPerformance", JasperReportsUtils.convertReportData( cepr01040122Business.getInvestmentPerformanceList() ) );
            params.put( "dsInvestmentYield", JasperReportsUtils.convertReportData( cepr01040122Business.getInvestmentYield().getYieldList() ) );
            params.put( "dsOneRow", JasperReportsUtils.convertReportData( cepr01040122Business.getOneRowList() ) );
            params.put( "dsNoteSekaligus", JasperReportsUtils.convertReportData( cepr01040122Business.getNoteSekaligusRowList() ) );
            params.put( "dsNoteBerkala", JasperReportsUtils.convertReportData( cepr01040122Business.getNoteBerkalaRowList() ) );
            
            double up = new Double(cepr01030102Form.getTotalSumInsured().toString());
            params.put( "uangPertanggunganPa", currencySymbol + " " + editorUtil.convertToTwoDigit(Math.min(up, 2000000000.0)) );
            
            currentIdx = 0;
            Cepr00000000RiderBusiness riderBusiness = new Cepr00000000RiderBusiness( eproposalManager, editorUtil, command );
            putRiderParam( params, riderBusiness.getRiderPaList(), "dsRiderPa", "riderPaIndex", null );
            params.put( "riderPaRisk", riderBusiness.getRiderPaRisk() );
            params.put( "dsRiderPaClone", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderPaList() ) );
            putRiderParam( params, riderBusiness.getRiderHcpList(), "dsRiderHcp", "riderHcpIndex", cepr01040122Business.getRiderHcpList() );
            putRiderParam( params, riderBusiness.getRiderTpdList(), "dsRiderTpd", "riderTpdIndex", null );
            putRiderParam( params, riderBusiness.getRiderCiList(), "dsRiderCi", "riderCiIndex", null );
            putRiderParam( params, riderBusiness.getRiderWaiverTpdList(), "dsRiderWaiverTpd", "riderWaiverTpdIndex", null );
            putRiderParam( params, riderBusiness.getRiderWaiverCiList(), "dsRiderWaiverCi", "riderWaiverCiIndex", null );
            putRiderParam( params, riderBusiness.getRiderPayorTpdDeathList(), "dsRiderPayorTpdDeath", "riderPayorTpdDeathIndex", null );
            putRiderParam( params, riderBusiness.getRiderPayorCiList(), "dsRiderPayorCi", "riderPayorCiIndex", null );
            putRiderParam( params, riderBusiness.getRiderPayorCiDeathList(), "dsRiderPayorCiDeath", "riderPayorCiDeathIndex", null );
            putRiderParam( params, riderBusiness.getRiderPayorSpouseTpdDeathList(), "dsRiderPayorSpouseTpdDeath", "riderPayorSpouseTpdDeathIndex", null );
            putRiderParam( params, riderBusiness.getRiderHcpFamilyList(), "dsRiderHcpFamily", "riderHcpFamilyIndex", null );
            putRiderParam( params, riderBusiness.getRiderTermRiderList(), "dsRiderTermRider", "riderTermRiderIndex", null );
            params.put( "dsRiderHcpFamilyParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderHcpFamilyParticipantsList() ) );
            params.put( "baseBenefitHcpFam", riderBusiness.computeBaseBenefitHcpFam() );

            params.put( "dataSource", cepr01040122Business.getPageList( currentIdx ) );
            
            params.put( "dsEkalinkRider", JasperReportsUtils.convertReportData( cepr01040122Business.getRiderPremiumList(cepr01030102Form, cepr01030101Form.getInsuredAge()) ) );
            params.put( "dsEkalinkRider2", JasperReportsUtils.convertReportData( cepr01040122Business.getRiderPremiumList(cepr01030102Form, cepr01030101Form.getInsuredAge()) ) );
            	
            jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );

            request.getSession().setAttribute( "messageBoxList", null );
            request.getSession().setAttribute( "messageEkaSehatList", null );
            result = new ModelAndView( jasperViewer, params );
        }

        return result;
    }

    public void putRiderParam( Map<String, Object> params, List contentList, String dsParamName, String idxParamName, String paramEx )
    {
        JRDataSource source = JrUtil.convertToDsAndSetNullIfEmpty( contentList );
        if( source != null ){ 
        	currentIdx = currentIdx + 1;
        	if(dsParamName.equals("dsRiderPa")){
        		params.put( "r1", dsParamName );
        	}else if(dsParamName.equals("dsRiderHcp")){
        		params.put( "r2", dsParamName );
        		params.put( "biayaHcp", paramEx );
        	}else if(dsParamName.equals("dsRiderPayorTpdDeath")){
        		params.put( "r3", dsParamName );
        	}else if(dsParamName.equals("dsRiderPayorCi")){
        		params.put( "r4", dsParamName );
        	}else if(dsParamName.equals("dsRiderWaiverTpd")){
        		params.put( "r5", dsParamName );
        	}else if(dsParamName.equals("dsRiderWaiverCi")){
        		params.put( "r6", dsParamName );
        	}else if(dsParamName.equals("dsRiderTpd")){
        		params.put( "r7", dsParamName );
        	}else if(dsParamName.equals("dsRiderCi")){
        		params.put( "r8", dsParamName );
        	}
        }

        params.put( dsParamName, source );
        params.put( idxParamName, currentIdx.toString() + "." );
    }
    
}
