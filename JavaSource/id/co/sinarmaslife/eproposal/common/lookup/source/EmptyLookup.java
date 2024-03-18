package id.co.sinarmaslife.eproposal.common.lookup.source;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: EmptyLookup
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Dec 24, 2007 4:07:19 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.standard.model.vo.OptionVO;

import java.util.List;
import java.util.ArrayList;

public class EmptyLookup
{

    private static List<OptionVO> emptyList;

    public static List<OptionVO> getEmptyList()
    {
        if( emptyList == null )
        {
            emptyList = new ArrayList<OptionVO>();
            OptionVO optionVO;
            optionVO = new OptionVO( "", "" );
            emptyList.add( optionVO );
        }
        return emptyList;
    }

}
