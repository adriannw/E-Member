package id.co.sinarmaslife.eproposal.common.lookup.source;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: MonthsLookup
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Apr 21, 2008 3:23:26 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.standard.model.vo.OptionVO;

import java.util.List;
import java.util.ArrayList;

public class MonthsLookup
{
    public static List<OptionVO> getMonthList( boolean addEmptyOption, Integer[] monthPeriodArr )
    {
        List<OptionVO> list = new ArrayList<OptionVO>();

        OptionVO optionVO;
        if( addEmptyOption )
        {
            optionVO = new OptionVO( "", "" );
            list.add( optionVO );
        }

        for( Integer monthPeriod : monthPeriodArr )
        {
            optionVO = new OptionVO( monthPeriod.toString(), monthPeriod + " bulan" );
            list.add( optionVO );
        }

        return list;
    }

}
