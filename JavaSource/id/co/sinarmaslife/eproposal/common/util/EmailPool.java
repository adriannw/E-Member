package id.co.sinarmaslife.eproposal.common.util;

import id.co.sinarmaslife.eproposal.model.Email;
import id.co.sinarmaslife.eproposal.service.EproposalManager;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.util.FileCopyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EmailPool {
	protected final Log logger = LogFactory.getLog( getClass() );
	private SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	private EproposalManager eproposalManager;
	
	public void setEproposalManager(EproposalManager eproposalManager) {
		this.eproposalManager = eproposalManager;
	}
	
	public Boolean send(String me_id, String system_name, Integer show_system_name, Integer show_footer, Integer mec_id, Integer me_status, String me_status_message, 
					 Integer me_count, Integer lus_id, Date me_input_date, Date me_sent_date, boolean isHtml, String from, 
					 String[] to, String[] cc, String[] bcc, String subject, String message, List<File> attachments, Integer lje_id) {
		String desc	= "OK";
		try{
			Email email = new Email();
			String emailTo =null;
			String emailCc =null;
			String emailBcc =null;
			Integer me_html = 1;
			email.setMe_id(me_id);
			email.setMe_system(system_name);
			email.setMe_show_system(show_system_name);
			email.setMe_show_footer(show_footer); 
			email.setMe_from(from);
			for(int i=0;i<to.length;i++){
				if (!to[i].toLowerCase().contains("@agency")){
					if(i==0){
						emailTo = to[i]+";";
					}else{
						emailTo += to[i]+";";
					}
				}
			}
			if(cc!=null)for(int i=0;i<cc.length;i++){
				if (!to[i].toLowerCase().contains("@agency")){
					if(i==0){
						emailCc = cc[i]+";";
					}else{
						emailCc += cc[i]+";";
					}
				}
			}
			if(bcc!=null)for(int i=0;i<bcc.length;i++){
				if (!to[i].toLowerCase().contains("@agency")){
					if(i==0){
						emailBcc = bcc[i]+";";
					}else{
						emailBcc += bcc[i]+";";
					}
				}
			}
			email.setMe_to(emailTo);
			email.setMe_cc(emailCc);
			email.setMe_bcc(emailBcc);
			email.setMe_subject(subject);
			email.setMe_message(message);
			email.setMec_id(mec_id);
			if(isHtml==false)me_html=0;
			email.setMe_html(me_html);
			email.setMe_status(me_status);
			email.setMe_status_message(me_status_message);
			email.setMe_count(me_count);
			email.setMe_lus_id(lus_id);
			email.setMe_input_date(me_input_date);
			email.setMe_sent_date(me_sent_date);
			email.setLje_id(lje_id);
			if(!isEmpty(from)) {
				if(from.contains("@")){
					eproposalManager.insertMstEmail(email);
				}
			}
			
			if(attachments!=null){
			//ATTACHMENT==============================
				Integer months = me_input_date.getMonth()+1;
				Integer years = me_input_date.getYear()+1900;
				String outputDir = "\\\\storage\\mailpools\\attachments" +"\\" +years+"\\"+FormatString.rpad("0", months.toString(), 2)+"\\"+me_id;
				File dirFile = new File(outputDir);
				if(!dirFile.exists()) dirFile.mkdirs();
				//save file
				for(File file : attachments){
					File outputFile = new File(outputDir +"/"+ file.getName());
				    FileCopyUtils.copy(file, outputFile);
				}
			//=========================================
			}
			return true;
		}catch (Exception e) {
			desc = "ERROR";
			logger.error("ERROR", e);
			return false;
		}
		
	}
	
	public Boolean send(String system_name, Integer show_system_name, Integer show_footer, Integer mec_id, Integer me_status, String me_status_message, 
            Integer me_count, Integer lus_id, Date me_input_date, Date me_sent_date, boolean isHtml, String from, 
            String[] to, String[] cc, String[] bcc, String subject, String message, List<File> attachments) {
	    
	    return send(system_name, show_system_name, show_footer, mec_id, me_status, me_status_message, me_count, lus_id, me_input_date, me_sent_date, isHtml, from, to, cc, bcc, subject, message, attachments, 15);
	    
	}
	
	public Boolean send(String system_name, Integer show_system_name, Integer show_footer, Integer mec_id, Integer me_status, String me_status_message, 
			 					Integer me_count, Integer lus_id, Date me_input_date, Date me_sent_date, boolean isHtml, String from, 
			 					String[] to, String[] cc, String[] bcc, String subject, String message, List<File> attachments, Integer lje_id) {
		String desc	= "OK";
		try{
			Email email = new Email();
			String emailTo =null;
			String emailCc =null;
			String emailBcc =null;
			Integer me_html = 1;
		
			String me_id = eproposalManager.selectSeqEmailId();
			email.setMe_id(me_id);
			email.setMe_system(system_name);
			email.setMe_show_system(show_system_name);
			email.setMe_show_footer(show_footer); 
			email.setMe_from(from);
			for(int i=0;i<to.length;i++){
				if (!to[i].toLowerCase().contains("@agency")){
					if(i==0){
						emailTo = to[i]+";";
					}else{
						emailTo += to[i]+";";
					}
				}
			}
			if(cc!=null)for(int i=0;i<cc.length;i++){
				if (!cc[i].toLowerCase().contains("@agency")){
					if(i==0){
						emailCc = cc[i]+";";
					}else{
						emailCc += cc[i]+";";
					}
				}
			}
			if(bcc!=null)for(int i=0;i<bcc.length;i++){
				if (!bcc[i].toLowerCase().contains("@agency")){
					if(i==0){
						emailBcc = bcc[i]+";";
					}else{
						emailBcc += bcc[i]+";";
					}
				}
			}
			email.setMe_to(emailTo);
			email.setMe_cc(emailCc);
			email.setMe_bcc(emailBcc);
			email.setMe_subject(subject);
			email.setMe_message(message);
			email.setMec_id(mec_id);
			if(isHtml==false)me_html=0;
			email.setMe_html(me_html);
			email.setMe_status(me_status);
			email.setMe_status_message(me_status_message);
			email.setMe_count(me_count);
			email.setMe_lus_id(lus_id);
			email.setMe_input_date(me_input_date);
			email.setMe_sent_date(me_sent_date);
		
			email.setLje_id(lje_id);
			if(!isEmpty(from)) {
				if(from.contains("@")){
					eproposalManager.insertMstEmail(email);
				}
			}
			
			if(attachments!=null){
			//ATTACHMENT==============================
				Integer months = me_input_date.getMonth()+1;
				Integer years = me_input_date.getYear()+1900;
				String outputDir = "\\\\storage\\mailpools\\attachments" +"\\" +years+"\\"+FormatString.rpad("0", months.toString(), 2)+"\\"+me_id;
				File dirFile = new File(outputDir);
				if(!dirFile.exists()) dirFile.mkdirs();
				//save file
				for(File file : attachments){
					File outputFile = new File(outputDir +"/"+ file.getName());
				    FileCopyUtils.copy(file, outputFile);
				}
			//=========================================
			}
			return true;
		}catch (Exception e) {
			desc = "ERROR";
			logger.error("ERROR", e);
			return false;
		}
		
		}
	
	
	
	/**
	 * Fungsi untuk mengecek apakah suatu object kosong/null atau tidak
	 * @param cek Object yang akan di cek (dapat berupa String/Integer/Long/Double/Map/List/ etc...)
	 * @return boolean true apabila kosong, false bila tidak
	 */	
	public boolean isEmpty(Object cek) {
		if(cek==null) return true;
		else	if(cek instanceof String) {
			String tmp = (String) cek;
				if(tmp.trim().equals("")) return true;
				else return false;
		}else if(cek instanceof List) {
			List tmp = (List) cek;
			return tmp.isEmpty();
		}else if(cek instanceof Map){
			return ((Map) cek).isEmpty();
		}else if(cek instanceof Integer || cek instanceof Long|| cek instanceof Double|| cek instanceof Float|| cek instanceof BigDecimal || cek instanceof Date){
			return false;
		}
		return true;
	}
}
