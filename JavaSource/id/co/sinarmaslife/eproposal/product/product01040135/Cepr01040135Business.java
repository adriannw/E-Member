package id.co.sinarmaslife.eproposal.product.product01040135;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040135Business
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
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
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

public class Cepr01040135Business extends Cepr01040000UlinkBusiness 
{
    protected final Log logger = LogFactory.getLog( getClass() );

    private Cepr00000000YieldResultVO yieldResultVO;

    public Cepr01040135Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
        setSekaligusRightPartCdArr( new Integer[]{ Cepr01040135Mapper.X1 } );
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

    public Map<String, Object> of_get_coi_term( String kurs_id, int umur_tt, BigDecimal premiTotalSekaligus, BigDecimal premiTotalTahunan, BigDecimal premiTotalSemesteran, BigDecimal premiTotalTriwulanan, BigDecimal premiTotalBulanan )
    {
    	// TERM RIDER 4
    	Map<String, Object> termRider = new HashMap<String, Object>();
    	double sekaligus = 0;
    	double tahunan = 0;
    	double rateTahunan = 0;
    	double semesteran = 0;
    	double triwulanan = 0;
    	double bulanan = 0;
    	
    	//for(int i = umur_tt ; i < (umur_tt+5) ; i++){
    		// SEKALIGUS
    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_SEKALIGUS;
    		if("01".equals(kurs_id))
    		{
    			sekaligus = FormatNumber.round((istr_prop.up_term / 1000) * selectLdecRateTerm(4, kurs_id, umur_tt), 2);
    		}else if("02".equals(kurs_id))
    		{
    			sekaligus = (istr_prop.up_term / 1000) * selectLdecRateTerm(4, kurs_id, umur_tt);
    		}
    		// TAHUNAN
    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_TAHUNAN;
    		if("01".equals(kurs_id))
    		{
    			rateTahunan = FormatNumber.round((istr_prop.up_term / 1000) * selectLdecRateTerm(3, kurs_id, umur_tt), 2);
    		}else if("02".equals(kurs_id))
    		{
    			rateTahunan = (istr_prop.up_term / 1000) * selectLdecRateTerm(3, kurs_id, umur_tt);
    		}
    		tahunan = tahunan + rateTahunan;
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
    	
    	return termRider;
    }
    
    public Map<String, Object> of_get_coi_ci( String kurs_id, int umur_tt, BigDecimal premiTotalSekaligus, BigDecimal premiTotalTahunan, BigDecimal premiTotalSemesteran, BigDecimal premiTotalTriwulanan, BigDecimal premiTotalBulanan )
    {
    	// CI RIDER 4
    	Map<String, Object> ciRider = new HashMap<String, Object>();
    	double sekaligus = 0;
    	double tahunan = 0;
    	double rateTahunan = 0;
    	double semesteran = 0;
    	double triwulanan = 0;
    	double bulanan = 0;
    	
    	//for(int i = umur_tt ; i < (umur_tt+5) ; i++){
    		// SEKALIGUS
    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_SEKALIGUS;
    		if("01".equals(kurs_id))
    		{
    			sekaligus = FormatNumber.round( (istr_prop.up * 0.5 / 1000) * selectLdecRateCiRider(5, kurs_id, umur_tt), 2);
    		}else if("02".equals(kurs_id))
    		{
    			sekaligus = (istr_prop.up * 0.5 / 1000) * selectLdecRateCiRider(5, kurs_id, umur_tt);
    		}
    		// TAHUNAN
    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_TAHUNAN;
    		if("01".equals(kurs_id))
    		{
    			rateTahunan = FormatNumber.round( (istr_prop.up * 0.5 / 1000) * selectLdecRateCiRider(4, kurs_id, umur_tt), 2);
    		}else if("02".equals(kurs_id))
    		{
    			rateTahunan = (istr_prop.up * 0.5 / 1000) * selectLdecRateCiRider(4, kurs_id, umur_tt);
    		}
    		tahunan = tahunan + rateTahunan;
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
    	
    	return ciRider;
    }
    
    public Map<String, Object> of_get_coi_hcp_family( int jenis, String kurs_id, int umur_tt, BigDecimal premiTotalSekaligus, BigDecimal premiTotalTahunan, BigDecimal premiTotalSemesteran, BigDecimal premiTotalTriwulanan, BigDecimal premiTotalBulanan )
    {
    	// HCP FAMILY RIDER 4
    	Map<String, Object> hcpRider = new HashMap<String, Object>();
    	double sekaligus = 0;
    	double tahunan = 0;
    	double rateTahunan = 0;
    	double semesteran = 0;
    	double triwulanan = 0;
    	double bulanan = 0;
    	
    	//for(int i = umur_tt ; i < (umur_tt+5) ; i++){
    		// SEKALIGUS
    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_SEKALIGUS;
    		
    		if("01".equals(kurs_id))
    		{
    			//sekaligus = selectLdecRateHcpFamilyRider(jenis, kurs_id, umur_tt) * 100;
    		}else if("02".equals(kurs_id))
    		{
    			//sekaligus = selectLdecRateHcpFamilyRider(jenis, kurs_id, umur_tt) * 100;
    		}
    		// TAHUNAN
    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_TAHUNAN;
    		if("01".equals(kurs_id))
    		{
    			rateTahunan = selectLdecRateHcpFamilyRider(jenis, kurs_id, umur_tt);
    		}else if("02".equals(kurs_id))
    		{
    			rateTahunan = selectLdecRateHcpFamilyRider(jenis, kurs_id, umur_tt);
    		}
    		tahunan = tahunan + rateTahunan;
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
    	
    	return hcpRider;
    }

}
