package id.co.sinarmaslife.eproposal.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040110TableVO
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 27, 2008 11:38:40 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

public class Cepr01040133TableVO implements Serializable
{
    /**********************************************************************
	 * Program History
	 *
	 * Project Name      	: E-Proposal
	 * Function Id         	: id.co.sinarmaslife.eproposal.model.vo
	 * Program Name   		: 
	 * Description         	:
	 * Environment      	: Java  1.5.0_06
	 * Author               : Fadly Mathendra
	 * Version              : 1.0
	 * Creation Date    	: Aug 30, 2012 10:19:52 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = -9081028608390580813L;
	private String benefit;
    private String tahapanBonus;
    private String yearNo;
    private String age;
    private String accumulatedPremium;
    private String cashValue;
    private String benefitTahapan;
    private String investasi;
    private String benefitAkmTahapan;
    private String danaHariTua;
    private String nilaiTunai2;

	public Cepr01040133TableVO( String yearNo, String age, String accumulatedPremium, String cashValue,
			String benefit, String tahapanBonus, String benefitTahapan, String investasi, 
			String benefitAkmTahapan, String danaHariTua, String nilaiTunai2 )
    {
        this.benefit = benefit;
        this.tahapanBonus = tahapanBonus;
        this.yearNo = yearNo;
        this.age = age;
        this.accumulatedPremium = accumulatedPremium;
        this.cashValue = cashValue;
        this.benefitTahapan = benefitTahapan;
        this.investasi = investasi;
        this.benefitAkmTahapan = benefitAkmTahapan;
        this.danaHariTua = danaHariTua;
        this.nilaiTunai2 = nilaiTunai2;
    }

    public String getDanaHariTua() {
		return danaHariTua;
	}

	public void setDanaHariTua(String danaHariTua) {
		this.danaHariTua = danaHariTua;
	}

	public String getBenefitAkmTahapan() {
		return benefitAkmTahapan;
	}

	public void setBenefitAkmTahapan(String benefitAkmTahapan) {
		this.benefitAkmTahapan = benefitAkmTahapan;
	}

	public String getInvestasi() {
		return investasi;
	}

	public void setInvestasi(String investasi) {
		this.investasi = investasi;
	}
	
    public String getAccumulatedPremium() {
		return accumulatedPremium;
	}

	public void setAccumulatedPremium(String accumulatedPremium) {
		this.accumulatedPremium = accumulatedPremium;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCashValue() {
		return cashValue;
	}

	public void setCashValue(String cashValue) {
		this.cashValue = cashValue;
	}

	public String getYearNo() {
		return yearNo;
	}

	public void setYearNo(String yearNo) {
		this.yearNo = yearNo;
	}

	public String getTahapanBonus() {
		return tahapanBonus;
	}

	public void setTahapanBonus(String tahapanBonus) {
		this.tahapanBonus = tahapanBonus;
	}

    public String getBenefit()
    {
        return benefit;
    }

    public void setBenefit( String benefit )
    {
        this.benefit = benefit;
    }

	public String getBenefitTahapan() {
		return benefitTahapan;
	}

	public void setBenefitTahapan(String benefitTahapan) {
		this.benefitTahapan = benefitTahapan;
	}

	public String getNilaiTunai2() {
		return nilaiTunai2;
	}

	public void setNilaiTunai2(String nilaiTunai2) {
		this.nilaiTunai2 = nilaiTunai2;
	}
	
}
