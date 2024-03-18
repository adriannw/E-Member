package id.co.sinarmaslife.eproposal.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Medical
 * Function Id         	: 
 * Program Name   		: Cmed01040101Form
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : Andy
 * Version              : 1.0
 * Creation Date    	: May 22, 2007 11:53:58 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.model.vo.ViewListProvider;
import id.co.sinarmaslife.standard.util.ListUtil;

public class Cepr01030501Form implements Serializable
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
	 * Creation Date    	: Nov 28, 2012 10:39:20 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = -4810611333613197846L;

	protected final transient Log logger = LogFactory.getLog( getClass() );
	
	private String sortRow;
	
	private String rsCd;
	private String rsNama;
	private String rsAlamat;
	private String kotaCd;
	
	private String firstPage;
	private String lastPage;
	private String previousPage;
	private String nextPage;
	private String noRow;
	
	private ArrayList< OptionVO > listRecords;
	private ArrayList< OptionVO > kotaList;
	private ArrayList< ViewListProvider > allList;
	
    private String policyHolderName;
    private Integer policyHolderAge;
    private Date policyHolderBirthDay;
    private String policyHolderSexCd;
    private String insuredName;
    private Integer insuredAge;
    private Date insuredBirthDay;
    private String insuredSexCd;
    private String proposalUser;
    private String proposalUserCd;
    private Date proposalDate;
    private ArrayList< OptionVO > assurancePlanList;
    private String assurancePlanCd1;
    private String assurancePlanCd2;
    private String assurancePlanCd3;
    private String assurancePlanCd4;
    private ArrayList< OptionVO > genderList;
  
    public Cepr01030501Form()
    {
        logger.info( "*-*-*-* Cepr01030501Form constructor is called ..." );
    }
	

	public String getPolicyHolderName()
    {
        return policyHolderName;
    }

    public void setPolicyHolderName( String policyHolderName )
    {
        this.policyHolderName = policyHolderName;
    }

    public Integer getPolicyHolderAge()
    {
        return policyHolderAge;
    }

    public void setPolicyHolderAge( Integer policyHolderAge )
    {
        this.policyHolderAge = policyHolderAge;
    }

    public Date getPolicyHolderBirthDay()
    {
        return policyHolderBirthDay;
    }

    public void setPolicyHolderBirthDay( Date policyHolderBirthDay )
    {
        this.policyHolderBirthDay = policyHolderBirthDay;
    }

    public String getPolicyHolderSexCd()
    {
        return policyHolderSexCd;
    }

    public void setPolicyHolderSexCd( String policyHolderSexCd )
    {
        this.policyHolderSexCd = policyHolderSexCd;
    }

    public String getInsuredName()
    {
        return insuredName;
    }

    public void setInsuredName( String insuredName )
    {
        this.insuredName = insuredName;
    }

    public Integer getInsuredAge()
    {
        return insuredAge;
    }

    public void setInsuredAge( Integer insuredAge )
    {
        this.insuredAge = insuredAge;
    }

    public Date getInsuredBirthDay()
    {
        return insuredBirthDay;
    }

    public void setInsuredBirthDay( Date insuredBirthDay )
    {
        this.insuredBirthDay = insuredBirthDay;
    }

    public String getInsuredSexCd()
    {
        return insuredSexCd;
    }

    public void setInsuredSexCd( String insuredSexCd )
    {
        this.insuredSexCd = insuredSexCd;
    }

    public String getProposalUser()
    {
        return proposalUser;
    }

    public void setProposalUser( String proposalUser )
    {
        this.proposalUser = proposalUser;
    }

    public String getProposalUserCd()
    {
        return proposalUserCd;
    }

    public void setProposalUserCd( String proposalUserCd )
    {
        this.proposalUserCd = proposalUserCd;
    }

    public Date getProposalDate()
    {
        return proposalDate;
    }

    public void setProposalDate( Date proposalDate )
    {
        this.proposalDate = proposalDate;
    }

    public ArrayList<OptionVO> getAssurancePlanList()
    {
        return assurancePlanList;
    }

    public void setAssurancePlanList( List<OptionVO> assurancePlanList )
    {
        this.assurancePlanList = ListUtil.serializableList(assurancePlanList);
    }

    public String getAssurancePlanCd1()
    {
        return assurancePlanCd1;
    }

    public void setAssurancePlanCd1( String assurancePlanCd1 )
    {
        this.assurancePlanCd1 = assurancePlanCd1;
    }

    public String getAssurancePlanCd2()
    {
        return assurancePlanCd2;
    }

    public void setAssurancePlanCd2( String assurancePlanCd2 )
    {
        this.assurancePlanCd2 = assurancePlanCd2;
    }

    public String getAssurancePlanCd3()
    {
        return assurancePlanCd3;
    }

    public void setAssurancePlanCd3( String assurancePlanCd3 )
    {
        this.assurancePlanCd3 = assurancePlanCd3;
    }

    public String getAssurancePlanCd4()
    {
        return assurancePlanCd4;
    }

    public void setAssurancePlanCd4( String assurancePlanCd4 )
    {
        this.assurancePlanCd4 = assurancePlanCd4;
    }

    public ArrayList<OptionVO> getGenderList()
    {
        return genderList;
    }

    public void setGenderList( List<OptionVO> genderList )
    {
        this.genderList = ListUtil.serializableList(genderList);
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

	public String getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(String previousPage) {
		this.previousPage = previousPage;
	}

	public String getNoRow() {
		return noRow;
	}

	public void setNoRow(String noRow) {
		this.noRow = noRow;
	}

	public ArrayList<ViewListProvider> getAllList() {
		return allList;
	}

	public void setAllList(List<ViewListProvider> allList) {
		this.allList = ListUtil.serializableList(allList);
	}

	public String getRsCd() {
		return rsCd;
	}

	public void setRsCd(String rsCd) {
		this.rsCd = rsCd;
	}

	public String getRsNama() {
		return rsNama;
	}

	public void setRsNama(String rsNama) {
		this.rsNama = rsNama;
	}

	public ArrayList<OptionVO> getListRecords() {
		return listRecords;
	}

	public void setListRecords(List<OptionVO> listRecords) {
		this.listRecords = ListUtil.serializableList(listRecords);
	}

	public String getSortRow() {
		return sortRow;
	}

	public void setSortRow(String sortRow) {
		this.sortRow = sortRow;
	}


	public String getRsAlamat() {
		return rsAlamat;
	}


	public void setRsAlamat(String rsAlamat) {
		this.rsAlamat = rsAlamat;
	}


	public String getKotaCd() {
		return kotaCd;
	}


	public void setKotaCd(String kotaCd) {
		this.kotaCd = kotaCd;
	}


	public ArrayList<OptionVO> getKotaList() {
		return kotaList;
	}


	public void setKotaList(List<OptionVO> kotaList) {
		this.kotaList = ListUtil.serializableList(kotaList);
	}

}
