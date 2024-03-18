package id.co.sinarmaslife.eproposal.business;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-eprical
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


import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01020201Form;
import id.co.sinarmaslife.eproposal.common.lookup.Cepr00000000ComboLookupBusiness;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01020000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01020201Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01020201Form;
import id.co.sinarmaslife.eproposal.web.Cepr01020201Form;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.model.vo.UserGroupId;
import id.co.sinarmaslife.standard.model.vo.ViewListGroup;
import id.co.sinarmaslife.standard.model.vo.TotalPage;
import id.co.sinarmaslife.standard.model.vo.UserGroup;
import id.co.sinarmaslife.standard.model.vo.ViewListAgent;
import id.co.sinarmaslife.standard.model.vo.ViewListProvider;
//import id.co.sinarmaslife.standard.model.vo.ViewListProviderDetail;

import id.co.sinarmaslife.standard.util.HtmlUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cepr01020201Business
{
    protected final Log logger = LogFactory.getLog( getClass() );

    protected EproposalManager eproposalManager;
    protected Cepr00000000EditorUtil editorUtil;

    public Cepr01020201Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil )
    {
        this.eproposalManager = eproposalManager;
        this.editorUtil = editorUtil;
    }

    public void resetForm( Cepr01020201Form cepr01020201Form )
    {
        //cepr01020201Form.setProposalDate( cepr00000000GeneralBusiness.selectNowDate() );
//        cepr01040103Form.setAssurancePlanList( cepr00000000ComboLookupBusiness.selectLstBusinessViewListGroupList( true, null ) );
    }
    
    public List<ViewListGroup> selectGroupList( String group, String noPage, String noRow, String sortRow )
    {
    	logger.info( "*-*-*-* Cepr01020201Business.selectGroupList" );
    	List<ViewListGroup> result = new ArrayList<ViewListGroup>();
    	//String ljpId = cepr01040103Form.getLjpId;
    	//Integer lstbId = 1;
    	
    	if(group == ""){group = null;}
    	
    	if("sortedByGroup".equals(sortRow)){
    		sortRow = "GROUP_NAME";
    	}
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put( "group_name", group );
    	params.put( "noPage", Integer.parseInt(noPage) );
    	params.put( "noRow", Integer.parseInt(noRow) );
    	
    	//todo
    	
    	result = eproposalManager.selectGroupList( params );
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
    
    public List<TotalPage> selectTotalGroupPage( String noRow, String group)
    {
    	logger.info( "*-*-*-* Cepr01020201Business.selectTotalGroupPage" );
    	List<TotalPage> result = new ArrayList<TotalPage>();
    	
    	if(group == ""){group = null;}
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put( "group_name", group );
    	params.put( "noRow", Integer.parseInt(noRow) );

    	result = eproposalManager.selectTotalGroupPage( params );
    	
    	return result;
    }
    
    public Map<String, Object> selectGroupList(Object command, String cPage, String sortRow){
    	
    	Cepr01020201Form cepr01020201Form = ( ( Cepr01020000HoldingForm ) command ).getCepr01020201Form();
    	
    	Map<String, Object> result = new HashMap<String, Object>();
        
        //Map<String, Object> map = new HashMap<String, Object>();
        
        //Cepr01020000HoldingForm cepr01020000HoldingForm = (Cepr01020000HoldingForm) command;
        
        
//-COMBOBOX-----------------------------------------------------------------------------------------------
        
        // LOGIKA UNTUK AMBIL ISI rsNama, HANDLE rsNama=NULL
        if( cepr01020201Form.getGroupName() == null ){ cepr01020201Form.setGroupName(""); }
        String group = cepr01020201Form.getGroupName();
        
        
 
//-COMBOBOX-Records/Page------------------------------------------------------------------------------------
        
        // UNTUK COMBOBOX ISI Records/Page
        List<OptionVO> listRecords = new ArrayList();
        listRecords.add(new OptionVO( "10" , "10" ));
        listRecords.add(new OptionVO( "25" , "25" ));
        listRecords.add(new OptionVO( "50" , "50" ));
        listRecords.add(new OptionVO( "100" , "100" ));
        listRecords.add(new OptionVO( "250" , "250" ));
        listRecords.add(new OptionVO( "500" , "500" ));
        cepr01020201Form.setListRecords(listRecords);
                

        
//-SEARCH-----------------------------------------------------------------------------------------------        
        
        String noPage = "";
        String noRow = "0";
        
        // MULAI HANDLE BAGIAN SEARCH DI PAGE Cepr01020201JSP
        //if( page == 0 || "onPressButtonSearch".equals( theEvent ) )
        //{	
        	
            if(cPage == null || cPage == "" ){
            	noPage = "1";
            }else{
            	noPage = cPage;
            }
        	
            if(cepr01020201Form.getNoRow() == null){
            	cepr01020201Form.setNoRow("25");
            	noRow = "25";
            }
            	noRow = cepr01020201Form.getNoRow();
            	
        	
            List<TotalPage> totalPage = selectTotalGroupPage(noRow, group);
            
            // HANDLE PAGE LESS OR PAGE OVER
            if(Integer.parseInt(noPage) < 1){
            	noPage = "1";
            }
            if(Integer.parseInt(noPage) > Integer.parseInt(totalPage.get(0).getTotalPage())){
            	noPage = totalPage.get(0).getTotalPage();
            }
            
        	List<ViewListGroup> allList = selectGroupList(group, noPage, noRow, cepr01020201Form.getSortRow());
        	
        	int currPage = Integer.parseInt(noPage);
        	cepr01020201Form.setFirstPage(1 + "");
        	cepr01020201Form.setLastPage(totalPage.get(0).getTotalPage());
        	cepr01020201Form.setNextPage((currPage + 1) + "");
        	cepr01020201Form.setPreviousPage((currPage - 1) + "");
        	
        	result.put("allList", allList);
        	result.put("currPage", noPage);
            
            
        
        //AKHIR HANDLE BAGIAN SEARCH DI PAGE Cepr01020201JSP
        
//------------------------------------------------------------------------------------------------        
        
    	return result;
    }
    
    public Map<String, Object> selectAddGroupName(Object command){
    	
    	Cepr01020201Form cepr01020201Form = ( ( Cepr01020000HoldingForm ) command ).getCepr01020201Form();
    	
    	String groupName = cepr01020201Form.getAddGroup();
    	
    	Map<String, Object> result = new HashMap<String, Object>();
    	if(groupName.equals("")){
    		result.put("keterangan", "Nama Group harus diisi");
    	}else if(!groupName.equals("")){
    		List<ViewListGroup> allList = eproposalManager.selectAllGroup(null);
    	
    		int MaxList;
    		MaxList = allList.size();
    		String maxValue = "";
        
    		// HANDLE UNTUK DATA MASIH 0
    		if (MaxList == 0){
    			maxValue = "1";
    		}else{
    			logger.info(Integer.parseInt(allList.get(MaxList-1).getGroupId()) +1);
    			maxValue = (Integer.parseInt(allList.get(MaxList-1).getGroupId()) +1) + "";
    		}
    	
    		Map<String, Object> params = new HashMap<String, Object>();
    		params.put("GROUP_ID", maxValue);
    		params.put("GROUP_NAME", groupName);
    		
    		if((eproposalManager.selectAllGroup(params)).size() == 0){
    			eproposalManager.insertNewGroup(params);
        		result.put("keterangan", "Add Nama Group sukses");
    		}else if((eproposalManager.selectAllGroup(params)).size() != 0){
        		result.put("keterangan", "Nama Group sudah ada");
    		}
    		
    	}
    	
    	return result;
    }
    
    public Map<String, Object> selectDeleteGroupName( List selectedGroupList){
    	
    	List allGroupList = new ArrayList();
    	
    	Map<String, Object> result = new HashMap<String, Object>();
    	Map<String, Object> params = new HashMap<String, Object>();
    	List<UserGroupId> allList = eproposalManager.selectAllUserGroup();
    	
    	for(int i = 0 ; i < allList.size() ; i++){
    		allGroupList.add(allList.get(i).getGroupId());
    	}
    	
    	int node = 0;
    	if(selectedGroupList.size() != 0){
    		for(int i = 0 ; i < selectedGroupList.size() ; i++){
    			if(!allGroupList.contains(selectedGroupList.get(i))){
    				node = node + 1;
					params.put("group_id", selectedGroupList.get(i));
					eproposalManager.deleteSelectedGroup(params);
					eproposalManager.deleteSelectedGroupDetail(params);
					// delete sukses
    			}
    		}
    	}
    		
    	if(node == selectedGroupList.size()){
    		result.put("keterangan", "delete sukses");
    	}else if(node < selectedGroupList.size() && node > 0){
    		result.put("keterangan", "sebagian ("+ node +") delete gagal. ada beberapa group yang sedang digunakan");
    	}else if(node == 0){
    		result.put("keterangan", "delete gagal. group sedang digunakan");
    	}
    		
    	
    	return result;
    }
}
