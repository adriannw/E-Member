package id.co.sinarmaslife.eproposal.product.product01040152;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040104
 * Program Name   		: Cepr01040104Entry
 * Description         	: Dana sejahtera (40)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 25, 2007 9:18:21 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000EntryInterface;
import id.co.sinarmaslife.eproposal.common.lookup.LookupList;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Entry;
import id.co.sinarmaslife.standard.util.LazyConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040152Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040152Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040104Entry constructor is called ..." );
        setDownloadUrl( "wepr01040152.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040104Entry.initDisplayedForm" );
        boolean isShowAdditionalAssurance;
      
            isShowAdditionalAssurance = false;
  
        displayStandardForm();
        displayAdditionalAssurance( isShowAdditionalAssurance );
        cepr01030102Form.setTermRiderFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setEkaSehatFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setButtonCountPremiumDisplay( CommonConst.DISPLAY_TRUE );
        if( credentialsVO != null && credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_DMTM ) ){
        	cepr01030102Form.setEmailDisplay( CommonConst.DISPLAY_TRUE );
        	cepr01030102Form.setFaxDisplay( CommonConst.DISPLAY_TRUE ); 
        }else{
        	cepr01030102Form.setEmailDisplay( CommonConst.DISPLAY_FALSE );
        	cepr01030102Form.setFaxDisplay( CommonConst.DISPLAY_FALSE ); 
        }
        
        if( cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD ) ){
        	cepr01030102Form.setWaiverPremiumDisabilityFlagDisplay( CommonConst.DISPLAY_FALSE );
        }else{
        	cepr01030102Form.setWaiverPremiumDisabilityFlagDisplay( CommonConst.DISPLAY_FALSE );
        }
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040104Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040104Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040104Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        Integer[] payModeArr;
        Integer termOfPayment;
//        checkedAdditionalAssurance( false );
        if( Cepr01040152Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            termOfPayment = 5;
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_SEKALIGUS };
        }
        else if( Cepr01040152Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            termOfPayment = 10;
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_SEKALIGUS };
        }
        else if( Cepr01040152Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            termOfPayment = 15;
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_SEKALIGUS };
        }
        else if( Cepr01040152Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            termOfPayment = 20;
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_SEKALIGUS };
        }
        else if( Cepr01040152Mapper.X5 == cepr01030102Form.getRightPartOfBusinessCd())
        {
            termOfPayment = 1;
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_SEKALIGUS };
        }
        else
        {
            throw new RuntimeException( "*-*-*-* Right part of business number not listed in Cepr01040104Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        }

        cepr01030102Form.setTermOfPayment( termOfPayment );
        cepr01030102Form.setPaymentModeCd( payModeArr[ 0 ] );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, payModeArr ) );

        double totalSumInsured;
        double totalSumInsuredIdr = 0;
        double totalSumInsuredUsd = 0;

        if( Cepr01040152Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040152Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd()
         || Cepr01040152Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040152Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd()
         || Cepr01040152Mapper.X5 == cepr01030102Form.getRightPartOfBusinessCd())
        {
            totalSumInsuredIdr = 100000000;
            totalSumInsuredUsd = 10000;
        }
       
        totalSumInsured = Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() )? totalSumInsuredIdr : totalSumInsuredUsd;

        cepr01030102Form.setTotalSumInsured( new BigDecimal( totalSumInsured ) );
        cepr01030102Form.setPremium( computePremium( cepr01030102Form.getPaymentModeCd() ) );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040104Entry.fillDefaultValueEachTimeFormCalled" );

        //cepr01030102Form.setTermOfContract( 79 - cepr01030101Form.getInsuredAge() );
        cepr01030102Form.setTermOfContract( 100 - cepr01030101Form.getInsuredAge() );
        
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        cepr01030102Form.setTermRiderUnitQtyList( LookupList.getSequenceNumberList( 1, 5, false ) );
        cepr01030102Form.setPersonalAccidentUnitQtyList( LookupList.getSequenceNumberList( 1, 3, false ) );
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040104Entry.validateCurrentPage" );
        
        int maxInsuredAge = 60;
        if( Cepr01040152Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() ){
        	maxInsuredAge = 55;
        }
        //commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 18, maxInsuredAge );
        if( cepr01030101Form.getInsuredAge() > maxInsuredAge )
        {
            errors.rejectValue( Cepr01030102FormConst.ASSURANCE_PLAN_CD, "error.insuredAgeMax", new Object[]{ Integer.toString( maxInsuredAge ) }, null );
        }
        
        if( cepr01030102Form.getPremium() != null )
        {
            double totalSumInsured = LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() );
            double minimumTotalSumInsuredIdr = 0;
            double minimumTotalSumInsuredUsd = 0;

          
                minimumTotalSumInsuredIdr = 100000000;
                minimumTotalSumInsuredUsd = 10000;
          
          
            commonValidator.validateMinimumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, totalSumInsured, minimumTotalSumInsuredIdr, minimumTotalSumInsuredUsd );
        }
        else
        {
            errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED, "error.formEmpty", null, null );
        }
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040104Entry.validatePreviousPage" );

       //commonValidator.validatePolicyHolderAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 18, 85 );
        
               
        // if take Payor Clause Rider then validate these ones
        if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getPayorsClauseFlag() )
                && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getPayorsClauseFlagDisplay() ) )
        {
            if( cepr01030101Form.getInsuredAge() > 15 )
            {
                errors.rejectValue( Cepr01030102FormConst.PAYORS_CLAUSE_FLAG, "error.insuredAgeMaxForPc", new Object[]{ "15" }, null );
            }
            else if( cepr01030101Form.getPolicyHolderAge() < 20 )
            {
                errors.rejectValue( Cepr01030102FormConst.PAYORS_CLAUSE_FLAG, "error.policyHolderAgeMinForPc", new Object[]{ "20" }, null );
            }
        }

        // if take WPD Rider then validate these ones
        if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getWaiverPremiumDisabilityFlag() )
                && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getPayorsClauseFlagDisplay() ) )
        {
            commonValidator.validatePolicyHolderIsInsured( Cepr01030102FormConst.WAIVER_PREMIUM_DISABILITY_FLAG );
        }
    }

    public BigDecimal computePremium( Integer payModeCd )
    {
        logger.info( "*-*-*-* Cepr01040104Entry.computePremium" );

        Map<String, Object> sqlParams = new HashMap<String, Object>();
        //sqlParams.put( "leftPartOfBusinessCd", new Integer( "163" ) );
        sqlParams.put( "leftPartOfBusinessCd", new Integer( "226" ) );
        sqlParams.put( "lstabLamaTanggung", new Integer( "0" ) );
        sqlParams.put( "lsdbsNumber", cepr01030102Form.getRightPartOfBusinessCd() );
        sqlParams.put( "lstabLamaBayar", cepr01030102Form.getTermOfPayment() );
        sqlParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_PREMI );
        sqlParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
        sqlParams.put( "paymentModeCd", payModeCd );
        sqlParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
        sqlParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
        sqlParams.put( "yearNoList", new Integer[]{ new Integer( "1" ) } );
        logger.info( "*-*-*-* sqlParams = " + sqlParams );
        List rateList = eproposalManager.selectRateNew( sqlParams );
        BigDecimal premium;
        BigDecimal rate;
        if( rateList != null && rateList.size() > 0 )
        {
            rate = eproposalManager.selectRateNew( sqlParams ).get( 0 );
            premium = computePremiumWithSimpleMethod( cepr01030102Form.getTotalSumInsured(), rate, new BigDecimal( "1" ) );
            
            if( cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_IDR_CD ) ){
	            BigDecimal amount =premium.setScale( 2, RoundingMode.HALF_EVEN);
	            DecimalFormat decimalFormat = new DecimalFormat( "##.00" );
	            String temp = decimalFormat.format( amount );
	         
	            BigDecimal prm = new BigDecimal( temp );
	            premium = prm;
            }
        }
        else
        {
            premium = new BigDecimal( "0" );
        }

        return premium;
    }
    
    


    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040104Entry.processFormAfterSubmitBeforeValidation" );
        cepr01030102Form.setPremium( computePremium( cepr01030102Form.getPaymentModeCd() ) );
    }
   
}
