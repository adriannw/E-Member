package id.co.sinarmaslife.eproposal.web;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000CommonValidator;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.setup.Product_setup;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_bisnis_rider;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_kurs;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_pay_mode;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_premium_comb;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_product_acquisition;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_product_calc;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_product_invest;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_product_setup;
import id.co.sinarmaslife.eproposal.model.setup.sub.RiderDisplay;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.standard.model.vo.AssurancePlanList;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.StringUtil;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.validation.Errors;

public class Cepr01030102Validator extends Cepr01040000CommonValidator
{

  public void set_product_setup(){
  	cepr01030102Form.setProduct_setup(new Product_setup());
  }

    public Cepr01030102Validator( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
    }

    public void validateCommon()
    {
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01040000CommonValidator commonValidator = new Cepr01040000CommonValidator( eproposalManager, editorUtil, command, errors );
        if( StringUtil.isEmpty( cepr01030102Form.getAssurancePlanCd() ) )
        {
            errors.rejectValue( Cepr01030102FormConst.ASSURANCE_PLAN_CD, "error.formEmpty", null, null );
        }
       
    }
    public void validate()
    {
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        CredentialsVO credentialsVO = cepr01030000HoldingForm.getCredentialsVO();
        Cepr01040000CommonValidator commonValidator = new Cepr01040000CommonValidator( eproposalManager, editorUtil, command, errors );
        
        if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_DANA_SEJAHTERA && cepr01030102Form.getPaymentModeCd() != null && cepr01030102Form.getPaymentModeCd() != Hardcode.PAY_MODE_CD_SEKALIGUS ){
        	if( cepr01030102Form.getPersonalAccidentFlagDisplay() != null && cepr01030102Form.getPersonalAccidentFlagDisplay().equals( CommonConst.DISPLAY_TRUE ) && cepr01030102Form.getPersonalAccidentFlag() != null && cepr01030102Form.getPersonalAccidentFlag().equals( CommonConst.CHECKED_TRUE ) ){
            	commonValidator.validateMaxAgeProduct( cepr01030101Form.getInsuredAge(), Cepr01030102FormConst.PERSONAL_ACCIDENT_FLAG, "PA", 55 );
        	}
        	if( cepr01030102Form.getWaiverPremiumDisabilityFlagDisplay() != null && cepr01030102Form.getWaiverPremiumDisabilityFlagDisplay().equals( CommonConst.DISPLAY_TRUE ) && cepr01030102Form.getWaiverPremiumDisabilityFlag() != null && cepr01030102Form.getWaiverPremiumDisabilityFlag().equals( CommonConst.CHECKED_TRUE ) ){
        		if( cepr01030102Form.getRightPartOfBusinessCd() == 6 || cepr01030102Form.getRightPartOfBusinessCd() == 11 || cepr01030102Form.getRightPartOfBusinessCd() == 16 ){
        			commonValidator.validateMaxAgeProduct( cepr01030101Form.getInsuredAge(),Cepr01030102FormConst.WAIVER_PREMIUM_DISABILITY_FLAG, "WPD", 50 );
            	}else if( cepr01030102Form.getRightPartOfBusinessCd() == 7 || cepr01030102Form.getRightPartOfBusinessCd() == 12 || cepr01030102Form.getRightPartOfBusinessCd() == 17 ){
        			commonValidator.validateMaxAgeProduct( cepr01030101Form.getInsuredAge(),Cepr01030102FormConst.WAIVER_PREMIUM_DISABILITY_FLAG, "WPD", 45 );
            	}else if( cepr01030102Form.getRightPartOfBusinessCd() == 8 || cepr01030102Form.getRightPartOfBusinessCd() == 13 || cepr01030102Form.getRightPartOfBusinessCd() == 18 ){
        			commonValidator.validateMaxAgeProduct( cepr01030101Form.getInsuredAge(),Cepr01030102FormConst.WAIVER_PREMIUM_DISABILITY_FLAG, "WPD", 40 );
            	}else if( cepr01030102Form.getRightPartOfBusinessCd() == 9 || cepr01030102Form.getRightPartOfBusinessCd() == 14 || cepr01030102Form.getRightPartOfBusinessCd() == 19 ){
        			commonValidator.validateMaxAgeProduct( cepr01030101Form.getInsuredAge(),Cepr01030102FormConst.WAIVER_PREMIUM_DISABILITY_FLAG, "WPD", 35 );
            	}
        	}
        	
        	if( cepr01030102Form.getTpdFlagDisplay() != null && cepr01030102Form.getTpdFlagDisplay().equals( CommonConst.DISPLAY_TRUE ) && cepr01030102Form.getTpdFlag() != null && cepr01030102Form.getTpdFlag().equals( CommonConst.CHECKED_TRUE ) ){
        		if( cepr01030102Form.getRightPartOfBusinessCd() == 6 || cepr01030102Form.getRightPartOfBusinessCd() == 11 || cepr01030102Form.getRightPartOfBusinessCd() == 16 ){
        			commonValidator.validateMaxAgeProduct( cepr01030101Form.getInsuredAge(),Cepr01030102FormConst.TPD_FLAG, "TPD", 55 );
            	}else if( cepr01030102Form.getRightPartOfBusinessCd() == 7 || cepr01030102Form.getRightPartOfBusinessCd() == 12 || cepr01030102Form.getRightPartOfBusinessCd() == 17 ){
        			commonValidator.validateMaxAgeProduct( cepr01030101Form.getInsuredAge(),Cepr01030102FormConst.TPD_FLAG, "TPD", 50 );
            	}else if( cepr01030102Form.getRightPartOfBusinessCd() == 8 || cepr01030102Form.getRightPartOfBusinessCd() == 13 || cepr01030102Form.getRightPartOfBusinessCd() == 18 ){
        			commonValidator.validateMaxAgeProduct( cepr01030101Form.getInsuredAge(),Cepr01030102FormConst.TPD_FLAG, "TPD", 45 );
            	}else if( cepr01030102Form.getRightPartOfBusinessCd() == 9 || cepr01030102Form.getRightPartOfBusinessCd() == 14 || cepr01030102Form.getRightPartOfBusinessCd() == 19 ){
        			commonValidator.validateMaxAgeProduct( cepr01030101Form.getInsuredAge(),Cepr01030102FormConst.TPD_FLAG, "TPD", 40 );
            	}
        	}
        	
        	if( cepr01030102Form.getCriticalIllnessFlagDisplay() != null && cepr01030102Form.getCriticalIllnessFlagDisplay().equals( CommonConst.DISPLAY_TRUE ) && cepr01030102Form.getCriticalIllnessFlag() != null && cepr01030102Form.getCriticalIllnessFlag().equals( CommonConst.CHECKED_TRUE ) ){
        		if( cepr01030102Form.getRightPartOfBusinessCd() == 6 || cepr01030102Form.getRightPartOfBusinessCd() == 11 || cepr01030102Form.getRightPartOfBusinessCd() == 16 ){
        			commonValidator.validateMaxAgeProduct( cepr01030101Form.getInsuredAge(),Cepr01030102FormConst.CRITICAL_ILLNESS_FLAG, "CI", 55 );
            	}else if( cepr01030102Form.getRightPartOfBusinessCd() == 7 || cepr01030102Form.getRightPartOfBusinessCd() == 12 || cepr01030102Form.getRightPartOfBusinessCd() == 17 ){
        			commonValidator.validateMaxAgeProduct( cepr01030101Form.getInsuredAge(),Cepr01030102FormConst.CRITICAL_ILLNESS_FLAG, "CI", 50 );
            	}else if( cepr01030102Form.getRightPartOfBusinessCd() == 8 || cepr01030102Form.getRightPartOfBusinessCd() == 13 || cepr01030102Form.getRightPartOfBusinessCd() == 18 ){
        			commonValidator.validateMaxAgeProduct( cepr01030101Form.getInsuredAge(),Cepr01030102FormConst.CRITICAL_ILLNESS_FLAG, "CI", 45 );
            	}else if( cepr01030102Form.getRightPartOfBusinessCd() == 9 || cepr01030102Form.getRightPartOfBusinessCd() == 14 || cepr01030102Form.getRightPartOfBusinessCd() == 19 ){
        			commonValidator.validateMaxAgeProduct( cepr01030101Form.getInsuredAge(),Cepr01030102FormConst.CRITICAL_ILLNESS_FLAG, "CI", 40 );
            	}
        	}
        	
        }
        
        
        
    	if( cepr01030102Form.getLeftPartOfBusinessCd() == 159 && cepr01030102Form.getSmileLadiesPackageCd() != null && cepr01030102Form.getSmileLadiesPackageCd() >1 ){
	    	if( cepr01030102Form.getPremiumCombinationCd() != null &&  !cepr01030102Form.getPremiumCombinationCd().equals( new BigDecimal("60") ) ){
	    		errors.rejectValue( Cepr01030102FormConst.PREMIUM_COMBINATION_CD, "error.combinationPremium", new Object[]{"60%"}, null );
	    	}
        }
    	
    	if( cepr01030102Form.getWaiverPremiumDisabilityFlagDisplay() != null && 
    			cepr01030102Form.getWaiverPremiumDisabilityFlagDisplay().equals( CommonConst.DISPLAY_TRUE ) && cepr01030102Form.getWaiverPremiumDisabilityFlag() != null && cepr01030102Form.getWaiverPremiumDisabilityFlag().equals( CommonConst.CHECKED_TRUE ) ){
    		if( cepr01030102Form.getPaymentModeCd() != Hardcode.PAY_MODE_CD_SEKALIGUS ){
    			commonValidator.validatePolicyHolderIsInsured( Cepr01030102FormConst.WAIVER_PREMIUM_DISABILITY_FLAG );
    		}
    	}
    	
    	if( cepr01030102Form.getLeftPartOfBusinessCd() != Hardcode.PRODUK_HIDUP_BAHAGIA){
    			if( cepr01030102Form.getPayorsClauseFlagDisplay() != null && cepr01030102Form.getPayorsClauseFlagDisplay().equals( CommonConst.DISPLAY_TRUE ) &&  cepr01030102Form.getPayorsClauseFlag() != null && cepr01030102Form.getPayorsClauseFlag().equals( CommonConst.CHECKED_TRUE ) ){
    				commonValidator.validateInsuredAge( Cepr01030102FormConst.PAYORS_CLAUSE_FLAG, 1, 15 );
    			}
    	}
    	
      	if( cepr01030102Form.getPersonalAccidentFlag() != null && cepr01030102Form.getPersonalAccidentFlag().equals( CommonConst.CHECKED_TRUE ) ){
      		if( cepr01030102Form.getLeftPartOfBusinessCd() == 163 )// dana sejahtera
      		{
      			if( cepr01030102Form.getTotalSumInsured().compareTo( new BigDecimal("1000000000") )  > 0 ){
      				errors.rejectValue( Cepr01030102FormConst.PERSONAL_ACCIDENT_FLAG, "error.maxRiderPa", null, null );
      			}
      		}
    	}
      	
    
    	if( cepr01030102Form.getLeftPartOfBusinessCd() == 120 ){ // excellent link
    	    if( cepr01030102Form.getSmileLadiesPackageCd() != null && cepr01030102Form.getSmileLadiesPackageCd() > 1 ){
            	if( cepr01030102Form.getSmileLadiesPackageCd().intValue() == 2 ){// Paket Diamond
            		commonValidator.validateInsuredIntervalAge( Cepr01030102FormConst.SMILE_LADIES_PACKAGE, 17, 60 );
            	}else if( cepr01030102Form.getSmileLadiesPackageCd().intValue() == 3 ){// Paket Ruby
            		commonValidator.validateInsuredIntervalAge( Cepr01030102FormConst.SMILE_LADIES_PACKAGE, 17, 60 );
            	}else if( cepr01030102Form.getSmileLadiesPackageCd().intValue() == 4 ){// Paket Pearl
            		commonValidator.validateInsuredIntervalAge( Cepr01030102FormConst.SMILE_LADIES_PACKAGE, 1, 60 );
            	}else if( cepr01030102Form.getSmileLadiesPackageCd().intValue() == 5 ){// Paket Fantastic
            		commonValidator.validateInsuredIntervalAge( Cepr01030102FormConst.SMILE_LADIES_PACKAGE, 17, 59 );
            	}else if( cepr01030102Form.getSmileLadiesPackageCd().intValue() == 6 ){ // Paket Excellent
            		commonValidator.validateInsuredIntervalAge( Cepr01030102FormConst.SMILE_LADIES_PACKAGE, 17, 60 );
            	}
            }
    	}else{
    	    if( cepr01030102Form.getSmileLadiesPackageCd() != null && cepr01030102Form.getSmileLadiesPackageCd() > 1 ){
            	if( cepr01030102Form.getSmileLadiesPackageCd().intValue() == 2 ){// Paket Diamond
            		commonValidator.validateInsuredIntervalAge( Cepr01030102FormConst.SMILE_LADIES_PACKAGE, 17, 60 );
            	}else if( cepr01030102Form.getSmileLadiesPackageCd().intValue() == 3 ){// Paket Ruby
            		commonValidator.validateInsuredIntervalAge( Cepr01030102FormConst.SMILE_LADIES_PACKAGE, 17, 60 );
            	}else if( cepr01030102Form.getSmileLadiesPackageCd().intValue() == 4 ){// Paket Pearl
            		commonValidator.validateInsuredIntervalAge( Cepr01030102FormConst.SMILE_LADIES_PACKAGE, 17, 60 );
            	}else if( cepr01030102Form.getSmileLadiesPackageCd().intValue() == 5 ){// Paket Fantastic
            		commonValidator.validateInsuredIntervalAge( Cepr01030102FormConst.SMILE_LADIES_PACKAGE, 17, 59 );
            	}else if( cepr01030102Form.getSmileLadiesPackageCd().intValue() == 6 ){ // Paket Excellent
            		commonValidator.validateInsuredIntervalAge( Cepr01030102FormConst.SMILE_LADIES_PACKAGE, 17, 60 );
            	}
            }
    	}
    
        if( cepr01030102Form.getSmilePensionPackageCd() != null && cepr01030102Form.getSmilePensionPackageCd() > 1 && cepr01030102Form.getLeftPartOfBusinessCd() != 129 ){
        		commonValidator.validateInsuredIntervalAge( Cepr01030102FormConst.SMILE_PENSION_PACKAGE, 17, 50 );
        }
        
        if( cepr01030102Form.getEducationPackageCd() != null && cepr01030102Form.getEducationPackageCd().intValue() > 1  ){
        	if( cepr01030102Form.getEducationPackageCd().equals(Hardcode.EDUCATION_PAKET_SIMPOL_BSM)){
        		commonValidator.validateInsuredAge( Cepr01030102FormConst.EDUCATION_PACKAGE, 0, 12 );
        	}else{
        		commonValidator.validateInsuredIntervalAge( Cepr01030102FormConst.EDUCATION_PACKAGE, 1, 17 );
        		commonValidator.validatePolicyHolderAge( Cepr01030102FormConst.EDUCATION_PACKAGE, 17, 65 );
        	}    		
    		if( cepr01030102Form.getPremiumFurloughYear().intValue() < 5 ){
//    			errors.rejectValue( Cepr01030102FormConst.EDUCATION_PACKAGE, "error.premiumFurloughYearMinimum", new Object[]{ "5" }, null );
    		}
        }
        
        if( cepr01030102Form.getProtectorPackageCd() != null && cepr01030102Form.getProtectorPackageCd().intValue() > 1  ){    	
    		commonValidator.validatePolicyHolderAge( Cepr01030102FormConst.PROTECTOR_PACKAGE, 20, 50);    		
        }
        
        if( cepr01030102Form.getAssurancePlanCd() != null ){
        	Integer flag_paket = 0;
        	for(AssurancePlanList temp : cepr01030102Form.getAssurancePlanList() ){
        		if( temp.getValue().equals( cepr01030102Form.getAssurancePlanCd() ) ){
        			if( temp.getFlag_paket() != null && temp.getFlag_paket().equals( 1 ) ){
        				flag_paket = 1;
        			}
        		}
        	}
        	if( flag_paket > 0){
        		if( cepr01030102Form.getLeftPartOfBusinessCd() == 159 ){// smile link(paket smile ladies exclusive)
        			if( cepr01030102Form.getSmileLadiesPackageCd() <= 1 ){
        				 errors.rejectValue( Cepr01030102FormConst.SMILE_LADIES_PACKAGE, "error.packageMustBeChoose", null, null );
        			}
        		}else if( cepr01030102Form.getLeftPartOfBusinessCd() == 116 ){// smile link 88(paket smile pension
        			if( cepr01030102Form.getSmilePensionPackageCd() <= 1 ){
       				 errors.rejectValue( Cepr01030102FormConst.SMILE_PENSION_PACKAGE, "error.packageMustBeChoose", null, null );
        			}
        		}else if( cepr01030102Form.getLeftPartOfBusinessCd() == 160 ){// smile link syariah
        			if( cepr01030102Form.getSmileLadiesPackageCd() <= 1 ){
          				 errors.rejectValue( Cepr01030102FormConst.SMILE_LADIES_PACKAGE, "error.packageMustBeChoose", null, null );
          			}
       			}else if( cepr01030102Form.getLeftPartOfBusinessCd() == 153 ){// smile link syariah
       				if( credentialsVO != null && credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_TIM_HAFRI ) ){
       					if( cepr01030102Form.getSecondPackageCd() <= 1 ){
            				 errors.rejectValue( Cepr01030102FormConst.SECOND_PACKAGE, "error.packageMustBeChoose", null, null );
            			}
       				}else{
       					if( cepr01030102Form.getSmilePensionPackageCd() <= 1 ){
            				 errors.rejectValue( Cepr01030102FormConst.SMILE_PENSION_PACKAGE, "error.packageMustBeChoose", null, null );
            			}
       				}
      			}
        	}
        }
        
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 120 || cepr01030102Form.getLeftPartOfBusinessCd() == 213 || cepr01030102Form.getLeftPartOfBusinessCd() == 216 ){ // simpol dengan pilihan investasi (Excellent/Simas-FUND)
        	 if( CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiFixListDisplay()) && ( cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo( new BigDecimal( "0" ) ) > 0 ) ){
        		 if(cepr01030102Form.getLjiFixCd().equals("")){
        			  errors.rejectValue( Cepr01030102FormConst.FIX_IDR, "error.formInvestedEmpty", null, null );
        		 }
        	 }
        	 if( CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiDynamicListDisplay()) && ( cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0 ) ){
        		 if(cepr01030102Form.getLjiDynamicCd().equals("")){
        			  errors.rejectValue( Cepr01030102FormConst.DYNAMIC_IDR, "error.formInvestedEmpty", null, null );
        		 }
        	 }
        	 if( CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiAggresiveListDisplay()) && ( cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0 ) ){
        		 if(cepr01030102Form.getLjiAggresiveCd().equals("")){
        			  errors.rejectValue( Cepr01030102FormConst.AGRESSIVE_IDR, "error.formInvestedEmpty", null, null );
        		 }
        	 }
        	
        	 if( CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiSecureUsdListDisplay()) && ( cepr01030102Form.getSecureUsd() != null && cepr01030102Form.getSecureUsd().compareTo( new BigDecimal( "0" ) ) > 0 ) ){
        		 if(cepr01030102Form.getLjiSecureUsdCd().equals("")){
        			  errors.rejectValue( Cepr01030102FormConst.SECURE_USD, "error.formInvestedEmpty", null, null );
        		 }
        	 }
        	 if( CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiDynamicUsdListDisplay()) && ( cepr01030102Form.getDynamicUsd() != null && cepr01030102Form.getDynamicUsd().compareTo( new BigDecimal( "0" ) ) > 0 ) ){
        		 if(cepr01030102Form.getLjiDynamicUsdCd().equals("")){
        			  errors.rejectValue( Cepr01030102FormConst.DYNAMIC_USD, "error.formInvestedEmpty", null, null );
        		 }
        	 }
        	 /**USD Fund BSIM Products**/
        	 if( CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiAggresiveUsdListDisplay()) && ( cepr01030102Form.getAggresiveUsd() != null && cepr01030102Form.getAggresiveUsd().compareTo( new BigDecimal( "0" ) ) > 0 ) ){
        		 if(cepr01030102Form.getLjiAggresiveUsdCd().equals("")){
        			  errors.rejectValue( Cepr01030102FormConst.AGGRESIVE_USD, "error.formInvestedEmpty", null, null );
        		 }
        	 }
        }
        
        if( (cepr01030102Form.getLeftPartOfBusinessCd() == 134 && (cepr01030102Form.getRightPartOfBusinessCd() ==5 || cepr01030102Form.getRightPartOfBusinessCd() ==10 || cepr01030102Form.getRightPartOfBusinessCd()==12
        	 || cepr01030102Form.getRightPartOfBusinessCd() == 7 || cepr01030102Form.getRightPartOfBusinessCd() ==8 ) )
        	 || (cepr01030102Form.getLeftPartOfBusinessCd() == 220 && (cepr01030102Form.getRightPartOfBusinessCd() == 4 || cepr01030102Form.getRightPartOfBusinessCd() <= 2) )
        	 || (cepr01030102Form.getLeftPartOfBusinessCd() == 190 && (cepr01030102Form.getRightPartOfBusinessCd() == 7 || cepr01030102Form.getRightPartOfBusinessCd() == 8 || cepr01030102Form.getRightPartOfBusinessCd() == 3 || cepr01030102Form.getRightPartOfBusinessCd() == 4))
        	 || (cepr01030102Form.getLeftPartOfBusinessCd() == 116 && cepr01030102Form.getRightPartOfBusinessCd() > 2 )
        	 || (cepr01030102Form.getLeftPartOfBusinessCd() == 217 && cepr01030102Form.getRightPartOfBusinessCd() == 1 )
        	 || (cepr01030102Form.getLeftPartOfBusinessCd() == 215 && cepr01030102Form.getRightPartOfBusinessCd() == 4) 
        	 || (cepr01030102Form.getLeftPartOfBusinessCd() == 224 && cepr01030102Form.getRightPartOfBusinessCd() == 3) ){ // simpol dengan pilihan investasi (Excellent/Simas-FUND)
       	 if( CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiFixListDisplay()) && ( cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo( new BigDecimal( "0" ) ) > 0 ) ){
       		 if(cepr01030102Form.getLjiFixCd().equals("")){
       			  errors.rejectValue( Cepr01030102FormConst.FIX_IDR, "error.formInvestedEmpty", null, null );
       		 }
       	 }
       	 if( cepr01030102Form.getFixSimasIdr() != null && cepr01030102Form.getFixSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 ) {
       		 if(cepr01030102Form.getLjiFixSimasCd().equals("")){
       			  errors.rejectValue( "cepr01030102Form.fixSimasIdr", "error.formInvestedEmpty", null, null );
       		 }
       	 }
       	 if( CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiDynamicListDisplay()) && ( cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0 ) ){
       		 if(cepr01030102Form.getLjiDynamicCd().equals("")){
       			  errors.rejectValue( Cepr01030102FormConst.DYNAMIC_IDR, "error.formInvestedEmpty", null, null );
       		 }
       	 }
       	 if( cepr01030102Form.getDynamicSimasIdr() != null && cepr01030102Form.getDynamicSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 ) {
       		 if(cepr01030102Form.getLjiDynamicSimasCd().equals("")){
       			  errors.rejectValue( "cepr01030102Form.dynamicSimasIdr", "error.formInvestedEmpty", null, null );
       		 }
       	 }
       	 if( CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiAggresiveListDisplay()) && ( cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0 ) ){
       		 if(cepr01030102Form.getLjiAggresiveCd().equals("")){
       			  errors.rejectValue( Cepr01030102FormConst.AGRESSIVE_IDR, "error.formInvestedEmpty", null, null );
       		 }
       	 }
    	 if( cepr01030102Form.getAggresiveSimasIdr() != null && cepr01030102Form.getAggresiveSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 ) {
       		 if(cepr01030102Form.getLjiAggresiveSimasCd().equals("")){
       			  errors.rejectValue( "cepr01030102Form.aggresiveSimasIdr", "error.formInvestedEmpty", null, null );
       		 }
       	 }
       	 
    	 if( CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiSecureUsdListDisplay()) && ( cepr01030102Form.getSecureUsd() != null && cepr01030102Form.getSecureUsd().compareTo( new BigDecimal( "0" ) ) > 0 ) ){
    		 if(cepr01030102Form.getLjiSecureUsdCd().equals("")){
    			  errors.rejectValue( Cepr01030102FormConst.SECURE_USD, "error.formInvestedEmpty", null, null );
    		 }
    	 }
    	 if( CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiDynamicUsdListDisplay()) && ( cepr01030102Form.getDynamicUsd() != null && cepr01030102Form.getDynamicUsd().compareTo( new BigDecimal( "0" ) ) > 0 ) ){
    		 if(cepr01030102Form.getLjiDynamicUsdCd().equals("")){
    			  errors.rejectValue( Cepr01030102FormConst.DYNAMIC_USD, "error.formInvestedEmpty", null, null );
    		 }
    	 }
    	 
    	 /**USD Fund BSIM Products**/
    	 if( CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiAggresiveUsdListDisplay()) && ( cepr01030102Form.getAggresiveUsd() != null && cepr01030102Form.getAggresiveUsd().compareTo( new BigDecimal( "0" ) ) > 0 ) ){
    		 if(cepr01030102Form.getLjiAggresiveUsdCd().equals("")){
    			  errors.rejectValue( Cepr01030102FormConst.AGGRESIVE_USD, "error.formInvestedEmpty", null, null );
    		 }
    	 }
    	 if( CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiCashFundDisplay()) &&  cepr01030102Form.getCashFundIdr() != null ){
    		 if(cepr01030102Form.getLjiCashFundCd().equals("") && cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) > 0  ){
    			  errors.rejectValue( Cepr01030102FormConst.CASHFUND_IDR, "error.formInvestedEmpty", null, null );
    		 }
    	 }
    	 
    	 if( CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiExcellinkEquityDisplay()) &&  cepr01030102Form.getExcellinkEquityIdr() != null ){
    		 if(cepr01030102Form.getLjiExcellinkEquityCd().equals("") && cepr01030102Form.getExcellinkEquityIdr().compareTo( new BigDecimal( "0" ) ) > 0  ){
    			  errors.rejectValue( Cepr01030102FormConst.EXCELLINKEQUITY_IDR, "error.formInvestedEmpty", null, null );
    		 }
    	 }
    	 
       }
        
        
    }
    
    public void validateEmptyProduct()
    {
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        
        if(cepr01030102Form.getLeftPartOfBusinessCd() == 0 && cepr01030102Form.getRightPartOfBusinessCd() == 0)
        {
            errors.rejectValue(Cepr01030102FormConst.ASSURANCE_PLAN_CD, "error.emptyProductId", null, null);
        }
    }    
}