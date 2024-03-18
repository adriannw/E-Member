package id.co.sinarmaslife.eproposal.product.product01040124;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	:
 * Program Name   		: Cepr01040124DownloadController
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
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.ListUtil;
import id.co.sinarmaslife.standard.util.HtmlUtil;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.jasperreports.AbstractJasperReportsView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040124DownloadController extends StandardParent implements Controller
{
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040124DownloadController.handleRequest" );

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
            jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040124_product.jasper" );

            BigDecimal[] rate = {
                    new BigDecimal( "0.95" ),
                    new BigDecimal( "1.02" ),
                    new BigDecimal( "1.085" ),
                    new BigDecimal( "1.125" ),
                    new BigDecimal( "1.17" ),
            };

            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            Map<String, Object> map;
            for( int i = 1; i <= 5; i++ )
            {
                map = new HashMap<String, Object>();
                map.put( "yearNo", editorUtil.convertToString( i ) );
                map.put( "cashValue", editorUtil.convertToString( rate[ i - 1 ].multiply( cepr01030102Form.getTotalSumInsured() ) ) );
                map.put( "nonAccidentBenefit", editorUtil.convertToString( (rate[ i - 1 ].multiply( cepr01030102Form.getTotalSumInsured() ) ).add(cepr01030102Form.getTotalSumInsured()) ) );
                map.put( "accidentBenefit", editorUtil.convertToString( (rate[ i - 1 ].multiply( cepr01030102Form.getTotalSumInsured() ) ).add(cepr01030102Form.getTotalSumInsured().multiply(new BigDecimal("2"))) ) );
                mapList.add( map );
            }
            BigDecimal uangPertanggungan = new BigDecimal("0");
            if(cepr01030101Form.getInsuredAge() <= 55)
            {
            	uangPertanggungan = new BigDecimal("75000");
            }
            else if(cepr01030101Form.getInsuredAge() >= 56 && cepr01030101Form.getInsuredAge() <= 60)
            {
            	uangPertanggungan = new BigDecimal("50000");
            }
            
            
            jasperViewer.setReportDataKey( "dataSource" );
            params.put( "butirKedua", "Apabila Tertanggung meninggal dunia karena kecelakaan dan Asuransi masih berlaku, maka kepada yang Ditunjuk akan diberikan Manfaat Asuransi sebesar 200% Uang Pertanggungan dengan maksimum manfaat Asuransi sebesar $"+ editorUtil.convertToRoundedNoDigit(uangPertanggungan.multiply(new BigDecimal("2"))) + " dan sejak saat itu asuransi berakhir." );
            params.put( "butirKetiga", "Apabila Tertanggung meninggal dunia bukan karena kecelakaan pada 6 (enam) bulan pertama sejak berlakunya polis, maka kepada Yang Ditunjuk akan dibayarkan 50% Uang Pertanggungan dengan maksimum Manfaat Asuransi sebesar $" + editorUtil.convertToRoundedNoDigit(uangPertanggungan.multiply(new BigDecimal("0.5"))) + " dan sejak saat itu asuransi berakhir." );
            params.put( "butirKeempat", "Apabila Tertanggung meninggal dunia bukan karena kecelakaan setelah 6 (enam) bulan sejak berlakunya polis, maka kepada Yang Ditunjuk akan dibayarkan 100% Uang Pertanggungan dengan maksimum Manfaat Asuransi sebesar $"+ editorUtil.convertToRoundedNoDigit(uangPertanggungan) + " dan sejak saat itu asuransi berakhir." );
           // params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            params.put( "dataSource", mapList );
            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( getHeaderRowList( command ) ) );
            params.put( "validityMsg", "" );

            jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
            request.getSession().setAttribute( "messageBoxList", null );
            result = new ModelAndView( jasperViewer, params );
        }

        return result;
    }

    public List< Map<String, Object> > getHeaderRowList( Object command ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040124DownloadController.getCommonHeaderRowList" );
        
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