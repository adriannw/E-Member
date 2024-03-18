package id.co.sinarmaslife.eproposal.product.product01040110;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040110
 * Program Name   		: Cepr01040110Entry
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

public class Cepr01040110Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );
    public static int INSURED_AGE_LIMIT = 70;

    public Cepr01040110Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040110Entry constructor is called ..." );
        setDownloadUrl( "wepr01040110.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040110Entry.initDisplayedForm" );
        displayStandardForm();
        cepr01030102Form.setButtonCountPremiumDisplay( CommonConst.DISPLAY_TRUE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040110Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );

        cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_TRUE );

        String paymentModeListIsDisabled;
        if( Cepr01040110Mapper.X6 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            paymentModeListIsDisabled = CommonConst.DISABLED_TRUE;
        }
        else
        {
            paymentModeListIsDisabled = CommonConst.DISABLED_FALSE;
        }
        cepr01030102Form.setPaymentModeListIsDisabled( paymentModeListIsDisabled );
        cepr01030102Form.setWaiverPremiumDisabilityFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPayorsClauseFlagIsDisabled( CommonConst.DISABLED_FALSE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040110Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040110Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );

        int termOfPayment;
        int rightPartOfBusinessCd = LazyConverter.toInt( cepr01030102Form.getRightPartOfBusinessCd() );
        switch( rightPartOfBusinessCd )
        {
            case Cepr01040110Mapper.X6: termOfPayment = 5; break;
            case Cepr01040110Mapper.X7: termOfPayment = 10; break;
            case Cepr01040110Mapper.X8: termOfPayment = 15; break;
            case Cepr01040110Mapper.X9: termOfPayment = 20; break;
            case Cepr01040110Mapper.X10: termOfPayment = 1; break;
            default: throw new RuntimeException( "*** rightPartOfBusinessCd not listed in Cepr01040110Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        }

        cepr01030102Form.setTotalSumInsured( new BigDecimal( "20000000" ) );
        cepr01030102Form.setTermOfPayment( termOfPayment );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040110Entry.fillDefaultValueEachTimeFormCalled" );

        cepr01030102Form.setTermOfContract( INSURED_AGE_LIMIT - cepr01030101Form.getInsuredAge() );
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        cepr01030102Form.setTermRiderUnitQtyList( LookupList.getSequenceNumberList( 1, 5, false ) );
        cepr01030102Form.setPersonalAccidentUnitQtyList( LookupList.getSequenceNumberList( 1, 3, false ) );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_TRUE );

        cepr01030102Form.setPayorsClauseFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPayorsClauseFlag( CommonConst.CHECKED_FALSE );
        cepr01030102Form.setWaiverPremiumDisabilityFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setWaiverPremiumDisabilityFlag( CommonConst.CHECKED_FALSE );

        Integer[] payModeArr;

        displayAdditionalAssurance( false );
        // Sekaligus
        if( Cepr01040110Mapper.X10 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_SEKALIGUS };
        }
        // Berkala
        else
        {
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN };
            cepr01030102Form.setHeaderAdditionalAssuranceDisplay( CommonConst.DISPLAY_TRUE );

            // a must to rider Waiver if insured = policyHolder
            if( commonUsedBusiness.policyHolderIsInsured() )
            {
                // added 1 row
                cepr01030102Form.setWaiverPremiumDisabilityFlagDisplay( CommonConst.DISPLAY_TRUE );
                cepr01030102Form.setWaiverPremiumDisabilityFlag( CommonConst.CHECKED_TRUE );

                // added 1 row
                cepr01030102Form.setPayorsClauseFlagDisplay( CommonConst.DISPLAY_FALSE );
                cepr01030102Form.setPayorsClauseFlag( CommonConst.CHECKED_FALSE );
            }
            else
            {
                if(     cepr01030101Form.getPolicyHolderAge() >= 17
                     && cepr01030101Form.getPolicyHolderAge() <= ( 60 - cepr01030102Form.getTermOfPayment() ) )
                {
                    // added 1 row
                    cepr01030102Form.setPayorsClauseFlagDisplay( CommonConst.DISPLAY_TRUE);
                    cepr01030102Form.setPayorsClauseFlag( CommonConst.CHECKED_TRUE);

                    // added 1 row
                    cepr01030102Form.setWaiverPremiumDisabilityFlagDisplay( CommonConst.DISPLAY_FALSE );
                    cepr01030102Form.setWaiverPremiumDisabilityFlag( CommonConst.CHECKED_FALSE );
                }
                else if( cepr01030101Form.getPolicyHolderAge() > ( 60 - cepr01030102Form.getTermOfPayment() ) )
                {
                    cepr01030102Form.setPayorsClauseFlag( CommonConst.CHECKED_FALSE );
                }
            }
        }
        cepr01030102Form.setPaymentModeCd( payModeArr[ 0 ] );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, payModeArr ) );

        cepr01030102Form.setPremium( computePremium( cepr01030102Form.getPaymentModeCd() ) );
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040110Entry.validateCurrentPage" );
        commonValidator.validateMinimumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, cepr01030102Form.getTotalSumInsured(), new BigDecimal( "20000000" ), null );
        if( Cepr01040110Mapper.X10 == cepr01030102Form.getRightPartOfBusinessCd() )
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
        logger.info( "*-*-*-* Cepr01040110Entry.validatePreviousPage" );
        if( commonValidator.validatePolicyHolderIntervalAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 85 ) )
        {
        	int maxAssuredAge;
            switch( cepr01030102Form.getRightPartOfBusinessCd() )
            {
                case Cepr01040110Mapper.X6: maxAssuredAge = 50; break;
                case Cepr01040110Mapper.X7: maxAssuredAge = 45; break;
                case Cepr01040110Mapper.X8: maxAssuredAge = 40; break;
                case Cepr01040110Mapper.X9: maxAssuredAge = 35; break;
                case Cepr01040110Mapper.X10: maxAssuredAge = 54; break;
                default:
                    throw new RuntimeException( "*-*-*-* rightPartOfBusinessCd not listed in Cepr01040110Entry.validatePreviousPage" );
            }
            commonValidator.validateInsuredIntervalAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 1, maxAssuredAge );
        }
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040110Entry.processFormAfterSubmitBeforeValidation" );
        cepr01030102Form.setPremium( computePremium( cepr01030102Form.getPaymentModeCd() ) );
    }

    public BigDecimal computePremium( Integer payModeCd )
    {
        logger.info( "*-*-*-* Cepr01040110Entry.computePremium" );

        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put( "leftPartOfBusinessCd", cepr01030102Form.getLeftPartOfBusinessCd() );
    //    sqlParams.put( "lstabLamaTanggung", INSURED_AGE_LIMIT );
        sqlParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_PREMI );
        sqlParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
        sqlParams.put( "paymentModeCd", payModeCd );
        sqlParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
        sqlParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
        sqlParams.put( "yearNoList", new Integer[]{ new Integer( "1" ) } );
        sqlParams.put( "lsdbsNumber", cepr01030102Form.getRightPartOfBusinessCd() );
        logger.info( "*-*-*-* sqlParams = " + sqlParams );
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

    public BigDecimal computeRiderWpdPremium( Cepr01030102Form cepr01030102Form )
    {
        return computeRider( cepr01030102Form, 814 );
    }

    public BigDecimal computeRiderPcPremium( Cepr01030102Form cepr01030102Form )
    {
        return computeRider( cepr01030102Form, 815 );
    }

    // from PB N_PROD167.OF_HIT_PREMI()
    public BigDecimal computeRider( Cepr01030102Form cepr01030102Form, int ii_bisnis_id )
    {
        logger.info( "*-*-*-* Cepr01040110Entry.computeRider" );

        // ii_lbayar = 5 tahun 10 tahun 15 tahun dst
        int ii_lbayar = cepr01030102Form.getRightPartOfBusinessCd() ;
        int li_jenis[] = new int[5];

        if( ii_bisnis_id == 814 ) //814 WPD
        {
            li_jenis = new int[]{ CommonConst.DUMMY_ZERO, 6, 7, 8, 9, 0 };
        }
        else if( ii_bisnis_id == 815 ) //815 PC
        {
            li_jenis = new int[]{ CommonConst.DUMMY_ZERO, 7, 8, 9, 10, 0 };
        }

        double  ldec_rate = 0;
        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put( "ii_bisnis_id", ii_bisnis_id );
        sqlParams.put( "li_jenis", li_jenis[ ii_lbayar - 5 ] );
        sqlParams.put( "is_kurs_id", cepr01030102Form.getCurrencyCd() );
        sqlParams.put( "ii_usia_pp", cepr01030101Form.getPolicyHolderAge() );
        sqlParams.put( "ii_usia_tt", cepr01030101Form.getInsuredAge() );

        if( ii_bisnis_id == 814 ) //814 WPD
        {
            ldec_rate = LazyConverter.toDouble( eproposalManager.selectRiderWpdRate( sqlParams ) );
        }
        else if( ii_bisnis_id == 815 ) //815 PC
        {
            ldec_rate = LazyConverter.toDouble( eproposalManager.selectRiderPcRate( sqlParams ) );
        }


        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double manfaat = ldec_rate * premium / 1000; 
        BigDecimal manfaatPremium = new BigDecimal( manfaat );
        logger.info( "*-*-*-* ldec_rate = " + ldec_rate );
        logger.info( "*-*-*-* premium = " + premium );
        logger.info( "*-*-*-* manfaat = " + manfaat );
        return manfaatPremium;
    }

}
