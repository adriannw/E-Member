package id.co.sinarmaslife.eproposal.product.product01040207;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040207X4Entry
 * Description         	: Eka Link 80 Plus (116)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 13, 2008 2:21:24 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.standard.util.LazyConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;

public class Cepr01040207X4Entry extends Cepr01040207Entry
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040207X4Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
    }

    public void initDisplayedForm()
    {
        super.initDisplayedForm();
        cepr01030103Form.setWaiver5Tpd10CiDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setPayor5Tpd10CiDeathDisplay( CommonConst.DISPLAY_TRUE );
    }

    public void initDisabledForm()
    {
        super.initDisabledForm();
        cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_FALSE );
        if( cepr01030102Form.getSmilePensionPackageCd() != null && cepr01030102Form.getSmilePensionPackageCd() > 1 ){
        	cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE);
			cepr01030102Form.setDynamicIdrIsDisabled( CommonConst.DISABLED_TRUE );
			cepr01030102Form.setFixIdrIsDisabled( CommonConst.DISABLED_TRUE );
			cepr01030102Form.setAggresiveIdrIsDisabled( CommonConst.DISABLED_TRUE );
        }else{
        	cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
			cepr01030102Form.setDynamicIdrIsDisabled( CommonConst.DISABLED_FALSE );
			cepr01030102Form.setFixIdrIsDisabled( CommonConst.DISABLED_FALSE );
			cepr01030102Form.setAggresiveIdrIsDisabled( CommonConst.DISABLED_FALSE );
        	
        }
    }

    public void initReadOnlyForm()
    {
        super.initReadOnlyForm();
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        cepr01030102Form.setPremiumFurloughYear( 10 );
        cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_TAHUNAN );
        super.fillDefaultValueEachTimeRightPartOfBusinessCdChanged();
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_SEMESTERAN, Hardcode.PAY_MODE_CD_TRIWULANAN, Hardcode.PAY_MODE_CD_BULANAN } ) );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTermOfPayment( 80 - cepr01030101Form.getInsuredAge() );
        cepr01030102Form.setPremiumFurloughYearIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPremiumFurloughYearListDisplay( CommonConst.DISPLAY_TRUE );
        refreshBaseTopupPremium();
        super.fillDefaultValueEachTimeFormCalled();
 	   if( cepr01030102Form.getSecondPackageListDisplay().equals( CommonConst.DISPLAY_TRUE ) && 
       		cepr01030102Form.getSecondPackageCd() != null ){
 		   if( cepr01030102Form.getSecondPackageCd() > 1 ){
 			     checkAllRider( false );
	         	 cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_BULANAN );
	         	 cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_TRUE );
	         	 cepr01030102Form.setPremiumCombinationCd(new BigDecimal("60") );
	         	 cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_TRUE );
	         	 cepr01030102Form.setPremiumFurloughYearIsDisabled( CommonConst.DISABLED_TRUE );
	         	 cepr01030102Form.setDynamicIdrIsDisabled( CommonConst.DISABLED_TRUE );
	         	 cepr01030102Form.setFixIdrIsDisabled( CommonConst.DISABLED_TRUE );
	         	 cepr01030102Form.setAggresiveIdrIsDisabled( CommonConst.DISABLED_TRUE );
	         	 if( cepr01030102Form.getSecondPackageCd().intValue() == 2 ){ // umroh dan haji plus
	         		 cepr01030102Form.setPremium( new BigDecimal( "1250000" ) );
	         		 cepr01030102Form.setPremiumFurloughYear(10);
	         	 }else if( cepr01030102Form.getSecondPackageCd().intValue() == 3 ){ // haji plus
	         		 cepr01030102Form.setPremium( new BigDecimal( "1000000" ) );	 
	         		 cepr01030102Form.setPremiumFurloughYear(10);
	         	 }else if( cepr01030102Form.getSecondPackageCd().intValue() == 4 ){ // umroh
	         		 cepr01030102Form.setPremium( new BigDecimal( "600000" ) );	 
	         		 cepr01030102Form.setPremiumFurloughYear(5);
	         	 }
	         	 cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_TRUE );
 		   }
 		   else{
 			   ibadahDisableOff();
 		   }
       }
    }

    public void validateCurrentPage()
    {
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double minPremiumIdr;
        double minPremiumUsd;

        switch( cepr01030102Form.getPaymentModeCd() )
        {
            case Hardcode.PAY_MODE_CD_SEKALIGUS:
                minPremiumIdr = 5000000;
                minPremiumUsd = 500;
                break;
            case Hardcode.PAY_MODE_CD_TAHUNAN:
                minPremiumIdr = 1800000;
                minPremiumUsd = 180;
                break;
            case Hardcode.PAY_MODE_CD_SEMESTERAN:
                minPremiumIdr = 900000;
                minPremiumUsd = 90;
                break;
            case Hardcode.PAY_MODE_CD_TRIWULANAN:
                minPremiumIdr = 450000;
                minPremiumUsd = 45;
                break;
            case Hardcode.PAY_MODE_CD_BULANAN:
                minPremiumIdr = 150000;
                minPremiumUsd = 15;
                break;
            default:
                throw new RuntimeException( "*-*-*-* cepr01030102Form.getPaymentModeCd() not listed in Cepr01040201Entry.validateCurrentPage" );
        }
        commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );

        super.validateCurrentPage();
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        
        if( cepr01030102Form.getSecondPackageListDisplay().equals( CommonConst.DISPLAY_TRUE ) && 
        		cepr01030102Form.getSecondPackageCd() != null &&  cepr01030102Form.getSecondPackageCd() > 1){
        	cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_BULANAN );
          	 if( cepr01030102Form.getSecondPackageCd().intValue() == 2 ){ // umroh dan haji plus
          		 cepr01030102Form.setPremium( new BigDecimal( "1250000" ) );
          		 cleanTopUpAndWithdraw();
          		 cepr01030104Form.getTopupDrawVOList().get(4).setYearFlag( CommonConst.CHECKED_TRUE );
          		 cepr01030104Form.getTopupDrawVOList().get(4).setDrawAmount( new BigDecimal( "30000000" ) );
          		 
        		 cepr01030104Form.getTopupDrawVOList().get(6).setYearFlag( CommonConst.CHECKED_TRUE );
           		 cepr01030104Form.getTopupDrawVOList().get(6).setDrawAmount( new BigDecimal( "54000000" ) );
           		
           		 cepr01030104Form.getTopupDrawVOList().get(9).setYearFlag( CommonConst.CHECKED_TRUE );
           		 cepr01030104Form.getTopupDrawVOList().get(9).setDrawAmount( new BigDecimal( "78000000" ) );
          	 }else if( cepr01030102Form.getSecondPackageCd().intValue() == 3 ){ // haji plus
          		 cepr01030102Form.setPremium( new BigDecimal( "1000000" ) );	 
          		 cleanTopUpAndWithdraw();
          		 cepr01030104Form.getTopupDrawVOList().get(6).setYearFlag( CommonConst.CHECKED_TRUE );
          		 cepr01030104Form.getTopupDrawVOList().get(6).setDrawAmount( new BigDecimal( "54000000" ) );
          		
          		 cepr01030104Form.getTopupDrawVOList().get(9).setYearFlag( CommonConst.CHECKED_TRUE );
          		 cepr01030104Form.getTopupDrawVOList().get(9).setDrawAmount( new BigDecimal( "78000000" ) );
          	 }else if( cepr01030102Form.getSecondPackageCd().intValue() == 4 ){ // umroh
          		 cepr01030102Form.setPremium( new BigDecimal( "600000" ) );	 
          		 cleanTopUpAndWithdraw();
          		cepr01030104Form.getTopupDrawVOList().get(4).setYearFlag( CommonConst.CHECKED_TRUE );
         		 cepr01030104Form.getTopupDrawVOList().get(4).setDrawAmount( new BigDecimal( "30000000" ) );
          	 }
          	 cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_TRUE );
          	 cepr01030102Form.setDynamicIdr( new BigDecimal("50") );
        	 cepr01030102Form.setAggresiveIdr( new BigDecimal("50") );
        }
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
        refreshBaseTopupPremium();
        super.processFormAfterSubmitBeforeValidation();
        if( cepr01030102Form.getSmilePensionPackageCd() != null && cepr01030102Form.getSmilePensionPackageCd() > 1 && cepr01030102Form.getPremium() != null ){
        	   double idec_rate;
               double adec_premi = premium;
               double idec_up;

               int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );

               switch( paymentModeCd )
               {
                   case Hardcode.PAY_MODE_CD_TRIWULANAN: adec_premi = adec_premi * 4;  break;
                   case Hardcode.PAY_MODE_CD_SEMESTERAN: adec_premi = adec_premi * 2;  break;
                   case Hardcode.PAY_MODE_CD_BULANAN   : adec_premi = adec_premi * 12; break;
                   default: break;
               }

               // I got it from n_prod_159 function of_set_premi
               idec_rate = 5000;
               idec_up = adec_premi * idec_rate / 1000;

               // count totalSumInsured with premiumCombinationPercent
               idec_up = idec_up * premiumCombinationPercent / 100;
               cepr01030102Form.setTotalSumInsured( new BigDecimal( idec_up ) );
        }else{
        	if( cepr01030102Form.getPremium() != null ){
	        if( "onChangePremium".equals( cepr01030102Form.getTheEvent() )
	            || "onPressButtonCountTotalSumInsured".equals( cepr01030102Form.getTheEvent() )
	            || cepr01030102Form.getTotalSumInsured() != null && cepr01030102Form.getTotalSumInsured().equals( new BigDecimal( "0" ) )  
	            || "onChangesecondPackageCd".equals( cepr01030102Form.getTheEvent() ) )
	        {
	            double idec_rate;
	            double adec_premi = premium;
	            double idec_up = 0;
	
	            int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );
	
	            switch( paymentModeCd )
	            {
	                case Hardcode.PAY_MODE_CD_TRIWULANAN: adec_premi = adec_premi * 4;  break;
	                case Hardcode.PAY_MODE_CD_SEMESTERAN: adec_premi = adec_premi * 2;  break;
	                case Hardcode.PAY_MODE_CD_BULANAN   : adec_premi = adec_premi * 12; break;
	                default: break;
	            }
	
	            // I got it from n_prod_159 function of_set_premi
	            //idec_rate = 5000;
	            //idec_up = adec_premi * idec_rate / 1000;
	            
	            int li_temp;
	            int li_bisnis = LazyConverter.toInt( cepr01030102Form.getLeftPartOfBusinessCd() );
	            li_temp = of_get_max( li_bisnis  );
	
	            // count totalSumInsured with premiumCombinationPercent
	            //idec_up = idec_up * premiumCombinationPercent / 100;
	            idec_up = adec_premi *  li_temp * premiumCombinationPercent / 100;
	            
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
	        }
        }
        }
    }
    
    public void cleanTopUpAndWithdraw(){
    	for( int i = 0 ; i < cepr01030104Form.getTopupDrawVOList().size() ; i ++ ){
    		cepr01030104Form.getTopupDrawVOList().get(i).setYearFlag( CommonConst.CHECKED_FALSE );
    		cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount( BigDecimal.ZERO );
    	}
    }
    public void ibadahDisableOff(){
	  	cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE );
	   	cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_FALSE );
	   	cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_FALSE );
	    cepr01030102Form.setPremiumFurloughYearIsDisabled( CommonConst.DISABLED_FALSE );
	   	cepr01030102Form.setDynamicIdrIsDisabled( CommonConst.DISABLED_FALSE );
	 	cepr01030102Form.setFixIdrIsDisabled( CommonConst.DISABLED_FALSE );
	 	cepr01030102Form.setAggresiveIdrIsDisabled( CommonConst.DISABLED_FALSE );
    }
}