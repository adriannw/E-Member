package id.co.sinarmaslife.eproposal.product.product01040129;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040129DownloadController
 * Description         	: Power Save New (142)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 13, 2007 10:29:28 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import com.lowagie.text.pdf.PdfWriter;

import id.co.sinarmaslife.eproposal.business.Cepr00000000RiderBusiness;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.model.vo.ParticipantVO;
import id.co.sinarmaslife.eproposal.product.product01040111.Cepr01040111Business;
import id.co.sinarmaslife.eproposal.product.product01040207.Cepr01040207Business;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030103Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030106Form;
import id.co.sinarmaslife.standard.util.*;
import net.sf.jasperreports.engine.JRDataSource;
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
import java.util.*;

public class Cepr01040129DownloadController extends StandardParent implements Controller
{
	static Integer currentIdx;
	protected final Log logger = LogFactory.getLog( getClass() );
	
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040129DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01040129Business cepr01040129Business = new Cepr01040129Business( eproposalManager, editorUtil, command );
        
        ModelAndView result = null;
        if( request.getSession().getAttribute( "messageBoxList" ) == null )
        {
            List<String> messageBoxList = new ArrayList<String>();
            messageBoxList.addAll( ListUtil.addNothingIfEmpty( "" ) );

            request.getSession().setAttribute( "messageBoxList", messageBoxList );
            result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        else
        {
          //  AbstractJasperReportsView jasperViewer = new JasperReportsPdfView();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put( "format", "pdf" );

            Map<JRExporterParameter, Object> exporterParam = new HashMap<JRExporterParameter, Object>();
            exporterParam = JasperUtils.exporterParam(exporterParam);

          //  jasperViewer.setExporterParameters( exporterParam );
         //   jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040129_product.jasper" );
            
            String kursLabel="";
            
            if("01".equals(cepr01030102Form.getCurrencyCd())){
            	kursLabel = "Rp ";
            }else if("02".equals(cepr01030102Form.getCurrencyCd())){
            	kursLabel = "US$ ";
            }
            
            Date nowDate = eproposalManager.selectNowDate();

            params.put( "insuredName", cepr01030101Form.getInsuredName().toString() );
            params.put( "insuredAge", cepr01030101Form.getInsuredAge().toString() );
            params.put( "policyHolderName", cepr01030101Form.getPolicyHolderName().toString() );
            params.put( "policyHolderAge", cepr01030101Form.getPolicyHolderAge().toString() );
            
            String[][] plans = {{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "A-100", "B-150", "C-200", "D-250", "E-300", "F-350", "G-400", "H-500", 
            						"I-600", "J-700", "K-800", "L-900", "M-1000", "N-1500", "O-2000", "P-5000","Q-7500" },
            					{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "12.500.000", "18.750.000", "25.000.000", "31.250.000", "37.500.000", "43.750.000", "50.000.000", "100.000.000", 
                					"125.000.000", "150.000.000", "175.000.000", "200.000.000", "225.000.000", "350.000.000", "475.000.000", "475.000.000", "475.000.000" },
                				{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "50.000.000", "75.000.000", "100.000.000", "125.000.000", "150.000.000", "175.000.000", "200.000.000", "400.000.000", 
                    				"500.000.000", "600.000.000", "700.000.000", "800.000.000", "900.000.000", "1.400.000.000", "1.900.000.000", "5.000.000.000", "6.000.000.000" }};
            
            
            
            Integer rightPartOfBusinessCd = cepr01030102Form.getRightPartOfBusinessCd();
            String title = "SMiLe MEDICAL";
            
            if( cepr01030102Form.getRightPartOfBusinessCd() >= Cepr01040129Mapper.X46  && cepr01030102Form.getRightPartOfBusinessCd() <= Cepr01040129Mapper.X60 ){
            	rightPartOfBusinessCd = cepr01030102Form.getRightPartOfBusinessCd() - 30;
          		title = "SMiLe MEDICAL (As CHARGE)";
            }
            else if( cepr01030102Form.getRightPartOfBusinessCd() >= Cepr01040129Mapper.X76 && cepr01030102Form.getRightPartOfBusinessCd() <= Cepr01040129Mapper.X90 ){
            	rightPartOfBusinessCd = cepr01030102Form.getRightPartOfBusinessCd() - 60;
            	title = "SMART MEDICAL CARE";
            } else if( cepr01030102Form.getRightPartOfBusinessCd() >= Cepr01040129Mapper.X91 && cepr01030102Form.getRightPartOfBusinessCd() <= Cepr01040129Mapper.X105 ){
            	rightPartOfBusinessCd = cepr01030102Form.getRightPartOfBusinessCd() - 75;
            	title = "VIP MEDICAL PLAN";
            }else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040129Mapper.X121 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040129Mapper.X122 ){
            	rightPartOfBusinessCd = cepr01030102Form.getRightPartOfBusinessCd() - 90;
            	title = "SMiLe MEDICAL (As CHARGE)";
            }
                       
          	params.put( "title", title );
            params.put( "jenisPlan", plans[0][rightPartOfBusinessCd] );
            params.put( "limitKejadian", plans[1][rightPartOfBusinessCd] );
            params.put( "batasMax", plans[2][rightPartOfBusinessCd] );
            
            if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040129Mapper.X121){   
            	params.put( "biaya1", editorUtil.convertToRoundedTwoDigits(new BigDecimal(5000000) ) );
            	params.put( "biaya2", editorUtil.convertToRoundedTwoDigits(new BigDecimal(4000000)) );
            }else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040129Mapper.X122){   
            	params.put( "biaya1", editorUtil.convertToRoundedTwoDigits(new BigDecimal(7500000)) );
            	params.put( "biaya2", editorUtil.convertToRoundedTwoDigits(new BigDecimal(4000000)) );
            }else{
            	params.put( "biaya1", editorUtil.convertToRoundedTwoDigits(cepr01030102Form.getTotalSumInsuredCd()) );
            	params.put( "biaya2", editorUtil.convertToRoundedTwoDigits(cepr01030102Form.getTotalSumInsuredCd().multiply(new BigDecimal(2))) );
            }
            String caraBayar = "Tahunan";
            if( cepr01030102Form.getPaymentModeCd().equals(1)){  //triwulan
            	caraBayar = "Triwulanan";
    		}else if( cepr01030102Form.getPaymentModeCd().equals(2) ){ //semesteran
    			caraBayar = "Semesteran";
    		}else if( cepr01030102Form.getPaymentModeCd().equals(6) ){ //bulanan
    			caraBayar = "Bulanan";
    		}
            String providerType = "Non Provider";
            if( cepr01030102Form.getProviderTypeCd() != null && cepr01030102Form.getProviderTypeCd().intValue() == Hardcode.PROVIDER_TYPE ){// PROVIDER
            	providerType = "Provider";
            }
            params.put( "providerType", providerType );
            params.put( "caraBayar", caraBayar );
            BigDecimal premiTotalTahunan = new BigDecimal(0);
            BigDecimal premiTotalSemesteran = new BigDecimal(0);
            BigDecimal premiTotalTriwulanan = new BigDecimal(0);
            BigDecimal premiTotalBulanan = new BigDecimal(0);
            premiTotalTahunan = cepr01030102Form.getPremiumCd();
            premiTotalSemesteran = cepr01030102Form.getPremiumCd().multiply(new BigDecimal(0.65));
            premiTotalTriwulanan = cepr01030102Form.getPremiumCd().multiply(new BigDecimal(0.35));
            premiTotalBulanan = cepr01030102Form.getPremiumCd().multiply(new BigDecimal(0.12));
            params.put( "premiTotalTahunan", editorUtil.convertToRoundedTwoDigits(premiTotalTahunan ));
            params.put( "premiTotalSemesteran", editorUtil.convertToRoundedNoDigit(premiTotalSemesteran) );
            params.put( "premiTotalTriwulanan", editorUtil.convertToRoundedNoDigit(premiTotalTriwulanan) );
            params.put( "premiTotalBulanan", editorUtil.convertToRoundedNoDigit(premiTotalBulanan) );
            
            Cepr00000000RiderBusiness riderBusiness = new Cepr00000000RiderBusiness( eproposalManager, editorUtil, command );
            params.put( "dsRiderEkaSehatParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderEkaSehatParticipantsList() ) );
            params.put( "participantEkaSehat", riderBusiness.checkParticapantEkaSehat() );
            
            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            
            currentIdx = 0;
            
         //   jasperViewer.setReportDataKey( "dataSource" );
            
            params.put( "dataSource", cepr01040129Business.getPageList( currentIdx ) );
            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            params.put( "validityMsg", "" );

            String productLabel = "SMiLe MEDICAL (Benefit As Charge)";
            
//          =========================
            String namaAgen = cepr01030101Form.getProposalUser();
            if(namaAgen == null)namaAgen = "";
            String sendType= (String) request.getSession().getAttribute("pdfEvent");//(Integer) session.getAttribute("sendType");
            request.getSession().removeAttribute("pdfEvent");
            if(sendType == null)sendType = "";
            String type="";
    		if("onPressButtonFax".equals(sendType)){
    			type="SENDFAX";
    		}else if("onPressButtonEmail".equals(sendType)){
    			type="SENDEMAIL";
    		}
    		
    		if(!"".equals(sendType)){
    			Properties props = new Properties();
    			props.put("xAction-Type", type);
    			props.put("xAction-To", "");
    			props.put("xAction-Subject", "Simulasi "+ productLabel  +" [Nama Telemarketing : "+ namaAgen +"] ");
    			//product+" [Nama Telemarketing : "+params.get("nama_agen")+"] ");
    			props.put("Content-Disposition", "attachment;filename=proposal.pdf");
    	//		jasperViewer.setHeaders(props);		
    		}
    		
    	    params.put( "dsCommonHeaderRincianRiderEkaSehat", JasperReportsUtils.convertReportData( cepr01040129Business.getCommonHeaderRincianSmallRowList() ) );
            params.put( "dsCommonBiayaRincianRiderEkaSehat", JasperReportsUtils.convertReportData( cepr01040129Business.getCommonBiayaRincianRider( premiTotalTahunan, caraBayar ) ));     
            params.put( "dsRincianRiderEkaSehat", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040129Business.getRincianRiderEkaSehat( rightPartOfBusinessCd ) ));
            params.put( "dsRincianRiderEkaSehatParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRincianRiderEkaSehatParticipantsList() ) ); 
    		
    		//==========================================    		
    		// Save E-Proposal PDF ke EBServer dan PopUp Download
            // ** Adrian@06/01/2014
            String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
            String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
            Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();       
            String fileName = msag_id+"_"+no_proposal+".pdf";
            params.put( "no_proposal",  no_proposal );
            
            List temp = cepr01040129Business.getPageList( currentIdx );
            String rootDir_EProp="";
            if(flag_test==1){
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP_TEST;
            }else{
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP;
            }
            String dirName = rootDir_EProp+"\\"+msag_id+"\\"+no_proposal;    
            
            File sourceFile = new File( dirName + "\\" + fileName );
           try{
			            JasperUtils.exportReportToPdf(
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040129_product.jasper",
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
    		
       //     jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
                      
            request.getSession().setAttribute( "messageBoxList", null );
       //     result = new ModelAndView( jasperViewer, params );
        }

        return result;
    }

}