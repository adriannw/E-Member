package id.co.sinarmaslife.eproposal.product.product01040222;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040105Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : Fadly Mathendra
 * Version              : 1.0
 * Creation Date    	: Jul 8, 2008 10:52:00 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import id.co.sinarmaslife.eproposal.business.Cepr00000000RiderBusiness;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Ilustration116Business;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000ProductBusiness;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000CurrencyUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.eproposal.model.vo.ParticipantVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030104Form;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.JrUtil;
import id.co.sinarmaslife.standard.util.LazyConverter;

//adrian : public class aslinya extends Cepr01040000ProductBusiness
//public class Cepr01040222Business extends Cepr01040000ProductBusiness
public class Cepr01040222Business extends Cepr01040000Ilustration116Business
{
    protected final Log logger = LogFactory.getLog( getClass() );
    private double rirj=0;
    private double rg=0;
    private double rb=0;
    private double pk=0; 
    private double totalByi=0; 

    public Cepr01040222Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
        setInsuredAgeLimit( 75 );
        
    }
    
    public static Map<String, Object> getManfaatPersonalAccidentParamsMap( Object command, Cepr00000000EditorUtil editorUtil ) throws IOException
    {
        System.out.println( "*-*-*-* ManfaatDataSource.getManfaatPersonalAccidentParamsMap" );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        Map<String, Object> params = new HashMap<String, Object>();
        String termOfPayment = editorUtil.convertToString( cepr01030102Form.getTermOfPayment() ) + " tahun.";
        String amount = Cepr00000000CurrencyUtil.sayForMoney( cepr01030102Form.getTotalSumInsured(), cepr01030102Form.getCurrencyCd() );
        String say = "Apabila Tertanggung meninggal dunia karena kecelakaan dalam masa Pertanggungan SMiLe PA Rider masih berlaku, maka akan dibayarkan Manfaat Asuransi Tambahan sebesar Uang Pertanggungan SMiLe PA Rider (".concat( amount ).concat( ").");
        params.put( "termOfPayment", termOfPayment );
        params.put( "paragraph1", say );
        return params;
    }
    
    public static Map<String, Object> getManfaatTermRiderParamsMap( Object command, Cepr00000000EditorUtil editorUtil ) throws IOException
    {
        System.out.println( "*-*-*-* ManfaatDataSource.getManfaatTermRiderParamsMap" );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        Map<String, Object> params = new HashMap<String, Object>();
        String termOfManfaatTermRider = editorUtil.convertToString( cepr01030102Form.getTermOfPayment() ) + " tahun.";
        String amount = Cepr00000000CurrencyUtil.sayForMoney( cepr01030102Form.getTotalSumInsured(), cepr01030102Form.getCurrencyCd() );
        String say = "akan dibayarkan manfaat tambahan sebesar 100% Uang Pertanggungan Pokok (".concat( amount ).concat( ").");
        params.put( "termOfManfaatTermRider", termOfManfaatTermRider );
        params.put( "amountInSpeechManfaatTermRider", say );
        return params;
    }
    
   
    public List<Map<String, Object>> getRiderMedicalPlusList()
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	  List<Map> result2 = new ArrayList();
    	  String riderMedicalPlusType="";
    	  String lsdbs_number="0";
    	  BigDecimal maxClaimPerYear=new BigDecimal(0);
    	  
     
            Map<String, Object> params;
                      
           
    		if(cepr01030102Form.getRightPartOfBusinessCd()==1){
            	riderMedicalPlusType = "GOLD";
            	lsdbs_number = "3";
            	maxClaimPerYear = new BigDecimal(1400000000);
            }
            else if(cepr01030102Form.getRightPartOfBusinessCd()==2){
            	riderMedicalPlusType = "PLATINUM";
            	lsdbs_number = "4";
            	maxClaimPerYear = new BigDecimal(1900000000);
            }
            
            
            params = new HashMap<String, Object>();
            params.put( "riderMedicalPlusType", riderMedicalPlusType);    
            result.add( params );
            
            Date tgl = eproposalManager.selectNowDate();          
            result2 = 	eproposalManager.selectRiderMedicalPlusList(lsdbs_number);
            
            for(int i=0;i<result2.size();i++){
            	String no = result2.get(i).get("NO").toString(); 
            	String ketBenefit = result2.get(i).get("KET_BENEFIT").toString();            	            	
            	BigDecimal nilaiBenefit = new BigDecimal(result2.get(i).get("NILAI_BENEFIT").toString());
            
            	 params = new HashMap<String, Object>();     
            	  params.put( "no",no );    
            	 params.put( "subject",ketBenefit );        
                 params.put( "descr", nilaiBenefit  );
                 params.put( "riderMedicalPlusType", riderMedicalPlusType  );
                 result.add( params );
            }
            
            params = new HashMap<String, Object>();          
            params.put( "maxClaimPerYear", maxClaimPerYear);
            params.put( "riderMedicalPlusType", riderMedicalPlusType);    
            result.add( params );
          
            
        

        return result;
    }
    
    
    public List<Map<String, Object>> getRiderMedicalPlusRjList()
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	  List<Map> result2 = new ArrayList();
    	  String riderMedicalPlusType="";
    	  String lsdbs_number="0";
    	  BigDecimal maxClaimPerYear=new BigDecimal(0);
    	  
    	
            Map<String, Object> params;
                                 
            if(cepr01030102Form.getRightPartOfBusinessCd()==1){
            	riderMedicalPlusType = "GOLD";
            	lsdbs_number = "3";
            	maxClaimPerYear = new BigDecimal(12500000);
            }
            else if(cepr01030102Form.getRightPartOfBusinessCd()==2){
            	riderMedicalPlusType = "PLATINUM";
            	lsdbs_number = "4";
            	maxClaimPerYear = new BigDecimal(15000000);
            }           
            
            params = new HashMap<String, Object>();
            params.put( "riderMedicalPlusType", riderMedicalPlusType);    
            result.add( params );
            
            Date tgl = eproposalManager.selectNowDate();          
            result2 = 	eproposalManager.selectRiderMedicalPlusRjList(lsdbs_number);
            
            for(int i=0;i<result2.size();i++){
            	String no = result2.get(i).get("NO").toString(); 
            	String ketBenefit = result2.get(i).get("KET_BENEFIT").toString();            	            	
            	BigDecimal nilaiBenefit = new BigDecimal(result2.get(i).get("NILAI_BENEFIT").toString());
            
            	 params = new HashMap<String, Object>();
            	 params.put( "no",no );    
            	 params.put( "subject",ketBenefit );        
                 params.put( "descr", nilaiBenefit  );
                 params.put( "riderMedicalPlusType", riderMedicalPlusType  );
                 result.add( params );
            }
            
            params = new HashMap<String, Object>();
            params.put( "maxClaimPerYear", maxClaimPerYear);
            params.put( "riderMedicalPlusType", riderMedicalPlusType);    
            result.add( params );          

        return result;
    }
    
    public List<Map<String, Object>> getRiderMedicalPlusRgList()
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	  List<Map> result2 = new ArrayList();
    	  String riderMedicalPlusType="";
    	  String lsdbs_number="0";
    	  BigDecimal maxClaimPerYear=new BigDecimal(0);
    	  
        if(CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getMedicalPlusRgFlag() ))
        {
            Map<String, Object> params;
                      
          
            if(cepr01030102Form.getRightPartOfBusinessCd()==1){
            	riderMedicalPlusType = "GOLD";
            	lsdbs_number = "23";
            	maxClaimPerYear = new BigDecimal(7500000);
            }
            else if(cepr01030102Form.getRightPartOfBusinessCd()==2){
            	riderMedicalPlusType = "PLATINUM";
            	lsdbs_number = "24";
            	maxClaimPerYear = new BigDecimal(10000000);
            }          
            
            params = new HashMap<String, Object>();
            params.put( "riderMedicalPlusType", riderMedicalPlusType);    
            result.add( params );
            
            Date tgl = eproposalManager.selectNowDate();          
            result2 = 	eproposalManager.selectRiderMedicalPlusRjList(lsdbs_number);
            
            for(int i=0;i<result2.size();i++){
            	String no = result2.get(i).get("NO").toString(); 
            	String ketBenefit = result2.get(i).get("KET_BENEFIT").toString();            	            	
            	BigDecimal nilaiBenefit = new BigDecimal(result2.get(i).get("NILAI_BENEFIT").toString());
            
            	 params = new HashMap<String, Object>();
            	 params.put( "no",no );    
            	 params.put( "subject",ketBenefit );        
                 params.put( "descr", nilaiBenefit  );
                 params.put( "riderMedicalPlusType", riderMedicalPlusType  );
                 result.add( params );
            }
            
            params = new HashMap<String, Object>();
            params.put( "maxClaimPerYear", maxClaimPerYear);
            params.put( "riderMedicalPlusType", riderMedicalPlusType);    
            result.add( params );
                      
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderMedicalPlusRbList()
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	  List<Map> result2 = new ArrayList();
    	  String riderMedicalPlusType="";
    	  String lsdbs_number="0";
    	     	  
    	if( ( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getMedicalPlusRbFlag()) || CommonConst.CHECKED_TRUE.equals(cepr01030113Form.getParticipantVOList().get(0).getMedicalPlusRbFlag()) ))
        {
            Map<String, Object> params;
                                  
            if(cepr01030102Form.getRightPartOfBusinessCd()==1){
            	riderMedicalPlusType = "GOLD";
            	lsdbs_number = "43";            
            }
            else if(cepr01030102Form.getRightPartOfBusinessCd()==2){
            	riderMedicalPlusType = "PLATINUM";
            	lsdbs_number = "44";            
            }
                      
            params = new HashMap<String, Object>();
            params.put( "riderMedicalPlusType", riderMedicalPlusType);    
            result.add( params );
            
            Date tgl = eproposalManager.selectNowDate();          
            result2 = 	eproposalManager.selectRiderMedicalPlusRjList(lsdbs_number);
            
            for(int i=0;i<result2.size();i++){
            	String no = result2.get(i).get("NO").toString(); 
            	String ketBenefit = result2.get(i).get("KET_BENEFIT").toString();            	            	
            	BigDecimal nilaiBenefit = new BigDecimal(result2.get(i).get("NILAI_BENEFIT").toString());
            
            	 params = new HashMap<String, Object>();
            	 params.put( "no",no );    
            	 params.put( "subject",ketBenefit );        
                 params.put( "descr", nilaiBenefit  );
                 params.put( "riderMedicalPlusType", riderMedicalPlusType  );
                 result.add( params );
            }
            
            params = new HashMap<String, Object>();
            params.put( "riderMedicalPlusType", riderMedicalPlusType);    
            result.add( params );
          
            
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderMedicalPlusPkList()
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	  List<Map> result2 = new ArrayList();
    	  String riderMedicalPlusType="";
    	  String lsdbs_number="0";
    	  BigDecimal maxClaimPerYear=new BigDecimal(0);
    	  
    	  if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getMedicalPlusPkFlag()))
    	 {
            Map<String, Object> params;
                                  
            if(cepr01030102Form.getRightPartOfBusinessCd()==1){
            	riderMedicalPlusType = "GOLD";
            	lsdbs_number = "63";            
            	maxClaimPerYear = new BigDecimal(5500000);
            }
            else if(cepr01030102Form.getRightPartOfBusinessCd()==2){
            	riderMedicalPlusType = "PLATINUM";
            	lsdbs_number = "64";            
            	maxClaimPerYear = new BigDecimal(7500000);
            }          
            
            params = new HashMap<String, Object>();
            params.put( "riderMedicalPlusType", riderMedicalPlusType);    
            result.add( params );
            
            Date tgl = eproposalManager.selectNowDate();          
            result2 = 	eproposalManager.selectRiderMedicalPlusRjList(lsdbs_number);
            
            for(int i=0;i<result2.size();i++){
            	String no = result2.get(i).get("NO").toString(); 
            	String ketBenefit = result2.get(i).get("KET_BENEFIT").toString();            	            	
            	BigDecimal nilaiBenefit = new BigDecimal(result2.get(i).get("NILAI_BENEFIT").toString());
            	
            	 params = new HashMap<String, Object>();
            	 params.put( "no",no );    
            	 params.put( "subject",ketBenefit );        
                 params.put( "descr", nilaiBenefit  );
                 params.put( "riderMedicalPlusType", riderMedicalPlusType  );
                 result.add( params );
            }
            
            params = new HashMap<String, Object>();
            params.put( "maxClaimPerYear", maxClaimPerYear);
            params.put( "riderMedicalPlusType", riderMedicalPlusType);    
            result.add( params );
          
            
        }

        return result;
    }
    
    
    public List<Map<String, Object>> getPageList( int currentIdx ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getPageList" );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub1" );
        result.add( params );

        if(CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getMedicalPlusRgFlag() ) || CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getMedicalPlusRbFlag()) || CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getMedicalPlusPkFlag()))
        {
        	   params = new HashMap<String, Object>();
               params.put( "pageCode", "sub2" );
               result.add( params );
        	
        }
     
               
        // if no riders, don't show this page ( page 3 )
        /*
        if( currentIdx != 0 )
        {
            params = new HashMap<String, Object>();
            params.put( "pageCode", "sub3" );
            result.add( params );
            
            params = new HashMap<String, Object>();
            params.put( "pageCode", "sub5" );
            result.add( params );
        }*/

//        params = new HashMap<String, Object>();
//        params.put( "pageCode", "sub4" );
//        result.add( params );

        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub6" );
        result.add( params );
             
        
        return result;
    }
    
    public BigDecimal computePremium( Integer payModeCd )
    {
        
        Map<String, Object> params = new HashMap<String, Object>();
        int riderBaru = 0;
        double ldec_rate=0, ldec_pct=1 ;
        double ldec_total=0;
        double ldec_temp=0;
        BigDecimal premium;
        
         rirj=0;
         rg=0;
         rb=0;
         pk=0;
         totalByi=0;
         
        if(cepr01030102Form.getRightPartOfBusinessCd()==1){
        	riderBaru = 1;        	
        } else if(cepr01030102Form.getRightPartOfBusinessCd()==2){
        	riderBaru = 2;        	
        }        
      
        params.put( "riderBaru", riderBaru );  
        params.put( "kursId", cepr01030102Form.getCurrencyCd() );
        params.put( "liUsia", cepr01030101Form.getInsuredAge() );
        ldec_rate = LazyConverter.toDouble( eproposalManager.selectProvestraRiRj( params ) );
    
		if( LazyConverter.toInt( cepr01030102Form.getPaymentModeCd() ) == 1 ){  //triwulan
			ldec_pct = 0.35;
		}else if( LazyConverter.toInt( cepr01030102Form.getPaymentModeCd() ) == 2 ){ //semesteran
			ldec_pct = 0.65;
		}else if( LazyConverter.toInt( cepr01030102Form.getPaymentModeCd() ) == 6 ){ //bulanan
			ldec_pct = 0.12;
		}
        ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
        rirj = ldec_temp;
//		ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
		ldec_total += ldec_temp;
		
		 //  Provestra Peserta Tambahan
    	 if( istr_prop.medicalPlus.peserta > 0  )
         {
    		 int riderBaruPeserta=1;
    		 if(cepr01030102Form.getRightPartOfBusinessCd()==1){
    			 riderBaruPeserta = 1;        	
    	     } else if(cepr01030102Form.getRightPartOfBusinessCd()==2){
    	    	 riderBaruPeserta = 2;        	
    	     }        
    
            for( int i = 1; i <= istr_prop.medicalPlus.peserta; i++ )
            {
                int li_usia_peserta  = istr_prop.medicalPlus.usia[ i ];               
                	riderBaruPeserta = riderBaruPeserta + 2;
              
                Map<String, Object> prm = new HashMap<String, Object>();
                prm.put( "riderBaru", riderBaruPeserta );  
                prm.put( "kursId", cepr01030102Form.getCurrencyCd() );
                prm.put( "liUsia", li_usia_peserta );
                ldec_rate = LazyConverter.toDouble( eproposalManager.selectProvestraRiRj( prm ) );	                        
                ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
                ldec_total += ldec_temp;	    
            }
            rirj = ldec_total;
         }
		
		if(CommonConst.CHECKED_TRUE.equals(cepr01030102Form.getMedicalPlusRgFlag())){
			params.put("lsbsId", "841");
            ldec_rate = LazyConverter.toDouble( eproposalManager.selectProvestraRgRbPk( params ) );
            ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
            rg = ldec_temp;
 			 ldec_total += ldec_temp;
 			 
 			 //  Provestra Peserta Tambahan
 	    	 if( istr_prop.medicalPlus.peserta > 0  )
 	         {
 	    		 int riderBaruPeserta=1;
 	    		 if(cepr01030102Form.getRightPartOfBusinessCd()==1){
 	    			 riderBaruPeserta = 1;        	
 	    	     } else if(cepr01030102Form.getRightPartOfBusinessCd()==2){
 	    	    	 riderBaruPeserta = 2;        	
 	    	     }        
 	    
 	            for( int i = 1; i <= istr_prop.medicalPlus.peserta; i++ )
 	            {
 	                int li_usia_peserta  = istr_prop.medicalPlus.usia[ i ];               
 	                	riderBaruPeserta = riderBaruPeserta + 2;
 	              
 	                Map<String, Object> prm = new HashMap<String, Object>();
 	                prm.put("lsbsId", "841");
 	                prm.put( "riderBaru", riderBaruPeserta );  
 	                prm.put( "kursId", cepr01030102Form.getCurrencyCd() );
 	                prm.put( "liUsia", li_usia_peserta );
 	                ldec_rate = LazyConverter.toDouble( eproposalManager.selectProvestraRgRbPk( prm ) );	                        
 	                ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
 	                rg += ldec_temp;
 	                ldec_total += ldec_temp;	    
 	            } 	            
 	         } 			 
		}
		
		if(CommonConst.CHECKED_TRUE.equals(cepr01030102Form.getMedicalPlusRbFlag())){           				
			 params.put("lsbsId", "842");        	
			 ldec_rate = LazyConverter.toDouble( eproposalManager.selectProvestraRgRbPk( params ) );
			 ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
            rb = ldec_temp;
			 ldec_total += ldec_temp;
			 
			//  Provestra Peserta Tambahan
 	    	 if( istr_prop.medicalPlus.peserta > 0  )
 	         {
 	    		 int riderBaruPeserta=1;
 	    		 if(cepr01030102Form.getRightPartOfBusinessCd()==1){
 	    			 riderBaruPeserta = 1;        	
 	    	     } else if(cepr01030102Form.getRightPartOfBusinessCd()==2){
 	    	    	 riderBaruPeserta = 2;        	
 	    	     }        
 	    
 	            for( int i = 1; i <= istr_prop.medicalPlus.peserta; i++ )
 	            {
 	                int li_usia_peserta  = istr_prop.medicalPlus.usia[ i ];               
 	                	riderBaruPeserta = riderBaruPeserta + 2;
 	              
 	                Map<String, Object> prm = new HashMap<String, Object>();
 	                prm.put("lsbsId", "842");
 	                prm.put( "riderBaru", riderBaruPeserta );  
 	                prm.put( "kursId", cepr01030102Form.getCurrencyCd() );
 	                prm.put( "liUsia", li_usia_peserta );
 	                ldec_rate = LazyConverter.toDouble( eproposalManager.selectProvestraRgRbPk( prm ) );	                        
 	                ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
 	                rb += ldec_temp;
 	                ldec_total += ldec_temp;	    
 	            } 	            
 	         }
		}
		
		if(CommonConst.CHECKED_TRUE.equals(cepr01030102Form.getMedicalPlusPkFlag())){           				
			 params.put("lsbsId", "843");        	
			 ldec_rate = LazyConverter.toDouble( eproposalManager.selectProvestraRgRbPk( params ) );
			 ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
           pk = ldec_temp;
			 ldec_total += ldec_temp;
			 
			//  Provestra Peserta Tambahan
 	    	 if( istr_prop.medicalPlus.peserta > 0  )
 	         {
 	    		 int riderBaruPeserta=1;
 	    		 if(cepr01030102Form.getRightPartOfBusinessCd()==1){
 	    			 riderBaruPeserta = 1;        	
 	    	     } else if(cepr01030102Form.getRightPartOfBusinessCd()==2){
 	    	    	 riderBaruPeserta = 2;        	
 	    	     }        
 	    
 	            for( int i = 1; i <= istr_prop.medicalPlus.peserta; i++ )
 	            {
 	                int li_usia_peserta  = istr_prop.medicalPlus.usia[ i ];               
 	                	riderBaruPeserta = riderBaruPeserta + 2;
 	              
 	                Map<String, Object> prm = new HashMap<String, Object>();
 	                prm.put("lsbsId", "843");
 	                prm.put( "riderBaru", riderBaruPeserta );  
 	                prm.put( "kursId", cepr01030102Form.getCurrencyCd() );
 	                prm.put( "liUsia", li_usia_peserta );
 	                ldec_rate = LazyConverter.toDouble( eproposalManager.selectProvestraRgRbPk( prm ) );	                        
 	                ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
 	                pk += ldec_temp;
 	                ldec_total += ldec_temp;	    
 	            } 	            
 	         }
		}	
		 ldec_total = FormatNumber.round(ldec_total, 2);
		 totalByi = ldec_total;
		 
         premium = new BigDecimal( ldec_total );
        
        return premium;
    } 
    
    
    public List<Map<String, Object>> getCommonHeaderRowList() throws IOException
    {       
        List sekaligusRightPartCdList = ArrUtil.toListFromArray( sekaligusRightPartCdArr );
        List berkalaRightPartCdList = ArrUtil.toListFromArray( berkalaRightPartCdArr );
                
        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        
        result.addAll( source.getPolicyHolderNameMap() );
    	result.addAll( source.getPolicyHolderAgeMap());
   
	    result.addAll( source.getInsuredNameMap() );
	    result.addAll( source.getInsuredAgeMap() );        
	    Map<String, Object> params;
	           
	    params = new HashMap<String, Object>();
	    params.put( "label", "Masa Pembayaran Premi" );
	    params.put( "content", editorUtil.convertToString( cepr01030102Form.getTermOfPayment() ) + " tahun (dan dapat diperpanjang)" );
	    result.add( params );
	    
	    result.addAll( getProvestraPlan() );
	    BigDecimal premium = computePremium( cepr01030102Form.getPaymentModeCd() );
	    
	    String labelCb = ""; 
		if( LazyConverter.toInt( cepr01030102Form.getPaymentModeCd() ) == 1 ){  //triwulan
			labelCb = "Triwulanan";
		}else if( LazyConverter.toInt( cepr01030102Form.getPaymentModeCd() ) == 2 ){ //semesteran
			labelCb = "Semesteran";
		}else if( LazyConverter.toInt( cepr01030102Form.getPaymentModeCd() ) == 6 ){ //bulanan
			labelCb = "Bulanan";
		}	   	     
	        params = new HashMap<String, Object>();
	        params.put( "label", "Premi "+labelCb);
	        params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( premium ));
	        result.add( params );
        
        return result;
    }
    
    public List<Map<String, Object>> getTermOfPaymentMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;
      
      //  if( cepr01030102Form.getPaymentModeCd() != Hardcode.PAY_MODE_CD_SEKALIGUS )
      //  {
            params = new HashMap<String, Object>();
            params.put( "label", "Masa Pembayaran Premi" );
            params.put( "content", editorUtil.convertToString( cepr01030102Form.getTermOfPayment() ) + " tahun (dan dapat diperpanjang)" );
            result.add( params );
      //  }

        return result;
    }
    
    
    public List<Map<String, Object>> getProvestraPlan() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;
      
		String plan = "";
		if(cepr01030102Form.getRightPartOfBusinessCd()==1){
			plan = "GOLD";
		}else if(cepr01030102Form.getRightPartOfBusinessCd()==2){
			plan="PLATINUM";
		}		
		 params = new HashMap<String, Object>();
         params.put( "label", "Jenis Plan Rawat Inap" );
         params.put( "content", plan );
         result.add( params );
         
         params = new HashMap<String, Object>();
         params.put( "label", "          Rawat Jalan" );
         params.put( "content", plan );
         result.add( params );
         
         if(CommonConst.CHECKED_TRUE.equals(cepr01030102Form.getMedicalPlusRgFlag())){
        	 params = new HashMap<String, Object>();
             params.put( "label", "          Rawat Gigi" );
             params.put( "content", plan );
             result.add( params ); 			
 		}
         
         if(CommonConst.CHECKED_TRUE.equals(cepr01030102Form.getMedicalPlusRbFlag())){
        	 params = new HashMap<String, Object>();
             params.put( "label", "          Rawat Bersalin" );
             params.put( "content", plan );
             result.add( params ); 			
 		}
                 		
         if(CommonConst.CHECKED_TRUE.equals(cepr01030102Form.getMedicalPlusPkFlag())){
        	 params = new HashMap<String, Object>();
             params.put( "label", "          Penunjang kesehatan" );
             params.put( "content", plan );
             result.add( params ); 			
 		}         		
                 	
            
        return result;
    }
    
    
    public List<OptionVO> getByi() throws IOException
    {
    	
    	 List<OptionVO> detailVOList2 = new ArrayList<OptionVO>();
    	 double ldec_total=0;
    	     	
    	// ldec_total = FormatNumber.round(rirj, 2);
    	 
    	 BigDecimal rirj2 = new BigDecimal( rirj );
    	 OptionVO optionVO = new OptionVO("Jaminan Rawat Inap & Rawat Jalan", commonUsedBusiness.toMoneyWithCurrencyCd( rirj2));
    	  detailVOList2.add(optionVO);
    	  
    	  if(CommonConst.CHECKED_TRUE.equals(cepr01030102Form.getMedicalPlusRgFlag())){
    		  BigDecimal rg2 = new BigDecimal( rg );
    	    	  optionVO = new OptionVO("Jaminan Tambahan Rawat Gigi", commonUsedBusiness.toMoneyWithCurrencyCd( rg2));
    	    	  detailVOList2.add(optionVO);    		  
    	  }
    	  if(CommonConst.CHECKED_TRUE.equals(cepr01030102Form.getMedicalPlusRbFlag())){
    		  BigDecimal rb2 = new BigDecimal( rb );
    	    	  optionVO = new OptionVO("Jaminan Tambahan Rawat Bersalin", commonUsedBusiness.toMoneyWithCurrencyCd( rb2));
    	    	  detailVOList2.add(optionVO);    		  
    	  }
    	  if(CommonConst.CHECKED_TRUE.equals(cepr01030102Form.getMedicalPlusPkFlag())){
    		  BigDecimal pk2 = new BigDecimal( pk );
    	    	  optionVO = new OptionVO("Jaminan Tambahan Pemeriksaan Kesehatan", commonUsedBusiness.toMoneyWithCurrencyCd( pk2));
    	    	  detailVOList2.add(optionVO);    		  
    	  }    	 
        		 
    	 return detailVOList2;
    }
    
    public String getTotalByi(){
    	String ttl;
    	 BigDecimal totalByi2 = new BigDecimal( totalByi );
    	 ttl = commonUsedBusiness.toMoneyWithCurrencyCd(totalByi2);
    	 
    	return ttl;
    			
    }
    
    public List<Map<String, Object>> getRincianRiderMedicalPlusParticipantsList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();    
        Cepr00000000RiderBusiness riderBusiness = new Cepr00000000RiderBusiness(eproposalManager, editorUtil, command);
        
            List<ParticipantVO> participantVOList = cepr01030113Form.getParticipantVOList();
            participantVOList = riderBusiness.removeEmptyParticipantVOFromTheList( participantVOList );
            Map<String, Object> params;
            Integer idx = 0;
            for( ParticipantVO vo : participantVOList )
            {
                idx++;
                params = new HashMap<String, Object>();
                params.put( "idx", idx.toString().concat( "." ) );
                params.put( "name", vo.getName() );
                params.put( "age", vo.getAge().toString() );
                params.put( "sex", vo.getSexCd().toString() );
                result.add( params );
          
        }

        return result;
    }
    
}
