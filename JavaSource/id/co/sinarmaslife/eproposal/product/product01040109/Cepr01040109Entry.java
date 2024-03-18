package id.co.sinarmaslife.eproposal.product.product01040109;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040109Entry
 * Description         	: Platinum Save (155)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jan 7, 2008 2:41:23 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000EntryInterface;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Entry;
import id.co.sinarmaslife.standard.util.StringUtil;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cepr01040109Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface

{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040109Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040109Entry constructor is called ..." );
        setDownloadUrl( "wepr01040109.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040109Entry.initDisplayedForm" );
        displayStandardForm();
        cepr01030102Form.setButtonRiderAndButtonTopupDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonRiderDisplay( CommonConst.DISPLAY_TRUE );
        
        cepr01030102Form.setButtonCountTotalSumInsuredDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setInvestRateInPercentDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setMgiListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setTargetListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setTaxBeforeAfterListDisplay( CommonConst.DISPLAY_TRUE );
        
        cepr01030103Form.setPaDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setHcpDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setHcpFamilyDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setPayorTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorSpouseTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverTpdDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setTpdDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setCiDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setTermRiderDisplay( CommonConst.DISPLAY_TRUE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040109Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPremiumListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPremiumListIsDisabled( CommonConst.DISABLED_FALSE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040109Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040109Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040109Entry.fillDefaultValueEachTimeFormCalled" );

        cepr01030102Form.setTermOfContract( 4 );
        cepr01030102Form.setTermOfPayment( 1 );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_SEKALIGUS } ) );
        cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_SEKALIGUS );
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD } ) );
        if( cepr01030102Form.getCurrencyCd() == null )
        {
            cepr01030102Form.setCurrencyCd( Hardcode.CUR_IDR_CD );
        }
        List<OptionVO> premiumList = new ArrayList<OptionVO>();
        OptionVO optionVO;
        optionVO = new OptionVO( "", "" );
        premiumList.add( optionVO );

        String value;
        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            for( int i = 25; i <= 40; i++ )
            {
                value = "" + i + "0,000,000.00";
                optionVO = new OptionVO( value, value );
                premiumList.add( optionVO );
            }
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            for( int i = 25; i <= 40; i++ )
            {
                value = "" + i + "000.00";
                optionVO = new OptionVO( value, value );
                premiumList.add( optionVO );
            }
        }

        cepr01030102Form.setPremiumList( premiumList );
        cepr01030102Form.setMgiList( comboLookupBusiness.getMonthOptionVOList( true, new Integer[]{ 3, 6 } ) );

        List<OptionVO> targetList = new ArrayList<OptionVO>();
        optionVO = new OptionVO( "", "" );
        targetList.add( optionVO );
        optionVO = new OptionVO( Hardcode.TARGET_BENEFIT_CD, "Benefit" );
        targetList.add( optionVO );
        optionVO = new OptionVO( Hardcode.TARGET_PREMI_CD, "Premi" );
        targetList.add( optionVO );
        cepr01030102Form.setTargetList( targetList );

        List<OptionVO> taxBeforeAfterList = new ArrayList<OptionVO>();
        optionVO = new OptionVO( "", "" );
        taxBeforeAfterList.add( optionVO );
        optionVO = new OptionVO( Hardcode.TAX_BEFORE_CD, "Sebelum" );
        taxBeforeAfterList.add( optionVO );
        optionVO = new OptionVO( Hardcode.TAX_AFTER_CD, "Sesudah" );
        taxBeforeAfterList.add( optionVO );
        cepr01030102Form.setTaxBeforeAfterList( taxBeforeAfterList );
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040109Entry.validateCurrentPage" );

        double minPremium = 0;
        if( cepr01030102Form.getInvestRateInPercent() != null )
        {
            if( cepr01030102Form.getInvestRateInPercent().compareTo( new BigDecimal( "100" ) ) > 0 )
            {
                errors.rejectValue( Cepr01030102FormConst.INVEST_RATE_IN_PERCENT, "error.valueMax", new Object[]{ "100%" }, null );
            }
            else if( cepr01030102Form.getInvestRateInPercent().compareTo( new BigDecimal( "0" ) ) < 0 )
            {
                errors.rejectValue( Cepr01030102FormConst.INVEST_RATE_IN_PERCENT, "error.valueAtLeast", new Object[]{ "0%" }, null );
            }
        }
        else
        {
            errors.rejectValue( Cepr01030102FormConst.INVEST_RATE_IN_PERCENT, "error.formEmpty", null, null );
        }

        if( cepr01030102Form.getMgiCd() == null )
        {
            errors.rejectValue( Cepr01030102FormConst.MGI_CD, "error.formEmpty", null, null );
        }
        if( StringUtil.isEmpty( cepr01030102Form.getTargetCd() ) )
        {
            errors.rejectValue( Cepr01030102FormConst.TARGET_CD, "error.formEmpty", null, null );
        }
        if( StringUtil.isEmpty( cepr01030102Form.getTaxBeforeAfterCd() ) )
        {
            errors.rejectValue( Cepr01030102FormConst.TAX_BEFORE_AFTER_CD, "error.formEmpty", null, null );
        }

        if( errors.getFieldErrorCount() == 0 )
        {
            if( cepr01030102Form.getPremium() != null )
            {
                double premium = cepr01030102Form.getPremium().doubleValue();
                if( Cepr01040109Mapper.X1 == rightPartOfBusinessCd )
                {
                    if( cepr01030102Form.getMgiCd().equals( 3 ) )
                    {
                        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) ) minPremium = 250000000;
                        if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) ) minPremium = 25000;
                    }
                    else if( cepr01030102Form.getMgiCd().equals( 6 ) )
                    {
                        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) ) minPremium = 500000000;
                        if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) ) minPremium = 50000;
                    }
                }
                else if( Cepr01040109Mapper.X2 == rightPartOfBusinessCd || Cepr01040109Mapper.X3 == rightPartOfBusinessCd )
                {
                    if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) ) minPremium = 27500000;
                    if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) ) minPremium = 2750;
                }
                else
                {
                    throw new RuntimeException( "Cepr01040109Entry.validateCurrentPage: product not registered in validation." );
                }

                if( premium < minPremium )
                {
                    if( Cepr01040109Mapper.X1 == rightPartOfBusinessCd )
                    {
                        errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.platinumSaveX1Error", null, null );
                    }
                    else
                    {
                        errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.valueAtLeast", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( minPremium ) }, null );
                    }
                }
            }
            else
            {
                errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.formEmpty", null, null );
            }
        }

    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040109Entry.validatePreviousPage" );
        commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 1, 68 );
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040109Entry.processFormAfterSubmitBeforeValidation" );
        cepr01030102Form.setTotalSumInsured( cepr01030102Form.getPremium() );
        commonUsedBusiness.processMaxTotalSumInsured( new BigDecimal( "1000000000" ), new BigDecimal( "100000" ) );
    }
}