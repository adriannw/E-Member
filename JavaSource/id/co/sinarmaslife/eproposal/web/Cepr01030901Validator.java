package id.co.sinarmaslife.eproposal.web;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01030401Validator
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 13, 2007 3:49:13 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.math.BigDecimal;

import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000CommonValidator;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Validator;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.standard.util.StringUtil;
import id.co.sinarmaslife.standard.util.NumberUtil;
import id.co.sinarmaslife.standard.util.LazyConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;


public class Cepr01030901Validator extends Cepr01040000Validator
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01030901Validator( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
    }

    public void validateCommon()
    {
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030901Form cepr01030901Form = cepr01030000HoldingForm.getCepr01030901Form();
        boolean cekNull = false;
        
        
        if( StringUtil.isEmpty( cepr01030901Form.getCustomerName() ) )
        {
            errors.rejectValue( Cepr01030901FormConst.CUSTOMER_NAME, "error.formEmpty", null, null );
            cekNull = true;
        }
        
        if( cepr01030901Form.getCustomerCount() == null )
        {
            errors.rejectValue( Cepr01030901FormConst.CUSTOMER_COUNT, "error.formEmpty", null, null );
            cekNull = true;
        }
        
        if( cepr01030901Form.getCustomerAge() == null)
        {
            errors.rejectValue( Cepr01030901FormConst.CUSTOMER_AGE, "error.formEmpty", null, null );
            cekNull = true;
        }
      
        if( cepr01030901Form.getNormalAgePension() == null)
        {
            errors.rejectValue( Cepr01030901FormConst.NORMAL_AGE_PENSION, "error.formEmpty", null, null );
            cekNull = true;
        }
        if( cepr01030901Form.getIncome() == null)
        {
            errors.rejectValue( Cepr01030901FormConst.INCOME, "error.formEmpty", null, null );
            cekNull = true;
        }
        if( cepr01030901Form.getPastWorkingPeriod() == null)
        {
            errors.rejectValue( Cepr01030901FormConst.PAST_WORKING_PERIOD, "error.formEmpty", null, null );
            cekNull = true;
        }
       
        if( cepr01030901Form.getPercentPremiumFirstMonth() == null)
        {
            errors.rejectValue( Cepr01030901FormConst.PERCENT_PREMIUM_FIRST_MONTH, "error.formEmpty", null, null );
            cekNull = true;
        }
     
        
        if( cepr01030901Form.getPercentPremiumCompany() == null)
        {
            errors.rejectValue( Cepr01030901FormConst.PERCENT_PREMIUM_COMPANY,"error.formEmpty", null, null );
            cekNull = true;
        }
        
        if( cepr01030901Form.getPercentPremiumEmployee() == null)
        {
            errors.rejectValue( Cepr01030901FormConst.PERCENT_PREMIUM_EMPLOYEE,"error.formEmpty", null, null );
            cekNull = true;
        }
        
        if( cepr01030901Form.getPercentIncomeIncreasePerYear() == null)
        {
            errors.rejectValue( Cepr01030901FormConst.PERCENT_INCOME_INCREASE_PER_YEAR,"error.formEmpty", null, null );
            cekNull = true;
        }
        if( cepr01030901Form.getPercentInterestInvestPerYear() == null)
        {
            errors.rejectValue( Cepr01030901FormConst.PERCENT_INTEREST_INVEST_PER_YEAR,"error.formEmpty", null, null );
            cekNull = true;
        }
        
        if(!cekNull){
       
       if( cepr01030901Form.getNormalAgePension()<cepr01030901Form.getCustomerAge() )
       {
    	   errors.rejectValue( Cepr01030901FormConst.NORMAL_AGE_PENSION, "error.minNormalAgePension", null, null );
       }
       
       if( cepr01030901Form.getAmountFundTransfer() == null)
       {
           errors.rejectValue( Cepr01030901FormConst.AMOUNT_FUND_TRANSFER, "error.formEmpty", null, null );
       }
       if( cepr01030901Form.getIncome() < 0)
       {
           errors.rejectValue( Cepr01030901FormConst.INCOME, "error.minimumPremium", null, null );          
       }       
  
       if( cepr01030901Form.getAmountFundTransfer() < 0)
       {
           errors.rejectValue( Cepr01030901FormConst.AMOUNT_FUND_TRANSFER, "error.minimumPremium", null, null );          
        }
       if( cepr01030901Form.getPercentPremiumCompany().intValue()>100)
       {
           errors.rejectValue( Cepr01030901FormConst.PERCENT_PREMIUM_COMPANY,"error.maximumPremium", null, null );
       }
       if( cepr01030901Form.getPercentPremiumEmployee().intValue() > 100)
       {
           errors.rejectValue( Cepr01030901FormConst.PERCENT_PREMIUM_EMPLOYEE,"error.maximumPremium", null, null );
       }
       if( cepr01030901Form.getPercentIncomeIncreasePerYear().intValue() > 100)
       {
           errors.rejectValue( Cepr01030901FormConst.PERCENT_INCOME_INCREASE_PER_YEAR,"error.maximumPremium", null, null );
       }
       if( cepr01030901Form.getPercentInterestInvestPerYear().intValue() > 100)
       {
           errors.rejectValue( Cepr01030901FormConst.PERCENT_INTEREST_INVEST_PER_YEAR,"error.maximumPremium", null, null );
       }
      
        }
    }
}
