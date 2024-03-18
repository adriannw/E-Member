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
import id.co.sinarmaslife.eproposal.web.Cepr01030501Form;
import id.co.sinarmaslife.eproposal.common.lookup.Cepr00000000ComboLookupBusiness;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01020000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01020301Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030501Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030501Form;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
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

public class Cepr01020301Business
{
    protected final Log logger = LogFactory.getLog( getClass() );

    protected EproposalManager eproposalManager;
    protected Cepr00000000EditorUtil editorUtil;

    public Cepr01020301Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil )
    {
        this.eproposalManager = eproposalManager;
        this.editorUtil = editorUtil;
    }

    public void resetForm( Cepr01020301Form cepr01020301Form )
    {
        //cepr01030501Form.setProposalDate( cepr00000000GeneralBusiness.selectNowDate() );
//        cepr01040103Form.setAssurancePlanList( cepr00000000ComboLookupBusiness.selectLstBusinessOptionVOList( true, null ) );
    }
    
    public List<OptionVO> selectAllGroupList(){
    	List<OptionVO> result = eproposalManager.selectAllGroupList();
    	OptionVO optionVO = new OptionVO( "", "" );
        result.add( 0, optionVO );
    	return result;
    }
    
    public List<ViewListAgent> selectAllAgentList( String msagId, String account, String mclFirst, String lcaNama, String lcaId, String lwkNama, String lsrgNama, String group, String acPass, String noPage, String noRow, String sortRow )
    {
    	logger.info( "*-*-*-* Cepr01020301Business.selectAllAgentList" );
    	List<ViewListAgent> result = new ArrayList<ViewListAgent>();
    	//String ljpId = cepr01040103Form.getLjpId;
    	//Integer lstbId = 1;
    	
    	if(msagId.equals("")){msagId = null;}
    	if(account.equals("")){account = null;}
    	if(mclFirst.equals("")){mclFirst = null;}
    	if(lcaNama.equals("")){lcaNama = null;}
    	if(lcaId.equals("")){lcaId = null;}
    	if(lwkNama.equals("")){lwkNama = null;}
    	if(lsrgNama.equals("")){lsrgNama = null;}
    	if(group.equals("")){group = null;}
    	if(acPass.equals("")){acPass = null;}
    	
    	if("sortedByAccount".equals(sortRow)){
    		sortRow = "ID";
    	}else if("sortedByMsag".equals(sortRow)){
    		sortRow = "MSAG_ID";
    	}else if("sortedByAgent".equals(sortRow)){
    		sortRow = "MCL_FIRST";
    	}else if("sortedByDivision".equals(sortRow)){
    		sortRow = "LCA_ID";
    	}else if("sortedByCabang".equals(sortRow)){
    		sortRow = "LCA_NAMA";
    	}else if("sortedByWakil".equals(sortRow)){
    		sortRow = "LWK_NAMA";
    	}else if("sortedByRegion".equals(sortRow)){
    		sortRow = "LSRG_NAMA";
    	}else if("sortedByGroup".equals(sortRow)){
    		sortRow = "GROUP_NAME";
    	}else if("sortedByTgl".equals(sortRow)){
    		sortRow = "mspe_date_birth";
    	}
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put( "MSAG_ID", msagId );
    	params.put( "account", account );
    	params.put( "GROUP_NAME", group );
    	params.put( "MCL_FIRST", mclFirst );
    	params.put( "LCA_NAMA", lcaNama );
    	if("42".equals(lcaId) ){
    		params.put( "LCA_ID_WORKSITE", "x" );
    		params.put( "LCA_ID_REGIONAL", null );
    		params.put( "LCA_ID_GENERAL", null );
		}else if("xx".equals(lcaId)){
			params.put( "LCA_ID_WORKSITE", null );
    		params.put( "LCA_ID_REGIONAL", "x" );
    		params.put( "LCA_ID_GENERAL", null );
		}else{
			params.put( "LCA_ID_WORKSITE", null );
    		params.put( "LCA_ID_REGIONAL", null );
    		params.put( "LCA_ID_GENERAL", lcaId );
		}
    	params.put( "LWK_NAMA", lwkNama );
    	params.put( "LSRG_NAMA", lsrgNama );
    	if("1".equals(acPass)){
    		params.put( "acPassNotNull", "x" );
    		params.put( "acPassNull", null );
    	}else if("2".equals(acPass)){
    		params.put( "acPassNotNull", null );
    		params.put( "acPassNull", "x" );
    	}else if(acPass == null){
    		params.put( "acPassNotNull", null );
    		params.put( "acPassNull", null );
    	}
    	params.put( "noPage", Integer.parseInt(noPage) );
    	params.put( "noRow", Integer.parseInt(noRow) );
    	params.put( "sortRow", sortRow );
    	//todo
    	
    	result = eproposalManager.selectAllAgentList( params );
    	logger.info( "params = " + params );
    	logger.info( "result = " + result );
    	
    	for(int i = 0 ; i < result.size() ; i++){
    		if(result.get(i).getIdAccount() != null){
    		if(result.get(i).getIdAccount().toString().contains("@")){
    			result.get(i).setIdAccount(result.get(i).getIdAccount().toString().substring(0, result.get(i).getIdAccount().toString().indexOf("@")));
    		}
    		}
    		if("08".equals(result.get(i).getLcaId().toString()) || "42".equals(result.get(i).getLcaId().toString()) ){
    			result.get(i).setLcaId("WORKSITE ");
    		}else if("37".equals(result.get(i).getLcaId().toString())){
    			result.get(i).setLcaId("AGENCY  ");
    		}else if("46".equals(result.get(i).getLcaId().toString())){
    			result.get(i).setLcaId("HYBRID / ARTHAMAS");
    		}else if("09".equals(result.get(i).getLcaId().toString())){
    			result.get(i).setLcaId("BANCASSURANCE ");
    		}else{
    			result.get(i).setLcaId("REGIONAL  ");
    		}
    	}
    	
    	
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
    
    public List<TotalPage> selectTotalAgentPage( String msagId, String account, String mclFirst, String lcaNama, String lcaId,  String lwkNama, String lsrgNama ,String noRow, String group, String acPass)
    {
    	logger.info( "*-*-*-* Cepr01030501Business.selectTotalProviderPage" );
    	List<TotalPage> result = new ArrayList<TotalPage>();
    	
    	if(msagId.equals("")){msagId = null;}
    	if(account.equals("")){account = null;}
    	if(mclFirst.equals("")){mclFirst = null;}
    	if(lcaNama.equals("")){lcaNama = null;}
    	if(lcaId.equals("")){lcaId = null;}
    	if(lwkNama.equals("")){lwkNama = null;}
    	if(lsrgNama.equals("")){lsrgNama = null;}
    	if(group.equals("")){group = null;}
    	if(acPass.equals("")){acPass = null;}
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put( "MSAG_ID", msagId );
    	params.put( "account", account );
    	params.put( "MCL_FIRST", mclFirst );
    	params.put( "LCA_NAMA", lcaNama );
    	if("42".equals(lcaId) ){
    		params.put( "LCA_ID_WORKSITE", "x" );
    		params.put( "LCA_ID_REGIONAL", null );
    		params.put( "LCA_ID_GENERAL", null );
		}else if("xx".equals(lcaId)){
			params.put( "LCA_ID_WORKSITE", null );
    		params.put( "LCA_ID_REGIONAL", "x" );
    		params.put( "LCA_ID_GENERAL", null );
		}else{
			params.put( "LCA_ID_WORKSITE", null );
    		params.put( "LCA_ID_REGIONAL", null );
    		params.put( "LCA_ID_GENERAL", lcaId );
		}
    	params.put( "LWK_NAMA", lwkNama );
    	params.put( "LSRG_NAMA", lsrgNama );
    	params.put( "GROUP_NAME", group );
    	if("1".equals(acPass)){
    		params.put( "acPassNotNull", "x" );
    		params.put( "acPassNull", null );
    	}else if("2".equals(acPass)){
    		params.put( "acPassNotNull", null );
    		params.put( "acPassNull", "x" );
    	}else if(acPass == null){
    		params.put( "acPassNotNull", null );
    		params.put( "acPassNull", null );
    	}
    	params.put( "noRow", Integer.parseInt(noRow) );

    	result = eproposalManager.selectTotalAgentPage( params );
    	
    	return result;
    }
    
    
    public Map<String, Object> selectAgentList(Object command, String cPage, String sortRow, List groupList){
    	
    	Cepr01020301Form cepr01020301Form = ( ( Cepr01020000HoldingForm ) command ).getCepr01020301Form();
    	
    	Map<String, Object> result = new HashMap<String, Object>();
        
        //Map<String, Object> map = new HashMap<String, Object>();
        
        Cepr01020000HoldingForm cepr01020000HoldingForm = (Cepr01020000HoldingForm) command;
        
//-COMBOBOX-----------------------------------------------------------------------------------------------
        
        // LOGIKA UNTUK AMBIL ISI rsNama, HANDLE rsNama=NULL
        if( cepr01020301Form.getMsagId() == null ){ cepr01020301Form.setMsagId(""); }
        if( cepr01020301Form.getIdAccount() == null ){ cepr01020301Form.setIdAccount(""); }
        if( cepr01020301Form.getMclFirst() == null ){ cepr01020301Form.setMclFirst(""); }
        if( cepr01020301Form.getLcaNama() == null ){ cepr01020301Form.setLcaNama(""); }
        if( cepr01020301Form.getLcaId() == null ){ cepr01020301Form.setLcaId(""); }
        if( cepr01020301Form.getLwkNama() == null ){ cepr01020301Form.setLwkNama(""); }
        if( cepr01020301Form.getLsrgNama() == null ){ cepr01020301Form.setLsrgNama(""); }
        if( cepr01020301Form.getGroupName() == null ){ cepr01020301Form.setGroupName(""); }
        if( cepr01020301Form.getAcPass() == null ){ cepr01020301Form.setAcPass(""); }
        String msagIdInput = cepr01020301Form.getMsagId();
        String account = cepr01020301Form.getIdAccount();
        String mclFirst = cepr01020301Form.getMclFirst();
        String lcaNama = cepr01020301Form.getLcaNama();
        String lcaId = cepr01020301Form.getLcaId();
        String lwkNama = cepr01020301Form.getLwkNama();
        String lsrgNama = cepr01020301Form.getLsrgNama();
        String group = cepr01020301Form.getGroupName();
        String acPass = cepr01020301Form.getAcPass();
        
        
 
//-COMBOBOX-Records/Page------------------------------------------------------------------------------------
        
        // UNTUK COMBOBOX ISI Records/Page
        List<OptionVO> listRecords = new ArrayList();
        listRecords.add(new OptionVO( "10" , "10" ));
        listRecords.add(new OptionVO( "25" , "25" ));
        listRecords.add(new OptionVO( "50" , "50" ));
        listRecords.add(new OptionVO( "100" , "100" ));
        listRecords.add(new OptionVO( "250" , "250" ));
        listRecords.add(new OptionVO( "500" , "500" ));
        cepr01020301Form.setListRecords(listRecords);
                
//-COMBOBOX-Division------------------------------------------------------------------------------------
        
        // UNTUK COMBOBOX ISI Division
        List<OptionVO> allDivision = new ArrayList();
        allDivision.add(new OptionVO("",""));
        allDivision.add(new OptionVO("37","AGENCY"));
        allDivision.add(new OptionVO("42","WORKSITE"));
        allDivision.add(new OptionVO("46","HYBRID / ARTHAMAS"));
        allDivision.add(new OptionVO("09","BANCASSURANCE"));
        allDivision.add(new OptionVO("xx","REGIONAL"));
        cepr01020301Form.setAllDivision(allDivision);
//-COMBOBOX-Acc-Pass-----------------------------------------------------------------------------------
        
        // UNTUK COMBOBOX ISI Account Password
        List<OptionVO> accPass = new ArrayList();
        accPass.add(new OptionVO("",""));
        accPass.add(new OptionVO("1","ADA"));
        accPass.add(new OptionVO("2","TIDAK"));
        cepr01020301Form.setAcPassList(accPass);
       
//-SEARCH-----------------------------------------------------------------------------------------------        
        
        String noPage = "";
        String noRow = "0";
        
        // MULAI HANDLE BAGIAN SEARCH DI PAGE Cepr01020301JSP
        //if( page == 0 || "onPressButtonSearch".equals( theEvent ) )
        //{	
        	
            if(cPage == null || cPage == "" ){
            	noPage = "1";
            }else{
            	noPage = cPage;
            }
        	
            if(cepr01020301Form.getNoRow() == null){
            	cepr01020301Form.setNoRow("25");
            	noRow = "25";
            }
            	noRow = cepr01020301Form.getNoRow();
            	
            if(cepr01020301Form.getSortRow() == null ){
            	cepr01020301Form.setSortRow("sortedByAccount");
            }else if(sortRow != ""){
            	cepr01020301Form.setSortRow(sortRow);
            }
            
        	
            List<TotalPage> totalPage = selectTotalAgentPage(msagIdInput, account, mclFirst ,lcaNama, lcaId, lwkNama ,lsrgNama ,noRow, group, acPass);
            
            // HANDLE PAGE LESS OR PAGE OVER
            if(Integer.parseInt(noPage) < 1){
            	noPage = "1";
            }
            if(Integer.parseInt(noPage) > Integer.parseInt(totalPage.get(0).getTotalPage())){
            	noPage = totalPage.get(0).getTotalPage();
            }
            
        	List<ViewListAgent> allList = selectAllAgentList(msagIdInput, account, mclFirst ,lcaNama , lcaId, lwkNama ,lsrgNama , group, acPass, noPage, noRow, cepr01020301Form.getSortRow());
        	
        	for(int i = 0 ; i < allList.size() ; i++){
        		for(int j = 0 ; j < groupList.size() ; j++){
        			String tempString = groupList.get(j).toString();
        			String msagId = tempString.substring(0, (tempString.indexOf(',')) );
        			if( allList.get(i).getMsagId().equals(msagId) ){
        				allList.get(i).setFlag("checked");
        			}
        		}
        	}
        	
        	int currPage = Integer.parseInt(noPage);
        	cepr01020301Form.setFirstPage(1 + "");
        	cepr01020301Form.setLastPage(totalPage.get(0).getTotalPage());
        	cepr01020301Form.setNextPage((currPage + 1) + "");
        	cepr01020301Form.setPreviousPage((currPage - 1) + "");
        	
        	result.put("allList", allList);
        	result.put("currPage", noPage);
            
            
        
        //AKHIR HANDLE BAGIAN SEARCH DI PAGE Cepr01020301JSP
        
//------------------------------------------------------------------------------------------------        
        
    	return result;
    }
    
    public void changeAgentList( Object command, List group ){
    	Cepr01020301Form cepr01020301Form = ( ( Cepr01020000HoldingForm ) command ).getCepr01020301Form();
    	
    	List groupList = new ArrayList();
    	String msagId;
    	String groupId;
    	String tempString;
    	String groupIdTemp;
    	String accountIdTemp;
    	String newGroup = cepr01020301Form.getAddGroup();
    	if(newGroup.equals("")){
    		
    		for(int i = 0 ; i < group.size() ; i++){
    			tempString = group.get(i).toString();
    			msagId = tempString.substring(0, (tempString.indexOf(',')) );
    			groupIdTemp = tempString.substring(tempString.indexOf(',')+1);
    			groupId = groupIdTemp.substring(0, groupIdTemp.indexOf(','));
    			accountIdTemp = tempString.substring(tempString.length()-1);
    			if(",".equals(accountIdTemp)){
    				// tidak dioperasikan
    			}else if(!",".equals(accountIdTemp)){
    				if(!groupId.equals("")){
        				groupList.add(msagId);
        			}
    			}
    		}
    		if(groupList.size() != 0){
    			Map<String, Object> groupMap = new HashMap<String, Object>();
    			groupMap.put("msagIdList", groupList);
    			eproposalManager.selectDeleteAgentGroup( groupMap );
    		}
    		
    	}else if(!newGroup.equals("")){
    		
    		for(int i = 0 ; i < group.size() ; i++){
    			Map<String, Object> groupMap = new HashMap<String, Object>();
    			tempString = group.get(i).toString();
    			msagId = tempString.substring(0, (tempString.indexOf(',')) );
    			groupIdTemp = tempString.substring(tempString.indexOf(',')+1);
    			groupId = groupIdTemp.substring(0, groupIdTemp.indexOf(','));
    			accountIdTemp = tempString.substring(tempString.length()-1);
    			if(",".equals(accountIdTemp)){
    				// tidak dioperasikan
    			}else if(!",".equals(accountIdTemp)){
    				groupMap.put("msagId", msagId);
    				groupMap.put("groupId", newGroup);
        			if(groupId.equals("")){
        				eproposalManager.selectInsertAgentGroup( groupMap );
        			}else if(!groupId.equals("")){
        				eproposalManager.selectUpdateAgentGroup( groupMap );
        			}
    			}
    		}
    		
    	}  	
    }
 
    
}
