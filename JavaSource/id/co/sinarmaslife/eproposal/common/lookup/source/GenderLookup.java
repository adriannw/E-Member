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

public class GenderLookup
{
    private static List<OptionVO> genderList;

    public static List<OptionVO> getGenderList()
    {
        if( genderList == null )
        {
            genderList = new ArrayList<OptionVO>();

            OptionVO optionVO;
            optionVO = new OptionVO( "L", "Laki-laki" );
            genderList.add( optionVO );
            optionVO = new OptionVO( "P", "Perempuan" );
            genderList.add( optionVO );
        }
        return genderList;
    }
}
