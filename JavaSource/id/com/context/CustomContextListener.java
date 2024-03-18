package id.com.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

public class CustomContextListener extends ContextLoaderListener {

	private  static ServletContext servletContextSpring;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		super.contextInitialized(event);
		
		event.getServletContext();
		
		if(servletContextSpring == null){
			this.servletContextSpring = event.getServletContext();
		}
		
	}

	public static ServletContext getSpringServletContext(){
		return servletContextSpring;
	}

	



}
