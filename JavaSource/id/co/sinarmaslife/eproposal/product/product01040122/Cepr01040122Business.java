package id.co.sinarmaslife.eproposal.product.product01040122;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040122Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 4, 2008 9:20:54 AM
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
import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Ilustration134Business;
import id.co.sinarmaslife.eproposal.common.pb.PbConverter;
import id.co.sinarmaslife.eproposal.common.pb.PbFunction;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.eproposal.model.pb.S_biaya;
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030104Form;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.FormatNumber;

public class Cepr01040122Business extends Cepr01040000Ilustration134Business
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040122Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
        setSekaligusRightPartCdArr( new Integer[]{ } );
        setBerkalaRightPartCdArr( new Integer[]{ Cepr01040122Mapper.X2 } );
        setInsuredAgeLimit( 80 );
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
        if( currentIdx != 0 )
        {
        	params = new HashMap<String, Object>();
        	params.put( "pageCode", "sub4" );
        	result.add( params );
        }

        return result;
    }
    
    public String getRiderHcpList()
    {
        //List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	String biayaHcp = "";
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFlag() ) )
        {
            double benefitAmount = 0;
            // todo
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                benefitAmount = cepr01030103Form.getHcpCd() * 100000;
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	//benefitAmount = cepr01030103Form.getHcpCd() * 10;
                benefitAmount = cepr01030103Form.getHcpCd()/3 * 10;
            }

            biayaHcp = editorUtil.convertToString( benefitAmount ) + " per hari\n";
            biayaHcp = biayaHcp + editorUtil.convertToString( benefitAmount * 2 ) + " per hari\n";
            biayaHcp = biayaHcp + editorUtil.convertToString( benefitAmount * 2 ) + " per hari";
        }
        
        return biayaHcp;
    }
    
    public List<Map<String, Object>> getCommonHeaderRowList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getCommonHeaderRowList" );

        List sekaligusRightPartCdList = ArrUtil.toListFromArray( sekaligusRightPartCdArr );
        List berkalaRightPartCdList = ArrUtil.toListFromArray( berkalaRightPartCdArr );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        // Fill header content from CommonHeaderDataSource
        result.addAll( source.getPolicyHolderNameMap() );
        result.addAll( source.getPolicyHolderAgeMap() );
        result.addAll( source.getInsuredNameMap() );
        result.addAll( source.getInsuredAgeMap() );
        result.addAll( source.getTermOfContractWithLimitMap( insuredAgeLimit ) );

        Map<String, Object> params;
            params = new HashMap<String, Object>();
            params.put( "label", "Nama Tertanggung" );
            params.put( "content", cepr01030101Form.getInsuredName() );
            result.add( params );
            
            params = new HashMap<String, Object>();
            params.put( "label", "Usia Tertanggung" );
            params.put( "content", cepr01030101Form.getInsuredAge() + " Tahun" );
            result.add( params );
            
            params = new HashMap<String, Object>();
            params.put( "label", "Masa Pertanggungan" );
            params.put( "content", cepr01030102Form.getTermOfContract() + " Tahun" );
            result.add( params );
            
            params = new HashMap<String, Object>();
            params.put( "label", "Premi Pokok" );
            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd(cepr01030102Form.getBasePremium()) );
            result.add( params );
            
            if(!cepr01030102Form.getTopUpPremium().toString().equals("0")){
            	params = new HashMap<String, Object>();
                params.put( "label", "Premi Top Up" );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getTopUpPremium() ) +".00"+ " (" + ( new BigDecimal( "100" ).subtract( cepr01030102Form.getPremiumCombinationCd() ) ).toString() + "%)" );
                result.add( params );

                params = new HashMap<String, Object>();
                params.put( "label", "" );
                params.put( "content", "------------------------" );
                result.add( params );

                params = new HashMap<String, Object>();
                params.put( "label", "Total Premi Sekaligus" );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() )+".00" );
                result.add( params );
            }
            
            if(!cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().toString().equals("0")){
            	params = new HashMap<String, Object>();
                params.put( "label", "Premi Top Up Single" );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount() )+".00");
                result.add( params );
            }

            params = new HashMap<String, Object>();
            params.put( "label", "Uang Pertanggungan (UP)" );
            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd(cepr01030102Form.getTotalSumInsured()) );
            result.add( params );
        //}

        //result.addAll( source.getTotalSumInsuredMap() );
        //result.addAll( source.getPolicyHolderMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_pp" ) ) );
        //result.addAll( source.getInsuredMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_tt" ) ) );

        return result;
    }
    
    public List<Map<String, Object>> getCommonHeaderSmallRowList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getCommonHeaderSmallRowList" );

        List sekaligusRightPartCdList = ArrUtil.toListFromArray( sekaligusRightPartCdArr );
        List berkalaRightPartCdList = ArrUtil.toListFromArray( berkalaRightPartCdArr );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        // Fill header content from CommonHeaderDataSource
        result.addAll( source.getInsuredNameMap() );
        result.addAll( source.getInsuredAgeMap() );
        result.addAll( source.getTermOfContractWithLimitMapInTwoRows( insuredAgeLimit ) );

        Map<String, Object> params;
        if( sekaligusRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
            params = new HashMap<String, Object>();
            params.put( "label", "Premi Pokok Sekaligus" );
            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) );
            result.add( params );
        }
        else if( berkalaRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
        	params = new HashMap<String, Object>();
        	  if(cepr01030102Form.getPaymentModeCd().toString().equals("3")){
              	params.put( "label", "Premi Pokok Tahunan" );
              }else if(cepr01030102Form.getPaymentModeCd().toString().equals("6")){
              	params.put( "label", "Premi Pokok Bulanan" );
              }else if(cepr01030102Form.getPaymentModeCd().toString().equals("2")){
              	params.put( "label", "Premi Pokok Semesteran" );
              }else if(cepr01030102Form.getPaymentModeCd().toString().equals("1")){
              	params.put( "label", "Premi Pokok Triwulanan" );
              }
            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getBasePremium() ) + " (" + cepr01030102Form.getPremiumCombinationCd().toString() + "%)" );
            result.add( params );
            
            if(!cepr01030102Form.getTopUpPremium().toString().equals("0")){
            	params = new HashMap<String, Object>();
            	   if(cepr01030102Form.getPaymentModeCd().toString().equals("3")){
                   	params.put( "label", "Premi Top Up Berkala Tahunan" );
                   }else if(cepr01030102Form.getPaymentModeCd().toString().equals("6")){
                   	params.put( "label", "Premi Top Up Berkala Bulanan" );
                   }else if(cepr01030102Form.getPaymentModeCd().toString().equals("2")){
                   	params.put( "label", "Premi Top Up Berkala Semesteran" );
                   }else if(cepr01030102Form.getPaymentModeCd().toString().equals("1")){
                   	params.put( "label", "Premi Top Up Berkala Triwulanan" );
                   }
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getTopUpPremium() ) + " (" + ( new BigDecimal( "100" ).subtract( cepr01030102Form.getPremiumCombinationCd() ) ).toString() + "%)" );
                result.add( params );

                params = new HashMap<String, Object>();
                params.put( "label", "" );
                params.put( "content", "------------------------" );
                result.add( params );

                params = new HashMap<String, Object>();
                params.put( "label", "Total Premi Tahunan" );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) );
                result.add( params );
            }
            
            if(!cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().toString().equals("0")){
            	params = new HashMap<String, Object>();
                params.put( "label", "Premi Top Up Single" );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount() ));
                result.add( params );
            }

            params = new HashMap<String, Object>();
            params.put( "label", "Asumsi cuti premi setelah" );
            params.put( "content", "tahun ke " + editorUtil.convertToString( cepr01030102Form.getPremiumFurloughYear() ) );
            result.add( params );
        }

        result.addAll( source.getTotalSumInsuredMap() );

        return result;
    }
    
    public IllustrationResultVO getIllustrationResult()
    {
        logger.info( "*-*-*-* Cepr01040000Ilustration134Business.getIllustrationResult" );
        IllustrationResultVO result = new IllustrationResultVO();
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        Cepr01030101Form cepr01030101Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030101Form();
        Cepr01030104Form cepr01030104Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030104Form();

        ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        List<TopupDrawVO> topupDrawVOList = cepr01030104Form.getTopupDrawVOList();
        int topupDrawVOListSize = topupDrawVOList.size();
        TopupDrawVO topupDrawVO;
        String premiumTotal;
        String topup;
        String draw;

        int li_ke = 0, li_bagi = 1000, li_hal = 3;
        double[] ldec_bak ;
        double[] ldec_hasil_ppokok = new double[3 + 1];
        double[] ldec_hasil_ptu = new double[3 + 1];
        double[] ldec_fph = {CommonConst.DUMMY_ZERO, 0, 0, 0.9, 0.85, 0.8};
        double ldec_bawal = 100000;
        double[] ldec_man_non = new double[2 + 1];
        double ldec_bak_tu = 0.05;
        double ldec_bak_tut = 0.05;
        double ldec_akuisisi;
        double ldec_premi_invest;
        double[][] ldec_hasil_invest = new double[5 + 1][3 + 1];
        double[] ldec_tarik = { CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0 };
        double ldec_wdraw;
        double[] ldec_premi_bulan = new double[12 + 1];
        double ldec_topup;
        double ldec_bass;
        double[][] ldec_bunga = new double[5 + 1][3 + 1];
        double[] ldec_bunga_avg = new double[3 + 1]; //= {0.06, 0.1, 0.08, 0.18, 0.12, 0.25} //{0.09, 0.06, 0.11, 0.15, 0.165, 0.25}  //fixed:0.09, 0.11, 0.15 (0.165); dynamic:0.08, 0.11, 0.18 (0.195)
        double ldec_fee = 0.020075;
        double ldec_coi = 0, ldec_mfc, ldec_sc, ldec_cost, ldec_ppokok, ldec_ptu, ldec_aph, ldec_ff, ldec_man_celaka, ldec_temp = 0; //, ldec_man[10+1] = {1, 1, 1, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7}
        boolean[] lb_minus = { CommonConst.DUMMY_FALSE, false, false, false };
        boolean lb_rider = false;
        String ls_sy = "", ls_temp; //ls_dpp = ' dari Premi Pokok', ls_sy = ''
        S_biaya lstr;
//n_riders ln_riders
        double ldec_manfaat, ldec_premi_setahun = 0, ldec_coi24 = 0;
////monthly fix cost
        ldec_mfc = 27500;
        //ldec_mfc = 15000;
        
        if(istr_prop.kurs_id.equals("02")){ 
        	ldec_mfc = 2.75;
        	//ldec_mfc = 2;
        	li_bagi = 1;
        }
        for( int i = 1; i <= 12; i++ )
        {
            ldec_premi_bulan[ i ] = 0;
            if( i == 1 ) ldec_premi_bulan[ i ] = istr_prop.premi;
            if( Hardcode.PAY_MODE_CD_TRIWULANAN == cepr01030102Form.getPaymentModeCd() )
            {
                if( i == 4 || i == 7 || i == 10 ) ldec_premi_bulan[ i ] = istr_prop.premi;
            }
            else if( Hardcode.PAY_MODE_CD_SEMESTERAN == cepr01030102Form.getPaymentModeCd() )
            {
                if( i == 7 ) ldec_premi_bulan[ i ] = istr_prop.premi;
            }
            else if( Hardcode.PAY_MODE_CD_BULANAN == cepr01030102Form.getPaymentModeCd() )
            {
                ldec_premi_bulan[ i ] = istr_prop.premi;
            }
            ldec_premi_setahun += ldec_premi_bulan[ i ];
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
        lstr = PbFunction.wf_get_biaya_84( istr_prop, command );
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

        lstr = PbFunction.wf_get_biaya_84( istr_prop, command );
        ldec_bak = lstr.bak;
        ldec_bunga = lstr.bunga;
        ldec_bunga[1][1] = 0.09;
        ldec_bunga[1][2] = 0.26;
        ldec_bunga[1][3] = 0.45;

        double[] np = new double[4];
        double[] celaka = new double[4];

        int j;
        //for( int i = 1; i <= istr_prop.ins_per; i++ )
        for( int i = 1; i <= 5; i++ )
        {
//        	surrender charge
        	//ldec_sc = 0;
        	//ldec_wdraw = 0;
        	ldec_topup = 0;
        	//ldec_coi = of_get_coi_basic(i);
        	
        	//if i <= Upperbound(lstr.tarik) Then	ldec_wdraw = lstr.tarik[i]
        	if(i <= ArrUtil.upperBound(lstr.topup))	ldec_topup = lstr.topup[i];
        	//If Upperbound(ldec_bak) > i Then ldec_akuisisi = ldec_bak[i]
        	//if(i <= 5) ldec_sc = ldec_tarik[i];
        	//
        	//ldec_cost = (ldec_coi + ldec_mfc);
        	
        	for(int k = 1 ; k <= 3; k++){
        		ldec_premi_invest = ((istr_prop.premi * ( 1 - ldec_bak[1] )) + (istr_prop.topup * ( 1 - ldec_bak_tu ))) * Math.pow(( 1 + ldec_bunga[1][k]) , i);
        		ldec_hasil_invest[1][k] = ldec_premi_invest;
        		//logger.info(ldec_bunga[1][k]);
        		logger.debug( ldec_bunga[1][k] );
        	}

            //j = cepr01030101Form.getInsuredAge() + i;
        	j = istr_prop.umur_tt + i;
            if( i <= 21 || j == 55 || j == 65 || j == 75 || j == 80 || j == 100 )
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
                    topup = "0";
                    draw = "0";
                    if( topupDrawVO.getYearFlag() != null && topupDrawVO.getYearFlag().equals("true") ){
	                    topup = editorUtil.convertToString( topupDrawVO.getTopupAmount().divide( new BigDecimal( "1000" ) ) );
	                    draw = editorUtil.convertToString( topupDrawVO.getDrawAmount().divide( new BigDecimal( "1000" ) ) );
                    }
                    if( "0".equals( topup ) ) topup = "0.00";
                    if( "0".equals( draw ) ) draw = "0.00";
                }
                else
                {
                    topup = "0.00";
                    draw = "0.00";
                }

                String valueLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 1 ] ) );
                String valueMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 2 ] ) );
                String valueHi = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 3 ] ) );

                String benefitLow = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 1 ], np[ 1 ] );
                String benefitMid = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 2 ], np[ 2 ] );
                String benefitHi = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 3 ], np[ 3 ] );

                map = new HashMap<String, Object>();
                map.put( "yearNo", editorUtil.convertToString( i ) );
                map.put( "insuredAge", editorUtil.convertToString( cepr01030101Form.getInsuredAge() + i ) );
                map.put( "premiumTotal", premiumTotal );
                map.put( "topupAssumption", topup );
                map.put( "drawAssumption", draw );
                map.put( "valueLow", valueLow );
                map.put( "valueMid", valueMid );
                map.put( "valueHi", valueHi );
                map.put( "benefitLow", benefitLow );
                map.put( "benefitMid", benefitMid );
                map.put( "benefitHi", benefitHi );
                mapList.add( map );

            }
        }

        result.setValidityMsg( lb_minus[ 1 ] ? "Maaf, Proposal yang Anda buat Tidak Layak Jual." : "" );
        result.setIllustrationList( mapList );
        return result;

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
        List< Mst_proposal > mstProposal = new ArrayList<Mst_proposal>();
    	for(int m = 0 ; m < cepr01030102Form.getTermOfPayment(); m++){
    		Map<String, Object> riderParams = new HashMap<String, Object>();
    		if(m < istr_prop.cuti_premi){
    			usia = usia +1;
				riderParams.put("yearNo", (m+1) + "");
				riderParams.put("insuredAge", usia + "");
    			for( int i = 1; i < ArrUtil.upperBound( istr_prop.rider_baru ); i++ ){
    				if( istr_prop.rider_baru[ i ] > 0 )
    				{
    					ldec_coi = of_get_coi_134_cost( i, m+1, mstProposal );
    					riderParams.put("rider"+(i-1), editorUtil.convertToRoundedNoDigit(new BigDecimal(ldec_coi * 10)));
    				}else if( istr_prop.rider_baru[ i ] == 0 ){
    					riderParams.put("rider"+(i-1), "-");
    				}
    			}
    			biayaRiderList.add(riderParams);
    		}else if(m >= istr_prop.cuti_premi){
    			usia = usia +1;
				riderParams.put("yearNo", (m+1) + "");
				riderParams.put("insuredAge", usia + "");
				for( int i = 1; i < ArrUtil.upperBound( istr_prop.rider_baru ); i++ ){
					riderParams.put("rider"+(i-1), "-");
				}
				biayaRiderList.add(riderParams);
    		}
    	}
        	
        //riderTambahan[0][1] = totalRider.toString();

        return biayaRiderList;
    }
}