package id.co.sinarmaslife.eproposal.product.product01040144;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040144DownloadController
 * Description         	: Power Save New (142)
 * Environment      	: Java  1.5.0_06
 * Author               : fadly mathendra
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

public class Cepr01040144DownloadController extends StandardParent implements Controller
{
	static Integer currentIdx;
	protected final Log logger = LogFactory.getLog( getClass() );
	
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040144DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01040144Business cepr01040144Business = new Cepr01040144Business( eproposalManager, editorUtil, command );
        
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
        //    AbstractJasperReportsView jasperViewer = new JasperReportsPdfView();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put( "format", "pdf" );

            Map<JRExporterParameter, Object> exporterParam = new HashMap<JRExporterParameter, Object>();
            exporterParam = JasperUtils.exporterParam(exporterParam);

        //    jasperViewer.setExporterParameters( exporterParam );
        //    jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040144_product.jasper" );
            
            String kursLabel="";
            
            if("01".equals(cepr01030102Form.getCurrencyCd())){
            	kursLabel = "Rp ";
            }else if("02".equals(cepr01030102Form.getCurrencyCd())){
            	kursLabel = "US$ ";
            }
            
            Date nowDate = eproposalManager.selectNowDate();
            String providerType = "Non Provider";
            if( cepr01030102Form.getProviderTypeCd() != null && cepr01030102Form.getProviderTypeCd().intValue() == Hardcode.PROVIDER_TYPE ){// PROVIDER
            	providerType = "Provider";
            }
            params.put( "providerType", providerType );
            
            params.put( "insuredName", cepr01030101Form.getInsuredName().toString() );
            params.put( "insuredAge", cepr01030101Form.getInsuredAge().toString() );
            params.put( "policyHolderName", cepr01030101Form.getPolicyHolderName().toString() );
            params.put( "policyHolderAge", cepr01030101Form.getPolicyHolderAge().toString() );
            
            String[][] plans = {{"",  "PLAN A", "PLAN B", "PLAN C", "PLAN D", "PLAN E", "PLAN F", "PLAN G", "PLAN H", 
            						"PLAN I", "PLAN J", "PLAN K", "PLAN L", "PLAN M", "PLAN N", "PLAN O" },
            					{"",  "12.500.000", "18.750.000", "25.000.000", "31.250.000", "37.500.000", "43.750.000", "50.000.000", "100.000.000", 
                					"125.000.000", "150.000.000", "175.000.000", "200.000.000", "225.000.000", "350.000.000", "475.000.000" },
                				{"",  "50.000.000", "75.000.000", "100.000.000", "125.000.000", "150.000.000", "175.000.000", "200.000.000", "250.000.000", 
                    					"300.000.000", "350.000.000", "400.000.000", "450.000.000", "500.000.000", "750.000.000", "1.000.000.000" }};
            
            params.put( "jenisPlan", plans[0][cepr01030102Form.getRightPartOfBusinessCd()] );
            params.put( "limitKejadian", plans[1][cepr01030102Form.getRightPartOfBusinessCd()] );
            params.put( "batasMax", plans[2][cepr01030102Form.getRightPartOfBusinessCd()] );
            
          double[] plan_a = {100000  , 200000  , 1750000  , 12000000  , 50000   , 300000  , 50000  , 40000  , 350000 };
          double[] plan_b = {150000  , 300000  , 2250000  , 17250000  , 60000   , 360000  , 60000  , 45000  , 500000 };
          double[] plan_c = {200000  , 400000  , 2750000  , 22500000  , 70000   , 420000  , 70000  , 50000  , 650000 };
          double[] plan_d = {250000  , 500000  , 3250000  , 27750000  , 80000   , 480000  , 80000  , 55000  , 800000 };
          double[] plan_e = {300000  , 600000  , 3750000  , 33000000  , 90000   , 540000  , 90000  , 60000  , 950000 };
          double[] plan_f = {350000  , 700000  , 4250000  , 38250000  , 100000  , 600000  , 100000 , 65000  , 1100000};
          double[] plan_g = {400000  , 800000  , 4750000  , 43500000  , 110000  , 660000  , 110000 , 70000  , 1250000 };
          double[] plan_h = {500000  , 1000000 , 5750000  , 54000000  , 130000  , 780000  , 130000 , 80000  , 1550000 };
          double[] plan_i = {600000  , 1200000 , 6750000  , 64500000  , 150000  , 900000  , 150000 , 90000  , 1850000 };
          double[] plan_j = {700000  , 1400000 , 7750000  , 75000000  , 170000  , 1020000 , 170000 , 100000 , 2150000 };
          double[] plan_k = {800000  , 1600000 , 8750000  , 85500000  , 190000  , 1140000 , 190000 , 110000 , 2450000 };
          double[] plan_l = {900000  , 1800000 , 9750000  , 96000000  , 210000  , 1260000 , 210000 , 120000 , 2750000 };
          double[] plan_m = {1000000 , 2000000 , 10750000 , 106500000 , 230000  , 1380000 , 230000 , 130000 , 3050000 };
          double[] plan_n = {1500000 , 3000000 , 15750000 , 159000000 , 330000  , 1980000 , 330000 , 180000 , 4550000 };
          double[] plan_o = {2000000 , 4000000 , 20750000 , 211500000 , 430000  , 2580000 , 430000 , 230000 , 6050000 };
          
          double[] planChoosed = {};
          if( cepr01030102Form.getRightPartOfBusinessCd() == 1 ) 		planChoosed = plan_a;
          else if( cepr01030102Form.getRightPartOfBusinessCd() == 2 ) 	planChoosed = plan_b;
          else if( cepr01030102Form.getRightPartOfBusinessCd() == 3 ) 	planChoosed = plan_c;
          else if( cepr01030102Form.getRightPartOfBusinessCd() == 4 ) 	planChoosed = plan_d;
          else if( cepr01030102Form.getRightPartOfBusinessCd() == 5 ) 	planChoosed = plan_e;
          else if( cepr01030102Form.getRightPartOfBusinessCd() == 6 ) 	planChoosed = plan_f;
          else if( cepr01030102Form.getRightPartOfBusinessCd() == 7 ) 	planChoosed = plan_g;
          else if( cepr01030102Form.getRightPartOfBusinessCd() == 8 ) 	planChoosed = plan_h;
          else if( cepr01030102Form.getRightPartOfBusinessCd() == 9 ) 	planChoosed = plan_i;
          else if( cepr01030102Form.getRightPartOfBusinessCd() == 10 ) 	planChoosed = plan_j;
          else if( cepr01030102Form.getRightPartOfBusinessCd() == 11 ) 	planChoosed = plan_k;
          else if( cepr01030102Form.getRightPartOfBusinessCd() == 12 ) 	planChoosed = plan_l;
          else if( cepr01030102Form.getRightPartOfBusinessCd() == 13 ) 	planChoosed = plan_m;
          else if( cepr01030102Form.getRightPartOfBusinessCd() == 14 ) 	planChoosed = plan_n;
          else if( cepr01030102Form.getRightPartOfBusinessCd() == 15 ) 	planChoosed = plan_o;
                
            params.put( "biaya1" , editorUtil.convertToRoundedTwoDigits( planChoosed[0] ) );
            params.put( "biaya2" , editorUtil.convertToRoundedTwoDigits( planChoosed[1] ) );
            params.put( "biaya3" , editorUtil.convertToRoundedTwoDigits( planChoosed[2] ) );
            params.put( "biaya4" , editorUtil.convertToRoundedTwoDigits( planChoosed[3] ) );
            params.put( "biaya5" , editorUtil.convertToRoundedTwoDigits( planChoosed[4] ) );
            params.put( "biaya6" , editorUtil.convertToRoundedTwoDigits( planChoosed[5] ) );
            params.put( "biaya7" , editorUtil.convertToRoundedTwoDigits( planChoosed[6] ) );
            params.put( "biaya8" , editorUtil.convertToRoundedTwoDigits( planChoosed[7] ) );
            params.put( "biaya9" , editorUtil.convertToRoundedTwoDigits( planChoosed[8] ) );
           
            
            String caraBayar = "Tahunan";
            if( cepr01030102Form.getPaymentModeCd().equals(1)){  //triwulan
            	caraBayar = "Triwulanan";
    		}else if( cepr01030102Form.getPaymentModeCd().equals(2) ){ //semesteran
    			caraBayar = "Semesteran";
    		}else if( cepr01030102Form.getPaymentModeCd().equals(6) ){ //bulanan
    			caraBayar = "Bulanan";
    		}
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
            
      //      jasperViewer.setReportDataKey( "dataSource" );
            
            params.put( "dataSource", cepr01040144Business.getPageList( currentIdx ) );
            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.syariah" ) );
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            params.put( "validityMsg", "" );
            params.put( "logoBismilah", eproposalManager.getProps().getProperty( "logo.bismilah" ) );

            // Save E-Proposal PDF ke EBServer dan PopUp Download
            // ** Adrian@09/01/2014
            String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
            String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
            Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();
        
            String fileName = msag_id+"_"+no_proposal+".pdf";
            params.put( "no_proposal",  no_proposal );
            
            List temp = cepr01040144Business.getPageList( currentIdx );
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
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040144_product.jasper",
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
