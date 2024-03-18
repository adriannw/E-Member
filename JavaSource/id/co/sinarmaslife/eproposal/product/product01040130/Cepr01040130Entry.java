package id.co.sinarmaslife.eproposal.product.product01040130;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040112X2Entry
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : Fadly Mathendra
 * Version              : 1.0
 * Creation Date    	: Okt 27, 2008 9:26:31 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Entry;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000Generator;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000EntryInterface;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.LazyConverter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;


public class Cepr01040130Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );
    public static int INSURED_AGE_LIMIT = 65;
    
    public Cepr01040130Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040130Entry constructor is called ..." );
        setDownloadUrl( "wepr01040130.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040130Entry.initDisplayedForm" );
        displayStandardForm();
        displayAdditionalAssurance( false );
        cepr01030102Form.setPremiumListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonCountTotalSumInsuredDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setInvestRateInPercentDisplay( CommonConst.DISPLAY_TRUE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040130Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setInvestRateInPercentIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040130Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040130Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );

        cepr01030102Form.setTermOfContract( 1 );
        cepr01030102Form.setTermOfPayment( 1 );

        Integer[] payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN };
        cepr01030102Form.setPaymentModeCd( payModeArr[ 0 ] );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, payModeArr ) );

        String[] currencyCdArr = new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD };
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, currencyCdArr ) );
        
		cepr01030102Form.setTotalSumInsured( new BigDecimal( "10000000" ) );
		cepr01030102Form.setInvestRateInPercent( new BigDecimal( "10" ) );

    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040130Entry.fillDefaultValueEachTimeFormCalled" );
        
		if("onChangeCurrencyCd".equals( cepr01030102Form.getTheEvent() ) || "onChangeAssurancePlanCd".equals( cepr01030102Form.getTheEvent() ))
		{
			cepr01030102Form.setTotalSumInsured(new BigDecimal(0));
			cepr01030102Form.setPremium(new BigDecimal(0));
			if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
			{
				cepr01030102Form.setTotalSumInsured( new BigDecimal( "10000000" ) );
				cepr01030102Form.setInvestRateInPercent( new BigDecimal( "10" ) );
			}
			else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
			{
				cepr01030102Form.setTotalSumInsured( new BigDecimal( "6000" ) );
				cepr01030102Form.setInvestRateInPercent( new BigDecimal( "5" ) );
			}
		}
		cepr01030102Form.setPremium( computePremium( cepr01030102Form.getPaymentModeCd() ) );
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040130Entry.validateCurrentPage" );

        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double totalSumInsured = LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() );
        double minPremiumIdr = 300000;
        double minTotalSumInsuredIdr = 10000000;
        double minTotalSumInsuredUsd = 6000;

        if( cepr01030102Form.getTotalSumInsured() != null )
        {
        	commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, 0 );
        	commonValidator.validateMinimumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, totalSumInsured, minTotalSumInsuredIdr, minTotalSumInsuredUsd );
        }
        else
        {
            errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED, "error.formEmpty", null, null );
        }
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040130Entry.validatePreviousPage" );
        commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 20, 65 );
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040130Entry.processFormAfterSubmitBeforeValidation" );
        cepr01030102Form.setPremium( computePremium( cepr01030102Form.getPaymentModeCd() ) );
    }
    
    public BigDecimal computePremium( Integer payModeCd )
    {
        logger.info( "*-*-*-* Cepr01040130Entry.computePremium" );

        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put( "leftPartOfBusinessCd", cepr01030102Form.getLeftPartOfBusinessCd() );
        sqlParams.put( "lstabLamaTanggung", cepr01030102Form.getTermOfContract() );
        sqlParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_PREMI );
        sqlParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
        sqlParams.put( "paymentModeCd", payModeCd );
        sqlParams.put( "termOfPayment",  cepr01030102Form.getTermOfPayment() );
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
    
}