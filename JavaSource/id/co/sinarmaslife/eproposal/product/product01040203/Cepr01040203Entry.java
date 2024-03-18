package id.co.sinarmaslife.eproposal.product.product01040203;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040203Entry
 * Description         	: Artha/Eka Link 88 (162)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jan 31, 2008 9:56:21 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000Generator;
import id.co.sinarmaslife.eproposal.common.lookup.LookupList;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.eproposal.product.product01040201.Cepr01040201Entry;
import id.co.sinarmaslife.eproposal.model.vo.UlinkValidatorParVO;
import id.co.sinarmaslife.eproposal.model.vo.AmountVO;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.LazyConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.util.List;
import java.math.BigDecimal;

public class Cepr01040203Entry extends Cepr01040201Entry
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040203Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040203Entry constructor is called ..." );
        setDownloadUrl( "wepr01040203.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040203Entry.initDisplayedForm" );
        super.initDisplayedForm();
        if(    Cepr01040203Mapper.X5 == rightPartOfBusinessCd
            || Cepr01040203Mapper.X7 == rightPartOfBusinessCd)
        {
            cepr01030103Form.setPayorTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setPayorCiDeathDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setPayorCiDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setPayorSpouseTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setWaiverTpdDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setWaiverCiDisplay( CommonConst.DISPLAY_FALSE );
        }
        else if(    Cepr01040203Mapper.X6 == rightPartOfBusinessCd
                 || Cepr01040203Mapper.X8 == rightPartOfBusinessCd)
        {
        }
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040203Entry.initDisabledForm" );
        super.initDisabledForm();
        if(    Cepr01040203Mapper.X5 == rightPartOfBusinessCd
            || Cepr01040203Mapper.X7 == rightPartOfBusinessCd)
        {
            cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_TRUE );
        }
        else if(    Cepr01040203Mapper.X6 == rightPartOfBusinessCd
                 || Cepr01040203Mapper.X8 == rightPartOfBusinessCd )
        {
            cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_FALSE );
        }

        if( Cepr01040203Mapper.X1 == rightPartOfBusinessCd
            || Cepr01040203Mapper.X5 == rightPartOfBusinessCd
            || Cepr01040203Mapper.X7 == rightPartOfBusinessCd)
        {
            cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
        }
        else if( Cepr01040203Mapper.X2 == rightPartOfBusinessCd
                || Cepr01040203Mapper.X6 == rightPartOfBusinessCd
                || Cepr01040203Mapper.X8 == rightPartOfBusinessCd )
        {
            cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
        }
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040203Entry.initReadOnlyForm" );
        super.initReadOnlyForm();
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040203Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        if( Cepr01040203Mapper.X1 == rightPartOfBusinessCd
            || Cepr01040203Mapper.X5 == rightPartOfBusinessCd
            || Cepr01040203Mapper.X7 == rightPartOfBusinessCd )
        {
            cepr01030102Form.setPremiumFurloughYear( 1 );
            cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_SEKALIGUS );
        }
        else if( Cepr01040203Mapper.X2 == rightPartOfBusinessCd
                || Cepr01040203Mapper.X6 == rightPartOfBusinessCd
                || Cepr01040203Mapper.X8 == rightPartOfBusinessCd )
        {
            cepr01030102Form.setPremiumFurloughYear( 10 );
            cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_TAHUNAN );
        }
        cepr01030102Form.setTotalSumInsured( new BigDecimal( "0" ) );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040203Entry.fillDefaultValueEachTimeFormCalled" );
        super.fillDefaultValueEachTimeFormCalled();
        cepr01030102Form.setTermOfContract( 88 - cepr01030101Form.getInsuredAge() );
        List<OptionVO> premiumList = null;
        if( Cepr01040203Mapper.X1 == rightPartOfBusinessCd )
        {
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 10000000, 25000000, 1000000 );
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 1000, 2500, 100 );
            }
        }
        else if( Cepr01040203Mapper.X2 == rightPartOfBusinessCd )
        {
            cepr01030102Form.setTermOfPayment( 88 - cepr01030101Form.getInsuredAge() );
            cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination5FoldedList() );
            cepr01030102Form.getPremiumCombinationList().remove( 1 );

            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 3000000, 20000000, 1000000 );
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 300, 2000, 100 );
            }
        }
        if( Cepr01040203Mapper.X5 == rightPartOfBusinessCd
            || Cepr01040203Mapper.X7 == rightPartOfBusinessCd )
        {
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 10000000, 25000000, 1000000 );
            }
        }
        else if( Cepr01040203Mapper.X6 == rightPartOfBusinessCd
                 || Cepr01040203Mapper.X8 == rightPartOfBusinessCd )
        {
            cepr01030102Form.setTermOfPayment( 88 - cepr01030101Form.getInsuredAge() );
            cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination5FoldedList() );
            cepr01030102Form.getPremiumCombinationList().remove( 1 );

            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 3000000, 20000000, 1000000 );
            }
        }
        cepr01030102Form.setPremiumList( premiumList );

        if( Cepr01040203Mapper.X5 == rightPartOfBusinessCd
            || Cepr01040203Mapper.X7 == rightPartOfBusinessCd )
        {
            cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
            cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_SEKALIGUS } ) );
            cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_TRUE );
            cepr01030102Form.setTermOfPayment( 1 );
            cepr01030102Form.setPremiumFurloughYearIsDisabled( CommonConst.DISABLED_TRUE );
            cepr01030102Form.setPremiumFurloughYearListDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030102Form.setPremiumCombinationCd( new BigDecimal( "100" ) );
            cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination10FoldedList().subList( 1, 2 ) );
            cepr01030102Form.setCurrencyCd( Hardcode.CUR_IDR_CD );
        }
        else if( Cepr01040203Mapper.X6 == rightPartOfBusinessCd
                || Cepr01040203Mapper.X8 == rightPartOfBusinessCd )
        {
            cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
            cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_SEMESTERAN, Hardcode.PAY_MODE_CD_TRIWULANAN, Hardcode.PAY_MODE_CD_BULANAN } ) );
            cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE );
            cepr01030102Form.setTermOfPayment( 80 - cepr01030101Form.getInsuredAge() );
            cepr01030102Form.setPremiumFurloughYearIsDisabled( CommonConst.DISABLED_FALSE );
            cepr01030102Form.setPremiumFurloughYearListDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setCurrencyCd( Hardcode.CUR_IDR_CD );
        }
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040203Entry.validateCurrentPage" );
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
        logger.info( "*-*-*-* Cepr01040203Entry.validatePreviousPage" );
        commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 0, 70 );
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040203Entry.processFormAfterSubmitBeforeValidation" );
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
        refreshBaseTopupPremium();

        logger.info( "*-*-*-* cepr01030102Form.getTheEvent() = " + cepr01030102Form.getTheEvent() );
        if( "onChangePremium".equals( cepr01030102Form.getTheEvent() )
            || "onPressButtonCountTotalSumInsured".equals( cepr01030102Form.getTheEvent() )
            || "onPressButtonPreviewProposal".equals( cepr01030102Form.getTheEvent() )
            || cepr01030102Form.getTotalSumInsured().equals( new BigDecimal( "0" ) ) )
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

            if(    Cepr01040203Mapper.X1 == rightPartOfBusinessCd
                || Cepr01040203Mapper.X5 == rightPartOfBusinessCd
                || Cepr01040203Mapper.X7 == rightPartOfBusinessCd )    
            {
                // I got it from n_prod_159 function of_set_premi
                idec_rate = 1250;
                idec_up = adec_premi * idec_rate / 1000;
                if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                {
                    idec_up = Math.max( idec_up, 15000000 );
                }
                else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                {
                    idec_up = Math.max( idec_up, 1500 );
                }
            }
            else if(   Cepr01040203Mapper.X2 == rightPartOfBusinessCd
                    || Cepr01040203Mapper.X6 == rightPartOfBusinessCd
                    || Cepr01040203Mapper.X8 == rightPartOfBusinessCd )
            {
                // I got it from n_prod_159 function of_set_premi
                idec_rate = 5000;
                idec_up = adec_premi * idec_rate / 1000;
            }
            // count totalSumInsured with premiumCombinationPercent
            idec_up = idec_up * premiumCombinationPercent / 100;
            cepr01030102Form.setTotalSumInsured( new BigDecimal( idec_up ) );
        }
    }
}
