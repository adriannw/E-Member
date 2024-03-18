package id.co.sinarmaslife.eproposal.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

import id.co.sinarmaslife.eproposal.common.parent.StandardParent;

/**
 * User: samuel
 * Date: Jul 1, 2010
 * Time: 9:27:59 AM
 */
public class Cepr00000001Controller extends StandardParent implements Controller
{
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        // ModelAndView result;
        // result = new ModelAndView( "CEPR00000001ProductFrameJSP" );
        // return result;
    	 String id = ServletRequestUtils.getStringParameter(request, "foto");
    	//Document document = new Document();
    	 String dirName = "C:\\Member";
      //   String fileName = "proposal.pdf";
         String fileName = id+".pdf";
         
    	try {
    		response.setHeader("Content-Disposition", "inline;filename=file.pdf");
    		response.setHeader("Expires","0");
    		response.setHeader("Cache-Control", "must-revalidate,post-check=0, pre-check=0");
    		response.setHeader("Pragma", "public");
    		response.setContentType("application/pdf");
    		
    		
    		
    		PdfReader reader = null;
    		try {
    			reader = new PdfReader(dirName+"\\"+fileName);
    		}
    		catch (IOException ioe3) {
    			
    		}
    		
    		ServletOutputStream sos = response.getOutputStream();
    		PdfStamper stamper = new PdfStamper(reader,sos);
    		if(stamper != null) {
    			stamper.close();
    		}
    		if(sos!=null){
    			sos.flush();
    			sos.close();
    		}
    		if(reader!=null) {
    			reader.close();
    		}
    		
    		
    	}catch(DocumentException de) {
    		ServletOutputStream out= response.getOutputStream();
    		out.println("<script>alert('tes');</script>");
    		out.flush();
    	}
    	
    	return null;
    	
    	
    	
    }
}