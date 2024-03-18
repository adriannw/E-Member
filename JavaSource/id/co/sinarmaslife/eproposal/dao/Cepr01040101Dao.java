package id.co.sinarmaslife.eproposal.dao;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	:
 * Program Name   		: Cepr01040101Dao
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import java.util.List;

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
public class Cepr01040101Dao extends EproposalDao
{

    public List selectTest() throws DataAccessException
    {
        return query( "selectTest", null );
    }
  
}