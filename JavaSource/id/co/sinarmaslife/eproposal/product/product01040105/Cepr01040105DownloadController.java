package id.co.sinarmaslife.eproposal.product.product01040105;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040105DownloadController
 * Description         	: Super sejahtera (39)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 8, 2007 2:12:02 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import com.lowagie.text.pdf.PdfWriter;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.common.datasource.ManfaatDataSource;
import id.co.sinarmaslife.eproposal.common.datasource.PremiumSummaryDataSource;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000MoneyUtil;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.eproposal.model.vo.StdTableDetailVO;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.product.product01040104.Cepr01040104Business;
import id.co.sinarmaslife.standard.util.*;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
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
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040105DownloadController extends StandardParent implements Controller
{
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040105DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01040105Business cepr01040105Business = new Cepr01040105Business( eproposalManager, editorUtil, command );

        ModelAndView result;
        if( request.getSession().getAttribute( "messageEkaSehatList" ) == null )
        {
        	List<String> messageEkaSehatList = cepr01040105Business.getEkaSehatMsgBox();
        	request.getSession().setAttribute( "messageEkaSehatList", messageEkaSehatList );
        	result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        
        if( request.getSession().getAttribute( "messageBoxList" ) == null )
        {
            List<String> messageBoxList = cepr01040105Business.getMedicalMsgBox();
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
            jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040105_product.jasper" );


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
            for( int i = 1; i <= 20; i++ )
            {
                age++;
                ageList.add( age );
                yearNoList.add( i );
            }

            age = 69;
            ageList.add( age );
            yearNoList.add( 21 );

            Map<String, Object> rateParams = new HashMap<String, Object>();
            rateParams.put( "leftPartOfBusinessCd", new Integer( "39" ) );
            rateParams.put( "lstabLamaTanggung", new Integer( "79" ) );
            rateParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_TUNAI );
            rateParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
            rateParams.put( "paymentModeCd", cepr01030102Form.getPaymentModeCd() );
            rateParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
            rateParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
            rateParams.put( "yearNoList", yearNoList );
            logger.info( "*-*-*-* rateParams = " + rateParams );
            List rateList = eproposalManager.selectRate( rateParams );

            // Fill all values to detail row
            jasperViewer.setReportDataKey( "dataSource" );
            List<StdTableDetailVO> detailVOList = new ArrayList<StdTableDetailVO>();
            StdTableDetailVO detailVO;

            String yearStr;
            String ageStr;
            String accumulatedStr;
            String cashValueStr;
            List<BigDecimal> cashValueList = new ArrayList<BigDecimal>();
            BigDecimal cashValue = new BigDecimal( "0" );
            cashValueList.add( cashValue );
            for( int i = 0; i < rateList.size(); i++ )
            {
                BigDecimal rate = ( BigDecimal ) rateList.get( i );
                cashValue = rate.multiply( cepr01030102Form.getTotalSumInsured() ).multiply( CommonConst.PER_MIL );
                cashValueList.add( cashValue );
                logger.info( "*-*-*-* cashValue = " + cashValue );
            }

            for( int i = 0; i < 20; i++ )
            {
                if( i < yearNoList.size()  ) yearStr = editorUtil.convertToString( yearNoList.get( i ) ); else yearStr = "";
                if( i < ageList.size() ) ageStr = editorUtil.convertToString( ageList.get( i ) ); else ageStr = "";
                if( i < accumulatedPremiumList.size() ) accumulatedStr = editorUtil.convertToString( accumulatedPremiumList.get( i ) ); else accumulatedStr = "";
                cashValueStr = editorUtil.convertToString( cashValueList.get( i ) );

                detailVO = new StdTableDetailVO( yearStr, ageStr, accumulatedStr, cashValueStr, "" );
                detailVOList.add( detailVO ) ;
            }

            Integer lastAge = 79;

            String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
            BigDecimal totalSumInsured = cepr01030102Form.getTotalSumInsured();
//        params.put( "insuredName", editorUtil.convertToString( cepr01030101Form.getInsuredName() ) );
//        params.put( "insuredAge", editorUtil.convertToString( cepr01030101Form.getInsuredAge() ) + " tahun" );
//        params.put( "termOfContract", editorUtil.convertToString( cepr01030102Form.getTermOfContract() ) + " tahun" );
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

            // Add Additional Assurance
            List<Map<String, Object>> manfaatTermRiderList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> manfaatPersonalAccidentList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> manfaatPenyakitKritisList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> manfaatPayorsClauseList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> manfaatWaiverPremiumDisabilityList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> premiumSummaryList;

            boolean additionalAssuranceExist = false;
            BigDecimal manfaatTermRiderAmount = new BigDecimal( "0" );
            BigDecimal manfaatPersonalAccidentAmount = new BigDecimal( "0" );
            BigDecimal manfaatPenyakitKritisAmount = new BigDecimal( "0" );
            BigDecimal manfaatPayorsClauseAmount = new BigDecimal( "0" );
            BigDecimal manfaatWaiverPremiumAmount = new BigDecimal( "0" );
            List < Mst_proposal > mstProposal = new ArrayList<Mst_proposal>(); 

            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getTermRiderFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getTermRiderFlagDisplay() ) )
            {
                additionalAssuranceExist = true;
                logger.info( "*-*-*-* Term Rider Checked" );
                Cepr01040105Entry entry = new Cepr01040105Entry( eproposalManager, editorUtil, command, null );
                manfaatTermRiderAmount = entry.computeManfaat( cepr01030102Form.getPaymentModeCd(), Hardcode.MANFAAT_TERM_OF_RIDER_CD );
                manfaatTermRiderList.add( ManfaatDataSource.getManfaatTermRiderParamsMap( command, editorUtil ) );
                
                mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal( Hardcode.MANFAAT_TERM_OF_RIDER_CD ), new BigDecimal( Hardcode.LSTAB_JENIS_PREMI ),
              			 BigDecimal.ZERO, BigDecimal.ZERO, manfaatTermRiderAmount, new BigDecimal(cepr01030102Form.getTermOfContract()), 
              			 new BigDecimal( cepr01030102Form.getPaymentModeCd() ),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getPersonalAccidentFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getPersonalAccidentFlagDisplay() ) )
            {
                additionalAssuranceExist = true;
                logger.info( "*-*-*-* Personal Accident Checked" );
                Cepr01040105Entry entry = new Cepr01040105Entry( eproposalManager, editorUtil, command, null );
                manfaatPersonalAccidentAmount = entry.computeManfaat( cepr01030102Form.getPaymentModeCd(), Hardcode.MANFAAT_PERSONAL_ACCIDENT );
                manfaatPersonalAccidentList.add( ManfaatDataSource.getManfaatPersonalAccidentParamsMap( command, editorUtil ) );
                
                mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal( Hardcode.MANFAAT_PERSONAL_ACCIDENT ), new BigDecimal( Hardcode.LSTAB_JENIS_PREMI ),
             			 BigDecimal.ZERO, BigDecimal.ZERO, manfaatPersonalAccidentAmount, new BigDecimal(cepr01030102Form.getTermOfContract()), 
             			 new BigDecimal( cepr01030102Form.getPaymentModeCd() ),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getCriticalIllnessFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getCriticalIllnessFlagDisplay() ) )
            {
                additionalAssuranceExist = true;
                logger.info( "*-*-*-* Penyakit Kritis Checked" );
                Cepr01040105Entry entry = new Cepr01040105Entry( eproposalManager, editorUtil, command, null );
                manfaatPenyakitKritisAmount = entry.computeManfaat( cepr01030102Form.getPaymentModeCd(), Hardcode.MANFAAT_PENYAKIT_KRITIS );
                manfaatPenyakitKritisList.add( ManfaatDataSource.getManfaatPenyakitKritisParamsMap( command, editorUtil ) );
                params.put( "note", "*** Harus dengan pemeriksaan kesehatan" );
                
                mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal( Hardcode.MANFAAT_PENYAKIT_KRITIS ), new BigDecimal( Hardcode.LSTAB_JENIS_PREMI ),
            			 BigDecimal.ZERO, BigDecimal.ZERO, manfaatPenyakitKritisAmount, new BigDecimal(cepr01030102Form.getTermOfContract()), 
            			 new BigDecimal( cepr01030102Form.getPaymentModeCd() ),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getPayorsClauseFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getPayorsClauseFlagDisplay() ) )
            {
                additionalAssuranceExist = true;
                logger.info( "*-*-*-* Payors Clause Checked" );
                Cepr01040105Entry entry = new Cepr01040105Entry( eproposalManager, editorUtil, command, null );
                manfaatPayorsClauseAmount = entry.computeManfaat( cepr01030102Form.getPaymentModeCd(), Hardcode.MANFAAT_PAYORS_CLAUSE );
                manfaatPayorsClauseList.add( ManfaatDataSource.getManfaatPayorsClauseParamsMap() );
                
                mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal( Hardcode.MANFAAT_PAYORS_CLAUSE ), new BigDecimal( Hardcode.LSTAB_JENIS_PREMI ),
           			 BigDecimal.ZERO, BigDecimal.ZERO, manfaatPayorsClauseAmount, new BigDecimal(cepr01030102Form.getTermOfContract()), 
           			 new BigDecimal( cepr01030102Form.getPaymentModeCd() ),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getWaiverPremiumDisabilityFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getWaiverPremiumDisabilityFlagDisplay() ) )
            {
                additionalAssuranceExist = true;
                logger.info( "*-*-*-* Waiver Premium Disability Checked" );
                Cepr01040105Entry entry = new Cepr01040105Entry( eproposalManager, editorUtil, command, null );
                manfaatWaiverPremiumAmount = entry.computeManfaat( cepr01030102Form.getPaymentModeCd(), Hardcode.MANFAAT_WAIVER_PREMIUM_DISABILITY );
                manfaatWaiverPremiumDisabilityList.add( ManfaatDataSource.getManfaatWaiverPremiumDisabilityParamsMap() );
                
                mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal( Hardcode.MANFAAT_WAIVER_PREMIUM_DISABILITY ), new BigDecimal( Hardcode.LSTAB_JENIS_PREMI ),
              			 BigDecimal.ZERO, BigDecimal.ZERO, manfaatWaiverPremiumAmount, new BigDecimal(cepr01030102Form.getTermOfContract()), 
              			 new BigDecimal( cepr01030102Form.getPaymentModeCd() ),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
            }

            // this will generate header for additional assurance
            List<Map<String, Object>> manfaatHeaderList = new ArrayList<Map<String, Object>>();
            if( additionalAssuranceExist )
            {
                Map<String, Object> manfaatHeaderMap = new HashMap<String, Object>();
                manfaatHeaderMap.put( "title", "Manfaat Program Asuransi Tambahan" );
                manfaatHeaderList.add( manfaatHeaderMap );
            }

            params.put( "beforeLastCashValue", editorUtil.convertToString( cashValueList.get( 20 ) ) );
            params.put( "halfTotalSumInsured", editorUtil.convertToString( cepr01030102Form.getTotalSumInsured().divide( new BigDecimal( "2" ), RoundingMode.UP ) ) );

            premiumSummaryList = PremiumSummaryDataSource.getPremiumSummaryParamsMapList( command, editorUtil, manfaatTermRiderAmount, manfaatPersonalAccidentAmount, manfaatPenyakitKritisAmount, manfaatPayorsClauseAmount, manfaatWaiverPremiumAmount, null, null );
            params.put( "totalPremium1", editorUtil.convertToString( Cepr00000000MoneyUtil.sum( premiumSummaryList, "premium1" ) ) );
            params.put( "totalPremium2", editorUtil.convertToString( Cepr00000000MoneyUtil.sum( premiumSummaryList, "premium2" ) ) );
            params.put( "totalPremium3", editorUtil.convertToString( Cepr00000000MoneyUtil.sum( premiumSummaryList, "premium3" ) ) );

            params.put( "dataSource", detailVOList );
            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
            params.put( "validityMsg", "" );

            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( getHeaderRowList( command ) ) );
            params.put( "dsManfaatHeader", JasperReportsUtils.convertReportData( manfaatHeaderList ) );
            params.put( "dsManfaatTermRider", JasperReportsUtils.convertReportData( manfaatTermRiderList ) );
            params.put( "dsManfaatPersonalAccident", JasperReportsUtils.convertReportData( manfaatPersonalAccidentList ) );
            params.put( "dsManfaatPenyakitKritis", JasperReportsUtils.convertReportData( manfaatPenyakitKritisList ) );
            params.put( "dsManfaatPayorsClause", JasperReportsUtils.convertReportData( manfaatPayorsClauseList ) );
            params.put( "dsManfaatWaiverPremiumDisability", JasperReportsUtils.convertReportData( manfaatWaiverPremiumDisabilityList ) );
            params.put( "dsPremiumSummary", JasperReportsUtils.convertReportData( premiumSummaryList ) );

            jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
            request.getSession().setAttribute( "messageBoxList", null );
            request.getSession().setAttribute( "messageEkaSehatList", null );
            result = new ModelAndView( jasperViewer, params );
        }

        return result;
    }

    public List< Map<String, Object> > getHeaderRowList( Object command ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040105DownloadController.getCommonHeaderRowList" );

        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List< Map<String, Object> > result = new ArrayList< Map<String, Object> >();

        // Fill header content from CommonHeaderDataSource
        result.addAll( source.getInsuredNameMap() );
        result.addAll( source.getInsuredAgeMap() );
        result.addAll( source.getTermOfContractMap() );
        result.addAll( source.getTermOfPaymentMap() );
        result.addAll( source.getTotalSumInsuredMap() );
        result.addAll( source.getManfaatTermRiderMap() );
        result.addAll( source.getManfaatPersonalAccidentMap() );
        Cepr01040105Business cepr01040105Business = new Cepr01040105Business( eproposalManager, editorUtil, command );
        result.addAll( source.getPolicyHolderMedicalCheckUpMap( ( String ) cepr01040105Business.of_cek_medis( command ).get( "medis_pp" ) ) );
        result.addAll( source.getInsuredMedicalCheckUpMap( ( String ) cepr01040105Business.of_cek_medis( command ).get( "medis_tt" ) ) );

        return result;
    }

}