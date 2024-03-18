package id.co.sinarmaslife.eproposal.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01030401Form
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 13, 2007 3:17:29 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.ListUtil;

public class Cepr01030901Form implements Serializable
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
	 * Creation Date    	: Dec 14, 2012 4:22:45 PM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = -3893203153655358630L;

	protected final transient Log logger = LogFactory.getLog( getClass() );

    private String customerName;
    private String incomeListDisplay;
    private String percentPremiumCompanyListDisplay;
    private String percentPremiumEmployeeListDisplay;
    private String percentIncomeIncreasePerYearListDisplay;
    private String percentInterestInvestPerYearListDisplay;
    
    private Integer customerCount;
    private Integer customerAge;
    private Integer normalAgePension;
    private Integer income;
    private Integer amountFundTransfer;
    
    private BigDecimal pastWorkingPeriod;  
    private BigDecimal percentPremiumFirstMonth;
    private BigDecimal percentPremiumCompany;
    private BigDecimal percentPremiumEmployee;
    private BigDecimal percentIncomeIncreasePerYear;
    private BigDecimal percentInterestInvestPerYear;
    private BigDecimal incomeCd;
    private BigDecimal percentPremiumCompanyCd;
    private BigDecimal percentPremiumEmployeeCd;
    private BigDecimal percentIncomeIncreasePerYearCd;
    private BigDecimal percentInterestInvestPerYearCd;
        
    private ArrayList<OptionVO> normalAgePensionList;
    private ArrayList<OptionVO> customerAgeList;    
    private ArrayList<OptionVO> incomeList;
    private ArrayList<OptionVO> percentPremiumCompanyList;
    private ArrayList<OptionVO> percentPremiumEmployeeList;
    private ArrayList<OptionVO> percentIncomeIncreasePerYearList;
    private ArrayList<OptionVO> percentInterestInvestPerYearList;
            
    private String downloadFlag;

    public Cepr01030901Form()
    {
        logger.info( "*-*-*-* Cepr01030401Form constructor is called ..." );
       
    }

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getIncomeListDisplay() {
		return incomeListDisplay;
	}

	public void setIncomeListDisplay(String incomeListDisplay) {
		this.incomeListDisplay = incomeListDisplay;
	}

	public String getPercentPremiumCompanyListDisplay() {
		return percentPremiumCompanyListDisplay;
	}

	public void setPercentPremiumCompanyListDisplay(
			String percentPremiumCompanyListDisplay) {
		this.percentPremiumCompanyListDisplay = percentPremiumCompanyListDisplay;
	}

	public String getPercentPremiumEmployeeListDisplay() {
		return percentPremiumEmployeeListDisplay;
	}

	public void setPercentPremiumEmployeeListDisplay(
			String percentPremiumEmployeeListDisplay) {
		this.percentPremiumEmployeeListDisplay = percentPremiumEmployeeListDisplay;
	}

	public String getPercentIncomeIncreasePerYearListDisplay() {
		return percentIncomeIncreasePerYearListDisplay;
	}

	public void setPercentIncomeIncreasePerYearListDisplay(
			String percentIncomeIncreasePerYearListDisplay) {
		this.percentIncomeIncreasePerYearListDisplay = percentIncomeIncreasePerYearListDisplay;
	}

	public String getPercentInterestInvestPerYearListDisplay() {
		return percentInterestInvestPerYearListDisplay;
	}

	public void setPercentInterestInvestPerYearListDisplay(
			String percentInterestInvestPerYearListDisplay) {
		this.percentInterestInvestPerYearListDisplay = percentInterestInvestPerYearListDisplay;
	}

	public Integer getCustomerCount() {
		return customerCount;
	}

	public void setCustomerCount(Integer customerCount) {
		this.customerCount = customerCount;
	}

	public Integer getCustomerAge() {
		return customerAge;
	}

	public void setCustomerAge(Integer customerAge) {
		this.customerAge = customerAge;
	}

	public Integer getNormalAgePension() {
		return normalAgePension;
	}

	public void setNormalAgePension(Integer normalAgePension) {
		this.normalAgePension = normalAgePension;
	}

	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}

	public Integer getAmountFundTransfer() {
		return amountFundTransfer;
	}

	public void setAmountFundTransfer(Integer amountFundTransfer) {
		this.amountFundTransfer = amountFundTransfer;
	}

	public BigDecimal getPastWorkingPeriod() {
		return pastWorkingPeriod;
	}

	public void setPastWorkingPeriod(BigDecimal pastWorkingPeriod) {
		this.pastWorkingPeriod = pastWorkingPeriod;
	}

	public BigDecimal getPercentPremiumFirstMonth() {
		return percentPremiumFirstMonth;
	}

	public void setPercentPremiumFirstMonth(BigDecimal percentPremiumFirstMonth) {
		this.percentPremiumFirstMonth = percentPremiumFirstMonth;
	}

	public BigDecimal getPercentPremiumCompany() {
		return percentPremiumCompany;
	}

	public void setPercentPremiumCompany(BigDecimal percentPremiumCompany) {
		this.percentPremiumCompany = percentPremiumCompany;
	}

	public BigDecimal getPercentPremiumEmployee() {
		return percentPremiumEmployee;
	}

	public void setPercentPremiumEmployee(BigDecimal percentPremiumEmployee) {
		this.percentPremiumEmployee = percentPremiumEmployee;
	}

	public BigDecimal getPercentIncomeIncreasePerYear() {
		return percentIncomeIncreasePerYear;
	}

	public void setPercentIncomeIncreasePerYear(
			BigDecimal percentIncomeIncreasePerYear) {
		this.percentIncomeIncreasePerYear = percentIncomeIncreasePerYear;
	}

	public BigDecimal getPercentInterestInvestPerYear() {
		return percentInterestInvestPerYear;
	}

	public void setPercentInterestInvestPerYear(
			BigDecimal percentInterestInvestPerYear) {
		this.percentInterestInvestPerYear = percentInterestInvestPerYear;
	}

	public BigDecimal getIncomeCd() {
		return incomeCd;
	}

	public void setIncomeCd(BigDecimal incomeCd) {
		this.incomeCd = incomeCd;
	}

	public BigDecimal getPercentPremiumCompanyCd() {
		return percentPremiumCompanyCd;
	}

	public void setPercentPremiumCompanyCd(BigDecimal percentPremiumCompanyCd) {
		this.percentPremiumCompanyCd = percentPremiumCompanyCd;
	}

	public BigDecimal getPercentPremiumEmployeeCd() {
		return percentPremiumEmployeeCd;
	}

	public void setPercentPremiumEmployeeCd(BigDecimal percentPremiumEmployeeCd) {
		this.percentPremiumEmployeeCd = percentPremiumEmployeeCd;
	}

	public BigDecimal getPercentIncomeIncreasePerYearCd() {
		return percentIncomeIncreasePerYearCd;
	}

	public void setPercentIncomeIncreasePerYearCd(
			BigDecimal percentIncomeIncreasePerYearCd) {
		this.percentIncomeIncreasePerYearCd = percentIncomeIncreasePerYearCd;
	}

	public BigDecimal getPercentInterestInvestPerYearCd() {
		return percentInterestInvestPerYearCd;
	}

	public void setPercentInterestInvestPerYearCd(
			BigDecimal percentInterestInvestPerYearCd) {
		this.percentInterestInvestPerYearCd = percentInterestInvestPerYearCd;
	}

	public ArrayList<OptionVO> getNormalAgePensionList() {
		return normalAgePensionList;
	}

	public void setNormalAgePensionList(List<OptionVO> normalAgePensionList) {
		this.normalAgePensionList = ListUtil.serializableList(normalAgePensionList);
	}

	public ArrayList<OptionVO> getCustomerAgeList() {
		return customerAgeList;
	}

	public void setCustomerAgeList(List<OptionVO> customerAgeList) {
		this.customerAgeList = ListUtil.serializableList(customerAgeList);
	}

	public ArrayList<OptionVO> getIncomeList() {
		return incomeList;
	}

	public void setIncomeList(List<OptionVO> incomeList) {
		this.incomeList = ListUtil.serializableList(incomeList);
	}

	public ArrayList<OptionVO> getPercentPremiumCompanyList() {
		return percentPremiumCompanyList;
	}

	public void setPercentPremiumCompanyList(
			List<OptionVO> percentPremiumCompanyList) {
		this.percentPremiumCompanyList = ListUtil.serializableList(percentPremiumCompanyList);
	}

	public ArrayList<OptionVO> getPercentPremiumEmployeeList() {
		return percentPremiumEmployeeList;
	}

	public void setPercentPremiumEmployeeList(
			List<OptionVO> percentPremiumEmployeeList) {
		this.percentPremiumEmployeeList = ListUtil.serializableList(percentPremiumEmployeeList);
	}

	public ArrayList<OptionVO> getPercentIncomeIncreasePerYearList() {
		return percentIncomeIncreasePerYearList;
	}

	public void setPercentIncomeIncreasePerYearList(
			List<OptionVO> percentIncomeIncreasePerYearList) {
		this.percentIncomeIncreasePerYearList = ListUtil.serializableList(percentIncomeIncreasePerYearList);
	}

	public ArrayList<OptionVO> getPercentInterestInvestPerYearList() {
		return percentInterestInvestPerYearList;
	}

	public void setPercentInterestInvestPerYearList(
			List<OptionVO> percentInterestInvestPerYearList) {
		this.percentInterestInvestPerYearList = ListUtil.serializableList(percentInterestInvestPerYearList);
	}

	public String getDownloadFlag() {
		return downloadFlag;
	}

	public void setDownloadFlag(String downloadFlag) {
		this.downloadFlag = downloadFlag;
	}

    

	
}
