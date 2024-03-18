package id.co.sinarmaslife.eproposal.product.product01040218;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040211X1Entry
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : fadly
 * Version              : 1.0
 * Creation Date    	: Feb 10, 2009 2:49:55 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.lookup.LookupList;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;

public class Cepr01040218X3Entry extends Cepr01040218Entry
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040218X3Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040211X9Entry constructor is called ..." );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040211X9Entry.initDisplayedForm" );
        super.initDisplayedForm();
        
        cepr01030102Form.setPremiumFurloughYearDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPremiumFurloughYearListDisplay( CommonConst.DISPLAY_FALSE );
        
        if( Cepr01040218Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd())        	
        {
	        cepr01030103Form.setWaiverCiDisplay( CommonConst.DISPLAY_FALSE );
	        cepr01030103Form.setWaiverTpdDisplay( CommonConst.DISPLAY_FALSE );
	        cepr01030103Form.setPayorCiDeathDisplay( CommonConst.DISPLAY_FALSE );
	        cepr01030103Form.setPayorCiDisplay( CommonConst.DISPLAY_FALSE );
	        cepr01030103Form.setPayorSpouseTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
	        cepr01030103Form.setPayorTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
	        //cepr01030103Form.setTermRiderDisplay( CommonConst.DISPLAY_FALSE );
        }
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040211X9Entry.initDisabledForm" );
        super.initDisabledForm();
        cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumFurloughYearIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040211X9Entry.initReadOnlyForm" );
        super.initReadOnlyForm();
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040211X9Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumFurloughYear( 1 );
        cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_SEKALIGUS );
        
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040211X9Entry.fillDefaultValueEachTimeFormCalled" );
        super.fillDefaultValueEachTimeFormCalled();
        
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_SEKALIGUS } ) );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTermOfPayment( 1 );
        cepr01030102Form.setTermOfContract( 100 - cepr01030101Form.getInsuredAge() );
        
        
        //cepr01030102Form.setPremiumCombinationCd( new BigDecimal( "100" ) );
        cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombinationFoldedList( 100 ) );
        //cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination10FoldedList().subList( 1, 2 ) );
        refreshBaseTopupPremium();
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040211X9Entry.validateCurrentPage" );
//        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
	//        double minPremiumIdr = 4000000;
	//        double minPremiumUsd = 400;
	//        commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double minPremiumIdr;
        double minPremiumUsd;
        double li_kali = 1;
        if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TRIWULANAN )      //triwulan
            li_kali = 4;
        else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEMESTERAN ) //semesteran
            li_kali = 2;
        else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_BULANAN ) //bulanan
            li_kali = 12;
        
        switch( cepr01030102Form.getPaymentModeCd() )
        {
        case Hardcode.PAY_MODE_CD_SEKALIGUS:
            minPremiumIdr = 10000000;
            minPremiumUsd = 1000;
            break;
        case Hardcode.PAY_MODE_CD_TAHUNAN:
            minPremiumIdr = 5000000;
            minPremiumUsd = 500;
            break;
        case Hardcode.PAY_MODE_CD_SEMESTERAN:
        	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040218Mapper.X6){ 
	        	minPremiumIdr = 2625000;
	        	minPremiumUsd = 262.5;
	        }else{
	        	minPremiumIdr = 2500000;
	        	minPremiumUsd = 250;
	        }
            break;
        case Hardcode.PAY_MODE_CD_TRIWULANAN:
        	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040218Mapper.X6){ 
        		minPremiumIdr = 1350000;
        		minPremiumUsd = 135;             		
        	}else{
        		minPremiumIdr = 1250000;
            	minPremiumUsd = 125;
        	}
            break;
        case Hardcode.PAY_MODE_CD_BULANAN:
            minPremiumIdr = 500000;
            minPremiumUsd = 50;
            break;
        default:
    	throw new RuntimeException( "*-*-*-* cepr01030102Form.getPaymentModeCd() not listed in Cepr01040201Entry.validateCurrentPage" );
    }


        if( cepr01030102Form.getPremium() != null )
        {
        	
       	 if( cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040218Mapper.X6 ){
        	double factor = 0;
        	if( cepr01030101Form.getInsuredAge() >= 1 && cepr01030101Form.getInsuredAge() <= 19 )factor = 50;
        	else if( cepr01030101Form.getInsuredAge() >= 20 && cepr01030101Form.getInsuredAge() <= 29 )factor = 40;
        	else if( cepr01030101Form.getInsuredAge() >= 30 && cepr01030101Form.getInsuredAge() <= 39 )factor = 30;
        	else if( cepr01030101Form.getInsuredAge() >= 40 && cepr01030101Form.getInsuredAge() <= 49 )factor = 20;
        	else if( cepr01030101Form.getInsuredAge() >= 50 && cepr01030101Form.getInsuredAge() <= 70 )factor = 10;
        	double maxUp = LazyConverter.toDouble( cepr01030102Form.getBasePremium() ) * li_kali * factor; 
        	commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() ), maxUp, maxUp );
        	commonValidator.validateMinimumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() ), 7500000, 750 );
       	 }
       	 else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040218Mapper.X6 ){
        	 //untuk perhitungan max up============================================
	        int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );
	        
	       
	    	double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
	    	double adec_premi = LazyConverter.toDouble( cepr01030102Form.getPremium() );
	        adec_premi = adec_premi * 1.25; 
	     
	        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
	        {
	            //idec_up = Math.max( idec_up, 15000000 );
	        	adec_premi = FormatNumber.round(adec_premi/1000,0);
	        	adec_premi = adec_premi * 1000;
	        }
	        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
	        { 
	            //idec_up = Math.max( idec_up, 1500 );
	        }
	        double idec_rate = 1000;
	        double idec_up = adec_premi * idec_rate / 1000;
	        // count totalSumInsured with premiumCombinationPercent
	       
	        double maxUp	= idec_up;
	        //=====================================================================
	    	       	
	//    	double minUp	= ( LazyConverter.toDouble( cepr01030102Form.getBasePremium() ) / factor_percentage ) * 5;
	        
	    	 if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
	         {
	    		 if(maxUp>15000000){
	    			 maxUp = maxUp;
	        	 }else{
	        		 maxUp = 15000000;
	        	 }  
	         }
	         else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
	         { 
	        	 if(maxUp>1500){
	        		 maxUp = maxUp;
	        	 }else{
	        		 maxUp = 1500;
	        	 }
	         }
	    	        	
	    	 commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() ), maxUp, maxUp );
       	 }
        	
       	    commonValidator.validateMinimumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() ), 7500000, 750 );
            commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );
            commonValidator.validateInvestmentSumIs100Percent();
        }
        else
        {
            errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.formEmpty", null, null );
        }
        super.validateCurrentPage();
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040211X9Entry.processFormAfterSubmitBeforeValidation" );
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
        refreshBaseTopupPremium();

        if( "onChangePremium".equals( cepr01030102Form.getTheEvent() )
            || "onPressButtonCountTotalSumInsured".equals( cepr01030102Form.getTheEvent() )
            || cepr01030102Form.getTotalSumInsured().equals( new BigDecimal( "0" ) ) 
            || "onPressButtonPreviewProposal".equals( cepr01030102Form.getTheEvent() ) )
        {
            double idec_rate;
            double adec_premi = premium;
            double up = 0.0;
            double idec_up = LazyConverter.toDouble( cepr01030102Form.getPremium() );
            idec_up = idec_up * 1.25;
            // count totalSumInsured with premiumCombinationPercent
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	if(idec_up <= 15000000){
                	idec_up = 15000000;
                }
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	if(idec_up <= 1500){
                	idec_up = 1500;
                }
            }
            
            cepr01030102Form.setTotalSumInsured( new BigDecimal( idec_up ) );
            cepr01030102Form.setTotalSumInsuredFix(new BigDecimal( idec_up ) );
        }
    }
    
    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040213Entry.validatePreviousPage" );
        super.validatePreviousPage();
    }
}