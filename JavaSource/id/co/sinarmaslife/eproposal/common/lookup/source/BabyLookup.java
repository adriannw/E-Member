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

public class BabyLookup
{
    private static List<OptionVO> babyChooseList;
    private static List<OptionVO> babyList;
    
    public static List<OptionVO> getBabyList()
    {
    	  if( babyList == null )
          {
          	babyList = new ArrayList<OptionVO>();

              OptionVO optionVO;
              optionVO = new OptionVO( "1", "Plan 1" );
              babyList.add( optionVO );
              optionVO = new OptionVO( "2", "Plan 2" );
              babyList.add( optionVO );
              optionVO = new OptionVO( "3", "Plan 3" );
              babyList.add( optionVO );          
          }
          return babyList;
    }

    public static List<OptionVO> getBabyChooseList()
    {
        if( babyChooseList == null )
        {
        	babyChooseList = new ArrayList<OptionVO>();

            OptionVO optionVO;
            optionVO = new OptionVO( "5", "5" );
            babyChooseList.add( optionVO );
            optionVO = new OptionVO( "6", "6" );
            babyChooseList.add( optionVO );
            optionVO = new OptionVO( "7", "7" );
            babyChooseList.add( optionVO );    
            optionVO = new OptionVO( "8", "8" );
            babyChooseList.add( optionVO );    
        }
        return babyChooseList;
    }
    
}
