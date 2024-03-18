package id.co.sinarmaslife.eproposal.product.product01040133;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040133
 * Program Name   		: Cepr01040133Entry
 * Description         	: Dana sejahtera (40)
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
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Entry;
import id.co.sinarmaslife.standard.util.LazyConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040133Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040133Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040133Entry constructor is called ..." );
        setDownloadUrl( "wepr01040133.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040133Entry.initDisplayedForm" );
        boolean isShowAdditionalAssurance;
        if( Cepr01040133Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040133Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd())
        {
            isShowAdditionalAssurance = true;
        }
        else if( Cepr01040133Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            isShowAdditionalAssurance = true;
        }
        else if( Cepr01040133Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            isShowAdditionalAssurance = true;
        }
        else if( Cepr01040133Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            isShowAdditionalAssurance = false;
        }
        else if( Cepr01040133Mapper.X5 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            isShowAdditionalAssurance = false;
        }
        else if( Cepr01040133Mapper.X6 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            isShowAdditionalAssurance = false;
        }
        else
        {
            throw new RuntimeException( "*-*-*-* Right part of business number not listed in Cepr01040133Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        }
        displayStandardForm();
        displayAdditionalAssurance( isShowAdditionalAssurance );
        cepr01030102Form.setTpdFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setInvestRateInPercentDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonCountPremiumDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setEkaSehatFlagDisplay( CommonConst.DISPLAY_FALSE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040133Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040133Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040133Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        cepr01030102Form.setInvestRateInPercent(new BigDecimal(10));
        Integer[] payModeArr;
        Integer termOfPayment;
        Integer termOfContract;
        if( Cepr01040133Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            termOfPayment = 10;
            termOfContract = 10;
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN };
        }
        else if( Cepr01040133Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            termOfPayment = 15;
            termOfContract = 15;
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN };
        }
        else if( Cepr01040133Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            termOfPayment = 20;
            termOfContract = 20;
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN };
        }
        else if( Cepr01040133Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
        	 termOfPayment = 1;
             termOfContract = 10;
             payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_SEKALIGUS };
        }
        else if( Cepr01040133Mapper.X5 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
        	termOfPayment = 1;
            termOfContract = 15;
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_SEKALIGUS };
        }
        else if( Cepr01040133Mapper.X6 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            termOfPayment = 1;
            termOfContract = 20;
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_SEKALIGUS };
        }
        else
        {
            throw new RuntimeException( "*-*-*-* Right part of business number not listed in Cepr01040133Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        }
        
        cepr01030102Form.setTermOfContract( termOfContract );
        cepr01030102Form.setTermOfPayment( termOfPayment );
        cepr01030102Form.setPaymentModeCd( payModeArr[ 0 ] );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, payModeArr ) );

        double totalSumInsured;
        double totalSumInsuredIdr = 100000000;
        double totalSumInsuredUsd = 10000;

        totalSumInsured = Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() )? totalSumInsuredIdr : totalSumInsuredUsd;

        cepr01030102Form.setTotalSumInsured( new BigDecimal( totalSumInsured ) );
        cepr01030102Form.setPremium( computePremium( cepr01030102Form.getPaymentModeCd() ) );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040133Entry.fillDefaultValueEachTimeFormCalled" );
        
        if("onChangeCurrencyCd".equals( cepr01030102Form.getTheEvent() ) )
        {
            double totalSumInsured;
            double totalSumInsuredIdr = 100000000;
            double totalSumInsuredUsd = 10000;

            totalSumInsured = Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() )? totalSumInsuredIdr : totalSumInsuredUsd;

            cepr01030102Form.setTotalSumInsured( new BigDecimal( totalSumInsured ) );
            cepr01030102Form.setPremium( computePremium( cepr01030102Form.getPaymentModeCd() ) );
        }
        
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD } ) );
        cepr01030102Form.setTermRiderUnitQtyList( LookupList.getSequenceNumberList( 1, 5, false ) );
        cepr01030102Form.setPersonalAccidentUnitQtyList( LookupList.getSequenceNumberList( 1, 3, false ) );
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040133Entry.validateCurrentPage" );

        if( cepr01030102Form.getPremium() != null )
        {
            double totalSumInsured = LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() );
            double minimumTotalSumInsuredIdr = 100000000;
            double minimumTotalSumInsuredUsd = 10000;
            
            commonValidator.validateMinimumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, totalSumInsured, minimumTotalSumInsuredIdr, minimumTotalSumInsuredUsd );
        }
        else
        {
            errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED, "error.formEmpty", null, null );
        }
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040133Entry.validatePreviousPage" );

        commonValidator.validatePolicyHolderAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 85 );
        int maxInsuredAge = 60;
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040133Mapper.X1 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040133Mapper.X4 ){
        	maxInsuredAge = 60;
        }else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040133Mapper.X2 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040133Mapper.X5 ){
        	maxInsuredAge = 55;
        }else{
        	maxInsuredAge = 50;
        }
        commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 1, maxInsuredAge );
        
        // if take Critical Illness Rider then validate these ones	
        if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getCriticalIllnessFlag() )
                && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getCriticalIllnessFlagDisplay() ) )
        {
            int maxAge;
            int minAge;

            if( Cepr01040133Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
            {
                minAge = 17;
                maxAge = 60;
            }
            else if( Cepr01040133Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() )
            {
                minAge = 17;
                maxAge = 55;
            }
            else if( Cepr01040133Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() )
            {
                minAge = 17;
                maxAge = 50;
            }
            else if( Cepr01040133Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() )
            {
                minAge = 17;
                maxAge = 60;
            }
            else if( Cepr01040133Mapper.X5 == cepr01030102Form.getRightPartOfBusinessCd() )
            {
                minAge = 17;
                maxAge = 60;
            }
            else if( Cepr01040133Mapper.X6 == cepr01030102Form.getRightPartOfBusinessCd() )
            {
                minAge = 17;
                maxAge = 60;
            }
            else
            {
                throw new RuntimeException( "*-*-*-* Right part of business number not listed in Cepr01040133Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
            }

            commonValidator.validateInsuredAge( Cepr01030102FormConst.CRITICAL_ILLNESS_FLAG, minAge, maxAge );
        }
    }

    public BigDecimal computePremium( Integer payModeCd )
    {
        logger.info( "*-*-*-* Cepr01040133Entry.computePremium" );

        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put( "leftPartOfBusinessCd", cepr01030102Form.getLeftPartOfBusinessCd() );
        sqlParams.put( "lstabLamaTanggung", cepr01030102Form.getTermOfContract() );
        sqlParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_PREMI );
        sqlParams.put( "lsdbsNumber", cepr01030102Form.getRightPartOfBusinessCd() );
        sqlParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
        sqlParams.put( "paymentModeCd", payModeCd );
        sqlParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
        sqlParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
        sqlParams.put( "yearNoList", new Integer[]{ new Integer( "1" ) } );
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


    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040133Entry.processFormAfterSubmitBeforeValidation" );
        cepr01030102Form.setPremium( computePremium( cepr01030102Form.getPaymentModeCd() ) );
    }
   
    
}
