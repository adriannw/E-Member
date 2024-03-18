package id.co.sinarmaslife.eproposal.product.product01040140;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040140DownloadController
 * Description         	: Power Save New (142)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 13, 2007 10:29:28 AM
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
import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.model.vo.ParticipantVO;
import id.co.sinarmaslife.eproposal.product.product01040111.Cepr01040111Business;
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
import java.util.*;

public class Cepr01040140DownloadController extends StandardParent implements Controller
{
	static Integer currentIdx;
	protected final Log logger = LogFactory.getLog( getClass() );
	
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01030103Form cepr01030103Form = cepr01030000HoldingForm.getCepr01030103Form();
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01030106Form cepr01030106Form = cepr01030000HoldingForm.getCepr01030106Form();
        Cepr01040140Business cepr01040140Business = new Cepr01040140Business( eproposalManager, editorUtil, command );

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
            jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040140_product.jasper" );
            
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
            double li_years;
            double li_bagi;
            double li_hari;
            double ldec_nt = 0;
            boolean lb_cetak;
            double ldec_ke;
            double ldec_premi;
            double li_ke = 0;
            int li_tahun = 0;
            double ldec_sc = 0.15;
            BigDecimal lifeBenefit;
            double investRate = cepr01030102Form.getInvestRateInPercent().doubleValue();
            double premium = cepr01030102Form.getPremium().doubleValue();
            int mgi = cepr01030102Form.getMgiCd();
            double ldec_mgi;

            switch( mgi )
            {
                case 1: ldec_mgi = 0.08; break;
                case 6: ldec_mgi = 0.5; break;
                case 12: ldec_mgi = 1.0; break;
                case 36: ldec_mgi = 3.0; break;
                case 3: ldec_mgi = 0.25; break;
                default: ldec_mgi = 0;
            }
            //Date nowDate = eproposalManager.selectNowDate();
            Date nowDate = eproposalManager.selectNowDate();
            Date ld1 = nowDate;
            Date ld2 = nowDate;


            // Set new base premium and total sum insured in case target premi and / or tax after method
            if( Hardcode.TARGET_PREMI_CD.equals( cepr01030102Form.getTargetCd() ) )
            {
                ldec_premi = premium;
                premium = FormatNumber.round( ldec_premi * ( ( 1 / Math.pow( ( 1 + (investRate/100)), ldec_mgi ) ) ), 2 );
                if( Hardcode.TAX_AFTER_CD.equals( cepr01030102Form.getTaxBeforeAfterCd() ) )
                {
                    if( mgi < 12 )
                    {
                        premium = FormatNumber.round( ldec_premi / ( 0.2 + 0.8 * ( Math.pow( ( 1 + ( investRate/100 ) ), ldec_mgi ) ) ), 2);
                    }
                }

                if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                {
                    cepr01030102Form.setTotalSumInsured( new BigDecimal( Math.min( 1000000000.0, premium ) ) );
                }
                else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
                {
                    cepr01030102Form.setTotalSumInsured( new BigDecimal( Math.min( 100000.0, premium ) ) );
                }
                cepr01030102Form.setPremium( new BigDecimal( premium ) );
            }

            BigDecimal premiTotalSekaligus = new BigDecimal(0);
            BigDecimal premiTotalTahunan = new BigDecimal(0);
            BigDecimal premiTotalSemesteran = new BigDecimal(0);
            BigDecimal premiTotalTriwulanan = new BigDecimal(0);
            BigDecimal premiTotalBulanan = new BigDecimal(0);
            ciRider = cepr01040140Business.of_get_coi_ci(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            termRider = cepr01040140Business.of_get_coi_term(kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
            if(jenis != 0){
            	hcpFamilyRider = cepr01040140Business.of_get_coi_hcp_family(jenis, kursId, cepr01030101Form.getInsuredAge(), premiTotalSekaligus, premiTotalTahunan, premiTotalSemesteran, premiTotalTriwulanan, premiTotalBulanan);
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
            
            if( mgi != 36 )
            {
                li_years = 48 / mgi;
                li_bagi = li_years / 4;
            }
            else
            {
                li_years = 4;
                li_bagi = 1;
            }

            if( mgi == 3 || mgi == 6 || mgi == 12 )
                li_tahun = 1;
            else if( mgi == 36 )
                li_tahun = 3;

            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            Map<String, Object> map;
            double ldec_saldo = premium;

            for( double li_loop = 1; li_loop <= li_years; li_loop++ )
            {
                // PROCESS TO COMPUTE cashValue
                ld2 = FormatDate.add( ld2, Calendar.MONTH, cepr01030102Form.getMgiCd() );
                li_hari = DateUtil.calculateDifference( ld1, ld2 );
                ld1 = ld2;
                if( mgi != 36 )
                {
                    if( Hardcode.TAX_AFTER_CD.equals( cepr01030102Form.getTaxBeforeAfterCd() ) && ( li_loop / li_bagi < 3 ) )
                    {
                        ldec_saldo += FormatNumber.round( li_hari / 365 * ldec_saldo * ( investRate / 100 ) * 0.8, 2);
                    }
                    else
                    {
                        ldec_saldo += FormatNumber.round( li_hari / 365 * ldec_saldo * ( investRate / 100 ), 2 );
                    }
                }
                else
                {
                    if( Hardcode.TAX_AFTER_CD.equals( cepr01030102Form.getTaxBeforeAfterCd() ) && ( li_loop / li_bagi < 3 ) )
                    {
                        ldec_saldo = FormatNumber.round( ( premium * Math.pow( 1 + investRate / 100, li_loop / li_bagi ) - premium ) * 0.8 + premium, 2);
                    }
                    else
                    {
                        ldec_saldo = FormatNumber.round( premium * Math.pow( 1 + investRate / 100, li_loop / li_bagi ), 2 );
                    }
                }

                // PROCESS TO COMPUTE yearNo
                lb_cetak = false;
                if( li_years == 48 )
                {
                    if( li_loop <= 12 ) lb_cetak = true;
                    else if( ( li_loop % 12 ) == 0 ) lb_cetak = true;
                    ldec_ke = li_loop;
                }
                else if( li_years == 16 )
                {
                    if( li_loop <= 4 ) lb_cetak = true;
                    else if( ( li_loop % 4 ) == 0 ) lb_cetak = true;
                    ldec_ke = ( li_ke += 3 );
                }
                else
                {
                    lb_cetak = true;
                    ldec_ke = li_loop / li_bagi;
                }

                // PROCESS TO COMPUTE cashValue

                if( li_loop <= li_tahun )
                {
                    ldec_nt = ( 1 - ldec_sc ) * premium;
                }
                else
                {
                    ldec_nt = ( 1 - ldec_sc ) * ldec_nt;
                }
                // PROCESS TO COMPUTE lifeBenefit
                if( li_loop == li_years )
                    lifeBenefit = new BigDecimal( ldec_saldo );
                else
                    lifeBenefit = null;

                if(lb_cetak)
                {
                    map = new HashMap<String, Object>();
                    map.put( "yearNo", editorUtil.convertToString( ( int ) ldec_ke ) );
                    map.put( "estimatedBalance", editorUtil.convertToString( new BigDecimal( ldec_saldo ) ) );
                    map.put( "cashValue", editorUtil.convertToString( new BigDecimal( ldec_nt ) ) );
                    map.put( "nonAccidentBenefit", editorUtil.convertToString( new BigDecimal( ldec_saldo ) ) );
                    map.put( "accidentBenefit", editorUtil.convertToString( new BigDecimal( ldec_saldo ) .add( cepr01030102Form.getTotalSumInsured() ) ) );
                    map.put( "lifeBenefit", editorUtil.convertToString( lifeBenefit ) );
                    mapList.add( map );
                }

                ldec_nt = ldec_saldo;

            }
            
            currentIdx = 0;
            Cepr00000000RiderBusiness riderBusiness = new Cepr00000000RiderBusiness( eproposalManager, editorUtil, command );
            putRiderParam( params, riderBusiness.getRiderTermRiderList(), "dsRiderTermRider", "riderTermRiderIndex" );
            putRiderParam( params, riderBusiness.getRiderCiList(), "dsRiderCi", "riderCiIndex" );
            putRiderParam( params, riderBusiness.getRiderHcpFamilyList(), "dsRiderHcpFamily", "riderHcpFamilyIndex" );
            putRiderParam( params, riderBusiness.getRiderHcpFamilyParticipantsList(), "dsRiderHcpFamilyParticipants", "riderHcpFamilyParticipantsIndex" );
            //params.put( "baseBenefitHcpFam", riderBusiness.computeBaseBenefitHcpFam() );
            
            jasperViewer.setReportDataKey( "dataSource" );
            
            params.put( "dataSource", mapList );
            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.syariah" ) );
            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( getHeaderRowList( command ) ) );
            if(currentIdx > 0){ 
            	params.put( "dsCommonHeader2", JasperReportsUtils.convertReportData( getHeaderRowList( command ) ) );
            	params.put( "dsCommonHeaderx", JasperReportsUtils.convertReportData( getHeaderRowList( command ) ) );
            }
            // disclaimer =========================================================================================================
           // params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate()) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            //=====================================================================================================================
            params.put( "validityMsg", "" );

       	    //==========================================    		
    		// Save E-Proposal PDF ke EBServer dan PopUp Download
            // ** Adrian@06/01/2014
            String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
            String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
            Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();       
            String fileName = msag_id+"_"+no_proposal+".pdf";
            params.put( "no_proposal",  no_proposal );
            List temp = mapList;
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
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040140_product.jasper",
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
            result = new ModelAndView( jasperViewer, params );
        }

        return result;
    }

    public List< Map<String, Object> > getHeaderRowList( Object command ) throws IOException
    {
        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List< Map<String, Object> > result = new ArrayList< Map<String, Object> >();

        // Fill header content from CommonHeaderDataSource
        result.addAll( source.getInsuredNameMap() );
        result.addAll( source.getInsuredAgeMap() );
        result.addAll( source.getTermOfContractMap() );
        result.addAll( source.getTermOfPaymentWithSekaligusMap() );
        result.addAll( source.getTotalSumInsuredMap() );
        result.addAll( source.getManfaatTermRiderMap() );
        result.addAll( source.getManfaatPersonalAccidentMap() );


        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        Map<String, Object> params;
        params = new HashMap<String, Object>();
        params.put( "label", "Masa Garansi Investasi Pertama (MGI 1) *)" );
        params.put( "content", editorUtil.convertToString( cepr01030102Form.getMgiCd() ).concat( " bulan" ) );
        result.add( params );

        params = new HashMap<String, Object>();
        params.put( "label", "Tingkat Suku bunga MGI Pertama" );
        params.put( "content", editorUtil.convertToString( cepr01030102Form.getInvestRateInPercent() ).concat( "% (p.a)" ) );
        result.add( params );

        String taxBeforeAfterChoice = MappingUtil.getLabel( cepr01030102Form.getTaxBeforeAfterList(), cepr01030102Form.getTaxBeforeAfterCd() );
        params = new HashMap<String, Object>();
        params.put( "label", "Perhitungan Saldo Premi Deposit & NT" );
        params.put( "content", taxBeforeAfterChoice.concat( " Pajak" ) );
        result.add( params );

        return result;
    }
    
    public void putRiderParam( Map<String, Object> params, List contentList, String dsParamName, String idxParamName )
    {
        JRDataSource source = JrUtil.convertToDsAndSetNullIfEmpty( contentList );
        if( source != null ){
        	currentIdx = currentIdx + 1;
        	if("dsRiderHcpFamily".equals(dsParamName)){
        		params.put( dsParamName, "on" );
        		params.put( idxParamName, currentIdx.toString() + "." );
        	}else if("dsRiderHcpFamilyParticipants".equals(dsParamName)){
        		params.put( dsParamName, "on" );
        		//params.put( idxParamName, currentIdx.toString() + "." );
        	}else if("dsRiderCi".equals(dsParamName)){
        		params.put( dsParamName, "on" );
        		params.put( idxParamName, currentIdx.toString() + "." );
        	}else if("dsRiderTermRider".equals(dsParamName)){
        		params.put( dsParamName, "on" );
        		params.put( idxParamName, currentIdx.toString() + "." );
        	}
        }

        //params.put( dsParamName, source );
        //params.put( idxParamName, currentIdx.toString() + "." );
    }

}
