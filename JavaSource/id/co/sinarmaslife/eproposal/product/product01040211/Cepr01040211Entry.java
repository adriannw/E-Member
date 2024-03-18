package id.co.sinarmaslife.eproposal.product.product01040211;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040211Entry
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 10, 2009 2:48:44 PM
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
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.standard.model.vo.OptionVO;

public class Cepr01040211Entry extends Cepr01040000UlinkEntry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040211Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        setDownloadUrl( "wepr01040211.pdf" );
    }

    public void initDisplayedForm()
    {
        displayStandardForm();
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
    
        cepr01030103Form.setPayorTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverTpdDisplay( CommonConst.DISPLAY_FALSE );
       
        cepr01030103Form.setWaiverCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiver5Ci10CiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setEkaSehatDisplay( CommonConst.DISPLAY_FALSE ); //Decommission Rider SMAC/SMEX Oktober 2021 (NCR/2021/10/106)
        cepr01030103Form.setEkaSehatInnerLimitDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setLadiesMedExpenseDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setLadiesMedExpenseInnerLimitDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setPayor5Tpd10TpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayor5Ci10CiDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiver5Tpd10TpdDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorTpdCiDeathDisplay( CommonConst.DISPLAY_TRUE );
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X15 ){
        	cepr01030103Form.setWaiverTpdCiDisplay( CommonConst.DISPLAY_TRUE );
        }else{
        	cepr01030103Form.setWaiverTpdCiDisplay( CommonConst.DISPLAY_FALSE );
        }
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X24 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X21 ){
            cepr01030103Form.setWaiver5Tpd10CiDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setPayor5Tpd10CiDeathDisplay( CommonConst.DISPLAY_FALSE );
        }else{
            cepr01030103Form.setWaiver5Tpd10CiDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030103Form.setPayor5Tpd10CiDeathDisplay( CommonConst.DISPLAY_TRUE );
        }
        if( Cepr01040211Mapper.X7 == cepr01030102Form.getRightPartOfBusinessCd() ||Cepr01040211Mapper.X8 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
        	cepr01030102Form.setSmileLadiesPackageListDisplay( CommonConst.DISPLAY_TRUE );
        }
        else 
        {
        	cepr01030102Form.setSmileLadiesPackageListDisplay( CommonConst.DISPLAY_FALSE );
        }
        
        if( Cepr01040211Mapper.X7 == cepr01030102Form.getRightPartOfBusinessCd() ||
        	Cepr01040211Mapper.X8 == cepr01030102Form.getRightPartOfBusinessCd() ||
        	Cepr01040211Mapper.X9 == cepr01030102Form.getRightPartOfBusinessCd() ) 
        {
        	cepr01030103Form.setScholarshipDisplay( CommonConst.DISPLAY_TRUE );
        }
        else 
        {
        	cepr01030103Form.setScholarshipDisplay( CommonConst.DISPLAY_FALSE );
        }
        
        
        if( Cepr01040211Mapper.X7 == cepr01030102Form.getRightPartOfBusinessCd() ||
        	Cepr01040211Mapper.X8 == cepr01030102Form.getRightPartOfBusinessCd() ||        	
        	Cepr01040211Mapper.X23== cepr01030102Form.getRightPartOfBusinessCd()  ||        	
            Cepr01040211Mapper.X11== cepr01030102Form.getRightPartOfBusinessCd())
        {
        	cepr01030102Form.setEducationPackageListDisplay( CommonConst.DISPLAY_TRUE );
        }
        else 
        {
        	cepr01030102Form.setEducationPackageListDisplay( CommonConst.DISPLAY_FALSE );
        }
        
        // SIMAS POWER LINK (Simpol) SINGLE tampilkan Paket Protector ** @Desvinna 9/12/2013 
        if( Cepr01040211Mapper.X12 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040211Mapper.X24 == cepr01030102Form.getRightPartOfBusinessCd())
        {
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) ){
            	 cepr01030102Form.setProtectorPackageListDisplay(CommonConst.DISPLAY_TRUE );
            }else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) ){                
            	 cepr01030102Form.setProtectorPackageListDisplay(CommonConst.DISPLAY_FALSE );      
            	disabledRiderForm( false );
               	checkAllRider( false );
                cepr01030102Form.setProtectorPackageCd( 1 );      
            }   
        }else
        {
        	cepr01030102Form.setProtectorPackageListDisplay(CommonConst.DISPLAY_FALSE );
        	cepr01030102Form.setProtectorPackageCd(1);
        } 
   
 	   if( cepr01030102Form.getCurrencyCd() != null ){
 		  if( cepr01030102Form.getCurrencyCd().equals( "01" ) )// rupiah
 		  {       	        	
 			 cepr01030103Form.setEkaSehatDisplay( CommonConst.DISPLAY_FALSE ); //Decommission Rider SMAC/SMEX Oktober 2021 (NCR/2021/10/106)
 		   	 cepr01030103Form.setEkaSehatInnerLimitDisplay( CommonConst.DISPLAY_TRUE );
 		     cepr01030103Form.setScholarshipDisplay(CommonConst.DISPLAY_TRUE );
 		  }else{       	        	 
 			 cepr01030103Form.setEkaSehatDisplay( CommonConst.DISPLAY_FALSE ); 
 		   	 cepr01030103Form.setEkaSehatInnerLimitDisplay( CommonConst.DISPLAY_FALSE );
 		     cepr01030103Form.setScholarshipDisplay(CommonConst.DISPLAY_FALSE );	
 		 }
 	   }
        
        if(Cepr01040211Mapper.X22 == cepr01030102Form.getRightPartOfBusinessCd() ||
        		Cepr01040211Mapper.X23 == cepr01030102Form.getRightPartOfBusinessCd() ||
        		Cepr01040211Mapper.X24 == cepr01030102Form.getRightPartOfBusinessCd() ||
           		Cepr01040211Mapper.X10 == cepr01030102Form.getRightPartOfBusinessCd() ||
           		Cepr01040211Mapper.X11 == cepr01030102Form.getRightPartOfBusinessCd() ||
           		Cepr01040211Mapper.X12 == cepr01030102Form.getRightPartOfBusinessCd() 
        		){
        	cepr01030103Form.setHcpDisplay( CommonConst.DISPLAY_FALSE );        	
        	//if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) ){
        		cepr01030103Form.setHcpProviderDisplay( CommonConst.DISPLAY_FALSE );
        	//}        	
        	cepr01030103Form.setHcpLadiesDisplay( CommonConst.DISPLAY_FALSE );
        	cepr01030103Form.setPayorTpdCiDeathDisplay( CommonConst.DISPLAY_FALSE );
        	cepr01030103Form.setPayor5Tpd10CiDeathDisplay( CommonConst.DISPLAY_FALSE );
        	cepr01030103Form.setWaiver5Tpd10CiDisplay( CommonConst.DISPLAY_FALSE );
        	          
        	cepr01030103Form.setEkaSehatInnerLimitDisplay(  CommonConst.DISPLAY_FALSE );
        	cepr01030103Form.setLadiesInsDisplay( CommonConst.DISPLAY_FALSE );
        	cepr01030103Form.setLadiesMedExpenseDisplay(  CommonConst.DISPLAY_FALSE );
        	cepr01030103Form.setLadiesMedExpenseInnerLimitDisplay( CommonConst.DISPLAY_FALSE );
        	cepr01030103Form.setScholarshipDisplay( CommonConst.DISPLAY_FALSE );
        	   if(Cepr01040211Mapper.X10 == cepr01030102Form.getRightPartOfBusinessCd() ||
               		Cepr01040211Mapper.X11 == cepr01030102Form.getRightPartOfBusinessCd()
        			   ){
               	cepr01030103Form.setWaiver5Tpd10TpdDisplay( CommonConst.DISPLAY_TRUE );
            	cepr01030103Form.setWaiver5Ci10CiDisplay( CommonConst.DISPLAY_TRUE );
            	cepr01030103Form.setPayor5Ci10CiDeathDisplay( CommonConst.DISPLAY_TRUE );
            	cepr01030103Form.setPayor5Tpd10TpdDeathDisplay( CommonConst.DISPLAY_TRUE );
        	   }
        	if( cepr01030102Form.getCurrencyCd() != null ){
       	        if( cepr01030102Form.getCurrencyCd().equals( "01" ) )// rupiah
       	        {       	        	
       	        	 cepr01030103Form.setEkaSehatDisplay( CommonConst.DISPLAY_FALSE ); //Decommission Rider SMAC/SMEX Oktober 2021 (NCR/2021/10/106)
       	        }else{       	        	 
       	        	 cepr01030103Form.setEkaSehatDisplay( CommonConst.DISPLAY_FALSE );
       	        }
             }          
               		
        }
        
       if(Cepr01040211Mapper.X7 == cepr01030102Form.getRightPartOfBusinessCd() ||
        Cepr01040211Mapper.X8== cepr01030102Form.getRightPartOfBusinessCd() ||
        Cepr01040211Mapper.X9 == cepr01030102Form.getRightPartOfBusinessCd() ||
        Cepr01040211Mapper.X13 == cepr01030102Form.getRightPartOfBusinessCd() ||
        Cepr01040211Mapper.X14== cepr01030102Form.getRightPartOfBusinessCd() ||
        Cepr01040211Mapper.X15 == cepr01030102Form.getRightPartOfBusinessCd()){
    	   	cepr01030103Form.setHcpDisplay( CommonConst.DISPLAY_FALSE);
    	    if( cepr01030102Form.getEducationPackageCd().equals( Hardcode.EDUCATION_PAKET_C )){
        		cepr01030103Form.setHcpDisplay( CommonConst.DISPLAY_TRUE);
    	    }
        	cepr01030103Form.setHcpProviderDisplay( CommonConst.DISPLAY_TRUE );
        	cepr01030103Form.setHcpFamilyDisplay(CommonConst.DISPLAY_TRUE );
        }
       cepr01030103Form.setMedicalPlusDisplay( CommonConst.DISPLAY_FALSE ); 
       
       if(Cepr01040211Mapper.X19 == cepr01030102Form.getRightPartOfBusinessCd() ||
    	  Cepr01040211Mapper.X20 == cepr01030102Form.getRightPartOfBusinessCd() ||
    	  Cepr01040211Mapper.X21 == cepr01030102Form.getRightPartOfBusinessCd() ){
    	   cepr01030103Form.setEarlyStageCi99Display( CommonConst.DISPLAY_TRUE );
       }else{
    	   cepr01030103Form.setEarlyStageCi99Display( CommonConst.DISPLAY_FALSE );
       }
     
    }

    public void initDisabledForm()
    {
        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPremiumListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_FALSE );
    }

    public void initReadOnlyForm()
    {
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
    	if( credentialsVO != null && credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_BANCASS ) ){
    		cepr01030102Form.setSmileLadiesPackageList( comboLookupBusiness.smileLadiesPackageBancassList() );
    	}else{
    		cepr01030102Form.setSmileLadiesPackageList( comboLookupBusiness.smileLadiesPackageList() );
    	}
    	
    	if( Cepr01040211Mapper.X7 == cepr01030102Form.getRightPartOfBusinessCd() || Cepr01040211Mapper.X8 == cepr01030102Form.getRightPartOfBusinessCd())
        {
            cepr01030102Form.setPremiumFurloughYear( 10 );
            cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_TAHUNAN );
            cepr01030103Form.setCiChooseCd("50");
            cepr01030103Form.setLadiesInsCd(1);
            cepr01030103Form.setLadiesInsChooseCd("50");
            if( credentialsVO != null && credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_BANCASS ) ){
            	 cepr01030103Form.setEkaSehatCd(13);
            }else{
            	 cepr01030103Form.setEkaSehatCd(1);
            }
            cepr01030103Form.setPaClassCd(1);
            cepr01030103Form.setPaRiskCd(1);
            cepr01030103Form.setPaUnitQtyCd(1);
            cepr01030103Form.setLadiesMedExpenseCd( 1 );
            cepr01030103Form.setHcpLadiesCd(1);
        }
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        cepr01030102Form.setFixIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setDynamicIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setAggresiveIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setCashFundIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setExcellinkEquityIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setSecureUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setDynamicUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        if( ( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X8 ) && cepr01030102Form.getSmileLadiesPackageCd() != null && cepr01030102Form.getSmileLadiesPackageCd() > 1 ){
        	if(credentialsVO != null && credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_BANCASS ) ){
        		cepr01030102Form.setPremiumCombinationList( getPremiumCombination50List() );
        	}else{
        		cepr01030102Form.setPremiumCombinationList( getPremiumCombinationList() );
        	}
        	cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        } else{
        	if(credentialsVO != null && credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_BANCASS ) &&
        			cepr01030102Form.getSmileLadiesPackageCd() != null && cepr01030102Form.getSmileLadiesPackageCd() > 1){
        		cepr01030102Form.setPremiumCombinationList( getPremiumCombination50List() );
        	}else{
        		cepr01030102Form.setPremiumCombinationList( getPremiumCombinationList(10) );
        	}
        }
        if(credentialsVO != null && credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_BANCASS ) ){
        	if( cepr01030102Form.getSmileLadiesPackageCd() != null && cepr01030102Form.getSmileLadiesPackageCd() > 1 ){
	    		cepr01030103Form.setCiChooseCd("100");
	    		cepr01030103Form.setLadiesInsChooseCd("100");
        	}
    	}else{
    		
    	}
        
        cepr01030102Form.setPremiumFurloughYearList( LookupList.getSequenceNumberList( 0, 20, 5, false ) );
//        cepr01030103Form.setTermRiderAmount( cepr01030102Form.getTotalSumInsured() );

        cepr01030102Form.setTermOfContract( 100 - cepr01030101Form.getInsuredAge() );

        if( cepr01030102Form.getCurrencyCd() == null )
        {
            cepr01030102Form.setCurrencyCd( Hardcode.CUR_IDR_CD );
        }
        List<OptionVO> premiumList = null;

        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 4000000, 13000000, 1000000 );

            cepr01030102Form.setFixIdrDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setFixIdrListDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setDynamicIdrDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setDynamicIdrListDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setAggresiveIdrDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setAggresiveIdrListDisplay( CommonConst.DISPLAY_TRUE );
            
            if( (cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X22 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X23 ||
            	cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X24) ){//IGA - PROJECT SIMPOL
//            		cepr01030102Form.setLjiFixListDisplay( CommonConst.DISPLAY_TRUE );
//            		cepr01030102Form.setLjiFixList(LookupList.getJenisInvestFixList());
//            		cepr01030102Form.setLjiDynamicListDisplay( CommonConst.DISPLAY_TRUE );
//            		cepr01030102Form.setLjiDynamicList(LookupList.getJenisInvestDynamicList());
//            		cepr01030102Form.setLjiAggresiveListDisplay( CommonConst.DISPLAY_TRUE );
//            		cepr01030102Form.setLjiAggresiveList(LookupList.getJenisInvestAggresiveList());
            	cepr01030102Form.setFixIdrDisplay( CommonConst.DISPLAY_TRUE );
                cepr01030102Form.setFixIdrListDisplay( CommonConst.DISPLAY_TRUE );
                cepr01030102Form.setDynamicIdrDisplay( CommonConst.DISPLAY_TRUE );
                cepr01030102Form.setDynamicIdrListDisplay( CommonConst.DISPLAY_TRUE );
                cepr01030102Form.setAggresiveIdrDisplay( CommonConst.DISPLAY_TRUE );
                cepr01030102Form.setAggresiveIdrListDisplay( CommonConst.DISPLAY_TRUE );
                cepr01030102Form.setCashFundIdrDisplay( CommonConst.DISPLAY_TRUE );
                cepr01030102Form.setCashFundIdrListDisplay( CommonConst.DISPLAY_TRUE );          
                cepr01030102Form.setExcellinkEquityIdrDisplay( CommonConst.DISPLAY_TRUE );
                cepr01030102Form.setExcellinkEquityListDisplay( CommonConst.DISPLAY_TRUE ); 
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
                cepr01030102Form.setExcellinkEquityListDisplay( CommonConst.DISPLAY_TRUE );//DONE
            }
            if( cepr01030102Form.getRightPartOfBusinessCd() >= Cepr01040211Mapper.X19 && cepr01030102Form.getRightPartOfBusinessCd() <= Cepr01040211Mapper.X21 ){
	            cepr01030102Form.setCashFundIdrDisplay( CommonConst.DISPLAY_TRUE );
	            cepr01030102Form.setCashFundIdrListDisplay( CommonConst.DISPLAY_TRUE );
	            cepr01030102Form.setExcellinkEquityIdrDisplay( CommonConst.DISPLAY_TRUE );
	            cepr01030102Form.setExcellinkEquityListDisplay( CommonConst.DISPLAY_TRUE );          
            }
            
            cepr01030102Form.setSecureUsd(new BigDecimal(0.0));
            cepr01030102Form.setDynamicUsd(new BigDecimal(0.0));
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 400, 1300, 100 );
            cepr01030102Form.setSecureUsdDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setSecureUsdListDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setDynamicUsdDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setDynamicUsdListDisplay( CommonConst.DISPLAY_TRUE );
            
            cepr01030102Form.setFixIdr(new BigDecimal(0.0));
            cepr01030102Form.setDynamicIdr(new BigDecimal(0.0));
            cepr01030102Form.setAggresiveIdr(new BigDecimal(0.0));
            
            cepr01030102Form.setLjiFixCd("");           
            cepr01030102Form.setLjiDynamicCd("");
            cepr01030102Form.setLjiAggresiveCd("");
        }

        cepr01030102Form.setPremiumList( premiumList );
        
    	if( (cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X8) && cepr01030102Form.getTheEvent().equals("onChangesmileLadiesPackageCd") ){
    		if( cepr01030102Form.getSmileLadiesPackageCd() != null && cepr01030102Form.getSmileLadiesPackageCd() > 1 ){
    			cepr01030102Form.setPremiumCombinationCd(new BigDecimal("60") );
    			cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
    		}
    	}
    	if( ( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X23 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X11 ) && cepr01030102Form.getTheEvent().equals("onChangeEducationPackageCd") ){
    		if( cepr01030102Form.getEducationPackageCd() != null && cepr01030102Form.getEducationPackageCd().equals(Hardcode.EDUCATION_PAKET_SIMPOL_BSM) ){
    			cepr01030102Form.setPremiumCombinationCd(new BigDecimal("50") );  
    		}
    	}
    	
    	if( (cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X23 ||  cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X11)){
    		if( cepr01030102Form.getEducationPackageCd() == null || cepr01030102Form.getEducationPackageCd() != null && cepr01030102Form.getEducationPackageCd() == 0 ){
    			disabledRiderForm( false );
        		cepr01030102Form.setEducationPackageCd( 0 );
        	}else{
        		checkAllRider( false );
        		disabledRiderForm( true );
        		disabledListOptionAtAllRiderForm( true );
        		
        		if( cepr01030102Form.getEducationPackageCd().equals(Hardcode.EDUCATION_PAKET_SIMPOL_BSM) ){         		
        			cepr01030103Form.setPayor5Tpd10TpdDeathFlag( CommonConst.CHECKED_TRUE );
        		}
        	}
    	}
    	else if((cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X12 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X24) && "onChangeProtectorPackageCd".equals( cepr01030102Form.getTheEvent())){
    	    // Set Riders, untuk Paket Simpol Protector
    		 setProtectorPackage();    		
    	} else{
    		if( "onChangeEducationPackageCd".equals( cepr01030102Form.getTheEvent() ) ){
            	setRiderValueForFirstTime();
            	setEducationPackage();
            }else if( "onChangesmileLadiesPackageCd".equals( cepr01030102Form.getTheEvent() ) ){
            	setRiderValueForFirstTime();
            	setSmileLadiesPackage();
            }    		
    	}     
    	       
    }

    public void validateCurrentPage()
    {
    	if( cepr01030102Form.getRightPartOfBusinessCd() >= 7 && cepr01030102Form.getRightPartOfBusinessCd() <= 12 ){
    		commonValidator.validateMinimumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, cepr01030102Form.getTotalSumInsured(), cepr01030102Form.getTotalSumInsuredFix(), cepr01030102Form.getTotalSumInsuredFix() );
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
        commonValidator.validatePremiumFurloughYear( Cepr01030102FormConst.PREMIUM_FURLOUGH_YEAR, 1, 20 );
    }

    public void validatePreviousPage()
    {
    	if( ( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X23 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X11)){
    		if( cepr01030102Form.getEducationPackageCd().equals(Hardcode.EDUCATION_PAKET_SIMPOL_BSM) ){ 
    			//commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 0, 15 );
    			commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 0, 12 );
    			commonValidator.validatePolicyHolderAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 40 );
    		}
    		// #REQ: Desvinna@21/05/2014 => DiNon-Aktifkan krn strategi penjualan kredit mikro masih perlu dibahas kembali dengan Bank Sinarmas (Achmad H/02-05-2014)
    		//else if(cepr01030102Form.getJenisCd()!=null && cepr01030102Form.getJenisCd()==2){
    		//	commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 0, 60 );
        	//	commonValidator.validatePolicyHolderAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 85 );
    		//}
    		else{    			
    			commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 0, 70 );
        		commonValidator.validatePolicyHolderAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 85 );
    		}
    	}else{  
    		commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 0, 70 );
    		commonValidator.validatePolicyHolderAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 85 );
    	}
    }

    public void processFormAfterSubmitBeforeValidation()
    {
    	// Do nothing
    }
    
    public static List<OptionVO> getPremiumCombinationList( int folded )
    {
        List<OptionVO> premiumCombinationList = new ArrayList<OptionVO>();

        OptionVO optionVO;
        optionVO = new OptionVO( "", "" );
        premiumCombinationList.add( optionVO );

        String value;
        String label;
        for( int i = 90 - folded; i > 0; i = i - folded )
        {
            value = i + ".00";
            label = "Premi Pokok " + i + "%, Premi Top-Up Berkala " + ( 100 - i ) + "%";
            optionVO = new OptionVO( value, label );
            premiumCombinationList.add( optionVO );
        }

        return premiumCombinationList;
    }
    
    public static List<OptionVO> getPremiumCombinationList()
    {
        List<OptionVO> premiumCombinationList = new ArrayList<OptionVO>();

        OptionVO optionVO;
        optionVO = new OptionVO( "", "" );
        premiumCombinationList.add( optionVO );

        String value;
        String label;
        value = 80 + ".00";
        label = "Premi Pokok " + 80 + "%, Premi Top-Up Berkala " + ( 100 - 80 ) + "%";
        optionVO = new OptionVO( value, label );
        premiumCombinationList.add( optionVO );
        
        value = 50 + ".00";
        label = "Premi Pokok " + 50 + "%, Premi Top-Up Berkala " + ( 100 - 50 ) + "%";
        optionVO = new OptionVO( value, label );
        premiumCombinationList.add( optionVO );

        return premiumCombinationList;
    }
    
    
    public static List<OptionVO> getPremiumCombination50List()
    {
        List<OptionVO> premiumCombinationList = new ArrayList<OptionVO>();

        OptionVO optionVO;
        optionVO = new OptionVO( "", "" );
        premiumCombinationList.add( optionVO );

        String value;
        String label;
        
        value = 50 + ".00";
        label = "Premi Pokok " + 50 + "%, Premi Top-Up Berkala " + ( 100 - 50 ) + "%";
        optionVO = new OptionVO( value, label );
        premiumCombinationList.add( optionVO );

        return premiumCombinationList;
    }
    
    
}