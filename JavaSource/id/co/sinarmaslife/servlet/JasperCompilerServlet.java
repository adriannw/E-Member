package id.co.sinarmaslife.servlet;

import id.co.sinarmaslife.std.util.Report;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

import org.springframework.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibatis.common.resources.Resources;

/**
 * @author Yusuf
 */
public class JasperCompilerServlet extends HttpServlet {

    protected final Log logger = LogFactory.getLog( getClass() );
    private static final long serialVersionUID = -3093269239617279770L;
	private Properties props;
	private Properties jdbc;
	
	public void setJdbc(Properties jdbc) {
		this.jdbc = jdbc;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String reportName;
		
		if((reportName = request.getParameter("report"))!=null) {
			ServletContext context = this.getServletConfig().getServletContext();
			logger.debug("Compiling " + reportName);
           // logger.info( "*-*-*-* Compiling " + reportName );
			String value = props.getProperty(reportName);
			value = "/WEB-INF/classes/" + value + ".jrxml";
			try {
				JasperCompileManager.compileReportToFile(context.getRealPath(value));
			} catch (JRException e) {
				logger.error("ERROR", e);
			}
		}else {
			compile(true);
		}
		
		response.sendRedirect(request.getContextPath()+"/common/console.htm");
	}

	public void init() throws ServletException {
        logger.info( "*-*-*-* JasperCompilerServlet.init" );
		try {
			setProps(Resources.getResourceAsProperties("eproposal.properties"));
			setJdbc(Resources.getResourceAsProperties("jdbc.properties"));
		}catch(IOException e) {
			logger.error("ERROR", e);
		}
		
		compile(props.getProperty("compile_reports_on_startup").equals("1"));
	}

	public void compile(boolean yesIwantToCompile) {
        logger.info( "*-*-*-* JasperCompilerServlet.compile" );
		if(yesIwantToCompile) {
			try {
				String[] compileReports = null;
				String[] compileResults = null;
				List<String> reportList = Report.listAllReports(props);
				String[] allReports = StringUtils.commaDelimitedListToStringArray(StringUtils.collectionToCommaDelimitedString(reportList));
				compileReports = allReports;
				compileResults = Report.compileReports(compileReports, getServletContext(), props);

				for(int i=0; i<compileReports.length; i++) {
					if(compileResults[i].equals("SUCCESS")) logger.info("REPORT COMPILED : " + compileReports[i]);
					else logger.error("ERROR : " + compileResults[i]);
				}
			} catch (Exception ioe) {
				logger.error(ioe.getMessage());
				//ioe.printStackTrace();
			}		
		}
	}
	
}
