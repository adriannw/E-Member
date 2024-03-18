package id.co.sinarmaslife.eproposal.product.product01040201;

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

import com.ibatis.common.resources.Resources;
import com.lowagie.text.pdf.PdfWriter;
import id.co.sinarmaslife.eproposal.business.Cepr00000000RiderBusiness;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
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
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Cepr01040201DownloadController extends StandardParent implements Controller
{
    static Integer currentIdx;
    protected final Log logger = LogFactory.getLog( getClass() );

    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040201DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Map<String, Object> params = new HashMap<String, Object>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        
        Cepr01040201Business cepr01040201Business = new Cepr01040201Business( eproposalManager, editorUtil, command );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        CredentialsVO credentialsVO = cepr01030000HoldingForm.getCredentialsVO();
      
       

        ModelAndView result;
        if( request.getSession().getAttribute( "messageEkaSehatList" ) == null )
        {
        	List<String> messageEkaSehatList = cepr01040201Business.getEkaSehatMsgBox();
        	request.getSession().setAttribute( "messageEkaSehatList", messageEkaSehatList );
        	result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }

        if( request.getSession().getAttribute( "messageBoxList" ) == null )
        {
            List<String> messageBoxList = cepr01040201Business.getMedicalMsgBox();
            request.getSession().setAttribute( "messageBoxList", messageBoxList );
            result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        else
        {
             map = controller(cepr01030000HoldingForm);
             String kelayakan = (StringUtil.isEmpty(map.get("proposalLayak")))?null:(String)map.get("proposalLayak");
        	if (kelayakan != null && kelayakan.equals("Maaf, Proposal yang Anda buat Tidak Layak Jual.")){
                    result = new ModelAndView( "CEPR00000000DownloadDocumentJSP2" );
 
        	}else{
        		  params = (Map<String, Object>) map.get("params");
                  String dirName = (StringUtil.isEmpty(map.get("dirName")))?null:(String)map.get("dirName");
                  String fileName = (StringUtil.isEmpty(map.get("fileName")))?null:(String)map.get("fileName");
                  JasperUtils.downloadReportPDF(response, dirName, fileName);   
                  request.getSession().setAttribute( "messageBoxList", map.get("messageBoxList") );
                  request.getSession().setAttribute( "messageEkaSehatList", map.get("messageEkaSehatList") );
                  result = null;  
        	}
        	
        	
        	
        }
        return result;        
    }	
        	
        	
        	
        	
        	
     public HashMap<String, Object> controller(  Cepr01030000HoldingForm cepr01030000HoldingForm )
        	            throws ServletException, IOException
       {      	
    	 HashMap<String, Object> result = new HashMap<String, Object>();
    	 Cepr01040201Business cepr01040201Business = new Cepr01040201Business( eproposalManager, editorUtil, cepr01030000HoldingForm );
    	 CredentialsVO credentialsVO = cepr01030000HoldingForm.getCredentialsVO();
         Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
         Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
         Cepr01030103Form cepr01030103Form = cepr01030000HoldingForm.getCepr01030103Form();
         String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
         
            AbstractJasperReportsView jasperViewer = new JasperReportsPdfView();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put( "format", "pdf" );

            Map<JRExporterParameter, Object> exporterParam = new HashMap<JRExporterParameter, Object>();
            exporterParam = JasperUtils.exporterParam(exporterParam);

            jasperViewer.setExporterParameters( exporterParam );
            jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040201_product.jasper" );
                                 
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
            
            params.put( "showMpp55Thn", showMpp55Thn );
            params.put( "titlePackage", titlePackage );
            //params.put( "title", "SMiLe LINK");
            Integer lsbsId = cepr01030102Form.getLeftPartOfBusinessCd();
            params.put( "title", eproposalManager.selectTitleBisnis(1, lsbsId));
            
            params.put( "currencySymbol", currencySymbol );
           // params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            params.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
                       
            jasperViewer.setReportDataKey( "dataSource" );
            params.put( "dataSource", JasperReportsUtils.convertReportData( new ArrayList() ) );
            jasperViewer.setJdbcDataSource( eproposalManager.getDataSource() );

            if( "show".equals( showMpp55Thn ) )
            {
            	IllustrationResultVO illustrationResultVO2 = cepr01040201Business.getIllustrationResult2(); // perhitungan polis
	            params.put( "validityMsg2", illustrationResultVO2.getValidityMsg() );
	            params.put( "dsEkalinkIllustration2", JasperReportsUtils.convertReportData( illustrationResultVO2.getIllustrationList() ) );
	            params.put( "dsInvestmentYield2", JasperReportsUtils.convertReportData( cepr01040201Business.getInvestmentYield().getYieldList() ) );
	            params.put( "dsNoteSekaligus2", JasperReportsUtils.convertReportData( cepr01040201Business.getNoteSekaligusRowList() ) );
	            params.put( "dsNoteBerkala2", JasperReportsUtils.convertReportData( cepr01040201Business.getNoteBerkalaRowList() ) );
	            params.put( "dsCommonHeaderSmall2", JasperReportsUtils.convertReportData( cepr01040201Business.getCommonHeaderSmallRowList() ) );
	            params.put( "dsInvestmentChoiceSmall2", JasperReportsUtils.convertReportData( cepr01040201Business.getInvestmentChoiceSmallRowList() ) );
	            params.put( "titleIllustration", "ILUSTRASI PERKEMBANGAN DANA APABILA PREMI DIBAYARKAN SAMPAI DENGAN USIA 55 TAHUN" );
            }
            
            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
            IllustrationResultVO illustrationResultVO = cepr01040201Business.getIllustrationResult(); // perhitungan polis
            params.put( "validityMsg", illustrationResultVO.getValidityMsg() );
            params.put( "dsEkalinkIllustration", JasperReportsUtils.convertReportData( illustrationResultVO.getIllustrationList() ) );

            params.put( "totalInvestmentAllocation", cepr01040201Business.getInvestmentYield().getTotalInvestmentAllocation() );
            params.put( "totalAssumptionLow", cepr01040201Business.getInvestmentYield().getTotalAssumptionLow() );
            params.put( "totalAssumptionMid", cepr01040201Business.getInvestmentYield().getTotalAssumptionMid() );
            params.put( "totalAssumptionHi", cepr01040201Business.getInvestmentYield().getTotalAssumptionHi() );

            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( cepr01040201Business.getCommonHeaderRowList() ) );
            params.put( "dsCommonHeaderSmall", JasperReportsUtils.convertReportData( cepr01040201Business.getCommonHeaderSmallRowList() ) );
            params.put( "dsInvestmentChoice", JasperReportsUtils.convertReportData( cepr01040201Business.getInvestmentChoiceRowList() ) );
            params.put( "dsInvestmentChoiceSmall", JasperReportsUtils.convertReportData( cepr01040201Business.getInvestmentChoiceSmallRowList() ) );
            params.put( "dsMonthlyPremium", JasperReportsUtils.convertReportData( cepr01040201Business.getMonthlyPremiumList( cepr01030102Form ) ) );
            params.put( "dsInvestmentPerformance", JasperReportsUtils.convertReportData( cepr01040201Business.getInvestmentPerformanceList() ) );
            params.put( "dsInvestmentYield", JasperReportsUtils.convertReportData( cepr01040201Business.getInvestmentYield().getYieldList() ) );
            params.put( "dsOneRow", JasperReportsUtils.convertReportData( cepr01040201Business.getOneRowList() ) );
            params.put( "dsNoteSekaligus", JasperReportsUtils.convertReportData( cepr01040201Business.getNoteSekaligusRowList() ) );
            params.put( "dsNoteBerkala", JasperReportsUtils.convertReportData( cepr01040201Business.getNoteBerkalaRowList() ) );
            
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
            		cepr01030102Form.getRightPartOfBusinessCd()== Cepr01040201Mapper.X2 ){
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
            putRiderParam( params, riderBusiness.getRiderPaList(), "dsRiderPa", "riderPaIndex" );
            params.put( "riderPaRisk", riderBusiness.getRiderPaRisk() );
            params.put( "dsRiderPaClone", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderPaList() ) );
            params.put( "riderLadiesInsTitle", MappingUtil.getLabel(cepr01030103Form.getLadiesInsList(), cepr01030103Form.getLadiesInsCd()));
            putRiderParam( params, riderBusiness.getRiderHcpList(), "dsRiderHcp", "riderHcpIndex" );
            putRiderParam( params, riderBusiness.getRiderHcpFamilyList(), "dsRiderHcpFamily", "riderHcpFamilyIndex" );
            putRiderParam( params, riderBusiness.getRiderHcpLadiesList(), "dsRiderHcpLadies", "riderHcpLadiesIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorTpdCiDeathList(), "dsRiderPayorTpdCiDeath", "riderPayorTpdCiDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderPayor5Tpd10CiDeathList(), "dsRiderPayor5Tpd10CiDeath", "riderPayor5Tpd10CiDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderWaiver5Tpd10CiList(), "dsRiderWaiver5Tpd10Ci", "riderWaiver5Tpd10CiIndex" );
            putRiderParam( params, riderBusiness.getRiderTpdList(), "dsRiderTpd", "riderTpdIndex" );
            putRiderParam( params, riderBusiness.getRiderTermRiderList(), "dsRiderTermRider", "riderTermRiderIndex" );
            putRiderParam( params, riderBusiness.getRiderLadiesInsList(), "dsRiderLadiesIns", "riderLadiesInsIndex" );
            putRiderParam( params, riderBusiness.getRiderWaiverTpdCiList(), "dsRiderWaiverTpdCi", "riderWaiverTpdCiIndex" );
            putRiderParam( params, riderBusiness.getRiderEkaSehatListMap(), "dsRiderEkaSehat", "riderEkaSehatIndex");
            putRiderParam( params, riderBusiness.getRiderHcpProviderList(), "dsRiderHcpProvider", "riderHcpProviderIndex" );
            putRiderParam( params, riderBusiness.getRiderCiList(), "dsRiderCi", "riderCiIndex" );
            putRiderParam( params, riderBusiness.getRiderEkaSehatInnerLimitListMap(), "dsRiderEkaSehatInnerLimit", "riderEkaSehatInnerLimitIndex");
            putRiderParam( params, riderBusiness.getRiderLadiesMedExpenseListMap(), "dsRiderLadiesMedExpense", "riderLadiesMedExpenseIndex");
            putRiderParam( params, riderBusiness.getRiderLadiesMedExpenseInnerLimitListMap(), "dsRiderLadiesMedExpenseInnerLimit", "riderLadiesMedExpenseInnerLimitIndex");
            
            putRiderParam( params, riderBusiness.getRiderPayorTpdDeathList(), "dsRiderPayorTpdDeath", "riderPayorTpdDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorCiDeathList(), "dsRiderPayorCiDeath", "riderPayorCiDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorCiList(), "dsRiderPayorCi", "riderPayorCiIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorSpouseTpdDeathList(), "dsRiderPayorSpouseTpdDeath", "riderPayorSpouseTpdDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderWaiverTpdList(), "dsRiderWaiverTpd", "riderWaiverTpdIndex" );
            putRiderParam( params, riderBusiness.getRiderWaiverCiList(), "dsRiderWaiverCi", "riderWaiverCiIndex" );
            putRiderParam( params, riderBusiness.getRiderScholarRiderList(), "dsRiderScholarRider", "riderScholarRiderIndex" );
            
            putRiderParam( params, riderBusiness.getRiderBabyList(), "dsRiderBaby", "riderBabyIndex" );
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getBabyFlag() ) )
            {
            	 params.put( "planBaby", cepr01030103Form.getBabyCd() );            	
            }
            putRiderParam( params, riderBusiness.getRiderEarlyStageCi99List(), "dsRiderEarlyStageCi99", "riderEarlyStageCi99Index" );
            putRiderParam( params, riderBusiness.getRiderMedicalPlusList(), "dsRiderMedicalPlus", "riderMedicalPlusIndex" );
            putRiderParam( params, riderBusiness.getRiderMedicalPlusRjList(), "dsRiderMedicalPlusRj", "riderMedicalPlusRjIndex" );
            putRiderParam( params, riderBusiness.getRiderMedicalPlusRgList(), "dsRiderMedicalPlusRg", "riderMedicalPlusRgIndex" );
            putRiderParam( params, riderBusiness.getRiderMedicalPlusRbList(), "dsRiderMedicalPlusRb", "riderMedicalPlusRbIndex" );
            putRiderParam( params, riderBusiness.getRiderMedicalPlusPkList(), "dsRiderMedicalPlusPk", "riderMedicalPlusPkIndex" );
            
            params.put( "dsChartUpSekaligus", JasperReportsUtils.convertReportData( riderBusiness.getChartScholarUpSekaligusTahunanList() ) );
            params.put( "dsUpSekaligusTahunan", JasperReportsUtils.convertReportData( riderBusiness.getScholarUpSekaligusTahunanList() ) );
            params.put( "usia_scholar", cepr01030103Form.getScholarshipChooseCd() );
            params.put( "dsRiderLadiesInsSummary", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesInsSummaryList() ) );
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
            
            //Halaman Tambahan Rincian Rider
            params.put( "dsCommonHeaderRincian", JasperReportsUtils.convertReportData( cepr01040201Business.getCommonHeaderRowList()) );
            double ldec_coi = cepr01040201Business.of_get_coi_basic( 1 );            
            BigDecimal biayaAsuransiPokok =  new BigDecimal(ldec_coi);
            params.put( "dsCommonBiayaRincian", JasperReportsUtils.convertReportData( cepr01040201Business.getCommonBiayaRincianRowList( biayaAsuransiPokok ) ) );
            
            params.put("pageRider", cepr01040201Business.getPageRider( currentIdx ));
            params.put( "dsCommonHeaderRincianRiderPa", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonHeaderRincianSmallRowList(1) ) );
            params.put( "dsCommonBiayaRincianRiderPa", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonBiayaRincianRider(1) ) );
            params.put( "dsCommonHeaderRincianRiderTpd", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonHeaderRincianSmallRowList(3) ) );
            params.put( "dsCommonBiayaRincianRiderTpd", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonBiayaRincianRider(3) ) ); 
            params.put( "dsCommonHeaderRincianRiderCi", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonHeaderRincianSmallRowList(4) ) );
            params.put( "dsCommonBiayaRincianRiderCi", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonBiayaRincianRider(4) ) ); 
            params.put( "dsCommonHeaderRincianRiderHCPFamily", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonHeaderRincianSmallRowList(11) ) );
            params.put( "dsCommonBiayaRincianRiderHCPFamily", JasperReportsUtils.convertReportData( cepr01040201Business.getCommonBiayaRincianRider(11) ) );
            params.put( "dsRincianRiderHCPFamily", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpFamilyList() ));
            params.put( "dsRincianRiderHcpFamilyParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderHcpFamilyParticipantsList() ) );
            
            params.put( "dsCommonHeaderRincianRiderHCP", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonHeaderRincianSmallRowList(2) ) );
            params.put( "dsCommonBiayaRincianRiderHCP", JasperReportsUtils.convertReportData( cepr01040201Business.getCommonBiayaRincianRider(2) ) );
            params.put( "dsRincianRiderHCP", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpList() ));    
            
            params.put( "dsCommonHeaderRincianRiderHCPProvider", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonHeaderRincianSmallRowList(16) ) );
            params.put( "dsCommonBiayaRincianRiderHCPProvider", JasperReportsUtils.convertReportData( cepr01040201Business.getCommonBiayaRincianRider(16) ) );
            params.put( "dsRincianRiderHCPProvider", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpProviderList()));
            params.put( "dsCommonHeaderRincianRiderTerm", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonHeaderRincianSmallRowList(12) ) );
            params.put( "dsCommonBiayaRincianRiderTerm", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonBiayaRincianRider(12) ) ); 
            params.put( "dsCommonHeaderRincianRiderEkaSehat", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonHeaderRincianSmallRowList(13) ) );
            params.put( "dsCommonBiayaRincianRiderEkaSehat", JasperReportsUtils.convertReportData( cepr01040201Business.getCommonBiayaRincianRider(13) ) );     
            params.put( "dsRincianRiderEkaSehat", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRincianRiderEkaSehat() ));
            params.put( "dsRincianRiderEkaSehatParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRincianRiderEkaSehatParticipantsList() ) ); 
            params.put( "dsCommonHeaderRincianRiderEkaSehatInnerLimit", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonHeaderRincianSmallRowList(15) ) );
            params.put( "dsCommonBiayaRincianRiderEkaSehatInnerLimit", JasperReportsUtils.convertReportData( cepr01040201Business.getCommonBiayaRincianRider(15) ) );     
            params.put( "dsRincianRiderEkaSehatInnerLimit", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRincianRiderEkaSehatInnerLimit() ));
            params.put( "dsRincianRiderEkaSehatInnerLimitParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRincianRiderEkaSehatInnerLimitParticipantsList() ) );
            params.put( "dsCommonHeaderRincianRiderWaiverTpdCi", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonHeaderRincianSmallRowList(17) ) );
            params.put( "dsCommonBiayaRincianRiderWaiverTpdCi", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonBiayaRincianRider(17) ) );
            params.put( "dsCommonHeaderRincianRiderPayorTpdCiDeath", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonHeaderRincianSmallRowList(18) ) );
            params.put( "dsCommonBiayaRincianRiderPayorTpdCiDeath", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonBiayaRincianRider(18) ) );
            
            params.put( "dsCommonHeaderRincianRiderLadiesIns", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonHeaderRincianSmallRowList(19) ) );
            params.put( "dsCommonBiayaRincianRiderLadiesIns", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonBiayaRincianRider(19) ) ); 
            params.put( "dsCommonHeaderRincianRiderHCPLadies", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonHeaderRincianSmallRowList(20) ) );
            params.put( "dsCommonBiayaRincianRiderHCPLadies", JasperReportsUtils.convertReportData( cepr01040201Business.getCommonBiayaRincianRider(20) ) );
            params.put( "dsRincianRiderHcpLadies", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpLadiesList()));
            params.put( "dsCommonHeaderRincianRiderLadiesMedExpense", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonHeaderRincianSmallRowList(21) ) );
            params.put( "dsCommonBiayaRincianRiderLadiesMedExpense", JasperReportsUtils.convertReportData( cepr01040201Business.getCommonBiayaRincianRider(21) ) );     
            params.put( "dsRincianRiderLadiesMedExpense", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRincianRiderLadiesMedExpenseSyariah() ));
            params.put( "dsRincianRiderLadiesMedExpenseParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesMedExpenseParticipantsList() ) );            
            params.put( "dsCommonHeaderRincianRiderLadiesMedExpenseInnerLimit", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonHeaderRincianSmallRowList(22) ) );
            params.put( "dsCommonBiayaRincianRiderLadiesMedExpenseInnerLimit", JasperReportsUtils.convertReportData( cepr01040201Business.getCommonBiayaRincianRider( 22) ) );     
            params.put( "dsRincianRiderLadiesMedExpenseInnerLimit", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRincianRiderLadiesMedExpenseInnerLimitSyariah() ));
            params.put( "dsRincianRiderLadiesMedExpenseInnerLimitParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesMedExpenseInnerLimitParticipantsList() ) );
                        
            params.put( "dsCommonHeaderRincianRiderPayor5Tpd10CiDeath", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonHeaderRincianSmallRowList(23) ) );
            params.put( "dsCommonBiayaRincianRiderPayor5Tpd10CiDeath", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonBiayaRincianRider(23) ) );
            params.put( "dsCommonHeaderRincianRiderWaiver5Tpd10Ci", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonHeaderRincianSmallRowList(24) ) );
            params.put( "dsCommonBiayaRincianRiderWaiver5Tpd10Ci", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonBiayaRincianRider(24) ) );
            params.put( "dsCommonHeaderRincianRiderScholar", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonHeaderRincianSmallRowList(25) ) );
            params.put( "dsCommonBiayaRincianRiderScholar", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonBiayaRincianRider(25) ) ); 
            params.put( "dsCommonHeaderRincianRiderEarlyStageCi99", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonHeaderRincianSmallRowList(27) ) );
            params.put( "dsCommonBiayaRincianRiderEarlyStageCi99", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonBiayaRincianRider(27) ) );            
            params.put( "dsCommonHeaderRincianRiderMedicalPlus", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonHeaderRincianSmallRowList(28) ) );
            params.put( "dsCommonBiayaRincianRiderMedicalPlus", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040201Business.getCommonBiayaRincianRider(28) ) );  
            params.put( "dsRincianRiderMedicalPlus", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderMedicalPlusList() ));
            params.put( "dsRincianRiderMedicalPlusRj", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderMedicalPlusRjList() ));
            params.put( "dsRincianRiderMedicalPlusRg", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderMedicalPlusRgList() ));
            params.put( "dsRincianRiderMedicalPlusRb", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderMedicalPlusRbList() ));
            params.put( "dsRincianRiderMedicalPlusPk", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderMedicalPlusPkList() ));                     
            params.put( "dsRincianRiderMedicalPlusParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRincianRiderMedicalPlusParticipantsList()) );   
            //     
            params.put( "dataSource", cepr01040201Business.getPageList( currentIdx ) );
              
            // Save E-Proposal PDF ke EBServer dan PopUp Download
            // ** Adrian@17/10/2013
            
            String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
            String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
            Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();
        
            String fileName = msag_id+"_"+no_proposal+".pdf";
            params.put( "no_proposal",  no_proposal );
            
            List temp = cepr01040201Business.getPageList( currentIdx );
            String rootDir_EProp="";
            if(flag_test==1){
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP_TEST;
            }else{
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP;
            }
           String dirName = rootDir_EProp+"\\"+msag_id;    
            
           if(illustrationResultVO.getValidityMsg().equals("Maaf, Proposal yang Anda buat Tidak Layak Jual.")){
          	 	result.put("proposalLayak",illustrationResultVO.getValidityMsg());
          }else{
           
            File sourceFile = new File( dirName + "\\" + fileName );
           try{
      
	        	   //if(cekDataApprovedOrNot.get(0).getPosisi()<3){
			            JasperUtils.exportReportToPdf(
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040201_product.jasper",
			            		dirName,
			                fileName,
							params,
							temp,
							PdfWriter.AllowPrinting,
			                null,
			                null
			            );
			          //  JasperUtils.downloadReportPDF(response, dirName, fileName);        	   
		    	   //}
	    	}catch( Exception e )
	        {
	    		
	            logger.error("ERROR", e);
	        }             
   		    
          //  jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
         //   request.getSession().setAttribute( "messageBoxList", null );
         //   request.getSession().setAttribute( "messageEkaSehatList", null );
         //   result = new ModelAndView( jasperViewer, params );   
           result.put("dirName", dirName);
           result.put("fileName", fileName);
           result.put("messageBoxList", null);
           result.put("messageEkaSehatList", null);
           result.put("params", params);
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