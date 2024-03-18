package id.co.sinarmaslife.eproposal.product.product01040111;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040111DownloadController
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Apr 18, 2008 2:36:13 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import com.lowagie.text.pdf.PdfWriter;
import id.co.sinarmaslife.eproposal.business.Cepr00000000RiderBusiness;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
import id.co.sinarmaslife.eproposal.model.vo.ParticipantVO;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030103Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030106Form;
import id.co.sinarmaslife.standard.util.*;
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
import java.util.ArrayList;
import java.util.Properties;

public class Cepr01040111DownloadController extends StandardParent implements Controller
{
    static Integer currentIdx;
    protected final Log logger = LogFactory.getLog( getClass() );

    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040111DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01030103Form cepr01030103Form = cepr01030000HoldingForm.getCepr01030103Form();
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01030106Form cepr01030106Form = cepr01030000HoldingForm.getCepr01030106Form();
        String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
        Cepr01040111Business cepr01040111Business = new Cepr01040111Business( eproposalManager, editorUtil, command );

        ModelAndView result;
        if( request.getSession().getAttribute( "messageBoxList" ) == null )
        {
            List<String> messageBoxList = new ArrayList<String>();
            messageBoxList.addAll( ListUtil.addNothingIfEmpty( "" ) );

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
            if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040111Mapper.X11 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040111Mapper.X12  ){
            	jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040111_new_product.jasper" );
            }else{
            	jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040111_product.jasper" );
            }
            

            String kursLabel="";
            String kursId="";
            
            if("01".equals(cepr01030102Form.getCurrencyCd())){
            	kursLabel = "Rp ";
            	kursId = "01";
            }else if("02".equals(cepr01030102Form.getCurrencyCd())){
            	kursLabel = "US$ ";
            	kursId = "02";
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
            
            
            params.put( "termRiderManfaat", kursLabel + editorUtil.convertToRoundedNoDigit(cepr01030102Form.getTotalSumInsured()));
            params.put( "ciRiderManfaat", kursLabel + editorUtil.convertToRoundedNoDigit(cepr01030102Form.getTotalSumInsured().multiply(new BigDecimal(0.5))));
            
            Map ciRider;
            Map termRider;
            Map hcpFamilyRider;
            Map paRider;
            Map hcpRider;
            Map tpdRider;
            Map ekaSehatRider;
            Map ekaSehatIlRider;
            Map ladiesInsRider;
            Map hcpLadRider;
            Map ladiesMedRider;
            Map hcpProRider;
            Map ladiesMedIlRider;
            BigDecimal premiTotalSekaligus = new BigDecimal(0);
            BigDecimal premiTotalTahunan = new BigDecimal(0);
            BigDecimal premiTotalSemesteran = new BigDecimal(0);
            BigDecimal premiTotalTriwulanan = new BigDecimal(0);
            BigDecimal premiTotalBulanan = new BigDecimal(0);
            JRDataSource source = null;
            Cepr00000000RiderBusiness riderBusiness = new Cepr00000000RiderBusiness( eproposalManager, editorUtil, command );
            ciRider = cepr01040111Business.of_get_coi_ci(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            termRider = cepr01040111Business.of_get_coi_term(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            paRider = cepr01040111Business.of_get_coi_pa(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            hcpRider = cepr01040111Business.of_get_coi_hcp(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            tpdRider = cepr01040111Business.of_get_coi_tpd(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            ekaSehatRider = cepr01040111Business.of_get_coi_eka_sehat(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            ekaSehatIlRider = cepr01040111Business.of_get_coi_eka_sehat_il(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            ladiesInsRider = cepr01040111Business.of_get_coi_ladies_ins(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            hcpLadRider = cepr01040111Business.of_get_coi_hcp_lad(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            ladiesMedRider = cepr01040111Business.of_get_coi_ladies_med(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            ladiesMedIlRider = cepr01040111Business.of_get_coi_ladies_med_il(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            hcpProRider = cepr01040111Business.of_get_coi_hcp_pro(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            if(jenis != 0){
            	source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpFamilyList() );
                if( source != null ){
	            	hcpFamilyRider = cepr01040111Business.of_get_coi_hcp_family(jenis, kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
	            	params.put( "premiHcpFamilySekaligus", hcpFamilyRider.get("hcpFamilyRiderSekaligus").toString() );
	                params.put( "premiHcpFamilyTahunan", hcpFamilyRider.get("hcpFamilyRiderTahunan").toString() );
	                params.put( "premiHcpFamilySemesteran", hcpFamilyRider.get("hcpFamilyRiderSemesteran").toString() );
	                params.put( "premiHcpFamilyTriwulanan", hcpFamilyRider.get("hcpFamilyRiderTriwulanan").toString() );
	                params.put( "premiHcpFamilyBulanan", hcpFamilyRider.get("hcpFamilyRiderBulanan").toString() );
	                premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(hcpFamilyRider.get("premiTotalSekaligus").toString()));
	                premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(hcpFamilyRider.get("premiTotalTahunan").toString()));
	                premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(hcpFamilyRider.get("premiTotalSemesteran").toString()));
	                premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(hcpFamilyRider.get("premiTotalTriwulanan").toString()));
	                premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(hcpFamilyRider.get("premiTotalBulanan").toString()));
                }
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderCiList() );
            if( source != null ){
	            params.put( "premiCiSekaligus", ciRider.get("ciRiderSekaligus").toString() );
	            params.put( "premiCiTahunan", ciRider.get("ciRiderTahunan").toString() );
	            params.put( "premiCiSemesteran", ciRider.get("ciRiderSemesteran").toString() );
	            params.put( "premiCiTriwulanan", ciRider.get("ciRiderTriwulanan").toString() );
	            params.put( "premiCiBulanan", ciRider.get("ciRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(ciRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(ciRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(ciRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(ciRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(ciRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderTermRiderList() );
            if( source != null ){
	            params.put( "premiTermSekaligus", termRider.get("termRiderSekaligus").toString() );
	            params.put( "premiTermTahunan", termRider.get("termRiderTahunan").toString() );
	            params.put( "premiTermSemesteran", termRider.get("termRiderSemesteran").toString() );
	            params.put( "premiTermTriwulanan", termRider.get("termRiderTriwulanan").toString() );
	            params.put( "premiTermBulanan", termRider.get("termRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(termRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(termRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(termRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(termRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(termRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderPaList() );
            if( source != null ){
	            params.put( "premiPaSekaligus", paRider.get("paRiderSekaligus").toString() );
	            params.put( "premiPaTahunan", paRider.get("paRiderTahunan").toString() );
	            params.put( "premiPaSemesteran", paRider.get("paRiderSemesteran").toString() );
	            params.put( "premiPaTriwulanan", paRider.get("paRiderTriwulanan").toString() );
	            params.put( "premiPaBulanan", paRider.get("paRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(paRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(paRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(paRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(paRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(paRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpList() );
            if( source != null ){
	            params.put( "premiHcpSekaligus", hcpRider.get("hcpRiderSekaligus").toString() );
	            params.put( "premiHcpTahunan", hcpRider.get("hcpRiderTahunan").toString() );
	            params.put( "premiHcpSemesteran", hcpRider.get("hcpRiderSemesteran").toString() );
	            params.put( "premiHcpTriwulanan", hcpRider.get("hcpRiderTriwulanan").toString() );
	            params.put( "premiHcpBulanan", hcpRider.get("hcpRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(hcpRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(hcpRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(hcpRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(hcpRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(hcpRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderTpdList() );
            if( source != null ){
	            params.put( "premiTpdSekaligus", tpdRider.get("tpdRiderSekaligus").toString() );
	            params.put( "premiTpdTahunan", tpdRider.get("tpdRiderTahunan").toString() );
	            params.put( "premiTpdSemesteran", tpdRider.get("tpdRiderSemesteran").toString() );
	            params.put( "premiTpdTriwulanan", tpdRider.get("tpdRiderTriwulanan").toString() );
	            params.put( "premiTpdBulanan", tpdRider.get("tpdRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(tpdRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(tpdRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(tpdRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(tpdRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(tpdRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderEkaSehatListMap() );
            if( source != null ){
	            params.put( "premiEkaSehatSekaligus", ekaSehatRider.get("ekaSehatRiderSekaligus").toString() );
	            params.put( "premiEkaSehatTahunan", ekaSehatRider.get("ekaSehatRiderTahunan").toString() );
	            params.put( "premiEkaSehatSemesteran", ekaSehatRider.get("ekaSehatRiderSemesteran").toString() );
	            params.put( "premiEkaSehatTriwulanan", ekaSehatRider.get("ekaSehatRiderTriwulanan").toString() );
	            params.put( "premiEkaSehatBulanan", ekaSehatRider.get("ekaSehatRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(ekaSehatRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(ekaSehatRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(ekaSehatRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(ekaSehatRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(ekaSehatRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderEkaSehatInnerLimitListMap() );
            if( source != null ){
	            params.put( "premiEkaSehatIlSekaligus", ekaSehatIlRider.get("ekaSehatIlRiderSekaligus").toString() );
	            params.put( "premiEkaSehatIlTahunan", ekaSehatIlRider.get("ekaSehatIlRiderTahunan").toString() );
	            params.put( "premiEkaSehatIlSemesteran", ekaSehatIlRider.get("ekaSehatIlRiderSemesteran").toString() );
	            params.put( "premiEkaSehatIlTriwulanan", ekaSehatIlRider.get("ekaSehatIlRiderTriwulanan").toString() );
	            params.put( "premiEkaSehatIlBulanan", ekaSehatIlRider.get("ekaSehatIlRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(ekaSehatIlRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(ekaSehatIlRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(ekaSehatIlRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(ekaSehatIlRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(ekaSehatIlRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderLadiesInsList() );
            if( source != null ){
	            params.put( "premiLadiesInsSekaligus", ladiesInsRider.get("ladiesInsRiderSekaligus").toString() );
	            params.put( "premiLadiesInsTahunan", ladiesInsRider.get("ladiesInsRiderTahunan").toString() );
	            params.put( "premiLadiesInsSemesteran", ladiesInsRider.get("ladiesInsRiderSemesteran").toString() );
	            params.put( "premiLadiesInsTriwulanan", ladiesInsRider.get("ladiesInsRiderTriwulanan").toString() );
	            params.put( "premiLadiesInsBulanan", ladiesInsRider.get("ladiesInsRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(ladiesInsRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(ladiesInsRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(ladiesInsRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(ladiesInsRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(ladiesInsRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpLadiesList() );
            if( source != null ){
	            params.put( "premiHcpLadSekaligus", hcpLadRider.get("hcpLadRiderSekaligus").toString() );
	            params.put( "premiHcpLadTahunan", hcpLadRider.get("hcpLadRiderTahunan").toString() );
	            params.put( "premiHcpLadSemesteran", hcpLadRider.get("hcpLadRiderSemesteran").toString() );
	            params.put( "premiHcpLadTriwulanan", hcpLadRider.get("hcpLadRiderTriwulanan").toString() );
	            params.put( "premiHcpLadBulanan", hcpLadRider.get("hcpLadRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(hcpLadRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(hcpLadRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(hcpLadRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(hcpLadRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(hcpLadRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderLadiesMedExpenseListMap() );
            if( source != null ){
	            params.put( "premiLadiesMedSekaligus", ladiesMedRider.get("ladiesMedRiderSekaligus").toString() );
	            params.put( "premiLadiesMedTahunan", ladiesMedRider.get("ladiesMedRiderTahunan").toString() );
	            params.put( "premiLadiesMedSemesteran", ladiesMedRider.get("ladiesMedRiderSemesteran").toString() );
	            params.put( "premiLadiesMedTriwulanan", ladiesMedRider.get("ladiesMedRiderTriwulanan").toString() );
	            params.put( "premiLadiesMedBulanan", ladiesMedRider.get("ladiesMedRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(ladiesMedRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(ladiesMedRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(ladiesMedRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(ladiesMedRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(ladiesMedRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderLadiesMedExpenseInnerLimitListMap() );
            if( source != null ){
	            params.put( "premiLadiesMedIlSekaligus", ladiesMedIlRider.get("ladiesMedIlRiderSekaligus").toString() );
	            params.put( "premiLadiesMedIlTahunan", ladiesMedIlRider.get("ladiesMedIlRiderTahunan").toString() );
	            params.put( "premiLadiesMedIlSemesteran", ladiesMedIlRider.get("ladiesMedIlRiderSemesteran").toString() );
	            params.put( "premiLadiesMedIlTriwulanan", ladiesMedIlRider.get("ladiesMedIlRiderTriwulanan").toString() );
	            params.put( "premiLadiesMedIlBulanan", ladiesMedIlRider.get("ladiesMedIlRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(ladiesMedIlRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(ladiesMedIlRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(ladiesMedIlRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(ladiesMedIlRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(ladiesMedIlRider.get("premiTotalBulanan").toString()));
            }
            
            source = JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderHcpProviderList() );
            if( source != null ){
	            params.put( "premiHcpProSekaligus", hcpProRider.get("hcpProRiderSekaligus").toString() );
	            params.put( "premiHcpProTahunan", hcpProRider.get("hcpProRiderTahunan").toString() );
	            params.put( "premiHcpProSemesteran", hcpProRider.get("hcpProRiderSemesteran").toString() );
	            params.put( "premiHcpProTriwulanan", hcpProRider.get("hcpProRiderTriwulanan").toString() );
	            params.put( "premiHcpProBulanan", hcpProRider.get("hcpProRiderBulanan").toString() );
	            premiTotalSekaligus = premiTotalSekaligus.add(new BigDecimal(hcpProRider.get("premiTotalSekaligus").toString()));
	            premiTotalTahunan = premiTotalTahunan.add(new BigDecimal(hcpProRider.get("premiTotalTahunan").toString()));
	            premiTotalSemesteran = premiTotalSemesteran.add(new BigDecimal(hcpProRider.get("premiTotalSemesteran").toString()));
	            premiTotalTriwulanan = premiTotalTriwulanan.add(new BigDecimal(hcpProRider.get("premiTotalTriwulanan").toString()));
	            premiTotalBulanan = premiTotalBulanan.add(new BigDecimal(hcpProRider.get("premiTotalBulanan").toString()));
            }
            
            
            if("01".equals(kursId))
            {
            	params.put( "premiTotalSekaligus", editorUtil.convertToRoundedNoDigit(premiTotalSekaligus )+".00");
            	params.put( "premiTotalTahunan", editorUtil.convertToRoundedNoDigit(premiTotalTahunan )+".00");
            }else if("02".equals(kursId))
            {
            	params.put( "premiTotalSekaligus", editorUtil.convertToRoundedTwoDigits(premiTotalSekaligus ));
            	params.put( "premiTotalTahunan", editorUtil.convertToRoundedTwoDigits(premiTotalTahunan ));
            }
            params.put( "premiTotalSemesteran", editorUtil.convertToRoundedNoDigit(premiTotalSemesteran)+".00" );
            params.put( "premiTotalTriwulanan", editorUtil.convertToRoundedNoDigit(premiTotalTriwulanan)+".00" );
            params.put( "premiTotalBulanan", editorUtil.convertToRoundedNoDigit(premiTotalBulanan)+".00" );
            
            int no = 0;
            List<ParticipantVO> participantVOList =  cepr01030106Form.getParticipantVOList();
            for(int i = 0 ; i < participantVOList.size() ; i++){
            	if(participantVOList.get(i).getName() != null && !"".equals(participantVOList.get(i).getName()) && participantVOList.get(i).getAge() != null && !"".equals(participantVOList.get(i).getAge())){
            		no++;
            		params.put( "participantName"+i, participantVOList.get(i).getName());
                    params.put( "participantAge"+i, participantVOList.get(i).getAge().toString());
            	}
            }
            
            if(no > 0){
            	params.put( "participantExist", "yes");
            }
            
            String productLabel = "STABLE LINK";
            String subproductLabel = "";
            if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040111Mapper.X11 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040111Mapper.X12 ){
            	params.put( "jenis", "bukan manfaat bulanan" );
            	productLabel = "STABLE LINK";
                params.put( "dsInvestmentChoice", JasperReportsUtils.convertReportData( cepr01040111Business.getInvestmentNewChoiceRowList() ) );
                params.put( "dsInvestmentChoiceSmall", JasperReportsUtils.convertReportData( cepr01040111Business.getInvestmentNewChoiceSmallRowList() ) );
            }else{
            	if(cepr01030102Form.getJenisCd() == 0){
                	params.put( "jenis", "bukan manfaat bulanan" );
                	params.put( "subTitle", "(Bukan Manfaat Bulanan)" );
                	subproductLabel = "-Bukan Manfaat Bulanan";
                }else if(cepr01030102Form.getJenisCd() == 1){
                	params.put( "jenis", "manfaat bulanan" );
                	params.put( "subTitle", "(Manfaat Bulanan)" );
                	subproductLabel = "-Manfaat Bulanan";
                }
                params.put( "dsInvestmentChoice", JasperReportsUtils.convertReportData( cepr01040111Business.getInvestmentChoiceRowList() ) );
                params.put( "dsInvestmentChoiceSmall", JasperReportsUtils.convertReportData( cepr01040111Business.getInvestmentChoiceSmallRowList() ) );
            }
            

            params.put( "title", productLabel );
            params.put( "currencySymbol", currencySymbol );
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
//            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            params.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
            params.put( "bisnis_id",  cepr01030102Form.getLeftPartOfBusinessCd() );
            params.put( "bisnis_no",  cepr01030102Form.getRightPartOfBusinessCd() );
            jasperViewer.setReportDataKey( "dataSource" );
            jasperViewer.setJdbcDataSource( eproposalManager.getDataSource() );
            
            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
            IllustrationResultVO illustrationResultVO = new IllustrationResultVO();
            if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040111Mapper.X11 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040111Mapper.X12 ){
            	illustrationResultVO = cepr01040111Business.getIllustrationNewResult();
            }else{
            	illustrationResultVO = cepr01040111Business.getIllustrationResult();
            }
            params.put( "validityMsg", illustrationResultVO.getValidityMsg() );
            params.put( "dsStableLinkIllustration", JasperReportsUtils.convertReportData( illustrationResultVO.getIllustrationList() ) );

            params.put( "totalInvestmentAllocation", cepr01040111Business.getInvestmentYield().getTotalInvestmentAllocation() );
            params.put( "totalAssumptionLow", cepr01040111Business.getInvestmentYield().getTotalAssumptionLow() );
            params.put( "totalAssumptionMid", cepr01040111Business.getInvestmentYield().getTotalAssumptionMid() );
            params.put( "totalAssumptionHi", cepr01040111Business.getInvestmentYield().getTotalAssumptionHi() );

            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( cepr01040111Business.getCommonHeaderRowList() ) );
            params.put( "dsCommonHeaderSmall", JasperReportsUtils.convertReportData( cepr01040111Business.getCommonHeaderSmallRowList() ) );
            params.put( "dsInvestmentPerformance", JasperReportsUtils.convertReportData( cepr01040111Business.getInvestmentPerformanceList() ) );
            params.put( "dsInvestmentYield", JasperReportsUtils.convertReportData( cepr01040111Business.getInvestmentYield().getYieldList() ) );
            params.put( "dsOneRow", JasperReportsUtils.convertReportData( cepr01040111Business.getOneRowList() ) );
            params.put( "dsNoteSekaligus", JasperReportsUtils.convertReportData( cepr01040111Business.getNoteSekaligusRowList() ) );
            params.put( "dsNoteBerkala", JasperReportsUtils.convertReportData( cepr01040111Business.getNoteBerkalaRowList() ) );
            
            currentIdx = 0;
            putRiderParam( params, riderBusiness.getRiderPaList(), "dsRiderPa", "riderPaIndex" );
            params.put( "riderPaRisk", riderBusiness.getRiderPaRisk() );
            params.put( "dsRiderPaClone", JrUtil.convertToDsAndSetNullIfEmpty( riderBusiness.getRiderPaList() ) );
            params.put( "riderLadiesInsTitle", MappingUtil.getLabel(cepr01030103Form.getLadiesInsList(), cepr01030103Form.getLadiesInsCd()));
            putRiderParam( params, riderBusiness.getRiderHcpList(), "dsRiderHcp", "riderHcpIndex" );
            putRiderParam( params, riderBusiness.getRiderHcpFamilyList(), "dsRiderHcpFamily", "riderHcpFamilyIndex" );
            putRiderParam( params, riderBusiness.getRiderHcpLadiesList(), "dsRiderHcpLadies", "riderHcpLadiesIndex" );
            putRiderParam( params, riderBusiness.getRiderTpdList(), "dsRiderTpd", "riderTpdIndex" );
            putRiderParam( params, riderBusiness.getRiderTermRiderList(), "dsRiderTermRider", "riderTermRiderIndex" );
            putRiderParam( params, riderBusiness.getRiderLadiesInsList(), "dsRiderLadiesIns", "riderLadiesInsIndex" );
            putRiderParam( params, riderBusiness.getRiderEkaSehatListMap(), "dsRiderEkaSehat", "riderEkaSehatIndex");
            putRiderParam( params, riderBusiness.getRiderHcpProviderList(), "dsRiderHcpProvider", "riderHcpProviderIndex" );
            putRiderParam( params, riderBusiness.getRiderCiList(), "dsRiderCi", "riderCiIndex" );
            putRiderParam( params, riderBusiness.getRiderEkaSehatInnerLimitListMap(), "dsRiderEkaSehatInnerLimit", "riderEkaSehatInnerLimitIndex");
            putRiderParam( params, riderBusiness.getRiderLadiesMedExpenseListMap(), "dsRiderLadiesMedExpense", "riderLadiesMedExpenseIndex");
            putRiderParam( params, riderBusiness.getRiderLadiesMedExpenseInnerLimitListMap(), "dsRiderLadiesMedExpenseInnerLimit", "riderLadiesMedExpenseInnerLimitIndex");
            
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

            
            if(currentIdx > 0){ 
            	params.put("footer", "enable");
            	params.put( "dsCommonHeader2", JasperReportsUtils.convertReportData( cepr01040111Business.getCommonHeaderRowList() ) );
            	params.put( "dsCommonHeaderx", JasperReportsUtils.convertReportData( cepr01040111Business.getCommonHeaderRowList()) );
            }
            params.put( "dataSource", cepr01040111Business.getPageList( currentIdx ) );
            
            //=========================
            String namaAgen = cepr01030101Form.getProposalUser();
            if(namaAgen == null)namaAgen = "";
            String sendType= (String) request.getSession().getAttribute("pdfEvent");//(Integer) session.getAttribute("sendType");
            request.getSession().removeAttribute("pdfEvent");
            if(sendType == null)sendType = "";
            String type="";
            if("onPressButtonFax".equals(sendType)){
    			type="SENDFAX";
    		}else if("onPressButtonEmail".equals(sendType)){
    			type="SENDEMAIL";
    		}
    		
    		if(!"".equals(sendType)){
    			Properties props = new Properties();
    			props.put("xAction-Type", type);
    			props.put("xAction-To", "");
    			props.put("xAction-Subject", "Simulasi "+ productLabel + subproductLabel +" [Nama Telemarketing : "+ namaAgen +"] ");
    			//product+" [Nama Telemarketing : "+params.get("nama_agen")+"] ");
    			props.put("Content-Disposition", "attachment;filename=proposal.pdf");
    			jasperViewer.setHeaders(props);		
    		}
            //=========================
            params.put( "marketer", "Marketer : " + cepr01030101Form.getProposalUser());
            
            //=========================    		
    		// Save E-Proposal PDF ke EBServer dan PopUp Download
            // ** Adrian@06/01/2014            
            String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
            String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
            Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();       
            String fileName = msag_id+"_"+no_proposal+".pdf";
            params.put( "no_proposal",  no_proposal );
            
            List temp = cepr01040111Business.getPageList( currentIdx );
            String rootDir_EProp="";
            if(flag_test==1){
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP_TEST;
            }else{
            	rootDir_EProp = CommonConst.ROOTDIR_EPROP;
            }
           String dirName = rootDir_EProp+"\\"+msag_id;                
           File sourceFile = new File( dirName + "\\" + fileName );
           try{
        	   if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040111Mapper.X11 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040111Mapper.X12  ){
		        		 JasperUtils.exportReportToPdf(
		                		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040111_new_product.jasper",
			            		dirName,
			                fileName,
							params,
							temp,
							PdfWriter.AllowPrinting,
			                null,
			                null
			            );
		        		 JasperUtils.downloadReportPDF(response, dirName, fileName);        
               }else{              
		                JasperUtils.exportReportToPdf(
		                		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040111_product.jasper",
			            		dirName,
			                fileName,
							params,
							temp,
							PdfWriter.AllowPrinting,
			                null,
			                null
			            );
			            JasperUtils.downloadReportPDF(response, dirName, fileName);        
               }
	    	}catch( Exception e )
	        {
	    		
	            logger.error("ERROR", e);
	        }      
            
            jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
            request.getSession().setAttribute( "messageBoxList", null );
            result = new ModelAndView( jasperViewer, params );
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