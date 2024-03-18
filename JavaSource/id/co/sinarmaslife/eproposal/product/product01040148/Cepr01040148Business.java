package id.co.sinarmaslife.eproposal.product.product01040148;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040107Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Apr 18, 2008 2:34:35 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Ilustration116Business;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000ProductBusiness;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000UlinkBusiness;
import id.co.sinarmaslife.eproposal.common.pb.PbFunction;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.eproposal.model.pb.N_riders;
import id.co.sinarmaslife.eproposal.model.pb.S_biaya;
import id.co.sinarmaslife.eproposal.model.vo.Cepr00000000PerformanceVO;
import id.co.sinarmaslife.eproposal.model.vo.Cepr00000000YieldResultVO;
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
import id.co.sinarmaslife.eproposal.model.vo.ParticipantVO;
import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.standard.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Cepr01040148Business extends Cepr01040000UlinkBusiness 
{
    protected final Log logger = LogFactory.getLog( getClass() );

    private Cepr00000000YieldResultVO yieldResultVO;

    public Cepr01040148Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
//        setSekaligusRightPartCdArr( new Integer[]{ Cepr01040129Mapper.X16 } );
    }

    public List<Map<String, Object>> getPageList( int currentIdx ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getPageList" );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub1" );
        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub2" );
        result.add( params );

        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub6" );
        result.add( params );
        
        return result;
    }
    
    public List<Map<String, Object>> getRincianRiderEkaSehat( int jenis )
    {
   	 List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
   
       		Map<String, Object> params;
     		String jenisSantunan = null;
     		String biayaInap = null;
     		String biayaIcu = null;
     		String limitKejadian = null;
     		String maksPenggantian = null;
     		
     	  // if( cepr01030102Form.getEkaSehatCd()!= null && cepr01030102Form.getEkaSehatCd() > 0 ){
     		//	jenis = cepr01030102Form.getEkaSehatCd();
     	 // }
         	if( jenis == 16 ){ // plan A
         		jenisSantunan="Plan A-100";
         		biayaInap="100,000";
         		biayaIcu="200,000";
         		limitKejadian="12,500,000";
         		maksPenggantian="50,000,000";
         	}else if( jenis == 17 ){ // plan B
         		jenisSantunan="Plan B-150";
         		biayaInap="150,000";
         		biayaIcu="300,000";
         		limitKejadian="18,750,000";
         		maksPenggantian="75,000,000";
         	}else if( jenis == 18 ){ // plan C
         		jenisSantunan="Plan C-200";
         		biayaInap="200,000";
         		biayaIcu="400,000";
         		limitKejadian="25,000,000";
         		maksPenggantian="100,000,000";
         	}else if( jenis == 19 ){ // plan D
         		jenisSantunan="Plan D-250";
         		biayaInap="250,000";
         		biayaIcu="500,000";
         		limitKejadian="31,250,000";
         		maksPenggantian="125,000,000";
         	}else if( jenis == 20 ){ // plan E
         		jenisSantunan="Plan E-300";
         		biayaInap="300,000";
         		biayaIcu="600,000";
         		limitKejadian="37,500,000";
         		maksPenggantian="150,000,000";
         	}else if( jenis == 21 ){ // plan F
         		jenisSantunan="Plan F-350";
         		biayaInap="350,000";
         		biayaIcu="700,000";
         		limitKejadian="43,750,000";
         		maksPenggantian="175,000,000";
         	}else if( jenis == 22 ){ // plan G
         		jenisSantunan="Plan G-400";
         		biayaInap="400,000";
         		biayaIcu="800,000";
         		limitKejadian="50,000,000";
         		maksPenggantian="200,000,000";
         	}else if( jenis == 23 ){ // plan H
         		jenisSantunan="Plan H-500";
         		biayaInap="500,000";
         		biayaIcu="1,000,000";
         		limitKejadian="100,000,000";
         		maksPenggantian="400,000,000";
         	}else if( jenis == 24 ){ // plan I
         		jenisSantunan="Plan I-600";
         		biayaInap="600,000";
         		biayaIcu="1,200,000";
         		limitKejadian="125,000,000";
         		maksPenggantian="500,000,000";
         	}else if( jenis == 25 ){ // plan J
         		jenisSantunan="Plan J-700";
         		biayaInap="700,000";
         		biayaIcu="1,400,000";
         		limitKejadian="150,000,000";
         		maksPenggantian="600,000,000";
         	}else if( jenis == 26 ){ // plan K
         		jenisSantunan="Plan K-800";
         		biayaInap="800,000";
         		biayaIcu="1,600,000";
         		limitKejadian="175,000,000";
         		maksPenggantian="700,000,000";
         	}else if( jenis == 27 ){ // plan L
         		jenisSantunan="Plan L-900";
         		biayaInap="900,000";
         		biayaIcu="1,800,000";
         		limitKejadian="200,000,000";
         		maksPenggantian="800,000,000";
         	}else if( jenis == 28 ){ // plan M
         		jenisSantunan="Plan M-1000";
         		biayaInap="1,000,000";
         		biayaIcu="2,000,000";
         		limitKejadian="225,000,000";
         		maksPenggantian="900,000,000";
         	}else if( jenis == 29 ){ // plan N
         		jenisSantunan="Plan N-1500";
         		biayaInap="1,500,000";
         		biayaIcu="3,000,000";
         		limitKejadian="350,000,000";
         		maksPenggantian="1,400,000,000";
         	}else if( jenis == 30 ){ // plan O
         		jenisSantunan="Plan O-2000";
         		biayaInap="2,000,000";
         		biayaIcu="4,000,000";
         		limitKejadian="475,000,000";
         		maksPenggantian="1,900,000,000";
         	}           
         	              
         	 params = new HashMap<String, Object>();         	
             params.put( "plan", jenisSantunan );            
             result.add( params ); 
         	
             params = new HashMap<String, Object>();
             params.put( "no", "1"  );          
             params.put( "subject", "Biaya Kamar dan Menginap di Rumah Sakit (maksimal 365 hari) per hari" );
             params.put( "descr", biayaInap  );          
             result.add( params );
              
             params = new HashMap<String, Object>();
             params.put( "no", "2"  );    
             params.put( "subject", "Biaya Kamar ICU/ICCU (maks 60 hari) per hari" );
             params.put( "descr", biayaIcu  );           
             result.add( params );
              
         	 params = new HashMap<String, Object>();
         	 params.put( "no", "3"  );    
         	 params.put( "subject", "Biaya Aneka Perawatan di Rumah Sakit" );
             params.put( "descr", "As Charge"  );      
             result.add( params );
              
         	 params = new HashMap<String, Object>();
         	 params.put( "no", "4"  );    
         	 params.put( "subject", "Biaya Operasi/Pembedahan" );
             params.put( "descr", "As Charge"  );       
             result.add( params );
              
         	 params = new HashMap<String, Object>();
         	 params.put( "no", "5"  );    
         	 params.put( "subject", "Biaya Kunjungan Dokter di Rumah Sakit" );
             params.put( "descr", "As Charge"  );
             params.put( "detail", ""  );
             result.add( params );
              
             params = new HashMap<String, Object>();
             params.put( "no", "6"  );    
             params.put( "subject", "Biaya Kunjungan Dokter Spesialis di Rumah Sakit" );
             params.put( "descr", "As Charge"  );
             params.put( "detail", ""  );
             result.add( params );
              
         	 params = new HashMap<String, Object>();
         	 params.put( "no", "7"  );    
         	 params.put( "subject", "Biaya Pemeriksaan Laboratorium & Test Diagnostik (7 hari sebelum Rawat Inap)" );
             params.put( "descr", "As Charge"  );            
             result.add( params );
              
         	 params = new HashMap<String, Object>();
         	 params.put( "no", "8"  );    
         	 params.put( "subject", "Biaya Konsultasi Lanjutan (30 hari setelah Rawat Inap)" );
             params.put( "descr", "As Charge"  );            
             result.add( params );
              
         	 params = new HashMap<String, Object>();
         	 params.put( "no", "9"  );     
         	 params.put( "subject", "Transportasi dgn Mobil Ambulance ke RS (darurat)" );
             params.put( "descr", "As Charge"  );         
             result.add( params );
              
         	 params = new HashMap<String, Object>();
             params.put( "limit", maksPenggantian );            
             result.add( params ); 
     
         
     return result;
    }
    
    public List<Map<String, Object>> getRincianRiderEkaSehatParticipantsList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
     
            List<ParticipantVO> participantVOList = cepr01030107Form.getParticipantVOList();
            participantVOList = removeEmptyParticipantVOFromTheList( participantVOList );
            Map<String, Object> params;
            Integer idx = 0;
            for( ParticipantVO vo : participantVOList )
            {
                idx++;
                params = new HashMap<String, Object>();
                params.put( "idx", idx.toString().concat( "." ) );
                params.put( "name", vo.getName() );
                params.put( "age", vo.getAge().toString() );
                result.add( params );
            }
        
        return result;
    }
    
    public static List<ParticipantVO> removeEmptyParticipantVOFromTheList( List<ParticipantVO> list )
    {
        List<ParticipantVO> result = new ArrayList<ParticipantVO>();
        String name;
        int usia;
        for( ParticipantVO vo : list )
        {
            name = vo.getName();
          //  usia = vo.getAge();
            name = name == null? "" : name.trim();
       
            if( vo.getAge() != null && vo.getAge() >= 0  )
            {
                result.add( vo );
            }
        }
        return result;
    }
    
    public List<Map<String, Object>> getCommonBiayaRincianRider(BigDecimal ldec_coi, String caraBayar) throws IOException
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();                     
		Map<String, Object> params = new HashMap<String, Object>();		
		params.put( "label", "-	Biaya Asuransi" );
		 
		//  params.put( "content", editorUtil.convertToString( "Rp."+ ldec_coi+",- per tahun*" ) );
		params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( ldec_coi )+ "  "+ caraBayar +"**");
		params.put( "separator", ":" );
		result.add( params );                   
      return result;      
    }
    
    public List<Map<String, Object>> getHcpProviderList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
       
        Integer jenis = null;
 		
 			jenis = cepr01030102Form.getRightPartOfBusinessCd();
 			String jenisSantunan = null;
     		String biaya = null;
     		   		     		
     	if(jenis==49 || jenis==37 || jenis==25){ // plan A
     		jenisSantunan="Plan R-100";
     		biaya="100,000";     		
     	}else if(jenis==50 || jenis==38 || jenis==26){ // plan B
     		jenisSantunan="Plan R-200";
     		biaya="200,000";     		
     	}else if(jenis==51 || jenis==39 || jenis==27){ // plan C
     		jenisSantunan="Plan R-300";
     		biaya="300,000";     		
     	}else if(jenis==52 || jenis==40 || jenis==28){ // plan D
     		jenisSantunan="Plan R-400";
     		biaya="400,000";     		
     	}else if(jenis==53 || jenis==41 || jenis==29){ // plan E
     		jenisSantunan="Plan R-500";
     		biaya="500,000";     		
     	}else if(jenis==54 || jenis==42 || jenis==30){ // plan F
     		jenisSantunan="Plan R-600";
     		biaya="600,000";     		
     	}else if(jenis==55 || jenis==43 || jenis==31){ // plan G
     		jenisSantunan="Plan R-700";
     		biaya="700,000";     	
     	}else if(jenis==56 || jenis==44 || jenis==32){ // plan H
     		jenisSantunan="Plan R-800";
     		biaya="800,000";     		
     	}else if(jenis==57 || jenis==45 || jenis==33){ // plan I
     		jenisSantunan="Plan R-900";
     		biaya="900,000";     		
     	}else if(jenis==58 || jenis==46 || jenis==34){ // plan J
     		jenisSantunan="Plan R-1000";
     		biaya="1,000,000";     		
     	}else if(jenis==59 || jenis==47 || jenis==35){ // plan K
     		jenisSantunan="Plan R-1500";
     		biaya="1,500,000";     		
     	}else if(jenis==60 || jenis==48 || jenis==36){ // plan L
     		jenisSantunan="Plan R-2000";
     		biaya="2,000,000";     		
     	}
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Jenis Santunan" );
            params.put( "descr", jenisSantunan  );
            params.put( "term", "" );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap karena sakit atau kecelakaan" );
           // params.put( "descr",  editorUtil.convertToStringWithoutCent( biaya )  );
            params.put( "descr",   biaya );
            params.put( "term", "Maks 180 hari per satu tahun polis *)" );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "pageCode", "sub1" );
            result.add( params );
            
        return result;
    }
    
    public BigDecimal computePremium()
    {      
        logger.info( "*-*-*-* Cepr01040139Entry.computePremium" );
        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put("lsbs_id", "195");
        sqlParams.put("plan", cepr01030102Form.getRightPartOfBusinessCd() );
        sqlParams.put("liUsia", cepr01030101Form.getInsuredAge());        
      
        double premium = LazyConverter.toDouble(eproposalManager.selectPremiKid(sqlParams)) ;
        
        double ldec_total = premium;
        double ldec_pct = 1, ldec_temp=0;        
          
        if( cepr01030109Form.getParticipantVOList().size() > 0  )
        {
	        for( int i = 0; i < cepr01030109Form.getParticipantVOList().size(); i++ )
	        {
	        	if( cepr01030109Form.getParticipantVOList().get(i).getAge() != null ){
	        		if(cepr01030109Form.getParticipantVOList().get(i).getAge() > 0 ){
		           int li_usia = cepr01030109Form.getParticipantVOList().get(i).getAge();
		
		            Map<String, Object> paramsPeserta = new HashMap<String, Object>();
		            paramsPeserta.put("lsbs_id", "195");
		            paramsPeserta.put( "plan", cepr01030102Form.getRightPartOfBusinessCd() );    
		            //paramsPeserta.put( "kursId", cepr01030102Form.getCurrencyCd() );
		            paramsPeserta.put( "liUsia", li_usia );
		            premium = LazyConverter.toDouble(eproposalManager.selectPremiKid(paramsPeserta)) ;
		      
		            ldec_temp = FormatNumber.round( ( premium * ldec_pct ) * 0.9 , 2 );
		            ldec_total += ldec_temp;
	        	}
	        }}
        }           
        
        int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );
        double factor_percentage = 1;
        switch( paymentModeCd )
        {
            case Hardcode.PAY_MODE_CD_TRIWULANAN: ldec_total = ldec_total * 0.27; 	break;
            case Hardcode.PAY_MODE_CD_SEMESTERAN: ldec_total = ldec_total * 0.525;  break;
            case Hardcode.PAY_MODE_CD_BULANAN   : ldec_total = ldec_total * 0.1; 	break;
            default: break;
        }              
              
      
       
        BigDecimal premium2 = new BigDecimal(ldec_total);       
                      
        return premium2;
    }
    
    public BigDecimal computePremiumSetahun()
    {
        logger.info( "*-*-*-* Cepr01040139Entry.computePremium" );
        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put("lsbs_id", "195");
        sqlParams.put("plan", cepr01030102Form.getRightPartOfBusinessCd() );
        sqlParams.put("liUsia", cepr01030101Form.getInsuredAge());        
      
        double premium = LazyConverter.toDouble(eproposalManager.selectPremiKid(sqlParams)) ;
        
        double ldec_total = premium;
        double ldec_pct = 1, ldec_temp=0;        
          
        if( cepr01030109Form.getParticipantVOList().size() > 0  )
        {
	        for( int i = 0; i < cepr01030109Form.getParticipantVOList().size(); i++ )
	        {
	        	if( cepr01030109Form.getParticipantVOList().get(i).getAge() != null ){
	        		if(cepr01030109Form.getParticipantVOList().get(i).getAge() > 0 ){
		           int li_usia = cepr01030109Form.getParticipantVOList().get(i).getAge();
		
		            Map<String, Object> paramsPeserta = new HashMap<String, Object>();
		            paramsPeserta.put("lsbs_id", "195");
		            paramsPeserta.put( "plan", cepr01030102Form.getRightPartOfBusinessCd() );    
		            //paramsPeserta.put( "kursId", cepr01030102Form.getCurrencyCd() );
		            paramsPeserta.put( "liUsia", li_usia );
		            premium = LazyConverter.toDouble(eproposalManager.selectPremiKid(paramsPeserta)) ;
		      
		            ldec_temp = FormatNumber.round( ( premium * ldec_pct ) * 0.9 , 2 );
		            ldec_total += ldec_temp;
	        	}
	        }}
        }       
        BigDecimal premium2 = new BigDecimal(ldec_total);       
                      
        return premium2;
    }
    
    public String getHcpProviderPlanList()
    {
             
        Integer jenis = null; 		
 			jenis = cepr01030102Form.getRightPartOfBusinessCd();
 			String plan = null;
     		     		 		
 		if(jenis==49 || jenis==37 || jenis==25){
     		plan="R-100";     		    		
     	} else if(jenis==50 || jenis==38 || jenis==26){
     		plan="R-200";     		 		
     	} else if(jenis==51 || jenis==39 || jenis==27){
     		plan="R-300";  		
     	} else if(jenis==52 || jenis==40 || jenis==28){
     		plan="R-400";		
     	}else if(jenis==53 || jenis==41 || jenis==29){
     		plan="R-500"; 		
     	}else if(jenis==54 || jenis==42 || jenis==30){
     		plan="R-600"; 		
     	}else if(jenis==55 || jenis==43 || jenis==31){
     		plan="R-700";    	
     	}else if(jenis==56 || jenis==44 || jenis==32){
     		plan="R-800";    		
     	}else if(jenis==57 || jenis==45 || jenis==33){
     		plan="R-900";		
     	}else if(jenis==58 || jenis==46 || jenis==34){
     		plan="R-1000";		
     	}else if(jenis==59 || jenis==47 || jenis==35){
     		plan="R-1500";   		
     	}else if(jenis==60 || jenis==48 || jenis==36){
     		plan="R-2000";
     	}
           
        return plan;
    }
    
    public String toMoneyWithCurrencyCd( BigDecimal amount )
    {
        String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
        return currencySymbol + editorUtil.convertToRoundedTwoDigits( amount );
    }
    
    public List<Map<String, Object>> getRiderHcpProviderParticipantsList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
      
            List<ParticipantVO> participantVOList = cepr01030109Form.getParticipantVOList();
            participantVOList = removeEmptyParticipantVOFromTheList( participantVOList );
            Map<String, Object> params;
            Integer idx = 0;
            for( ParticipantVO vo : participantVOList )
            {
                idx++;
                params = new HashMap<String, Object>();
                params.put( "idx_eka_sehat", idx.toString().concat( "." ) );
                params.put( "name_eka_sehat", vo.getName() );
                params.put( "age_eka_sehat", vo.getAge().toString() );
                result.add( params );
            }
       

        return result;
    }
    
}
