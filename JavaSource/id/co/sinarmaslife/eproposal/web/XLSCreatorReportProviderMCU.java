package id.co.sinarmaslife.eproposal.web;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import id.co.sinarmaslife.standard.model.vo.ViewListProviderDetail;


public class XLSCreatorReportProviderMCU {
	//@Override
	public void buildExcelDocument( String fileName, String dirFileName, List<ViewListProviderDetail> ListForRpm) throws Exception {
			
		HSSFWorkbook workBook = new HSSFWorkbook();
		
		HSSFSheet sampleDataSheet 	= workBook.createSheet(fileName);
		sampleDataSheet.setDefaultColumnWidth((short)12);
			
		 SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		
		   HSSFCellStyle styleHeader, styleHeader2, styleHeader3, styleHeader4;
			HSSFFont titleFont = workBook.createFont();
			titleFont.setColor(HSSFColor.BLACK.index);
	        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        titleFont.setFontHeight((short) 230);
	        HSSFFont titleFont3 = workBook.createFont();
			titleFont3.setColor(HSSFColor.BLACK.index);
	        titleFont3.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	        	       
	        styleHeader2 = workBook.createCellStyle();
			styleHeader2.setFont(titleFont);
			styleHeader = workBook.createCellStyle();
			styleHeader.setBorderBottom((short) 1);
			styleHeader.setBorderLeft((short) 1);
			styleHeader.setBorderRight((short) 1);
			styleHeader.setBorderTop((short) 1);
			styleHeader.setFont(titleFont);
			styleHeader.setWrapText(true);
			styleHeader.setAlignment((short)2);			
				
			styleHeader3 = workBook.createCellStyle();		
			styleHeader3.setBorderLeft((short) 1);
			styleHeader3.setBorderRight((short) 1);	
			styleHeader3.setFont(titleFont3);
			styleHeader3.setWrapText(true);
			styleHeader4 = workBook.createCellStyle();		
			styleHeader4.setBorderLeft((short) 1);
			styleHeader4.setBorderRight((short) 1);
			styleHeader4.setBorderBottom((short)1);
			styleHeader4.setFont(titleFont3);
			styleHeader4.setWrapText(true);
		 
		HSSFRow headerRow0 = sampleDataSheet.createRow(0);
		HSSFRow headerRow1 = sampleDataSheet.createRow(1);
		HSSFRow headerRow2 = sampleDataSheet.createRow(2);
				
		HSSFCell firstHeaderCellh0 = headerRow0.createCell((short) 0);
		firstHeaderCellh0.setCellValue(new HSSFRichTextString("REPORT LISTING MEMBER"));
		
		HSSFCell firstHeaderCellh1 = headerRow1.createCell((short) 0);
		firstHeaderCellh1.setCellValue(new HSSFRichTextString(""));
		HSSFCell firstHeaderCell = headerRow2.createCell((short) 0);	
		firstHeaderCell.setCellValue(new HSSFRichTextString("NO"));
		firstHeaderCell.setCellStyle(styleHeader);
		firstHeaderCell = headerRow2.createCell((short) 1);
		firstHeaderCell.setCellValue(new HSSFRichTextString("NAMA MEMBER"));
		firstHeaderCell.setCellStyle(styleHeader);
		firstHeaderCell = headerRow2.createCell((short) 2);
		firstHeaderCell.setCellValue(new HSSFRichTextString("NO HP"));
		firstHeaderCell.setCellStyle(styleHeader);
		firstHeaderCell = headerRow2.createCell((short) 3);
		firstHeaderCell.setCellValue(new HSSFRichTextString("TGL LAHIR"));
		firstHeaderCell.setCellStyle(styleHeader);
		firstHeaderCell = headerRow2.createCell((short) 4);
		firstHeaderCell.setCellValue(new HSSFRichTextString("EMAIL"));
		firstHeaderCell.setCellStyle(styleHeader);
		firstHeaderCell = headerRow2.createCell((short) 5);
		firstHeaderCell.setCellValue(new HSSFRichTextString("no_ktp"));
		firstHeaderCell.setCellStyle(styleHeader);
		firstHeaderCell = headerRow2.createCell((short) 6);
		
								
		sampleDataSheet.setColumnWidth((short) 0, (short)(1300));		
		sampleDataSheet.setColumnWidth((short) 1, (short)(12000));
		sampleDataSheet.setColumnWidth((short) 2, (short)(15000));
		sampleDataSheet.setColumnWidth((short) 3, (short)(9000));
		sampleDataSheet.setColumnWidth((short) 4, (short)(5000));
		sampleDataSheet.setColumnWidth((short) 5, (short)(3000));
		sampleDataSheet.setColumnWidth((short) 6, (short)(13000));
	
				
		
		firstHeaderCellh0.setCellStyle(styleHeader2);
		firstHeaderCellh1.setCellStyle(styleHeader2);
							
		int row = 3;
		int no=1;
		
		for(int i=0; i<ListForRpm.size(); i++) {
			String nomor = String.valueOf(no);
			HSSFRow dataRow1 = sampleDataSheet.createRow(row+i);
			
			 HSSFCell celln= dataRow1.createCell((short) 0);		
			 celln.setCellValue(new HSSFRichTextString(nomor));
			
			 if(no==ListForRpm.size()){
				 celln.setCellStyle(styleHeader4);	}else
			 {
					 celln.setCellStyle(styleHeader3); 
			 }
			 
			 celln= dataRow1.createCell((short) 1);	
			String namaRs = ListForRpm.get(i).getNama();
			celln.setCellValue(new HSSFRichTextString(namaRs));
			 if(no==ListForRpm.size()){
				 celln.setCellStyle(styleHeader4);	}else
			 {
					 celln.setCellStyle(styleHeader3); 
			 }
			 
			
			
			
			
			 celln= dataRow1.createCell((short) 2);	
			 String noHp = ListForRpm.get(i).getNomor_hp();
			 if(ListForRpm.get(i).getNomor_hp() == null){
					celln.setCellValue(new HSSFRichTextString(""));
	        	}else{
	        		celln.setCellValue(new HSSFRichTextString(noHp));
	        	}
			 if(no==ListForRpm.size()){
				 celln.setCellStyle(styleHeader4);	}else
			 {
					 celln.setCellStyle(styleHeader3); 
			 }
			
		
			
			celln= dataRow1.createCell((short)3);	
			String teleponRs = ListForRpm.get(i).getTgl_lahir();
			if(ListForRpm.get(i).getTgl_lahir() == null){
				celln.setCellValue(new HSSFRichTextString(""));
        	}else{
        		celln.setCellValue(new HSSFRichTextString(teleponRs));
        	}
			if(no==ListForRpm.size()){
				 celln.setCellStyle(styleHeader4);	}else
			 {
					 celln.setCellStyle(styleHeader3); 
			 }
			
			celln= dataRow1.createCell((short)4);	
			String email = ListForRpm.get(i).getEmail();
			if(ListForRpm.get(i).getEmail() == null){
				celln.setCellValue(new HSSFRichTextString(""));
        	}else{
        		celln.setCellValue(new HSSFRichTextString(email));
        	}
			if(no==ListForRpm.size()){
				 celln.setCellStyle(styleHeader4);	}else
			 {
					 celln.setCellStyle(styleHeader3); 
			 }
			
			celln= dataRow1.createCell((short)5);	
			String noKtp = ListForRpm.get(i).getNo_ktp();
			if(ListForRpm.get(i).getNo_ktp() == null){
				celln.setCellValue(new HSSFRichTextString(""));
        	}else{
        		celln.setCellValue(new HSSFRichTextString(noKtp));
        	}
			if(no==ListForRpm.size()){
				 celln.setCellStyle(styleHeader4);	}else
			 {
					 celln.setCellStyle(styleHeader3); 
			 }
		
			no++;
		}			
						
		FileOutputStream fileOutputStream = new FileOutputStream(dirFileName+"\\"+fileName);
		workBook.write(fileOutputStream);
		fileOutputStream.close();	
	}
}

