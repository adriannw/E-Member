package id.co.sinarmaslife.eproposal.product.product01040206;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040204
 * Program Name   		: Cepr01040204Entry
 * Description         	: Eka Link 80 Syariah (152)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 8, 2008 2:32:48 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.product.product01040204.Cepr01040204Entry;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

public class Cepr01040206Entry extends Cepr01040204Entry
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040206Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040206Entry constructor is called ..." );
        setDownloadUrl( "wepr01040206.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040206Entry.initDisplayedForm" );
        super.initDisplayedForm();
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040206Entry.initDisabledForm" );
        super.initDisabledForm();
        cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040206Entry.initReadOnlyForm" );
        super.initReadOnlyForm();
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040206Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        super.fillDefaultValueEachTimeRightPartOfBusinessCdChanged();
        cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040206Entry.fillDefaultValueEachTimeFormCalled" );
        super.fillDefaultValueEachTimeFormCalled();
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        cepr01030102Form.setCurrencyCd( Hardcode.CUR_IDR_CD );
        cepr01030102Form.setFixIdrDisplay( CommonConst.DISPLAY_FALSE );
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040206Entry.validateCurrentPage" );
        super.validateCurrentPage();
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040206Entry.validatePreviousPage" );
        super.validatePreviousPage();
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040206Entry.processFormAfterSubmitBeforeValidation" );
        super.processFormAfterSubmitBeforeValidation();
    }
}