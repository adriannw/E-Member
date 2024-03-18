package id.co.sinarmaslife.eproposal.web;

import id.co.sinarmaslife.eproposal.service.EproposalManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * User: samuel
 * Date: Jul 2, 2010
 * Time: 11:26:45 AM
 */
public class Cepr00000003Controller implements Controller
{
	protected EproposalManager eproposalManager;
	
	
    public void setEproposalManager(EproposalManager eproposalManager) {
		this.eproposalManager = eproposalManager;
	}

	public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
        ModelAndView result;
        
        Object currentUser = request.getSession().getAttribute("currentUser");
        Cepr01010101Form cepr01010101Form = (Cepr01010101Form) currentUser;
        Map<String,Object> params = new HashMap<String, Object>();
        List<String> lsbsIdList = eproposalManager.selectLsbsIdByMsagIdList(cepr01010101Form.getMsagId());
        params.put("lsbsIdList", lsbsIdList);
        result = new ModelAndView( "CEPR00000003MenuBawahJSP", params );
        return result;
    }
}
