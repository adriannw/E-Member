package id.co.sinarmaslife.eproposal.product.product01040208;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040208DownloadController
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


import id.co.sinarmaslife.eproposal.business.Cepr00000000RiderBusiness;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
import id.co.sinarmaslife.eproposal.product.product01040211.Cepr01040211Business;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030103Form;
import id.co.sinarmaslife.standard.StandardConst;
import id.co.sinarmaslife.standard.model.vo.AssurancePlanList;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.JrUtil;
import id.co.sinarmaslife.standard.util.MappingUtil;
import id.co.sinarmaslife.standard.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.lowagie.text.pdf.PdfWriter;

public class Cepr01040208DownloadController extends StandardParent implements Controller
{
    static Integer currentIdx;
    protected final Log logger = LogFactory.getLog( getClass() );

    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040208DownloadController.handleRequest" );

        ModelAndView result;
        HttpSession session = request.getSession();
//        AbstractJasperReportsView jasperViewer = new JasperReportsPdfView();
        Map<String, Object> params = new HashMap<String, Object>();
        CredentialsVO currentCredentialsVO = ( CredentialsVO ) session.getAttribute( StandardConst.CREDENTIALS_SES );
                       
        
        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01040208Business cepr01040208Business = new Cepr01040208Business( eproposalManager, editorUtil, command );
        
        
        HashMap<String, Object> map = new HashMap<String, Object>();
        if( request.getSession().getAttribute( "messageEkaSehatList" ) == null )
        {
        	List<String> messageEkaSehatList = cepr01040208Business.getEkaSehatMsgBox();
        	request.getSession().setAttribute( "messageEkaSehatList", messageEkaSehatList );
        	result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        
        if( request.getSession().getAttribute( "messageBoxList" ) == null )
        {
            List<String> messageBoxList = cepr01040208Business.getMedicalMsgBox();
            request.getSession().setAttribute( "messageBoxList", messageBoxList );
            result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        else
        {
        	 Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        	
        	map = controller(cepr01030000HoldingForm);
//         jasperViewer = (AbstractJasperReportsView) map.get("jasperViewer");
        	 String kelayakan = (StringUtil.isEmpty(map.get("proposalLayak")))?null:(String)map.get("proposalLayak");
         	if (kelayakan != null && kelayakan.equals("Maaf, Proposal yang Anda buat Tidak Layak Jual.")){
                     result = new ModelAndView( "CEPR00000000DownloadDocumentJSP2" );
  
         	}else{
        	
           params = (Map<String, Object>) map.get("params");
           String dirName = (StringUtil.isEmpty(map.get("dirName")))?null:(String)map.get("dirName");
           String fileName = (StringUtil.isEmpty(map.get("fileName")))?null:(String)map.get("fileName");
           JasperUtils.downloadReportPDF(response, dirName, fileName);   
//         jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
           request.getSession().setAttribute( "messageBoxList", map.get("messageBoxList") );
           request.getSession().setAttribute( "messageEkaSehatList", map.get("messageEkaSehatList") );
//         result = new ModelAndView( jasperViewer, params );
           result = null;   
         	}
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
        	}else if(dsParamName.equals("dsRiderWaiver10Tpd")){
        		params.put( "r5", dsParamName );
        	}else if(dsParamName.equals("dsRiderWaiverCi")){
        		params.put( "r6", dsParamName );
        	}else if(dsParamName.equals("dsRiderWaiver10Ci")){
        		params.put( "r7", dsParamName );
        	}else if(dsParamName.equals("dsRiderCi")){
        		params.put( "r8", dsParamName );
        	}else if(dsParamName.equals("dsRiderPayorCiDeath")){
        		params.put( "r9", dsParamName );
        	}else if(dsParamName.equals("dsRiderHcpFamily")){
        		params.put( "r11", dsParamName );
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
    
    /**
     * @param cepr01030000HoldingForm
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public HashMap<String, Object> controller(  Cepr01030000HoldingForm cepr01030000HoldingForm )
            throws ServletException, IOException
    {
    	  logger.info( "*-*-*-* Cepr01040211DownloadController.handleRequest" ); 
          HashMap<String, Object> result = new HashMap<String, Object>();
          Cepr01040208Business cepr01040208Business = new Cepr01040208Business(eproposalManager, editorUtil, cepr01030000HoldingForm);
          
    	 Cepr01030103Form cepr01030103Form = cepr01030000HoldingForm.getCepr01030103Form();
         Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
         Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
         String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
       
        // AbstractJasperReportsView jasperViewer = new JasperReportsPdfView();
         Map<String, Object> params = new HashMap<String, Object>();
         params.put( "format", "pdf" );

         Map<JRExporterParameter, Object> exporterParam = new HashMap<JRExporterParameter, Object>();
         exporterParam = JasperUtils.exporterParam(exporterParam);

     //    jasperViewer.setExporterParameters( exporterParam );
     //    jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040208_product.jasper" );

         // Dynamic title
         String assurancePlanCd = cepr01030102Form.getAssurancePlanCd();
         List<AssurancePlanList> assurancePlanList = cepr01030102Form.getAssurancePlanList();
         String title = "PLATINUM LINK"; // Default title 
         String titlePackage = "";
         for(AssurancePlanList plan : assurancePlanList){
         	if(plan.getValue().equals(assurancePlanCd)){
         		title = plan.getLabel();
         	}
         }
         if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10){
   	   		title = "SIMAS PRIME LINK";
   	   		titlePackage = "(RIDER SAVE)";
         } else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13){  
      		title = "SMART PLATINUM LINK";
         }
                  
         params.put( "title", title );
         params.put( "titlePackage", titlePackage );
         params.put( "currencySymbol", currencySymbol );
         // disclaimer =========================================================================================================
    //     params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
         params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
         params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
         params.put( "agentName", cepr01030101Form.getProposalUser() );
         //=====================================================================================================================         
         params.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
      //   jasperViewer.setReportDataKey( "dataSource" );
     //    jasperViewer.setJdbcDataSource( eproposalManager.getDataSource() );

         params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
         IllustrationResultVO illustrationResultVO = cepr01040208Business.getIllustrationResult();
         params.put( "validityMsg", illustrationResultVO.getValidityMsg() );
         params.put( "dsEkalinkIllustration", JasperReportsUtils.convertReportData( illustrationResultVO.getIllustrationList() ) );
         
         if( cepr01030102Form.getRightPartOfBusinessCd() >= Cepr01040208Mapper.X12 ||  
        	(cepr01030102Form.getRightPartOfBusinessCd() >= Cepr01040208Mapper.X5 && cepr01030102Form.getRightPartOfBusinessCd() <= Cepr01040208Mapper.X8) ){
	         // Estimasi Nilai Polis dikurangi biaya batal (Akhir Tahun) / FAKTOR BYi PENARIKAN dari DANA INVESTASI (SK)
	         IllustrationResultVO illustrationResultVO2 = cepr01040208Business.getIllustrationResult2();
	         params.put( "dsEkalinkIllustration2", JasperReportsUtils.convertReportData( illustrationResultVO2.getIllustrationList() ) );
	         
	         if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
	        	 params.put( "titleIllustration", "ILUSTRASI PERKEMBANGAN DANA APABILA PREMI DIBAYARKAN SEUMUR HIDUP ("+String.valueOf(100-cepr01030101Form.getInsuredAge())+" Tahun)");
	        	 IllustrationResultVO illustrationResultVO3 = cepr01040208Business.getIllustrationResult3();
		         params.put( "dsEkalinkIllustration3", JasperReportsUtils.convertReportData( illustrationResultVO3.getIllustrationList() ) );
		         IllustrationResultVO illustrationResultVO4 = cepr01040208Business.getIllustrationResult3();
		         params.put( "dsEkalinkIllustration4", JasperReportsUtils.convertReportData( illustrationResultVO4.getIllustrationList() ) );
	         }
         }
         
         int hcpRider = 0;
         int ekaSehatRider = 0;
         if( cepr01030103Form.getHcpFlag() != null && cepr01030103Form.getHcpFlag().equals( CommonConst.CHECKED_TRUE ) || 
         		cepr01030103Form.getHcpFamilyFlag() != null && cepr01030103Form.getHcpFamilyFlag().equals( CommonConst.CHECKED_TRUE ) || 
         				cepr01030103Form.getHcpProviderFlag() != null && cepr01030103Form.getHcpProviderFlag().equals( CommonConst.CHECKED_TRUE ) ){
         	hcpRider += 1;
         }
         
         if( cepr01030103Form.getEkaSehatFlag() != null && cepr01030103Form.getEkaSehatFlag().equals( CommonConst.CHECKED_TRUE ) || 
         		cepr01030103Form.getEkaSehatInnerLimitFlag() != null && cepr01030103Form.getEkaSehatInnerLimitFlag().equals( CommonConst.CHECKED_TRUE ) ){
         	ekaSehatRider += 1;
         }

         if( hcpRider > 0 && ekaSehatRider > 0 ){
         	   params.put( "combineHcpEkaSehat", "yes" );
         }
         if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X12
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8  ){
	            params.put( "totalInvestmentAllocation", cepr01040208Business.getInvestmentYield().getTotalInvestmentAllocation() );
	            params.put( "totalAssumptionLow", cepr01040208Business.getInvestmentYield().getTotalAssumptionLow() );
	            params.put( "totalAssumptionMid", cepr01040208Business.getInvestmentYield().getTotalAssumptionMid() );
	            params.put( "totalAssumptionHi", cepr01040208Business.getInvestmentYield().getTotalAssumptionHi() );
         }else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
        	  params.put( "totalInvestmentAllocation", cepr01040208Business.of_get_rate_ds().getTotalInvestmentAllocation() );
	            params.put( "totalAssumptionLow", cepr01040208Business.of_get_rate_ds().getTotalAssumptionLow() );
	            params.put( "totalAssumptionMid", cepr01040208Business.of_get_rate_ds().getTotalAssumptionMid() );
	            params.put( "totalAssumptionHi", cepr01040208Business.of_get_rate_ds().getTotalAssumptionHi() );
         }         
         else{
	            params.put( "totalInvestmentAllocation", cepr01040208Business.of_get_rate_ds_ProAsset().getTotalInvestmentAllocation());
	            params.put( "totalAssumptionLow", cepr01040208Business.of_get_rate_ds_ProAsset().getTotalAssumptionLow() );
	            params.put( "totalAssumptionMid", cepr01040208Business.of_get_rate_ds_ProAsset().getTotalAssumptionMid() );
	            params.put( "totalAssumptionHi", cepr01040208Business.of_get_rate_ds_ProAsset().getTotalAssumptionHi() );            	
         }
         
         params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( cepr01040208Business.getCommonHeaderRowList() ) );
         params.put( "dsCommonHeaderSmall", JasperReportsUtils.convertReportData( cepr01040208Business.getCommonHeaderSmallRowList() ) );
         params.put( "dsCommonHeaderSmall2", JasperReportsUtils.convertReportData( cepr01040208Business.getCommonHeaderSmallRowList() ) );
         params.put( "dsCommonHeaderSmall3", JasperReportsUtils.convertReportData( cepr01040208Business.getCommonHeaderSmallRowList3() ) );
         params.put( "dsCommonHeaderSmall4", JasperReportsUtils.convertReportData( cepr01040208Business.getCommonHeaderSmallRowList3() ) );
         if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10 || cepr01030102Form.getRightPartOfBusinessCd() >= Cepr01040208Mapper.X12
        	 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8 ) {
	            params.put( "dsInvestmentChoice", JasperReportsUtils.convertReportData( cepr01040208Business.getInvestmentChoiceRowList() ) );
	            params.put( "dsInvestmentChoiceSmall", JasperReportsUtils.convertReportData( cepr01040208Business.getInvestmentChoiceSmallRowList() ) );
	            params.put( "dsInvestmentChoiceSmall2", JasperReportsUtils.convertReportData( cepr01040208Business.getInvestmentChoiceSmallRowList() ) );
	            params.put( "dsInvestmentChoiceSmall4", JasperReportsUtils.convertReportData( cepr01040208Business.getInvestmentChoiceSmallRowList() ) );
         }else{
	            params.put( "dsInvestmentChoice", JasperReportsUtils.convertReportData( cepr01040208Business.getInvestmentChoiceRowList_ProAsset() ) );
	            params.put( "dsInvestmentChoiceSmall", JasperReportsUtils.convertReportData( cepr01040208Business.getInvestmentChoiceSmallRowList_ProAsset() ) );
	            params.put( "dsInvestmentChoiceSmall2", JasperReportsUtils.convertReportData( cepr01040208Business.getInvestmentChoiceSmallRowList_ProAsset() ) );
         }
        
         params.put( "dsMonthlyPremium", JasperReportsUtils.convertReportData( cepr01040208Business.getMonthlyPremiumList( cepr01030102Form ) ) );
         params.put( "dsInvestmentPerformance", JasperReportsUtils.convertReportData( cepr01040208Business.getInvestmentPerformanceList() ) ); 
         if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10 || cepr01030102Form.getRightPartOfBusinessCd() >= Cepr01040208Mapper.X12
        	 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8 ) {
         	params.put( "dsInvestmentYield", JasperReportsUtils.convertReportData( cepr01040208Business.of_get_rate_ds().getYieldList() ) );
         	params.put( "dsInvestmentYield2", JasperReportsUtils.convertReportData( cepr01040208Business.of_get_rate_ds().getYieldList() ) );
         	params.put( "dsInvestmentYield3", JasperReportsUtils.convertReportData( cepr01040208Business.of_get_rate_ds().getYieldList() ) );
         	params.put( "dsInvestmentYield4", JasperReportsUtils.convertReportData( cepr01040208Business.of_get_rate_ds().getYieldList() ) );
         }else{
         	params.put( "dsInvestmentYield", JasperReportsUtils.convertReportData( cepr01040208Business.of_get_rate_ds_ProAsset().getYieldList() ) );
         	params.put( "dsInvestmentYield2", JasperReportsUtils.convertReportData( cepr01040208Business.of_get_rate_ds_ProAsset().getYieldList() ) );
         }
         
         params.put( "dsOneRow", JasperReportsUtils.convertReportData( cepr01040208Business.getOneRowList() ) );
         params.put( "dsOneRow2", JasperReportsUtils.convertReportData( cepr01040208Business.getOneRowList() ) );
         params.put( "dsOneRow3", JasperReportsUtils.convertReportData( cepr01040208Business.getOneRowList() ) );
         params.put( "dsOneRow4", JasperReportsUtils.convertReportData( cepr01040208Business.getOneRowList() ) );
         params.put( "dsOneRow5", JasperReportsUtils.convertReportData( cepr01040208Business.getOneRowList() ) );
         params.put( "dsNoteSekaligus", JasperReportsUtils.convertReportData( cepr01040208Business.getNoteSekaligusRowList() ) );
         params.put( "dsNoteBerkala", JasperReportsUtils.convertReportData( cepr01040208Business.getNoteBerkalaRowList() ) );
         
         double up = new Double(cepr01030102Form.getTotalSumInsured().toString());
         params.put( "uangPertanggunganPa", currencySymbol + " " + editorUtil.convertToTwoDigit(Math.min(up, 2000000000.0)) );
         currentIdx = 0;
         Cepr00000000RiderBusiness riderBusiness = new Cepr00000000RiderBusiness( eproposalManager, editorUtil, cepr01030000HoldingForm );
         putRiderParam( params, riderBusiness.getRiderPaList(), "dsRiderPa", "riderPaIndex", null );
         params.put( "riderPaRisk", riderBusiness.getRiderPaRisk() );
         params.put( "dsRiderPaClone", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderPaList() ) );
         params.put( "riderLadiesInsTitle", MappingUtil.getLabel(cepr01030103Form.getLadiesInsList(), cepr01030103Form.getLadiesInsCd()));
         putRiderParam( params, riderBusiness.getRiderHcpList(), "dsRiderHcp", "riderHcpIndex", null );
         putRiderParam( params, riderBusiness.getRiderHcpFamilyList(), "dsRiderHcpFamily", "riderHcpFamilyIndex", null );
         putRiderParam( params, riderBusiness.getRiderHcpLadiesList(), "dsRiderHcpLadies", "riderHcpLadiesIndex", null );
         putRiderParam( params, riderBusiness.getRiderPayorTpdCiDeathList(), "dsRiderPayorTpdCiDeath", "riderPayorTpdCiDeathIndex", null );
         putRiderParam( params, riderBusiness.getRiderPayor5Tpd10CiDeathList(), "dsRiderPayor5Tpd10CiDeath", "riderPayor5Tpd10CiDeathIndex", null );
         putRiderParam( params, riderBusiness.getRiderWaiver5Tpd10CiList(), "dsRiderWaiver5Tpd10Ci", "riderWaiver5Tpd10CiIndex", null );
         putRiderParam( params, riderBusiness.getRiderTpdList(), "dsRiderTpd", "riderTpdIndex", null );
         putRiderParam( params, riderBusiness.getRiderTermRiderList(), "dsRiderTermRider", "riderTermRiderIndex", null );
         putRiderParam( params, riderBusiness.getRiderLadiesInsList(), "dsRiderLadiesIns", "riderLadiesInsIndex", null );
         putRiderParam( params, riderBusiness.getRiderWaiverTpdCiList(), "dsRiderWaiverTpdCi", "riderWaiverTpdCiIndex", null );
         putRiderParam( params, riderBusiness.getRiderEkaSehatListMap(), "dsRiderEkaSehat", "riderEkaSehatIndex", null );
         putRiderParam( params, riderBusiness.getRiderHcpProviderList(), "dsRiderHcpProvider", "riderHcpProviderIndex", null );
         putRiderParam( params, riderBusiness.getRiderCiList(), "dsRiderCi", "riderCiIndex", null );
         putRiderParam( params, riderBusiness.getRiderEkaSehatInnerLimitListMap(), "dsRiderEkaSehatInnerLimit", "riderEkaSehatInnerLimitIndex", null );
         putRiderParam( params, riderBusiness.getRiderLadiesMedExpenseListMap(), "dsRiderLadiesMedExpense", "riderLadiesMedExpenseIndex", null );
         putRiderParam( params, riderBusiness.getRiderLadiesMedExpenseInnerLimitListMap(), "dsRiderLadiesMedExpenseInnerLimit", "riderLadiesMedExpenseInnerLimitIndex", null );
         
         
         putRiderParam( params, riderBusiness.getRiderPayorTpdDeathList(), "dsRiderPayorTpdDeath", "riderPayorTpdDeathIndex", null );
         putRiderParam( params, riderBusiness.getRiderPayorCiDeathList(), "dsRiderPayorCiDeath", "riderPayorCiDeathIndex", null );
         putRiderParam( params, riderBusiness.getRiderPayorCiList(), "dsRiderPayorCi", "riderPayorCiIndex", null );
         putRiderParam( params, riderBusiness.getRiderPayorSpouseTpdDeathList(), "dsRiderPayorSpouseTpdDeath", "riderPayorSpouseTpdDeathIndex", null );
         putRiderParam( params, riderBusiness.getRiderWaiverTpdList(), "dsRiderWaiverTpd", "riderWaiverTpdIndex", null );
         putRiderParam( params, riderBusiness.getRiderWaiverCiList(), "dsRiderWaiverCi", "riderWaiverCiIndex", null );
         putRiderParam( params, riderBusiness.getRiderScholarRiderList(), "dsRiderScholarRider", "riderScholarRiderIndex", null); 
         putRiderParam( params, riderBusiness.getRiderEarlyStageCi99List(), "dsRiderEarlyStageCi99", "riderEarlyStageCi99Index", null );
         putRiderParam( params, riderBusiness.getRiderMedicalPlusList(), "dsRiderMedicalPlus", "riderMedicalPlusIndex", null );
         putRiderParam( params, riderBusiness.getRiderMedicalPlusRjList(), "dsRiderMedicalPlusRj", "riderMedicalPlusRjIndex", null );
         putRiderParam( params, riderBusiness.getRiderMedicalPlusRgList(), "dsRiderMedicalPlusRg", "riderMedicalPlusRgIndex", null );
         putRiderParam( params, riderBusiness.getRiderMedicalPlusRbList(), "dsRiderMedicalPlusRb", "riderMedicalPlusRbIndex", null );
         putRiderParam( params, riderBusiness.getRiderMedicalPlusPkList(), "dsRiderMedicalPlusPk", "riderMedicalPlusPkIndex", null );
         putRiderParam( params, riderBusiness.getRiderEkaSehatExtraListMap(), "dsRiderEkaSehatExtra", "riderEkaSehatExtraIndex", null);
         
         /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
         String usiaMaxTerm = MappingUtil.getLabel(cepr01030103Form.getTerm5575RiderChooseList(), cepr01030103Form.getTerm5575RiderChooseCd());
         params.put( "usiaMaxTerm", usiaMaxTerm+" " );
         putRiderParam( params, riderBusiness.getRiderTerm5575RiderList(), "dsRiderTerm5575Rider", "riderTerm5575RiderIndex", null ); 
         
         params.put( "dsRiderLadiesInsSummary", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesInsSummaryList() ) );
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
         String titleHcp = "HCP/ Eka Sehat Rider";
         params.put( "dataSource", cepr01040208Business.getPageList( currentIdx ) );
         params.put( "titleHcp", titleHcp );
         params.put( "dsChartUpSekaligus", JasperReportsUtils.convertReportData( riderBusiness.getChartScholarUpSekaligusTahunanList() ) );
         params.put( "dsEkalinkRider", JasperReportsUtils.convertReportData( cepr01040208Business.getRiderPremiumList(cepr01030102Form, cepr01030101Form.getInsuredAge()) ) );
         params.put( "dsEkalinkRider2", JasperReportsUtils.convertReportData( cepr01040208Business.getRiderPremiumList(cepr01030102Form, cepr01030101Form.getInsuredAge()) ) );
        
         Integer titlePayor5Tpd10CiDeath = cepr01030102Form.getPremiumFurloughYear();
         params.put( "titlePayor5Tpd10CiDeath", titlePayor5Tpd10CiDeath.toString() );  
         Integer riderWaiver5Tpd10CiTitle = cepr01030102Form.getPremiumFurloughYear();
         params.put( "riderWaiver5Tpd10CiTitle", riderWaiver5Tpd10CiTitle.toString() );
         
         params.put( "dsCommonHeaderRincian", JasperReportsUtils.convertReportData( cepr01040208Business.getCommonHeaderRincianRowList()) );
         
         double ldec_coi;
         if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11|| cepr01030102Form.getRightPartOfBusinessCd() >= Cepr01040208Mapper.X12){
        	 ldec_coi = cepr01040208Business.of_get_coi_basic_190(1);
     	}else{
     		 ldec_coi = cepr01040208Business.of_get_coi_basic(1);
     	}           
         BigDecimal biayaAsuransiPokok =  new BigDecimal(ldec_coi);
         params.put( "dsCommonBiayaRincian", JasperReportsUtils.convertReportData( cepr01040208Business.getCommonBiayaRincianRowList( biayaAsuransiPokok ) ) );
         
         params.put("pageRider", cepr01040208Business.getPageRider( currentIdx ));
         params.put( "dsCommonHeaderRincianRiderPa", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(1) ) );
         params.put( "dsCommonBiayaRincianRiderPa", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonBiayaRincianRider(1) ) );
         params.put( "dsCommonHeaderRincianRiderTpd", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(3) ) );
         params.put( "dsCommonBiayaRincianRiderTpd", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonBiayaRincianRider(3) ) ); 
         params.put( "dsCommonHeaderRincianRiderCi", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(4) ) );
         params.put( "dsCommonBiayaRincianRiderCi", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonBiayaRincianRider(4) ) ); 
         params.put( "dsCommonHeaderRincianRiderHCPFamily", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(11) ) );
         params.put( "dsCommonBiayaRincianRiderHCPFamily", JasperReportsUtils.convertReportData( cepr01040208Business.getCommonBiayaRincianRider(11) ) );
         params.put( "dsRincianRiderHCPFamily", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpFamilyList() ));
         params.put( "dsRincianRiderHcpFamilyParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderHcpFamilyParticipantsList() ) );
         
         params.put( "dsCommonHeaderRincianRiderHCP", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(2) ) );
         params.put( "dsCommonBiayaRincianRiderHCP", JasperReportsUtils.convertReportData( cepr01040208Business.getCommonBiayaRincianRider(2) ) );
         params.put( "dsRincianRiderHCP", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpList() ));    
         
         params.put( "dsCommonHeaderRincianRiderHCPProvider", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(16) ) );
         params.put( "dsCommonBiayaRincianRiderHCPProvider", JasperReportsUtils.convertReportData( cepr01040208Business.getCommonBiayaRincianRider(16) ) );
         params.put( "dsRincianRiderHCPProvider", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpProviderList()));    
         
         params.put( "dsCommonHeaderRincianRiderTerm", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(12) ) );
         params.put( "dsCommonBiayaRincianRiderTerm", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonBiayaRincianRider(12) ) ); 
         params.put( "dsCommonHeaderRincianRiderEkaSehat", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(13) ) );
         params.put( "dsCommonBiayaRincianRiderEkaSehat", JasperReportsUtils.convertReportData( cepr01040208Business.getCommonBiayaRincianRider(13) ) );     
         params.put( "dsRincianRiderEkaSehat", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRincianRiderEkaSehat() ));
         params.put( "dsRincianRiderEkaSehatParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRincianRiderEkaSehatParticipantsList() ) ); 
         params.put( "dsCommonHeaderRincianRiderEkaSehatInnerLimit", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(15) ) );
         params.put( "dsCommonBiayaRincianRiderEkaSehatInnerLimit", JasperReportsUtils.convertReportData( cepr01040208Business.getCommonBiayaRincianRider(15) ) );     
         params.put( "dsRincianRiderEkaSehatInnerLimit", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRincianRiderEkaSehatInnerLimit() ));
         params.put( "dsRincianRiderEkaSehatInnerLimitParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRincianRiderEkaSehatInnerLimitParticipantsList() ) );
         params.put( "dsCommonHeaderRincianRiderWaiverTpdCi", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(17) ) );
         params.put( "dsCommonBiayaRincianRiderWaiverTpdCi", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonBiayaRincianRider(17) ) );
         params.put( "dsCommonHeaderRincianRiderPayorTpdCiDeath", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(18) ) );
         params.put( "dsCommonBiayaRincianRiderPayorTpdCiDeath", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonBiayaRincianRider(18) ) );
         
         params.put( "dsCommonHeaderRincianRiderLadiesIns", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(19) ) );
         params.put( "dsCommonBiayaRincianRiderLadiesIns", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonBiayaRincianRider(19) ) ); 
         params.put( "dsCommonHeaderRincianRiderHCPLadies", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(20) ) );
         params.put( "dsCommonBiayaRincianRiderHCPLadies", JasperReportsUtils.convertReportData( cepr01040208Business.getCommonBiayaRincianRider(20) ) );
         params.put( "dsRincianRiderHcpLadies", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpLadiesList()));
         params.put( "dsCommonHeaderRincianRiderLadiesMedExpense", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(21) ) );
         params.put( "dsCommonBiayaRincianRiderLadiesMedExpense", JasperReportsUtils.convertReportData( cepr01040208Business.getCommonBiayaRincianRider(21) ) );     
         params.put( "dsRincianRiderLadiesMedExpense", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRincianRiderLadiesMedExpenseSyariah() ));
         params.put( "dsRincianRiderLadiesMedExpenseParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesMedExpenseParticipantsList() ) );            
         params.put( "dsCommonHeaderRincianRiderLadiesMedExpenseInnerLimit", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(22) ) );
         params.put( "dsCommonBiayaRincianRiderLadiesMedExpenseInnerLimit", JasperReportsUtils.convertReportData( cepr01040208Business.getCommonBiayaRincianRider( 22) ) );     
         params.put( "dsRincianRiderLadiesMedExpenseInnerLimit", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRincianRiderLadiesMedExpenseInnerLimitSyariah() ));
         params.put( "dsRincianRiderLadiesMedExpenseInnerLimitParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesMedExpenseInnerLimitParticipantsList() ) );
                     
         params.put( "dsCommonHeaderRincianRiderPayor5Tpd10CiDeath", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(23) ) );
         params.put( "dsCommonBiayaRincianRiderPayor5Tpd10CiDeath", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonBiayaRincianRider(23) ) );
         params.put( "dsCommonHeaderRincianRiderWaiver5Tpd10Ci", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(24) ) );
         params.put( "dsCommonBiayaRincianRiderWaiver5Tpd10Ci", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonBiayaRincianRider(24) ) );
         params.put( "dsCommonHeaderRincianRiderScholar", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(25) ) );
         params.put( "dsCommonBiayaRincianRiderScholar", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonBiayaRincianRider(25) ) ); 
         params.put( "dsCommonHeaderRincianRiderEarlyStageCi99", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(27) ) );
         params.put( "dsCommonBiayaRincianRiderEarlyStageCi99", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonBiayaRincianRider(27) ) );            
         params.put( "dsCommonHeaderRincianRiderMedicalPlus", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(28) ) );
         params.put( "dsCommonBiayaRincianRiderMedicalPlus", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonBiayaRincianRider(28) ) );  
         params.put( "dsRincianRiderMedicalPlus", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderMedicalPlusList() ));
         params.put( "dsRincianRiderMedicalPlusRj", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderMedicalPlusRjList() ));
         params.put( "dsRincianRiderMedicalPlusRg", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderMedicalPlusRgList() ));
         params.put( "dsRincianRiderMedicalPlusRb", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderMedicalPlusRbList() ));
         params.put( "dsRincianRiderMedicalPlusPk", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderMedicalPlusPkList() ));                     
         params.put( "dsRincianRiderMedicalPlusParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRincianRiderMedicalPlusParticipantsList()) );         
         params.put( "dsCommonHeaderRincianRiderEkaSehatExtra", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(29) ) );
         params.put( "dsCommonBiayaRincianRiderEkaSehatExtra", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonBiayaRincianRider(29) ) );     
         params.put( "dsRincianRiderEkaSehatExtra", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRincianRiderEkaSehatExtra() ));
         params.put( "participantEkaSehatExtra", riderBusiness.checkParticapantEkaSehatExtra() );
         params.put( "dsRiderEkaSehatExtraParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderEkaSehatExtraParticipantsList() ) );
         params.put( "dsRincianRiderEkaSehatExtraParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRincianRiderEkaSehatExtraParticipantsList() ) ); 
         params.put( "tipeEkaSehat", cepr01040208Business.checkTipeEkaSehat(29) );
         
         /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
         params.put( "dsCommonHeaderRincianRiderTerm5575", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonHeaderRincianSmallRowList(30) ) );
         params.put( "dsCommonBiayaRincianRiderTerm5575", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040208Business.getCommonBiayaRincianRider(30) ) ); 
         
         // Save E-Proposal PDF ke EBServer dan PopUp Download
         // ** Adrian@28/11/2013            
         String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
         String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
         Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();
       //  String fileName="testx.pdf";
         String fileName = msag_id+"_"+no_proposal+".pdf";
         params.put( "no_proposal",  no_proposal );
         
         List temp = cepr01040208Business.getPageList( currentIdx );
         String rootDir_EProp="";
         if(flag_test==1){
        	 rootDir_EProp = CommonConst.ROOTDIR_EPROP_TEST;
         }else{
        	 rootDir_EProp = CommonConst.ROOTDIR_EPROP;
         }
         String dirName = rootDir_EProp+"\\"+msag_id+"\\"+no_proposal; 
         
         if(illustrationResultVO.getValidityMsg().equals("Maaf, Proposal yang Anda buat Tidak Layak Jual.")){
         	 result.put("proposalLayak",illustrationResultVO.getValidityMsg());
         }else{
         
         File sourceFile = new File( dirName + "\\" + fileName );
        try{      
	        	   //if(cekDataApprovedOrNot.get(0).getPosisi()<3){
			            JasperUtils.exportReportToPdf(
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040208_product.jasper",
			            		dirName,
			                fileName,
							params,
							temp,
							PdfWriter.AllowPrinting,
			                null,
			                null
			            );
//			            JasperUtils.downloadReportPDF(response, dirName, fileName);        	   
		    	   //}
	    	   }catch( Exception e )
	        	{
	    		   logger.error("ERROR", e);	
	        	} 
                     
 //        jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
        result.put("dirName", dirName);
        result.put("fileName", fileName);
        result.put("messageBoxList", null);
        result.put("messageEkaSehatList", null);
     //    result.put("jasperViewer", jasperViewer);
        result.put("params", params);
//         result = new ModelAndView( jasperViewer, params );
         }                 
        return result;    
    }
}