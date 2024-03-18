package id.co.sinarmaslife.eproposal.product.product01040120;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040120DownloadController
 * Description         	: Power Save Syariah (175)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jan 21, 2009 10:29:28 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import com.lowagie.text.pdf.PdfWriter;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.standard.util.*;
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

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cepr01040120DownloadController extends StandardParent implements Controller
{
	protected final Log logger = LogFactory.getLog( getClass() );
	
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040120DownloadController.handleRequest" );

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
            jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040120_product.jasper" );

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
           // Date nowDate = eproposalManager.selectNowDate();
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
                    logger.info( "*-*-*-* bagian 1 premium = " + premium );
                    ldec_nt = ( 1 - ldec_sc ) * premium;
                }
                else
                {
                    logger.info( "*-*-*-* bagian 2 ldec_nt = " + ldec_nt );
                    ldec_nt = ( 1 - ldec_sc ) * ldec_nt;
                }
                // PROCESS TO COMPUTE lifeBenefit
                if( li_loop == li_years )
                    lifeBenefit = new BigDecimal( ldec_saldo );
                else
                    lifeBenefit = null;

                if(lb_cetak)
                {
                    logger.info( "*-*-*-* ldec_ke = " + ldec_ke );
                    logger.info( "*-*-*-* ldec_saldo = " + ldec_saldo );
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

            jasperViewer.setReportDataKey( "dataSource" );
            params.put( "dataSource", mapList );
            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.syariah" ) );
            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( getHeaderRowList( command ) ) );
          //  params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
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
      
	        	   //if(cekDataApprovedOrNot.get(0).getPosisi()<3){
			            JasperUtils.exportReportToPdf(
			            		"id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040120_product.jasper",
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
        logger.info( "*-*-*-* Cepr01040120DownloadController.getCommonHeaderRowList" );

        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List< Map<String, Object> > result = new ArrayList< Map<String, Object> >();

        // Fill header content from CommonHeaderDataSource
        result.addAll( source.getInsuredNameSyariahMap() );
        result.addAll( source.getInsuredAgeSyariahMap() );
        result.addAll( source.getTermOfContractMap() );
        result.addAll( source.getTermOfPaymentWithSekaligusSyariahMap() );
        result.addAll( source.getTotalSumInsuredMap() );
        result.addAll( source.getManfaatTermRiderMap() );
        result.addAll( source.getManfaatPersonalAccidentMap() );


        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        Map<String, Object> params;
        params = new HashMap<String, Object>();
        params.put( "label", "Masa Proyeksi Investasi (MPI 1) *)" );
        params.put( "content", editorUtil.convertToString( cepr01030102Form.getMgiCd() ).concat( " bulan" ) );
        result.add( params );

        params = new HashMap<String, Object>();
        params.put( "label", "Tingkat Proyeksi Investasi MPI Pertama" );
        params.put( "content", editorUtil.convertToString( cepr01030102Form.getInvestRateInPercent() ).concat( "% (p.a)" ) );
        result.add( params );

        String taxBeforeAfterChoice = MappingUtil.getLabel( cepr01030102Form.getTaxBeforeAfterList(), cepr01030102Form.getTaxBeforeAfterCd() );
        params = new HashMap<String, Object>();
        params.put( "label", "Perhitungan Saldo Kontribusi/Premi Deposit & NT" );
        params.put( "content", taxBeforeAfterChoice.concat( " Pajak" ) );
        result.add( params );

        return result;
    }

}
