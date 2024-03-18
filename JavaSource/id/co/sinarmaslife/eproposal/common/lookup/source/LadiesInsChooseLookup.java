package id.co.sinarmaslife.eproposal.common.lookup.source;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: HcpLookup
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 24, 2007 4:34:42 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.standard.model.vo.OptionVO;

import java.util.List;
import java.util.ArrayList;

public class LadiesInsChooseLookup
{
    private static List<OptionVO> ladiesInsChooseList;

    
    public static List<OptionVO> getladiesInsChooseList()
    {
        if( ladiesInsChooseList == null )
        {
            ladiesInsChooseList = new ArrayList<OptionVO>();

            OptionVO optionVO;
            optionVO = new OptionVO( "50", "50 %" );
            ladiesInsChooseList.add( optionVO );
            optionVO = new OptionVO( "60", "60 %" );
            ladiesInsChooseList.add( optionVO );
            optionVO = new OptionVO( "70", "70 %" );
            ladiesInsChooseList.add( optionVO );
            optionVO = new OptionVO( "80", "80 %" );
            ladiesInsChooseList.add( optionVO );
            optionVO = new OptionVO( "90", "90 %" );
            ladiesInsChooseList.add( optionVO );
            optionVO = new OptionVO( "100", "100 %" );
            ladiesInsChooseList.add( optionVO );
        }
        return ladiesInsChooseList;
    }

}
