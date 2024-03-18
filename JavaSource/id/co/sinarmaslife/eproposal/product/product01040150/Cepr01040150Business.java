package id.co.sinarmaslife.eproposal.product.product01040150;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040139Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : fadly m
 * Version              : 1.0
 * Creation Date    	: Jan 01, 2012 11:27:50 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000ProductBusiness;
import id.co.sinarmaslife.eproposal.common.pb.PbConverter;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000MoneyUtil;
import id.co.sinarmaslife.eproposal.product.product01040136.Cepr01040136Mapper;
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040150Business extends Cepr01040000ProductBusiness
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040150Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
    //    istr_prop = PbConverter.get_istr_prop( command, 50 );
    }

    public BigDecimal computePremiumWithSimpleMethod( BigDecimal totalSumInsured, BigDecimal rate, BigDecimal factor )
    {
        BigDecimal result;
        if( totalSumInsured != null && rate != null && factor != null )
        {
            result = totalSumInsured.multiply( rate ).multiply( factor ).multiply( CommonConst.PER_MIL );
        }
        else
        {
            result = null;
        }

        return result;
    }
    
    public BigDecimal computeManfaat( Integer payModeCd, Integer manfaatCd )
    {
        logger.info( "*-*-*-* Cepr01040139Business.computeManfaat" );

        Cepr01030101Form cepr01030101Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();

        int riderUnitQty;
        if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getPersonalAccidentFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getPersonalAccidentFlagDisplay() ) )
        {
            riderUnitQty = cepr01030102Form.getPersonalAccidentUnitQtyCd();
        }
        else
        {
            riderUnitQty = 1;
        }

        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put( "leftPartOfBusinessCd", manfaatCd );
        sqlParams.put( "lstabLamaTanggung", null );
        sqlParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_PREMI );
        sqlParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
        sqlParams.put( "paymentModeCd", payModeCd );
        sqlParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
        sqlParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
        sqlParams.put( "yearNoList", new Integer[]{ new Integer( "1" ) } );
        List<BigDecimal> rateList = eproposalManager.selectRate( sqlParams );
        BigDecimal rate;
        double manfaatPremium;
        if( rateList != null && rateList.size() > 0 )
        {
            rate = rateList.get( 0 );

            double totalSumInsured = cepr01030102Form.getTotalSumInsured().doubleValue();
            double maxUpRider = 0;
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                maxUpRider = 1000000000;
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                maxUpRider = 200000;
            }
            double upRider = totalSumInsured * riderUnitQty;
            upRider = MathUtil.min( upRider, maxUpRider );
            manfaatPremium = upRider * 2 * 0.001;

            logger.info( "*-*-*-* rate = " + rate );
            logger.info( "*-*-*-* manfaatPremium = " + manfaatPremium );
        }
        else
        {
            manfaatPremium = 0;
        }
        return new BigDecimal( manfaatPremium );
    }

    public BigDecimal computeManfaatPk( Integer payModeCd, Integer manfaatCd )
    {
        logger.info( "*-*-*-* Cepr01040139Business.computeManfaat" );
        double ldec_uppk = 0;
        if( istr_prop.kurs_id.equals( Hardcode.CUR_IDR_CD ) )
        {
            ldec_uppk = MathUtil.min( istr_prop.up / 2, 500000000 );
        }
        else if( istr_prop.kurs_id.equals( Hardcode.CUR_USD_CD ) )
        {
            ldec_uppk = MathUtil.min( istr_prop.up / 2, 50000 );
        }

        // this array used just to fit variable in PB
        double ldec_pct = 1;
        double[] lad_1 = new double[7];
        lad_1[6] = ldec_uppk * of_get_rate_for_pk() / 1000 * ldec_pct;

        return Cepr00000000MoneyUtil.rounding( new BigDecimal( lad_1[6] ) );
    }

    private double of_get_rate_for_pk()
    {
        logger.info( "*-*-*-* Cepr01040139Business.of_get_rate_for_pk" );
        double result;
        Cepr01030101Form cepr01030101Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        Map<String, Object> sqlParams = new HashMap<String, Object>();
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040136Mapper.X6  ||
        		cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040136Mapper.X7 ||
        		cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040136Mapper.X8 ||
        		cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040136Mapper.X9 ||
        		cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040136Mapper.X10 )
        {
        	sqlParams.put("businessNo", cepr01030102Form.getRightPartOfBusinessCd() - 4 );                	
        }
        else
        {
        	sqlParams.put("businessNo", cepr01030102Form.getRightPartOfBusinessCd() + 1 );	
        }
        sqlParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
        sqlParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
        BigDecimal rate = eproposalManager.selectPkRateForDanaSejahtera( sqlParams );
        result = LazyConverter.toDouble( rate );

        return result;
    }

    public double of_get_rate( Integer payModeCd, Integer manfaatCd )
    {
        logger.info( "*-*-*-* Cepr01040000Entry.of_get_rate" );

        Cepr01030101Form cepr01030101Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        
        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put( "leftPartOfBusinessCd", manfaatCd );
        sqlParams.put( "lstabLamaTanggung", null );
        sqlParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_PREMI );
        sqlParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
        sqlParams.put( "paymentModeCd", payModeCd );
        sqlParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
        sqlParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
        sqlParams.put( "yearNoList", new Integer[]{ new Integer( "1" ) } );
        List rateList = eproposalManager.selectRate( sqlParams );
        Double rate;
        if( rateList != null && rateList.size() > 0 )
        {
            rate = LazyConverter.toDouble( eproposalManager.selectRate( sqlParams ).get( 0 ) );
        }
        else
        {
            rate = 0.0;
        }
        return rate;
    }

    // for PA used only
    public List<Map<String, Object>> getManfaatPersonalAccidentMap(  Object command ) throws IOException
    {
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();

        if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getPersonalAccidentFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getPersonalAccidentFlagDisplay() ) )
        {
            logger.info( "*-*-*-* Personal Accident Checked" );

            double totalSumInsured = cepr01030102Form.getTotalSumInsured().doubleValue();
            double personalAccidentUnitQty = cepr01030102Form.getPersonalAccidentUnitQtyCd().doubleValue();
            double maxUpRider = 0;
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                maxUpRider = 1000000000;
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                maxUpRider = 200000;
            }
            double upRider = totalSumInsured * personalAccidentUnitQty;

            upRider = MathUtil.min( upRider, maxUpRider );

            params.put( "label", "UP Personal Accident (PA)" );
            params.put( "content", currencySymbol + editorUtil.convertToString( upRider ) );
            result.add( params );
        }

        return result;
    }

    public List< Map<String, Object> > getHeaderRowList( Object command, String differ ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040139DownloadController.getCommonHeaderRowList" );

        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List< Map<String, Object> > result = new ArrayList< Map<String, Object> >();
        // Fill header content from CommonHeaderDataSource
        if( "title".equals( differ ) )
        {
        	result.addAll( source.getPolicyHolderNameMap() );
        	result.addAll( source.getPolicyHolderAgeMap() );
        }
        result.addAll( source.getInsuredNameMap() );
        result.addAll( source.getInsuredAgeMap() );
        result.addAll( source.getTermOfContractMap() );
        result.addAll( source.getTermOfPaymentMap() );
        result.addAll( source.getTotalSumInsuredMap() );
        if( "aboveDetail".equals( differ ) )
        {
        	result.addAll( source.getManfaatTermRiderMap() );
        	result.addAll( getManfaatPersonalAccidentMap( command ) );
        }
        if( "title".equals( differ ) )
        {
        	result.addAll( source.getPolicyHolderMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_pp" ) ) );
        	result.addAll( source.getInsuredMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_tt" ) ) );
        }
        return result;
    }
    
    public BigDecimal computePremium( Integer payModeCd )
    {
    	 Map<String, Object> sqlParams = new HashMap<String, Object>();
    	 sqlParams.put("lsbs_id", "219");
         sqlParams.put("plan", cepr01030102Form.getRightPartOfBusinessCd());
     
         if(cepr01030101Form.getBabyFlag()==CommonConst.CHECKED_FALSE){ 
        	 sqlParams.put("liUsia", cepr01030101Form.getInsuredAge());
        }else{
        	if( CommonConst.CHECKED_TRUE.equals(cepr01030101Form.getBabyFlag()) ) {			
        	 sqlParams.put("liUsia", 0);
        	}
        }
         
         double premium = LazyConverter.toDouble(eproposalManager.selectPremiKid(sqlParams)) * LazyConverter.toDouble(cepr01030102Form.getTotalSumInsured()) * 0.001 ;
                 
         double factor_percentage = 1;

         int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );
         
         switch( paymentModeCd )
         {
             case Hardcode.PAY_MODE_CD_TRIWULANAN: factor_percentage = 0.27;  break;
             case Hardcode.PAY_MODE_CD_SEMESTERAN: factor_percentage = 0.525;  break;
             case Hardcode.PAY_MODE_CD_BULANAN   : factor_percentage = 0.1; break;
             default: break;
         }               
         double premi = premium * factor_percentage;
         premi = premi/10000;        
         premi = Math.ceil(premi);
         premi = premi*10000;
         BigDecimal premium2 = new BigDecimal(premi);
        return premium2;
    }
    
    public BigDecimal computePremiumSetahun( Integer payModeCd )
    {
    	 Map<String, Object> sqlParams = new HashMap<String, Object>();
         sqlParams.put("lsbs_id", "219");
         sqlParams.put("plan", cepr01030102Form.getRightPartOfBusinessCd());
     
         if(cepr01030101Form.getBabyFlag()==CommonConst.CHECKED_FALSE){ 
        	 sqlParams.put("liUsia", cepr01030101Form.getInsuredAge());
        }else{
        	if( CommonConst.CHECKED_TRUE.equals(cepr01030101Form.getBabyFlag()) ) {	
        	 sqlParams.put("liUsia", 0);
        	}
        }         
         double premium = LazyConverter.toDouble(eproposalManager.selectPremiKid(sqlParams)) * LazyConverter.toDouble(cepr01030102Form.getTotalSumInsured()) * 0.001 ;
         double factor_percentage = 1;

         int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );
         
         switch( paymentModeCd )
         {
             case Hardcode.PAY_MODE_CD_TRIWULANAN: factor_percentage = 0.27;  break;
             case Hardcode.PAY_MODE_CD_SEMESTERAN: factor_percentage = 0.525;  break;
             case Hardcode.PAY_MODE_CD_BULANAN   : factor_percentage = 0.1; break;
             default: break;
         }               
         double premi = premium * factor_percentage;                  
         
         switch( paymentModeCd )
         {
             case Hardcode.PAY_MODE_CD_TRIWULANAN: premi = premi * 4;  break;
             case Hardcode.PAY_MODE_CD_SEMESTERAN: premi = premi * 2;  break;
             case Hardcode.PAY_MODE_CD_BULANAN   : premi = premi * 12; break;
             default: break;
         }
         premi = premi/10000;        
         premi = Math.ceil(premi);
         premi = premi*10000;
         BigDecimal premium2 = new BigDecimal(premi);        
                          
        return premium2;
    }
    
    public BigDecimal computeNT( Integer yearNo )
    {
        logger.info( "*-*-*-* Cepr01040139Entry.computePremium" );
        Map<String, Object> sqlParams = new HashMap<String, Object>();
        
        sqlParams.put("lsbs_id", "212");
        sqlParams.put("plan", 3);                   
       	sqlParams.put("liUsia", cepr01030101Form.getInsuredAge());     
       	sqlParams.put("lamaBayar", cepr01030102Form.getPremiumFurloughYearCd());      
        sqlParams.put("yearNo", yearNo);
        sqlParams.put("cb_id", Hardcode.PAY_MODE_CD_BULANAN);
        double nt = LazyConverter.toDouble(eproposalManager.selectNTKid(sqlParams)) * LazyConverter.toDouble(cepr01030102Form.getPremium()) * 0.01 * 50 ;
                
        BigDecimal nt2 =new BigDecimal(nt);
        return nt2;
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
       
        result.addAll( source.getPolicyHolderNameMap() );
        result.addAll( source.getPolicyHolderAgeMap() );
          
        Map<String, Object> param;
        param = new HashMap<String, Object>();
        param.put( "label", "Nama Tertanggung" );   
        param.put( "content",  cepr01030101Form.getInsuredName() );            	
        result.add( param );
            	 
        param = new HashMap<String, Object>();            
        param.put( "label", "Usia Tertanggung" );            	            	  
        param.put( "content", cepr01030101Form.getInsuredAge() +" Tahun" );  
        result.add( param );
            	 
        param = new HashMap<String, Object>();            
        param.put( "label", "Masa Pertanggungan" );            	            	  
        param.put( "content", cepr01030102Form.getPremiumFurloughYearCd() +" Tahun" );  
        result.add( param );
            	
        int m = 1; //Frekuensi pembayaran premi sesuai CB, default=1=Bulanan
        if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TAHUNAN ){
           	m = 12;
        }else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEMESTERAN ){
           	m = 6;
        }else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TRIWULANAN ){
           	m = 3;
        }
             	 
        param = new HashMap<String, Object>();            
        param.put( "label", "Manfaat Bulanan" );
        String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
	    param.put( "content",  currencySymbol + " " + editorUtil.convertToTwoDigit( cepr01030102Form.getPremium().doubleValue() * 10 / m ) );
        result.add( param );
            	 
      return result;
    }
    
    public List<Map<String, Object>> getPageList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getPageList" );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub6" );
        result.add( params );
        return result;        
    }   
    
    public List<Map<String, Object>> getBiayaList(String word, BigDecimal biaya, String cara_bayar) throws IOException
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
	    Map<String, Object> params = new HashMap<String, Object>();
	    params.put( "label", "-	Premi "+ word );
	   	 
	    params.put( "content", editorUtil.convertToString( "Rp."+ editorUtil.convertToTwoDigit(biaya)+"  "+ cara_bayar +"*" ) );
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
        	
                
         Map<String, Object> param;
    	 param = new HashMap<String, Object>();
    	 param.put( "label", "Nama Tertanggung" );
    	// param.put( "content", editorUtil.convertToString( "Calon bayi Ibu "+cepr01030101Form.getInsuredName() ) );
    	     	 
    	 if(cepr01030101Form.getBabyFlag()!=CommonConst.CHECKED_FALSE){
         	if( CommonConst.CHECKED_TRUE.equals(cepr01030101Form.getBabyFlag()) ) {	
         		 param.put( "content",  "(Calon Bayi)  ");
         	}
    	 }else{
    		 param.put( "content",  cepr01030101Form.getInsuredName() );
    	 }                   	             	 
    	 result.add( param );
    	 
    	 param = new HashMap<String, Object>();            
    	 param.put( "label", "Usia Kandungan" );
    	
    	 if(cepr01030101Form.getBabyFlag()!=CommonConst.CHECKED_FALSE){
          	if( CommonConst.CHECKED_TRUE.equals(cepr01030101Form.getBabyFlag()) ) {	
    			 Integer babyChooseCd = Integer.valueOf(cepr01030102Form.getBabyChooseCd());
                 param.put( "content", "" + babyChooseCd + " Bulan");               
           }}else{               
        	   param.put( "content", cepr01030101Form.getInsuredAge() +" Tahun" );
           }         
     
         result.add( param );
        
        return result;
    }
}


