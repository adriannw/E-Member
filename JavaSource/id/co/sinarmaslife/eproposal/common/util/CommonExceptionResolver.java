package id.co.sinarmaslife.eproposal.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.standard.StandardConst;


/**
 * Class yang digunakan untuk me-redirect error yang terjadi di aplikasi ke
 * halaman bersangkutan (view bernama 'exception')
 * 
 * @author Yusuf
 * @since 01/11/2005
 */
public class CommonExceptionResolver implements HandlerExceptionResolver {

	protected final Log logger = LogFactory.getLog( getClass() );
	
	
	private String jdbcName;
	private EmailPool emailPool;
	
	public void setJdbcName(String jdbcName) {this.jdbcName = jdbcName;}	
	

	/**
	 * Fungsi untuk menampilkan error pada aplikasi ke halaman 'exception'
	 * 
	 * @param request
	 *            Object request
	 * @param response
	 *            Object response
	 * @param handler
	 *            Object handler
	 * @param exception
	 *            Exception yang terjadi
	 * @return ModelAndView 'exception'
	 */
	
//	Servlet.service() for servlet [spring] in context with path [] threw exception [Request processing failed; nested exception is java.lang.NullPointerException] with root cause
	
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception) {
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy (hh:mm:ss)");
		Date now = new Date();//DateUtil.selectSysdate();//eproposalManager.selectNowDate();
		String pengirim = "";
		CredentialsVO currentUser = (CredentialsVO) request.getSession().getAttribute( StandardConst.CREDENTIALS_SES );		
      //  User currentUser = (User) request.getSession().getAttribute("currentUser");
		logger.info( "*-*-*-* CommonExceptionResolver-resolveException");
		StringBuffer stackTrace = new StringBuffer();
		stackTrace.append("\n===== START ERROR [E-Proposal][" + df.format(now) + "] ===============");
		logger.info( "Class " +handler);
		if(currentUser!=null){
        	logger.info( " Agen "+currentUser.getUserName() );
        	pengirim = "Pesan dikirim oleh : " + currentUser.getUserName() + " ["+currentUser.getMsagId()+"]\n\n";
    		stackTrace.append("\n- User : " + currentUser.getUserName() + " [" + currentUser.getLusId() + "] ");
        }
		stackTrace.append("\n\n- Class : " + handler);
		stackTrace.append("\n- Request Parameters : ");
		Enumeration paramNames = request.getParameterNames();
		if(paramNames!=null){
			while(paramNames.hasMoreElements()) {
    			String paramName = (String) paramNames.nextElement();
    			stackTrace.append("\n   " + paramName + " = " + request.getParameter(paramName));
    		}
		}
		
		//get cepr01030101Form params
		try {
			stackTrace.append("\n\ntry get cepr01030000HoldingForm");
			Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
	        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
	        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
	        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
	        stackTrace.append("\ntry get cepr01030101Form param");
	        getObject(cepr01030101Form);
	        stackTrace.append("\ntry get cepr01030102Form param");
	        getObject(cepr01030102Form);
		}catch (Exception e) {
			stackTrace.append("\nfailed get cepr01030000HoldingForm params");
		}

    		stackTrace.append("\n- Request QueryString : " + request.getQueryString());
    		stackTrace.append("\n- Request URL : " + request.getRequestURL());		

    		stackTrace.append("\n- Exception : \n");
    		StringWriter sw = null;
    		PrintWriter pw = null;
    		try{
    		sw = new StringWriter();
    		pw = new PrintWriter(sw);
    		Throwable e = getRootCause(exception); //get root exception		
    		e.printStackTrace(pw);
    		stackTrace.append(sw);
    		
    		stackTrace.append("\n- Request Headers: ");
    		Enumeration headerNames = request.getHeaderNames();
    		while(headerNames.hasMoreElements()) {
    			String headerName = (String) headerNames.nextElement();
    			stackTrace.append("\n  " + headerName + " = " + request.getHeader(headerName));
    		}
    		
    		stackTrace.append("\n- Request Method : " + request.getMethod());
    		stackTrace.append("\n- Request Protocol : " + request.getProtocol());
    		stackTrace.append("\n- Request RemoteAddr : " + request.getRemoteAddr());
    		stackTrace.append("\n- Request RemoteHost : " + request.getRemoteHost());
    		stackTrace.append("\n- Request RemotePort : " + request.getRemotePort());
    		stackTrace.append("\n- Request RequestURI : " + request.getRequestURI());
    		stackTrace.append("\n- Request Scheme : " + request.getScheme());
    		stackTrace.append("\n- Request ServerName : " + request.getServerName());
    		stackTrace.append("\n- Request ServerPort : " + request.getServerPort());
    		stackTrace.append("\n- Request ServletPath : " + request.getServletPath());
    		stackTrace.append("\n- Session No Pre: " + request.getSession().getAttribute("no_pre"));		
    				
    		stackTrace.append("\n\n===== END ERROR [E-Proposal][" + df.format(now) + "] ===============");

    		}finally{
    			try {
    				if(sw!=null){
    					sw.flush();
    					sw.close();
    				}
    				if(pw!=null){
    					pw.flush();
    					pw.close();
    				}
    				
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				logger.error("ERROR", e);
    			}
    		}
    		
    		logger.error(stackTrace);
//    		logger.info(stackTrace);
    		
    		/**
    		 * klo error max upload size maka alert aja
    		 */
    		if( stackTrace.toString().contains("SizeLimitExceededException")){
    	 		ServletOutputStream out = null;
    			try {
    				out = response.getOutputStream();
    				out.println("<script>alert('Maksimum ukuran file Upload 5 Mb terlampaui. /nHarap upload file dengan ukuran di bawah ukuran maksimal.');history.go(-1);</script>");
    	    		
    	    		
    			} catch( Exception e1) { 
    				// TODO Auto-generated catch block
    				logger.error( e1 );
    			}finally{
    				try {
    					if(out!=null){
    						out.flush();
    						out.close();
    					}
    					
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					logger.error("ERROR", e);
    				}
    			}
    			return null;
    		}
    		
    		
    		
    		
    		
    		emailPool.send("SMiLe E-Proposal", 1, 1, 0, 0, 
    				null, 0, 0, now, null, 
    				false,
    				 new String("ajsjava@sinarmasmsiglife.co.id"),
    				//Yusuf (8 Feb 2012) Penambahan daftar penerima email error, agar tahu bila ada error
    				//TOLONG konfirmasi dulu ke saya kalau mau menambah/kurang email penerima ini.
    				new String[] {
    					"adrian_n@sinarmasmsiglife.co.id", 
    					"iga.ukiarwan@sinarmasmsiglife.co.id"},    					
    				null, null, 
    				"ERROR pada E-Proposal [" + (jdbcName != null ? jdbcName : "") + "]", 
    				pengirim + stackTrace.toString(), 
    				null);
    		

		return new ModelAndView("exception");
	}
	
	public void getObject(Object obj) throws IllegalArgumentException, IllegalAccessException {
	    for (Field field : obj.getClass().getDeclaredFields()) {
	        field.setAccessible(true); // if you want to modify private fields
	        logger.error("\n-" + field.getName()
	                 + " - " + field.getType()
	                 + " - " + field.get(obj));
	    }
	}

	/**
	 * Fungsi rekursif untuk mengambil exception paling atas (sumber dari exception), 
	 * agar tahu errornya apa, dan jelas error message nya
	 * 
	 * @author Yusuf
	 * @since 6 Feb 2012
	 * @param ex exception yang di throw
	 * @return exception parent nya
	 */
	public static Throwable getRootCause(Throwable ex){
		Throwable bapak = ex.getCause();
		if(bapak != null) return getRootCause(bapak);
		else return ex;
	}

	public void setEmailPool(EmailPool emailPool) {
		this.emailPool = emailPool;
	}

}
