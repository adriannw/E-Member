package id.co.sinarmaslife.eproposal.common.lookup.source;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: LadiesMedExpenseLookup
 * Description         	: Lookup Idr and Usd List
 * Environment      	: Java  1.5.0_06
 * Author               : Alfian
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

public class LadiesMedExpenseLookup
{
    private static List<OptionVO> ladiesMedExpenseIdrList;
    private static List<OptionVO> ladiesMedExpenseUsdList;
    
    public static List<OptionVO> getladiesMedExpenseIdrList()
    {
        if( ladiesMedExpenseIdrList == null )
        {
        	ladiesMedExpenseIdrList = new ArrayList<OptionVO>();

            OptionVO optionVO;
//            optionVO = new OptionVO( "", "None" );
//            ladiesMedExpenseIdrList.add( optionVO );
            optionVO = new OptionVO( "1", "PAKET A(Rp)" );
            ladiesMedExpenseIdrList.add( optionVO );
            optionVO = new OptionVO( "2", "PAKET B(Rp)" );
            ladiesMedExpenseIdrList.add( optionVO );
            optionVO = new OptionVO( "3", "PAKET C(Rp)" );
            ladiesMedExpenseIdrList.add( optionVO );
            optionVO = new OptionVO( "4", "PAKET D(Rp)" );
            ladiesMedExpenseIdrList.add( optionVO );
            optionVO = new OptionVO( "5", "PAKET E(Rp)" );
            ladiesMedExpenseIdrList.add( optionVO );
            optionVO = new OptionVO( "6", "PAKET F(Rp)" );
            ladiesMedExpenseIdrList.add( optionVO );
            optionVO = new OptionVO( "7", "PAKET G(Rp)" );
            ladiesMedExpenseIdrList.add( optionVO );
        }
        return ladiesMedExpenseIdrList;
    }

    public static List<OptionVO> getladiesMedExpenseUsdList()
    {
        if( ladiesMedExpenseUsdList == null )
        {
        	ladiesMedExpenseUsdList = new ArrayList<OptionVO>();

        	 OptionVO optionVO;
//             optionVO = new OptionVO( "", "None" );
//             ladiesMedExpenseUsdList.add( optionVO );
             optionVO = new OptionVO( "1", "PAKET A($)" );
             ladiesMedExpenseUsdList.add( optionVO );
             optionVO = new OptionVO( "2", "PAKET B($)" );
             ladiesMedExpenseUsdList.add( optionVO );
             optionVO = new OptionVO( "3", "PAKET C($)" );
             ladiesMedExpenseUsdList.add( optionVO );
             optionVO = new OptionVO( "4", "PAKET D($)" );
             ladiesMedExpenseUsdList.add( optionVO );
             optionVO = new OptionVO( "5", "PAKET E($)" );
             ladiesMedExpenseUsdList.add( optionVO );
             optionVO = new OptionVO( "6", "PAKET F($)" );
             ladiesMedExpenseUsdList.add( optionVO );
             optionVO = new OptionVO( "7", "PAKET G($)" );
             ladiesMedExpenseUsdList.add( optionVO );
        }
        return ladiesMedExpenseUsdList;
    }
}
