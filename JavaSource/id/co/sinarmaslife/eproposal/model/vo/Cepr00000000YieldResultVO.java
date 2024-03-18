package id.co.sinarmaslife.eproposal.model.vo;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr00000000YieldResultVO
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Oct 8, 2007 10:13:39 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Cepr00000000YieldResultVO implements Serializable
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
	 * Creation Date    	: Aug 30, 2012 10:19:35 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = -5123888324442867017L;
	private ArrayList< Map<String, Object> > yieldList;
    private String totalInvestmentAllocation;
    private String totalAssumptionLow;
    private String totalAssumptionMid;
    private String totalAssumptionHi;

    public ArrayList<Map<String, Object>> getYieldList()
    {
        return yieldList;
    }

    public void setYieldList( ArrayList<Map<String, Object>> yieldList )
    {
        this.yieldList = yieldList;
    }

    public String getTotalInvestmentAllocation()
    {
        return totalInvestmentAllocation;
    }

    public void setTotalInvestmentAllocation( String totalInvestmentAllocation )
    {
        this.totalInvestmentAllocation = totalInvestmentAllocation;
    }

    public String getTotalAssumptionLow()
    {
        return totalAssumptionLow;
    }

    public void setTotalAssumptionLow( String totalAssumptionLow )
    {
        this.totalAssumptionLow = totalAssumptionLow;
    }

    public String getTotalAssumptionMid()
    {
        return totalAssumptionMid;
    }

    public void setTotalAssumptionMid( String totalAssumptionMid )
    {
        this.totalAssumptionMid = totalAssumptionMid;
    }

    public String getTotalAssumptionHi()
    {
        return totalAssumptionHi;
    }

    public void setTotalAssumptionHi( String totalAssumptionHi )
    {
        this.totalAssumptionHi = totalAssumptionHi;
    }
}
