package id.co.sinarmaslife.eproposal.product.product01040146;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040201
 * Program Name   		: Cepr01040201Entry
 * Description         	: Eka Link Family (159)
 * Environment      	: Java  1.5.0_06
 * Author               : Fadly Mathendra
 * Version              : 1.0
 * Creation Date    	: Okt 29, 2007 10:03:20 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.lookup.LookupList;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000UlinkEntry;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000Generator;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000EntryInterface;
import id.co.sinarmaslife.eproposal.model.vo.AmountVO;
import id.co.sinarmaslife.eproposal.model.vo.UlinkValidatorParVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.eproposal.web.Cepr01030103FormConst;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.LazyConverter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

public class Cepr01040146Entry extends Cepr01040000UlinkEntry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040146Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040146Entry constructor is called ..." );
        setDownloadUrl( "wepr01040146.pdf" );
    } 

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040146Entry.initDisplayedForm" );
        displayStandardForm();
        cepr01030102Form.setPremiumListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setHeaderInvestChoiceDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumCombinationListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonCountTotalSumInsuredDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonRiderAndButtonTopupDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonRiderDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonTopupDisplay( CommonConst.DISPLAY_TRUE );
        
        cepr01030102Form.setPremiumFurloughYearListDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030102Form.setPremiumFurloughYearDisplay( CommonConst.DISPLAY_TRUE );
        
        cepr01030102Form.setBasePremiumDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setTopUpPremiumDisplay( CommonConst.DISPLAY_TRUE );
        if( Cepr01040146Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() ){
        	cepr01030102Form.setPremiumFurloughYearListDisplayHelperDisplay( CommonConst.DISPLAY_TRUE );
        }else if( Cepr01040146Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() ){
        	cepr01030102Form.setPremiumFurloughYearListDisplayHelperDisplay( CommonConst.DISPLAY_FALSE );
         }
        // rider
    	cepr01030102Form.setTotalSumInsuredListDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDeathDisplay(CommonConst.DISPLAY_FALSE);
        cepr01030103Form.setPayorSpouseTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverTpdDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverCiDisplay( CommonConst.DISPLAY_FALSE );
       
        cepr01030103Form.setHcpFamilyDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setEkaSehatDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setEkaSehatInnerLimitDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setLadiesMedExpenseDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setLadiesMedExpenseInnerLimitDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setHcpLadiesDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setPayorSpouseTpdDeathDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setScholarshipDisplay( CommonConst.DISPLAY_TRUE );
      //SMiLe Medical Extra Syariah Maret 2021
        cepr01030103Form.setEkaSehatExtraDisplay( CommonConst.DISPLAY_TRUE );  
        if( Cepr01040146Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() ){
        	cepr01030103Form.setWaiverTpdCiDisplay( CommonConst.DISPLAY_FALSE );
        	cepr01030102Form.setEducationPackageListDisplay( CommonConst.DISPLAY_FALSE );
        }else if( Cepr01040146Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() ){
        	cepr01030103Form.setWaiverTpdCiDisplay( CommonConst.DISPLAY_TRUE );
        	cepr01030102Form.setEducationPackageListDisplay( CommonConst.DISPLAY_FALSE );
        }
        if( credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals(Hardcode.GROUP_MNC)){
        	 if( Cepr01040146Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
             {
       		  cepr01030102Form.setSpecialOfferListDisplay( CommonConst.DISPLAY_FALSE );
             }
             else if( Cepr01040146Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() )
             {
           	  cepr01030102Form.setSpecialOfferListDisplay( CommonConst.DISPLAY_TRUE );
//           	  cepr01030102Form.setSpecialOfferCd( Hardcode.SPECIAL_OFFER_ADDITIONAL_UNIT );
//           	  cepr01030102Form.setSpecialOfferListIsDisabled( CommonConst.DISABLED_TRUE );
             }
        }
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040146Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPremiumListIsDisabled( CommonConst.DISABLED_FALSE );

//      restore investment choice
        cepr01030102Form.setFixIdrIsDisabled( CommonConst.DISABLED_FALSE  );
        cepr01030102Form.setDynamicIdrIsDisabled( CommonConst.DISABLED_FALSE  );
        cepr01030102Form.setAggresiveIdrIsDisabled( CommonConst.DISABLED_FALSE  );
        cepr01030102Form.setDynamicUsdIsDisabled( CommonConst.DISABLED_FALSE  );
        cepr01030102Form.setSecureUsdIsDisabled( CommonConst.DISABLED_FALSE  );
        //cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_FALSE );
        //cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_TRUE);
        
        if( Cepr01040146Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
        {cepr01030102Form.setTotalSumInsuredIsDisabled(CommonConst.DISABLED_TRUE);
        }
	    else if(Cepr01040146Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd())
	    {cepr01030102Form.setTotalSumInsuredIsDisabled(CommonConst.DISABLED_FALSE);
        }
        if( credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals(Hardcode.GROUP_MNC)){
//        	cepr01030102Form.setSpecialOfferListIsDisabled( CommonConst.DISABLED_TRUE );
        }

    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040146Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
    	
    	if( Cepr01040146Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            cepr01030102Form.setPremiumCombinationCd( new BigDecimal(100) );
            cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_TRUE );
        }
	    else if(Cepr01040146Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd())
	    {
            cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_FALSE );
	    }
    	
    	for(int i = 0 ; i < cepr01030104Form.getTopupDrawVOList().size() ; i++)
    	{
    		cepr01030104Form.getTopupDrawVOList().get(i).setTopupAmount(new BigDecimal(0));
    		cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(0));
    		cepr01030104Form.getTopupDrawVOList().get(i).setYearFlag(CommonConst.CHECKED_FALSE);    		
    	}
    	if(cepr01030101Form.getInsuredAge()<18)
    	{
	    	int getUmurInsured = cepr01030101Form.getInsuredAge();
	    	int minAgeTopUp = 13; // krn index, maka minAge - 1
	    	int maxAgeTopUp = 21; // krn index, maka maxAge - 1
	    	int[] valueDrawAmount = {50,10,10,10,50};
	    	for(int i = 0 ; i < cepr01030104Form.getTopupDrawVOList().size() ; i++)
	    	{
	    		if(getUmurInsured + i >= minAgeTopUp && getUmurInsured + i <= maxAgeTopUp)
	    		{
	    			cepr01030104Form.getTopupDrawVOList().get(i).setYearFlag(CommonConst.CHECKED_TRUE);
	    	  		if(getUmurInsured + i == 17){cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(valueDrawAmount[0]));}
	    	  		if(getUmurInsured + i == 18){cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(valueDrawAmount[1]));}
	    	  		if(getUmurInsured + i == 19){cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(valueDrawAmount[2]));}
	    	  		if(getUmurInsured + i == 20){cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(valueDrawAmount[3]));}
	    	  		if(getUmurInsured + i == 21){cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(valueDrawAmount[4]));}
	    		}
	    	}
    	}
    	
    	cepr01030102Form.setTempInsuredAge(cepr01030101Form.getInsuredAge());
    	
        logger.info( "*-*-*-* Cepr01040146Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_BULANAN );
        String[] currencyCdArr = new String[]{ Hardcode.CUR_IDR_CD};
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, currencyCdArr ) );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040146Entry.fillDefaultValueEachTimeFormCalled" );
        cepr01030102Form.setPremiumOptionIsToRefreshPage( CommonConst.REFRESH_TRUE );
        cepr01030102Form.setFixIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setDynamicIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setAggresiveIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setCashFundIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setSecureUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setDynamicUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        
        if( cepr01030102Form.getPremium() != null  && cepr01030102Form.getPaymentModeCd().equals( Hardcode.PAY_MODE_CD_BULANAN ) ){
        	double premi = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        	if( premi >= 150000.0 && premi <= 250000){
        		cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombinationFoldedStart70List(10) );
        	}else{
        		cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombinationFoldedList(10) );
        	}
        }else{
        	   cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombinationFoldedList(10) );
        }
        
     
        if( Cepr01040146Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
        	cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_TRUE );
        	if(cepr01030102Form.getSpecialOfferCd() != null)cepr01030102Form.setSpecialOfferCd(0);
        }
        else if(Cepr01040146Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd())
	    {
        	cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_FALSE );
	    }
        cepr01030102Form.setTermOfContract( 80 - cepr01030101Form.getInsuredAge() );
        cepr01030103Form.setPaRiskList( getPaRiskList() );
        cepr01030103Form.setPaClassList( LookupList.getSequenceNumberList( 1, 1, false ) );
        cepr01030103Form.setPaUnitQtyList( LookupList.getSequenceNumberList( 1, 1, false ) );
        
        if( Cepr01040146Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
        	cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_SEKALIGUS } ) );
        }
	    else if(Cepr01040146Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd())
	    {
	    	cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_BULANAN, Hardcode.PAY_MODE_CD_TAHUNAN } ) );
	    }
        
        
        List<OptionVO> premiumList = null;
        
        if( Cepr01040146Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
        	if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 10000000, 220000000, 1000000 );
            }
            cepr01030102Form.setTermOfPayment( 1 );
        }
        else if( Cepr01040146Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
        	if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 3000000, 15000000, 1000000 );
            }
            cepr01030102Form.setTermOfPayment( 55 - cepr01030101Form.getInsuredAge() );
        }
        
        //double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        //double minPremiumIdr;
        //double minPremiumUsd = -1;
        
        /*if( cepr01030102Form.getPremium() != null )
        {
        	if( Cepr01040146Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
            {
        		minPremiumIdr = 5000000;
        		commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );
            }
    	    else if(Cepr01040146Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd())
    	    {
    	    	minPremiumIdr = 1200000;
    	    	commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );
    	    }
            
            if( cepr01030101Form.getInsuredAge() <=5 )
            {
            	commonValidator.validateMaximumMoney( Cepr01030102FormConst.PREMIUM, premium, 10000000, -1 );            	
            }
            else if( cepr01030101Form.getInsuredAge() > 5 )
            {
            	commonValidator.validateMaximumMoney( Cepr01030102FormConst.PREMIUM, premium, 20000000, -1 );	
            }
            
            commonValidator.validateInvestmentSumIs100Percent();
        }*/

        if( cepr01030102Form.getCurrencyCd() == null )
        {
            cepr01030102Form.setCurrencyCd( Hardcode.CUR_IDR_CD );
        }

        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            cepr01030102Form.setFixIdrDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setFixIdrListDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setDynamicIdrDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setDynamicIdrListDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setAggresiveIdrDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setAggresiveIdrListDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setCashFundIdrDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setCashFundIdrListDisplay( CommonConst.DISPLAY_TRUE );
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            cepr01030102Form.setSecureUsdDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setSecureUsdListDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setDynamicUsdDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setDynamicUsdListDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setCashFundIdrDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030102Form.setCashFundIdrListDisplay( CommonConst.DISPLAY_FALSE );
        }
        
        if( Cepr01040146Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
        	cepr01030102Form.setPremiumFurloughYearList( LookupList.getSequenceNumberList( 1, 1, 1, false ) );
        	cepr01030102Form.setPremiumFurloughYear(1);
        }
	    else if(Cepr01040146Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd())
	    {
	    	cepr01030102Form.setPremiumFurloughYearList( LookupList.getSequenceNumberList( 0, 20, 5, false ) );
	    }
        
        cepr01030102Form.setPremiumList( premiumList );
        refreshBaseTopupPremium();
        if( "onChangeEducationPackageCd".equals( cepr01030102Form.getTheEvent() ) ){
        	setRiderValueForFirstTime();
        	setEducationPackage();
        }
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040146Entry.validateCurrentPage" );
        UlinkValidatorParVO parVO = new UlinkValidatorParVO();
        parVO.setUnder5YearsBerkalaMaxBasePremiumVO( new AmountVO( 1000000000, 100000 ) );
        parVO.setUnder5YearsSekaligusMaxBasePremiumVO( new AmountVO( 1000000000, 100000 ) );
        parVO.setBeyond5YearsBerkalaMaxBasePremiumVO( new AmountVO( 1000000000, 100000 ) );
        parVO.setBeyond5YearsSekaligusMaxBasePremiumVO( new AmountVO( 1000000000, 100000 ) );
        super.validateCurrentPage( parVO );
        if( errors.getErrorCount() == 0 && errors.getGlobalErrorCount() == 0 )
        {
        	if(cepr01030101Form.getInsuredAge() < 6){
        		commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, cepr01030102Form.getTotalSumInsured(), new BigDecimal("500000000"), new BigDecimal("50000") );
        	}
        }
        validateCurrentPageInCommon();
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040146Entry.validatePreviousPage" );
        if( Cepr01040146Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
	    	commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 1, 55 );
        }
	    else if(Cepr01040146Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd())
	    {
	    	commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 1, 55 );
	    	if( cepr01030102Form.getPremiumFurloughYearCd() > 10 ){
	    		commonValidator.validatePremiumFurloughYearMax( 55, cepr01030101Form.getInsuredAge() + cepr01030102Form.getPremiumFurloughYearCd() );
	    	}
	    }
//        commonValidator.validatePolicyHolderIsInsured( Cepr01030102FormConst.ASSURANCE_PLAN_CD );
	    commonValidator.validatePolicyHolderAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 85 );
               
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040146Entry.processFormAfterSubmitBeforeValidation" );
        
        double[] upRate = 
        {0,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,102,97,93,88,84,80,75,71,68,64,60,   
           57 ,54 ,50 ,47 ,45 ,42 ,40 ,37 ,35 ,33 ,31 ,29 ,28 ,26 ,25 ,23 ,22 ,21 ,19 ,18 ,17,16,15,14,14,13,12,11,11,10};
        
        if(cepr01030102Form.getTempInsuredAge()!=null)
        {
	        if(cepr01030102Form.getTempInsuredAge() != cepr01030101Form.getInsuredAge())
	        {
	        	for(int i = 0 ; i < cepr01030104Form.getTopupDrawVOList().size() ; i++)
	        	{
	        		cepr01030104Form.getTopupDrawVOList().get(i).setTopupAmount(new BigDecimal(0));
	        		cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(0));
	        		cepr01030104Form.getTopupDrawVOList().get(i).setYearFlag(CommonConst.CHECKED_FALSE);    		
	        	}
	        	if(cepr01030101Form.getInsuredAge()<18)
	        	{
		        	int getUmurInsured = cepr01030101Form.getInsuredAge();
		        	int minAgeTopUp = 13; // krn index, maka minAge - 1
		        	int maxAgeTopUp = 21; // krn index, maka maxAge - 1
		        	int[] valueDrawAmount = {50,10,10,10,50};
		        	for(int i = 0 ; i < cepr01030104Form.getTopupDrawVOList().size() ; i++)
		        	{
		        		if(getUmurInsured + i >= minAgeTopUp && getUmurInsured + i <= maxAgeTopUp)
		        		{
		        			cepr01030104Form.getTopupDrawVOList().get(i).setYearFlag(CommonConst.CHECKED_TRUE);
		        	  		if(getUmurInsured + i == 17){cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(valueDrawAmount[0]));}
		        	  		if(getUmurInsured + i == 18){cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(valueDrawAmount[1]));}
		        	  		if(getUmurInsured + i == 19){cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(valueDrawAmount[2]));}
		        	  		if(getUmurInsured + i == 20){cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(valueDrawAmount[3]));}
		        	  		if(getUmurInsured + i == 21){cepr01030104Form.getTopupDrawVOList().get(i).setDrawAmount(new BigDecimal(valueDrawAmount[4]));}
		        		}
		        	}
	        	}
	        	cepr01030102Form.setTempInsuredAge(cepr01030101Form.getInsuredAge());
	        }
        }
        
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
        refreshBaseTopupPremium();
        double idec_up = 0;   
        if( "onChangePremium".equals( cepr01030102Form.getTheEvent() )
            || "onPressButtonCountTotalSumInsured".equals( cepr01030102Form.getTheEvent() )
            || "onPressButtonPreviewProposal".equals( cepr01030102Form.getTheEvent() )
            || cepr01030102Form.getTotalSumInsured() != null && cepr01030102Form.getTotalSumInsured().equals( new BigDecimal( "0" ) ))
        {
        	int getUmurInsured = cepr01030101Form.getInsuredAge();
            double adec_premi = premium;
            double adec_premi_pokok = 0;;
            double adec_premi_combine = premiumCombinationPercent/100;
              
            if( Cepr01040146Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
            {
            	adec_premi_pokok = adec_premi;
            	idec_up = adec_premi * 1.25;
            	idec_up = idec_up * adec_premi_combine;
            	if(idec_up < 15000000){
            		idec_up = 15000000;
            	}
            }
    	    else if(Cepr01040146Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd())
    	    {
    	    	if( cepr01030102Form.getPaymentModeCd() != null ){
    	    		
    	    	if(cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TRIWULANAN){
    	    		idec_up = adec_premi * 4;
    	    	}else if(cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEMESTERAN){
    	    		idec_up = adec_premi * 2;
    	    	}else if(cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_BULANAN){
    	    		idec_up = adec_premi * 12;
    	    	}else if(cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TAHUNAN){
    	    		idec_up = adec_premi * 1;
    	    	}
    	    	}
    	    	adec_premi_pokok = idec_up;
    	    	idec_up = idec_up * 5;
    	    	idec_up = idec_up * adec_premi_combine;
    	    	if(idec_up < 7500000){
            		idec_up = 7500000;
            	}
    	    }
            
            
            
//            List<OptionVO> totalSumInsuredList = new ArrayList<OptionVO>();
//            totalSumInsuredList.add(new OptionVO(editorUtil.convertToString(idec_up), editorUtil.convertToString(idec_up)));
//            totalSumInsuredList.add(new OptionVO(editorUtil.convertToString(((adec_premi_pokok * adec_premi_combine) * upRate[getUmurInsured])), editorUtil.convertToString(((adec_premi_pokok * adec_premi_combine) * upRate[getUmurInsured]))));
//            cepr01030102Form.setTotalSumInsuredList( totalSumInsuredList );
            if( "onChangePremium".equals( cepr01030102Form.getTheEvent() )
                    || "onPressButtonCountTotalSumInsured".equals( cepr01030102Form.getTheEvent() )
                    || cepr01030102Form.getTotalSumInsured().equals( new BigDecimal( "0" ) ) )
                {
            		cepr01030102Form.setTotalSumInsured( new BigDecimal( idec_up ) );
                }
//            else if( "onPressButtonPreviewProposal".equals( cepr01030102Form.getTheEvent()) ){
//                	idec_up = cepr01030102Form.getTotalSumInsured().doubleValue();
//                	if(idec_up > ((adec_premi_pokok * adec_premi_combine) * upRate[getUmurInsured])){
//                    	idec_up = (adec_premi_pokok * adec_premi_combine) * upRate[getUmurInsured];
//                    }
//                	cepr01030102Form.setTotalSumInsured( new BigDecimal( idec_up ) );
//                }
        }
        
        	//validateCurrentPageInCommon();
        	if( cepr01030102Form.getTotalSumInsured().compareTo( new BigDecimal( "7500000" ) ) < 0 ){
        		cepr01030102Form.setTotalSumInsured( new BigDecimal( "7500000" ) );
        		cepr01030102Form.setTotalSumInsuredCd( new BigDecimal( "7500000" ) );
        	} 
        	
    }

    public void validateCurrentPageInCommon()
    {
        logger.info( "*-*-*-* Cepr01040146Entry.validateCurrentPageInCommon" );
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double minPremiumIdr;
        double minPremiumUsd = -1;
        switch( cepr01030102Form.getPaymentModeCd() )
        {
            case Hardcode.PAY_MODE_CD_SEKALIGUS:
                minPremiumIdr = 5000000;
                minPremiumUsd = 500;
                break;
            case Hardcode.PAY_MODE_CD_TAHUNAN:
            	if( credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals(Hardcode.GROUP_MNC)){
            		minPremiumIdr = 1200000;
                    minPremiumUsd = 120;
            	}else{
            		//REQ: Auditya@13/02/2014
            		//minPremiumIdr = 3000000;
                    //minPremiumUsd = 300;
            		minPremiumIdr = 1800000;
                    minPremiumUsd = 180;
            	}
                break;
            case Hardcode.PAY_MODE_CD_SEMESTERAN:
                minPremiumIdr = 1500000;
                minPremiumUsd = 150;
                break;
            case Hardcode.PAY_MODE_CD_TRIWULANAN:
                minPremiumIdr = 750000;
                minPremiumUsd = 75;
                break;
            case Hardcode.PAY_MODE_CD_BULANAN:
            	 if( credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals(Hardcode.GROUP_MNC)){
            		 minPremiumIdr = 100000;
                     minPremiumUsd = 50;
                 }else{                	
                	 minPremiumIdr = 150000;
                	 minPremiumUsd = 50;                	
                 }
                break;
            default:
                throw new RuntimeException( "*-*-*-* cepr01030102Form.getPaymentModeCd() not listed in Cepr01040201Entry.validateCurrentPage" );
        }

        if( cepr01030102Form.getPremium() != null )
        {
        	if( Cepr01040146Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
            {
        		commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );
            }
    	    else if(Cepr01040146Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd())
    	    {
    	    	commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );
    	    }
            /*
            if( cepr01030101Form.getInsuredAge() <=5 )
            {
            	commonValidator.validateMaximumMoney( Cepr01030102FormConst.PREMIUM, premium, 10000000, -1 );            	
            }
            else if( cepr01030101Form.getInsuredAge() > 5 )
            {
            	commonValidator.validateMaximumMoney( Cepr01030102FormConst.PREMIUM, premium, 20000000, -1 );	
            }*/
            
            commonValidator.validateInvestmentSumIs100Percent();
        }
        else
        {
            errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.formEmpty", null, null );
        }

        if( cepr01030102Form.getPremiumCombinationCd() == null )
        {
            errors.rejectValue( Cepr01030102FormConst.PREMIUM_COMBINATION_CD, "error.formEmpty", null, null );
        }
      /*
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTermRiderFlag() ) )
        {
            commonValidator.validateMaximumMoney( Cepr01030102FormConst.ASSURANCE_PLAN_CD, cepr01030103Form.getTermRiderAmount(), cepr01030102Form.getTotalSumInsured(), cepr01030102Form.getTotalSumInsured(), "error.termRiderAmountMaxLimit" );
        }
      */
        commonValidator.validateTenFoldedForAllInvestment();
       
        if( Cepr01040146Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
        	commonValidator.validatePremiumFurloughYear( Cepr01030102FormConst.PREMIUM_FURLOUGH_YEAR, 2, 20 );
        	commonValidator.validateSpecialOffer();
        }
    }
    
    public List<OptionVO> getPremiumCombinationList( int folded )
    {
        List<OptionVO> premiumCombinationList = new ArrayList<OptionVO>();

        OptionVO optionVO;
        optionVO = new OptionVO( "", "" );
        premiumCombinationList.add( optionVO );
        optionVO = new OptionVO( "100.00", "Premi Pokok 100%" );
        premiumCombinationList.add( optionVO );
        String value;
        String label;
        int help = 0;
        for( int i = 100 - folded; i > 0; i = i - folded )
        {
            value = i + ".00";
            
            if(i == 40 && help == 0)
            {
            	i = 50;
            	label = "Premi Pokok " + i + "%, Premi Top-Up Berkala " + ( 100 - i ) + "%";
            	i = 60;
            	value = 50 + ".00";
            	help = 1;
            	optionVO = new OptionVO( value, label );
            }
            else {
            	
            	label = "Premi Pokok " + i + "%, Premi Top-Up Berkala " + ( 100 - i ) + "%";
            	optionVO = new OptionVO( value, label );
            }
           
            premiumCombinationList.add( optionVO );
        }

        return premiumCombinationList;
    }
    
    public static List<OptionVO> getPaRiskList()
    {
    	List<OptionVO> paRiskList = null;
        if( paRiskList == null )
        {
            paRiskList = new ArrayList<OptionVO>();

            OptionVO optionVO;
            optionVO = new OptionVO( "1", "Resiko A" );
            paRiskList.add( optionVO );
        }
        return paRiskList;
    }
}
