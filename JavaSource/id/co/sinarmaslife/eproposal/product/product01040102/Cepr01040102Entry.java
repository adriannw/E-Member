package id.co.sinarmaslife.eproposal.product.product01040102;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040102
 * Program Name   		: Cepr01040102Entry
 * Description         	: Super Sehat (53)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 16, 2007 1:43:18 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000EntryInterface;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Entry;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cepr01040102Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040102Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors);
        logger.info( "*-*-*-* Cepr01040102Entry constructor is called ..." );
        setDownloadUrl( "wepr01040102.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040102Entry.initDisplayedForm" );
        displayStandardForm();
        cepr01030102Form.setButtonCountPremiumDisplay( CommonConst.DISPLAY_FALSE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040102Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040102Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040102Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040102Entry.fillDefaultValueEachTimeFormCalled" );

        cepr01030102Form.setTermOfContract( 1 );
        cepr01030102Form.setTermOfPayment( 1 );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN } ) );

        String currencyCd = cepr01030102Form.getCurrencyCd();
        BigDecimal totalSumInsured;
        Integer rightPartOfBusinessCd = cepr01030102Form.getRightPartOfBusinessCd();
        if( rightPartOfBusinessCd.equals( Cepr01040102Mapper.X1 ) )       { totalSumInsured =  currencyCd.equals( Hardcode.CUR_IDR_CD )? new BigDecimal( "100000" ) : new BigDecimal( "50" ); cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_FALSE ); cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD } ) ); }
        else if( rightPartOfBusinessCd.equals( Cepr01040102Mapper.X2 ) )  { totalSumInsured =  currencyCd.equals( Hardcode.CUR_IDR_CD )? new BigDecimal( "200000" ) : new BigDecimal( "100" ); cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_FALSE ); cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD } ) ); }
        else if( rightPartOfBusinessCd.equals( Cepr01040102Mapper.X3 ) )  { totalSumInsured =  currencyCd.equals( Hardcode.CUR_IDR_CD )? new BigDecimal( "300000" ) : new BigDecimal( "150" ); cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_FALSE ); cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD } ) ); }
        else if( rightPartOfBusinessCd.equals( Cepr01040102Mapper.X4 ) )  { totalSumInsured =  currencyCd.equals( Hardcode.CUR_IDR_CD )? new BigDecimal( "400000" ) : new BigDecimal( "200" ); cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_FALSE ); cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD } ) ); }
        else if( rightPartOfBusinessCd.equals( Cepr01040102Mapper.X5 ) )  { totalSumInsured = new BigDecimal( "500000" ); cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_TRUE );  cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) ); cepr01030102Form.setCurrencyCd( Hardcode.CUR_IDR_CD ); }
        else if( rightPartOfBusinessCd.equals( Cepr01040102Mapper.X6 ) )  { totalSumInsured = new BigDecimal( "600000" ); cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_TRUE );  cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) ); cepr01030102Form.setCurrencyCd( Hardcode.CUR_IDR_CD ); }
        else if( rightPartOfBusinessCd.equals( Cepr01040102Mapper.X7 ) )  { totalSumInsured = new BigDecimal( "700000" ); cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_TRUE );  cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) ); cepr01030102Form.setCurrencyCd( Hardcode.CUR_IDR_CD ); }
        else if( rightPartOfBusinessCd.equals( Cepr01040102Mapper.X8 ) )  { totalSumInsured = new BigDecimal( "800000" ); cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_TRUE );  cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) ); cepr01030102Form.setCurrencyCd( Hardcode.CUR_IDR_CD ); }
        else if( rightPartOfBusinessCd.equals( Cepr01040102Mapper.X9 ) )  { totalSumInsured = new BigDecimal( "900000" ); cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_TRUE );  cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) ); cepr01030102Form.setCurrencyCd( Hardcode.CUR_IDR_CD ); }
        else if( rightPartOfBusinessCd.equals( Cepr01040102Mapper.X10 ) ) { totalSumInsured = new BigDecimal("1000000" ); cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_TRUE );  cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) ); cepr01030102Form.setCurrencyCd( Hardcode.CUR_IDR_CD ); }
        else { totalSumInsured = new BigDecimal( "0" ); }

        cepr01030102Form.setTotalSumInsured( totalSumInsured );
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040102Entry.validateCurrentPage" );

        if( new BigDecimal( "0" ).equals( cepr01030102Form.getPremium() ) )
        {
            errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.cannotZero", null, null );
        }
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040102Entry.validatePreviousPage" );

        if( !commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 1, 59 ) )
        {
            errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.premiumIsNotValidBecauseInsuredAge", null, null );
        }
    }

    public BigDecimal computePremium( Object clazzCdIns )
    {
        logger.info( "*-*-*-* Cepr01040102Entry.computePremium" );

        BigDecimal premium;
        Map<String, Object> sqlParams = new HashMap<String, Object>();
        Integer leftPartBusinessCd = cepr01030102Form.getLeftPartOfBusinessCd();
        Integer rightPartBusinessCd = cepr01030102Form.getRightPartOfBusinessCd();
        Integer insuredAge = cepr01030101Form.getInsuredAge();
        String currencyCd = cepr01030102Form.getCurrencyCd();
        sqlParams.put( "leftPartBusinessCd", leftPartBusinessCd );
        sqlParams.put( "rightPartBusinessCd", rightPartBusinessCd );
        sqlParams.put( "insuredAge", insuredAge );
        sqlParams.put( "currencyCd", currencyCd );
        premium = eproposalManager.selectProduct53Rate( sqlParams );

        return premium;
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040102Entry.processFormAfterSubmitBeforeValidation" );
        BigDecimal premium = computePremium( null );
        logger.info( "*-*-*-* premium = " + premium );
        cepr01030102Form.setPremium( premium );
    }
}
