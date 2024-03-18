package id.co.sinarmaslife.eproposal.model.vo;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: TopupDrawVO
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 28, 2007 10:31:02 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.io.Serializable;
import java.math.BigDecimal;

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;

public class TopupDrawVO implements Serializable
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
	 * Creation Date    	: Aug 30, 2012 10:20:53 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = -7385660126865462226L;
	Integer yearNo;
    String yearFlag;
    BigDecimal topupAmount;
    BigDecimal drawAmount;

    public TopupDrawVO( Integer yearNo, String yearFlag, BigDecimal topupAmount, BigDecimal drawAmount )
    {
        this.yearNo = yearNo;
        this.yearFlag = yearFlag;
        this.topupAmount = topupAmount;
        this.drawAmount = drawAmount;
    }

    public TopupDrawVO( Integer yearNo )
    {
        this.yearNo = yearNo;
        this.yearFlag = CommonConst.CHECKED_FALSE;
        this.topupAmount = new BigDecimal( "0" );
        this.drawAmount = new BigDecimal( "0" );
    }

    public Integer getYearNo()
    {
        return yearNo;
    }

    public void setYearNo( Integer yearNo )
    {
        this.yearNo = yearNo;
    }

    public String getYearFlag()
    {
        return yearFlag;
    }

    public void setYearFlag( String yearFlag )
    {
        this.yearFlag = yearFlag;
    }

    public BigDecimal getTopupAmount()
    {
        return topupAmount;
    }

    public void setTopupAmount( BigDecimal topupAmount )
    {
        this.topupAmount = topupAmount;
    }

    public BigDecimal getDrawAmount()
    {
        return drawAmount;
    }

    public void setDrawAmount( BigDecimal drawAmount )
    {
        this.drawAmount = drawAmount;
    }

}
