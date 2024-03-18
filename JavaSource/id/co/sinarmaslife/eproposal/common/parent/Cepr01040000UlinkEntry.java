package id.co.sinarmaslife.eproposal.common.parent;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040000UlinkEntry
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Mar 5, 2008 8:30:42 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.eproposal.web.Cepr01030103Form;
import id.co.sinarmaslife.eproposal.model.setup.Product_setup;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_bisnis_rider;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_premium_comb;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_product_calc;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_product_setup;
import id.co.sinarmaslife.eproposal.model.setup.sub.RiderDisplay;
import id.co.sinarmaslife.eproposal.model.vo.UlinkValidatorParVO;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.StringUtil;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.validation.Errors;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import javax.sql.CommonDataSource;

public class Cepr01040000UlinkEntry extends Cepr01040000Entry
{
    protected final Log logger = LogFactory.getLog( getClass() );
    
    public Cepr01040000UlinkEntry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040000UlinkEntry constructor is called ..." );
        
    }

    public void validateCurrentPage( UlinkValidatorParVO parVO )
    {
        logger.info( "*-*-*-* Cepr01040000UlinkEntry.validateCurrentPage" );

        // Determine max UP, I got it from PB aplication, i.e., w_data_usul in event itemchanged
        int li_pmode = cepr01030102Form.getPaymentModeCd();
        if( li_pmode != Hardcode.PAY_MODE_CD_SEKALIGUS )
        {
            List<Integer> businessList = ArrUtil.toListFromArray( new Integer[]{
                    116, 118, 121, 129, 153, 134, 138, 159, 160, 162, 166, 153, 120, 140, 119, 190, 200, 213, 216, 217, 218, 220,224 /**NCR/2021/02/053	Changes Max. UP 1000 x**/ 
            } );

            double ldec_premi;
            double ldec_max;
            double li_komp;
            double data;
            int li_temp;
            int li_bisnis = LazyConverter.toInt( cepr01030102Form.getLeftPartOfBusinessCd() );

            if( businessList.contains( cepr01030102Form.getLeftPartOfBusinessCd() ) )
            {
                ldec_premi = LazyConverter.toDouble( cepr01030102Form.getPremium() );
                li_komp = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
                ldec_max = ldec_premi * li_komp / 100;
                if( li_pmode == Hardcode.PAY_MODE_CD_TRIWULANAN )
                    ldec_max = 4 * ldec_max;
                else if( li_pmode == Hardcode.PAY_MODE_CD_SEMESTERAN )
                    ldec_max = 2 * ldec_max;
                else if( li_pmode == Hardcode.PAY_MODE_CD_BULANAN )
                    ldec_max = 12 * ldec_max;

                data = LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() );
                                
                double fiveTimesUp;
                
                List<Integer> businessListAgency = ArrUtil.toListFromArray( new Integer[]{
                        116, 118, 138, 153, 190, 200, 217, 218
                } );
                
                if( businessListAgency.contains( cepr01030102Form.getLeftPartOfBusinessCd() ) )
                {
                	fiveTimesUp = ldec_max * 10;
                	
                	boolean cek75 = false;
                	if( cepr01030102Form.getCurrencyCd().equals( "01" ) ){
                		if( fiveTimesUp < 7500000 ){
                			fiveTimesUp = 7500000;
                			cek75 = true;
                		}                		
                	}else if( cepr01030102Form.getCurrencyCd().equals( "02" ) ){
                		if( fiveTimesUp < 750 ){
                			fiveTimesUp = 750;
                			cek75 = true;
                		}
                		                		
                	}
                	
                	
                	if( (data < fiveTimesUp) && cek75)
                    {
                        errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED, "error.valueAtLeast", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( fiveTimesUp ) }, null );
                    }
                	else if( (data < fiveTimesUp) && (cek75==false) ){
                		 errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED, "error.minimumUP10Times", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( fiveTimesUp ) }, null );
                	}
                	
                }else{
                	fiveTimesUp = ldec_max * 5;
                	
                	boolean cek75 = false;
                	if( cepr01030102Form.getCurrencyCd().equals( "01" ) ){
                		if( fiveTimesUp < 7500000 ){
                			fiveTimesUp = 7500000;
                			cek75 = true;
                		}                		
                	}else if( cepr01030102Form.getCurrencyCd().equals( "02" ) ){
                		if( fiveTimesUp < 750 ){
                			fiveTimesUp = 750;
                			cek75 = true;
                		}
                		                		
                	}
                	
                	if( (data < fiveTimesUp) && cek75)
                    {
                        errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED, "error.valueAtLeast", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( fiveTimesUp ) }, null );
                    }
                	else if( (data < fiveTimesUp) && (cek75==false) && cepr01030102Form.getLeftPartOfBusinessCd() != 120 )
                     {
                         errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED, "error.minimumUP5Times", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( fiveTimesUp ) }, null );
                     }
                }                
                
                li_temp = of_get_max( li_bisnis );
//                if( cepr01030102Form.getLeftPartOfBusinessCd() == 134 && cepr01030102Form.getRightPartOfBusinessCd() == 13 ){
//                	li_temp = 25;
//                }
                
                double nilaiMaxTemp;
                double nilaiMax;

                if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) ) nilaiMaxTemp = 7500000; else nilaiMaxTemp = 750;
                nilaiMax = nilaiMaxTemp > fiveTimesUp? nilaiMaxTemp : fiveTimesUp;
                String st_infoText = "Info: Batas UP = "
                        + commonUsedBusiness.toMoneyWithCurrencyCd( nilaiMax ) +
                        " s/d " + commonUsedBusiness.toMoneyWithCurrencyCd( li_temp * ldec_max );
                if( data > li_temp * ldec_max )
                {
                    errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED,
                            "error.generalMsg",
                            new Object[]{
                                    "Maximum UP yang diperbolehkan u/ Usia " + cepr01030101Form.getInsuredAge() + " tahun adalah "
                                            + li_temp + " x Premi Tahunan = " + commonUsedBusiness.toMoneyWithCurrencyCd( li_temp * ldec_max )
                                            + "***) " + st_infoText
                            }, null );
                }
                
                
            }
        }

        if( cepr01030102Form.getLeftPartOfBusinessCd() != 120 && cepr01030102Form.getLeftPartOfBusinessCd() != 220 && cepr01030102Form.getLeftPartOfBusinessCd() != 224) /**NCR/2021/02/053	Changes Max. UP 1000 x**/
        {
	        if( cepr01030102Form.getPaymentModeCd() != null )
	        {
	            // Determine max Premium
	            double basePremium = LazyConverter.toDouble( cepr01030102Form.getBasePremium() );
	            double maxBasePremiumIdr = 0;
	            double maxBasePremiumUsd = 0;
	            if( cepr01030101Form.getInsuredAge() >= 1 && cepr01030101Form.getInsuredAge() <= 5 )
	            {
	                if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEKALIGUS )
	                {
	                    maxBasePremiumIdr = parVO.getUnder5YearsSekaligusMaxBasePremiumVO().getIdr();
	                    maxBasePremiumUsd = parVO.getUnder5YearsSekaligusMaxBasePremiumVO().getUsd();
	                }
	                else
	                {
	                    maxBasePremiumIdr = parVO.getUnder5YearsBerkalaMaxBasePremiumVO().getIdr();
	                    maxBasePremiumUsd = parVO.getUnder5YearsBerkalaMaxBasePremiumVO().getUsd();
	                }
	            }
	            else if( cepr01030101Form.getInsuredAge() > 5 )
	            {
	                if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEKALIGUS )
	                {
	                    maxBasePremiumIdr = parVO.getBeyond5YearsSekaligusMaxBasePremiumVO().getIdr();
	                    maxBasePremiumUsd = parVO.getBeyond5YearsSekaligusMaxBasePremiumVO().getUsd();
	                }
	                else
	                {
	                    maxBasePremiumIdr = parVO.getBeyond5YearsBerkalaMaxBasePremiumVO().getIdr();
	                    maxBasePremiumUsd = parVO.getBeyond5YearsBerkalaMaxBasePremiumVO().getUsd();
	                }
	            }
	
	            commonValidator.validateMaximumMoney( Cepr01030102FormConst.BASE_PREMIUM, basePremium, maxBasePremiumIdr, maxBasePremiumUsd );
	        }
        }
    }

    //private int of_get_max( int ar_bisnis )
    protected int of_get_max( int ar_bisnis )
    {
        int li_tf, li_temp, li_usia;
        if( ar_bisnis == 134 ) li_tf = 134; else li_tf = 116;
        if( ar_bisnis == 162 ) li_tf = 162;
        if( ar_bisnis == 166 ) li_tf = 134;
        if( ar_bisnis == 160 || ar_bisnis == 153 || ar_bisnis == 120 || ar_bisnis == 159 || ar_bisnis == 116 || ar_bisnis == 140 ||
        	ar_bisnis == 190 || ar_bisnis == 200 || ar_bisnis == 119 || ar_bisnis == 213 || ar_bisnis == 216 || ar_bisnis == 217 || ar_bisnis == 218 || ar_bisnis == 220) li_tf = 153;
        
        li_usia = LazyConverter.toInt( cepr01030101Form.getInsuredAge() );
        if( ( li_tf == 116 || li_tf == 134 ) && ( li_usia > 60 ) ) li_usia = 60;
        if( ( li_tf == 120 ) && ( li_usia > 65 ) ) li_usia = 65;
        
        Map<String, Object> par = new HashMap<String, Object>();
        par.put( "li_tf", li_tf );
        par.put( "li_usia", li_usia );
        
        //MAX UP JANUARI 2021
        int li_bisnis_det = LazyConverter.toInt( cepr01030102Form.getRightPartOfBusinessCd());
        String prod = ar_bisnis+"-"+li_bisnis_det; /**NCR/2021/02/053	Changes Max. UP 1000 x**/
        HashMap mapGroup = eproposalManager.selectMstConfig(6, "Setting", prod); /**NCR/2021/02/053	Changes Max. UP 1000 x**/
        if (mapGroup != null){ /**NCR/2021/02/053	Changes Max. UP 1000 x**/
        	String maxUp = mapGroup.get("NAME").toString();
        	li_temp = Integer.parseInt(maxUp); 
        }else{
        	li_temp = LazyConverter.toInt( eproposalManager.selectFMax( par ) );
        }
 
        return li_temp;
    }

    //OK
    protected void refreshBaseTopupPremium()
    {
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
        cepr01030102Form.setBasePremium( new BigDecimal( premium * premiumCombinationPercent / 100 ) );
        cepr01030102Form.setTopUpPremium( new BigDecimal( premium * ( 100 - premiumCombinationPercent  ) / 100 ) );
    }
    
    protected void setEducationPackage(){
    	if( cepr01030102Form.getEducationPackageCd() == null || cepr01030102Form.getEducationPackageCd() != null && cepr01030102Form.getEducationPackageCd() == 1 ){
    		disabledRiderForm( false );
    		checkAllRider( false );
    		cepr01030102Form.setEducationPackageCd( 1 );
    	}else{
    		checkAllRider( false );
    		disabledRiderForm( true );
    		disabledListOptionAtAllRiderForm( true );
    		
    		if( cepr01030102Form.getEducationPackageCd() > 1 ){
    			cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
    			
    			cepr01030103Form.setPaFlag( CommonConst.CHECKED_TRUE );
    			cepr01030103Form.setPaRiskCd( 1 );
    			cepr01030103Form.setPaClassListIsDisabled( CommonConst.DISABLED_FALSE );
    			cepr01030103Form.setPaUnitQtyListIsDisabled( CommonConst.DISABLED_FALSE );
    			
    			cepr01030103Form.setPayorTpdCiDeathFlag( CommonConst.CHECKED_TRUE );
    			
    			if( cepr01030102Form.getEducationPackageCd().equals( Hardcode.EDUCATION_PAKET_B ) ){
    				cepr01030103Form.setScholarshipFlag( CommonConst.CHECKED_TRUE );
    				cepr01030103Form.setScholarshipListIsDisabled( CommonConst.DISABLED_FALSE );
    				cepr01030103Form.setScholarshipChooseListIsDisabled( CommonConst.DISABLED_FALSE );
    			}else if( cepr01030102Form.getEducationPackageCd().equals( Hardcode.EDUCATION_PAKET_C ) ){
    				cepr01030103Form.setScholarshipFlag( CommonConst.CHECKED_TRUE );
    				cepr01030103Form.setScholarshipListIsDisabled( CommonConst.DISABLED_FALSE );
    				cepr01030103Form.setScholarshipChooseListIsDisabled( CommonConst.DISABLED_FALSE );
    				
    				cepr01030103Form.setEkaSehatFlag( CommonConst.CHECKED_TRUE );
        			cepr01030103Form.setEkaSehatListIsDisabled( CommonConst.DISABLED_FALSE );
        			cepr01030103Form.setEkaSehatFlagIsDisabled( CommonConst.DISABLED_FALSE );        			     		
        	
        			cepr01030103Form.setHcpFlag( CommonConst.CHECKED_TRUE );
        			cepr01030103Form.setHcpListIsDisabled( CommonConst.DISABLED_FALSE );
        			cepr01030103Form.setHcpFlagIsDisabled( CommonConst.DISABLED_FALSE );
    			}
    		}
    	}
    }
    
    protected void setSmileLadiesPackage(){
    	if( cepr01030102Form.getSmileLadiesPackageCd() == null || cepr01030102Form.getSmileLadiesPackageCd() != null && cepr01030102Form.getSmileLadiesPackageCd() == 1 ){
    		disabledRiderForm( false );
    		cepr01030102Form.setSmileLadiesPackageCd( 1 );
    	}else{
    		checkAllRider( false );
    		disabledRiderForm( true );
    		disabledListOptionAtAllRiderForm(true);
    		cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
    		if( cepr01030102Form.getSmileLadiesPackageCd().equals( Hardcode.SMILE_LADIES_PAKET_DIAMOND ) )
    		{
    			cepr01030103Form.setLadiesInsFlag( CommonConst.CHECKED_TRUE );
    			cepr01030103Form.setLadiesInsChooseListIsDisabled( CommonConst.DISABLED_FALSE );
    			cepr01030103Form.setLadiesInsListIsDisabled( CommonConst.DISABLED_FALSE );
    			cepr01030103Form.setLadiesMedExpenseFlag( CommonConst.CHECKED_TRUE );
    			cepr01030103Form.setLadiesMedExpenseListIsDisabled( CommonConst.DISABLED_FALSE );
        	}
    		else if( cepr01030102Form.getSmileLadiesPackageCd().equals( Hardcode.SMILE_LADIES_PAKET_RUBY ) )
        	{
        		cepr01030103Form.setLadiesMedExpenseFlag( CommonConst.CHECKED_TRUE );
        		cepr01030103Form.setLadiesMedExpenseListIsDisabled( CommonConst.DISABLED_FALSE );
        	}
        	else if( cepr01030102Form.getSmileLadiesPackageCd().equals( Hardcode.SMILE_LADIES_PAKET_PEARL ) )
        	{
        		cepr01030103Form.setHcpLadiesFlag( CommonConst.CHECKED_TRUE );
        		cepr01030103Form.setHcpLadiesListIsDisabled( CommonConst.DISABLED_FALSE );
        	}
        	else if( cepr01030102Form.getSmileLadiesPackageCd().equals( Hardcode.SMILE_LADIES_PAKET_FANTASTIC ) )
        	{
        		cepr01030103Form.setCiFlag( CommonConst.CHECKED_TRUE );
        		cepr01030103Form.setCiChooseListIsDisabled( CommonConst.DISABLED_FALSE );
        		
    			
    			cepr01030103Form.setEkaSehatFlag( CommonConst.CHECKED_TRUE );
    			cepr01030103Form.setEkaSehatListIsDisabled( CommonConst.DISABLED_FALSE );
    			
    			cepr01030103Form.setPaFlag( CommonConst.CHECKED_TRUE );
    			cepr01030103Form.setPaRiskCd( 1 );
    			cepr01030103Form.setPaClassListIsDisabled( CommonConst.DISABLED_FALSE );
    			cepr01030103Form.setPaUnitQtyListIsDisabled( CommonConst.DISABLED_FALSE );
    			
    			if( credentialsVO != null && credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_BANCASS ) ){
    				cepr01030103Form.setLadiesInsCd( 3 ); 
    				cepr01030103Form.setLadiesInsFlag( CommonConst.CHECKED_TRUE );
        			cepr01030103Form.setLadiesInsChooseListIsDisabled( CommonConst.DISABLED_FALSE );
        			cepr01030103Form.setLadiesInsListIsDisabled( CommonConst.DISABLED_TRUE );
    			}else{
    				cepr01030103Form.setLadiesInsFlag( CommonConst.CHECKED_TRUE );
        			cepr01030103Form.setLadiesInsChooseListIsDisabled( CommonConst.DISABLED_FALSE );
        			cepr01030103Form.setLadiesInsListIsDisabled( CommonConst.DISABLED_FALSE );
    			}
    			
        		
        	}
        	else if( cepr01030102Form.getSmileLadiesPackageCd().equals( Hardcode.SMILE_LADIES_PAKET_EXCELLENT ) )
        	{
        		cepr01030103Form.setCiFlag( CommonConst.CHECKED_TRUE );
        		cepr01030103Form.setCiChooseListIsDisabled( CommonConst.DISABLED_FALSE );
        		
    			cepr01030103Form.setLadiesInsFlag( CommonConst.CHECKED_TRUE );
    			cepr01030103Form.setLadiesInsChooseListIsDisabled( CommonConst.DISABLED_FALSE );
    			cepr01030103Form.setLadiesInsListIsDisabled( CommonConst.DISABLED_FALSE );
    			
    		}
    	}
    }
    
    protected void setRiderValueForFirstTime() {
    	
//        cepr01030103Form.setCiChooseCd("50");
//    	cepr01030103Form.setciRiderAmount( new BigDecimal (7500000) );/**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
//        cepr01030103Form.setEsci99ChooseCd("50");
//        cepr01030103Form.setesci99RiderAmountt( new BigDecimal (7500000) );/**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
        cepr01030103Form.setLadiesInsCd(1);
        cepr01030103Form.setLadiesInsChooseCd("50");
        cepr01030103Form.setPaClassCd(1);
        cepr01030103Form.setPaRiskCd(1);
        cepr01030103Form.setPaUnitQtyCd(1);
        cepr01030103Form.setLadiesMedExpenseCd( 1 );
        cepr01030103Form.setHcpLadiesCd(1);		
        cepr01030103Form.setScholarshipChooseCd( "22" );
        cepr01030103Form.setScholarshipCd( 5000000 );
        cepr01030103Form.setWaiverTpdCiChooseCd(1);
        
        if( cepr01030102Form.getSmilePensionPackageCd() != null && cepr01030102Form.getSmilePensionPackageCd().intValue() > 1 ){
            cepr01030103Form.setHcpCd(5);
        }else{
        	cepr01030103Form.setHcpCd( 1 );
        }
 
        if( credentialsVO != null && credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_BANCASS ) ){
        	cepr01030103Form.setEkaSehatCd(13);
        }else{
        	if( cepr01030102Form.getSmilePensionPackageCd() != null && cepr01030102Form.getSmilePensionPackageCd().intValue() > 1 ){
        		cepr01030103Form.setEkaSehatCd(8);
        	}else{
        		cepr01030103Form.setEkaSehatCd(1);
        	}
        }
        
	}
    
    protected void setSmilePension() {
    	if( cepr01030102Form.getSmilePensionPackageCd() == null || cepr01030102Form.getSmilePensionPackageCd() != null && cepr01030102Form.getSmilePensionPackageCd() == 1 ){
    		disabledRiderForm( false );
    		cepr01030102Form.setSmilePensionPackageCd(1);
    	}else{
    		checkAllRider( false );
    		disabledRiderForm( true );
    		disabledListOptionAtAllRiderForm(true);
    		cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
    		
    		cepr01030103Form.setPaFlag( CommonConst.CHECKED_TRUE );
			cepr01030103Form.setPaRiskCd( 1 );
			cepr01030103Form.setPaUnitQtyCd( 1 );
			cepr01030103Form.setPaClassListIsDisabled( CommonConst.DISABLED_FALSE );
			cepr01030103Form.setPaUnitQtyListIsDisabled( CommonConst.DISABLED_TRUE );
			
			cepr01030103Form.setWaiverTpdCiChooseCd(1);
			cepr01030103Form.setWaiverTpdCiFlag( CommonConst.CHECKED_TRUE );
			
			cepr01030102Form.setDynamicIdr( new BigDecimal("100"));
			cepr01030102Form.setFixIdr( BigDecimal.ZERO );
			cepr01030102Form.setAggresiveIdr( BigDecimal.ZERO );
			
    		if( cepr01030102Form.getSmilePensionPackageCd().equals( Hardcode.SMILE_PENSION_PAKET_B ) )
        	{
    			cepr01030103Form.setTpdFlag( CommonConst.CHECKED_TRUE );
        		cepr01030103Form.setTpdFlagIsDisabled( CommonConst.DISABLED_TRUE );
        	}
        	else if( cepr01030102Form.getSmilePensionPackageCd().equals( Hardcode.SMILE_PENSION_PAKET_C ) )
        	{
        		cepr01030103Form.setTpdFlag( CommonConst.CHECKED_TRUE );
        		cepr01030103Form.setTpdFlagIsDisabled( CommonConst.DISABLED_TRUE );
        		
         		cepr01030103Form.setCiFlag( CommonConst.CHECKED_TRUE );
        		cepr01030103Form.setCiChooseListIsDisabled( CommonConst.DISABLED_FALSE );
        	}
        	else if( cepr01030102Form.getSmilePensionPackageCd().equals( Hardcode.SMILE_PENSION_PAKET_D ) )
        	{
        		cepr01030103Form.setTpdFlag( CommonConst.CHECKED_TRUE );
        		cepr01030103Form.setTpdFlagIsDisabled( CommonConst.DISABLED_TRUE );
        		
         		cepr01030103Form.setCiFlag( CommonConst.CHECKED_TRUE );
        		cepr01030103Form.setCiChooseListIsDisabled( CommonConst.DISABLED_FALSE );
        		
        		cepr01030103Form.setHcpCd(5);
        		cepr01030103Form.setHcpFlag( CommonConst.CHECKED_TRUE );
        		cepr01030103Form.setHcpListIsDisabled( CommonConst.DISABLED_TRUE );
        	}
        	else if( cepr01030102Form.getSmilePensionPackageCd().equals( Hardcode.SMILE_PENSION_PAKET_E ) )
        	{
        		cepr01030103Form.setTpdFlag( CommonConst.CHECKED_TRUE );
        		cepr01030103Form.setTpdFlagIsDisabled( CommonConst.DISABLED_TRUE );
        		
         		cepr01030103Form.setCiFlag( CommonConst.CHECKED_TRUE );
        		cepr01030103Form.setCiChooseListIsDisabled( CommonConst.DISABLED_FALSE );
        		
        		cepr01030103Form.setEkaSehatCd(8);
        		cepr01030103Form.setEkaSehatFlag( CommonConst.CHECKED_TRUE );
    			cepr01030103Form.setEkaSehatListIsDisabled( CommonConst.DISABLED_TRUE );
    			
    		}
    	}
	}
    
    protected void setProtectorPackage(){    	
    	if( cepr01030102Form.getProtectorPackageCd() == null || cepr01030102Form.getProtectorPackageCd() != null && cepr01030102Form.getProtectorPackageCd() == 1 ){
        	disabledRiderForm( false );
        	checkAllRider( false );
        	cepr01030102Form.setProtectorPackageCd( 1 );
        	disabledListOptionAtAllRiderForm(true);
        	
        }else{
        	checkAllRider( false );
    		disabledRiderForm( true ); 
    		
    		cepr01030103Form.setTermRiderAmount( cepr01030102Form.getTotalSumInsured() );
//        	cepr01030103Form.setCiChooseCd("50");
//    		cepr01030103Form.setciRiderAmount( new BigDecimal (7500000) );/**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
        	cepr01030103Form.setPaUnitQtyCd(2);
        	cepr01030103Form.setPaRiskCd(1);
        	
    		cepr01030103Form.setPaUnitQtyListIsDisabled( CommonConst.DISABLED_TRUE);
    		cepr01030103Form.setPaRiskListIsDisabled(CommonConst.DISABLED_FALSE);
    		cepr01030103Form.setPaClassListIsDisabled(CommonConst.DISABLED_FALSE);
    		
    		cepr01030103Form.setPaFlag( CommonConst.CHECKED_TRUE );
    		cepr01030103Form.setTpdFlag( CommonConst.CHECKED_TRUE );
    		cepr01030103Form.setCiFlag( CommonConst.CHECKED_TRUE );
    		cepr01030103Form.setTermRiderFlag(  CommonConst.CHECKED_TRUE );
        }
    }
        
    
}