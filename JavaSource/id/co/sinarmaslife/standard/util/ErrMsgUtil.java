package id.co.sinarmaslife.standard.util;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Agency
 * Function Id         	: 
 * Program Name   		: ErrMsgUtil
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jun 11, 2008 4:12:49 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.standard.StandardConst;

import javax.servlet.http.HttpServletRequest;

public class ErrMsgUtil
{

    public static void showAnError( HttpServletRequest request, String errMsg )
    {
        request.setAttribute( StandardConst.ERROR_MESSAGE_LIST, ArrUtil.toListFromArray( new String[]{ errMsg } ) );
    }

    public static void showErrors( HttpServletRequest request, String[] errMsgArr )
    {
        request.setAttribute( StandardConst.ERROR_MESSAGE_LIST, ArrUtil.toListFromArray( errMsgArr ) );
    }
}
