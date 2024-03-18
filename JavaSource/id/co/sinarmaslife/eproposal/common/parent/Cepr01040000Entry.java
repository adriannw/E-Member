package id.co.sinarmaslife.eproposal.common.parent;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040000Entry
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jun 22, 2007 10:31:16 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.business.*;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.lookup.Cepr00000000ComboLookupBusiness;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.product.product01040139.Cepr01040139Entry;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.*;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.standard.util.StringUtil;
import id.co.sinarmaslife.standard.util.LazyConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040000Entry
{
    protected final Log logger = LogFactory.getLog( getClass() );
    protected EproposalManager eproposalManager;
    protected Cepr00000000EditorUtil editorUtil;
    protected Cepr00000000ComboLookupBusiness comboLookupBusiness;
    protected Cepr00000000GeneralBusiness generalBusiness;
    protected Cepr00000000CommonUsedBusiness commonUsedBusiness;
    protected Cepr01040000CommonValidator commonValidator;
    protected Object command;
    protected Errors errors;
    protected Integer rightPartOfBusinessCd;
    protected String downloadUrl;
    protected Map<String, Object> dataMap;

    protected CredentialsVO credentialsVO;
    protected Cepr01030101Form cepr01030101Form;
    protected Cepr01030102Form cepr01030102Form;
    protected Cepr01030103Form cepr01030103Form;
    protected Cepr01030104Form cepr01030104Form;
    protected Cepr01030106Form cepr01030106Form;
    protected Cepr01030107Form cepr01030107Form;
    protected Cepr01030108Form cepr01030108Form;
    protected Cepr01030109Form cepr01030109Form;
    protected Cepr01030110Form cepr01030110Form;
    protected Cepr01030111Form cepr01030111Form;
    protected Cepr01030112Form cepr01030112Form;
    protected Cepr01030113Form cepr01030113Form;
    protected Cepr01030114Form cepr01030114Form;
    
    public String getDownloadUrl()
    {
        return downloadUrl;
    }

    public void setDownloadUrl( String downloadUrl )
    {
        this.downloadUrl = downloadUrl;
    }

    public Map<String, Object> getDataMap()
    {
        return dataMap;
    }

    public void setDataMap( Map<String, Object> dataMap )
    {
        this.dataMap = dataMap;
    }

    public Cepr01040000Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        this.eproposalManager = eproposalManager;
        this.editorUtil = editorUtil;
        this.comboLookupBusiness = new Cepr00000000ComboLookupBusiness( eproposalManager, editorUtil );
        this.generalBusiness = new Cepr00000000GeneralBusiness( eproposalManager, editorUtil );
        this.commonUsedBusiness = new Cepr00000000CommonUsedBusiness( eproposalManager, editorUtil, command );
        this.commonValidator = new Cepr01040000CommonValidator( eproposalManager, editorUtil, command, errors );
        this.command = command;
        this.errors = errors;

        this.credentialsVO = ( ( Cepr01030000HoldingForm ) command ).getCredentialsVO();
        this.cepr01030101Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030101Form();
        this.cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        this.cepr01030103Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030103Form();
        this.cepr01030104Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030104Form();
        this.cepr01030106Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030106Form();
        this.cepr01030107Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030107Form();
        this.cepr01030108Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030108Form();
        this.cepr01030109Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030109Form();
        this.cepr01030110Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030110Form();
        this.cepr01030111Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030111Form();
        this.cepr01030112Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030112Form();
        this.cepr01030113Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030113Form();
        this.cepr01030114Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030114Form();
        
        this.rightPartOfBusinessCd = cepr01030102Form.getRightPartOfBusinessCd();
    }

    protected void displayAdditionalAssurance( boolean isToDisplay )
    {
        logger.info( "*-*-*-* Cepr01040000Entry.displayAdditionalAssurance" );
        logger.info( "*-*-*-* isToDisplay = " + isToDisplay );
        String displayCss;
        if( isToDisplay )
        {
            displayCss = CommonConst.DISPLAY_TRUE;
        }
        else
        {
            displayCss = CommonConst.DISPLAY_FALSE;
        }

        cepr01030102Form.setHeaderAdditionalAssuranceDisplay( displayCss );
        cepr01030102Form.setTermRiderFlagDisplay( displayCss );
        cepr01030102Form.setPersonalAccidentFlagDisplay( displayCss );
        cepr01030102Form.setCriticalIllnessFlagDisplay( displayCss );
        cepr01030102Form.setWaiverPremiumDisabilityFlagDisplay( displayCss );
        cepr01030102Form.setEkaSehatFlagDisplay( displayCss );
        cepr01030102Form.setTpdFlagDisplay( displayCss );
    }

    protected void checkedAdditionalAssurance( boolean isChecked )
    {
        String checked;
        if( isChecked )
        {
        	checked = CommonConst.CHECKED_TRUE;
        }
        else
        {
        	checked = CommonConst.CHECKED_FALSE;
        }

        cepr01030102Form.setTermRiderFlag( checked );
        cepr01030102Form.setPersonalAccidentFlag( checked );
        cepr01030102Form.setCriticalIllnessFlag( checked );
        cepr01030102Form.setWaiverPremiumDisabilityFlag( checked );
        cepr01030102Form.setEkaSehatFlag( checked );
        cepr01030102Form.setTpdFlag( checked );
    }
    
    protected void disabledAllForm( boolean isDisabled )
    {
        logger.info( "*-*-*-* Cepr01040000Entry.disabledAllForm" );
        String value;
        if( isDisabled )
        {
            value = CommonConst.DISABLED_TRUE;
        }
        else
        {
            value = CommonConst.DISABLED_FALSE;
        }
        cepr01030102Form.setAssurancePlanListIsDisabled( value );
        cepr01030102Form.setPremiumIsDisabled( value );      
        cepr01030102Form.setPremiumListIsDisabled( value );
        cepr01030102Form.setClazzListIsDisabled( value );
        cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_TRUE);  //CR#: NCR/2020/08/019 FLAG VIP (IGA)
        cepr01030102Form.setCurrencyListIsDisabled( value );
        cepr01030102Form.setCriticalIllnessFlagIsDisabled( value );
        cepr01030102Form.setCurrencyListIsDisabled( value );
        cepr01030102Form.setInvestRateInPercentIsDisabled( value );
        cepr01030102Form.setPaymentModeListIsDisabled( value );
        cepr01030102Form.setSmileLadiesPackageListIsDisabled( value );
        cepr01030102Form.setSecondPackageListIsDisabled( value );
        cepr01030102Form.setEducationPackageListIsDisabled( value );
        cepr01030102Form.setProtectorPackageListIsDisabled( value );
        cepr01030102Form.setProviderTypeListIsDisabled( value );
        cepr01030102Form.setSmilePensionPackageListIsDisabled( value );
        cepr01030102Form.setSpecialOfferListIsDisabled( value );
        cepr01030102Form.setPayorsClauseFlagIsDisabled( value );
        cepr01030102Form.setPersonalAccidentFlagIsDisabled( value );
        cepr01030102Form.setPersonalAccidentUnitQtyListIsDisabled( value );
        cepr01030102Form.setEkaSehatFlagIsDisabled( value );
        cepr01030102Form.setEkaSehatListIsDisabled( value );
        cepr01030102Form.setTermOfContractIsDisabled( value );
        cepr01030102Form.setTermOfPaymentIsDisabled( value );
        cepr01030102Form.setTermRiderFlagIsDisabled( value );
        cepr01030102Form.setTermRiderUnitQtyListIsDisabled( value );
        cepr01030102Form.setTotalSumInsuredIsDisabled( value );
        cepr01030102Form.setTotalSumInsuredListIsDisabled( value );
        cepr01030102Form.setWaiverPremiumDisabilityFlagIsDisabled( value );
        cepr01030102Form.setMgiListIsDisabled( value );
        cepr01030102Form.setMtiListIsDisabled( value );
        cepr01030102Form.setJenisListIsDisabled( value );
        cepr01030102Form.setTargetListIsDisabled( value );
        cepr01030102Form.setTaxBeforeAfterListIsDisabled( value );
        cepr01030102Form.setFixIdrIsDisabled( value );
        cepr01030102Form.setPremiumFurloughYearIsDisabled( value );
        
        cepr01030102Form.setLjiFixListIsDisabled( value );
        cepr01030102Form.setLjiDynamicListIsDisabled( value );
        cepr01030102Form.setLjiAggresiveListIsDisabled( value );
        cepr01030102Form.setLjiCashFundListIsDisabled( value );
        
        cepr01030102Form.setLjiExcellinkEquityIsDisabled( value );
        
        cepr01030102Form.setLjiSecureUsdListIsDisabled( value );
        cepr01030102Form.setLjiDynamicUsdListIsDisabled( value );
        cepr01030102Form.setLjiAggresiveUsdListIsDisabled(value);/**USD Fund BSIM Products**/
        
        cepr01030102Form.setTopUpPremiumIsDisabled( value );        
              
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
        
        Integer linebus = eproposalManager.selectLineBus(cepr01030102Form.getLeftPartOfBusinessCd()); //IGA 25112020 | Penambahan validasi prod syariah tdk bisa ambil VIP
        if (linebus != null){
        	if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
        		if(premi_pokok >= 100000000 && cara_bayar == Hardcode.PAY_MODE_CD_TAHUNAN && linebus != 3){ //IGA 25112020 | Penambahan validasi prod syariah tdk bisa ambil VIP
        			cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_FALSE);  
        		}else if(premi_pokok == 0 && premi >= 100000000 && cara_bayar == Hardcode.PAY_MODE_CD_TAHUNAN && linebus != 3){ //IGA 25112020 | Penambahan validasi prod syariah tdk bisa ambil VIP
        			cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_FALSE);
        		}else if(premi_pokok >= 100000000 && cara_bayar != Hardcode.PAY_MODE_CD_TAHUNAN && linebus != 3){
        			cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_FALSE);
        		}else if(premi_pokok == 0 && premi >= 100000000 && cara_bayar != Hardcode.PAY_MODE_CD_TAHUNAN && linebus != 3){
        			cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_FALSE);
        		}
        		else{
        			cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_TRUE );  
        			cepr01030102Form.setFlagOfVIP(CommonConst.CHECKED_FALSE);
        		}
            }  else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            { 
            	if(premi_pokok >= 10000 && cara_bayar == Hardcode.PAY_MODE_CD_TAHUNAN && linebus != 3){ //IGA 25112020 | Penambahan validasi prod syariah tdk bisa ambil VIP
            		cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_FALSE );  
            	}else if(premi_pokok == 0 && premi >= 10000 && cara_bayar == Hardcode.PAY_MODE_CD_TAHUNAN && linebus != 3){ //IGA 25112020 | Penambahan validasi prod syariah tdk bisa ambil VIP
            		cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_FALSE);
            	}else if(premi_pokok >= 10000 && cara_bayar != Hardcode.PAY_MODE_CD_TAHUNAN && linebus != 3){
        			cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_FALSE);
        		}else if(premi_pokok == 0 && premi >= 10000 && cara_bayar != Hardcode.PAY_MODE_CD_TAHUNAN && linebus != 3){
        			cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_FALSE);
        		}
            	else{
            		cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_TRUE ); 
            		cepr01030102Form.setFlagOfVIP(CommonConst.CHECKED_FALSE);
            	}
            }
        	
        	 if( credentialsVO != null && credentialsVO.getIsVIP()==true ){
             	cepr01030102Form.setFlagOfVIP(CommonConst.CHECKED_TRUE);
             	cepr01030102Form.setFlagOfVIPIsDisabled( CommonConst.DISABLED_TRUE ); 
             }
        }
        
    }

    protected void readOnlyAllForm( boolean isReadOnly )
    {
        logger.info( "*-*-*-* Cepr01040000Entry.readOnlyAllForm" );
        String value;
        if( isReadOnly )
        {
            value = CommonConst.READ_ONLY_TRUE;
        }
        else
        {
            value = CommonConst.READ_ONLY_FALSE;
        }

        cepr01030102Form.setTotalSumInsuredReadOnly( value );
        cepr01030102Form.setPremiumReadOnly( value );
    }
    
    protected void clearTopUpAndWithdrawal() {
		for(int i = 0 ; i < cepr01030104Form.getTopupDrawVOList().size() ; i ++ ){
			cepr01030104Form.getTopupDrawVOList().get(i).setYearFlag( CommonConst.CHECKED_FALSE );
		}
	}

    protected void showAllForm( boolean isShowed )
    {
        logger.info( "*-*-*-* Cepr01040000Entry.showAllForm" );
        String value;
        if( isShowed )
        {
            value = CommonConst.DISPLAY_TRUE;
        }
        else
        {
            value = CommonConst.DISPLAY_FALSE;
        }
        cepr01030102Form.setAssurancePlanListDisplay( value );
        cepr01030102Form.setPremiumDisplay( value );
        cepr01030102Form.setPremiumListDisplay( value );
        cepr01030102Form.setBasePremiumDisplay( value );
        cepr01030102Form.setFlagOfVIPDisplay(value); //CR#: NCR/2020/08/019 FLAG VIP (IGA)
        cepr01030102Form.setTopUpPremiumDisplay( value );
        cepr01030102Form.setButtonCountTotalSumInsuredDisplay( value );
        cepr01030102Form.setClazzListDisplay( value );
        cepr01030102Form.setCriticalIllnessFlagDisplay( value );
        cepr01030102Form.setCurrencyListDisplay( value );
        cepr01030102Form.setHeaderAdditionalAssuranceDisplay( value );
        cepr01030102Form.setInvestRateInPercentDisplay( value );
        cepr01030102Form.setPaymentModeListDisplay( value );
        cepr01030102Form.setSmileLadiesPackageListDisplay( value );
        cepr01030102Form.setSecondPackageListDisplay( value );
        cepr01030102Form.setEducationPackageListDisplay( value );
        cepr01030102Form.setProtectorPackageListDisplay( value );
        cepr01030102Form.setProviderTypeListDisplay( value );
        cepr01030102Form.setSmilePensionPackageListDisplay( value );
        cepr01030102Form.setPayorsClauseFlagDisplay( value );
        cepr01030102Form.setPersonalAccidentFlagDisplay( value );
        cepr01030102Form.setTermOfContractDisplay( value );
        cepr01030102Form.setTermOfPaymentDisplay( value );
        cepr01030102Form.setTermRiderFlagDisplay( value );
        cepr01030102Form.setTotalSumInsuredDisplay( value );
        cepr01030102Form.setTotalSumInsuredListDisplay( value );
        cepr01030102Form.setWaiverPremiumDisabilityFlagDisplay( value );
        cepr01030102Form.setMgiListDisplay( value );
        cepr01030102Form.setMtiListDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPesertaEkaSehatStandAloneDisplay( value );
        cepr01030102Form.setPesertaHcpProviderDisplayStandAloneDisplay(value);
        cepr01030102Form.setPesertaMedicalPlusStandAloneDisplay( value );
        cepr01030102Form.setJenisListDisplay( value );
        cepr01030102Form.setTargetListDisplay( value );
        cepr01030102Form.setTaxBeforeAfterListDisplay( value );
        cepr01030102Form.setHeaderInvestChoiceDisplay( value );
        cepr01030102Form.setSpecialOfferListDisplay( value );
        cepr01030102Form.setEmailDisplay( value );
        cepr01030102Form.setFaxDisplay( value );

        cepr01030102Form.setFixIdrDisplay( value );
        cepr01030102Form.setFixIdrListDisplay( value );
        cepr01030102Form.setDynamicIdrDisplay( value );
        cepr01030102Form.setDynamicIdrListDisplay( value );
        cepr01030102Form.setAggresiveIdrDisplay( value );
        cepr01030102Form.setAggresiveIdrListDisplay( value );
        cepr01030102Form.setCashFundIdrDisplay( value );
        cepr01030102Form.setCashFundIdrListDisplay( value );
        cepr01030102Form.setExcellinkEquityIdrDisplay( value );
        cepr01030102Form.setExcellinkEquityListDisplay( value );
        
        cepr01030102Form.setSecureUsdDisplay( value );
        cepr01030102Form.setSecureUsdListDisplay( value );
        cepr01030102Form.setDynamicUsdDisplay( value );
        cepr01030102Form.setDynamicUsdListDisplay( value );
        cepr01030102Form.setPremiumFurloughYearDisplay( value );
        cepr01030102Form.setPremiumFurloughYearListDisplay( value );
        cepr01030102Form.setPremiumFurloughYearListDisplayHelperDisplay( value );
        cepr01030102Form.setTermRiderUnitQtyListDisplay( value );
        cepr01030102Form.setPersonalAccidentUnitQtyListDisplay( value );
        cepr01030102Form.setEkaSehatFlagDisplay( value );
        cepr01030102Form.setPremiumCombinationListDisplay( value );

        cepr01030102Form.setLjiFixListDisplay( value );
        cepr01030102Form.setLjiDynamicListDisplay( value );
        cepr01030102Form.setLjiAggresiveListDisplay( value );
        cepr01030102Form.setLjiCashFundDisplay( value );
        cepr01030102Form.setLjiExcellinkEquityDisplay( value );
        cepr01030102Form.setLjiSecureUsdListDisplay( value );
        cepr01030102Form.setLjiDynamicUsdListDisplay( value );        
        
        cepr01030102Form.setButtonCountTotalSumInsuredDisplay( value );
        cepr01030102Form.setButtonCountPremiumDisplay( value );
        cepr01030102Form.setButtonTopupWithdrawDisplay( value );
        cepr01030102Form.setButtonRiderAndButtonTopupDisplay( value );
        cepr01030102Form.setButtonRiderDisplay( value );
        cepr01030102Form.setButtonTopupDisplay( value );
        cepr01030102Form.setButtonViewProposalDisplay( value );

        cepr01030103Form.setPaDisplay( value );
        cepr01030103Form.setHcpDisplay( value );
        cepr01030103Form.setHcpFamilyDisplay( value );
        cepr01030103Form.setHcpLadiesDisplay( value );
        cepr01030103Form.setLadiesInsDisplay( value );
        cepr01030103Form.setScholarshipDisplay( value );
        cepr01030103Form.setHcpProviderDisplay( value );
        cepr01030103Form.setPayor5Tpd10TpdDeathDisplay( value );
        cepr01030103Form.setPayor5Tpd10CiDeathDisplay( value );
        cepr01030103Form.setPayorTpdDeathDisplay( value );
        cepr01030103Form.setPayorCiDeathDisplay( value );
        cepr01030103Form.setPayor5Ci10CiDeathDisplay( value );
        cepr01030103Form.setPayorCiDisplay( value );
        cepr01030103Form.setPayorSpouseTpdDeathDisplay( value );
        cepr01030103Form.setWaiverTpdDisplay( value );
        cepr01030103Form.setWaiverTpdCiDisplay( value );
        cepr01030103Form.setWaiverCiDisplay( value );
        cepr01030103Form.setTpdDisplay( value );
        cepr01030103Form.setCiDisplay( value );
        cepr01030103Form.setTermRiderDisplay( value );
        cepr01030103Form.setEkaSehatDisplay( value );
        cepr01030103Form.setEkaSehatInnerLimitDisplay( value );
        cepr01030103Form.setLadiesMedExpenseDisplay( value );
        cepr01030103Form.setLadiesMedExpenseInnerLimitDisplay( value );
        
        cepr01030102Form.setPacketListDisplay( value );
        cepr01030103Form.setBabyDisplay( value );
        cepr01030103Form.setEarlyStageCi99Display( value );   
        cepr01030103Form.setMedicalPlusDisplay( value );
        cepr01030103Form.setEkaSehatExtraDisplay( value );
        
        /**USD Fund BSIM Products**/
        cepr01030102Form.setAggresiveUsdDisplay(value);
        cepr01030102Form.setAggresiveUsdListDisplay( value );
        cepr01030102Form.setLjiAggresiveUsdListDisplay( value );
        
        /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
        cepr01030103Form.setTerm5575RiderDisplay( value );
    }
    
    protected void disabledRiderForm( boolean isShowed ){
    	String value;
    	
    	if( isShowed )value = CommonConst.DISABLED_TRUE;
    	else value = CommonConst.DISABLED_FALSE;
    
        cepr01030103Form.setPaFlagIsDisabled( value );
        cepr01030103Form.setHcpFlagIsDisabled( value );
        cepr01030103Form.setHcpFamilyFlagIsDisabled( value );
        cepr01030103Form.setHcpLadiesFlagIsDisabled( value );
        cepr01030103Form.setLadiesInsFlagIsDisabled( value );
        cepr01030103Form.setScholarshipFlagIsDisabled( value );
        cepr01030103Form.setHcpProviderFlagIsDisabled( value );
        cepr01030103Form.setWaiverTpdCiFlagIsDisabled( value );
        cepr01030103Form.setCiFlagIsDisabled( value );
        cepr01030103Form.setEkaSehatFlagIsDisabled( value );
        cepr01030103Form.setEkaSehatInnerLimitFlagIsDisabled( value );
        cepr01030103Form.setLadiesMedExpenseFlagIsDisabled( value );
        cepr01030103Form.setLadiesMedExpenseInnerLimitFlagIsDisabled( value );
        cepr01030103Form.setPayor5Tpd10TpdDeathFlagIsDisabled( value );
        cepr01030103Form.setPayor5Tpd10CiDeathFlagIsDisabled( value );
        cepr01030103Form.setPayorTpdDeathFlagIsDisabled( value );
        cepr01030103Form.setPayorCiDeathFlagIsDisabled( value );
        cepr01030103Form.setPayor5Ci10CiDeathFlagIsDisabled( value );
        cepr01030103Form.setPayorCiFlagIsDisabled( value );
        cepr01030103Form.setPayorSpouseTpdDeathFlagIsDisabled( value );
        cepr01030103Form.setWaiverTpdFlagIsDisabled( value );
        cepr01030103Form.setWaiverCiFlagIsDisabled( value );
        cepr01030103Form.setTpdFlagIsDisabled( value );
        cepr01030103Form.setTermRiderFlagIsDisabled( value );
        cepr01030103Form.setPayorTpdCiDeathFlagIsDisabled( value );
        cepr01030103Form.setWaiver5Ci10CiFlagIsDisabled( value );
        cepr01030103Form.setWaiver5Tpd10CiFlagIsDisabled( value );
        cepr01030103Form.setWaiver5Tpd10TpdFlagIsDisabled( value );
        cepr01030103Form.setBabyFlagIsDisabled(value);   
        cepr01030103Form.setEarlyStageCi99FlagIsDisabled( value );
        cepr01030103Form.setMedicalPlusFlagIsDisabled( value );
        cepr01030103Form.setMedicalPlusRjFlagIsDisabled( value);
        cepr01030103Form.setMedicalPlusRgFlagIsDisabled( value);
        cepr01030103Form.setMedicalPlusRbFlagIsDisabled( value);
        cepr01030103Form.setMedicalPlusPkFlagIsDisabled( value);
        cepr01030103Form.setEkaSehatExtraFlagIsDisabled( value );
        
        /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
        cepr01030103Form.setTerm5575RiderFlagIsDisabled( value );
    }
    
    
    protected void disabledListOptionAtAllRiderForm( boolean isShowed ){
    	String value;
    	
    	if( isShowed )value = CommonConst.DISABLED_TRUE;
    	else value = CommonConst.DISABLED_FALSE;
           
        cepr01030103Form.setPaClassListIsDisabled( value );
        cepr01030103Form.setPaRiskListIsDisabled( value );
        cepr01030103Form.setPaUnitQtyListIsDisabled( value );
        cepr01030103Form.setHcpListIsDisabled( value );
        cepr01030103Form.setHcpFamilyListIsDisabled( value );
        cepr01030103Form.setHcpLadiesListIsDisabled( value );
        cepr01030103Form.setLadiesInsChooseListIsDisabled( value );
        cepr01030103Form.setLadiesInsListIsDisabled( value );
        cepr01030103Form.setScholarshipChooseListIsDisabled( value );
        cepr01030103Form.setScholarshipListIsDisabled( value );
        cepr01030103Form.setScholarshipChooseListIsDisabled( value );
        cepr01030103Form.setHcpProviderListIsDisabled( value );
        cepr01030103Form.setWaiverTpdCiChooseListIsDisabled( value );
        cepr01030103Form.setCiChooseListIsDisabled( value );
        cepr01030103Form.setEkaSehatListIsDisabled( value );
        cepr01030103Form.setEkaSehatInnerLimitListIsDisabled( value );
        cepr01030103Form.setLadiesMedExpenseListIsDisabled( value );
        cepr01030103Form.setLadiesMedExpenseInnerLimitListIsDisabled( value );
        cepr01030103Form.setBabyChooseListIsDisabled(value);
        cepr01030103Form.setBabyListIsDisabled(value);
        cepr01030103Form.setEsci99ChooseListIsDisabled( value );
        cepr01030103Form.setMedicalPlusChooseListIsDisabled( value );
        cepr01030103Form.setEkaSehatExtraListIsDisabled( value );
        /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
        cepr01030103Form.setTerm5575RiderChooseListIsDisabled( value );
               
    }
    
    
    protected void checkAllRider( boolean isChecked ){
    	String value;
    	
    	if( isChecked )value = CommonConst.CHECKED_TRUE;
    	else value = CommonConst.CHECKED_FALSE;
           
        cepr01030103Form.setPaFlag( value );
        cepr01030103Form.setHcpFlag( value );
        cepr01030103Form.setHcpFamilyFlag( value );
        cepr01030103Form.setHcpLadiesFlag( value );
        cepr01030103Form.setLadiesInsFlag( value );
        cepr01030103Form.setScholarshipFlag( value );
        cepr01030103Form.setHcpProviderFlag( value );
        cepr01030103Form.setPayor5Tpd10TpdDeathFlag( value );
        cepr01030103Form.setPayor5Tpd10CiDeathFlag( value );
        cepr01030103Form.setPayorTpdDeathFlag( value );
        cepr01030103Form.setPayorCiDeathFlag( value );
        cepr01030103Form.setPayor5Ci10CiDeathFlag( value );
        cepr01030103Form.setPayorCiFlag( value );
        cepr01030103Form.setPayorSpouseTpdDeathFlag( value );
        cepr01030103Form.setWaiverTpdFlag( value );
        cepr01030103Form.setWaiverTpdCiFlag( value );
        cepr01030103Form.setWaiverCiFlag( value );
        cepr01030103Form.setTpdFlag( value );
        cepr01030103Form.setCiFlag( value );
        cepr01030103Form.setTermRiderFlag( value );
        cepr01030103Form.setEkaSehatFlag( value );
        cepr01030103Form.setEkaSehatInnerLimitFlag( value );
        cepr01030103Form.setLadiesMedExpenseFlag( value );
        cepr01030103Form.setLadiesMedExpenseInnerLimitFlag( value );
        cepr01030103Form.setPayorTpdCiDeathFlag( value );
        cepr01030103Form.setWaiver5Ci10CiFlag( value );
        cepr01030103Form.setWaiver5Tpd10CiFlag( value );
        cepr01030103Form.setWaiver5Tpd10TpdFlag( value );
        cepr01030103Form.setBabyFlag(value);
        cepr01030103Form.setEarlyStageCi99Flag( value );
        cepr01030103Form.setMedicalPlusFlag( value );
        cepr01030103Form.setMedicalPlusRjFlag(value);
        cepr01030103Form.setMedicalPlusRgFlag(value);
        cepr01030103Form.setMedicalPlusRbFlag(value);
        cepr01030103Form.setMedicalPlusPkFlag(value);
        cepr01030103Form.setEkaSehatExtraFlag( value );
    }

    protected void displayStandardForm()
    {
        logger.info( "*-*-*-* Cepr01040000Entry.displayStandardForm" );
        showAllForm( false );
        cepr01030102Form.setAssurancePlanListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPremiumDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setCurrencyListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPaymentModeListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setSmileLadiesPackageListDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setSecondPackageListDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setEducationPackageListDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setProtectorPackageListDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setProviderTypeListDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setSmilePensionPackageListDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setTermOfContractDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setTermOfPaymentDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setTotalSumInsuredDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setEmailDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setFaxDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setFlagOfVIPDisplay( CommonConst.DISPLAY_TRUE ); //CR#: NCR/2020/08/019 FLAG VIP (IGA)
        
        cepr01030103Form.setPaDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setHcpDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setHcpFamilyDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setHcpLadiesDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setLadiesInsDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setHcpProviderDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setPayor5Tpd10TpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayor5Tpd10CiDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayor5Ci10CiDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorSpouseTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiver5Tpd10TpdDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiver5Tpd10CiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverTpdDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiver5Ci10CiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setTpdDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setCiDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setTermRiderDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setEkaSehatDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setEkaSehatInnerLimitDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setLadiesMedExpenseDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setLadiesMedExpenseInnerLimitDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setSpecialOfferListDisplay( CommonConst.DISPLAY_FALSE );
        
        cepr01030103Form.setWaiverTpdCiDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setPayorTpdCiDeathDisplay(CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setBabyDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setEarlyStageCi99Display( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setMedicalPlusDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setEkaSehatExtraDisplay( CommonConst.DISPLAY_FALSE );
       /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
        cepr01030103Form.setTerm5575RiderDisplay( CommonConst.DISPLAY_FALSE );
        
        if( !StringUtil.isEmpty( cepr01030102Form.getAssurancePlanCd() ) )
        {
            cepr01030102Form.setButtonViewProposalDisplay( CommonConst.DISPLAY_TRUE );
        }
    }

    protected void initStandard()
    {
        cepr01030102Form.setClazzList( comboLookupBusiness.getClazzOptionVOList( true ) );
    }

    public BigDecimal computePremiumWithSimpleMethod( BigDecimal totalSumInsured, BigDecimal rate, BigDecimal factor )
    {
        BigDecimal result;
        if( totalSumInsured != null && rate != null && factor != null )
        {
            result = totalSumInsured.multiply( rate ).multiply( factor ).multiply( CommonConst.PER_MIL );
        }
        else
        {
            result = null;
        }

        return result;
    }

    public BigDecimal computeManfaat( Integer payModeCd, Integer manfaatCd )
    {
        logger.info( "*-*-*-* Cepr01040000Entry.computeManfaat" );

        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put( "leftPartOfBusinessCd", manfaatCd );
        sqlParams.put( "lstabLamaTanggung", null );
        sqlParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_PREMI );
        sqlParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
        sqlParams.put( "paymentModeCd", payModeCd );
        sqlParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
        sqlParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
        sqlParams.put( "yearNoList", new Integer[]{ new Integer( "1" ) } );
        List rateList = eproposalManager.selectRate( sqlParams );
        BigDecimal manfaatPremium = null;
        BigDecimal rate;
        if( rateList != null && rateList.size() > 0 )
        {
            rate = eproposalManager.selectRate( sqlParams ).get( 0 );
            if (manfaatCd ==Hardcode.MANFAAT_TERM_OF_RIDER_CD){
            	manfaatPremium = computePremiumWithSimpleMethod( cepr01030102Form.getTotalSumInsured(), rate, new BigDecimal( cepr01030102Form.getTermRiderUnitQtyCd()));
            }else{
            	manfaatPremium = computePremiumWithSimpleMethod( cepr01030102Form.getTotalSumInsured(), rate, new BigDecimal( "1" ) );
            }          
            logger.info( "*-*-*-* rate = " + rate );
            logger.info( "*-*-*-* manfaatPremium = " + manfaatPremium );
        }
        else
        {
            manfaatPremium = new BigDecimal( "0" );
        }
        return manfaatPremium;
    }

    public BigDecimal computeManfaatNew( Integer payModeCd, Integer manfaatCd, Integer lsdbsNumber, Integer lstabLamaTanggung )
    {
        logger.info( "*-*-*-* Cepr01040000Entry.computeManfaat" );

        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put( "leftPartOfBusinessCd", manfaatCd );
        sqlParams.put( "lstabLamaTanggung", lstabLamaTanggung );
        sqlParams.put( "lsdbsNumber", lsdbsNumber );
        sqlParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_PREMI );
        sqlParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
        sqlParams.put( "paymentModeCd", payModeCd );
        sqlParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
        sqlParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
        sqlParams.put( "yearNoList", new Integer[]{ new Integer( "1" ) } );
        List rateList = eproposalManager.selectRateNew( sqlParams );
        BigDecimal manfaatPremium;
        BigDecimal rate;
        if( rateList != null && rateList.size() > 0 )
        {
            rate = eproposalManager.selectRateNew( sqlParams ).get( 0 );
            manfaatPremium = computePremiumWithSimpleMethod( cepr01030102Form.getTotalSumInsured(), rate, new BigDecimal( "1" ) );
            logger.info( "*-*-*-* rate = " + rate );
            logger.info( "*-*-*-* manfaatPremium = " + manfaatPremium );
        }
        else
        {
            manfaatPremium = new BigDecimal( "0" );
        }
        return manfaatPremium;
    }
    
    public double of_get_rate( Integer payModeCd, Integer leftPartOfBusinessCd )
    {
        logger.info( "*-*-*-* Cepr01040000Entry.of_get_rate" );
        Integer age = cepr01030101Form.getInsuredAge();
        if( leftPartOfBusinessCd.intValue() == 802 ){
        	age = cepr01030101Form.getPolicyHolderAge();
        }
        
        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put( "leftPartOfBusinessCd", leftPartOfBusinessCd );
        sqlParams.put( "lstabLamaTanggung", null );
        sqlParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_PREMI );
        sqlParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
        sqlParams.put( "paymentModeCd", payModeCd );
        sqlParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
        sqlParams.put( "insuredAge", age );
        sqlParams.put( "yearNoList", new Integer[]{ new Integer( "1" ) } );
        List rateList = eproposalManager.selectRate( sqlParams );
        Double rate;
        if( rateList != null && rateList.size() > 0 )
        {
            rate = LazyConverter.toDouble( eproposalManager.selectRate( sqlParams ).get( 0 ) );
        }
        else
        {
            rate = 0.0;
        }
        return rate;
    }

    public void synchronizeSelectedListOption()
    {
        logger.info( "*-*-*-* Cepr01040000Entry.synchronizeSelectedListOption" );
        // place this on the last
        cepr01030102Form.setPremiumCd( cepr01030102Form.getPremium() );
        cepr01030102Form.setTotalSumInsuredCd( cepr01030102Form.getTotalSumInsured() );
        cepr01030102Form.setPremiumFurloughYearCd( cepr01030102Form.getPremiumFurloughYear() );
        cepr01030102Form.setFixIdrCd( cepr01030102Form.getFixIdr() );
        cepr01030102Form.setDynamicIdrCd( cepr01030102Form.getDynamicIdr() );
        cepr01030102Form.setAggresiveIdrCd( cepr01030102Form.getAggresiveIdr() );
        cepr01030102Form.setSecureUsdCd( cepr01030102Form.getSecureUsd() );
        cepr01030102Form.setDynamicUsdCd( cepr01030102Form.getDynamicUsd() );
        cepr01030102Form.setAggresiveUsdCd( cepr01030102Form.getAggresiveUsd() );/**USD Fund BSIM Products**/
    }

    // todo : it cost much source ( remember singleton )
    public void resetFormsWhenRightPartCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040000Entry.resetFormsWhenRightPartCdChanged" );
        Cepr01030101Business cepr01030101Business = new Cepr01030101Business( eproposalManager, editorUtil );
        Cepr01030102Business cepr01030102Business = new Cepr01030102Business( eproposalManager, editorUtil );
        Cepr01030103Business cepr01030103Business = new Cepr01030103Business( eproposalManager, editorUtil );
        Cepr01030104Business cepr01030104Business = new Cepr01030104Business( eproposalManager, editorUtil );
        Cepr01030106Business cepr01030106Business = new Cepr01030106Business( eproposalManager, editorUtil );
        Cepr01030107Business cepr01030107Business = new Cepr01030107Business( eproposalManager, editorUtil );
        Cepr01030108Business cepr01030108Business = new Cepr01030108Business( eproposalManager, editorUtil );
        Cepr01030109Business cepr01030109Business = new Cepr01030109Business( eproposalManager, editorUtil );
        Cepr01030110Business cepr01030110Business = new Cepr01030110Business( eproposalManager, editorUtil );
        Cepr01030111Business cepr01030111Business = new Cepr01030111Business( eproposalManager, editorUtil );
        Cepr01030112Business cepr01030112Business = new Cepr01030112Business( eproposalManager, editorUtil );
        Cepr01030113Business cepr01030113Business = new Cepr01030113Business( eproposalManager, editorUtil );
        Cepr01030114Business cepr01030114Business = new Cepr01030114Business( eproposalManager, editorUtil );
//        Cepr01030301Business cepr01030301Business = new Cepr01030301Business( eproposalManager, editorUtil );

        cepr01030101Business.resetForm( cepr01030101Form, generalBusiness, comboLookupBusiness, credentialsVO );
        cepr01030102Business.resetForm( cepr01030102Form );
        cepr01030103Business.resetForm( cepr01030103Form );
        cepr01030104Business.resetForm( cepr01030104Form );
        cepr01030106Business.resetForm( cepr01030106Form );
        cepr01030107Business.resetForm( cepr01030107Form );
        cepr01030108Business.resetForm( cepr01030108Form );
        cepr01030109Business.resetForm( cepr01030109Form );
        cepr01030110Business.resetForm( cepr01030110Form );
        cepr01030111Business.resetForm( cepr01030111Form );
        cepr01030112Business.resetForm( cepr01030112Form );
        cepr01030113Business.resetForm( cepr01030113Form );
        cepr01030114Business.resetForm( cepr01030114Form );
//        cepr01030301Business.resetForm( cepr01030301Form, request );
        
    }
}