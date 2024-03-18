package id.co.sinarmaslife.eproposal.product.product01040220;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040201
 * Program Name   		: Cepr01040201DownloadController
 * Description         	: Eka Link Family (159)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 22, 2007 9:53:56 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import com.lowagie.text.pdf.PdfWriter;
import id.co.sinarmaslife.eproposal.business.Cepr00000000RiderBusiness;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030103Form;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.JrUtil;
import id.co.sinarmaslife.standard.util.MappingUtil;
import id.co.sinarmaslife.standard.util.StringUtil;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.jasperreports.AbstractJasperReportsView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040220DownloadController extends StandardParent implements Controller
{
    static Integer currentIdx;
    protected final Log logger = LogFactory.getLog( getClass() );

    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040201DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        
        Cepr01040220Business cepr01040220Business = new Cepr01040220Business( eproposalManager, editorUtil, command );

        ModelAndView result = null;
        if( request.getSession().getAttribute( "messageEkaSehatList" ) == null )
        {
        	List<String> messageEkaSehatList = cepr01040220Business.getEkaSehatMsgBox();
        	request.getSession().setAttribute( "messageEkaSehatList", messageEkaSehatList );
        	result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }

        if( request.getSession().getAttribute( "messageBoxList" ) == null )
        {
            List<String> messageBoxList = cepr01040220Business.getMedicalMsgBox();
            request.getSession().setAttribute( "messageBoxList", messageBoxList );
            result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        else
        {
        	
        	 Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
             map = controller(cepr01030000HoldingForm);
             
             String kelayakan = (StringUtil.isEmpty(map.get("proposalLayak")))?null:(String)map.get("proposalLayak");
           	if (kelayakan != null && kelayakan.equals("Maaf, Proposal yang Anda buat Tidak Layak Jual.")){
                       result = new ModelAndView( "CEPR00000000DownloadDocumentJSP2" );
           	}else{  
             
//             jasperViewer = (AbstractJasperReportsView) map.get("jasperViewer");
             params = (Map<String, Object>) map.get("params");
             String dirName = (StringUtil.isEmpty(map.get("dirName")))?null:(String)map.get("dirName");
             String fileName = (StringUtil.isEmpty(map.get("fileName")))?null:(String)map.get("fileName");
             JasperUtils.downloadReportPDF(response, dirName, fileName);   
//             jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
             request.getSession().setAttribute( "messageBoxList", map.get("messageBoxList") );
             request.getSession().setAttribute( "messageEkaSehatList", map.get("messageEkaSehatList") );
//             result = new ModelAndView( jasperViewer, params );
             result = null;  
        }
        }
        
        return result;        
    }	
        	
        	
        	
    public HashMap<String, Object> controller(  Cepr01030000HoldingForm cepr01030000HoldingForm )
            throws ServletException, IOException
    {  	
    	 HashMap<String, Object> result = new HashMap<String, Object>();	
    	 Cepr01040220Business cepr01040220Business = new Cepr01040220Business( eproposalManager, editorUtil, cepr01030000HoldingForm );
    	 Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
         Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
         Cepr01030103Form cepr01030103Form = cepr01030000HoldingForm.getCepr01030103Form();
         
         CredentialsVO credentialsVO = cepr01030000HoldingForm.getCredentialsVO();
         String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
         //   AbstractJasperReportsView jasperViewer = new JasperReportsPdfView();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put( "format", "pdf" );

            Map<JRExporterParameter, Object> exporterParam = new HashMap<JRExporterParameter, Object>();
            exporterParam = JasperUtils.exporterParam(exporterParam);

         //   jasperViewer.setExporterParameters( exporterParam );
        //    jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040220_product.jasper" );

            
            String showMpp55Thn = "notshow";
            if( credentialsVO != null && credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_MNC )) {
            	
            }else{
            	if( cepr01030102Form.getPaymentModeCd().equals( Hardcode.PAY_MODE_CD_SEKALIGUS ) ){
            		showMpp55Thn = "notshow";
            	}else{
            		showMpp55Thn = "show";
            	}
            }
            if( cepr01030101Form.getInsuredAge() + cepr01030102Form.getPremiumFurloughYear() >= 55 )
            {
            	showMpp55Thn = "notshow";
            }
            
            String titlePackage = "";
            if( cepr01030102Form.getSmileLadiesPackageCd() != null ){
            	if( cepr01030102Form.getSmileLadiesPackageCd().intValue() == 2 ) titlePackage = "(SMiLe Ladies Diamond)";
            	else if( cepr01030102Form.getSmileLadiesPackageCd().intValue() == 3 ) titlePackage = "(SMiLe Ladies Ruby)";
            	else if( cepr01030102Form.getSmileLadiesPackageCd().intValue() == 4 ) titlePackage = "(SMiLe Ladies Pearl)";
            	else if( cepr01030102Form.getSmileLadiesPackageCd().intValue() == 5 ) titlePackage = "(SMiLe Ladies Fantastic)";
            	else if( cepr01030102Form.getSmileLadiesPackageCd().intValue() == 6 ) titlePackage = "(SMiLe Ladies Excellent)";
            }
            
            if( cepr01030102Form.getEducationPackageCd() != null ){
            	if( cepr01030102Form.getEducationPackageCd().intValue() == 2 ) titlePackage = "(PAKET A)";
            	else if( cepr01030102Form.getEducationPackageCd().intValue() == 3 ) titlePackage = "(PAKET B)";
            	else if( cepr01030102Form.getEducationPackageCd().intValue() == 4 ) titlePackage = "(PAKET C)";
            }
            
            if(cepr01030102Form.getJenisCd() != null){
               	if(cepr01030102Form.getJenisCd().intValue() == 27) titlePackage = "(Bronze)";
               	else if(cepr01030102Form.getJenisCd().intValue() == 28) titlePackage = "(Classic)";
             	else if(cepr01030102Form.getJenisCd().intValue() == 29) titlePackage = "(Silver)";
             	else if(cepr01030102Form.getJenisCd().intValue() == 30) titlePackage = "(Gold)";	
            }
            
            params.put( "showMpp55Thn", showMpp55Thn );
            params.put( "titlePackage", titlePackage );
            if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040220Mapper.X1 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040220Mapper.X2){
            	params.put( "title", "SMiLe LINK BRIDGE SYARIAH");
            }
            else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040220Mapper.X3 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040220Mapper.X4 ){
            	params.put( "title", "SMiLe LINK 99 SYARIAH");            	
            }
            else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040220Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040220Mapper.X6 ){
            	params.put( "title", "SUPERLINK SYARIAH");            	
            }
            params.put( "currencySymbol", currencySymbol );
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            params.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
            
        //    jasperViewer.setReportDataKey( "dataSource" );
        //    jasperViewer.setJdbcDataSource( eproposalManager.getDataSource() );

            if( "show".equals( showMpp55Thn ) )
            {
            	IllustrationResultVO illustrationResultVO2 = cepr01040220Business.getIllustrationResult2(); // perhitungan polis
	            params.put( "validityMsg2", illustrationResultVO2.getValidityMsg() );
	            params.put( "dsEkalinkIllustration2", JasperReportsUtils.convertReportData( illustrationResultVO2.getIllustrationList() ) );
	            params.put( "dsInvestmentYield2", JasperReportsUtils.convertReportData( cepr01040220Business.getInvestmentYield().getYieldList() ) );
	            params.put( "dsNoteSekaligus2", JasperReportsUtils.convertReportData( cepr01040220Business.getNoteSekaligusRowList() ) );
	            params.put( "dsNoteBerkala2", JasperReportsUtils.convertReportData( cepr01040220Business.getNoteBerkalaRowList() ) );
	            params.put( "dsCommonHeaderSmall2", JasperReportsUtils.convertReportData( cepr01040220Business.getCommonHeaderSmallRowSyariahList_Surrender() ) );
	            params.put( "dsInvestmentChoiceSmall2", JasperReportsUtils.convertReportData( cepr01040220Business.getInvestmentChoiceSmallRowList() ) );
	            params.put( "dsEkalinkIllustration4", JasperReportsUtils.convertReportData( illustrationResultVO2.getIllustrationList() ) );
	            params.put( "dsInvestmentYield4", JasperReportsUtils.convertReportData( cepr01040220Business.getInvestmentYield().getYieldList() ) );
	            params.put( "dsNoteSekaligus4", JasperReportsUtils.convertReportData( cepr01040220Business.getNoteSekaligusRowList() ) );
	            params.put( "dsNoteBerkala4", JasperReportsUtils.convertReportData( cepr01040220Business.getNoteBerkalaRowList() ) );
	            params.put( "dsCommonHeaderSmall4", JasperReportsUtils.convertReportData( cepr01040220Business.getCommonHeaderSmallRowSyariahList_Surrender() ) );
	            params.put( "dsInvestmentChoiceSmall4", JasperReportsUtils.convertReportData( cepr01040220Business.getInvestmentChoiceSmallRowList() ) );
	            params.put( "titleIllustration", "ILUSTRASI PERKEMBANGAN DANA APABILA KONTRIBUSI DIBAYARKAN SAMPAI DENGAN USIA 55 TAHUN" );
            }
            
            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.syariah" ) );
            params.put( "logoBismilah", eproposalManager.getProps().getProperty( "logo.bismilah" ) );
            IllustrationResultVO illustrationResultVO = cepr01040220Business.getIllustrationResult(); // perhitungan polis
            params.put( "validityMsg", illustrationResultVO.getValidityMsg() );
            params.put( "dsEkalinkIllustration", JasperReportsUtils.convertReportData( illustrationResultVO.getIllustrationList() ) );
            params.put( "dsEkalinkIllustration3", JasperReportsUtils.convertReportData( illustrationResultVO.getIllustrationList() ) );

            params.put( "totalInvestmentAllocation", cepr01040220Business.getInvestmentYield().getTotalInvestmentAllocation() );
            params.put( "totalAssumptionLow", cepr01040220Business.getInvestmentYield().getTotalAssumptionLow() );
            params.put( "totalAssumptionMid", cepr01040220Business.getInvestmentYield().getTotalAssumptionMid() );
            params.put( "totalAssumptionHi", cepr01040220Business.getInvestmentYield().getTotalAssumptionHi() );

            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( cepr01040220Business.getCommonHeaderRowList() ) );
            params.put( "dsCommonHeaderSmall", JasperReportsUtils.convertReportData( cepr01040220Business.getCommonHeaderSmallRowSyariahList() ) );
            params.put( "dsCommonHeaderSmall3", JasperReportsUtils.convertReportData( cepr01040220Business.getCommonHeaderSmallRowSyariahList() ) );
            params.put( "dsInvestmentChoice", JasperReportsUtils.convertReportData( cepr01040220Business.getInvestmentChoiceRowList() ) );
            params.put( "dsInvestmentChoiceSmall", JasperReportsUtils.convertReportData( cepr01040220Business.getInvestmentChoiceSmallRowList() ) );
            params.put( "dsInvestmentChoiceSmall3", JasperReportsUtils.convertReportData( cepr01040220Business.getInvestmentChoiceSmallRowList() ) );
            params.put( "dsMonthlyPremium", JasperReportsUtils.convertReportData( cepr01040220Business.getMonthlyPremiumList( cepr01030102Form ) ) );
            params.put( "dsInvestmentPerformance", JasperReportsUtils.convertReportData( cepr01040220Business.getInvestmentPerformanceList() ) );
            params.put( "dsInvestmentYield", JasperReportsUtils.convertReportData( cepr01040220Business.getInvestmentYield().getYieldList() ) );
            params.put( "dsInvestmentYield3", JasperReportsUtils.convertReportData( cepr01040220Business.getInvestmentYield().getYieldList() ) );
            params.put( "dsOneRow", JasperReportsUtils.convertReportData( cepr01040220Business.getOneRowList() ) );
            params.put( "dsNoteSekaligus", JasperReportsUtils.convertReportData( cepr01040220Business.getNoteSekaligusRowList() ) );
            params.put( "dsNoteBerkala", JasperReportsUtils.convertReportData( cepr01040220Business.getNoteBerkalaRowList() ) );
            params.put( "dsNoteSekaligus3", JasperReportsUtils.convertReportData( cepr01040220Business.getNoteSekaligusRowList() ) );
            params.put( "dsNoteBerkala3", JasperReportsUtils.convertReportData( cepr01040220Business.getNoteBerkalaRowList() ) );
            
            String label_special_offer = "";
            String note_special_offer = "";
            String mnc = "";
            if( cepr01030102Form.getSpecialOfferCd() != null ){
	            if(cepr01030102Form.getSpecialOfferCd().equals( Hardcode.DISCOUNT_PREMIUM ) ){
	            	label_special_offer = "Discount Premium";
	            	note_special_offer = "**  Special Offer berupa discount premium akan dikembalikan ke nasabah.";
	            }else if(cepr01030102Form.getSpecialOfferCd().equals( Hardcode.ADDITIONAL_UNIT ) ){
	            	label_special_offer = "Additional Unit";
	            	note_special_offer = "**  Special Offer berupa additional unit (penambahan unit fund) yang  otomatis akan ditambahkan ke nilai polis sebagai investment fund.";
	            }
            }
            String groupId = credentialsVO.getGroupId();
            String note_lucky_draw = null;
          
            params.put( "groupId", groupId );
            params.put( "note_lucky_draw", note_lucky_draw );
            if( credentialsVO != null && credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_MNC ) && 
            		cepr01030102Form.getRightPartOfBusinessCd()== Cepr01040220Mapper.X2 ){
            	//REQUEST 02/09/2013@Vito Shadiq : Special Offer= NONE(0) digunakan utk mendisable Special Offer pd Distribusi MNC 
            	if(cepr01030102Form.getSpecialOfferCd() != null && !cepr01030102Form.getSpecialOfferCd().equals( 0 )){
            	mnc = "mnc";
            	if( cepr01030102Form.getSpecialOfferCd() != null && cepr01030102Form.getSpecialOfferCd().equals( Hardcode.SPECIAL_OFFER_LUCKY_DRAW ) )
            	{
            		mnc = "";
            		note_lucky_draw = "Proposal ini mengikuti program Lucky Draw";
            	}
            	}else{
                	mnc = "";
                }            	
            }else{
            	mnc = "";
            }
            params.put( "mnc", mnc );
            params.put( "note_lucky_draw", note_lucky_draw );
            params.put( "groupId", credentialsVO.getGroupId() );
            params.put( "label_special_offer", label_special_offer );
            params.put( "note_special_offer", note_special_offer );
            Integer titlePayor5Tpd10CiDeath = cepr01030102Form.getPremiumFurloughYear();
            params.put( "titlePayor5Tpd10CiDeath", titlePayor5Tpd10CiDeath.toString() );
            String rider5Tpd10CiTitle = cepr01030102Form.getPremiumFurloughYear().toString();
            params.put( "riderWaiver5Tpd10CiTitle", rider5Tpd10CiTitle );
            currentIdx = 0;
            Cepr00000000RiderBusiness riderBusiness = new Cepr00000000RiderBusiness( eproposalManager, editorUtil, cepr01030000HoldingForm );
            putRiderParam( params, riderBusiness.getRiderPaSyariahList(), "dsRiderPa", "riderPaIndex" );
            params.put( "riderPaRisk", riderBusiness.getRiderPaRisk() );
            params.put( "dsRiderPaClone", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderPaSyariahList() ) );
            params.put( "riderLadiesInsTitle", MappingUtil.getLabel(cepr01030103Form.getLadiesInsList(), cepr01030103Form.getLadiesInsCd()));
            putRiderParam( params, riderBusiness.getRiderHcpSyariahList(), "dsRiderHcp", "riderHcpIndex" );
            putRiderParam( params, riderBusiness.getRiderHcpFamilySyariahList(), "dsRiderHcpFamily", "riderHcpFamilyIndex" );
            putRiderParam( params, riderBusiness.getRiderHcpLadiesSyariahList(), "dsRiderHcpLadies", "riderHcpLadiesIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorTpdCiDeathSyariahList(), "dsRiderPayorTpdCiDeath", "riderPayorTpdCiDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderPayor5Tpd10CiDeathSyariahList(), "dsRiderPayor5Tpd10CiDeath", "riderPayor5Tpd10CiDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderWaiver5Tpd10CiSyariahList(), "dsRiderWaiver5Tpd10Ci", "riderWaiver5Tpd10CiIndex" );
            putRiderParam( params, riderBusiness.getRiderTpdSyariahList(), "dsRiderTpd", "riderTpdIndex" );
            putRiderParam( params, riderBusiness.getRiderTermRiderList(), "dsRiderTermRider", "riderTermRiderIndex" );
            putRiderParam( params, riderBusiness.getRiderLadiesInsSyariahList(), "dsRiderLadiesIns", "riderLadiesInsIndex" );
            putRiderParam( params, riderBusiness.getRiderWaiverTpdCiSyariahList(), "dsRiderWaiverTpdCi", "riderWaiverTpdCiIndex" );
            putRiderParam( params, riderBusiness.getRiderEkaSehatSyariahListMap(), "dsRiderEkaSehat", "riderEkaSehatIndex");
            putRiderParam( params, riderBusiness.getRiderHcpProviderSyariahList(), "dsRiderHcpProvider", "riderHcpProviderIndex" );
            putRiderParam( params, riderBusiness.getRiderCiSyariahList(), "dsRiderCi", "riderCiIndex" );
            putRiderParam( params, riderBusiness.getRiderEkaSehatInnerLimitSyariahListMap(), "dsRiderEkaSehatInnerLimit", "riderEkaSehatInnerLimitIndex");
            putRiderParam( params, riderBusiness.getRiderLadiesMedExpenseSyariahListMap(), "dsRiderLadiesMedExpense", "riderLadiesMedExpenseIndex");
            putRiderParam( params, riderBusiness.getRiderLadiesMedExpenseInnerSyariahLimitListMap(), "dsRiderLadiesMedExpenseInnerLimit", "riderLadiesMedExpenseInnerLimitIndex");
                      
            
            putRiderParam( params, riderBusiness.getRiderPayorTpdDeathSyariahList(), "dsRiderPayorTpdDeath", "riderPayorTpdDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorCiDeathSyariahList(), "dsRiderPayorCiDeath", "riderPayorCiDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorCiSyariahList(), "dsRiderPayorCi", "riderPayorCiIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorSpouseTpdDeathSyariahList(), "dsRiderPayorSpouseTpdDeath", "riderPayorSpouseTpdDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderWaiverTpdSyariahList(), "dsRiderWaiverTpd", "riderWaiverTpdIndex" );
            putRiderParam( params, riderBusiness.getRiderWaiverCiSyariahList(), "dsRiderWaiverCi", "riderWaiverCiIndex" );
            putRiderParam( params, riderBusiness.getRiderScholarSyariahRiderList(), "dsRiderScholarRider", "riderScholarRiderIndex" );
            putRiderParam( params, riderBusiness.getRiderEarlyStageCi99SyariahList(), "dsRiderEarlyStageCi99", "riderEarlyStageCi99Index" );
            putRiderParam( params, riderBusiness.getRiderMedicalPlusList(), "dsRiderMedicalPlus", "riderMedicalPlusIndex" );
            putRiderParam( params, riderBusiness.getRiderMedicalPlusRjList(), "dsRiderMedicalPlusRj", "riderMedicalPlusRjIndex" );
            putRiderParam( params, riderBusiness.getRiderMedicalPlusRgList(), "dsRiderMedicalPlusRg", "riderMedicalPlusRgIndex" );
            putRiderParam( params, riderBusiness.getRiderMedicalPlusRbList(), "dsRiderMedicalPlusRb", "riderMedicalPlusRbIndex" );
            putRiderParam( params, riderBusiness.getRiderMedicalPlusPkList(), "dsRiderMedicalPlusPk", "riderMedicalPlusPkIndex" );
            putRiderParam( params, riderBusiness.getRiderEkaSehatExtraListMap(), "dsRiderEkaSehatExtra", "riderEkaSehatExtraIndex");
            
            /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
            String usiaMaxTerm = MappingUtil.getLabel(cepr01030103Form.getTerm5575RiderChooseList(), cepr01030103Form.getTerm5575RiderChooseCd());
            params.put( "usiaMaxTerm", usiaMaxTerm+" " );
            putRiderParam( params, riderBusiness.getRiderTerm5575RiderList(), "dsRiderTerm5575Rider", "riderTerm5575RiderIndex" ); 
                        
            params.put( "dsChartUpSekaligus", JasperReportsUtils.convertReportData( riderBusiness.getChartScholarUpSekaligusTahunanList() ) );
            params.put( "dsUpSekaligusTahunan", JasperReportsUtils.convertReportData( riderBusiness.getScholarUpSekaligusTahunanList() ) );
            params.put( "usia_scholar", cepr01030103Form.getScholarshipChooseCd() );
            params.put( "dsRiderLadiesInsSummary", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesInsSyariahSummaryList() ) );
            params.put( "participantLadiesMedExpense", riderBusiness.checkParticipantLadiesMedExpense() );
            params.put( "participantEkaSehatInnerLimit", riderBusiness.checkParticapantEkaSehatInnerLimit() );
            params.put( "dsRiderEkaSehatInnerLimitParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderEkaSehatInnerLimitParticipantsList() ) );
            params.put( "dsRiderHcpFamilyParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderHcpFamilyParticipantsList() ) );
            params.put( "dsRiderHcpLadiesParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderHcpLadiesParticipantsList() ) );
            params.put( "baseBenefitHcpFam", riderBusiness.computeBaseBenefitHcpFam() );
            params.put( "baseBenefitHcpLad", riderBusiness.computeBaseBenefitHcpLadies() );
            params.put( "dsRiderHcpProviderParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderHcpProviderParticipantsList() ) );
            params.put( "baseBenefitHcpPro", riderBusiness.computeBaseBenefitHcpPro() );
            params.put( "participantEkaSehat", riderBusiness.checkParticapantEkaSehat() );
            params.put( "dsRiderEkaSehatParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderEkaSehatParticipantsList() ) );
            params.put( "dsRiderLadiesMedExpenseParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesMedExpenseParticipantsList()) );
            params.put( "dsRiderLadiesMedExpenseInnerLimitParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesMedExpenseInnerLimitParticipantsList()) );
            params.put( "dsRiderMedicalPlusParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderMedicalPlusParticipantsList()) );
            params.put( "participantMedicalPlus", riderBusiness.checkParticapantMedicalPlus() );
            params.put("jenis", cepr01030102Form.getRightPartOfBusinessCd()+"");
            String mappingTitleWaiverTpdCi = MappingUtil.getLabel(cepr01030103Form.getWaiverTpdCiChooseList(), cepr01030103Form.getWaiverTpdCiChooseCd());
            params.put( "riderWaiverTpdCiTitle", mappingTitleWaiverTpdCi );
            params.put( "dataSource", cepr01040220Business.getPageList( currentIdx ) );
              
            params.put( "dsCommonHeaderRincian", JasperReportsUtils.convertReportData( cepr01040220Business.getCommonHeaderRincianRowList()) );
            double ldec_coi = cepr01040220Business.of_get_coi_basic_190( 1 );
            BigDecimal biayaAsuransiPokok =  new BigDecimal(ldec_coi);
            params.put( "dsCommonBiayaRincian", JasperReportsUtils.convertReportData( cepr01040220Business.getCommonBiayaRincianRowList( biayaAsuransiPokok ) ) );
            
            params.put("pageRider", cepr01040220Business.getPageRider( currentIdx ));
            params.put( "dsCommonHeaderRincianRiderPa", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(1) ) );
            params.put( "dsCommonBiayaRincianRiderPa", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonBiayaRincianRider(1) ) );         
            params.put( "dsCommonHeaderRincianRiderTpd", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(3) ) );
            params.put( "dsCommonBiayaRincianRiderTpd", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonBiayaRincianRider(3) ) ); 
            params.put( "dsCommonHeaderRincianRiderCi", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(4) ) );
            params.put( "dsCommonBiayaRincianRiderCi", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonBiayaRincianRider(4) ) ); 
            params.put( "dsCommonHeaderRincianRiderHCPFamily", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(11) ) );
            params.put( "dsCommonBiayaRincianRiderHCPFamily", JasperReportsUtils.convertReportData( cepr01040220Business.getCommonBiayaRincianRider(11) ) );     
            params.put( "dsRincianRiderHCPFamily", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpFamilySyariahList() ));
            params.put( "dsRincianRiderHcpFamilyParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderHcpFamilyParticipantsList() ) );
            
            params.put( "dsCommonHeaderRincianRiderHCP", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(2) ) );
            params.put( "dsCommonBiayaRincianRiderHCP", JasperReportsUtils.convertReportData( cepr01040220Business.getCommonBiayaRincianRider(2) ) );
            params.put( "dsRincianRiderHCP", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpSyariahList() ));  
            params.put( "dsCommonHeaderRincianRiderHCPProvider", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(16) ) );
            params.put( "dsCommonBiayaRincianRiderHCPProvider", JasperReportsUtils.convertReportData( cepr01040220Business.getCommonBiayaRincianRider(16) ) );
            params.put( "dsRincianRiderHCPProvider", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpProviderSyariahList()));    
                        
            params.put( "dsCommonHeaderRincianRiderTerm", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(12) ) );
            params.put( "dsCommonBiayaRincianRiderTerm", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonBiayaRincianRider(12) ) );  
            params.put( "dsCommonHeaderRincianRiderEkaSehat", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(13) ) );
            params.put( "dsCommonBiayaRincianRiderEkaSehat", JasperReportsUtils.convertReportData( cepr01040220Business.getCommonBiayaRincianRider(13) ) );     
            params.put( "dsRincianRiderEkaSehat", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRincianRiderEkaSehat() ));
            params.put( "dsRincianRiderEkaSehatParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRincianRiderEkaSehatParticipantsList() ) );
            params.put( "dsCommonHeaderRincianRiderEkaSehatInnerLimit", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(15) ) );
            params.put( "dsCommonBiayaRincianRiderEkaSehatInnerLimit", JasperReportsUtils.convertReportData( cepr01040220Business.getCommonBiayaRincianRider(15) ) );     
            params.put( "dsRincianRiderEkaSehatInnerLimit", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRincianRiderEkaSehatInnerLimit() ));
            params.put( "dsRincianRiderEkaSehatInnerLimitParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRincianRiderEkaSehatInnerLimitParticipantsList() ) );
            params.put( "dsCommonHeaderRincianRiderWaiverTpdCi", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(17) ) );
            params.put( "dsCommonBiayaRincianRiderWaiverTpdCi", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonBiayaRincianRider(17) ) );
            params.put( "dsCommonHeaderRincianRiderPayorTpdCiDeath", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(18) ) );
            params.put( "dsCommonBiayaRincianRiderPayorTpdCiDeath", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonBiayaRincianRider(18) ) );
            
            params.put( "dsCommonHeaderRincianRiderLadiesIns", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(19) ) );            
            params.put( "dsCommonBiayaRincianRiderLadiesIns", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonBiayaRincianRider(19) ) );             
            params.put( "dsCommonHeaderRincianRiderHCPLadies", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(20) ) );
            params.put( "dsCommonBiayaRincianRiderHCPLadies", JasperReportsUtils.convertReportData( cepr01040220Business.getCommonBiayaRincianRider(20) ) );          
            params.put( "dsRincianRiderHcpLadies", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpLadiesList()));            
            params.put( "dsCommonHeaderRincianRiderLadiesMedExpense", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(21) ) );
            params.put( "dsCommonBiayaRincianRiderLadiesMedExpense", JasperReportsUtils.convertReportData( cepr01040220Business.getCommonBiayaRincianRider(21) ) );     
            params.put( "dsRincianRiderLadiesMedExpense", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRincianRiderLadiesMedExpenseSyariah() ));
            params.put( "dsRincianRiderLadiesMedExpenseParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesMedExpenseParticipantsList() ) );            
            params.put( "dsCommonHeaderRincianRiderLadiesMedExpenseInnerLimit", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(22) ) );
            params.put( "dsCommonBiayaRincianRiderLadiesMedExpenseInnerLimit", JasperReportsUtils.convertReportData( cepr01040220Business.getCommonBiayaRincianRider( 22) ) );     
            params.put( "dsRincianRiderLadiesMedExpenseInnerLimit", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRincianRiderLadiesMedExpenseInnerLimitSyariah() ));
            params.put( "dsRincianRiderLadiesMedExpenseInnerLimitParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesMedExpenseInnerLimitParticipantsList() ) );
                        
            params.put( "dsCommonHeaderRincianRiderPayor5Tpd10CiDeath", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(23) ) );
            params.put( "dsCommonBiayaRincianRiderPayor5Tpd10CiDeath", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonBiayaRincianRider(23) ) );
            params.put( "dsCommonHeaderRincianRiderWaiver5Tpd10Ci", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(24) ) );
            params.put( "dsCommonBiayaRincianRiderWaiver5Tpd10Ci", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonBiayaRincianRider(24) ) );
            params.put( "dsCommonHeaderRincianRiderScholar", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(25) ) );
            params.put( "dsCommonBiayaRincianRiderScholar", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonBiayaRincianRider(25) ) ); 
            params.put( "dsCommonHeaderRincianRiderEarlyStageCi99", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(27) ) );
            params.put( "dsCommonBiayaRincianRiderEarlyStageCi99", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonBiayaRincianRider(27) ) );
            params.put( "dsCommonHeaderRincianRiderMedicalPlus", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(28) ) );
            params.put( "dsCommonBiayaRincianRiderMedicalPlus", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonBiayaRincianRider(28) ) );  
            params.put( "dsRincianRiderMedicalPlus", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderMedicalPlusList() ));
            params.put( "dsRincianRiderMedicalPlusRj", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderMedicalPlusRjList() ));
            params.put( "dsRincianRiderMedicalPlusRg", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderMedicalPlusRgList() ));
            params.put( "dsRincianRiderMedicalPlusRb", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderMedicalPlusRbList() ));
            params.put( "dsRincianRiderMedicalPlusPk", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderMedicalPlusPkList() ));                     
            params.put( "dsRincianRiderMedicalPlusParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRincianRiderMedicalPlusParticipantsList()) );
            
            params.put( "dsCommonHeaderRincianRiderEkaSehatExtra", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(29) ) );
            params.put( "dsCommonBiayaRincianRiderEkaSehatExtra", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonBiayaRincianRider(29) ) );     
            params.put( "dsRincianRiderEkaSehatExtra", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRincianRiderEkaSehatExtra() ));
            params.put( "participantEkaSehatExtra", riderBusiness.checkParticapantEkaSehatExtra() );
            params.put( "dsRiderEkaSehatExtraParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderEkaSehatExtraParticipantsList() ) );
            params.put( "dsRincianRiderEkaSehatExtraParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRincianRiderEkaSehatExtraParticipantsList() ) ); 
            params.put( "tipeEkaSehat", cepr01040220Business.checkTipeEkaSehat(29) );
            
            /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
            params.put( "dsCommonHeaderRincianRiderTerm5575", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonHeaderRincianSmallRowList(30) ) );
            params.put( "dsCommonBiayaRincianRiderTerm5575", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040220Business.getCommonBiayaRincianRider(30) ) );
            
            // Save E-Proposal PDF ke EBServer dan PopUp Download
            // ** Adrian@28/11/2013
            String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
            String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
            Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();
          //  String fileName="testx.pdf";
            String fileName = msag_id+"_"+no_proposal+".pdf";
            params.put( "no_proposal",  no_proposal );
            
            List temp = cepr01040220Business.getPageList( currentIdx );
            String rootDir_EProp="";
            if(flag_test==1){
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP_TEST;
            }else{
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP;
            }
            String dirName = rootDir_EProp+"\\"+msag_id+"\\"+no_proposal;   
            
            if(illustrationResultVO.getValidityMsg().equals("Maaf, Proposal yang Anda buat Tidak Layak Jual.")){
            	 result.put("proposalLayak",illustrationResultVO.getValidityMsg());
            }else{
            
            File sourceFile = new File( dirName + "\\" + fileName );
            try{      
	        	   //if(cekDataApprovedOrNot.get(0).getPosisi()<3){
			            JasperUtils.exportReportToPdf(
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040220_product.jasper",
			            		dirName,
			                fileName,
							params,
							temp,
							PdfWriter.AllowPrinting,
			                null,
			                null
			            );
			     //       JasperUtils.downloadReportPDF(response, dirName, fileName);        	   
		    	   //}
	    	   }catch( Exception e )
	          {
	    		   logger.error("ERROR", e);	
	          }   
            
         //   jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
                       
            //request.getSession().setAttribute( "messageBoxList", null );
            //request.getSession().setAttribute( "messageEkaSehatList", null );
            result.put("dirName", dirName);
            result.put("fileName", fileName);
            result.put("messageBoxList", null);
            result.put("messageEkaSehatList", null);
            result.put("params", params);
       //     result = new ModelAndView( jasperViewer, params );
          }

        return result;
    }

    public void putRiderParam( Map<String, Object> params, List contentList, String dsParamName, String idxParamName )
    {
        JRDataSource source = JrUtil.convertToDsAndSetNullIfEmpty( contentList );
        if( source != null ) currentIdx = currentIdx + 1;
        params.put( dsParamName, source );
        params.put( idxParamName, currentIdx.toString() + "." );
    }
}