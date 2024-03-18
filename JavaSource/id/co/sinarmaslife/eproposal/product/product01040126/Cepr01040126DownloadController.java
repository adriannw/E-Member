package id.co.sinarmaslife.eproposal.product.product01040126;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	:
 * Program Name   		: Cepr01040126DownloadController
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
import id.co.sinarmaslife.eproposal.common.datasource.PremiumSummaryDataSource.RoundMode;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.util.JasperUtils;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;
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
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040126DownloadController extends StandardParent implements Controller
{
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        logger.info( "*-*-*-* Cepr01040126DownloadController.handleRequest" );

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
            jasperViewer.setUrl( "/WEB-INF/classes/" + "id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040126_product.jasper" );
           // BigDecimal tempBunga = new BigDecimal("0.085");
            BigDecimal ratekedua = new BigDecimal("0");
            BigDecimal rateKetiga = new BigDecimal( "0");
            BigDecimal rateKeempat =  new BigDecimal("0");
            BigDecimal helpCountAccidentBenefit =  new BigDecimal("0");
            String label = "";
            if(cepr01030102Form.getRightPartOfBusinessCd() == 1)
            {
            	ratekedua = new BigDecimal( "0.98" );
            	rateKetiga = new BigDecimal( "0");
            	rateKeempat = new BigDecimal( "0");
            	helpCountAccidentBenefit = new BigDecimal("1000").pow(3);
            	label = "**)";
            }
            else if(cepr01030102Form.getRightPartOfBusinessCd() == 2)
            {
            	ratekedua = new BigDecimal( "1.02" );
            	rateKetiga = (new BigDecimal( "1" ).add(new BigDecimal( "0.0400115" ))).pow(3);
            	rateKeempat = (new BigDecimal( "1" ).add(new BigDecimal( "0.040003" ))).multiply(rateKetiga);
            	helpCountAccidentBenefit = new BigDecimal("10").pow(5);
            	label = "";
            }
            BigDecimal[] rate = {
                    new BigDecimal( "0.90" ),
                    ratekedua,
                    rateKetiga,
                    rateKeempat
            };
            BigDecimal[] nonAccidentBenefitSimponi = {
                    new BigDecimal( "1.02" ),
                    new BigDecimal( "1.04" ),
                    new BigDecimal( "1.06" ),
                    new BigDecimal( "0" )
            };
            BigDecimal[] nonAccidentBenefitSecured = {
                    new BigDecimal( "1.10" ),
                    new BigDecimal( "1.10" ),
                    new BigDecimal( "1.10" ),
                    new BigDecimal( "1.10" )
            };
            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            Map<String, Object> map;
            for( int i = 1; i <= 4; i++ )
            {
                map = new HashMap<String, Object>();
                double cashValue = FormatNumber.round(LazyConverter.toDouble(rate[ i - 1 ].multiply( cepr01030102Form.getTotalSumInsured() )), 2);
                map.put( "yearNo", editorUtil.convertToString( i ) );
                map.put( "cashValue", editorUtil.convertToString( cashValue ));

                if(cepr01030102Form.getRightPartOfBusinessCd() == 1) // jika simponi
                {
                	double leftNonAccidentBenefit =  FormatNumber.round( LazyConverter.toDouble( (cepr01030102Form.getTotalSumInsured().multiply(nonAccidentBenefitSimponi[i - 1]))), 2 );
                	map.put( "nonAccidentBenefit", editorUtil.convertToString( Math.max( leftNonAccidentBenefit , cashValue ) ) );
                	map.put( "accidentBenefit", editorUtil.convertToString( Math.min(LazyConverter.toDouble(helpCountAccidentBenefit), LazyConverter.toDouble(cepr01030102Form.getTotalSumInsured())) + Math.max(LazyConverter.toDouble(cepr01030102Form.getTotalSumInsured()), cashValue )) );
                }
                else if(cepr01030102Form.getRightPartOfBusinessCd() == 2) // jika secured
                {
                	double leftNonAccidentBenefit = FormatNumber.round( LazyConverter.toDouble( (cepr01030102Form.getTotalSumInsured().multiply(nonAccidentBenefitSecured[i - 1]))), 2);
                	map.put( "nonAccidentBenefit", editorUtil.convertToString( Math.max( leftNonAccidentBenefit, cashValue) ) );
                	map.put( "accidentBenefit", editorUtil.convertToString( cepr01030102Form.getTotalSumInsured().add( new BigDecimal( cashValue ))));
                } 
                mapList.add( map );
            }
            String differ = "";
            String butirPertama = "";
            String butirKetiga = "";
            String butirKeduaAyatSatu = "";
            String butirKeduaAyatDua = "";
            String judulTabel = "";
            String judul = "";
            String NotePertama = "";
            if(cepr01030102Form.getRightPartOfBusinessCd() == 1) // simponi rupiah
            {
            	differ = "1";
            	butirPertama = "Apabila Tertanggung hidup sampai dengan akhir tahun polis ketiga, maka akan dibayarkan Manfaat Asuransi seperti yang tercantum pada Tabel Nilai Tunai dan selanjutnya pertanggungan berakhir. Namun demikian, apabila pada akhir tahun polis ketiga atas permintaan Pemegang Polis dan atas persetujuan Penanggung, Pemegang Polis memilih untuk tidak menerima Manfaat Asuransi tersebut, maka pemegang polis dapat menerima Manfaat Asuransi tersebut pada akhir tahun polis keempat. Namun demikian, khusus untuk Manfaat Asuransi di akhir tahun polis keempat besarnya tidak dijamin, tergantung dari bunga pasar pada waktu tersebut.";
            	butirKetiga = "Apabila Tertanggung meninggal dunia bukan karena Kecelakaan dalam masa asuransi dan asuransi masih berlaku, maka kepada yang Ditunjuk akan diberikan Manfaat Asuransi sesuai tabel berikut :";
            	judulTabel = "Tabel Nilai Tunai";
            	judul = "SIMPONI DOLLAR";
            	butirKeduaAyatSatu = "- 100 % Uang Pertanggungan(maksimum seratus ribu US dollar, untuk semua Polis yang diterbitkan Penanggung);";
            	butirKeduaAyatDua = "- Nilai Tunai atau 100% Uang Pertanggungan, mana yang lebih besar dan selanjutnya pertanggungan berakhir.";
            	NotePertama = "Polis batal disebabkan oleh apapun (termasuk meninggal dunia).";
            }
            else if(cepr01030102Form.getRightPartOfBusinessCd() == 2) // secured invest rupiah
            {
            	differ = "2";
            	butirPertama = "Apabila Tertanggung hidup sampai dengan akhir tahun polis ketiga, maka akan dibayarkan Manfaat Asuransi seperti yang tercantum pada Klausula Daftar Nilai Tunai dan selanjutnya pertanggungan berakhir. Namun demikian, apabila pada akhir tahun polis ketiga atas permintaan Pemegang Polis dan atas persetujuan Penanggung, Pemegang Polis memilih untuk tidak menerima Manfaat Asuransi tersebut, maka pemegang polis dapat menerima Manfaat Asuransi tersebut pada akhir tahun polis keempat yang besarnya seperti yang tercantum pada Klausula Daftar Nilai Tunai dan selanjutnya pertanggungan berakhir.";
            	butirKetiga = "Apabila Tertanggung meninggal dunia bukan karena Kecelakaan dalam masa asuransi dan asuransi masih berlaku, maka kepada yang Ditunjuk akan diberikan Manfaat Asuransi sebesar 110% Uang Pertanggungan atau Nilai Tunai, mana yang lebih besar dan selanjutnya pertanggungan berakhir.";
            	judulTabel = "Klausula Daftar Nilai Tunai";
            	judul = "SECURED INVEST DELUVE DOLLAR";
            	butirKeduaAyatSatu = "- 100% Uang Pertanggungan (maksimum seratus ribu US dollar), ditambah";
            	butirKeduaAyatDua = "- Nilai Tunai dan selanjutnya pertanggungan berakhir";
            	NotePertama = "Nilai Tunai yang tercantum diatas adalah Nilai Tunai sebelum dikenakan Biaya pajak(sesuai dengan ketentuan UU yang berlaku)";
            }
            params.put( "NotePertama", NotePertama );
            params.put( "butirKeduaAyatSatu", butirKeduaAyatSatu );
            params.put( "butirKeduaAyatDua", butirKeduaAyatDua );
            params.put( "differ", differ );
            params.put("butirPertama", butirPertama);
            params.put("butirKetiga", butirKetiga);
            params.put( "judulTabel", judulTabel );
            params.put( "judul", judul );
            params.put( "label", label );
            jasperViewer.setReportDataKey( "dataSource" );
            params.put( "dataSource", mapList );          
            params.put( "logoPath", eproposalManager.getProps().getProperty( "logo.path" ) );
            params.put( "dsCommonHeader", JasperReportsUtils.convertReportData( getHeaderRowList( command ) ) );
            params.put( "validityMsg", "" );
          //  params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "placeAndDate", "Jakarta, " + FormatDate.toIndonesian( eproposalManager.selectNowDate() ) );
            params.put( "agentCode", cepr01030101Form.getProposalUserCd() );
            params.put( "agentName", cepr01030101Form.getProposalUser() );
            jasperViewer.setApplicationContext( WebApplicationContextUtils.getWebApplicationContext( request.getSession().getServletContext() ) );
            request.getSession().setAttribute( "messageBoxList", null );
            result = new ModelAndView( jasperViewer, params );
        }

        return result;
    }

    public List< Map<String, Object> > getHeaderRowList( Object command ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040126DownloadController.getCommonHeaderRowList" );
        
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
        result.addAll( getInsuredNameMap() );
        return result;
    }
    
    public List<Map<String, Object>> getInsuredNameMap() throws IOException
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "label", "Tingkat Bunga Penjaminan" );
        params.put( "content", "4.00%" );
        result.add( params );
        return result;
    }
}