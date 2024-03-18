package id.co.sinarmaslife.eproposal.product.product01040145;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040145Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : Fadly Mathendra
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
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000ProductBusiness;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000UlinkBusiness;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000MoneyUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.MappingUtil;
import id.co.sinarmaslife.standard.util.MathUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040145Business extends Cepr01040000UlinkBusiness
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040145Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
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
    
    public List<Map<String, Object>> getPageList( int currentIdx ) throws IOException
    {

        logger.info( "*-*-*-* Cepr01040201Business.getPageList" );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub1" );
        result.add( params );

        // if no riders, don't show this page ( page 3 )
        if( currentIdx != 0 )
        {
            params = new HashMap<String, Object>();
            params.put( "pageCode", "sub2" );
            result.add( params );
        }

        return result;
    
    }
    
    public BigDecimal computeManfaat( Integer payModeCd, Integer manfaatCd )
    {
        logger.info( "*-*-*-* Cepr01040145Business.computeManfaat" );

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
        logger.info( "*-*-*-* Cepr01040145Business.computeManfaat" );
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
        logger.info( "*-*-*-* Cepr01040145Business.of_get_rate_for_pk" );
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

            params.put( "label", "UP Personal Accident (PA)" );
            params.put( "content", currencySymbol + editorUtil.convertToString( upRider ) );
            result.add( params );
        }

        return result;
    }

    public List< Map<String, Object> > getHeaderRowList( Object command ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040145DownloadController.getCommonHeaderRowList" );

        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List< Map<String, Object> > result = new ArrayList< Map<String, Object> >();

        // Fill header content from CommonHeaderDataSource
        result.addAll( source.getInsuredNameMap() );
        result.addAll( source.getInsuredAgeMap() );
        result.addAll( source.getTermOfContractMap() );
        result.addAll( source.getTotalSumInsuredMap() );
        result.addAll( getPremiumMap() );
        result.addAll( source.getTermOfPaymentMap() );
        result.addAll( source.getManfaatTermRiderMap() );
        result.addAll( source.getPolicyHolderMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_pp" ) ) );
        result.addAll( source.getInsuredMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_tt" ) ) );

        return result;
    }
    
    public List<Map<String, Object>> getPremiumMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
        if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TRIWULANAN )
        	{ params.put( "label", "Premi Triwulanan" ); }
        else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEMESTERAN ) 
        	{ params.put( "label", "Premi Semesteran" ); }
        else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TAHUNAN )
        	{ params.put( "label", "Premi Tahunan" ); }
        params.put( "content", currencySymbol + editorUtil.convertToRoundedTwoDigits( cepr01030102Form.getPremium() )  );
        result.add( params );
        return result;
    }
    
//    public Map<String, Object> of_get_coi_term( String kurs_id, int umur_tt, BigDecimal premiTotalSekaligus, BigDecimal premiTotalTahunan, BigDecimal premiTotalSemesteran, BigDecimal premiTotalTriwulanan, BigDecimal premiTotalBulanan )
//    {
//    	// TERM RIDER 4
//    	Map<String, Object> termRider = new HashMap<String, Object>();
//    	double sekaligus = 0;
//    	double tahunan = 0;
//    	double rateTahunan = 0;
//    	double semesteran = 0;
//    	double triwulanan = 0;
//    	double bulanan = 0;
//    	
//    	//for(int i = umur_tt ; i < (umur_tt+5) ; i++){
//    		// SEKALIGUS
//    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_SEKALIGUS;
//    		if("01".equals(kurs_id))
//    		{
//    			//sekaligus = FormatNumber.round((istr_prop.up_term / 1000) * selectLdecRateTerm(5, kurs_id, umur_tt), 2);
//    		}else if("02".equals(kurs_id))
//    		{
//    			//sekaligus = (istr_prop.up_term / 1000) * selectLdecRateTerm(5, kurs_id, umur_tt);
//    		}
//    		// TAHUNAN
//    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_TAHUNAN;
//    		if("01".equals(kurs_id))
//    		{
//    			rateTahunan = FormatNumber.round((istr_prop.up_term / 1000) * selectLdecRateTerm(5, kurs_id, umur_tt), 2);
//    		}else if("02".equals(kurs_id))
//    		{
//    			rateTahunan = (istr_prop.up_term / 1000) * selectLdecRateTerm(5, kurs_id, umur_tt);
//    		}
//    		tahunan = tahunan + rateTahunan;
//    		// SEMESTERAN
//    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_SEMESTERAN;
//    		semesteran = semesteran + (rateTahunan * 0.525);
//    		// TRIWULANAN
//    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_TRIWULANAN;
//    		triwulanan = triwulanan + (rateTahunan * 0.27);
//    		// BULANAN
//    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_BULANAN;
//    		bulanan = bulanan + (rateTahunan * 0.1);
//    	//}
//    	
//    	// MAIN
//    	if("01".equals(kurs_id))
//        {
//    		termRider.put("termRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus))+".00");
//    		termRider.put("termRiderTahunan", editorUtil.convertToRoundedNoDigit(new BigDecimal(tahunan))+".00");
//    		termRider.put("termRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
//    		termRider.put("termRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
//    		termRider.put("termRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
//    		
//    		termRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(0, RoundingMode.HALF_UP));
//    		termRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(0, RoundingMode.HALF_UP));
//    		termRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
//    		termRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
//    		termRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
//        }else if("02".equals(kurs_id))
//        {
//        	termRider.put("termRiderSekaligus", editorUtil.convertToRoundedTwoDigits(new BigDecimal(sekaligus)));
//    		termRider.put("termRiderTahunan", editorUtil.convertToRoundedTwoDigits(new BigDecimal(tahunan)));
//    		termRider.put("termRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
//    		termRider.put("termRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
//    		termRider.put("termRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
//    		
//    		termRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(2, RoundingMode.HALF_UP));
//    		termRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(2, RoundingMode.HALF_UP));
//    		termRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
//    		termRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
//    		termRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
//        }
//    	
//    	return termRider;
//    }
//    
//    public Map<String, Object> of_get_coi_ci( String kurs_id, int umur_tt, BigDecimal premiTotalSekaligus, BigDecimal premiTotalTahunan, BigDecimal premiTotalSemesteran, BigDecimal premiTotalTriwulanan, BigDecimal premiTotalBulanan )
//    {
//    	// CI RIDER 4
//    	Map<String, Object> ciRider = new HashMap<String, Object>();
//    	double sekaligus = 0;
//    	double tahunan = 0;
//    	double rateTahunan = 0;
//    	double semesteran = 0;
//    	double triwulanan = 0;
//    	double bulanan = 0;
//    	
//    	//for(int i = umur_tt ; i < (umur_tt+5) ; i++){
//    		// SEKALIGUS
//    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_SEKALIGUS;
//    		if("01".equals(kurs_id))
//    		{
//    			//sekaligus = FormatNumber.round( (istr_prop.up * 0.5 / 1000) * selectLdecRateCiRider(6, kurs_id, umur_tt), 2);
//    		}else if("02".equals(kurs_id))
//    		{
//    			//sekaligus = (istr_prop.up * 0.5 / 1000) * selectLdecRateCiRider(6, kurs_id, umur_tt);
//    		}
//    		// TAHUNAN
//    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_TAHUNAN;
//    		if("01".equals(kurs_id))
//    		{
//    			rateTahunan = FormatNumber.round( (istr_prop.up * 0.5 / 1000) * selectLdecRateCiRider(6, kurs_id, umur_tt), 2);
//    		}else if("02".equals(kurs_id))
//    		{
//    			rateTahunan = (istr_prop.up * 0.5 / 1000) * selectLdecRateCiRider(6, kurs_id, umur_tt);
//    		}
//    		tahunan = tahunan + rateTahunan;
//    		// SEMESTERAN
//    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_SEMESTERAN;
//    		semesteran = semesteran + (rateTahunan * 0.525);
//    		// TRIWULANAN
//    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_TRIWULANAN;
//    		triwulanan = triwulanan + (rateTahunan * 0.27);
//    		// BULANAN
//    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_BULANAN;
//    		bulanan = bulanan + (rateTahunan * 0.1);
//    	//}
//    	
//    	// MAIN
//    	if("01".equals(kurs_id))
//    	{
//    		ciRider.put("ciRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus))+".00");
//    		ciRider.put("ciRiderTahunan", editorUtil.convertToRoundedNoDigit(new BigDecimal(tahunan))+".00");
//    		ciRider.put("ciRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
//    		ciRider.put("ciRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
//    		ciRider.put("ciRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
//    		
//    		ciRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(0, RoundingMode.HALF_UP));
//    		ciRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(0, RoundingMode.HALF_UP));
//    		ciRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
//    		ciRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
//    		ciRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
//    	}else if("02".equals(kurs_id))
//    	{
//    		ciRider.put("ciRiderSekaligus", editorUtil.convertToRoundedTwoDigits(new BigDecimal(sekaligus)));
//    		ciRider.put("ciRiderTahunan", editorUtil.convertToRoundedTwoDigits(new BigDecimal(tahunan)));
//    		ciRider.put("ciRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
//    		ciRider.put("ciRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
//    		ciRider.put("ciRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
//    		
//    		ciRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(2, RoundingMode.HALF_UP));
//    		ciRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(2, RoundingMode.HALF_UP));
//    		ciRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
//    		ciRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
//    		ciRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
//    	}
//    	
//    	return ciRider;
//    }
//    
//    public Map<String, Object> of_get_coi_hcp_family( int jenis, String kurs_id, int umur_tt, BigDecimal premiTotalSekaligus, BigDecimal premiTotalTahunan, BigDecimal premiTotalSemesteran, BigDecimal premiTotalTriwulanan, BigDecimal premiTotalBulanan )
//    {
//    	// HCP FAMILY RIDER 4
//    	Map<String, Object> hcpRider = new HashMap<String, Object>();
//    	double sekaligus = 0;
//    	double tahunan = 0;
//    	double rateTahunan = 0;
//    	double semesteran = 0;
//    	double triwulanan = 0;
//    	double bulanan = 0;
//    	jenis = jenis + 100;
//    	
//    	//for(int i = umur_tt ; i < (umur_tt+5) ; i++){
//    		// SEKALIGUS
//    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_SEKALIGUS;
//    		
//    		if("01".equals(kurs_id))
//    		{
//    			//sekaligus = selectLdecRateHcpFamilyRider(jenis, kurs_id, umur_tt) * 100;
//    		}else if("02".equals(kurs_id))
//    		{
//    			//sekaligus = selectLdecRateHcpFamilyRider(jenis, kurs_id, umur_tt) * 100;
//    		}
//    		// TAHUNAN
//    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_TAHUNAN;
//    		if("01".equals(kurs_id))
//    		{
//    			rateTahunan = selectLdecRateHcpFamilyRider(jenis, kurs_id, umur_tt);
//    		}else if("02".equals(kurs_id))
//    		{
//    			rateTahunan = selectLdecRateHcpFamilyRider(jenis, kurs_id, umur_tt);
//    		}
//    		tahunan = tahunan + rateTahunan;
//    		// SEMESTERAN
//    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_SEMESTERAN;
//    		semesteran = semesteran + (rateTahunan * 0.525);
//    		// TRIWULANAN
//    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_TRIWULANAN;
//    		triwulanan = triwulanan + (rateTahunan * 0.27);
//    		// BULANAN
//    		//istr_prop.cb_id = Hardcode.PAY_MODE_CD_BULANAN;
//    		bulanan = bulanan + (rateTahunan * 0.1);
//    	//}
//    	
//    	// MAIN
//    	//hcpRider.put("hcpFamilyRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus)));
//    	if("01".equals(kurs_id))
//        {
//    		hcpRider.put("hcpFamilyRiderSekaligus", editorUtil.convertToRoundedNoDigit(new BigDecimal(sekaligus))+".00");
//    		hcpRider.put("hcpFamilyRiderTahunan", editorUtil.convertToRoundedNoDigit(new BigDecimal(tahunan))+".00");
//    		hcpRider.put("hcpFamilyRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
//    		hcpRider.put("hcpFamilyRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
//    		hcpRider.put("hcpFamilyRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
//    		
//    		hcpRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(0, RoundingMode.HALF_UP));
//    		hcpRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(0, RoundingMode.HALF_UP));
//    		hcpRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
//    		hcpRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
//    		hcpRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
//        }else if("02".equals(kurs_id))
//        {
//        	hcpRider.put("hcpFamilyRiderSekaligus", editorUtil.convertToRoundedTwoDigits(new BigDecimal(sekaligus)));
//    		hcpRider.put("hcpFamilyRiderTahunan", editorUtil.convertToRoundedTwoDigits(new BigDecimal(tahunan)));
//    		hcpRider.put("hcpFamilyRiderSemesteran", editorUtil.convertToRoundedNoDigit(new BigDecimal(semesteran))+".00");
//    		hcpRider.put("hcpFamilyRiderTriwulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(triwulanan))+".00");
//    		hcpRider.put("hcpFamilyRiderBulanan", editorUtil.convertToRoundedNoDigit(new BigDecimal(bulanan))+".00");
//    		
//    		hcpRider.put("premiTotalSekaligus", (new BigDecimal(sekaligus)).setScale(2, RoundingMode.HALF_UP));
//    		hcpRider.put("premiTotalTahunan", (new BigDecimal(tahunan)).setScale(2, RoundingMode.HALF_UP));
//    		hcpRider.put("premiTotalSemesteran", (new BigDecimal(semesteran)).setScale(0, RoundingMode.HALF_UP));
//    		hcpRider.put("premiTotalTriwulanan", (new BigDecimal(triwulanan)).setScale(0, RoundingMode.HALF_UP));
//    		hcpRider.put("premiTotalBulanan", (new BigDecimal(bulanan)).setScale(0, RoundingMode.HALF_UP));
//        }
//    	
//    	return hcpRider;
//    }
    
    
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
	    	if( istr_prop.bisnis_id == 96 || istr_prop.bisnis_id == 182 || istr_prop.bisnis_id == 194 || istr_prop.bisnis_id == 111){
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
	    	
//	    	if ab_single and Pos('096, 164, 174, 182, 194', string(istr_prop.bisnis_id, '000')) > 0 then return 0
//	    			li_jenis = istr_prop.ins_per
//	    			if Pos('164, 174, 177, 207', string(istr_prop.bisnis_id, '000')) > 0 then li_jenis = 4
//	    			if Pos('096, 182, 194', string(istr_prop.bisnis_id, '000')) > 0 then li_jenis = 6
//	    			//if istr_prop.bisnis_id = 142 then
//	    			if Pos('142, 155, 158, 184, 175, 177', string(istr_prop.bisnis_id, '000')) > 0 Then
//	    				li_jenis = 4
//	    				if ab_single then li_jenis = 5
//	    			End if
//	    			//khusus bsm, rate beda
//	    			if Pos('142, 164', string(istr_prop.bisnis_id, '000')) > 0 and istr_prop.bisnis_no = 2 Then li_jenis = 7
//	    			
	    	int li_jenis = istr_prop.ins_per;
	//    			if Pos('164, 174, 177, 207', string(istr_prop.bisnis_id, '000')) > 0 then li_jenis = 4
	//    			if Pos('096, 182, 194', string(istr_prop.bisnis_id, '000')) > 0 then li_jenis = 6
	    	if( istr_prop.bisnis_id == 164 || istr_prop.bisnis_id == 174 || istr_prop.bisnis_id == 177 || istr_prop.bisnis_id == 207 ){
	    		li_jenis = 4;
	    	}
	    	if( istr_prop.bisnis_id == 96 || istr_prop.bisnis_id == 182 || istr_prop.bisnis_id == 194 || istr_prop.bisnis_id == 111 ){
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
    		if( istr_prop.bisnis_id == 96 || istr_prop.bisnis_id == 182 || istr_prop.bisnis_id == 194 || istr_prop.bisnis_id == 111 ){
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
	        bulanan = bulanan + (rateTahunan * 0.12);
	    	
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
	        bulanan = bulanan + (rateTahunan * 0.12);
	    	
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


