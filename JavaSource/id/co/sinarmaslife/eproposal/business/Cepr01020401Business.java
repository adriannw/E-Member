package id.co.sinarmaslife.eproposal.business;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-eprical
 * Function Id         	: 
 * Program Name   		: Cepr01040101Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : Andy
 * Version              : 1.0
 * Creation Date    	: Aug 30, 2007 10:31:35 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/


import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01020201Form;
import id.co.sinarmaslife.eproposal.common.lookup.Cepr00000000ComboLookupBusiness;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.vo.User;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01010101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01020000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01020201Form;
import id.co.sinarmaslife.eproposal.web.Cepr01020301Form;
import id.co.sinarmaslife.eproposal.web.Cepr01020401Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01020201Form;
import id.co.sinarmaslife.eproposal.web.Cepr01020201Form;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.model.vo.UserGroupId;
import id.co.sinarmaslife.standard.model.vo.ViewListGroup;
import id.co.sinarmaslife.standard.model.vo.TotalPage;
import id.co.sinarmaslife.standard.model.vo.UserGroup;
import id.co.sinarmaslife.standard.model.vo.ViewListAgent;
import id.co.sinarmaslife.standard.model.vo.ViewListProvider;
//import id.co.sinarmaslife.standard.model.vo.ViewListProviderDetail;

import id.co.sinarmaslife.standard.util.HtmlUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;


public class Cepr01020401Business
{
    protected final Log logger = LogFactory.getLog( getClass() );

    protected EproposalManager eproposalManager;
    protected Cepr00000000EditorUtil editorUtil;

    public Cepr01020401Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil )
    {
        this.eproposalManager = eproposalManager;
        this.editorUtil = editorUtil;
    }

    public void resetForm( Cepr01020201Form cepr01020201Form )
    {
        //cepr01020201Form.setProposalDate( cepr00000000GeneralBusiness.selectNowDate() );
//        cepr01040103Form.setAssurancePlanList( cepr00000000ComboLookupBusiness.selectLstBusinessViewListGroupList( true, null ) );
    }
    
    /* Window Daftar User Branch Admin */
	public Map usersStatus(Object command, HttpServletRequest request){
		Cepr01020401Form cepr01020401Form = ( ( Cepr01020000HoldingForm ) command ).getCepr01020401Form();
		ServletContext context = request.getSession().getServletContext();
		HashMap users = (HashMap) context.getAttribute("users");
		Map cmd = new HashMap();
		
		String lca_id = ServletRequestUtils.getStringParameter(request, "lca_id", "");
		String lde_id = ServletRequestUtils.getStringParameter(request, "lde_id", "");
		int onoff = ServletRequestUtils.getIntParameter(request, "onoff", 1);
		
		cepr01020401Form.setSortRow(request.getParameter("sortRow"));
		if("".equals(cepr01020401Form.getMsagId())){cepr01020401Form.setMsagId(null);}
    	if("".equals(cepr01020401Form.getMclFirst())){cepr01020401Form.setMclFirst(null);}
    	if("".equals(cepr01020401Form.getLcaNama())){cepr01020401Form.setLcaNama(null);}
    	if("".equals(cepr01020401Form.getLwkNama())){cepr01020401Form.setLwkNama(null);}
    	if("".equals(cepr01020401Form.getGroupName())){cepr01020401Form.setGroupName(null);}
    	if("".equals(cepr01020401Form.getSortRow())){cepr01020401Form.setSortRow(null);}
    	
    	if("sortedByMsag".equals(cepr01020401Form.getSortRow())){
    		cepr01020401Form.setSortRow("lus_id");
    	}else if("sortedByAgent".equals(cepr01020401Form.getSortRow())){
    		cepr01020401Form.setSortRow("name");
    	}else if("sortedByCabang".equals(cepr01020401Form.getSortRow())){
    		cepr01020401Form.setSortRow("cabang");
    	}else if("sortedByWakil".equals(cepr01020401Form.getSortRow())){
    		cepr01020401Form.setSortRow("wakil");
    	}else if("sortedByGroup".equals(cepr01020401Form.getSortRow())){
    		cepr01020401Form.setSortRow("groupName");
    	}
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put( "MSAG_ID", cepr01020401Form.getMsagId() );
    	params.put( "MCL_FIRST", cepr01020401Form.getMclFirst() );
    	params.put( "LCA_NAMA", cepr01020401Form.getLcaNama() );
    	params.put( "LWK_NAMA", cepr01020401Form.getLwkNama() );
    	params.put( "GROUP_NAME", cepr01020401Form.getGroupName() );
    	params.put( "sortRow", cepr01020401Form.getSortRow() );
		
		List<User> daftarUser = eproposalManager.selectAllUsers(params);
		
		// setting parameter admin 
		User admin = new User();
		admin.setLus_id("admin");
		admin.setName("admin");
		admin.setCabang("PUSAT");
		admin.setWakil("PUSAT");
		admin.setGroupId("x");
		admin.setGroupName("ADMIN");
		daftarUser.add(admin);
		
		// setting parameter aktuaria
		User aktuaria = new User();
		aktuaria.setLus_id("999998");
		aktuaria.setName("aktuaria");
		aktuaria.setCabang("PUSAT");
		aktuaria.setWakil("PUSAT");
		aktuaria.setGroupId("x");
		aktuaria.setGroupName("AKTUARIA");
		daftarUser.add(aktuaria);
		
		// setting parameter clickforlife
		User clickForLife = new User();
		clickForLife.setLus_id("999993");
		clickForLife.setName("clickforlife");
		clickForLife.setCabang("PUSAT");
		clickForLife.setWakil("PUSAT");
		clickForLife.setGroupId("x");
		clickForLife.setGroupName("CLICKFORLIFE");
		daftarUser.add(clickForLife);
		
		// setting parameter superuser
		User superuser = new User();
		superuser.setLus_id("999999");
		superuser.setName("superuser");
		superuser.setCabang("PUSAT");
		superuser.setWakil("PUSAT");
		superuser.setGroupId("x");
		superuser.setGroupName("SUPERUSER");
		daftarUser.add(superuser);
		
		// setting parameter light168
		User light168 = new User();
		light168.setLus_id("999997");
		light168.setName("light168");
		light168.setCabang("PUSAT");
		light168.setWakil("PUSAT");
		light168.setGroupId("x");
		light168.setGroupName("LIGHT168");
		daftarUser.add(light168);
		
		for(User user : daftarUser) {
			if(users.containsKey(user.getLus_id()) ) {
				user.setOnline(1);
				Map tmp = (HashMap) users.get(user.getLus_id());
				Cepr01010101Form temp = (Cepr01010101Form) tmp.get("user");
				//user.setDivision(temp.getDivision());
				user.setLoginTime(temp.getLoginTime());
				user.setIp(temp.getIp());
				//user.setJumlahSpaj(eproposalManager.selectJumlahSpajAksesHist(user.getLus_id()));
			}else {
				user.setOnline(0);
			}
		}
		
		if(onoff == 1) {
			List<User> daftarUserOnline = new ArrayList<User>();
			for(User user : daftarUser) {
				if(user.getOnline().intValue() == 1){
					daftarUserOnline.add(user);
				}
			}
			cmd.put("daftarUser", daftarUserOnline);
		}else {
			cmd.put("daftarUser", daftarUser);
		}
		
		cmd.put("lca_id", lca_id);
		cmd.put("lde_id", lde_id);
		cmd.put("onoff", onoff);
		
		return cmd;
	}
	
	public Map kickUser(HttpServletRequest request, String event, String lusId){
		ServletContext context = request.getSession().getServletContext();
		Map cmd = new HashMap();
    	HashMap users = (HashMap)context.getAttribute("users");
		
    	// Kick User
		if("onPressButtonKick".equals(event)) {
    		String lus_id = lusId;
    		if(users.containsKey(lus_id)) {
    			Map tmp = (HashMap) users.get(lus_id);
    			Cepr01010101Form user = (Cepr01010101Form) tmp.get("user");
    			List<String> daftar = new ArrayList<String>();
    			daftar.add(lus_id+" => "+user.getIp()+" has been kicked");
    			HttpSession currentSession = (HttpSession) tmp.get("session");
    			currentSession.invalidate();
    			users.remove(lus_id); 
    			cmd.put("daftar", daftar);
    		}
    	// Kick All Offline User
    	}else if("onPressButtonKickAllOffUsers".equals(event)) {
    		List<String> daftar = new ArrayList<String>();
    		for(Object o : users.keySet()) {
    			String lus_id = (String) o;
        		if(users.containsKey(lus_id)) {
        			Map tmp = (HashMap) users.get(lus_id);
        			Cepr01010101Form user = (Cepr01010101Form) tmp.get("user");
        			String result = ping(user.getIp());
        			if(result.contains("dead")) {
            			HttpSession currentSession = (HttpSession) tmp.get("session");
            			daftar.add(lus_id + " => " + result);
            			currentSession.invalidate();
            			users.remove(lus_id); 
        			}
        			//daftar.add(result);
        		}
    		}
    		if(daftar.size() == 0){
    			daftar.add("NONE");
    		}
    		cmd.put("daftar", daftar);
    	}
		
		return cmd;
	}
	
	public String ping(String ip) {
		String result = "";
		try {
			Process p = Runtime.getRuntime().exec("ping -n 1 " + ip);
			int status = p.waitFor();
			result = (ip + " is " + (status == 0 ? "alive" : "dead"));
		} catch (InterruptedException e) {
			logger.error("ERROR", e);
		} catch (IOException e) {
			logger.error("ERROR", e);
		}
		return result;
	}
}
