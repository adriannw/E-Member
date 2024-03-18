package id.co.sinarmaslife.eproposal.web;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;

import com.ibatis.common.resources.Resources;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	:
 * Program Name   		: Cepr01030000Controller
 * Description         	: Control Everything about pages
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 01, 2007 9:21:02 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *    1.0                                   Samuel B.
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.business.Cepr00000000GeneralBusiness;
import id.co.sinarmaslife.eproposal.business.Cepr00000000RiderBusiness;
import id.co.sinarmaslife.eproposal.business.Cepr01030101Business;
import id.co.sinarmaslife.eproposal.business.Cepr01030102Business;
import id.co.sinarmaslife.eproposal.business.Cepr01030103Business;
import id.co.sinarmaslife.eproposal.business.Cepr01030104Business;
import id.co.sinarmaslife.eproposal.business.Cepr01030106Business;
import id.co.sinarmaslife.eproposal.business.Cepr01030107Business;
import id.co.sinarmaslife.eproposal.business.Cepr01030108Business;
import id.co.sinarmaslife.eproposal.business.Cepr01030109Business;
import id.co.sinarmaslife.eproposal.business.Cepr01030110Business;
import id.co.sinarmaslife.eproposal.business.Cepr01030111Business;
import id.co.sinarmaslife.eproposal.business.Cepr01030112Business;
import id.co.sinarmaslife.eproposal.business.Cepr01030113Business;
import id.co.sinarmaslife.eproposal.business.Cepr01030114Business;
import id.co.sinarmaslife.eproposal.business.Cepr01030301Business;
import id.co.sinarmaslife.eproposal.business.Cepr01030501Business;
import id.co.sinarmaslife.eproposal.business.Cepr01030502Business;
import id.co.sinarmaslife.eproposal.business.Cepr01030901Business;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.constant.Rider;
import id.co.sinarmaslife.eproposal.common.lookup.Cepr00000000ComboLookupBusiness;
import id.co.sinarmaslife.eproposal.common.lookup.LookupList;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000Generator;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.model.vo.ParticipantVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.standard.StandardConst;
import id.co.sinarmaslife.standard.model.vo.AssurancePlanList;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.model.vo.ViewListProviderDetail;
import id.co.sinarmaslife.standard.propertyeditors.CurrencyFormatEditor;
import id.co.sinarmaslife.standard.util.DateUtil;
import id.co.sinarmaslife.standard.util.DownloadUtil;
import id.co.sinarmaslife.standard.util.JrUtil;
import id.co.sinarmaslife.standard.util.StringUtil;
import id.co.sinarmaslife.std.model.vo.DropDown;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class Cepr01030000Controller extends AbstractWizardFormController  implements Serializable

/*
 * berikut :
initBinder
onBind
suppressValidation
validatePage
postProcessPage
referenceData

kembali :
init binder
onBind
suppressValidation
postProcessPage
referenceData

onchange :
initBinder
onBind
suppressValidation
validatePage
postProcessPage
referenceData
 */

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
	 * Creation Date    	: Aug 30, 2012 9:41:13 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = 661653006000374676L;
	public static final int CEPR01030101JSP = 0;
    public static final int CEPR01030102JSP = 1;
    public static final int CEPR01030301JSP = 2;
    public static final int CEPR01030401JSP = 3;
    //public static final int CEPR00000000DownloadDocumentJSP = 4;
    public static final int CEPR00000000DOWNLOADDOCUMENTJSP = 4;
    public static final int CEPR01030103JSP = 5;
    public static final int CEPR01030104JSP = 6;
    public static final int CEPR01030105JSP = 7;
    public static final int CEPR01030106JSP = 8;
    public static final int CEPR01030501JSP = 9;
    public static final int CEPR01030502JSP = 10;
    public static final int CEPR01030701JSP = 11;
    public static final int CEPR01030107JSP = 12;
    public static final int CEPR01030108JSP = 13;
    public static final int CEPR01030109JSP = 14;
    public static final int CEPR01030110JSP = 15;
    public static final int CEPR01030111JSP = 16;
    public static final int CEPR01030112JSP = 17;
    public static final int CEPR01030901JSP = 18;
    public static final int CEPR01030904JSP = 19;
    public static final int CEPR01030113JSP = 20;
    public static final int CEPR01030114JSP = 21;
      
    protected EproposalManager eproposalManager;
    protected transient CustomDateEditor dateEditor;
    protected transient CustomNumberEditor integerEditor;
    protected CurrencyFormatEditor currencyEditor;
    protected DateFormat dateFormat;
    protected Cepr00000000EditorUtil editorUtil;

    protected Cepr00000000ComboLookupBusiness comboLookupBusiness;
    protected Cepr00000000GeneralBusiness generalBusiness;
    protected Cepr01030101Business cepr01030101Business;
    protected Cepr01030102Business cepr01030102Business;
    protected Cepr01030103Business cepr01030103Business;
    protected Cepr01030104Business cepr01030104Business;
    protected Cepr01030106Business cepr01030106Business;
    protected Cepr01030107Business cepr01030107Business;
    protected Cepr01030108Business cepr01030108Business;
    protected Cepr01030109Business cepr01030109Business;
    protected Cepr01030110Business cepr01030110Business;
    protected Cepr01030111Business cepr01030111Business;
    protected Cepr01030112Business cepr01030112Business;
    protected Cepr01030113Business cepr01030113Business;
    protected Cepr01030114Business cepr01030114Business;
    protected Cepr01030301Business cepr01030301Business;
    protected Cepr01030501Business cepr01030501Business;
    protected Cepr01030502Business cepr01030502Business;
    protected Cepr01030901Business cepr01030901Business;

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
        logger.info( "*-*-*-* Cepr01030000Controller.formBackingObject" );
        this.pages = getPages();
        
        setAllowDirtyBack( false );
        Cepr01030000HoldingForm cepr01030000HoldingForm = new Cepr01030000HoldingForm();
        CredentialsVO credentialsVO = ( CredentialsVO ) request.getSession().getAttribute( StandardConst.CREDENTIALS_SES );
        Cepr01010101Form cepr01010101Form = new Cepr01010101Form();
        Cepr01030101Form cepr01030101Form = new Cepr01030101Form();
        Cepr01030102Form cepr01030102Form = new Cepr01030102Form();
        Cepr01030103Form cepr01030103Form = new Cepr01030103Form();
        Cepr01030104Form cepr01030104Form = new Cepr01030104Form();
        Cepr01030105Form cepr01030105Form = new Cepr01030105Form();
        Cepr01030106Form cepr01030106Form = new Cepr01030106Form();
        Cepr01030107Form cepr01030107Form = new Cepr01030107Form();
        Cepr01030108Form cepr01030108Form = new Cepr01030108Form();
        Cepr01030109Form cepr01030109Form = new Cepr01030109Form();
        Cepr01030110Form cepr01030110Form = new Cepr01030110Form();
        Cepr01030111Form cepr01030111Form = new Cepr01030111Form();
        Cepr01030112Form cepr01030112Form = new Cepr01030112Form();
        Cepr01030113Form cepr01030113Form = new Cepr01030113Form();
        Cepr01030114Form cepr01030114Form = new Cepr01030114Form();
        Cepr01030301Form cepr01030301Form = new Cepr01030301Form();
        Cepr01030401Form cepr01030401Form = new Cepr01030401Form();
        Cepr01030501Form cepr01030501Form = new Cepr01030501Form();
        Cepr01030502Form cepr01030502Form = new Cepr01030502Form();
        Cepr01030901Form cepr01030901Form = new Cepr01030901Form();
        Cepr01031001Form cepr01031001Form = new Cepr01031001Form();
        
        comboLookupBusiness = Cepr00000000ComboLookupBusiness.getInstance( eproposalManager, editorUtil );
        generalBusiness = Cepr00000000GeneralBusiness.getInstance( eproposalManager, editorUtil );

        cepr01030101Business = new Cepr01030101Business( eproposalManager, editorUtil );
        cepr01030102Business = new Cepr01030102Business( eproposalManager, editorUtil );
        cepr01030103Business = new Cepr01030103Business( eproposalManager, editorUtil );
        cepr01030104Business = new Cepr01030104Business( eproposalManager, editorUtil );
        cepr01030106Business = new Cepr01030106Business( eproposalManager, editorUtil );
        cepr01030107Business = new Cepr01030107Business( eproposalManager, editorUtil );
        cepr01030108Business = new Cepr01030108Business( eproposalManager, editorUtil );
        cepr01030109Business = new Cepr01030109Business( eproposalManager, editorUtil );
        cepr01030110Business = new Cepr01030110Business( eproposalManager, editorUtil );
        cepr01030111Business = new Cepr01030111Business( eproposalManager, editorUtil );
        cepr01030112Business = new Cepr01030112Business( eproposalManager, editorUtil );
        cepr01030113Business = new Cepr01030113Business( eproposalManager, editorUtil );
        cepr01030114Business = new Cepr01030114Business( eproposalManager, editorUtil );
        cepr01030301Business = new Cepr01030301Business( eproposalManager, editorUtil, request );
        cepr01030501Business = new Cepr01030501Business( eproposalManager, editorUtil);
        cepr01030502Business = new Cepr01030502Business( eproposalManager, editorUtil);
        cepr01030901Business = new Cepr01030901Business( eproposalManager, editorUtil); 
        
       // cepr01030101Business.resetForm( cepr01030101Form, generalBusiness, comboLookupBusiness, credentialsVO );  
        
        HttpSession session = request.getSession(true);
        String jenis = (String) request.getSession().getAttribute("jn_bank");
                
        if(jenis != null){
            if(jenis.equals("2")){
            	cepr01030101Form.setDisajikanListDisplay( CommonConst.DISPLAY_FALSE);
            	cepr01030101Form.setDisajikanAdminListDisplay( CommonConst.DISPLAY_TRUE);
            	cepr01030101Form.setJn_bank(jenis);
            }
            }else{
            	cepr01030101Form.setDisajikanListDisplay( CommonConst.DISPLAY_TRUE);
            	cepr01030101Form.setDisajikanAdminListDisplay( CommonConst.DISPLAY_FALSE);
            }
        
        cepr01030102Business.resetForm( cepr01030102Form );
        cepr01030103Business.resetForm( cepr01030103Form );
        cepr01030106Business.resetForm( cepr01030106Form );
        cepr01030107Business.resetForm( cepr01030107Form );
        cepr01030108Business.resetForm( cepr01030108Form );
        cepr01030109Business.resetForm( cepr01030109Form );
        cepr01030110Business.resetForm( cepr01030110Form );
        cepr01030111Business.resetForm( cepr01030111Form );
        cepr01030112Business.resetForm( cepr01030112Form );
        cepr01030113Business.resetForm( cepr01030113Form );
        cepr01030114Business.resetForm( cepr01030114Form );
        cepr01030301Business.resetForm( cepr01030301Form );
        cepr01030104Business.resetForm( cepr01030104Form );
        cepr01030501Business.resetForm( cepr01030501Form );
        cepr01030502Business.resetForm( cepr01030502Form );
        
        cepr01030000HoldingForm.setCredentialsVO( credentialsVO );
        cepr01030000HoldingForm.setCepr01030101Form( cepr01030101Form );
        cepr01030000HoldingForm.setCepr01030102Form( cepr01030102Form );
        cepr01030000HoldingForm.setCepr01030103Form( cepr01030103Form );
        cepr01030000HoldingForm.setCepr01030104Form( cepr01030104Form );
        cepr01030000HoldingForm.setCepr01030105Form( cepr01030105Form );
        cepr01030000HoldingForm.setCepr01030106Form( cepr01030106Form );
        cepr01030000HoldingForm.setCepr01030107Form( cepr01030107Form );
        cepr01030000HoldingForm.setCepr01030108Form( cepr01030108Form );
        cepr01030000HoldingForm.setCepr01030109Form( cepr01030109Form );
        cepr01030000HoldingForm.setCepr01030110Form( cepr01030110Form );
        cepr01030000HoldingForm.setCepr01030111Form( cepr01030111Form );
        cepr01030000HoldingForm.setCepr01030112Form( cepr01030112Form );
        cepr01030000HoldingForm.setCepr01030113Form( cepr01030113Form );
        cepr01030000HoldingForm.setCepr01030114Form( cepr01030114Form );
        cepr01030000HoldingForm.setCepr01030301Form( cepr01030301Form );
        cepr01030000HoldingForm.setCepr01030401Form( cepr01030401Form );
        cepr01030000HoldingForm.setCepr01030501Form( cepr01030501Form );
        cepr01030000HoldingForm.setCepr01030502Form( cepr01030502Form );
        cepr01030000HoldingForm.setCepr01030901Form( cepr01030901Form );
        cepr01030000HoldingForm.setCepr01031001Form( cepr01031001Form );
        
        String productId = request.getParameter("productId");
        productId = productId == null? null : productId.replace("'", "").trim();
        String ax = request.getParameter("a");
        String bx = request.getParameter("b");
        String cx = request.getParameter("c");
        String dx = request.getParameter("d");
        String ex = request.getParameter("e");
        String fx = request.getParameter("f");
        String gx = request.getParameter("g");
        String hx = request.getParameter("h");
        String ix = request.getParameter("i");
        String jx = request.getParameter("j");
        
        String msag_id = request.getParameter("msags_id");
        String fcd = request.getParameter("fcd");
        
//        if( a!=null || b!=null || c!=null || d!=null || e!=null ||
//        		 f!=null || g!=null || h!=null || i!=null || j!=null ){
//        	request.getSession().setAttribute("a", a);
//        	request.getSession().setAttribute("b", b);
//        	request.getSession().setAttribute("c", c);
//        	request.getSession().setAttribute("d", d);
//        	request.getSession().setAttribute("e", e);
//        	request.getSession().setAttribute("f", f);
//        	request.getSession().setAttribute("g", g);
//        	request.getSession().setAttribute("h", h);
//        	request.getSession().setAttribute("i", i);
//        	request.getSession().setAttribute("j", j);
//        }
        String a = (String) ax;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date b = null;
        if(bx != null){
        	if(!"".equals( (String) bx)){
        		b = sdf.parse((String)bx);
        	}
        }
        int c = 0;
        if(cx != null){
        	if(!"".equals((String)cx)){
        		c = Integer.parseInt( (String)cx);
        	}
        }
        String d = (String) dx;
        String e = (String) ex;
        Date f = null;
        if(fx != null){
        	if(!"".equals( (String) fx)){
        		f = sdf.parse((String)fx);
        	}
        }
        int g = 0;
        if(gx != null){
        	if(!"".equals( (String) gx)){
        		g = Integer.parseInt((String) gx);
        	}
        }
        String h = (String) hx;
        String i = (String) ix;
        Date j = null;
        if(jx != null){
        	if(!"".equals((String)jx)){
        		j = sdf.parse((String)jx);
        	}
        }
        
        request.getSession().removeAttribute("a");
    	request.getSession().removeAttribute("b");
    	request.getSession().removeAttribute("c");
    	request.getSession().removeAttribute("d");
    	request.getSession().removeAttribute("e");
    	request.getSession().removeAttribute("f");
    	request.getSession().removeAttribute("g");
    	request.getSession().removeAttribute("h");
    	request.getSession().removeAttribute("i");
    	request.getSession().removeAttribute("j");
    	request.getSession().removeAttribute("clickforlife");
    	request.getSession().removeAttribute("clickForLife");
    	request.setAttribute("clickForLife", null);
    	
    	cepr01030000HoldingForm.getCepr01030101Form().setPolicyHolderName(a);
    	cepr01030000HoldingForm.getCepr01030101Form().setPolicyHolderBirthDay(b);
    	cepr01030000HoldingForm.getCepr01030101Form().setPolicyHolderAge(c);
    	cepr01030000HoldingForm.getCepr01030101Form().setPolicyHolderSexCd(d);
    	cepr01030000HoldingForm.getCepr01030101Form().setInsuredName(e);
    	cepr01030000HoldingForm.getCepr01030101Form().setInsuredBirthDay(f);
    	cepr01030000HoldingForm.getCepr01030101Form().setInsuredAge(g);
    	cepr01030000HoldingForm.getCepr01030101Form().setInsuredSexCd(h);
    	cepr01030000HoldingForm.getCepr01030101Form().setProposalDate(eproposalManager.selectNowDate());
    	//cepr01030000HoldingForm.getCepr01030101Form().setProposalDate(eproposalManager.selectNowDate());
       
        
        return cepr01030000HoldingForm;
    }

    @Override
    protected void initBinder( HttpServletRequest request, ServletRequestDataBinder binder ) throws Exception
    {
        logger.info( "*-*-*-* Cepr01030000Controller.initBinder" );
        binder.registerCustomEditor( Date.class, null, dateEditor );
        binder.registerCustomEditor( Integer.class, null, integerEditor );
        binder.registerCustomEditor( BigDecimal.class, null, currencyEditor );
    }

    @Override
    protected void onBind( HttpServletRequest request, Object command )
    {
        logger.info( "*-*-*-* Cepr01030000Controller.onBind" );
        String theEvent = request.getParameter( "theEvent" );

        Cepr01030101Form cepr01030101Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030101Form();
        cepr01030101Form.setTheEvent( theEvent );

        int currentPage = getCurrentPage( request );
        if( currentPage == CEPR01030102JSP )
        {
            cepr01030102Business.doSpecificForEachProduct( command, Cepr01030102Business.JobDescr.DO_PROCESS_FORM_AFTER_SUBMISSION_BEFORE_VALIDATION, null, theEvent );
        }
    }

    @Override
    protected boolean suppressValidation( HttpServletRequest request, Object command, BindException errors )
    {
        logger.info( "*-*-*-* Cepr01030000Controller.suppressValidation" );

        String theEvent = request.getParameter( "theEvent" );
        logger.info( "*-*-*-* theEvent = " + theEvent );

        boolean result = false;
        int currentPage = getCurrentPage( request );
        int targetPage = getTargetPage( request, currentPage );
        logger.info( "*-*-*-* currentPage = " + currentPage );
        logger.info( "*-*-*-* targetPage = " + targetPage );

        if( "onChangeCurrencyCd".equals( theEvent ) )
        {
            result = true;   
        }      
        else if( targetPage == CEPR01030101JSP && !"onPressButtonPreviewLetter".equals( theEvent ))
        {
            result = true;
        }
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
        else if( targetPage == CEPR01030701JSP )
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
        else if( currentPage == CEPR01030101JSP && targetPage == CEPR01030901JSP )
        {
            result = true;
        }
        else if( currentPage == CEPR01030101JSP && targetPage == CEPR01030904JSP )
        {
            result = true;
        }
        else if(    ( currentPage == CEPR01030102JSP && targetPage == CEPR01030103JSP )
                 || ( currentPage == CEPR01030102JSP && targetPage == CEPR01030104JSP ) )
        {
            result = true;
        }
        else if( currentPage == CEPR01030101JSP && targetPage == CEPR00000000DOWNLOADDOCUMENTJSP )
        {
            result = true;
        }     
      
        logger.info( "*-*-*-* result suppressValidation = " + result );
        return result;
    }

    @Override
    protected void validatePage( Object command, Errors errors, int page )
    {
        Cepr01030101Form cepr01030101Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        
        if( page == CEPR01030101JSP )
        {
            logger.info( "*-*-*-* validateCepr01030101" );
            
            
            Cepr01030101Validator cepr01030101Validator = new Cepr01030101Validator( eproposalManager, editorUtil, command, errors );
            if( "onPressButtonPreviewLetter".equals( cepr01030101Form.getTheEvent() ) )
            {
                cepr01030101Validator.validateCreatingLetter();
            }
            else
            {
                cepr01030101Validator.validateCommon();
            }
        }
        else if( page == CEPR01030102JSP )
        {
            logger.info( "*-*-*-* validateCepr01030102" );
            Cepr01030102Validator cepr01030102Validator = new Cepr01030102Validator( eproposalManager, editorUtil, command, errors );
            cepr01030102Validator.validateCommon();
            cepr01030102Validator.validate();
            cepr01030102Business.doSpecificForEachProduct( command, Cepr01030102Business.JobDescr.DO_VALIDATION, errors, "" );
            cepr01030102Validator.validateEmptyProduct();
        }
        else if( page == CEPR01030103JSP )
        {
            logger.info( "*-*-*-* validateCepr01030103" );
            Cepr01030103Validator cepr01030103Validator = new Cepr01030103Validator( eproposalManager, editorUtil, command, errors );        
           	cepr01030103Validator.validate();         
        }
        else if( page == CEPR01030104JSP )
        {
            logger.info( "*-*-*-* validateCepr01030104" );
            Cepr01030104Validator cepr01030104Validator = new Cepr01030104Validator( eproposalManager, editorUtil, command, errors );
            if(cepr01030102Form.getLeftPartOfBusinessCd() == 141 || cepr01030102Form.getLeftPartOfBusinessCd() == 127)//eduvest-cerdas siswa
            {
            	
            }
            else
            {
            	 cepr01030104Validator.validate();
            }
           
        }
        else if( page == CEPR01030106JSP )
        {
        	logger.info( "*-*-*-* validateCepr01030106" );
            Cepr01030106Validator cepr01030106Validator = new Cepr01030106Validator( eproposalManager, editorUtil, command, errors );
            cepr01030106Validator.validate();
            
        }
        else if( page == CEPR01030107JSP )
        {
            logger.info( "*-*-*-* validateCepr01030107" );
            Cepr01030107Validator cepr01030107Validator = new Cepr01030107Validator( eproposalManager, editorUtil, command, errors );
            cepr01030107Validator.validate();
        }
        else if( page == CEPR01030108JSP )
        {
            logger.info( "*-*-*-* validateCepr01030108" );
            Cepr01030108Validator cepr01030108Validator = new Cepr01030108Validator( eproposalManager, editorUtil, command, errors );
            cepr01030108Validator.validate();
        }
        else if( page == CEPR01030109JSP )
        {
            logger.info( "*-*-*-* validateCepr01030109" );
            Cepr01030109Validator cepr01030109Validator = new Cepr01030109Validator( eproposalManager, editorUtil, command, errors );
            cepr01030109Validator.validate();
        }
        else if( page == CEPR01030110JSP )
        {
        	logger.info( "*-*-*-* validateCepr01030110" );
            Cepr01030110Validator cepr01030110Validator = new Cepr01030110Validator( eproposalManager, editorUtil, command, errors );
            cepr01030110Validator.validate();
            
        }
        else if( page == CEPR01030111JSP )
        {
        	logger.info( "*-*-*-* validateCepr01030111" );
            Cepr01030111Validator cepr01030111Validator = new Cepr01030111Validator( eproposalManager, editorUtil, command, errors );
            cepr01030111Validator.validate();
            
        }
        else if( page == CEPR01030112JSP )
        {
        	logger.info( "*-*-*-* validateCepr01030112" );
            Cepr01030112Validator cepr01030112Validator = new Cepr01030112Validator( eproposalManager, editorUtil, command, errors );
            cepr01030112Validator.validate();
            
        }
        else if( page == CEPR01030113JSP )
        {
        	logger.info( "*-*-*-* validateCepr01030113" );
            Cepr01030113Validator cepr01030113Validator = new Cepr01030113Validator( eproposalManager, editorUtil, command, errors );
            cepr01030113Validator.validate();
            
        }
        else if( page == CEPR01030114JSP )
        {
        	logger.info( "*-*-*-* validateCepr01030114" );
            Cepr01030114Validator cepr01030114Validator = new Cepr01030114Validator( eproposalManager, editorUtil, command, errors );
            cepr01030114Validator.validate();
            
        }
        else if( page == CEPR01030401JSP )
        {
            logger.info( "*-*-*-* validateCepr01030401" );
            Cepr01030401Validator cepr01030401Validator = new Cepr01030401Validator( eproposalManager, editorUtil, command, errors );
            cepr01030401Validator.validateCommon();	            
        }
        else if( page == CEPR01030901JSP )
        {
            logger.info( "*-*-*-* validateCepr01030901" );
            Cepr01030901Validator cepr01030901Validator = new Cepr01030901Validator( eproposalManager, editorUtil, command, errors );
            cepr01030901Validator.validateCommon();	            
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors, int)
     */
    /* (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.AbstractWizardFormController#referenceData(javax.servlet.http.HttpServletRequest, java.lang.Object, org.springframework.validation.Errors, int)
     */
    @Override
    protected Map referenceData( HttpServletRequest request, Object command, Errors errors, int page ) throws Exception
    {
        String theEvent = request.getParameter( "theEvent" );
        CredentialsVO credentialsVO = ( CredentialsVO ) request.getSession().getAttribute( StandardConst.CREDENTIALS_SES );
        
        Map<String, Object> map = new HashMap<String, Object>();
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Object currentUser = request.getSession().getAttribute("currentUser");
        Cepr01010101Form cepr01010101Form = (Cepr01010101Form) currentUser;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01030103Form cepr01030103Form = cepr01030000HoldingForm.getCepr01030103Form();
        Cepr01030104Form cepr01030104Form = cepr01030000HoldingForm.getCepr01030104Form();
        Cepr01030106Form cepr01030106Form = cepr01030000HoldingForm.getCepr01030106Form();
        Cepr01030107Form cepr01030107Form = cepr01030000HoldingForm.getCepr01030107Form();
        Cepr01030108Form cepr01030108Form = cepr01030000HoldingForm.getCepr01030108Form();
        Cepr01030109Form cepr01030109Form = cepr01030000HoldingForm.getCepr01030109Form();
        Cepr01030110Form cepr01030110Form = cepr01030000HoldingForm.getCepr01030110Form();
        Cepr01030111Form cepr01030111Form = cepr01030000HoldingForm.getCepr01030111Form();
        Cepr01030112Form cepr01030112Form = cepr01030000HoldingForm.getCepr01030112Form();
        Cepr01030113Form cepr01030113Form = cepr01030000HoldingForm.getCepr01030113Form();
        Cepr01030114Form cepr01030114Form = cepr01030000HoldingForm.getCepr01030114Form();
        Cepr01031001Form cepr01031001Form = cepr01030000HoldingForm.getCepr01031001Form();
        
        // tambahan filter eka sarjana mandiri
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01030901Form cepr01030901Form = cepr01030000HoldingForm.getCepr01030901Form();
       
      
        
        if( page == CEPR01030101JSP )
        {
//        	Integer flag=ServletRequestUtils.getIntParameter(request, "flag",0);
//        	if(flag==4){//Hitung umur pemegang polis
//				int umur_pp_skrg=0;
//				SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
//				try {
//					umur_pp_skrg =editorUtil.getAge(eproposalManager.selectNowDate(), sdf.parse(sdf.format(cepr01030101Form.getPolicyHolderBirthDay())));
//					cepr01030101Form.setPolicyHolderAge(umur_pp_skrg);
//				} catch (Exception e) {
//					//err.reject("", "Format tanggal lahir Pemegang Polis salah");
//				}
//				//err.reject("","Hitung Umur Pemegang Polis");
//        	}else if(flag==5){//Hitung Umur Tertanggung
//				int umur_tt_skrg=0;
//				SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
//				try {
//					umur_tt_skrg =editorUtil.getAge(eproposalManager.selectNowDate(), sdf.parse(sdf.format(cepr01030101Form.getInsuredBirthDay())));
//					cepr01030101Form.setInsuredAge(umur_tt_skrg);
//				} catch (Exception e) {
//					//err.reject("", "Format tanggal lahir Tertanggung salah");
//				}
//				//err.reject("","Hitung Umur Tertanggung");
//				
//        	}
    
        String nama_agen="";
        if("onSelectRow5".equals( cepr01030101Form.getTheEvent())) {
            cepr01030101Form.setDownloadFlag( "newPage" );
            request.getSession().setAttribute( CommonConst.SES_DOWNLOAD_URL_SESSION, "wepr01030302.pdf" );
        	
        }
        else if("onSelectRow4".equals( cepr01030101Form.getTheEvent())) {
       	 String id = request.getParameter( "selectedSmcId" );
   		
				
				  // String id = (String) request.getAttribute("selectedSmcId");
				  
				  Class.forName("org.postgresql.Driver"); String jdbcUrl =
				  "jdbc:postgresql://localhost:5432/postgres"; String username = "postgres";
				  String password = "admin"; Connection connection =
				  DriverManager.getConnection(jdbcUrl, username, password); Statement stmt =
				  connection.createStatement() ; String query =
				  "DELETE from eka.member where id='"+id+"';" ; 
				  int rs = stmt.executeUpdate(query) ;
				  
				  query = "select id,nama, nomor_hp,tgl_lahir, email, no_ktp from eka.member;";
				  ResultSet rs2 = stmt.executeQuery(query);
				  
				  
				  List<HashMap<String, Object>> result2 = new ArrayList<HashMap<String,
				  Object>>(); 
				  while (rs2.next()) {
					  Map<String, Object> result = new HashMap<String, Object>(); 
					  result.put("id", rs2.getString(1));
					  result.put("nama", rs2.getString(2)); 
					  result.put("nomor_hp", rs2.getString(3)); 
					  result.put("tgl_lahir", rs2.getString(4));
					  result.put("email", rs2.getString(5));
					  result.put("no_ktp", rs2.getString(6));
				  result2.add((HashMap<String, Object>)result);
				  	}
				  
				  request.setAttribute( "allList", result2 );
				  
				  
				  rs2.close(); connection.close(); stmt.close();
				 
       	
       }
        else if("onSelectRow3".equals( cepr01030101Form.getTheEvent())) {
        	 String id = request.getParameter( "selectedSmcId" );
    		
				/*
				 * // String id = (String) request.getAttribute("selectedSmcId");
				 * 
				 * Class.forName("org.postgresql.Driver"); String jdbcUrl =
				 * "jdbc:postgresql://localhost:5432/postgres"; String username = "postgres";
				 * String password = "admin"; Connection connection =
				 * DriverManager.getConnection(jdbcUrl, username, password); Statement stmt =
				 * connection.createStatement() ; String query =
				 * "DELETE from public.user where id='"+id+"';" ; int rs =
				 * stmt.executeUpdate(query) ;
				 * 
				 * query = "select id,nama from public.user;" ; ResultSet rs2 =
				 * stmt.executeQuery(query);
				 * 
				 * 
				 * List<HashMap<String, Object>> result2 = new ArrayList<HashMap<String,
				 * Object>>(); while (rs2.next()) { Map<String, Object> result = new
				 * HashMap<String, Object>(); result.put("id", rs2.getString(1));
				 * result.put("nama", rs2.getString(2)); result2.add((HashMap<String, Object>)
				 * result); }
				 * 
				 * request.setAttribute( "allList", result2 );
				 * 
				 * 
				 * rs2.close(); connection.close(); stmt.close();
				 */
        	 Class.forName("org.postgresql.Driver");
             String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
             String username = "postgres";
             String password = "admin";
             Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement stmt = connection.createStatement() ;
             String query = "select id,nama, nomor_hp,tgl_lahir, email, no_ktp from eka.member;";
             ResultSet rs = stmt.executeQuery(query);
             
             //List<HashMap<String, Object>> result2 = new ArrayList<HashMap<String, Object>>();
             List<ViewListProviderDetail> ListForRpm = new  ArrayList<ViewListProviderDetail>();
             while (rs.next()) {
            	 ViewListProviderDetail result = new ViewListProviderDetail();
            	 result.setId(rs.getString(1));
            	 result.setNama(rs.getString(2));
           
            	 result.setNomor_hp(rs.getString(3));
            	 result.setTgl_lahir(rs.getString(4));
            	 result.setEmail(rs.getString(5));
            	 result.setNo_ktp(rs.getString(6));
            	 ListForRpm.add(result);
           	}
             
             XLSCreatorReportProviderMCU xls =new XLSCreatorReportProviderMCU();
             xls.buildExcelDocument("test.xls","C:\\Member",ListForRpm);
             
             
             connection.close();
             stmt.close();

            // DownloadUtil.downloadAttachment(request, null, "C:\\Ekaweb", "test.xls");
            
        	
        	
        } else {
        	  Class.forName("org.postgresql.Driver");
              String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
              String username = "postgres";
              String password = "admin";
              Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
              Statement stmt = connection.createStatement() ;
              String query = "select id,nama, nomor_hp,tgl_lahir, email, no_ktp, foto from eka.member;";
              ResultSet rs = stmt.executeQuery(query);
             
             
             	
              List<HashMap<String, Object>> result2 = new ArrayList<HashMap<String, Object>>();
              while (rs.next()) {
              	 Map<String, Object> result = new HashMap<String, Object>();
              	  result.put("id", rs.getString(1));
				  result.put("nama", rs.getString(2)); 
				  result.put("nomor_hp", rs.getString(3)); 
				  result.put("tgl_lahir", rs.getString(4));
				  result.put("email", rs.getString(5));
				  result.put("no_ktp", rs.getString(6));
				  result.put("foto", rs.getString(7));
              	 result2.add((HashMap<String, Object>) result);
            	}
              
              request.setAttribute( "allList", result2 );
              rs.close();
              connection.close();
              stmt.close();
           
        	
        	
        	
        }
     
       	String proposalUser=cepr01030000HoldingForm.getCepr01030101Form().getProposalUser();
       	String proposalUserCd=cepr01030000HoldingForm.getCepr01030101Form().getProposalUserCd();
       	
       	if( page == CEPR01030101JSP )
        {
       		
       		
        }
       	
      
        }
        else if( page == CEPR01030301JSP )
        {
        	//CHANGE FORMS
        	if( "onPressButtonCountTotalSumInsured".equals( cepr01030101Form.getTheEvent() ) ){
        		if( cepr01030102Form.getTotalSumInsured() != null && cepr01030102Form.getTotalSumInsured().compareTo( BigDecimal.ZERO ) > 0 ){
        			cepr01030103Form.setTermRiderAmount( cepr01030102Form.getTotalSumInsured() );
        		}
        		
        	}
//        	if( "onChangeAssurancePlanCd".equals( cepr01030101Form.getTheEvent() ) ){
//        		cepr01030107Business.resetForm(cepr01030107Form);
//        		cepr01030108Business.resetForm(cepr01030108Form);
//        		cepr01030109Business.resetForm(cepr01030109Form);
//        		cepr01030110Business.resetForm(cepr01030110Form);
//        		cepr01030111Business.resetForm(cepr01030111Form);
//        		cepr01030112Business.resetForm(cepr01030112Form);
//        	}
        	
           
         
            // Create No_Proposal & Save data to EKA.MST_DATA_PROPOSAL + EKA.MST_PROPOSAL_PRODUCT (Saat tekan Tombol "LIHAT PROPOSAL") 
            // ** Adrian@17/10/2013             
        	if( "onPressButtonResetLetterContent".equals( cepr01030101Form.getTheEvent() )  ){
        		String id = request.getParameter( "id" );
        		String nama = request.getParameter( "nama" );
        		String noHp = request.getParameter( "noHp" );
        		String tglLahir = request.getParameter( "tglLahir" );
        		String email = request.getParameter( "email" );
        		String noKtp = request.getParameter( "noKtp" );
        		String foto = request.getParameter( "foto" );
                // String id = (String) request.getAttribute("selectedSmcId");
                 
                 Class.forName("org.postgresql.Driver");
                 String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
                 String username = "postgres";
                 String password = "admin";
                 Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
                 Statement stmt = connection.createStatement() ;
                 String query = "update eka.member set nama='"+nama+"',nomor_Hp='"+noHp+"',email='"+email+"',no_ktp='"+noKtp+"',foto='"+foto+"' where id='"+id+"';" ;
                 int rs = stmt.executeUpdate(query) ;
                
                
                 request.setAttribute("id", id);
              	 request.setAttribute("nama", nama);
                
              	 request.setAttribute("noHp",noHp);
              	 request.setAttribute("email", email);
              	 request.setAttribute("noKtp",noKtp);
              	 request.setAttribute("foto", foto);
                
                 connection.close();
                 stmt.close();
        		
        		
        	}else {
        		
        		 String id = request.getParameter( "selectedSmcId" );
                 // String id = (String) request.getAttribute("selectedSmcId");
                  
                  Class.forName("org.postgresql.Driver");
                  String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
                  String username = "postgres";
                  String password = "admin";
                  Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
                  Statement stmt = connection.createStatement() ;
                  String query = "select  id,nama, nomor_hp,tgl_lahir, email, no_ktp, foto from eka.member where id='"+id+"';" ;
                  ResultSet rs = stmt.executeQuery(query) ;
                 
                 
                 	
                  List<HashMap<String, Object>> result2 = new ArrayList<HashMap<String, Object>>();
                  while (rs.next()) {
                	  request.setAttribute("id", rs.getString(1));
                 	 request.setAttribute("nama", rs.getString(2));
                 	request.setAttribute("noHp", rs.getString(3));
                	request.setAttribute("tglLahir", rs.getString(4));
                	request.setAttribute("email", rs.getString(5));
                	request.setAttribute("noKtp", rs.getString(6));
                	request.setAttribute("foto", rs.getString(7));
                  }
                  rs.close();
                  connection.close();
                  stmt.close();
                
        		
        	}
        	if( "onPressButtonResetLetterContent".equals( cepr01030101Form.getTheEvent() ) ){
        		
        		
        	}
                //===========
        	 
         
            
                    
            }          
      
    
        
        
        return map;
    }

    @Override
    protected ModelAndView processFinish( HttpServletRequest request, HttpServletResponse response, Object command, BindException errors ) throws Exception
    {  
    	logger.info( "*-*-*-* Cepr01030000Controller.processFinish" ); 
        throw new RuntimeException( "meredirect view yg seharusnya tidak pernah dipanggil" );
//        return new ModelAndView( new RedirectView( "wepr01030000.htm" ) );
       
    }

    protected void postProcessPage( HttpServletRequest request, Object command, Errors errors, int page ) throws Exception
    {
        logger.info( "*-*-*-* Cepr01030000Controller.postProcessPage" );
        logger.info( "*-*-*-* page = " + page );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030301Form cepr01030301Form = cepr01030000HoldingForm.getCepr01030301Form();
        Cepr01030401Form cepr01030401Form = cepr01030000HoldingForm.getCepr01030401Form();
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01030901Form cepr01030901Form = cepr01030000HoldingForm.getCepr01030901Form();
        
        String theEvent = request.getParameter( "theEvent" );
        logger.info( "*-*-*-* theEvent = " + theEvent );
        if( errors.getErrorCount() == 0 && errors.getGlobalErrorCount() == 0 )
        {
            if( "onPressButtonPreviewProposal".equals( theEvent ) )
            {
                logger.info( "*-*-*-* onPressButtonPreviewProposal" );
                	
                if( !"".equals( cepr01030102Form.getAssurancePlanCd() ) && errors.getErrorCount() == 0 && errors.getGlobalErrorCount() == 0 )
                {
                	// FIXME fadly
//                    if( ( cepr01030102Form.getSmileLadiesPackageCd() != null && cepr01030102Form.getSmileLadiesPackageCd() > 1 ) &&  
//                    		( cepr01030102Form.getPremiumCombinationCd() != null && !cepr01030102Form.getPremiumCombinationCd().equals( new BigDecimal("60"))))
//                    {
//                    	
//                    }else{
                        cepr01030102Form.setDownloadFlag( "newPage" );
                        request.getSession().setAttribute( CommonConst.SES_COMMAND, command );
                        Map<String, Object> result = cepr01030102Business.doSpecificForEachProduct( command, Cepr01030102Business.JobDescr.DO_DOWNLOAD, errors, theEvent );
                        String downloadUrl = ( String ) result.get( CommonConst.SES_DOWNLOAD_URL_SESSION );
                        request.getSession().setAttribute( CommonConst.SES_DOWNLOAD_URL_SESSION, downloadUrl );
//                    }
                }
            }
            else if( "onPressButtonFax".equals( theEvent ) || "onPressButtonEmail".equals( theEvent ))
            {
                logger.info( "*-*-*-* onPressButtonFax onPressButtonEmail" );

                if( !"".equals( cepr01030102Form.getAssurancePlanCd() ) && errors.getErrorCount() == 0 && errors.getGlobalErrorCount() == 0 )
                {
                    cepr01030102Form.setDownloadFlag( "newPage" );
                    request.getSession().setAttribute( CommonConst.SES_COMMAND, command );
                    Map<String, Object> result = cepr01030102Business.doSpecificForEachProduct( command, Cepr01030102Business.JobDescr.DO_DOWNLOAD, errors, theEvent );
                    String downloadUrl = ( String ) result.get( CommonConst.SES_DOWNLOAD_URL_SESSION );
                    request.getSession().setAttribute( CommonConst.SES_DOWNLOAD_URL_SESSION, downloadUrl );
                    request.getSession().setAttribute( "pdfEvent", theEvent );
                }
            }
            else if( "onPressButtonResetLetterContent".equals( theEvent ) )
            {
                logger.info( "*-*-*-* onPressButtonResetLetterContent" );
                cepr01030301Form.setContent( cepr01030301Business.loadLetterContentFromFile() );
            }         
            else if( "onPressButtonPreviewCover".equals( theEvent ) )
            {
                logger.info( "*-*-*-* onPressButtonPreviewCover" );
                cepr01030401Form.setDownloadFlag( "newPage" );
                request.getSession().setAttribute( CommonConst.SES_COMMAND, command );
                request.getSession().setAttribute( CommonConst.SES_DOWNLOAD_URL_SESSION, "wepr01030402.pdf" );
            }
            else if( "onPressButtonDownloadMedical".equals( theEvent ) )
            {
                logger.info( "*-*-*-* onPressButtonDownloadMedical" );
                cepr01030101Form.setDownloadFlag( "newPage" );
                request.getSession().setAttribute( CommonConst.SES_COMMAND, command );
                request.getSession().setAttribute( CommonConst.SES_DOWNLOAD_URL_SESSION, "wepr01030601.pdf" );
            }
            else if( "onPressButtonDownloadViewMedical".equals( theEvent ) )
            {
                logger.info( "*-*-*-* onPressButtonDownloadViewMedical" );
                cepr01030101Form.setDownloadFlag( "newPage" );
                request.getSession().setAttribute( CommonConst.SES_COMMAND, command );
                request.getSession().setAttribute( CommonConst.SES_DOWNLOAD_URL_SESSION, "wepr01030801.pdf" );
            } else if( "onPressButtonPreviewDPLK".equals( theEvent ) )
            {
            	logger.info( "*-*-*-* onPressButtonCreateDPLK" );
                cepr01030901Form.setDownloadFlag( "newPage" );
                request.getSession().setAttribute( CommonConst.SES_COMMAND, command );
                request.getSession().setAttribute( CommonConst.SES_DOWNLOAD_URL_SESSION, "wepr01030902.pdf" );
            }else if( "onPressButtonUpdateProduct".equals( theEvent ) )
            {
            	Map cmd = new HashMap();
            	String dir = request.getContextPath()+"/include/"+"installer";
            	List<DropDown> daftarFile = listFilesInDirectory(dir);
//    			for(DropDown d : daftarFile) {
//    				if(d.getKey().toLowerCase().startsWith("upas_")) {
//    					d.setDesc("Update Proposal hanya untuk Agency System (Untuk Regional Tidak perlu download proposal yang Agency System)");
//    				}else if(d.getKey().toLowerCase().startsWith("update_absen")) {
//    					d.setDesc("Update Absensi");
//    				}else if(d.getKey().toLowerCase().startsWith("update_")) {
//    					d.setDesc("Update Proposal Regional (Proposal Biasa)");
//    				}else if(d.getKey().toLowerCase().startsWith("upfa_")) {
//    					d.setDesc("Update Proposal Bancassurance");
//    				}else if(d.getKey().toLowerCase().startsWith("upbsm_")) {
//    					d.setDesc("Update Proposal Bank Sinarmas");
//    				}else {
//    					d.setDesc("");
//    				}
//    			}    			
    			cmd.put("daftarFile", daftarFile);
           }
           else if( "onPressButtonDowloadRiskProfil".equals( theEvent ) )
           {                
                cepr01030101Form.setDownloadFlag( "newPage" );
                request.getSession().setAttribute( CommonConst.SES_COMMAND, command );
                request.getSession().setAttribute( CommonConst.SES_DOWNLOAD_URL_SESSION, "wepr01030802.pdf" );
           }
           else if( "onPressButtonDownloadGuideRiskProfil".equals( theEvent ) )
           {                
                cepr01030101Form.setDownloadFlag( "newPage" );
                request.getSession().setAttribute( CommonConst.SES_COMMAND, command );
                request.getSession().setAttribute( CommonConst.SES_DOWNLOAD_URL_SESSION, "wepr01030803.pdf" );
           }
        }       
       
    }
    
	public static List<DropDown> listFilesInDirectory(String dir) {
		File destDir = new File(dir);
		List<DropDown> daftar = new ArrayList<DropDown>();
		if(destDir.exists()) {
			String[] children = destDir.list();
			daftar = new ArrayList<DropDown>();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			for(int i=0; i<children.length; i++) {
				File file = new File(destDir+"/"+children[i]);
				daftar.add(new DropDown(children[i], df.format(new Date(file.lastModified())), dir));
			}
		}
		return daftar;
	}	
	
	
	 public List<Map<String, Object>> getPageList( int currentIdx ) throws IOException
	    {
	        logger.info( "*-*-*-* Cepr01040201Business.getPageList" );
	        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
	        Map<String, Object> params;

	        params = new HashMap<String, Object>();
	        params.put( "pageCode", "sub1" );
	        result.add( params );

	        params = new HashMap<String, Object>();
	        params.put( "pageCode", "sub2a" );
	        result.add( params );
	        
	        params = new HashMap<String, Object>();
	        params.put( "pageCode", "sub2c" );
	        result.add( params );
	        
	        params = new HashMap<String, Object>();
	        params.put( "pageCode", "sub2b" );
	        result.add( params );
	               
	        params = new HashMap<String, Object>();
	        params.put( "pageCode", "sub2d" );
	        result.add( params );        
	        // if no riders, don't show this page ( page 3 )
	        if( currentIdx != 0 )
	        {
	            params = new HashMap<String, Object>();
	            params.put( "pageCode", "sub3" );
	            result.add( params );
	            
	          
	        }
	        
	        if( currentIdx != 0 )
	        {                   
	            
	            params = new HashMap<String, Object>();
	        	params.put( "pageCode", "sub4" );
	        	result.add( params );
	        }
	       
	        
	    	params = new HashMap<String, Object>();
	    	params.put( "pageCode", "sub6" );
	    	result.add( params );

	        return result;
	    }
	    
}