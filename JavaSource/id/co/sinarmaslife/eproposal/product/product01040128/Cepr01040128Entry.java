package id.co.sinarmaslife.eproposal.product.product01040128;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040128
 * Program Name   		: Cepr01040128Entry
 * Description         	: Hidup Bahagia (167)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 25, 2007 9:18:21 AM
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
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Entry;
import id.co.sinarmaslife.standard.util.LazyConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040128Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );
    public static int INSURED_AGE_LIMIT = 99;

    public Cepr01040128Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    { 
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040128Entry constructor is called ..." );
        setDownloadUrl( "wepr01040128.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040128Entry.initDisplayedForm" );
        displayStandardForm();
        cepr01030102Form.setButtonCountPremiumDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setInvestRateInPercentDisplay( CommonConst.DISPLAY_TRUE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040128Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE  );
        cepr01030102Form.setWaiverPremiumDisabilityFlagIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPayorsClauseFlagIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setInvestRateInPercentIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040128Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040128Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        
        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
        	cepr01030102Form.setTotalSumInsured( new BigDecimal( "100000000" ) );	
        	cepr01030102Form.setInvestRateInPercent( new BigDecimal("9") );
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
        	cepr01030102Form.setTotalSumInsured( new BigDecimal( "10000" ) );	
        	cepr01030102Form.setInvestRateInPercent( new BigDecimal("5") );
        }    

        int termOfPayment;
        int rightPartOfBusinessCd = LazyConverter.toInt( cepr01030102Form.getRightPartOfBusinessCd() );
        switch( rightPartOfBusinessCd )
        {
            case Cepr01040128Mapper.X1: termOfPayment = 10; break;
            case Cepr01040128Mapper.X2: termOfPayment = 15; break;
            case Cepr01040128Mapper.X3: termOfPayment = 20; break;
            case Cepr01040128Mapper.X4: termOfPayment = 1; break;
            default: throw new RuntimeException( "*** rightPartOfBusinessCd not listed in Cepr01040128Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        }
        
        cepr01030102Form.setTermOfPayment( termOfPayment );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040128Entry.fillDefaultValueEachTimeFormCalled" );
        
        cepr01030102Form.setTermOfContract( INSURED_AGE_LIMIT - cepr01030101Form.getInsuredAge() );
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( true, new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD } ) );
        cepr01030102Form.setTermRiderUnitQtyList( LookupList.getSequenceNumberList( 1, 5, false ) );
        cepr01030102Form.setPersonalAccidentUnitQtyList( LookupList.getSequenceNumberList( 1, 3, false ) );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE );

        cepr01030102Form.setPayorsClauseFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPayorsClauseFlag( CommonConst.CHECKED_FALSE );
        cepr01030102Form.setWaiverPremiumDisabilityFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setWaiverPremiumDisabilityFlag( CommonConst.CHECKED_FALSE );
        if( "onChangeCurrencyCd".equals( cepr01030102Form.getTheEvent()) && Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
        	cepr01030102Form.setTotalSumInsured( new BigDecimal( "100000000" ) );	
        	cepr01030102Form.setInvestRateInPercent( new BigDecimal("9") );
        }
        else if( "onChangeCurrencyCd".equals( cepr01030102Form.getTheEvent()) && Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
        	cepr01030102Form.setTotalSumInsured( new BigDecimal( "10000" ) );	
        	cepr01030102Form.setInvestRateInPercent( new BigDecimal("5") );
        }        
        
        Integer[] payModeArr = null;

        displayAdditionalAssurance( false );
        if( Cepr01040128Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_SEKALIGUS };
        }
        else
        {
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN };
        }
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, payModeArr ) );

        cepr01030102Form.setPremium( computePremium( cepr01030102Form.getPaymentModeCd() ) );
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040128Entry.validateCurrentPage" );
        commonValidator.validateMinimumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, cepr01030102Form.getTotalSumInsured(), new BigDecimal( "100000000" ), new BigDecimal( "10000" ) );
        if( Cepr01040128Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
        }
        // Berkala
        else
        {
            // check age for rider waiver is a must
            if( commonUsedBusiness.policyHolderIsInsured() )
            {
                commonValidator.validateInsuredIntervalAge( Cepr01030102FormConst.WAIVER_PREMIUM_DISABILITY_FLAG, 17, 55 - cepr01030102Form.getTermOfPayment() );
            }
            else
            {
                
            }
        }
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040128Entry.validatePreviousPage" );
        if( commonValidator.validatePolicyHolderIntervalAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 85 ) )
        {
        	int maxAssuredAge = 55;
            commonValidator.validateInsuredIntervalAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 1, maxAssuredAge );
        }
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040128Entry.processFormAfterSubmitBeforeValidation" );
        cepr01030102Form.setPremium( computePremium( cepr01030102Form.getPaymentModeCd() ) );
    }

    public BigDecimal computePremium( Integer payModeCd )
    {
        logger.info( "*-*-*-* Cepr01040128Entry.computePremium" );
        Integer termOfPayment = null;
        //TODO
        if( cepr01030102Form.getTermOfPayment() != null )
        {
        	if( cepr01030102Form.getPaymentModeCd() != null && cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEKALIGUS )
        	{
        		termOfPayment = 1;
        	}
        	else if( cepr01030102Form.getPaymentModeCd() != null && cepr01030102Form.getPaymentModeCd() != Hardcode.PAY_MODE_CD_SEKALIGUS )
        	{
        		termOfPayment = cepr01030102Form.getTermOfPayment();
        	}
        }

        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put( "leftPartOfBusinessCd", cepr01030102Form.getLeftPartOfBusinessCd() );
        sqlParams.put( "lstabLamaTanggung", 0 );
        sqlParams.put( "lstabLamaBayar", cepr01030102Form.getTermOfPayment() );
        sqlParams.put( "lsdbsNumber", cepr01030102Form.getRightPartOfBusinessCd() );
        sqlParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_PREMI );
        sqlParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
        sqlParams.put( "paymentModeCd", payModeCd );
        sqlParams.put( "termOfPayment", termOfPayment );
        sqlParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
        sqlParams.put( "yearNoList", new Integer[]{ new Integer( "1" ) } );
        List rateList = eproposalManager.selectRateNew( sqlParams );
        BigDecimal premium;
        BigDecimal rate;
        if( rateList != null && rateList.size() > 0 )
        {
            rate = eproposalManager.selectRateNew( sqlParams ).get( 0 );
            premium = computePremiumWithSimpleMethod( cepr01030102Form.getTotalSumInsured(), rate, new BigDecimal( "1" ) );
        }
        else
        {
            premium = new BigDecimal( "0" );
        }

        return premium;
    }

}
