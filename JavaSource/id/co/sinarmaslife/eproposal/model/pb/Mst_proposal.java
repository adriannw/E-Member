package id.co.sinarmaslife.eproposal.model.pb;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: S_medis
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : fadly
 * Version              : 1.0
 * Creation Date    	: Mar 24, 2011 5:02:59 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Mst_proposal implements Serializable
{
	/**********************************************************************
	 * Program History
	 *
	 * Project Name      	: E-Proposal
	 * Function Id         	: id.co.sinarmaslife.eproposal.model.pb
	 * Program Name   		: 
	 * Description         	:
	 * Environment      	: Java  1.5.0_06
	 * Author               : Fadly Mathendra
	 * Version              : 1.0
	 * Creation Date    	: June 18, 2013 5:27:40 PM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = -8983330384245897053L;
	
	public String id;
	public String msag_id;
    public String lku_id;
    public BigDecimal lsbs_id;
    public BigDecimal lsdbs_number;
    public BigDecimal mspr_tsi;
    public BigDecimal mspr_premium;
    public BigDecimal mspr_rate;
    public BigDecimal mspr_lama_bayar;
    public BigDecimal lscb_id;
    public Date tgl_lahir;
    public Integer usia;
    public Date create_date;
    public Integer flag_packet;
    public Integer flag_test;
    
    public Mst_proposal(){
    	
    }
    
    public Mst_proposal(String lku_id, BigDecimal lsbs_id, BigDecimal lsdbs_number, BigDecimal mspr_tsi, BigDecimal mspr_premium,
    		BigDecimal mspr_rate, BigDecimal mspr_lama_bayar, BigDecimal lscb_id, Date tgl_lahir, Integer usia ){
    	this.lku_id = lku_id;
    	this.lsbs_id = lsbs_id;
    	this.lsdbs_number = lsdbs_number;
    	this.mspr_tsi = mspr_tsi;
    	this.mspr_premium = mspr_premium;
    	this.mspr_rate = mspr_rate;
    	this.mspr_lama_bayar = mspr_lama_bayar;
    	this.lscb_id = lscb_id;
    	this.tgl_lahir = tgl_lahir;
    	this.usia = usia;
    }
    
    
	public Date getTgl_lahir() {
		return tgl_lahir;
	}

	public void setTgl_lahir(Date tgl_lahir) {
		this.tgl_lahir = tgl_lahir;
	}

	public Integer getUsia() {
		return usia;
	}

	public void setUsia(Integer usia) {
		this.usia = usia;
	}
	public BigDecimal getLsbs_id() {
		return lsbs_id;
	}
	public void setLsbs_id(BigDecimal lsbsId) {
		lsbs_id = lsbsId;
	}
	public BigDecimal getLsdbs_number() {
		return lsdbs_number;
	}
	public void setLsdbs_number(BigDecimal lsdbsNumber) {
		lsdbs_number = lsdbsNumber;
	}
	public BigDecimal getMspr_tsi() {
		return mspr_tsi;
	}
	public void setMspr_tsi(BigDecimal msprTsi) {
		mspr_tsi = msprTsi;
	}
	public BigDecimal getMspr_premium() {
		return mspr_premium;
	}
	public void setMspr_premium(BigDecimal msprPremium) {
		mspr_premium = msprPremium;
	}
	public BigDecimal getMspr_rate() {
		return mspr_rate;
	}
	public void setMspr_rate(BigDecimal msprRate) {
		mspr_rate = msprRate;
	}
	public BigDecimal getMspr_lama_bayar() {
		return mspr_lama_bayar;
	}
	public void setMspr_lama_bayar(BigDecimal msprLamaBayar) {
		mspr_lama_bayar = msprLamaBayar;
	}
	public BigDecimal getLscb_id() {
		return lscb_id;
	}
	public void setLscb_id(BigDecimal lscbId) {
		lscb_id = lscbId;
	}
	public String getLku_id() {
		return lku_id;
	}
	public void setLku_id(String lkuId) {
		lku_id = lkuId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsag_id() {
		return msag_id;
	}

	public void setMsag_id(String msag_id) {
		this.msag_id = msag_id;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Integer getFlag_packet() {
		return flag_packet;
	}

	public void setFlag_packet(Integer flag_packet) {
		this.flag_packet = flag_packet;
	}

	public Integer getFlag_test() {
		return flag_test;
	}

	public void setFlag_test(Integer flag_test) {
		this.flag_test = flag_test;
	}
    
}
