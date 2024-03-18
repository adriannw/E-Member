package id.co.sinarmaslife.eproposal.common.lookup.source;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: PaRiskLookup
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 24, 2007 3:33:22 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.standard.model.vo.OptionVO;

import java.util.ArrayList;
import java.util.List;

public class PaRiskLookup
{
    private static List<OptionVO> paRiskList;

    public static List<OptionVO> getPaRiskList()
    {
        if( paRiskList == null )
        {
            paRiskList = new ArrayList<OptionVO>();

            OptionVO optionVO;
            optionVO = new OptionVO( "", "None" );
            paRiskList.add( optionVO );
            optionVO = new OptionVO( "1", "Resiko A" );
            paRiskList.add( optionVO );
            optionVO = new OptionVO( "2", "Resiko AB" );
            paRiskList.add( optionVO );
            optionVO = new OptionVO( "3", "Resiko ABD" );
            paRiskList.add( optionVO );
        }
        return paRiskList;
    }
}
