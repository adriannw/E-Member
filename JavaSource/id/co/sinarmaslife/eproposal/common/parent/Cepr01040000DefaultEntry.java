package id.co.sinarmaslife.eproposal.common.parent;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040000DefaultEntry
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jun 22, 2007 11:57:51 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000EntryInterface;
import id.co.sinarmaslife.eproposal.common.lookup.LookupList;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;

public class Cepr01040000DefaultEntry extends Cepr01040000Entry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040000DefaultEntry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040000DefaultEntry constructor is called ..." );
        setDownloadUrl( "" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040000DefaultEntry.initDisplayedForm" );
        showAllForm( false );
        cepr01030102Form.setAssurancePlanListDisplay( CommonConst.DISPLAY_TRUE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040000DefaultEntry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040000DefaultEntry.initReadOnlyForm" );

        cepr01030102Form.setPremiumReadOnly( CommonConst.READ_ONLY_FALSE );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040000DefaultEntry.fillDefaultValueEachTimeFormCalled" );

        cepr01030102Form.setPremium( new BigDecimal( "0" ) );
        cepr01030102Form.setTermOfContract( 1 );
        cepr01030102Form.setTermOfPayment( 1 );
        cepr01030102Form.setTotalSumInsured( new BigDecimal( "0" ) );
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, null ) );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( true, null ) );
//        cepr01030102Form.setSmileLadiesPackageList( comboLookupBusiness.smileLadiesPackageList() );
//        cepr01030102Form.setSmilePensionPackageList( comboLookupBusiness.smilePensionPackageList() );
        cepr01030102Form.setSpecialOfferList( comboLookupBusiness.specialOfferList() );
        cepr01030102Form.setTermRiderUnitQtyList( LookupList.getSequenceNumberList( 1, 5, false ) );
        cepr01030102Form.setPersonalAccidentUnitQtyList( LookupList.getSequenceNumberList( 1, 3, false ) );
        cepr01030102Form.setEkaSehatList( LookupList.getEkaSehatIdrList() );
        cepr01030102Form.setBabyList( LookupList.getBabyList() );
        cepr01030102Form.setBabyChooseList( LookupList.getBabyChooseList());
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040000DefaultEntry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040000DefaultEntry.validateCurrentPage" );
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040000DefaultEntry.validatePreviousPage" );
    }

    public BigDecimal computePremium( Object param )
    {
        logger.info( "*-*-*-* Cepr01040000DefaultEntry.computePremium" );
        return null;
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040000DefaultEntry.processFormAfterSubmitBeforeValidation" );
    }


}
