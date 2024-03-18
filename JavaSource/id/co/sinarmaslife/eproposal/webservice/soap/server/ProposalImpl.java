package id.co.sinarmaslife.eproposal.webservice.soap.server;

import javax.jws.WebService;

//Service Implementation Bean

@WebService(endpointInterface = "id.co.sinarmaslife.eproposal.webservice.soap.server.Proposal")
public class ProposalImpl implements Proposal{

	public String generateProposal() {
		return "Hello World JAX-WS";
	}
}

