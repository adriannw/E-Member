package id.co.sinarmaslife.standard.util.jasper;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: JasperScriptlet
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Dec 3, 2007 2:37:07 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import com.ibatis.common.resources.Resources;
import id.co.sinarmaslife.standard.util.AngkaTerbilang;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.FormatString;
import id.co.sinarmaslife.standard.util.LazyConverter;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.fill.JRFillParameter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * Class ini berfungsi untuk disertakan dalam jasperreports sebagai scriptlet utama,
 * dimana didalamnya sudah dimasukkan fungsi2 kecil yang berguna untuk reporting
 * apabila ingin menambahkan fungsi yang spesifik untuk sebuah report tertentu, lebih baik
 * class ini di-extend saja sebagai parent.
 *
 * @author Yusuf
 * @since 20051208
 */
public class JasperScriptlet extends JRDefaultScriptlet {

	protected final Log logger = LogFactory.getLog(getClass());

	public void beforeReportInit() throws JRScriptletException {
		if(logger.isDebugEnabled()) {
			JRFillParameter fillParameter = (JRFillParameter) parametersMap.get("REPORT_PARAMETERS_MAP");
			Map parameterMap = (Map) fillParameter.getValue();
			logger.debug(parameterMap.get("reportPath"));
		}
	}

	public String rpad(String karakter, String kata, int panjang) {
		return FormatString.rpad(karakter, kata, panjang);
	}

	public String formatTerbilang(BigDecimal amount, String lku){
		return AngkaTerbilang.indonesian(amount.toString(), lku);
	}

	public String formatDateIndonesian(Object tgl) throws JRScriptletException {
		return FormatDate.toIndonesian((Date) tgl);
	}

	public String formatDateIndonesian2(String tgl) throws JRScriptletException {
		return FormatDate.toIndonesian(tgl);
	}

	public String formatDateIndonesian1(String tgl) throws JRScriptletException {
		DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
		Date tanggal=null;
		try {
			tanggal = df1.parse(tgl);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error("ERROR", e);
		}
		return FormatDate.toIndonesian((Date) tanggal);
	}

	public String formatMonth(String temp) throws JRScriptletException{
		temp = FormatString.rpad("0", temp, 2);
		return
			(temp.equals("01") ? " Januari " : temp
					.equals("02") ? " Februari " : temp
					.equals("03") ? " Maret " : temp
					.equals("04") ? " April " : temp
					.equals("05") ? " Mei " : temp
					.equals("06") ? " Juni " : temp
					.equals("07") ? " Juli " : temp
					.equals("08") ? " Agustus " : temp
					.equals("09") ? " September " : temp
					.equals("10") ? " Oktober " : temp
					.equals("11") ? " November " : temp
					.equals("12") ? " Desember " : "");
	}

	public String formatCurrency(String currency, BigDecimal amount) {
		if (amount == null){
			return "-";
//		}else if(amount.toString().indexOf(".")==-1){
//			return (currency != null ? currency : "") + new DecimalFormat("#,##0;(#,##0)").format(amount);
		}else{
			//return (currency != null ? currency : "") + new DecimalFormat("#,##0.00;(#,##0.00)").format(amount);
			return (currency != null ? currency : "") + new DecimalFormat("#,##0;(#,##0)").format(amount);
		}
	}

	public String format4Digit(BigDecimal amount) {
		if (amount == null){
			return "";
		}else{
			return new DecimalFormat("#,##0.0000;(#,##0.0000)").format(amount);
		}
	}

	public String format3Digit(BigDecimal amount) {
		if (amount == null){
			return "";
		}else{
			return new DecimalFormat("#,##0.000;(#,##0.000)").format(amount);
		}
	}

	public String format2Digit(BigDecimal amount) {
		if (amount == null){
			return "";
		}else{
			return new DecimalFormat("#,##0.00;(#,##0.00)").format(amount);
		}
	}
	
	public String format0Digit(BigDecimal amount) {
		if (amount == null){
			return "";
		}else{
			return new DecimalFormat("#,##0;(#,##0)").format(amount);
		}
	}

	public String formatNumber(BigDecimal amount) {
		if (amount == null)
			return "";
		else if(amount.toString().indexOf(".")==-1)
			return new DecimalFormat("#,##0;(#,##0)").format(amount);
		else
			return new DecimalFormat("#,##0.00;(#,##0.00)").format(amount);
	}

	public String formatDateWithTime(Object tanggal) {
		if (tanggal == null) return "";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		return df.format(tanggal);
	}

	public String formatDateNumber(Object tanggal) {
		if (tanggal == null) return "";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(tanggal);
	}

	public String formatDateStripes(Object tanggal) {
		if (tanggal == null) return "";
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return df.format(tanggal);
	}

	public String formatDateString(Object tanggal) {
		if (tanggal == null) return "";
		SimpleDateFormat df = new SimpleDateFormat("d MMM yyyy");
		return df.format(tanggal);
	}

	public String formatinvoice(String kata) {
		if (kata == null) {
			return "";
		} else{
			return kata.substring(0, 3) + "/" + kata.substring(3,7)+ "/" + kata.substring(7,9);
		}
	}

	public String formatbilling(String kata) {
		if (kata == null) {
			return "";
		} else{
			return kata.substring(0, 2) + "." + kata.substring(2,kata.length());
		}
	}

	public Date convertstringdate(String kata){
		Date a=null;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			a= df.parse(kata.substring(6, 8)+"/"+kata.substring(4, 6)+"/"+kata.substring(0, 4));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error("ERROR", e);
		}
		return a;
	}

	public String formatPolis(String kata) {
		if (kata == null) {
			return "";
		} else if (kata.length() == 9) {
			return kata.substring(0, 2) + "." + kata.substring(2);
		} else if (kata.length() == 11) {
			return kata.substring(0, 2) + "." + kata.substring(2, 6) + "."
					+ kata.substring(6);
		} else if (kata.length() == 14) {
			return kata.substring(0, 2) + "." + kata.substring(2, 5) + "."
					+ kata.substring(5, 9) + "." + kata.substring(9);
		} else
			return "";
	}

	public String formatSPAJ(String kata) {
		if (kata == null) {
			return "";
		} else if (kata.length() == 7) {
			return kata;
		} else if (kata.length() == 9) {
			return kata.substring(0, 4) + "." + kata.substring(4);
		} else if (kata.length() == 11) {
			return kata.substring(0, 2) + "." + kata.substring(2, 6) + "."
					+ kata.substring(6);
		} else
			return "";
	}

	public String formatTTS(String kata) {
		if (kata == null) {
			return "";
		} else{
			return  kata.substring(0,3)+"/"+kata.substring(3,9)+"/"+kata.substring(9,kata.length());
		}
	}

	public String formatMask(Object kt, String format) { //contoh: formatMask("12345678901", "@@.@@@@.@.@@@@");
		if(kt==null || format==null) return "";
		String kata;
		if(!(kt instanceof String)) kata = kt.toString();
		else kata = (String) kt;

		String[] temp = format.split("\\.");
		if(temp.length==1) return kata;
		StringBuffer result = new StringBuffer();

		try{
			for(int i=0; i<temp.length; i++){
				result.append( (i!=0?".":"") + (kata.length()<temp[i].length()?kata:kata.substring(0, temp[i].length())) );
				kata = kata.substring(temp[i].length());
			}
		}catch(Exception e){
			logger.error( e );
		}

		return result.toString();
	}

	public String getProperty(String name) throws IOException,
			FileNotFoundException, JRScriptletException {
		Properties props = (Properties) this.getParameterValue("props");

//		logger.info("nilai = " + props.getProperty(name));

		if (props == null) {
			props = new Properties();
			FileInputStream in = new FileInputStream(Resources.getResourceAsFile("ekalife.properties"));
			//FileInputStream in = new FileInputStream("D:\\WorkspaceMyEclipse5\\E-Lions-server\\JavaSource\\ekalife.properties");
			props.load(in);
		}
		return props.getProperty(name);
	}

	public String formatPadding(String karakter, String kata, int panjang) {
		return FormatString.rpad(karakter, kata, panjang);
	}

	public String formatNullValue(String value) {
		if(value==null)
			return "-";
		else return value;
	}
	
	public double toDouble( String value ){
		double result = 0.0;
		if( value != null && value != "" ){
			value = value.replace(",", "");
			result = LazyConverter.toDouble( new BigDecimal( value ) );
		}
		return result;
	}

    public String convertToRoundedNoDigit( double amount )
    {
        String result;
        BigDecimal temp = new BigDecimal( amount );
        temp = temp.setScale( 0, RoundingMode.HALF_UP );
        DecimalFormat decimalFormat = new DecimalFormat( "###,###,###,###,##0" );
        result = decimalFormat.format( temp );

        return result;
    }
    
	public String formatUpperCase(String value) {
		value = value.toUpperCase();
		return value;
	}

}

