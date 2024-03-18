package id.co.sinarmaslife.eproposal.product.product01040107;

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
import id.co.sinarmaslife.eproposal.model.pb.S_biaya;
import id.co.sinarmaslife.eproposal.model.vo.Cepr00000000PerformanceVO;
import id.co.sinarmaslife.eproposal.model.vo.Cepr00000000YieldResultVO;
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

public class Cepr01040107Business extends Cepr01040000UlinkBusiness 
{
    protected final Log logger = LogFactory.getLog( getClass() );

    private Cepr00000000YieldResultVO yieldResultVO;

    public Cepr01040107Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
        setSekaligusRightPartCdArr( new Integer[]{ Cepr01040107Mapper.X1 } );
    }

    public List<Map<String, Object>> getOneRowList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        params = new HashMap<String, Object>();
        params.put( "dummy", "row1" );
        result.add( params );
        return result;
    }

    
    public Map<String, Object> of_get_coi_pa( String kurs_id, int umur_tt, BigDecimal premiTotalSekaligus, BigDecimal premiTotalTahunan, BigDecimal premiTotalSemesteran, BigDecimal premiTotalTriwulanan, BigDecimal premiTotalBulanan )
    {
    	// CI RIDER 4
    	Map<String, Object> paRider = new HashMap<String, Object>();
    	double sekaligus = 0;
    	double tahunan = 0;
    	double rateTahunan = 0;
    	double semesteran = 0;
    	double triwulanan = 0;
    	double bulanan = 0;
    	
    	if( istr_prop.rider_baru[ 1 ] > 0 ){
    	
	        int li_class = istr_prop.pa_class;
	        double maxUp = 2000000000.0;
	        if( istr_prop.umur_tt <= 16 ) li_class = 2;
	        Map<String, Object> params = new HashMap<String, Object>();
	        params.put( "paRisk", istr_prop.pa_risk );
	        params.put( "liClass", li_class );
	        if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) ){
	    		maxUp = 200000.0;
	    	}else{
	    		maxUp = 2000000000.0;
	    	}
	        double maxAmount = MathUtil.min( istr_prop.rider_baru[ 1 ] * istr_prop.up, maxUp );
	        rateTahunan = FormatNumber.round( (maxAmount / 1000) * LazyConverter.toDouble( eproposalManager.selectLdecRatePa( params ) ) * 0.1, 2);
	        tahunan = tahunan + rateTahunan;
	        // SEMESTERAN
	        semesteran = semesteran + (rateTahunan * 0.525);
	        // TRIWULANAN
	        triwulanan = triwulanan + (rateTahunan * 0.27);
	        // BULANAN
	        bulanan = bulanan + (rateTahunan * 0.1);
	    	//}
	    	
	    	// MAIN
	    	if("01".equals(kurs_id))
	    	{
	    		paRider.put("paRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus))+".00");
	    		paRider.put("paRiderTahunan", editorUtil.convertToRoundedNoDigit(new BigDecimal(tahunan))+".00");
	    		paRider.put("paRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		paRider.put("paRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		paRider.put("paRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		paRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(0, RoundingMode.HALF_UP));
	    		paRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(0, RoundingMode.HALF_UP));
	    		paRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		paRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		paRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	    	}else if("02".equals(kurs_id))
	    	{
	    		paRider.put("paRiderSekaligus", editorUtil.convertToRoundedTwoDigits(new BigDecimal(sekaligus)));
	    		paRider.put("paRiderTahunan", editorUtil.convertToRoundedTwoDigits(new BigDecimal(tahunan)));
	    		paRider.put("paRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		paRider.put("paRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		paRider.put("paRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		paRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(2, RoundingMode.HALF_UP));
	    		paRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(2, RoundingMode.HALF_UP));
	    		paRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		paRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		paRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	    	}
    	}
    	return paRider;
    }
    
    
    public Map<String, Object> of_get_coi_eka_sehat( String kurs_id, int umur_tt, BigDecimal premiTotalSekaligus, BigDecimal premiTotalTahunan, BigDecimal premiTotalSemesteran, BigDecimal premiTotalTriwulanan, BigDecimal premiTotalBulanan )
    {
    	// EKA SEHAT RIDER 4
    	Map<String, Object> ekaSehatRider = new HashMap<String, Object>();
    	double sekaligus = 0;
    	double tahunan = 0;
    	double rateTahunan = 0;
    	double semesteran = 0;
    	double triwulanan = 0;
    	double bulanan = 0;
    	
    	if( istr_prop.rider_baru[ 13 ] > 0 ){
	    	int li_usia;
	    	int li_jenis = istr_prop.rider_baru[13];
	    	
	//    	if not ab_single then
	//		li_jenis = istr_prop.rider_baru[13]
	//		li_plan = 823
	//		if istr_prop.rider_baru[15] > 0 then
	//			li_jenis = istr_prop.rider_baru[15]
	//			li_plan = 825
	//		End if
	//    	if (istr_prop.rider_baru[13] + istr_prop.rider_baru[15]) > 0 and not (Pos('183, 189, 193, 201', string(istr_prop.bisnis_id, '000')) > 0) Then
			
	        Map<String, Object> params = new HashMap<String, Object>();
	        params.put( "riderBaru", li_jenis );  
	        params.put( "kursId", istr_prop.kurs_id );
	        params.put( "liUsia", umur_tt );
	
	        rateTahunan = FormatNumber.round( ( LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) )), 2 );
	    			
	        if( istr_prop.eka_sehat.peserta > 0 )
	        {
	//    				if li_jenis > 15 then li_jenis -= 15
	//    				if li_jenis > 15 then li_jenis -= 15 // sekali lagi buat ekasehat kry 31-45
	        	if( li_jenis > 15 ) li_jenis -= 15;
	        	if( li_jenis > 15 ) li_jenis -= 15;// sekali lagi buat ekasehat kry 31-45
	        	for( int i = 1; i <= istr_prop.eka_sehat.peserta; i++ )
	        	{
	        		li_usia = istr_prop.eka_sehat.usia[ i ];
	
	        		Map<String, Object> paramsParticipants = new HashMap<String, Object>();
	        		paramsParticipants.put( "riderBaru", istr_prop.rider_baru[ 13 ]  );    
	        		paramsParticipants.put( "kursId", istr_prop.kurs_id );
	        		paramsParticipants.put( "liUsia", li_usia );
	        		double ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( paramsParticipants ) );
	        		double ldec_temp = FormatNumber.round( (ldec_rate * 0.975), 2 );
	        		rateTahunan += ldec_temp;
	        	}
	        }
	//    		}	
	        tahunan = tahunan + rateTahunan;
	        // SEMESTERAN
	        semesteran = semesteran + (rateTahunan * 0.65);
	        // TRIWULANAN
	        triwulanan = triwulanan + (rateTahunan * 0.35);
	        // BULANAN
	        bulanan = bulanan + (rateTahunan * 0.10);
	    	
	    	// MAIN
	    	//ekaSehatRider.put("ekaSehatRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus)));
	    	if("01".equals(kurs_id))
	        {
	    		ekaSehatRider.put("ekaSehatRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus))+".00");
	    		ekaSehatRider.put("ekaSehatRiderTahunan", editorUtil.convertToRoundedNoDigit(new BigDecimal(tahunan))+".00");
	    		ekaSehatRider.put("ekaSehatRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		ekaSehatRider.put("ekaSehatRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		ekaSehatRider.put("ekaSehatRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		ekaSehatRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(0, RoundingMode.HALF_UP));
	    		ekaSehatRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(0, RoundingMode.HALF_UP));
	    		ekaSehatRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		ekaSehatRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		ekaSehatRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	        }else if("02".equals(kurs_id))
	        {
	        	ekaSehatRider.put("ekaSehatRiderSekaligus", editorUtil.convertToRoundedTwoDigits(new BigDecimal(sekaligus)));
	    		ekaSehatRider.put("ekaSehatRiderTahunan", editorUtil.convertToRoundedTwoDigits(new BigDecimal(tahunan)));
	    		ekaSehatRider.put("ekaSehatRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		ekaSehatRider.put("ekaSehatRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		ekaSehatRider.put("ekaSehatRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		ekaSehatRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(2, RoundingMode.HALF_UP));
	    		ekaSehatRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(2, RoundingMode.HALF_UP));
	    		ekaSehatRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		ekaSehatRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		ekaSehatRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	        }
    	
    	}
    	return ekaSehatRider;
    }
    
    
    public Map<String, Object> of_get_coi_eka_sehat_il( String kurs_id, int umur_tt, BigDecimal premiTotalSekaligus, BigDecimal premiTotalTahunan, BigDecimal premiTotalSemesteran, BigDecimal premiTotalTriwulanan, BigDecimal premiTotalBulanan )
    {
    	// EKA SEHAT RIDER 4
    	Map<String, Object> ekaSehatIlRider = new HashMap<String, Object>();
    	double sekaligus = 0;
    	double tahunan = 0;
    	double rateTahunan = 0;
    	double semesteran = 0;
    	double triwulanan = 0;
    	double bulanan = 0;
    	
    	if( istr_prop.rider_baru[ 15 ] > 0 ){
	    	int li_usia;
	    	int li_jenis = istr_prop.rider_baru[15];
	    	
	        Map<String, Object> params = new HashMap<String, Object>();
	        params.put( "riderBaru", li_jenis );  
	        params.put( "kursId", istr_prop.kurs_id );
	        params.put( "liUsia", umur_tt );
	        rateTahunan = FormatNumber.round( ( LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ) )), 2 );
	    			
	        if( istr_prop.ekaSehatInnerLimit.peserta > 0 )
	        {
	        	if( li_jenis > 15 ) li_jenis -= 15;
	        	if( li_jenis > 15 ) li_jenis -= 15;// sekali lagi buat ekasehat kry 31-45
	        	for( int i = 1; i <= istr_prop.ekaSehatInnerLimit.peserta; i++ )
	        	{
	        		li_usia = istr_prop.ekaSehatInnerLimit.usia[ i ];
	        		Map<String, Object> paramsParticipants = new HashMap<String, Object>();
	        		paramsParticipants.put( "riderBaru", istr_prop.rider_baru[ 15 ]  );    
	        		paramsParticipants.put( "kursId", istr_prop.kurs_id );
	        		paramsParticipants.put( "liUsia", li_usia );
	        		double ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider(paramsParticipants) );
	        		double ldec_temp = FormatNumber.round( (ldec_rate * 0.975), 2 );
	        		rateTahunan += ldec_temp;
	        	}
	        }
	        tahunan = tahunan + rateTahunan;
	        // SEMESTERAN
	        semesteran = semesteran + (rateTahunan * 0.65);
	        // TRIWULANAN
	        triwulanan = triwulanan + (rateTahunan * 0.35);
	        // BULANAN
	        bulanan = bulanan + (rateTahunan * 0.10);
	    	
	    	// MAIN
	    	//ekaSehatIlRider.put("ekaSehatIlRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus)));
	    	if("01".equals(kurs_id))
	        {
	    		ekaSehatIlRider.put("ekaSehatIlRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus))+".00");
	    		ekaSehatIlRider.put("ekaSehatIlRiderTahunan", editorUtil.convertToRoundedNoDigit(new BigDecimal(tahunan))+".00");
	    		ekaSehatIlRider.put("ekaSehatIlRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		ekaSehatIlRider.put("ekaSehatIlRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		ekaSehatIlRider.put("ekaSehatIlRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		ekaSehatIlRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(0, RoundingMode.HALF_UP));
	    		ekaSehatIlRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(0, RoundingMode.HALF_UP));
	    		ekaSehatIlRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		ekaSehatIlRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		ekaSehatIlRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	        }else if("02".equals(kurs_id))
	        {
	        	ekaSehatIlRider.put("ekaSehatIlRiderSekaligus", editorUtil.convertToRoundedTwoDigits(new BigDecimal(sekaligus)));
	    		ekaSehatIlRider.put("ekaSehatIlRiderTahunan", editorUtil.convertToRoundedTwoDigits(new BigDecimal(tahunan)));
	    		ekaSehatIlRider.put("ekaSehatIlRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		ekaSehatIlRider.put("ekaSehatIlRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		ekaSehatIlRider.put("ekaSehatIlRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		ekaSehatIlRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(2, RoundingMode.HALF_UP));
	    		ekaSehatIlRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(2, RoundingMode.HALF_UP));
	    		ekaSehatIlRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		ekaSehatIlRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		ekaSehatIlRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	        }
    	}
    	
    	return ekaSehatIlRider;
    }
    
    
    public Map<String, Object> of_get_coi_term( String kurs_id, int umur_tt, BigDecimal premiTotalSekaligus, BigDecimal premiTotalTahunan, BigDecimal premiTotalSemesteran, BigDecimal premiTotalTriwulanan, BigDecimal premiTotalBulanan )
    {
    	// TERM RIDER 4
    	Map<String, Object> termRider = new HashMap<String, Object>();
    	double sekaligus = 0;
    	double tahunan = 0;
     	double rateSekaligus = 0;
    	double rateTahunan = 0;
    	double semesteran = 0;
    	double triwulanan = 0;
    	double bulanan = 0;
    	int li_jenis = 5;
    	//for(int i = umur_tt ; i < (umur_tt+5) ; i++){
//    	if Pos('096, 182, 194', string(istr_prop.bisnis_id, '000')) > 0 then li_jenis = 5
//    			//if istr_prop.bisnis_id = 142 then
//    			if Pos('142, 155, 158, 164, 174, 175, 184, 177, 207', string(istr_prop.bisnis_id, '000')) > 0 Then
//    				li_jenis = 3
//    				if ab_single then li_jenis = 4
//    			End if
//    			if Pos('142, 164', string(istr_prop.bisnis_id, '000')) > 0 and istr_prop.bisnis_no = 2 Then li_jenis = 6
    	if( istr_prop.rider_baru[ 12 ] > 0 ){
	    	if( istr_prop.bisnis_id == 96 || istr_prop.bisnis_id == 182 || istr_prop.bisnis_id == 194 ){
	    		li_jenis  = 5;
	    	}
	    	if( istr_prop.bisnis_id == 142 || istr_prop.bisnis_id == 155 || istr_prop.bisnis_id == 158 || istr_prop.bisnis_id == 164 || istr_prop.bisnis_id == 174
	    			|| istr_prop.bisnis_id == 175 || istr_prop.bisnis_id == 184 || istr_prop.bisnis_id == 177 || istr_prop.bisnis_id == 207 ){
	    		li_jenis = 3;
	//    		if ab_single then li_jenis = 4
	    	}
	    	if( ( istr_prop.bisnis_id == 142 || istr_prop.bisnis_id == 164 ) && istr_prop.bisnis_no == 2 ){
	    		li_jenis  = 6;
	    	}
	    			rateSekaligus = selectLdecRateTerm(4,kurs_id, umur_tt);
	    		// TAHUNAN
	    			rateTahunan = selectLdecRateTerm(li_jenis, kurs_id, umur_tt);
	//    			ldec_temp = Round((istr_prop.up_term / 1000) * ldec_rate, 2)
	    			rateTahunan = FormatNumber.round((istr_prop.up_term / 1000) * rateTahunan, 2);
	    			rateSekaligus = FormatNumber.round((istr_prop.up_term / 1000) * rateSekaligus, 2);
	    		tahunan = tahunan + rateTahunan;
	    		sekaligus= sekaligus + rateSekaligus;
	    		// SEMESTERAN
	    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_SEMESTERAN;
	    		semesteran = semesteran + (rateTahunan * 0.525);
	    		// TRIWULANAN
	    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_TRIWULANAN;
	    		triwulanan = triwulanan + (rateTahunan * 0.27);
	    		// BULANAN
	    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_BULANAN;
	    		bulanan = bulanan + (rateTahunan * 0.1);
	    	//}
	    	
	    	// MAIN
	    	if("01".equals(kurs_id))
	        {
	    		termRider.put("termRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus))+".00");
	    		termRider.put("termRiderTahunan", editorUtil.convertToRoundedNoDigit(new BigDecimal(tahunan))+".00");
	    		termRider.put("termRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		termRider.put("termRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		termRider.put("termRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		termRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(0, RoundingMode.HALF_UP));
	    		termRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(0, RoundingMode.HALF_UP));
	    		termRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		termRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		termRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	        }else if("02".equals(kurs_id))
	        {
	        	termRider.put("termRiderSekaligus", editorUtil.convertToRoundedTwoDigits(new BigDecimal(sekaligus)));
	    		termRider.put("termRiderTahunan", editorUtil.convertToRoundedTwoDigits(new BigDecimal(tahunan)));
	    		termRider.put("termRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		termRider.put("termRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		termRider.put("termRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		termRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(2, RoundingMode.HALF_UP));
	    		termRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(2, RoundingMode.HALF_UP));
	    		termRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		termRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		termRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	        }
    	}
    	return termRider;
    }
    
    
    public Map<String, Object> of_get_coi_ci( String kurs_id, int umur_tt, BigDecimal premiTotalSekaligus, BigDecimal premiTotalTahunan, BigDecimal premiTotalSemesteran, BigDecimal premiTotalTriwulanan, BigDecimal premiTotalBulanan )
    {
    	// CI RIDER 4
    	Map<String, Object> ciRider = new HashMap<String, Object>();
    	double sekaligus = 0;
    	double tahunan = 0;
    	double rateTahunan = 0;
    	double rateSekaligus = 0;
    	double semesteran = 0;
    	double triwulanan = 0;
    	double bulanan = 0;
    	double ldec_persen_ci = 0.5;
    	if( istr_prop.rider_baru[ 4 ] > 0 ){
	    	Map<String, Object> params = new HashMap<String, Object>();
	    	params.put( "kursId", istr_prop.kurs_id );
	    	params.put( "liUsia", umur_tt );
	    	double persentase = 50.0;
	    	if(cepr01030103Form.getCiChooseCd() != null) persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getCiChooseCd())) / 100.0;
	    	double upCi = ( istr_prop.up * persentase );
	    	double maxUp;
	    	if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) ){
	    		maxUp = 200000.0;
	    	}else{
	    		maxUp = 2000000000.0;
	    	}
	    	double temp = MathUtil.min( upCi, maxUp );
	    	
	    	int li_jenis = istr_prop.ins_per;
	//    			if Pos('164, 174, 177, 207', string(istr_prop.bisnis_id, '000')) > 0 then li_jenis = 4
	//    			if Pos('096, 182, 194', string(istr_prop.bisnis_id, '000')) > 0 then li_jenis = 6
	    	if( istr_prop.bisnis_id == 164 || istr_prop.bisnis_id == 174 || istr_prop.bisnis_id == 177 || istr_prop.bisnis_id == 207 ){
	    		li_jenis = 4;
	    	}
	    	if( istr_prop.bisnis_id == 96 || istr_prop.bisnis_id == 182 || istr_prop.bisnis_id == 194 ){
	    		li_jenis = 6;
	    	}
	//    	if Pos('142, 155, 158, 184, 175, 177', string(istr_prop.bisnis_id, '000')) > 0 Then
	    	if( istr_prop.bisnis_id == 142 || istr_prop.bisnis_id == 155 || istr_prop.bisnis_id == 158 || istr_prop.bisnis_id == 184 || istr_prop.bisnis_id == 175 || istr_prop.bisnis_id == 177 ){
	    		li_jenis = 4;
	//    		if ab_single then li_jenis = 5
	    	}
	//    	if Pos('142, 164', string(istr_prop.bisnis_id, '000')) > 0 and istr_prop.bisnis_no = 2 Then li_jenis = 7
	    	if( ( istr_prop.bisnis_id == 142 || istr_prop.bisnis_id == 164 ) &&  istr_prop.bisnis_no == 2 ){
	    		 li_jenis = 7;
	    	}
	    		// TAHUNAN
	    		rateTahunan = selectLdecRateCiRider(li_jenis, kurs_id, umur_tt);
	    		rateSekaligus = selectLdecRateCiRider(5, kurs_id, umur_tt);
	//    		if istr_prop.rider_baru[4] >= 50 then ldec_persen_ci = istr_prop.rider_baru[4]/100
	//    				ldec_temp = Round((istr_prop.up * ldec_persen_ci / 1000) * ldec_rate, 2)
	    	    if( cepr01030103Form.getCiChooseCd() != null) ldec_persen_ci = LazyConverter.toDouble(new BigDecimal(cepr01030103Form.getCiChooseCd()))/100;
	    		rateTahunan = FormatNumber.round((istr_prop.up * ldec_persen_ci / 1000) * rateTahunan, 2);
	    		rateSekaligus = FormatNumber.round((istr_prop.up * ldec_persen_ci / 1000) * rateSekaligus, 2);
	    		
	    		sekaligus = sekaligus+rateSekaligus;
	    		tahunan = tahunan + rateTahunan;
	    		// SEMESTERAN
	    		semesteran = semesteran + (rateTahunan * 0.525);
	    		// TRIWULANAN
	    		triwulanan = triwulanan + (rateTahunan * 0.27);
	    		// BULANAN
	    		bulanan = bulanan + (rateTahunan * 0.1);
	    	
	    	// MAIN
	    	if("01".equals(kurs_id))
	    	{
	    		ciRider.put("ciRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus))+".00");
	    		ciRider.put("ciRiderTahunan", editorUtil.convertToRoundedNoDigit(new BigDecimal(tahunan))+".00");
	    		ciRider.put("ciRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		ciRider.put("ciRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		ciRider.put("ciRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		ciRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(0, RoundingMode.HALF_UP));
	    		ciRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(0, RoundingMode.HALF_UP));
	    		ciRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		ciRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		ciRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	    	}else if("02".equals(kurs_id))
	    	{
	    		ciRider.put("ciRiderSekaligus", editorUtil.convertToRoundedTwoDigits(new BigDecimal(sekaligus)));
	    		ciRider.put("ciRiderTahunan", editorUtil.convertToRoundedTwoDigits(new BigDecimal(tahunan)));
	    		ciRider.put("ciRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		ciRider.put("ciRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		ciRider.put("ciRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		ciRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(2, RoundingMode.HALF_UP));
	    		ciRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(2, RoundingMode.HALF_UP));
	    		ciRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		ciRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		ciRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	    	}
    	}
    	return ciRider;
    }
    public Map<String, Object> of_get_coi_hcp_family( int jenis, String kurs_id, int umur_tt, BigDecimal premiTotalSekaligus, BigDecimal premiTotalTahunan, BigDecimal premiTotalSemesteran, BigDecimal premiTotalTriwulanan, BigDecimal premiTotalBulanan )
    {
    	// HCP FAMILY RIDER 4
    	Map<String, Object> hcpRider = new HashMap<String, Object>();
    	double sekaligus = 0;
    	double tahunan = 0;
    	double rateTahunan = 0;
    	double rateSekaligus = 0;
    	double semesteran = 0;
    	double triwulanan = 0;
    	double bulanan = 0;
    	int li_usia = istr_prop.umur_tt;
    	int li_jenis = 0;
    	
    	// TAHUNAN
    	if( istr_prop.rider_baru[11] > 0 && li_usia < 65 ){
    		li_jenis = istr_prop.rider_baru[11] + 280;
//        		if Pos('096, 182, 194', string(istr_prop.bisnis_id, '000')) > 0 then li_jenis = istr_prop.rider_baru[11] + 380
//        		if Pos('142, 164', string(istr_prop.bisnis_id, '000')) > 0 and istr_prop.bisnis_no = 2 Then li_jenis = istr_prop.rider_baru[11] + 430
    		if( istr_prop.bisnis_id == 96 || istr_prop.bisnis_id == 182 || istr_prop.bisnis_id == 194 ){
    			li_jenis = istr_prop.rider_baru[11] + 380;
    		}
    		if( (istr_prop.bisnis_id == 142 || istr_prop.bisnis_id == 164) && istr_prop.bisnis_no == 2 ){
    			li_jenis = istr_prop.rider_baru[11] + 430;
    		}
    		rateTahunan = selectLdecRateHcpFamilyRider(li_jenis, kurs_id, li_usia);    		    		
    		tahunan = tahunan + rateTahunan;    		
    		
    		// SEMESTERAN
    		semesteran = semesteran + (rateTahunan * 0.525);
    		// TRIWULANAN
    		triwulanan = triwulanan + (rateTahunan * 0.27);
    		// BULANAN
    		bulanan = bulanan + (rateTahunan * 0.1);
    		//}
    		
    		// MAIN
    		//hcpRider.put("hcpFamilyRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus)));
    		if("01".equals(kurs_id))
    		{
    			hcpRider.put("hcpFamilyRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus))+".00");
	    		hcpRider.put("hcpFamilyRiderTahunan", editorUtil.convertToRoundedNoDigit(new BigDecimal(tahunan))+".00");
	    		hcpRider.put("hcpFamilyRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		hcpRider.put("hcpFamilyRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		hcpRider.put("hcpFamilyRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		hcpRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(0, RoundingMode.HALF_UP));
	    		hcpRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(0, RoundingMode.HALF_UP));
	    		hcpRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		hcpRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		hcpRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	        }else if("02".equals(kurs_id))
	        {
	        	hcpRider.put("hcpFamilyRiderSekaligus", editorUtil.convertToRoundedTwoDigits(new BigDecimal(sekaligus)));
	    		hcpRider.put("hcpFamilyRiderTahunan", editorUtil.convertToRoundedTwoDigits(new BigDecimal(tahunan)));
	    		hcpRider.put("hcpFamilyRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		hcpRider.put("hcpFamilyRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		hcpRider.put("hcpFamilyRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		hcpRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(2, RoundingMode.HALF_UP));
	    		hcpRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(2, RoundingMode.HALF_UP));
	    		hcpRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		hcpRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		hcpRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	        }
    	}
    	return hcpRider;
    }

}
