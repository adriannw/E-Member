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

public class EkaSehatLookup
{
    private static List<OptionVO> ekaSehatIdrList;
    private static List<OptionVO> ekaSehatUsdList;
    private static List<OptionVO> ekaSehatExtraList;
    
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

    
    public static List<OptionVO> getEkaSehatIdrList()
    {
        if( ekaSehatIdrList == null )
        {
            ekaSehatIdrList = new ArrayList<OptionVO>();

            OptionVO optionVO;
//            optionVO = new OptionVO( "", "None" );
//            ekaSehatIdrList.add( optionVO );
            optionVO = new OptionVO( "1", "PLAN A-100" );
            ekaSehatIdrList.add( optionVO );
            optionVO = new OptionVO( "2", "PLAN B-150" );
            ekaSehatIdrList.add( optionVO );
            optionVO = new OptionVO( "3", "PLAN C-200" );
            ekaSehatIdrList.add( optionVO );
            optionVO = new OptionVO( "4", "PLAN D-250" );
            ekaSehatIdrList.add( optionVO );
            optionVO = new OptionVO( "5", "PLAN E-300" );
            ekaSehatIdrList.add( optionVO );
            optionVO = new OptionVO( "6", "PLAN F-350" );
            ekaSehatIdrList.add( optionVO );
            optionVO = new OptionVO( "7", "PLAN G-400" );
            ekaSehatIdrList.add( optionVO );
            optionVO = new OptionVO( "8", "PLAN H-500" );
            ekaSehatIdrList.add( optionVO );
            optionVO = new OptionVO( "9", "PLAN I-600" );
            ekaSehatIdrList.add( optionVO );
            optionVO = new OptionVO( "10", "PLAN J-700" );
            ekaSehatIdrList.add( optionVO );
            optionVO = new OptionVO( "11", "PLAN K-800" );
            ekaSehatIdrList.add( optionVO );
            optionVO = new OptionVO( "12", "PLAN L-900" );
            ekaSehatIdrList.add( optionVO );
            optionVO = new OptionVO( "13", "PLAN M-1000" );
            ekaSehatIdrList.add( optionVO );
            optionVO = new OptionVO( "14", "PLAN N-1500" );
            ekaSehatIdrList.add( optionVO );
            optionVO = new OptionVO( "15", "PLAN O-2000" );
            ekaSehatIdrList.add( optionVO );
            optionVO = new OptionVO( "16", "PLAN P-5000" );
            ekaSehatIdrList.add( optionVO );
            optionVO = new OptionVO( "17", "PLAN Q-7500" );
            ekaSehatIdrList.add( optionVO );
        }
        return ekaSehatIdrList;
    }

    public static List<OptionVO> getekaSehatUsdList()
    {
        if( ekaSehatUsdList == null )
        {
            ekaSehatUsdList = new ArrayList<OptionVO>();

            OptionVO optionVO;
//            optionVO = new OptionVO( "", "None" );
//            ekaSehatUsdList.add( optionVO );
            optionVO = new OptionVO( "1", "PLAN A" );
            ekaSehatUsdList.add( optionVO );
            optionVO = new OptionVO( "2", "PLAN B" );
            ekaSehatUsdList.add( optionVO );
            optionVO = new OptionVO( "3", "PLAN C" );
            ekaSehatUsdList.add( optionVO );
            optionVO = new OptionVO( "4", "PLAN D" );
            ekaSehatUsdList.add( optionVO );
            optionVO = new OptionVO( "5", "PLAN E" );
            ekaSehatUsdList.add( optionVO );
            optionVO = new OptionVO( "6", "PLAN F" );
            ekaSehatUsdList.add( optionVO );
            optionVO = new OptionVO( "7", "PLAN G" );
            ekaSehatUsdList.add( optionVO );
            optionVO = new OptionVO( "8", "PLAN H" );
            ekaSehatUsdList.add( optionVO );
            optionVO = new OptionVO( "9", "PLAN I" );
            ekaSehatUsdList.add( optionVO );
            optionVO = new OptionVO( "10", "PLAN J" );
            ekaSehatUsdList.add( optionVO );
            optionVO = new OptionVO( "11", "PLAN K" );
            ekaSehatUsdList.add( optionVO );
            optionVO = new OptionVO( "12", "PLAN L" );
            ekaSehatUsdList.add( optionVO );
            optionVO = new OptionVO( "13", "PLAN M" );
            ekaSehatUsdList.add( optionVO );
            optionVO = new OptionVO( "14", "PLAN N" );
            ekaSehatUsdList.add( optionVO );
            optionVO = new OptionVO( "15", "PLAN O" );
            ekaSehatUsdList.add( optionVO );
        }
        return ekaSehatUsdList;
    }
    
    public static List<OptionVO> getEkaSehatExtraList()
    {
        if( ekaSehatExtraList == null )
        {
        	ekaSehatExtraList = new ArrayList<OptionVO>();

            OptionVO optionVO;
//            optionVO = new OptionVO( "", "None" );
//            ekaSehatExtraList.add( optionVO );
            optionVO = new OptionVO( "1", "PLAN A-200" );
            ekaSehatExtraList.add( optionVO );
            optionVO = new OptionVO( "2", "PLAN B-300" );
            ekaSehatExtraList.add( optionVO );
            optionVO = new OptionVO( "3", "PLAN C-400" );
            ekaSehatExtraList.add( optionVO );
            optionVO = new OptionVO( "4", "PLAN D-500" );
            ekaSehatExtraList.add( optionVO );
            optionVO = new OptionVO( "5", "PLAN E-800" );
            ekaSehatExtraList.add( optionVO );
            optionVO = new OptionVO( "6", "PLAN F-1000" );
            ekaSehatExtraList.add( optionVO );
            optionVO = new OptionVO( "7", "PLAN G-1500" );
            ekaSehatExtraList.add( optionVO );
            optionVO = new OptionVO( "8", "PLAN H-2000" );
            ekaSehatExtraList.add( optionVO );
            optionVO = new OptionVO( "9", "PLAN I-5000" );
            ekaSehatExtraList.add( optionVO );
            optionVO = new OptionVO( "10", "PLAN J-7500" );
            ekaSehatExtraList.add( optionVO );
           
        }
        return ekaSehatExtraList;
    }
    
}
