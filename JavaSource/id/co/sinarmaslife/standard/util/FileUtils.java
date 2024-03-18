package id.co.sinarmaslife.standard.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibatis.common.resources.Resources;
import com.lowagie.text.pdf.PdfWriter;

import id.co.sinarmaslife.standard.model.vo.DropDown;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
/**
 * @author Yusuf
 * @since 01/12/2006
 * Fungsi2 berhubungan dengan file, seperti download..
 */
public class FileUtils {
	protected final static Log logger = LogFactory.getLog( FileUtils.class);
	
	//baca isi dari file dan return dalam bentuk string
	public static String readFileAsString(String filePath) throws java.io.IOException{
		StringBuilder fileData = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(
			        new FileReader(filePath));
			char[] buf = new char[1024];
			int numRead=0;
			while((numRead=reader.read(buf)) != -1){
			    fileData.append(buf, 0, numRead);
			}
		}finally {
			try {
				if (reader != null) {
					reader.close();
				}
			}catch(Exception e) {
				logger.error("reader cannot be closed", e);
			}
		}
		
		return fileData.toString();
	}		
	
	//delete file oleh user dari server
	public static boolean deleteFile(String destDir, String fileName, HttpServletResponse response) 
			throws FileNotFoundException, IOException{
		File file = new File(destDir+"/"+fileName);
		if(file.exists()) return file.delete();
		return false;
	}	
	
	//download file oleh user dari server
	public static void downloadFile(String attachOrInline, String destDir, String fileName, HttpServletResponse response) 
			throws FileNotFoundException, IOException{
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(destDir+"\\"+fileName);
			if (in != null) {
				out = new BufferedOutputStream(response.getOutputStream());
				in = new BufferedInputStream(in);
				//String contentType = "application/unknown";
				response.setHeader("Content-Disposition", attachOrInline+"filename=\"" + fileName + "\"");
                response.setHeader("Expires", "0");
				response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Pragma", "public");
				if(fileName.toLowerCase().indexOf(".pdf")>-1)
                {
					response.setContentType("application/pdf");
				}
                else if(fileName.toLowerCase().indexOf(".xls")>-1)
                {
					response.setContentType("application/vnd.ms-excel");
				}
                else
                {
                    throw new RuntimeException( "Type file ini belum terdaftar di FileUtils.downloadFile" );
                }
				
				IOUtils.copy(in, out);
            }
		} finally {
			if (in != null) try {in.close(); } catch (Exception e) {
				logger.error( e );
			}
			if (out != null) try {out.flush(); out.close(); } catch (Exception e) {
				logger.error( e );
			}
		}				
	}
	
	//listing file di dalam suatu directory
	public static List<DropDown> listFilesInDirectory(String dir) {
		File destDir = new File(dir);
		List<DropDown> daftar = new ArrayList<DropDown>();
		if(destDir.exists()) {
			String[] children = destDir.list();
			daftar = new ArrayList<DropDown>();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			for(int i=0; i<children.length; i++) {
				File file = new File(destDir+"/"+children[i]);
				daftar.add(new DropDown(children[i], df.format(new Date(file.lastModified())), dir));
			}
		}
		return daftar;
	}
	
	//listing file di dalam suatu directory
	public static List<File> listFilesInDirectory2(String dir) {
		File destDir = new File(dir);
		List<File> daftar = new ArrayList<File>();
		if(destDir.exists()) {
			String[] children = destDir.list();
			daftar = new ArrayList<File>();
			for(int i=0; i<children.length; i++) {
				daftar.add(new File(destDir+"/"+children[i]));
			}
		}
		return daftar;
	}
	
	// save file ke drive c
	//if("yes".equals(request.getParameter("save"))){
    	//FileUtils fileUtils = new FileUtils();
    	//fileUtils.save("id/co/sinarmaslife/eproposal/source/report/jasper/xepr_01040119_product.jasper", request.getParameter("fileDirName"), request.getParameter("fileName"), params, cepr01040119Business.getPageList());
    //}
	public void save(String jasperDirName, String fileDirName, String fileName, Map params, List dataList){

			try
			{
				exportReportToPdf(
						jasperDirName,
						fileDirName,
						fileName,
						params,
						dataList,
						PdfWriter.AllowPrinting,
						null,
						null
				);

				List<File> attachments = new ArrayList<File>();
				File sourceFile = new File( fileDirName + "\\" + fileName );
				attachments.add( sourceFile );

			}
			catch( Exception e )
			{
				logger.error("ERROR", e);
				//result = result + "\\nGagal export ke PDF";
			}
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
		params.put("reportDataKey", "dataSource");
		params.put("subReportDataKeys", new String[] {"subDS"});

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(dataList));
        JRPdfExporter jrpdfexporter = new JRPdfExporter();
		jrpdfexporter.setParameters(getPdfExporterParameter(permissions, ownerPassword, userPassword));
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
	
	
	//listing file di dalam suatu directory
//	public static List<DropDown> listFilesInDirectoryRecursive(File destDir) {
//		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//		String error = validateDirectory(destDir);
//	    List<DropDown> result = new ArrayList<DropDown>();
//
//	    File[] filesAndDirs = destDir.listFiles();
//	    List filesDirs = Arrays.asList(filesAndDirs);
//	    Iterator filesIter = filesDirs.iterator();
//	    File file = null;
//	    
//	    while ( filesIter.hasNext() ) {
//	      file = (File) filesIter.next();
//	      result.add(new DropDown(file.getAbsolutePath(), df.format(new Date(file.lastModified())))); //always add, even if directory
//	      if (!file.isFile()) {
//	        //must be a directory
//	        //recursive call!
//	        List deeperList = listFilesInDirectoryRecursive(file);
//	        result.addAll(deeperList);
//	      }
//
//	    }
//	    return result;		
//	}
//	
//	  private static String validateDirectory(File aDirectory) {
//		if (aDirectory == null) {
//			return ("Directory should not be null.");
//		}
//		if (!aDirectory.exists()) {
//			return ("Directory does not exist: " + aDirectory);
//		}
//		if (!aDirectory.isDirectory()) {
//			return ("Is not a directory: " + aDirectory);
//		}
//		if (!aDirectory.canRead()) {
//			return ("Directory cannot be read: " + aDirectory);
//		}
//		return "";
//	}	
	
}
