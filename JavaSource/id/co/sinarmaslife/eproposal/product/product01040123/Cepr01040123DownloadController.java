package id.co.sinarmaslife.eproposal.product.product01040123;

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

import id.co.sinarmaslife.eproposal.business.Cepr00000000RiderBusiness;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
import id.co.sinarmaslife.eproposal.product.product01040201.Cepr01040201Mapper;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030103Form;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.JrUtil;
import id.co.sinarmaslife.standard.util.MappingUtil;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.lowagie.text.pdf.PdfWriter;

public class Cepr01040123DownloadController extends StandardParent implements Controller
{
    static Integer currentIdx;
    protected final Log logger = LogFactory.getLog( getClass() );

    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040201DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01030103Form cepr01030103Form = cepr01030000HoldingForm.getCepr01030103Form();
        CredentialsVO credentialsVO = cepr01030000HoldingForm.getCredentialsVO();
        String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
        Cepr01040123Business cepr01040123Business = new Cepr01040123Business( eproposalManager, editorUtil, command );

        ModelAndView result;
        if( request.getSession().getAttribute( "messageEkaSehatList" ) == null )
        {
        	List<String> messageEkaSehatList = cepr01040123Business.getEkaSehatMsgBox();
        	request.getSession().setAttribute( "messageEkaSehatList", messageEkaSehatList );
        	result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        
        if( request.getSession().getAttribute( "messageBoxList" ) == null )
        {
            List<String> messageBoxList = cepr01040123Business.getMedicalMsgBox();
            request.getSession().setAttribute( "messageBoxList", messageBoxList );
            result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        else
        {
            AbstractJasperReportsView jasperViewer = new JasperReportsPdfView();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put( "format", "pdf" );

            Map<JRExporterParameter, Object> exporterParam = new HashMap<JRExporterParameter, Object>();
            exporterParam = JasperUtils.exporterParam(exporterParam);

            jasperViewer.setExporterParameters( exporterParam );
            jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040123_product.jasper" );
            
            //params.put( "title", "SMiLe LINK MEDIVEST" );
            Integer lsbsId = cepr01030102Form.getLeftPartOfBusinessCd();
            params.put( "title", eproposalManager.selectTitleBisnis(1, lsbsId));
            
            params.put( "currencySymbol", currencySymbol );
           // params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            params.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
            
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
            
            if( credentialsVO != null && credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_MNC ) && 
            		cepr01030102Form.getRightPartOfBusinessCd()== Cepr01040123Mapper.X2 ){
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
            
            String[][] riderTambahan = cepr01040123Business.getRiderPremiumList( cepr01030102Form );
            BigDecimal totalRider = new BigDecimal(0);
            for(int i = 0 ; i < riderTambahan.length ; i++){
            	params.put("asuransiTambahan"+(i+1), riderTambahan[i][0]);
            	params.put("biayaAsuransiTambahan"+(i+1), riderTambahan[i][1]);
            	if( i==0 ){
            		totalRider = new BigDecimal(riderTambahan[i][1]);
            	}
            }
            
            BigDecimal alokasiBiayaAkuisisi = cepr01030102Form.getBasePremium().multiply(new BigDecimal("0.8"));
            BigDecimal biayaAsuransiPokok = cepr01030102Form.getBasePremium().multiply(new BigDecimal("0.008"));
            BigDecimal biayaAdministrasi = new BigDecimal("15000");
            BigDecimal premiTopUpBerkala = cepr01030102Form.getTopUpPremium();
            BigDecimal biayaTopUpBerkala = premiTopUpBerkala.multiply(new BigDecimal("0.05"));
            
            String labelPilihanDana = "";
            String alokasiPremi = "";
            String alokasiPremiPersen = "";
            String alokasiInvestasiRendah = "";
            String alokasiInvestasiSedang = "";
            String alokasiInvestasiTinggi = "";
            String alokasiInvestasi = "";
            
            BigDecimal percent = new BigDecimal("100");
            if(cepr01030102Form.getFixIdr() != null){
            	params.put( "pilihanDanaInvestasiFixed", cepr01030102Form.getFixIdr().toString() + "%" );
            	labelPilihanDana = "- EXCELLINK Fixed Income Fund\n";
            	alokasiPremi = "Rp. " + editorUtil.convertToRoundedNoDigit(cepr01030102Form.getPremium().multiply(cepr01030102Form.getFixIdr().divide(percent))) + "\n";
            	alokasiInvestasi = cepr01030102Form.getFixIdr() + "% \n";
            	alokasiPremiPersen = alokasiInvestasi;
            	alokasiInvestasiRendah = (new BigDecimal("6.0").multiply(cepr01030102Form.getFixIdr().divide(percent))) + "%\n";
            	alokasiInvestasiSedang = (new BigDecimal("8.0").multiply(cepr01030102Form.getFixIdr().divide(percent))) + "%\n";
            	alokasiInvestasiTinggi = (new BigDecimal("12.0").multiply(cepr01030102Form.getFixIdr().divide(percent))) + "%\n";
            }else{
            	alokasiInvestasi = "0%\n";
            	alokasiInvestasiRendah = "0.00%\n";
            	alokasiInvestasiSedang = "0.00%\n";
                alokasiInvestasiTinggi = "0.00%\n";
            }
            if(cepr01030102Form.getDynamicIdr() != null){
            	params.put( "pilihanDanaInvestasiDynamic", cepr01030102Form.getDynamicIdr().toString() + "%" );
            	labelPilihanDana = labelPilihanDana + "- EXCELLINK Dynamic Income Fund\n";
            	alokasiPremi = alokasiPremi + "Rp. " + editorUtil.convertToRoundedNoDigit(cepr01030102Form.getPremium().multiply(cepr01030102Form.getDynamicIdr().divide(percent))) + "\n";
            	alokasiInvestasi = alokasiInvestasi + cepr01030102Form.getDynamicIdr() + "% \n";
            	alokasiPremiPersen = alokasiInvestasi;
            	alokasiInvestasiRendah = alokasiInvestasiRendah + (new BigDecimal("10.0").multiply(cepr01030102Form.getDynamicIdr().divide(percent))) + "%\n";
            	alokasiInvestasiSedang = alokasiInvestasiSedang + (new BigDecimal("18.0").multiply(cepr01030102Form.getDynamicIdr().divide(percent))) + "%\n";
            	alokasiInvestasiTinggi = alokasiInvestasiTinggi + (new BigDecimal("25.0").multiply(cepr01030102Form.getDynamicIdr().divide(percent))) + "%\n";
            }else{
            	alokasiInvestasi = alokasiInvestasi + "0%\n";
            	alokasiInvestasiRendah = alokasiInvestasiRendah + "0.00%\n";
            	alokasiInvestasiSedang = alokasiInvestasiSedang + "0.00%\n";
                alokasiInvestasiTinggi = alokasiInvestasiTinggi + "0.00%\n";
            }
            if(cepr01030102Form.getAggresiveIdr() != null){
            	params.put( "pilihanDanaInvestasiAggressive", cepr01030102Form.getAggresiveIdr().toString() + "%" );
            	labelPilihanDana = labelPilihanDana + "- EXCELLINK Aggresive Income Fund";
            	alokasiPremi = alokasiPremi + "Rp. " + editorUtil.convertToRoundedNoDigit(cepr01030102Form.getPremium().multiply(cepr01030102Form.getAggresiveIdr().divide(percent))) ;
            	alokasiInvestasi = alokasiInvestasi + cepr01030102Form.getAggresiveIdr() + "% ";
            	alokasiPremiPersen = alokasiInvestasi;
            	alokasiInvestasiRendah = alokasiInvestasiRendah + (new BigDecimal("10.0").multiply(cepr01030102Form.getAggresiveIdr().divide(percent))) + "%";
            	alokasiInvestasiSedang = alokasiInvestasiSedang + (new BigDecimal("22.0").multiply(cepr01030102Form.getAggresiveIdr().divide(percent))) + "%";
            	alokasiInvestasiTinggi = alokasiInvestasiTinggi + (new BigDecimal("32.0").multiply(cepr01030102Form.getAggresiveIdr().divide(percent))) + "%";
            }else{
            	alokasiInvestasi = alokasiInvestasi + "0%";
            	alokasiInvestasiRendah = alokasiInvestasiRendah + "0.00%";
            	alokasiInvestasiSedang = alokasiInvestasiSedang + "0.00%";
                alokasiInvestasiTinggi = alokasiInvestasiTinggi + "0.00%";
            }
            
            if( Cepr01040123Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
            {
            	params.put( "differ", "1");// MEDIVEST SINGLE
            }
            else if( Cepr01040123Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() )
            {
            	params.put( "differ", "2");// MEDIVEST
            }
            
            params.put( "labelPilihanDana", labelPilihanDana);
            params.put( "alokasiPremi", alokasiPremi);
            params.put( "alokasiPremiPersen", alokasiPremiPersen);
            params.put( "totalAlokasiInvestasi", "100% ");
            params.put( "alokasiInvestasi", alokasiInvestasi);
            params.put( "alokasiInvestasiRendah", alokasiInvestasiRendah);
            params.put( "alokasiInvestasiSedang", alokasiInvestasiSedang);
            params.put( "alokasiInvestasiTinggi", alokasiInvestasiTinggi);
            
            params.put( "alokasiBiayaAkuisisi", editorUtil.convertToTwoDigit(alokasiBiayaAkuisisi) );
            params.put( "biayaAsuransiPokok", editorUtil.convertToTwoDigit(biayaAsuransiPokok) );
            params.put( "biayaAdministrasi", editorUtil.convertToTwoDigit(biayaAdministrasi) );
            params.put( "premiTopUpBerkala", editorUtil.convertToTwoDigit(premiTopUpBerkala) );
            params.put( "biayaTopUpBerkala", editorUtil.convertToTwoDigit(biayaTopUpBerkala) );
            params.put( "totalAlokasi", editorUtil.convertToTwoDigit(cepr01030102Form.getBasePremium().subtract(alokasiBiayaAkuisisi).subtract(biayaAsuransiPokok).add(premiTopUpBerkala).subtract(biayaTopUpBerkala).subtract(totalRider).subtract(biayaAdministrasi)) );
            
            params.put( "namaTertanggung", cepr01030101Form.getInsuredName() );
            params.put( "usiaTertanggung", cepr01030101Form.getInsuredAge() + " Tahun" );
            params.put( "namaPemegangPolis", cepr01030101Form.getPolicyHolderName() );
            params.put( "usiaPemegangPolis", cepr01030101Form.getPolicyHolderAge() + " Tahun" );
            params.put( "masaPertanggungan", cepr01030102Form.getTermOfContract() + " Tahun");
            params.put("premiPokok", currencySymbol + " " + editorUtil.convertToTwoDigit(cepr01030102Form.getBasePremium()) );
            params.put("premiPokok2", editorUtil.convertToTwoDigit(cepr01030102Form.getBasePremium()) );
            params.put( "masaPembayaranPremi", cepr01030102Form.getTermOfPayment() + " Tahun");
            params.put( "uangPertanggungan", currencySymbol + " " + editorUtil.convertToTwoDigit(cepr01030102Form.getTotalSumInsured()) );
            double up = new Double(cepr01030102Form.getTotalSumInsured().toString());
            params.put( "uangPertanggunganPa", currencySymbol + " " + editorUtil.convertToTwoDigit(Math.min(up, 2000000000.0)) );
            String tipeMedisPP = cepr01040123Business.getTipeMedis(cepr01030101Form.getPolicyHolderAge());
            String tipeMedisTT = cepr01040123Business.getTipeMedis(cepr01030101Form.getInsuredAge());
            params.put( "tipeMedisCalonTertanggung", "\""+tipeMedisTT+"\", jika belum punya polis di Sinarmas MSIG Life" );
            params.put( "tipeMedisCalonPemegangPolis", "\""+tipeMedisPP+"\", jika belum punya polis di Sinarmas MSIG Life" );
            
            logger.info( "*-*-*-* cepr01030102Form.getCurrencyCd() = " + cepr01030102Form.getCurrencyCd() );
            
            jasperViewer.setReportDataKey( "dataSource" );
            jasperViewer.setJdbcDataSource( eproposalManager.getDataSource() );

            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
            
            Map<String, Object> mapMaster = new HashMap<String, Object>();
            IllustrationResultVO illustrationResultVO = cepr01040123Business.getIllustration123Result();
            IllustrationResultVO illustrationResultVO_Surrender = cepr01040123Business.getIllustrationResult_Surrender();
            //IllustrationResultVO illustrationResultVO = (IllustrationResultVO) mapMaster.get("Illustration1");
            IllustrationResultVO illustrationResultVO2 = (IllustrationResultVO) mapMaster.get("Illustration2");
            
            params.put( "validityMsg", illustrationResultVO.getValidityMsg() );
            params.put( "dsEkalinkIllustration", JasperReportsUtils.convertReportData( illustrationResultVO_Surrender.getIllustrationList() ) );
            params.put( "dsEkalinkIllustration3", JasperReportsUtils.convertReportData( illustrationResultVO.getIllustrationList() ) );
            
            String titlePackage = "";            
            if( cepr01030102Form.getEducationPackageCd() != null ){
            	if( cepr01030102Form.getEducationPackageCd().intValue() == 2 ) titlePackage = "(PAKET A)";
            	else if( cepr01030102Form.getEducationPackageCd().intValue() == 3 ) titlePackage = "(PAKET B)";
            	else if( cepr01030102Form.getEducationPackageCd().intValue() == 4 ) titlePackage = "(PAKET C)";
            }
            
            params.put( "titlePackage", titlePackage );
            logger.info( "*-*-*-* cepr01040201Business.getInvestmentYield() = " + cepr01040123Business.getInvestmentYield() );
            params.put( "totalInvestmentAllocation", cepr01040123Business.getInvestmentYield().getTotalInvestmentAllocation() );
            params.put( "totalAssumptionLow", cepr01040123Business.getInvestmentYield().getTotalAssumptionLow() );
            params.put( "totalAssumptionMid", cepr01040123Business.getInvestmentYield().getTotalAssumptionMid() );
            params.put( "totalAssumptionHi", cepr01040123Business.getInvestmentYield().getTotalAssumptionHi() );

            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( cepr01040123Business.getCommonHeaderRow123List() ) );
            params.put( "dsCommonHeaderSmall", JasperReportsUtils.convertReportData( cepr01040123Business.getCommonHeaderSmallRow123List() ) );
            params.put( "dsCommonHeaderSmall3", JasperReportsUtils.convertReportData( cepr01040123Business.getCommonHeaderSmallRow123List() ) );
            params.put( "dsInvestmentChoice", JasperReportsUtils.convertReportData( cepr01040123Business.getInvestmentChoiceRowList() ) );
            params.put( "dsInvestmentChoiceSmall", JasperReportsUtils.convertReportData( cepr01040123Business.getInvestmentChoiceSmallRow123List() ) );
            params.put( "dsInvestmentChoiceSmall3", JasperReportsUtils.convertReportData( cepr01040123Business.getInvestmentChoiceSmallRow123List() ) );
            params.put( "dsMonthlyPremium", JasperReportsUtils.convertReportData( cepr01040123Business.getMonthlyPremiumList( cepr01030102Form ) ) );
            params.put( "dsInvestmentPerformance", JasperReportsUtils.convertReportData( cepr01040123Business.getInvestmentPerformanceList() ) );
            params.put( "dsInvestmentYield", JasperReportsUtils.convertReportData( cepr01040123Business.getInvestmentYield().getYieldList() ) );
            params.put( "dsInvestmentYield3", JasperReportsUtils.convertReportData( cepr01040123Business.getInvestmentYield().getYieldList() ) );
            //params.put( "dsOneRow", JasperReportsUtils.convertReportData( cepr01040123Business.getOneRowList() ) );
            //params.put( "dsNoteSekaligus", JasperReportsUtils.convertReportData( cepr01040201Business.getNoteSekaligusRowList() ) );
            //params.put( "dsNoteBerkala", JasperReportsUtils.convertReportData( cepr01040201Business.getNoteBerkalaRowList() ) );
            
            currentIdx = 0;
            Cepr00000000RiderBusiness riderBusiness = new Cepr00000000RiderBusiness( eproposalManager, editorUtil, command );
            putRiderParam( params, riderBusiness.getRiderPaList(), "dsRiderPa", "riderPaIndex" );
            params.put( "riderPaRisk", riderBusiness.getRiderPaRisk() );
            params.put( "dsRiderPaClone", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderPaList() ) );
            params.put( "riderLadiesInsTitle", MappingUtil.getLabel(cepr01030103Form.getLadiesInsList(), cepr01030103Form.getLadiesInsCd()));
            putRiderParam( params, riderBusiness.getRiderHcpList(), "dsRiderHcp", "riderHcpIndex" );
            putRiderParam( params, riderBusiness.getRiderHcpFamilyList(), "dsRiderHcpFamily", "riderHcpFamilyIndex" );
            putRiderParam( params, riderBusiness.getRiderHcpLadiesList(), "dsRiderHcpLadies", "riderHcpLadiesIndex" );
            putRiderParam( params, riderBusiness.getRiderLadiesInsList(), "dsRiderLadiesIns", "riderLadiesInsIndex" );
            putRiderParam( params, riderBusiness.getRiderWaiverTpdCiList(), "dsRiderWaiverTpdCi", "riderWaiverTpdCiIndex" );
            putRiderParam( params, riderBusiness.getRiderHcpProviderList(), "dsRiderHcpProvider", "riderHcpProviderIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorTpdCiDeathList(), "dsRiderPayorTpdCiDeath", "riderPayorTpdCiDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderTpdList(), "dsRiderTpd", "riderTpdIndex" );
            putRiderParam( params, riderBusiness.getRiderCiList(), "dsRiderCi", "riderCiIndex" );
            putRiderParam( params, riderBusiness.getRiderTermRiderList(), "dsRiderTermRider", "riderTermRiderIndex" );
            putRiderParam( params, riderBusiness.getRiderEkaSehatListMap(), "dsRiderEkaSehat", "riderEkaSehatIndex");
            putRiderParam( params, riderBusiness.getRiderEkaSehatInnerLimitListMap(), "dsRiderEkaSehatInnerLimit", "riderEkaSehatInnerLimitIndex");
            putRiderParam( params, riderBusiness.getRiderLadiesMedExpenseListMap(), "dsRiderLadiesMedExpense", "riderLadiesMedExpenseIndex");
            putRiderParam( params, riderBusiness.getRiderLadiesMedExpenseInnerLimitListMap(), "dsRiderLadiesMedExpenseInnerLimit", "riderLadiesMedExpenseInnerLimitIndex");
            putRiderParam( params, riderBusiness.getRiderMedicalPlusList(), "dsRiderMedicalPlus", "riderMedicalPlusIndex" );
            putRiderParam( params, riderBusiness.getRiderMedicalPlusRjList(), "dsRiderMedicalPlusRj", "riderMedicalPlusRjIndex" );
            putRiderParam( params, riderBusiness.getRiderMedicalPlusRgList(), "dsRiderMedicalPlusRg", "riderMedicalPlusRgIndex" );
            putRiderParam( params, riderBusiness.getRiderMedicalPlusRbList(), "dsRiderMedicalPlusRb", "riderMedicalPlusRbIndex" );
            putRiderParam( params, riderBusiness.getRiderMedicalPlusPkList(), "dsRiderMedicalPlusPk", "riderMedicalPlusPkIndex" );        
            
//            putRiderParam( params, riderBusiness.getRiderPayorTpdDeathList(), "dsRiderPayorTpdDeath", "riderPayorTpdDeathIndex" );
//            putRiderParam( params, riderBusiness.getRiderPayorCiDeathList(), "dsRiderPayorCiDeath", "riderPayorCiDeathIndex" );
//            putRiderParam( params, riderBusiness.getRiderPayorCiList(), "dsRiderPayorCi", "riderPayorCiIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorSpouseTpdDeathList(), "dsRiderPayorSpouseTpdDeath", "riderPayorSpouseTpdDeathIndex" );
//            putRiderParam( params, riderBusiness.getRiderWaiverTpdList(), "dsRiderWaiverTpd", "riderWaiverTpdIndex" );
//            putRiderParam( params, riderBusiness.getRiderWaiverCiList(), "dsRiderWaiverCi", "riderWaiverCiIndex" );
            putRiderParam( params, riderBusiness.getRiderScholarRiderList(), "dsRiderScholarRider", "riderScholarRiderIndex" );
            putRiderParam( params, riderBusiness.getRiderBabyList(), "dsRiderBaby", "riderBabyIndex" );
            if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getBabyFlag() ) )
            {
            	 params.put( "planBaby", cepr01030103Form.getBabyCd() );            	
            }
            putRiderParam( params, riderBusiness.getRiderEarlyStageCi99List(), "dsRiderEarlyStageCi99", "riderEarlyStageCi99Index" );
            putRiderParam( params, riderBusiness.getRiderEkaSehatExtraListMap(), "dsRiderEkaSehatExtra", "riderEkaSehatExtraIndex");
            
            params.put( "dsChartUpSekaligus", JasperReportsUtils.convertReportData( riderBusiness.getChartScholarUpSekaligusTahunanList() ) );
            params.put( "dsUpSekaligusTahunan", JasperReportsUtils.convertReportData( riderBusiness.getScholarUpSekaligusTahunanList() ) );
            params.put( "usia_scholar", cepr01030103Form.getScholarshipChooseCd() );
            params.put( "dsRiderLadiesInsSummary", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesInsSummaryList() ) );
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
            
            params.put( "dataSource", cepr01040123Business.getPageList( currentIdx ) );
            
            params.put( "dsEkalinkRider", JasperReportsUtils.convertReportData( cepr01040123Business.getRiderPremiumList(cepr01030102Form, cepr01030101Form.getInsuredAge()) ) );
            params.put( "dsEkalinkRider2", JasperReportsUtils.convertReportData( cepr01040123Business.getRiderPremiumList(cepr01030102Form, cepr01030101Form.getInsuredAge()) ) );
            //=========================================== Hal.Rincian
            double byiAsuransiPokok = cepr01040123Business.of_get_coi_basic( 1 );     
            params.put( "dsCommonHeaderRincian", JasperReportsUtils.convertReportData( cepr01040123Business.getCommonHeaderRincianRowList()) );
            params.put( "dsCommonBiayaRincian", JasperReportsUtils.convertReportData( cepr01040123Business.getCommonBiayaRincianRowList( new BigDecimal(byiAsuransiPokok)  ) ) );
            
            params.put( "dsCommonHeaderRincianRiderPa", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(1) ) );
            params.put( "dsCommonBiayaRincianRiderPa", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonBiayaRincianRider(1) ) );
            params.put( "dsCommonHeaderRincianRiderTpd", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(3) ) );
            params.put( "dsCommonBiayaRincianRiderTpd", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonBiayaRincianRider(3) ) ); 
            params.put( "dsCommonHeaderRincianRiderCi", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(4) ) );
            params.put( "dsCommonBiayaRincianRiderCi", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonBiayaRincianRider(4) ) ); 
            params.put( "dsCommonHeaderRincianRiderHCPFamily", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(11) ) );
            params.put( "dsCommonBiayaRincianRiderHCPFamily", JasperReportsUtils.convertReportData( cepr01040123Business.getCommonBiayaRincianRider(11) ) );
            params.put( "dsRincianRiderHCPFamily", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpFamilyList() ));
            params.put( "dsRincianRiderHcpFamilyParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderHcpFamilyParticipantsList() ) );
            
            params.put( "dsCommonHeaderRincianRiderHCP", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(2) ) );
            params.put( "dsCommonBiayaRincianRiderHCP", JasperReportsUtils.convertReportData( cepr01040123Business.getCommonBiayaRincianRider(2) ) );
            params.put( "dsRincianRiderHCP", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpList() ));    
            
            params.put( "dsCommonHeaderRincianRiderHCPProvider", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(16) ) );
            params.put( "dsCommonBiayaRincianRiderHCPProvider", JasperReportsUtils.convertReportData( cepr01040123Business.getCommonBiayaRincianRider(16) ) );
            params.put( "dsRincianRiderHCPProvider", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpProviderList()));    
            
            params.put( "dsCommonHeaderRincianRiderTerm", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(12) ) );
            params.put( "dsCommonBiayaRincianRiderTerm", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonBiayaRincianRider(12) ) ); 
            params.put( "dsCommonHeaderRincianRiderEkaSehat", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(13) ) );
            params.put( "dsCommonBiayaRincianRiderEkaSehat", JasperReportsUtils.convertReportData( cepr01040123Business.getCommonBiayaRincianRider(13) ) );     
            params.put( "dsRincianRiderEkaSehat", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRincianRiderEkaSehat() ));
            params.put( "dsRincianRiderEkaSehatParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRincianRiderEkaSehatParticipantsList() ) ); 
            params.put( "dsCommonHeaderRincianRiderEkaSehatInnerLimit", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(15) ) );
            params.put( "dsCommonBiayaRincianRiderEkaSehatInnerLimit", JasperReportsUtils.convertReportData( cepr01040123Business.getCommonBiayaRincianRider(15) ) );     
            params.put( "dsRincianRiderEkaSehatInnerLimit", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRincianRiderEkaSehatInnerLimit() ));
            params.put( "dsRincianRiderEkaSehatInnerLimitParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRincianRiderEkaSehatInnerLimitParticipantsList() ) );
            params.put( "dsCommonHeaderRincianRiderWaiverTpdCi", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(17) ) );
            params.put( "dsCommonBiayaRincianRiderWaiverTpdCi", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonBiayaRincianRider(17) ) );
            params.put( "dsCommonHeaderRincianRiderPayorTpdCiDeath", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(18) ) );
            params.put( "dsCommonBiayaRincianRiderPayorTpdCiDeath", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonBiayaRincianRider(18) ) );
            
            params.put( "dsCommonHeaderRincianRiderLadiesIns", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(19) ) );
            params.put( "dsCommonBiayaRincianRiderLadiesIns", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonBiayaRincianRider(19) ) ); 
            params.put( "dsCommonHeaderRincianRiderHCPLadies", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(20) ) );
            params.put( "dsCommonBiayaRincianRiderHCPLadies", JasperReportsUtils.convertReportData( cepr01040123Business.getCommonBiayaRincianRider(20) ) );
            params.put( "dsRincianRiderHcpLadies", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpLadiesList()));
            params.put( "dsCommonHeaderRincianRiderLadiesMedExpense", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(21) ) );
            params.put( "dsCommonBiayaRincianRiderLadiesMedExpense", JasperReportsUtils.convertReportData( cepr01040123Business.getCommonBiayaRincianRider(21) ) );     
            params.put( "dsRincianRiderLadiesMedExpense", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRincianRiderLadiesMedExpenseSyariah() ));
            params.put( "dsRincianRiderLadiesMedExpenseParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesMedExpenseParticipantsList() ) );            
            params.put( "dsCommonHeaderRincianRiderLadiesMedExpenseInnerLimit", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(22) ) );
            params.put( "dsCommonBiayaRincianRiderLadiesMedExpenseInnerLimit", JasperReportsUtils.convertReportData( cepr01040123Business.getCommonBiayaRincianRider( 22) ) );     
            params.put( "dsRincianRiderLadiesMedExpenseInnerLimit", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRincianRiderLadiesMedExpenseInnerLimitSyariah() ));
            params.put( "dsRincianRiderLadiesMedExpenseInnerLimitParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesMedExpenseInnerLimitParticipantsList() ) );
                        
            params.put( "dsCommonHeaderRincianRiderPayor5Tpd10CiDeath", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(23) ) );
            params.put( "dsCommonBiayaRincianRiderPayor5Tpd10CiDeath", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonBiayaRincianRider(23) ) );
            params.put( "dsCommonHeaderRincianRiderWaiver5Tpd10Ci", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(24) ) );
            params.put( "dsCommonBiayaRincianRiderWaiver5Tpd10Ci", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonBiayaRincianRider(24) ) );
            params.put( "dsCommonHeaderRincianRiderScholar", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(25) ) );
            params.put( "dsCommonBiayaRincianRiderScholar", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonBiayaRincianRider(25) ) ); 
            params.put( "dsCommonHeaderRincianRiderEarlyStageCi99", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(27) ) );
            params.put( "dsCommonBiayaRincianRiderEarlyStageCi99", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonBiayaRincianRider(27) ) );            
            params.put( "dsCommonHeaderRincianRiderMedicalPlus", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(28) ) );
            params.put( "dsCommonBiayaRincianRiderMedicalPlus", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonBiayaRincianRider(28) ) );  
            params.put( "dsRincianRiderMedicalPlus", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderMedicalPlusList() ));
            params.put( "dsRincianRiderMedicalPlusRj", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderMedicalPlusRjList() ));
            params.put( "dsRincianRiderMedicalPlusRg", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderMedicalPlusRgList() ));
            params.put( "dsRincianRiderMedicalPlusRb", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderMedicalPlusRbList() ));
            params.put( "dsRincianRiderMedicalPlusPk", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderMedicalPlusPkList() ));                     
          
            params.put( "dsCommonHeaderRincianRiderEkaSehatExtra", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonHeaderRincianSmallRowList(29) ) );
            params.put( "dsCommonBiayaRincianRiderEkaSehatExtra", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040123Business.getCommonBiayaRincianRider(29) ) );     
            params.put( "dsRincianRiderEkaSehatExtra", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRincianRiderEkaSehatExtra() ));
            params.put( "participantEkaSehatExtra", riderBusiness.checkParticapantEkaSehatExtra() );
            params.put( "dsRiderEkaSehatExtraParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderEkaSehatExtraParticipantsList() ) );
            params.put( "dsRincianRiderEkaSehatExtraParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRincianRiderEkaSehatExtraParticipantsList() ) ); 
            
            params.put("pageRider", cepr01040123Business.getPageRider( currentIdx ));
            //==========================================    		
    		// Save E-Proposal PDF ke EBServer dan PopUp Download
            // ** Adrian@06/01/2014
            String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
            String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
            Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();       
            String fileName = msag_id+"_"+no_proposal+".pdf";
            params.put( "no_proposal",  no_proposal );
            
            List temp = cepr01040123Business.getPageList( currentIdx );
            String rootDir_EProp="";
            if(flag_test==1){
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP_TEST;
            }else{
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP;
            }
            String dirName = rootDir_EProp+"\\"+msag_id+"\\"+no_proposal;
            
            File sourceFile = new File( dirName + "\\" + fileName );
           try{
			            JasperUtils.exportReportToPdf(
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040123_product.jasper",
			            		dirName,
			                fileName,
							params,
							temp,
							PdfWriter.AllowPrinting,
			                null,
			                null
			            );
			            JasperUtils.downloadReportPDF(response, dirName, fileName);        	   
		    	   //}
	    	   }catch( Exception e )
	        	{
	    		   
	            logger.error("ERROR", e);
	        }   
           
            jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
              
            request.getSession().setAttribute( "messageBoxList", null );
            request.getSession().setAttribute( "messageEkaSehatList", null );
            result = new ModelAndView( jasperViewer, params );
        }

        return result;
    }

    public void putRiderParam( Map<String, Object> params, List contentList, String dsParamName, String idxParamName )
    {
        JRDataSource source = JrUtil.convertToDsAndSetNullIfEmpty( contentList );
        if( source != null ){ 
        	currentIdx = currentIdx + 1;
        	if(currentIdx == 1){
        		params.put( "dsOneRow", JasperReportsUtils.convertReportData( getOneRowList() ) );
        		params.put( "dsTwoRow", JasperReportsUtils.convertReportData( getOneRowList() ) );
        	}
        	if(dsParamName.equals("dsRiderPa")){
        		params.put( "r1", dsParamName );
        	}else if(dsParamName.equals("dsRiderHcp")){
        		params.put( "r2", dsParamName );
        	}
        }

        params.put( dsParamName, source );
        params.put( idxParamName, currentIdx.toString() + "." );
    }
    
    public List<Map<String, Object>> getOneRowList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        params = new HashMap<String, Object>();
        params.put( "dummy", "row1" );
        result.add( params );
        return result;
    }
}