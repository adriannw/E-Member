package id.co.sinarmaslife.eproposal.model.vo;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: StdTableDetailVO
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 27, 2007 2:39:24 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StdTableDetailVO implements Serializable
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
	 * Creation Date    	: Aug 30, 2012 10:20:46 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = -381848765469043558L;

	protected final transient Log logger = LogFactory.getLog( getClass() );

    private String yearNo;
    private String age;
    private String accumulatedPremium;
    private String cashValue;
    private String oldAgeFund;

    public StdTableDetailVO()
    {
    }

    public StdTableDetailVO( String yearNo, String age, String accumulatedPremium, String cashValue, String oldAgeFund )
    {
        this.yearNo = yearNo;
        this.age = age;
        this.accumulatedPremium = accumulatedPremium;
        this.cashValue = cashValue;
        this.oldAgeFund = oldAgeFund;
    }

    public String getYearNo()
    {
        return yearNo;
    }

    public void setYearNo( String yearNo )
    {
        this.yearNo = yearNo;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge( String age )
    {
        this.age = age;
    }

    public String getAccumulatedPremium()
    {
        return accumulatedPremium;
    }

    public void setAccumulatedPremium( String accumulatedPremium )
    {
        this.accumulatedPremium = accumulatedPremium;
    }

    public String getCashValue()
    {
        return cashValue;
    }

    public void setCashValue( String cashValue )
    {
        this.cashValue = cashValue;
    }

    public String getOldAgeFund()
    {
        return oldAgeFund;
    }

    public void setOldAgeFund( String oldAgeFund )
    {
        this.oldAgeFund = oldAgeFund;
    }
}
