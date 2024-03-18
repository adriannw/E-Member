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

import java.util.Date;

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Validator;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.LazyConverter;

import org.springframework.validation.Errors;

public class Cepr01030113Validator extends Cepr01040000Validator
{
    public Cepr01030113Validator( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
    }

    public void validate()
    {       
        Cepr01030101Form cepr01030101Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030101Form();     
        Cepr01030103Form cepr01030103Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030103Form();
        Cepr01030113Form cepr01030113Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030113Form();
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag()) &&  CommonConst.CHECKED_TRUE.equals(cepr01030113Form.getParticipantVOList().get(0).getMedicalPlusRbFlag()) ){
        	if( cepr01030103Form.getMedicalPlusRjFlag()==null){
        		errors.rejectValue( "cepr01030113Form.participantVOList["+ 0 + "].age", "error.riderMedicalPlusRjMustBeChoose",null, null );
        	}
        }
        
        if(CommonConst.CHECKED_TRUE.equals( cepr01030113Form.getParticipantVOList().get(0).getMedicalPlusRbFlag()) && cepr01030113Form.getParticipantVOList().get(0).getSexCd().equalsIgnoreCase("L")  ){
        	errors.rejectValue( "cepr01030113Form.participantVOList["+ 0 + "].age", "error.insuredGenderMustBeWoman",null, null );        	
        }
        
        int sum = 0;
        for( int i = 0 ; i < cepr01030113Form.getParticipantVOList().size() ; i ++ )
        {        	
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag() ))
      		{
        	 if( cepr01030113Form.getParticipantVOList().get(i).getAge() != null || cepr01030113Form.getParticipantVOList().get(i).getName()!= ""  )
             {
        		if( "".equals(cepr01030113Form.getParticipantVOList().get(i).getSexCd()))
         		{
         			errors.rejectValue( "cepr01030113Form.participantVOList["+ i + "].age", "error.insuredGenderCannotBeEmpty", null, null );	
         		}	
        		if( cepr01030113Form.getParticipantVOList().get(i).getAge() == null)
         		{
         			errors.rejectValue( "cepr01030113Form.participantVOList["+ i + "].age", "error.insuredBirthdayEmpty", null, null );	
         		}
        		if( "".equals(cepr01030113Form.getParticipantVOList().get(i).getName()))
         		{
         			errors.rejectValue( "cepr01030113Form.participantVOList["+ i + "].age", "error.insuredNameCannotBeEmpty", null, null );	
         		}
        		
        		if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag() ) && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals( cepr01030113Form.getParticipantVOList().get(0).getMedicalPlusRbFlag()))
                {	
        			//if(cepr01030113Form.getParticipantVOList().get(i).getAge() != null && cepr01030113Form.getParticipantVOList().get(i).getLsre_id() == null)
	    			//{
	    			//	errors.rejectValue( "cepr01030113Form.participantVOList["+ i + "].age", "error.formRelationCannotBeEmpty", null, null );
	    			//}
	    			//else 
        			if( i == 0  ){ 				
	    				if( cepr01030113Form.getParticipantVOList().get(i).getAge() < 18 || cepr01030113Form.getParticipantVOList().get(i).getAge() > 40 )
	            		{
	            			errors.rejectValue( "cepr01030113Form.participantVOList["+ i + "].age", "error.insuredAgeInterval", new Object[]{ Integer.toString( 18 ), Integer.toString( 40 ) }, null );
	            		}	
        			}
	    			
                }        		
        		if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag() ) && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusRjFlag()))
                {	
    			
	    		//	if(cepr01030113Form.getParticipantVOList().get(i).getLsre_id() == null)
	    		//	{
	    		//		errors.rejectValue( "cepr01030113Form.participantVOList["+ i + "].age", "error.formRelationCannotBeEmpty", null, null );
	    		//	}
	    		//	else
        			if( i == 0  ){
	    				if( cepr01030113Form.getParticipantVOList().get(i).getAge() != null && ( cepr01030113Form.getParticipantVOList().get(i).getAge() < 17 || cepr01030113Form.getParticipantVOList().get(i).getAge() > 60 ) )
		    			{
		    				errors.rejectValue( "cepr01030113Form.participantVOList["+ i + "].age", "error.insuredAgeInterval", new Object[]{ Integer.toString( 17 ), Integer.toString( 60 ) }, null );
		    			}
	    				
	    			}else if( i > 0  )
	    			{
	    				if(cepr01030113Form.getParticipantVOList().get(i).getBirthDay() != null){
		    				String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030113Form.getParticipantVOList().get(i).getBirthDay());
//		    				String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030113Form.getParticipantVOList().get(i).getBirthDay());
			        		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<15 )
			        		{
			        			errors.rejectValue( "cepr01030113Form.participantVOList["+ i + "].age", "error.insuredAgeMinDay", new Object[]{ Integer.toString( 15 )  }, null );
			        		}else if(  cepr01030113Form.getParticipantVOList().get(i).getAge() != null && cepr01030113Form.getParticipantVOList().get(i).getAge() > 24 )
			    	        {
			    	            errors.rejectValue( "cepr01030113Form.participantVOList["+ i + "].age", "error.insuredAgeMax", new Object[]{ Integer.toString( 24 ) }, null );
			    	        }	
	    				}else{	    				
		    				if(  cepr01030113Form.getParticipantVOList().get(i).getAge() != null && cepr01030113Form.getParticipantVOList().get(i).getAge() > 24 )
			    	        {
			    	            errors.rejectValue( "cepr01030113Form.participantVOList["+ i + "].age", "error.insuredAgeMax", new Object[]{ Integer.toString( 24 ) }, null );
			    	        }
	    				}
	    			}
                }
        		else{ 
        		//	if(cepr01030113Form.getParticipantVOList().get(i).getLsre_id() == null)
	    		//	{
	    		//		errors.rejectValue( "cepr01030113Form.participantVOList["+ i + "].age", "error.formRelationCannotBeEmpty", null, null );
	    		//	}
	    		//	else 
        			if( i == 0  ){
	    				if( cepr01030113Form.getParticipantVOList().get(i).getAge() != null && ( cepr01030113Form.getParticipantVOList().get(i).getAge() < 17 || cepr01030113Form.getParticipantVOList().get(i).getAge() > 65 ) )
		    			{
		    				errors.rejectValue( "cepr01030113Form.participantVOList["+ i + "].age", "error.insuredAgeInterval", new Object[]{ Integer.toString( 17 ), Integer.toString( 65 ) }, null );
		    			}
	    				
	    			}else if( i > 0  )
	    			{
	    				if(cepr01030113Form.getParticipantVOList().get(i).getBirthDay() != null){
		    				String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030113Form.getParticipantVOList().get(i).getBirthDay());
//		    				String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030113Form.getParticipantVOList().get(i).getBirthDay());
			        		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<15 )
			        		{
			        			errors.rejectValue( "cepr01030113Form.participantVOList["+ i + "].age", "error.insuredAgeMinDay", new Object[]{ Integer.toString( 15 )  }, null );
			        		}else if(  cepr01030113Form.getParticipantVOList().get(i).getAge() != null && cepr01030113Form.getParticipantVOList().get(i).getAge() > 24 )
			    	        {
			    	            errors.rejectValue( "cepr01030113Form.participantVOList["+ i + "].age", "error.insuredAgeMax", new Object[]{ Integer.toString( 24 ) }, null );
			    	        }	
	    				}else{	    				
		    				if(  cepr01030113Form.getParticipantVOList().get(i).getAge() != null && cepr01030113Form.getParticipantVOList().get(i).getAge() > 24 )
			    	        {
			    	            errors.rejectValue( "cepr01030113Form.participantVOList["+ i + "].age", "error.insuredAgeMax", new Object[]{ Integer.toString( 24 ) }, null );
			    	        }
	    				}
	    			}        			
        		}
             }	
        	}else{        		
        	
        	if( i == 0 || i == 1 ){
        		if( cepr01030113Form.getParticipantVOList().get(i).getAge() != null && ( cepr01030113Form.getParticipantVOList().get(i).getAge() < 17 || cepr01030113Form.getParticipantVOList().get(i).getAge() > 60 ) )
        		{
        			errors.rejectValue( "cepr01030113Form.participantVOList["+ i + "].age", "error.insuredAgeInterval", new Object[]{ Integer.toString( 17 ), Integer.toString( 60 ) }, null );
        		}	
        	}
        	else{
        		if( cepr01030113Form.getParticipantVOList().get(i).getAge() != null && ( cepr01030113Form.getParticipantVOList().get(i).getAge() < 1 || cepr01030113Form.getParticipantVOList().get(i).getAge() > 24 ) )
        		{
        			errors.rejectValue( "cepr01030113Form.participantVOList["+ i + "].age", "error.insuredAgeInterval", new Object[]{ Integer.toString( 1 ), Integer.toString( 24 ) }, null );
        		}
        	}

        	}
        }
    }
}
