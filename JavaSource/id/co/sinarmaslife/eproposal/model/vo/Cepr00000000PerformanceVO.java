package id.co.sinarmaslife.eproposal.model.vo;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr00000000PerformanceVO
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Oct 4, 2007 2:20:22 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/


import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

public class Cepr00000000PerformanceVO implements Serializable
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
	 * Creation Date    	: Aug 30, 2012 10:19:22 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = 6738248966049672713L;
	private Date date;
    private BigDecimal fixIncome;
    private BigDecimal dynamicFund;
    private BigDecimal aggressiveFund;
    private BigDecimal secureDollar;
    private BigDecimal dynamicDollar;

    public Date getDate()
    {
        return date;
    }

    public void setDate( Date date )
    {
        this.date = date;
    }

    public BigDecimal getFixIncome()
    {
        return fixIncome;
    }

    public void setFixIncome( BigDecimal fixIncome )
    {
        this.fixIncome = fixIncome;
    }

    public BigDecimal getDynamicFund()
    {
        return dynamicFund;
    }

    public void setDynamicFund( BigDecimal dynamicFund )
    {
        this.dynamicFund = dynamicFund;
    }

    public BigDecimal getAggressiveFund()
    {
        return aggressiveFund;
    }

    public void setAggressiveFund( BigDecimal aggressiveFund )
    {
        this.aggressiveFund = aggressiveFund;
    }

    public BigDecimal getSecureDollar()
    {
        return secureDollar;
    }

    public void setSecureDollar( BigDecimal secureDollar )
    {
        this.secureDollar = secureDollar;
    }

    public BigDecimal getDynamicDollar()
    {
        return dynamicDollar;
    }

    public void setDynamicDollar( BigDecimal dynamicDollar )
    {
        this.dynamicDollar = dynamicDollar;
    }
}
