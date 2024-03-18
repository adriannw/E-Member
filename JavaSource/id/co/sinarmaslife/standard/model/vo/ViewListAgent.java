package id.co.sinarmaslife.standard.model.vo;

import java.io.Serializable;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Medical
 * Function Id         	: 
 * Program Name   		: OptionVO
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 14, 2007 1:38:45 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ViewListAgent implements Serializable
{
    protected final transient Log logger = LogFactory.getLog( getClass() );

    private String msagId;
    private String idAccount;
    private String mclFirst;
	private String lcaNama;
	private String lcaId;
	private String lwkNama;
	private String lsrgNama;
    private String groupId;
    private String groupName;
    private String tglLahir;
    private String flag;

    public ViewListAgent()
    {
        logger.info( "---------- ViewListAgent constructor is called ..." );
    }

	public ViewListAgent(String msagId, String idAccount, String tglLahir, String mclFirst, String lcaNama, String lcaId, String lwkNama, String lsrgNama, String groupId, String groupName, String flag) {
		super();
		this.msagId = msagId;
		this.idAccount = idAccount;
		this.mclFirst = mclFirst;
		this.lcaNama = lcaNama;
		this.tglLahir = tglLahir;
		this.lcaId = lcaId;
		this.lwkNama = lwkNama;
		this.lsrgNama = lsrgNama;
		this.groupId = groupId;
		this.groupName = groupName;
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(String idAccount) {
		this.idAccount = idAccount;
	}

	public String getLcaNama() {
		return lcaNama;
	}

	public void setLcaNama(String lcaNama) {
		this.lcaNama = lcaNama;
	}

	public String getLsrgNama() {
		return lsrgNama;
	}

	public void setLsrgNama(String lsrgNama) {
		this.lsrgNama = lsrgNama;
	}

	public String getLwkNama() {
		return lwkNama;
	}

	public void setLwkNama(String lwkNama) {
		this.lwkNama = lwkNama;
	}

	public String getMclFirst() {
		return mclFirst;
	}

	public void setMclFirst(String mclFirst) {
		this.mclFirst = mclFirst;
	}

	public String getMsagId() {
		return msagId;
	}

	public void setMsagId(String msagId) {
		this.msagId = msagId;
	}

	public String getLcaId() {
		return lcaId;
	}

	public void setLcaId(String lcaId) {
		this.lcaId = lcaId;
	}

	public String getTglLahir() {
		return tglLahir;
	}

	public void setTglLahir(String tglLahir) {
		this.tglLahir = tglLahir;
	}
    
    

    
}