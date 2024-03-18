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

public class EkaSehatInnerLimitLookup
{
    private static List<OptionVO> ekaSehatInnerLimitIdrList;
    private static List<OptionVO> ekaSehatInnerLimitUsdList;
//    private static List<OptionVO> ekaSehatProviderList;

//    public static List<OptionVO> getEkaSehatProviderList()
//    {
//        if( ekaSehatProviderList == null )
//        {
//        	ekaSehatProviderList = new ArrayList<OptionVO>();
//
//            OptionVO optionVO;
//            optionVO = new OptionVO( "1", "Non Provider" );
//            ekaSehatProviderList.add( optionVO );
//            optionVO = new OptionVO( "2", "Provider" );
//            ekaSehatProviderList.add( optionVO );
//        }
//        return ekaSehatProviderList;
//    }

    
    public static List<OptionVO> getEkaSehatInnerLimitIdrList()
    {
        if( ekaSehatInnerLimitIdrList == null )
        {
            ekaSehatInnerLimitIdrList = new ArrayList<OptionVO>();

            OptionVO optionVO;
//            optionVO = new OptionVO( "", "None" );
//            ekaSehatInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "1", "PLAN A-100" );
            ekaSehatInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "2", "PLAN B-150" );
            ekaSehatInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "3", "PLAN C-200" );
            ekaSehatInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "4", "PLAN D-250" );
            ekaSehatInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "5", "PLAN E-300" );
            ekaSehatInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "6", "PLAN F-350" );
            ekaSehatInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "7", "PLAN G-400" );
            ekaSehatInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "8", "PLAN H-500" );
            ekaSehatInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "9", "PLAN I-600" );
            ekaSehatInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "10", "PLAN J-700" );
            ekaSehatInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "11", "PLAN K-800" );
            ekaSehatInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "12", "PLAN L-900" );
            ekaSehatInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "13", "PLAN M-1000" );
            ekaSehatInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "14", "PLAN N-1500" );
            ekaSehatInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "15", "PLAN O-2000" );
            ekaSehatInnerLimitIdrList.add( optionVO );
        }
        return ekaSehatInnerLimitIdrList;
    }

    public static List<OptionVO> getekaSehatInnerLimitUsdList()
    {
        if( ekaSehatInnerLimitUsdList == null )
        {
            ekaSehatInnerLimitUsdList = new ArrayList<OptionVO>();

            OptionVO optionVO;
//            optionVO = new OptionVO( "", "None" );
//            ekaSehatInnerLimitUsdList.add( optionVO );
            optionVO = new OptionVO( "1", "PLAN A" );
            ekaSehatInnerLimitUsdList.add( optionVO );
            optionVO = new OptionVO( "2", "PLAN B" );
            ekaSehatInnerLimitUsdList.add( optionVO );
            optionVO = new OptionVO( "3", "PLAN C" );
            ekaSehatInnerLimitUsdList.add( optionVO );
            optionVO = new OptionVO( "4", "PLAN D" );
            ekaSehatInnerLimitUsdList.add( optionVO );
            optionVO = new OptionVO( "5", "PLAN E" );
            ekaSehatInnerLimitUsdList.add( optionVO );
            optionVO = new OptionVO( "6", "PLAN F" );
            ekaSehatInnerLimitUsdList.add( optionVO );
            optionVO = new OptionVO( "7", "PLAN G" );
            ekaSehatInnerLimitUsdList.add( optionVO );
            optionVO = new OptionVO( "8", "PLAN H" );
            ekaSehatInnerLimitUsdList.add( optionVO );
            optionVO = new OptionVO( "9", "PLAN I" );
            ekaSehatInnerLimitUsdList.add( optionVO );
            optionVO = new OptionVO( "10", "PLAN J" );
            ekaSehatInnerLimitUsdList.add( optionVO );
            optionVO = new OptionVO( "11", "PLAN K" );
            ekaSehatInnerLimitUsdList.add( optionVO );
            optionVO = new OptionVO( "12", "PLAN L" );
            ekaSehatInnerLimitUsdList.add( optionVO );
            optionVO = new OptionVO( "13", "PLAN M" );
            ekaSehatInnerLimitUsdList.add( optionVO );
            optionVO = new OptionVO( "14", "PLAN N" );
            ekaSehatInnerLimitUsdList.add( optionVO );
            optionVO = new OptionVO( "15", "PLAN O" );
            ekaSehatInnerLimitUsdList.add( optionVO );
        }
        return ekaSehatInnerLimitUsdList;
    }
}
