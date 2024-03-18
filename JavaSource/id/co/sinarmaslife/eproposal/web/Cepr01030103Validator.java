package id.co.sinarmaslife.eproposal.web;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01030103Validator
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Oct 9, 2007 10:02:14 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 * 1.1			11-12-2008					Andy				tambahan filter TPD & CI
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.math.BigDecimal;

import org.springframework.validation.Errors;

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000CommonValidator;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.product.product01040207.Cepr01040207Mapper;
import id.co.sinarmaslife.eproposal.product.product01040211.Cepr01040211Mapper;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.MappingUtil;

public class Cepr01030103Validator extends Cepr01040000CommonValidator
{
    public Cepr01030103Validator( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
    }

    public void validate()
    {
        logger.info( "*-*-*-* Cepr01030103Validator.validateCurrentPage" );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01030103Form cepr01030103Form = cepr01030000HoldingForm.getCepr01030103Form();
        Cepr01030111Form cepr01030111Form = cepr01030000HoldingForm.getCepr01030111Form();// peserta ladies medical as charge
        Cepr01030112Form cepr01030112Form = cepr01030000HoldingForm.getCepr01030112Form();// peserta ladies medical inner limit
        CredentialsVO credentialsVO = cepr01030000HoldingForm.getCredentialsVO();
        Cepr01040000CommonValidator commonValidator = new Cepr01040000CommonValidator( eproposalManager, editorUtil, command, errors );

        int errHCP = 0;
        int tdp = 0;
        int ci = 0;
        int hcp = 0;
        int ekasehat = 0;
        int ladies = 0;
        int payor = 0;
        int waiver  = 0;
        boolean onlyRiderPa = false;
        boolean onlyRiderBaby =  false;
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 153 ){
            if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040207Mapper.X4 && cepr01030102Form.getPremium() != null ){
	            double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
	        	if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_BULANAN && premium <= 250000  && cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_IDR_CD )){
	        		onlyRiderPa = true;
	        	}
            }
        }
        
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 159 || cepr01030102Form.getLeftPartOfBusinessCd() == 116 || cepr01030102Form.getLeftPartOfBusinessCd() == 140 
        		|| cepr01030102Form.getLeftPartOfBusinessCd() == 141 || cepr01030102Form.getLeftPartOfBusinessCd() == 190 || cepr01030102Form.getLeftPartOfBusinessCd() == 160
        		|| cepr01030102Form.getLeftPartOfBusinessCd() == 200 //Perbedaan Antara Ilustrasi Online dengan Offline di Premi 250rb
        		|| cepr01030102Form.getLeftPartOfBusinessCd() == 118
        		|| cepr01030102Form.getLeftPartOfBusinessCd() == 153
        		|| cepr01030102Form.getLeftPartOfBusinessCd() == 217
        		|| cepr01030102Form.getLeftPartOfBusinessCd() == 218){ 
        	if( credentialsVO.getGroupId() == null || ( credentialsVO.getGroupId() != null && !credentialsVO.getGroupId().equals(Hardcode.GROUP_MNC) ) )
        	{
        		double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
	        	if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_BULANAN && premium <= 250000 && cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_IDR_CD ) ){
	        		onlyRiderPa = true;
	        	}	        	
	        	if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_BULANAN && premium >= 15 && premium <= 25 && cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD) ){
	        		onlyRiderPa = true;
	        	}
	        	
        	}
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getBabyFlag() ) ){
        	onlyRiderBaby = true;
        }
        
        if( onlyRiderPa == true || onlyRiderBaby == true){
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPaFlag() ) )
 	        {
        		if( onlyRiderBaby== true) errors.rejectValue( Cepr01030103FormConst.PA_FLAG, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );
 	        }
        	
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverTpdCiFlag() ) )
 	        {
        		if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.WAIVER_TPD_CI_FLAG, "error.thisProductJustRider", new Object[]{ "PA" }, null );
        		else errors.rejectValue( Cepr01030103FormConst.WAIVER_TPD_CI_FLAG, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );        		
 	        }
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorSpouseTpdDeathFlag() ) )
 	        {
        		if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.PAYOR_SPOUSE_TPD_DEATH_FLAG, "error.thisProductJustRider", new Object[]{ "PA" }, null );
        		else errors.rejectValue( Cepr01030103FormConst.PAYOR_SPOUSE_TPD_DEATH_FLAG, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );
 	        }
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpLadiesFlag() ) )
 	        {
        		if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.thisProductJustRider", new Object[]{ "PA" }, null );
        		else errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );
             	
 	        }
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTermRiderFlag() ) )
 	        {
        		if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.TERM_RIDER_FLAG, "error.thisProductJustRider", new Object[]{ "PA" }, null );
        		else errors.rejectValue( Cepr01030103FormConst.TERM_RIDER_FLAG, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );
             	
 	        }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorTpdCiDeathFlag() ) )
	        {
            	if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.PAYOR_TPD_CI_DEATH_FLAG, "error.thisProductJustRider", new Object[]{ "PA" }, null );
            	else errors.rejectValue( Cepr01030103FormConst.PAYOR_TPD_CI_DEATH_FLAG, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );
            	
	        }
	        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Tpd10CiDeathFlag() ) )
	        {
	        	if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.PAYOR_TPD_10_CI_FLAG, "error.thisProductJustRider", new Object[]{ "PA" }, null );
	        	else errors.rejectValue( Cepr01030103FormConst.PAYOR_TPD_10_CI_FLAG, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );
	        	
	        }
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTpdFlag() ) )
            {
        		if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.TPD_FLAG, "error.thisProductJustRider", new Object[]{ "PA" }, null );
        		else  errors.rejectValue( Cepr01030103FormConst.TPD_FLAG, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );        		
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiver5Tpd10CiFlag() ) )
            {            	
            	if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.WAIVER_5_TPD_10_CI_FLAG, "error.thisProductJustRider", new Object[]{ "PA" }, null );
            	else errors.rejectValue( Cepr01030103FormConst.WAIVER_5_TPD_10_CI_FLAG, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );
            }
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getCiFlag() ) )
            {
        		if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.CI_FLAG, "error.thisProductJustRider", new Object[]{ "PA" }, null );
        		else errors.rejectValue( Cepr01030103FormConst.CI_FLAG, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFlag() ) )
            {
            	if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.thisProductJustRider", new Object[]{ "PA" }, null );
            	else  errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFamilyFlag() ) )
            {            	
            	if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.thisProductJustRider", new Object[]{ "PA" }, null );
            	else errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpProviderFlag() ) )
            {           
            	if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.thisProductJustRider", new Object[]{ "PA" }, null );
            	else errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );
            }
            
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) )
            {
            	if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.thisProductJustRider", new Object[]{ "PA" }, null );
            	else errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );            	
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatInnerLimitFlag() ) )
            {
            	if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_INNER_LIMIT_FLAG, "error.thisProductJustRider", new Object[]{ "PA" }, null );
            	else errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_INNER_LIMIT_FLAG, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesMedExpenseFlag() ) )
            {
            	if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.LADIES_MED_EXPENSE, "error.thisProductJustRider", new Object[]{ "PA" }, null );
            	else errors.rejectValue( Cepr01030103FormConst.LADIES_MED_EXPENSE, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );
            }
            
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesMedExpenseInnerLimitFlag() ) )
            {
            	if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.LADIES_MED_EXPENSE_INNER_LIMIT, "error.thisProductJustRider", new Object[]{ "PA" }, null );
            	else errors.rejectValue( Cepr01030103FormConst.LADIES_MED_EXPENSE_INNER_LIMIT, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesInsFlag() ) )
            {
            	if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.LADIES_INS_FLAG, "error.thisProductJustRider", new Object[]{ "PA" }, null );
            	else errors.rejectValue( Cepr01030103FormConst.LADIES_INS_FLAG, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getScholarshipFlag() ) )
            {
            	if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.SCHOLARSHIP_FLAG, "error.thisProductJustRider", new Object[]{ "PA" }, null );
            	else errors.rejectValue( Cepr01030103FormConst.SCHOLARSHIP_FLAG, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );
            }            
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getBabyFlag() ) )
 	        {
        		if( onlyRiderPa== true) errors.rejectValue( Cepr01030103FormConst.BABY_FLAG, "error.thisProductJustRider", new Object[]{ "PA" }, null );
 	        }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEarlyStageCi99Flag() ) )
            {
        		if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.EARLY_STAGE_CI99_FLAG, "error.thisProductJustRider", new Object[]{ "PA" }, null );
        		else  errors.rejectValue( Cepr01030103FormConst.EARLY_STAGE_CI99_FLAG, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );        		
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatExtraFlag() ) )//Perbedaan Antara Ilustrasi Online dengan Offline di Premi 200rb
            {
            	if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_EXTRA_FLAG, "error.thisProductJustRider", new Object[]{ "PA" }, null );
            	else errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_EXTRA_FLAG, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag() ) )//Perbedaan Antara Ilustrasi Online dengan Offline di Premi 200rb
            {
            	if( onlyRiderPa == true) errors.rejectValue( Cepr01030103FormConst.MEDICAL_PLUS_FLAG, "error.thisProductJustRider", new Object[]{ "PA" }, null );
            	else errors.rejectValue( Cepr01030103FormConst.MEDICAL_PLUS_FLAG, "error.thisProductJustRider", new Object[]{ "SMiLe Baby" }, null );
            }
        }
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPaFlag() ) )
        {
            if( cepr01030103Form.getPaRiskCd() == null )
            {
                errors.rejectValue( Cepr01030103FormConst.PA_FLAG, "error.formRiskCannotBeEmpty", null, null );
            }
            else if( cepr01030103Form.getPaClassCd() == null )
            {
                errors.rejectValue( Cepr01030103FormConst.PA_FLAG, "error.formClassCannotBeEmpty", null, null );
            }
            else if( cepr01030103Form.getPaUnitQtyCd() == null )
            {
                errors.rejectValue( Cepr01030103FormConst.PA_FLAG, "error.formUnitAmountCannotBeEmpty", null, null );
            }
            commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.PA_FLAG, 1, 59 );
                        
            if( cepr01030102Form.getLeftPartOfBusinessCd() == 190){
            	if( cepr01030101Form.getInsuredAge() <= 16 && cepr01030103Form.getPaClassCd() != 2)
                {
                    errors.rejectValue( Cepr01030103FormConst.PA_FLAG, "error.PaClassSelected", new Object[]{ "2" }, null );
                }            	
         
            	//  SMiLe Link 99/VIP FAMILY PLAN --> JIKA TERTANGGUNG BERUMUR KURANG DR 6 BULAN TIDAK DAPAT AMBIL RIDER PA
               		String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
//            		String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
            		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung.substring(2, 4))<6 && LazyConverter.toInt(umurTertanggung.substring(0, 2))==0)
            		{
            			errors.rejectValue( Cepr01030103FormConst.PA_FLAG, "error.insuredAgeMinMonth", new Object[]{ Integer.toString( 6 )  }, null );
            		}
            }
                        
        }
		if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) )
        {
            if( cepr01030103Form.getEkaSehatCd() == null )
            {
                errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.formEmpty", null, null );
            }
            /*
            if( cepr01030101Form.getInsuredAge() < 1 ){
            	errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.insuredAgeMin", new Object[]{ "1" }, null );
            }else if( cepr01030101Form.getInsuredAge() > 60 ){
            	errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.insuredAgeMax", new Object[]{ "60" }, null );
            }
            */
            String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
//    		String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
    		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<15 )
    		{
    			errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.insuredAgeMinDay", new Object[]{ Integer.toString( 15 )  }, null );
    		}else if( cepr01030101Form.getInsuredAge() > 65 )
	        {
	            errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.insuredAgeMax", new Object[]{ Integer.toString( 60 ) }, null );
	        }
            
        }
		if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatInnerLimitFlag() ) )
        {
			if( cepr01030103Form.getEkaSehatInnerLimitCd() == null )
            {
                errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_INNER_LIMIT_FLAG, "error.formEmpty", null, null );
            }
			/*
            if( cepr01030101Form.getInsuredAge() < 1 ){
            	errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_INNER_LIMIT_FLAG, "error.insuredAgeMin", new Object[]{ "1" }, null );
            }else if( cepr01030101Form.getInsuredAge() > 60 ){
            	errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_INNER_LIMIT_FLAG, "error.insuredAgeMax", new Object[]{ "60" }, null );
            }
            */
            String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay()); 
//    		String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
    		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<15 )
    		{
    			errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_INNER_LIMIT_FLAG, "error.insuredAgeMinDay", new Object[]{ Integer.toString( 15 )  }, null );
    		}else if( cepr01030101Form.getInsuredAge() > 65 )
	        {
	            errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_INNER_LIMIT_FLAG, "error.insuredAgeMax", new Object[]{ Integer.toString( 65 ) }, null );
	        }
            
        }
		if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatExtraFlag() ) )
        {
            if( cepr01030103Form.getEkaSehatExtraCd() == null )
            {
                errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_EXTRA_FLAG, "error.formEmpty", null, null );
            }
            /*
            if( cepr01030101Form.getInsuredAge() < 1 ){
            	errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.insuredAgeMin", new Object[]{ "1" }, null );
            }else if( cepr01030101Form.getInsuredAge() > 60 ){
            	errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.insuredAgeMax", new Object[]{ "60" }, null );
            }
            */
            String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
//    		String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
    		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<15 )
    		{
    			errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_EXTRA_FLAG, "error.insuredAgeMinDay", new Object[]{ Integer.toString( 15 )  }, null );
    		}else if( cepr01030101Form.getInsuredAge() > 64 )
	        {
	            errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_EXTRA_FLAG, "error.insuredAgeMax", new Object[]{ Integer.toString( 64 ) }, null );
	        }
            
        }
		
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFlag() ) )
        {
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFamilyFlag() ) )
            {
                errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpOnlyTakenOne", null, null );
            }
            else if( cepr01030103Form.getHcpCd() == null )
            {
                errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.formEmpty", null, null );
            }
            if( cepr01030102Form.getLeftPartOfBusinessCd() == 134 ){// priority link
            	if( cepr01030103Form.getHcpCd() == 5 || cepr01030103Form.getHcpCd() == 10 || cepr01030103Form.getHcpCd() == 15 || cepr01030103Form.getHcpCd() == 20 ){
            		
            	}else{
            		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.hcpselected", new Object[]{"R500, R1000, D50, D100"}, null );
            	}
            }else if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_100 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_100_SYARIAH
            		|| cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99_SYARIAH 
            		|| cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_88 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_EKA_LINK_80_PLUS_SYARIAH
            		){            	
            	if( cepr01030103Form.getHcpCd() != 5 && cepr01030103Form.getHcpCd() != 10 ){
            		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.hcpselected", new Object[]{"R500, R1000"}, null );
            	}            	
            }
            
            String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
//    		String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
    		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<15 )
    		{
    			errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.insuredAgeMinDay", new Object[]{ Integer.toString( 15 )  }, null );
    		}else if( cepr01030101Form.getInsuredAge() > 64 )
	        {
	            errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.insuredAgeMax", new Object[]{ Integer.toString( 64 ) }, null );
	        }
            
    		if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_100 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_100_SYARIAH
            	|| cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99_SYARIAH 
            	|| cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_88 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_EKA_LINK_80_PLUS_SYARIAH
            	){    		
    			if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
    			{
    				if( (cepr01030103Form.getHcpCd() == 5 || cepr01030103Form.getHcpCd() == 10)  ){
    				if( cepr01030103Form.getHcpCd() == 5 ){
    					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (250000000))  < 0  ) {
    	    				errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.batasUp", new Object[]{ new Integer (250000000),new Integer (499999999)  }, null );      	    		
    	    				errHCP++;
    					}
    					else if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (500000000))  >= 0  ) {
    	    				errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.batasUp", new Object[]{ new Integer (250000000),new Integer (499999999)  }, null );      	    		
    	    				errHCP++;
    					}
    				}
    				else if( cepr01030103Form.getHcpCd() == 10){
    					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (500000000))  < 0  ) {
    	    				errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.batasUp", new Object[]{ new Integer (500000000), new String("Keatas")  }, null );      	    		
    	    				errHCP++;
    					}    					
    				}
    				if( errHCP==0 && (cepr01030103Form.getHcpCd() == 5 || cepr01030103Form.getHcpCd() == 10) && cepr01030103Form.getEkaSehatFlag()== CommonConst.CHECKED_FALSE  ){
	    				errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
	    				errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
	    			}	
    				
    				
    				}
    						
    				if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) ){ 
	    				if( cepr01030103Form.getEkaSehatCd() < 13 ){
	    					errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC minimal Plan-M1000!") }, null );
	    				}    				
	    			}    	
    				
    			}else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) ){
    				/*
    				if( cepr01030103Form.getHcpCd() == 5 ){
    					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (25000))  < 0  ) {
    	    				errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.batasUp", new Object[]{ new Integer (25000),new Integer (49999)  }, null );      	    		
    	    	    	}
    					else if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000))  >= 0  ) {
    	    				errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.batasUp", new Object[]{ new Integer (25000),new Integer (49999)  }, null );      	    		
    	    	    	}
    				}if( cepr01030103Form.getHcpCd() == 10){
    					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000))  < 0  ) {
    	    				errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.batasUp", new Object[]{ new Integer (50000), new String("Keatas")  }, null );      	    		
    	    	    	}    					
    				}*/
    				
    				if( (cepr01030103Form.getHcpCd() == 5 || cepr01030103Form.getHcpCd() == 10)  ){
        				if( cepr01030103Form.getHcpCd() == 5 ){
        					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (25000))  < 0  ) {
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.batasUp", new Object[]{ new Integer (25000),new Integer (49999)  }, null );      	    		
        	    				errHCP++;
        					}
        					else if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000))  >= 0  ) {
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.batasUp", new Object[]{ new Integer (25000),new Integer (49999)  }, null );      	    		
        	    				errHCP++;
        					}
        				}
        				else if( cepr01030103Form.getHcpCd() == 10){
        					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000))  < 0  ) {
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.batasUp", new Object[]{ new Integer (50000), new String("Keatas")  }, null );      	    		
        	    				errHCP++;
        					}    					
        				}
        				if( errHCP==0 && (cepr01030103Form.getHcpCd() == 5 || cepr01030103Form.getHcpCd() == 10) && cepr01030103Form.getEkaSehatFlag()== CommonConst.CHECKED_FALSE  ){
    	    				errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
    	    				errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
    	    			}else if( errHCP== 0 && (cepr01030103Form.getHcpCd() == 5 || cepr01030103Form.getHcpCd() == 10 ) && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) ){ 
    	    				if( cepr01030103Form.getEkaSehatCd() < 13 ){
    	    					errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC minimal Plan-M1000!") }, null );
    	    				}    				
    	    			}    		
        				
        				
        				}
    				
    				
    			}
        
    			
    			
            }else{            	
            
    	    if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
    	    	if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000000) ) == 0)  && cepr01030103Form.getHcpCd() > 1) {
    	    		 errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (50) ), "R-100" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (100000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (100000000) ) == 0)  && cepr01030103Form.getHcpCd() > 2) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (100) ), "R-200" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (150000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (150000000) ) == 0)  && cepr01030103Form.getHcpCd() > 3) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (150) ), "R-300" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (200000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (200000000) ) == 0)  && cepr01030103Form.getHcpCd() > 4) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (200) ), "R-400" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (250000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (250000000) ) == 0)  && cepr01030103Form.getHcpCd() > 5) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (250) ), "R-500" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (300000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (300000000) ) == 0)  && cepr01030103Form.getHcpCd() > 6) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (300) ), "R-600" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (350000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (350000000) ) == 0)  && cepr01030103Form.getHcpCd() > 7) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (350) ), "R-700" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (400000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (400000000) ) == 0)  && cepr01030103Form.getHcpCd() > 8) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (400) ), "R-800" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (450000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (450000000) ) == 0)  && cepr01030103Form.getHcpCd() > 9) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (450) ), "R-900" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (500000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (500000000) ) == 0)  && cepr01030103Form.getHcpCd() > 10) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (500) ), "R-1000" }, null );    	    		
    	    	} 
            }else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (5000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (5000) ) == 0)  && cepr01030103Form.getHcpCd() > 11) {
            		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (5000) ), "D-10" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (10000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (10000) ) == 0)  && cepr01030103Form.getHcpCd() > 12) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (10000) ), "D-20" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (15000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (15000) ) == 0)  && cepr01030103Form.getHcpCd() > 13) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (15000) ), "D-30" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (20000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (20000) ) == 0)  && cepr01030103Form.getHcpCd() > 14) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (20000) ), "D-40" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (25000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (25000) ) == 0)  && cepr01030103Form.getHcpCd() > 15) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (25000) ), "D-50" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (30000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (30000) ) == 0)  && cepr01030103Form.getHcpCd() > 16) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (30000) ), "D-60" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (35000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (35000) ) == 0)  && cepr01030103Form.getHcpCd() > 17) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (35000) ), "D-70" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (40000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (40000) ) == 0)  && cepr01030103Form.getHcpCd() > 18) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (40000) ), "D-80" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (45000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (45000) ) == 0)  && cepr01030103Form.getHcpCd() > 19) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (45000) ), "D-90" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000) ) == 0)  && cepr01030103Form.getHcpCd() > 20) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (50000) ), "D-100" }, null );    	    		
	   	    	}             	
            
          }    		
        }
        }	
    		
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFamilyFlag() ) )
        {
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFlag() ) )
            {
                errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpOnlyTakenOne", null, null );
            }
            else if( cepr01030103Form.getHcpFamilyCd() == null )
            {
                errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.formEmpty", null, null );
            }
            
            if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_MAXI_SAVE ){
            	  commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.HCP_FAMILY_FLAG, 1, 57 );
            }else{
              // commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.HCP_FAMILY_FLAG, 1, 60 );            	  
            	String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
//        		String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
         		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<15 )
         		{
         			errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.insuredAgeMinDay", new Object[]{ Integer.toString( 15 )  }, null );
         		}else if( cepr01030101Form.getInsuredAge() > 64 )
     	        {
     	            errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.insuredAgeMax", new Object[]{ Integer.toString( 64 ) }, null );
     	        }            	  
            }
            
            if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_100 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_100_SYARIAH
            	|| cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99_SYARIAH 
            	|| cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_88 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_EKA_LINK_80_PLUS_SYARIAH
            ){            	
            	if( cepr01030103Form.getHcpFamilyCd() != 5 && cepr01030103Form.getHcpFamilyCd() != 10 ){
            		errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.hcpselected", new Object[]{"R500, R1000"}, null );
            	}            	
            }
            
            if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_100 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_100_SYARIAH
                	|| cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99_SYARIAH 
                	|| cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_88 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_EKA_LINK_80_PLUS_SYARIAH
                	){    		
        			if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        			{/*
        				if( cepr01030103Form.getHcpFamilyCd() == 5 ){
        					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (250000000))  < 0  ) {
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.batasUp", new Object[]{ new Integer (250000000),new Integer (499999999)  }, null );      	    		
        	    	    	}
        					else if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (500000000))  >= 0  ) {
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.batasUp", new Object[]{ new Integer (250000000),new Integer (499999999)  }, null );      	    		
        	    	    	}
        				}if( cepr01030103Form.getHcpFamilyCd() == 10){
        					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (500000000))  < 0  ) {
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.batasUp", new Object[]{ new Integer (500000000), new String("Keatas")  }, null );      	    		
        	    	    	}    					
        				}
        				*/
        				
        				if( (cepr01030103Form.getHcpFamilyCd() == 5 || cepr01030103Form.getHcpFamilyCd() == 10)  ){
            				if( cepr01030103Form.getHcpFamilyCd() == 5 ){
            					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (250000000))  < 0  ) {
            	    				errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.batasUp", new Object[]{ new Integer (250000000),new Integer (499999999)  }, null );      	    		
            	    				errHCP++;
            					}
            					else if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (500000000))  >= 0  ) {
            	    				errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.batasUp", new Object[]{ new Integer (250000000),new Integer (499999999)  }, null );      	    		
            	    				errHCP++;
            					}
            				}
            				else if( cepr01030103Form.getHcpFamilyCd() == 10){
            					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (500000000))  < 0  ) {
            	    				errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.batasUp", new Object[]{ new Integer (500000000), new String("Keatas")  }, null );      	    		
            	    				errHCP++;
            					}    					
            				}
            				if( errHCP==0 && (cepr01030103Form.getHcpFamilyCd() == 5 || cepr01030103Form.getHcpFamilyCd() == 10) && cepr01030103Form.getEkaSehatFlag()== CommonConst.CHECKED_FALSE  ){
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
        	    				errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
        	    			}else if( errHCP== 0 && (cepr01030103Form.getHcpFamilyCd() == 5 || cepr01030103Form.getHcpFamilyCd() == 10 ) && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) ){ 
        	    				if( cepr01030103Form.getEkaSehatCd() < 13 ){
        	    					errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC minimal Plan-M1000!") }, null );
        	    				}    				
        	    			}    		
            				
            				
            				}
        				        				
        				
        			}else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) ){
        				/*
        				if( cepr01030103Form.getHcpFamilyCd() == 5 ){
        					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (25000))  < 0  ) {
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.batasUp", new Object[]{ new Integer (25000),new Integer (49999)  }, null );      	    		
        	    	    	}
        					else if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000))  >= 0  ) {
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.batasUp", new Object[]{ new Integer (25000),new Integer (49999)  }, null );      	    		
        	    	    	}
        				}if( cepr01030103Form.getHcpFamilyCd() == 10){
        					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000))  < 0  ) {
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.batasUp", new Object[]{ new Integer (50000), new String("Keatas")  }, null );      	    		
        	    	    	}    					
        				}  */
        				        				
        				
        				if( (cepr01030103Form.getHcpFamilyCd() == 5 || cepr01030103Form.getHcpFamilyCd() == 10)  ){
            				if( cepr01030103Form.getHcpFamilyCd() == 5 ){
            					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (25000))  < 0  ) {
            	    				errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.batasUp", new Object[]{ new Integer (25000),new Integer (49999)  }, null );      	    		
            	    				errHCP++;
            					}
            					else if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000))  >= 0  ) {
            	    				errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.batasUp", new Object[]{ new Integer (25000),new Integer (49999)  }, null );      	    		
            	    				errHCP++;
            					}
            				}
            				else if( cepr01030103Form.getHcpFamilyCd() == 10){
            					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000))  < 0  ) {
            	    				errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.batasUp", new Object[]{ new Integer (50000), new String("Keatas")  }, null );      	    		
            	    				errHCP++;
            					}    					
            				}
            				if( errHCP==0 && (cepr01030103Form.getHcpFamilyCd() == 5 || cepr01030103Form.getHcpFamilyCd() == 10) && cepr01030103Form.getEkaSehatFlag()== CommonConst.CHECKED_FALSE  ){
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
        	    				errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
        	    			}else if( errHCP== 0 && (cepr01030103Form.getHcpFamilyCd() == 5 || cepr01030103Form.getHcpCd() == 10 ) && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) ){ 
        	    				if( cepr01030103Form.getEkaSehatCd() < 13 ){
        	    					errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC minimal Plan-M1000!") }, null );
        	    				}    				
        	    			}    		
            				
            				
            				}
        				
        			}
        			/*
        			if( cepr01030103Form.getEkaSehatFlag()== CommonConst.CHECKED_FALSE ){
        				errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
        				errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
        			}else if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) ){ 
        				if( cepr01030103Form.getEkaSehatCd() < 13 ){
        					errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC minimal Plan-M1000!") }, null );
        				}    				
        			}*/
        			
            }else{ 
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000000) ) == 0)  && cepr01030103Form.getHcpFamilyCd() > 1) {
    	    		 errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (50) ), "R-100" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (100000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (100000000) ) == 0) && cepr01030103Form.getHcpFamilyCd() > 2) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (100) ), "R-200" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (150000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (150000000) ) == 0) && cepr01030103Form.getHcpFamilyCd() > 3) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (150) ), "R-300" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (200000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (200000000) ) == 0) && cepr01030103Form.getHcpFamilyCd() > 4) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (200) ), "R-400" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (250000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (250000000) ) == 0) && cepr01030103Form.getHcpFamilyCd() > 5) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (250) ), "R-500" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (300000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (300000000) ) == 0) && cepr01030103Form.getHcpFamilyCd() > 6) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (300) ), "R-600" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (350000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (350000000) ) == 0) && cepr01030103Form.getHcpFamilyCd() > 7) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (350) ), "R-700" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (400000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (400000000) ) == 0) && cepr01030103Form.getHcpFamilyCd() > 8) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (400) ), "R-800" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (450000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (450000000) ) == 0) && cepr01030103Form.getHcpFamilyCd() > 9) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (450) ), "R-900" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (500000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (500000000) ) == 0)  && cepr01030103Form.getHcpFamilyCd() > 10) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (500) ), "R-1000" }, null );    	    		
    	    	} 
            }else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (5000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (5000) ) == 0)  && cepr01030103Form.getHcpFamilyCd() > 11) {
            		errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (5000) ), "D-10" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (10000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (10000) ) == 0)  && cepr01030103Form.getHcpFamilyCd() > 12) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (10000) ), "D-20" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (15000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (15000) ) == 0)  && cepr01030103Form.getHcpFamilyCd() > 13) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (15000) ), "D-30" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (20000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (20000) ) == 0)  && cepr01030103Form.getHcpFamilyCd() > 14) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (20000) ), "D-40" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (25000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (25000) ) == 0)  && cepr01030103Form.getHcpFamilyCd() > 15) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (25000) ), "D-50" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (30000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (30000) ) == 0)  && cepr01030103Form.getHcpFamilyCd() > 16) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (30000) ), "D-60" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (35000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (35000) ) == 0)  && cepr01030103Form.getHcpFamilyCd() > 17) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (35000) ), "D-70" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (40000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (40000) ) == 0)  && cepr01030103Form.getHcpFamilyCd() > 18) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (40000) ), "D-80" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (45000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (45000) ) == 0)  && cepr01030103Form.getHcpFamilyCd() > 19) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (45000) ), "D-90" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000) ) == 0)  && cepr01030103Form.getHcpFamilyCd() > 20) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (50000) ), "D-100" }, null );    	    		
	   	    	}             	
            }
          }
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpProviderFlag() ) ){
        	if( cepr01030103Form.getHcpProviderCd() == null )
            {
                errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.formEmpty", null, null );
            }
        	
            if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_CERDAS_SYARIAH ){
           	  	commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.HCP_PROVIDER_FLAG, 1, 60 );
            }else if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_CERDAS ){            	
            		String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
//            		String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
            		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<15 )
            		{
            			errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.insuredAgeMinDay", new Object[]{ Integer.toString( 15 )  }, null );
            		}else if( cepr01030101Form.getInsuredAge() > 60 )
        	        {
        	            errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.insuredAgeMax", new Object[]{ Integer.toString( 60 ) }, null );
        	        }
            }else{
            	String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
//        		String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
        		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<15 )
        		{
        			errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.insuredAgeMinDay", new Object[]{ Integer.toString( 15 )  }, null );
        		}else if( cepr01030101Form.getInsuredAge() > 64 )
    	        {
    	            errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.insuredAgeMax", new Object[]{ Integer.toString( 64 ) }, null );
    	        }
            }
            
            if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_100 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_100_SYARIAH
                	|| cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99_SYARIAH 
                	|| cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_88 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_EKA_LINK_80_PLUS_SYARIAH
                ){            	
                	if( cepr01030103Form.getHcpProviderCd() != 5 && cepr01030103Form.getHcpProviderCd() != 10 ){
                		errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.hcpselected", new Object[]{"R500, R1000"}, null );
                	}            	
                }
         
            if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_100 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_100_SYARIAH
                	|| cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99_SYARIAH 
                	|| cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_88 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_EKA_LINK_80_PLUS_SYARIAH
                	){    		
        			if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        			{/*
        				if( cepr01030103Form.getHcpProviderCd() == 5 ){
        					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (250000000))  < 0  ) {
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.batasUp", new Object[]{ new Integer (250000000),new Integer (499999999)  }, null );      	    		
        	    	    	}
        					else if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (500000000))  >= 0  ) {
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.batasUp", new Object[]{ new Integer (250000000),new Integer (499999999)  }, null );      	    		
        	    	    	}
        				}
        				if( cepr01030103Form.getHcpFamilyCd() == 10){
        					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (500000000))  < 0  ) {
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.batasUp", new Object[]{ new Integer (500000000), new String("Keatas")  }, null );      	    		
        	    	    	}    					
        				}*/
        				
        				if( (cepr01030103Form.getHcpProviderCd() == 5 || cepr01030103Form.getHcpProviderCd() == 10)  ){
            				if( cepr01030103Form.getHcpProviderCd() == 5 ){
            					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (250000000))  < 0  ) {
            	    				errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.batasUp", new Object[]{ new Integer (250000000),new Integer (499999999)  }, null );      	    		
            	    				errHCP++;
            					}
            					else if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (500000000))  >= 0  ) {
            	    				errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.batasUp", new Object[]{ new Integer (250000000),new Integer (499999999)  }, null );      	    		
            	    				errHCP++;
            					}
            				}
            				else if( cepr01030103Form.getHcpProviderCd() == 10){
            					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (500000000))  < 0  ) {
            	    				errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.batasUp", new Object[]{ new Integer (500000000), new String("Keatas")  }, null );      	    		
            	    				errHCP++;
            					}    					
            				}
            				if( errHCP==0 && (cepr01030103Form.getHcpProviderCd() == 5 || cepr01030103Form.getHcpProviderCd() == 10) && cepr01030103Form.getEkaSehatFlag()== CommonConst.CHECKED_FALSE  ){
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
        	    				errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
        	    			}else if( errHCP== 0 && (cepr01030103Form.getHcpProviderCd() == 5 || cepr01030103Form.getHcpProviderCd() == 10 ) && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) ){ 
        	    				if( cepr01030103Form.getEkaSehatCd() < 13 ){
        	    					errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC minimal Plan-M1000!") }, null );
        	    				}    				
        	    			}    		
            				
            				
            				}        				
        				
        			}else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) ){
        			/*	if( cepr01030103Form.getHcpProviderCd() == 5 ){
        					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (25000))  < 0  ) {
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.batasUp", new Object[]{ new Integer (25000),new Integer (49999)  }, null );      	    		
        	    	    	}
        					else if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000))  >= 0  ) {
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.batasUp", new Object[]{ new Integer (25000),new Integer (49999)  }, null );      	    		
        	    	    	}
        				}if( cepr01030103Form.getHcpProviderCd() == 10){
        					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000))  < 0  ) {
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.batasUp", new Object[]{ new Integer (50000), new String("Keatas")  }, null );      	    		
        	    	    	}    					
        				}  */
        				
        				if( (cepr01030103Form.getHcpProviderCd() == 5 || cepr01030103Form.getHcpProviderCd() == 10)  ){
            				if( cepr01030103Form.getHcpProviderCd() == 5 ){
            					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (25000))  < 0  ) {
            	    				errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.batasUp", new Object[]{ new Integer (25000),new Integer (49999)  }, null );      	    		
            	    				errHCP++;
            					}
            					else if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000))  >= 0  ) {
            	    				errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.batasUp", new Object[]{ new Integer (25000),new Integer (49999)  }, null );      	    		
            	    				errHCP++;
            					}
            				}
            				else if( cepr01030103Form.getHcpProviderCd() == 10){
            					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000))  < 0  ) {
            	    				errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.batasUp", new Object[]{ new Integer (50000), new String("Keatas")  }, null );      	    		
            	    				errHCP++;
            					}    					
            				}
            				if( errHCP==0 && (cepr01030103Form.getHcpProviderCd() == 5 || cepr01030103Form.getHcpProviderCd() == 10) && cepr01030103Form.getEkaSehatFlag()== CommonConst.CHECKED_FALSE  ){
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
        	    				errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
        	    			}else if( errHCP== 0 && (cepr01030103Form.getHcpProviderCd() == 5 || cepr01030103Form.getHcpProviderCd() == 10 ) && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) ){ 
        	    				if( cepr01030103Form.getEkaSehatCd() < 13 ){
        	    					errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC minimal Plan-M1000!") }, null );
        	    				}    				
        	    			}    		
            				
            				
            				}
        				
        				
        				
        			}        			
        			/*
        			if( cepr01030103Form.getEkaSehatFlag()== CommonConst.CHECKED_FALSE ){
        				errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
        				errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
        			}else if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) ){ 
        				if( cepr01030103Form.getEkaSehatCd() < 13 ){
        					errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC minimal Plan-M1000!") }, null );
        				}    				
        			} */
        			
            }else{ 
            
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000000) ) == 0)  && cepr01030103Form.getHcpProviderCd() > 1) {
    	    		 errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (50) ), "R-100" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (100000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (100000000) ) == 0) && cepr01030103Form.getHcpProviderCd() > 2) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (100) ), "R-200" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (150000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (150000000) ) == 0) && cepr01030103Form.getHcpProviderCd() > 3) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (150) ), "R-300" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (200000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (200000000) ) == 0) && cepr01030103Form.getHcpProviderCd() > 4) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (200) ), "R-400" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (250000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (250000000) ) == 0) && cepr01030103Form.getHcpProviderCd() > 5) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (250) ), "R-500" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (300000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (300000000) ) == 0) && cepr01030103Form.getHcpProviderCd() > 6) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (300) ), "R-600" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (350000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (350000000) ) == 0) && cepr01030103Form.getHcpProviderCd() > 7) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (350) ), "R-700" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (400000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (400000000) ) == 0) && cepr01030103Form.getHcpProviderCd() > 8) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (400) ), "R-800" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (450000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (450000000) ) == 0) && cepr01030103Form.getHcpProviderCd() > 9) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (450) ), "R-900" }, null );    	    		
    	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (500000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (500000000) ) == 0) && cepr01030103Form.getHcpProviderCd() > 10) {
    	    		errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (500) ), "R-1000" }, null );    	    		
    	    	}            	    	    	
            	
            }else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (5000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (5000) ) == 0)  && cepr01030103Form.getHcpProviderCd() > 11) {
            		errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (5000) ), "D-10" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (10000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (10000) ) == 0)  && cepr01030103Form.getHcpProviderCd() > 12) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (10000) ), "D-20" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (15000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (15000) ) == 0)  && cepr01030103Form.getHcpProviderCd() > 13) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (15000) ), "D-30" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (20000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (20000) ) == 0)  && cepr01030103Form.getHcpProviderCd() > 14) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (20000) ), "D-40" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (25000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (25000) ) == 0)  && cepr01030103Form.getHcpProviderCd() > 15) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (25000) ), "D-50" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (30000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (30000) ) == 0)  && cepr01030103Form.getHcpProviderCd() > 16) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (30000) ), "D-60" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (35000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (35000) ) == 0)  && cepr01030103Form.getHcpProviderCd() > 17) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (35000) ), "D-70" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (40000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (40000) ) == 0)  && cepr01030103Form.getHcpProviderCd() > 18) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (40000) ), "D-80" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (45000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (45000) ) == 0)  && cepr01030103Form.getHcpProviderCd() > 19) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (45000) ), "D-90" }, null );    	    		
	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000) ) == 0)  && cepr01030103Form.getHcpProviderCd() > 20) {
	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (50000) ), "D-100" }, null );    	    		
	   	    	}             	     	
            }
            }
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpLadiesFlag()) ){
        	if( cepr01030103Form.getHcpLadiesCd()== null )
            {
                errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.formEmpty", null, null );
            }
        	
        	//commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.HCP_LADIES_FLAG, 1, 64 );
        	String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
    		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<15 )
    		{
    			errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.insuredAgeMinDay", new Object[]{ Integer.toString( 15 )  }, null );
    		}else if( cepr01030101Form.getInsuredAge() > 64 )
	        {
	            errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.insuredAgeMax", new Object[]{ Integer.toString( 64 ) }, null );
	        }
    		
    		 if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_100 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_100_SYARIAH
                 	|| cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99_SYARIAH 
                 	|| cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_88 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_EKA_LINK_80_PLUS_SYARIAH
                 ){            	
                 	if( cepr01030103Form.getHcpLadiesCd() != 5 && cepr01030103Form.getHcpLadiesCd() != 10 ){
                 		errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.hcpselected", new Object[]{"R500, R1000"}, null );
                 	}            	
               }
    		 
    		  if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_100 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_PRO_100_SYARIAH
                  	|| cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99_SYARIAH 
                  	|| cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_88 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_EKA_LINK_80_PLUS_SYARIAH
                  	){    		
          			if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
          			{
          				if( (cepr01030103Form.getHcpLadiesCd() == 5 || cepr01030103Form.getHcpLadiesCd() == 10)  ){
            				if( cepr01030103Form.getHcpLadiesCd() == 5 ){
            					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (250000000))  < 0  ) {
            	    				errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.batasUp", new Object[]{ new Integer (250000000),new Integer (499999999)  }, null );      	    		
            	    				errHCP++;
            					}
            					else if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (500000000))  >= 0  ) {
            	    				errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.batasUp", new Object[]{ new Integer (250000000),new Integer (499999999)  }, null );      	    		
            	    				errHCP++;
            					}
            				}
            				else if( cepr01030103Form.getHcpLadiesCd() == 10){
            					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (500000000))  < 0  ) {
            	    				errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.batasUp", new Object[]{ new Integer (500000000), new String("Keatas")  }, null );      	    		
            	    				errHCP++;
            					}    					
            				}
            				if( errHCP==0 && (cepr01030103Form.getHcpLadiesCd() == 5 || cepr01030103Form.getHcpLadiesCd() == 10) && cepr01030103Form.getEkaSehatFlag()== CommonConst.CHECKED_FALSE  ){
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
        	    				errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
        	    			}else if( errHCP== 0 && (cepr01030103Form.getHcpLadiesCd() == 5 || cepr01030103Form.getHcpLadiesCd() == 10 ) && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) ){ 
        	    				if( cepr01030103Form.getEkaSehatCd() < 13 ){
        	    					errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC minimal Plan-M1000!") }, null );
        	    				}    				
        	    			}    		
            				
            				
            				}
          			}else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) ){
          				/*if( cepr01030103Form.getHcpLadiesCd() == 5 ){
          					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (25000))  < 0  ) {
          	    				errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.batasUp", new Object[]{ new Integer (25000),new Integer (49999)  }, null );      	    		
          	    	    	}
          					else if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000))  >= 0  ) {
          	    				errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.batasUp", new Object[]{ new Integer (25000),new Integer (49999)  }, null );      	    		
          	    	    	}
          				}if( cepr01030103Form.getHcpLadiesCd() == 10){
          					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000))  < 0  ) {
          	    				errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.batasUp", new Object[]{ new Integer (50000), new String("Keatas")  }, null );      	    		
          	    	    	}    					
          				}  */
          				
          				if( (cepr01030103Form.getHcpLadiesCd() == 5 || cepr01030103Form.getHcpLadiesCd() == 10)  ){
            				if( cepr01030103Form.getHcpLadiesCd() == 5 ){
            					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (25000))  < 0  ) {
            	    				errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.batasUp", new Object[]{ new Integer (25000),new Integer (49999)  }, null );      	    		
            	    				errHCP++;
            					}
            					else if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000))  >= 0  ) {
            	    				errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.batasUp", new Object[]{ new Integer (25000),new Integer (49999)  }, null );      	    		
            	    				errHCP++;
            					}
            				}
            				else if( cepr01030103Form.getHcpLadiesCd() == 10){
            					if( cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000))  < 0  ) {
            	    				errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.batasUp", new Object[]{ new Integer (50000), new String("Keatas")  }, null );      	    		
            	    				errHCP++;
            					}    					
            				}
            				if( errHCP==0 && (cepr01030103Form.getHcpLadiesCd() == 5 || cepr01030103Form.getHcpLadiesCd() == 10) && cepr01030103Form.getEkaSehatFlag()== CommonConst.CHECKED_FALSE  ){
        	    				errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
        	    				errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
        	    			}else if( errHCP== 0 && (cepr01030103Form.getHcpLadiesCd() == 5 || cepr01030103Form.getHcpLadiesCd() == 10 ) && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) ){ 
        	    				if( cepr01030103Form.getEkaSehatCd() < 13 ){
        	    					errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC minimal Plan-M1000!") }, null );
        	    				}    				
        	    			}    		
            				
            				
            				}
          				
          				
          			}
          			
          			/*
          			if( CommonConst.CHECKED_FALSE.equals( cepr01030103Form.getEkaSehatFlag() )){
        				errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
        				errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC harus diambil!") }, null );
        			}else if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) ){ 
        				if( cepr01030103Form.getEkaSehatCd() < 13 ){
        					errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.generalMsg", new Object[]{ new String("HCP, Rider SMiLe Medical AC minimal Plan-M1000!") }, null );
        				}    				
        			} */
          			
              }else{
    		
    		  if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
              {
              	if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000000) ) == 0)  && cepr01030103Form.getHcpLadiesCd() > 1) {
      	    		 errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (50) ), "R-100" }, null );    	    		
      	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (100000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (100000000) ) == 0) && cepr01030103Form.getHcpLadiesCd() > 2) {
      	    		errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (100) ), "R-200" }, null );    	    		
      	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (150000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (150000000) ) == 0) && cepr01030103Form.getHcpLadiesCd() > 3) {
      	    		errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (150) ), "R-300" }, null );    	    		
      	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (200000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (200000000) ) == 0) && cepr01030103Form.getHcpLadiesCd() > 4) {
      	    		errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (200) ), "R-400" }, null );    	    		
      	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (250000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (250000000) ) == 0) && cepr01030103Form.getHcpLadiesCd() > 5) {
      	    		errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (250) ), "R-500" }, null );    	    		
      	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (300000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (300000000) ) == 0) && cepr01030103Form.getHcpLadiesCd() > 6) {
      	    		errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (300) ), "R-600" }, null );    	    		
      	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (350000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (350000000) ) == 0) && cepr01030103Form.getHcpLadiesCd() > 7) {
      	    		errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (350) ), "R-700" }, null );    	    		
      	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (400000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (400000000) ) == 0) && cepr01030103Form.getHcpLadiesCd() > 8) {
      	    		errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (400) ), "R-800" }, null );    	    		
      	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (450000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (450000000) ) == 0) && cepr01030103Form.getHcpLadiesCd() > 9) {
      	    		errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (450) ), "R-900" }, null );    	    		
      	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (500000000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (500000000) ) == 0) && cepr01030103Form.getHcpLadiesCd() > 10) {
      	    		errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.riderHcpOnlyCanChoose", new Object[]{ Integer.toString(new Integer (500) ), "R-1000" }, null );    	    		
      	    	} 
              }else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
              {
              	if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (5000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (5000) ) == 0)  && cepr01030103Form.getHcpLadiesCd() > 11) {
              		errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (5000) ), "D-10" }, null );    	    		
  	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (10000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (10000) ) == 0)  && cepr01030103Form.getHcpLadiesCd() > 12) {
  	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (10000) ), "D-20" }, null );    	    		
  	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (15000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (15000) ) == 0)  && cepr01030103Form.getHcpLadiesCd() > 13) {
  	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (15000) ), "D-30" }, null );    	    		
  	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (20000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (20000) ) == 0)  && cepr01030103Form.getHcpLadiesCd() > 14) {
  	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (20000) ), "D-40" }, null );    	    		
  	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (25000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (25000) ) == 0)  && cepr01030103Form.getHcpLadiesCd() > 15) {
  	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (25000) ), "D-50" }, null );    	    		
  	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (30000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (30000) ) == 0)  && cepr01030103Form.getHcpLadiesCd() > 16) {
  	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (30000) ), "D-60" }, null );    	    		
  	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (35000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (35000) ) == 0)  && cepr01030103Form.getHcpLadiesCd() > 17) {
  	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (35000) ), "D-70" }, null );    	    		
  	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (40000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (40000) ) == 0)  && cepr01030103Form.getHcpLadiesCd() > 18) {
  	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (40000) ), "D-80" }, null );    	    		
  	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (45000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (45000) ) == 0)  && cepr01030103Form.getHcpLadiesCd() > 19) {
  	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (45000) ), "D-90" }, null );    	    		
  	   	    	} else if( (cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000) ) < 0 || cepr01030102Form.getTotalSumInsured().compareTo(new BigDecimal (50000) ) == 0)  && cepr01030103Form.getHcpLadiesCd() > 20) {
  	   	    		errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.riderHcpUSDOnlyCanChoose", new Object[]{ Integer.toString(new Integer (50000) ), "D-100" }, null );    	    		
  	   	    	}             	     	
              }
    		  
            } 
    		  if(cepr01030101Form.getInsuredSexCd() != null){
    			  if(cepr01030101Form.getInsuredSexCd().equals("L")){
    				  errors.rejectValue( Cepr01030103FormConst.HCP_LADIES_FLAG, "error.insuredGenderMustBeWoman", null, null );
    			  }
    		  }
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesMedExpenseFlag() ) ){
        	if( cepr01030103Form.getLadiesMedExpenseCd()== null )
            {
                errors.rejectValue( Cepr01030103FormConst.LADIES_MED_EXPENSE, "error.formEmpty", null, null );
            }
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesMedExpenseInnerLimitFlag() ) ){
        	if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd()== null )
            {
                errors.rejectValue( Cepr01030103FormConst.LADIES_MED_EXPENSE_INNER_LIMIT, "error.formEmpty", null, null );
            }
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorTpdDeathFlag() ) )
        {
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorCiDeathFlag() ) )
            {
                errors.rejectValue( Cepr01030103FormConst.PAYOR_TPD_DEATH_FLAG, "error.rider25DeathOnlyTakenOne", null, null );
            }
            else if( commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.PAYOR_TPD_DEATH_FLAG, 1, 17 ) )
            {
                commonValidator.validatePolicyHolderIntervalAge( Cepr01030103FormConst.PAYOR_TPD_DEATH_FLAG, 20, 64 );
            }
        } 
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Ci10CiDeathFlag() ) )
        {
        	 commonValidator.validatePolicyHolderIsNotInsured( Cepr01030103FormConst.PAYOR_CI_10_FLAG );
        	 if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_CERDAS ){
             	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X22 ){
             		commonValidator.validatePolicyHolderIntervalAge( Cepr01030103FormConst.PAYOR_CI_10_FLAG, 20, 55 );
             	}else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X23 ){
             		commonValidator.validatePolicyHolderIntervalAge( Cepr01030103FormConst.PAYOR_CI_10_FLAG, 20, 50 );
             	}
             }
        }
        
    	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Tpd10TpdDeathFlag() ) )
    	{
//           commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.PAYOR_TPD_10_FLAG, 1, 24 );
            commonValidator.validatePolicyHolderIsNotInsured( Cepr01030103FormConst.PAYOR_TPD_10_FLAG );
            if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_CERDAS ){
            	commonValidator.validatePolicyHolderIntervalAge( Cepr01030103FormConst.PAYOR_TPD_10_FLAG, 17, 50 );
            }
    	}
    	
    	 if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Tpd10CiDeathFlag() ) )
         {
    		 if( cepr01030102Form.getPaymentModeCd().intValue() == Hardcode.PAY_MODE_CD_SEKALIGUS ) {
    			 errors.rejectValue( Cepr01030103FormConst.PAYOR_TPD_10_CI_FLAG, "error.riderCanNotTakenIfPaymentOnce", null, null );
    		 }
    		 if( cepr01030102Form.getLeftPartOfBusinessCd() == 120 || cepr01030102Form.getLeftPartOfBusinessCd() == 202){// cerdas && simpol syariah
    			 if( !( cepr01030102Form.getTermOfPayment() == 5 || cepr01030102Form.getTermOfPayment() == 10 ) ){
        			 errors.rejectValue( Cepr01030103FormConst.PAYOR_TPD_10_CI_FLAG, "error.only5and10", null, null );
        		 }
    			 commonValidator.validatePolicyHolderIntervalAge( Cepr01030103FormConst.PAYOR_TPD_10_CI_FLAG, 17, 50 );
    		 }else{
    			 if( !( cepr01030102Form.getPremiumFurloughYear() == 5 || cepr01030102Form.getPremiumFurloughYear() == 10 ) ){
        			 errors.rejectValue( Cepr01030103FormConst.PAYOR_TPD_10_CI_FLAG, "error.only5and10", null, null );
        		 }
    			 commonValidator.validatePolicyHolderIntervalAge( Cepr01030103FormConst.PAYOR_TPD_10_CI_FLAG, 17, 50 );
    		 }
         	 commonValidator.validatePolicyHolderIsNotInsured( Cepr01030103FormConst.PAYOR_TPD_10_CI_FLAG );
         }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorCiDeathFlag() ) )
        {
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorCiFlag() ) )
            {
                errors.rejectValue( Cepr01030103FormConst.PAYOR_CI_DEATH_FLAG, "error.riderCiOnlyTakenOne", null, null );
            }
            else if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorTpdDeathFlag() ) )
            {
                errors.rejectValue( Cepr01030103FormConst.PAYOR_CI_DEATH_FLAG, "error.rider25DeathOnlyTakenOne", null, null );
            }
            else if( commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.PAYOR_CI_DEATH_FLAG, 1, 17 ) )
            {
                commonValidator.validatePolicyHolderIntervalAge( Cepr01030103FormConst.PAYOR_CI_DEATH_FLAG, 20, 64 );
            }
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorCiFlag() ) )
        {
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorCiDeathFlag() ) )
            {
                errors.rejectValue( Cepr01030103FormConst.PAYOR_CI_FLAG, "error.riderCiOnlyTakenOne", null, null );
            }
            else if( commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.PAYOR_CI_FLAG, 1, 17 ) )
            {
                commonValidator.validatePolicyHolderIntervalAge( Cepr01030103FormConst.PAYOR_CI_FLAG, 20, 64 );
            }
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorTpdCiDeathFlag() ) )
        {
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorCiDeathFlag() ) )
            {
                errors.rejectValue( Cepr01030103FormConst.PAYOR_TPD_CI_DEATH_FLAG, "error.rider25DeathOnlyTakenOne", null, null );
            }
            
           // if( commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.PAYOR_TPD_CI_DEATH_FLAG, 1, 17 ) )

            String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
    		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung.substring(2, 4))<6 && LazyConverter.toInt(umurTertanggung.substring(0, 2))==0)
    		{
    			errors.rejectValue( Cepr01030103FormConst.PAYOR_TPD_CI_DEATH_FLAG, "error.insuredAgeMinMonth", new Object[]{ Integer.toString( 6 )  }, null );
    		}else if( cepr01030101Form.getInsuredAge() > 17 )
            {
    			 errors.rejectValue( Cepr01030103FormConst.PAYOR_TPD_CI_DEATH_FLAG, "error.insuredAgeMax", new Object[]{ Integer.toString( 17 ) }, null );
            }    			
    			
    		if( commonValidator.validatePolicyHolderIntervalAge( Cepr01030103FormConst.PAYOR_TPD_CI_DEATH_FLAG, 20, 57) ){
    			// Usia max Pemegang Polis,                	
            	// utk mengambil Riders: Payor 25 (payorTpdCiDeathFlag) = 65 – (25 + Usia Ttg)
            	int maxPolicyHolderAge = 65-(25-cepr01030101Form.getInsuredAge());
            	commonValidator.validateMaxAgeProduct( cepr01030101Form.getPolicyHolderAge(), Cepr01030103FormConst.PAYOR_TPD_CI_DEATH_FLAG , "Pemegang Polis, Payor 25 TPD/CI/Death", maxPolicyHolderAge );
    		}
                                  
            commonValidator.validatePolicyHolderIsNotInsured( Cepr01030103FormConst.PAYOR_TPD_CI_DEATH_FLAG );
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverTpdCiFlag() ) ){
        	commonValidator.validatePolicyHolderIsInsured( Cepr01030103FormConst.WAIVER_TPD_CI_FLAG );
        	if( cepr01030103Form.getWaiverTpdCiChooseCd().intValue() == 1 ){//55
        		commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.WAIVER_TPD_CI_FLAG, 17, 54 );
        	}else if( cepr01030103Form.getWaiverTpdCiChooseCd().intValue() == 2 ){//60
        		commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.WAIVER_TPD_CI_FLAG, 17, 59 );
        	}else if( cepr01030103Form.getWaiverTpdCiChooseCd().intValue() == 3 ){//65
        		commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.WAIVER_TPD_CI_FLAG, 17, 64 );
        	}
        }
        
        if( cepr01030102Form.getEducationPackageCd() != null && cepr01030102Form.getEducationPackageCd().equals( Hardcode.EDUCATION_PAKET_C ) ){
        	int flag_rider = 0;
        	if( cepr01030103Form.getEkaSehatFlag() != null && cepr01030103Form.getEkaSehatFlag().equals( CommonConst.CHECKED_TRUE ) ){
        		flag_rider = flag_rider + 1;
        	}
        	if( cepr01030103Form.getHcpFlag() != null && cepr01030103Form.getHcpFlag().equals( CommonConst.CHECKED_TRUE ) ){
        		flag_rider = flag_rider + 1;
        	}
        	if( flag_rider == 0 ){
        		String packageName = MappingUtil.getLabel( cepr01030102Form.getEducationPackageList(), cepr01030102Form.getEducationPackageCd() );
        		errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.packetMusthChooseThisRider", new Object[]{ packageName }, null );
        		errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.packetMusthChooseThisRider", new Object[]{ packageName }, null );
        	}
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesInsFlag() ) )
        {
        	commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.LADIES_INS_FLAG, 17, 60 );
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getScholarshipFlag() ) )
        {
        	commonValidator.validatePolicyHolderIsNotInsured( Cepr01030103FormConst.SCHOLARSHIP_FLAG );
         // Revisi=> Vito-Aktuaria@11-02-2015
         // commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.SCHOLARSHIP_FLAG, 1, 17 );
        	commonValidator.validateInsuredAge(Cepr01030103FormConst.SCHOLARSHIP_FLAG, 0, 17 );
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesMedExpenseFlag() ) )
        {
        	/*        
        	if("onPressButtonOk".equals( cepr01030101Form.getTheEvent() ) )
        	{
        		int flag_peserta = 0;
        		for( int i = 0 ; i < cepr01030111Form.getParticipantVOList().size() ; i ++ ){
        			if( cepr01030111Form.getParticipantVOList().get(i).getAge() != null && cepr01030111Form.getParticipantVOList().get(i).getAge() >= 17 ){
        				flag_peserta += 1;
        			}
        		}        	
        		if( flag_peserta == 0 ){
        	*/		
        			commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.LADIES_MED_EXPENSE, 17, 65 );
        	//}
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesMedExpenseInnerLimitFlag() ) )
        {
        	/*
        	if("onPressButtonOk".equals( cepr01030101Form.getTheEvent() ) )
        	{
        		int flag_peserta = 0;
        		for( int i = 0 ; i < cepr01030112Form.getParticipantVOList().size() ; i ++ ){
        			if( cepr01030112Form.getParticipantVOList().get(i).getAge() != null && cepr01030112Form.getParticipantVOList().get(i).getAge() >= 17 ){
        				flag_peserta += 1;
        			}
        		}
        		if( flag_peserta == 0 ){
        	*/
        			commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.LADIES_MED_EXPENSE_INNER_LIMIT, 17, 65 );
        			        		
        	//}
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTpdFlag() ) ){
        	 commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.TPD_FLAG, 1, 59 );
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEarlyStageCi99Flag() ) ){
       	 	commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.EARLY_STAGE_CI99_FLAG, 16, 65 );
       	 	if(cepr01030101Form.getInsuredSexCd().equals("")){
       	 		errors.rejectValue( Cepr01030103FormConst.EARLY_STAGE_CI99_FLAG, "error.insuredGenderCannotBeEmpty", null, null );
       	 	}
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEarlyStageCi99Flag()) && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getCiFlag()) ){
        		errors.rejectValue( Cepr01030103FormConst.EARLY_STAGE_CI99_FLAG, "error.riderCiNEsci99OnlyTakenOne", null, null );
        		errors.rejectValue( Cepr01030103FormConst.CI_FLAG, "error.riderCiNEsci99OnlyTakenOne", null, null );
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getCiFlag() ) )
        {
        	 if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_MAXI_SAVE ){
             	commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.CI_FLAG, 20, 60 );
        	 }else if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99 ){
              	commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.CI_FLAG, 1, 60 );
         	 }else{
             	commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.CI_FLAG, 17, 60 );
        	 }
//        	if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_MULTI_INVEST ||
//        			cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_MAXI_SAVE ){
//        	  if( !cepr01030103Form.getCiChooseCd().equals("50") ){
//        		  errors.rejectValue( Cepr01030103FormConst.CI_FLAG, "error.riderCiOnlyCanChoose", new Object[]{ "50" } , null );
//        	  }
//        	}
//        	 commonValidator.validateMinimumMoney( Cepr01030103FormConst.CI_FLAG, cepr01030103Form.getciRiderAmount(),new BigDecimal(7500000), new BigDecimal(750));
//        	 commonValidator.validateMaximumMoney( Cepr01030103FormConst.CI_FLAG, cepr01030103Form.getciRiderAmount(), new BigDecimal(5.E9), new BigDecimal(500000), "error.termRiderAmountMaxLimit" );
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag() ) )
        {	 
          if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag() ) && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusRjFlag())
        		  && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusRbFlag()) )
          {	
        	  commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.MEDICAL_PLUS_FLAG, 18, 40 );
        	  
        	  if(cepr01030101Form.getInsuredSexCd().equals("")){
         	 		errors.rejectValue( Cepr01030103FormConst.MEDICAL_PLUS_FLAG, "error.insuredGenderCannotBeEmpty", null, null );
         	 	}     
        	  
        	  if(cepr01030101Form.getInsuredSexCd().equalsIgnoreCase("L")){
        		  errors.rejectValue( Cepr01030103FormConst.MEDICAL_PLUS_FLAG, "error.insuredGenderMustBeWoman", null, null );        		 
        	  }
          }
          else if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag() ) && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusRjFlag()))
          {	
   			String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay()); 
//    		String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
       		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<15 )
       		{
       			errors.rejectValue( Cepr01030103FormConst.MEDICAL_PLUS_FLAG, "error.insuredAgeMinDay", new Object[]{ Integer.toString( 15 )  }, null );
       		}else if( cepr01030101Form.getInsuredAge() > 60 )
   	        {
   	            errors.rejectValue( Cepr01030103FormConst.MEDICAL_PLUS_FLAG, "error.insuredAgeMax", new Object[]{ Integer.toString( 60 ) }, null );
   	        }
          }
          else{ 
        	  	if(cepr01030101Form.getInsuredSexCd().equals("")){
        	  		errors.rejectValue( Cepr01030103FormConst.MEDICAL_PLUS_FLAG, "error.insuredGenderCannotBeEmpty", null, null );
       	 		}     
        	  
	            String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
//        		String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
	    		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<15 )
	    		{
	    			errors.rejectValue( Cepr01030103FormConst.MEDICAL_PLUS_FLAG, "error.insuredAgeMinDay", new Object[]{ Integer.toString( 15 )  }, null );
	    		}else if( cepr01030101Form.getInsuredAge() > 65 )
		        {
		            errors.rejectValue( Cepr01030103FormConst.MEDICAL_PLUS_FLAG, "error.insuredAgeMax", new Object[]{ Integer.toString( 65 ) }, null );
		        }
          }
        }	
                
        // tambahan filter TPD & CI untuk multiple check
	        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Ci10CiDeathFlag() ) )
	        {
	        	payor = payor + 1;
	        }
	        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Tpd10TpdDeathFlag() ) )
	    	{
	        	if( cepr01030102Form.getLeftPartOfBusinessCd() != Hardcode.PRODUK_CERDAS ){
	        		payor = payor + 1;
	        	}
	    	}
	        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Tpd10CiDeathFlag() ) )
	        {
	        	payor = payor + 1;
	        }
	        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorTpdCiDeathFlag() ) )
	        {
	        	payor = payor + 1;
	        }
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorTpdDeathFlag() ) )
            {
                tdp = tdp + 1;
            }
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverTpdFlag() ) )
            {
        		tdp = tdp + 1;
            }
        	//REQ: Gusti 02/01/2014 --> Rider Waiver 5/10 TPD/CI dapat diambil bersamaan dengan TPD
        	//if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTpdFlag() ) )
            //{
        	//	tdp = tdp + 1;
            //}
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorSpouseTpdDeathFlag() ) )
            {
        		tdp = tdp + 1;
            }
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Tpd10TpdDeathFlag() ) )
            {
        		tdp = tdp + 1;
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiver5Tpd10TpdFlag() ) )
            {
            	tdp = tdp + 1;
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiver5Tpd10CiFlag() ) )
            {
            	tdp = tdp + 1;
            }
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorCiFlag() ) )
            {
                ci = ci + 1;
            }
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorCiDeathFlag() ) )
            {
        		ci = ci + 1;
            }
        	//REQ : Desvinna(08-08-2014): Rider Waiver & Payor - CI boleh diambil bersamaan dg Rider CI
        	/*
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getCiFlag() ) )
             {
        		ci = ci + 1;
             }
            */
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverCiFlag() ) )
            {
        		ci = ci + 1;
            }
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Ci10CiDeathFlag() ) )
            {
        		ci = ci + 1;
            }        	
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiver5Ci10CiFlag() ) )
            {
            	ci = ci + 1;
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFlag() ) )
            {
            	hcp = hcp + 1;
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFamilyFlag() ) )
            {
            	hcp = hcp + 1;
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpProviderFlag() ) )
            {
            	hcp = hcp + 1;
            }
            
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) )
            {
            	ekasehat = ekasehat + 1;
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatInnerLimitFlag() ) )
            {
            	ekasehat = ekasehat + 1;
            }
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag() ) )
            {
            	ekasehat = ekasehat + 1;
            }            
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatExtraFlag() ) )
            {
            	ekasehat = ekasehat + 1;
            }
            
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesMedExpenseFlag() ) )
            {
            	ladies = ladies + 1;
            }
            
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesMedExpenseInnerLimitFlag() ) )
            {
            	ladies = ladies + 1;
            }
            
          //  if(cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_AMANAH_LINK){         
            	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverTpdCiFlag() ) ){
                		waiver = waiver + 1;
                	}            
            	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiver5Tpd10CiFlag() ) )	{
            			waiver = waiver + 1;
            	}     
            	
            	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiver5Tpd10TpdFlag() ) )	{
        			waiver = waiver + 1;        			
            	}   
            	
            	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiver5Ci10CiFlag() ) )	{
        			waiver = waiver + 1;        			
            	}   
            	
          //  }
            
        //----------------------------------------
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorSpouseTpdDeathFlag() ) )
        {
            if( commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.PAYOR_SPOUSE_TPD_DEATH_FLAG, 20, 59 ) )
            {
                commonValidator.validatePolicyHolderIsNotInsured( Cepr01030103FormConst.PAYOR_SPOUSE_TPD_DEATH_FLAG );
            }
        }
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiver5Tpd10TpdFlag() ) )
        {
        	commonValidator.validatePolicyHolderIsInsured( Cepr01030103FormConst.WAIVER_TPD_FLAG );
        	if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_CERDAS ){
        		if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X22){
        			commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.WAIVER_TPD_FLAG, 17, 55 );
              	}else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X23 ){
              		commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.WAIVER_TPD_FLAG, 17, 50 );
              	}
        	}else{
        		commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.WAIVER_TPD_FLAG, 17, 59 );
        	}
        }
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiver5Tpd10CiFlag() ) )
        {
        	commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.WAIVER_5_TPD_10_CI_FLAG, 17, 55 );
         	commonValidator.validatePolicyHolderIsInsured( Cepr01030103FormConst.WAIVER_5_TPD_10_CI_FLAG );
         	
         	if( cepr01030102Form.getLeftPartOfBusinessCd() == 213 || cepr01030102Form.getLeftPartOfBusinessCd() == 216 || cepr01030102Form.getLeftPartOfBusinessCd() == 217 
         		|| cepr01030102Form.getLeftPartOfBusinessCd() == 218  || cepr01030102Form.getLeftPartOfBusinessCd() == 220 || cepr01030102Form.getLeftPartOfBusinessCd() == 134 ){
				 if( !( cepr01030102Form.getPremiumFurloughYear() == 5 || cepr01030102Form.getPremiumFurloughYear() == 10 ) ){
	    			 errors.rejectValue( Cepr01030103FormConst.WAIVER_5_TPD_10_CI_FLAG, "error.only5and10", null, null );
	    		 }
			 }
         	
        }
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiver5Ci10CiFlag()) )
        {
         	commonValidator.validatePolicyHolderIsInsured( Cepr01030103FormConst.WAIVER_CI_10_FLAG );
         	if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_CERDAS ){
        		if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X22 ){
        			commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.WAIVER_CI_10_FLAG, 17, 55 );
              	}else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X23 ){
              		commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.WAIVER_CI_10_FLAG, 17, 50 );
              	}
        	}else{
        		commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.WAIVER_CI_10_FLAG, 17, 59 );
        	}
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTermRiderFlag() ) )
        {
        	 if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_MAXI_SAVE ){
        		 commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.TERM_RIDER_FLAG, 20, 60 );
        	 }else{
        		 commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.TERM_RIDER_FLAG, 1, 65 );
        	 }
//        	if(cepr01030102Form.getLeftPartOfBusinessCd() == 96 || cepr01030102Form.getLeftPartOfBusinessCd() == 164 ){
//        		commonValidator.validateMaximumMoney( Cepr01030103FormConst.TERM_RIDER_FLAG, cepr01030103Form.getTermRiderAmount(), cepr01030102Form.getTotalSumInsured(), cepr01030102Form.getTotalSumInsured(), "error.termRiderAmountMaxLimit2" );
//        	}else{
//        		commonValidator.validateMaximumMoney( Cepr01030103FormConst.TERM_RIDER_FLAG, cepr01030103Form.getTermRiderAmount(), cepr01030102Form.getTotalSumInsured(), cepr01030102Form.getTotalSumInsured(), "error.termRiderAmountMaxLimit" );
//        	}
//        	
//        	if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99 ){
//        		commonValidator.validateMinimumMoney( Cepr01030103FormConst.TERM_RIDER_FLAG, cepr01030103Form.getTermRiderAmount(),new BigDecimal(7500000), new BigDecimal(750));
//        	}
        }
        
        //for tambahan only
        if(tdp > 1){
        	errors.rejectValue( Cepr01030103FormConst.PAYOR_TPD_DEATH_FLAG, "error.riderTpdOnlyTakenOne", null, null );
        	errors.rejectValue( Cepr01030103FormConst.PAYOR_SPOUSE_TPD_DEATH_FLAG, "error.riderTpdOnlyTakenOne", null, null );
        	errors.rejectValue( Cepr01030103FormConst.WAIVER_TPD_FLAG, "error.riderTpdOnlyTakenOne", null, null );
        	errors.rejectValue( Cepr01030103FormConst.TPD_FLAG, "error.riderTpdOnlyTakenOne", null, null );
        }
        if(ci > 1){
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorCiDeathFlag() ) )errors.rejectValue( Cepr01030103FormConst.PAYOR_CI_DEATH_FLAG, "error.riderCiOnlyTakenOne", null, null );
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorCiFlag() ) )errors.rejectValue( Cepr01030103FormConst.PAYOR_CI_FLAG, "error.riderCiOnlyTakenOne", null, null );
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverCiFlag() ) )errors.rejectValue( Cepr01030103FormConst.WAIVER_CI_FLAG, "error.riderCiOnlyTakenOne", null, null );
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getCiFlag() ) )errors.rejectValue( Cepr01030103FormConst.CI_FLAG, "error.riderCiOnlyTakenOne", null, null );
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Ci10CiDeathFlag() ) )errors.rejectValue( Cepr01030103FormConst.PAYOR_CI_10_FLAG, "error.riderCiOnlyTakenOne", null, null );
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiver5Ci10CiFlag() ) )errors.rejectValue( Cepr01030103FormConst.WAIVER_CI_10_FLAG, "error.riderCiOnlyTakenOne", null, null );
        }
        
        if( payor > 1 ){
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Tpd10TpdDeathFlag() ) ) errors.rejectValue( Cepr01030103FormConst.PAYOR_TPD_10_FLAG, "error.riderPayorOnlyTakenOne", null, null );
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Ci10CiDeathFlag() ) ) errors.rejectValue( Cepr01030103FormConst.PAYOR_CI_10_FLAG, "error.riderPayorOnlyTakenOne", null, null );
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Tpd10CiDeathFlag() ) ) errors.rejectValue( Cepr01030103FormConst.PAYOR_TPD_10_CI_FLAG, "error.riderPayorOnlyTakenOne", null, null );
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorTpdCiDeathFlag() ) ) errors.rejectValue( Cepr01030103FormConst.PAYOR_TPD_CI_DEATH_FLAG, "error.riderPayorOnlyTakenOne", null, null );
        }
        
        if( hcp > 1 ){
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFlag() ) )errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpOnlyTakenOne", null, null );
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFamilyFlag() ) )errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpOnlyTakenOne", null, null );
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpProviderFlag() ) )errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpOnlyTakenOne", null, null );
        	
        }
         
        if(hcp>1 && ekasehat==1 || hcp==1 && ekasehat>1 || hcp>1 && ekasehat>1){
        	if( cepr01030102Form.getLeftPartOfBusinessCd() != Hardcode.PRODUK_MAXI_SAVE ){
	        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFlag() ) )errors.rejectValue( Cepr01030103FormConst.HCP_FLAG, "error.riderHcpNEkaSehatOnlyTakenOne", null, null );
	        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFamilyFlag() ) )errors.rejectValue( Cepr01030103FormConst.HCP_FAMILY_FLAG, "error.riderHcpNEkaSehatOnlyTakenOne", null, null );
	        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpProviderFlag() ) )errors.rejectValue( Cepr01030103FormConst.HCP_PROVIDER_FLAG, "error.riderHcpNEkaSehatOnlyTakenOne", null, null );
	        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) )errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.riderHcpNEkaSehatOnlyTakenOne", null, null );
	        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatInnerLimitFlag() ) )errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_INNER_LIMIT_FLAG, "error.riderHcpNEkaSehatOnlyTakenOne", null, null );
        	}
        }
        
        if(ekasehat>1){
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) ) errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_FLAG, "error.riderEkaSehatOnlyTakenOne", null, null );
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatInnerLimitFlag() ) ) errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_INNER_LIMIT_FLAG, "error.riderEkaSehatOnlyTakenOne", null, null );
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag() ) ) errors.rejectValue( Cepr01030103FormConst.MEDICAL_PLUS_FLAG, "error.riderEkaSehatOnlyTakenOne", null, null );
        	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatExtraFlag() ) ) errors.rejectValue( Cepr01030103FormConst.EKA_SEHAT_EXTRA_FLAG, "error.riderEkaSehatOnlyTakenOne", null, null );
        }
        
        if(ladies>1){
        	errors.rejectValue( Cepr01030103FormConst.LADIES_MED_EXPENSE_INNER_LIMIT,"error.riderLadiesMedExpenseTakenOne", null, null);
        	errors.rejectValue( Cepr01030103FormConst.LADIES_MED_EXPENSE,"error.riderLadiesMedExpenseTakenOne", null, null);
        }
        
        if(waiver>1){
        	errors.rejectValue( Cepr01030103FormConst.WAIVER_TPD_CI_FLAG,"error.riderWaiverOnlyTakenOne", null, null);
        	errors.rejectValue( Cepr01030103FormConst.WAIVER_5_TPD_10_CI_FLAG,"error.riderWaiverOnlyTakenOne", null, null);
        	errors.rejectValue( Cepr01030103FormConst.WAIVER_CI_10_FLAG,"error.riderWaiverOnlyTakenOne", null, null);
        	errors.rejectValue( Cepr01030103FormConst.WAIVER_TPD_10_FLAG,"error.riderWaiverOnlyTakenOne", null, null);        	
        }
        /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getCiFlag() )){
        	double upRider = cepr01030103Form.getCiRiderAmount().doubleValue();
        	if( cepr01030102Form.getCurrencyCd().equals( "01" ) ){
        		if(upRider < 7500000){
        			upRider = 7500000;
        			errors.rejectValue( Cepr01030103FormConst.CI_FLAG, "error.minimumUPRider", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( upRider ) }, null );
        		}else if (upRider > 5.E9){
        			upRider = 5.E9;
        			errors.rejectValue( Cepr01030103FormConst.CI_FLAG, "error.maximumUPRider", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( upRider ) }, null );
        		}
        	}else if( cepr01030102Form.getCurrencyCd().equals( "02" ) ){
        		if(upRider < 750){
        			upRider = 750;
        			errors.rejectValue( Cepr01030103FormConst.CI_FLAG, "error.minimumUPRider", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( upRider ) }, null );
        		}else if (upRider > 500000){
        			upRider = 500000;
        			errors.rejectValue( Cepr01030103FormConst.CI_FLAG, "error.maximumUPRider", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( upRider ) }, null );
        		}
        	}
        	
        }
        /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEarlyStageCi99Flag() )){
        	double upRider = cepr01030103Form.getEsci99RiderAmount().doubleValue();
        	if( cepr01030102Form.getCurrencyCd().equals( "01" ) ){
        		if(upRider < 7500000){
        			upRider = 7500000;
        			errors.rejectValue( Cepr01030103FormConst.EARLY_STAGE_CI99_FLAG, "error.minimumUPRider", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( upRider ) }, null );
        		}else if (upRider > 5.E9){
        			upRider = 5.E9;
        			errors.rejectValue( Cepr01030103FormConst.EARLY_STAGE_CI99_FLAG, "error.maximumUPRider", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( upRider ) }, null );
        		}
        	}else if( cepr01030102Form.getCurrencyCd().equals( "02" ) ){
        		if(upRider < 750){
        			upRider = 750;
        			errors.rejectValue( Cepr01030103FormConst.EARLY_STAGE_CI99_FLAG, "error.minimumUPRider", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( upRider ) }, null );
        		}else if (upRider > 500000){
        			upRider = 500000;
        			errors.rejectValue( Cepr01030103FormConst.EARLY_STAGE_CI99_FLAG, "error.maximumUPRider", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( upRider ) }, null );
        		}
        	}
        	
        }
        /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTermRiderFlag() )){
        	double upRider = cepr01030103Form.getTermRiderAmount().doubleValue();
        	if( cepr01030102Form.getCurrencyCd().equals( "01" ) ){
        		if(upRider < 7500000){
        			upRider = 7500000;
        			errors.rejectValue( Cepr01030103FormConst.TERM_RIDER_FLAG, "error.minimumUPRider", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( upRider ) }, null );
        		}else if (upRider > 5.E9){
        			upRider = 5.E9;
        			errors.rejectValue( Cepr01030103FormConst.TERM_RIDER_FLAG, "error.maximumUPRider", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( upRider ) }, null );
        		}
        	}else if( cepr01030102Form.getCurrencyCd().equals( "02" ) ){
        		if(upRider < 750){
        			upRider = 750;
        			errors.rejectValue( Cepr01030103FormConst.TERM_RIDER_FLAG, "error.minimumUPRider", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( upRider ) }, null );
        		}else if (upRider > 500000){
        			upRider = 500000;
        			errors.rejectValue( Cepr01030103FormConst.TERM_RIDER_FLAG, "error.maximumUPRider", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( upRider ) }, null );
        		}
        	}
        	
        }
        
        /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTerm5575RiderFlag() )){
        	  if( cepr01030103Form.getTerm5575RiderChooseCd() == Hardcode.TERM_55_RIDER ){
        	   		// commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.TERM_5575_RIDER_FLAG, 1, 54 );
        	   		 
        	   		String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());        	
            		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<600)
            		{
            			errors.rejectValue( Cepr01030103FormConst.TERM_5575_RIDER_FLAG, "error.insuredAgeMinMonth", new Object[]{ Integer.toString( 6 )  }, null );
            		}
            		
        			commonValidator.validateMaxAgeProduct( cepr01030101Form.getInsuredAge(),Cepr01030103FormConst.TERM_5575_RIDER_FLAG, "Term Rider 55", 54 );

        	   		 
        	   	 }
        	   	 else  if( cepr01030103Form.getTerm5575RiderChooseCd() == Hardcode.TERM_65_RIDER ){
        	       	 //commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.TERM_5575_RIDER_FLAG, 1, 64 );
        	       	 
        	       	String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());        	
            		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<600)
            		{
            			errors.rejectValue( Cepr01030103FormConst.TERM_5575_RIDER_FLAG, "error.insuredAgeMinMonth", new Object[]{ Integer.toString( 6 )  }, null );
            		}
            		
        			commonValidator.validateMaxAgeProduct( cepr01030101Form.getInsuredAge(),Cepr01030103FormConst.TERM_5575_RIDER_FLAG, "Term Rider 65", 64 );
        	       	 
        	       	 
        	   	 }
        	   	 else if( cepr01030103Form.getTerm5575RiderChooseCd() == Hardcode.TERM_75_RIDER ){
        	     //  	 commonValidator.validateInsuredIntervalAge( Cepr01030103FormConst.TERM_5575_RIDER_FLAG, 1, 65 );
        	      	 
         	       	String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());        	
             		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<600)
             		{
             			errors.rejectValue( Cepr01030103FormConst.TERM_5575_RIDER_FLAG, "error.insuredAgeMinMonth", new Object[]{ Integer.toString( 6 )  }, null );
             		}
             		
         			commonValidator.validateMaxAgeProduct( cepr01030101Form.getInsuredAge(),Cepr01030103FormConst.TERM_5575_RIDER_FLAG, "Term Rider 75", 65 );
         	       	 
        	       	 
        	   	 }
        	
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTerm5575RiderFlag() )){
        	double upRider = cepr01030103Form.getTerm5575RiderAmount().doubleValue();
        	if( cepr01030102Form.getCurrencyCd().equals( "01" ) ){
        		if(upRider < 7500000){
        			upRider = 7500000;
        			errors.rejectValue( Cepr01030103FormConst.TERM_5575_RIDER_FLAG, "error.minimumUPRider", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( upRider ) }, null );
        		}else if (upRider > 5.E9){
        			upRider = 5.E9;
        			errors.rejectValue( Cepr01030103FormConst.TERM_5575_RIDER_FLAG, "error.maximumUPRider", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( upRider ) }, null );
        		}
        	}else if( cepr01030102Form.getCurrencyCd().equals( "02" ) ){
        		if(upRider < 750){
        			upRider = 750;
        			errors.rejectValue( Cepr01030103FormConst.TERM_5575_RIDER_FLAG, "error.minimumUPRider", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( upRider ) }, null );
        		}else if (upRider > 500000){
        			upRider = 500000;
        			errors.rejectValue( Cepr01030103FormConst.TERM_5575_RIDER_FLAG, "error.maximumUPRider", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( upRider ) }, null );
        		}
        	}
        	
        }
  
    }
}