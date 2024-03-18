package id.co.sinarmaslife.eproposal.product.product01040214;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040214DownloadController
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 10, 2009 3:28:12 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import com.lowagie.text.pdf.PdfWriter;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.business.Cepr00000000RiderBusiness;
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.standard.util.*;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.jasperreports.AbstractJasperReportsView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class Cepr01040214DownloadController extends StandardParent implements Controller
{
    static Integer currentIdx;
    protected final Log logger = LogFactory.getLog( getClass() );

    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040214DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
        Cepr01040214Business cepr01040214Business = new Cepr01040214Business( eproposalManager, editorUtil, command );

        double li_kali = 1;
        if( cepr01030102Form.getPaymentModeCd() == 1 )      //triwulan
            li_kali = 4;
        else if( cepr01030102Form.getPaymentModeCd() == 2 ) //semesteran
            li_kali = 2;
        else if( cepr01030102Form.getPaymentModeCd() == 6 ) //bulanan
            li_kali = 12;
        
        ModelAndView result = null;
        if( request.getSession().getAttribute( "messageEkaSehatList" ) == null )
        {
        	List<String> messageEkaSehatList = cepr01040214Business.getEkaSehatMsgBox();
        	request.getSession().setAttribute( "messageEkaSehatList", messageEkaSehatList );
        	result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        
        if( request.getSession().getAttribute( "messageBoxList" ) == null )
        {
            List<String> messageBoxList = cepr01040214Business.getMedicalMsgBox();
            request.getSession().setAttribute( "messageBoxList", messageBoxList );
            result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        else
        {
        //	AbstractJasperReportsView jasperViewer = new JasperReportsPdfView();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put( "format", "pdf" );

            Map<JRExporterParameter, Object> exporterParam = new HashMap<JRExporterParameter, Object>();
            exporterParam = JasperUtils.exporterParam(exporterParam);

       //    jasperViewer.setExporterParameters( exporterParam );
       //     jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040214_product.jasper" );
            
//          TITLE
            if(cepr01030102Form.getRightPartOfBusinessCd() == 1){
            	params.put( "title", "CERDAS 5" );
            	params.put( "titleRider", "CERDAS 5" );
            }else if(cepr01030102Form.getRightPartOfBusinessCd() == 2){
            	params.put( "title", "CERDAS 10" );
            	params.put( "titleRider", "CERDAS 10" );
            }else if(cepr01030102Form.getRightPartOfBusinessCd() == 5){
            	params.put( "title", "CERDAS SINGLE" );
            	params.put( "titleRider", "CERDAS SINGLE" );
            }else if(cepr01030102Form.getRightPartOfBusinessCd() == 3){
            	params.put( "title", "CERDAS CARE 5" );
            	params.put( "titleRider", "CERDAS CARE 5" );
            }else if(cepr01030102Form.getRightPartOfBusinessCd() == 4){
            	params.put( "title", "CERDAS CARE 10" );
            	params.put( "titleRider", "CERDAS CARE 10" );
            }else if(cepr01030102Form.getRightPartOfBusinessCd() == 6){
            	params.put( "title", "Excellent Link \n (For Your Education)" );
            	params.put( "titleRider", "EXCELLENT LINK (FOR YOUR EDUCATION)" );
            }else if(cepr01030102Form.getRightPartOfBusinessCd() == 7){
            	params.put( "title", "Excellent Link \n (For Your Education)" );
            	params.put( "titleRider", "EXCELLENT LINK (FOR YOUR EDUCATION)" );
            }else if(cepr01030102Form.getRightPartOfBusinessCd() == 8){
            	params.put( "title", "Excellent Link \n (For Your Education)" );
            	params.put( "titleRider", "EXCELLENT LINK (FOR YOUR EDUCATION)" );
            }else if(cepr01030102Form.getRightPartOfBusinessCd() == 9){
            	params.put( "title", "Excellent Link \n (For Your Education Plus)" );
            	params.put( "titleRider", "EXCELLENT LINK (FOR YOUR EDUCATION PLUS)" );
            }else if(cepr01030102Form.getRightPartOfBusinessCd() == 10){
            	params.put( "title", "Excellent Link \n (For Your Education Plus)" );
            	params.put( "titleRider", "EXCELLENT LINK (FOR YOUR EDUCATION PLUS)" );
            }
            
            params.put( "plan", LazyConverter.toString( cepr01030102Form.getRightPartOfBusinessCd() ) );
            params.put( "currencySymbol", currencySymbol );
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            params.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
            BigDecimal alokasiBiayaAkuisisi = cepr01030102Form.getBasePremium().multiply(new BigDecimal("0.8"));
//            BigDecimal biayaAsuransiPokok = cepr01030102Form.getBasePremium().multiply(new BigDecimal("0.0008"));
            BigDecimal biayaAsuransiPokok =  new BigDecimal(cepr01040214Business.of_get_coi_basic(1));
            BigDecimal biayaAdministrasi = BigDecimal.ZERO;
            
            if(cepr01030102Form.getPaymentModeCd() == 0){
            	biayaAdministrasi = new BigDecimal("25000");
            }else{
            	if("01".equals(cepr01030102Form.getCurrencyCd())){
            		biayaAdministrasi = new BigDecimal("15000");
                }else if("02".equals(cepr01030102Form.getCurrencyCd())){
                	biayaAdministrasi = new BigDecimal("20000");
                }
            }
            
            if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	biayaAdministrasi = biayaAdministrasi.divide(new BigDecimal("10000"));
            }
            
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
            BigDecimal totalAlokasi = cepr01030102Form.getBasePremium().subtract(alokasiBiayaAkuisisi).subtract(biayaAsuransiPokok).add(premiTopUpBerkala).subtract(biayaTopUpBerkala).subtract(biayaAdministrasi);
            //params.put( "totalAlokasi", editorUtil.convertToTwoDigit(cepr01030102Form.getBasePremium().subtract(alokasiBiayaAkuisisi).subtract(biayaAsuransiPokok).add(premiTopUpBerkala).subtract(biayaTopUpBerkala).subtract(biayaAdministrasi)) );
            
            params.put( "namaTertanggung", cepr01030101Form.getInsuredName() );
            params.put( "usiaTertanggung", cepr01030101Form.getInsuredAge() + " Tahun" );
            params.put( "namaPemegangPolis", cepr01030101Form.getPolicyHolderName() );
            params.put( "usiaPemegangPolis", cepr01030101Form.getPolicyHolderAge() + " Tahun" );
            params.put( "masaPertanggungan", cepr01030102Form.getTermOfContract() + " Tahun");
            params.put("premiPokok", currencySymbol + " " + editorUtil.convertToTwoDigit(cepr01030102Form.getBasePremium()) );
            params.put("premiPokok2", editorUtil.convertToTwoDigit(cepr01030102Form.getBasePremium()) );
            params.put( "masaPembayaranPremi", cepr01030102Form.getTermOfPayment() + " Tahun");
            double maksUangPertanggunganPa = cepr01030102Form.getCurrencyCd().equals("02")?200000.0: 2000000000.0;
            double maksUangPertanggunganPa3 = cepr01030102Form.getCurrencyCd().equals("02")?50000.0: 500000000.0;
            params.put( "uangPertanggungan", currencySymbol + " " + editorUtil.convertToTwoDigit(cepr01030102Form.getTotalSumInsured()) );
            double up = new Double(cepr01030102Form.getTotalSumInsured().toString());
            params.put( "uangPertanggunganPa", currencySymbol + " " + editorUtil.convertToTwoDigit(Math.min(up, maksUangPertanggunganPa)) );
            params.put( "uangPertanggunganPa3", currencySymbol + " " + editorUtil.convertToTwoDigit(Math.min(2 * LazyConverter.toDouble(cepr01030102Form.getBasePremium()) * li_kali,maksUangPertanggunganPa3)) );
            double maksUangPertanggunganCI = cepr01030102Form.getCurrencyCd().equals("02")?200000.0: 2000000000.0;
            params.put( "uangPertanggunganCI", currencySymbol + " " + editorUtil.convertToTwoDigit(Math.min( cepr01030102Form.getTotalSumInsured().doubleValue(),maksUangPertanggunganCI)) );
            String tipeMedisPP = cepr01040214Business.getTipeMedis(cepr01030101Form.getPolicyHolderAge());
            String tipeMedisTT = cepr01040214Business.getTipeMedis(cepr01030101Form.getInsuredAge());
            params.put( "tipeMedisCalonTertanggung", "\""+tipeMedisTT+"\", jika belum punya polis di Sinarmas MSIG Life" );
            params.put( "tipeMedisCalonPemegangPolis", "\""+tipeMedisPP+"\", jika belum punya polis di Sinarmas MSIG Life" );
            
       //     jasperViewer.setReportDataKey( "dataSource" );
       //     jasperViewer.setJdbcDataSource( eproposalManager.getDataSource() );

            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
            
            Map<String, Object> mapMaster = new HashMap<String, Object>();
            mapMaster = cepr01040214Business.getIllustration214Result(cepr01030102Form.getTermOfPayment());
            IllustrationResultVO illustrationResultVO = (IllustrationResultVO) mapMaster.get("Illustration1");
            IllustrationResultVO illustrationResultVO2 = (IllustrationResultVO) mapMaster.get("Illustration2");
            
            params.put( "validityMsg", illustrationResultVO.getValidityMsg() );
            params.put( "dsEkalinkIllustration", JasperReportsUtils.convertReportData( illustrationResultVO.getIllustrationList() ) );
            params.put( "dsEkalinkIllustration2", JasperReportsUtils.convertReportData( illustrationResultVO2.getIllustrationList() ) );
            int exr = 0;
            String[][] riderTambahan = cepr01040214Business.getRiderPremiumList( cepr01030102Form );
            BigDecimal totalRider = new BigDecimal(0);
            for(int i = 0 ; i < riderTambahan.length ; i++){
            	params.put("asuransiTambahan"+(i+1), riderTambahan[i][0]);
            	params.put("biayaAsuransiTambahan"+(i+1), riderTambahan[i][1]);
            	if( i==0 ){
            		totalRider = new BigDecimal(riderTambahan[i][1]);
            	}else if(riderTambahan[i][6] != null){
            		if(i >= 1 && i<= 4){
            			totalAlokasi = totalAlokasi.subtract(new BigDecimal(riderTambahan[i][6]));
            			exr = exr+1;
            		}
            	}
            }
            
            if(exr == 4 || cepr01030102Form.getRightPartOfBusinessCd() == 6 || cepr01030102Form.getRightPartOfBusinessCd() == 5){
            	params.put( "exr", "extraRider");
            }else{
            	params.put( "exr", null);
            }
            
            params.put( "totalAlokasi", editorUtil.convertToTwoDigit(totalAlokasi));
            params.put("premiPokokLabel", "Premi Pokok " + riderTambahan[0][3] +"an");
            params.put("premiTopBerkalaLabel", "Premi Top Up Berkala " + riderTambahan[0][3] + "an");
            params.put("biayaAsuransiPokokLabel", "Biaya Asuransi Pokok Per" + riderTambahan[0][2]);
            params.put("BiayaAdministrasiLabel", "Biaya Administrasi Per" + riderTambahan[0][2]);
            
            logger.info( "*-*-*-* cepr01040201Business.getInvestmentYield() = " + cepr01040214Business.getInvestmentYield() );
            params.put( "totalInvestmentAllocation", cepr01040214Business.getInvestmentYield().getTotalInvestmentAllocation() );
            params.put( "totalAssumptionLow", cepr01040214Business.getInvestmentYield().getTotalAssumptionLow() );
            params.put( "totalAssumptionMid", cepr01040214Business.getInvestmentYield().getTotalAssumptionMid() );
            params.put( "totalAssumptionHi", cepr01040214Business.getInvestmentYield().getTotalAssumptionHi() );

            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( cepr01040214Business.getCommonHeaderRowList() ) );
            params.put( "dsCommonHeader2", JasperReportsUtils.convertReportData( cepr01040214Business.getCommonHeaderRowList() ) );
            params.put( "dsCommonHeader3", JasperReportsUtils.convertReportData( cepr01040214Business.getCommonHeaderRowList() ) );
            params.put( "dsCommonHeaderSmall", JasperReportsUtils.convertReportData( cepr01040214Business.getCommonHeaderSmallRowList() ) );
            params.put( "dsInvestmentChoice", JasperReportsUtils.convertReportData( cepr01040214Business.getInvestmentChoiceRowList() ) );
            params.put( "dsInvestmentChoiceSmall", JasperReportsUtils.convertReportData( cepr01040214Business.getInvestmentChoiceSmallRowList() ) );
            //params.put( "dsMonthlyPremium", JasperReportsUtils.convertReportData( cepr01040114Business.getMonthlyPremiumList( cepr01030102Form ) ) );
            //params.put( "dsInvestmentPerformance", JasperReportsUtils.convertReportData( cepr01040201Business.getInvestmentPerformanceList() ) );
            params.put( "dsInvestmentYield", JasperReportsUtils.convertReportData( cepr01040214Business.getInvestmentYield().getYieldList() ) );
            //params.put( "dsOneRow", JasperReportsUtils.convertReportData( cepr01040201Business.getOneRowList() ) );
            //params.put( "dsNoteSekaligus", JasperReportsUtils.convertReportData( cepr01040201Business.getNoteSekaligusRowList() ) );
            //params.put( "dsNoteBerkala", JasperReportsUtils.convertReportData( cepr01040201Business.getNoteBerkalaRowList() ) );
            
            currentIdx = 0;
            Cepr00000000RiderBusiness riderBusiness = new Cepr00000000RiderBusiness( eproposalManager, editorUtil, command );
            putRiderParam( params, riderBusiness.getRiderPaList(), "dsRiderPa", "riderPaIndex" );
            params.put( "riderPaRisk", riderBusiness.getRiderPaRisk() );
            params.put( "dsRiderPaClone", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderPaList() ) );
            putRiderParam( params, riderBusiness.getRiderHcpList(), "dsRiderHcp", "riderHcpIndex" );
            putRiderParam( params, riderBusiness.getRiderHcpFamilyList(), "dsRiderHcpFamily", "riderHcpFamilyIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorTpdDeathList(), "dsRiderPayorTpdDeath", "riderPayorTpdDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorCiDeathList(), "dsRiderPayorCiDeath", "riderPayorCiDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorCiList(), "dsRiderPayorCi", "riderPayorCiIndex" );
            putRiderParam( params, riderBusiness.getRiderPayorSpouseTpdDeathList(), "dsRiderPayorSpouseTpdDeath", "riderPayorSpouseTpdDeathIndex" );
            putRiderParam( params, riderBusiness.getRiderWaiverTpdList(), "dsRiderWaiverTpd", "riderWaiverTpdIndex" );
            putRiderParam( params, riderBusiness.getRiderWaiverCiList(), "dsRiderWaiverCi", "riderWaiverCiIndex" );
            putRiderParam( params, riderBusiness.getRiderTpdList(), "dsRiderTpd", "riderTpdIndex" );
            putRiderParam( params, riderBusiness.getRiderCiList(), "dsRiderCi", "riderCiIndex" );
            putRiderParam( params, riderBusiness.getRiderTermRiderList(), "dsRiderTermRider", "riderTermRiderIndex" );
            params.put( "dsRiderHcpFamilyParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderHcpFamilyParticipantsList() ) );
            params.put( "baseBenefitHcpFam", riderBusiness.computeBaseBenefitHcpFam() );
            params.put( "dataSource", cepr01040214Business.getPageList( currentIdx ) );

            // Save E-Proposal PDF ke EBServer dan PopUp Download
            // ** Adrian@28/11/2013            
            String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
            String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
            Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();
          //  String fileName="testx.pdf";
            String fileName = msag_id+"_"+no_proposal+".pdf";
            params.put( "no_proposal", no_proposal );
            
            List temp = cepr01040214Business.getPageList( currentIdx );
            String rootDir_EProp="";
            if(flag_test==1){
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP_TEST;
            }else{
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP;
            }
           String dirName = rootDir_EProp+"\\"+msag_id;    
            
            File sourceFile = new File( dirName + "\\" + fileName );
            try{      
	        	   //if(cekDataApprovedOrNot.get(0).getPosisi()<3){
			            JasperUtils.exportReportToPdf(
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040214_product.jasper",
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
            
         //   jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
            request.getSession().setAttribute( "messageBoxList", null );
            request.getSession().setAttribute( "messageEkaSehatList", null );
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
