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

public class HcpLookup
{
    private static List<OptionVO> hcpIdrList;
    private static List<OptionVO> hcpUsdList;

    public static List<OptionVO> getHcpIdrList()
    {
        if( hcpIdrList == null )
        {
            hcpIdrList = new ArrayList<OptionVO>();

            OptionVO optionVO;
//            optionVO = new OptionVO( "", "None" );
//            hcpIdrList.add( optionVO );
            optionVO = new OptionVO( "1", "R-100" );
            hcpIdrList.add( optionVO );
            optionVO = new OptionVO( "2", "R-200" );
            hcpIdrList.add( optionVO );
            optionVO = new OptionVO( "3", "R-300" );
            hcpIdrList.add( optionVO );
            optionVO = new OptionVO( "4", "R-400" );
            hcpIdrList.add( optionVO );
            optionVO = new OptionVO( "5", "R-500" );
            hcpIdrList.add( optionVO );
            optionVO = new OptionVO( "6", "R-600" );
            hcpIdrList.add( optionVO );
            optionVO = new OptionVO( "7", "R-700" );
            hcpIdrList.add( optionVO );
            optionVO = new OptionVO( "8", "R-800" );
            hcpIdrList.add( optionVO );
            optionVO = new OptionVO( "9", "R-900" );
            hcpIdrList.add( optionVO );
            optionVO = new OptionVO( "10", "R-1000" );
            hcpIdrList.add( optionVO );
        }
        return hcpIdrList;
    }

    public static List<OptionVO> getHcpUsdList()
    {
        if( hcpUsdList == null )
        {
            hcpUsdList = new ArrayList<OptionVO>();

            OptionVO optionVO;
//            optionVO = new OptionVO( "", "None" );
//            hcpUsdList.add( optionVO );
            optionVO = new OptionVO( "11", "D-10" );
            hcpUsdList.add( optionVO );
            optionVO = new OptionVO( "12", "D-20" );
            hcpUsdList.add( optionVO );
            optionVO = new OptionVO( "13", "D-30" );
            hcpUsdList.add( optionVO );
            optionVO = new OptionVO( "14", "D-40" );
            hcpUsdList.add( optionVO );
            optionVO = new OptionVO( "15", "D-50" );
            hcpUsdList.add( optionVO );
            optionVO = new OptionVO( "16", "D-60" );
            hcpUsdList.add( optionVO );
            optionVO = new OptionVO( "17", "D-70" );
            hcpUsdList.add( optionVO );
            optionVO = new OptionVO( "18", "D-80" );
            hcpUsdList.add( optionVO );
            optionVO = new OptionVO( "19", "D-90" );
            hcpUsdList.add( optionVO );
            optionVO = new OptionVO( "20", "D-100" );
            hcpUsdList.add( optionVO );
        }
        return hcpUsdList;
    }
}
