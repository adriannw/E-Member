package id.co.sinarmaslife.eproposal.product.product01040140;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040140
 * Program Name   		: Cepr01040140Entry
 * Description         	: Power Save New (142)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 13, 2007 10:33:45 AM
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
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000Generator;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Entry;
import id.co.sinarmaslife.standard.util.StringUtil;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cepr01040140Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface

{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040140Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        setDownloadUrl( "wepr01040140.pdf" );
    }

    public void initDisplayedForm()
    {
        displayStandardForm();
        
        cepr01030102Form.setButtonCountTotalSumInsuredDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setInvestRateInPercentDisplay( CommonConst.DISPLAY_TRUE );
        
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
        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPremiumListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPremiumListIsDisabled( CommonConst.DISABLED_FALSE );
    }

    public void initReadOnlyForm()
    {
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
    	cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_TAHUNAN );
    	cepr01030102Form.setInvestRateInPercent( new BigDecimal("10") );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        cepr01030102Form.setTermOfContract( 4 );
        cepr01030102Form.setTermOfPayment( 4 );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_BULANAN, Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_SEMESTERAN, Hardcode.PAY_MODE_CD_TRIWULANAN } ) );
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        if( cepr01030102Form.getCurrencyCd() == null )
        {
            cepr01030102Form.setCurrencyCd( Hardcode.CUR_IDR_CD );
        }
        OptionVO optionVO;

        List<OptionVO> premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 500000, 2000000, 100000 );
        cepr01030102Form.setPremiumList( premiumList );
        cepr01030102Form.setMgiList( comboLookupBusiness.getMonthOptionVOList( true, new Integer[]{ 3, 6, 12, 36 } ) );
        cepr01030102Form.setMgiCd( 12 );
        cepr01030102Form.setTaxBeforeAfterCd( Hardcode.TAX_BEFORE_CD );
        cepr01030102Form.setTargetCd( Hardcode.TARGET_PREMI_CD );
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
        if( cepr01030102Form.getPremium() != null ){
	        int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );
	        double premi = LazyConverter.toDouble( cepr01030102Form.getPremium() );
	        switch( paymentModeCd )
	        {
	            case Hardcode.PAY_MODE_CD_TRIWULANAN: premi = 500000;  break;
	            case Hardcode.PAY_MODE_CD_SEMESTERAN: premi = 1000000;  break;
	            case Hardcode.PAY_MODE_CD_BULANAN   : premi = 200000;  break;
	            case Hardcode.PAY_MODE_CD_TAHUNAN  : premi = 2000000;  break;
	            default: break;
	        }
	        commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM,  LazyConverter.toDouble( cepr01030102Form.getPremium() ), premi, -1 );
        }
        else
        {
            errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.formEmpty", null, null );
        }

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

    }

    public void validatePreviousPage()
    {
        commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 1, 68 );
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        cepr01030102Form.setTotalSumInsured( cepr01030102Form.getPremium() );
        commonUsedBusiness.processMaxTotalSumInsured( new BigDecimal( "1000000000" ), new BigDecimal( "100000" ) );
    }
}