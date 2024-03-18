package id.co.sinarmaslife.eproposal.common.lookup.source;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: NumberLookup
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 1, 2007 4:24:48 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.standard.model.vo.OptionVO;

import java.util.ArrayList;
import java.util.List;

public class NumberLookup
{
    public static List<OptionVO> getSequenceNumberList( int a, int b, int step, boolean addEmptyOption )
    {
        List<OptionVO> list = new ArrayList<OptionVO>();

        OptionVO optionVO;
        if( addEmptyOption )
        {
            optionVO = new OptionVO( "", "" );
            list.add( optionVO );
        }

        for( int i = a; i <= b; i = i + step )
        {
            optionVO = new OptionVO( Integer.toString( i ), Integer.toString( i ) );
            list.add( optionVO );
        }
        
        return list;
    }

    public static List<OptionVO> getSequenceNumberList( int a, int b, boolean addEmptyOption )
    {
        return getSequenceNumberList( a, b, 1, addEmptyOption );
    }

    public static List<OptionVO> getPercentTwoDigitsSequenceList( int[] percentArr )
    {
        List<OptionVO> list = new ArrayList<OptionVO>();

        OptionVO optionVO;
        String percentStr;
        for( int percent : percentArr )
        {
            percentStr = Integer.toString( percent );
            optionVO = new OptionVO( percentStr.concat( ".00" ), percentStr + "%" );
            list.add( optionVO );
        }
        return list;
    }
}
