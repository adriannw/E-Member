package id.co.sinarmaslife.eproposal.product.product01040211;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040211X10Entry
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
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000StringUtil;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.lookup.LookupList;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_premium_comb;
import id.co.sinarmaslife.eproposal.model.vo.AmountVO;
import id.co.sinarmaslife.eproposal.model.vo.UlinkValidatorParVO;
import id.co.sinarmaslife.eproposal.product.product01040205.Cepr01040205Mapper;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.StringUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CeprMasterEntry extends Cepr01040211Entry {
	protected final Log logger = LogFactory.getLog(getClass());

	public CeprMasterEntry(EproposalManager eproposalManager,
			Cepr00000000EditorUtil editorUtil, Object command, Errors errors) {
		super(eproposalManager, editorUtil, command, errors);
		logger.info("*-*-*-* CeprMasterEntry constructor is called ...");
	}

	public void initDisplayedForm() {
		logger.info("*-*-*-* CeprMasterEntry.initDisplayedForm");
		super.initDisplayedForm();

		cepr01030102Form
				.setPremiumFurloughYearDisplay(CommonConst.DISPLAY_FALSE);
		cepr01030102Form
				.setPremiumFurloughYearListDisplay(CommonConst.DISPLAY_FALSE);

		if (credentialsVO != null) {
			if (credentialsVO.getMsagId().equals(Hardcode.BANK_SINARMAS)
					|| credentialsVO.getGroupId().equals(
							Hardcode.GROUP_BANK_SINARMAS)
					|| credentialsVO.getGroupId()
							.equals(Hardcode.GROUP_BANCASS)
					|| credentialsVO.getGroupId().equals(
							Hardcode.GROUP_BANCASS_SUPPORT)
					|| credentialsVO.getGroupId().equals(
							Hardcode.GROUP_AKTUARIA)) {
				cepr01030102Form.setJenisListDisplay(CommonConst.DISPLAY_TRUE);
			}
			if (credentialsVO.getType() != null) {
				if (credentialsVO.getType().equals("bsm")) {
					cepr01030102Form
							.setJenisListDisplay(CommonConst.DISPLAY_TRUE);
				}
			}
		}
	}

	public void initDisabledForm() {
		logger.info("*-*-*-* CeprMasterEntry.initDisabledForm");
		super.initDisabledForm();
		cepr01030102Form
				.setPremiumCombinationListIsDisabled(CommonConst.DISABLED_FALSE);
		cepr01030102Form
				.setTotalSumInsuredIsDisabled(CommonConst.DISABLED_FALSE);
		cepr01030102Form
				.setPremiumFurloughYearIsDisabled(CommonConst.DISABLED_TRUE);
		cepr01030102Form.setJenisListIsDisabled(CommonConst.DISABLED_FALSE);

	}

	public void initReadOnlyForm() {
		logger.info("*-*-*-* CeprMasterEntry.initReadOnlyForm");
		super.initReadOnlyForm();
	}

	public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged() {
		logger.info("*-*-*-* CeprMasterEntry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged");
		cepr01030102Form
				.setTotalSumInsuredIsDisabled(CommonConst.DISABLED_FALSE);
		cepr01030102Form.setPremiumFurloughYear(1);
		cepr01030102Form.setPaymentModeCd(Hardcode.PAY_MODE_CD_TAHUNAN);

	}

	public void fillDefaultValueEachTimeFormCalled() {
		logger.info("*-*-*-* CeprMasterEntry.fillDefaultValueEachTimeFormCalled");
		super.fillDefaultValueEachTimeFormCalled();

		cepr01030102Form.setJenisList(comboLookupBusiness
				.getJenisOptionVOBSIMList(false));
		
		int[] fundValue = new int[] { 0, 10, 20, 30, 40, 50, 60,
				70, 80, 90, 100 };

		cepr01030102Form.setFixIdrList(LookupList.getPercentSequenceList(fundValue));
		cepr01030102Form.setDynamicIdrList(LookupList.getPercentSequenceList(fundValue));
		cepr01030102Form.setAggresiveIdrList(LookupList.getPercentSequenceList(fundValue));
		cepr01030102Form.setSecureUsdList(LookupList.getPercentSequenceList(fundValue));
		cepr01030102Form.setDynamicUsdList(LookupList.getPercentSequenceList(fundValue));
		
		if (cepr01030102Form.getSmileLadiesPackageCd() != null
				&& cepr01030102Form.getSmileLadiesPackageCd() > 1) {
			cepr01030102Form.setCurrencyList(comboLookupBusiness
					.selectCurrencyOptionVOList(false,
							new String[] { Hardcode.CUR_IDR_CD }));
		} else {
			cepr01030102Form.setCurrencyList(comboLookupBusiness
					.selectCurrencyOptionVOList(false, new String[] {
							Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD }));
		}
		
		List<Integer> payModeList = new ArrayList<Integer>();
		for(int i = 0 ; i < cepr01030102Form.getProduct_setup().getPayModeList().size() ; i++){
			payModeList.add(cepr01030102Form.getProduct_setup().getPayModeList().get(i).getLscb_id());
		}
		
		cepr01030102Form.setPaymentModeList(comboLookupBusiness
				.selectPayModeOptionVOList(false, (Integer[]) payModeList.toArray()));
		
		cepr01030102Form
				.setPaymentModeListIsDisabled(CommonConst.DISABLED_FALSE);
		
		cepr01030102Form.setTermOfPayment(cepr01030102Form.getProduct_setup()
				.getLst_product_setup().getPay_period());
		
		cepr01030102Form.setTermOfContract(100 - cepr01030101Form
				.getInsuredAge());

		cepr01030102Form
				.setPremiumCombinationList(getPremiumCombinationNewList(cepr01030102Form
						.getProduct_setup().getPremiumCombList()));
		
		refreshBaseTopupPremium();

	}

	// OK
	// PENDING : validateInvestmentSumIs100Percent
	public void validateCurrentPage() {
		logger.info("*-*-*-* CeprMasterEntry.validateCurrentPage");
		double premium = LazyConverter.toDouble(cepr01030102Form.getPremium());
		double minPremium = cepr01030102Form.getProduct_setup()
				.getLst_product_calc().getMin_premium();

		if (cepr01030102Form.getPremium() != null) {

			double minUp = getMinUp();
			double maxUp = getMaxUp();
			double idec_up = getUp();

			// validate range
			if (idec_up <= minUp) {
				idec_up = minUp;
			}

			commonValidator.validateMinimumMoney(
					Cepr01030102FormConst.TOTAL_SUM_INSURED, LazyConverter
							.toDouble(cepr01030102Form.getTotalSumInsured()),
					minUp, minUp);

			commonValidator.validateMaximumMoney(
					Cepr01030102FormConst.TOTAL_SUM_INSURED, LazyConverter
							.toDouble(cepr01030102Form.getTotalSumInsured()),
					maxUp, maxUp);

			commonValidator.validateMinimumMoney(Cepr01030102FormConst.PREMIUM,
					premium, minPremium, minPremium);

			commonValidator.validateInvestmentSumIs100Percent();
		} else {
			errors.rejectValue(Cepr01030102FormConst.PREMIUM,
					"error.formEmpty", null, null);
		}

	}

	// OK
	public void processFormAfterSubmitBeforeValidation() {
		/*
		logger.info("*-*-*-* CeprMasterEntry.processFormAfterSubmitBeforeValidation");
		refreshBaseTopupPremium();
		if ("onChangePremium".equals(cepr01030102Form.getTheEvent())
				|| "onPressButtonCountTotalSumInsured".equals(cepr01030102Form
						.getTheEvent())
				|| cepr01030102Form.getTotalSumInsured().equals(
						new BigDecimal("0"))) {

			double idec_up = getUp();
			double minUp = getMinUp();

			// validate range
			if (idec_up <= minUp) {
				idec_up = minUp;
			}

			cepr01030102Form.setTotalSumInsured(new BigDecimal(idec_up));
			cepr01030102Form.setTotalSumInsuredFix(new BigDecimal(idec_up));
		}
		*/

	}

	// PENDING : validatePreviousPage
	public void validatePreviousPage() {
		super.validatePreviousPage();

	}

	// OK
	public double getUp() {
		double premium = LazyConverter.toDouble(cepr01030102Form.getPremium());
		double premiumCombinationPercent = LazyConverter
				.toDouble(cepr01030102Form.getPremiumCombinationCd());
		double idec_up = 0.0;
		double adec_premi = premium;
		int li_kali = cepr01030102Form.getProduct_setup().getLst_product_calc()
				.getLi_kali();

		String query = cepr01030102Form.getProduct_setup()
				.getLst_product_calc().getF_up();

		if (StringUtil.isEmpty(query)) {
			idec_up = cepr01030102Form.getProduct_setup().getLst_product_calc()
					.getUp();
		} else {
			query = query.replaceAll(Pattern.quote("P{BASE_PREMIUM}"),
					Cepr00000000StringUtil.toString(adec_premi));
			query = query.replaceAll(Pattern.quote("P{LI_KALI}"),
					Cepr00000000StringUtil.toString(li_kali));
			idec_up = eproposalManager.selectNumericData(query).doubleValue();
		}

		// count totalSumInsured with premiumCombinationPercent
		idec_up = idec_up * premiumCombinationPercent / 100;

		return idec_up;
	}

	// OK
	public double getMinUp() {
		double minUp = 0.0;
		int li_kali = cepr01030102Form.getProduct_setup().getLst_product_calc()
				.getLi_kali();

		String query_minUp = cepr01030102Form.getProduct_setup()
				.getLst_product_calc().getF_min_up();

		if (StringUtil.isEmpty(query_minUp)) {
			minUp = cepr01030102Form.getProduct_setup().getLst_product_calc()
					.getMin_up();
		} else {
			query_minUp = query_minUp.replaceAll(Pattern
					.quote("P{BASE_PREMIUM}"), Cepr00000000StringUtil
					.toString(cepr01030102Form.getBasePremium()));
			query_minUp = query_minUp.replaceAll(Pattern.quote("P{LI_KALI}"),
					Cepr00000000StringUtil.toString(li_kali));
			minUp = eproposalManager.selectNumericData(query_minUp)
					.doubleValue();
		}
		return minUp;
	}

	// OK
	public double getMaxUp() {
		double li_kali = cepr01030102Form.getProduct_setup()
				.getLst_product_calc().getLi_kali();
		double maxUp = 0.0;
		String query_maxUp = cepr01030102Form.getProduct_setup()
				.getLst_product_calc().getF_max_up();

		if (StringUtil.isEmpty(query_maxUp)) {
			maxUp = cepr01030102Form.getProduct_setup().getLst_product_calc()
					.getMax_up();
		} else {
			query_maxUp = query_maxUp.replaceAll(Pattern
					.quote("P{BASE_PREMIUM}"), Cepr00000000StringUtil
					.toString(cepr01030102Form.getBasePremium()));
			query_maxUp = query_maxUp.replaceAll(Pattern.quote("P{LI_KALI}"),
					Cepr00000000StringUtil.toString(li_kali));
			query_maxUp = query_maxUp.replaceAll(Pattern.quote("P{UMUR_TT}"),
					Cepr00000000StringUtil.toString(cepr01030101Form
							.getInsuredAge()));
			query_maxUp = query_maxUp.replaceAll(Pattern.quote("P{LSBS_ID}"),
					Cepr00000000StringUtil.toString(cepr01030102Form
							.getLeftPartOfBusinessCd()));
			maxUp = eproposalManager.selectNumericData(query_maxUp)
					.doubleValue();
		}
		return maxUp;
	}

	// OK
	public List<OptionVO> getPremiumCombinationNewList(
			ArrayList<Lst_premium_comb> premiumCombList) {
		List<OptionVO> premiumCombinationList = new ArrayList<OptionVO>();

		OptionVO optionVO;
		optionVO = new OptionVO("", "");
		premiumCombinationList.add(optionVO);
		String label;

		for (int i = 0; i < premiumCombList.size(); i++) {
			if (premiumCombList.get(i).getBase_premium() == 100) {
				label = "Premi Pokok "
						+ premiumCombList.get(i).getBase_premium() + "%";
			} else {
				label = "Premi Pokok "
						+ premiumCombList.get(i).getBase_premium()
						+ "%, Premi Top-Up Berkala "
						+ premiumCombList.get(i).getTopup_premium() + "%";
			}
			optionVO = new OptionVO(
					Cepr00000000StringUtil.toString(premiumCombList.get(i)
							.getBase_premium()) + ".00", label);
			premiumCombinationList.add(optionVO);
		}

		return premiumCombinationList;
	}

}