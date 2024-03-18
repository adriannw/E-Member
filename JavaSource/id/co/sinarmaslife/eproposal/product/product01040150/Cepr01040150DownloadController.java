package id.co.sinarmaslife.eproposal.product.product01040150;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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

public class Cepr01040150DownloadController extends StandardParent implements Controller
{
	static Integer currentIdx;
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040150DownloadController.handleRequest" );
        
//        AbstractJasperReportsView jasperViewer = new JasperReportsPdfView();
        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Map<String, Object> params = new HashMap<String, Object>();        
        Cepr01040150Business cepr0104050Business = new Cepr01040150Business( eproposalManager, editorUtil, command );        
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
//            jasperViewer = (AbstractJasperReportsView) map.get("jasperViewer");
            params = (Map<String, Object>) map.get("params");
            String dirName = (StringUtil.isEmpty(map.get("dirName")))?null:(String)map.get("dirName");
            String fileName = (StringUtil.isEmpty(map.get("fileName")))?null:(String)map.get("fileName");
            JasperUtils.downloadReportPDF(response, dirName, fileName);   
//            jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
            request.getSession().setAttribute( "messageBoxList", map.get("messageBoxList") );
            request.getSession().setAttribute( "messageEkaSehatList", map.get("messageEkaSehatList") );
//            result = new ModelAndView( jasperViewer, params );
            result = null; 
        }	
        	
        return result;
    }
    
    
    public HashMap<String, Object> controller(  Cepr01030000HoldingForm cepr01030000HoldingForm )
            throws ServletException, IOException
    {
    	 currentIdx = 0;
    	 HashMap<String, Object> result = new HashMap<String, Object>();
    	 Cepr01040150Business cepr0104050Business = new Cepr01040150Business( eproposalManager, editorUtil, cepr01030000HoldingForm );
         Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
         Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
         logger.info( "*-*-*-* editorUtil = " + editorUtil );
    	
    //	  AbstractJasperReportsView jasperViewer = new JasperReportsPdfView();
          HashMap<String, Object> params = new HashMap<String, Object>();
          HashMap<String, Object> copyparams = new HashMap<String, Object>();
          params.put( "format", "pdf" );

          Map<JRExporterParameter, Object> exporterParam = new HashMap<JRExporterParameter, Object>();
        

     //     jasperViewer.setExporterParameters( exporterParam );
     //      jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040150_product.jasper" );

          
          // Fill all values to detail row
    //      jasperViewer.setReportDataKey( "dataSource" );
    //      jasperViewer.setJdbcDataSource( eproposalManager.getDataSource() );
          
          HashMap<String, Object> paramsSantunan = new HashMap<String, Object>();
          paramsSantunan.put("product_code", "015");
          paramsSantunan.put("plan", cepr01030102Form.getRightPartOfBusinessCd()+8);
     //     List<SantunanMedicare> selectSantunanMedicare = eproposalManager.selectManfaat(paramsSantunan);
     //     params.put( "dataSource", selectSantunanMedicare );
//          copyparams.put( "dataSource", selectSantunanMedicare );
          
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
  	//			String nama_plan = MappingUtil.getLabel(cepr01030102Form.getAssurancePlanList(), "208~X"+cepr01030102Form.getRightPartOfBusinessCd());
  			//String nama_plan = nama_plan.replace("SMART KID ", "");  				
  				params.put("pcode_simulasi", "015");	
  				params.put("lump_sum",cepr01030102Form.getTotalSumInsured());
  		//		String jamMntDtk = eproposalManager.selectJamMntDtk();
  		//		params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectSysDate() ) + " "  + jamMntDtk );
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
  				
  				copyparams.put("pcode_simulasi", "015");	
  				copyparams.put("lump_sum",cepr01030102Form.getTotalSumInsured());
  	//			copyparams.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectSysDate() ) + " "  + jamMntDtk );
  				
  				params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
  				copyparams.put("bill_freq", cepr01030102Form.getPaymentModeCd().toString());
//  				copyparams.put("nama_agen",cepr01030101Form.get);
  				copyparams.put("nama_pp_1",cepr01030101Form.getPolicyHolderName());
  				copyparams.put("nama_pp", cepr01030101Form.getPolicyHolderName());
  				copyparams.put("nama_tt", cepr01030101Form.getInsuredName());
  				copyparams.put("plan", cepr01030102Form.getRightPartOfBusinessCd()+"");
  				copyparams.put("product_code", "015");
  				copyparams.put("sex_pp", cepr01030101Form.getInsuredSexCd());
  	//			copyparams.put("sex_tt", cepr01030102Form.getInsuredAdditionSexCd());
  				copyparams.put("umur_pp", cepr01030101Form.getPolicyHolderAge());
  				copyparams.put("umur_tt", cepr01030101Form.getInsuredAge() );
  				
//  				params.put("bod_pp", new SimpleDateFormat("dd/MM/yyyy").format(cepr01030101Form.getInsuredBirthDay()));
  	//			params.put("tgl_proposal",FormatDate.toIndonesian(new SimpleDateFormat("dd/MM/yyyy").format(eproposalManager.selectSysDate())));
  				params.put("premi_bln",premi_bln);
  				params.put("premi_tri",premi_tri);
  				params.put("premi_smt",premi_smt);
  				params.put("premi_thn",premi_thn);
  //				params.put("nama_plan", nama_plan);
  				params.put("fisibel", Boolean.FALSE);
  				  				
  	//			copyparams.put("tgl_proposal",FormatDate.toIndonesian(new SimpleDateFormat("dd/MM/yyyy").format(eproposalManager.selectSysDate())));
  				copyparams.put("premi_bln",premi_bln);
  				copyparams.put("premi_tri",premi_tri);
  				copyparams.put("premi_smt",premi_smt);
  				copyparams.put("premi_thn",premi_thn);
  	//			copyparams.put("nama_plan", nama_plan);
  				copyparams.put("fisibel", Boolean.FALSE);  				
  			}
         
          double premiMstProposal = 0.0;
          if( cepr01030102Form.getPaymentModeCd() == 6 ){// bulanan
          	premiMstProposal = LazyConverter.toDouble(cepr01030102Form.getPremium()) / 12.0;
          }else if( cepr01030102Form.getPaymentModeCd() == 3 ){// tahunan
          	premiMstProposal = LazyConverter.toDouble(cepr01030102Form.getPremium());
          }else if( cepr01030102Form.getPaymentModeCd() == 2 ){// semesteran
          	premiMstProposal = LazyConverter.toDouble(cepr01030102Form.getPremium()) / 2.0;
          }else if( cepr01030102Form.getPaymentModeCd() == 1 ){// triwulan
          	premiMstProposal = LazyConverter.toDouble(cepr01030102Form.getPremium()) / 4.0;
          }
   //       tempMstProposal.add( new Mst_proposal(cepr01030101Form.getKd_tamu(), cepr01030102Form.getCurrencyCd(), new BigDecimal(cepr01030102Form.getLeftPartOfBusinessCd()), new BigDecimal(cepr01030102Form.getRightPartOfBusinessCd()), 
   //       		cepr01030102Form.getTotalSumInsured(), new BigDecimal(FormatNumber.round(premiMstProposal,0)), BigDecimal.ZERO, new BigDecimal(cepr01030102Form.getTermOfContract()), new BigDecimal(cepr01030102Form.getPaymentModeCd()), "Smart Kid", "Dasar", cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge()));
          
//          if( cepr01030102Form.getInsuredAdditionName() != null && !"".equals( cepr01030102Form.getInsuredAdditionName() ) 
//          		&& cepr01030102Form.getInsuredAdditionAge() != null && cepr01030102Form.getInsuredAdditionAge() > 0 ){
//              tempMstProposal.add( new Mst_proposal(cepr01030101Form.getKd_tamu(), cepr01030102Form.getCurrencyCd(), new BigDecimal(cepr01030102Form.getLeftPartOfBusinessCd()), new BigDecimal(cepr01030102Form.getRightPartOfBusinessCd()), 
//              		cepr01030102Form.getTotalSumInsured(), new BigDecimal(FormatNumber.round(premiMstProposal,0)), BigDecimal.ZERO, new BigDecimal(cepr01030102Form.getTermOfContract()), new BigDecimal(cepr01030102Form.getPaymentModeCd()), "Smart Medicare", "Tambahan", cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge()));
//          }
  //        if( cepr01030101Form.getKd_tamu() != null && !"".equals(cepr01030101Form.getKd_tamu()))
 //     	{
          	BigDecimal payModeCd = new BigDecimal(cepr01030102Form.getPaymentModeCd());
//      		String selectJamMntDtk = eproposalManager.selectJamMntDtk();
//      		Date newDateTemp = eproposalManager.selectNewDate();
//      		String[] tempJamMntDtk = selectJamMntDtk.split(":");
//      		Integer jam = LazyConverter.toInt( tempJamMntDtk[0] );
//      		Integer menit =  LazyConverter.toInt( tempJamMntDtk[1] );
//      		Integer detik =  LazyConverter.toInt( tempJamMntDtk[2] );
//      		DateFormat tempDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//      		String dateString = tempDate.format(newDateTemp);
//      		Date newDate = FormatDate.toStampDate(dateString);
//      		newDate.setHours(jam);
//      		newDate.setMinutes(menit);
//      		newDate.setSeconds(detik);
//      		String dateStringFinal = tempDate.format(newDate);
    //      	String newDateFull = eproposalManager.selectNewDateNewFm();
    //  		String mhtActivity = eproposalManager.selectMstHistoryTamu(cepr01030101Form.getKd_tamu());
      //		if( mhtActivity != null && "CETAK PROPOSAL PRO SAFE".equals(mhtActivity) ){// jika log terakhir yg tersimpan di mst_history_tamu merupakan 'cetak proposal' maka tidak perlu diinsert lg
      			
      //		}else{
   //   			eproposalManager.insertMstHistoryTamu(cepr01030101Form.getKd_tamu().toUpperCase(), newDateFull, "CETAK PROPOSAL PRO SAFE");
      		
   //   		eproposalManager.insertMstProposal(tempMstProposal,payModeCd,0);
      	
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
          	logger.error("ERROR", e);
				// TODO: handle exception
			}*/
  	//	}
          params.put( "dsCommonHeaderRincian", JasperReportsUtils.convertReportData( cepr0104050Business.getCommonHeaderRincianRowList()) );      
          params.put( "dsRincian", JasperReportsUtils.convertReportData( cepr0104050Business.getPageList()) );
          params.put( "dsCommonHeaderRincianRiderBaby", JasperReportsUtils.convertReportData( cepr0104050Business.getCommonHeaderRincianSmallRowList()) );
                    
          int usiaTT = cepr01030101Form.getInsuredAge();
          
          if(cepr01030101Form.getBabyFlag()==CommonConst.CHECKED_FALSE){ 
       	   usiaTT = cepr01030101Form.getInsuredAge();
       	   params.put( "usiaTT", usiaTT);
         }else{
	       	if( CommonConst.CHECKED_TRUE.equals(cepr01030101Form.getBabyFlag()) ){	
	       	  usiaTT = 0;
	       	 params.put( "usiaTT",  usiaTT);
	       	 params.put( "usiaIbu",  cepr01030101Form.getInsuredAge());
	       	}
         }          
         
          params.put( "usiaPP", cepr01030101Form.getPolicyHolderAge());
          params.put( "mpp", cepr01030102Form.getPremiumFurloughYearCd());
          
          params.put("baby", CommonConst.REFRESH_FALSE );
      	if(cepr01030101Form.getBabyFlag()!=CommonConst.CHECKED_FALSE){
           	if( CommonConst.CHECKED_TRUE.equals(cepr01030101Form.getBabyFlag()) ) {	
          	Integer babyChooseCd = Integer.valueOf(cepr01030102Form.getBabyChooseCd());
          	 params.put( "usiaKandungan", new BigDecimal(babyChooseCd));
          	 params.put("baby", CommonConst.CHECKED_TRUE );
          } }   	
                      
      	 String labelCalonBayi="";
      	 if(cepr01030101Form.getBabyFlag()!=CommonConst.CHECKED_FALSE){
           	if( CommonConst.CHECKED_TRUE.equals(cepr01030101Form.getBabyFlag()) ) {		
           		labelCalonBayi = "(Calon Bayi) ";                 		
           	}
      	 }
      	params.put("nama_tt", cepr01030101Form.getInsuredName());
          
          List<Map<String, Object>> illustrationList;
          List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
         Map<String, Object> map; 
         double[][] tahapan = new double[4][5];
         tahapan[0][0] = 0.05;
         tahapan[0][1] = 0.1;
         tahapan[0][2] = 0.2;
         tahapan[0][3] = 0.5;
         tahapan[0][4] = 0.15;
         
         tahapan[1][0] = 0;
         tahapan[1][1] = 0.1;
         tahapan[1][2] = 0.2;
         tahapan[1][3] = 0.5;
         tahapan[1][4] = 0.2;
         
         tahapan[2][0] = 0;
         tahapan[2][1] = 0;
         tahapan[2][2] = 0.2;
         tahapan[2][3] = 0.5;
         tahapan[2][4] = 0.3;
         
         tahapan[3][0] = 0;
         tahapan[3][1] = 0;
         tahapan[3][2] = 0;
         tahapan[3][3] = 0.5;
         tahapan[3][4] = 0.5;
         double[] listTahapan = new double[23];
         double tmpTahapan = 0;
         
         double[][] rateBaby = new double[4][5];
         rateBaby[0][0] = 0;
         rateBaby[0][1] = 0;
         rateBaby[0][2] = 0;
         rateBaby[0][3] = 0;
         rateBaby[0][4] = 0;
                  
         rateBaby[1][0] = 1332000;
         rateBaby[1][1] = 1332000;
         rateBaby[1][2] = 1332000;
         rateBaby[1][3] = 1332000;
         rateBaby[1][4] = 1332000;
         
         rateBaby[2][0] = 2603000;
         rateBaby[2][1] = 2603000;
         rateBaby[2][2] = 2603000;
         rateBaby[2][3] = 2603000;
         rateBaby[2][4] = 2603000;
         
         rateBaby[3][0] = 4217000;
         rateBaby[3][1] = 4217000;
         rateBaby[3][2] = 4217000;
         rateBaby[3][3] = 4217000;
         rateBaby[3][4] = 4217000;
     
        
         Integer plan=5;
         String planKid;
         plan = cepr01030102Form.getRightPartOfBusinessCd();
         if(plan == 1){
      	   planKid = "Plan A";
         } else if(plan == 2){
      	   planKid = "Plan B";
         } else if(plan == 3){
      	   planKid = "Plan C"; 
         }else{
      	   planKid = "Plan D";
         }
         params.put( "planKid", planKid );
                                     
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
    	  params.put( "mbpremi",  new BigDecimal( (cepr01030102Form.getPremium().doubleValue()*10)/m) );       
    	  
         int a=0;
         for( int i = 1; i <= cepr01030102Form.getPremiumFurloughYearCd(); i++ )
         {
      	   a++;
      	   listTahapan[i] = 0;
      	   //hitung Tahapan
      	   /*
      	   if((usiaTT+i)==6){        	
      			   listTahapan[i] = tahapan[cepr01030102Form.getRightPartOfBusinessCd()-1][0] * cepr01030102Form.getTotalSumInsured().doubleValue();
      	   }
      	   else if((usiaTT+i)==12){
      		   listTahapan[i] = tahapan[cepr01030102Form.getRightPartOfBusinessCd()-1][1] * cepr01030102Form.getTotalSumInsured().doubleValue();
      	   }
      	   else if((usiaTT+i)==15){
      		   listTahapan[i] = tahapan[cepr01030102Form.getRightPartOfBusinessCd()-1][2] * cepr01030102Form.getTotalSumInsured().doubleValue();
      	   }
      	   else if((usiaTT+i)==18){
      		   listTahapan[i] = tahapan[cepr01030102Form.getRightPartOfBusinessCd()-1][3] * cepr01030102Form.getTotalSumInsured().doubleValue();
      	   }
      	   else if((usiaTT+i)==22){
      		   listTahapan[i] = tahapan[cepr01030102Form.getRightPartOfBusinessCd()-1][4] * cepr01030102Form.getTotalSumInsured().doubleValue();
      	   }
      	   tmpTahapan += listTahapan[i];
      	   */
         	 map = new HashMap<String, Object>();
         	 map.put( "yearNo", editorUtil.convertToString(i ) );
         	 map.put( "age", usiaTT+i );      	            	
         	 map.put( "premi", cepr01030102Form.getPremium()); 
         	 BigDecimal nt =  cepr0104050Business.computeNT( i );
         	 map.put( "nt", new BigDecimal(nt.doubleValue() / m) );          	 
         	 
         	 if(i == cepr01030102Form.getPremiumFurloughYearCd()){
         		 map.put( "lt", new BigDecimal( (cepr01030102Form.getPremium().doubleValue() * 12 * i)/m ));           		 
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
         params.put( "dsCommonBiayaRincian", JasperReportsUtils.convertReportData( cepr0104050Business.getBiayaList("", premi , label)) );
         params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
         params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
         params.put( "agentName", cepr01030101Form.getProposalUser() );
         params.put( "no_proposal", cepr01030102Form.getMstProposal().get(0).getId() );
         params.put( "labelCb", label );
         
         String title = "";
         if(cepr01030102Form.getRightPartOfBusinessCd()==1){
      	   title = "SMART LIFE CARE PLUS";
         }else if(cepr01030102Form.getRightPartOfBusinessCd()==2){
      	   title = "SIMAS PROTEKSI";
         }else if(cepr01030102Form.getRightPartOfBusinessCd()==3){
      	   title = "SMiLe LIFE CARE PLUS";
         }else if(cepr01030102Form.getRightPartOfBusinessCd()==6){
      	   title = "SMART LIFE PROTECTION";
         }else if(cepr01030102Form.getRightPartOfBusinessCd()==9 || cepr01030102Form.getRightPartOfBusinessCd()== 14){
        	   title = "SMiLe PROTEKSI";
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
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040150_product.jasper",
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
	    		logger.error( e );
	        	}
        
     //     jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
         result.put("dirName", dirName);
         result.put("fileName", fileName);
         result.put("messageBoxList", null);
         result.put("messageEkaSehatList", null);
    //     result.put("jasperViewer", jasperViewer);
         result.put("params", params);
   	
         return result;
    	
    }
        
  }


