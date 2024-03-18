package id.co.sinarmaslife.eproposal.common.constant;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: CommonConst
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 30, 2007 2:36:22 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.math.BigDecimal;

public class CommonConst
{
    public static final String CHECKED_TRUE = "true";
    public static final String CHECKED_FALSE = null;
    public static final String DISPLAY_TRUE = "";
    public static final String DISPLAY_FALSE = "none";
    public static final String DISABLED_TRUE = "true";
    public static final String DISABLED_FALSE = "";
    public static final String READ_ONLY_TRUE = "true";
    public static final String READ_ONLY_FALSE = "";
    public static final String REFRESH_TRUE = "true";
    public static final String REFRESH_FALSE = "false";

    public static final String SES_DOWNLOAD_URL_SESSION = "downloadUrlSession";
    public static final String SES_COMMAND = "command";
    public static final BigDecimal PER_MIL = new BigDecimal( "0.001" );
    public static final Integer DUMMY_ZERO = 0;
    public static final boolean DUMMY_FALSE = false;
    
    public static final String ROOTDIR_EPROP_TEST = "\\\\Storage\\pdfind\\E-Proposal\\pdf_proposal_testing";
    public static final String ROOTDIR_EPROP = "\\\\Storage\\pdfind\\E-Proposal\\pdf_proposal";
    public static final String ROOTDIR = "C:\\Ekaweb";
    
}
