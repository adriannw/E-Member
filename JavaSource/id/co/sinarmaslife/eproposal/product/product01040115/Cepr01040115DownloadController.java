package id.co.sinarmaslife.eproposal.product.product01040115;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040112DownloadController
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : Fadly Mathendra
 * Version              : 1.0
 * Creation Date    	: Okt 27, 2008 9:43:18 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import com.lowagie.text.pdf.PdfWriter;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.standard.util.FormatDate;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.jasperreports.AbstractJasperReportsView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040115DownloadController extends StandardParent implements Controller
{
	protected final Log logger = LogFactory.getLog( getClass() );
	
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040115DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01040115Business cepr01040115Business = new Cepr01040115Business( eproposalManager, editorUtil, command );
        
        ModelAndView result;
        
        if( request.getSession().getAttribute( "messageEkaSehatList" ) == null )
        {
        	List<String> messageEkaSehatList = cepr01040115Business.getEkaSehatMsgBox();
        	request.getSession().setAttribute( "messageEkaSehatList", messageEkaSehatList );
        	result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        
        if( request.getSession().getAttribute( "messageBoxList" ) == null )
        {
            List<String> messageBoxList = cepr01040115Business.getMedicalMsgBox();
            request.getSession().setAttribute( "messageBoxList", messageBoxList );
            result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        else
        {
            
            List<Map> descMapList1 = new ArrayList<Map>();
            int totalPersentaseUp = 0;
            Double totalDana = 0.00; 
            BigDecimal percent = new BigDecimal(100);
            int insuredAge = cepr01030101Form.getInsuredAge();
            // EKASISWA EMAS NEW
            int[] akhirTahunAnak = {6, 12, 15, 18, 19, 20, 21, 22, 23};
            // EKASISWA EMAS
            //int[] akhirTahunAnak = {6, 12, 15, 18, 19, 20, 21, 22, 23};
            int[] akhirTahunAnak2 = {};
            int[] persentaseUp = {};            
            if(insuredAge>=1 && insuredAge<=4){
            	int[] akhirTahunAnakTemp = {6, 12, 15, 18, 19, 20, 21, 22, 23};
            	int[] persentaseUpTemp = {10, 15, 20, 100, 25, 25, 25, 25, 25};
            	akhirTahunAnak2 = akhirTahunAnakTemp;
            	persentaseUp = persentaseUpTemp;
            }else if(insuredAge>=5 && insuredAge<=10){
            	int[] akhirTahunAnakTemp = {12, 15, 18, 19, 20, 21, 22, 23};
            	int[] persentaseUpTemp = {0, 15, 20, 100, 25, 25, 25, 25, 25};
            	persentaseUp = persentaseUpTemp;
            	akhirTahunAnak2 = akhirTahunAnakTemp;
            }else if(insuredAge>=11 && insuredAge<=12){
            	int[] akhirTahunAnakTemp = {15, 18, 19, 20, 21, 22, 23};
            	int[] persentaseUpTemp = {0, 0, 20, 100, 25, 25, 25, 25, 25};
            	persentaseUp = persentaseUpTemp;
            	akhirTahunAnak2 = akhirTahunAnakTemp;
            }
            
            List<BigDecimal> tahapanDanaList = new ArrayList<BigDecimal>();
            
            if(insuredAge>=1 && insuredAge<=4){
            	//int[] persentaseUp = {10, 15, 20, 100, 25, 25, 25, 25, 25};
            	for(int i = 0 ; i < 9; i++){
            		if(akhirTahunAnak[i] <= (cepr01030102Form.getTermOfContract() + insuredAge) ){
            			Map<String, Object> map = new HashMap<String, Object>();
            			map.put( "tahunPolis", (akhirTahunAnak[i]-insuredAge) + "");
            			map.put( "usia", akhirTahunAnak[i] + " Tahun");
            			map.put( "persentasePembayaran", persentaseUp[i] + " %");
            			BigDecimal dana = cepr01030102Form.getTotalSumInsured().multiply(new BigDecimal(persentaseUp[i])).divide(percent);
            			map.put( "danaPendidikan", editorUtil.convertToTwoDigit(dana));
            			descMapList1.add(map);
            			tahapanDanaList.add(dana);
            			totalDana = totalDana + new Double(dana.toString());
            			totalPersentaseUp = totalPersentaseUp + persentaseUp[i];
            		}
            	}
            }else if(insuredAge>=5 && insuredAge<=10){
            	//int[] persentaseUp = {0, 15, 20, 100, 25, 25, 25, 25, 25};
            	for(int i = 1 ; i < 9; i++){
            		if(akhirTahunAnak[i] <= (cepr01030102Form.getTermOfContract() + insuredAge)){
            			Map<String, Object> map = new HashMap<String, Object>();
            			map.put( "tahunPolis", (akhirTahunAnak[i]-insuredAge) + "");
            			map.put( "usia", akhirTahunAnak[i] + " Tahun");
            			map.put( "persentasePembayaran", persentaseUp[i] + " %" + "     ");
            			BigDecimal dana = cepr01030102Form.getTotalSumInsured().multiply(new BigDecimal(persentaseUp[i])).divide(percent);
            			map.put( "danaPendidikan", editorUtil.convertToTwoDigit(dana));
            			descMapList1.add(map);
            			tahapanDanaList.add(dana);
            			totalDana = totalDana + new Double(dana.toString());
            			totalPersentaseUp = totalPersentaseUp + persentaseUp[i];
            		}
            	}
            }else if(insuredAge>=11 && insuredAge<=12){
            	//int[] persentaseUp = {0, 0, 20, 100, 25, 25, 25, 25, 25};
            	for(int i = 2 ; i < 9; i++){
            		if(akhirTahunAnak[i] <= (cepr01030102Form.getTermOfContract() + insuredAge)){
            			Map<String, Object> map = new HashMap<String, Object>();
            			map.put( "tahunPolis", (akhirTahunAnak[i]-insuredAge) + "");
            			map.put( "usia", akhirTahunAnak[i] + " Tahun");
            			map.put( "persentasePembayaran", persentaseUp[i] + " %");
            			BigDecimal dana = cepr01030102Form.getTotalSumInsured().multiply(new BigDecimal(persentaseUp[i])).divide(percent);
            			map.put( "danaPendidikan", editorUtil.convertToTwoDigit(dana));
            			descMapList1.add(map);
            			tahapanDanaList.add(dana);
            			totalDana = totalDana + new Double(dana.toString());
            			totalPersentaseUp = totalPersentaseUp + persentaseUp[i];
            		}
            	}
            }
            
            // page 2-----------------------------------------------------------------------------------------------------------------------
            Map<String, Object> sqlParams = new HashMap<String, Object>();
            sqlParams.put( "leftPartOfBusinessCd", new Integer( "172" ) );
            sqlParams.put( "lstabLamaTanggung", cepr01030102Form.getTermOfContract() );
            sqlParams.put( "lstabLamaBayar", cepr01030102Form.getTermOfPayment() );
            sqlParams.put( "lsdbsNumber", cepr01030102Form.getRightPartOfBusinessCd() );
            sqlParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_TUNAI );
            sqlParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
            sqlParams.put( "paymentModeCd", cepr01030102Form.getPaymentModeCd() );
            sqlParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
            sqlParams.put( "insuredAge", cepr01030101Form.getPolicyHolderAge() );
            //sqlParams.put( "yearNoList", new Integer[]{ new Integer( "1" ) } );
            logger.info( "*-*-*-* sqlParams = " + sqlParams );
            List<BigDecimal> rateList = new ArrayList<BigDecimal>(); 
            rateList.addAll(eproposalManager.selectRateNew( sqlParams ));
            
            Double multiplier = 0.00;
            Double multiplierCurrency = 0.00;
            Double investasi = 0.00;
            if("01".equals(cepr01030102Form.getCurrencyCd())){
            	multiplier = new Double((cepr01030102Form.getTotalSumInsured().divide(new BigDecimal(10000000))).toString());
            	multiplierCurrency = 10000.00;
            	investasi = 10.00;// 10/100
            }else if("02".equals(cepr01030102Form.getCurrencyCd())){
            	multiplier = new Double((cepr01030102Form.getTotalSumInsured().divide(new BigDecimal(1000))).toString());
            	multiplierCurrency = 1.00;
            	investasi = 20.00;// 5/100
            }
            
            Double totalTdp = 0.00;
            Double nilaiTunai = 0.00;
            Double totalPremiPokok = 0.00;
            Double totalBenefitTdp = 0.00;
            Double akumulasiTdp = 0.00;
            Double bungaAkumulasiTdp = 0.00;
            Double tahapanDana = 0.00;
            DecimalFormat df2 = new DecimalFormat( "#########0.00" );
            //Double totalBenefitAkumulasiTdp = 0.00;
            
            int usiaAnak = cepr01030101Form.getInsuredAge();
            int rateListLength = rateList.size();
            int nodeAkhirTahunAnak = 0;
            int nodeTahapanDana = 0;
            int nodePlus = 0;
            int nodeTahapanDanaPlus = 0;
            //int nodeBunga = 0;
            int nodeAkumulasiTdp = 0;
            
            List<Map> descMapList2 = new ArrayList<Map>();
            for(int i = 1 ; i <= cepr01030102Form.getTermOfContract() ; i++){
            	Map<String, Object> map = new HashMap<String, Object>();
            	
            	// AKHIR TAHUN
            	map.put("akhirTahunKe", i + "");
            	
            	// USIA ANAK
            	usiaAnak = usiaAnak + 1;
            	map.put("usiaAnak", usiaAnak + " Th.");
            	
            	// PREMI POKOK
            	if(i <= cepr01030102Form.getTermOfPayment()){
            		totalPremiPokok = totalPremiPokok + new Double(df2.format(cepr01030102Form.getPremium()));
            		map.put("premiPokok", editorUtil.convertToTwoDigit(cepr01030102Form.getPremium()) );
            	}else if(i > cepr01030102Form.getTermOfPayment()){
            		map.put("premiPokok", "0.00");
            	}
            	
            	// NILAI TUNAI
            	if( i < cepr01030102Form.getTermOfContract() && i <= rateListLength){
            		nilaiTunai = new Double(rateList.get(i-1).toString()) * multiplier * multiplierCurrency;
            		map.put("nilaiTunai", editorUtil.convertToTwoDigit(nilaiTunai) );
            	}else if( i == cepr01030102Form.getTermOfContract() || i > rateListLength){
            		nilaiTunai = 0.00;
            		map.put("nilaiTunai", nilaiTunai.toString());
            	}
            	
            	// TAHAPAN DANA
            	if( usiaAnak == akhirTahunAnak2[nodeAkhirTahunAnak] ){
            		//if(persentaseUp[nodeAkhirTahunAnak] != 0){
            			tahapanDana = new Double(tahapanDanaList.get(nodeTahapanDana).toString());
                		map.put("tahapanDana", editorUtil.convertToTwoDigit(tahapanDana) );
                		nodeTahapanDanaPlus = 1;
            		//}else if(persentaseUp[nodeAkhirTahunAnak] == 0){
            			//tahapanDana = 0.00;
                		//map.put("tahapanDana", tahapanDana.toString());
                		//nodeTahapanDanaPlus = 0;
            		//}
            		nodePlus = 1;
            	//}else if(i == 1 && i == cepr01030102Form.getTermOfContract() ){
            		//tahapanDana = 0.00;
            		//map.put("tahapanDana", tahapanDana.toString());
            		//nodePlus = 0;
            		//nodeTahapanDanaPlus = 0;
            	}else{
            		tahapanDana = 0.00;
            		map.put("tahapanDana", tahapanDana.toString());
            		nodePlus = 0;
            		nodeTahapanDanaPlus = 0;
            	}
            	
            	// TOTAL BENEFIT TDP
            	if( nodeTahapanDanaPlus == 1){
            		totalTdp = totalTdp + new Double(tahapanDanaList.get(nodeTahapanDana).toString());
            		totalBenefitTdp = nilaiTunai + totalTdp;
            		map.put("totalBenefitTdp", editorUtil.convertToTwoDigit(totalBenefitTdp) );
            	}else if( nodeTahapanDanaPlus == 0){
            		// ada perhitungan akumulasi tdp
            		totalBenefitTdp = nilaiTunai + totalTdp;
            		map.put("totalBenefitTdp", editorUtil.convertToTwoDigit(totalBenefitTdp) );
            	}	
            	
            	// AKUMULASI TDP
            	if(nodeAkumulasiTdp == 0){
            		akumulasiTdp = akumulasiTdp + tahapanDana;
            		nodeAkumulasiTdp = 1;
            		map.put("akumulasiTdp", editorUtil.convertToTwoDigit(akumulasiTdp) );
            	}else if(nodeAkumulasiTdp == 1){
            		bungaAkumulasiTdp = akumulasiTdp / investasi;
            		akumulasiTdp = (akumulasiTdp + tahapanDana) + bungaAkumulasiTdp;
            		map.put("akumulasiTdp", editorUtil.convertToTwoDigit(akumulasiTdp) );
            	}
            	
            	// TOTAL BENEFIT AKUMULASI TDP
            	map.put("totalBenefitAkumulasiTdp", editorUtil.convertToTwoDigit(akumulasiTdp + nilaiTunai));
            	
            	if(nodePlus == 1){
            		nodeAkhirTahunAnak = nodeAkhirTahunAnak + 1;
            	}
            	if(nodeTahapanDanaPlus == 1){
            		nodeTahapanDana = nodeTahapanDana + 1;
            	}
            	
            	descMapList2.add(map);
            }

            /*List<Integer> yearNoSingleList;
            List<BigDecimal> valueList;
            for( Integer year : yearNoList )
            {
                yearNoSingleList = new ArrayList<Integer>();
                yearNoSingleList.add( year );
                rateParams.put( "yearNoList", yearNoSingleList );
                valueList = eproposalManager.selectRate( rateParams );
                if( valueList == null || valueList.size() == 0 )
                {
                    valueList.add( new BigDecimal( "0" ) );
                }
                rateList.addAll( valueList );
            }*/
            
            //------------------------------------------------------------------------------------------------------------
            
            
            AbstractJasperReportsView jasperViewer = new JasperReportsPdfView();
            Map<String, Object> params = new HashMap<String, Object>();
            
            params.put( "format", "pdf" );
            
            Map<JRExporterParameter, Object> exporterParam = new HashMap<JRExporterParameter, Object>();
            exporterParam = JasperUtils.exporterParam(exporterParam);

            jasperViewer.setExporterParameters( exporterParam );
            jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040115_product.jasper" );
            jasperViewer.setReportDataKey( "dataSource" );
            Date tglSkr = eproposalManager.selectNowDate();
            
            String formatTglSkr = FormatDate.toIndonesian(tglSkr);
            
            BigDecimal premiSemesteran = cepr01030102Form.getPremium().multiply(new BigDecimal( Hardcode.FACTOR_SEMESTER ));
            BigDecimal premiTriwulanan = cepr01030102Form.getPremium().multiply(new BigDecimal( Hardcode.FACTOR_TRIWULAN ));
            BigDecimal premiBulanan = cepr01030102Form.getPremium().multiply(new BigDecimal( Hardcode.FACTOR_MONTHLY ));
            
            /*if(cepr01030102Form.getRightPartOfBusinessCd() == 2)
            {
            	params.put( "type", "A");
            }
            else if(cepr01030102Form.getRightPartOfBusinessCd() == 3)
            {
            	params.put( "type", "AB");
            }
            else if(cepr01030102Form.getRightPartOfBusinessCd() == 4)
            {
            	params.put( "type", "ABD");
            }*/
            
            params.put( "namaAnak", cepr01030101Form.getInsuredName() );
            params.put( "usiaAnak", cepr01030101Form.getInsuredAge() + " Tahun" );
            params.put( "namaOrangTua", cepr01030101Form.getPolicyHolderName() );
            params.put( "usiaOrangTua", cepr01030101Form.getPolicyHolderAge() + " Tahun" );
            
            if("01".equals(cepr01030102Form.getCurrencyCd())){
            	params.put( "uangPertanggungan", "Rp. " + editorUtil.convertToTwoDigit(cepr01030102Form.getTotalSumInsured()) );
            }else if("02".equals(cepr01030102Form.getCurrencyCd())){
            	params.put( "uangPertanggungan", "US$ " + editorUtil.convertToTwoDigit(cepr01030102Form.getTotalSumInsured()) );
            }
            
            params.put( "masaPertanggungan", cepr01030102Form.getTermOfContract() + " Tahun");
            params.put( "masaPembayaranPremi", cepr01030102Form.getTermOfPayment() + " Tahun");
            
            params.put( "lastYearNo", editorUtil.convertToString(cepr01030101Form.getInsuredAge() ) );
            params.put( "tglProposal","Jakarta, " +  formatTglSkr);
            params.put( "dataSource", cepr01040115Business.getDetailList() );
            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
            params.put( "validityMsg", "" );
           // params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            
            params.put( "totalPremiPokok", editorUtil.convertToTwoDigit(totalPremiPokok) );
            params.put( "totalDana", editorUtil.convertToTwoDigit(totalDana) );
            params.put( "totalPersentaseUp", totalPersentaseUp + " %" );
            
            if("01".equals(cepr01030102Form.getCurrencyCd())){
            	params.put( "investasi", "10.00 % **" );
            	params.put( "keterangan" , "Bunga tidak mengikat. Ilustrasi pada Akumulasi Tahapan menggunakan tingkat investasi 10.00 % pertahun.");
            }else if("02".equals(cepr01030102Form.getCurrencyCd())){
            	params.put( "investasi", "5.00 % **" );
            	params.put( "keterangan" , "Bunga tidak mengikat. Ilustrasi pada Akumulasi Tahapan menggunakan tingkat investasi 5.00 % pertahun.");
            }
            
            params.put( "premiTahunan", editorUtil.convertToTwoDigit(cepr01030102Form.getPremium()) );
            params.put( "premiSemesteran", editorUtil.convertToRoundedNoDigit(premiSemesteran) + ".00" );
            params.put( "premiTriwulanan", editorUtil.convertToRoundedNoDigit(premiTriwulanan) + ".00");
            params.put( "premiBulanan", editorUtil.convertToRoundedNoDigit(premiBulanan) + ".00");
            
            params.put( "descDs1", JasperReportsUtils.convertReportData( descMapList1 ) );
            params.put( "descDs2", JasperReportsUtils.convertReportData( descMapList2 ) );
            params.put( "umur_tt", cepr01030101Form.getInsuredAge() );
            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( cepr01040115Business.getHeaderRowList( command ) ) );
                        
          //=========================    		
    		// Save E-Proposal PDF ke EBServer dan PopUp Download
            // ** Adrian@06/01/2014            
            String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
            String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
            Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();
          //  String fileName="testx.pdf";
            String fileName = msag_id+"_"+no_proposal+".pdf";
            params.put( "no_proposal",  no_proposal );
            List temp = cepr01040115Business.getDetailList();
            String rootDir_EProp="";
            if(flag_test==1){
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP_TEST;
            }else{
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP;
            }
           String dirName = rootDir_EProp+"\\"+msag_id;    
            
            File sourceFile = new File( dirName + "\\" + fileName );
           try{
      
	        	   //if(cekDataApprovedOrNot.get(0).getPosisi()<3){
			            JasperUtils.exportReportToPdf(
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040115_product.jasper",
			            		dirName,
			                fileName,
							params,
							temp,
							PdfWriter.AllowPrinting,
			                null,
			                null
			            );
			            JasperUtils.downloadReportPDF(response, dirName, fileName);        	   
		    	   //}
	    	   }catch( Exception e )
	        	{
	    		   
	            logger.error("ERROR", e);
	        }   
                        
            jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
            request.getSession().setAttribute( "messageBoxList", null );
            request.getSession().setAttribute( "messageEkaSehatList", null );
            result = new ModelAndView( jasperViewer, params );
        }
        return result;
    }



}