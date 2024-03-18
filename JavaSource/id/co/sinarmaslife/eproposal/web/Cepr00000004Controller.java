package id.co.sinarmaslife.eproposal.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * User: samuel
 * Date: Jul 2, 2010
 * Time: 11:28:11 AM
 */
public class Cepr00000004Controller implements Controller
{
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        ModelAndView result;
        result = new ModelAndView( "CEPR00000004MainJSP" );
        return result;
    }
}
