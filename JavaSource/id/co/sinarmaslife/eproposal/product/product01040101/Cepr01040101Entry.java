package id.co.sinarmaslife.eproposal.product.product01040101;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040101
 * Program Name   		: Cepr01040101Entry
 * Description         	: Super Protection (45)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jun 22, 2007 10:29:55 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000EntryInterface;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Entry;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;

public class Cepr01040101Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040101Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040101Entry constructor is called ..." );
        setDownloadUrl( "wepr01040101.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040101Entry.initDisplayedForm" );
        displayStandardForm();
        cepr01030102Form.setClazzListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonCountPremiumDisplay( CommonConst.DISPLAY_TRUE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040101Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040101Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040101Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        cepr01030102Form.setTotalSumInsured( new BigDecimal( "50000000" ) );
    }


    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040101Entry.fillDefaultValueEachTimeFormCalled" );
        cepr01030102Form.setTermOfContract( 1 );
        cepr01030102Form.setTermOfPayment( 1 );
        cepr01030102Form.setClazzList( comboLookupBusiness.getClazzOptionVOList( true ) );
        cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_TAHUNAN );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN } ) );
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD } ) );
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040101Entry.validateCurrentPage" );
        if( cepr01030102Form.getClazzCd() == null )
        {
            errors.rejectValue( Cepr01030102FormConst.CLAZZ_CD, "error.formEmpty", null, null );
        }

        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getTotalSumInsured().compareTo( new BigDecimal( "50000000" ) ) < 0 )
            {
                errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED, "error.valueAtLeast", new Object[]{ "Rp.50.000,000.00"}, null );
            }
            
            if( cepr01030102Form.getTotalSumInsured().compareTo( new BigDecimal( "1000000000" ) ) > 0 )
            {
                errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED, "error.valueMax", new Object[]{ "Rp.1.000,000,000.00"}, null );
            }
        }
        
        
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040101Entry.validatePreviousPage" );
        commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 59 );
        commonValidator.validatePolicyHolderIsInsured( Cepr01030102FormConst.ASSURANCE_PLAN_CD );
    }

    public BigDecimal computePremium( Object clazzCdIns )
    {
        logger.info( "*-*-*-* Cepr01040101Entry.computePremium" );
        BigDecimal rate;

        int clazzCd = clazzCdIns == null? 0 : ( Integer ) clazzCdIns;
        switch( clazzCd )
        {
            case  1: rate = new BigDecimal( "3.8" ); break;
            case  2: rate = new BigDecimal( "5.5" ); break;
            case  3: rate = new BigDecimal( "7.1" ); break;
            case  4: rate = new BigDecimal( "11.1" ); break;
            default: rate = new BigDecimal( "0" );
        }

        BigDecimal totalSumInsured = cepr01030102Form.getTotalSumInsured();
        BigDecimal factor = new BigDecimal( 1 );

        return computePremiumWithSimpleMethod( totalSumInsured, rate, factor );
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040101Entry.processFormAfterSubmitBeforeValidation" );
        Integer clazzCd = cepr01030102Form.getClazzCd();
        BigDecimal premium = computePremium( clazzCd );
        logger.info( "*-*-*-* premiums = " + premium );
        cepr01030102Form.setPremium( premium );
    }
}
