package id.co.sinarmaslife.eproposal.web;

import id.co.sinarmaslife.eproposal.common.util.ParentMultiController;
import id.co.sinarmaslife.standard.model.vo.DropDown;
import id.co.sinarmaslife.standard.util.FileUtils;
import id.co.sinarmaslife.std.util.Session;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Yusuf
 *
 */
public class UpdateMultiController extends ParentMultiController {
	protected final Log logger = LogFactory.getLog(getClass());
	
	public ModelAndView proposal(HttpServletRequest request, HttpServletResponse response)throws ServletRequestBindingException, ParseException{
		
		String download = ServletRequestUtils.getStringParameter(request, "download", "");
		String dir = props.getProperty("upload.proposal");
		
		if(!download.equals("")) {

			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				File file = new File(dir+"//"+download);
				Date modified_date = sdf.parse(sdf.format(file.lastModified()));
				Map map = new HashMap();
				map.put("jenis", "Proposal");
				map.put("filename", download);
				map.put("modified_date", modified_date);
				String filetype ="";
				if(download.contains("upas")){
					filetype="Update Proposal Agency";
				}else if(download.contains("upbsm")){
					filetype="Update Proposal Bank Sinarmas";
				}else if(download.contains("update")){
					filetype="Update Proposal Regional";
				}else if(download.contains("upfa")){
					filetype="Update Proposal Bancassurance";
				}else if(download.contains("Full_Agency_System")){
					filetype="Proposal Agency System";
				}else if(download.contains("full_bsm_Syariah")){
					filetype="Proposal BSM Syariah";
				}else if(download.contains("full_bsm")){
					filetype="Proposal BSM";
				}else if(download.contains("Full_Regional")){
					filetype="Proposal Regional";
				}
				map.put("filetype", filetype);
				FileUtils.downloadFile("attachment;", dir, download, response);
			} catch (FileNotFoundException e) {
				logger.error("ERROR", e);
			} catch (IOException e) {
				logger.error("ERROR", e);
			}
			return null;

		}else {
			
			Map cmd = new HashMap();
			List<DropDown> daftarFile = FileUtils.listFilesInDirectory(dir);
			
			for(DropDown d : daftarFile) {
				if(d.getKey().toLowerCase().startsWith("upas_")) {
					d.setDesc("Update Proposal hanya untuk Agency System (Untuk Regional Tidak perlu download proposal yang Agency System)");
				}else if(d.getKey().toLowerCase().startsWith("update_absen")) {
					d.setDesc("Update Absensi");
				}else if(d.getKey().toLowerCase().startsWith("update_")) {
					d.setDesc("Update Proposal Regional (Proposal Biasa)");
				}else if(d.getKey().toLowerCase().startsWith("upfa_")) {
					d.setDesc("Update Proposal Bancassurance");
				}else if(d.getKey().toLowerCase().startsWith("upbsm_")) {
					d.setDesc("Update Proposal Bank Sinarmas");
				}else {
					d.setDesc("");
				}
			}
			
			cmd.put("daftarFile", daftarFile);
			return new ModelAndView("update_proposal/update", cmd);
			
		}
	}



}
	