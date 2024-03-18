package id.co.sinarmaslife.eproposal.product.product01040205;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040205X3Entry
 * Description         	: Eka Link 80 Plus (116)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 13, 2008 2:14:04 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.lookup.LookupList;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.standard.util.LazyConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;

public class Cepr01040205X3Entry extends Cepr01040205Entry
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040205X3Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
    }

    public void initDisplayedForm()
    {
        super.initDisplayedForm();
        // hide some features in rider page
        cepr01030103Form.setPayorTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorSpouseTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverTpdDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverCiDisplay( CommonConst.DISPLAY_FALSE );
    }

    public void initDisabledForm()
    {
        super.initDisabledForm();
        cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void initReadOnlyForm()
    {
        super.initReadOnlyForm();
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumFurloughYear( 1 );
        cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_SEKALIGUS );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        super.fillDefaultValueEachTimeFormCalled();
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD } ) );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_SEKALIGUS } ) );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPayment( 1 );
        cepr01030102Form.setPremiumFurloughYearIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumFurloughYearListDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPremiumCombinationCd( new BigDecimal( "100" ) );
        cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination10FoldedList().subList( 0, 1 ) );
        refreshBaseTopupPremium();
    }

    public void validateCurrentPage()
    {
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double minPremiumIdr = 10000000;
        double minPremiumUsd = 1000;
        commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );

        super.validateCurrentPage();
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
        refreshBaseTopupPremium();
        super.processFormAfterSubmitBeforeValidation();
        if( "onChangePremium".equals( cepr01030102Form.getTheEvent() )
            || "onPressButtonCountTotalSumInsured".equals( cepr01030102Form.getTheEvent() )
            || cepr01030102Form.getTotalSumInsured() != null && cepr01030102Form.getTotalSumInsured().equals( new BigDecimal( "0" ) ) )
        {
            double idec_rate;
            double adec_premi = premium;
            double idec_up;

            int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );

            switch( paymentModeCd )
            {
                case Hardcode.PAY_MODE_CD_TRIWULANAN: adec_premi = adec_premi * 4;  break;
                case Hardcode.PAY_MODE_CD_SEMESTERAN: adec_premi = adec_premi * 2;  break;
                case Hardcode.PAY_MODE_CD_BULANAN   : adec_premi = adec_premi * 12; break;
                default: break;
            }

            // I got it from n_prod_159 function of_set_premi
            idec_rate = 1250;
            idec_up = adec_premi * idec_rate / 1000;
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                idec_up = Math.max( idec_up, 15000000 );
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                idec_up = Math.max( idec_up, 1500 );
            }

            // count totalSumInsured with premiumCombinationPercent
            idec_up = idec_up * premiumCombinationPercent / 100;
            cepr01030102Form.setTotalSumInsured( new BigDecimal( idec_up ) );
            
            
        }
      
    }
}
