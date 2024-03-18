package id.co.sinarmaslife.eproposal.product.product01040119;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040119Entry
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Dec 30, 2008 10:03:49 AM
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
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000EntryInterface;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;

public class Cepr01040119Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface

{
    protected final Log logger = LogFactory.getLog( getClass() );
    protected Cepr01040119Business cepr01040119Business;

    public Cepr01040119Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040119Entry constructor is called ..." );
        cepr01040119Business = new Cepr01040119Business( eproposalManager, editorUtil, command );
        setDownloadUrl( "wepr01040119.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040119Entry.initDisplayedForm" );
        displayStandardForm();
        cepr01030102Form.setPacketListDisplay( CommonConst.DISPLAY_TRUE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040119Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumListIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040119Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040119Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_TAHUNAN );
        cepr01030102Form.setPacketCd( Hardcode.PACKET_HOSPITAL_CASH_PLAN );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040119Entry.fillDefaultValueEachTimeFormCalled" );

        cepr01030102Form.setTermOfContract( 65 - cepr01030101Form.getInsuredAge() );
        int rightPartOfBusinessCd = cepr01030102Form.getRightPartOfBusinessCd();
        int termOfPayment = 0;

        if( rightPartOfBusinessCd >= 1 && rightPartOfBusinessCd <= 5 )
            termOfPayment = 5;
        else if( rightPartOfBusinessCd >= 6 && rightPartOfBusinessCd <= 10 )
            termOfPayment = 10;
        else if( rightPartOfBusinessCd >= 11 && rightPartOfBusinessCd <= 15 )
            termOfPayment = 15;
        else if( rightPartOfBusinessCd >= 16 && rightPartOfBusinessCd <= 20 )
            termOfPayment = 55 - cepr01030101Form.getInsuredAge();

        cepr01030102Form.setTermOfPayment( termOfPayment );

        double totalSumInsured = 0;
        int sisa = rightPartOfBusinessCd % 5;
        switch( sisa )
        {
            case 1: totalSumInsured = 20000000; break;
            case 2: totalSumInsured = 35000000; break;
            case 3: totalSumInsured = 50000000; break;
            case 4: totalSumInsured = 65000000; break;
            case 0: totalSumInsured = 100000000; break;
            default:break;
        }

        cepr01030102Form.setTotalSumInsured( new BigDecimal( totalSumInsured ) );

        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_SEMESTERAN, Hardcode.PAY_MODE_CD_TRIWULANAN, Hardcode.PAY_MODE_CD_BULANAN,  } ) );
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        if( cepr01030102Form.getCurrencyCd() == null )
        {
            cepr01030102Form.setCurrencyCd( Hardcode.CUR_IDR_CD );
        }
        cepr01030102Form.setPacketList( comboLookupBusiness.selectPacketOptionVOList( false ) );

        Integer leftPartBusinessCd = cepr01030102Form.getLeftPartOfBusinessCd();
        Integer rightPartBusinessCd = cepr01030102Form.getRightPartOfBusinessCd();
        Integer insuredAge = cepr01030101Form.getInsuredAge();
        String currencyCd = cepr01030102Form.getCurrencyCd();
        Integer paymentModeCd = cepr01030102Form.getPaymentModeCd();
        cepr01030102Form.setPremium( cepr01040119Business.computePremium( leftPartBusinessCd, rightPartBusinessCd, insuredAge, currencyCd, paymentModeCd ) );
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040119Entry.validateCurrentPage" );
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040119Entry.validatePreviousPage" );
        commonValidator.validatePolicyHolderAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 85 );

        int downLimit = 0;
        int upLimit = 9999;

        if( rightPartOfBusinessCd >= 1 && rightPartOfBusinessCd <= 5 )
        {
            downLimit = 1;
            upLimit = 50;
        }
        else if( rightPartOfBusinessCd >= 6 && rightPartOfBusinessCd <= 10 )
        {
            downLimit = 1;
            upLimit = 45;
        }
        else if( rightPartOfBusinessCd >= 11 && rightPartOfBusinessCd <= 15 )
        {
            downLimit = 1;
            upLimit = 40;
        }
        else if( rightPartOfBusinessCd >= 16 && rightPartOfBusinessCd <= 20 )
        {
            downLimit = 1;
            upLimit = 50;
        }
        commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, downLimit, upLimit );
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040119Entry.processFormAfterSubmitBeforeValidation" );
    }

}