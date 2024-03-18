package id.co.sinarmaslife.eproposal.common.lookup.source;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: TopupDrawVOLookup
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 28, 2007 11:02:28 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;

import java.util.List;
import java.util.ArrayList;

public class TopupDrawVOLookup
{
    public static List<TopupDrawVO> getTopupDrawVOList()
    {
    		List<TopupDrawVO> topupDrawVOList;
            topupDrawVOList = new ArrayList<TopupDrawVO>();
            TopupDrawVO topupDrawVO;

            for( int i = 1; i <= 50; i++ )
            {
                topupDrawVO = new TopupDrawVO( i );
                topupDrawVOList.add( topupDrawVO );
            }
        return topupDrawVOList;
    }
}
