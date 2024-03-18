package id.co.sinarmaslife.eproposal.model.vo;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: ParticipantVO
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Sep 3, 2007 11:32:36 AM
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

public class ParticipantVO implements Serializable
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
	 * Creation Date    	: Aug 30, 2012 10:20:39 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = -5308650094722760194L;
	private String name;
    private Date birthDay;
    private Integer age;
    private String status;
    //=== Added by Daru @since 06 Mar 2014
    private Integer lsre_id;
    private List<OptionVO> relation;
    private String sexCd;
    private ArrayList< OptionVO > genderList;
    private String medicalPlusRbFlag;
    //===

    public ParticipantVO()
    {
    }

    public ParticipantVO( String name, Date birthDay, Integer age )
    {
        this.name = name;
        this.birthDay = birthDay;
        this.age = age;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public Date getBirthDay()
    {
        return birthDay;
    }

    public void setBirthDay( Date birthDay )
    {
        this.birthDay = birthDay;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge( Integer age )
    {
        this.age = age;
    }

	public Integer getLsre_id() {
		return lsre_id;
	}

	public void setLsre_id(Integer lsre_id) {
		this.lsre_id = lsre_id;
	}

	public List<OptionVO> getRelation() {
		return relation;
	}

	public void setRelation(List<OptionVO> relation) {
		this.relation = relation;
	}
	
	public ArrayList<OptionVO> getGenderList()
	{
	    return genderList;
	}

	public void setGenderList( List<OptionVO> genderList )
	{
	    this.genderList = ListUtil.serializableList(genderList);
	}

	public String getMedicalPlusRbFlag() {
		return medicalPlusRbFlag;
	}

	public void setMedicalPlusRbFlag(String medicalPlusRbFlag) {
		this.medicalPlusRbFlag = medicalPlusRbFlag;
	}

	public String getSexCd() {
		return sexCd;
	}

	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}
	
}
