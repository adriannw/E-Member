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

public class Term5575RiderChooseLookup
{
    private static List<OptionVO> term5575RiderList;
     

    
    public static List<OptionVO> getTerm5575RiderChooseList()
    {
      
            if( term5575RiderList == null )
            {
            	term5575RiderList = new ArrayList<OptionVO>();

                OptionVO optionVO;
                optionVO = new OptionVO( "7", "55" );
                term5575RiderList.add( optionVO );
                optionVO = new OptionVO( "8", "65" );
                term5575RiderList.add( optionVO );
                optionVO = new OptionVO( "9", "75" );
                term5575RiderList.add( optionVO );
            }
            return term5575RiderList;
      
    }

        
}
