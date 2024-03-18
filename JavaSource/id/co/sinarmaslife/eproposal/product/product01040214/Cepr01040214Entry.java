package id.co.sinarmaslife.eproposal.product.product01040214;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040214Entry
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 10, 2009 2:48:44 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000EntryInterface;
import id.co.sinarmaslife.eproposal.common.lookup.LookupList;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000UlinkEntry;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000Generator;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.eproposal.model.vo.UlinkValidatorParVO;
import id.co.sinarmaslife.eproposal.model.vo.AmountVO;
import id.co.sinarmaslife.eproposal.product.product01040212.Cepr01040212Mapper;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.LazyConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cepr01040214Entry extends Cepr01040000UlinkEntry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040214Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040214Entry constructor is called ..." );
        setDownloadUrl( "wepr01040214.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040214Entry.initDisplayedForm" );
        displayStandardForm();
        cepr01030102Form.setPremiumListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setHeaderInvestChoiceDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumCombinationListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonCountTotalSumInsuredDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumFurloughYearDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumFurloughYearListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonRiderAndButtonTopupDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonRiderDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonTopupDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setBasePremiumDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setTopUpPremiumDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonRiderDisplay(CommonConst.DISPLAY_FALSE);
        
        //rider
        //cepr01030103Form.setHcpFamilyDisplay(CommonConst.DISPLAY_FALSE);
        //cepr01030103Form.setHcpDisplay(CommonConst.DISPLAY_FALSE);
        //cepr01030103Form.setPayorTpdDeathDisplay(CommonConst.DISPLAY_FALSE);
        //cepr01030103Form.setPayorCiDeathDisplay(CommonConst.DISPLAY_FALSE);
        //cepr01030103Form.setPayorCiDisplay(CommonConst.DISPLAY_FALSE);
        //cepr01030103Form.setPayorSpouseTpdDeathDisplay(CommonConst.DISPLAY_FALSE);
        //cepr01030103Form.setWaiverTpdDisplay(CommonConst.DISPLAY_FALSE);
        //cepr01030103Form.setWaiverCiDisplay(CommonConst.DISPLAY_FALSE);
        //cepr01030103Form.setTpdDisplay(CommonConst.DISPLAY_FALSE);
        //cepr01030103Form.setCiDisplay(CommonConst.DISPLAY_FALSE);
        //cepr01030103Form.setTermRiderDisplay(CommonConst.DISPLAY_FALSE);
        
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040214Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPremiumListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_FALSE );
        
        /*
        cepr01030103Form.setPaRiskListIsDisabled(CommonConst.DISABLED_TRUE);
        cepr01030103Form.setPaFlagIsDisabled(CommonConst.DISABLED_TRUE);
        cepr01030103Form.setPaClassCdIsDisabled(CommonConst.DISABLED_FALSE);
        cepr01030103Form.setPaClassListIsDisabled(CommonConst.DISABLED_FALSE);
        cepr01030103Form.setPaRiskCdIsDisabled(CommonConst.DISABLED_TRUE);
        cepr01030103Form.setPaClassCdIsDisabled(CommonConst.DISABLED_FALSE);
        cepr01030103Form.setPaUnitQtyListIsDisabled(CommonConst.DISABLED_TRUE);
        */
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040214Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }
    
    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040214Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        
//        for(int i = 0 ; i < cepr01030104Form.getTopupDrawVOList().size() ; i++)
//    	{
//    		cepr01030104Form.getTopupDrawVOList().get(i).setTopupAmount(new BigDecimal(0));
//    		cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(0));
//    		cepr01030104Form.getTopupDrawVOList().get(i).setYearFlag(CommonConst.CHECKED_FALSE);    		
//    	}
        if(cepr01030101Form.getInsuredAge()<21)
    	{
	    	int getUmurInsured = cepr01030101Form.getInsuredAge();
	    	int minAgeTopUp = 1; // krn index, maka minAge - 1
	    	int maxAgeTopUp = 23; // krn index, maka maxAge - 1
	    	int[] valueDrawAmount = {5,5,10,20,30,40,50,60,0};
	    	for(int i = 0 ; i < cepr01030104Form.getTopupDrawVOList().size() ; i++)
	    	{
	    		if(getUmurInsured + i >= minAgeTopUp && getUmurInsured + i <= maxAgeTopUp)
	    		{
	    	  		if(getUmurInsured + i+1 == 4){cepr01030104Form.getTopupDrawVOList().get(i).setYearFlag(CommonConst.CHECKED_TRUE);cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(valueDrawAmount[0]));}
	    	  		if(getUmurInsured + i+1 == 6){cepr01030104Form.getTopupDrawVOList().get(i).setYearFlag(CommonConst.CHECKED_TRUE);cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(valueDrawAmount[1]));}
	    	  		if(getUmurInsured + i+1 == 12){cepr01030104Form.getTopupDrawVOList().get(i).setYearFlag(CommonConst.CHECKED_TRUE);cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(valueDrawAmount[2]));}
	    	  		if(getUmurInsured + i+1 == 15){cepr01030104Form.getTopupDrawVOList().get(i).setYearFlag(CommonConst.CHECKED_TRUE);cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(valueDrawAmount[3]));}
	    	  		if(getUmurInsured + i+1 == 18){cepr01030104Form.getTopupDrawVOList().get(i).setYearFlag(CommonConst.CHECKED_TRUE);cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(valueDrawAmount[4]));}
	    	  		if(getUmurInsured + i+1 == 19){cepr01030104Form.getTopupDrawVOList().get(i).setYearFlag(CommonConst.CHECKED_TRUE);cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(valueDrawAmount[5]));}
	    	  		if(getUmurInsured + i+1 == 20){cepr01030104Form.getTopupDrawVOList().get(i).setYearFlag(CommonConst.CHECKED_TRUE);cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(valueDrawAmount[6]));}
	    	  		if(getUmurInsured + i+1 == 21){cepr01030104Form.getTopupDrawVOList().get(i).setYearFlag(CommonConst.CHECKED_TRUE);cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(valueDrawAmount[7]));}
	    	  		if(getUmurInsured + i+1 == 22){cepr01030104Form.getTopupDrawVOList().get(i).setYearFlag(CommonConst.CHECKED_TRUE);cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(valueDrawAmount[8]));}
	    		}
	    	}
    	}
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040214Entry.fillDefaultValueEachTimeFormCalled" );

        cepr01030102Form.setFixIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setDynamicIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setAggresiveIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setSecureUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setDynamicUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
//        cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination10FoldedList() );
        cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombinationFoldedStart80List( 10 ) );
        cepr01030102Form.setPremiumFurloughYearList( LookupList.getSequenceNumberList( 0, 20, 5, false ) );
//        cepr01030103Form.setTermRiderAmount( cepr01030102Form.getTotalSumInsured() );
        cepr01030103Form.setPaRiskCd(2);
        cepr01030103Form.setPaClassCd(1);
        cepr01030103Form.setPaUnitQtyCd(1);
        
        cepr01030102Form.setTermOfContract( 100 - cepr01030101Form.getInsuredAge() );

        if( cepr01030102Form.getCurrencyCd() == null )
        {
            cepr01030102Form.setCurrencyCd( Hardcode.CUR_IDR_CD );
        }
        List<OptionVO> premiumList = null;

        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 4000000, 16000000, 1000000 );

            cepr01030102Form.setFixIdrDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setFixIdrListDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setDynamicIdrDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setDynamicIdrListDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setAggresiveIdrDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setAggresiveIdrListDisplay( CommonConst.DISPLAY_TRUE );
            
            cepr01030102Form.setSecureUsd(new BigDecimal(0.0));
            cepr01030102Form.setDynamicUsd(new BigDecimal(0.0));
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 400, 1600, 100 );
            cepr01030102Form.setSecureUsdDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setSecureUsdListDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setDynamicUsdDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setDynamicUsdListDisplay( CommonConst.DISPLAY_TRUE );
            
            cepr01030102Form.setFixIdr(new BigDecimal(0.0));
            cepr01030102Form.setDynamicIdr(new BigDecimal(0.0));
            cepr01030102Form.setAggresiveIdr(new BigDecimal(0.0));
        }

        cepr01030102Form.setPremiumList( premiumList );
    }
    
    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040214Entry.validateCurrentPage" );

        //UlinkValidatorParVO parVO = new UlinkValidatorParVO();
        //parVO.setUnder5YearsBerkalaMaxBasePremiumVO( new AmountVO( 100000000, 10000 ) );
        //parVO.setUnder5YearsSekaligusMaxBasePremiumVO( new AmountVO( 400000000, 40000 ) );
        //parVO.setBeyond5YearsBerkalaMaxBasePremiumVO( new AmountVO( 200000000, 20000 ) );
        //parVO.setBeyond5YearsSekaligusMaxBasePremiumVO( new AmountVO( 1000000000, 100000 ) );
        //super.validateCurrentPage( parVO );

        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double minPremiumIdr;
        double minPremiumUsd;
        double maxPremiumIdr;
        double maxPremiumUsd;
        double adec_premi = cepr01030102Form.getBasePremium().doubleValue();
        int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );
        switch( paymentModeCd )
        {
        	case Hardcode.PAY_MODE_CD_TRIWULANAN: adec_premi = adec_premi * 4;  break;
        	case Hardcode.PAY_MODE_CD_SEMESTERAN: adec_premi = adec_premi * 2;  break;
        	case Hardcode.PAY_MODE_CD_BULANAN   : adec_premi = adec_premi * 12; break;
        	default: break;
        }
        
        if( cepr01030102Form.getTotalSumInsured() != null && !cepr01030102Form.getTotalSumInsured().equals(BigDecimal.ZERO) ){
        	double factor;
        	if( cepr01030101Form.getInsuredAge() < 20 ){
        		factor = 50.0;
        	}else{
        		factor = 40.0;
        	}
        	double maks_up = factor * adec_premi;
        	double up = LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() );
        	if( maks_up > 0.0 && up > maks_up ){
        		BigDecimal maks = new BigDecimal("500000000");
        		if( cepr01030102Form.getCurrencyCd().equals("02") )maks = new BigDecimal("50000");
        		if( ( cepr01030101Form.getInsuredAge() <= 5 && cepr01030102Form.getTotalSumInsured().compareTo( maks ) <= 0 ) ||
        				cepr01030101Form.getInsuredAge() >= 6 ){
        			commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, cepr01030102Form.getTotalSumInsured().doubleValue(), maks_up, maks_up );
        		}
        	}
        }

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
        	commonValidator.validateMinimumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() ), 7500000, 750 );
            commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );
//            commonValidator.validateMaximumMoney( Cepr01030102FormConst.PREMIUM, premium, maxPremiumIdr, maxPremiumUsd );
            commonValidator.validateInvestmentSumIs100Percent();
        }
        else
        {
            errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.formEmpty", null, null );
        }

        if( cepr01030102Form.getPremiumCombinationCd() == null )
        {
            errors.rejectValue( Cepr01030102FormConst.PREMIUM_COMBINATION_CD, "error.formEmpty", null, null );
        }

        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTermRiderFlag() ) )
        {
            commonValidator.validateMaximumMoney( Cepr01030102FormConst.ASSURANCE_PLAN_CD, cepr01030103Form.getTermRiderAmount(), cepr01030102Form.getTotalSumInsured(), cepr01030102Form.getTotalSumInsured(), "error.termRiderAmountMaxLimit" );
        }
        commonValidator.validateTenFoldedForAllInvestment();
        commonValidator.validatePremiumFurloughYear( Cepr01030102FormConst.PREMIUM_FURLOUGH_YEAR, 1, 20 );

        if( cepr01030102Form.getTotalSumInsured() != null )
        {
            //double totalSumInsured = LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() );
            //double minTotalSumInsuredIdr = 4000000;
            //double minTotalSumInsuredUsd = 400;
            //commonValidator.validateMinimumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, totalSumInsured, minTotalSumInsuredIdr, minTotalSumInsuredUsd );
        }
        else
        {
            //errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED, "error.formEmpty", null, null );
        }
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040214Entry.validatePreviousPage" );
        commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 0, 20 );
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040214Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040214Mapper.X9 ){
        	commonValidator.validatePolicyHolderAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 55 );
        }else{
        	commonValidator.validatePolicyHolderAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 50 );
        }
       
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040214Entry.processFormAfterSubmitBeforeValidation" );
    }
    public static List<OptionVO> getPremiumCombinationList( int folded )
    {
        List<OptionVO> premiumCombinationList = new ArrayList<OptionVO>();

        OptionVO optionVO;
        optionVO = new OptionVO( "", "" );
        premiumCombinationList.add( optionVO );

        String value;
        String label;
        for( int i = 90 - folded; i > 0; i = i - folded )
        {
            value = i + ".00";
            label = "Premi Pokok " + i + "%, Premi Top-Up Berkala " + ( 100 - i ) + "%";
            optionVO = new OptionVO( value, label );
            premiumCombinationList.add( optionVO );
        }

        return premiumCombinationList;
    }

}