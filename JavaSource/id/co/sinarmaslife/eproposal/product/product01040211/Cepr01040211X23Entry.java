package id.co.sinarmaslife.eproposal.product.product01040211;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040211X11Entry
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : fadly
 * Version              : 1.0
 * Creation Date    	: Feb 10, 2009 2:49:55 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.lookup.LookupList;
import id.co.sinarmaslife.eproposal.model.vo.AmountVO;
import id.co.sinarmaslife.eproposal.model.vo.UlinkValidatorParVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cepr01040211X23Entry extends Cepr01040211Entry
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040211X23Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040211X11Entry constructor is called ..." );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040211X11Entry.initDisplayedForm" );
        super.initDisplayedForm();
        
        cepr01030102Form.setPremiumFurloughYearDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPremiumFurloughYearListDisplay( CommonConst.DISPLAY_FALSE );
        
        if( credentialsVO != null){
        	if(credentialsVO.getMsagId().equals(Hardcode.BANK_SINARMAS) || credentialsVO.getGroupId().equals(Hardcode.GROUP_BANK_SINARMAS) || credentialsVO.getGroupId().equals(Hardcode.GROUP_BANCASS) || credentialsVO.getGroupId().equals(Hardcode.GROUP_BANCASS_SUPPORT) ||
        			credentialsVO.getGroupId().equals(Hardcode.GROUP_AKTUARIA)) {
        		cepr01030102Form.setJenisListDisplay( CommonConst.DISPLAY_TRUE );
        	}
        	if( credentialsVO.getType() != null){
        		if(credentialsVO.getType().equals("bsm")){
        			cepr01030102Form.setJenisListDisplay( CommonConst.DISPLAY_TRUE );}
       		}
        }
      // #REQ: Desvinna@21/05/2014 => DiNon-Aktifkan krn strategi penjualan kredit mikro masih perlu dibahas kembali dengan Bank Sinarmas (Achmad H/02-05-2014)  
      //  if(cepr01030102Form.getJenisCd()!=null && cepr01030102Form.getJenisCd()==2){
      // 	cepr01030102Form.setButtonRiderDisplay( CommonConst.DISPLAY_FALSE );
      // }
      //--> IGA UPDATE RIDER SESUAI SPEK SIMAS POWER LINK 24082020
        cepr01030103Form.setPayor5Tpd10CiDeathDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setWaiver5Tpd10CiDisplay (CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setEkaSehatInnerLimitDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setHcpProviderDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setHcpDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setLadiesMedExpenseDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setLadiesMedExpenseInnerLimitDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setHcpLadiesDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setLadiesInsDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setScholarshipDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setEarlyStageCi99Display(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setPayorTpdCiDeathDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setWaiverTpdCiDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setEkaSehatExtraDisplay(CommonConst.DISPLAY_FALSE); //Decommission Rider SMAC/SMEX Oktober 2021 (NCR/2021/10/106)
        //<-- DONE    
        }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040211X11Entry.initDisabledForm" );
        super.initDisabledForm();
        cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPremiumFurloughYearIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setJenisListIsDisabled( CommonConst.DISABLED_FALSE );
        
        if(cepr01030102Form.getEducationPackageCd()>1){        	
        	cepr01030102Form.setTotalSumInsuredIsDisabled(CommonConst.DISABLED_TRUE);
        	cepr01030102Form.setPremiumCombinationListIsDisabled(CommonConst.DISABLED_TRUE);
        	cepr01030102Form.setDynamicIdrIsDisabled(CommonConst.DISABLED_TRUE );
        	cepr01030102Form.setLjiDynamicListIsDisabled( CommonConst.DISABLED_TRUE );
        }else{        	
        	cepr01030102Form.setTotalSumInsuredIsDisabled(CommonConst.DISABLED_FALSE);
        	cepr01030102Form.setPremiumCombinationListIsDisabled(CommonConst.DISABLED_FALSE );
        	cepr01030102Form.setDynamicIdrIsDisabled(CommonConst.DISABLED_FALSE );
        	cepr01030102Form.setLjiDynamicListIsDisabled( CommonConst.DISABLED_FALSE );
        }
      // #REQ: Desvinna@21/05/2014 => DiNon-Aktifkan krn strategi penjualan kredit mikro masih perlu dibahas kembali dengan Bank Sinarmas (Achmad H/02-05-2014)
      //  if(cepr01030102Form.getJenisCd()!=null && cepr01030102Form.getJenisCd()==2){
      //  	cepr01030102Form.setFixIdrIsDisabled(CommonConst.DISABLED_TRUE );        	
      //  }else{
      //  	cepr01030102Form.setFixIdrIsDisabled(CommonConst.DISABLED_FALSE );       
      //  }
        
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040211X11Entry.initReadOnlyForm" );
        super.initReadOnlyForm();
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040211X11Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPremiumFurloughYear( 1 );
        cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_TAHUNAN );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040211X11Entry.fillDefaultValueEachTimeFormCalled" );
        super.fillDefaultValueEachTimeFormCalled();
                
        cepr01030102Form.setJenisList( comboLookupBusiness.getJenisOptionVOBSIMList(false));
        
        cepr01030102Form.setFixIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 } ) );
        cepr01030102Form.setDynamicIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 } ) );
        cepr01030102Form.setAggresiveIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 } ) );
        cepr01030102Form.setSecureUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 } ) );
        cepr01030102Form.setDynamicUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 } ) );
       
        if( cepr01030102Form.getSmileLadiesPackageCd() != null && cepr01030102Form.getSmileLadiesPackageCd() > 1 ||
        		cepr01030102Form.getEducationPackageCd() != null && cepr01030102Form.getEducationPackageCd().equals(Hardcode.EDUCATION_PAKET_SIMPOL_BSM) ){
        	cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        } else {
        	cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD } ) );
        }
//        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{Hardcode.PAY_MODE_CD_TAHUNAN } ) );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_SEMESTERAN, Hardcode.PAY_MODE_CD_TRIWULANAN, Hardcode.PAY_MODE_CD_BULANAN } ) );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTermOfPayment( 10 );
        cepr01030102Form.setTermOfContract( 100 - cepr01030101Form.getInsuredAge() );
        
        
        //cepr01030102Form.setPremiumCombinationCd( new BigDecimal( "100" ) );
       
        // #REQ: Desvinna@21/05/2014 => DiNon-Aktifkan krn strategi penjualan kredit mikro masih perlu dibahas kembali dengan Bank Sinarmas (Achmad H/02-05-2014)
        //if(cepr01030102Form.getJenisCd()!=null && cepr01030102Form.getJenisCd()==2 && cepr01030101Form.getInsuredAge()>40){
    	//	cepr01030102Form.setPremiumCombinationList( getPremiumCombinationList(10).subList(2,9));
       // }else{
    		cepr01030102Form.setPremiumCombinationList( getPremiumCombinationNewList( 20 ) ); 
       // } 
      // #REQ	
    		
        //cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination10FoldedList().subList( 1, 2 ) );    		
        refreshBaseTopupPremium();       
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040211X11Entry.validateCurrentPage" );
//        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
//        double minPremiumIdr = 4000000;
//        double minPremiumUsd = 400;
//        commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double minPremiumIdr;
        double minPremiumUsd;
        double li_kali = 1;
        if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_TRIWULANAN )      //triwulan
            li_kali = 4;
        else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEMESTERAN ) //semesteran
            li_kali = 2;
        else if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_BULANAN ) //bulanan
            li_kali = 12;

        switch( cepr01030102Form.getPaymentModeCd() )
        {
        case Hardcode.PAY_MODE_CD_SEKALIGUS:
            minPremiumIdr = 10000000;
            minPremiumUsd = 1000;
            break;
        case Hardcode.PAY_MODE_CD_TAHUNAN:
            minPremiumIdr = 5000000;
            minPremiumUsd = 500;
            break;
        case Hardcode.PAY_MODE_CD_SEMESTERAN:
            minPremiumIdr = 2625000;
            minPremiumUsd = 262.5;
            break;
        case Hardcode.PAY_MODE_CD_TRIWULANAN:
            minPremiumIdr = 1350000;
            minPremiumUsd = 135;
            break;
        case Hardcode.PAY_MODE_CD_BULANAN:
            minPremiumIdr = 500000;
            minPremiumUsd = 50;
            break;
        default:
    	throw new RuntimeException( "*-*-*-* cepr01030102Form.getPaymentModeCd() not listed in Cepr01040201Entry.validateCurrentPage" );
    }


        if( cepr01030102Form.getPremium() != null )
        {
               double factor_percentage = 1;

               int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );
               
               switch( paymentModeCd )
               {
	               case Hardcode.PAY_MODE_CD_TRIWULANAN: factor_percentage = 0.27;  break;
	               case Hardcode.PAY_MODE_CD_SEMESTERAN: factor_percentage = 0.525;  break;
	               case Hardcode.PAY_MODE_CD_BULANAN   : factor_percentage = 0.1; break;
                   default: break;
               }
               
               double maxUp = 0;  
               if(cepr01030102Form.getRightPartOfBusinessCd()==Cepr01040211Mapper.X23 || cepr01030102Form.getRightPartOfBusinessCd()==Cepr01040211Mapper.X20 || cepr01030102Form.getRightPartOfBusinessCd()==Cepr01040211Mapper.X11){ //SIMAS POWER LINK(120 X11)
            	 //MAKS UP (lama) berdasarkan No. 084/AJS-SK/VIII/2011
            	/*
            	double factor = 0;
           		if( cepr01030101Form.getInsuredAge() >= 1 && cepr01030101Form.getInsuredAge() <= 19 )factor = 50;
           		else if( cepr01030101Form.getInsuredAge() >= 20 && cepr01030101Form.getInsuredAge() <= 29 )factor = 40;
           		else if( cepr01030101Form.getInsuredAge() >= 30 && cepr01030101Form.getInsuredAge() <= 39 )factor = 30;
           		else if( cepr01030101Form.getInsuredAge() >= 40 && cepr01030101Form.getInsuredAge() <= 49 )factor = 20;
           		else if( cepr01030101Form.getInsuredAge() >= 50 && cepr01030101Form.getInsuredAge() <= 70 )factor = 10;
           		maxUp = ( LazyConverter.toDouble( cepr01030102Form.getBasePremium() ) / factor_percentage ) * factor; 
           		commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() ), maxUp, maxUp );
                */
            	//DIGUNAKAN TABEL MAXIMUM UP SIMAS POWER LINK Lampiran III : SK-SE No. 117/AJS-SE/X/2014
           		double factor = 0;
           		if     ( cepr01030101Form.getInsuredAge() >= 1  && cepr01030101Form.getInsuredAge() <= 15 )factor = 220;
            	else if( cepr01030101Form.getInsuredAge() >= 16 && cepr01030101Form.getInsuredAge() <= 20 )factor = 240;
            	else if( cepr01030101Form.getInsuredAge() >= 21 && cepr01030101Form.getInsuredAge() <= 25 )factor = 200;
            	else if( cepr01030101Form.getInsuredAge() >= 26 && cepr01030101Form.getInsuredAge() <= 30 )factor = 180;
            	else if( cepr01030101Form.getInsuredAge() >= 31 && cepr01030101Form.getInsuredAge() <= 35 )factor = 160;
            	else if( cepr01030101Form.getInsuredAge() >= 36 && cepr01030101Form.getInsuredAge() <= 40 )factor = 150;
            	else if( cepr01030101Form.getInsuredAge() >= 41 && cepr01030101Form.getInsuredAge() <= 45 )factor = 140;
            	else if( cepr01030101Form.getInsuredAge() >= 46 && cepr01030101Form.getInsuredAge() <= 50 )factor = 100;
            	else if( cepr01030101Form.getInsuredAge() >= 51 && cepr01030101Form.getInsuredAge() <= 55 )factor = 80;
            	else if( cepr01030101Form.getInsuredAge() >= 56 && cepr01030101Form.getInsuredAge() <= 70 )factor = 50;
               	maxUp = LazyConverter.toDouble( cepr01030102Form.getBasePremium() ) * li_kali * factor; 
                BigDecimal maksUp = new BigDecimal("500000000");
            	BigDecimal upFix = cepr01030102Form.getTotalSumInsuredFix();
            	if( cepr01030102Form.getCurrencyCd().equals("02") ) maksUp = new BigDecimal("50000");
            	if( cepr01030101Form.getInsuredAge() <= 5 && cepr01030102Form.getTotalSumInsured().compareTo( maksUp ) > 0  ){
                	commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, cepr01030102Form.getTotalSumInsured(), new BigDecimal("500000000"),  new BigDecimal("50000"));
                }else{
                	commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() ), maxUp, maxUp );
                }            
               }
            
        	
        	//untuk perhitungan min up============================================
        	double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
        	double adec_premi = LazyConverter.toDouble( cepr01030102Form.getPremium() );
            switch( paymentModeCd )
            {
            case Hardcode.PAY_MODE_CD_TRIWULANAN: adec_premi = (adec_premi /2) / 0.270 *10;  break;
            case Hardcode.PAY_MODE_CD_SEMESTERAN: adec_premi = (adec_premi /2) / 0.525 *10;  break;
            case Hardcode.PAY_MODE_CD_BULANAN   : adec_premi = adec_premi *10 * 5; break;
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
            }
       	        	
          	commonValidator.validateMinimumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() ), minUp, minUp );
        	  
        	if(cepr01030102Form.getJenisCd() == 1 && cepr01030102Form.getPaymentModeCd()==Hardcode.PAY_MODE_CD_BULANAN && cepr01030102Form.getEducationPackageCd() == 0){
            	minPremiumIdr = 300000;
                minPremiumUsd = 30;
            	commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );
        	}/*
        	// #REQ: Desvinna@21/05/2014 => DiNon-Aktifkan krn strategi penjualan kredit mikro masih perlu dibahas kembali dengan Bank Sinarmas (Achmad H/02-05-2014)
        	else if(cepr01030102Form.getJenisCd() == 2){
          		switch( cepr01030102Form.getPaymentModeCd() )
          		{
          		case Hardcode.PAY_MODE_CD_BULANAN:
          			minPremiumIdr = 100000;
                      minPremiumUsd = 10;
          			break;
                  case Hardcode.PAY_MODE_CD_TAHUNAN:
                  	minPremiumIdr = 1000000;
                      minPremiumUsd = 100;
          			break;
                  //default: break;	
                  default:
                  	throw new RuntimeException( "*-*-*-* (Jenis=Nasabah Kredit Mikro BSIM)--cepr01030102Form.getPaymentModeCd() not supported in Cepr01040211X22Entry.validateCurrentPage" );
                  }
          		commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );            	
           }*/
           else{        	   
        	   if(cepr01030102Form.getEducationPackageCd().equals(Hardcode.EDUCATION_PAKET_SIMPOL_BSM)){
        		 //Adrian@24/03/2015: Paket SIMPOL Education BSM (NEW!!) -Edy Kohar
        		   switch( cepr01030102Form.getPaymentModeCd() )
             		{
             		case Hardcode.PAY_MODE_CD_BULANAN:
             			minPremiumIdr = 1000000;                        
             			break;
                     case Hardcode.PAY_MODE_CD_TAHUNAN:
                     	minPremiumIdr = 12000000;
             			break;
                     case Hardcode.PAY_MODE_CD_TRIWULANAN:
                      	minPremiumIdr = 3000000;
              			break;
                     case Hardcode.PAY_MODE_CD_SEMESTERAN:
                       	minPremiumIdr = 6000000;
               			break;	
                     default: break;
                     }
        	   } 
        	   commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );
           }
        	
            commonValidator.validateInvestmentSumIs100Percent();
        }
        else
        {
            errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.formEmpty", null, null );
        }
        UlinkValidatorParVO parVO = new UlinkValidatorParVO();
//        parVO.setUnder5YearsBerkalaMaxBasePremiumVO( new AmountVO( 0.0, 0.0 ) );
//        parVO.setUnder5YearsSekaligusMaxBasePremiumVO( new AmountVO( 0.0, 0.0 ) );
//        parVO.setBeyond5YearsBerkalaMaxBasePremiumVO( new AmountVO( 0.0, 0.0 ) );
//        parVO.setBeyond5YearsSekaligusMaxBasePremiumVO( new AmountVO( 0.0, 0.0 ) );
        super.validateCurrentPage( parVO );
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040211X11Entry.processFormAfterSubmitBeforeValidation" );
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
        super.processFormAfterSubmitBeforeValidation();
        refreshBaseTopupPremium();

        if( "onChangePremium".equals( cepr01030102Form.getTheEvent() )
            || "onPressButtonCountTotalSumInsured".equals( cepr01030102Form.getTheEvent() )
            || cepr01030102Form.getTotalSumInsured().equals( new BigDecimal( "0" ) ) )
        {

            double idec_rate;
            double adec_premi = LazyConverter.toDouble( cepr01030102Form.getPremium() );
            double idec_up;

            int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );
            /*
            switch( paymentModeCd )
            {
            
            case Hardcode.PAY_MODE_CD_TRIWULANAN: adec_premi = (adec_premi /2) / 0.270 *10;  break;
            case Hardcode.PAY_MODE_CD_SEMESTERAN: adec_premi = (adec_premi /2) / 0.525 *10;  break;
            case Hardcode.PAY_MODE_CD_BULANAN   : adec_premi = adec_premi *10 * 5; break;
            case Hardcode.PAY_MODE_CD_SEKALIGUS : adec_premi = adec_premi * 5; break;
            case Hardcode.PAY_MODE_CD_TAHUNAN   : adec_premi = adec_premi * 5; break;
                default: break;
            }*/
            //DIGUNAKAN MAXIMUM UP SIMAS POWER LINK Lampiran III : SK. Direksi No. 003/AJS-SK/I/2013
            switch( paymentModeCd )
            {
                case Hardcode.PAY_MODE_CD_TRIWULANAN: adec_premi = adec_premi * 4 *5;  break;
                case Hardcode.PAY_MODE_CD_SEMESTERAN: adec_premi = adec_premi * 2 * 5;  break;
                case Hardcode.PAY_MODE_CD_BULANAN   : adec_premi = adec_premi * 12 * 5; break;
                case Hardcode.PAY_MODE_CD_TAHUNAN   : adec_premi = adec_premi * 5; break;
                default: break;
            }
            
            
            // I got it from n_prod_159 function of_set_premi
            
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
            idec_rate = 1000;
            idec_up = adec_premi * idec_rate / 1000;
            // count totalSumInsured with premiumCombinationPercent
            idec_up = idec_up * premiumCombinationPercent / 100;
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	if(idec_up <= 7500000){
                	idec_up = 7500000;
                }
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	if(idec_up <= 750){
                	idec_up = 750;
                }
            }            
            cepr01030102Form.setTotalSumInsured( new BigDecimal( idec_up ) );
            cepr01030102Form.setTotalSumInsuredFix(new BigDecimal( idec_up ) );  
         }        
      
        if(cepr01030102Form.getEducationPackageCd().equals(Hardcode.EDUCATION_PAKET_SIMPOL_BSM)){        	
        	cepr01030102Form.setTotalSumInsured(new BigDecimal("100000000"));
        	cepr01030102Form.setDynamicIdr(new BigDecimal(100.0));
        	cepr01030102Form.setLjiDynamicCd("36");
       }
      // #REQ: Desvinna@21/05/2014 => DiNon-Aktifkan krn strategi penjualan kredit mikro masih perlu dibahas kembali dengan Bank Sinarmas (Achmad H/02-05-2014)  
      // if(cepr01030102Form.getJenisCd()!=null && cepr01030102Form.getJenisCd()==2){
      //	   cepr01030102Form.setFixIdr(new BigDecimal(100.0));
      // }
    }
    
    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040213Entry.validatePreviousPage" );
        super.validatePreviousPage();
    }
    
    public List<OptionVO> getPremiumCombinationNewList( int folded )
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
}