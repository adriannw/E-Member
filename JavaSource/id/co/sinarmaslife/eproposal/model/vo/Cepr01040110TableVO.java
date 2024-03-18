package id.co.sinarmaslife.eproposal.model.vo;

import java.io.Serializable;

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

public class Cepr01040110TableVO extends StdTableDetailVO implements Serializable
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
	 * Creation Date    	: Aug 30, 2012 10:19:43 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = 4640980853021366572L;
	private String tahapanPasti;
    private String benefit;

    public Cepr01040110TableVO( String yearNo, String age, String accumulatedPremium, String cashValue, String oldAgeFund, String tahapanPasti, String benefit )
    {
        super( yearNo, age, accumulatedPremium, cashValue, oldAgeFund );
        this.tahapanPasti = tahapanPasti;
        this.benefit = benefit;
    }

    public String getTahapanPasti()
    {
        return tahapanPasti;
    }

    public void setTahapanPasti( String tahapanPasti )
    {
        this.tahapanPasti = tahapanPasti;
    }

    public String getBenefit()
    {
        return benefit;
    }

    public void setBenefit( String benefit )
    {
        this.benefit = benefit;
    }
}
