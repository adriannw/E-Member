package id.co.sinarmaslife.eproposal.product.product01040111;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040111Entry
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Apr 18, 2008 2:47:36 PM
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
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000Generator;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.LazyConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.List;

public class Cepr01040111Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface
{
    @SuppressWarnings( { "WeakerAccess" } )
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040111Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040111Entry constructor is called ..." );
        setDownloadUrl( "wepr01040111.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040111Entry.initDisplayedForm" );
        displayStandardForm();
        cepr01030102Form.setButtonRiderAndButtonTopupDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonRiderDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonCountTotalSumInsuredDisplay( CommonConst.DISPLAY_TRUE );
//        cepr01030102Form.setInvestCostInPercentDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setInvestRateInPercentDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setMtiListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setJenisListDisplay( CommonConst.DISPLAY_TRUE );
        
        cepr01030103Form.setPaDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setHcpDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setHcpFamilyDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setPayorTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorSpouseTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverTpdDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setTpdDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setCiDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setTermRiderDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setEkaSehatDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setEkaSehatInnerLimitDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setHcpProviderDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setHcpLadiesDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setLadiesInsDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setLadiesMedExpenseDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setLadiesMedExpenseInnerLimitDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setPayorTpdCiDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverTpdCiDisplay( CommonConst.DISPLAY_FALSE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040111Entry.initDisabledForm" );

        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPremiumListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setJenisListIsDisabled( CommonConst.DISABLED_FALSE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040111Entry.initReadOnlyForm" );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040111Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        cepr01030102Form.setMtiList( comboLookupBusiness.getMonthOptionVOList( true, new Integer[]{ 3, 6, 12, 24, 36 } ) );
        cepr01030102Form.setMtiCd( 12 );
        cepr01030102Form.setJenisList( comboLookupBusiness.getJenisOptionVOList(true));
        cepr01030102Form.setJenisCd( 0 );
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( true, new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD } ) );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_SEKALIGUS } ) );
        cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_SEKALIGUS );
        cepr01030102Form.setTotalSumInsured( new BigDecimal( "0" ) );
        cepr01030102Form.setTermOfPayment( 1 );
        cepr01030102Form.setInvestRateInPercent( new BigDecimal( "10" ) );
        cepr01030102Form.setPremium( new BigDecimal( "0" ) );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040111Entry.fillDefaultValueEachTimeFormCalled" );
        cepr01030102Form.setJenisList( comboLookupBusiness.getJenisOptionVOList(true));
        Double totalSumInsured;
        Double premiumSekaligus = 0.0;
        List<OptionVO> premiumList = null;
        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 20000000.0, 35000000.0, 1000000.0 );
            //premiumSekaligus = 20000000.0;
            premiumSekaligus = LazyConverter.toDouble( cepr01030102Form.getPremium() );
            if( "onChangeCurrencyCd".equals( cepr01030102Form.getTheEvent() ) ){cepr01030102Form.setInvestRateInPercent( new BigDecimal( "10" ) );}
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 2000.0, 3500.0, 100.0 );
            //premiumSekaligus = 2000.0;
            premiumSekaligus = LazyConverter.toDouble( cepr01030102Form.getPremium() );
            if( "onChangeCurrencyCd".equals( cepr01030102Form.getTheEvent() ) ){cepr01030102Form.setInvestRateInPercent( new BigDecimal( "5" ) );}
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTermRiderFlag() ) ){
        	cepr01030103Form.setTermRiderAmount( cepr01030102Form.getTotalSumInsured() );
        }
        
        double topUp = cepr01030102Form.getPremium().subtract( new BigDecimal( getMaxPremiPokok() ) ).doubleValue();
        totalSumInsured = (premiumSekaligus - topUp) * ( 125.0 / 100.0 );

        cepr01030102Form.setPremiumList( premiumList );
        cepr01030102Form.setTotalSumInsured( new BigDecimal( totalSumInsured ) );
        cepr01030102Form.setTermOfContract( 100 - cepr01030101Form.getInsuredAge() );
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040111Entry.validateCurrentPage" );

        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double minPremiumIdr = 20000000.0;
        double minPremiumUsd = 2000.0;
        if( cepr01030102Form.getPremium() != null )
        {
        	if( cepr01030101Form.getInsuredAge() > 65 && cepr01030101Form.getInsuredAge() < 76 ){
        		commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, 1000000000, 100000 );
        	}else{
        		commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );
        	}
        }
        else
        {
            errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.formEmpty", null, null );
        }
        commonValidator.validatePolicyHolderIntervalAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 80 );
        commonValidator.validateInsuredIntervalAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 1, 75 );
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040111Entry.validatePreviousPage" );
//        commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 0, 70 );
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040111Entry.processFormAfterSubmitBeforeValidation" );
    }
    
    public double getMaxPremiPokok()
    {
        double maxPremiPokok = 0;
        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            maxPremiPokok = 20000000;
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            maxPremiPokok = 2000;
        }
        return maxPremiPokok;
    }
}
