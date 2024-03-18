package id.co.sinarmaslife.eproposal.dao;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	:
 * Program Name   		: Cmed01010101Dao
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : Andy
 * Version              : 1.0
 * Creation Date    	: May 6, 2008 8:52:05 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/


import id.co.sinarmaslife.eproposal.common.parent.EproposalDao;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.model.vo.TotalPage;
import id.co.sinarmaslife.standard.model.vo.UserGroup;
import id.co.sinarmaslife.standard.model.vo.UserGroupId;
import id.co.sinarmaslife.standard.model.vo.ViewListAgent;
import id.co.sinarmaslife.standard.model.vo.ViewListGroup;

import id.co.sinarmaslife.standard.model.vo.ViewListProvider;



import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

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
public class Cepr01020201Dao extends EproposalDao
{
    public List<ViewListGroup> selectGroupList(Map<String, Object> params) throws DataAccessException
    {  	
    		return query( "selectGroupList", params );
    }
    
    public List<TotalPage> selectTotalGroupPage( Map<String, Object> params ) throws DataAccessException
    {  	
    		return query( "selectTotalGroupPage", params );
    }
    
    public List<ViewListGroup> selectAllGroup(Map<String, Object> params) throws DataAccessException
    {  	
    		return query( "selectAllGroup", params );
    }
    
    public void insertNewGroup(Map<String, Object> params) throws DataAccessException
    {  	
    	    insert( "insertNewGroup", params );
    }
    
    public List<UserGroupId> selectAllUserGroup() throws DataAccessException
    {  	
    		return query( "selectAllUserGroup", null );
    }
    
    public void deleteSelectedGroup(Map<String, Object> params) throws DataAccessException
    {  	
    		delete( "deleteSelectedGroup", params );
    }
    
    public void deleteSelectedGroupDetail(Map<String, Object> params) throws DataAccessException
    {  	
    		delete( "deleteSelectedGroupDetail", params );
    }
    
}
