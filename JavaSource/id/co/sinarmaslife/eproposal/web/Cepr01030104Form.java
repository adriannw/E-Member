package id.co.sinarmaslife.eproposal.web;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01030104Form
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 24, 2007 10:07:46 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.standard.util.ListUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cepr01030104Form implements Serializable
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
	 * Creation Date    	: Nov 28, 2012 10:36:38 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = -4711784063898567125L;
	BigDecimal topupDefaultAmount;
    BigDecimal drawDefaultAmount;

    ArrayList<TopupDrawVO> topupDrawVOList;

    BigDecimal totalTopupAmount;
    BigDecimal totalDrawAmount;
    String checkAllFlag;

    public BigDecimal getTopupDefaultAmount()
    {
        return topupDefaultAmount;
    }

    public void setTopupDefaultAmount( BigDecimal topupDefaultAmount )
    {
        this.topupDefaultAmount = topupDefaultAmount;
    }

    public BigDecimal getDrawDefaultAmount()
    {
        return drawDefaultAmount;
    }

    public void setDrawDefaultAmount( BigDecimal drawDefaultAmount )
    {
        this.drawDefaultAmount = drawDefaultAmount;
    }

    public ArrayList<TopupDrawVO> getTopupDrawVOList()
    {
        return topupDrawVOList;
    }

    public void setTopupDrawVOList( List<TopupDrawVO> topupDrawVOList )
    {
        this.topupDrawVOList = ListUtil.serializableList(topupDrawVOList);
    }

    public BigDecimal getTotalTopupAmount()
    {
        return totalTopupAmount;
    }

    public void setTotalTopupAmount( BigDecimal totalTopupAmount )
    {
        this.totalTopupAmount = totalTopupAmount;
    }

    public BigDecimal getTotalDrawAmount()
    {
        return totalDrawAmount;
    }

    public void setTotalDrawAmount( BigDecimal totalDrawAmount )
    {
        this.totalDrawAmount = totalDrawAmount;
    }

    public String getCheckAllFlag()
    {
        return checkAllFlag;
    }

    public void setCheckAllFlag( String checkAllFlag )
    {
        this.checkAllFlag = checkAllFlag;
    }
}
