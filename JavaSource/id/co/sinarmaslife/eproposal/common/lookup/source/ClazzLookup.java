package id.co.sinarmaslife.eproposal.common.lookup.source;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	:
 * Program Name   		: GenderLookup
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jun 19, 2007 8:55:20 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.standard.model.vo.OptionVO;

import java.util.ArrayList;
import java.util.List;

public class ClazzLookup
{
    private static List<OptionVO> clazzList;

    public static List<OptionVO> getClazzList()
    {
        if( clazzList == null )
        {
            clazzList = new ArrayList<OptionVO>();

            OptionVO optionVO;
            optionVO = new OptionVO( "1", "1" );
            clazzList.add( optionVO );
            optionVO = new OptionVO( "2", "2" );
            clazzList.add( optionVO );
            optionVO = new OptionVO( "3", "3" );
            clazzList.add( optionVO );
            optionVO = new OptionVO( "4", "4" );
            clazzList.add( optionVO );

        }
        return clazzList;
    }
}
