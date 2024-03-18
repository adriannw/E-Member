package id.co.sinarmaslife.eproposal.product.product01040137;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040137
 * Program Name   		: Cepr01040137Entry
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

public class Cepr01040137Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040137Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040137Entry constructor is called ..." );
        setDownloadUrl( "wepr01040137.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040137Entry.initDisplayedForm" );
        displayStandardForm();
        cepr01030102Form.setButtonCountPremiumDisplay( CommonConst.DISPLAY_TRUE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040137Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040137Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040137Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        Integer[] payModeArr;
        Integer termOfPayment;
        if( Cepr01040137Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            termOfPayment = 5;
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_SEKALIGUS };
        }
        else if( Cepr01040137Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            termOfPayment = 10;
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_SEKALIGUS };
        }
        else if( Cepr01040137Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            termOfPayment = 1;
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_SEKALIGUS };
        }
        else
        {
            throw new RuntimeException( "*-*-*-* Right part of business number not listed in Cepr01040137Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        }

        cepr01030102Form.setTermOfPayment( termOfPayment );
        cepr01030102Form.setPaymentModeCd( payModeArr[ 0 ] );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, payModeArr ) );

        double totalSumInsuredIdr = 0.0;

        if( Cepr01040137Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040137Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            totalSumInsuredIdr = 75000000.0;
        }
        else if( Cepr01040137Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            totalSumInsuredIdr = 50000000.0;
        }
        cepr01030102Form.setTotalSumInsured( new BigDecimal( totalSumInsuredIdr ) );
        cepr01030102Form.setPremium( computePremium( cepr01030102Form.getPaymentModeCd() ) );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040137Entry.fillDefaultValueEachTimeFormCalled" );

        cepr01030102Form.setTermOfContract( 80 - cepr01030101Form.getInsuredAge() );
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        cepr01030102Form.setTermRiderUnitQtyList( LookupList.getSequenceNumberList( 1, 5, false ) );
        cepr01030102Form.setPersonalAccidentUnitQtyList( LookupList.getSequenceNumberList( 1, 3, false ) );
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040137Entry.validateCurrentPage" );

        if( cepr01030102Form.getPremium() != null )
        {
            double totalSumInsured = LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() );
            double minimumTotalSumInsuredIdr = 0.0;
            minimumTotalSumInsuredIdr =  Cepr01040137Mapper.X1 == rightPartOfBusinessCd || Cepr01040137Mapper.X2 == rightPartOfBusinessCd ? 75000000.0: 50000000.0;
            
            commonValidator.validateMinimumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, totalSumInsured, minimumTotalSumInsuredIdr, -1 );
        }
        else
        {
            errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED, "error.formEmpty", null, null );
        }
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040137Entry.validatePreviousPage" );

        commonValidator.validatePolicyHolderAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 18, 85 );
//        int maxInsuredAge =  Cepr01040137Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd()? 55 : 60;
        commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 18, 60 );

    }

    public BigDecimal computePremium( Integer payModeCd )
    {
        logger.info( "*-*-*-* Cepr01040137Entry.computePremium" );
        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put( "leftPartOfBusinessCd", new Integer( "298" ) );
        sqlParams.put( "lstabLamaTanggung", 80 - cepr01030101Form.getInsuredAge() );
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
            rate = eproposalManager.selectRate( sqlParams ).get( 0 );
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
        logger.info( "*-*-*-* Cepr01040137Entry.processFormAfterSubmitBeforeValidation" );
        cepr01030102Form.setPremium( computePremium( cepr01030102Form.getPaymentModeCd() ) );
    }
   
}
