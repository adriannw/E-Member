package id.co.sinarmaslife.eproposal.product.product01040219;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040201
 * Program Name   		: Cepr01040201Entry
 * Description         	: Eka Link Family (159)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 22, 2007 10:03:20 AM
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
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000UlinkEntry;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000Generator;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.eproposal.model.vo.UlinkValidatorParVO;
import id.co.sinarmaslife.eproposal.model.vo.AmountVO;
import id.co.sinarmaslife.eproposal.product.product01040208.Cepr01040208Mapper;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.LazyConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Cepr01040219Entry extends Cepr01040000UlinkEntry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );
    
    public Cepr01040219Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        setDownloadUrl( "wepr01040219.pdf" );
    } 

    public void initDisplayedForm()
    {
        displayStandardForm();
        cepr01030102Form.setPremiumListDisplay( CommonConst.DISPLAY_TRUE );        

        //(Helpdesk 56020): Bridge Agency
        if( Cepr01040219Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X9 == cepr01030102Form.getRightPartOfBusinessCd())
        {
        	cepr01030102Form.setJenisListDisplay( CommonConst.DISPLAY_TRUE );
        }
        cepr01030102Form.setHeaderInvestChoiceDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumCombinationListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonCountTotalSumInsuredDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumFurloughYearDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumFurloughYearListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonRiderAndButtonTopupDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonRiderDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonTopupDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setHcpDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setEkaSehatInnerLimitDisplay( CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setHcpLadiesDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setLadiesInsDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setHcpProviderDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setHcpFamilyDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setLadiesMedExpenseDisplay( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setLadiesMedExpenseInnerLimitDisplay( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setPayorSpouseTpdDeathDisplay( CommonConst.DISPLAY_TRUE );
        
        if(  cepr01030102Form.getRightPartOfBusinessCd() < Cepr01040219Mapper.X5){
        /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
        	cepr01030103Form.setTerm5575RiderDisplay( CommonConst.DISPLAY_TRUE ); 
        	cepr01030103Form.setTermRiderDisplay( CommonConst.DISPLAY_FALSE );
        }else{
        	 cepr01030103Form.setTerm5575RiderDisplay( CommonConst.DISPLAY_FALSE ); 
             cepr01030103Form.setTermRiderDisplay( CommonConst.DISPLAY_TRUE );
        }
        
        if( Cepr01040219Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() ||Cepr01040219Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd())
        {
        	 cepr01030103Form.setWaiverTpdCiDisplay( CommonConst.DISPLAY_TRUE);
        	 cepr01030103Form.setPayorTpdCiDeathDisplay( CommonConst.DISPLAY_TRUE );
        	 cepr01030103Form.setPayor5Tpd10CiDeathDisplay( CommonConst.DISPLAY_TRUE );
        	 cepr01030103Form.setWaiver5Tpd10CiDisplay( CommonConst.DISPLAY_TRUE);
        	 cepr01030102Form.setEducationPackageListDisplay( CommonConst.DISPLAY_FALSE );
        	 cepr01030103Form.setWaiverTpdDisplay(CommonConst.DISPLAY_FALSE );
        	 cepr01030103Form.setWaiverCiDisplay(CommonConst.DISPLAY_FALSE );
        	 cepr01030103Form.setScholarshipDisplay( CommonConst.DISPLAY_TRUE );  
        }
        else if( Cepr01040219Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X5 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X7 == cepr01030102Form.getRightPartOfBusinessCd()  )
        {
        	 cepr01030103Form.setPayorTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030103Form.setPayorCiDeathDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030103Form.setPayorCiDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030103Form.setPayorSpouseTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030103Form.setWaiverTpdDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030103Form.setWaiverCiDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030103Form.setWaiverTpdCiDisplay( CommonConst.DISPLAY_FALSE);
             cepr01030103Form.setPayorTpdCiDeathDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030103Form.setPayor5Tpd10CiDeathDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030103Form.setWaiver5Tpd10CiDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030102Form.setEducationPackageListDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030103Form.setScholarshipDisplay( CommonConst.DISPLAY_TRUE );            
         }
        else if( Cepr01040219Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X6 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X8 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X9 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
        	 cepr01030103Form.setPayorTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030103Form.setPayorCiDeathDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030103Form.setPayorCiDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030103Form.setPayorSpouseTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030103Form.setWaiverTpdDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030103Form.setWaiverCiDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030103Form.setWaiverTpdCiDisplay( CommonConst.DISPLAY_TRUE );
             cepr01030103Form.setPayorTpdCiDeathDisplay( CommonConst.DISPLAY_TRUE );
             cepr01030103Form.setPayor5Tpd10CiDeathDisplay( CommonConst.DISPLAY_TRUE );
             cepr01030103Form.setWaiver5Tpd10CiDisplay( CommonConst.DISPLAY_TRUE);
             cepr01030102Form.setEducationPackageListDisplay( CommonConst.DISPLAY_FALSE );
             cepr01030103Form.setScholarshipDisplay( CommonConst.DISPLAY_TRUE );           
        }
                       
        if( cepr01030102Form.getCurrencyCd() != null ){
	        if( cepr01030102Form.getCurrencyCd().equals( "01" ) )// rupiah
	        {
	        	 cepr01030103Form.setEkaSehatInnerLimitDisplay( CommonConst.DISPLAY_TRUE);
	        	 cepr01030103Form.setEkaSehatDisplay( CommonConst.DISPLAY_FALSE ); //Decommission Rider SMAC/SMEX Oktober 2021 (NCR/2021/10/106)
	        	 cepr01030103Form.setEkaSehatExtraDisplay( CommonConst.DISPLAY_FALSE ); //Decommission Rider SMAC/SMEX Oktober 2021 (NCR/2021/10/106)
	        	 cepr01030103Form.setScholarshipDisplay(CommonConst.DISPLAY_TRUE );
	        	 cepr01030103Form.setBabyDisplay(CommonConst.DISPLAY_TRUE );
	        	 cepr01030103Form.setMedicalPlusDisplay(CommonConst.DISPLAY_TRUE );
	        }else{
	        	 cepr01030103Form.setEkaSehatInnerLimitDisplay( CommonConst.DISPLAY_FALSE);
	        	 cepr01030103Form.setEkaSehatDisplay( CommonConst.DISPLAY_FALSE );
	        	 cepr01030103Form.setScholarshipDisplay(CommonConst.DISPLAY_FALSE );	
	        	 cepr01030103Form.setBabyDisplay(CommonConst.DISPLAY_FALSE );
	        	 cepr01030103Form.setMedicalPlusDisplay(CommonConst.DISPLAY_FALSE );
	        }
        }
        
        if( Cepr01040219Mapper.X7 == cepr01030102Form.getRightPartOfBusinessCd() ||Cepr01040219Mapper.X8 == cepr01030102Form.getRightPartOfBusinessCd())
        {
        	cepr01030103Form.setBabyDisplay(CommonConst.DISPLAY_FALSE );        	
        }
        
        if( Cepr01040219Mapper.X5 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X6 == cepr01030102Form.getRightPartOfBusinessCd() ||
        		Cepr01040219Mapper.X9 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
        	cepr01030103Form.setEarlyStageCi99Display(CommonConst.DISPLAY_FALSE);
        	if( Cepr01040219Mapper.X9 == cepr01030102Form.getRightPartOfBusinessCd() ) {
        		cepr01030103Form.setScholarshipDisplay( CommonConst.DISPLAY_FALSE );
        		 cepr01030102Form.setPremiumListDisplay( CommonConst.DISPLAY_FALSE );   
        	}
            cepr01030103Form.setBabyDisplay(CommonConst.DISPLAY_FALSE);
            cepr01030103Form.setMedicalPlusDisplay(CommonConst.DISPLAY_FALSE);
        }
        
        cepr01030102Form.setBasePremiumDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setTopUpPremiumDisplay( CommonConst.DISPLAY_TRUE );
     
        if( credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals(Hardcode.GROUP_MNC)){
        	 cepr01030102Form.setEducationPackageListDisplay( CommonConst.DISPLAY_FALSE );
        	
        	  if( Cepr01040219Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X5 == cepr01030102Form.getRightPartOfBusinessCd() )
              {
        		  cepr01030102Form.setSpecialOfferListDisplay( CommonConst.DISPLAY_FALSE );
              }
              else if( Cepr01040219Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X6 == cepr01030102Form.getRightPartOfBusinessCd() )
              {
            	  cepr01030102Form.setSpecialOfferListDisplay( CommonConst.DISPLAY_TRUE );
              }
        }else{       	
        	if( cepr01030102Form.getLeftPartOfBusinessCd() == 159 ){// ekalink family
	        	 if( Cepr01040219Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
	             {
	        		 cepr01030102Form.setSmileLadiesPackageListDisplay( CommonConst.DISPLAY_FALSE );
	             }
	             else if( Cepr01040219Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() )
	             {
	            	 cepr01030102Form.setSmileLadiesPackageListDisplay( CommonConst.DISPLAY_TRUE );
	             }
        	}
        }
    }

    public void initDisabledForm()
    {
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
        
//		 (Helpdesk 56020): Bridge Agency
        cepr01030102Form.setJenisListIsDisabled( CommonConst.DISABLED_FALSE );   
        
        
        if( Cepr01040219Mapper.X9 == cepr01030102Form.getRightPartOfBusinessCd() ){
        	boolean TRUE = true;
        	disabledRiderForm( TRUE );      	
        }
        double premi_pokok= LazyConverter.toDouble( cepr01030102Form.getBasePremium() ); //CR#: NCR/2020/08/019 FLAG VIP (IGA)
        double premi= LazyConverter.toDouble( cepr01030102Form.getPremium() );
        int cara_bayar =  LazyConverter.toInt( cepr01030102Form.getPaymentModeCd() );
        
       switch( cara_bayar )
       {
                  case Hardcode.PAY_MODE_CD_TRIWULANAN: premi = premi * 4;  break;
                  case Hardcode.PAY_MODE_CD_SEMESTERAN: premi = premi * 2;  break;
                  case Hardcode.PAY_MODE_CD_BULANAN   : premi = premi * 12; break;
                  default: break;
        }        
       switch( cara_bayar )
       {
                  case Hardcode.PAY_MODE_CD_TRIWULANAN: premi_pokok = premi_pokok * 4;  break;
                  case Hardcode.PAY_MODE_CD_SEMESTERAN: premi_pokok = premi_pokok * 2;  break;
                  case Hardcode.PAY_MODE_CD_BULANAN   : premi_pokok = premi_pokok * 12; break;
                  default: break;
        }        
        
        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
		if(premi_pokok >= 100000000 && cara_bayar == Hardcode.PAY_MODE_CD_TAHUNAN){
			cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_FALSE);  
		}else if(premi_pokok == 0 && premi >= 100000000 && cara_bayar == Hardcode.PAY_MODE_CD_TAHUNAN){
			cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_FALSE);
		}else if(premi_pokok >= 100000000 && cara_bayar != Hardcode.PAY_MODE_CD_TAHUNAN){
			cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_FALSE);
		}else if(premi_pokok == 0 && premi >= 100000000 && cara_bayar != Hardcode.PAY_MODE_CD_TAHUNAN){
			cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_FALSE);
		}
		else{
			cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_TRUE );  
			cepr01030102Form.setFlagOfVIP(CommonConst.CHECKED_FALSE);
		}
        }  else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        { 
		if(premi_pokok >= 10000 && cara_bayar == Hardcode.PAY_MODE_CD_TAHUNAN){
			cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_FALSE );  
		}else if(premi_pokok == 0 && premi >= 10000 && cara_bayar == Hardcode.PAY_MODE_CD_TAHUNAN){
			cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_FALSE);
		}else if(premi_pokok >= 10000 && cara_bayar != Hardcode.PAY_MODE_CD_TAHUNAN){
			cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_FALSE);
		}else if(premi_pokok == 0 && premi >= 10000 && cara_bayar != Hardcode.PAY_MODE_CD_TAHUNAN){
			cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_FALSE);
		}
		else{
			cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_TRUE ); 
			cepr01030102Form.setFlagOfVIP(CommonConst.CHECKED_FALSE);
		}
        } //DONE
        if( credentialsVO.getIsVIP()==true){
        //	cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_TRUE );  
        //	cepr01030102Form.setFlagOfVIP(CommonConst.CHECKED_TRUE);
        	
        }
        
    }

    public void initReadOnlyForm()
    {
        readOnlyAllForm( false );
        if( Cepr01040219Mapper.X9 == cepr01030102Form.getRightPartOfBusinessCd() ){ 
	        cepr01030102Form.setPremiumReadOnly( CommonConst.READ_ONLY_TRUE );
	        cepr01030102Form.setTotalSumInsuredReadOnly( CommonConst.READ_ONLY_TRUE );
        }
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
    	cepr01030102Form.setSmileLadiesPackageList( comboLookupBusiness.smileLadiesPackageList() );
        if( Cepr01040219Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() ||  Cepr01040219Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X5 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X7 == cepr01030102Form.getRightPartOfBusinessCd() ){
            cepr01030102Form.setPremiumFurloughYear( 1 );
            cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_SEKALIGUS );
        
        }
        else if( Cepr01040219Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X6 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X8 == cepr01030102Form.getRightPartOfBusinessCd() ) {
            cepr01030102Form.setPremiumFurloughYear( 10 );
            cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_TAHUNAN );
        } 
     
    
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
    	if( cepr01030102Form.getTheEvent().equals("onChangesmileLadiesPackageCd") ){
    		if( cepr01030102Form.getSmileLadiesPackageCd() != null && cepr01030102Form.getSmileLadiesPackageCd() > 1 ){
    			cepr01030102Form.setPremiumCombinationCd(new BigDecimal("60") );
    		}
    	}
    	// cepr01030103Form.setTermRiderAmount( cepr01030102Form.getTotalSumInsured() );
        if( Cepr01040219Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X5 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X7 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X9 == cepr01030102Form.getRightPartOfBusinessCd())
        {
        	if(cepr01030102Form.getSpecialOfferCd() != null)cepr01030102Form.setSpecialOfferCd(0);
        }
        cepr01030102Form.setPremiumOptionIsToRefreshPage( CommonConst.REFRESH_TRUE );
        cepr01030102Form.setFixIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setDynamicIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setAggresiveIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setCashFundIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setExcellinkEquityIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setSecureUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setDynamicUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        
        if( Cepr01040219Mapper.X7 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X8 == cepr01030102Form.getRightPartOfBusinessCd()
        || Cepr01040219Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() ){
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
            
            cepr01030102Form.setLjiExcellinkEquityDisplay( CommonConst.DISPLAY_TRUE );
    		cepr01030102Form.setLjiExcellinkEquityList(LookupList.getJenisInvestExcellinkEquityInvestList());	 
            cepr01030102Form.setExcellinkEquityIdrDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setExcellinkEquityListDisplay( CommonConst.DISPLAY_TRUE );
    		
        }
        
        if( cepr01030102Form.getPremium() != null  && cepr01030102Form.getPaymentModeCd().equals( Hardcode.PAY_MODE_CD_BULANAN ) ){
        	double premi = LazyConverter.toDouble( cepr01030102Form.getPremium() );
    		
        	if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            { 
	        		if( Cepr01040219Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() ){
	        			cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination5FoldedList());
	        		}else if( Cepr01040219Mapper.X6 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X8 == cepr01030102Form.getRightPartOfBusinessCd()){
	        			
	    	            
	        			cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination5FoldedList().subList( 0, 15 ) );
	        			if( premi >= 150000.0 && premi < 250000 ){
	        			   cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombinationFoldedStart70List(5).subList(2, 11) );          			
	        		   }
	        		}else{
	        			cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombinationFoldedList(1).subList(0, 96));
	        		}
            			if( premi >= 150000.0 && premi < 250000 ){
            				cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombinationFoldedStart70List(5).subList(2, 11) );
            			}
            	
            	
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	if( Cepr01040219Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() ){
        			cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination5FoldedList() );
        		}else if( Cepr01040219Mapper.X6 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X8 == cepr01030102Form.getRightPartOfBusinessCd()){
        			cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination5FoldedList().subList( 0, 15 ) );
        			if( premi >= 15.0 && premi < 25 ){
        			   cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombinationFoldedStart70List(5).subList(2, 11) );          			
        		   }
        		}else{
        			cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombinationFoldedList(1).subList(0, 96));
        		}
        			if( premi >= 15.0 && premi < 25 ){
        				cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombinationFoldedStart70List(5).subList(2, 11) );
            		}
        		
            }
        	
        }else{
        	if( Cepr01040219Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X9 == cepr01030102Form.getRightPartOfBusinessCd()){
        		cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination5FoldedList() );
        	}else if( Cepr01040219Mapper.X6 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X8 == cepr01030102Form.getRightPartOfBusinessCd()){
    			cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination5FoldedList().subList( 0, 15 ) );
        	}else{
        		cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombinationFoldedList(1).subList(0, 96));
        	}
        }
       
        cepr01030102Form.setPremiumFurloughYearList( LookupList.getSequenceNumberList( 0, 20, 5, false ) );

        cepr01030102Form.setTermOfContract( 99 - cepr01030101Form.getInsuredAge() );

        List<OptionVO> premiumList = null;
        if( Cepr01040219Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X5 == cepr01030102Form.getRightPartOfBusinessCd() 
        		|| Cepr01040219Mapper.X7 == cepr01030102Form.getRightPartOfBusinessCd() )
        {        	
            cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_TRUE );
            cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
        	if(credentialsVO.getMsagId() != null && "999993".equals(credentialsVO.getMsagId())){
        		cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        	}        	
        	else if( cepr01030102Form.getSmileLadiesPackageCd() != null && cepr01030102Form.getSmileLadiesPackageCd() >1 ||
        				cepr01030102Form.getEducationPackageCd() != null && cepr01030102Form.getEducationPackageCd() >1 ){
        			cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        	}
        	else{
        			cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD } ) );
        	}
        	
           
            cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_SEKALIGUS } ) );
            cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_TRUE );
            cepr01030102Form.setTermOfPayment( 1 );
            cepr01030102Form.setPremiumFurloughYearIsDisabled( CommonConst.DISABLED_TRUE );
            cepr01030102Form.setPremiumFurloughYearListDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination10FoldedList().subList( 0, 1 ) );
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 3000000, 20000000, 1000000 );
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 1000, 2500, 100 );
            }
            
            cepr01030102Form.setPremiumCombinationCd(new BigDecimal(100));
            cepr01030102Form.setTopUpPremium(new BigDecimal(0));        
        }
        else if( Cepr01040219Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X6 == cepr01030102Form.getRightPartOfBusinessCd()
        		|| Cepr01040219Mapper.X8 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X9 == cepr01030102Form.getRightPartOfBusinessCd())
        {
        	if(credentialsVO.getMsagId() != null && "999993".equals(credentialsVO.getMsagId())){
        		cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        	}
        	else if( cepr01030102Form.getSmileLadiesPackageCd() != null && cepr01030102Form.getSmileLadiesPackageCd() >1 ||
        				cepr01030102Form.getEducationPackageCd() != null && cepr01030102Form.getEducationPackageCd() >1){
        				cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        	}else{
        		cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD } ) );
        	}
        	
            cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_FALSE );
            cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
            cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_SEMESTERAN, Hardcode.PAY_MODE_CD_TRIWULANAN, Hardcode.PAY_MODE_CD_BULANAN } ) );
                       
            cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE );
            cepr01030102Form.setTermOfPayment( 99 - cepr01030101Form.getInsuredAge() );
            cepr01030102Form.setPremiumFurloughYearIsDisabled( CommonConst.DISABLED_FALSE );
            cepr01030102Form.setPremiumFurloughYearListDisplay( CommonConst.DISPLAY_TRUE );
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 3000000, 20000000, 1000000 );
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 300, 2000, 100 );
            }
            
            if( Cepr01040219Mapper.X9 == cepr01030102Form.getRightPartOfBusinessCd() ){
                cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN } ) );
                cepr01030102Form.setPremiumFurloughYear( 5 );
                cepr01030102Form.setPremiumFurloughYearIsDisabled( CommonConst.DISABLED_TRUE );
                                
            }
        
        }

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
            if( cepr01030102Form.getRightPartOfBusinessCd() <= Cepr01040219Mapper.X8 ){
	            cepr01030102Form.setCashFundIdrDisplay( CommonConst.DISPLAY_TRUE );
	            cepr01030102Form.setCashFundIdrListDisplay( CommonConst.DISPLAY_TRUE );
	            cepr01030102Form.setExcellinkEquityIdrDisplay( CommonConst.DISPLAY_TRUE );
	            cepr01030102Form.setExcellinkEquityListDisplay( CommonConst.DISPLAY_TRUE );          
            }
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            cepr01030102Form.setSecureUsdDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setSecureUsdListDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setDynamicUsdDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setDynamicUsdListDisplay( CommonConst.DISPLAY_TRUE );
            
            cepr01030102Form.setCashFundIdrDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030102Form.setExcellinkEquityIdrDisplay( CommonConst.DISPLAY_FALSE );
        }

        cepr01030102Form.setPremiumList( premiumList );
        refreshBaseTopupPremium();
        if( "onChangeEducationPackageCd".equals( cepr01030102Form.getTheEvent() ) ){
        	setRiderValueForFirstTime();
        	setEducationPackage();
        }else if( "onChangesmileLadiesPackageCd".equals( cepr01030102Form.getTheEvent() ) ){
        	setRiderValueForFirstTime();
        	setSmileLadiesPackage();
        }
        
//		 (Helpdesk 56020): Bridge Agency
        if( CommonConst.DISPLAY_TRUE.equals(cepr01030102Form.getJenisListDisplay() )){
	        if( Cepr01040219Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() ){
	        	cepr01030102Form.setJenisList( comboLookupBusiness.getJenisOptionVOBridgeAgencyList(false));
	        	 if(cepr01030102Form.getJenisCd() != null){
	               	if(cepr01030102Form.getJenisCd()==27 || cepr01030102Form.getJenisCd()==28 || cepr01030102Form.getJenisCd()==29 || cepr01030102Form.getJenisCd()==30 ){
	               	 	cepr01030102Form.setButtonRiderDisplay(CommonConst.DISPLAY_FALSE);     
	                   	cepr01030102Form.setPremiumIsDisabled(CommonConst.DISABLED_TRUE );
	                    cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_TRUE );
	                   	cepr01030102Form.setTotalSumInsuredIsDisabled(CommonConst.DISABLED_TRUE);
	                    cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_BULANAN } ) );
	                   	cepr01030102Form.setPremiumFurloughYearIsDisabled(CommonConst.DISABLED_TRUE);
	                   	cepr01030102Form.setAggresiveIdrIsDisabled(CommonConst.DISABLED_FALSE);
	               	}else{
	               		cepr01030102Form.setJenisCd(0);
	               	 	cepr01030102Form.setButtonRiderDisplay(CommonConst.DISPLAY_TRUE);     
	               	  	cepr01030102Form.setPremiumIsDisabled(CommonConst.DISABLED_FALSE );
	                    cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_FALSE );
	                   	cepr01030102Form.setTotalSumInsuredIsDisabled(CommonConst.DISABLED_FALSE);
	                   	cepr01030102Form.setPremiumFurloughYearIsDisabled(CommonConst.DISABLED_FALSE);
	                   	cepr01030102Form.setAggresiveIdrIsDisabled(CommonConst.DISABLED_FALSE);               		
	               	}
	              }
	        }
	        
	        if( Cepr01040219Mapper.X9 == cepr01030102Form.getRightPartOfBusinessCd() ){ 
	        	cepr01030102Form.setJenisList( comboLookupBusiness.getPacketTabelList(String.valueOf(cepr01030102Form.getLeftPartOfBusinessCd()), String.valueOf(cepr01030102Form.getRightPartOfBusinessCd())));
	        	 cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_TRUE );
	        	 cepr01030102Form.setAggresiveIdrIsDisabled(CommonConst.DISABLED_TRUE);
	        	  cepr01030103Form.setPaFlag( CommonConst.CHECKED_TRUE );
	        	processFormAfterSubmitBeforeValidation();
	        	cepr01030102Form.setPremiumCombinationCd(new BigDecimal("100"));
	        }
        }
    }

    public void validateCurrentPage()
    {
        UlinkValidatorParVO parVO = new UlinkValidatorParVO();
        parVO.setUnder5YearsBerkalaMaxBasePremiumVO( new AmountVO( 100000000.0, 10000 ) );
        parVO.setUnder5YearsSekaligusMaxBasePremiumVO( new AmountVO( 400000000.0, 40000 ) );
        parVO.setBeyond5YearsBerkalaMaxBasePremiumVO( new AmountVO( 1000000000.0, 100000 ) );
        parVO.setBeyond5YearsSekaligusMaxBasePremiumVO( new AmountVO( 1000000000.0, 100000 ) );
        
        //--> (SD-204) IGA KETENTUAN UP TT DIBAWAH 5 TAHUN REQ AKTUARI
//      if (cepr01030101Form.getInsuredAge() > 5){
      	super.validateCurrentPage( parVO );	
//      }
      
//      if( errors.getErrorCount() == 0 && errors.getGlobalErrorCount() == 0 )
//      {
//      	if(cepr01030101Form.getInsuredAge() < 6){
//      		commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, cepr01030102Form.getTotalSumInsured(), new BigDecimal("500000000"), new BigDecimal("50000") );
//      	}
//      }
      
      if( errors.getErrorCount() == 0 && errors.getGlobalErrorCount() == 0 )
      {
      	if(cepr01030101Form.getInsuredAge() < 6){
      		if( credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals(Hardcode.GROUP_AGENCY)){
      			commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, cepr01030102Form.getTotalSumInsured(), new BigDecimal("1000000000"), new BigDecimal("100000") );
      		}else {
      			commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, cepr01030102Form.getTotalSumInsured(), new BigDecimal("500000000"), new BigDecimal("50000") );
      		}
      	}
      }//<--DONE (SD-204)
        
        if( cepr01030102Form.getTotalSumInsured() != null )
        {
            double totalSumInsured = LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() );
            double minTotalSumInsuredIdr = 7500000;
            double minTotalSumInsuredUsd = 750;
           // commonValidator.validateMinimumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, totalSumInsured, minTotalSumInsuredIdr, minTotalSumInsuredUsd );
        }
        validateCurrentPageInCommon();
     
    }

    public void validatePreviousPage()
    {        
        if( Cepr01040219Mapper.X9 == cepr01030102Form.getRightPartOfBusinessCd() ){        	  
        		String umurTertanggung = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());        	
        		if( umurTertanggung != null && LazyConverter.toInt(umurTertanggung)<600)
        		{
        			errors.rejectValue( Cepr01030102FormConst.ASSURANCE_PLAN_CD, "error.insuredAgeMinMonth", new Object[]{ Integer.toString( 6 )  }, null );
        		}
        		if( cepr01030101Form.getInsuredAge() > 55 )
    	        {
    	            errors.rejectValue( Cepr01030102FormConst.ASSURANCE_PLAN_CD, "error.insuredAgeMax", new Object[]{ Integer.toString( 55 ) }, null );
    	        }        	  
        }else{
        	 commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 0, 70 );
        }        
        commonValidator.validatePolicyHolderAge(Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 85);                
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
        refreshBaseTopupPremium();

        if( cepr01030102Form.getPremium() != null ){
        if( "onChangePremium".equals( cepr01030102Form.getTheEvent() )
        	|| ("onPressButtonPreviewProposal".equals( cepr01030102Form.getTheEvent() ) && Cepr01040219Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() ) 
            || "onPressButtonCountTotalSumInsured".equals( cepr01030102Form.getTheEvent() )
            || cepr01030102Form.getTotalSumInsured() != null && cepr01030102Form.getTotalSumInsured().equals( new BigDecimal( "0" ) ))
        {
            double idec_rate;
            double adec_premi = premium;
            double idec_up = 0;

            int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );

            switch( paymentModeCd )
            {
                case Hardcode.PAY_MODE_CD_TRIWULANAN: adec_premi = adec_premi * 4;  break;
                case Hardcode.PAY_MODE_CD_SEMESTERAN: adec_premi = adec_premi * 2;  break;
                case Hardcode.PAY_MODE_CD_BULANAN   : adec_premi = adec_premi * 12; break;
                default: break;
            }

            if( Cepr01040219Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X5 == cepr01030102Form.getRightPartOfBusinessCd() 
            		|| Cepr01040219Mapper.X7 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X9 == cepr01030102Form.getRightPartOfBusinessCd())
            {
                // I got it from n_prod_159 function of_set_premi
                idec_rate = 1250;
                idec_up = adec_premi * idec_rate / 1000;
                if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                {
                    idec_up = Math.max( idec_up, 15000000 );
                }
                else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                {
                    idec_up = Math.max( idec_up, 1500 );
                }
            }
            else if( Cepr01040219Mapper.X6 == cepr01030102Form.getRightPartOfBusinessCd() ||  Cepr01040219Mapper.X8 == cepr01030102Form.getRightPartOfBusinessCd() ||  Cepr01040219Mapper.X9 == cepr01030102Form.getRightPartOfBusinessCd() )
            {
                // I got it from n_prod_159 function of_set_premi
                idec_rate = 5000;
                idec_up = adec_premi * idec_rate / 1000;
            }
            else if( Cepr01040219Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() ) {
            	   int li_temp;
                   int li_bisnis = LazyConverter.toInt( cepr01030102Form.getLeftPartOfBusinessCd() );
                   li_temp = of_get_max( li_bisnis  );
                   
                   idec_up = adec_premi *  li_temp;            	
            }
                        
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
        }     
        }
        
//		 (Helpdesk 56020): Bridge Agency
        if( Cepr01040219Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() && CommonConst.DISPLAY_TRUE.equals(cepr01030102Form.getJenisListDisplay())){        	
         if(cepr01030102Form.getJenisCd() != null){
           	if(cepr01030102Form.getJenisCd()==27 ){           	
           		cepr01030102Form.setTotalSumInsured(new BigDecimal("21000000"));           
           		cepr01030102Form.setPremiumCombinationCd(new BigDecimal("73"));
           		cepr01030102Form.setPremiumFurloughYear(5);
           		if(cepr01030102Form.getPaymentModeCd() == 6){
           			cepr01030102Form.setPremium(new BigDecimal("350000"));
           		}else if(cepr01030102Form.getPaymentModeCd() == 3){
           			cepr01030102Form.setPremium(new BigDecimal("4200000"));           			
           		}
           	}else if(cepr01030102Form.getJenisCd()==28 ){
           		cepr01030102Form.setTotalSumInsured(new BigDecimal("42000000"));
           		cepr01030102Form.setPremiumCombinationCd(new BigDecimal("73"));
           		cepr01030102Form.setPremiumFurloughYear(5);
           		if(cepr01030102Form.getPaymentModeCd() == 6){
           			cepr01030102Form.setPremium(new BigDecimal("700000"));
           		}else if(cepr01030102Form.getPaymentModeCd() == 3){
           			cepr01030102Form.setPremium(new BigDecimal("8400000"));           			
           		}           		
           	}else if(cepr01030102Form.getJenisCd()==29 ){
           		cepr01030102Form.setTotalSumInsured(new BigDecimal("84000000"));
           		cepr01030102Form.setPremiumCombinationCd(new BigDecimal("73"));
           		cepr01030102Form.setPremiumFurloughYear(5);
           		if(cepr01030102Form.getPaymentModeCd() == 6){
           			cepr01030102Form.setPremium(new BigDecimal("1400000"));
           		}else if(cepr01030102Form.getPaymentModeCd() == 3){
           			cepr01030102Form.setPremium(new BigDecimal("16800000"));           			
           		}          
        	}else if(cepr01030102Form.getJenisCd()==30 ){           		
           		cepr01030102Form.setTotalSumInsured(new BigDecimal("126000000"));
           		cepr01030102Form.setPremiumCombinationCd(new BigDecimal("73"));
           		cepr01030102Form.setPremiumFurloughYear(5);
           		if(cepr01030102Form.getPaymentModeCd() == 6){
           			cepr01030102Form.setPremium(new BigDecimal("2100000"));
           		}else if(cepr01030102Form.getPaymentModeCd() == 3){
           			cepr01030102Form.setPremium(new BigDecimal("25200000"));           			
           		} 
           	}else{
           		         		
           	}
          }
        }
        
        if( Cepr01040219Mapper.X9 == cepr01030102Form.getRightPartOfBusinessCd() ){
        	cepr01030102Form.setAggresiveIdr(new BigDecimal("100"));
        	if(cepr01030102Form.getJenisCd()==null){
        		cepr01030102Form.setPremium(new BigDecimal("2500000")); 
         		cepr01030102Form.setTotalSumInsured(new BigDecimal("25000000"));
         		cepr01030102Form.setBasePremium(new BigDecimal("2500000"));
        	}else{     		
         	if(cepr01030102Form.getJenisCd()==35){
         		  cepr01030103Form.setPaRiskCd(1);
         		 cepr01030103Form.setPaClassCd(1);
         		 cepr01030103Form.setPaUnitQtyCd(1);
         		cepr01030102Form.setPremium(new BigDecimal("2500000"));         		
         		cepr01030102Form.setTotalSumInsured(new BigDecimal("25000000"));
         	}else if(cepr01030102Form.getJenisCd()==36){       
         		  cepr01030103Form.setPaRiskCd(2);
         		 cepr01030103Form.setPaClassCd(1);
         		 cepr01030103Form.setPaUnitQtyCd(1);
         		cepr01030102Form.setPremium(new BigDecimal("5000000")); 
         		cepr01030102Form.setTotalSumInsured(new BigDecimal("50000000"));
         	}else if(cepr01030102Form.getJenisCd()==37){   
         		  cepr01030103Form.setPaRiskCd(3);
          		 cepr01030103Form.setPaClassCd(1);
          		 cepr01030103Form.setPaUnitQtyCd(1);
         		cepr01030102Form.setPremium(new BigDecimal("10000000")); 
         		cepr01030102Form.setTotalSumInsured(new BigDecimal("100000000"));
         	}}
        	
        	 if(cepr01030101Form.getInsuredAge()<=17){
        		 cepr01030103Form.setPaClassCd(2);
        	 }else if(cepr01030101Form.getInsuredAge()>17 && cepr01030101Form.getInsuredAge()<56){
        		 cepr01030103Form.setPaClassCd(3);
        	 }
        }
        
    }

    public void validateCurrentPageInCommon()
    {
        logger.info( "*-*-*-* Cepr01040201Entry.validateCurrentPageInCommon" );
        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double minPremiumIdr;
        double minPremiumUsd;

        switch( cepr01030102Form.getPaymentModeCd() )
        {
            case Hardcode.PAY_MODE_CD_SEKALIGUS:         
              	minPremiumIdr = 10000000;
                minPremiumUsd = 1000; 
                break;
            case Hardcode.PAY_MODE_CD_TAHUNAN:            
             	minPremiumIdr = 1800000;
                minPremiumUsd = 180; 
                break;
            case Hardcode.PAY_MODE_CD_SEMESTERAN:            
                minPremiumIdr = 900000;
                minPremiumUsd = 90;              
                break;
            case Hardcode.PAY_MODE_CD_TRIWULANAN:              
                minPremiumIdr = 450000;
                minPremiumUsd = 45;             
                break;
            case Hardcode.PAY_MODE_CD_BULANAN:            	
                minPremiumIdr = 150000;
                minPremiumUsd = 15;
                break;
            default:
                throw new RuntimeException( "*-*-*-* cepr01030102Form.getPaymentModeCd() not listed in Cepr01040201Entry.validateCurrentPage" );
        }

        if( cepr01030102Form.getPremium() != null )
        {
            commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );
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

        commonValidator.validateTenFoldedForAllInvestment();
        if( Cepr01040219Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X6 == cepr01030102Form.getRightPartOfBusinessCd()
        		|| Cepr01040219Mapper.X8 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040219Mapper.X9 == cepr01030102Form.getRightPartOfBusinessCd() ) {
        	//commonValidator.validatePremiumFurloughYear( Cepr01030102FormConst.PREMIUM_FURLOUGH_YEAR, 2, 20 );
        	 commonValidator.validatePremiumFurloughYearMin(2, cepr01030102Form.getPremiumFurloughYear() );
        }
      
    	//REQUEST 02/09/2013@Vito Shadiq : Special Offer= NONE(0) digunakan utk mendisable Special Offer pd Distribusi MNC 
        if( Cepr01040219Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
       // 	commonValidator.validateSpecialOffer();
        }
        
    }
      
}