package id.co.sinarmaslife.eproposal.product.product01040114;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	:
 * Program Name   		: Cepr01040201Business
 * Description         	: Eka Link Family (159)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Sep 14, 2007 9:11:19 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Ilustration116Business;
import id.co.sinarmaslife.eproposal.common.pb.PbConverter;
import id.co.sinarmaslife.eproposal.common.pb.PbFunction;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.eproposal.model.pb.N_riders;
import id.co.sinarmaslife.eproposal.model.pb.S_biaya;
import id.co.sinarmaslife.eproposal.model.vo.Cepr00000000YieldResultVO;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030104Form;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;

public class Cepr01040114Business extends Cepr01040000Ilustration116Business
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040114Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
        setSekaligusRightPartCdArr( new Integer[]{ Cepr01040114Mapper.X1 } );
        setBerkalaRightPartCdArr( new Integer[]{ Cepr01040114Mapper.X2} );
        setInsuredAgeLimit( 100 );
        setInvestmentTitleArr( new String[] {
            "",
            "EXCELLINK Fixed Income Fund",
            "EXCELLINK Dynamic Fund",
            "EXCELLINK Aggressive Fund",
            "EXCELLINK Secure Dollar Income",
            "EXCELLINK Dynamic Dollar Fund"
        } );
        setInvestmentTitleAndDescrArr( new String[][] {
            { "", "", "" },
            { "", "- EXCELLINK Fixed Income Fund : 100% Fixed Income", "  Penempatan dana pada investasi pendapatan tetap dan instrumen pasar uang." },
            { "", "- EXCELLINK Dynamic Fund : minimum 50% Fixed Income dan maksimum 50% Ekuitas", "  Penempatan dana pada investasi pendapatan tetap, ekuitas serta instrumen pasar uang." },
            { "", "- EXCELLINK Aggressive Fund : maksimum 100% Fixed Income Rupiah atau maksimum 50% Fixed", "  Income US Dollar atau maksimum 100% Ekuitas" },
            { "", "- EXCELLINK Secure Dollar Income Fund : 100% Fixed Income.", null },
            { "", "- EXCELLINK Dynamic Dollar Fund : minimum 50% Fixed Income dan maksimum 50% Ekuitas.", null },
        } );
        
        istr_prop = PbConverter.get_istr_prop( command, insuredAgeLimit );
    }
    
    public Cepr00000000YieldResultVO getInvestmentYield()
    {
        return of_get_rate_ds();
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
        
        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub5" );
        result.add( params );

        return result;
    }
    
    public String[][] getRiderPremiumList( Cepr01030102Form cepr01030102Form ) throws IOException
    {
    	double ldec_coi;
    	
        String[][] riderTambahan = new String[24][2];
        BigDecimal totalRider = new BigDecimal(0);
        
        riderTambahan[0][0] = "Biaya Asuransi Tambahan Perbulan";
        riderTambahan[0][1] = "";

        N_riders ln_riders = new N_riders();
        int j = 1;
        List< Mst_proposal > mstProposal = new ArrayList<Mst_proposal>();
        for( int i = 1; i <= ArrUtil.upperBound( istr_prop.rider_baru ); i++ )
        {
            if( istr_prop.rider_baru[ i ] > 0 )
            {
                ldec_coi = of_get_coi_141_cost( i, 1, mstProposal );
                totalRider = totalRider.add(new BigDecimal(ldec_coi));
                riderTambahan[j][0] = ln_riders.of_nama( i, cepr01030102Form, cepr01030103Form );
                riderTambahan[j][1] = editorUtil.convertToRoundedTwoDigits(ldec_coi);
                j=j+1;
            }
        }
        riderTambahan[0][1] = totalRider.toString();

        return riderTambahan;
    }
    
    public Map<String, Object> getIllustration114Result(int lamaBayar)
    {
        logger.info( "*-*-*-* Cepr01040000Ilustration116Business.getIllustrationResult" );
        IllustrationResultVO result = new IllustrationResultVO();
        IllustrationResultVO result2 = new IllustrationResultVO();
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        Cepr01030101Form cepr01030101Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030101Form();
        Cepr01030104Form cepr01030104Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030104Form();
        CredentialsVO credentialsVO = ( ( Cepr01030000HoldingForm ) command ).getCredentialsVO();
        
        ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        ArrayList<Map<String, Object>> mapList2 = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        Map<String, Object> mapMaster = new HashMap<String, Object>();
        List<TopupDrawVO> topupDrawVOList = cepr01030104Form.getTopupDrawVOList();
        int topupDrawVOListSize = topupDrawVOList.size();
        TopupDrawVO topupDrawVO;
        String premiumTotal;
        String topup;
//        String draw;

        int li_ke = 0, li_bagi = 1000, li_hal = 3;
        double[] ldec_bak;
        double ldec_bawal = 100000;
        double[] ldec_man_non = new double[2 + 1];
        double ldec_bak_tu = 0.05;
        double ldec_bak_tut = 0.05;
        double ldec_akuisisi;
        double ldec_premi_invest;
        double[][] ldec_hasil_invest = new double[5 + 1][3 + 1];
        double[] ldec_tarik = { CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0 };
        double ldec_wdraw, li_persen_wdraw;
        double[] ldec_premi_bulan = new double[12 + 1];
        double[] premi_bulan_sp = new double[12 + 1];
        double  ldec_premi_setahun_sp = 0;
        double ldec_topup;
        double ldec_bass;
        double[][] ldec_bunga = new double[5 + 1][3 + 1];
        double[] ldec_bunga_avg = new double[3 + 1]; //= {0.06, 0.1, 0.08, 0.18, 0.12, 0.25} //{0.09, 0.06, 0.11, 0.15, 0.165, 0.25}  //fixed:0.09, 0.11, 0.15 (0.165); dynamic:0.08, 0.11, 0.18 (0.195)
        double ldec_fee = 0.020075;
        double ldec_coi = 0, ldec_mfc, ldec_sc, ldec_cost, ldec_man_celaka, ldec_temp = 0; //, ldec_man[10+1] = {1, 1, 1, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7}
        boolean[] lb_minus = { CommonConst.DUMMY_FALSE, false, false, false };
        boolean lb_rider = false;
        String ls_sy = "", ls_temp; //ls_dpp = ' dari Premi Pokok', ls_sy = ''
        S_biaya lstr;
        double specialOfferDouble = 0;
//n_riders ln_riders
        double ldec_manfaat, ldec_premi_setahun = 0, ldec_coi24 = 0;
////monthly fix cost
        ldec_mfc = 15000;
        
        //lds_fixed.DataObject = 'd_ilus_128_sum' + trim(string(istr_prop.bunga))
		//lds_temp2.DataObject = 'd_ilus_tarik128'
		Boolean lb_hal_tarik = true;
		if (istr_prop.bisnis_id == 141 || of_cek_rider() <= 0 ){
			li_hal = 4;
		}
		ldec_coi = of_get_coi_141_in_header( 1, 1 );
        /*if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            ldec_mfc = 1.5;
            li_bagi = 1;
        }

        if( cepr01030102Form.getLeftPartOfBusinessCd() == 141 )
        {
            ldec_mfc = 45000;
            if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) ) ldec_mfc = 5;
        }*/

        if( istr_prop.bisnis_id == 115 || istr_prop.bisnis_id == 152 )
        {
            ldec_bak_tut = 0.01;
            ldec_bak_tu = 0.01;
	        ldec_tarik = new double[] { CommonConst.DUMMY_ZERO, 0.05, 0.04, 0.03, 0.02, 0.01 };
        }
        else if( istr_prop.bisnis_id == 138 )
        {
            ldec_bak_tut = 0;
	        ldec_bak_tu = 0;
        }

        for( int i = 1; i <= 12; i++ )
        {
            ldec_premi_bulan[ i ] = 0;
            if( i == 1 ) {
            	ldec_premi_bulan[ i ] = istr_prop.premi * (istr_prop.pct_premi / 100);
            	if( cepr01030102Form.getBasePremium() != null)premi_bulan_sp[i] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
            }
            if( Hardcode.PAY_MODE_CD_TRIWULANAN == cepr01030102Form.getPaymentModeCd() )
            {
                if( i == 4 || i == 7 || i == 10 ) {
                	ldec_premi_bulan[ i ] = istr_prop.premi;
                	if( cepr01030102Form.getBasePremium() != null)premi_bulan_sp[i] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
                }
            }
            else if( Hardcode.PAY_MODE_CD_SEMESTERAN == cepr01030102Form.getPaymentModeCd() )
            {
                if( i == 7 ) {
                	ldec_premi_bulan[ i ] = istr_prop.premi;
                	if( cepr01030102Form.getBasePremium() != null)premi_bulan_sp[i] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
                }
            }
            else if( Hardcode.PAY_MODE_CD_BULANAN == cepr01030102Form.getPaymentModeCd() )
            {
                ldec_premi_bulan[ i ] = istr_prop.premi;
                if( cepr01030102Form.getBasePremium() != null)premi_bulan_sp[i] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
            }
            ldec_premi_setahun += ldec_premi_bulan[ i ];
            ldec_premi_setahun_sp += premi_bulan_sp[ i ];
        }

        for( int i = 1; i <= 3; i++ )
        {
            ldec_hasil_invest[ 1 ][ i ] = 0;
        }

        ldec_manfaat = istr_prop.up;
////Biaya Akuisisi & asumsi penarikan (1 tarik, 1-nya tdk tarik)

        lstr = PbFunction.wf_get_biaya_84( istr_prop, command );
        ldec_bak = lstr.bak;
        ldec_bunga = lstr.bunga;

        int a, b;
        /*if( "01".equals( istr_prop.kurs_id ) )
        {
            a = 1;
            b = 3;
        }
        else
        {
            a = 4;
            b = 5;
        }*/

        for( int i = 1; i <= 3; i++ )
        {
            ldec_bunga_avg[ i ] = 0;
            for( int j = 1; j <= 5; j++ )
            {
                ldec_bunga_avg[ i ] += ldec_bunga[ j ][ i ] * ( istr_prop.fund[ j ] / 100 );
            }
//            logger.info("-*ldec_bunga_avg" + ldec_bunga_avg[ i ]);
        }

        double[] np = new double[4];
        double[] celaka = new double[4];
        ldec_premi_invest = 0;
        int j;
        specialOfferDouble =(ldec_premi_setahun_sp * 0.3);
        for( int i = 1 ; i <= istr_prop.ins_per ; i++){
    	ldec_sc = 0;
    	ldec_wdraw = 0;
    	ldec_topup = 0;
    	ldec_akuisisi = 0;
    	ldec_coi = of_get_coi_141( i );
    	if(i <= ArrUtil.upperBound(lstr.tarik)){ 
    		
    		ldec_wdraw = lstr.tarik[i];
    	}
    	
    	if(i <= ArrUtil.upperBound(lstr.topup))ldec_topup = lstr.topup[i];
    	if(ArrUtil.upperBound(ldec_bak) > i )ldec_akuisisi = ldec_bak[i];
    	//ldec_coi = of_get_coi_120_120(i, lstr_rider);
    	
    	/*if( cepr01030102Form.getLeftPartOfBusinessCd() == 141 )
        {
            if( i == 1 )
            {
                //ass basic dinolin, ass rider tidk 23/08/07
                ldec_temp = of_get_coi_120_basic( i );
                ldec_coi = ldec_coi - ldec_temp;
                //ldec_temp = ldec_temp / 2 //distahunkan /24
                ldec_mfc = 0;
                //ass rider pa, hcp, hcp fam ada (3/9/07)
                for( int k = 1; k <= ArrUtil.upperBound( istr_prop.rider_baru ); k++ )
                {
                	if( !( ( k == 1 ) || ( k == 2 ) || ( k == 11 ) ) )
                    {
                        if( istr_prop.rider_baru[ k ] > 0 )
                        {
                            ldec_coi -= of_get_coi_120_rider( k, 1 );
                            ldec_temp += of_get_coi_120_rider( k, 1 );
                        }
                    }
                    else
                    {
                        //pa, hcp, hcp fam tdk ditunda
                    }
                }
                ldec_temp = ldec_temp / 24;  //distahunkan /24
            }
            else if( i >= 3 && i <= 4 )
            {
                ldec_coi24 = ldec_temp;
            }
            else
            {
                ldec_coi24 = 0;
            }

            if( i >= 2 )
            {
                ldec_mfc = 45000; //30000  16/07/07
                if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) )
                {
                    ldec_mfc = 5;
                }
                //tambahin per 10/8/07
                if( istr_prop.cb_id == 0 )
                {
                    ldec_mfc = 30000;
                    if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) )
                    {
                        ldec_mfc = 4;
                    }
                }
            }
        }*/
    	
    	ldec_cost = (ldec_coi + ldec_mfc);
    	li_persen_wdraw = ldec_wdraw;
    	//
    	j = istr_prop.umur_tt + i;
    		for( int k = 1 ; k <= 3; k++){
    			for(int li_bulan = 1 ; li_bulan <= 12 ; li_bulan++){
    				ldec_premi_invest = 0;
    				if(i <= lamaBayar ){
    					ldec_premi_invest = FormatNumber.round(( (ldec_premi_bulan[li_bulan] * (istr_prop.pct_premi / 100)) * ( 1 - ldec_akuisisi)),2);
    					ldec_premi_invest = FormatNumber.round( ldec_premi_invest + ((ldec_premi_bulan[li_bulan] * (100 - istr_prop.pct_premi) / 100)) * ( 1 - ldec_bak_tu),2);  //topup berkala
    				}
    					if(li_bulan == 1)ldec_premi_invest = FormatNumber.round(ldec_premi_invest + (ldec_topup * ( 1 - ldec_bak_tut)),2);  //topup tunggal
    					if( i == 1 ){    						
    						 if( credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_MNC ) && cepr01030102Form.getSpecialOfferCd() != null
    								 && cepr01030102Form.getSpecialOfferCd().equals( Hardcode.ADDITIONAL_UNIT ) )
    						 {	
    							 if( li_bulan == 1 ) ldec_premi_invest += ( specialOfferDouble );  // additional unit
    						 }
    					}
    					
    					//ldec_hasil_invest[1][k] = FormatNumber.round(( ldec_premi_invest + ldec_hasil_invest[1][k] - ldec_cost) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2);
    					ldec_hasil_invest[1][k] = FormatNumber.round(( ldec_premi_invest + ldec_hasil_invest[1][k] - ldec_cost) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2);
    			}
    			ldec_tarik[k] = 0;
    			if(lb_hal_tarik){
    				if(istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 141){
    					//ldec_tarik[k] = Round(ldec_hasil_invest[1][k] * li_persen_wdraw/100, 2);
    					ldec_tarik[k] = FormatNumber.round(ldec_hasil_invest[1][k] * li_persen_wdraw/100, 2);
    					//ldec_wdraw = Max(0, ldec_tarik[k]);
    					ldec_wdraw = Math.max(0, ldec_tarik[k]);
    				}else if( istr_prop.bisnis_id == 128){
    					//proteksi
    					//th 20: 20%, tiap 5th: 5%, usia 70: 100%
    					if(j < 70){
    						if(i == 20){
    							//ldec_tarik[k] = Round(ldec_hasil_invest[1][k] * 0.2, 2);
    							ldec_tarik[k] = FormatNumber.round(ldec_hasil_invest[1][k] * 0.2, 2);
    						}else if( i > 20 && (i%5) == 0){
    							//ldec_tarik[k] = Round(ldec_hasil_invest[1][k] * 0.05, 2);
    							ldec_tarik[k] = FormatNumber.round(ldec_hasil_invest[1][k] * 0.05, 2);
    						}
    					}else if( j == 70){
    						//ldec_tarik[k] = ldec_hasil_invest[1, k]
    					}
    					//ldec_wdraw = Max(0, ldec_tarik[k]);
    					ldec_wdraw = Math.max(0, ldec_tarik[k]);
    				}else if( istr_prop.bisnis_id == 129){
    					//sejahtera
    					//usia 55: 50%, usia 65: 100%
    					if( j == 55){
    						//ldec_tarik[k] = Round(ldec_hasil_invest[1][k] * 0.5, 2);
    						ldec_tarik[k] = FormatNumber.round(ldec_hasil_invest[1][k] * 0.5, 2);
    					}else if( j == 65){
    						//ldec_tarik[k] = ldec_hasil_invest[1, k]
    					}
    					//ldec_wdraw = Max(0, ldec_tarik[k]);
    					ldec_wdraw = Math.max(0, ldec_tarik[k]);
    				}
    			}
    			ldec_wdraw = FormatNumber.round(ldec_wdraw,2);
    			BigDecimal ldec_wdrawbd = new BigDecimal(ldec_wdraw);
    			
    			ldec_hasil_invest[1][k] = FormatNumber.round(ldec_hasil_invest[1][k] - (new Double(ldec_wdrawbd.toString()) * (1 + ldec_sc)), 2);
    			if( ldec_hasil_invest[ 1 ][ k ] <= 0 )
                {
                    lb_minus[ k ] = true;
                }
    		}

            if( i <= 23 || j == 55 || j == 65 || j == 70 || j == 75 || j == 80 || j == 100 )
            {
                for( int k = 1; k <= 3; k++ )
                {
                	if(j == 100){
                		np[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] + ldec_manfaat ) / li_bagi, 0 );
                	}else {
                		np[ k ] = FormatNumber.round( ldec_hasil_invest[ 1 ][ k ] / li_bagi, 0 );
                	}
                	celaka[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] + ldec_manfaat ) / li_bagi, 0 );
                }

                if( i <= lamaBayar )
                {
                    //premiumTotal = editorUtil.convertToStringWithoutCent( ldec_premi_setahun / li_bagi );
                	premiumTotal = editorUtil.convertToStringWithoutCentAndNillIfNegative( FormatNumber.round( ( ldec_premi_setahun / li_bagi ), 0) + "" );
                }
                else
                {
                    premiumTotal = "";
                }

                if( i < topupDrawVOListSize )
                {
                    // why ( i - 1 )? becoz index in Java start from 0, not like PB programming language
                    topupDrawVO = topupDrawVOList.get( i - 1 );
                    topup = "0";
                    if( topupDrawVO.getYearFlag() != null && topupDrawVO.getYearFlag().equals("true") ){
                    	topup = editorUtil.convertToString( topupDrawVO.getTopupAmount().divide( new BigDecimal( "1000" ) ) );
                    }
//                    draw = editorUtil.convertToString( topupDrawVO.getDrawAmount().divide( new BigDecimal( "1000" ) ) );
                    if( "0".equals( topup ) ) topup = "0.00";
//                    if( "0".equals( draw ) ) draw = "0.00";
                }
                else
                {
                    topup = "0.00";
//                    draw = "0.00";
                }
               
                String valueLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 1 ] ) );
                String valueMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 2 ] ) );
                String valueHi = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 3 ] ) );

                String benefitLow = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 1 ], np[ 1 ] );
                String benefitMid = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 2 ], np[ 2 ] );
                String benefitHi = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 3 ], np[ 3 ] );
                
                if("nil".equals(valueLow)){
                	valueLow = "**";
                }
                if("nil".equals(valueMid)){
                	valueMid = "**";
                }
                if("nil".equals(valueHi)){
                	valueHi = "**";
                }
                if("nil".equals(benefitLow)){
                	benefitLow = "**";
                }
                if("nil".equals(benefitMid)){
                	benefitMid = "**";
                }
                if("nil".equals(benefitHi)){
                	benefitHi = "**";
                }
                
                map = new HashMap<String, Object>();
                
                if( i == 1 ){
                	//REQUEST 02/09/2013@Vito Shadiq : Special Offer= NONE(0) digunakan utk mendisable Special Offer pd Distribusi MNC 
                	if( credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_MNC ) && cepr01030102Form.getSpecialOfferCd() != null
							 && cepr01030102Form.getSpecialOfferCd().equals( Hardcode.ADDITIONAL_UNIT ) ){
                		String specialOffer = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( specialOfferDouble/1000 ) );
                		map.put( "specialOffer", specialOffer );
                	}else{
                		map.put( "specialOffer", "" );
                	}
                }else{
                	map.put( "specialOffer", "" );
                }
                
                map.put( "yearNo", editorUtil.convertToString( i ) );
                map.put( "insuredAge", editorUtil.convertToString( cepr01030101Form.getInsuredAge() + i ) );
                map.put( "premiumTotal", premiumTotal );
                map.put( "topupAssumption", topup );
                map.put( "drawAssumption", "0.00" );
                map.put( "valueLow", valueLow );
                map.put( "valueMid", valueMid );
                map.put( "valueHi", valueHi );
                map.put( "benefitLow", benefitLow );
                map.put( "benefitMid", benefitMid );
                map.put( "benefitHi", benefitHi );
                mapList.add( map );

            }
            if (i <= 25 || (j % 5) == 0 ){
            	Map<String, Object> map2 = new HashMap<String, Object>();
                if( i == 1 ){
                	//REQUEST 02/09/2013@Vito Shadiq : Special Offer= NONE(0) digunakan utk mendisable Special Offer pd Distribusi MNC 
                	if( credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_MNC ) && cepr01030102Form.getSpecialOfferCd() != null
							 && cepr01030102Form.getSpecialOfferCd().equals( Hardcode.ADDITIONAL_UNIT ) ){
                		String specialOffer = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( specialOfferDouble/1000 ) );
                		map2.put( "specialOffer", specialOffer );
                	}else{
                		map2.put( "specialOffer", "" );
                	}
                }else{
                	map2.put( "specialOffer", "" );
                }
            	String drawAssumptionLow = editorUtil.convertToRoundedNoDigit( new BigDecimal( ldec_tarik[ 1 ] / 1000.00 ) );
                String drawAssumptionMid = editorUtil.convertToRoundedNoDigit( new BigDecimal( ldec_tarik[ 2 ] / 1000.00 ) );
                String drawAssumptionHi = editorUtil.convertToRoundedNoDigit( new BigDecimal( ldec_tarik[ 3 ] / 1000.00 ) );
                
                map2.put( "yearNo", editorUtil.convertToString( i ) );
                map2.put( "insuredAge", editorUtil.convertToString( cepr01030101Form.getInsuredAge() + i ) );
                map2.put( "drawAssumptionLow", drawAssumptionLow );
                map2.put( "drawAssumptionMid", drawAssumptionMid );
                map2.put( "drawAssumptionHi", drawAssumptionHi );
                mapList2.add( map2 );
            }
        }

        result.setValidityMsg( lb_minus[ 1 ] ? "Maaf, Proposal yang Anda buat Tidak Layak Jual." : "" );
        result.setIllustrationList( mapList );
        result2.setIllustrationList( mapList2 );
        mapMaster.put("Illustration1", result);
        mapMaster.put("Illustration2", result2);
        return mapMaster;

    }
    
    public List getRiderPremiumList( Cepr01030102Form cepr01030102Form, int usia) throws IOException
    {
    	double ldec_coi;
    	
        //String[][] riderTambahan = new String[3][2];
        //BigDecimal totalRider = new BigDecimal(0);
        
        //riderTambahan[0][0] = "Biaya Asuransi Tambahan Perbulan";
        //riderTambahan[0][1] = "";

        //N_riders ln_riders = new N_riders();
        //int j = 1;
    	List biayaRiderList = new ArrayList();
        	
    	for(int m = 0 ; m < cepr01030102Form.getTermOfPayment(); m++){
    		Map<String, Object> riderParams = new HashMap<String, Object>();
    		if(m < istr_prop.cuti_premi){
				riderParams.put("yearNo", (m+1) + "");
				riderParams.put("insuredAge", usia + "");
    			for( int i = 1; i < ArrUtil.upperBound( istr_prop.rider_baru ); i++ ){
    				if( istr_prop.rider_baru[ i ] > 0 )
    				{
    					ldec_coi = of_get_coi_134( i, m+1 );
    					riderParams.put("rider"+(i-1), editorUtil.convertToRoundedNoDigit(new BigDecimal(ldec_coi * 10)));
    				}else if( istr_prop.rider_baru[ i ] == 0 ){
    					riderParams.put("rider"+(i-1), "-");
    				}
    			}
    			biayaRiderList.add(riderParams);
    			usia = usia +1;
    		}else if(m >= istr_prop.cuti_premi){
				riderParams.put("yearNo", (m+1) + "");
				riderParams.put("insuredAge", usia + "");
				for( int i = 1; i < ArrUtil.upperBound( istr_prop.rider_baru ); i++ ){
					riderParams.put("rider"+(i-1), "-");
				}
				biayaRiderList.add(riderParams);
				usia = usia +1;
    		}
    	}
        	
        //riderTambahan[0][1] = totalRider.toString();

        return biayaRiderList;
    }
    
}
