package id.co.sinarmaslife.eproposal.business;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040101Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : Andy
 * Version              : 1.0
 * Creation Date    	: Aug 30, 2007 10:31:35 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.lookup.Cepr00000000ComboLookupBusiness;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030501Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030502Form;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.model.vo.TotalPage;
import id.co.sinarmaslife.standard.model.vo.ViewListProvider;
import id.co.sinarmaslife.standard.model.vo.ViewListProviderDetail;
import id.co.sinarmaslife.standard.util.HtmlUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cepr01030502Business
{
    protected final Log logger = LogFactory.getLog( getClass() );

    protected EproposalManager eproposalManager;
    protected Cepr00000000EditorUtil editorUtil;

    public Cepr01030502Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil )
    {
        this.eproposalManager = eproposalManager;
        this.editorUtil = editorUtil;
    }

    public void resetForm( Cepr01030502Form cepr01030502Form )
    {
        //cepr01030502Form.setProposalDate( cepr00000000GeneralBusiness.selectNowDate() );
//        cepr01040103Form.setAssurancePlanList( cepr00000000ComboLookupBusiness.selectLstBusinessOptionVOList( true, null ) );
    }
    
    public Map<String, Object> selectProviderDetailList( Object command, String selectedRsId )
    {
    	//-DETAIL-----------------------------------------------------------------------------------------------        

    	//Cepr01050102Form cepr01050102Form = cepr01030000HoldingForm.getCepr01050102Form();
    	
    	Map<String, Object> result = new HashMap<String, Object>();
    		
    		//String selectedRsId = request.getParameter( "selectedRsId" );
    		List<ViewListProviderDetail> detailRs = selectAllProviderDetailList(selectedRsId);
    		//request.setAttribute( "detailRs", detailRs );
    		result.put( "rsNama", detailRs.get(0).getRsNama() );
    		result.put( "rsAlamat", detailRs.get(0).getRsAlamat() );
    		result.put( "rsKodePos", detailRs.get(0).getRsKodePos() );
    		result.put( "rsTelepon", detailRs.get(0).getRsTelepon() );
    		result.put( "rsFax", detailRs.get(0).getRsFax() );
    		
    		return result;
    }
    
    public List<ViewListProviderDetail> selectAllProviderDetailList( String rsId )
    {
    	logger.info( "*-*-*-* Cepr01030502Business.selectAllProviderDetailList" );
    	List<ViewListProviderDetail> result = new ArrayList<ViewListProviderDetail>();
    	//String ljpId = cepr01040103Form.getLjpId;
    	//Integer lstbId = 1;
    	
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put( "RSID", rsId );
    	
    	//todo
    	
    	result = eproposalManager.selectAllProviderDetailList( params );
    	logger.info( "params = " + params );
    	logger.info( "result = " + result );
    	
    	
    	//LSTB_ID, LSTB_NAME
    	//String lstbId;
    	//String lstbName;
    	
    	//for( Map resultMap : result )
    	//{
    	//	lstbId = ( String ) resultMap.get( "LSTB_ID" );
    	//	lstbName = ( String ) resultMap.get( "LSTB_NAME" );
    	//}
    	
    	return result;
    }
    
    
}
