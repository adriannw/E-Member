package id.co.sinarmaslife.eproposal.web;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	:
 * Program Name   		: Cepr01010101Interceptor
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 5, 2008 9:10:40 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import id.co.sinarmaslife.eproposal.business.Cepr01010101Business;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.standard.StandardConst;
import id.co.sinarmaslife.standard.model.vo.UserGroup;

/**
 * Interceptor ini merupakan bean spring yang berguna untuk meng-intercept setiap kali
 * sebuah controller diakses. Saat ini penggunaannya untuk membatasi akses apabila seorang user belum LOGIN
 *
 * @author Yusuf Sutarko
 * @since Mar 21, 2007 (10:44:52 AM)
 */
public class Cepr01010101Interceptor extends HandlerInterceptorAdapter
{

    protected final Log logger = LogFactory.getLog( getClass() );
    
    protected EproposalManager eproposalManager;
    Cepr01010101Business cepr01010101Business;
    protected String web="";
    
    public void setCepr01010101Business( Cepr01010101Business cepr01010101Business )
    {
        this.cepr01010101Business = cepr01010101Business;
    }
    public void setEproposalManager( EproposalManager eproposalManager )
    {
        this.eproposalManager = eproposalManager;
    }

    /**
     * Pada fungsi preHandle ini diisi LOGIC untuk memblok / membolehkan
     * seorang user untuk mengakses sebuah controller
     * Saat ini logicnya hanya memblok apabila seorang user belum LOGIN
     */

    
    
    private void doSetProductAccessRightAsDefault( String msagId, String groupIdParam, String aplikasi )
    {
        logger.info( "*-*-*-* Cepr01010101Validator.doSetProductAccessRightAsDefault" );
        
        String groupName = "";
        String groupId = "";
        if( groupIdParam != null && groupIdParam.equals( Hardcode.GROUP_MNC ) )
        {
        	groupName = "mnc";
        	groupId = Hardcode.GROUP_MNC;
        }
        else
        {
	        String lcaId = cepr01010101Business.selectAgentType( msagId );
	        if( aplikasi != null && "eagency".equals( aplikasi ) ){
	        	lcaId = "37";
	        }
	        if( lcaId.equals( "46" ) )
	        {
	            groupName = "hybrid";
	        }
	        else if( lcaId.equals( "37" ) )
	        {
	            groupName = "agency";
	        }
	        else if( !lcaId.equals( "08" ) && !lcaId.equals( "09" ) && !lcaId.equals( "37" ) && !lcaId.equals( "40" ) && !lcaId.equals( "46" ) )
	        {
	            groupName = "regional";
	        }
	        else
	        {
	            groupName = "default";
	        }
	
	        groupId = cepr01010101Business.selectGroupId(groupName);
        }
        if( groupId != null )
        {
            cepr01010101Business.insertNewUserProduct( msagId, groupId );
        }
        else
        {
            throw new RuntimeException( "Cepr01010101Validator.insertNewUserProductAccess: database doesn't have "+groupName+" product access group" );
        }
    }

}