package id.co.sinarmaslife.eproposal.product.product01040144;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040144
 * Program Name   		: Cepr01040144Entry
 * Description         	: Power Save New (142)
 * Environment      	: Java  1.5.0_06
 * Author               : fadly mathendra
 * Version              : 1.0
 * Creation Date    	: Aug 13, 2007 10:33:45 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000EntryInterface;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Entry;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.StringUtil;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040144Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface

{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040144Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040144Entry constructor is called ..." );
        setDownloadUrl( "wepr01040144.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040144Entry.initDisplayedForm" );
        displayStandardForm();
        //cepr01030102Form.setButtonRiderAndButtonTopupDisplay( CommonConst.DISPLAY_TRUE );
        //cepr01030102Form.setButtonRiderDisplay( CommonConst.DISPLAY_TRUE );
        //cepr01030102Form.setButtonCountTotalSumInsuredDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setButtonCountPremiumDisplay( CommonConst.DISPLAY_TRUE );
        //cepr01030102Form.setPremiumListDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setInvestRateInPercentDisplay( CommonConst.DISPLAY_FALSE );
        
        cepr01030103Form.setPaDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setHcpDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setHcpFamilyDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setPayorTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setPayorSpouseTpdDeathDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverTpdDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setWaiverCiDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setTpdDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030103Form.setCiDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030103Form.setTermRiderDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setPesertaEkaSehatStandAloneDisplay( CommonConst.DISPLAY_TRUE );
       // cepr01030102Form.setProviderTypeListDisplay( CommonConst.DISPLAY_TRUE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040144Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTotalSumInsuredListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setCurrencyListIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setPremiumListIsDisabled( CommonConst.DISABLED_TRUE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040144Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040144Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
        cepr01030102Form.setPaymentModeCd( Hardcode.PAY_MODE_CD_TAHUNAN );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040144Entry.fillDefaultValueEachTimeFormCalled" );
        
        cepr01030102Form.setInvestRateInPercent(new BigDecimal(10));
        cepr01030102Form.setPremiumCombinationCd(new BigDecimal(100));
        cepr01030102Form.setTermOfContract( 1 );
        cepr01030102Form.setTermOfPayment( 1 );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN } ) );
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        if( cepr01030102Form.getCurrencyCd() == null )
        {
            cepr01030102Form.setCurrencyCd( Hardcode.CUR_IDR_CD );
        }
        List<OptionVO> premiumList = new ArrayList<OptionVO>();
        OptionVO optionVO;
        optionVO = new OptionVO( "", "" );
        premiumList.add( optionVO );

        double minTotalSumInsuredIdr = 0;
        double minTotalSumInsuredUsd = -1;
        Integer rightPartOfBusinessCd = cepr01030102Form.getRightPartOfBusinessCd();
        if( Cepr01040144Mapper.X1 == rightPartOfBusinessCd ){ minTotalSumInsuredIdr = 100000;}
        else if( Cepr01040144Mapper.X2 == rightPartOfBusinessCd ){minTotalSumInsuredIdr = 150000;}
        else if( Cepr01040144Mapper.X3 == rightPartOfBusinessCd ){minTotalSumInsuredIdr = 200000;}
        else if( Cepr01040144Mapper.X4 == rightPartOfBusinessCd ){minTotalSumInsuredIdr = 250000;}
        else if( Cepr01040144Mapper.X5 == rightPartOfBusinessCd ){minTotalSumInsuredIdr = 300000;}
        else if( Cepr01040144Mapper.X6 == rightPartOfBusinessCd ){minTotalSumInsuredIdr = 350000;}
        else if( Cepr01040144Mapper.X7 == rightPartOfBusinessCd ){minTotalSumInsuredIdr = 400000;}
        else if( Cepr01040144Mapper.X8 == rightPartOfBusinessCd ){minTotalSumInsuredIdr = 500000;}
        else if( Cepr01040144Mapper.X9 == rightPartOfBusinessCd ){minTotalSumInsuredIdr = 600000;}
        else if( Cepr01040144Mapper.X10 == rightPartOfBusinessCd ){minTotalSumInsuredIdr = 700000;}
        else if( Cepr01040144Mapper.X11 == rightPartOfBusinessCd ){minTotalSumInsuredIdr = 800000;}
        else if( Cepr01040144Mapper.X12 == rightPartOfBusinessCd ){minTotalSumInsuredIdr = 900000;}
        else if( Cepr01040144Mapper.X13 == rightPartOfBusinessCd ){minTotalSumInsuredIdr = 1000000;}
        else if( Cepr01040144Mapper.X14 == rightPartOfBusinessCd ){minTotalSumInsuredIdr = 1500000;}
        else if( Cepr01040144Mapper.X15 == rightPartOfBusinessCd ){minTotalSumInsuredIdr = 2000000;}
        cepr01030102Form.setTotalSumInsured(new BigDecimal(minTotalSumInsuredIdr));
        
        cepr01030102Form.setPremium(computePremium(cepr01030102Form.getPaymentModeCd()));
        

    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040144Entry.validateCurrentPage" );

        //double premium = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        double minTotalSumInsuredIdr = LazyConverter.toDouble(cepr01030102Form.getTotalSumInsured());
        double minTotalSumInsuredUsd = -1;
        commonUsedBusiness.processMaxTotalSumInsured( new BigDecimal(minTotalSumInsuredIdr), new BigDecimal(minTotalSumInsuredUsd) );
        

        if( cepr01030102Form.getInvestRateInPercent() != null )
        {
            if( cepr01030102Form.getInvestRateInPercent().compareTo( new BigDecimal( "100" ) ) > 0 )
            {
                errors.rejectValue( Cepr01030102FormConst.INVEST_RATE_IN_PERCENT, "error.valueMax", new Object[]{ "100%" }, null );
            }
            else if( cepr01030102Form.getInvestRateInPercent().compareTo( new BigDecimal( "0" ) ) < 0 )
            {
                errors.rejectValue( Cepr01030102FormConst.INVEST_RATE_IN_PERCENT, "error.valueAtLeast", new Object[]{ "0%" }, null );
            }
        }
        else
        {
            errors.rejectValue( Cepr01030102FormConst.INVEST_RATE_IN_PERCENT, "error.formEmpty", null, null );
        }


    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040144Entry.validatePreviousPage" );       
        commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 0, 65 );
        commonValidator.validatePolicyHolderAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 17, 90 );
    }

    public void processFormAfterSubmitBeforeValidation()
    {
        logger.info( "*-*-*-* Cepr01040144Entry.processFormAfterSubmitBeforeValidation" );
        
    }
    

    public BigDecimal computePremium( Integer payModeCd )
    {
        logger.info( "*-*-*-* Cepr01040144Entry.computePremium" );

        BigDecimal premium = new BigDecimal(0);
        
//        double[] plan_a = {223000 , 420000  , 605000  , 685500  , 905500  , 1008000 };
//        double[] plan_b = {334500 , 629500  , 907500  , 1028000 , 1358000 , 1631500 };
//        double[] plan_c = {446000 , 839500  , 1210000 , 1370500 , 1810500 , 2175000 };
//        double[] plan_d = {551500 , 1038500 , 1278500 , 1450500 , 1927000 , 2315000 };
//        double[] plan_e = {662000 , 1121500 , 1347500 , 1530500 , 2044000 , 2454500 };
//        double[] plan_f = {772000 , 1177500 , 1416000 , 1610500 , 2160500 , 2594000 };
//        double[] plan_g = {882500 , 1273500 , 1484500 , 1690500 , 2277500 , 2734000 };
//        double[] plan_h = {918100 , 1325000 , 1544800 , 1759300 , 2370000 , 2845100 };
//        double[] plan_i = {1124300, 1628000 , 1893800 , 2149200 , 2887500 , 3446500 };
//        double[] plan_j = {1478200, 2139000 , 2489500 , 2827400 , 3791500 , 4542400 };
//        double[] plan_k = {1909700, 2753700 , 3212500 , 3662000 , 4936700 , 5935900 };
//        double[] plan_l = {2387400, 3434100 , 4013200 , 4586500 , 6195000 , 7480300 };
//        double[] plan_m = {2949900, 4310300 , 5011200 , 5673900 , 7620800 , 9051900 };
//        double[] plan_n = {5260700, 8364500 , 9438200 , 10469200, 13096100, 15322100};
//        double[] plan_o = {8406000, 13958500, 15389700, 16788900, 19723100, 22744200};
//        
//        double[][] plans = {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, plan_a, plan_b, plan_c, plan_d, plan_e, plan_f, plan_g, plan_h, 
//        					plan_i, plan_j, plan_k, plan_l, plan_m, plan_n, plan_o};
//
//        if(cepr01030101Form.getInsuredAge() <= 19){premium = new BigDecimal(plans[cepr01030102Form.getRightPartOfBusinessCd()][0]); }
//        else if(cepr01030101Form.getInsuredAge() >= 20 && cepr01030101Form.getInsuredAge() <= 29){premium = new BigDecimal(plans[cepr01030102Form.getRightPartOfBusinessCd()][1]); }
//        else if(cepr01030101Form.getInsuredAge() >= 30 && cepr01030101Form.getInsuredAge() <= 39){premium = new BigDecimal(plans[cepr01030102Form.getRightPartOfBusinessCd()][2]); }
//        else if(cepr01030101Form.getInsuredAge() >= 40 && cepr01030101Form.getInsuredAge() <= 49){premium = new BigDecimal(plans[cepr01030102Form.getRightPartOfBusinessCd()][3]); }
//        else if(cepr01030101Form.getInsuredAge() >= 50 && cepr01030101Form.getInsuredAge() <= 55){premium = new BigDecimal(plans[cepr01030102Form.getRightPartOfBusinessCd()][4]); }
//        else if(cepr01030101Form.getInsuredAge() >= 56 && cepr01030101Form.getInsure/dAge() <= 60){premium = new BigDecimal(plans[cepr01030102Form.getRightPartOfBusinessCd()][5]); }
        double ldec_total = 0, ldec_temp, ldec_pct = 1, ldec_rate;
        int li_usia;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "riderBaru", cepr01030102Form.getRightPartOfBusinessCd() );  
        params.put( "kursId", cepr01030102Form.getCurrencyCd() );
        params.put( "liUsia", cepr01030101Form.getInsuredAge() );
        ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( params ) );
		if( cepr01030102Form.getPaymentModeCd().equals(1)){  //triwulan
			ldec_pct = 0.35;
		}else if( cepr01030102Form.getPaymentModeCd().equals(2) ){ //semesteran
			ldec_pct = 0.65;
		}else if( cepr01030102Form.getPaymentModeCd().equals(6) ){ //bulanan
			ldec_pct = 0.12;
		}
		ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) , 2 );
        ldec_total += ldec_temp;
        
        for( int i = 0; i < cepr01030107Form.getParticipantVOList().size(); i++ )
        {
        	if( cepr01030107Form.getParticipantVOList().get(i).getAge() != null && cepr01030107Form.getParticipantVOList().get(i).getAge() > 0 ){
	            li_usia = cepr01030107Form.getParticipantVOList().get(i).getAge();
	
	            Map<String, Object> paramsPeserta = new HashMap<String, Object>();
	            paramsPeserta.put( "riderBaru", cepr01030102Form.getRightPartOfBusinessCd()  );    
	            paramsPeserta.put( "kursId", cepr01030102Form.getCurrencyCd() );
	            paramsPeserta.put( "liUsia", li_usia );
	            ldec_rate = LazyConverter.toDouble( eproposalManager.selectEkaSehatInnerLimitRider( paramsPeserta ) );
	            ldec_temp = FormatNumber.round( ( ldec_rate* ldec_pct ) * 0.975 , 2 );
	            ldec_total += ldec_temp;
        	}
        }
        ldec_total = FormatNumber.round( ldec_total , 2 );
        premium = new BigDecimal( ldec_total );
        return premium;
    }
}