package id.co.sinarmaslife.eproposal.product.product01040211;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040211X12Entry
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

public class Cepr01040211X24Entry extends Cepr01040211Entry
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040211X24Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040211X12Entry constructor is called ..." );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040211X12Entry.initDisplayedForm" );
        super.initDisplayedForm();
        
        cepr01030102Form.setPremiumFurloughYearDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPremiumFurloughYearListDisplay( CommonConst.DISPLAY_FALSE );
        
        cepr01030103Form.setWaiverCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverTpdDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorSpouseTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        //cepr01030103Form.setTermRiderDisplay( CommonConst.DISPLAY_FALSE );
      //--> IGA UPDATE RIDER SESUAI SPEK SIMAS POWER LINK 24082020
        cepr01030103Form.setPayor5Tpd10CiDeathDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setWaiver5Tpd10CiDisplay (CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setEkaSehatInnerLimitDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setHcpProviderDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setHcpDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setLadiesMedExpenseDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setLadiesMedExpenseInnerLimitDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setHcpLadiesDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setLadiesInsDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setScholarshipDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setEarlyStageCi99Display(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setPayorTpdCiDeathDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setWaiverTpdCiDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setEkaSehatExtraDisplay(CommonConst.DISPLAY_FALSE); //Decommission Rider SMAC/SMEX Oktober 2021 (NCR/2021/10/106)
        //<-- DONE
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040211X12Entry.initDisabledForm" );
        super.initDisabledForm();
        cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumFurloughYearIsDisabled( CommonConst.DISABLED_TRUE );
        
        if(cepr01030102Form.getProtectorPackageCd()>1){
        	cepr01030102Form.setDynamicIdrIsDisabled(CommonConst.DISABLED_TRUE );    
        	cepr01030102Form.setPremiumIsDisabled(CommonConst.DISABLED_TRUE );
        	
        }else{
        	cepr01030102Form.setDynamicIdrIsDisabled(CommonConst.DISABLED_FALSE );   
        	cepr01030102Form.setPremiumIsDisabled(CommonConst.DISABLED_FALSE );
        
        }
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040211X12Entry.initReadOnlyForm" );
        super.initReadOnlyForm();
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040211X12Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumFurloughYear( 1 );
        cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_SEKALIGUS );
       
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040211X12Entry.fillDefaultValueEachTimeFormCalled" );
        super.fillDefaultValueEachTimeFormCalled();
        
        cepr01030102Form.setFixIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 } ) );
        cepr01030102Form.setDynamicIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 } ) );
        cepr01030102Form.setAggresiveIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 } ) );
        cepr01030102Form.setSecureUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 } ) );
        cepr01030102Form.setDynamicUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 } ) );
        
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD } ) );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_SEKALIGUS } ) );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTermOfPayment( 1 );
        cepr01030102Form.setTermOfContract( 100 - cepr01030101Form.getInsuredAge() );        
        
        //cepr01030102Form.setPremiumCombinationCd( new BigDecimal( "100" ) );       
        cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombinationFoldedList( 100 ) );     
        //cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination10FoldedList().subList( 1, 2 ) );
        
        if(cepr01030102Form.getProtectorPackageCd()==24){
    		cepr01030102Form.setPremium(new BigDecimal("100000000"));
    		cepr01030102Form.setTotalSumInsured(new BigDecimal("100000000"));
    		cepr01030102Form.setPremiumCombinationCd(new BigDecimal("80") ); 
    		cepr01030102Form.setDynamicIdr(new BigDecimal(100.0));
    		cepr01030103Form.setTermRiderAmount( cepr01030102Form.getTotalSumInsured() );
    	}
    	else if(cepr01030102Form.getProtectorPackageCd()==25){
    		cepr01030102Form.setPremium(new BigDecimal("200000000"));
    		cepr01030102Form.setTotalSumInsured(new BigDecimal("200000000"));
    		cepr01030102Form.setPremiumCombinationCd(new BigDecimal("80") ); 
    		cepr01030102Form.setDynamicIdr(new BigDecimal(100.0));
    		cepr01030103Form.setTermRiderAmount( cepr01030102Form.getTotalSumInsured() );
    	}
    	 else if(cepr01030102Form.getProtectorPackageCd()==26){
    		 cepr01030102Form.setPremium(new BigDecimal("300000000"));
    		 cepr01030102Form.setTotalSumInsured(new BigDecimal("300000000"));
    		 cepr01030102Form.setPremiumCombinationCd(new BigDecimal("80") );
    		 cepr01030102Form.setDynamicIdr(new BigDecimal(100.0));
    		 cepr01030103Form.setTermRiderAmount( cepr01030102Form.getTotalSumInsured() );
    	 }
        
        refreshBaseTopupPremium(); 
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040211X12Entry.validateCurrentPage" );
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
            minPremiumIdr = 2500000;
            minPremiumUsd = 250;
            break;
        case Hardcode.PAY_MODE_CD_TRIWULANAN:
            minPremiumIdr = 1250000;
            minPremiumUsd = 125;
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
        	   double factor_percentage = 1;

               int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );
               
               switch( paymentModeCd )
               {
	               case Hardcode.PAY_MODE_CD_TRIWULANAN: factor_percentage = 0.27;  break;
	               case Hardcode.PAY_MODE_CD_SEMESTERAN: factor_percentage = 0.525;  break;
	               case Hardcode.PAY_MODE_CD_BULANAN   : factor_percentage = 0.1; break;
                   default: break;
               }
               
               double maxUp = 0;  
               if(cepr01030102Form.getRightPartOfBusinessCd()==Cepr01040211Mapper.X24|| cepr01030102Form.getRightPartOfBusinessCd()==Cepr01040211Mapper.X21 || cepr01030102Form.getRightPartOfBusinessCd()==Cepr01040211Mapper.X12){ //SIMAS POWER LINK(120 X10)
            	//MAKS UP (lama) berdasarkan No. 084/AJS-SK/VIII/2011
               	/*
            	double factor = 0;
           		if( cepr01030101Form.getInsuredAge() >= 1 && cepr01030101Form.getInsuredAge() <= 19 )factor = 50;
           		else if( cepr01030101Form.getInsuredAge() >= 20 && cepr01030101Form.getInsuredAge() <= 29 )factor = 40;
           		else if( cepr01030101Form.getInsuredAge() >= 30 && cepr01030101Form.getInsuredAge() <= 39 )factor = 30;
           		else if( cepr01030101Form.getInsuredAge() >= 40 && cepr01030101Form.getInsuredAge() <= 49 )factor = 20;
           		else if( cepr01030101Form.getInsuredAge() >= 50 && cepr01030101Form.getInsuredAge() <= 70 )factor = 10;
           		maxUp = ( LazyConverter.toDouble( cepr01030102Form.getBasePremium() ) / factor_percentage ) * factor; 
           		commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() ), maxUp, maxUp );
               */
            	//DIGUNAKAN TABEL MAXIMUM UP SIMAS POWER LINK : SK-SE No. 117/AJS-SE/X/2014
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
                maxUp = LazyConverter.toDouble( cepr01030102Form.getBasePremium() ) * li_kali * factor; 
                BigDecimal maksUp = new BigDecimal("500000000");
               	BigDecimal upFix = cepr01030102Form.getTotalSumInsuredFix();
               	if( cepr01030102Form.getCurrencyCd().equals("02") ) maksUp = new BigDecimal("50000");
               	if( cepr01030101Form.getInsuredAge() <= 5 && cepr01030102Form.getTotalSumInsured().compareTo( maksUp ) > 0 ){
                   	commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, cepr01030102Form.getTotalSumInsured(), new BigDecimal("500000000"),  new BigDecimal("50000"));
                   }else{
                   	commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() ), maxUp, maxUp );
                   }  
               }
             
//        	commonValidator.validateMinimumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() ), 7500000, 750 );
        	  
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
        logger.info( "*-*-*-* Cepr01040211X12Entry.processFormAfterSubmitBeforeValidation" );
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
      
   
        if( ("onChangePremium".equals( cepr01030102Form.getTheEvent() )
                || "onPressButtonCountTotalSumInsured".equals( cepr01030102Form.getTheEvent() )
                || cepr01030102Form.getTotalSumInsured().equals( new BigDecimal( "0" ) ) 
                || "onPressButtonPreviewProposal".equals( cepr01030102Form.getTheEvent() ))
                && cepr01030102Form.getProtectorPackageCd()==0)
            {
        	  refreshBaseTopupPremium();
        	  
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
        
       if("onChangeProtectorPackageCd".equals( cepr01030102Form.getTheEvent()) 
        	|| "onPressButtonCountTotalSumInsured".equals( cepr01030102Form.getTheEvent())
        	|| "onPressButtonPreviewProposal".equals( cepr01030102Form.getTheEvent())
        	|| cepr01030102Form.getProtectorPackageCd()>1
        	)
        {  
        	if(cepr01030102Form.getProtectorPackageCd()==24){
        		cepr01030102Form.setPremium(new BigDecimal("100000000"));
        		cepr01030102Form.setTotalSumInsured(new BigDecimal("100000000"));
        		cepr01030102Form.setPremiumCombinationCd(new BigDecimal("80") ); 
        		cepr01030102Form.setDynamicIdr(new BigDecimal(100.0));
        	}
        	else if(cepr01030102Form.getProtectorPackageCd()==25){
        		cepr01030102Form.setPremium(new BigDecimal("200000000"));
        		cepr01030102Form.setTotalSumInsured(new BigDecimal("200000000"));
        		cepr01030102Form.setPremiumCombinationCd(new BigDecimal("80") ); 
        		cepr01030102Form.setDynamicIdr(new BigDecimal(100.0));        	
        	}
        	 else if(cepr01030102Form.getProtectorPackageCd()==26){
        		 cepr01030102Form.setPremium(new BigDecimal("300000000"));
        		 cepr01030102Form.setTotalSumInsured(new BigDecimal("300000000"));
        		 cepr01030102Form.setPremiumCombinationCd(new BigDecimal("80") );
        		 cepr01030102Form.setDynamicIdr(new BigDecimal(100.0));
        	 }
        	
        }
        
        if("onChangeProtectorPackageCd".equals( cepr01030102Form.getTheEvent())
            	&& cepr01030102Form.getProtectorPackageCd()==0
            	)
            {  
            	if(cepr01030102Form.getProtectorPackageCd()==0){
            		cepr01030102Form.setTotalSumInsured( new BigDecimal( "0" ) );
            		cepr01030102Form.setPremium(new BigDecimal("0"));
            		cepr01030102Form.setDynamicIdr(new BigDecimal(0.0));
                	cepr01030102Form.setPremiumCombinationCd(new BigDecimal("100") );
            	}    
            }
    }
    
    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040213Entry.validatePreviousPage" );
        super.validatePreviousPage();
    }
}