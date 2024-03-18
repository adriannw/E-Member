package id.co.sinarmaslife.eproposal.product.product01040131;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040131Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Apr 25, 2008 11:27:50 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Ilustration116Business;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000ProductBusiness;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000MoneyUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.MappingUtil;
import id.co.sinarmaslife.standard.util.MathUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040131Business extends Cepr01040000Ilustration116Business
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040131Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
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
        logger.info( "*-*-*-* Cepr01040131Business.computeManfaat" );

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
        logger.info( "*-*-*-* Cepr01040131Business.computeManfaat" );
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

        return Cepr00000000MoneyUtil.rounding( new BigDecimal( lad_1[6] ), cepr01030102Form.getCurrencyCd() );
    }

    private double of_get_rate_for_pk()
    {
        logger.info( "*-*-*-* Cepr01040131Business.of_get_rate_for_pk" );
        double result;
        Cepr01030101Form cepr01030101Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();

        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
        sqlParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
        sqlParams.put( "businessNo", cepr01030102Form.getRightPartOfBusinessCd() + 1 );
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

            params.put( "label", "UP SMiLe Personal Accident (PA)" );
            params.put( "content", currencySymbol + editorUtil.convertToString( upRider ) );
            result.add( params );
        }

        return result;
    }

    public List< Map<String, Object> > getHeaderRowList( Object command ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040131DownloadController.getCommonHeaderRowList" );

        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List< Map<String, Object> > result = new ArrayList< Map<String, Object> >();

        // Fill header content from CommonHeaderDataSource
        result.addAll( source.getPolicyHolderNameMap() );
        result.addAll( source.getPolicyHolderAgeMap() );
        result.addAll( source.getInsuredNameMap() );
        result.addAll( source.getInsuredAgeMap() );
        result.addAll( source.getTermOfContractMap() );
        result.addAll( source.getTermOfPaymentMap() );
        result.addAll( source.getTotalSumInsuredMap() );
        result.addAll( source.getManfaatTermRiderMap() );
        result.addAll( getManfaatPersonalAccidentMap( command ) );
        result.addAll( source.getPolicyHolderMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_pp" ) ) );
        result.addAll( source.getInsuredMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_tt" ) ) );

        return result;
    }
    
    public List< Map<String, Object> > getManfaatMeninggalList( Object command ) throws IOException
    {
    	 logger.info( "*-*-*-* Cepr01040131DownloadController.getManfaatMeninggalList" );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        

        for( int i = 1 ; i <= cepr01030102Form.getTermOfContract().intValue() ; i ++ ){
        	Map<String, Object> params = new HashMap<String, Object>();
        	if(cepr01030102Form.getPaymentModeCd().intValue() == Hardcode.PAY_MODE_CD_SEKALIGUS ){
        		if( i == 1 ){
        			params.put("premi", editorUtil.convertToRoundedTwoDigits( cepr01030102Form.getPremium() ));
        		}else{
        			params.put("premi", editorUtil.convertToRoundedTwoDigits( BigDecimal.ZERO ));
        		}
        		params.put("yearNo", i + "." );
	        	params.put("usia", LazyConverter.toString(cepr01030101Form.getInsuredAge()+i) );
	            params.put("manfaat_meninggal", editorUtil.convertToRoundedTwoDigits( cepr01030102Form.getTotalSumInsured() ));
	            params.put("nilai_tunai", editorUtil.convertToRoundedTwoDigits( 0 ));
        	}else{
        		if( i <= cepr01030102Form.getTermOfPayment() ){
		            params.put("premi", editorUtil.convertToRoundedTwoDigits( cepr01030102Form.getPremium() ));
	            }else{
	            	params.put("premi", editorUtil.convertToRoundedTwoDigits( BigDecimal.ZERO ));
	            }
	        	params.put("yearNo", i + "." );
	        	params.put("usia", LazyConverter.toString(cepr01030101Form.getInsuredAge()+i) );
	            params.put("manfaat_meninggal", editorUtil.convertToRoundedTwoDigits( cepr01030102Form.getTotalSumInsured() ));
	            params.put("nilai_tunai", editorUtil.convertToRoundedTwoDigits( 0 ));
        	}
        	result.add(params);
        }
      
        return result;
    }
}


