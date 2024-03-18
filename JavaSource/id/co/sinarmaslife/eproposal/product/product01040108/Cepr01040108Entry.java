package id.co.sinarmaslife.eproposal.product.product01040108;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040108
 * Program Name   		: Cepr01040108Entry
 * Description         	: Power Save Bulanan (158)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 16, 2007 1:56:28 PM
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
import id.co.sinarmaslife.eproposal.common.lookup.LookupList;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Entry;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.standard.util.StringUtil;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cepr01040108Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface

{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040108Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040108Entry constructor is called ..." );
        setDownloadUrl( "wepr01040108.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040108Entry.initDisplayedForm" );
        displayStandardForm();
        cepr01030102Form.setButtonRiderAndButtonTopupDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonRiderDisplay( CommonConst.DISPLAY_TRUE );
        
        cepr01030102Form.setButtonCountTotalSumInsuredDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setInvestRateInPercentDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setMgiListDisplay( CommonConst.DISPLAY_TRUE );
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
        cepr01030103Form.setHcpProviderDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setHcpLadiesDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorTpdCiDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setLadiesInsDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverTpdCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setEkaSehatDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setEkaSehatInnerLimitDisplay(CommonConst.DISPLAY_TRUE);
        
        if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) ){
       	 	cepr01030103Form.setEkaSehatDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setEkaSehatInnerLimitDisplay( CommonConst.DISPLAY_FALSE );
       }    
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040108Entry.initDisabledForm" );

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
        logger.info( "*-*-*-* Cepr01040108Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040108Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040108Entry.fillDefaultValueEachTimeFormCalled" );

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
            for( int i = 10; i <= 25; i++ )
            {
                value = "" + i + "0,000,000.00";
                optionVO = new OptionVO( value, value );
                premiumList.add( optionVO );
            }
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            for( int i = 10; i <= 25; i++ )
            {
                value = "" + i + ",000.00";
                optionVO = new OptionVO( value, value );
                premiumList.add( optionVO );
            }
        }

        cepr01030102Form.setPremiumList( premiumList );
        cepr01030102Form.setMgiList( comboLookupBusiness.getMonthOptionVOList( true, new Integer[]{ 12, 24, 36 } ) );

        List<OptionVO> taxBeforeAfterList = new ArrayList<OptionVO>();
        optionVO = new OptionVO( "","" ); taxBeforeAfterList.add( optionVO );
        optionVO = new OptionVO( Hardcode.TAX_BEFORE_CD, "Sebelum" ); taxBeforeAfterList.add( optionVO );
        optionVO = new OptionVO( Hardcode.TAX_AFTER_CD, "Sesudah" ); taxBeforeAfterList.add( optionVO );
        cepr01030102Form.setTaxBeforeAfterList( taxBeforeAfterList );
            
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040108Entry.validateCurrentPage" );

        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double minPremiumIdr;
        double minPremiumUsd;
        Integer rightPartOfBusinessCd = cepr01030102Form.getRightPartOfBusinessCd();
        if( Cepr01040108Mapper.X5 == rightPartOfBusinessCd )
        {
            minPremiumIdr = 500000000;
            minPremiumUsd = 50000;
        }
        else
        {
            minPremiumIdr = 100000000;
            minPremiumUsd = 10000;
        }

        if( cepr01030102Form.getPremium() != null )
        {
            commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );
        }
        else
        {
            errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.formEmpty", null, null );
        }

        if( cepr01030102Form.getInvestRateInPercent() != null )
        {
            if( cepr01030102Form.getInvestRateInPercent().compareTo( new BigDecimal( "100" ) ) > 0 )
            {
                errors.rejectValue( Cepr01030102FormConst.INVEST_RATE_IN_PERCENT, "error.valueMax", new Object[]{ "100%"}, null );
            }
            else if( cepr01030102Form.getInvestRateInPercent().compareTo( new BigDecimal( "0" ) ) < 0 )
            {
                errors.rejectValue( Cepr01030102FormConst.INVEST_RATE_IN_PERCENT, "error.valueAtLeast", new Object[]{ "0%"}, null );
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
        
        if( StringUtil.isEmpty( cepr01030102Form.getTaxBeforeAfterCd() ) )
        {
            errors.rejectValue( Cepr01030102FormConst.TAX_BEFORE_AFTER_CD, "error.formEmpty", null, null );
        }
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040108Entry.validatePreviousPage" );
        commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 1, 68 );
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040108Entry.processFormAfterSubmitBeforeValidation" );
        cepr01030102Form.setTotalSumInsured( cepr01030102Form.getPremium() );
        commonUsedBusiness.processMaxTotalSumInsured( new BigDecimal( "1000000000" ), new BigDecimal( "100000" ) );
    }     
  
    
}