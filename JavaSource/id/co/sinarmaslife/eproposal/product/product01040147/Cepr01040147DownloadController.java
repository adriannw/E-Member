package id.co.sinarmaslife.eproposal.product.product01040147;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.jasperreports.AbstractJasperReportsView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.lowagie.text.pdf.PdfWriter;

/**********************************************************************
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
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030103Form;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.JrUtil;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.MappingUtil;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;

public class Cepr01040147DownloadController extends StandardParent implements Controller
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
        Cepr01040147Business cepr01040147Business = new Cepr01040147Business( eproposalManager, editorUtil, command );

        ModelAndView result = null;
        if( request.getSession().getAttribute( "messageEkaSehatList" ) == null )
        {
        	List<String> messageEkaSehatList = cepr01040147Business.getEkaSehatMsgBox();
        	request.getSession().setAttribute( "messageEkaSehatList", messageEkaSehatList );
        	result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        
        if( request.getSession().getAttribute( "messageBoxList" ) == null )
        {
            List<String> messageBoxList = cepr01040147Business.getMedicalMsgBox();
            request.getSession().setAttribute( "messageBoxList", messageBoxList );
            result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        else
        {
//            AbstractJasperReportsView jasperViewer = new JasperReportsPdfView();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put( "format", "pdf" );

            Map<JRExporterParameter, Object> exporterParam = new HashMap<JRExporterParameter, Object>();
            exporterParam = JasperUtils.exporterParam(exporterParam);

//            jasperViewer.setExporterParameters( exporterParam );
//            jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040147_product.jasper" );
            
            params.put( "logoBismilah", eproposalManager.getProps().getProperty( "logo.bismilah" ) );
            params.put( "title", "SMiLe LINK EDUVEST SYARIAH" );
            params.put( "currencySymbol", currencySymbol );
          //  params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate()) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            params.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
            
            String label_special_offer = "";
            String note_special_offer = "";
            
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
            if( cepr01030102Form.getSpecialOfferCd() != null && cepr01030102Form.getSpecialOfferCd().equals( Hardcode.SPECIAL_OFFER_LUCKY_DRAW ) )
            {
            	groupId = "7";
            	note_lucky_draw = "Proposal ini mengikuti program Lucky Draw";
            }
            params.put( "groupId", groupId );
            params.put( "note_lucky_draw", note_lucky_draw );
            params.put( "label_special_offer", label_special_offer );
            params.put( "note_special_offer", note_special_offer );
            
            String[][] riderTambahan = cepr01040147Business.getRiderPremiumList( cepr01030102Form );
            BigDecimal totalRider = new BigDecimal(0);
            for(int i = 0 ; i < riderTambahan.length ; i++){
            	params.put("asuransiTambahan"+(i+1), riderTambahan[i][0]);
            	params.put("biayaAsuransiTambahan"+(i+1), riderTambahan[i][1]);
            	if( i==0 ){
            		totalRider = new BigDecimal(riderTambahan[i][1]);
            	}
            }
            
            BigDecimal alokasiBiayaAkuisisi = cepr01030102Form.getBasePremium().multiply(new BigDecimal("0.8"));
            BigDecimal biayaAsuransiPokok = cepr01030102Form.getTotalSumInsured().multiply(new BigDecimal("0.00016"));
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
//            	alokasiInvestasi = "0%\n";
//            	alokasiInvestasiRendah = "0.00%\n";
//            	alokasiInvestasiSedang = "0.00%\n";
//                alokasiInvestasiTinggi = "0.00%\n";
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
//            	alokasiInvestasi = alokasiInvestasi + "0%\n";
//            	alokasiInvestasiRendah = alokasiInvestasiRendah + "0.00%\n";
//            	alokasiInvestasiSedang = alokasiInvestasiSedang + "0.00%\n";
//                alokasiInvestasiTinggi = alokasiInvestasiTinggi + "0.00%\n";
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
//            	alokasiInvestasi = alokasiInvestasi + "0%";
//            	alokasiInvestasiRendah = alokasiInvestasiRendah + "0.00%";
//            	alokasiInvestasiSedang = alokasiInvestasiSedang + "0.00%";
//                alokasiInvestasiTinggi = alokasiInvestasiTinggi + "0.00%";
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
            String tipeMedisPP = cepr01040147Business.getTipeMedis(cepr01030101Form.getPolicyHolderAge());
            String tipeMedisTT = cepr01040147Business.getTipeMedis(cepr01030101Form.getInsuredAge());
            params.put( "tipeMedisCalonTertanggung", "\""+tipeMedisTT+"\", jika belum punya polis di Sinarmas MSIG Life" );
            params.put( "tipeMedisCalonPemegangPolis", "\""+tipeMedisPP+"\", jika belum punya polis di Sinarmas MSIG Life" );
            
            logger.info( "*-*-*-* cepr01030102Form.getCurrencyCd() = " + cepr01030102Form.getCurrencyCd() );
            
//            jasperViewer.setReportDataKey( "dataSource" );
//            jasperViewer.setJdbcDataSource( eproposalManager.getDataSource() );

            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.syariah" ) );
            
            Map<String, Object> mapMaster = new HashMap<String, Object>();
            mapMaster = cepr01040147Business.getIllustration114Result(cepr01030102Form.getTermOfPayment());
            IllustrationResultVO illustrationResultVO = (IllustrationResultVO) mapMaster.get("Illustration1");
            IllustrationResultVO illustrationResultVO2 = (IllustrationResultVO) mapMaster.get("Illustration2");
            
            params.put( "validityMsg", illustrationResultVO.getValidityMsg() );
            params.put( "dsEkalinkIllustration", JasperReportsUtils.convertReportData( illustrationResultVO.getIllustrationList() ) );
            params.put( "dsEkalinkIllustration2", JasperReportsUtils.convertReportData( illustrationResultVO2.getIllustrationList() ) );

            logger.info( "*-*-*-* cepr01040201Business.getInvestmentYield() = " + cepr01040147Business.getInvestmentYield() );
            params.put( "totalInvestmentAllocation", cepr01040147Business.getInvestmentYield().getTotalInvestmentAllocation() );
            params.put( "totalAssumptionLow", cepr01040147Business.getInvestmentYield().getTotalAssumptionLow() );
            params.put( "totalAssumptionMid", cepr01040147Business.getInvestmentYield().getTotalAssumptionMid() );
            params.put( "totalAssumptionHi", cepr01040147Business.getInvestmentYield().getTotalAssumptionHi() );

            String titlePackage = "";
            
            if( cepr01030102Form.getEducationPackageCd() != null ){
            	if( cepr01030102Form.getEducationPackageCd().intValue() == 2 ) titlePackage = "(PAKET A)";
            	else if( cepr01030102Form.getEducationPackageCd().intValue() == 3 ) titlePackage = "(PAKET B)";
            	else if( cepr01030102Form.getEducationPackageCd().intValue() == 4 ) titlePackage = "(PAKET C)";
            }
            
            params.put( "titlePackage", titlePackage );
            
            //params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( cepr01040201Business.getCommonHeaderRowList() ) );
            //params.put( "dsCommonHeaderSmall", JasperReportsUtils.convertReportData( cepr01040201Business.getCommonHeaderSmallRowList() ) );
            //params.put( "dsInvestmentChoice", JasperReportsUtils.convertReportData( cepr01040201Business.getInvestmentChoiceRowList() ) );
            //params.put( "dsInvestmentChoiceSmall", JasperReportsUtils.convertReportData( cepr01040201Business.getInvestmentChoiceSmallRowList() ) );
            //params.put( "dsMonthlyPremium", JasperReportsUtils.convertReportData( cepr01040114Business.getMonthlyPremiumList( cepr01030102Form ) ) );
            //params.put( "dsInvestmentPerformance", JasperReportsUtils.convertReportData( cepr01040201Business.getInvestmentPerformanceList() ) );
            params.put( "dsInvestmentYield", JasperReportsUtils.convertReportData( cepr01040147Business.getInvestmentYield().getYieldList() ) );
            //params.put( "dsOneRow", JasperReportsUtils.convertReportData( cepr01040201Business.getOneRowList() ) );
            //params.put( "dsNoteSekaligus", JasperReportsUtils.convertReportData( cepr01040201Business.getNoteSekaligusRowList() ) );
            //params.put( "dsNoteBerkala", JasperReportsUtils.convertReportData( cepr01040201Business.getNoteBerkalaRowList() ) );
            
            currentIdx = 0;
            Cepr00000000RiderBusiness riderBusiness = new Cepr00000000RiderBusiness( eproposalManager, editorUtil, command );
            putRiderParam( params, riderBusiness.getRiderPaSyariahList(), "dsRiderPa", "riderPaIndex", null );
            params.put( "riderPaRisk", riderBusiness.getRiderPaRisk() );
            params.put( "dsRiderPaClone", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderPaSyariahList() ) );
            putRiderParam( params, riderBusiness.getRiderHcpSyariahList(), "dsRiderHcp", "riderHcpIndex", null );
            putRiderParam( params, riderBusiness.getRiderHcpFamilySyariahList(), "dsRiderHcpFamily", "riderHcpFamilyIndex", null );
            putRiderParam( params, riderBusiness.getRiderHcpLadiesSyariahList(), "dsRiderHcpLadies", "riderHcpLadiesIndex", null );
            putRiderParam( params, riderBusiness.getRiderLadiesInsSyariahList(), "dsRiderLadiesIns", "riderLadiesInsIndex", null );
            putRiderParam( params, riderBusiness.getRiderWaiverTpdCiSyariahList(), "dsRiderWaiverTpdCi", "riderWaiverTpdCiIndex", null );
            putRiderParam( params, riderBusiness.getRiderHcpProviderSyariahList(), "dsRiderHcpProvider", "riderHcpProviderIndex", null );
            putRiderParam( params, riderBusiness.getRiderPayorTpdCiDeathSyariahList(), "dsRiderPayorTpdCiDeath", "riderPayorTpdCiDeathIndex", null );
            putRiderParam( params, riderBusiness.getRiderTpdSyariahList(), "dsRiderTpd", "riderTpdIndex", null );
            putRiderParam( params, riderBusiness.getRiderCiSyariahList(), "dsRiderCi", "riderCiIndex", null );
            putRiderParam( params, riderBusiness.getRiderTermRiderList(), "dsRiderTermRider", "riderTermRiderIndex", null );
            putRiderParam( params, riderBusiness.getRiderEkaSehatSyariahListMap(), "dsRiderEkaSehat", "riderEkaSehatIndex", null );
            putRiderParam( params, riderBusiness.getRiderEkaSehatInnerLimitSyariahListMap(), "dsRiderEkaSehatInnerLimit", "riderEkaSehatInnerLimitIndex", null );
            putRiderParam( params, riderBusiness.getRiderLadiesMedExpenseSyariahListMap(), "dsRiderLadiesMedExpense", "riderLadiesMedExpenseIndex", null );
            putRiderParam( params, riderBusiness.getRiderLadiesMedExpenseInnerSyariahLimitListMap(), "dsRiderLadiesMedExpenseInnerLimit", "riderLadiesMedExpenseInnerLimitIndex", null );
            
//            putRiderParam( params, riderBusiness.getRiderTpd10List(), "dsRider10Tpd", "riderTpd10Index", null );
//            putRiderParam( params, riderBusiness.getRiderWaiver10TpdList(), "dsRiderWaiver10Tpd", "riderWaiver10TpdIndex", null );
//            putRiderParam( params, riderBusiness.getRiderWaiver10CiList(), "dsRiderWaiver10Ci", "riderWaiver10CiIndex", null );
//            putRiderParam( params, riderBusiness.getRiderPayorTpdDeathList(), "dsRiderPayorTpdDeath", "riderPayorTpdDeathIndex", null );
//            putRiderParam( params, riderBusiness.getRiderPayorCiList(), "dsRiderPayorCi", "riderPayorCiIndex", null );
//            putRiderParam( params, riderBusiness.getRiderPayor10CiList(), "dsRiderPayor10Ci", "riderPayorCi10Index", null );
//            putRiderParam( params, riderBusiness.getRiderPayorCiDeathList(), "dsRiderPayorCiDeath", "riderPayorCiDeathIndex", null );
            putRiderParam( params, riderBusiness.getRiderPayorSpouseTpdDeathSyariahList(), "dsRiderPayorSpouseTpdDeath", "riderPayorSpouseTpdDeathIndex", null );
            putRiderParam( params, riderBusiness.getRiderScholarSyariahRiderList(), "dsRiderScholarRider", "riderScholarRiderIndex", null );
            putRiderParam( params, riderBusiness.getRiderEarlyStageCi99SyariahList(), "dsRiderEarlyStageCi99", "riderEarlyStageCi99Index", null );
            
            params.put( "dsChartUpSekaligus", JasperReportsUtils.convertReportData( riderBusiness.getChartScholarUpSekaligusTahunanList() ) );
            params.put( "dsUpSekaligusTahunan", JasperReportsUtils.convertReportData( riderBusiness.getScholarUpSekaligusTahunanList() ) );
            params.put( "usia_scholar", cepr01030103Form.getScholarshipChooseCd() );
//            paymentMode
            String paymentMode = null;
            
            switch( LazyConverter.toInt( cepr01030102Form.getPaymentModeCd() ) ){
            	case Hardcode.PAY_MODE_CD_TAHUNAN : paymentMode = "Tahunan"; break;
            	case Hardcode.PAY_MODE_CD_SEMESTERAN : paymentMode = "Semesteran"; break;
            	case Hardcode.PAY_MODE_CD_TRIWULANAN : paymentMode = "Triwulanan"; break;
            	case Hardcode.PAY_MODE_CD_BULANAN : paymentMode = "Bulanan"; break;
            	default:break;
            }
            
            params.put( "paymentMode", paymentMode );
            params.put( "dsRiderLadiesInsSummary", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesInsSyariahSummaryList() ) );
            params.put( "dsRiderHcpFamilyParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderHcpFamilyParticipantsList() ) );
            params.put( "baseBenefitHcpFam", riderBusiness.computeBaseBenefitHcpFam() );
            params.put( "baseBenefitHcpLad", riderBusiness.computeBaseBenefitHcpLadies() );
            params.put( "dsRiderHcpLadiesParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderHcpLadiesParticipantsList() ) );
            params.put( "participantEkaSehatInnerLimit", riderBusiness.checkParticapantEkaSehatInnerLimit() );
            params.put( "dsRiderEkaSehatInnerLimitParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderEkaSehatInnerLimitParticipantsList() ) );
            params.put( "dsRiderHcpProviderParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderHcpProviderParticipantsList() ) );
            params.put( "baseBenefitHcpPro", riderBusiness.computeBaseBenefitHcpPro() );
            params.put( "dsEkalinkRider", JasperReportsUtils.convertReportData( cepr01040147Business.getRiderPremiumList(cepr01030102Form, cepr01030101Form.getInsuredAge()) ) );
            params.put( "participantEkaSehat", riderBusiness.checkParticapantEkaSehat() );
            params.put( "dsRiderEkaSehatParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderEkaSehatParticipantsList() ) );
            params.put( "dsRiderLadiesMedExpenseParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesMedExpenseParticipantsList()) );
            params.put( "dsRiderLadiesMedExpenseInnerLimitParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesMedExpenseInnerLimitParticipantsList()) );
            params.put("jenis", cepr01030102Form.getRightPartOfBusinessCd()+"");
            String mappingTitleWaiverTpdCi = MappingUtil.getLabel(cepr01030103Form.getWaiverTpdCiChooseList(), cepr01030103Form.getWaiverTpdCiChooseCd());
            params.put( "riderWaiverTpdCiTitle", mappingTitleWaiverTpdCi );
            
            params.put( "dataSource", cepr01040147Business.getPageList( currentIdx ) );
            
            // Save PDF E-Proposal ke EBServer dan PopUp Download
            // *** Adrian@13/01/2014
            String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
            String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
            Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();
        
            String fileName = msag_id+"_"+no_proposal+".pdf";
            params.put( "no_proposal",  no_proposal );
            
            List temp = cepr01040147Business.getPageList( currentIdx );
            String rootDir_EProp="";
            if(flag_test==1){
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP_TEST;
            }else{
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP;
            }
           String dirName = rootDir_EProp+"\\"+msag_id;
            
            File sourceFile = new File( dirName + "\\" + fileName );
           try{
			       JasperUtils.exportReportToPdf(
			    		   "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040147_product.jasper",
			            		dirName,
			                fileName,
							params,
							temp,
							PdfWriter.AllowPrinting,
			                null,
			                null
			            );
			            JasperUtils.downloadReportPDF(response, dirName, fileName);  
	    	}catch( Exception e )
	        {
	    		
	            logger.error("ERROR", e);  
	        }            
            
//            jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
            request.getSession().setAttribute( "messageBoxList", null );
            request.getSession().setAttribute( "messageEkaSehatList", null );
//            result = new ModelAndView( jasperViewer, params );
        }

        return result;
    }

    public void putRiderParam( Map<String, Object> params, List contentList, String dsParamName, String idxParamName, String paramEx )
    {
        JRDataSource source = JrUtil.convertToDsAndSetNullIfEmpty( contentList );
        if( source != null ){ 
        	currentIdx = currentIdx + 1;
        	if(dsParamName.equals("dsRiderPa")){
        		params.put( "r1", dsParamName );
        	}else if(dsParamName.equals("dsRiderHcp")){
        		params.put( "r2", dsParamName );
        		params.put( "biayaHcp", paramEx );
        	}else if(dsParamName.equals("dsRiderPayorTpdDeath")){
        		params.put( "r3", dsParamName );
        	}else if(dsParamName.equals("dsRiderPayorCi")){
        		params.put( "r4", dsParamName );
        	}else if(dsParamName.equals("dsRiderWaiver10Tpd")){
        		params.put( "r5", dsParamName );
        	}else if(dsParamName.equals("dsRiderWaiverCi")){
        		params.put( "r6", dsParamName );
        	}else if(dsParamName.equals("dsRiderWaiver10Ci")){
        		params.put( "r7", dsParamName );
        	}else if(dsParamName.equals("dsRiderCi")){
        		params.put( "r8", dsParamName );
        	}else if(dsParamName.equals("dsRiderPayorCiDeath")){
        		params.put( "r9", dsParamName );
        	}else if(dsParamName.equals("dsRiderHcpFamily")){
        		params.put( "r11", dsParamName );
        	}else if(dsParamName.equals("dsRider10Tpd")){
        		params.put( "r12", dsParamName );
        	}else if(dsParamName.equals("dsRiderEkaSehat")){
        		params.put( "r13", dsParamName );
        	}else if(dsParamName.equals("dsRiderEkaSehatInnerLimit")){
        		params.put( "r14", dsParamName );
        	}else if(dsParamName.equals("dsRiderHcpProvider")){
        		params.put( "r15", dsParamName );
        	}else if(dsParamName.equals("dsRiderLadiesMedExpense")){
        		params.put( "r20", dsParamName );
        	}else if(dsParamName.equals("dsRiderLadiesMedExpenseInnerLimit")){
        		params.put( "r21", dsParamName );
        	}
        	
        }
        params.put( dsParamName, source );
        params.put( idxParamName, currentIdx.toString() + "." );
    }
}