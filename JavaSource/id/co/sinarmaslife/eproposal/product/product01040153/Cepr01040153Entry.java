package id.co.sinarmaslife.eproposal.product.product01040153;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040139
 * Program Name   		: Cepr01040139Entry
 * Description         	: Pro safe (192)
 * Environment      	: Java  1.5.0_06
 * Author               : fadly m
 * Version              : 1.0
 * Creation Date    	: Jan 01, 2012 9:18:21 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000EntryInterface;
import id.co.sinarmaslife.eproposal.product.product01040153.Cepr01040153Mapper;
import id.co.sinarmaslife.eproposal.common.lookup.LookupList;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000Generator;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Entry;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.DateUtil;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040153Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040153Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040153Entry constructor is called ..." );
        setDownloadUrl( "wepr01040153.pdf" );
    }

    public void initDisplayedForm()
    {
        
        displayStandardForm();
        cepr01030102Form.setButtonCountPremiumDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPremiumDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setTotalSumInsuredListDisplay( CommonConst.DISPLAY_TRUE );
        
        cepr01030102Form.setButtonCountTotalSumInsuredDisplay( CommonConst.DISPLAY_TRUE );
        displayAdditionalAssurance( true );
        cepr01030102Form.setEkaSehatFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setTpdFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPayorsClauseFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPersonalAccidentFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setCriticalIllnessFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setWaiverPremiumDisabilityFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setTermRiderFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPremiumListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumFurloughYearDisplay( CommonConst.DISPLAY_TRUE );
     // setPremiumFurloughYearListDisplayHelperDisplay set to true if to display only list (not text box) 
        cepr01030102Form.setPremiumFurloughYearListDisplayHelperDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumFurloughYearListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setTermOfContractDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setTermOfPaymentDisplay( CommonConst.DISPLAY_FALSE );
    //    cepr01030102Form.setMedicalPlusDisplay(CommonConst.DISPLAY_FALSE);
    //    cepr01030102Form.setBabyFlag(CommonConst.CHECKED_FALSE);
       
        if(cepr01030101Form.getBabyFlag()!=CommonConst.CHECKED_FALSE){
        	if(cepr01030101Form.getBabyFlag().equalsIgnoreCase(CommonConst.CHECKED_TRUE)){
        		 cepr01030102Form.setBabyFlag(CommonConst.CHECKED_TRUE);
        		 
        	}
        	
        }else{
        	 displayAdditionalAssurance( false );
    	}
        
        
        
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040139Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
       
        	cepr01030102Form.setBabyFlagIsDisabled( CommonConst.DISABLED_TRUE );
       
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040139Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040139Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        Integer[] payModeArr = null;
        Integer termOfPayment = cepr01030102Form.getPremiumFurloughYearCd();
        payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_BULANAN, Hardcode.PAY_MODE_CD_SEMESTERAN, Hardcode.PAY_MODE_CD_TRIWULANAN};
             
        List<OptionVO> premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 100000000, 900000000, 10000000 );
        cepr01030102Form.setTotalSumInsuredList( premiumList );
        cepr01030102Form.setTermOfPayment( termOfPayment );
        cepr01030102Form.setPaymentModeCd( payModeArr[ 0 ] );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, payModeArr ) );
//        cepr01030102Form.setPremiumFurloughYearList( LookupList.getSequenceNumberList( 6, 6, 1, false ) );
//        cepr01030102Form.setPremiumFurloughYearCd(6);
  	  	cepr01030102Form.setPremiumFurloughYear( 6 );
  	  	cepr01030102Form.setPremiumFurloughYearList( LookupList.getSequenceNumberList( 6, 6, 6, false ) );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040139Entry.fillDefaultValueEachTimeFormCalled" );
        
        Integer termOfContract = 12;	
     
        cepr01030102Form.setTermOfContract( termOfContract );
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        cepr01030102Form.setTermRiderUnitQtyList( LookupList.getSequenceNumberList( 1, 5, false ) );
        cepr01030102Form.setPersonalAccidentUnitQtyList( LookupList.getSequenceNumberList( 1, 3, false ) );
        
        cepr01030102Form.setTotalSumInsuredOptionIsToRefreshPage("true");
        
        List<OptionVO> premiumList;
       
        if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TAHUNAN ){
        	premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 1200000, 28800000, 1200000 );
        }else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEMESTERAN ){
        	premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 600000, 14400000, 600000 );
        }else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TRIWULANAN ){
        	premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 300000, 7200000, 300000 );
        }else {
        	premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 100000, 2400000, 100000 );
        }
        
        cepr01030102Form.setPremiumList( premiumList );
        
        cepr01030102Form.setTermOfPayment(cepr01030102Form.getPremiumFurloughYear());
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040139Entry.validateCurrentPage" );
     
        if( cepr01030102Form.getPremium() != null )
        {
            double totalSumInsured = LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() );
            double minimumTotalSumInsuredIdr = 100000000.0;
            double maksimumTotalSumInsuredIdr = 1200000000.0;
            
            commonValidator.validateMinimumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, totalSumInsured, minimumTotalSumInsuredIdr, -1 );
            
            if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_BULANAN ){
            	commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(100000), new BigDecimal(10));
//            	commonValidator.validateMaximumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(2400000), new BigDecimal(240));
            }else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TAHUNAN ){
            	commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(1000000), new BigDecimal(100));
//            	commonValidator.validateMaximumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(28800000), new BigDecimal(2880));            	
            }else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEMESTERAN ){
            	commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(525000), new BigDecimal(52.5));
//            	commonValidator.validateMaximumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(14400000), new BigDecimal(1440));            	
            }else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TRIWULANAN ){
            	commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(275000), new BigDecimal(27.5));
//            	commonValidator.validateMaximumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(7200000), new BigDecimal(720));            	
            }
            
            commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, totalSumInsured, maksimumTotalSumInsuredIdr, -1 );
        }
        else
        {
            errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED, "error.formEmpty", null, null );
        }
        
        if( cepr01030102Form.getPremium() == null || cepr01030102Form.getPremium().equals(new BigDecimal(0)) )
        {
        	 errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.formEmpty", null, null );
        }
    }

    public void validatePreviousPage()
    {
        commonValidator.validatePolicyHolderAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 85 );
           
        String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay()); 
        if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<30)
		{
			errors.rejectValue( Cepr01030102FormConst.ASSURANCE_PLAN_CD, "error.insuredAgeMinDay", new Object[]{ Integer.toString( 30 )  }, null );
		}else if( cepr01030101Form.getInsuredAge() == 0 && DateUtil.dateDifference(cepr01030101Form.getInsuredBirthDay(), eproposalManager.selectNowDate(), true) > 180 ){
			// menurut R@ndy 180 hari keatas 1 thn (dlm 6 bln)
			cepr01030101Form.setInsuredAge(1);
		}
        
        if( cepr01030101Form.getInsuredAge() > 50 )
        {
            errors.rejectValue( Cepr01030102FormConst.ASSURANCE_PLAN_CD, "error.insuredAgeMax", new Object[]{ Integer.toString( 50 ) }, null );
        } 
//        if( cepr01030102Form.getPremiumFurloughYear() + cepr01030101Form.getInsuredAge() > 60 ){
//        	   errors.rejectValue( Cepr01030102FormConst.ASSURANCE_PLAN_CD, "error.maxPremiumFurloughYear", null, null );
//        }
        
        
    }

    public BigDecimal computePremium( Integer payModeCd )
    {
    	logger.info( "*-*-*-* Cepr01040104Entry.computePremium" );

        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put( "leftPartOfBusinessCd", cepr01030102Form.getLeftPartOfBusinessCd() );
        sqlParams.put( "lsdbsNumber", cepr01030102Form.getRightPartOfBusinessCd() );
        sqlParams.put( "lstabLamaTanggung", cepr01030102Form.getTermOfContract() );
        sqlParams.put( "lstabLamaBayar", cepr01030102Form.getTermOfPayment() );
        sqlParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_PREMI );
        sqlParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
        sqlParams.put( "paymentModeCd", payModeCd );
        sqlParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
        sqlParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
        sqlParams.put( "yearNoList", new Integer[]{ new Integer( "1" ) } );
        logger.info( "*-*-*-* sqlParams = " + sqlParams );
        List rateList = eproposalManager.selectRateNew( sqlParams );
        BigDecimal premium;
        BigDecimal rate;
        if( rateList != null && rateList.size() > 0 )
        {
            rate = eproposalManager.selectRateNew( sqlParams ).get( 0 );
			premium = computePremiumWithSimpleMethod( cepr01030102Form.getTotalSumInsured(), rate, new BigDecimal( "1" ) );
            
            if( cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_IDR_CD ) ){
	            BigDecimal amount =premium.setScale( 2, RoundingMode.HALF_EVEN);
	            DecimalFormat decimalFormat = new DecimalFormat( "##.00" );
	            String temp = decimalFormat.format( amount );
	         
	            BigDecimal prm = new BigDecimal( temp );
	            premium = prm;
            }
        }
        else
        {
            premium = new BigDecimal( "0" );
        }

        return premium;
    }


    public void processFormAfterSubmitBeforeValidation()
    {        
    	logger.info( "*-*-*-* Cepr01040139Entry.processFormAfterSubmitBeforeValidation" );
    	if(cepr01030102Form.getTotalSumInsured() != null){
    		cepr01030102Form.setPremium( computePremium( cepr01030102Form.getPaymentModeCd() ) );
    	}
    }        
    
   
}
