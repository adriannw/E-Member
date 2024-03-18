package id.co.sinarmaslife.eproposal.model.vo;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: CredentialsVO
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 5, 2008 10:23:29 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.io.Serializable;

/**
 * Model Class untuk tabel LST_USER, digunakan juga untuk data login user credentials di session
 *
 * @author Yusuf Sutarko
 * @since Feb 13, 2007 (11:28:13 AM)
 */

public class CredentialsVO implements Serializable
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
	 * Creation Date    	: Aug 30, 2012 10:20:02 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = 840231980483771730L;
	private String userName;
    private String password;
    private String msagId;
    private String groupId;
    private Integer jenis;  
	private String type;    
    private String distId;
    private String leadId;
    private String lusId;
    private boolean isVIP;
    private String isVIP2;
    
    /**
	 * @return the isVIP2
	 */
	public String getIsVIP2() {
		return isVIP2;
	}

	/**
	 * @param isVIP2 the isVIP2 to set
	 */
	public void setIsVIP2(String isVIP2) {
		this.isVIP2 = isVIP2;
	}

	public CredentialsVO( String userName, String password, String msagId, String groupId, Integer jenis, String type, String distId, String leadId){
    	this.userName = userName;
    	this.password = password;
    	this.msagId = msagId;
    	this.groupId = groupId;
    	this.jenis = jenis;
    	this.type = type;
    	this.distId = distId;
    	this.leadId = leadId;
    }
    
    public CredentialsVO(){
    	
    }
    
    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName( String userName )
    {
        this.userName = userName;
    }

    public String getMsagId()
    {
        return msagId;
    }

    public void setMsagId( String msagId )
    {
        this.msagId = msagId;
    }

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Integer getJenis() {
		return jenis;
	}

	public void setJenis(Integer jenis) {
		this.jenis = jenis;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDistId() {
		return distId;
	}

	public void setDistId(String distId) {
		this.distId = distId;
	}

	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getLusId() {
		return lusId;
	}

	public void setLusId(String lusId) {
		this.lusId = lusId;
	}
	
	public boolean getIsVIP() {
		return isVIP;
	}

	public void setIsVIP(boolean isVIP) {
		this.isVIP = isVIP;
	}


    
}
