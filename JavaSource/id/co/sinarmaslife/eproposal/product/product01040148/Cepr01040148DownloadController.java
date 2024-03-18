package id.co.sinarmaslife.eproposal.product.product01040148;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040139DownloadController
 * Description         	: smart medicare (025)
 * Environment      	: Java  1.5.0_06
 * Author               : fadly m
 * Version              : 1.0
 * Creation Date    	: Jan 01, 2012 9:38:52 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import com.lowagie.text.pdf.PdfWriter;

import id.co.sinarmaslife.eproposal.business.Cepr00000000CommonUsedBusiness;
import id.co.sinarmaslife.eproposal.business.Cepr00000000RiderBusiness;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.datasource.ManfaatDataSource;
import id.co.sinarmaslife.eproposal.common.datasource.PremiumSummaryDataSource;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000MoneyUtil;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.eproposal.model.vo.StdTableDetailVO;
import id.co.sinarmaslife.eproposal.product.product01040139.Cepr01040139Business;
import id.co.sinarmaslife.eproposal.product.product01040139.Cepr01040139Mapper;
import id.co.sinarmaslife.eproposal.product.product01040219.Cepr01040219Mapper;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.standard.util.*;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class Cepr01040148DownloadController extends StandardParent implements Controller
{
	static Integer currentIdx;
	
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040139DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        logger.info( "*-*-*-* editorUtil = " + editorUtil );
    
        List <Mst_proposal> tempMstProposal = new ArrayList<Mst_proposal>();
        currentIdx = 0;
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
         //   AbstractJasperReportsView jasperViewer = new JasperReportsPdfView();
            HashMap<String, Object> params = new HashMap<String, Object>();
            HashMap<String, Object> copyparams = new HashMap<String, Object>();
            params.put( "format", "pdf" );

            Map<JRExporterParameter, Object> exporterParam = new HashMap<JRExporterParameter, Object>();
          

        //    jasperViewer.setExporterParameters( exporterParam );
        //    jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040148_product.jasper" );

            
            // Fill all values to detail row
       //     jasperViewer.setReportDataKey( "dataSource" );
       //     jasperViewer.setJdbcDataSource( eproposalManager.getDataSource() );
            
            HashMap<String, Object> paramsSantunan = new HashMap<String, Object>();
            paramsSantunan.put("product_code", "015");
            paramsSantunan.put("plan", cepr01030102Form.getRightPartOfBusinessCd()+8);
       //     List<SantunanMedicare> selectSantunanMedicare = eproposalManager.selectManfaat(paramsSantunan);
       //     params.put( "dataSource", selectSantunanMedicare );
//            copyparams.put( "dataSource", selectSantunanMedicare );
            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
    		
   			
    	//			
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    //        String dateFormat = sdf.format( cepr01030101Form.getTgl_input() );
            String insuredNameForProposalName = cepr01030101Form.getInsuredName().trim().replace(" ", "_");
            String proposalName = "Proposal_smart_medicare_"+insuredNameForProposalName;

            /*
            try{
            exportReportToPdf(
            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040139_product.jasper", 
            		"C:\\mall_ass_pdf\\" +cepr01030101Form.getMsag_id() + "\\" + dateFormat +"\\"+ cepr01030101Form.getKd_tamu() + "\\" , 
            		proposalName+".pdf", 
            		copyparams, 
            		selectSantunanMedicare,
            		PdfWriter.AllowPrinting, 
            		null, 
            		null);
            }catch (Exception e) {
            	e.printStackTrace();
				// TODO: handle exception
			}*/
    	//	}
         
           List<Map<String, Object>> illustrationList;
           List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
           Map<String, Object> map; 
                   
           /*
           int a=0;                  	   
           map = new HashMap<String, Object>();           
           map.put( "premi", "33");           
           map.put( "nt", new BigDecimal(333) );
          // map.put( "nt", nt );
           map.put( "descr", "dhhhhddddddnnn" );
           mapList.add( map ); */
           Cepr01040148Business cepr01040148Business = new Cepr01040148Business( eproposalManager, editorUtil, command );  
                   
          params.put( "title", "VIP HOSPITAL PLAN "  );
          if( cepr01030102Form.getRightPartOfBusinessCd() >= 25 && cepr01030102Form.getRightPartOfBusinessCd() <= 36){
        	  params.put( "title", "SMiLe HOSPITAL PROTECTION (+) (TSR) ");
          }else if( cepr01030102Form.getRightPartOfBusinessCd() >= 37 && cepr01030102Form.getRightPartOfBusinessCd() <= 48){
        	  params.put( "title", "SMiLe HOSPITAL PLUS ");
          }
        
           
           params.put( "nama_tt", cepr01030101Form.getInsuredName() );
           params.put( "usiaTT", cepr01030101Form.getInsuredAge() );
           params.put( "nama_pp", cepr01030101Form.getPolicyHolderName() );
           params.put( "umur_pp", cepr01030101Form.getPolicyHolderAge() );
           params.put( "plan", cepr01040148Business.getHcpProviderPlanList() );
           params.put( "premi", cepr01040148Business.toMoneyWithCurrencyCd(cepr01040148Business.computePremium()) );
           
           BigDecimal premiSetahun = cepr01040148Business.computePremiumSetahun();
           double factor_percentage = 1;
           double premiTriwulanan = premiSetahun.doubleValue() * 0.27;
           double premiSemesteran = premiSetahun.doubleValue() * 0.525;
           double premiBulanan = premiSetahun.doubleValue() * 0.1;
        
           params.put( "premi_thn", cepr01040148Business.toMoneyWithCurrencyCd( premiSetahun ));
           params.put( "premi_bln", cepr01040148Business.toMoneyWithCurrencyCd(new BigDecimal(premiBulanan)) );
           params.put( "premi_smt", cepr01040148Business.toMoneyWithCurrencyCd(new BigDecimal(premiSemesteran)) );
           params.put( "premi_tri", cepr01040148Business.toMoneyWithCurrencyCd(new BigDecimal(premiTriwulanan)) );
           
           mapList.addAll(0, cepr01040148Business.getHcpProviderList());           
           params.put( "dataSource", mapList );
               
           int cara_bayar =  LazyConverter.toInt( cepr01030102Form.getPaymentModeCd() );
        	String label = "Tahunan";
        	if( cara_bayar == Hardcode.PAY_MODE_CD_TAHUNAN ){
        		label = "Tahunan";
        	}else if( cara_bayar == Hardcode.PAY_MODE_CD_BULANAN ){
        		label = "Bulanan";
        	}else if( cara_bayar == Hardcode.PAY_MODE_CD_TRIWULANAN ){
        		label = "Triwulanan";
        	}else if( cara_bayar == Hardcode.PAY_MODE_CD_SEMESTERAN ){
        		label = "Semesteran";
        	}            	 
              
           params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
           params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
           params.put( "agentName", cepr01030101Form.getProposalUser() );
           params.put( "no_proposal", cepr01030102Form.getMstProposal().get(0).getId() );
           params.put( "labelCb", label );
           
            // Save E-Proposal PDF ke EBServer dan PopUp Download
            // ** Adrian@17/10/2013            
            String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
            String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
            Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();
          //  String fileName="testx.pdf";
            String fileName = msag_id+"_"+no_proposal+".pdf";
            params.put( "no_proposal",  no_proposal );
            
           
            params.put( "dsRiderHcpProviderParticipants",  JrUtil.convertToDsAndSetNullIfEmpty( cepr01040148Business.getRiderHcpProviderParticipantsList() ) );
    
            
            String rootDir_EProp="";
            if(flag_test==1){
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP_TEST;
            }else{
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP;
            }
            
            String dirName = rootDir_EProp+"\\"+msag_id+"\\"+no_proposal;  
            
            File sourceFile = new File( dirName + "\\" + fileName );
           try{
      
	        	   //if(cekDataApprovedOrNot.get(0).getPosisi()<3){
			            JasperUtils.exportReportToPdf(
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040148_product.jasper",
			            		dirName,
			                fileName,
							params,
							mapList,
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
            request.getSession().setAttribute( "messageEkaSehatList", null );
         //   result = new ModelAndView( jasperViewer, params );
       
    }
        return result;
    }
    }



