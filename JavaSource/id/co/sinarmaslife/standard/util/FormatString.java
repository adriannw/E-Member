package id.co.sinarmaslife.standard.util;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: FormatString
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Dec 3, 2007 2:38:12 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.CharacterIterator;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.StringCharacterIterator;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

/**
 * Class yang digunakan untuk fungsi2 berhubungan dengan string
 *
 * @author Yusuf
 * @since Nov 29, 2005
 */
public class FormatString implements Serializable {

	private static final long serialVersionUID = 7523152599859817721L;
	protected final transient Log logger = LogFactory.getLog( getClass() );
	/**
	 * Fungsi yang mengikuti fungsi RPAD di Oracle, contoh: rpad("0", "YUSUF",
	 * 10) menghasilkan "00000YUSUF"
	 *
	 * @param karakter
	 *            Karakter untuk melengkapi sisa string
	 * @param kata
	 *            String yang mau dipanjangkan
	 * @param panjang
	 *            Panjang dari string hasilnya
	 * @return String hasil penggabungan dari karakter dan kata
	 * see Fungsi RPAD di Oracle
	 */
	public static String rpad(String karakter, String kata, int panjang) {
		if(kata==null) return null;
		StringBuffer result = new StringBuffer();
		if (kata.length() < panjang) {
			for (int i = 0; i < panjang - kata.length(); i++) {
				result.append(karakter);
			}
			result.append(kata);
			return result.toString();
		} else {
			return kata;
		}
	}

	public static String formatCurrency(String currency, BigDecimal amount) {
		if (amount == null){
			return "-";
//		}else if(amount.toString().indexOf(".")==-1){
//			return (currency != null ? currency : "") + new DecimalFormat("#,##0;(#,##0)").format(amount);
		}else{
			return (currency != null ? currency : "") + new DecimalFormat("#,##0.00;(#,##0.00)").format(amount);
		}
	}

	/**
	 * Fungsi untuk mem-format string nomor polis
	 *
	 * @param kata
	 *            string yang akan di format (hanya boleh terdiri atas angka
	 * @return String hasil yang sudah diformat
	 * see Kolom MSPO_POLICY_HOLDER pada table EKA.MST_POLICY
	 */
	public static String nomorPolis(String kata) {
//		Regex reg9 = new Regex("\\d{9,9}");
//		Regex reg11 = new Regex("\\d{11,11}");
//		Regex reg14 = new Regex("\\d{14,14}");

		if(kata==null){
			return kata;
		}else if(kata.length()==9){
			return kata.substring(0,2)+"."+kata.substring(2);
		}else if(kata.length()==11){
			return kata.substring(0,2)+"."+kata.substring(2,6)+"."+kata.substring(6);
		}else if(kata.length()==14){
			return kata.substring(0,2)+"."+kata.substring(2,5)+"."+kata.substring(5,9)+"."+kata.substring(9);
		}else return kata;

	}

	/**
	 * Fungsi untuk mem-format string nomor spaj
	 *
	 * @param kata
	 *            string yang akan di format (hanya boleh terdiri atas angka
	 * @return String hasil yang sudah diformat
	 * see Kolom REG_SPAJ pada table EKA.MST_POLICY
	 */
	public static String nomorSPAJ(String kata) {
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

	/**
	 * Fungsi untuk mem-format string mirip seperti PowerBuilder
	 *
	 *
	 *            string yang akan di format (hanya boleh terdiri atas angka
	 * @return String hasil yang sudah diformat
	 */
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

	/**
	 * Fungsi untuk me-return string kosong berisi spasi (space) sepanjang karakter yang diberikan
	 *
	 * @param panjang
	 *            panjang string yang dikembalikan
	 * @return String kosong berisi spasi
	 */
	public static String spasi(int panjang) {
		StringBuffer result = new StringBuffer();
		for(int i=0; i<panjang; i++)result.append(" ");
		return result.toString();
	}

	public static String sessionangka(
		NumberFormat f, HttpSession session, String angka)
	{

		if (session.getAttribute(angka)!=null){
			if(!session.getAttribute(angka).toString().equals("")){
				return f.format(Double.parseDouble(session.getAttribute(angka).toString()));
			}else return "";
		}else return "";
	}

	/**
	 * Fungsi untuk me-return string yang angka depannya saja di ambil berdasarkan posisi index dari ~X
	 *
	 * @param prod
	 *            Nama produk yang berisi plan dan nomor bisnis (810~X3)
	 * @return String plan atau index ke 0 sampai batas index ~X
	 */
	public static String getPlan(String prod){
		String simbol="~";
		String plan;
		int pos=prod.indexOf(simbol);
		plan=prod.substring(0,pos);

		return plan;
	}

	/**
	 * Fungsi untuk me-return string yang Belakangnya saja di ambil berdasarkan posisi index dari ~X
	 *
	 * @param prod
	 *            Nama produk yang berisi plan dan nomor bisnis (810~X3)
	 * @return String bisnis number atau index ke ~X sampai teraknir
	 */
	public static String getBisnisNumber(String prod){
		String simbol="X";
		String bisnisNumber;
		int pos=prod.indexOf(simbol);
		bisnisNumber=prod.substring(pos+1,prod.length());

		return bisnisNumber;
	}

	/**
	 * Fungsi untuk me-return string yang Belakangnya saja di ambil berdasarkan posisi index dari -
	 *
	 *            Nama Bisnis yang berisi plan dan nomor bisnis (HCP R-100)
	 * @return String bisnis number atau index ke - sampai teraknir
	 */
	public static String getAngkaNamaBisnis(String bisnisName){
		String simbol="-";
		String bisnisNumber;
		int pos=bisnisName.indexOf(simbol);
		bisnisNumber=bisnisName.substring(pos+1,bisnisName.length());

		return bisnisNumber;
	}

	public static String escapeHTML(String url) {
		return StringUtils.replace(StringUtils.replace(StringUtils.replace(StringUtils.replace(url, "/", "%2F"), "?", "%3F"), "=", "%3D"), "&", "%26");
	}

	public static String upper(String string) {
		if(string==null) return null;
		else return string.toUpperCase();
	}

	/**
	   * Escape characters for text appearing in HTML markup.
	   *
	   * <P>This method exists as a defence against Cross Site Scripting (XSS) hacks.
	   * This method escapes all characters recommended by the Open Web App
	   * Security Project -
	   * <a href='http://www.owasp.org/index.php/Cross_Site_Scripting'>link</a>.
	   *
	   * <P>The following characters are replaced with corresponding HTML
	   * character entities :
	   * <table border='1' cellpadding='3' cellspacing='0'>
	   * <tr><th> Character </th><th> Encoding </th></tr>
	   * <tr><td> < </td><td> &lt; </td></tr>
	   * <tr><td> > </td><td> &gt; </td></tr>
	   * <tr><td> & </td><td> &amp; </td></tr>
	   * <tr><td> " </td><td> &quot;</td></tr>
	   * <tr><td> ' </td><td> &#039;</td></tr>
	   * <tr><td> ( </td><td> &#040;</td></tr>
	   * <tr><td> ) </td><td> &#041;</td></tr>
	   * <tr><td> # </td><td> &#035;</td></tr>
	   * <tr><td> % </td><td> &#037;</td></tr>
	   * <tr><td> ; </td><td> &#059;</td></tr>
	   * <tr><td> + </td><td> &#043; </td></tr>
	   * <tr><td> - </td><td> &#045; </td></tr>
	   * </table>
	   *
	   * <P>Note that JSTL's {@code <c:out>} escapes <em>only the first
	   * five</em> of the above characters.
	   */
	   public static String forHTML(String aText){
	     final StringBuilder result = new StringBuilder();
	     final StringCharacterIterator iterator = new StringCharacterIterator(aText);
	     char character =  iterator.current();
	     while (character != CharacterIterator.DONE ){
	       if (character == '<') {
	         result.append("&lt;");
	       }
	       else if (character == '>') {
	         result.append("&gt;");
	       }
	       else if (character == '&') {
	         result.append("&amp;");
	      }
	       else if (character == '\"') {
	         result.append("&quot;");
	       }
	       else if (character == '\'') {
	         result.append("&#039;");
	       }
	       else if (character == '(') {
	         result.append("&#040;");
	       }
	       else if (character == ')') {
	         result.append("&#041;");
	       }
	       else if (character == '#') {
	         result.append("&#035;");
	       }
	       else if (character == '%') {
	         result.append("&#037;");
	       }
	       else if (character == ';') {
	         result.append("&#059;");
	       }
	       else if (character == '+') {
	         result.append("&#043;");
	       }
	       else if (character == '-') {
	         result.append("&#045;");
	       }
	       else {
	         //the char is not a special one
	         //add it to the result as is
	         result.append(character);
	       }
	       character = iterator.next();
	     }
	     return result.toString();
	  }

}
