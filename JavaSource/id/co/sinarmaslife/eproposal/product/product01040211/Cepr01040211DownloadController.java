package id.co.sinarmaslife.eproposal.product.product01040211;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040211DownloadController
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : fadly mathendra
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
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
import id.co.sinarmaslife.eproposal.model.vo.ParticipantVO;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030103Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030104Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030106Form;
import id.co.sinarmaslife.eproposal.web.Cepr01031001Form;
import id.co.sinarmaslife.standard.StandardConst;
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
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

public class Cepr01040211DownloadController extends StandardParent implements Controller
{
    static Integer currentIdx;
    protected final Log logger = LogFactory.getLog( getClass() );

    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040211DownloadController.handleRequest" ); 
        
        ModelAndView result;
        HttpSession session = request.getSession();
//        AbstractJasperReportsView jasperViewer = new JasperReportsPdfView();
        Map<String, Object> params = new HashMap<String, Object>();
        CredentialsVO currentCredentialsVO = ( CredentialsVO ) session.getAttribute( StandardConst.CREDENTIALS_SES );
        
        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01040211Business cepr01040211Business = new Cepr01040211Business( eproposalManager, editorUtil, command );
        
        HashMap<String, Object> map = new HashMap<String, Object>();
        if( request.getSession().getAttribute( "messageEkaSehatList" ) == null )
        {
        	List<String> messageEkaSehatList = cepr01040211Business.getEkaSehatMsgBox();
        	request.getSession().setAttribute( "messageEkaSehatList", messageEkaSehatList );
        	result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        
        if( request.getSession().getAttribute( "messageBoxList" ) == null )
        {
            List<String> messageBoxList = cepr01040211Business.getMedicalMsgBox();
            request.getSession().setAttribute( "messageBoxList", messageBoxList );
            result = new ModelAndView( "CEPR00000000DownloadDocumentJSP" );
        }
        else
        {
        	 Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        	 
            map = controller(cepr01030000HoldingForm);
//            jasperViewer = (AbstractJasperReportsView) map.get("jasperViewer");
            
            String kelayakan = (StringUtil.isEmpty(map.get("proposalLayak")))?null:(String)map.get("proposalLayak");
            if (kelayakan != null && kelayakan.equals("Maaf, Proposal yang Anda buat Tidak Layak Jual.")){
                result = new ModelAndView( "CEPR00000000DownloadDocumentJSP2" );

            }else{
            	
            
          params = (Map<String, Object>) map.get("params");
          String dirName = (StringUtil.isEmpty(map.get("dirName")))?null:(String)map.get("dirName");
          String fileName = (StringUtil.isEmpty(map.get("fileName")))?null:(String)map.get("fileName");
          JasperUtils.downloadReportPDF(response, dirName, fileName);   
//          jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
          request.getSession().setAttribute( "messageBoxList", map.get("messageBoxList") );
          request.getSession().setAttribute( "messageEkaSehatList", map.get("messageEkaSehatList") );
//         result = new ModelAndView( jasperViewer, params );
          result = null; 
            }
        }
        return result;
    }
    
    public void putRiderParam( Map<String, Object> params, List contentList, String dsParamName, String idxParamName )
    {
        JRDataSource source = JrUtil.convertToDsAndSetNullIfEmpty( contentList );
        Integer test = currentIdx;
        if( source != null && contentList!= null && contentList.size() > 0 ) currentIdx = currentIdx + 1;
        Integer test2 = currentIdx;
        params.put( dsParamName, source );
        params.put( idxParamName, currentIdx.toString() + "." );
    }
    
       
    public HashMap<String, Object> controller(  Cepr01030000HoldingForm cepr01030000HoldingForm )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040211DownloadController.handleRequest" ); 
        HashMap<String, Object> result = new HashMap<String, Object>();
          Cepr01040211Business cepr01040211Business = new Cepr01040211Business(eproposalManager, editorUtil, cepr01030000HoldingForm);
        
           Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
           Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
           Cepr01030103Form cepr01030103Form = cepr01030000HoldingForm.getCepr01030103Form();
           Cepr01030104Form cepr01030104Form = cepr01030000HoldingForm.getCepr01030104Form();
           Cepr01030106Form cepr01030106Form = cepr01030000HoldingForm.getCepr01030106Form();
           Cepr01031001Form cepr01031001Form = cepr01030000HoldingForm.getCepr01031001Form();
           String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
                           
           CredentialsVO currentCredentialsVO = cepr01030000HoldingForm.getCredentialsVO();
           
     	
        //   AbstractJasperReportsView jasperViewer = new JasperReportsPdfView();
           Map<String, Object> params = new HashMap<String, Object>();
           params.put( "format", "pdf" );

           Map<JRExporterParameter, Object> exporterParam = new HashMap<JRExporterParameter, Object>();
           exporterParam = JasperUtils.exporterParam(exporterParam);

       //    jasperViewer.setExporterParameters( exporterParam );
       //    jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040211_product.jasper" );
               
//         TITLE
           if(cepr01030102Form.getRightPartOfBusinessCd() == 1){
           	params.put( "title", "CERDAS 5" );
           }else if(cepr01030102Form.getRightPartOfBusinessCd() == 2){
           	params.put( "title", "CERDAS 10" );
           }else if(cepr01030102Form.getRightPartOfBusinessCd() == 3){
           	params.put( "title", "CERDAS SINGLE" );
           }else if(cepr01030102Form.getRightPartOfBusinessCd() == 4){
           	params.put( "title", "CERDAS CARE 5" );
           }else if(cepr01030102Form.getRightPartOfBusinessCd() == 5){
           	params.put( "title", "CERDAS CARE 10" );
           }else if(cepr01030102Form.getRightPartOfBusinessCd() == 6){
           	params.put( "title", "CERDAS CARE SINGLE" );
           }else if(cepr01030102Form.getRightPartOfBusinessCd() == 7){
           	params.put( "title", "EXCELLENT LINK" );
           }else if(cepr01030102Form.getRightPartOfBusinessCd() == 8){
           	params.put( "title", "EXCELLENT LINK" );
           }else if(cepr01030102Form.getRightPartOfBusinessCd() == 9){
           	params.put( "title", "EXCELLENT LINK" );
           }else if(cepr01030102Form.getRightPartOfBusinessCd() == 10){
           	if( currentCredentialsVO.getMsagId().equals( Hardcode.DMTM_MSAG_ID ) && currentCredentialsVO.getGroupId().equals( Hardcode.GROUP_DMTM ) ){
           		params.put( "title", "CERDAS" );
           	}else{
           		params.put( "title", "SIMAS POWER LINK" );
           	}
           }else if(cepr01030102Form.getRightPartOfBusinessCd() == 11){
           	params.put( "title", "SIMAS POWER LINK" );
           }else if(cepr01030102Form.getRightPartOfBusinessCd() == 12){
           	params.put( "title", "SIMAS POWER LINK" );
           }else if(cepr01030102Form.getRightPartOfBusinessCd() == 13){
           	params.put( "title", "EXCELLENT LINK (FOR YOUR HEALTH)" );
           }else if(cepr01030102Form.getRightPartOfBusinessCd() == 14){
           	params.put( "title", "EXCELLENT LINK (FOR YOUR HEALTH)" );
           }else if(cepr01030102Form.getRightPartOfBusinessCd() == 15){
           	params.put( "title", "EXCELLENT LINK (FOR YOUR HEALTH)" );
           }else if(cepr01030102Form.getRightPartOfBusinessCd() == 19){
           	params.put( "title", "SMiLe LINK SATU" );
           }else if(cepr01030102Form.getRightPartOfBusinessCd() == 20){
           	params.put( "title", "SMiLe LINK SATU" );
           }else if(cepr01030102Form.getRightPartOfBusinessCd() == 21){
           	params.put( "title", "SMiLe LINK SATU" );
           }else if(cepr01030102Form.getRightPartOfBusinessCd() == 22){
           	if( currentCredentialsVO.getMsagId().equals( Hardcode.DMTM_MSAG_ID ) && currentCredentialsVO.getGroupId().equals( Hardcode.GROUP_DMTM ) ){
           		params.put( "title", "CERDAS" );
           	}else{
           		params.put( "title", "SIMAS POWER LINK" );
           	}
           }else if(cepr01030102Form.getRightPartOfBusinessCd() == 23){
           	params.put( "title", "SIMAS POWER LINK" );
           }else if(cepr01030102Form.getRightPartOfBusinessCd() == 24){
           	params.put( "title", "SIMAS POWER LINK" );
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
           	else if( cepr01030102Form.getEducationPackageCd().equals(Hardcode.EDUCATION_PAKET_SIMPOL_BSM) ) titlePackage = "(PAKET Education)";
           }
           
           if( cepr01030102Form.getProtectorPackageCd() != null ){
           	if( cepr01030102Form.getProtectorPackageCd().intValue() == 2 ) titlePackage = "(Protector GOLD)";
           	else if( cepr01030102Form.getProtectorPackageCd().intValue() == 3 ) titlePackage = "(Protector TITANIUM)";
           	else if( cepr01030102Form.getProtectorPackageCd().intValue() == 4 ) titlePackage = "(Protector PLATINUM)";
           }
           
           params.put( "titlePackage", titlePackage );
           params.put( "currencySymbol", currencySymbol );
           // disclaimer =========================================================================================================
           params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
           params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
           params.put( "agentName", cepr01030101Form.getProposalUser() );
           //=====================================================================================================================
           
           params.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
           
           BigDecimal alokasiBiayaAkuisisi = cepr01030102Form.getPaymentModeCd().intValue() == Hardcode.PAY_MODE_CD_SEKALIGUS ? cepr01030102Form.getBasePremium().multiply(new BigDecimal("0.05")): cepr01030102Form.getBasePremium().multiply(new BigDecimal("0.8"));
           
//           BigDecimal biayaAsuransiPokok = cepr01030102Form.getBasePremium().multiply(new BigDecimal("0.00094"));
           BigDecimal biayaAsuransiPokok =  new BigDecimal(cepr01040211Business.of_get_coi_basic(1));
           BigDecimal biayaAdministrasi = BigDecimal.ZERO;
           
//           if(cepr01030102Form.getPaymentModeCd() == 0){
//           	biayaAdministrasi = new BigDecimal("25000");
//           }else{
           	if("01".equals(cepr01030102Form.getCurrencyCd())){
           		biayaAdministrasi = new BigDecimal("15000");
               }else if("02".equals(cepr01030102Form.getCurrencyCd())){
               	biayaAdministrasi = new BigDecimal("20000");
               }
//           }
           
           if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
           {
           	biayaAdministrasi = biayaAdministrasi.divide(new BigDecimal("10000"));
           }
           
           int jenis = 0;
           
           if(cepr01030103Form.getHcpFamilyCd() != null){
           	jenis = cepr01030103Form.getHcpFamilyCd();
           	for(int i = 0 ; i < 20 ; i++){
               	if(cepr01030103Form.getHcpFamilyCd() == (i+1)){
               		String[][] mPlanHcp = {	{"R-100" , "100000" , "200000" , "200000" , "250000" , "500000" , "750000" , "1000000" },
       										{"R-200" , "200000" , "400000" , "400000" , "500000" , "1000000", "1500000", "2000000" },
       										{"R-300" , "300000" , "600000" , "600000" , "750000" , "1500000", "2250000", "3000000" },
       										{"R-400" , "400000" , "800000" , "800000" , "1000000", "2000000", "3000000", "4000000" },
       										{"R-500" , "500000" , "1000000", "1000000", "1250000", "2500000", "3750000", "5000000" },
       										{"R-600" , "600000" , "1200000", "1200000", "1500000", "3000000", "4500000", "6000000" },
       										{"R-700" , "700000" , "1400000", "1400000", "1750000", "3500000", "5250000", "7000000" },
       										{"R-800" , "800000" , "1600000", "1600000", "2000000", "4000000", "6000000", "8000000" },
       										{"R-900" , "900000" , "1800000", "1800000", "2250000", "4500000", "6750000", "9000000" },
       										{"R-1000", "1000000", "2000000", "2000000", "2500000", "5000000", "7500000", "10000000"},
       										{"D-10"  , "10"     , "20"     , "20"     , "25"     , "50"     , "75"     , "100"     },
       										{"D-20"  , "20"     , "40"     , "40"     , "40"     , "80"     , "120"    , "160"     },
       										{"D-30"  , "30"     , "60"     , "60"     , "75"     , "150"    , "225"    , "300"     },
       										{"D-40"  , "40"     , "80"     , "80"     , "80"     , "160"    , "240"    , "320"     },
       										{"D-50"  , "50"     , "100"    , "100"    , "100"    , "200"    , "300"    , "400"     },
       										{"D-60"  , "60"     , "120"    , "120"    , "120"    , "240"    , "360"    , "480"     },
       										{"D-70"  , "70"     , "140"    , "140"    , "140"    , "280"    , "420"    , "560"     },
       										{"D-80"  , "80"     , "160"    , "160"    , "160"    , "320"    , "480"    , "640"     },
       										{"D-90"  , "90"     , "180"    , "180"    , "180"    , "360"    , "540"    , "720"     },
       										{"D-100" , "100"    , "200"    , "200"    , "200"    , "400"    , "600"    , "800"     } };
               		
               		params.put( "jenisSantunan", mPlanHcp[i][0]);
               		params.put( "sub1", editorUtil.convertToRoundedNoDigit(new BigDecimal(mPlanHcp[i][1])));
               		params.put( "sub2", editorUtil.convertToRoundedNoDigit(new BigDecimal(mPlanHcp[i][2])));
               		params.put( "sub3", editorUtil.convertToRoundedNoDigit(new BigDecimal(mPlanHcp[i][3])));
               		params.put( "minor", editorUtil.convertToRoundedNoDigit(new BigDecimal(mPlanHcp[i][4])));
               		params.put( "intermediate", editorUtil.convertToRoundedNoDigit(new BigDecimal(mPlanHcp[i][5])));
               		params.put( "major", editorUtil.convertToRoundedNoDigit(new BigDecimal(mPlanHcp[i][6])));
               		params.put( "complex", editorUtil.convertToRoundedNoDigit(new BigDecimal(mPlanHcp[i][7])));
               		i=19;
               	}
               }
           }
           
           int no=0;
           List<ParticipantVO> participantVOList =  cepr01030106Form.getParticipantVOList();
           for(int i = 0 ; i < participantVOList.size() ; i++){
           	if(participantVOList.get(i).getName() != null && !"".equals(participantVOList.get(i).getName()) && participantVOList.get(i).getAge() != null && !"".equals(participantVOList.get(i).getAge())){
           		no=no+1;
           		params.put( "participantNo"+i, no+".");
           		params.put( "participantName"+i, participantVOList.get(i).getName());
                   params.put( "participantAge"+i, participantVOList.get(i).getAge().toString());
           	}
           }
           
           if(no > 0){
           	params.put( "participantExist", "yes");
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
           
           String illustrasiManfaat = "";
           if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEKALIGUS ){
           	illustrasiManfaat = "* Biaya akuisisi sebesar 5% dari Premi Sekaligus. Biaya Top up sebesar 5% dari Premi Top Up.";
           }else{
           	illustrasiManfaat = "* Biaya akuisisi sebesar 80% dari Premi Tahun ke-1, 15% untuk thn ke-2. Biaya Top up sebesar 5% dari Premi Top up.";
           }
           	
           params.put( "paymentMode", cepr01030102Form.getPaymentModeCd() );
           params.put( "illustrasiManfaat", illustrasiManfaat );
           params.put( "alokasiBiayaAkuisisi", editorUtil.convertToTwoDigit(alokasiBiayaAkuisisi) );
           params.put( "biayaAsuransiPokok", editorUtil.convertToTwoDigit(biayaAsuransiPokok) );
           params.put( "biayaAdministrasi", editorUtil.convertToTwoDigit(biayaAdministrasi) );
           params.put( "premiTopUpBerkala", editorUtil.convertToTwoDigit(premiTopUpBerkala) );
           params.put( "biayaTopUpBerkala", editorUtil.convertToTwoDigit(biayaTopUpBerkala) );
           
           
           BigDecimal premiSekaligus = cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount();
           BigDecimal biayaSekaligus = premiSekaligus.multiply(new BigDecimal("0.05"));
           if( cepr01030104Form.getTopupDrawVOList() == null ||
           	cepr01030104Form.getTopupDrawVOList() != null && cepr01030104Form.getTopupDrawVOList().size() == 0 ||
           	cepr01030104Form.getTopupDrawVOList().get(0) == null || 
           	cepr01030104Form.getTopupDrawVOList().get(0).getYearFlag() == null )
           {
           	premiSekaligus = BigDecimal.ZERO;
           	biayaSekaligus = BigDecimal.ZERO;
           }
           
           BigDecimal totalAlokasi = BigDecimal.ZERO;
           if( cepr01030102Form.getRightPartOfBusinessCd() >= 7 && cepr01030102Form.getRightPartOfBusinessCd() <= 9 ){
           	BigDecimal first = premiSekaligus.add( cepr01030102Form.getBasePremium() ).add( premiTopUpBerkala );
           	BigDecimal second = alokasiBiayaAkuisisi.add(biayaAsuransiPokok).add( biayaTopUpBerkala ).add( biayaAdministrasi ).add( biayaSekaligus );
           	totalAlokasi = first.subtract( second );
           }else{
           	totalAlokasi = cepr01030102Form.getBasePremium().subtract(alokasiBiayaAkuisisi).subtract(biayaAsuransiPokok).add(premiTopUpBerkala).subtract(biayaTopUpBerkala).subtract(biayaAdministrasi);
           }
           
           //params.put( "totalAlokasi", editorUtil.convertToTwoDigit(cepr01030102Form.getBasePremium().subtract(alokasiBiayaAkuisisi).subtract(biayaAsuransiPokok).add(premiTopUpBerkala).subtract(biayaTopUpBerkala).subtract(biayaAdministrasi)) );
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
           params.put( "uangPertanggunganPa", currencySymbol + " " + editorUtil.convertToTwoDigit(Math.min(up * 2, 2000000000.0)) );
           params.put( "uangPertanggunganPa2", currencySymbol + " " + editorUtil.convertToTwoDigit(Math.min(up,2000000000.0)) );
           params.put( "uangPertanggunganPa3", currencySymbol + " " + editorUtil.convertToTwoDigit(Math.min(up * 0.4,500000000.0)) );
           String tipeMedisPP = cepr01040211Business.getTipeMedis(cepr01030101Form.getPolicyHolderAge());
           String tipeMedisTT = cepr01040211Business.getTipeMedis(cepr01030101Form.getInsuredAge());
           params.put( "tipeMedisCalonTertanggung", "\""+tipeMedisTT+"\", jika belum punya polis di Sinarmas MSIG Life" );
           params.put( "tipeMedisCalonPemegangPolis", "\""+tipeMedisPP+"\", jika belum punya polis di Sinarmas MSIG Life" );
           
       //    jasperViewer.setReportDataKey( "dataSource" );
       //    jasperViewer.setJdbcDataSource( eproposalManager.getDataSource() );

           params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
           
           Map<String, Object> mapMaster = new HashMap<String, Object>();
          mapMaster = cepr01040211Business.getIllustration211Result(cepr01030102Form.getTermOfPayment());
           
       //   mapMaster = illustrationFormulaParent.getIllustrationResult(cepr01040211Business, editorUtil, cepr01030101Form, cepr01030102Form, cepr01030104Form, currentCredentialsVO, cepr01030102Form.getTermOfPayment());
           
           IllustrationResultVO illustrationResultVO = (IllustrationResultVO) mapMaster.get("Illustration1");
           IllustrationResultVO illustrationResultVO2 = (IllustrationResultVO) mapMaster.get("Illustration2");
           
           params.put( "validityMsg", illustrationResultVO.getValidityMsg() );
           params.put( "dsEkalinkIllustration", JasperReportsUtils.convertReportData( illustrationResultVO.getIllustrationList() ) );
           params.put( "dsEkalinkIllustration2", JasperReportsUtils.convertReportData( illustrationResultVO2.getIllustrationList() ) );
           params.put( "dsEkalinkIllustration3", JasperReportsUtils.convertReportData( illustrationResultVO.getIllustrationList() ) );
           params.put( "bisnis_no", cepr01030102Form.getRightPartOfBusinessCd() );
           params.put( "bisnis_id", cepr01030102Form.getLeftPartOfBusinessCd() );
           params.put( "riderCiPercentage", cepr01030103Form.getCiChooseCd()+"%"); 
           //int exr = 0;
           String[][] riderTambahan = cepr01040211Business.getRiderPremiumList( cepr01030102Form );
           BigDecimal totalRider = new BigDecimal(0);
           for(int i = 0 ; i < riderTambahan.length ; i++){
           	params.put("asuransiTambahan"+(i+1), riderTambahan[i][0]);
           	params.put("biayaAsuransiTambahan"+(i+1), riderTambahan[i][1]);
           	if( i==0 ){
           		totalRider = new BigDecimal(riderTambahan[i][1]);
           	}else if(riderTambahan[i][6] != null){
           		if(i >= 1 && i<= 6){
           			totalAlokasi = totalAlokasi.subtract(new BigDecimal(riderTambahan[i][6]));
           			//exr = exr+1;
           		}
           	}
           }
           
           if(cepr01030102Form.getRightPartOfBusinessCd() >= 3 ){
           	//params.put( "exr", "extraRider");
           }else{
           	params.put( "exr", null);
           }
           String titlePayor5Tpd10CiDeath = cepr01030102Form.getTermOfPayment().toString();
           String riderWaiver5Tpd10CiTitle = cepr01030102Form.getTermOfPayment().toString();
           String premiLabel = cepr01030102Form.getPaymentModeCd().intValue() == Hardcode.PAY_MODE_CD_SEKALIGUS ? riderTambahan[0][3] : riderTambahan[0][3] + "an";
           
           params.put( "riderLadiesInsTitle", MappingUtil.getLabel(cepr01030103Form.getLadiesInsList(), cepr01030103Form.getLadiesInsCd()));
           params.put( "titlePayor5Tpd10CiDeath", titlePayor5Tpd10CiDeath);
           params.put( "riderWaiver5Tpd10CiTitle", riderWaiver5Tpd10CiTitle);
           params.put( "totalAlokasi", editorUtil.convertToTwoDigit(totalAlokasi));
           params.put("premiPokokLabel", "Premi Pokok " + premiLabel);
           params.put("premiTopBerkalaLabel", "Premi Top Up Berkala " + premiLabel);
           params.put("biayaTopBerkalaLabel", "Biaya Top Up Berkala " + premiLabel);
           params.put("premiSekaligusLabel", "Premi Top Up Sekaligus tahun 1");
           params.put("biayaSekaligusLabel", "Biaya Top Up Sekaligus tahun 1");
           
           params.put( "bisnisNo", cepr01030102Form.getRightPartOfBusinessCd() );
           
           params.put("premiSekaligus", editorUtil.convertToTwoDigit(premiSekaligus) );
           params.put("biayaSekaligus", editorUtil.convertToTwoDigit(biayaSekaligus) );
          
           
           params.put("biayaAsuransiPokokLabel", "Biaya Asuransi Pokok Per" + riderTambahan[0][2]);
           params.put("BiayaAdministrasiLabel", "Biaya Administrasi Per" + riderTambahan[0][2]);
           
           params.put( "totalInvestmentAllocation", cepr01040211Business.getInvestmentYield().getTotalInvestmentAllocation() );
           params.put( "totalAssumptionLow", cepr01040211Business.getInvestmentYield().getTotalAssumptionLow() );
           params.put( "totalAssumptionMid", cepr01040211Business.getInvestmentYield().getTotalAssumptionMid() );
           params.put( "totalAssumptionHi", cepr01040211Business.getInvestmentYield().getTotalAssumptionHi() );

           params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( cepr01040211Business.getCommonHeaderRowList() ) );
           params.put( "dsCommonHeader2", JasperReportsUtils.convertReportData( cepr01040211Business.getCommonHeaderRowList() ) );
           params.put( "dsCommonHeader3", JasperReportsUtils.convertReportData( cepr01040211Business.getCommonHeaderRowList() ) );
           params.put( "dsCommonHeaderSmall", JasperReportsUtils.convertReportData( cepr01040211Business.getCommonHeaderSmallRowList() ) );
           params.put( "dsCommonHeaderSmall3", JasperReportsUtils.convertReportData( cepr01040211Business.getCommonHeaderSmallRowList() ) );
           params.put( "dsInvestmentChoice", JasperReportsUtils.convertReportData( cepr01040211Business.getInvestmentChoiceRowList() ) );
                      
           params.put( "dsInvestmentChoiceSmall", JasperReportsUtils.convertReportData( cepr01040211Business.getInvestmentChoiceSmallRowList() ) );
           params.put( "dsInvestmentChoiceSmall3", JasperReportsUtils.convertReportData( cepr01040211Business.getInvestmentChoiceSmallRowList() ) );
           
           //params.put( "dsMonthlyPremium", JasperReportsUtils.convertReportData( cepr01040114Business.getMonthlyPremiumList( cepr01030102Form ) ) );
           //params.put( "dsInvestmentPerformance", JasperReportsUtils.convertReportData( cepr01040201Business.getInvestmentPerformanceList() ) );
           
           //Adrian@12/05/2014 => SIMPOL terdiri dari 6 Pilihan Investasi: EXCELLINK & Simas Fund
           //params.put( "dsInvestmentYield", JasperReportsUtils.convertReportData( cepr01040211Business.getInvestmentYield().getYieldList() ) );            
           params.put( "dsInvestmentYield", JasperReportsUtils.convertReportData( cepr01040211Business.of_get_rate_ds().getYieldList()  ) );
           params.put( "dsInvestmentYield3", JasperReportsUtils.convertReportData( cepr01040211Business.of_get_rate_ds().getYieldList()  ) );
           
           //params.put( "dsOneRow", JasperReportsUtils.convertReportData( cepr01040201Business.getOneRowList() ) );
           //params.put( "dsNoteSekaligus", JasperReportsUtils.convertReportData( cepr01040201Business.getNoteSekaligusRowList() ) );
           //params.put( "dsNoteBerkala", JasperReportsUtils.convertReportData( cepr01040201Business.getNoteBerkalaRowList() ) );
           
           params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
           params.put( "agentName", cepr01030101Form.getProposalUser() );
           params.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
           
           params.put( "dsCommonHeaderRincian", JasperReportsUtils.convertReportData( cepr01040211Business.getCommonHeaderRincianRowList() ) );
           params.put( "dsCommonBiayaRincian", JasperReportsUtils.convertReportData( cepr01040211Business.getCommonBiayaRincianRowList( biayaAsuransiPokok ) ) );
           
           if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X22 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X23 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X24 ||
           		 cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X10 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X11 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X12 	)
               {
           	params.put( "riderPaTitle", "Manfaat SMiLe Personal Accident Rider (PA) " );
           	params.put( "riderPaTitleSummary", "SMiLe Personal Accident Rider" );
           	params.put( "riderHcpFamilySummaryTitle", "Hospital Cash Plan (Family)");
	            if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X22 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X10 ){
	            	params.put( "riderWaiver10CiTitle", "Manfaat Waiver 5 CI - Rider" );
	            	params.put( "riderTpd10Title", "Manfaat Payor 5 TPD/Death - Rider" );
	            	params.put( "riderWaiver10TpdTitle", "Manfaat Waiver 5 TPD - Rider" );
	            	params.put( "riderPayorCi10Title", "Manfaat Payor 5 CI - Rider" );
	            }else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X23 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X11 ){
	            	params.put( "riderWaiver10CiTitle", "Manfaat Waiver 10 CI - Rider" );
	            	params.put( "riderTpd10Title", "Manfaat Payor 10 TPD/Death - Rider" );
	            	params.put( "riderWaiver10TpdTitle", "Manfaat Waiver 10 TPD - Rider" );
	            	params.put( "riderPayorCi10Title", "Manfaat Payor 10 CI - Rider" );
	            }
           }
           	

//           	riderWaiver10CiTitle
           currentIdx = 0;
           Cepr00000000RiderBusiness riderBusiness = new Cepr00000000RiderBusiness( eproposalManager, editorUtil, cepr01030000HoldingForm );
           putRiderParam( params, riderBusiness.getRiderPaList(), "dsRiderPa", "riderPaIndex" );
           params.put( "riderPaRisk", riderBusiness.getRiderPaRisk() );
           params.put( "dsRiderPaClone", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderPaList() ) );
           //putRiderParam( params, riderBusiness.getRiderHcpList(), "dsRiderHcp", "riderHcpIndex" );
           putRiderParam( params, riderBusiness.getRiderHcpList(), "dsRiderHcp", "riderHcpIndex" );
           putRiderParam( params, riderBusiness.getRiderHcpFamilyList(), "dsRiderHcpFamily", "riderHcpFamilyIndex" );
           putRiderParam( params, riderBusiness.getRiderHcpLadiesList(), "dsRiderHcpLadies", "riderHcpLadiesIndex" );
           putRiderParam( params, riderBusiness.getRiderPayorTpdCiDeathList(), "dsRiderPayorTpdCiDeath", "riderPayorTpdCiDeathIndex" );
           putRiderParam( params, riderBusiness.getRiderPayor5Tpd10CiDeathList(), "dsRiderPayor5Tpd10CiDeath", "riderPayor5Tpd10CiDeathIndex" );
           putRiderParam( params, riderBusiness.getRiderWaiver5Tpd10CiList(), "dsRiderWaiver5Tpd10Ci", "riderWaiver5Tpd10CiIndex" );
           putRiderParam( params, riderBusiness.getRiderWaiver10CiList(), "dsRiderWaiver10Ci", "riderWaiver10CiIndex" );
           putRiderParam( params, riderBusiness.getRiderPayorTpdDeathList(), "dsRiderPayorTpdDeath", "riderPayorTpdDeathIndex" );
           putRiderParam( params, riderBusiness.getRiderPayorCiList(), "dsRiderPayorCi", "riderPayorCiIndex" );
           putRiderParam( params, riderBusiness.getRiderPayorCiDeathList(), "dsRiderPayorCiDeath", "riderPayorCiDeathIndex" );
           putRiderParam( params, riderBusiness.getRiderPayorSpouseTpdDeathList(), "dsRiderPayorSpouseTpdDeath", "riderPayorSpouseTpdDeathIndex" );
           putRiderParam( params, riderBusiness.getRiderLadiesInsList(), "dsRiderLadiesIns", "riderLadiesInsIndex" );
           putRiderParam( params, riderBusiness.getRiderWaiverTpdCiList(), "dsRiderWaiverTpdCi", "riderWaiverTpdCiIndex" );
           putRiderParam( params, riderBusiness.getRiderHcpProviderList(), "dsRiderHcpProvider", "riderHcpProviderIndex" );
           putRiderParam( params, riderBusiness.getRiderTpdList(), "dsRiderTpd", "riderTpdIndex" );
           putRiderParam( params, riderBusiness.getRiderCiList(), "dsRiderCi", "riderCiIndex" );
           putRiderParam( params, riderBusiness.getRiderTermRiderList(), "dsRiderTermRider", "riderTermRiderIndex" );
           putRiderParam( params, riderBusiness.getRiderEkaSehatListMap(), "dsRiderEkaSehat", "riderEkaSehatIndex" );
           putRiderParam( params, riderBusiness.getRiderEkaSehatInnerLimitListMap(), "dsRiderEkaSehatInnerLimit", "riderEkaSehatInnerLimitIndex" );
           putRiderParam( params, riderBusiness.getRiderLadiesMedExpenseListMap(), "dsRiderLadiesMedExpense", "riderLadiesMedExpenseIndex" );
           putRiderParam( params, riderBusiness.getRiderLadiesMedExpenseInnerLimitListMap(), "dsRiderLadiesMedExpenseInnerLimit", "riderLadiesMedExpenseInnerLimitIndex" );
           putRiderParam( params, riderBusiness.getRiderTpd10List(), "dsRiderTpd10", "riderTpd10Index" );
           putRiderParam( params, riderBusiness.getRiderPayor10CiList(), "dsRiderPayor10Ci", "riderPayorCi10Index" );
           putRiderParam( params, riderBusiness.getRiderWaiver10TpdList(), "dsRiderWaiver10Tpd", "riderWaiver10TpdIndex" );
           putRiderParam( params, riderBusiness.getRiderScholarRiderList(), "dsRiderScholarRider", "riderScholarRiderIndex" );
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
           params.put( "dsRiderHcpFamilyParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderHcpFamilyParticipantsList() ) );
           params.put( "baseBenefitHcpFam", riderBusiness.computeBaseBenefitHcpFam() );
           params.put( "baseBenefitHcpLad", riderBusiness.computeBaseBenefitHcpLadies() );
           params.put( "dsRiderHcpLadiesParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderHcpLadiesParticipantsList() ) );
           params.put( "participantEkaSehatInnerLimit", riderBusiness.checkParticapantEkaSehatInnerLimit() );
           params.put( "dsRiderEkaSehatInnerLimitParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderEkaSehatInnerLimitParticipantsList() ) );
           params.put( "dsRiderHcpProviderParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderHcpProviderParticipantsList() ) );
           params.put( "baseBenefitHcpPro", riderBusiness.computeBaseBenefitHcpPro() );
           params.put( "dsEkalinkRider", JasperReportsUtils.convertReportData( cepr01040211Business.getRiderPremiumList(cepr01030102Form) ) );
           params.put( "participantEkaSehat", riderBusiness.checkParticapantEkaSehat() );
           params.put( "dsRiderEkaSehatParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderEkaSehatParticipantsList() ) );
           params.put( "dsRiderLadiesMedExpenseParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesMedExpenseParticipantsList()) );
           params.put( "dsRiderLadiesMedExpenseInnerLimitParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderLadiesMedExpenseInnerLimitParticipantsList()) );
           params.put( "dsRiderMedicalPlusParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderMedicalPlusParticipantsList()) );
           params.put( "participantMedicalPlus", riderBusiness.checkParticapantMedicalPlus() );       
           params.put( "jenis", cepr01030102Form.getRightPartOfBusinessCd()+"");
           String mappingTitleWaiverTpdCi = MappingUtil.getLabel(cepr01030103Form.getWaiverTpdCiChooseList(), cepr01030103Form.getWaiverTpdCiChooseCd());
           params.put( "riderWaiverTpdCiTitle", mappingTitleWaiverTpdCi );
           params.put( "dayPolicy", "180" );
           params.put( "wordingWaiver10Tpd", "setelah 180 hari Polis diberlakukan");
           params.put( "wordingWaiver10Ci", "setelah 180 hari Polis diberlakukan" );
           
           if(cepr01030103Form.getPaRiskCd() != null){
           	params.put( "paRisk", cepr01030103Form.getPaRiskCd().toString());
           	if("1".equals(cepr01030103Form.getPaRiskCd().toString())){
           		params.put( "paRiskLabel1", "Resiko A" );
               	params.put( "paRiskLabel2", "(PA Risiko A)" );
           	}else if("2".equals(cepr01030103Form.getPaRiskCd().toString())){
           		params.put( "paRiskLabel1", "Resiko A+B" );
               	params.put( "paRiskLabel2", "(PA Risiko A+B)" );
           	}else if("3".equals(cepr01030103Form.getPaRiskCd().toString())){
           		params.put( "paRiskLabel1", "Resiko A+B+D" );
               	params.put( "paRiskLabel2", "(PA Risiko A+B+D)" );
           	}
           }
           
           params.put("pageRider", cepr01040211Business.getPageRider( currentIdx ));
           params.put( "dsCommonHeaderRincianRiderHCPFamily", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040211Business.getCommonHeaderRincianSmallRowList(11) ) );
           params.put( "dsCommonBiayaRincianRiderHCPFamily", JasperReportsUtils.convertReportData( cepr01040211Business.getCommonBiayaRincianRider(riderTambahan, 11) ) );     
           params.put( "dsRincianRiderHCPFamily", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpFamilyList() ));
           params.put( "dsRincianRiderHcpFamilyParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRiderHcpFamilyParticipantsList() ) );
           
           params.put( "dsCommonHeaderRincianRiderTerm", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040211Business.getCommonHeaderRincianSmallRowList(12) ) );
           params.put( "dsCommonBiayaRincianRiderTerm", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040211Business.getCommonBiayaRincianRider(riderTambahan, 12) ) );     
           params.put( "dsCommonHeaderRincianRiderTpd", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040211Business.getCommonHeaderRincianSmallRowList(3) ) );
           params.put( "dsCommonBiayaRincianRiderTpd", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040211Business.getCommonBiayaRincianRider(riderTambahan, 3) ) ); 
           params.put( "dsCommonHeaderRincianRiderCi", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040211Business.getCommonHeaderRincianSmallRowList(4) ) );
           params.put( "dsCommonBiayaRincianRiderCi", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040211Business.getCommonBiayaRincianRider(riderTambahan, 4) ) ); 
           params.put( "dsCommonHeaderRincianRiderPa", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040211Business.getCommonHeaderRincianSmallRowList(1) ) );
           params.put( "dsCommonBiayaRincianRiderPa", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040211Business.getCommonBiayaRincianRider(riderTambahan, 1) ) ); 
           params.put( "dsCommonHeaderRincianRiderEkaSehat", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040211Business.getCommonHeaderRincianSmallRowList(13) ) );
           params.put( "dsCommonBiayaRincianRiderEkaSehat", JasperReportsUtils.convertReportData( cepr01040211Business.getCommonBiayaRincianRider(riderTambahan, 13) ) );     
           params.put( "dsRincianRiderEkaSehat", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRincianRiderEkaSehat() ));
           params.put( "dsRincianRiderEkaSehatParticipants", JasperReportsUtils.convertReportData( riderBusiness.getRincianRiderEkaSehatParticipantsList() ) );
          
           params.put( "dsCommonHeaderRincianRiderPayorTpd10", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040211Business.getCommonHeaderRincianSmallRowList(6) ) );
           params.put( "dsCommonBiayaRincianRiderPayorTpd10", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040211Business.getCommonBiayaRincianRider(riderTambahan, 6) ) ); 
           params.put( "dsCommonHeaderRincianRiderPayorCi10", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040211Business.getCommonHeaderRincianSmallRowList(9) ) );
           params.put( "dsCommonBiayaRincianRiderPayorCi10", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040211Business.getCommonBiayaRincianRider(riderTambahan, 9) ) );
           params.put( "dsCommonHeaderRincianRiderWaiverTpd10", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040211Business.getCommonHeaderRincianSmallRowList(5) ) );
           params.put( "dsCommonBiayaRincianRiderWaiverTpd10", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040211Business.getCommonBiayaRincianRider(riderTambahan, 5) ) );            
           params.put( "dsCommonHeaderRincianRiderWaiverCi10", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040211Business.getCommonHeaderRincianSmallRowList(7) ) );
           params.put( "dsCommonBiayaRincianRiderWaiverCi10", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040211Business.getCommonBiayaRincianRider(riderTambahan, 7) ) );
           params.put( "dsCommonHeaderRincianRiderEarlyStageCi99", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040211Business.getCommonHeaderRincianSmallRowList(27) ) );
           params.put( "dsCommonBiayaRincianRiderEarlyStageCi99", JrUtil.convertToDsAndSetNullIfEmpty( cepr01040211Business.getCommonBiayaRincianRider(riderTambahan, 27) ) );
           
           //manfaat
           //manfaat max 100%
           params.put( "manfaat100p", currencySymbol + " " + editorUtil.convertToTwoDigit(Math.min(up , 1000000000.0)) );
           //manfaat max 50%
           params.put( "manfaat50p", currencySymbol + " " + editorUtil.convertToTwoDigit(Math.min(up/2, 500000000.0)) );
           //manfaat max 10%
           params.put( "manfaat10p", currencySymbol + " " + editorUtil.convertToTwoDigit(Math.min(up/10, 100000000.0)) );
           
           params.put( "dataSource", cepr01040211Business.getPageList( currentIdx ));

           // Save E-Proposal PDF ke EBServer dan PopUp Download
           // ** Adrian@17/10/2013            
           String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
           String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
           Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();
         //  String fileName="testx.pdf";
           String fileName = msag_id+"_"+no_proposal+".pdf";
           params.put( "no_proposal", no_proposal );
           
           List temp = cepr01040211Business.getPageList( currentIdx );
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
                "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040211_product.jasper",
                 dirName,
                  fileName,
                 params,
                 temp,
                PdfWriter.AllowPrinting,
               null,
              null
               );
//                                                   JasperUtils.downloadReportPDF(response, dirName, fileName);                        
               //}
             }catch( Exception e )
              {
            	 logger.error("ERROR", e);                    
              } 
 //          jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
          result.put("dirName", dirName);
          result.put("fileName", fileName);
          result.put("messageBoxList", null);
          result.put("messageEkaSehatList", null);
     //     result.put("jasperViewer", jasperViewer);
          result.put("params", params);
//        request.getSession().setAttribute( "messageBoxList", null );
//        request.getSession().setAttribute( "messageEkaSehatList", null );
//        result = new ModelAndView( jasperViewer, params );
    	
          }
          return result;
    }
    
}