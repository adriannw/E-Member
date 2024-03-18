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

import org.springframework.validation.Errors;

public class Cepr01030109Validator extends Cepr01040000Validator
{
    public Cepr01030109Validator( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
    }

    public void validate()
    {
        Cepr01030109Form cepr01030109Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030109Form();
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        Cepr01030103Form cepr01030103Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030103Form();
        int sum = 0;
        for( int i = 0 ; i < cepr01030109Form.getParticipantVOList().size() ; i ++ )
        {	
        	
        	if( cepr01030102Form.getLeftPartOfBusinessCd() ==  Hardcode.PRODUK_CERDAS && (cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X22 || 
        			cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X23 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X24))
        	{
        		if(cepr01030109Form.getParticipantVOList().get(i).getAge() != null && cepr01030109Form.getParticipantVOList().get(i).getBirthDay() == null)
        		{
        			errors.rejectValue( "cepr01030109Form.participantVOList["+ i + "].age", "error.insuredBirthdayEmpty", null, null);
        		}
        		else if(cepr01030109Form.getParticipantVOList().get(i).getBirthDay() != null)
        		{  
        			String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030109Form.getParticipantVOList().get(i).getBirthDay());
//        			String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030109Form.getParticipantVOList().get(i).getBirthDay());
	        		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<15 )
	        		{
	        			errors.rejectValue( "cepr01030109Form.participantVOList["+ i + "].age", "error.insuredAgeMinDay", new Object[]{ Integer.toString( 15 )  }, null );
	        		}else if(  cepr01030109Form.getParticipantVOList().get(i).getAge() != null && cepr01030109Form.getParticipantVOList().get(i).getAge() > 60 )
	    	        {
	    	            errors.rejectValue( "cepr01030109Form.participantVOList["+ i + "].age", "error.insuredAgeMax", new Object[]{ Integer.toString( 60 ) }, null );
	    	        }
        		}
        	}
        	else if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpProviderFlag() ) || cepr01030102Form.getLeftPartOfBusinessCd() ==  Hardcode.PRODUK_VIP_HOSPITAL_PLAN )
      		{
    			if( cepr01030109Form.getParticipantVOList().get(i).getAge() != null )
            	{	
    			
	    			if(cepr01030109Form.getParticipantVOList().get(i).getLsre_id() == null)
	    			{
	    				errors.rejectValue( "cepr01030109Form.participantVOList["+ i + "].age", "error.formRelationCannotBeEmpty", null, null );
	    			}
	    			else if(cepr01030109Form.getParticipantVOList().get(i).getLsre_id() == 2 || cepr01030109Form.getParticipantVOList().get(i).getLsre_id() == 5)
	    			{
	    				if( cepr01030109Form.getParticipantVOList().get(i).getBirthDay() == null)
	            		{
	            			errors.rejectValue( "cepr01030109Form.participantVOList["+ i + "].age", "error.insuredBirthdayEmpty", null, null);
	            		}else{
		    				String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030109Form.getParticipantVOList().get(i).getBirthDay());
//		    				String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030109Form.getParticipantVOList().get(i).getBirthDay());
			        		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<15 )
			        		{
			        			errors.rejectValue( "cepr01030109Form.participantVOList["+ i + "].age", "error.insuredAgeMinDay", new Object[]{ Integer.toString( 15 )  }, null );
			        		}else if(  cepr01030109Form.getParticipantVOList().get(i).getAge() != null && cepr01030109Form.getParticipantVOList().get(i).getAge() > 64 )
			    	        {
			    	            errors.rejectValue( "cepr01030109Form.participantVOList["+ i + "].age", "error.insuredAgeMax", new Object[]{ Integer.toString( 64 ) }, null );
			    	        }
	            		}
	    			}else if(cepr01030109Form.getParticipantVOList().get(i).getLsre_id() == 7 || cepr01030109Form.getParticipantVOList().get(i).getLsre_id() == 4)
	    			{
	    				if( cepr01030109Form.getParticipantVOList().get(i).getAge() != null && ( cepr01030109Form.getParticipantVOList().get(i).getAge() < 1 || cepr01030109Form.getParticipantVOList().get(i).getAge() > 24 ) )
		    			{
		    				errors.rejectValue( "cepr01030109Form.participantVOList["+ i + "].age", "error.insuredAgeInterval", new Object[]{ Integer.toString( 1 ), Integer.toString( 24 ) }, null );
		    			}    				
	    			}
            	}
        	}
        	else{
	        	if( i == 0 ){
	    			if( cepr01030109Form.getParticipantVOList().get(i).getAge() != null && ( cepr01030109Form.getParticipantVOList().get(i).getAge() < 1 || cepr01030109Form.getParticipantVOList().get(i).getAge() > 60 ) )
	    			{
	    				errors.rejectValue( "cepr01030109Form.participantVOList["+ i + "].age", "error.insuredAgeInterval", new Object[]{ Integer.toString( 1 ), Integer.toString( 60 ) }, null );
	    			}	
	    		}else{
	    			if( cepr01030109Form.getParticipantVOList().get(i).getAge() != null && ( cepr01030109Form.getParticipantVOList().get(i).getAge() < 1 || cepr01030109Form.getParticipantVOList().get(i).getAge() > 24 ) )
	    			{
	    				errors.rejectValue( "cepr01030109Form.participantVOList["+ i + "].age", "error.insuredAgeInterval", new Object[]{ Integer.toString( 1 ), Integer.toString( 24 ) }, null );
	    			}
	    		}
	//        	if( cepr01030109Form.getParticipantVOList().get(i).getAge() != null )
	//        	{   sum = sum + 1; 
	//        		if( sum == 1 ){
	//        			if( cepr01030109Form.getParticipantVOList().get(i).getAge() < 1 || cepr01030109Form.getParticipantVOList().get(i).getAge() > 59 )
	//            		{
	//            			errors.rejectValue( "cepr01030109Form.participantVOList["+ i + "].age", "error.insuredAgeInterval", new Object[]{ Integer.toString( 1 ), Integer.toString( 59 ) }, null );
	//            		}	
	//        		}else if( sum > 1 )
	//        		{
	//        			if( cepr01030109Form.getParticipantVOList().get(i).getAge() < 1 || cepr01030109Form.getParticipantVOList().get(i).getAge() > 24 )
	//            		{
	//            			errors.rejectValue( "cepr01030109Form.participantVOList["+ i + "].age", "error.insuredAgeInterval", new Object[]{ Integer.toString( 1 ), Integer.toString( 24 ) }, null );
	//            		}
	//        		}
	//        		
	//        	}
        	}
        }
    }
}
