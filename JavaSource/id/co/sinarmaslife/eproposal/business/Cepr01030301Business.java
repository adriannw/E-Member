package id.co.sinarmaslife.eproposal.business;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01030301Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 13, 2007 9:22:29 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000TextFileReader;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030301Form;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

public class Cepr01030301Business
{
    protected final Log logger = LogFactory.getLog( getClass() );
    protected EproposalManager eproposalManager;
    protected Cepr00000000EditorUtil editorUtil;
    protected String realPath;

    public Cepr01030301Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, HttpServletRequest request  )
    {
        this.eproposalManager = eproposalManager;
        this.editorUtil = editorUtil;
        this.realPath = request.getSession().getServletContext().getRealPath( "" );
    }

    public void resetForm( Cepr01030301Form cepr01030301Form ) throws IOException
    {
        cepr01030301Form.setContent( loadLetterContentFromFile() );
    }

    public String loadLetterContentFromFile() throws IOException
    {
        logger.info( "*-*-*-* Cepr01030301Business.loadLetterContentFromFile" );
        String fileName = "xepr_01030302_letter_content_1.txt";
        return Cepr00000000TextFileReader.loadReportContentFromTextFile( realPath, fileName );
    }

}
