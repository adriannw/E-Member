package id.co.sinarmaslife.eproposal.product.product01040215;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
 * Program Name   		: Cepr01040215Business
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
import id.co.sinarmaslife.eproposal.web.Cepr01030104Form;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.MappingUtil;

public class Cepr01040215Business extends Cepr01040000UlinkBusiness 
{
    protected final Log logger = LogFactory.getLog( getClass() );

    private Cepr00000000YieldResultVO yieldResultVO;

    public Cepr01040215Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
        setSekaligusRightPartCdArr( new Integer[]{ Cepr01040215Mapper.X1 } );
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
        logger.info( "*-*-*-* Cepr01040201Business.of_get_coi_basic" );
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

        Map<String, Object> par = new HashMap<String, Object>();
        par.put( "liUsia", li_usia );
        par.put("liSex", li_sex);
        ldec_rate = eproposalManager.selectLdecRateToGetCoiBasic( par ).doubleValue();

        ldec_temp = FormatNumber.round( ( ( istr_prop.up / 1000 ) * ldec_rate ) * 0.1, 2 );
        ldec_total += ldec_temp;

        logger.info( "*-*-*-* ldec_total = " + ldec_total );
        return ldec_total;
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
        logger.info( "*-*-*-* Cepr01040202Business.getInvestmentPerformanceList" );
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

    public double getMaxPremiPokok()
    {
        double maxPremiPokok = 0;
        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            maxPremiPokok = 20000000;
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            maxPremiPokok = 2000;
        }
        return maxPremiPokok;
    }

    public List<Map<String, Object>> getCommonHeaderRowList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getCommonHeaderRowList" );

        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );

        // Fill header content from CommonHeaderDataSource
        result.addAll( source.getInsuredNameMap() );
        result.addAll( source.getInsuredAgeMap() );
        result.addAll( source.getTermOfContractWithLimitMap( Hardcode.LIFE_TIME ) );
        result.addAll( source.getMti1TermMap() );
        result.addAll( source.getMti1RateMap() );
        result.addAll( source.getMti1BeginningDateMap() );

        double maxPremiPokok = getMaxPremiPokok();

        Map<String, Object> params;
        if( cepr01030102Form.getPremium().compareTo( new BigDecimal( maxPremiPokok ) ) > 0 )
        {

            params = new HashMap<String, Object>();
            params.put( "label", "Premi Sekaligus" );
            params.put( "content", currencySymbol + editorUtil.convertToRoundedTwoDigits( new BigDecimal( maxPremiPokok ) ) );
            result.add( params );
            
            params = new HashMap<String, Object>();
            params.put( "label", "Premi Top Up" );
            params.put( "content", currencySymbol + editorUtil.convertToRoundedTwoDigits( cepr01030102Form.getPremium().subtract( new BigDecimal( maxPremiPokok ) ) ) );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "label", "" );
            params.put( "content", "------------------------" );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "label", "Total Premi" );
            params.put( "content", currencySymbol + editorUtil.convertToRoundedTwoDigits( cepr01030102Form.getPremium() ) );
            result.add( params );
        }
        else
        {
            params = new HashMap<String, Object>();
            params.put( "label", "Premi Sekaligus" );
            params.put( "content", currencySymbol + editorUtil.convertToRoundedTwoDigits( cepr01030102Form.getPremium() ) );
            result.add( params );
        }

        result.addAll( source.getTotalSumInsuredMap() );
        
        params = new HashMap<String, Object>();
        params.put( "label", "Tipe Medis Calon Peserta" );
        params.put( "content", "\"NM\"" );
        result.add( params );
        
        return result;
    }

    public List<Map<String, Object>> getCommonHeaderSmallRowList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getCommonHeaderSmallRowList" );

        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );

        // Fill header content from CommonHeaderDataSource
        result.addAll( source.getInsuredNameMap() );
        result.addAll( source.getInsuredAgeMap() );
        result.addAll( source.getTermOfContractWithLimitMap( Hardcode.LIFE_TIME ) );
        result.addAll( source.getMti1TermMap() );
        result.addAll( source.getMti1RateMap() );
        result.addAll( source.getMti1BeginningDateMap() );

        double maxPremiPokok = 0;
        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            maxPremiPokok = 20000000;
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            maxPremiPokok = 2000;
        }

        Map<String, Object> params;
        if( cepr01030102Form.getPremium().compareTo( new BigDecimal( maxPremiPokok ) ) > 0 )
        {

            params = new HashMap<String, Object>();
            params.put( "label", "Premi Sekaligus" );
            params.put( "content", currencySymbol + editorUtil.convertToRoundedTwoDigits( new BigDecimal( maxPremiPokok ) ) );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "label", "Premi Top Up" );
            params.put( "content", currencySymbol + editorUtil.convertToRoundedTwoDigits( cepr01030102Form.getPremium().subtract( new BigDecimal( maxPremiPokok ) ) ) );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "label", "" );
            params.put( "content", "------------------------" );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "label", "Total Premi" );
            params.put( "content", currencySymbol + editorUtil.convertToRoundedTwoDigits( cepr01030102Form.getPremium() ) );
            result.add( params );
        }
        else
        {
            params = new HashMap<String, Object>();
            params.put( "label", "Premi Sekaligus" );
            params.put( "content", currencySymbol + editorUtil.convertToRoundedTwoDigits( cepr01030102Form.getPremium() ) );
            result.add( params );
        }
        
        result.addAll( source.getTotalSumInsuredMap() );

        return result;
    }

    public List<Map<String, Object>> getInvestmentChoiceRowList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getInvestmentChoiceRowList" );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        Map<String, Object> params;
        String productLabel = "";

        String investmentTitleAndDescrArr[][];
        investmentTitleAndDescrArr = new String[][] {
            { "", "", "" },
            { "", "- " + productLabel + " Stable Syariah Fund Rupiah", "  Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi",
                                                               "  berpendapatan tetap Syariah dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 20%",
                                                               "  (dua puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dalam",
                                                               "  mata uang Rupiah"
            },
            { "", "- " + productLabel + " Stable Syariah Fund Dollar", "  Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi",
                                                               "  berpendapatan tetap Syariah dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 20%",
                                                               "  (dua puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dalam",
                                                               "  mata uang US Dollar"
            },
        };

        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            params = new HashMap<String, Object>();
            params.put( "description", investmentTitleAndDescrArr[ 1 ][ 1 ] );
            params.put( "allocationPercent", "100%" );
            result.add( params );

            for( int i = 2;  i < investmentTitleAndDescrArr[ 1 ].length; i++  )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 1 ][ i ] );
                params.put( "allocationPercent", "" );
                result.add( params );
            }
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            params = new HashMap<String, Object>();
            params.put( "description", investmentTitleAndDescrArr[ 2 ][ 1 ] );
            params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getSecureUsd() + "%" ) );
            result.add( params );

            for( int i = 2;  i < investmentTitleAndDescrArr[ 2 ].length; i++  )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 2 ][ i ] );
                params.put( "allocationPercent", "" );
                result.add( params );
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
            params = new HashMap<String, Object>();
            params.put( "description", "Stable Syariah Fund Rupiah" );
            params.put( "currencySymbol", currencySymbol );
            params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium() ) );
            params.put( "allocationPercent", "100%" );
            result.add( params );
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            params = new HashMap<String, Object>();
            params.put( "description", "Stable Syariah Fund Dollar" );
            params.put( "currencySymbol", currencySymbol );
            params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium() ) );
            params.put( "allocationPercent", "100%" );
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

    // i got it from wf_set_164
    public IllustrationResultVO getIllustrationResult()
    {
        logger.info( "*-*-*-* Cepr01040215Business.getIllustrationResult" );

        IllustrationResultVO result = new IllustrationResultVO();
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        Cepr01030101Form cepr01030101Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030101Form();
        Cepr01030104Form cepr01030104Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030104Form();

        ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        List<TopupDrawVO> topupDrawVOList = cepr01030104Form.getTopupDrawVOList();
        int topupDrawVOListSize = topupDrawVOList.size();
        TopupDrawVO topupDrawVO;
        double premiSekaligus;
        String topup;
        String draw;

   // todo
        int li_bulan[] = { CommonConst.DUMMY_ZERO, 1, 6, 12, 36, 3, 24},  li_jenis = 1, li_month;
        double li_bagi = 1, li_hari;
        double[][] ldec_hasil_invest = new double[5 + 1][3 + 1];
        double[][] ldec_bunga = new double[5 + 1][3 + 1];
        double[] ldec_bunga_avg  = new double[3 + 1];
        double ldec_premi_invest, ldec_wdraw, ldec_topup, ldec_jumlah;
        double ldec_bass;
        double ldec_coi, ldec_mfc, ldec_sc, ldec_cost, ldec_man_celaka, ldec_temp;
        boolean lb_minus[] = {CommonConst.DUMMY_FALSE, false, false, false}, lb_rider = false;
        S_biaya lstr;
//n_riders ln_riders
        double ldec_manfaat;
        String ls_jenis = "Stable Syariah Fund Rupiah", ls_rp = "Rp. ";
        Date ld2;

        if( istr_prop.kurs_id.equals( "02" ) )
        {
            ls_jenis = "Stable Syariah Fund Dollar";
            li_bagi = 1;
            li_jenis = 4;
            ls_rp = "US$ ";
        }

//monthly fix cost
        ldec_mfc = 0;
        for( int i = 1; i <= 3; i++ )
        {
            ldec_hasil_invest[1][i] = 0;
        }

//
        ldec_manfaat = istr_prop.up;
//Biaya Akuisisi & asumsi penarikan (1 tarik, 1-nya tdk tarik)
        lstr = PbFunction.wf_get_biaya_84( istr_prop, command );
        ldec_bunga = lstr.bunga;

        for( int i = 1; i <= 3; i++ )
        {
            ldec_bunga[li_jenis][i] = (istr_prop.pct_invest +  2 + i + (i-1)) / 100;
        }

        lstr.topup[1] = istr_prop.topup;

        double[] np = new double[3 + 1];
        double[] celaka = new double[3 + 1];

        // I got it from event click OK on w_data_usul
        int jmlBulan = cepr01030102Form.getMtiCd();
        if( jmlBulan == 3 ) istr_prop.biaya_ass = 97.5;
        else if( jmlBulan == 6 ) istr_prop.biaya_ass = 95.0;
        else if( jmlBulan >= 12 ) istr_prop.biaya_ass = 90.0;
        else throw new RuntimeException( "*-*-*-* Cepr01040215Business.getIllustrationResult: cepr01030102Form.getMtiCd tidak terdaftar" );

//Proses Hitung
// Fee di-nol'in pd saat hitung2
// Hitung ilustrasi 1
        Date ld1 = istr_prop.tgl_prop;
        ldec_temp = istr_prop.premi + istr_prop.topup;
        for( int i = 1; i <=5; i++ )
        {
            //surrender charge
            ldec_topup = 0;
            if( i <= ArrUtil.upperBound( lstr.topup ) )
            {
                ldec_topup = lstr.topup[i];
            }

            li_month = li_bulan[istr_prop.bunga] * i;
            
            if(li_bulan[istr_prop.bunga] >= 24){
        		ld2 = PbFunction.f_add_months( eproposalManager, istr_prop.tgl_prop, li_bulan[istr_prop.bunga] * i);
        		li_hari = PbFunction.daysAfter(istr_prop.tgl_prop, ld2);
        		//ldec_temp = istr_prop.premi + istr_prop.topup;
        	}else{
        		ld2 = PbFunction.f_add_months( eproposalManager, ld1, li_bulan[istr_prop.bunga]);
        		li_hari = PbFunction.daysAfter(ld1, ld2);
        		//logger.info("li_hari " + li_hari);
        		logger.debug( "li_hari " + li_hari );
        		ld1 = PbFunction.relativedate(ld2, 1);
        	}
            
            ldec_jumlah = PbFunction.round(ldec_temp * li_hari/365 * (istr_prop.pct_invest/100), 2) + ldec_temp;
            
            for( int k = 1; k <=3; k++ )
            {
                if( li_bulan[istr_prop.bunga] >= 24 )
                {
                    //perhitungan bunga 24bln ke atas beda
                    ldec_jumlah = PbFunction.round(ldec_temp * (Math.pow( (1 + ldec_bunga[li_jenis][k]), (li_month/12)) ), 2);
                }
                else
                {
                	if(i > 1){ldec_temp = ldec_hasil_invest[1][k];}
                    ldec_jumlah = PbFunction.round(ldec_temp * li_hari/365 * (ldec_bunga[li_jenis][k]), 2) + ldec_temp;
                }

                ldec_premi_invest = ldec_jumlah;

                if( ldec_bunga[li_jenis][k] > (istr_prop.pct_invest / 100 ) )
                {
                    if( li_bulan[istr_prop.bunga] >= 24 )
                    {
                        ldec_premi_invest = ldec_jumlah - ( istr_prop.biaya_ass/100 * (ldec_jumlah - (PbFunction.round(ldec_temp * ( Math.pow( (1 + istr_prop.pct_invest/100), (li_month/12))), 2))));
                    }
                    else
                    {
                        ldec_premi_invest = ldec_jumlah - ( istr_prop.biaya_ass/100 * (ldec_jumlah - (PbFunction.round(ldec_temp * li_hari/365 * (istr_prop.pct_invest/100), 2) + ldec_temp)));
                    }
                }
                ldec_hasil_invest[1][k] = ldec_premi_invest;
            }
            //Insert Fixed

            for( int k = 1; k <= 3; k++ )
            {
                np[ k ] = FormatNumber.round( ldec_hasil_invest[ 1 ][ k ] / li_bagi, 0 );
                celaka[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] + ldec_manfaat ) / li_bagi, 0 );
            }

            String premiSekaligusStr = "";
            String topupAssumptionStr = "";
            double premiumTopUp = 0;
            double premiTotal = LazyConverter.toDouble( cepr01030102Form.getPremium() );
            if( i == 1 )
            {
                double maxPremiPokok = getMaxPremiPokok();
                if( premiTotal > maxPremiPokok )
                {
                    premiSekaligus = maxPremiPokok;
                    premiumTopUp = premiTotal - maxPremiPokok;
                }
                else
                {
                    premiSekaligus = premiTotal;
                    premiumTopUp = 0;
                }
            }
            else
            {
                premiSekaligus = 0;
                premiumTopUp = 0;
            }

            if( premiSekaligus == 0 )
            {
                premiSekaligusStr = "";
                topupAssumptionStr = "0";
            }
            else
            {
                premiSekaligusStr = editorUtil.convertToStringWithoutCent( premiSekaligus );
                topupAssumptionStr = editorUtil.convertToStringWithoutCent( premiumTopUp );
            }

            String valueLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 1 ] ) );
            String valueMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 2 ] ) );
            String valueHi = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 3 ] ) );
            
            String benefitLow = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 1 ], np[ 1 ] );
            String benefitMid = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 2 ], np[ 2 ] );
            String benefitHi = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 3 ], np[ 3 ] );

            map = new HashMap<String, Object>();
            map.put( "no", editorUtil.convertToString( i ) );
            map.put( "policyAge", editorUtil.convertToString( cepr01030102Form.getMtiCd() * i ) );
            map.put( "premiSekaligus", premiSekaligusStr );
            map.put( "topupAssumption", topupAssumptionStr );
            map.put( "valueLow", valueLow );
            map.put( "valueMid", valueMid );
            map.put( "valueHi", valueHi );
            map.put( "benefitLow", benefitLow );
            map.put( "benefitMid", benefitMid );
            map.put( "benefitHi", benefitHi );
            mapList.add( map );

        }

        /// Start compute result of Investment result assumption
        Cepr00000000YieldResultVO yieldResultVO = new Cepr00000000YieldResultVO();
        ArrayList<Map<String, Object>> yieldList = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        params = new HashMap<String, Object>();
        params.put( "fundDesc", ls_jenis );
        params.put( "annualYieldLow", editorUtil.convertToTwoDigit( ldec_bunga[li_jenis][1] * 100 ) + "%" );
        params.put( "annualYieldMid", editorUtil.convertToTwoDigit( ldec_bunga[li_jenis][2] * 100 ) + "%" );
        params.put( "annualYieldHi",  editorUtil.convertToTwoDigit( ldec_bunga[li_jenis][3] * 100 ) + "%" );
        params.put( "investmentAllocation", "100%" );
        params.put( "allocationYieldLow", editorUtil.convertToTwoDigit( ldec_bunga[li_jenis][1] * 100 ) + "%" );
        params.put( "allocationYieldMid", editorUtil.convertToTwoDigit( ldec_bunga[li_jenis][2] * 100 ) + "%" );
        params.put( "allocationYieldHi",  editorUtil.convertToTwoDigit( ldec_bunga[li_jenis][3] * 100 ) + "%" );
        yieldList.add( params );

        yieldResultVO.setYieldList( yieldList );
        yieldResultVO.setTotalInvestmentAllocation( "100%" );
        yieldResultVO.setTotalAssumptionLow( editorUtil.convertToTwoDigit( ldec_bunga[li_jenis][1] * 100 ) + "%" );
        yieldResultVO.setTotalAssumptionMid( editorUtil.convertToTwoDigit( ldec_bunga[li_jenis][2] * 100 ) + "%" );
        yieldResultVO.setTotalAssumptionHi( editorUtil.convertToTwoDigit( ldec_bunga[li_jenis][3] * 100 ) + "%" );

        // assign the result to member of this class
        this.yieldResultVO = yieldResultVO;

        result.setValidityMsg( lb_minus[ 1 ] ? "Maaf, Proposal yang Anda buat Tidak Layak Jual." : "" );
        result.setIllustrationList( mapList );
        return result;

    }

    public Cepr00000000YieldResultVO getInvestmentYield()
    {
        return this.yieldResultVO;
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
    			sekaligus = FormatNumber.round((istr_prop.up_term / 1000) * selectLdecRateTerm(5, kurs_id, umur_tt), 2);
    		}else if("02".equals(kurs_id))
    		{
    			sekaligus = (istr_prop.up_term / 1000) * selectLdecRateTerm(5, kurs_id, umur_tt);
    		}
    		// TAHUNAN
    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_TAHUNAN;
    		if("01".equals(kurs_id))
    		{
    			rateTahunan = FormatNumber.round((istr_prop.up_term / 1000) * selectLdecRateTerm(5, kurs_id, umur_tt), 2);
    		}else if("02".equals(kurs_id))
    		{
    			rateTahunan = (istr_prop.up_term / 1000) * selectLdecRateTerm(5, kurs_id, umur_tt);
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
    			sekaligus = FormatNumber.round( (istr_prop.up * 0.5 / 1000) * selectLdecRateCiRider(80, kurs_id, umur_tt), 2);
    		}else if("02".equals(kurs_id))
    		{
    			sekaligus = (istr_prop.up * 0.5 / 1000) * selectLdecRateCiRider(80, kurs_id, umur_tt);
    		}
    		// TAHUNAN
    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_TAHUNAN;
    		if("01".equals(kurs_id))
    		{
    			rateTahunan = FormatNumber.round( (istr_prop.up * 0.5 / 1000) * selectLdecRateCiRider(80, kurs_id, umur_tt), 2);
    		}else if("02".equals(kurs_id))
    		{
    			rateTahunan = (istr_prop.up * 0.5 / 1000) * selectLdecRateCiRider(80, kurs_id, umur_tt);
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
