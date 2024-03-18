package id.co.sinarmaslife.eproposal.product.product01040134;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040134DownloadController
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
import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.common.datasource.ManfaatDataSource;
import id.co.sinarmaslife.eproposal.common.datasource.PremiumSummaryDataSource;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000MoneyUtil;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.model.vo.ParticipantVO;
import id.co.sinarmaslife.eproposal.model.vo.StdTableDetailVO;
import id.co.sinarmaslife.eproposal.product.product01040133.Cepr01040133DownloadController;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030103Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030106Form;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040134DownloadController extends StandardParent implements Controller
{
	protected final static Log logger = LogFactory.getLog( Cepr01040134DownloadController.class );
	static Integer currentIdx;
	
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040134DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01030103Form cepr01030103Form = cepr01030000HoldingForm.getCepr01030103Form();
        Cepr01030106Form cepr01030106Form = cepr01030000HoldingForm.getCepr01030106Form();
        logger.info( "*-*-*-* editorUtil = " + editorUtil );
        Cepr01040134Business cepr01040134Business = new Cepr01040134Business( eproposalManager, editorUtil, command );

        int rightPartOfBusinessCd = cepr01030102Form.getRightPartOfBusinessCd();
        ModelAndView result;
        if( request.getSession().getAttribute( "messageEkaSehatList" ) == null )
        {
        	List<String> messageEkaSehatList = cepr01040134Business.getEkaSehatMsgBox();
        	request.getSession().setAttribute( "messageEkaSehatList", messageEkaSehatList );
        	result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        
        if( request.getSession().getAttribute( "messageBoxList" ) == null )
        {
            List<String> messageBoxList = cepr01040134Business.getMedicalMsgBox();
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
            jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040134_product.jasper" );

            String businessIdx;
//            if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040134Mapper.X17 )
//            {
//                businessIdx = "SEKALIGUS";
//            }
//            else
//            {
                businessIdx = Integer.toString( cepr01030102Form.getRightPartOfBusinessCd() * 5 );
//            }

            List<BigDecimal> accumulatedPremiumList = new ArrayList<BigDecimal>();
            BigDecimal accumulatedPremium = new BigDecimal( "0" );
            BigDecimal premium = cepr01030102Form.getPremium();
            for( int i = 0; i < cepr01030102Form.getTermOfPayment(); i++ )
            {
                accumulatedPremium = accumulatedPremium.add( premium );
                accumulatedPremiumList.add( accumulatedPremium );
            }

            List<Integer> yearNoList = new ArrayList<Integer>();
            List<Double> cashValueList = new ArrayList<Double>();
            double  li_kali = 100;
            if( cepr01030102Form.getPaymentModeCd() == 2 ){
            	li_kali = 52.5;
            }else if( cepr01030102Form.getPaymentModeCd() == 1 ){
            	li_kali = 27;
            }else if( cepr01030102Form.getPaymentModeCd() == 6 ){
            	li_kali = 10;
            }
            double ld_premi = LazyConverter.toDouble( cepr01030102Form.getPremium() );
            ld_premi = (ld_premi * 100 / 100) / (li_kali / 100);
           
            double idec_up = LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() );
            double ii_permil = 1000;
            double ldec_rate;
            double ldec_tunai;
            int li_jenis = cepr01030102Form.getRightPartOfBusinessCd();
            Map<String, Object> cashParams = new HashMap<String, Object>();
            for( int i = 1; i <= 8; i++ )
            {
                yearNoList.add( i );
                // get cash value(nilai tunai)
                cashParams.put( "leftPartOfBusinessCd", new Integer( "96" ) );
                cashParams.put( "lstabLamaTanggung", cepr01030102Form.getTermOfContract() );
                cashParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_TUNAI );
                cashParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
                cashParams.put( "paymentModeCd", 3 );
                cashParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
                cashParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
                cashParams.put( "yearNo", i );
                ldec_rate = LazyConverter.toDouble( eproposalManager.selectCashDanaSejahtera( cashParams ) );
                ldec_tunai = ldec_rate * idec_up / ii_permil;
                cashValueList.add( ldec_tunai );
            }
            // Fill all values to detail row
            jasperViewer.setReportDataKey( "dataSource" );
            List<StdTableDetailVO> detailVOList = new ArrayList<StdTableDetailVO>();

            double[] nonCelaka = { 25, 40, 60, 80, 100, 0, 0, 0 };
            if( cepr01030101Form.getInsuredAge() <= 55 )
            {
            	nonCelaka = new double[]{ 100, 100, 100, 100, 100, 0, 0, 0 };
            }
            
            int[] lifeBenefitNotTake = { 0, 0, 0, 0, 0, 600, 633, 675};//600, 650, 720} ;
            List< Map< String, Object > > mapList = new ArrayList< Map< String, Object > >();
            Map< String, Object > map;
            for( int i = 0 ; i < 8 ; i ++ )
            {
            	map = new HashMap< String, Object >();
            	BigDecimal nonCelakaIndex = new BigDecimal( nonCelaka[i] );
            	double cashValueCount;
            	double lifeBenefit;
            	
            	if( i == 4 )
            	{
                	cashValueCount = ld_premi * 5.7;
                	lifeBenefit = ld_premi * 5.7;
            	}
            	else
            	{
            		cashValueCount = cashValueList.get( i );
            		lifeBenefit = 0;
            	}
//            	cashValueCount =
            	map.put("NONCELAKA", editorUtil.convertToRoundedTwoDigits( ( nonCelakaIndex.multiply( cepr01030102Form.getTotalSumInsured().divide( new BigDecimal( 100 ) ) ) ).add( new BigDecimal( cashValueCount ) ) ));
            	map.put("CELAKA",  editorUtil.convertToRoundedTwoDigits( ( LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() ) * 2 ) + cashValueCount ));
            	map.put("CASHVALUE", editorUtil.convertToRoundedTwoDigits( cashValueList.get( i ) ));
            	map.put("YEARNO", i + 1 + "");
            	map.put("LIFEBENEFIT", editorUtil.convertToRoundedTwoDigits( lifeBenefit ));
            	map.put("LIFEBENEFITNOTTAKE", editorUtil.convertToRoundedTwoDigits( ( lifeBenefitNotTake[i] * ld_premi ) / 100 ));
            	
            	mapList.add( map );
            }
            params.put( "manfaat", JasperReportsUtils.convertReportData(mapList) );

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
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ));
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            params.put( "currencySymbol", currencySymbol );
            params.put( "lastAge", editorUtil.convertToString( lastAge ) );
            params.put( "lastYearNo", editorUtil.convertToString( lastAge - cepr01030101Form.getInsuredAge() ) );
            params.put( "note", "Persyaratan underwriting sesuai dengan persyaratan pemeriksaan kesehatan asuransi jiwa individu." );
            params.put( "logoBismilah", eproposalManager.getProps().getProperty( "logo.bismilah" ) );
            params.put( "usiaMaxTerm", "68" );
            params.put( "riderTermRiderTitle", "SMiLe Term Insurance Syariah Rider 8");
            

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

            String kursLabel="";
            String kursId="";
            
            if("01".equals(cepr01030102Form.getCurrencyCd())){
            	kursLabel = "Rp ";
            	kursId = "01";
            }else if("02".equals(cepr01030102Form.getCurrencyCd())){
            	kursLabel = "US$ ";
            	kursId = "02";
            }
            
            int jenis = 0;
            
            if(cepr01030103Form.getHcpFamilyCd() != null){
            	jenis = cepr01030103Form.getHcpFamilyCd();
            	for(int i = 0 ; i < 20 ; i++){
                	if(cepr01030103Form.getHcpFamilyCd() == (i+1)){
                		String[][] mPlanHcp = {	{"R-100" , "100000" , "200000" , "200000" , "250000" , "500000" , "750000" , "1000000" },
        										{"R-200" , "200000" , "400000" , "400000" , "500000" , "1000000", "1500000", "2000000" },
        										{"R-300" , "300000" , "600000" , "600000" , "750000" , "1500000", "2250000", "3000000" },
        										{"R-400" , "400000" , "800000" , "800000" , "1000000", "2000000", "3000000", "4000000" },
        										{"R-500" , "500000" , "1000000", "1000000", "1250000", "2500000", "3750000", "5000000" },
        										{"R-600" , "600000" , "1200000", "1200000", "1500000", "3000000", "4500000", "6000000" },
        										{"R-700" , "700000" , "1400000", "1400000", "1750000", "3500000", "5250000", "7000000" },
        										{"R-800" , "800000" , "1600000", "1600000", "2000000", "4000000", "6000000", "8000000" },
        										{"R-900" , "900000" , "1800000", "1800000", "2250000", "4500000", "6750000", "9000000" },
        										{"R-1000", "1000000", "2000000", "2000000", "2500000", "5000000", "7500000", "10000000"},
        										{"D-10"  , "10"     , "20"     , "20"     , "25"     , "50"     , "75"     , "100"     },
        										{"D-20"  , "20"     , "40"     , "40"     , "40"     , "80"     , "120"    , "160"     },
        										{"D-30"  , "30"     , "60"     , "60"     , "75"     , "150"    , "225"    , "300"     },
        										{"D-40"  , "40"     , "80"     , "80"     , "80"     , "160"    , "240"    , "320"     },
        										{"D-50"  , "50"     , "100"    , "100"    , "100"    , "200"    , "300"    , "400"     },
        										{"D-60"  , "60"     , "120"    , "120"    , "120"    , "240"    , "360"    , "480"     },
        										{"D-70"  , "70"     , "140"    , "140"    , "140"    , "280"    , "420"    , "560"     },
        										{"D-80"  , "80"     , "160"    , "160"    , "160"    , "320"    , "480"    , "640"     },
        										{"D-90"  , "90"     , "180"    , "180"    , "180"    , "360"    , "540"    , "720"     },
        										{"D-100" , "100"    , "200"    , "200"    , "200"    , "400"    , "600"    , "800"     } };
                		
                		params.put( "jenisSantunan", mPlanHcp[i][0]);
                		params.put( "sub1", editorUtil.convertToRoundedNoDigit(new BigDecimal(mPlanHcp[i][1])));
                		params.put( "sub2", editorUtil.convertToRoundedNoDigit(new BigDecimal(mPlanHcp[i][2])));
                		params.put( "sub3", editorUtil.convertToRoundedNoDigit(new BigDecimal(mPlanHcp[i][3])));
                		params.put( "minor", editorUtil.convertToRoundedNoDigit(new BigDecimal(mPlanHcp[i][4])));
                		params.put( "intermediate", editorUtil.convertToRoundedNoDigit(new BigDecimal(mPlanHcp[i][5])));
                		params.put( "major", editorUtil.convertToRoundedNoDigit(new BigDecimal(mPlanHcp[i][6])));
                		params.put( "complex", editorUtil.convertToRoundedNoDigit(new BigDecimal(mPlanHcp[i][7])));
                		i=19;
                	}
                }
            }
            
            Map ciRider;
            Map termRider;
            Map hcpFamilyRider;
            Map paRider;
            Map hcpRider;
            Map tpdRider;
            Map ekaSehatRider;
            Map ekaSehatIlRider;
            Map ladiesInsRider;
            Map hcpLadRider;
            Map ladiesMedRider;
            Map hcpProRider;
            Map ladiesMedIlRider;
            BigDecimal premiTotalSekaligus = new BigDecimal(0);
            BigDecimal premiTotalTahunan = new BigDecimal(0);
            BigDecimal premiTotalSemesteran = new BigDecimal(0);
            BigDecimal premiTotalTriwulanan = new BigDecimal(0);
            BigDecimal premiTotalBulanan = new BigDecimal(0);
            JRDataSource source = null;
            Cepr00000000RiderBusiness riderBusiness = new Cepr00000000RiderBusiness( eproposalManager, editorUtil, command );
            ciRider = cepr01040134Business.of_get_coi_ci(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            termRider = cepr01040134Business.of_get_coi_term(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            paRider = cepr01040134Business.of_get_coi_pa(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            hcpRider = cepr01040134Business.of_get_coi_hcp(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            tpdRider = cepr01040134Business.of_get_coi_tpd(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            ekaSehatRider = cepr01040134Business.of_get_coi_eka_sehat(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            ekaSehatIlRider = cepr01040134Business.of_get_coi_eka_sehat_il(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            ladiesInsRider = cepr01040134Business.of_get_coi_ladies_ins(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            hcpLadRider = cepr01040134Business.of_get_coi_hcp_lad(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            ladiesMedRider = cepr01040134Business.of_get_coi_ladies_med(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            ladiesMedIlRider = cepr01040134Business.of_get_coi_ladies_med_il(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            hcpProRider = cepr01040134Business.of_get_coi_hcp_pro(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            if(jenis != 0){
            	source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpFamilyList() );
                if( source != null ){
	            	hcpFamilyRider = cepr01040134Business.of_get_coi_hcp_family(jenis, kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
	            	params.put( "premiHcpFamilySekaligus", hcpFamilyRider.get("hcpFamilyRiderSekaligus").toString() );
	                params.put( "premiHcpFamilyTahunan", hcpFamilyRider.get("hcpFamilyRiderTahunan").toString() );
	                params.put( "premiHcpFamilySemesteran", hcpFamilyRider.get("hcpFamilyRiderSemesteran").toString() );
	                params.put( "premiHcpFamilyTriwulanan", hcpFamilyRider.get("hcpFamilyRiderTriwulanan").toString() );
	                params.put( "premiHcpFamilyBulanan", hcpFamilyRider.get("hcpFamilyRiderBulanan").toString() );
	                premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(hcpFamilyRider.get("premiTotalSekaligus").toString()));
	                premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(hcpFamilyRider.get("premiTotalTahunan").toString()));
	                premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(hcpFamilyRider.get("premiTotalSemesteran").toString()));
	                premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(hcpFamilyRider.get("premiTotalTriwulanan").toString()));
	                premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(hcpFamilyRider.get("premiTotalBulanan").toString()));
                }
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderCiList() );
            if( source != null ){
	            params.put( "premiCiSekaligus", ciRider.get("ciRiderSekaligus").toString() );
	            params.put( "premiCiTahunan", ciRider.get("ciRiderTahunan").toString() );
	            params.put( "premiCiSemesteran", ciRider.get("ciRiderSemesteran").toString() );
	            params.put( "premiCiTriwulanan", ciRider.get("ciRiderTriwulanan").toString() );
	            params.put( "premiCiBulanan", ciRider.get("ciRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(ciRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(ciRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(ciRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(ciRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(ciRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderTermRiderList() );
            if( source != null ){
	            params.put( "premiTermSekaligus", termRider.get("termRiderSekaligus").toString() );
	            params.put( "premiTermTahunan", termRider.get("termRiderTahunan").toString() );
	            params.put( "premiTermSemesteran", termRider.get("termRiderSemesteran").toString() );
	            params.put( "premiTermTriwulanan", termRider.get("termRiderTriwulanan").toString() );
	            params.put( "premiTermBulanan", termRider.get("termRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(termRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(termRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(termRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(termRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(termRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderPaList() );
            if( source != null ){
	            params.put( "premiPaSekaligus", paRider.get("paRiderSekaligus").toString() );
	            params.put( "premiPaTahunan", paRider.get("paRiderTahunan").toString() );
	            params.put( "premiPaSemesteran", paRider.get("paRiderSemesteran").toString() );
	            params.put( "premiPaTriwulanan", paRider.get("paRiderTriwulanan").toString() );
	            params.put( "premiPaBulanan", paRider.get("paRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(paRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(paRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(paRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(paRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(paRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpList() );
            if( source != null ){
	            params.put( "premiHcpSekaligus", hcpRider.get("hcpRiderSekaligus").toString() );
	            params.put( "premiHcpTahunan", hcpRider.get("hcpRiderTahunan").toString() );
	            params.put( "premiHcpSemesteran", hcpRider.get("hcpRiderSemesteran").toString() );
	            params.put( "premiHcpTriwulanan", hcpRider.get("hcpRiderTriwulanan").toString() );
	            params.put( "premiHcpBulanan", hcpRider.get("hcpRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(hcpRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(hcpRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(hcpRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(hcpRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(hcpRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderTpdList() );
            if( source != null ){
	            params.put( "premiTpdSekaligus", tpdRider.get("tpdRiderSekaligus").toString() );
	            params.put( "premiTpdTahunan", tpdRider.get("tpdRiderTahunan").toString() );
	            params.put( "premiTpdSemesteran", tpdRider.get("tpdRiderSemesteran").toString() );
	            params.put( "premiTpdTriwulanan", tpdRider.get("tpdRiderTriwulanan").toString() );
	            params.put( "premiTpdBulanan", tpdRider.get("tpdRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(tpdRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(tpdRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(tpdRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(tpdRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(tpdRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderEkaSehatListMap() );
            if( source != null ){
	            params.put( "premiEkaSehatSekaligus", ekaSehatRider.get("ekaSehatRiderSekaligus").toString() );
	            params.put( "premiEkaSehatTahunan", ekaSehatRider.get("ekaSehatRiderTahunan").toString() );
	            params.put( "premiEkaSehatSemesteran", ekaSehatRider.get("ekaSehatRiderSemesteran").toString() );
	            params.put( "premiEkaSehatTriwulanan", ekaSehatRider.get("ekaSehatRiderTriwulanan").toString() );
	            params.put( "premiEkaSehatBulanan", ekaSehatRider.get("ekaSehatRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(ekaSehatRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(ekaSehatRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(ekaSehatRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(ekaSehatRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(ekaSehatRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderEkaSehatInnerLimitListMap() );
            if( source != null ){
	            params.put( "premiEkaSehatIlSekaligus", ekaSehatIlRider.get("ekaSehatIlRiderSekaligus").toString() );
	            params.put( "premiEkaSehatIlTahunan", ekaSehatIlRider.get("ekaSehatIlRiderTahunan").toString() );
	            params.put( "premiEkaSehatIlSemesteran", ekaSehatIlRider.get("ekaSehatIlRiderSemesteran").toString() );
	            params.put( "premiEkaSehatIlTriwulanan", ekaSehatIlRider.get("ekaSehatIlRiderTriwulanan").toString() );
	            params.put( "premiEkaSehatIlBulanan", ekaSehatIlRider.get("ekaSehatIlRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(ekaSehatIlRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(ekaSehatIlRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(ekaSehatIlRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(ekaSehatIlRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(ekaSehatIlRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderLadiesInsList() );
            if( source != null ){
	            params.put( "premiLadiesInsSekaligus", ladiesInsRider.get("ladiesInsRiderSekaligus").toString() );
	            params.put( "premiLadiesInsTahunan", ladiesInsRider.get("ladiesInsRiderTahunan").toString() );
	            params.put( "premiLadiesInsSemesteran", ladiesInsRider.get("ladiesInsRiderSemesteran").toString() );
	            params.put( "premiLadiesInsTriwulanan", ladiesInsRider.get("ladiesInsRiderTriwulanan").toString() );
	            params.put( "premiLadiesInsBulanan", ladiesInsRider.get("ladiesInsRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(ladiesInsRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(ladiesInsRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(ladiesInsRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(ladiesInsRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(ladiesInsRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpLadiesList() );
            if( source != null ){
	            params.put( "premiHcpLadSekaligus", hcpLadRider.get("hcpLadRiderSekaligus").toString() );
	            params.put( "premiHcpLadTahunan", hcpLadRider.get("hcpLadRiderTahunan").toString() );
	            params.put( "premiHcpLadSemesteran", hcpLadRider.get("hcpLadRiderSemesteran").toString() );
	            params.put( "premiHcpLadTriwulanan", hcpLadRider.get("hcpLadRiderTriwulanan").toString() );
	            params.put( "premiHcpLadBulanan", hcpLadRider.get("hcpLadRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(hcpLadRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(hcpLadRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(hcpLadRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(hcpLadRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(hcpLadRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderLadiesMedExpenseListMap() );
            if( source != null ){
	            params.put( "premiLadiesMedSekaligus", ladiesMedRider.get("ladiesMedRiderSekaligus").toString() );
	            params.put( "premiLadiesMedTahunan", ladiesMedRider.get("ladiesMedRiderTahunan").toString() );
	            params.put( "premiLadiesMedSemesteran", ladiesMedRider.get("ladiesMedRiderSemesteran").toString() );
	            params.put( "premiLadiesMedTriwulanan", ladiesMedRider.get("ladiesMedRiderTriwulanan").toString() );
	            params.put( "premiLadiesMedBulanan", ladiesMedRider.get("ladiesMedRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(ladiesMedRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(ladiesMedRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(ladiesMedRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(ladiesMedRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(ladiesMedRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderLadiesMedExpenseInnerLimitListMap() );
            if( source != null ){
	            params.put( "premiLadiesMedIlSekaligus", ladiesMedIlRider.get("ladiesMedIlRiderSekaligus").toString() );
	            params.put( "premiLadiesMedIlTahunan", ladiesMedIlRider.get("ladiesMedIlRiderTahunan").toString() );
	            params.put( "premiLadiesMedIlSemesteran", ladiesMedIlRider.get("ladiesMedIlRiderSemesteran").toString() );
	            params.put( "premiLadiesMedIlTriwulanan", ladiesMedIlRider.get("ladiesMedIlRiderTriwulanan").toString() );
	            params.put( "premiLadiesMedIlBulanan", ladiesMedIlRider.get("ladiesMedIlRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(ladiesMedIlRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(ladiesMedIlRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(ladiesMedIlRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(ladiesMedIlRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(ladiesMedIlRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpProviderList() );
            if( source != null ){
	            params.put( "premiHcpProSekaligus", hcpProRider.get("hcpProRiderSekaligus").toString() );
	            params.put( "premiHcpProTahunan", hcpProRider.get("hcpProRiderTahunan").toString() );
	            params.put( "premiHcpProSemesteran", hcpProRider.get("hcpProRiderSemesteran").toString() );
	            params.put( "premiHcpProTriwulanan", hcpProRider.get("hcpProRiderTriwulanan").toString() );
	            params.put( "premiHcpProBulanan", hcpProRider.get("hcpProRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(hcpProRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(hcpProRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(hcpProRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(hcpProRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(hcpProRider.get("premiTotalBulanan").toString()));
            }
            
            
            if("01".equals(kursId))
            {
            	params.put( "premiTotalSekaligus", editorUtil.convertToRoundedNoDigit(premiTotalSekaligus )+".00");
            	params.put( "premiTotalTahunan", editorUtil.convertToRoundedNoDigit(premiTotalTahunan )+".00");
            }else if("02".equals(kursId))
            {
            	params.put( "premiTotalSekaligus", editorUtil.convertToRoundedTwoDigits(premiTotalSekaligus ));
            	params.put( "premiTotalTahunan", editorUtil.convertToRoundedTwoDigits(premiTotalTahunan ));
            }
            params.put( "premiTotalSemesteran", editorUtil.convertToRoundedNoDigit(premiTotalSemesteran)+".00" );
            params.put( "premiTotalTriwulanan", editorUtil.convertToRoundedNoDigit(premiTotalTriwulanan)+".00" );
            params.put( "premiTotalBulanan", editorUtil.convertToRoundedNoDigit(premiTotalBulanan)+".00" );
            
            params.put( "termRiderManfaat", kursLabel + editorUtil.convertToRoundedNoDigit(cepr01030102Form.getTotalSumInsured()));
            params.put( "ciRiderManfaat", kursLabel + editorUtil.convertToRoundedNoDigit(cepr01030102Form.getTotalSumInsured().multiply(new BigDecimal(0.5))));

            int no = 0;
            List<ParticipantVO> participantVOList =  cepr01030106Form.getParticipantVOList();
            for(int i = 0 ; i < participantVOList.size() ; i++){
            	if(participantVOList.get(i).getName() != null && !"".equals(participantVOList.get(i).getName()) && participantVOList.get(i).getAge() != null && !"".equals(participantVOList.get(i).getAge())){
            		no++;
            		params.put( "participantName"+i, participantVOList.get(i).getName());
                    params.put( "participantAge"+i, participantVOList.get(i).getAge().toString());
            	}
            }
            
            if(no > 0){
            	params.put( "participantExist", "yes");
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
            premiumSummaryList = PremiumSummaryDataSource.getPremiumSummaryParamsMapList( command, editorUtil, manfaatTermRiderAmount, manfaatPersonalAccidentAmount, manfaatPenyakitKritisAmount, manfaatPayorsClauseAmount, manfaatWaiverPremiumAmount, BigDecimal.ZERO, BigDecimal.ZERO, labelMap );

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
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            params.put( "usia_tt", cepr01030101Form.getInsuredAge() );
            
            
            if( Hardcode.PAY_MODE_CD_TAHUNAN == cepr01030102Form.getPaymentModeCd() )
            {
                params.put( "dsDetailTahunan", JasperReportsUtils.convertReportData( detailVOList ) );
            }
            else if( Hardcode.PAY_MODE_CD_SEKALIGUS == cepr01030102Form.getPaymentModeCd() )
            {
                params.put( "dsDetailSekaligus", JasperReportsUtils.convertReportData( detailVOList ) );
            }
            
            String titleReport = "";
            if( Cepr01040134Mapper.X19 == cepr01030102Form.getRightPartOfBusinessCd() ) // MULTI INVEST SILVER
    	    {
            	titleReport = "MULTI INVEST SYARIAH SILVER";
    	    }
            else if( Cepr01040134Mapper.X20 == cepr01030102Form.getRightPartOfBusinessCd())// MULTI INVEST GOLD
    	    {
            	titleReport = "MULTI INVEST SYARIAH GOLD";
    	    }
            else if( Cepr01040134Mapper.X21 == cepr01030102Form.getRightPartOfBusinessCd())// MULTI INVEST PLATINUM
    	    {
            	titleReport = "MULTI INVEST SYARIAH PLATINUM";
    	    }
            else if(Cepr01040134Mapper.X22 == cepr01030102Form.getRightPartOfBusinessCd() ) // MULTI INVEST SILVER
    	    {
            	titleReport = "SMiLe MULTI INVEST SYARIAH SILVER";
    	    }
            else if(Cepr01040134Mapper.X23 == cepr01030102Form.getRightPartOfBusinessCd() )// MULTI INVEST GOLD
    	    {
            	titleReport = "SMiLe MULTI INVEST SYARIAH GOLD";
    	    }
            else if(Cepr01040134Mapper.X24 == cepr01030102Form.getRightPartOfBusinessCd() )// MULTI INVEST PLATINUM
    	    {
            	titleReport = "SMiLe MULTI INVEST SYARIAH PLATINUM";
    	    }
            
            currentIdx = 0;
            putRiderParam( params, riderBusiness.getRiderPaList(), "dsRiderPa", "riderPaIndex" );
            params.put( "riderPaRisk", riderBusiness.getRiderPaRisk() );
            params.put( "dsRiderPaClone", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderPaList() ) );
            params.put( "riderLadiesInsTitle", MappingUtil.getLabel(cepr01030103Form.getLadiesInsList(), cepr01030103Form.getLadiesInsCd()));
            putRiderParam( params, riderBusiness.getRiderHcpList(), "dsRiderHcp", "riderHcpIndex" );
            putRiderParam( params, riderBusiness.getRiderHcpFamilyList(), "dsRiderHcpFamily", "riderHcpFamilyIndex" );
            putRiderParam( params, riderBusiness.getRiderHcpLadiesList(), "dsRiderHcpLadies", "riderHcpLadiesIndex" );
            putRiderParam( params, riderBusiness.getRiderTpdList(), "dsRiderTpd", "riderTpdIndex" );
            putRiderParam( params, riderBusiness.getRiderTermRiderList(), "dsRiderTermRider", "riderTermRiderIndex" );
            putRiderParam( params, riderBusiness.getRiderLadiesInsList(), "dsRiderLadiesIns", "riderLadiesInsIndex" );
            putRiderParam( params, riderBusiness.getRiderEkaSehatListMap(), "dsRiderEkaSehat", "riderEkaSehatIndex");
            putRiderParam( params, riderBusiness.getRiderHcpProviderList(), "dsRiderHcpProvider", "riderHcpProviderIndex" );
            putRiderParam( params, riderBusiness.getRiderCiList(), "dsRiderCi", "riderCiIndex" );
            putRiderParam( params, riderBusiness.getRiderEkaSehatInnerLimitListMap(), "dsRiderEkaSehatInnerLimit", "riderEkaSehatInnerLimitIndex");
            putRiderParam( params, riderBusiness.getRiderLadiesMedExpenseListMap(), "dsRiderLadiesMedExpense", "riderLadiesMedExpenseIndex");
            putRiderParam( params, riderBusiness.getRiderLadiesMedExpenseInnerLimitListMap(), "dsRiderLadiesMedExpenseInnerLimit", "riderLadiesMedExpenseInnerLimitIndex");
            
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
            params.put( "riderHcpFamilyTitle", "SMiLe Hospital Protection Syariah (Family) Rider 8");
            //params.put( "baseBenefitHcpFam", riderBusiness.computeBaseBenefitHcpFam() );
            
            if(currentIdx > 0){ 
            	params.put( "dsCommonHeader2", JasperReportsUtils.convertReportData( cepr01040134Business.getHeaderRowList( command ) ) );
            	params.put( "dsCommonHeaderx", JasperReportsUtils.convertReportData( cepr01040134Business.getHeaderRowList( command ) ) );
            }
            
            params.put( "dataSource", cepr01040134Business.getPageList(currentIdx) );
            
            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.syariah" ) );
            params.put( "validityMsg", "" );

            params.put( "titleReport", titleReport );
            params.put( "umur", cepr01030101Form.getInsuredAge());
            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( cepr01040134Business.getHeaderRowList( command ) ) );
            params.put( "dsManfaatHeader", JasperReportsUtils.convertReportData( manfaatHeaderList ) );
            params.put( "dsManfaatTermRider", JasperReportsUtils.convertReportData( manfaatTermRiderList ) );
            params.put( "dsManfaatPersonalAccident", JasperReportsUtils.convertReportData( manfaatPersonalAccidentList ) );
            params.put( "dsManfaatPenyakitKritis", JasperReportsUtils.convertReportData( manfaatPenyakitKritisList ) );
            params.put( "dsManfaatPayorsClause", JasperReportsUtils.convertReportData( manfaatPayorsClauseList ) );
            params.put( "dsManfaatWaiverPremiumDisability", JasperReportsUtils.convertReportData( manfaatWaiverPremiumDisabilityList ) );
            params.put( "dsPremiumSummary", JasperReportsUtils.convertReportData( premiumSummaryList ) );

            params.put( "businessIdx", businessIdx );            
            params.put( "businessId",cepr01030102Form.getRightPartOfBusinessCd() );

           //==========================================    		
    		// Save E-Proposal PDF ke EBServer dan PopUp Download
            // ** Adrian@06/01/2014
            String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
            String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
            Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();       
            String fileName = msag_id+"_"+no_proposal+".pdf";
            params.put( "no_proposal",  no_proposal );
            
            List temp = cepr01040134Business.getPageList(currentIdx);
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
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040134_product.jasper",
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
       // logger.info( "*-*-*-* Cepr01040134DownloadController.getManfaatPersonalAccidentParamsMap" );
        logger.info( "*-*-*-* Cepr01040134DownloadController.getManfaatPersonalAccidentParamsMap" );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        Map<String, Object> params = new HashMap<String, Object>();
        String termOfPayment = editorUtil.convertToString( cepr01030102Form.getTermOfPayment() ) + " tahun.";
        String say = "Apabila Tertanggung meninggal dunia karena kecelakaan dalam masa Pertanggungan PA Rider masih berlaku, maka akan dibayarkan Manfaat Asuransi Tambahan sebesar 100% Uang Pertanggungan PA rider.";
        params.put( "termOfPayment", termOfPayment );
        params.put( "paragraph1", say );
        return params;
    }
    
    public List< Map<String, Object> > getHeaderRowList( Object command ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040118DownloadController.getCommonHeaderRowList" );

        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List< Map<String, Object> > result = new ArrayList< Map<String, Object> >();

        // Fill header content from CommonHeaderDataSource
        result.addAll( source.getInsuredNameMap() );
        result.addAll( source.getInsuredAgeMap() );
        result.addAll( source.getTermOfContractMap() );
        result.addAll( source.getTermOfPaymentWithSekaligusMap() );
        result.addAll( source.getTotalSumInsuredMap() );
        result.addAll( source.getManfaatTermRiderMap() );
        result.addAll( source.getManfaatPersonalAccidentMap() );


        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        Map<String, Object> params;
        params = new HashMap<String, Object>();
        params.put( "label", "Masa Garansi Investasi Pertama (MGI 1) *)" );
        params.put( "content", editorUtil.convertToString( cepr01030102Form.getMgiCd() ).concat( " bulan" ) );
        result.add( params );

        params = new HashMap<String, Object>();
        params.put( "label", "Tingkat Suku bunga MGI Pertama" );
        params.put( "content", editorUtil.convertToString( cepr01030102Form.getInvestRateInPercent() ).concat( "% (p.a)" ) );
        result.add( params );

        String taxBeforeAfterChoice = MappingUtil.getLabel( cepr01030102Form.getTaxBeforeAfterList(), cepr01030102Form.getTaxBeforeAfterCd() );
        params = new HashMap<String, Object>();
        params.put( "label", "Perhitungan Saldo Premi Deposit & NT" );
        params.put( "content", taxBeforeAfterChoice.concat( " Pajak" ) );
        result.add( params );

        return result;
    }
    
    
    public void putRiderParam( Map<String, Object> params, List contentList, String dsParamName, String idxParamName )
    {
        JRDataSource source = JrUtil.convertToDsAndSetNullIfEmpty( contentList );
        if( source != null ) currentIdx = currentIdx + 1;
        params.put( dsParamName, source );
        params.put( idxParamName, currentIdx.toString() + "." );
    }
    
//    public void putRiderParam( Map<String, Object> params, List contentList, String dsParamName, String idxParamName )
//    {
//        JRDataSource source = JrUtil.convertToDsAndSetNullIfEmpty( contentList );
//        if( source != null ){
//        	currentIdx = currentIdx + 1;
//        	if("dsRiderHcpFamily".equals(dsParamName)){
//        		params.put( dsParamName, "on" );
//        		params.put( idxParamName, currentIdx.toString() + "." );
//        	}else if("dsRiderHcpFamilyParticipants".equals(dsParamName)){
//        		params.put( dsParamName, "on" );
//        		//params.put( idxParamName, currentIdx.toString() + "." );
//        	}else if("dsRiderCi".equals(dsParamName)){
//        		params.put( dsParamName, "on" );
//        		params.put( idxParamName, currentIdx.toString() + "." );
//        	}else if("dsRiderTermRider".equals(dsParamName)){
//        		params.put( dsParamName, "on" );
//        		params.put( idxParamName, currentIdx.toString() + "." );
//        	}
//        }
//
//        //params.put( dsParamName, source );
//        //params.put( idxParamName, currentIdx.toString() + "." );
//    }

}
