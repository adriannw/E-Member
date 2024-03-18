package id.co.sinarmaslife.eproposal.business;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr00000000GeneralBusiness
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jun 19, 2007 10:30:01 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000DownloadInterface;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.std.util.DateUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.Map;

public class Cepr00000000GeneralBusiness
{
    protected final Log logger = LogFactory.getLog( getClass() );

    private static Cepr00000000GeneralBusiness instance;
    protected EproposalManager eproposalManager;
    protected Cepr00000000EditorUtil editorUtil;

    public Cepr00000000GeneralBusiness( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil )
    {
        this.eproposalManager = eproposalManager;
        this.editorUtil = editorUtil;
    }

    public static Cepr00000000GeneralBusiness getInstance( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil )
    {
        if( instance == null )
        {
            instance = new Cepr00000000GeneralBusiness( eproposalManager, editorUtil );
        }
        return instance;
    }

    public Date selectNowDate()
    {
        logger.info( "*-*-*-* eproposalManager = " + eproposalManager );
        return eproposalManager.selectNowDate();
        //return eproposalManager.selectNowDate();
    }

    public static void putDownloadUrlIntoMap( Cepr01040000DownloadInterface download, Map<String, Object> map )
    {
        map.put( CommonConst.SES_DOWNLOAD_URL_SESSION, download.getDownloadUrl() );
    }

    public static void putDownloadUrlIntoSession( Cepr01040000DownloadInterface download, Map<String, Object> map )
    {
        map.put( "downloadUrlSession", download.getDownloadUrl() );
    }

}

