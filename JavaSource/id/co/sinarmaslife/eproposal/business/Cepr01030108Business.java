package id.co.sinarmaslife.eproposal.business;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01030107Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Sep 3, 2007 1:48:43 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import id.co.sinarmaslife.eproposal.model.vo.ParticipantVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.lookup.LookupList;
import id.co.sinarmaslife.eproposal.web.Cepr01030108Form;

public class Cepr01030108Business
{
    protected final Log logger = LogFactory.getLog( getClass() );
    protected EproposalManager eproposalManager;
    protected Cepr00000000EditorUtil editorUtil;

    // todo: validateCurrentPage if data in the list not valid
    public Cepr01030108Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil )
    {
        this.eproposalManager = eproposalManager;
        this.editorUtil = editorUtil;
    }

    public void resetForm( Cepr01030108Form cepr01030108Form )
    {
        cepr01030108Form.setParticipantVOList( LookupList.getParticipantEkaSehatInnerLimitVOList() );
    }

}
