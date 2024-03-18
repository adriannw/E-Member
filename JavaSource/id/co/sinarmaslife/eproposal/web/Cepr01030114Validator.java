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
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Validator;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.product.product01040211.Cepr01040211Mapper;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.LazyConverter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.validation.Errors;

public class Cepr01030114Validator extends Cepr01040000Validator
{
    public Cepr01030114Validator( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
    }

    public void validate()
    {
    	Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
    	Cepr01030103Form cepr01030103Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030103Form();
        Cepr01030114Form cepr01030114Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030114Form();
        int sum = 0;
        for( int i = 0 ; i < cepr01030114Form.getParticipantVOList().size() ; i ++ )
        {
        	if(  !cepr01030114Form.getParticipantVOList().get(i).getName().equals("")  ) 
        	{	
        		if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatExtraFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030103Form.getEkaSehatExtraDisplay()) )
        		{        	
        			if(cepr01030114Form.getParticipantVOList().get(i).getAge() == null)
	    			{
        				errors.rejectValue( "cepr01030114Form.participantVOList["+ i + "].age", "error.generalMsg", new Object[]{ new String("usia harus diisi!") }, null );
	    			}
        			
        			if(cepr01030114Form.getParticipantVOList().get(i).getLsre_id() == null)
	    			{
	    				errors.rejectValue( "cepr01030114Form.participantVOList["+ i + "].age", "error.formRelationCannotBeEmpty", null, null );
	    			}//SME
        			else if(cepr01030114Form.getParticipantVOList().get(i).getLsre_id() == 2 || cepr01030114Form.getParticipantVOList().get(i).getLsre_id() == 5 || cepr01030114Form.getParticipantVOList().get(i).getLsre_id() == 7)
	    			{
        				if( cepr01030114Form.getParticipantVOList().get(i).getAge() != null && cepr01030114Form.getParticipantVOList().get(i).getAge() < 17 )
		    			{
		    				errors.rejectValue( "cepr01030114Form.participantVOList["+ i + "].age", "error.insuredAgeMin", new Object[]{ Integer.toString( 17 ) }, null );
		    			}else if( cepr01030114Form.getParticipantVOList().get(i).getAge() != null && cepr01030114Form.getParticipantVOList().get(i).getAge() > 64 )
		    			{
		    				errors.rejectValue( "cepr01030114Form.participantVOList["+ i + "].age", "error.insuredAgeMax", new Object[]{ Integer.toString( 64 ) }, null );
		    			}	
        				
	    			}else if(cepr01030114Form.getParticipantVOList().get(i).getLsre_id() == 4 || cepr01030114Form.getParticipantVOList().get(i).getLsre_id() == 1)
	    			{
	    				
	    				
	    			
	    				if( cepr01030114Form.getParticipantVOList().get(i).getAge() != null ){
	    					
	    					 if( cepr01030114Form.getParticipantVOList().get(i).getAge() > 0 ){
	    					if(cepr01030114Form.getParticipantVOList().get(i).getLsre_id() == 1){
	    						if( cepr01030114Form.getParticipantVOList().get(i).getAge() > 64 ){
		    						 errors.rejectValue( "cepr01030114Form.participantVOList["+ i + "].age", "error.insuredAgeMax", new Object[]{ Integer.toString( 64 ) }, null );
	    						}
	    						
	    					}else{
	    						if( cepr01030114Form.getParticipantVOList().get(i).getAge() > 24 ){
		    						 errors.rejectValue( "cepr01030114Form.participantVOList["+ i + "].age", "error.insuredAgeMax", new Object[]{ Integer.toString( 24 ) }, null );
	    						}
	    						
	    					}
	    					
	    							
	    				}else if( cepr01030114Form.getParticipantVOList().get(i).getAge() == 0 ){
	    					String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030114Form.getParticipantVOList().get(i).getBirthDay());
//	        				String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030107Form.getParticipantVOList().get(i).getBirthDay());
	    	        		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<15 )
	    	        		{
	    	        			errors.rejectValue( "cepr01030114Form.participantVOList["+ i + "].age", "error.insuredAgeMinDay", new Object[]{ Integer.toString( 15 )  }, null );
	    	        		}else if(umurTertanggung != null && LazyConverter.toInt(umurTertanggung)>14){
	        					cepr01030114Form.getParticipantVOList().get(i).setAge(1);
	        				}
	    					
	    				}
	    				
	    				
	    			}
        			        			
        		}
        		/*
        		else{
        			sum = sum + 1; 
        			if( cepr01030114Form.getParticipantVOList().get(i).getBirthDay() != null){
        				String stringDateFormat = "22/12/2017";
        				DateFormat formatter = null;

        		        formatter =new SimpleDateFormat("dd/MM/yyyy");
        		        Date convertedDate=null;
						try {
							convertedDate = (Date) formatter.parse(stringDateFormat);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

        				String umurTertanggung = FormatDate.getUmur(convertedDate, cepr01030114Form.getParticipantVOList().get(i).getBirthDay());
        				String umr= String.valueOf(LazyConverter.toInt(umurTertanggung));
        				if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<14 )
    	        		{
    	        			errors.rejectValue( "cepr01030107Form.participantVOList["+ i + "].age", "error.insuredAgeMinDay", new Object[]{ Integer.toString( 15 )  }, null );
    	        		}else if( umurTertanggung != null && umr.length()<=4 && LazyConverter.toInt(umurTertanggung)>=14 ){
    	        			cepr01030114Form.getParticipantVOList().get(i).setAge(1);
    	        		}
        			}
		        	if( i == 0 ){
		    			if( cepr01030114Form.getParticipantVOList().get(i).getAge() != null && cepr01030114Form.getParticipantVOList().get(i).getAge() > 65 )
		    			{
		    				errors.rejectValue( "cepr01030107Form.participantVOList["+ i + "].age", "error.insuredAgeInterval", new Object[]{ Integer.toString( 1 ), Integer.toString( 65 ) }, null );
		    			}	
		    		}else{
		    			if( cepr01030114Form.getParticipantVOList().get(i).getAge() != null && cepr01030114Form.getParticipantVOList().get(i).getAge() > 24 )
		    			{
		    				errors.rejectValue( "cepr01030107Form.participantVOList["+ i + "].age", "error.insuredAgeInterval", new Object[]{ Integer.toString( 1 ), Integer.toString( 24 ) }, null );
		    			}
		    		}
        		}*/
        	}
        }

        }
    }
}
