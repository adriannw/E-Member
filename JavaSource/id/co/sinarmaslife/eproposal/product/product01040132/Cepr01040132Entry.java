package id.co.sinarmaslife.eproposal.product.product01040132;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040132
 * Program Name   		: Cepr01040132Entry
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
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000Generator;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Entry;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.LazyConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040132Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040132Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040132Entry constructor is called ..." );
        setDownloadUrl( "wepr01040132.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040132Entry.initDisplayedForm" );
        displayStandardForm();
//        displayAdditionalAssurance( true );
        cepr01030102Form.setPersonalAccidentFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setInvestRateInPercentDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonCountTotalSumInsuredDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumListDisplay( CommonConst.DISPLAY_TRUE );
        
        //button
        cepr01030102Form.setButtonRiderAndButtonTopupDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonRiderDisplay( CommonConst.DISPLAY_TRUE );
        
        // rider
        cepr01030103Form.setHcpDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorSpouseTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverTpdDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setTpdDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPaDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorTpdCiDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverTpdCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setEkaSehatDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setEkaSehatInnerLimitDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setLadiesInsDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setHcpLadiesDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setLadiesMedExpenseDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setLadiesMedExpenseInnerLimitDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setHcpProviderDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setEarlyStageCi99Display(CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setMedicalPlusDisplay( CommonConst.DISPLAY_FALSE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040132Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040132Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040132Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        cepr01030102Form.setInvestRateInPercent(new BigDecimal(10));
        List<OptionVO> premiumList = null;
        Integer[] payModeArr;
        Integer termOfPayment;
        Integer termOfContract;
        termOfPayment = 5;
        termOfContract = 8;
        payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN ,Hardcode.PAY_MODE_CD_SEMESTERAN, Hardcode.PAY_MODE_CD_TRIWULANAN };
        
        if(Cepr01040132Mapper.X19 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040132Mapper.X28 == cepr01030102Form.getRightPartOfBusinessCd())
	    {
        	premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 5000000, 19000000, 1000000 );
	    }
        else if(Cepr01040132Mapper.X20 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040132Mapper.X29 == cepr01030102Form.getRightPartOfBusinessCd())
	    {
        	premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 21000000, 35000000, 1000000 );
	    }
        else if(Cepr01040132Mapper.X21 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040132Mapper.X30 == cepr01030102Form.getRightPartOfBusinessCd())
	    {
        	premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 76000000, 90000000, 1000000 );
	    }
        
        cepr01030102Form.setPremiumList( premiumList );
        cepr01030102Form.setPremiumCombinationCd(new BigDecimal(100));
        
        cepr01030102Form.setTermOfContract( termOfContract );
        cepr01030102Form.setTermOfPayment( termOfPayment );
        cepr01030102Form.setPaymentModeCd( payModeArr[ 0 ] );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, payModeArr ) );

    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
    }

    public void validateCurrentPage()
    {
        if( cepr01030102Form.getPremium() != null )
        {
            double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
            double minimumPremiumIdr = 0;
            double maksimumPremiumIdr = 0;
            double minimumPremiumUsd = 0;
            double maksimumPremiumUsd = 0;
            
            if( Cepr01040132Mapper.X19 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040132Mapper.X28 == cepr01030102Form.getRightPartOfBusinessCd())
    	    {
                if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TRIWULANAN )
                {
                	minimumPremiumIdr = 1500000;
                    maksimumPremiumIdr = 5400000;
                }
                else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEMESTERAN ) 
                {
                	minimumPremiumIdr = 2625000;
                    maksimumPremiumIdr = 10500000;
                }
                else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TAHUNAN )
                {
                	minimumPremiumIdr = 5000000;
                    maksimumPremiumIdr = 20000000;
                }
    	    }
            else if( Cepr01040132Mapper.X20 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040132Mapper.X29 == cepr01030102Form.getRightPartOfBusinessCd() )
    	    {
            	if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TRIWULANAN )
                {
                	minimumPremiumIdr = 5400001;
                    maksimumPremiumIdr = 20250000;
                }
                else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEMESTERAN ) 
                {
                	minimumPremiumIdr = 10500001;
                    maksimumPremiumIdr = 39375000;      
                }
                else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TAHUNAN )
                {
                	minimumPremiumIdr = 20000001;
                    maksimumPremiumIdr = 75000000;
                }
    	    }
            else if( Cepr01040132Mapper.X21 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040132Mapper.X30 == cepr01030102Form.getRightPartOfBusinessCd() )
    	    {
            	if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TRIWULANAN )
                {
                	minimumPremiumIdr = 20250001;
                    maksimumPremiumIdr = 270000000;
                }
                else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEMESTERAN ) 
                {
                	minimumPremiumIdr = 39375001;
                    maksimumPremiumIdr = 525000000;
                }
                else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TAHUNAN )
                {
                	minimumPremiumIdr = 75000001;
                    maksimumPremiumIdr = 1000000000;
                }
            	
    	    }
            
            commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minimumPremiumIdr, minimumPremiumUsd );
            commonValidator.validateMaximumMoney(Cepr01030102FormConst.PREMIUM, premium, maksimumPremiumIdr, maksimumPremiumUsd);
        }
        else
        {
            errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.formEmpty", null, null );
        }
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040132Entry.validatePreviousPage" );

        commonValidator.validatePolicyHolderAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 99 );
        commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 1, 60 );

    }  

    public void processFormAfterSubmitBeforeValidation()
    {
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
//        if( "onPressButtonCountTotalSumInsured".equals( cepr01030102Form.getTheEvent() ) ||
//        		"onPressButtonPreviewProposal".equals(  cepr01030102Form.getTheEvent() ) )
//        {
        if( cepr01030102Form.getPremium() != null && cepr01030102Form.getPaymentModeCd()  != null )
        {
            double idec_rate;
            double adec_premi = premium;
            double idec_up;
            double multiplyFactor = 100;

//          int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );            
//            switch( paymentModeCd )
//            {
//                case Hardcode.PAY_MODE_CD_TRIWULANAN: adec_premi = adec_premi * 4;  break;
//                case Hardcode.PAY_MODE_CD_SEMESTERAN: adec_premi = adec_premi * 2;  break;
//                case Hardcode.PAY_MODE_CD_TAHUNAN : adec_premi = adec_premi * 1;  break;
//                default: break;
//            }
            
            if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TRIWULANAN )
            {
            	multiplyFactor = 27;
            }
            else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEMESTERAN ) 
            {
            	multiplyFactor = 52.5;
            }
            else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TAHUNAN )
            {
            	multiplyFactor = 100;
            }
            
            adec_premi = adec_premi * 1;
            // I got it from n_prod_159 function of_set_premi
            //idec_rate = 5000;
            idec_rate = 2000;
            idec_up = adec_premi * idec_rate / 1000;

            // count totalSumInsured with premiumCombinationPercent
            idec_up = idec_up * premiumCombinationPercent / multiplyFactor;
            cepr01030102Form.setTotalSumInsured( new BigDecimal( idec_up ) );
            cepr01030103Form.setTermRiderAmount( new BigDecimal( idec_up ) );
        }
    }
   
    
}
