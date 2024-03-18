package id.co.sinarmaslife.eproposal.dao;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	:
 * Program Name   		: Cepr01030101Dao
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 24, 2007 9:10:36 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.parent.EproposalDao;
import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.standard.model.vo.AssurancePlanList;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contoh implementasi DAO, dan langsung diakses oleh service layer (EbManager)
 * Seharusnya tidak langsung, melainkan menggunakan interface, untuk memudahkan
 * dalam penggantian implementasi (misalnya tidak pake ibatis lagi, tapi pake hibernate)
 * <p/>
 * Untuk query2 simple, biasanya penamaan classnya menggunakan syntax SQL, misalnya
 * select, insert, update, delete, etc..
 * <p/>
 * Untuk proses2 yang lebih ribet (berupa transaksi), penamaannya beda, misalnya
 * saveApplication, prosesDataXXX, etc..
 * <p/>
 * Bean ini untuk akses source pada database EB
 *
 * @author Yusuf Sutarko
 * @since Feb 12, 2007 (6:50:01 PM)
 */
@SuppressWarnings( "unchecked" )
public class Cepr01030102Dao extends EproposalDao
{
    public Cepr01030102Dao() throws Exception {
        initDao();
    }
    
    public List<OptionVO> selectCurrencyOptionVOList( Map<String, List<String>> params ) throws DataAccessException
    {
        return query( "selectCurrencyOptionVOList", params );
    }
  
    public List<OptionVO> selectPayModeOptionVOList( Map<String, List<Integer>> params ) throws DataAccessException
    {
        return query( "selectPayModeOptionVOList", params );
    }

    public List<AssurancePlanList> selectSpecificLstBusinessOptionVOList( Map<String, List<String>> params  ) throws DataAccessException
    {
        return query( "selectSpecificLstBusinessOptionVOList", params );
    }

    public List<String> selectLstProductsFilteredOptionVOList( String msagId ) throws DataAccessException
    {
        return query( "selectLstProductsFilteredOptionVOList", msagId );
    }
    
    public List<String> selectLstProductsPackageFilteredOptionVOList( String msagId ) throws DataAccessException
    {
        return query( "selectLstProductsPackageFilteredOptionVOList", msagId );
    }
    
    public List<Map> selectNoProposal(Map<String, Object> params) throws DataAccessException
    {
    	return query("selectNoProposal", params );
    }
    
    public void updateNoProposal(Map<String, Object> params) throws DataAccessException
    {  	
	    update( "updateNoProposal", params );
    }
    
    public void insertMstDataProposal(Map<String, Object> params) throws DataAccessException
    {  	
	    insert( "insertMstDataProposal", params );
    }
    
    public void insertMstProductProposal(Map<String, Object> params, List<Map<String, Object>> list) throws DataAccessException
    {  	
	    insert( "insertMstProductProposal", params );
	    
	    if(list != null && list.size() > 0) {
	    	for(int i = 0; i < list.size(); i++) {
	    		Map<String, Object> map = (Map<String, Object>) list.get(i);
	    		insert( "insertMstPacketProposal", map);
	    	}
	    }
    }
    
    public  List<Map> selectCredentialsVOLogin ( Map<String, Object> params ) throws DataAccessException
    {
    	return ( List<Map> ) query( "selectCredentialsVOLogin", params );
    }
    
    public void insertMstRiderProposal(List<Map<String, Object>> rider, List<Map<String, Object>> peserta) throws DataAccessException
    {
    	if(rider != null && rider.size() > 0) {
    		for(int i = 0; i < rider.size(); i++) {
    			Map<String, Object> r = (Map<String, Object>) rider.get(i);
    			insert("insertMstRiderProposal", r);
    		}
    	}
    	
    	if(peserta != null && peserta.size() > 0) {
    		for(int i = 0; i < peserta.size(); i++) {
    			Map<String, Object> p = (Map<String, Object>) peserta.get(i);
    			insert("insertMstPesertaRiderProposal", p);
    		}
    	}
    }
    
    public List<OptionVO> selectRelationOptionVOList()
    {
    	return query("selectRelationOptionVOList", null);
    }     
       
    
    public void insertProposalProductUlink(Map<String, Object> params) throws DataAccessException
    {  	
	    insert( "insertProposalProductUlink", params );
    }
    
    public void insertProposalProductTopupAndDraw(String no_proposal, List<TopupDrawVO> topupDrawVOList  ) throws DataAccessException
    {
    	for( TopupDrawVO vo : topupDrawVOList )
        {
        	if( vo.getYearFlag() != null && (vo.getTopupAmount().compareTo(new BigDecimal(0)) >= 1 || vo.getDrawAmount().compareTo(new BigDecimal(0)) >= 1  )   ){
        		Map<String,Object> param = new HashMap<String, Object>();	    
        	 	param.put( "no_proposal", no_proposal );	
        	 	param.put("thn_ke", vo.getYearNo());
        	 	param.put("topup", vo.getTopupAmount());
        	 	param.put("draw", vo.getDrawAmount());
        	 	insert("insertProposalProductTopupAndDraw", param);
        	}
        }
    	
    }
    
    public List<OptionVO> selectLstPacket(Integer params ) throws DataAccessException
    {
        return query( "selectLstPacket", params );
    }
    
    public List<Map<String, Object>> selectNamaAdminBCList( Map<String, Object> params  ) throws DataAccessException
    {	 	
        return (List<Map<String, Object>>) query( "selectNamaAdminBCList", params );
    }
    
    public Map<String, Object> selectDetailAdminBC( String lrbId  ) throws DataAccessException
    {	 	
    	return ( Map ) querySingle( "selectDetailAdminBC", lrbId );
    }
     
         
    public List< OptionVO > getPacketTabelList(  Map<String, Object> params)
    {
    	 return query( "getPacketTabelList", params );
    }
}