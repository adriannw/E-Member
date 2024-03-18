package id.co.sinarmaslife.eproposal.web;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01030111Form
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : Alfian
 * Version              : 1.0
 * Creation Date    	: Aug 24, 2007 4:53:11 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.model.vo.ParticipantVO;
import id.co.sinarmaslife.standard.util.ListUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cepr01030111Form implements Serializable
{
    /**********************************************************************
	 * Program History
	 *
	 * Project Name      	: E-Proposal
	 * Function Id         	: id.co.sinarmaslife.eproposal.web
	 * Program Name   		: 
	 * Description         	:
	 * Environment      	: Java  1.5.0_06
	 * Author               : Fadly Mathendra
	 * Version              : 1.0
	 * Creation Date    	: Nov 28, 2012 10:38:50 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = -6622780774920470650L;
	ArrayList<ParticipantVO> participantVOList;

    public ArrayList<ParticipantVO> getParticipantVOList()
    {
        return participantVOList;
    }

    public void setParticipantVOList( List<ParticipantVO> participantVOList )
    {
        this.participantVOList = ListUtil.serializableList(participantVOList);
    }
}
