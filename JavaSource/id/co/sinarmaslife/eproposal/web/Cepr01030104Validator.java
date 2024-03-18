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

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Validator;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.validation.Errors;

public class Cepr01030104Validator extends Cepr01040000Validator
{
    public Cepr01030104Validator( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
    }

    public void validate()
    {
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        Cepr01030104Form cepr01030104Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030104Form();
        List<TopupDrawVO> topupDrawVOList = cepr01030104Form.getTopupDrawVOList();

        cepr01030104Form.setTotalTopupAmount(new BigDecimal(0));        
        
        TopupDrawVO topupDrawVO;
        for( int i = 0; i < topupDrawVOList.size(); i++ )
        {
            topupDrawVO = topupDrawVOList.get( i );
            if( CommonConst.CHECKED_TRUE.equals( topupDrawVO.getYearFlag() ) )
            {
                if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                {
                	
                	if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_SYARIAH && cepr01030102Form.getRightPartOfBusinessCd() == 2){
                                		
                		if( topupDrawVO.getDrawAmount().compareTo( new BigDecimal( "1000000" ) ) < 0 && topupDrawVO.getTopupAmount().equals( new BigDecimal( "0" ) ) )
                        {
                              errors.rejectValue( "cepr01030104Form.topupDrawVOList["+ i + "].drawAmount", "error.valueAtLeast", new Object[]{ "Rp.1.000,000.00"}, null );
                        } else if( topupDrawVO.getTopupAmount().compareTo( new BigDecimal( "1000000" ) ) < 0  && topupDrawVO.getDrawAmount().equals( new BigDecimal( "0" ) ) )
                        {
                            errors.rejectValue( "cepr01030104Form.topupDrawVOList["+ i + "].topupAmount", "error.valueAtLeast", new Object[]{ "Rp.1.000,000.00"}, null );
                        } else if( (topupDrawVO.getDrawAmount().compareTo( new BigDecimal( "1000000" ) ) < 0 && topupDrawVO.getDrawAmount().compareTo( new BigDecimal( "0" ) ) > 0 )
                        		|| ( topupDrawVO.getTopupAmount().compareTo( new BigDecimal( "0" ) ) > 0 && topupDrawVO.getTopupAmount().compareTo( new BigDecimal( "1000000" ) ) < 0 ))
                        {
                            errors.rejectValue( "cepr01030104Form.topupDrawVOList["+ i + "].drawAmount", "error.valueAtLeast", new Object[]{ "Rp.1.000,000.00"}, null );
                        }
                        	
                		                        	
                		if( topupDrawVO.getTopupAmount().compareTo( new BigDecimal( "50000000000" ) ) > 0  )
                        {
                              errors.rejectValue( "cepr01030104Form.topupDrawVOList["+ i + "].topupAmount", "error.valueMax", new Object[]{ "Rp 50.000,000,000.00"}, null );
                        }
                		
                		
                	}else{
                		  if( topupDrawVO.getTopupAmount().compareTo( new BigDecimal( "1000000" ) ) < 0 && topupDrawVO.getDrawAmount().equals( new BigDecimal( "0" ) ) )
                          {
                              errors.rejectValue( "cepr01030104Form.topupDrawVOList["+ i + "].topupAmount", "error.valueAtLeast", new Object[]{ "Rp.1.000,000.00"}, null );
                          }
                          else if( topupDrawVO.getDrawAmount().compareTo( new BigDecimal( "1000000" ) ) < 0 && topupDrawVO.getTopupAmount().equals( new BigDecimal( "0" ) ) )
                          {
                              errors.rejectValue( "cepr01030104Form.topupDrawVOList["+ i + "].drawAmount", "error.valueAtLeast", new Object[]{ "Rp.1.000,000.00"}, null );
                          }
                          else if( topupDrawVO.getDrawAmount().compareTo( new BigDecimal( "1000000" ) ) < 0 && topupDrawVO.getTopupAmount().compareTo( new BigDecimal( "1000000" ) ) < 0 )
                          {
                              errors.rejectValue( "cepr01030104Form.topupDrawVOList["+ i + "].topupAmount", "error.valueAtLeast", new Object[]{ "Rp.1.000,000.00"}, null );
                          }
                          
                          if( topupDrawVO.getTopupAmount().compareTo( new BigDecimal( "10000000000" ) ) > 0 && topupDrawVO.getDrawAmount().equals( new BigDecimal( "0" ) ) )
                          {
                              errors.rejectValue( "cepr01030104Form.topupDrawVOList["+ i + "].topupAmount", "error.valueMax", new Object[]{ "Rp.10.000,000,000.00"}, null );
                          }
                		
                	}
                	                    
                    cepr01030104Form.setTotalTopupAmount(cepr01030104Form.getTotalTopupAmount().add(topupDrawVO.getTopupAmount()));   
                    
                }
            }
        }
        
      	if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_SYARIAH && cepr01030102Form.getRightPartOfBusinessCd() == 2){
	      	 if( cepr01030104Form.getTotalTopupAmount().compareTo( new BigDecimal( "100000000000" ) ) > 0  )
	         {
	                 errors.rejectValue( "cepr01030104Form.totalTopupAmount", "error.valueMax", new Object[]{ "Rp.100.000,000,000.00"}, null );
	         }
      	}else{
	      	  if( cepr01030104Form.getTotalTopupAmount().compareTo( new BigDecimal( "30000000000" ) ) > 0  )
	          {
	              errors.rejectValue( "cepr01030104Form.totalTopupAmount", "error.valueMax", new Object[]{ "Rp.30.000,000,000.00"}, null );
	          }
      	}
        
    }
}
