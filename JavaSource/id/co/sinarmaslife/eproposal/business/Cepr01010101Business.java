package id.co.sinarmaslife.eproposal.business;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01010101Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 6, 2008 4:07:41 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 * 1.0			10/9/2008					Andy				atur hak akses			
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.model.vo.UserGroup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01010101Business extends StandardParent
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public boolean matchWithAgentDate( String msagId, String birthday )
    {
        logger.info( "*-*-*-* Cepr01010101Business.matchWithAgentDate" );

        boolean result = false;
        List resultList;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "msag_id", msagId );
        params.put( "mspe_date_birth", birthday );
        resultList = eproposalManager.selectCheckCredentialsForFirstTime( params );

        if( resultList != null && resultList.size() > 0 )
        {
            result = true;
        }

        return result;
    }

    public List<Map> selectToCheckAgentExistence( String account )
    {
        logger.info( "*-*-*-* Cepr01010101Business.selectToCheckAgentExistence" );

        List<Map> result;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "account", account );
        result = eproposalManager.selectToCheckAgentExistence( params );

        return result;
    }

    public List<Map> selectToCheckUserExternalExistence( String account )
    {
        logger.info( "*-*-*-* Cepr01010101Business.selectToCheckUserExternalExistence" );

        List<Map> result;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "account", account );
        result = eproposalManager.selectToCheckUserExternalExistence( params );

        return result;
    }
    
    public CredentialsVO selectLoginInfo( String account, String msagId )
    {
        logger.info("Cepr01010101Business.selectLoginInfo");

        CredentialsVO result;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "account", account );
        params.put( "msagId", msagId );
        result = eproposalManager.selectLoginInfo( params );

        return result;
    }

    public boolean insertUserExternal( String msagId, String account, String password, String jenis, String jn_bank )
    {
        logger.info( "*-*-*-* Cepr01010101Business.insertUserExternal" );
        boolean processIsSucceeded = false;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "msag_id", msagId );
        params.put( "id", account );
        params.put( "password", password );
        params.put( "jenis", jenis );
        params.put( "jn_bank", jn_bank );
        eproposalManager.insertUserExternal( params );

        return processIsSucceeded;
    }
    
    public UserGroup selectUserGroup( String msagId, String group_id )
    {
        logger.info("Cepr01010101Business.selectUserGroup");

        UserGroup result;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "msag_id", msagId );
        params.put( "group_id", group_id );
        result = eproposalManager.selectUserGroup( params );

        return result;
    }
    
    public UserGroup selectUserGroupHardcodedReferential( String msagId, String userName )
    {
        logger.info("Cepr01010101Business.selectUserGroupHardcodedReferential");

        UserGroup result;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "msag_id", msagId );
        params.put( "userName", userName );
        result = eproposalManager.selectUserGroupHardcodedReferential( params );

        return result;
    }
    
    public String selectAgentType( String msagId )
    {
        logger.info("Cepr01010101Business.selectAgentType");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "msag_id", msagId );
        String result = eproposalManager.selectAgentType( params );

        return result;
    }
    
    public String selectGroupId(String groupName)
    {
        logger.info("Cepr01010101Business.selectGroupId");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "GROUP_NAME", groupName );
        String result = eproposalManager.selectGroupId(params);

        return result;
    }
    
    public String selectGroup(String msag_id) //CR#: NCR/2020/08/018 DISABLE ACCESS (IGA) 
    {
        logger.info("Cepr01010101Business.selectGroup");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "msag_id", msag_id );
        String result = eproposalManager.selectGroup(params);

        return result;
    }
    //DONE
    public void insertNewUserProduct(String account, String groupId)
    {
        logger.info("Cepr01010101Business.insertNewUserProduct");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("MSAG_ID", account);
        params.put("GROUP_ID", groupId);
        eproposalManager.insertNewUserProduct(params);
    }

    public void updateUserProduct( String msagId, String groupId )
    {
        logger.info( "*-*-*-* Cepr01010101Business.updateUserProduct" );

        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "msag_id", msagId );
        params.put( "group_id", groupId );
        eproposalManager.updateUserProduct( params );
    }

    public String authenticateAndgetAsmFullName( String account, String password )
    {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "account", account );
        params.put( "password", password );
        return eproposalManager.selectAuthenticateAsmUser( params );
    }
    
    public List<Map> selectLoginInfoEB( String msagid, String lus_email, String password ) throws DataAccessException
    {
    	return eproposalManager.selectLoginInfoEB( msagid, lus_email, password );
    }
    
    public List<Map> selectLoginLstUser( Integer jn_bank, String lus_login_name, String password ) throws DataAccessException
    {
    	return eproposalManager.selectLoginLstUser(jn_bank, lus_login_name, password);
    }
    
    public List<Map> selectBancass1FromLst_Reff_Bii( String account )
    {
        logger.info( "*-*-*-* Cepr01010101Business.selectBancassFromLst_Reff_Bii" );

        List<Map> result;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "account", account );
        result = eproposalManager.selectBancass1FromLst_Reff_Bii( params );

        return result;
    }
    
    public List<Map> selectBancass2FromLst_Reff_Bii( String account )
    {
        logger.info( "*-*-*-* Cepr01010101Business.selectBancass2FromLst_Reff_Bii" );

        List<Map> result;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "account", account );
        result = eproposalManager.selectBancass2FromLst_Reff_Bii( params );

        return result;
    }
    
    public List<Map> selectBancass3FromLst_Reff_Bii( String account )
    {
        logger.info( "*-*-*-* Cepr01010101Business.selectBancass3FromLst_Reff_Bii" );

        List<Map> result;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "account", account );
        result = eproposalManager.selectBancass3FromLst_Reff_Bii( params );

        return result;
    }
    
    public List<Map> selectBancass4FromLst_Reff_Bii( String account )
    {
        logger.info( "*-*-*-* Cepr01010101Business.selectBancass4FromLst_Reff_Bii" );

        List<Map> result;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "account", account );
        result = eproposalManager.selectBancass4FromLst_Reff_Bii( params );

        return result;
    }
    
    public Map selectUserHardcodedReferential( String account, String password )
    {
        logger.info( "*-*-*-* Cepr01010101Business.selectUserHardcodedReferential" );

        Map result;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "account", account );
        params.put( "password", password );
        result = eproposalManager.selectUserHardcodedReferential( params );

        return result;
    }
    
    public Map selectAgentBC( String msagId )
    {      
       
    	Map result;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "msag_id", msagId );      
        result = eproposalManager.selectAgentBC( params );
      

        return result;
    }      
    
    public Map selectMspeEmail( String msagId )
    {  
    	Map result;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "msag_id", msagId );      
        result = eproposalManager.selectMspeEmail( params );
      
        return result;
    }
    
    public Map selectAgentBCVIP()
    {      
       
    	Map result;
        
        result = eproposalManager.selectAgentBCVIP();
      

        return result;
    }      
           
}
