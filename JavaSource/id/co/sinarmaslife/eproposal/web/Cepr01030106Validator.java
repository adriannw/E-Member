package id.co.sinarmaslife.eproposal.web;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01030104Validator
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 30, 2007 2:32:29 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Validator;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;

import org.springframework.validation.Errors;

public class Cepr01030106Validator extends Cepr01040000Validator
{
    public Cepr01030106Validator( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
    }

    public void validate()
    {
        Cepr01030106Form cepr01030106Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030106Form();
        int sum = 0;
        for( int i = 0 ; i < cepr01030106Form.getParticipantVOList().size() ; i ++ )
        {
        	if( cepr01030106Form.getParticipantVOList().get(i).getAge() != null )
        	{   
        		sum = sum + 1; 
	        	if( i == 0 ){
	    			if( cepr01030106Form.getParticipantVOList().get(i).getAge() != null && ( cepr01030106Form.getParticipantVOList().get(i).getAge() < 1 || cepr01030106Form.getParticipantVOList().get(i).getAge() > 60 ) )
	    			{
	    				errors.rejectValue( "cepr01030106Form.participantVOList["+ i + "].age", "error.insuredAgeInterval", new Object[]{ Integer.toString( 1 ), Integer.toString( 60 ) }, null );
	    			}	
	    		}else{
	    			if( cepr01030106Form.getParticipantVOList().get(i).getAge() != null && ( cepr01030106Form.getParticipantVOList().get(i).getAge() < 1 || cepr01030106Form.getParticipantVOList().get(i).getAge() > 24 ) )
	    			{
	    				errors.rejectValue( "cepr01030106Form.participantVOList["+ i + "].age", "error.insuredAgeInterval", new Object[]{ Integer.toString( 1 ), Integer.toString( 24 ) }, null );
	    			}
	    		}
        	
//        		if( sum == 1 ){
//        			if( cepr01030106Form.getParticipantVOList().get(i).getAge() < 1 || cepr01030106Form.getParticipantVOList().get(i).getAge() > 59 )
//            		{
//            			errors.rejectValue( "cepr01030106Form.participantVOList["+ i + "].age", "error.insuredAgeInterval", new Object[]{ Integer.toString( 1 ), Integer.toString( 59 ) }, null );
//            		}	
//        		}else if( sum > 1 )
//        		{
//        			if( cepr01030106Form.getParticipantVOList().get(i).getAge() < 1 || cepr01030106Form.getParticipantVOList().get(i).getAge() > 24 )
//            		{
//            			errors.rejectValue( "cepr01030106Form.participantVOList["+ i + "].age", "error.insuredAgeInterval", new Object[]{ Integer.toString( 1 ), Integer.toString( 24 ) }, null );
//            		}
//        		}
        		
        	}
        }
    }
}
