package id.co.sinarmaslife.eproposal.dao;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01010101Dao
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 6, 2008 8:52:05 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.parent.EproposalDao;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.standard.model.vo.AssurancePlanList;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.model.vo.UserGroup;

import org.springframework.dao.DataAccessException;

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
public class Cepr01010101Dao extends EproposalDao
{
    public Cepr01010101Dao() throws Exception {
        initDao();
    }
    
    public List<Map> selectCheckCredentialsForFirstTime( Map<String, Object> params ) throws DataAccessException
    {
        return query( "selectCheckCredentialsForFirstTime", params );
    }
   
    public CredentialsVO selectLoginInfo( Map<String, Object> params ) throws DataAccessException
    {
        return ( CredentialsVO ) querySingle( "selectLoginInfo", params );
    }

    public HashMap selectMstConfig(Integer app_id, String section, String sub_section){
		HashMap result;
		HashMap param = new HashMap();
		param.put("app_id", app_id);
		param.put("section", section);
		param.put("sub_section", sub_section);
		result =(HashMap) querySingle("selectMstConfig", param);
		return result;
	}
    
    public List<Map> selectToCheckAgentExistence( Map<String, Object> params ) throws DataAccessException
    {
        return ( List<Map> ) query( "selectToCheckAgentExistence", params );
    }
    
    public List<Map> selectToCheckUserExternalExistence( Map<String, Object> params ) throws DataAccessException
    {
        return ( List<Map> ) query( "selectToCheckUserExternalExistence", params );
    }
    
    public List<Map> selectBancass1FromLst_Reff_Bii( Map<String, Object> params ) throws DataAccessException
    {
        return ( List<Map> ) query( "selectBancass1FromLst_Reff_Bii", params );
    }
    
    public List<Map> selectBancass2FromLst_Reff_Bii( Map<String, Object> params ) throws DataAccessException
    {
        return ( List<Map> ) query( "selectBancass2FromLst_Reff_Bii", params );
    }
    
    public List<Map> selectBancass3FromLst_Reff_Bii( Map<String, Object> params ) throws DataAccessException
    {
        return ( List<Map> ) query( "selectBancass3FromLst_Reff_Bii", params );
    }
    
    public List<Map> selectBancass4FromLst_Reff_Bii( Map<String, Object> params ) throws DataAccessException
    {
        return ( List<Map> ) query( "selectBancass4FromLst_Reff_Bii", params );
    }
    
    public Map selectUserHardcodedReferential( Map<String, Object> params ) throws DataAccessException
    {
    	return ( Map ) querySingle( "selectUserHardcodedReferential", params );
    }
    
    public  List<Map> selectLoginInfoEB ( Map<String, Object> params ) throws DataAccessException
    {
    	return ( List<Map> ) query( "selectLoginInfoEB", params );
    }
    
    public  List<Map> selectLoginLstUser ( Map<String, Object> params ) throws DataAccessException
    {
    	return ( List<Map> ) query( "selectLoginLstUser", params );
    }
    
    public  List<Map> selectLoginInfoAjsDb ( Map<String, Object> params ) throws DataAccessException
    {
    	return ( List<Map> ) query( "selectLoginInfoAjsDb", params );
    }
    
    public Map selectLoginInfoHardcodedAjsDb( Map<String, Object> params ) throws DataAccessException
    {
    	return ( Map ) querySingle( "selectLoginInfoHardcodedAjsDb", params );
    }
    
    public void insertUserHardcoded( Map<String, Object> params ) throws DataAccessException
    {
    	insert("insertUserHardcoded", params);
    }
    
    public String selectDecrypt( Map<String, Object> params ) throws DataAccessException
    {
    	return ( String ) querySingle("selectDecrypt", params);
    }

    public void insertUserExternal( Map<String, Object> params  ) throws DataAccessException
    {
        insert( "insertUserExternal", params );
    }
    
    public void insertNewUserProduct( Map<String, Object> params  ) throws DataAccessException
    {
        insert( "insertNewUserProduct", params );
    }
    
    public void insertUserProduct( Map<String, Object> params  ) throws DataAccessException
    {
        insert( "insertUserProduct", params );
    }
    
    public UserGroup selectUserGroup( Map<String, Object> params ) throws DataAccessException
    {
        return ( UserGroup ) querySingle( "selectUserGroup", params );
    }
    
    public UserGroup selectUserGroupHardcodedReferential( Map<String, Object> params ) throws DataAccessException
    {
        return ( UserGroup ) querySingle( "selectUserGroupHardcodedReferential", params );
    }
    
    public String selectAgentType( Map<String, Object> params ) throws DataAccessException
    {
        return ( String ) querySingle( "selectAgentType", params );
    }
    
    public String selectGroupId( Map<String, Object> params ) throws DataAccessException
    {
        return ( String ) querySingle( "selectGroupId", params );
    }
    
    public String selectGroup( Map<String, Object> params) throws DataAccessException
    {
        return ( String ) querySingle( "selectGroup", params);
    }

    public int updateUserProduct( Map<String, Object> params ) throws DataAccessException
    {
        return update( "checkIdentityBeforeInsertUserExternal", params );
    }

    public String selectAuthenticateAsmUser( Map<String, Object> params ) throws DataAccessException
    {
        return ( String ) querySingle( "selectAuthenticateAsmUser", params );
    }
    
	@SuppressWarnings("unchecked")
	public void insertMstLoginHist(Integer jenis, String remote_addr, String remote_host, 
			String request_url, String user_id, Integer user_login_attempt, String user_pass_entered) throws DataAccessException{
		Map map = new HashMap();
		map.put("jenis", jenis);
		map.put("remote_addr", remote_addr);
		map.put("remote_host", remote_host);
		map.put("request_url", request_url);
		map.put("user_id", user_id);
		map.put("user_login_attempt", user_login_attempt);
		map.put("user_pass_entered", user_pass_entered);
		insert("insertMstLoginHist", map);
	}
	
	public void updateOnlineDateLstUserExtenal(String msagId, Integer jenis) throws DataAccessException
	{
	    Map map = new HashMap();
		map.put("msag_id", msagId);
		map.put("jenis", jenis);
	    update( "updateOnlineDateLstUserExtenal", map );
	}

	 public Map selectLoginUrlSecure( Map<String, Object> params ) throws DataAccessException
	 {
	     return ( Map ) querySingle( "selectLoginUrlSecure", params );
	 }
	 
	 public void deleteLoginUrlSecure(Map<String, Object> params) throws DataAccessException
	 {  	
		 	delete( "deleteLoginUrlSecure", params );
	 }
	 
	 public void insertAksesHist(Map<String, Object> params) throws DataAccessException
	 {  			
		 insert( "insertAksesHist", params );
	 }
	 	 
	 public Map selectAgentBC( Map<String, Object> params ) throws DataAccessException
	 {
	        return ( Map ) querySingle( "selectAgentBC", params );
	 }
	 	
	 public Map selectMspeEmail( Map<String, Object> params ) throws DataAccessException
	 {
	        return ( Map ) querySingle( "selectMspeEmail", params );
	 }
	 
	 public Map selectAgentBCVIP() throws DataAccessException
	 {
	        return ( Map ) querySingle( "selectAgentBCVIP", null );
	 }
	 	
}
