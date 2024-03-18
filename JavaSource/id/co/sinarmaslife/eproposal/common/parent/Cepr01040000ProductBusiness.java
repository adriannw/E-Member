package id.co.sinarmaslife.eproposal.common.parent;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040000ProductBusiness
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 27, 2008 11:53:44 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.business.Cepr00000000CommonUsedBusiness;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.pb.PbConverter;
import id.co.sinarmaslife.eproposal.common.pb.PbFunction;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.pb.Istr_prop;
import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.eproposal.model.pb.S_biaya;
import id.co.sinarmaslife.eproposal.model.pb.S_medis;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.*;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.ListUtil;
import id.co.sinarmaslife.standard.util.HtmlUtil;
import id.co.sinarmaslife.standard.util.MathUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040000ProductBusiness
{
    protected final Log logger = LogFactory.getLog( getClass() );
    protected EproposalManager eproposalManager;
    protected Cepr00000000EditorUtil editorUtil;

    protected Cepr00000000CommonUsedBusiness commonUsedBusiness;
    protected Integer[] sekaligusRightPartCdArr;
    protected Integer[] berkalaRightPartCdArr;

    protected Istr_prop istr_prop;

    protected Object command;
    protected Cepr01030101Form cepr01030101Form;
    protected Cepr01030102Form cepr01030102Form;
    protected Cepr01030103Form cepr01030103Form;
    protected Cepr01030104Form cepr01030104Form;
    protected Cepr01030105Form cepr01030105Form;
    protected Cepr01030106Form cepr01030106Form;
    protected Cepr01030107Form cepr01030107Form;
    protected Cepr01030108Form cepr01030108Form;
    protected Cepr01030109Form cepr01030109Form;
    protected Cepr01030110Form cepr01030110Form;
    protected Cepr01030111Form cepr01030111Form;
    protected Cepr01030112Form cepr01030112Form;
    protected Cepr01030113Form cepr01030113Form;
    protected Cepr01030114Form cepr01030114Form;
    protected Cepr01031001Form cepr01031001Form;

    public Cepr01040000ProductBusiness( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        logger.info( "*-*-*-* Cepr01040110Business constructor is called ..." );
        this.eproposalManager = eproposalManager;
        this.editorUtil = editorUtil;
        this.commonUsedBusiness = new Cepr00000000CommonUsedBusiness( eproposalManager, editorUtil, command );

        this.command = command;
        this.cepr01030101Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030101Form();
        this.cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        this.cepr01030103Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030103Form();
        this.cepr01030104Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030104Form();
        this.cepr01030105Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030105Form();
        this.cepr01030106Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030106Form();
        this.cepr01030107Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030107Form();
        this.cepr01030108Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030108Form();
        this.cepr01030109Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030109Form();
        this.cepr01030110Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030110Form();
        this.cepr01030111Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030111Form();
        this.cepr01030112Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030112Form();
        this.cepr01030113Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030113Form();
        this.cepr01030114Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030114Form();
        this.cepr01031001Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01031001Form();

        istr_prop = PbConverter.get_istr_prop( command, 0 );
    }

    public Cepr00000000CommonUsedBusiness getCommonUsedBusiness()
    {
        return commonUsedBusiness;
    }

    public void setCommonUsedBusiness( Cepr00000000CommonUsedBusiness commonUsedBusiness )
    {
        this.commonUsedBusiness = commonUsedBusiness;
    }

	public Integer[] getBerkalaRightPartCdArr() {
		return berkalaRightPartCdArr;
	}

	public void setBerkalaRightPartCdArr(Integer[] berkalaRightPartCdArr) {
		this.berkalaRightPartCdArr = berkalaRightPartCdArr;
	}

	public Integer[] getSekaligusRightPartCdArr() {
		return sekaligusRightPartCdArr;
	}

	public void setSekaligusRightPartCdArr(Integer[] sekaligusRightPartCdArr) {
		this.sekaligusRightPartCdArr = sekaligusRightPartCdArr;
	}

    public int of_cek_rider()
    {
        int k = 0;
        for( int i = 1; i <= ArrUtil.upperBound( istr_prop.rider_baru ); i++ )
        {
            k += istr_prop.rider_baru[ i ];
        }
        return k;
    }
    
    public double of_get_tunai( int liUsia , int liTahunKe)
    {
    	double ldec_rate;
    	
    	Map<String, Object> par = new HashMap<String, Object>();
        par.put( "liUsia", liUsia );
        par.put( "liTahunKe", liTahunKe );
        ldec_rate = eproposalManager.selectLdecRateToGetTunai( par ).doubleValue();
    
        return ldec_rate;
    }

    public double of_get_sar( int ar_plan, int ar_lbayar )
    {
        Double result;
        
        if( "120, 127, 128, 129, 141, 202".indexOf( LazyConverter.toString( istr_prop.bisnis_id ) ) > -1 ) {
        	if(cepr01030102Form.getTermOfPayment().intValue() == 5 ){
        		result = 4312.0;
        	}else{
        		result = 7247.0;
        	}
        }
        else{
	        Map<String, Object> sqlParams;
	        sqlParams = new HashMap<String, Object>();
	        sqlParams.put( "ar_plan", ar_plan );
	        sqlParams.put( "kurs_id", cepr01030102Form.getCurrencyCd() );
	        sqlParams.put( "ar_lbayar", ar_lbayar );
	        sqlParams.put( "umur_tt", cepr01030101Form.getInsuredAge() );
	
	        result = eproposalManager.selectSar( sqlParams );
	        if(istr_prop.bisnis_id == 134 && ar_plan == 815 
	        		|| istr_prop.bisnis_id == 134 && ar_plan == 816
	        		|| istr_prop.bisnis_id == 134 && ar_plan == 817){
	        	result = 7.247;
	        	if(istr_prop.kurs_id.equals("02")){
	        		result = 9.162;
	        	}
	        }
        }
        if( result == null ){
        	result = 0.0;
        }
        return result;
    }

    // I got this function from w_print_prop
    public Map<String, Object> of_cek_medis( Object command )
    {
        logger.info( "*-*-*-* Cepr01040110Business.of_cek_medis" );
        Map<String, Object> result = new HashMap<String, Object>();
        List<String> errorMessageList = new ArrayList<String>();
        List<String> ttMessageList = new ArrayList<String>();
        List<String> ppMessageList = new ArrayList<String>();

        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        Cepr01030103Form cepr01030103Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030103Form();
        Map<String, Object> sqlParams;

        double ldec_sar, ldec_temp, ldec_rate;
        Integer li_kali = 1, li_sarid = 0, li_usia, li_ageid, li_nomor;
        String ls_temp, ls_pp = "";
        String ls_medis = "", ls_NKurs;
        boolean lb_ok = true, lb_payor = false, lb_waiver = false;
        //If Pos('167, 163', string(istr_prop.bisnis_id, '000#')) = 0 Then Return False
        ldec_sar = LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() );
        S_medis astr_medis = new S_medis();
        astr_medis.medis_pp = "";
        astr_medis.medis_tt = "";
        astr_medis.sar_pp = 0;
        astr_medis.sar_tt = 0;
//        ls_NKurs = Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() )? "Rp. " : "US$ ";

        if( ArrUtil.toListFromArray( new Integer[]{ 45, 53, 54, 73, 95, 105, 106, 111, 123, 124, 158, 198 } ).contains( istr_prop.bisnis_id ) )
            ldec_sar = 0;

        if( istr_prop.bisnis_id == 167){
            int li_sar[] = { CommonConst.DUMMY_ZERO, 240, 200, 180, 160, 100, 240, 200, 180, 160, 100 };
        	ldec_sar = ldec_sar * li_sar[ cepr01030102Form.getBisnisNoPbVersion() ] / 100;
        }else if( istr_prop.bisnis_id == 173  || istr_prop.bisnis_id == 172){
        	ldec_sar = 0;
        	astr_medis.sar_pp = istr_prop.up;
        	if (istr_prop.bisnis_no > 1 ){
	    		ldec_sar = istr_prop.up * 2;
	    		if ( istr_prop.kurs_id.equals("02") ) ldec_sar = istr_prop.up * 3.5;
	    		astr_medis.sar_pp = ldec_sar;
	    		ldec_sar = 0;
        	}
        }else if( istr_prop.bisnis_id == 181 ){
        	ldec_sar = ldec_sar * 2;
        }

        astr_medis.sar_tt = ldec_sar;

        ls_temp = "Resiko Awal Basic TT : " + editorUtil.convertToMoney( ldec_sar, cepr01030102Form );

        	
        if( of_cek_rider() > 0 )
        {
            if( istr_prop.cb_id == 1 )      //triwulan
                li_kali = 4;
            else if( istr_prop.cb_id == 2 ) //semesteran
                li_kali = 2;
            else if( istr_prop.cb_id == 6 ) //bulanan
                li_kali = 12;

            //ci 50% up 813
            if( istr_prop.rider_baru[4] > 0 ){
            	BigDecimal ciChoose = new BigDecimal("50");
//            	if( cepr01030103Form.getCiChooseCd() != null ){
//            		ciChoose = new BigDecimal(cepr01030103Form.getCiChooseCd());
//            	}
//            	BigDecimal persentase = ciChoose.divide(new BigDecimal("100"));
                ldec_temp = cepr01030103Form.getCiRiderAmount().doubleValue();
                astr_medis.sar_tt += ldec_temp;
                ls_temp += "Resiko Awal CI TT : " + editorUtil.convertToMoney( ldec_temp, cepr01030102Form );
            }
            //pb 25 tpd 815
            if( istr_prop.rider_baru[6] > 0 ){
                lb_payor = true;
                li_nomor = 1;
                if( istr_prop.bisnis_id == 119 ) li_nomor = istr_prop.bisnis_no;
                ldec_rate = of_get_sar( 815, li_nomor );
                ldec_temp = ((istr_prop.premi * istr_prop.pct_premi / 100) * li_kali / 1000) * ldec_rate;
                astr_medis.sar_pp += ldec_temp;
                ls_pp += "Resiko Awal Payor TPD PP : " + editorUtil.convertToMoney( ldec_temp, cepr01030102Form );
            }
            //pb 25 ci/death 817
            if( istr_prop.rider_baru[8] > 0 && !ArrUtil.toListFromArray( new Integer[]{ 120, 127, 128, 129 } ).contains( istr_prop.bisnis_id ) )
            {
                if( !lb_payor )
                {
                    ldec_rate = of_get_sar(817, 1);
                    ldec_temp = ((istr_prop.premi * istr_prop.pct_premi / 100) * li_kali / 1000) * ldec_rate;
                    astr_medis.sar_pp += ldec_temp;
                    ls_pp += "Resiko Awal Payor CI/Death PP : " + editorUtil.convertToMoney( ldec_temp, cepr01030102Form );
                }
            }

            //pb 25 ci 817
            if( istr_prop.rider_baru[9] > 0 && !ArrUtil.toListFromArray( new Integer[]{ 120, 127, 128, 129, 202 } ).contains( istr_prop.bisnis_id ) )
            {
                if( !lb_payor )
                {
                    ldec_rate = of_get_sar(817, 1);
                    ldec_temp = ((istr_prop.premi * istr_prop.pct_premi / 100) * li_kali / 1000) * ldec_rate;
                    astr_medis.sar_pp += ldec_temp;
                    ls_pp += "Resiko Awal Payor CI PP : " + editorUtil.convertToMoney( ldec_temp, cepr01030102Form );
                }
            }

            //payor spouse 815, 6
            if( istr_prop.rider_baru[10] > 0 )
            {
                ldec_rate = of_get_sar(815, 6);
                ldec_temp = ((istr_prop.premi * istr_prop.pct_premi / 100) * li_kali / 1000) * ldec_rate;
                astr_medis.sar_pp += ldec_temp;
                ls_pp += "Resiko Awal Payor Spouse PP : " + editorUtil.convertToMoney( ldec_temp, cepr01030102Form );
            }

            //wp 60tpd 814
            if( istr_prop.rider_baru[5] > 0 )
            {
                lb_waiver = true;
                li_nomor = 1;
                if( istr_prop.bisnis_id == 119 ) li_nomor = istr_prop.bisnis_no;
                ldec_rate = of_get_sar(814, li_nomor);
                ldec_temp = ((istr_prop.premi * istr_prop.pct_premi / 100) * li_kali / 1000) * ldec_rate;
                astr_medis.sar_tt += ldec_temp;
                ls_temp += "Resiko Awal WP TPD TT : " + editorUtil.convertToMoney( ldec_temp, cepr01030102Form );
            }

            //wp 60ci 816
            if( istr_prop.rider_baru[7] > 0 )
            {
                if( !lb_waiver )
                {
                    ldec_rate = of_get_sar(816, 1);
                    ldec_temp = ((istr_prop.premi * istr_prop.pct_premi / 100) * li_kali / 1000) * ldec_rate;
                    astr_medis.sar_tt += ldec_temp;
                    ls_temp += "Resiko Awal WP CI TT : " + editorUtil.convertToMoney( ldec_temp, cepr01030102Form );
                }
            }
            
        	//term
        	if( istr_prop.rider_baru[12] > 0 ){
        		//ldec_temp = istr_prop.up;
        		ldec_temp = cepr01030103Form.getTermRiderAmount().doubleValue();
        		astr_medis.sar_tt += ldec_temp;
        		
        		ls_temp += "Resiko Awal Term TT : " + editorUtil.convertToMoney( ldec_temp, cepr01030102Form ) + "~n";
        	}
        	
        	//payor 25 tpd/ci/death
        	if( istr_prop.rider_baru[18] > 0 && !ArrUtil.toListFromArray( new Integer[]{ 120, 127, 128, 129, 202 } ).contains( istr_prop.bisnis_id ) ) {
        		if ( !lb_payor ) {
        			ldec_rate = of_get_sar(828, 1);
        			ldec_temp = ((istr_prop.premi * istr_prop.pct_premi / 100) * li_kali / 1000) * ldec_rate;
        			astr_medis.sar_pp += ldec_temp;
        			ls_pp += "Resiko Awal Payor 25 TPD/CI/Death PP : " + editorUtil.convertToMoney( ldec_temp, cepr01030102Form ) + "~n";
        		}
        	}
        	
        	//waiver tpd/ci
        	if( istr_prop.rider_baru[17] > 0 ){
        		lb_waiver = true;
        		if( istr_prop.rider_baru[17] == 1) { li_nomor = 55; }
        		else if( istr_prop.rider_baru[17] == 2 ) { li_nomor = 60; }
        		else { li_nomor = 65; }
        		if( istr_prop.bisnis_id == 119 ) li_nomor = istr_prop.bisnis_no;
        		ldec_rate = of_get_sar(827, li_nomor);
        		ldec_temp = ((istr_prop.premi * istr_prop.pct_premi / 100) * li_kali / 1000) * ldec_rate;
        		astr_medis.sar_tt += ldec_temp;
        		ls_temp += "Resiko Awal WP " + li_nomor + " TPD/CI/Death TT : " + editorUtil.convertToMoney( ldec_temp, cepr01030102Form ) + "~n";
        	}
        	
        	//ladies Insurance
        	if( istr_prop.rider_baru[19] > 0 ){
        		ldec_temp = (istr_prop.up * istr_prop.persen_ins / 100);
        		astr_medis.sar_tt += ldec_temp;
        		ls_temp += "Resiko Awal Ladies Insurance TT : " + editorUtil.convertToMoney( ldec_temp, cepr01030102Form ) + "~n";
        	}
        	
        	//Waiver 5/10 TPD/CI
        	if( istr_prop.rider_baru[24] > 0 && istr_prop.umur_tt >= 16 && istr_prop.umur_tt <= 50){
        		Double rate =  0.0;
        		if(cepr01030102Form.getPremiumFurloughYearCd()==5){
        			if(istr_prop.kurs_id.equals("01")){
        				rate = 4312.0;        				
        			}else if(istr_prop.kurs_id.equals("02")){
        				rate = 4630.0;        				
        			}        			
        		}else if(cepr01030102Form.getPremiumFurloughYearCd()==10){
        			if(istr_prop.kurs_id.equals("01")){
        				rate = 7247.0;        				
        			}else if(istr_prop.kurs_id.equals("02")){
        				rate = 8435.0;        				
        			}        			
        		}
        		ldec_temp = ((istr_prop.premi * istr_prop.pct_premi / 100) * li_kali / 1000) * rate;
        		astr_medis.sar_tt += ldec_temp;
        		ls_temp += "Resiko Awal Waiver 5/10 TPD/CI : " + editorUtil.convertToMoney( ldec_temp, cepr01030102Form ) + "~n";
        	}        	
        	
        	//SMiLe ESCI 99 (Early Stage Critical Illness 99)
        	if( istr_prop.rider_baru[27] > 0 ){
        		BigDecimal esci99Choose = new BigDecimal("50");
        		double ldec_min1, ldec_min2, ldec_min3, ldec_kurs;
        		
//            	if( cepr01030103Form.getEsci99ChooseCd() != null ){
//            		esci99Choose = new BigDecimal(cepr01030103Form.getEsci99ChooseCd());
//            	}
            	BigDecimal persentase = cepr01030103Form.getEsci99RiderAmount();
            		            	
        		ldec_min2 = (persentase.doubleValue() * 1.2) + 75000000;
        		ldec_min3 = (persentase.doubleValue() * 1.3) ;
            	
        		ldec_temp = (persentase.doubleValue() * 1.3) ;
        		ldec_min1 = ldec_temp;
        		
        		if(istr_prop.kurs_id.equals("01")){
        			ldec_kurs = 275000000;
        			BigDecimal standard = new BigDecimal("2275000000");
        					
        			if(ldec_temp < standard.doubleValue()){
        				//if ldec_temp > 750000000 then ldec_temp = ldec_temp * 1.2 + 75000000
        				ldec_temp = ldec_temp;
        				ldec_min3 = ldec_temp;
        			}else{
        				if(ldec_temp > new BigDecimal("2000000000").doubleValue()){
        					ldec_temp = istr_prop.up + ldec_kurs;
        				}        							
        				if(ldec_temp > new BigDecimal("2275000000").doubleValue()){
        					ldec_temp = new BigDecimal("2275000000").doubleValue();
        				}
        				ldec_min1 = ldec_temp;
        			}
        		}else{
        			ldec_kurs = 27500;
        			if(ldec_temp < 227500){
        				ldec_temp = ldec_temp;
        			}else{
        				if(ldec_temp > new BigDecimal("200000").doubleValue()){
        					ldec_temp = istr_prop.up + ldec_kurs;
        				}      
        				if(ldec_temp > new BigDecimal("227500").doubleValue()){
        					ldec_temp = new BigDecimal("227500").doubleValue();
        				}
        			}        					
        		}        		
        		ldec_temp = MathUtil.min(ldec_min1, MathUtil.min(ldec_min2, ldec_min3));
        		astr_medis.sar_tt += ldec_temp;
        		ls_temp += "Resiko Awal SMiLe ESCI 99 TT: " + editorUtil.convertToMoney( ldec_temp, cepr01030102Form ) + "~n"; 
        	}
        }
      
        li_usia = istr_prop.umur_tt;
        if(cepr01030101Form.getBabyFlag()==CommonConst.CHECKED_FALSE){
        	 li_usia = istr_prop.umur_tt;
        }else if(cepr01030101Form.getBabyFlag().equalsIgnoreCase(CommonConst.CHECKED_TRUE)){
        	 li_usia = 0;
        } //CR#: NCR/2020/08/019 FLAG VIP (IGA)
        HashMap mapGroup = eproposalManager.selectMstConfig(17, "PROPOSAL", "GET_TABEL_MEDIS_NEW");
		String[] aktif = mapGroup.get("NAME")!=null?mapGroup.get("NAME").toString().split(","):null;
        double li_sar_new;
        String li_kurs_new = null;
        Integer flag_vip = 0;
		boolean aktifValidasi = false;
		if(aktif != null){
			for(String s: aktif){
				if(s.equals("1"))
					aktifValidasi=true;
			}
		}
		if (!aktifValidasi){
			//--> tutup proses medis lama di ganti ke mst_medis_new
	        sqlParams = new HashMap<String, Object>();
	        sqlParams.put( "sar", astr_medis.sar_tt );
	        sqlParams.put( "kurs_id", cepr01030102Form.getCurrencyCd() );
	        li_sarid = eproposalManager.selectIdRangeSar( sqlParams );
	        if( li_sarid == null )
	        {
	            logger.info( "Range SAR Medis Tertanggung Tidak Ada. SAR : " + editorUtil.convertToMoney( astr_medis.sar_tt, cepr01030102Form ) +", Kurs : " + cepr01030102Form.getCurrencyCd() );
	            errorMessageList.add( "Range SAR Medis Tertanggung Tidak Ada. SAR : " + editorUtil.convertToMoney( astr_medis.sar_tt, cepr01030102Form ) +", Kurs : " + cepr01030102Form.getCurrencyCd() );
	        }

	        sqlParams = new HashMap<String, Object>();
	        sqlParams.put( "li_usia", li_usia );
	        li_ageid = eproposalManager.selectIdRangeAge( sqlParams );
	        if( li_ageid == null )
	        {
	            logger.info( "Range Usia Tertanggung Medis Tidak Ada. Usia : " + li_usia );
	            errorMessageList.add( "Range Usia Tertanggung Medis Tidak Ada. Usia : " + li_usia );
	        }

	        ls_medis = of_get_medis( li_ageid, li_sarid, command );
	        if( ls_medis == null )
	        {
	            logger.info( "Jenis Medis Tertanggung Tidak Ada" );
	            errorMessageList.add( "Jenis Medis Tertanggung Tidak Ada" );
	        }

	        ls_temp = li_ageid + "/" + li_sarid + ": " + ls_temp + "\\n";
	        logger.info(  ls_temp + "Total Risiko Awal TT: " + editorUtil.convertToMoney( astr_medis.sar_tt, cepr01030102Form ) );
	        ttMessageList.add( ls_temp + "Total Risiko Awal TT: " + editorUtil.convertToMoney( astr_medis.sar_tt, cepr01030102Form ) + "\\nSertakan Fund Fact Sheet" );
	        //<--done
		}else if (aktifValidasi){
			//--> new proses table medis 
	        li_sar_new = astr_medis.sar_tt;
	        li_kurs_new = cepr01030102Form.getCurrencyCd();
	        
	        if(CommonConst.CHECKED_TRUE.equals(cepr01030102Form.getFlagOfVIP())) {
	           flag_vip = 1;
	        }
	        
	      //Logic penentuan tipe medis regular dan VIP untuk mata uang USD di e-proposal menggunakan logic yang sama seperti ORION--Eirene_Aktuaria
	        if(li_kurs_new.equals("02")){
	        	 li_sar_new =  li_sar_new * 12000;
	        	 li_kurs_new = "01";
	        }
	       
	        ls_medis = of_get_medis_new( li_usia, li_sar_new, li_kurs_new, flag_vip, command );
	        if( ls_medis == null )
	        {
	            logger.info( "Jenis Medis Tertanggung Tidak Ada" );
	            errorMessageList.add( "Jenis Medis Tertanggung Tidak Ada" );
	        }
	        //<-- done
	        ls_temp = li_usia + "/" + li_sar_new + ": " + ls_temp + "\\n";
	        logger.info(  ls_temp + "Total Risiko Awal TT: " + editorUtil.convertToMoney( astr_medis.sar_tt, cepr01030102Form ) );
	        ttMessageList.add( ls_temp + "Total Risiko Awal TT: " + editorUtil.convertToMoney( astr_medis.sar_tt, cepr01030102Form ) + "\\nSertakan Fund Fact Sheet" );

		}
        
        
        
    	if ( istr_prop.kurs_id.equals("01") ){    		
    		   if( (li_usia <= 40 && astr_medis.sar_tt <= 2000000000) || (istr_prop.bisnis_id == 134 && istr_prop.bisnis_no==5 && li_usia>70 && li_usia<81) || (istr_prop.bisnis_id == 215 && li_usia>70 && li_usia<81) ){
    			   ls_medis = "NM";
    		   }
    	}else{
    		 if( li_usia <= 40 && astr_medis.sar_tt <= 250000 || (istr_prop.bisnis_id == 134 && istr_prop.bisnis_no==5 && li_usia>70 && li_usia<81) || (istr_prop.bisnis_id == 215 && li_usia>70 && li_usia<81)){
  			   ls_medis = "NM";
  		   } 
    	}     
    	
        if( ls_medis.equals( "N/A" ) )
        {
            astr_medis.medis_tt = "N/A (Silahkan hubungi Underwriting)";
        }
        else
        {
            astr_medis.medis_tt = "\"" + ls_medis.trim() + "\", jika belum punya polis di MSIG Life Insurance Indonesia";
        }

        if( astr_medis.sar_pp > 0 )
        {
	        li_usia = istr_prop.umur_pp;
	        if (!aktifValidasi){ //CR#: NCR/2020/08/019 FLAG VIP (IGA)
	        	sqlParams = new HashMap<String, Object>();
	            sqlParams.put( "sar", astr_medis.sar_pp );
	            sqlParams.put( "kurs_id", cepr01030102Form.getCurrencyCd() );
	            li_sarid = eproposalManager.selectIdRangeSar( sqlParams );
	            if( li_sarid == null )
	            {
	                logger.info( "Range SAR Medis Pemegang Tidak Ada. SAR : " + astr_medis.sar_pp );
	                ppMessageList.add( "Range SAR Medis Pemegang Tidak Ada. SAR : " + astr_medis.sar_pp );
	            }
	
	            sqlParams = new HashMap<String, Object>();
	            sqlParams.put( "li_usia", li_usia );
	            li_ageid = eproposalManager.selectIdRangeAge( sqlParams );
	            if( li_ageid == null )
	            {
	                logger.info( "*-*-*-* Range Usia Pemegang Medis Tidak Ada. Usia : " + li_usia );
	                ppMessageList.add( "Range Usia Pemegang Medis Tidak Ada. Usia : " + li_usia );
	            }
	            ls_medis = of_get_medis(li_ageid, li_sarid, command );
	            if( ls_medis == null )
	            {
	                logger.info( "Jenis Medis Pemegang Tidak Ada" );
	                ppMessageList.add( "Jenis Medis Pemegang Tidak Ada" );
	            }

	            ls_pp = li_ageid + "/" + li_sarid + ": " + ls_pp + "\\n";
	            logger.info( ls_pp + "Total Risiko Awal PP: " + editorUtil.convertToMoney( astr_medis.sar_pp, cepr01030102Form ) );
	            ppMessageList.add( ls_pp + "Total Risiko Awal PP: " + editorUtil.convertToMoney( astr_medis.sar_pp, cepr01030102Form ) + "\\nSertakan Fund Fact Sheet" );
	            
	        }else if(aktifValidasi){
	        	li_sar_new = astr_medis.sar_pp;
	            ls_medis = of_get_medis_new( li_usia, li_sar_new, li_kurs_new, flag_vip, command );
	            if( ls_medis == null )
	            {
	                logger.info( "Jenis Medis Pemegang Tidak Ada" );
	                ppMessageList.add( "Jenis Medis Pemegang Tidak Ada" );
	            }

	            ls_pp = li_usia + "/" + li_sar_new + ": " + ls_pp + "\\n";
	            logger.info( ls_pp + "Total Risiko Awal PP: " + editorUtil.convertToMoney( astr_medis.sar_pp, cepr01030102Form ) );
	            ppMessageList.add( ls_pp + "Total Risiko Awal PP: " + editorUtil.convertToMoney( astr_medis.sar_pp, cepr01030102Form ) + "\\nSertakan Fund Fact Sheet" );

	        }//DONE
           
            if( ls_medis.equals( "N/A" ) )
            {
                astr_medis.medis_pp = "N/A (Cek Uang Pertanggungan atau Usia)";
            }
            else
            {
                astr_medis.medis_pp = "\"" + ls_medis.trim() + "\", jika belum punya polis di MSIG Life Insurance Indonesia";
            }
        }

        result.put( "errorMessageList", errorMessageList );
        result.put( "ttMessageList", ttMessageList );
        result.put( "ppMessageList", ppMessageList );
        result.put( "medis_tt", astr_medis.medis_tt );
        result.put( "medis_pp", astr_medis.medis_pp );

        return result;
    }




//

//
////astr_medis.medis_tt = Trim(ls_medis) + ' ( Risiko Awal: ' + ls_NKurs + string(astr_medis.sar_tt, '#,##0') + '), jika belum punya polis di AJS'
//if ls_medis = 'N/A' then astr_medis.medis_tt = 'N/A (Cek Uang Pertanggungan atau Usia)' Else &
//	astr_medis.medis_tt = '"' + Trim(ls_medis) + '", jika belum punya polis di AJS'
////
//if astr_medis.sar_pp > 0 Then
//	//
//	li_usia = istr_prop.umur_pp
//
//	  SELECT "EKA"."LST_MEDICAL_RANGE_SAR"."ID_RANGE_SAR"
//		 INTO :li_sarid
//		 FROM "EKA"."LST_MEDICAL_RANGE_SAR"
//		WHERE ( :astr_medis.sar_pp >= EKA."LST_MEDICAL_RANGE_SAR"."SAR_AWAL" ) AND
//				( :astr_medis.sar_pp <= EKA."LST_MEDICAL_RANGE_SAR"."SAR_AKHIR" ) AND
//				( EKA."LST_MEDICAL_RANGE_SAR"."LKU_ID" = :istr_prop.kurs_id )   ;
//	//
//	if sqlca.sqlcode <> 0 Then
//		MessageBox('Alert', 'Range SAR Medis Pemegang Tidak Ada. SAR : ' + string(astr_medis.sar_pp, '#,##0'))
//		lb_ok = False
//	End if
//	//
//	  SELECT "EKA"."LST_MEDICAL_RANGE_AGE"."ID_RANGE_AGE"
//		 INTO :li_ageid
//		 FROM "EKA"."LST_MEDICAL_RANGE_AGE"
//		WHERE ( :li_usia >= EKA."LST_MEDICAL_RANGE_AGE"."USIA_AWAL" ) AND
//				( :li_usia <= EKA."LST_MEDICAL_RANGE_AGE"."USIA_AKHIR" )   ;
//	//
//	if sqlca.sqlcode <> 0 Then
//		MessageBox('Alert', 'Range Usia Pemegang Medis Tidak Ada. Usia : ' + string(li_usia))
//		lb_ok = False
//	End if
//	//
//	ls_medis = of_get_medis(li_ageid, li_sarid)
//	if isnull(ls_medis) then
//		MessageBox('Alert', 'Jenis Medis Pemegang Tidak Ada')
//		lb_ok = False
//	End if
//
//	//astr_medis.medis_pp = Trim(ls_medis) + ' ( Risiko Awal: ' + ls_NKurs + string(astr_medis.sar_pp, '#,##0') + '), jika belum punya polis di AJS'
//	if ls_medis = 'N/A' then astr_medis.medis_pp = 'N/A (Cek Uang Pertanggungan atau Usia)' Else &
//	   astr_medis.medis_pp = '"' + Trim(ls_medis) + '", jika belum punya polis di AJS'
//End if
////as_jenis = ls_medis
//
//Return lb_ok


    private String of_get_medis( Integer ar_ageid, Integer ar_sarid, Object command )
    {
        logger.info( "*-*-*-* Cepr01040110Business.of_get_medis" );
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        List<String> jenisMedicalPrefixList;
        String ls_medis = "";

        Map<String, Object> sqlParams;

        sqlParams = new HashMap<String, Object>();
        sqlParams.put( "li_ageid", ar_ageid );
        sqlParams.put( "li_sarid", ar_sarid );
        sqlParams.put( "kurs_id", cepr01030102Form.getCurrencyCd() );

        // i got this query below from d_jns_medis
        jenisMedicalPrefixList = eproposalManager.selectJenisMedicalPrefix( sqlParams );

        if( jenisMedicalPrefixList != null && jenisMedicalPrefixList.size() > 0 )
        {
            ls_medis = jenisMedicalPrefixList.get( 0 ).trim();
            if( jenisMedicalPrefixList.size() > 1 )
            {
                String ls_medis_ke_2 = jenisMedicalPrefixList.get( 1 ).trim();
                // to swap words so that  "FS+G" become "G+FS" and "FS+H" become "H+FS".
                if( ls_medis_ke_2.length() < ls_medis.length() )
                {     
                   // ls_medis = jenisMedicalPrefixList.get( 1 ).trim() + "+" + ls_medis;
                      ls_medis = jenisMedicalPrefixList.get( 1 ).trim(); 
                }
                else if( ls_medis.length() < ls_medis_ke_2.length() )
                {      
                	// ls_medis = ls_medis + "+" + jenisMedicalPrefixList.get( 1 ).trim();
                	   ls_medis = jenisMedicalPrefixList.get( 0 ).trim(); 
                }
            }
        }
        else
        {
            ls_medis = "N/A";
        }

        return ls_medis;
    }
    
    private String of_get_medis_new( Integer li_usia, double li_sar_new, String li_kurs_new, Integer flag_vip, Object command )
    { //CR#: NCR/2020/08/019 FLAG VIP (IGA)
        logger.info( "*-*-*-* Cepr01040110Business.of_get_medis" );
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        List<String> jenisMedicalPrefixListNew;
        String ls_medis = "";

        Map<String, Object> sqlParams;

        sqlParams = new HashMap<String, Object>();
        sqlParams.put( "li_usia", li_usia );
        sqlParams.put( "li_sar_new", li_sar_new );
        sqlParams.put( "li_kurs_new", li_kurs_new );
        sqlParams.put( "flag_vip", flag_vip );

        // i got this query below from d_jns_medis
        jenisMedicalPrefixListNew = eproposalManager.selectJenisMedicalPrefixNew( sqlParams );

        if( jenisMedicalPrefixListNew != null && jenisMedicalPrefixListNew.size() > 0 )
        {
            ls_medis = jenisMedicalPrefixListNew.get( 0 ).trim();
            if( jenisMedicalPrefixListNew.size() > 1 )
            {
                String ls_medis_ke_2 = jenisMedicalPrefixListNew.get( 1 ).trim();
                // to swap words so that  "FS+G" become "G+FS" and "FS+H" become "H+FS".
                if( ls_medis_ke_2.length() < ls_medis.length() )
                {     
                   // ls_medis = jenisMedicalPrefixList.get( 1 ).trim() + "+" + ls_medis;
                      ls_medis = jenisMedicalPrefixListNew.get( 1 ).trim(); 
                }
                else if( ls_medis.length() < ls_medis_ke_2.length() )
                {      
                	// ls_medis = ls_medis + "+" + jenisMedicalPrefixList.get( 1 ).trim();
                	   ls_medis = jenisMedicalPrefixListNew.get( 0 ).trim(); 
                }
            }
        }
        else
        {
            ls_medis = "N/A";
        }

        return ls_medis;
    }

    public List<String> getMedicalMsgBox()
    {
    
        Map<String, Object> medicalResult = of_cek_medis( command );
        List ttMessageList = ( List ) medicalResult.get( "ttMessageList" );
        List ppMessageList = ( List ) medicalResult.get( "ppMessageList" );

        List<String> messageBoxList = new ArrayList<String>();
        messageBoxList.addAll( ListUtil.addNothingIfEmpty( HtmlUtil.genMsgStrInJsBox( ttMessageList ) ) );
        messageBoxList.addAll( ListUtil.addNothingIfEmpty( HtmlUtil.genMsgStrInJsBox( ppMessageList ) ) );
        
        return messageBoxList;
    }
    
    public String getTipeMedis(Integer age)
    {
        logger.info( "*-*-*-* Cepr01040000UlinkBusiness.getTipeMedis" );
        String tipeMedis = null;
        
        if((age.intValue()<=60 && cepr01030102Form.getCurrencyCd().equals("01") && cepr01030102Form.getTotalSumInsured().doubleValue() <= 500000000.0) ||
        		(age.intValue()<=60 && cepr01030102Form.getCurrencyCd().equals("02") && cepr01030102Form.getTotalSumInsured().doubleValue() <= 55600.0)){
        	tipeMedis = "NM";
        }
        if(age.intValue()>=20 && age.intValue()<=70){
        	if((cepr01030102Form.getTotalSumInsured().doubleValue() > 2500000000.0 && cepr01030102Form.getCurrencyCd().equals("01") && cepr01030102Form.getTotalSumInsured().doubleValue() <= 5000000000.0) || 
        			(cepr01030102Form.getTotalSumInsured().doubleValue() > 278000.0 && cepr01030102Form.getCurrencyCd().equals("02") && cepr01030102Form.getTotalSumInsured().doubleValue() <= 556000.0))tipeMedis = "G";
        	else if((cepr01030102Form.getTotalSumInsured().doubleValue() > 5000000000.0 && cepr01030102Form.getCurrencyCd().equals("01") && cepr01030102Form.getTotalSumInsured().doubleValue() <= 10000000000.0)|| 
        			(cepr01030102Form.getTotalSumInsured().doubleValue() > 556000.0 && cepr01030102Form.getCurrencyCd().equals("02") && cepr01030102Form.getTotalSumInsured().doubleValue() <= 1111000.0))tipeMedis = "G+FS";
        	else if((cepr01030102Form.getTotalSumInsured().doubleValue() > 10000000000.0 && cepr01030102Form.getCurrencyCd().equals("01")) || 
        			(cepr01030102Form.getTotalSumInsured().doubleValue() > 1111000.0 && cepr01030102Form.getCurrencyCd().equals("02")))tipeMedis = "H+FS";
        }
        
        if(age.intValue() <= 19){
        	if( cepr01030102Form.getCurrencyCd().equals("01")){// Rupiah
        		if(cepr01030102Form.getTotalSumInsured().doubleValue() > 500000000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 750000000.0)tipeMedis = "A";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 750000000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 2500000000.0)tipeMedis = "B";
        	}else if( cepr01030102Form.getCurrencyCd().equals("02")){// Dollar
        		if(cepr01030102Form.getTotalSumInsured().doubleValue() > 55600.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 83300.0)tipeMedis = "A";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 83300.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 278000.0)tipeMedis = "B";
        	}
        }else if( age.intValue() >= 20 && age.intValue() <= 30 ){
        	if( cepr01030102Form.getCurrencyCd().equals("01")){// Rupiah
        		if(cepr01030102Form.getTotalSumInsured().doubleValue() > 500000000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 1000000000.0)tipeMedis = "NM";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 1000000000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 2500000000.0)tipeMedis = "D";
        	}else if( cepr01030102Form.getCurrencyCd().equals("02")){// Dollar
        		if(cepr01030102Form.getTotalSumInsured().doubleValue() > 55600.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 111000.0)tipeMedis = "NM";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 111000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 278000.0)tipeMedis = "D";
        	}
        }else if( age.intValue() >= 31 && age.intValue() <= 40 ){
        	if( cepr01030102Form.getCurrencyCd().equals("01")){// Rupiah
        		if(cepr01030102Form.getTotalSumInsured().doubleValue() > 500000000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 750000000.0)tipeMedis = "NM";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 750000000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 1000000000.0)tipeMedis = "C";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 1000000000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 2500000000.0)tipeMedis = "E";
        	}else if( cepr01030102Form.getCurrencyCd().equals("02")){// Dollar
        		if(cepr01030102Form.getTotalSumInsured().doubleValue() > 55600.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 83300.0)tipeMedis = "NM";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 83300.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 111000.0)tipeMedis = "C";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 111000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 278000.0)tipeMedis = "E";
        	}
        }else if( age.intValue() >= 41 && age.intValue() <= 50 ){
        	if( cepr01030102Form.getCurrencyCd().equals("01")){// Rupiah
        		if(cepr01030102Form.getTotalSumInsured().doubleValue() > 500000000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 750000000.0)tipeMedis = "NM";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 750000000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 1000000000.0)tipeMedis = "D";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 1000000000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 2500000000.0)tipeMedis = "E";
        	}else if( cepr01030102Form.getCurrencyCd().equals("02")){// Dollar
        		if(cepr01030102Form.getTotalSumInsured().doubleValue() > 55600.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 83300.0)tipeMedis = "NM";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 83300.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 111000.0)tipeMedis = "D";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 111000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 278000.0)tipeMedis = "E";
        	}
        }else if( age.intValue() >= 51 && age.intValue() <= 60 ){
        	if( cepr01030102Form.getCurrencyCd().equals("01")){// Rupiah
        		if(cepr01030102Form.getTotalSumInsured().doubleValue() > 500000000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 750000000.0)tipeMedis = "D";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 750000000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 1000000000.0)tipeMedis = "E";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 1000000000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 2500000000.0)tipeMedis = "F";
        	}else if( cepr01030102Form.getCurrencyCd().equals("02")){// Dollar
        		if(cepr01030102Form.getTotalSumInsured().doubleValue() > 55600.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 83300.0)tipeMedis = "D";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 83300.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 111000.0)tipeMedis = "E";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 111000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 278000.0)tipeMedis = "F";
        	}
        }else if( age.intValue() >= 61 && age.intValue() <= 70 ){
        	if( cepr01030102Form.getCurrencyCd().equals("01")){// Rupiah
        		if(cepr01030102Form.getTotalSumInsured().doubleValue() <= 25000000.0)tipeMedis = "NM";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 25000000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 250000000.0)tipeMedis = "C";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 250000000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 750000000.0)tipeMedis = "D";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 750000000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 1000000000.0)tipeMedis = "E";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 1000000000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 2500000000.0)tipeMedis = "F";
        	}else if( cepr01030102Form.getCurrencyCd().equals("02")){// Dollar
        		if(cepr01030102Form.getTotalSumInsured().doubleValue() <= 2800.0)tipeMedis = "NM";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 2800.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 27800.0)tipeMedis = "C";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 27800.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 83300.0)tipeMedis = "D";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 83300.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 111000.0)tipeMedis = "E";
        		else if(cepr01030102Form.getTotalSumInsured().doubleValue() > 111000.0 && cepr01030102Form.getTotalSumInsured().doubleValue() <= 278000.0)tipeMedis = "F";
        	}
        }
        return tipeMedis;
    }
    
    public double of_premi_rider( Integer ai_jenis, Integer ai_th, boolean ab_single )
    {
        logger.info( "*-*-*-* Cepr01040000ProductBusiness.of_premi_rider" );

        Double rate = 0.0;
        return rate;
    }
    
    public List<String> getEkaSehatMsgBox()
    {
        List<String> messageBoxList = new ArrayList<String>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) || CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatExtraFlag()) ){
        	 messageBoxList.add("Lihat Ketentuan Underwriting Eka Sehat di VIEW MEDIS");
        }else{
        	 messageBoxList.addAll( ListUtil.addNothingIfEmpty( "" ) );
        }
        return messageBoxList;
    }
    
    public List<String> getEkaSehatILMsgBox()
    {
        List<String> messageBoxList = new ArrayList<String>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatInnerLimitFlag() ) ){
//        	 messageBoxList.add("Lihat Ketentuan Underwriting Eka Sehat di VIEW MEDIS");
        }else{
        	 messageBoxList.addAll( ListUtil.addNothingIfEmpty( "" ) );
        }
        return messageBoxList;
    }
    
    public Map<String, Object> of_premi_rider( Object command ){
    	Map<String, Object> result = new HashMap<String, Object>();
    	
    	return result;
    }
    
    public List<Map<String, Object>> of_get_Extra_Premi(){
    	//Set Label Header Extra (COI/Mortalita/Premi) Atau Extra JOB
    	String content = "";
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	 
    	if(cepr01030102Form.getExtraPremium() == null) cepr01030102Form.setExtraPremium(new BigDecimal(0));
    	if(cepr01030102Form.getExtraJob() == null) cepr01030102Form.setExtraJob(new BigDecimal(0));
    	
    	if(cepr01030102Form.getExtraPremium().compareTo(new BigDecimal(0))>=1 && cepr01030102Form.getExtraJob().compareTo(new BigDecimal(0))==0){
    		 content =  editorUtil.convertToString(cepr01030102Form.getExtraPremium()+"%");
    	}else if(cepr01030102Form.getExtraJob().compareTo(new BigDecimal(0))>=1 && cepr01030102Form.getExtraPremium().compareTo(new BigDecimal(0))==0){  
    		content = editorUtil.convertToString( cepr01030102Form.getExtraJob()+" o/oo");
    	}else if(cepr01030102Form.getExtraJob().compareTo(new BigDecimal(0))>=1 && cepr01030102Form.getExtraPremium().compareTo(new BigDecimal(0))>=1){  
    		 content = editorUtil.convertToString(  cepr01030102Form.getExtraPremium()+"% dan " ) + editorUtil.convertToString( cepr01030102Form.getExtraJob()+" o/oo");
    	}
    	
    	if(cepr01030102Form.getExtraPremium().compareTo(new BigDecimal(0))>=1 || cepr01030102Form.getExtraJob().compareTo(new BigDecimal(0))>=1){
    		 Map params = new HashMap<String, Object>();
    		 params.put( "label", "Extra COI" );
    		 params.put( "content", content );
    		 result.add( params );
    	}   	
    	
    	return result;
    }
       
}
