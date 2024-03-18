package id.co.sinarmaslife.eproposal.common.util;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr00000000Generator
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 5, 2008 2:40:16 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import id.co.sinarmaslife.standard.model.vo.OptionVO;

import java.util.List;
import java.util.ArrayList;

public class Cepr00000000Generator
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr00000000Generator()
    {
        logger.info( "*-*-*-* Cepr00000000Generator constructor is called ..." );
    }

    public static List<OptionVO> genMoneyList( Cepr00000000EditorUtil editorUtil, double start, double end, double step, boolean isEmptyAdded )
    {
        List<OptionVO> result = new ArrayList<OptionVO>();
        OptionVO optionVO;

        if( isEmptyAdded )
        {
            optionVO = new OptionVO( "", "" );
            result.add( optionVO );
        }

        String valueStr;
        for( double i = start; i <= end; i = i + step )
        {
            valueStr = editorUtil.convertToString( i );
            optionVO = new OptionVO( valueStr, valueStr );
            result.add( optionVO );
        }
        return result;
    }

    public static List<OptionVO> genMoneyList( Cepr00000000EditorUtil editorUtil, double start, double end, double step )
    {
        return genMoneyList( editorUtil, start, end, step, true);
    }

    public static List<OptionVO> genMoneyList( Cepr00000000EditorUtil editorUtil, int start, int end, int step )
    {
        return genMoneyList( editorUtil, start, end, step, true);
    }
}
