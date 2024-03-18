package id.co.sinarmaslife.eproposal.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.view.RedirectView;

import id.co.sinarmaslife.eproposal.business.Cepr00000000GeneralBusiness;
import id.co.sinarmaslife.eproposal.business.Cepr01020201Business;
import id.co.sinarmaslife.eproposal.business.Cepr01020202Business;
import id.co.sinarmaslife.eproposal.business.Cepr01020301Business;
import id.co.sinarmaslife.eproposal.business.Cepr01020401Business;
import id.co.sinarmaslife.eproposal.common.lookup.Cepr00000000ComboLookupBusiness;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.standard.propertyeditors.CurrencyFormatEditor;
import id.co.sinarmaslife.standard.util.ArrUtil;

public class Cepr01020000Controller extends AbstractWizardFormController implements Serializable
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
	 * Creation Date    	: Aug 30, 2012 9:41:28 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = 8835678747160939699L;
		public static final int CEPR01020101JSP = 0;
	    public static final int CEPR01020201JSP = 1;
	    public static final int CEPR01020202JSP = 2;
	    public static final int CEPR01020301JSP = 3;
	    public static final int CEPR01020401JSP = 4;
	    
	    protected EproposalManager eproposalManager;
	    protected transient CustomDateEditor dateEditor;
	    protected transient CustomNumberEditor integerEditor;
	    protected CurrencyFormatEditor currencyEditor;
	    protected DateFormat dateFormat;
	    protected Cepr00000000EditorUtil editorUtil;

	    protected Cepr00000000ComboLookupBusiness comboLookupBusiness;
	    protected Cepr00000000GeneralBusiness generalBusiness;
	    //protected Cepr01020101Business cepr01020101Business;
	    protected Cepr01020201Business cepr01020201Business;
	    protected Cepr01020202Business cepr01020202Business;
	    protected Cepr01020301Business cepr01020301Business;
	    protected Cepr01020401Business cepr01020401Business;
	    
	    public void setEproposalManager( EproposalManager eproposalManager )
	    {
	        this.eproposalManager = eproposalManager;
	    }

	    public void setDateFormat( DateFormat dateFormat )
	    {
	        this.dateFormat = dateFormat;
	    }

	    public void setDateEditor( CustomDateEditor dateEditor )
	    {
	        this.dateEditor = dateEditor;
	    }

	    public void setIntegerEditor( CustomNumberEditor integerEditor )
	    {
	        this.integerEditor = integerEditor;
	    }

	    public void setCurrencyEditor( CurrencyFormatEditor currencyEditor )
	    {
	        this.currencyEditor = currencyEditor;
	    }

	    public void setEditorUtil( Cepr00000000EditorUtil editorUtil )
	    {
	        this.editorUtil = editorUtil;
	    }

	    protected String pages[];

	    @Override
	    protected Object formBackingObject( HttpServletRequest request ) throws Exception
	    {
	        logger.info( "*-*-*-* Cepr01020000Controller.formBackingObject" );
	        this.pages = getPages();

	        setAllowDirtyBack( false );

	        Cepr01020000HoldingForm cepr01020000HoldingForm = new Cepr01020000HoldingForm();
	        //CredentialsVO credentialsVO = ( CredentialsVO ) request.getSession().getAttribute( StandardConst.CREDENTIALS_SES );
	        Cepr01020101Form cepr01020101Form = new Cepr01020101Form();
	        Cepr01020201Form cepr01020201Form = new Cepr01020201Form();
	        Cepr01020202Form cepr01020202Form = new Cepr01020202Form();
	        Cepr01020301Form cepr01020301Form = new Cepr01020301Form();
	        Cepr01020401Form cepr01020401Form = new Cepr01020401Form();
	        
	        cepr01020101Form.setMsagId((String) request.getAttribute("user")); 
	        cepr01020101Form.setGroupId((String) request.getAttribute("groupId")); 
	        cepr01020101Form.setGroupName((String) request.getAttribute("groupName")); 

	        comboLookupBusiness = Cepr00000000ComboLookupBusiness.getInstance( eproposalManager, editorUtil );
	        generalBusiness = Cepr00000000GeneralBusiness.getInstance( eproposalManager, editorUtil );
	        
	        cepr01020201Business = new Cepr01020201Business( eproposalManager, editorUtil );
	        cepr01020202Business = new Cepr01020202Business( eproposalManager, editorUtil );
	        cepr01020301Business = new Cepr01020301Business( eproposalManager, editorUtil );
	        cepr01020401Business = new Cepr01020401Business( eproposalManager, editorUtil );
	        
	        //cepr01030101Business = new Cepr01030101Business( eproposalManager, editorUtil );
	        //cepr01030102Business = new Cepr01030102Business( eproposalManager, editorUtil );
	        //cepr01030103Business = new Cepr01030103Business( eproposalManager, editorUtil );

	        //cepr01030101Business.resetForm( cepr01030101Form, generalBusiness, comboLookupBusiness, credentialsVO );
	        //cepr01030102Business.resetForm( cepr01030102Form );
	        //cepr01030103Business.resetForm( cepr01030103Form );

	        //cepr01030000HoldingForm.setCredentialsVO( credentialsVO );
	        cepr01020000HoldingForm.setCepr01020101Form( cepr01020101Form );
	        cepr01020000HoldingForm.setCepr01020201Form( cepr01020201Form );
	        cepr01020000HoldingForm.setCepr01020202Form( cepr01020202Form );
	        cepr01020000HoldingForm.setCepr01020301Form( cepr01020301Form );
	        cepr01020000HoldingForm.setCepr01020401Form( cepr01020401Form );

	        return cepr01020000HoldingForm;
	    }

	    @Override
	    protected void initBinder( HttpServletRequest request, ServletRequestDataBinder binder ) throws Exception
	    {
	        logger.info( "*-*-*-* Cepr01020000Controller.initBinder" );
	        binder.registerCustomEditor( Date.class, null, dateEditor );
	        binder.registerCustomEditor( Integer.class, null, integerEditor );
	        binder.registerCustomEditor( BigDecimal.class, null, currencyEditor );
	    }

	    @Override
	    protected void onBind( HttpServletRequest request, Object command )
	    {
	        logger.info( "*-*-*-* Cepr01020000Controller.onBind" );
	        String theEvent = request.getParameter( "theEvent" );

	        int currentPage = getCurrentPage( request );
	        
	    }

	    @Override
	    protected boolean suppressValidation( HttpServletRequest request, Object command, BindException errors )
	    {
	        logger.info( "*-*-*-* Cepr01020000Controller.suppressValidation" );

	        String theEvent = request.getParameter( "theEvent" );
	        logger.info( "*-*-*-* theEvent = " + theEvent );

	        boolean result = false;
	        int currentPage = getCurrentPage( request );
	        int targetPage = getTargetPage( request, currentPage );
	        logger.info( "*-*-*-* currentPage = " + currentPage );
	        logger.info( "*-*-*-* targetPage = " + targetPage );

	        if( targetPage == CEPR01020101JSP )
	        {
	            result = true;
	        }/*
	        else if( targetPage == CEPR01030105JSP )
	        {
	            result = true;
	        }
	        else if( targetPage == CEPR01030501JSP )
	        {
	            result = true;
	        }
	        else if( targetPage == CEPR01030502JSP )
	        {
	            result = true;
	        }
	        else if( currentPage == CEPR01030101JSP && targetPage == CEPR01030301JSP )
	        {
	            result = true;
	        }
	        else if( currentPage == CEPR01030101JSP && targetPage == CEPR01030401JSP )
	        {
	            result = true;
	        }
	        else if(    ( currentPage == CEPR01030102JSP && targetPage == CEPR01030103JSP )
	                 || ( currentPage == CEPR01030102JSP && targetPage == CEPR01030104JSP ) )
	        {
	            result = true;
	        }
*/
	        logger.info( "*-*-*-* result suppressValidation = " + result );
	        return result;
	    }

	    @Override
	    protected void validatePage( Object command, Errors errors, int page )
	    {
	        logger.info( "*-*-*-* Cepr01020000Controller.validatePage" );
	        logger.info( "*-*-*-* page = " + page );

	        if( page == CEPR01020101JSP )
	        {
	            logger.info( "*-*-*-* validateCepr01020101" );
	            //Cepr01030101Validator cepr01030101Validator = new Cepr01030101Validator( eproposalManager, editorUtil, command, errors );
	            //cepr01030101Validator.validateCommon();
	        }
	        else if( page == CEPR01020201JSP )
	        {
	            logger.info( "*-*-*-* validateCepr01020201" );
	            //Cepr01030102Validator cepr01030102Validator = new Cepr01030102Validator( eproposalManager, editorUtil, command, errors );
	            //cepr01030102Validator.validateCommon();
	            //cepr01030102Business.doSpecificForEachProduct( command, Cepr01030102Business.JobDescr.DO_VALIDATION, errors, "" );
	        }
	        else if( page == CEPR01020202JSP )
	        {
	            logger.info( "*-*-*-* validateCepr01020202" );
	            //Cepr01030103Validator cepr01030103Validator = new Cepr01030103Validator( eproposalManager, editorUtil, command, errors );
	            //cepr01030103Validator.validate();
	        }
	        else if( page == CEPR01020301JSP )
	        {
	            logger.info( "*-*-*-* validateCepr01020301" );
	            //Cepr01030104Validator cepr01030104Validator = new Cepr01030104Validator( eproposalManager, editorUtil, command, errors );
	            //cepr01030104Validator.validate();
	        }
	        else if( page == CEPR01020401JSP )
	        {
	            logger.info( "*-*-*-* validateCepr01020401" );
	            //Cepr01030104Validator cepr01030104Validator = new Cepr01030104Validator( eproposalManager, editorUtil, command, errors );
	            //cepr01030104Validator.validate();
	        }
	        
	        
	        logger.info( "*-*-*-* errors.getErrorCount() = " + errors.getErrorCount() );
	    }

	    @Override
	    protected Map referenceData( HttpServletRequest request, Object command, Errors errors, int page ) throws Exception
	    {
	    	
	        logger.info( "*-*-*-* Cepr01020000Controller.referenceData" );
	        logger.info( "*-*-*-* page = " + page );
	        String theEvent = request.getParameter( "theEvent" );
	        
	        Map<String, Object> map = new HashMap<String, Object>();
	        
	        //Cepr01020000HoldingForm cepr01020000HoldingForm = ( Cepr01020000HoldingForm ) command;
	        //Cepr01020101Form cepr01020101Form = cepr01020000HoldingForm.getCepr01020101Form();

	        if( page == CEPR01020101JSP )
	        {/*
	            cepr01030102Form.setAssurancePlanList( cepr01030102Business.selectSpecificLstBusinessOptionVOList( command ) );
	            if( cepr01030102Form.getAssurancePlanList().size() == 1 )
	            {
	                cepr01030102Form.setAssurancePlanCd( cepr01030102Form.getAssurancePlanList().get( 0 ).getValue() );
	            }

	            // reset assurancePlanCd if user choose other plan
	            String assurancePlanCd;
	            boolean haveTheSamePlan = false;
	            for( OptionVO optionVO : cepr01030102Form.getAssurancePlanList() )
	            {
	                assurancePlanCd = optionVO.getValue();
	                if( assurancePlanCd.equals( cepr01030102Form.getPrevAssurancePlanCd() ) )
	                {
	                    haveTheSamePlan = true;
	                    break;
	                }
	            }
	            if( !haveTheSamePlan && cepr01030102Form.getAssurancePlanList().size() > 1 )
	            {
	                logger.info( "*-*-*-* CredentialsVO didn't choose the same assurancePlanCd" );
	                cepr01030102Form.setAssurancePlanCd( "" );
	            }
	            else
	            {
	                logger.info( "*-*-*-* CredentialsVO did choose the same assurancePlanCd" );
	            }

	            cepr01030102Business.doSpecificForEachProduct( command, Cepr01030102Business.JobDescr.DO_INIT_FORM, errors, theEvent );
	        */
	        }
	        /*else*/ if( page == CEPR01020201JSP )
	        {
	        	if( "onPressButtonAddGroup".equals(request.getParameter("theEvent"))){
	        		Map<String, Object> result = new HashMap<String, Object>();
	        		result = cepr01020201Business.selectAddGroupName( command );
	        		String keterangan = (String) result.get("keterangan");
	        		request.setAttribute( "keterangan", keterangan );
	        	}else if( "onPressButtonDeleteGroup".equals(request.getParameter("theEvent"))){
	        		Map<String, Object> result = new HashMap<String, Object>();
	        		List deleteGroup = ArrUtil.toListFromArray(request.getParameterValues("chkArr"));
	        		result = cepr01020201Business.selectDeleteGroupName( deleteGroup );
	        		String keterangan = (String) result.get("keterangan");
	        		request.setAttribute( "keterangan", keterangan );
	        	}
	        	Map<String, Object> result = new HashMap<String, Object>();
        		result = cepr01020201Business.selectGroupList( command, request.getParameter("cPage"), request.getParameter("sortRow") );
        		List allList = ( List ) result.get("allList");
        		request.setAttribute( "allList", allList );
        		request.setAttribute("currPage", result.get("currPage") );
	        }
	        else if( page == CEPR01020202JSP )
	        {	
	        	String groupId = request.getParameter("selectedGroupId");
	        	if( "onPressButtonSaveAdd".equals(request.getParameter("theEvent"))){
	        		Map<String, Object> resultSave = new HashMap<String, Object>();
	        		Map<String, Object> resultAdd = new HashMap<String, Object>();
	        		List changeGroup = ArrUtil.toListFromArray(request.getParameterValues("chkArr"));
	        		logger.info(changeGroup);
	        		resultSave = cepr01020202Business.selectChangeBusiness(changeGroup, groupId);
	        		resultAdd = cepr01020202Business.selectAddBusiness(command, groupId);
	        		String keteranganSave = (String) resultSave.get("keterangan");
	        		String keteranganAdd = (String) resultAdd.get("keterangan");
	        		if(keteranganSave.equals(keteranganAdd)){
	        			request.setAttribute( "keterangan", "Save & Add sukses" );
	        		}else if(!keteranganSave.equals(keteranganAdd)){
	        			request.setAttribute( "keterangan", "Save & Add gagal" );
	        		}
	        		if(keteranganAdd.equals("nama product harus dipilih")){
	        			request.setAttribute( "keterangan", "nama product harus dipilih" );
	        		}
	        		
	        	}else if( "onPressButtonSaveGroup".equals(request.getParameter("theEvent"))){
	        		Map<String, Object> result = new HashMap<String, Object>();
	        		List changeGroup = ArrUtil.toListFromArray(request.getParameterValues("chkArr"));
	        		logger.info(changeGroup);
	        		result = cepr01020202Business.selectChangeBusiness(changeGroup, groupId);
	        		String keterangan = (String) result.get("keterangan");
	        		request.setAttribute( "keterangan", keterangan );
	        	}
	        	Map<String, Object> result = new HashMap<String, Object>();
	        	request.setAttribute( "groupId", groupId );
	            result = cepr01020202Business.selectBusinessList( groupId );
	            request.setAttribute( "allBusiness", cepr01020202Business.selectAllBusinessList() );
	            List allList = ( List ) result.get("allList");
	            request.setAttribute( "allList", allList );
	            String groupNameLabel = (String) result.get("groupName");
	            request.setAttribute( "groupNameLabel", groupNameLabel );
	        }
	        else if( page == CEPR01020301JSP )
	        {	
	        	List group = ArrUtil.toListFromArray(request.getParameterValues("chkArr"));
	        	if( "onPressButtonChange".equals(request.getParameter("theEvent"))){
	        		if(group.size() != 0){
	        			cepr01020301Business.changeAgentList( command, group );
	        			request.setAttribute( "keterangan", "Change user group sukses" );
	        		}
	        	}
	        	Map<String, Object> result = new HashMap<String, Object>();
	        	result = cepr01020301Business.selectAgentList( command, request.getParameter("cPage"), request.getParameter("sortRow"), group );
	        	List allList = ( List ) result.get("allList");
	        	request.setAttribute( "allGroup", cepr01020301Business.selectAllGroupList() );
	        	request.setAttribute( "allList", allList );
	        	request.setAttribute("currPage", result.get("currPage") );
	        	
	        }
	        else if( page == CEPR01020401JSP )
	        {
	        	// untuk console
	        	if("onPressButtonPingIp".equals(theEvent)){
	        		String pingIpResult = cepr01020401Business.ping(request.getParameter("ipAddressUser"));
	        		request.setAttribute("pingIpResult", pingIpResult);
	        	}else if("onPressButtonKick".equals(theEvent)){
	        		Map kickedUsers = cepr01020401Business.kickUser(request, theEvent, request.getParameter("lusIdUser"));
	        		request.setAttribute("pingIpResult", ((List)kickedUsers.get("daftar")).get(0));
	        	}else if("onPressButtonKickAllOffUsers".equals(theEvent)){
	        		Map kickedUsers = cepr01020401Business.kickUser(request, theEvent, request.getParameter("lusIdUser"));
	        		request.setAttribute("pingIpResultList", (List)kickedUsers.get("daftar"));
	        	}
	        	Map daftarUserMap = cepr01020401Business.usersStatus(command, request);
	        	request.setAttribute("daftarUser", daftarUserMap.get("daftarUser"));
	        	//logger.info(daftarUserMap.get("daftarUser"));	        	
	        	logger.debug( daftarUserMap.get("daftarUser") );
	        }
	        

	        // set backup data for previous plan
	       // cepr01030102Form.setPrevAssurancePlanCd( cepr01030102Form.getAssurancePlanCd() );

	        return map;
	    }

	    @Override
	    protected ModelAndView processFinish( HttpServletRequest request, HttpServletResponse response, Object command, BindException errors ) throws Exception
	    {
	        //logger.info( "*-*-*-* Cepr01030000Controller.processFinish" );
	        //throw new RuntimeException( "meredirect view yg seharusnya tidak pernah dipanggil" );
	        return new ModelAndView( new RedirectView( "wepr01020000.htm" ) );
	    }

	    protected void postProcessPage( HttpServletRequest request, Object command, Errors errors, int page ) throws Exception
	    {
	        logger.info( "*-*-*-* Cepr01030000Controller.postProcessPage" );
	        logger.info( "*-*-*-* page = " + page );

	        Cepr01020000HoldingForm cepr01020000HoldingForm = ( Cepr01020000HoldingForm ) command;
	        Cepr01020101Form cepr01020101Form = cepr01020000HoldingForm.getCepr01020101Form();
	        Cepr01020201Form cepr01020201Form = cepr01020000HoldingForm.getCepr01020201Form();
	        Cepr01020202Form cepr01020202Form = cepr01020000HoldingForm.getCepr01020202Form();
	        Cepr01020301Form cepr01020301Form = cepr01020000HoldingForm.getCepr01020301Form();

	        String theEvent = request.getParameter( "theEvent" );
	        logger.info( "*-*-*-* theEvent = " + theEvent );
/*
	        if( errors.getErrorCount() == 0 && errors.getGlobalErrorCount() == 0 )
	        {
	            if( "onPressButtonPreviewProposal".equals( theEvent ) )
	            {
	                logger.info( "*-*-*-* onPressButtonPreviewProposal" );

	                if( !"".equals( cepr01030102Form.getAssurancePlanCd() ) && errors.getErrorCount() == 0 && errors.getGlobalErrorCount() == 0 )
	                {
	                    cepr01030102Form.setDownloadFlag( "newPage" );
	                    request.getSession().setAttribute( CommonConst.SES_COMMAND, command );
	                    //Map<String, Object> result = cepr01030102Business.doSpecificForEachProduct( command, Cepr01030102Business.JobDescr.DO_DOWNLOAD, errors, theEvent );
	                    //String downloadUrl = ( String ) result.get( CommonConst.SES_DOWNLOAD_URL_SESSION );
	                    //request.getSession().setAttribute( CommonConst.SES_DOWNLOAD_URL_SESSION, downloadUrl );
	                }
	            }
	            else if( "onPressButtonResetLetterContent".equals( theEvent ) )
	            {
	                logger.info( "*-*-*-* onPressButtonResetLetterContent" );
	                //cepr01030301Form.setContent( cepr01030301Business.loadLetterContentFromFile() );
	            }
	            else if( "onPressButtonPreviewLetter".equals( theEvent ) )
	            {
	                logger.info( "*-*-*-* onPressButtonPreviewLetter" );
	                cepr01030301Form.setDownloadFlag( "newPage" );
	                request.getSession().setAttribute( CommonConst.SES_COMMAND, command );
	                request.getSession().setAttribute( CommonConst.SES_DOWNLOAD_URL_SESSION, "wepr01030302.pdf" );
	            }
	            else if( "onPressButtonPreviewCover".equals( theEvent ) )
	            {
	                logger.info( "*-*-*-* onPressButtonPreviewCover" );
	                cepr01030401Form.setDownloadFlag( "newPage" );
	                request.getSession().setAttribute( CommonConst.SES_COMMAND, command );
	                request.getSession().setAttribute( CommonConst.SES_DOWNLOAD_URL_SESSION, "wepr01030402.pdf" );
	            }
	        }*/
	    }
}
