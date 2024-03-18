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
import id.co.sinarmaslife.standard.model.vo.ViewListProvider;
import id.co.sinarmaslife.standard.model.vo.ViewListProviderDetail;


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
public class Cepr01030502Dao extends EproposalDao
{
    public List<ViewListProviderDetail> selectAllProviderDetailList( Map<String, Object> params ) throws DataAccessException
    {  	
    	
    		return query( "selectAllProviderDetailList", params );
    	
    }
      
}
