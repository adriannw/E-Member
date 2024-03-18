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
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cepr01040218X2Entry extends Cepr01040218Entry
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040218X2Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040211X8Entry constructor is called ..." );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040211X8Entry.initDisplayedForm" );
        super.initDisplayedForm();
        
        cepr01030102Form.setPremiumFurloughYearDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPremiumFurloughYearListDisplay( CommonConst.DISPLAY_FALSE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040211X8Entry.initDisabledForm" );
        super.initDisabledForm();
        cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPremiumFurloughYearIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040211X8Entry.initReadOnlyForm" );
        super.initReadOnlyForm();
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
    	super.fillDefaultValueEachTimeRightPartOfBusinessCdChanged();
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPremiumFurloughYear( 1 );
        cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_TAHUNAN );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040211X8Entry.fillDefaultValueEachTimeFormCalled" );
        super.fillDefaultValueEachTimeFormCalled();
        
      
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
       
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_SEMESTERAN, Hardcode.PAY_MODE_CD_TRIWULANAN, Hardcode.PAY_MODE_CD_BULANAN } ) );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTermOfPayment( 10 );
        cepr01030102Form.setTermOfContract( 100 - cepr01030101Form.getInsuredAge() );
        
        //cepr01030102Form.setPremiumCombinationCd( new BigDecimal( "100" ) );
//        cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombinationFoldedList( 20 ) );
        //cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination10FoldedList().subList( 1, 2 ) );
        refreshBaseTopupPremium();
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040211X8Entry.validateCurrentPage" );
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

        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040218Mapper.X5 ){
	        //untuk perhitungan min up============================================
	        int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );
	        
	       
	    	double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
	    	double adec_premi = LazyConverter.toDouble( cepr01030102Form.getPremium() );
	    	
	    	/*
	        switch( paymentModeCd )
	        {
	        case Hardcode.PAY_MODE_CD_TRIWULANAN: adec_premi = (adec_premi /2) / 0.270 *10;  break;
	        case Hardcode.PAY_MODE_CD_SEMESTERAN: adec_premi = (adec_premi /2) / 0.525 *10;  break;
	        case Hardcode.PAY_MODE_CD_BULANAN   : adec_premi = adec_premi *10 * 5; break;
	        case Hardcode.PAY_MODE_CD_SEKALIGUS : adec_premi = adec_premi * 5; break;
	        case Hardcode.PAY_MODE_CD_TAHUNAN   : adec_premi = adec_premi * 5; break;
	            default: break;
	        }*/
	    	//REQ : Mayda (03/07/2014) -- SIMPOL Syariah
	    	switch( paymentModeCd )
		    {
		       case Hardcode.PAY_MODE_CD_TRIWULANAN: adec_premi = adec_premi *li_kali* 5;  break;
		       case Hardcode.PAY_MODE_CD_SEMESTERAN: adec_premi = adec_premi *li_kali* 5;  break;
		       case Hardcode.PAY_MODE_CD_BULANAN   : adec_premi = adec_premi *li_kali* 5; break;
		       case Hardcode.PAY_MODE_CD_SEKALIGUS : adec_premi = adec_premi * 5; break;
		       case Hardcode.PAY_MODE_CD_TAHUNAN   : adec_premi = adec_premi * 5; break;
		         default: break;
		    }
	    	
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
	        idec_up = idec_up * premiumCombinationPercent / 100;
	        double minUp	= idec_up;
	        //=====================================================================
	    	       	
	//    	double minUp	= ( LazyConverter.toDouble( cepr01030102Form.getBasePremium() ) / factor_percentage ) * 5;
	        
	    	 if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
	         {
	    		 if(minUp>7500000){
	        		 minUp = minUp;
	        	 }else{
	        		 minUp = 7500000;
	        	 }  
	         }
	         else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
	         { 
	        	 if(minUp>750){
	        		 minUp = minUp;
	        	 }else{
	        		 minUp = 750;
	        	 }
	         }
	    	        	
	       	commonValidator.validateMinimumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() ), minUp, minUp );
        }else{
        	commonValidator.validateMinimumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() ), 7500000, 750 );
        }
        switch( cepr01030102Form.getPaymentModeCd() )
        {
	        case Hardcode.PAY_MODE_CD_SEKALIGUS:
	            minPremiumIdr = 10000000;
	            minPremiumUsd = 1000;
	            break;
	        case Hardcode.PAY_MODE_CD_TAHUNAN:
	        	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040218Mapper.X8){
	        		minPremiumIdr = 1200000;
	        		minPremiumUsd = 120;        		
	        	}else{
	        		minPremiumIdr = 5000000;
	        		minPremiumUsd = 500;
	        	} 
	        	break;
	        case Hardcode.PAY_MODE_CD_SEMESTERAN:
	          if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040218Mapper.X5){
	        		minPremiumIdr = 2625000;
	        		minPremiumUsd = 262.5;
	          }else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040218Mapper.X8){
	        		minPremiumIdr = 600000;
	        		minPremiumUsd = 60;
	          }else{
	            minPremiumIdr = 2500000;
	            minPremiumUsd = 250;
	          }
	          break;
	        case Hardcode.PAY_MODE_CD_TRIWULANAN:
	        	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040218Mapper.X5){ 
	        		minPremiumIdr = 1350000;
	        		minPremiumUsd = 135;             		
	        	}else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040218Mapper.X8){
	        		minPremiumIdr = 300000;
	        		minPremiumUsd = 30;        		
	        	}else{
	        		minPremiumIdr = 1250000;
	        		minPremiumUsd = 125;
	        	}
	             break;
	        case Hardcode.PAY_MODE_CD_BULANAN:
	        	 if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040218Mapper.X8){
	          		minPremiumIdr = 100000;
	          		minPremiumUsd = 10;        		
	          	}else{
	          		minPremiumIdr = 500000;
	          		minPremiumUsd = 50;
	          	}
	          	break;
	        default:
        	throw new RuntimeException( "*-*-*-* cepr01030102Form.getPaymentModeCd() not listed in Cepr01040201Entry.validateCurrentPage" );
        }


        if( cepr01030102Form.getPremium() != null )
        {
        	double factor = 0;
        	if     ( cepr01030101Form.getInsuredAge() >= 1  && cepr01030101Form.getInsuredAge() <= 15 )factor = 220;
        	else if( cepr01030101Form.getInsuredAge() >= 16 && cepr01030101Form.getInsuredAge() <= 20 )factor = 240;
        	else if( cepr01030101Form.getInsuredAge() >= 21 && cepr01030101Form.getInsuredAge() <= 25 )factor = 200;
        	else if( cepr01030101Form.getInsuredAge() >= 26 && cepr01030101Form.getInsuredAge() <= 30 )factor = 180;
        	else if( cepr01030101Form.getInsuredAge() >= 31 && cepr01030101Form.getInsuredAge() <= 35 )factor = 160;
        	else if( cepr01030101Form.getInsuredAge() >= 36 && cepr01030101Form.getInsuredAge() <= 40 )factor = 150;
        	else if( cepr01030101Form.getInsuredAge() >= 41 && cepr01030101Form.getInsuredAge() <= 45 )factor = 140;
        	else if( cepr01030101Form.getInsuredAge() >= 46 && cepr01030101Form.getInsuredAge() <= 50 )factor = 100;
        	else if( cepr01030101Form.getInsuredAge() >= 51 && cepr01030101Form.getInsuredAge() <= 55 )factor = 80;
        	else if( cepr01030101Form.getInsuredAge() >= 56 && cepr01030101Form.getInsuredAge() <= 70 )factor = 50;
        	double maxUp = LazyConverter.toDouble( cepr01030102Form.getBasePremium() ) * li_kali * factor;
        	
        	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040218Mapper.X8){
        		if     ( cepr01030101Form.getInsuredAge() >= 1  && cepr01030101Form.getInsuredAge() <= 15 )factor = 110;
            	else if( cepr01030101Form.getInsuredAge() >= 16 && cepr01030101Form.getInsuredAge() <= 20 )factor = 120;
            	else if( cepr01030101Form.getInsuredAge() >= 21 && cepr01030101Form.getInsuredAge() <= 25 )factor = 100;
            	else if( cepr01030101Form.getInsuredAge() >= 26 && cepr01030101Form.getInsuredAge() <= 30 )factor = 90;
            	else if( cepr01030101Form.getInsuredAge() >= 31 && cepr01030101Form.getInsuredAge() <= 35 )factor = 80;
            	else if( cepr01030101Form.getInsuredAge() >= 36 && cepr01030101Form.getInsuredAge() <= 40 )factor = 75;
            	else if( cepr01030101Form.getInsuredAge() >= 41 && cepr01030101Form.getInsuredAge() <= 45 )factor = 70;
            	else if( cepr01030101Form.getInsuredAge() >= 46 && cepr01030101Form.getInsuredAge() <= 50 )factor = 50;
            	else if( cepr01030101Form.getInsuredAge() >= 51 && cepr01030101Form.getInsuredAge() <= 55 )factor = 40;
            	else if( cepr01030101Form.getInsuredAge() >= 56 && cepr01030101Form.getInsuredAge() <= 70 )factor = 25;
            	maxUp = LazyConverter.toDouble( cepr01030102Form.getBasePremium() ) * li_kali * factor * 0.5;
        	}
        	
        	BigDecimal maksUp = new BigDecimal("500000000");
        	BigDecimal upFix = cepr01030102Form.getTotalSumInsuredFix();
        	if( cepr01030102Form.getCurrencyCd().equals("02") ) maksUp = new BigDecimal("50000");
        	if( cepr01030101Form.getInsuredAge() <= 5 ){
        		if( cepr01030102Form.getTotalSumInsured().compareTo( maksUp ) > 0 ){
        			commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, cepr01030102Form.getTotalSumInsured(), new BigDecimal("500000000"),  new BigDecimal("50000"));
        		}else{
        			commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() ), maxUp, maxUp );
        		}
            }else{
            	commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() ), maxUp, maxUp );
            }
           
            commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );
            commonValidator.validateInvestmentSumIs100Percent();
        }else
        {
            errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.formEmpty", null, null );
        }
        super.validateCurrentPage();
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040211X8Entry.processFormAfterSubmitBeforeValidation" );
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
        
        super.processFormAfterSubmitBeforeValidation();
        refreshBaseTopupPremium();
        if( "onChangePremium".equals( cepr01030102Form.getTheEvent() )
            || "onPressButtonCountTotalSumInsured".equals( cepr01030102Form.getTheEvent() )
            || cepr01030102Form.getTotalSumInsured().equals( new BigDecimal( "0" ) ) )
        {

            double idec_rate;
            double adec_premi = premium;
            double idec_up;

            int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );
            
            switch( paymentModeCd )
            {
                case Hardcode.PAY_MODE_CD_TRIWULANAN: adec_premi = (adec_premi * 10) * 2;  break;
                case Hardcode.PAY_MODE_CD_SEMESTERAN: adec_premi = adec_premi * 10;  break;
                case Hardcode.PAY_MODE_CD_BULANAN   :adec_premi = (adec_premi * 10) * 6;  break;
                case Hardcode.PAY_MODE_CD_SEKALIGUS : adec_premi = adec_premi * 5; break;
                case Hardcode.PAY_MODE_CD_TAHUNAN   : adec_premi = adec_premi * 5; break;
                default: break;
            }
            
            // I got it from n_prod_159 function of_set_premi
            
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
            idec_rate = 1000;
            idec_up = adec_premi * idec_rate / 1000;
            // count totalSumInsured with premiumCombinationPercent
            idec_up = idec_up * premiumCombinationPercent / 100;
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	if(idec_up <= 7500000){
                	idec_up = 7500000;
                }
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	if(idec_up <= 750){
                	idec_up = 750;
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