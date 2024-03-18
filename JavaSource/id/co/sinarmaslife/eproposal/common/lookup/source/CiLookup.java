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

public class CiLookup
{
    private static List<OptionVO> ciChooseList;
    private static List<OptionVO> ciChooseExtList;
    
    public static List<OptionVO> getCiChooseList()
    {
        if( ciChooseList == null )
        {
            ciChooseList = new ArrayList<OptionVO>();

            OptionVO optionVO;
            optionVO = new OptionVO( "50", "50 %" );
            ciChooseList.add( optionVO );
            optionVO = new OptionVO( "60", "60 %" );
            ciChooseList.add( optionVO );
            optionVO = new OptionVO( "70", "70 %" );
            ciChooseList.add( optionVO );
            optionVO = new OptionVO( "80", "80 %" );
            ciChooseList.add( optionVO );
            optionVO = new OptionVO( "90", "90 %" );
            ciChooseList.add( optionVO );
            optionVO = new OptionVO( "100", "100 %" );
            ciChooseList.add( optionVO );
        }
        return ciChooseList;
    }
    
    public static List<OptionVO> getCiChooseExtList()
    {
        if( ciChooseExtList == null )
        {
        	ciChooseExtList = new ArrayList<OptionVO>();

            OptionVO optionVO;
            optionVO = new OptionVO( "50", "50 %" );
            ciChooseExtList.add( optionVO );
            optionVO = new OptionVO( "60", "60 %" );
            ciChooseExtList.add( optionVO );
            optionVO = new OptionVO( "70", "70 %" );
            ciChooseExtList.add( optionVO );
            optionVO = new OptionVO( "80", "80 %" );
            ciChooseExtList.add( optionVO );
            optionVO = new OptionVO( "90", "90 %" );
            ciChooseExtList.add( optionVO );
            optionVO = new OptionVO( "100", "100 %" );
            ciChooseExtList.add( optionVO );
            optionVO = new OptionVO( "150", "150 %" );
            ciChooseExtList.add( optionVO );
            optionVO = new OptionVO( "200", "200 %" );
            ciChooseExtList.add( optionVO );
            optionVO = new OptionVO( "250", "250 %" );
            ciChooseExtList.add( optionVO );
            optionVO = new OptionVO( "300", "300 %" );
            ciChooseExtList.add( optionVO );
        }
        return ciChooseExtList;
    }

}
