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

public class ScholarshipLookup
{
    private static List<OptionVO> scholarshipLookupList;

    
    public static List<OptionVO> getscholarshipLookupList()
    {
        if( scholarshipLookupList == null )
        {
            scholarshipLookupList = new ArrayList<OptionVO>();

            OptionVO optionVO;
            optionVO = new OptionVO( "5000000", "5,000,000.00" );
            scholarshipLookupList.add( optionVO );
            optionVO = new OptionVO( "10000000", "10,000,000.00" );
            scholarshipLookupList.add( optionVO );
            optionVO = new OptionVO( "20000000", "20,000,000.00" );
            scholarshipLookupList.add( optionVO );
            optionVO = new OptionVO( "30000000", "30,000,000.00" );
            scholarshipLookupList.add( optionVO );
            optionVO = new OptionVO( "50000000", "50,000,000.00" );
            scholarshipLookupList.add( optionVO );
        }
        return scholarshipLookupList;
    }

}
