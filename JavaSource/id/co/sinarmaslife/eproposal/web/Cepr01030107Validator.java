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

public class Cepr01030107Validator extends Cepr01040000Validator
{
    public Cepr01030107Validator( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
    }

    public void validate()
    {
    	Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
    	Cepr01030103Form cepr01030103Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030103Form();
        Cepr01030107Form cepr01030107Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030107Form();
        int sum = 0;
        for( int i = 0 ; i < cepr01030107Form.getParticipantVOList().size() ; i ++ )
        {
        	if( cepr01030107Form.getParticipantVOList().get(i).getAge() != null )
        	{	
        		if(cepr01030107Form.getParticipantVOList().get(i).getAge() == 0){
            		String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030107Form.getParticipantVOList().get(i).getBirthDay());		

            		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<15 )
            		{
            			errors.rejectValue( "cepr01030107Form.participantVOList["+ i + "].age", "error.insuredAgeMinDay", new Object[]{ Integer.toString( 15 )  }, null );
            		}else if(umurTertanggung != null && LazyConverter.toInt(umurTertanggung)>14){
            			cepr01030107Form.getParticipantVOList().get(i).setAge(1);
    				}
    				
            	}
        		else if(cepr01030107Form.getParticipantVOList().get(i).getLsre_id() == 4){
    					if( cepr01030107Form.getParticipantVOList().get(i).getAge() > 24 ){
    						 errors.rejectValue( "cepr01030107Form.participantVOList["+ i + "].age", "error.insuredAgeInterval", new Object[]{ Integer.toString( 1 ), Integer.toString( 24 ) }, null );
    					}
    					
    				}else{
    					if( cepr01030107Form.getParticipantVOList().get(i).getAge() > 65 ){
    						 errors.rejectValue( "cepr01030107Form.participantVOList["+ i + "].age", "error.insuredAgeInterval", new Object[]{ Integer.toString( 1 ), Integer.toString( 65 ) }, null );
    					}
    					
    				}  			
        		
        	}
}
}
}
