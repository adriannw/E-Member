package id.co.sinarmaslife.eproposal.web;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000CommonValidator;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.setup.Product_setup;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_bisnis_rider;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_kurs;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_pay_mode;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_premium_comb;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_product_acquisition;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_product_calc;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_product_invest;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_product_setup;
import id.co.sinarmaslife.eproposal.model.setup.sub.RiderDisplay;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.standard.model.vo.AssurancePlanList;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.StringUtil;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.validation.Errors;

public class CeprProductSetupValidator extends Cepr01040000CommonValidator {
	
	protected final Log logger = LogFactory.getLog( getClass() );
	// private static String ews_path_lst_product_setup =
	// "http://ews.sinarmasmsiglife.co.id/api/eproposal/lst_product_setup";
	// private static String ews_path_lst_product_calc =
	// "http://ews.sinarmasmsiglife.co.id/api/eproposal/lst_product_calc";
	// private static String ews_path_lst_premium_comb =
	// "http://ews.sinarmasmsiglife.co.id/api/eproposal/lst_premium_comb";
	// private static String ews_path_lst_bisnis_rider =
	// "http://ews.sinarmasmsiglife.co.id/api/eproposal/lst_bisnis_rider";
	// private static String ews_path_rider_display_list =
	// "http://ews.sinarmasmsiglife.co.id/api/eproposal/generateRiderList";

	private static String ews_path_lst_product_setup = "http://localhost:8080/EWS/api/eproposal/lst_product_setup";
	private static String ews_path_lst_product_calc = "http://localhost:8080/EWS/api/eproposal/lst_product_calc";
	private static String ews_path_lst_premium_comb = "http://localhost:8080/EWS/api/eproposal/lst_premium_comb";
	private static String ews_path_lst_bisnis_rider = "http://localhost:8080/EWS/api/eproposal/lst_bisnis_rider";
	private static String ews_path_rider_display_list = "http://localhost:8080/EWS/api/eproposal/generateRiderList";
	private static String ews_path_option_lst_kurs = "http://localhost:8080/EWS/api/eproposal/option_lst_kurs";
	private static String ews_path_option_lst_pay_mode = "http://localhost:8080/EWS/api/eproposal/option_lst_pay_mode";
	private static String ews_path_lst_product_invest = "http://localhost:8080/EWS/api/eproposal/lst_product_invest";
	private static String ews_path_lst_product_acquisition = "http://localhost:8080/EWS/api/eproposal/lst_product_acquisition";

	private static String ews_path_lst_packet_rider = "http://localhost:8080/EWS/api/eproposal/lst_packet_rider";
	private static String ews_path_lst_packet_calc = "http://localhost:8080/EWS/api/eproposal/lst_packet_calc";
	private static String ews_path_lst_packet_invest = "http://localhost:8080/EWS/api/eproposal/lst_packet_invest";
	
	public JSONObject getPostEws(String url) {

		JSONObject responsePost = null;
		HttpClient httpClient = new HttpClient();
		PostMethod method = new PostMethod(url);

		try {

			HashMap<String, Object> ews_param = new HashMap<String, Object>();
			ews_param.put(
					"pset_id",
					String.valueOf(cepr01030102Form.getProduct_setup()
							.getLst_product_setup().getPset_id()));
			ews_param.put("lsbs_id",
					String.valueOf(cepr01030102Form.getLeftPartOfBusinessCd()));
			ews_param
					.put("lsdbs_number", String.valueOf(cepr01030102Form
							.getRightPartOfBusinessCd()));
			ews_param.put("lku_id",
					String.valueOf(cepr01030102Form.getCurrencyCd()));
			ews_param.put("lscb_id",
					String.valueOf(cepr01030102Form.getPaymentModeCd()));

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("data", ews_param);

			StringRequestEntity requestEntity = new StringRequestEntity(
					jsonObject.toString(), "application/json", null);

			method.setRequestEntity(requestEntity);

			int statusCode = httpClient.executeMethod(method);
			//logger.info("HTTP status " + statusCode
			//		+ " creating con\n\n");
			logger.debug( "HTTP status " + statusCode+ " creating con\n\n");

			if (statusCode == HttpStatus.SC_OK) {
				responsePost = new JSONObject(
						new JSONTokener(new InputStreamReader(
								method.getResponseBodyAsStream())));

			}
		} catch (Exception e) {
			logger.error("ERROR", e);
			responsePost = null;
		}

		return responsePost;
	}

	public void setLstProductSetup(String url) {
		JSONArray getArray = new JSONArray();
		JSONObject responsePost = getPostEws(url);
		if (responsePost != null) {
			try {
				getArray = responsePost.getJSONArray("LST_PRODUCT_SETUP");

				int p = getArray.length();
				if (p > 0) {
					for (int i = 0; i < getArray.length(); i++) {
						JSONObject jsoObject = getArray.getJSONObject(i);

						int PSET_ID = jsoObject.optInt("PSET_ID");
						String ID_TICKET = jsoObject.optString("ID_TICKET");
						String SK = jsoObject.optString("SK");
						int JENIS_APP = jsoObject.optInt("JENIS_APP");
						int PRODUCT_CODE = jsoObject.optInt("PRODUCT_CODE");
						int PLAN = jsoObject.optInt("PLAN");
						// Date EFFECTIVE_FROM = (Date)
						// jsoObject.opt("EFFECTIVE_FROM");
						// Date EFFECTIVE_TO = (Date)
						// jsoObject.opt("EFFECTIVE_TO");
						int FLAG_ACTIVE = jsoObject.optInt("FLAG_ACTIVE");
						// Date DATE_ACTIVE = (Date)
						// jsoObject.opt("DATE_ACTIVE");
						int INSURED_PERIOD_FLAG = jsoObject
								.optInt("INSURED_PERIOD_FLAG");
						int INSURED_PERIOD = jsoObject.optInt("INSURED_PERIOD");
						String PAY_PERIOD = jsoObject.optString("PAY_PERIOD");
						int HOLDER_AGE_FROM = jsoObject
								.optInt("HOLDER_AGE_FROM");
						int HOLDER_AGE_TO = jsoObject.optInt("HOLDER_AGE_TO");
						int INSURED_AGE_FROM_FLAG = jsoObject
								.optInt("INSURED_AGE_FROM_FLAG");
						int INSURED_AGE_FROM = jsoObject
								.optInt("INSURED_AGE_FROM");
						int INSURED_AGE_TO = jsoObject.optInt("INSURED_AGE_TO");

						Lst_product_setup lst_product_setup = new Lst_product_setup();
						lst_product_setup.setPset_id(PSET_ID);
						lst_product_setup.setId_ticket(ID_TICKET);
						lst_product_setup.setSk(SK);
						lst_product_setup.setJenis_app(JENIS_APP);
						lst_product_setup.setProduct_code(PRODUCT_CODE);
						lst_product_setup.setPlan(PLAN);
						// lst_product_setup.setEffective_from(EFFECTIVE_FROM);
						// lst_product_setup.setEffective_to(EFFECTIVE_TO);
						lst_product_setup.setFlag_active(FLAG_ACTIVE);
						// lst_product_setup.setDate_active(DATE_ACTIVE);
						lst_product_setup
								.setInsured_period_flag(INSURED_PERIOD_FLAG);
						lst_product_setup.setInsured_period(INSURED_PERIOD);
						if (!StringUtil.isEmpty(PAY_PERIOD))
							lst_product_setup.setPay_period(Integer
									.valueOf(PAY_PERIOD));
						lst_product_setup.setInsured_age_from(INSURED_AGE_FROM);
						lst_product_setup.setInsured_age_to(INSURED_AGE_TO);
						lst_product_setup.setHolder_age_from(HOLDER_AGE_FROM);
						lst_product_setup.setHolder_age_to(HOLDER_AGE_TO);
						lst_product_setup
								.setInsured_age_from_flag(INSURED_AGE_FROM_FLAG);

						cepr01030102Form.getProduct_setup()
								.setLst_product_setup(lst_product_setup);
					}
				} else {
					// dibiarkan
					// cepr01030102Form.getProduct_setup().setLst_product_setup(null);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				logger.error("ERROR", e);
			}
		}
	}

	public void setLstProductCalc(String url) {
		JSONArray getArray = new JSONArray();
		JSONObject responsePost = getPostEws(url);
		if (responsePost != null) {
			try {
				getArray = responsePost.getJSONArray("LST_PRODUCT_CALC");

				int p = getArray.length();
				if (p > 0) {
					for (int i = 0; i < getArray.length(); i++) {
						JSONObject jsoObject = getArray.getJSONObject(i);

						int PSET_ID = jsoObject.optInt("PSET_ID");
						String LKU_ID = jsoObject.optString("LKU_ID");
						int LSCB_ID = jsoObject.optInt("LSCB_ID");
						double FACTOR = jsoObject.optDouble("FACTOR");
						double PREMIUM = jsoObject.optDouble("PREMIUM");
						double UP = jsoObject.optDouble("UP");
						String F_PREMIUM = jsoObject.optString("F_PREMIUM");
						String F_UP = jsoObject.optString("F_UP");
						double MIN_PREMIUM = jsoObject.optDouble("MIN_PREMIUM");
						double MAX_PREMIUM = jsoObject.optDouble("MAX_PREMIUM");
						String F_MIN_PREMIUM = jsoObject
								.optString("F_MIN_PREMIUM");
						String F_MAX_PREMIUM = jsoObject
								.optString("F_MAX_PREMIUM");
						double MIN_UP = jsoObject.optDouble("MIN_UP");
						double MAX_UP = jsoObject.optDouble("MAX_UP");
						String F_MIN_UP = jsoObject.optString("F_MIN_UP");
						String F_MAX_UP = jsoObject.optString("F_MAX_UP");
						int LI_KALI = jsoObject.optInt("LI_KALI");
						String F_COI_RIDER = jsoObject.optString("F_COI_RIDER");
						String F_COI_RIDER_TAMB = jsoObject
								.optString("F_COI_RIDER_TAMB");
						int FLAG_AGE_VALIDATION = jsoObject
								.optInt("FLAG_AGE_VALIDATION");
						int FLAG_INVEST_FUND = jsoObject
								.optInt("FLAG_INVEST_FUND");

						Lst_product_calc lst_product_calc = new Lst_product_calc();
						lst_product_calc.setPset_id(PSET_ID);
						lst_product_calc.setLku_id(LKU_ID);
						lst_product_calc.setLscb_id(LSCB_ID);
						lst_product_calc.setFactor(FACTOR);
						lst_product_calc.setPremium(PREMIUM);
						lst_product_calc.setUp(UP);
						lst_product_calc.setF_premium(F_PREMIUM);
						lst_product_calc.setF_up(F_UP);
						lst_product_calc.setMin_premium(MIN_PREMIUM);
						lst_product_calc.setMax_premium(MAX_PREMIUM);
						lst_product_calc.setF_min_premium(F_MIN_PREMIUM);
						lst_product_calc.setF_max_premium(F_MAX_PREMIUM);
						lst_product_calc.setMin_up(MIN_UP);
						lst_product_calc.setMax_up(MAX_UP);
						lst_product_calc.setF_min_up(F_MIN_UP);
						lst_product_calc.setF_max_up(F_MAX_UP);
						lst_product_calc.setLi_kali(LI_KALI);
						lst_product_calc.setF_coi_rider(F_COI_RIDER);
						lst_product_calc.setF_coi_rider_tamb(F_COI_RIDER_TAMB);
						lst_product_calc
								.setFlag_age_validation(FLAG_AGE_VALIDATION);
						lst_product_calc.setFlag_invest_fund(FLAG_INVEST_FUND);

						cepr01030102Form.getProduct_setup()
								.setLst_product_calc(lst_product_calc);
					}
				} else {
					cepr01030102Form.getProduct_setup().setLst_product_calc(
							null);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				logger.error("ERROR", e);
			}
		}
	}

	public void setLstPremiumComb(String url) {
		JSONArray getArray = new JSONArray();
		JSONObject responsePost = getPostEws(url);
		if (responsePost != null) {
			try {
				getArray = responsePost.getJSONArray("LST_PREMIUM_COMB");

				int p = getArray.length();
				if (p > 0) {
					for (int i = 0; i < getArray.length(); i++) {
						JSONObject jsoObject = getArray.getJSONObject(i);

						int LPP_ID = jsoObject.optInt("LPP_ID");
						String COMB_NAME = jsoObject.optString("COMB_NAME");
						int BASE_PREMIUM = jsoObject.optInt("BASE_PREMIUM");
						int TOPUP_PREMIUM = jsoObject.optInt("TOPUP_PREMIUM");

						Lst_premium_comb lst_premium_comb = new Lst_premium_comb();
						lst_premium_comb.setLpp_id(LPP_ID);
						lst_premium_comb.setComb_name(COMB_NAME);
						lst_premium_comb.setBase_premium(BASE_PREMIUM);
						lst_premium_comb.setTopup_premium(TOPUP_PREMIUM);

						cepr01030102Form.getProduct_setup()
								.getPremiumCombList().add(lst_premium_comb);
					}
				} else {
					cepr01030102Form.getProduct_setup()
							.setPremiumCombList(null);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				logger.error("ERROR", e);
			}
		}
	}

	public void setRiderDisplayList(String url) {
		JSONArray getArray = new JSONArray();
		JSONObject responsePost = getPostEws(url);
		if (responsePost != null) {
			try {
				getArray = responsePost.getJSONArray("GENERATERIDERLIST");

				int p = getArray.length();
				if (p > 0) {
					for (int i = 0; i < getArray.length(); i++) {
						JSONObject jsoObject = getArray.getJSONObject(i);

						int ID_ELIGIBLE = jsoObject.optInt("ID_ELIGIBLE");
						int RIDER_ID = jsoObject.optInt("RIDER_ID");
						String LSBS_NAME = jsoObject.optString("LSBS_NAME");

						ArrayList<HashMap<String, Object>> RIDERDETAILLIST = new ArrayList<HashMap<String, Object>>();
						JSONArray jsonRiderArray = jsoObject
								.optJSONArray("RIDERDETAILLIST");
						if (jsonRiderArray != null) {
							int riderlen = jsonRiderArray.length();
							for (int j = 0; j < riderlen; j++) {
								JSONObject jsoRiderObject = jsonRiderArray
										.getJSONObject(j);
								HashMap<String, Object> riderMap = new HashMap<String, Object>();
								riderMap.put("rider_number",
										jsoRiderObject.optInt("RIDER_NUMBER"));
								riderMap.put("rider_name",
										jsoRiderObject.optString("RIDER_NAME"));
								RIDERDETAILLIST.add(riderMap);
							}
						}

						RiderDisplay riderDisplay = new RiderDisplay();
						riderDisplay.setId_eligible(ID_ELIGIBLE);
						riderDisplay.setRider_id(RIDER_ID);
						riderDisplay.setLsbs_name(LSBS_NAME);
						riderDisplay.setRiderDetailList(RIDERDETAILLIST);

						cepr01030102Form.getProduct_setup()
								.getRiderDisplayList().add(riderDisplay);
					}
				} else {
					cepr01030102Form.getProduct_setup().setRiderDisplayList(
							null);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				logger.error("ERROR", e);
			}
		}
	}

	public void setRiderList(String url) {
		JSONArray getArray = new JSONArray();
		JSONObject responsePost = getPostEws(url);
		if (responsePost != null) {
			try {
				getArray = responsePost.getJSONArray("LST_BISNIS_RIDER");

				int p = getArray.length();
				if (p > 0) {
					for (int i = 0; i < getArray.length(); i++) {
						JSONObject jsoObject = getArray.getJSONObject(i);
						int LSBS_ID = jsoObject.optInt("LSBS_ID");
						int LSDBS_NUMBER = jsoObject.optInt("LSDBS_NUMBER");
						int RIDER_ID = jsoObject.optInt("RIDER_ID");
						int RIDER_NUMBER = jsoObject.optInt("RIDER_NUMBER");
						int FLAG_ACTIVE = jsoObject.optInt("FLAG_ACTIVE");
						int FLAG_ACTIVE_PROPOSAL = jsoObject
								.optInt("FLAG_ACTIVE_PROPOSAL");
						String LKU_ID = jsoObject.optString("LKU_ID");

						Lst_bisnis_rider lst_bisnis_rider = new Lst_bisnis_rider();
						lst_bisnis_rider.setLsbs_id(LSBS_ID);
						lst_bisnis_rider.setLsdbs_number(LSDBS_NUMBER);
						lst_bisnis_rider.setRider_id(RIDER_ID);
						lst_bisnis_rider.setRider_number(RIDER_NUMBER);
						lst_bisnis_rider.setFlag_active(FLAG_ACTIVE);
						lst_bisnis_rider
								.setFlag_active_proposal(FLAG_ACTIVE_PROPOSAL);
						lst_bisnis_rider.setLku_id(LKU_ID);

						cepr01030102Form.getProduct_setup().getRiderList()
								.add(lst_bisnis_rider);
					}
				} else {
					cepr01030102Form.getProduct_setup().getRiderList()
							.add(null);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				logger.error("ERROR", e);
			}
		}
	}

	public void setKursList(String url) {
		JSONArray getArray = new JSONArray();
		JSONObject responsePost = getPostEws(url);
		if (responsePost != null) {
			try {
				getArray = responsePost.getJSONArray("OPTION_LST_KURS");

				int p = getArray.length();
				if (p > 0) {
					for (int i = 0; i < getArray.length(); i++) {
						JSONObject jsoObject = getArray.getJSONObject(i);
						String LKU_ID = jsoObject.optString("LKU_ID");
						int LSNE_ID = jsoObject.optInt("LSNE_ID");
						String LKU_SYMBOL = jsoObject.optString("LKU_SYMBOL");

						Lst_kurs lst_kurs = new Lst_kurs();
						lst_kurs.setLku_id(LKU_ID);
						lst_kurs.setLsne_id(LSNE_ID);
						lst_kurs.setLku_symbol(LKU_SYMBOL);

						cepr01030102Form.getProduct_setup().getKursList()
								.add(lst_kurs);
					}
				} else {
					cepr01030102Form.getProduct_setup().getKursList().add(null);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				logger.error("ERROR", e);
			}
		}
	}

	public void setPayModeList(String url) {
		JSONArray getArray = new JSONArray();
		JSONObject responsePost = getPostEws(url);
		if (responsePost != null) {
			try {
				getArray = responsePost.getJSONArray("OPTION_LST_PAY_MODE");

				int p = getArray.length();
				if (p > 0) {
					for (int i = 0; i < getArray.length(); i++) {
						JSONObject jsoObject = getArray.getJSONObject(i);
						int LSCB_ID = jsoObject.optInt("LSCB_ID");
						String LSCB_PAY_MODE = jsoObject
								.optString("LSCB_PAY_MODE");
						int LSCB_TTL_MONTH = jsoObject.optInt("LSCB_TTL_MONTH");
						String LSCB_PRINT = jsoObject.optString("LSCB_PRINT");
						int LSCB_KALI = jsoObject.optInt("LSCB_KALI");

						Lst_pay_mode lst_pay_mode = new Lst_pay_mode();
						lst_pay_mode.setLscb_id(LSCB_ID);
						lst_pay_mode.setLscb_pay_mode(LSCB_PAY_MODE);
						lst_pay_mode.setLscb_ttl_month(LSCB_TTL_MONTH);
						lst_pay_mode.setLscb_print(LSCB_PRINT);
						lst_pay_mode.setLscb_kali(LSCB_KALI);

						cepr01030102Form.getProduct_setup().getPayModeList()
								.add(lst_pay_mode);
					}
				} else {
					cepr01030102Form.getProduct_setup().getPayModeList()
							.add(null);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				logger.error("ERROR", e);
			}
		}
	}

	public void setJenisInvestList(String url) {
		JSONArray getArray = new JSONArray();
		JSONObject responsePost = getPostEws(url);
		if (responsePost != null) {
			try {
				getArray = responsePost.getJSONArray("LST_PRODUCT_INVEST");

				int p = getArray.length();
				if (p > 0) {
					for (int i = 0; i < getArray.length(); i++) {
						JSONObject jsoObject = getArray.getJSONObject(i);

						String LJI_ID = jsoObject.optString("LJI_ID");
						String LJI_INVEST = jsoObject.optString("LJI_INVEST");
						String LKU_ID = jsoObject.optString("LKU_ID");
						int LJI_GRUP = jsoObject.optInt("LJI_GRUP");
						String JENIS = jsoObject.optString("JENIS");

						Lst_product_invest lst_product_invest = new Lst_product_invest();
						lst_product_invest.setLji_id(LJI_ID);
						lst_product_invest.setLji_invest(LJI_INVEST);
						lst_product_invest.setLku_id(LKU_ID);
						lst_product_invest.setLji_grup(LJI_GRUP);
						lst_product_invest.setJenis(JENIS);

						cepr01030102Form.getProduct_setup()
								.getJenisInvestList().add(lst_product_invest);
					}
				} else {
					cepr01030102Form.getProduct_setup().getJenisInvestList()
							.add(null);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				logger.error("ERROR", e);
			}
		}
	}

	public void setLstProductAcquisition(String url) {
		JSONArray getArray = new JSONArray();
		JSONObject responsePost = getPostEws(url);
		if (responsePost != null) {
			try {
				getArray = responsePost.getJSONArray("LST_PRODUCT_ACQUISITION");

				int p = getArray.length();
				if (p > 0) {
					for (int i = 0; i < getArray.length(); i++) {
						JSONObject jsoObject = getArray.getJSONObject(i);

						int TAHUN_KE = jsoObject.optInt("TAHUN_KE");
						double RATE = jsoObject.optDouble("RATE");

						Lst_product_acquisition lst_product_acquisition = new Lst_product_acquisition();
						lst_product_acquisition.setTahun_ke(TAHUN_KE);
						lst_product_acquisition.setRate(RATE);

						cepr01030102Form.getProduct_setup()
								.getProductAcquisitionList()
								.add(lst_product_acquisition);
					}
				} else {
					cepr01030102Form.getProduct_setup()
							.getProductAcquisitionList().add(null);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				logger.error("ERROR", e);
			}
		}
	}

	public void set_product_setup() {
		cepr01030102Form.setProduct_setup(new Product_setup());
		setLstProductSetup(ews_path_lst_product_setup);
		setLstProductCalc(ews_path_lst_product_calc);
		setLstPremiumComb(ews_path_lst_premium_comb);
		setRiderList(ews_path_lst_bisnis_rider);
		setRiderDisplayList(ews_path_rider_display_list);
		setKursList(ews_path_option_lst_kurs);
		setPayModeList(ews_path_option_lst_pay_mode);
		setJenisInvestList(ews_path_lst_product_invest);
		setLstProductAcquisition(ews_path_lst_product_acquisition);
	}

	public CeprProductSetupValidator(EproposalManager eproposalManager,
			Cepr00000000EditorUtil editorUtil, Object command, Errors errors) {
		super(eproposalManager, editorUtil, command, errors);
		// set_product_setup();
	}

}