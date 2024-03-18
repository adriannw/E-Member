package id.co.sinarmaslife.eproposal.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01010101Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 6, 2008 4:07:41 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.pb.PbFunction;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.pb.Istr_prop;
import id.co.sinarmaslife.eproposal.model.pb.S_biaya;
import id.co.sinarmaslife.eproposal.model.pb.S_ekaSehat;
import id.co.sinarmaslife.eproposal.model.pb.S_ekaSehatInnerLimit;
import id.co.sinarmaslife.eproposal.model.pb.S_hcpf;
import id.co.sinarmaslife.eproposal.model.vo.Cepr00000000YieldResultVO;
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01050000Form;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;

public class Cepr01050000Business
{
	protected Istr_prop istr_prop;
    protected final Log logger = LogFactory.getLog( getClass() );
    
    protected EproposalManager eproposalManager;
    protected Cepr00000000EditorUtil editorUtil;

    public Cepr01050000Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil )
    {
        this.eproposalManager = eproposalManager;
        this.editorUtil = editorUtil;
    }
    
    public IllustrationResultVO getIllustration191Result(BigDecimal up, Cepr01050000Form cepr01050000Form, Integer insuredAge, double fixIncome, double dynamic, double aggresive, BigDecimal kombinasi)
    {
    	String[] namaPlan = {null, "SMiLe CERDAS"};
    	istr_prop = new Istr_prop();
    	
    	istr_prop.biaya_ass = 0;
    	istr_prop.fund = new double[]{0.0, fixIncome, dynamic, aggresive, 0.0, 0.0};
    	istr_prop.bisnis_id = 191;
    	istr_prop.kurs_id = "01";
        istr_prop.tgl_prop = eproposalManager.selectNowDate();
    	//istr_prop.tgl_prop = eproposalManager.selectNowDate();
    	istr_prop.umur_pp = insuredAge;
    	istr_prop.umur_tt = insuredAge;
    	istr_prop.up = LazyConverter.toDouble(up);
    	istr_prop.nama_plan = namaPlan;
    	istr_prop.premi = cepr01050000Form.getPremium();
    	istr_prop.bisnis_no = 1;
    	istr_prop.bunga = -1;
    	istr_prop.cb_id = 6;
    	istr_prop.cuti_premi = 10;
    	istr_prop.ins_per = 80 - insuredAge;
    	istr_prop.pa_risk = 1;
    	istr_prop.pct_premi = LazyConverter.toDouble( kombinasi );
    	S_ekaSehat ekaSehatTemp = new S_ekaSehat();
    	ekaSehatTemp.changed = false;
    	ekaSehatTemp.nama = new String[]{null, null, null, null, null};
    	ekaSehatTemp.peserta = 0;
    	ekaSehatTemp.tgl = new Date[]{null, null, null, null, null };
    	ekaSehatTemp.usia = new int[]{0, 0, 0, 0, 0};
    	istr_prop.eka_sehat = ekaSehatTemp;
    	
    	S_hcpf hcpfTemp = new S_hcpf();
    	hcpfTemp.changed = false;
    	hcpfTemp.nama = new String[]{null, null, null, null, null};
    	hcpfTemp.peserta = 0;
    	hcpfTemp.tgl = new Date[]{null, null, null, null, null };
    	hcpfTemp.usia = new int[]{0, 0, 0, 0, 0};
    	istr_prop.hcpf = hcpfTemp;
    	
    	S_ekaSehatInnerLimit ekaSehatInnerLimitTemp = new S_ekaSehatInnerLimit();
    	ekaSehatInnerLimitTemp.changed = false;
    	ekaSehatInnerLimitTemp.nama = new String[]{null, null, null, null, null};
    	ekaSehatInnerLimitTemp.peserta = 0;
    	ekaSehatInnerLimitTemp.tgl = new Date[]{null, null, null, null, null };
    	ekaSehatInnerLimitTemp.usia = new int[]{0, 0, 0, 0, 0};
    	istr_prop.ekaSehatInnerLimit = ekaSehatInnerLimitTemp;
    	
    	List<TopupDrawVO> topupDrawVOListTemp = new ArrayList<TopupDrawVO>();
    	for( int n = 0 ; n < 50 ; n ++ ){
    		topupDrawVOListTemp.add(new TopupDrawVO(n+1, null, BigDecimal.ZERO, BigDecimal.ZERO));
    	}
    	cepr01050000Form.setTopupDrawVOList(topupDrawVOListTemp);
        IllustrationResultVO result = new IllustrationResultVO();

        ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        List<TopupDrawVO> topupDrawVOList = cepr01050000Form.getTopupDrawVOList();
        int topupDrawVOListSize = topupDrawVOList.size();
        TopupDrawVO topupDrawVO;
        String premiumTotal;
        String topup;
        String draw;

        int li_ke = 0, li_bagi = 1000, li_hal = 3;
        double[] ldec_bak ;
        double[] ldec_hasil_ppokok = new double[3 + 1];
        double[] ldec_basic_premium_bd = new double[3 + 1];
        double[] ldec_topup_bd = new double[3 + 1];
        double[] ldec_basic_cash_value = new double[3 + 1];
        double[] ldec_hasil_ptu = new double[3 + 1];
        double[] ldec_fph = {CommonConst.DUMMY_ZERO, 0, 0, 0.9, 0.85, 0.8};
        double[] ldec_bpp = {CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0, 0, 0.1, 0.1, 0.1, 0.5, 0, 0, 0, 0, 0.25};
        double[] ldec_surr_charge = {CommonConst.DUMMY_ZERO, 0.6, 0.5, 0.4, 0.3, 0.2 };
        double ldec_bawal = 100000;
        double[] ldec_man_non = new double[2 + 1];
        double ldec_bak_tu = 0.05;
        double ldec_bak_tut = 0.05;
        double ldec_akuisisi;
//        double ldec_premi_invest;
        double[][] ldec_hasil_invest = new double[5 + 1][3 + 1];
        double[] ldec_tarik = { CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0 };
        double ldec_wdraw;
        double[] ldec_premi_bulan = new double[12 + 1];
        double[] ldec_premi_ppokok_bulan = new double[12 + 1];
        double ldec_topup;
        double ldec_bass;
        double[][] ldec_bunga = new double[5 + 1][3 + 1];
        double[] ldec_bunga_avg = new double[3 + 1]; //= {0.06, 0.1, 0.08, 0.18, 0.12, 0.25} //{0.09, 0.06, 0.11, 0.15, 0.165, 0.25}  //fixed:0.09, 0.11, 0.15 (0.165); dynamic:0.08, 0.11, 0.18 (0.195)
        double ldec_fee = 0.020075;
        double ldec_coi = 0, ldec_premi_ppokok, ldec_mfc, ldec_sc, ldec_cost, ldec_ppokok, ldec_ptu, ldec_aph, ldec_ff, ldec_man_celaka, ldec_temp = 0; //, ldec_man[10+1] = {1, 1, 1, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7}
        boolean[] lb_minus = { CommonConst.DUMMY_FALSE, false, false, false };
        boolean lb_rider = false;
        String ls_sy = "", ls_temp, bonus = ""; //ls_dpp = ' dari Premi Pokok', ls_sy = ''
        S_biaya lstr;
//n_riders ln_riders
        double ldec_manfaat, ldec_premi_setahun = 0, ldec_premi_ppokok_setahun = 0, ldec_coi24 = 0;
////monthly fix cost
        ldec_mfc = 27500;
        //ldec_mfc = 15000;
        ldec_premi_ppokok = istr_prop.premi * (istr_prop.pct_premi/100);
        if(istr_prop.kurs_id.equals("02")){ 
        	ldec_mfc = 2.75;
        	//ldec_mfc = 2;
        	li_bagi = 1;
        }
        for( int i = 1; i <= 12; i++ )
        {
            ldec_premi_bulan[ i ] = 0;
            ldec_premi_ppokok_bulan[ i ] = 0;
            if( i == 1 ) 
            {
            	ldec_premi_bulan[ i ] = istr_prop.premi;
            	ldec_premi_ppokok_bulan[ i ] = ldec_premi_ppokok;
            }
            ldec_premi_bulan[ i ] = istr_prop.premi;
            ldec_premi_ppokok_bulan[ i ] = ldec_premi_ppokok;
            ldec_premi_setahun += ldec_premi_bulan[ i ];
            ldec_premi_ppokok_setahun += ldec_premi_ppokok_bulan[ i ];
        }
        //
        for(int i = 1 ; i <= 3 ; i++){
    		ldec_hasil_invest[1][i] = 0;
    		ldec_hasil_ppokok[i] = 0;
    		ldec_hasil_ptu[i] = 0;
    		//ldec_pre_pp[i] = 0
    		//ldec_pre_tu[i] = 0
        }
        //
        ldec_manfaat = istr_prop.up;
        //    Biaya Akuisisi & asumsi penarikan (1 tarik, 1-nya tdk tarik)
        lstr = PbFunction.wf_get_biaya_191(istr_prop,cepr01050000Form);
        ldec_bak = lstr.bak;
    	ldec_bunga = lstr.bunga;
    	int a, b;
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

        for( int i = 1; i <= 3; i++ )
        {
            ldec_bunga_avg[ i ] = 0;
            for( int j = a; j <= b; j++ )
            {
                ldec_bunga_avg[ i ] += ldec_bunga[ j ][ i ] * ( istr_prop.fund[ j ] / 100 );
            }
        }
        

        

       /* for( int i = 1; i <= 3; i++ )
        {
            ldec_hasil_invest[ 1 ][ i ] = 0;
        }*/

        //ldec_manfaat = istr_prop.up;
////Biaya Akuisisi & asumsi penarikan (1 tarik, 1-nya tdk tarik)

        lstr = PbFunction.wf_get_biaya_191(istr_prop, cepr01050000Form);
        ldec_bak = lstr.bak;
        ldec_bunga = lstr.bunga;


        double[] np = new double[4];
        double[] celaka = new double[4];

        int j;
        for( int i = 1; i <= istr_prop.ins_per; i++ )
        {
//        	surrender charge
        	ldec_sc = 0;
        	ldec_wdraw = 0;
        	ldec_topup = 0;
        	ldec_coi = of_get_coi_basic(i);
        	ldec_akuisisi = 0;
        	
        	//if i <= Upperbound(lstr.tarik) Then	ldec_wdraw = lstr.tarik[i]
//            if( i <= ArrUtil.upperBound( lstr.tarik ) ) ldec_wdraw = lstr.tarik[ i ];
            if( i <= ArrUtil.upperBound( lstr.topup ) ) ldec_topup = lstr.topup[ i ];
        	if(ArrUtil.upperBound(ldec_bak) > i )ldec_akuisisi = ldec_bak[i];
        	if(i <= 5) ldec_sc = ldec_tarik[i];
        	//
        	for(int k = 1 ; k <= 3; k++){
        		if(i <= ArrUtil.upperBound(lstr.tarik)) ldec_wdraw = lstr.tarik[i];
        		for(int li_bulan = 1 ; li_bulan <= 12 ; li_bulan++){
        			ldec_cost = (ldec_coi + ldec_mfc);
        			ldec_ppokok = 0;
        			ldec_ptu = 0;
        			ldec_ff = 0;
        			ldec_aph = 0;
        			if(i <= istr_prop.cuti_premi){
        				ldec_ppokok = ldec_premi_bulan[li_bulan] * (istr_prop.pct_premi / 100)* ( 1 - ldec_akuisisi);
        				ldec_ptu = (ldec_premi_bulan[li_bulan] * (100 - istr_prop.pct_premi) / 100);
        			}
        			if(li_bulan == 1) ldec_ptu += ldec_topup;
        			//ldec_hasil_ppokok[k] = (ldec_ppokok + ldec_hasil_ppokok[k]) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12)));
        			ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2);
        			if(i == 1){
        				logger.info("***-------ldec_bunga_avg[k] "+ldec_bunga_avg[k]);
    				}
        			//ldec_ppokok = ldec_ppokok * ((1 + ldec_bunga_avg[k])^(1/12))
        			//ldec_hasil_ppokok[k] = ldec_hasil_ppokok[k] * ((1 + ldec_bunga_avg[k])^(1/12)) + ldec_ppokok
        			ldec_hasil_ptu[k] = (ldec_ptu + ldec_hasil_ptu[k]) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12)));
        			//if k = 1 and i < 3 then messagebox(string(i) + '/' + string(li_bulan), string(ldec_hasil_ppokok[k], '#,##0.00'))
        			if(i == 1 && li_bulan == 1){ 
        				//ldec_pre_pp[k] = ldec_hasil_ppokok[k]
        				//ldec_pre_tu[k] = ldec_hasil_ptu[k]
        			}
        			if(i == istr_prop.ins_per && li_bulan == 12) ldec_cost = 0;
        			if( "01".equals(istr_prop.kurs_id) ){ // perhitungan biaya pokok( foundation fee )
            	    	if(ldec_hasil_ptu[k]>0.0) 
            	    		{
            	    			ldec_ff = (ldec_hasil_ptu[k] * 0.03 / 12);
            	    		}
            	    	if(i <= 8) ldec_ff += (ldec_hasil_ppokok[k] * 0.03 / 12);
        			}else{
        				if(ldec_hasil_ptu[k]>0.0) 
        				{
        					ldec_ff = (ldec_hasil_ptu[k] * 0.04 / 12);
        				}
            	    	if(i <= 8) ldec_ff += (ldec_hasil_ppokok[k] * 0.04 / 12);
        			}
        	    	//premium holiday
        	    	if(i <= 5 && istr_prop.cuti_premi <= 5){ 
        	    		if(i > istr_prop.cuti_premi) ldec_aph = ldec_fph[i] / (1 - ldec_fph[i]) * (ldec_cost + ldec_ff);
        	    	}
        	    	ldec_temp = ldec_hasil_ptu[k] - (ldec_cost + ldec_ff + ldec_aph);
        	    	//if k = 2 and i < 3 then messagebox(string(i) + '/' + string(li_bulan), string(ldec_hasil_ppokok[k], '#,##0.00') + ' / ' + string(ldec_hasil_ptu[k], '#,##0.00') + ' / ' + string(ldec_cost, '#,##0.00') + ' / ' + string(ldec_ff, '#,##0.00'))
        	    	//if k = 2 and i < 3 then messagebox(string(i) + '/' + string(li_bulan), string(ldec_ptu, '#,##0.00') + ' / ' + string(ldec_hasil_ptu[k], '#,##0.00') + ' / ' + string(ldec_cost, '#,##0.00') + ' / ' + string(ldec_ff, '#,##0.00'))
        	    	if(ldec_temp < 0){
        	    		ldec_hasil_ppokok[k] += ldec_temp;
        	    		ldec_hasil_ptu[k] = 0;
        	    	}else{
        	    		ldec_hasil_ptu[k] = ldec_temp;
        	    	}
        	    	
           	    	// perhitungan pengurangan bonus
//        	    	if(li_bulan == 12 && i <= 15){
//        	    		double bonusValue = ldec_premi_ppokok_setahun * ldec_bpp[i];
//        	    		if(i <= 2){ 
//        	    			bonusValue = 0;
//        	    		}else{
//        	    			if(i <= 5) bonusValue = Math.min(bonusValue, ldec_hasil_ptu[k]);
//        	    				ldec_temp = ldec_hasil_ptu[k] - bonusValue;
//        	    			if(ldec_temp < 0){
//        	    				ldec_hasil_ppokok[k] += ldec_temp;
//        	    				ldec_hasil_ptu[k] = 0;
//        	    			}else{
//        	    				ldec_hasil_ptu[k] = ldec_temp;
//        	    			}
//        	    		} 
//        	    	}
        	    	//if k = 2 and i < 3 then messagebox(string(i) + '/' + string(li_bulan), string(ldec_ptu, '#,##0.00') + ' / ' + string(ldec_hasil_ptu[k], '#,##0.00') + ' / ' + string(ldec_cost, '#,##0.00') + ' / ' + string(ldec_ff, '#,##0.00'))
        	    	if(ldec_wdraw > 0 && li_bulan == 12 ){
        	    		//messagebox(string(i) + '/' + string(li_bulan), string(ldec_ptu, '#,##0.00') + ' / ' + string(ldec_hasil_ptu[k], '#,##0.00') + ' / ' + string(ldec_cost, '#,##0.00') + ' / ' + string(ldec_ff, '#,##0.00'))
        	    		if(i <= 2){ 
        	    			ldec_wdraw = 0;
        	    		}else{
        	    			if(i <= 5) ldec_wdraw = Math.min(ldec_wdraw, ldec_hasil_ptu[k]);
        	    				ldec_temp = ldec_hasil_ptu[k] - ldec_wdraw;
        	    			if(ldec_temp < 0){
        	    				ldec_hasil_ppokok[k] += ldec_temp;
        	    				ldec_hasil_ptu[k] = 0;
        	    			}else{
        	    				ldec_hasil_ptu[k] = ldec_temp;
        	    			}
        	    		}
        	    	}
        		}
        		ldec_hasil_invest[1][k] = ldec_hasil_ppokok[k] + ldec_hasil_ptu[k];
        		
        	//ldec_hasil_invest[1, k] -= (ldec_wdraw * (1 + ldec_sc));
        	}

            //j = cepr01030101Form.getInsuredAge() + i;
        	j = istr_prop.umur_tt + i;
            if( i <= 23 || j == 55 || j == 65 || j == 75 || j == 80 || j == 100 )
            {
                for( int k = 1; k <= 3; k++ )
                {
                    np[ k ] = FormatNumber.round( ldec_hasil_invest[ 1 ][ k ] / li_bagi, 0 );
                    celaka[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] + ldec_manfaat ) / li_bagi, 0 );
                }

                if( i <= istr_prop.cuti_premi )
                {
                    premiumTotal = editorUtil.convertToStringWithoutCent( ldec_premi_setahun / li_bagi );
                }
                else
                {
                    premiumTotal = "";
                }

                if( i < topupDrawVOListSize )
                {
                    // why ( i - 1 )? becoz index in Java start from 0, not like PB programming language
                    topupDrawVO = topupDrawVOList.get( i - 1 );
                    topup = editorUtil.convertToString( topupDrawVO.getTopupAmount().divide( new BigDecimal( "1000" ) ) );
                    draw = editorUtil.convertToString( topupDrawVO.getDrawAmount().divide( new BigDecimal( "1000" ) ) );
                    if( "0".equals( topup ) ) topup = "0.00";
                    if( "0".equals( draw ) ) draw = "0.00";
                }
                else
                {
                    topup = "0.00";
                    draw = "0.00";
                }
                BigDecimal bonusBD = BigDecimal.ZERO;
                if( i <= 15 ) {
                	double bonusValue = ( ldec_premi_ppokok_setahun * ldec_bpp[i]);
                	bonusBD = new BigDecimal(bonusValue);
                	bonus = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal(bonusValue) );
                }else{
                	bonus = "0";
                }
                if("0".equals(bonus))bonus = null;
                BigDecimal valueLowBigDecimal = new BigDecimal( np[ 1 ] ).multiply(new BigDecimal(1000));
                BigDecimal valueMidBigDecimal = new BigDecimal( np[ 2 ] ).multiply(new BigDecimal(1000));
                BigDecimal valueHiBigDecimal = new BigDecimal( np[ 3 ] ).multiply(new BigDecimal(1000));
                
                String valueLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 1 ] ) );
                String valueMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 2 ] ) );
                String valueHi = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 3 ] ) );

                String benefitLow = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 1 ], np[ 1 ] );
                String benefitMid = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 2 ], np[ 2 ] );
                String benefitHi = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 3 ], np[ 3 ] );

                if(i == 1){
                	cepr01050000Form.setPolisRendah1(valueLowBigDecimal);
                	cepr01050000Form.setPolisSedang1(valueMidBigDecimal);
                	cepr01050000Form.setPolisTinggi1(valueHiBigDecimal);
                	cepr01050000Form.setBonus1(bonusBD);
                }
                if(i == 5){
                	cepr01050000Form.setPolisRendah5(valueLowBigDecimal);
                	cepr01050000Form.setPolisSedang5(valueMidBigDecimal);
                	cepr01050000Form.setPolisTinggi5(valueHiBigDecimal);
                	cepr01050000Form.setBonus5(bonusBD);
                }
                if(i == 10){
                	cepr01050000Form.setPolisRendah10(valueLowBigDecimal);
                	cepr01050000Form.setPolisSedang10(valueMidBigDecimal);
                	cepr01050000Form.setPolisTinggi10(valueHiBigDecimal);
                	cepr01050000Form.setBonus10(bonusBD);
                }
                if(i == 15){
                	cepr01050000Form.setPolisRendah15(valueLowBigDecimal);
                	cepr01050000Form.setPolisSedang15(valueMidBigDecimal);
                	cepr01050000Form.setPolisTinggi15(valueHiBigDecimal);
                	cepr01050000Form.setBonus15(bonusBD);
                }
                
                map = new HashMap<String, Object>();
                map.put( "yearNo", editorUtil.convertToString( i ) );
                map.put( "insuredAge", editorUtil.convertToString( insuredAge + i ) );
                map.put( "premiumTotal", premiumTotal );
                map.put( "topupAssumption", topup );
                map.put( "drawAssumption", draw );
                map.put( "valueLow", valueLow );
                map.put( "valueMid", valueMid );
                map.put( "valueHi", valueHi );
                map.put( "benefitLow", benefitLow );
                map.put( "benefitMid", benefitMid );
                map.put( "benefitHi", benefitHi );
                map.put( "bonus", bonus );
                mapList.add( map );

            }
        }
        if( istr_prop.ins_per <= 0 )// jika tertanggung umur nya di atas 80
        {
        	cepr01050000Form.setPolisRendah1(BigDecimal.ZERO);
        	cepr01050000Form.setPolisSedang1(BigDecimal.ZERO);
        	cepr01050000Form.setPolisTinggi1(BigDecimal.ZERO);
        	cepr01050000Form.setBonus1(BigDecimal.ZERO);
        	cepr01050000Form.setPolisRendah5(BigDecimal.ZERO);
        	cepr01050000Form.setPolisSedang5(BigDecimal.ZERO);
        	cepr01050000Form.setPolisTinggi5(BigDecimal.ZERO);
        	cepr01050000Form.setBonus5(BigDecimal.ZERO);
        	cepr01050000Form.setPolisRendah10(BigDecimal.ZERO);
        	cepr01050000Form.setPolisSedang10(BigDecimal.ZERO);
        	cepr01050000Form.setPolisTinggi10(BigDecimal.ZERO);
        	cepr01050000Form.setBonus10(BigDecimal.ZERO);
        	cepr01050000Form.setPolisRendah15(BigDecimal.ZERO);
        	cepr01050000Form.setPolisSedang15(BigDecimal.ZERO);
        	cepr01050000Form.setPolisTinggi15(BigDecimal.ZERO);
        	cepr01050000Form.setBonus15(BigDecimal.ZERO);
        }else if( istr_prop.ins_per < 5 ){// jika tertanggung umur nya di atas 75
        	cepr01050000Form.setBonus1(BigDecimal.ZERO);
        	cepr01050000Form.setPolisRendah5(BigDecimal.ZERO);
        	cepr01050000Form.setPolisSedang5(BigDecimal.ZERO);
        	cepr01050000Form.setPolisTinggi5(BigDecimal.ZERO);
        	cepr01050000Form.setBonus5(BigDecimal.ZERO);
        	cepr01050000Form.setPolisRendah10(BigDecimal.ZERO);
        	cepr01050000Form.setPolisSedang10(BigDecimal.ZERO);
        	cepr01050000Form.setPolisTinggi10(BigDecimal.ZERO);
        	cepr01050000Form.setBonus10(BigDecimal.ZERO);
        	cepr01050000Form.setPolisRendah15(BigDecimal.ZERO);
        	cepr01050000Form.setPolisSedang15(BigDecimal.ZERO);
        	cepr01050000Form.setPolisTinggi15(BigDecimal.ZERO);
        	cepr01050000Form.setBonus15(BigDecimal.ZERO);
        }else if( istr_prop.ins_per < 10 ){// jika tertanggung umur nya di atas 70
        	cepr01050000Form.setPolisRendah10(BigDecimal.ZERO);
        	cepr01050000Form.setPolisSedang10(BigDecimal.ZERO);
        	cepr01050000Form.setPolisTinggi10(BigDecimal.ZERO);
        	cepr01050000Form.setBonus10(BigDecimal.ZERO);
        	cepr01050000Form.setPolisRendah15(BigDecimal.ZERO);
        	cepr01050000Form.setPolisSedang15(BigDecimal.ZERO);
        	cepr01050000Form.setPolisTinggi15(BigDecimal.ZERO);
        	cepr01050000Form.setBonus15(BigDecimal.ZERO);
        }else if( istr_prop.ins_per < 15 ){// jika tertanggung umur nya di atas 65
        	cepr01050000Form.setPolisRendah15(BigDecimal.ZERO);
        	cepr01050000Form.setPolisSedang15(BigDecimal.ZERO);
        	cepr01050000Form.setPolisTinggi15(BigDecimal.ZERO);
        	cepr01050000Form.setBonus15(BigDecimal.ZERO);
        }

        result.setValidityMsg( lb_minus[ 1 ] ? "Maaf, Proposal yang Anda buat Tidak Layak Jual." : "" );
        result.setIllustrationList( mapList );
        return result;

    }
    
    public double of_get_coi_basic( int ai_th )
    {
        logger.info( "*-*-*-* Cepr01040201Business.of_get_coi_basic" );
        double ldec_total = 0, ldec_temp;
        double ldec_rate;
        int li_usia;
        li_usia = istr_prop.umur_tt + ai_th - 1;

        Map<String, Object> par = new HashMap<String, Object>();
        par.put( "liUsia", li_usia );
        ldec_rate = eproposalManager.selectLdecRateToGetCoiBasic( par ).doubleValue();

        ldec_temp = FormatNumber.round( ( ( istr_prop.up / 1000 ) * ldec_rate ) * 0.1, 2 );
        ldec_total += ldec_temp;

        logger.info( "*-*-*-* ldec_total = " + ldec_total );
        return ldec_total;
    }    
    
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
    
    public Cepr00000000YieldResultVO of_get_rate_ds( BigDecimal up, Cepr01050000Form cepr01050000Form, Integer insuredAge, double fixIncome, double dynamic, double aggresive )
    {
        logger.info( "*-*-*-* Cepr01040201Business.of_get_rate_ds" );
        Cepr00000000YieldResultVO result = new Cepr00000000YieldResultVO();
        ArrayList<Map<String, Object>> yieldList = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        int i;
        S_biaya lstr;

        lstr = PbFunction.wf_get_biaya_191(istr_prop, cepr01050000Form);
        int a, b;
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
            if( i == 1 ){ cepr01050000Form.setFixIncome(new BigDecimal(annualYieldMid).toString()+"%");}
            if( i == 2 ){ cepr01050000Form.setDynamic(new BigDecimal(annualYieldMid).toString()+"%");}
            if( i == 3 ){ cepr01050000Form.setAggresive(new BigDecimal(annualYieldMid).toString()+"%");}
            

            params = new HashMap<String, Object>();
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
    
}
