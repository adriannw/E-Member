package id.co.sinarmaslife.eproposal.product.product01040127;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	:
 * Program Name   		: Cepr01040127DownloadController
 * Description         	: Power Simponi Rupiah (95)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 9, 2007 11:37:13 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import com.lowagie.text.pdf.PdfWriter;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.standard.util.AngkaTerbilang;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.ListUtil;
import id.co.sinarmaslife.standard.util.HtmlUtil;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040127DownloadController extends StandardParent implements Controller
{
	protected final Log logger = LogFactory.getLog( getClass() );
	
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040127DownloadController.handleRequest" );

        Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

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
            jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040127_product.jasper" );

            BigDecimal[] rate = {
                    new BigDecimal( "0.95" ),
                    new BigDecimal( "1.00" ),
                    new BigDecimal( "1.05" ),
                    new BigDecimal( "1.10" ),
                    new BigDecimal( "1.15" ),
                    new BigDecimal( "1.20" ),
                    new BigDecimal( "1.30" ),
                    new BigDecimal( "1.40" ),
            };
            

            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            Map<String, Object> map;
            for( int i = 1; i <= 8; i++ )
            {
            	map = new HashMap<String, Object>();
            	BigDecimal cashValue = rate[ i - 1 ].multiply( cepr01030102Form.getTotalSumInsured() );
                map.put( "yearNo", editorUtil.convertToString( i ) );
                map.put( "cashValue", editorUtil.convertToString( cashValue ) );                
                float compareValueBelow50 = 75000;
                float compareValueAbove50 = 50000;
                if( cepr01030101Form.getInsuredAge() <= 50 )
                {
                	map.put( "accidentBenefit", editorUtil.convertToString( Math.min( cepr01030102Form.getTotalSumInsured().floatValue(), compareValueBelow50 ) + Float.parseFloat( cashValue.toString() ) ) );	
                }
                else if( cepr01030101Form.getInsuredAge() >= 51 && cepr01030101Form.getInsuredAge() <= 57)
                {
                	map.put( "accidentBenefit", editorUtil.convertToString( Math.min( cepr01030102Form.getTotalSumInsured().floatValue() / 2, compareValueAbove50 ) + Float.parseFloat( cashValue.toString() ) ) );
                }
                mapList.add( map );
            }
            BigDecimal uangPertanggungan = new BigDecimal("0");
            BigDecimal persentase = BigDecimal.ZERO;
            BigDecimal manfaatAsuransi = BigDecimal.ZERO;
            String wordForDetail = "";
            if( cepr01030101Form.getInsuredAge() <= 50 )
            {
            	uangPertanggungan = new BigDecimal("75000");
            	manfaatAsuransi = new BigDecimal("37500");
            	persentase = new BigDecimal("100");
            	wordForDetail = "1 (satu) tahun sampai dengan 50 (lima puluh) tahun.";
            }
            else if( cepr01030101Form.getInsuredAge() >= 51 && cepr01030101Form.getInsuredAge() <= 57)
            {
            	uangPertanggungan = new BigDecimal("50000");
            	manfaatAsuransi = new BigDecimal("25000");
            	persentase = new BigDecimal("50");
            	wordForDetail = "diatas 50(lima puluh) tahun.";
            }
            
            jasperViewer.setReportDataKey( "dataSource" );
            params.put( "butirKedua", "Apabila Tertanggung meninggal dunia pada 6 (enam) bulan pertama sejak berlakunya polis, dan asuransi masih berlaku, maka kepada yang Ditunjuk akan dibayarkan "+ editorUtil.convertToRoundedNoDigit( persentase.divide( new BigDecimal("2") ) ) + "% ("+ AngkaTerbilang.indonesian( editorUtil.convertToRoundedNoDigit( persentase.divide( new BigDecimal("2") ) ))+"perseratus) Uang Pertanggungan dengan maksimum Manfaat Asuransi sebesar US$" + editorUtil.convertToRoundedNoDigit( manfaatAsuransi ) + "("+ AngkaTerbilang.indonesian( manfaatAsuransi.toString() )+"US Dollar) dan sejak saat itu asuransi berakhir." );
            params.put( "butirKetiga", "Apabila Tertanggung meninggal dunia setelah 6 (enam) bulan sejak berlakunya polis, dan asuransi masih berlaku, maka kepada Yang Ditunjuk akan dibayarkan "+ editorUtil.convertToRoundedNoDigit( persentase ) + "% ("+ AngkaTerbilang.indonesian( persentase.toString() ) +"perseratus) Uang Pertanggungan dengan maksimum Manfaat Asuransi yang dapat dibayarkan sebesar US$ "+ editorUtil.convertToRoundedNoDigit( uangPertanggungan ) + " ("+ AngkaTerbilang.indonesian( uangPertanggungan.toString() ) +"US Dollar) dan sejak saat itu asuransi berakhir." );
            params.put( "butirKeempat", "Apabila Tertanggung mempunyai lebih dari satu polis Asuransi Simponi, maka Total Manfaat Asuransi Kematian atas diri Tertanggung sebagaimana dimaksud pada butir 2 dan butir 3 diatas yang dapat dibayarkan adalah Maksimum US$ "+ editorUtil.convertToRoundedNoDigit( uangPertanggungan ) + "("+ AngkaTerbilang.indonesian( uangPertanggungan.toString() )+"US Dollar) bagi Tertanggung yang pada saat masuk asuransi berusia "+ wordForDetail );
            //params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            params.put( "dataSource", mapList );
            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( getHeaderRowList( command ) ) );
            params.put( "validityMsg", "" );

            //==========================================    		
    		// Save E-Proposal PDF ke EBServer dan PopUp Download
            // ** Adrian@06/01/2014
            String msag_id = cepr01030102Form.getMstProposal().get(0).getMsag_id();
            String no_proposal = cepr01030102Form.getMstProposal().get(0).getId();
            Integer flag_test = cepr01030102Form.getMstProposal().get(0).getFlag_test();       
            String fileName = msag_id+"_"+no_proposal+".pdf";
            
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
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040127_product.jasper",
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
        logger.info( "*-*-*-* Cepr01040127DownloadController.getCommonHeaderRowList" );
        
        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List< Map<String, Object> > result = new ArrayList< Map<String, Object> >();

        // Fill header content from CommonHeaderDataSource
        result.addAll( source.getInsuredNameMap() );
        result.addAll( source.getInsuredAgeMap() );
        result.addAll( source.getTermOfContractMap() );
        result.addAll( source.getTotalSumInsuredMap() );
        result.addAll( source.getManfaatTermRiderMap() );
        result.addAll( source.getManfaatPersonalAccidentMap() );
        result.addAll( source.getTermOfPaymentWithSekaligusMap() );

        return result;
    }
}