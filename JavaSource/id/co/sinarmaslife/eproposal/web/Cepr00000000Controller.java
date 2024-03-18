package id.co.sinarmaslife.eproposal.web;

import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.standard.util.DownloadUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * User: samuel
 * Date: Jul 2, 2010
 * Time: 7:38:37 PM
 */
public class Cepr00000000Controller extends StandardParent implements Controller
{

    public ModelAndView handleRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException
    {
        logger.info( "*-*-*-* Cepr00000000Controller.handleRequest" );
        //String dirName = (request.getRequestURL().toString()).replace("/wepr01030601.pdf", "");
        String dirName = "C:\\Member";
        String fileName = "test.xls";
        DownloadUtil.downloadInline( request, response, dirName, fileName );

//        Harus return null karena response sudah dibuat oleh writer download
        return null;

    }
}
