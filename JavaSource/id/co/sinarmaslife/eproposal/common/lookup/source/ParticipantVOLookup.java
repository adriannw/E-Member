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
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.standard.model.vo.OptionVO;

import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ParticipantVOLookup
{
    private EproposalManager eproposalManager;
    
    public EproposalManager getEproposalManager() { return this.eproposalManager; }
    public void setEproposalManager(EproposalManager eproposalManager) { this.eproposalManager = eproposalManager; }
	
	public static List<ParticipantVO> getParticipantVOList()
    {
    	List<ParticipantVO> participantVOList = new ArrayList<ParticipantVO>();
    	participantVOList = new ArrayList<ParticipantVO>();
    	ParticipantVO participantVO;
    	
    	for( int i = 1; i <= 4; i++ )
    	{
    		participantVO = new ParticipantVO();
    		participantVOList.add( participantVO );
    	}
    	
    	for( int i = 0; i < participantVOList.size(); i++ )
    	{
//    		if( i == 0 )participantVOList.get(i).setStatus("pasangan");
//    		else participantVOList.get(i).setStatus("anak");
    		participantVOList.get(i).setStatus("T.Tambahan " + (i+1));
    	}
    	
    	// Set the relation list - Daru @since 06 Mar 2014
    	for( int i = 0; i < participantVOList.size(); i++ )
    	{
    		participantVOList.get(i).setRelation(getRelationOptionVOList());
    	}
    	
        return participantVOList;
    }
	
	/**
	 * Get a list of relation available for relation option
	 * @return List<OptionVO>
	 * @author Daru
	 * @since Mar 6, 2014
	 */
	public static List<OptionVO> getRelationOptionVOList()
	{
		List<OptionVO> relation = new ArrayList<OptionVO>();
		relation.add(new OptionVO("1", "Diri Sendiri"));
		relation.add(new OptionVO("7", "Adik/Kakak Kandung"));		
		relation.add(new OptionVO("4", "Anak Kandung"));	
		relation.add(new OptionVO("2", "Orang Tua Kandung"));		
		relation.add(new OptionVO("5", "Suami/Istri"));
		return relation;
	}
}
