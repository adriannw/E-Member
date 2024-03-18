package id.co.sinarmaslife.eproposal.product.product01040208;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040208Entry (134)
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
import id.co.sinarmaslife.eproposal.dao.Cepr01040208Dao;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000EntryInterface;
import id.co.sinarmaslife.eproposal.model.vo.AmountVO;
import id.co.sinarmaslife.eproposal.model.vo.UlinkValidatorParVO;
import id.co.sinarmaslife.eproposal.product.product01040228.Cepr01040228Mapper;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.LazyConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cepr01040208Entry extends Cepr01040000UlinkEntry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );
    
   // private static final int ASSURANCE_TERM = 80;
    private static final int ASSURANCE_TERM = 100;

    public Cepr01040208Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040208Entry constructor is called ..." );
        setDownloadUrl( "wepr01040208.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040208Entry.initDisplayedForm" );
        displayStandardForm();
        cepr01030102Form.setTotalSumInsuredListDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPremiumListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setHeaderInvestChoiceDisplay( CommonConst.DISPLAY_TRUE );
        
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){ 
        	cepr01030102Form.setPremiumCombinationListDisplay( CommonConst.DISPLAY_TRUE );
        }else{
        	cepr01030102Form.setPremiumCombinationListDisplay( CommonConst.DISPLAY_FALSE );
        }
        
        cepr01030102Form.setButtonCountTotalSumInsuredDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumFurloughYearDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumFurloughYearListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonRiderAndButtonTopupDisplay( CommonConst.DISPLAY_TRUE );
        
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11 ){
        	cepr01030102Form.setButtonRiderDisplay( CommonConst.DISPLAY_FALSE );
        	cepr01030102Form.setButtonTopupDisplay( CommonConst.DISPLAY_FALSE );
        }else{
        	cepr01030102Form.setButtonRiderDisplay( CommonConst.DISPLAY_TRUE );
        	cepr01030102Form.setButtonTopupDisplay( CommonConst.DISPLAY_TRUE );
        }    
        cepr01030102Form.setBasePremiumDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setTopUpPremiumDisplay( CommonConst.DISPLAY_TRUE );
        
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){ 
        	// setPremiumFurloughYearListDisplayHelperDisplay set to true if to display only list (not text box) 
            cepr01030102Form.setPremiumFurloughYearListDisplayHelperDisplay( CommonConst.DISPLAY_FALSE );
        }else{
        	// setPremiumFurloughYearListDisplayHelperDisplay set to true if to display only list (not text box) 
            cepr01030102Form.setPremiumFurloughYearListDisplayHelperDisplay( CommonConst.DISPLAY_TRUE );
        }
       
        cepr01030103Form.setPayorCiDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorSpouseTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiver5Tpd10TpdDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiver5Ci10CiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverTpdDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayor5Ci10CiDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayor5Tpd10TpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        
        cepr01030103Form.setPayorCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorTpdCiDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverTpdCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setHcpDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setEkaSehatInnerLimitDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setLadiesMedExpenseDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setLadiesMedExpenseInnerLimitDisplay( CommonConst.DISPLAY_TRUE ); 
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X3 ){
            cepr01030103Form.setWaiver5Tpd10CiDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030103Form.setPayor5Tpd10CiDeathDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030103Form.setHcpFamilyDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030103Form.setTermRiderDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030103Form.setLadiesInsDisplay( CommonConst.DISPLAY_TRUE );
        }else  if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X4 ){
            cepr01030103Form.setWaiver5Tpd10CiDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setPayor5Tpd10CiDeathDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setHcpFamilyDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setTermRiderDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setLadiesInsDisplay( CommonConst.DISPLAY_FALSE );
        }else  if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6 
        		|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8 
        		|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X12 ){
        	cepr01030103Form.setPaDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setWaiver5Tpd10CiDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setPayor5Tpd10CiDeathDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setHcpFamilyDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setTermRiderDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setLadiesInsDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setLadiesMedExpenseDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setLadiesMedExpenseInnerLimitDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setMedicalPlusDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setEarlyStageCi99Display( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setEkaSehatDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setEkaSehatInnerLimitDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setLadiesInsDisplay(CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setHcpLadiesDisplay(CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setHcpDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030103Form.setHcpProviderDisplay( CommonConst.DISPLAY_FALSE );
        }else  if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
        	   cepr01030103Form.setHcpDisplay( CommonConst.DISPLAY_FALSE );
        	   cepr01030103Form.setHcpFamilyDisplay( CommonConst.DISPLAY_FALSE );
        	   cepr01030103Form.setHcpProviderDisplay( CommonConst.DISPLAY_FALSE );
        	   cepr01030103Form.setHcpLadiesDisplay(CommonConst.DISPLAY_FALSE );
        	   cepr01030103Form.setLadiesMedExpenseDisplay( CommonConst.DISPLAY_FALSE );
               cepr01030103Form.setLadiesMedExpenseInnerLimitDisplay( CommonConst.DISPLAY_FALSE );
               cepr01030103Form.setLadiesInsDisplay( CommonConst.DISPLAY_FALSE );
        	   cepr01030103Form.setEkaSehatDisplay( CommonConst.DISPLAY_FALSE ); //Decommission Rider SMAC/SMEX Oktober 2021 (NCR/2021/10/106)
        	   cepr01030103Form.setEkaSehatExtraDisplay( CommonConst.DISPLAY_FALSE ); //Decommission Rider SMAC/SMEX Oktober 2021 (NCR/2021/10/106)
        	   cepr01030103Form.setScholarshipDisplay(CommonConst.DISPLAY_TRUE );
        	   cepr01030103Form.setPayor5Tpd10CiDeathDisplay( CommonConst.DISPLAY_TRUE );
        	   cepr01030103Form.setPayorTpdCiDeathDisplay( CommonConst.DISPLAY_TRUE );
        	   cepr01030103Form.setWaiver5Tpd10CiDisplay( CommonConst.DISPLAY_TRUE );
        	   cepr01030103Form.setWaiverTpdCiDisplay( CommonConst.DISPLAY_TRUE );
        }
        cepr01030102Form.setPesertaHcpProviderDisplayStandAloneDisplay( CommonConst.DISPLAY_FALSE );
        
        /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
       
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040208Entry.initDisabledForm" );

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
        cepr01030102Form.setAggresiveUsdIsDisabled( CommonConst.DISABLED_FALSE  ); /**USD Fund BSIM Products**/
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_FALSE );

        cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_FALSE );
        if( cepr01030102Form.getRightPartOfBusinessCd() ==  Cepr01040208Mapper.X13 ){
        	cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
        }else{
        	 cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
        }
        cepr01030102Form.setTopUpPremiumIsDisabled( CommonConst.DISABLED_TRUE );
               
    }

    public void initReadOnlyForm()
    {
        readOnlyAllForm( false );
        cepr01030102Form.setBasePremiumReadOnly(CommonConst.READ_ONLY_FALSE);
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
    	
      
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
    	// move from fillDefaultValueEachTimeRightPartOfBusinessCdChanged
    	  if( cepr01030102Form.getRightPartOfBusinessCd() ==  Cepr01040208Mapper.X3 ){
        	  cepr01030102Form.setPremiumFurloughYear( 10 );
              cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_TAHUNAN );
              cepr01030102Form.setPremiumFurloughYearList( LookupList.getSequenceNumberList( 10, 10, 10, false ) );
        }else  if( cepr01030102Form.getRightPartOfBusinessCd() ==  Cepr01040208Mapper.X4 || cepr01030102Form.getRightPartOfBusinessCd() ==  Cepr01040208Mapper.X5
        		|| cepr01030102Form.getRightPartOfBusinessCd() ==  Cepr01040208Mapper.X6 || cepr01030102Form.getRightPartOfBusinessCd() ==  Cepr01040208Mapper.X7
        		|| cepr01030102Form.getRightPartOfBusinessCd() ==  Cepr01040208Mapper.X8 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9
        		|| cepr01030102Form.getRightPartOfBusinessCd() ==  Cepr01040208Mapper.X10|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11
        		|| cepr01030102Form.getRightPartOfBusinessCd() ==  Cepr01040208Mapper.X12 ){
        	  cepr01030102Form.setPremiumFurloughYear( 1 );
              cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_SEKALIGUS);
              cepr01030102Form.setPremiumFurloughYearList( LookupList.getSequenceNumberList( 1, 1, 1, false ) );
        }
        else  if( cepr01030102Form.getRightPartOfBusinessCd() ==  Cepr01040208Mapper.X13 ){        	
        	cepr01030102Form.setPremiumFurloughYearList( LookupList.getSequenceNumberList( 5, ASSURANCE_TERM - cepr01030101Form.getInsuredAge(), false ) );
        	//cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_TAHUNAN );
        }
    	//end move
    	  
        cepr01030102Form.setPremiumOptionIsToRefreshPage( CommonConst.REFRESH_TRUE );
        cepr01030102Form.setFixIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setDynamicIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setAggresiveIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setCashFundIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setExcellinkEquityIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );

        cepr01030102Form.setSecureUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setDynamicUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        /**USD Fund BSIM Products**/
        cepr01030102Form.setAggresiveUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6 
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8 
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X12){ //SIMAS PRIME LINK & SMiLe AssetPro(Agency) & SMiLe LINK INVESTA
        	cepr01030102Form.setTermOfContract( 100 - cepr01030101Form.getInsuredAge() );
        }else{
        	cepr01030102Form.setTermOfContract( ASSURANCE_TERM - cepr01030101Form.getInsuredAge() );
        }
       
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X3 ){
        	 cepr01030103Form.setPaRiskList( getPaRiskABDList() );
        }else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X4 ){
        	 cepr01030103Form.setPaRiskList( getPaRiskAList() );
        }
        
        List<OptionVO> premiumList = null;
        
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
            cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        }else{
            cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD } ) );
        }
        
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X4 || cepr01030102Form.getRightPartOfBusinessCd() ==  Cepr01040208Mapper.X5 
        	|| cepr01030102Form.getRightPartOfBusinessCd() ==  Cepr01040208Mapper.X6 || cepr01030102Form.getRightPartOfBusinessCd() ==  Cepr01040208Mapper.X7
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8  || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X12){
        	cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_SEKALIGUS } ) );
        	cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombinationFoldedList(100) );       
        }else {
        	cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_SEMESTERAN, Hardcode.PAY_MODE_CD_TRIWULANAN, Hardcode.PAY_MODE_CD_BULANAN } ) );
        	cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination10FoldedList() );
        }
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTermOfPayment( cepr01030102Form.getPremiumFurloughYear() );
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
        	cepr01030103Form.setEkaSehatFlag( CommonConst.CHECKED_FALSE );
        	cepr01030103Form.setEkaSehatDisplay( CommonConst.DISPLAY_FALSE );
        }
        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            cepr01030102Form.setFixIdrDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setFixIdrListDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setDynamicIdrDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setDynamicIdrListDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setAggresiveIdrDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setAggresiveIdrListDisplay( CommonConst.DISPLAY_TRUE );
            
            if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10 || cepr01030102Form.getRightPartOfBusinessCd() >= Cepr01040208Mapper.X12
            	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 ||	cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8 ){
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
	            
	            if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10 ){
	            	cepr01030102Form.setFixIdrDisplay( CommonConst.DISPLAY_FALSE );
	            	cepr01030102Form.setDynamicIdrDisplay( CommonConst.DISPLAY_FALSE );
	            	cepr01030102Form.setAggresiveIdrDisplay( CommonConst.DISPLAY_FALSE );
	            	cepr01030102Form.setCashFundIdrDisplay( CommonConst.DISPLAY_FALSE );
	            	cepr01030102Form.setExcellinkEquityIdrDisplay( CommonConst.DISPLAY_FALSE );	            	  
	            	cepr01030102Form.setLjiAggresiveSimasCd( "37" );
	            	cepr01030102Form.setAggresiveSimasIdr( new BigDecimal(100) );
	            }
            } 
            else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 
            	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11 
            	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X12){ 
            	 cepr01030102Form.setCashFundIdrDisplay( CommonConst.DISPLAY_TRUE );
                 cepr01030102Form.setCashFundIdrListDisplay( CommonConst.DISPLAY_TRUE ); 
                 cepr01030102Form.setExcellinkEquityIdrDisplay( CommonConst.DISPLAY_TRUE );
 	             cepr01030102Form.setExcellinkEquityListDisplay( CommonConst.DISPLAY_TRUE );                 
            }
            else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 ){           
                cepr01030102Form.setLjiFixListDisplay( CommonConst.DISPLAY_TRUE );
                cepr01030102Form.setLjiFixList(LookupList.getJenisInvestSimasUsdFix());
                cepr01030102Form.setLjiDynamicListDisplay( CommonConst.DISPLAY_TRUE );
                cepr01030102Form.setLjiDynamicList(LookupList.getJenisInvestSimasUsdDynamic());
                cepr01030102Form.setLjiAggresiveListDisplay( CommonConst.DISPLAY_TRUE );
                cepr01030102Form.setLjiAggresiveList(LookupList.getJenisInvestSimasUsdAggresiv());
                
                cepr01030102Form.setLjiSecureUsdListDisplay( CommonConst.DISPLAY_FALSE );  
                cepr01030102Form.setLjiDynamicUsdListDisplay( CommonConst.DISPLAY_FALSE );
            }
            
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            cepr01030102Form.setSecureUsdDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setSecureUsdListDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setDynamicUsdDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setDynamicUsdListDisplay( CommonConst.DISPLAY_TRUE );
                        
            if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5){
                cepr01030102Form.setLjiSecureUsdListDisplay( CommonConst.DISPLAY_TRUE );  
                cepr01030102Form.setLjiDynamicUsdListDisplay( CommonConst.DISPLAY_TRUE );
                cepr01030102Form.setLjiSecureUsdList(LookupList.getSecureUsdList());
                cepr01030102Form.setLjiDynamicUsdList(LookupList.getDynamicUsdList());
                /**USD Fund BSIM Products**/
                cepr01030102Form.setAggresiveUsdDisplay(CommonConst.DISPLAY_TRUE);
                cepr01030102Form.setAggresiveUsdListDisplay( CommonConst.DISPLAY_TRUE );
                cepr01030102Form.setLjiAggresiveUsdListDisplay( CommonConst.DISPLAY_TRUE );
                cepr01030102Form.setLjiAggresiveUsdList(LookupList.getJenisGlobalAggresiveUsdList());
             }
             else  if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9){
            	 cepr01030102Form.setLjiFixListDisplay( CommonConst.DISPLAY_FALSE );                 
                 cepr01030102Form.setLjiDynamicListDisplay( CommonConst.DISPLAY_FALSE );  
                 cepr01030102Form.setLjiAggresiveListDisplay( CommonConst.DISPLAY_FALSE );
                 
                 cepr01030102Form.setLjiSecureUsdListDisplay( CommonConst.DISPLAY_TRUE );  
                 cepr01030102Form.setLjiDynamicUsdListDisplay( CommonConst.DISPLAY_TRUE );
                 cepr01030102Form.setLjiSecureUsdList(LookupList.getJenisInvestSimasUsdSecure());
             	 cepr01030102Form.setLjiDynamicUsdList(LookupList.getJenisInvestSimasUsdDynamicDollar());
             }            
        }
        
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 ){     
	        cepr01030102Form.setLjiFixListIsDisabled( CommonConst.DISABLED_TRUE  );
	        cepr01030102Form.setLjiDynamicListIsDisabled( CommonConst.DISABLED_TRUE  );
	        cepr01030102Form.setLjiAggresiveListIsDisabled( CommonConst.DISABLED_TRUE  );
	        
	        cepr01030102Form.setLjiSecureUsdListIsDisabled( CommonConst.DISABLED_TRUE  );
	        cepr01030102Form.setLjiDynamicUsdListIsDisabled( CommonConst.DISABLED_TRUE  );
        }        
		
        cepr01030102Form.setPremiumList( premiumList );
            
       double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
       double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
     //  refreshBaseTopupPremium();

        if( "onChangePremium".equals( cepr01030102Form.getTheEvent() )
            || "onPressButtonCountTotalSumInsured".equals( cepr01030102Form.getTheEvent() )
            || new BigDecimal( "0" ).equals( cepr01030102Form.getTotalSumInsured() )
            || "onPressButtonPreviewProposal".equals( cepr01030102Form.getTheEvent() ) 
            )
        {
            double idec_rate;
            double adec_premi = premium;
            double idec_up = 0;

            int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );

            if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEKALIGUS ){
            	if( cepr01030102Form.getPremium() != null ){
            		double premium_sekaligus;
            		if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6
            			|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8
            			|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10
            			|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11|| cepr01030102Form.getRightPartOfBusinessCd() >= Cepr01040208Mapper.X12){//SIMAS PRIME LINK
            			 premium_sekaligus = LazyConverter.toDouble( cepr01030102Form.getBasePremium() );
            			 
            			// double topup_premium = LazyConverter.toDouble( cepr01030102Form.getPremium()) - LazyConverter.toDouble( cepr01030102Form.getBasePremium() );
            			// cepr01030102Form.setTopUpPremium( new BigDecimal(topup_premium));            			            			 
            		}else{
            			 premium_sekaligus = LazyConverter.toDouble( cepr01030102Form.getPremium() );            			
            		}            		
            		double factor = 125;
            		double up_sekaligus = premium_sekaligus * ( factor / 100 ); 
            		
            		  if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                      {
                          idec_up = Math.max( up_sekaligus, 15000000 );
                      }
            		  else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                      {
                          idec_up = Math.max( up_sekaligus, 1500 );
                      }
            		  
            		 double topup_premium = LazyConverter.toDouble( cepr01030102Form.getPremium()) - LazyConverter.toDouble( cepr01030102Form.getBasePremium() );            	        
           			 cepr01030102Form.setTopUpPremium( new BigDecimal(topup_premium));
                 	
           			if( cepr01030101Form.getInsuredAge() > 70  &&  cepr01030101Form.getInsuredAge() < 81){
          			  if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                        {
                            idec_up =  15000000;
                        }
              		  else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                        {
                            idec_up = 1500;
                        }
          			
           			}
            		cepr01030102Form.setTotalSumInsured( new BigDecimal( idec_up ) );
            	}
            	
            }else{
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
                if(  "onPressButtonCountTotalSumInsured".equals( cepr01030102Form.getTheEvent() ) ){
                	 cepr01030102Form.setTotalSumInsured( new BigDecimal( idec_up ) );
                }
            }
        }
     
        
        
    }

    public void validateCurrentPage()
    {
//        compute max value for base premium
        UlinkValidatorParVO parVO = new UlinkValidatorParVO();
        parVO.setUnder5YearsBerkalaMaxBasePremiumVO( new AmountVO( 1000000000, 100000 ) );
        parVO.setUnder5YearsSekaligusMaxBasePremiumVO( new AmountVO( 1000000000, 100000 ) );
        parVO.setBeyond5YearsBerkalaMaxBasePremiumVO( new AmountVO( 1000000000, 100000 ) );
        parVO.setBeyond5YearsSekaligusMaxBasePremiumVO( new AmountVO( 1000000000, 100000 ) );
    	super.validateCurrentPage( parVO );

        validateCurrentPageInCommon();
    }

    public void validatePreviousPage()
    {    
    	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10){	
    		commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 0, 80 );
    	}else{
    		commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 0, 70 );
    	}
        commonValidator.validatePolicyHolderAge(Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 85);
    }

    public void processFormAfterSubmitBeforeValidation()
    {   
    	int c=1;
    	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6 
    		|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8 
    		|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10
    		|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X12 ){ //SIMAS PRIME LINK & SMiLe AssetPro(Agency)
    	  double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
          double premiumCombinationPercent = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );
        //  cepr01030102Form.setBasePremium( new BigDecimal( premium * premiumCombinationPercent / 100 ) );
       //   cepr01030102Form.setTopUpPremium( new BigDecimal( premium * ( 100 - premiumCombinationPercent  ) / 100 ) );
          if( cepr01030101Form.getInsuredAge() > 70  &&  cepr01030101Form.getInsuredAge() < 81){
        	  if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
              {
        		  cepr01030102Form.setBasePremium( new BigDecimal( 10000000 ) );
              }
    		  else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
    		  {
    			  cepr01030102Form.setBasePremium( new BigDecimal( 1000 ) );
    		  }
		  } 
          if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10 ){
				 cepr01030102Form.setBasePremium( new BigDecimal(10000000) );
		  }
         // if( "onPressChangeBasePremium".equals( cepr01030102Form.getTheEvent() )){
        	 double topup_premium = LazyConverter.toDouble( cepr01030102Form.getPremium()) - LazyConverter.toDouble( cepr01030102Form.getBasePremium() );        
  			 cepr01030102Form.setTopUpPremium( new BigDecimal(topup_premium));        	
         // }
         
          
    	}else{
    		refreshBaseTopupPremium();
    	}
    	
    	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
	    	 double premium_sekaligus = LazyConverter.toDouble( cepr01030102Form.getBasePremium() );
	    	 double idec_rate = 5000;
	    	 double idec_up = premium_sekaligus * idec_rate / 1000;
	          // count totalSumInsured with premiumCombinationPercent    
	    	  int li_pmode = cepr01030102Form.getPaymentModeCd();
	    	    if( li_pmode == Hardcode.PAY_MODE_CD_TRIWULANAN )
	    	    	idec_up = 4 * idec_up;
                else if( li_pmode == Hardcode.PAY_MODE_CD_SEMESTERAN )
                	idec_up = 2 * idec_up;
                else if( li_pmode == Hardcode.PAY_MODE_CD_BULANAN )
                	idec_up = 12 * idec_up;
	    	    if(  "onPressButtonCountTotalSumInsured".equals( cepr01030102Form.getTheEvent() ) ){
               	 cepr01030102Form.setTotalSumInsured( new BigDecimal( idec_up ) );
               }
    	}
    }

    public void validateCurrentPageInCommon()
    {
        double basePremium = LazyConverter.toDouble( cepr01030102Form.getBasePremium() );
        double totalPremium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double minPremiumIdr;
        double minPremiumUsd;

        switch( cepr01030102Form.getPaymentModeCd() )
        {
            case Hardcode.PAY_MODE_CD_SEKALIGUS:
            	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6
            		|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8 
            		|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10
            		|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X12){
	                minPremiumIdr = 10000000;
	                minPremiumUsd = 1000;
            	}else{
            		minPremiumIdr = 5000000;
 	                minPremiumUsd = 500;
            	}
                break;
            case Hardcode.PAY_MODE_CD_TAHUNAN:
            	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
            		 minPremiumIdr = 6000000; /**Perubahan Premi bulanan Smart Platinum Link untuk Karyawan BTN**/
            		 minPremiumUsd = 600;
            	}else{
            		 minPremiumIdr = 25000000;
                     minPremiumUsd = 2500;
            	}
                break;
            case Hardcode.PAY_MODE_CD_SEMESTERAN:
            	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
            		  minPremiumIdr = 3000000;/**Perubahan Premi bulanan Smart Platinum Link untuk Karyawan BTN**/
            		  minPremiumUsd = 300;
            	}else{
            		  minPremiumIdr = 12500000;
                      minPremiumUsd = 1250;
            	}              
                break;
            case Hardcode.PAY_MODE_CD_TRIWULANAN:
            	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
          		  	minPremiumIdr = 1500000;/**Perubahan Premi bulanan Smart Platinum Link untuk Karyawan BTN**/
          		  	minPremiumUsd = 150;
            	}else{
            		 minPremiumIdr = 6250000;
                     minPremiumUsd = 625;
            	}              
                break;
            case Hardcode.PAY_MODE_CD_BULANAN:
            	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
          		  	minPremiumIdr = 500000;/**Perubahan Premi bulanan Smart Platinum Link untuk Karyawan BTN**/
          		  	minPremiumUsd = 50;
            	}else{
            		minPremiumIdr = 2500000;
                    minPremiumUsd = 500;
            	}
                break;
            default:
                throw new RuntimeException( "*-*-*-* cepr01030102Form.getPaymentModeCd() not listed in Cepr01040208Entry.validateCurrentPage" );
        }
        double topUpPremium = LazyConverter.toDouble( cepr01030102Form.getTopUpPremium() );

        if( cepr01030102Form.getPremium() != null )
        {
        	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
                commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, totalPremium , minPremiumIdr, minPremiumUsd );
        	}else{
        		  commonValidator.validateMinimumMoney( Cepr01030102FormConst.BASE_PREMIUM, basePremium, minPremiumIdr, minPremiumUsd ); 
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
        
        if( cepr01030102Form.getBasePremium() == null  )
        {
            errors.rejectValue( Cepr01030102FormConst.BASE_PREMIUM, "error.formEmpty", null, null );
        }
        //adrian+deddy commonexceptionResolver        
        if( cepr01030102Form.getPremium() != null && cepr01030102Form.getBasePremium() != null  )
        {        	
	        if( cepr01030102Form.getBasePremium().compareTo(cepr01030102Form.getPremium()) >0 )
	        {
	        //	String cc = cepr01030102Form.getPremium().doubleValue();
	            errors.rejectValue( Cepr01030102FormConst.BASE_PREMIUM, "error.valueMax", new Object[]{ commonUsedBusiness.toMoneyWithCurrencyCd(cepr01030102Form.getPremium())}, null );
	    }}
        
        commonValidator.validateTenFoldedForAllInvestment();
//      commonValidator.validatePremiumFurloughYear( Cepr01030102FormConst.PREMIUM_FURLOUGH_YEAR, 5, 10 );
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 ){        	
        	  double val = LazyConverter.toDouble( cepr01030102Form.getFixSimasIdr() );
              if( val % 10 != 0 )
              {
                  errors.rejectValue( Cepr01030102FormConst.FIX_SIMAS_IDR, "error.investmentPercentMustInTenFolded", null, null );
              }
              val = LazyConverter.toDouble( cepr01030102Form.getDynamicSimasIdr() );
              if( val % 10 != 0 )
              {
                  errors.rejectValue( Cepr01030102FormConst.DYNAMIC_SIMAS_IDR, "error.investmentPercentMustInTenFolded", null, null );
              }
              val = LazyConverter.toDouble( cepr01030102Form.getAggresiveSimasIdr() );
              if( val % 10 != 0 )
              {
                  errors.rejectValue( Cepr01030102FormConst.AGRESSIVE_SIMAS_IDR, "error.investmentPercentMustInTenFolded", null, null );
              }
        }
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
	        if( cepr01030102Form.getPremiumFurloughYear().intValue() < 5 ){
			errors.rejectValue( Cepr01030102FormConst.PREMIUM_FURLOUGH_YEAR, "error.premiumFurloughYearMinimum", new Object[]{ "5" }, null );
		}}
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
    
    public static List<OptionVO> getPaRiskABDList()
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
    
    public static List<OptionVO> getPaRiskAList()
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

}