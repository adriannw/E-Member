package id.co.sinarmaslife.eproposal.common.datasource;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: CommonHeaderDataSource
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 3, 2007 1:56:46 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030103Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030104Form;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.standard.util.MappingUtil;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonHeaderDataSource
{
    protected final Log logger = LogFactory.getLog( getClass() );

    EproposalManager eproposalManager;
    private Cepr01030101Form cepr01030101Form;
    private Cepr01030102Form cepr01030102Form;
    private Cepr01030104Form cepr01030104Form;
    private Cepr00000000EditorUtil editorUtil;
    private String currencySymbol;

	private Cepr01030103Form cepr01030103Form;

    public CommonHeaderDataSource( Object command, Cepr00000000EditorUtil editorUtil, EproposalManager eproposalManager )
    {
        Cepr01030000HoldingForm cmd = ( Cepr01030000HoldingForm ) command;
        this.eproposalManager = eproposalManager;
        this.cepr01030101Form = cmd.getCepr01030101Form();
        this.cepr01030102Form = cmd.getCepr01030102Form();
        this.cepr01030103Form = cmd.getCepr01030103Form();
        this.cepr01030104Form = cmd.getCepr01030104Form();
        this.editorUtil = editorUtil;
        this.currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );

        logger.info( "*-*-*-* CommonHeaderDataSource constructor is called ..." );
    }

    public List<Map<String, Object>> getPolicyHolderNameMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "label", "Nama Pemegang Polis" );
        params.put( "content", editorUtil.convertToString( cepr01030101Form.getPolicyHolderName() ) );
        result.add( params );
        return result;
    }
    
    public List<Map<String, Object>> getParentNameMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "label", "Nama Orang Tua" );
        params.put( "content", editorUtil.convertToString( cepr01030101Form.getPolicyHolderName() ) );
        result.add( params );
        return result;
    }

    public List<Map<String, Object>> getParentAgeMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "label", "Usia Orang Tua" );
        params.put( "content", editorUtil.convertToString( cepr01030101Form.getPolicyHolderAge() ) + " tahun" );
        result.add( params );
        return result;
    }
    
    public List<Map<String, Object>> getPolicyHolderAgeMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "label", "Usia Pemegang Polis" );
        params.put( "content", editorUtil.convertToString( cepr01030101Form.getPolicyHolderAge() ) + " tahun" );
        result.add( params );
        return result;
    }
    
    public List<Map<String, Object>> getChildNameMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "label", "Nama Anak" );
        params.put( "content", editorUtil.convertToString( cepr01030101Form.getInsuredName() ) );
        result.add( params );
        return result;
    }

    public List<Map<String, Object>> getInsuredNameMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "label", "Nama Tertanggung" );
        params.put( "content", editorUtil.convertToString( cepr01030101Form.getInsuredName() ) );
        result.add( params );
        return result;
    }

    public List<Map<String, Object>> getInsuredNameSyariahMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "label", "Nama Peserta" );
        params.put( "content", editorUtil.convertToString( cepr01030101Form.getInsuredName() ) );
        result.add( params );
        return result;
    }

    public List<Map<String, Object>> getChildAgeMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "label", "Usia Anak" );
        params.put( "content", editorUtil.convertToString( cepr01030101Form.getInsuredAge() ) + " tahun" );
        result.add( params );
        return result;
    }
    
    public List<Map<String, Object>> getInsuredAgeMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "label", "Usia Tertanggung" );        
        params.put( "content", editorUtil.convertToString( cepr01030101Form.getInsuredAge() ) + " tahun" );
        result.add( params );
        return result;
    }

    public List<Map<String, Object>> getInsuredAgeSyariahMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "label", "Usia Peserta" );
        params.put( "content", editorUtil.convertToString( cepr01030101Form.getInsuredAge() ) + " tahun" );
        result.add( params );
        return result;
    }
    
    public List<Map<String, Object>> getGenderInsuredMap() throws IOException
    {
    	 List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    
    	if(!(cepr01030101Form.getInsuredSexCd().equals(""))){
	        Map<String, Object> params = new HashMap<String, Object>();
	        params.put( "label", "Jenis Kelamin Tertanggung" );
	        
	        String genderInsured = cepr01030101Form.getInsuredSexCd();  
	        if(genderInsured.equals("L")){
	        	genderInsured = "Laki-laki";
	        }else if(genderInsured.equals("P")){
	        	genderInsured = "Perempuan";
	        }        
	        params.put( "content", genderInsured );
	        result.add( params );
    	}
	        
        return result;
    }

    public List<Map<String, Object>> getGenderInsuredSyariahMap() throws IOException
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	if(!(cepr01030101Form.getInsuredSexCd().equals(""))){    	

	        Map<String, Object> params = new HashMap<String, Object>();
	        params.put( "label", "Jenis Kelamin Peserta" );
	        
	        String genderInsured = cepr01030101Form.getInsuredSexCd();  
	        if(genderInsured.equals("L")){
	        	genderInsured = "Laki-laki";
	        }else if(genderInsured.equals("P")){
	        	genderInsured = "Perempuan";
	        }        
	        params.put( "content", genderInsured );
	        result.add( params );
    	}
        return result;
    }

    public List<Map<String, Object>> getTermOfContractMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "label", "Masa Pertanggungan" );
        String tempLabel = " tahun";
        params.put( "content", editorUtil.convertToString( cepr01030102Form.getTermOfContract() ) + tempLabel );
        result.add( params );
        return result;
    }

    public List<Map<String, Object>> getTermOfContractWithLimitMap( int limit ) throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        String content;

        content = limit == Hardcode.LIFE_TIME?
                "Seumur Hidup" : editorUtil.convertToString( cepr01030102Form.getTermOfContract() ) + " tahun (Sampai Tertanggung Berusia " + Integer.toString( limit ) + " Tahun)";
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getBabyFlag()) )
        {
            content = editorUtil.convertToString( limit ) + " tahun";
        }        
        params = new HashMap<String, Object>();
        params.put( "label", "Masa Pertanggungan" );
        params.put( "content", content );
        result.add( params );

        return result;
    }
    
    public List<Map<String, Object>> getTermOfContractWithLimitSyariahMap( int limit ) throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        String content;

        content = limit == Hardcode.LIFE_TIME?
                "Seumur Hidup" : editorUtil.convertToString( cepr01030102Form.getTermOfContract() ) + " tahun (Sampai Peserta Berusia " + Integer.toString( limit ) + " Tahun)";
        params = new HashMap<String, Object>();
        params.put( "label", "Masa Asuransi Syariah" );
        params.put( "content", content );
        result.add( params );

        return result;
    }

    public List<Map<String, Object>> getTermOfContractWithLimitMapInTwoRows( int limit ) throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;
        params = new HashMap<String, Object>();
        params.put( "label", "Masa Pertanggungan" );
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getBabyFlag()) )
        {        
            params.put( "content", Integer.toString( limit ) + " Tahun");
            result.add( params );
        }
        else
        {
	        params.put( "content", editorUtil.convertToString( cepr01030102Form.getTermOfContract() ) + " tahun (Sampai Tertanggung");
	        result.add( params );       
	        params = new HashMap<String, Object>();
	        params.put( "label", "" );
	        params.put( "content", "Berusia " + Integer.toString( limit ) + " Tahun)");
	        result.add( params );
        }
        return result;
    }
    
    public List<Map<String, Object>> getTermOfContractWithLimitSyariahMapInTwoRows( int limit ) throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params
                ;
        params = new HashMap<String, Object>();
        params.put( "label", "Masa Asuransi Syariah" );
        params.put( "content", editorUtil.convertToString( cepr01030102Form.getTermOfContract() ) + " tahun (Sampai Peserta");
        result.add( params );

        params = new HashMap<String, Object>();
        params.put( "label", "" );
        params.put( "content", "Berusia " + Integer.toString( limit ) + " Tahun)");
        result.add( params );

        return result;
    }

    public List<Map<String, Object>> getTotalSumInsuredMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "label", "Uang Pertanggungan (UP)" );
        params.put( "content", currencySymbol + editorUtil.convertToRoundedTwoDigits( cepr01030102Form.getTotalSumInsured() )  );
        result.add( params );
        return result;
    }

    public List<Map<String, Object>> getTotalSumInsuredSyariahMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "label", "Manfaat Asuransi Syariah" );
        params.put( "content", currencySymbol + editorUtil.convertToRoundedTwoDigits( cepr01030102Form.getTotalSumInsured() )  );
        result.add( params );
        return result;
    }
    
    public List<Map<String, Object>> getTermOfPaymentWithSekaligusMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;
        
        if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEKALIGUS)
        {
            params = new HashMap<String, Object>();
            params.put( "label", "Premi Sekaligus" );
            params.put( "content", currencySymbol + editorUtil.convertToString( cepr01030102Form.getPremium() ) );
        }
        else
        {        	
            params = new HashMap<String, Object>();
            params.put( "label", "Masa Pembayaran Premi" );
            params.put( "content", editorUtil.convertToString( cepr01030102Form.getTermOfPayment() ) + " tahun" );
        }

        result.add( params );
        return result;
    }

    public List<Map<String, Object>> getTermOfPaymentWithSekaligusSyariahMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEKALIGUS)
        {
            params = new HashMap<String, Object>();
            params.put( "label", "Kontribusi/Premi Sekaligus" );
            params.put( "content", currencySymbol + editorUtil.convertToString( cepr01030102Form.getPremium() ) );
        }
        else
        {
            params = new HashMap<String, Object>();
            params.put( "label", "Masa Pembayaran Kontribusi/Premi" );
            params.put( "content", editorUtil.convertToString( cepr01030102Form.getTermOfPayment() ) + " tahun" );
        }

        result.add( params );
        return result;
    }

    public List<Map<String, Object>> getTermOfPaymentMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        if( cepr01030102Form.getPaymentModeCd() != Hardcode.PAY_MODE_CD_SEKALIGUS )
        {
            params = new HashMap<String, Object>();
            params.put( "label", "Masa Pembayaran Premi" );
            params.put( "content", editorUtil.convertToString( cepr01030102Form.getTermOfPayment() ) + " tahun" );
            result.add( params );
        }

        return result;
    }

     public List<Map<String, Object>> getManfaatTermRiderMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();

        if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getTermRiderFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getTermRiderFlagDisplay() ) )
        {
            logger.info( "*-*-*-* Term Rider Checked" );
            params.put( "label", "UP SMiLe Term Rider" );
            params.put( "content", currencySymbol + editorUtil.convertToString( cepr01030102Form.getTotalSumInsured().multiply( new BigDecimal( cepr01030102Form.getTermRiderUnitQtyCd() ) ) ) );
            result.add( params );
        }

        return result;
    }
     
     public List<Map<String, Object>> getPremiumMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();

        String label = "Premi Tahunan";
        if( cepr01030102Form.getPaymentModeCd().intValue() == Hardcode.PAY_MODE_CD_SEKALIGUS ){
        	label = "Premi Sekaligus";
        }
        
        params.put( "label", label );
        params.put( "content", currencySymbol + editorUtil.convertToString( cepr01030102Form.getPremium() ) );
        result.add( params );

        return result;
    }

    public List<Map<String, Object>> getManfaatPersonalAccidentMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();

        if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getPersonalAccidentFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getPersonalAccidentFlagDisplay() ) )
        {
            logger.info( "*-*-*-* Personal Accident Checked" );
            params.put( "label", "UP SMiLe Personal Accident (PA)" );
            params.put( "content", currencySymbol + editorUtil.convertToString( cepr01030102Form.getTotalSumInsured().multiply( new BigDecimal( cepr01030102Form.getTermRiderUnitQtyCd() ) ) ) );
            result.add( params );
        }
        
        return result;
    }

    public List<Map<String, Object>> getMti1TermMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "label", "Masa Target Investasi I (MTI 1)" );
        params.put( "content", MappingUtil.getLabel( cepr01030102Form.getMtiList(), cepr01030102Form.getMtiCd().toString() ) );
        result.add( params );
        return result;
    }

    public List<Map<String, Object>> getMti1RateMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "label", "Tingkat Target Investasi MTI 1" );
        params.put( "content", editorUtil.convertToString( cepr01030102Form.getInvestRateInPercent() ) + "% (p.a)" );
        result.add( params );
        return result;
    }

    public List<Map<String, Object>> getMti1BeginningDateMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "label", "Tanggal Awal MTI" );
        params.put( "content", FormatDate.toIndonesianWoClock( eproposalManager.selectNowDate() ) );
        //params.put( "content", FormatDate.toIndonesianWoClock( eproposalManager.selectNowDate()) );
        result.add( params );
        return result;
    }

    public List<Map<String, Object>> getInsuredMedicalCheckUpMap( String tipeMedis ) throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        if( !StringUtil.isEmpty( tipeMedis ) )
        {
            Map<String, Object> params;
            params = new HashMap<String, Object>();
            if( cepr01030102Form.getFlagOfVIP() != null ){
            	 if( cepr01030102Form.getFlagOfVIP().equals( CommonConst.CHECKED_TRUE ) )
                 {
                 	 params.put( "label", "Tipe Medis Calon Tertanggung (VIP)" );
                 } else if( cepr01030102Form.getFlagOfVIP().equals( CommonConst.CHECKED_FALSE ) )
            	 {
                	 params.put( "label", "Tipe Medis Calon Tertanggung" );
            	 }
            }else{
            	 params.put( "label", "Tipe Medis Calon Tertanggung" );
            	
            }
          
            params.put( "content", tipeMedis );
            result.add( params );
        }
        return result;
    }
    
    public List<Map<String, Object>> getChildMedicalCheckUpMap( String tipeMedis ) throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        if( !StringUtil.isEmpty( tipeMedis ) )
        {
            Map<String, Object> params;
            params = new HashMap<String, Object>();
            params.put( "label", "Tipe Medis Calon Tertanggung(anak)" );
            params.put( "content", tipeMedis );
            result.add( params );
        }
        return result;
    }
    
    public List<Map<String, Object>> getInsuredMedicalCheckUpSyariahMap( String tipeMedis ) throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        if( !StringUtil.isEmpty( tipeMedis ) )
        {
            Map<String, Object> params;
            params = new HashMap<String, Object>();
            params.put( "label", "Tipe Medis Calon Peserta" );
            params.put( "content", tipeMedis );
            result.add( params );
        }
        return result;
    }

    public List<Map<String, Object>> getPolicyHolderMedicalCheckUpMap( String tipeMedis ) throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        if( !StringUtil.isEmpty( tipeMedis ) )
        {
            Map<String, Object> params;
            params = new HashMap<String, Object>();
            params.put( "label", "Tipe Medis Calon Pemegang Polis" );
            params.put( "content", tipeMedis );
            result.add( params );
        }
        return result;
    }
    


}
