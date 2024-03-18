package id.co.sinarmaslife.eproposal.product.product01040153;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

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

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.ListUtil;
import id.co.sinarmaslife.standard.util.StringUtil;
import net.sf.jasperreports.engine.JRExporterParameter;

public class Cepr01040153DownloadController extends StandardParent implements Controller
{
	static Integer currentIdx;
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040153DownloadController.handleRequest" );
        
//        AbstractJasperReportsView jasperViewer = new JasperReportsPdfView();
        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Map<String, Object> params = new HashMap<String, Object>();        
        Cepr01040153Business cepr0104053Business = new Cepr01040153Business( eproposalManager, editorUtil, command );        
        List <Mst_proposal> tempMstProposal = new ArrayList<Mst_proposal>();    
        
        ModelAndView result;
        HashMap<String, Object> map = new HashMap<String, Object>();
        
        if( request.getSession().getAttribute( "messageBoxList" ) == null )
        {
        	List<String> messageBoxList = new ArrayList<String>();
            messageBoxList.addAll( ListUtil.addNothingIfEmpty( "" ) );
            request.getSession().setAttribute( "messageBoxList", messageBoxList );
            result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        else
        {
    		Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
            map = controller(cepr01030000HoldingForm);
//        	String ratenull =(StringUtil.isEmpty(map.get("rateLt").toString()))?null:(String)map.get("rateLt").toString();
        	if (map.get("rateLt") == null){
                result = new ModelAndView( "exception_of_rate" );
        	}else{
//                jasperViewer = (AbstractJasperReportsView) map.get("jasperViewer");
                params = (Map<String, Object>) map.get("params");
                String dirName = (StringUtil.isEmpty(map.get("dirName")))?null:(String)map.get("dirName");
                String fileName = (StringUtil.isEmpty(map.get("fileName")))?null:(String)map.get("fileName");
                JasperUtils.downloadReportPDF(response, dirName, fileName);   
//                jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
                request.getSession().setAttribute( "messageBoxList", map.get("messageBoxList") );
                request.getSession().setAttribute( "messageEkaSehatList", map.get("messageEkaSehatList") );
//                result = new ModelAndView( jasperViewer, params );
                result = null; 
        	}      	
        }	
        	
        return result;
    }
    
    
    public HashMap<String, Object> controller(  Cepr01030000HoldingForm cepr01030000HoldingForm )
            throws ServletException, IOException
    {
    	 currentIdx = 0;
    	 HashMap<String, Object> result = new HashMap<String, Object>();
    	 Cepr01040153Business cepr01040153Business = new Cepr01040153Business( eproposalManager, editorUtil, cepr01030000HoldingForm );
         Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
         Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
         logger.info( "*-*-*-* editorUtil = " + editorUtil );
    	
          HashMap<String, Object> params = new HashMap<String, Object>();
          params.put( "format", "pdf" );

          Map<JRExporterParameter, Object> exporterParam = new HashMap<JRExporterParameter, Object>();
          
          HashMap<String, Object> paramsSantunan = new HashMap<String, Object>();
          paramsSantunan.put("product_code", "015");
          paramsSantunan.put("plan", cepr01030102Form.getRightPartOfBusinessCd()+8);
          
  		Double tempRate=0.0;
  		Double tempPremi_tt_1=0.0;
  		
			BigDecimal premi_tt_1=new BigDecimal(0);
			BigDecimal premi_tt_2=new BigDecimal(0);
			
			
			BigDecimal UP= new BigDecimal(0); 
			BigDecimal RATE= new BigDecimal(0);
			BigDecimal temp=new BigDecimal(0);
			BigDecimal premi_bln=new BigDecimal(0);
			BigDecimal premi_tri=new BigDecimal(0);
			BigDecimal premi_smt=new BigDecimal(0);
			BigDecimal premi_thn=new BigDecimal(0);
			BigDecimal premi_bln2=new BigDecimal(0);
			BigDecimal premi_tri2=new BigDecimal(0);
			BigDecimal premi_smt2=new BigDecimal(0);
			BigDecimal premi_thn2=new BigDecimal(0);
			
  		Map<String, Object> paramsPremi = new HashMap<String, Object>();
  		
  		if( cepr01030102Form.getPremium() != null ){
  			tempPremi_tt_1=tempRate=FormatNumber.round(cepr01030102Form.getPremium().doubleValue(), 2);
  		}  		
  		
  		if(tempRate!=null || (tempPremi_tt_1!=null)){
  				/**
  				 * HITUNG PREMI
  				 */
  			UP = cepr01030102Form.getTotalSumInsured();
  			RATE = new BigDecimal(tempRate.toString());
  			temp=RATE.multiply(UP.divide(new BigDecimal("1000")));
  			//bulanan				
  			premi_bln=temp.multiply(new BigDecimal("0.10"));
  			//triwulanan
  			premi_tri=temp.multiply(new BigDecimal("0.27"));
  			//semesteran
  			premi_smt=temp.multiply(new BigDecimal("0.525"));
  			//tahunan
  			premi_thn=temp;
				
  				/**
  				 * Hitung Manfaat
  				 * Ga perlu disini langsung di jasper aja
  				 */			
  				//List MANFAAT= database.selectManfaat("013", 1);
  				
  				
  				/**
  				 * Generate PDF
  				 */			
  				params.put("pcode_simulasi", "015");	
  				params.put("lump_sum",cepr01030102Form.getTotalSumInsured());
  				params.put("bill_freq", cepr01030102Form.getPaymentModeCd().toString());
//  				params.put("nama_agen",cepr01030101Form.get);
  				params.put("nama_pp_1",cepr01030101Form.getPolicyHolderName());
  				params.put("nama_pp", cepr01030101Form.getPolicyHolderName());
  				params.put("nama_tt", cepr01030101Form.getInsuredName());
  				params.put("plan", cepr01030102Form.getRightPartOfBusinessCd()+"");
  				params.put("product_code", "015");
  				params.put("sex_pp", cepr01030101Form.getInsuredSexCd());
  		//		params.put("sex_tt", cepr01030102Form.getInsuredAdditionSexCd());
  				params.put("umur_pp", cepr01030101Form.getPolicyHolderAge());
  				params.put("umur_tt", cepr01030101Form.getInsuredAge() );
  				
  				params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );			
//  				params.put("bod_pp", new SimpleDateFormat("dd/MM/yyyy").format(cepr01030101Form.getInsuredBirthDay()));
  	//			params.put("tgl_proposal",FormatDate.toIndonesian(new SimpleDateFormat("dd/MM/yyyy").format(eproposalManager.selectSysDate())));
  				params.put("premi_bln",premi_bln);
  				params.put("premi_tri",premi_tri);
  				params.put("premi_smt",premi_smt);
  				params.put("premi_thn",premi_thn);
  //				params.put("nama_plan", nama_plan);
  				params.put("fisibel", Boolean.FALSE);
  				  						
  			}
         

      	
          SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
          String insuredNameForProposalName = cepr01030101Form.getInsuredName().trim().replace(" ", "_");
          String proposalName = "Proposal_smart_medicare_"+insuredNameForProposalName;

          params.put( "dsCommonHeaderRincian", JasperReportsUtils.convertReportData( cepr01040153Business.getCommonHeaderRincianRowList()) ); 
          params.put( "dsRincian", JasperReportsUtils.convertReportData( cepr01040153Business.getPageList()) );
                    
          int usiaTT = cepr01030101Form.getInsuredAge();       
         
          params.put( "usiaPP", cepr01030101Form.getPolicyHolderAge());
          params.put( "usiaTT", cepr01030101Form.getInsuredAge());
          params.put( "mpp", cepr01030102Form.getPremiumFurloughYearCd());
          params.put( "masaAsuransi", cepr01030102Form.getTermOfContract()); 	
                      
      	  params.put("nama_tt", cepr01030101Form.getInsuredName());

          List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
         Map<String, Object> map; 
         
                                        
         BigDecimal premi = cepr01030102Form.getPremium();                
         params.put( "premi", premi );              
                  
         int m = 1; //Frekuensi pembayaran premi sesuai CB, default=1=Bulanan
    	 if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TAHUNAN ){
         	m = 12;
         }else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEMESTERAN ){
         	m = 6;
         }else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TRIWULANAN ){
        	m = 3;
         } 
//    	  params.put( "mbpremi",  new BigDecimal( (cepr01030102Form.getPremium().doubleValue()*10)/m) );       
    	 
    	 //factor pengali total premi
     	 int factorPremi = 1;
		 if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_BULANAN ){
			 factorPremi = 12;
         }else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEMESTERAN ){
        	 factorPremi = 2;
         }else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TRIWULANAN ){
        	 factorPremi = 4;
         } 
		 
    	BigDecimal sumPremi = cepr01030102Form.getPremium().multiply(new BigDecimal( factorPremi));	 
    	BigDecimal rateBenefitofReturnPremium = cepr01040153Business.computeRateLT();
    	
         for( int i = 1; i <= cepr01030102Form.getTermOfContract(); i++ )
         {
         	 map = new HashMap<String, Object>();
         	 map.put( "yearNo", editorUtil.convertToString(i ) );
         	 map.put( "age", usiaTT+i );
         	 BigDecimal rate =  cepr01040153Business.computeRate( i );//rate
         	 if (i <= cepr01030102Form.getPremiumFurloughYearCd()){
         		map.put( "premi", cepr01030102Form.getPremium());//premi
         		map.put("sumPremi", sumPremi.multiply(new BigDecimal(i)));//total premi
         		map.put( "nt", new BigDecimal(sumPremi.multiply(new BigDecimal(i)).doubleValue() * rate.doubleValue()) ); 
         	 }else if (i > cepr01030102Form.getPremiumFurloughYearCd()){
         		map.put( "nt", new BigDecimal(sumPremi.multiply(new BigDecimal(cepr01030102Form.getPremiumFurloughYearCd())).doubleValue() * rate.doubleValue()) ); 
         	 }
         	 map.put("mmd", cepr01030102Form.getTotalSumInsured());//manfaat meninggal dunia
         	 map.put("mmdk",new BigDecimal( cepr01030102Form.getTotalSumInsured().doubleValue() * 2));//manfaat meninggal dunia kecelakaan
         	 
         	 if (i > 8 ){
         		 map.put("mmd",new BigDecimal( cepr01030102Form.getTotalSumInsured().doubleValue() * 0.5)); //manfaat meninggal dunia
         		 map.put("mmdk",new BigDecimal( cepr01030102Form.getTotalSumInsured().doubleValue() * 0.5)); //manfaat meninggal dunia kecelakaan
         	 }  	 
         	 	
         	 if(i == 8){
         		 
         		 if (rateBenefitofReturnPremium == null){
             		 params.put("rateLt", null);
             		 map.put( "lt", null);  
             		 result.put("rateLt", null);
         		 }else{
         			 double rateLt = (100 + rateBenefitofReturnPremium.doubleValue());
         			 result.put("rateLt", rateLt);
             		 DecimalFormat df = new DecimalFormat("#");
             		 params.put("rateLt",rateLt);
             		 map.put( "lt", new BigDecimal( sumPremi.multiply(new BigDecimal(cepr01030102Form.getPremiumFurloughYearCd())).doubleValue() * (rateLt/100)));  
         		 }	 
         	 }else{
         		 map.put( "lt", new BigDecimal(0));      
         	 }         	 
         	  mapList.add( map );
         }
         params.put( "dataSource", mapList );
         int cara_bayar =  LazyConverter.toInt( cepr01030102Form.getPaymentModeCd() );
      	String label = "Tahunan";
      	if( cara_bayar == Hardcode.PAY_MODE_CD_TAHUNAN ){
      		label = "Tahunan";
      	}else if( cara_bayar == Hardcode.PAY_MODE_CD_BULANAN ){
      		label = "per bulan**";
      	}else if( cara_bayar == Hardcode.PAY_MODE_CD_TRIWULANAN ){
      		label = "Triwulanan";
      	}else if( cara_bayar == Hardcode.PAY_MODE_CD_SEMESTERAN ){
      		label = "Semesteran";
      	}            	 
         params.put( "dsCommonBiayaRincian", JasperReportsUtils.convertReportData( cepr01040153Business.getBiayaList("", premi , label)) );
         params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
         params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
         params.put( "agentName", cepr01030101Form.getProposalUser() );
         params.put( "no_proposal", cepr01030102Form.getMstProposal().get(0).getId() );
         params.put( "labelCb", label );
         
         String title = "";
         if(cepr01030102Form.getRightPartOfBusinessCd()==1){
        	 title = "SMiLe INCOME PROTECTION X-TRA"; 
           }    
         params.put( "title", title );
         
          // Save E-Proposal PDF ke EBServer dan PopUp Download
          // ** Adrian@17/10/2013            
          String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
          String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
          Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();
        //  String fileName="testx.pdf";
          String fileName = msag_id+"_"+no_proposal+".pdf";
          params.put( "no_proposal",  no_proposal );
          
        
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
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040153_product.jasper",
			            		dirName,
			                fileName,
							params,
							mapList,
							PdfWriter.AllowPrinting,
			                null,
			                null
			            );
	//		            JasperUtils.downloadReportPDF(response, dirName, fileName);        	   
		    	   //}
	    	   }catch( Exception e )
	        	{
	    		   System.out.println(e);
//	    		logger.error( e );
	        	}
        
     //     jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
         result.put("dirName", dirName);
         result.put("fileName", fileName);
         result.put("messageBoxList", null);
         result.put("messageEkaSehatList", null);
    //     result.put("jasperViewer", jasperViewer);
         result.put("params", params);
         
         if (rateBenefitofReturnPremium != null){
        	 double rateLt = (100 + rateBenefitofReturnPremium.doubleValue());
 			 result.put("rateLt", rateLt);
         }else{
        	 result.put("rateLt", null); 
         }
         return result;
    	
    }
        
  }


