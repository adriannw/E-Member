package id.co.sinarmaslife.eproposal.product.product01040151;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040139
 * Program Name   		: Cepr01040139Entry
 * Description         	: Pro safe (192)
 * Environment      	: Java  1.5.0_06
 * Author               : fadly m
 * Version              : 1.0
 * Creation Date    	: Jan 01, 2012 9:18:21 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000EntryInterface;
import id.co.sinarmaslife.eproposal.product.product01040150.Cepr01040150Mapper;
import id.co.sinarmaslife.eproposal.common.lookup.LookupList;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000Generator;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Entry;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.FormatNumber;
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

public class Cepr01040151Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040151Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040139Entry constructor is called ..." );
        setDownloadUrl( "wepr01040151.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040139Entry.initDisplayedForm" );
        displayStandardForm();
        cepr01030102Form.setButtonCountPremiumDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPremiumDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setTotalSumInsuredListDisplay( CommonConst.DISPLAY_FALSE );
        
        cepr01030102Form.setButtonCountTotalSumInsuredDisplay( CommonConst.DISPLAY_FALSE );
        displayAdditionalAssurance( true );
        cepr01030102Form.setEkaSehatFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setTpdFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPayorsClauseFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPersonalAccidentFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setCriticalIllnessFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setWaiverPremiumDisabilityFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setTermRiderFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPremiumListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumFurloughYearDisplay( CommonConst.DISPLAY_TRUE );
     // setPremiumFurloughYearListDisplayHelperDisplay set to true if to display only list (not text box) 
        cepr01030102Form.setPremiumFurloughYearListDisplayHelperDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumFurloughYearListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setTermOfContractDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setTermOfPaymentDisplay( CommonConst.DISPLAY_FALSE );
    //    cepr01030102Form.setMedicalPlusDisplay(CommonConst.DISPLAY_FALSE);
    //    cepr01030102Form.setBabyFlag(CommonConst.CHECKED_FALSE);
               
       //012345
        /*
        String umr = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
        String umr2 = umr.substring(2, 4);
        Integer ur  = Integer.valueOf(umr2);
        
        String umr3 = umr.substring(0, 2);
        Integer ur2  = Integer.valueOf(umr3);
        
        if(ur2==0 && ur>=5 && ur<=8) {
       
        	 cepr01030102Form.setBabyFlag(CommonConst.CHECKED_TRUE);
       }else{
    	   displayAdditionalAssurance( false );
    	   cepr01030102Form.setBabyFlag(CommonConst.CHECKED_FALSE);
       }
         	*/
        if(cepr01030101Form.getBabyFlag()!=CommonConst.CHECKED_FALSE){
        	if(cepr01030101Form.getBabyFlag().equalsIgnoreCase(CommonConst.CHECKED_TRUE)){
        		 cepr01030102Form.setBabyFlag(CommonConst.CHECKED_TRUE);        		 
        	}
        	
        }else{
        	 displayAdditionalAssurance( false );
    	}               
        
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040139Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
       
        	cepr01030102Form.setBabyFlagIsDisabled( CommonConst.DISABLED_TRUE );
       
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040139Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040139Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        Integer[] payModeArr = null;
        Integer termOfPayment = cepr01030102Form.getPremiumFurloughYearCd();
               
        payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_BULANAN, Hardcode.PAY_MODE_CD_SEMESTERAN, Hardcode.PAY_MODE_CD_TRIWULANAN};
               
        List<OptionVO> premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 10000000, 500000000, 10000000 );
        cepr01030102Form.setTotalSumInsuredList( premiumList );
        cepr01030102Form.setTermOfPayment( termOfPayment );
        cepr01030102Form.setPaymentModeCd( payModeArr[ 0 ] );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, payModeArr ) );
                
        if( cepr01030102Form.getRightPartOfBusinessCd()==Cepr01040151Mapper.X2 ){
        	cepr01030102Form.setPremiumFurloughYear( 10 );
        	cepr01030102Form.setPremiumFurloughYearList( LookupList.getSequenceNumberList( 10, 20, false ) );
        }else{
        	cepr01030102Form.setPremiumFurloughYear( 8 );
        	cepr01030102Form.setPremiumFurloughYearList( LookupList.getSequenceNumberList( 8, 20, false ) );
        }
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040139Entry.fillDefaultValueEachTimeFormCalled" );
        
        Integer termOfContract = 0;
        if(cepr01030101Form.getBabyFlag()==CommonConst.CHECKED_FALSE){ 
        	 termOfContract = 22 - cepr01030101Form.getInsuredAge();
        }else{
	       	if(cepr01030101Form.getBabyFlag().equalsIgnoreCase(CommonConst.CHECKED_TRUE)){
	       		termOfContract = 22 - 0;
	       	}	
       	}	
        cepr01030102Form.setTermOfContract( termOfContract );
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        cepr01030102Form.setTermRiderUnitQtyList( LookupList.getSequenceNumberList( 1, 5, false ) );
        cepr01030102Form.setPersonalAccidentUnitQtyList( LookupList.getSequenceNumberList( 1, 3, false ) );
        
        cepr01030102Form.setTotalSumInsuredOptionIsToRefreshPage("true");
        
        List<OptionVO> premiumList = null;
        if( cepr01030102Form.getPaymentModeCd()==Hardcode.PAY_MODE_CD_BULANAN){
        	 if( cepr01030102Form.getRightPartOfBusinessCd()==Cepr01040151Mapper.X2 ){
        		 premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 100000, 2400000, 100000 );
        	 }else{
        		 premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 50000, 2400000, 50000 );
        	 }
        } else if( cepr01030102Form.getPaymentModeCd()==Hardcode.PAY_MODE_CD_TAHUNAN){
        	premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 600000, 28800000, 600000 );
        }else if( cepr01030102Form.getPaymentModeCd()==Hardcode.PAY_MODE_CD_SEMESTERAN){
        	premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 100000, 4800000, 100000 );
        }else if( cepr01030102Form.getPaymentModeCd()==Hardcode.PAY_MODE_CD_TRIWULANAN){
        	premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 200000, 9600000, 200000 );
        }
        		

        cepr01030102Form.setPremiumList( premiumList );
        
        /*
        String umr = FormatDate.getUmur(eproposalManager.selectNowDate(), cepr01030101Form.getInsuredBirthDay());
        String umr2 = umr.substring(2, 4);
        Integer ur  = Integer.valueOf(umr2);
        
        String umr3 = umr.substring(0, 2);
        Integer ur2  = Integer.valueOf(umr3);
        
        if(ur2==0 && ur>=5 && ur<=8) {
        	cepr01030102Form.setBabyChooseCd(String.valueOf(ur));
        }
        */
        cepr01030102Form.setTermOfPayment(cepr01030102Form.getPremiumFurloughYear());
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040139Entry.validateCurrentPage" );
     
        if( cepr01030102Form.getPremium() != null )
        {
                  
            if( cepr01030102Form.getPaymentModeCd()==Hardcode.PAY_MODE_CD_BULANAN){
            	if( cepr01030102Form.getRightPartOfBusinessCd()==Cepr01040151Mapper.X2 ){
            		commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(100000), new BigDecimal(10));
                	commonValidator.validateMaximumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(2400000), new BigDecimal(240));
            	}else{
            		commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(50000), new BigDecimal(5));
                	commonValidator.validateMaximumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(2400000), new BigDecimal(240));
            	}
            } else if( cepr01030102Form.getPaymentModeCd()==Hardcode.PAY_MODE_CD_TAHUNAN){
            	if( cepr01030102Form.getRightPartOfBusinessCd()==Cepr01040151Mapper.X2 ){
            		commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(1200000), new BigDecimal(120));
                	commonValidator.validateMaximumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(28800000), new BigDecimal(2880));
            	}else{
            		commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(600000), new BigDecimal(60));
                	commonValidator.validateMaximumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(28800000), new BigDecimal(2880));
            	}
            }else if( cepr01030102Form.getPaymentModeCd()==Hardcode.PAY_MODE_CD_SEMESTERAN){
            	if( cepr01030102Form.getRightPartOfBusinessCd()==Cepr01040151Mapper.X2 ){
            		commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(600000), new BigDecimal(60));
                	commonValidator.validateMaximumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(14400000), new BigDecimal(1440));
            	}else{
	            	commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(300000), new BigDecimal(30));
	            	commonValidator.validateMaximumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(14400000), new BigDecimal(1440));
            	}
            }else if( cepr01030102Form.getPaymentModeCd()==Hardcode.PAY_MODE_CD_TRIWULANAN){
            	if( cepr01030102Form.getRightPartOfBusinessCd()==Cepr01040151Mapper.X2 ){
                	commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(300000), new BigDecimal(30));
                    commonValidator.validateMaximumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(7200000), new BigDecimal(720));
                }else{
	            	commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(150000), new BigDecimal(15));
	            	commonValidator.validateMaximumMoney( Cepr01030102FormConst.PREMIUM, cepr01030102Form.getPremium() , new BigDecimal(7200000), new BigDecimal(720));
                }
           }
                        
        }
        else
        {
            errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED, "error.formEmpty", null, null );
        }
        
        if( cepr01030102Form.getPremium() == null || cepr01030102Form.getPremium().equals(new BigDecimal(0)) )
        {
        	 errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.formEmpty", null, null );
        }
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040139Entry.validatePreviousPage" );

//        commonValidator.validatePolicyHolderAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 18, 85 );
//        int maxInsuredAge =  Cepr01040139Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd()? 55 : 60;
        commonValidator.validatePolicyHolderAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 85 );
             
        commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 18, 50 );
                
        if( cepr01030102Form.getPremiumFurloughYear() + cepr01030101Form.getInsuredAge() > 60 ){
        	   errors.rejectValue( Cepr01030102FormConst.ASSURANCE_PLAN_CD, "error.maxPremiumFurloughYear", null, null );
        }
        
        
    }

    public BigDecimal computePremium( Integer payModeCd )
    {
        logger.info( "*-*-*-* Cepr01040139Entry.computePremium" );
        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put("lsbs_id", "219");
        sqlParams.put("plan", cepr01030102Form.getRightPartOfBusinessCd());
                
        if(cepr01030101Form.getBabyFlag()==CommonConst.CHECKED_FALSE){ 
        	 sqlParams.put("liUsia", cepr01030101Form.getInsuredAge());
        }else{
        	if(cepr01030101Form.getBabyFlag().equalsIgnoreCase(CommonConst.CHECKED_TRUE)){
        	 sqlParams.put("liUsia", 0);
        	}
        }        
        double premium = LazyConverter.toDouble(eproposalManager.selectPremiKid(sqlParams)) * LazyConverter.toDouble(cepr01030102Form.getTotalSumInsured()) * 0.001 ;
                
        double factor_percentage = 1;

        int paymentModeCd = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd(), -1 );
        
        switch( paymentModeCd )
        {
            case Hardcode.PAY_MODE_CD_TRIWULANAN: factor_percentage = 0.27;  break;
            case Hardcode.PAY_MODE_CD_SEMESTERAN: factor_percentage = 0.525;  break;
            case Hardcode.PAY_MODE_CD_BULANAN   : factor_percentage = 0.1; break;
            default: break;
        }               
        double premi = premium * factor_percentage;
        premi = premi/10000;        
        premi = Math.ceil(premi);
        premi = premi*10000;
        BigDecimal premium2 = new BigDecimal(premi);
       
        return premium2;
    }


    public void processFormAfterSubmitBeforeValidation()
    {        
        if( "onChangePremium".equals( cepr01030102Form.getTheEvent() )
                || "onPressButtonCountTotalSumInsured".equals( cepr01030102Form.getTheEvent() )
                || new BigDecimal( "0" ).equals( cepr01030102Form.getTotalSumInsured() )
                //|| "onPressButtonPreviewProposal".equals( cepr01030102Form.getTheEvent() ) 
            ){
        	
        	//m = Jumlah bulan dalam periode 1 (satu) kami pembayaran kontribusi sesuai cara bayar        	
        	int m = 1;
        	
        	  double manfaat_bln = 0;
              if( cepr01030102Form.getPaymentModeCd()==Hardcode.PAY_MODE_CD_TAHUNAN){
             	 m = 12;
              }else if( cepr01030102Form.getPaymentModeCd()==Hardcode.PAY_MODE_CD_SEMESTERAN){
              	m = 6;
              }else if( cepr01030102Form.getPaymentModeCd()==Hardcode.PAY_MODE_CD_TRIWULANAN){
             	 m = 3;
              }else if( cepr01030102Form.getPaymentModeCd()==Hardcode.PAY_MODE_CD_BULANAN){
              	 m = 1;
               }
              
              manfaat_bln = (cepr01030102Form.getPremium().doubleValue()*10)/m;
              manfaat_bln = 50 * manfaat_bln;
        	 cepr01030102Form.setTotalSumInsured( new BigDecimal( manfaat_bln ) );
        	
        }
        
    }
   
}
