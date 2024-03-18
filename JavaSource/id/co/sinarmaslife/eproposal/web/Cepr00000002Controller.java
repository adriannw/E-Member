package id.co.sinarmaslife.eproposal.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * User: samuel
 * Date: Jul 2, 2010
 * Time: 11:26:34 AM
 */
public class Cepr00000002Controller implements Controller
{
    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        ModelAndView result;
        Map<String,Object> params = new HashMap<String, Object>();
        String productId = request.getParameter("productId");
        params.put("productId", productId);
        result = new ModelAndView( "CEPR00000002MenuAtasJSP", params  );
        return result;
    }
}
