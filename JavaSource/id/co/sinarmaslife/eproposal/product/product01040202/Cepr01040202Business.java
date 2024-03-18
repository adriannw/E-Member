package id.co.sinarmaslife.eproposal.product.product01040202;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040202Business
 * Description         	: Eka Link Family Syariah (160)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jan 23, 2008 9:56:41 AM
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

import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Ilustration116Business;
import id.co.sinarmaslife.eproposal.common.pb.PbConverter;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.eproposal.model.pb.N_riders;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.standard.util.ArrUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cepr01040202Business extends Cepr01040000Ilustration116Business
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040202Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
        setSekaligusRightPartCdArr( new Integer[]{ Cepr01040202Mapper.X1 } );
        setBerkalaRightPartCdArr( new Integer[]{ Cepr01040202Mapper.X2 } );
        setInsuredAgeLimit( 80 );

        String productLabel = "EXCELLINK";
      
        setInvestmentTitleArr( new String[] {
                "",
                productLabel + " Fixed Income Syariah",
                productLabel + " Dynamic Syariah",
                productLabel + " Aggressive Syariah",
                productLabel + " Secure Dollar Income Syariah",
                productLabel + " Dynamic Dollar Syariah"
            } );
        
        setInvestmentTitleAndDescrArr( new String[][] {
                { "", "", "" },
                { "", "- " + productLabel + " Fixed Income Syariah Fund ", " Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang."},
                { "", "- " + productLabel + " Dynamic Syariah Fund ", " Penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas)."},
                { "", "- " + productLabel + " Aggressive Syariah Fund ", "  Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dan/atau maksimum penempatan 20% (dua puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar uang" },
                { "", "- " + productLabel + " Secure Dollar Income Syariah Fund : Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang." },
                { "", "- " + productLabel + " Dynamic Dollar Syaria Fund : Penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas), dimana semua instrumen-instrumen investasi tersebut dalam mata uang US Dollar." },
            } );
        
        istr_prop = PbConverter.get_istr_prop( command, insuredAgeLimit );
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
        	if( cepr01030102Form.getEducationPackageCd().intValue() == 2 ) titlePackage = "(Paket A)";
        	else if( cepr01030102Form.getEducationPackageCd().intValue() == 3 ) titlePackage = "(Paket B)";
        	else if( cepr01030102Form.getEducationPackageCd().intValue() == 4 ) titlePackage = "(Paket C)";
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
        }else if(istr_prop.bisnis_id == 116){
        	 params.put( "productName", "SMiLe LINK 88" + titlePackage );
        }else if(istr_prop.bisnis_id == 160){
        	params.put( "productName", "SMiLe LINK SYARIAH" + titlePackage );
        }else{
        	 params.put( "productName", istr_prop.nama_plan[ 1 ] );
        }
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 140 ){// MEDIVEST
        	if( cepr01030102Form.getRightPartOfBusinessCd()  == 1 ) params.put( "productName", " SMiLe MEDIVEST" );
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
            
            params = new HashMap<String, Object>();
            params.put( "pageCode", "sub33" );
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
        String word_premi = "Kontribusi/Premi";
        result.addAll( source.getPolicyHolderNameMap() );
        result.addAll( source.getPolicyHolderAgeMap() );
        result.addAll( source.getInsuredNameSyariahMap() );
        result.addAll( source.getInsuredAgeSyariahMap() );
        result.addAll( source.getGenderInsuredSyariahMap() );
        result.addAll( source.getTermOfContractWithLimitSyariahMap( insuredAgeLimit ) );
       

        Map<String, Object> params;
        if( sekaligusRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
            params = new HashMap<String, Object>();
            params.put( "label", word_premi + " Pokok Sekaligus" );
            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) );
            result.add( params );
            
            if( cepr01030104Form.getTopupDrawVOList().get(0).getYearFlag() != null && !cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().toString().equals("0"))
       	 	{
  	        	params = new HashMap<String, Object>();
  	            params.put( "label", "Kontribusi/Premi Top Up Single" );
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
   	            params.put( "label", "Kontribusi/Premi Top Up Single" );
   	            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount() ) );
   	            result.add( params ); 
   	       	 }
        	 
            params = new HashMap<String, Object>();
            params.put( "label", "Asumsi cuti " + word_premi + " setelah" );
            params.put( "content", "Tahun ke " + editorUtil.convertToString( cepr01030102Form.getPremiumFurloughYear() ) );
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

        // Fill header content from CommonHeaderDataSource.
        String word_premi = "Kontribusi/Premi";
        result.addAll( source.getInsuredNameSyariahMap() );
        result.addAll( source.getInsuredAgeSyariahMap() );
        result.addAll( source.getTermOfContractWithLimitSyariahMap( insuredAgeLimit ) );

        Map<String, Object> params;
        if( sekaligusRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
            params = new HashMap<String, Object>();
            params.put( "label", "Premi Pokok Sekaligus" );
            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) );
            result.add( params );
            
            if( cepr01030104Form.getTopupDrawVOList().get(0).getYearFlag() != null && !cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().toString().equals("0"))
           	{
      	        	params = new HashMap<String, Object>();
      	            params.put( "label", "Kontribusi/Premi Top Up Single" );
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
   	            params.put( "label", "Kontribusi/Premi Top Up Single" );
   	            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount() ) );
   	            result.add( params ); 
   	       	}        	

            params = new HashMap<String, Object>();
            params.put( "label", "Asumsi cuti " + word_premi + " setelah" );
            params.put( "content", "Tahun ke " + editorUtil.convertToString( cepr01030102Form.getPremiumFurloughYear() ) );
            result.add( params );
        }

        result.addAll( source.getTotalSumInsuredSyariahMap() );

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
    
}
