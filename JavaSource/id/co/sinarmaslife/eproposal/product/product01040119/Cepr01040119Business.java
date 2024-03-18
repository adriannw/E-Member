 package id.co.sinarmaslife.eproposal.product.product01040119;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040119Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Dec 30, 2008 10:10:21 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000ProductBusiness;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.MappingUtil;

public class Cepr01040119Business extends Cepr01040000ProductBusiness
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040119Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
    }

    public List< Map<String, Object> > getHeaderRowList( Object command ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040119DownloadController.getCommonHeaderRowList" );

        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List< Map<String, Object> > result = new ArrayList< Map<String, Object> >();

        // Fill header content from CommonHeaderDataSource
        result.addAll( source.getPolicyHolderNameMap() );
        result.addAll( source.getPolicyHolderAgeMap() );
        result.addAll( source.getInsuredNameMap() );
        result.addAll( source.getInsuredAgeMap() );
        result.addAll( source.getTermOfContractMap() );
        result.addAll( yearPremium() );
        result.addAll( source.getTermOfPaymentWithSekaligusMap() );
        result.addAll( source.getTotalSumInsuredMap() );     

        result.addAll( source.getPolicyHolderMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_pp" ) ) );
        result.addAll( source.getInsuredMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_tt" ) ) );

        return result;
    }

    public List< Map<String, String> > getDetailList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040119Business.getDetailList" );

        List< Map<String, String> > result = new ArrayList< Map<String, String> >();      
        Map<String, String> params;

        int helpCountPremium = 0;
        int helpCountAge = 0;
        int ageStartMid = 0;
        double nilaiTunai = 0;
        int yearStartMid =  (55 - ( cepr01030101Form.getInsuredAge() + 5 )) ;

        cepr01030102Form.setTempPremium(LazyConverter.toInt( cepr01030102Form.getPremium().toString() ));
        int subProduct = cepr01030102Form.getRightPartOfBusinessCd();
        int tempBenefit = 0;
        double[] decNilaiTunaiHcp = {971.58, 869.31, 766.03, 661.58, 555.77, 448.51, 339.70, 229.06, 115.95, 0};
        double[] decNilaiTunaiDanaKehidupan = {71.58, 69.31, 66.03, 61.58, 55.77, 48.51, 39.70, 29.06, 15.95,0};
        
        if( subProduct == 1 || subProduct == 6 || subProduct == 11 || subProduct == 16 )
        {
        	tempBenefit = 250000;
        }
        else if( subProduct == 2 || subProduct == 7 || subProduct == 12 || subProduct == 17 )
        {
        	tempBenefit = 500000;
        }
        else if( subProduct == 3 || subProduct == 8 || subProduct == 13 || subProduct == 18 )
        {
        	tempBenefit = 750000;
        }
        else if( subProduct == 4 || subProduct == 9 || subProduct == 14 || subProduct == 19 )
        {
        	tempBenefit = 1000000;
        }
        else if( subProduct == 5 || subProduct == 10 || subProduct == 15 || subProduct == 20 )
        {
        	tempBenefit = 1500000;
        }      
        int index = 65 - (cepr01030101Form.getInsuredAge() + 1);
        int helpIndex = 0;
        int helpMax = 0;
//        if(cepr01030102Form.getRightPartOfBusinessCd() >= 1 && cepr01030102Form.getRightPartOfBusinessCd() <= 5)
//        {
//        	ageStartMid = 50;
//            if(index > 15)
//            {
//            	helpMax = 5;
//            	helpIndex = 16;
//            }
//            else
//            {
//            	helpMax = index + 1;
//            	helpIndex = index + 1;
//            }
//        }
//        else if(cepr01030102Form.getRightPartOfBusinessCd() >= 6 && cepr01030102Form.getRightPartOfBusinessCd() <= 10)
//        {
//        	ageStartMid = 45;
//            if(index > 20)
//            {
//            	helpMax = 10;
//            	helpIndex = 21;
//            }
//            else
//            {
//            	helpMax = index + 1;
//            	helpIndex = index + 1;
//            }
//        }
//        else if(cepr01030102Form.getRightPartOfBusinessCd() >= 11 && cepr01030102Form.getRightPartOfBusinessCd() <= 15)
//        {
        	ageStartMid = 40;
            if(index > 25)
            {
            	helpMax = 15;
            	helpIndex = 26;
            }
            else
            {
            	helpMax = index + 1;
            	helpIndex = index + 1;
            }
//        }
//        else if(cepr01030102Form.getRightPartOfBusinessCd() >= 16 && cepr01030102Form.getRightPartOfBusinessCd() <= 20)
//        {
//        	helpMax = index + 1;
//        	helpIndex = index + 1;
//        }
        
        //default helpMax helpIndex
        //helpMax = 15;
        //helpIndex = 26;

        for( int i = 0 ; i < helpIndex ; i++ )
        {   
        	params = new HashMap<String, String>();

        	if( i >= 0 && i < helpMax)
        	{        
        		if(helpCountAge < 55)
        		{
        			cepr01030102Form.setTempPremium(LazyConverter.toInt( cepr01030102Form.getTempPremium().toString() ) + helpCountPremium);
        		}
                params.put( "yearNo", LazyConverter.toString( i + 1 ) );
                helpCountAge = cepr01030101Form.getInsuredAge() + ( i + 1 );
                params.put( "age", editorUtil.convertToString( helpCountAge ) );
        	}
        	else
        	{
                params.put( "yearNo", LazyConverter.toString( yearStartMid + i ) );
                if(index > helpIndex - 1)
                {
                	helpCountAge = ageStartMid + i ;
                }
                else 
                {
                	helpCountAge = cepr01030101Form.getInsuredAge() + ( i + 1 );
                }
                params.put( "age", editorUtil.convertToString( helpCountAge ) );
        	}
        	if(helpCountAge > 55)
        	{
        		int tempIndexNilaiTunai = 10 - (65 - (helpCountAge - 1) );
                if(cepr01030102Form.getPacketCd() == Hardcode.PACKET_HOSPITAL_CASH_PLAN)
                {  
                	params.put("hcpBenefit", editorUtil.convertToRoundedNoDigit( new BigDecimal( tempBenefit ) ));
                    if( tempIndexNilaiTunai < 10 )
                    {
                    	nilaiTunai = decNilaiTunaiHcp[tempIndexNilaiTunai] * LazyConverter.toDouble(cepr01030102Form.getTotalSumInsured()) / 1000;
                    }
                }
                else if(cepr01030102Form.getPacketCd() == Hardcode.PACKET_DANA_KEHIDUPAN)
                {
                    if( tempIndexNilaiTunai < 10 )
                    {
                    	nilaiTunai = decNilaiTunaiDanaKehidupan[tempIndexNilaiTunai] * LazyConverter.toDouble(cepr01030102Form.getTotalSumInsured()) / 1000;
                    }
                	params.put("hcpBenefit", "" );
                }
        	}
        	else
        	{
                if(cepr01030102Form.getPacketCd() == Hardcode.PACKET_HOSPITAL_CASH_PLAN)
                {
                	params.put("hcpBenefit", "" );
                }
                else if(cepr01030102Form.getPacketCd() == Hardcode.PACKET_DANA_KEHIDUPAN)
                {
                	if(helpCountAge == 55)
                	{
                    	params.put("hcpBenefit", editorUtil.convertToRoundedNoDigit(cepr01030102Form.getTotalSumInsuredCd()) );
                	}
                	else
                	{
                		params.put("hcpBenefit", "" );
                	}
                }
                if(helpCountAge == 55){
                	nilaiTunai = of_get_tunai(cepr01030101Form.getInsuredAge(), 35) * LazyConverter.toDouble(cepr01030102Form.getTotalSumInsured()) / 1000;
                }else if((i+1) > 1 && helpCountAge < 55){
                	nilaiTunai = of_get_tunai(cepr01030101Form.getInsuredAge(), i+1) * LazyConverter.toDouble(cepr01030102Form.getTotalSumInsured()) / 1000;
                }else{
                	nilaiTunai = 0;
                }
        	}
            params.put( "accumulatedPremium",  editorUtil.convertToRoundedTwoDigits( cepr01030102Form.getTempPremium() ));
            params.put( "deathBenefit", editorUtil.convertToRoundedTwoDigits( cepr01030102Form.getTotalSumInsured()) );
            params.put( "cashValue", editorUtil.convertToRoundedNoDigit(new BigDecimal( nilaiTunai ) ) );
            helpCountPremium = LazyConverter.toInt( cepr01030102Form.getPremium().toString() );
            result.add( params );
        }   
        return result;
    }
    // START
    public BigDecimal computePremium( Integer leftPartBusinessCd, Integer rightPartBusinessCd, Integer insuredAge, String currencyCd, Integer paymentModeCd )
    {
        logger.info( "*-*-*-* Cepr01040119Business.computePremium" );
        BigDecimal premium;
        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put( "leftPartBusinessCd", leftPartBusinessCd );
        sqlParams.put( "rightPartBusinessCd", rightPartBusinessCd );
        sqlParams.put( "insuredAge", insuredAge );
        sqlParams.put( "currencyCd", currencyCd );
        premium = eproposalManager.selectProduct168PremiRate( sqlParams );
        BigDecimal factor = BigDecimal.ZERO;
        switch( LazyConverter.toInt( cepr01030102Form.getPaymentModeCd() ) )
        {
            case Hardcode.PAY_MODE_CD_TAHUNAN: factor = new BigDecimal( Hardcode.FACTOR_TAHUN ); break;
            case Hardcode.PAY_MODE_CD_SEMESTERAN: factor = new BigDecimal( Hardcode.FACTOR_SEMESTER ); break;
            case Hardcode.PAY_MODE_CD_TRIWULANAN: factor = new BigDecimal( Hardcode.FACTOR_TRIWULAN ); break;
            case Hardcode.PAY_MODE_CD_BULANAN: factor = new BigDecimal( Hardcode.FACTOR_MONTHLY ); break;
            default:break;
        }

        if( premium == null ) premium = BigDecimal.ZERO;
        return premium.multiply( factor );
    }
    public List<Map<String, Object>> yearPremium() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "label", "Premi Tahunan" );
        String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
        params.put( "content", currencySymbol + editorUtil.convertToRoundedTwoDigits( cepr01030102Form.getPremium() ) );
        result.add( params );
        return result;
    }
    
    public List< Map<String, String> > getSecondPageHcp() throws IOException
    {
        List< Map<String, String> > result = new ArrayList< Map<String, String> >();      
        Map<String, String> params;
        
        params = new HashMap<String, String>();
        params.put( "pageCode", "sub1" );
        result.add( params );
        
        return result ;
    }
    
    public List<Map<String, Object>> getPageList( ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getPageList" );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;
        
        if(cepr01030102Form.getPacketCd() == Hardcode.PACKET_HOSPITAL_CASH_PLAN)
        {
            params = new HashMap<String, Object>();
            params.put( "pageCode", "sub1" );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "pageCode", "sub2" );
            result.add( params );
        }
        else if(cepr01030102Form.getPacketCd() == Hardcode.PACKET_DANA_KEHIDUPAN)
        {
            params = new HashMap<String, Object>();
            params.put( "pageCode", "sub1" );
            result.add( params );
        }
        return result;
    }
    
    public List< Map<String, Object> > getDetailBenefit() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040119Business.getDetailList" );

        List< Map<String, Object> > result = new ArrayList< Map< String, Object > >();      
        Map< String, Object > params;
        
      
        int subProduct = cepr01030102Form.getRightPartOfBusinessCd();
        int tempBenefit = 0;
        int tempBenefitIcu = 0;
        int tempBenefitAccident = 0;
        int bedahType1 = 0;
        int bedahType2 = 0;
        int bedahType3 = 0;
        int bedahType4 = 0;
        if( subProduct == 1 || subProduct == 6 || subProduct == 11 || subProduct == 16 )
        {
        	 tempBenefit = 250;
             tempBenefitIcu = 500;
             tempBenefitAccident = 500;
             bedahType1 = 625;
             bedahType2 = 1250;
             bedahType3 = 1825;
             bedahType4 = 2500;
        }
        else if( subProduct == 2 || subProduct == 7 || subProduct == 12 || subProduct == 17 )
        {
        	 tempBenefit = 500;
             tempBenefitIcu = 1000;
             tempBenefitAccident = 1000;
             bedahType1 = 1250;
             bedahType2 = 2500;
             bedahType3 = 3750;
             bedahType4 = 5000;
        }
        else if( subProduct == 3 || subProduct == 8 || subProduct == 13 || subProduct == 18 )
        {
        	 tempBenefit = 750;
             tempBenefitIcu = 1500;
             tempBenefitAccident = 1500;
             bedahType1 = 1500;
             bedahType2 = 3000;
             bedahType3 = 4500;
             bedahType4 = 6000;
        }
        else if( subProduct == 4 || subProduct == 9 || subProduct == 14 || subProduct == 19 )
        {
        	 tempBenefit = 1000;
             tempBenefitIcu = 2000;
             tempBenefitAccident = 2000;
             bedahType1 = 2500;
             bedahType2 = 5000;
             bedahType3 = 7500;
             bedahType4 = 10000;
        }
        else if( subProduct == 5 || subProduct == 10 || subProduct == 15 || subProduct == 20 )
        {
        	 tempBenefit = 1500;
             tempBenefitIcu = 3000;
             tempBenefitAccident = 3000;
             bedahType1 = 3750;
             bedahType2 = 7500;
             bedahType3 = 11250;
             bedahType4 = 15000;
        }        
        
        params = new HashMap<String, Object>();
        params.put("ket", "Santunan harian Rawat Inap Di Rumah Sakit per hari");
        params.put("batasanManfaat", editorUtil.convertToRoundedNoDigit( new BigDecimal( tempBenefit ) ) );
        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put("ket", "Santunan harian Rawat Inap Di Ruang ICU per hari");
        params.put("batasanManfaat", editorUtil.convertToRoundedNoDigit( new BigDecimal( tempBenefitIcu ) ) );
        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put("ket", "(Maksimum 30 hari per tahun polis)");
        params.put("batasanManfaat", "");
        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put("ket", "Santunan Rawat Inap di Rumah Sakit akibat Kecelakaan per hari");
        params.put("batasanManfaat", editorUtil.convertToRoundedNoDigit( new BigDecimal( tempBenefitAccident ) ) );
        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put("ket", "Manfaat Tindakan Pembedahan :");
        params.put("batasanManfaat","");
        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put("ket", "4.1. Pembedahan - Tipe 1");
        params.put("batasanManfaat", editorUtil.convertToRoundedNoDigit( new BigDecimal( bedahType1 ) ) );
        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put("ket", "4.1. Pembedahan - Tipe 2");
        params.put("batasanManfaat", editorUtil.convertToRoundedNoDigit( new BigDecimal( bedahType2 ) ) );
        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put("ket", "4.1. Pembedahan - Tipe 3");
        params.put("batasanManfaat", editorUtil.convertToRoundedNoDigit( new BigDecimal( bedahType3 ) ) );
        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put("ket", "4.1. Pembedahan - Tipe 4");
        params.put("batasanManfaat", editorUtil.convertToRoundedNoDigit( new BigDecimal( bedahType4 ) ) );
        result.add( params );
        return result;
    }
}
