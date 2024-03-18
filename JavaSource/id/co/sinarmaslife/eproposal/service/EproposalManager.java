package id.co.sinarmaslife.eproposal.service;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	:
 * Program Name   		: EproposalManager
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 24, 2007 9:06:54 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.dao.*;
import id.co.sinarmaslife.eproposal.model.data_proposal.DataProposal;
import id.co.sinarmaslife.eproposal.model.vo.Cepr00000000PerformanceVO;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.standard.model.vo.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import id.co.sinarmaslife.eproposal.model.Email;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Bean ini merupakan service layer dari aplikasi, semua controller hanya boleh
 * mengakses bean ini. Bean inilah yang selanjutnya mengakses dao-dao dibawahnya.
 * Bean ini juga merupakan transaction manager, dimana fungsi2 didalamnya akan dirollback saat terjadi
 * kesalahan, dan akan di commit saat sukses
 */

public class EproposalManager
{
    protected final Log logger = LogFactory.getLog( getClass() );
    private Properties props;
    private Cepr00000000CommonDao cepr00000000CommonDao;
    private Cepr01010101Dao cepr01010101Dao;
    private Cepr01020201Dao cepr01020201Dao;
    private Cepr01020202Dao cepr01020202Dao;
    private Cepr01020301Dao cepr01020301Dao;
    private Cepr01020401Dao cepr01020401Dao;
    private Cepr01030101Dao cepr01030101Dao;
    private Cepr01030102Dao cepr01030102Dao;
    private Cepr01030501Dao cepr01030501Dao;
    private Cepr01030502Dao cepr01030502Dao;

    // products non unit links
    private Cepr01040101Dao cepr01040101Dao;
    private Cepr01040102Dao cepr01040102Dao;
    private Cepr01040103Dao cepr01040103Dao;
    private Cepr01040104Dao cepr01040104Dao;
    private Cepr01040105Dao cepr01040105Dao;
    private Cepr01040106Dao cepr01040106Dao;
    private Cepr01040107Dao cepr01040107Dao;
    private Cepr01040108Dao cepr01040108Dao;
    private Cepr01040109Dao cepr01040109Dao;
    private Cepr01040110Dao cepr01040110Dao;
    private Cepr01040111Dao cepr01040111Dao;
    private Cepr01040112Dao cepr01040112Dao;
    private Cepr01040113Dao cepr01040113Dao;
    private Cepr01040114Dao cepr01040114Dao;
    private Cepr01040115Dao cepr01040115Dao;
    private Cepr01040116Dao cepr01040116Dao;
    private Cepr01040117Dao cepr01040117Dao;
    private Cepr01040118Dao cepr01040118Dao;
    private Cepr01040119Dao cepr01040119Dao;
    private Cepr01040120Dao cepr01040120Dao;
    private Cepr01040121Dao cepr01040121Dao;
    private Cepr01040124Dao cepr01040124Dao;
    private Cepr01040125Dao cepr01040125Dao;
    private Cepr01040126Dao cepr01040126Dao;
    private Cepr01040127Dao cepr01040127Dao;
    private Cepr01040128Dao cepr01040128Dao;
    private Cepr01040130Dao cepr01040130Dao;
    private Cepr01040131Dao cepr01040131Dao;
    private Cepr01040133Dao cepr01040133Dao;
    private Cepr01040136Dao cepr01040136Dao;
    private Cepr01040137Dao cepr01040137Dao;
    private Cepr01040147Dao cepr01040147Dao;
    
    // products unit links
    private Cepr01040201Dao cepr01040201Dao;
    private Cepr01040202Dao cepr01040202Dao;
    private Cepr01040203Dao cepr01040203Dao;
    private Cepr01040204Dao cepr01040204Dao;
    private Cepr01040205Dao cepr01040205Dao;
    private Cepr01040206Dao cepr01040206Dao;
    private Cepr01040207Dao cepr01040207Dao;
    private Cepr01040208Dao cepr01040208Dao;
    private Cepr01040209Dao cepr01040209Dao;
    private Cepr01040210Dao cepr01040210Dao;
    private Cepr01040211Dao cepr01040211Dao;
    private Cepr01040212Dao cepr01040212Dao;
    private Cepr01040213Dao cepr01040213Dao;
    private Cepr01040214Dao cepr01040214Dao;
    private Cepr01040215Dao cepr01040215Dao;
    private Cepr01040216Dao cepr01040216Dao;
    private Cepr01040219Dao cepr01040219Dao;
    private Cepr01040220Dao cepr01040220Dao;

    private Connection connection;

    public EproposalManager()
    {
        logger.info( "*-*-*-* EproposalManager constructor is called ..." );
    }


    public void setProps( Properties props )
    {
        this.props = props;
    }

    /*
    public Connection getConnection()
    {
        if( connection == null )
        {
            try
            {
                connection = cepr00000000CommonDao.getDataSource().getConnection();
            }
            catch( SQLException e )
            {
                logger.error("ERROR", e);
            }
        }
        return connection;
    }
    */

    public DataSource getDataSource()
    {
        return cepr00000000CommonDao.getDataSource();
    }
    public void setCepr00000000CommonDao( Cepr00000000CommonDao cepr00000000CommonDao )
    {
        this.cepr00000000CommonDao = cepr00000000CommonDao;
    }

    public void setCepr01010101Dao( Cepr01010101Dao cepr01010101Dao )
    {
        this.cepr01010101Dao = cepr01010101Dao;
    }

    public void setCepr01020201Dao( Cepr01020201Dao cepr01020201Dao )
    {
        this.cepr01020201Dao = cepr01020201Dao;
    }

    public void setCepr01020202Dao( Cepr01020202Dao cepr01020202Dao )
    {
        this.cepr01020202Dao = cepr01020202Dao;
    }

    public void setCepr01020301Dao( Cepr01020301Dao cepr01020301Dao )
    {
        this.cepr01020301Dao = cepr01020301Dao;
    }
    
    public void setCepr01020401Dao( Cepr01020401Dao cepr01020401Dao )
    {
        this.cepr01020401Dao = cepr01020401Dao;
    }
    
    public void setCepr01030101Dao( Cepr01030101Dao cepr01030101Dao )
    {
        this.cepr01030101Dao = cepr01030101Dao;
    }

    public void setCepr01030102Dao( Cepr01030102Dao cepr01030102Dao )
    {
        this.cepr01030102Dao = cepr01030102Dao;
    }

    public void setCepr01030501Dao( Cepr01030501Dao cepr01030501Dao )
    {
        this.cepr01030501Dao = cepr01030501Dao;
    }

    public void setCepr01030502Dao( Cepr01030502Dao cepr01030502Dao )
    {
        this.cepr01030502Dao = cepr01030502Dao;
    }

    public void setCepr01040101Dao( Cepr01040101Dao cepr01040101Dao )
    {
        this.cepr01040101Dao = cepr01040101Dao;
    }

    public void setCepr01040102Dao( Cepr01040102Dao cepr01040102Dao )
    {
        this.cepr01040102Dao = cepr01040102Dao;
    }

    public void setCepr01040103Dao( Cepr01040103Dao cepr01040103Dao )
    {
        this.cepr01040103Dao = cepr01040103Dao;
    }

    public void setCepr01040104Dao( Cepr01040104Dao cepr01040104Dao )
    {
        this.cepr01040104Dao = cepr01040104Dao;
    }

    public void setCepr01040105Dao( Cepr01040105Dao cepr01040105Dao )
    {
        this.cepr01040105Dao = cepr01040105Dao;
    }

    public void setCepr01040106Dao( Cepr01040106Dao cepr01040106Dao )
    {
        this.cepr01040106Dao = cepr01040106Dao;
    }

    public void setCepr01040107Dao( Cepr01040107Dao cepr01040107Dao )
    {
        this.cepr01040107Dao = cepr01040107Dao;
    }

    public void setCepr01040108Dao( Cepr01040108Dao cepr01040108Dao )
    {
        this.cepr01040108Dao = cepr01040108Dao;
    }

    public void setCepr01040109Dao( Cepr01040109Dao cepr01040109Dao )
    {
        this.cepr01040109Dao = cepr01040109Dao;
    }

    public void setCepr01040110Dao( Cepr01040110Dao cepr01040110Dao )
    {
        this.cepr01040110Dao = cepr01040110Dao;
    }

    public void setCepr01040111Dao( Cepr01040111Dao cepr01040111Dao )
    {
        this.cepr01040111Dao = cepr01040111Dao;
    }

    public void setCepr01040112Dao( Cepr01040112Dao cepr01040112Dao )
    {
        this.cepr01040112Dao = cepr01040112Dao;
    }

	public void setCepr01040113Dao(Cepr01040113Dao cepr01040113Dao) {
		this.cepr01040113Dao = cepr01040113Dao;
	}

	public void setCepr01040114Dao(Cepr01040114Dao cepr01040114Dao) {
		this.cepr01040114Dao = cepr01040114Dao;
	}
	
	public void setCepr01040115Dao(Cepr01040115Dao cepr01040115Dao) {
		this.cepr01040115Dao = cepr01040115Dao;
	}

	public void setCepr01040116Dao(Cepr01040116Dao cepr01040116Dao) {
		this.cepr01040116Dao = cepr01040116Dao;
	}
	
	public void setCepr01040117Dao(Cepr01040117Dao cepr01040117Dao) {
		this.cepr01040117Dao = cepr01040117Dao;
	}
	
	public void setCepr01040118Dao(Cepr01040118Dao cepr01040118Dao) {
		this.cepr01040118Dao = cepr01040118Dao;
	}

    public void setCepr01040119Dao( Cepr01040119Dao cepr01040119Dao )
    {
        this.cepr01040119Dao = cepr01040119Dao;
    }

    public void setCepr01040120Dao( Cepr01040120Dao cepr01040120Dao )
    {
        this.cepr01040120Dao = cepr01040120Dao;
    }

    public void setCepr01040121Dao( Cepr01040121Dao cepr01040121Dao )
    {
        this.cepr01040121Dao = cepr01040121Dao;
    }
    
    public void setCepr01040124Dao(Cepr01040124Dao cepr01040124Dao) {
		this.cepr01040124Dao = cepr01040124Dao;
	}

	public void setCepr01040125Dao(Cepr01040125Dao cepr01040125Dao) {
		this.cepr01040125Dao = cepr01040125Dao;
	}
	
	public void setCepr01040126Dao(Cepr01040126Dao cepr01040126Dao) {
		this.cepr01040126Dao = cepr01040126Dao;
	}

	public void setCepr01040201Dao( Cepr01040201Dao cepr01040201Dao )
    {
        this.cepr01040201Dao = cepr01040201Dao;
    }

    public void setCepr01040202Dao( Cepr01040202Dao cepr01040202Dao )
    {
        this.cepr01040202Dao = cepr01040202Dao;
    }

    public void setCepr01040203Dao( Cepr01040203Dao cepr01040203Dao )
    {
        this.cepr01040203Dao = cepr01040203Dao;
    }

    public void setCepr01040204Dao( Cepr01040204Dao cepr01040204Dao )
    {
        this.cepr01040204Dao = cepr01040204Dao;
    }

    public void setCepr01040205Dao( Cepr01040205Dao cepr01040205Dao )
    {
        this.cepr01040205Dao = cepr01040205Dao;
    }

    public void setCepr01040206Dao( Cepr01040206Dao cepr01040206Dao )
    {
        this.cepr01040206Dao = cepr01040206Dao;
    }

    public void setCepr01040207Dao( Cepr01040207Dao cepr01040207Dao )
    {
        this.cepr01040207Dao = cepr01040207Dao;
    }

    public void setCepr01040208Dao( Cepr01040208Dao cepr01040208Dao )
    {
        this.cepr01040208Dao = cepr01040208Dao;
    }
    
    public void setCepr01040209Dao(Cepr01040209Dao cepr01040209Dao) {
		this.cepr01040209Dao = cepr01040209Dao;
	}

	public void setCepr01040210Dao(Cepr01040210Dao cepr01040210Dao) {
		this.cepr01040210Dao = cepr01040210Dao;
	}

    public void setCepr01040211Dao( Cepr01040211Dao cepr01040211Dao )
    {
        this.cepr01040211Dao = cepr01040211Dao;
    }
    
	public void setCepr01040212Dao(Cepr01040212Dao cepr01040212Dao) {
		this.cepr01040212Dao = cepr01040212Dao;
	}


	public void setCepr01040213Dao(Cepr01040213Dao cepr01040213Dao) {
		this.cepr01040213Dao = cepr01040213Dao;
	}


	public void setCepr01040214Dao(Cepr01040214Dao cepr01040214Dao) {
		this.cepr01040214Dao = cepr01040214Dao;
	}


	public void setCepr01040215Dao(Cepr01040215Dao cepr01040215Dao) {
		this.cepr01040215Dao = cepr01040215Dao;
	}

	
    public void setCepr01040216Dao(Cepr01040216Dao cepr01040216Dao) {
		this.cepr01040216Dao = cepr01040216Dao;
	}


	public void setCepr01040127Dao(Cepr01040127Dao cepr01040127Dao) {
		this.cepr01040127Dao = cepr01040127Dao;
	}


	public void setCepr01040128Dao(Cepr01040128Dao cepr01040128Dao) {
		this.cepr01040128Dao = cepr01040128Dao;
	}

	public void setCepr01040130Dao(Cepr01040130Dao cepr01040130Dao) {
		this.cepr01040130Dao = cepr01040130Dao;
	}


	public void setCepr01040131Dao(Cepr01040131Dao cepr01040131Dao) {
		this.cepr01040131Dao = cepr01040131Dao;
	}


	public Cepr01040133Dao getCepr01040133Dao() {
		return cepr01040133Dao;
	}


	public void setCepr01040133Dao(Cepr01040133Dao cepr01040133Dao) {
		this.cepr01040133Dao = cepr01040133Dao;
	}
	
	public Cepr01040219Dao getCepr01040219Dao() {
		return cepr01040219Dao;
	}
	
	public void setCepr01040219Dao(Cepr01040219Dao cepr01040219Dao) {
		this.cepr01040219Dao = cepr01040219Dao;
	}
	
	public Cepr01040220Dao getCepr01040220Dao() {
		return cepr01040220Dao;
	}
	
	public void setCepr01040220Dao(Cepr01040220Dao cepr01040220Dao) {
		this.cepr01040220Dao = cepr01040220Dao;
	}
		
	public Cepr01040147Dao getCepr01040147Dao() {
		return cepr01040147Dao;
	}
	
	public void setCepr01040147Dao(Cepr01040147Dao cepr01040147Dao) {
		this.cepr01040147Dao = cepr01040147Dao;
	}	
	
    public Properties getProps()
    {
        return props;
    }

    // start common
    public Date selectNowDate()
    {
        return cepr00000000CommonDao.selectNowDate();
    }
    
    public String selectTitleBisnis(int flag, int lsbsId)
    {
    	Map<String,Object> params = new HashMap<String, Object>();
    	params.put( "flag", flag );    
    	params.put( "lsbsId", lsbsId );   	
        return cepr00000000CommonDao.selectTitleBisnis(params);
    }    
    
    public Date selectAddMonths( Map<String, Object> params )
    {
        return cepr00000000CommonDao.selectAddMonths( params );
    }

    public List< String > selectJenisMedicalPrefix( Map<String, Object> params )
    {
        return cepr00000000CommonDao.selectJenisMedicalPrefix( params );
    }
    //CR#: NCR/2020/08/019 FLAG VIP (IGA)
    public List< String > selectJenisMedicalPrefixNew( Map<String, Object> params )
    {
        return cepr00000000CommonDao.selectJenisMedicalPrefixNew( params );
    }//DONE

    public String selectDecrypt ( String decrypt )
    {
    	Map<String,Object> params = new HashMap<String, Object>();
    	params.put("decrypt", decrypt);
    	return cepr01010101Dao.selectDecrypt(params);
    }
    
    public Integer selectIdRangeSar( Map<String, Object> params )
    {
        return cepr00000000CommonDao.selectIdRangeSar( params );
    }

    public Integer selectIdRangeAge( Map<String, Object> params )
    {
        return cepr00000000CommonDao.selectIdRangeAge( params );
    }

    // start business

    public List<Map> selectCheckCredentialsForFirstTime( Map<String, Object> params )
    {
        return cepr01010101Dao.selectCheckCredentialsForFirstTime( params );
    }

    public CredentialsVO selectLoginInfo( Map<String, Object> params )
    {
        return cepr01010101Dao.selectLoginInfo( params );
    }
    
    public List<Map> selectLoginInfoEB( String msagid, String lus_email, String password ) throws DataAccessException
    {
    	Map<String,Object> params = new HashMap<String, Object>();
    	params.put( "msagid", msagid );
    	params.put( "lus_email", lus_email );
    	params.put( "password", password );
    	return cepr01010101Dao.selectLoginInfoEB(params);
    }
    
    public List<Map> selectLoginLstUser( Integer jn_bank, String lus_login_name, String password ) throws DataAccessException
    {
    	Map<String,Object> params = new HashMap<String, Object>();
    	params.put( "jn_bank", jn_bank );
    	params.put( "lus_login_name", lus_login_name );
    	params.put( "password", password );
    	return cepr01010101Dao.selectLoginLstUser(params);
    }
    
    public List<Map> selectLoginInfoAjsDb( String lusId, String lus_email, String password, String jn_bank ) throws DataAccessException
    {
    	Map<String,Object> params = new HashMap<String, Object>();
    	params.put( "lusId", lusId );
    	params.put( "lus_email", lus_email );
    	params.put( "password", password );
    	params.put( "jn_bank", jn_bank );
    	return cepr01010101Dao.selectLoginInfoAjsDb(params);
    }
    
    public Map selectLoginInfoHardcodedAjsDb( String account, String lus_email, String password ) throws DataAccessException
    {
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put( "account", account );
    	params.put( "lus_email", lus_email );
    	params.put( "password", password );
    	
    	return cepr01010101Dao.selectLoginInfoHardcodedAjsDb(params);
    }
    
    public void insertUserHardcoded( String msag_id, String lus_id, String username, String password, String jenis, String group_id, Integer flag_aktif, String description, Integer kode ) throws DataAccessException
    {
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("msag_id", msag_id);
    	params.put("lus_id", lus_id);
    	params.put("username", username);
    	params.put("password", password);
    	params.put("jenis", jenis);
    	params.put("group_id", group_id);
    	params.put("flag_aktif", flag_aktif);
    	params.put("description", description);
    	params.put("kode", kode);
    	
    	cepr01010101Dao.insertUserHardcoded(params);
    }
    
    
    public UserGroup selectUserGroup( Map<String, Object> params )
    {
        return cepr01010101Dao.selectUserGroup( params );
    }
    
    public UserGroup selectUserGroupHardcodedReferential( Map<String, Object> params )
    {
        return cepr01010101Dao.selectUserGroupHardcodedReferential( params );
    }
    
    public String selectAgentType( Map<String, Object> params )
    {
        return cepr01010101Dao.selectAgentType( params );
    }
    
    public String selectGroupId( Map<String, Object> params )
    {
        return cepr01010101Dao.selectGroupId(params);
    }
    //CR#: NCR/2020/08/018 DISABLE ACCESS (IGA) 
    public String selectGroup( Map<String, Object> params )
    {
        return cepr01010101Dao.selectGroup(params);
    }//DONE

    public List<Map> selectToCheckAgentExistence( Map<String, Object> params )
    {
        return cepr01010101Dao.selectToCheckAgentExistence( params );
    }
    
    public List<Map> selectToCheckUserExternalExistence( Map<String, Object> params )
    {
        return cepr01010101Dao.selectToCheckUserExternalExistence( params );
    }
    
    public List<Map> selectBancass1FromLst_Reff_Bii( Map<String, Object> params )
    {
        return cepr01010101Dao.selectBancass1FromLst_Reff_Bii( params );
    }
    
    public HashMap selectMstConfig ( Integer app_id, String section, String sub_section )//CR#: NCR/2020/08/018 DISABLE ACCESS (IGA) 
    {
        return cepr01010101Dao.selectMstConfig(app_id, section, sub_section);
    }//DONE
    
    public List<Map> selectBancass2FromLst_Reff_Bii( Map<String, Object> params )
    {
        return cepr01010101Dao.selectBancass2FromLst_Reff_Bii( params );
    }
    
    public List<Map> selectBancass3FromLst_Reff_Bii( Map<String, Object> params )
    {
        return cepr01010101Dao.selectBancass3FromLst_Reff_Bii( params );
    }
    
    public List<Map> selectBancass4FromLst_Reff_Bii( Map<String, Object> params )
    {
        return cepr01010101Dao.selectBancass4FromLst_Reff_Bii( params );
    }
    
    public Map selectUserHardcodedReferential( Map<String, Object> params )
    {
        return cepr01010101Dao.selectUserHardcodedReferential( params );
    }
    
    public void insertUserExternal( Map<String, Object> params )
    {
        cepr01010101Dao.insertUserExternal( params );
    }
    
    public void insertNewUserProduct( Map<String, Object> params )
    {
        cepr01010101Dao.insertNewUserProduct( params );
    }

    public void insertUserProduct( Map<String, Object> params )
    {
        cepr01010101Dao.insertUserProduct( params );
    }
    
    public String selectAuthenticateAsmUser( Map<String, Object> params )
    {
        return cepr01010101Dao.selectAuthenticateAsmUser( params );
    }

    public void validateAndInsertUserProduct( String msagId, String groupId ){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "msag_id", msagId );
        params.put( "group_id", groupId );
    	UserGroup selectUserGroup = selectUserGroup(params);
    	if( selectUserGroup == null ){
    		//#MSAG_ID#, #GROUP_ID#
    		Map<String, Object> paramsInsert = new HashMap<String, Object>();
    		paramsInsert.put( "MSAG_ID", msagId );
    		paramsInsert.put( "GROUP_ID", groupId );
    		insertNewUserProduct(paramsInsert);
    	}
    }
    
    public void validateAndInsertUserProductNew( String msagId, String groupId, Integer jenis){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "msag_id", msagId );
        params.put( "group_id", groupId );
        params.put("jenis", jenis);
    	UserGroup selectUserGroup = selectUserGroup(params);
    	if( selectUserGroup == null ){
    		//#MSAG_ID#, #GROUP_ID#
    		Map<String, Object> paramsInsert = new HashMap<String, Object>();
    		paramsInsert.put( "MSAG_ID", msagId );
    		paramsInsert.put( "GROUP_ID", groupId );
    		paramsInsert.put("JENIS", jenis);
    		insertUserProduct(paramsInsert);
    	}
    }
    
    public List selectAllUsers( Map<String, Object> params ) {
		return cepr01020401Dao.selectAllUsers(params);
	}
    
    public List< OptionVO > selectLstBusinessOptionVOList( Map<String, List<String>> params )
    {
        return cepr01030101Dao.selectLstBusinessOptionVOList( params );
    }
    
    public List<String> selectLstBusinessFilteredOptionVOList( String msagId, String groupId )
    {
    	Map<String, Object> params= new HashMap<String, Object>();
    	params.put("msagId", msagId);
    	params.put("groupId", groupId);
        return cepr01030101Dao.selectLstBusinessFilteredOptionVOList( params );
    }
    
    public List<String> selectLsbsIdByMsagIdList( String msagId )
    {
        return cepr01030101Dao.selectLsbsIdByMsagIdList( msagId );
    }

    public String selectLastDay()
    {
        return cepr01030101Dao.selectLastDay();
    }

    
    public List<String> selectLstProductsFilteredOptionVOList( String msagId )
    {
        return cepr01030102Dao.selectLstProductsFilteredOptionVOList( msagId );
    }
    
    public List<String> selectLstProductsPackageFilteredOptionVOList( String msagId )
    {
        return cepr01030102Dao.selectLstProductsPackageFilteredOptionVOList( msagId );
    }

    public List< AssurancePlanList > selectSpecificLstBusinessOptionVOList( Map<String, List<String>> params )
    {
        return cepr01030102Dao.selectSpecificLstBusinessOptionVOList( params );
    }

    public List< OptionVO > selectCurrencyOptionVOList( Map<String, List<String>> params )
    {
        return cepr01030102Dao.selectCurrencyOptionVOList( params );
    }

    public List< OptionVO > selectPayModeOptionVOList( Map<String, List<Integer>> params )
    {
        return cepr01030102Dao.selectPayModeOptionVOList( params );
    }

    public List selectTest()
    {
        return cepr01040101Dao.selectTest();
    }

    public BigDecimal selectProduct53Rate( Map<String, Object> params )
    {
        return cepr01040102Dao.selectProduct53Rate( params );
    }

    public BigDecimal selectProduct54Rate( Map<String, Object> params )
    {
        return cepr01040103Dao.selectProduct54Rate( params );
    }

    public List<BigDecimal> selectRate( Map<String, Object> params )
    {
        return cepr00000000CommonDao.selectRate( params );  
    }
    
    public List<BigDecimal> selectRateNew( Map<String, Object> params )
    {
        return cepr00000000CommonDao.selectRateNew( params );  
    }

    public BigDecimal selectRateForLoopingYearNo( Map<String, Object> params )
    {
        return cepr00000000CommonDao.selectRateForLoopingYearNo( params );  
    }
    
    public BigDecimal selectRateForLoopingYearNoNew( Map<String, Object> params )
    {
        return cepr00000000CommonDao.selectRateForLoopingYearNoNew( params );  
    }
    
    public BigDecimal selectLdecRateToGetCoi( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateToGetCoi( params );
    }
    
    public BigDecimal selectLdecRateToGetCoi_119( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateToGetCoi_119( params );
    }

    public BigDecimal selectLdecRateToGetCoiBasic( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateToGetCoiBasic( params );
    }
    
    public BigDecimal selectLdecRateToGetCoi_190( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateToGetCoi_190( params );
    }    
    
    public BigDecimal selectLdecRateToGetTunai( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateToGetTunai( params );
    }
    
    public BigDecimal selectLdecRateToGetTunai_180( Map<String, Object> params )
    {
        return cepr01040133Dao.selectLdecRateToGetTunai_180( params );
    }
    
    public void setCepr01040136Dao(Cepr01040136Dao cepr01040136Dao) {
		this.cepr01040136Dao = cepr01040136Dao;
	}

	public void setCepr01040137Dao(Cepr01040137Dao cepr01040137Dao) {
		this.cepr01040137Dao = cepr01040137Dao;
	}

	public BigDecimal selectLdecRatePa( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRatePa( params );
    }

    public BigDecimal selectLdecRateHcp( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateHcp( params );
    }

    public BigDecimal selectLdecRateTpd( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateTpd( params );
    }
    
    public BigDecimal selectLdecRateTpd_120( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateTpd_120( params );
    }

    public BigDecimal selectLdecRateCi( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateCi( params );
    }
    
    public BigDecimal selectLdecRateCiRider( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateCiRider( params );
    }

    public BigDecimal selectLdecRateWp60Tpd( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateWp60Tpd( params );
    }
    
    public BigDecimal selectLdecRateWp60Tpd_120( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateWp60Tpd_120( params );
    }
    
    public BigDecimal selectLdecRateWp10Tpd( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateWp10Tpd( params );
    }

    public BigDecimal selectLdecRatePb25Tpd( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRatePb25Tpd( params );
    }

    public BigDecimal selectLdecRateWp60Ci( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateWp60Ci( params );
    }
    
    public BigDecimal selectLdecRateWp60Ci_120( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateWp60Ci_120( params );
    }
    
    public BigDecimal selectLdecRateWp10Ci( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateWp10Ci( params );
    }

    public BigDecimal selectLdecRatePb25CiDeath( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRatePb25CiDeath( params );
    }

    public BigDecimal selectLdecRatePb25Ci( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRatePb25Ci( params );
    }

    public BigDecimal selectLdecRatePayorSpouse( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRatePayorSpouse( params );
    }
    
    public BigDecimal selectLdecRatePayorTpdCiDeath( Map<String, Object> params)
    {
		return cepr01040201Dao.selectLdecRatePayorTpdCiDeath(params);
    }
    
    public BigDecimal selectLdecRateWaiverTpdCi( Map<String, Object> params)
    {
		return cepr01040201Dao.selectLdecRateWaiverTpdCi(params);
    }
    
    public BigDecimal selectLdecRateScholarship( Map<String, Object> params)
    {
		return cepr01040201Dao.selectLdecRateScholarship(params);
    }

    public BigDecimal selectLdecRateBaby( Map<String, Object> params)
    {
		return cepr01040201Dao.selectLdecRateBaby(params);
    }
    
    public BigDecimal selectLdecRateEarlyStageCi99( Map<String, Object> params)
    {
		return cepr01040201Dao.selectLdecRateEarlyStageCi99(params);
    }
    
    public BigDecimal selectLdecRateLadiesIns( Map<String, Object> params)
    {
		return cepr01040201Dao.selectLdecRateLadiesIns(params);
    }
    
    public BigDecimal selectLdecRateHcpFamily( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateHcpFamily( params );
    }

    public BigDecimal selectLdecRateHcpFamilyPesertaTambahan( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateHcpFamilyPesertaTambahan( params );
    }
    
    public BigDecimal selectLdecRateHcpLadies( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateHcpLadies( params );
    }

    public BigDecimal selectLdecRateHcpLadiesPesertaTambahan( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateHcpLadiesPesertaTambahan( params );
    }
    
    public BigDecimal selectLdecRateHcpProvider( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateHcpProvider( params );
    }

    public BigDecimal selectLdecRateHcpProviderPesertaTambahan( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateHcpProviderPesertaTambahan( params );
    }

    public List<Cepr00000000PerformanceVO> selectInvestmentPerformanceLastFourYears( Map<String, Object> params )
    {
        return cepr01040201Dao.selectInvestmentPerformanceLastFourYears( params );
    }

    public BigDecimal selectLdecRateTermRider( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateTermRider( params );
    }
    
    public BigDecimal selectLdecRateTerm( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateTerm( params );
    }
    
    public BigDecimal selectLdecRateTermRider_120( Map<String, Object> params )
    {
        return cepr01040201Dao.selectLdecRateTermRider_120( params );
    }

    public BigDecimal selectEkaSehatRider( Map<String, Object> params )
    {
        return cepr01040201Dao.selectEkaSehatRider( params );
    }
    
    public BigDecimal selectCashDanaSejahtera( Map<String, Object> params )
    {
        return cepr01040104Dao.selectCashDanaSejahtera( params );
    }
    
    public BigDecimal selectCashDanaSejahteraNew( Map<String, Object> params )
    {
        return cepr01040104Dao.selectCashDanaSejahteraNew( params );
    }

    public BigDecimal selectPkRateForDanaSejahtera( Map<String, Object> params )
    {
        return cepr01040104Dao.selectPkRateForDanaSejahtera( params );
    }
    
    public BigDecimal selectPkRate( Map<String, Object> params )
    {
        return cepr01040104Dao.selectPkRate( params );
    }
    
    public Integer selectFMax( Map<String, Object> params )
    {
        return cepr00000000CommonDao.selectFMax( params );
    }

    public BigDecimal selectRiderPcRate( Map<String, Object> params )
    {
        return cepr01040110Dao.selectRiderPcRate( params );
    }

    public BigDecimal selectRiderWpdRate( Map<String, Object> params )
    {
        return cepr01040110Dao.selectRiderWpdRate( params );
    }

    public Double selectSar( Map<String, Object> params ) throws DataAccessException
    {
        return cepr00000000CommonDao.selectSar( params );
    }
    
    public BigDecimal selectTableFactor( Map<String, Object> params ) throws DataAccessException
    {
        return cepr00000000CommonDao.selectTableFactor( params );
    }
    
    public BigDecimal selectNumericData( String params ) throws DataAccessException
    {
        return cepr00000000CommonDao.selectNumericData( params );
    }
    
    public List<ViewListProvider> selectAllProviderList(Map<String, Object> params)
    {
    	return cepr01030501Dao.selectAllProviderList(params);
    }
    
    public List<OptionVO> selectAllCityProvider(Map<String, Object> params)
    {
    	List<OptionVO> result = new ArrayList<OptionVO>();
    	result.add(new OptionVO(null, "All"));
    	result.addAll(cepr01030501Dao.selectAllCityProvider(params));
    	return result;
    }
    
    
    public List<TotalPage> selectTotalProviderPage(Map<String, Object> params)
    {
        return cepr01030501Dao.selectTotalProviderPage(params);
    }
    
    public List<ViewListProviderDetail> selectAllProviderDetailList(Map<String, Object> params)
    {
    	return cepr01030502Dao.selectAllProviderDetailList(params);
    }
    
    public List<ViewListAgent> selectAllAgentList(Map<String, Object> params)
    {
    	return cepr01020301Dao.selectAllAgentList(params);
    }
    
    public List<TotalPage> selectTotalAgentPage(Map<String, Object> params)
    {
        return cepr01020301Dao.selectTotalAgentPage(params);
    }
    
    public List<OptionVO> selectAllGroupList() throws DataAccessException
    {  	
    	return cepr01020301Dao.selectAllGroupList();
    }
    
    public void selectDeleteAgentGroup(Map<String, Object> params) throws DataAccessException
    {  	
    	 cepr01020301Dao.selectDeleteAgentGroup(params);
    }
    
    public void selectInsertAgentGroup(Map<String, Object> params) throws DataAccessException
    {  	
    	 cepr01020301Dao.selectInsertAgentGroup(params);
    }
    
    public void selectUpdateAgentGroup(Map<String, Object> params) throws DataAccessException
    {  	
    	 cepr01020301Dao.selectUpdateAgentGroup(params);
    }
    
    public List<ViewListGroup> selectGroupList(Map<String, Object> params) throws DataAccessException
    {  	
    	return cepr01020201Dao.selectGroupList(params);
    }
    
    public List<TotalPage> selectTotalGroupPage(Map<String, Object> params)
    {
        return cepr01020201Dao.selectTotalGroupPage(params);
    }
    
    public List<ViewListGroup> selectAllGroup(Map<String, Object> params) throws DataAccessException
    {  	
    	return cepr01020201Dao.selectAllGroup(params);
    }
    
    public void insertNewGroup(Map<String, Object> params) throws DataAccessException
    {  	
    	cepr01020201Dao.insertNewGroup(params);
    }
    
    public List<UserGroupId> selectAllUserGroup() throws DataAccessException
    {  	
    	return cepr01020201Dao.selectAllUserGroup();
    }
    
    public void deleteSelectedGroup(Map<String, Object> params) throws DataAccessException
    {  	
    	cepr01020201Dao.deleteSelectedGroup(params);
    }
    
    public void deleteSelectedGroupDetail(Map<String, Object> params) throws DataAccessException
    {  	
    	cepr01020201Dao.deleteSelectedGroupDetail(params);
    }
    
    public List<ViewListBusiness> selectBusinessList(Map<String, Object> params) throws DataAccessException
    {  	
    	return cepr01020202Dao.selectBusinessList(params);
    }
    
    public String selectGroupNameLabel(Map<String, Object> params) throws DataAccessException
    {  	
    	return cepr01020202Dao.selectGroupNameLabel(params);
    }
    
    public List<ViewListIdBusiness> selectBusinessParentIdList(Map<String, Object> params) throws DataAccessException
    {  	
    	return cepr01020202Dao.selectBusinessParentIdList(params);
    }
    
    public List<ViewListHardcodedBusiness> selectBusinessHardcodedParentIdList(Map<String, Object> params) throws DataAccessException
    {  	
    	return cepr01020202Dao.selectBusinessHardcodedParentIdList(params);
    }
    
    public List<ViewSelectedListBusiness> selectedBusinessList(Map<String, Object> params) throws DataAccessException
    {  	
    	return cepr01020202Dao.selectedBusinessList(params);
    }
    
    public List<ViewSelectedListBusiness> selectedHardcodedBusinessList(Map<String, Object> params) throws DataAccessException
    {  	
    	return cepr01020202Dao.selectedHardcodedBusinessList(params);
    }
    
    public List<ViewListBusiness> selectAddBusiness(Map<String, Object> params){
    	return cepr01020202Dao.selectAddBusiness(params);
    }
    
    public void addBusiness(Map<String, Object> params){
    	cepr01020202Dao.addBusiness(params);
    }
    
    public void selectedNewChildList(Map<String, Object> params){
    	cepr01020202Dao.selectedNewChildList(params);
    }
    
    public void unselectedChildList(Map<String, Object> params){
    	cepr01020202Dao.unselectedChildList(params);
    }
    
    public void unselectedParentList(Map<String, Object> params){
    	cepr01020202Dao.unselectedParentList(params);
    }
    
    public void unselectedHardcodedParentList(Map<String, Object> params){
    	cepr01020202Dao.unselectedHardcodedParentList(params);
    }

    public void updateUserProduct( Map<String, Object> params )
    {
    	cepr01010101Dao.updateUserProduct( params );
    }

    public BigDecimal selectProduct168PremiRate( Map<String, Object> params )
    {
        return cepr01040119Dao.selectProduct168PremiRate( params );
    }

	public BigDecimal selectEkaSehatInnerLimitRider(Map<String, Object> params) {
		return  cepr01040201Dao.selectEkaSehatInnerLimitRider( params );
	}
	
	public BigDecimal selectLadiesMedExpenseRider(Map<String, Object> params) {
		return  cepr01040201Dao.selectLadiesMedExpenseRider( params );
	}
	
	public BigDecimal selectLadiesMedExpenseInnerLimitRider(Map<String, Object> params) {
		return  cepr01040201Dao.selectLadiesMedExpenseInnerLimitRider( params );
	}
	
	public void insertMstLoginHist(Integer jenis, String remote_addr, String remote_host, 
			String request_url, String user_id, Integer user_login_attempt, String user_pass_entered) {
		cepr01010101Dao.insertMstLoginHist(jenis, remote_addr, remote_host, request_url, user_id, user_login_attempt, user_pass_entered);
	}

	public List<Map> selectNoProposal(String mmyyyy)
    {
		Map<String,Object> params = new HashMap<String, Object>();
    	params.put( "mmyyyy", mmyyyy );
        return cepr01030102Dao.selectNoProposal(params);
    }

	public void updateNoProposal(Integer noProposal, String mmyyyy)
    {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put( "noProposal", noProposal );    
    	params.put( "mmyyyy", mmyyyy );    	
        cepr01030102Dao.updateNoProposal(params);
    }
	
	public void insertMstDataProposal(Map<String, Object> params){
		cepr01030102Dao.insertMstDataProposal(params);
    }
    
	public void insertMstProductProposal(Map<String, Object> params, List<Map<String, Object>> list){
		cepr01030102Dao.insertMstProductProposal(params, list);
    }
	
	public List<Map> selectCredentialsVOLogin( String msagid) throws DataAccessException
	{
	    	Map<String,Object> params = new HashMap<String, Object>();
	    	params.put( "msagid", msagid );	    	
	    	return cepr01030102Dao.selectCredentialsVOLogin(params);
	}
	
	public void insertMstRiderProposal(List<Map<String, Object>> rider, List<Map<String, Object>> peserta) {
		cepr01030102Dao.insertMstRiderProposal(rider, peserta);
	}
	
	public List<OptionVO> selectRelationOptionVOList()
	{
		return cepr01030102Dao.selectRelationOptionVOList();
	}
		
	public BigDecimal selectRateExtMortalita( Map<String, Object> params )
    {
        return cepr01040201Dao.selectRateExtMortalita( params );
    }
	
	public void insertProposalProductUlink(Map<String, Object> params){
		cepr01030102Dao.insertProposalProductUlink(params);
    }
	
	public List<Map> selectRiderMedicalPlusList(String lsdbs_number) throws DataAccessException
	{
	 	Map<String,Object> param = new HashMap<String, Object>();	    
	   	param.put( "lsdbs_number", lsdbs_number );	    
	   	return cepr01040201Dao.selectRiderMedicalPlusList(param);
	}
	 
	public List<Map> selectRiderMedicalPlusRjList(String lsdbs_number) throws DataAccessException
	{
	   	Map<String,Object> param = new HashMap<String, Object>();	    
	   	param.put( "lsdbs_number", lsdbs_number );	    
	   	return cepr01040201Dao.selectRiderMedicalPlusRjList(param);
	 }	 
	
	public BigDecimal selectMedicalPlusRider( Map<String, Object> params )
	{
	    return cepr01040201Dao.selectMedicalPlusRider( params );
	}

	public BigDecimal selectMedicalPlusRjRider( Map<String, Object> params )
	{
	   return cepr01040201Dao.selectMedicalPlusRjRider( params );
	}
	
	public void insertProposalProductTopupAndDraw(String no_proposal, List<TopupDrawVO> topupDrawVOList ) {		 
		cepr01030102Dao.insertProposalProductTopupAndDraw(no_proposal, topupDrawVOList);
	}
	
	public void updateOnlineDateLstUserExtenal(String msagId, Integer jenis ) {		 
		cepr01010101Dao.updateOnlineDateLstUserExtenal(msagId, jenis);
	}
	
	public List< OptionVO > selectLstPacket( Integer params )
	{
		//List<OptionVO> result = new ArrayList<OptionVO>();
		return cepr01030102Dao.selectLstPacket( params );  
		//result = HtmlUtil.addEmptyOption( result );
		//return result;
	}
			
	public Map  selectLoginUrlSecure( String id_encrypt_1, String id_encrypt_2 ) throws DataAccessException
    {
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put( "idencrypt1", id_encrypt_1 );
    	params.put( "idencrypt2", id_encrypt_2 );

    	return cepr01010101Dao.selectLoginUrlSecure(params);
    }
	
	 public void deleteLoginUrlSecure( String id_encrypt_1, String id_encrypt_2) throws DataAccessException
	 {  	
		Map<String,Object> params = new HashMap<String,Object>();
	    params.put( "id_encrypt_1", id_encrypt_1 );
	    params.put( "id_encrypt_2", id_encrypt_2 );

	    cepr01010101Dao.deleteLoginUrlSecure(params);
	 }
	 
	 public void insertAksesHist( String ipHost, String kode_agen )
	 {
		 	Map<String,Object> params = new HashMap<String,Object>();
		 	params.put( "msah_jenis", "1");	    
	    	params.put( "msah_uri", "BRIDGEINDONESIA.COM");
	    	params.put( "remote_addr", ipHost);
	    	params.put( "msah_ip", ipHost);	   
	    	params.put( "jenis_id", "17");
	    	params.put( "user_id", kode_agen);
	        cepr01010101Dao.insertAksesHist( params );
	 }		
	 
    public Map selectAgentBC( Map<String, Object> params )
    {
        return cepr01010101Dao.selectAgentBC( params );
    }    
    
    public Map selectMspeEmail( Map<String, Object> params )
    {
        return cepr01010101Dao.selectMspeEmail( params );
    }
    
    public List<Map<String, Object>> selectNamaAdminBCList( String cariNamaAdminBC, String cariKodeAdminBC, String jn_bank)
    {
    	Map<String,Object> params = new HashMap<String,Object>();
	 	params.put( "cariNamaAdminBC", cariNamaAdminBC);	    
    	params.put( "cariKodeAdminBC", cariKodeAdminBC);
    	params.put( "jn_bank", jn_bank);
        return cepr01030102Dao.selectNamaAdminBCList( params );
    }
    
    public Map selectDetailAdminBC( String lrbId )
    {
        return cepr01030102Dao.selectDetailAdminBC( lrbId );
    }
       
   public BigDecimal selectProvestraRiRj( Map<String, Object> params )
	{
	   return cepr01040201Dao.selectProvestraRiRj( params );
	}
   
   public BigDecimal selectProvestraRgRbPk( Map<String, Object> params )
	{
	   return cepr01040201Dao.selectProvestraRgRbPk( params );
	}
   
   public BigDecimal selectPremiKid( Map<String, Object> params )
  	{
  	   return cepr01040201Dao.selectPremiKid( params );
  	}
   
   public BigDecimal selectNTKid( Map<String, Object> params )
 	{
 	   return cepr01040201Dao.selectNTKid( params );
 	}
   
   public DataProposal selectProposalData( String noProposal ) {
       return cepr00000000CommonDao.selectProposalData(noProposal);
   }
   
   public Integer selectFlagInvestFund(String noProposal ){
	   return cepr00000000CommonDao.selectFlagInvestFund(noProposal);	   
   }
    
   
   public String selectLabelProductName(Integer lsbsId, Integer lsdbsNumber) {
       return cepr00000000CommonDao.selectLabelProductName(lsbsId, lsdbsNumber);
   }
   
   public BigDecimal selectLdecRateToGetCoi_220( Map<String, Object> params )
   {
       return cepr01040201Dao.selectLdecRateToGetCoi_220( params );
   }
   
   public String selectSeqEmailId(){
		return cepr00000000CommonDao.selectSeqEmailId();
	}
   
   public void insertMstEmail(Email email){
	   cepr00000000CommonDao.insertMstEmail(email);		
	}
   
   public List< OptionVO > getPacketTabelList(  Map<String, Object> params)
   {
       return cepr01030102Dao.getPacketTabelList( params );
   }
   
   public BigDecimal selectSMiLeMedicalExtraRider( Map<String, Object> params )
   {
       return cepr01040201Dao.selectSMiLeMedicalExtraRider( params );
   }
 //IGA 25112020 | Penambahan validasi prod syariah tdk bisa ambil VIP
   public Integer selectLineBus(Integer lsbsId ){
	   return cepr00000000CommonDao.selectLineBus(lsbsId);	   
   }
   /**NCR/2021/02/052	SMiLe Income Protection X-Tra**/
   public BigDecimal selectRateForBenefitofReturnPremium( Map<String, Object> params )
   {
       return cepr00000000CommonDao.selectRateForBenefitofReturnPremium( params );  
   }
   
   public Map selectAgentBCVIP()
   {
       return cepr01010101Dao.selectAgentBCVIP();
   }    

}