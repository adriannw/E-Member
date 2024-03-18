package id.co.sinarmaslife.eproposal.common.lookup.source;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: ParticipantEkaSehatInnerLimitVOLookup
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : fadly
 * Version              : 1.0
 * Creation Date    	: Sep 3, 2007 1:50:21 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.model.vo.ParticipantVO;

import java.util.List;
import java.util.ArrayList;

public class ParticipantEkaSehatInnerLimitVOLookup
{
    public static List<ParticipantVO> getParticipantEkaSehatInnerLimitVOList()
    {
    	List<ParticipantVO> participantEkaSehatInnerLimitVOList = new ArrayList<ParticipantVO>();
    	participantEkaSehatInnerLimitVOList = new ArrayList<ParticipantVO>();
    	ParticipantVO participantVO;
    	
    	for( int i = 1; i <= 4; i++ )
    	{
    		participantVO = new ParticipantVO();
    		participantEkaSehatInnerLimitVOList.add( participantVO );
    	}
    	
    	for( int i = 0; i < participantEkaSehatInnerLimitVOList.size(); i++ )
    	{
//    		if( i == 0 )participantEkaSehatInnerLimitVOList.get(i).setStatus("pasangan");
//    		else participantEkaSehatInnerLimitVOList.get(i).setStatus("anak");
    		participantEkaSehatInnerLimitVOList.get(i).setStatus("T.Tambahan " + (i+1));
    	}
    	
    	// Set the relation list - Daru @since 06 Mar 2014
    	for( int i = 0; i < participantEkaSehatInnerLimitVOList.size(); i++ )
    	{
    		participantEkaSehatInnerLimitVOList.get(i).setRelation(ParticipantVOLookup.getRelationOptionVOList());
    	}
    	
        return participantEkaSehatInnerLimitVOList;
    }
}
