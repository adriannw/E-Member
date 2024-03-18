package id.co.sinarmaslife.eproposal.product.product01040136;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040136
 * Program Name   		: Cepr01040136Entry
 * Description         	: Super sejahtera (39)
 * Environment      	: Java  1.5.0_06
 * Author               : Fadly Mathendra
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

public class Cepr01040136Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040136Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040136Entry constructor is called ..." );
        setDownloadUrl( "wepr01040136.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040136Entry.initDisplayedForm" );
        displayStandardForm();
//        displayAdditionalAssurance( true );
        cepr01030102Form.setButtonCountPremiumDisplay( CommonConst.DISPLAY_TRUE );
        if( Cepr01040136Mapper.X5 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040136Mapper.X10 == cepr01030102Form.getRightPartOfBusinessCd() ||
        		Cepr01040136Mapper.X15 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040136Mapper.X20 == cepr01030102Form.getRightPartOfBusinessCd())
        {
            displayAdditionalAssurance( false );
        }
        else if( Cepr01040136Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040136Mapper.X6 == cepr01030102Form.getRightPartOfBusinessCd() ||
        		Cepr01040136Mapper.X11 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040136Mapper.X16 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            displayAdditionalAssurance( true );
        }
        else if( Cepr01040136Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040136Mapper.X7 == cepr01030102Form.getRightPartOfBusinessCd() ||
        		Cepr01040136Mapper.X12 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040136Mapper.X17 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            displayAdditionalAssurance( true );
        }
        else if( Cepr01040136Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040136Mapper.X8 == cepr01030102Form.getRightPartOfBusinessCd() ||
        		Cepr01040136Mapper.X13 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040136Mapper.X18 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            displayAdditionalAssurance( true );
        }
        else if( Cepr01040136Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040136Mapper.X9 == cepr01030102Form.getRightPartOfBusinessCd() ||
        		Cepr01040136Mapper.X14 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040136Mapper.X19 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            displayAdditionalAssurance( true );
        }
        else
        {
            displayAdditionalAssurance( true );
        }

//        cepr01030102Form.setPaymentModeCd( payModeArr[ 0 ] );
        cepr01030102Form.setEkaSehatFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setTpdFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPayorsClauseFlagDisplay( CommonConst.DISPLAY_TRUE );
        if( credentialsVO != null && credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_DMTM ) ){
        	cepr01030102Form.setEmailDisplay( CommonConst.DISPLAY_TRUE );
        	cepr01030102Form.setFaxDisplay( CommonConst.DISPLAY_TRUE ); 
        }else{
        	cepr01030102Form.setEmailDisplay( CommonConst.DISPLAY_FALSE );
        	cepr01030102Form.setFaxDisplay( CommonConst.DISPLAY_FALSE ); 
        }
        // 054/AJSMSIG-SE/X/2022
        cepr01030102Form.setTermRiderFlagDisplay( CommonConst.DISPLAY_FALSE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040136Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040136Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040136Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040136Entry.fillDefaultValueEachTimeFormCalled" );

        cepr01030102Form.setTermOfContract( 79 - cepr01030101Form.getInsuredAge() );

        Integer[] payModeArr;
        if( Cepr01040136Mapper.X5 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040136Mapper.X10 == cepr01030102Form.getRightPartOfBusinessCd() ||
        		Cepr01040136Mapper.X15 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040136Mapper.X20 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            cepr01030102Form.setTermOfPayment( 1 );
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_SEKALIGUS };
        }
        else if( Cepr01040136Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040136Mapper.X6 == cepr01030102Form.getRightPartOfBusinessCd() ||
        		Cepr01040136Mapper.X11 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040136Mapper.X16 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            cepr01030102Form.setTermOfPayment( 5 );
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN };
        }
        else if( Cepr01040136Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040136Mapper.X7 == cepr01030102Form.getRightPartOfBusinessCd() ||
        		Cepr01040136Mapper.X12 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040136Mapper.X17 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            cepr01030102Form.setTermOfPayment( 10 );
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN }; 
        }
        else if( Cepr01040136Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040136Mapper.X8 == cepr01030102Form.getRightPartOfBusinessCd() ||
        		Cepr01040136Mapper.X13 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040136Mapper.X18 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            cepr01030102Form.setTermOfPayment( 15 );
        	payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN };
        }
        else if( Cepr01040136Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040136Mapper.X9 == cepr01030102Form.getRightPartOfBusinessCd() ||
        		Cepr01040136Mapper.X14 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040136Mapper.X19 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            cepr01030102Form.setTermOfPayment( 20 );
        	payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN };
        }
        else
        {
            cepr01030102Form.setTermOfPayment( 0 );
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_SEKALIGUS };
        }
        

//        cepr01030102Form.setPaymentModeCd( payModeArr[ 0 ] );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, payModeArr ) );
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        cepr01030102Form.setTermRiderUnitQtyList( LookupList.getSequenceNumberList( 1, 5, false ) );
        cepr01030102Form.setPersonalAccidentUnitQtyList( LookupList.getSequenceNumberList( 1, 3, false ) );
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040136Entry.validateCurrentPage" );

        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getTotalSumInsured().compareTo( new BigDecimal( "100000000" ) ) < 0 )
            {
                errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED, "error.valueAtLeast", new Object[]{ "Rp.100.000,000.00"}, null );
            }
            
//            if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEKALIGUS )
//            {
//                if( cepr01030102Form.getPremium().compareTo( new BigDecimal( "5000000" ) ) < 0 )
//                {
//                    errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.valueAtLeast", new Object[]{ "Rp.5,000,000.00"}, null );
//                }
//            }
//            else
//            {
//                if( cepr01030102Form.getPremium().compareTo( new BigDecimal( "1500000" ) ) < 0 )
//                {
//                    errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.valueAtLeast", new Object[]{ "Rp.1,500,000.00"}, null );
//                }
//            }
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getTotalSumInsured().compareTo( new BigDecimal( "10000" ) ) < 0 )
            {
                errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED, "error.valueAtLeast", new Object[]{ "$10,000.00"}, null );
            }
            
//            if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEKALIGUS )
//            {
//                if( cepr01030102Form.getPremium().compareTo( new BigDecimal( "500" ) ) < 0 )
//                {
//                    errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.valueAtLeast", new Object[]{ "$500.00"}, null );
//                }
//            }
//            else
//            {
//                if( cepr01030102Form.getPremium().compareTo( new BigDecimal( "150" ) ) < 0 )
//                {
//                    errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.valueAtLeast", new Object[]{ "$150.00"}, null );
//                }
//            }
        }
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040136Entry.validatePreviousPage" );

        if( cepr01030101Form.getInsuredAge() > 55 )
        {
        	 errors.rejectValue( Cepr01030102FormConst.ASSURANCE_PLAN_CD, "error.insuredAgeMax", new Object[]{ "55" }, null );
        }
        
//        if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getPayorsClauseFlag() ) )
//        {
//            if( cepr01030101Form.getInsuredAge() < 15 )
//            {
//                errors.rejectValue( Cepr01030102FormConst.PAYORS_CLAUSE_FLAG, "error.insuredAgeMin", new Object[]{ "15" }, null );
//            }
//            else if( cepr01030101Form.getPolicyHolderAge() < 20 )
//            {
//                errors.rejectValue( Cepr01030102FormConst.PAYORS_CLAUSE_FLAG, "error.policyHolderAgeMin", new Object[]{ "20" }, null );
//            }
//        }

    }

    public BigDecimal computePremium( Integer payModeCd )
    {
        logger.info( "*-*-*-* Cepr01040136Entry.computePremium" );

        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put( "leftPartOfBusinessCd", cepr01030102Form.getLeftPartOfBusinessCd() );
        sqlParams.put( "lstabLamaTanggung", new Integer("0") );
        sqlParams.put( "lsdbsNumber", cepr01030102Form.getRightPartOfBusinessCd() );
        sqlParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_PREMI );
        sqlParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
        sqlParams.put( "paymentModeCd", payModeCd );
        sqlParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
        sqlParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
        sqlParams.put( "yearNoList", new Integer[]{ new Integer( "1" ) } );
        
        List rateList = eproposalManager.selectRateNew( sqlParams );
        BigDecimal premium;
        BigDecimal rate;
        if( rateList != null && rateList.size() > 0 )
        {
            rate = ( BigDecimal ) eproposalManager.selectRateNew( sqlParams ).get( 0 );
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
        logger.info( "*-*-*-* Cepr01040136Entry.computePremium" );

        return computePremium( cepr01030102Form.getPaymentModeCd() );
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040136Entry.processFormAfterSubmitBeforeValidation" );
        BigDecimal premium = computePremium( cepr01030102Form.getPaymentModeCd() );
        logger.info( "*-*-*-* premium = " + premium );
        cepr01030102Form.setPremium( premium );
    }

}
