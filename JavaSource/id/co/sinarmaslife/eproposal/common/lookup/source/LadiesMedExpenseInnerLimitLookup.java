package id.co.sinarmaslife.eproposal.common.lookup.source;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: LadiesMedExpenseLookup
 * Description         	: Lookup Idr and Usd List
 * Environment      	: Java  1.5.0_06
 * Author               : Fadly M
 * Version              : 1.0
 * Creation Date    	: May 02, 2012 08:57 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.standard.model.vo.OptionVO;

import java.util.List;
import java.util.ArrayList;

public class LadiesMedExpenseInnerLimitLookup
{
    private static List<OptionVO> ladiesMedExpenseInnerLimitIdrList;
    private static List<OptionVO> ladiesMedExpenseInnerLimitUsdList;
    
    public static List<OptionVO> getladiesMedExpenseInnerLimitIdrList()
    {
        if( ladiesMedExpenseInnerLimitIdrList == null )
        {
        	ladiesMedExpenseInnerLimitIdrList = new ArrayList<OptionVO>();

            OptionVO optionVO;
//            optionVO = new OptionVO( "", "None" );
//            ladiesMedExpenseInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "1", "PAKET A(Rp)" );
            ladiesMedExpenseInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "2", "PAKET B(Rp)" );
            ladiesMedExpenseInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "3", "PAKET C(Rp)" );
            ladiesMedExpenseInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "4", "PAKET D(Rp)" );
            ladiesMedExpenseInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "5", "PAKET E(Rp)" );
            ladiesMedExpenseInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "6", "PAKET F(Rp)" );
            ladiesMedExpenseInnerLimitIdrList.add( optionVO );
            optionVO = new OptionVO( "7", "PAKET G(Rp)" );
            ladiesMedExpenseInnerLimitIdrList.add( optionVO );
        }
        return ladiesMedExpenseInnerLimitIdrList;
    }

    public static List<OptionVO> getladiesMedExpenseInnerLimitUsdList()
    {
        if( ladiesMedExpenseInnerLimitUsdList == null )
        {
        	ladiesMedExpenseInnerLimitUsdList = new ArrayList<OptionVO>();

        	 OptionVO optionVO;
//             optionVO = new OptionVO( "", "None" );
//             ladiesMedExpenseInnerLimitUsdList.add( optionVO );
             optionVO = new OptionVO( "1", "PAKET A($)" );
             ladiesMedExpenseInnerLimitUsdList.add( optionVO );
             optionVO = new OptionVO( "2", "PAKET B($)" );
             ladiesMedExpenseInnerLimitUsdList.add( optionVO );
             optionVO = new OptionVO( "3", "PAKET C($)" );
             ladiesMedExpenseInnerLimitUsdList.add( optionVO );
             optionVO = new OptionVO( "4", "PAKET D($)" );
             ladiesMedExpenseInnerLimitUsdList.add( optionVO );
             optionVO = new OptionVO( "5", "PAKET E($)" );
             ladiesMedExpenseInnerLimitUsdList.add( optionVO );
             optionVO = new OptionVO( "6", "PAKET F($)" );
             ladiesMedExpenseInnerLimitUsdList.add( optionVO );
             optionVO = new OptionVO( "7", "PAKET G($)" );
             ladiesMedExpenseInnerLimitUsdList.add( optionVO );
        }
        return ladiesMedExpenseInnerLimitUsdList;
    }
}
