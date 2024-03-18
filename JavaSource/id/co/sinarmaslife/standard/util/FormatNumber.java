package id.co.sinarmaslife.standard.util;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: FormatNumber
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : Yusuf
 * Version              : 1.0
 * Creation Date    	: Aug 15, 2007 6:28:26 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.Hardcode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class berisi fungsi-fungsi untuk angka, misalnya pembulatan
 *
 * @author Yusuf
 * @since 02/20/2005
 */
public class FormatNumber implements Serializable {

	private static final long serialVersionUID = 8171862015966401783L;

	/**
	 * Fungsi untuk pembulatan per desimal angka (xx angka dibelakang koma) (contoh: <b>round(3.1235, 2) = 3.12</b>)
	 * @param number nilai yang ingin dibulatkan
	 * @param decimalPlace jumlah desimal dibelakang koma
	 * @return Hasil setelah dibulatkan
	 */
	public static double round(double number, int decimalPlace, String currency) {
		BigDecimal bd = new BigDecimal(number);
		if( currency.equals( "02" ) ){
			
		}else{
			bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		}
		return bd.doubleValue();
	}

	public static double round(double number, int decimalPlace) {
		BigDecimal bd = new BigDecimal(number);
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
	
	/**
	 * Fungsi untuk pembulatan per angka (contohnya, 25) berdasarkan satuan yang dimasukkan
	 * (contoh: <b>rounding(1378, true, 25) = 1400</b>)
	 * @param ad_nilai Nilai yang ingin dibulatkan
	 * @param up True apabila dibulatkan keatas, False apabila dibulatkan kebawah
	 * @param satuan pembulatan
	 * @return Hasil setelah dibulatkan
	 * @see Double
	 */
	public static Double rounding(Double ad_nilai, boolean up, double satuan){
		double ad_sisa;

		ad_sisa = ad_nilai.doubleValue() % satuan;

		if(ad_sisa != 0 ){
			if(up) ad_nilai = new Double(ad_nilai.doubleValue() - ad_sisa + satuan);
			else ad_nilai = new Double(ad_nilai.doubleValue() - ad_sisa);
		}

		return ad_nilai;
	}

    public static double truncate(double number, int decimalPlace) {
		BigDecimal bd = new BigDecimal(number);
		bd = bd.setScale(decimalPlace, RoundingMode.DOWN );
		return bd.doubleValue();
	}

//	public static void main(String[] args) {
//		NumberFormat nf = NumberFormat.getNumberInstance();
//		logger.info(nf.format(rounding((double) 34000000, false, 5000000)));
//		logger.info(nf.format(rounding((double) 29000000, false, 5000000)));
//		logger.info(nf.format(rounding((double) 24000000, false, 5000000)));
//		logger.info(nf.format(rounding((double) 31000000, false, 5000000)));
//	}

}
