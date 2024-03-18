package id.co.sinarmaslife.eproposal.webservice.soap.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

//Service Implementation Bean

@WebService
//@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface Proposal{

	@WebMethod String generateProposal();
	
}

