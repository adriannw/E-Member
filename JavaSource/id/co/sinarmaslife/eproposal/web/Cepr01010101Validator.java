package id.co.sinarmaslife.eproposal.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import id.co.sinarmaslife.eproposal.business.Cepr01010101Business;
import id.co.sinarmaslife.eproposal.service.EproposalManager;

//import sun.nio.cs.ext.MSISO2022JP;

public class Cepr01010101Validator implements Validator
{
    protected final Log logger = LogFactory.getLog( getClass() );
    Cepr01010101Business cepr01010101Business;
    EproposalManager eproposalManager;

    public void setCepr01010101Business( Cepr01010101Business cepr01010101Business )
    {
        this.cepr01010101Business = cepr01010101Business;
    }
    

    public void setEproposalManager(EproposalManager eproposalManager) {
		this.eproposalManager = eproposalManager;
	}


	public boolean supports( Class clazz )
    {
        return clazz.equals( Cepr01010101Form.class );
    }
	
	

    public void validate( Object obj, Errors errors )
    {
    	// Do nothing
    }

  

}
