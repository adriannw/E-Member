package id.co.sinarmaslife.eproposal.common.lookup.source;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: PaRiskLookup
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : Fadly Mathendra
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

public class SpecialOfferLookup
{
    private static List<OptionVO> specialOfferList;

    public static List<OptionVO> getspecialOfferList()
    {
        if( specialOfferList == null )
        {
        	specialOfferList = new ArrayList<OptionVO>();

            OptionVO optionVO;
            optionVO = new OptionVO( "", "None" );
            specialOfferList.add( optionVO );
//            optionVO = new OptionVO( "1", "Discount Premium" );
//            specialOfferList.add( optionVO );
            optionVO = new OptionVO( "2", "Additional Unit" );
            specialOfferList.add( optionVO );
//            optionVO = new OptionVO( "3", "Lucky Draw" );
//            specialOfferList.add( optionVO );
        }
        return specialOfferList;
    }
}
