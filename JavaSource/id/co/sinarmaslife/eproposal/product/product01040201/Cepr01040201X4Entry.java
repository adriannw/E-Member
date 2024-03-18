package id.co.sinarmaslife.eproposal.product.product01040201;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040201
 * Program Name   		: Cepr01040201X4Entry
 * Description         	: Entry Utk Trans Batavia
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jun 30, 2008 8:43:04 AM
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
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.LazyConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.List;

public class Cepr01040201X4Entry extends Cepr01040000UlinkEntry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040201X4Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        setDownloadUrl( "wepr01040201.pdf" );
    }

    public void initDisplayedForm()
    {
        displayStandardForm();
        cepr01030102Form.setPremiumListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setHeaderInvestChoiceDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumCombinationListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonCountTotalSumInsuredDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPremiumFurloughYearDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonRiderAndButtonTopupDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonRiderDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonTopupDisplay( CommonConst.DISPLAY_FALSE );

        cepr01030102Form.setBasePremiumDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setTopUpPremiumDisplay( CommonConst.DISPLAY_TRUE );

        cepr01030102Form.setFixIdrDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setDynamicIdrDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setAggresiveIdrDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPremiumFurloughYearListDisplay( CommonConst.DISPLAY_FALSE );

        // set rider off except HCP Plan
        cepr01030103Form.setPaDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setHcpDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setHcpFamilyDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorSpouseTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverTpdDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setTpdDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setTermRiderDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setBabyDisplay(CommonConst.DISPLAY_TRUE);
    }

    public void initDisabledForm()
    {
        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPremiumListIsDisabled( CommonConst.DISABLED_FALSE );

        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_TRUE );

        cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
        
        cepr01030102Form.setPremiumFurloughYearIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setDynamicIdrIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void initReadOnlyForm()
    {
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        cepr01030102Form.setPremiumFurloughYear( 10 );
        cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_TAHUNAN );
        cepr01030102Form.setPremium( new BigDecimal( "125000" ) );
        cepr01030102Form.setTotalSumInsured( new BigDecimal( "10000000" ) );
        cepr01030103Form.setHcpCd( 1 );
        cepr01030103Form.setHcpFlag( CommonConst.CHECKED_TRUE );
        cepr01030103Form.setHcpFlagIsDisabled( CommonConst.DISABLED_TRUE );

    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        cepr01030102Form.setPremiumOptionIsToRefreshPage( CommonConst.REFRESH_TRUE );
        cepr01030102Form.setFixIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setDynamicIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setAggresiveIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setSecureUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setDynamicUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination10FoldedList() );
        cepr01030102Form.setPremiumFurloughYearList( LookupList.getSequenceNumberList( 0, 20, 5, false ) );

        cepr01030102Form.setTermOfContract( 80 - cepr01030101Form.getInsuredAge() );

        List<OptionVO> premiumList;

        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD } ) );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_SEMESTERAN, Hardcode.PAY_MODE_CD_TRIWULANAN, Hardcode.PAY_MODE_CD_BULANAN } ) );
        cepr01030102Form.setTermOfPayment( 80 - cepr01030101Form.getInsuredAge() );

        premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 125000, 20000000, 125000 );

        if( cepr01030102Form.getCurrencyCd() == null )
        {
            cepr01030102Form.setCurrencyCd( Hardcode.CUR_IDR_CD );
        }

        cepr01030102Form.setPremiumList( premiumList );

        // Berkala 40%
        cepr01030102Form.setPremiumCombinationCd( new BigDecimal( 100 - 40 ) );
        cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_BULANAN );
        cepr01030103Form.setHcpCd( 1 );
        cepr01030102Form.setFixIdr( new BigDecimal( "0" ) );
        cepr01030102Form.setDynamicIdr( new BigDecimal( "100" ) );
        cepr01030102Form.setAggresiveIdr( new BigDecimal( "0" ) );
        refreshBaseTopupPremium();
    }

    public void validateCurrentPage()
    {
        UlinkValidatorParVO parVO = new UlinkValidatorParVO();
        parVO.setUnder5YearsBerkalaMaxBasePremiumVO( new AmountVO( 1000000000, 100000 ) );
        parVO.setUnder5YearsSekaligusMaxBasePremiumVO( new AmountVO( 1000000000, 100000 ) );
        parVO.setBeyond5YearsBerkalaMaxBasePremiumVO( new AmountVO( 1000000000, 100000 ) );
        parVO.setBeyond5YearsSekaligusMaxBasePremiumVO( new AmountVO( 1000000000, 100000 ) );
        super.validateCurrentPage( parVO );

        validateCurrentPageInCommon();
    }

    public void validatePreviousPage()
    {
        if( commonValidator.validatePolicyHolderAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 85 ) )
        {
            commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 1, 59 );
        }
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
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
        }
    }

    public void validateCurrentPageInCommon()
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
                minPremiumIdr = 3000000;
                minPremiumUsd = 300;
                break;
            case Hardcode.PAY_MODE_CD_SEMESTERAN:
                minPremiumIdr = 1500000;
                minPremiumUsd = 150;
                break;
            case Hardcode.PAY_MODE_CD_TRIWULANAN:
                minPremiumIdr = 750000;
                minPremiumUsd = 75;
                break;
            case Hardcode.PAY_MODE_CD_BULANAN:
                minPremiumIdr = 125000;
                minPremiumUsd = 50;
                break;
            default:
                throw new RuntimeException( "*-*-*-* cepr01030102Form.getPaymentModeCd() not listed in Cepr01040201X4Entry.validateCurrentPage" );
        }

        if( cepr01030102Form.getPremium() != null )
        {
            commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );
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
        
        if((cepr01030102Form.getPremium().compareTo( new BigDecimal(149999) ) > 0) && (cepr01030102Form.getPremium().compareTo( new BigDecimal(250001)) < 0 ) ){
     	   if(cepr01030102Form.getEducationPackageCd()>1){
     	   errors.rejectValue( Cepr01030102FormConst.EDUCATION_PACKAGE, "error.thisProductJustRider", new Object[]{"PA"}, null );
         }
        }
    }
}
