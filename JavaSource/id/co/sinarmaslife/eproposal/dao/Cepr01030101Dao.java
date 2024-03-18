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
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.std.util.DateUtil;

import org.springframework.dao.DataAccessException;

import java.util.Calendar;
import java.util.Date;
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
public class Cepr01030101Dao extends EproposalDao {
	
    public List<OptionVO> selectLstBusinessOptionVOList( Map<String, List<String>> params ) throws DataAccessException {
        return query( "selectLstBusinessOptionVOList", params );
    }
   
    public List<String> selectLstBusinessFilteredOptionVOList( Map<String, Object> params ) throws DataAccessException {
        return query( "selectLstBusinessFilteredOptionVOList", params );
    }

    public List<String> selectLsbsIdByMsagIdList( String msagId ) throws DataAccessException {
        return query( "selectLsbsIdByMsagIdList", msagId );
    }

    /**Fungsi: 	Untuk mendapatkan hari terakhir dari bulan dengan NTP Server Date (dd/MM/yyyy)
	 * @param	int (+/- day)
	 * @return	Date
	 * @author	Andy
	 */
	public String selectLastDay() {
		return String.valueOf(DateUtil.selectLastDay());
	}
}