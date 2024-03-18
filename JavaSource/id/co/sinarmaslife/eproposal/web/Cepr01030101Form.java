package id.co.sinarmaslife.eproposal.web;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01030101Form
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 22, 2007 11:53:58 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.ListUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cepr01030101Form implements Serializable
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
	 * Creation Date    	: Nov 28, 2012 10:36:55 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = -7348213744374189153L;
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
    private ArrayList< OptionVO > assurancePlanPackageList;
    private String assurancePlanPackageCd1;
    private ArrayList< OptionVO > genderList;
    private String downloadFlag;
    private String theEvent;
    private String assurancePlanPackageListDisplay;
    private String downloadLinkDisplay;
    private String babyFlag;
    
    private String disajikanListDisplay;
    private String disajikanAdminListDisplay;
    private String namaAdminBC;
    private String kodeAdminBC;
    private ArrayList< OptionVO > namaAdminBCList;
    private String cariNamaAdminBC;
    private String cariKodeAdminBC;
    private String jn_bank;
    
    
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

    public String getDownloadFlag()
    {
        return downloadFlag;
    }

    public void setDownloadFlag( String downloadFlag )
    {
        this.downloadFlag = downloadFlag;
    }

    public String getTheEvent()
    {
        return theEvent;
    }

    public void setTheEvent( String theEvent )
    {
        this.theEvent = theEvent;
    }

	public ArrayList<OptionVO> getAssurancePlanPackageList() {
		return assurancePlanPackageList;
	}

	public void setAssurancePlanPackageList(List<OptionVO> assurancePlanPackageList) {
		this.assurancePlanPackageList = ListUtil.serializableList(assurancePlanPackageList);
	}

	public String getAssurancePlanPackageCd1() {
		return assurancePlanPackageCd1;
	}

	public void setAssurancePlanPackageCd1(String assurancePlanPackageCd1) {
		this.assurancePlanPackageCd1 = assurancePlanPackageCd1;
	}

	public String getAssurancePlanPackageListDisplay() {
		return assurancePlanPackageListDisplay;
	}

	public void setAssurancePlanPackageListDisplay(
			String assurancePlanPackageListDisplay) {
		this.assurancePlanPackageListDisplay = assurancePlanPackageListDisplay;
	}

	public String getDownloadLinkDisplay() {
		return downloadLinkDisplay;
	}

	public void setDownloadLinkDisplay(String downloadLinkDisplay) {
		this.downloadLinkDisplay = downloadLinkDisplay;
	}

	public String getBabyFlag() {
		return babyFlag;
	}

	public void setBabyFlag(String babyFlag) {
		this.babyFlag = babyFlag;
	}

	public String getDisajikanListDisplay() {
		return disajikanListDisplay;
	}

	public void setDisajikanListDisplay(String disajikanListDisplay) {
		this.disajikanListDisplay = disajikanListDisplay;
	}

	public String getNamaAdminBC() {
		return namaAdminBC;
	}

	public void setNamaAdminBC(String namaAdminBC) {
		this.namaAdminBC = namaAdminBC;
	}

	public String getKodeAdminBC() {
		return kodeAdminBC;
	}

	public void setKodeAdminBC(String kodeAdminBC) {
		this.kodeAdminBC = kodeAdminBC;
	}

	public ArrayList<OptionVO> getNamaAdminBCList() {
		return namaAdminBCList;
	}

	public void setNamaAdminBCList(ArrayList<OptionVO> namaAdminBCList) {
		this.namaAdminBCList = namaAdminBCList;
	}

	public String getCariNamaAdminBC() {
		return cariNamaAdminBC;
	}

	public void setCariNamaAdminBC(String cariNamaAdminBC) {
		this.cariNamaAdminBC = cariNamaAdminBC;
	}

	public String getCariKodeAdminBC() {
		return cariKodeAdminBC;
	}

	public void setCariKodeAdminBC(String cariKodeAdminBC) {
		this.cariKodeAdminBC = cariKodeAdminBC;
	}

	public String getDisajikanAdminListDisplay() {
		return disajikanAdminListDisplay;
	}

	public void setDisajikanAdminListDisplay(String disajikanAdminListDisplay) {
		this.disajikanAdminListDisplay = disajikanAdminListDisplay;
	}

	public String getJn_bank() {
		return jn_bank;
	}

	public void setJn_bank(String jn_bank) {
		this.jn_bank = jn_bank;
	}
	
}
