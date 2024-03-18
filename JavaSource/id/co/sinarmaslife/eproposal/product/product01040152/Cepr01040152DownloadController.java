package id.co.sinarmaslife.eproposal.product.product01040152;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040104DownloadController
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
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.datasource.ManfaatDataSource;
import id.co.sinarmaslife.eproposal.common.datasource.PremiumSummaryDataSource;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000MoneyUtil;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.eproposal.model.vo.StdTableDetailVO;
import id.co.sinarmaslife.eproposal.product.product01040105.Cepr01040105Entry;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
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
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Cepr01040152DownloadController extends StandardParent implements Controller
{
	protected final static Log logger = LogFactory.getLog( Cepr01040152DownloadController.class );
	static Integer currentIdx;
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040104DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        logger.info( "*-*-*-* editorUtil = " + editorUtil );
        Cepr01040152Business cepr01040152Business = new Cepr01040152Business( eproposalManager, editorUtil, command );
        currentIdx = 0;
        ModelAndView result;
        if( request.getSession().getAttribute( "messageBoxList" ) == null )
        {
        	List<String> messageBoxList = new ArrayList<String>();
            messageBoxList.addAll( ListUtil.addNothingIfEmpty( "" ) );

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
            jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040104_product.jasper" );

            
        	String sekaligusOrTahunan = null; // untuk manghandle tampilan subreport premium summary(tahunan) atau subreport premium sekaligus(sekaligus)
            String businessIdx;
            if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040152Mapper.X1  )
        		{ businessIdx = "5"; sekaligusOrTahunan = "tahunan"; }
            else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040152Mapper.X2 )
        		{ businessIdx = "10"; sekaligusOrTahunan = "tahunan"; }
            else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040152Mapper.X3 )
    			{ businessIdx = "15"; sekaligusOrTahunan = "tahunan"; }
            else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040152Mapper.X4 )
            	{ businessIdx = "20";  sekaligusOrTahunan = "tahunan"; }
            else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040152Mapper.X5 )
    			{ businessIdx = "SEKALIGUS";  sekaligusOrTahunan = "sekaligus"; }
            else
            	{ businessIdx = "";}
        
//            if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEKALIGUS )
//        	{ sekaligusOrTahunan = "sekaligus"; }

            String point1 = null;
            String point2 = null;
            String point3 = null;
            String point4 = null;
            if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040152Mapper.X1  ||
            		cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040152Mapper.X2 ||
            		cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040152Mapper.X3 ||
            		cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040152Mapper.X4
            		 )
            {
            	
            	point1 = "Premi dibayar selama "+businessIdx+" tahun, dan setelah itu polis menjadi bebas premi dan pertanggungan tetap berjalan.";
                point2 = "Apabila Tertanggung meninggal dunia pada waktu kontrak Asuransi masih berlaku dan usia Tertanggung pada saat itu kurang dari 100 tahun, maka kepada yang ditunjuk akan dibayarkan 100% (seratus persen) Uang Pertanggungan dan setelah itu kontrak Asuransi berakhir.";
            	point3 = "Apabila Tertanggung hidup pada akhir tahun polis dimana Tertanggung berusia 100 tahun dan kontrak Asuransi masih berlaku maka akan dibayarkan 100% (seratus persen) Uang Pertanggungan dan setelah itu kontrak Asuransi berakhir.";
            	point4 = "Apabila tertanggung memutuskan kontrak dalam masa asuransi (surrender) maka akan dibayarkan nilai tunai sesuai dengan perhitungan aktuaria yang berlaku untuk produk ini.";
            }
            else
            {
            	if( cepr01030102Form.getPaymentModeCd().equals( Hardcode.PAY_MODE_CD_SEKALIGUS ) ){
            		point1 = "Premi dibayar secara sekaligus di awal pertanggungan.";
            	}
            	point2 = "Apabila Tertanggung meninggal dunia pada waktu kontrak Asuransi masih berlaku dan usia Tertanggung pada saat itu kurang dari 100 tahun, maka kepada Ahli Waris (pihak yang ditunjuk) akan dibayarkan 100% (seratus persen) Uang Pertanggungan dan setelah itu kontrak Asuransi berakhir.";
             	point3 = "Apabila Tertanggung hidup pada akhir tahun polis dimana Tertanggung berusia 100 tahun dan kontrak Asuransi masih berlaku maka akan dibayarkan 100% (seratus persen) Uang Pertanggungan dan setelah itu kontrak Asuransi berakhir.";
             	point4 = "Apabila tertanggung memutuskan kontrak dalam masa asuransi (surrender) maka akan dibayarkan nilai tunai sesuai dengan perhitungan aktuaria yang berlaku untuk produk ini.";
            }
            List<BigDecimal> accumulatedPremiumList = new ArrayList<BigDecimal>();
            BigDecimal accumulatedPremium = new BigDecimal( "0" );
            BigDecimal premium = cepr01030102Form.getPremium();
            for( int i = 0; i < cepr01030102Form.getTermOfPayment(); i++ )
            {
            	
            	if( cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD ) ){
            		premium = premium.setScale(2, RoundingMode.DOWN);
            	}
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
            Integer lama_tanggung = 0;
            if( (cepr01030102Form.getRightPartOfBusinessCd() >= Cepr01040152Mapper.X1 && cepr01030102Form.getRightPartOfBusinessCd() <= Cepr01040152Mapper.X5)) {
            //	lama_tanggung = cepr01030102Form.getTermOfContract();
            }
            for( int i = 1; i <= 20; i++ )
            {
                age++;
                ageList.add( age );
                yearNoList.add( i );

                // get cash value(nilai tunai)
                //cashParams.put( "leftPartOfBusinessCd", new Integer( "163" ) );
                cashParams.put( "leftPartOfBusinessCd", new Integer( "226" ) );
                cashParams.put( "lstabLamaTanggung", lama_tanggung );
                cashParams.put( "lstabLamaBayar", cepr01030102Form.getTermOfPayment() );
                cashParams.put( "lsdbsNumber", cepr01030102Form.getRightPartOfBusinessCd() );
                cashParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_TUNAI );
                cashParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
                cashParams.put( "paymentModeCd", cepr01030102Form.getPaymentModeCd() );
                cashParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
                cashParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
                cashParams.put( "yearNo", i );
                
                ldec_rate = LazyConverter.toDouble( eproposalManager.selectCashDanaSejahteraNew( cashParams ) );
                ldec_tunai = ldec_rate * idec_up / ii_permil;
                cashValueList.add( ldec_tunai );
            }
            
            cashParams.put( "leftPartOfBusinessCd", new Integer( "226" ) );
            cashParams.put( "lstabLamaTanggung", lama_tanggung );
            cashParams.put( "lstabLamaBayar", cepr01030102Form.getTermOfPayment() );
            cashParams.put( "lsdbsNumber", cepr01030102Form.getRightPartOfBusinessCd() );
            cashParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_TUNAI );
            cashParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
            cashParams.put( "paymentModeCd", cepr01030102Form.getPaymentModeCd() );
            cashParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
            cashParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
            cashParams.put( "yearNo", (100-cepr01030101Form.getInsuredAge())-1  );
            ldec_rate = LazyConverter.toDouble( eproposalManager.selectCashDanaSejahteraNew( cashParams ) );
            ldec_tunai = ldec_rate * idec_up / ii_permil;
            params.put( "lastCashValue1", editorUtil.convertToString( ldec_tunai) );
            
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
                if( cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD ) ){
                	 if( i < accumulatedPremiumList.size() ) accumulatedStr = editorUtil.convertToTwoDigitTruncate( accumulatedPremiumList.get( i ) ); else accumulatedStr = "";
                }else{
                	 if( i < accumulatedPremiumList.size() ) accumulatedStr = editorUtil.convertToTwoDigitNoRound( accumulatedPremiumList.get( i ) ); else accumulatedStr = "";
                }
                cashValueStr = editorUtil.convertToTwoDigitNoRound2( cashValueList.get( i ) );
                detailVO = new StdTableDetailVO( yearStr, ageStr, accumulatedStr, cashValueStr, "" );
                detailVOList.add( detailVO ) ;
            }

            
            //Integer lastAge = 79;
            Integer lastAge = 100;
            
            String kursId="";
            String kursLabel="";
            if("01".equals(cepr01030102Form.getCurrencyCd())){
            	kursLabel = "Rp ";
            	kursId = "01";
            }else if("02".equals(cepr01030102Form.getCurrencyCd())){
            	kursLabel = "US$ ";
            	kursId = "02";
            }
            
            double minSwine = 0;
            if("01".equals(cepr01030102Form.getCurrencyCd()) )
            {
            	minSwine = 100000000;
            }
            else if("02".equals(cepr01030102Form.getCurrencyCd()) )
            {
            	minSwine = 10000;
            }
            
            if(cepr01030102Form.getTotalSumInsured().doubleValue() >= minSwine){
            	currentIdx = currentIdx + 1;
            	params.put( "swineRiderManfaat", kursLabel + editorUtil.convertToRoundedNoDigit(new BigDecimal(20000000)));
            	params.put( "riderSwineIndex", currentIdx + "");
            	params.put( "swineRider", "enable");
            }           

            String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
            BigDecimal totalSumInsured = cepr01030102Form.getTotalSumInsured();
            params.put( "marketer", editorUtil.convertToString( cepr01030101Form.getProposalUser() ) );
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
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
//            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            params.put( "currencySymbol", currencySymbol );
            params.put( "lastAge", editorUtil.convertToString( lastAge ) );
            params.put( "lastAge1", editorUtil.convertToString( lastAge-1 ) );
            params.put( "sekaligusOrTahunan", editorUtil.convertToString( sekaligusOrTahunan ) );
            params.put( "lastYearNo", editorUtil.convertToString( lastAge - cepr01030101Form.getInsuredAge() ) );
            params.put( "lastYearNo1", editorUtil.convertToString( lastAge - cepr01030101Form.getInsuredAge()-1 ) );
            params.put( "point1", point1 );
            params.put( "point2", point2 );
            params.put( "point3", point3 );
            params.put( "point4", point4 );
            params.put( "note", "* Persyaratan underwriting sesuai dengan persyaratan pemeriksaan kesehatan asuransi jiwa individu." );

            // Add Additional Assurance
            List<Map<String, Object>> manfaatTermRiderList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> manfaatPersonalAccidentList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> manfaatPenyakitKritisList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> manfaatPayorsClauseList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> manfaatWaiverPremiumDisabilityList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> manfaatTpdList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> premiumSummaryList;

            boolean additionalAssuranceExist = false;
            BigDecimal manfaatTermRiderAmount = new BigDecimal( "0" );
            BigDecimal manfaatPersonalAccidentAmount = new BigDecimal( "0" );
            BigDecimal manfaatPenyakitKritisAmount = new BigDecimal( "0" );
            BigDecimal manfaatPayorsClauseAmount = new BigDecimal( "0" );
            BigDecimal manfaatWaiverPremiumAmount = new BigDecimal( "0" );
            BigDecimal manfaatTpdAmount = new BigDecimal( "0" );
            List < Mst_proposal > mstProposal = new ArrayList<Mst_proposal>(); 
            
            // this is get from w_print_prop.of_dtl_premi
            if( cepr01030102Form.getPaymentModeCd() != null && cepr01030102Form.getPaymentModeCd() != Hardcode.PAY_MODE_CD_SEKALIGUS ){
	            if( cepr01030102Form.getPersonalAccidentFlagDisplay() != null && cepr01030102Form.getPersonalAccidentFlagDisplay().equals( CommonConst.DISPLAY_TRUE ) && CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getPersonalAccidentFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getPersonalAccidentFlagDisplay() ) )
	            {
	            	// save mstproposal
	                additionalAssuranceExist = true;
	                manfaatPersonalAccidentAmount = cepr01040152Business.computeManfaatNew( cepr01030102Form.getPaymentModeCd(), Hardcode.MANFAAT_PERSONAL_ACCIDENT, mstProposal );
	                manfaatPersonalAccidentList.add( getManfaatPersonalAccidentParamsMap( command, editorUtil ) );
	            } 	
	            if( cepr01030102Form.getCriticalIllnessFlagDisplay() != null && cepr01030102Form.getCriticalIllnessFlagDisplay().equals( CommonConst.DISPLAY_TRUE ) && CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getCriticalIllnessFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getCriticalIllnessFlagDisplay() ) )
	            {
	            	// save mstproposal
	                additionalAssuranceExist = true;
	                manfaatPenyakitKritisAmount = cepr01040152Business.computeManfaatPk( cepr01030102Form.getPaymentModeCd(), Hardcode.MANFAAT_PENYAKIT_KRITIS, mstProposal );
	                manfaatPenyakitKritisList.add( ManfaatDataSource.getManfaatPenyakitKritisParamsMap( command, editorUtil ) );
	                params.put( "note", "*** Harus dengan pemeriksaan kesehatan" );
	            }
	            if( cepr01030102Form.getPayorsClauseFlagDisplay() != null && cepr01030102Form.getPayorsClauseFlagDisplay().equals( CommonConst.DISPLAY_TRUE ) &&  CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getPayorsClauseFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getPayorsClauseFlagDisplay() ) )
	            {
	            	// save mstproposal
	                additionalAssuranceExist = true;
	                manfaatPayorsClauseAmount = cepr01040152Business.computeManfaatNew( cepr01030102Form.getPaymentModeCd(), Hardcode.MANFAAT_PAYORS_CLAUSE, mstProposal );
	                manfaatPayorsClauseList.add( ManfaatDataSource.getManfaatPayorsClauseParamsMap() );
	            }
	            if( cepr01030102Form.getWaiverPremiumDisabilityFlagDisplay() != null && cepr01030102Form.getWaiverPremiumDisabilityFlagDisplay().equals( CommonConst.DISPLAY_TRUE ) && CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getWaiverPremiumDisabilityFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getWaiverPremiumDisabilityFlagDisplay() ) )
	            {
	            	// save mstproposal
	            	int[] li_jenis = { 6, 7, 8, 9, 0 };
	                additionalAssuranceExist = true;
	                BigDecimal temp = BigDecimal.ZERO;
	                int lsdbsNumber = 1;
	                Integer lsbsId = 804;
	                if( cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD ) ){
	                	Map<String, Object> paramForPkRate = new HashMap<String, Object>();
	                	Integer lstabLamaTanggung = cepr01030102Form.getRightPartOfBusinessCd()==1?5:cepr01030102Form.getRightPartOfBusinessCd()==2?10
	                			:cepr01030102Form.getRightPartOfBusinessCd()==3?15:20;
	                	lsbsId = 804;
	                	paramForPkRate.put( "leftPartOfBusinessCd", lsbsId );
	                	paramForPkRate.put( "lstabLamaTanggung", lstabLamaTanggung );
	                	paramForPkRate.put( "lsdbsNumber", lsdbsNumber );
	                	paramForPkRate.put( "lstabJenis", 1 );
	                	paramForPkRate.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
	                	paramForPkRate.put( "paymentModeCd", cepr01030102Form.getPaymentModeCd() );
	                	paramForPkRate.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
	                	paramForPkRate.put( "insuredAge", cepr01030101Form.getInsuredAge() );
	                	paramForPkRate.put( "yearNo", 1 );
	                    temp = eproposalManager.selectCashDanaSejahteraNew( paramForPkRate );
	                }else{
	                	lsbsId = 814;
	                	Map<String, Object> paramForPkRate = new HashMap<String, Object>();
		                paramForPkRate.put("lsbsId", lsbsId );
		                paramForPkRate.put("businessNo", li_jenis[(cepr01030102Form.getTermOfPayment()/5) - 1] );	
		                paramForPkRate.put("currencyCd", cepr01030102Form.getCurrencyCd() );
		                paramForPkRate.put("insuredAge", cepr01030101Form.getInsuredAge() );
		                temp = eproposalManager.selectPkRate( paramForPkRate );
	                }
	//                Double theRate = cepr01040104Business.of_get_rate( cepr01030102Form.getPaymentModeCd(), Hardcode.MANFAAT_WAIVER_PREMIUM_DISABILITY );
	                Double theRate = LazyConverter.toDouble( temp );
	                Double thePremium = LazyConverter.toDouble( premium );
	                Double manfaat = theRate * thePremium / 1000;
	                manfaatWaiverPremiumAmount = new BigDecimal( manfaat );
	                manfaatWaiverPremiumDisabilityList.add( ManfaatDataSource.getManfaatWaiverPremiumDisabilityParamsMap() );
	                mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal( lsbsId ), new BigDecimal( 1 ),
	              			 BigDecimal.ZERO, BigDecimal.ZERO, temp, new BigDecimal(cepr01030102Form.getTermOfContract()), 
	              			 new BigDecimal( cepr01030102Form.getPaymentModeCd() ),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
	                
	            }
	            if( cepr01030102Form.getTpdFlagDisplay() != null && cepr01030102Form.getTpdFlagDisplay().equals( CommonConst.DISPLAY_TRUE ) && CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getTpdFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getTpdFlagDisplay() ) )
	            {
	            	// save mstproposal
	                additionalAssuranceExist = true;
	                manfaatTpdAmount = cepr01040152Business.computeManfaatTpd( cepr01030102Form.getPaymentModeCd(), Hardcode.MANFAAT_TPD, mstProposal );
	                manfaatTpdList.add( ManfaatDataSource.getManfaatTpdParamsMap( command, editorUtil ) );
	            }
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
            premiumSummaryList = PremiumSummaryDataSource.getPremiumSummaryNoRoundParamsMapList( command, editorUtil, manfaatTermRiderAmount, manfaatPersonalAccidentAmount, manfaatPenyakitKritisAmount, manfaatPayorsClauseAmount, manfaatWaiverPremiumAmount, manfaatTpdAmount, labelMap );

            params.put( "totalPremium1", premiumSummaryList.get(0).get("premium1") );
            params.put( "totalPremium2", premiumSummaryList.get(0).get("premium2") );
            params.put( "totalPremium3", premiumSummaryList.get(0).get("premium3") );
            params.put( "totalPremium4", premiumSummaryList.get(0).get("premium4") );
//            params.put( "totalPremium4", editorUtil.convertToRoundedTwoDigits( Cepr00000000MoneyUtil.sum( premiumSummaryList, "premium4" ) ) );
            
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

            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( cepr01040152Business.getHeaderRowList( command, "title" ) ) );
            params.put( "dsAboveDetail", JasperReportsUtils.convertReportData( cepr01040152Business.getHeaderRowList( command, "aboveDetail" ) ) );
            params.put( "dsManfaatHeader", JasperReportsUtils.convertReportData( manfaatHeaderList ) );
            params.put( "dsManfaatTermRider", JasperReportsUtils.convertReportData( manfaatTermRiderList ) );
            params.put( "dsManfaatPersonalAccident", JasperReportsUtils.convertReportData( manfaatPersonalAccidentList ) );
            params.put( "dsManfaatPenyakitKritis", JasperReportsUtils.convertReportData( manfaatPenyakitKritisList ) );
            params.put( "dsManfaatTpd", JasperReportsUtils.convertReportData( manfaatTpdList ) );
            params.put( "dsManfaatPayorsClause", JasperReportsUtils.convertReportData( manfaatPayorsClauseList ) );
            params.put( "dsManfaatWaiverPremiumDisability", JasperReportsUtils.convertReportData( manfaatWaiverPremiumDisabilityList ) );
            params.put( "dsPremiumSummary", JasperReportsUtils.convertReportData( premiumSummaryList ) );
            params.put( "dsDetail", JasperReportsUtils.convertReportData( detailVOList ) );
            
            params.put( "businessIdx", businessIdx );
            
            //String title = "SMiLe DANA SEJAHTERA";
            Integer lsbsId = cepr01030102Form.getLeftPartOfBusinessCd();
            String title = eproposalManager.selectTitleBisnis(1, lsbsId);
            
            if( cepr01030102Form.getRightPartOfBusinessCd() >= 6 && cepr01030102Form.getRightPartOfBusinessCd() <= 10 ){// DANA SEJAHTERA BSM
            	title = "DANA SEJAHTERA";
            }
            params.put( "title", title );
            String productLabel = "SIMAS LEGACY PLAN";
        	String subproductLabel = businessIdx;
            
//          =========================
            String namaAgen = cepr01030101Form.getProposalUser();
            if(namaAgen == null)namaAgen = "";
            String sendType= (String) request.getSession().getAttribute("pdfEvent");//(Integer) session.getAttribute("sendType");
            request.getSession().removeAttribute("pdfEvent");
            if(sendType == null)sendType = "";
            String type="";
            if("onPressButtonFax".equals(sendType)){
    			type="SENDFAX";
    		}else if("onPressButtonEmail".equals(sendType)){
    			type="SENDEMAIL";
    		}
    		
    		if(!"".equals(sendType)){
    			Properties props = new Properties();
    			props.put("xAction-Type", type);
    			props.put("xAction-To", "");
    			props.put("xAction-Subject", "Simulasi "+ productLabel + " " + subproductLabel +" [Nama Telemarketing : "+ namaAgen +"] ");
    			//product+" [Nama Telemarketing : "+params.get("nama_agen")+"] ");
    			props.put("Content-Disposition", "attachment;filename=proposal.pdf");
    			jasperViewer.setHeaders(props);		
    		}
            //=========================    		
    		// Save E-Proposal PDF ke EBServer dan PopUp Download
            // ** Adrian@06/01/2014
            
            String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
            String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
            Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();
          //  String fileName="testx.pdf";
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
      
	        	   //if(cekDataApprovedOrNot.get(0).getPosisi()<3){
			            JasperUtils.exportReportToPdf(
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040104_product.jasper",
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
            result = new ModelAndView( jasperViewer, params );
        }

        return result;
    }


    // for PA used only
    public static Map<String, Object> getManfaatPersonalAccidentParamsMap( Object command, Cepr00000000EditorUtil editorUtil ) throws IOException
    {
        //logger.info( "*-*-*-* Cepr01040104DownloadController.getManfaatPersonalAccidentParamsMap" );
        logger.info("*-*-*-* Cepr01040104DownloadController.getManfaatPersonalAccidentParamsMap");
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        Map<String, Object> params = new HashMap<String, Object>();
        String termOfPayment = editorUtil.convertToString( cepr01030102Form.getTermOfPayment() ) + " tahun.";
        double ldec_uppk = MathUtil.min( LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() ), 1000000000 );
        BigDecimal up = new BigDecimal( ldec_uppk );
        String say = "Apabila Tertanggung meninggal dunia karena kecelakaan dalam masa Pertanggungan SMiLe PA Rider masih berlaku, maka akan dibayarkan Manfaat Asuransi Tambahan sebesar 100% Uang Pertanggungan SMiLe PA rider (" + AngkaTerbilang.indonesian( up.toString(), cepr01030102Form.getCurrencyCd()) + ").";
        params.put( "termOfPayment", termOfPayment );
        params.put( "paragraph1", say );
        return params;
    }

}
