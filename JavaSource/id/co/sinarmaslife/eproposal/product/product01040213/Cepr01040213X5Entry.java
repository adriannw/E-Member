package id.co.sinarmaslife.eproposal.product.product01040213;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040213X7Entry
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
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000Generator;
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

public class Cepr01040213X5Entry extends Cepr01040213Entry
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040213X5Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040213X7Entry constructor is called ..." );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040213X7Entry.initDisplayedForm" );
        super.initDisplayedForm();
        
        cepr01030102Form.setPremiumFurloughYearDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPremiumFurloughYearListDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setButtonRiderDisplay( CommonConst.DISPLAY_FALSE );
     	cepr01030102Form.setSmilePensionPackageListDisplay( CommonConst.DISPLAY_TRUE );
     	cepr01030102Form.setButtonTopupDisplay( CommonConst.DISPLAY_FALSE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040213X7Entry.initDisabledForm" );
        super.initDisabledForm();
        cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPremiumFurloughYearIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040213X7Entry.initReadOnlyForm" );
        super.initReadOnlyForm();
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
    	cepr01030102Form.setSmilePensionPackageList( comboLookupBusiness.excellentPensionPackageList() );
        cepr01030102Form.setPremiumFurloughYear( 1 );
        cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_TAHUNAN );
        cepr01030102Form.setDynamicIdr( new BigDecimal("100") );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        super.fillDefaultValueEachTimeFormCalled();
        cepr01030102Form.setFixIdrIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setDynamicIdrIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setAggresiveIdrIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        if( credentialsVO != null && credentialsVO.getGroupId() != null && (credentialsVO.getGroupId().equals( Hardcode.GROUP_BANCASS ) || credentialsVO.getGroupId().equals( Hardcode.GROUP_BANCASS_SUPPORT )) ){
        	cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( true, new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_SEMESTERAN, Hardcode.PAY_MODE_CD_TRIWULANAN, Hardcode.PAY_MODE_CD_BULANAN } ) );
        }else{
        	cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_BULANAN } ) );
        }
        
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTermOfPayment( 5 );
        cepr01030102Form.setTermOfContract( 100 - cepr01030101Form.getInsuredAge() );
        if( cepr01030102Form.getSmilePensionPackageCd() != null && cepr01030102Form.getSmilePensionPackageCd().equals(4) ){
        	cepr01030103Form.setEkaSehatFlag( CommonConst.CHECKED_TRUE );
        	cepr01030103Form.setEkaSehatCd(8);
        } else {
        	cepr01030103Form.setEkaSehatFlag( CommonConst.CHECKED_FALSE );
        	cepr01030103Form.setEkaSehatCd(null);
        }
        
        //cepr01030102Form.setPremiumCombinationCd( new BigDecimal( "100" ) );
        cepr01030102Form.setPremiumCombinationList( getPremiumCombinationList() );
        //cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination10FoldedList().subList( 1, 2 ) );
        refreshBaseTopupPremium();
        List<OptionVO> premiumList = null;
        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
        	List<OptionVO> tempPremiumList = new ArrayList<OptionVO>();
        	tempPremiumList.add( new OptionVO( "500,000.00", "500,000.00" ) );
        	tempPremiumList.add( new OptionVO( "750,000.00", "750,000.00" ) );
        	tempPremiumList.add( new OptionVO( "1,000,000.00", "1,000,000.00" ) );
        	tempPremiumList.add( new OptionVO( "1,500,000.00", "1,500,000.00" ) );
        	tempPremiumList.add( new OptionVO( "2,000,000.00", "2,000,000.00" ) );
        	tempPremiumList.add( new OptionVO( "2,500,000.00", "2,500,000.00" ) );
        	tempPremiumList.add( new OptionVO( "3,000,000.00", "3,000,000.00" ) );
        	premiumList = tempPremiumList;
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
        	List<OptionVO> tempPremiumList = new ArrayList<OptionVO>();
        	tempPremiumList.add( new OptionVO( "50.00", "50.00" ) );
        	tempPremiumList.add( new OptionVO( "75.00", "75.00" ) );
        	tempPremiumList.add( new OptionVO( "100.00", "100.00" ) );
        	tempPremiumList.add( new OptionVO( "150.00", "150.00" ) );
        	tempPremiumList.add( new OptionVO( "200.00", "200.00" ) );
        	tempPremiumList.add( new OptionVO( "250.00", "250.00" ) );
        	tempPremiumList.add( new OptionVO( "300.00", "300.00" ) );
        	premiumList = tempPremiumList;
        }

        cepr01030102Form.setPremiumList( premiumList );
        
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040213X7Entry.validateCurrentPage" );
        int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );
        double premiMinimum = 0.0;
        switch( paymentModeCd )
        {
            case Hardcode.PAY_MODE_CD_TRIWULANAN: premiMinimum = 1250000;  break;
            case Hardcode.PAY_MODE_CD_SEMESTERAN: premiMinimum = 2500000;  break;
            case Hardcode.PAY_MODE_CD_BULANAN   : premiMinimum = 500000;  break;
            case Hardcode.PAY_MODE_CD_TAHUNAN  : premiMinimum = 5000000;  break;
            default: break;
        }
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, premiMinimum, -1 );
        
        super.validateCurrentPage();
        
        if( cepr01030102Form.getBasePremium() != null && cepr01030102Form.getTotalSumInsured() != null ){
        	
            double premi = LazyConverter.toDouble( cepr01030102Form.getBasePremium() );
            switch( paymentModeCd )
            {
	            case Hardcode.PAY_MODE_CD_TRIWULANAN: premi = ( premi * 4 ) * 5;  break;
	            case Hardcode.PAY_MODE_CD_SEMESTERAN: premi = ( premi * 2 ) * 5;  break;
	            case Hardcode.PAY_MODE_CD_BULANAN   : premi = ( premi * 12 ) * 5;  break;
	            case Hardcode.PAY_MODE_CD_TAHUNAN  : premi = premi * 5;  break;
                default: break;
            }
            commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED,  LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() ), premi, premi );
        }
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040213X7Entry.processFormAfterSubmitBeforeValidation" );
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
        refreshBaseTopupPremium();

        if( "onChangePremium".equals( cepr01030102Form.getTheEvent() )
            || "onPressButtonCountTotalSumInsured".equals( cepr01030102Form.getTheEvent() )
            || cepr01030102Form.getTotalSumInsured().equals( new BigDecimal( "0" ) ) )
        {
	        if( cepr01030102Form.getPremium() != null ){
	            double idec_rate;
	            double adec_premi = premium;
	            double idec_up;
	
	            int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );
	            
	            switch( paymentModeCd )
	            {
		            case Hardcode.PAY_MODE_CD_TRIWULANAN: adec_premi = (adec_premi * 10) * 2;  break;
		            case Hardcode.PAY_MODE_CD_SEMESTERAN: adec_premi = adec_premi * 10;  break;
		            case Hardcode.PAY_MODE_CD_BULANAN   : adec_premi = (adec_premi * 10) * 6;  break;
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
	        }
        }
    }
    
    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040213Entry.validatePreviousPage" );
        super.validatePreviousPage();
    }
    
    public static List<OptionVO> getPremiumCombinationList()
    {
        List<OptionVO> premiumCombinationList = new ArrayList<OptionVO>();

        OptionVO optionVO;
        optionVO = new OptionVO( "", "" );
        premiumCombinationList.add( optionVO );

        String value;
        String label;
        value = 80 + ".00";
        label = "Premi Pokok " + 80 + "%, Premi Top-Up Berkala " + ( 100 - 80 ) + "%";
        optionVO = new OptionVO( value, label );
        premiumCombinationList.add( optionVO );
        
        value = 50 + ".00";
        label = "Premi Pokok " + 50 + "%, Premi Top-Up Berkala " + ( 100 - 50 ) + "%";
        optionVO = new OptionVO( value, label );
        premiumCombinationList.add( optionVO );

        return premiumCombinationList;
    }
}