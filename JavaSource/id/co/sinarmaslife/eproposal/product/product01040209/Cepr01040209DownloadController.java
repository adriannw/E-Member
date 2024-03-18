package id.co.sinarmaslife.eproposal.product.product01040209;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040209DownloadController
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
import id.co.sinarmaslife.eproposal.web.Cepr01030103Form;
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
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Cepr01040209DownloadController extends StandardParent implements Controller
{
    static Integer currentIdx;
    protected final Log logger = LogFactory.getLog( getClass() );

    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040209DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01030103Form cepr01030103Form = cepr01030000HoldingForm.getCepr01030103Form();
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
        Cepr01040209Business cepr01040209Business = new Cepr01040209Business( eproposalManager, editorUtil, command );

        ModelAndView result;
        if( request.getSession().getAttribute( "messageEkaSehatList" ) == null )
        {
        	List<String> messageEkaSehatList = cepr01040209Business.getEkaSehatMsgBox();
        	request.getSession().setAttribute( "messageEkaSehatList", messageEkaSehatList );
        	result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        
        if( request.getSession().getAttribute( "messageBoxList" ) == null )
        {
            List<String> messageBoxList = cepr01040209Business.getMedicalMsgBox();
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
            jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040209_product.jasper" );

            String title = "AMANAH LINK";
            if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040209Mapper.X2 ) title = "BERKAH LINK";      
            
            params.put( "title", title );
            params.put( "currencySymbol", currencySymbol );
            // disclaimer =========================================================================================================
           // params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            //=====================================================================================================================
            
            params.put( "currencyCd", cepr01030102Form.getCurrencyCd() );

            jasperViewer.setReportDataKey( "dataSource" );
            jasperViewer.setJdbcDataSource( eproposalManager.getDataSource() );

            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
            params.put( "logoBismilah", eproposalManager.getProps().getProperty( "logo.bismilah" ) );
            IllustrationResultVO illustrationResultVO = cepr01040209Business.getIllustrationResult();
            params.put( "validityMsg", illustrationResultVO.getValidityMsg() );
            params.put( "dsEkalinkIllustration", JasperReportsUtils.convertReportData( illustrationResultVO.getIllustrationList() ) );


            logger.info( "*-*-*-* cepr01040209Business.getInvestmentYield() = " + cepr01040209Business.getInvestmentYield() );
            params.put( "totalInvestmentAllocation", cepr01040209Business.getInvestmentYield().getTotalInvestmentAllocation() );
            params.put( "totalAssumptionLow", cepr01040209Business.getInvestmentYield().getTotalAssumptionLow() );
            params.put( "totalAssumptionMid", cepr01040209Business.getInvestmentYield().getTotalAssumptionMid() );
            params.put( "totalAssumptionHi", cepr01040209Business.getInvestmentYield().getTotalAssumptionHi() );

            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( cepr01040209Business.getCommonHeaderRowList() ) );
            params.put( "dsCommonHeaderSmall", JasperReportsUtils.convertReportData( cepr01040209Business.getCommonHeaderSmallRowList() ) );
            params.put( "dsCommonHeaderSmall2", JasperReportsUtils.convertReportData( cepr01040209Business.getCommonHeaderSmallRowList() ) );
            params.put( "dsCommonHeaderSmall3", JasperReportsUtils.convertReportData( cepr01040209Business.getCommonHeaderSmallRowList() ) );
            params.put( "dsInvestmentChoice", JasperReportsUtils.convertReportData( cepr01040209Business.getInvestmentChoiceRowList() ) );
            params.put( "dsInvestmentChoiceSmall", JasperReportsUtils.convertReportData( cepr01040209Business.getInvestmentChoiceSmallRowList() ) );
            params.put( "dsMonthlyPremium", JasperReportsUtils.convertReportData( cepr01040209Business.getMonthlyPremiumList( cepr01030102Form ) ) );
            params.put( "dsInvestmentPerformance", JasperReportsUtils.convertReportData( cepr01040209Business.getInvestmentPerformanceList() ) );
            params.put( "dsInvestmentYield", JasperReportsUtils.convertReportData( cepr01040209Business.getInvestmentSyariahYield().getYieldList() ) );
            
            params.put( "dsNoteSekaligus", JasperReportsUtils.convertReportData( cepr01040209Business.getNoteSekaligusRowList() ) );
            params.put( "dsNoteBerkala", JasperReportsUtils.convertReportData( cepr01040209Business.getNoteBerkalaRowList() ) );
            
            double up = new Double(cepr01030102Form.getTotalSumInsured().toString());
            params.put( "uangPertanggunganPa", currencySymbol + " " + editorUtil.convertToTwoDigit(Math.min(up, 2000000000.0)) );
            currentIdx = 0;
            Cepr00000000RiderBusiness riderBusiness = new Cepr00000000RiderBusiness( eproposalManager, editorUtil, command );
            putRiderParam( params, riderBusiness.getRiderPaSyariahList(), "dsRiderPa", "riderPaIndex" );
            params.put( "riderPaRisk", riderBusiness.getRiderPaRisk() );
            params.put( "dsRiderPaClone", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderPaSyariahList() ) );
            params.put( "riderLadiesInsTitle", MappingUtil.getLabel(cepr01030103Form.getLadiesInsList(), cepr01030103Form.getLadiesInsCd()));
            putRiderParam( params, riderBusiness.getRiderHcpSyariahList(), "dsRiderHcp", "riderHcpIndex" );
            putRiderParam( params, riderBusiness.getRiderHcpFamilySyariahList(), "dsRiderHcpFamily", "riderHcpFamilyIndex" );
            putRiderParam( params, riderBusiness.getRiderHcpLadiesSyariahList(), "dsRiderHcpLadies", "riderHcpLadiesIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorTpdCiDeathSyariahList(), "dsRiderPayorTpdCiDeath", "riderPayorTpdCiDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderTpdSyariahList(), "dsRiderTpd", "riderTpdIndex" );
            putRiderParam( params, riderBusiness.getRiderTermRiderList(), "dsRiderTermRider", "riderTermRiderIndex" );
            putRiderParam( params, riderBusiness.getRiderLadiesInsSyariahList(), "dsRiderLadiesIns", "riderLadiesInsIndex" );
            putRiderParam( params, riderBusiness.getRiderWaiverTpdCiSyariahList(), "dsRiderWaiverTpdCi", "riderWaiverTpdCiIndex" );
            putRiderParam( params, riderBusiness.getRiderEkaSehatSyariahListMap(), "dsRiderEkaSehat", "riderEkaSehatIndex");
            putRiderParam( params, riderBusiness.getRiderHcpProviderSyariahList(), "dsRiderHcpProvider", "riderHcpProviderIndex" );
            putRiderParam( params, riderBusiness.getRiderCiSyariahList(), "dsRiderCi", "riderCiIndex" );
            putRiderParam( params, riderBusiness.getRiderEkaSehatInnerLimitSyariahListMap(), "dsRiderEkaSehatInnerLimit", "riderEkaSehatInnerLimitIndex");
            putRiderParam( params, riderBusiness.getRiderLadiesMedExpenseSyariahListMap(), "dsRiderLadiesMedExpense", "riderLadiesMedExpenseIndex");
            putRiderParam( params, riderBusiness.getRiderLadiesMedExpenseInnerSyariahLimitListMap(), "dsRiderLadiesMedExpenseInnerLimit", "riderLadiesMedExpenseInnerLimitIndex");
            
            putRiderParam( params, riderBusiness.getRiderPayorTpdDeathSyariahList(), "dsRiderPayorTpdDeath", "riderPayorTpdDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorCiDeathSyariahList(), "dsRiderPayorCiDeath", "riderPayorCiDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorCiSyariahList(), "dsRiderPayorCi", "riderPayorCiIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorSpouseTpdDeathSyariahList(), "dsRiderPayorSpouseTpdDeath", "riderPayorSpouseTpdDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderWaiverTpdSyariahList(), "dsRiderWaiverTpd", "riderWaiverTpdIndex" );
            putRiderParam( params, riderBusiness.getRiderWaiverCiSyariahList(), "dsRiderWaiverCi", "riderWaiverCiIndex" );
           
            Integer titlePayor5Tpd10CiDeath = cepr01030102Form.getPremiumFurloughYear();
            params.put( "titlePayor5Tpd10CiDeath", titlePayor5Tpd10CiDeath.toString() );
            String rider5Tpd10CiTitle = cepr01030102Form.getPremiumFurloughYear().toString();
            params.put( "riderWaiver5Tpd10CiTitle", rider5Tpd10CiTitle );
            putRiderParam( params, riderBusiness.getRiderPayor5Tpd10CiDeathSyariahList(), "dsRiderPayor5Tpd10CiDeath", "riderPayor5Tpd10CiDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderWaiver5Tpd10CiSyariahList(), "dsRiderWaiver5Tpd10Ci", "riderWaiver5Tpd10CiIndex" );
            
            params.put( "dsRiderLadiesInsSummary", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesInsSyariahSummaryList() ) );
            params.put( "participantLadiesMedExpense", riderBusiness.checkParticipantLadiesMedExpense() );
            params.put( "participantEkaSehatInnerLimit", riderBusiness.checkParticapantEkaSehatInnerLimit() );
            params.put( "dsRiderEkaSehatInnerLimitParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderEkaSehatInnerLimitParticipantsList() ) );
            params.put( "dsRiderHcpFamilyParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderHcpFamilyParticipantsList() ) );
            params.put( "dsRiderHcpLadiesParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderHcpLadiesParticipantsList() ) );
            params.put( "baseBenefitHcpFam", riderBusiness.computeBaseBenefitHcpFam() );
            params.put( "baseBenefitHcpLad", riderBusiness.computeBaseBenefitHcpLadies() );
            params.put( "dsRiderHcpProviderParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderHcpProviderParticipantsList() ) );
            params.put( "baseBenefitHcpPro", riderBusiness.computeBaseBenefitHcpPro() );
            params.put( "participantEkaSehat", riderBusiness.checkParticapantEkaSehat() );
            params.put( "dsRiderEkaSehatParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderEkaSehatParticipantsList() ) );
            params.put( "dsRiderLadiesMedExpenseParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesMedExpenseParticipantsList()) );
            params.put( "dsRiderLadiesMedExpenseInnerLimitParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesMedExpenseInnerLimitParticipantsList()) );
            params.put("jenis", cepr01030102Form.getRightPartOfBusinessCd()+"");
            String mappingTitleWaiverTpdCi = MappingUtil.getLabel(cepr01030103Form.getWaiverTpdCiChooseList(), cepr01030103Form.getWaiverTpdCiChooseCd());
            params.put( "riderWaiverTpdCiTitle", mappingTitleWaiverTpdCi );

            String titleLadiesmedical;
            if( cepr01030103Form.getLadiesMedExpenseInnerLimitFlag() != null && cepr01030103Form.getLadiesMedExpenseInnerLimitFlag() .equals( CommonConst.CHECKED_TRUE ) )
            {
            	titleLadiesmedical = "SMiLe Ladies Medical Syariah (Benefit Inner Limit)";
            }else{
            	titleLadiesmedical = "SMiLe Ladies Medical Syariah (Benefit As Charge)";
            }
            
            String titleHcp;
            if( cepr01030103Form.getHcpFamilyFlag() != null && cepr01030103Form.getHcpFamilyFlag().equals( CommonConst.CHECKED_TRUE ) ){
            	titleHcp = "SMiLe Hospital Protection Family Syariah";
            }else if( cepr01030103Form.getHcpProviderFlag() != null && cepr01030103Form.getHcpProviderFlag().equals( CommonConst.CHECKED_TRUE )){
            	titleHcp = "SMiLe Hospital Protection Syariah (+) (Provider Benefit)";
            }
            else{
            	titleHcp = "SMiLe Hospital Protection Syariah";
            }
            
            String titleEkaSehat;
            if( cepr01030103Form.getEkaSehatInnerLimitFlag() != null && cepr01030103Form.getEkaSehatInnerLimitFlag().equals( CommonConst.CHECKED_TRUE ) ){
            	titleEkaSehat = "SMiLe Medical Syariah (Benefit Inner Limit)(Provider)";
            }else{
            	titleEkaSehat = "SMiLe Medical Syariah (Benefit As Charge)(Provider)";
            }
            String titleCi;
            if( cepr01030103Form.getPayorCiDeathFlag() != null && cepr01030103Form.getPayorCiDeathFlag().equals( CommonConst.CHECKED_TRUE ) 
            		|| cepr01030103Form.getPayorCiFlag() != null && cepr01030103Form.getPayorCiFlag().equals( CommonConst.CHECKED_TRUE )){
            	titleCi = "Payor 25 CI Death Rider";
            }else{
            	titleCi = "Payor 5/10 CI Rider";
            }
            
            String titleTpd;
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorSpouseTpdDeathFlag() ) ){
            	titleTpd = "Payor Spouse 60 TPD Syariah Rider";
            }else{
            	titleTpd = "Total Permanent Disability Syariah Rider";
            }
            String titleWaiverTpd;
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverTpdFlag() ) ){
            	titleWaiverTpd = "Waiver 60 TPD Rider";
            }else if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorTpdDeathFlag() ) ){
            	titleWaiverTpd = "Payor 25 TPD Death Rider";
            }else if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Tpd10TpdDeathFlag() ) ){
            	titleWaiverTpd = "Payor 5/10 TPD Rider";
            }else{
            	titleWaiverTpd = "Waiver 5/10 TPD Rider";
            }
            
            String titleWaiverCi;
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverCiFlag() ) ){
            	titleWaiverCi = "Waiver 60 CI Rider";
            }else{
            	titleWaiverCi = "Waiver 5/10 CI Rider";
            }
            String riderWaiverCiTitle;
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverCiFlag() ) ){
            	riderWaiverCiTitle = "Manfaat Waiver 60 CI - Rider";
            }else{
            	riderWaiverCiTitle = "Manfaat Waiver 5/10 CI - Rider";
            }
//            String titlePayorTpd;
//            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorTpdDeathFlag() ) ){
//            	titlePayorTpd = "Payor 25 TPD Death Rider";
//            }else{
//            	titlePayorTpd = "Payor 5/10 TPD Rider";
//            }
            
            String titleSum = "";
            if( cepr01030102Form.getLeftPartOfBusinessCd() == 191 ){
            	titleSum = "SMiLe CERDAS";
            }
            
            String paymentMode = "TAHUNAN";
            if(cepr01030102Form.getPaymentModeCd() == 1 ){  //triwulan
            	paymentMode = "TRIWULANAN";
        	}else if( cepr01030102Form.getPaymentModeCd() == 2){ //semesteran
        		paymentMode = "SEMESTERAN";
        	}else if( cepr01030102Form.getPaymentModeCd() == 6){ //bulanan
        		paymentMode = "BULANAN";
        	}else{
        		paymentMode = "TAHUNAN";
        	}
            
            params.put( "titleHcp", titleHcp );
            params.put( "titleCi", titleCi );
            params.put( "titleTpd", titleTpd);
            params.put( "titleWaiverTpd", titleWaiverTpd );
            params.put( "titleWaiverCi", titleWaiverCi );
            params.put( "titleLadiesmedical", titleLadiesmedical );
//            params.put( "titlePayorTpd", titlePayorTpd );
            params.put( "titleEkaSehat", titleEkaSehat );
            params.put( "riderWaiverCiTitle", riderWaiverCiTitle );
            params.put( "paymentMode", paymentMode );
           
            params.put( "dataSource", cepr01040209Business.getPageList( currentIdx ) );
            
            params.put( "dsEkalinkRider1", JasperReportsUtils.convertReportData( cepr01040209Business.getRiderPremiumList(cepr01030102Form, cepr01030101Form.getInsuredAge()) ) );
            params.put( "dsEkalinkRider2", JasperReportsUtils.convertReportData( cepr01040209Business.getRiderPremiumList(cepr01030102Form, cepr01030101Form.getInsuredAge()) ) );
           
            // Save E-Proposal PDF ke EBServer dan PopUp Download
            // ** Adrian@28/11/2013            
            String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
            String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
            Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();
          //  String fileName="testx.pdf";
            String fileName = msag_id+"_"+no_proposal+".pdf";
            params.put( "no_proposal", no_proposal );
            
            List temp = cepr01040209Business.getPageList( currentIdx );
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
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040209_product.jasper",
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
        if( source != null ){ 
        	currentIdx = currentIdx + 1;
        	if(currentIdx == 1){
        		params.put( "dsOneRow", JasperReportsUtils.convertReportData( getOneRowList() ) );
        	}
        	if(dsParamName.equals("dsRiderPa")){
        		params.put( "r1", dsParamName );
        	}else if(dsParamName.equals("dsRiderHcp")){
        		params.put( "r2", dsParamName );
        	}
        }

        params.put( dsParamName, source );
        params.put( idxParamName, currentIdx.toString() + "." );
    }
    
    public List<Map<String, Object>> getOneRowList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        params = new HashMap<String, Object>();
        params.put( "dummy", "row1" );
        result.add( params );
        return result;
    }
}
