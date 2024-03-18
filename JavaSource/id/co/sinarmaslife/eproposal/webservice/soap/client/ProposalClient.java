package id.co.sinarmaslife.eproposal.webservice.soap.client;

import id.co.sinarmaslife.eproposal.webservice.soap.server.Proposal;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProposalClient {
	protected final static Log logger = LogFactory.getLog( ProposalClient.class );
	
	public static void main(String[] args) throws Exception {

		URL url = new URL("http://localhost:8080/E-Proposal/Proposal?wsdl");

	        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
	        QName qname = new QName("http://server.soap.webservice.eproposal.sinarmaslife.co.id/", "ProposalImplService");

	        Service service = Service.create(url, qname);
	        
	        Proposal hello = service.getPort(Proposal.class);

	        //logger.info(hello.generateProposal());
	        logger.info( hello.generateProposal());

	    }

}
