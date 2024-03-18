package id.co.sinarmaslife.eproposal.web;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01010101Form
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 6, 2008 9:53:20 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/


import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.std.util.DateUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class Cepr01010101Form implements Serializable, HttpSessionBindingListener
{

    private static final long serialVersionUID = 7374002096426178571L;

    private String account;
    private String password;
    private String newPassword1;
    private String newPassword2;
    private String loginFormDisplay;
    private String newPasswordFormDisplay;
    private String msagId;
    private String theEvent;
    
    private String name;
    private String lde_id;
    private String dept;
    private String lca_id;
    private String cabang;
    private String lus_email;
    
    private Integer online;
    private String lus_id;
    private Date loginTime;
    
    private String ip;
    
    private String groupId;
    private String groupName;
    
    private List<OptionVO> jenisUserList;
    private String jenisUserCd;

    public String getAccount()
    {
        return account;
    }

    public void setAccount( String account )
    {
        this.account = account;
    }

    
    public String getTheEvent() {
		return theEvent;
	}

	public void setTheEvent(String theEvent) {
		this.theEvent = theEvent;
	}

	public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getNewPassword1()
    {
        return newPassword1;
    }

    public void setNewPassword1( String newPassword1 )
    {
        this.newPassword1 = newPassword1;
    }

    public String getNewPassword2()
    {
        return newPassword2;
    }

    public void setNewPassword2( String newPassword2 )
    {
        this.newPassword2 = newPassword2;
    }

    public String getLoginFormDisplay()
    {
        return loginFormDisplay;
    }

    public void setLoginFormDisplay( String loginFormDisplay )
    {
        this.loginFormDisplay = loginFormDisplay;
    }

    public String getNewPasswordFormDisplay()
    {
        return newPasswordFormDisplay;
    }

    public void setNewPasswordFormDisplay( String newPasswordFormDisplay )
    {
        this.newPasswordFormDisplay = newPasswordFormDisplay;
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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLus_id() {
		return lus_id;
	}

	public void setLus_id(String lus_id) {
		this.lus_id = lus_id;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public String getCabang() {
		return cabang;
	}

	public void setCabang(String cabang) {
		this.cabang = cabang;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getLca_id() {
		return lca_id;
	}

	public void setLca_id(String lca_id) {
		this.lca_id = lca_id;
	}

	public String getLde_id() {
		return lde_id;
	}

	public void setLde_id(String lde_id) {
		this.lde_id = lde_id;
	}

	public String getLus_email() {
		return lus_email;
	}

	public void setLus_email(String lus_email) {
		this.lus_email = lus_email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/** FUNGSI2 TAMBAHAN **/
	public void valueBound(HttpSessionBindingEvent e) {
		HttpSession session = e.getSession();
		ServletContext context = session.getServletContext();
		Map users = (HashMap) context.getAttribute("users");
		if(users==null) users = new HashMap();
		
		if(!users.containsKey(msagId)) {
			Map<String, Object> tmp = new HashMap<String, Object>();
			this.loginTime = new Date();//DateUtil.selectSysdate();//eproposalManager.selectNowDate();
			tmp.put("user", this);
			tmp.put("session", session);
			users.put(msagId, tmp);
			context.setAttribute("users", users);
		}
	}
	
	public void valueUnbound(HttpSessionBindingEvent e) {
		HttpSession session = e.getSession();
		ServletContext context = session.getServletContext();
		Map users = (HashMap) context.getAttribute("users");
		
		if(users!=null)
			if(users.containsKey(msagId))
				users.remove(msagId); 
		context.setAttribute("users", users);
	}

	public List<OptionVO> getJenisUserList() {
		return jenisUserList;
	}

	public void setJenisUserList(List<OptionVO> jenisUserList) {
		this.jenisUserList = jenisUserList;
	}

	public String getJenisUserCd() {
		return jenisUserCd;
	}

	public void setJenisUserCd(String jenisUserCd) {
		this.jenisUserCd = jenisUserCd;
	}

}
