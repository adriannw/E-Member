package id.co.sinarmaslife.eproposal.product.product01040213;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040213X10Entry
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : fadly mathendra
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

public class Cepr01040213X10Entry extends Cepr01040213Entry
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040213X10Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040213X10Entry constructor is called ..." );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040213X10Entry.initDisplayedForm" );
        super.initDisplayedForm();
        
        cepr01030102Form.setPremiumFurloughYearDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPremiumFurloughYearListDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setButtonRiderDisplay( CommonConst.DISPLAY_FALSE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040213X10Entry.initDisabledForm" );
        super.initDisabledForm();
        cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumFurloughYearIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040213X10Entry.initReadOnlyForm" );
        super.initReadOnlyForm();
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040213X10Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        cepr01030102Form.setPremiumFurloughYear( 1 );
        cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_TAHUNAN );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040213X10Entry.fillDefaultValueEachTimeFormCalled" );
        super.fillDefaultValueEachTimeFormCalled();
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD } ) );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_SEMESTERAN, Hardcode.PAY_MODE_CD_TRIWULANAN, Hardcode.PAY_MODE_CD_BULANAN } ) );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTermOfPayment( 10 );
        cepr01030102Form.setTermOfContract( 100 - cepr01030101Form.getInsuredAge() );
        
        
        //cepr01030102Form.setPremiumCombinationCd( new BigDecimal( "100" ) );
        cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombinationFoldedStart80List( 10 ) );
        //cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination10FoldedList().subList( 1, 2 ) );
        refreshBaseTopupPremium();
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040213X10Entry.validateCurrentPage" );
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
//        double minPremiumIdr = 4000000;
//        double minPremiumUsd = 400;
//        commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );

        super.validateCurrentPage();
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040213X10Entry.processFormAfterSubmitBeforeValidation" );
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
        refreshBaseTopupPremium();

//        if( "onChangePremium".equals( cepr01030102Form.getTheEvent() )
//            || "onPressButtonCountTotalSumInsured".equals( cepr01030102Form.getTheEvent() )
//            || cepr01030102Form.getTotalSumInsured().equals( new BigDecimal( "0" ) ) )
//        {
        if( cepr01030102Form.getPremium() != null ){
            double idec_rate;
            double adec_premi = premium;
            double idec_up;

            int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );
            
            switch( paymentModeCd )
            {
	            case Hardcode.PAY_MODE_CD_TRIWULANAN: adec_premi = (adec_premi * 10) * 2;  break;
	            case Hardcode.PAY_MODE_CD_SEMESTERAN: adec_premi = adec_premi * 10;  break;
	            case Hardcode.PAY_MODE_CD_BULANAN   :adec_premi = (adec_premi * 10) * 6;  break;
	            case Hardcode.PAY_MODE_CD_SEKALIGUS   : adec_premi = adec_premi * 5; break;
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
        }
    }
    
    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040213Entry.validatePreviousPage" );
        super.validatePreviousPage();
    }
}