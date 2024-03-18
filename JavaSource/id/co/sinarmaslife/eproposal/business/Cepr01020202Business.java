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
import id.co.sinarmaslife.eproposal.common.data.HardcodedProducts;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01020201Form;
import id.co.sinarmaslife.eproposal.common.lookup.Cepr00000000ComboLookupBusiness;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.vo.HardcodedProductVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01020000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01020201Form;
import id.co.sinarmaslife.eproposal.web.Cepr01020202Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01020201Form;
import id.co.sinarmaslife.eproposal.web.Cepr01020201Form;
import id.co.sinarmaslife.standard.model.vo.AssurancePlanList;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.model.vo.ViewListBusiness;
import id.co.sinarmaslife.standard.model.vo.ViewListGroup;
import id.co.sinarmaslife.standard.model.vo.TotalPage;
import id.co.sinarmaslife.standard.model.vo.UserGroup;
import id.co.sinarmaslife.standard.model.vo.ViewListAgent;
import id.co.sinarmaslife.standard.model.vo.ViewListHardcodedBusiness;
import id.co.sinarmaslife.standard.model.vo.ViewListIdBusiness;
import id.co.sinarmaslife.standard.model.vo.ViewListProvider;
import id.co.sinarmaslife.standard.model.vo.ViewSelectedListBusiness;
//import id.co.sinarmaslife.standard.model.vo.ViewListProviderDetail;

import id.co.sinarmaslife.standard.util.HtmlUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cepr01020202Business
{
    protected final Log logger = LogFactory.getLog( getClass() );

    protected EproposalManager eproposalManager;
    protected Cepr00000000EditorUtil editorUtil;

    public Cepr01020202Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil )
    {
        this.eproposalManager = eproposalManager;
        this.editorUtil = editorUtil;
    }

    public void resetForm( Cepr01020202Form cepr01020202Form )
    {
        //cepr01020201Form.setProposalDate( cepr00000000GeneralBusiness.selectNowDate() );
//        cepr01040103Form.setAssurancePlanList( cepr00000000ComboLookupBusiness.selectLstBusinessViewListGroupList( true, null ) );
    }
    
    public List<ViewListBusiness> selectAllBusinessList( String groupId )
    {
    	logger.info( "*-*-*-* Cepr01020202Business.selectAllBusinessList" );
    	List<ViewListBusiness> result = new ArrayList<ViewListBusiness>();
    	List HardCodedProList = new ArrayList();
    	
    	List HardCodedLsbsId = new ArrayList();
    	List HardCodedLsdbsId = new ArrayList();
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put( "GROUP_ID", groupId );
    	
    	result = eproposalManager.selectBusinessList( params );
    	logger.info( "params = " + params );
    	logger.info( "result = " + result );
    	
    	List<HardcodedProductVO> hardcodedProductVOList = HardcodedProducts.getHardcodedProductVOList();
    	for( HardcodedProductVO prodVO : hardcodedProductVOList )
        {
            HardCodedProList.add( prodVO.getAssurancePlanCd().substring(0, prodVO.getAssurancePlanCd().indexOf("=")) );
            String c = prodVO.getAssurancePlanCd();
        }
    	//int i = 0;
    	for(ViewListBusiness viewListBusiness : result){
        	String lsbsName = viewListBusiness.getLsbsName();
        	String lsbsId = viewListBusiness.getLsbsId();
        	String lsdbsName = viewListBusiness.getLsdbsName();
        	String lsdbsNumber = viewListBusiness.getLsdbsNumber();
        	
        	for(int i=0;i<hardcodedProductVOList.size();i++){
        		AssurancePlanList[] optionVOarr = hardcodedProductVOList.get(i).getBusinessOptionVOArr();
        		String contentValue;
        		String contentLabel;
        		for( AssurancePlanList optionVO : optionVOarr )
        		{
        			String id1 = hardcodedProductVOList.get(i).getAssurancePlanCd().substring(0,hardcodedProductVOList.get(i).getAssurancePlanCd().indexOf("="));
        			if(lsbsId.equals(id1) && lsdbsNumber.equals(optionVO.getValue().substring(optionVO.getValue().indexOf("~X")+2)) ){
        				viewListBusiness.setLsbsName(hardcodedProductVOList.get(i).getProductDescr()+"@"+"HARDCODED");
        				viewListBusiness.setLsdbsName(optionVO.getLabel());
        			}
        		}
        	}
			
        }
    	//xxxxxxxxxxxxxxx
    	return result;
    }
    
    public List<ViewSelectedListBusiness> selectedBusinessList( String groupId )
    {
    	logger.info( "*-*-*-* Cepr01020202Business.selectAllBusinessList" );
    	List<ViewSelectedListBusiness> result = new ArrayList<ViewSelectedListBusiness>();
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put( "GROUP_ID", groupId );
    	
    	result = eproposalManager.selectedBusinessList( params );
    	logger.info( "params = " + params );
    	logger.info( "result = " + result );
    	
    	return result;
    }
    
    public List<ViewSelectedListBusiness> selectedHardcodedBusinessList( String groupId )
    {
    	logger.info( "*-*-*-* Cepr01020202Business.selectAllBusinessList" );
    	List<ViewSelectedListBusiness> result = new ArrayList<ViewSelectedListBusiness>();
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put( "GROUP_ID", groupId );
    	
    	result = eproposalManager.selectedHardcodedBusinessList( params );
    	logger.info( "params = " + params );
    	logger.info( "result = " + result );
    	
    	return result;
    }
    
    public Map<String, Object> selectBusinessList(String groupId){
    	
    	Map<String, Object> result = new HashMap<String, Object>();
        String node = "";
        String nodeEx = "";
        List<ViewListBusiness> allList = selectAllBusinessList(groupId);
        List<ViewSelectedListBusiness> selectedList = selectedBusinessList(groupId);
        List<ViewSelectedListBusiness> selectedHardcodedList = selectedHardcodedBusinessList(groupId);
        List<Integer> selectedIndexHardcoded = new ArrayList<Integer>();
        List<String> specialCaseLsbsId = new ArrayList<String>();
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("group_id", groupId);
        String groupName = eproposalManager.selectGroupNameLabel(param);
        
        List<HardcodedProductVO> hardcodedProductVOList = HardcodedProducts.getHardcodedProductVOList();
        int allListLength = allList.size();
        String lsbsTemp = "";
        
        if(hardcodedProductVOList.size() > 1){
        	for(int i = 0 ; i < hardcodedProductVOList.size() ; i++){
        		if(i==0){
        			lsbsTemp = hardcodedProductVOList.get(0).getLeftPartOfBusinessCd() + "";
        		}
        		if(i>0){
        			if( (i+1) != hardcodedProductVOList.size() ){
        				String hdLsbsId = hardcodedProductVOList.get(i+1).getLeftPartOfBusinessCd() + "";
        				if(hdLsbsId.equals(lsbsTemp)){
        					specialCaseLsbsId.add(lsbsTemp);
        				}else if( !hdLsbsId.equals(lsbsTemp) ){
        					lsbsTemp = hardcodedProductVOList.get(i+1).getLeftPartOfBusinessCd() + "";
        				}
        			}
        		}
        	}
        }
        
        // remove child dari list apabila parent termasuk hardcoded
        // add hardcode yg sesuai dengan child ke selectedIndexHardcoded
        for(int i = 0 ; i < hardcodedProductVOList.size() ; i++){
        	for(int j = 0; j < allList.size() ; j++){
        		String allListtemp = allList.get(j).getLsbsId();
        		String lsdbsNumberTemp = allList.get(j).getLsdbsNumber();
        		// cek isi list database dengan isi list hardcoded --> lsbsId -> cocok
        		if(allListtemp.equals(hardcodedProductVOList.get(i).getAssurancePlanCd().substring(0, hardcodedProductVOList.get(i).getAssurancePlanCd().indexOf("=") ) ) ){
        			allList.remove(j);
        			if(!selectedIndexHardcoded.contains(i)){
        				if(specialCaseLsbsId.size() != 0){
        					for(int k = 0 ; k < specialCaseLsbsId.size() ; k++){
                				if(!allListtemp.equals(specialCaseLsbsId.get(k))){
                					selectedIndexHardcoded.add(i);
                				}
                			}
        				}else{
        					AssurancePlanList[] optionVOArr = hardcodedProductVOList.get(i).getBusinessOptionVOArr();
        					for(AssurancePlanList optionVO : optionVOArr){
        						if(lsdbsNumberTemp.equals(optionVO.getValue().substring(optionVO.getValue().indexOf("~X")+2)) ){
        							selectedIndexHardcoded.add(i);
        						}
        					}
        					//selectedIndexHardcoded.add(i);
        				}
        			}
        			j=j-1;
        		}
        	}
        }
        
        int d;
        // hapus duplikat selectedIndexHardcoded
        for(int i = 0 ; i < selectedIndexHardcoded.size() ; i++){
        	d = 0;
        	for(int j = 0 ; j < selectedIndexHardcoded.size() ; j++){
        		if(selectedIndexHardcoded.get(i) == selectedIndexHardcoded.get(j)){
        			d=d+1;
        		}
        		if(d>1){
        			selectedIndexHardcoded.remove(j);
        			d=1;
        			j=j-1;
        		}
        	}
        }
        
        // maxi deposit tidak ada centang
        // add list dengan all hardcoded yg berhubungan dengan selected hardcoded
        for(int i=0 ; i<selectedIndexHardcoded.size() ; i++){
        	for(int j = 0 ; j < hardcodedProductVOList.size() ; j++){
        		if(j == selectedIndexHardcoded.get(i)){
        			String lsbsId = hardcodedProductVOList.get(j).getAssurancePlanCd().substring(0, hardcodedProductVOList.get(i).getAssurancePlanCd().indexOf("="));
        			String lsbsName = hardcodedProductVOList.get(j).getProductDescr();
        			AssurancePlanList[] optionVOarr = hardcodedProductVOList.get(j).getBusinessOptionVOArr();
        			String contentValue;
        			String contentLabel;
        			for( AssurancePlanList optionVO : optionVOarr )
        			{
        				contentValue = optionVO.getValue().substring(optionVO.getValue().indexOf("~X")+2);
        				contentLabel = optionVO.getLabel();
        				allList.add(new ViewListBusiness(lsbsId, lsbsName, contentValue, contentLabel, "", lsbsName));
        				//allList.add(new ViewListBusiness(lsbsId, HardCodedNameProList.get(i).toString(), contentValue, contentLabel, "checked", HardCodedNameProList.get(i).toString()));
        			}
        			
        		}
        	}
        }
        
        // masalah
        for(int i = 0 ; i < selectedList.size() ; i++){
        	if(specialCaseLsbsId.contains(selectedList.get(i).getLsbsId())){
        		for(int j = 0 ; j < hardcodedProductVOList.size() ; j++){
        			if(selectedList.get(i).getLsbsId().equals(hardcodedProductVOList.get(j).getLeftPartOfBusinessCd() + "")){
        				String lsbsId = hardcodedProductVOList.get(j).getAssurancePlanCd().substring(0, hardcodedProductVOList.get(j).getAssurancePlanCd().indexOf("="));
            			String lsbsName = hardcodedProductVOList.get(j).getProductDescr();
            			AssurancePlanList[] optionVOarr = hardcodedProductVOList.get(j).getBusinessOptionVOArr();
            			String contentValue;
            			String contentLabel;
            			List<String> optionVOList = new ArrayList<String>();
            			for( AssurancePlanList optionVO : optionVOarr )
            			{
            				optionVOList.add(optionVO.getValue().substring(optionVO.getValue().indexOf("~X")+2));
            			}
            			if(optionVOList.contains(selectedList.get(i).getLsdbsNumber())){
            				for( AssurancePlanList optionVO : optionVOarr )
            				{
            					contentValue = optionVO.getValue().substring(optionVO.getValue().indexOf("~X")+2);
            					contentLabel = optionVO.getLabel();
            					//cek double -> gagal
            					//ViewListBusiness temp = new ViewListBusiness(lsbsId, lsbsName, contentValue, contentLabel, "", lsbsName);
            					String nodeAdd = "yes";
            					for(int m = 0 ; m < allList.size() ; m++){
            						if(!allList.get(m).getLsbsId().equals(lsbsId) && !allList.get(m).getLsdbsNumber().equals(contentValue)){
            							nodeAdd = "yes";
            						}else if(allList.get(m).getLsbsId().equals(lsbsId) && allList.get(m).getLsdbsNumber().equals(contentValue)){
            							nodeAdd = "no";
            							m = allList.size();
            						}
            					}
            					if(nodeAdd.equals("yes")){
            						allList.add(new ViewListBusiness(lsbsId, lsbsName, contentValue, contentLabel, "", lsbsName));
            					}
            					//allList.add(new ViewListBusiness(lsbsId, HardCodedNameProList.get(i).toString(), contentValue, contentLabel, "checked", HardCodedNameProList.get(i).toString()));
            				}
            			}
            			
        			}
        		}
        	}
        }
        
        /*
        //remove child dari list apabila parent termasuk hardcoded dan child tidak ada dalam hardcoded 
        for(int i = 0 ; i < hardcodedProductVOList.size() ; i++){
        	for(int j = 0; j < allList.size() ; j++){
        		String allListtemp = allList.get(j).getLsbsId();
        		if(allListtemp.equals(hardcodedProductVOList.get(i).getAssurancePlanCd().substring(0, hardcodedProductVOList.get(i).getAssurancePlanCd().indexOf("=")))){
        			for(int k = 0 ; k < selectedList.size() ; k++){
        				if(selectedList.get(k).getLsbsId().equals(allListtemp) && selectedList.get(k).getLsdbsNumber().equals(allList.get(j).getLsdbsNumber())){
        					node = "ada";
        					k = selectedList.size();
        				}else{
        					if( selectedList.get(k).getHardcodedFlag() == null ){
        					
        					}else{
        					if( selectedList.get(k).getHardcodedFlag().equals(allList.get(j).getLsbsName()) ){
        						if(allList.get(j).getLsbsName().substring(allList.get(j).getLsbsName().indexOf("@") +1).equals("HARDCODED")){
        							node = "ada";
            						k = selectedList.size();
        						}else{
        							node = "tidak";
        						}
        					}else{
        						node = "tidak";
        					}}
        				}
        			}
        			if(!node.equals("ada")){
        				allList.remove(j);
        				j=j-1;
    				}
        		}
        	}
        }
        */
        
        //remove parent&child(database) dari list apabila sudah ada hardcoded-nya
        /*for(int i = 0 ; i < allList.size() ; i++){
        	for(int j = 0 ; j < allList.size() ; j++){
            	if(allList.get(i).getLsbsId().equals(allList.get(j).getLsbsId())){
            		if(!allList.get(i).getLsbsName().contains("@HARDCODED")){
            			allList.remove(i);
            			i = i-1;
            		}
            	}
            }
        }*/
        
        //hapus @HARDCODED
        for(int i = 0 ; i < allList.size() ; i++){
        	if(allList.get(i).getLsbsName().contains("@HARDCODE") ){
        		allList.get(i).setLsbsName(allList.get(i).getLsbsName().substring(0, allList.get(i).getLsbsName().indexOf("@")));
        	}
        }
        /*
        int j = 0;
        for(int i = 0 ; i < allList.size() ; i++){
        	if(selectedList.get(j).getLsbsId().equals( allList.get(i).getLsbsId() )
        			&& selectedList.get(j).getLsdbsNumber().equals( allList.get(i).getLsdbsNumber() ))
        	{
        		allList.get(i).setFlag("checked");
        		j=j+1;
        		if(j == selectedList.size()){
        			i = allList.size();
        		}
        	}
        }*/
        
        //maxi deposit tidak ada centang
        for(int i = 0 ; i < allList.size() ; i++){
        	// case not hardcoded
        	String nodeExist = "no";
        	for(int j = 0; j < selectedList.size(); j++){
        		if(selectedList.get(j).getLsbsId().equals( allList.get(i).getLsbsId() )
        			&& selectedList.get(j).getLsdbsNumber().equals( allList.get(i).getLsdbsNumber() ))
        		{
        			nodeExist = "yes";
        			j=selectedList.size();
        		}
        	}
        	if(nodeExist.equals("yes")){
        		allList.get(i).setFlag("checked");
        	}
        	
        	// case hardcoded
        	nodeExist = "no";
        	String lsdbsAktif = "0";
        	for(int j = 0; j < selectedHardcodedList.size(); j++){
        		if(selectedHardcodedList.get(j).getLsbsId().equals( allList.get(i).getLsbsId() )
        			&& selectedHardcodedList.get(j).getLsdbsNumber().equals( allList.get(i).getLsdbsNumber() ))
        		{
        			nodeExist = "yes";
        			lsdbsAktif = selectedHardcodedList.get(j).getLsdbsAktif();
        			j=selectedHardcodedList.size();
        		}
        	}
        	if(nodeExist.equals("yes")){
        		allList.get(i).setFlag("checked");
        		allList.get(i).setLsdbsAktif(lsdbsAktif);
        	}
        }
        
        
        
        result.put("groupName", groupName);
        result.put("allList", allList);
        
    	return result;
    }
    
    public List<OptionVO> selectAllBusinessList(){
    	List<OptionVO> result = new ArrayList<OptionVO>();
    	
    	result = eproposalManager.selectLstBusinessOptionVOList(null);
    	for(int i = 0 ; i < result.size() ; i++){
    		result.get(i).setValue(result.get(i).getValue().substring(0, result.get(i).getValue().indexOf("=")));
    	}
    	List<HardcodedProductVO> hardcodedProductVOList = HardcodedProducts.getHardcodedProductVOList();
    	OptionVO optionVO;
    	for( HardcodedProductVO prodVO : hardcodedProductVOList )
        {
    		for(int i=0 ; i < result.size() ; i++){
    			if(result.get(i).getValue().equals(prodVO.getLeftPartOfBusinessCd()+"")){
    				result.remove(i);
    			}
    		}
            optionVO = new OptionVO( prodVO.getAssurancePlanCd(), prodVO.getProductDescr() );
            result.add( optionVO );
        }
    	result = HtmlUtil.addEmptyOption(result);
    	return result;
    }
    
    public Map<String, Object> selectAddBusiness(Object command, String groupId){
    	
    	Cepr01020202Form cepr01020202Form = ( ( Cepr01020000HoldingForm ) command ).getCepr01020202Form();
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put( "GROUP_ID", groupId );
    	List<ViewSelectedListBusiness> allIdcList = eproposalManager.selectedBusinessList( params );
    	List<ViewSelectedListBusiness> allIdhcList = eproposalManager.selectedHardcodedBusinessList( params );
    	List allChildList = new ArrayList();
    	
    	for(int k = 0; k < allIdcList.size() ; k++){
    		allChildList.add(allIdcList.get(k).getLsdbsNumber() + "@" + allIdcList.get(k).getLsbsId());
    	}
    	
    	for(int k = 0; k < allIdhcList.size() ; k++){
    		if(!allChildList.contains(allIdhcList.get(k).getLsdbsNumber() + "@" + allIdhcList.get(k).getLsbsId())){
    			allChildList.add(allIdhcList.get(k).getLsdbsNumber() + "@" + allIdhcList.get(k).getLsbsId());
    		}
    	}
    	
    	Map<String, Object> result = new HashMap<String, Object>();
    		
    	params.clear();
    	params = new HashMap<String, Object>();
    	List HardCodedProList = new ArrayList();
    	List HardCodedNameProList = new ArrayList();
    	List<ViewListBusiness> allList = new ArrayList<ViewListBusiness>();
    	
    	
    	// cek ada hardcoded tidak perlu cek database
    	List<HardcodedProductVO> hardcodedProductVOList = HardcodedProducts.getHardcodedProductVOList();
    	for( HardcodedProductVO prodVO : hardcodedProductVOList )
        {
            HardCodedProList.add( prodVO.getAssurancePlanCd() );
            HardCodedNameProList.add( prodVO.getProductDescr() );
        }
        if(HardCodedProList.contains(cepr01020202Form.getLsbsId())){
        	for(int i = 0; i<HardCodedProList.size();i++){
        		if(HardCodedProList.get(i).equals(cepr01020202Form.getLsbsId())){
        			AssurancePlanList[] optionVOarr = hardcodedProductVOList.get(i).getBusinessOptionVOArr();
        			String contentValue;
        			String contentLabel;
        			String lsbsId;
        			for( AssurancePlanList optionVO : optionVOarr )
        			{
        				contentValue = optionVO.getValue().substring(optionVO.getValue().indexOf("~X")+2);
        				contentLabel = optionVO.getLabel();
        				//bergantung ke cepr01020202Form.getLsbsId()
        				lsbsId = cepr01020202Form.getLsbsId().substring(0, cepr01020202Form.getLsbsId().indexOf("="));
        				//bergantung ke HardCodedProList
        				//lsbsId = optionVO.getValue().substring(0, optionVO.getValue().indexOf("~X"));
        				logger.info("content");
        				allList.add(new ViewListBusiness(lsbsId, HardCodedNameProList.get(i).toString(), contentValue, contentLabel, "checked", HardCodedNameProList.get(i).toString()));
        			}
        			i = HardCodedProList.size();
        			logger.info("true");
        		}
        	}
        	cepr01020202Form.setLsbsId(cepr01020202Form.getLsbsId().toString().substring(0, cepr01020202Form.getLsbsId().toString().indexOf("=")));
        	logger.info("true");
        }else{
        	params.put( "LSBS_ID", cepr01020202Form.getLsbsId() );
        	allList = eproposalManager.selectAddBusiness(params);
        	for(int i = 0;i<allList.size() ;i++){
        		allList.get(i).setHardcodedFlag("");
        	}
        }
        
    	
    	//params.put( "LSBS_ID", cepr01020202Form.getLsbsId() );
    	//List<ViewListBusiness> allList = eproposalManager.selectAddBusiness(params);
    	
    	
    	for(int i = 0 ; i < allList.size() ; i++ ){
    		if(!allChildList.contains(allList.get(i).getLsdbsNumber() + "@" + allList.get(i).getLsbsId())){
    			Map<String, Object> addParams = new HashMap<String, Object>();
    			addParams.put("GROUP_ID", groupId);
    			addParams.put("LSBS_ID", allList.get(i).getLsbsId());
    			addParams.put("LSDBS_NUMBER", allList.get(i).getLsdbsNumber());
    			addParams.put("HARDCODED_FLAG", allList.get(i).getHardcodedFlag());
    			eproposalManager.addBusiness(addParams);
    			logger.info("**********data ditambah");
    		}else{
    			logger.info("**********data sudah ada(aktif)");
    		}
    	}
    	logger.info("***"+allList);
    	
    	if(cepr01020202Form.getLsbsId().equals("")){
    		result.put("keterangan", "nama product harus dipilih");
    	}else if(!cepr01020202Form.getLsbsId().equals("")){
    		result.put("keterangan", "Save sukses");
    	}
    	
    	return result;
    	
    }
    
    public Map<String, Object> selectChangeBusiness(List changeGroup, String groupId){
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	Map<String, Object> result = new HashMap<String, Object>();
    	params.put( "GROUP_ID", groupId );
    	List<ViewListIdBusiness> allIdpList = eproposalManager.selectBusinessParentIdList(params);
    	List<ViewSelectedListBusiness> allIdcList = eproposalManager.selectedBusinessList( params );
    	List<ViewSelectedListBusiness> allIdhcList = eproposalManager.selectedHardcodedBusinessList( params );
    	List<ViewListHardcodedBusiness> allIdhpList = eproposalManager.selectBusinessHardcodedParentIdList( params );
    	//List unselectedParentList = new ArrayList();
    	List unselectedHardcodedParentList = new ArrayList();
    	//List unselectedChildList = new ArrayList();
    	List selectedParentList = new ArrayList();
    	List selectedHardcodedParentList = new ArrayList();
    	List selectedChildList = new ArrayList();
    	List allChildList = new ArrayList();
    	List allParentList = new ArrayList();
    	List allHardcodedParentList = new ArrayList();
    	//List selectedNewChildList = new ArrayList();
    	String LSBS_ID;
    	String LSDBS_NUMBER;
    	
    	// set nilai------------------------
    	int j = 0;
    	//String node = "";
    	for(int i = 0 ; i < changeGroup.size() ; i++ ){
    		if(!changeGroup.get(i).toString().contains("@")){
    			List<HardcodedProductVO> hardcodedProductVOList = HardcodedProducts.getHardcodedProductVOList();
    			String parentId = changeGroup.get(i).toString().substring(0, changeGroup.get(i).toString().indexOf("x"));
    			String childId = changeGroup.get(i).toString().substring(changeGroup.get(i).toString().indexOf("x")+1);
    			for(int m = 0 ; m < hardcodedProductVOList.size() ; m++){
					if(parentId.equals(hardcodedProductVOList.get(m).getLeftPartOfBusinessCd()+"")){
						AssurancePlanList[] optionVOArr = hardcodedProductVOList.get(m).getBusinessOptionVOArr();
    				    for(AssurancePlanList optionVO : optionVOArr){
							if(childId.equals(optionVO.getValue().toString().substring(optionVO.getValue().toString().indexOf("~X")+2))){
    				    		//x
    				    	    //selectedChildList.add(childId + "@" + parentId);
								selectedHardcodedParentList.add(childId + "@" + parentId);
    				    	    //node = "ada";
    				    	    //m = hardcodedProductVOList.size();
    				    	}
    				    }
    				 }
    			}
    			selectedParentList.add(changeGroup.get(i).toString().substring(0, changeGroup.get(i).toString().indexOf("x")));
    		}
    	}
    	
    	for(int i = 0 ; i < changeGroup.size() ; i++ ){
    		if(changeGroup.get(i).toString().contains("@")){
    			selectedChildList.add(changeGroup.get(i).toString());
    		}
    	}
    	
    	for(int k = 0; k < allIdcList.size() ; k++){
    		allChildList.add(allIdcList.get(k).getLsdbsNumber() + "@" + allIdcList.get(k).getLsbsId());
    	}
    	
    	for(int k = 0; k < allIdhcList.size() ; k++){
    		if(!allChildList.contains(allIdhcList.get(k).getLsdbsNumber() + "@" + allIdhcList.get(k).getLsbsId())){
    			allChildList.add(allIdhcList.get(k).getLsdbsNumber() + "@" + allIdhcList.get(k).getLsbsId());
    		}
    	}
    	
    	for(int k = 0; k < allIdpList.size() ; k++){
    		allParentList.add(allIdpList.get(k).getId());
    	}
    	
    	int node = 0;// masalah
    	for(int k = 0; k < allIdhpList.size() ; k++){
    		if( node == 0){
    			allHardcodedParentList.add(allIdhpList.get(k).getLsdbsNumber() + "@" + allIdhpList.get(k).getLsbsId() + "=>" + allIdhpList.get(k).getHardcodedFlag());
    			node =1;
    		}else{
    			if(!allIdhpList.get(k).getHardcodedFlag().equals(allIdhpList.get(k-1).getHardcodedFlag())){
    				allHardcodedParentList.add(allIdhpList.get(k).getLsdbsNumber() + "@" + allIdhpList.get(k).getLsbsId() + "=>" + allIdhpList.get(k).getHardcodedFlag());
    			}
    		}
    	}
    	
    	//-------------------------------------
    	
    	//	cek insert child
    	for(int i = 0; i < selectedChildList.size() ; i++){
    		if( !allChildList.contains(selectedChildList.get(i)) ){
				Map<String, Object> paramsId = new HashMap<String, Object>();
				LSBS_ID = selectedChildList.get(i).toString().substring(selectedChildList.get(i).toString().indexOf("@")+1);
				LSDBS_NUMBER = 	selectedChildList.get(i).toString().substring(0, selectedChildList.get(i).toString().indexOf("@"));
				
				String HARDCODED_FLAG = "";
				List<HardcodedProductVO> hardcodedProductVOList = HardcodedProducts.getHardcodedProductVOList();
		    	for( HardcodedProductVO prodVO : hardcodedProductVOList )
		        {
		    		String id1 = prodVO.getAssurancePlanCd().substring(0,prodVO.getAssurancePlanCd().indexOf("="));
		    		AssurancePlanList[] optionVOarr = prodVO.getBusinessOptionVOArr();
		    		for( AssurancePlanList optionVO : optionVOarr )
	        		{
	        			String id2 = optionVO.getValue().substring(optionVO.getValue().indexOf("~X")+2);
	        			if(LSBS_ID.equals(id1) && LSDBS_NUMBER.equals(id2) ){
	        				HARDCODED_FLAG = prodVO.getProductDescr();
	        			}
	        		}
		    		String id2 = prodVO.getAssurancePlanCd().substring(0,prodVO.getAssurancePlanCd().indexOf("="));
		        }
				
				paramsId.put("GROUP_ID", groupId );
				paramsId.put("LSBS_ID", LSBS_ID);
				paramsId.put("LSDBS_NUMBER", LSDBS_NUMBER);
				paramsId.put("HARDCODED_FLAG", HARDCODED_FLAG);
				eproposalManager.selectedNewChildList(paramsId);
				//selectedNewChildList.add(paramsId);
			}
    	}
    	//--------------------------------------------------------
    	// cek delete child
    	for(int i = 0; i < allChildList.size() ; i++){
    		if( !selectedChildList.contains( allChildList.get(i) )){
    			Map<String, Object> paramsId = new HashMap<String, Object>();
    			//LSBS_ID = allIdcList.get(i).getLsbsId();
    			//LSDBS_NUMBER = 	allIdcList.get(i).getLsdbsNumber();
    			LSDBS_NUMBER = allChildList.get(i).toString().substring(0,allChildList.get(i).toString().indexOf("@"));
    			LSBS_ID = allChildList.get(i).toString().substring(allChildList.get(i).toString().indexOf("@")+1);
    			paramsId.put("GROUP_ID", groupId );
    			paramsId.put("LSBS_ID", LSBS_ID);
    			paramsId.put("LSDBS_NUMBER", LSDBS_NUMBER);
    			eproposalManager.unselectedChildList(paramsId);
    			//unselectedChildList.add(paramsId);
    		}
    	}
    	//----------------------------------------------------------
    	//	cek delete parent
    	for(int i = 0 ; i < allParentList.size() ; i++ ){
    		if(!selectedParentList.contains(allParentList.get(i))){
    			Map<String, Object> paramsId = new HashMap<String, Object>();
    			paramsId.put("GROUP_ID", groupId );
    			paramsId.put("LSBS_ID", allIdpList.get(i).getId());
    			eproposalManager.unselectedParentList(paramsId);
    			//unselectedParentList.add(paramsId);
    		}
    	}
    	//----------------------------------------------------------
    	//	cek delete hardcoded parent
    	// masalah
    	for(int i = 0 ; i < allHardcodedParentList.size() ; i++ ){
    		String x = allHardcodedParentList.get(i).toString().substring(0, allHardcodedParentList.get(i).toString().indexOf("=>"));
    		if(!selectedHardcodedParentList.contains(allHardcodedParentList.get(i).toString().substring(0, allHardcodedParentList.get(i).toString().indexOf("=>")))){
    			Map<String, Object> paramsId = new HashMap<String, Object>();
    			paramsId.put("GROUP_ID", groupId );
    			paramsId.put("HARDCODED_FLAG", allHardcodedParentList.get(i).toString().substring(allHardcodedParentList.get(i).toString().indexOf("=>") +2) );
    			eproposalManager.unselectedHardcodedParentList(paramsId);
    			//unselectedHardcodedParentList.add(paramsId);
    		}
    	}
    	//------------------------------------------------------------
    	result.put("keterangan", "Save sukses");
    	
    	return result;
    	//
    	//if(unselectedParentList.size() > 0){
    		// lakukan delete parent
    	//}
    	//for(int i = 0 ; i < allList.size() ; i++ ){
    		//if(allList.get(i).getLsbsId())
    	//}
	/*
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put( "LSBS_ID", cepr01020202Form.getLsbsId() );
    	
    	List<ViewListBusiness> allList = eproposalManager.selectAddBusiness(params);
    	
    	for(int i = 0 ; i < allList.size() ; i++ ){
    		try{
    			Map<String, Object> addParams = new HashMap<String, Object>();
    			addParams.put("GROUP_ID", groupId);
    			addParams.put("LSBS_ID", allList.get(i).getLsbsId());
    			addParams.put("LSDBS_NUMBER", allList.get(i).getLsdbsNumber());
    			eproposalManager.addBusiness(addParams);
    			logger.info("**********data ditambah");
    		}catch(Exception e){
    			logger.info("**********data sudah ada(aktif)");
    		}
    	}
    	logger.info("***"+allList);
    	*/
    }
    
}
