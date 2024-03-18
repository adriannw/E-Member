package id.co.sinarmaslife.eproposal.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
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
import id.co.sinarmaslife.eproposal.business.Cepr01050000Business;
import id.co.sinarmaslife.eproposal.common.lookup.Cepr00000000ComboLookupBusiness;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.standard.propertyeditors.CurrencyFormatEditor;
import id.co.sinarmaslife.standard.util.LazyConverter;

public class Cepr01050000Controller extends AbstractWizardFormController implements Serializable
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
	 * Creation Date    	: Sep 3, 2012 5:27:00 PM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = 1897558814699849630L;

		public static final int CEPR05000000JSP = 0;
	    
	    protected EproposalManager eproposalManager;
	    protected transient CustomDateEditor dateEditor;
	    protected transient CustomNumberEditor integerEditor;
	    protected CurrencyFormatEditor currencyEditor;
	    protected DateFormat dateFormat;
	    protected Cepr00000000EditorUtil editorUtil;

	    protected Cepr00000000ComboLookupBusiness comboLookupBusiness;
	    protected Cepr00000000GeneralBusiness generalBusiness;
	    protected Cepr01050000Business cepr01050000Business;
	    
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
	        logger.info( "*-*-*-* Cepr01050000Controller.formBackingObject" );
	        this.pages = getPages();

	        setAllowDirtyBack( false );

	        Cepr01050000HoldingForm cepr01050000HoldingForm = new Cepr01050000HoldingForm();
	        //CredentialsVO credentialsVO = ( CredentialsVO ) request.getSession().getAttribute( StandardConst.CREDENTIALS_SES );
	        Cepr01050000Form cepr01050000Form = new Cepr01050000Form();
	        comboLookupBusiness = Cepr00000000ComboLookupBusiness.getInstance( eproposalManager, editorUtil );
	        generalBusiness = Cepr00000000GeneralBusiness.getInstance( eproposalManager, editorUtil );
	        
	        cepr01050000Business = new Cepr01050000Business( eproposalManager, editorUtil );
	        //cepr01030101Business = new Cepr01030101Business( eproposalManager, editorUtil );
	        //cepr01030102Business = new Cepr01030102Business( eproposalManager, editorUtil );
	        //cepr01030103Business = new Cepr01030103Business( eproposalManager, editorUtil );

	        //cepr01030101Business.resetForm( cepr01030101Form, generalBusiness, comboLookupBusiness, credentialsVO );
	        //cepr01030102Business.resetForm( cepr01030102Form );
	        //cepr01030103Business.resetForm( cepr01030103Form );

	        //cepr01030000HoldingForm.setCredentialsVO( credentialsVO );
	        cepr01050000HoldingForm.setCepr01050000Form( cepr01050000Form );

	        double premium = new Double( request.getParameter("a").toString() );
	        cepr01050000Form.setPremium(premium);
	        processFormAfterSubmitBeforeValidation(premium, cepr01050000Form);

	        return cepr01050000HoldingForm;
	    }

	    @Override
	    protected void initBinder( HttpServletRequest request, ServletRequestDataBinder binder ) throws Exception
	    {
	        logger.info( "*-*-*-* Cepr01050000Controller.initBinder" );
	        binder.registerCustomEditor( Date.class, null, dateEditor );
	        binder.registerCustomEditor( Integer.class, null, integerEditor );
	        binder.registerCustomEditor( BigDecimal.class, null, currencyEditor );
	    }

	    @Override
	    protected void onBind( HttpServletRequest request, Object command )
	    {
	        logger.info( "*-*-*-* Cepr01050000Controller.onBind" );
	        String theEvent = request.getParameter( "theEvent" );

	        int currentPage = getCurrentPage( request );
	        
	    }

	    @Override
	    protected boolean suppressValidation( HttpServletRequest request, Object command, BindException errors )
	    {
	        logger.info( "*-*-*-* Cepr01050000Controller.suppressValidation" );

	        String theEvent = request.getParameter( "theEvent" );
	        logger.info( "*-*-*-* theEvent = " + theEvent );

	        boolean result = false;
	        int currentPage = getCurrentPage( request );
	        int targetPage = getTargetPage( request, currentPage );
	        logger.info( "*-*-*-* currentPage = " + currentPage );
	        logger.info( "*-*-*-* targetPage = " + targetPage );

	        if( targetPage == CEPR05000000JSP )
	        {
	            result = true;
	        }
	        
	        logger.info( "*-*-*-* result suppressValidation = " + result );
	        return result;
	    }

	    @Override
	    protected void validatePage( Object command, Errors errors, int page )
	    {
	        logger.info( "*-*-*-* Cepr01050000Controller.validatePage" );
	        logger.info( "*-*-*-* page = " + page );

	        if( page == CEPR05000000JSP )
	        {
	            logger.info( "*-*-*-* validateCepr04000000" );
	            //Cepr01030101Validator cepr01030101Validator = new Cepr01030101Validator( eproposalManager, editorUtil, command, errors );
	            //cepr01030101Validator.validateCommon();
	        }
	        
	        
	        logger.info( "*-*-*-* errors.getErrorCount() = " + errors.getErrorCount() );
	    }

	    @Override
	    protected Map referenceData( HttpServletRequest request, Object command, Errors errors, int page ) throws Exception
	    {
	    	
	        logger.info( "*-*-*-* Cepr01050000Controller.referenceData" );
	        logger.info( "*-*-*-* page = " + page );
	        String theEvent = request.getParameter( "theEvent" );
	        
	        Map<String, Object> map = new HashMap<String, Object>();
	        
	        Cepr01050000HoldingForm cepr01050000HoldingForm = ( Cepr01050000HoldingForm ) command;
	        Cepr01050000Form cepr01050000Form = cepr01050000HoldingForm.getCepr01050000Form();
	        String e = request.getParameter("e");
	        String kombinasi = request.getParameter("f");
	        BigDecimal kombinasiPremiPokok = new BigDecimal(kombinasi.substring(0, kombinasi.indexOf("-")));
	        if( page == CEPR05000000JSP )
	        {
	        	Integer insuredAge = LazyConverter.toInt(e);
	        	double fixIncome = new Double( request.getParameter("b") );
	        	double dynamic = new Double( request.getParameter("c") );
	        	double aggresive = new Double( request.getParameter("d") );
		        cepr01050000Business.getIllustration191Result( cepr01050000Form.getTotalSumInsured(),cepr01050000Form, insuredAge, fixIncome, dynamic, aggresive, kombinasiPremiPokok );
		        cepr01050000Business.of_get_rate_ds(cepr01050000Form.getTotalSumInsured(),cepr01050000Form, insuredAge, fixIncome, dynamic, aggresive);
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
	        return new ModelAndView( new RedirectView( "wepr01050000.htm" ) );
	    }

	    protected void postProcessPage( HttpServletRequest request, Object command, Errors errors, int page ) throws Exception
	    {
	        logger.info( "*-*-*-* Cepr01050000Controller.postProcessPage" );
	        logger.info( "*-*-*-* page = " + page );

	        Cepr01050000HoldingForm cepr01050000HoldingForm = ( Cepr01050000HoldingForm ) command;
	        Cepr01050000Form cepr01050000Form = cepr01050000HoldingForm.getCepr01050000Form();

	        String theEvent = request.getParameter( "theEvent" );
	        logger.info( "*-*-*-* theEvent = " + theEvent );
	    }
	    
	    public void processFormAfterSubmitBeforeValidation( double premium, Cepr01050000Form cepr01050000Form )
	    {
	        double premiumCombinationPercent = 80.0;
	        refreshBaseTopupPremium(premium, premiumCombinationPercent, cepr01050000Form);

	            double idec_rate;
	            double adec_premi = premium;
	            double idec_up;
	            
	            adec_premi = adec_premi * 12;

	            // I got it from n_prod_159 function of_set_premi
	            idec_rate = 5000;
	            idec_up = adec_premi * idec_rate / 1000;

	            // count totalSumInsured with premiumCombinationPercent
	            idec_up = idec_up * premiumCombinationPercent / 100;
	            cepr01050000Form.setTotalSumInsured( new BigDecimal( idec_up ) );
	    }
	    
	    protected void refreshBaseTopupPremium(double premium, double combination, Cepr01050000Form cepr01050000Form)
	    {
	        cepr01050000Form.setBasePremium( new BigDecimal( premium * combination / 100 ) );
	        cepr01050000Form.setTopUpPremium( new BigDecimal( premium * ( 100 - combination  ) / 100 ) );
	    }
}
