package id.co.sinarmaslife.standard.util;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: HtmlUtil
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 25, 2007 4:25:44 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import id.co.sinarmaslife.standard.model.vo.AssurancePlanList;
import id.co.sinarmaslife.standard.model.vo.OptionVO;

import java.util.List;

public class HtmlUtil
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public static List<OptionVO> addEmptyOption( List<OptionVO> optionVOList )
    {
        OptionVO optionVO;
        if( optionVOList != null && optionVOList.size() > 0 )
        {
            optionVO = optionVOList.get( 0 );
            if( !"".equals( optionVO.getValue() ) && optionVOList.size() > 1 )
            {
                optionVO = new OptionVO( "", "" );
                optionVOList.add( 0, optionVO );
            }
        }
        return optionVOList;
    }
    
    public static List<OptionVO> addEmptyOption2( List<OptionVO> optionVOList )
    {
        OptionVO optionVO;
        if( optionVOList != null && optionVOList.size() > 0 )
        {
            optionVO = optionVOList.get( 0 );
            optionVO = new OptionVO( "", "" );
            optionVOList.add( 0, optionVO );
        }
        return optionVOList;
    }
    
    public static List<AssurancePlanList> addEmptyProductOption( List<AssurancePlanList> optionVOList )
    {
        AssurancePlanList optionVO;
        if( optionVOList != null && optionVOList.size() > 0 )
        {
            optionVO = optionVOList.get( 0 );
            if( !"".equals( optionVO.getValue() ) && optionVOList.size() > 1 )
            {
                optionVO = new AssurancePlanList( "", "", null );
                optionVOList.add( 0, optionVO );
            }
        }
        return optionVOList;
    }

    /**
     *
     * @param msgList this is list og message
     * @return string that ready to use in function alert in Javascript
     */
    public static String genMsgStrInJsBox( List msgList )
    {
        
        StringBuffer result = new StringBuffer( "" );

        for( Object msg : msgList )
        {
            if( msg instanceof String )
            {
                result = result.append( msg.toString() ).append( "\\n" );
            }
            else
            {
                throw new RuntimeException( "Error in HtmlUtil.genMsgStrInJsBox because not all content in list is String Type " );  
            }
        }

        return result.toString();
    }
}
