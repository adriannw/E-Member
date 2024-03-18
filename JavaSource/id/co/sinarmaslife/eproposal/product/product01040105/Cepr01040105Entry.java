package id.co.sinarmaslife.eproposal.product.product01040105;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040105
 * Program Name   		: Cepr01040105Entry
 * Description         	: Super sejahtera (39)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 8, 2007 2:10:04 PM
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
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Entry;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040105Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040105Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040105Entry constructor is called ..." );
        setDownloadUrl( "wepr01040105.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040105Entry.initDisplayedForm" );
        displayStandardForm();
        displayAdditionalAssurance( true );
        cepr01030102Form.setButtonCountPremiumDisplay( CommonConst.DISPLAY_TRUE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040105Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040105Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040105Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040105Entry.fillDefaultValueEachTimeFormCalled" );

        cepr01030102Form.setTermOfContract( 79 - cepr01030101Form.getInsuredAge() );

        Integer[] payModeArr;
        if( Hardcode.ASSURANCE_PLAN_CD_SUPER_SEJAHTERA_SEKALIGUS.equals( cepr01030102Form.getAssurancePlanCd() ) )
        {
            cepr01030102Form.setTermOfPayment( 1 );
            displayAdditionalAssurance( false );
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_SEKALIGUS };
        }
        else if( Hardcode.ASSURANCE_PLAN_CD_SUPER_SEJAHTERA_5.equals( cepr01030102Form.getAssurancePlanCd() ) )
        {
            cepr01030102Form.setTermOfPayment( 5 );
            displayAdditionalAssurance( true );
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN };
        }
        else if( Hardcode.ASSURANCE_PLAN_CD_SUPER_SEJAHTERA_10.equals( cepr01030102Form.getAssurancePlanCd() ) )
        {
            cepr01030102Form.setTermOfPayment( 10 );
            displayAdditionalAssurance( true );
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN };
        }
        else if( Hardcode.ASSURANCE_PLAN_CD_SUPER_SEJAHTERA_15.equals( cepr01030102Form.getAssurancePlanCd() ) )
        {
            cepr01030102Form.setTermOfPayment( 15 );
            displayAdditionalAssurance( true );
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN };
        }
        else if( Hardcode.ASSURANCE_PLAN_CD_SUPER_SEJAHTERA_20.equals( cepr01030102Form.getAssurancePlanCd() ) )
        {
            cepr01030102Form.setTermOfPayment( 20 );
            displayAdditionalAssurance( true );
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN };
        }
        else
        {
            cepr01030102Form.setTermOfPayment( 0 );
            displayAdditionalAssurance( true );
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_SEKALIGUS };
        }

        cepr01030102Form.setTpdFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPaymentModeCd( payModeArr[ 0 ] );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, payModeArr ) );
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD } ) );
        cepr01030102Form.setTermRiderUnitQtyList( LookupList.getSequenceNumberList( 1, 5, false ) );
        cepr01030102Form.setPersonalAccidentUnitQtyList( LookupList.getSequenceNumberList( 1, 3, false ) );
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040105Entry.validateCurrentPage" );

        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getTotalSumInsured().compareTo( new BigDecimal( "10000000" ) ) < 0 )
            {
                errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED, "error.valueAtLeast", new Object[]{ "Rp.10.000,000.00"}, null );
            }
            if( cepr01030102Form.getPremium().compareTo( new BigDecimal( "300000" ) ) < 0 )
            {
                errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.valueAtLeast", new Object[]{ "Rp.300,000.00"}, null );
            }
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getTotalSumInsured().compareTo( new BigDecimal( "6000" ) ) < 0 )
            {
                errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED, "error.valueAtLeast", new Object[]{ "$6,000.00"}, null );
            }
        }
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040105Entry.validatePreviousPage" );

        if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getPayorsClauseFlag() ) )
        {
            if( cepr01030101Form.getInsuredAge() < 15 )
            {
                errors.rejectValue( Cepr01030102FormConst.PAYORS_CLAUSE_FLAG, "error.insuredAgeMin", new Object[]{ "15" }, null );
            }
            else if( cepr01030101Form.getPolicyHolderAge() < 20 )
            {
                errors.rejectValue( Cepr01030102FormConst.PAYORS_CLAUSE_FLAG, "error.policyHolderAgeMin", new Object[]{ "20" }, null );
            }
        }
    }

    public BigDecimal computePremium( Integer payModeCd )
    {
        logger.info( "*-*-*-* Cepr01040105Entry.computePremium" );

        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put( "leftPartOfBusinessCd", new Integer( "39" ) );
        sqlParams.put( "lstabLamaTanggung", new Integer( "79" ) );
        sqlParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_PREMI );
        sqlParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
        sqlParams.put( "paymentModeCd", payModeCd );
        sqlParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
        sqlParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
        sqlParams.put( "yearNoList", new Integer[]{ new Integer( "1" ) } );
        logger.info( "*-*-*-* sqlParams = " + sqlParams );
        List rateList = eproposalManager.selectRate( sqlParams );
        BigDecimal premium;
        BigDecimal rate;
        if( rateList != null && rateList.size() > 0 )
        {
            rate = ( BigDecimal ) eproposalManager.selectRate( sqlParams ).get( 0 );
            premium = computePremiumWithSimpleMethod( cepr01030102Form.getTotalSumInsured(), rate,  new BigDecimal( "1" ) );
        }
        else
        {
            premium = new BigDecimal( "0" );
        }

        return premium;
    }

    public BigDecimal computePremium( Object obj )
    {
        logger.info( "*-*-*-* Cepr01040105Entry.computePremium" );

        return computePremium( cepr01030102Form.getPaymentModeCd() );
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040105Entry.processFormAfterSubmitBeforeValidation" );
        BigDecimal premium = computePremium( null );
        logger.info( "*-*-*-* premium = " + premium );
        cepr01030102Form.setPremium( premium );
    }

}
