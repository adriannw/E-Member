package id.co.sinarmaslife.eproposal.product.product01040229;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040208Business
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

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Ilustration134Business;
import id.co.sinarmaslife.eproposal.common.pb.PbConverter;
import id.co.sinarmaslife.eproposal.common.pb.PbFunction;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.eproposal.model.pb.N_riders;
import id.co.sinarmaslife.eproposal.model.pb.S_biaya;
import id.co.sinarmaslife.eproposal.model.vo.Cepr00000000YieldResultVO;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
import id.co.sinarmaslife.eproposal.model.vo.ParticipantVO;
import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.eproposal.product.product01040208.Cepr01040208Mapper;
import id.co.sinarmaslife.eproposal.product.product01040211.Cepr01040211Mapper;
import id.co.sinarmaslife.eproposal.product.product01040223.Cepr01040223Mapper;
import id.co.sinarmaslife.eproposal.product.product01040228.Cepr01040228Mapper;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030104Form;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.MappingUtil;
import id.co.sinarmaslife.standard.util.MathUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cepr01040229Business extends Cepr01040000Ilustration134Business
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040229Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
        setSekaligusRightPartCdArr( new Integer[]{ } );
        setBerkalaRightPartCdArr( new Integer[]{ Cepr01040229Mapper.X1 , Cepr01040229Mapper.X2, Cepr01040229Mapper.X3});
        setInsuredAgeLimit( 100 );
                
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040223Mapper.X3 ){        
	       	 setInvestmentTitleArr( new String[] {
	                 "",
	                 " EXCELLINK Fixed Income Fund Syariah",
	                 " EXCELLINK Dynamic Fund Syariah",
	                 " EXCELLINK Aggressive Fund Syariah",
	                 " Simas Fixed Income Fund Syariah",
	                 " Simas Dynamic Fund Syariah",
	                 " Simas Aggressive Fund Syariah",
	                 " EXCELLINK Cash Fund Syariah",
	                 " EXCELLINK Secure Dollar Income Syariah",
	                 " EXCELLINK Dynamic Dollar Fund Syariah"
	             } ); 
       	
	         setInvestmentTitleAndDescrArr( new String[][] {
	      	            { "", "", "" },
	      	            { "", "- " +  "EXCELLINK Fixed Income Fund Syariah:"+ " Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang", "" },
	      	            { "", "- " +  "Simas Fixed Income Fund Syariah:"+ " Merupakan penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang", "" },
	      	            { "", "- " +  "EXCELLINK Dynamic Fund Syariah:"+ " Penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas)", "" },
	      	            { "", "- " +  "Simas Dynamic Fund Syariah:"+ " Merupakan penempatan maksimum 79% (tujuh puluh sembilan perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan pasar modal (ekuitas)", "" },
	    	            { "", "- " +  "EXCELLINK Aggresive Fund Syariah:"+ " Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dan/atau maksimum penempatan 20% (dua puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar uang", "" },
	    	            { "", "- " +  "Simas Aggressive Fund Syariah:"+ " Merupakan penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dan maksimum 20% (dua puluh perseratus)  instrumen-instrumen investasi pasar uang", "" },
	    	            { "", "- " +  "Excellink Cash Fund Syariah:"+  " Penempatan dana investasi 100% pada instrumen investasi pasar uang", "" },
	    	            { "", "- EXCELLINK Secure Dollar Income Fund Syariah:"+ "penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang, dimana semua instrumen-instrumen investasi tersebut dalam mata uang US Dollar", null },
	      	            { "", "- EXCELLINK Dynamic Dollar Fund Syariah: penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas), dimana semua instrumen-instrumen investasi tersebut dalam mata uang US Dollar", null },
	      	   } );        	
       } else{
    	   
       
        String productLabel = "EXCELLINK"; 
        String productLabelFixedFund = "EXCELLINK", productLabelDynamicFund = "EXCELLINK", productLabelAggresiveFund ="EXCELLINK";
       
        
	        setInvestmentTitleArr( new String[] {
	                "",
	                productLabel + " Fixed Income Fund Syariah",
	                productLabel + " Dynamic Fund Syariah",
	                productLabel + " Aggressive Fund Syariah",
	                productLabel + " Cash Fund Syariah",
	                productLabel + " Stable Dollar Fund",
	                productLabel + " Balance Dollar Fund"
	            } );
	               
	        setInvestmentTitleAndDescrArr( new String[][] {
	            { "", "", "" },
	            { "", "- " + "Excellink Fixed Income Fund Syariah", "Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang." },
	            { "", "- " + "Excellink Dynamic Fund Syariah", "Penempatan minimum 50% pada Fixed Income dan maksimum 50% pada Equity. Investasi berpendapatan tetap, ekuitas serta instrumen pasar uang" },
	            { "", "- " + "Excellink Aggresive Fund Syariah", "Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dan/atau maksimum penempatan 20% (dua puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar uang. " },
	            { "", "- " + "Excellink Cash Fund Syariah", "Penempatan dana investasi 100% pada instrumen investasi pasar uang" },
	            { "", "- Excellink Secure Dollar Income Fund Syariah:", "Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang." },
	            { "", "- Excellink Dynamic Dollar Fund Syariah", "Penempatan minimum 50% pada Fixed Income dan maksimum 50% pada Equity" },
	        } );
       }
        
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
        params.put( "pageCode", "sub2a" );
        result.add( params );
        
        //iga 20022020 change template
        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub2c" );
        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub2b" );
        result.add( params );
                
        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub2d" );
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
               
    	params = new HashMap<String, Object>();
    	params.put( "pageCode", "sub6" );
    	result.add( params );

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
        result.addAll( source.getInsuredNameSyariahMap() );
        result.addAll( source.getInsuredAgeSyariahMap() );
        result.addAll( source.getGenderInsuredSyariahMap() ); // IGA PROJECT NEW COI
        result.addAll( source.getTermOfContractWithLimitSyariahMap( insuredAgeLimit ) );

        Map<String, Object> params;
        if( sekaligusRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
            params = new HashMap<String, Object>();
            params.put( "label", "Kontribusi Pokok Sekaligus" );
            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() )+".00" );
            result.add( params );
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
                params.put( "label", "Kontribusi Pokok "+ label );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getBasePremium() ) );
                result.add( params );
        	} else{
  	          params = new HashMap<String, Object>();
  	          params.put( "label", "Kontribusi Pokok "+ label );
  	          params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getBasePremium() ) + " (" + cepr01030102Form.getPremiumCombinationCd().toString() + "%)" );
  	          result.add( params );
  	
  	          params = new HashMap<String, Object>();
  	          params.put( "label", "Kontribusi Top Up Berkala "+ label );
  	          params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getTopUpPremium() ) + " (" + ( new BigDecimal( "100" ).subtract( cepr01030102Form.getPremiumCombinationCd() ) ).toString() + "%)" );
  	          result.add( params );
  		
                params = new HashMap<String, Object>();
                params.put( "label", "" );
                params.put( "content", "------------------------" );
                result.add( params );
                
                params = new HashMap<String, Object>();
                params.put( "label", "Total Kontribusi " + label );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) );
                result.add( params );
        	}
            
            if(!cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().toString().equals("0")){
            	params = new HashMap<String, Object>();
            	params.put( "label", "Kontribusi Top Up Single" );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount() )+".00");
                result.add( params );
            }

            params = new HashMap<String, Object>();
            params.put( "label", "Masa Pembayaran Kontribusi" );
            params.put( "content", editorUtil.convertToString( cepr01030102Form.getPremiumFurloughYear() ) +" Tahun" );
            result.add( params );
        }

        result.addAll( source.getTotalSumInsuredSyariahMap() );
        result.addAll( source.getPolicyHolderMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_pp" ) ) );
        result.addAll( source.getInsuredMedicalCheckUpSyariahMap( ( String ) of_cek_medis( command ).get( "medis_tt" ) ) );

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
        result.addAll( source.getInsuredNameSyariahMap() );
        result.addAll( source.getInsuredAgeSyariahMap() );
        result.addAll( source.getTermOfContractWithLimitSyariahMapInTwoRows( insuredAgeLimit ) );

        Map<String, Object> params;
        if( sekaligusRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
            params = new HashMap<String, Object>();
            params.put( "label", "Kontribusi Pokok Sekaligus" );
            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) );
            result.add( params );
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
        	
	       params = new HashMap<String, Object>();
	       params.put( "label", "Kontribusi Pokok " + label );
	       params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getBasePremium() ) + " (" + cepr01030102Form.getPremiumCombinationCd().toString() + "%)" );
	        result.add( params );
	
	       params = new HashMap<String, Object>();
	       params.put( "label", "Kontribusi Top Up Berkala " + label );
	       params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getTopUpPremium() ) + " (" + ( new BigDecimal( "100" ).subtract( cepr01030102Form.getPremiumCombinationCd() ) ).toString() + "%)" );
	       result.add( params );
         		
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
        		 params.put( "label", "Kontribusi Top Up Single" );
                 params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount() ) );
                 result.add( params ); 
        	} 
            
            params = new HashMap<String, Object>();
            params.put( "label", "Masa Pembayaran Kontribusi " );
            params.put( "content", "Tahun ke " + editorUtil.convertToString( cepr01030102Form.getPremiumFurloughYear() ) );
            result.add( params );
        }

        result.addAll( source.getTotalSumInsuredSyariahMap() );

        return result;
    }
    
//    public List<Map<String, Object>> getBonusList()
//    {
//        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
//        double[] ldec_bpp = {CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0, 0, 0.1, 0.1, 0.1, 0.5, 0, 0, 0, 0, 0.25};
//        double ldec_premi_setahun = 0;
//        double[] ldec_premi_bulan = new double[12 + 1];
//        int li_bagi = 1000;
//        String bonus = null;
//        String premiumTotal;
//        if(istr_prop.kurs_id.equals("02")){ 
//        	li_bagi = 1;
//        }
//        for( int i = 1; i <= 12; i++ )
//        {
//            ldec_premi_bulan[ i ] = 0;
//            if( i == 1 ) ldec_premi_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
//            if( Hardcode.PAY_MODE_CD_TRIWULANAN == cepr01030102Form.getPaymentModeCd() )
//            {
//                if( i == 4 || i == 7 || i == 10 ) ldec_premi_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
//            }
//            else if( Hardcode.PAY_MODE_CD_SEMESTERAN == cepr01030102Form.getPaymentModeCd() )
//            {
//                if( i == 7 ) ldec_premi_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
//            }
//            else if( Hardcode.PAY_MODE_CD_BULANAN == cepr01030102Form.getPaymentModeCd() )
//            {
//                ldec_premi_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
//            }
//            ldec_premi_setahun += ldec_premi_bulan[ i ];
//        }
//        int maxValue = 15 ;
//        if( cepr01030102Form.getPremiumFurloughYear() > 15 ) maxValue = cepr01030102Form.getPremiumFurloughYear();
//        for( int i = 1; i <= maxValue; i++ )
//        {
//            if( i <= istr_prop.cuti_premi )
//            {
//                premiumTotal = editorUtil.convertToStringWithoutCent( ldec_premi_setahun / li_bagi );
//            }
//            else
//            {
//                premiumTotal = "";
//            }
//            if( i <= 15 ) bonus = editorUtil.convertToStringWithoutCent( (ldec_premi_setahun / li_bagi) * ldec_bpp[i] );
//            else bonus = editorUtil.convertToStringWithoutCent( 0.0 );
//        	Map<String, Object> params;
//        	params = new HashMap<String, Object>();
//        	params.put( "yearNo", editorUtil.convertToString( i ) );
//        	params.put( "insuredAge", editorUtil.convertToString( cepr01030101Form.getInsuredAge() + i ) );
//        	params.put( "jmlPremiPokok", premiumTotal );
//        	params.put( "bonus", bonus );
//        	result.add( params );
//        }
//
//        return result;
//    }
    
  
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
        String bonus = "0.00";
        
        int li_ke = 0, li_bagi = 1000, li_hal = 3;
        double[] ldec_bak ;
        double[] ldec_hasil_ppokok = new double[3 + 1];
        double[] ldec_hasil_ptu = new double[3 + 1];
        double[] ldec_fph = {CommonConst.DUMMY_ZERO, 0, 0, 0.9, 0.85, 0.8};
        double ldec_bawal = 100000;
        double[] ldec_man_non = new double[2 + 1];
        double ldec_bak_tu = 0.03;
        double ldec_bak_tut = 0.03;
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040228Mapper.X3 ){
        	ldec_bak_tut = 0.05;
        }
        double ldec_akuisisi;
        double ldec_premi_invest;
        double[][] ldec_hasil_invest = new double[5 + 1][3 + 1];
      //  double[] ldec_tarik = { CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0 };
        double[] ldec_tarik = new double[] { CommonConst.DUMMY_ZERO, 0.5, 0.4, 0.3, 0.2, 0.1, 0.05, 0};
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
        ldec_mfc = 20000;
        //ldec_mfc = 15000;
        double[] flag_syarat_bonus_premi = new double[3 + 1];        
        double[] ldec_premi_ppokok_bulan = new double[12 + 1];
        double ldec_premi_ppokok_setahun = 0;
        double[] ldec_basic_wdraw = new double[3 + 1];

        if(istr_prop.kurs_id.equals("02")){ 
        	ldec_mfc = 2;
        	//ldec_mfc = 2;
        	li_bagi = 1;
        }
        for( int i = 1; i <= 12; i++ )
        {
            ldec_premi_bulan[ i ] = 0;
            if( i == 1 ){
            	ldec_premi_bulan[ i ] = istr_prop.premi;
            	ldec_premi_ppokok_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
            }
            if( Hardcode.PAY_MODE_CD_TRIWULANAN == cepr01030102Form.getPaymentModeCd() )
            {
                if( i == 4 || i == 7 || i == 10 ) {
                	ldec_premi_bulan[ i ] = istr_prop.premi;                
                	ldec_premi_ppokok_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
                }
            }
            else if( Hardcode.PAY_MODE_CD_SEMESTERAN == cepr01030102Form.getPaymentModeCd() )
            {
                if( i == 7 ) {
                	ldec_premi_bulan[ i ] = istr_prop.premi;
                	ldec_premi_ppokok_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
                }
            }
            else if( Hardcode.PAY_MODE_CD_BULANAN == cepr01030102Form.getPaymentModeCd() )
            {
                ldec_premi_bulan[ i ] = istr_prop.premi;
                ldec_premi_ppokok_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
            }
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
    		 flag_syarat_bonus_premi[i] = 0;
    		 ldec_basic_wdraw[i] = 0;
        }
        //
        ldec_manfaat = istr_prop.up;
        //    Biaya Akuisisi & asumsi penarikan (1 tarik, 1-nya tdk tarik)
        lstr = PbFunction.wf_get_biaya_84( istr_prop, command );
        ldec_bak = lstr.bak;
    	ldec_bunga = lstr.bunga;
    	int a, b;
    
    	 if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040229Mapper.X3 ){
    	     if( "01".equals( istr_prop.kurs_id ) )
             {
                  a = 1;       
                  b = 7;          
             }
             else
             {
                  a = 8;
                  b = 9;
             }    	 
    	 
    	 }else{
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


        double[] np = new double[4];
        double[] celaka = new double[4];
        double[] npbatal = new double[4]; //iga 20022020 change template
        
        boolean cekAdaTarik = false;
        double ldec_tarik_cek = 0;
        for( int i = 1; i <= istr_prop.ins_per; i++ )
        {
        	
        	 if( i <= ArrUtil.upperBound( lstr.tarik ) ) ldec_tarik_cek = lstr.tarik[ i ];
        	
        	 if( ldec_tarik_cek > 0){
        		 cekAdaTarik = true;
        		 break;
        	 }
        }
        
        
        int j;
        for( int i = 1; i <= istr_prop.ins_per; i++ )
        {
//        	surrender charge
        	ldec_sc = 0;
        	ldec_wdraw = 0;
        	ldec_topup = 0;
        	ldec_coi = of_get_coi_220( i );
        	
        	//if i <= Upperbound(lstr.tarik) Then	ldec_wdraw = lstr.tarik[i]
        	if(i <= ArrUtil.upperBound(lstr.topup))	ldec_topup = lstr.topup[i];
        	
        			
        			if(ArrUtil.upperBound(ldec_bak) > i ){
                		ldec_akuisisi = ldec_bak[i];
                	}else{
                		ldec_akuisisi = 0;
                	}		
        			
        	if(i <= 7) ldec_sc = ldec_tarik[i];
        	//
        	ldec_cost = (ldec_coi + ldec_mfc);
        	
        	for(int k = 1 ; k <= 3; k++){
        		if(i <= ArrUtil.upperBound(lstr.tarik)) ldec_wdraw = lstr.tarik[i];
        		for(int li_bulan = 1 ; li_bulan <= 12 ; li_bulan++){
        			ldec_cost = (ldec_coi + ldec_mfc);
        			ldec_premi_invest = 0;
        			ldec_ppokok = 0;
        			ldec_ptu = 0;
        			ldec_ff = 0;
        			ldec_aph = 0;
        			if(i <= istr_prop.cuti_premi){
        				ldec_premi_invest = ldec_premi_bulan[li_bulan] * (istr_prop.pct_premi / 100);
        				ldec_premi_invest += ((ldec_premi_bulan[li_bulan] * (100 - istr_prop.pct_premi) / 100));
        				//ldec_ppokok = ldec_premi_bulan[li_bulan] * (istr_prop.pct_premi / 100);
        				ldec_ppokok = ldec_premi_bulan[li_bulan] * (istr_prop.pct_premi / 100)* ( 1 - ldec_akuisisi);
        				ldec_ptu = (ldec_premi_bulan[li_bulan] * (100 - istr_prop.pct_premi) / 100);
        			}
        			if(li_bulan == 1) ldec_ptu += ldec_topup;
        			 if(li_bulan == 1) {
      	            	  ldec_basic_wdraw[k] = ldec_wdraw*( 1 + ldec_sc );
        			 }
        			//ldec_hasil_ppokok[k] = (ldec_ppokok + ldec_hasil_ppokok[k]) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12)));
        			       			
        			if(!cekAdaTarik){
        				flag_syarat_bonus_premi[k] = flag_syarat_bonus_premi( i , cepr01030102Form , k , ldec_premi_ppokok_setahun);
                	}
        			
        			
        			if(li_bulan == 12){
            		//ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k] ) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2) + flag_syarat_bonus_premi[k];
					//ldec_hasil_ppokok[k] =  (( ldec_ppokok + ldec_hasil_ppokok[k] ) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12))) ) - (ldec_basic_wdraw[k] * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12)))) + flag_syarat_bonus_premi[k];
					
					ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15))  + (ldec_hasil_ppokok[k]  * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15)) - (ldec_basic_wdraw[k] * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15)),2) + flag_syarat_bonus_premi[k];

        			}else{
        			ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2);
        			}
        			
        			if(i == 1){
        				logger.info("***-------ldec_bunga_avg[k] "+ldec_bunga_avg[k]);
    				}
        			//ldec_ppokok = ldec_ppokok * ((1 + ldec_bunga_avg[k])^(1/12))
        			//ldec_hasil_ppokok[k] = ldec_hasil_ppokok[k] * ((1 + ldec_bunga_avg[k])^(1/12)) + ldec_ppokok
        			ldec_hasil_ptu[k] = (ldec_ptu *( 1 - ldec_bak_tut) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12)))) + ((ldec_hasil_ptu[k]) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12)))) ;
        			//if k = 1 and i < 3 then messagebox(string(i) + '/' + string(li_bulan), string(ldec_hasil_ppokok[k], '#,##0.00'))
        			if(i == 1 && li_bulan == 1){ 
        		//		ldec_cost *= 2;
        				//ldec_pre_pp[k] = ldec_hasil_ppokok[k]
        				//ldec_pre_tu[k] = ldec_hasil_ptu[k]
        			}
        			
        		//	if(i == istr_prop.ins_per && li_bulan == 12) ldec_cost = 0;
        			
        	    	//if(i <= 2) ldec_ff = (ldec_hasil_ptu[k] * 0 / 12);
        	    	//if(i <= istr_prop.pay_per) ldec_ff += (ldec_hasil_ppokok[k] * 0.025 / 12);        	    	
        			if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040228Mapper.X3 ){
        				if(i <= 7) ldec_ff += (ldec_hasil_ppokok[k] * 0.0475 / 12);
        			}else{
        				if(i <= 7) ldec_ff += (ldec_hasil_ppokok[k] * 0.045 / 12);
        			}
        			
        	    	//premium holiday
        	    	if(i <= 5 && istr_prop.cuti_premi <= 5){ 
        	   // 		if(i > istr_prop.cuti_premi) ldec_aph = ldec_fph[i] / (1 - ldec_fph[i]) * (ldec_cost + ldec_ff);
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
        	    	//if k = 2 and i < 3 then messagebox(string(i) + '/' + string(li_bulan), string(ldec_ptu, '#,##0.00') + ' / ' + string(ldec_hasil_ptu[k], '#,##0.00') + ' / ' + string(ldec_cost, '#,##0.00') + ' / ' + string(ldec_ff, '#,##0.00'))
        	    	
        	    	/* Adrian-Withdraw
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
        	    	}*/
        		}
        		ldec_hasil_invest[1][k] = ldec_hasil_ppokok[k] + ldec_hasil_ptu[k];
        		
        		if( ldec_hasil_invest[ 1 ][ k ] <= 0 &&  ( i <= 10 ) ){
					lb_minus[k] = true;	               
				}
        		
        	//ldec_hasil_invest[1][k] -= (ldec_wdraw * (1 + ldec_sc));
        	}

            //j = cepr01030101Form.getInsuredAge() + i;
        	j = istr_prop.umur_tt + i;
            if( i <= 23 || j == 55 || j == 65 || j == 75 || j == 80 || j == 100 )
            {
                for( int k = 1; k <= 3; k++ )
                {
                    np[ k ] = FormatNumber.round( ldec_hasil_invest[ 1 ][ k ] / li_bagi, 0 );
                    celaka[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] + ldec_manfaat ) / li_bagi, 0 );
//                    npbatal[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] * (1 - ldec_sc)  ) / li_bagi, 0 ); //iga 20022020 change template
                    // Revisi NISA+NUNG untuk cb.reguler yg dikalikan surrender charge nilai premi pokoknya saja           
                    npbatal[ k ] = FormatNumber.round( ( (ldec_hasil_ppokok[k] * (1 - ldec_sc) ) + ldec_hasil_ptu[k] )/ li_bagi, 0 );
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
                    if( "01".equals( istr_prop.kurs_id ) ){
                    	topup = editorUtil.convertToString( topupDrawVO.getTopupAmount().divide( new BigDecimal( "1000" ) ) );
                        draw = editorUtil.convertToString( topupDrawVO.getDrawAmount().divide( new BigDecimal( "1000" ) ) );
                    }else{
                    	topup = editorUtil.convertToString( topupDrawVO.getTopupAmount() );
                        draw = editorUtil.convertToString( topupDrawVO.getDrawAmount() );
                    }                    
                    if( "0".equals( topup ) ) topup = "0.00";
                    if( "0".equals( draw ) ) draw = "0.00";
                }
                else
                {
                    topup = "0.00";
                    draw = "0.00";
                }    
               
                if(!cekAdaTarik){                	
	                double bonus2;
	                if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd()) )
	                {
	                	 bonus2 = flag_syarat_bonus_premi2( i, cepr01030102Form , ldec_premi_ppokok_setahun);
	                }else{
	                	 bonus2 = flag_syarat_bonus_premi2( i, cepr01030102Form , ldec_premi_ppokok_setahun)/1000;
	                }
	                if( bonus2 == 0 ) {
	                     	//bonus = null;
	                     	//bonus = " - ";
	                     	 bonus = "0.00";
	                }else{
	                     	bonus = editorUtil.convertToStringWithoutCentAndNillIfNegative(bonus2);
	                }                                            
                }                

                String valueLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 1 ] ) );
                String valueMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 2 ] ) );
                String valueHi = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 3 ] ) );
                
                //iga 20022020 change template
                String batalLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 1 ] ) );
                String batalMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 2 ] ) );
                String batalHi  =  editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 3 ] ) );
                
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
                map.put( "batalLow", batalLow ); //iga 20022020 change template
                map.put( "batalMid", batalMid );
                map.put( "batalHi", batalHi );
                map.put( "benefitLow", benefitLow );
                map.put( "benefitMid", benefitMid );
                map.put( "benefitHi", benefitHi );
                map.put( "bonus",  bonus );
                mapList.add( map );

            }
        }

        result.setValidityMsg( lb_minus[ 1 ] ? "Maaf, Proposal yang Anda buat Tidak Layak Jual." : "" );
        result.setIllustrationList( mapList );
        return result;

    }    
    
    
    public IllustrationResultVO getIllustrationResult2()
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
        String bonus = "0.00";
        
        int li_ke = 0, li_bagi = 1000, li_hal = 3, lama_tanggung = 0;
        double[] ldec_bak ;
        double[] ldec_hasil_ppokok = new double[3 + 1];
        double[] ldec_hasil_ptu = new double[3 + 1];
        double[] ldec_fph = {CommonConst.DUMMY_ZERO, 0, 0, 0.9, 0.85, 0.8};
        double ldec_bawal = 100000;
        double[] ldec_man_non = new double[2 + 1];
        double ldec_bak_tu = 0.03;
        double ldec_bak_tut = 0.03;
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040228Mapper.X4 ){
        	ldec_bak_tut = 0.05;
        }
        double ldec_akuisisi;
        double ldec_premi_invest;
        double[][] ldec_hasil_invest = new double[5 + 1][3 + 1];
      //  double[] ldec_tarik = { CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0 };
        double[] ldec_tarik = new double[] { CommonConst.DUMMY_ZERO, 0.5, 0.4, 0.3, 0.2, 0.1, 0.05, 0};
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
        ldec_mfc = 20000;
        //ldec_mfc = 15000;
        double[] flag_syarat_bonus_premi = new double[3 + 1];        
        double[] ldec_premi_ppokok_bulan = new double[12 + 1];
        double ldec_premi_ppokok_setahun = 0;
        double[] ldec_basic_wdraw = new double[3 + 1];

        if(istr_prop.kurs_id.equals("02")){ 
        	ldec_mfc = 2;
        	//ldec_mfc = 2;
        	li_bagi = 1;
        }
        for( int i = 1; i <= 12; i++ )
        {
            ldec_premi_bulan[ i ] = 0;
            if( i == 1 ){
            	ldec_premi_bulan[ i ] = istr_prop.premi;
            	ldec_premi_ppokok_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
            }
            if( Hardcode.PAY_MODE_CD_TRIWULANAN == cepr01030102Form.getPaymentModeCd() )
            {
                if( i == 4 || i == 7 || i == 10 ) {
                	ldec_premi_bulan[ i ] = istr_prop.premi;                
                	ldec_premi_ppokok_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
                }
            }
            else if( Hardcode.PAY_MODE_CD_SEMESTERAN == cepr01030102Form.getPaymentModeCd() )
            {
                if( i == 7 ) {
                	ldec_premi_bulan[ i ] = istr_prop.premi;
                	ldec_premi_ppokok_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
                }
            }
            else if( Hardcode.PAY_MODE_CD_BULANAN == cepr01030102Form.getPaymentModeCd() )
            {
                ldec_premi_bulan[ i ] = istr_prop.premi;
                ldec_premi_ppokok_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
            }
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
    		 flag_syarat_bonus_premi[i] = 0;
    		  ldec_basic_wdraw[i] = 0;
        }
        //
        ldec_manfaat = istr_prop.up;
        //    Biaya Akuisisi & asumsi penarikan (1 tarik, 1-nya tdk tarik)
        lstr = PbFunction.wf_get_biaya_84( istr_prop, command );
        ldec_bak = lstr.bak;
    	ldec_bunga = lstr.bunga;
    	int a, b;
    	
   	 	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040229Mapper.X3 ){
		     if( "01".equals( istr_prop.kurs_id ) )
	         {
	              a = 1;       
	              b = 7;          
	         }
	         else
	         {
	              a = 8;
	              b = 9;
	         }   	 
		}else{
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


        double[] np = new double[4];
        double[] celaka = new double[4];
        double[] npbatal = new double[4]; //adrian 26022020 change template
        
        boolean cekAdaTarik = false;
        double ldec_tarik_cek = 0;
        for( int i = 1; i <= istr_prop.ins_per; i++ )
        {
        	
        	 if( i <= ArrUtil.upperBound( lstr.tarik ) ) ldec_tarik_cek = lstr.tarik[ i ];
        	
        	 if( ldec_tarik_cek > 0){
        		 cekAdaTarik = true;
        		 break;
        	 }
        }
        
        lama_tanggung = 100 - istr_prop.umur_tt;
        int j;
        for( int i = 1; i <= istr_prop.ins_per; i++ )
        {
//        	surrender charge
        	ldec_sc = 0;
        	ldec_wdraw = 0;
        	ldec_topup = 0;
        	ldec_coi = of_get_coi_220( i );
        	
        	//if i <= Upperbound(lstr.tarik) Then	ldec_wdraw = lstr.tarik[i]
        	if(i <= ArrUtil.upperBound(lstr.topup))	ldec_topup = lstr.topup[i];
        	
        			
        			if(ArrUtil.upperBound(ldec_bak) > i ){
                		ldec_akuisisi = ldec_bak[i];
                	}else{
                		ldec_akuisisi = 0;
                	}		
        			
        	if(i <= 7) ldec_sc = ldec_tarik[i];
        	//
        	ldec_cost = (ldec_coi + ldec_mfc);
        	
        	for(int k = 1 ; k <= 3; k++){
        		if(i <= ArrUtil.upperBound(lstr.tarik)) ldec_wdraw = lstr.tarik[i];
        		for(int li_bulan = 1 ; li_bulan <= 12 ; li_bulan++){
        			ldec_cost = (ldec_coi + ldec_mfc);
        			ldec_premi_invest = 0;
        			ldec_ppokok = 0;
        			ldec_ptu = 0;
        			ldec_ff = 0;
        			ldec_aph = 0;
        			if(i <= lama_tanggung ){
        				ldec_premi_invest = ldec_premi_bulan[li_bulan] * (istr_prop.pct_premi / 100);
        				ldec_premi_invest += ((ldec_premi_bulan[li_bulan] * (100 - istr_prop.pct_premi) / 100));
        				//ldec_ppokok = ldec_premi_bulan[li_bulan] * (istr_prop.pct_premi / 100);
        				ldec_ppokok = ldec_premi_bulan[li_bulan] * (istr_prop.pct_premi / 100)* ( 1 - ldec_akuisisi);
        				ldec_ptu = (ldec_premi_bulan[li_bulan] * (100 - istr_prop.pct_premi) / 100);
        			}
        			if(li_bulan == 1) ldec_ptu += ldec_topup;
        			 if(li_bulan == 1) {
      	            	  ldec_basic_wdraw[k] = ldec_wdraw*( 1 + ldec_sc );
        			 }
        			//ldec_hasil_ppokok[k] = (ldec_ppokok + ldec_hasil_ppokok[k]) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12)));
        			       			
        			if(!cekAdaTarik){
        				flag_syarat_bonus_premi[k] = flag_syarat_bonus_premi( i , cepr01030102Form , k , ldec_premi_ppokok_setahun);
                	}
        			
        			
        			if(li_bulan == 12){
            		//ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k] ) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2) + flag_syarat_bonus_premi[k];
					//ldec_hasil_ppokok[k] =  (( ldec_ppokok + ldec_hasil_ppokok[k] ) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12))) ) - (ldec_basic_wdraw[k] * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12)))) + flag_syarat_bonus_premi[k];
					
					ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15))  + (ldec_hasil_ppokok[k]  * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15)) - (ldec_basic_wdraw[k] * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15)),2) + flag_syarat_bonus_premi[k];

        			}else{
        			ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2);
        			}
        			
        			if(i == 1){
        				logger.info("***-------ldec_bunga_avg[k] "+ldec_bunga_avg[k]);
    				}
        			//ldec_ppokok = ldec_ppokok * ((1 + ldec_bunga_avg[k])^(1/12))
        			//ldec_hasil_ppokok[k] = ldec_hasil_ppokok[k] * ((1 + ldec_bunga_avg[k])^(1/12)) + ldec_ppokok
        			ldec_hasil_ptu[k] = (ldec_ptu *( 1 - ldec_bak_tut) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12)))) + ((ldec_hasil_ptu[k]) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12)))) ;
        			//if k = 1 and i < 3 then messagebox(string(i) + '/' + string(li_bulan), string(ldec_hasil_ppokok[k], '#,##0.00'))
        			if(i == 1 && li_bulan == 1){ 
        		//		ldec_cost *= 2;
        				//ldec_pre_pp[k] = ldec_hasil_ppokok[k]
        				//ldec_pre_tu[k] = ldec_hasil_ptu[k]
        			}
        			
        		//	if(i == istr_prop.ins_per && li_bulan == 12) ldec_cost = 0;
        			
        	    	//if(i <= 2) ldec_ff = (ldec_hasil_ptu[k] * 0 / 12);
        	    	//if(i <= istr_prop.pay_per) ldec_ff += (ldec_hasil_ppokok[k] * 0.025 / 12);
        	    	
        			if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040228Mapper.X3 ){
        				if(i <= 7) ldec_ff += (ldec_hasil_ppokok[k] * 0.0475 / 12);
        			}else{
        				if(i <= 7) ldec_ff += (ldec_hasil_ppokok[k] * 0.045 / 12);
        			}
        	    	//premium holiday
        	    	if(i <= 5 && istr_prop.cuti_premi <= 5){ 
        	   // 		if(i > istr_prop.cuti_premi) ldec_aph = ldec_fph[i] / (1 - ldec_fph[i]) * (ldec_cost + ldec_ff);
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
        	    	//if k = 2 and i < 3 then messagebox(string(i) + '/' + string(li_bulan), string(ldec_ptu, '#,##0.00') + ' / ' + string(ldec_hasil_ptu[k], '#,##0.00') + ' / ' + string(ldec_cost, '#,##0.00') + ' / ' + string(ldec_ff, '#,##0.00'))
        	    	
        	    	/* Adrian-Withdraw
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
        	    	}*/
        		}
        		ldec_hasil_invest[1][k] = ldec_hasil_ppokok[k] + ldec_hasil_ptu[k];
        		if( ldec_hasil_invest[ 1 ][ k ] <= 0 &&  ( i <= 10 ) ){
					lb_minus[k] = true;	               
				}
        		
        	//ldec_hasil_invest[1][k] -= (ldec_wdraw * (1 + ldec_sc));
        	}

            //j = cepr01030101Form.getInsuredAge() + i;
        	j = istr_prop.umur_tt + i;
            if( i <= 23 || j == 55 || j == 65 || j == 75 || j == 80 || j == 100 )
            {
                for( int k = 1; k <= 3; k++ )
                {
                    np[ k ] = FormatNumber.round( ldec_hasil_invest[ 1 ][ k ] / li_bagi, 0 );
                    celaka[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] + ldec_manfaat ) / li_bagi, 0 );
                 // Revisi NISA+NUNG untuk cb.reguler yg dikalikan surrender charge nilai premi pokoknya saja           
                    npbatal[ k ] = FormatNumber.round( ( (ldec_hasil_ppokok[k] * (1 - ldec_sc) ) + ldec_hasil_ptu[k] )/ li_bagi, 0 );
                }

            	if(i <= lama_tanggung )
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
                    if( "01".equals( istr_prop.kurs_id ) ){
                    	topup = editorUtil.convertToString( topupDrawVO.getTopupAmount().divide( new BigDecimal( "1000" ) ) );
                        draw = editorUtil.convertToString( topupDrawVO.getDrawAmount().divide( new BigDecimal( "1000" ) ) );
                    }else{
                    	topup = editorUtil.convertToString( topupDrawVO.getTopupAmount() );
                        draw = editorUtil.convertToString( topupDrawVO.getDrawAmount() );
                    }                    
                    if( "0".equals( topup ) ) topup = "0.00";
                    if( "0".equals( draw ) ) draw = "0.00";
                }
                else
                {
                    topup = "0.00";
                    draw = "0.00";
                }
                
                
                if(!cekAdaTarik){                	
	                double bonus2;
	                if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd()) )
	                {
	                	 bonus2 = flag_syarat_bonus_premi2( i, cepr01030102Form , ldec_premi_ppokok_setahun);
	                }else{
	                	 bonus2 = flag_syarat_bonus_premi2( i, cepr01030102Form , ldec_premi_ppokok_setahun)/1000;
	                }
	                if( bonus2 == 0 ) {
	                     	//bonus = null;
	                     	//bonus = " - ";
	                     	 bonus = "0.00";
	                }else{
	                     	bonus = editorUtil.convertToStringWithoutCentAndNillIfNegative(bonus2);
	                }                                            
                }

                String valueLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 1 ] ) );
                String valueMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 2 ] ) );
                String valueHi = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 3 ] ) );

                //Adrian 26022020 change template
                String batalLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 1 ] ) );
                String batalMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 2 ] ) );
                String batalHi  =  editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 3 ] ) );
                
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
                map.put( "batalLow", batalLow );
                map.put( "batalMid", batalMid );
                map.put( "batalHi", batalHi );
                map.put( "benefitLow", benefitLow );
                map.put( "benefitMid", benefitMid );
                map.put( "benefitHi", benefitHi );
                map.put( "bonus",  bonus );
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
    
    public double flag_syarat_bonus_premi(int i, Cepr01030102Form cepr01030102Form , int k , double ldec_premi_ppokok_setahun){
    	double result = 0;
    	
    	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040228Mapper.X3 ){
    		
    		if( cepr01030102Form.getPremiumFurloughYear() >= 7 && cepr01030102Form.getPremiumFurloughYear() <= 9 ){    		
        		if( i == 7 && k == 1){
        			result =  FormatNumber.round((0.5 * ldec_premi_ppokok_setahun) * FormatNumber.round(Math.pow((1 + 0.04),( ( double ) 1/12)), 15),2) ;    //CR#: NCR/2020/08/019 FLAG VIP (IGA)	
       //     		ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2) + flag_syarat_bonus_premi[k];

        		}else if( i == 7 && k == 2){    		
        			result = (0.5 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.08),( ( double ) 1/12)));    
        		}else if( i == 7 && k == 3){
        			result = (0.5 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.10),( ( double ) 1/12)));
        		}
        	} else if( cepr01030102Form.getPremiumFurloughYear() >= 10 && cepr01030102Form.getPremiumFurloughYear() <= 14 ){
        		if( i == 7 && k == 1){
        			result =  ((0.5 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.04),( ( double ) 1/12))));    	//CR#: NCR/2020/08/019 FLAG VIP (IGA)				
        		}else if( i == 7 && k == 2){    		
        			result = (0.5 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.08),( ( double ) 1/12)));    
        		}else if( i == 7 && k == 3){
        			result = (0.5 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.10),( ( double ) 1/12)));
        		} 
        		else if( i == 10 && k == 1){    		
        			result = (0.1 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.04),( ( double ) 1/12)));  //CR#: NCR/2020/08/019 FLAG VIP (IGA)
        		}else if( i == 10 && k == 2){    			
        			result = (0.1 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.08),( ( double ) 1/12)));  
        		}if( i == 10 && k == 3){    			
        			result = (0.1 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.10),( ( double ) 1/12)));  
        		}    		
        	} else if( cepr01030102Form.getPremiumFurloughYear() >= 15){
        		if( i == 7 && k == 1){
        			result =  ((0.5 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.04),( ( double ) 1/12))));   //CR#: NCR/2020/08/019 FLAG VIP (IGA) 					
        		}else if( i == 7 && k == 2){    		
        			result = (0.5 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.08),( ( double ) 1/12)));    
        		}else if( i == 7 && k == 3){
        			result = (0.5 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.10),( ( double ) 1/12)));
        		} 
        		else if( i == 10 && k == 1){    		
        			result = (0.1 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.04),( ( double ) 1/12)));  //CR#: NCR/2020/08/019 FLAG VIP (IGA)
        		}else if( i == 10 && k == 2){    			
        			result = (0.1 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.08),( ( double ) 1/12)));  
        		}else if( i == 10 && k == 3){    			
        			result = (0.1 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.10),( ( double ) 1/12)));  
        		}else if( i == 15 && k == 1){    			
        			result = (0.25 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.04),( ( double ) 1/12))); //CR#: NCR/2020/08/019 FLAG VIP (IGA)
        		}else if( i == 15 && k == 2){    		
        			result = (0.25 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.08),( ( double ) 1/12))); 
        		}else if( i == 15 && k == 3){    		
        			result = (0.25 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.10),( ( double ) 1/12))); 
        		}    		   		    		
        	}    		
    		    		
    	}else{    	
    	
    	if( cepr01030102Form.getPremiumFurloughYear() >= 7 && cepr01030102Form.getPremiumFurloughYear() <= 14 ){    		
    		if( i == 7 && k == 1){
    			result =  FormatNumber.round((0.5 * ldec_premi_ppokok_setahun) * FormatNumber.round(Math.pow((1 + 0.04),( ( double ) 1/12)), 15),2) ;   //CR#: NCR/2020/08/019 FLAG VIP (IGA) 	
   //     		ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2) + flag_syarat_bonus_premi[k];

    		}else if( i == 7 && k == 2){    		
    			result = (0.5 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.08),( ( double ) 1/12)));    
    		}else if( i == 7 && k == 3){
    			result = (0.5 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.10),( ( double ) 1/12)));
    		}
    	} else if( cepr01030102Form.getPremiumFurloughYear() >= 15 && cepr01030102Form.getPremiumFurloughYear() <= 19 ){
    		if( i == 7 && k == 1){
    			result =  ((0.5 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.04),( ( double ) 1/12))));    					//CR#: NCR/2020/08/019 FLAG VIP (IGA)
    		}else if( i == 7 && k == 2){    		
    			result = (0.5 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.08),( ( double ) 1/12)));    
    		}else if( i == 7 && k == 3){
    			result = (0.5 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.10),( ( double ) 1/12)));
    		} 
    		else if( i == 15 && k == 1){    		
    			result = (0.25 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.04),( ( double ) 1/12)));  //CR#: NCR/2020/08/019 FLAG VIP (IGA)
    		}else if( i == 15 && k == 2){    			
    			result = (0.25 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.08),( ( double ) 1/12)));  
    		}if( i == 15 && k == 3){    			
    			result = (0.25 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.10),( ( double ) 1/12)));  
    		}    		
    	} else if( cepr01030102Form.getPremiumFurloughYear() >= 20){
    		if( i == 7 && k == 1){
    			result =  ((0.5 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.04),( ( double ) 1/12))));  //CR#: NCR/2020/08/019 FLAG VIP (IGA)  					
    		}else if( i == 7 && k == 2){    		
    			result = (0.5 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.08),( ( double ) 1/12)));    
    		}else if( i == 7 && k == 3){
    			result = (0.5 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.10),( ( double ) 1/12)));
    		} 
    		else if( i == 15 && k == 1){    		
    			result = (0.25 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.04),( ( double ) 1/12)));  //CR#: NCR/2020/08/019 FLAG VIP (IGA)
    		}else if( i == 15 && k == 2){    			
    			result = (0.25 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.08),( ( double ) 1/12)));  
    		}else if( i == 15 && k == 3){    			
    			result = (0.25 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.10),( ( double ) 1/12)));  
    		}else if( i == 20 && k == 1){    			
    			result = (0.25 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.04),( ( double ) 1/12))); //CR#: NCR/2020/08/019 FLAG VIP (IGA)
    		}else if( i == 20 && k == 2){    		
    			result = (0.25 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.08),( ( double ) 1/12))); 
    		}else if( i == 20 && k == 3){    		
    			result = (0.25 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.10),( ( double ) 1/12))); 
    		}    		   		    		
    	}
    	}
    //	if ( result == null ) result = 1;
    	return result;
    }
    
    public double flag_syarat_bonus_premi2(int i, Cepr01030102Form cepr01030102Form ,double ldec_premi_ppokok_setahun){
    	double result = 0;
    	
    	
    	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040229Mapper.X3 ){
    		if( cepr01030102Form.getPremiumFurloughYear() >= 7 && cepr01030102Form.getPremiumFurloughYear() <= 9 ){    		
        		if( i == 7 ){
        			result = 0.5 * ldec_premi_ppokok_setahun;    					
        		}
        	} else if( cepr01030102Form.getPremiumFurloughYear() >= 10 && cepr01030102Form.getPremiumFurloughYear() <= 14 ){
        		if( i == 7 ){
        			result = 0.5 * ldec_premi_ppokok_setahun;    					
        		}else if( i == 10 ){
        			result = 0.1 * ldec_premi_ppokok_setahun;    					
        		} 		
        	} else if( cepr01030102Form.getPremiumFurloughYear() >= 15){
        		if( i == 7 ){
        			result = 0.5 * ldec_premi_ppokok_setahun;    					
        		}else if( i == 10 ){
        			result = 0.1 * ldec_premi_ppokok_setahun; 
        		}else if( i == 15 ){
        			result = 0.25 * ldec_premi_ppokok_setahun;    					
        		}    		   		    		
        	}
    	}else{
	    	if( cepr01030102Form.getPremiumFurloughYear() >= 7 && cepr01030102Form.getPremiumFurloughYear() <= 14 ){    		
	    		if( i == 7 ){
	    			result = 0.5 * ldec_premi_ppokok_setahun;    					
	    		}
	    	} else if( cepr01030102Form.getPremiumFurloughYear() >= 15 && cepr01030102Form.getPremiumFurloughYear() <= 19 ){
	    		if( i == 7 ){
	    			result = 0.5 * ldec_premi_ppokok_setahun;    					
	    		}else if( i == 15 ){
	    			result = 0.25 * ldec_premi_ppokok_setahun;    					
	    		} 		
	    	} else if( cepr01030102Form.getPremiumFurloughYear() >= 20){
	    		if( i == 7 ){
	    			result = 0.5 * ldec_premi_ppokok_setahun;    					
	    		}else if( i == 15 ){
	    			result = 0.25 * ldec_premi_ppokok_setahun; 
	    		}else if( i == 20 ){
	    			result = 0.25 * ldec_premi_ppokok_setahun;    					
	    		}    		   		    		
	    	}  
    	}
    //	if ( result == null ) result = 1;
    	return result;
    }
    
    
    public List<Map<String, Object>> getMonthlyPremiumList( Cepr01030102Form cepr01030102Form ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getMonthlyPremiumList" );
        double ldec_coi;
        
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        Map<String, Object> params;
       
        ldec_coi = of_get_coi_basic_220( 1 );   
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
        
        if(cepr01030102Form.getJenisCd() != null){
           	if(	cepr01030102Form.getJenisCd().equals(1)	) titlePackage = " (Bronze)";
           	else if(	cepr01030102Form.getJenisCd().equals(2)	) titlePackage = " (Classic)";
         	else if(	cepr01030102Form.getJenisCd().equals(3)	) titlePackage = " (Silver)";
         	else if(	cepr01030102Form.getJenisCd().equals(4)	) titlePackage = " (Gold)";	
        }
        
        if(istr_prop.bisnis_id == 159){
        	 params.put( "productName", "SMiLe LINK" + titlePackage );
        }else if(istr_prop.bisnis_id == 116){
        	 params.put( "productName", "SMiLe LINK 88" + titlePackage );
        }else if(istr_prop.bisnis_id == 160){
       	 params.put( "productName", "SMiLe LINK SYARIAH" + titlePackage );
        }else if(istr_prop.bisnis_id == 140){
          	 params.put( "productName", "SMiLe LINK MEDIVEST" + titlePackage );
        }else if(istr_prop.bisnis_id == 190){
         	 params.put( "productName", istr_prop.nama_plan[ 1 ] + titlePackage );
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
                params.put( "productName", ln_riders.of_nama_syariah( i, cepr01030102Form, cepr01030103Form ) );// nama label di halaman pertama
                params.put( "monthlyPremium", editorUtil.convertToMoney( ldec_coi, cepr01030102Form ) );
                result.add( params );
                                
            }
        }

        return result;
    }
    
    
    public List<Map<String, Object>> getCommonHeaderRincianRowList() throws IOException
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
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 224 ){ //SYARIAH
            	
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
  	          params = new HashMap<String, Object>();
  	          params.put( "label", word_premi + " Pokok "+ label );
  	          params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getBasePremium() ) + " (" + cepr01030102Form.getPremiumCombinationCd().toString() + "%)" );
  	          result.add( params );
  	
  	          params = new HashMap<String, Object>();
  	          params.put( "label", word_premi + " Top Up Berkala "+ label );
  	          params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getTopUpPremium() ) + " (" + ( new BigDecimal( "100" ).subtract( cepr01030102Form.getPremiumCombinationCd() ) ).toString() + "%)" );
  	          result.add( params );
  		
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
            params.put( "label", "Masa Pembayaran " + word_premi  );
            params.put( "content", editorUtil.convertToString( cepr01030102Form.getPremiumFurloughYear() ) + " Tahun" );
            result.add( params );
        }
        
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 224 ){ //SYARIAH
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
    
    public String getPageRider( int currentIdx ) throws IOException
    { 
    	String result = null;
    
    	 if( currentIdx != 0 )
         {             
            result = "sub3";            
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
      
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040228Mapper.X3 ){
	       	 if( "01".equals( istr_prop.kurs_id ) )
	         {
	             a = 1;	        
	             b = 7;          
	         }
	         else
	         {
	             a = 8;
	             b = 9;
	         }
       }else{
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
       }
       

        double totalInvestmentAllocation = 0;
        double totalAsumptionLow = 0;
        double totalAsumptionMid = 0;
        double totalAsumptionHi = 0;

        double allocationYieldLow =0;
        double allocationYieldMid=0;
        double allocationYieldHi=0;

        double annualYieldLow;
        double annualYieldMid;
        double annualYieldHi;

        int j;
        for( i = a; i <= b; i++ )
        {
        	j=i;
        	if( "01".equals( istr_prop.kurs_id ) )
            {
        		
        	 /*if(CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiFixListDisplay()) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiDynamicListDisplay()) &&
             		CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiAggresiveListDisplay())	){
             	if(j>3){
             		j=j-3;
             	}        		 
             }*/
            }
        	 
            annualYieldLow = lstr.bunga[ j ][ 1 ] * 100;
            annualYieldMid = lstr.bunga[ j ][ 2 ] * 100;
            annualYieldHi = lstr.bunga[ j ][ 3 ] * 100;
            
            allocationYieldLow = annualYieldLow * istr_prop.fund[ j ] / 100;
	        allocationYieldMid = annualYieldMid * istr_prop.fund[ j ] / 100;
	        allocationYieldHi = annualYieldHi * istr_prop.fund[ j ] / 100;
            params = new HashMap<String, Object>();	
           
            // => 6 Pilihan Investasi Fund: EXCELLINK & Simas
        	 if("01".equals( istr_prop.kurs_id )){
        		   
        	         params.put( "fundDesc", ls_jenis[ i ] );
        			 params.put( "investmentAllocation", editorUtil.convertToTwoDigit( istr_prop.fund[ j ] ) + "%" );
        			 params.put( "allocationYieldLow", editorUtil.convertToTwoDigit( allocationYieldLow ) + "%" );
        	         params.put( "allocationYieldMid", editorUtil.convertToTwoDigit( allocationYieldMid ) + "%" );
        	         params.put( "allocationYieldHi", editorUtil.convertToTwoDigit( allocationYieldHi ) + "%" );
              
             }
        	 else if("02".equals( istr_prop.kurs_id )) {
        		 params.put( "fundDesc", ls_jenis[ i ] );
        		 params.put( "investmentAllocation", editorUtil.convertToTwoDigit( istr_prop.fund[ i ] ) + "%" );
                                   
                 params.put( "allocationYieldLow", editorUtil.convertToTwoDigit( allocationYieldLow ) + "%" );
                 params.put( "allocationYieldMid", editorUtil.convertToTwoDigit( allocationYieldMid ) + "%" );
                 params.put( "allocationYieldHi", editorUtil.convertToTwoDigit( allocationYieldHi ) + "%" );       		 
        	 }        	 
          
        	 
        	params.put( "annualYieldLow", editorUtil.convertToTwoDigit( annualYieldLow ) + "%" );
            params.put( "annualYieldMid", editorUtil.convertToTwoDigit( annualYieldMid ) + "%" );
            params.put( "annualYieldHi", editorUtil.convertToTwoDigit( annualYieldHi ) + "%" );
            yieldList.add( params );
            
            if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040228Mapper.X3 ){
              	 if( ("01".equals( istr_prop.kurs_id ) && i<8) )
              	 {
       	            totalInvestmentAllocation = totalInvestmentAllocation + istr_prop.fund[ j ];
       	            totalAsumptionLow = totalAsumptionLow + allocationYieldLow;
       	            totalAsumptionMid = totalAsumptionMid + allocationYieldMid;
       	            totalAsumptionHi = totalAsumptionHi + allocationYieldHi;
                   }            	
              }else{
            	 if( ("01".equals( istr_prop.kurs_id ) && i<5) ||  ("02".equals( istr_prop.kurs_id ) && i<7))
                 {
     	            totalInvestmentAllocation = totalInvestmentAllocation + istr_prop.fund[ j ];
     	            totalAsumptionLow = totalAsumptionLow + allocationYieldLow;
     	            totalAsumptionMid = totalAsumptionMid + allocationYieldMid;
     	            totalAsumptionHi = totalAsumptionHi + allocationYieldHi;
                 }
              }
        }        
        
        result.setYieldList( yieldList );
        result.setTotalInvestmentAllocation( editorUtil.convertToTwoDigit( totalInvestmentAllocation ) + "%" );
        result.setTotalAssumptionLow( editorUtil.convertToTwoDigit( totalAsumptionLow ) + "%" );
        result.setTotalAssumptionMid( editorUtil.convertToTwoDigit( totalAsumptionMid ) + "%" );
        result.setTotalAssumptionHi( editorUtil.convertToTwoDigit( totalAsumptionHi ) + "%" );

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
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getSecureUsd() != null && cepr01030102Form.getSecureUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                if( cepr01030102Form.getRightPartOfBusinessCd() < Cepr01040229Mapper.X1 ){
                	  params.put( "description", "- " + investmentTitleArr[ 5 ] );
                }else{
                	  params.put( "description", "- " + investmentTitleArr[ 4 ] );                	
                }
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getSecureUsd() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getSecureUsd() + "%" ) );
                result.add( params );
            }

            if( cepr01030102Form.getDynamicUsd() != null && cepr01030102Form.getDynamicUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                if( cepr01030102Form.getRightPartOfBusinessCd() < Cepr01040229Mapper.X1 ){
              	  params.put( "description", "- " + investmentTitleArr[ 6 ] );
              }else{
              	  params.put( "description", "- " + investmentTitleArr[ 5 ] );                	
              }
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getDynamicUsd() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicUsd() + "%" ) );
                result.add( params );
            }
        }

        return result;
    }
    
    
    public List<Map<String, Object>> getInvestmentChoiceSmallRowList2() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getInvestmentChoiceSmallRowList2" );

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
            
            if( cepr01030102Form.getFixSimasIdr() != null && cepr01030102Form.getFixSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
            	if( cepr01030102Form.getLjiFixSimasCd().equals("35") || cepr01030102Form.getLjiFixSimasCd().equals("58") ){
	                params = new HashMap<String, Object>();
	                params.put( "description", "- " + investmentTitleArr[ 4 ] );
	                params.put( "currencySymbol", currencySymbol );
	                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getFixSimasIdr() ).divide( new BigDecimal( "100" ) ) ) );
	                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getFixSimasIdr() + "%" ) );
	                result.add( params );
	            }
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

            if( cepr01030102Form.getDynamicSimasIdr() != null  && cepr01030102Form.getDynamicSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
            	if( cepr01030102Form.getLjiDynamicSimasCd().equals("36") || cepr01030102Form.getLjiDynamicSimasCd().equals("59") ){
	                params = new HashMap<String, Object>();
	                params.put( "description", "- " + investmentTitleArr[ 5 ] );
	                params.put( "currencySymbol", currencySymbol );
	                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getDynamicSimasIdr() ).divide( new BigDecimal( "100" ) ) ) );
	                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicSimasIdr() + "%" ) );
	                result.add( params );
            	}
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
            
            if( cepr01030102Form.getAggresiveSimasIdr() != null  && cepr01030102Form.getAggresiveSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
            	if( cepr01030102Form.getLjiAggresiveSimasCd().equals("37") || cepr01030102Form.getLjiAggresiveSimasCd().equals("60")){
            		 params = new HashMap<String, Object>();
                     params.put( "description", "- " + investmentTitleArr[ 6 ] );
                     params.put( "currencySymbol", currencySymbol );
                     params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getAggresiveSimasIdr() ).divide( new BigDecimal( "100" ) ) ) );
                     params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveSimasIdr() + "%" ) );
                     result.add( params );
            	}               
            }
            
            if( cepr01030102Form.getCashFundIdr() != null ){
            		if( cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 7 ] );                
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getCashFundIdr() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getCashFundIdr() + "%" ) );
                result.add( params );
            }}
                     
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getSecureUsd() != null && cepr01030102Form.getSecureUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                if( cepr01030102Form.getRightPartOfBusinessCd() < Cepr01040228Mapper.X3 ){
                	  params.put( "description", "- " + investmentTitleArr[ 6 ] );
                }else{
                	  params.put( "description", "- " + investmentTitleArr[ 4 ] );                	
                }
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getSecureUsd() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getSecureUsd() + "%" ) );
                result.add( params );
            }

            if( cepr01030102Form.getDynamicUsd() != null && cepr01030102Form.getDynamicUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                if( cepr01030102Form.getRightPartOfBusinessCd() < Cepr01040228Mapper.X3 ){
              	  params.put( "description", "- " + investmentTitleArr[ 7 ] );
              }else{
              	  params.put( "description", "- " + investmentTitleArr[ 5 ] );                	
              }
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getDynamicUsd() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicUsd() + "%" ) );
                result.add( params );
            }
        }

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
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getSecureUsd() != null && cepr01030102Form.getSecureUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
            	 if( cepr01030102Form.getRightPartOfBusinessCd() < Cepr01040229Mapper.X1 ){
            		 params = new HashMap<String, Object>();
                     params.put( "description", investmentTitleAndDescrArr[ 5 ][ 1 ] );
                     params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getSecureUsd() + "%" ) );
                     result.add( params );

                     for( int i = 2;  i < investmentTitleAndDescrArr[ 5 ].length; i++  )
                     {
                     	if( investmentTitleAndDescrArr[ 5 ][ i ] != null)
                     	{
     	                    params = new HashMap<String, Object>();
     	                    params.put( "description", investmentTitleAndDescrArr[ 5 ][ i ] );
     	                    params.put( "allocationPercent", "" );
     	                    result.add( params );
                     	}
                     }
            		 
            		 
            	 }else{
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
               
            }
            if( cepr01030102Form.getDynamicUsd() != null && cepr01030102Form.getDynamicUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
            	 if( cepr01030102Form.getRightPartOfBusinessCd() < Cepr01040229Mapper.X1 ){            		 
                     params = new HashMap<String, Object>();
                     params.put( "description", investmentTitleAndDescrArr[ 6 ][ 1 ] );
                     params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicUsd() + "%" ) );
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
            	 }else{
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
            }
        }

        return result;
    }
    
    
    public List<Map<String, Object>> getInvestmentChoiceRowList2() throws IOException
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
            if( cepr01030102Form.getFixSimasIdr() != null && cepr01030102Form.getFixSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 2 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getFixSimasIdr() + "%" ) );
                result.add( params );

                for( int i = 2;  i < investmentTitleAndDescrArr[ 2 ].length; i++  )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", investmentTitleAndDescrArr[ 2 ][ i ] );
                    params.put( "allocationPercent", "" );
                    result.add( params );
                }
            }
            
            if( cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 3 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicIdr() + "%" ) );
                result.add( params );

                for( int i = 2;  i < investmentTitleAndDescrArr[ 3 ].length; i++  )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", investmentTitleAndDescrArr[ 3 ][ i ] );
                    params.put( "allocationPercent", "" );
                    result.add( params );
                }
            }
            
            if( cepr01030102Form.getDynamicSimasIdr() != null && cepr01030102Form.getDynamicSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 4 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicSimasIdr() + "%" ) );
                result.add( params );

                for( int i = 2;  i < investmentTitleAndDescrArr[ 4 ].length; i++  )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", investmentTitleAndDescrArr[ 4 ][ i ] );
                    params.put( "allocationPercent", "" );
                    result.add( params );
                }
            }
            
            if( cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 5 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveIdr() + "%" ) );
                result.add( params );

                for( int i = 2;  i < investmentTitleAndDescrArr[ 5 ].length; i++  )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", investmentTitleAndDescrArr[ 5 ][ i ] );
                    params.put( "allocationPercent", "" );
                    result.add( params );
                }
            }
                        
            if( cepr01030102Form.getAggresiveSimasIdr() != null && cepr01030102Form.getAggresiveSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 6 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveSimasIdr() + "%" ) );
                result.add( params );

                for( int i = 2;  i < investmentTitleAndDescrArr[ 6 ].length; i++  )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", investmentTitleAndDescrArr[ 6 ][ i ] );
                    params.put( "allocationPercent", "" );
                    result.add( params );
                }
            }
            if( cepr01030102Form.getCashFundIdr() != null && cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
            	 params = new HashMap<String, Object>();
                 params.put( "description", investmentTitleAndDescrArr[ 7 ][ 1 ] );
                 params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getCashFundIdr() + "%" ) );
                 result.add( params );
                 
                 for( int i = 2;  i < investmentTitleAndDescrArr[ 7 ].length; i++  )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", investmentTitleAndDescrArr[ 7 ][ i ] );
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
                params.put( "description", investmentTitleAndDescrArr[ 9 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getSecureUsd() + "%" ) );
                result.add( params );

                for( int i = 2;  i < investmentTitleAndDescrArr[ 9 ].length; i++  )
                {
                	if( investmentTitleAndDescrArr[ 9 ][ i ] != null)
                	{
	                    params = new HashMap<String, Object>();
	                    params.put( "description", investmentTitleAndDescrArr[ 9 ][ i ] );
	                    params.put( "allocationPercent", "" );
	                    result.add( params );
                	}
                }
            }
            if( cepr01030102Form.getDynamicUsd() != null && cepr01030102Form.getDynamicUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 10 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicUsd() + "%" ) );
                result.add( params );
                for( int i = 2;  i < investmentTitleAndDescrArr[ 10 ].length; i++  )
                {
                	if( investmentTitleAndDescrArr[ 10 ][ i ] != null ){
	                    params = new HashMap<String, Object>();
	                    params.put( "description", investmentTitleAndDescrArr[ 10 ][ i ] );
	                    params.put( "allocationPercent", "" );
	                    result.add( params );
                	}
                }
            }
        }

        return result;
    }
    
    
    public double of_get_coi_basic_220( int ai_th )
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
        return ldec_total;
    }
  //SMiLe Medical Extra Syariah Maret 2021
    public String checkTipeEkaSehat(int rider_baru)
    {    	
    	  String result = null;
    	  if( istr_prop.rider_baru[ rider_baru ] > 0 )
          {      
    		  if (rider_baru == 29){
    			  result = "Extra";
    		  }
    		  
          }
    	  
    	  return result;
    }
    
}