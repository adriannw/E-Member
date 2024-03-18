package id.co.sinarmaslife.servlet;

import id.co.sinarmaslife.std.util.Report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.Thread.State;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

import org.springframework.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibatis.common.resources.Resources;

/**
 * @author Andy
 */
public class JasperCompilerServletListener implements ServletContextListener {

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
	
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		//logger.info("ServletContextListener destroyed");
		logger.info("ServletContextListener destroyed");
		closeConnectionAndUnregisterThem();
	}
	
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		logger.info( "*-*-*-* JasperCompilerServlet.init" );
		try {
			setProps(Resources.getResourceAsProperties("eproposal.properties"));
			setJdbc(Resources.getResourceAsProperties("jdbc.properties"));
		}catch(IOException e) {
			logger.error(e.getMessage());
		}
		
		compile(props.getProperty("compile_reports_on_startup").equals("1"), servletContextEvent);
	}

	public void compile(boolean yesIwantToCompile, ServletContextEvent servletContextEvent) {
        logger.info( "*-*-*-* JasperCompilerServlet.compile" );
		if(yesIwantToCompile) {
			try {
				String[] compileReports = null;
				String[] compileResults = null;
				List<String> reportList = Report.listAllReports(props);
				String[] allReports = StringUtils.commaDelimitedListToStringArray(StringUtils.collectionToCommaDelimitedString(reportList));
				compileReports = allReports;
				compileResults = Report.compileReports(compileReports, servletContextEvent.getServletContext(), props);

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
	
	/**
	 * Patar Timotius
	 */
	private void closeConnectionAndUnregisterThem(){
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            if (driver.getClass().getClassLoader() == cl) {
            try {
                DriverManager.deregisterDriver(driver);
                logger.info("driver:"+driver);
                
               // LOG.log(Level.INFO, String.format("deregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
            	System.out.println(e);
            	logger.info("e:"+e.getMessage());
                
            	//  LOG.log(Level.SEVERE, String.format("Error deregistering driver %s", driver), e);
            }
            };

        }
        
	}
	
	
}
