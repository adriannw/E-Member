package id.co.sinarmaslife.eproposal.product.product01040131;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040131DownloadController
 * Description         	: Dana sejahtera (163)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 25, 2007 9:38:52 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import com.lowagie.text.pdf.PdfWriter;

import id.co.sinarmaslife.eproposal.business.Cepr00000000RiderBusiness;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.datasource.ManfaatDataSource;
import id.co.sinarmaslife.eproposal.common.datasource.PremiumSummaryDataSource;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000MoneyUtil;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.model.vo.StdTableDetailVO;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class Cepr01040131DownloadController extends StandardParent implements Controller
{
	protected final static Log logger = LogFactory.getLog( Cepr01040131DownloadController.class );
	static Integer currentIdx;
	
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040131DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        logger.info( "*-*-*-* editorUtil = " + editorUtil );
        Cepr01040131Business cepr01040131Business = new Cepr01040131Business( eproposalManager, editorUtil, command );

        ModelAndView result;
        if( request.getSession().getAttribute( "messageEkaSehatList" ) == null )
        {
        	List<String> messageEkaSehatList = cepr01040131Business.getEkaSehatMsgBox();
        	request.getSession().setAttribute( "messageEkaSehatList", messageEkaSehatList );
        	result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        
        if( request.getSession().getAttribute( "messageBoxList" ) == null )
        {
            List<String> messageBoxList = cepr01040131Business.getMedicalMsgBox();
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
            jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040131_product.jasper" );

            String businessIdx = "";
            if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040131Mapper.X1 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040131Mapper.X5){
            	businessIdx = "5";
            }else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040131Mapper.X2 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040131Mapper.X6){
            	businessIdx = "10";
            }else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040131Mapper.X3 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040131Mapper.X7){
            	businessIdx = "15";
            }else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040131Mapper.X4 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040131Mapper.X8){
            	businessIdx = "20";
            }else{
            	businessIdx = "20";
            }

            List<BigDecimal> accumulatedPremiumList = new ArrayList<BigDecimal>();
            BigDecimal accumulatedPremium = new BigDecimal( "0" );
            BigDecimal premium = cepr01030102Form.getPremium();
            for( int i = 0; i < cepr01030102Form.getTermOfPayment(); i++ )
            {
                accumulatedPremium = accumulatedPremium.add( premium );
                accumulatedPremiumList.add( accumulatedPremium );
            }

            List<Integer> yearNoList = new ArrayList<Integer>();
            List<Integer> ageList = new ArrayList<Integer>();
            int age = cepr01030101Form.getInsuredAge();

            List<Double> cashValueList = new ArrayList<Double>();
            double idec_up = LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() );
            double ii_permil = 1000;
            double ldec_rate;
            double ldec_tunai;
            Map<String, Object> cashParams = new HashMap<String, Object>();
            for( int i = 1; i <= 20; i++ )
            {
                age++;
                ageList.add( age );
                yearNoList.add( i );

                // get cash value(nilai tunai)
                cashParams.put( "leftPartOfBusinessCd", new Integer( "163" ) );
                cashParams.put( "lstabLamaTanggung", new Integer( "79" ) );
                cashParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_TUNAI );
                cashParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
                cashParams.put( "paymentModeCd", cepr01030102Form.getPaymentModeCd() );
                cashParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
                cashParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
                cashParams.put( "yearNo", i );
                logger.info( "*-*-*-* cashParams = " + cashParams );
                ldec_rate = LazyConverter.toDouble( eproposalManager.selectCashDanaSejahtera( cashParams ) );
                ldec_tunai = ldec_rate * idec_up / ii_permil;
                cashValueList.add( ldec_tunai );
            }

            // Fill all values to detail row
            jasperViewer.setReportDataKey( "dataSource" );
            List<StdTableDetailVO> detailVOList = new ArrayList<StdTableDetailVO>();
            StdTableDetailVO detailVO;

            String yearStr;
            String ageStr;
            String accumulatedStr;
            String cashValueStr;



            for( int i = 0; i < 20; i++ )
            {
                if( i < yearNoList.size()  ) yearStr = editorUtil.convertToString( yearNoList.get( i ) ); else yearStr = "";
                if( i < ageList.size() ) ageStr = editorUtil.convertToString( ageList.get( i ) ); else ageStr = "";
                if( i < accumulatedPremiumList.size() ) accumulatedStr = editorUtil.convertToRoundedTwoDigits( accumulatedPremiumList.get( i ) ); else accumulatedStr = "";
                cashValueStr = editorUtil.convertToRoundedTwoDigits( cashValueList.get( i ) );

                detailVO = new StdTableDetailVO( yearStr, ageStr, accumulatedStr, cashValueStr, "" );
                detailVOList.add( detailVO ) ;
            }

            Integer lastAge = 79;

            String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
            BigDecimal totalSumInsured = cepr01030102Form.getTotalSumInsured();
            params.put( "paymentModeCd", cepr01030102Form.getPaymentModeCd() );
            params.put( "totalSumInsured", editorUtil.convertToString( totalSumInsured ) );
            params.put( "premium", currencySymbol + editorUtil.convertToString( premium ) );
            params.put( "ssNumber", LazyConverter.toString( cepr01030102Form.getRightPartOfBusinessCd() ) );
            params.put( "dailyCompensation1", editorUtil.convertToString( totalSumInsured ) );
            params.put( "dailyCompensation2", editorUtil.convertToString( totalSumInsured.multiply( new BigDecimal( 2.0 ) ) ) );
            params.put( "dailyCompensation3", editorUtil.convertToString( totalSumInsured.multiply( new BigDecimal( 2.0 ) ) ) );
            params.put( "compensation41", editorUtil.convertToString( totalSumInsured.multiply( new BigDecimal( 2.5 ) ) ) );
            params.put( "compensation42", editorUtil.convertToString( totalSumInsured.multiply( new BigDecimal( 5.0 ) ) ) );
            params.put( "compensation43", editorUtil.convertToString( totalSumInsured.multiply( new BigDecimal( 7.5 ) ) ) );
            params.put( "compensation44", editorUtil.convertToString( totalSumInsured.multiply( new BigDecimal( 10.0 ) ) ) );
            params.put( "compensation5", editorUtil.convertToString( totalSumInsured.multiply( new BigDecimal( 50.0 ) ) ) );
           // params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            params.put( "currencySymbol", currencySymbol );
            params.put( "lastAge", editorUtil.convertToString( lastAge ) );
            params.put( "lastYearNo", editorUtil.convertToString( lastAge - cepr01030101Form.getInsuredAge() ) );
            params.put( "note", "Persyaratan underwriting sesuai dengan persyaratan pemeriksaan kesehatan asuransi jiwa individu." );
            params.put( "paymentMode", LazyConverter.toString( cepr01030102Form.getPaymentModeCd() ) );
            // Add Additional Assurance
            List<Map<String, Object>> manfaatTermRiderList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> manfaatPersonalAccidentList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> manfaatPenyakitKritisList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> manfaatPayorsClauseList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> manfaatWaiverPremiumDisabilityList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> manfaaEkaSehatList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> premiumSummaryList;

            boolean additionalAssuranceExist = false;
            BigDecimal manfaatTermRiderAmount = new BigDecimal( "0" );
            BigDecimal manfaatPersonalAccidentAmount = new BigDecimal( "0" );
            BigDecimal manfaatPenyakitKritisAmount = new BigDecimal( "0" );
            BigDecimal manfaatPayorsClauseAmount = new BigDecimal( "0" );
            BigDecimal manfaatWaiverPremiumAmount = new BigDecimal( "0" );
            BigDecimal manfaatEkaSehatmount = new BigDecimal( "0" );

            // this is get from w_print_prop.of_dtl_premi
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getPersonalAccidentFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getPersonalAccidentFlagDisplay() ) )
            {
                additionalAssuranceExist = true;
                logger.info( "*-*-*-* Personal Accident Checked" );
                manfaatPersonalAccidentAmount = cepr01040131Business.computeManfaat( cepr01030102Form.getPaymentModeCd(), Hardcode.MANFAAT_PERSONAL_ACCIDENT );
                logger.info( "*-*-*-* manfaatPersonalAccidentAmount = " + manfaatPersonalAccidentAmount );
                manfaatPersonalAccidentList.add( getManfaatPersonalAccidentParamsMap( command, editorUtil ) );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getCriticalIllnessFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getCriticalIllnessFlagDisplay() ) )
            {
                additionalAssuranceExist = true;
                manfaatPenyakitKritisAmount = cepr01040131Business.computeManfaatPk( cepr01030102Form.getPaymentModeCd(), Hardcode.MANFAAT_PENYAKIT_KRITIS );
                manfaatPenyakitKritisList.add( ManfaatDataSource.getManfaatPenyakitKritisParamsMap( command, editorUtil ) );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getPayorsClauseFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getPayorsClauseFlagDisplay() ) )
            {
                additionalAssuranceExist = true;
                manfaatPayorsClauseAmount = cepr01040131Business.computeManfaat( cepr01030102Form.getPaymentModeCd(), Hardcode.MANFAAT_PAYORS_CLAUSE );
                manfaatPayorsClauseList.add( ManfaatDataSource.getManfaatPayorsClauseParamsMap() );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getWaiverPremiumDisabilityFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getWaiverPremiumDisabilityFlagDisplay() ) )
            {
                additionalAssuranceExist = true;
                Double theRate = cepr01040131Business.of_premi_rider(Hardcode.AI_JENIS_WP_60_TPD, 1);
                Double thePremium = LazyConverter.toDouble( premium );
                Double manfaat = theRate * thePremium / 1000;
                manfaatWaiverPremiumAmount = new BigDecimal( manfaat );
                manfaatWaiverPremiumDisabilityList.add( ManfaatDataSource.getManfaatWaiverPremiumDisabilityParamsMap() );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getEkaSehatFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getEkaSehatFlagDisplay() ) )
            {
                additionalAssuranceExist = true;
                manfaatEkaSehatmount = new BigDecimal( cepr01040131Business.of_premi_rider(Hardcode.AI_JENIS_EKA_SEHAT, 1) );
                manfaaEkaSehatList.add( ManfaatDataSource.getManfaatEkaSehatParamsMap() );
            }

            // this will generate header for additional assurance
            List<Map<String, Object>> manfaatHeaderList = new ArrayList<Map<String, Object>>();
            if( additionalAssuranceExist )
            {
                Map<String, Object> manfaatHeaderMap = new HashMap<String, Object>();
                manfaatHeaderMap.put( "title", "Manfaat Program Asuransi Tambahan" );
                manfaatHeaderList.add( manfaatHeaderMap );
            }

            Map<String, String> labelMap = new HashMap<String, String>();
            labelMap.put( PremiumSummaryDataSource.MAP_KEY_PK_LABEL, "Premi PK" );

            // to show summary
            premiumSummaryList = PremiumSummaryDataSource.getPremiumSummaryParamsMapList( command, editorUtil, manfaatTermRiderAmount, manfaatPersonalAccidentAmount, manfaatPenyakitKritisAmount, manfaatPayorsClauseAmount, manfaatWaiverPremiumAmount, manfaatEkaSehatmount, BigDecimal.ZERO, labelMap );

            params.put( "totalPremium1", editorUtil.convertToRoundedTwoDigits( Cepr00000000MoneyUtil.sum( premiumSummaryList, "premium1" ) ) );
            params.put( "totalPremium2", editorUtil.convertToRoundedTwoDigits( Cepr00000000MoneyUtil.sum( premiumSummaryList, "premium2" ) ) );
            params.put( "totalPremium3", editorUtil.convertToRoundedTwoDigits( Cepr00000000MoneyUtil.sum( premiumSummaryList, "premium3" ) ) );

            params.put( "manfaat_meninggal", editorUtil.convertToRoundedTwoDigits( cepr01030102Form.getTotalSumInsured() ));
            params.put( "premi", editorUtil.convertToRoundedTwoDigits( cepr01030102Form.getPremium() ));
            params.put( "nilai_tunai", editorUtil.convertToRoundedTwoDigits( 0 ));
            params.put( "usia1", cepr01030101Form.getInsuredAge() + 1 + "");
            params.put( "usia2", cepr01030101Form.getInsuredAge() + 2 + "");
            params.put( "usia3", cepr01030101Form.getInsuredAge() + 3 + "");
            params.put( "usia4", cepr01030101Form.getInsuredAge() + 4 + "");
            params.put( "usia5", cepr01030101Form.getInsuredAge() + 5 + "");
            params.put( "providerType", "Non Provider" );
            
            params.put( "dataSource", new ArrayList() );
            if( Hardcode.PAY_MODE_CD_TAHUNAN == cepr01030102Form.getPaymentModeCd() )
            {
                params.put( "dsDetailTahunan", JasperReportsUtils.convertReportData( detailVOList ) );
            }
            else if( Hardcode.PAY_MODE_CD_SEKALIGUS == cepr01030102Form.getPaymentModeCd() )
            {
                params.put( "dsDetailSekaligus", JasperReportsUtils.convertReportData( detailVOList ) );
            }
            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
            params.put( "validityMsg", "" );

            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( cepr01040131Business.getHeaderRowList( command ) ) );
            params.put( "dsCommonHeaderSmall", JasperReportsUtils.convertReportData( cepr01040131Business.getHeaderRowList( command ) ) );
            params.put( "dsManfaatHeader", JasperReportsUtils.convertReportData( manfaatHeaderList ) );
            params.put( "dsManfaatTermRider", JasperReportsUtils.convertReportData( manfaatTermRiderList ) );
            params.put( "dsManfaatPersonalAccident", JasperReportsUtils.convertReportData( manfaatPersonalAccidentList ) );
            params.put( "dsManfaatPenyakitKritis", JasperReportsUtils.convertReportData( manfaatPenyakitKritisList ) );
            params.put( "dsManfaatPayorsClause", JasperReportsUtils.convertReportData( manfaatPayorsClauseList ) );
            params.put( "dsManfaatWaiverPremiumDisability", JasperReportsUtils.convertReportData( manfaatWaiverPremiumDisabilityList ) );
            params.put( "dsManfaatEkaSehat", JasperReportsUtils.convertReportData( manfaaEkaSehatList ) );
            params.put( "dsManfaatMeninggal", JasperReportsUtils.convertReportData( cepr01040131Business.getManfaatMeninggalList(command) ) );
            params.put( "dsPremiumSummary", JasperReportsUtils.convertReportData( premiumSummaryList ) );
            
            currentIdx = 0;
            Cepr00000000RiderBusiness riderBusiness = new Cepr00000000RiderBusiness( eproposalManager, editorUtil, command );
            putRiderParam( params, riderBusiness.getRiderEkaSehatListMap(), "dsRiderEkaSehat", "riderEkaSehatIndex");
            params.put( "participantEkaSehatInnerLimit", riderBusiness.checkParticapantEkaSehatInnerLimit() );
            params.put( "dsRiderEkaSehatParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderEkaSehatParticipantsList() ) );
            params.put( "businessIdx", businessIdx );

            //=========================================================    		
    		// Save E-Proposal PDF ke EBServer dan PopUp Download
            // ** Adrian@06/01/2014
            String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
            String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
            Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();       
            String fileName = msag_id+"_"+no_proposal+".pdf";
            params.put( "no_proposal",  no_proposal );
            
            List temp = new ArrayList();
            String rootDir_EProp="";
            if(flag_test==1){
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP_TEST;
            }else{
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP;
            }
           String dirName = rootDir_EProp+"\\"+msag_id+"\\"+no_proposal;  
           
            File sourceFile = new File( dirName + "\\" + fileName );
           try{
			            JasperUtils.exportReportToPdf(
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040131_product.jasper",
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


    // for PA used only
    public static Map<String, Object> getManfaatPersonalAccidentParamsMap( Object command, Cepr00000000EditorUtil editorUtil ) throws IOException
    {
        //logger.info( "*-*-*-* Cepr01040131DownloadController.getManfaatPersonalAccidentParamsMap" );
    	logger.info( "*-*-*-* Cepr01040131DownloadController.getManfaatPersonalAccidentParamsMap" );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        Map<String, Object> params = new HashMap<String, Object>();
        String termOfPayment = editorUtil.convertToString( cepr01030102Form.getTermOfPayment() ) + " tahun.";
        String say = "Apabila Tertanggung meninggal dunia karena kecelakaan dalam masa Pertanggungan PA Rider masih berlaku, maka akan dibayarkan Manfaat Asuransi Tambahan sebesar 100% Uang Pertanggungan PA rider.";
        params.put( "termOfPayment", termOfPayment );
        params.put( "paragraph1", say );
        return params;
    }
    
    public void putRiderParam( Map<String, Object> params, List contentList, String dsParamName, String idxParamName )
    {
        JRDataSource source = JrUtil.convertToDsAndSetNullIfEmpty( contentList );
        if( source != null ) currentIdx = currentIdx + 1;
        params.put( dsParamName, source );
        params.put( idxParamName, currentIdx.toString() + "." );
    }

}
