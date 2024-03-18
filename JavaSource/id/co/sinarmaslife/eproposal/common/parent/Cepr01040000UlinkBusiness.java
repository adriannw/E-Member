package id.co.sinarmaslife.eproposal.common.parent;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040000UlinkBusiness
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 14, 2008 11:27:29 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.common.pb.PbConverter;
import id.co.sinarmaslife.eproposal.common.pb.PbFunction;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.pb.Istr_prop;
import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.eproposal.model.pb.N_riders;
import id.co.sinarmaslife.eproposal.model.pb.S_biaya;
import id.co.sinarmaslife.eproposal.model.vo.Cepr00000000PerformanceVO;
import id.co.sinarmaslife.eproposal.model.vo.Cepr00000000YieldResultVO;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.eproposal.product.product01040228.Cepr01040228Mapper;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030103Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030104Form;
import id.co.sinarmaslife.standard.StandardConst;
import id.co.sinarmaslife.standard.model.vo.AssurancePackageList;
import id.co.sinarmaslife.standard.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040000UlinkBusiness extends Cepr01040000ProductBusiness
{
    protected final Log logger = LogFactory.getLog( getClass() );

    protected int insuredAgeLimit;
    protected String[] investmentTitleArr;
    protected String[][] investmentTitleAndDescrArr;
    protected Istr_prop istr_prop;

	private int ldec_max;

    public Cepr01040000UlinkBusiness( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
        istr_prop = PbConverter.get_istr_prop( command, 0 );
    }

    public Integer[] getSekaligusRightPartCdArr()
    {
        return sekaligusRightPartCdArr;
    }

    public void setSekaligusRightPartCdArr( Integer[] sekaligusRightPartCdArr )
    {
        this.sekaligusRightPartCdArr = sekaligusRightPartCdArr;
    }

    public Integer[] getBerkalaRightPartCdArr()
    {
        return berkalaRightPartCdArr;
    }

    public void setBerkalaRightPartCdArr( Integer[] berkalaRightPartCdArr )
    {
        this.berkalaRightPartCdArr = berkalaRightPartCdArr;
    }

    public int getInsuredAgeLimit()
    {
        return insuredAgeLimit;
    }

    public void setInsuredAgeLimit( int insuredAgeLimit )
    {
        this.insuredAgeLimit = insuredAgeLimit;
    }

    public String[] getInvestmentTitleArr()
    {
        return investmentTitleArr;
    }

    public void setInvestmentTitleArr( String[] investmentTitleArr )
    {
        this.investmentTitleArr = investmentTitleArr;
    }

    public String[][] getInvestmentTitleAndDescrArr()
    {
        return investmentTitleAndDescrArr;
    }

    public void setInvestmentTitleAndDescrArr( String[][] investmentTitleAndDescrArr )
    {
        this.investmentTitleAndDescrArr = investmentTitleAndDescrArr;
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

    public List<Map<String, Object>> getNoteSekaligusRowList()
    {
        List<Map<String, Object>> result;
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        if( Hardcode.PAY_MODE_CD_SEKALIGUS == cepr01030102Form.getPaymentModeCd() )
            result = getOneRowList();
        else
            result = new ArrayList<Map<String, Object>>();
        return result;
    }

    public List<Map<String, Object>> getNoteBerkalaRowList()
    {
        List<Map<String, Object>> result;
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        if( Hardcode.PAY_MODE_CD_SEKALIGUS != cepr01030102Form.getPaymentModeCd() )
            result = getOneRowList();
        else
            result = new ArrayList<Map<String, Object>>();
        return result;
    }

    public double of_get_coi_basic( int ai_th )
    {
//        logger.info( "*-*-*-* Cepr01040201Business.of_get_coi_basic" );
        double ldec_total = 0, ldec_temp;
        double ldec_rate;
        int li_usia;
        String li_sex;  //IGA - PROJECT NEW COI
        li_sex = cepr01030101Form.getInsuredSexCd();
        if(li_sex.equals("L")){
        	li_sex = "1".trim();
        }else if(li_sex.equals("P")){
        	li_sex = "0".trim();
        }else{
        	li_sex = "9".trim();
        }
        li_usia = istr_prop.umur_tt + ai_th - 1;
        if(li_usia == 0) li_usia=1;
        Map<String, Object> par = new HashMap<String, Object>();
        par.put( "liUsia", li_usia );
        par.put("liSex", li_sex);
        ldec_rate = eproposalManager.selectLdecRateToGetCoiBasic( par ).doubleValue();
        // DONE
        ldec_temp = FormatNumber.round( ( ( istr_prop.up / 1000 ) * ldec_rate ) * 0.1, 2, istr_prop.kurs_id );
        ldec_total += ldec_temp;

//        logger.info( "*-*-*-* ldec_total = " + ldec_total );
        return ldec_total;
    }
    
    // this is the distract factor to value in illustration
    // don't forget to add this when new rider added
    public double of_get_coi( int ai_th )
    {
        double ldec_total = 0, ldec_temp;
        double ldec_rate;
        int li_usia;
        String li_sex;  //IGA - PROJECT NEW COI
        li_sex = cepr01030101Form.getInsuredSexCd();
        if(li_sex.equals("L")){
        	li_sex = "1".trim();
        }else if(li_sex.equals("P")){
        	li_sex = "0".trim();
        }else{
        	li_sex = "9".trim();
        }
        li_usia = istr_prop.umur_tt + ai_th - 1;
        if(li_usia == 0) li_usia=1;
        
        Map<String, Object> par = new HashMap<String, Object>();
        par.put( "liUsia", li_usia );
        par.put("liSex", li_sex);
        ldec_rate = eproposalManager.selectLdecRateToGetCoi( par ).doubleValue();
            
        ldec_temp = FormatNumber.round( ( ( ( istr_prop.up / 1000 ) * ldec_rate ) * 0.1 ), 2, istr_prop.kurs_id );
        ldec_total += ldec_temp;
        
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PA, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_HCP, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_TPD, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_WP_60_TPD, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PB_25_TPD, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_WP_60_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PB_25_CI_DEATH, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PB_25_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PAYOR_SPOUSE, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_HCP_FAMILY, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_TERM_RIDER, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_WAIVER_TPD_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_EKA_SEHAT, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_EKA_SEHAT_IL, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_HCP_PROVIDER, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PAYOR_TPD_CI_DEATH, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_LADIES_INS, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_LADIES_HCP, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_LADIES_MED_EXPENSE, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_LADIES_MED_EXPENSE_INNER_LIMIT, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PAYOR_5_TPD_10_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_WAIVER_5_TPD_10_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_SCHOLAR, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_BABY, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_EARLY_STAGE_CI99, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_MEDICAL_PLUS, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_MEDICAL_EXTRA, ai_th );
        /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_TERM_5575_RIDER, ai_th );
        return ldec_total;
    }   
    
    public double of_get_coi_basic_119( String kurs, int ai_th )
    {
        logger.info( "*-*-*-* Cepr01040201Business.of_get_coi_basic" );
        double ldec_total = 0, ldec_temp;
        double ldec_rate;
        int li_usia;
        li_usia = istr_prop.umur_tt; // + ai_th - 1;

        Map<String, Object> par = new HashMap<String, Object>();
        par.put( "liUsia", li_usia );
        par.put( "kurs_id", kurs );
        ldec_rate = eproposalManager.selectLdecRateToGetCoiBasic( par ).doubleValue();

        ldec_temp = FormatNumber.round( ( ( istr_prop.up / 1000 ) * ldec_rate ) * 0.1, 2, istr_prop.kurs_id );
        ldec_total += ldec_temp;

        logger.info( "*-*-*-* ldec_total = " + ldec_total );
        return ldec_total;
    }
    
    public double of_get_coi_basic_190( int ai_th )
    {
     
        double ldec_total = 0, ldec_temp;
        double ldec_rate;
        int li_usia;
        String li_sex;  //IGA - PROJECT NEW COI
        li_sex = cepr01030101Form.getInsuredSexCd();
        if(li_sex.equals("L")){
        	li_sex = "1".trim();
        }else if(li_sex.equals("P")){
        	li_sex = "0".trim();
        }else{
        	li_sex = "9".trim();
        }
        li_usia = istr_prop.umur_tt + ai_th - 1;
        if(li_usia == 0) li_usia=1;
        Map<String, Object> par = new HashMap<String, Object>();
        par.put( "liUsia", li_usia );
        par.put("liSex", li_sex);
        ldec_rate = eproposalManager.selectLdecRateToGetCoi_190( par ).doubleValue();
        //DONE
        ldec_temp = FormatNumber.round( ( ( istr_prop.up / 1000 ) * ldec_rate ) * 0.1, 2, istr_prop.kurs_id );
        ldec_total += ldec_temp;

//        logger.info( "*-*-*-* ldec_total = " + ldec_total );
        return ldec_total;
    }
    
    public double of_get_coi_190( int ai_th )
    {
        double ldec_total = 0, ldec_temp;
        double ldec_rate;
        int li_usia;
        String li_sex;  //IGA - PROJECT NEW COI
        li_sex = cepr01030101Form.getInsuredSexCd();
        if(li_sex.equals("L")){
        	li_sex = "1".trim();
        }else if(li_sex.equals("P")){
        	li_sex = "0".trim();
        }else{
        	li_sex = "9".trim();
        }
        li_usia = istr_prop.umur_tt + ai_th - 1;
        if(li_usia == 0) li_usia=1;
        // Extra (COI/Mortalita/Premi)/ JOB
        double ldec_extra_premi = 0, ldec_extra_job = 0;
        
        Map<String, Object> par = new HashMap<String, Object>();
        par.put( "liUsia", li_usia );
        par.put("liSex", li_sex);
        ldec_rate = eproposalManager.selectLdecRateToGetCoi_190( par ).doubleValue();

        /*
        //Set Extra COI/Mortalita/Premi	
        if(cepr01030102Form.getExtraPremium().compareTo(new BigDecimal(0))>=1){    
        	//ldec_rate = ldec_rate * (1+(cepr01030102Form.getExtraPremium().doubleValue()/100));        	
        	double lem_yrt;  
            par.put( "liUsia", li_usia );
            lem_yrt = eproposalManager.selectRateExtMortalita( par ).doubleValue();
        	ldec_extra_premi = ((cepr01030102Form.getExtraPremium().doubleValue() *0.01) * lem_yrt * 0.001) * istr_prop.up * 0.1;
        }        
        //Set Extra JOB	
        if(cepr01030102Form.getExtraJob().compareTo(new BigDecimal(0))>=1){    
        	ldec_extra_job = ((cepr01030102Form.getExtraJob().doubleValue() * 0.001) * istr_prop.up) * 0.1 ;        	        	        	
        }*/
          
        
          ldec_temp = FormatNumber.round( ( ( ( istr_prop.up / 1000 ) * ldec_rate ) * 0.1 ), 2, istr_prop.kurs_id );      
     //   ldec_temp = FormatNumber.round( ( ( ( istr_prop.up / 1000 ) * ldec_rate ) * 0.1 ) + ldec_extra_premi + ldec_extra_job , 2, istr_prop.kurs_id );
        ldec_total += ldec_temp;

        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PA, ai_th );
        //Set Extra (COI/Mortalita/Premi) RIDER
        //ldec_total = ldec_total + of_get_extra_premi_rider( Hardcode.AI_JENIS_PA, ai_th );        
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_HCP, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_TPD, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_WP_60_TPD, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PB_25_TPD, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_WP_60_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PB_25_CI_DEATH, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PB_25_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PAYOR_SPOUSE, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_HCP_FAMILY, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_TERM_RIDER, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_WAIVER_TPD_CI, ai_th );
        //Set Extra (COI/Mortalita/Premi) RIDER
        //ldec_total = ldec_total + of_get_extra_premi_rider( Hardcode.AI_JENIS_WAIVER_TPD_CI, ai_th ); 
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_EKA_SEHAT, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_EKA_SEHAT_IL, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_HCP_PROVIDER, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PAYOR_TPD_CI_DEATH, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_LADIES_INS, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_LADIES_HCP, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_LADIES_MED_EXPENSE, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_LADIES_MED_EXPENSE_INNER_LIMIT, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PAYOR_5_TPD_10_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_WAIVER_5_TPD_10_CI, ai_th );
        //Set Extra (COI/Mortalita/Premi) RIDER
        //ldec_total = ldec_total + of_get_extra_premi_rider( Hardcode.AI_JENIS_WAIVER_5_TPD_10_CI, ai_th ); 
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_SCHOLAR, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_BABY, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_EARLY_STAGE_CI99, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_MEDICAL_PLUS, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_MEDICAL_EXTRA, ai_th );
        /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_TERM_5575_RIDER, ai_th );
        return ldec_total;
    }
    
    
    public double of_get_coi_119( String kurs, int ai_th )
    {
        double ldec_total = 0, ldec_temp;
        double ldec_rate;
        int li_usia;
        li_usia = istr_prop.umur_tt; //+ ai_th - 1;

        Map<String, Object> par = new HashMap<String, Object>();
        String li_sex; //IGA - PROJECT NEW COI
        li_sex = cepr01030101Form.getInsuredSexCd();
        if(li_sex.equals("L")){
        	li_sex = "1".trim();
        }else if(li_sex.equals("P")){
        	li_sex = "0".trim();
        }else{
        	li_sex = "9".trim();
        }
        par.put("liSex", li_sex);
        par.put( "liUsia", li_usia );
        par.put( "kurs_id", kurs );
        ldec_rate = eproposalManager.selectLdecRateToGetCoi_119( par ).doubleValue();

        ldec_temp = FormatNumber.round( ( ( ( istr_prop.up / 1000 ) * ldec_rate ) * 0.1 ), 2, istr_prop.kurs_id );
        ldec_total += ldec_temp;

        ldec_total = ldec_total + of_get_coi_rider_119( Hardcode.AI_JENIS_PA, ai_th );
        ldec_total = ldec_total + of_get_coi_rider_119( Hardcode.AI_JENIS_HCP, ai_th );
        ldec_total = ldec_total + of_get_coi_rider_119( Hardcode.AI_JENIS_TPD, ai_th );
        ldec_total = ldec_total + of_get_coi_rider_119( Hardcode.AI_JENIS_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_rider_119( Hardcode.AI_JENIS_WP_60_TPD, ai_th );
        ldec_total = ldec_total + of_get_coi_rider_119( Hardcode.AI_JENIS_PB_25_TPD, ai_th );
        ldec_total = ldec_total + of_get_coi_rider_119( Hardcode.AI_JENIS_WP_60_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_rider_119( Hardcode.AI_JENIS_PB_25_CI_DEATH, ai_th );
        ldec_total = ldec_total + of_get_coi_rider_119( Hardcode.AI_JENIS_PB_25_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_rider_119( Hardcode.AI_JENIS_PAYOR_SPOUSE, ai_th );
        ldec_total = ldec_total + of_get_coi_rider_119( Hardcode.AI_JENIS_HCP_FAMILY, ai_th );
        ldec_total = ldec_total + of_get_coi_rider_119( Hardcode.AI_JENIS_TERM_RIDER, ai_th );

        return ldec_total;
    }
    
    public double of_get_coi_120( int ai_th )
    {
        double ldec_total = 0, ldec_temp;
        double ldec_rate;
        int li_usia;
        li_usia = istr_prop.umur_tt + ai_th - 1;
        String li_sex; //IGA - PROJECT NEW COI
        li_sex = cepr01030101Form.getInsuredSexCd();
        if(li_sex.equals("L")){
        	li_sex = "1".trim();
        }else if(li_sex.equals("P")){
        	li_sex = "0".trim();
        }else{
        	li_sex = "9".trim();
        }

        Map<String, Object> par = new HashMap<String, Object>();
        par.put( "liUsia", li_usia );
        par.put("liSex", li_sex);
        //DONE
        ldec_rate = eproposalManager.selectLdecRateToGetCoi( par ).doubleValue();

        ldec_temp = FormatNumber.round( ( ( ( istr_prop.up / 1000 ) * ldec_rate ) * 0.1 ), 2, istr_prop.kurs_id );
       
        ldec_total += ldec_temp;

        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_PA, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_HCP, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_TPD, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_WP_60_TPD, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_PB_25_TPD, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_WP_60_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_PB_25_CI_DEATH, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_PB_25_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_PAYOR_SPOUSE, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_HCP_FAMILY, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_TERM_RIDER, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_WAIVER_TPD_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_EKA_SEHAT, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_EKA_SEHAT_IL, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_HCP_PROVIDER, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_PAYOR_TPD_CI_DEATH, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_LADIES_INS, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_LADIES_HCP, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_LADIES_MED_EXPENSE, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_LADIES_MED_EXPENSE_INNER_LIMIT, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_PAYOR_5_TPD_10_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_WAIVER_5_TPD_10_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_SCHOLAR, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_EARLY_STAGE_CI99, ai_th );
        ldec_total = ldec_total + of_get_coi_120( Hardcode.AI_JENIS_MEDICAL_PLUS, ai_th );
        
        return ldec_total;
    }
    
    public double of_get_coi_141( int ai_th )
    {
        double ldec_total = 0, ldec_temp;
        double ldec_rate;
        int li_usia;
        li_usia = istr_prop.umur_tt + ai_th - 1;


        Map<String, Object> par = new HashMap<String, Object>();
        par.put( "liUsia", li_usia );
        ldec_rate = eproposalManager.selectLdecRateToGetCoi( par ).doubleValue();

        ldec_temp = FormatNumber.round( ( ( ( istr_prop.up / 1000 ) * ldec_rate ) * 0.1 ), 2, istr_prop.kurs_id );
      
        ldec_total += ldec_temp;

        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_PA, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_HCP, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_TPD, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_WP_60_TPD, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_PB_25_TPD, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_WP_60_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_PB_25_CI_DEATH, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_PB_25_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_PAYOR_SPOUSE, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_HCP_FAMILY, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_TERM_RIDER, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_EKA_SEHAT, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_EKA_SEHAT_IL, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_HCP_PROVIDER, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_PAYOR_TPD_CI_DEATH, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_LADIES_INS, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_LADIES_HCP, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_LADIES_MED_EXPENSE, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_LADIES_MED_EXPENSE_INNER_LIMIT, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_SCHOLAR, ai_th );
        ldec_total = ldec_total + of_get_coi_141( Hardcode.AI_JENIS_EARLY_STAGE_CI99, ai_th );
        
        return ldec_total;
    }
    
    public double of_get_coi_rider_119( int ai_jenis, int ai_th )
    {
        double ldec_total = 0, ldec_temp;
        double ldec_rate;
        int li_usia, li_tahun_ke, li_kali = 1, li_jenis, li_class;
        List< Mst_proposal > mstProposal = new ArrayList< Mst_proposal >();
        li_usia = istr_prop.umur_tt + ai_th - 1;

        if( istr_prop.rider_baru[ 5 ] > 0 || istr_prop.rider_baru[ 6 ] > 0 || istr_prop.rider_baru[ 7 ] > 0 || istr_prop.rider_baru[ 8 ] > 0 || istr_prop.rider_baru[ 9 ] > 0 || istr_prop.rider_baru[ 10 ] > 0 )
        {
            if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_TRIWULANAN )
            {
                li_kali = 4;
            }
            else if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_SEMESTERAN )
            {
                li_kali = 2;
            }
            else if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_BULANAN )
            {
                li_kali = 12;
            }
        }
//
        switch( ai_jenis )
        {
            case 4:
                //ci
                if( istr_prop.rider_baru[ 4 ] > 0 )
                {
	               	 String li_sex;  // -- IGA - PROJECT NEW COR
	                 li_sex = cepr01030101Form.getInsuredSexCd();
	                 if(li_sex.equals("L")){
	                 	li_sex = "1".trim();
	                 }else if(li_sex.equals("P")){
	                 	li_sex = "0".trim();
	                 }else{
	                 	li_sex = "9".trim();
	                 }
                	 Map<String, Object> params = new HashMap<String, Object>();
                     params.put( "kursId", istr_prop.kurs_id );
                     params.put( "liUsia", li_usia );
                     params.put("liSex", li_sex); //-- DONE
                     ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateCi( params ) );
//                     double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getCiChooseCd())) / 100.0;
                     /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
                     double upCi = cepr01030103Form.getCiRiderAmount().doubleValue();
                     double maxUp;
                     if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) ){
                     	maxUp = 500000.0;
                     }else{
                     	maxUp = 5000000000.0;
                     }
                     double temp = MathUtil.min( upCi, maxUp );
                     ldec_temp = FormatNumber.round( ( temp / 1000 * ldec_rate ) * 0.1, 2 );
                     ldec_total += ldec_temp;
                     if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("813"), new BigDecimal("8"),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }
                break;
                //
            case 5:
                //wp 60tpd
                if( istr_prop.rider_baru[ 5 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.bisnis_no );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp60Tpd( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("814"), new BigDecimal( istr_prop.bisnis_no ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                       }
                }
                break;
            case 6:
                //pb 25 tpd
                if( istr_prop.rider_baru[ 6 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp;
                    //li_jenis = istr_prop.rider_baru[ 6 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", istr_prop.bisnis_no );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", 0 );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Tpd( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal( istr_prop.bisnis_no ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;

            default:
                break;
        }
        return ldec_total;
    }
    
    
    public double of_get_coi_rider_119_cost( int ai_jenis, int ai_th, List< Mst_proposal > mstProposal )
    {
        double ldec_total = 0, ldec_temp;
        double ldec_rate;
        int li_usia, li_tahun_ke, li_kali = 1, li_jenis, li_class;
        li_usia = istr_prop.umur_tt + ai_th - 1;

        if( istr_prop.rider_baru[ 5 ] > 0 || istr_prop.rider_baru[ 6 ] > 0 || istr_prop.rider_baru[ 7 ] > 0 || istr_prop.rider_baru[ 8 ] > 0 || istr_prop.rider_baru[ 9 ] > 0 || istr_prop.rider_baru[ 10 ] > 0 )
        {
            if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_TRIWULANAN )
            {
                li_kali = 4;
            }
            else if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_SEMESTERAN )
            {
                li_kali = 2;
            }
            else if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_BULANAN )
            {
                li_kali = 12;
            }
        }
//
        switch( ai_jenis )
        {
            case 4:
                //ci
                if( istr_prop.rider_baru[ 4 ] > 0 )
                {
                	 String li_sex;  // -- IGA - PROJECT NEW COR
                     li_sex = cepr01030101Form.getInsuredSexCd();
                     if(li_sex.equals("L")){
                     	li_sex = "1".trim();
                     }else if(li_sex.equals("P")){
                     	li_sex = "0".trim();
                     }else{
                     	li_sex = "9".trim();
                     }
                	 Map<String, Object> params = new HashMap<String, Object>();
                     params.put( "kursId", istr_prop.kurs_id );
                     params.put( "liUsia", li_usia );
                     params.put("liSex", li_sex); //-- DONE
                     ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateCi( params ) );
//                     double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getCiChooseCd())) / 100.0;
                     /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
                     double upCi = cepr01030103Form.getCiRiderAmount().doubleValue();
                     double maxUp;
                     if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) ){
                     	maxUp = 500000.0;
                     }else{
                     	maxUp = 5000000000.0;
                     }
                     double temp = MathUtil.min( upCi, maxUp );
                     ldec_temp = FormatNumber.round( ( temp / 1000 * ldec_rate ) * 0.1, 2 );
                     ldec_total += ldec_temp;
                     if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("813"), new BigDecimal("8"),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }
                break;
                //
            case 5:
                //wp 60tpd
                if( istr_prop.rider_baru[ 5 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.bisnis_no );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp60Tpd( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("814"), new BigDecimal( istr_prop.bisnis_no ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                       }
                }
                break;
            case 6:
                //pb 25 tpd
                if( istr_prop.rider_baru[ 6 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp;
                    //li_jenis = istr_prop.rider_baru[ 6 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", istr_prop.bisnis_no );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", 0 );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Tpd( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal( istr_prop.bisnis_no ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;

            default:
                break;
        }
        cepr01031001Form.setMstProposal( mstProposal );
        return ldec_total;
    }


    // I got it from window w_print_prop at PB
    // will be display on data window d_rincian_biaya
    public double of_get_coi_rider( int ai_jenis, int ai_th )
    {
        double ldec_total = 0, ldec_temp;
        double ldec_rate;
        int li_usia, li_tahun_ke, li_kali = 1, li_jenis, li_class, li_usia_tt, li_usia_pp;
        li_usia = istr_prop.umur_tt + ai_th - 1;
        if(li_usia == 0) li_usia=1;
        li_usia_pp = istr_prop.umur_pp + ai_th - 1;
        List< Mst_proposal > mstProposal = new ArrayList<Mst_proposal>();
        
        double ldec_pct = 1;
        if(ai_jenis != 0){
        	if(istr_prop.cb_id == 1 ){  //triwulan
        		li_kali = 4;
        		ldec_pct = 0.27;
        	}else if( istr_prop.cb_id == 2){ //semesteran
        		li_kali = 2;
        		ldec_pct = 0.525;
        	}else if( istr_prop.cb_id == 6){ //bulanan
        		li_kali = 12;
        		ldec_pct = 0.1;
        	}
        }

        if( istr_prop.rider_baru[ 5 ] > 0 || istr_prop.rider_baru[ 6 ] > 0 || istr_prop.rider_baru[ 7 ] > 0 || istr_prop.rider_baru[ 8 ] > 0 || istr_prop.rider_baru[ 9 ] > 0 || istr_prop.rider_baru[ 10 ] > 0 )
        {
            if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_TRIWULANAN )
            {
                li_kali = 4;
            }
            else if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_SEMESTERAN )
            {
                li_kali = 2;
            }
            else if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_BULANAN )
            {
                li_kali = 12;
            }
        }
//       
        switch( ai_jenis )
        {
            case 1:
                //pa
                if( ( istr_prop.rider_baru[ 1 ] > 0 ) && ( li_usia < 60 ) )
                {
                    li_class = istr_prop.pa_class;
                    if( istr_prop.umur_tt < 17 ) li_class = 2;
                   /* if( istr_prop.umur_tt > 17 && istr_prop.umur_tt < 56){
                    	li_class = 3;
                    }*/
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "paRisk", istr_prop.pa_risk );
                    params.put( "liClass", li_class );
                    params.put("liSex", li_sex); //-- DONE
                    
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePa( params ) );
                    double maks = 2000000000.0;
                    if( istr_prop.kurs_id.equals( Hardcode.CUR_USD_CD ) ){
                    	 maks = 200000.0;
                    }
                    double maxAmount = MathUtil.min( istr_prop.rider_baru[ 1 ] * istr_prop.up, maks );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2, istr_prop.kurs_id );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("810"), new BigDecimal( istr_prop.pa_risk ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                       }
                }
                break;
            case 2:
                //hcp
                if( ( istr_prop.rider_baru[ 2 ] > 0 ) && ( li_usia < 65 ) )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 2 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );

                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcp( params ) );
                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2, istr_prop.kurs_id );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("811"), new BigDecimal(istr_prop.rider_baru[ 2 ]),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
            case 3:
                //tpd
                if( istr_prop.rider_baru[ 3 ] > 0 &&  li_usia < 60 )
                {
                	String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    params.put("liSex", li_sex); //-- DONE	
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTpd( params ) );
                    // maks 2 milyar
                    if( "01".equals( istr_prop.kurs_id ) ){
                    	ldec_max = 2000000000;
                    }else if( "02".equals( istr_prop.kurs_id ) ){
                    	ldec_max = 200000;
                    }
                    double maxAmount = MathUtil.min( istr_prop.up, ldec_max );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2, istr_prop.kurs_id );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("812"), new BigDecimal("7"),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                  }
                }
                break;
            case 4:
                //ci
                if( istr_prop.rider_baru[ 4 ] > 0 &&  li_usia < 75 )
                {
	               	 String li_sex;  // -- IGA - PROJECT NEW COR
	                 li_sex = cepr01030101Form.getInsuredSexCd();
	                 if(li_sex.equals("L")){
	                 	li_sex = "1".trim();
	                 }else if(li_sex.equals("P")){
	                 	li_sex = "0".trim();
	                 }else{
	                 	li_sex = "9".trim();
	                 }
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    params.put("liSex", li_sex); //-- DONE
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateCi( params ) );
//                    double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getCiChooseCd())) / 100.0;
                    /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
                    double upCi = cepr01030103Form.getCiRiderAmount().doubleValue();
                    double maxUp;
                    if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) ){
                    	maxUp = 500000.0;
                    }else{
                    	maxUp = 5000000000.0;
                    }
                    double temp = MathUtil.min( upCi, maxUp );
                    ldec_temp = FormatNumber.round( ( temp / 1000 * ldec_rate ) * 0.1, 2, istr_prop.kurs_id );
//                    ldec_temp = FormatNumber.round( ( ( istr_prop.up * persentase / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("813"), new BigDecimal("8"),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                    
                }
                break;
                //
            case 5:
                //wp 60tpd
                if( istr_prop.rider_baru[ 5 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 5 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp60Tpd( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2, istr_prop.kurs_id );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("814"), new BigDecimal( istr_prop.rider_baru[ 5 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                       }

                }
                break;
            case 6:
                //pb 25 tpd
                if( istr_prop.rider_baru[ 6 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 6 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Tpd( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2, istr_prop.kurs_id );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal( li_jenis ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                  }
                }
                break;
            case 7:
                //wp 60ci
                if( istr_prop.rider_baru[ 7 ] > 0 )
                {

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 7 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp60Ci( params ) );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2, istr_prop.kurs_id );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("816"), new BigDecimal( istr_prop.rider_baru[ 7 ] ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                  }
                }
                break;
            case 8:
                //pb 25 ci/death
                if( istr_prop.rider_baru[ 8 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 8 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25CiDeath( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2, istr_prop.kurs_id );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("817"), new BigDecimal(li_jenis),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
                //pb 25 ci
            case 9:
                if( istr_prop.rider_baru[ 9 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = 4;

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Ci( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2, istr_prop.kurs_id );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("817"), new BigDecimal(li_jenis),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                       }
                }
                break;
                //payor spouse
            case 10:
                if( istr_prop.rider_baru[ 10 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = 6;

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorSpouse( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2, istr_prop.kurs_id );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal(li_jenis),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            case 11:
                //hcp family
                if( istr_prop.rider_baru[ 11 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 11 ] + 140 );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamily( params ) );

                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2, istr_prop.kurs_id );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("819"), new BigDecimal( istr_prop.rider_baru[ 11 ] + 140 ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }

                //hcp family peserta tambahan
                if( istr_prop.rider_baru[ 11 ] > 0 && istr_prop.hcpf.peserta > 0 && li_usia < 65)
                {
                    for( int i = 1; i <= istr_prop.hcpf.peserta; i++ )
                    {
                    	li_usia_tt = istr_prop.hcpf.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 11 ] + 140 );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia_tt );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamilyPesertaTambahan( params ) );
                        ldec_temp = 0;
                    	if (!( i > 1 && li_usia_tt > 24 )){
                    		 ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2, istr_prop.kurs_id );
                    	}
//                        ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                        ldec_total += ldec_temp;
                        Integer lsdbs_hcp_fam = null;
                        if( i == 1 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + 20;
                        }else if( i == 2 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 5 );
                        }
                        if( ai_th == 1 ){
                        	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("819"), new BigDecimal( lsdbs_hcp_fam ),
                        			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        			 new BigDecimal(istr_prop.cb_id),  istr_prop.hcpf.tgl[i], istr_prop.hcpf.usia[i] ) );
                        }
                    }
                }
                
                break;

            case 12:
                //term rider
                if( istr_prop.rider_baru[ 12 ] > 0 && li_usia <= 69 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTermRider( params ) );
                    ldec_temp = FormatNumber.round( ( ( istr_prop.up_term / 1000 ) * ldec_rate ) * 0.1, 2, istr_prop.kurs_id );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("818"), new BigDecimal( "2" ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
                
            case 13:
                //eka sehat
                if( istr_prop.rider_baru[ 13 ] > 0 && li_usia < 75 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();                    
                    if(cepr01030101Form.getProposalUserCd().equals(Hardcode.DMTM_MSAG_ID))
                    {
                    	 params.put( "riderBaru", istr_prop.rider_baru[ 13 ] + 105 );
                    }else{
                    	if( istr_prop.rider_baru[ 13 ] == 16 ){
                    		params.put( "riderBaru", 211 );
                    	}else if( istr_prop.rider_baru[ 13 ] == 17 ){
                    		params.put( "riderBaru", 212 );
                    	}else{
                    		 params.put( "riderBaru", istr_prop.rider_baru[ 13 ] );
                    	}
                    }                         
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) );
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
//                    ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
        			ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2, istr_prop.kurs_id );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("823"), new BigDecimal( istr_prop.rider_baru[ 13 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }

                //eka sehat peserta tambahan
                if( istr_prop.rider_baru[ 13 ] > 0 && istr_prop.eka_sehat.peserta > 0   && li_usia < 75 )
                {
                    for( int i = 1; i <= istr_prop.eka_sehat.peserta; i++ )
                    {
                        li_usia = istr_prop.eka_sehat.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        if( istr_prop.rider_baru[ 13 ] == 16 || istr_prop.rider_baru[ 13 ] == 17 ){
                    		params.put( "riderBaru", istr_prop.rider_baru[ 13 ] + 195 );                    		
                    	}else{
                    		 params.put( "riderBaru", istr_prop.rider_baru[ 13 ] );
                    	}
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) );
//                        ldec_temp = FormatNumber.round( (ldec_rate * 0.975 * ldec_pct) * 0.1, 2 );
                        ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2, istr_prop.kurs_id );
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_e_sehat = null;
                        if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + 15;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 5 );
                        }
                        if( ai_th == 1 ){
                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("823"), new BigDecimal( lsdbs_e_sehat ),
                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                          			 new BigDecimal(istr_prop.cb_id), istr_prop.eka_sehat.tgl[i], istr_prop.eka_sehat.usia[i] ) );
                        }
                    }
                }
                break;
                
            case 15:
            	//eka sehat inner limit
            	if( istr_prop.rider_baru[ 15 ] > 0 && li_usia < 75 )
            	{
            		Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 15 ] );  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ) );
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
                    ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2, istr_prop.kurs_id );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("825"), new BigDecimal( istr_prop.rider_baru[ 15 ] ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
	            }
            	if( istr_prop.rider_baru[ 15 ] > 0 && istr_prop.ekaSehatInnerLimit.peserta > 0 && li_usia < 75 )
            	{
            		for ( int i = 1; i <= istr_prop.ekaSehatInnerLimit.peserta; i++)
            		{
            			li_usia = istr_prop.ekaSehatInnerLimit.usia[ i ] + ai_th - 1;
            			
            			Map<String, Object> params = new HashMap<String, Object>();
            			params.put( "riderBaru", istr_prop.rider_baru[ 15 ] );
            			params.put( "kursId", istr_prop.kurs_id );
            			params.put( "liUsia", li_usia );
            			ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ));
            			ldec_temp = FormatNumber.round((ldec_rate * 0.117), 2, istr_prop.kurs_id);
            			ldec_total += ldec_temp;
            			
            			Integer lsdbs_e_sehat = null;
            			if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + 15;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 5 );
                        }
            			if( ai_th == 1 ){
                         	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("825"), new BigDecimal( lsdbs_e_sehat ),
                         			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                         			 new BigDecimal(istr_prop.cb_id), istr_prop.ekaSehatInnerLimit.tgl[i], istr_prop.ekaSehatInnerLimit.usia[i] ) );
                        }
            		}
            	}
	            break;
                         
            case 16:
                //hcp Provider
                if( istr_prop.rider_baru[ 16 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );   
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProvider( params ) );

                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2, istr_prop.kurs_id );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("826"), new BigDecimal( istr_prop.rider_baru[ 16 ] ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }

                //hcp provider peserta tambahan
                if( istr_prop.rider_baru[ 16 ] > 0 && istr_prop.hcpPro.peserta > 0 && li_usia < 65 )
                {
                    for( int i = 1; i <= istr_prop.hcpPro.peserta; i++ )
                    {
                        li_usia = istr_prop.hcpPro.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );   
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProviderPesertaTambahan( params ) );
                        ldec_temp = 0;
                        if  (i > 1 && li_usia > 24){
                        	
                        }else{
                        	  ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2, istr_prop.kurs_id );
                        }
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_hcp_pro= null;
                        if( i == 1 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + 12;
                        }else if( i == 2 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 5 );
                        }
                        
                        if( ai_th == 1 ){
                        	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("826"), new BigDecimal( lsdbs_hcp_pro ),
                        			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        			 new BigDecimal(istr_prop.cb_id), istr_prop.hcpPro.tgl[i], istr_prop.hcpPro.usia[i] ) );
                       }
                    }
                }
                break;
            case 18:
            	//Payor TPD/CI/Death
            	if( istr_prop.rider_baru[ 18 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 18 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                	String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getPolicyHolderSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    params.put("liSex", li_sex); //-- DONE	
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorTpdCiDeath(params) );
                    
                    ldec_max = 200000;
//                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ), 2 );
//                    ldec_temp = FormatNumber.round( ( ( istr_prop.premi * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * 1 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2, istr_prop.kurs_id );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("828"), new BigDecimal( li_jenis ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
            case 17:
            	//Waiver TPD/CI/Death
            	if( istr_prop.rider_baru[ 17 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put( "liJenis", istr_prop.rider_baru[ 17 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", 0 );
                    params.put( "liUsia", li_usia );
                    params.put("liSex", li_sex); //-- DONE
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWaiverTpdCi(params) );
                    
                    ldec_max = 200000;
//                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
//                    ldec_temp = FormatNumber.round( ( ( istr_prop.premi * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * 1 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2, istr_prop.kurs_id );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("827"), new BigDecimal( istr_prop.rider_baru[ 17 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
            case 19:
                //ladies insurance
                if( istr_prop.rider_baru[ 19 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", istr_prop.rider_baru[ 19 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", 0 );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateLadiesIns(params) );
                    
                    ldec_max = 200000;
                    if( istr_prop.kurs_id.equals("01") ){ ldec_max = 2000000000;}
                    double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getLadiesInsChooseCd())) / 100.0;
                    double maxAmount = MathUtil.min( istr_prop.up * persentase, ldec_max );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2, istr_prop.kurs_id );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("830"), new BigDecimal( istr_prop.rider_baru[ 19 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
                
            case 20:
                //hcp ladies
                if( istr_prop.rider_baru[ 20 ] > 0 && istr_prop.hcpLad.peserta == 0)
                {
                	if( li_usia < 65 && istr_prop.hcpLad.peserta == 0 ){
	                    Map<String, Object> params = new HashMap<String, Object>();
	                    params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                    params.put( "kursId", istr_prop.kurs_id );
	                    params.put( "liUsia", li_usia );
	                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadies( params ) );
	
	                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2, istr_prop.kurs_id );
	                    ldec_total += ldec_temp;
	                    if( ai_th == 1 ){
	                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("831"), new BigDecimal( istr_prop.rider_baru[ 20 ] ),
	                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
	                    }
                	}
                }

                //hcp ladies peserta tambahan
                if( istr_prop.rider_baru[ 20 ] > 0 && istr_prop.hcpLad.peserta > 0 )
                {
                	li_usia = istr_prop.hcpLad.usia[ 1 ] + ai_th - 1;
                	if( li_usia < 65 && istr_prop.hcpLad.peserta > 0 )
                	{
	                    for( int i = 1; i <= istr_prop.hcpLad.peserta; i++ )
	                    {
	                        li_usia = istr_prop.hcpLad.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadiesPesertaTambahan( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2, istr_prop.kurs_id );
	                        if(i >= 2){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2, istr_prop.kurs_id );
	                        	if(i>=3 && li_usia>25) ldec_temp = 0;
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_hcp_lad = null;
 	                        if( i == 1 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ];
	                        }else if( i == 2 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 );
	                        }else if( i == 3 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 2 );
	                        }else if( i == 4 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 3 );
	                        }else if( i == 5 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 4 );
	                        }
 	                        if( ai_th == 1 ){
 	 	                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("831"), new BigDecimal( lsdbs_hcp_lad ),
 	 	                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
 	 	                    			 new BigDecimal(istr_prop.cb_id), istr_prop.hcpLad.tgl[i], istr_prop.hcpLad.usia[i] ) );
 	 	                    }
	                    }
                	}
                }
                break;
            case 21:
                //ladies medical expense
                if( istr_prop.rider_baru[ 21 ] > 0 && li_usia < 66 && istr_prop.ladiesMedExpense.peserta == 0)
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider(params));

                    ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2, istr_prop.kurs_id );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("832"), new BigDecimal( istr_prop.rider_baru[ 21 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }

                //ladies medical expense peserta tambahan
                if( istr_prop.rider_baru[ 21 ] > 0 && istr_prop.ladiesMedExpense.peserta > 0 )
                {
                	li_usia = istr_prop.ladiesMedExpense.usia[1] + ai_th - 1;
                	if( li_usia < 66 ){
	                    for( int i = 1; i <= istr_prop.ladiesMedExpense.peserta; i++ )
	                    {
	                        li_usia = istr_prop.ladiesMedExpense.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2, istr_prop.kurs_id );
	                        if(i > 1){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.117, 2, istr_prop.kurs_id );
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_ladies_med = null;
	                        if( i == 1 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ];
 	                        }else if( i == 2 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 );
 	                        }else if( i == 3 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 2 );
 	                        }else if( i == 4 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 3 );
 	                        }else if( i == 5 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 4 );
 	                        }
 	                        
	                        if( ai_th == 1 ){
	                       	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("832"), new BigDecimal( lsdbs_ladies_med ),
	                       			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                       			 new BigDecimal(istr_prop.cb_id), istr_prop.ladiesMedExpense.tgl[i], istr_prop.ladiesMedExpense.usia[i] ) );
	                       }
	                    }
                	}
                }
                break;
            case 22:
                //ladies medical expense inner limit
                if( istr_prop.rider_baru[ 22 ] > 0 && li_usia < 66 && istr_prop.ladiesMedExpenseInnerLimit.peserta == 0)
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider(params));

                    ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2, istr_prop.kurs_id );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("833"), new BigDecimal( istr_prop.rider_baru[ 22 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }

                //ladies medical expense inner limit peserta tambahan
                if( istr_prop.rider_baru[ 22 ] > 0 && istr_prop.ladiesMedExpenseInnerLimit.peserta > 0 )
                {
                	li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[1] + ai_th - 1;
                	if( li_usia < 66  ){
	                    for( int i = 1; i <= istr_prop.ladiesMedExpenseInnerLimit.peserta; i++ )
	                    {
	                        li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2, istr_prop.kurs_id );
	                        if(i > 1){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.117, 2, istr_prop.kurs_id );
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_ladies_med_il = null;
	                        if( i == 1 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ];
 	                        }else if( i == 2 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 );
 	                        }else if( i == 3 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 2 );
 	                        }else if( i == 4 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 3 );
 	                        }else if( i == 5 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 4 );
 	                        }
 	                        
	                        if( ai_th == 1 ){
	                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("833"), new BigDecimal( lsdbs_ladies_med_il ),
	                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                          			 new BigDecimal(istr_prop.cb_id), istr_prop.ladiesMedExpenseInnerLimit.tgl[i], istr_prop.ladiesMedExpenseInnerLimit.usia[i] ) );
	                        }
	                    }
                	}
                }
                break;
            case 23:
            	//Payor 5/10 TPD/CI/Death
            	if( istr_prop.rider_baru[ 23 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                	if(  cepr01030102Form.getPremiumFurloughYear() == 5 ){
            			istr_prop.rider_baru[23] = 2;
            		}else if(  cepr01030102Form.getPremiumFurloughYear() == 10 ){
            			istr_prop.rider_baru[23] = 3;
            		}
                    li_jenis = istr_prop.rider_baru[ 23 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getPolicyHolderSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liTahunKe",  istr_prop.umur_pp);
                    params.put( "liUsia", ai_th );
                    params.put("liSex", li_sex); //-- DONE	
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorTpdCiDeath(params) );
                    		
                    ldec_max = 200000;
                    double ldec_pct_pp = 1;
                    if( istr_prop.kurs_id == "01" ) ldec_max = 2000000000;
                    ldec_temp = FormatNumber.round( ( ( ( ( istr_prop.premi * ldec_pct_pp ) * li_kali / 1000 ) * ldec_rate)  * 0.1 ), 2, istr_prop.kurs_id );
                	if( istr_prop.bisnis_id == 120 ||  istr_prop.bisnis_id == 202){
                	    ldec_temp = FormatNumber.round( ( ( ( ldec_rate / 1000 ) * ( istr_prop.premi * li_kali)) * 0.1 ), 2, istr_prop.kurs_id );
                	}
                    ldec_total += ldec_temp;
                    
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("828"), new BigDecimal( li_jenis ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }
                break;
                
            case 24:
            	//Waiver 5/10 TPD/CI
            	if( istr_prop.rider_baru[ 24 ] > 0 )
                {
            		li_usia = istr_prop.umur_tt + ai_th - 1;
            		if(  cepr01030102Form.getPremiumFurloughYear() == 5 ){
            			istr_prop.rider_baru[24] = 4;
            		}else if(  cepr01030102Form.getPremiumFurloughYear() == 10 ){
            			istr_prop.rider_baru[24] = 5;
            		}
            		li_jenis = istr_prop.rider_baru[24]	;
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", ai_th );
                    params.put( "liUsia", li_usia );
                    params.put("liSex", li_sex); //-- DONE
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWaiverTpdCi(params) );
                    
                    ldec_max = 200000;
//            		ldec_temp = Round(((ldec_rate/1000)*(istr_prop.premi *li_kali))*0.1, 2)
                    ldec_temp = FormatNumber.round( ( ( ldec_rate / 1000 )*( istr_prop.premi * li_kali ) ) * 0.1, 2, istr_prop.kurs_id );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("827"), new BigDecimal( li_jenis ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
            	break;
            case 25:
            	if( istr_prop.rider_baru[25] > 0 && li_usia < istr_prop.usia_schol ){
           		 Map<String, Object> params = new HashMap<String, Object>();
                 params.put( "kursId", istr_prop.kurs_id );
                 params.put( "liUsia", li_usia_pp );
                 ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateScholarship( params ) );
                 
                 Map<String, Object> paramsRate = new HashMap<String, Object>();
                 Integer lijenis = 0;
                 if( istr_prop.usia_schol == 22 ){
                	 lijenis = 1;
                 }else if( istr_prop.usia_schol == 25 ){
                	 lijenis = 2;
                 }
                 paramsRate = new HashMap<String, Object>();
                 paramsRate.put( "lsbsId", "835" );
                 paramsRate.put( "liJenis", lijenis );
                 paramsRate.put( "liUsia", li_usia );
                 paramsRate.put( "kursId", istr_prop.kurs_id );
                 BigDecimal rate_factor = eproposalManager.selectTableFactor( paramsRate );
                 if( rate_factor == null ) { rate_factor = BigDecimal.ZERO;}
                 double rate_factor_double = LazyConverter.toDouble( rate_factor );

                 ldec_temp = FormatNumber.round( ( ( ldec_rate / 1000 )* istr_prop.up_schol * rate_factor_double ) * 0.1, 2 );
                 ldec_total += ldec_temp;
                 
                 if( ai_th == 1 ){
                 	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("835"), new BigDecimal( lijenis ),
                 			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                 			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
        	}
            	
                break;
            case 26:
            	if( istr_prop.rider_baru[26] > 0 ){            		            		
            			double ldec_pct_pp = istr_prop.pct_premi / 100;
            			if(li_usia == 0) li_usia=1;
            			li_jenis = Integer.valueOf(cepr01030103Form.getBabyCd());            		
            			int liUsiaBaby =   istr_prop.umur_tt+ ai_th - 1;
            			
            			Map<String, Object> params = new HashMap<String, Object>();
                         params.put( "kursId", istr_prop.kurs_id );
                         params.put( "liUsiaBaby",  liUsiaBaby );
                         params.put( "liJenis", li_jenis);
                         ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateBaby( params ) );
            			        		         			
                         Map<String, Object> param = new HashMap<String, Object>();                    
                         param.put( "liUsia", li_usia );
                       //ldec_rb = of_get_coi_basic()
                         double ldec_rb = LazyConverter.toDouble( eproposalManager.selectLdecRateToGetCoiBasic( param ) );
                     
            			if(ai_th == 1){
            					//For i = 1 to 12   
            				double[] ldec_premi_bulan = new double[12 + 1];
            				double ldec_premi_setahun = 0;
            					for( int i = istr_prop.bln_ke; i <= 12; i++ ){
            						ldec_premi_bulan[i] = 0;
            						if(i == istr_prop.bln_ke)  ldec_premi_bulan[i] = istr_prop.premi;
            						if(istr_prop.cb_id == 1) 
            						{	//triwulan
            							if(i == 4 || i == 7 || i == 10) ldec_premi_bulan[i] = istr_prop.premi;
            						} 
            						else if(istr_prop.cb_id == 2 )
            						{	//semesteran
            							if(i == 7) ldec_premi_bulan[i] = istr_prop.premi;
            						} 
            						else if(istr_prop.cb_id == 6 ) 
            						{	//bulanan
            							ldec_premi_bulan[i] = istr_prop.premi;
            						}
            								
            						ldec_premi_setahun += ldec_premi_bulan[i];
            					}
            					ldec_temp = FormatNumber.round((ldec_rate * 0.1) + (ldec_premi_setahun * ldec_pct_pp * 0.7 * ldec_rb / 1000 * 0.1), 2);		
            					ldec_total += ldec_temp;
            			}else{
            					ldec_temp = FormatNumber.round(ldec_rate * 0.1, 2);
            					ldec_total += ldec_temp;
            			}
            	}
                break;  
            case 27:
            	if( istr_prop.rider_baru[27] > 0  ){            		
            		String genderInsured = cepr01030101Form.getInsuredSexCd().trim();
            		li_jenis=0;
            		if(genderInsured.equals("L")){
            			li_jenis = 1;
            		}
            		else if(genderInsured.equals("P")){
            			li_jenis = 2;
            		}
            		
            		Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "lku_id", istr_prop.kurs_id );
                    params.put( "lsr_jenis", li_jenis );
                    params.put( "li_usia", li_usia);
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateEarlyStageCi99( params ) );
                            		
//                    double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getEsci99ChooseCd())) / 100.0;
                    /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
                    double upEsci99 =  cepr01030103Form.getEsci99RiderAmount().doubleValue();
                    double maxUp;
                    if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) ){
                    	maxUp = 500000.0;
                    }else{
                    	maxUp = 5000000000.0;
                    }
                    double temp = MathUtil.min( upEsci99, maxUp );                                        
                    //ldec_temp = FormatNumber.round(((ldec_rate / 1000) * istr_prop.up) * 0.1, 2);
                    ldec_temp = FormatNumber.round( ( temp / 1000 * ldec_rate ) * 0.1, 2, istr_prop.kurs_id );
                	ldec_total += ldec_temp;
                	
        			 if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("837"), new BigDecimal( li_jenis ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
            	}
                break;     
            case 28:
                //SMiLe Medical (+)
                if( istr_prop.rider_baru[ 28 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    int riderBaru = istr_prop.rider_baru[ 28 ];
                    
                    params.put( "riderBaru", riderBaru );  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRider( params ) );
                  
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
//                    ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
        			ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
        			ldec_total += ldec_temp;                	               	

        			if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("838"), new BigDecimal( riderBaru ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
        			
        			if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag())){
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
                        ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
             			ldec_total += ldec_temp;
             			
             			if( ai_th == 1 ){
                       	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                       			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                       			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                       }
        			}
        			if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRgFlag())){           				
        				riderBaru = istr_prop.rider_baru[ 28 ]+20;
                    	                       	
                    	params.put( "riderBaru", riderBaru );  
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
        				ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
                        ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
            			ldec_total += ldec_temp;
            			
            			if( ai_th == 1 ){
                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                          			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                          }
        			}
        			if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRbFlag())){           				
        				riderBaru = istr_prop.rider_baru[ 28 ]+40;
                    	                      	
                    	params.put( "riderBaru", riderBaru );  
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
        				ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
                        ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
            			ldec_total += ldec_temp;
            			 
            			if( ai_th == 1 ){
                           	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                           			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                           			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                        }
        			}
        			if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusPkFlag())){           				
        				riderBaru = istr_prop.rider_baru[ 28 ]+60;
                    	
                    	params.put( "riderBaru", riderBaru );  
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
        				ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
                        ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
            			ldec_total += ldec_temp;
            			
            			if( ai_th == 1 ){
                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                          			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                       }
        			} 
                }
               //SMiLe Medical(+) Peserta Tambahan
           	   if( istr_prop.rider_baru[ 28 ] > 0 && istr_prop.medicalPlus.peserta > 0   && li_usia < 75 )
                {
                   for( int i = 1; i <= istr_prop.medicalPlus.peserta; i++ )
                   {
                       li_usia = istr_prop.medicalPlus.usia[ i ] + ai_th - 1;
                       int riderBaru = istr_prop.rider_baru[ 28 ];
                     
                       if(i==1){
                       	riderBaru = riderBaru + 4;
                       }
                       else if(i==2){
                       	riderBaru = riderBaru + 8;
                       }
                       else if(i==3){
                       	riderBaru = riderBaru + 12;
                       }
                       else if(i==4){
                       	riderBaru = riderBaru + 16;
                       }
                       Map<String, Object> params = new HashMap<String, Object>();
                       params.put( "riderBaru", riderBaru );  
                       params.put( "kursId", istr_prop.kurs_id );
                       params.put( "liUsia", li_usia );
                       ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRider( params ) );	                        
                       ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
                       ldec_total += ldec_temp;	    
                       
                       if( ai_th == 1 ){
                         	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("838"), new BigDecimal( riderBaru ),
                         			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                         			 new BigDecimal(istr_prop.cb_id), istr_prop.medicalPlus.tgl[i], istr_prop.medicalPlus.usia[i] ) );
                       }
                       
                       if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag())){           				
                            ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
                            ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
                 			 ldec_total += ldec_temp;
                 			 
                 			if( ai_th == 1 ){
                            	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                            	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                            	new BigDecimal(istr_prop.cb_id),  istr_prop.medicalPlus.tgl[i], istr_prop.medicalPlus.usia[i] ) );
                            }
           				}
                        if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRgFlag())){           				
            				riderBaru = istr_prop.rider_baru[ 28 ]+20;
                        	
                        	if(i==1){
	                        	riderBaru = riderBaru + 4;
	                        }
	                        else if(i==2){
	                        	riderBaru = riderBaru + 8;
	                        }
	                        else if(i==3){
	                        	riderBaru = riderBaru + 12;
	                        }
	                        else if(i==4){
	                        	riderBaru = riderBaru + 16;
	                        }
                        	
                        	params.put( "riderBaru", riderBaru );  
                            params.put( "kursId", istr_prop.kurs_id );
                            params.put( "liUsia", li_usia );
            				ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
            				ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
                 			ldec_total += ldec_temp;
                 			 
                 			if( ai_th == 1 ){
                            	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                            	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                            	new BigDecimal(istr_prop.cb_id),  istr_prop.medicalPlus.tgl[i], istr_prop.medicalPlus.usia[i] ) );
                            }
            			}
                        if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(istr_prop.medicalPlus.medicalPlusRbFlag[i])){        				
            				riderBaru = istr_prop.rider_baru[ 28 ]+40;
                        	                         	
                        	if(i==1){
	                        	riderBaru = riderBaru + 4;
	                        }
	                        else if(i==2){
	                        	riderBaru = riderBaru + 8;
	                        }
	                        else if(i==3){
	                        	riderBaru = riderBaru + 12;
	                        }
	                        else if(i==4){
	                        	riderBaru = riderBaru + 16;
	                        }	                         	
                            params.put( "riderBaru", riderBaru );  
                            params.put( "kursId", istr_prop.kurs_id );
                            params.put( "liUsia", li_usia );
            				ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
            				ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
                			ldec_total += ldec_temp;
                			 
                			if( ai_th == 1 ){
                            	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                            	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                            	new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                            }
            			}                        
                        if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusPkFlag())){           				
            				riderBaru = istr_prop.rider_baru[ 28 ]+60;
                        
                        	if(i==1){
	                        	riderBaru = riderBaru + 4;
	                        }
	                        else if(i==2){
	                        	riderBaru = riderBaru + 8;
	                        }
	                        else if(i==3){
	                        	riderBaru = riderBaru + 12;
	                        }
	                        else if(i==4){
	                        	riderBaru = riderBaru + 16;
	                        }	    
                        	 params.put( "riderBaru", riderBaru );  
                             params.put( "kursId", istr_prop.kurs_id );
                             params.put( "liUsia", li_usia );
            				 ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
            				 ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
                			 ldec_total += ldec_temp;
                			 
                			if( ai_th == 1 ){
                            	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                            	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                            	new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                           }
            			}
                   }
                }
           	   break;          	   
            case 29:
            	//SME (71-140)
                //smile medical extra
                if( istr_prop.rider_baru[ 29 ] > 0 && li_usia < 75 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                   
                    params.put( "riderBaru", istr_prop.rider_baru[ 29 ] + 70);                
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectSMiLeMedicalExtraRider( params ) );
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
//                    ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
        			ldec_temp = FormatNumber.round( (ldec_rate) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("848"), new BigDecimal( istr_prop.rider_baru[ 29 ] ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }

                //eka sehat peserta tambahan
                if( istr_prop.rider_baru[ 29 ] > 0 && istr_prop.eka_sehatExtra.peserta > 0   && li_usia < 75 )
                {
                    for( int i = 1; i <= istr_prop.eka_sehatExtra.peserta; i++ )
                    {
                    	
                        li_usia = istr_prop.eka_sehatExtra.usia[ i ] + ai_th - 1;
                        Map<String, Object> params = new HashMap<String, Object>();                      
                    	params.put( "riderBaru", istr_prop.rider_baru[ 29 ] + 70);                    	
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectSMiLeMedicalExtraRider( params ) );
//                        ldec_temp = FormatNumber.round( (ldec_rate * 0.975 * ldec_pct) * 0.1, 2 );
                     //   ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
                        ldec_temp = FormatNumber.round( (ldec_rate * 0.0975) , 2 );
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_e_sehat = null;
                        if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 29 ] + 10;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 29 ] + ( 10 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 29 ] + ( 10 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 29 ] + ( 10 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 29 ] + ( 10 * 5 );
                        }
                        if( ai_th == 1 ){
                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("848"), new BigDecimal( lsdbs_e_sehat ),
                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                          			 new BigDecimal(istr_prop.cb_id), istr_prop.eka_sehat.tgl[i], istr_prop.eka_sehat.usia[i] ) );
                        }
                    }
                }
                break;
            case 30:
            /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
           	 if( istr_prop.rider_baru[ 30 ] > 0 && li_usia < 75 )
                {
           		 String li_sex;
	                 li_sex = cepr01030101Form.getInsuredSexCd();
	                 if(li_sex.equals("L")){
	                 	li_sex = "1".trim();
	                 }else if(li_sex.equals("P")){
	                 	li_sex = "0".trim();
	                 }else{
	                 	li_sex = "9".trim();
	                 }
	                 String lsdbs_number = String.valueOf(istr_prop.rider_baru[ 30 ]);
	                 
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    params.put("liSex", li_sex); //-- DONE
                    params.put("li_jenis", lsdbs_number); //-- DONE
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTerm( params ) );                    
                    ldec_temp = FormatNumber.round( ( ( istr_prop.up_term5575 / 1000 ) * ldec_rate ) * 0.1, 2, istr_prop.kurs_id );                    
                    ldec_total += ldec_temp;
            
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("818"), new BigDecimal( lsdbs_number ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
                                
           	   
            default:
                break;
        }
        
        return ldec_total;
    }
    
    
    public double of_premi_rider( int ai_jenis, int ai_th )
    {
        double ldec_total = 0, ldec_temp, ldec_kali = 1;
        double ldec_rate;
        int li_usia, li_tahun_ke, li_kali = 1, li_jenis, li_class, li_usia_tt;
        li_usia = istr_prop.umur_tt + ai_th - 1;
        List< Mst_proposal > mstProposal = new ArrayList<Mst_proposal>();
        
        double ldec_pct = 1;
        if(ai_jenis != 0){
        	if(istr_prop.cb_id == 1 ){  //triwulan
        		li_kali = 4;
        		ldec_pct = 0.27;
        	}else if( istr_prop.cb_id == 2){ //semesteran
        		li_kali = 2;
        		ldec_pct = 0.525;
        	}else if( istr_prop.cb_id == 6){ //bulanan
        		li_kali = 12;
        		ldec_pct = 0.1;
        	}
        }

        if( istr_prop.rider_baru[ 5 ] > 0 || istr_prop.rider_baru[ 6 ] > 0 || istr_prop.rider_baru[ 7 ] > 0 || istr_prop.rider_baru[ 8 ] > 0 || istr_prop.rider_baru[ 9 ] > 0 || istr_prop.rider_baru[ 10 ] > 0 )
        {
            if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_TRIWULANAN )
            {
                li_kali = 4;
            }
            else if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_SEMESTERAN )
            {
                li_kali = 2;
            }
            else if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_BULANAN )
            {
                li_kali = 12;
            }
        }
//
        switch( ai_jenis )
        {
            case 1:
                //pa
                if( ( istr_prop.rider_baru[ 1 ] > 0 ) && ( li_usia < 60 ) )
                {
                    li_class = istr_prop.pa_class;
                    if( istr_prop.umur_tt <= 16 ) li_class = 2;
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "paRisk", istr_prop.pa_risk );
                    params.put( "liClass", li_class );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePa( params ) );
                    double maks = 2000000000.0;
                    if( istr_prop.kurs_id.equals( Hardcode.CUR_USD_CD ) ){
                    	 maks = 200000.0;
                    }
                    double maxAmount = MathUtil.min( istr_prop.rider_baru[ 1 ] * istr_prop.up, maks );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("810"), new BigDecimal( istr_prop.pa_risk ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
            case 2:
                //hcp
                if( ( istr_prop.rider_baru[ 2 ] > 0 ) && ( li_usia < 65 ) )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 2 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );

                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcp( params ) );
                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("811"), new BigDecimal(istr_prop.rider_baru[ 2 ]),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
            case 3:
                //tpd
                if( istr_prop.rider_baru[ 3 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTpd( params ) );
                    // maks 2 milyar
                    if( "01".equals( istr_prop.kurs_id ) ){
                    	ldec_max = 2000000000;
                    }else if( "02".equals( istr_prop.kurs_id ) ){
                    	ldec_max = 200000;
                    }
                    double maxAmount = MathUtil.min( istr_prop.up, ldec_max );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("812"), new BigDecimal( "7" ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                  }
                }
                break;
            case 4:
                //ci
                if( istr_prop.rider_baru[ 4 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateCi( params ) );
//                    double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getCiChooseCd())) / 100.0;
                    /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
                    double upCi = cepr01030103Form.getCiRiderAmount().doubleValue();
                    double maxUp;
                    if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) ){
                    	maxUp = 500000.0;
                    }else{
                    	maxUp = 5000000000.0;
                    }
                    double temp = MathUtil.min( upCi, maxUp );
                    ldec_temp = FormatNumber.round( ( temp / 1000 * ldec_rate ) * 0.1, 2 );
//                    ldec_temp = FormatNumber.round( ( ( istr_prop.up * persentase / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("813"), new BigDecimal("8"),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
                //
            case 5:
                //wp 60tpd
                if( istr_prop.rider_baru[ 5 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    if( istr_prop.bisnis_id == 169 ){
                    	if( cepr01030102Form.getTermOfContract().equals(5)) {
                    		li_jenis = 6;
                    	}else if( cepr01030102Form.getTermOfContract().equals(10)) {
                    		li_jenis = 7;
                    	}else if( cepr01030102Form.getTermOfContract().equals(15)) {
                    		li_jenis = 8;
                    	}else if( cepr01030102Form.getTermOfContract().equals(20)) {
                    		li_jenis = 9;
                    	}else{
                    		li_jenis = 6;
                    	}
                    	
                    }else{
                    	li_jenis = istr_prop.rider_baru[ 5 ];
                    }
                    params.put( "riderBaru", li_jenis);
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp60Tpd( params ) );

                    ldec_temp = FormatNumber.round(  ldec_rate , 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("814"), new BigDecimal( li_jenis ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
            case 6:
                //pb 25 tpd
                if( istr_prop.rider_baru[ 6 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 6 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Tpd( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal( li_jenis ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
            case 7:
                //wp 60ci
                if( istr_prop.rider_baru[ 7 ] > 0 )
                {

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 7 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp60Ci( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("816"), new BigDecimal( istr_prop.rider_baru[ 7 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
            case 8:
                //pb 25 ci/death
                if( istr_prop.rider_baru[ 8 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 8 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25CiDeath( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("817"), new BigDecimal(li_jenis),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
                //pb 25 ci
            case 9:
                if( istr_prop.rider_baru[ 9 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = 4;

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Ci( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("817"), new BigDecimal(li_jenis),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                       }
                }
                break;
                //payor spouse
            case 10:
                if( istr_prop.rider_baru[ 10 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = 6;

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorSpouse( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal(li_jenis),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            case 11:
                //hcp family
                if( istr_prop.rider_baru[ 11 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 11 ] + 140 );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamily( params ) );

                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("819"), new BigDecimal( istr_prop.rider_baru[ 11 ] + 140 ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }

                //hcp family peserta tambahan
                if( istr_prop.rider_baru[ 11 ] > 0 && istr_prop.hcpf.peserta > 0 )
                {
                    for( int i = 1; i <= istr_prop.hcpf.peserta; i++ )
                    {
                    	li_usia_tt = istr_prop.hcpf.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 11 ] + 140 );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia_tt );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamilyPesertaTambahan( params ) );
                        ldec_temp = 0;
                    	if (!( i > 1 && li_usia_tt > 24 )){
                    		 ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                    	}
//                        ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                        ldec_total += ldec_temp;
                        Integer lsdbs_hcp_fam = null;
                        if( i == 1 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + 20;
                        }else if( i == 2 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 5 );
                        }
                        if( ai_th == 1 ){
                        	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("819"), new BigDecimal( lsdbs_hcp_fam ),
                        			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        			 new BigDecimal(istr_prop.cb_id),  istr_prop.hcpf.tgl[i], istr_prop.hcpf.usia[i] ) );
                        }
                    }
                }
                break;

            case 12:
                //term rider
                if( istr_prop.rider_baru[ 12 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTermRider( params ) );
                    ldec_temp = FormatNumber.round( ( ( istr_prop.up_term / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("818"), new BigDecimal( "2" ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
                
            case 13:
                //eka sehat
            	li_jenis = istr_prop.rider_baru[13];
                if( istr_prop.rider_baru[ 13 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", li_jenis );  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) );
        			ldec_temp = FormatNumber.round( ldec_rate, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("823"), new BigDecimal( istr_prop.rider_baru[ 13 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }

                //eka sehat peserta tambahan
                if( istr_prop.rider_baru[ 13 ] > 0 && istr_prop.eka_sehat.peserta > 0 )
                {
                	if ( li_jenis > 15 ) li_jenis -= 15;
            		if ( li_jenis > 15 ) li_jenis -= 15; // sekali lagi buat ekasehat kry 31-45
                    for( int i = 1; i <= istr_prop.eka_sehat.peserta; i++ )
                    {
                        li_usia = istr_prop.eka_sehat.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 13 ]  );    
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) );
//                        ldec_temp = FormatNumber.round( (ldec_rate * 0.975 * ldec_pct) * 0.1, 2 );
                    	if( istr_prop.bisnis_id == 183 && (istr_prop.bisnis_no > 30 && istr_prop.bisnis_no < 46) ){
                    		int li_bagi = 100, li_sisa = 0;
                    		ldec_rate = ldec_rate * 0.7;
                    		ldec_rate = FormatNumber.round(ldec_rate * 0.975, 2); //disc 2.5%
                    			li_sisa =  (int) (ldec_rate % li_bagi);
        						if( li_sisa > 0 ){
        							ldec_rate = ldec_rate - li_sisa + li_bagi;
        						}
        						ldec_temp = ldec_rate;
                    	} else if( istr_prop.bisnis_id == 183 || istr_prop.bisnis_id == 193 || istr_prop.bisnis_id == 189 || istr_prop.bisnis_id == 201 ){
                    		ldec_temp = FormatNumber.round((ldec_rate * ldec_kali) * 0.975, 2); //disc 2.5%
                    	}else{
                    		ldec_temp = FormatNumber.round(ldec_rate * 0.975, 2); //disc 2.5%
                    	}
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_e_sehat = null;
                        if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + 15;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 5 );
                        }
                        if( ai_th == 1 ){
                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("823"), new BigDecimal( lsdbs_e_sehat ),
                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                          			 new BigDecimal(istr_prop.cb_id), istr_prop.eka_sehat.tgl[i], istr_prop.eka_sehat.usia[i] ) );
                        }
                    }
                }
                break;
                
            case 15:
            	//eka sehat inner limit
            	if( istr_prop.rider_baru[ 15 ] > 0 && li_usia < 75 )
            	{
            		Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 15 ] );  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ) );
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
                    ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("825"), new BigDecimal( istr_prop.rider_baru[ 15 ] ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
	            }
            	if( istr_prop.rider_baru[ 15 ] > 0 && istr_prop.ekaSehatInnerLimit.peserta > 0 && li_usia < 75 )
            	{
            		for ( int i = 1; i <= istr_prop.ekaSehatInnerLimit.peserta; i++)
            		{
            			li_usia = istr_prop.ekaSehatInnerLimit.usia[ i ] + ai_th - 1;
            			
            			Map<String, Object> params = new HashMap<String, Object>();
            			params.put( "riderBaru", istr_prop.rider_baru[ 15 ] );
            			params.put( "kursId", istr_prop.kurs_id );
            			params.put( "liUsia", li_usia );
            			ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ));
            			ldec_temp = FormatNumber.round((ldec_rate * 0.117), 2);
            			ldec_total += ldec_temp;
            			
            			Integer lsdbs_e_sehat = null;
            			if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + 15;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 5 );
                        }
            			if( ai_th == 1 ){
                         	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("825"), new BigDecimal( lsdbs_e_sehat ),
                         			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                         			 new BigDecimal(istr_prop.cb_id), istr_prop.ekaSehatInnerLimit.tgl[i], istr_prop.ekaSehatInnerLimit.usia[i] ) );
                        }
            		}
            	}
	            break;
                         
            case 16:
                //hcp Provider
                if( istr_prop.rider_baru[ 16 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );   
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProvider( params ) );

                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("826"), new BigDecimal( istr_prop.rider_baru[ 16 ] ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }

                //hcp provider peserta tambahan
                if( istr_prop.rider_baru[ 16 ] > 0 && istr_prop.hcpPro.peserta > 0 && li_usia < 65 )
                {
                    for( int i = 1; i <= istr_prop.hcpPro.peserta; i++ )
                    {
                        li_usia = istr_prop.hcpPro.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );   
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProviderPesertaTambahan( params ) );
                        ldec_temp = 0;
                        if  (i > 1 && li_usia > 24){
                        	
                        }else{
                        	  ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                        }
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_hcp_pro= null;
                        if( i == 1 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + 12;
                        }else if( i == 2 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 5 );
                        }
                        
                        if( ai_th == 1 ){
                        	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("826"), new BigDecimal( lsdbs_hcp_pro ),
                        			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        			 new BigDecimal(istr_prop.cb_id), istr_prop.hcpPro.tgl[i], istr_prop.hcpPro.usia[i] ) );
                       }
                    }
                }
                break;
            case 18:
            	//Payor TPD/CI/Death
            	if( istr_prop.rider_baru[ 18 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 18 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorTpdCiDeath(params) );
                    
                    ldec_max = 200000;
//                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ), 2 );
//                    ldec_temp = FormatNumber.round( ( ( istr_prop.premi * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * 1 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("828"), new BigDecimal( li_jenis ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
            case 17:
            	//Waiver TPD/CI/Death
            	if( istr_prop.rider_baru[ 17 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", istr_prop.rider_baru[ 17 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", 0 );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWaiverTpdCi(params) );
                    
                    ldec_max = 200000;
//                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
//                    ldec_temp = FormatNumber.round( ( ( istr_prop.premi * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * 1 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("827"), new BigDecimal( istr_prop.rider_baru[ 17 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
            case 19:
                //ladies insurance
                if( istr_prop.rider_baru[ 19 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", istr_prop.rider_baru[ 19 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", 0 );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateLadiesIns(params) );
                    
                    ldec_max = 200000;
                    if( istr_prop.kurs_id.equals("01") ){ ldec_max = 2000000000;}
                    double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getLadiesInsChooseCd())) / 100.0;
                    double maxAmount = MathUtil.min( istr_prop.up * persentase, ldec_max );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("830"), new BigDecimal( istr_prop.rider_baru[ 19 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
                
            case 20:
                //hcp ladies
                if( istr_prop.rider_baru[ 20 ] > 0 && istr_prop.hcpLad.peserta == 0)
                {
                	if( li_usia < 66 && istr_prop.hcpLad.peserta == 0 ){
	                    Map<String, Object> params = new HashMap<String, Object>();
	                    params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                    params.put( "kursId", istr_prop.kurs_id );
	                    params.put( "liUsia", li_usia );
	                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadies( params ) );
	
	                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
	                    ldec_total += ldec_temp;
	                    if( ai_th == 1 ){
	                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("831"), new BigDecimal( istr_prop.rider_baru[ 20 ] ),
	                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
	                    }
                	}
                }

                //hcp ladies peserta tambahan
                if( istr_prop.rider_baru[ 20 ] > 0 && istr_prop.hcpLad.peserta > 0 )
                {
                	li_usia = istr_prop.hcpLad.usia[ 1 ] + ai_th - 1;
                	if( li_usia < 66 && istr_prop.hcpLad.peserta > 0 )
                	{
	                    for( int i = 1; i <= istr_prop.hcpLad.peserta; i++ )
	                    {
	                        li_usia = istr_prop.hcpLad.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadiesPesertaTambahan( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
	                        if(i >= 2){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
	                        	if(i>=3 && li_usia>25) ldec_temp = 0;
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_hcp_lad = null;
 	                        if( i == 1 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ];
	                        }else if( i == 2 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 );
	                        }else if( i == 3 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 2 );
	                        }else if( i == 4 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 3 );
	                        }else if( i == 5 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 4 );
	                        }
 	                        if( ai_th == 1 ){
 	 	                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("831"), new BigDecimal( lsdbs_hcp_lad ),
 	 	                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
 	 	                    			 new BigDecimal(istr_prop.cb_id), istr_prop.hcpLad.tgl[i], istr_prop.hcpLad.usia[i] ) );
 	 	                    }
	                    }
                	}
                }
                break;
            case 21:
                //ladies medical expense
                if( istr_prop.rider_baru[ 21 ] > 0 && li_usia < 75 && istr_prop.ladiesMedExpense.peserta == 0)
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider(params));

                    ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("832"), new BigDecimal( istr_prop.rider_baru[ 21 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }

                //ladies medical expense peserta tambahan
                if( istr_prop.rider_baru[ 21 ] > 0 && istr_prop.ladiesMedExpense.peserta > 0 )
                {
                	li_usia = istr_prop.ladiesMedExpense.usia[1] + ai_th - 1;
                	if( li_usia < 75 ){
	                    for( int i = 1; i <= istr_prop.ladiesMedExpense.peserta; i++ )
	                    {
	                        li_usia = istr_prop.ladiesMedExpense.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
	                        if(i > 1){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.117, 2 );
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_ladies_med = null;
	                        if( i == 1 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ];
 	                        }else if( i == 2 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 );
 	                        }else if( i == 3 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 2 );
 	                        }else if( i == 4 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 3 );
 	                        }else if( i == 5 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 4 );
 	                        }
 	                        
	                        if( ai_th == 1 ){
	                       	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("832"), new BigDecimal( lsdbs_ladies_med ),
	                       			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                       			 new BigDecimal(istr_prop.cb_id), istr_prop.ladiesMedExpense.tgl[i], istr_prop.ladiesMedExpense.usia[i] ) );
	                       }
	                    }
                	}
                }
                break;
            case 22:
                //ladies medical expense inner limit
                if( istr_prop.rider_baru[ 22 ] > 0 && li_usia < 75 && istr_prop.ladiesMedExpenseInnerLimit.peserta == 0)
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider(params));

                    ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("833"), new BigDecimal( istr_prop.rider_baru[ 22 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }

                //ladies medical expense inner limit peserta tambahan
                if( istr_prop.rider_baru[ 22 ] > 0 && istr_prop.ladiesMedExpenseInnerLimit.peserta > 0 )
                {
                	li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[1] + ai_th - 1;
                	if( li_usia < 75  ){
	                    for( int i = 1; i <= istr_prop.ladiesMedExpenseInnerLimit.peserta; i++ )
	                    {
	                        li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
	                        if(i > 1){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.117, 2 );
	                        }
	                        ldec_total += ldec_temp;
	                        Integer lsdbs_ladies_med_il = null;
	                        if( i == 1 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ];
 	                        }else if( i == 2 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 );
 	                        }else if( i == 3 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 2 );
 	                        }else if( i == 4 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 3 );
 	                        }else if( i == 5 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 4 );
 	                        }
 	                        
	                        if( ai_th == 1 ){
	                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("833"), new BigDecimal( lsdbs_ladies_med_il ),
	                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                          			 new BigDecimal(istr_prop.cb_id), istr_prop.ladiesMedExpenseInnerLimit.tgl[i], istr_prop.ladiesMedExpenseInnerLimit.usia[i] ) );
	                        }
	                    }
                	}
                }
                break;
            case 23:
            	//Payor 5/10 TPD/CI/Death
            	if( istr_prop.rider_baru[ 23 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                	if(  cepr01030102Form.getPremiumFurloughYear() == 5 ){
            			istr_prop.rider_baru[23] = 2;
            		}else if(  cepr01030102Form.getPremiumFurloughYear() == 10 ){
            			istr_prop.rider_baru[23] = 3;
            		}
                    li_jenis = istr_prop.rider_baru[ 23 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorTpdCiDeath(params) );
                    
                    		
                    		
                    ldec_max = 200000;
                    double ldec_pct_pp = 1;
                    if( istr_prop.kurs_id == "01" ) ldec_max = 2000000000;
                    ldec_temp = FormatNumber.round( ( ( ( ( istr_prop.premi * ldec_pct_pp ) * li_kali / 1000 ) * ldec_rate)  * 0.1 ), 2 );
                	if( istr_prop.bisnis_id == 120 ||  istr_prop.bisnis_id == 202){
                	    ldec_temp = FormatNumber.round( ( ( ( ldec_rate / 1000 ) * ( istr_prop.premi * li_kali)) * 0.1 ), 2 );
                	}
                    ldec_total += ldec_temp;
                    
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("828"), new BigDecimal( li_jenis ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
                
            case 24:
            	//Waiver 5/10 TPD/CI
            	if( istr_prop.rider_baru[ 24 ] > 0 )
                {
            		li_usia = istr_prop.umur_tt + ai_th - 1;
            		if(  cepr01030102Form.getPremiumFurloughYear() == 5 ){
            			istr_prop.rider_baru[24] = 4;
            		}else if(  cepr01030102Form.getPremiumFurloughYear() == 10 ){
            			istr_prop.rider_baru[24] = 5;
            		}
            		li_jenis = istr_prop.rider_baru[24]	;
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", ai_th );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWaiverTpdCi(params) );
                    
                    ldec_max = 200000;
//            		ldec_temp = Round(((ldec_rate/1000)*(istr_prop.premi *li_kali))*0.1, 2)
                    ldec_temp = FormatNumber.round( ( ( ldec_rate / 1000 )*( istr_prop.premi * li_kali ) ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("827"), new BigDecimal( li_jenis ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            default:
                break;
        }
        return ldec_total;
    }
    
    
    public double of_premi_rider_cost( int ai_jenis, int ai_th, List< Mst_proposal > mstProposal )
    {
        double ldec_total = 0, ldec_temp, ldec_kali = 1;
        double ldec_rate;
        int li_usia, li_tahun_ke, li_kali = 1, li_jenis, li_class, li_usia_tt;
        li_usia = istr_prop.umur_tt + ai_th - 1;
        
        double ldec_pct = 1;
        if(ai_jenis != 0){
        	if(istr_prop.cb_id == 1 ){  //triwulan
        		li_kali = 4;
        		ldec_pct = 0.27;
        	}else if( istr_prop.cb_id == 2){ //semesteran
        		li_kali = 2;
        		ldec_pct = 0.525;
        	}else if( istr_prop.cb_id == 6){ //bulanan
        		li_kali = 12;
        		ldec_pct = 0.1;
        	}
        }

        if( istr_prop.rider_baru[ 5 ] > 0 || istr_prop.rider_baru[ 6 ] > 0 || istr_prop.rider_baru[ 7 ] > 0 || istr_prop.rider_baru[ 8 ] > 0 || istr_prop.rider_baru[ 9 ] > 0 || istr_prop.rider_baru[ 10 ] > 0 )
        {
            if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_TRIWULANAN )
            {
                li_kali = 4;
            }
            else if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_SEMESTERAN )
            {
                li_kali = 2;
            }
            else if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_BULANAN )
            {
                li_kali = 12;
            }
        }
//
        switch( ai_jenis )
        {
            case 1:
                //pa
                if( ( istr_prop.rider_baru[ 1 ] > 0 ) && ( li_usia < 60 ) )
                {
                    li_class = istr_prop.pa_class;
                    if( istr_prop.umur_tt <= 16 ) li_class = 2;
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "paRisk", istr_prop.pa_risk );
                    params.put( "liClass", li_class );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePa( params ) );
                    double maks = 2000000000.0;
                    if( istr_prop.kurs_id.equals( Hardcode.CUR_USD_CD ) ){
                    	 maks = 200000.0;
                    }
                    double maxAmount = MathUtil.min( istr_prop.rider_baru[ 1 ] * istr_prop.up, maks );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("810"), new BigDecimal( istr_prop.pa_risk ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
            case 2:
                //hcp
                if( ( istr_prop.rider_baru[ 2 ] > 0 ) && ( li_usia < 65 ) )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 2 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );

                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcp( params ) );
                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("811"), new BigDecimal(istr_prop.rider_baru[ 2 ]),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
            case 3:
                //tpd
                if( istr_prop.rider_baru[ 3 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTpd( params ) );
                    // maks 2 milyar
                    if( "01".equals( istr_prop.kurs_id ) ){
                    	ldec_max = 2000000000;
                    }else if( "02".equals( istr_prop.kurs_id ) ){
                    	ldec_max = 200000;
                    }
                    double maxAmount = MathUtil.min( istr_prop.up, ldec_max );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("812"), new BigDecimal( "7" ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                  }
                }
                break;
            case 4:
                //ci
                if( istr_prop.rider_baru[ 4 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateCi( params ) );
//                    double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getCiChooseCd())) / 100.0;
                    /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
                    double upCi = cepr01030103Form.getCiRiderAmount().doubleValue();
                    double maxUp;
                    if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) ){
                    	maxUp = 500000.0;
                    }else{
                    	maxUp = 5000000000.0;
                    }
                    double temp = MathUtil.min( upCi, maxUp );
                    ldec_temp = FormatNumber.round( ( temp / 1000 * ldec_rate ) * 0.1, 2 );
//                    ldec_temp = FormatNumber.round( ( ( istr_prop.up * persentase / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("813"), new BigDecimal("8"),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
                //
            case 5:
                //wp 60tpd
                if( istr_prop.rider_baru[ 5 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    if( istr_prop.bisnis_id == 169 ){
                    	if( cepr01030102Form.getTermOfContract().equals(5)) {
                    		li_jenis = 6;
                    	}else if( cepr01030102Form.getTermOfContract().equals(10)) {
                    		li_jenis = 7;
                    	}else if( cepr01030102Form.getTermOfContract().equals(15)) {
                    		li_jenis = 8;
                    	}else if( cepr01030102Form.getTermOfContract().equals(20)) {
                    		li_jenis = 9;
                    	}else{
                    		li_jenis = 6;
                    	}
                    	
                    }else{
                    	li_jenis = istr_prop.rider_baru[ 5 ];
                    }
                    params.put( "riderBaru", li_jenis);
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp60Tpd( params ) );

                    ldec_temp = FormatNumber.round(  ldec_rate , 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("814"), new BigDecimal( li_jenis ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
            case 6:
                //pb 25 tpd
                if( istr_prop.rider_baru[ 6 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 6 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Tpd( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal( li_jenis ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
            case 7:
                //wp 60ci
                if( istr_prop.rider_baru[ 7 ] > 0 )
                {

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 7 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp60Ci( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("816"), new BigDecimal( istr_prop.rider_baru[ 7 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
            case 8:
                //pb 25 ci/death
                if( istr_prop.rider_baru[ 8 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 8 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25CiDeath( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("817"), new BigDecimal(li_jenis),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
                //pb 25 ci
            case 9:
                if( istr_prop.rider_baru[ 9 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = 4;

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Ci( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("817"), new BigDecimal(li_jenis),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                       }
                }
                break;
                //payor spouse
            case 10:
                if( istr_prop.rider_baru[ 10 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = 6;

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorSpouse( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal(li_jenis),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            case 11:
                //hcp family
                if( istr_prop.rider_baru[ 11 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 11 ] + 140 );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamily( params ) );

                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("819"), new BigDecimal( istr_prop.rider_baru[ 11 ] + 140 ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }

                //hcp family peserta tambahan
                if( istr_prop.rider_baru[ 11 ] > 0 && istr_prop.hcpf.peserta > 0 )
                {
                    for( int i = 1; i <= istr_prop.hcpf.peserta; i++ )
                    {
                    	li_usia_tt = istr_prop.hcpf.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 11 ] + 140 );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia_tt );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamilyPesertaTambahan( params ) );
                        ldec_temp = 0;
                    	if (!( i > 1 && li_usia_tt > 24 )){
                    		 ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                    	}
//                        ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                        ldec_total += ldec_temp;
                        Integer lsdbs_hcp_fam = null;
                        if( i == 1 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + 20;
                        }else if( i == 2 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 5 );
                        }
                        if( ai_th == 1 ){
                        	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("819"), new BigDecimal( lsdbs_hcp_fam ),
                        			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        			 new BigDecimal(istr_prop.cb_id),  istr_prop.hcpf.tgl[i], istr_prop.hcpf.usia[i] ) );
                        }
                    }
                }
                break;

            case 12:
                //term rider
                if( istr_prop.rider_baru[ 12 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTermRider( params ) );
                    ldec_temp = FormatNumber.round( ( ( istr_prop.up_term / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("818"), new BigDecimal( "2" ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
                
            case 13:
                //eka sehat
            	li_jenis = istr_prop.rider_baru[13];
                if( istr_prop.rider_baru[ 13 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", li_jenis );  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) );
        			ldec_temp = FormatNumber.round( ldec_rate, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("823"), new BigDecimal( istr_prop.rider_baru[ 13 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }

                //eka sehat peserta tambahan
                if( istr_prop.rider_baru[ 13 ] > 0 && istr_prop.eka_sehat.peserta > 0 )
                {
                	if ( li_jenis > 15 ) li_jenis -= 15;
            		if ( li_jenis > 15 ) li_jenis -= 15; // sekali lagi buat ekasehat kry 31-45
                    for( int i = 1; i <= istr_prop.eka_sehat.peserta; i++ )
                    {
                        li_usia = istr_prop.eka_sehat.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 13 ]  );    
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) );
//                        ldec_temp = FormatNumber.round( (ldec_rate * 0.975 * ldec_pct) * 0.1, 2 );
                    	if( istr_prop.bisnis_id == 183 && (istr_prop.bisnis_no > 30 && istr_prop.bisnis_no < 46) ){
                    		int li_bagi = 100, li_sisa = 0;
                    		ldec_rate = ldec_rate * 0.7;
                    		ldec_rate = FormatNumber.round(ldec_rate * 0.975, 2); //disc 2.5%
                    			li_sisa =  (int) (ldec_rate % li_bagi);
        						if( li_sisa > 0 ){
        							ldec_rate = ldec_rate - li_sisa + li_bagi;
        						}
        						ldec_temp = ldec_rate;
                    	} else if( istr_prop.bisnis_id == 183 || istr_prop.bisnis_id == 193 || istr_prop.bisnis_id == 189 || istr_prop.bisnis_id == 201 ){
                    		ldec_temp = FormatNumber.round((ldec_rate * ldec_kali) * 0.975, 2); //disc 2.5%
                    	}else{
                    		ldec_temp = FormatNumber.round(ldec_rate * 0.975, 2); //disc 2.5%
                    	}
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_e_sehat = null;
                        if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + 15;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 5 );
                        }
                        if( ai_th == 1 ){
                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("823"), new BigDecimal( lsdbs_e_sehat ),
                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                          			 new BigDecimal(istr_prop.cb_id), istr_prop.eka_sehat.tgl[i], istr_prop.eka_sehat.usia[i] ) );
                        }
                    }
                }
                break;
                
            case 15:
            	//eka sehat inner limit
            	if( istr_prop.rider_baru[ 15 ] > 0 && li_usia < 75 )
            	{
            		Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 15 ] );  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ) );
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
                    ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("825"), new BigDecimal( istr_prop.rider_baru[ 15 ] ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
	            }
            	if( istr_prop.rider_baru[ 15 ] > 0 && istr_prop.ekaSehatInnerLimit.peserta > 0 && li_usia < 75 )
            	{
            		for ( int i = 1; i <= istr_prop.ekaSehatInnerLimit.peserta; i++)
            		{
            			li_usia = istr_prop.ekaSehatInnerLimit.usia[ i ] + ai_th - 1;
            			
            			Map<String, Object> params = new HashMap<String, Object>();
            			params.put( "riderBaru", istr_prop.rider_baru[ 15 ] );
            			params.put( "kursId", istr_prop.kurs_id );
            			params.put( "liUsia", li_usia );
            			ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ));
            			ldec_temp = FormatNumber.round((ldec_rate * 0.117), 2);
            			ldec_total += ldec_temp;
            			
            			Integer lsdbs_e_sehat = null;
            			if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + 15;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 5 );
                        }
            			if( ai_th == 1 ){
                         	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("825"), new BigDecimal( lsdbs_e_sehat ),
                         			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                         			 new BigDecimal(istr_prop.cb_id), istr_prop.ekaSehatInnerLimit.tgl[i], istr_prop.ekaSehatInnerLimit.usia[i] ) );
                        }
            		}
            	}
	            break;
                         
            case 16:
                //hcp Provider
                if( istr_prop.rider_baru[ 16 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );   
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProvider( params ) );

                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("826"), new BigDecimal( istr_prop.rider_baru[ 16 ] ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }

                //hcp provider peserta tambahan
                if( istr_prop.rider_baru[ 16 ] > 0 && istr_prop.hcpPro.peserta > 0 && li_usia < 65 )
                {
                    for( int i = 1; i <= istr_prop.hcpPro.peserta; i++ )
                    {
                        li_usia = istr_prop.hcpPro.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );   
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProviderPesertaTambahan( params ) );
                        ldec_temp = 0;
                        if  (i > 1 && li_usia > 24){
                        	
                        }else{
                        	  ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                        }
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_hcp_pro= null;
                        if( i == 1 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + 12;
                        }else if( i == 2 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 5 );
                        }
                        
                        if( ai_th == 1 ){
                        	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("826"), new BigDecimal( lsdbs_hcp_pro ),
                        			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        			 new BigDecimal(istr_prop.cb_id), istr_prop.hcpPro.tgl[i], istr_prop.hcpPro.usia[i] ) );
                       }
                    }
                }
                break;
            case 18:
            	//Payor TPD/CI/Death
            	if( istr_prop.rider_baru[ 18 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 18 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorTpdCiDeath(params) );
                    
                    ldec_max = 200000;
//                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ), 2 );
//                    ldec_temp = FormatNumber.round( ( ( istr_prop.premi * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * 1 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("828"), new BigDecimal( li_jenis ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
            case 17:
            	//Waiver TPD/CI/Death
            	if( istr_prop.rider_baru[ 17 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", istr_prop.rider_baru[ 17 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", 0 );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWaiverTpdCi(params) );
                    
                    ldec_max = 200000;
//                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
//                    ldec_temp = FormatNumber.round( ( ( istr_prop.premi * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * 1 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("827"), new BigDecimal( istr_prop.rider_baru[ 17 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
            case 19:
                //ladies insurance
                if( istr_prop.rider_baru[ 19 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", istr_prop.rider_baru[ 19 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", 0 );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateLadiesIns(params) );
                    
                    ldec_max = 200000;
                    if( istr_prop.kurs_id.equals("01") ){ ldec_max = 2000000000;}
                    double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getLadiesInsChooseCd())) / 100.0;
                    double maxAmount = MathUtil.min( istr_prop.up * persentase, ldec_max );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("830"), new BigDecimal( istr_prop.rider_baru[ 19 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
                
            case 20:
                //hcp ladies
                if( istr_prop.rider_baru[ 20 ] > 0 && istr_prop.hcpLad.peserta == 0)
                {
                	if( li_usia < 66 && istr_prop.hcpLad.peserta == 0 ){
	                    Map<String, Object> params = new HashMap<String, Object>();
	                    params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                    params.put( "kursId", istr_prop.kurs_id );
	                    params.put( "liUsia", li_usia );
	                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadies( params ) );
	
	                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
	                    ldec_total += ldec_temp;
	                    if( ai_th == 1 ){
	                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("831"), new BigDecimal( istr_prop.rider_baru[ 20 ] ),
	                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
	                    }
                	}
                }

                //hcp ladies peserta tambahan
                if( istr_prop.rider_baru[ 20 ] > 0 && istr_prop.hcpLad.peserta > 0 )
                {
                	li_usia = istr_prop.hcpLad.usia[ 1 ] + ai_th - 1;
                	if( li_usia < 66 && istr_prop.hcpLad.peserta > 0 )
                	{
	                    for( int i = 1; i <= istr_prop.hcpLad.peserta; i++ )
	                    {
	                        li_usia = istr_prop.hcpLad.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadiesPesertaTambahan( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
	                        if(i >= 2){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
	                        	if(i>=3 && li_usia>25) ldec_temp = 0;
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_hcp_lad = null;
 	                        if( i == 1 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ];
	                        }else if( i == 2 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 );
	                        }else if( i == 3 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 2 );
	                        }else if( i == 4 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 3 );
	                        }else if( i == 5 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 4 );
	                        }
 	                        if( ai_th == 1 ){
 	 	                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("831"), new BigDecimal( lsdbs_hcp_lad ),
 	 	                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
 	 	                    			 new BigDecimal(istr_prop.cb_id), istr_prop.hcpLad.tgl[i], istr_prop.hcpLad.usia[i] ) );
 	 	                    }
	                    }
                	}
                }
                break;
            case 21:
                //ladies medical expense
                if( istr_prop.rider_baru[ 21 ] > 0 && li_usia < 75 && istr_prop.ladiesMedExpense.peserta == 0)
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider(params));

                    ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("832"), new BigDecimal( istr_prop.rider_baru[ 21 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }

                //ladies medical expense peserta tambahan
                if( istr_prop.rider_baru[ 21 ] > 0 && istr_prop.ladiesMedExpense.peserta > 0 )
                {
                	li_usia = istr_prop.ladiesMedExpense.usia[1] + ai_th - 1;
                	if( li_usia < 75 ){
	                    for( int i = 1; i <= istr_prop.ladiesMedExpense.peserta; i++ )
	                    {
	                        li_usia = istr_prop.ladiesMedExpense.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
	                        if(i > 1){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.117, 2 );
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_ladies_med = null;
	                        if( i == 1 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ];
 	                        }else if( i == 2 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 );
 	                        }else if( i == 3 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 2 );
 	                        }else if( i == 4 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 3 );
 	                        }else if( i == 5 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 4 );
 	                        }
 	                        
	                        if( ai_th == 1 ){
	                       	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("832"), new BigDecimal( lsdbs_ladies_med ),
	                       			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                       			 new BigDecimal(istr_prop.cb_id), istr_prop.ladiesMedExpense.tgl[i], istr_prop.ladiesMedExpense.usia[i] ) );
	                       }
	                    }
                	}
                }
                break;
            case 22:
                //ladies medical expense inner limit
                if( istr_prop.rider_baru[ 22 ] > 0 && li_usia < 75 && istr_prop.ladiesMedExpenseInnerLimit.peserta == 0)
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider(params));

                    ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("833"), new BigDecimal( istr_prop.rider_baru[ 22 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }

                //ladies medical expense inner limit peserta tambahan
                if( istr_prop.rider_baru[ 22 ] > 0 && istr_prop.ladiesMedExpenseInnerLimit.peserta > 0 )
                {
                	li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[1] + ai_th - 1;
                	if( li_usia < 75  ){
	                    for( int i = 1; i <= istr_prop.ladiesMedExpenseInnerLimit.peserta; i++ )
	                    {
	                        li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
	                        if(i > 1){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.117, 2 );
	                        }
	                        ldec_total += ldec_temp;
	                        Integer lsdbs_ladies_med_il = null;
	                        if( i == 1 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ];
 	                        }else if( i == 2 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 );
 	                        }else if( i == 3 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 2 );
 	                        }else if( i == 4 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 3 );
 	                        }else if( i == 5 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 4 );
 	                        }
 	                        
	                        if( ai_th == 1 ){
	                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("833"), new BigDecimal( lsdbs_ladies_med_il ),
	                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                          			 new BigDecimal(istr_prop.cb_id), istr_prop.ladiesMedExpenseInnerLimit.tgl[i], istr_prop.ladiesMedExpenseInnerLimit.usia[i] ) );
	                        }
	                    }
                	}
                }
                break;
            case 23:
            	//Payor 5/10 TPD/CI/Death
            	if( istr_prop.rider_baru[ 23 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                	if(  cepr01030102Form.getPremiumFurloughYear() == 5 ){
            			istr_prop.rider_baru[23] = 2;
            		}else if(  cepr01030102Form.getPremiumFurloughYear() == 10 ){
            			istr_prop.rider_baru[23] = 3;
            		}
                    li_jenis = istr_prop.rider_baru[ 23 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorTpdCiDeath(params) );
                    
                    		
                    		
                    ldec_max = 200000;
                    double ldec_pct_pp = 1;
                    if( istr_prop.kurs_id == "01" ) ldec_max = 2000000000;
                    ldec_temp = FormatNumber.round( ( ( ( ( istr_prop.premi * ldec_pct_pp ) * li_kali / 1000 ) * ldec_rate)  * 0.1 ), 2 );
                	if( istr_prop.bisnis_id == 120 ||  istr_prop.bisnis_id == 202){
                	    ldec_temp = FormatNumber.round( ( ( ( ldec_rate / 1000 ) * ( istr_prop.premi * li_kali)) * 0.1 ), 2 );
                	}
                    ldec_total += ldec_temp;
                    
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("828"), new BigDecimal( li_jenis ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
                
            case 24:
            	//Waiver 5/10 TPD/CI
            	if( istr_prop.rider_baru[ 24 ] > 0 )
                {
            		li_usia = istr_prop.umur_tt + ai_th - 1;
            		if(  cepr01030102Form.getPremiumFurloughYear() == 5 ){
            			istr_prop.rider_baru[24] = 4;
            		}else if(  cepr01030102Form.getPremiumFurloughYear() == 10 ){
            			istr_prop.rider_baru[24] = 5;
            		}
            		li_jenis = istr_prop.rider_baru[24]	;
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", ai_th );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWaiverTpdCi(params) );
                    
                    ldec_max = 200000;
//            		ldec_temp = Round(((ldec_rate/1000)*(istr_prop.premi *li_kali))*0.1, 2)
                    ldec_temp = FormatNumber.round( ( ( ldec_rate / 1000 )*( istr_prop.premi * li_kali ) ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("827"), new BigDecimal( li_jenis ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            default:
                break;
        }
        cepr01031001Form.setMstProposal( mstProposal );
        return ldec_total;
    }
    
    public double of_get_coi_rider_cost( int ai_jenis, int ai_th, List< Mst_proposal > mstProposal )
    {
        double ldec_total = 0, ldec_temp;
        double ldec_rate;
        int li_usia, li_tahun_ke, li_kali = 1, li_jenis, li_class, li_usia_tt, li_usia_pp, li_risk, li_temp;;
        li_usia = istr_prop.umur_tt + ai_th - 1;
        li_usia_pp = istr_prop.umur_pp + ai_th - 1;
        
        double ldec_pct = 1;
        if(ai_jenis != 0){
        	if(istr_prop.cb_id == 1 ){  //triwulan
        		li_kali = 4;
        		ldec_pct = 0.27;
        	}else if( istr_prop.cb_id == 2){ //semesteran
        		li_kali = 2;
        		ldec_pct = 0.525;
        	}else if( istr_prop.cb_id == 6){ //bulanan
        		li_kali = 12;
        		ldec_pct = 0.1;
        	}
        }

        if( istr_prop.rider_baru[ 5 ] > 0 || istr_prop.rider_baru[ 6 ] > 0 || istr_prop.rider_baru[ 7 ] > 0 || istr_prop.rider_baru[ 8 ] > 0 || istr_prop.rider_baru[ 9 ] > 0 || istr_prop.rider_baru[ 10 ] > 0 )
        {
            if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_TRIWULANAN )
            {
                li_kali = 4;
            }
            else if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_SEMESTERAN )
            {
                li_kali = 2;
            }
            else if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_BULANAN )
            {
                li_kali = 12;
            }
        }
//
        switch( ai_jenis )
        {
            case 1:
                //pa
                if( ( istr_prop.rider_baru[ 1 ] > 0 ) && ( li_usia < 60 ) )
                {
                	 li_class = istr_prop.pa_class;
                     li_risk = 1;
                     li_temp = li_usia;
                 	if(istr_prop.bisnis_id == 120  || istr_prop.bisnis_id == 202 || istr_prop.bisnis_id == 141){
                 		//khusus 120-cerdas usia tdk naik
                 		li_risk = istr_prop.pa_risk;
                 		li_temp = istr_prop.umur_tt;
                 	}
                   //  if(istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 141 || istr_prop.bisnis_id == 202 || istr_prop.bisnis_id == 116 ){
                    	 if(li_temp <= 16){li_class = 2;}
                   /* }
                      
                    if(istr_prop.bisnis_id == 159 || istr_prop.bisnis_id == 190 || istr_prop.bisnis_id == 200 || istr_prop.bisnis_id == 220){
                  		//if(li_temp <= 16){li_class = 2;}else{li_class = 1;}
                  		if(li_temp <= 17){li_class = 2;}
                  		if(li_temp > 17 && li_temp < 56){li_class = 3;}                  		
              		}
                    if( istr_prop.bisnis_id == 220 ){
                  		if(li_temp <= 17){li_class = 2;}else{li_class = 1;}
              		}     */                              
                    
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put( "paRisk", istr_prop.pa_risk );
                    params.put( "liClass", li_class );
                    params.put("liSex", li_sex); //-- DONE
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePa( params ) );
                    double maks = 2000000000.0;
                    if( istr_prop.kurs_id.equals( Hardcode.CUR_USD_CD ) ){
                    	 maks = 200000.0;
                    }
                    double maxAmount = MathUtil.min( istr_prop.rider_baru[ 1 ] * istr_prop.up, maks );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("810"), new BigDecimal( istr_prop.pa_risk ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            case 2:
                //hcp
                if( ( istr_prop.rider_baru[ 2 ] > 0 ) && ( li_usia < 65 ) )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 2 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );

                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcp( params ) );
                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("811"), new BigDecimal(istr_prop.rider_baru[ 2 ]),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                  }
                }
                break;
            case 3:
                //tpd
                if( istr_prop.rider_baru[ 3 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    params.put("liSex", li_sex); //-- DONE	
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTpd( params ) );
                    // maks 2 milyar
                    if( "01".equals( istr_prop.kurs_id ) ){
                    	ldec_max = 2000000000;
                    }else if( "02".equals( istr_prop.kurs_id ) ){
                    	ldec_max = 200000;
                    }
                    double maxAmount = MathUtil.min( istr_prop.up, ldec_max );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("812"), new BigDecimal( "7" ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
            case 4:
                //ci
                if( istr_prop.rider_baru[ 4 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }	
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    params.put("liSex", li_sex); //-- DONE
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateCi( params ) );
//                    double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getCiChooseCd())) / 100.0;
                    /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
                    double upCi = cepr01030103Form.getCiRiderAmount().doubleValue();
                    double maxUp;
                    if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) ){
                    	maxUp = 500000.0;
                    }else{
                    	maxUp = 5000000000.0;
                    }
                    double temp = MathUtil.min( upCi, maxUp );
                    ldec_temp = FormatNumber.round( ( temp / 1000 * ldec_rate ) * 0.1, 2 );
//                    ldec_temp = FormatNumber.round( ( ( istr_prop.up * persentase / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("813"), new BigDecimal("8"),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
                //
            case 5:
                //wp 60tpd
                if( istr_prop.rider_baru[ 5 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 5 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp60Tpd( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("814"), new BigDecimal( istr_prop.rider_baru[ 5 ] ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }
                break;
            case 6:
                //pb 25 tpd
                if( istr_prop.rider_baru[ 6 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 6 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Tpd( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal( li_jenis ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
            case 7:
                //wp 60ci
                if( istr_prop.rider_baru[ 7 ] > 0 )
                {

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 7 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp60Ci( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("816"), new BigDecimal( istr_prop.rider_baru[ 7 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
            case 8:
                //pb 25 ci/death
                if( istr_prop.rider_baru[ 8 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 8 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25CiDeath( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("817"), new BigDecimal(li_jenis),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
                //pb 25 ci
            case 9:
                if( istr_prop.rider_baru[ 9 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = 4;

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Ci( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("817"), new BigDecimal(li_jenis),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                       }
                }
                break;
                //payor spouse
            case 10:
                if( istr_prop.rider_baru[ 10 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = 6;

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorSpouse( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal(li_jenis),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            case 11:
                //hcp family
                if( istr_prop.rider_baru[ 11 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 11 ] + 140 );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamily( params ) );

                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("819"), new BigDecimal( istr_prop.rider_baru[ 11 ] + 140 ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }

                //hcp family peserta tambahan
                if( istr_prop.rider_baru[ 11 ] > 0 && istr_prop.hcpf.peserta > 0 )
                {
                    for( int i = 1; i <= istr_prop.hcpf.peserta; i++ )
                    {
                    	li_usia_tt = istr_prop.hcpf.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 11 ] + 140 );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia_tt );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamilyPesertaTambahan( params ) );
                        ldec_temp = 0;
                    	if (!( i > 1 && li_usia_tt > 24 ) ){
                    		 ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                    	}
//                        ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                        ldec_total += ldec_temp;
                        Integer lsdbs_hcp_fam = null;
                        if( i == 1 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + 20;
                        }else if( i == 2 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 5 );
                        }
                        if( ai_th == 1 ){
                        	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("819"), new BigDecimal( lsdbs_hcp_fam ),
                        			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        			 new BigDecimal(istr_prop.cb_id),  istr_prop.hcpf.tgl[i], istr_prop.hcpf.usia[i] ) );
                        }
                    }
                }
                break;

            case 12:
                //term rider
                if( istr_prop.rider_baru[ 12 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTermRider( params ) );
                    ldec_temp = FormatNumber.round( ( ( istr_prop.up_term / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("818"), new BigDecimal( "2" ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
                
            case 13:
                //eka sehat
                if( istr_prop.rider_baru[ 13 ] > 0 && li_usia < 75 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                   
                    if(cepr01030101Form.getProposalUserCd().equals(Hardcode.DMTM_MSAG_ID))
                    {
                    	 params.put( "riderBaru", istr_prop.rider_baru[ 13 ] + 105 );
                    }else{
                    	if( istr_prop.rider_baru[ 13 ] == 16 || istr_prop.rider_baru[ 13 ] == 17 ){
                    		params.put( "riderBaru", istr_prop.rider_baru[ 13 ] + 195 );                    		
                    	}else{
                    		 params.put( "riderBaru", istr_prop.rider_baru[ 13 ] );
                    	}
                    }                  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) );
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
//                    ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
        			ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("823"), new BigDecimal( istr_prop.rider_baru[ 13 ] ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }

                //eka sehat peserta tambahan
                if( istr_prop.rider_baru[ 13 ] > 0 && istr_prop.eka_sehat.peserta > 0   && li_usia < 75 )
                {
                    for( int i = 1; i <= istr_prop.eka_sehat.peserta; i++ )
                    {
                        li_usia = istr_prop.eka_sehat.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        if( istr_prop.rider_baru[ 13 ] == 16 || istr_prop.rider_baru[ 13 ] == 17 ){
                    		params.put( "riderBaru", istr_prop.rider_baru[ 13 ] + 195 );                    		
                    	}else{
                    		 params.put( "riderBaru", istr_prop.rider_baru[ 13 ] );
                    	}
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) );
//                        ldec_temp = FormatNumber.round( (ldec_rate * 0.975 * ldec_pct) * 0.1, 2 );
                        ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_e_sehat = null;
                        if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + 15;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 5 );
                        }
                        if( ai_th == 1 ){
                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("823"), new BigDecimal( lsdbs_e_sehat ),
                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                          			 new BigDecimal(istr_prop.cb_id), istr_prop.eka_sehat.tgl[i], istr_prop.eka_sehat.usia[i] ) );
                        }
                    }
                }
                break;
                
            case 15:
            	//eka sehat inner limit
            	if( istr_prop.rider_baru[ 15 ] > 0 && li_usia < 75 )
            	{
            		Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 15 ] );  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ) );
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
                    ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("825"), new BigDecimal( istr_prop.rider_baru[ 15 ] ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
	            }
            	if( istr_prop.rider_baru[ 15 ] > 0 && istr_prop.ekaSehatInnerLimit.peserta > 0 && li_usia < 75 )
            	{
            		for ( int i = 1; i <= istr_prop.ekaSehatInnerLimit.peserta; i++)
            		{
            			li_usia = istr_prop.ekaSehatInnerLimit.usia[ i ] + ai_th - 1;
            			
            			Map<String, Object> params = new HashMap<String, Object>();
            			params.put( "riderBaru", istr_prop.rider_baru[ 15 ] );
            			params.put( "kursId", istr_prop.kurs_id );
            			params.put( "liUsia", li_usia );
            			ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ));
            			ldec_temp = FormatNumber.round((ldec_rate * 0.117), 2);
            			ldec_total += ldec_temp;
            			
            			Integer lsdbs_e_sehat = null;
            			if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + 15;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 5 );
                        }
            			if( ai_th == 1 ){
                         	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("825"), new BigDecimal( lsdbs_e_sehat ),
                         			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                         			 new BigDecimal(istr_prop.cb_id), istr_prop.ekaSehatInnerLimit.tgl[i], istr_prop.ekaSehatInnerLimit.usia[i] ) );
                        }
            		}
            	}
	            break;
                         
            case 16:
                //hcp Provider
                if( istr_prop.rider_baru[ 16 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );   
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProvider( params ) );

                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("826"), new BigDecimal( istr_prop.rider_baru[ 16 ] ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }

                //hcp provider peserta tambahan
                if( istr_prop.rider_baru[ 16 ] > 0 && istr_prop.hcpPro.peserta > 0 && li_usia < 65 )
                {
                    for( int i = 1; i <= istr_prop.hcpPro.peserta; i++ )
                    {
                        li_usia = istr_prop.hcpPro.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );   
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProviderPesertaTambahan( params ) );
                        ldec_temp = 0;
                        if  (i > 1 && li_usia > 24){
                        	
                        }else{
                        	  ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                        }
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_hcp_pro= null;
                        if( i == 1 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + 12;
                        }else if( i == 2 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 5 );
                        }
                        
                        if( ai_th == 1 ){
                        	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("826"), new BigDecimal( lsdbs_hcp_pro ),
                        			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        			 new BigDecimal(istr_prop.cb_id), istr_prop.hcpPro.tgl[i], istr_prop.hcpPro.usia[i] ) );
                       }
                    }
                }
                break;
            case 18:
            	//Payor TPD/CI/Death
            	if( istr_prop.rider_baru[ 18 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 18 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getPolicyHolderSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    params.put("liSex", li_sex); //-- DONE
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorTpdCiDeath(params) );
                    
                    ldec_max = 200000;
//                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ), 2 );
//                    ldec_temp = FormatNumber.round( ( ( istr_prop.premi * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * 1 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("828"), new BigDecimal( li_jenis ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
            case 17:
            	//Waiver TPD/CI/Death
            	if( istr_prop.rider_baru[ 17 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put( "liJenis", istr_prop.rider_baru[ 17 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", 0 );
                    params.put( "liUsia", li_usia );
                    params.put("liSex", li_sex); //-- DONE
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWaiverTpdCi(params) );
                    
                    ldec_max = 200000;
//                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
//                    ldec_temp = FormatNumber.round( ( ( istr_prop.premi * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * 1 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("827"), new BigDecimal( istr_prop.rider_baru[ 17 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
            case 19:
                //ladies insurance
                if( istr_prop.rider_baru[ 19 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", istr_prop.rider_baru[ 19 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", 0 );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateLadiesIns(params) );
                    
                    ldec_max = 200000;
                    if( istr_prop.kurs_id.equals("01") ){ ldec_max = 2000000000;}
                    double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getLadiesInsChooseCd())) / 100.0;
                    double maxAmount = MathUtil.min( istr_prop.up * persentase, ldec_max );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("830"), new BigDecimal( istr_prop.rider_baru[ 19 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
                
            case 20:
                //hcp ladies
            	 if( istr_prop.rider_baru[ 20 ] > 0 && istr_prop.hcpLad.peserta == 0)
                 {
                 	if( li_usia < 66 && istr_prop.hcpLad.peserta == 0 ){
 	                    Map<String, Object> params = new HashMap<String, Object>();
 	                    params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
 	                    params.put( "kursId", istr_prop.kurs_id );
 	                    params.put( "liUsia", li_usia );
 	                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadies( params ) );
 	
 	                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
 	                    ldec_total += ldec_temp;
 	                   if( ai_th == 1 ){
	                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("831"), new BigDecimal( istr_prop.rider_baru[ 20 ] ),
	                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
	                    }
                 	}
                 }

                 //hcp ladies peserta tambahan
                 if( istr_prop.rider_baru[ 20 ] > 0 && istr_prop.hcpLad.peserta > 0 )
                 {
                 	li_usia = istr_prop.hcpLad.usia[ 1 ] + ai_th - 1;
                 	if( li_usia < 66 && istr_prop.hcpLad.peserta > 0 )
                 	{
 	                    for( int i = 1; i <= istr_prop.hcpLad.peserta; i++ )
 	                    {
 	                        li_usia = istr_prop.hcpLad.usia[ i ] + ai_th - 1;
 	
 	                        Map<String, Object> params = new HashMap<String, Object>();
 	                        params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
 	                        params.put( "kursId", istr_prop.kurs_id );
 	                        params.put( "liUsia", li_usia );
 	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadiesPesertaTambahan( params ) );
 	                        ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
 	                        if(i >= 2){
 	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
 	                        	if(i>=3 && li_usia>25) ldec_temp = 0;
 	                        }
 	                        ldec_total += ldec_temp;
 	                        
 	                       Integer lsdbs_hcp_lad = null;
	                        if( i == 1 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ];
	                        }else if( i == 2 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 );
	                        }else if( i == 3 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 2 );
	                        }else if( i == 4 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 3 );
	                        }else if( i == 5 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 4 );
	                        }
	                        if( ai_th == 1 ){
	 	                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("831"), new BigDecimal( lsdbs_hcp_lad ),
	 	                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	 	                    			 new BigDecimal(istr_prop.cb_id), istr_prop.hcpLad.tgl[i], istr_prop.hcpLad.usia[i] ) );
	 	                    }
 	                    }
                 	}
                 }
                 break;
            case 21:
                //ladies medical expense
                if( istr_prop.rider_baru[ 21 ] > 0 && li_usia < 75 && istr_prop.ladiesMedExpense.peserta == 0)
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider(params));

                    ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("832"), new BigDecimal( istr_prop.rider_baru[ 21 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }

                //ladies medical expense peserta tambahan
                if( istr_prop.rider_baru[ 21 ] > 0 && istr_prop.ladiesMedExpense.peserta > 0 )
                {
                	li_usia = istr_prop.ladiesMedExpense.usia[1] + ai_th - 1;
                	if( li_usia < 75 ){
	                    for( int i = 1; i <= istr_prop.ladiesMedExpense.peserta; i++ )
	                    {
	                        li_usia = istr_prop.ladiesMedExpense.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
	                        if(i > 1){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.117, 2 );
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_ladies_med = null;
	                        if( i == 1 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ];
 	                        }else if( i == 2 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 );
 	                        }else if( i == 3 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 2 );
 	                        }else if( i == 4 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 3 );
 	                        }else if( i == 5 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 4 );
 	                        }
 	                        
	                        if( ai_th == 1 ){
	                       	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("832"), new BigDecimal( lsdbs_ladies_med ),
	                       			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                       			 new BigDecimal(istr_prop.cb_id), istr_prop.ladiesMedExpense.tgl[i], istr_prop.ladiesMedExpense.usia[i] ) );
	                       }
	                    }
                	}
                }
                break;
            case 22:
                //ladies medical expense inner limit
                if( istr_prop.rider_baru[ 22 ] > 0 && li_usia < 75 && istr_prop.ladiesMedExpenseInnerLimit.peserta == 0)
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider(params));

                    ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("833"), new BigDecimal( istr_prop.rider_baru[ 22 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }

                //ladies medical expense inner limit peserta tambahan
                if( istr_prop.rider_baru[ 22 ] > 0 && istr_prop.ladiesMedExpenseInnerLimit.peserta > 0 )
                {
                	li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[1] + ai_th - 1;
                	if( li_usia < 75  ){
	                    for( int i = 1; i <= istr_prop.ladiesMedExpenseInnerLimit.peserta; i++ )
	                    {
	                        li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
	                        if(i > 1){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.117, 2 );
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_ladies_med_il = null;
	                        if( i == 1 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ];
 	                        }else if( i == 2 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 );
 	                        }else if( i == 3 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 2 );
 	                        }else if( i == 4 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 3 );
 	                        }else if( i == 5 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 4 );
 	                        }
 	                        
	                        if( ai_th == 1 ){
	                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("833"), new BigDecimal( lsdbs_ladies_med_il ),
	                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                          			 new BigDecimal(istr_prop.cb_id), istr_prop.ladiesMedExpenseInnerLimit.tgl[i], istr_prop.ladiesMedExpenseInnerLimit.usia[i] ) );
	                        }
	                    }
                	}
                }
                break;
            case 23:
            	//Payor 5/10 TPD/CI/Death
            	if( istr_prop.rider_baru[ 23 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                	if(  cepr01030102Form.getPremiumFurloughYear() == 5 ){
            			istr_prop.rider_baru[23] = 2;
            		}else if(  cepr01030102Form.getPremiumFurloughYear() == 10 ){
            			istr_prop.rider_baru[23] = 3;
            		}
                    li_jenis = istr_prop.rider_baru[ 23 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getPolicyHolderSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    params.put("liSex", li_sex); //-- DONE
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorTpdCiDeath(params) );
                    
                    		
                    		
                    ldec_max = 200000;
                    if( istr_prop.kurs_id == "01" ) ldec_max = 2000000000;
                    double ldec_pct_pp = 1;
                    ldec_temp = FormatNumber.round( ( ( ( ( istr_prop.premi * ldec_pct_pp ) * li_kali / 1000 ) * ldec_rate)  * 0.1 ), 2 );
                	if( istr_prop.bisnis_id == 120 ||  istr_prop.bisnis_id == 202){
                	    ldec_temp = FormatNumber.round( ( ( ( ldec_rate / 1000 ) * ( istr_prop.premi * li_kali)) * 0.1 ), 2 );
                	}
                    ldec_total += ldec_temp;
                    
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("828"), new BigDecimal( li_jenis ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
                
            case 24:
            	//Waiver 5/10 TPD/CI
            	if( istr_prop.rider_baru[ 24 ] > 0 )
                {
            		li_usia = istr_prop.umur_tt + ai_th - 1;
            		if(  cepr01030102Form.getPremiumFurloughYear() == 5 ){
            			istr_prop.rider_baru[24] = 4;
            		}else if(  cepr01030102Form.getPremiumFurloughYear() == 10 ){
            			istr_prop.rider_baru[24] = 5;
            		}
            		li_jenis = istr_prop.rider_baru[24]	;
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", ai_th );
                    params.put( "liUsia", li_usia );
                    params.put("liSex", li_sex); //-- DONE
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWaiverTpdCi(params) );
                    
                    ldec_max = 200000;
//            		ldec_temp = Round(((ldec_rate/1000)*(istr_prop.premi *li_kali))*0.1, 2)
                    ldec_temp = FormatNumber.round( ( ( ldec_rate / 1000 )*( istr_prop.premi * li_kali ) ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("827"), new BigDecimal( li_jenis ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
            	break;
            case 25:
            	if( istr_prop.rider_baru[25] > 0 && li_usia < istr_prop.usia_schol ){
            		 Map<String, Object> params = new HashMap<String, Object>();
                     params.put( "kursId", istr_prop.kurs_id );
                     params.put( "liUsia", li_usia_pp );
                     ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateScholarship( params ) );
                     
                     Map<String, Object> paramsRate = new HashMap<String, Object>();
                     Integer lijenis = 0;
                     if( istr_prop.usia_schol == 22 ){
                    	 lijenis = 1;
                     }else if( istr_prop.usia_schol == 25 ){
                    	 lijenis = 2;
                     }
                     paramsRate = new HashMap<String, Object>();
                     paramsRate.put( "lsbsId", "835" );
                     paramsRate.put( "liJenis", lijenis );
                     paramsRate.put( "liUsia", li_usia );
                     paramsRate.put( "kursId", istr_prop.kurs_id );
                     BigDecimal rate_factor = eproposalManager.selectTableFactor( paramsRate );
                     if( rate_factor == null ) { rate_factor = BigDecimal.ZERO;}
                     double rate_factor_double = LazyConverter.toDouble( rate_factor );
//                      153.240.000 * 1,79 /1000 *10% = 27.430 (yg ini yg bener ya)
//                      200.000.000 * 1.79 / 1000 * 10% = 35.800

                     ldec_temp = FormatNumber.round( ( ( ldec_rate / 1000 )* istr_prop.up_schol * rate_factor_double ) * 0.1, 2 );
                     ldec_total += ldec_temp;
                     
                     if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("835"), new BigDecimal( lijenis ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
            	}
            	
                break;      
            case 26:
            	if( istr_prop.rider_baru[26] > 0  ){            		            		
        			double ldec_pct_pp = istr_prop.pct_premi / 100;
        			li_usia = 1;
        			li_jenis = Integer.valueOf(cepr01030103Form.getBabyCd());
    
        			Map<String, Object> params = new HashMap<String, Object>();
                     params.put( "kursId", istr_prop.kurs_id );
                     params.put( "liUsiaBaby", 0 );
                     params.put( "liJenis", li_jenis);
                     ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateBaby( params ) );
        			        		         			
                     Map<String, Object> param = new HashMap<String, Object>();                    
                     param.put( "liUsia", li_usia );
                   //ldec_rb = of_get_coi_basic()
                     double ldec_rb = LazyConverter.toDouble( eproposalManager.selectLdecRateToGetCoiBasic( param ) );
                 
        			ldec_temp = FormatNumber.round((ldec_rate * 0.1) + (istr_prop.premi * ldec_pct_pp * 0.7 * ldec_rb / 1000 * 0.1), 2);		
        			ldec_total += ldec_temp;  
        			
        			 if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("836"), new BigDecimal( li_jenis ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
            	}
                break;
            case 27:
            	if( istr_prop.rider_baru[27] > 0  ){            		
            		String genderInsured = cepr01030101Form.getInsuredSexCd().trim();
            		li_jenis=0;
            		if(genderInsured.equals("L")){
            			li_jenis = 1;
            		}
            		else if(genderInsured.equals("P")){
            			li_jenis = 2;
            		}
            		
            		Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "lku_id", istr_prop.kurs_id );
                    params.put( "lsr_jenis", li_jenis );
                    params.put( "li_usia", li_usia);
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateEarlyStageCi99( params ) );

//                    double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getEsci99ChooseCd())) / 100.0;
                    /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
                    double upEsci99 =  cepr01030103Form.getEsci99RiderAmount().doubleValue();
                    double maxUp;
                    if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) ){
                    	maxUp = 500000.0;
                    }else{
                    	maxUp = 5000000000.0;
                    }
                    double temp = MathUtil.min( upEsci99, maxUp );
                    ldec_temp = FormatNumber.round(((ldec_rate / 1000) * temp) * 0.1, 2);
                	ldec_total += ldec_temp;
                	
        			 if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("837"), new BigDecimal( li_jenis ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
            	}
                break;
            case 28:
            	 //SMiLe Medical (+)
            	if( istr_prop.rider_baru[28] > 0 ){ 			
                         Map<String, Object> params = new HashMap<String, Object>();
                         int riderBaru = istr_prop.rider_baru[ 28 ];
                       
                         params.put( "riderBaru", riderBaru );  
                         params.put( "kursId", istr_prop.kurs_id );
                         params.put( "liUsia", li_usia );
                         ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRider( params ) );
                     
             			if( istr_prop.cb_id == 1 ){  //triwulan
             				ldec_pct = 0.35;
             			}else if( istr_prop.cb_id == 2 ){ //semesteran
             				ldec_pct = 0.65;
             			}else if( istr_prop.cb_id == 6 ){ //bulanan
             				ldec_pct = 0.12;
             			}
//                         ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
             			ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
             			ldec_total += ldec_temp;
             			
             			if( ai_th == 1 ){
                         	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("838"), new BigDecimal( riderBaru ),
                         	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                         	new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                         }
             			
             			if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag())){           				
                             ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
                             ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
                  			 ldec_total += ldec_temp;
                  			 
                  			if( ai_th == 1 ){
                             	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                             	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                             	new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                             }
            			}
             			if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRgFlag())){           				
             				riderBaru = istr_prop.rider_baru[ 28 ]+20;
                         	                      	
                         	 params.put( "riderBaru", riderBaru );  
                             params.put( "kursId", istr_prop.kurs_id );
                             params.put( "liUsia", li_usia );
             				 ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
                             ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
                 			 ldec_total += ldec_temp;
                 			 
                 			if( ai_th == 1 ){
                             	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                             	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                             	new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                             }
             			}
             			if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRbFlag())){           				
             				riderBaru = istr_prop.rider_baru[ 28 ]+40;
                         	                     	
                         	 params.put( "riderBaru", riderBaru );  
                             params.put( "kursId", istr_prop.kurs_id );
                             params.put( "liUsia", li_usia );
             				 ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
                             ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
                 			 ldec_total += ldec_temp;
                 			 
                 			if( ai_th == 1 ){
                             	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                             	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                             	new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                             }
             			}
             			if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusPkFlag())){           				
             				riderBaru = istr_prop.rider_baru[ 28 ]+60;
                         	
                         	 params.put( "riderBaru", riderBaru );  
                             params.put( "kursId", istr_prop.kurs_id );
                             params.put( "liUsia", li_usia );
             				 ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
                             ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
                 			 ldec_total += ldec_temp;
                 			 
                 			if( ai_th == 1 ){
                             	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                             	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                             	new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                            }
             			}
                     }
                     //SMiLe Medical(+) Peserta Tambahan
	            	 if( istr_prop.rider_baru[ 28 ] > 0 && istr_prop.medicalPlus.peserta > 0   && li_usia < 75 )
	                 {
	                    for( int i = 1; i <= istr_prop.medicalPlus.peserta; i++ )
	                    {
	                        li_usia = istr_prop.medicalPlus.usia[ i ] + ai_th - 1;
	                        int riderBaru = istr_prop.rider_baru[ 28 ];
	                        
	                        if(i==1){
	                        	riderBaru = riderBaru + 4;
	                        }
	                        else if(i==2){
	                        	riderBaru = riderBaru + 8;
	                        }
	                        else if(i==3){
	                        	riderBaru = riderBaru + 12;
	                        }
	                        else if(i==4){
	                        	riderBaru = riderBaru + 16;
	                        }
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", riderBaru );  
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRider( params ) );	                        
	                        ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
	                        ldec_total += ldec_temp;	    
	                        
	                        if( ai_th == 1 ){
	                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("838"), new BigDecimal( riderBaru ),
	                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                          			 new BigDecimal(istr_prop.cb_id), istr_prop.medicalPlus.tgl[i], istr_prop.medicalPlus.usia[i] ) );
	                        }
	                        
	                        if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag())){           				
	                             ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
	                             ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
	                  			 ldec_total += ldec_temp;
	                  			 
	                  			if( ai_th == 1 ){
	                             	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
	                             	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                             	new BigDecimal(istr_prop.cb_id),  istr_prop.medicalPlus.tgl[i], istr_prop.medicalPlus.usia[i] ) );
	                             }
	            			}
	                        if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRgFlag())){           				
	             				riderBaru = istr_prop.rider_baru[ 28 ]+20;
	                         	
	                         	if(i==1){
		                        	riderBaru = riderBaru + 4;
		                        }
		                        else if(i==2){
		                        	riderBaru = riderBaru + 8;
		                        }
		                        else if(i==3){
		                        	riderBaru = riderBaru + 12;
		                        }
		                        else if(i==4){
		                        	riderBaru = riderBaru + 16;
		                        }
	                         	
	                         	params.put( "riderBaru", riderBaru );  
	                            params.put( "kursId", istr_prop.kurs_id );
	                            params.put( "liUsia", li_usia );
	             				ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
	             				ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
	                  			ldec_total += ldec_temp;
	                  			 
	                  			if( ai_th == 1 ){
	                             	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
	                             	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                             	new BigDecimal(istr_prop.cb_id),  istr_prop.medicalPlus.tgl[i], istr_prop.medicalPlus.usia[i] ) );
	                             }
	             			}
	                        if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(istr_prop.medicalPlus.medicalPlusRbFlag[i])){        				
	             				riderBaru = istr_prop.rider_baru[ 28 ]+40;
	                         	                         	
	                         	if(i==1){
		                        	riderBaru = riderBaru + 4;
		                        }
		                        else if(i==2){
		                        	riderBaru = riderBaru + 8;
		                        }
		                        else if(i==3){
		                        	riderBaru = riderBaru + 12;
		                        }
		                        else if(i==4){
		                        	riderBaru = riderBaru + 16;
		                        }	                         	
	                         	params.put( "riderBaru", riderBaru );  
	                            params.put( "kursId", istr_prop.kurs_id );
	                            params.put( "liUsia", li_usia );
	             				ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
	             				ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
	                 			ldec_total += ldec_temp;
	                 			 
	                 			if( ai_th == 1 ){
	                             	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
	                             	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                             	new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
	                             }
	             			}                        
	                        if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusPkFlag())){           				
	             				riderBaru = istr_prop.rider_baru[ 28 ]+60;
	                         	
	                         	if(i==1){
		                        	riderBaru = riderBaru + 4;
		                        }
		                        else if(i==2){
		                        	riderBaru = riderBaru + 8;
		                        }
		                        else if(i==3){
		                        	riderBaru = riderBaru + 12;
		                        }
		                        else if(i==4){
		                        	riderBaru = riderBaru + 16;
		                        }	    
	                         	 params.put( "riderBaru", riderBaru );  
	                             params.put( "kursId", istr_prop.kurs_id );
	                             params.put( "liUsia", li_usia );
	             				 ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
	             				 ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
	                 			 ldec_total += ldec_temp;
	                 			 
	                 			if( ai_th == 1 ){
	                             	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
	                             	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                             	new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
	                            }
	             			}
	                    }
	                 }
	            break;
	            
            case 29:
            	//SME (71-140)
                //smile medical extra
                if( istr_prop.rider_baru[ 29 ] > 0 && li_usia < 75 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                   
                    params.put( "riderBaru", istr_prop.rider_baru[ 29 ] + 70);                
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectSMiLeMedicalExtraRider( params ) );
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
//                    ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
        			ldec_temp = FormatNumber.round( (ldec_rate) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("848"), new BigDecimal( istr_prop.rider_baru[ 29 ] ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }

                //eka sehat peserta tambahan
                if( istr_prop.rider_baru[ 29 ] > 0 && istr_prop.eka_sehatExtra.peserta > 0   && li_usia < 75 )
                {
                    for( int i = 1; i <= istr_prop.eka_sehatExtra.peserta; i++ )
                    {
                        li_usia = istr_prop.eka_sehatExtra.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();                      
                    	params.put( "riderBaru", istr_prop.rider_baru[ 29 ] + 70 );                    	
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectSMiLeMedicalExtraRider( params ) );
//                        ldec_temp = FormatNumber.round( (ldec_rate * 0.975 * ldec_pct) * 0.1, 2 );
                   //     ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
                        ldec_temp = FormatNumber.round( (ldec_rate * 0.0975) , 2 );
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_e_sehat = null;
                        if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 29 ] + 10;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 29 ] + ( 10 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 29 ] + ( 10 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 29 ] + ( 10 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 29 ] + ( 10 * 5 );
                        }
                        if( ai_th == 1 ){
                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("848"), new BigDecimal( lsdbs_e_sehat ),
                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                          			 new BigDecimal(istr_prop.cb_id), istr_prop.eka_sehat.tgl[i], istr_prop.eka_sehat.usia[i] ) );
                        }
                    }
                }
                break;  
                
            case 30:
            	 /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
            	 if( istr_prop.rider_baru[ 30 ] > 0 )
                 {
            		 String li_sex;
	                 li_sex = cepr01030101Form.getInsuredSexCd();
	                 if(li_sex.equals("L")){
	                 	li_sex = "1".trim();
	                 }else if(li_sex.equals("P")){
	                 	li_sex = "0".trim();
	                 }else{
	                 	li_sex = "9".trim();
	                 }
	                 String lsdbs_number = String.valueOf(istr_prop.rider_baru[ 30 ]);
	                 
                     Map<String, Object> params = new HashMap<String, Object>();
                     params.put( "kursId", istr_prop.kurs_id );
                     params.put( "liUsia", li_usia );
                     params.put("liSex", li_sex); //-- DONE
                     params.put("li_jenis", lsdbs_number); //-- DONE
                     ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTerm( params ) );
                     ldec_temp = FormatNumber.round( ( ( istr_prop.up_term5575 / 1000 ) * ldec_rate ) * 0.1, 2 );
                     ldec_total += ldec_temp;
                     if( ai_th == 1 ){
                       	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("818"), new BigDecimal( lsdbs_number ),
                       			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                       			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                       }
                 }
                 break;
                                
                
            default:
                break;
        }
        cepr01031001Form.setMstProposal( mstProposal );
        return ldec_total;
    }
    
   
    
    public double of_get_coi_134( int ai_jenis, int ai_th )
    {
        double ldec_temp = 0, ldec_total = 0;
        double ldec_rate, ldec_pct = 1;
        int li_usia, li_tahun_ke, li_kali = 1, li_jenis, li_class, li_usia_tt;
        li_usia = istr_prop.umur_tt + ai_th - 1;
        List< Mst_proposal > mstProposal = new ArrayList<Mst_proposal>();
        //
        if(ai_jenis != 0){
        	if(istr_prop.cb_id == 1 ){  //triwulan
        		li_kali = 4;
        		ldec_pct = 0.27;
        	}else if( istr_prop.cb_id == 2){ //semesteran
        		li_kali = 2;
        		ldec_pct = 0.525;
        	}else if( istr_prop.cb_id == 6){ //bulanan
        		li_kali = 12;
        		ldec_pct = 0.1;
        	}
        }
//
        switch( ai_jenis )
        {
            case 1:
                //pa
                if( ( istr_prop.rider_baru[ 1 ] > 0 ) && ( li_usia < 60 ) )
                {
                    li_class = istr_prop.pa_class;
                    if( istr_prop.umur_tt <= 16 ) li_class = 2;
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "paRisk", istr_prop.pa_risk );
                    params.put( "liClass", li_class );
                	String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePa( params ) );
                    double maks = 2000000000.0;
                    if( istr_prop.kurs_id.equals( Hardcode.CUR_USD_CD ) ){
                    	 maks = 200000.0;
                    }
                    double maxAmount = MathUtil.min( istr_prop.rider_baru[ 1 ] * istr_prop.up, maks );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate * ldec_pct ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("810"), new BigDecimal( istr_prop.pa_risk ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
            case 2:
                //hcp
                if( ( istr_prop.rider_baru[ 2 ] > 0 ) && ( li_usia < 65 ) )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 2 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );

                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcp( params ) );
                    ldec_temp = FormatNumber.round( ldec_rate * 0.1 * ldec_pct, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("811"), new BigDecimal(istr_prop.rider_baru[ 2 ]),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                  }
                }
                break;
            case 3:
                //tpd
            	if(li_usia == 60){
            		String debug = "true";
            	}
                if( istr_prop.rider_baru[ 3 ] > 0 &&  li_usia < 60 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                  //  params.put( "li_jenis", 1 ); Rate TPD lama diganti baru
                    params.put( "li_jenis", 7 );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                	String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTpd_120( params ) );
                    // maks 2 milyar
                    if( "01".equals( istr_prop.kurs_id ) ){
                    	ldec_max = 2000000000;
                    }else if( "02".equals( istr_prop.kurs_id ) ){
                    	ldec_max = 200000;
                    }
                    
                    double ldec_up = MathUtil.min( istr_prop.up, ldec_max );
                    ldec_temp = FormatNumber.round( ( ( ldec_up / 1000 ) * ldec_rate * ldec_pct ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("812"), new BigDecimal( "1" ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
            case 4:
                //ci
                if( istr_prop.rider_baru[ 4 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                	String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateCi( params ) );
//                    double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getCiChooseCd())) / 100.0;
                    /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
                    double upCi = cepr01030103Form.getCiRiderAmount().doubleValue();
                    double maxUp;
                    if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) ){
                    	maxUp = 500000.0;
                    }else{
                    	maxUp = 5000000000.0;
                    }
                    double temp = MathUtil.min( upCi, maxUp );
                    ldec_temp = FormatNumber.round( ( ( temp / 1000 ) * ( ldec_rate * ldec_pct ) ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("813"), new BigDecimal("8"),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
                //
            case 5:
                //wp 10 tpd
                if( istr_prop.rider_baru[ 5 ] > 0 )
                {
                	li_jenis = 5;//istr_prop.rider_baru[ 5 ];
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", ai_th );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp10Tpd( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("814"), new BigDecimal( li_jenis ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            case 6:
                //pb 10 tpd
                if( istr_prop.rider_baru[ 6 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = 5;//istr_prop.rider_baru[ 6 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Tpd( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal( li_jenis ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
            case 7:
                //wp 10 ci
                if( istr_prop.rider_baru[ 7 ] > 0 )
                {
                	li_jenis = 3;//istr_prop.rider_baru[ 7 ];
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", ai_th );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp10Ci( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("816"), new BigDecimal( li_jenis ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
            case 8:
                //pb 25 ci/death
                if( istr_prop.rider_baru[ 8 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = 3;//istr_prop.rider_baru[ 8 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25CiDeath( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("817"), new BigDecimal(li_jenis),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
                //pb 10 ci
            case 9:
                if( istr_prop.rider_baru[ 9 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = 3;

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Ci( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("817"), new BigDecimal(li_jenis),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                       }
                }
                break;
                //payor spouse
            case 10:
                if( istr_prop.rider_baru[ 10 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = 6;

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorSpouse( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal(li_jenis),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            case 11:
                //hcp family
                if( istr_prop.rider_baru[ 11 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 11 ] + 140 );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamily( params ) );

                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("819"), new BigDecimal( istr_prop.rider_baru[ 11 ] + 140 ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }

                //hcp family peserta tambahan
                if( istr_prop.rider_baru[ 11 ] > 0 && istr_prop.hcpf.peserta > 0 )
                {
                    for( int i = 1; i <= istr_prop.hcpf.peserta; i++ )
                    {
                    	li_usia_tt = istr_prop.hcpf.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 11 ] + 140 );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia_tt );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamilyPesertaTambahan( params ) );
//                        ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                        ldec_temp = 0;
                        if (!( i > 1 && li_usia_tt > 24 ) ){
                        	 ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                    	}
                        ldec_total += ldec_temp;
                        Integer lsdbs_hcp_fam = null;
                        if( i == 1 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + 20;
                        }else if( i == 2 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 5 );
                        }
                        if( ai_th == 1 ){
                        	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("819"), new BigDecimal( lsdbs_hcp_fam ),
                        			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        			 new BigDecimal(istr_prop.cb_id),  istr_prop.hcpf.tgl[i], istr_prop.hcpf.usia[i] ) );
                        }
                    }
                }
                break;

            case 12:
                //term rider
                if( istr_prop.rider_baru[ 12 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTermRider( params ) );
                    ldec_temp = FormatNumber.round( ( ( istr_prop.up_term / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("818"), new BigDecimal( "2" ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
                
            case 13:
                //eka sehat
                if( istr_prop.rider_baru[ 13 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 13 ] );  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) );
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
                    ldec_temp = FormatNumber.round( (ldec_rate * 0.12) * 0.1, 2 );
//        			ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("823"), new BigDecimal( istr_prop.rider_baru[ 13 ] ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }

                //eka sehat peserta tambahan
                if( istr_prop.rider_baru[ 13 ] > 0 && istr_prop.eka_sehat.peserta > 0 )
                {
                    for( int i = 1; i <= istr_prop.eka_sehat.peserta; i++ )
                    {
                        li_usia = istr_prop.eka_sehat.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 13 ]  );    
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) );
//                        ldec_temp = FormatNumber.round( (ldec_rate * 0.975 * ldec_pct) * 0.1, 2 );
                        ldec_temp = FormatNumber.round( (ldec_rate * 0.117), 2 );
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_e_sehat = null;
                        if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + 15;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 5 );
                        }
                        if( ai_th == 1 ){
                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("823"), new BigDecimal( lsdbs_e_sehat ),
                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                          			 new BigDecimal(istr_prop.cb_id), istr_prop.eka_sehat.tgl[i], istr_prop.eka_sehat.usia[i] ) );
                        }
                    }
                }
                break;
            case 15:
            	//eka sehat inner limit
            	if( istr_prop.rider_baru[ 15 ] > 0 && li_usia < 75 )
            	{
            		Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 15 ] );  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ) );
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
        			ldec_temp = FormatNumber.round( (ldec_rate * 0.12) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("825"), new BigDecimal( istr_prop.rider_baru[ 15 ] ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
	            }
            	if( istr_prop.rider_baru[ 15 ] > 0 && istr_prop.ekaSehatInnerLimit.peserta > 0 && li_usia < 75 )
            	{
            		for ( int i = 1; i <= istr_prop.ekaSehatInnerLimit.peserta; i++)
            		{
            			li_usia = istr_prop.ekaSehatInnerLimit.usia[ i ] + ai_th - 1;
            			
            			Map<String, Object> params = new HashMap<String, Object>();
            			params.put( "riderBaru", istr_prop.rider_baru[ 15 ] );
            			params.put( "kursId", istr_prop.kurs_id );
            			params.put( "liUsia", li_usia );
            			ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ));
            		    ldec_temp = FormatNumber.round( (ldec_rate * 0.117), 2 );
            			ldec_total += ldec_temp;
            			
            			Integer lsdbs_e_sehat = null;
            			if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + 15;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 5 );
                        }
            			if( ai_th == 1 ){
                         	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("825"), new BigDecimal( lsdbs_e_sehat ),
                         			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                         			 new BigDecimal(istr_prop.cb_id), istr_prop.ekaSehatInnerLimit.tgl[i], istr_prop.ekaSehatInnerLimit.usia[i] ) );
                        }
            		}
            	}
	            break;
                         
            case 16:
                //hcp Provider
                if( istr_prop.rider_baru[ 16 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );   
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProvider( params ) );

                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("826"), new BigDecimal( istr_prop.rider_baru[ 16 ] ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }

                //hcp provider peserta tambahan
                if( istr_prop.rider_baru[ 16 ] > 0 && istr_prop.hcpPro.peserta > 0 && li_usia < 65 )
                {
                    for( int i = 1; i <= istr_prop.hcpPro.peserta; i++ )
                    {
                        li_usia = istr_prop.hcpPro.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProviderPesertaTambahan( params ) );
                        ldec_temp = 0;
                        if  (i > 1 && li_usia > 24){
                        	
                        }else{
                        	  ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                        }
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_hcp_pro= null;
                        if( i == 1 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + 12;
                        }else if( i == 2 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 5 );
                        }
                        
                        if( ai_th == 1 ){
                        	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("826"), new BigDecimal( lsdbs_hcp_pro ),
                        			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        			 new BigDecimal(istr_prop.cb_id), istr_prop.hcpPro.tgl[i], istr_prop.hcpPro.usia[i] ) );
                       }
                    }
                }
                break;
            case 18:
            	//Payor TPD/CI/Death
            	if( istr_prop.rider_baru[ 18 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 18 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                	String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getPolicyHolderSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorTpdCiDeath(params) );
                    
                    ldec_max = 200000;
//                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * ldec_pct ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * 1 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("828"), new BigDecimal( li_jenis ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
            case 17:
            	//Waiver TPD/CI/Death
            	if( istr_prop.rider_baru[ 17 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", istr_prop.rider_baru[ 17 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", 0 );
                    params.put( "liUsia", li_usia );
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWaiverTpdCi(params) );
                    
                    ldec_max = 200000;
//                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 10 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * 1 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("827"), new BigDecimal( istr_prop.rider_baru[ 17 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
                
            case 19:
                //ladies insurance
                if( istr_prop.rider_baru[ 19 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", istr_prop.rider_baru[ 19 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", 0 );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateLadiesIns(params) );
                    
                    ldec_max = 200000;
                    if( istr_prop.kurs_id.equals("01") ){ ldec_max = 2000000000;}
                    double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getLadiesInsChooseCd())) / 100.0;
                    double maxAmount = MathUtil.min( istr_prop.up * persentase, ldec_max );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("830"), new BigDecimal( istr_prop.rider_baru[ 19 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
                
            case 20:
                //hcp ladies
            	 if( istr_prop.rider_baru[ 20 ] > 0 && istr_prop.hcpLad.peserta == 0)
                 {
                 	if( li_usia < 66 && istr_prop.hcpLad.peserta == 0 ){
 	                    Map<String, Object> params = new HashMap<String, Object>();
 	                    params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
 	                    params.put( "kursId", istr_prop.kurs_id );
 	                    params.put( "liUsia", li_usia );
 	                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadies( params ) );
 	
 	                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
 	                    ldec_total += ldec_temp;
 	                   if( ai_th == 1 ){
	                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("831"), new BigDecimal( istr_prop.rider_baru[ 20 ] ),
	                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
	                    }
                 	}
                 }

                 //hcp ladies peserta tambahan
                 if( istr_prop.rider_baru[ 20 ] > 0 && istr_prop.hcpLad.peserta > 0 )
                 {
                 	li_usia = istr_prop.hcpLad.usia[ 1 ] + ai_th - 1;
                 	if( li_usia < 66 && istr_prop.hcpLad.peserta > 0 )
                 	{
 	                    for( int i = 1; i <= istr_prop.hcpLad.peserta; i++ )
 	                    {
 	                        li_usia = istr_prop.hcpLad.usia[ i ] + ai_th - 1;
 	
 	                        Map<String, Object> params = new HashMap<String, Object>();
 	                        params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
 	                        params.put( "kursId", istr_prop.kurs_id );
 	                        params.put( "liUsia", li_usia );
 	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadiesPesertaTambahan( params ) );
 	                        ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
 	                        if(i >= 2){
 	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
 	                        	if(i>=3 && li_usia>25) ldec_temp = 0;
 	                        }
 	                        ldec_total += ldec_temp;
 	                        
 	                       Integer lsdbs_hcp_lad = null;
	                        if( i == 1 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ];
	                        }else if( i == 2 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 );
	                        }else if( i == 3 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 2 );
	                        }else if( i == 4 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 3 );
	                        }else if( i == 5 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 4 );
	                        }
	                        if( ai_th == 1 ){
	 	                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("831"), new BigDecimal( lsdbs_hcp_lad ),
	 	                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	 	                    			 new BigDecimal(istr_prop.cb_id), istr_prop.hcpLad.tgl[i], istr_prop.hcpLad.usia[i] ) );
	 	                    }
 	                    }
                 	}
                 }
                 break;
            case 21:
                //ladies medical expense
                if( istr_prop.rider_baru[ 21 ] > 0 && li_usia < 75 && istr_prop.ladiesMedExpense.peserta == 0)
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider(params));

                    ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("832"), new BigDecimal( istr_prop.rider_baru[ 21 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }

                //ladies medical expense peserta tambahan
                if( istr_prop.rider_baru[ 21 ] > 0 && istr_prop.ladiesMedExpense.peserta > 0 )
                {
                	li_usia = istr_prop.ladiesMedExpense.usia[1] + ai_th - 1;
                	if( li_usia < 75 ){
	                    for( int i = 1; i <= istr_prop.ladiesMedExpense.peserta; i++ )
	                    {
	                        li_usia = istr_prop.ladiesMedExpense.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
	                        if(i > 1){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.117, 2 );
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_ladies_med = null;
	                        if( i == 1 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ];
 	                        }else if( i == 2 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 );
 	                        }else if( i == 3 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 2 );
 	                        }else if( i == 4 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 3 );
 	                        }else if( i == 5 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 4 );
 	                        }
 	                        
	                        if( ai_th == 1 ){
	                       	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("832"), new BigDecimal( lsdbs_ladies_med ),
	                       			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                       			 new BigDecimal(istr_prop.cb_id), istr_prop.ladiesMedExpense.tgl[i], istr_prop.ladiesMedExpense.usia[i] ) );
	                       }
	                    }
                	}
                }
                break;
            case 22:
                //ladies medical expense inner limit
                if( istr_prop.rider_baru[ 22 ] > 0 && li_usia < 75 && istr_prop.ladiesMedExpenseInnerLimit.peserta == 0)
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider(params));

                    ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("833"), new BigDecimal( istr_prop.rider_baru[ 22 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }

                //ladies medical expense inner limit peserta tambahan
                if( istr_prop.rider_baru[ 22 ] > 0 && istr_prop.ladiesMedExpenseInnerLimit.peserta > 0 )
                {
                	li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[1] + ai_th - 1;
                	if( li_usia < 75 ){
	                    for( int i = 1; i <= istr_prop.ladiesMedExpenseInnerLimit.peserta; i++ )
	                    {
	                        li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
	                        if(i > 1){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.117, 2 );
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_ladies_med_il = null;
	                        if( i == 1 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ];
 	                        }else if( i == 2 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 );
 	                        }else if( i == 3 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 2 );
 	                        }else if( i == 4 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 3 );
 	                        }else if( i == 5 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 4 );
 	                        }
 	                        
	                        if( ai_th == 1 ){
	                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("833"), new BigDecimal( lsdbs_ladies_med_il ),
	                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                          			 new BigDecimal(istr_prop.cb_id), istr_prop.ladiesMedExpenseInnerLimit.tgl[i], istr_prop.ladiesMedExpenseInnerLimit.usia[i] ) );
	                        }
	                    }
                	}
                }
                break;
                
            case 23:
            	//Payor 5/10 TPD/CI/Death
            	if( istr_prop.rider_baru[ 23 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                	if(  cepr01030102Form.getPremiumFurloughYear() == 5 ){
            			istr_prop.rider_baru[23] = 2;
            		}else if(  cepr01030102Form.getPremiumFurloughYear() == 10 ){
            			istr_prop.rider_baru[23] = 3;
            		}
                    li_jenis = istr_prop.rider_baru[ 23 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getPolicyHolderSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorTpdCiDeath(params) );
                    
                    		
                    		
                    ldec_max = 200000;
                    if( istr_prop.kurs_id == "01" ) ldec_max = 2000000000;
                    ldec_temp = FormatNumber.round( ( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate)  * 0.1 ), 2 );
                	if( istr_prop.bisnis_id == 120 ||  istr_prop.bisnis_id == 202){
                	    ldec_temp = FormatNumber.round( ( ( ( ldec_rate / 1000 ) * ( istr_prop.premi * li_kali)) * 0.1 ), 2 );
                	}
                    ldec_total += ldec_temp;
                    
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("828"), new BigDecimal( li_jenis ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
                
            case 24:
            	//Waiver 5/10 TPD/CI
            	if( istr_prop.rider_baru[ 24 ] > 0 )
                {
            		li_usia = istr_prop.umur_tt + ai_th - 1;
            		if(  cepr01030102Form.getPremiumFurloughYear() == 5 ){
            			istr_prop.rider_baru[24] = 4;
            		}else if(  cepr01030102Form.getPremiumFurloughYear() == 10 ){
            			istr_prop.rider_baru[24] = 5;
            		}
            		li_jenis = istr_prop.rider_baru[24]	;
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", ai_th );
                    params.put( "liUsia", li_usia );
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWaiverTpdCi(params) );
                    
                    ldec_max = 200000;
//            		ldec_temp = Round(((ldec_rate/1000)*(istr_prop.premi *li_kali))*0.1, 2)
                    ldec_temp = FormatNumber.round( ( ( ldec_rate / 1000 )*( istr_prop.premi * li_kali ) ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("827"), new BigDecimal( li_jenis ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
                
            case 30:
            	 /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
               	 if( istr_prop.rider_baru[ 30 ] > 0 && li_usia < 75 )
                 {
               		 String li_sex;
    	                 li_sex = cepr01030101Form.getInsuredSexCd();
    	                 if(li_sex.equals("L")){
    	                 	li_sex = "1".trim();
    	                 }else if(li_sex.equals("P")){
    	                 	li_sex = "0".trim();
    	                 }else{
    	                 	li_sex = "9".trim();
    	                 }
    	                 String lsdbs_number = String.valueOf(istr_prop.rider_baru[ 30 ]);
    	                 
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        params.put("liSex", li_sex); //-- DONE
                        params.put("li_jenis", lsdbs_number); //-- DONE
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTerm( params ) );
                        ldec_temp = FormatNumber.round( ( ( istr_prop.up_term5575 / 1000 ) * ldec_rate ) * 0.1, 2 );
                        ldec_total += ldec_temp;
                        if( ai_th == 1 ){
                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("818"), new BigDecimal( lsdbs_number ),
                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                          			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                          }
                  }
                  break;
            default:
                break;
        }
        return ldec_total;
    }
    
    
    public double of_get_coi_134_cost( int ai_jenis, int ai_th, List< Mst_proposal > mstProposal )
    {
        double ldec_temp = 0, ldec_total = 0;
        double ldec_rate, ldec_pct = 1;
        int li_usia, li_tahun_ke, li_kali = 1, li_jenis, li_class, li_usia_tt;
        li_usia = istr_prop.umur_tt + ai_th - 1;
        //
        if(ai_jenis != 0){
        	if(istr_prop.cb_id == 1 ){  //triwulan
        		li_kali = 4;
        		ldec_pct = 0.27;
        	}else if( istr_prop.cb_id == 2){ //semesteran
        		li_kali = 2;
        		ldec_pct = 0.525;
        	}else if( istr_prop.cb_id == 6){ //bulanan
        		li_kali = 12;
        		ldec_pct = 0.1;
        	}
        }
//
        switch( ai_jenis )
        {
            case 1:
                //pa
                if( ( istr_prop.rider_baru[ 1 ] > 0 ) && ( li_usia < 60 ) )
                {
                    li_class = istr_prop.pa_class;
                    if( istr_prop.umur_tt <= 16 ) li_class = 2;
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    params.put( "paRisk", istr_prop.pa_risk );
                    params.put( "liClass", li_class );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePa( params ) );
                    double maks = 2000000000.0;
                    if( istr_prop.kurs_id.equals( Hardcode.CUR_USD_CD ) ){
                    	 maks = 200000.0;
                    }
                    double maxAmount = MathUtil.min( istr_prop.rider_baru[ 1 ] * istr_prop.up, maks );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate * ldec_pct ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("810"), new BigDecimal( istr_prop.pa_risk ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
            case 2:
                //hcp
                if( ( istr_prop.rider_baru[ 2 ] > 0 ) && ( li_usia < 65 ) )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 2 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );

                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcp( params ) );
                    ldec_temp = FormatNumber.round( ldec_rate * 0.1 * ldec_pct, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("811"), new BigDecimal(istr_prop.rider_baru[ 2 ]),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                  }
                }
                break;
            case 3:
                //tpd
                if( istr_prop.rider_baru[ 3 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    params.put( "li_jenis", 1 );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTpd_120( params ) );
                    // maks 2 milyar
                    if( "01".equals( istr_prop.kurs_id ) ){
                    	ldec_max = 1000000000;
                    }else if( "02".equals( istr_prop.kurs_id ) ){
                    	ldec_max = 100000;
                    }
                    
                    double ldec_up = MathUtil.min( istr_prop.up, ldec_max );
                    ldec_temp = FormatNumber.round( ( ( ldec_up / 1000 ) * ldec_rate * ldec_pct ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("812"), new BigDecimal( "1" ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
            case 4:
                //ci
                if( istr_prop.rider_baru[ 4 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateCi( params ) );
//                    double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getCiChooseCd())) / 100.0;
                    /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
                    double upCi = cepr01030103Form.getCiRiderAmount().doubleValue();
                    double maxUp;
                    if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) ){
                    	maxUp = 500000.0;
                    }else{
                    	maxUp = 5000000000.0;
                    }
                    double temp = MathUtil.min( upCi, maxUp );
                    ldec_temp = FormatNumber.round( ( ( temp / 1000 ) * ( ldec_rate * ldec_pct ) ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("813"), new BigDecimal("8"),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
                //
            case 5:
                //wp 10 tpd
                if( istr_prop.rider_baru[ 5 ] > 0 )
                {
                	li_jenis = 5;//istr_prop.rider_baru[ 5 ];
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", ai_th );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp10Tpd( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("814"), new BigDecimal( li_jenis ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            case 6:
                //pb 10 tpd
                if( istr_prop.rider_baru[ 6 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = 5;//istr_prop.rider_baru[ 6 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Tpd( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal( li_jenis ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
            case 7:
                //wp 10 ci
                if( istr_prop.rider_baru[ 7 ] > 0 )
                {
                	li_jenis = 3;//istr_prop.rider_baru[ 7 ];
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", ai_th );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp10Ci( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("816"), new BigDecimal( li_jenis ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
            case 8:
                //pb 25 ci/death
                if( istr_prop.rider_baru[ 8 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = 3;//istr_prop.rider_baru[ 8 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25CiDeath( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("817"), new BigDecimal(li_jenis),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
                //pb 10 ci
            case 9:
                if( istr_prop.rider_baru[ 9 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = 3;

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Ci( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("817"), new BigDecimal(li_jenis),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                       }
                }
                break;
                //payor spouse
            case 10:
                if( istr_prop.rider_baru[ 10 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = 6;

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorSpouse( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal(li_jenis),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            case 11:
                //hcp family
                if( istr_prop.rider_baru[ 11 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 11 ] + 140 );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamily( params ) );

                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("819"), new BigDecimal( istr_prop.rider_baru[ 11 ] + 140 ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }

                //hcp family peserta tambahan
                if( istr_prop.rider_baru[ 11 ] > 0 && istr_prop.hcpf.peserta > 0 )
                {
                    for( int i = 1; i <= istr_prop.hcpf.peserta; i++ )
                    {
                    	li_usia_tt = istr_prop.hcpf.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 11 ] + 140 );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia_tt );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamilyPesertaTambahan( params ) );
//                        ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                        ldec_temp = 0;
                        if (!( i > 1 && li_usia_tt > 24 ) ){
                        	 ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                    	}
                        ldec_total += ldec_temp;
                        Integer lsdbs_hcp_fam = null;
                        if( i == 1 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + 20;
                        }else if( i == 2 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 5 );
                        }
                        if( ai_th == 1 ){
                        	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("819"), new BigDecimal( lsdbs_hcp_fam ),
                        			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        			 new BigDecimal(istr_prop.cb_id),  istr_prop.hcpf.tgl[i], istr_prop.hcpf.usia[i] ) );
                        }
                    }
                }
                break;

            case 12:
                //term rider
                if( istr_prop.rider_baru[ 12 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTermRider( params ) );
                    ldec_temp = FormatNumber.round( ( ( istr_prop.up_term / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("818"), new BigDecimal( "2" ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
                
            case 13:
                //eka sehat
                if( istr_prop.rider_baru[ 13 ] > 0 && li_usia < 75 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 13 ] );  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) );
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
 //                   ldec_temp = FormatNumber.round( (ldec_rate * 0.12) * 0.1, 2 );
      			ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("823"), new BigDecimal( istr_prop.rider_baru[ 13 ] ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }

                //eka sehat peserta tambahan
                if( istr_prop.rider_baru[ 13 ] > 0 && istr_prop.eka_sehat.peserta > 0 )
                {
                    for( int i = 1; i <= istr_prop.eka_sehat.peserta; i++ )
                    {
                        li_usia = istr_prop.eka_sehat.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 13 ]  );    
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) );
                        ldec_temp = FormatNumber.round( (ldec_rate * 0.975 * ldec_pct) * 0.1, 2 );
                      //  ldec_temp = FormatNumber.round( (ldec_rate * 0.117), 2 );                  
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_e_sehat = null;
                        if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + 15;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 5 );
                        }
                        if( ai_th == 1 ){
                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("823"), new BigDecimal( lsdbs_e_sehat ),
                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                          			 new BigDecimal(istr_prop.cb_id), istr_prop.eka_sehat.tgl[i], istr_prop.eka_sehat.usia[i] ) );
                        }
                    }
                }
                break;
            case 15:
            	//eka sehat inner limit
            	if( istr_prop.rider_baru[ 15 ] > 0 && li_usia < 75 )
            	{
            		Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 15 ] );  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ) );
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
  //      			ldec_temp = FormatNumber.round( (ldec_rate * 0.12) * 0.1, 2 );
        			ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("825"), new BigDecimal( istr_prop.rider_baru[ 15 ] ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
	            }
            	if( istr_prop.rider_baru[ 15 ] > 0 && istr_prop.ekaSehatInnerLimit.peserta > 0 && li_usia < 75 )
            	{
            		for ( int i = 1; i <= istr_prop.ekaSehatInnerLimit.peserta; i++)
            		{
            			li_usia = istr_prop.ekaSehatInnerLimit.usia[ i ] + ai_th - 1;
            			
            			Map<String, Object> params = new HashMap<String, Object>();
            			params.put( "riderBaru", istr_prop.rider_baru[ 15 ] );
            			params.put( "kursId", istr_prop.kurs_id );
            			params.put( "liUsia", li_usia );
            			ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ));
            		   // ldec_temp = FormatNumber.round( (ldec_rate * 0.117), 2 );
            		    ldec_temp = FormatNumber.round( (ldec_rate * 0.975 * ldec_pct) * 0.1, 2 );
            		    ldec_total += ldec_temp;
            			
            			Integer lsdbs_e_sehat = null;
            			if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + 15;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 5 );
                        }
            			if( ai_th == 1 ){
                         	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("825"), new BigDecimal( lsdbs_e_sehat ),
                         			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                         			 new BigDecimal(istr_prop.cb_id), istr_prop.ekaSehatInnerLimit.tgl[i], istr_prop.ekaSehatInnerLimit.usia[i] ) );
                        }
            		}
            	}
	            break;
                         
            case 16:
                //hcp Provider
                if( istr_prop.rider_baru[ 16 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );   
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProvider( params ) );
                 
                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("826"), new BigDecimal( istr_prop.rider_baru[ 16 ] ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }

                //hcp provider peserta tambahan
                if( istr_prop.rider_baru[ 16 ] > 0 && istr_prop.hcpPro.peserta > 0 && li_usia < 65 )
                {
                    for( int i = 1; i <= istr_prop.hcpPro.peserta; i++ )
                    {
                        li_usia = istr_prop.hcpPro.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProviderPesertaTambahan( params ) );
                        ldec_temp = 0;
                        if  (i > 1 && li_usia > 24){
                        	
                        }else{
                        	  ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                        }
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_hcp_pro= null;
                        if( i == 1 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + 12;
                        }else if( i == 2 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 5 );
                        }
                        
                        if( ai_th == 1 ){
                        	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("826"), new BigDecimal( lsdbs_hcp_pro ),
                        			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        			 new BigDecimal(istr_prop.cb_id), istr_prop.hcpPro.tgl[i], istr_prop.hcpPro.usia[i] ) );
                       }
                    }
                }
                break;
            case 18:
            	//Payor TPD/CI/Death
            	if( istr_prop.rider_baru[ 18 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 18 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getPolicyHolderSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorTpdCiDeath(params) );
                    
                    ldec_max = 200000;
//                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * ldec_pct ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * 1 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("828"), new BigDecimal( li_jenis ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
            case 17:
            	//Waiver TPD/CI/Death
            	if( istr_prop.rider_baru[ 17 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE
                    params.put( "liJenis", istr_prop.rider_baru[ 17 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", 0 );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWaiverTpdCi(params) );
                    
                    ldec_max = 200000;
//                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 10 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * 1 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("827"), new BigDecimal( istr_prop.rider_baru[ 17 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
                
            case 19:
                //ladies insurance
                if( istr_prop.rider_baru[ 19 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", istr_prop.rider_baru[ 19 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", 0 );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateLadiesIns(params) );
                    
                    ldec_max = 200000;
                    if( istr_prop.kurs_id.equals("01") ){ ldec_max = 2000000000;}
                    double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getLadiesInsChooseCd())) / 100.0;
                    double maxAmount = MathUtil.min( istr_prop.up * persentase, ldec_max );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1 * ldec_pct, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("830"), new BigDecimal( istr_prop.rider_baru[ 19 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
                
            case 20:
                //hcp ladies
            	 if( istr_prop.rider_baru[ 20 ] > 0 && istr_prop.hcpLad.peserta == 0)
                 {
                 	if( li_usia < 66 && istr_prop.hcpLad.peserta == 0 ){
 	                    Map<String, Object> params = new HashMap<String, Object>();
 	                    params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
 	                    params.put( "kursId", istr_prop.kurs_id );
 	                    params.put( "liUsia", li_usia );
 	                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadies( params ) );
	 	              
 	                    if( istr_prop.cb_id == 1 ){  //triwulan
	        				ldec_pct = 0.35;
	        			}else if( istr_prop.cb_id == 2 ){ //semesteran
	        				ldec_pct = 0.65;
	        			}else if( istr_prop.cb_id == 6 ){ //bulanan
	        				ldec_pct = 0.12;
	        			}    
 	                    
 	                    ldec_temp = FormatNumber.round( ldec_rate * ldec_pct * 0.1, 2 );
 	                    ldec_total += ldec_temp;
 	                   if( ai_th == 1 ){
	                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("831"), new BigDecimal( istr_prop.rider_baru[ 20 ] ),
	                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
	                    }
                 	}
                 }

                 //hcp ladies peserta tambahan
                 if( istr_prop.rider_baru[ 20 ] > 0 && istr_prop.hcpLad.peserta > 0 )
                 {
                 	li_usia = istr_prop.hcpLad.usia[ 1 ] + ai_th - 1;
                 	if( li_usia < 66 && istr_prop.hcpLad.peserta > 0 )
                 	{
 	                    for( int i = 1; i <= istr_prop.hcpLad.peserta; i++ )
 	                    {
 	                        li_usia = istr_prop.hcpLad.usia[ i ] + ai_th - 1;
 	
 	                        Map<String, Object> params = new HashMap<String, Object>();
 	                        params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
 	                        params.put( "kursId", istr_prop.kurs_id );
 	                        params.put( "liUsia", li_usia );
 	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadiesPesertaTambahan( params ) );
 	                        ldec_temp = FormatNumber.round( ldec_rate * ldec_pct * 0.1, 2 );
 	                        if(i >= 2){
 	                        	ldec_temp = FormatNumber.round( ldec_rate * ldec_pct * 0.09, 2 );
 	                        	if(i>=3 && li_usia>25) ldec_temp = 0;
 	                        }
 	                        ldec_total += ldec_temp;
 	                        
 	                       Integer lsdbs_hcp_lad = null;
	                        if( i == 1 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ];
	                        }else if( i == 2 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 );
	                        }else if( i == 3 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 2 );
	                        }else if( i == 4 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 3 );
	                        }else if( i == 5 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 4 );
	                        }
	                        if( ai_th == 1 ){
	 	                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("831"), new BigDecimal( lsdbs_hcp_lad ),
	 	                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	 	                    			 new BigDecimal(istr_prop.cb_id), istr_prop.hcpLad.tgl[i], istr_prop.hcpLad.usia[i] ) );
	 	                    }
 	                    }
                 	}
                 }
                 break;
            case 21:
                //ladies medical expense
                if( istr_prop.rider_baru[ 21 ] > 0 && li_usia < 75 && istr_prop.ladiesMedExpense.peserta == 0)
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider(params));
                	if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}    
                	
              //      ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
                	 ldec_temp = FormatNumber.round( ldec_rate * ldec_pct, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("832"), new BigDecimal( istr_prop.rider_baru[ 21 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }

                //ladies medical expense peserta tambahan
                if( istr_prop.rider_baru[ 21 ] > 0 && istr_prop.ladiesMedExpense.peserta > 0 )
                {
                	li_usia = istr_prop.ladiesMedExpense.usia[1] + ai_th - 1;
                	if( li_usia < 75 ){
	                    for( int i = 1; i <= istr_prop.ladiesMedExpense.peserta; i++ )
	                    {
	                        li_usia = istr_prop.ladiesMedExpense.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider( params ) );
	                       // ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
	                        ldec_temp = FormatNumber.round( ldec_rate * ldec_pct, 2 );
	                        if(i > 1){
	                        	//ldec_temp = FormatNumber.round( ldec_rate * 0.117, 2 );	                        	
	                        	ldec_temp = FormatNumber.round( (ldec_rate * 0.975 * ldec_pct) * 0.1, 2 );
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_ladies_med = null;
	                        if( i == 1 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ];
 	                        }else if( i == 2 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 );
 	                        }else if( i == 3 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 2 );
 	                        }else if( i == 4 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 3 );
 	                        }else if( i == 5 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 4 );
 	                        }
 	                        
	                        if( ai_th == 1 ){
	                       	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("832"), new BigDecimal( lsdbs_ladies_med ),
	                       			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                       			 new BigDecimal(istr_prop.cb_id), istr_prop.ladiesMedExpense.tgl[i], istr_prop.ladiesMedExpense.usia[i] ) );
	                       }
	                    }
                	}
                }
                break;
            case 22:
                //ladies medical expense inner limit
                if( istr_prop.rider_baru[ 22 ] > 0 && li_usia < 75 && istr_prop.ladiesMedExpenseInnerLimit.peserta == 0)
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider(params));
                    if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}                        
                    
           //         ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
                    ldec_temp = FormatNumber.round( ldec_rate * ldec_pct, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("833"), new BigDecimal( istr_prop.rider_baru[ 22 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }

                //ladies medical expense inner limit peserta tambahan
                if( istr_prop.rider_baru[ 22 ] > 0 && istr_prop.ladiesMedExpenseInnerLimit.peserta > 0 )
                {
                	li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[1] + ai_th - 1;
                	if( li_usia < 75 ){
	                    for( int i = 1; i <= istr_prop.ladiesMedExpenseInnerLimit.peserta; i++ )
	                    {
	                        li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider( params ) );
	                       // ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
	                        ldec_temp = FormatNumber.round( ldec_rate * ldec_pct, 2 );
	                        if(i > 1){
	                        	//ldec_temp = FormatNumber.round( ldec_rate * 0.117, 2 );	                        	
	                        	ldec_temp = FormatNumber.round( (ldec_rate * 0.975 * ldec_pct) * 0.1, 2 );
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_ladies_med_il = null;
	                        if( i == 1 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ];
 	                        }else if( i == 2 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 );
 	                        }else if( i == 3 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 2 );
 	                        }else if( i == 4 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 3 );
 	                        }else if( i == 5 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 4 );
 	                        }
 	                        
	                        if( ai_th == 1 ){
	                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("833"), new BigDecimal( lsdbs_ladies_med_il ),
	                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                          			 new BigDecimal(istr_prop.cb_id), istr_prop.ladiesMedExpenseInnerLimit.tgl[i], istr_prop.ladiesMedExpenseInnerLimit.usia[i] ) );
	                        }
	                    }
                	}
                }
                break;
                
            case 23:
            	//Payor 5/10 TPD/CI/Death
            	if( istr_prop.rider_baru[ 23 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                	if(  cepr01030102Form.getPremiumFurloughYear() == 5 ){
            			istr_prop.rider_baru[23] = 2;
            		}else if(  cepr01030102Form.getPremiumFurloughYear() == 10 ){
            			istr_prop.rider_baru[23] = 3;
            		}
                    li_jenis = istr_prop.rider_baru[ 23 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getPolicyHolderSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorTpdCiDeath(params) );
                    
                    		
                    		
                    ldec_max = 200000;
                    if( istr_prop.kurs_id == "01" ) ldec_max = 2000000000;
                    ldec_temp = FormatNumber.round( ( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate * ldec_pct)  * 0.1 ), 2 );
                	if( istr_prop.bisnis_id == 120 ||  istr_prop.bisnis_id == 202){
                	    ldec_temp = FormatNumber.round( ( ( ( ldec_rate / 1000 ) * ( istr_prop.premi * li_kali)) * 0.1 ), 2 );
                	}
                    ldec_total += ldec_temp;
                    
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("828"), new BigDecimal( li_jenis ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
                
            case 24:
            	//Waiver 5/10 TPD/CI
            	if( istr_prop.rider_baru[ 24 ] > 0 )
                {
            		li_usia = istr_prop.umur_tt + ai_th - 1;
            		if(  cepr01030102Form.getPremiumFurloughYear() == 5 ){
            			istr_prop.rider_baru[24] = 4;
            		}else if(  cepr01030102Form.getPremiumFurloughYear() == 10 ){
            			istr_prop.rider_baru[24] = 5;
            		}
            		li_jenis = istr_prop.rider_baru[24]	;
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", ai_th );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWaiverTpdCi(params) );
                    
                    ldec_max = 200000;
//            		ldec_temp = Round(((ldec_rate/1000)*(istr_prop.premi *li_kali))*0.1, 2)
                    ldec_temp = FormatNumber.round( ( ( ldec_rate / 1000 )*( istr_prop.premi * li_kali * ldec_pct) ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("827"), new BigDecimal( li_jenis ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            default:
                break;
        }
        cepr01031001Form.setMstProposal( mstProposal );
        return ldec_total;
    }
    
    
    public double of_get_coi_141( int ai_jenis, int ai_th )
    {
    	double ldec_total = 0, ldec_temp;
        double ldec_rate, ldec_max = 100000;
        int li_usia, li_tahun_ke, li_kali = 1, li_jenis, li_class, li_usia_tt, li_usia_pp, li_risk, li_temp;
        li_usia = istr_prop.umur_tt + ai_th - 1;
        li_usia_pp = istr_prop.umur_pp + ai_th - 1;
        List< Mst_proposal > mstProposal = new ArrayList<Mst_proposal>();
        
        double ldec_pct = 1;
        if(ai_jenis != 0){
        	if(istr_prop.cb_id == 1 ){  //triwulan
        		li_kali = 4;
        		ldec_pct = 0.27;
        	}else if( istr_prop.cb_id == 2){ //semesteran
        		li_kali = 2;
        		ldec_pct = 0.525;
        	}else if( istr_prop.cb_id == 6){ //bulanan
        		li_kali = 12;
        		ldec_pct = 0.1;
        	}
        }
        
        if( istr_prop.rider_baru[ 5 ] > 0 || istr_prop.rider_baru[ 6 ] > 0 || istr_prop.rider_baru[ 7 ] > 0 || istr_prop.rider_baru[ 8 ] > 0 || istr_prop.rider_baru[ 9 ] > 0 || istr_prop.rider_baru[ 10 ] > 0 )
        {
            if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_TRIWULANAN )
            {
                li_kali = 4;
            }
            else if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_SEMESTERAN )
            {
                li_kali = 2;
            }
            else if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_BULANAN )
            {
                li_kali = 12;
            }
        }
//
        switch( ai_jenis )
        {
            case 1:
                //pa
            	
                if( ( istr_prop.rider_baru[ 1 ] > 0 ) && ( li_usia < 60 ) )
                {
                    li_class = istr_prop.pa_class;
                    li_risk = 1;
                    li_temp = li_usia;
                	if(istr_prop.bisnis_id == 120  || istr_prop.bisnis_id == 202 || istr_prop.bisnis_id == 141){
                		//khusus 120-cerdas usia tdk naik
                		li_risk = istr_prop.pa_risk;
                		li_temp = istr_prop.umur_tt;
                	}
                    if(istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 141 || istr_prop.bisnis_id == 202 ){
                		if(li_temp <= 20){li_class = 2;}else{li_class = 1;}
            		}
                    //if( istr_prop.umur_tt <= 16 ) li_class = 2;
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "paRisk", li_risk );
                    params.put( "liClass", li_class );
                    double maks = 2000000000.0;
                    if( istr_prop.kurs_id.equals( Hardcode.CUR_USD_CD ) ){
                    	 maks = 200000.0;
                    }
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePa( params ) );
                    double maxAmount = MathUtil.min( istr_prop.rider_baru[ 1 ] * istr_prop.up, maks );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("810"), new BigDecimal( li_risk ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
            case 2:
                //hcp
                if( ( istr_prop.rider_baru[ 2 ] > 0 ) && ( li_usia < 65 ) )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 2 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );

                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcp( params ) );
                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("811"), new BigDecimal(istr_prop.rider_baru[ 2 ]),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                  }
                }
                break;
                
            case 3:
                //tpd
                if( istr_prop.rider_baru[ 3 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTpd_120( params ) );
                    // maks 2 milyar
                    if( "01".equals( istr_prop.kurs_id ) ){
                    	ldec_max = 2000000000;
                    }else if( "02".equals( istr_prop.kurs_id ) ){
                    	ldec_max = 200000;
                    }
                    double maxAmount = MathUtil.min( istr_prop.up, ldec_max );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("812"), new BigDecimal( "7" ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;	
            case 4:
                //ci
                if( istr_prop.rider_baru[ 4 ] > 0 )
                {
                	li_tahun_ke = li_usia;
                	if(istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 141){
                		li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
            		}
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_tahun_ke );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateCi( params ) );

                    ldec_temp = FormatNumber.round( ( ( istr_prop.up * 0.4 / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("813"), new BigDecimal("8"),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
                //
            case 5:
                //wp 60tpd
                if( istr_prop.rider_baru[ 5 ] > 0 )
                {
                	li_jenis = istr_prop.rider_baru[ 5 ];
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "li_jenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "ai_th", ai_th );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp60Tpd_120( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("814"), new BigDecimal( li_jenis ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                       }
                }
                break;
            case 6:
                //pb 25 tpd
                if( istr_prop.rider_baru[ 6 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 6 ];
                    Integer bisnisNo =  istr_prop.bisnis_no;
                    if( bisnisNo.equals(1) ) { li_jenis = 4; }
                    else { li_jenis = 5; }
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Tpd( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal( li_jenis ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
            case 7:
                //wp 60ci
                if( istr_prop.rider_baru[ 7 ] > 0 )
                {

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 7 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "ai_th", ai_th );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp60Ci_120( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("816"), new BigDecimal( istr_prop.rider_baru[ 7 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            case 8:
                //pb 25 ci/death
                if( istr_prop.rider_baru[ 8 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 8 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25CiDeath( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("817"), new BigDecimal(li_jenis),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
                //pb 25 ci
                //--
            case 9:
                if( istr_prop.rider_baru[ 9 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    Integer bisnisNo =  istr_prop.bisnis_no;
                    if( bisnisNo.equals(1) ) { li_jenis = 2; }
                    else { li_jenis = 3; }

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Ci( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("817"), new BigDecimal(li_jenis),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                       }
                }
                break;
                //payor spouse
            case 10:
                if( istr_prop.rider_baru[ 10 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = 6;

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorSpouse( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal(li_jenis),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            case 11:
                //hcp family
                if( istr_prop.rider_baru[ 11 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 11 ] + 140 );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamily( params ) );

                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("819"), new BigDecimal( istr_prop.rider_baru[ 11 ] + 140 ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }

                //hcp family peserta tambahan
                if( istr_prop.rider_baru[ 11 ] > 0 && istr_prop.hcpf.peserta > 0 )
                {
                    for( int i = 1; i <= istr_prop.hcpf.peserta; i++ )
                    {
                    	li_usia_tt = istr_prop.hcpf.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 11 ] + 140 );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia_tt );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamilyPesertaTambahan( params ) );
//                        ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                        ldec_temp = 0;
                        if (!( i > 1 && li_usia_tt > 24 ) ){
                        	ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                    	}
                        ldec_total += ldec_temp;
                        Integer lsdbs_hcp_fam = null;
                        if( i == 1 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + 20;
                        }else if( i == 2 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 5 );
                        }
                        if( ai_th == 1 ){
                        	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("819"), new BigDecimal( lsdbs_hcp_fam ),
                        			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        			 new BigDecimal(istr_prop.cb_id),  istr_prop.hcpf.tgl[i], istr_prop.hcpf.usia[i] ) );
                        }
                    }
                }
                break;

            case 12:
                //term rider
                if( istr_prop.rider_baru[ 12 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTermRider( params ) );
                    ldec_temp = FormatNumber.round( ( ( istr_prop.up_term / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("818"), new BigDecimal( "2" ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
                
            case 13:
                //eka sehat
                if( istr_prop.rider_baru[ 13 ] > 0 && li_usia < 75 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 13 ] );  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) );
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
//                    ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
        			ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("823"), new BigDecimal( istr_prop.rider_baru[ 13 ] ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }

                //eka sehat peserta tambahan
                if( istr_prop.rider_baru[ 13 ] > 0 && istr_prop.eka_sehat.peserta > 0   && li_usia < 75 )
                {
                    for( int i = 1; i <= istr_prop.eka_sehat.peserta; i++ )
                    {
                        li_usia = istr_prop.eka_sehat.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 13 ]  );    
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) );
//                        ldec_temp = FormatNumber.round( (ldec_rate * 0.975 * ldec_pct) * 0.1, 2 );
                        ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_e_sehat = null;
                        if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + 15;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 5 );
                        }
                        if( ai_th == 1 ){
                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("823"), new BigDecimal( lsdbs_e_sehat ),
                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                          			 new BigDecimal(istr_prop.cb_id), istr_prop.eka_sehat.tgl[i], istr_prop.eka_sehat.usia[i] ) );
                        }
                    }
                }
                break;
                
            case 15:
            	//eka sehat inner limit
            	if( istr_prop.rider_baru[ 15 ] > 0 && li_usia < 75 )
            	{
            		Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 15 ] );  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ) );
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
                    ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("825"), new BigDecimal( istr_prop.rider_baru[ 15 ] ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
	            }
            	if( istr_prop.rider_baru[ 15 ] > 0 && istr_prop.ekaSehatInnerLimit.peserta > 0 && li_usia < 75 )
            	{
            		for ( int i = 1; i <= istr_prop.ekaSehatInnerLimit.peserta; i++)
            		{
            			li_usia = istr_prop.ekaSehatInnerLimit.usia[ i ] + ai_th - 1;
            			
            			Map<String, Object> params = new HashMap<String, Object>();
            			params.put( "riderBaru", istr_prop.rider_baru[ 15 ] );
            			params.put( "kursId", istr_prop.kurs_id );
            			params.put( "liUsia", li_usia );
            			ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ));
            			ldec_temp = FormatNumber.round((ldec_rate * 0.117), 2);
            			ldec_total += ldec_temp;
            			
            			Integer lsdbs_e_sehat = null;
            			if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + 15;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 5 );
                        }
            			if( ai_th == 1 ){
                         	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("825"), new BigDecimal( lsdbs_e_sehat ),
                         			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                         			 new BigDecimal(istr_prop.cb_id), istr_prop.ekaSehatInnerLimit.tgl[i], istr_prop.ekaSehatInnerLimit.usia[i] ) );
                        }
            		}
            	}
	            break;
                         
                
          
            case 112:
                //term rider header
                if( istr_prop.rider_baru[ 12 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTermRider( params ) );
                    ldec_temp = FormatNumber.round( ( ( istr_prop.up_term / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                }
                break;
            case 16:
                //hcp Provider
                if( istr_prop.rider_baru[ 16 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );   
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProvider( params ) );

                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("826"), new BigDecimal( istr_prop.rider_baru[ 16 ] ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }

                //hcp provider peserta tambahan
                if( istr_prop.rider_baru[ 16 ] > 0 && istr_prop.hcpPro.peserta > 0 && li_usia < 65 )
                {
                    for( int i = 1; i <= istr_prop.hcpPro.peserta; i++ )
                    {
                        li_usia = istr_prop.hcpPro.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProviderPesertaTambahan( params ) );
                        ldec_temp = 0;
                        if  (i > 1 && li_usia > 24){
                        	
                        }else{
                        	  ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                        }
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_hcp_pro= null;
                        if( i == 1 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + 12;
                        }else if( i == 2 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 5 );
                        }
                        
                        if( ai_th == 1 ){
                        	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("826"), new BigDecimal( lsdbs_hcp_pro ),
                        			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        			 new BigDecimal(istr_prop.cb_id), istr_prop.hcpPro.tgl[i], istr_prop.hcpPro.usia[i] ) );
                       }
                    }
                }
                break;
            case 18:
            	//Payor TPD/CI/Death
            	if( istr_prop.rider_baru[ 18 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 18 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorTpdCiDeath(params) );
                    
                    ldec_max = 200000;
//                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * 1 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("828"), new BigDecimal( li_jenis ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
            case 17:
            	//Waiver TPD/CI/Death
            	if( istr_prop.rider_baru[ 17 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", istr_prop.rider_baru[ 17 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", 0 );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWaiverTpdCi(params) );
                    
                    ldec_max = 200000;
//                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 10 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * 1 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("827"), new BigDecimal( istr_prop.rider_baru[ 17 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
            case 19:
                //ladies insurance
                if( istr_prop.rider_baru[ 19 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", istr_prop.rider_baru[ 19 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", 0 );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateLadiesIns(params) );
                    
                    ldec_max = 200000;
                    if( istr_prop.kurs_id.equals("01") ){ ldec_max = 2000000000;}
                    double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getLadiesInsChooseCd())) / 100.0;
                    double maxAmount = MathUtil.min( istr_prop.up * persentase, ldec_max );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("830"), new BigDecimal( istr_prop.rider_baru[ 19 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
                
            case 20:
                //hcp ladies
            	 if( istr_prop.rider_baru[ 20 ] > 0 && istr_prop.hcpLad.peserta == 0)
                 {
                 	if( li_usia < 66 && istr_prop.hcpLad.peserta == 0 ){
 	                    Map<String, Object> params = new HashMap<String, Object>();
 	                    params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
 	                    params.put( "kursId", istr_prop.kurs_id );
 	                    params.put( "liUsia", li_usia );
 	                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadies( params ) );
 	
 	                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
 	                    ldec_total += ldec_temp;
 	                   if( ai_th == 1 ){
	                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("831"), new BigDecimal( istr_prop.rider_baru[ 20 ] ),
	                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
	                    }
                 	}
                 }

                 //hcp ladies peserta tambahan
                 if( istr_prop.rider_baru[ 20 ] > 0 && istr_prop.hcpLad.peserta > 0 )
                 {
                 	li_usia = istr_prop.hcpLad.usia[ 1 ] + ai_th - 1;
                 	if( li_usia < 66 && istr_prop.hcpLad.peserta > 0 )
                 	{
 	                    for( int i = 1; i <= istr_prop.hcpLad.peserta; i++ )
 	                    {
 	                        li_usia = istr_prop.hcpLad.usia[ i ] + ai_th - 1;
 	
 	                        Map<String, Object> params = new HashMap<String, Object>();
 	                        params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
 	                        params.put( "kursId", istr_prop.kurs_id );
 	                        params.put( "liUsia", li_usia );
 	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadiesPesertaTambahan( params ) );
 	                        ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
 	                        if(i >= 2){
 	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
 	                        	if(i>=3 && li_usia>25) ldec_temp = 0;
 	                        }
 	                        ldec_total += ldec_temp;
 	                        
 	                       Integer lsdbs_hcp_lad = null;
	                        if( i == 1 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ];
	                        }else if( i == 2 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 );
	                        }else if( i == 3 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 2 );
	                        }else if( i == 4 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 3 );
	                        }else if( i == 5 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 4 );
	                        }
	                        if( ai_th == 1 ){
	 	                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("831"), new BigDecimal( lsdbs_hcp_lad ),
	 	                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	 	                    			 new BigDecimal(istr_prop.cb_id), istr_prop.hcpLad.tgl[i], istr_prop.hcpLad.usia[i] ) );
	 	                    }
 	                    }
                 	}
                 }
                 break;
            case 21:
                //ladies medical expense
                if( istr_prop.rider_baru[ 21 ] > 0 && li_usia < 75 && istr_prop.ladiesMedExpense.peserta == 0)
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider(params));

                    ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("832"), new BigDecimal( istr_prop.rider_baru[ 21 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }

                //ladies medical expense peserta tambahan
                if( istr_prop.rider_baru[ 21 ] > 0 && istr_prop.ladiesMedExpense.peserta > 0 )
                {
                	li_usia = istr_prop.ladiesMedExpense.usia[1] + ai_th - 1;
                	if( li_usia < 75 ){
	                    for( int i = 1; i <= istr_prop.ladiesMedExpense.peserta; i++ )
	                    {
	                        li_usia = istr_prop.ladiesMedExpense.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
	                        if(i > 1){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.117, 2 );
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_ladies_med = null;
	                        if( i == 1 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ];
 	                        }else if( i == 2 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 );
 	                        }else if( i == 3 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 2 );
 	                        }else if( i == 4 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 3 );
 	                        }else if( i == 5 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 4 );
 	                        }
 	                        
	                        if( ai_th == 1 ){
	                       	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("832"), new BigDecimal( lsdbs_ladies_med ),
	                       			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                       			 new BigDecimal(istr_prop.cb_id), istr_prop.ladiesMedExpense.tgl[i], istr_prop.ladiesMedExpense.usia[i] ) );
	                       }
	                    }
                	}
                }
                break;
            case 22:
                //ladies medical expense inner limit
                if( istr_prop.rider_baru[ 22 ] > 0 && li_usia < 75 && istr_prop.ladiesMedExpenseInnerLimit.peserta == 0)
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider(params));

                    ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("833"), new BigDecimal( istr_prop.rider_baru[ 22 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }

                //ladies medical expense inner limit peserta tambahan
                if( istr_prop.rider_baru[ 22 ] > 0 && istr_prop.ladiesMedExpenseInnerLimit.peserta > 0 )
                {
                	li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[1] + ai_th - 1;
                	if( li_usia < 75 ){
	                    for( int i = 1; i <= istr_prop.ladiesMedExpenseInnerLimit.peserta; i++ )
	                    {
	                        li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
	                        if(i > 1){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.117, 2 );
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_ladies_med_il = null;
	                        if( i == 1 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ];
 	                        }else if( i == 2 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 );
 	                        }else if( i == 3 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 2 );
 	                        }else if( i == 4 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 3 );
 	                        }else if( i == 5 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 4 );
 	                        }
 	                        
	                        if( ai_th == 1 ){
	                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("833"), new BigDecimal( lsdbs_ladies_med_il ),
	                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                          			 new BigDecimal(istr_prop.cb_id), istr_prop.ladiesMedExpenseInnerLimit.tgl[i], istr_prop.ladiesMedExpenseInnerLimit.usia[i] ) );
	                        }
	                    }
                	}
                }
                break;
                
            case 25:
            	if( istr_prop.rider_baru[25] > 0 && li_usia < istr_prop.usia_schol ){
            		 Map<String, Object> params = new HashMap<String, Object>();
                     params.put( "kursId", istr_prop.kurs_id );
                     params.put( "liUsia", li_usia_pp );
                     ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateScholarship( params ) );
                     
                     Map<String, Object> paramsRate = new HashMap<String, Object>();
                     Integer lijenis = 0;
                     if( istr_prop.usia_schol == 22 ){
                    	 lijenis = 1;
                     }else if( istr_prop.usia_schol == 25 ){
                    	 lijenis = 2;
                     }
                     paramsRate = new HashMap<String, Object>();
                     paramsRate.put( "lsbsId", "835" );
                     paramsRate.put( "liJenis", lijenis );
                     paramsRate.put( "liUsia", li_usia );
                     paramsRate.put( "kursId", istr_prop.kurs_id );
                     BigDecimal rate_factor = eproposalManager.selectTableFactor( paramsRate );
                     if( rate_factor == null ) { rate_factor = BigDecimal.ZERO;}
                     double rate_factor_double = LazyConverter.toDouble( rate_factor );

                     ldec_temp = FormatNumber.round( ( ( ldec_rate / 1000 )* istr_prop.up_schol * rate_factor_double ) * 0.1, 2 );
                     ldec_total += ldec_temp;
                     
                     if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("835"), new BigDecimal( lijenis ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
            	}            	
                break;
                
            case 27:
            	if( istr_prop.rider_baru[27] > 0  ){            		
            		String genderInsured = cepr01030101Form.getInsuredSexCd().trim();
            		li_jenis=0;
            		if(genderInsured.equals("L")){
            			li_jenis = 1;
            		}
            		else if(genderInsured.equals("P")){
            			li_jenis = 2;
            		}
            		
            		Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "lku_id", istr_prop.kurs_id );
                    params.put( "lsr_jenis", li_jenis );
                    params.put( "li_usia", li_usia);
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateEarlyStageCi99( params ) );
                            		
                    ldec_temp = FormatNumber.round(((ldec_rate / 1000) * istr_prop.up) * 0.1, 2);
                	ldec_total += ldec_temp;
                	
        			 if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("837"), new BigDecimal( li_jenis ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
            	}
                break;  
                
            default:
                break;
        }
        return ldec_total;
    }
    
    
    public double of_get_coi_141_cost( int ai_jenis, int ai_th, List< Mst_proposal > mstProposal )
    {
    	double ldec_total = 0, ldec_temp;
        double ldec_rate, ldec_max = 100000;
        int li_usia, li_tahun_ke, li_kali = 1, li_jenis, li_class, li_usia_tt, li_usia_pp, li_risk, li_temp;
        li_usia = istr_prop.umur_tt + ai_th - 1;
        li_usia_pp = istr_prop.umur_pp + ai_th - 1;
        
        double ldec_pct = 1;
        if(ai_jenis != 0){
        	if(istr_prop.cb_id == 1 ){  //triwulan
        		li_kali = 4;
        		ldec_pct = 0.27;
        	}else if( istr_prop.cb_id == 2){ //semesteran
        		li_kali = 2;
        		ldec_pct = 0.525;
        	}else if( istr_prop.cb_id == 6){ //bulanan
        		li_kali = 12;
        		ldec_pct = 0.1;
        	}
        }
        
        if( istr_prop.rider_baru[ 5 ] > 0 || istr_prop.rider_baru[ 6 ] > 0 || istr_prop.rider_baru[ 7 ] > 0 || istr_prop.rider_baru[ 8 ] > 0 || istr_prop.rider_baru[ 9 ] > 0 || istr_prop.rider_baru[ 10 ] > 0 )
        {
            if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_TRIWULANAN )
            {
                li_kali = 4;
            }
            else if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_SEMESTERAN )
            {
                li_kali = 2;
            }
            else if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_BULANAN )
            {
                li_kali = 12;
            }
        }
//
        switch( ai_jenis )
        {
            case 1:
                //pa
            	
                if( ( istr_prop.rider_baru[ 1 ] > 0 ) && ( li_usia < 60 ) )
                {
                    li_class = istr_prop.pa_class;
                    li_risk = 1;
                    li_temp = li_usia;
                	if(istr_prop.bisnis_id == 120  || istr_prop.bisnis_id == 202 || istr_prop.bisnis_id == 141){
                		//khusus 120-cerdas usia tdk naik
                		li_risk = istr_prop.pa_risk;
                		li_temp = istr_prop.umur_tt;
                	}
                    if(istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 141 || istr_prop.bisnis_id == 202 ){
                		if(li_temp <= 20){li_class = 2;}else{li_class = 1;}
            		}
                    //if( istr_prop.umur_tt <= 16 ) li_class = 2;
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "paRisk", li_risk );
                    params.put( "liClass", li_class );
                    double maks = 2000000000.0;
                    if( istr_prop.kurs_id.equals( Hardcode.CUR_USD_CD ) ){
                    	 maks = 200000.0;
                    }
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePa( params ) );
                    double maxAmount = MathUtil.min( istr_prop.rider_baru[ 1 ] * istr_prop.up, maks );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("810"), new BigDecimal( li_risk ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
            case 2:
                //hcp
                if( ( istr_prop.rider_baru[ 2 ] > 0 ) && ( li_usia < 65 ) )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 2 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );

                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcp( params ) );
                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("811"), new BigDecimal(istr_prop.rider_baru[ 2 ]),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                  }
                }
                break;
                
            case 3:
                //tpd
                if( istr_prop.rider_baru[ 3 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTpd_120( params ) );
                    // maks 2 milyar
                    if( "01".equals( istr_prop.kurs_id ) ){
                    	ldec_max = 2000000000;
                    }else if( "02".equals( istr_prop.kurs_id ) ){
                    	ldec_max = 200000;
                    }
                    double maxAmount = MathUtil.min( istr_prop.up, ldec_max );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("812"), new BigDecimal( "7" ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;	
            case 4:
                //ci
                if( istr_prop.rider_baru[ 4 ] > 0 )
                {
                	li_tahun_ke = li_usia;
                	if(istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 141){
                		li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
            		}
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_tahun_ke );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateCi( params ) );

                    ldec_temp = FormatNumber.round( ( ( istr_prop.up * 0.4 / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("813"), new BigDecimal("8"),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
                //
            case 5:
                //wp 60tpd
                if( istr_prop.rider_baru[ 5 ] > 0 )
                {
                	li_jenis = istr_prop.rider_baru[ 5 ];
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "li_jenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "ai_th", ai_th );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp60Tpd_120( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("814"), new BigDecimal( li_jenis ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                       }
                }
                break;
            case 6:
                //pb 25 tpd
                if( istr_prop.rider_baru[ 6 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 6 ];
                    Integer bisnisNo =  istr_prop.bisnis_no;
                    if( bisnisNo.equals(1) ) { li_jenis = 4; }
                    else { li_jenis = 5; }
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Tpd( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal( li_jenis ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
                }
                break;
            case 7:
                //wp 60ci
                if( istr_prop.rider_baru[ 7 ] > 0 )
                {

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 7 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "ai_th", ai_th );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp60Ci_120( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("816"), new BigDecimal( istr_prop.rider_baru[ 7 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            case 8:
                //pb 25 ci/death
                if( istr_prop.rider_baru[ 8 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 8 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25CiDeath( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("817"), new BigDecimal(li_jenis),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
                //pb 25 ci
                //--
            case 9:
                if( istr_prop.rider_baru[ 9 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    Integer bisnisNo =  istr_prop.bisnis_no;
                    if( bisnisNo.equals(1) ) { li_jenis = 2; }
                    else { li_jenis = 3; }

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Ci( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("817"), new BigDecimal(li_jenis),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                       }
                }
                break;
                //payor spouse
            case 10:
                if( istr_prop.rider_baru[ 10 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = 6;

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorSpouse( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal(li_jenis),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            case 11:
                //hcp family
                if( istr_prop.rider_baru[ 11 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 11 ] + 140 );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamily( params ) );

                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("819"), new BigDecimal( istr_prop.rider_baru[ 11 ] + 140 ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }

                //hcp family peserta tambahan
                if( istr_prop.rider_baru[ 11 ] > 0 && istr_prop.hcpf.peserta > 0 )
                {
                    for( int i = 1; i <= istr_prop.hcpf.peserta; i++ )
                    {
                    	li_usia_tt = istr_prop.hcpf.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 11 ] + 140 );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia_tt );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamilyPesertaTambahan( params ) );
//                        ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                        ldec_temp = 0;
                        if (!( i > 1 && li_usia_tt > 24 ) ){
                        	ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                    	}
                        ldec_total += ldec_temp;
                        Integer lsdbs_hcp_fam = null;
                        if( i == 1 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + 20;
                        }else if( i == 2 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 5 );
                        }
                        if( ai_th == 1 ){
                        	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("819"), new BigDecimal( lsdbs_hcp_fam ),
                        			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        			 new BigDecimal(istr_prop.cb_id),  istr_prop.hcpf.tgl[i], istr_prop.hcpf.usia[i] ) );
                        }
                    }
                }
                break;

            case 12:
                //term rider
                if( istr_prop.rider_baru[ 12 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTermRider( params ) );
                    ldec_temp = FormatNumber.round( ( ( istr_prop.up_term / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("818"), new BigDecimal( "2" ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
                
            case 13:
                //eka sehat
                if( istr_prop.rider_baru[ 13 ] > 0 && li_usia < 75 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 13 ] );  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) );
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
//                    ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
        			ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("823"), new BigDecimal( istr_prop.rider_baru[ 13 ] ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }

                //eka sehat peserta tambahan
                if( istr_prop.rider_baru[ 13 ] > 0 && istr_prop.eka_sehat.peserta > 0   && li_usia < 75 )
                {
                    for( int i = 1; i <= istr_prop.eka_sehat.peserta; i++ )
                    {
                        li_usia = istr_prop.eka_sehat.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 13 ]  );    
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) );
//                        ldec_temp = FormatNumber.round( (ldec_rate * 0.975 * ldec_pct) * 0.1, 2 );
                        ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_e_sehat = null;
                        if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + 15;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 5 );
                        }
                        if( ai_th == 1 ){
                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("823"), new BigDecimal( lsdbs_e_sehat ),
                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                          			 new BigDecimal(istr_prop.cb_id), istr_prop.eka_sehat.tgl[i], istr_prop.eka_sehat.usia[i] ) );
                        }
                    }
                }
                break;
                
            case 15:
            	//eka sehat inner limit
            	if( istr_prop.rider_baru[ 15 ] > 0 && li_usia < 75 )
            	{
            		Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 15 ] );  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ) );
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
                    ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("825"), new BigDecimal( istr_prop.rider_baru[ 15 ] ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
	            }
            	if( istr_prop.rider_baru[ 15 ] > 0 && istr_prop.ekaSehatInnerLimit.peserta > 0 && li_usia < 75 )
            	{
            		for ( int i = 1; i <= istr_prop.ekaSehatInnerLimit.peserta; i++)
            		{
            			li_usia = istr_prop.ekaSehatInnerLimit.usia[ i ] + ai_th - 1;
            			
            			Map<String, Object> params = new HashMap<String, Object>();
            			params.put( "riderBaru", istr_prop.rider_baru[ 15 ] );
            			params.put( "kursId", istr_prop.kurs_id );
            			params.put( "liUsia", li_usia );
            			ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ));
            			ldec_temp = FormatNumber.round((ldec_rate * 0.117), 2);
            			ldec_total += ldec_temp;
            			
            			Integer lsdbs_e_sehat = null;
            			if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + 15;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 5 );
                        }
            			if( ai_th == 1 ){
                         	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("825"), new BigDecimal( lsdbs_e_sehat ),
                         			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                         			 new BigDecimal(istr_prop.cb_id), istr_prop.ekaSehatInnerLimit.tgl[i], istr_prop.ekaSehatInnerLimit.usia[i] ) );
                        }
            		}
            	}
	            break;
                         
                
          
            case 112:
                //term rider header
                if( istr_prop.rider_baru[ 12 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTermRider( params ) );
                    ldec_temp = FormatNumber.round( ( ( istr_prop.up_term / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                }
                break;
            case 16:
                //hcp Provider
                if( istr_prop.rider_baru[ 16 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );   
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProvider( params ) );

                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("826"), new BigDecimal( istr_prop.rider_baru[ 16 ] ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }

                //hcp provider peserta tambahan
                if( istr_prop.rider_baru[ 16 ] > 0 && istr_prop.hcpPro.peserta > 0 && li_usia < 65 )
                {
                    for( int i = 1; i <= istr_prop.hcpPro.peserta; i++ )
                    {
                        li_usia = istr_prop.hcpPro.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProviderPesertaTambahan( params ) );
                        ldec_temp = 0;
                        if  (i > 1 && li_usia > 24){
                        	
                        }else{
                        	  ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                        }
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_hcp_pro= null;
                        if( i == 1 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + 12;
                        }else if( i == 2 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 5 );
                        }
                        
                        if( ai_th == 1 ){
                        	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("826"), new BigDecimal( lsdbs_hcp_pro ),
                        			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        			 new BigDecimal(istr_prop.cb_id), istr_prop.hcpPro.tgl[i], istr_prop.hcpPro.usia[i] ) );
                       }
                    }
                }
                break;
            case 18:
            	//Payor TPD/CI/Death
            	if( istr_prop.rider_baru[ 18 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 18 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorTpdCiDeath(params) );
                    
                    ldec_max = 200000;
//                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * 1 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("828"), new BigDecimal( li_jenis ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
            case 17:
            	//Waiver TPD/CI/Death
            	if( istr_prop.rider_baru[ 17 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", istr_prop.rider_baru[ 17 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", 0 );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWaiverTpdCi(params) );
                    
                    ldec_max = 200000;
//                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 10 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * 1 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("827"), new BigDecimal( istr_prop.rider_baru[ 17 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
            case 19:
                //ladies insurance
                if( istr_prop.rider_baru[ 19 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", istr_prop.rider_baru[ 19 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", 0 );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateLadiesIns(params) );
                    
                    ldec_max = 200000;
                    if( istr_prop.kurs_id.equals("01") ){ ldec_max = 2000000000;}
                    double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getLadiesInsChooseCd())) / 100.0;
                    double maxAmount = MathUtil.min( istr_prop.up * persentase, ldec_max );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("830"), new BigDecimal( istr_prop.rider_baru[ 19 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                break;
                
            case 20:
                //hcp ladies
            	 if( istr_prop.rider_baru[ 20 ] > 0 && istr_prop.hcpLad.peserta == 0)
                 {
                 	if( li_usia < 66 && istr_prop.hcpLad.peserta == 0 ){
 	                    Map<String, Object> params = new HashMap<String, Object>();
 	                    params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
 	                    params.put( "kursId", istr_prop.kurs_id );
 	                    params.put( "liUsia", li_usia );
 	                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadies( params ) );
 	
 	                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
 	                    ldec_total += ldec_temp;
 	                   if( ai_th == 1 ){
	                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("831"), new BigDecimal( istr_prop.rider_baru[ 20 ] ),
	                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
	                    }
                 	}
                 }

                 //hcp ladies peserta tambahan
                 if( istr_prop.rider_baru[ 20 ] > 0 && istr_prop.hcpLad.peserta > 0 )
                 {
                 	li_usia = istr_prop.hcpLad.usia[ 1 ] + ai_th - 1;
                 	if( li_usia < 66 && istr_prop.hcpLad.peserta > 0 )
                 	{
 	                    for( int i = 1; i <= istr_prop.hcpLad.peserta; i++ )
 	                    {
 	                        li_usia = istr_prop.hcpLad.usia[ i ] + ai_th - 1;
 	
 	                        Map<String, Object> params = new HashMap<String, Object>();
 	                        params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
 	                        params.put( "kursId", istr_prop.kurs_id );
 	                        params.put( "liUsia", li_usia );
 	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadiesPesertaTambahan( params ) );
 	                        ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
 	                        if(i >= 2){
 	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
 	                        	if(i>=3 && li_usia>25) ldec_temp = 0;
 	                        }
 	                        ldec_total += ldec_temp;
 	                        
 	                       Integer lsdbs_hcp_lad = null;
	                        if( i == 1 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ];
	                        }else if( i == 2 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 );
	                        }else if( i == 3 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 2 );
	                        }else if( i == 4 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 3 );
	                        }else if( i == 5 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 4 );
	                        }
	                        if( ai_th == 1 ){
	 	                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("831"), new BigDecimal( lsdbs_hcp_lad ),
	 	                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	 	                    			 new BigDecimal(istr_prop.cb_id), istr_prop.hcpLad.tgl[i], istr_prop.hcpLad.usia[i] ) );
	 	                    }
 	                    }
                 	}
                 }
                 break;
            case 21:
                //ladies medical expense
                if( istr_prop.rider_baru[ 21 ] > 0 && li_usia < 75 && istr_prop.ladiesMedExpense.peserta == 0)
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider(params));

                    ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("832"), new BigDecimal( istr_prop.rider_baru[ 21 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }

                //ladies medical expense peserta tambahan
                if( istr_prop.rider_baru[ 21 ] > 0 && istr_prop.ladiesMedExpense.peserta > 0 )
                {
                	li_usia = istr_prop.ladiesMedExpense.usia[1] + ai_th - 1;
                	if( li_usia < 75 ){
	                    for( int i = 1; i <= istr_prop.ladiesMedExpense.peserta; i++ )
	                    {
	                        li_usia = istr_prop.ladiesMedExpense.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
	                        if(i > 1){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.117, 2 );
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_ladies_med = null;
	                        if( i == 1 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ];
 	                        }else if( i == 2 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 );
 	                        }else if( i == 3 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 2 );
 	                        }else if( i == 4 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 3 );
 	                        }else if( i == 5 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 4 );
 	                        }
 	                        
	                        if( ai_th == 1 ){
	                       	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("832"), new BigDecimal( lsdbs_ladies_med ),
	                       			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                       			 new BigDecimal(istr_prop.cb_id), istr_prop.ladiesMedExpense.tgl[i], istr_prop.ladiesMedExpense.usia[i] ) );
	                       }
	                    }
                	}
                }
                break;
            case 22:
                //ladies medical expense inner limit
                if( istr_prop.rider_baru[ 22 ] > 0 && li_usia < 75 && istr_prop.ladiesMedExpenseInnerLimit.peserta == 0)
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider(params));

                    ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("833"), new BigDecimal( istr_prop.rider_baru[ 22 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }

                //ladies medical expense inner limit peserta tambahan
                if( istr_prop.rider_baru[ 22 ] > 0 && istr_prop.ladiesMedExpenseInnerLimit.peserta > 0 )
                {
                	li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[1] + ai_th - 1;
                	if( li_usia < 75 ){
	                    for( int i = 1; i <= istr_prop.ladiesMedExpenseInnerLimit.peserta; i++ )
	                    {
	                        li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
	                        if(i > 1){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.117, 2 );
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_ladies_med_il = null;
	                        if( i == 1 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ];
 	                        }else if( i == 2 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 );
 	                        }else if( i == 3 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 2 );
 	                        }else if( i == 4 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 3 );
 	                        }else if( i == 5 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 4 );
 	                        }
 	                        
	                        if( ai_th == 1 ){
	                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("833"), new BigDecimal( lsdbs_ladies_med_il ),
	                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                          			 new BigDecimal(istr_prop.cb_id), istr_prop.ladiesMedExpenseInnerLimit.tgl[i], istr_prop.ladiesMedExpenseInnerLimit.usia[i] ) );
	                        }
	                    }
                	}
                }
                break;                
            case 25:
            	if( istr_prop.rider_baru[25] > 0 && li_usia < istr_prop.usia_schol ){
            		 Map<String, Object> params = new HashMap<String, Object>();
                     params.put( "kursId", istr_prop.kurs_id );
                     params.put( "liUsia", li_usia_pp );
                     ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateScholarship( params ) );
                     
                     Map<String, Object> paramsRate = new HashMap<String, Object>();
                     Integer lijenis = 0;
                     if( istr_prop.usia_schol == 22 ){
                    	 lijenis = 1;
                     }else if( istr_prop.usia_schol == 25 ){
                    	 lijenis = 2;
                     }
                     paramsRate = new HashMap<String, Object>();
                     paramsRate.put( "lsbsId", "835" );
                     paramsRate.put( "liJenis", lijenis );
                     paramsRate.put( "liUsia", li_usia );
                     paramsRate.put( "kursId", istr_prop.kurs_id );
                     BigDecimal rate_factor = eproposalManager.selectTableFactor( paramsRate );
                     if( rate_factor == null ) { rate_factor = BigDecimal.ZERO;}
                     double rate_factor_double = LazyConverter.toDouble( rate_factor );

                     ldec_temp = FormatNumber.round( ( ( ldec_rate / 1000 )* istr_prop.up_schol * rate_factor_double ) * 0.1, 2 );
                     ldec_total += ldec_temp;
                     
                     if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("835"), new BigDecimal( lijenis ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
            	}
            	
                break;                
            case 27:
            	if( istr_prop.rider_baru[27] > 0  ){            		
            		String genderInsured = cepr01030101Form.getInsuredSexCd().trim();
            		li_jenis=0;
            		if(genderInsured.equals("L")){
            			li_jenis = 1;
            		}
            		else if(genderInsured.equals("P")){
            			li_jenis = 2;
            		}
            		
            		Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "lku_id", istr_prop.kurs_id );
                    params.put( "lsr_jenis", li_jenis );
                    params.put( "li_usia", li_usia);
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateEarlyStageCi99( params ) );
                    /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
                    double upEsci99 =  cepr01030103Form.getEsci99RiderAmount().doubleValue();
                    double maxUp;
                    if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) ){
                    	maxUp = 500000.0;
                    }else{
                    	maxUp = 5000000000.0;
                    }
                    double temp = MathUtil.min( upEsci99, maxUp );        		
                    ldec_temp = FormatNumber.round(((ldec_rate / 1000) * temp) * 0.1, 2);
                	ldec_total += ldec_temp;
                	
        			 if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("837"), new BigDecimal( li_jenis ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
            	}
                break; 
                
            default:
                break;
        }
        cepr01031001Form.setMstProposal( mstProposal );
        return ldec_total;
    }
    
    public void wf_set_120()
    {
    	if( istr_prop.bisnis_id == 120){
    		if(istr_prop.rider_baru[5]>0)//814 waiver tpd
    		{
    			if( istr_prop.bisnis_no == 1 || istr_prop.bisnis_no == 4 || istr_prop.bisnis_no == 7 || istr_prop.bisnis_no == 10 || istr_prop.bisnis_no == 13 || istr_prop.bisnis_no == 19 || istr_prop.bisnis_no == 22 )
    			{
    				istr_prop.rider_baru[5] = 4;
    			}else{
    				istr_prop.rider_baru[5] = 5;
    			}
    			if( istr_prop.bisnis_no == 15 ) istr_prop.rider_baru[5] = 1;
    		}
    		if(istr_prop.rider_baru[6]>0)//815 payor tpd
			{
    			if( istr_prop.bisnis_no == 1 || istr_prop.bisnis_no == 4 || istr_prop.bisnis_no == 7 || istr_prop.bisnis_no == 10 || istr_prop.bisnis_no == 13 || istr_prop.bisnis_no == 19 || istr_prop.bisnis_no == 22)
    			{
    				istr_prop.rider_baru[6] = 4;
    			}else{
    				istr_prop.rider_baru[6] = 5;
    			}
    			if( istr_prop.bisnis_no == 15 ) istr_prop.rider_baru[6] = 1;
			}
    		if(istr_prop.rider_baru[7]>0)//816 waiver ci
			{
    			if( istr_prop.bisnis_no == 1 || istr_prop.bisnis_no == 4 || istr_prop.bisnis_no == 7 || istr_prop.bisnis_no == 10 || istr_prop.bisnis_no == 13 || istr_prop.bisnis_no == 19 || istr_prop.bisnis_no == 22 )
    			{
    				istr_prop.rider_baru[7] = 2;
    			}else{
    				istr_prop.rider_baru[7] = 3;
    			}
    			if( istr_prop.bisnis_no == 15 ) istr_prop.rider_baru[7] = 1;
			}
    		if(istr_prop.rider_baru[8]>0)//817 payor ci
			{
    			if( istr_prop.bisnis_no == 1 || istr_prop.bisnis_no == 4 || istr_prop.bisnis_no == 7 || istr_prop.bisnis_no == 10 || istr_prop.bisnis_no == 13 || istr_prop.bisnis_no == 19 || istr_prop.bisnis_no == 22 )
    			{
    				istr_prop.rider_baru[8] = 2;
    			}else{
    				istr_prop.rider_baru[8] = 3;
    			}
    			if( istr_prop.bisnis_no == 15 ) istr_prop.rider_baru[8] = 1;
			}
    		if(istr_prop.rider_baru[12]>0)//818 term
			{
    			istr_prop.rider_baru[12] = 2;
			}
    		if(istr_prop.rider_baru[23]>0)//828 payor 5/10 tpd/ci/death
			{
    			if( istr_prop.bisnis_no == 1 || istr_prop.bisnis_no == 4 || istr_prop.bisnis_no == 7 || istr_prop.bisnis_no == 10 || istr_prop.bisnis_no == 13 || istr_prop.bisnis_no == 19 || istr_prop.bisnis_no == 22 )
    			{
    				istr_prop.rider_baru[23] = 2;
    			}else{
    				istr_prop.rider_baru[23] = 3;
    			}
    			if( istr_prop.bisnis_no == 15 ) istr_prop.rider_baru[23] = 1;
			}
    		if(istr_prop.rider_baru[24]>0)
			{
    			if( istr_prop.bisnis_no == 1 || istr_prop.bisnis_no == 4 || istr_prop.bisnis_no == 7 || istr_prop.bisnis_no == 10 || istr_prop.bisnis_no == 13 || istr_prop.bisnis_no == 19 || istr_prop.bisnis_no == 22)
    			{
    				istr_prop.rider_baru[24] = 4;
    			}else{
    				istr_prop.rider_baru[24] = 5;
    			}
    			if( istr_prop.bisnis_no == 15 ) istr_prop.rider_baru[24] = 1;
			}
    	}
    	else if( istr_prop.bisnis_id == 202){
    		//814 waiver tpd
    		if(istr_prop.rider_baru[5]>0)istr_prop.rider_baru[5] = istr_prop.bisnis_no == 1 ? 14 :15;
    		//815 payor tpd
    		if(istr_prop.rider_baru[6]>0)istr_prop.rider_baru[6] = istr_prop.bisnis_no == 1 ? 4 : 5;
    		//816 waiver ci
    		if(istr_prop.rider_baru[7]>0)istr_prop.rider_baru[7] = istr_prop.bisnis_no == 1 ? 2 : 3;
    		//817 payor ci
    		if(istr_prop.rider_baru[8]>0)istr_prop.rider_baru[8] = istr_prop.bisnis_no == 1 ? 2 : 3;
    		//818 term
    		if(istr_prop.rider_baru[12]>0)istr_prop.rider_baru[12] = 2;
    		//828 payor 5/10 tpd/ci/death
    		if(istr_prop.rider_baru[23]>0)
    		{
    			if( istr_prop.bisnis_no == 1 || istr_prop.bisnis_no == 4 )
    			{
    				istr_prop.rider_baru[23] = 2;
    			}else{
    				istr_prop.rider_baru[23] = 3;
    			}
    		}
    		if(istr_prop.rider_baru[24]>0)
    		{
    			if( istr_prop.bisnis_no == 1 || istr_prop.bisnis_no == 4 )
    			{
    				istr_prop.rider_baru[24] = 4;
    			}else{
    				istr_prop.rider_baru[24] = 5;
    			}    			
    		}
    	}
     	else if( istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 141 ){
     		if( istr_prop.bisnis_no == 1 || istr_prop.bisnis_no == 3 || istr_prop.bisnis_no == 7 || istr_prop.bisnis_no == 9 )
			{
     			istr_prop.rider_baru[1] = 1;  //810 pa
     			istr_prop.rider_baru[6] = 14;  //815 payor tpd
     			istr_prop.rider_baru[8] = 5;  //817 payor ci
			}
     		else if( istr_prop.bisnis_no == 2 || istr_prop.bisnis_no == 4 || istr_prop.bisnis_no == 8 || istr_prop.bisnis_no == 10 )
			{
     			istr_prop.rider_baru[1] = 1;  //810 pa
     			istr_prop.rider_baru[6] = 15;  //815 payor tpd
     			istr_prop.rider_baru[8] = 6;  //817 payor ci
			}
     		if( istr_prop.bisnis_no == 3 || istr_prop.bisnis_no == 4 || istr_prop.bisnis_no == 9 || istr_prop.bisnis_no == 10 )
			{
     			istr_prop.rider_baru[4] = 1;  //813 ci
			}
     		if( istr_prop.bisnis_id == 141 ){
     			 if( istr_prop.rider_baru[9] > 0 ) {
     				 istr_prop.rider_baru[9] = 2;
     				 if( istr_prop.bisnis_no == 2 ) istr_prop.rider_baru[9] = 3;
     			 }
     			 else if( istr_prop.rider_baru[6] > 0 ) {
     				 istr_prop.rider_baru[6] = 4;
     				 if( istr_prop.bisnis_no == 2 ) istr_prop.rider_baru[6] = 5;
     			 }
     			 else if( istr_prop.rider_baru[23] > 0 ){
     				 istr_prop.rider_baru[23] = 2;
     				 if( istr_prop.bisnis_no == 2 ) istr_prop.rider_baru[23] = 3;
     			 }
     			 else if( istr_prop.rider_baru[24] > 0 ){
     				 istr_prop.rider_baru[24] = 4;
     				 if( istr_prop.bisnis_no == 2 ) istr_prop.rider_baru[24] = 5;
     			 }
     		}
     	}
     	else if( istr_prop.bisnis_id == 128){ //cerdas proteksi 
     		if( istr_prop.bisnis_no == 1 || istr_prop.bisnis_no == 3 || istr_prop.bisnis_no == 7 || istr_prop.bisnis_no == 9 )
			{
     			//cerdas proteksi A/B 5
    			istr_prop.rider_baru[1] = 2;  //810 pa
    			istr_prop.rider_baru[3] = 2;  //812 TPD
    			istr_prop.rider_baru[4] = 1;  //813 ci
    			istr_prop.rider_baru[5] = 14;  //814 waiver tpd
    			istr_prop.rider_baru[7] = 4;  //816 waiver ci
    			istr_prop.rider_baru[12] = 1;  //818 term
			}
     		else if( istr_prop.bisnis_no == 2 || istr_prop.bisnis_no == 4 || istr_prop.bisnis_no == 8 || istr_prop.bisnis_no == 10 )
			{
    			istr_prop.rider_baru[1] = 2;  //810 pa
    			istr_prop.rider_baru[3] = 2;  //812 TPD
    			istr_prop.rider_baru[4] = 1;  //813 ci
    			istr_prop.rider_baru[5] = 15;  //814 waiver tpd
    			istr_prop.rider_baru[7] = 5;  //816 waiver ci
    			istr_prop.rider_baru[12] = 1;  //818 term
			}
     		if( istr_prop.bisnis_no == 3 || istr_prop.bisnis_no == 4 || istr_prop.bisnis_no == 9 || istr_prop.bisnis_no == 10 )
			{
     			istr_prop.rider_baru[2] = 5;  //811 hcp r500
     			if( istr_prop.kurs_id.equals("02") ) istr_prop.rider_baru[2] = 15;  //811 hcp d50
			}
     	}
     	else if( istr_prop.bisnis_id == 129){ //cerdas sejahtera
     		istr_prop.rider_baru[1] = 2;  //810 pa
     		istr_prop.rider_baru[3] = 2;  //812 TPD		
     		if( istr_prop.bisnis_no == 3 || istr_prop.bisnis_no == 4 || istr_prop.bisnis_no == 9 || istr_prop.bisnis_no == 10 )
			{
     			istr_prop.rider_baru[4] = 1;  //813 ci
     			if( istr_prop.bisnis_no == 3 || istr_prop.bisnis_no == 9 )	istr_prop.rider_baru[7] = 4;  //816 waiver ci	
     			if( istr_prop.bisnis_no == 4 || istr_prop.bisnis_no == 10 )	istr_prop.rider_baru[7] = 5;  //816 waiver ci	
			}
     		if( cepr01030102Form.getSmilePensionPackageCd() != null && cepr01030102Form.getSmilePensionPackageCd() > 1 ){
     			istr_prop.rider_baru[1] = 0;  //810 pa
         		istr_prop.rider_baru[3] = 0;  //812 TPD		
         		istr_prop.rider_baru[4] = 0;  //813 ci
         		istr_prop.rider_baru[7] = 0;  //816 waiver ci	
         		
       			istr_prop.rider_baru[1] = 2;  //810 pa
     			if( istr_prop.bisnis_no == 5 ){
     				istr_prop.rider_baru[5] = 14;  //814 waiver tpd
     			}else if( istr_prop.bisnis_no == 6 ){
     				istr_prop.rider_baru[5] = 15;  //814 waiver tpd
     			}
         		if( cepr01030102Form.getSmilePensionPackageCd() != null && cepr01030102Form.getSmilePensionPackageCd().equals( 2 ) ){// PAKET A
      
         			
         		}else if( cepr01030102Form.getSmilePensionPackageCd() != null && cepr01030102Form.getSmilePensionPackageCd().equals( 3 ) ){// PAKET B
         			istr_prop.rider_baru[4] = 1;  //813 ci
         			if( istr_prop.bisnis_no == 5 )	istr_prop.rider_baru[7] = 4;  //816 waiver ci	
         			else if( istr_prop.bisnis_no == 6 )	istr_prop.rider_baru[7] = 5;  //816 waiver ci	
         		}else if( cepr01030102Form.getSmilePensionPackageCd() != null && cepr01030102Form.getSmilePensionPackageCd().equals( 4 ) ){// PAKET c
         			istr_prop.rider_baru[4] = 1;  //813 ci
         			istr_prop.rider_baru[13] = 8;
         			if( istr_prop.bisnis_no == 5 )	istr_prop.rider_baru[7] = 4;  //816 waiver ci	
         			else if( istr_prop.bisnis_no == 6 )	istr_prop.rider_baru[7] = 5;  //816 waiver ci	
         		}
     		}
     	}
     		
    }
    public double of_get_coi_120( int ai_jenis, int ai_th )
    {
    	
    	double ldec_total = 0, ldec_temp, ldec_persen_ci = 0;
    	double ldec_pct = 1;
        double ldec_rate, ldec_max = 100000;
        int li_usia, li_tahun_ke, li_kali = 1, li_jenis, li_class, li_temp, li_risk, li_usia_tt, li_usia_pp;
        li_usia = istr_prop.umur_tt + ai_th - 1;
        li_usia_pp = istr_prop.umur_pp + ai_th - 1;
        List< Mst_proposal > mstProposal = new ArrayList<Mst_proposal>();

//        if( istr_prop.rider_baru[ 5 ] > 0 || istr_prop.rider_baru[ 6 ] > 0 || istr_prop.rider_baru[ 7 ] > 0 || istr_prop.rider_baru[ 8 ] > 0 || istr_prop.rider_baru[ 9 ] > 0 || istr_prop.rider_baru[ 10 ] > 0 )
//        {
            if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_TRIWULANAN )
            {
                li_kali = 4;
            }
            else if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_SEMESTERAN )
            {
                li_kali = 2;
            }
            else if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_BULANAN )
            {
                li_kali = 12;
            }
//        }
        wf_set_120();
        switch( ai_jenis )
        {
            case 1:
                //pa
            	
                if( ( istr_prop.rider_baru[ 1 ] > 0 ) && ( li_usia < 60 ) )
                {
                    li_class = istr_prop.pa_class;
                    li_risk = 1;
                    li_temp = li_usia;
                	if(istr_prop.bisnis_id == 120  || istr_prop.bisnis_id == 202 || istr_prop.bisnis_id == 141){
                		//khusus 120-cerdas usia tdk naik
                		li_risk = istr_prop.pa_risk;
                		li_temp = istr_prop.umur_tt;
                	}
                    if(istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 141 || istr_prop.bisnis_id == 202 ){
                		if(li_temp <= 20){li_class = 2;}
            		}
                    if(istr_prop.bisnis_id == 120){
                    	if( li_temp <= 16 ) li_class = 2;
                    }
                  
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    params.put( "paRisk", li_risk );
                    params.put( "liClass", li_class );
                    double maks = 2000000000.0;
                    if( istr_prop.kurs_id.equals( Hardcode.CUR_USD_CD ) ){
                    	 maks = 200000.0;
                    }
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePa( params ) );
                    double maxAmount = MathUtil.min( istr_prop.rider_baru[ 1 ] * istr_prop.up, maks );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("810"), new BigDecimal(li_risk),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            case 2:
                //hcp
                if( ( istr_prop.rider_baru[ 2 ] > 0 ) && ( li_usia < 65 ) )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 2 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );

                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcp( params ) );
                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("811"), new BigDecimal(istr_prop.rider_baru[ 2 ]),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
                
            case 3:
                //tpd
                if( istr_prop.rider_baru[ 3 ] > 0 )
                {
                	li_jenis = istr_prop.bisnis_id == 120 || istr_prop.bisnis_id == 202 ? 7 : istr_prop.rider_baru[3];
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    params.put( "li_jenis", li_jenis );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTpd_120( params ) );
                    // maks 2 milyar
                    if( "01".equals( istr_prop.kurs_id ) ){
                    	ldec_max = 2000000000;
                    }else if( "02".equals( istr_prop.kurs_id ) ){
                    	ldec_max = 200000;
                    }
                    double maxAmount = MathUtil.min( istr_prop.up, ldec_max );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("812"), new BigDecimal(li_jenis),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
            case 4:
                //ci
                if( istr_prop.rider_baru[ 4 ] > 0 &&  li_usia < 75)
                {
                	li_tahun_ke = li_usia;
                	if(istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 141){
                		li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
            		}
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_tahun_ke );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateCi( params ) );
                    
//                    ldec_temp = ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali ) * 2;
//                    ldec_max = 500000000;
//                    if(istr_prop.kurs_id.equals("02")){
//                    	ldec_max = 50000;
//                    }
//                    double minAmount = MathUtil.min( ldec_temp, ldec_max );
//                    ldec_temp = FormatNumber.round( ( ( minAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
//                    if(istr_prop.bisnis_id == 120 || istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 128 || istr_prop.bisnis_id == 129 || istr_prop.bisnis_id == 202){
//                    	if( istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 128 || istr_prop.bisnis_id == 129){
//                    		ldec_persen_ci = 1;
//                    		if( istr_prop.bisnis_id == 129 && cepr01030102Form.getSmilePensionPackageCd() != null && cepr01030102Form.getSmilePensionPackageCd() > 1 ){
//                    			ldec_persen_ci = 0.5;
//                    		}
//                    	}else{
//                    		ldec_persen_ci = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getCiChooseCd())) / 100.0;
//                    	}
//                    	ldec_max = 2000000000;
//                   	 if(istr_prop.kurs_id.equals("02")){
//                   		 ldec_max = 200000;
//                   	 }
                    	/**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
                        double upCi = cepr01030103Form.getCiRiderAmount().doubleValue();
                        if( istr_prop.kurs_id.equals("02") ){
                        	ldec_max = 500000.0;
                        }else{
                        	ldec_max = 5000000000.0;
                        }
                        double minAmount = MathUtil.min(upCi, ldec_max);
                    	 ldec_temp = FormatNumber.round(((minAmount / 1000) * ldec_rate) * 0.1, 2);
//                    }
                    
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("813"), new BigDecimal("8"),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                  }
                }
                break;
                //
            case 5:
                //wp 60tpd
                if( istr_prop.rider_baru[ 5 ] > 0 )
                {
                	li_jenis = istr_prop.rider_baru[ 5 ];
                	if( ( istr_prop.bisnis_id == 120 && istr_prop.bisnis_no != 10 && istr_prop.bisnis_no != 11 && istr_prop.bisnis_no != 12 && istr_prop.bisnis_no != 22 && istr_prop.bisnis_no != 23 && istr_prop.bisnis_no != 24) || istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 128 || istr_prop.bisnis_id == 129 ){
                		if( cepr01030102Form.getTermOfPayment().intValue() == 5 ){//5
                			li_jenis = 14;
                		}else if( cepr01030102Form.getTermOfPayment().intValue() == 10 ){//10
                			li_jenis = 15;
                		}
                	}
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "li_jenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "ai_th", ai_th );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp60Tpd_120( params ) );
                    double factor = istr_prop.pct_premi / 100;
                    if( istr_prop.bisnis_id == 129 && cepr01030102Form.getSmilePensionPackageCd() != null && cepr01030102Form.getSmilePensionPackageCd() > 1 ){
                    	factor = 1;
                    }
                   // *** Rider Payor dan Waiver Simas Power Link(120) mengcover Premi Pokok + Top Up : Desvinna@9/10/2013 ***		
                    //ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * factor ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("814"), new BigDecimal(li_jenis),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            case 6:
                //pb 25 tpd
                if( istr_prop.rider_baru[ 6 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 6 ];
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
//                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Tpd( params ) );
                    // *** Rider Payor dan Waiver Simas Power Link(120) mengcover Premi Pokok + Top Up : Desvinna@9/10/2013 ***		
                    //ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal(li_jenis),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
                
            case 7:
                //wp 60ci
                if( istr_prop.rider_baru[ 7 ] > 0 )
                {
//                	li_jenis = istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 129 || istr_prop.bisnis_id == 128 ? 4 : istr_prop.rider_baru[ 7 ];
                	li_jenis = istr_prop.rider_baru[ 7 ];
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "ai_th", ai_th );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp60Ci_120( params ) );
                    double factor = istr_prop.pct_premi / 100;
                    if( istr_prop.bisnis_id == 129 && cepr01030102Form.getSmilePensionPackageCd() != null && cepr01030102Form.getSmilePensionPackageCd() > 1 ){
                    	factor = 1;
                    }
                   // *** Rider Payor dan Waiver Simas Power Link(120) mengcover Premi Pokok + Top Up : Desvinna@9/10/2013 ***		
                    //ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * factor ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("816"), new BigDecimal(li_jenis),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
            case 8:
                //pb 25 ci/death
                if( istr_prop.rider_baru[ 8 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 8 ];
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25CiDeath( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("817"), new BigDecimal(li_jenis),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
                //pb 25 ci
                //--
            case 9:
                if( istr_prop.rider_baru[ 9 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 9 ];
                    if( istr_prop.bisnis_id == Hardcode.PRODUK_CERDAS ){
                    	if( istr_prop.bisnis_no == 10 || istr_prop.bisnis_no == 22 ){// simpol
                    		li_jenis = 2;
                    	}else if( istr_prop.bisnis_no == 11 || istr_prop.bisnis_no == 23){
                    		li_jenis = 3;
                    	}
                    }
                    
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Ci( params ) );
                    // *** Rider Payor dan Waiver Simas Power Link(120) mengcover Premi Pokok + Top Up : Desvinna@9/10/2013 ***		
                    //ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("817"), new BigDecimal(li_jenis),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
                //payor spouse
            case 10:
                if( istr_prop.rider_baru[ 10 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = 13;

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorSpouse( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal(li_jenis),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }
                break;
                
            case 11:
                //hcp family
                if( istr_prop.rider_baru[ 11 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 11 ] + 140 );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamily( params ) );

                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("819"), new BigDecimal( istr_prop.rider_baru[ 11 ] + 140 ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                //hcp family peserta tambahan
                if( istr_prop.rider_baru[ 11 ] > 0 && istr_prop.hcpf.peserta > 0 && li_usia < 65 )
                {
                    for( int i = 1; i <= istr_prop.hcpf.peserta; i++ )
                    {
                    	li_usia_tt = istr_prop.hcpf.usia[ i ] + ai_th - 1;
                    	

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 11 ] + 140 );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia_tt );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamilyPesertaTambahan( params ) );
                    	ldec_temp = 0;
                    	  if (!( i > 1 && li_usia_tt > 24 ) ){
                    		  ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                    	}
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_hcp_fam = null;
                        if( i == 1 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + 20;
                        }else if( i == 2 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 5 );
                        }
                        if( ai_th == 1 ){
                        	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("819"), new BigDecimal( lsdbs_hcp_fam ),
                        			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        			 new BigDecimal(istr_prop.cb_id),  istr_prop.hcpf.tgl[i], istr_prop.hcpf.usia[i] ) );
                        }
                    }
                }
                break;
            case 12:
                //term rider
            	li_temp = 10;
            	if(istr_prop.bisnis_id == 120 || istr_prop.bisnis_id == 202)	li_temp = 70;
                if( istr_prop.rider_baru[ 12 ] > 0 && ai_th <= li_temp )
                {
                	double up_term = istr_prop.up_term;
                	if( istr_prop.bisnis_id == 128 ){
                		up_term = istr_prop.up;
                	}
                	li_jenis = istr_prop.rider_baru[ 12 ];
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTermRider_120( params ) );
                    ldec_temp = FormatNumber.round( ( ( up_term / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("818"), new BigDecimal( li_jenis ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            case 13:
                //eka sehat
                if( istr_prop.rider_baru[ 13 ] > 0 && li_usia < 75 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 13 ] );  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) );
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
//                    ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
        			ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("823"), new BigDecimal( istr_prop.rider_baru[ 13 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }

                //eka sehat peserta tambahan
                if( istr_prop.rider_baru[ 13 ] > 0 && istr_prop.eka_sehat.peserta > 0   && li_usia < 75 )
                {
                    for( int i = 1; i <= istr_prop.eka_sehat.peserta; i++ )
                    {
                        li_usia = istr_prop.eka_sehat.usia[ i ] + ai_th - 1;
                        
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 13 ]  );    
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) );
//                        ldec_temp = FormatNumber.round( (ldec_rate * 0.975 * ldec_pct) * 0.1, 2 );
                        ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_e_sehat = null;
                        if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + 15;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 5 );
                        }
                        if( ai_th == 1 ){
                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("823"), new BigDecimal( lsdbs_e_sehat ),
                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                          			 new BigDecimal(istr_prop.cb_id), istr_prop.eka_sehat.tgl[i], istr_prop.eka_sehat.usia[i] ) );
                        }
                    }
                }
                break;
                
            case 15:
            	//eka sehat inner limit
            	if( istr_prop.rider_baru[ 15 ] > 0 && li_usia < 75 )
            	{
            		Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 15 ] );  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ) );
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
                    ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("825"), new BigDecimal( istr_prop.rider_baru[ 15 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
	            }
            	if( istr_prop.rider_baru[ 15 ] > 0 && istr_prop.ekaSehatInnerLimit.peserta > 0 && li_usia < 75 )
            	{
            		for ( int i = 1; i <= istr_prop.ekaSehatInnerLimit.peserta; i++)
            		{
            			li_usia = istr_prop.ekaSehatInnerLimit.usia[ i ] + ai_th - 1;
            			
            			Map<String, Object> params = new HashMap<String, Object>();
            			params.put( "riderBaru", istr_prop.rider_baru[ 15 ] );
            			params.put( "kursId", istr_prop.kurs_id );
            			params.put( "liUsia", li_usia );
            			ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ));
            			ldec_temp = FormatNumber.round((ldec_rate * 0.117), 2);
            			ldec_total += ldec_temp;
            			
            			Integer lsdbs_e_sehat = null;
            			if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + 15;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 5 );
                        }
            			if( ai_th == 1 ){
                         	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("825"), new BigDecimal( lsdbs_e_sehat ),
                         			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                         			 new BigDecimal(istr_prop.cb_id), istr_prop.ekaSehatInnerLimit.tgl[i], istr_prop.ekaSehatInnerLimit.usia[i] ) );
                        }
            		}
            	}
	            break;
          
            case 16:
                //hcp Provider
                if( istr_prop.rider_baru[ 16 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );   
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProvider( params ) );

                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("826"), new BigDecimal( istr_prop.rider_baru[ 16 ] ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }

                //hcp provider peserta tambahan
                if( istr_prop.rider_baru[ 16 ] > 0 && istr_prop.hcpPro.peserta > 0 && li_usia < 65 )
                {
                    for( int i = 1; i <= istr_prop.hcpPro.peserta; i++ )
                    {
                        li_usia = istr_prop.hcpPro.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProviderPesertaTambahan( params ) );
                        ldec_temp = 0;
                        if (!( i > 1 && li_usia > 24 ) ){
                        	  ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                        }
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_hcp_pro= null;
                        if( i == 1 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + 12;
                        }else if( i == 2 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 5 );
                        }
                        
                        if( ai_th == 1 ){
                        	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("826"), new BigDecimal( lsdbs_hcp_pro ),
                        			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        			 new BigDecimal(istr_prop.cb_id), istr_prop.hcpPro.tgl[i], istr_prop.hcpPro.usia[i] ) );
                       }
                    }
                }
                break;
                
            case 17:
            	//Waiver TPD/CI/Death
            	if( istr_prop.rider_baru[ 17 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    params.put( "liJenis", istr_prop.rider_baru[ 17 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", 0 );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWaiverTpdCi(params) );
                    
                    ldec_max = 200000;
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * 1 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("827"), new BigDecimal( istr_prop.rider_baru[ 17 ] ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
                
            case 18:
            	//Payor TPD/CI/Death
            	if( istr_prop.rider_baru[ 18 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 18 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getPolicyHolderSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorTpdCiDeath(params) );
                    
                    ldec_max = 200000;
//                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ), 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * 1 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("828"), new BigDecimal( li_jenis ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
            
            case 19:
                //ladies insurance
                if( istr_prop.rider_baru[ 19 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", istr_prop.rider_baru[ 19 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", 0 );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateLadiesIns(params) );
                    
                    ldec_max = 200000;
                    if( istr_prop.kurs_id.equals("01") ){ ldec_max = 2000000000;}
                    double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getLadiesInsChooseCd())) / 100.0;
                    double maxAmount = MathUtil.min( istr_prop.up * persentase, ldec_max );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("830"), new BigDecimal( istr_prop.rider_baru[ 19 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
                
            case 20:
                //hcp ladies
            	 if( istr_prop.rider_baru[ 20 ] > 0 && istr_prop.hcpLad.peserta == 0)
                 {
                 	if( li_usia < 66 && istr_prop.hcpLad.peserta == 0 ){
 	                    Map<String, Object> params = new HashMap<String, Object>();
 	                    params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
 	                    params.put( "kursId", istr_prop.kurs_id );
 	                    params.put( "liUsia", li_usia );
 	                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadies( params ) );
 	
 	                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
 	                    ldec_total += ldec_temp;
 	                   if( ai_th == 1 ){
 	                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("831"), new BigDecimal( istr_prop.rider_baru[ 20 ] ),
 	                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
 	                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
 	                    }
                 	}
                 }

                 //hcp ladies peserta tambahan
                 if( istr_prop.rider_baru[ 20 ] > 0 && istr_prop.hcpLad.peserta > 0 )
                 {
                 	li_usia = istr_prop.hcpLad.usia[ 1 ] + ai_th - 1;
                 	if( li_usia < 66 && istr_prop.hcpLad.peserta > 0 )
                 	{
 	                    for( int i = 1; i <= istr_prop.hcpLad.peserta; i++ )
 	                    {
 	                        li_usia = istr_prop.hcpLad.usia[ i ] + ai_th - 1;
 	                       
 	                        Map<String, Object> params = new HashMap<String, Object>();
 	                        params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
 	                        params.put( "kursId", istr_prop.kurs_id );
 	                        params.put( "liUsia", li_usia );
 	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadiesPesertaTambahan( params ) );
 	                        ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
 	                        if(i >= 2){
 	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
 	                        	if(i>=3 && li_usia>25) ldec_temp = 0;
 	                        }
 	                        ldec_total += ldec_temp;
 	                        
 	                        Integer lsdbs_hcp_lad = null;
 	                        if( i == 1 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ];
	                        }else if( i == 2 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 );
	                        }else if( i == 3 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 2 );
	                        }else if( i == 4 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 3 );
	                        }else if( i == 5 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 4 );
	                        }
 	                        if( ai_th == 1 ){
 	 	                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("831"), new BigDecimal( lsdbs_hcp_lad ),
 	 	                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
 	 	                    			 new BigDecimal(istr_prop.cb_id), istr_prop.hcpLad.tgl[i], istr_prop.hcpLad.usia[i] ) );
 	 	                    }
 	                    }
                 	}
                 }
                 break;
            case 21:
                //ladies medical expense
                if( istr_prop.rider_baru[ 21 ] > 0  && istr_prop.ladiesMedExpense.peserta == 0 && li_usia < 75 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider(params));

                    ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("832"), new BigDecimal( istr_prop.rider_baru[ 21 ] ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }

                //ladies medical expense peserta tambahan
                
              
                if( istr_prop.rider_baru[ 21 ] > 0 && istr_prop.ladiesMedExpense.peserta > 0  )
                {
                	li_usia = istr_prop.ladiesMedExpense.usia[1] + ai_th - 1;
                	if( li_usia < 75 ){
	                    for( int i = 1; i <= istr_prop.ladiesMedExpense.peserta; i++ )
	                    {
	                        li_usia = istr_prop.ladiesMedExpense.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
	                      
	                        if(i > 1){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.117, 2 );
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_ladies_med = null;
	                        if( i == 1 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ];
 	                        }else if( i == 2 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 );
 	                        }else if( i == 3 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 2 );
 	                        }else if( i == 4 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 3 );
 	                        }else if( i == 5 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 4 );
 	                        }
 	                        
	                        if( ai_th == 1 ){
	                       	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("832"), new BigDecimal( lsdbs_ladies_med ),
	                       			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                       			 new BigDecimal(istr_prop.cb_id), istr_prop.ladiesMedExpense.tgl[i], istr_prop.ladiesMedExpense.usia[i] ) );
	                       }
	                    }
                	}
                }
                break;
            case 22:
                //ladies medical expense inner limit
                if( istr_prop.rider_baru[ 22 ] > 0 && li_usia < 75 && istr_prop.ladiesMedExpenseInnerLimit.peserta == 0)
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider(params));

                    ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("833"), new BigDecimal( istr_prop.rider_baru[ 22 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }

                //ladies medical expense inner limit peserta tambahan
                if( istr_prop.rider_baru[ 22 ] > 0 && istr_prop.ladiesMedExpenseInnerLimit.peserta > 0 )
                {
                	li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[1] + ai_th - 1;
                	if( li_usia < 75 ){
	                    for( int i = 1; i <= istr_prop.ladiesMedExpenseInnerLimit.peserta; i++ )
	                    {
	                        li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[ i ] + ai_th - 1;
	                        
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
	                        if(i > 1){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.117, 2 );
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_ladies_med_il = null;
	                        if( i == 1 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ];
 	                        }else if( i == 2 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 );
 	                        }else if( i == 3 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 2 );
 	                        }else if( i == 4 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 3 );
 	                        }else if( i == 5 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 4 );
 	                        }
 	                        
	                        if( ai_th == 1 ){
	                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("833"), new BigDecimal( lsdbs_ladies_med_il ),
	                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                          			 new BigDecimal(istr_prop.cb_id), istr_prop.ladiesMedExpenseInnerLimit.tgl[i], istr_prop.ladiesMedExpenseInnerLimit.usia[i] ) );
	                        }
	                    }
                    }
                }
                break;
                
            case 23:
            	//Payor 5/10 TPD/CI/Death
            	if( istr_prop.rider_baru[ 23 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 23 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getPolicyHolderSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorTpdCiDeath(params) );
                    
                    		
                    		
                    ldec_max = 200000;
                    if( istr_prop.kurs_id == "01" ) ldec_max = 2000000000;
                    ldec_temp = FormatNumber.round( ( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate)  * 0.1 ), 2 );
                	if( istr_prop.bisnis_id == 120 ||  istr_prop.bisnis_id == 202){
                	    ldec_temp = FormatNumber.round( ( ( ( ldec_rate / 1000 ) * ( istr_prop.premi * li_kali)) * 0.1 ), 2 );
                	}
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("828"), new BigDecimal( li_jenis ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
                
            case 24:
            	//Waiver 5/10 TPD/CI
            	if( istr_prop.rider_baru[ 24 ] > 0 )
                {
            		li_usia = istr_prop.umur_tt + ai_th - 1;
            		li_jenis = istr_prop.rider_baru[24]	;
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", ai_th );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWaiverTpdCi(params) );
                    
                    ldec_max = 200000;
//            		ldec_temp = Round(((ldec_rate/1000)*(istr_prop.premi *li_kali))*0.1, 2)
                    ldec_temp = FormatNumber.round( ( ( ldec_rate / 1000 )*( istr_prop.premi * li_kali ) ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("827"), new BigDecimal( li_jenis ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }
                break;
                
            case 25:
            	if( istr_prop.rider_baru[25] > 0 && li_usia < istr_prop.usia_schol ){
           		 Map<String, Object> params = new HashMap<String, Object>();
                 params.put( "kursId", istr_prop.kurs_id );
                 params.put( "liUsia", li_usia_pp );
                 ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateScholarship( params ) );
                 
                 Map<String, Object> paramsRate = new HashMap<String, Object>();
                 Integer lijenis = 0;
                 if( istr_prop.usia_schol == 22 ){
                	 lijenis = 1;
                 }else if( istr_prop.usia_schol == 25 ){
                	 lijenis = 2;
                 }
                 paramsRate = new HashMap<String, Object>();
                 paramsRate.put( "lsbsId", "835" );
                 paramsRate.put( "liJenis", lijenis );
                 paramsRate.put( "liUsia", li_usia );
                 paramsRate.put( "kursId", istr_prop.kurs_id );
                 BigDecimal rate_factor = eproposalManager.selectTableFactor( paramsRate );
                 if( rate_factor == null ) { rate_factor = BigDecimal.ZERO;}
                 double rate_factor_double = LazyConverter.toDouble( rate_factor );
//                  153.240.000 * 1,79 /1000 *10% = 27.430 (yg ini yg bener ya)
//                  200.000.000 * 1.79 / 1000 * 10% = 35.800

                 ldec_temp = FormatNumber.round( ( ( ldec_rate / 1000 )* istr_prop.up_schol * rate_factor_double ) * 0.1, 2 );
                 ldec_total += ldec_temp;
                 if( ai_th == 1 ){
                 	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("835"), new BigDecimal( lijenis ),
                 			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                 			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
        	}
            break;
            
            case 27:
            	if( istr_prop.rider_baru[27] > 0  ){            		
            		String genderInsured = cepr01030101Form.getInsuredSexCd().trim();
            		li_jenis=0;
            		if(genderInsured.equals("L")){
            			li_jenis = 1;
            		}
            		else if(genderInsured.equals("P")){
            			li_jenis = 2;
            		}
            		
            		Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "lku_id", istr_prop.kurs_id );
                    params.put( "lsr_jenis", li_jenis );
                    params.put( "li_usia", li_usia);
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateEarlyStageCi99( params ) );
                            		
//                    double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getEsci99ChooseCd())) / 100.0;
                    /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
                    double upEsci99 =  cepr01030103Form.getEsci99RiderAmount().doubleValue();
                    double maxUp;
                    if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) ){
                    	maxUp = 500000.0;
                    }else{
                    	maxUp = 5000000000.0;
                    }
                    double temp = MathUtil.min( upEsci99, maxUp );                                        
                    //ldec_temp = FormatNumber.round(((ldec_rate / 1000) * istr_prop.up) * 0.1, 2);
                    ldec_temp = FormatNumber.round( ( temp / 1000 * ldec_rate ) * 0.1, 2, istr_prop.kurs_id );
                	ldec_total += ldec_temp;
                	
        			 if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("837"), new BigDecimal( li_jenis ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
            }
            break;    
            
            case 28:
                //SMiLe Medical (+)
                if( istr_prop.rider_baru[ 28 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    int riderBaru = istr_prop.rider_baru[ 28 ];
                    
                    params.put( "riderBaru", riderBaru );  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRider( params ) );
                  
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
//                    ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
        			ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
        			ldec_total += ldec_temp;                	               	

        			if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("838"), new BigDecimal( riderBaru ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
        			
        			if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag())){
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
                        ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
             			ldec_total += ldec_temp;
             			
             			if( ai_th == 1 ){
                       	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                       			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                       			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                       }
        			}
        			if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRgFlag())){           				
        				riderBaru = istr_prop.rider_baru[ 28 ]+20;
                    	                       	
                    	params.put( "riderBaru", riderBaru );  
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
        				ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
                        ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
            			ldec_total += ldec_temp;
            			
            			if( ai_th == 1 ){
                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                          			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                          }
        			}
        			if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRbFlag())){           				
        				riderBaru = istr_prop.rider_baru[ 28 ]+40;
                    	                      	
                    	params.put( "riderBaru", riderBaru );  
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
        				ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
                        ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
            			ldec_total += ldec_temp;
            			 
            			if( ai_th == 1 ){
                           	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                           			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                           			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                        }
        			}
        			if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusPkFlag())){           				
        				riderBaru = istr_prop.rider_baru[ 28 ]+60;
                    	
                    	params.put( "riderBaru", riderBaru );  
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
        				ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
                        ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
            			ldec_total += ldec_temp;
            			
            			if( ai_th == 1 ){
                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                          			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                       }
        			} 
                }
               //SMiLe Medical(+) Peserta Tambahan
           	   if( istr_prop.rider_baru[ 28 ] > 0 && istr_prop.medicalPlus.peserta > 0   && li_usia < 75 )
                {
                   for( int i = 1; i <= istr_prop.medicalPlus.peserta; i++ )
                   {
                       li_usia = istr_prop.medicalPlus.usia[ i ] + ai_th - 1;
                       int riderBaru = istr_prop.rider_baru[ 28 ];
                     
                       if(i==1){
                       	riderBaru = riderBaru + 4;
                       }
                       else if(i==2){
                       	riderBaru = riderBaru + 8;
                       }
                       else if(i==3){
                       	riderBaru = riderBaru + 12;
                       }
                       else if(i==4){
                       	riderBaru = riderBaru + 16;
                       }
                       Map<String, Object> params = new HashMap<String, Object>();
                       params.put( "riderBaru", riderBaru );  
                       params.put( "kursId", istr_prop.kurs_id );
                       params.put( "liUsia", li_usia );
                       ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRider( params ) );	                        
                       ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
                       ldec_total += ldec_temp;	    
                       
                       if( ai_th == 1 ){
                         	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("838"), new BigDecimal( riderBaru ),
                         			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                         			 new BigDecimal(istr_prop.cb_id), istr_prop.medicalPlus.tgl[i], istr_prop.medicalPlus.usia[i] ) );
                       }
                       
                       if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag())){           				
                            ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
                            ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
                 			 ldec_total += ldec_temp;
                 			 
                 			if( ai_th == 1 ){
                            	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                            	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                            	new BigDecimal(istr_prop.cb_id),  istr_prop.medicalPlus.tgl[i], istr_prop.medicalPlus.usia[i] ) );
                            }
           				}
                        if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRgFlag())){           				
            				riderBaru = istr_prop.rider_baru[ 28 ]+20;
                        	
                        	if(i==1){
	                        	riderBaru = riderBaru + 4;
	                        }
	                        else if(i==2){
	                        	riderBaru = riderBaru + 8;
	                        }
	                        else if(i==3){
	                        	riderBaru = riderBaru + 12;
	                        }
	                        else if(i==4){
	                        	riderBaru = riderBaru + 16;
	                        }
                        	
                        	params.put( "riderBaru", riderBaru );  
                            params.put( "kursId", istr_prop.kurs_id );
                            params.put( "liUsia", li_usia );
            				ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
            				ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
                 			ldec_total += ldec_temp;
                 			 
                 			if( ai_th == 1 ){
                            	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                            	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                            	new BigDecimal(istr_prop.cb_id),  istr_prop.medicalPlus.tgl[i], istr_prop.medicalPlus.usia[i] ) );
                            }
            			}
                        if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(istr_prop.medicalPlus.medicalPlusRbFlag[i])){        				
            				riderBaru = istr_prop.rider_baru[ 28 ]+40;
                        	                         	
                        	if(i==1){
	                        	riderBaru = riderBaru + 4;
	                        }
	                        else if(i==2){
	                        	riderBaru = riderBaru + 8;
	                        }
	                        else if(i==3){
	                        	riderBaru = riderBaru + 12;
	                        }
	                        else if(i==4){
	                        	riderBaru = riderBaru + 16;
	                        }	                         	
                            params.put( "riderBaru", riderBaru );  
                            params.put( "kursId", istr_prop.kurs_id );
                            params.put( "liUsia", li_usia );
            				ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
            				ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
                			ldec_total += ldec_temp;
                			 
                			if( ai_th == 1 ){
                            	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                            	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                            	new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                            }
            			}                        
                        if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusPkFlag())){           				
            				riderBaru = istr_prop.rider_baru[ 28 ]+60;
                        
                        	if(i==1){
	                        	riderBaru = riderBaru + 4;
	                        }
	                        else if(i==2){
	                        	riderBaru = riderBaru + 8;
	                        }
	                        else if(i==3){
	                        	riderBaru = riderBaru + 12;
	                        }
	                        else if(i==4){
	                        	riderBaru = riderBaru + 16;
	                        }	    
                        	 params.put( "riderBaru", riderBaru );  
                             params.put( "kursId", istr_prop.kurs_id );
                             params.put( "liUsia", li_usia );
            				 ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
            				 ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
                			 ldec_total += ldec_temp;
                			 
                			if( ai_th == 1 ){
                            	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                            	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                            	new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                           }
            			}
                   }
                }
           	   break;
            
            case 112:
                //term rider header
                if( istr_prop.rider_baru[ 12 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTermRider( params ) );
                    ldec_temp = FormatNumber.round( ( ( istr_prop.up_term / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("818"), new BigDecimal( "2" ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            default:
                break;
        }
        return ldec_total;
    }

    
    public double of_get_coi_120_cost( int ai_jenis, int ai_th, List< Mst_proposal > mstProposal )
    {
    	
    	double ldec_total = 0, ldec_temp, ldec_persen_ci = 0;
    	double ldec_pct = 1;
        double ldec_rate, ldec_max = 100000;
        int li_usia, li_tahun_ke, li_kali = 1, li_jenis, li_class, li_temp, li_risk, li_usia_tt, li_usia_pp;
        li_usia = istr_prop.umur_tt + ai_th - 1;
        li_usia_pp = istr_prop.umur_pp + ai_th - 1;

//        if( istr_prop.rider_baru[ 5 ] > 0 || istr_prop.rider_baru[ 6 ] > 0 || istr_prop.rider_baru[ 7 ] > 0 || istr_prop.rider_baru[ 8 ] > 0 || istr_prop.rider_baru[ 9 ] > 0 || istr_prop.rider_baru[ 10 ] > 0 )
//        {
            if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_TRIWULANAN )
            {
                li_kali = 4;
            }
            else if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_SEMESTERAN )
            {
                li_kali = 2;
            }
            else if( istr_prop.cb_id == Hardcode.PAY_MODE_CD_BULANAN )
            {
                li_kali = 12;
            }
//        }
        wf_set_120();
        switch( ai_jenis )
        {
            case 1:
                //pa
            	
                if( ( istr_prop.rider_baru[ 1 ] > 0 ) && ( li_usia < 60 ) )
                {
                    li_class = istr_prop.pa_class;
                    li_risk = 1;
                    li_temp = li_usia;
                	if(istr_prop.bisnis_id == 120  || istr_prop.bisnis_id == 202 || istr_prop.bisnis_id == 141){
                		//khusus 120-cerdas usia tdk naik
                		li_risk = istr_prop.pa_risk;
                		li_temp = istr_prop.umur_tt;
                	}
                    if(istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 141 || istr_prop.bisnis_id == 202 ){
                		if( li_temp <= 20 ) li_class = 2;
            		}
                    if(istr_prop.bisnis_id == 120){
                    	if( li_temp <= 16 ) li_class = 2;
                    }
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    params.put( "paRisk", li_risk );
                    params.put( "liClass", li_class );
                    double maks = 2000000000.0;
                    if( istr_prop.kurs_id.equals( Hardcode.CUR_USD_CD ) ){
                    	 maks = 200000.0;
                    }
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePa( params ) );
                    double maxAmount = MathUtil.min( istr_prop.rider_baru[ 1 ] * istr_prop.up, maks );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("810"), new BigDecimal(li_risk),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            case 2:
                //hcp
                if( ( istr_prop.rider_baru[ 2 ] > 0 ) && ( li_usia < 65 ) )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 2 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );

                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcp( params ) );
                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("811"), new BigDecimal(istr_prop.rider_baru[ 2 ]),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
                
            case 3:
                //tpd
                if( istr_prop.rider_baru[ 3 ] > 0 )
                {
                	li_jenis = istr_prop.bisnis_id == 120 || istr_prop.bisnis_id == 202 ? 7 : istr_prop.rider_baru[3];
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    params.put( "li_jenis", li_jenis );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTpd_120( params ) );
                    // maks 2 milyar
                    if( "01".equals( istr_prop.kurs_id ) ){
                    	ldec_max = 2000000000;
                    }else if( "02".equals( istr_prop.kurs_id ) ){
                    	ldec_max = 200000;
                    }
                    double maxAmount = MathUtil.min( istr_prop.up, ldec_max );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("812"), new BigDecimal(li_jenis),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
            case 4:
                //ci
                if( istr_prop.rider_baru[ 4 ] > 0 )
                {
                	li_tahun_ke = li_usia;
                	if(istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 141){
                		li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
            		}
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_tahun_ke );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateCi( params ) );
                    
//                    ldec_temp = ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali ) * 2;
//                    ldec_max = 500000000;
//                    if(istr_prop.kurs_id.equals("02")){
//                    	ldec_max = 50000;
//                    }
//                    double minAmount = MathUtil.min( ldec_temp, ldec_max );
//                    ldec_temp = FormatNumber.round( ( ( minAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
//                    if(istr_prop.bisnis_id == 120 || istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 128 || istr_prop.bisnis_id == 129 || istr_prop.bisnis_id == 202){
//                    	if( istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 128 || istr_prop.bisnis_id == 129  ){
//                    		ldec_persen_ci = 1;
//                    		if( istr_prop.bisnis_id == 129 && cepr01030102Form.getSmilePensionPackageCd() != null && cepr01030102Form.getSmilePensionPackageCd() > 1 ){
//                    			ldec_persen_ci = 0.5;
//                    		}
//                    	}else{
//                    		ldec_persen_ci = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getCiChooseCd())) / 100.0;
//                    	}
//                    	ldec_max = 2000000000;
//                    	 if(istr_prop.kurs_id.equals("02")){
//                    		 ldec_max = 200000;
//                    	 }
//                    	 minAmount = MathUtil.min(istr_prop.up * ldec_persen_ci, ldec_max);
                    	/**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
                        double upCi = cepr01030103Form.getCiRiderAmount().doubleValue();
                        if( istr_prop.kurs_id.equals("02") ){
                        	ldec_max = 500000.0;
                        }else{
                        	ldec_max = 5000000000.0;
                        }
                    	 double minAmount = MathUtil.min(upCi, ldec_max);
                    	 ldec_temp = FormatNumber.round(((minAmount / 1000) * ldec_rate) * 0.1, 2);
//                    }
                    
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("813"), new BigDecimal("8"),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                  }
                }
                break;
                //
            case 5:
                //wp 60tpd
                if( istr_prop.rider_baru[ 5 ] > 0 )
                {
                	li_jenis = istr_prop.rider_baru[ 5 ];
                	if( ( istr_prop.bisnis_id == 120 && istr_prop.bisnis_no != 10 && istr_prop.bisnis_no != 11 && istr_prop.bisnis_no != 12 && istr_prop.bisnis_no != 22 && istr_prop.bisnis_no != 23 && istr_prop.bisnis_no != 24 )|| istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 128 || istr_prop.bisnis_id == 129 ){
                		if( cepr01030102Form.getTermOfPayment().intValue() == 5 ){//5
                			li_jenis = 14;
                		}else if( cepr01030102Form.getTermOfPayment().intValue() == 10 ){//10
                			li_jenis = 15;
                		}
                	}
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "li_jenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "ai_th", ai_th );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp60Tpd_120( params ) );
                    double factor = istr_prop.pct_premi / 100;
                    if( istr_prop.bisnis_id == 129 && cepr01030102Form.getSmilePensionPackageCd() != null && cepr01030102Form.getSmilePensionPackageCd() > 1 ){
                    	factor = 1;
                    }
                    // *** Rider Payor dan Waiver Simas Power Link(120) mengcover Premi Pokok + Top Up : Desvinna@9/10/2013 ***		
                    //ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * factor ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("814"), new BigDecimal(li_jenis),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            case 6:
                //pb 25 tpd
                if( istr_prop.rider_baru[ 6 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 6 ];
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
//                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Tpd( params ) );                    
                    //ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    // *** Rider Payor dan Waiver Simas Power Link(120) mengcover Premi Pokok + Top Up : Desvinna@9/10/2013 ***		
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal(li_jenis),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
                
            case 7:
                //wp 60ci
                if( istr_prop.rider_baru[ 7 ] > 0 )
                {
//                	li_jenis = istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 129 || istr_prop.bisnis_id == 128 ? 4 : istr_prop.rider_baru[ 7 ];
                	li_jenis = istr_prop.rider_baru[ 7 ];
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "ai_th", ai_th );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWp60Ci_120( params ) );
                    double factor = istr_prop.pct_premi / 100;
                    if( istr_prop.bisnis_id == 129 && cepr01030102Form.getSmilePensionPackageCd() != null && cepr01030102Form.getSmilePensionPackageCd() > 1 ){
                    	factor = 1;
                    }
                     // *** Rider Payor dan Waiver Simas Power Link(120) mengcover Premi Pokok + Top Up : Desvinna@9/10/2013 ***
                    //ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * factor ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                     ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("816"), new BigDecimal(li_jenis),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
            case 8:
                //pb 25 ci/death
                if( istr_prop.rider_baru[ 8 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 8 ];
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25CiDeath( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("817"), new BigDecimal(li_jenis),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
                //pb 25 ci
                //--
            case 9:
                if( istr_prop.rider_baru[ 9 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 9 ];
                    if( istr_prop.bisnis_id == Hardcode.PRODUK_CERDAS ){
                    	if( istr_prop.bisnis_no == 10 || istr_prop.bisnis_no == 22){// simpol
                    		li_jenis = 2;
                    	}else if( istr_prop.bisnis_no == 11 || istr_prop.bisnis_no == 23 ){
                    		li_jenis = 3;
                    	}
                    }
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePb25Ci( params ) );
                    // *** Rider Payor dan Waiver Simas Power Link(120) mengcover Premi Pokok + Top Up : Desvinna@9/10/2013 ***		
                    //ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("817"), new BigDecimal(li_jenis),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
                //payor spouse
            case 10:
                if( istr_prop.rider_baru[ 10 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = 13;

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorSpouse( params ) );

                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("815"), new BigDecimal(li_jenis),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }
                break;
                
            case 11:
                //hcp family
                if( istr_prop.rider_baru[ 11 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 11 ] + 140 );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamily( params ) );

                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("819"), new BigDecimal( istr_prop.rider_baru[ 11 ] + 140 ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
                }
                //hcp family peserta tambahan
                if( istr_prop.rider_baru[ 11 ] > 0 && istr_prop.hcpf.peserta > 0  )
                {
                    for( int i = 1; i <= istr_prop.hcpf.peserta; i++ )
                    {
                    	li_usia_tt = istr_prop.hcpf.usia[ i ] + ai_th - 1;
                    	Integer lsdbs_hcp_fam = null;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 11 ] + 140 );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia_tt );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamilyPesertaTambahan( params ) );
                    	ldec_temp = 0;
                    	  if (!( i > 1 && li_usia_tt > 24 ) ){
                    		  ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                    	}
                        ldec_total += ldec_temp;
                        if( i == 1 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + 20;
                        }else if( i == 2 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_hcp_fam = istr_prop.rider_baru[ 11 ] + 140 + ( 20 * 5 );
                        }
                        if( ai_th == 1 ){
                        	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("819"), new BigDecimal( lsdbs_hcp_fam ),
                        			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        			 new BigDecimal(istr_prop.cb_id),  istr_prop.hcpf.tgl[i], istr_prop.hcpf.usia[i] ) );
                        }
                    }
                }
                break;
            case 12:
                //term rider
            	li_temp = 10;
            	if(istr_prop.bisnis_id == 120 || istr_prop.bisnis_id == 202)	li_temp = 70;
                if( istr_prop.rider_baru[ 12 ] > 0 && ai_th <= li_temp )
                {
                	double up_term = istr_prop.up_term;
                	if( istr_prop.bisnis_id == 128 ){
                		up_term = istr_prop.up;
                	}
                	li_jenis = istr_prop.rider_baru[ 12 ];
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTermRider_120( params ) );
                    ldec_temp = FormatNumber.round( ( ( up_term / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("818"), new BigDecimal( li_jenis ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            case 13:
                //eka sehat
                if( istr_prop.rider_baru[ 13 ] > 0 && li_usia < 75 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 13 ] );  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) );
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
//                    ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
        			ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("823"), new BigDecimal( istr_prop.rider_baru[ 13 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }

                //eka sehat peserta tambahan
                if( istr_prop.rider_baru[ 13 ] > 0 && istr_prop.eka_sehat.peserta > 0   && li_usia < 75 )
                {
                    for( int i = 1; i <= istr_prop.eka_sehat.peserta; i++ )
                    {
                        li_usia = istr_prop.eka_sehat.usia[ i ] + ai_th - 1;
                        
                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 13 ]  );    
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatRider( params ) );
//                        ldec_temp = FormatNumber.round( (ldec_rate * 0.975 * ldec_pct) * 0.1, 2 );
                        ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_e_sehat = null;
                        if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + 15;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 13 ] + ( 15 * 5 );
                        }
                        if( ai_th == 1 ){
                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("823"), new BigDecimal( lsdbs_e_sehat ),
                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                          			 new BigDecimal(istr_prop.cb_id), istr_prop.eka_sehat.tgl[i], istr_prop.eka_sehat.usia[i] ) );
                        }
                    }
                }
                break;
                
            case 15:
            	//eka sehat inner limit
            	if( istr_prop.rider_baru[ 15 ] > 0 && li_usia < 75 )
            	{
            		Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 15 ] );  
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ) );
        			if( istr_prop.cb_id == 1 ){  //triwulan
        				ldec_pct = 0.35;
        			}else if( istr_prop.cb_id == 2 ){ //semesteran
        				ldec_pct = 0.65;
        			}else if( istr_prop.cb_id == 6 ){ //bulanan
        				ldec_pct = 0.12;
        			}
                    ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                      	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("825"), new BigDecimal( istr_prop.rider_baru[ 15 ] ),
                      			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                      			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                      }
	            }
            	if( istr_prop.rider_baru[ 15 ] > 0 && istr_prop.ekaSehatInnerLimit.peserta > 0 && li_usia < 75 )
            	{
            		for ( int i = 1; i <= istr_prop.ekaSehatInnerLimit.peserta; i++)
            		{
            			li_usia = istr_prop.ekaSehatInnerLimit.usia[ i ] + ai_th - 1;
            			
            			Map<String, Object> params = new HashMap<String, Object>();
            			params.put( "riderBaru", istr_prop.rider_baru[ 15 ] );
            			params.put( "kursId", istr_prop.kurs_id );
            			params.put( "liUsia", li_usia );
            			ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ));
            			ldec_temp = FormatNumber.round((ldec_rate * 0.117), 2);
            			ldec_total += ldec_temp;
            			
            			Integer lsdbs_e_sehat = null;
            			if( i == 1 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + 15;
                        }else if( i == 2 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_e_sehat = istr_prop.rider_baru[ 15 ] + ( 15 * 5 );
                        }
            			if( ai_th == 1 ){
                         	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("825"), new BigDecimal( lsdbs_e_sehat ),
                         			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                         			 new BigDecimal(istr_prop.cb_id), istr_prop.ekaSehatInnerLimit.tgl[i], istr_prop.ekaSehatInnerLimit.usia[i] ) );
                        }
            		}
            	}
	            break;
          
            case 16:
                //hcp Provider
                if( istr_prop.rider_baru[ 16 ] > 0 && li_usia < 65 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );   
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProvider( params ) );

                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("826"), new BigDecimal( istr_prop.rider_baru[ 16 ] ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }

                //hcp provider peserta tambahan
                if( istr_prop.rider_baru[ 16 ] > 0 && istr_prop.hcpPro.peserta > 0 && li_usia < 65 )
                {
                    for( int i = 1; i <= istr_prop.hcpPro.peserta; i++ )
                    {
                        li_usia = istr_prop.hcpPro.usia[ i ] + ai_th - 1;

                        Map<String, Object> params = new HashMap<String, Object>();
                        params.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProviderPesertaTambahan( params ) );
                        ldec_temp = 0;
                        if (!( i > 1 && li_usia > 24 ) ){
                        	  ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
                        }
                        ldec_total += ldec_temp;
                        
                        Integer lsdbs_hcp_pro= null;
                        if( i == 1 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + 12;
                        }else if( i == 2 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 2 );
                        }else if( i == 3 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 3 );
                        }else if( i == 4 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 4 );
                        }else if( i == 5 ) { 
                        	lsdbs_hcp_pro = istr_prop.rider_baru[ 16 ] + ( 12 * 5 );
                        }
                        
                        if( ai_th == 1 ){
                        	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("826"), new BigDecimal( lsdbs_hcp_pro ),
                        			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        			 new BigDecimal(istr_prop.cb_id), istr_prop.hcpPro.tgl[i], istr_prop.hcpPro.usia[i] ) );
                       }
                    }
                }
                break;
                
            case 17:
            	//Waiver TPD/CI/Death
            	if( istr_prop.rider_baru[ 17 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    params.put( "liJenis", istr_prop.rider_baru[ 17 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", 0 );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWaiverTpdCi(params) );
                    
                    ldec_max = 200000;
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * 1 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("827"), new BigDecimal( istr_prop.rider_baru[ 17 ] ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
                
            case 18:
            	//Payor TPD/CI/Death
            	if( istr_prop.rider_baru[ 18 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 18 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getPolicyHolderSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorTpdCiDeath(params) );
                    
                    ldec_max = 200000;
//                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate ), 2 );
                    ldec_temp = FormatNumber.round( ( ( ( istr_prop.premi * 1 ) * li_kali / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("828"), new BigDecimal( li_jenis ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
            
            case 19:
                //ladies insurance
                if( istr_prop.rider_baru[ 19 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "liJenis", istr_prop.rider_baru[ 19 ] );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", 0 );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateLadiesIns(params) );
                    
                    ldec_max = 200000;
                    if( istr_prop.kurs_id.equals("01") ){ ldec_max = 2000000000;}
                    double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getLadiesInsChooseCd())) / 100.0;
                    double maxAmount = MathUtil.min( istr_prop.up * persentase, ldec_max );
                    ldec_temp = FormatNumber.round( ( ( maxAmount / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("830"), new BigDecimal( istr_prop.rider_baru[ 19 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }
                break;
                
            case 20:
                //hcp ladies
            	 if( istr_prop.rider_baru[ 20 ] > 0 && istr_prop.hcpLad.peserta == 0)
                 {
                 	if( li_usia < 66 && istr_prop.hcpLad.peserta == 0 ){
 	                    Map<String, Object> params = new HashMap<String, Object>();
 	                    params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
 	                    params.put( "kursId", istr_prop.kurs_id );
 	                    params.put( "liUsia", li_usia );
 	                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadies( params ) );
 	
 	                    ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
 	                    ldec_total += ldec_temp;
 	                   if( ai_th == 1 ){
 	                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("831"), new BigDecimal( istr_prop.rider_baru[ 20 ] ),
 	                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
 	                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
 	                    }
                 	}
                 }

                 //hcp ladies peserta tambahan
                 if( istr_prop.rider_baru[ 20 ] > 0 && istr_prop.hcpLad.peserta > 0 )
                 {
                 	li_usia = istr_prop.hcpLad.usia[ 1 ] + ai_th - 1;
                 	if( li_usia < 66 && istr_prop.hcpLad.peserta > 0 )
                 	{
 	                    for( int i = 1; i <= istr_prop.hcpLad.peserta; i++ )
 	                    {
 	                        li_usia = istr_prop.hcpLad.usia[ i ] + ai_th - 1;
 	                       
 	                        Map<String, Object> params = new HashMap<String, Object>();
 	                        params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
 	                        params.put( "kursId", istr_prop.kurs_id );
 	                        params.put( "liUsia", li_usia );
 	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadiesPesertaTambahan( params ) );
 	                        ldec_temp = FormatNumber.round( ldec_rate * 0.1, 2 );
 	                        if(i >= 2){
 	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.09, 2 );
 	                        	if(i>=3 && li_usia>25) ldec_temp = 0;
 	                        }
 	                        ldec_total += ldec_temp;
 	                        
 	                        Integer lsdbs_hcp_lad = null;
 	                        if( i == 1 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ];
	                        }else if( i == 2 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 );
	                        }else if( i == 3 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 2 );
	                        }else if( i == 4 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 3 );
	                        }else if( i == 5 ) { 
	                        	lsdbs_hcp_lad = istr_prop.rider_baru[ 20 ] + ( 24 * 4 );
	                        }
 	                        if( ai_th == 1 ){
 	 	                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("831"), new BigDecimal( lsdbs_hcp_lad ),
 	 	                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
 	 	                    			 new BigDecimal(istr_prop.cb_id), istr_prop.hcpLad.tgl[i], istr_prop.hcpLad.usia[i] ) );
 	 	                    }
 	                    }
                 	}
                 }
                 break;                 
            case 21:
                //ladies medical expense
                if( istr_prop.rider_baru[ 21 ] > 0  && istr_prop.ladiesMedExpense.peserta == 0 && li_usia < 75 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider(params));

                    ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("832"), new BigDecimal( istr_prop.rider_baru[ 21 ] ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }

                //ladies medical expense peserta tambahan
                
              
                if( istr_prop.rider_baru[ 21 ] > 0 && istr_prop.ladiesMedExpense.peserta > 0  )
                {
                	li_usia = istr_prop.ladiesMedExpense.usia[1] + ai_th - 1;
                	if( li_usia < 75 ){
	                    for( int i = 1; i <= istr_prop.ladiesMedExpense.peserta; i++ )
	                    {
	                        li_usia = istr_prop.ladiesMedExpense.usia[ i ] + ai_th - 1;
	
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
	                      
	                        if(i > 1){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.117, 2 );
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_ladies_med = null;
	                        if( i == 1 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ];
 	                        }else if( i == 2 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 );
 	                        }else if( i == 3 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 2 );
 	                        }else if( i == 4 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 3 );
 	                        }else if( i == 5 ) { 
 	                        	lsdbs_ladies_med = istr_prop.rider_baru[ 21 ] + ( 7 * 4 );
 	                        }
 	                        
	                        if( ai_th == 1 ){
	                       	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("832"), new BigDecimal( lsdbs_ladies_med ),
	                       			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                       			 new BigDecimal(istr_prop.cb_id), istr_prop.ladiesMedExpense.tgl[i], istr_prop.ladiesMedExpense.usia[i] ) );
	                       }
	                    }
                	}
                }
                break;
            case 22:
                //ladies medical expense inner limit
                if( istr_prop.rider_baru[ 22 ] > 0 && li_usia < 75 && istr_prop.ladiesMedExpenseInnerLimit.peserta == 0)
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider(params));

                    ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                   	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("833"), new BigDecimal( istr_prop.rider_baru[ 22 ] ),
                   			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                   			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                   }
                }

                //ladies medical expense inner limit peserta tambahan
                if( istr_prop.rider_baru[ 22 ] > 0 && istr_prop.ladiesMedExpenseInnerLimit.peserta > 0 )
                {
                	li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[1] + ai_th - 1;
                	if( li_usia < 75 ){
	                    for( int i = 1; i <= istr_prop.ladiesMedExpenseInnerLimit.peserta; i++ )
	                    {
	                        li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[ i ] + ai_th - 1;
	                        
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider( params ) );
	                        ldec_temp = FormatNumber.round( ldec_rate * 0.12, 2 );
	                        if(i > 1){
	                        	ldec_temp = FormatNumber.round( ldec_rate * 0.117, 2 );
	                        }
	                        ldec_total += ldec_temp;
	                        
	                        Integer lsdbs_ladies_med_il = null;
	                        if( i == 1 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ];
 	                        }else if( i == 2 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 );
 	                        }else if( i == 3 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 2 );
 	                        }else if( i == 4 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 3 );
 	                        }else if( i == 5 ) { 
 	                        	lsdbs_ladies_med_il = istr_prop.rider_baru[ 22 ] + ( 7 * 4 );
 	                        }
 	                        
	                        if( ai_th == 1 ){
	                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("833"), new BigDecimal( lsdbs_ladies_med_il ),
	                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                          			 new BigDecimal(istr_prop.cb_id), istr_prop.ladiesMedExpenseInnerLimit.tgl[i], istr_prop.ladiesMedExpenseInnerLimit.usia[i] ) );
	                        }
	                    }
                    }
                }
                break;                
            case 23:
            	//Payor 5/10 TPD/CI/Death
            	if( istr_prop.rider_baru[ 23 ] > 0 )
                {
                    li_tahun_ke = istr_prop.umur_pp + ai_th - 1;
                    li_jenis = istr_prop.rider_baru[ 23 ];

                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getPolicyHolderSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", li_tahun_ke );
                    params.put( "liUsia", ai_th );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRatePayorTpdCiDeath(params) );                                        		
                    		
                    ldec_max = 200000;
                    if( istr_prop.kurs_id == "01" ) ldec_max = 2000000000;
                    ldec_temp = FormatNumber.round( ( ( ( ( istr_prop.premi * istr_prop.pct_premi / 100 ) * li_kali / 1000 ) * ldec_rate)  * 0.1 ), 2 );
                	if( istr_prop.bisnis_id == 120 ||  istr_prop.bisnis_id == 202){
                	    ldec_temp = FormatNumber.round( ( ( ( ldec_rate / 1000 ) * ( istr_prop.premi * li_kali)) * 0.1 ), 2 );
                	}
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("828"), new BigDecimal( li_jenis ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;                
            case 24:
            	//Waiver 5/10 TPD/CI
            	if( istr_prop.rider_baru[ 24 ] > 0 )
                {
            		li_usia = istr_prop.umur_tt + ai_th - 1;
            		li_jenis = istr_prop.rider_baru[24]	;
                    Map<String, Object> params = new HashMap<String, Object>();
                    String li_sex;  // -- IGA - PROJECT NEW COR
                    li_sex = cepr01030101Form.getInsuredSexCd();
                    if(li_sex.equals("L")){
                    	li_sex = "1".trim();
                    }else if(li_sex.equals("P")){
                    	li_sex = "0".trim();
                    }else{
                    	li_sex = "9".trim();
                    }
                    params.put("liSex", li_sex); //-- DONE	
                    params.put( "liJenis", li_jenis );
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liTahunKe", ai_th );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateWaiverTpdCi(params) );
                    
                    ldec_max = 200000;
//            		ldec_temp = Round(((ldec_rate/1000)*(istr_prop.premi *li_kali))*0.1, 2)
                    ldec_temp = FormatNumber.round( ( ( ldec_rate / 1000 )*( istr_prop.premi * li_kali ) ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("827"), new BigDecimal( li_jenis ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
                }
                break;                
            case 25:
            	if( istr_prop.rider_baru[25] > 0 && li_usia < istr_prop.usia_schol ){
           		 Map<String, Object> params = new HashMap<String, Object>();
                 params.put( "kursId", istr_prop.kurs_id );
                 params.put( "liUsia", li_usia_pp );
                 ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateScholarship( params ) );
                 
                 Map<String, Object> paramsRate = new HashMap<String, Object>();
                 Integer lijenis = 0;
                 if( istr_prop.usia_schol == 22 ){
                	 lijenis = 1;
                 }else if( istr_prop.usia_schol == 25 ){
                	 lijenis = 2;
                 }
                 paramsRate = new HashMap<String, Object>();
                 paramsRate.put( "lsbsId", "835" );
                 paramsRate.put( "liJenis", lijenis );
                 paramsRate.put( "liUsia", li_usia );
                 paramsRate.put( "kursId", istr_prop.kurs_id );
                 BigDecimal rate_factor = eproposalManager.selectTableFactor( paramsRate );
                 if( rate_factor == null ) { rate_factor = BigDecimal.ZERO;}
                 double rate_factor_double = LazyConverter.toDouble( rate_factor );
//                  153.240.000 * 1,79 /1000 *10% = 27.430 (yg ini yg bener ya)
//                  200.000.000 * 1.79 / 1000 * 10% = 35.800

                 ldec_temp = FormatNumber.round( ( ( ldec_rate / 1000 )* istr_prop.up_schol * rate_factor_double ) * 0.1, 2 );
                 ldec_total += ldec_temp;
                 if( ai_th == 1 ){
                 	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("835"), new BigDecimal( lijenis ),
                 			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                 			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                 }
        	}
            break;
            
            case 27:
            	if( istr_prop.rider_baru[27] > 0  ){            		
            		String genderInsured = cepr01030101Form.getInsuredSexCd().trim();
            		li_jenis=0;
            		if(genderInsured.equals("L")){
            			li_jenis = 1;
            		}
            		else if(genderInsured.equals("P")){
            			li_jenis = 2;
            		}
            		
            		Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "lku_id", istr_prop.kurs_id );
                    params.put( "lsr_jenis", li_jenis );
                    params.put( "li_usia", li_usia);
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateEarlyStageCi99( params ) );

//                    double persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getEsci99ChooseCd())) / 100.0;
                    /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
                    double upEsci99 =  cepr01030103Form.getEsci99RiderAmount().doubleValue();
                    double maxUp;
                    if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) ){
                    	maxUp = 500000.0;
                    }else{
                    	maxUp = 5000000000.0;
                    }
                    double temp = MathUtil.min( upEsci99, maxUp );
                    ldec_temp = FormatNumber.round(((ldec_rate / 1000) * temp) * 0.1, 2);
                	ldec_total += ldec_temp;
                	
        			 if( ai_th == 1 ){
                     	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("837"), new BigDecimal( li_jenis ),
                     			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                     			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                     }
            }
            break;
            
            case 28:
           	 //SMiLe Medical (+)
           	if( istr_prop.rider_baru[28] > 0 ){ 			
                        Map<String, Object> params = new HashMap<String, Object>();
                        int riderBaru = istr_prop.rider_baru[ 28 ];
                      
                        params.put( "riderBaru", riderBaru );  
                        params.put( "kursId", istr_prop.kurs_id );
                        params.put( "liUsia", li_usia );
                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRider( params ) );
                    
            			if( istr_prop.cb_id == 1 ){  //triwulan
            				ldec_pct = 0.35;
            			}else if( istr_prop.cb_id == 2 ){ //semesteran
            				ldec_pct = 0.65;
            			}else if( istr_prop.cb_id == 6 ){ //bulanan
            				ldec_pct = 0.12;
            			}
//                        ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
            			ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
            			ldec_total += ldec_temp;
            			
            			if( ai_th == 1 ){
                        	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("838"), new BigDecimal( riderBaru ),
                        	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                        	new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                        }
            			
            			if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag())){           				
                            ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
                            ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
                 			 ldec_total += ldec_temp;
                 			 
                 			if( ai_th == 1 ){
                            	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                            	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                            	new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                            }
           			}
            			if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRgFlag())){           				
            				riderBaru = istr_prop.rider_baru[ 28 ]+20;
                        	                      	
                        	 params.put( "riderBaru", riderBaru );  
                            params.put( "kursId", istr_prop.kurs_id );
                            params.put( "liUsia", li_usia );
            				 ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
                            ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
                			 ldec_total += ldec_temp;
                			 
                			if( ai_th == 1 ){
                            	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                            	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                            	new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                            }
            			}
            			if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRbFlag())){           				
            				riderBaru = istr_prop.rider_baru[ 28 ]+40;
                        	                     	
                        	 params.put( "riderBaru", riderBaru );  
                            params.put( "kursId", istr_prop.kurs_id );
                            params.put( "liUsia", li_usia );
            				 ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
                            ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
                			 ldec_total += ldec_temp;
                			 
                			if( ai_th == 1 ){
                            	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                            	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                            	new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                            }
            			}
            			if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusPkFlag())){           				
            				riderBaru = istr_prop.rider_baru[ 28 ]+60;
                        	
                        	 params.put( "riderBaru", riderBaru );  
                            params.put( "kursId", istr_prop.kurs_id );
                            params.put( "liUsia", li_usia );
            				 ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
                            ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
                			 ldec_total += ldec_temp;
                			 
                			if( ai_th == 1 ){
                            	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
                            	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                            	new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                           }
            			}
                    }
                    //SMiLe Medical(+) Peserta Tambahan
	            	 if( istr_prop.rider_baru[ 28 ] > 0 && istr_prop.medicalPlus.peserta > 0   && li_usia < 75 )
	                 {
	                    for( int i = 1; i <= istr_prop.medicalPlus.peserta; i++ )
	                    {
	                        li_usia = istr_prop.medicalPlus.usia[ i ] + ai_th - 1;
	                        int riderBaru = istr_prop.rider_baru[ 28 ];
	                        
	                        if(i==1){
	                        	riderBaru = riderBaru + 4;
	                        }
	                        else if(i==2){
	                        	riderBaru = riderBaru + 8;
	                        }
	                        else if(i==3){
	                        	riderBaru = riderBaru + 12;
	                        }
	                        else if(i==4){
	                        	riderBaru = riderBaru + 16;
	                        }
	                        Map<String, Object> params = new HashMap<String, Object>();
	                        params.put( "riderBaru", riderBaru );  
	                        params.put( "kursId", istr_prop.kurs_id );
	                        params.put( "liUsia", li_usia );
	                        ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRider( params ) );	                        
	                        ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
	                        ldec_total += ldec_temp;	    
	                        
	                        if( ai_th == 1 ){
	                          	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("838"), new BigDecimal( riderBaru ),
	                          			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                          			 new BigDecimal(istr_prop.cb_id), istr_prop.medicalPlus.tgl[i], istr_prop.medicalPlus.usia[i] ) );
	                        }
	                        
	                        if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag())){           				
	                             ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
	                             ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
	                  			 ldec_total += ldec_temp;
	                  			 
	                  			if( ai_th == 1 ){
	                             	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
	                             	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                             	new BigDecimal(istr_prop.cb_id),  istr_prop.medicalPlus.tgl[i], istr_prop.medicalPlus.usia[i] ) );
	                             }
	            			}
	                        if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRgFlag())){           				
	             				riderBaru = istr_prop.rider_baru[ 28 ]+20;
	                         	
	                         	if(i==1){
		                        	riderBaru = riderBaru + 4;
		                        }
		                        else if(i==2){
		                        	riderBaru = riderBaru + 8;
		                        }
		                        else if(i==3){
		                        	riderBaru = riderBaru + 12;
		                        }
		                        else if(i==4){
		                        	riderBaru = riderBaru + 16;
		                        }
	                         	
	                         	params.put( "riderBaru", riderBaru );  
	                            params.put( "kursId", istr_prop.kurs_id );
	                            params.put( "liUsia", li_usia );
	             				ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
	             				ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
	                  			ldec_total += ldec_temp;
	                  			 
	                  			if( ai_th == 1 ){
	                             	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
	                             	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                             	new BigDecimal(istr_prop.cb_id),  istr_prop.medicalPlus.tgl[i], istr_prop.medicalPlus.usia[i] ) );
	                             }
	             			}
	                        if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(istr_prop.medicalPlus.medicalPlusRbFlag[i])){        				
	             				riderBaru = istr_prop.rider_baru[ 28 ]+40;
	                         	                         	
	                         	if(i==1){
		                        	riderBaru = riderBaru + 4;
		                        }
		                        else if(i==2){
		                        	riderBaru = riderBaru + 8;
		                        }
		                        else if(i==3){
		                        	riderBaru = riderBaru + 12;
		                        }
		                        else if(i==4){
		                        	riderBaru = riderBaru + 16;
		                        }	                         	
	                         	params.put( "riderBaru", riderBaru );  
	                            params.put( "kursId", istr_prop.kurs_id );
	                            params.put( "liUsia", li_usia );
	             				ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
	             				ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
	                 			ldec_total += ldec_temp;
	                 			 
	                 			if( ai_th == 1 ){
	                             	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
	                             	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                             	new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
	                             }
	             			}                        
	                        if(CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals(cepr01030103Form.getMedicalPlusPkFlag())){           				
	             				riderBaru = istr_prop.rider_baru[ 28 ]+60;
	                         	
	                         	if(i==1){
		                        	riderBaru = riderBaru + 4;
		                        }
		                        else if(i==2){
		                        	riderBaru = riderBaru + 8;
		                        }
		                        else if(i==3){
		                        	riderBaru = riderBaru + 12;
		                        }
		                        else if(i==4){
		                        	riderBaru = riderBaru + 16;
		                        }	    
	                         	 params.put( "riderBaru", riderBaru );  
	                             params.put( "kursId", istr_prop.kurs_id );
	                             params.put( "liUsia", li_usia );
	             				 ldec_rate = LazyConverter.toDouble( eproposalManager.selectMedicalPlusRjRider( params ) );
	             				 ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
	                 			 ldec_total += ldec_temp;
	                 			 
	                 			if( ai_th == 1 ){
	                             	mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("839"), new BigDecimal( riderBaru ),
	                             	BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
	                             	new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
	                            }
	             			}
	                    }
	                 }
	            break;
            case 112:
                //term rider header
                if( istr_prop.rider_baru[ 12 ] > 0 )
                {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put( "kursId", istr_prop.kurs_id );
                    params.put( "liUsia", li_usia );
                    ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTermRider( params ) );
                    ldec_temp = FormatNumber.round( ( ( istr_prop.up_term / 1000 ) * ldec_rate ) * 0.1, 2 );
                    ldec_total += ldec_temp;
                    if( ai_th == 1 ){
                    	 mstProposal.add( new Mst_proposal( cepr01030102Form.getCurrencyCd(), new BigDecimal("818"), new BigDecimal( "2" ),
                    			 BigDecimal.ZERO, new BigDecimal(ldec_temp * 10), new BigDecimal(ldec_rate), new BigDecimal(cepr01030102Form.getTermOfContract()), 
                    			 new BigDecimal(istr_prop.cb_id),  cepr01030101Form.getInsuredBirthDay(), cepr01030101Form.getInsuredAge() ) );
                    }
                }
                break;
            default:
                break;
        }
        
        if(cepr01031001Form != null) {
            cepr01031001Form.setMstProposal( mstProposal );
        }
        
        return ldec_total;
    }

    
    public List<Map<String, Object>> getMonthlyPremiumList( Cepr01030102Form cepr01030102Form ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getMonthlyPremiumList" );
        double ldec_coi;

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        Map<String, Object> params;

        ldec_coi = of_get_coi_basic( 1 );
        params = new HashMap<String, Object>();
        params.put( "assuranceBenefit", "Dasar" );
        String titlePackage = "";
        if( cepr01030102Form.getSmileLadiesPackageCd() != null ){
        	if( cepr01030102Form.getSmileLadiesPackageCd().equals(2) ) titlePackage = "(SMiLe Ladies Diamond)";
        	else if( cepr01030102Form.getSmileLadiesPackageCd().equals(3) ) titlePackage = "(SMiLe Ladies Ruby)";
        	else if( cepr01030102Form.getSmileLadiesPackageCd().equals(4) ) titlePackage = "(SMiLe Ladies Pearl)";
        	else if( cepr01030102Form.getSmileLadiesPackageCd().equals(5) ) titlePackage = "(SMiLe Ladies Fantastic)";
        	else if( cepr01030102Form.getSmileLadiesPackageCd().equals(6) ) titlePackage = "(SMiLe Ladies Excellent)";
        }
        
        if( cepr01030102Form.getEducationPackageCd() != null ){
        	if( cepr01030102Form.getEducationPackageCd().equals(2) ) titlePackage = "(Paket A)";
        	else if( cepr01030102Form.getEducationPackageCd().equals(3) ) titlePackage = "(Paket B)";
        	else if( cepr01030102Form.getEducationPackageCd().equals(4) ) titlePackage = "(Paket C)";
        }
        
        if( cepr01030102Form.getProtectorPackageCd() != null ){
        	if( cepr01030102Form.getProtectorPackageCd().equals(2) ) titlePackage = "(Protector GOLD)";
        	else if( cepr01030102Form.getProtectorPackageCd().equals(3) ) titlePackage = "(Protector TITANIUM)";
        	else if( cepr01030102Form.getProtectorPackageCd().equals(4) ) titlePackage = "(Protector PLATINUM)";
        }
        
        if( cepr01030102Form.getSmilePensionPackageCd() != null && istr_prop.bisnis_id != 159 ){
        	if( cepr01030102Form.getSmilePensionPackageCd().equals(2)) titlePackage = "(PAKET A)";
        	else if( cepr01030102Form.getSmilePensionPackageCd().equals(3) ) titlePackage = "(PAKET B)";
        	else if( cepr01030102Form.getSmilePensionPackageCd().equals(4) ) titlePackage = "(PAKET C)";
        	else if( cepr01030102Form.getSmilePensionPackageCd().equals(5) ) titlePackage = "(PAKET D)";
        	else if( cepr01030102Form.getSmilePensionPackageCd().equals(6) ) titlePackage = "(PAKET E)";
        }
        if(istr_prop.bisnis_id == 159){
        	 params.put( "productName", "SMiLe LINK" + titlePackage );
        }else if(istr_prop.bisnis_id == 116 || istr_prop.bisnis_id == 118){ //Prod 118 Bancass
        	 params.put( "productName", "SMiLe LINK 88" + titlePackage );
        }else if(istr_prop.bisnis_id == 160){
       	 params.put( "productName", "SMiLe LINK SYARIAH" + titlePackage );
        }else if(istr_prop.bisnis_id == 140){
          	 params.put( "productName", "SMiLe LINK MEDIVEST" + titlePackage );
        }else{
        	 params.put( "productName", istr_prop.nama_plan[ 1 ] );
        }
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 140 ){// MEDIVEST
        	if( cepr01030102Form.getRightPartOfBusinessCd()  == 1 ) params.put( "productName", "SMiLe LINK MEDIVEST" );
        }
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 200 ){// SMiLe Link BRIDGE/ 99 SYARIAH
        	if( cepr01030102Form.getRightPartOfBusinessCd()  == 1 || cepr01030102Form.getRightPartOfBusinessCd()  == 2 ) params.put( "productName", "SMiLe LINK BRIDGE SYARIAH" );
        	else if( cepr01030102Form.getRightPartOfBusinessCd()  == 3 || cepr01030102Form.getRightPartOfBusinessCd()  == 4 ) params.put( "productName", "SMiLe LINK 99 SYARIAH" );
        }
        params.put( "monthlyPremium", editorUtil.convertToMoney( ldec_coi, cepr01030102Form ) );
        result.add( params );
        
        String assuranceBenefit;
        boolean firstBenefitFlag = true;
        N_riders ln_riders = new N_riders();
        List< Mst_proposal > mstProposal = new ArrayList<Mst_proposal>();
        for( int i = 1; i <= ArrUtil.upperBound( istr_prop.rider_baru ); i++ )
        {
            if( istr_prop.rider_baru[ i ] > 0 )
            {
                if( firstBenefitFlag )
                {
                    assuranceBenefit = "Tambahan";
                    firstBenefitFlag = false;
                }
                else
                {
                    assuranceBenefit = "";
                }

                ldec_coi = of_get_coi_rider_cost( i, 1, mstProposal );

                params = new HashMap<String, Object>();
                params.put( "assuranceBenefit", assuranceBenefit );
                params.put( "productName", ln_riders.of_nama( i, cepr01030102Form, cepr01030103Form ) );// nama label di halaman pertama
                params.put( "monthlyPremium", editorUtil.convertToMoney( ldec_coi, cepr01030102Form ) );
                result.add( params );
            }
        }

        return result;
    }

    public List<Map<String, Object>> getInvestmentPerformanceList()
    {
        logger.info( "*-*-*-* Cepr01040201Business.getInvestmentPerformanceList" );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        List<Cepr00000000PerformanceVO> performanceList = eproposalManager.selectInvestmentPerformanceLastFourYears( null );

        String fixIncome;
        String dynamicFund;
        String aggresiveFund;
        String secureDollar;
        String dynamicDollar;

        for( Cepr00000000PerformanceVO performanceVO : performanceList )
        {
            fixIncome = editorUtil.convertToStringRoundPercent( performanceVO.getFixIncome() );
            dynamicFund = editorUtil.convertToStringRoundPercent( performanceVO.getDynamicFund() );
            aggresiveFund = editorUtil.convertToStringRoundPercent( performanceVO.getAggressiveFund() );
            secureDollar = editorUtil.convertToStringRoundPercent( performanceVO.getSecureDollar() );
            dynamicDollar = editorUtil.convertToStringRoundPercent( performanceVO.getDynamicDollar() );
            params = new HashMap<String, Object>();
            params.put( "month", FormatDate.toIndonesianShort( performanceVO.getDate() ) );
            params.put( "fixIncome", fixIncome.equals( "0%" ) ? "nil " : fixIncome );
            params.put( "dynamicFund", dynamicFund.equals( "0%" ) ? "nil " : dynamicFund );
            params.put( "aggresiveFund", aggresiveFund.equals( "0%" ) ? "nil " : aggresiveFund );
            params.put( "secureDollar", secureDollar.equals( "0%" ) ? "nil " : secureDollar );
            params.put( "dynamicDollar", dynamicDollar.equals( "0%" ) ? "nil " : dynamicDollar );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getInvestmentPerformanceSyariahList()
    {
        logger.info( "*-*-*-* Cepr01040000UlinkBusiness.getInvestmentPerformanceSyariahList" );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        String[][] data =
                {
                        { "30 Jun 2003", "Nil",  "Nil"  },
                        { "31 Dec 2003", "Nil",  "Nil"  },
                        { "30 Jun 2004", "Nil",  "Nil"  },
                        { "31 Dec 2004", "Nil",  "Nil"  },
                        { "30 Jun 2005", "Nil",  "Nil"  },
                        { "31 Dec 2005", "Nil",  "Nil"  },
                        { "30 Jun 2006", "3.03", "Nil"  },
                        { "31 Dec 2006", "2.32", "Nil"  },
                        { "30 Jun 2007", "7.12", "9.44" }
                };

        for( String value[] : data )
        {
            params = new HashMap<String, Object>();
            params.put( "month", value[0] );
            params.put( "dynamicFund", value[1] );
            params.put( "aggresiveFund", value[2] );
            result.add( params );
        }

        return result;
    }

    public Cepr00000000YieldResultVO of_get_rate_ds()
    {
        logger.info( "*-*-*-* Cepr01040201Business.of_get_rate_ds" );
        String[] ls_jenis = getInvestmentTitleArr();
        Cepr00000000YieldResultVO result = new Cepr00000000YieldResultVO();
        ArrayList<Map<String, Object>> yieldList = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        int i;
        S_biaya lstr;

        lstr = PbFunction.wf_get_biaya_84( istr_prop, command );
        int a, b;
        
        if( (cepr01030102Form.getLeftPartOfBusinessCd() == 190 && (cepr01030102Form.getRightPartOfBusinessCd() >= 3 && cepr01030102Form.getRightPartOfBusinessCd() <= 8))
        	|| (cepr01030102Form.getLeftPartOfBusinessCd() == 202 && (cepr01030102Form.getRightPartOfBusinessCd() >= 4 && cepr01030102Form.getRightPartOfBusinessCd() <= 6))	
        	|| (cepr01030102Form.getLeftPartOfBusinessCd() == 200 && cepr01030102Form.getRightPartOfBusinessCd() <= 4) 
        	|| (cepr01030102Form.getLeftPartOfBusinessCd() == 116 && (cepr01030102Form.getRightPartOfBusinessCd() >= 3 && cepr01030102Form.getRightPartOfBusinessCd() <= 4))
        	|| (cepr01030102Form.getLeftPartOfBusinessCd() == 140 && (cepr01030102Form.getRightPartOfBusinessCd() >= 1 && cepr01030102Form.getRightPartOfBusinessCd() <= 2))
        	|| (cepr01030102Form.getLeftPartOfBusinessCd() == 199 && (cepr01030102Form.getRightPartOfBusinessCd() >= 1 && cepr01030102Form.getRightPartOfBusinessCd() <= 2))
        	|| (cepr01030102Form.getLeftPartOfBusinessCd() == 153 && (cepr01030102Form.getRightPartOfBusinessCd() >= 3 && cepr01030102Form.getRightPartOfBusinessCd() <= 4)) ){
        	if( "01".equals( istr_prop.kurs_id ) )
             {
                 a = 1;
                 b = 4;          
             }
             else
             {
                 a = 5;
                 b = 6;
             }
        }else if(cepr01030102Form.getLeftPartOfBusinessCd() == 134 && cepr01030102Form.getRightPartOfBusinessCd() == 5){/**USD Fund BSIM Products**/
	        if( "01".equals( istr_prop.kurs_id ) )
	        {
	            a = 1;
	            b = 3;
	        }
	        else
	        {
	            a = 4;
	            b = 6;
	        }
        }
        else{
	        if( "01".equals( istr_prop.kurs_id ) )
	        {
	            a = 1;
	            b = 3;
	        }
	        else
	        {
	            a = 4;
	            b = 5;
	        }
        }
        
        
        double totalInvestmentAllocation = 0;
        double totalAsumptionLow = 0;
        double totalAsumptionMid = 0;
        double totalAsumptionHi = 0;

        double allocationYieldLow;
        double allocationYieldMid;
        double allocationYieldHi;

        double annualYieldLow;
        double annualYieldMid;
        double annualYieldHi;

        for( i = a; i <= b; i++ )
        {
            annualYieldLow = lstr.bunga[ i ][ 1 ] * 100;
            annualYieldMid = lstr.bunga[ i ][ 2 ] * 100;
            annualYieldHi = lstr.bunga[ i ][ 3 ] * 100;

            allocationYieldLow = annualYieldLow * istr_prop.fund[ i ] / 100;
            allocationYieldMid = annualYieldMid * istr_prop.fund[ i ] / 100;
            allocationYieldHi = annualYieldHi * istr_prop.fund[ i ] / 100;


            params = new HashMap<String, Object>();
            params.put( "fundDesc", ls_jenis[ i ] );
            params.put( "annualYieldLow", editorUtil.convertToTwoDigit( annualYieldLow ) + "%" );
            params.put( "annualYieldMid", editorUtil.convertToTwoDigit( annualYieldMid ) + "%" );
            params.put( "annualYieldHi", editorUtil.convertToTwoDigit( annualYieldHi ) + "%" );
            params.put( "investmentAllocation", editorUtil.convertToTwoDigit( istr_prop.fund[ i ] ) + "%" );
            params.put( "allocationYieldLow", editorUtil.convertToTwoDigit( allocationYieldLow ) + "%" );
            params.put( "allocationYieldMid", editorUtil.convertToTwoDigit( allocationYieldMid ) + "%" );
            params.put( "allocationYieldHi", editorUtil.convertToTwoDigit( allocationYieldHi ) + "%" );
            yieldList.add( params );

            totalInvestmentAllocation = totalInvestmentAllocation + istr_prop.fund[ i ];
            totalAsumptionLow = totalAsumptionLow + allocationYieldLow;
            totalAsumptionMid = totalAsumptionMid + allocationYieldMid;
            totalAsumptionHi = totalAsumptionHi + allocationYieldHi;
        }

        result.setYieldList( yieldList );
        result.setTotalInvestmentAllocation( editorUtil.convertToTwoDigit( totalInvestmentAllocation ) + "%" );
        result.setTotalAssumptionLow( editorUtil.convertToTwoDigit( totalAsumptionLow ) + "%" );
        result.setTotalAssumptionMid( editorUtil.convertToTwoDigit( totalAsumptionMid ) + "%" );
        result.setTotalAssumptionHi( editorUtil.convertToTwoDigit( totalAsumptionHi ) + "%" );

        return result;
    }
    
    
    public Cepr00000000YieldResultVO of_get_rate_ds_syariah()
    {
        logger.info( "*-*-*-* Cepr01040201Business.of_get_rate_ds" );
        String[] ls_jenis = getInvestmentTitleArr();
        Cepr00000000YieldResultVO result = new Cepr00000000YieldResultVO();
        ArrayList<Map<String, Object>> yieldList = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        int i;
        S_biaya lstr;

        lstr = PbFunction.wf_get_biaya_84( istr_prop, command );
        int a, b;
        if( "01".equals( istr_prop.kurs_id ) )
        {
            a = 2;
            b = 3;
        }
        else
        {
            a = 4;
            b = 5;
        }

        double totalInvestmentAllocation = 0;
        double totalAsumptionLow = 0;
        double totalAsumptionMid = 0;
        double totalAsumptionHi = 0;

        double allocationYieldLow;
        double allocationYieldMid;
        double allocationYieldHi;

        double annualYieldLow;
        double annualYieldMid;
        double annualYieldHi;

        for( i = a; i <= b; i++ )
        {
            annualYieldLow = lstr.bunga[ i ][ 1 ] * 100;
            annualYieldMid = lstr.bunga[ i ][ 2 ] * 100;
            annualYieldHi = lstr.bunga[ i ][ 3 ] * 100;

            allocationYieldLow = annualYieldLow * istr_prop.fund[ i ] / 100;
            allocationYieldMid = annualYieldMid * istr_prop.fund[ i ] / 100;
            allocationYieldHi = annualYieldHi * istr_prop.fund[ i ] / 100;


            params = new HashMap<String, Object>();
            params.put( "fundDesc", ls_jenis[ i ] );
            params.put( "annualYieldLow", editorUtil.convertToTwoDigit( annualYieldLow ) + "%" );
            params.put( "annualYieldMid", editorUtil.convertToTwoDigit( annualYieldMid ) + "%" );
            params.put( "annualYieldHi", editorUtil.convertToTwoDigit( annualYieldHi ) + "%" );
            params.put( "investmentAllocation", editorUtil.convertToTwoDigit( istr_prop.fund[ i ] ) + "%" );
            params.put( "allocationYieldLow", editorUtil.convertToTwoDigit( allocationYieldLow ) + "%" );
            params.put( "allocationYieldMid", editorUtil.convertToTwoDigit( allocationYieldMid ) + "%" );
            params.put( "allocationYieldHi", editorUtil.convertToTwoDigit( allocationYieldHi ) + "%" );
            yieldList.add( params );

            totalInvestmentAllocation = totalInvestmentAllocation + istr_prop.fund[ i ];
            totalAsumptionLow = totalAsumptionLow + allocationYieldLow;
            totalAsumptionMid = totalAsumptionMid + allocationYieldMid;
            totalAsumptionHi = totalAsumptionHi + allocationYieldHi;
        }

        result.setYieldList( yieldList );
        result.setTotalInvestmentAllocation( editorUtil.convertToTwoDigit( totalInvestmentAllocation ) + "%" );
        result.setTotalAssumptionLow( editorUtil.convertToTwoDigit( totalAsumptionLow ) + "%" );
        result.setTotalAssumptionMid( editorUtil.convertToTwoDigit( totalAsumptionMid ) + "%" );
        result.setTotalAssumptionHi( editorUtil.convertToTwoDigit( totalAsumptionHi ) + "%" );

        return result;
    }

    public List<Map<String, Object>> getCommonHeaderRowList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getCommonHeaderRowList" );

        List sekaligusRightPartCdList = ArrUtil.toListFromArray( sekaligusRightPartCdArr );
        List berkalaRightPartCdList = ArrUtil.toListFromArray( berkalaRightPartCdArr );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01030104Form cepr01030104Form = cepr01030000HoldingForm.getCepr01030104Form();
        
        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        // Fill header content from CommonHeaderDataSource
        String word_premi = "";
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 200 || cepr01030102Form.getLeftPartOfBusinessCd() == 202 || cepr01030102Form.getLeftPartOfBusinessCd() == 153 || cepr01030102Form.getLeftPartOfBusinessCd() == 160)// produk produk syariah
        {
        	result.addAll( source.getPolicyHolderNameMap() );
            result.addAll( source.getPolicyHolderAgeMap() );
            result.addAll( source.getInsuredNameSyariahMap() );
            result.addAll( source.getInsuredAgeSyariahMap() );
            result.addAll( source.getGenderInsuredSyariahMap() );
             result.addAll( source.getTermOfContractWithLimitSyariahMap( insuredAgeLimit ) );
             word_premi = "Kontribusi";
        }else{
        	 result.addAll( source.getPolicyHolderNameMap() );
             result.addAll( source.getPolicyHolderAgeMap() );
             
             if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getBabyFlag() ) )
             {
            	 Map<String, Object> param;
            	 param = new HashMap<String, Object>();
            	 param.put( "label", "Nama Tertanggung" );
            	 param.put( "content", editorUtil.convertToString( "Calon bayi Ibu "+cepr01030101Form.getInsuredName() ) );
            	 result.add( param );
            	 param = new HashMap<String, Object>();            
            	 param.put( "label", "Usia Tertanggung" );
            	 param.put( "content", editorUtil.convertToString( "0" ) + " tahun" );
            	 result.add( param );
            	 result.addAll( source.getGenderInsuredMap() );
             }else
             {
                 result.addAll( source.getInsuredNameMap() );
                 result.addAll( source.getInsuredAgeMap() );
                 result.addAll( source.getGenderInsuredMap() );                 
            }             
             result.addAll( source.getTermOfContractWithLimitMap( insuredAgeLimit ) );
             word_premi = "Premi";
        }
       

        Map<String, Object> params;
        if( sekaligusRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
            params = new HashMap<String, Object>();
            params.put( "label", word_premi + " Pokok Sekaligus" );
            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) );
            result.add( params );
            
           if( cepr01030104Form.getTopupDrawVOList().get(0).getYearFlag() != null && cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().compareTo(new BigDecimal(0)) >= 1)
 	       {
        	   params = new HashMap<String, Object>();
        	   params.put( "label", word_premi + " Top Up Single" );
 	           params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount() ) );
 	           result.add( params ); 
 	       }
        }
        else if( berkalaRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
        	int cara_bayar = istr_prop.cb_id;
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
        	if(  cepr01030102Form.getPremiumCombinationCd().equals( new BigDecimal("100") ) ){
                params = new HashMap<String, Object>();
                params.put( "label", word_premi + " Pokok "+ label );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getBasePremium() ) );
                result.add( params );
        	} else{
//       		(Helpdesk 56020): Paket-Bridge Agency tidak ditampilkan
        		if( (cepr01030102Form.getLeftPartOfBusinessCd() == 200 || cepr01030102Form.getLeftPartOfBusinessCd() == 190) && CommonConst.DISPLAY_TRUE.equals(cepr01030102Form.getJenisListDisplay()) && cepr01030102Form.getJenisCd() > 0 )
                {
                }else{
	                params = new HashMap<String, Object>();
	                params.put( "label", word_premi + " Pokok "+ label );
	                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getBasePremium() ) + " (" + cepr01030102Form.getPremiumCombinationCd().toString() + "%)" );
	                result.add( params );
	
	                params = new HashMap<String, Object>();
	                params.put( "label", word_premi + " Top Up Berkala "+ label );
	                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getTopUpPremium() ) + " (" + ( new BigDecimal( "100" ).subtract( cepr01030102Form.getPremiumCombinationCd() ) ).toString() + "%)" );
	                result.add( params );
                }
        		
                params = new HashMap<String, Object>();
                params.put( "label", "" );
                params.put( "content", "------------------------" );
                result.add( params );
                
                params = new HashMap<String, Object>();
                params.put( "label", "Total " + word_premi + " " + label );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) );
                result.add( params );
        	}
        	        	        	
	       if( cepr01030104Form.getTopupDrawVOList().get(0).getYearFlag() != null && cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().compareTo(new BigDecimal(0)) >= 1)
	       {
	        		 params = new HashMap<String, Object>();
	        		 params.put( "label", word_premi + " Top Up Single" );
	                 params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount() ) );
	                 result.add( params ); 
	       }
        	
            params = new HashMap<String, Object>();
            params.put( "label", "Asumsi cuti " + word_premi + " setelah" );
            params.put( "content", "Tahun ke " + editorUtil.convertToString( cepr01030102Form.getPremiumFurloughYear() ) );
            result.add( params );
        }
        
        if(cepr01030102Form.getLeftPartOfBusinessCd() == 153 || cepr01030102Form.getLeftPartOfBusinessCd() == 200)// produk produk syariah
        {
        	 result.addAll( source.getTotalSumInsuredSyariahMap() );
             result.addAll( source.getInsuredMedicalCheckUpSyariahMap( ( String ) of_cek_medis( command ).get( "medis_pp" ) ) );
             result.addAll( source.getInsuredMedicalCheckUpSyariahMap( ( String ) of_cek_medis( command ).get( "medis_tt" ) ) );            	
        }else{
        	 result.addAll( source.getTotalSumInsuredMap() );
             result.addAll( source.getPolicyHolderMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_pp" ) ) );
             result.addAll( source.getInsuredMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_tt" ) ) );        	
        }

        
        return result;
    }

    public List<Map<String, Object>> getCommonHeaderSmallRowList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getCommonHeaderSmallRowList" );

        List sekaligusRightPartCdList = ArrUtil.toListFromArray( sekaligusRightPartCdArr );
        List berkalaRightPartCdList = ArrUtil.toListFromArray( berkalaRightPartCdArr );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01030104Form cepr01030104Form = cepr01030000HoldingForm.getCepr01030104Form();
        
        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

     
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getBabyFlag() ) )
        {
	       	 Map<String, Object> param;
	       	 param = new HashMap<String, Object>();
	       	 param.put( "label", "Nama Tertanggung" );
	       	 param.put( "content", editorUtil.convertToString( "Calon bayi Ibu "+cepr01030101Form.getInsuredName() ) );
	       	 result.add( param );
	       	 param = new HashMap<String, Object>();            
	       	 param.put( "label", "Usia Tertanggung" );
	       	 param.put( "content", editorUtil.convertToString( "0" ) + " tahun" );
	       	 result.add( param );
        }else
        {
        	 // Fill header content from CommonHeaderDataSource
            result.addAll( source.getInsuredNameMap() );
            result.addAll( source.getInsuredAgeMap() ) ;
        }
        
        result.addAll( source.getTermOfContractWithLimitMapInTwoRows( insuredAgeLimit ) );

        Map<String, Object> params;
        if( sekaligusRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
            params = new HashMap<String, Object>();
            params.put( "label", "Premi Pokok Sekaligus" );
            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) );
            result.add( params );
            
           if( cepr01030104Form.getTopupDrawVOList().get(0).getYearFlag() != null && cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().compareTo(new BigDecimal(0)) >= 1)
  	       {
        	   params = new HashMap<String, Object>();
               params.put( "label", "Premi Top Up Single" );
               params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount() ) );
               result.add( params ); 
         	} 
        }
        else if( berkalaRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
        	int cara_bayar = istr_prop.cb_id;
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
        	
//			(Helpdesk 56020): Paket-Bridge Agency tidak ditampilkan
    		if( (cepr01030102Form.getLeftPartOfBusinessCd() == 200 || cepr01030102Form.getLeftPartOfBusinessCd() == 190) && CommonConst.DISPLAY_TRUE.equals(cepr01030102Form.getJenisListDisplay()) && cepr01030102Form.getJenisCd() > 0 )
            {
            }else{
	            params = new HashMap<String, Object>();
	            params.put( "label", "Premi Pokok " + label );
	            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getBasePremium() ) + " (" + cepr01030102Form.getPremiumCombinationCd().toString() + "%)" );
	            result.add( params );
	
	            params = new HashMap<String, Object>();
	            params.put( "label", "Premi Top Up Berkala " + label );
	            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getTopUpPremium() ) + " (" + ( new BigDecimal( "100" ).subtract( cepr01030102Form.getPremiumCombinationCd() ) ).toString() + "%)" );
	            result.add( params );
            }
    		
            params = new HashMap<String, Object>();
            params.put( "label", "" );
            params.put( "content", "------------------------" );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "label", "Total Premi " + label );
            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) );
            result.add( params );

           if( cepr01030104Form.getTopupDrawVOList().get(0).getYearFlag() != null && cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().compareTo(new BigDecimal(0)) >= 1)
 	       {
        		 params = new HashMap<String, Object>();
                 params.put( "label", "Premi Top Up Single" );
                 params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount() ) );
                 result.add( params ); 
        	} 
            
            params = new HashMap<String, Object>();
            params.put( "label", "Asumsi cuti premi setelah" );
            params.put( "content", "Tahun ke " + editorUtil.convertToString( cepr01030102Form.getPremiumFurloughYear() ) );
            result.add( params );
        }

        result.addAll( source.getTotalSumInsuredMap() );

        return result;
    }
    
    public List<Map<String, Object>> getCommonHeaderSmallRowSyariahList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getCommonHeaderSmallRowSyariahList" );

        List sekaligusRightPartCdList = ArrUtil.toListFromArray( sekaligusRightPartCdArr );
        List berkalaRightPartCdList = ArrUtil.toListFromArray( berkalaRightPartCdArr );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

     
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getBabyFlag() ) )
        {
	       	 Map<String, Object> param;
	       	 param = new HashMap<String, Object>();
	       	 param.put( "label", "Nama Peserta" );
	       	 param.put( "content", editorUtil.convertToString( "Calon bayi Ibu "+cepr01030101Form.getInsuredName() ) );
	       	 result.add( param );
	       	 param = new HashMap<String, Object>();            
	       	 param.put( "label", "Usia Peserta" );
	       	 param.put( "content", editorUtil.convertToString( "0" ) + " tahun" );
	       	 result.add( param );
        }else
        {
        	 // Fill header content from CommonHeaderDataSource
        	 result.addAll( source.getInsuredNameSyariahMap() );
             result.addAll( source.getInsuredAgeSyariahMap() ) ;
        }
        
        result.addAll( source.getTermOfContractWithLimitSyariahMapInTwoRows( insuredAgeLimit ) );

        Map<String, Object> params;
        if( sekaligusRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
            params = new HashMap<String, Object>();
            params.put( "label", "Kontribusi Pokok Sekaligus" );
            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) );
            result.add( params );
            
            if( cepr01030104Form.getTopupDrawVOList().get(0).getYearFlag() != null && cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().compareTo(new BigDecimal(0)) >= 1)
  	       {
         		 params = new HashMap<String, Object>();
                 params.put( "label", "Kontribusi Top Up Single" );
                 params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount() ) );
                 result.add( params ); 
         	} 
        }
        else if( berkalaRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
        	int cara_bayar = istr_prop.cb_id;
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

//			(Helpdesk 56020): Paket-Bridge Agency tidak ditampilkan
    		if( (cepr01030102Form.getLeftPartOfBusinessCd() == 200 || cepr01030102Form.getLeftPartOfBusinessCd() == 190) && CommonConst.DISPLAY_TRUE.equals(cepr01030102Form.getJenisListDisplay()) && cepr01030102Form.getJenisCd() > 0 )
            {
            }else{        	
	            params = new HashMap<String, Object>();
	            params.put( "label", "Kontribusi Pokok " + label );
	            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getBasePremium() ) + " (" + cepr01030102Form.getPremiumCombinationCd().toString() + "%)" );
	            result.add( params );
	
	            params = new HashMap<String, Object>();
	            params.put( "label", "Kontribusi Top Up Berkala " + label );
	            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getTopUpPremium() ) + " (" + ( new BigDecimal( "100" ).subtract( cepr01030102Form.getPremiumCombinationCd() ) ).toString() + "%)" );
	            result.add( params );
            }
    		
            params = new HashMap<String, Object>();
            params.put( "label", "" );
            params.put( "content", "------------------------" );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "label", "Total Kontribusi " + label );
            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) );
            result.add( params );

           if( cepr01030104Form.getTopupDrawVOList().get(0).getYearFlag() != null && cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().compareTo(new BigDecimal(0)) >= 1)
 	       {
 	        	params = new HashMap<String, Object>();
 	            params.put( "label", "KontribusiTop Up Single" );
 	            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount() ) );
 	            result.add( params ); 
 	       }
            
            params = new HashMap<String, Object>();
            params.put( "label", "Asumsi cuti Kontribusi setelah" );
            params.put( "content", "Tahun ke " + editorUtil.convertToString( cepr01030102Form.getPremiumFurloughYear() ) );
            result.add( params );
        }

        result.addAll( source.getTotalSumInsuredSyariahMap() );

        return result;
    }

    public List<Map<String, Object>> getInvestmentChoiceRowList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getInvestmentChoiceRowList" );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        Map<String, Object> params;

        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 1 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getFixIdr() + "%" ) );
                result.add( params );

                for( int i = 2;  i < investmentTitleAndDescrArr[ 1 ].length; i++  )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", investmentTitleAndDescrArr[ 1 ][ i ] );
                    params.put( "allocationPercent", "" );
                    result.add( params );
                }
            }
            if( cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 2 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicIdr() + "%" ) );
                result.add( params );

                for( int i = 2;  i < investmentTitleAndDescrArr[ 2 ].length; i++  )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", investmentTitleAndDescrArr[ 2 ][ i ] );
                    params.put( "allocationPercent", "" );
                    result.add( params );
                }
            }
            if( cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 3 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveIdr() + "%" ) );
                result.add( params );

                for( int i = 2;  i < investmentTitleAndDescrArr[ 3 ].length; i++  )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", investmentTitleAndDescrArr[ 3 ][ i ] );
                    params.put( "allocationPercent", "" );
                    result.add( params );
                }
            }
            if( cepr01030102Form.getCashFundIdr() != null ){
            	if( cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) > 0 )            
            {
            	 params = new HashMap<String, Object>();
                 params.put( "description", investmentTitleAndDescrArr[ 4 ][ 1 ] );
                 params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getCashFundIdr() + "%" ) );
                 result.add( params );
                 
                 for( int i = 2;  i < investmentTitleAndDescrArr[ 4 ].length; i++  )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", investmentTitleAndDescrArr[ 4 ][ i ] );
                     params.put( "allocationPercent", "" );
                     result.add( params );
                 }
            	
            }
            }
            
            if( cepr01030102Form.getExcellinkEquityIdr() != null && cepr01030102Form.getExcellinkEquityIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
            	 params = new HashMap<String, Object>();
                 params.put( "description", investmentTitleAndDescrArr[ 5 ][ 1 ] );
                 params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getExcellinkEquityIdr() + "%" ) );
                 result.add( params );
                 
                 for( int i = 2;  i < investmentTitleAndDescrArr[ 5 ].length; i++  )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", investmentTitleAndDescrArr[ 5 ][ i ] );
                     params.put( "allocationPercent", "" );
                     result.add( params );
                 }
            	
            }            
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getSecureUsd() != null && cepr01030102Form.getSecureUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 4 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getSecureUsd() + "%" ) );
                result.add( params );

                for( int i = 2;  i < investmentTitleAndDescrArr[ 4 ].length; i++  )
                {
                	if( investmentTitleAndDescrArr[ 4 ][ i ] != null)
                	{
	                    params = new HashMap<String, Object>();
	                    params.put( "description", investmentTitleAndDescrArr[ 4 ][ i ] );
	                    params.put( "allocationPercent", "" );
	                    result.add( params );
                	}
                }
            }
            if( cepr01030102Form.getDynamicUsd() != null && cepr01030102Form.getDynamicUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 5 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicUsd() + "%" ) );
                result.add( params );
                for( int i = 2;  i < investmentTitleAndDescrArr[ 5 ].length; i++  )
                {
                	if( investmentTitleAndDescrArr[ 5 ][ i ] != null ){
	                    params = new HashMap<String, Object>();
	                    params.put( "description", investmentTitleAndDescrArr[ 5 ][ i ] );
	                    params.put( "allocationPercent", "" );
	                    result.add( params );
                	}
                }
            }
            /**USD Fund BSIM Products**/
            if( cepr01030102Form.getAggresiveUsd() != null && cepr01030102Form.getAggresiveUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 6 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveUsd() + "%" ) );
                result.add( params );
                for( int i = 2;  i < investmentTitleAndDescrArr[ 6 ].length; i++  )
                {
                	if( investmentTitleAndDescrArr[ 6 ][ i ] != null ){
	                    params = new HashMap<String, Object>();
	                    params.put( "description", investmentTitleAndDescrArr[ 6 ][ i ] );
	                    params.put( "allocationPercent", "" );
	                    result.add( params );
                	}
                }
            }
            
        }

        return result;
    }

    public List<Map<String, Object>> getInvestmentChoiceSmallRowList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getInvestmentChoiceSmallRowList" );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        Map<String, Object> params;

        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 1 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getFixIdr() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getFixIdr() + "%" ) );
                result.add( params );
            }

            if( cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 2 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getDynamicIdr() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicIdr() + "%" ) );
                result.add( params );
            }

            if( cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 3 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getAggresiveIdr() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveIdr() + "%" ) );
                result.add( params );
            }
            
            if( cepr01030102Form.getCashFundIdr() != null ){ 
            	if( cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 4 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getCashFundIdr() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getCashFundIdr() + "%" ) );
                result.add( params );
            }}
            
            if( cepr01030102Form.getExcellinkEquityIdr() != null && cepr01030102Form.getExcellinkEquityIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 5 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getExcellinkEquityIdr() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getExcellinkEquityIdr() + "%" ) );
                result.add( params );
            }            
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getSecureUsd() != null && cepr01030102Form.getSecureUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 4 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getSecureUsd() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getSecureUsd() + "%" ) );
                result.add( params );
            }

            if( cepr01030102Form.getDynamicUsd() != null && cepr01030102Form.getDynamicUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 5 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getDynamicUsd() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicUsd() + "%" ) );
                result.add( params );
            }
            /**USD Fund BSIM Products**/
            if( cepr01030102Form.getAggresiveUsd() != null && cepr01030102Form.getAggresiveUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 6 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getAggresiveUsd() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveUsd() + "%" ) );
                result.add( params );
            }
        }

        return result;
    }

    
    public List<Map<String, Object>> getInvestmentChoiceSmallRowSyariahList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getInvestmentChoiceSmallRowList" );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        Map<String, Object> params;

        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 1 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getFixIdr() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getFixIdr() + "%" ) );
                result.add( params );
            }

            if( cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 2 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getDynamicIdr() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicIdr() + "%" ) );
                result.add( params );
            }

            if( cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 3 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getAggresiveIdr() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveIdr() + "%" ) );
                result.add( params );
            }
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getSecureUsd() != null && cepr01030102Form.getSecureUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 4 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getSecureUsd() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getSecureUsd() + "%" ) );
                result.add( params );
            }

            if( cepr01030102Form.getDynamicUsd() != null && cepr01030102Form.getDynamicUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 5 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getDynamicUsd() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicUsd() + "%" ) );
                result.add( params );
            }
        }

        return result;
    }
    
    public Cepr00000000YieldResultVO getInvestmentYield()
    {
        return of_get_rate_ds();
    }

    public Cepr00000000YieldResultVO getInvestmentSyariahYield()
    {
        return of_get_rate_ds_syariah();
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

        // if no riders, don't show this page ( page 3 )
        if( currentIdx != 0 )
        {
            params = new HashMap<String, Object>();
            params.put( "pageCode", "sub3" );
            result.add( params );
        }

        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub4" );
        result.add( params );

        return result;
    }

    // I got it from d_ilus_166_sum7
    // If( np1 >0, string(  celaka1, '#,##0'), 'nil')
    public String convertToStringWithoutCentAndSetNill( Cepr00000000EditorUtil editorUtil, double celaka, double np )
    {
        String result;

        if( celaka < 0 )
        {
            result = "nil";
        }
        else if( np > 0 )
        {
            result = editorUtil.convertToStringWithoutCent( celaka );
        }
        else
        {
            result = "nil";
        }

        return result;
    }
    
    public double selectLdecRateTerm( int jenis, String kurs_id, int umur_tt ) throws DataAccessException
    {
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put( "li_jenis", jenis );
    	params.put( "kursId", kurs_id );
        params.put( "liUsia", umur_tt );
        double ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateTerm( params ) );
        //ldec_temp = FormatNumber.round( ( ( istr_prop.rider_baru[ 12 ] * istr_prop.up / 1000 ) * ldec_rate ) * 0.1, 2 );
        //ldec_total += ldec_temp;
        //System.out.println("term" + ldec_temp);
        //return ( BigDecimal ) querySingle( "selectLdecRateTerm", params );
        return ldec_rate;
    }
    
    public double selectLdecRateCiRider( int jenis, String kurs_id, int umur_tt ) throws DataAccessException
    {
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put( "li_jenis", jenis );
    	params.put( "kursId", kurs_id );
        params.put( "liUsia", umur_tt );
        double ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateCiRider( params ) );
        //ldec_temp = FormatNumber.round( ( ( istr_prop.rider_baru[ 12 ] * istr_prop.up / 1000 ) * ldec_rate ) * 0.1, 2 );
        //ldec_total += ldec_temp;
        //System.out.println("term" + ldec_temp);
        //return ( BigDecimal ) querySingle( "selectLdecRateTerm", params );
        return ldec_rate;
    }
    
    public double selectLdecRateHcpFamilyRider( int jenis, String kurs_id, int umur_tt ) throws DataAccessException
    {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put( "riderBaru", jenis ); // dimulai dari 281
            params.put( "kursId", istr_prop.kurs_id );
            params.put( "liUsia", umur_tt );
            double ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamily( params ) );

            ldec_rate = FormatNumber.round( ldec_rate * 1, 2 );
            //ldec_total += ldec_temp;

        //hcp family peserta tambahan
            int[] usia = istr_prop.hcpf.usia;
            for( int i = 1; i <= istr_prop.hcpf.peserta; i++ )
            {
                Map<String, Object> paramsAddition = new HashMap<String, Object>();
                paramsAddition.put( "riderBaru", jenis); // dimulai dari 281
                paramsAddition.put( "kursId", istr_prop.kurs_id );
                paramsAddition.put( "liUsia", usia[i] );
                double ldec_temp = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpFamilyPesertaTambahan( paramsAddition ) );
                double percentage = 0.9;
//            	ldec_temp = Round(ldec_rate * 0.9, 2) //disc 10% -> 90% 
                ldec_temp = FormatNumber.round(ldec_temp * percentage, 2); //disc 10% -> 90% 
                ldec_rate = ldec_rate + ldec_temp;
            }
        return ldec_rate;
    }
    
    
    public double selectLdecRateHcpProviderRider( int jenis, String kurs_id, int umur_tt ) throws DataAccessException
    {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put( "riderBaru", jenis+280 ); // dimulai dari 281
            params.put( "kursId", istr_prop.kurs_id );
            params.put( "liUsia", umur_tt );
            double ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProvider( params ) );

            ldec_rate = FormatNumber.round( ldec_rate * 1, 2 );
            //ldec_total += ldec_temp;

        //hcp family peserta tambahan
            int[] usia = istr_prop.hcpf.usia;
            for( int i = 1; i <= istr_prop.hcpf.peserta; i++ )
            {
                Map<String, Object> paramsAddition = new HashMap<String, Object>();
                paramsAddition.put( "riderBaru", jenis+280 ); // dimulai dari 281
                paramsAddition.put( "kursId", istr_prop.kurs_id );
                paramsAddition.put( "liUsia", usia[i] );
                double ldec_temp = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProviderPesertaTambahan( paramsAddition ) );
                double percentage = 0.9;
//                if(usia[i]>25){
//                	percentage = 1;
//                }else{
//                	percentage = 0.9;
//                }
                ldec_temp = FormatNumber.round(ldec_temp * percentage, 2); //disc 10% -> 90% 
                ldec_rate = ldec_rate + ldec_temp;
            }
        //}
    	//ldec_rate = ldec_rate + ldec_total
    	//ldec_temp = FormatNumber.round( ( ( istr_prop.rider_baru[ 12 ] * istr_prop.up / 1000 ) * ldec_rate ) * 0.1, 2 );
        //ldec_total += ldec_temp;
        //System.out.println("term" + ldec_temp);
        //return ( BigDecimal ) querySingle( "selectLdecRateTerm", params );
        return ldec_rate;
    }

    public double selectLdecRatePaRider( int jenis, String kurs_id, int umur_tt ) throws DataAccessException
    {
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put( "li_jenis", jenis );
    	params.put( "kursId", kurs_id );
        params.put( "liUsia", umur_tt );
        double ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateCiRider( params ) );
        //ldec_temp = FormatNumber.round( ( ( istr_prop.rider_baru[ 12 ] * istr_prop.up / 1000 ) * ldec_rate ) * 0.1, 2 );
        //ldec_total += ldec_temp;
        //System.out.println("term" + ldec_temp);
        //return ( BigDecimal ) querySingle( "selectLdecRateTerm", params );
        return ldec_rate;
    }
    
    // don't forget to add this when new rider added
    public double of_get_coi_rider_in_header( int ai_jenis, int ai_th )
    {
        return of_get_coi_rider( ai_jenis, ai_th );
    }
    
    public double of_get_coi_119_in_header( int ai_jenis, int ai_th )
    {
        return of_get_coi_rider_119( ai_jenis, ai_th );
    }
    
    public double of_get_coi_120_in_header( int ai_jenis, int ai_th )
    {
        return of_get_coi_120( ai_jenis, ai_th );
    }
    
    public double of_get_coi_141_in_header( int ai_jenis, int ai_th )
    {
        return of_get_coi_141( ai_jenis, ai_th );
    }
        
    
    public List<Map<String, Object>> getCommonBiayaRincianRowList(BigDecimal biayaAsuransiPokok) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040211Business.getCommonBiayaRincianRowList" );
        List sekaligusRightPartCdList = ArrUtil.toListFromArray( sekaligusRightPartCdArr );
        List berkalaRightPartCdList = ArrUtil.toListFromArray( berkalaRightPartCdArr ); 
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        
        Map<String, Object> params = new HashMap<String, Object>();
     
        String lblCb = "Premi";
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 153 || cepr01030102Form.getLeftPartOfBusinessCd() == 160 || cepr01030102Form.getLeftPartOfBusinessCd() == 200 ||
        	cepr01030102Form.getLeftPartOfBusinessCd() == 202 || cepr01030102Form.getLeftPartOfBusinessCd() == 215 || cepr01030102Form.getLeftPartOfBusinessCd() == 216 ||
        	cepr01030102Form.getLeftPartOfBusinessCd() == 218 || cepr01030102Form.getLeftPartOfBusinessCd() == 224){ //SYARIAH
        	lblCb = "Kontribusi";
        }
        
        if( sekaligusRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
        	params.put( "label", "-	Biaya Akuisisi sebesar 5% dari " + lblCb +" Sekaligus" );
 	        params.put( "content", editorUtil.convertToString( "" ) );
 	        params.put( "separator", "" );
 	        result.add( params );
 	        
 	        params = new HashMap<String, Object>();
	        params.put( "label", "- Biaya Top Up sebesar 5% dari "+ lblCb +" Top Up Tunggal" );
	        params.put( "content", editorUtil.convertToString( "" ) );
	        params.put( "separator", "" );
	        result.add( params );        	
        }
        else if( berkalaRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
	        // Fill header content 
	        params.put( "label", "-	Biaya Akuisisi "+ lblCb +" Pokok Regular" );
	        params.put( "content", editorUtil.convertToString( "" ) );
	        params.put( "separator", "" );
	        result.add( params );
	        
	        if( cepr01030102Form.getLeftPartOfBusinessCd() == 200 || cepr01030102Form.getLeftPartOfBusinessCd() == 190){ //SMiLe Link 99/BRIDGE Syariah
		        params = new HashMap<String, Object>();
		        params.put( "label", "    - Tahun ke-1" );	       
		        params.put( "content", editorUtil.convertToString( "100%" ) );
		        params.put( "separator", ":" );
		        result.add( params );
		        
		        params = new HashMap<String, Object>();
		        params.put( "label", "    - Tahun ke-2" );
		        params.put( "content", editorUtil.convertToString( "60%" ) );
		        params.put( "separator", ":" );
		        result.add( params );
		       
		        params = new HashMap<String, Object>();
		        params.put( "label", "    - Tahun ke-3-5" );
		        params.put( "content", editorUtil.convertToString( "15%" ) );
		        params.put( "separator", ":" );
		        result.add( params );
		        
		        params = new HashMap<String, Object>();
		        params.put( "label", "    - Tahun ke-6 dan seterusnya" );
		        params.put( "content", editorUtil.convertToString( "0%" ) );
		        params.put( "separator", ":" );
		        result.add( params );
	        }else if( cepr01030102Form.getLeftPartOfBusinessCd() == 120 || cepr01030102Form.getLeftPartOfBusinessCd() == 202){ //SIMPOL/Excellent Link 		         
		         params = new HashMap<String, Object>();
		         params.put( "label", "    - Tahun ke-1" );
		         params.put( "content", editorUtil.convertToString( "80%" ) );
		         params.put( "separator", ":" );
		         result.add( params );
		         
		         params = new HashMap<String, Object>();
		         params.put( "label", "    - Tahun ke-2" );
		         params.put( "content", editorUtil.convertToString( "15%" ) );
		         params.put( "separator", ":" );
		         result.add( params );
		        
		         params = new HashMap<String, Object>();
		         params.put( "label", "    - Tahun ke-3 dan seterusnya" );
		         params.put( "content", editorUtil.convertToString( "0%" ) );
		         params.put( "separator", ":" );
		         result.add( params );
	        }else if( cepr01030102Form.getLeftPartOfBusinessCd() == 116 || cepr01030102Form.getLeftPartOfBusinessCd() == 153){ //SMiLe Link 88 /Syariah      
	        	 params = new HashMap<String, Object>();
			     params.put( "label", "    - Tahun ke-1" );	       
			     params.put( "content", editorUtil.convertToString( "70%" ) );
			     params.put( "separator", ":" );
			     result.add( params );
			        
			    params = new HashMap<String, Object>();
			    params.put( "label", "    - Tahun ke-2" );
			    params.put( "content", editorUtil.convertToString( "20%" ) );
			    params.put( "separator", ":" );
			    result.add( params );
			       
			    params = new HashMap<String, Object>();
			    params.put( "label", "    - Tahun ke-3-5" );
			    params.put( "content", editorUtil.convertToString( "5%" ) );
			    params.put( "separator", ":" );
			    result.add( params );
			        
			    params = new HashMap<String, Object>();
			    params.put( "label", "    - Tahun ke-6 dan seterusnya" );
			    params.put( "content", editorUtil.convertToString( "0%" ) );
			    params.put( "separator", ":" );
			    result.add( params );
	        }else if( cepr01030102Form.getLeftPartOfBusinessCd() == 159 || cepr01030102Form.getLeftPartOfBusinessCd() == 160){ //SMiLe Link /Syariah  
	        	params = new HashMap<String, Object>();
	  	        params.put( "label", "    - Tahun ke-1" );  	      
	  	        params.put( "content", editorUtil.convertToString( "90%" ) );
	  	        params.put( "separator", ":" );
	  	        result.add( params );
	  	        
	  	        params = new HashMap<String, Object>();
	  	        params.put( "label", "    - Tahun ke-2" );
	  	        params.put( "content", editorUtil.convertToString( "45%" ) );
	  	        params.put( "separator", ":" );
	  	        result.add( params );
	  	       
	  	        params = new HashMap<String, Object>();
			    params.put( "label", "    - Tahun ke-3-5" );
			    params.put( "content", editorUtil.convertToString( "15%" ) );
			    params.put( "separator", ":" );
			    result.add( params );
			        
			    params = new HashMap<String, Object>();
			    params.put( "label", "    - Tahun ke-6 dan seterusnya" );
			    params.put( "content", editorUtil.convertToString( "0%" ) );
			    params.put( "separator", ":" );
			    result.add( params ); 	
	        }else if( cepr01030102Form.getLeftPartOfBusinessCd() == 213 || cepr01030102Form.getLeftPartOfBusinessCd() == 216 ){ //SIMAS MAGNA LINK 
	        	params = new HashMap<String, Object>();
	  	        params.put( "label", "    - Tahun ke-1" );  	      
	  	        params.put( "content", editorUtil.convertToString( "50%" ) );
	  	        params.put( "separator", ":" );
	  	        result.add( params );
	  	        
	  	        params = new HashMap<String, Object>();
	  	        params.put( "label", "    - Tahun ke-2" );
	  	        params.put( "content", editorUtil.convertToString( "10%" ) );
	  	        params.put( "separator", ":" );
	  	        result.add( params );
	  	       
	  	        params = new HashMap<String, Object>();
			    params.put( "label", "    - Tahun ke-3-5" );
			    params.put( "content", editorUtil.convertToString( "0%" ) );
			    params.put( "separator", ":" );
			    result.add( params );
			        
			    params = new HashMap<String, Object>();
			    params.put( "label", "    - Tahun ke-6 dan seterusnya" );
			    params.put( "content", editorUtil.convertToString( " -5% (penambahan ke Investasi)" ) );
			    params.put( "separator", ":" );
			    result.add( params ); 	
	        } else if( cepr01030102Form.getLeftPartOfBusinessCd() == 217 || cepr01030102Form.getLeftPartOfBusinessCd() == 218 ){ //SMiLe LINK PRO 100
	        	params = new HashMap<String, Object>();
	  	        params.put( "label", "    - Tahun ke-1" );  	      
	  	        params.put( "content", editorUtil.convertToString( "70%" ) );
	  	        params.put( "separator", ":" );
	  	        result.add( params );
	  	        
	  	        params = new HashMap<String, Object>();
	  	        params.put( "label", "    - Tahun ke-2" );
	  	        params.put( "content", editorUtil.convertToString( "40%" ) );
	  	        params.put( "separator", ":" );
	  	        result.add( params );
	  	       
	  	        params = new HashMap<String, Object>();
			    params.put( "label", "    - Tahun ke-3-5" );
			    params.put( "content", editorUtil.convertToString( "15%" ) );
			    params.put( "separator", ":" );
			    result.add( params );
			        
			    params = new HashMap<String, Object>();
			    params.put( "label", "    - Tahun ke-6 dan seterusnya" );
			    params.put( "content", editorUtil.convertToString( " 0%" ) );
			    params.put( "separator", ":" );
			    result.add( params ); 	
	        }  else if( cepr01030102Form.getLeftPartOfBusinessCd() == 220 || cepr01030102Form.getLeftPartOfBusinessCd() == 224){ //SMiLe LINK PLUS/JEMPOL LINK
	        	params = new HashMap<String, Object>();
	  	        params.put( "label", "    - Tahun ke-1" );  	      
	  	        params.put( "content", editorUtil.convertToString( "50%" ) );
	  	        params.put( "separator", ":" );
	  	        result.add( params );
	  	        
	  	        params = new HashMap<String, Object>();
	  	        params.put( "label", "    - Tahun ke-2 dan seterusnya" );
	  	        params.put( "content", editorUtil.convertToString( "0%" ) );
	  	        params.put( "separator", ":" );
	  	        result.add( params );  
	        }                              
	        params = new HashMap<String, Object>();
	        params.put( "label", "- Biaya Akuisisi "+lblCb+" Top-up Regular dan "+ lblCb + " Top-up Single adalah" );
	        if( cepr01030102Form.getLeftPartOfBusinessCd() == 213 || cepr01030102Form.getLeftPartOfBusinessCd() == 216 ){ //SIMAS MAGNA LINK 
	        	params.put( "content", editorUtil.convertToString( "0 %" ) );
	        } else if( (cepr01030102Form.getLeftPartOfBusinessCd() == 220 && cepr01030102Form.getRightPartOfBusinessCd() < 4 ) || (cepr01030102Form.getLeftPartOfBusinessCd() == 224 && cepr01030102Form.getRightPartOfBusinessCd() < 3) ){ 
	        	params.put( "content", editorUtil.convertToString( "3 %" ) );
	        } else{
	        	params.put( "content", editorUtil.convertToString( "5 %" ) );
	        }
	        params.put( "separator", "" );
	        result.add( params );
        }
        
        params = new HashMap<String, Object>();
        params.put( "label", "- Biaya Administrasi" );
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 200 && (cepr01030102Form.getRightPartOfBusinessCd() == 5 || cepr01030102Form.getRightPartOfBusinessCd() == 6) || cepr01030102Form.getLeftPartOfBusinessCd() == 215 ||
        	cepr01030102Form.getLeftPartOfBusinessCd() == 218	){//SUPERLINK SYARIAH
        	params.put( "content", editorUtil.convertToString( "Rp.27,500.00 *" ) );        	
        }else if( cepr01030102Form.getLeftPartOfBusinessCd() == 200 || cepr01030102Form.getLeftPartOfBusinessCd() == 190 || cepr01030102Form.getLeftPartOfBusinessCd() == 217){  //SMiLe Link 99/BRIDGE Syariah
        	params.put( "content", editorUtil.convertToString( "Rp.27,500.00/US$ 5 per bulan*" ) );        	
        }else if( cepr01030102Form.getLeftPartOfBusinessCd() == 213 || cepr01030102Form.getLeftPartOfBusinessCd() == 220 ){//SIMAS MAGNA LINK
        	params.put( "content", editorUtil.convertToString( "Rp.20,000.00/US$ 2 per bulan*" ) );        	
        }else if( cepr01030102Form.getLeftPartOfBusinessCd() == 216 || cepr01030102Form.getLeftPartOfBusinessCd() == 224){ // SYARIAH
        	params.put( "content", editorUtil.convertToString( "Rp.20,000.00*" ) );   
        }else{
        	// if(cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_IDR_CD )){
             	params.put( "content", editorUtil.convertToString( "Rp.15,000.00/US$ 2 per bulan*" ) );
            // }
           //  else if(cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD )){
           // 	params.put( "content", editorUtil.convertToString( "US$ 2 per bulan*" ) );
           //  } 
        }      
        params.put( "separator", ":" );
        result.add( params );
        
        params = new HashMap<String, Object>();
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 153 || cepr01030102Form.getLeftPartOfBusinessCd() == 160 || cepr01030102Form.getLeftPartOfBusinessCd() == 200 ||
            cepr01030102Form.getLeftPartOfBusinessCd() == 202 || cepr01030102Form.getLeftPartOfBusinessCd() == 215 || cepr01030102Form.getLeftPartOfBusinessCd() == 216 ||
            cepr01030102Form.getLeftPartOfBusinessCd() == 218 || cepr01030102Form.getLeftPartOfBusinessCd() == 224){ //SYARIAH
        	params.put( "label", "- Biaya Asuransi Syariah" );      
        }else{
        	params.put( "label", "- Biaya Asuransi " );   
        }
        if(cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_IDR_CD )){
        	//params.put( "content", "Rp. "+editorUtil.convertToString( biayaAsuransiPokok )+ ",- per bulan**");
        	params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( biayaAsuransiPokok )+ " per bulan**");
        }
        else if(cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD )){
        	//params.put( "content", "US$ "+editorUtil.convertToString( biayaAsuransiPokok )+ " per bulan*");
        	params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( biayaAsuransiPokok )+ " per bulan**");
        }        
        params.put( "separator", ":" );
        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put( "label", "- Biaya Pengelolaan Investasi" );
        if(cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_IDR_CD )){        	
        	 if( cepr01030102Form.getCashFundIdr() != null ){ 
        		 if( cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) > 0 && cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "100" ) ) == 0  ){
        			 params.put( "content", editorUtil.convertToString( "1% per tahun dari dana investasi untuk jenis Cash Fund" ));
        		 }else if( cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) > 0 && cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "100" ) ) < 0 ){
        			 params.put( "content", editorUtil.convertToString( "2% per tahun dari Dana Investasi (Sesuai dengan Jenis Fund yang dipilih) dan 1% per tahun dari dana investasi untuk jenis Cash Fund" ));
        		 }else if( cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) == 0  ){
        			 params.put( "content", editorUtil.convertToString( "2% per tahun dari Dana Investasi (Sesuai dengan Jenis Fund yang dipilih)" ));
        		 }
        	}else{
        		params.put( "content", editorUtil.convertToString( "2% per tahun dari Dana Investasi (Sesuai dengan Jenis Fund yang dipilih)" ));
        	}        	
        }
        else if(cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD )){        	
        	params.put( "content", editorUtil.convertToString( "1.5% per tahun dari Dana Investasi (Sesuai dengan Jenis Fund yang dipilih)" ));
        }       
        params.put( "separator", ":" );        
        result.add( params );
        
        if( cepr01030102Form.getLeftPartOfBusinessCd() != 213 && cepr01030102Form.getLeftPartOfBusinessCd() != 216 && cepr01030102Form.getLeftPartOfBusinessCd() != 224 &&
        	cepr01030102Form.getLeftPartOfBusinessCd() != 217 && cepr01030102Form.getLeftPartOfBusinessCd() != 218 && cepr01030102Form.getLeftPartOfBusinessCd() != 220){ //SIMAS MAGNA LINK SDH DIJELASKAN SENDIRI
	        params = new HashMap<String, Object>();
	        params.put( "label", "- Biaya Penarikan" );
	        params.put( "content", editorUtil.convertToString( "1.25% untuk penarikan yang ke-3 dan seterusnya dalam setahun atau Sebelum 3 bulan polis berjalan" ));       
	        params.put( "separator", ":" );        
	        result.add( params );
        }        
        params = new HashMap<String, Object>();
        params.put( "label", "- Biaya Pengalihan" );
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 213 || cepr01030102Form.getLeftPartOfBusinessCd() == 217 ){ //SIMAS MAGNA LINK
        	params.put( "content", editorUtil.convertToString( "Bebas biaya untuk 5 (lima) kali pengalihan dalam setahun Polis, pengalihan yang ke-6 (enam) dan seterusnya dalam satu tahun Polis dikenakan biaya pengalihan sebesar Rp. 100.000,-/ US$ 10,- yang akan dipotong dari Dana Investasi" ));
        } else if( cepr01030102Form.getLeftPartOfBusinessCd() == 216 || cepr01030102Form.getLeftPartOfBusinessCd() == 218){ //SYARIAH
            	params.put( "content", editorUtil.convertToString( "Bebas biaya untuk 5 (lima) kali pengalihan dalam setahun Polis, pengalihan yang ke-6 (enam) dan seterusnya dalam satu tahun Polis dikenakan biaya pengalihan sebesar Rp. 100.000,- yang akan dipotong dari Dana Investasi" ));
        }else if( cepr01030102Form.getLeftPartOfBusinessCd() == 220 || cepr01030102Form.getLeftPartOfBusinessCd() == 224){
        	params.put( "content", editorUtil.convertToString( "Rp 100.000,- untuk pengalihan dana investasi yang ke-6 dan seterusnya dalam  satu tahun polis" ));
        }else{
        	params.put( "content", editorUtil.convertToString( "2% untuk untuk pengalihan dana investasi yang ke-3 dan seterusnya dalam satu tahun polis" ));    
        }
        params.put( "separator", ":" );        
        result.add( params );

        return result;
    }
    
    public List<Map<String, Object>> getCommonHeaderRincianSmallRowList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040211Business.getCommonHeaderRowList" );
       
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();        
        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
             
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "label", "" );
        params.put( "content", "" );
        result.add( params );
        
        result.addAll( source.getPolicyHolderNameMap() );
        result.addAll( source.getPolicyHolderAgeMap() );
            	
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 200 || cepr01030102Form.getLeftPartOfBusinessCd() == 202 || cepr01030102Form.getLeftPartOfBusinessCd() == 153 || cepr01030102Form.getLeftPartOfBusinessCd() == 160  || cepr01030102Form.getLeftPartOfBusinessCd() == 189
        	|| cepr01030102Form.getLeftPartOfBusinessCd() == 215 || cepr01030102Form.getLeftPartOfBusinessCd() == 216 || cepr01030102Form.getLeftPartOfBusinessCd() == 218 || cepr01030102Form.getLeftPartOfBusinessCd() == 224)// produk produk syariah
        {     	
        	 result.addAll( source.getInsuredNameSyariahMap() );
             result.addAll( source.getInsuredAgeSyariahMap() );
        }else{        	
        	 result.addAll( source.getInsuredNameMap() );
             result.addAll( source.getInsuredAgeMap() );        	
        }              	
        
        return result;
    }
    
    public List<Map<String, Object>> getCommonHeaderRincianSmallRowList( int rider_baru ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040211Business.getCommonHeaderRowList" );       
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();   
        
        if( istr_prop.rider_baru[ rider_baru ] > 0 )
        {       
	        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
	             
	        Map<String, Object> params = new HashMap<String, Object>();
	        params.put( "label", "" );
	        params.put( "content", "" );
	        result.add( params );
	        
	        result.addAll( source.getPolicyHolderNameMap() );
	        result.addAll( source.getPolicyHolderAgeMap() );
	            	
	        if( cepr01030102Form.getLeftPartOfBusinessCd() == 199 || cepr01030102Form.getLeftPartOfBusinessCd() == 202 || cepr01030102Form.getLeftPartOfBusinessCd() == 153 || cepr01030102Form.getLeftPartOfBusinessCd() == 160  || cepr01030102Form.getLeftPartOfBusinessCd() == 189
	        	|| cepr01030102Form.getLeftPartOfBusinessCd() == 215 || cepr01030102Form.getLeftPartOfBusinessCd() == 216 || cepr01030102Form.getLeftPartOfBusinessCd() == 218 || cepr01030102Form.getLeftPartOfBusinessCd() == 224
	        	|| cepr01030102Form.getLeftPartOfBusinessCd() == 200)
	        	// produk produk syariah
	        {     	
	        	 result.addAll( source.getInsuredNameSyariahMap() );
	             result.addAll( source.getInsuredAgeSyariahMap() );
	        }else{        	
	        	 result.addAll( source.getInsuredNameMap() );
	             result.addAll( source.getInsuredAgeMap() );        	
	        }              	
        }
        return result;
    }
    
    public List<Map<String, Object>> getCommonBiayaRincianRider(int rider_baru) throws IOException
    {    	
    	  List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();    	
          N_riders ln_riders = new N_riders();
          List< Mst_proposal > mstProposal = new ArrayList<Mst_proposal>();      
          double ldec_coi;
               if( istr_prop.rider_baru[ rider_baru ] > 0 )
               {                 
                   ldec_coi = of_get_coi_rider_cost( rider_baru, 1, mstProposal );
                       	    	 
	          // Fill content            
		          Map<String, Object> params = new HashMap<String, Object>();
		          if( cepr01030102Form.getLeftPartOfBusinessCd() == 200 || cepr01030102Form.getLeftPartOfBusinessCd() == 160 || cepr01030102Form.getLeftPartOfBusinessCd() == 216 
		        		  || cepr01030102Form.getLeftPartOfBusinessCd() == 215 || cepr01030102Form.getLeftPartOfBusinessCd() == 218 || cepr01030102Form.getLeftPartOfBusinessCd() == 224 || cepr01030102Form.getLeftPartOfBusinessCd() == 199){ 
		        	  params.put( "label", "-	Biaya Asuransi Syariah" ); 		        	  
		          }else{
		        	  params.put( "label", "-	Biaya Asuransi" );
		          }		        
		   //       params.put( "content", editorUtil.convertToString( "Rp."+ ldec_coi+",- per bulan*" ) );
		          params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( ldec_coi )+ " per bulan**");
		          params.put( "separator", ":" );
		          result.add( params );
               }
      return result;      
    }
    
    public double of_get_coi_220( int ai_th )
    {
        double ldec_total = 0, ldec_temp;
        double ldec_rate;
        int li_usia; 
        String li_sex;  //IGA - PROJECT NEW COI
        li_sex = cepr01030101Form.getInsuredSexCd();
        if(li_sex.equals("L")){
        	li_sex = "1".trim();
        }else if(li_sex.equals("P")){
        	li_sex = "0".trim();
        }else{
        	li_sex = "9".trim();
        }
        li_usia = istr_prop.umur_tt + ai_th - 1;
        if(li_usia == 0) li_usia=1;
    
        Map<String, Object> par = new HashMap<String, Object>();
        par.put( "liUsia", li_usia );
        par.put("liSex", li_sex);
        ldec_rate = eproposalManager.selectLdecRateToGetCoi_220( par ).doubleValue();                 
        
        ldec_temp = FormatNumber.round( ( ( ( istr_prop.up / 1000 ) * ldec_rate ) * 0.1 ), 2, istr_prop.kurs_id ); 
        ldec_total += ldec_temp;

        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PA, ai_th );           
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_HCP, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_TPD, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_WP_60_TPD, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PB_25_TPD, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_WP_60_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PB_25_CI_DEATH, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PB_25_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PAYOR_SPOUSE, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_HCP_FAMILY, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_TERM_RIDER, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_WAIVER_TPD_CI, ai_th );     
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_EKA_SEHAT, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_EKA_SEHAT_IL, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_HCP_PROVIDER, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PAYOR_TPD_CI_DEATH, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_LADIES_INS, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_LADIES_HCP, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_LADIES_MED_EXPENSE, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_LADIES_MED_EXPENSE_INNER_LIMIT, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_PAYOR_5_TPD_10_CI, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_WAIVER_5_TPD_10_CI, ai_th );    
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_SCHOLAR, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_BABY, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_EARLY_STAGE_CI99, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_MEDICAL_PLUS, ai_th );
        ldec_total = ldec_total + of_get_coi_rider( Hardcode.AI_JENIS_MEDICAL_EXTRA, ai_th );
        return ldec_total;
    }

	public Istr_prop getIstr_prop() {
		return istr_prop;
	}

	public void setIstr_prop(Istr_prop istr_prop) {
		this.istr_prop = istr_prop;
	}
        
}