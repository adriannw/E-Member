package id.co.sinarmaslife.eproposal.business;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01030104Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 30, 2007 8:54:00 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.lookup.LookupList;
import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.eproposal.web.Cepr01030104Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;

import java.math.BigDecimal;
import java.util.List;

public class Cepr01030104Business
{
    protected final Log logger = LogFactory.getLog( getClass() );

    protected EproposalManager eproposalManager;
    protected Cepr00000000EditorUtil editorUtil;

    public Cepr01030104Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil )
    {
        this.eproposalManager = eproposalManager;
        this.editorUtil = editorUtil;
    }

    public void resetForm( Cepr01030104Form cepr01030104Form )
    {
        logger.info( "*-*-*-* Cepr01030104Business.resetForm" );
        cepr01030104Form.setTopupDefaultAmount( new BigDecimal( "0" ) );
        cepr01030104Form.setDrawDefaultAmount( new BigDecimal( "0" ) );
        cepr01030104Form.setTopupDrawVOList( LookupList.getTopupDrawVOList() );
//        cepr01030104Form.setTopupDefaultAmount( new BigDecimal( "0" ) );
//        cepr01030104Form.setDrawDefaultAmount( new BigDecimal( "0" ) );
//        cepr01030104Form.setTopupDrawVOList( LookupList.getTopupDrawVOList() );
//        if( cepr01030104Form.getTopupDrawVOList() != null && cepr01030104Form.getTopupDrawVOList().size() > 0 ){
//	        for( int i = 0 ; i < cepr01030104Form.getTopupDrawVOList().size() ; i ++ ){
//	        	cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount( BigDecimal.ZERO );
//	        	cepr01030104Form.getTopupDrawVOList().get(i).setTopupAmount( BigDecimal.ZERO );
//	        	cepr01030104Form.getTopupDrawVOList().get(i).setYearFlag( CommonConst.CHECKED_FALSE );
//	        }
//        }
    }

//    public void fillDefaultValueEachTimeFormCalled( Object command )
//    {
//        logger.info( "*-*-*-* Cepr01030104Business.fillDefaultValueEachTimeFormCalled" );
//        Cepr01030104Form cepr01030104Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030104Form();
//        cepr01030104Form.setTotalTopupAmount( computeTotalTopup( cepr01030104Form.getTopupDrawVOList() ) );
//        cepr01030104Form.setTotalDrawAmount( computeTotalDraw( cepr01030104Form.getTopupDrawVOList() ) );
//    }

    public BigDecimal computeTotalTopup( List<TopupDrawVO> topupDrawVOList)
    {
        logger.info( "*-*-*-* Cepr01030104Business.computeTotalTopup" );
        BigDecimal result = new BigDecimal( "0" );
        for( TopupDrawVO vo : topupDrawVOList )
        {
        	if( vo.getYearFlag() != null ){
        		result = result.add( vo.getTopupAmount() );
        	}else if( vo.getYearFlag() == null ){
        		vo.setTopupAmount( BigDecimal.ZERO );
        	}
        }
        return result;
    }

    public BigDecimal computeTotalDraw( List<TopupDrawVO> topupDrawVOList )
    {
        logger.info( "*-*-*-* Cepr01030104Business.computeTotalDraw" );
        BigDecimal result = new BigDecimal( "0" );
        for( TopupDrawVO vo : topupDrawVOList )
        {
        	if( vo.getYearFlag() != null ){
        		result = result.add( vo.getDrawAmount() );
        	}else if( vo.getYearFlag() == null ){
        		vo.setDrawAmount( BigDecimal.ZERO );
        	}
        }
        return result;
    }
    
    public void clearValueTopUpAndDraw( List<TopupDrawVO> topupDrawVOList ){
//        for( TopupDrawVO vo : topupDrawVOList )
//        {
//        	if( vo.getYearFlag() == null ){
//        		vo.setDrawAmount( BigDecimal.ZERO );
//        		vo.setTopupAmount( BigDecimal.ZERO );
//        	}
//        }
    	
    }

}
