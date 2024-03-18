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
import id.co.sinarmaslife.eproposal.common.lookup.Cepr00000000ComboLookupBusiness;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ParticipantMedicalPlusVOLookup
{

    public static List<ParticipantVO> getParticipantMedicalPlusVOList()
    {
    	List<ParticipantVO> participantMedicalPlusVOList = new ArrayList<ParticipantVO>();
    	participantMedicalPlusVOList = new ArrayList<ParticipantVO>();
    	ParticipantVO participantVO;
    	Cepr00000000ComboLookupBusiness cepr00000000ComboLookupBusiness =  new Cepr00000000ComboLookupBusiness(null, null);

    	for( int i = 1; i <= 4; i++ )
    	{
    		participantVO = new ParticipantVO();
    		participantMedicalPlusVOList.add( participantVO );
    	}
    	
    	
    	for( int i = 0; i < participantMedicalPlusVOList.size(); i++ )
    	{
    		if( i == 0 )participantMedicalPlusVOList.get(i).setStatus("pasangan");
     		else participantMedicalPlusVOList.get(i).setStatus("anak");    
    	}
    	
    	// Set the relation list - Daru @since 06 Mar 2014
    	//for( int i = 0; i < participantMedicalPlusVOList.size(); i++ )
    	//{
    	//	participantMedicalPlusVOList.get(i).setRelation(ParticipantVOLookup.getRelationOptionVOList());
    	//}
    	
    	// Set Gender list - Adrian @since 25 Juni 2014
    	for( int i = 0; i < participantMedicalPlusVOList.size(); i++ )
    	{
    		participantMedicalPlusVOList.get(i).setGenderList(cepr00000000ComboLookupBusiness.getGenderOptionVOList( false ));
    	}
        return participantMedicalPlusVOList;
    }
}
