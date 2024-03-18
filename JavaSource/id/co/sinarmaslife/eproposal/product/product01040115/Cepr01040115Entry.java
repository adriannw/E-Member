package id.co.sinarmaslife.eproposal.product.product01040115;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040112X2Entry
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : Fadly Mathendra
 * Version              : 1.0
 * Creation Date    	: Okt 27, 2008 9:26:31 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Entry;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000Generator;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000EntryInterface;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.LazyConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040115Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040115Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040115Entry constructor is called ..." );
        setDownloadUrl( "wepr01040115.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040115Entry.initDisplayedForm" );
        displayStandardForm();
        displayAdditionalAssurance( false );
        //cepr01030102Form.setPremiumListDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setTotalSumInsuredListDisplay( CommonConst.DISPLAY_TRUE );
        //cepr01030102Form.setButtonCountTotalSumInsuredDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setButtonCountPremiumDisplay( CommonConst.DISPLAY_TRUE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040115Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040115Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040115Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );

        cepr01030102Form.setTermOfContract( 23 - cepr01030101Form.getInsuredAge() );
        cepr01030102Form.setTermOfPayment( 18 - cepr01030101Form.getInsuredAge() );

        Integer[] payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN };
        cepr01030102Form.setPaymentModeCd( payModeArr[ 0 ] );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, payModeArr ) );

        String[] currencyCdArr = new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD };
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, currencyCdArr ) );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040115Entry.fillDefaultValueEachTimeFormCalled" );
        List<OptionVO> premiumList = null;
        cepr01030102Form.setTotalSumInsuredOptionIsToRefreshPage( CommonConst.REFRESH_TRUE );

        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 10000000, 200000000, 10000000 );
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 1000, 20000, 1000 );
        }
        cepr01030102Form.setTotalSumInsuredList( premiumList );
        
		if("onChangeCurrencyCd".equals( cepr01030102Form.getTheEvent() ) || "onChangeAssurancePlanCd".equals( cepr01030102Form.getTheEvent() ) || "onPressButtonNext".equals( cepr01030102Form.getTheEvent() ))
		{
			premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 10000000, 200000000, 10000000 );
			if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
	        {
	            premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 10000000, 200000000, 10000000 );
	        }
	        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
	        {
	            premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 1000, 20000, 1000 );
	        }
			cepr01030102Form.setTotalSumInsuredList( premiumList );
			cepr01030102Form.setTotalSumInsured(new BigDecimal(0));
			cepr01030102Form.setPremium(new BigDecimal(0));
			cepr01030102Form.setTermOfContract( 23 - cepr01030101Form.getInsuredAge() );
	        cepr01030102Form.setTermOfPayment( 18 - cepr01030101Form.getInsuredAge() );
			if("onChangeAssurancePlanCd".equals( cepr01030102Form.getTheEvent() ) )
			{
				cepr01030102Form.setCurrencyCd(Hardcode.CUR_IDR_CD);
			}
		}
		
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040115Entry.validateCurrentPage" );

        double totalSumInsured = LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() );
        double minTotalSumInsuredIdr = 10000000;
        //double maxTotalSumInsuredIdr = 90000000;
        double minTotalSumInsuredUsd = 1000;
        //double maxTotalSumInsuredUsd = 9000;

        if( cepr01030102Form.getTotalSumInsured() != null )
        {
            commonValidator.validateMinimumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, totalSumInsured, minTotalSumInsuredIdr, minTotalSumInsuredUsd );
            //commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, totalSumInsured, maxTotalSumInsuredIdr, maxTotalSumInsuredUsd );
        }
        else
        {
            errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED, "error.formEmpty", null, null );
        }
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040115Entry.validatePreviousPage" );
        commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 1, 12 );
        commonValidator.validatePolicyHolderAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 20, 55 );
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040115Entry.processFormAfterSubmitBeforeValidation" );
        double ratePremium = 0;
		double countPerUp = 0;
		double countPremium = 0;
        if( "onChangePremium".equals( cepr01030102Form.getTheEvent() )
                || "onPressButtonCountPremium".equals( cepr01030102Form.getTheEvent() ) || "onPressButtonPreviewProposal".equals(cepr01030102Form.getTheEvent()))
            {
        			countPremium = new Double(computePremium(Integer.parseInt(cepr01030102Form.getPaymentModeCd().toString())).toString());
		            cepr01030102Form.setPremium( new BigDecimal(countPremium) );
            }

    }
    
    public BigDecimal computePremium( Integer payModeCd )
    {
        logger.info( "*-*-*-* Cepr01040104Entry.computePremium" );

        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put( "leftPartOfBusinessCd", new Integer( "172" ) );
        sqlParams.put( "lstabLamaTanggung", cepr01030102Form.getTermOfContract() );
        sqlParams.put( "lsdbsNumber", cepr01030102Form.getRightPartOfBusinessCd() );
        sqlParams.put( "lstabLamaBayar", cepr01030102Form.getTermOfPayment() );
        sqlParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_PREMI );
        sqlParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
        sqlParams.put( "paymentModeCd", payModeCd );
        sqlParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
        sqlParams.put( "insuredAge", cepr01030101Form.getPolicyHolderAge() );
        List rateList = eproposalManager.selectRateNew( sqlParams );
        BigDecimal premium;
        BigDecimal rate;
        if( rateList != null && rateList.size() > 0 )
        {
            rate = eproposalManager.selectRateNew( sqlParams ).get( 0 );
            premium = computePremiumWithSimpleMethod( cepr01030102Form.getTotalSumInsured(), rate, new BigDecimal( "1" ) );
        }
        else
        {
            premium = new BigDecimal( "0" );
        }

        return premium;
    }
    
    public double countRatePremium()
    {
    	double ratePremium = 0;
    	
	    if( Cepr01040115Mapper.X1 == rightPartOfBusinessCd ||
	    		Cepr01040115Mapper.X2 == rightPartOfBusinessCd )
        {
	    	ratePremium = 1.8;
        }
	    
    	return ratePremium;
    }

}