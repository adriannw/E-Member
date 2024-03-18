package id.co.sinarmaslife.eproposal.product.product01040210;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	:
 * Program Name   		: Cepr01040210Business
 * Description         	: Excel Link 80 / Eka Link 80 (115)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 8, 2008 2:24:22 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Ilustration119Business;
import id.co.sinarmaslife.eproposal.common.pb.PbConverter;
import id.co.sinarmaslife.eproposal.common.pb.PbFunction;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.pb.N_riders;
import id.co.sinarmaslife.eproposal.model.pb.S_biaya;
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030104Form;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.FormatNumber;

public class Cepr01040210Business extends Cepr01040000Ilustration119Business {
	protected final Log logger = LogFactory.getLog(getClass());

	public Cepr01040210Business(EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command) {
		super(eproposalManager, editorUtil, command);
		setSekaligusRightPartCdArr(new Integer[] { Cepr01040210Mapper.X4 });
		setBerkalaRightPartCdArr(new Integer[] { Cepr01040210Mapper.X5, Cepr01040210Mapper.X6 });
		// setInsuredAgeLimit( 80 );

		String productLabel = "EXCELLINK";

		setInvestmentTitleArr(new String[] { "", productLabel + " Fixed Income Fund", productLabel + " Dynamic Fund",
				productLabel + " Aggressive Fund", productLabel + " Secure Dollar Income",
				productLabel + " Dynamic Dollar Fund" });
		setInvestmentTitleAndDescrArr(new String[][] { { "", "", "" },
				{ "", "- " + productLabel + " Fixed Income Fund : 100% Fixed Income",
						"  Penempatan dana pada investasi pendapatan tetap dan instrumen pasar uang." },
				{ "", "- " + productLabel + " Dynamic Fund : minimum 50% Fixed Income dan maksimum 50% Ekuitas",
						"  Penempatan dana pada investasi pendapatan tetap, ekuitas serta instrumen pasar uang." },
				{ "", "- " + productLabel
						+ " Aggressive Fund : maksimum 100% Fixed Income Rupiah atau maksimum 50% Fixed",
						"  Income US Dollar atau maksimum 100% Ekuitas" },
				{ "", "- " + productLabel + " Secure Dollar Income Fund : 100% Fixed Income.", null },
				{ "", "- " + productLabel + " Dynamic Dollar Fund : minimum 50% Fixed Income dan maksimum 50% Ekuitas.",
						null }, });

		istr_prop = PbConverter.get_istr_prop(command, insuredAgeLimit);
	}

	public List<Map<String, Object>> getCommonHeaderRowList() throws IOException {
		logger.info("*-*-*-* Cepr01040201Business.getCommonHeaderRowList");

		List sekaligusRightPartCdList = ArrUtil.toListFromArray(sekaligusRightPartCdArr);
		List berkalaRightPartCdList = ArrUtil.toListFromArray(berkalaRightPartCdArr);

		Cepr01030000HoldingForm cepr01030000HoldingForm = (Cepr01030000HoldingForm) command;
		Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

		CommonHeaderDataSource source = new CommonHeaderDataSource(command, editorUtil, eproposalManager);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		// Fill header content from CommonHeaderDataSource
		result.addAll(source.getPolicyHolderNameMap());
		result.addAll(source.getPolicyHolderAgeMap());
		result.addAll(source.getInsuredNameMap());
		result.addAll(source.getInsuredAgeMap());
		result.addAll(source.getTermOfContractMap());

		Map<String, Object> params;
		if (sekaligusRightPartCdList.contains(cepr01030102Form.getRightPartOfBusinessCd())) {
			params = new HashMap<String, Object>();
			params.put("label", "Premi Pokok Sekaligus");
			params.put("content", commonUsedBusiness.toMoneyWithCurrencyCd(cepr01030102Form.getPremium()) + ".00");
			result.add(params);
		} else if (berkalaRightPartCdList.contains(cepr01030102Form.getRightPartOfBusinessCd())) {
			params = new HashMap<String, Object>();
			if (cepr01030102Form.getPaymentModeCd().toString().equals("3")) {
				params.put("label", "Premi Pokok Tahunan");
			} else if (cepr01030102Form.getPaymentModeCd().toString().equals("6")) {
				params.put("label", "Premi Pokok Bulanan");
			}
			if (cepr01030102Form.getPaymentModeCd().toString().equals("2")) {
				params.put("label", "Premi Pokok Semesteran");
			}
			if (cepr01030102Form.getPaymentModeCd().toString().equals("1")) {
				params.put("label", "Premi Pokok Triwulanan");
			}
			params.put("content", commonUsedBusiness.toMoneyWithCurrencyCd(cepr01030102Form.getBasePremium()) + ".00"
					+ " (" + cepr01030102Form.getPremiumCombinationCd().toString() + "%)");
			result.add(params);

			params = new HashMap<String, Object>();
			if (cepr01030102Form.getPaymentModeCd().toString().equals("3")) {
				params.put("label", "Premi Top Up Berkala Tahunan");
			} else if (cepr01030102Form.getPaymentModeCd().toString().equals("6")) {
				params.put("label", "Premi Top Up Berkala Bulanan");
			}
			if (cepr01030102Form.getPaymentModeCd().toString().equals("2")) {
				params.put("label", "Premi Top Up Berkala Semesteran");
			}
			if (cepr01030102Form.getPaymentModeCd().toString().equals("1")) {
				params.put("label", "Premi Top Up Berkala Triwulanan");
			}
			params.put("content",
					commonUsedBusiness.toMoneyWithCurrencyCd(cepr01030102Form.getTopUpPremium()) + ".00" + " ("
							+ (new BigDecimal("100").subtract(cepr01030102Form.getPremiumCombinationCd())).toString()
							+ "%)");
			result.add(params);

			params = new HashMap<String, Object>();
			params.put("label", "");
			params.put("content", "------------------------");
			result.add(params);

			params = new HashMap<String, Object>();
			if (cepr01030102Form.getPaymentModeCd().toString().equals("3")) {
				params.put("label", "Total Premi Tahunan");
			} else if (cepr01030102Form.getPaymentModeCd().toString().equals("6")) {
				params.put("label", "Total Premi Bulanan");
			}
			if (cepr01030102Form.getPaymentModeCd().toString().equals("2")) {
				params.put("label", "Total Premi Semesteran");
			}
			if (cepr01030102Form.getPaymentModeCd().toString().equals("1")) {
				params.put("label", "Total Premi Triwulanan");
			}
			params.put("content", commonUsedBusiness.toMoneyWithCurrencyCd(cepr01030102Form.getPremium()) + ".00");
			result.add(params);

			params = new HashMap<String, Object>();
			params.put("label", "Masa Pembayaran Premi");
			params.put("content", editorUtil.convertToString(cepr01030102Form.getPremiumFurloughYear()) + " tahun");
			result.add(params);
		}

		result.addAll(source.getTotalSumInsuredMap());
		result.addAll(source.getPolicyHolderMedicalCheckUpMap((String) of_cek_medis(command).get("medis_pp")));
		result.addAll(source.getInsuredMedicalCheckUpMap((String) of_cek_medis(command).get("medis_tt")));

		return result;
	}

	public List<Map<String, Object>> getCommonHeaderSmallRowList() throws IOException {
		logger.info("*-*-*-* Cepr01040201Business.getCommonHeaderSmallRowList");

		List sekaligusRightPartCdList = ArrUtil.toListFromArray(sekaligusRightPartCdArr);
		List berkalaRightPartCdList = ArrUtil.toListFromArray(berkalaRightPartCdArr);

		Cepr01030000HoldingForm cepr01030000HoldingForm = (Cepr01030000HoldingForm) command;
		Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

		CommonHeaderDataSource source = new CommonHeaderDataSource(command, editorUtil, eproposalManager);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		// Fill header content from CommonHeaderDataSource
		result.addAll(source.getInsuredNameMap());
		result.addAll(source.getInsuredAgeMap());
		result.addAll(source.getTermOfContractMap());

		Map<String, Object> params;
		if (sekaligusRightPartCdList.contains(cepr01030102Form.getRightPartOfBusinessCd())) {
			params = new HashMap<String, Object>();
			params.put("label", "Premi Pokok Sekaligus");
			params.put("content", commonUsedBusiness.toMoneyWithCurrencyCd(cepr01030102Form.getPremium()) + ".00");
			result.add(params);
		} else if (berkalaRightPartCdList.contains(cepr01030102Form.getRightPartOfBusinessCd())) {
			params = new HashMap<String, Object>();

			if (cepr01030102Form.getPaymentModeCd().toString().equals("3")) {
				params.put("label", "Premi Pokok Tahunan");
			} else if (cepr01030102Form.getPaymentModeCd().toString().equals("6")) {
				params.put("label", "Premi Pokok Bulanan");
			}
			if (cepr01030102Form.getPaymentModeCd().toString().equals("2")) {
				params.put("label", "Premi Pokok Semesteran");
			}
			if (cepr01030102Form.getPaymentModeCd().toString().equals("1")) {
				params.put("label", "Premi Pokok Triwulanan");
			}

			params.put("content", commonUsedBusiness.toMoneyWithCurrencyCd(cepr01030102Form.getBasePremium()) + ".00"
					+ " (" + cepr01030102Form.getPremiumCombinationCd().toString() + "%)");
			result.add(params);

			params = new HashMap<String, Object>();
			if (cepr01030102Form.getPaymentModeCd().toString().equals("3")) {
				params.put("label", "Premi Top Up Berkala Tahunan");
			} else if (cepr01030102Form.getPaymentModeCd().toString().equals("6")) {
				params.put("label", "Premi Top Up Berkala Bulanan");
			}
			if (cepr01030102Form.getPaymentModeCd().toString().equals("2")) {
				params.put("label", "Premi Top Up Berkala Semesteran");
			}
			if (cepr01030102Form.getPaymentModeCd().toString().equals("1")) {
				params.put("label", "Premi Top Up Berkala Triwulanan");
			}
			params.put("content",
					commonUsedBusiness.toMoneyWithCurrencyCd(cepr01030102Form.getTopUpPremium()) + ".00" + " ("
							+ (new BigDecimal("100").subtract(cepr01030102Form.getPremiumCombinationCd())).toString()
							+ "%)");
			result.add(params);

			params = new HashMap<String, Object>();
			params.put("label", "");
			params.put("content", "------------------------");
			result.add(params);

			params = new HashMap<String, Object>();
			if (cepr01030102Form.getPaymentModeCd().toString().equals("3")) {
				params.put("label", "Total Premi Tahunan");
			} else if (cepr01030102Form.getPaymentModeCd().toString().equals("6")) {
				params.put("label", "Total Premi Bulanan");
			}
			if (cepr01030102Form.getPaymentModeCd().toString().equals("2")) {
				params.put("label", "Total Premi Semesteran");
			}
			if (cepr01030102Form.getPaymentModeCd().toString().equals("1")) {
				params.put("label", "Total Premi Triwulanan");
			}
			params.put("content", commonUsedBusiness.toMoneyWithCurrencyCd(cepr01030102Form.getPremium()) + ".00");
			result.add(params);

			params = new HashMap<String, Object>();
			params.put("label", "Masa Pembayaran Premi");
			params.put("content", editorUtil.convertToString(cepr01030102Form.getPremiumFurloughYear()) + " tahun");
			result.add(params);
		}

		result.addAll(source.getTotalSumInsuredMap());

		return result;
	}

	public double of_get_coi_basic_119(String kurs, int ai_th) {
		logger.info("*-*-*-* Cepr01040201Business.of_get_coi_basic");
		double ldec_total = 0, ldec_temp;
		double ldec_rate;
		int li_usia;
		String li_sex;  //IGA - PROJECT NEW COI
        li_sex = cepr01030101Form.getInsuredSexCd();
        if(li_sex.equals("L")){
        	li_sex = "1".trim();
        }else if(li_sex.equals("P")){
        	li_sex = "0".trim();
        }else{
        	li_sex = "9".trim();
        }
		li_usia = istr_prop.umur_tt; // + ai_th - 1;
		
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("liUsia", li_usia);
		par.put("kurs_id", kurs);
		par.put("liSex", li_sex);
		ldec_rate = eproposalManager.selectLdecRateToGetCoi_119(par).doubleValue();

		ldec_temp = FormatNumber.round(((istr_prop.up / 1000) * ldec_rate) * 0.1, 2);
		ldec_total += ldec_temp;

		logger.info("*-*-*-* ldec_total = " + ldec_total);
		return ldec_total;
	}

	public List<Map<String, Object>> getMonthlyPremiumList(Cepr01030102Form cepr01030102Form, int umur_tt)
			throws IOException {
		logger.info("*-*-*-* Cepr01040201Business.getMonthlyPremiumList");
		double ldec_coi;

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		Map<String, Object> params;

		ldec_coi = of_get_coi_basic_119(cepr01030102Form.getCurrencyCd(), umur_tt);
		params = new HashMap<String, Object>();
		params.put("assuranceBenefit", "Dasar");
		params.put("productName", istr_prop.nama_plan[1]);
		params.put("monthlyPremium", editorUtil.convertToRoundedTwoDigits(ldec_coi));
		result.add(params);

		String assuranceBenefit;
		boolean firstBenefitFlag = true;
		N_riders ln_riders = new N_riders();
		for (int i = 1; i <= ArrUtil.upperBound(istr_prop.rider_baru); i++) {
			if (istr_prop.rider_baru[i] > 0) {
				if (firstBenefitFlag) {
					assuranceBenefit = "Tambahan";
					firstBenefitFlag = false;
				} else {
					assuranceBenefit = "";
				}

				ldec_coi = of_get_coi_119_in_header(i, 1);

				params = new HashMap<String, Object>();
				params.put("assuranceBenefit", assuranceBenefit);
				if (istr_prop.bisnis_no == 2
						&& "Payor 25 TDP/Death".equals(ln_riders.of_nama(i, cepr01030102Form, cepr01030103Form))) {
					params.put("productName", "Payor 3 TDP/Death");
				} else if (istr_prop.bisnis_no == 3
						&& "Payor 25 TDP/Death".equals(ln_riders.of_nama(i, cepr01030102Form, cepr01030103Form))) {
					params.put("productName", "Payor 6 TDP/Death");
				} else {
					params.put("productName", ln_riders.of_nama(i, cepr01030102Form, cepr01030103Form));
				}
				params.put("monthlyPremium", editorUtil.convertToMoney(ldec_coi, cepr01030102Form) + ".00");
				result.add(params);
			}
		}

		return result;
	}

	// I got this from wf_set_119
	public IllustrationResultVO getIllustrationResult() {
		logger.info("*-*-*-* Cepr01040000Ilustration116Business.getIllustrationResult");
		IllustrationResultVO result = new IllustrationResultVO();
		Cepr01030102Form cepr01030102Form = ((Cepr01030000HoldingForm) command).getCepr01030102Form();
		Cepr01030101Form cepr01030101Form = ((Cepr01030000HoldingForm) command).getCepr01030101Form();
		Cepr01030104Form cepr01030104Form = ((Cepr01030000HoldingForm) command).getCepr01030104Form();

		ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		List<TopupDrawVO> topupDrawVOList = cepr01030104Form.getTopupDrawVOList();
		int topupDrawVOListSize = topupDrawVOList.size();
		TopupDrawVO topupDrawVO;
		String premiumTotal;
		String topup;
		String draw;

		int li_ke = 0, li_bagi = 1000, li_hal = 3;
		double[] ldec_bak;
		double ldec_bawal = 100000;
		double[] ldec_man_non = new double[2 + 1];
		double ldec_bak_tu = 0.05;
		double ldec_bak_tut = 0.05;
		double ldec_akuisisi;
		double ldec_premi_invest;
		double[][] ldec_hasil_invest = new double[5 + 1][3 + 1];
		double[] ldec_tarik = { CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0 };
		double ldec_wdraw;
		double[] ldec_premi_bulan = new double[12 + 1];
		double ldec_topup;
		double ldec_bass;
		double[][] ldec_bunga = new double[5 + 1][3 + 1];
		double[] ldec_bunga_avg = new double[3 + 1]; // = {0.06, 0.1, 0.08, 0.18, 0.12, 0.25} //{0.09, 0.06, 0.11, 0.15,
														// 0.165, 0.25} //fixed:0.09, 0.11, 0.15 (0.165); dynamic:0.08,
														// 0.11, 0.18 (0.195)
		double ldec_fee = 0.020075;
		double ldec_coi = 0, ldec_mfc, ldec_sc, ldec_cost, ldec_man_celaka, ldec_temp = 0; // , ldec_man[10+1] = {1, 1,
																							// 1, 1.1, 1.2, 1.3, 1.4,
																							// 1.5, 1.6, 1.7}
		double ld_max = 100000;
		boolean[] lb_minus = { CommonConst.DUMMY_FALSE, false, false, false };
		boolean lb_rider = false;
		String ls_sy = "", ls_temp; // ls_dpp = ' dari Premi Pokok', ls_sy = ''
		S_biaya lstr;
		// n_riders ln_riders
		double ldec_manfaat, ldec_premi_setahun = 0, ldec_coi24 = 0;
		//// monthly fix cost
		ldec_mfc = 15000;
		if (Hardcode.CUR_USD_CD.equals(cepr01030102Form.getCurrencyCd())) {
			ldec_mfc = 2;
			li_bagi = 1;
		} else {
			ld_max = Math.pow(1000, 3);
		}

		for (int i = 1; i <= 12; i++) {
			ldec_premi_bulan[i] = 0;
			if (i == 1)
				ldec_premi_bulan[i] = istr_prop.premi;
			if (Hardcode.PAY_MODE_CD_TRIWULANAN == cepr01030102Form.getPaymentModeCd()) {
				if (i == 4 || i == 7 || i == 10)
					ldec_premi_bulan[i] = istr_prop.premi;
			} else if (Hardcode.PAY_MODE_CD_SEMESTERAN == cepr01030102Form.getPaymentModeCd()) {
				if (i == 7)
					ldec_premi_bulan[i] = istr_prop.premi;
			} else if (Hardcode.PAY_MODE_CD_BULANAN == cepr01030102Form.getPaymentModeCd()) {
				ldec_premi_bulan[i] = istr_prop.premi;
			}
			ldec_premi_setahun += ldec_premi_bulan[i];
		}

		for (int i = 1; i <= 3; i++) {
			ldec_hasil_invest[1][i] = 0;
		}

		ldec_manfaat = istr_prop.up;
		//// Biaya Akuisisi & asumsi penarikan (1 tarik, 1-nya tdk tarik)

		lstr = PbFunction.wf_get_biaya_84(istr_prop, command);
		ldec_bak = lstr.bak;
		ldec_bunga = lstr.bunga;

		int a, b;
		if ("01".equals(istr_prop.kurs_id)) {
			a = 1;
			b = 3;
		} else {
			a = 4;
			b = 5;
		}

		for (int i = 1; i <= 3; i++) {
			ldec_bunga_avg[i] = 0;
			for (int j = a; j <= b; j++) {
				ldec_bunga_avg[i] += ldec_bunga[j][i] * (istr_prop.fund[j] / 100);
			}
		}

		ldec_coi = of_get_coi_119(cepr01030102Form.getCurrencyCd(), 1);
		double[] np = new double[4];
		double[] non = new double[4];
		double[] celaka = new double[4];

		int j;
		for (int i = 1; i <= cepr01030102Form.getTermOfContract(); i++) {
			// surrender charge
			ldec_sc = 0;
			ldec_wdraw = 0;
			ldec_topup = 0;
			ldec_akuisisi = 0;
			// ldec_coi = of_get_coi( i );
			if (i <= ArrUtil.upperBound(lstr.tarik))
				ldec_wdraw = lstr.tarik[i];
			if (i <= ArrUtil.upperBound(lstr.topup))
				ldec_topup = lstr.topup[i];
			if (ArrUtil.upperBound(ldec_bak) > i)
				ldec_akuisisi = ldec_bak[i];
			if (i > 6) {
				ldec_coi = 0;
			} else {
				ldec_coi = of_get_coi_119(cepr01030102Form.getCurrencyCd(), i);
			}

			// if( i <= 5 && ( istr_prop.bisnis_id == 115 || istr_prop.bisnis_id == 152 ) )
			// ldec_sc = ldec_tarik[i];

			ldec_cost = (ldec_coi + ldec_mfc);

			for (int k = 1; k <= 3; k++) {
				for (int li_bulan = 1; li_bulan <= 12; li_bulan++) {
					ldec_premi_invest = 0;
					if (i <= cepr01030102Form.getPremiumFurloughYear()) {
						ldec_premi_invest = ((ldec_premi_bulan[li_bulan] * (istr_prop.pct_premi / 100))
								* (1 - ldec_akuisisi));
						ldec_premi_invest += ((ldec_premi_bulan[li_bulan] * (100 - istr_prop.pct_premi) / 100))
								* (1 - ldec_bak_tu); // topup berkala
					}
					if (li_bulan == 1)
						ldec_premi_invest += (ldec_topup * (1 - ldec_bak_tut)); // topup tunggal

					double copy = ldec_hasil_invest[1][k];

					ldec_hasil_invest[1][k] = (ldec_premi_invest + copy - ldec_cost)
							* Math.pow((1 + ldec_bunga_avg[k]), ((double) 1 / 12));
				}

				ldec_hasil_invest[1][k] -= (ldec_wdraw * (1 + ldec_sc));

				if (ldec_hasil_invest[1][k] <= 0
						&& ((istr_prop.umur_tt < 50 && i <= 15) || (istr_prop.umur_tt >= 50 && i <= 10))) {
					lb_minus[k] = true;
				}
			}

			j = cepr01030101Form.getInsuredAge() + i;
			if (i <= 21 || j == 55 || j == 65 || j == 75 || j == 80 || j == 100
					|| (j == 88 && istr_prop.bisnis_id == 162)) {
				if (j <= 69) {
					ldec_man_celaka = Math.min(ld_max, ldec_manfaat) + ldec_manfaat;
				} else {
					ldec_man_celaka = ldec_manfaat;
				}

				for (int k = 1; k <= 3; k++) {
					np[k] = FormatNumber.round(ldec_hasil_invest[1][k] / li_bagi, 0);
					non[k] = FormatNumber.round((ldec_hasil_invest[1][k] + ldec_manfaat) / li_bagi, 0);
					celaka[k] = FormatNumber.round((ldec_hasil_invest[1][k] + ldec_manfaat) / li_bagi, 0);
				}

				if (i <= istr_prop.cuti_premi) {
					premiumTotal = editorUtil.convertToStringWithoutCent(ldec_premi_setahun / li_bagi);
				} else {
					premiumTotal = "";
				}

				if (i < topupDrawVOListSize) {
					// why ( i - 1 )? becoz index in Java start from 0, not like PB programming
					// language
					topupDrawVO = topupDrawVOList.get(i - 1);
					topup = "0";
					draw = "0";
					if (topupDrawVO.getYearFlag() != null && topupDrawVO.getYearFlag().equals("true")) {
						topup = editorUtil.convertToString(topupDrawVO.getTopupAmount().divide(new BigDecimal("1000")));
						draw = editorUtil.convertToString(topupDrawVO.getDrawAmount().divide(new BigDecimal("1000")));
					}
					if ("0".equals(topup))
						topup = "0.00";
					if ("0".equals(draw))
						draw = "0.00";
				} else {
					topup = "0.00";
					draw = "0.00";
				}

				String valueLow = editorUtil.convertToStringWithoutCentAndNillIfNegative(new BigDecimal(np[1]));
				String valueMid = editorUtil.convertToStringWithoutCentAndNillIfNegative(new BigDecimal(np[2]));
				String valueHi = editorUtil.convertToStringWithoutCentAndNillIfNegative(new BigDecimal(np[3]));

				String benefitLow = convertToStringWithoutCentAndSetNill(editorUtil, non[1], np[1]);
				String benefitMid = convertToStringWithoutCentAndSetNill(editorUtil, non[2], np[2]);
				String benefitHi = convertToStringWithoutCentAndSetNill(editorUtil, non[3], np[3]);

				String benefitLow2 = convertToStringWithoutCentAndSetNill(editorUtil, celaka[1], np[1]);
				String benefitMid2 = convertToStringWithoutCentAndSetNill(editorUtil, celaka[2], np[2]);
				String benefitHi2 = convertToStringWithoutCentAndSetNill(editorUtil, celaka[3], np[3]);

				map = new HashMap<String, Object>();
				map.put("yearNo", editorUtil.convertToString(i));
				map.put("insuredAge", editorUtil.convertToString(cepr01030101Form.getInsuredAge() + i));
				map.put("premiumTotal", premiumTotal);
				map.put("topupAssumption", topup);
				map.put("drawAssumption", draw);
				map.put("valueLow", valueLow);
				map.put("valueMid", valueMid);
				map.put("valueHi", valueHi);
				map.put("benefitLow", benefitLow);
				map.put("benefitMid", benefitMid);
				map.put("benefitHi", benefitHi);
				map.put("benefitLow2", benefitLow2);
				map.put("benefitMid2", benefitMid2);
				map.put("benefitHi2", benefitHi2);
				mapList.add(map);

			}
		}

		result.setValidityMsg(lb_minus[1] ? "Maaf, Proposal yang Anda buat Tidak Layak Jual." : "");
		result.setIllustrationList(mapList);
		return result;

	}
}