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
import id.co.sinarmaslife.standard.model.vo.ViewListAgent;
import id.co.sinarmaslife.standard.model.vo.ViewListBusiness;
import id.co.sinarmaslife.standard.model.vo.ViewListGroup;
import id.co.sinarmaslife.standard.model.vo.ViewListHardcodedBusiness;
import id.co.sinarmaslife.standard.model.vo.ViewListIdBusiness;
import id.co.sinarmaslife.standard.model.vo.ViewSelectedListBusiness;

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
public class Cepr01020202Dao extends EproposalDao
{
    public List<ViewListBusiness> selectBusinessList(Map<String, Object> params) throws DataAccessException
    {  	
    	return query( "selectBusinessList", params );
    }
    
    public String selectGroupNameLabel(Map<String, Object> params) throws DataAccessException
    {  	
    	return (String) querySingle( "selectGroupNameLabel", params );
    }
    
    public List<ViewListIdBusiness> selectBusinessParentIdList(Map<String, Object> params) throws DataAccessException
    {  	
    	return query( "selectBusinessParentIdList", params );
    }
    
    public List<ViewListHardcodedBusiness> selectBusinessHardcodedParentIdList(Map<String, Object> params) throws DataAccessException
    {  	
    	return query( "selectBusinessHardcodedParentIdList", params );
    }
    
    public List<ViewSelectedListBusiness> selectedBusinessList(Map<String, Object> params) throws DataAccessException
    {  	
    	return query( "selectedBusinessList", params );
    }
    
    public List<ViewSelectedListBusiness> selectedHardcodedBusinessList(Map<String, Object> params) throws DataAccessException
    {  	
    	return query( "selectedHardcodedBusinessList", params );
    }
    
    public List<ViewListBusiness> selectAddBusiness(Map<String, Object> params)
    {
    	return query( "selectAddBusiness", params );
    }
    
    public void addBusiness(Map<String, Object> params)
    {
    	insert( "addBusiness", params );
    }
    
    public void selectedNewChildList(Map<String, Object> params)
    {
    	insert( "selectedNewChildList", params );
    }
    
    public void unselectedChildList(Map<String, Object> params)
    {
    	delete( "unselectedChildList", params );
    }
    
    public void unselectedParentList(Map<String, Object> params)
    {
    	delete( "unselectedParentList", params );
    }
    
    public void unselectedHardcodedParentList(Map<String, Object> params)
    {
    	delete( "unselectedHardcodedParentList", params );
    }
   
}
