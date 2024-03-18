package id.co.sinarmaslife.eproposal.common.lookup.source;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: ParticipantVOLookup
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
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

public class ParticipantHcpLadiesVOLookup
{
    public static List<ParticipantVO> getParticipantVOList()
    {
    	List<ParticipantVO> participantVOList = new ArrayList<ParticipantVO>();
    	participantVOList = new ArrayList<ParticipantVO>();
    	ParticipantVO participantVO;
    	
    	for( int i = 1; i <= 5; i++ )
    	{
    		participantVO = new ParticipantVO();
    		participantVOList.add( participantVO );
    	}
    	
    	for( int i = 0; i < participantVOList.size(); i++ )
    	{
//    		if( i == 0 )participantVOList.get(i).setStatus("T.Utama");
//    		else if( i == 1 )participantVOList.get(i).setStatus("Peserta");
//    		else participantVOList.get(i).setStatus("Anak");
    		if( i == 0 )participantVOList.get(i).setStatus("T.Utama");
    		else participantVOList.get(i).setStatus("T.Tambahan " + i);
    	}
    	
    	// Set the relation list - Daru @since 06 Mar 2014
    	for( int i = 0; i < participantVOList.size(); i++ )
    	{
    		participantVOList.get(i).setRelation(ParticipantVOLookup.getRelationOptionVOList());
    	}
    	
        return participantVOList;
    }
}
