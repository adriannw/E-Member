package id.co.sinarmaslife.eproposal.web;

import id.co.sinarmaslife.standard.model.vo.OptionVO;

import java.io.Serializable;
import java.util.List;

public class Cepr01020401Form implements Serializable{
	
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
	 * Creation Date    	: Nov 28, 2012 10:37:58 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = 7556632850282396432L;
	private String msagId;
	private String idAccount;
	private String mclFirst;
	private String lcaNama;
	private String lwkNama;
	private String lsrgNama;
    private String groupId;
    private String groupName;
    private String eMail;
    private String addGroup;
    
    private String firstPage;
	private String lastPage;
	private String previousPage;
	private String nextPage;
	private String noRow;
	private String sortRow;
    
	private List< OptionVO > allGroup;
    private List< OptionVO > listRecords;
    
	public List<OptionVO> getListRecords() {
		return listRecords;
	}
	public void setListRecords(List<OptionVO> listRecords) {
		this.listRecords = listRecords;
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
	public String getMsagId() {
		return msagId;
	}
	public void setMsagId(String msagId) {
		this.msagId = msagId;
	}
	public String getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}
	public String getLastPage() {
		return lastPage;
	}
	public void setLastPage(String lastPage) {
		this.lastPage = lastPage;
	}
	public String getNextPage() {
		return nextPage;
	}
	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}
	public String getNoRow() {
		return noRow;
	}
	public void setNoRow(String noRow) {
		this.noRow = noRow;
	}
	public String getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(String previousPage) {
		this.previousPage = previousPage;
	}
	public String getSortRow() {
		return sortRow;
	}
	public void setSortRow(String sortRow) {
		this.sortRow = sortRow;
	}
	public String getAddGroup() {
		return addGroup;
	}
	public void setAddGroup(String addGroup) {
		this.addGroup = addGroup;
	}
	public List<OptionVO> getAllGroup() {
		return allGroup;
	}
	public void setAllGroup(List<OptionVO> allGroup) {
		this.allGroup = allGroup;
	}
	public String getEMail() {
		return eMail;
	}
	public void setEMail(String mail) {
		eMail = mail;
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
	public String getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(String idAccount) {
		this.idAccount = idAccount;
	}

}
