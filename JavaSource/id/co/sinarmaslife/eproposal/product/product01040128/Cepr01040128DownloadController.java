package id.co.sinarmaslife.eproposal.product.product01040128;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040128DownloadController
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

import id.co.sinarmaslife.eproposal.business.Cepr00000000CommonUsedBusiness;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.datasource.ManfaatDataSource;
import id.co.sinarmaslife.eproposal.common.datasource.PremiumSummaryDataSource;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000MoneyUtil;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.model.vo.Cepr01040128TableVO;
import id.co.sinarmaslife.eproposal.model.vo.StdTableDetailVO;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.standard.model.vo.LimitVO;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.MappingUtil;

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

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.jasperreports.AbstractJasperReportsView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cepr01040128DownloadController extends StandardParent implements Controller
{
	protected final Log logger = LogFactory.getLog( getClass() );
	
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {    	
        logger.info( "*-*-*-* Cepr01040128DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01040128Business cepr01040128Business = new Cepr01040128Business( eproposalManager, editorUtil, command );
        int[] tahapan= null;
        int periodeThp = 0;
        int payPer = 0;
        
        if( Cepr01040128Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
        	tahapan = new int[]{2,4,8,16,120};
        	periodeThp = 2;
        	payPer = 10;
        }
        else if( Cepr01040128Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
        	tahapan = new int[]{3,6,12,24,130};
        	periodeThp = 3;
        	payPer = 15;
        }
        else if( Cepr01040128Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() ||  Cepr01040128Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd())
        {
        	tahapan = new int[]{4,8,16,32,140};
        	periodeThp = 4;
        	payPer = 20;
        }

        ModelAndView result;
        if( request.getSession().getAttribute( "messageEkaSehatList" ) == null )
        {
        	List<String> messageEkaSehatList = cepr01040128Business.getEkaSehatMsgBox();
        	request.getSession().setAttribute( "messageEkaSehatList", messageEkaSehatList );
        	result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }

        
        if( request.getSession().getAttribute( "messageBoxList" ) == null )
        {
            List<String> messageBoxList = cepr01040128Business.getMedicalMsgBox();
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
            jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040128_product.jasper" );


            double accumulatedPremium = 0;
            double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );

            List<Integer> yearNoList;
            int age = cepr01030101Form.getInsuredAge();
            int lanjutanUmur = ( 99 - ( age + 20 ) ) + 20;
            int termOfPaymentParam;
            
            if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEKALIGUS )
            {
            	termOfPaymentParam = 1;
            }
            else
            {
            	termOfPaymentParam = cepr01030102Form.getTermOfPayment();
            }
            
            LimitVO[] limitVOArr = new LimitVO[]{ new LimitVO( 1, 20 ), new LimitVO( lanjutanUmur, 99 ) };
            yearNoList = ArrUtil.genPartitionsIntList( limitVOArr );

            Map<String, Object> rateParams = new HashMap<String, Object>();
            rateParams.put( "leftPartOfBusinessCd", new Integer( "179" ) );
            rateParams.put( "lstabLamaTanggung", cepr01030102Form.getTermOfContract() );
            rateParams.put( "lstabLamaBayar", cepr01030102Form.getTermOfPayment() );
            rateParams.put( "lsdbsNumber", cepr01030102Form.getRightPartOfBusinessCd() );
            rateParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_TUNAI );
            rateParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
            rateParams.put( "paymentModeCd", cepr01030102Form.getPaymentModeCd() );
            rateParams.put( "termOfPayment", termOfPaymentParam );
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

            List< Cepr01040128TableVO > detailVOList = new ArrayList< Cepr01040128TableVO >();
            Cepr01040128TableVO detailVO;
            
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
            String benefitStr = editorUtil.convertToString( cepr01030102Form.getTotalSumInsured() );
            int yearNo;
            int tempCount = 0;
            BigDecimal totalTahapanBonus = BigDecimal.ZERO;
            BigDecimal investasi = BigDecimal.ZERO;
            String investasiStr = null;
            BigDecimal benefitAkmTahapan = BigDecimal.ZERO;
            String benefitAkmTahapanStr = null;
            BigDecimal benefitTahapan = BigDecimal.ZERO;
            String benefitTahapanStr = null;
            String danaHariTua = null;
            BigDecimal cashValue2 = BigDecimal.ZERO;
            String cashValue2Str = null;
            double totalPremi = 0;
            for( int i = 0; i <= ( 99 - age ); i++ )
            {
                yearNo = yearNoList.get( i );
                yearNoStr = editorUtil.convertToString( yearNo );
                age = yearNo + cepr01030101Form.getInsuredAge();
                ageStr = editorUtil.convertToString( age );
                BigDecimal tahapanBonus = BigDecimal.ZERO;
                String tahapanBonusStr = null;
                if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEKALIGUS )
                {
                	if( yearNo == 1 )
                	{
                		accumulatedPremium = premium; 
                		totalPremi = accumulatedPremium;
                	}
                	else 
                	{
                		accumulatedPremium = 0;
                	}
                }
                else
                {
                    if( i < payPer)
                    {
                    	accumulatedPremium = premium;
                    	totalPremi = accumulatedPremium * payPer;
                    }
                    else
                    {
                    	accumulatedPremium = 0;
                    }
                }
                if( i < payPer)
                {
                	if( yearNo % periodeThp == 0 )
                	{
                		tahapanBonus = ( cepr01030102Form.getTotalSumInsured().multiply( new BigDecimal( tahapan[tempCount] ) ) ).divide( new BigDecimal("100")) ;
                		tempCount ++;
                		totalTahapanBonus = totalTahapanBonus.add( tahapanBonus );
                		tahapanBonusStr = editorUtil.convertToString( tahapanBonus );
                	}
                	else
                	{
                		
                	}
                }
                else
                {
                	
                }
                
                if( i < 20 )
                {
                	if( !BigDecimal.ZERO.equals( investasi ) && Double.parseDouble( investasi.toString()) > 0 )
                	{
                		if( BigDecimal.ZERO.equals( tahapanBonus ) || Double.parseDouble( tahapanBonus.toString()) == 0  )
                		{
                			investasi = investasi.add( investasi.multiply( cepr01030102Form.getInvestRateInPercent().divide( new BigDecimal("100") ) ) ) ;	
                		}
                		else
                		{
                			investasi = tahapanBonus.add( investasi.add( investasi.multiply( cepr01030102Form.getInvestRateInPercent().divide( new BigDecimal("100") ) ) ) );
                		}
                	}
                	else
                	{
                    	investasi = tahapanBonus;
                	}
                	
                	benefitTahapan = totalTahapanBonus.add( cashValueList.get( i ) ) ;
                	cashValue2 = cashValueList.get(i).add( tahapanBonus );
                }
                else
                {
                	investasi = BigDecimal.ZERO;
                	benefitTahapan = BigDecimal.ZERO;
                	cashValue2 = BigDecimal.ZERO;
                }
                
                benefitAkmTahapan = investasi.add( cashValueList.get( i ) ); // perhitungan total benefit + akumulasi tahapan
                
                if( accumulatedPremium != 0 && accumulatedPremium > 0 ){ accumulatedStr = editorUtil.convertToString( accumulatedPremium ); }
                else{ accumulatedStr = null; }
                
            	if( cashValueList.get( i ) != null && !BigDecimal.ZERO.equals( cashValueList.get( i ) ) && Double.parseDouble( cashValueList.get( i ).toString()) > 0 ){ cashValueStr = editorUtil.convertToString( cashValueList.get( i ) ); }
            	else{ cashValueStr = null; }
                
            	if( benefitTahapan != null && !BigDecimal.ZERO.equals( benefitTahapan ) && Double.parseDouble( benefitTahapan.toString()) > 0 ){ benefitTahapanStr = editorUtil.convertToString( benefitTahapan ); }
            	else{ benefitTahapanStr = null; }
            	
            	if( investasi != null && !BigDecimal.ZERO.equals( investasi ) && Double.parseDouble( investasi.toString()) > 0 ){ investasiStr = editorUtil.convertToString( investasi ); }
            	else{ investasiStr = null; }
            	
            	if( benefitAkmTahapan != null && !BigDecimal.ZERO.equals( benefitAkmTahapan ) && Double.parseDouble( benefitAkmTahapan.toString()) > 0 ){ benefitAkmTahapanStr = editorUtil.convertToString( benefitAkmTahapan ); }
            	else{ benefitAkmTahapanStr = null; }
            	
            	if( cashValue2 != null && !BigDecimal.ZERO.equals( cashValue2 ) && Double.parseDouble( cashValue2.toString()) > 0 ){ cashValue2Str = editorUtil.convertToString( cashValue2 ); }
            	else{ cashValue2Str = null; }
            	
            	if( age == 99  )
            	{
            		danaHariTua = editorUtil.convertToString( cepr01030102Form.getTotalSumInsured() );
            	}
                detailVO = new Cepr01040128TableVO( yearNoStr, ageStr, accumulatedStr, cashValueStr, 
                		benefitStr, tahapanBonusStr, benefitTahapanStr, 
                		investasiStr, benefitAkmTahapanStr, danaHariTua, cashValue2Str );
                detailVOList.add( detailVO ) ;
            }

            Integer lastAge = 99;

            String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
            BigDecimal totalSumInsured = cepr01030102Form.getTotalSumInsured();
            params.put( "investPersen", editorUtil.convertToString( cepr01030102Form.getInvestRateInPercent() ) + "%" );
            params.put( "totalPremi", editorUtil.convertToString( totalPremi ) );
            params.put( "totalTahapanBonus", editorUtil.convertToString( totalTahapanBonus ) );
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
            params.put( "lastAge", editorUtil.convertToString( lastAge ) );
            params.put( "lastYearNo", editorUtil.convertToString( lastAge - cepr01030101Form.getInsuredAge() ) );
            params.put( "paymentModeCd", cepr01030102Form.getPaymentModeCd() );
            params.putAll( getDetailMpp( cepr01030102Form ) );
            
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

            // this will generate header for additional assurance
            List<Map<String, Object>> manfaatHeaderList = new ArrayList<Map<String, Object>>();
            if( additionalAssuranceExist )
            {
                Map<String, Object> manfaatHeaderMap = new HashMap<String, Object>();
                manfaatHeaderMap.put( "title", "MANFAAT PROGRAM ASURANSI TAMBAHAN" );
                manfaatHeaderList.add( manfaatHeaderMap );
            }

            premiumSummaryList = PremiumSummaryDataSource.getPremiumSummaryParamsMapList( command, editorUtil, manfaatTermRiderAmount, manfaatPersonalAccidentAmount, manfaatPenyakitKritisAmount, manfaatPayorsClauseAmount, manfaatWaiverPremiumAmount, BigDecimal.ZERO, BigDecimal.ZERO );
            params.put( "totalPremium1", editorUtil.convertToRoundedTwoDigits( Cepr00000000MoneyUtil.sumList( premiumSummaryList, "premium1" ) ) );
            params.put( "totalPremium2", editorUtil.convertToRoundedTwoDigits( Cepr00000000MoneyUtil.sumList( premiumSummaryList, "premium2" ) ) );
            params.put( "totalPremium3", editorUtil.convertToRoundedTwoDigits( Cepr00000000MoneyUtil.sumList( premiumSummaryList, "premium3" ) ) );

            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
            params.put( "validityMsg", "" );           
            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( cepr01040128Business.getHeaderRowList( command ) ) );
            params.put( "dsCommonHeaderPage2", JasperReportsUtils.convertReportData( cepr01040128Business.getHeaderRowList( command ) ) );
            params.put( "dsManfaatHeader", JasperReportsUtils.convertReportData( manfaatHeaderList ) );
            params.put( "dsManfaatPayorsClause", JasperReportsUtils.convertReportData( manfaatPayorsClauseList ) );
            params.put( "dsManfaatWaiverPremiumDisability", JasperReportsUtils.convertReportData( manfaatWaiverPremiumDisabilityList ) );
            params.put( "dsPremiumSummary", JasperReportsUtils.convertReportData( premiumSummaryList ) );
            
            params.put( "dsManfaatDetail", JasperReportsUtils.convertReportData( detailVOList ) );

            // Fill all values to detail row
            jasperViewer.setReportDataKey( "dataSource" );
            params.put( "dataSource", getPageList( cepr01030102Form ) );

            //==========================================    		
    		// Save E-Proposal PDF ke EBServer dan PopUp Download
            // ** Adrian@06/01/2014
            String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
            String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
            Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();       
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
			            JasperUtils.exportReportToPdf(
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040128_product.jasper",
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
        logger.info( "*-*-*-* Cepr01040128DownloadController.getPageList" );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub1" );
        result.add( params );

        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub2" );
        result.add( params );
        
        return result;
    }
    
    public Map<String, Object > getDetailMpp ( Cepr01030102Form cepr01030102Form ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040128DownloadController.getPageList" );
        Map<String, Object> params;
        String[] akhirTahunKe = null;
        String[] tahapanBonus = null; 
      
        
        if( Cepr01040128Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
        	akhirTahunKe = new String[]{ "2", "4", "6", "8", "10" };
        	tahapanBonus = new String[]{ "2% UP", "4% UP", "8% UP", "16% UP", "100% UP + Bonus 20% UP" };
        }
        else if( Cepr01040128Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
        	akhirTahunKe = new String[]{ "3", "6", "9", "12", "15" };
        	tahapanBonus = new String[]{ "3% UP", "6% UP", "12% UP", "24% UP", "100% UP + Bonus 30% UP" };
        }
        else if( Cepr01040128Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040128Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
        	akhirTahunKe = new String[]{ "4", "8", "12", "16", "20" };
        	tahapanBonus = new String[]{ "4% UP", "8% UP", "16% UP", "32% UP", "100% UP + Bonus 40% UP" };
        }
        
        params = new HashMap<String, Object>();
        params.put("akhirTahunKe1", akhirTahunKe[0]);
        params.put("akhirTahunKe2", akhirTahunKe[1]);
        params.put("akhirTahunKe3", akhirTahunKe[2]);
        params.put("akhirTahunKe4", akhirTahunKe[3]);
        params.put("akhirTahunKe5", akhirTahunKe[4]);
        
        params.put("tahapanBonusKe1", tahapanBonus[0]);
        params.put("tahapanBonusKe2", tahapanBonus[1]);
        params.put("tahapanBonusKe3", tahapanBonus[2]);
        params.put("tahapanBonusKe4", tahapanBonus[3]);
        params.put("tahapanBonusKe5", tahapanBonus[4]);
        
        return params;
    }
    

}
