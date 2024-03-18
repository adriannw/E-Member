package id.co.sinarmaslife.eproposal.product.product01040222;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040136DownloadController
 * Description         	: Super sejahtera (39)
 * Environment      	: Java  1.5.0_06
 * Author               : Fadly Mathendra
 * Version              : 1.0
 * Creation Date    	: Aug 8, 2009 2:12:02 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.business.Cepr00000000RiderBusiness;
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
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.JrUtil;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.ListUtil;
import id.co.sinarmaslife.standard.util.MappingUtil;
import id.co.sinarmaslife.standard.util.MathUtil;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.jasperreports.AbstractJasperReportsView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.lowagie.text.pdf.PdfWriter;

public class Cepr01040222DownloadController extends StandardParent implements Controller
{
	static Integer currentIdx;
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040136DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01040222Business cepr01040222Business = new Cepr01040222Business( eproposalManager, editorUtil, command );
        
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
            jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040222_product.jasper" );


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
            int beforeLastYearNo = 0;
            if( cepr01030101Form.getInsuredAge() < 69 )
            {
            	int temp = 69 - ageList.get( ageList.size() - 2);
            	beforeLastYearNo = 20 + temp;
            }
            yearNoList.add( beforeLastYearNo );
            List rateList = new ArrayList();
            for( int i = 0 ; i < yearNoList.size() ; i ++ )
            {
	            Map<String, Object> rateParams = new HashMap<String, Object>();
	            rateParams.put( "leftPartOfBusinessCd", cepr01030102Form.getLeftPartOfBusinessCd() );
	            rateParams.put( "lstabLamaTanggung", new Integer("0") );
	            rateParams.put( "lsdbsNumber", cepr01030102Form.getRightPartOfBusinessCd() );
	            rateParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_TUNAI );
	            rateParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
	            rateParams.put( "paymentModeCd", cepr01030102Form.getPaymentModeCd() );
	            rateParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
	            rateParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
	            rateParams.put( "yearNoList", yearNoList.get(i) );
	            logger.info( "*-*-*-* rateParams = " + rateParams );
	            BigDecimal selectRate = eproposalManager.selectRateForLoopingYearNoNew( rateParams );
	            if( selectRate == null || selectRate != null && selectRate == BigDecimal.ZERO )
	            {
	            	selectRate = BigDecimal.ZERO;
	            }
	            rateList.add( selectRate );
            }
            // Fill all values to detail row
            jasperViewer.setReportDataKey( "dataSource" );
            List<StdTableDetailVO> detailVOList = new ArrayList<StdTableDetailVO>();            
            StdTableDetailVO detailVO;

            String yearStr;
            String ageStr;
            String accumulatedStr;
            String cashValueStr;
            String oldAgeFund = "";
            List<BigDecimal> cashValueList = new ArrayList<BigDecimal>();
            BigDecimal cashValue = new BigDecimal( "0" );
            cashValueList.add( cashValue );
            for( int i = 0; i < rateList.size(); i++ )
            {
                BigDecimal rate = new BigDecimal( rateList.get( i ).toString()  );
                cashValue = rate.multiply( cepr01030102Form.getTotalSumInsured() ).multiply( CommonConst.PER_MIL );
                cashValueList.add( cashValue );
                logger.info( "*-*-*-* cashValue = " + cashValue );
            }

            for( int i = 0; i < 20; i++ )
            {
                if( i < yearNoList.size()  ) yearStr = editorUtil.convertToString( yearNoList.get( i ) ); else yearStr = "";
                if( i < ageList.size() ) ageStr = editorUtil.convertToString( ageList.get( i ) ); else ageStr = "";
                if( i < accumulatedPremiumList.size() ) accumulatedStr = editorUtil.convertToString( accumulatedPremiumList.get( i ) ); else accumulatedStr = "";
                if( cepr01030101Form.getInsuredAge() > 48 ) 
                {
                	if( ageStr != null && ageStr.equals("69") ){
                		oldAgeFund = editorUtil.convertToString(cepr01030102Form.getTotalSumInsured().divide( new BigDecimal( "2" ), RoundingMode.UP ) );
                	}else{
                		oldAgeFund = "";
                	}
                }
                cashValueStr = editorUtil.convertToString( cashValueList.get( i + 1 ) );

                detailVO = new StdTableDetailVO( yearStr, ageStr, accumulatedStr, cashValueStr, oldAgeFund );
                detailVOList.add( detailVO ) ;
            }

            Integer lastAge = 79;

            String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
         	String currency;
            String test = cepr01030102Form.getCurrencyCd();
            if( cepr01030102Form.getCurrencyCd() != null && Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	currency = "Rp.";
            }
            else if( cepr01030102Form.getCurrencyCd() != null && Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	currency = "$";
            }
            else 
            {
            	currency = "Rp.";
            }
            	
            BigDecimal totalSumInsured = cepr01030102Form.getTotalSumInsured();
            
            String displayBeforeLast = "available"; // utk menghandle tampilan detailVOList utk umur 69 
            if( cepr01030101Form.getInsuredAge() > 48 ) // jika umur insured nya lebih dari 48 maka detail utk umur 69 dihilangkan.
            {
            	displayBeforeLast = "invisible";
            }
            
        	String businessIdx = null; // utk menampilkan title super sejahtera 5 / 10 /15 / 20 / sekaligus
        	String sekaligusOrTahunan = null; // untuk manghandle tampilan subreport premium summary(tahunan) atau subreport premium sekaligus(sekaligus)

            if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040222Mapper.X1 )
            	{ businessIdx = "5"; sekaligusOrTahunan = "tahunan"; }
            else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040222Mapper.X2 ){
            	{ businessIdx = "10"; sekaligusOrTahunan = "tahunan"; }
            }
            
            if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEKALIGUS )
            	{ sekaligusOrTahunan = "sekaligus"; }
            
            String point1 = null;
            String point2 = null;
            String point3 = null;
            String point4 = null;
            String dmTmOrNot = null;
            
            if( 	cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040222Mapper.X1 )
            {
            	point1 = "Apabila Tertanggung hidup dan polis masih berlaku, maka manfaat polis ini dapat dibayarkan pada saat Tertanggung berusia 69 tahun sebesar  50% Uang Pertanggungan dan sisanya dibayarkan pada saat berusia 79 tahun sebesar 50% Uang Pertanggungan dan setelah itu kontrak asuransi berakhir.";
            	point2 = "Apabila Tertanggung meninggal dunia bukan karena kecelakaan  dalam 1(satu) bulan pertama Polis dan usia Tertanggung pada saat itu kurang dari 69 tahun, maka tidak ada pembayaran manfaat asuransi apapun kepada Ahli Waris (pihak yang ditunjuk) dan Penanggung akan mengembalikan 100% (seratus persen) dari Premi yang telah dibayarkan dan setelah itu kontrak Asuransi berakhir.";
            	point3 = "Apabila Tertanggung meninggal dunia karena kecelakaan dalam 1(satu) bulan pertama Polis dan usia Tertanggung pada saat itu kurang dari 69 tahun, maka kepada Ahli Waris (pihak yang ditunjuk) akan dibayarkan 100% (seratus persen) Uang Pertanggungan dan setelah itu kontrak Asuransi berakhir.";
            	point4 = "Apabila Tertanggung meninggal dunia karena sebab apapun setelah 1(satu) bulan pertama Polis dan usia Tertanggung pada saat itu kurang dari 69 tahun, maka kepada Ahli Waris (pihak yang ditunjuk) akan dibayarkan 100% (seratus persen) Uang Pertanggungan dan setelah itu kontrak Asuransi berakhir.  Tetapi Apabila Tertanggung meninggal pada saat usia 69 tahun atau lebih maka kepada ahli waris akan dibayarkan 50% Uang Pertanggungan dan selanjutnya kontrak Asuransi berakhir.";
            	dmTmOrNot = "yes";
            }
            else
            {
            	point1 = "Apabila Tertanggung hidup dan polis masih berlaku, maka manfaat polis ini dapat dibayarkan pada saat Tertanggung berusia 69 tahun sebesar  50% Uang Pertanggungan dan sisanya dibayarkan pada saat berusia 79 tahun sebesar 50% Uang Pertanggungan dan setelah itu kontrak asuransi berakhir.";
            	point2 = "Apabila Tertanggung meninggal dunia dalam masa asuransi dan usia Tertanggung pada saat itu kurang dari 69 tahun, maka kepada Ahli Warisnya akan dibayarkan 100% Uang Pertanggungan dan setelah itu kontrak asuransi berakhir. Tetapi apabila Tertanggung meninggal pada saat usia 69 tahun atau lebih maka kepada Ahli Waris akan dibayarkan 50% Uang Pertanggungan, dan setelah itu kontrak asuransi berakhir.";
            	point3 = null;
            	point4 = null;
            	dmTmOrNot = "no";
            }
            
            //        params.put( "insuredName", editorUtil.convertToString( cepr01030101Form.getInsuredName() ) );
//        params.put( "insuredAge", editorUtil.convertToString( cepr01030101Form.getInsuredAge() ) + " tahun" );
//        params.put( "termOfContract", editorUtil.convertToString( cepr01030102Form.getTermOfContract() ) + " tahun" );
            params.put( "point1", editorUtil.convertToString( point1 ) );
            params.put( "point2", editorUtil.convertToString( point2 ) );
            params.put( "point3", editorUtil.convertToString( point3 ) );
            params.put( "point4", editorUtil.convertToString( point4 ) );
            params.put( "dmTmOrNot", editorUtil.convertToString( dmTmOrNot ) );
            params.put( "marketer", editorUtil.convertToString( cepr01030101Form.getProposalUser() ) );
            params.put( "premium", currencySymbol + editorUtil.convertToString( premium ) );
            params.put( "currencySymbol", currencySymbol );
            params.put( "ssNumber", LazyConverter.toString( cepr01030102Form.getRightPartOfBusinessCd() ) );
            params.put( "dailyCompensation1", editorUtil.convertToString( totalSumInsured ) );
            params.put( "dailyCompensation2", editorUtil.convertToString( totalSumInsured.multiply( new BigDecimal( 2.0 ) ) ) );
            params.put( "dailyCompensation3", editorUtil.convertToString( totalSumInsured.multiply( new BigDecimal( 2.0 ) ) ) );
            params.put( "compensation41", editorUtil.convertToString( totalSumInsured.multiply( new BigDecimal( 2.5 ) ) ) );
            params.put( "compensation42", editorUtil.convertToString( totalSumInsured.multiply( new BigDecimal( 5.0 ) ) ) );
            params.put( "compensation43", editorUtil.convertToString( totalSumInsured.multiply( new BigDecimal( 7.5 ) ) ) );
            params.put( "compensation44", editorUtil.convertToString( totalSumInsured.multiply( new BigDecimal( 10.0 ) ) ) );
            params.put( "compensation5", editorUtil.convertToString( totalSumInsured.multiply( new BigDecimal( 50.0 ) ) ) );
            // disclaimer =========================================================================================================
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            //=====================================================================================================================
            params.put( "lastAge", editorUtil.convertToString( lastAge ) );
            params.put( "lastYearNo", editorUtil.convertToString( lastAge - cepr01030101Form.getInsuredAge() ) );
            params.put( "beforeLastYearNo",  editorUtil.convertToString( beforeLastYearNo ) );
            params.put( "displayBeforeLast", editorUtil.convertToString( displayBeforeLast ) );
            params.put("businessIdx", editorUtil.convertToString( businessIdx ) );
            params.put("sekaligusOrTahunan", editorUtil.convertToString( sekaligusOrTahunan ) );

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
            BigDecimal halfTotalSumInsured = cepr01030102Form.getTotalSumInsured().divide( new BigDecimal( "2" ), RoundingMode.UP );
            List < Mst_proposal > mstProposal = new ArrayList<Mst_proposal>();

            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getTermRiderFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getTermRiderFlagDisplay() ) )
            {
                additionalAssuranceExist = true;
                logger.info( "*-*-*-* Term Rider Checked" );
                Cepr01040222Entry entry = new Cepr01040222Entry( eproposalManager, editorUtil, command, null );
                manfaatTermRiderAmount = entry.computeManfaat( cepr01030102Form.getPaymentModeCd(), Hardcode.MANFAAT_TERM_OF_RIDER_CD );
                manfaatTermRiderList.add( cepr01040222Business.getManfaatTermRiderParamsMap( command, editorUtil ) );
                
                mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal( Hardcode.MANFAAT_TERM_OF_RIDER_CD ), new BigDecimal( Hardcode.LSTAB_JENIS_PREMI ),
           			 BigDecimal.ZERO, BigDecimal.ZERO, manfaatTermRiderAmount, new BigDecimal(cepr01030102Form.getTermOfContract()), 
           			 new BigDecimal( cepr01030102Form.getPaymentModeCd() ),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getPersonalAccidentFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getPersonalAccidentFlagDisplay() ) )
            {
                additionalAssuranceExist = true;
                logger.info( "*-*-*-* Personal Accident Checked" );
                Cepr01040222Entry entry = new Cepr01040222Entry( eproposalManager, editorUtil, command, null );
                manfaatPersonalAccidentAmount = entry.computeManfaat( cepr01030102Form.getPaymentModeCd(), Hardcode.MANFAAT_PERSONAL_ACCIDENT );
                logger.info( "*-*-*-* manfaatPersonalAccidentAmount = " + manfaatPersonalAccidentAmount );
                manfaatPersonalAccidentList.add( cepr01040222Business.getManfaatPersonalAccidentParamsMap( command, editorUtil ) );
                
                mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal( Hardcode.MANFAAT_PERSONAL_ACCIDENT ), new BigDecimal( Hardcode.LSTAB_JENIS_PREMI ),
              			 BigDecimal.ZERO, BigDecimal.ZERO, manfaatPersonalAccidentAmount, new BigDecimal(cepr01030102Form.getTermOfContract()), 
              			 new BigDecimal( cepr01030102Form.getPaymentModeCd() ),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getCriticalIllnessFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getCriticalIllnessFlagDisplay() ) )
            {
                additionalAssuranceExist = true;
                logger.info( "*-*-*-* Penyakit Kritis Checked" );
                Cepr01040222Entry entry = new Cepr01040222Entry( eproposalManager, editorUtil, command, null );
                Map<String, Object> paramForPkRate = new HashMap<String, Object>();
                int businessNo = 0;
                if(		cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040222Mapper.X1 )
                {
                //	businessNo =  cepr01030102Form.getRightPartOfBusinessCd() - 4;    
                	businessNo =  cepr01030102Form.getRightPartOfBusinessCd() - 14;    
                }
                else
                {
                	//businessNo = cepr01030102Form.getRightPartOfBusinessCd() + 1;	
                	businessNo = (cepr01030102Form.getRightPartOfBusinessCd()-10) + 1;	
                }
            	paramForPkRate.put("businessNo", businessNo );	
                paramForPkRate.put("currencyCd", cepr01030102Form.getCurrencyCd() );
                paramForPkRate.put("insuredAge", cepr01030101Form.getInsuredAge() );
                BigDecimal temp = eproposalManager.selectPkRateForDanaSejahtera( paramForPkRate );
                
                double maxBenefix = 0.0;
                if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                {
                    maxBenefix = 2000000000;
                }
                else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                {
                    maxBenefix = 200000;   
                }
                BigDecimal maxUP = new BigDecimal( MathUtil.min( LazyConverter.toDouble( halfTotalSumInsured ), maxBenefix ) );
                
                manfaatPenyakitKritisAmount = maxUP.multiply( temp ).multiply( CommonConst.PER_MIL );
                manfaatPenyakitKritisList.add( ManfaatDataSource.getManfaatPenyakitKritisParamsMap( command, editorUtil ) );
                params.put( "note", "Persyaratan underwriting sesuai dengan persyaratan pemeriksaan kesehatan asuransi jiwa individu." );
                
                mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("801"), new BigDecimal( businessNo ),
           			 BigDecimal.ZERO, BigDecimal.ZERO, temp, new BigDecimal(cepr01030102Form.getTermOfContract()), 
           			 new BigDecimal( cepr01030102Form.getPaymentModeCd() ),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getPayorsClauseFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getPayorsClauseFlagDisplay() ) )
            {
                additionalAssuranceExist = true;
                logger.info( "*-*-*-* Payors Clause Checked" );
                Cepr01040222Entry entry = new Cepr01040222Entry( eproposalManager, editorUtil, command, null );
                double temp = entry.of_get_rate( cepr01030102Form.getPaymentModeCd(), Hardcode.MANFAAT_PAYORS_CLAUSE );
                temp = LazyConverter.toDouble( cepr01030102Form.getPremium() ) * temp / 1000;
                manfaatPayorsClauseAmount = new BigDecimal( temp );
//                manfaatPayorsClauseAmount = entry.of_get_rate( cepr01030102Form.getPaymentModeCd(), Hardcode.MANFAAT_PAYORS_CLAUSE ); 
                manfaatPayorsClauseList.add( ManfaatDataSource.getManfaatPayorsClauseParamsMap() );
                
                mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal( Hardcode.MANFAAT_PAYORS_CLAUSE ), new BigDecimal( Hardcode.LSTAB_JENIS_PREMI ),
             			 BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal( temp ), new BigDecimal(cepr01030102Form.getTermOfContract()), 
             			 new BigDecimal( cepr01030102Form.getPaymentModeCd() ),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getWaiverPremiumDisabilityFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getWaiverPremiumDisabilityFlagDisplay() ) )
            {
                additionalAssuranceExist = true;
                logger.info( "*-*-*-* Waiver Premium Disability Checked" );
                Cepr01040222Entry entry = new Cepr01040222Entry( eproposalManager, editorUtil, command, null );
                
                BigDecimal temp = new BigDecimal ( entry.of_get_rate( cepr01030102Form.getPaymentModeCd(), Hardcode.MANFAAT_WAIVER_PREMIUM_DISABILITY ));
                manfaatWaiverPremiumAmount = cepr01030102Form.getPremium().multiply( temp ).multiply( CommonConst.PER_MIL );
//                manfaatWaiverPremiumAmount = entry.computeManfaat( cepr01030102Form.getPaymentModeCd(), Hardcode.MANFAAT_WAIVER_PREMIUM_DISABILITY );
                logger.info( "*-*-*-* manfaatWaiverPremiumAmount = " + manfaatWaiverPremiumAmount );
                manfaatWaiverPremiumDisabilityList.add( ManfaatDataSource.getManfaatWaiverPremiumDisabilityParamsMap() );
                mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal( Hardcode.MANFAAT_WAIVER_PREMIUM_DISABILITY ), new BigDecimal( Hardcode.LSTAB_JENIS_PREMI ),
            			 BigDecimal.ZERO, BigDecimal.ZERO, temp, new BigDecimal(cepr01030102Form.getTermOfContract()), 
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

            params.put( "beforeLastCashValue", editorUtil.convertToString( cashValueList.get( cashValueList.size() - 1 ) ) );
            params.put( "halfTotalSumInsured", editorUtil.convertToString( halfTotalSumInsured ) );
            params.put( "lastCashValue", editorUtil.convertToString( 0 ) );

            Map<String, String> labelMap = new HashMap<String, String>();
            labelMap.put( PremiumSummaryDataSource.MAP_KEY_PK_LABEL, "Premi PK" );
            
            premiumSummaryList = PremiumSummaryDataSource.getPremiumSummaryNoRoundParamsMapList(command, editorUtil, manfaatTermRiderAmount, manfaatPersonalAccidentAmount, manfaatPenyakitKritisAmount, manfaatPayorsClauseAmount, manfaatWaiverPremiumAmount, BigDecimal.ZERO, labelMap);
            params.put( "totalPremium1", editorUtil.convertToString( Cepr00000000MoneyUtil.sum( premiumSummaryList, "premium1" ) ) );
            params.put( "totalPremium2", editorUtil.convertToString( Cepr00000000MoneyUtil.sum( premiumSummaryList, "premium2" ) ) );
            params.put( "totalPremium3", editorUtil.convertToString( Cepr00000000MoneyUtil.sum( premiumSummaryList, "premium3" ) ) );
            params.put( "totalPremium4", editorUtil.convertToString( Cepr00000000MoneyUtil.sum( premiumSummaryList, "premium4" ) ) );

          //  params.put( "dataSource", detailVOList );
            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
            params.put( "validityMsg", "" );

            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( cepr01040222Business.getCommonHeaderRowList() ) );
          
            params.put( "dsManfaatHeader", JasperReportsUtils.convertReportData( manfaatHeaderList ) );
            params.put( "dsManfaatTermRider", JasperReportsUtils.convertReportData( manfaatTermRiderList ) );
            params.put( "dsManfaatPersonalAccident", JasperReportsUtils.convertReportData( manfaatPersonalAccidentList ) );
            params.put( "dsManfaatPenyakitKritis", JasperReportsUtils.convertReportData( manfaatPenyakitKritisList ) );
            params.put( "dsManfaatPayorsClause", JasperReportsUtils.convertReportData( manfaatPayorsClauseList ) );
            params.put( "dsManfaatWaiverPremiumDisability", JasperReportsUtils.convertReportData( manfaatWaiverPremiumDisabilityList ) );
            params.put( "dsPremiumSummary", JasperReportsUtils.convertReportData( premiumSummaryList ) );
           // params.put( "dsDetail", JasperReportsUtils.convertReportData( detailVOList ) );
            
            // adrian----
            List<OptionVO> detailVOList2 = new ArrayList<OptionVO>();
            Map<String, Object> lblMap = new HashMap<String, Object>();
            
            OptionVO optionVO = new OptionVO("Jaminan Rawat Inap & Rawat Jalan", "13,000,000" );
            detailVOList2.add(optionVO);
             optionVO = new OptionVO("Jaminan Tambahan Rawat Gigi", "13,000,000" );
            detailVOList2.add(optionVO);
            optionVO = new OptionVO("Jaminan Tambahan Rawat Bersalin", "13,000,000" );
            detailVOList2.add(optionVO);
            optionVO = new OptionVO("Jaminan Tambahan Penunjang Kesehatan", "13,000,000" );
            detailVOList2.add(optionVO);
           // params.put( "dsDetail", JasperReportsUtils.convertReportData( detailVOList2 ) );
          
            params.put( "dsDetail", JasperReportsUtils.convertReportData(cepr01040222Business.getByi()) );
            params.put("totalByi",cepr01040222Business.getTotalByi());
            
            params.put("standalone", "standalone");
            params.put( "dsCommonHeader2", JasperReportsUtils.convertReportData( cepr01040222Business.getCommonHeaderRowList() ));
            params.put( "dsOneRow", JasperReportsUtils.convertReportData( cepr01040222Business.getOneRowList() ) );
            putRiderParam( params, cepr01040222Business.getRiderMedicalPlusList(), "dsRiderMedicalPlus", "riderMedicalPlusIndex" );
            putRiderParam( params, cepr01040222Business.getRiderMedicalPlusRjList(), "dsRiderMedicalPlusRj", "riderMedicalPlusRjIndex" );
            putRiderParam( params, cepr01040222Business.getRiderMedicalPlusRgList(), "dsRiderMedicalPlusRg", "riderMedicalPlusRgIndex" );
            putRiderParam( params, cepr01040222Business.getRiderMedicalPlusRbList(), "dsRiderMedicalPlusRb", "riderMedicalPlusRbIndex" );
            putRiderParam( params, cepr01040222Business.getRiderMedicalPlusPkList(), "dsRiderMedicalPlusPk", "riderMedicalPlusPkIndex" );
            
            String 	labelCb = "*Tahunan"; 
    		if( LazyConverter.toInt( cepr01030102Form.getPaymentModeCd() ) == 1 ){  //triwulan
    				labelCb = "*Triwulanan";
    		}else if( LazyConverter.toInt( cepr01030102Form.getPaymentModeCd() ) == 2 ){ //semesteran
    				labelCb = "*Semesteran";
    		}else if( LazyConverter.toInt( cepr01030102Form.getPaymentModeCd() ) == 6 ){ //bulanan
    				labelCb = "*Bulanan";
    		}	   	     
    		params.put("labelCb", labelCb);  
    		
           params.put( "dsCommonHeaderRincianRiderMedicalPlus", JasperReportsUtils.convertReportData( cepr01040222Business.getCommonHeaderRincianSmallRowList() ) );
           //params.put( "dsCommonBiayaRincianRiderMedicalPlus", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040219Business.getCommonBiayaRincianRider(28) ) );  
           params.put( "dsRincianRiderMedicalPlus", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040222Business.getRiderMedicalPlusList() ));
           params.put( "dsRincianRiderMedicalPlusRj", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040222Business.getRiderMedicalPlusRjList() ));
           params.put( "dsRincianRiderMedicalPlusRg", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040222Business.getRiderMedicalPlusRgList() ));
           params.put( "dsRincianRiderMedicalPlusRb", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040222Business.getRiderMedicalPlusRbList() ));
           params.put( "dsRincianRiderMedicalPlusPk", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040222Business.getRiderMedicalPlusPkList() ));     
           params.put( "dsRincianRiderMedicalPlusParticipants", JasperReportsUtils.convertReportData( cepr01040222Business.getRincianRiderMedicalPlusParticipantsList()) );
           params.put( "dsOneRow2", JasperReportsUtils.convertReportData( cepr01040222Business.getOneRowList() ) );
           params.put( "title", "PROVESTARA SMART HEALTH" );
           
           params.put( "dataSource", cepr01040222Business.getPageList( currentIdx ) );
           params.put("jenis", cepr01030102Form.getRightPartOfBusinessCd()+"");
           
            //==========================================    		
    		// Save E-Proposal PDF ke EBServer dan PopUp Download
            // ** Adrian@06/01/2014
            String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
            String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
            Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();       
            String fileName = msag_id+"_"+no_proposal+".pdf";
            params.put( "no_proposal",  no_proposal );
            
            List temp = cepr01040222Business.getPageList( currentIdx );
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
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040222_product.jasper",
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
	            e.printStackTrace();
	        }   
           
            jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
                       
            request.getSession().setAttribute( "messageBoxList", null );
            result = new ModelAndView( jasperViewer, params );
            
            String productLabel = "SMiLe SUPER SEJAHTERA";
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