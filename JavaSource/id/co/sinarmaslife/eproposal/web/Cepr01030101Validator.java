package id.co.sinarmaslife.eproposal.web;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000CommonValidator;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.standard.util.StringUtil;

public class Cepr01030101Validator extends Cepr01040000CommonValidator 
{
	protected final Log logger = LogFactory.getLog (getClass() );

	public Cepr01030101Validator( EproposalManager eproposalManager,Cepr00000000EditorUtil editorUtil, Object command, Errors errors) 
	{
		super( eproposalManager, editorUtil, command, errors);
	}

	public void validateCommon()
    {
        logger.info( "*-*-*-* Cepr01030101Validator.validateCommon" );
        
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;     
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();

        if( StringUtil.isEmpty( cepr01030101Form.getPolicyHolderName() ) )
        {
            errors.rejectValue( Cepr01030101FormConst.POLICY_HOLDER_NAME, "error.formEmpty", null, null );
        }
        else if( !StringUtil.isEmpty( cepr01030101Form.getPolicyHolderName() ) )
        {
        	try{
        		Double val = new Double( cepr01030101Form.getPolicyHolderName().toString());
        		errors.rejectValue( Cepr01030101FormConst.POLICY_HOLDER_NAME, "error.formMustCharacter", null, null );
        	}catch(Exception e){
        		logger.debug( "Policy Holder Name :" + cepr01030101Form.getPolicyHolderName() );
        	}
        }

        if( StringUtil.isEmpty( cepr01030101Form.getInsuredName() ) )
        {
            errors.rejectValue( Cepr01030101FormConst.INSURED_NAME, "error.formEmpty", null, null );
        }
        else if( !StringUtil.isEmpty( cepr01030101Form.getInsuredName() ) )
        {
        	try{
        		Double val = new Double( cepr01030101Form.getInsuredName().toString());
        		errors.rejectValue( Cepr01030101FormConst.INSURED_NAME, "error.formMustCharacter", null, null );
        	}catch(Exception e){
        		logger.debug( "Insured Name :" + cepr01030101Form.getInsuredName() );
        	}
        }

        if( cepr01030101Form.getPolicyHolderAge() == null )
        {
            errors.rejectValue( Cepr01030101FormConst.POLICY_HOLDER_AGE, "error.formEmpty", null, null );
            return;
        } 
        if( cepr01030101Form.getInsuredAge() == null )
        {
            errors.rejectValue( Cepr01030101FormConst.INSURED_AGE, "error.formEmpty", null, null );
            return;
        }

        if( !("208==>SIMAS KID".equals(cepr01030101Form.getAssurancePlanCd1())) && !("208==>VIP EDU PLAN".equals(cepr01030101Form.getAssurancePlanCd1())) 
        		&& !("208==>SMiLe KIDs".equals(cepr01030101Form.getAssurancePlanCd1()))  && !("208==>SMiLe KID INSURANCE".equals(cepr01030101Form.getAssurancePlanCd1()))
        		&& !("219==>SMART PLAN PROTECTION SYARIAH".equals(cepr01030101Form.getAssurancePlanCd1()))){
	        if( cepr01030101Form.getInsuredAge() == null )
	        {
	            errors.rejectValue( Cepr01030101FormConst.INSURED_AGE, "error.formEmpty", null, null );
	        }
	        
	        if( ("226==>SIMAS LEGACY PLAN".equals(cepr01030101Form.getAssurancePlanCd1()) )){
	        	 if( cepr01030101Form.getPolicyHolderAge() < 18 )
		 	     {
		 	        errors.rejectValue( Cepr01030101FormConst.POLICY_HOLDER_AGE, "error.policyHolderAgeMin", new Object[]{ "18" }, null );
		 	     }else if( cepr01030101Form.getPolicyHolderAge() > 85 ){
		 	    	errors.rejectValue( Cepr01030101FormConst.POLICY_HOLDER_AGE, "error.policyHolderAgeMax", new Object[]{ "85" }, null );
		 	     }
	        	 if( cepr01030101Form.getInsuredAge() < 18 )
		 	     {
		 	        errors.rejectValue( Cepr01030101FormConst.INSURED_AGE, "error.insuredAgeMin", new Object[]{ "18" }, null );
		 	     }else if( cepr01030101Form.getInsuredAge() > 60 ){
		 	    	errors.rejectValue( Cepr01030101FormConst.INSURED_AGE, "error.insuredAgeMax", new Object[]{ "60" }, null );
		 	     }
	        	 
	        }else{//default ALL produk
	        	if( cepr01030101Form.getPolicyHolderAge() < 17 )
	 	        {
	 	            errors.rejectValue( Cepr01030101FormConst.POLICY_HOLDER_AGE, "error.policyHolderAgeMin", new Object[]{ "17" }, null );
	 	        }	        	
	        }	        	       
	        
	        if(cepr01030101Form.getBabyFlag()!=CommonConst.CHECKED_FALSE){
	    	if( CommonConst.CHECKED_TRUE.equals(cepr01030101Form.getBabyFlag()) ) {
	    		errors.rejectValue( "cepr01030101Form.babyFlag", "error.canNotTakeThisRider", null, null );	    		
	    	}}
        }else if ("219==>SMART PLAN PROTECTION SYARIAH".equals(cepr01030101Form.getAssurancePlanCd1())){
        	if( cepr01030101Form.getPolicyHolderAge() < 20 )
	        {
	            errors.rejectValue( Cepr01030101FormConst.POLICY_HOLDER_AGE, "error.policyHolderAgeMin", new Object[]{ "20" }, null );
	        }
        }
        
        if(cepr01030101Form.getBabyFlag()!=CommonConst.CHECKED_FALSE){
     	    	if( CommonConst.CHECKED_TRUE.equals(cepr01030101Form.getBabyFlag()) ) {
     	    		if(cepr01030101Form.getInsuredSexCd()!=CommonConst.CHECKED_FALSE){
     	    			if("L".equalsIgnoreCase(cepr01030101Form.getInsuredSexCd().trim())){
     	    				errors.rejectValue( "cepr01030101Form.insuredSexCd", "error.insuredGenderMustBeWoman", null, null );	    
     	    			}     	    			
     	    		}     	    		
     	 }}        
       
        Integer produk = 0;
        if( !StringUtil.isEmpty( cepr01030101Form.getAssurancePlanCd1() ) || 
        		!StringUtil.isEmpty( cepr01030101Form.getAssurancePlanCd2() ) ||
        		!StringUtil.isEmpty( cepr01030101Form.getAssurancePlanCd3() ) ||
        		!StringUtil.isEmpty( cepr01030101Form.getAssurancePlanCd4() ) ){
        	produk = 1;
        }
        if( produk > 0 ){
        	if( ! StringUtil.isEmpty( cepr01030101Form.getAssurancePlanPackageCd1() )  ){
        		 errors.rejectValue( Cepr01030101FormConst.ASSURANCE_PLAN_CD_1, "error.selectOnlyOneBothProductOrPackage", null, null );
        		 errors.rejectValue( Cepr01030101FormConst.ASSURANCE_PLAN_PACKAGE_CD_1, "error.selectOnlyOneBothProductOrPackage", null, null );
        	}
        }
        
        if(    StringUtil.isEmpty( cepr01030101Form.getAssurancePlanCd1() )
                && StringUtil.isEmpty( cepr01030101Form.getAssurancePlanCd2() )
                && StringUtil.isEmpty( cepr01030101Form.getAssurancePlanCd3() )
                && StringUtil.isEmpty( cepr01030101Form.getAssurancePlanCd4() )
                && StringUtil.isEmpty( cepr01030101Form.getAssurancePlanPackageCd1() ))
        {
            errors.rejectValue( Cepr01030101FormConst.ASSURANCE_PLAN_CD_1, "error.selectAtLeastSelectOnePlan", null, null );
        }
        
        
        if( cepr01030101Form.getProposalUserCd().length()>6)
        {
          String ls_kdAgen = cepr01030101Form.getProposalUserCd().substring(0,3).trim();
          if( !("LUI".equalsIgnoreCase(ls_kdAgen)) && !("LUE".equalsIgnoreCase(ls_kdAgen)) && !("MAE".equalsIgnoreCase(ls_kdAgen)) && !("LRB".equalsIgnoreCase(ls_kdAgen)))  
          	{	  
            errors.rejectValue( Cepr01030101FormConst.PROPOSAL_USER_CD, "error.kodeAgenMax", new Object[]{ "6" }, null );
          	}
        }
       
       
       if( cepr01030101Form.getJn_bank() != null ){     
	        if( cepr01030101Form.getNamaAdminBC() == null || "".equals(cepr01030101Form.getNamaAdminBC()) ){
	        	errors.rejectValue( "cepr01030101Form.namaAdminBC", "error.agenReffBIICannotBeEmpty", null, null );
	        	
	        }
       }
       if (!cepr01030101Form.getAssurancePlanCd1().isEmpty() && cepr01030101Form.getAssurancePlanCd1()!=null){
    	 //--> IGA || PROJECT NEW COI || GENDER TERTANGGUNG WAJIB ISI
           HashMap mapGroup = eproposalManager.selectMstConfig(17, "PROPOSAL", "PROD_INCLUDE_GENDER");
           String[] aktif = mapGroup.get("NAME")!=null?mapGroup.get("NAME").toString().split(","):null;
           String[] prod = mapGroup.get("NAME2")!=null?mapGroup.get("NAME2").toString().split(","):null;
           
           if(prod != null){
        	   String codeProd = cepr01030101Form.getAssurancePlanCd1();
       		boolean prodIncludeGender = false;
       		for(String s: prod){
       			if(s.equals(codeProd.substring(0, 3)))
       				prodIncludeGender=true;
       		}
       		boolean aktifValidasi = false;
       		if(aktif != null){
       			for(String s: aktif){
       				if(s.equals("1"))
       					aktifValidasi=true;
       			}
       		}
             							
              if(aktifValidasi && prodIncludeGender){
           	   if(cepr01030101Form.getInsuredSexCd().equals("")){
          	 		errors.rejectValue( Cepr01030101FormConst.INSURED_SEX_CD, "error.insuredGenderCannotBeEmpty", null, null );
          	 	}
              } //<-- DONE   
           }   
       }
    }

	public void validateCreatingLetter() {
		logger.info("*-*-*-* Cepr01030101Validator.validateCreatingLetter");

		Cepr01030000HoldingForm cepr01030000HoldingForm = (Cepr01030000HoldingForm) command;
		Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm
				.getCepr01030101Form();

		if (StringUtil.isEmpty(cepr01030101Form.getPolicyHolderName())) {
			errors.rejectValue(Cepr01030101FormConst.POLICY_HOLDER_NAME,
					"error.formEmpty", null, null);
		}

		if (StringUtil.isEmpty(cepr01030101Form.getAssurancePlanCd1())
				&& StringUtil.isEmpty(cepr01030101Form.getAssurancePlanCd2())
				&& StringUtil.isEmpty(cepr01030101Form.getAssurancePlanCd3())
				&& StringUtil.isEmpty(cepr01030101Form.getAssurancePlanCd4())) {
			errors.rejectValue(Cepr01030101FormConst.ASSURANCE_PLAN_CD_1,
					"error.selectAtLeastSelectOnePlan", null, null);
		}

		if (StringUtil.isEmpty(cepr01030101Form.getProposalUser())) {
			errors.rejectValue(Cepr01030101FormConst.PROPOSAL_USER,
					"error.formEmpty", null, null);
		}
	}
}