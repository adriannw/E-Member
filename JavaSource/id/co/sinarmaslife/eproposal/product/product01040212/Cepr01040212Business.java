package id.co.sinarmaslife.eproposal.product.product01040212;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040212Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 10, 2009 3:26:40 PM
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
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Ilustration116Business;
import id.co.sinarmaslife.eproposal.common.pb.PbConverter;
import id.co.sinarmaslife.eproposal.common.pb.PbFunction;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.eproposal.model.pb.S_biaya;
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030103Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030104Form;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.MappingUtil;

public class Cepr01040212Business extends Cepr01040000Ilustration116Business {
	protected final Log logger = LogFactory.getLog(getClass());

	public Cepr01040212Business(EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command) {
		super(eproposalManager, editorUtil, command);
		// setSekaligusRightPartCdArr( new Integer[]{ Cepr01040212Mapper.X5,
		// Cepr01040212Mapper.X6 } );
		setBerkalaRightPartCdArr(new Integer[] { Cepr01040212Mapper.X7, Cepr01040212Mapper.X8, Cepr01040212Mapper.X9,
				Cepr01040212Mapper.X10 });
		setInsuredAgeLimit(100);

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

	public List<Map<String, Object>> getPageList(int currentIdx) throws IOException {
		logger.info("*-*-*-* Cepr01040212Business.getPageList");
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> params;

		params = new HashMap<String, Object>();
		params.put("pageCode", "sub1");
		result.add(params);

		params = new HashMap<String, Object>();
		params.put("pageCode", "sub2");
		result.add(params);

		// if no riders, don't show this page ( page 3 )
		// if( currentIdx != 0 )
		// {
		params = new HashMap<String, Object>();
		params.put("pageCode", "sub3");
		result.add(params);
		// }

		params = new HashMap<String, Object>();
		params.put("pageCode", "sub4");
		result.add(params);

		params = new HashMap<String, Object>();
		params.put("pageCode", "sub5");
		result.add(params);

		return result;
	}

	public String[][] getRiderPremiumList(Cepr01030102Form cepr01030102Form) throws IOException {
		double ldec_coi;

		String[][] riderTambahan = new String[8][7];
		BigDecimal totalRider = new BigDecimal(0);

		List sekaligusRightPartCdList = ArrUtil.toListFromArray(sekaligusRightPartCdArr);
		List berkalaRightPartCdList = ArrUtil.toListFromArray(berkalaRightPartCdArr);

		if (cepr01030102Form.getPaymentModeCd().toString().equals("3")) {
			riderTambahan[0][0] = "Biaya Asuransi Tambahan Pertahun";
			riderTambahan[0][2] = "tahun";
			riderTambahan[0][3] = "Tahun";
		} else if (cepr01030102Form.getPaymentModeCd().toString().equals("6")) {
			riderTambahan[0][0] = "Biaya Asuransi Tambahan Perbulan";
			riderTambahan[0][2] = "bulan";
			riderTambahan[0][3] = "Bulan";
		} else if (cepr01030102Form.getPaymentModeCd().toString().equals("2")) {
			riderTambahan[0][0] = "Biaya Asuransi Tambahan Persemester";
			riderTambahan[0][2] = "semester";
			riderTambahan[0][3] = "Semester";
		} else if (cepr01030102Form.getPaymentModeCd().toString().equals("1")) {
			riderTambahan[0][0] = "Biaya Asuransi Tambahan Pertriwulan";
			riderTambahan[0][2] = "triwulan";
			riderTambahan[0][3] = "Triwulan";
		} else if (cepr01030102Form.getPaymentModeCd().toString().equals("0")) {
			riderTambahan[0][0] = "Biaya Asuransi Tambahan Sekaligus";
			riderTambahan[0][2] = "sekaligus";
			riderTambahan[0][3] = "Sekaligus";
		}

		int j = 1;
		List<Mst_proposal> mstProposal = new ArrayList<Mst_proposal>();
		for (int i = 1; i <= ArrUtil.upperBound(istr_prop.rider_baru); i++) {
			if (istr_prop.rider_baru[i] > 0) {

				ldec_coi = of_get_coi_120_cost(i, 1, mstProposal);
				totalRider = totalRider.add(new BigDecimal(ldec_coi));
				riderTambahan[j][0] = of_nama(i, cepr01030102Form, cepr01030103Form);
				riderTambahan[j][1] = editorUtil.convertToRoundedTwoDigits(ldec_coi);
				riderTambahan[j][6] = ldec_coi + "";
				j = j + 1;
			}
		}
		riderTambahan[0][1] = totalRider.toString();

		return riderTambahan;
	}

	public String of_nama(int ai_no, Cepr01030102Form cepr01030102Form, Cepr01030103Form cepr01030103Form) {
		String ls_nama = "";
		String syariah = "";
		if (cepr01030102Form.getLeftPartOfBusinessCd() == 153)// produk produk syariah
		{
			syariah = " Syariah";
		} else {
			syariah = "";
		}
		switch (ai_no) {
		case 1:
			ls_nama = "SMiLe Personal Accident" + syariah;
			break;
		case 2:
			ls_nama = "SMiLe Hospital Protection" + syariah;
			break;
		case 3:
			ls_nama = "Total Permanent Disability (TPD)" + syariah;
			break;
		case 4:
			ls_nama = "Critical Illness (CI)" + syariah;
			break;
		case 5:
			ls_nama = "Waiver TPD" + syariah;
			break;
		case 6:
			ls_nama = "Payor TPD/Death" + syariah;
			break;
		case 7:
			ls_nama = "Waiver CI" + syariah;
			break;
		case 8:
			ls_nama = "Payor CI" + syariah;
			break;
		case 9:
			ls_nama = "Payor 25 CI" + syariah;
			break;
		case 10:
			ls_nama = "Payor Spouse 60 TPD/Death" + syariah;
			break;
		case 11:
			ls_nama = "SMiLe Hospital Protection Family" + syariah;
			break;
		case 12:
			ls_nama = "Term Rider" + syariah;
			break;
		case 13:
			ls_nama = "SMiLe Medical(Benefit As Charge)" + syariah;
			break;
		case 14:
			ls_nama = "Swine" + syariah;
			break;
		case 15:
			ls_nama = "SMiLe Medical(Benefit Inner Limit)" + syariah;
			break;
		case 16:
			ls_nama = "SMiLe Hospital Protection" + syariah + " (+)";
			break;
		case 17:
			String label = MappingUtil.getLabel(cepr01030103Form.getWaiverTpdCiChooseList(),
					cepr01030103Form.getWaiverTpdCiChooseCd());
			ls_nama = "Waiver " + label + " TPD/CI" + syariah;
			break;
		case 18:
			ls_nama = "Payor 25 TPD/CI/Death" + syariah;
			break;
		case 19:
			ls_nama = "SMiLe Ladies Insurance" + syariah;
			break;
		case 20:
			ls_nama = "SMiLe Ladies Hospital Protection" + syariah;
			break;
		case 21:
			ls_nama = "SMiLe Ladies Medical(Benefit As Charge)" + syariah;
			break;
		case 22:
			ls_nama = "SMiLe Ladies Medical(Benefit Inner Limit)" + syariah;
			break;
		case 23:
			ls_nama = "Payor TPD/CI/Death" + syariah;
			break;
		// case 14:
		// ls_nama = "Eka Sehat Inner Limit"+syariah; break;
		// case 15:
		// ls_nama = "HCP Provider"+syariah; break;
		// case 16:
		// ls_nama = "Payor 25 TPD/CI/Death"+syariah; break;
		// case 17:
		// String label =
		// MappingUtil.getLabel(cepr01030103Form.getWaiverTpdCiChooseList(),
		// cepr01030103Form.getWaiverTpdCiChooseCd());
		// ls_nama = "Waiver "+label+" TPD/CI"+syariah; break;
		// case 18:
		// ls_nama = "Ladies Insurance"+syariah; break;
		// case 19:
		// ls_nama = "Ladies HCP"+syariah; break;
		// case 20:
		// ls_nama = "Ladies Med. Expense"+syariah; break;
		// case 21:
		// ls_nama = "Ladies Med. Expense"+syariah; break;
		default:
			break;
		}

		return ls_nama;
	}

	public List<Map<String, Object>> getCommonHeaderRowList() throws IOException {
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
		// result.addAll( source.getTermOfContractWithLimitMap( insuredAgeLimit ) );

		Map<String, Object> params;

		params = new HashMap<String, Object>();
		params.put("label", "Masa Pertanggungan");
		params.put("content", cepr01030102Form.getTermOfContract() + " Tahun");
		result.add(params);
		if (sekaligusRightPartCdList.contains(cepr01030102Form.getRightPartOfBusinessCd())) {
			params = new HashMap<String, Object>();
			params.put("label", "Premi Pokok Sekaligus");
			params.put("content", commonUsedBusiness.toMoneyWithCurrencyCd(cepr01030102Form.getPremium()) + ".00");
			result.add(params);

			params = new HashMap<String, Object>();
			params.put("label", "Masa Pembayaran Premi");
			params.put("content", editorUtil.convertToString(cepr01030102Form.getTermOfPayment()) + " Tahun");
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
			// params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd(
			// cepr01030102Form.getBasePremium() ) + ".00" );
			params.put("content", commonUsedBusiness.toMoneyWithCurrencyCd(cepr01030102Form.getBasePremium()) + ".00"
					+ " (" + cepr01030102Form.getPremiumCombinationCd().toString() + "%)");
			result.add(params);

			if (!cepr01030102Form.getTopUpPremium().toString().equals("0")) {
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
				params.put("content", commonUsedBusiness.toMoneyWithCurrencyCd(cepr01030102Form.getTopUpPremium())
						+ ".00" + " ("
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
			}

			if (!cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().toString().equals("0")) {
				params = new HashMap<String, Object>();
				params.put("label", "Premi Top Up Single");
				params.put("content", commonUsedBusiness
						.toMoneyWithCurrencyCd(cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount()) + ".00");
				result.add(params);
			}

			params = new HashMap<String, Object>();
			params.put("label", "Masa Pembayaran Premi");
			params.put("content", editorUtil.convertToString(cepr01030102Form.getTermOfPayment()) + " Tahun");
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
		// result.addAll( source.getTermOfContractWithLimitMapInTwoRows( insuredAgeLimit
		// ) );

		Map<String, Object> params;

		params = new HashMap<String, Object>();
		params.put("label", "Masa Pertanggungan");
		params.put("content", cepr01030102Form.getTermOfContract() + " Tahun");
		result.add(params);

		if (sekaligusRightPartCdList.contains(cepr01030102Form.getRightPartOfBusinessCd())) {
			params = new HashMap<String, Object>();
			params.put("label", "Premi Pokok Sekaligus");
			params.put("content", commonUsedBusiness.toMoneyWithCurrencyCd(cepr01030102Form.getPremium()));
			result.add(params);

			params = new HashMap<String, Object>();
			params.put("label", "Masa Pembayaran Premi");
			params.put("content", editorUtil.convertToString(cepr01030102Form.getTermOfPayment()) + " Tahun");
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
			params.put("content", commonUsedBusiness.toMoneyWithCurrencyCd(cepr01030102Form.getBasePremium()) + " ("
					+ cepr01030102Form.getPremiumCombinationCd().toString() + "%)");
			result.add(params);

			if (!cepr01030102Form.getTopUpPremium().toString().equals("0")) {
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
				params.put("content", commonUsedBusiness.toMoneyWithCurrencyCd(cepr01030102Form.getTopUpPremium())
						+ " (" + (new BigDecimal("100").subtract(cepr01030102Form.getPremiumCombinationCd())).toString()
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
				params.put("content", commonUsedBusiness.toMoneyWithCurrencyCd(cepr01030102Form.getPremium()));
				result.add(params);
			}

			if (!cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().toString().equals("0")) {
				params = new HashMap<String, Object>();
				params.put("label", "Premi Top Up Single");
				params.put("content", commonUsedBusiness
						.toMoneyWithCurrencyCd(cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount()));
				result.add(params);
			}

			params = new HashMap<String, Object>();
			params.put("label", "Masa Pembayaran Premi");
			params.put("content", editorUtil.convertToString(cepr01030102Form.getTermOfPayment()) + " Tahun");
			result.add(params);
		}

		result.addAll(source.getTotalSumInsuredMap());

		return result;
	}

	public Map<String, Object> getIllustration212Result(int lamaBayar) {
		logger.info("*-*-*-* Cepr01040000Ilustration116Business.getIllustrationResult");
		IllustrationResultVO result = new IllustrationResultVO();
		IllustrationResultVO result2 = new IllustrationResultVO();
		Cepr01030102Form cepr01030102Form = ((Cepr01030000HoldingForm) command).getCepr01030102Form();
		Cepr01030101Form cepr01030101Form = ((Cepr01030000HoldingForm) command).getCepr01030101Form();
		Cepr01030104Form cepr01030104Form = ((Cepr01030000HoldingForm) command).getCepr01030104Form();

		ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		ArrayList<Map<String, Object>> mapList2 = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		Map<String, Object> mapMaster = new HashMap<String, Object>();
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
		double ld_max = 100000;
		double ldec_akuisisi;
		double ldec_premi_invest;
		double[][] ldec_hasil_invest = new double[5 + 1][3 + 1];
		double[] ldec_tarik = { CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0 };
		double ldec_wdraw, li_persen_wdraw;
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
		boolean[] lb_minus = { CommonConst.DUMMY_FALSE, false, false, false };
		boolean lb_rider = false;
		String ls_sy = "", ls_temp; // ls_dpp = ' dari Premi Pokok', ls_sy = ''
		S_biaya lstr;
		// n_riders ln_riders
		double ldec_manfaat, ldec_premi_setahun = 0, ldec_coi24 = 0;
		//// monthly fix cost
		ldec_mfc = 15000;

		// lds_fixed.DataObject = 'd_ilus_128_sum' + trim(string(istr_prop.bunga))
		// lds_temp2.DataObject = 'd_ilus_tarik128'

		Boolean lb_hal_tarik = false;

		if (istr_prop.bisnis_id == 120 && (istr_prop.bisnis_no >= 4 && istr_prop.bisnis_no <= 6)) {
			ldec_mfc = 25000;
		}
		if (istr_prop.kurs_id.equals("02")) {// dollar
			ldec_mfc = 2;
			if ("120, 127, 128, 129, 202".indexOf(LazyConverter.toString(istr_prop.bisnis_id)) > -1) {
				if (istr_prop.bisnis_no >= 4 && istr_prop.bisnis_no <= 6)
					ldec_mfc = 2.5;
				if (istr_prop.bisnis_no >= 7 && istr_prop.bisnis_no <= 15)
					ldec_mfc = 2;
			}
			li_bagi = 1;

		} else {
			ld_max = 1000 ^ 3;
		}

		//
		// if( istr_prop.bisnis_id == 115 || istr_prop.bisnis_id == 152 )
		// {
		// ldec_bak_tut = 0.01;
		// ldec_bak_tu = 0.01;
		// ldec_tarik = new double[] { CommonConst.DUMMY_ZERO, 0.05, 0.04, 0.03, 0.02,
		// 0.01 };
		// }
		// else if( istr_prop.bisnis_id == 138 )
		// {
		// ldec_bak_tut = 0;
		// ldec_bak_tu = 0;
		// }

		for (int i = 1; i <= 12; i++) {
			ldec_premi_bulan[i] = 0;
			if (i == 1)
				ldec_premi_bulan[i] = istr_prop.premi;// * (istr_prop.pct_premi / 100);
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

		for (int i = 1; i <= 3; i++) {
			ldec_bunga_avg[i] = 0;
			for (int j = 1; j <= 5; j++) {
				ldec_bunga_avg[i] += ldec_bunga[j][i] * (istr_prop.fund[j] / 100);
			}
			logger.info("-*ldec_bunga_avg" + ldec_bunga_avg[i]);
		}

		if (istr_prop.bisnis_id == 120) {// cerdas
			if (of_cek_rider() > 0) {
				li_hal = 4;
				// 814 waiver tpd
				if (istr_prop.rider_baru[5] > 0) {
					if ("01, 04, 07, 10, 13".indexOf(LazyConverter.toString(istr_prop.bisnis_no)) > -1) {
						istr_prop.rider_baru[5] = 4;
					} else {
						istr_prop.rider_baru[5] = 5;
					}
					// khusus excelent health 99
					if (istr_prop.bisnis_no == 15) {
						istr_prop.rider_baru[5] = 1;
					}
				}
				// 815 payor tpd
				if (istr_prop.rider_baru[6] > 0) {
					if ("01, 04, 07, 10, 13".indexOf(LazyConverter.toString(istr_prop.bisnis_no)) > -1) {
						istr_prop.rider_baru[6] = 4;
					} else {
						istr_prop.rider_baru[6] = 5;
					}
					// khusus excelent health 99
					if (istr_prop.bisnis_no == 15) {
						istr_prop.rider_baru[6] = 1;
					}
				}
				// 816 waiver ci
				if (istr_prop.rider_baru[7] > 0) {
					if ("01, 04, 07, 10, 13".indexOf(LazyConverter.toString(istr_prop.bisnis_no)) > -1) {
						istr_prop.rider_baru[7] = 2;
					} else {
						istr_prop.rider_baru[7] = 3;
					}
					// khusus excelent health 99
					if (istr_prop.bisnis_no == 15) {
						istr_prop.rider_baru[7] = 1;
					}
				}
				// 817 payor ci
				if (istr_prop.rider_baru[8] > 0) {
					if ("01, 04, 07, 10, 13".indexOf(LazyConverter.toString(istr_prop.bisnis_no)) > -1) {
						istr_prop.rider_baru[8] = 2;
					} else {
						istr_prop.rider_baru[8] = 3;
					}
					// khusus excelent health 99
					if (istr_prop.bisnis_no == 15) {
						istr_prop.rider_baru[8] = 1;
					}
				}
				// 818 term
				if (istr_prop.rider_baru[12] > 0)
					istr_prop.rider_baru[12] = 2;
			}
		} else if (istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 141) { // cerdas siswa
			if (istr_prop.bisnis_id == 127) {
				if ("01, 03, 07, 09".indexOf(LazyConverter.toString(istr_prop.bisnis_no)) > -1) {
					// cerdas siswa A/B 5
					istr_prop.rider_baru[1] = 1; // 810 pa
					istr_prop.rider_baru[6] = 4; // 815 payor tpd
					istr_prop.rider_baru[8] = 2; // 817 payor ci
				}
				if ("02, 04, 08, 10".indexOf(LazyConverter.toString(istr_prop.bisnis_no)) > -1) {
					// cerdas siswa A/B 10
					istr_prop.rider_baru[1] = 1; // 810 pa
					istr_prop.rider_baru[6] = 5; // 815 payor tpd
					istr_prop.rider_baru[8] = 3; // 817 payor ci
				}
			}
			//
			if ("03, 04, 09, 10".indexOf(LazyConverter.toString(istr_prop.bisnis_no)) > -1) {
				// cerdas siswa B 5/10
				istr_prop.rider_baru[4] = 1; // 813 ci
			}
			lb_hal_tarik = true;
			if (istr_prop.bisnis_id == 141 && of_cek_rider() > 0) {
				if (istr_prop.rider_baru[9] > 0) {
					istr_prop.rider_baru[9] = 2;
					if (istr_prop.bisnis_no == 2) {
						istr_prop.rider_baru[9] = 3;
					}
				} else if (istr_prop.rider_baru[6] > 0) {
					istr_prop.rider_baru[6] = 4;
					if (istr_prop.bisnis_no == 2) {
						istr_prop.rider_baru[6] = 5;
					}
				}
			}
		} else if (istr_prop.bisnis_id == 128) {
			if ("01, 03, 07, 09".indexOf(LazyConverter.toString(istr_prop.bisnis_no)) > -1) {
				// cerdas proteksi A/B 5
				istr_prop.rider_baru[1] = 2; // 810 pa
				istr_prop.rider_baru[3] = 2; // 812 TPD
				istr_prop.rider_baru[4] = 1; // 813 ci
				istr_prop.rider_baru[5] = 4; // 814 waiver tpd
				istr_prop.rider_baru[7] = 2; // 816 waiver ci
				istr_prop.rider_baru[12] = 1; // 818 term
			} else if ("02, 04, 08, 10".indexOf(LazyConverter.toString(istr_prop.bisnis_no)) > -1) {
				// cerdas proteksi A/B 10
				istr_prop.rider_baru[1] = 2; // 810 pa
				istr_prop.rider_baru[3] = 2; // 812 TPD
				istr_prop.rider_baru[4] = 1; // 813 ci
				istr_prop.rider_baru[5] = 5; // 814 waiver tpd
				istr_prop.rider_baru[7] = 3; // 816 waiver ci
				istr_prop.rider_baru[12] = 1; // 818 term
			}
			istr_prop.up_term = istr_prop.up;
			//
			if ("03, 04, 09, 10".indexOf(LazyConverter.toString(istr_prop.bisnis_no)) > -1) {
				// cerdas siswa B 5/10
				istr_prop.rider_baru[2] = 5; // 811 hcp r500
				if (istr_prop.kurs_id.equals("02"))
					istr_prop.rider_baru[2] = 15; // 811 hcp d50
			}
			lb_hal_tarik = true;
		} else if (istr_prop.bisnis_id == 129) {
			istr_prop.rider_baru[1] = 2; // 810 pa
			istr_prop.rider_baru[3] = 2; // 812 TPD
			if ("03, 04, 09, 10".indexOf(LazyConverter.toString(istr_prop.bisnis_no)) > -1) {
				// cerdas siswa B 5/10
				istr_prop.rider_baru[4] = 1; // 813 ci
				istr_prop.rider_baru[7] = 2; // 816 waiver ci
				if ("04, 10".indexOf(LazyConverter.toString(istr_prop.bisnis_no)) > -1) {
					istr_prop.rider_baru[7] = 3; // 816 waiver ci
				}
			}
			lb_hal_tarik = true;
		}

		double[] np = new double[4];
		double[] celaka = new double[4];
		ldec_premi_invest = 0;
		int j;
		for (int i = 1; i <= istr_prop.ins_per; i++) {
			ldec_sc = 0;
			ldec_wdraw = 0;
			ldec_topup = 0;
			ldec_akuisisi = 0;
			if (i <= ArrUtil.upperBound(lstr.tarik))
				ldec_wdraw = lstr.tarik[i];
			if (i <= ArrUtil.upperBound(lstr.topup))
				ldec_topup = lstr.topup[i];
			if (ArrUtil.upperBound(ldec_bak) > i)
				ldec_akuisisi = ldec_bak[i];
			ldec_coi = of_get_coi_120(i);
			ldec_cost = (ldec_coi + ldec_mfc);
			li_persen_wdraw = ldec_wdraw;
			//
			j = istr_prop.umur_tt + i;
			for (int k = 1; k <= 3; k++) {
				for (int li_bulan = 1; li_bulan <= 12; li_bulan++) {
					ldec_premi_invest = 0;
					if (i <= lamaBayar) {
						ldec_premi_invest = FormatNumber.round(
								((ldec_premi_bulan[li_bulan] * (istr_prop.pct_premi / 100)) * (1 - ldec_akuisisi)), 2);
						ldec_premi_invest = FormatNumber.round(
								ldec_premi_invest + ((ldec_premi_bulan[li_bulan] * (100 - istr_prop.pct_premi) / 100))
										* (1 - ldec_bak_tu),
								2); // topup berkala
					}
					if (li_bulan == 1)
						ldec_premi_invest = FormatNumber.round(ldec_premi_invest + (ldec_topup * (1 - ldec_bak_tut)),
								2); // topup tunggal
					ldec_hasil_invest[1][k] = FormatNumber
							.round((ldec_premi_invest + ldec_hasil_invest[1][k] - ldec_cost)
									* FormatNumber.round(Math.pow((1 + ldec_bunga_avg[k]), ((double) 1 / 12)), 15), 2);
				}
				ldec_tarik[k] = 0;
				if (lb_hal_tarik) {
					if (istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 141) {
						ldec_tarik[k] = FormatNumber.round(ldec_hasil_invest[1][k] * li_persen_wdraw / 100, 2);
						ldec_wdraw = Math.max(0, ldec_tarik[k]);
					} else if (istr_prop.bisnis_id == 128) {
						// proteksi
						if (j < 70) {
							if (i == 20) {
								ldec_tarik[k] = FormatNumber.round(ldec_hasil_invest[1][k] * 0.2, 2);
							} else if (i > 20 && (i % 5) == 0) {
								ldec_tarik[k] = FormatNumber.round(ldec_hasil_invest[1][k] * 0.05, 2);
							}
						} else if (j == 70) {
						}
						ldec_wdraw = Math.max(0, ldec_tarik[k]);
					} else if (istr_prop.bisnis_id == 129) {
						// sejahtera
						// usia 55: 50%, usia 65: 100%
						if (j == 55) {
							ldec_tarik[k] = FormatNumber.round(ldec_hasil_invest[1][k] * 0.5, 2);
						} else if (j == 65) {
						}
						ldec_wdraw = Math.max(0, ldec_tarik[k]);
					}
				}
				ldec_wdraw = FormatNumber.round(ldec_wdraw, 2);
				BigDecimal ldec_wdrawbd = new BigDecimal(ldec_wdraw);

				ldec_hasil_invest[1][k] = FormatNumber
						.round(ldec_hasil_invest[1][k] - (new Double(ldec_wdraw) * (1 + ldec_sc)), 2);
				if (ldec_hasil_invest[1][k] <= 0) {
					lb_minus[k] = true;
				}
			}

			if (i <= 23 || j == 55 || j == 65 || j == 70 || j == 75 || j == 80 || j == 100) {
				for (int k = 1; k <= 3; k++) {
					if (j == 100) {
						np[k] = FormatNumber.round((ldec_hasil_invest[1][k] + ldec_manfaat) / li_bagi, 0);
					} else {
						np[k] = FormatNumber.round(ldec_hasil_invest[1][k] / li_bagi, 0);
					}
					celaka[k] = FormatNumber.round((ldec_hasil_invest[1][k] + ldec_manfaat) / li_bagi, 0);
				}

				if (i <= lamaBayar) {
					// premiumTotal = editorUtil.convertToStringWithoutCent( ldec_premi_setahun /
					// li_bagi );
					premiumTotal = FormatNumber.round((ldec_premi_setahun / li_bagi), 0) + "";
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

				String benefitLow = convertToStringWithoutCentAndSetNill(editorUtil, celaka[1], np[1]);
				String benefitMid = convertToStringWithoutCentAndSetNill(editorUtil, celaka[2], np[2]);
				String benefitHi = convertToStringWithoutCentAndSetNill(editorUtil, celaka[3], np[3]);

				if ("nil".equals(valueLow)) {
					valueLow = "**";
				}
				if ("nil".equals(valueMid)) {
					valueMid = "**";
				}
				if ("nil".equals(valueHi)) {
					valueHi = "**";
				}
				if ("nil".equals(benefitLow)) {
					benefitLow = "**";
				}
				if ("nil".equals(benefitMid)) {
					benefitMid = "**";
				}
				if ("nil".equals(benefitHi)) {
					benefitHi = "**";
				}

				map = new HashMap<String, Object>();
				map.put("yearNo", editorUtil.convertToString(i));
				map.put("insuredAge", editorUtil.convertToString(cepr01030101Form.getInsuredAge() + i));
				map.put("premiumTotal", editorUtil.convertToStringWithoutCentAndNillIfNegative(premiumTotal));
				map.put("topupAssumption", topup);
				map.put("valueLow", valueLow);
				map.put("valueMid", valueMid);
				map.put("valueHi", valueHi);
				map.put("benefitLow", benefitLow);
				map.put("benefitMid", benefitMid);
				map.put("benefitHi", benefitHi);
				mapList.add(map);

			}
			if (i <= 25 || (j % 5) == 0) {
				Map<String, Object> map2 = new HashMap<String, Object>();
				String drawAssumptionLow = editorUtil.convertToRoundedNoDigit(new BigDecimal(ldec_tarik[1] / li_bagi));
				String drawAssumptionMid = editorUtil.convertToRoundedNoDigit(new BigDecimal(ldec_tarik[2] / li_bagi));
				String drawAssumptionHi = editorUtil.convertToRoundedNoDigit(new BigDecimal(ldec_tarik[3] / li_bagi));

				map2.put("yearNo", editorUtil.convertToString(i));
				map2.put("insuredAge", editorUtil.convertToString(cepr01030101Form.getInsuredAge() + i));
				map2.put("drawAssumptionLow", drawAssumptionLow);
				map2.put("drawAssumptionMid", drawAssumptionMid);
				map2.put("drawAssumptionHi", drawAssumptionHi);
				mapList2.add(map2);
			}
		}

		result.setValidityMsg(lb_minus[1] ? "Maaf, Proposal yang Anda buat Tidak Layak Jual." : "");
		result.setIllustrationList(mapList);
		result2.setIllustrationList(mapList2);
		mapMaster.put("Illustration1", result);
		mapMaster.put("Illustration2", result2);
		return mapMaster;

	}

}