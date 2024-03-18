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
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030501Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030501Form;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.model.vo.TotalPage;
import id.co.sinarmaslife.standard.model.vo.ViewListProvider;
//import id.co.sinarmaslife.standard.model.vo.ViewListProviderDetail;

import id.co.sinarmaslife.standard.util.HtmlUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cepr01030501Business
{
    protected final Log logger = LogFactory.getLog( getClass() );

    protected EproposalManager eproposalManager;
    protected Cepr00000000EditorUtil editorUtil;

    public Cepr01030501Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil )
    {
        this.eproposalManager = eproposalManager;
        this.editorUtil = editorUtil;
    }

    public void resetForm( Cepr01030501Form cepr01030501Form )
    {
        //cepr01030501Form.setProposalDate( cepr00000000GeneralBusiness.selectNowDate() );
//        cepr01040103Form.setAssurancePlanList( cepr00000000ComboLookupBusiness.selectLstBusinessOptionVOList( true, null ) );
    }
    
    public List<ViewListProvider> selectAllProviderList( String rsNama, String rsAlamat, String kota, String noPage, String noRow, String sortRow )
    {
    	logger.info( "*-*-*-* Cepr01030501Business.selectAllProviderList" );
    	List<ViewListProvider> result = new ArrayList<ViewListProvider>();
    	//String ljpId = cepr01040103Form.getLjpId;
    	//Integer lstbId = 1;
    	
    	if(rsNama == ""){rsNama = null;}
    	if(rsAlamat == ""){rsAlamat = null;}
    	
    	if("sortedByNama".equals(sortRow)){
    		sortRow = "RSNAMA";
    	}else if("sortedByAlamat".equals(sortRow)){
    		sortRow = "RSALAMAT";
    	}
    	
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put( "RSNAMA", rsNama );
    	params.put( "RSALAMAT", rsAlamat );
    	params.put( "LSKA_ID", kota );
    	params.put( "noPage", Integer.parseInt(noPage) );
    	params.put( "noRow", Integer.parseInt(noRow) );
    	params.put( "sortRow", sortRow );
    	//todo
    	
    	result = eproposalManager.selectAllProviderList( params );
    	
    	
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
    
    public List<TotalPage> selectTotalProviderPage( String rsNama, String noRow, String rsAlamat)
    {
    	logger.info( "*-*-*-* Cepr01030501Business.selectTotalProviderPage" );
    	List<TotalPage> result = new ArrayList<TotalPage>();
    	
    	if(rsNama == ""){rsNama = null;}
    	if(rsAlamat == ""){rsAlamat = null;}
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put( "RSNAMA", rsNama );
    	params.put( "RSALAMAT", rsAlamat );
    	params.put( "noRow", Integer.parseInt(noRow) );

    	result = eproposalManager.selectTotalProviderPage( params );
    	
    	return result;
    }
    
    public Map<String, Object> selectProviderList(Object command, String cPage, String sortRow){
    	
    	Cepr01030501Form cepr01030501Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030501Form();
    	
    	Map<String, Object> result = new HashMap<String, Object>();
        
        //Map<String, Object> map = new HashMap<String, Object>();
        
        Cepr01030000HoldingForm cepr01030000HoldingForm = (Cepr01030000HoldingForm) command;
        
       
        //Cepr01030501Form cepr01030501Form = cepr01030000HoldingForm.getCepr01030501Form();
        
        List<OptionVO> selectAllCityProvider = eproposalManager.selectAllCityProvider(null);
        cepr01030501Form.setKotaList( selectAllCityProvider );
        
//-COMBOBOX-----------------------------------------------------------------------------------------------
        
        // LOGIKA UNTUK AMBIL ISI rsNama, HANDLE rsNama=NULL
        if( cepr01030501Form.getRsNama() == null ){ cepr01030501Form.setRsNama(""); }
        if( cepr01030501Form.getRsAlamat() == null ){ cepr01030501Form.setRsAlamat(""); }
        String rsNama = cepr01030501Form.getRsNama();
        String rsAlamat = cepr01030501Form.getRsAlamat();
        String kota = cepr01030501Form.getKotaCd();
        
        
 
//-COMBOBOX-Records/Page------------------------------------------------------------------------------------
        
        // UNTUK COMBOBOX ISI Records/Page
        List<OptionVO> listRecords = new ArrayList();
        listRecords.add(new OptionVO( "10" , "10" ));
        listRecords.add(new OptionVO( "25" , "25" ));
        listRecords.add(new OptionVO( "50" , "50" ));
        listRecords.add(new OptionVO( "100" , "100" ));
        listRecords.add(new OptionVO( "250" , "250" ));
        listRecords.add(new OptionVO( "500" , "500" ));
        cepr01030501Form.setListRecords(listRecords);
                
        
        
//-SEARCH-----------------------------------------------------------------------------------------------        
        
        String noPage = "";
        String noRow = "0";
        
        // MULAI HANDLE BAGIAN SEARCH DI PAGE Cepr01030501JSP
        //if( page == 0 || "onPressButtonSearch".equals( theEvent ) )
        //{	
        	
            if(cPage == null || cPage == "" ){
            	noPage = "1";
            }else{
            	noPage = cPage;
            }
        	
            if(cepr01030501Form.getNoRow() == null){
            	cepr01030501Form.setNoRow("25");
            	noRow = "25";
            }
            	noRow = cepr01030501Form.getNoRow();
            	
            if(cepr01030501Form.getSortRow() == null ){
            	cepr01030501Form.setSortRow("sortedByNama");
            }else if(sortRow != ""){
            	cepr01030501Form.setSortRow(sortRow);
            }
            
        	
            List<TotalPage> totalPage = selectTotalProviderPage(rsNama, noRow, rsAlamat);
            
            // HANDLE PAGE LESS OR PAGE OVER
            if(Integer.parseInt(noPage) < 1){
            	noPage = "1";
            }
            if(Integer.parseInt(noPage) > Integer.parseInt(totalPage.get(0).getTotalPage())){
            	noPage = totalPage.get(0).getTotalPage();
            }
            
        	List<ViewListProvider> allList = selectAllProviderList(rsNama, rsAlamat, kota, noPage, noRow, cepr01030501Form.getSortRow());
        	
        	int currPage = Integer.parseInt(noPage);
        	cepr01030501Form.setFirstPage(1 + "");
        	cepr01030501Form.setLastPage(totalPage.get(0).getTotalPage());
        	cepr01030501Form.setNextPage((currPage + 1) + "");
        	cepr01030501Form.setPreviousPage((currPage - 1) + "");
        	
        	result.put("allList", allList);
        	result.put("currPage", noPage);
            
            if( cepr01030501Form.getAllList() == null){
            	logger.info( "hasilnya ga ada" );
            }else {
            	logger.info( "hasilnya ada" );
            }   
        //} 
        //AKHIR HANDLE BAGIAN SEARCH DI PAGE Cepr01030501JSP
        
//------------------------------------------------------------------------------------------------        
        
    	return result;
    }
    
    
    
}
