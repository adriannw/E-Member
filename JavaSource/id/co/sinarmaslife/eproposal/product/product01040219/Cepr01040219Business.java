package id.co.sinarmaslife.eproposal.product.product01040219;

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

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Ilustration116Business;
import id.co.sinarmaslife.eproposal.common.pb.PbConverter;
import id.co.sinarmaslife.eproposal.common.pb.PbFunction;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.eproposal.model.pb.N_riders;
import id.co.sinarmaslife.eproposal.model.pb.S_biaya;
import id.co.sinarmaslife.eproposal.model.pb.S_medis;
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
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.MappingUtil;
import id.co.sinarmaslife.standard.util.MathUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cepr01040219Business extends Cepr01040000Ilustration116Business
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040219Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
        setSekaligusRightPartCdArr( new Integer[]{ Cepr01040219Mapper.X1, Cepr01040219Mapper.X3, Cepr01040219Mapper.X5, Cepr01040219Mapper.X7, } );
        setBerkalaRightPartCdArr( new Integer[]{ Cepr01040219Mapper.X2, Cepr01040219Mapper.X4, Cepr01040219Mapper.X6, Cepr01040219Mapper.X8 , Cepr01040219Mapper.X9} );
        setInsuredAgeLimit( 99 );
        
        if( cepr01030102Form.getRightPartOfBusinessCd() < Cepr01040219Mapper.X3 || ( cepr01030102Form.getRightPartOfBusinessCd() > Cepr01040219Mapper.X4 && cepr01030102Form.getRightPartOfBusinessCd() < Cepr01040219Mapper.X7 ) ){
       	 setInvestmentTitleArr( new String[] {
    	            "",
    	            "EXCELLINK Fixed Income Fund",
    	            "EXCELLINK Dynamic Fund",
    	            "EXCELLINK Aggressive Fund",
    	            "EXCELLINK Cash Fund",
    	            "EXCELLINK Equity Bakti Peduli",
    	            "EXCELLINK Secure Dollar Income Fund",
    	            "EXCELLINK Dynamic Dollar Fund"
    	        } );
    	        setInvestmentTitleAndDescrArr( new String[][] {
    	            { "", "", "" },
    	            { "", "- EXCELLINK Fixed Income Fund ", " Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang" },
    	            { "", "- EXCELLINK Dynamic Fund ", "Penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas)" },
    	            { "", "- EXCELLINK Aggressive Fund ", "Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dan/atau maksimum penempatan 20% (dua puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar uang" },
	   	            { "", "- EXCELLINK Cash Fund", "Penempatan dana investasi 100% pada instrumen investasi pasar uang" },
	   	            { "", "- Excellink Equity Bakti Peduli", " Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi pasar modal (ekuitas) dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang" },
	   	            { "", "- EXCELLINK Secure Dollar Income Fund ", "Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang, dimana semua instrumen-instrumen investasi tersebut dalam mata uang US Dollar" },
    	            { "", "- EXCELLINK Dynamic Dollar Fund ", "Penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas), dimana semua instrumen-instrumen investasi tersebut dalam mata uang US Dollar" },
    	        } );
       	
       }else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040219Mapper.X8 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040219Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040219Mapper.X3 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040219Mapper.X4 ){
        	 setInvestmentTitleArr( new String[] {
     	            "",
     	            "EXCELLINK Fixed Income Fund",
     	            "EXCELLINK Dynamic Fund",
     	            "EXCELLINK Aggressive Fund",
     	            "Simas Fixed Income Fund",
                    "Simas Dynamic Fund",
                    "Simas Aggressive Fund",
     	            "EXCELLINK Cash Fund",
     	            "EXCELLINK Equity Bakti Peduli",
     	            "EXCELLINK Secure Dollar Income Fund",
     	            "EXCELLINK Dynamic Dollar Fund"
     	        } );
     	        setInvestmentTitleAndDescrArr( new String[][] {
     	            { "", "", "" },
     	            { "", "- " +  "EXCELLINK Fixed Income Fund : "+ " Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang" },
     	            { "", "- " +  "Simas Fixed Income Fund :  "+ "Merupakan penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang" },
     	            { "", "- " +  "EXCELLINK Dynamic Fund : "+ "Penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas)" },
     	            { "", "- " +  "Simas Dynamic Fund : "+ "Merupakan penempatan maksimum 79% (tujuh puluh sembilan perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan pasar modal (ekuitas)" },
     	           	{ "", "- " +  "EXCELLINK Aggressive Fund : "+ "Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dan/atau maksimum penempatan 20% (dua puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar uang" },
     	           	{ "", "- " +  "Simas Aggressive Fund : "+ "Merupakan penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dan maksimum 20% (dua puluh perseratus)  instrumen-instrumen investasi pasar uang"},
     	           	{ "", "- " +  "EXCELLINK Cash Fund : "+ "Penempatan dana investasi 100% pada instrumen investasi pasar uang" },
    	            { "", "- " +  "Excellink Equity Bakti Peduli : "+ " Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi pasar modal (ekuitas) dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang" },
    	            { "", "- " +  "EXCELLINK Secure Dollar Income Fund : "+ "Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang, dimana semua instrumen-instrumen investasi tersebut dalam mata uang US Dollar" },
     	            { "", "- " +  "EXCELLINK Dynamic Dollar Fund : "+ "Penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas), dimana semua instrumen-instrumen investasi tersebut dalam mata uang US Dollar" },
     	        } );
        	
        }else{
	        setInvestmentTitleArr( new String[] {
	            "",
	            "EXCELLINK Fixed Income Fund",
	            "EXCELLINK Dynamic Fund",
	            "EXCELLINK Aggressive Fund",
	            "EXCELLINK Secure Dollar Income Fund",
	            "EXCELLINK Dynamic Dollar Fund"
	        } );
	        setInvestmentTitleAndDescrArr( new String[][] {
	            { "", "", "" },
	            { "", "- EXCELLINK Fixed Income Fund ", " Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang" },
	            { "", "- EXCELLINK Dynamic Fund ", "Penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas)" },
	            { "", "- EXCELLINK Aggressive Fund ", "Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dan/atau maksimum penempatan 20% (dua puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar uang" },
	            { "", "- EXCELLINK Secure Dollar Income Fund ", "Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang" },
	            { "", "- EXCELLINK Dynamic Dollar Fund ", "Penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas), dimana semua instrumen-instrumen investasi tersebut dalam mata uang US Dollar" },
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
        params.put( "pageCode", "sub2" );
        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub2b" );
        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub7" );
        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub7b" );
        result.add( params );
               
        // if no riders, don't show this page ( page 3 )
        if( currentIdx != 0 )
        {
            params = new HashMap<String, Object>();
            params.put( "pageCode", "sub3" );
            result.add( params );
            
            params = new HashMap<String, Object>();
            params.put( "pageCode", "sub5" );
            result.add( params );
        }

//        params = new HashMap<String, Object>();
//        params.put( "pageCode", "sub4" );
//        result.add( params );

        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub6" );
        result.add( params );
        
        return result;
    }
  
    public List<Map<String, Object>> getMonthlyPremiumList( Cepr01030102Form cepr01030102Form ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getMonthlyPremiumList" );
        double ldec_coi;
        
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        Map<String, Object> params;

        ldec_coi = of_get_coi_basic_190( 1 );
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
        
        if(istr_prop.bisnis_id == 190 && (istr_prop.bisnis_no == 7 || istr_prop.bisnis_no == 8) ){
        	 params.put( "productName", "SMiLe OPTIMA LINK" );
        }
        else if(istr_prop.bisnis_id == 190 && (istr_prop.bisnis_no == 3 || istr_prop.bisnis_no == 4) ){
       	 	params.put( "productName", "SMiLe LINK 99" );
        }
        else if(istr_prop.bisnis_id == 190){
       	 	params.put( "productName", istr_prop.nama_plan[ 1 ] + titlePackage );
        } else{
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
    
    
//  I got this from wf_set_116
  public IllustrationResultVO getIllustrationResult()
  {
      logger.info( "*-*-*-* Cepr01040000Ilustration116Business.getIllustrationResult" );
      IllustrationResultVO result = new IllustrationResultVO();
      Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
      Cepr01030101Form cepr01030101Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030101Form();
      Cepr01030104Form cepr01030104Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030104Form();
      CredentialsVO credentialsVO = ( ( Cepr01030000HoldingForm ) command ).getCredentialsVO();

      ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
      Map<String, Object> map;
      List<TopupDrawVO> topupDrawVOList = cepr01030104Form.getTopupDrawVOList();
      int topupDrawVOListSize = topupDrawVOList.size();
      TopupDrawVO topupDrawVO;
      String premiumTotal;
      String topup;
      String draw;
      
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
      double ldec_wdraw;
      double[] ldec_premi_bulan = new double[12 + 1];
      double[] premi_bulan_sp = new double[12 + 1];
      double  ldec_premi_setahun_sp = 0;
      double ldec_topup;
      double ldec_bass;
      double[][] ldec_bunga = new double[5 + 1][3 + 1];
      double[] ldec_bunga_avg = new double[3 + 1]; //= {0.06, 0.1, 0.08, 0.18, 0.12, 0.25} //{0.09, 0.06, 0.11, 0.15, 0.165, 0.25}  //fixed:0.09, 0.11, 0.15 (0.165); dynamic:0.08, 0.11, 0.18 (0.195)
      double ldec_fee = 0.020075;
      double ldec_coi = 0, ldec_mfc, ldec_sc, ldec_cost, ldec_man_celaka, ldec_temp = 0; //, ldec_man[10+1] = {1, 1, 1, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7}
      double specialOfferDouble = 0;
      boolean[] lb_minus = { CommonConst.DUMMY_FALSE, false, false, false };
      boolean lb_rider = false;
      String ls_sy = "", ls_temp; //ls_dpp = ' dari Premi Pokok', ls_sy = ''
      S_biaya lstr;
//n_riders ln_riders
      double ldec_manfaat, ldec_premi_setahun = 0, ldec_coi24 = 0;
      String bonus = "0";
////monthly fix cost
      ldec_mfc = 15000;
      if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
      {
          ldec_mfc = 2;
          li_bagi = 1;
      }

      if( istr_prop.bisnis_id == 162 || istr_prop.bisnis_id == 190 || istr_prop.bisnis_id == 200)
      {
      	//ldec_mfc = Biaya Administrasi
          ldec_mfc = 45000;
          if ( istr_prop.bisnis_id == 190 || istr_prop.bisnis_id == 200)  ldec_mfc = 27500;
          if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) ) ldec_mfc = 5;
      }

      if( istr_prop.bisnis_id == 115 || istr_prop.bisnis_id == 152 )
      {
          ldec_bak_tut = 0.01;
          ldec_bak_tu = 0.01;
	        ldec_tarik = new double[] { CommonConst.DUMMY_ZERO, 0.05, 0.04, 0.03, 0.02, 0.01 };
      }
      else if( istr_prop.bisnis_id == 138 || ( istr_prop.bisnis_id == 116 && istr_prop.bisnis_no == 6 ))
      {
          ldec_bak_tut = 0;
	        ldec_bak_tu = 0;
      }
     
  
      for( int i = 1; i <= 12; i++ )
      {
          ldec_premi_bulan[ i ] = 0;
          premi_bulan_sp[ i ] = 0;
          if( i == 1 ) {
          	ldec_premi_bulan[ i ] = istr_prop.premi;
          	if( cepr01030102Form.getBasePremium() != null)premi_bulan_sp[i] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
          }
          if( Hardcode.PAY_MODE_CD_TRIWULANAN == cepr01030102Form.getPaymentModeCd() )
          {
              if( i == 4 || i == 7 || i == 10 ) 
              	{
              		ldec_premi_bulan[ i ] = istr_prop.premi;
              		if( cepr01030102Form.getBasePremium() != null)premi_bulan_sp[i] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
              	}
          }
          else if( Hardcode.PAY_MODE_CD_SEMESTERAN == cepr01030102Form.getPaymentModeCd() )
          {
              if( i == 7 ) 
              {
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
      if( "01".equals( istr_prop.kurs_id ) )
      {
    	  if( istr_prop.bisnis_id == 190 && (istr_prop.bisnis_no == 7 || istr_prop.bisnis_no == 8 || istr_prop.bisnis_no == 3 || istr_prop.bisnis_no == 4) ){
	          a = 1;
	          b = 8;
    	  }
    	  else if( istr_prop.bisnis_id == 190 && (istr_prop.bisnis_no <= 6) ){
	          a = 1;
	          b = 5;
    	  }else{
    		  a = 1;
	          b = 3;
    	  }
      }
      else
      {
    	  if( istr_prop.bisnis_id == 190 && (istr_prop.bisnis_no == 7 || istr_prop.bisnis_no == 8 || istr_prop.bisnis_no == 3 || istr_prop.bisnis_no == 4) ){
	          a = 9;
	          b = 10;
    	  }
    	  else if( istr_prop.bisnis_id == 190 && ( istr_prop.bisnis_no <= 6 ) ){
	          a = 6;
	          b = 7;
    	  }else{
    		  a = 4;
	          b = 5;
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

      double[] np = new double[4];
      double[] celaka = new double[4];
      double[] npbatal = new double[4];
      
      if(istr_prop.bisnis_id == 159 || istr_prop.bisnis_id == 160)// ekalink family
      {
      	 specialOfferDouble =(ldec_premi_setahun_sp * 0.4);
      }else if(istr_prop.bisnis_id == 140){// medivest
      	 specialOfferDouble =(ldec_premi_setahun_sp * 0.3);
      }            
      
      int j;
                    
      for( int i = 1; i <= istr_prop.ins_per; i++ )
      {
          //surrender charge
          ldec_sc = 0;
          ldec_wdraw = 0;
          ldec_topup = 0;
          ldec_akuisisi = 0;
          // compute tabel Ilustrasi Perkembangan Dana
          ldec_coi = of_get_coi_190( i );
          if( i <= ArrUtil.upperBound( lstr.tarik ) ) ldec_wdraw = lstr.tarik[ i ];
          if( i <= ArrUtil.upperBound( lstr.topup ) ) ldec_topup = lstr.topup[ i ];
          if( ArrUtil.upperBound( ldec_bak ) > i ) ldec_akuisisi = ldec_bak[ i ];

          if( i <= 5 && ( istr_prop.bisnis_id == 115 || istr_prop.bisnis_id == 152 ) ) ldec_sc = ldec_tarik[i];

          if( cepr01030102Form.getLeftPartOfBusinessCd() == 162  )
          {
              if( i == 1 )
              {
                  //ass basic dinolin, ass rider tidk 23/08/07
                  ldec_temp = of_get_coi_basic( i );
                  ldec_coi = ldec_coi - ldec_temp;
                  //ldec_temp = ldec_temp / 2 //distahunkan /24
                  ldec_mfc = 0;
                  //ass rider pa, hcp, hcp fam ada (3/9/07)
                  for( int k = 1; k <= ArrUtil.upperBound( istr_prop.rider_baru ); k++ )
                  {
                      if( !( ( k == 1 ) || ( k == 2 ) || ( k == 11 ) || ( k == 13 ) || ( k == 15 )) )
                      {
                          if( istr_prop.rider_baru[ k ] > 0 )
                          {
                              ldec_coi -= of_get_coi_rider( k, 1 );
                              ldec_temp += of_get_coi_rider( k, 1 );
                          }
                      }
                      else
                      {
                          //pa, hcp, hcp fam tdk ditunda
                      }
                  }
                  ldec_temp = ldec_temp / 2;  //distahunkan /24
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
          }   
          if( cepr01030102Form.getLeftPartOfBusinessCd() == 190 )
          {
          	if( istr_prop.cb_id != 0 ){
          		if( i == 1 ){
          			ldec_temp = ldec_coi;
          			ldec_coi = 0;
          			ldec_mfc = 0;
          		// SE No.101/AJS-SE/VIII/2014 TTG KET.PEMOTONGAN BIAYA ADMINISTRASI DAN BIAYA ASURANSI TAHUN PERTAMA UNTUK PRODUK SMiLe LINK 99 SERIES          		
          		//}else if( i == 2 ){	
          		}else if( i >= 2 && i <= 5){
          			double kurs = 0.0;
          		     if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) )
                       {
          		    	 kurs = 5;
                       }else{
                      	 kurs = 27500;
                       }
          			ldec_coi24 = (ldec_temp + kurs) / 4;
          		}else{
          			ldec_coi24 = 0;
          		}
          		
          		if( i >= 2  ){
          			ldec_mfc = 27500;
          			if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) )
                      {
          				ldec_mfc = 5;
                      }
          		}
          	}
          }

          ldec_cost = ( ldec_coi + ldec_mfc + ldec_coi24 );

          for( int k = 1; k <= 3; k++ )
          {
              for( int li_bulan = 1; li_bulan <= 12; li_bulan++ )
              {
                  ldec_premi_invest = 0;
                  if( i <= cepr01030102Form.getPremiumFurloughYear() )
                  {
                      ldec_premi_invest = ( ( ldec_premi_bulan[ li_bulan ] * ( istr_prop.pct_premi / 100 ) ) * ( 1 - ldec_akuisisi ) );
                      ldec_premi_invest += ( ( ldec_premi_bulan[ li_bulan ] * ( 100 - istr_prop.pct_premi ) / 100 ) ) * ( 1 - ldec_bak_tu );  //topup berkala
                  }
                  if( li_bulan == 1 ) ldec_premi_invest += ( ldec_topup * ( 1 - ldec_bak_tut ) );  //topup tunggal
                  if( i == 1 ){
	                    // special offer hanya utk group mnc
	                    	if( credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_MNC ) && cepr01030102Form.getSpecialOfferCd() != null// utk group mnc
	                    			&& cepr01030102Form.getSpecialOfferCd().equals( Hardcode.ADDITIONAL_UNIT ) )
	                    	{	
	                    		if( li_bulan == 1 ) ldec_premi_invest += ( specialOfferDouble );  // additional unit
	                    	}
                  }
                  double copy = ldec_hasil_invest[ 1 ][ k ];

                  ldec_hasil_invest[ 1 ][ k ] = ( ldec_premi_invest + copy - ldec_cost ) * Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 ) );
              }

              ldec_hasil_invest[ 1 ][ k ] -= ( ldec_wdraw * ( 1 + ldec_sc ) );
              
              if( cepr01030102Form.getLeftPartOfBusinessCd() == 190 && (cepr01030102Form.getRightPartOfBusinessCd() < 5 || cepr01030102Form.getRightPartOfBusinessCd() > 8) &&  i> 1 ){
              	if( ldec_hasil_invest[ 1 ][ k ] <= 0 && ( ( istr_prop.umur_tt < 50 && i <= 15 ) || ( istr_prop.umur_tt >= 50 && i <= 10 ) ) )
	                {
	                    lb_minus[ k ] = true;
	                }                	
              }else if( cepr01030102Form.getLeftPartOfBusinessCd() == 190 && (cepr01030102Form.getRightPartOfBusinessCd() == 5 || cepr01030102Form.getRightPartOfBusinessCd() == 6  ) ) {
              //REQ: VITO 27/04/2015 : SE No.013/AJS-SK/IV/2015
      		  //VIP Family Plan (UNIT LINK [190-5/190-6] di BANCASSURANCE), Layak Jual, jika: 
  			  //1. Terdapat **/nil bukan pada tahun pertama di asumsi rendah, tapi tidak ada sama sekali **/nil di asumsi sedang 
  			  //  (Terdapat **/nil selain tahun ke-1 di asumsi rendah tapi tidak ada **/nil sampai usia 99 di asumsi sedang dan tinggi)
            	  if( i == 1 ){
  					if( ldec_hasil_invest[ 1 ][ k ] < 0 ){
  						lb_minus[ k ] = true;
  					}
  				}else{
  					if(k>=2){
      					if( ldec_hasil_invest[ 1 ][ k ] < 0 ){
      						lb_minus[ 1 ] = true;
      					}
      				}
  				} 
              }else if( cepr01030102Form.getLeftPartOfBusinessCd() == 190 && (cepr01030102Form.getRightPartOfBusinessCd() == 7 || cepr01030102Form.getRightPartOfBusinessCd() ==8  ) ) {
            	  if( ldec_hasil_invest[ 1 ][ k ] < 0 &&  ( i <= 10 ) ){
  					lb_minus[k] = true;	               
  					}
              }  
              else if( cepr01030102Form.getLeftPartOfBusinessCd() != 190){
              	if( ldec_hasil_invest[ 1 ][ k ] <= 0 && ( ( istr_prop.umur_tt < 50 && i <= 15 ) || ( istr_prop.umur_tt >= 50 && i <= 10 ) ) )
	                {
	                    lb_minus[ k ] = true;
	                }  
              }
          }
        
           j = istr_prop.umur_tt + i;    
          
          if( i <= 20|| j == 55 || j == 65 || j == 75 || j == 80 || j == 100 || ( j == 88 && istr_prop.bisnis_id == 162) || ((j == 90 || j == 95 || j == 99) && (istr_prop.bisnis_id == 190 || istr_prop.bisnis_id == 200 )) )
          {
              for( int k = 1; k <= 3; k++ )
              {
                  np[ k ] = FormatNumber.round( ldec_hasil_invest[ 1 ][ k ] / li_bagi, 0, istr_prop.kurs_id );
                  celaka[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] + ldec_manfaat ) / li_bagi, 0, istr_prop.kurs_id );
                  npbatal[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] * (1 - ldec_sc)  ) / li_bagi, 0, istr_prop.kurs_id );
              }
              if( i <= istr_prop.cuti_premi )
              {
                  premiumTotal = editorUtil.convertToStringWithoutCentAndNillIfNegative( ldec_premi_setahun / li_bagi );
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
                	  if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) ){
                		  	topup = editorUtil.convertToString( topupDrawVO.getTopupAmount() );
  	                    	draw = editorUtil.convertToString( topupDrawVO.getDrawAmount() );
                	  }else{
                		  topup = editorUtil.convertToString( topupDrawVO.getTopupAmount().divide( new BigDecimal( "1000" ) ) );
  	                    	draw = editorUtil.convertToString( topupDrawVO.getDrawAmount().divide( new BigDecimal( "1000" ) ) );                		  
                	  }
                  }
                  if( "0".equals( topup ) || "0.00".equals( topup ) ) topup = "0";
                  if( "0".equals( draw ) || "0.00".equals( draw ) ) draw = "0";
              }
              else
              {
                  topup = "0";
                  draw = "0";
              }
              map = new HashMap<String, Object>();
              
              if( i == 1 ){
              	String specialOffer = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( specialOfferDouble/1000 ) );
              	map.put( "specialOffer", specialOffer );
              }else{
              	map.put( "specialOffer", "" );
              }
              String valueLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 1 ] ) );
              String valueMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 2 ] ) );
              String valueHi = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 3 ] ) );

              String batalLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 1 ] ) );
              String batalMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 2 ] ) );
              String batalHi  =  editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 3 ] ) );
              
              String benefitLow = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 1 ], np[ 1 ] );
              String benefitMid = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 2 ], np[ 2 ] );
              String benefitHi = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 3 ], np[ 3 ] );
           
              if( i == 1 && istr_prop.cb_id != 0 && istr_prop.pct_premi == 100 ){
            	  //Rambe
            	  benefitLow =  editorUtil.convertToStringWithoutCent( ldec_manfaat / li_bagi);
             	  benefitMid =  editorUtil.convertToStringWithoutCent( ldec_manfaat / li_bagi);
             	  benefitHi =  editorUtil.convertToStringWithoutCent( ldec_manfaat / li_bagi);
             }
            	  
              map.put( "yearNo", editorUtil.convertToString( i ) );
             // map.put( "insuredAge", editorUtil.convertToString( cepr01030101Form.getInsuredAge() + i ) );                
              map.put( "insuredAge", editorUtil.convertToString( istr_prop.umur_tt + i ) );
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
  
  public String getPageRider( int currentIdx ) throws IOException
  { 
  	String result = null;
  
  	 if( currentIdx != 0 )
       {             
          result = "sub3";            
       }
  	   return result;
  } 
    
  public List<Map<String, Object>> getCommonHeaderRincianRowList() throws IOException
  {
      logger.info( "*-*-*-* Cepr01040201Business.getCommonHeaderRincianRowList" );

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
          word_premi = "Kontribusi/Premi";
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
//      ls_NKurs = Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() )? "Rp. " : "US$ ";

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
//          	if( cepr01030103Form.getCiChooseCd() != null ){
//          		ciChoose = new BigDecimal(cepr01030103Form.getCiChooseCd());
//          	}
//          	BigDecimal persentase = ciChoose.divide(new BigDecimal("100"));
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
      		
//          	if( cepr01030103Form.getEsci99ChooseCd() != null ){
//          		esci99Choose = new BigDecimal(cepr01030103Form.getEsci99ChooseCd());
//          	}
          	BigDecimal persentase = cepr01030103Form.getEsci99RiderAmount();
          		            	
      		ldec_min2 = (persentase.doubleValue() * 1.2)  + 75000000;
      		ldec_min3 = (persentase.doubleValue()* 1.3) ;
          	
      		ldec_temp = (persentase.doubleValue() * 1.3);
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
      //CR#: NCR/2020/08/019 FLAG VIP (IGA)
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
		
		
	if (!aktifValidasi){ //CR#: NCR/2020/08/019 FLAG VIP (IGA)
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
	}else if (aktifValidasi){ //CR#: NCR/2020/08/019 FLAG VIP (IGA)
		//--> new proses table medis 
        li_sar_new = astr_medis.sar_tt;
        li_kurs_new = cepr01030102Form.getCurrencyCd();
        
        if(CommonConst.CHECKED_TRUE.equals(cepr01030102Form.getFlagOfVIP())) {
           flag_vip = 1;
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

	}//DONE
	
      if ( istr_prop.kurs_id.equals("01") ){    		
		   if( li_usia <= 40 && astr_medis.sar_tt <= 2000000000 ){
			   ls_medis = "NM";
		   }
		}else{
			 if( li_usia <= 40 && astr_medis.sar_tt <= 250000 ){
				   ls_medis = "NM";
			   } 
	  }     
      
      if( ls_medis.equals( "N/A" ) )
      {
          astr_medis.medis_tt = "N/A (Silahkan hubungi Underwriting)";
      }
      else
      {
    	  if( istr_prop.bisnis_id == 190 && istr_prop.bisnis_no > 8 && istr_prop.bisnis_no < 11){
    		  astr_medis.medis_tt = "\"" + ls_medis.trim() + "\" ";
    	  }else{
    		  astr_medis.medis_tt = "\"" + ls_medis.trim() + "\", jika belum punya polis di Sinarmas MSIG Life";
    	  }        
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
	      }else if(aktifValidasi){ //CR#: NCR/2020/08/019 FLAG VIP (IGA)
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
        	  if( istr_prop.bisnis_id == 190 && istr_prop.bisnis_no > 8 && istr_prop.bisnis_no < 11){
        		  astr_medis.medis_pp = "\"" + ls_medis.trim() + "\" ";
        	  }else{
        		  astr_medis.medis_pp = "\"" + ls_medis.trim() + "\", jika belum punya polis di Sinarmas MSIG Life";
        	  }        	  
          }
      }

      result.put( "errorMessageList", errorMessageList );
      result.put( "ttMessageList", ttMessageList );
      result.put( "ppMessageList", ppMessageList );
      result.put( "medis_tt", astr_medis.medis_tt );
      result.put( "medis_pp", astr_medis.medis_pp );

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
           word_premi = "Kontribusi/Premi";
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
//     		(Helpdesk 56020): Paket-Bridge Agency tidak ditampilkan
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

      //Extra (COI/Mortalita/Premi) Atau Extra JOB ==> SMiLe Link, SMiLe Link 88, SMiLe Link Bridge,SMiLe Medivest 
      if(cepr01030102Form.getLeftPartOfBusinessCd() == 190 || cepr01030102Form.getLeftPartOfBusinessCd() == 159 ||  cepr01030102Form.getLeftPartOfBusinessCd() == 116 )
      { 
      	//result.addAll(of_get_Extra_Premi()); 
      }
      
      return result;
  }
  
  
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
  
  private String of_get_medis_new( Integer li_usia, double li_sar_new, String li_kurs_new, Integer flag_vip, Object command )//CR#: NCR/2020/08/019 FLAG VIP (IGA)
  {
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
  //DONE
  
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
                  params.put( "description", investmentTitleAndDescrArr[ 6 ][ 1 ] );
                  params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getSecureUsd() + "%" ) );
                  result.add( params );

                  for( int i = 2;  i < investmentTitleAndDescrArr[ 6 ].length; i++  )
                  {
                  	if( investmentTitleAndDescrArr[ 6 ][ i ] != null)
                  	{
    	                    params = new HashMap<String, Object>();
    	                    params.put( "description", investmentTitleAndDescrArr[ 6 ][ i ] );
    	                    params.put( "allocationPercent", "" );
    	                    result.add( params );
                  	}
                  }
        	  }
             
          
          if( cepr01030102Form.getDynamicUsd() != null && cepr01030102Form.getDynamicUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
          {
	              params = new HashMap<String, Object>();
	              params.put( "description", investmentTitleAndDescrArr[ 7 ][ 1 ] );
	              params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicUsd() + "%" ) );
	              result.add( params );
	              for( int i = 2;  i < investmentTitleAndDescrArr[ 7 ].length; i++  )
	              {
	              	if( investmentTitleAndDescrArr[ 7 ][ i ] != null ){
		                    params = new HashMap<String, Object>();
		                    params.put( "description", investmentTitleAndDescrArr[ 7 ][ i ] );
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
              params.put( "description", "- " + investmentTitleArr[ 6 ] );
                         
              params.put( "currencySymbol", currencySymbol );
              params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getSecureUsd() ).divide( new BigDecimal( "100" ) ) ) );
              params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getSecureUsd() + "%" ) );
              result.add( params ); 
          }

          if( cepr01030102Form.getDynamicUsd() != null && cepr01030102Form.getDynamicUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
          {
        	  params = new HashMap<String, Object>();
        	  params.put( "description", "- " + investmentTitleArr[ 7 ] );
                          
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
          
          if( cepr01030102Form.getFixSimasIdr() != null && cepr01030102Form.getFixSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
          {
          	if( cepr01030102Form.getLjiFixSimasCd().equals("35") ){
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
          	if( cepr01030102Form.getLjiDynamicSimasCd().equals("36") ){
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
          	if( cepr01030102Form.getLjiAggresiveSimasCd().equals("37") ){
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
              if( cepr01030102Form.getRightPartOfBusinessCd() < Cepr01040219Mapper.X3 ){
            	  params.put( "description", "- " + investmentTitleArr[ 4 ] );
              }else{
            	  params.put( "description", "- " + investmentTitleArr[ 7 ] );
              }
              params.put( "currencySymbol", currencySymbol );
              params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getCashFundIdr() ).divide( new BigDecimal( "100" ) ) ) );
              params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getCashFundIdr() + "%" ) );
              result.add( params );
          }}          
          if( cepr01030102Form.getExcellinkEquityIdr() != null && cepr01030102Form.getExcellinkEquityIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
          {
              params = new HashMap<String, Object>();
              if( cepr01030102Form.getRightPartOfBusinessCd() < Cepr01040219Mapper.X3 ){
            	  params.put( "description", "- " + investmentTitleArr[ 5 ] );
              }else{
            	  params.put( "description", "- " + investmentTitleArr[ 8 ] );
              }
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
              if( cepr01030102Form.getRightPartOfBusinessCd() < Cepr01040219Mapper.X3 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040219Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040219Mapper.X6 ){
            	  params.put( "description", "- " + investmentTitleArr[ 6 ] );
              }else{
            	  params.put( "description", "- " + investmentTitleArr[ 9 ] );
              }
              params.put( "currencySymbol", currencySymbol );
              params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getSecureUsd() ).divide( new BigDecimal( "100" ) ) ) );
              params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getSecureUsd() + "%" ) );
              result.add( params ); 
          }

          if( cepr01030102Form.getDynamicUsd() != null && cepr01030102Form.getDynamicUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
          {
              params = new HashMap<String, Object>();
              if( cepr01030102Form.getRightPartOfBusinessCd() < Cepr01040219Mapper.X3 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040219Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040219Mapper.X6 ){
            	  params.put( "description", "- " + investmentTitleArr[ 7 ] );
              }else{
            	  params.put( "description", "- " + investmentTitleArr[ 10 ] );
              }
              params.put( "currencySymbol", currencySymbol );
              params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getDynamicUsd() ).divide( new BigDecimal( "100" ) ) ) );
              params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicUsd() + "%" ) );
              result.add( params );
          }
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
      
      if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040219Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040219Mapper.X8
    	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040219Mapper.X3 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040219Mapper.X4 ){
	       	 if( "01".equals( istr_prop.kurs_id ) )
	         {
	             a = 1;	        
	             b = 8;          
	         }
	         else
	         {
	             a = 9;
	             b = 10;
	         }
      }else if( (cepr01030102Form.getLeftPartOfBusinessCd() == 190 && cepr01030102Form.getRightPartOfBusinessCd() <= 6) ){
      	if( "01".equals( istr_prop.kurs_id ) )
           {
               a = 1;
               b = 5;          
           }
           else
           {
               a = 6;
               b = 7;
           }
      }else{
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
      int j;
      for( i = a; i <= b; i++ )
      {
    	  j=i;
    	  
    	  annualYieldLow = lstr.bunga[ j ][ 1 ] * 100;
          annualYieldMid = lstr.bunga[ j ][ 2 ] * 100;
          annualYieldHi = lstr.bunga[ j ][ 3 ] * 100;
          
          allocationYieldLow = annualYieldLow * istr_prop.fund[ j ] / 100;
	      allocationYieldMid = annualYieldMid * istr_prop.fund[ j ] / 100;
	      allocationYieldHi = annualYieldHi * istr_prop.fund[ j ] / 100;

          params = new HashMap<String, Object>();
          
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
	          params.put( "allocationYieldMid", editorUtil.convertToTwoDigit( allocationYieldMid ) +  "%" );
	          params.put( "allocationYieldHi", editorUtil.convertToTwoDigit( allocationYieldHi ) + "%" );       		 
	 	  }  
          
          params.put( "fundDesc", ls_jenis[ i ] );
          params.put( "annualYieldLow", editorUtil.convertToTwoDigit( annualYieldLow ) + "%" );
          params.put( "annualYieldMid", editorUtil.convertToTwoDigit( annualYieldMid ) + "%" );
          params.put( "annualYieldHi", editorUtil.convertToTwoDigit( annualYieldHi ) + "%" );
//          params.put( "investmentAllocation", editorUtil.convertToTwoDigit( istr_prop.fund[ i ] ) + "%" );
//          params.put( "allocationYieldLow", editorUtil.convertToTwoDigit( allocationYieldLow ) + "%" );
//          params.put( "allocationYieldMid", editorUtil.convertToTwoDigit( allocationYieldMid ) + "%" );
//          params.put( "allocationYieldHi", editorUtil.convertToTwoDigit( allocationYieldHi ) + "%" );
          yieldList.add( params );

          if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040219Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() <= Cepr01040219Mapper.X8
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040219Mapper.X3 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040219Mapper.X4){
        	 if( ("01".equals( istr_prop.kurs_id ) && i<9) ||  ("02".equals( istr_prop.kurs_id ) && i<11))
        	 {
 	            totalInvestmentAllocation = totalInvestmentAllocation + istr_prop.fund[ j ];
 	            totalAsumptionLow = totalAsumptionLow + allocationYieldLow;
 	            totalAsumptionMid = totalAsumptionMid + allocationYieldMid;
 	            totalAsumptionHi = totalAsumptionHi + allocationYieldHi;
             }            	
         }else{
         	 
         		totalInvestmentAllocation = totalInvestmentAllocation + istr_prop.fund[ j ];
                totalAsumptionLow = totalAsumptionLow + allocationYieldLow;
                totalAsumptionMid = totalAsumptionMid + allocationYieldMid;
                totalAsumptionHi = totalAsumptionHi + allocationYieldHi;
                          	
         }
          
      }

      result.setYieldList( yieldList );
      result.setTotalInvestmentAllocation( editorUtil.convertToTwoDigit( totalInvestmentAllocation ) + "%" );
      result.setTotalAssumptionLow( editorUtil.convertToTwoDigit( totalAsumptionLow ) + "%" );
      result.setTotalAssumptionMid( editorUtil.convertToTwoDigit( totalAsumptionMid ) + "%" );
      result.setTotalAssumptionHi( editorUtil.convertToTwoDigit( totalAsumptionHi ) + "%" );

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
          if( cepr01030102Form.getCashFundIdr() != null ){
          	if( cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) > 0 )            
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
          
          if( cepr01030102Form.getExcellinkEquityIdr() != null && cepr01030102Form.getExcellinkEquityIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
          {
          	 params = new HashMap<String, Object>();
               params.put( "description", investmentTitleAndDescrArr[ 8 ][ 1 ] );
               params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getExcellinkEquityIdr() + "%" ) );
               result.add( params );
               
               for( int i = 2;  i < investmentTitleAndDescrArr[ 8 ].length; i++  )
               {
                   params = new HashMap<String, Object>();
                   params.put( "description", investmentTitleAndDescrArr[ 8 ][ i ] );
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
  
  public List<Map<String, Object>> getCommonHeaderSmallRowList3() throws IOException
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
      
     // result.addAll( source.getTermOfContractWithLimitMapInTwoRows( insuredAgeLimit ) );

      Map<String, Object> params;
      params = new HashMap<String, Object>();
      params.put( "label", "Masa Pertanggungan" );
      params.put( "content", editorUtil.convertToString( cepr01030102Form.getTermOfContract() ) + " tahun ");
      result.add( params );
            
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
    
  public List<Map<String, Object>> getNoteSekaligusRowList_Surrender()
  {
      List<Map<String, Object>> result;
      Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
      if( Hardcode.PAY_MODE_CD_SEKALIGUS == cepr01030102Form.getPaymentModeCd() )
          result = getOneRowList_Surrender();
      else
          result = new ArrayList<Map<String, Object>>();
      return result;
  }
  
  public List<Map<String, Object>> getNoteBerkalaRowList_Surrender()
  {
      List<Map<String, Object>> result;
      Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
      if( Hardcode.PAY_MODE_CD_SEKALIGUS != cepr01030102Form.getPaymentModeCd() )
          result = getOneRowList_Surrender();
      else
          result = new ArrayList<Map<String, Object>>();
      return result;
  }
  
  public List<Map<String, Object>> getOneRowList_Surrender()
  {
      List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
      Map<String, Object> params;

      params = new HashMap<String, Object>();
      params.put( "dummy", "rowSurrender" );
      result.add( params );
      return result;
  }
  
  
  public IllustrationResultVO getIllustrationResult2()
  {
      logger.info( "*-*-*-* Cepr01040000Ilustration116Business.getIllustrationResult" );
      IllustrationResultVO result = new IllustrationResultVO();
      Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
      Cepr01030101Form cepr01030101Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030101Form();
      Cepr01030104Form cepr01030104Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030104Form();
      CredentialsVO credentialsVO = ( ( Cepr01030000HoldingForm ) command ).getCredentialsVO();

      ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
      Map<String, Object> map;
      List<TopupDrawVO> topupDrawVOList = cepr01030104Form.getTopupDrawVOList();
      int topupDrawVOListSize = topupDrawVOList.size();
      TopupDrawVO topupDrawVO;
      String premiumTotal;
      String topup;
      String draw;
      
      int li_ke = 0, li_bagi = 1000, li_hal = 3, lama_tanggung = 0;
      double[] ldec_bak;
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
      double[] premi_bulan_sp = new double[12 + 1];
      double  ldec_premi_setahun_sp = 0;
      double ldec_topup;
      double ldec_bass;
      double[][] ldec_bunga = new double[5 + 1][3 + 1];
      double[] ldec_bunga_avg = new double[3 + 1]; //= {0.06, 0.1, 0.08, 0.18, 0.12, 0.25} //{0.09, 0.06, 0.11, 0.15, 0.165, 0.25}  //fixed:0.09, 0.11, 0.15 (0.165); dynamic:0.08, 0.11, 0.18 (0.195)
      double ldec_fee = 0.020075;
      double ldec_coi = 0, ldec_mfc, ldec_sc, ldec_cost, ldec_man_celaka, ldec_temp = 0; //, ldec_man[10+1] = {1, 1, 1, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7}
      double specialOfferDouble = 0;
      boolean[] lb_minus = { CommonConst.DUMMY_FALSE, false, false, false };
      boolean lb_rider = false;
      String ls_sy = "", ls_temp; //ls_dpp = ' dari Premi Pokok', ls_sy = ''
      S_biaya lstr;
//n_riders ln_riders
      double ldec_manfaat, ldec_premi_setahun = 0, ldec_coi24 = 0;
      String bonus = "0";
////monthly fix cost
      ldec_mfc = 15000;
      if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
      {
          ldec_mfc = 2;
          li_bagi = 1;
      }

      if( istr_prop.bisnis_id == 162 || istr_prop.bisnis_id == 190 || istr_prop.bisnis_id == 200)
      {
      	//ldec_mfc = Biaya Administrasi
          ldec_mfc = 45000;
          if ( istr_prop.bisnis_id == 190 || istr_prop.bisnis_id == 200)  ldec_mfc = 27500;
          if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) ) ldec_mfc = 5;
      }

      if( istr_prop.bisnis_id == 115 || istr_prop.bisnis_id == 152 )
      {
          ldec_bak_tut = 0.01;
          ldec_bak_tu = 0.01;
	        ldec_tarik = new double[] { CommonConst.DUMMY_ZERO, 0.05, 0.04, 0.03, 0.02, 0.01 };
      }
      else if( istr_prop.bisnis_id == 138 || ( istr_prop.bisnis_id == 116 && istr_prop.bisnis_no == 6 ))
      {
          ldec_bak_tut = 0;
	        ldec_bak_tu = 0;
      }
     
  
      for( int i = 1; i <= 12; i++ )
      {
          ldec_premi_bulan[ i ] = 0;
          premi_bulan_sp[ i ] = 0;
          if( i == 1 ) {
          	ldec_premi_bulan[ i ] = istr_prop.premi;
          	if( cepr01030102Form.getBasePremium() != null)premi_bulan_sp[i] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
          }
          if( Hardcode.PAY_MODE_CD_TRIWULANAN == cepr01030102Form.getPaymentModeCd() )
          {
              if( i == 4 || i == 7 || i == 10 ) 
              	{
              		ldec_premi_bulan[ i ] = istr_prop.premi;
              		if( cepr01030102Form.getBasePremium() != null)premi_bulan_sp[i] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
              	}
          }
          else if( Hardcode.PAY_MODE_CD_SEMESTERAN == cepr01030102Form.getPaymentModeCd() )
          {
              if( i == 7 ) 
              {
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
      if( "01".equals( istr_prop.kurs_id ) )
      {
    	  if( istr_prop.bisnis_id == 190 && (istr_prop.bisnis_no == 7 || istr_prop.bisnis_no == 8 || istr_prop.bisnis_no == 3 || istr_prop.bisnis_no == 4) ){
	          a = 1;
	          b = 8;
    	  }
    	  else if( istr_prop.bisnis_id == 190 && (istr_prop.bisnis_no <= 6) ){
	          a = 1;
	          b = 5;
    	  }else{
    		  a = 1;
	          b = 3;
    	  }
      }
      else
      {
    	  if( istr_prop.bisnis_id == 190 && (istr_prop.bisnis_no == 7 || istr_prop.bisnis_no == 8 || istr_prop.bisnis_no == 3 || istr_prop.bisnis_no == 4) ){
	          a = 9;
	          b = 10;
    	  }
    	  else if( istr_prop.bisnis_id == 190 && ( istr_prop.bisnis_no <= 6 ) ){
	          a = 6;
	          b = 7;
    	  }else{
    		  a = 4;
	          b = 5;
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

      double[] np = new double[4];
      double[] celaka = new double[4];
      double[] npbatal = new double[4];
      
      if(istr_prop.bisnis_id == 159 || istr_prop.bisnis_id == 160)// ekalink family
      {
      	 specialOfferDouble =(ldec_premi_setahun_sp * 0.4);
      }else if(istr_prop.bisnis_id == 140){// medivest
      	 specialOfferDouble =(ldec_premi_setahun_sp * 0.3);
      }            
      
      int j;
      lama_tanggung = 55 - istr_prop.umur_tt;   
      for( int i = 1; i <= istr_prop.ins_per; i++ )
      {
          //surrender charge
          ldec_sc = 0;
          ldec_wdraw = 0;
          ldec_topup = 0;
          ldec_akuisisi = 0;
          // compute tabel Ilustrasi Perkembangan Dana
          ldec_coi = of_get_coi_190( i );
          if( i <= ArrUtil.upperBound( lstr.tarik ) ) ldec_wdraw = lstr.tarik[ i ];
          if( i <= ArrUtil.upperBound( lstr.topup ) ) ldec_topup = lstr.topup[ i ];
          if( ArrUtil.upperBound( ldec_bak ) > i ) ldec_akuisisi = ldec_bak[ i ];

          if( i <= 5 && ( istr_prop.bisnis_id == 115 || istr_prop.bisnis_id == 152 ) ) ldec_sc = ldec_tarik[i];

          if( cepr01030102Form.getLeftPartOfBusinessCd() == 162  )
          {
              if( i == 1 )
              {
                  //ass basic dinolin, ass rider tidk 23/08/07
                  ldec_temp = of_get_coi_basic( i );
                  ldec_coi = ldec_coi - ldec_temp;
                  //ldec_temp = ldec_temp / 2 //distahunkan /24
                  ldec_mfc = 0;
                  //ass rider pa, hcp, hcp fam ada (3/9/07)
                  for( int k = 1; k <= ArrUtil.upperBound( istr_prop.rider_baru ); k++ )
                  {
                      if( !( ( k == 1 ) || ( k == 2 ) || ( k == 11 ) || ( k == 13 ) || ( k == 15 )) )
                      {
                          if( istr_prop.rider_baru[ k ] > 0 )
                          {
                              ldec_coi -= of_get_coi_rider( k, 1 );
                              ldec_temp += of_get_coi_rider( k, 1 );
                          }
                      }
                      else
                      {
                          //pa, hcp, hcp fam tdk ditunda
                      }
                  }
                  ldec_temp = ldec_temp / 2;  //distahunkan /24
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
          }   
          if( cepr01030102Form.getLeftPartOfBusinessCd() == 190 )
          {
          	if( istr_prop.cb_id != 0 ){
          		if( i == 1 ){
          			ldec_temp = ldec_coi;
          			ldec_coi = 0;
          			ldec_mfc = 0;
          		// SE No.101/AJS-SE/VIII/2014 TTG KET.PEMOTONGAN BIAYA ADMINISTRASI DAN BIAYA ASURANSI TAHUN PERTAMA UNTUK PRODUK SMiLe LINK 99 SERIES          		
          		//}else if( i == 2 ){	
          		}else if( i >= 2 && i <= 5){
          			double kurs = 0.0;
          		     if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) )
                       {
          		    	 kurs = 5;
                       }else{
                      	 kurs = 27500;
                       }
          			ldec_coi24 = (ldec_temp + kurs) / 4;
          		}else{
          			ldec_coi24 = 0;
          		}
          		
          		if( i >= 2  ){
          			ldec_mfc = 27500;
          			if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) )
                      {
          				ldec_mfc = 5;
                      }
          		}
          	}
          }

          ldec_cost = ( ldec_coi + ldec_mfc + ldec_coi24 );

          for( int k = 1; k <= 3; k++ )
          {
              for( int li_bulan = 1; li_bulan <= 12; li_bulan++ )
              {
                  ldec_premi_invest = 0;
                  if( i <= lama_tanggung )
                  {
                      ldec_premi_invest = ( ( ldec_premi_bulan[ li_bulan ] * ( istr_prop.pct_premi / 100 ) ) * ( 1 - ldec_akuisisi ) );
                      ldec_premi_invest += ( ( ldec_premi_bulan[ li_bulan ] * ( 100 - istr_prop.pct_premi ) / 100 ) ) * ( 1 - ldec_bak_tu );  //topup berkala
                  }
                  if( li_bulan == 1 ) ldec_premi_invest += ( ldec_topup * ( 1 - ldec_bak_tut ) );  //topup tunggal
                  if( i == 1 ){
	                    // special offer hanya utk group mnc
	                    	if( credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_MNC ) && cepr01030102Form.getSpecialOfferCd() != null// utk group mnc
	                    			&& cepr01030102Form.getSpecialOfferCd().equals( Hardcode.ADDITIONAL_UNIT ) )
	                    	{	
	                    		if( li_bulan == 1 ) ldec_premi_invest += ( specialOfferDouble );  // additional unit
	                    	}
                  }
                  double copy = ldec_hasil_invest[ 1 ][ k ];

                  ldec_hasil_invest[ 1 ][ k ] = ( ldec_premi_invest + copy - ldec_cost ) * Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 ) );
              }

              ldec_hasil_invest[ 1 ][ k ] -= ( ldec_wdraw * ( 1 + ldec_sc ) );
              
              if( cepr01030102Form.getLeftPartOfBusinessCd() == 190 && (cepr01030102Form.getRightPartOfBusinessCd() < 5 || cepr01030102Form.getRightPartOfBusinessCd() > 8) &&  i> 1 ){
              	if( ldec_hasil_invest[ 1 ][ k ] <= 0 && ( ( istr_prop.umur_tt < 50 && i <= 15 ) || ( istr_prop.umur_tt >= 50 && i <= 10 ) ) )
	                {
	                    lb_minus[ k ] = true;
	                }                	
              }else if( cepr01030102Form.getLeftPartOfBusinessCd() == 190 && (cepr01030102Form.getRightPartOfBusinessCd() == 5 || cepr01030102Form.getRightPartOfBusinessCd() == 6  ) ) {
              //REQ: VITO 27/04/2015 : SE No.013/AJS-SK/IV/2015
      		  //VIP Family Plan (UNIT LINK [190-5/190-6] di BANCASSURANCE), Layak Jual, jika: 
  			  //1. Terdapat **/nil bukan pada tahun pertama di asumsi rendah, tapi tidak ada sama sekali **/nil di asumsi sedang 
  			  //  (Terdapat **/nil selain tahun ke-1 di asumsi rendah tapi tidak ada **/nil sampai usia 99 di asumsi sedang dan tinggi)
            	  if( i == 1 ){
  					if( ldec_hasil_invest[ 1 ][ k ] < 0 ){
  						lb_minus[ k ] = true;
  					}
  				}else{
  					if(k>=2){
      					if( ldec_hasil_invest[ 1 ][ k ] < 0 ){
      						lb_minus[ 1 ] = true;
      					}
      				}
  				} 
              }else if( cepr01030102Form.getLeftPartOfBusinessCd() == 190 && (cepr01030102Form.getRightPartOfBusinessCd() == 7 || cepr01030102Form.getRightPartOfBusinessCd() ==8  ) ) {
            	  if( ldec_hasil_invest[ 1 ][ k ] < 0 &&  ( i <= 10 ) ){
  					lb_minus[k] = true;	               
  					}
              }  
              else if( cepr01030102Form.getLeftPartOfBusinessCd() != 190){
              	if( ldec_hasil_invest[ 1 ][ k ] <= 0 && ( ( istr_prop.umur_tt < 50 && i <= 15 ) || ( istr_prop.umur_tt >= 50 && i <= 10 ) ) )
	                {
	                    lb_minus[ k ] = true;
	                }  
              }
          }
        
           j = istr_prop.umur_tt + i;    
          
          if( i <= 20|| j == 55 || j == 65 || j == 75 || j == 80 || j == 100 || ( j == 88 && istr_prop.bisnis_id == 162) || ((j == 90 || j == 95 || j == 99) && (istr_prop.bisnis_id == 190 || istr_prop.bisnis_id == 200 )) )
          {
              for( int k = 1; k <= 3; k++ )
              {
                  np[ k ] = FormatNumber.round( ldec_hasil_invest[ 1 ][ k ] / li_bagi, 0, istr_prop.kurs_id );
                  celaka[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] + ldec_manfaat ) / li_bagi, 0, istr_prop.kurs_id );
                  npbatal[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] * (1 - ldec_sc)  ) / li_bagi, 0, istr_prop.kurs_id );
              }
              if( i <= lama_tanggung )
              {
                  premiumTotal = editorUtil.convertToStringWithoutCentAndNillIfNegative( ldec_premi_setahun / li_bagi );
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
                	  if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) ){
                		  	topup = editorUtil.convertToString( topupDrawVO.getTopupAmount() );
  	                    	draw = editorUtil.convertToString( topupDrawVO.getDrawAmount() );
                	  }else{
                		  topup = editorUtil.convertToString( topupDrawVO.getTopupAmount().divide( new BigDecimal( "1000" ) ) );
  	                    	draw = editorUtil.convertToString( topupDrawVO.getDrawAmount().divide( new BigDecimal( "1000" ) ) );                		  
                	  }
                  }
                  if( "0".equals( topup ) || "0.00".equals( topup ) ) topup = "0";
                  if( "0".equals( draw ) || "0.00".equals( draw ) ) draw = "0";
              }
              else
              {
                  topup = "0";
                  draw = "0";
              }
              map = new HashMap<String, Object>();
              
              if( i == 1 ){
              	String specialOffer = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( specialOfferDouble/1000 ) );
              	map.put( "specialOffer", specialOffer );
              }else{
              	map.put( "specialOffer", "" );
              }
              String valueLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 1 ] ) );
              String valueMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 2 ] ) );
              String valueHi = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 3 ] ) );

              String batalLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 1 ] ) );
              String batalMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 2 ] ) );
              String batalHi  =  editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 3 ] ) );
              
              String benefitLow = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 1 ], np[ 1 ] );
              String benefitMid = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 2 ], np[ 2 ] );
              String benefitHi = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 3 ], np[ 3 ] );
           
              if( i == 1 && istr_prop.cb_id != 0 && istr_prop.pct_premi == 100 ){
            	  //Rambe
            	  benefitLow =  editorUtil.convertToStringWithoutCent( ldec_manfaat / li_bagi);
             	  benefitMid =  editorUtil.convertToStringWithoutCent( ldec_manfaat / li_bagi);
             	  benefitHi =  editorUtil.convertToStringWithoutCent( ldec_manfaat / li_bagi);
             }
            	  
              map.put( "yearNo", editorUtil.convertToString( i ) );
             // map.put( "insuredAge", editorUtil.convertToString( cepr01030101Form.getInsuredAge() + i ) );                
              map.put( "insuredAge", editorUtil.convertToString( istr_prop.umur_tt + i ) );
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
  
  public List<Map<String, Object>> getCommonHeaderSmallRowList4() throws IOException
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
      //result.addAll( source.getTermOfContractWithLimitMap( insuredAgeLimit ) );

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
          
         
      }

      result.addAll( source.getTotalSumInsuredMap() );

      return result;
  }
  
}