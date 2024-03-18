package id.co.sinarmaslife.eproposal.product.product01040211;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040211Business
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
import id.co.sinarmaslife.eproposal.model.pb.N_riders;
import id.co.sinarmaslife.eproposal.model.pb.S_biaya;
import id.co.sinarmaslife.eproposal.model.vo.Cepr00000000YieldResultVO;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030104Form;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.MappingUtil;

public class Cepr01040211Business extends Cepr01040000Ilustration116Business {
	protected final Log logger = LogFactory.getLog(getClass());

	public Cepr01040211Business(EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command) {
		super(eproposalManager, editorUtil, command);
		setSekaligusRightPartCdArr(new Integer[] { Cepr01040211Mapper.X9, Cepr01040211Mapper.X24,
				Cepr01040211Mapper.X21, Cepr01040211Mapper.X12 });
		setBerkalaRightPartCdArr(new Integer[] { Cepr01040211Mapper.X7, Cepr01040211Mapper.X8, Cepr01040211Mapper.X22,
				Cepr01040211Mapper.X23, Cepr01040211Mapper.X13, Cepr01040211Mapper.X14, Cepr01040211Mapper.X15,
				Cepr01040211Mapper.X19, Cepr01040211Mapper.X20, Cepr01040211Mapper.X10, Cepr01040211Mapper.X11 });

		setInsuredAgeLimit(100);

		String productLabel = "";
		String productLabelFixedFund = "EXCELLINK", productLabelDynamicFund = "EXCELLINK",
				productLabelAggresiveFund = "EXCELLINK";

		if (cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X19
				|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X20
				|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X21) {
			productLabel = "Simas";

			setInvestmentTitleArr(new String[] { "", productLabel + " Fixed Income Fund",
					productLabel + " Dynamic Fund", productLabel + " Aggressive Fund", "EXCELLINK Cash Fund",  "EXCELLINK Equity Bakti Peduli",
					productLabel + " Secure Dollar Income", productLabel + " Dynamic Dollar Fund" });
		} else if (cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X22
				|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X23
				|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X24) {

			productLabel = "EXCELLINK";
//  //IGA - PROJECT SIMPOL
//			if (cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo(new BigDecimal("0")) > 0
//					&& CommonConst.DISPLAY_TRUE.equals(cepr01030102Form.getLjiFixListDisplay())
//					&& cepr01030102Form.getLjiFixCd().equals("01")) {
//				productLabelFixedFund = "EXCELLINK";
//			} else if (cepr01030102Form.getFixIdr() != null
//					&& cepr01030102Form.getFixIdr().compareTo(new BigDecimal("0")) > 0
//					&& CommonConst.DISPLAY_TRUE.equals(cepr01030102Form.getLjiFixListDisplay())
//					&& cepr01030102Form.getLjiFixCd().equals("35")) {
//				productLabelFixedFund = "Simas";
//			}
//
//			if (cepr01030102Form.getDynamicIdr() != null
//					&& cepr01030102Form.getDynamicIdr().compareTo(new BigDecimal("0")) > 0
//					&& CommonConst.DISPLAY_TRUE.equals(cepr01030102Form.getLjiDynamicListDisplay())
//					&& cepr01030102Form.getLjiDynamicCd().equals("02")) {
//				productLabelDynamicFund = "EXCELLINK";
//			} else if (cepr01030102Form.getDynamicIdr() != null
//					&& cepr01030102Form.getDynamicIdr().compareTo(new BigDecimal("0")) > 0
//					&& CommonConst.DISPLAY_TRUE.equals(cepr01030102Form.getLjiDynamicListDisplay())
//					&& cepr01030102Form.getLjiDynamicCd().equals("36")) {
//				productLabelDynamicFund = "Simas";
//			}
//			if (cepr01030102Form.getAggresiveIdr() != null
//					&& cepr01030102Form.getAggresiveIdr().compareTo(new BigDecimal("0")) > 0
//					&& CommonConst.DISPLAY_TRUE.equals(cepr01030102Form.getLjiAggresiveListDisplay())
//					&& cepr01030102Form.getLjiAggresiveCd().equals("03")) {
//				productLabelAggresiveFund = "EXCELLINK";
//			} else if (cepr01030102Form.getAggresiveIdr() != null
//					&& cepr01030102Form.getAggresiveIdr().compareTo(new BigDecimal("0")) > 0
//					&& CommonConst.DISPLAY_TRUE.equals(cepr01030102Form.getLjiAggresiveListDisplay())
//					&& cepr01030102Form.getLjiAggresiveCd().equals("37")) {
//				productLabelAggresiveFund = "Simas";
//			} //DONE
			/*
			 * setInvestmentTitleArr( new String[] { "", productLabelFixedFund +
			 * " Fixed Income Fund", productLabelDynamicFund + " Dynamic Fund",
			 * productLabelAggresiveFund + " Aggressive Fund", productLabel +
			 * " Secure Dollar Income", productLabel + " Dynamic Dollar Fund" } );
			 */

//			setInvestmentTitleArr(new String[] { "", " EXCELLINK Fixed Income Fund", " EXCELLINK Dynamic Fund", //IGA - PROJECT SIMPOL
//					" EXCELLINK Aggressive Fund", " Simas Fixed Income Fund", " Simas Dynamic Fund",
//					" Simas Aggressive Fund", " EXCELLINK Secure Dollar Income", " EXCELLINK Dynamic Dollar Fund" }); //DONE

			setInvestmentTitleArr( new String[] { //IGA - PROJECT SIMPOL
	 	            "",
	 	            "EXCELLINK Fixed Income Fund",
	 	            "EXCELLINK Dynamic Fund",
	 	            "EXCELLINK Aggressive Fund",
	 	            "Simas Fixed Income Fund",
	                "Simas Dynamic Fund",
	                "Simas Aggressive Fund",
	 	            "EXCELLINK Cash Fund",
	 	            "EXCELLINK Equity Bakti Peduli",
	 	            "EXCELLINK Secure Dollar Income Fund",
	 	            "EXCELLINK Dynamic Dollar Fund"
	 	        } );
			//DONE
		} else {
			productLabel = "EXCELLINK";

			setInvestmentTitleArr(new String[] { "", productLabel + " Fixed Income Fund",
					productLabel + " Dynamic Fund", productLabel + " Aggressive Fund",
					productLabel + " Secure Dollar Income", productLabel + " Dynamic Dollar Fund" });
		}

		String investmentDescrFixedFund = "", investmentDescrDynamicFund = "", investmentDescrAggresiveFund = "";

		if (cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X19
				|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X20
				|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X21) {
			setInvestmentTitleAndDescrArr(new String[][] { { "", "", "" }, { "", "- " + productLabel
					+ " Fixed Income : Penempatan minimum 80% pada Fixed Income dan maksimum 20% pada Pasar Uang", "" },
					{ "", "- " + productLabel
							+ " Dynamic Fund : Penempatan maksimum 79% pada masing-masing instrument Pasar Uang, Equity serta Investasi berpendapatan tetap",
							"" },
					{ "", "- " + productLabel
							+ " Aggressive Fund : Penempatan minimum 80% pada Equity dan maksimum 20% pada Pasar Uang",
							"" },
					{ "", "- " + "EXCELLINK Cash Fund : Penempatan dana investasi 100% pada instrumen investasi pasar uang",
							"" },		
					{ "", "- " + "EXCELLINK Equity Bakti Peduli : Penempatan minimum 80% (delapan puluh perseratus) dana investasi instrumen-instrumen investasi pasar "+
								" modal (ekuitas) dan maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang",
									"" },		
					{ "", "- " + productLabel + " Secure Dollar Income Fund : 100% Fixed Income.", null }, { "",
							"- " + productLabel
									+ " Dynamic Dollar Fund : minimum 50% Fixed Income dan maksimum 50% Ekuitas.",
							null }, });
		} else if (cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X22
				|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X23
				|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X24) {

//			if (cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo(new BigDecimal("0")) > 0 //IGA - PROJECT SIMPOL
//					&& CommonConst.DISPLAY_TRUE.equals(cepr01030102Form.getLjiFixListDisplay())
//					&& cepr01030102Form.getLjiFixCd().equals("01")) {
//				investmentDescrFixedFund = " Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang";
//			} else if (cepr01030102Form.getFixIdr() != null
//					&& cepr01030102Form.getFixIdr().compareTo(new BigDecimal("0")) > 0
//					&& CommonConst.DISPLAY_TRUE.equals(cepr01030102Form.getLjiFixListDisplay())
//					&& cepr01030102Form.getLjiFixCd().equals("35")) {
//				investmentDescrFixedFund = " Penempatan minimum 80% pada Fixed Income dan maksimum 20% pada Pasar Uang ";
//			}
//			if (cepr01030102Form.getDynamicIdr() != null
//					&& cepr01030102Form.getDynamicIdr().compareTo(new BigDecimal("0")) > 0
//					&& CommonConst.DISPLAY_TRUE.equals(cepr01030102Form.getLjiDynamicListDisplay())
//					&& cepr01030102Form.getLjiDynamicCd().equals("02")) {
//				investmentDescrDynamicFund = " Penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas).";
//			} else if (cepr01030102Form.getDynamicIdr() != null
//					&& cepr01030102Form.getDynamicIdr().compareTo(new BigDecimal("0")) > 0
//					&& CommonConst.DISPLAY_TRUE.equals(cepr01030102Form.getLjiDynamicListDisplay())
//					&& cepr01030102Form.getLjiDynamicCd().equals("36")) {
//				investmentDescrDynamicFund = " Penempatan maksimum 79% pada masing-masing instrument Pasar Uang, Equity serta Investasi berpendapatan tetap.";
//			}
//			if (cepr01030102Form.getAggresiveIdr() != null
//					&& cepr01030102Form.getAggresiveIdr().compareTo(new BigDecimal("0")) > 0
//					&& CommonConst.DISPLAY_TRUE.equals(cepr01030102Form.getLjiAggresiveListDisplay())
//					&& cepr01030102Form.getLjiAggresiveCd().equals("03")) {
//				investmentDescrAggresiveFund = " Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dan/atau maksimum penempatan 20% (dua puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar uang.";
//			} else if (cepr01030102Form.getAggresiveIdr() != null
//					&& cepr01030102Form.getAggresiveIdr().compareTo(new BigDecimal("0")) > 0
//					&& CommonConst.DISPLAY_TRUE.equals(cepr01030102Form.getLjiAggresiveListDisplay())
//					&& cepr01030102Form.getLjiAggresiveCd().equals("37")) {
//				investmentDescrAggresiveFund = " Penempatan minimum 80% pada Equity dan maksimum 20% pada Pasar Uang.";
//			}
//
//			setInvestmentTitleAndDescrArr(new String[][] { { "", "", "" },
//					{ "", "- " + productLabelFixedFund + " Fixed Income Fund :" + investmentDescrFixedFund, "" },
//					{ "", "- " + productLabelDynamicFund + " Dynamic Fund :" + investmentDescrDynamicFund, "" },
//					{ "", "- " + productLabelAggresiveFund + " Aggressive Fund :" + investmentDescrAggresiveFund, "" },
//					{ "", "- " + productLabel + " Secure Dollar Income Fund :"
//							+ "penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang",
//							null },
//					{ "", "- " + productLabel
//							+ " Dynamic Dollar Fund : penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas), dimana semua instrumen-instrumen investasi tersebut dalam mata uang US Dollar",
//							null }, });
			setInvestmentTitleAndDescrArr( new String[][] {
	 	            { "", "", "" },
	 	            { "", "- " +  "EXCELLINK Fixed Income Fund : "+ " Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang" },
	 	            { "", "- " +  "Simas Fixed Income Fund :  "+ "Merupakan penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang" },
	 	            { "", "- " +  "EXCELLINK Dynamic Fund : "+ "Penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas)" },
	 	            { "", "- " +  "Simas Dynamic Fund : "+ "Merupakan penempatan maksimum 79% (tujuh puluh sembilan perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan pasar modal (ekuitas)" },
	 	           	{ "", "- " +  "EXCELLINK Aggressive Fund : "+ "Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dan/atau maksimum penempatan 20% (dua puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar uang" },
	 	           	{ "", "- " +  "Simas Aggressive Fund : "+ "Merupakan penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dan maksimum 20% (dua puluh perseratus)  instrumen-instrumen investasi pasar uang"},
	 	           	{ "", "- " +  "EXCELLINK Cash Fund : "+ "Penempatan dana investasi 100% pada instrumen investasi pasar uang" },
		            { "", "- " +  "Excellink Equity Bakti Peduli : "+ " Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi pasar modal (ekuitas) dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang" },
		            { "", "- " +  "EXCELLINK Secure Dollar Income Fund : "+ "Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang dalam mata uang US Dollar" },
	 	            { "", "- " +  "EXCELLINK Dynamic Dollar Fund : "+ "Penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas), dimana semua instrumen-instrumen investasi tersebut dalam mata uang US Dollar" },
	 	        } );
			//DONE
		} else {
			setInvestmentTitleAndDescrArr(new String[][] { { "", "", "" },
					{ "", "- " + productLabel + " Fixed Income Fund : 100% Fixed Income",
							"  Penempatan dana pada investasi pendapatan tetap dan instrumen pasar uang." },
					{ "", "- " + productLabel + " Dynamic Fund : minimum 50% Fixed Income dan maksimum 50% Ekuitas",
							"  Penempatan dana pada investasi pendapatan tetap, ekuitas serta instrumen pasar uang." },
					{ "", "- " + productLabel
							+ " Aggressive Fund : maksimum 100% Fixed Income Rupiah atau maksimum 50% Fixed",
							"  Income US Dollar atau maksimum 100% Ekuitas" },
					{ "", "- " + productLabel + " Secure Dollar Income Fund : 100% Fixed Income.", null }, { "",
							"- " + productLabel
									+ " Dynamic Dollar Fund : minimum 50% Fixed Income dan maksimum 50% Ekuitas.",
							null }, });

		}

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
		if (currentIdx != 0) {
			params = new HashMap<String, Object>();
			params.put("pageCode", "sub3");
			result.add(params);

			params = new HashMap<String, Object>();
			params.put("pageCode", "sub33");
			result.add(params);

		}

		params = new HashMap<String, Object>();
		params.put("pageCode", "sub4");
		result.add(params);
		
		params = new HashMap<String, Object>(); 
		params.put( "pageCode", "sub5" );
		result.add( params );
		
		params = new HashMap<String, Object>();
		params.put("pageCode", "sub6");
		result.add(params);

		return result;
	}

	public String[][] getRiderPremiumList(Cepr01030102Form cepr01030102Form) throws IOException {
		double ldec_coi;

		String[][] riderTambahan = new String[12][7];
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

		N_riders ln_riders = new N_riders();
		int j = 1;
		List<Mst_proposal> mstProposal = new ArrayList<Mst_proposal>();
		for (int i = 1; i <= ArrUtil.upperBound(istr_prop.rider_baru); i++) {
			if (istr_prop.rider_baru[i] > 0) {

				ldec_coi = of_get_coi_120_cost(i, 1, mstProposal);
				totalRider = totalRider.add(new BigDecimal(ldec_coi));
				riderTambahan[j][0] = ln_riders.of_nama(i, cepr01030102Form, cepr01030103Form);
				riderTambahan[j][1] = editorUtil.convertToRoundedTwoDigits(ldec_coi);
				riderTambahan[j][6] = ldec_coi + "";
				j = j + 1;
			}
		}
		riderTambahan[0][1] = totalRider.toString();

		return riderTambahan;
	}

	public List<Map<String, Object>> getCommonHeaderRowList() throws IOException {
		logger.info("*-*-*-* Cepr01040211Business.getCommonHeaderRowList");

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
		result.addAll( source.getGenderInsuredMap() ); // IGA PROJECT NEW COI
		// result.addAll( source.getTermOfContractWithLimitMap( insuredAgeLimit ) );

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
				params.put("content", "------------------------------");
				result.add(params);

				params = new HashMap<String, Object>();
				params.put("label", "");
				params.put("content", "");
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
		result.addAll(source.getPolicyHolderMedicalCheckUpMap((String) of_cek_medis(command).get("medis_pp")));
		result.addAll(source.getInsuredMedicalCheckUpMap((String) of_cek_medis(command).get("medis_tt")));

		return result;
	}

	public List<Map<String, Object>> getCommonHeaderRincianRowList() throws IOException {
		logger.info("*-*-*-* Cepr01040211Business.getCommonHeaderRowList");

		List sekaligusRightPartCdList = ArrUtil.toListFromArray(sekaligusRightPartCdArr);
		List berkalaRightPartCdList = ArrUtil.toListFromArray(berkalaRightPartCdArr);

		Cepr01030000HoldingForm cepr01030000HoldingForm = (Cepr01030000HoldingForm) command;
		Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		CommonHeaderDataSource source = new CommonHeaderDataSource(command, editorUtil, eproposalManager);

		// Fill header content from CommonHeaderDataSource
		result.addAll(source.getPolicyHolderNameMap());
		result.addAll(source.getPolicyHolderNameMap());
		result.addAll(source.getPolicyHolderAgeMap());
		result.addAll(source.getInsuredNameMap());
		result.addAll(source.getInsuredAgeMap());

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
				params.put("content", "------------------------------");
				result.add(params);

				params = new HashMap<String, Object>();
				params.put("label", "");
				params.put("content", "");
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

	public List<Map<String, Object>> getCommonBiayaRincianRider(String[][] riderTambahan, int rider_baru)
			throws IOException {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		N_riders ln_riders = new N_riders();
		// String namaRiders = null;
		String namaRiders = ln_riders.of_nama(rider_baru, cepr01030102Form, cepr01030103Form);

		boolean process = false;
		for (int i = 1; i <= ArrUtil.upperBound(istr_prop.rider_baru); i++) {
			if (istr_prop.rider_baru[i] > 0 && i == rider_baru) {
				process = true;
			}
		}
		/*
		 * if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTpdFlag() ) &&
		 * rider_baru==3 ) { namaRiders = ln_riders.of_nama( rider_baru,
		 * cepr01030102Form, cepr01030103Form ); //namaRiders =
		 * "SMiLe Total Permanent Disability (TPD)"; } else if(
		 * CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFamilyFlag() ) &&
		 * rider_baru==11 ) { // namaRiders = "SMiLe Hospital Protection (Family)";
		 * namaRiders = ln_riders.of_nama( rider_baru, cepr01030102Form,
		 * cepr01030103Form ); } else if( CommonConst.CHECKED_TRUE.equals(
		 * cepr01030103Form.getTermRiderFlag() ) && rider_baru==12 ) { // namaRiders =
		 * "SMiLe Term Insurance"; namaRiders = ln_riders.of_nama( rider_baru,
		 * cepr01030102Form, cepr01030103Form ); } else if(
		 * CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getCiFlag() ) &&
		 * rider_baru==4) { // namaRiders = "SMiLe Term Insurance"; namaRiders =
		 * ln_riders.of_nama( rider_baru, cepr01030102Form, cepr01030103Form ); }
		 */

		if (namaRiders != null && process) {
			int posisiRider = 0;
			int lenRider = riderTambahan.length - 1;
			for (int i = 1; i < lenRider; i++) {
				if (riderTambahan[i][0] != null) {
					String namaRider = riderTambahan[i][0];
					if (namaRider.equalsIgnoreCase(namaRiders)) {
						posisiRider = i;
					}
				}
			}

			// Fill content
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("label", "-	Biaya Asuransi");
			params.put("content", editorUtil.convertToString("Rp." + riderTambahan[posisiRider][1] + ",- per bulan*"));
			params.put("separator", ":");
			result.add(params);
		}
		return result;
	}

	public String getPageRider(int currentIdx) throws IOException {
		String result = null;

		if (currentIdx != 0) {
			result = "sub3";
		}
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

	public Map<String, Object> getIllustration211Result(int lamaBayar) {
		logger.info("*-*-*-* Cepr01040000Ilustration116Business.getIllustrationResult");
		IllustrationResultVO result = new IllustrationResultVO();
		IllustrationResultVO result2 = new IllustrationResultVO();
		Cepr01030102Form cepr01030102Form = ((Cepr01030000HoldingForm) command).getCepr01030102Form();
		Cepr01030101Form cepr01030101Form = ((Cepr01030000HoldingForm) command).getCepr01030101Form();
		Cepr01030104Form cepr01030104Form = ((Cepr01030000HoldingForm) command).getCepr01030104Form();
		CredentialsVO credentialsVO = ((Cepr01030000HoldingForm) command).getCredentialsVO();

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
		double ldec_akuisisi;
		double ldec_premi_invest;
		double[][] ldec_hasil_invest = new double[5 + 1][3 + 1];
		double[] ldec_tarik = { CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0 };
		double ldec_wdraw, li_persen_wdraw;
		double[] ldec_premi_bulan = new double[12 + 1];
		double ldec_topup;
		double ldec_bass;
		double ld_max = 100000;
		double[][] ldec_bunga = new double[5 + 1][3 + 1];
		double[] ldec_bunga_avg = new double[3 + 1]; // = {0.06, 0.1, 0.08, 0.18, 0.12, 0.25} //{0.09, 0.06, 0.11, 0.15,
														// 0.165, 0.25} //fixed:0.09, 0.11, 0.15 (0.165); dynamic:0.08,
														// 0.11, 0.18 (0.195)
		double ldec_fee = 0.020075;
		double ldec_coi = 0, ldec_mfc, ldec_sc, ldec_cost, ldec_man_celaka, ldec_temp = 0; // , ldec_man[10+1] = {1, 1,
																							// 1, 1.1, 1.2, 1.3, 1.4,
																							// 1.5, 1.6, 1.7}
		boolean[] lb_minus = { CommonConst.DUMMY_FALSE, false, false, false };
		double[] lb_minus_detail = new double[3 + 1];
		boolean lb_rider = false;
		String ls_sy = "", ls_temp; // ls_dpp = ' dari Premi Pokok', ls_sy = ''
		S_biaya lstr;
		// n_riders ln_riders
		double ldec_manfaat, ldec_premi_setahun = 0, ldec_coi24 = 0;
		//// monthly fix cost
		ldec_mfc = 15000;
              	
		// lds_fixed.DataObject = 'd_ilus_128_sum' + trim(string(istr_prop.bunga))
		// lds_temp2.DataObject = 'd_ilus_tarik128'

		Boolean lb_hal_tarik = true;
		if (istr_prop.bisnis_id == 141 || of_cek_rider() <= 0) {
			li_hal = 4;
		}
		ldec_coi = of_get_coi_120_in_header( 1, 1 );
//		if istr_prop.bisnis_id = 120 and (istr_prop.bisnis_no >= 4 and istr_prop.bisnis_no <= 6) then ldec_mfc = 25000
		if(istr_prop.bisnis_id == 120 && (istr_prop.bisnis_no >= 4 && istr_prop.bisnis_no <= 6) ) ldec_mfc = 25000;
		
        if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            ldec_mfc = 2;
//        	If Pos('120, 127, 128, 129, 202', string(istr_prop.bisnis_id, '00#')) > 0 then
            if(istr_prop.bisnis_id == 120 || istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 128 || istr_prop.bisnis_id == 129 || istr_prop.bisnis_id == 202 ){
	            if(istr_prop.bisnis_no >= 4 && istr_prop.bisnis_no <= 6) ldec_mfc = 2.5;	        
	            if (istr_prop.bisnis_no >= 7 && istr_prop.bisnis_no <= 24)  ldec_mfc = 2;
            }
            li_bagi = 1;
        }else{
        	ld_max = 1000000000;
        }
/*
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 141 )
        {
            ldec_mfc = 45000;
            if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) ) ldec_mfc = 5;
        }*/

//        if( istr_prop.bisnis_id == 115 || istr_prop.bisnis_id == 152 )
//        {
//            ldec_bak_tut = 0.01;
//            ldec_bak_tu = 0.01;
//	        ldec_tarik = new double[] { CommonConst.DUMMY_ZERO, 0.05, 0.04, 0.03, 0.02, 0.01 };
//        }
//        else if( istr_prop.bisnis_id == 138 )
//        {
//            ldec_bak_tut = 0;
//	        ldec_bak_tu = 0;
//        }

        for( int i = 1; i <= 12; i++ )
        {
            ldec_premi_bulan[ i ] = 0;
            if( i == 1 ) ldec_premi_bulan[ i ] = istr_prop.premi;//* (istr_prop.pct_premi / 100);
            if( Hardcode.PAY_MODE_CD_TRIWULANAN == cepr01030102Form.getPaymentModeCd() )
            {
                if( i == 4 || i == 7 || i == 10 ) ldec_premi_bulan[ i ] = istr_prop.premi;
            }
            else if( Hardcode.PAY_MODE_CD_SEMESTERAN == cepr01030102Form.getPaymentModeCd() )
            {
                if( i == 7 ) ldec_premi_bulan[ i ] = istr_prop.premi;
            }
            else if( Hardcode.PAY_MODE_CD_BULANAN == cepr01030102Form.getPaymentModeCd() )
            {
                ldec_premi_bulan[ i ] = istr_prop.premi;
            }
            ldec_premi_setahun += ldec_premi_bulan[ i ];
        }
        
        for( int i = 1; i <= 3; i++ )
        {
            ldec_hasil_invest[ 1 ][ i ] = 0;
        }

        ldec_manfaat = istr_prop.up;
////Biaya Akuisisi & asumsi penarikan (1 tarik, 1-nya tdk tarik)

        lstr = PbFunction.wf_get_biaya_84( istr_prop, command );
        ldec_bak = lstr.bak;
        ldec_bunga = lstr.bunga;

        int a, b;
        /*if( "01".equals( istr_prop.kurs_id ) )
        {
            a = 1;
            b = 3;
        }
        else
        {
            a = 4;
            b = 5;
        }*/

        for( int i = 1; i <= 3; i++ )
        {
            ldec_bunga_avg[ i ] = 0;
            if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	if(istr_prop.bisnis_id == 120 && ((istr_prop.bisnis_no >= 22 && istr_prop.bisnis_no <= 24))){ //IGA - PROJECT SIMPOL
            		 for( int j = 9; j <= 10; j++ )
                     {
                         ldec_bunga_avg[ i ] += ldec_bunga[ j ][ i ] * ( istr_prop.fund[ j ] / 100 );
                     }
            	}
            	else{
            		 for( int j = 6; j <= 7; j++ )
                     {
                         ldec_bunga_avg[ i ] += ldec_bunga[ j ][ i ] * ( istr_prop.fund[ j ] / 100 );
                     }
            	} 
            	
            	
            }else{
            	if(istr_prop.bisnis_id == 120 && ((istr_prop.bisnis_no >= 22 && istr_prop.bisnis_no <= 24))){
            		for( int j = 1; j <= 8; j++ )
                    {
                        ldec_bunga_avg[ i ] += ldec_bunga[ j ][ i ] * ( istr_prop.fund[ j ] / 100 );
                    }
            	}else{
            		for( int j = 1; j <= 5; j++ )
                    {
                        ldec_bunga_avg[ i ] += ldec_bunga[ j ][ i ] * ( istr_prop.fund[ j ] / 100 );
                    }
            	}
            	 //DONE
            	
            }
                       
            
            logger.info("-*ldec_bunga_avg" + ldec_bunga_avg[ i ]);
        }
       
        double[] np = new double[4];
        double[] np_nonUP = new double [4];
        double[] celaka = new double[4];
        double[] celaka_nonUP = new double[4];
        double[] npbatal = new double[4];
        
        ldec_premi_invest = 0;
        int j;
        for( int i = 1 ; i <= istr_prop.ins_per ; i++){
    	ldec_sc = 0;
    	ldec_wdraw = 0;
    	ldec_topup = 0;
    	ldec_akuisisi = 0;
    	if(i <= ArrUtil.upperBound(lstr.tarik))ldec_wdraw = lstr.tarik[i];
    	if(i <= ArrUtil.upperBound(lstr.topup))ldec_topup = lstr.topup[i];
    	if(ArrUtil.upperBound(ldec_bak) > i )ldec_akuisisi = ldec_bak[i];
    	ldec_coi = of_get_coi_120( i );    	    	
    	//ldec_coi = of_get_coi_120_120(i, lstr_rider);
    	/*if( cepr01030102Form.getLeftPartOfBusinessCd() == 141 )
        {
            if( i == 1 )
            {
                //ass basic dinolin, ass rider tidk 23/08/07
                ldec_temp = of_get_coi_120_basic( i );
                ldec_coi = ldec_coi - ldec_temp;
                //ldec_temp = ldec_temp / 2 //distahunkan /24
                ldec_mfc = 0;
                //ass rider pa, hcp, hcp fam ada (3/9/07)
                for( int k = 1; k <= ArrUtil.upperBound( istr_prop.rider_baru ); k++ )
                {
                	if( !( ( k == 1 ) || ( k == 2 ) || ( k == 11 ) ) )
                    {
                        if( istr_prop.rider_baru[ k ] > 0 )
                        {
                            ldec_coi -= of_get_coi_120_rider( k, 1 );
                            ldec_temp += of_get_coi_120_rider( k, 1 );
                        }
                    }
                    else
                    {
                        //pa, hcp, hcp fam tdk ditunda
                    }
                }
                ldec_temp = ldec_temp / 24;  //distahunkan /24
            }
            else if( i >= 3 && i <= 4 )
            {
                ldec_coi24 = ldec_temp;
            }
            else
            {
                ldec_coi24 = 0;
            }

            if( i >= 2 )
            {
                ldec_mfc = 45000; //30000  16/07/07
                if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) )
                {
                    ldec_mfc = 5;
                }
                //tambahin per 10/8/07
                if( istr_prop.cb_id == 0 )
                {
                    ldec_mfc = 30000;
                    if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) )
                    {
                        ldec_mfc = 4;
                    }
                }
            }
        }*/
    	ldec_cost = (ldec_coi + ldec_mfc);
    	li_persen_wdraw = ldec_wdraw;
    	//
    	j = istr_prop.umur_tt + i;
    		for( int k = 1 ; k <= 3; k++){
    			for(int li_bulan = 1 ; li_bulan <= 12 ; li_bulan++){
    				ldec_premi_invest = 0;
    				if(i <= lamaBayar ){
    					ldec_premi_invest = FormatNumber.round(( (ldec_premi_bulan[li_bulan] * (istr_prop.pct_premi / 100)) * ( 1 - ldec_akuisisi)),2);
    					ldec_premi_invest = FormatNumber.round( ldec_premi_invest + ((ldec_premi_bulan[li_bulan] * (100 - istr_prop.pct_premi) / 100)) * ( 1 - ldec_bak_tu),2);  //topup berkala
    				}
    					if(li_bulan == 1)ldec_premi_invest = FormatNumber.round(ldec_premi_invest + (ldec_topup * ( 1 - ldec_bak_tut)),2);  //topup tunggal
    					
    					
    					//ldec_hasil_invest[1][k] = FormatNumber.round(( ldec_premi_invest + ldec_hasil_invest[1][k] - ldec_cost) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2);
    					ldec_hasil_invest[1][k] = FormatNumber.round(( ldec_premi_invest + ldec_hasil_invest[1][k] - ldec_cost) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2);
//    					if( k == 1 ){
//    						if(ldec_hasil_invest[1][k] <=0){//plan 120 5/10 jika premi tahunan diatas 100jt dan nilai minus setelah 20tahun bisa di print..
//    							if( istr_prop.bisnis_id == 120 && 
//    									( istr_prop.bisnis_no == 1 || istr_prop.bisnis_no == 2 || istr_prop.bisnis_no == 4 || istr_prop.bisnis_no == 5 ||
//    									istr_prop.bisnis_no == 7 || istr_prop.bisnis_no == 8 || istr_prop.bisnis_no == 10 || istr_prop.bisnis_no == 11 || 
//    									istr_prop.bisnis_no == 13 || istr_prop.bisnis_no == 14) ){
//    								
//    							}
//    							}
//    					}
    			}
    			ldec_tarik[k] = 0;
    			if(lb_hal_tarik){
    				if(istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 141){
    					//ldec_tarik[k] = Round(ldec_hasil_invest[1][k] * li_persen_wdraw/100, 2);
    					ldec_tarik[k] = FormatNumber.round(ldec_hasil_invest[1][k] * li_persen_wdraw/100, 2);
    					//ldec_wdraw = Max(0, ldec_tarik[k]);
    					ldec_wdraw = Math.max(0, ldec_tarik[k]);
    				}else if( istr_prop.bisnis_id == 128){
    					//proteksi
    					//th 20: 20%, tiap 5th: 5%, usia 70: 100%
    					if(j < 70){
    						if(i == 20){
    							//ldec_tarik[k] = Round(ldec_hasil_invest[1][k] * 0.2, 2);
    							ldec_tarik[k] = FormatNumber.round(ldec_hasil_invest[1][k] * 0.2, 2);
    						}else if( i > 20 && (i%5) == 0){
    							//ldec_tarik[k] = Round(ldec_hasil_invest[1][k] * 0.05, 2);
    							ldec_tarik[k] = FormatNumber.round(ldec_hasil_invest[1][k] * 0.05, 2);
    						}
    					}else if( j == 70){
    						//ldec_tarik[k] = ldec_hasil_invest[1, k]
    					}
    					//ldec_wdraw = Max(0, ldec_tarik[k]);
    					ldec_wdraw = Math.max(0, ldec_tarik[k]);
    				}else if( istr_prop.bisnis_id == 129){
    					//sejahtera
    					//usia 55: 50%, usia 65: 100%
    					if( j == 55){
    						//ldec_tarik[k] = Round(ldec_hasil_invest[1][k] * 0.5, 2);
    						ldec_tarik[k] = FormatNumber.round(ldec_hasil_invest[1][k] * 0.5, 2);
    					}else if( j == 65){
    						//ldec_tarik[k] = ldec_hasil_invest[1, k]
    					}
    					//ldec_wdraw = Max(0, ldec_tarik[k]);
    					ldec_wdraw = Math.max(0, ldec_tarik[k]);
    				}
    			}
    			ldec_wdraw = FormatNumber.round(ldec_wdraw,2);
    			BigDecimal ldec_wdrawbd = new BigDecimal(ldec_wdraw);
    			
    			ldec_hasil_invest[1][k] = FormatNumber.round(ldec_hasil_invest[1][k] - (new Double(ldec_wdrawbd.toString()) * (1 + ldec_sc)), 2);    			
    	
    			if(istr_prop.bisnis_id == 120 && ((istr_prop.bisnis_no >= 22 && istr_prop.bisnis_no <= 24) || (istr_prop.bisnis_no >= 10 && istr_prop.bisnis_no <= 12))){    		
    				if( credentialsVO.getGroupId().equals( Hardcode.GROUP_DMTM ) || credentialsVO.getGroupId().equals( Hardcode.GROUP_BANCASS ) || credentialsVO.getGroupId().equals( Hardcode.GROUP_BANK_SINARMAS ) || credentialsVO.getMsagId().equals("LUI2216")){	
    					if( ldec_hasil_invest[ 1 ][ k ] <= 0 &&  ( i <= 10 )  )
    	                {
    	                    lb_minus[ k ] = true;
    	                }
    				}else{
    					//REQ: DESVINNA 06/10/2014 
    	    		    //SIMPOL Layak Jual, jika: 1.Perhitungan Nill (**) terjadi pd usia  TTG pada Ilustrasi Perkembangan Dana /usia Tertanggung pd tahun Polis, usia  > 70 thn
    	    		   	//                  2.Utk usia masuk TTG (usia statis) > 60 THN, LAYAK JUAL DIIJINKAN JIKA Ternyata ADA NILAI YG NIL(**) PD ILUSTASI PERKEMBANGAN DANA DIATAS PERIODE/THN POLIS KE 10	
    	    			/*
    	    				if(j <= 70 || istr_prop.umur_tt>=60 ){
    	    					if( ldec_hasil_invest[ 1 ][ k ] <= 0 )
    	                        { 
    	       	    				double ldec_maks_premi_tahunan = 100000000.0;
    	       	    				if( istr_prop.kurs_id.equals("02") )  ldec_maks_premi_tahunan = 10000.0;
    	       	    				 
    	       	    				if( istr_prop.umur_tt>=60 ){
    	       	    					if(	i <= 10 ){
    	       	    						lb_minus[ k ] = true;
    	       	    					}
    	       	    				}else if( i <= 20 ){
    	       	    					 lb_minus[ k ] = true;
    	       	    				}else if( i > 20 && ldec_premi_setahun <= ldec_maks_premi_tahunan ){
    	       	    					 lb_minus[ k ] = true;
    	       	    				}
    	                        }
    	    				}*/  				
    	    				//REQ: VITO 23/04/2015 : SE No.013/AJS-SK/IV/2015
    	        		    //SIMPOL & VIP Family Plan,  Layak Jual, jika: 
    	    				//1. Terdapat **/nil bukan pada tahun pertama di asumsi rendah, tapi tidak ada sama sekali **/nil di asumsi Tinggi 
    	    				//   (terdapat **/nil selain tahun ke-1 di asumsi rendah tapi tidak ada **/nil sampai usia 99 di asumsi Tinggi)
    	    				if(	i == 1 ){
    	    					if( ldec_hasil_invest[ 1 ][ k ] < 0 ){
    	    						lb_minus[ k ] = true;
    	    					}
    	    				}else{
    	    					if(k>2){
    	        					if( ldec_hasil_invest[ 1 ][ k ] < 0 ){
    	        						lb_minus[ 1 ] = true;
    	        					}
    	        				}
    	    				}
    				}
    			} else if(istr_prop.bisnis_id == 120 && (istr_prop.bisnis_no >= 19 && istr_prop.bisnis_no <= 21) && i> 1){
    				//REQ: VITO @15/09/2015
        		    //E-Proposal Sinarmas Sekuritas (SMiLe Link Satu):
    				//sampai tahun ke-10 tidak ada ** (NIL)
    	           if( ldec_hasil_invest[ 1 ][ k ] <= 0 && ( i <= 10  ) )
    		       {
    		           lb_minus[ k ] = true;
    		       }  
    			}
    			else{
    				 if( ldec_hasil_invest[ 1 ][ k ] <= 0 )
                     {   
        				 /*
        				 // Kredit Mikro BSIM (2)
        				 if(cepr01030102Form.getJenisCd()!=null && cepr01030102Form.getJenisCd()==2){
        					if( ( istr_prop.umur_tt < 50 && i <= 15 ) || ( istr_prop.umur_tt >= 50 && i <= 10 ) ) 
        		                {
        		                    lb_minus[ k ] = true;
        		                }  
        				 }else{
        				 */
    	    				 double ldec_maks_premi_tahunan = 100000000.0;
    	    				 if( istr_prop.kurs_id.equals("02") )  ldec_maks_premi_tahunan = 10000.0;
    	    				 if( i <= 20 ){
    	    					 lb_minus[ k ] = true;
    	    				 }else if( i > 20 && ldec_premi_setahun <= ldec_maks_premi_tahunan ){
    	    					 lb_minus[ k ] = true;
    	    				 }    				 
        				// }
                     }
    			 }
    		}    	

            if( i <= 23 || j == 55 || j == 65 || j == 70 || j == 75 || j== 80 || j == 100 )
            {
                for( int k = 1; k <= 3; k++ )
                {
                	if(j == 100){
                		if( istr_prop.cb_id == 0 ){
                			np[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] ) / li_bagi, 0 );
                			np_nonUP[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] ) / li_bagi, 0 );
                		}else{
                			np[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] + ldec_manfaat ) / li_bagi, 0 );
                			np_nonUP[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] ) / li_bagi, 0 );
                		}
                	
                	}else {
                		np[ k ] = FormatNumber.round( ldec_hasil_invest[ 1 ][ k ] / li_bagi, 0 );
                		np_nonUP[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] ) / li_bagi, 0 );
                	}
                	
                	if(j == 100){
                		if( istr_prop.cb_id == 0 ){
                			npbatal[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] * (1 - ldec_sc)  ) / li_bagi, 0 );
                		}else{
                			npbatal[ k ] = FormatNumber.round( ( (ldec_hasil_invest[ 1 ][ k ]+ ldec_manfaat)  * (1 - ldec_sc)  ) / li_bagi, 0 );
                		}
                	}else{
                		npbatal[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] * (1 - ldec_sc)  ) / li_bagi, 0 );
                	}
                	
                	celaka[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] + ldec_manfaat ) / li_bagi, 0 );
                	celaka_nonUP[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] ) / li_bagi, 0 );
                }

                if( i <= lamaBayar )
                {
                    //premiumTotal = editorUtil.convertToStringWithoutCent( ldec_premi_setahun / li_bagi );
                	premiumTotal = FormatNumber.round( ( ldec_premi_setahun / li_bagi ), 0) + "";
                }
                else
                {
                    premiumTotal = "";
                }

                if( i < topupDrawVOListSize )
                {
                    // why ( i - 1 )? becoz index in Java start from 0, not like PB programming language
                    topupDrawVO = topupDrawVOList.get( i - 1 );
                    topup = "0";
                    draw = "0";
                    if( topupDrawVO.getYearFlag() != null && topupDrawVO.getYearFlag().equals("true") ){                    
                      if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) ){
                  		  	topup = editorUtil.convertToString( topupDrawVO.getTopupAmount() );
    	                   	draw = editorUtil.convertToString( topupDrawVO.getDrawAmount() );
                  	  }else{
                  		  topup = editorUtil.convertToString( topupDrawVO.getTopupAmount().divide( new BigDecimal( "1000" ) ) );
    	                  draw = editorUtil.convertToString( topupDrawVO.getDrawAmount().divide( new BigDecimal( "1000" ) ) );                		  
                  	  }  
                    }
                    if( "0".equals( topup ) ) topup = "0.00";
                    if( "0".equals( draw ) ) draw = "0.00";
                }
                else
                {
                    topup = "0.00";
                    draw = "0.00";
                }
                /*	
                String valueLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 1 ] ) );
                String valueMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 2 ] ) );
                String valueHi = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 3 ] ) );

                String benefitLow = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 1 ], np[ 1 ] );
                String benefitMid = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 2 ], np[ 2 ] );
                String benefitHi = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 3 ], np[ 3 ] );
                */
                String valueLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np_nonUP[ 1 ] ) );
                String valueMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np_nonUP[ 2 ] ) );
                String valueHi = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np_nonUP[ 3 ] ) );
                if(!"nil".equals(valueLow)){
                	   valueLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 1 ] ) );
                }
                if(!"nil".equals(valueMid)){
                	  valueMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 2 ] ) );
                }
                if(!"nil".equals(valueHi)){
                	valueHi = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 3 ] ) );
                }
                
                String batalLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 1 ] ) );
                String batalMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 2 ] ) );
                String batalHi  =  editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 3 ] ) );
                                
                String benefitLow = convertToStringWithoutCentAndSetNill( editorUtil, celaka_nonUP[ 1 ], np_nonUP[ 1 ] );
                String benefitMid = convertToStringWithoutCentAndSetNill( editorUtil, celaka_nonUP[ 2 ], np_nonUP[ 2 ] );
                String benefitHi = convertToStringWithoutCentAndSetNill( editorUtil, celaka_nonUP[ 3 ], np_nonUP[ 3 ] );
                if(!"nil".equals(benefitLow)){
                	benefitLow = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 1 ], np[ 1 ] );
                }
                if(!"nil".equals(benefitMid)){
                	benefitMid = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 2 ], np[ 2 ] );
             	}
             	if(!"nil".equals(benefitHi)){
             		benefitHi = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 3 ], np[ 3 ] );
             	}
                
                
                if("nil".equals(valueLow)){
                	valueLow = "**";
                }
                if("nil".equals(valueMid)){
                	valueMid = "**";
                }
                if("nil".equals(valueHi)){
                	valueHi = "**";
                }
                if("nil".equals(benefitLow)){
                	benefitLow = "**";
                }
                if("nil".equals(benefitMid)){
                	benefitMid = "**";
                }
                if("nil".equals(benefitHi)){
                	benefitHi = "**";
                }
                
                map = new HashMap<String, Object>();
                map.put( "yearNo", editorUtil.convertToString( i ) );
                map.put( "insuredAge", editorUtil.convertToString( cepr01030101Form.getInsuredAge() + i ) );
                map.put( "premiumTotal", editorUtil.convertToStringWithoutCentAndNillIfNegative( premiumTotal ) );
                map.put( "topupAssumption", topup );
                map.put( "drawAssumption", draw );
                map.put( "valueLow", valueLow );
                map.put( "valueMid", valueMid );
                map.put( "valueHi", valueHi );
                map.put( "batalLow", batalLow );
                map.put( "batalMid", batalMid );
                map.put( "batalHi", batalHi );
                map.put( "benefitLow", benefitLow );
                map.put( "benefitMid", benefitMid );
                map.put( "benefitHi", benefitHi );
                mapList.add( map );

            }
            if (i <= 25 || (j % 5) == 0 ){
            	Map<String, Object> map2 = new HashMap<String, Object>();
            	String drawAssumptionLow = editorUtil.convertToRoundedNoDigit( new BigDecimal( ldec_tarik[ 1 ] / li_bagi ) );
                String drawAssumptionMid = editorUtil.convertToRoundedNoDigit( new BigDecimal( ldec_tarik[ 2 ] / li_bagi ) );
                String drawAssumptionHi = editorUtil.convertToRoundedNoDigit( new BigDecimal( ldec_tarik[ 3 ] / li_bagi ) );
                
                map2.put( "yearNo", editorUtil.convertToString( i ) );
                map2.put( "insuredAge", editorUtil.convertToString( cepr01030101Form.getInsuredAge() + i ) );
                map2.put( "drawAssumptionLow", drawAssumptionLow );
                map2.put( "drawAssumptionMid", drawAssumptionMid );
                map2.put( "drawAssumptionHi", drawAssumptionHi );
                mapList2.add( map2 );
            }
        }

        result.setValidityMsg( lb_minus[ 1 ] ? "Maaf, Proposal yang Anda buat Tidak Layak Jual." : "" );
        result.setIllustrationList( mapList );
        result2.setIllustrationList( mapList2 );
        mapMaster.put("Illustration1", result);
        mapMaster.put("Illustration2", result2);
        return mapMaster;

    }
        
    public Cepr00000000YieldResultVO of_get_rate_ds()
    {
        logger.info( "*-*-*-* Cepr01040201Business.of_get_rate_ds" );
        String[] ls_jenis = getInvestmentTitleArr();
        Cepr00000000YieldResultVO result = new Cepr00000000YieldResultVO();
        ArrayList<Map<String, Object>> yieldList = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        int i;
        S_biaya lstr;

        lstr = PbFunction.wf_get_biaya_84( istr_prop, command );
        int a, b;
       
        if(istr_prop.bisnis_id == 120 && ( istr_prop.bisnis_no >= 19 && istr_prop.bisnis_no <= 21) ){
        	if( "01".equals( istr_prop.kurs_id ) )
            {
                a = 1;               
                b = 5;                
            }
            else
            {
                a = 6;
                b = 7;
            } 
        }else if(istr_prop.bisnis_id == 120 && ( istr_prop.bisnis_no >= 22 && istr_prop.bisnis_no <= 24) ){ //IGA - PROJECT SIMPOL
        	if( "01".equals( istr_prop.kurs_id ) )
            {
                a = 1;               
                b = 8;                
            }
            else
            {
                a = 9;
                b = 10;
            } //DONE
        }else{        	
        	if( "01".equals( istr_prop.kurs_id ) )
            {
                a = 1;
                if(CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiFixListDisplay()) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiDynamicListDisplay()) &&
                		CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiAggresiveListDisplay())	){
                	b = 6;
                }else{
                	b = 3;
                }
            }
            else
            {
                a = 4;
                b = 5;
            }
        }  

        double totalInvestmentAllocation = 0;
        double totalAsumptionLow = 0;
        double totalAsumptionMid = 0;
        double totalAsumptionHi = 0;

        double allocationYieldLow =0;
        double allocationYieldMid=0;
        double allocationYieldHi=0;

        double annualYieldLow;
        double annualYieldMid;
        double annualYieldHi;

        int j;
        for( i = a; i <= b; i++ )
        {
        	j=i;
        	if( "01".equals( istr_prop.kurs_id ) )
            {
        	 if(CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiFixListDisplay()) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiDynamicListDisplay()) &&
             		CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiAggresiveListDisplay())	){
             	if(j>3){
             		if(istr_prop.bisnis_id != 120 && ( istr_prop.bisnis_no != 22 || istr_prop.bisnis_no != 23 || istr_prop.bisnis_no != 24) ){ //IGA - PROJECT SIMPOL
             		j=j-3;
             		} //DONE
             	}        		 
             }
            }
        	 
            annualYieldLow = lstr.bunga[ j ][ 1 ] * 100;
            annualYieldMid = lstr.bunga[ j ][ 2 ] * 100;
            annualYieldHi = lstr.bunga[ j ][ 3 ] * 100;
            
            allocationYieldLow = annualYieldLow * istr_prop.fund[ j ] / 100;
	        allocationYieldMid = annualYieldMid * istr_prop.fund[ j ] / 100;
	        allocationYieldHi = annualYieldHi * istr_prop.fund[ j ] / 100;
            params = new HashMap<String, Object>();	
           
            //SIMPOL => 6 Pilihan Investasi Fund: EXCELLINK & Simas
            if(cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X22 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X23 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X24){ 
        	 if("01".equals( istr_prop.kurs_id )//IGA - PROJECT SIMPOL 
//        			 && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiFixListDisplay()) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiDynamicListDisplay()) &&
//             		CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiAggresiveListDisplay())	
             		){
        		
//        		 if( (i==1 && cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo( new BigDecimal( "0" ) ) > 0  && cepr01030102Form.getLjiFixCd().equals("01")) ||
//        			 (i==2 && cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0  && cepr01030102Form.getLjiDynamicCd().equals("02")) ||
//        			 (i==3 && cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0  && cepr01030102Form.getLjiAggresiveCd().equals("03")) ||
//        			 (i==4 && cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo( new BigDecimal( "0" ) ) > 0  && cepr01030102Form.getLjiFixCd().equals("35")) ||
//        			 (i==5 && cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0  && cepr01030102Form.getLjiDynamicCd().equals("36")) ||
//        			 (i==6 && cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0  && cepr01030102Form.getLjiAggresiveCd().equals("37"))
//        		){	       
        	         params.put( "fundDesc", ls_jenis[ i ] );
        			 params.put( "investmentAllocation", editorUtil.convertToTwoDigit( istr_prop.fund[ j ] ) + "%" );
        			 params.put( "allocationYieldLow", editorUtil.convertToTwoDigit( allocationYieldLow ) + "%" );
        	         params.put( "allocationYieldMid", editorUtil.convertToTwoDigit( allocationYieldMid ) + "%" );
        	         params.put( "allocationYieldHi", editorUtil.convertToTwoDigit( allocationYieldHi ) + "%" );
                 }        		
//                 else{
//                
//        	         params.put( "fundDesc", ls_jenis[ i ] );
//        			 params.put( "investmentAllocation", editorUtil.convertToTwoDigit( 0 ) + "%" );
//        			 params.put( "allocationYieldLow", editorUtil.convertToTwoDigit( 0 ) + "%" );
//        	         params.put( "allocationYieldMid", editorUtil.convertToTwoDigit( 0 ) + "%" );
//        	         params.put( "allocationYieldHi", editorUtil.convertToTwoDigit( 0 ) + "%" );
//                	  
//                 }        		         		 
//             }
        	 else if("02".equals( istr_prop.kurs_id )) {
        		 params.put( "fundDesc", ls_jenis[ i ] );
        		 params.put( "investmentAllocation", editorUtil.convertToTwoDigit( istr_prop.fund[ i ] ) + "%" );//DONE                              
                 params.put( "allocationYieldLow", editorUtil.convertToTwoDigit( allocationYieldLow ) + "%" );
                 params.put( "allocationYieldMid", editorUtil.convertToTwoDigit( allocationYieldMid ) + "%" );
                 params.put( "allocationYieldHi", editorUtil.convertToTwoDigit( allocationYieldHi ) + "%" );       		 
        	 }        	 
            }
        	else{
                 params.put( "fundDesc", ls_jenis[ i ] );
        		  params.put( "investmentAllocation", editorUtil.convertToTwoDigit( istr_prop.fund[ i ] ) + "%" );
                                    
                  params.put( "allocationYieldLow", editorUtil.convertToTwoDigit( allocationYieldLow ) + "%" );
                  params.put( "allocationYieldMid", editorUtil.convertToTwoDigit( allocationYieldMid ) + "%" );
                  params.put( "allocationYieldHi", editorUtil.convertToTwoDigit( allocationYieldHi ) + "%" );
        		 
        	}
        	 
        	params.put( "annualYieldLow", editorUtil.convertToTwoDigit( annualYieldLow ) + "%" );
            params.put( "annualYieldMid", editorUtil.convertToTwoDigit( annualYieldMid ) + "%" );
            params.put( "annualYieldHi", editorUtil.convertToTwoDigit( annualYieldHi ) + "%" );
            yieldList.add( params );

            if( ("01".equals( istr_prop.kurs_id ) ) ||  ("02".equals( istr_prop.kurs_id ) ))
            {
	            totalInvestmentAllocation = totalInvestmentAllocation + istr_prop.fund[ j ];
	            totalAsumptionLow = totalAsumptionLow + allocationYieldLow;
	            totalAsumptionMid = totalAsumptionMid + allocationYieldMid;
	            totalAsumptionHi = totalAsumptionHi + allocationYieldHi;
            }           
        }        
        
        result.setYieldList( yieldList );
        result.setTotalInvestmentAllocation( editorUtil.convertToTwoDigit( totalInvestmentAllocation ) + "%" );
        result.setTotalAssumptionLow( editorUtil.convertToTwoDigit( totalAsumptionLow ) + "%" );
        result.setTotalAssumptionMid( editorUtil.convertToTwoDigit( totalAsumptionMid ) + "%" );
        result.setTotalAssumptionHi( editorUtil.convertToTwoDigit( totalAsumptionHi ) + "%" );

        return result;
    }
    
    
    public List<Map<String, Object>> getInvestmentChoiceSmallRowList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getInvestmentChoiceSmallRowList" );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        String[] smallInvestmentTitleArr;
        
        String productLabelFixedFund = "EXCELLINK", productLabelDynamicFund = "EXCELLINK", productLabelAggresiveFund ="EXCELLINK";
        String productLabel = "EXCELLINK";
        
        
        if(cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X19 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X20 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X21){ 
	        	productLabel 			= "Simas";        	
	        	productLabelFixedFund 	= "Simas"; 
	        	productLabelDynamicFund = "Simas"; 
	        	productLabelAggresiveFund ="Simas";
//        }else if(cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X22 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X23 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X24){ //IGA - PROJECT SIMPOL
// 	        if( cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo( new BigDecimal( "0" ) ) > 0 && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiFixListDisplay()) &&
//	   			 cepr01030102Form.getLjiFixCd().equals("01"))
//	        {
// 	        	productLabelFixedFund = "EXCELLINK";
//	        }
//	        else if( cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo( new BigDecimal( "0" ) ) > 0 && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiFixListDisplay()) &&
//				 cepr01030102Form.getLjiFixCd().equals("35"))
//	        {	 
//	        	productLabelFixedFund = "Simas";
//	        }
//	   	 
//	        if( cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0 && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiDynamicListDisplay()) &&
//	   			 cepr01030102Form.getLjiDynamicCd().equals("02"))
//	        {
//	        	productLabelDynamicFund = "EXCELLINK";
//	        }
//	        else if( cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0 && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiDynamicListDisplay()) &&
//	   			 cepr01030102Form.getLjiDynamicCd().equals("36"))
//	        {
//	        	productLabelDynamicFund = "Simas";
//	        }        	 
//	        if( cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0 && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiAggresiveListDisplay()) &&
//	   			 cepr01030102Form.getLjiAggresiveCd().equals("03"))
//	        {
//	        	productLabelAggresiveFund = "EXCELLINK";
//	        }
//	   	 	else if( cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0 && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiAggresiveListDisplay()) &&
//	   			 cepr01030102Form.getLjiAggresiveCd().equals("37"))
//	        {
//	   	 		productLabelAggresiveFund = "Simas";
//	        }//DONE
        }   
       
        smallInvestmentTitleArr = new String[]{
        		  "",
        		  productLabelFixedFund + " Fixed Income Fund",
        		  productLabelDynamicFund + " Dynamic Fund",
        		  productLabelAggresiveFund + " Aggressive Fund",
        		  "EXCELLINK Cash Fund",
   	              "EXCELLINK Equity Bakti Peduli",
        		  productLabel + " Secure Dollar Income",
        		  productLabel + " Dynamic Dollar Fund"
 	        } ; 	       
 	       
        Map<String, Object> params;
        if(cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X22 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X23 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X24){ //IGA - PROJECT SIMPOL
        	 if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
             {
                 if( cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", "- " + investmentTitleArr[ 1 ] );
                     params.put( "currencySymbol", currencySymbol );
                     params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getFixIdr() ).divide( new BigDecimal( "100" ) ) ) );
                     params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getFixIdr() + "%" ) );
                     result.add( params );
                 }
                 
                 if( cepr01030102Form.getFixSimasIdr() != null && cepr01030102Form.getFixSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
                 {
                 	if( cepr01030102Form.getLjiFixSimasCd().equals("35") ){
       	                params = new HashMap<String, Object>();
       	                params.put( "description", "- " + investmentTitleArr[ 4 ] );
       	                params.put( "currencySymbol", currencySymbol );
       	                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getFixSimasIdr() ).divide( new BigDecimal( "100" ) ) ) );
       	                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getFixSimasIdr() + "%" ) );
       	                result.add( params );
       	            }
                 }
                 
                 if( cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", "- " + investmentTitleArr[ 2 ] );
                     params.put( "currencySymbol", currencySymbol );
                     params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getDynamicIdr() ).divide( new BigDecimal( "100" ) ) ) );
                     params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicIdr() + "%" ) );
                     result.add( params );
                 }
                 
                 if( cepr01030102Form.getDynamicSimasIdr() != null  && cepr01030102Form.getDynamicSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
                 {
                 	if( cepr01030102Form.getLjiDynamicSimasCd().equals("36") ){
       	                params = new HashMap<String, Object>();
       	                params.put( "description", "- " + investmentTitleArr[ 5 ] );
       	                params.put( "currencySymbol", currencySymbol );
       	                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getDynamicSimasIdr() ).divide( new BigDecimal( "100" ) ) ) );
       	                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicSimasIdr() + "%" ) );
       	                result.add( params );
                 	}
                 }

                 if( cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", "- " + investmentTitleArr[ 3 ] );
                     params.put( "currencySymbol", currencySymbol );
                     params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getAggresiveIdr() ).divide( new BigDecimal( "100" ) ) ) );
                     params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveIdr() + "%" ) );
                     result.add( params );
                 }
                 if( cepr01030102Form.getAggresiveSimasIdr() != null  && cepr01030102Form.getAggresiveSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
                 {
                 	if( cepr01030102Form.getLjiAggresiveSimasCd().equals("37") ){
                 		 params = new HashMap<String, Object>();
                          params.put( "description", "- " + investmentTitleArr[ 6 ] );
                          params.put( "currencySymbol", currencySymbol );
                          params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getAggresiveSimasIdr() ).divide( new BigDecimal( "100" ) ) ) );
                          params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveSimasIdr() + "%" ) );
                          result.add( params );
                 	}               
                 }
                 
                 if( cepr01030102Form.getCashFundIdr() != null ){
                 		if( cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", "- " + investmentTitleArr[ 7 ] );
                     params.put( "currencySymbol", currencySymbol );
                     params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getCashFundIdr() ).divide( new BigDecimal( "100" ) ) ) );
                     params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getCashFundIdr() + "%" ) );
                     result.add( params );
                 }}          
                 if( cepr01030102Form.getExcellinkEquityIdr() != null && cepr01030102Form.getExcellinkEquityIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", "- " + investmentTitleArr[ 8 ] );
                     params.put( "currencySymbol", currencySymbol );
                     params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getExcellinkEquityIdr() ).divide( new BigDecimal( "100" ) ) ) );
                     params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getExcellinkEquityIdr() + "%" ) );
                     result.add( params );
                 }
             }
             else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
             {    	
                 if( cepr01030102Form.getSecureUsd() != null && cepr01030102Form.getSecureUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
                 {
                     params = new HashMap<String, Object>();
                
                     params.put( "description", "- " + investmentTitleArr[ 9 ] );         
                     params.put( "currencySymbol", currencySymbol );
                     params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getSecureUsd() ).divide( new BigDecimal( "100" ) ) ) );
                     params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getSecureUsd() + "%" ) );
                     result.add( params ); 
                 }

                 if( cepr01030102Form.getDynamicUsd() != null && cepr01030102Form.getDynamicUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", "- " + investmentTitleArr[ 10 ] );
                                  
                     params.put( "currencySymbol", currencySymbol );
                     params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getDynamicUsd() ).divide( new BigDecimal( "100" ) ) ) );
                     params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicUsd() + "%" ) );
                     result.add( params );
                 }
             }
        }else{
        	if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                if( cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", "- " + smallInvestmentTitleArr[ 1 ] );
                    params.put( "currencySymbol", currencySymbol );
                    params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getFixIdr() ).divide( new BigDecimal( "100" ) ) ) );
                    params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getFixIdr() + "%" ) );
                    result.add( params );
                }

                if( cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", "- " + smallInvestmentTitleArr[ 2 ] );
                    params.put( "currencySymbol", currencySymbol );
                    params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getDynamicIdr() ).divide( new BigDecimal( "100" ) ) ) );
                    params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicIdr() + "%" ) );
                    result.add( params );
                }

                if( cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", "- " + smallInvestmentTitleArr[ 3 ] );
                    params.put( "currencySymbol", currencySymbol );
                    params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getAggresiveIdr() ).divide( new BigDecimal( "100" ) ) ) );
                    params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveIdr() + "%" ) );
                    result.add( params );
                }
                if( cepr01030102Form.getCashFundIdr() != null ){
            		if( cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            		{
    	            params = new HashMap<String, Object>();
    	            params.put( "description", "- " + investmentTitleArr[ 4 ] );
    	            params.put( "currencySymbol", currencySymbol );
    	            params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getCashFundIdr() ).divide( new BigDecimal( "100" ) ) ) );
    	            params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getCashFundIdr() + "%" ) );
    	            result.add( params );
            	}}            
                if( cepr01030102Form.getExcellinkEquityIdr() != null && cepr01030102Form.getExcellinkEquityIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", "- " + investmentTitleArr[ 5 ] );
                    params.put( "currencySymbol", currencySymbol );
                    params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getExcellinkEquityIdr() ).divide( new BigDecimal( "100" ) ) ) );
                    params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getExcellinkEquityIdr() + "%" ) );
                    result.add( params );
                }            
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                if( cepr01030102Form.getSecureUsd() != null && cepr01030102Form.getSecureUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", "- " + smallInvestmentTitleArr[ 6 ] );
                    params.put( "currencySymbol", currencySymbol );
                    params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getSecureUsd() ).divide( new BigDecimal( "100" ) ) ) );
                    params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getSecureUsd() + "%" ) );
                    result.add( params );
                }

                if( cepr01030102Form.getDynamicUsd() != null && cepr01030102Form.getDynamicUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", "- " + smallInvestmentTitleArr[ 7 ] );
                    params.put( "currencySymbol", currencySymbol );
                    params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getDynamicUsd() ).divide( new BigDecimal( "100" ) ) ) );
                    params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicUsd() + "%" ) );
                    result.add( params );
                }
            }
        }
        //DONE
        return result;
    }
    
    public List<Map<String, Object>> getInvestmentChoiceRowList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getInvestmentChoiceRowList" );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        Map<String, Object> params;
        if (cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X22 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X23 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X24){//IGA - PROJECT SIMPOL
        	 if( cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
             {
                 params = new HashMap<String, Object>();
                 params.put( "description", investmentTitleAndDescrArr[ 1 ][ 1 ] );
                 params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getFixIdr() + "%" ) );
                 result.add( params );

                 for( int i = 2;  i < investmentTitleAndDescrArr[ 1 ].length; i++  )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", investmentTitleAndDescrArr[ 1 ][ i ] );
                     params.put( "allocationPercent", "" );
                     result.add( params );
                 }
             }
             if( cepr01030102Form.getFixSimasIdr() != null && cepr01030102Form.getFixSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
             {
                 params = new HashMap<String, Object>();
                 params.put( "description", investmentTitleAndDescrArr[ 2 ][ 1 ] );
                 params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getFixSimasIdr() + "%" ) );
                 result.add( params );

                 for( int i = 2;  i < investmentTitleAndDescrArr[ 2 ].length; i++  )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", investmentTitleAndDescrArr[ 2 ][ i ] );
                     params.put( "allocationPercent", "" );
                     result.add( params );
                 }
             }
             if( cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
             {
                 params = new HashMap<String, Object>();
                 params.put( "description", investmentTitleAndDescrArr[ 3 ][ 1 ] );
                 params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicIdr() + "%" ) );
                 result.add( params );

                 for( int i = 2;  i < investmentTitleAndDescrArr[ 3 ].length; i++  )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", investmentTitleAndDescrArr[ 3 ][ i ] );
                     params.put( "allocationPercent", "" );
                     result.add( params );
                 }
             }
             if( cepr01030102Form.getDynamicSimasIdr() != null && cepr01030102Form.getDynamicSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
             {
                 params = new HashMap<String, Object>();
                 params.put( "description", investmentTitleAndDescrArr[ 4 ][ 1 ] );
                 params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicSimasIdr() + "%" ) );
                 result.add( params );

                 for( int i = 2;  i < investmentTitleAndDescrArr[ 4 ].length; i++  )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", investmentTitleAndDescrArr[ 4 ][ i ] );
                     params.put( "allocationPercent", "" );
                     result.add( params );
                 }
             }
             if( cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
             {
                 params = new HashMap<String, Object>();
                 params.put( "description", investmentTitleAndDescrArr[ 5 ][ 1 ] );
                 params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveIdr() + "%" ) );
                 result.add( params );

                 for( int i = 2;  i < investmentTitleAndDescrArr[ 5 ].length; i++  )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", investmentTitleAndDescrArr[ 5 ][ i ] );
                     params.put( "allocationPercent", "" );
                     result.add( params );
                 }
             }
             if( cepr01030102Form.getAggresiveSimasIdr() != null && cepr01030102Form.getAggresiveSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
             {
                 params = new HashMap<String, Object>();
                 params.put( "description", investmentTitleAndDescrArr[ 6 ][ 1 ] );
                 params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveSimasIdr() + "%" ) );
                 result.add( params );

                 for( int i = 2;  i < investmentTitleAndDescrArr[ 6 ].length; i++  )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", investmentTitleAndDescrArr[ 6 ][ i ] );
                     params.put( "allocationPercent", "" );
                     result.add( params );
                 }
             }
             if( cepr01030102Form.getCashFundIdr() != null ){
             	if( cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) > 0 )            
   	          {
   	          	 params = new HashMap<String, Object>();
   	               params.put( "description", investmentTitleAndDescrArr[ 7 ][ 1 ] );
   	               params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getCashFundIdr() + "%" ) );
   	               result.add( params );
   	               
   	               for( int i = 2;  i < investmentTitleAndDescrArr[ 7 ].length; i++  )
   	               {
   	                   params = new HashMap<String, Object>();
   	                   params.put( "description", investmentTitleAndDescrArr[ 7 ][ i ] );
   	                   params.put( "allocationPercent", "" );
   	                   result.add( params );
   	               }
   	          	
   	          }
             }
             
             if( cepr01030102Form.getExcellinkEquityIdr() != null && cepr01030102Form.getExcellinkEquityIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
             {
             	 params = new HashMap<String, Object>();
                  params.put( "description", investmentTitleAndDescrArr[ 8 ][ 1 ] );
                  params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getExcellinkEquityIdr() + "%" ) );
                  result.add( params );
                  
                  for( int i = 2;  i < investmentTitleAndDescrArr[ 8 ].length; i++  )
                  {
                      params = new HashMap<String, Object>();
                      params.put( "description", investmentTitleAndDescrArr[ 8 ][ i ] );
                      params.put( "allocationPercent", "" );
                      result.add( params );
                  }          	
             }   
        }else{
        	 if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
             {
                 if( cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", investmentTitleAndDescrArr[ 1 ][ 1 ] );
                     params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getFixIdr() + "%" ) );
                     result.add( params );

                     for( int i = 2;  i < investmentTitleAndDescrArr[ 1 ].length; i++  )
                     {
                         params = new HashMap<String, Object>();
                         params.put( "description", investmentTitleAndDescrArr[ 1 ][ i ] );
                         params.put( "allocationPercent", "" );
                         result.add( params );
                     }
                 }
                 if( cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", investmentTitleAndDescrArr[ 2 ][ 1 ] );
                     params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicIdr() + "%" ) );
                     result.add( params );

                     for( int i = 2;  i < investmentTitleAndDescrArr[ 2 ].length; i++  )
                     {
                         params = new HashMap<String, Object>();
                         params.put( "description", investmentTitleAndDescrArr[ 2 ][ i ] );
                         params.put( "allocationPercent", "" );
                         result.add( params );
                     }
                 }
                 if( cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", investmentTitleAndDescrArr[ 3 ][ 1 ] );
                     params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveIdr() + "%" ) );
                     result.add( params );

                     for( int i = 2;  i < investmentTitleAndDescrArr[ 3 ].length; i++  )
                     {
                         params = new HashMap<String, Object>();
                         params.put( "description", investmentTitleAndDescrArr[ 3 ][ i ] );
                         params.put( "allocationPercent", "" );
                         result.add( params );
                     }
                 }
                 if( cepr01030102Form.getCashFundIdr() != null ){
                 	if( cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) > 0 )            
                 {
                 	 params = new HashMap<String, Object>();
                      params.put( "description", investmentTitleAndDescrArr[ 4 ][ 1 ] );
                      params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getCashFundIdr() + "%" ) );
                      result.add( params );
                      
                      for( int i = 2;  i < investmentTitleAndDescrArr[ 4 ].length; i++  )
                      {
                          params = new HashMap<String, Object>();
                          params.put( "description", investmentTitleAndDescrArr[ 4 ][ i ] );
                          params.put( "allocationPercent", "" );
                          result.add( params );
                      }
                 	
                 }
                 }
                 
                 if( cepr01030102Form.getExcellinkEquityIdr() != null && cepr01030102Form.getExcellinkEquityIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
                 {
                 	 params = new HashMap<String, Object>();
                      params.put( "description", investmentTitleAndDescrArr[ 5 ][ 1 ] );
                      params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getExcellinkEquityIdr() + "%" ) );
                      result.add( params );
                      
                      for( int i = 2;  i < investmentTitleAndDescrArr[ 5 ].length; i++  )
                      {
                          params = new HashMap<String, Object>();
                          params.put( "description", investmentTitleAndDescrArr[ 5 ][ i ] );
                          params.put( "allocationPercent", "" );
                          result.add( params );
                      }
                 	
                 }            
             }
             else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
             {
                 if( cepr01030102Form.getSecureUsd() != null && cepr01030102Form.getSecureUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", investmentTitleAndDescrArr[ 6 ][ 1 ] );
                     params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getSecureUsd() + "%" ) );
                     result.add( params );

                     for( int i = 2;  i < investmentTitleAndDescrArr[ 6 ].length; i++  )
                     {
                     	if( investmentTitleAndDescrArr[ 6 ][ i ] != null)
                     	{
     	                    params = new HashMap<String, Object>();
     	                    params.put( "description", investmentTitleAndDescrArr[ 6 ][ i ] );
     	                    params.put( "allocationPercent", "" );
     	                    result.add( params );
                     	}
                     }
                 }
                 if( cepr01030102Form.getDynamicUsd() != null && cepr01030102Form.getDynamicUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", investmentTitleAndDescrArr[ 7 ][ 1 ] );
                     params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicUsd() + "%" ) );
                     result.add( params );
                     for( int i = 2;  i < investmentTitleAndDescrArr[ 7 ].length; i++  )
                     {
                     	if( investmentTitleAndDescrArr[ 7 ][ i ] != null ){
     	                    params = new HashMap<String, Object>();
     	                    params.put( "description", investmentTitleAndDescrArr[ 7 ][ i ] );
     	                    params.put( "allocationPercent", "" );
     	                    result.add( params );
                     	}
                     }
                 }
             }
        }//DONE

        return result;
    }
    

}