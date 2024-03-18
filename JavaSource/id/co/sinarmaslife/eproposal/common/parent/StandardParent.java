package id.co.sinarmaslife.eproposal.common.parent;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: StandardParent
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 23, 2007 1:39:24 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibatis.common.resources.Resources;

import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;

public class StandardParent
{
    protected final Log logger = LogFactory.getLog( getClass() );

    protected EproposalManager eproposalManager;
    protected Cepr00000000EditorUtil editorUtil;

    public void setEproposalManager( EproposalManager eproposalManager )
    {
        this.eproposalManager = eproposalManager;
    }

    public void setEditorUtil( Cepr00000000EditorUtil editorUtil )
    {
        this.editorUtil = editorUtil;
    }
    
    public static void exportReportToPdf(
			String reportSourcePath, String outputDirectory, String outputFileName,
			Map params, List dataList,
			Integer permissions, String ownerPassword, String userPassword) throws IOException, JRException{

        String op = null;
        if(outputFileName == null) {
        	op = outputDirectory;
    		File userDir = new File(outputDirectory.substring(0, outputDirectory.lastIndexOf("\\")));
            if(!userDir.exists()) userDir.mkdirs();
        } else {
        	op = outputDirectory+"\\"+outputFileName;
    		File userDir = new File(outputDirectory);
            if(!userDir.exists()) userDir.mkdirs();
        }

		File sourceFile = Resources.getResourceAsFile(reportSourcePath);
		JasperReport jasperReport = (JasperReport)JRLoader.loadObject(sourceFile);
//		params.put("reportDataKey", "dataSource");
//		params.put("subReportDataKeys", new String[] {"subDS"});

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(dataList));
        JRPdfExporter jrpdfexporter = new JRPdfExporter();
		jrpdfexporter.setParameters(	getPdfExporterParameter(permissions, ownerPassword, userPassword));
		jrpdfexporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		jrpdfexporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, op);
		jrpdfexporter.exportReport();
	}
    
    public static Map<JRExporterParameter, Comparable> getPdfExporterParameter(
			Integer permissions, String ownerPassword, String userPassword) {
		Map<JRExporterParameter, Comparable> expParams = new HashMap<JRExporterParameter, Comparable>();
		if (permissions != null) expParams.put(JRPdfExporterParameter.PERMISSIONS, permissions);
		expParams.put(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
		expParams.put(JRPdfExporterParameter.IS_128_BIT_KEY, Boolean.TRUE);
		if (ownerPassword != null) expParams.put(JRPdfExporterParameter.OWNER_PASSWORD, ownerPassword);
		if (userPassword != null) expParams.put(JRPdfExporterParameter.USER_PASSWORD, userPassword);
		//exp.put(JRPdfExporterParameter.PERMISSIONS, new Integer(PdfWriter.AllowCopy));
		//exp.put(JRPdfExporterParameter.USER_PASSWORD, elionsManager.validationVerify(1).get("PASSWORD").toString().toLowerCase());
		//exp.put(JRPdfExporterParameter.OWNER_PASSWORD, props.getProperty("pdf.masterPassword"));
		//exp.put("net.sf.jasperreports.engine.export.JRPdfExporterParameter.PERMISSIONS", new Integer(PdfWriter.AllowPrinting));			
		//AllowPrinting, AllowModifyContents, AllowCopy, AllowModifyAnnotations, AllowFillIn, AllowScreenReaders, AllowAssembly and AllowDegradedPrinting 

		return expParams;
	}
}
