package id.co.sinarmaslife.eproposal.model.interfaces;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040000EntryInterface
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jun 22, 2007 10:53:19 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.util.Map;

public interface Cepr01040000EntryInterface extends Cepr01040000DownloadInterface
{

    public void initDisplayedForm();
    public void initDisabledForm();
    public void initReadOnlyForm();
    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged();
    public void fillDefaultValueEachTimeFormCalled();
    public void synchronizeSelectedListOption();
    public void validateCurrentPage();
    public void validatePreviousPage();
    public void processFormAfterSubmitBeforeValidation();
    public void resetFormsWhenRightPartCdChanged();

    // setter getter for product

    // used this for general data
    public Map<String, Object> getDataMap();
    public void setDataMap( Map<String, Object> dataMap );
}
