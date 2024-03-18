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

public class MedicalPlusLookup
{
    private static List<OptionVO> medicalPlusChooseList;

    public static List<OptionVO> getMedicalPlusChooseList()
    {
        if( medicalPlusChooseList == null )
        {
        	medicalPlusChooseList = new ArrayList<OptionVO>();

                 OptionVO optionVO;
                 optionVO = new OptionVO( "2", "SILVER" );
                 medicalPlusChooseList.add( optionVO );
                 optionVO = new OptionVO( "3", "GOLD" );
                 medicalPlusChooseList.add( optionVO );
                 optionVO = new OptionVO( "4", "PLATINUM" );
                 medicalPlusChooseList.add( optionVO );
            }
             return medicalPlusChooseList;
    }

  
}
