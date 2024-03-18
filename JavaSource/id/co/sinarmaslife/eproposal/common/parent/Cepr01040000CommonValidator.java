package id.co.sinarmaslife.eproposal.common.parent;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040000CommonValidator
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Sep 27, 2007 10:37:26 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.Date;

import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.business.Cepr00000000CommonUsedBusiness;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.eproposal.web.Cepr01030104Form;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.LazyConverter;

public class Cepr01040000CommonValidator extends Cepr01040000Validator
{
    protected final Log logger = LogFactory.getLog( getClass() );
    protected Cepr01030101Form cepr01030101Form;
    protected Cepr01030102Form cepr01030102Form;
    protected Cepr01030104Form cepr01030104Form;
    protected Cepr00000000CommonUsedBusiness commonUsedBusiness;

    public Cepr01040000CommonValidator( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040000CommonValidator constructor is called ..." );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        this.cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        this.cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        this.cepr01030104Form = cepr01030000HoldingForm.getCepr01030104Form();
        this.commonUsedBusiness = new Cepr00000000CommonUsedBusiness( eproposalManager, editorUtil, command );
    }

    public boolean validateInsuredAge( String formId, int minAge, int maxAge )
    {

        logger.info( "*-*-*-* Cepr01040000CommonValidator.validateInsuredAge" );
        boolean result = false;
        if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_EKA_LINK_80_PLUS_SYARIAH ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_SYARIAH ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_88 ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_EDUVEST ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_MEDICAL ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_MEDICAL_IL ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_MEDICAL_IL_SYARIAH ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_MEDICAL_SYARIAH ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99 ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99_SYARIAH ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_CERDAS ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_CERDAS_SYARIAH ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_PLATINUM_LINK ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SIMAS_PRIME_LINK_SYARIAH ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_AMANAH_LINK ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SIMAS_MAGNA_LINK ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SIMAS_MAGNA_LINK_SYARIAH ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_PROVESTARA_SMART_HEALTH ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_VIP_HOSPITAL_PLAN ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PLUS ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_SYARIAH ||
        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_100
        		)
        {
        	if( cepr01030101Form.getInsuredAge() == 0 ){
        		String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
        		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<15)
        		{
        			errors.rejectValue( formId, "error.insuredAgeMinDay", new Object[]{ Integer.toString( 15 )  }, null );
        		}else{
        			// JIKA TERTANGGUNG BERUMUR LBH DR 15 HARI DIHITUNG JADI 1 TAHUN
        			if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_EKA_LINK_80_PLUS_SYARIAH ||
        					cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_EDUVEST ||
        					cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_SYARIAH ||
        	        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK ||
        	        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_88 ||
        	        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_MEDICAL ||
        	        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_MEDICAL_SYARIAH ||
        	        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_MEDIVEST ||
        	        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_MEDICAL_IL ||
        	        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_MEDICAL_IL_SYARIAH ||
        	        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99 ||
        	        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99_SYARIAH ||
        	        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_CERDAS ||
        	        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_CERDAS_SYARIAH ||
        	        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_PLATINUM_LINK ||
        	               	cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SIMAS_PRIME_LINK_SYARIAH ||
        	        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_AMANAH_LINK ||
        	        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SIMAS_MAGNA_LINK ||
        	        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SIMAS_MAGNA_LINK_SYARIAH ||
        	        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_PROVESTARA_SMART_HEALTH ||
        	        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_VIP_HOSPITAL_PLAN ||
        	        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PLUS ||
        	        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_SYARIAH ||
        	        		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_100
        					)	
        				cepr01030101Form.setInsuredAge(1);
        			result = true;
        		}        		
        	}else{
        		if( cepr01030101Form.getInsuredAge() > maxAge )
    	        {
    	            errors.rejectValue( formId, "error.insuredAgeMax", new Object[]{ Integer.toString( maxAge ) }, null );
    	        }
    	        else if( cepr01030101Form.getInsuredAge() < minAge )
    	        {
    	            errors.rejectValue( formId, "error.insuredAgeMin", new Object[]{ Integer.toString( minAge )  }, null );
    	        }
    	        else
    	        {
    	            result = true;
    	        }
        	}
        }
        else{
	        if( cepr01030101Form.getInsuredAge() > maxAge )
	        {
	            errors.rejectValue( formId, "error.insuredAgeMax", new Object[]{ Integer.toString( maxAge ) }, null );
	        }
	        else if( cepr01030101Form.getInsuredAge() < minAge )
	        {
	            errors.rejectValue( formId, "error.insuredAgeMin", new Object[]{ Integer.toString( minAge )  }, null );
	        }
	        else
	        {
	            result = true;
	        }
        }

        return result;
    }
    
    public boolean validatePremiumFurloughYearMax( int maxAge, int age )
    {
        logger.info( "*-*-*-* Cepr01040000CommonValidator.validateInsuredAge" );
        boolean result = false;

        if( age > maxAge )
        {
            errors.rejectValue( Cepr01030102FormConst.PREMIUM_FURLOUGH_YEAR, "error.premiumFurloughYearMax", new Object[]{ Integer.toString( maxAge ) }, null );
        }
        else
        {
            result = true;
        }

        return result;
    }
       
    public boolean validatePremiumFurloughYearMin( int minAge, int age )
    {
        logger.info( "*-*-*-* Cepr01040000CommonValidator.validateInsuredAge" );
        boolean result = false;

        if( age < minAge )
        {
            errors.rejectValue( Cepr01030102FormConst.PREMIUM_FURLOUGH_YEAR, "error.premiumFurloughYearMin", new Object[]{ Integer.toString( minAge ) }, null );
        }
        else
        {
            result = true;
        }

        return result;
    }
    
    
    public boolean validatePolicyHolderAge( String formId, int minAge, int maxAge )
    {
        logger.info( "*-*-*-* Cepr01040000CommonValidator.validatePolicyHolderAge" );
        boolean result = false;

        if( cepr01030101Form.getPolicyHolderAge() > maxAge )
        {
            errors.rejectValue( formId, "error.policyHolderAgeMax", new Object[]{ Integer.toString( maxAge ) }, null );
        }
        else if( cepr01030101Form.getPolicyHolderAge() < minAge )
        {
            errors.rejectValue( formId, "error.policyHolderAgeMin", new Object[]{ Integer.toString( minAge )  }, null );
        }
        else
        {
            result = true;
        }

        return result;
    }


    public boolean validateInsuredIntervalAge( String formId, int minAge, int maxAge, String errMsgId )
    {
        logger.info( "*-*-*-* Cepr01040000CommonValidator.validateInsuredIntervalAge" );
        boolean result = false;

        if( cepr01030101Form.getInsuredAge() > maxAge || cepr01030101Form.getInsuredAge() < minAge )
        {
            errors.rejectValue( formId, errMsgId, new Object[]{ Integer.toString( minAge ), Integer.toString( maxAge ) }, null );
        }
        else
        {
            result = true;
        }

        return result;
    }
    
    
    public boolean validateMaxAgeProduct( Integer age, String formId, String product, int maxAge )
    {
        boolean result = false;

        if( age > maxAge )
        {
            errors.rejectValue( formId, "error.maxAgeProduct", new Object[]{ product, Integer.toString( maxAge ) }, null );
        }
        else
        {
            result = true;
        }

        return result;
    }

    public boolean validateInsuredIntervalAge( String formId, int minAge, int maxAge )
    {
        return validateInsuredIntervalAge( formId, minAge, maxAge, "error.insuredAgeInterval" );
    }

    public boolean validatePolicyHolderIntervalAge( String formId, int minAge, int maxAge, String errMsgId )
    {
        logger.info( "*-*-*-* Cepr01040000CommonValidator.validatePolicyHolderIntervalAge" );
        boolean result = false;

        if( cepr01030101Form.getPolicyHolderAge() > maxAge || cepr01030101Form.getPolicyHolderAge() < minAge )
        {
            errors.rejectValue( formId, errMsgId, new Object[]{ Integer.toString( minAge ), Integer.toString( maxAge ) }, null );
        }
        else
        {
            result = true;
        }

        return result;
    }

    public boolean validatePolicyHolderIntervalAge( String formId, int minAge, int maxAge )
    {
        return validatePolicyHolderIntervalAge( formId, minAge, maxAge, "error.policyHolderAgeInterval" );
    }

    public boolean validatePolicyHolderIsInsured( String formId )
    {
        logger.info( "*-*-*-* Cepr01040000CommonValidator.validatePolicyHolderIsInsured" );
        boolean policyHolderIsInsured = true;
        if( !commonUsedBusiness.policyHolderIsInsured() )
        {
            errors.rejectValue( formId, "error.policyHolderMustBeTheSamePersonAsInsured", null, null );
            policyHolderIsInsured = false;
        }
        return policyHolderIsInsured;
    }

    public boolean validatePolicyHolderIsNotInsured( String formId )
    {
        logger.info( "*-*-*-* Cepr01040000CommonValidator.validatePolicyHolderIsNotInsured" );
        boolean policyHolderIsNotInsured = true;
        if( commonUsedBusiness.policyHolderIsInsured() )
        {
            errors.rejectValue( formId, "error.policyHolderMustNotBeTheSamePersonAsInsured", null, null );
            policyHolderIsNotInsured = false;
        }
        return policyHolderIsNotInsured;
    }

    public boolean validateMinimumMoney( String formId, BigDecimal value, BigDecimal minValueIdr, BigDecimal minValueUsd )
    {
        logger.info( "*-*-*-* Cepr01040000CommonValidator.validateMinimumMoney" );
        boolean result = true;
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        Cepr00000000CommonUsedBusiness commonUsedBusiness = new Cepr00000000CommonUsedBusiness( null, editorUtil, command );
        if( value != null )
        {
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                if( value.compareTo( minValueIdr ) < 0 )
                {
                    errors.rejectValue( formId, "error.valueAtLeast", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( minValueIdr ) }, null );
                    result = false;
                }
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                if( value.compareTo( minValueUsd ) < 0 )
                {
                    errors.rejectValue( formId, "error.valueAtLeast", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( minValueUsd ) }, null );
                    result = false;
                }
            }
        }
        return result;
    }
    
    
    public boolean validateRangeMoney( String formId, BigDecimal value, BigDecimal minValue, BigDecimal maxValue )
    {
        logger.info( "*-*-*-* Cepr01040000CommonValidator.validateMinimumMoney" );
        boolean result = true;
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        Cepr00000000CommonUsedBusiness commonUsedBusiness = new Cepr00000000CommonUsedBusiness( null, editorUtil, command );
        if( value != null )
        {
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                if( value.compareTo( minValue ) < 0 ||  value.compareTo( maxValue ) > 0 )
                {
                	String min = commonUsedBusiness.toMoneyWithCurrencyCd( minValue );
                	String max = commonUsedBusiness.toMoneyWithCurrencyCd( maxValue );
                    errors.rejectValue( formId, "error.batasUp", new Object[]{ min,  max }, null );
                    result = false;
                }
            }
        }
        return result;
    }

    public boolean validateMinimumMoney( String formId, double value, double minValueIdr, double minValueUsd )
    {
        logger.info( "*-*-*-* Cepr01040000CommonValidator.validateMinimumMoney" );
        return validateMinimumMoney( formId, new BigDecimal( value ), new BigDecimal( minValueIdr ), new BigDecimal( minValueUsd ) );
    }

    public boolean validateMaximumMoney( String formId, BigDecimal value, BigDecimal maxValueIdr, BigDecimal maxValueUsd, String errMsg )
    {
        logger.info( "*-*-*-* Cepr01040000CommonValidator.validateMaximumMoney" );
        boolean result = true;
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        Cepr00000000CommonUsedBusiness commonUsedBusiness = new Cepr00000000CommonUsedBusiness( null, editorUtil, command );
        if( value != null )
        {
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	if( maxValueIdr != null ){
                if( value.compareTo( maxValueIdr ) > 0 )
                {
                    errors.rejectValue( formId, errMsg, new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( maxValueIdr ) }, null );
                    result = false;
                }}
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	if( maxValueUsd != null ){
                if( value.compareTo( maxValueUsd ) > 0 )
                {
                    errors.rejectValue( formId, errMsg, new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( maxValueUsd ) }, null );
                    result = false;
                }}
            }
        }
        return result;
    }

    public boolean validateMaximumMoney( String formId, double value, double maxValueIdr, double maxValueUsd, String errMsgId )
    {
        logger.info( "*-*-*-* Cepr01040000CommonValidator.validateMaximumMoney" );
        return validateMaximumMoney( formId, new BigDecimal( value ), new BigDecimal( maxValueIdr ), new BigDecimal( maxValueUsd ), errMsgId );
    }

    public boolean validateMaximumMoney( String formId, BigDecimal value, BigDecimal maxValueIdr, BigDecimal maxValueUsd )
    {
        logger.info( "*-*-*-* Cepr01040000CommonValidator.validateMaximumMoney" );
        return validateMaximumMoney( formId, value, maxValueIdr, maxValueUsd, "error.valueMax" );
    }

    public boolean validateMaximumMoney( String formId, double value, double maxValueIdr, double maxValueUsd )
    {
        logger.info( "*-*-*-* Cepr01040000CommonValidator.validateMaximumMoney" );
        return validateMaximumMoney( formId, new BigDecimal( value ), new BigDecimal( maxValueIdr ), new BigDecimal( maxValueUsd ) );
    }

    public boolean validateTenFolded( String formId, BigDecimal value )
    {
        logger.info( "*-*-*-* Cepr01040000CommonValidator.validateTenFolded" );
        boolean result = true;
        double val = LazyConverter.toDouble( value );

        if( val % 10 != 0 )
        {
            errors.rejectValue( formId, "error.investmentPercentMustInTenFolded", null, null );
            result = false;
        }

        return result;
    }

    public boolean validateTenFoldedForAllInvestment()
    {
        logger.info( "*-*-*-* Cepr01040000CommonValidator.validateTenFoldedForAllInvestment" );
        boolean result = true;
        int err_result = 0;
        
        if( CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getFixIdrDisplay() ) )
        {
        	//result = result & validateTenFolded( Cepr01030102FormConst.FIX_IDR, cepr01030102Form.getFixIdr() );
        	err_result = err_result + (validateTenFolded( Cepr01030102FormConst.FIX_IDR, cepr01030102Form.getFixIdr() )?0:1);
        }
        if( CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getDynamicIdrDisplay() ) )
        {
//            result = result & validateTenFolded( Cepr01030102FormConst.DYNAMIC_IDR, cepr01030102Form.getDynamicIdr() );
            err_result = err_result + (validateTenFolded( Cepr01030102FormConst.DYNAMIC_IDR, cepr01030102Form.getDynamicIdr() )?0:1);
        }
        if( CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getAggresiveIdrDisplay() ) )
        {
//            result = result & validateTenFolded( Cepr01030102FormConst.AGRESSIVE_IDR, cepr01030102Form.getAggresiveIdr() );
            err_result = err_result + (validateTenFolded( Cepr01030102FormConst.AGRESSIVE_IDR, cepr01030102Form.getAggresiveIdr() )?0:1);
        }
        if( CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getSecureUsdDisplay() ) )
        {
//            result = result & validateTenFolded( Cepr01030102FormConst.SECURE_USD, cepr01030102Form.getSecureUsd() );
            err_result = err_result + (validateTenFolded( Cepr01030102FormConst.SECURE_USD, cepr01030102Form.getSecureUsd() )?0:1);
        }
        if( CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getDynamicUsdDisplay() ) )
        {
//            result = result & validateTenFolded( Cepr01030102FormConst.DYNAMIC_USD, cepr01030102Form.getDynamicUsd() );
            err_result = err_result + (validateTenFolded( Cepr01030102FormConst.DYNAMIC_USD, cepr01030102Form.getDynamicUsd() )?0:1);
        }
        
        if( CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getAggresiveUsdDisplay() ) )/**USD Fund BSIM Products**/
        {
//            result = result & validateTenFolded( Cepr01030102FormConst.AGGRESIVE_USD, cepr01030102Form.getAggresiveUsd() );
            err_result = err_result + (validateTenFolded( Cepr01030102FormConst.AGGRESIVE_USD, cepr01030102Form.getAggresiveUsd() )?0:1);
        }
        
        if(cepr01030102Form.getLeftPartOfBusinessCd() != Hardcode.PRODUK_PLATINUM_LINK){ 
        	validateMaxPremiumUsd();
        }
        
        if(err_result > 0) {
        	result = false;
        }

        return result;

    }
    
    public void validateMaxPremiumUsd(){
    	if( cepr01030102Form.getPremium() != null )
        {
    		double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
            if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {             	
            	if( new BigDecimal(100000).compareTo( new BigDecimal(premium) ) < 0 )
                {
                    errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.valueMax", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( new BigDecimal(100000) ) }, null );
                }
            }
        }
    }
    
    public boolean validateSpecialOffer(){
    	 boolean result = true;
//    	 CredentialsVO credentialsVO = ( CredentialsVO ) command;
    	 Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
    	 CredentialsVO credentialsVO = cepr01030000HoldingForm.getCredentialsVO();
    	 if( credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_MNC ) ){
	    	 if( cepr01030102Form.getSpecialOfferCd() == null || cepr01030102Form.getSpecialOfferCd() != null && cepr01030102Form.getSpecialOfferCd().equals( 0 ) ){
	    		 result = false;
	    		 errors.rejectValue( Cepr01030102FormConst.SPECIAL_OFFER, "error.formEmpty", null, null );
	    	 }
	     }
    	 return result;
    }
    public boolean validatePremiumFurloughYear( String formId, int minYear, int maxYear )
    {
        logger.info( "*-*-*-* Cepr01040000CommonValidator.validatePremiumFurloughYear" );
        boolean result = false;

        if( cepr01030102Form.getPremiumFurloughYear() > maxYear || cepr01030102Form.getPremiumFurloughYear() < minYear )
        {
            errors.rejectValue( formId, "error.premiumFurloughYearInterval", new Object[]{ Integer.toString( minYear ), Integer.toString( maxYear ) }, null );
        }
        else
        {
            result = true;
        }

        return result;
    }

    public boolean validateInvestmentSumIs100Percent()
    {
        logger.info( "*-*-*-* Cepr01040000CommonValidator.validateInvestmentSumIs100Percent" );
        boolean result = false;

        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
        	 if( (cepr01030102Form.getLeftPartOfBusinessCd() == 134 && (cepr01030102Form.getRightPartOfBusinessCd() == 5 || cepr01030102Form.getRightPartOfBusinessCd() == 10 || cepr01030102Form.getRightPartOfBusinessCd() >= 12
        		|| cepr01030102Form.getRightPartOfBusinessCd() == 7	|| cepr01030102Form.getRightPartOfBusinessCd() == 8 )) 
        		|| (cepr01030102Form.getLeftPartOfBusinessCd() == 220 && (cepr01030102Form.getRightPartOfBusinessCd() == 4 || cepr01030102Form.getRightPartOfBusinessCd() <= 2) )
        		|| (cepr01030102Form.getLeftPartOfBusinessCd() == 190 && (cepr01030102Form.getRightPartOfBusinessCd() == 7 ||  cepr01030102Form.getRightPartOfBusinessCd() == 8 || cepr01030102Form.getRightPartOfBusinessCd() == 3 || cepr01030102Form.getRightPartOfBusinessCd() == 4) )
        		|| (cepr01030102Form.getLeftPartOfBusinessCd() == 116 && (cepr01030102Form.getRightPartOfBusinessCd() == 3 ||  cepr01030102Form.getRightPartOfBusinessCd() == 4))
        		|| (cepr01030102Form.getLeftPartOfBusinessCd() == 217 && (cepr01030102Form.getRightPartOfBusinessCd() == 1))
        		|| (cepr01030102Form.getLeftPartOfBusinessCd() == 120 && (cepr01030102Form.getRightPartOfBusinessCd() == 22) || (cepr01030102Form.getRightPartOfBusinessCd() == 23) || (cepr01030102Form.getRightPartOfBusinessCd() == 24)) //IGA - PROJECT SIMPOL
        		) {
        		 if( cepr01030102Form.getFixIdr() == null &&
                         cepr01030102Form.getDynamicIdr() == null &&
                         cepr01030102Form.getAggresiveIdr() == null &&
                         cepr01030102Form.getFixSimasIdr() == null &&
                         cepr01030102Form.getDynamicSimasIdr() == null &&
                         cepr01030102Form.getAggresiveSimasIdr() == null &&
                         cepr01030102Form.getCashFundIdr() == null &&
                         cepr01030102Form.getExcellinkEquityIdr() == null
        				 )
                 {
                     errors.rejectValue( Cepr01030102FormConst.FIX_IDR, "error.oneOfInvestmentChoice", null, null );
                 }else{
                 
	        		 double fixIdr = LazyConverter.toDouble( cepr01030102Form.getFixIdr() );
	                 double dynamicIdr = LazyConverter.toDouble( cepr01030102Form.getDynamicIdr() );
	                 double aggresiveIdr = LazyConverter.toDouble( cepr01030102Form.getAggresiveIdr() );
	                 double fixSimasIdr = LazyConverter.toDouble( cepr01030102Form.getFixSimasIdr() );
	                 double dynamicSimasIdr = LazyConverter.toDouble( cepr01030102Form.getDynamicSimasIdr() );
	                 double aggresiveSimasIdr = LazyConverter.toDouble( cepr01030102Form.getAggresiveSimasIdr() );
	                 double cashFundIdr = LazyConverter.toDouble( cepr01030102Form.getCashFundIdr() );
	                 double excellinkEquityIdr = LazyConverter.toDouble( cepr01030102Form.getExcellinkEquityIdr() );
	                 double total = fixIdr + dynamicIdr + aggresiveIdr + fixSimasIdr + dynamicSimasIdr + aggresiveSimasIdr + cashFundIdr + excellinkEquityIdr;
	                 if( total != 100 )
	                 {
	                     errors.rejectValue( Cepr01030102FormConst.DYNAMIC_IDR, "error.totalInvestmentPercentMust100", null, null );
	                 }        		 
        		 
                 } 
        	 }
        	 else if( (cepr01030102Form.getLeftPartOfBusinessCd() == 215 && cepr01030102Form.getRightPartOfBusinessCd() == 4) 
        			 || (cepr01030102Form.getLeftPartOfBusinessCd() == 224 && cepr01030102Form.getRightPartOfBusinessCd() == 3)){
        		 if( cepr01030102Form.getFixIdr() == null &&
                         cepr01030102Form.getDynamicIdr() == null &&
                         cepr01030102Form.getAggresiveIdr() == null &&
                         cepr01030102Form.getFixSimasIdr() == null &&
                         cepr01030102Form.getDynamicSimasIdr() == null &&
                         cepr01030102Form.getAggresiveSimasIdr() == null &&
                         cepr01030102Form.getCashFundIdr() == null  )
                 {
                     errors.rejectValue( Cepr01030102FormConst.FIX_IDR, "error.oneOfInvestmentChoice", null, null );
                 }else{
                 
	        		 double fixIdr = LazyConverter.toDouble( cepr01030102Form.getFixIdr() );
	                 double dynamicIdr = LazyConverter.toDouble( cepr01030102Form.getDynamicIdr() );
	                 double aggresiveIdr = LazyConverter.toDouble( cepr01030102Form.getAggresiveIdr() );
	                 double fixSimasIdr = LazyConverter.toDouble( cepr01030102Form.getFixSimasIdr() );
	                 double dynamicSimasIdr = LazyConverter.toDouble( cepr01030102Form.getDynamicSimasIdr() );
	                 double aggresiveSimasIdr = LazyConverter.toDouble( cepr01030102Form.getAggresiveSimasIdr() );
	                 double cashFundIdr = LazyConverter.toDouble( cepr01030102Form.getCashFundIdr() );
	                 double total = fixIdr + dynamicIdr + aggresiveIdr + fixSimasIdr + dynamicSimasIdr + aggresiveSimasIdr + cashFundIdr ;
	                 if( total != 100 )
	                 {
	                     errors.rejectValue( Cepr01030102FormConst.DYNAMIC_IDR, "error.totalInvestmentPercentMust100", null, null );
	                 }        		 
        		 
                 }
        	 }
        	 else if( cepr01030102Form.getFixIdr() == null &&
                    cepr01030102Form.getDynamicIdr() == null &&
                    cepr01030102Form.getAggresiveIdr() == null && 
                    cepr01030102Form.getCashFundIdr() == null && 
                    cepr01030102Form.getExcellinkEquityIdr() == null)
            {
                errors.rejectValue( Cepr01030102FormConst.FIX_IDR, "error.oneOfInvestmentChoice", null, null );
            }
            else
            {
                double fixIdr = LazyConverter.toDouble( cepr01030102Form.getFixIdr() );
                double dynamicIdr = LazyConverter.toDouble( cepr01030102Form.getDynamicIdr() );
                double aggresiveIdr = LazyConverter.toDouble( cepr01030102Form.getAggresiveIdr() );
                double cashFundIdr = LazyConverter.toDouble( cepr01030102Form.getCashFundIdr() );
                double excellinkEquityIdr = LazyConverter.toDouble( cepr01030102Form.getExcellinkEquityIdr() );
                double total = fixIdr + dynamicIdr + aggresiveIdr + cashFundIdr + excellinkEquityIdr;
                if( total != 100 )
                {
                    errors.rejectValue( Cepr01030102FormConst.DYNAMIC_IDR, "error.totalInvestmentPercentMust100", null, null );
                }
                else
                {
                    result = true;
                }
                if( cepr01030102Form.getLeftPartOfBusinessCd() == 120 && ( cepr01030102Form.getRightPartOfBusinessCd() == 19 || cepr01030102Form.getRightPartOfBusinessCd() == 20 || 
                		cepr01030102Form.getRightPartOfBusinessCd() == 21 ) ){
                	int temp = 0;
                	if( cepr01030102Form.getFixIdr().compareTo( BigDecimal.ZERO ) > 0){
                		temp = temp + 1;
                	}
                	if( cepr01030102Form.getDynamicIdr().compareTo( BigDecimal.ZERO ) > 0){
                		temp = temp + 1;
                	}
                	if( cepr01030102Form.getAggresiveIdr().compareTo( BigDecimal.ZERO ) > 0){
                		temp = temp + 1;
                	}
                	if( temp > 1 ){
                		errors.rejectValue( Cepr01030102FormConst.FIX_IDR, "error.investmentOnlyOne", null, null );
                	}
                }
            }
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getSecureUsd() == null &&
                    cepr01030102Form.getDynamicUsd() == null 
            		&& cepr01030102Form.getAggresiveUsd() == null) /**USD Fund BSIM Products**/
            {
                errors.rejectValue( Cepr01030102FormConst.SECURE_USD, "error.oneOfInvestmentChoice", null, null );
            }
            else
            {
                double secureUsd = LazyConverter.toDouble( cepr01030102Form.getSecureUsd() );
                double dynamicUsd = LazyConverter.toDouble( cepr01030102Form.getDynamicUsd() );
                double aggresiveUsd = LazyConverter.toDouble( cepr01030102Form.getAggresiveUsd() ); /**USD Fund BSIM Products**/
                double total = secureUsd + dynamicUsd + aggresiveUsd;
                if( total != 100 )
                {
                    errors.rejectValue( Cepr01030102FormConst.SECURE_USD, "error.totalInvestmentPercentMust100", null, null );
                }
                else
                {
                    result = true;
                }
                if( cepr01030102Form.getLeftPartOfBusinessCd() == 120 && ( cepr01030102Form.getRightPartOfBusinessCd() == 19 || cepr01030102Form.getRightPartOfBusinessCd() == 20 || 
                		cepr01030102Form.getRightPartOfBusinessCd() == 21 ) ){
                	int temp = 0;
                	if( cepr01030102Form.getSecureUsd().compareTo( BigDecimal.ZERO ) > 0){
                		temp = temp + 1;
                	}
                	if( cepr01030102Form.getDynamicUsd().compareTo( BigDecimal.ZERO ) > 0){
                		temp = temp + 1;
                	}
                	 if( temp > 1 ){
                 		errors.rejectValue( Cepr01030102FormConst.DYNAMIC_USD, "error.investmentOnlyOne", null, null );
                 	}
                }
               
            }
        }
        return result;
    }
    
}