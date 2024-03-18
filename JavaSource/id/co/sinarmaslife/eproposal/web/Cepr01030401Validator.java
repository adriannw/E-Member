package id.co.sinarmaslife.eproposal.web;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01030401Validator
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 13, 2007 3:49:13 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Validator;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.standard.util.StringUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

public class Cepr01030401Validator extends Cepr01040000Validator
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01030401Validator( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
    }

    public void validateCommon()
    {
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030401Form cepr01030401Form = cepr01030000HoldingForm.getCepr01030401Form();
        
        if( StringUtil.isEmpty( cepr01030401Form.getCustomerName() ) )
        {
            errors.rejectValue( Cepr01030401FormConst.CUSTOMER_NAME, "error.formEmpty", null, null );
        }
        if( StringUtil.isEmpty( cepr01030401Form.getAgentName() ) )
        {
            errors.rejectValue( Cepr01030401FormConst.AGENT_NAME, "error.formEmpty", null, null );
        }
        if( StringUtil.isEmpty( cepr01030401Form.getBranchName() ) )
        {
            errors.rejectValue( Cepr01030401FormConst.BRANCH_NAME, "error.formEmpty", null, null );
        }
        if( StringUtil.isEmpty( cepr01030401Form.getHomePhoneNo() ) && StringUtil.isEmpty( cepr01030401Form.getMobilePhoneNo() ) )
        {
            errors.rejectValue( Cepr01030401FormConst.HOME_PHONE_NO, "error.fillAtLeastFillOnePhoneNo", null, null );
        }
    }
}
