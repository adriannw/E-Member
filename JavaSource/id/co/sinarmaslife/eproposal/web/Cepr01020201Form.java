package id.co.sinarmaslife.eproposal.web;

import id.co.sinarmaslife.standard.model.vo.OptionVO;

import java.io.Serializable;
import java.util.List;

public class Cepr01020201Form implements Serializable {
	
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
	 * Creation Date    	: Nov 28, 2012 10:37:35 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = 3513455251002911930L;
	private String groupId;
	private String addGroup;
    private String groupName;
    private List< OptionVO > listRecords;
	private List< OptionVO > allGroup;
	
    private String firstPage;
	private String lastPage;
	private String previousPage;
	private String nextPage;
	private String noRow;
	private String sortRow;

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

	public List<OptionVO> getAllGroup() {
		return allGroup;
	}

	public void setAllGroup(List<OptionVO> allGroup) {
		this.allGroup = allGroup;
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

	public List<OptionVO> getListRecords() {
		return listRecords;
	}

	public void setListRecords(List<OptionVO> listRecords) {
		this.listRecords = listRecords;
	}

	public String getAddGroup() {
		return addGroup;
	}

	public void setAddGroup(String addGroup) {
		this.addGroup = addGroup;
	}
}
