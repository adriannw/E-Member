package id.co.sinarmaslife.eproposal.web;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01010101Controller
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 6, 2008 9:47:43 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/
import id.co.sinarmaslife.eproposal.business.Cepr01010101Business;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.standard.StandardConst;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.model.vo.UserGroup;
import id.co.sinarmaslife.standard.util.StringUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import com.ibatis.common.resources.Resources;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cepr01010101Controller extends SimpleFormController
{
	EproposalManager eproposalManager;
	Cepr01010101Business cepr01010101Business;
	

    public void setCepr01010101Business(Cepr01010101Business cepr01010101Business) {
		this.cepr01010101Business = cepr01010101Business;
	}

	public void setEproposalManager(EproposalManager eproposalManager) {
		this.eproposalManager = eproposalManager;
	}

	@Override
	protected  void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) {

        Cepr01010101Form cepr01010101Form = ( Cepr01010101Form ) command;
        
     //   Map agentBCVIP = cepr01010101Business.selectAgentBCVIP(); 
        if( CommonConst.DISPLAY_TRUE.equals( cepr01010101Form.getLoginFormDisplay() ) )
        {
            // remove space first
            if( cepr01010101Form.getAccount() != null )
            {
                cepr01010101Form.setAccount( cepr01010101Form.getAccount().trim() );
            }
        
   //     Map userhardcoded = cepr01010101Business.selectUserHardcodedReferential( cepr01010101Form.getAccount(),cepr01010101Form.getPassword().trim() ); 
	/*
	 * if(userhardcoded != null){ cepr01010101Form.setMsagId(
	 * userhardcoded.get("MSAG_ID").toString()); cepr01010101Form.setGroupId(
	 * userhardcoded.get("GROUP_ID").toString());
	 * eproposalManager.validateAndInsertUserProduct(cepr01010101Form.getMsagId(),
	 * cepr01010101Form.getGroupId());//kedepannya tidak diperlukan table ini } //}
	 */        else{
            // start user check
            if( StringUtil.isEmpty( cepr01010101Form.getAccount() ) )
            {
                errors.rejectValue( Cepr01010101FormConst.ACCOUNT, "error.login.emptyAccount",
                        null, "account name to fill." );
            }
            else if( StringUtil.isEmpty( cepr01010101Form.getPassword() ) )
            {
                errors.rejectValue( Cepr01010101FormConst.PASSWORD, "error.login.emptyPassword",
                        null, "account password to fill" );
            } 
           /* else if( cepr01010101Form.getAccount().toLowerCase().equals( "superuser" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "superuser" )
                    )
            {
                cepr01010101Form.setMsagId( Hardcode.SUPER_USER_MSAG_ID );
            }
            //else if( cepr01010101Form.getAccount().toLowerCase().equals( "aktuaria" )
            //         && cepr01010101Form.getPassword().toLowerCase().equals( "aktuaria" )
            //       )
            //{
            //    cepr01010101Form.setMsagId( Hardcode.ACTUARIST_MSAG_ID );
            //}
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "jaime" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "jaime" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.JAMIE_MSAG_ID );
           }          
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "bsm" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "testing" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.BANK_SINARMAS );
           }
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "sekuritas" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "sekuritas" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.SEKURITAS );
               cepr01010101Form.setGroupId( Hardcode.GROUP_SEKURITAS );
           }
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "tim_hafri" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "testing" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.TIM_HAFRI );
           }
           // else if( cepr01010101Form.getAccount().toLowerCase().equals( "gusti" )
           //         && cepr01010101Form.getPassword().toLowerCase().equals( "testing" )
           //        )
           //{
           //    cepr01010101Form.setMsagId( Hardcode.ACTUARIST_GUSTI );
           //}
           // else if( cepr01010101Form.getAccount().toLowerCase().equals( "vito" )
           //         && cepr01010101Form.getPassword().toLowerCase().equals( "testing" )
           //        )
           //{
           //    cepr01010101Form.setMsagId( Hardcode.ACTUARIST_VITO );
           //}
           // else if( cepr01010101Form.getAccount().toLowerCase().equals( "mayda" )
           //         && cepr01010101Form.getPassword().toLowerCase().equals( "testing" )
           //        )
           //{
           //    cepr01010101Form.setMsagId( Hardcode.ACTUARIST_MAYDA );
           //}
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "joko" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "testing" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.ACTUARIST_JOKO );
           }
           // else if( cepr01010101Form.getAccount().toLowerCase().equals( "dwi_kusuma" )
           //        && cepr01010101Form.getPassword().toLowerCase().equals( "testing" )
           //        )
           //{
           //    cepr01010101Form.setMsagId( Hardcode.ACTUARIST_DWI_KUSUMA );
           //}
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "fitria" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "testing" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.ACTUARIST_FITRIA );
           }
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "mall" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "mall" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.MALLASSURANCE );
           }
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "mnc" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "mnc" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.MNC );
               cepr01010101Form.setGroupId( Hardcode.GROUP_MNC );
           }
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "bancass" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "bancass" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.BANCASS );
               cepr01010101Form.setGroupId( Hardcode.GROUP_BANCASS );
           }
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "mnctest" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "mnctest" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.MNC_TEST );
               cepr01010101Form.setGroupId( Hardcode.GROUP_MNC_TEST );
           }
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "fcd" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "fcd" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.FCD );
               cepr01010101Form.setGroupId( Hardcode.GROUP_FCD );
           }
           else if( cepr01010101Form.getAccount().toLowerCase().equals( "clickforlife" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "clickforlife" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.CLICKFORLIFE );
           }
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "dmtmuser" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "dmtmuser" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.DMTM_MSAG_ID );
               cepr01010101Form.setGroupId( Hardcode.GROUP_DMTM );
           }
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "light168" )
                     && cepr01010101Form.getPassword().toLowerCase().equals( "light168" )
                    )
            {
                cepr01010101Form.setMsagId( Hardcode.TRAINER_MSAG_ID );
            }
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "worksite" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "worksite" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.WORKSITE );
           }
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "userasm" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "userasm" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.USER_ASM );
           }
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "trainer_co" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "ajsmsiglife" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.TRAINER_CO );
           }
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "alivenet1" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "123456" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.ALIVENET1);
               cepr01010101Form.setGroupId( Hardcode.GROUP_MNC );
           }
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "alivenet2" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "123456" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.ALIVENET2);
               cepr01010101Form.setGroupId( Hardcode.GROUP_MNC );
           }
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "agency" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "agency" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.DEMO_AGENCY );
           }
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "eko" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "testing" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.BISDEV_SYARIAH_EKO );
               cepr01010101Form.setGroupId( Hardcode.GROUP_BISDEV_SYARIAH );
           } 
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "hafri" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "testing" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.BISDEV_SYARIAH_HAFRI );
               cepr01010101Form.setGroupId( Hardcode.GROUP_BISDEV_SYARIAH );
           }
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "jazai" )
                    && cepr01010101Form.getPassword().toLowerCase().equals( "testing" )
                   )
           {
               cepr01010101Form.setMsagId( Hardcode.BISDEV_SYARIAH_JAZAI );
               cepr01010101Form.setGroupId( Hardcode.GROUP_BISDEV_SYARIAH );
           }
            else if( cepr01010101Form.getAccount().toLowerCase().equals( "sadewo" )
                   && cepr01010101Form.getPassword().toLowerCase().equals( "testing" )
                  )
          {
              cepr01010101Form.setMsagId( Hardcode.BISDEV_SYARIAH_SADEWO );
              cepr01010101Form.setGroupId( Hardcode.GROUP_BISDEV_SYARIAH );
          }*/
   
            }
        }
    
	}
	
	@Override
    protected Object formBackingObject( HttpServletRequest request ) throws Exception
    {
        logger.info( "*-*-*-* Cepr01010101Controller.formBackingObject" );

        Cepr01010101Form cepr01010101Form = new Cepr01010101Form();
        cepr01010101Form.setLoginFormDisplay( CommonConst.DISPLAY_TRUE );
        cepr01010101Form.setNewPasswordFormDisplay( CommonConst.DISPLAY_FALSE );
        
        Class.forName("org.postgresql.Driver");
        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "admin";
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = connection.createStatement() ;
        String query = "select nama from public.user;" ;
        ResultSet rs = stmt.executeQuery(query) ;
        while (rs.next()) {
        	  System.out.println(rs.getString(1));
        	}
        stmt.close();
        rs.close();
        connection.close();
        
        
        
     //   Map agentBCVIP = cepr01010101Business.selectAgentBCVIP(); 
        
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        
        String a = request.getParameter("a");
        String b = request.getParameter("b");
        String c = request.getParameter("c");
        String d = request.getParameter("d");
        String e = request.getParameter("e");
        String f = request.getParameter("f");
        String g = request.getParameter("g");
        String h = request.getParameter("h");
        String i = request.getParameter("i");
        String j = request.getParameter("j");
        String clickForLife = request.getParameter("clickforlife");
        
        if( a!=null || b!=null || c!=null || d!=null || e!=null ||
        		 f!=null || g!=null || h!=null || i!=null || j!=null ){
        	request.getSession().setAttribute("a", a);
        	request.getSession().setAttribute("b", b);
        	request.getSession().setAttribute("c", c);
        	request.getSession().setAttribute("d", d);
        	request.getSession().setAttribute("e", e);
        	request.getSession().setAttribute("f", f);
        	request.getSession().setAttribute("g", g);
        	request.getSession().setAttribute("h", h);
        	request.getSession().setAttribute("i", i);
        	request.getSession().setAttribute("j", j);
        }
        
        request.setAttribute("user", user);
        request.setAttribute("pass", pass);
        if( clickForLife != null && clickForLife.equals("true")){
        	request.setAttribute("clickForLife", "clickForLife");
        }
        
		/*
		 * Properties props = Resources.getResourceAsProperties("jdbc.properties");
		 * 
		 * if ("eka8i".equalsIgnoreCase(props.get("jdbc.name").toString())) {
		 * request.getSession().setAttribute("deebee", "Eka8i"); } else if
		 * ("ekatest".equalsIgnoreCase(props.get("jdbc.name").toString())) {
		 * //user.setPass("AA"); request.getSession().setAttribute("deebee", "EkaTest");
		 * } else if ("ajsdb".equalsIgnoreCase(props.get("jdbc.name").toString())) {
		 * //user.setPass("AA"); request.getSession().setAttribute("deebee", "AJSDB");
		 * 
		 * } else request.getSession().setAttribute("deebee", "Unknown!");
		 * 
		 * if(!request.getRemoteAddr().startsWith("128.21.")) {
		 * if(!ServletRequestUtils.getStringParameter(request, "eka_web",
		 * "").equals("PASS")) { //request.setAttribute("notAllowed", "true"); } }
		 * cepr01010101Form.setJenisUserCd("99"); List<OptionVO> jenisUserList = new
		 * ArrayList<OptionVO>(); // jenisUserList.add( new OptionVO("1", "AGENCY")); //
		 * jenisUserList.add( new OptionVO("2", "REGIONAL")); // jenisUserList.add( new
		 * OptionVO("3", "MNC")); // jenisUserList.add( new OptionVO("4", "BSM")); //
		 * jenisUserList.add( new OptionVO("5", "BANCASS I")); // jenisUserList.add( new
		 * OptionVO("6", "BANCASS II")); // jenisUserList.add( new OptionVO("7",
		 * "ASM")); jenisUserList.add( new OptionVO("99", "Others")); jenisUserList.add(
		 * new OptionVO("8", "Sinarmas Sekuritas"));
		 * cepr01010101Form.setJenisUserList(jenisUserList);
		 */
        return cepr01010101Form;
    }

    protected ModelAndView onSubmit( HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) throws java.lang.Exception
    {
        logger.info( "*-*-*-* Cepr01010101Controller.onSubmit" );
        Cepr01010101Form cepr01010101Form = ( Cepr01010101Form ) command;
        
        CredentialsVO currentCredentialsVO = new CredentialsVO();
        currentCredentialsVO.setUserName( cepr01010101Form.getAccount() );
        currentCredentialsVO.setPassword( cepr01010101Form.getPassword() );
        currentCredentialsVO.setMsagId( cepr01010101Form.getMsagId() );
        currentCredentialsVO.setGroupId( cepr01010101Form.getGroupId() );

		/*
		 * currentCredentialsVO.setIsVIP(false); Map agentBCVIP =
		 * cepr01010101Business.selectAgentBCVIP(); if(agentBCVIP != null){
		 * currentCredentialsVO.setIsVIP(true);
		 * 
		 * }
		 * 
		 * 
		 */
        
        request.getSession().setAttribute( StandardConst.CREDENTIALS_SES, currentCredentialsVO );
//        cepr01010101Form.setGroupId("6");
//        cepr01010101Form.setMsagId("999996");
//        cepr01010101Form.setGroupName("DMTM");
        request.setAttribute( "user", cepr01010101Form.getMsagId() );
        request.setAttribute( "groupId", cepr01010101Form.getGroupId() );
        request.setAttribute( "groupName", cepr01010101Form.getGroupName() );
        
        String url = null;
        String groupName = cepr01010101Form.getGroupName();
        if(groupName == null){groupName = "";}
        String groupId = cepr01010101Form.getGroupId();
        
        if (groupId.equals("1"))
        {
        url = "wepr01020000.htm";
        }
        else if (groupName.equals("admin"))
        {
        url = "wepr01020000.htm";
        }
        else 
        {
        url = "wepr01030000.htm";
        }
        if("999993".equals( cepr01010101Form.getMsagId() ) ){// clickforlife
        	url = "wepr01030000.htm";
        }
        
        
        //console
        //User user = new User();
        //user = (User) command;
		HttpSession session = request.getSession(true);
		ServletContext context = session.getServletContext();
		Map users = (HashMap) context.getAttribute("users");
		if(users == null) users = new HashMap();
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(err, "name", "user.name");
		//ValidationUtils.rejectIfEmptyOrWhitespace(err, "pass", "user.pass");

		if(!request.getRemoteAddr().startsWith("128.21.")) {
			if(!ServletRequestUtils.getStringParameter(request, "eka_web", "").equals("PASS")) {
				//request.setAttribute("notAllowed", "true");
				//err.reject("", "Maaf, tetapi anda tidak mempunyai hak akses");
			}
		}
		if(!bindException.hasErrors()) {
			//String pass = user.getPass();
			//user = eproposalManager.selectLoginAuthentication(user);

			//if (user == null)
				//err.reject("user.error", "Nama atau password salah");
			//else {
				try {
					//Cara 1 : User login di 2 tempat, dilarang
					/**
					if(users.containsKey(user.getLus_id())) {
		    			Map tmp = (HashMap) users.get(user.getLus_id());
		    			User loggedUser = (User) tmp.get("user");
						err.reject("user.alreadySignedIn", new String[] {loggedUser.getIp()}, "Maaf, tetapi USER ID anda sedang digunakan di komputer dengan IP [{0}]. Silahkan hubungi EDP");
					}else {
						user.setIp(request.getRemoteAddr());
						user.setAksesCabang(elionsManager.selectAksesCabang(Integer.parseInt(user.getLus_id())));
						session.setAttribute("currentUser", user);
					}
					**/
					
					//Cara 2 : User login di 2 tempat, tempat pertamanya di tendang
					/***/
		    		//String userName= user.getName();
		    		String userName= cepr01010101Form.getMsagId();
		    		if(userName != null && !"999993".equals(userName))// clickforlife
		    		{
			    		if(users.containsKey(userName)) {
			    			Map tmp = (HashMap) users.get(userName);
			    			HttpSession currentSession = (HttpSession) tmp.get("session");
			    			currentSession.invalidate();
			    			users.remove(userName); 
			    		}
		    		}
		    		cepr01010101Form.setIp(request.getRemoteAddr());
	
					//if (props.getProperty("access.viewer.region").indexOf(user.getLde_id()) > -1) {
						//user.setAksesCabang(elionsManager.selectAksesCabang_lar(Integer
								//.parseInt(user.getLus_id())));
					//}else{
						//user.setAksesCabang(elionsManager.selectAksesCabang(Integer
								//.parseInt(user.getLus_id())));
					//}
	
					//user.setScreenWidth(ServletRequestUtils.getIntParameter(request, "screenWidth", 800));
					//user.setScreenHeight(ServletRequestUtils.getIntParameter(request, "screenHeight", 600));
					
			        //double angka = (double) user.getScreenWidth() / (double) user.getScreenHeight();
			        //if(angka > 1.4) {
			        	//user.setWideScreen(true);
			        //}else {
			        	//user.setWideScreen(false);
			        //}
					
					//user.setPass(pass);
					session.setAttribute("currentUser", cepr01010101Form);
					/***/
				}catch(Exception e) {
					logger.error( e );
					bindException.reject("", "Silahkan LOGIN kembali karena session anda sudah berakhir.");
				}
				
			}
		//}
		//
        
        return new ModelAndView( new RedirectView( url ) );
    }
    
    private void doSetProductAccessRightAsDefault( String msagId, String groupIdParam, Integer jenis )
    {
        logger.info( "*-*-*-* Cepr01010101Validator.doSetProductAccessRightAsDefault" );
        
        String groupName = "";
        String groupId = "";
        if( groupIdParam != null && groupIdParam.equals( Hardcode.GROUP_MNC ) )
        {
        	groupName = "mnc";
        	groupId = Hardcode.GROUP_MNC;
        }else if( groupIdParam != null && groupIdParam.equals( Hardcode.GROUP_SEKURITAS ) )
        {
        	groupName = "sekuritas";
        	groupId = Hardcode.GROUP_SEKURITAS;
        }
        else
        {
	        String lcaId = cepr01010101Business.selectAgentType( msagId );
	        if( lcaId.equals( "42" ) )
	        {
	            groupName = "worksite";
	        }
	        else if( lcaId.equals( "46" ) )
	        {
	            groupName = "hybrid";
	        }
	        else if( lcaId.equals( "37" ) )
	        {
	            groupName = "agency";
	        }
	        else if( !lcaId.equals( "08" ) && !lcaId.equals( "09" ) && !lcaId.equals( "37" ) && !lcaId.equals( "40" ) && !lcaId.equals( "46" ) )
	        {
	            groupName = "regional";
	        }
	        else if( lcaId.equals( "09" ) )
	        {
	        //	if 
	            groupName = "Bank Sinarmas";
	        }
	        else
	        {
	            groupName = "default";
	        }
	        if( jenis != null ){
	        	if( jenis.intValue() == Hardcode.JENIS_FCD ){
	        		 groupName = "fcd";
	        	}
	        }
	        groupId = cepr01010101Business.selectGroupId(groupName);
        }
        
        
        if( groupId != null )
        {
            cepr01010101Business.insertNewUserProduct( msagId, groupId );
        }
        else
        {
            throw new RuntimeException( "Cepr01010101Validator.insertNewUserProductAccess: database doesn't have "+groupName+" product access group" );
        }
    }

}