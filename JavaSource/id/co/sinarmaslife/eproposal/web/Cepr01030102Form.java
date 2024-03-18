package id.co.sinarmaslife.eproposal.web;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01030102Form
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 22, 2007 11:54:15 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *  1.0                                     Samuel              formbean for proposal detail
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.standard.model.vo.AssurancePlanList;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.ListUtil;
import id.co.sinarmaslife.eproposal.model.setup.Product_setup;
import id.co.sinarmaslife.eproposal.model.setup.sub.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cepr01030102Form implements Serializable
{


	public List<OptionVO> getLjiExcellinkEquityList() {
		return ljiExcellinkEquityList;
	}

	public void setLjiExcellinkEquityList(List<OptionVO> ljiExcellinkEquityList) {
		this.ljiExcellinkEquityList = ljiExcellinkEquityList;
	}

	public String getLjiExcellinkEquityDisplay() {
		return ljiExcellinkEquityDisplay;
	}

	public void setLjiExcellinkEquityDisplay(String ljiExcellinkEquityDisplay) {
		this.ljiExcellinkEquityDisplay = ljiExcellinkEquityDisplay;
	}

	public String getLjiExcellinkEquityCd() {
		return ljiExcellinkEquityCd;
	}

	public void setLjiExcellinkEquityCd(String ljiExcellinkEquityCd) {
		this.ljiExcellinkEquityCd = ljiExcellinkEquityCd;
	}

	public String getLjiExcellinkEquityIsDisabled() {
		return ljiExcellinkEquityIsDisabled;
	}

	public void setLjiExcellinkEquityIsDisabled(String ljiExcellinkEquityIsDisabled) {
		this.ljiExcellinkEquityIsDisabled = ljiExcellinkEquityIsDisabled;
	}

	public BigDecimal getExcellinkEquityIdr() {
		return excellinkEquityIdr;
	}

	public void setExcellinkEquityIdr(BigDecimal excellinkEquityIdr) {
		this.excellinkEquityIdr = excellinkEquityIdr;
	}

	public BigDecimal getExcellinkEquityIdrCd() {
		return excellinkEquityIdrCd;
	}

	public void setExcellinkEquityIdrCd(BigDecimal excellinkEquityIdrCd) {
		this.excellinkEquityIdrCd = excellinkEquityIdrCd;
	}

	public ArrayList<OptionVO> getExcellinkEquityIdrList() {
		return excellinkEquityIdrList;
	}

	public void setExcellinkEquityIdrList(List<OptionVO> excellinkEquityIdrList) {
		this.excellinkEquityIdrList = ListUtil.serializableList(excellinkEquityIdrList);
	}
	
	public String getExcellinkEquityIdrDisplay() {
		return excellinkEquityIdrDisplay;
	}

	public void setExcellinkEquityIdrDisplay(String excellinkEquityIdrDisplay) {
		this.excellinkEquityIdrDisplay = excellinkEquityIdrDisplay;
	}

	public String getExcellinkEquityListDisplay() {
		return excellinkEquityListDisplay;
	}

	public void setExcellinkEquityListDisplay(String excellinkEquityListDisplay) {
		this.excellinkEquityListDisplay = excellinkEquityListDisplay;
	}

	public String getExcellinkEquityIdrListIsDisabled() {
		return excellinkEquityIdrListIsDisabled;
	}

	public void setExcellinkEquityIdrListIsDisabled(
			String excellinkEquityIdrListIsDisabled) {
		this.excellinkEquityIdrListIsDisabled = excellinkEquityIdrListIsDisabled;
	}

	public String getExcellinkEquityIsDisabled() {
		return excellinkEquityIsDisabled;
	}

	public void setExcellinkEquityIsDisabled(String excellinkEquityIsDisabled) {
		this.excellinkEquityIsDisabled = excellinkEquityIsDisabled;
	}

	/**********************************************************************
	 * Program History
	 *
	 * Project Name      	: E-Proposal
	 * Function Id         	: id.co.sinarmaslife.eproposal.web
	 * Program Name   		: 
	 * Description         	:
	 * Environment      	: Java  1.5.0_06
	 * Author               : Fadly Mathendra
	 * Version              : 1.0
	 * Creation Date    	: Nov 22, 2012 10:58:41 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = 3543545387966976942L;
	private String assurancePlanCd;
    private int leftPartOfBusinessCd;
    private int rightPartOfBusinessCd;
    private Integer bisnisNoPbVersion;
    private Integer tempInsuredAge;
    private String currencyCd;
    private BigDecimal totalSumInsured;
    private BigDecimal totalSumInsuredCd;
    private BigDecimal totalSumInsuredFix;
    private BigDecimal premium;
    private BigDecimal premiumCd;
    private BigDecimal basePremium;
    private BigDecimal topUpPremium;
    private Integer paymentModeCd;
    private Integer smileLadiesPackageCd;
    private Integer secondPackageCd;
    private Integer educationPackageCd;
    private Integer protectorPackageCd;
    private Integer providerTypeCd;
    private Integer smilePensionPackageCd;
    private Integer specialOfferCd;
    private Integer termOfContract;
    private Integer termOfPayment;
    private String flagOfVIP; //CR#: NCR/2020/08/019 FLAG VIP (IGA)
    private BigDecimal investRateInPercent;
    private String termRiderFlag;
    private String personalAccidentFlag;
    private String criticalIllnessFlag;
    private String tpdFlag;
    private String payorsClauseFlag;
    private String waiverPremiumDisabilityFlag;
    private Integer clazzCd;
    private Integer termRiderUnitQtyCd;
    private Integer personalAccidentUnitQtyCd;
    private Integer mgiCd;
    private Integer mtiCd;
    private Integer jenisCd;
    private String targetCd;
    private String taxBeforeAfterCd;
    private BigDecimal premiumCombinationCd;
    private BigDecimal fixIdr;
    private BigDecimal fixIdrCd;
    private BigDecimal dynamicIdr;
    private BigDecimal dynamicIdrCd;
    private BigDecimal aggresiveIdr;
    private BigDecimal aggresiveIdrCd;
    private BigDecimal cashFundIdr;
    private BigDecimal cashFundIdrCd; 
    private BigDecimal excellinkEquityIdr; 
    private BigDecimal excellinkEquityIdrCd;
    
    private BigDecimal secureUsd;
    private BigDecimal secureUsdCd;
    private BigDecimal dynamicUsd;
    private BigDecimal dynamicUsdCd;
    private BigDecimal aggresiveUsd; /**USD Fund BSIM Products**/
    private BigDecimal aggresiveUsdCd; /**USD Fund BSIM Products**/
    private Integer premiumFurloughYear;
    private Integer premiumFurloughYearCd;
    private Integer packetCd;
    private Integer tempPremium;
    private BigDecimal extraPremium;
    private BigDecimal extraPremiumCd;
    private BigDecimal extraJob;
    private String ljiFixCd;
    private String ljiDynamicCd;
    private String ljiAggresiveCd;
    private String ljiCashFundCd;
    private String ljiExcellinkEquityCd;
    private String ljiSecureUsdCd;
    private String ljiDynamicUsdCd;
    private String ljiAggresiveUsdCd; /**USD Fund BSIM Products**/
    
    private ArrayList<AssurancePlanList> assurancePlanList;
    private ArrayList<OptionVO> totalSumInsuredList;
    private ArrayList<OptionVO> premiumList;
    private ArrayList<OptionVO> currencyList;
    private ArrayList<OptionVO> paymentModeList;
    private ArrayList<OptionVO> smileLadiesPackageList;
    private ArrayList<OptionVO> secondPackageList;
    private ArrayList<OptionVO> educationPackageList;
    private ArrayList<OptionVO> protectorPackageList;
    private ArrayList<OptionVO> providerTypeList;
    private ArrayList<OptionVO> smilePensionPackageList;
    private ArrayList<OptionVO> specialOfferList;
    private ArrayList<OptionVO> clazzList;
    private ArrayList<OptionVO> termRiderUnitQtyList;
    private ArrayList<OptionVO> personalAccidentUnitQtyList;
    private ArrayList<OptionVO> mgiList;
    private ArrayList<OptionVO> mtiList;
    private ArrayList<OptionVO> jenisList;
    private ArrayList<OptionVO> targetList;
    private ArrayList<OptionVO> taxBeforeAfterList;
    private ArrayList<OptionVO> premiumCombinationList;
    private ArrayList<OptionVO> fixIdrList;
    private ArrayList<OptionVO> dynamicIdrList;
    private ArrayList<OptionVO> aggresiveIdrList;
    private ArrayList<OptionVO> cashFundIdrList;
    private ArrayList<OptionVO> excellinkEquityIdrList;
    private ArrayList<OptionVO> secureUsdList;
    private ArrayList<OptionVO> dynamicUsdList;
    private ArrayList<OptionVO> aggresiveUsdList;
    private ArrayList<OptionVO> premiumFurloughYearList;
    private ArrayList<OptionVO> packetList;
    private List<OptionVO> extraPremiumList;
    private List<OptionVO> ljiFixList;
    private List<OptionVO> ljiDynamicList;
    private List<OptionVO> ljiAggresiveList;
    private List<OptionVO> ljiCashFundList;
    private List<OptionVO> ljiExcellinkEquityList;
    private List<OptionVO> ljiSecureUsdList;
    private List<OptionVO> ljiDynamicUsdList;
    private List<OptionVO> ljiAggresiveUsdList; /**USD Fund BSIM Products**/
    
    private String totalSumInsuredDisplay;
    private String premiumDisplay;
    private String basePremiumDisplay;
    private String topUpPremiumDisplay;
    private String totalSumInsuredListDisplay;
    private String premiumListDisplay;
    private String termOfContractDisplay;
    private String termOfPaymentDisplay;
    private String flagOfVIPDisplay; //CR#: NCR/2020/08/019 FLAG VIP (IGA)
    private String investRateInPercentDisplay;
    private String termRiderFlagDisplay;
    private String personalAccidentFlagDisplay;
    private String criticalIllnessFlagDisplay;
    private String tpdFlagDisplay;
    private String payorsClauseFlagDisplay;
    private String waiverPremiumDisabilityFlagDisplay;
    private String termRiderUnitQtyListDisplay;
    private String personalAccidentUnitQtyListDisplay;
    private String assurancePlanListDisplay;
    private String currencyListDisplay;
    private String paymentModeListDisplay;
    private String smileLadiesPackageListDisplay;
    private String secondPackageListDisplay;
    private String educationPackageListDisplay;
    private String protectorPackageListDisplay;
    private String providerTypeListDisplay;
    private String smilePensionPackageListDisplay;
    private String specialOfferListDisplay;
    private String clazzListDisplay;
    private String mgiListDisplay;
    private String mtiListDisplay;
    private String jenisListDisplay;
    private String targetListDisplay;
    private String taxBeforeAfterListDisplay;
    private String premiumCombinationListDisplay;
    private String fixIdrDisplay;
    private String dynamicIdrDisplay;
    private String aggresiveIdrDisplay;
    private String cashFundIdrDisplay;
    private String excellinkEquityIdrDisplay;
    private String secureUsdDisplay;
    private String dynamicUsdDisplay;
    private String aggresiveUsdDisplay; /**USD Fund BSIM Products**/
    private String fixIdrListDisplay;
    private String dynamicIdrListDisplay;
    private String aggresiveIdrListDisplay;
    private String cashFundIdrListDisplay;
    private String excellinkEquityListDisplay;
    private String secureUsdListDisplay;
    private String dynamicUsdListDisplay;
    private String aggresiveUsdListDisplay;
    private String premiumFurloughYearDisplay;
    private String premiumFurloughYearListDisplay;
    private String premiumFurloughYearListDisplayHelperDisplay;
    private String packetListDisplay;
    private String pesertaEkaSehatStandAloneDisplay;
    private String extraPremiumDisplay;
    private String extraPremiumListDisplay;
    private String extraJobDisplay;
    private String ljiFixListDisplay;
    private String ljiDynamicListDisplay;
    private String ljiAggresiveListDisplay;
    private String ljiCashFundDisplay;
    private String ljiExcellinkEquityDisplay;
    private String ljiSecureUsdListDisplay;
    private String ljiDynamicUsdListDisplay;
    private String ljiAggresiveUsdListDisplay; /**USD Fund BSIM Products**/
         
    private String buttonCountTotalSumInsuredDisplay;
    private String buttonCountPremiumDisplay;
    private String buttonTopupWithdrawDisplay;
    private String buttonRiderAndButtonTopupDisplay;
    private String buttonRiderDisplay;
    private String buttonTopupDisplay;
    private String buttonViewProposalDisplay;

    private String headerAdditionalAssuranceDisplay;
    private String headerInvestChoiceDisplay;
    
    private String totalSumInsuredIsDisabled;
    private String premiumIsDisabled;
    private String totalSumInsuredListIsDisabled;
    private String premiumListIsDisabled;
    private String termOfContractIsDisabled;
    private String termOfPaymentIsDisabled;
    private String flagOfVIPIsDisabled; //CR#: NCR/2020/08/019 FLAG VIP (IGA)
    private String investRateInPercentIsDisabled;
    private String termRiderFlagIsDisabled;
    private String personalAccidentFlagIsDisabled;
    private String criticalIllnessFlagIsDisabled;
    private String tpdFlagIsDisabled;
    private String payorsClauseFlagIsDisabled;
    private String waiverPremiumDisabilityFlagIsDisabled;
    private String termRiderUnitQtyListIsDisabled;
    private String personalAccidentUnitQtyListIsDisabled;
    private String assurancePlanListIsDisabled;
    private String currencyListIsDisabled;
    private String paymentModeListIsDisabled;
    private String smileLadiesPackageListIsDisabled;
    private String secondPackageListIsDisabled;
    private String educationPackageListIsDisabled;
    private String protectorPackageListIsDisabled;
    private String providerTypeListIsDisabled;
    private String smilePensionPackageListIsDisabled;
    private String specialOfferListIsDisabled;
    private String clazzListIsDisabled;
    private String mgiListIsDisabled;
    private String mtiListIsDisabled;
    private String jenisListIsDisabled;
    private String targetListIsDisabled;
    private String taxBeforeAfterListIsDisabled;
    private String premiumCombinationListIsDisabled;
    private String fixIdrListIsDisabled;
    private String dynamicIdrListIsDisabled;
    private String aggresiveIdrListIsDisabled;
    private String cashFundIdrListIsDisabled;
    private String excellinkEquityIdrListIsDisabled;
    private String secureUsdListIsDisabled;
    private String dynamicUsdListIsDisabled;
    private String aggresiveUsdListIsDisabled;/**USD Fund BSIM Products**/
    private String fixIdrIsDisabled;
    private String dynamicIdrIsDisabled;
    private String aggresiveIdrIsDisabled;
    private String cashFundIdrIsDisabled;
    private String excellinkEquityIsDisabled;
    private String secureUsdIsDisabled;
    private String dynamicUsdIsDisabled;
    private String aggresiveUsdIsDisabled; /**USD Fund BSIM Products**/
    private String premiumFurloughYearIsDisabled;
    private String premiumFurloughYearListIsDisabled;
    private String extraPremiumIsDisabled;
    private String extraPremiumListIsDisabled;  
    private String extraJobIsDisabled;
    private String ljiFixListIsDisabled;
    private String ljiDynamicListIsDisabled;
    private String ljiAggresiveListIsDisabled;
    private String ljiCashFundListIsDisabled;
    private String ljiExcellinkEquityIsDisabled;
    private String ljiSecureUsdListIsDisabled;
    private String ljiDynamicUsdListIsDisabled;
    private String ljiAggresiveUsdListIsDisabled;
    
    private String premiumReadOnly;
    private String totalSumInsuredReadOnly;

    private String downloadFlag;
    private String prevAssurancePlanCd;
    private String prevAssurancePlanPackageCd;

    private String totalSumInsuredOptionIsToRefreshPage;
    private String premiumOptionIsToRefreshPage;
    private String theEvent;
    
    private String ekaSehatFlag;
    private Integer ekaSehatCd;
    private ArrayList<OptionVO> ekaSehatList;
    private String ekaSehatFlagIsDisabled;
    private String ekaSehatListIsDisabled;
    private String buttonParticipantEkaSehatIsDisabled;    
    private String ekaSehatFlagDisplay;
    private String emailDisplay;
    private String faxDisplay;
    private ArrayList< Mst_proposal > mstProposal;
      
    private List<lst_premium_combination> lst_premium_combination;
    
    private String medicalPlusDisplay;   
    private String medicalPlusRgFlag;
    private String medicalPlusRbFlag;
    private String medicalPlusPkFlag;       
    private String pesertaMedicalPlusStandAloneDisplay;
    private String pesertaHcpProviderDisplayStandAloneDisplay;
    
    private String babyDisplay;
    private String babyFlag;
    private String babyFlagIsDisabled;
    private String babyChooseCd;
    private ArrayList<OptionVO> babyChooseList;
    private String babyCd;
    private ArrayList<OptionVO> babyList;
    private String basePremiumReadOnly;
    
    //PrimeLink Fund
    private String ljiFixSimasListDisplay;
    private String ljiDynamicSimasListDisplay;
    private String ljiAggresiveSimasListDisplay;
    private String ljiFixSimasCd;
    private String ljiDynamicSimasCd;
    private String ljiAggresiveSimasCd;
    private List<OptionVO> ljiFixSimasList;
    private List<OptionVO> ljiDynamicSimasList;
    private List<OptionVO> ljiAggresiveSimasList;
    private String ljiFixSimasListIsDisabled;
    private String ljiDynamicSimasListIsDisabled;
    private String ljiAggresiveSimasListIsDisabled;
    private BigDecimal fixSimasIdr;
    private BigDecimal fixSimasIdrCd;
    private BigDecimal dynamicSimasIdr;
    private BigDecimal dynamicSimasIdrCd;
    private BigDecimal aggresiveSimasIdr;
    private BigDecimal aggresiveSimasIdrCd;
    private String fixSimasIdrIsDisabled;
    private String dynamicSimasIdrIsDisabled;
    private String aggresiveSimasIdrIsDisabled;
    private String fixSimasIdrListDisplay;
    private String dynamicSimasIdrListDisplay;
    private String aggresiveSimasIdrListDisplay;
    
    private String fixSimasIdrListIsDisabled;
    private String dynamicSimasIdrListIsDisabled;
    private String aggresiveSimasIdrListIsDisabled;
    private String topUpPremiumIsDisabled;
    
    private Lst_product_setup lst_product_setup;
    private Product_setup product_setup;
    
    public String getEkaSehatFlag() {
		return ekaSehatFlag;
	}

	public void setEkaSehatFlag(String ekaSehatFlag) {
		this.ekaSehatFlag = ekaSehatFlag;
	}

	public Integer getEkaSehatCd() {
		return ekaSehatCd;
	}

	public Integer getSpecialOfferCd() {
		return specialOfferCd;
	}

	public void setSpecialOfferCd(Integer specialOfferCd) {
		this.specialOfferCd = specialOfferCd;
	}

	public ArrayList<OptionVO> getSpecialOfferList() {
		return specialOfferList;
	}

	public void setSpecialOfferList(List<OptionVO> specialOfferList) {
		this.specialOfferList = ListUtil.serializableList(specialOfferList);
	}

	public String getSpecialOfferListDisplay() {
		return specialOfferListDisplay;
	}

	public void setSpecialOfferListDisplay(String specialOfferListDisplay) {
		this.specialOfferListDisplay = specialOfferListDisplay;
	}

	public String getSpecialOfferListIsDisabled() {
		return specialOfferListIsDisabled;
	}

	public void setSpecialOfferListIsDisabled(String specialOfferListIsDisabled) {
		this.specialOfferListIsDisabled = specialOfferListIsDisabled;
	}

	public void setEkaSehatCd(Integer ekaSehatCd) {
		this.ekaSehatCd = ekaSehatCd;
	}

	public ArrayList<OptionVO> getEkaSehatList() {
		return ekaSehatList;
	}

	public void setEkaSehatList(List<OptionVO> ekaSehatList) {
		this.ekaSehatList = ListUtil.serializableList(ekaSehatList);
	}

	public String getEkaSehatFlagIsDisabled() {
		return ekaSehatFlagIsDisabled;
	}

	public void setEkaSehatFlagIsDisabled(String ekaSehatFlagIsDisabled) {
		this.ekaSehatFlagIsDisabled = ekaSehatFlagIsDisabled;
	}

	public String getEkaSehatListIsDisabled() {
		return ekaSehatListIsDisabled;
	}

	public void setEkaSehatListIsDisabled(String ekaSehatListIsDisabled) {
		this.ekaSehatListIsDisabled = ekaSehatListIsDisabled;
	}

	public String getButtonParticipantEkaSehatIsDisabled() {
		return buttonParticipantEkaSehatIsDisabled;
	}

	public void setButtonParticipantEkaSehatIsDisabled(
			String buttonParticipantEkaSehatIsDisabled) {
		this.buttonParticipantEkaSehatIsDisabled = buttonParticipantEkaSehatIsDisabled;
	}

	public String getEkaSehatFlagDisplay() {
		return ekaSehatFlagDisplay;
	}

	public void setEkaSehatFlagDisplay(String ekaSehatFlagDisplay) {
		this.ekaSehatFlagDisplay = ekaSehatFlagDisplay;
	}

	public String getAssurancePlanCd()
    {
        return assurancePlanCd;
    }

    public void setAssurancePlanCd( String assurancePlanCd )
    {
        this.assurancePlanCd = assurancePlanCd;
    }

    public int getLeftPartOfBusinessCd()
    {
        return leftPartOfBusinessCd;
    }

    public void setLeftPartOfBusinessCd( int leftPartOfBusinessCd )
    {
        this.leftPartOfBusinessCd = leftPartOfBusinessCd;
    }

	public int getRightPartOfBusinessCd()
    {
        return rightPartOfBusinessCd;
    }

    public void setRightPartOfBusinessCd( int rightPartOfBusinessCd )
    {
        this.rightPartOfBusinessCd = rightPartOfBusinessCd;
    }

    public Integer getBisnisNoPbVersion()
    {
        return bisnisNoPbVersion;
    }

    public void setBisnisNoPbVersion( Integer bisnisNoPbVersion )
    {
        this.bisnisNoPbVersion = bisnisNoPbVersion;
    }

    public String getCurrencyCd()
    {
        return currencyCd;
    }

    public void setCurrencyCd( String currencyCd )
    {
        this.currencyCd = currencyCd;
    }

    public BigDecimal getTotalSumInsured()
    {
        return totalSumInsured;
    }

    public void setTotalSumInsured( BigDecimal totalSumInsured )
    {
        this.totalSumInsured = totalSumInsured;
    }

    public BigDecimal getTotalSumInsuredCd()
    {
        return totalSumInsuredCd;
    }

    public void setTotalSumInsuredCd( BigDecimal totalSumInsuredCd )
    {
        this.totalSumInsuredCd = totalSumInsuredCd;
    }

    public BigDecimal getPremium()
    {
        return premium;
    }

    public void setPremium( BigDecimal premium )
    {
        this.premium = premium;
    }

    public BigDecimal getPremiumCd()
    {
        return premiumCd;
    }

    public void setPremiumCd( BigDecimal premiumCd )
    {
        this.premiumCd = premiumCd;
    }

    public BigDecimal getBasePremium()
    {
        return basePremium;
    }

    public void setBasePremium( BigDecimal basePremium )
    {
        this.basePremium = basePremium;
    }

    public BigDecimal getTopUpPremium()
    {
        return topUpPremium;
    }

    public void setTopUpPremium( BigDecimal topUpPremium )
    {
        this.topUpPremium = topUpPremium;
    }

    public Integer getPaymentModeCd()
    {
        return paymentModeCd;
    }

    public void setPaymentModeCd( Integer paymentModeCd )
    {
        this.paymentModeCd = paymentModeCd;
    }

    public Integer getTermOfContract()
    {
        return termOfContract;
    }

    public void setTermOfContract( Integer termOfContract )
    {
        this.termOfContract = termOfContract;
    }

    public Integer getTermOfPayment()
    {
        return termOfPayment;
    }

    public void setTermOfPayment( Integer termOfPayment )
    {
        this.termOfPayment = termOfPayment;
    }

    public String getFlagOfVIP() { //-->CR#: NCR/2020/08/019 FLAG VIP (IGA)
		return flagOfVIP;
	}

	public void setFlagOfVIP(String flagOfVIP) {
		this.flagOfVIP = flagOfVIP;
	}
	//<--CR#: NCR/2020/08/019 FLAG VIP (IGA)
	public BigDecimal getInvestRateInPercent()
    {
        return investRateInPercent;
    }

    public void setInvestRateInPercent( BigDecimal investRateInPercent )
    {
        this.investRateInPercent = investRateInPercent;
    }

    public String getTermRiderFlag()
    {
        return termRiderFlag;
    }

    public void setTermRiderFlag( String termRiderFlag )
    {
        this.termRiderFlag = termRiderFlag;
    }

    public String getPersonalAccidentFlag()
    {
        return personalAccidentFlag;
    }

    public void setPersonalAccidentFlag( String personalAccidentFlag )
    {
        this.personalAccidentFlag = personalAccidentFlag;
    }

    public String getCriticalIllnessFlag()
    {
        return criticalIllnessFlag;
    }

    public void setCriticalIllnessFlag( String criticalIllnessFlag )
    {
        this.criticalIllnessFlag = criticalIllnessFlag;
    }

    public String getPayorsClauseFlag()
    {
        return payorsClauseFlag;
    }

    public void setPayorsClauseFlag( String payorsClauseFlag )
    {
        this.payorsClauseFlag = payorsClauseFlag;
    }

    public String getWaiverPremiumDisabilityFlag()
    {
        return waiverPremiumDisabilityFlag;
    }

    public void setWaiverPremiumDisabilityFlag( String waiverPremiumDisabilityFlag )
    {
        this.waiverPremiumDisabilityFlag = waiverPremiumDisabilityFlag;
    }

    public Integer getClazzCd()
    {
        return clazzCd;
    }

    public void setClazzCd( Integer clazzCd )
    {
        this.clazzCd = clazzCd;
    }

    public Integer getTermRiderUnitQtyCd()
    {
        return termRiderUnitQtyCd;
    }

    public void setTermRiderUnitQtyCd( Integer termRiderUnitQtyCd )
    {
        this.termRiderUnitQtyCd = termRiderUnitQtyCd;
    }

    public Integer getPersonalAccidentUnitQtyCd()
    {
        return personalAccidentUnitQtyCd;
    }

    public void setPersonalAccidentUnitQtyCd( Integer personalAccidentUnitQtyCd )
    {
        this.personalAccidentUnitQtyCd = personalAccidentUnitQtyCd;
    }

    public Integer getMgiCd()
    {
        return mgiCd;
    }

    public void setMgiCd( Integer mgiCd )
    {
        this.mgiCd = mgiCd;
    }

    public Integer getJenisCd() {
		return jenisCd;
	}

	public void setJenisCd(Integer jenisCd) {
		this.jenisCd = jenisCd;
	}

	public ArrayList<OptionVO> getJenisList() {
		return jenisList;
	}

	public void setJenisList(List<OptionVO> jenisList) {
		this.jenisList = ListUtil.serializableList(jenisList);
	}

	public String getJenisListDisplay() {
		return jenisListDisplay;
	}

	public void setJenisListDisplay(String jenisListDisplay) {
		this.jenisListDisplay = jenisListDisplay;
	}

	public String getJenisListIsDisabled() {
		return jenisListIsDisabled;
	}

	public void setJenisListIsDisabled(String jenisListIsDisabled) {
		this.jenisListIsDisabled = jenisListIsDisabled;
	}

	public Integer getMtiCd()
    {
        return mtiCd;
    }

    public void setMtiCd( Integer mtiCd )
    {
        this.mtiCd = mtiCd;
    }

    public String getTargetCd()
    {
        return targetCd;
    }

    public void setTargetCd( String targetCd )
    {
        this.targetCd = targetCd;
    }

    public String getTaxBeforeAfterCd()
    {
        return taxBeforeAfterCd;
    }

    public void setTaxBeforeAfterCd( String taxBeforeAfterCd )
    {
        this.taxBeforeAfterCd = taxBeforeAfterCd;
    }

    public BigDecimal getPremiumCombinationCd()
    {
        return premiumCombinationCd;
    }

    public void setPremiumCombinationCd( BigDecimal premiumCombinationCd )
    {
        this.premiumCombinationCd = premiumCombinationCd;
    }

    public BigDecimal getFixIdr()
    {
        return fixIdr;
    }

    public void setFixIdr( BigDecimal fixIdr )
    {
        this.fixIdr = fixIdr;
    }

    public BigDecimal getFixIdrCd()
    {
        return fixIdrCd;
    }

    public void setFixIdrCd( BigDecimal fixIdrCd )
    {
        this.fixIdrCd = fixIdrCd;
    }

    public BigDecimal getDynamicIdr()
    {
        return dynamicIdr;
    }

    public void setDynamicIdr( BigDecimal dynamicIdr )
    {
        this.dynamicIdr = dynamicIdr;
    }

    public BigDecimal getDynamicIdrCd()
    {
        return dynamicIdrCd;
    }

    public void setDynamicIdrCd( BigDecimal dynamicIdrCd )
    {
        this.dynamicIdrCd = dynamicIdrCd;
    }

    public BigDecimal getAggresiveIdr()
    {
        return aggresiveIdr;
    }

    public void setAggresiveIdr( BigDecimal aggresiveIdr )
    {
        this.aggresiveIdr = aggresiveIdr;
    }

    public BigDecimal getAggresiveIdrCd()
    {
        return aggresiveIdrCd;
    }

    public void setAggresiveIdrCd( BigDecimal aggresiveIdrCd )
    {
        this.aggresiveIdrCd = aggresiveIdrCd;
    }

    public BigDecimal getSecureUsd()
    {
        return secureUsd;
    }

    public void setSecureUsd( BigDecimal secureUsd )
    {
        this.secureUsd = secureUsd;
    }

    public BigDecimal getSecureUsdCd()
    {
        return secureUsdCd;
    }

    public void setSecureUsdCd( BigDecimal secureUsdCd )
    {
        this.secureUsdCd = secureUsdCd;
    }

    public BigDecimal getDynamicUsd()
    {
        return dynamicUsd;
    }

    public void setDynamicUsd( BigDecimal dynamicUsd )
    {
        this.dynamicUsd = dynamicUsd;
    }

    public BigDecimal getDynamicUsdCd()
    {
        return dynamicUsdCd;
    }

    public void setDynamicUsdCd( BigDecimal dynamicUsdCd )
    {
        this.dynamicUsdCd = dynamicUsdCd;
    }

    public Integer getPremiumFurloughYear()
    {
        return premiumFurloughYear;
    }

    public void setPremiumFurloughYear( Integer premiumFurloughYear )
    {
        this.premiumFurloughYear = premiumFurloughYear;
    }

    public Integer getPremiumFurloughYearCd()
    {
        return premiumFurloughYearCd;
    }

    public void setPremiumFurloughYearCd( Integer premiumFurloughYearCd )
    {
        this.premiumFurloughYearCd = premiumFurloughYearCd;
    }

    public ArrayList<AssurancePlanList> getAssurancePlanList()
    {
        return assurancePlanList;
    }

    public void setAssurancePlanList( List<AssurancePlanList> assurancePlanList )
    {
        this.assurancePlanList = ListUtil.serializableList(assurancePlanList);
    }

    public ArrayList<OptionVO> getTotalSumInsuredList()
    {
        return totalSumInsuredList;
    }

    public void setTotalSumInsuredList( List<OptionVO> totalSumInsuredList )
    {
        this.totalSumInsuredList = ListUtil.serializableList(totalSumInsuredList);
    }

    public ArrayList<OptionVO> getPremiumList()
    {
        return premiumList;
    }

    public void setPremiumList( List<OptionVO> premiumList )
    {
        this.premiumList = ListUtil.serializableList(premiumList);
    }

    public ArrayList<OptionVO> getCurrencyList()
    {
        return currencyList;
    }

    public void setCurrencyList( List<OptionVO> currencyList )
    {
        this.currencyList = ListUtil.serializableList(currencyList);
    }

    public ArrayList<OptionVO> getPaymentModeList()
    {
        return paymentModeList;
    }

    public void setPaymentModeList( List<OptionVO> paymentModeList )
    {
        this.paymentModeList = ListUtil.serializableList(paymentModeList);
    }

    public ArrayList<OptionVO> getClazzList()
    {
        return clazzList;
    }

    public void setClazzList( List<OptionVO> clazzList )
    {
        this.clazzList = ListUtil.serializableList(clazzList);
    }

    public ArrayList<OptionVO> getTermRiderUnitQtyList()
    {
        return termRiderUnitQtyList;
    }

    public void setTermRiderUnitQtyList( List<OptionVO> termRiderUnitQtyList )
    {
        this.termRiderUnitQtyList = ListUtil.serializableList(termRiderUnitQtyList);
    }

    public ArrayList<OptionVO> getPersonalAccidentUnitQtyList()
    {
        return personalAccidentUnitQtyList;
    }

    public void setPersonalAccidentUnitQtyList( List<OptionVO> personalAccidentUnitQtyList )
    {
        this.personalAccidentUnitQtyList = ListUtil.serializableList(personalAccidentUnitQtyList);
    }

    public ArrayList<OptionVO> getMgiList()
    {
        return mgiList;
    }

    public void setMgiList( List<OptionVO> mgiList )
    {
        this.mgiList = ListUtil.serializableList(mgiList);
    }

    public ArrayList<OptionVO> getMtiList()
    {
        return mtiList;
    }

    public void setMtiList( List<OptionVO> mtiList )
    {
        this.mtiList = ListUtil.serializableList(mtiList);
    }

    public ArrayList<OptionVO> getTargetList()
    {
        return targetList;
    }

    public void setTargetList( List<OptionVO> targetList )
    {
        this.targetList = ListUtil.serializableList(targetList);
    }

    public ArrayList<OptionVO> getTaxBeforeAfterList()
    {
        return taxBeforeAfterList;
    }

    public void setTaxBeforeAfterList( List<OptionVO> taxBeforeAfterList )
    {
        this.taxBeforeAfterList = ListUtil.serializableList(taxBeforeAfterList);
    }

    public ArrayList<OptionVO> getPremiumCombinationList()
    {
        return premiumCombinationList;
    }

    public void setPremiumCombinationList( List<OptionVO> premiumCombinationList )
    {
        this.premiumCombinationList = ListUtil.serializableList(premiumCombinationList);
    }

    public ArrayList<OptionVO> getFixIdrList()
    {
        return fixIdrList;
    }

    public void setFixIdrList( List<OptionVO> fixIdrList )
    {
        this.fixIdrList = ListUtil.serializableList(fixIdrList);
    }

    public ArrayList<OptionVO> getDynamicIdrList()
    {
        return dynamicIdrList;
    }

    public void setDynamicIdrList( List<OptionVO> dynamicIdrList )
    {
        this.dynamicIdrList = ListUtil.serializableList(dynamicIdrList);
    }

    public ArrayList<OptionVO> getAggresiveIdrList()
    {
        return aggresiveIdrList;
    }

    public void setAggresiveIdrList( List<OptionVO> aggresiveIdrList )
    {
        this.aggresiveIdrList = ListUtil.serializableList(aggresiveIdrList);
    }

    public ArrayList<OptionVO> getSecureUsdList()
    {
        return secureUsdList;
    }

    public void setSecureUsdList( List<OptionVO> secureUsdList )
    {
        this.secureUsdList = ListUtil.serializableList(secureUsdList);
    }

    public ArrayList<OptionVO> getDynamicUsdList()
    {
        return dynamicUsdList;
    }

    public void setDynamicUsdList( List<OptionVO> dynamicUsdList )
    {
        this.dynamicUsdList = ListUtil.serializableList(dynamicUsdList);
    }

    public ArrayList<OptionVO> getPremiumFurloughYearList()
    {
        return premiumFurloughYearList;
    }

    public void setPremiumFurloughYearList( List<OptionVO> premiumFurloughYearList )
    {
        this.premiumFurloughYearList = ListUtil.serializableList(premiumFurloughYearList);
    }

    public String getTotalSumInsuredDisplay()
    {
        return totalSumInsuredDisplay;
    }

    public void setTotalSumInsuredDisplay( String totalSumInsuredDisplay )
    {
        this.totalSumInsuredDisplay = totalSumInsuredDisplay;
    }

    public String getPremiumDisplay()
    {
        return premiumDisplay;
    }

    public void setPremiumDisplay( String premiumDisplay )
    {
        this.premiumDisplay = premiumDisplay;
    }

    public String getBasePremiumDisplay()
    {
        return basePremiumDisplay;
    }

    public void setBasePremiumDisplay( String basePremiumDisplay )
    {
        this.basePremiumDisplay = basePremiumDisplay;
    }

    public String getTopUpPremiumDisplay()
    {
        return topUpPremiumDisplay;
    }

    public void setTopUpPremiumDisplay( String topUpPremiumDisplay )
    {
        this.topUpPremiumDisplay = topUpPremiumDisplay;
    }

    public String getTotalSumInsuredListDisplay()
    {
        return totalSumInsuredListDisplay;
    }

    public void setTotalSumInsuredListDisplay( String totalSumInsuredListDisplay )
    {
        this.totalSumInsuredListDisplay = totalSumInsuredListDisplay;
    }

    public String getPremiumListDisplay()
    {
        return premiumListDisplay;
    }

    public void setPremiumListDisplay( String premiumListDisplay )
    {
        this.premiumListDisplay = premiumListDisplay;
    }

    public String getTermOfContractDisplay()
    {
        return termOfContractDisplay;
    }

    public void setTermOfContractDisplay( String termOfContractDisplay )
    {
        this.termOfContractDisplay = termOfContractDisplay;
    }

    public String getTermOfPaymentDisplay()
    {
        return termOfPaymentDisplay;
    }

    public void setTermOfPaymentDisplay( String termOfPaymentDisplay )
    {
        this.termOfPaymentDisplay = termOfPaymentDisplay;
    }
    //CR#: NCR/2020/08/019 FLAG VIP (IGA)
    public String getFlagOfVIPDisplay()
    {
        return flagOfVIPDisplay;
    }

    public void setFlagOfVIPDisplay( String flagOfVIPDisplay)
    {
        this.flagOfVIPDisplay = flagOfVIPDisplay;
    }
    //DONE
    public String getInvestRateInPercentDisplay()
    {
        return investRateInPercentDisplay;
    }

    public void setInvestRateInPercentDisplay( String investRateInPercentDisplay )
    {
        this.investRateInPercentDisplay = investRateInPercentDisplay;
    }

    public String getTermRiderFlagDisplay()
    {
        return termRiderFlagDisplay;
    }

    public void setTermRiderFlagDisplay( String termRiderFlagDisplay )
    {
        this.termRiderFlagDisplay = termRiderFlagDisplay;
    }

    public String getPersonalAccidentFlagDisplay()
    {
        return personalAccidentFlagDisplay;
    }

    public void setPersonalAccidentFlagDisplay( String personalAccidentFlagDisplay )
    {
        this.personalAccidentFlagDisplay = personalAccidentFlagDisplay;
    }

    public String getCriticalIllnessFlagDisplay()
    {
        return criticalIllnessFlagDisplay;
    }

    public void setCriticalIllnessFlagDisplay( String criticalIllnessFlagDisplay )
    {
        this.criticalIllnessFlagDisplay = criticalIllnessFlagDisplay;
    }

    public String getPayorsClauseFlagDisplay()
    {
        return payorsClauseFlagDisplay;
    }

    public void setPayorsClauseFlagDisplay( String payorsClauseFlagDisplay )
    {
        this.payorsClauseFlagDisplay = payorsClauseFlagDisplay;
    }

    public String getWaiverPremiumDisabilityFlagDisplay()
    {
        return waiverPremiumDisabilityFlagDisplay;
    }

    public void setWaiverPremiumDisabilityFlagDisplay( String waiverPremiumDisabilityFlagDisplay )
    {
        this.waiverPremiumDisabilityFlagDisplay = waiverPremiumDisabilityFlagDisplay;
    }

    public String getTermRiderUnitQtyListDisplay()
    {
        return termRiderUnitQtyListDisplay;
    }

    public void setTermRiderUnitQtyListDisplay( String termRiderUnitQtyListDisplay )
    {
        this.termRiderUnitQtyListDisplay = termRiderUnitQtyListDisplay;
    }

    public String getPersonalAccidentUnitQtyListDisplay()
    {
        return personalAccidentUnitQtyListDisplay;
    }

    public void setPersonalAccidentUnitQtyListDisplay( String personalAccidentUnitQtyListDisplay )
    {
        this.personalAccidentUnitQtyListDisplay = personalAccidentUnitQtyListDisplay;
    }

    public String getAssurancePlanListDisplay()
    {
        return assurancePlanListDisplay;
    }

    public void setAssurancePlanListDisplay( String assurancePlanListDisplay )
    {
        this.assurancePlanListDisplay = assurancePlanListDisplay;
    }

    public String getCurrencyListDisplay()
    {
        return currencyListDisplay;
    }

    public void setCurrencyListDisplay( String currencyListDisplay )
    {
        this.currencyListDisplay = currencyListDisplay;
    }

    public String getPaymentModeListDisplay()
    {
        return paymentModeListDisplay;
    }

    public void setPaymentModeListDisplay( String paymentModeListDisplay )
    {
        this.paymentModeListDisplay = paymentModeListDisplay;
    }

    public String getClazzListDisplay()
    {
        return clazzListDisplay;
    }

    public void setClazzListDisplay( String clazzListDisplay )
    {
        this.clazzListDisplay = clazzListDisplay;
    }

    public String getMgiListDisplay()
    {
        return mgiListDisplay;
    }

    public void setMgiListDisplay( String mgiListDisplay )
    {
        this.mgiListDisplay = mgiListDisplay;
    }

    public String getMtiListDisplay()
    {
        return mtiListDisplay;
    }

    public void setMtiListDisplay( String mtiListDisplay )
    {
        this.mtiListDisplay = mtiListDisplay;
    }

    public String getTargetListDisplay()
    {
        return targetListDisplay;
    }

    public void setTargetListDisplay( String targetListDisplay )
    {
        this.targetListDisplay = targetListDisplay;
    }

    public String getTaxBeforeAfterListDisplay()
    {
        return taxBeforeAfterListDisplay;
    }

    public void setTaxBeforeAfterListDisplay( String taxBeforeAfterListDisplay )
    {
        this.taxBeforeAfterListDisplay = taxBeforeAfterListDisplay;
    }

    public String getPremiumCombinationListDisplay()
    {
        return premiumCombinationListDisplay;
    }

    public void setPremiumCombinationListDisplay( String premiumCombinationListDisplay )
    {
        this.premiumCombinationListDisplay = premiumCombinationListDisplay;
    }

    public String getFixIdrDisplay()
    {
        return fixIdrDisplay;
    }

    public void setFixIdrDisplay( String fixIdrDisplay )
    {
        this.fixIdrDisplay = fixIdrDisplay;
    }

    public String getDynamicIdrDisplay()
    {
        return dynamicIdrDisplay;
    }

    public void setDynamicIdrDisplay( String dynamicIdrDisplay )
    {
        this.dynamicIdrDisplay = dynamicIdrDisplay;
    }

    public String getAggresiveIdrDisplay()
    {
        return aggresiveIdrDisplay;
    }

    public void setAggresiveIdrDisplay( String aggresiveIdrDisplay )
    {
        this.aggresiveIdrDisplay = aggresiveIdrDisplay;
    }

    public String getSecureUsdDisplay()
    {
        return secureUsdDisplay;
    }

    public void setSecureUsdDisplay( String secureUsdDisplay )
    {
        this.secureUsdDisplay = secureUsdDisplay;
    }

    public String getDynamicUsdDisplay()
    {
        return dynamicUsdDisplay;
    }

    public void setDynamicUsdDisplay( String dynamicUsdDisplay )
    {
        this.dynamicUsdDisplay = dynamicUsdDisplay;
    }

    public String getFixIdrListDisplay()
    {
        return fixIdrListDisplay;
    }

    public void setFixIdrListDisplay( String fixIdrListDisplay )
    {
        this.fixIdrListDisplay = fixIdrListDisplay;
    }

    public String getDynamicIdrListDisplay()
    {
        return dynamicIdrListDisplay;
    }

    public void setDynamicIdrListDisplay( String dynamicIdrListDisplay )
    {
        this.dynamicIdrListDisplay = dynamicIdrListDisplay;
    }

    public String getAggresiveIdrListDisplay()
    {
        return aggresiveIdrListDisplay;
    }

    public void setAggresiveIdrListDisplay( String aggresiveIdrListDisplay )
    {
        this.aggresiveIdrListDisplay = aggresiveIdrListDisplay;
    }

    public String getSecureUsdListDisplay()
    {
        return secureUsdListDisplay;
    }

    public void setSecureUsdListDisplay( String secureUsdListDisplay )
    {
        this.secureUsdListDisplay = secureUsdListDisplay;
    }

    public String getDynamicUsdListDisplay()
    {
        return dynamicUsdListDisplay;
    }

    public void setDynamicUsdListDisplay( String dynamicUsdListDisplay )
    {
        this.dynamicUsdListDisplay = dynamicUsdListDisplay;
    }

    public String getPremiumFurloughYearDisplay()
    {
        return premiumFurloughYearDisplay;
    }

    public void setPremiumFurloughYearDisplay( String premiumFurloughYearDisplay )
    {
        this.premiumFurloughYearDisplay = premiumFurloughYearDisplay;
    }

    public String getPremiumFurloughYearListDisplay()
    {
        return premiumFurloughYearListDisplay;
    }

    public void setPremiumFurloughYearListDisplay( String premiumFurloughYearListDisplay )
    {
        this.premiumFurloughYearListDisplay = premiumFurloughYearListDisplay;
    }

    public String getPremiumFurloughYearListDisplayHelperDisplay()
    {
        return premiumFurloughYearListDisplayHelperDisplay;
    }

    public void setPremiumFurloughYearListDisplayHelperDisplay( String premiumFurloughYearListDisplayHelperDisplay )
    {
        this.premiumFurloughYearListDisplayHelperDisplay = premiumFurloughYearListDisplayHelperDisplay;
    }

    public String getButtonCountTotalSumInsuredDisplay()
    {
        return buttonCountTotalSumInsuredDisplay;
    }

    public void setButtonCountTotalSumInsuredDisplay( String buttonCountTotalSumInsuredDisplay )
    {
        this.buttonCountTotalSumInsuredDisplay = buttonCountTotalSumInsuredDisplay;
    }

    public String getButtonCountPremiumDisplay()
    {
        return buttonCountPremiumDisplay;
    }

    public void setButtonCountPremiumDisplay( String buttonCountPremiumDisplay )
    {
        this.buttonCountPremiumDisplay = buttonCountPremiumDisplay;
    }

    public String getButtonTopupWithdrawDisplay()
    {
        return buttonTopupWithdrawDisplay;
    }

    public void setButtonTopupWithdrawDisplay( String buttonTopupWithdrawDisplay )
    {
        this.buttonTopupWithdrawDisplay = buttonTopupWithdrawDisplay;
    }

    public String getButtonRiderAndButtonTopupDisplay()
    {
        return buttonRiderAndButtonTopupDisplay;
    }

    public void setButtonRiderAndButtonTopupDisplay( String buttonRiderAndButtonTopupDisplay )
    {
        this.buttonRiderAndButtonTopupDisplay = buttonRiderAndButtonTopupDisplay;
    }

    public String getButtonRiderDisplay()
    {
        return buttonRiderDisplay;
    }

    public void setButtonRiderDisplay( String buttonRiderDisplay )
    {
        this.buttonRiderDisplay = buttonRiderDisplay;
    }

    public String getButtonTopupDisplay()
    {
        return buttonTopupDisplay;
    }

    public void setButtonTopupDisplay( String buttonTopupDisplay )
    {
        this.buttonTopupDisplay = buttonTopupDisplay;
    }

    public String getButtonViewProposalDisplay()
    {
        return buttonViewProposalDisplay;
    }

    public void setButtonViewProposalDisplay( String buttonViewProposalDisplay )
    {
        this.buttonViewProposalDisplay = buttonViewProposalDisplay;
    }

    public String getHeaderAdditionalAssuranceDisplay()
    {
        return headerAdditionalAssuranceDisplay;
    }

    public void setHeaderAdditionalAssuranceDisplay( String headerAdditionalAssuranceDisplay )
    {
        this.headerAdditionalAssuranceDisplay = headerAdditionalAssuranceDisplay;
    }

    public String getHeaderInvestChoiceDisplay()
    {
        return headerInvestChoiceDisplay;
    }

    public void setHeaderInvestChoiceDisplay( String headerInvestChoiceDisplay )
    {
        this.headerInvestChoiceDisplay = headerInvestChoiceDisplay;
    }

    public String getTotalSumInsuredIsDisabled()
    {
        return totalSumInsuredIsDisabled;
    }

    public void setTotalSumInsuredIsDisabled( String totalSumInsuredIsDisabled )
    {
        this.totalSumInsuredIsDisabled = totalSumInsuredIsDisabled;
    }

    public String getPremiumIsDisabled()
    {
        return premiumIsDisabled;
    }

    public void setPremiumIsDisabled( String premiumIsDisabled )
    {
        this.premiumIsDisabled = premiumIsDisabled;
    }

    public String getTotalSumInsuredListIsDisabled()
    {
        return totalSumInsuredListIsDisabled;
    }

    public void setTotalSumInsuredListIsDisabled( String totalSumInsuredListIsDisabled )
    {
        this.totalSumInsuredListIsDisabled = totalSumInsuredListIsDisabled;
    }

    public String getPremiumListIsDisabled()
    {
        return premiumListIsDisabled;
    }

    public void setPremiumListIsDisabled( String premiumListIsDisabled )
    {
        this.premiumListIsDisabled = premiumListIsDisabled;
    }

    public String getTermOfContractIsDisabled()
    {
        return termOfContractIsDisabled;
    }

    public void setTermOfContractIsDisabled( String termOfContractIsDisabled )
    {
        this.termOfContractIsDisabled = termOfContractIsDisabled;
    }

    public String getTermOfPaymentIsDisabled()
    {
        return termOfPaymentIsDisabled;
    }

    public void setTermOfPaymentIsDisabled( String termOfPaymentIsDisabled )
    {
        this.termOfPaymentIsDisabled = termOfPaymentIsDisabled;
    }

    public String getFlagOfVIPIsDisabled() //CR#: NCR/2020/08/019 FLAG VIP (IGA)
    {
        return flagOfVIPIsDisabled;
    }

    public void setFlagOfVIPIsDisabled( String flagOfVIPIsDisabled )
    {
        this.flagOfVIPIsDisabled = flagOfVIPIsDisabled;
    }
    //DONE
	public String getInvestRateInPercentIsDisabled()
    {
        return investRateInPercentIsDisabled;
    }

    public void setInvestRateInPercentIsDisabled( String investRateInPercentIsDisabled )
    {
        this.investRateInPercentIsDisabled = investRateInPercentIsDisabled;
    }

    public String getTermRiderFlagIsDisabled()
    {
        return termRiderFlagIsDisabled;
    }

    public void setTermRiderFlagIsDisabled( String termRiderFlagIsDisabled )
    {
        this.termRiderFlagIsDisabled = termRiderFlagIsDisabled;
    }

    public String getPersonalAccidentFlagIsDisabled()
    {
        return personalAccidentFlagIsDisabled;
    }

    public void setPersonalAccidentFlagIsDisabled( String personalAccidentFlagIsDisabled )
    {
        this.personalAccidentFlagIsDisabled = personalAccidentFlagIsDisabled;
    }

    public String getCriticalIllnessFlagIsDisabled()
    {
        return criticalIllnessFlagIsDisabled;
    }

    public void setCriticalIllnessFlagIsDisabled( String criticalIllnessFlagIsDisabled )
    {
        this.criticalIllnessFlagIsDisabled = criticalIllnessFlagIsDisabled;
    }

    public String getPayorsClauseFlagIsDisabled()
    {
        return payorsClauseFlagIsDisabled;
    }

    public void setPayorsClauseFlagIsDisabled( String payorsClauseFlagIsDisabled )
    {
        this.payorsClauseFlagIsDisabled = payorsClauseFlagIsDisabled;
    }

    public String getWaiverPremiumDisabilityFlagIsDisabled()
    {
        return waiverPremiumDisabilityFlagIsDisabled;
    }

    public void setWaiverPremiumDisabilityFlagIsDisabled( String waiverPremiumDisabilityFlagIsDisabled )
    {
        this.waiverPremiumDisabilityFlagIsDisabled = waiverPremiumDisabilityFlagIsDisabled;
    }

    public String getTermRiderUnitQtyListIsDisabled()
    {
        return termRiderUnitQtyListIsDisabled;
    }

    public void setTermRiderUnitQtyListIsDisabled( String termRiderUnitQtyListIsDisabled )
    {
        this.termRiderUnitQtyListIsDisabled = termRiderUnitQtyListIsDisabled;
    }

    public String getPersonalAccidentUnitQtyListIsDisabled()
    {
        return personalAccidentUnitQtyListIsDisabled;
    }

    public void setPersonalAccidentUnitQtyListIsDisabled( String personalAccidentUnitQtyListIsDisabled )
    {
        this.personalAccidentUnitQtyListIsDisabled = personalAccidentUnitQtyListIsDisabled;
    }

    public String getAssurancePlanListIsDisabled()
    {
        return assurancePlanListIsDisabled;
    }

    public void setAssurancePlanListIsDisabled( String assurancePlanListIsDisabled )
    {
        this.assurancePlanListIsDisabled = assurancePlanListIsDisabled;
    }

    public String getCurrencyListIsDisabled()
    {
        return currencyListIsDisabled;
    }

    public void setCurrencyListIsDisabled( String currencyListIsDisabled )
    {
        this.currencyListIsDisabled = currencyListIsDisabled;
    }

    public String getPaymentModeListIsDisabled()
    {
        return paymentModeListIsDisabled;
    }

    public void setPaymentModeListIsDisabled( String paymentModeListIsDisabled )
    {
        this.paymentModeListIsDisabled = paymentModeListIsDisabled;
    }

    public String getClazzListIsDisabled()
    {
        return clazzListIsDisabled;
    }

    public void setClazzListIsDisabled( String clazzListIsDisabled )
    {
        this.clazzListIsDisabled = clazzListIsDisabled;
    }

    public String getMgiListIsDisabled()
    {
        return mgiListIsDisabled;
    }

    public void setMgiListIsDisabled( String mgiListIsDisabled )
    {
        this.mgiListIsDisabled = mgiListIsDisabled;
    }

    public String getMtiListIsDisabled()
    {
        return mtiListIsDisabled;
    }

    public void setMtiListIsDisabled( String mtiListIsDisabled )
    {
        this.mtiListIsDisabled = mtiListIsDisabled;
    }

    public String getTargetListIsDisabled()
    {
        return targetListIsDisabled;
    }

    public void setTargetListIsDisabled( String targetListIsDisabled )
    {
        this.targetListIsDisabled = targetListIsDisabled;
    }

    public String getTaxBeforeAfterListIsDisabled()
    {
        return taxBeforeAfterListIsDisabled;
    }

    public void setTaxBeforeAfterListIsDisabled( String taxBeforeAfterListIsDisabled )
    {
        this.taxBeforeAfterListIsDisabled = taxBeforeAfterListIsDisabled;
    }

    public String getPremiumCombinationListIsDisabled()
    {
        return premiumCombinationListIsDisabled;
    }

    public void setPremiumCombinationListIsDisabled( String premiumCombinationListIsDisabled )
    {
        this.premiumCombinationListIsDisabled = premiumCombinationListIsDisabled;
    }

    public String getFixIdrListIsDisabled()
    {
        return fixIdrListIsDisabled;
    }

    public void setFixIdrListIsDisabled( String fixIdrListIsDisabled )
    {
        this.fixIdrListIsDisabled = fixIdrListIsDisabled;
    }

    public String getDynamicIdrListIsDisabled()
    {
        return dynamicIdrListIsDisabled;
    }

    public void setDynamicIdrListIsDisabled( String dynamicIdrListIsDisabled )
    {
        this.dynamicIdrListIsDisabled = dynamicIdrListIsDisabled;
    }

    public String getAggresiveIdrListIsDisabled()
    {
        return aggresiveIdrListIsDisabled;
    }

    public void setAggresiveIdrListIsDisabled( String aggresiveIdrListIsDisabled )
    {
        this.aggresiveIdrListIsDisabled = aggresiveIdrListIsDisabled;
    }

    public String getSecureUsdListIsDisabled()
    {
        return secureUsdListIsDisabled;
    }

    public void setSecureUsdListIsDisabled( String secureUsdListIsDisabled )
    {
        this.secureUsdListIsDisabled = secureUsdListIsDisabled;
    }

    public String getDynamicUsdListIsDisabled()
    {
        return dynamicUsdListIsDisabled;
    }

    public void setDynamicUsdListIsDisabled( String dynamicUsdListIsDisabled )
    {
        this.dynamicUsdListIsDisabled = dynamicUsdListIsDisabled;
    }

    public String getFixIdrIsDisabled()
    {
        return fixIdrIsDisabled;
    }

    public void setFixIdrIsDisabled( String fixIdrIsDisabled )
    {
        this.fixIdrIsDisabled = fixIdrIsDisabled;
    }

    public String getDynamicIdrIsDisabled()
    {
        return dynamicIdrIsDisabled;
    }

    public void setDynamicIdrIsDisabled( String dynamicIdrIsDisabled )
    {
        this.dynamicIdrIsDisabled = dynamicIdrIsDisabled;
    }

    public String getAggresiveIdrIsDisabled()
    {
        return aggresiveIdrIsDisabled;
    }

    public void setAggresiveIdrIsDisabled( String aggresiveIdrIsDisabled )
    {
        this.aggresiveIdrIsDisabled = aggresiveIdrIsDisabled;
    }

    public String getSecureUsdIsDisabled()
    {
        return secureUsdIsDisabled;
    }

    public void setSecureUsdIsDisabled( String secureUsdIsDisabled )
    {
        this.secureUsdIsDisabled = secureUsdIsDisabled;
    }

    public String getDynamicUsdIsDisabled()
    {
        return dynamicUsdIsDisabled;
    }

    public void setDynamicUsdIsDisabled( String dynamicUsdIsDisabled )
    {
        this.dynamicUsdIsDisabled = dynamicUsdIsDisabled;
    }

    public String getPremiumFurloughYearIsDisabled()
    {
        return premiumFurloughYearIsDisabled;
    }

    public void setPremiumFurloughYearIsDisabled( String premiumFurloughYearIsDisabled )
    {
        this.premiumFurloughYearIsDisabled = premiumFurloughYearIsDisabled;
    }

    public String getPremiumFurloughYearListIsDisabled()
    {
        return premiumFurloughYearListIsDisabled;
    }

    public void setPremiumFurloughYearListIsDisabled( String premiumFurloughYearListIsDisabled )
    {
        this.premiumFurloughYearListIsDisabled = premiumFurloughYearListIsDisabled;
    }

    public String getPremiumReadOnly()
    {
        return premiumReadOnly;
    }

    public void setPremiumReadOnly( String premiumReadOnly )
    {
        this.premiumReadOnly = premiumReadOnly;
    }

    public String getTotalSumInsuredReadOnly()
    {
        return totalSumInsuredReadOnly;
    }

    public void setTotalSumInsuredReadOnly( String totalSumInsuredReadOnly )
    {
        this.totalSumInsuredReadOnly = totalSumInsuredReadOnly;
    }

    public String getDownloadFlag()
    {
        return downloadFlag;
    }

    public void setDownloadFlag( String downloadFlag )
    {
        this.downloadFlag = downloadFlag;
    }

    public String getPrevAssurancePlanCd()
    {
        return prevAssurancePlanCd;
    }

    public void setPrevAssurancePlanCd( String prevAssurancePlanCd )
    {
        this.prevAssurancePlanCd = prevAssurancePlanCd;
    }

    public String getTotalSumInsuredOptionIsToRefreshPage()
    {
        return totalSumInsuredOptionIsToRefreshPage;
    }

    public void setTotalSumInsuredOptionIsToRefreshPage( String totalSumInsuredOptionIsToRefreshPage )
    {
        this.totalSumInsuredOptionIsToRefreshPage = totalSumInsuredOptionIsToRefreshPage;
    }

    public String getPremiumOptionIsToRefreshPage()
    {
        return premiumOptionIsToRefreshPage;
    }

    public void setPremiumOptionIsToRefreshPage( String premiumOptionIsToRefreshPage )
    {
        this.premiumOptionIsToRefreshPage = premiumOptionIsToRefreshPage;
    }

    public String getTheEvent()
    {
        return theEvent;
    }

    public void setTheEvent( String theEvent )
    {
        this.theEvent = theEvent;
    }

    public Integer getTempInsuredAge()
    {
        return tempInsuredAge;
    }

    public void setTempInsuredAge( Integer tempInsuredAge )
    {
        this.tempInsuredAge = tempInsuredAge;
    }

    public ArrayList<OptionVO> getPacketList()
    {
        return packetList;
    }

    public void setPacketList( List<OptionVO> packetList )
    {
        this.packetList = ListUtil.serializableList(packetList);
    }

    public String getPacketListDisplay()
    {
        return packetListDisplay;
    }

    public void setPacketListDisplay( String packetListDisplay )
    {
        this.packetListDisplay = packetListDisplay;
    }

    public Integer getPacketCd()
    {
        return packetCd;
    }

    public void setPacketCd( Integer packetCd )
    {
        this.packetCd = packetCd;
    }

	public Integer getTempPremium() {
		return tempPremium;
	}

	public void setTempPremium(Integer tempPremium) {
		this.tempPremium = tempPremium;
	}

	public Integer getSmileLadiesPackageCd() {
		return smileLadiesPackageCd;
	}

	public void setSmileLadiesPackageCd(Integer smileLadiesPackageCd) {
		this.smileLadiesPackageCd = smileLadiesPackageCd;
	}

	public ArrayList<OptionVO> getSmileLadiesPackageList() {
		return smileLadiesPackageList;
	}

	public void setSmileLadiesPackageList(List<OptionVO> smileLadiesPackageList) {
		this.smileLadiesPackageList = ListUtil.serializableList(smileLadiesPackageList);
	}

	public String getSmileLadiesPackageListDisplay() {
		return smileLadiesPackageListDisplay;
	}

	public void setSmileLadiesPackageListDisplay(
			String smileLadiesPackageListDisplay) {
		this.smileLadiesPackageListDisplay = smileLadiesPackageListDisplay;
	}

	public String getSmileLadiesPackageListIsDisabled() {
		return smileLadiesPackageListIsDisabled;
	}

	public void setSmileLadiesPackageListIsDisabled(
			String smileLadiesPackageListIsDisabled) {
		this.smileLadiesPackageListIsDisabled = smileLadiesPackageListIsDisabled;
	}

	public BigDecimal getTotalSumInsuredFix() {
		return totalSumInsuredFix;
	}

	public void setTotalSumInsuredFix(BigDecimal totalSumInsuredFix) {
		this.totalSumInsuredFix = totalSumInsuredFix;
	}

	public ArrayList<OptionVO> getSmilePensionPackageList() {
		return smilePensionPackageList;
	}

	public void setSmilePensionPackageList(List<OptionVO> smilePensionPackageList) {
		this.smilePensionPackageList = ListUtil.serializableList(smilePensionPackageList);
	}

	public Integer getSmilePensionPackageCd() {
		return smilePensionPackageCd;
	}

	public void setSmilePensionPackageCd(Integer smilePensionPackageCd) {
		this.smilePensionPackageCd = smilePensionPackageCd;
	}

	public String getSmilePensionPackageListDisplay() {
		return smilePensionPackageListDisplay;
	}

	public void setSmilePensionPackageListDisplay(
			String smilePensionPackageListDisplay) {
		this.smilePensionPackageListDisplay = smilePensionPackageListDisplay;
	}

	public String getSmilePensionPackageListIsDisabled() {
		return smilePensionPackageListIsDisabled;
	}

	public void setSmilePensionPackageListIsDisabled(
			String smilePensionPackageListIsDisabled) {
		this.smilePensionPackageListIsDisabled = smilePensionPackageListIsDisabled;
	}

	public String getPrevAssurancePlanPackageCd() {
		return prevAssurancePlanPackageCd;
	}

	public void setPrevAssurancePlanPackageCd(String prevAssurancePlanPackageCd) {
		this.prevAssurancePlanPackageCd = prevAssurancePlanPackageCd;
	}

	public String getPesertaEkaSehatStandAloneDisplay() {
		return pesertaEkaSehatStandAloneDisplay;
	}

	public void setPesertaEkaSehatStandAloneDisplay(
			String pesertaEkaSehatStandAloneDisplay) {
		this.pesertaEkaSehatStandAloneDisplay = pesertaEkaSehatStandAloneDisplay;
	}

	public String getTpdFlag() {
		return tpdFlag;
	}

	public void setTpdFlag(String tpdFlag) {
		this.tpdFlag = tpdFlag;
	}

	public String getTpdFlagDisplay() {
		return tpdFlagDisplay;
	}

	public void setTpdFlagDisplay(String tpdFlagDisplay) {
		this.tpdFlagDisplay = tpdFlagDisplay;
	}

	public String getTpdFlagIsDisabled() {
		return tpdFlagIsDisabled;
	}

	public void setTpdFlagIsDisabled(String tpdFlagIsDisabled) {
		this.tpdFlagIsDisabled = tpdFlagIsDisabled;
	}

	public Integer getSecondPackageCd() {
		return secondPackageCd;
	}

	public void setSecondPackageCd(Integer secondPackageCd) {
		this.secondPackageCd = secondPackageCd;
	}

	public ArrayList<OptionVO> getSecondPackageList() {
		return secondPackageList;
	}

	public void setSecondPackageList(List<OptionVO> secondPackageList) {
		this.secondPackageList = ListUtil.serializableList(secondPackageList);
	}

	public String getSecondPackageListDisplay() {
		return secondPackageListDisplay;
	}

	public void setSecondPackageListDisplay(String secondPackageListDisplay) {
		this.secondPackageListDisplay = secondPackageListDisplay; 
	}

	public String getSecondPackageListIsDisabled() {
		return secondPackageListIsDisabled;
	}

	public void setSecondPackageListIsDisabled(String secondPackageListIsDisabled) {
		this.secondPackageListIsDisabled = secondPackageListIsDisabled;
	}

	public Integer getProviderTypeCd() {
		return providerTypeCd;
	}

	public void setProviderTypeCd(Integer providerTypeCd) {
		this.providerTypeCd = providerTypeCd;
	}

	public ArrayList<OptionVO> getProviderTypeList() {
		return providerTypeList;
	}

	public void setProviderTypeList(List<OptionVO> providerTypeList) {
		this.providerTypeList = ListUtil.serializableList(providerTypeList);
	}

	public String getProviderTypeListDisplay() {
		return providerTypeListDisplay;
	}

	public void setProviderTypeListDisplay(String providerTypeListDisplay) {
		this.providerTypeListDisplay = providerTypeListDisplay;
	}

	public String getProviderTypeListIsDisabled() {
		return providerTypeListIsDisabled;
	}

	public void setProviderTypeListIsDisabled(String providerTypeListIsDisabled) {
		this.providerTypeListIsDisabled = providerTypeListIsDisabled;
	}

	public Integer getEducationPackageCd() {
		return educationPackageCd;
	}

	public void setEducationPackageCd(Integer educationPackageCd) {
		this.educationPackageCd = educationPackageCd;
	}

	public ArrayList<OptionVO> getEducationPackageList() {
		return educationPackageList;
	}

	public void setEducationPackageList(List<OptionVO> educationPackageList) {
		this.educationPackageList = ListUtil.serializableList(educationPackageList);
	}

	public String getEducationPackageListDisplay() {
		return educationPackageListDisplay;
	}

	public void setEducationPackageListDisplay(String educationPackageListDisplay) {
		this.educationPackageListDisplay = educationPackageListDisplay;
	}

	public String getEducationPackageListIsDisabled() {
		return educationPackageListIsDisabled;
	}

	public void setEducationPackageListIsDisabled(
			String educationPackageListIsDisabled) {
		this.educationPackageListIsDisabled = educationPackageListIsDisabled;
	}

	public String getEmailDisplay() {
		return emailDisplay;
	}

	public void setEmailDisplay(String emailDisplay) {
		this.emailDisplay = emailDisplay;
	}

	public String getFaxDisplay() {
		return faxDisplay;
	}

	public void setFaxDisplay(String faxDisplay) {
		this.faxDisplay = faxDisplay;
	}

	public ArrayList<Mst_proposal> getMstProposal() {
		return mstProposal;
	}

	public void setMstProposal(List<Mst_proposal> mstProposal) {
		this.mstProposal = ListUtil.serializableList(mstProposal);
	}

	public Integer getProtectorPackageCd() {
		return protectorPackageCd;
	}

	public void setProtectorPackageCd(Integer protectorPackageCd) {
		this.protectorPackageCd = protectorPackageCd;
	}

	public ArrayList<OptionVO> getProtectorPackageList() {
		return protectorPackageList;
	}

	public void setProtectorPackageList(List<OptionVO> protectorPackageList) {
		this.protectorPackageList = ListUtil.serializableList(protectorPackageList);
	}

	public String getProtectorPackageListDisplay() {
		return protectorPackageListDisplay;
	}

	public void setProtectorPackageListDisplay(String protectorPackageListDisplay) {
		this.protectorPackageListDisplay = protectorPackageListDisplay;
	}

	public String getProtectorPackageListIsDisabled() {
		return protectorPackageListIsDisabled;
	}

	public void setProtectorPackageListIsDisabled(
			String protectorPackageListIsDisabled) {
		this.protectorPackageListIsDisabled = protectorPackageListIsDisabled;
	}

	public BigDecimal getExtraPremium() {
		return extraPremium;
	}

	public void setExtraPremium(BigDecimal extraPremium) {
		this.extraPremium = extraPremium;
	}

	public String getExtraPremiumDisplay() {
		return extraPremiumDisplay;
	}

	public void setExtraPremiumDisplay(String extraPremiumDisplay) {
		this.extraPremiumDisplay = extraPremiumDisplay;
	}

	public String getExtraPremiumIsDisabled() {
		return extraPremiumIsDisabled;
	}

	public void setExtraPremiumIsDisabled(String extraPremiumIsDisabled) {
		this.extraPremiumIsDisabled = extraPremiumIsDisabled;
	}

	public String getExtraPremiumListDisplay() {
		return extraPremiumListDisplay;
	}

	public void setExtraPremiumListDisplay(String extraPremiumListDisplay) {
		this.extraPremiumListDisplay = extraPremiumListDisplay;
	}

	public BigDecimal getExtraPremiumCd() {
		return extraPremiumCd;
	}

	public void setExtraPremiumCd(BigDecimal extraPremiumCd) {
		this.extraPremiumCd = extraPremiumCd;
	}

	public List<OptionVO> getExtraPremiumList() {
		return extraPremiumList;
	}

	public void setExtraPremiumList(List<OptionVO> extraPremiumList) {
		this.extraPremiumList = extraPremiumList;
	}

	public String getExtraPremiumListIsDisabled() {
		return extraPremiumListIsDisabled;
	}

	public void setExtraPremiumListIsDisabled(String extraPremiumListIsDisabled) {
		this.extraPremiumListIsDisabled = extraPremiumListIsDisabled;
	}

	public BigDecimal getExtraJob() {
		return extraJob;
	}

	public void setExtraJob(BigDecimal extraJob) {
		this.extraJob = extraJob;
	}

	public String getExtraJobDisplay() {
		return extraJobDisplay;
	}

	public void setExtraJobDisplay(String extraJobDisplay) {
		this.extraJobDisplay = extraJobDisplay;
	}

	public String getExtraJobIsDisabled() {
		return extraJobIsDisabled;
	}

	public void setExtraJobIsDisabled(String extraJobIsDisabled) {
		this.extraJobIsDisabled = extraJobIsDisabled;
	}

	public String getLjiFixCd() {
		return ljiFixCd;
	}

	public void setLjiFixCd(String ljiFixCd) {
		this.ljiFixCd = ljiFixCd;
	}

	public List<OptionVO> getLjiFixList() {
		return ljiFixList;
	}

	public void setLjiFixList(List<OptionVO> ljiFixList) {
		this.ljiFixList = ljiFixList;
	}

	public String getLjiFixListDisplay() {
		return ljiFixListDisplay;
	}

	public void setLjiFixListDisplay(String ljiFixListDisplay) {
		this.ljiFixListDisplay = ljiFixListDisplay;
	}

	public String getLjiFixListIsDisabled() {
		return ljiFixListIsDisabled;
	}

	public void setLjiFixListIsDisabled(String ljiFixListIsDisabled) {
		this.ljiFixListIsDisabled = ljiFixListIsDisabled;
	}

	public String getLjiDynamicCd() {
		return ljiDynamicCd;
	}

	public void setLjiDynamicCd(String ljiDynamicCd) {
		this.ljiDynamicCd = ljiDynamicCd;
	}

	public List<OptionVO> getLjiDynamicList() {
		return ljiDynamicList;
	}

	public void setLjiDynamicList(List<OptionVO> ljiDynamicList) {
		this.ljiDynamicList = ljiDynamicList;
	}

	public String getLjiDynamicListDisplay() {
		return ljiDynamicListDisplay;
	}

	public void setLjiDynamicListDisplay(String ljiDynamicListDisplay) {
		this.ljiDynamicListDisplay = ljiDynamicListDisplay;
	}

	public String getLjiDynamicListIsDisabled() {
		return ljiDynamicListIsDisabled;
	}

	public void setLjiDynamicListIsDisabled(String ljiDynamicListIsDisabled) {
		this.ljiDynamicListIsDisabled = ljiDynamicListIsDisabled;
	}

	public String getLjiAggresiveCd() {
		return ljiAggresiveCd;
	}

	public void setLjiAggresiveCd(String ljiAggresiveCd) {
		this.ljiAggresiveCd = ljiAggresiveCd;
	}

	public List<OptionVO> getLjiAggresiveList() {
		return ljiAggresiveList;
	}

	public void setLjiAggresiveList(List<OptionVO> ljiAggresiveList) {
		this.ljiAggresiveList = ljiAggresiveList;
	}

	public String getLjiAggresiveListDisplay() {
		return ljiAggresiveListDisplay;
	}

	public void setLjiAggresiveListDisplay(String ljiAggresiveListDisplay) {
		this.ljiAggresiveListDisplay = ljiAggresiveListDisplay;
	}

	public String getLjiAggresiveListIsDisabled() {
		return ljiAggresiveListIsDisabled;
	}

	public void setLjiAggresiveListIsDisabled(String ljiAggresiveListIsDisabled) {
		this.ljiAggresiveListIsDisabled = ljiAggresiveListIsDisabled;
	}
	

	public String getMedicalPlusDisplay() {
		return medicalPlusDisplay;
	}

	public void setMedicalPlusDisplay(String medicalPlusDisplay) {
		this.medicalPlusDisplay = medicalPlusDisplay;
	}

	public String getMedicalPlusRgFlag() {
		return medicalPlusRgFlag;
	}

	public void setMedicalPlusRgFlag(String medicalPlusRgFlag) {
		this.medicalPlusRgFlag = medicalPlusRgFlag;
	}

	public String getMedicalPlusRbFlag() {
		return medicalPlusRbFlag;
	}

	public void setMedicalPlusRbFlag(String medicalPlusRbFlag) {
		this.medicalPlusRbFlag = medicalPlusRbFlag;
	}

	public String getMedicalPlusPkFlag() {
		return medicalPlusPkFlag;
	}

	public void setMedicalPlusPkFlag(String medicalPlusPkFlag) {
		this.medicalPlusPkFlag = medicalPlusPkFlag;
	}

	public String getPesertaMedicalPlusStandAloneDisplay() {
		return pesertaMedicalPlusStandAloneDisplay;
	}

	public void setPesertaMedicalPlusStandAloneDisplay(
			String pesertaMedicalPlusStandAloneDisplay) {
		this.pesertaMedicalPlusStandAloneDisplay = pesertaMedicalPlusStandAloneDisplay;
	}
		

	public String getBabyDisplay() {
		return babyDisplay;
	}

	public void setBabyDisplay(String babyDisplay) {
		this.babyDisplay = babyDisplay;
	}

	public String getBabyFlag() {
		return babyFlag;
	}

	public void setBabyFlag(String babyFlag) {
		this.babyFlag = babyFlag;
	}

	public String getBabyFlagIsDisabled() {
		return babyFlagIsDisabled;
	}

	public void setBabyFlagIsDisabled(String babyFlagIsDisabled) {
		this.babyFlagIsDisabled = babyFlagIsDisabled;
	}

	public String getBabyChooseCd() {
		return babyChooseCd;
	}

	public void setBabyChooseCd(String babyChooseCd) {
		this.babyChooseCd = babyChooseCd;
	}

	public ArrayList<OptionVO> getBabyChooseList() {
		return babyChooseList;
	}

	public void setBabyChooseList(List<OptionVO> babyChooseList) {
		this.babyChooseList = ListUtil.serializableList(babyChooseList);
	}

	public String getBabyCd() {
		return babyCd;
	}

	public void setBabyCd(String babyCd) {
		this.babyCd = babyCd;
	}

	public ArrayList<OptionVO> getBabyList() {
		return babyList;
	}

	public void setBabyList(List<OptionVO> babyList) {
		this.babyList =  ListUtil.serializableList(babyList);
	}

	public String getBasePremiumReadOnly() {
		return basePremiumReadOnly;
	}

	public void setBasePremiumReadOnly(String basePremiumReadOnly) {
		this.basePremiumReadOnly = basePremiumReadOnly;
	}

	public String getLjiFixSimasListDisplay() {
		return ljiFixSimasListDisplay;
	}

	public void setLjiFixSimasListDisplay(String ljiFixSimasListDisplay) {
		this.ljiFixSimasListDisplay = ljiFixSimasListDisplay;
	}

	public String getLjiDynamicSimasListDisplay() {
		return ljiDynamicSimasListDisplay;
	}

	public void setLjiDynamicSimasListDisplay(String ljiDynamicSimasListDisplay) {
		this.ljiDynamicSimasListDisplay = ljiDynamicSimasListDisplay;
	}

	public String getLjiAggresiveSimasListDisplay() {
		return ljiAggresiveSimasListDisplay;
	}

	public void setLjiAggresiveSimasListDisplay(String ljiAggresiveSimasListDisplay) {
		this.ljiAggresiveSimasListDisplay = ljiAggresiveSimasListDisplay;
	}

	public String getLjiFixSimasCd() {
		return ljiFixSimasCd;
	}

	public void setLjiFixSimasCd(String ljiFixSimasCd) {
		this.ljiFixSimasCd = ljiFixSimasCd;
	}

	public String getLjiDynamicSimasCd() {
		return ljiDynamicSimasCd;
	}

	public void setLjiDynamicSimasCd(String ljiDynamicSimasCd) {
		this.ljiDynamicSimasCd = ljiDynamicSimasCd;
	}

	public String getLjiAggresiveSimasCd() {
		return ljiAggresiveSimasCd;
	}

	public void setLjiAggresiveSimasCd(String ljiAggresiveSimasCd) {
		this.ljiAggresiveSimasCd = ljiAggresiveSimasCd;
	}

	public List<OptionVO> getLjiFixSimasList() {
		return ljiFixSimasList;
	}

	public void setLjiFixSimasList(List<OptionVO> ljiFixSimasList) {
		this.ljiFixSimasList = ljiFixSimasList;
	}

	public List<OptionVO> getLjiDynamicSimasList() {
		return ljiDynamicSimasList;
	}

	public void setLjiDynamicSimasList(List<OptionVO> ljiDynamicSimasList) {
		this.ljiDynamicSimasList = ljiDynamicSimasList;
	}

	public List<OptionVO> getLjiAggresiveSimasList() {
		return ljiAggresiveSimasList;
	}

	public void setLjiAggresiveSimasList(List<OptionVO> ljiAggresiveSimasList) {
		this.ljiAggresiveSimasList = ljiAggresiveSimasList;
	}

	public String getLjiFixSimasListIsDisabled() {
		return ljiFixSimasListIsDisabled;
	}

	public void setLjiFixSimasListIsDisabled(String ljiFixSimasListIsDisabled) {
		this.ljiFixSimasListIsDisabled = ljiFixSimasListIsDisabled;
	}

	public String getLjiDynamicSimasListIsDisabled() {
		return ljiDynamicSimasListIsDisabled;
	}

	public void setLjiDynamicSimasListIsDisabled(
			String ljiDynamicSimasListIsDisabled) {
		this.ljiDynamicSimasListIsDisabled = ljiDynamicSimasListIsDisabled;
	}

	public String getLjiAggresiveSimasListIsDisabled() {
		return ljiAggresiveSimasListIsDisabled;
	}

	public void setLjiAggresiveSimasListIsDisabled(
			String ljiAggresiveSimasListIsDisabled) {
		this.ljiAggresiveSimasListIsDisabled = ljiAggresiveSimasListIsDisabled;
	}

	public BigDecimal getFixSimasIdr() {
		return fixSimasIdr;
	}

	public void setFixSimasIdr(BigDecimal fixSimasIdr) {
		this.fixSimasIdr = fixSimasIdr;
	}

	public BigDecimal getFixSimasIdrCd() {
		return fixSimasIdrCd;
	}

	public void setFixSimasIdrCd(BigDecimal fixSimasIdrCd) {
		this.fixSimasIdrCd = fixSimasIdrCd;
	}

	public BigDecimal getDynamicSimasIdr() {
		return dynamicSimasIdr;
	}

	public void setDynamicSimasIdr(BigDecimal dynamicSimasIdr) {
		this.dynamicSimasIdr = dynamicSimasIdr;
	}

	public BigDecimal getDynamicSimasIdrCd() {
		return dynamicSimasIdrCd;
	}

	public void setDynamicSimasIdrCd(BigDecimal dynamicSimasIdrCd) {
		this.dynamicSimasIdrCd = dynamicSimasIdrCd;
	}

	public BigDecimal getAggresiveSimasIdr() {
		return aggresiveSimasIdr;
	}

	public void setAggresiveSimasIdr(BigDecimal aggresiveSimasIdr) {
		this.aggresiveSimasIdr = aggresiveSimasIdr;
	}

	public BigDecimal getAggresiveSimasIdrCd() {
		return aggresiveSimasIdrCd;
	}

	public void setAggresiveSimasIdrCd(BigDecimal aggresiveSimasIdrCd) {
		this.aggresiveSimasIdrCd = aggresiveSimasIdrCd;
	}

	public String getFixSimasIdrIsDisabled() {
		return fixSimasIdrIsDisabled;
	}

	public void setFixSimasIdrIsDisabled(String fixSimasIdrIsDisabled) {
		this.fixSimasIdrIsDisabled = fixSimasIdrIsDisabled;
	}

	public String getDynamicSimasIdrIsDisabled() {
		return dynamicSimasIdrIsDisabled;
	}

	public void setDynamicSimasIdrIsDisabled(String dynamicSimasIdrIsDisabled) {
		this.dynamicSimasIdrIsDisabled = dynamicSimasIdrIsDisabled;
	}

	public String getAggresiveSimasIdrIsDisabled() {
		return aggresiveSimasIdrIsDisabled;
	}

	public void setAggresiveSimasIdrIsDisabled(String aggresiveSimasIdrIsDisabled) {
		this.aggresiveSimasIdrIsDisabled = aggresiveSimasIdrIsDisabled;
	}

	public String getFixSimasIdrListDisplay() {
		return fixSimasIdrListDisplay;
	}

	public void setFixSimasIdrListDisplay(String fixSimasIdrListDisplay) {
		this.fixSimasIdrListDisplay = fixSimasIdrListDisplay;
	}

	public String getDynamicSimasIdrListDisplay() {
		return dynamicSimasIdrListDisplay;
	}

	public void setDynamicSimasIdrListDisplay(String dynamicSimasIdrListDisplay) {
		this.dynamicSimasIdrListDisplay = dynamicSimasIdrListDisplay;
	}

	public String getAggresiveSimasIdrListDisplay() {
		return aggresiveSimasIdrListDisplay;
	}

	public void setAggresiveSimasIdrListDisplay(String aggresiveSimasIdrListDisplay) {
		this.aggresiveSimasIdrListDisplay = aggresiveSimasIdrListDisplay;
	}

	public String getFixSimasIdrListIsDisabled() {
		return fixSimasIdrListIsDisabled;
	}

	public void setFixSimasIdrListIsDisabled(String fixSimasIdrListIsDisabled) {
		this.fixSimasIdrListIsDisabled = fixSimasIdrListIsDisabled;
	}

	public String getDynamicSimasIdrListIsDisabled() {
		return dynamicSimasIdrListIsDisabled;
	}

	public void setDynamicSimasIdrListIsDisabled(
			String dynamicSimasIdrListIsDisabled) {
		this.dynamicSimasIdrListIsDisabled = dynamicSimasIdrListIsDisabled;
	}

	public String getAggresiveSimasIdrListIsDisabled() {
		return aggresiveSimasIdrListIsDisabled;
	}

	public void setAggresiveSimasIdrListIsDisabled(
			String aggresiveSimasIdrListIsDisabled) {
		this.aggresiveSimasIdrListIsDisabled = aggresiveSimasIdrListIsDisabled;
	}

	public String getLjiSecureUsdListDisplay() {
		return ljiSecureUsdListDisplay;
	}

	public void setLjiSecureUsdListDisplay(String ljiSecureUsdListDisplay) {
		this.ljiSecureUsdListDisplay = ljiSecureUsdListDisplay;
	}

	public String getLjiDynamicUsdListDisplay() {
		return ljiDynamicUsdListDisplay;
	}

	public void setLjiDynamicUsdListDisplay(String ljiDynamicUsdListDisplay) {
		this.ljiDynamicUsdListDisplay = ljiDynamicUsdListDisplay;
	}

	public String getLjiSecureUsdCd() {
		return ljiSecureUsdCd;
	}

	public void setLjiSecureUsdCd(String ljiSecureUsdCd) {
		this.ljiSecureUsdCd = ljiSecureUsdCd;
	}

	public String getLjiDynamicUsdCd() {
		return ljiDynamicUsdCd;
	}

	public void setLjiDynamicUsdCd(String ljiDynamicUsdCd) {
		this.ljiDynamicUsdCd = ljiDynamicUsdCd;
	}

	public List<OptionVO> getLjiSecureUsdList() {
		return ljiSecureUsdList;
	}

	public void setLjiSecureUsdList(List<OptionVO> ljiSecureUsdList) {
		this.ljiSecureUsdList = ljiSecureUsdList;
	}

	public List<OptionVO> getLjiDynamicUsdList() {
		return ljiDynamicUsdList;
	}

	public void setLjiDynamicUsdList(List<OptionVO> ljiDynamicUsdList) {
		this.ljiDynamicUsdList = ljiDynamicUsdList;
	}

	public String getTopUpPremiumIsDisabled() {
		return topUpPremiumIsDisabled;
	}

	public void setTopUpPremiumIsDisabled(String topUpPremiumIsDisabled) {
		this.topUpPremiumIsDisabled = topUpPremiumIsDisabled;
	}

	public Lst_product_setup getLst_product_setup() {
		return lst_product_setup;
	}

	public void setLst_product_setup(Lst_product_setup lst_product_setup) {
		this.lst_product_setup = lst_product_setup;
	}

	public Product_setup getProduct_setup() {
		return product_setup;
	}

	public void setProduct_setup(Product_setup product_setup) {
		this.product_setup = product_setup;
	}
	
	
	public String getPesertaHcpProviderDisplayStandAloneDisplay() {
		return pesertaHcpProviderDisplayStandAloneDisplay;
	}

	public void setPesertaHcpProviderDisplayStandAloneDisplay(
			String pesertaHcpProviderDisplayStandAloneDisplay) {
		this.pesertaHcpProviderDisplayStandAloneDisplay = pesertaHcpProviderDisplayStandAloneDisplay;
	}

	public String getLjiSecureUsdListIsDisabled() {
		return ljiSecureUsdListIsDisabled;
	}

	public void setLjiSecureUsdListIsDisabled(String ljiSecureUsdListIsDisabled) {
		this.ljiSecureUsdListIsDisabled = ljiSecureUsdListIsDisabled;
	}

	public String getLjiDynamicUsdListIsDisabled() {
		return ljiDynamicUsdListIsDisabled;
	}

	public void setLjiDynamicUsdListIsDisabled(String ljiDynamicUsdListIsDisabled) {
		this.ljiDynamicUsdListIsDisabled = ljiDynamicUsdListIsDisabled;
	}

	public BigDecimal getCashFundIdr() {
		return cashFundIdr;
	}

	public void setCashFundIdr(BigDecimal cashFundIdr) {
		this.cashFundIdr = cashFundIdr;
	}

	public BigDecimal getCashFundIdrCd() {
		return cashFundIdrCd;
	}

	public void setCashFundIdrCd(BigDecimal cashFundIdrCd) {
		this.cashFundIdrCd = cashFundIdrCd;
	}

	public String getLjiCashFundCd() {
		return ljiCashFundCd;
	}

	public void setLjiCashFundCd(String ljiCashFundCd) {
		this.ljiCashFundCd = ljiCashFundCd;
	}

	public ArrayList<OptionVO> getCashFundIdrList() {
		return cashFundIdrList;
	}
	
	public void setCashFundIdrList(List<OptionVO> cashFundIdrList) {
		this.cashFundIdrList = ListUtil.serializableList(cashFundIdrList);
	}
	
	public List<OptionVO> getLjiCashFundList() {
		return ljiCashFundList;
	}

	public void setLjiCashFundList(List<OptionVO> ljiCashFundList) {
		this.ljiCashFundList = ljiCashFundList;
	}

	public String getCashFundIdrDisplay() {
		return cashFundIdrDisplay;
	}

	public void setCashFundIdrDisplay(String cashFundIdrDisplay) {
		this.cashFundIdrDisplay = cashFundIdrDisplay;
	}

	public String getCashFundIdrListDisplay() {
		return cashFundIdrListDisplay;
	}

	public void setCashFundIdrListDisplay(String cashFundIdrListDisplay) {
		this.cashFundIdrListDisplay = cashFundIdrListDisplay;
	}

	public String getLjiCashFundDisplay() {
		return ljiCashFundDisplay;
	}

	public void setLjiCashFundDisplay(String ljiCashFundDisplay) {
		this.ljiCashFundDisplay = ljiCashFundDisplay;
	}

	public String getCashFundIdrListIsDisabled() {
		return cashFundIdrListIsDisabled;
	}

	public void setCashFundIdrListIsDisabled(String cashFundIdrListIsDisabled) {
		this.cashFundIdrListIsDisabled = cashFundIdrListIsDisabled;
	}

	public String getCashFundIdrIsDisabled() {
		return cashFundIdrIsDisabled;
	}

	public void setCashFundIdrIsDisabled(String cashFundIdrIsDisabled) {
		this.cashFundIdrIsDisabled = cashFundIdrIsDisabled;
	}

	public String getLjiCashFundListIsDisabled() {
		return ljiCashFundListIsDisabled;
	}

	public void setLjiCashFundListIsDisabled(String ljiCashFundListIsDisabled) {
		this.ljiCashFundListIsDisabled = ljiCashFundListIsDisabled;
	}

	public List<lst_premium_combination> getLst_premium_combination() {
		return lst_premium_combination;
	}

	public void setLst_premium_combination(
			List<lst_premium_combination> lst_premium_combination) {
		this.lst_premium_combination = lst_premium_combination;
	}
	/**USD Fund BSIM Products**/
	public String getAggresiveUsdDisplay() {
		return aggresiveUsdDisplay;
	}

	public void setAggresiveUsdDisplay(String aggresiveUsdDisplay) {
		this.aggresiveUsdDisplay = aggresiveUsdDisplay;
	}

	public String getLjiAggresiveUsdListDisplay() {
		return ljiAggresiveUsdListDisplay;
	}

	public void setLjiAggresiveUsdListDisplay(String ljiAggresiveUsdListDisplay) {
		this.ljiAggresiveUsdListDisplay = ljiAggresiveUsdListDisplay;
	}

	public String getLjiAggresiveUsdCd() {
		return ljiAggresiveUsdCd;
	}

	public void setLjiAggresiveUsdCd(String ljiAggresiveUsdCd) {
		this.ljiAggresiveUsdCd = ljiAggresiveUsdCd;
	}

	public List<OptionVO> getLjiAggresiveUsdList() {
		return ljiAggresiveUsdList;
	}

	public void setLjiAggresiveUsdList(List<OptionVO> ljiAggresiveUsdList) {
		this.ljiAggresiveUsdList = ljiAggresiveUsdList;
	}

	public String getLjiAggresiveUsdListIsDisabled() {
		return ljiAggresiveUsdListIsDisabled;
	}

	public void setLjiAggresiveUsdListIsDisabled(
			String ljiAggresiveUsdListIsDisabled) {
		this.ljiAggresiveUsdListIsDisabled = ljiAggresiveUsdListIsDisabled;
	}

	public BigDecimal getAggresiveUsd() {
		return aggresiveUsd;
	}

	public void setAggresiveUsd(BigDecimal aggresiveUsd) {
		this.aggresiveUsd = aggresiveUsd;
	}

	public BigDecimal getAggresiveUsdCd() {
		return aggresiveUsdCd;
	}

	public void setAggresiveUsdCd(BigDecimal aggresiveUsdCd) {
		this.aggresiveUsdCd = aggresiveUsdCd;
	}

	public ArrayList<OptionVO> getAggresiveUsdList() {
		return aggresiveUsdList;
	}

	public void setAggresiveUsdList(List<OptionVO> aggresiveUsdList) {
		this.aggresiveUsdList = ListUtil.serializableList(aggresiveUsdList);
	}

	public String getAggresiveUsdListDisplay() {
		return aggresiveUsdListDisplay;
	}

	public void setAggresiveUsdListDisplay(String aggresiveUsdListDisplay) {
		this.aggresiveUsdListDisplay = aggresiveUsdListDisplay;
	}

	public String getAggresiveUsdListIsDisabled() {
		return aggresiveUsdListIsDisabled;
	}

	public void setAggresiveUsdListIsDisabled(String aggresiveUsdListIsDisabled) {
		this.aggresiveUsdListIsDisabled = aggresiveUsdListIsDisabled;
	}

	public String getAggresiveUsdIsDisabled() {
		return aggresiveUsdIsDisabled;
	}

	public void setAggresiveUsdIsDisabled(String aggresiveUsdIsDisabled) {
		this.aggresiveUsdIsDisabled = aggresiveUsdIsDisabled;
	}
	
	
}