package id.co.sinarmaslife.eproposal.common.datasource;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: PremiumSummaryDataSource
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 6, 2007 2:34:53 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000MoneyUtil;
import id.co.sinarmaslife.eproposal.product.product01040152.Cepr01040152Mapper;
import id.co.sinarmaslife.eproposal.product.product01040223.Cepr01040223Mapper;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Method;

public class PremiumSummaryDataSource
{
    protected final static Log logger = LogFactory.getLog( PremiumSummaryDataSource.class );

    public enum RoundMode
    {
        NORMAL, TWO_DIGIT
    }

    public static final String MAP_KEY_PK_LABEL = "mapKeyPkLabel";

    public static List< Map<String, Object> > getPremiumSummaryParamsMapList( RoundMode roundMode, Object command, 
    		Cepr00000000EditorUtil editorUtil, BigDecimal manfaatTermRiderAmount, 
    		BigDecimal manfaatPersonalAccidentAmount, BigDecimal manfaatPenyakitKritisAmount, 
    		BigDecimal manfaatPayorsClauseAmount, BigDecimal manfaatWaiverPremiumAmount , BigDecimal manfaatEkaSehatAmount,  BigDecimal manfaatTpdAmount, 
Map<String, String> labelMap ) throws IOException
    {
        //logger.info( "*-*-*-* PremiumSummaryDataSource.getPremiumSummaryParamsMapList" );
        logger.info( "*-*-*-* PremiumSummaryDataSource.getPremiumSummaryParamsMapList" );
        List< Map<String, Object> > result = new ArrayList< Map<String, Object> >();
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        String premium1, premium2, premium3, premium4;
        BigDecimal premium = cepr01030102Form.getPremium();

        try
        {
            String methodName = "";
            if( roundMode == RoundMode.NORMAL ) methodName = "convertToMoney";
            else if( roundMode == RoundMode.TWO_DIGIT ) methodName = "convertToRoundedTwoDigits";

            Method method = editorUtil.getClass().getMethod( methodName, BigDecimal.class, Cepr01030102Form.class );
            premium1 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( premium, cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
            premium2 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( premium.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
            premium3 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( premium.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
            premium4 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( premium.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );

            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "label", "Premi Pokok" );
            params.put( "premium1", premium1 );
            params.put( "premium2", premium2 );
            params.put( "premium3", premium3 );
            params.put( "premium4", premium4 );
            result.add( params );

            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getTermRiderFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getTermRiderFlagDisplay() ) )
            {
//                manfaatTermRiderAmount = manfaatTermRiderAmount.multiply( new BigDecimal( cepr01030102Form.getTermRiderUnitQtyCd() ) );
                premium1 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatTermRiderAmount, cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
                premium2 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatTermRiderAmount.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form  );
                premium3 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatTermRiderAmount.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form  );
                premium4 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatTermRiderAmount.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form  );

                params = new HashMap<String, Object>();
                params.put( "label", "Premi SMiLe Term" );
                params.put( "premium1", premium1 );
                params.put( "premium2", premium2 );
                params.put( "premium3", premium3 );
                params.put( "premium4", premium4 );
                result.add( params );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getPersonalAccidentFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getPersonalAccidentFlagDisplay() ) )
            {
//                manfaatPersonalAccidentAmount = manfaatPersonalAccidentAmount.multiply( new BigDecimal( cepr01030102Form.getPersonalAccidentUnitQtyCd() ) );
            	
            	premium1 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPersonalAccidentAmount, cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
                premium2 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPersonalAccidentAmount.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
                premium3 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPersonalAccidentAmount.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
                premium4 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPersonalAccidentAmount.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );

                params = new HashMap<String, Object>();
                params.put( "label", "Premi SMiLe Personal Accident" );
                params.put( "premium1", premium1 );
                params.put( "premium2", premium2 );
                params.put( "premium3", premium3 );
                params.put( "premium4", premium4 );
                result.add( params );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getCriticalIllnessFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getCriticalIllnessFlagDisplay() ) )
            {
                premium1 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPenyakitKritisAmount, cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
               premium2 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPenyakitKritisAmount.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
               premium3 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPenyakitKritisAmount.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
                premium4 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPenyakitKritisAmount.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );

               
                params = new HashMap<String, Object>();
               // params.put( "label", labelMap.get( MAP_KEY_PK_LABEL ) );
                params.put( "label", "Premi Penyakit Kritis" );
                params.put( "premium1", premium1 );
                params.put( "premium2", premium2 );
                params.put( "premium3", premium3 );
                params.put( "premium4", premium4 );
                result.add( params );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getTpdFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getTpdFlagDisplay() ) )
            {
                premium1 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatTpdAmount, cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
                premium2 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatTpdAmount.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
                premium3 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatTpdAmount.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
                premium4 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatTpdAmount.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );

                params = new HashMap<String, Object>();
                params.put( "label", "Premi SMiLe Total Permanent Disability (TPD)" );
                params.put( "premium1", premium1 );
                params.put( "premium2", premium2 );
                params.put( "premium3", premium3 );
                params.put( "premium4", premium4 );
                result.add( params );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getPayorsClauseFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getPayorsClauseFlagDisplay() ) )
            {
                premium1 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPayorsClauseAmount, cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
                premium2 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPayorsClauseAmount.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
                premium3 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPayorsClauseAmount.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
                premium4 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPayorsClauseAmount.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );

                params = new HashMap<String, Object>();
                params.put( "label", "Premi SMiLe Payor Clause" );
                params.put( "premium1", premium1 );
                params.put( "premium2", premium2 );
                params.put( "premium3", premium3 );
                params.put( "premium4", premium4 );
                result.add( params );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getWaiverPremiumDisabilityFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getWaiverPremiumDisabilityFlagDisplay() ) )
            {
                premium1 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatWaiverPremiumAmount, cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
                premium2 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatWaiverPremiumAmount.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
                premium3 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatWaiverPremiumAmount.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
                premium4 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatWaiverPremiumAmount.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );

                params = new HashMap<String, Object>();
                params.put( "label", "Premi SMiLe Waiver Premium Disability (WPD)" );
                params.put( "premium1", premium1 );
                params.put( "premium2", premium2 );
                params.put( "premium3", premium3 );
                params.put( "premium4", premium4 );
                result.add( params );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getEkaSehatFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getEkaSehatFlagDisplay() ) )
            {
            	double triwulan =  0.35;//triwulan
            	double semesteran = 0.65; //semesteran
            	double bulanan = 0.12;//bulanan
                premium1 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatEkaSehatAmount, cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
                premium2 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatEkaSehatAmount.multiply( new BigDecimal( semesteran ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
                premium3 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatEkaSehatAmount.multiply( new BigDecimal( triwulan ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
                premium4 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatEkaSehatAmount.multiply( new BigDecimal( bulanan ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );

                params = new HashMap<String, Object>();
                params.put( "label", "Premi SMiLe Medical" );
                params.put( "premium1", premium1 );
                params.put( "premium2", premium2 );
                params.put( "premium3", premium3 );
                params.put( "premium4", premium4 );
                result.add( params );
            }
        }
        catch( Exception e )
        {
            logger.error("ERROR", e);
        }

        return result;
    }

    public static List< Map<String, Object> > getPremiumSummaryNoRoundParamsMapList( Object command, Cepr00000000EditorUtil editorUtil, BigDecimal manfaatTermRiderAmount, BigDecimal manfaatPersonalAccidentAmount, BigDecimal manfaatPenyakitKritisAmount, BigDecimal manfaatPayorsClauseAmount, BigDecimal manfaatWaiverPremiumAmount, BigDecimal manfaatTpdAmount, Map<String, String> labelMap ) throws IOException
    {
       // logger.info( "*-*-*-* PremiumSummaryDataSource.getPremiumSummaryNoRoundParamsMapList" );
        logger.info( "*-*-*-* PremiumSummaryDataSource.getPremiumSummaryNoRoundParamsMapList" );
        List< Map<String, Object> > result = new ArrayList< Map<String, Object> >();
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        String premium1, premium2, premium3, premium4;
        BigDecimal premium = cepr01030102Form.getPremium();

        try
        {
        	String methodName = "convertToMoneyNoRound";
        	
            Method method = editorUtil.getClass().getMethod( methodName, BigDecimal.class, Cepr01030102Form.class );
            premium1 = ( String ) method.invoke( editorUtil, premium, cepr01030102Form );
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            { 
            if( cepr01030102Form.getRightPartOfBusinessCd() <= Cepr01040152Mapper.X5){
            		premium2 = ( String ) method.invoke( editorUtil,  premium.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ) , cepr01030102Form );
                    premium3 = ( String ) method.invoke( editorUtil, premium.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ) , cepr01030102Form );
                 	premium4 = ( String ) method.invoke( editorUtil, premium.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ) , cepr01030102Form  ); 
           	}else{
                premium2 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( premium.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ) ), cepr01030102Form );
                premium3 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( premium.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ) ), cepr01030102Form );
            	premium4 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( premium.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ) ), cepr01030102Form  ); 
           	}
           	}
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            { 
                premium2 = ( String ) method.invoke( editorUtil, premium.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ), cepr01030102Form );
                premium3 = ( String ) method.invoke( editorUtil, premium.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ), cepr01030102Form );
            	premium4 = ( String ) method.invoke( editorUtil, premium.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ), cepr01030102Form  ); 
            }
            else
            { 
                premium2 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( premium.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ) ), cepr01030102Form );
                premium3 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( premium.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ) ), cepr01030102Form );
            	premium4 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( premium.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ) ), cepr01030102Form  );
            }
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "label", "Premi Pokok" );
            params.put( "premium1", premium1 );
            params.put( "premium2", premium2 );
            params.put( "premium3", premium3 );
            params.put( "premium4", premium4 );
            result.add( params );

            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getTermRiderFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getTermRiderFlagDisplay() ) )
            {
//                manfaatTermRiderAmount = manfaatTermRiderAmount.multiply( new BigDecimal( cepr01030102Form.getTermRiderUnitQtyCd() ) );
                premium1 = ( String ) method.invoke( editorUtil, manfaatTermRiderAmount, cepr01030102Form );
                if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                {
                    premium2 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatTermRiderAmount.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ) ), cepr01030102Form  );
                    premium3 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatTermRiderAmount.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ) ), cepr01030102Form  );
                	premium4 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatTermRiderAmount.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ) ), cepr01030102Form  ); 
                }
                else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                {
                    premium2 = ( String ) method.invoke( editorUtil, manfaatTermRiderAmount.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ), cepr01030102Form  );
                    premium3 = ( String ) method.invoke( editorUtil, manfaatTermRiderAmount.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ), cepr01030102Form  );
                	premium4 = ( String ) method.invoke( editorUtil, manfaatTermRiderAmount.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ), cepr01030102Form  ); 
                }

                params = new HashMap<String, Object>();
                params.put( "label", "Premi SMiLe Term" );
                params.put( "premium1", premium1 );
                params.put( "premium2", premium2 );
                params.put( "premium3", premium3 );
                params.put( "premium4", premium4 );
                result.add( params );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getPersonalAccidentFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getPersonalAccidentFlagDisplay() ) )
            {
//                manfaatPersonalAccidentAmount = manfaatPersonalAccidentAmount.multiply( new BigDecimal( cepr01030102Form.getPersonalAccidentUnitQtyCd() ) );
                premium1 = ( String ) method.invoke( editorUtil, manfaatPersonalAccidentAmount, cepr01030102Form );
                if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                {
                    premium2 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPersonalAccidentAmount.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ) ), cepr01030102Form );
                    premium3 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPersonalAccidentAmount.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ) ), cepr01030102Form );
                	premium4 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPersonalAccidentAmount.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ) ), cepr01030102Form  ); 
                }
                else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                {
                    premium2 = ( String ) method.invoke( editorUtil, manfaatPersonalAccidentAmount.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ), cepr01030102Form );
                    premium3 = ( String ) method.invoke( editorUtil, manfaatPersonalAccidentAmount.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ), cepr01030102Form );
                	premium4 = ( String ) method.invoke( editorUtil, manfaatPersonalAccidentAmount.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ), cepr01030102Form  ); 
                }

                params = new HashMap<String, Object>();
                params.put( "label", "Premi SMiLe Personal Accident" );
                params.put( "premium1", premium1 );
                params.put( "premium2", premium2 );
                params.put( "premium3", premium3 );
                params.put( "premium4", premium4 );
                result.add( params );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getCriticalIllnessFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getCriticalIllnessFlagDisplay() ) )
            {
                premium1 = ( String ) method.invoke( editorUtil, manfaatPenyakitKritisAmount, cepr01030102Form );
                if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                { 
                        premium2 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPenyakitKritisAmount.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ) ), cepr01030102Form );
                        premium3 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPenyakitKritisAmount.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ) ), cepr01030102Form );
                    	premium4 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPenyakitKritisAmount.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ) ), cepr01030102Form  ); 
                }
                else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        		{
                    premium2 = ( String ) method.invoke( editorUtil, manfaatPenyakitKritisAmount.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ), cepr01030102Form );
                    premium3 = ( String ) method.invoke( editorUtil, manfaatPenyakitKritisAmount.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ), cepr01030102Form );
                	premium4 = ( String ) method.invoke( editorUtil, manfaatPenyakitKritisAmount.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ), cepr01030102Form  ); 
                }

                params = new HashMap<String, Object>();
               // params.put( "label", labelMap.get( MAP_KEY_PK_LABEL ) );
                params.put( "label", "Premi Penyakit Kritis" );
                params.put( "premium1", premium1 );
                params.put( "premium2", premium2 );
                params.put( "premium3", premium3 );
                params.put( "premium4", premium4 );
                result.add( params );
            }
            
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getTpdFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getTpdFlagDisplay() ) )
            {
                premium1 = ( String ) method.invoke( editorUtil, manfaatTpdAmount, cepr01030102Form );
                premium2 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatTpdAmount.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
                premium3 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatTpdAmount.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form );
                premium4 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatTpdAmount.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ), cepr01030102Form.getCurrencyCd() ), cepr01030102Form  ); 

                params = new HashMap<String, Object>();
                params.put( "label", "Premi SMiLe Total Permanent Disability (TPD)" );
                params.put( "premium1", premium1 );
                params.put( "premium2", premium2 );
                params.put( "premium3", premium3 );
                params.put( "premium4", premium4 );
                result.add( params );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getPayorsClauseFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getPayorsClauseFlagDisplay() ) )
            {
                premium1 = ( String ) method.invoke( editorUtil, manfaatPayorsClauseAmount, cepr01030102Form );
                if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                { 
                    premium2 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPayorsClauseAmount.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ) ), cepr01030102Form );
                    premium3 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPayorsClauseAmount.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ) ), cepr01030102Form );
                	premium4 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatPayorsClauseAmount.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ) ), cepr01030102Form  ); 
                }
                else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                { 
                    premium2 = ( String ) method.invoke( editorUtil, manfaatPayorsClauseAmount.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ), cepr01030102Form );
                    premium3 = ( String ) method.invoke( editorUtil, manfaatPayorsClauseAmount.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ), cepr01030102Form );
                	premium4 = ( String ) method.invoke( editorUtil, manfaatPayorsClauseAmount.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ), cepr01030102Form  ); 
                }
               
                params = new HashMap<String, Object>();
                params.put( "label", "Premi SMiLe Payor Clause" );
                params.put( "premium1", premium1 );
                params.put( "premium2", premium2 );
                params.put( "premium3", premium3 );
                params.put( "premium4", premium4 );
                result.add( params );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getWaiverPremiumDisabilityFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getWaiverPremiumDisabilityFlagDisplay() ) )
            {
                premium1 = ( String ) method.invoke( editorUtil, manfaatWaiverPremiumAmount, cepr01030102Form );
                if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                { 
                    premium2 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatWaiverPremiumAmount.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ) ), cepr01030102Form );
                    premium3 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatWaiverPremiumAmount.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ) ), cepr01030102Form );
                	premium4 = ( String ) method.invoke( editorUtil, Cepr00000000MoneyUtil.rounding( manfaatWaiverPremiumAmount.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ) ), cepr01030102Form  ); 
                }
                else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
				{
                    premium2 = ( String ) method.invoke( editorUtil, manfaatWaiverPremiumAmount.multiply( new BigDecimal( Hardcode.FACTOR_SEMESTER ) ), cepr01030102Form );
                    premium3 = ( String ) method.invoke( editorUtil, manfaatWaiverPremiumAmount.multiply( new BigDecimal( Hardcode.FACTOR_TRIWULAN ) ), cepr01030102Form );
                	premium4 = ( String ) method.invoke( editorUtil, manfaatWaiverPremiumAmount.multiply( new BigDecimal( Hardcode.FACTOR_MONTHLY ) ), cepr01030102Form  ); 
                }
                
                params = new HashMap<String, Object>();
                params.put( "label", "Premi SMiLe Waiver Premium Disability");
                params.put( "premium1", premium1 );
                params.put( "premium2", premium2 );
                params.put( "premium3", premium3 );
                params.put( "premium4", premium4 );
                result.add( params );
            }
        }
        catch( Exception e )
        {
            logger.error("ERROR", e);
        }

        return result;
    }
    
    // method that have not param labelMap will set default content for labelMap
    public static List< Map<String, Object> > getPremiumSummaryParamsMapList( RoundMode roundMode, Object command, Cepr00000000EditorUtil editorUtil, BigDecimal manfaatTermRiderAmount, BigDecimal manfaatPersonalAccidentAmount, BigDecimal manfaatPenyakitKritisAmount, BigDecimal manfaatPayorsClauseAmount, BigDecimal manfaatWaiverPremiumAmount, BigDecimal manfaatEkaSehatAmount, BigDecimal manfaatTpdAmount ) throws IOException
    {
       // logger.info( "*-*-*-* PremiumSummaryDataSource.getPremiumSummaryParamsMapList" );
        logger.info( "*-*-*-* PremiumSummaryDataSource.getPremiumSummaryParamsMapList" );
        Map<String, String> labelMap = new HashMap<String, String>();

        // this is intended for replace default "Premi PK"
        labelMap.put( MAP_KEY_PK_LABEL, "Premi PK" );

        return getPremiumSummaryParamsMapList( roundMode, command, editorUtil, manfaatTermRiderAmount, manfaatPersonalAccidentAmount, manfaatPenyakitKritisAmount, manfaatPayorsClauseAmount, manfaatWaiverPremiumAmount, manfaatEkaSehatAmount, manfaatTpdAmount, labelMap );
    }

    public static List< Map<String, Object> > getPremiumSummaryParamsMapList( Object command, Cepr00000000EditorUtil editorUtil, BigDecimal manfaatTermRiderAmount, BigDecimal manfaatPersonalAccidentAmount, BigDecimal manfaatPenyakitKritisAmount, BigDecimal manfaatPayorsClauseAmount, BigDecimal manfaatWaiverPremiumAmount, BigDecimal manfaatEkaSehatAmount, BigDecimal manfaatTpdAmount ) throws IOException
    {
        return getPremiumSummaryParamsMapList( RoundMode.NORMAL, command, editorUtil, manfaatTermRiderAmount, manfaatPersonalAccidentAmount, manfaatPenyakitKritisAmount, manfaatPayorsClauseAmount, manfaatWaiverPremiumAmount, manfaatEkaSehatAmount, manfaatTpdAmount );
    }

    public static List< Map<String, Object> > getPremiumSummaryParamsMapList( Object command, Cepr00000000EditorUtil editorUtil, BigDecimal manfaatTermRiderAmount, BigDecimal manfaatPersonalAccidentAmount, BigDecimal manfaatPenyakitKritisAmount, BigDecimal manfaatPayorsClauseAmount, BigDecimal manfaatWaiverPremiumAmount, BigDecimal manfaatEkaSehatAmount, BigDecimal manfaatTpdAmount, Map<String, String> labelMap ) throws IOException
    {
        return getPremiumSummaryParamsMapList( RoundMode.TWO_DIGIT, command, editorUtil, manfaatTermRiderAmount, manfaatPersonalAccidentAmount, manfaatPenyakitKritisAmount, manfaatPayorsClauseAmount, manfaatWaiverPremiumAmount, manfaatEkaSehatAmount, manfaatTpdAmount, labelMap );
    }

}

