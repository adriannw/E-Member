package id.co.sinarmaslife.eproposal.product.product01040110;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040110DownloadController
 * Description         	: Hidup Bahagia (167)
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
import id.co.sinarmaslife.eproposal.business.Cepr00000000CommonUsedBusiness;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.datasource.ManfaatDataSource;
import id.co.sinarmaslife.eproposal.common.datasource.PremiumSummaryDataSource;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000MoneyUtil;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.model.vo.Cepr01040110TableVO;
import id.co.sinarmaslife.eproposal.model.vo.StdTableDetailVO;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.standard.model.vo.LimitVO;
import id.co.sinarmaslife.standard.util.*;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040110DownloadController extends StandardParent implements Controller
{
	protected final Log logger = LogFactory.getLog( getClass() );

    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040110DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01040110Business cepr01040110Business = new Cepr01040110Business( eproposalManager, editorUtil, command );

        ModelAndView result;
        if( request.getSession().getAttribute( "messageEkaSehatList" ) == null )
        {
        	List<String> messageEkaSehatList = cepr01040110Business.getEkaSehatMsgBox();
        	request.getSession().setAttribute( "messageEkaSehatList", messageEkaSehatList );
        	result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        
        if( request.getSession().getAttribute( "messageBoxList" ) == null )
        {
            List<String> messageBoxList = cepr01040110Business.getMedicalMsgBox();
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
            jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040110_product.jasper" );


            double accumulatedPremium;
            double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );

            List<Integer> yearNoList;
            int age = cepr01030101Form.getInsuredAge();
            LimitVO[] limitVOArr = new LimitVO[]{ new LimitVO( 1, 5 ), new LimitVO( 55 - age, 70 - age ) };
            yearNoList = ArrUtil.genPartitionsIntList( limitVOArr );

            Map<String, Object> rateParams = new HashMap<String, Object>();
            rateParams.put( "leftPartOfBusinessCd", new Integer( "167" ) );
            rateParams.put( "lsdbsNumber", cepr01030102Form.getRightPartOfBusinessCd() );
          //  rateParams.put( "lstabLamaTanggung", new Integer( "70" ) );
            rateParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_TUNAI );
            rateParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
            rateParams.put( "paymentModeCd", cepr01030102Form.getPaymentModeCd() );
            rateParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
            rateParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
            List<BigDecimal> rateList = new ArrayList<BigDecimal>();

            List<Integer> yearNoSingleList;
            List<BigDecimal> valueList;
            for( Integer year : yearNoList )
            {
                yearNoSingleList = new ArrayList<Integer>();
                yearNoSingleList.add( year );
                rateParams.put( "yearNoList", yearNoSingleList );
                valueList = eproposalManager.selectRateNew( rateParams );
                if( valueList == null || valueList.size() == 0 )
                {
                    valueList.add( new BigDecimal( "0" ) );
                }
                rateList.addAll( valueList );
            }

            List<StdTableDetailVO> detailVOList = new ArrayList<StdTableDetailVO>();
            Cepr01040110TableVO detailVO;

            String yearNoStr;
            String ageStr;
            String accumulatedStr;
            String cashValueStr;

            List<BigDecimal> cashValueList = new ArrayList<BigDecimal>();
            BigDecimal cashValue;

            for( BigDecimal rate : rateList )
            {
                cashValue = rate.multiply( cepr01030102Form.getTotalSumInsured() ).multiply( CommonConst.PER_MIL );
                cashValueList.add( cashValue );
            }

            Double totalInsured = LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() );
            double termOfPayment = LazyConverter.toInt( cepr01030102Form.getTermOfPayment() );
            double tahapanPasti = totalInsured * 0.2;
            double totalTahapanPasti = 0;
            String tahapanPastiStr = editorUtil.convertToString( tahapanPasti );
            String tahapanPastiTempStr;
            String benefitStr = editorUtil.convertToString( cepr01030102Form.getTotalSumInsured() );
            String oldAgeFundStr = editorUtil.convertToString( cepr01030102Form.getTotalSumInsured() );
            String oldAgeFundTempStr;
            int yearNo;

            for( int i = 0; i <= 20; i++ )
            {
                yearNo = yearNoList.get( i );
                yearNoStr = editorUtil.convertToString( yearNo );
                age = yearNo + cepr01030101Form.getInsuredAge();
                ageStr = editorUtil.convertToString( age );
                accumulatedPremium = yearNo < termOfPayment? premium * yearNo : premium * termOfPayment;
                accumulatedStr = editorUtil.convertToString( accumulatedPremium );
                cashValueStr = editorUtil.convertToString( cashValueList.get( i ) );
                if( age < 55 || age == 70 )
                {
                    tahapanPastiTempStr = "0.00";
                }
                else
                {
                    tahapanPastiTempStr = tahapanPastiStr;
                    totalTahapanPasti = totalTahapanPasti + tahapanPasti;
                }
                oldAgeFundTempStr = ( age == 70? oldAgeFundStr : "" );
                detailVO = new Cepr01040110TableVO( yearNoStr, ageStr, accumulatedStr, cashValueStr, oldAgeFundTempStr, tahapanPastiTempStr, benefitStr );
                detailVOList.add( detailVO ) ;
            }

            Integer lastAge = 70;

            String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
            BigDecimal totalSumInsured = cepr01030102Form.getTotalSumInsured();
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
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
//            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            params.put( "lastAge", editorUtil.convertToString( lastAge ) );
            params.put( "lastYearNo", editorUtil.convertToString( lastAge - cepr01030101Form.getInsuredAge() ) );
            params.put( "totalTahapanPasti", editorUtil.convertToString( totalTahapanPasti ) );
            params.put( "paymentModeCd", cepr01030102Form.getPaymentModeCd() );

            // Add Additional Assurance
            List<Map<String, Object>> manfaatPayorsClauseList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> manfaatWaiverPremiumDisabilityList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> premiumSummaryList;

            boolean additionalAssuranceExist = false;
            BigDecimal manfaatTermRiderAmount = new BigDecimal( "0" );
            BigDecimal manfaatPersonalAccidentAmount = new BigDecimal( "0" );
            BigDecimal manfaatPenyakitKritisAmount = new BigDecimal( "0" );
            BigDecimal manfaatPayorsClauseAmount = new BigDecimal( "0" );
            BigDecimal manfaatWaiverPremiumAmount = new BigDecimal( "0" );

            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getPayorsClauseFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getPayorsClauseFlagDisplay() ) )
            {
                additionalAssuranceExist = true;
                logger.info( "*-*-*-* Payors Clause Checked" );
                Cepr01040110Entry entry = new Cepr01040110Entry( eproposalManager, editorUtil, command, null );
                manfaatPayorsClauseAmount = entry.computeRiderPcPremium( cepr01030102Form );
                logger.info( "*-*-*-* manfaatPayorsClauseAmount = " + manfaatPayorsClauseAmount );
                manfaatPayorsClauseList.add( ManfaatDataSource.getManfaatPayorsClauseParamsMap() );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getWaiverPremiumDisabilityFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getWaiverPremiumDisabilityFlagDisplay() ) )
            {
                additionalAssuranceExist = true;
                logger.info( "*-*-*-* Waiver Premium Disability Checked" );
                Cepr01040110Entry entry = new Cepr01040110Entry( eproposalManager, editorUtil, command, null );
                manfaatWaiverPremiumAmount = entry.computeRiderWpdPremium( cepr01030102Form );
                logger.info( "*-*-*-* manfaatWaiverPremiumAmount = " + manfaatWaiverPremiumAmount );
                manfaatWaiverPremiumDisabilityList.add( ManfaatDataSource.getManfaatWaiverPremiumDisabilityParamsMap() );
            }

            // this will generate header for additional assurance
            List<Map<String, Object>> manfaatHeaderList = new ArrayList<Map<String, Object>>();
            if( additionalAssuranceExist )
            {
                Map<String, Object> manfaatHeaderMap = new HashMap<String, Object>();
                manfaatHeaderMap.put( "title", "MANFAAT PROGRAM ASURANSI TAMBAHAN" );
                manfaatHeaderList.add( manfaatHeaderMap );
            }

            premiumSummaryList = PremiumSummaryDataSource.getPremiumSummaryParamsMapList( command, editorUtil, manfaatTermRiderAmount, manfaatPersonalAccidentAmount, manfaatPenyakitKritisAmount, manfaatPayorsClauseAmount, manfaatWaiverPremiumAmount, null, null );
            params.put( "totalPremium1", editorUtil.convertToRoundedTwoDigits( Cepr00000000MoneyUtil.sum( premiumSummaryList, "premium1" ) ) );
            params.put( "totalPremium2", editorUtil.convertToRoundedTwoDigits( Cepr00000000MoneyUtil.sum( premiumSummaryList, "premium2" ) ) );
            params.put( "totalPremium3", editorUtil.convertToRoundedTwoDigits( Cepr00000000MoneyUtil.sum( premiumSummaryList, "premium3" ) ) );

            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
            params.put( "validityMsg", "" );

            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( cepr01040110Business.getHeaderRowList( command ) ) );
            params.put( "dsCommonHeaderPage2", JasperReportsUtils.convertReportData( cepr01040110Business.getHeaderRowList( command ) ) );
            params.put( "dsManfaatHeader", JasperReportsUtils.convertReportData( manfaatHeaderList ) );
            params.put( "dsManfaatPayorsClause", JasperReportsUtils.convertReportData( manfaatPayorsClauseList ) );
            params.put( "dsManfaatWaiverPremiumDisability", JasperReportsUtils.convertReportData( manfaatWaiverPremiumDisabilityList ) );
            params.put( "dsPremiumSummary", JasperReportsUtils.convertReportData( premiumSummaryList ) );

            params.put( "dsManfaatDetail", JasperReportsUtils.convertReportData( detailVOList ) );

            Cepr00000000CommonUsedBusiness commonUsedBusiness = new Cepr00000000CommonUsedBusiness( eproposalManager, editorUtil, command );
            if( !commonUsedBusiness.policyHolderIsInsured() )
            {
                if( cepr01030101Form.getPolicyHolderAge() > 60 - termOfPayment )
                {
                    params.put(
                            "note",
                            "Untuk MPP-" + ( int ) termOfPayment + " tahun, pemegang polis yang berusia > " + ( int ) ( 60 - termOfPayment ) +
                                    " tahun tidak dapat mengikuti Asuransi Tambahan Rider Payor's Clause." );
                }
            }

            // Fill all values to detail row
            jasperViewer.setReportDataKey( "dataSource" );
            params.put( "dataSource", getPageList( cepr01030102Form ) );

            //=========================    		
    		// Save E-Proposal PDF ke EBServer dan PopUp Download
            // ** Adrian@06/01/2014            
            String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
            String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
            Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();
          //  String fileName="testx.pdf";
            String fileName = msag_id+"_"+no_proposal+".pdf";
            
            List temp = getPageList( cepr01030102Form );
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
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040110_product.jasper",
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

    public List<Map<String, Object>> getPageList( Cepr01030102Form cepr01030102Form ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040110DownloadController.getPageList" );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub1" );
        result.add( params );

        if( Hardcode.PAY_MODE_CD_SEKALIGUS != cepr01030102Form.getPaymentModeCd() )
        {
            params = new HashMap<String, Object>();
            params.put( "pageCode", "sub2" );
            result.add( params );
        }
        return result;
    }

}
