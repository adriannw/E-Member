package id.co.sinarmaslife.eproposal.common.lookup.source;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: ParticipantEkaSehatVOLookup
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ParticipantEkaSehatVOLookup
{

    public static List<ParticipantVO> getParticipantEkaSehatVOList()
    {
    	List<ParticipantVO> participantEkaSehatVOList = new ArrayList<ParticipantVO>();
    	participantEkaSehatVOList = new ArrayList<ParticipantVO>();
    	ParticipantVO participantVO;

    	for( int i = 1; i <= 4; i++ )
    	{
    		participantVO = new ParticipantVO();
    		participantEkaSehatVOList.add( participantVO );
    	}
    	
    	for( int i = 0; i < participantEkaSehatVOList.size(); i++ )
    	{
//    		if( i == 0 )participantEkaSehatVOList.get(i).setStatus("pasangan");
//    		else participantEkaSehatVOList.get(i).setStatus("anak");
    		participantEkaSehatVOList.get(i).setStatus("T.Tambahan " + (i+1));
    	}
    	
    	// Set the relation list - Daru @since 06 Mar 2014
    	for( int i = 0; i < participantEkaSehatVOList.size(); i++ )
    	{
    		participantEkaSehatVOList.get(i).setRelation(ParticipantVOLookup.getRelationOptionVOList());
    	}
    	
        return participantEkaSehatVOList;
    }
}
