package id.co.sinarmaslife.eproposal.product.product01040229;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040216Entry (134)
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 4, 2008 9:22:03 AM
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
import id.co.sinarmaslife.eproposal.product.product01040208.Cepr01040208Mapper;
import id.co.sinarmaslife.eproposal.product.product01040211.Cepr01040211Mapper;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cepr01040229Entry extends Cepr01040000UlinkEntry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );
    private static final int ASSURANCE_TERM = 100;

    public Cepr01040229Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040229Entry constructor is called ..." );
        setDownloadUrl( "wepr01040229.pdf" );
    }

    public void initDisplayedForm()
    {
    	 logger.info( "*-*-*-* Cepr01040208Entry.initDisplayedForm" );
         displayStandardForm();
         cepr01030102Form.setTotalSumInsuredListDisplay( CommonConst.DISPLAY_FALSE );
         cepr01030102Form.setPremiumListDisplay( CommonConst.DISPLAY_TRUE );
         cepr01030102Form.setHeaderInvestChoiceDisplay( CommonConst.DISPLAY_TRUE );
         cepr01030102Form.setPremiumCombinationListDisplay( CommonConst.DISPLAY_TRUE );
         cepr01030102Form.setButtonCountTotalSumInsuredDisplay( CommonConst.DISPLAY_TRUE );
         cepr01030102Form.setPremiumFurloughYearDisplay( CommonConst.DISPLAY_TRUE );
         cepr01030102Form.setPremiumFurloughYearListDisplay( CommonConst.DISPLAY_TRUE );
         cepr01030102Form.setButtonRiderAndButtonTopupDisplay( CommonConst.DISPLAY_TRUE );
         cepr01030102Form.setButtonRiderDisplay( CommonConst.DISPLAY_TRUE );
         cepr01030102Form.setButtonTopupDisplay( CommonConst.DISPLAY_TRUE );

         cepr01030102Form.setBasePremiumDisplay( CommonConst.DISPLAY_TRUE );
         cepr01030102Form.setTopUpPremiumDisplay( CommonConst.DISPLAY_TRUE );
         
         // setPremiumFurloughYearListDisplayHelperDisplay set to true if to display only list (not text box) 
         cepr01030102Form.setPremiumFurloughYearListDisplayHelperDisplay( CommonConst.DISPLAY_TRUE );

         cepr01030103Form.setPayorSpouseTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
         cepr01030103Form.setWaiver5Tpd10TpdDisplay( CommonConst.DISPLAY_FALSE );
         cepr01030103Form.setWaiver5Ci10CiDisplay( CommonConst.DISPLAY_FALSE );
         cepr01030103Form.setWaiverCiDisplay( CommonConst.DISPLAY_FALSE );
         cepr01030103Form.setWaiverTpdDisplay( CommonConst.DISPLAY_FALSE );
         cepr01030103Form.setPayorTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
         cepr01030103Form.setPayorCiDeathDisplay( CommonConst.DISPLAY_FALSE );
         cepr01030103Form.setPayor5Ci10CiDeathDisplay( CommonConst.DISPLAY_FALSE );
         cepr01030103Form.setPayor5Tpd10TpdDeathDisplay( CommonConst.DISPLAY_FALSE );
         cepr01030103Form.setPayorCiDisplay( CommonConst.DISPLAY_FALSE );
         cepr01030103Form.setHcpDisplay(  CommonConst.DISPLAY_TRUE);
         cepr01030103Form.setPayorCiDisplay( CommonConst.DISPLAY_FALSE );
         
         cepr01030103Form.setPayor5Tpd10CiDeathDisplay( CommonConst.DISPLAY_TRUE );
         cepr01030103Form.setWaiver5Tpd10CiDisplay(CommonConst.DISPLAY_TRUE );
         cepr01030103Form.setEarlyStageCi99Display(CommonConst.DISPLAY_TRUE );
         cepr01030103Form.setMedicalPlusDisplay(CommonConst.DISPLAY_FALSE );
         cepr01030103Form.setPayorTpdCiDeathDisplay(CommonConst.DISPLAY_TRUE );
         cepr01030103Form.setWaiverTpdCiDisplay(CommonConst.DISPLAY_TRUE );
         cepr01030103Form.setLadiesMedExpenseDisplay( CommonConst.DISPLAY_TRUE );
         cepr01030103Form.setScholarshipDisplay(CommonConst.DISPLAY_TRUE );
         cepr01030103Form.setHcpFamilyDisplay( CommonConst.DISPLAY_TRUE );
         cepr01030103Form.setLadiesInsDisplay( CommonConst.DISPLAY_TRUE );
         cepr01030103Form.setHcpLadiesDisplay( CommonConst.DISPLAY_TRUE );
         cepr01030103Form.setEkaSehatDisplay( CommonConst.DISPLAY_FALSE );//Decommission Rider SMAC/SMEX Oktober 2021 (NCR/2021/10/106)
         cepr01030103Form.setEkaSehatInnerLimitDisplay( CommonConst.DISPLAY_TRUE );
         cepr01030103Form.setHcpProviderDisplay( CommonConst.DISPLAY_TRUE );
         cepr01030103Form.setLadiesMedExpenseInnerLimitDisplay( CommonConst.DISPLAY_TRUE );
         cepr01030103Form.setMedicalPlusDisplay(CommonConst.DISPLAY_TRUE );
       //SMiLe Medical Extra Syariah Maret 2021
         cepr01030103Form.setEkaSehatExtraDisplay( CommonConst.DISPLAY_FALSE );//Decommission Rider SMAC/SMEX Oktober 2021 (NCR/2021/10/106)  
         
         if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
         {
         	  cepr01030103Form.setHcpProviderDisplay( CommonConst.DISPLAY_TRUE );
         	  cepr01030103Form.setEkaSehatDisplay( CommonConst.DISPLAY_FALSE );
         	  cepr01030103Form.setEkaSehatInnerLimitDisplay( CommonConst.DISPLAY_FALSE );
         	  cepr01030103Form.setMedicalPlusDisplay(CommonConst.DISPLAY_FALSE );
         }      
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040221Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPremiumListIsDisabled( CommonConst.DISABLED_FALSE );
//        restore investment choice
        cepr01030102Form.setFixIdrIsDisabled( CommonConst.DISABLED_FALSE  );
        cepr01030102Form.setDynamicIdrIsDisabled( CommonConst.DISABLED_FALSE  );
        cepr01030102Form.setAggresiveIdrIsDisabled( CommonConst.DISABLED_FALSE  );
        cepr01030102Form.setDynamicUsdIsDisabled( CommonConst.DISABLED_FALSE  );
        cepr01030102Form.setSecureUsdIsDisabled( CommonConst.DISABLED_FALSE  );

        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_FALSE );

        cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_FALSE );
        //cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
       
    }

    public void initReadOnlyForm()
    {
    	  logger.info( "*-*-*-* Cepr01040221Entry.initReadOnlyForm" );
          readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
    	logger.info( "*-*-*-* Cepr01040221Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
      //  cepr01030102Form.setPremiumFurloughYear( 10 );
      // cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_TAHUNAN );
        
      //  cepr01030102Form.setExtraPremium( new BigDecimal( "0" ) );
      //  cepr01030102Form.setExtraJob( new BigDecimal( "0" ) );     
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040221Entry.fillDefaultValueEachTimeFormCalled" );
 
        if( cepr01030102Form.getPremiumFurloughYear() == null){
        	cepr01030102Form.setPremiumFurloughYear( 10 );
        }
        if( cepr01030102Form.getPaymentModeCd() == null){
        	cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_TAHUNAN );
        }	
        
        
        
        
        cepr01030102Form.setPremiumOptionIsToRefreshPage( CommonConst.REFRESH_TRUE );
        cepr01030102Form.setFixIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setDynamicIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setAggresiveIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setCashFundIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setSecureUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setDynamicUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setPremiumCombinationList( getPremiumCombinationList( 10 ) );
        cepr01030102Form.setPremiumFurloughYearList( LookupList.getSequenceNumberList( 5, ASSURANCE_TERM - cepr01030101Form.getInsuredAge(), false ) );

        cepr01030102Form.setTermOfContract( ASSURANCE_TERM - cepr01030101Form.getInsuredAge() );
        cepr01030103Form.setPaRiskList( LookupList.getPaRiskList() );
        
        List<OptionVO> premiumList = null;
        
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_SEMESTERAN, Hardcode.PAY_MODE_CD_TRIWULANAN, Hardcode.PAY_MODE_CD_BULANAN } ) );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTermOfPayment( 10 );
        if( cepr01030102Form.getCurrencyCd() == null )
        {
            cepr01030102Form.setCurrencyCd( Hardcode.CUR_IDR_CD );
        }
        
        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 25000000, 100000000, 5000000 );
           
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 2500, 4000, 100 );        	
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
                               
	        cepr01030102Form.setLjiFixListDisplay( CommonConst.DISPLAY_FALSE );
	        cepr01030102Form.setLjiDynamicListDisplay( CommonConst.DISPLAY_FALSE );
	        cepr01030102Form.setLjiAggresiveListDisplay( CommonConst.DISPLAY_FALSE );
	            
	        cepr01030102Form.setLjiSecureUsdListDisplay( CommonConst.DISPLAY_FALSE );  
	        cepr01030102Form.setLjiDynamicUsdListDisplay( CommonConst.DISPLAY_FALSE );
	        
	        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X3 ){
	            cepr01030102Form.setLjiFixListDisplay( CommonConst.DISPLAY_TRUE );
	    		cepr01030102Form.setLjiFixList(LookupList.getJenisInvestFixExcellinkList());
	    		cepr01030102Form.setLjiDynamicListDisplay( CommonConst.DISPLAY_TRUE );
	    		cepr01030102Form.setLjiDynamicList(LookupList.getJenisInvestDynamicExcellinkList());
	    		cepr01030102Form.setLjiAggresiveListDisplay( CommonConst.DISPLAY_TRUE );
	    		cepr01030102Form.setLjiAggresiveList(LookupList.getJenisInvestAggresiveExcellinkList());
	                    
	    		cepr01030102Form.setLjiFixSimasList(LookupList.getJenisInvestFixSimasList());
	    		cepr01030102Form.setLjiDynamicSimasList(LookupList.getJenisInvestDynamicSimasList());
	    		cepr01030102Form.setLjiAggresiveSimasList(LookupList.getJenisInvestAggresiveSimasList());
	    		
	    		cepr01030102Form.setLjiCashFundDisplay( CommonConst.DISPLAY_TRUE );
	    		cepr01030102Form.setLjiCashFundList(LookupList.getJenisInvestCashFundInvestList());	    		
	    		cepr01030102Form.setCashFundIdrDisplay( CommonConst.DISPLAY_TRUE );
	            cepr01030102Form.setCashFundIdrListDisplay( CommonConst.DISPLAY_TRUE );	            	         
	            	            
            } 
	        
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
        	 cepr01030102Form.setFixIdrDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030102Form.setFixIdrListDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030102Form.setDynamicIdrDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030102Form.setDynamicIdrListDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030102Form.setAggresiveIdrDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030102Form.setAggresiveIdrListDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030102Form.setCashFundIdrDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030102Form.setCashFundIdrListDisplay( CommonConst.DISPLAY_FALSE );        	        	
            
             cepr01030102Form.setSecureUsdDisplay( CommonConst.DISPLAY_TRUE );
             cepr01030102Form.setSecureUsdListDisplay( CommonConst.DISPLAY_TRUE );
             cepr01030102Form.setDynamicUsdDisplay( CommonConst.DISPLAY_TRUE );
             cepr01030102Form.setDynamicUsdListDisplay( CommonConst.DISPLAY_TRUE );
        }
             
                
        cepr01030102Form.setPremiumList( premiumList );
        refreshBaseTopupPremium();
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040221Entry.validateCurrentPage" );

      //untuk perhitungan min up============================================
    	   	
//      compute max value for base premium
        /**NCR/2021/02/053	Changes Max. UP 1000 x**/
        int li_bisnis_det = LazyConverter.toInt( cepr01030102Form.getRightPartOfBusinessCd());
        int ar_bisnis = LazyConverter.toInt( cepr01030102Form.getLeftPartOfBusinessCd());
        String prod = ar_bisnis+"-"+li_bisnis_det;
		HashMap mapGroup = eproposalManager.selectMstConfig(6, "Setting", prod);
		
		if (mapGroup != null){
	      UlinkValidatorParVO parVO = new UlinkValidatorParVO();
	      parVO.setUnder5YearsBerkalaMaxBasePremiumVO( new AmountVO( 1000000000, 100000 ) );
	      parVO.setUnder5YearsSekaligusMaxBasePremiumVO( new AmountVO( 1000000000, 100000 ) );
	      parVO.setBeyond5YearsBerkalaMaxBasePremiumVO( new AmountVO( 1000000000, 100000 ) );
	      parVO.setBeyond5YearsSekaligusMaxBasePremiumVO( new AmountVO( 1000000000, 100000 ) );
	      super.validateCurrentPage( parVO );
        }else{
        	double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
        	double adec_premi = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        	double ldec_max;    	
        	
        	int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );  
        	  double li_kali = 1;
              if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TRIWULANAN )      //triwulan
                  li_kali = 4;
              else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEMESTERAN ) //semesteran
                  li_kali = 2;
              else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_BULANAN ) //bulanan
                  li_kali = 12;
              
            ldec_max = (li_kali*adec_premi* premiumCombinationPercent / 100) * 20;
              
        	switch( paymentModeCd )
            {
             case Hardcode.PAY_MODE_CD_TRIWULANAN: adec_premi = adec_premi * li_kali * 5;  break;
             case Hardcode.PAY_MODE_CD_SEMESTERAN: adec_premi = adec_premi * li_kali * 5;  break;
             case Hardcode.PAY_MODE_CD_BULANAN   : adec_premi = adec_premi * li_kali * 5; break;
             case Hardcode.PAY_MODE_CD_SEKALIGUS : adec_premi = adec_premi * 5; break;
             case Hardcode.PAY_MODE_CD_TAHUNAN   : adec_premi = adec_premi * 5; break;
                 default: break;
            }          
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                //idec_up = Math.max( idec_up, 15000000 );
            	adec_premi = FormatNumber.round(adec_premi/1000,0);
            	adec_premi = adec_premi * 1000;
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            { 
                //idec_up = Math.max( idec_up, 1500 );
            }
            double idec_rate = 1000;
            double idec_up = adec_premi * idec_rate / 1000;
            // count totalSumInsured with premiumCombinationPercent
            idec_up = idec_up * premiumCombinationPercent / 100;
            double minUp	= idec_up;
            //=====================================================================
        	       	
//        	double minUp	= ( LazyConverter.toDouble( cepr01030102Form.getBasePremium() ) / factor_percentage ) * 5;
            
        	 if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
             {
        		 if(minUp>7500000){
            		 minUp = minUp;
            	 }else{
            		 minUp = 7500000;
            	 }  
             }
             else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
             { 
            	 if(minUp>750){
            		 minUp = minUp;
            	 }else{
            		 minUp = 750;
            	 }
            	 ldec_max = ldec_max;
             }
        	        	
           	commonValidator.validateMinimumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() ), minUp, minUp );
        	commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() ), ldec_max, ldec_max ); 	
        }
      validateCurrentPageInCommon();
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040221Entry.validatePreviousPage" );
        commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 0, 70 );
        commonValidator.validatePolicyHolderAge(Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 85);
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040221Entry.processFormAfterSubmitBeforeValidation" );

        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
        refreshBaseTopupPremium();

        if( "onChangePremium".equals( cepr01030102Form.getTheEvent() )
            || "onPressButtonCountTotalSumInsured".equals( cepr01030102Form.getTheEvent() )
            || new BigDecimal( "0" ).equals( cepr01030102Form.getTotalSumInsured() )
            //|| "onPressButtonPreviewProposal".equals( cepr01030102Form.getTheEvent() ) 
         //   || "onPressButtonRider".equals( cepr01030102Form.getTheEvent() ) )
        ){
            double idec_rate;
            double adec_premi = premium;
            double idec_up;

            int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );

            switch( paymentModeCd )
            {
                case Hardcode.PAY_MODE_CD_TRIWULANAN: adec_premi = adec_premi * 4;  break;
                case Hardcode.PAY_MODE_CD_SEMESTERAN: adec_premi = adec_premi * 2;  break;
                case Hardcode.PAY_MODE_CD_BULANAN   : adec_premi = adec_premi * 12; break;
                default: break;
            }

            // I got it from n_prod_159 function of_set_premi
            idec_rate = 5000;
            idec_up = adec_premi * idec_rate / 1000;

            // count totalSumInsured with premiumCombinationPercent
            idec_up = idec_up * premiumCombinationPercent / 100;
            cepr01030102Form.setTotalSumInsured( new BigDecimal( idec_up ) );
            cepr01030103Form.setTermRiderAmount( new BigDecimal( idec_up ) );
        }
    }

    public void validateCurrentPageInCommon()
    {
    	logger.info( "*-*-*-* Cepr01040221Entry.validateCurrentPageInCommon" );
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double base_premium = LazyConverter.toDouble( cepr01030102Form.getBasePremium() );
        double minPremiumIdr;
        double minPremiumUsd;

        switch( cepr01030102Form.getPaymentModeCd() )
        {
            case Hardcode.PAY_MODE_CD_SEKALIGUS:
                minPremiumIdr = 5000000;
                minPremiumUsd = 500;
                break;
            case Hardcode.PAY_MODE_CD_TAHUNAN:
            	if(cepr01030102Form.getRightPartOfBusinessCd()==2 || cepr01030102Form.getRightPartOfBusinessCd()== 3){
	            	minPremiumIdr = 3000000;
	                minPremiumUsd = 300;
            	}else{
            		minPremiumIdr = 2400000;
                    minPremiumUsd = 240;
            	}
                break;
            case Hardcode.PAY_MODE_CD_SEMESTERAN:
            	if(cepr01030102Form.getRightPartOfBusinessCd()==2 || cepr01030102Form.getRightPartOfBusinessCd()== 3){
	            	minPremiumIdr = 1500000;
	                minPremiumUsd = 150;
            	}else{
            		minPremiumIdr = 1200000;
                    minPremiumUsd = 120;
            		
            	}                
                break;
            case Hardcode.PAY_MODE_CD_TRIWULANAN:
            	if(cepr01030102Form.getRightPartOfBusinessCd()==2 || cepr01030102Form.getRightPartOfBusinessCd()== 3){
	            	minPremiumIdr = 750000;
	                minPremiumUsd = 75;
            	}else{
            		 minPremiumIdr = 600000;
                     minPremiumUsd = 60;           		
            	}               
                break;
            case Hardcode.PAY_MODE_CD_BULANAN:
            	if(cepr01030102Form.getRightPartOfBusinessCd()==2 || cepr01030102Form.getRightPartOfBusinessCd()== 3){
	            	minPremiumIdr = 300000;
	                minPremiumUsd = 30;
            	}else{
            		minPremiumIdr = 100000;
                    minPremiumUsd = 10;
            	}                
                break;
            default:
                throw new RuntimeException( "*-*-*-* cepr01030102Form.getPaymentModeCd() not listed in Cepr01040216Entry.validateCurrentPage" );
        }

        if( cepr01030102Form.getPremium() != null )
        {
            commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );            
            if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {             	
            	if( new BigDecimal(100000).compareTo( new BigDecimal(premium) ) < 0 )
                {
                    errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.valueMax", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd( new BigDecimal(100000) ) }, null );
                }
            }
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

        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTermRiderFlag() ) )
        {
            commonValidator.validateMaximumMoney( Cepr01030102FormConst.ASSURANCE_PLAN_CD, cepr01030103Form.getTermRiderAmount(), cepr01030102Form.getTotalSumInsured(), cepr01030102Form.getTotalSumInsured(), "error.termRiderAmountMaxLimit" );
        }

        if( cepr01030102Form.getPremiumFurloughYearCd()!=5 && cepr01030102Form.getPremiumFurloughYearCd()!=10){
        	if( cepr01030103Form.getPayor5Tpd10CiDeathFlag() != CommonConst.CHECKED_FALSE ){
        		 if( cepr01030103Form.getPayor5Tpd10CiDeathFlag().equals(CommonConst.CHECKED_TRUE)){
        			 errors.rejectValue( Cepr01030102FormConst.PREMIUM_FURLOUGH_YEAR, "error.riderPayorWaiveronly5and10", null, null );        		
        	}}
        	if( cepr01030103Form.getWaiver5Tpd10CiFlag() != CommonConst.CHECKED_FALSE ){
       		 	if( cepr01030103Form.getWaiver5Tpd10CiFlag().equals(CommonConst.CHECKED_TRUE)){
       			 errors.rejectValue( Cepr01030102FormConst.PREMIUM_FURLOUGH_YEAR, "error.riderPayorWaiveronly5and10", null, null );        		
       		 }} 
        }
        
        if( cepr01030103Form.getPayor5Tpd10CiDeathFlag() != CommonConst.CHECKED_FALSE ){
      		 if( cepr01030103Form.getPayor5Tpd10CiDeathFlag().equals(CommonConst.CHECKED_TRUE)){
      			 if( cepr01030101Form.getPolicyHolderAge() < 17 || cepr01030101Form.getPolicyHolderAge() > 50 )
      	        {
      	            errors.rejectValue( Cepr01030102FormConst.ASSURANCE_PLAN_CD, "error.riderPayorPolicyHolderAgeInterval", new Object[]{ Integer.toString( 17 ), Integer.toString( 50 ) }, null );
      	        }   	      
      		 }}
       	if( cepr01030103Form.getWaiver5Tpd10CiFlag() != CommonConst.CHECKED_FALSE ){
      		 	if( cepr01030103Form.getWaiver5Tpd10CiFlag().equals(CommonConst.CHECKED_TRUE)){
      		 	 if( cepr01030101Form.getPolicyHolderAge() < 17 || cepr01030101Form.getPolicyHolderAge() > 59 )
       	      {
       	            errors.rejectValue( Cepr01030102FormConst.ASSURANCE_PLAN_CD, "error.riderWaiverInsuredAgeInterval", new Object[]{ Integer.toString( 17 ), Integer.toString( 59 ) }, null );
       	      }  
   	   		  if( !commonUsedBusiness.policyHolderIsInsured() )
   	          {
   	              errors.rejectValue( Cepr01030102FormConst.ASSURANCE_PLAN_CD, "error.riderWaiverpolicyHolderMustBeTheSamePersonAsInsured", null, null );	             
   	          }   		 	 
      		 }} 
        
        commonValidator.validateTenFoldedForAllInvestment();
        commonValidator.validatePremiumFurloughYear( Cepr01030102FormConst.PREMIUM_FURLOUGH_YEAR, 5, 100 );
    	
    }
    
    public static List<OptionVO> getHcpList()
    {
    	List<OptionVO> hcpList = null;
        if( hcpList == null )
        {
        	hcpList = new ArrayList<OptionVO>();

            OptionVO optionVO;
            optionVO = new OptionVO( "1", "R500" );
            hcpList.add( optionVO );
            optionVO = new OptionVO( "2", "R1000" );
            hcpList.add( optionVO );
            optionVO = new OptionVO( "3", "D50" );
            hcpList.add( optionVO );
            optionVO = new OptionVO( "4", "D100" );
            hcpList.add( optionVO );
        }
        return hcpList;
    }
    
    public static List<OptionVO> getPaRiskList()
    {
    	List<OptionVO> paRiskList = null;
        if( paRiskList == null )
        {
            paRiskList = new ArrayList<OptionVO>();

            OptionVO optionVO;
            optionVO = new OptionVO( "3", "Resiko ABD" );
            paRiskList.add( optionVO );
        }
        return paRiskList;
    }
    
    public static List<OptionVO> getPremiumCombinationList( int folded )
    {
        List<OptionVO> premiumCombinationList = new ArrayList<OptionVO>();

        OptionVO optionVO;
        optionVO = new OptionVO( "", "" );
        premiumCombinationList.add( optionVO );

        String value;
        String label;
        optionVO = new OptionVO( "100.00", "Premi Pokok 100%" );
        premiumCombinationList.add( optionVO );
        for( int i = 100 - folded; i > 0; i = i - folded )
        {
            value = i + ".00";
            label = "Premi Pokok " + i + "%, Premi Top-Up Berkala " + ( 100 - i ) + "%";
            optionVO = new OptionVO( value, label );
            premiumCombinationList.add( optionVO );
        }

        return premiumCombinationList;
    }

}