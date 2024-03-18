package id.co.sinarmaslife.eproposal.web;

import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.ListUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01030103Form
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : Fadly Mathendra
 * Version              : 1.0
 * Creation Date    	: Aug 24, 2007 10:07:32 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

public class Cepr01030103Form implements Serializable
{ 
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
	 * Creation Date    	: Nov 9, 2012 10:29:59 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = -8531817588010598067L;
	private String paFlag;
    private String hcpFlag;
    private String hcpFamilyFlag;
    private String hcpProviderFlag;
    private String hcpLadiesFlag;
    private String payor5Tpd10TpdDeathFlag;
    private String payor5Tpd10CiDeathFlag;
    private String payorTpdDeathFlag;
    private String payorCiDeathFlag;
    private String payor5Ci10CiDeathFlag;
    private String payorCiFlag;
    private String payorSpouseTpdDeathFlag;
    private String payorTpdCiDeathFlag;
    private String waiver5Tpd10TpdFlag;
    private String waiver5Tpd10CiFlag;
    private String waiverTpdFlag;
    private String waiverTpdCiFlag;
    private String waiver5Ci10CiFlag;
    private String waiverCiFlag;
    private String tpdFlag;
    private String ciFlag;
    private String ladiesInsFlag;
    private String scholarshipFlag;
    private String termRiderFlag;
    private String ekaSehatFlag;
    private String ekaSehatInnerLimitFlag;
    private String ladiesMedExpenseFlag;
    private String ladiesMedExpenseInnerLimitFlag;
    private String babyFlag;
    private String earlyStageCi99Flag;
    private String medicalPlusFlag;
    private String medicalPlusRjFlag;
    private String medicalPlusRgFlag;
    private String medicalPlusRbFlag;
    private String medicalPlusPkFlag;
    private String ekaSehatExtraFlag;
    
    private BigDecimal ciRiderAmount; /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
    private BigDecimal esci99RiderAmount; /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
    private BigDecimal termRiderAmount;
    private String theEvent;
    
    private Integer paRiskCd;
    private Integer paClassCd;
    private Integer paUnitQtyCd;
    private Integer hcpCd;
    private Integer ladiesInsCd;
    private Integer scholarshipCd;
    private Integer hcpFamilyCd;
    private Integer hcpLadiesCd;
    private Integer hcpProviderCd;
    private Integer ekaSehatCd;
    private Integer ekaSehatInnerLimitCd;
    private Integer ladiesMedExpenseCd;
    private Integer ladiesMedExpenseInnerLimitCd;
//    private Integer ekaSehatProviderCd;
    private Integer ekaSehatExtraCd;

    
    private ArrayList<OptionVO> paRiskList;
    private ArrayList<OptionVO> paClassList;
    private ArrayList<OptionVO> paUnitQtyList;
    private ArrayList<OptionVO> hcpList;
    private ArrayList<OptionVO> hcpFamilyList;
    private ArrayList<OptionVO> ladiesInsList;
    private ArrayList<OptionVO> scholarshipList;
    private ArrayList<OptionVO> hcpProviderList;
    private ArrayList<OptionVO> hcpLadiesList;
    private ArrayList<OptionVO> ekaSehatList;
    private ArrayList<OptionVO> ekaSehatInnerLimitList;
    private ArrayList<OptionVO> ladiesMedExpenseList;
    private ArrayList<OptionVO> ladiesMedExpenseInnerLimitList;
//    private ArrayList<OptionVO> ekaSehatProviderList;
    private ArrayList<OptionVO> ekaSehatExtraList;

    private String paFlagIsDisabled;
    private String hcpFlagIsDisabled;
    private String hcpFamilyFlagIsDisabled;
    private String hcpLadiesFlagIsDisabled;
    private String hcpProviderFlagIsDisabled;
    private String payor5Tpd10TpdDeathFlagIsDisabled;
    private String payor5Tpd10CiDeathFlagIsDisabled;
    private String payorTpdDeathFlagIsDisabled;
    private String payorCiDeathFlagIsDisabled;
    private String payor5Ci10CiDeathFlagIsDisabled;
    private String payorCiFlagIsDisabled;
    private String payorSpouseTpdDeathFlagIsDisabled;
    private String payorTpdCiDeathFlagIsDisabled;
    private String waiver5Tpd10TpdFlagIsDisabled;
    private String waiver5Tpd10CiFlagIsDisabled;
    private String waiverTpdFlagIsDisabled;
    private String waiverTpdCiFlagIsDisabled;
    private String waiver5Ci10CiFlagIsDisabled;
    private String waiverCiFlagIsDisabled;
    private String tpdFlagIsDisabled;
    private String ciFlagIsDisabled;
    private String ladiesInsFlagIsDisabled;
    private String scholarshipFlagIsDisabled;
    private String termRiderFlagIsDisabled;
    private String termRiderAmountIsDisabled;
    private String ladiesMedExpenseFlagIsDisabled;
    private String ladiesMedExpenseInnerLimitFlagIsDisabled;
    private String babyFlagIsDisabled;
    private String earlyStageCi99FlagIsDisabled;
    private String medicalPlusFlagIsDisabled;
    private String medicalPlusRjFlagIsDisabled;
    private String medicalPlusRgFlagIsDisabled;
    private String medicalPlusRbFlagIsDisabled;
    private String medicalPlusPkFlagIsDisabled;
    private String ciRiderAmountIsDisabled; /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
    private String esci99RiderAmountIsDisabled; /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
    
    
    private String paRiskCdIsDisabled;
    private String paClassCdIsDisabled;
    private String paUnitQtyCdIsDisabled;
    private String hcpCdIsDisabled;
    private String hcpFamilyCdIsDisabled;
    private String hcpLadiesCdIsDisabled;
    private String hcpProviderCdIsDisabled;
    private String paRiskListIsDisabled;
    private String paClassListIsDisabled;
    private String paUnitQtyListIsDisabled;
    private String hcpListIsDisabled;
    private String hcpFamilyListIsDisabled;
    private String ladiesInsListIsDisabled;
    private String scholarshipListIsDisabled;
    private String hcpLadiesListIsDisabled;
    private String hcpProviderListIsDisabled;
    private String ekaSehatFlagIsDisabled;
    private String ekaSehatListIsDisabled;
    private String ekaSehatInnerLimitFlagIsDisabled;
    private String ekaSehatInnerLimitListIsDisabled;
    private String ladiesMedExpenseListIsDisabled;
    private String ladiesMedExpenseInnerLimitListIsDisabled;
//    private String ekaSehatProviderIsDisabled;
    private String buttonParticipantHcpProviderIsDisabled;
    private String buttonClassIsDisabled;
    private String buttonParticipantIsDisabled;
    private String buttonParticipantHcpLadiesIsDisabled;
    private String buttonParticipantEkaSehatIsDisabled;    
    private String buttonParticipantEkaSehatInnerLimitIsDisabled;
    private String buttonParticipantLadiesMedExpenseIsDisabled;
    private String buttonParticipantLadiesMedExpenseInnerLimitIsDisabled;
    private String ekaSehatExtraFlagIsDisabled;
    private String ekaSehatExtraListIsDisabled;
    private String buttonParticipantEkaSehatExtraIsDisabled;
    
	private String paDisplay;
    private String hcpDisplay;
    private String hcpFamilyDisplay;
    private String hcpLadiesDisplay;
    private String hcpProviderDisplay;
    private String payor5Tpd10TpdDeathDisplay;
    private String payor5Tpd10CiDeathDisplay;
    private String payorTpdDeathDisplay;
    private String payorCiDeathDisplay;
    private String payor5Ci10CiDeathDisplay;
    private String payorCiDisplay;
    private String payorSpouseTpdDeathDisplay;
    private String payorTpdCiDeathDisplay;
    private String waiver5Tpd10TpdDisplay;
    private String waiver5Tpd10CiDisplay;
    private String waiverTpdDisplay;
    private String waiverTpdCiDisplay;
    private String waiver5Ci10CiDisplay;
    private String waiverCiDisplay;
    private String tpdDisplay;
    private String ciDisplay;
    private String ladiesInsDisplay;
    private String scholarshipDisplay;
    private String termRiderDisplay;
    private String ekaSehatDisplay;
    private String ekaSehatInnerLimitDisplay;
    private String ladiesMedExpenseDisplay;
    private String ladiesMedExpenseInnerLimitDisplay;
    private String babyDisplay;
    private String earlyStageCi99Display;
    private String medicalPlusDisplay;    
    private String ekaSehatExtraDisplay;
    
    private String ciChooseCd;
    private ArrayList<OptionVO> ciChooseList;
    private String ciChooseListIsDisabled;
    
    private String esci99ChooseCd;
    private ArrayList<OptionVO> esci99ChooseList;
    private String esci99ChooseListIsDisabled;
    
    private String medicalPlusChooseCd;
    private ArrayList<OptionVO> medicalPlusChooseList;
    private String medicalPlusChooseListIsDisabled;
    
    private String ladiesInsChooseCd;
    private ArrayList<OptionVO> ladiesInsChooseList;
    private String ladiesInsChooseListIsDisabled;
    
    private String scholarshipChooseCd;
    private ArrayList<OptionVO> scholarshipChooseList;
    private String scholarshipChooseListIsDisabled;
    
    private Integer waiverTpdCiChooseCd;
    private ArrayList<OptionVO> waiverTpdCiChooseList;
    private String waiverTpdCiChooseListIsDisabled;
    
    private String babyChooseCd;
    private ArrayList<OptionVO> babyChooseList;
    private String babyChooseListIsDisabled;
    private String babyCd;
    private ArrayList<OptionVO> babyList;
    private String babyListIsDisabled;
    
    /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
    private String term5575RiderDisplay;
    private String term5575RiderFlag;
    private String term5575RiderFlagIsDisabled;
    private BigDecimal term5575RiderAmount;
    private String term5575RiderAmountIsDisabled;
    
    private Integer term5575RiderChooseCd;
    private ArrayList<OptionVO> term5575RiderChooseList;
    private String term5575RiderChooseListIsDisabled;
    
    
    public String getLadiesMedExpenseInnerLimitFlag() {
		return ladiesMedExpenseInnerLimitFlag;
	}

	public void setLadiesMedExpenseInnerLimitFlag(
			String ladiesMedExpenseInnerLimitFlag) {
		this.ladiesMedExpenseInnerLimitFlag = ladiesMedExpenseInnerLimitFlag;
	}

	public Integer getLadiesMedExpenseInnerLimitCd() {
		return ladiesMedExpenseInnerLimitCd;
	}

	public void setLadiesMedExpenseInnerLimitCd(Integer ladiesMedExpenseInnerLimitCd) {
		this.ladiesMedExpenseInnerLimitCd = ladiesMedExpenseInnerLimitCd;
	}

	public ArrayList<OptionVO> getLadiesMedExpenseInnerLimitList() {
		return ladiesMedExpenseInnerLimitList;
	}

	public void setLadiesMedExpenseInnerLimitList(
			List<OptionVO> ladiesMedExpenseInnerLimitList) {
		this.ladiesMedExpenseInnerLimitList = ListUtil.serializableList(ladiesMedExpenseInnerLimitList);
	}

	public String getLadiesMedExpenseInnerLimitFlagIsDisabled() {
		return ladiesMedExpenseInnerLimitFlagIsDisabled;
	}

	public void setLadiesMedExpenseInnerLimitFlagIsDisabled(
			String ladiesMedExpenseInnerLimitFlagIsDisabled) {
		this.ladiesMedExpenseInnerLimitFlagIsDisabled = ladiesMedExpenseInnerLimitFlagIsDisabled;
	}

	public String getLadiesMedExpenseInnerLimitListIsDisabled() {
		return ladiesMedExpenseInnerLimitListIsDisabled;
	}

	public void setLadiesMedExpenseInnerLimitListIsDisabled(
			String ladiesMedExpenseInnerLimitListIsDisabled) {
		this.ladiesMedExpenseInnerLimitListIsDisabled = ladiesMedExpenseInnerLimitListIsDisabled;
	}

	public String getButtonParticipantLadiesMedExpenseInnerLimitIsDisabled() {
		return buttonParticipantLadiesMedExpenseInnerLimitIsDisabled;
	}

	public void setButtonParticipantLadiesMedExpenseInnerLimitIsDisabled(
			String buttonParticipantLadiesMedExpenseInnerLimitIsDisabled) {
		this.buttonParticipantLadiesMedExpenseInnerLimitIsDisabled = buttonParticipantLadiesMedExpenseInnerLimitIsDisabled;
	}

	public String getLadiesMedExpenseInnerLimitDisplay() {
		return ladiesMedExpenseInnerLimitDisplay;
	}

	public void setLadiesMedExpenseInnerLimitDisplay(
			String ladiesMedExpenseInnerLimitDisplay) {
		this.ladiesMedExpenseInnerLimitDisplay = ladiesMedExpenseInnerLimitDisplay;
	}

	public String getLadiesInsFlag() {
		return ladiesInsFlag;
	}

	public void setLadiesInsFlag(String ladiesInsFlag) {
		this.ladiesInsFlag = ladiesInsFlag;
	}

	public Integer getLadiesInsCd() {
		return ladiesInsCd;
	}

	public void setLadiesInsCd(Integer ladiesInsCd) {
		this.ladiesInsCd = ladiesInsCd;
	}

	public ArrayList<OptionVO> getLadiesInsList() {
		return ladiesInsList;
	}

	public void setLadiesInsList(List<OptionVO> ladiesInsList) {
		this.ladiesInsList = ListUtil.serializableList(ladiesInsList);
	}

	public String getLadiesInsFlagIsDisabled() {
		return ladiesInsFlagIsDisabled;
	}

	public void setLadiesInsFlagIsDisabled(String ladiesInsFlagIsDisabled) {
		this.ladiesInsFlagIsDisabled = ladiesInsFlagIsDisabled;
	}

	public String getLadiesInsListIsDisabled() {
		return ladiesInsListIsDisabled;
	}

	public void setLadiesInsListIsDisabled(String ladiesInsListIsDisabled) {
		this.ladiesInsListIsDisabled = ladiesInsListIsDisabled;
	}

	public String getLadiesInsDisplay() {
		return ladiesInsDisplay;
	}

	public void setLadiesInsDisplay(String ladiesInsDisplay) {
		this.ladiesInsDisplay = ladiesInsDisplay;
	}

	public String getLadiesInsChooseCd() {
		return ladiesInsChooseCd;
	}

	public void setLadiesInsChooseCd(String ladiesInsChooseCd) {
		this.ladiesInsChooseCd = ladiesInsChooseCd;
	}

	public ArrayList<OptionVO> getLadiesInsChooseList() {
		return ladiesInsChooseList;
	}

	public void setLadiesInsChooseList(List<OptionVO> ladiesInsChooseList) {
		this.ladiesInsChooseList = ListUtil.serializableList(ladiesInsChooseList);
	}

	public String getLadiesInsChooseListIsDisabled() {
		return ladiesInsChooseListIsDisabled;
	}

	public void setLadiesInsChooseListIsDisabled(
			String ladiesInsChooseListIsDisabled) {
		this.ladiesInsChooseListIsDisabled = ladiesInsChooseListIsDisabled;
	}

	public String getButtonParticipantHcpLadiesIsDisabled() {
		return buttonParticipantHcpLadiesIsDisabled;
	}

	public void setButtonParticipantHcpLadiesIsDisabled(
			String buttonParticipantHcpLadiesIsDisabled) {
		this.buttonParticipantHcpLadiesIsDisabled = buttonParticipantHcpLadiesIsDisabled;
	}

	public String getHcpLadiesFlag() {
		return hcpLadiesFlag;
	}

	public void setHcpLadiesFlag(String hcpLadiesFlag) {
		this.hcpLadiesFlag = hcpLadiesFlag;
	}

	public Integer getHcpLadiesCd() {
		return hcpLadiesCd;
	}

	public void setHcpLadiesCd(Integer hcpLadiesCd) {
		this.hcpLadiesCd = hcpLadiesCd;
	}

	public ArrayList<OptionVO> getHcpLadiesList() {
		return hcpLadiesList;
	}

	public void setHcpLadiesList(List<OptionVO> hcpLadiesList) {
		this.hcpLadiesList = ListUtil.serializableList(hcpLadiesList);
	}

	public String getHcpLadiesFlagIsDisabled() {
		return hcpLadiesFlagIsDisabled;
	}

	public void setHcpLadiesFlagIsDisabled(String hcpLadiesFlagIsDisabled) {
		this.hcpLadiesFlagIsDisabled = hcpLadiesFlagIsDisabled;
	}

	public String getHcpLadiesCdIsDisabled() {
		return hcpLadiesCdIsDisabled;
	}

	public void setHcpLadiesCdIsDisabled(String hcpLadiesCdIsDisabled) {
		this.hcpLadiesCdIsDisabled = hcpLadiesCdIsDisabled;
	}

	public String getHcpLadiesListIsDisabled() {
		return hcpLadiesListIsDisabled;
	}

	public void setHcpLadiesListIsDisabled(String hcpLadiesListIsDisabled) {
		this.hcpLadiesListIsDisabled = hcpLadiesListIsDisabled;
	}

	public String getHcpLadiesDisplay() {
		return hcpLadiesDisplay;
	}

	public void setHcpLadiesDisplay(String hcpLadiesDisplay) {
		this.hcpLadiesDisplay = hcpLadiesDisplay;
	}

	public String getPayor5Tpd10CiDeathFlag() {
		return payor5Tpd10CiDeathFlag;
	}

	public void setPayor5Tpd10CiDeathFlag(String payor5Tpd10CiDeathFlag) {
		this.payor5Tpd10CiDeathFlag = payor5Tpd10CiDeathFlag;
	}

	public String getPayor5Tpd10CiDeathFlagIsDisabled() {
		return payor5Tpd10CiDeathFlagIsDisabled;
	}

	public void setPayor5Tpd10CiDeathFlagIsDisabled(
			String payor5Tpd10CiDeathFlagIsDisabled) {
		this.payor5Tpd10CiDeathFlagIsDisabled = payor5Tpd10CiDeathFlagIsDisabled;
	}

	public String getPayor5Tpd10CiDeathDisplay() {
		return payor5Tpd10CiDeathDisplay;
	}

	public void setPayor5Tpd10CiDeathDisplay(String payor5Tpd10CiDeathDisplay) {
		this.payor5Tpd10CiDeathDisplay = payor5Tpd10CiDeathDisplay;
	}

	public String getWaiverTpdCiFlag() {
		return waiverTpdCiFlag;
	}

	public Integer getWaiverTpdCiChooseCd() {
		return waiverTpdCiChooseCd;
	}

	public void setWaiverTpdCiChooseCd(Integer waiverTpdCiChooseCd) {
		this.waiverTpdCiChooseCd = waiverTpdCiChooseCd;
	}

	public ArrayList<OptionVO> getWaiverTpdCiChooseList() {
		return waiverTpdCiChooseList;
	}

	public void setWaiverTpdCiChooseList(List<OptionVO> waiverTpdCiChooseList) {
		this.waiverTpdCiChooseList = ListUtil.serializableList(waiverTpdCiChooseList);
	}

	public String getWaiverTpdCiChooseListIsDisabled() {
		return waiverTpdCiChooseListIsDisabled;
	}

	public void setWaiverTpdCiChooseListIsDisabled(
			String waiverTpdCiChooseListIsDisabled) {
		this.waiverTpdCiChooseListIsDisabled = waiverTpdCiChooseListIsDisabled;
	}

	public void setWaiverTpdCiFlag(String waiverTpdCiFlag) {
		this.waiverTpdCiFlag = waiverTpdCiFlag;
	}

	public String getCiChooseCd() {
		return ciChooseCd;
	}

	public void setCiChooseCd(String ciChooseCd) {
		this.ciChooseCd = ciChooseCd;
	}

	public ArrayList<OptionVO> getCiChooseList() {
		return ciChooseList;
	}

	public void setCiChooseList(List<OptionVO> ciChooseList) {
		this.ciChooseList = ListUtil.serializableList(ciChooseList);
	}

	public String getCiChooseListIsDisabled() {
		return ciChooseListIsDisabled;
	}

	public void setCiChooseListIsDisabled(String ciChooseListIsDisabled) {
		this.ciChooseListIsDisabled = ciChooseListIsDisabled;
	}

	public String getButtonParticipantHcpProviderIsDisabled() {
		return buttonParticipantHcpProviderIsDisabled;
	}

	public void setButtonParticipantHcpProviderIsDisabled(
			String buttonParticipantHcpProviderIsDisabled) {
		this.buttonParticipantHcpProviderIsDisabled = buttonParticipantHcpProviderIsDisabled;
	}

	public String getHcpProviderFlag() {
		return hcpProviderFlag;
	}

	public void setHcpProviderFlag(String hcpProviderFlag) {
		this.hcpProviderFlag = hcpProviderFlag;
	}

	public Integer getHcpProviderCd() {
		return hcpProviderCd;
	}

	public void setHcpProviderCd(Integer hcpProviderCd) {
		this.hcpProviderCd = hcpProviderCd;
	}

	public ArrayList<OptionVO> getHcpProviderList() {
		return hcpProviderList;
	}

	public void setHcpProviderList(List<OptionVO> hcpProviderList) {
		this.hcpProviderList = ListUtil.serializableList(hcpProviderList);
	}

	public String getHcpProviderFlagIsDisabled() {
		return hcpProviderFlagIsDisabled;
	}

	public void setHcpProviderFlagIsDisabled(String hcpProviderFlagIsDisabled) {
		this.hcpProviderFlagIsDisabled = hcpProviderFlagIsDisabled;
	}

	public String getHcpProviderCdIsDisabled() {
		return hcpProviderCdIsDisabled;
	}

	public void setHcpProviderCdIsDisabled(String hcpProviderCdIsDisabled) {
		this.hcpProviderCdIsDisabled = hcpProviderCdIsDisabled;
	}

	public String getHcpProviderListIsDisabled() {
		return hcpProviderListIsDisabled;
	}

	public void setHcpProviderListIsDisabled(String hcpProviderListIsDisabled) {
		this.hcpProviderListIsDisabled = hcpProviderListIsDisabled;
	}

	public String getHcpProviderDisplay() {
		return hcpProviderDisplay;
	}

	public void setHcpProviderDisplay(String hcpProviderDisplay) {
		this.hcpProviderDisplay = hcpProviderDisplay;
	}

	public String getEkaSehatDisplay() {
		return ekaSehatDisplay;
	}

	public void setEkaSehatDisplay(String ekaSehatDisplay) {
		this.ekaSehatDisplay = ekaSehatDisplay;
	}

	public String getPaFlag()
    {
        return paFlag;
    }

    public void setPaFlag( String paFlag )
    {
        this.paFlag = paFlag;
    }

    public String getHcpFlag()
    {
        return hcpFlag;
    }

    public void setHcpFlag( String hcpFlag )
    {
        this.hcpFlag = hcpFlag;
    }

    public String getHcpFamilyFlag()
    {
        return hcpFamilyFlag;
    }

    public void setHcpFamilyFlag( String hcpFamilyFlag )
    {
        this.hcpFamilyFlag = hcpFamilyFlag;
    }

    public String getPayorTpdDeathFlag()
    {
        return payorTpdDeathFlag;
    }

    public void setPayorTpdDeathFlag( String payorTpdDeathFlag )
    {
        this.payorTpdDeathFlag = payorTpdDeathFlag;
    }

    public String getPayorCiDeathFlag()
    {
        return payorCiDeathFlag;
    }

    public void setPayorCiDeathFlag( String payorCiDeathFlag )
    {
        this.payorCiDeathFlag = payorCiDeathFlag;
    }

    public String getPayorCiFlag()
    {
        return payorCiFlag;
    }

    public void setPayorCiFlag( String payorCiFlag )
    {
        this.payorCiFlag = payorCiFlag;
    }

    public String getPayorSpouseTpdDeathFlag()
    {
        return payorSpouseTpdDeathFlag;
    }

    public void setPayorSpouseTpdDeathFlag( String payorSpouseTpdDeathFlag )
    {
        this.payorSpouseTpdDeathFlag = payorSpouseTpdDeathFlag;
    }

    public String getWaiverTpdCiFlagIsDisabled() {
		return waiverTpdCiFlagIsDisabled;
	}

	public void setWaiverTpdCiFlagIsDisabled(String waiverTpdCiFlagIsDisabled) {
		this.waiverTpdCiFlagIsDisabled = waiverTpdCiFlagIsDisabled;
	}

	public String getWaiverTpdCiDisplay() {
		return waiverTpdCiDisplay;
	}

	public void setWaiverTpdCiDisplay(String waiverTpdCiDisplay) {
		this.waiverTpdCiDisplay = waiverTpdCiDisplay;
	}

	public String getWaiverTpdFlag()
    {
        return waiverTpdFlag;
    }

    public void setWaiverTpdFlag( String waiverTpdFlag )
    {
        this.waiverTpdFlag = waiverTpdFlag;
    }

    public String getWaiverCiFlag()
    {
        return waiverCiFlag;
    }

    public void setWaiverCiFlag( String waiverCiFlag )
    {
        this.waiverCiFlag = waiverCiFlag;
    }

    public String getTpdFlag()
    {
        return tpdFlag;
    }

    public void setTpdFlag( String tpdFlag )
    {
        this.tpdFlag = tpdFlag;
    }

    public String getCiFlag()
    {
        return ciFlag;
    }

    public void setCiFlag( String ciFlag )
    {
        this.ciFlag = ciFlag;
    }

    public String getTermRiderFlag()
    {
        return termRiderFlag;
    }

    public void setTermRiderFlag( String termRiderFlag )
    {
        this.termRiderFlag = termRiderFlag;
    }

    public BigDecimal getTermRiderAmount()
    {
        return termRiderAmount;
    }

    public void setTermRiderAmount( BigDecimal termRiderAmount )
    {
        this.termRiderAmount = termRiderAmount;
    }

    public Integer getPaRiskCd()
    {
        return paRiskCd;
    }

    public void setPaRiskCd( Integer paRiskCd )
    {
        this.paRiskCd = paRiskCd;
    }

    public Integer getPaClassCd()
    {
        return paClassCd;
    }

    public void setPaClassCd( Integer paClassCd )
    {
        this.paClassCd = paClassCd;
    }

    public Integer getPaUnitQtyCd()
    {
        return paUnitQtyCd;
    }

    public void setPaUnitQtyCd( Integer paUnitQtyCd )
    {
        this.paUnitQtyCd = paUnitQtyCd;
    }

    public Integer getHcpCd()
    {
        return hcpCd;
    }

    public void setHcpCd( Integer hcpCd )
    {
        this.hcpCd = hcpCd;
    }

    public Integer getHcpFamilyCd()
    {
        return hcpFamilyCd;
    }

    public void setHcpFamilyCd( Integer hcpFamilyCd )
    {
        this.hcpFamilyCd = hcpFamilyCd;
    }

    public ArrayList<OptionVO> getPaRiskList()
    {
        return paRiskList;
    }

    public void setPaRiskList( List<OptionVO> paRiskList )
    {
        this.paRiskList = ListUtil.serializableList(paRiskList);
    }

    public ArrayList<OptionVO> getPaClassList()
    {
        return paClassList;
    }

    public void setPaClassList( List<OptionVO> paClassList )
    {
        this.paClassList = ListUtil.serializableList(paClassList);
    }

    public ArrayList<OptionVO> getPaUnitQtyList()
    {
        return paUnitQtyList;
    }

    public void setPaUnitQtyList( List<OptionVO> paUnitQtyList )
    {
        this.paUnitQtyList = ListUtil.serializableList(paUnitQtyList);
    }

    public ArrayList<OptionVO> getHcpList()
    {
        return hcpList;
    }

    public void setHcpList( List<OptionVO> hcpList )
    {
        this.hcpList = ListUtil.serializableList(hcpList);
    }

    public ArrayList<OptionVO> getHcpFamilyList()
    {
        return hcpFamilyList;
    }

    public void setHcpFamilyList( List<OptionVO> hcpFamilyList )
    {
        this.hcpFamilyList = ListUtil.serializableList(hcpFamilyList);
    }

    public String getPaFlagIsDisabled()
    {
        return paFlagIsDisabled;
    }

    public void setPaFlagIsDisabled( String paFlagIsDisabled )
    {
        this.paFlagIsDisabled = paFlagIsDisabled;
    }

    public String getHcpFlagIsDisabled()
    {
        return hcpFlagIsDisabled;
    }

    public void setHcpFlagIsDisabled( String hcpFlagIsDisabled )
    {
        this.hcpFlagIsDisabled = hcpFlagIsDisabled;
    }

    public String getHcpFamilyFlagIsDisabled()
    {
        return hcpFamilyFlagIsDisabled;
    }

    public void setHcpFamilyFlagIsDisabled( String hcpFamilyFlagIsDisabled )
    {
        this.hcpFamilyFlagIsDisabled = hcpFamilyFlagIsDisabled;
    }

    public String getPayorTpdDeathFlagIsDisabled()
    {
        return payorTpdDeathFlagIsDisabled;
    }

    public void setPayorTpdDeathFlagIsDisabled( String payorTpdDeathFlagIsDisabled )
    {
        this.payorTpdDeathFlagIsDisabled = payorTpdDeathFlagIsDisabled;
    }

    public String getPayorCiDeathFlagIsDisabled()
    {
        return payorCiDeathFlagIsDisabled;
    }

    public void setPayorCiDeathFlagIsDisabled( String payorCiDeathFlagIsDisabled )
    {
        this.payorCiDeathFlagIsDisabled = payorCiDeathFlagIsDisabled;
    }

    public String getPayorCiFlagIsDisabled()
    {
        return payorCiFlagIsDisabled;
    }

    public void setPayorCiFlagIsDisabled( String payorCiFlagIsDisabled )
    {
        this.payorCiFlagIsDisabled = payorCiFlagIsDisabled;
    }

    public String getPayorSpouseTpdDeathFlagIsDisabled()
    {
        return payorSpouseTpdDeathFlagIsDisabled;
    }

    public void setPayorSpouseTpdDeathFlagIsDisabled( String payorSpouseTpdDeathFlagIsDisabled )
    {
        this.payorSpouseTpdDeathFlagIsDisabled = payorSpouseTpdDeathFlagIsDisabled;
    }

    public String getWaiverTpdFlagIsDisabled()
    {
        return waiverTpdFlagIsDisabled;
    }

    public void setWaiverTpdFlagIsDisabled( String waiverTpdFlagIsDisabled )
    {
        this.waiverTpdFlagIsDisabled = waiverTpdFlagIsDisabled;
    }

    public String getWaiverCiFlagIsDisabled()
    {
        return waiverCiFlagIsDisabled;
    }

    public void setWaiverCiFlagIsDisabled( String waiverCiFlagIsDisabled )
    {
        this.waiverCiFlagIsDisabled = waiverCiFlagIsDisabled;
    }

    public String getTpdFlagIsDisabled()
    {
        return tpdFlagIsDisabled;
    }

    public void setTpdFlagIsDisabled( String tpdFlagIsDisabled )
    {
        this.tpdFlagIsDisabled = tpdFlagIsDisabled;
    }

    public String getCiFlagIsDisabled()
    {
        return ciFlagIsDisabled;
    }

    public void setCiFlagIsDisabled( String ciFlagIsDisabled )
    {
        this.ciFlagIsDisabled = ciFlagIsDisabled;
    }

    public String getTermRiderFlagIsDisabled()
    {
        return termRiderFlagIsDisabled;
    }

    public void setTermRiderFlagIsDisabled( String termRiderFlagIsDisabled )
    {
        this.termRiderFlagIsDisabled = termRiderFlagIsDisabled;
    }

    public String getTermRiderAmountIsDisabled()
    {
        return termRiderAmountIsDisabled;
    }

    public void setTermRiderAmountIsDisabled( String termRiderAmountIsDisabled )
    {
        this.termRiderAmountIsDisabled = termRiderAmountIsDisabled;
    }

    public String getPaRiskCdIsDisabled()
    {
        return paRiskCdIsDisabled;
    }

    public void setPaRiskCdIsDisabled( String paRiskCdIsDisabled )
    {
        this.paRiskCdIsDisabled = paRiskCdIsDisabled;
    }

    public String getPaClassCdIsDisabled()
    {
        return paClassCdIsDisabled;
    }

    public void setPaClassCdIsDisabled( String paClassCdIsDisabled )
    {
        this.paClassCdIsDisabled = paClassCdIsDisabled;
    }

    public String getPaUnitQtyCdIsDisabled()
    {
        return paUnitQtyCdIsDisabled;
    }

    public void setPaUnitQtyCdIsDisabled( String paUnitQtyCdIsDisabled )
    {
        this.paUnitQtyCdIsDisabled = paUnitQtyCdIsDisabled;
    }

    public String getHcpCdIsDisabled()
    {
        return hcpCdIsDisabled;
    }

    public void setHcpCdIsDisabled( String hcpCdIsDisabled )
    {
        this.hcpCdIsDisabled = hcpCdIsDisabled;
    }

    public String getHcpFamilyCdIsDisabled()
    {
        return hcpFamilyCdIsDisabled;
    }

    public void setHcpFamilyCdIsDisabled( String hcpFamilyCdIsDisabled )
    {
        this.hcpFamilyCdIsDisabled = hcpFamilyCdIsDisabled;
    }

    public String getPaRiskListIsDisabled()
    {
        return paRiskListIsDisabled;
    }

    public void setPaRiskListIsDisabled( String paRiskListIsDisabled )
    {
        this.paRiskListIsDisabled = paRiskListIsDisabled;
    }

    public String getPaClassListIsDisabled()
    {
        return paClassListIsDisabled;
    }

    public void setPaClassListIsDisabled( String paClassListIsDisabled )
    {
        this.paClassListIsDisabled = paClassListIsDisabled;
    }

    public String getPaUnitQtyListIsDisabled()
    {
        return paUnitQtyListIsDisabled;
    }

    public void setPaUnitQtyListIsDisabled( String paUnitQtyListIsDisabled )
    {
        this.paUnitQtyListIsDisabled = paUnitQtyListIsDisabled;
    }

    public String getHcpListIsDisabled()
    {
        return hcpListIsDisabled;
    }

    public void setHcpListIsDisabled( String hcpListIsDisabled )
    {
        this.hcpListIsDisabled = hcpListIsDisabled;
    }

    public String getHcpFamilyListIsDisabled()
    {
        return hcpFamilyListIsDisabled;
    }

    public void setHcpFamilyListIsDisabled( String hcpFamilyListIsDisabled )
    {
        this.hcpFamilyListIsDisabled = hcpFamilyListIsDisabled;
    }

    public String getButtonClassIsDisabled()
    {
        return buttonClassIsDisabled;
    }

    public void setButtonClassIsDisabled( String buttonClassIsDisabled )
    {
        this.buttonClassIsDisabled = buttonClassIsDisabled;
    }

    public String getButtonParticipantIsDisabled()
    {
        return buttonParticipantIsDisabled;
    }

    public void setButtonParticipantIsDisabled( String buttonParticipantIsDisabled )
    {
        this.buttonParticipantIsDisabled = buttonParticipantIsDisabled;
    }

    public String getPaDisplay()
    {
        return paDisplay;
    }

    public void setPaDisplay( String paDisplay )
    {
        this.paDisplay = paDisplay;
    }

    public String getHcpDisplay()
    {
        return hcpDisplay;
    }

    public void setHcpDisplay( String hcpDisplay )
    {
        this.hcpDisplay = hcpDisplay;
    }

    public String getHcpFamilyDisplay()
    {
        return hcpFamilyDisplay;
    }

    public void setHcpFamilyDisplay( String hcpFamilyDisplay )
    {
        this.hcpFamilyDisplay = hcpFamilyDisplay;
    }

    public String getPayorTpdDeathDisplay()
    {
        return payorTpdDeathDisplay;
    }

    public void setPayorTpdDeathDisplay( String payorTpdDeathDisplay )
    {
        this.payorTpdDeathDisplay = payorTpdDeathDisplay;
    }

    public String getPayorCiDeathDisplay()
    {
        return payorCiDeathDisplay;
    }

    public void setPayorCiDeathDisplay( String payorCiDeathDisplay )
    {
        this.payorCiDeathDisplay = payorCiDeathDisplay;
    }

    public String getPayorCiDisplay()
    {
        return payorCiDisplay;
    }

    public void setPayorCiDisplay( String payorCiDisplay )
    {
        this.payorCiDisplay = payorCiDisplay;
    }

    public String getPayorSpouseTpdDeathDisplay()
    {
        return payorSpouseTpdDeathDisplay;
    }

    public void setPayorSpouseTpdDeathDisplay( String payorSpouseTpdDeathDisplay )
    {
        this.payorSpouseTpdDeathDisplay = payorSpouseTpdDeathDisplay;
    }

    public String getWaiverTpdDisplay()
    {
        return waiverTpdDisplay;
    }

    public void setWaiverTpdDisplay( String waiverTpdDisplay )
    {
        this.waiverTpdDisplay = waiverTpdDisplay;
    }

    public String getWaiverCiDisplay()
    {
        return waiverCiDisplay;
    }

    public void setWaiverCiDisplay( String waiverCiDisplay )
    {
        this.waiverCiDisplay = waiverCiDisplay;
    }

    public String getTpdDisplay()
    {
        return tpdDisplay;
    }

    public void setTpdDisplay( String tpdDisplay )
    {
        this.tpdDisplay = tpdDisplay;
    }

    public String getCiDisplay()
    {
        return ciDisplay;
    }

    public void setCiDisplay( String ciDisplay )
    {
        this.ciDisplay = ciDisplay;
    }

    public String getTermRiderDisplay()
    {
        return termRiderDisplay;
    }

    public void setTermRiderDisplay( String termRiderDisplay )
    {
        this.termRiderDisplay = termRiderDisplay;
    }

	public String getPayor5Ci10CiDeathDisplay() {
		return payor5Ci10CiDeathDisplay;
	}

	public void setPayor5Ci10CiDeathDisplay(String payor5Ci10CiDeathDisplay) {
		this.payor5Ci10CiDeathDisplay = payor5Ci10CiDeathDisplay;
	}

	public String getPayor5Ci10CiDeathFlag() {
		return payor5Ci10CiDeathFlag;
	}

	public void setPayor5Ci10CiDeathFlag(String payor5Ci10CiDeathFlag) {
		this.payor5Ci10CiDeathFlag = payor5Ci10CiDeathFlag;
	}

	public String getPayor5Ci10CiDeathFlagIsDisabled() {
		return payor5Ci10CiDeathFlagIsDisabled;
	}

	public void setPayor5Ci10CiDeathFlagIsDisabled(String payor5Ci10CiDeathFlagIsDisabled) {
		this.payor5Ci10CiDeathFlagIsDisabled = payor5Ci10CiDeathFlagIsDisabled;
	}

	public String getPayor5Tpd10TpdDeathDisplay() {
		return payor5Tpd10TpdDeathDisplay;
	}

	public void setPayor5Tpd10TpdDeathDisplay(String payor5Tpd10TpdDeathDisplay) {
		this.payor5Tpd10TpdDeathDisplay = payor5Tpd10TpdDeathDisplay;
	}

	public String getPayor5Tpd10TpdDeathFlag() {
		return payor5Tpd10TpdDeathFlag;
	}

	public void setPayor5Tpd10TpdDeathFlag(String payor5Tpd10TpdDeathFlag) {
		this.payor5Tpd10TpdDeathFlag = payor5Tpd10TpdDeathFlag;
	}

	public String getPayor5Tpd10TpdDeathFlagIsDisabled() {
		return payor5Tpd10TpdDeathFlagIsDisabled;
	}

	public void setPayor5Tpd10TpdDeathFlagIsDisabled(
			String payor5Tpd10TpdDeathFlagIsDisabled) {
		this.payor5Tpd10TpdDeathFlagIsDisabled = payor5Tpd10TpdDeathFlagIsDisabled;
	}

	public String getWaiver5Ci10CiFlag() {
		return waiver5Ci10CiFlag;
	}

	public void setWaiver5Ci10CiFlag(String waiver5Ci10CiFlag) {
		this.waiver5Ci10CiFlag = waiver5Ci10CiFlag;
	}

	public String getWaiver5Tpd10TpdFlag() {
		return waiver5Tpd10TpdFlag;
	}

	public void setWaiver5Tpd10TpdFlag(String waiver5Tpd10TpdFlag) {
		this.waiver5Tpd10TpdFlag = waiver5Tpd10TpdFlag;
	}

	public String getWaiver5Ci10CiDisplay() {
		return waiver5Ci10CiDisplay;
	}

	public void setWaiver5Ci10CiDisplay(String waiver5Ci10CiDisplay) {
		this.waiver5Ci10CiDisplay = waiver5Ci10CiDisplay;
	}

	public String getWaiver5Ci10CiFlagIsDisabled() {
		return waiver5Ci10CiFlagIsDisabled;
	}

	public void setWaiver5Ci10CiFlagIsDisabled(String waiver5Ci10CiFlagIsDisabled) {
		this.waiver5Ci10CiFlagIsDisabled = waiver5Ci10CiFlagIsDisabled;
	}

	public String getWaiver5Tpd10TpdDisplay() {
		return waiver5Tpd10TpdDisplay;
	}

	public void setWaiver5Tpd10TpdDisplay(String waiver5Tpd10TpdDisplay) {
		this.waiver5Tpd10TpdDisplay = waiver5Tpd10TpdDisplay;
	}

	public String getWaiver5Tpd10TpdFlagIsDisabled() {
		return waiver5Tpd10TpdFlagIsDisabled;
	}

	public void setWaiver5Tpd10TpdFlagIsDisabled(
			String waiver5Tpd10TpdFlagIsDisabled) {
		this.waiver5Tpd10TpdFlagIsDisabled = waiver5Tpd10TpdFlagIsDisabled;
	}

	public String getEkaSehatFlag() {
		return ekaSehatFlag;
	}

	public void setEkaSehatFlag(String ekaSehatFlag) {
		this.ekaSehatFlag = ekaSehatFlag;
	}

	public String getEkaSehatFlagIsDisabled() {
		return ekaSehatFlagIsDisabled;
	}

	public void setEkaSehatFlagIsDisabled(String ekaSehatFlagIsDisabled) {
		this.ekaSehatFlagIsDisabled = ekaSehatFlagIsDisabled;
	}

	public ArrayList<OptionVO> getEkaSehatList() {
		return ekaSehatList;
	}

	public void setEkaSehatList(List<OptionVO> ekaSehatList) {
		this.ekaSehatList = ListUtil.serializableList(ekaSehatList);
	}

	public String getEkaSehatListIsDisabled() {
		return ekaSehatListIsDisabled;
	}

	public void setEkaSehatListIsDisabled(String ekaSehatListIsDisabled) {
		this.ekaSehatListIsDisabled = ekaSehatListIsDisabled;
	}

	public Integer getEkaSehatCd() {
		return ekaSehatCd;
	}

	public void setEkaSehatCd(Integer ekaSehatCd) {
		this.ekaSehatCd = ekaSehatCd;
	}

	public String getButtonParticipantEkaSehatIsDisabled() {
		return buttonParticipantEkaSehatIsDisabled;
	}

	public void setButtonParticipantEkaSehatIsDisabled(
			String buttonParticipantEkaSehatIsDisabled) {
		this.buttonParticipantEkaSehatIsDisabled = buttonParticipantEkaSehatIsDisabled;
	}

	public String getEkaSehatInnerLimitFlag() {
		return ekaSehatInnerLimitFlag;
	}

	public void setEkaSehatInnerLimitFlag(String ekaSehatInnerLimitFlag) {
		this.ekaSehatInnerLimitFlag = ekaSehatInnerLimitFlag;
	}

	public Integer getEkaSehatInnerLimitCd() {
		return ekaSehatInnerLimitCd;
	}

	public void setEkaSehatInnerLimitCd(Integer ekaSehatInnerLimitCd) {
		this.ekaSehatInnerLimitCd = ekaSehatInnerLimitCd;
	}

	public ArrayList<OptionVO> getEkaSehatInnerLimitList() {
		return ekaSehatInnerLimitList;
	}

	public void setEkaSehatInnerLimitList(List<OptionVO> ekaSehatInnerLimitList) {
		this.ekaSehatInnerLimitList = ListUtil.serializableList(ekaSehatInnerLimitList);
	}

	public String getEkaSehatInnerLimitFlagIsDisabled() {
		return ekaSehatInnerLimitFlagIsDisabled;
	}

	public void setEkaSehatInnerLimitFlagIsDisabled(
			String ekaSehatInnerLimitFlagIsDisabled) {
		this.ekaSehatInnerLimitFlagIsDisabled = ekaSehatInnerLimitFlagIsDisabled;
	}

	public String getEkaSehatInnerLimitListIsDisabled() {
		return ekaSehatInnerLimitListIsDisabled;
	}

	public void setEkaSehatInnerLimitListIsDisabled(
			String ekaSehatInnerLimitListIsDisabled) {
		this.ekaSehatInnerLimitListIsDisabled = ekaSehatInnerLimitListIsDisabled;
	}

	public String getButtonParticipantEkaSehatInnerLimitIsDisabled() {
		return buttonParticipantEkaSehatInnerLimitIsDisabled;
	}

	public void setButtonParticipantEkaSehatInnerLimitIsDisabled(
			String buttonParticipantEkaSehatInnerLimitIsDisabled) {
		this.buttonParticipantEkaSehatInnerLimitIsDisabled = buttonParticipantEkaSehatInnerLimitIsDisabled;
	}

	public String getEkaSehatInnerLimitDisplay() {
		return ekaSehatInnerLimitDisplay;
	}

	public void setEkaSehatInnerLimitDisplay(String ekaSehatInnerLimitDisplay) {
		this.ekaSehatInnerLimitDisplay = ekaSehatInnerLimitDisplay;
	}

	public String getPayorTpdCiDeathDisplay() {
		return payorTpdCiDeathDisplay;
	}

	public void setPayorTpdCiDeathDisplay(String payorTpdCiDeathDisplay) {
		this.payorTpdCiDeathDisplay = payorTpdCiDeathDisplay;
	}
	
	public String getPayorTpdCiDeathFlag() {
		return payorTpdCiDeathFlag;
	}

	public void setPayorTpdCiDeathFlag(String payorTpdCiDeathFlag) {
		this.payorTpdCiDeathFlag = payorTpdCiDeathFlag;
	}

	public String getPayorTpdCiDeathFlagIsDisabled() {
		return payorTpdCiDeathFlagIsDisabled;
	}

	public void setPayorTpdCiDeathFlagIsDisabled(
			String payorTpdCiDeathFlagIsDisabled) {
		this.payorTpdCiDeathFlagIsDisabled = payorTpdCiDeathFlagIsDisabled;
	}
	
	public String getLadiesMedExpenseFlag() {
		return ladiesMedExpenseFlag;
	}

	public void setLadiesMedExpenseFlag(String ladiesMedExpenseFlag) {
		this.ladiesMedExpenseFlag = ladiesMedExpenseFlag;
	}

	public Integer getLadiesMedExpenseCd() {
		return ladiesMedExpenseCd;
	}

	public void setLadiesMedExpenseCd(Integer ladiesMedExpenseCd) {
		this.ladiesMedExpenseCd = ladiesMedExpenseCd;
	}

	public ArrayList<OptionVO> getLadiesMedExpenseList() {
		return ladiesMedExpenseList;
	}

	public void setLadiesMedExpenseList(List<OptionVO> ladiesMedExpenseList) {
		this.ladiesMedExpenseList = ListUtil.serializableList(ladiesMedExpenseList);
	}

	public String getLadiesMedExpenseFlagIsDisabled() {
		return ladiesMedExpenseFlagIsDisabled;
	}

	public void setLadiesMedExpenseFlagIsDisabled(
			String ladiesMedExpenseFlagIsDisabled) {
		this.ladiesMedExpenseFlagIsDisabled = ladiesMedExpenseFlagIsDisabled;
	}

	public String getLadiesMedExpenseListIsDisabled() {
		return ladiesMedExpenseListIsDisabled;
	}

	public void setLadiesMedExpenseListIsDisabled(
			String ladiesMedExpenseListIsDisabled) {
		this.ladiesMedExpenseListIsDisabled = ladiesMedExpenseListIsDisabled;
	}

	public String getLadiesMedExpenseDisplay() {
		return ladiesMedExpenseDisplay;
	}

	public void setLadiesMedExpenseDisplay(String ladiesMedExpenseDisplay) {
		this.ladiesMedExpenseDisplay = ladiesMedExpenseDisplay;
	}

    public String getButtonParticipantLadiesMedExpenseIsDisabled() {
		return buttonParticipantLadiesMedExpenseIsDisabled;
	}

	public void setButtonParticipantLadiesMedExpenseIsDisabled(
			String buttonParticipantLadiesMedExpenseIsDisabled) {
		this.buttonParticipantLadiesMedExpenseIsDisabled = buttonParticipantLadiesMedExpenseIsDisabled;
	}

	public String getWaiver5Tpd10CiFlag() {
		return waiver5Tpd10CiFlag;
	}

	public void setWaiver5Tpd10CiFlag(String waiver5Tpd10CiFlag) {
		this.waiver5Tpd10CiFlag = waiver5Tpd10CiFlag;
	}

	public String getWaiver5Tpd10CiFlagIsDisabled() {
		return waiver5Tpd10CiFlagIsDisabled;
	}

	public void setWaiver5Tpd10CiFlagIsDisabled(String waiver5Tpd10CiFlagIsDisabled) {
		this.waiver5Tpd10CiFlagIsDisabled = waiver5Tpd10CiFlagIsDisabled;
	}

	public String getWaiver5Tpd10CiDisplay() {
		return waiver5Tpd10CiDisplay;
	}

	public void setWaiver5Tpd10CiDisplay(String waiver5Tpd10CiDisplay) {
		this.waiver5Tpd10CiDisplay = waiver5Tpd10CiDisplay;
	}

	public String getTheEvent() {
		return theEvent;
	}

	public void setTheEvent(String theEvent) {
		this.theEvent = theEvent;
	}

	public String getScholarshipFlag() {
		return scholarshipFlag;
	}

	public void setScholarshipFlag(String scholarshipFlag) {
		this.scholarshipFlag = scholarshipFlag;
	}

	public Integer getScholarshipCd() {
		return scholarshipCd;
	}

	public void setScholarshipCd(Integer scholarshipCd) {
		this.scholarshipCd = scholarshipCd;
	}

	public ArrayList<OptionVO> getScholarshipList() {
		return scholarshipList;
	}

	public void setScholarshipList(List<OptionVO> scholarshipList) {
		this.scholarshipList = ListUtil.serializableList(scholarshipList);
	}

	public String getScholarshipFlagIsDisabled() {
		return scholarshipFlagIsDisabled;
	}

	public void setScholarshipFlagIsDisabled(String scholarshipFlagIsDisabled) {
		this.scholarshipFlagIsDisabled = scholarshipFlagIsDisabled;
	}

	public String getScholarshipListIsDisabled() {
		return scholarshipListIsDisabled;
	}

	public void setScholarshipListIsDisabled(String scholarshipListIsDisabled) {
		this.scholarshipListIsDisabled = scholarshipListIsDisabled;
	}

	public String getScholarshipDisplay() {
		return scholarshipDisplay;
	}

	public void setScholarshipDisplay(String scholarshipDisplay) {
		this.scholarshipDisplay = scholarshipDisplay;
	}

	public String getScholarshipChooseCd() {
		return scholarshipChooseCd;
	}

	public void setScholarshipChooseCd(String scholarshipChooseCd) {
		this.scholarshipChooseCd = scholarshipChooseCd;
	}

	public ArrayList<OptionVO> getScholarshipChooseList() {
		return scholarshipChooseList;
	}

	public void setScholarshipChooseList(List<OptionVO> scholarshipChooseList) {
		this.scholarshipChooseList = ListUtil.serializableList(scholarshipChooseList);
	}

	public String getScholarshipChooseListIsDisabled() {
		return scholarshipChooseListIsDisabled;
	}

	public void setScholarshipChooseListIsDisabled(
			String scholarshipChooseListIsDisabled) {
		this.scholarshipChooseListIsDisabled = scholarshipChooseListIsDisabled;
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

	public String getBabyDisplay() {
		return babyDisplay;
	}

	public void setBabyDisplay(String babyDisplay) {
		this.babyDisplay = babyDisplay;
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

	public String getBabyChooseListIsDisabled() {
		return babyChooseListIsDisabled;
	}

	public void setBabyChooseListIsDisabled(String babyChooseListIsDisabled) {
		this.babyChooseListIsDisabled = babyChooseListIsDisabled;
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
		this.babyList = ListUtil.serializableList(babyList);
	}

	public String getBabyListIsDisabled() {
		return babyListIsDisabled;
	}

	public void setBabyListIsDisabled(String babyListIsDisabled) {
		this.babyListIsDisabled = babyListIsDisabled;
	}
	

	public String getEarlyStageCi99Flag() {
		return earlyStageCi99Flag;
	}

	public void setEarlyStageCi99Flag(String earlyStageCi99Flag) {
		this.earlyStageCi99Flag = earlyStageCi99Flag;
	}

	public String getEarlyStageCi99FlagIsDisabled() {
		return earlyStageCi99FlagIsDisabled;
	}

	public void setEarlyStageCi99FlagIsDisabled(String earlyStageCi99FlagIsDisabled) {
		this.earlyStageCi99FlagIsDisabled = earlyStageCi99FlagIsDisabled;
	}

	public String getEarlyStageCi99Display() {
		return earlyStageCi99Display;
	}

	public void setEarlyStageCi99Display(String earlyStageCi99Display) {
		this.earlyStageCi99Display = earlyStageCi99Display;
	}

	public String getEsci99ChooseCd() {
		return esci99ChooseCd;
	}

	public void setEsci99ChooseCd(String esci99ChooseCd) {
		this.esci99ChooseCd = esci99ChooseCd;
	}

	public ArrayList<OptionVO> getEsci99ChooseList() {
		return esci99ChooseList;
	}

	public void setEsci99ChooseList(List<OptionVO> esci99ChooseList) {
		this.esci99ChooseList = ListUtil.serializableList(esci99ChooseList);
	}

	public String getEsci99ChooseListIsDisabled() {
		return esci99ChooseListIsDisabled;
	}

	public void setEsci99ChooseListIsDisabled(String esci99ChooseListIsDisabled) {
		this.esci99ChooseListIsDisabled = esci99ChooseListIsDisabled;
	}

	public String getMedicalPlusFlag() {
		return medicalPlusFlag;
	}

	public void setMedicalPlusFlag(String medicalPlusFlag) {
		this.medicalPlusFlag = medicalPlusFlag;
	}

	public String getMedicalPlusRjFlag() {
		return medicalPlusRjFlag;
	}

	public void setMedicalPlusRjFlag(String medicalPlusRjFlag) {
		this.medicalPlusRjFlag = medicalPlusRjFlag;
	}

	public String getMedicalPlusRgFlag() {
		return medicalPlusRgFlag;
	}

	public void setMedicalPlusRgFlag(String medicalPlusRgFlag) {
		this.medicalPlusRgFlag = medicalPlusRgFlag;
	}

	public String getMedicalPlusFlagIsDisabled() {
		return medicalPlusFlagIsDisabled;
	}

	public void setMedicalPlusFlagIsDisabled(String medicalPlusFlagIsDisabled) {
		this.medicalPlusFlagIsDisabled = medicalPlusFlagIsDisabled;
	}

	public String getMedicalPlusRjFlagIsDisabled() {
		return medicalPlusRjFlagIsDisabled;
	}

	public void setMedicalPlusRjFlagIsDisabled(String medicalPlusRjFlagIsDisabled) {
		this.medicalPlusRjFlagIsDisabled = medicalPlusRjFlagIsDisabled;
	}

	public String getMedicalPlusRgFlagIsDisabled() {
		return medicalPlusRgFlagIsDisabled;
	}

	public void setMedicalPlusRgFlagIsDisabled(String medicalPlusRgFlagIsDisabled) {
		this.medicalPlusRgFlagIsDisabled = medicalPlusRgFlagIsDisabled;
	}

	public String getMedicalPlusDisplay() {
		return medicalPlusDisplay;
	}

	public void setMedicalPlusDisplay(String medicalPlusDisplay) {
		this.medicalPlusDisplay = medicalPlusDisplay;
	}

	public String getMedicalPlusChooseCd() {
		return medicalPlusChooseCd;
	}

	public void setMedicalPlusChooseCd(String medicalPlusChooseCd) {
		this.medicalPlusChooseCd = medicalPlusChooseCd;
	}

	public ArrayList<OptionVO> getMedicalPlusChooseList() {
		return medicalPlusChooseList;
	}

	public void setMedicalPlusChooseList(List<OptionVO> medicalPlusChooseList) {
		this.medicalPlusChooseList = ListUtil.serializableList(medicalPlusChooseList);
	}

	public String getMedicalPlusChooseListIsDisabled() {
		return medicalPlusChooseListIsDisabled;
	}

	public void setMedicalPlusChooseListIsDisabled(
			String medicalPlusChooseListIsDisabled) {
		this.medicalPlusChooseListIsDisabled = medicalPlusChooseListIsDisabled;
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

	public String getMedicalPlusRbFlagIsDisabled() {
		return medicalPlusRbFlagIsDisabled;
	}

	public void setMedicalPlusRbFlagIsDisabled(String medicalPlusRbFlagIsDisabled) {
		this.medicalPlusRbFlagIsDisabled = medicalPlusRbFlagIsDisabled;
	}

	public String getMedicalPlusPkFlagIsDisabled() {
		return medicalPlusPkFlagIsDisabled;
	}

	public void setMedicalPlusPkFlagIsDisabled(String medicalPlusPkFlagIsDisabled) {
		this.medicalPlusPkFlagIsDisabled = medicalPlusPkFlagIsDisabled;
	}
	

	public String getEkaSehatExtraDisplay() {
			return ekaSehatExtraDisplay;
	}

	public void setEkaSehatExtraDisplay(String ekaSehatExtraDisplay) {
			this.ekaSehatExtraDisplay = ekaSehatExtraDisplay;
	}
		
	public String getEkaSehatExtraFlag() {
		return ekaSehatExtraFlag;
	}

	public void setEkaSehatExtraFlag(String ekaSehatExtraFlag) {
		this.ekaSehatExtraFlag = ekaSehatExtraFlag;
	}
	
	public String getEkaSehatExtraFlagIsDisabled() {
		return ekaSehatExtraFlagIsDisabled;
	}

	public void setEkaSehatExtraFlagIsDisabled(String ekaSehatExtraFlagIsDisabled) {
		this.ekaSehatExtraFlagIsDisabled = ekaSehatExtraFlagIsDisabled;
	}
//	public Integer getEkaSehatProviderCd() {
//		return ekaSehatProviderCd;
//	}
//
//	public void setEkaSehatProviderCd(Integer ekaSehatProviderCd) {
//		this.ekaSehatProviderCd = ekaSehatProviderCd;
//	}
//
//	public String getEkaSehatProviderIsDisabled() {
//		return ekaSehatProviderIsDisabled;
//	}
//
//	public void setEkaSehatProviderIsDisabled(String ekaSehatProviderIsDisabled) {
//		this.ekaSehatProviderIsDisabled = ekaSehatProviderIsDisabled;
//	}
//
//	public ArrayList<OptionVO> getEkaSehatProviderList() {
//		return ekaSehatProviderList;
//	}
//
//	public void setEkaSehatProviderList(List<OptionVO> ekaSehatProviderList) {
//		this.ekaSehatProviderList = ekaSehatProviderList;
//	}
	public ArrayList<OptionVO> getEkaSehatExtraList() {
		return ekaSehatExtraList;
	}

	public void setEkaSehatExtraList(List<OptionVO> list) {
		this.ekaSehatExtraList = ListUtil.serializableList(list);
	}

	public Integer getEkaSehatExtraCd() {
		return ekaSehatExtraCd;
	}

	public void setEkaSehatExtraCd(Integer ekaSehatExtraCd) {
		this.ekaSehatExtraCd = ekaSehatExtraCd;
	}

	public String getButtonParticipantEkaSehatExtraIsDisabled() {
		return buttonParticipantEkaSehatExtraIsDisabled;
	}

	public void setButtonParticipantEkaSehatExtraIsDisabled(
			String buttonParticipantEkaSehatExtraIsDisabled) {
		this.buttonParticipantEkaSehatExtraIsDisabled = buttonParticipantEkaSehatExtraIsDisabled;
	}

	public String getEkaSehatExtraListIsDisabled() {
		return ekaSehatExtraListIsDisabled;
	}

	public void setEkaSehatExtraListIsDisabled(String ekaSehatExtraListIsDisabled) {
		this.ekaSehatExtraListIsDisabled = ekaSehatExtraListIsDisabled;
	}
	/**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/		

	public BigDecimal getCiRiderAmount() {
		return ciRiderAmount;
	}

	public void setCiRiderAmount(BigDecimal ciRiderAmount) {
		this.ciRiderAmount = ciRiderAmount;
	}

	public String getCiRiderAmountIsDisabled() {
		return ciRiderAmountIsDisabled;
	}

	public void setCiRiderAmountIsDisabled(String ciRiderAmountIsDisabled) {
		this.ciRiderAmountIsDisabled = ciRiderAmountIsDisabled;
	}

	public BigDecimal getEsci99RiderAmount() {
		return esci99RiderAmount;
	}
	public String getEsci99RiderAmountIsDisabled() {
		return esci99RiderAmountIsDisabled;
	}

	public void setEsci99RiderAmountIsDisabled(String esci99RiderAmountIsDisabled) {
		this.esci99RiderAmountIsDisabled = esci99RiderAmountIsDisabled;
	}

	public void setEsci99RiderAmount(BigDecimal esci99RiderAmount) {
		this.esci99RiderAmount = esci99RiderAmount;
	}	
	/* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
	public String getTerm5575RiderDisplay() {
		return term5575RiderDisplay;
	}

	public void setTerm5575RiderDisplay(String term5575RiderDisplay) {
		this.term5575RiderDisplay = term5575RiderDisplay;
	}

	public String getTerm5575RiderFlag() {
		return term5575RiderFlag;
	}

	public void setTerm5575RiderFlag(String term5575RiderFlag) {
		this.term5575RiderFlag = term5575RiderFlag;
	}

	public String getTerm5575RiderFlagIsDisabled() {
		return term5575RiderFlagIsDisabled;
	}

	public void setTerm5575RiderFlagIsDisabled(String term5575RiderFlagIsDisabled) {
		this.term5575RiderFlagIsDisabled = term5575RiderFlagIsDisabled;
	}

	public BigDecimal getTerm5575RiderAmount() {
		return term5575RiderAmount;
	}

	public void setTerm5575RiderAmount(BigDecimal term5575RiderAmount) {
		this.term5575RiderAmount = term5575RiderAmount;
	}

	public String getTerm5575RiderAmountIsDisabled() {
		return term5575RiderAmountIsDisabled;
	}

	public void setTerm5575RiderAmountIsDisabled(
			String term5575RiderAmountIsDisabled) {
		this.term5575RiderAmountIsDisabled = term5575RiderAmountIsDisabled;
	}

	public Integer getTerm5575RiderChooseCd() {
		return term5575RiderChooseCd;
	}

	public void setTerm5575RiderChooseCd(Integer term5575RiderChooseCd) {
		this.term5575RiderChooseCd = term5575RiderChooseCd;
	}

	public ArrayList<OptionVO> getTerm5575RiderChooseList() {
		return term5575RiderChooseList;
	}

	public void setTerm5575RiderChooseList(
			List<OptionVO> list) {
		this.term5575RiderChooseList = ListUtil.serializableList(list);
	}

	public String getTerm5575RiderChooseListIsDisabled() {
		return term5575RiderChooseListIsDisabled;
	}

	public void setTerm5575RiderChooseListIsDisabled(
			String term5575RiderChooseListIsDisabled) {
		this.term5575RiderChooseListIsDisabled = term5575RiderChooseListIsDisabled;
	}
}
