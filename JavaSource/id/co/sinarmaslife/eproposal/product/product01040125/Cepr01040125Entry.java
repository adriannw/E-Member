package id.co.sinarmaslife.eproposal.product.product01040125;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040125
 * Program Name   		: Cepr01040125Entry
 * Description         	: Power Simponi Rupiah (95)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 9, 2007 11:14:34 AM
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
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Entry;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.LazyConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

public class Cepr01040125Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface

{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040125Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040125Entry constructor is called ..." );
        setDownloadUrl( "wepr01040125.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040125Entry.initDisplayedForm" );
        displayStandardForm();
        cepr01030102Form.setButtonCountTotalSumInsuredDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setInvestRateInPercentDisplay(CommonConst.DISPLAY_TRUE);
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040125Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setInvestRateInPercentIsDisabled(CommonConst.DISABLED_TRUE);
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumListIsDisabled( CommonConst.DISABLED_FALSE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040125Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040125Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040125Entry.fillDefaultValueEachTimeFormCalled" );
        cepr01030102Form.setInvestRateInPercent(new BigDecimal("8.5"));
        cepr01030102Form.setTermOfContract( 4 );
        cepr01030102Form.setTermOfPayment( 1 );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_SEKALIGUS } ) );
        cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_SEKALIGUS );
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        cepr01030102Form.setCurrencyCd( Hardcode.CUR_IDR_CD );

        List<OptionVO> premiumList = new ArrayList<OptionVO>();
        OptionVO optionVO;
        optionVO = new OptionVO( "", "" );
        premiumList.add( optionVO );

        String value;
        for( int i = 1; i <= 10; i++ )
        {
            value = "" + i + "0,000,000.00";
            optionVO = new OptionVO( value, value );
            premiumList.add( optionVO );
        }
        cepr01030102Form.setPremiumList( premiumList );
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040125Entry.validateCurrentPage" );
        if(cepr01030102Form.getPremium()!=null)
        {
	        if( cepr01030102Form.getPremium().compareTo( new BigDecimal( "10000000" ) ) < 0 )
	        {
	            errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.valueAtLeast", new Object[]{ "Rp.10,000,000.00"}, null );
	        }
        }
        
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040125Entry.validatePreviousPage" );
        commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 1, 60 );
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040125Entry.processFormAfterSubmitBeforeValidation" );
        BigDecimal totalSumInsured = cepr01030102Form.getPremium();
        logger.info( "*-*-*-* totalSumInsured = " + totalSumInsured );
        cepr01030102Form.setTotalSumInsured( totalSumInsured );
    }
}
