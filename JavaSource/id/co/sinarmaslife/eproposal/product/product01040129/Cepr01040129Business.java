package id.co.sinarmaslife.eproposal.product.product01040129;

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

public class Cepr01040129Business extends Cepr01040000UlinkBusiness 
{
    protected final Log logger = LogFactory.getLog( getClass() );

    private Cepr00000000YieldResultVO yieldResultVO;

    public Cepr01040129Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
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
         	if( jenis == 16 || jenis == 91 ){ // plan A
         		jenisSantunan="Plan A-100";
         		biayaInap="100,000";
         		biayaIcu="200,000";
         		limitKejadian="12,500,000";
         		maksPenggantian="50,000,000";
         	}else if( jenis == 17 || jenis == 92 ){ // plan B
         		jenisSantunan="Plan B-150";
         		biayaInap="150,000";
         		biayaIcu="300,000";
         		limitKejadian="18,750,000";
         		maksPenggantian="75,000,000";
         	}else if( jenis == 18 || jenis == 93 ){ // plan C
         		jenisSantunan="Plan C-200";
         		biayaInap="200,000";
         		biayaIcu="400,000";
         		limitKejadian="25,000,000";
         		maksPenggantian="100,000,000";
         	}else if( jenis == 19 || jenis == 94 ){ // plan D
         		jenisSantunan="Plan D-250";
         		biayaInap="250,000";
         		biayaIcu="500,000";
         		limitKejadian="31,250,000";
         		maksPenggantian="125,000,000";
         	}else if( jenis == 20 || jenis == 95 ){ // plan E
         		jenisSantunan="Plan E-300";
         		biayaInap="300,000";
         		biayaIcu="600,000";
         		limitKejadian="37,500,000";
         		maksPenggantian="150,000,000";
         	}else if( jenis == 21 || jenis == 96 ){ // plan F
         		jenisSantunan="Plan F-350";
         		biayaInap="350,000";
         		biayaIcu="700,000";
         		limitKejadian="43,750,000";
         		maksPenggantian="175,000,000";
         	}else if( jenis == 22 || jenis == 97 ){ // plan G
         		jenisSantunan="Plan G-400";
         		biayaInap="400,000";
         		biayaIcu="800,000";
         		limitKejadian="50,000,000";
         		maksPenggantian="200,000,000";
         	}else if( jenis == 23 || jenis == 98 ){ // plan H
         		jenisSantunan="Plan H-500";
         		biayaInap="500,000";
         		biayaIcu="1,000,000";
         		limitKejadian="100,000,000";
         		maksPenggantian="400,000,000";
         	}else if( jenis == 24 || jenis == 99 ){ // plan I
         		jenisSantunan="Plan I-600";
         		biayaInap="600,000";
         		biayaIcu="1,200,000";
         		limitKejadian="125,000,000";
         		maksPenggantian="500,000,000";
         	}else if( jenis == 25 || jenis == 100 ){ // plan J
         		jenisSantunan="Plan J-700";
         		biayaInap="700,000";
         		biayaIcu="1,400,000";
         		limitKejadian="150,000,000";
         		maksPenggantian="600,000,000";
         	}else if( jenis == 26 || jenis == 101 ){ // plan K
         		jenisSantunan="Plan K-800";
         		biayaInap="800,000";
         		biayaIcu="1,600,000";
         		limitKejadian="175,000,000";
         		maksPenggantian="700,000,000";
         	}else if( jenis == 27 || jenis == 102 ){ // plan L
         		jenisSantunan="Plan L-900";
         		biayaInap="900,000";
         		biayaIcu="1,800,000";
         		limitKejadian="200,000,000";
         		maksPenggantian="800,000,000";
         	}else if( jenis == 28 || jenis == 103 ){ // plan M
         		jenisSantunan="Plan M-1000";
         		biayaInap="1,000,000";
         		biayaIcu="2,000,000";
         		limitKejadian="225,000,000";
         		maksPenggantian="900,000,000";
         	}else if( jenis == 29 || jenis == 104 ){ // plan N
         		jenisSantunan="Plan N-1500";
         		biayaInap="1,500,000";
         		biayaIcu="3,000,000";
         		limitKejadian="350,000,000";
         		maksPenggantian="1,400,000,000";
         	}else if( jenis == 30 || jenis == 105 ){ // plan O
         		jenisSantunan="Plan O-2000";
         		biayaInap="2,000,000";
         		biayaIcu="4,000,000";
         		limitKejadian="475,000,000";
         		maksPenggantian="1,900,000,000";
         	}else if( jenis == 31 ){ // plan P
         		jenisSantunan="Plan P-5000";
         		biayaInap="5,000,000";
         		biayaIcu="4,000,000";
         		limitKejadian="475,000,000";
         		maksPenggantian="5,000,000,000";
         	}else if( jenis == 32 ){ // plan Q
         		jenisSantunan="Plan Q-7500";
         		biayaInap="7,500,000";
         		biayaIcu="4,000,000";
         		limitKejadian="475,000,000";
         		maksPenggantian="6,000,000,000";
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
             
             if( jenis == 31 || jenis == 32){
	             params = new HashMap<String, Object>();
	         	 params.put( "no", "10"  );     
	         	 params.put( "subject", "Perawat Pribadi di Rumah" );
	             params.put( "descr", "As Charge"  );         
	             result.add( params );
             } 
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
        for( ParticipantVO vo : list )
        {
            name = vo.getName();
            name = name == null? "" : name.trim();
            if( !"".equals( name ) )
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
    
}
