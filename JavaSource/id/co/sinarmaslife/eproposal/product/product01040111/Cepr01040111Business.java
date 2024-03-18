package id.co.sinarmaslife.eproposal.product.product01040111;

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
 * Program Name   		: Cepr01040111Business
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
import id.co.sinarmaslife.standard.util.MathUtil;

public class Cepr01040111Business extends Cepr01040000UlinkBusiness 
{
    protected final Log logger = LogFactory.getLog( getClass() );

    private Cepr00000000YieldResultVO yieldResultVO;

    public Cepr01040111Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
        setSekaligusRightPartCdArr( new Integer[]{ Cepr01040111Mapper.X1 } );
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
        li_usia = istr_prop.umur_tt + ai_th - 1;

        Map<String, Object> par = new HashMap<String, Object>();
        par.put( "liUsia", li_usia );
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
        
        if(cepr01030102Form.getJenisCd() == 1){
	        double m = LazyConverter.toDouble(new BigDecimal(eproposalManager.selectLastDay()));
	        double y = 365;
	        params = new HashMap<String, Object>();
	        params.put( "label", "Target Investasi Ulang Bulan I" );
	        params.put( "content", currencySymbol + editorUtil.convertToRoundedTwoDigits( m/y * (cepr01030102Form.getPremium().doubleValue() + 0) * (cepr01030102Form.getInvestRateInPercent().doubleValue()/100) ) );
	        result.add( params );
        }
        
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
        
        if(cepr01030102Form.getJenisCd() == 1){
	        double m = LazyConverter.toDouble(new BigDecimal(eproposalManager.selectLastDay()));;
	        double y = 365;
	        params = new HashMap<String, Object>();
	        params.put( "label", "Target Investasi Ulang Bulan I" );
	        params.put( "content", currencySymbol + editorUtil.convertToRoundedTwoDigits( m/y * (cepr01030102Form.getPremium().doubleValue() + 0) * (cepr01030102Form.getInvestRateInPercent().doubleValue()/100) ) );
	        result.add( params );
        }

        return result;
    }

    public List<Map<String, Object>> getInvestmentChoiceRowList() throws IOException
    {
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        Map<String, Object> params;
        String productLabel = "";

        String investmentTitleAndDescrArr[][];
        investmentTitleAndDescrArr = new String[][] {
            { "", "", "" },
            { "", "- " + productLabel + " Stable Fund Rupiah", "  Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi",
                                                               "  berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 20%",
                                                               "  (dua puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dalam",
                                                               "  mata uang Rupiah"
            },
            { "", "- " + productLabel + " Stable Fund Dollar", "  Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi",
                                                               "  berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 20%",
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
            params.put( "allocationPercent", editorUtil.convertToString( "100%" ) );
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

    
    public List<Map<String, Object>> getInvestmentNewChoiceRowList() throws IOException
    {
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        Map<String, Object> params;
        String productLabel = "";

        String investmentTitleAndDescrArr[][];
        investmentTitleAndDescrArr = new String[][] {
            { "", "", "" },
            { "", "- " + productLabel + " Stable Fund Rupiah II", "  Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi",
                                                               "  berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 20%",
                                                               "  (dua puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dalam",
                                                               "  mata uang Rupiah"
            },
            { "", "- " + productLabel + " Stable Fund Dollar II", "  Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi",
                                                               "  berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 20%",
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
            params.put( "allocationPercent", editorUtil.convertToString( "100%" ) );
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
            params.put( "description", "Stable Fund Rupiah" );
            params.put( "currencySymbol", currencySymbol );
            params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium() ) );
            params.put( "allocationPercent", "100%" );
            result.add( params );
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            params = new HashMap<String, Object>();
            params.put( "description", "Stable Fund Dollar" );
            params.put( "currencySymbol", currencySymbol );
            params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium() ) );
            params.put( "allocationPercent", "100%" );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getInvestmentNewChoiceSmallRowList() throws IOException
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
            params.put( "description", "Stable Fund Rupiah II" );
            params.put( "currencySymbol", currencySymbol );
            params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium() ) );
            params.put( "allocationPercent", "100%" );
            result.add( params );
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            params = new HashMap<String, Object>();
            params.put( "description", "Stable Fund Dollar II" );
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
    public IllustrationResultVO getIllustrationNewResult()
    {
        logger.info( "*-*-*-* Cepr01040111Business.getIllustrationResult" );

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
        double ldec_coi, ldec_mfc, ldec_sc, ldec_cost, ldec_man_celaka, ldec_temp, ldec_premi_ti;
        boolean lb_minus[] = {CommonConst.DUMMY_FALSE, false, false, false}, lb_rider = false;
        S_biaya lstr;
//n_riders ln_riders
        double ldec_manfaat;
        String ls_jenis = "Stable Fund Rupiah II", ls_rp = "Rp. ";
        Date ld2;

        if( istr_prop.kurs_id.equals( "02" ) )
        {
            ls_jenis = "Stable Fund Dollar";
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
            ldec_bunga[li_jenis][i] = (istr_prop.pct_invest +  4 + ( i ) ) / 100;
        }

        lstr.topup[1] = istr_prop.topup;

        double[] np = new double[3 + 1];
        double[] celaka = new double[3 + 1];
        double[] bp = new double[3 + 1];

        // I got it from event click OK on w_data_usul
        int jmlBulan = cepr01030102Form.getMtiCd();
        if( jmlBulan == 3 ) istr_prop.biaya_ass = 97.5;
        else if( jmlBulan == 6 ) istr_prop.biaya_ass = 95.0;
        else if( jmlBulan >= 12 ) istr_prop.biaya_ass = 90.0;
        else throw new RuntimeException( "*-*-*-* Cepr01040111Business.getIllustrationResult: cepr01030102Form.getMtiCd tidak terdaftar" );

//Proses Hitung
// Fee di-nol'in pd saat hitung2
// Hitung ilustrasi 1
        Date ld1 = istr_prop.tgl_prop;
        ldec_temp = istr_prop.premi + istr_prop.topup;
        ldec_premi_ti = ldec_temp;
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
        		ld1 = PbFunction.relativedate(ld2, 1);
        	}
            
            ldec_jumlah = PbFunction.round(ldec_temp * li_hari/365 * (istr_prop.pct_invest/100), 2) + ldec_temp;
            
            if(cepr01030102Form.getJenisCd() == 0)
            {
	            for( int k = 1; k <=3; k++ )
	            {
	                if( li_bulan[istr_prop.bunga] >= 24 )
	                {
	                    //perhitungan bunga 24bln ke atas beda
	                    ldec_jumlah = PbFunction.round(ldec_temp * (Math.pow( (1 + ldec_bunga[li_jenis][k]), (li_month/12)) ), 2);
	                    if(k == 1) ldec_premi_ti = PbFunction.round(ldec_temp * ( Math.pow( (1 + istr_prop.pct_invest/100), (li_month/12))), 2);
	                }
	                else
	                {
	                	if(i > 1){ldec_temp = ldec_hasil_invest[1][k];}
	                    ldec_jumlah = PbFunction.round(ldec_temp * li_hari/365 * (ldec_bunga[li_jenis][k]), 2) + ldec_temp;
	                    if(k == 1) ldec_premi_ti = PbFunction.round(ldec_premi_ti * li_hari/365 * (istr_prop.pct_invest/100), 2) + ldec_premi_ti;
	                }
	
	                ldec_premi_invest = ldec_jumlah;
	                
	                if( ldec_bunga[li_jenis][k] > (istr_prop.pct_invest / 100 ) )
	                {
	                    if( li_bulan[istr_prop.bunga] >= 24 )
	                    {
//	                        ldec_premi_invest = ldec_jumlah - ( 0.25 * (ldec_jumlah - (PbFunction.round(ldec_temp * ( Math.pow( (1 + istr_prop.pct_invest/100), (li_month/12))), 2))));
	                    	ldec_premi_invest = ldec_jumlah;
	                    }
	                    else
	                    {
	                        ldec_premi_invest = ldec_jumlah - ( 0.25 * (ldec_jumlah - (PbFunction.round(ldec_temp * li_hari/365 * (istr_prop.pct_invest/100), 2) + ldec_temp)));
	                    }
	                }
	                ldec_hasil_invest[1][k] = ldec_premi_invest;
	                
	            }
    		}else{
    			for( int k = 1; k <=3; k++ )
 	            {
    				ldec_hasil_invest[1][k] = ldec_temp;
 	            }
    		}
            //Insert Fixed

            for( int k = 1; k <= 3; k++ )
            {
                np[ k ] = FormatNumber.round( ldec_hasil_invest[ 1 ][ k ] / li_bagi, 0 );
                celaka[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] + ldec_manfaat ) / li_bagi, 0 );
                bp[ k ] = FormatNumber.round( (ldec_hasil_invest[1][k] - ldec_premi_ti)/ li_bagi, 0 );
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
            
            String bonusLow = null;
            String bonusMid = null;
            if( cepr01030102Form.getMtiCd().equals(3) || cepr01030102Form.getMtiCd().equals(6)){
            	bonusLow = "-";
                bonusMid = "-";
            }else{
            	bonusLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( bp[ 1 ] ) );
                bonusMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( bp[ 2 ] ) );
            }

            String bonusHi = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( bp[ 3 ] ) );

            String valueLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 1 ] ) );
            String valueMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 2 ] ) );
            String valueHi = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 3 ] ) );

            String benefitLow = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 1 ], np[ 1 ] );
            String benefitMid = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 2 ], np[ 2 ] );
            String benefitHi = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 3 ], np[ 3 ] );
            
            String akum_premi = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( ldec_premi_ti / li_bagi ) );

            map = new HashMap<String, Object>();
            map.put( "no", editorUtil.convertToString( i ) );
            map.put( "policyAge", editorUtil.convertToString( cepr01030102Form.getMtiCd() * i ) );
            map.put( "premiSekaligus", premiSekaligusStr );
            map.put( "topupAssumption", topupAssumptionStr );
            map.put( "bonusLow", bonusLow );
            map.put( "bonusMid", bonusMid );
            map.put( "bonusHi", bonusHi );
            map.put( "valueLow", valueLow );
            map.put( "valueMid", valueMid );
            map.put( "valueHi", valueHi );
            map.put( "benefitLow", benefitLow );
            map.put( "benefitMid", benefitMid );
            map.put( "benefitHi", benefitHi );
            map.put( "akum_premi", akum_premi );
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

    // i got it from wf_set_164
    public IllustrationResultVO getIllustrationResult()
    {
        logger.info( "*-*-*-* Cepr01040111Business.getIllustrationResult" );

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
        double ldec_coi, ldec_mfc, ldec_sc, ldec_cost, ldec_man_celaka, ldec_temp, ldec_premi_ti;
        boolean lb_minus[] = {CommonConst.DUMMY_FALSE, false, false, false}, lb_rider = false;
        S_biaya lstr;
//n_riders ln_riders
        double ldec_manfaat;
        String ls_jenis = "Stable Fund Rupiah", ls_rp = "Rp. ";
        Date ld2;

        if( istr_prop.kurs_id.equals( "02" ) )
        {
            ls_jenis = "Stable Fund Dollar";
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
        double[] bp = new double[3 + 1];

        // I got it from event click OK on w_data_usul
        int jmlBulan = cepr01030102Form.getMtiCd();
        if( jmlBulan == 3 ) istr_prop.biaya_ass = 97.5;
        else if( jmlBulan == 6 ) istr_prop.biaya_ass = 95.0;
        else if( jmlBulan >= 12 ) istr_prop.biaya_ass = 90.0;
        else throw new RuntimeException( "*-*-*-* Cepr01040111Business.getIllustrationResult: cepr01030102Form.getMtiCd tidak terdaftar" );

//Proses Hitung
// Fee di-nol'in pd saat hitung2
// Hitung ilustrasi 1
        Date ld1 = istr_prop.tgl_prop;
        ldec_temp = istr_prop.premi + istr_prop.topup;
        ldec_premi_ti = ldec_temp;
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
            
            if(cepr01030102Form.getJenisCd() == 0)
            {
	            for( int k = 1; k <=3; k++ )
	            {
	                if( li_bulan[istr_prop.bunga] >= 24 )
	                {
	                    //perhitungan bunga 24bln ke atas beda
	                    ldec_jumlah = PbFunction.round(ldec_temp * (Math.pow( (1 + ldec_bunga[li_jenis][k]), (li_month/12)) ), 2);
	                    if(k == 1) ldec_premi_ti = PbFunction.round(ldec_temp * ( Math.pow( (1 + istr_prop.pct_invest/100), (li_month/12))), 2);
	                }
	                else
	                {
	                	if(i > 1){ldec_temp = ldec_hasil_invest[1][k];}
	                    ldec_jumlah = PbFunction.round(ldec_temp * li_hari/365 * (ldec_bunga[li_jenis][k]), 2) + ldec_temp;
	                    if(k == 1) ldec_premi_ti = PbFunction.round(ldec_premi_ti * li_hari/365 * (istr_prop.pct_invest/100), 2) + ldec_premi_ti;
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
    		}else{
    			for( int k = 1; k <=3; k++ )
 	            {
    				ldec_hasil_invest[1][k] = ldec_temp;
 	            }
    		}
            //Insert Fixed

            for( int k = 1; k <= 3; k++ )
            {
                np[ k ] = FormatNumber.round( ldec_hasil_invest[ 1 ][ k ] / li_bagi, 0 );
                celaka[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] + ldec_manfaat ) / li_bagi, 0 );
                bp[ k ] = FormatNumber.round( (ldec_hasil_invest[1][k] - ldec_premi_ti)/ li_bagi, 0 );
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
            
            String bonusLow = null;
            String bonusMid = null;
            if( cepr01030102Form.getMtiCd().equals(3) || cepr01030102Form.getMtiCd().equals(6)){
            	bonusLow = "-";
                bonusMid = "-";
            }else{
            	bonusLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( bp[ 1 ] ) );
                bonusMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( bp[ 2 ] ) );
            }

            String bonusHi = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( bp[ 3 ] ) );

            String valueLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 1 ] ) );
            String valueMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 2 ] ) );
            String valueHi = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 3 ] ) );

            String benefitLow = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 1 ], np[ 1 ] );
            String benefitMid = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 2 ], np[ 2 ] );
            String benefitHi = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 3 ], np[ 3 ] );
            
            String akum_premi = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( ldec_premi_ti / li_bagi ) );

            map = new HashMap<String, Object>();
            map.put( "no", editorUtil.convertToString( i ) );
            map.put( "policyAge", editorUtil.convertToString( cepr01030102Form.getMtiCd() * i ) );
            map.put( "premiSekaligus", premiSekaligusStr );
            map.put( "topupAssumption", topupAssumptionStr );
            map.put( "bonusLow", bonusLow );
            map.put( "bonusMid", bonusMid );
            map.put( "bonusHi", bonusHi );
            map.put( "valueLow", valueLow );
            map.put( "valueMid", valueMid );
            map.put( "valueHi", valueHi );
            map.put( "benefitLow", benefitLow );
            map.put( "benefitMid", benefitMid );
            map.put( "benefitHi", benefitHi );
            map.put( "akum_premi", akum_premi );
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
	    		// TAHUNAN
	    			rateTahunan = selectLdecRateTerm(li_jenis, kurs_id, umur_tt);
	//    			ldec_temp = Round((istr_prop.up_term / 1000) * ldec_rate, 2)
	    			rateTahunan = FormatNumber.round((istr_prop.up_term / 1000) * rateTahunan, 2);
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
	//    		if istr_prop.rider_baru[4] >= 50 then ldec_persen_ci = istr_prop.rider_baru[4]/100
	//    				ldec_temp = Round((istr_prop.up * ldec_persen_ci / 1000) * ldec_rate, 2)
	    	    if( cepr01030103Form.getCiChooseCd() != null) ldec_persen_ci = LazyConverter.toDouble(new BigDecimal(cepr01030103Form.getCiChooseCd()))/100;
	    		rateTahunan = FormatNumber.round((istr_prop.up * ldec_persen_ci / 1000) * rateTahunan, 2);
	    		
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
    
    
    public Map<String, Object> of_get_coi_tpd( String kurs_id, int umur_tt, BigDecimal premiTotalSekaligus, BigDecimal premiTotalTahunan, BigDecimal premiTotalSemesteran, BigDecimal premiTotalTriwulanan, BigDecimal premiTotalBulanan )
    {
    	// TPD RIDER
    	Map<String, Object> tpdRider = new HashMap<String, Object>();
    	double sekaligus = 0;
    	double tahunan = 0;
    	double rateTahunan = 0;
    	double semesteran = 0;
    	double triwulanan = 0;
    	double bulanan = 0;
    	double ldec_max = 0;
    	
    	if( istr_prop.rider_baru[ 3 ] > 0 ){
    	
	    	Map<String, Object> params = new HashMap<String, Object>();
	    	params.put( "kursId", istr_prop.kurs_id );
	    	params.put( "liUsia", umur_tt );
	    	// maks 2 milyar
	    	if( "01".equals( istr_prop.kurs_id ) ){
	    		ldec_max = 2000000000;
	    	}else if( "02".equals( istr_prop.kurs_id ) ){
	    		ldec_max = 200000;
	    	}
	    	double maxAmount = MathUtil.min( istr_prop.up, ldec_max );
	          
	    	rateTahunan = FormatNumber.round( (maxAmount / 1000) * LazyConverter.toDouble( eproposalManager.selectLdecRateTpd( params ) ), 2);
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
	    		tpdRider.put("tpdRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus))+".00");
	    		tpdRider.put("tpdRiderTahunan", editorUtil.convertToRoundedNoDigit(new BigDecimal(tahunan))+".00");
	    		tpdRider.put("tpdRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		tpdRider.put("tpdRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		tpdRider.put("tpdRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		tpdRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(0, RoundingMode.HALF_UP));
	    		tpdRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(0, RoundingMode.HALF_UP));
	    		tpdRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		tpdRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		tpdRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	    	}else if("02".equals(kurs_id))
	    	{
	    		tpdRider.put("tpdRiderSekaligus", editorUtil.convertToRoundedTwoDigits(new BigDecimal(sekaligus)));
	    		tpdRider.put("tpdRiderTahunan", editorUtil.convertToRoundedTwoDigits(new BigDecimal(tahunan)));
	    		tpdRider.put("tpdRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		tpdRider.put("tpdRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		tpdRider.put("tpdRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		tpdRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(2, RoundingMode.HALF_UP));
	    		tpdRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(2, RoundingMode.HALF_UP));
	    		tpdRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		tpdRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		tpdRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	    	}
    	
    	}
    	return tpdRider;
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
    
    
    public Map<String, Object> of_get_coi_hcp( String kurs_id, int umur_tt, BigDecimal premiTotalSekaligus, BigDecimal premiTotalTahunan, BigDecimal premiTotalSemesteran, BigDecimal premiTotalTriwulanan, BigDecimal premiTotalBulanan )
    {
    	// HCP RIDER 4
    	Map<String, Object> hcpRider = new HashMap<String, Object>();
    	double sekaligus = 0;
    	double tahunan = 0;
    	double rateTahunan = 0;
    	double semesteran = 0;
    	double triwulanan = 0;
    	double bulanan = 0;
    	
    	if( istr_prop.rider_baru[ 2 ] > 0){
    	
			 Map<String, Object> params = new HashMap<String, Object>();
	         params.put( "riderBaru", istr_prop.rider_baru[ 2 ] );
	         params.put( "kursId", istr_prop.kurs_id );
	         params.put( "liUsia", umur_tt );
	
	         rateTahunan = FormatNumber.round( LazyConverter.toDouble( eproposalManager.selectLdecRateHcp( params )  ) * 0.1 , 2);
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
	    		hcpRider.put("hcpRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus))+".00");
	    		hcpRider.put("hcpRiderTahunan", editorUtil.convertToRoundedNoDigit(new BigDecimal(tahunan))+".00");
	    		hcpRider.put("hcpRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		hcpRider.put("hcpRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		hcpRider.put("hcpRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		hcpRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(0, RoundingMode.HALF_UP));
	    		hcpRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(0, RoundingMode.HALF_UP));
	    		hcpRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		hcpRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		hcpRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	    	}else if("02".equals(kurs_id))
	    	{
	    		hcpRider.put("hcpRiderSekaligus", editorUtil.convertToRoundedTwoDigits(new BigDecimal(sekaligus)));
	    		hcpRider.put("hcpRiderTahunan", editorUtil.convertToRoundedTwoDigits(new BigDecimal(tahunan)));
	    		hcpRider.put("hcpRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		hcpRider.put("hcpRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		hcpRider.put("hcpRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		hcpRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(2, RoundingMode.HALF_UP));
	    		hcpRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(2, RoundingMode.HALF_UP));
	    		hcpRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		hcpRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		hcpRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	    	}
    	}
    	
    	return hcpRider;
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
    
    
    public Map<String, Object> of_get_coi_ladies_ins( String kurs_id, int umur_tt, BigDecimal premiTotalSekaligus, BigDecimal premiTotalTahunan, BigDecimal premiTotalSemesteran, BigDecimal premiTotalTriwulanan, BigDecimal premiTotalBulanan )
    {
    	// EKA SEHAT RIDER 4
    	Map<String, Object> ladiesInsRider = new HashMap<String, Object>();
    	double sekaligus = 0;
    	double tahunan = 0;
    	double rateTahunan = 0;
    	double semesteran = 0;
    	double triwulanan = 0;
    	double bulanan = 0;
    	double ldec_max = 0;
    	double persentase = 0;
    	int li_usia;
    	if( istr_prop.rider_baru[ 19 ] > 0 ){
    		int li_jenis = istr_prop.rider_baru[19];
	    	Map<String, Object> params = new HashMap<String, Object>();
	    	params.put( "liJenis", li_jenis );
	    	params.put( "kursId", istr_prop.kurs_id );
	     	params.put( "liTahunKe", 0 );
	     	params.put( "liUsia", umur_tt );
	     
	     	ldec_max = 200000;
	     	if( istr_prop.kurs_id.equals("01") ){ ldec_max = 2000000000;}
	        if(cepr01030103Form.getLadiesInsFlag() != null && cepr01030103Form.getLadiesInsFlag().equals( CommonConst.CHECKED_TRUE ) )persentase = LazyConverter.toDouble( new BigDecimal(cepr01030103Form.getLadiesInsChooseCd())) / 100.0;
	     	double maxAmount = MathUtil.min( istr_prop.up * persentase, ldec_max );
	     	rateTahunan = FormatNumber.round( ( ( maxAmount / 1000 ) * LazyConverter.toDouble( eproposalManager.selectLdecRateLadiesIns(params) ) ), 2 );
	     	tahunan = tahunan + rateTahunan;
	     	// SEMESTERAN
	     	semesteran = semesteran + (rateTahunan * 0.525);
	     	// TRIWULANAN
	     	triwulanan = triwulanan + (rateTahunan * 0.27);
	     	// BULANAN
	     	bulanan = bulanan + (rateTahunan * 0.1);
	    	//}
	    	
	    	// MAIN
	    	//ladiesInsRider.put("ladiesInsRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus)));
	    	if("01".equals(kurs_id))
	        {
	    		ladiesInsRider.put("ladiesInsRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus))+".00");
	    		ladiesInsRider.put("ladiesInsRiderTahunan", editorUtil.convertToRoundedNoDigit(new BigDecimal(tahunan))+".00");
	    		ladiesInsRider.put("ladiesInsRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		ladiesInsRider.put("ladiesInsRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		ladiesInsRider.put("ladiesInsRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		ladiesInsRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(0, RoundingMode.HALF_UP));
	    		ladiesInsRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(0, RoundingMode.HALF_UP));
	    		ladiesInsRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		ladiesInsRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		ladiesInsRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	        }else if("02".equals(kurs_id))
	        {
	        	ladiesInsRider.put("ladiesInsRiderSekaligus", editorUtil.convertToRoundedTwoDigits(new BigDecimal(sekaligus)));
	    		ladiesInsRider.put("ladiesInsRiderTahunan", editorUtil.convertToRoundedTwoDigits(new BigDecimal(tahunan)));
	    		ladiesInsRider.put("ladiesInsRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		ladiesInsRider.put("ladiesInsRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		ladiesInsRider.put("ladiesInsRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		ladiesInsRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(2, RoundingMode.HALF_UP));
	    		ladiesInsRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(2, RoundingMode.HALF_UP));
	    		ladiesInsRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		ladiesInsRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		ladiesInsRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	        }
    	}
    	return ladiesInsRider;
    }
    
    
    public Map<String, Object> of_get_coi_hcp_lad( String kurs_id, int umur_tt, BigDecimal premiTotalSekaligus, BigDecimal premiTotalTahunan, BigDecimal premiTotalSemesteran, BigDecimal premiTotalTriwulanan, BigDecimal premiTotalBulanan )
    {
    	// EKA SEHAT RIDER 4
    	Map<String, Object> hcpLadRider = new HashMap<String, Object>();
    	double sekaligus = 0;
    	double tahunan = 0;
    	double rateTahunan = 0;
    	double semesteran = 0;
    	double triwulanan = 0;
    	double bulanan = 0;
    	double ldec_max = 0;
    	
    	int li_usia = umur_tt;
    	if(istr_prop.rider_baru[ 20 ] > 0 ){
    	
	    	Map<String, Object> params = new HashMap<String, Object>();
	    	params.put( "riderBaru", istr_prop.rider_baru[ 20 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	    	params.put( "kursId", istr_prop.kurs_id );
	    	params.put( "liUsia", umur_tt );
	    	if( li_usia < 66 && istr_prop.hcpLad.peserta == 0 ){
	    		rateTahunan = FormatNumber.round( LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadies( params ) ), 2 );
	    	}
	    	if( istr_prop.hcpLad.peserta > 0){
	    		li_usia = istr_prop.hcpLad.usia[ 1 ];
	    		if( li_usia < 66 )
	    		{
	    			for( int i = 1; i <= istr_prop.hcpLad.peserta; i++ )
	    			{
	    				li_usia = istr_prop.hcpLad.usia[ i ];
	    				Map<String, Object> paramsparticipants = new HashMap<String, Object>();
	    				paramsparticipants.put( "riderBaru", istr_prop.rider_baru[ 20 ] );    // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	    				paramsparticipants.put( "kursId", istr_prop.kurs_id );
	    				paramsparticipants.put( "liUsia", li_usia );
	    				double ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpLadiesPesertaTambahan( paramsparticipants ) );
	    				double ldec_temp = FormatNumber.round( ldec_rate, 2 );
	    				if(i >= 2){
	    					ldec_temp = FormatNumber.round( ldec_rate * 0.9, 2 );
	    					if(i>=3 && li_usia>25) ldec_temp = 0;
	    				}
	    				rateTahunan += ldec_temp;
	    			}
	    		}
	    	}
	//    		}	
	    	tahunan = tahunan + rateTahunan;
	    	// SEMESTERAN
	    	semesteran = semesteran + (rateTahunan * 0.525);
	    	// TRIWULANAN
	    	triwulanan = triwulanan + (rateTahunan * 0.27);
	    	// BULANAN
	    	bulanan = bulanan + (rateTahunan * 0.1);
	    	
	    	// MAIN
	    	//hcpLadRider.put("hcpLadRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus)));
	    	if("01".equals(kurs_id))
	        {
	    		hcpLadRider.put("hcpLadRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus))+".00");
	    		hcpLadRider.put("hcpLadRiderTahunan", editorUtil.convertToRoundedNoDigit(new BigDecimal(tahunan))+".00");
	    		hcpLadRider.put("hcpLadRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		hcpLadRider.put("hcpLadRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		hcpLadRider.put("hcpLadRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		hcpLadRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(0, RoundingMode.HALF_UP));
	    		hcpLadRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(0, RoundingMode.HALF_UP));
	    		hcpLadRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		hcpLadRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		hcpLadRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	        }else if("02".equals(kurs_id))
	        {
	        	hcpLadRider.put("hcpLadRiderSekaligus", editorUtil.convertToRoundedTwoDigits(new BigDecimal(sekaligus)));
	    		hcpLadRider.put("hcpLadRiderTahunan", editorUtil.convertToRoundedTwoDigits(new BigDecimal(tahunan)));
	    		hcpLadRider.put("hcpLadRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		hcpLadRider.put("hcpLadRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		hcpLadRider.put("hcpLadRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		hcpLadRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(2, RoundingMode.HALF_UP));
	    		hcpLadRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(2, RoundingMode.HALF_UP));
	    		hcpLadRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		hcpLadRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		hcpLadRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	        }
    	}
    	
    	return hcpLadRider;
    }
    
    
    public Map<String, Object> of_get_coi_ladies_med( String kurs_id, int umur_tt, BigDecimal premiTotalSekaligus, BigDecimal premiTotalTahunan, BigDecimal premiTotalSemesteran, BigDecimal premiTotalTriwulanan, BigDecimal premiTotalBulanan )
    {
    	// EKA SEHAT RIDER 4
    	Map<String, Object> ladiesMedRider = new HashMap<String, Object>();
    	double sekaligus = 0;
    	double tahunan = 0;
    	double rateTahunan = 0;
    	double semesteran = 0;
    	double triwulanan = 0;
    	double bulanan = 0;
    	double ldec_max = 0;
    	
    	if( istr_prop.rider_baru[ 21 ] > 0 ){
    		int li_usia = umur_tt;
	    	
	    	Map<String, Object> params = new HashMap<String, Object>();
	    	params.put( "riderBaru", istr_prop.rider_baru[ 21 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	    	params.put( "kursId", istr_prop.kurs_id );
	    	params.put( "liUsia", umur_tt );
//	    	if istr_prop.lmed.peserta = 0 and li_usia < 75 then
	    	if( li_usia < 75 && istr_prop.ladiesMedExpense.peserta == 0 ){
	    		rateTahunan = FormatNumber.round( LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider(params)), 2 );
	    	}
	    	if( istr_prop.ladiesMedExpense.peserta > 0 )
	    	{
	    		li_usia = istr_prop.ladiesMedExpense.usia[ 1 ];
	    		if( li_usia < 75 ){
			    	for( int i = 1; i <= istr_prop.ladiesMedExpense.peserta; i++ )
			    	{
			    		li_usia = istr_prop.ladiesMedExpense.usia[ i ];
			    		
			    		Map<String, Object> paramsparticipants = new HashMap<String, Object>();
			    		paramsparticipants.put( "riderBaru", istr_prop.rider_baru[ 21 ] );    
			    		paramsparticipants.put( "kursId", istr_prop.kurs_id );
			    		paramsparticipants.put( "liUsia", li_usia );
			    		double ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseRider( paramsparticipants ) );
			    		double ldec_temp = FormatNumber.round( ldec_rate, 2 );
			    		if(i > 1){
//			    				ldec_temp = Round(ldec_rate * 0.975, 2) //disc 2.5% -> 97.5%
			    			ldec_temp = FormatNumber.round( ldec_rate * 0.975, 2 );
			    		}
			    		rateTahunan += ldec_temp;
			    	}
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
	    	//}
	    	
	    	// MAIN
	    	//ladiesMedRider.put("ladiesMedRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus)));
	    	if("01".equals(kurs_id))
	        {
	    		ladiesMedRider.put("ladiesMedRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus))+".00");
	    		ladiesMedRider.put("ladiesMedRiderTahunan", editorUtil.convertToRoundedNoDigit(new BigDecimal(tahunan))+".00");
	    		ladiesMedRider.put("ladiesMedRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		ladiesMedRider.put("ladiesMedRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		ladiesMedRider.put("ladiesMedRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		ladiesMedRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(0, RoundingMode.HALF_UP));
	    		ladiesMedRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(0, RoundingMode.HALF_UP));
	    		ladiesMedRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		ladiesMedRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		ladiesMedRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	        }else if("02".equals(kurs_id))
	        {
	        	ladiesMedRider.put("ladiesMedRiderSekaligus", editorUtil.convertToRoundedTwoDigits(new BigDecimal(sekaligus)));
	    		ladiesMedRider.put("ladiesMedRiderTahunan", editorUtil.convertToRoundedTwoDigits(new BigDecimal(tahunan)));
	    		ladiesMedRider.put("ladiesMedRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		ladiesMedRider.put("ladiesMedRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		ladiesMedRider.put("ladiesMedRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		ladiesMedRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(2, RoundingMode.HALF_UP));
	    		ladiesMedRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(2, RoundingMode.HALF_UP));
	    		ladiesMedRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		ladiesMedRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		ladiesMedRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	        }
    	
    	}
    	return ladiesMedRider;
    }
    
    
    public Map<String, Object> of_get_coi_ladies_med_il( String kurs_id, int umur_tt, BigDecimal premiTotalSekaligus, BigDecimal premiTotalTahunan, BigDecimal premiTotalSemesteran, BigDecimal premiTotalTriwulanan, BigDecimal premiTotalBulanan )
    {
    	// EKA SEHAT RIDER 4
    	Map<String, Object> ladiesMedIlRider = new HashMap<String, Object>();
    	double sekaligus = 0;
    	double tahunan = 0;
    	double rateTahunan = 0;
    	double semesteran = 0;
    	double triwulanan = 0;
    	double bulanan = 0;
    	
    	if( istr_prop.rider_baru[ 22 ] > 0 ){
    		int li_usia = umur_tt;
	    	
	    	Map<String, Object> params = new HashMap<String, Object>();
	    	params.put( "riderBaru", istr_prop.rider_baru[ 22 ] );   // li_jenis in PB = riderBaru, + 140 bcoz start from 141
	    	params.put( "kursId", istr_prop.kurs_id );
	    	params.put( "liUsia", umur_tt );
//	    	if istr_prop.lmed.peserta = 0 and li_usia < 75 then
	    	if( li_usia < 75 && istr_prop.ladiesMedExpenseInnerLimit.peserta == 0 ){
	    		rateTahunan = FormatNumber.round( LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider(params)), 2 );
	    	}
	    	if( istr_prop.ladiesMedExpenseInnerLimit.peserta > 0 )
	    	{
	    		li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[ 1 ];
	    		if( li_usia < 75 ){
			    	for( int i = 1; i <= istr_prop.ladiesMedExpenseInnerLimit.peserta; i++ )
			    	{
			    		li_usia = istr_prop.ladiesMedExpenseInnerLimit.usia[ i ];
			    		
			    		Map<String, Object> paramsparticipants = new HashMap<String, Object>();
			    		paramsparticipants.put( "riderBaru", istr_prop.rider_baru[ 22 ] );    
			    		paramsparticipants.put( "kursId", istr_prop.kurs_id );
			    		paramsparticipants.put( "liUsia", li_usia );
			    		double ldec_rate = LazyConverter.toDouble( eproposalManager.selectLadiesMedExpenseInnerLimitRider(paramsparticipants) );
			    		double ldec_temp = FormatNumber.round( ldec_rate, 2 );
			    		if(i > 1){
//			    				ldec_temp = Round(ldec_rate * 0.975, 2) //disc 2.5% -> 97.5%
			    			ldec_temp = FormatNumber.round( ldec_rate * 0.975, 2 );
			    		}
			    		rateTahunan += ldec_temp;
			    	}
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
	    	//}
	    	
	    	// MAIN
	    	//ladiesMedIlRider.put("ladiesMedIlRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus)));
	    	if("01".equals(kurs_id))
	        {
	    		ladiesMedIlRider.put("ladiesMedIlRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus))+".00");
	    		ladiesMedIlRider.put("ladiesMedIlRiderTahunan", editorUtil.convertToRoundedNoDigit(new BigDecimal(tahunan))+".00");
	    		ladiesMedIlRider.put("ladiesMedIlRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		ladiesMedIlRider.put("ladiesMedIlRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		ladiesMedIlRider.put("ladiesMedIlRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		ladiesMedIlRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(0, RoundingMode.HALF_UP));
	    		ladiesMedIlRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(0, RoundingMode.HALF_UP));
	    		ladiesMedIlRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		ladiesMedIlRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		ladiesMedIlRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	        }else if("02".equals(kurs_id))
	        {
	        	ladiesMedIlRider.put("ladiesMedIlRiderSekaligus", editorUtil.convertToRoundedTwoDigits(new BigDecimal(sekaligus)));
	    		ladiesMedIlRider.put("ladiesMedIlRiderTahunan", editorUtil.convertToRoundedTwoDigits(new BigDecimal(tahunan)));
	    		ladiesMedIlRider.put("ladiesMedIlRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		ladiesMedIlRider.put("ladiesMedIlRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		ladiesMedIlRider.put("ladiesMedIlRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		ladiesMedIlRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(2, RoundingMode.HALF_UP));
	    		ladiesMedIlRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(2, RoundingMode.HALF_UP));
	    		ladiesMedIlRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		ladiesMedIlRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		ladiesMedIlRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	        }
    	
    	}
    	return ladiesMedIlRider;
    }
    
    public Map<String, Object> of_get_coi_hcp_pro( String kurs_id, int umur_tt, BigDecimal premiTotalSekaligus, BigDecimal premiTotalTahunan, BigDecimal premiTotalSemesteran, BigDecimal premiTotalTriwulanan, BigDecimal premiTotalBulanan )
    {
    	// EKA SEHAT RIDER 4
    	Map<String, Object> hcpProRider = new HashMap<String, Object>();
    	double sekaligus = 0;
    	double tahunan = 0;
    	double rateTahunan = 0;
    	double semesteran = 0;
    	double triwulanan = 0;
    	double bulanan = 0;
    	double ldec_max = 0;
    	
    	if( istr_prop.rider_baru[ 16 ] > 0 ){
    	
	    	int li_usia;
	    	int li_jenis = istr_prop.rider_baru[16];
	    	
	    	Map<String, Object> params = new HashMap<String, Object>();
	    	params.put( "riderBaru", li_jenis );   
	    	params.put( "kursId", istr_prop.kurs_id );
	    	params.put( "liUsia", umur_tt );
	
	    	rateTahunan = FormatNumber.round( LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProvider(params)), 2 );
	             
	    	for( int i = 1; i <= istr_prop.hcpPro.peserta; i++ )
	    	{
	    		li_usia = istr_prop.hcpPro.usia[ i ];
	    		Map<String, Object> paramsparticipants = new HashMap<String, Object>();
	    		paramsparticipants.put( "riderBaru", istr_prop.rider_baru[ 16 ]  );   
	    		paramsparticipants.put( "kursId", istr_prop.kurs_id );
	    		paramsparticipants.put( "liUsia", li_usia );
	    		double ldec_rate = LazyConverter.toDouble( eproposalManager.selectLdecRateHcpProviderPesertaTambahan( paramsparticipants ) );
	    		double ldec_temp = 0;
	    		ldec_temp = FormatNumber.round( ldec_rate * 0.9, 2 );
	    		rateTahunan += ldec_temp;
	    	}
	    	tahunan = tahunan + rateTahunan;
	    	// SEMESTERAN
	    	semesteran = semesteran + (rateTahunan * 0.65);
	    	// TRIWULANAN
	    	triwulanan = triwulanan + (rateTahunan * 0.35);
	    	// BULANAN
	    	bulanan = bulanan + (rateTahunan * 0.10);
	    	
	    	// MAIN
	    	//hcpProRider.put("hcpProRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus)));
	    	if("01".equals(kurs_id))
	        {
	    		hcpProRider.put("hcpProRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus))+".00");
	    		hcpProRider.put("hcpProRiderTahunan", editorUtil.convertToRoundedNoDigit(new BigDecimal(tahunan))+".00");
	    		hcpProRider.put("hcpProRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		hcpProRider.put("hcpProRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		hcpProRider.put("hcpProRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		hcpProRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(0, RoundingMode.HALF_UP));
	    		hcpProRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(0, RoundingMode.HALF_UP));
	    		hcpProRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		hcpProRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		hcpProRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	        }else if("02".equals(kurs_id))
	        {
	        	hcpProRider.put("hcpProRiderSekaligus", editorUtil.convertToRoundedTwoDigits(new BigDecimal(sekaligus)));
	    		hcpProRider.put("hcpProRiderTahunan", editorUtil.convertToRoundedTwoDigits(new BigDecimal(tahunan)));
	    		hcpProRider.put("hcpProRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
	    		hcpProRider.put("hcpProRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
	    		hcpProRider.put("hcpProRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
	    		
	    		hcpProRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(2, RoundingMode.HALF_UP));
	    		hcpProRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(2, RoundingMode.HALF_UP));
	    		hcpProRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
	    		hcpProRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
	    		hcpProRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
	        }
	    	
    	}
    	return hcpProRider;
    }
    
}
