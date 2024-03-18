package id.co.sinarmaslife.eproposal.product.product01040141;

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
import id.co.sinarmaslife.eproposal.product.product01040113.Cepr01040113Mapper;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.LazyConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.List;

public class Cepr01040141Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040141Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040141Entry constructor is called ..." );
        setDownloadUrl( "wepr01040141.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040141Entry.initDisplayedForm" );
        displayStandardForm();
        displayAdditionalAssurance( false );
        cepr01030102Form.setPremiumListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonCountTotalSumInsuredDisplay( CommonConst.DISPLAY_TRUE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040141Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPaymentModeListIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040141Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040141Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );

        cepr01030102Form.setTermOfContract( 1 );
        cepr01030102Form.setTermOfPayment( 1 );

        Integer[] payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN };
        cepr01030102Form.setPaymentModeCd( payModeArr[ 0 ] );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, payModeArr ) );

        String[] currencyCdArr = new String[]{ Hardcode.CUR_IDR_CD };
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, currencyCdArr ) );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040141Entry.fillDefaultValueEachTimeFormCalled" );
        List<OptionVO> premiumList = null;
        cepr01030102Form.setPremiumOptionIsToRefreshPage( CommonConst.REFRESH_TRUE );
        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 150000, 900000, 50000 );
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            premiumList = Cepr00000000Generator.genMoneyList( editorUtil, 15, 90, 5 );
        }
        cepr01030102Form.setPremiumList( premiumList );
        
		if("onChangeCurrencyCd".equals( cepr01030102Form.getTheEvent() ) || "onChangeAssurancePlanCd".equals( cepr01030102Form.getTheEvent() ))
		{
			cepr01030102Form.setTotalSumInsured(new BigDecimal(0));
			cepr01030102Form.setPremium(new BigDecimal(0));
			if("onChangeAssurancePlanCd".equals( cepr01030102Form.getTheEvent() ) )
			{
				cepr01030102Form.setCurrencyCd(Hardcode.CUR_IDR_CD);
			}
		}
		
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040141Entry.validateCurrentPage" );

        double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double minPremiumIdr = 150000;
        double maxPremiumIdr = 900000;
        double minPremiumUsd = 15;
        double maxPremiumUsd = 90;
        double totalSumInsured = LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() );
        double maxTotalSumInsuredIdr = 500000000;
        double maxTotalSumInsuredUsd = 50000;
             
        if( cepr01030102Form.getPremium() != null )
        {
            commonValidator.validateMinimumMoney( Cepr01030102FormConst.PREMIUM, premium, minPremiumIdr, minPremiumUsd );
           // commonValidator.validateMaximumMoney( Cepr01030102FormConst.PREMIUM, premium, maxPremiumIdr, maxPremiumUsd );
            commonValidator.validateMaximumMoney( Cepr01030102FormConst.TOTAL_SUM_INSURED, totalSumInsured, maxTotalSumInsuredIdr, maxTotalSumInsuredUsd );
        }
        else
        {
            errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.formEmpty", null, null );
        }
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040141Entry.validatePreviousPage" );
        commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 59 );
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040141Entry.processFormAfterSubmitBeforeValidation" );
		double ratePremium = 0;
		double countPerUp = 0;
		double countSumInsured = 0;
        if( "onChangePremium".equals( cepr01030102Form.getTheEvent() )
                || "onPressButtonCountTotalSumInsured".equals( cepr01030102Form.getTheEvent() ) || "onPressButtonPreviewProposal".equals(cepr01030102Form.getTheEvent()))
            {

		            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
		            {
		            	//if( Integer.parseInt(cepr01030102Form.getPremium().toString()) >= 150000 && Integer.parseInt(cepr01030102Form.getPremium().toString()) <= 900000 )
		            	if( Integer.parseInt(cepr01030102Form.getPremium().toString()) >= 150000)
		            	{
		            		ratePremium = countRatePremium();	            	    
		            	    if(ratePremium>0)
		            	    {
		            	    	countPerUp = 1000 / ratePremium;
		            	    }
			        	}		            	
		            }
		            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
		            {
		            	//if( Integer.parseInt(cepr01030102Form.getPremium().toString()) >= 15 && Integer.parseInt(cepr01030102Form.getPremium().toString()) <= 90 )
		            	if( Integer.parseInt(cepr01030102Form.getPremium().toString()) >= 15 )
		            	{
		            		ratePremium = countRatePremium();
		            	    if(ratePremium>0)
		            	    {
		            	    	countPerUp = 1000 / ratePremium;
		            	    }
			        	}
		            }	
		            countSumInsured = countPerUp * cepr01030102Form.getPremium().intValue();
		            
		            cepr01030102Form.setTotalSumInsured( new BigDecimal(countSumInsured) );
            }

    }
    
    public double countRatePremium()
    {
    	double ratePremium = 0;
    	
	    if( Cepr01040141Mapper.X2 == rightPartOfBusinessCd )
        {
	    	ratePremium = 1.8;
        }
	    else if( Cepr01040141Mapper.X3 == rightPartOfBusinessCd )
        {
	    	ratePremium = 3.9;
        }
	    else if( Cepr01040141Mapper.X4 == rightPartOfBusinessCd )
        {
	    	ratePremium = 6.2;
        }
	    else if( Cepr01040141Mapper.X5 == rightPartOfBusinessCd )
        {
	    	ratePremium = 2.1;
        }
	    else if( Cepr01040141Mapper.X6 == rightPartOfBusinessCd )
        {
	    	ratePremium = 4.6;
        }
	    else if( Cepr01040141Mapper.X7 == rightPartOfBusinessCd )
        {
	    	ratePremium = 7.3;
        }   
	    
    	return ratePremium;
    }

}