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

import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.standard.util.ListUtil;

public class Cepr01050000Form implements Serializable
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
	 * Creation Date    	: Oct 19, 2012 9:53:31 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = 470493997323509428L;

	protected final transient Log logger = LogFactory.getLog( getClass() );

    private BigDecimal basePremium;
    private BigDecimal topUpPremium;
    private BigDecimal totalSumInsured;
    
    private BigDecimal polisRendah1;
    private BigDecimal polisRendah5;
    private BigDecimal polisRendah10;
    private BigDecimal polisRendah15;
    
    private BigDecimal polisSedang1;
    private BigDecimal polisSedang5;
    private BigDecimal polisSedang10;
    private BigDecimal polisSedang15;
    
    private BigDecimal polisTinggi1;
    private BigDecimal polisTinggi5;
    private BigDecimal polisTinggi10;
    private BigDecimal polisTinggi15;
    
    private BigDecimal bonus1;
    private BigDecimal bonus5;
    private BigDecimal bonus10;
    private BigDecimal bonus15;
    
    private String fixIncome;
    private String dynamic;
    private String aggresive;
    
    private double premium;

    private ArrayList<TopupDrawVO> topupDrawVOList;
    
    public double getPremium() {
		return premium;
	}

	public ArrayList<TopupDrawVO> getTopupDrawVOList() {
		return topupDrawVOList;
	}

	public void setTopupDrawVOList(List<TopupDrawVO> topupDrawVOList) {
		this.topupDrawVOList = ListUtil.serializableList(topupDrawVOList);
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public BigDecimal getTotalSumInsured() {
		return totalSumInsured;
	}

	public void setTotalSumInsured(BigDecimal totalSumInsured) {
		this.totalSumInsured = totalSumInsured;
	}

	public BigDecimal getBasePremium() {
		return basePremium;
	}

	public void setBasePremium(BigDecimal basePremium) {
		this.basePremium = basePremium;
	}

	public BigDecimal getTopUpPremium() {
		return topUpPremium;
	}

	public void setTopUpPremium(BigDecimal topUpPremium) {
		this.topUpPremium = topUpPremium;
	}


	public BigDecimal getPolisRendah1() {
		return polisRendah1;
	}

	public void setPolisRendah1(BigDecimal polisRendah1) {
		this.polisRendah1 = polisRendah1;
	}

	public BigDecimal getPolisRendah5() {
		return polisRendah5;
	}

	public void setPolisRendah5(BigDecimal polisRendah5) {
		this.polisRendah5 = polisRendah5;
	}

	public BigDecimal getPolisRendah10() {
		return polisRendah10;
	}

	public void setPolisRendah10(BigDecimal polisRendah10) {
		this.polisRendah10 = polisRendah10;
	}

	public BigDecimal getPolisRendah15() {
		return polisRendah15;
	}

	public void setPolisRendah15(BigDecimal polisRendah15) {
		this.polisRendah15 = polisRendah15;
	}

	public BigDecimal getPolisSedang1() {
		return polisSedang1;
	}

	public void setPolisSedang1(BigDecimal polisSedang1) {
		this.polisSedang1 = polisSedang1;
	}

	public BigDecimal getPolisSedang5() {
		return polisSedang5;
	}

	public void setPolisSedang5(BigDecimal polisSedang5) {
		this.polisSedang5 = polisSedang5;
	}

	public BigDecimal getPolisSedang10() {
		return polisSedang10;
	}

	public void setPolisSedang10(BigDecimal polisSedang10) {
		this.polisSedang10 = polisSedang10;
	}

	public BigDecimal getPolisSedang15() {
		return polisSedang15;
	}

	public void setPolisSedang15(BigDecimal polisSedang15) {
		this.polisSedang15 = polisSedang15;
	}

	public BigDecimal getPolisTinggi1() {
		return polisTinggi1;
	}

	public void setPolisTinggi1(BigDecimal polisTinggi1) {
		this.polisTinggi1 = polisTinggi1;
	}

	public BigDecimal getPolisTinggi5() {
		return polisTinggi5;
	}

	public void setPolisTinggi5(BigDecimal polisTinggi5) {
		this.polisTinggi5 = polisTinggi5;
	}

	public BigDecimal getPolisTinggi10() {
		return polisTinggi10;
	}

	public void setPolisTinggi10(BigDecimal polisTinggi10) {
		this.polisTinggi10 = polisTinggi10;
	}

	public BigDecimal getPolisTinggi15() {
		return polisTinggi15;
	}

	public void setPolisTinggi15(BigDecimal polisTinggi15) {
		this.polisTinggi15 = polisTinggi15;
	}


	public String getFixIncome() {
		return fixIncome;
	}

	public void setFixIncome(String fixIncome) {
		this.fixIncome = fixIncome;
	}

	public String getDynamic() {
		return dynamic;
	}

	public void setDynamic(String dynamic) {
		this.dynamic = dynamic;
	}

	public String getAggresive() {
		return aggresive;
	}

	public void setAggresive(String aggresive) {
		this.aggresive = aggresive;
	}

	public BigDecimal getBonus1() {
		return bonus1;
	}

	public void setBonus1(BigDecimal bonus1) {
		this.bonus1 = bonus1;
	}

	public BigDecimal getBonus5() {
		return bonus5;
	}

	public void setBonus5(BigDecimal bonus5) {
		this.bonus5 = bonus5;
	}

	public BigDecimal getBonus10() {
		return bonus10;
	}

	public void setBonus10(BigDecimal bonus10) {
		this.bonus10 = bonus10;
	}

	public BigDecimal getBonus15() {
		return bonus15;
	}

	public void setBonus15(BigDecimal bonus15) {
		this.bonus15 = bonus15;
	}

	public Cepr01050000Form()
    {
        logger.info( "*-*-*-* Cepr01050000Form constructor is called ..." );
    }

}
