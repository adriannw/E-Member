package id.co.sinarmaslife.standard.util;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: DateUtil
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 14, 2007 11:25:39 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
    protected final Log logger = LogFactory.getLog( getClass() );
    private static final int MILLISECS_PER_DAY = 1000 * 60 * 60 * 24;
    
    public DateUtil()
    {
        logger.info( "*-*-*-* DateUtil constructor is called ..." );
    }

    // a copy from http://forum.java.sun.com/thread.jspa?forumID=31&threadID=395202
    public static int calculateDifference( Date a, Date b )
    {
        int tempDifference;
        int difference = 0;
        Calendar earlier = Calendar.getInstance();
        Calendar later = Calendar.getInstance();

        if( a.compareTo( b ) < 0 )
        {
            earlier.setTime( a );
            later.setTime( b );
        }
        else
        {
            earlier.setTime( b );
            later.setTime( a );
        }

        while( earlier.get( Calendar.YEAR ) != later.get( Calendar.YEAR ) )
        {
            tempDifference = 365 * ( later.get( Calendar.YEAR ) - earlier.get( Calendar.YEAR ) );
            difference += tempDifference;
            earlier.add( Calendar.DAY_OF_YEAR, tempDifference );
        }

        if( earlier.get( Calendar.DAY_OF_YEAR ) != later.get( Calendar.DAY_OF_YEAR ) )
        {
            tempDifference = later.get( Calendar.DAY_OF_YEAR ) - earlier.get( Calendar.DAY_OF_YEAR );
            difference += tempDifference;
            earlier.add( Calendar.DAY_OF_YEAR, tempDifference );
        }
        return difference;
    }

    public static int calculateDifference( Calendar a, Calendar b )
    {
        int tempDifference;
        int difference = 0;

        Calendar earlier;
        Calendar later;

        if( a.compareTo( b ) < 0 )
        {
            earlier = a;
            later = b;
        }
        else
        {
            earlier = b;
            later = a;
        }

        while( earlier.get( Calendar.YEAR ) != later.get( Calendar.YEAR ) )
        {
            tempDifference = 365 * ( later.get( Calendar.YEAR ) - earlier.get( Calendar.YEAR ) );
            difference += tempDifference;
            earlier.add( Calendar.DAY_OF_YEAR, tempDifference );
        }

        if( earlier.get( Calendar.DAY_OF_YEAR ) != later.get( Calendar.DAY_OF_YEAR ) )
        {
            tempDifference = later.get( Calendar.DAY_OF_YEAR ) - earlier.get( Calendar.DAY_OF_YEAR );
            difference += tempDifference;
            earlier.add( Calendar.DAY_OF_YEAR, tempDifference );
        }
        return difference;
    }

    public static int umur(int tahun1, int bulan1, int tanggal1,int tahun2, int bulan2, int tanggal2) 
	{
		int li_month = 0;
		int li_Umur = 0;
		int li_add = 0;
		int li_curr_month = 0;
		int li_curr_year = 0;
		int li_curr_day = 0;
		//Calendar tgl_sekarang = Calendar.getInstance(); 
		/*li_curr_day   = tgl_sekarang.get(Calendar.DAY_OF_MONTH);
		li_curr_month = tgl_sekarang.get(Calendar.MONTH)+1;
		li_curr_year  = tgl_sekarang.get(Calendar.YEAR);*/
		li_curr_day =tanggal2;
		li_curr_month=bulan2;
		li_curr_year=tahun2;

		if (tahun1 != li_curr_year)
		{
			if (li_curr_month >= bulan1)
			{
				li_Umur = li_curr_year - tahun1;
			}else{
				li_Umur = (li_curr_year - tahun1) - 1;
				li_add = 12;
			}
			li_month = li_curr_month + li_add - bulan1;
			if (li_month >= 6)
			{
				if (li_month==6)
				{
					if ((li_curr_day  - tanggal1) >= 0)
					{
						li_Umur = li_Umur+1;
					}
				}else{
					li_Umur = li_Umur+1;
				}
				
			}
		}
		if (li_Umur<0){
			li_Umur=0;
		}
		return li_Umur;
	}
    
    /**
	 * Fungsi untuk menghitung selisih antara 2 tanggal (dalam bentuk angka
	 * absolut / tidak ada negatif)
	 * 
	 * @param before
	 *            Tanggal awal
	 * @param after
	 *            Tanggal akhir
	 * @param flagAbsolut
	 *            True: return value selalu positif, False: tergantung selisih antara after-before
	 * @return long yang berisi selisihnya
	 * @see Date, Calendar
	 */
	public static long dateDifference(Date before, Date after, boolean flagAbsolut) {
		Calendar calAfter = Calendar.getInstance();
		Calendar calBefore = Calendar.getInstance();
		calAfter.setTime(after);
		calBefore.setTime(before);
		long endL = calAfter.getTimeInMillis()
				+ calAfter.getTimeZone().getOffset(calAfter.getTimeInMillis());
		long startL = calBefore.getTimeInMillis()
				+ calBefore.getTimeZone()
						.getOffset(calBefore.getTimeInMillis());
		if(flagAbsolut)
			return Math.abs((endL - startL) / MILLISECS_PER_DAY);
		else 
			return (endL - startL) / MILLISECS_PER_DAY;
	}
	
    
    
}
