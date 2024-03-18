package id.co.sinarmaslife.eproposal.product.product01040207;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040207Entry
 * Description         	: Eka Link 80 Plus (116)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 13, 2008 1:49:24 PM
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
import id.co.sinarmaslife.eproposal.product.product01040205.Cepr01040205Mapper;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.LazyConverter;

public class Cepr01040207Entry extends Cepr01040000UlinkEntry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040207Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        setDownloadUrl( "wepr01040207.pdf" );
    }

    public void initDisplayedForm()
    {
        displayStandardForm();
        cepr01030103Form.setEkaSehatDisplay( CommonConst.DISPLAY_FALSE ); //Decommission Rider SMEX Oktober 2021 (NCR/2021/10/106)
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
        cepr01030103Form.setEkaSehatInnerLimitDisplay( CommonConst.DISPLAY_TRUE);
        cepr01030103Form.setEkaSehatExtraDisplay( CommonConst.DISPLAY_FALSE); //Decommission Rider SMEX Oktober 2021 (NCR/2021/10/106)
        cepr01030103Form.setLadiesMedExpenseDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setLadiesMedExpenseInnerLimitDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setPayorSpouseTpdDeathDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setScholarshipDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setMedicalPlusDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setHcpDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setHcpFamilyDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setHcpLadiesDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setHcpProviderDisplay( CommonConst.DISPLAY_TRUE ); 
        /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
        cepr01030103Form.setTerm5575RiderDisplay( CommonConst.DISPLAY_TRUE ); 
        cepr01030103Form.setTermRiderDisplay( CommonConst.DISPLAY_FALSE );
        
        if( Cepr01040207Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
   		 	cepr01030102Form.setSmilePensionPackageListDisplay( CommonConst.DISPLAY_FALSE );
   		 	cepr01030102Form.setEducationPackageListDisplay( CommonConst.DISPLAY_FALSE );
        }
        else if( Cepr01040207Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
       	 	cepr01030102Form.setSmilePensionPackageListDisplay( CommonConst.DISPLAY_FALSE );
       	 	cepr01030102Form.setEducationPackageListDisplay( CommonConst.DISPLAY_FALSE );
        }
        if( cepr01030102Form.getSmilePensionPackageCd() != null && cepr01030102Form.getSmilePensionPackageCd() > 1 ){
        	if( cepr01030102Form.getSmilePensionPackageCd().equals( Hardcode.SMILE_PENSION_PAKET_A ) ){
			}else if( cepr01030102Form.getSmilePensionPackageCd().equals( Hardcode.SMILE_PENSION_PAKET_B ) ){
			}else if( cepr01030102Form.getSmilePensionPackageCd().equals( Hardcode.SMILE_PENSION_PAKET_C ) ){
			}else if( cepr01030102Form.getSmilePensionPackageCd().equals( Hardcode.SMILE_PENSION_PAKET_D ) ){
			}else if( cepr01030102Form.getSmilePensionPackageCd().equals( Hardcode.SMILE_PENSION_PAKET_E ) ){
			}
        }else{
        	
        }
        
        if( credentialsVO != null && credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_TIM_HAFRI )
        		&& cepr01030102Form.getRightPartOfBusinessCd() ==  Cepr01040207Mapper.X4){
        	cepr01030102Form.setSecondPackageListDisplay( CommonConst.DISPLAY_TRUE );
        	cepr01030102Form.setSmilePensionPackageListDisplay( CommonConst.DISPLAY_FALSE );
        	if( cepr01030102Form.getSecondPackageCd() != null && cepr01030102Form.getSecondPackageCd() > 1 ){
        		cepr01030102Form.setButtonRiderDisplay( CommonConst.DISPLAY_FALSE );
            	cepr01030102Form.setButtonTopupDisplay( CommonConst.DISPLAY_FALSE );
        	}else{
        		cepr01030102Form.setButtonRiderDisplay( CommonConst.DISPLAY_TRUE );
            	cepr01030102Form.setButtonTopupDisplay( CommonConst.DISPLAY_TRUE );
        	}
        }else{
        	cepr01030102Form.setSecondPackageListDisplay( CommonConst.DISPLAY_FALSE );
        	cepr01030102Form.setButtonRiderDisplay( CommonConst.DISPLAY_TRUE );
        	cepr01030102Form.setButtonTopupDisplay( CommonConst.DISPLAY_TRUE );
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
        if( Cepr01040207Mapper.X3 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
        	cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
        }
        else if( Cepr01040207Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
        	cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
        }
    }

    public void initReadOnlyForm()
    {
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
    	cepr01030102Form.setSmilePensionPackageList( comboLookupBusiness.smilePensionPackageList() );
    	cepr01030102Form.setSecondPackageList( comboLookupBusiness.ibadahPackageList() );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        cepr01030102Form.setFixIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setDynamicIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setAggresiveIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setCashFundIdrList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setSecureUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setDynamicUsdList( LookupList.getPercentSequenceList( new int[]{ 0, 20, 40, 60, 80, 100 } ) );
        cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombination5FoldedList() );
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040207Mapper.X4 && cepr01030102Form.getPremium() != null ){
        	double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        	if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_BULANAN && ( premium >= 150000 && premium <= 250000 ) ){
        		 cepr01030102Form.setPremiumCombinationList( LookupList.getPremiumCombinationFoldedStart70List(10) );
        	}
        }
        cepr01030102Form.setPremiumFurloughYearList( LookupList.getSequenceNumberList( 0, 20, 5, false ) );

        cepr01030102Form.setTermOfContract( 80 - cepr01030101Form.getInsuredAge() );

        if( cepr01030102Form.getCurrencyCd() == null )
        {
            cepr01030102Form.setCurrencyCd( Hardcode.CUR_IDR_CD );
        }
        List<OptionVO> premiumList = null;

        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 3000000, 20000000, 1000000 );

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
            premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 1000, 2500, 100 );
            cepr01030102Form.setSecureUsdDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setSecureUsdListDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setDynamicUsdDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setDynamicUsdListDisplay( CommonConst.DISPLAY_TRUE );
            cepr01030102Form.setCashFundIdrDisplay( CommonConst.DISPLAY_FALSE );
            cepr01030102Form.setCashFundIdrListDisplay( CommonConst.DISPLAY_FALSE );
        }

        cepr01030102Form.setPremiumList( premiumList );
        
    	if( cepr01030102Form.getSmilePensionPackageCd() != null && cepr01030102Form.getSmilePensionPackageCd() >1 ){
    		cepr01030102Form.setPremiumCombinationCd(new BigDecimal("40") );
			cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
			cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_TRUE );
			cepr01030102Form.setPremiumFurloughYear( 55 - cepr01030101Form.getInsuredAge() );
			cepr01030102Form.setPremiumFurloughYearIsDisabled( CommonConst.DISABLED_TRUE );
			cepr01030103Form.setWaiverTpdCiFlagIsDisabled( CommonConst.DISABLED_TRUE );
		}else{
			cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
			cepr01030102Form.setPremiumCombinationListIsDisabled( CommonConst.DISABLED_FALSE );
			cepr01030102Form.setPremiumFurloughYearIsDisabled( CommonConst.DISABLED_FALSE );
		}
    	 if( cepr01030102Form.getTheEvent().equals("onChangeSmilePensionPackageCd") ){
         	if( cepr01030102Form.getSmilePensionPackageCd() != null && cepr01030102Form.getSmilePensionPackageCd() > 1 ){
             	if( cepr01030102Form.getSmilePensionPackageCd().equals( 1 ) ){
             		checkAllRider( false );
             	}
             }else{
             	checkAllRider( false );
             }
         }
    	 if( "onChangeEducationPackageCd".equals( cepr01030102Form.getTheEvent() ) ){
    		 setRiderValueForFirstTime();
    		 setEducationPackage();
    	 }else if( "onChangeSmilePensionPackageCd".equals( cepr01030102Form.getTheEvent() ) ){
    		 setRiderValueForFirstTime();
    		 setSmilePension();
    	 }
    }

    public void validateCurrentPage()
    {

        UlinkValidatorParVO parVO = new UlinkValidatorParVO();
        parVO.setUnder5YearsBerkalaMaxBasePremiumVO( new AmountVO( 100000000.0, 10000 ) );
        parVO.setUnder5YearsSekaligusMaxBasePremiumVO( new AmountVO( 400000000.0, 40000 ) );
        parVO.setBeyond5YearsBerkalaMaxBasePremiumVO( new AmountVO( 1000000000.0, 100000 ) );
        parVO.setBeyond5YearsSekaligusMaxBasePremiumVO( new AmountVO( 1000000000.0, 100000 ) );
        super.validateCurrentPage( parVO );


        if( cepr01030102Form.getPremium() != null )
        {
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
        if( cepr01030102Form.getSmilePensionPackageCd() != null &&  cepr01030102Form.getSmilePensionPackageCd() > 1 ){
        	
        }else{
        	if( Cepr01040205Mapper.X4 == cepr01030102Form.getRightPartOfBusinessCd() )
            {
        		commonValidator.validatePremiumFurloughYear( Cepr01030102FormConst.PREMIUM_FURLOUGH_YEAR, 2, 20 );
            }
        }
        
        

        if( cepr01030102Form.getTotalSumInsured() != null )
        {
            double totalSumInsured = LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() );
            double minTotalSumInsuredIdr = 7500000;
            double minTotalSumInsuredUsd = 750;
          //  commonValidator.validateMinimumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, totalSumInsured, minTotalSumInsuredIdr, minTotalSumInsuredUsd );
        }
        else
        {
        }
    }

    public void validatePreviousPage()
    {
        commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 0, 70 );
    }

    public void processFormAfterSubmitBeforeValidation()
    {
    	// Do nothing
    }
}