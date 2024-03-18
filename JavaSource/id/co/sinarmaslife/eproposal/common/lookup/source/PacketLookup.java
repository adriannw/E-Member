package id.co.sinarmaslife.eproposal.common.lookup.source;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: PacketLookup
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jan 7, 2009 2:04:45 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;

import java.util.List;
import java.util.ArrayList;

public class PacketLookup
{
     public static List<OptionVO> getPacketList( boolean addEmptyOption )
    {
        List<OptionVO> list = new ArrayList<OptionVO>();

        OptionVO optionVO;
        if( addEmptyOption )
        {
            optionVO = new OptionVO( "", "" );
            list.add( optionVO );
        }

        optionVO = new OptionVO( Integer.toString( Hardcode.PACKET_HOSPITAL_CASH_PLAN ), "Hospital Cash Plan" );
        list.add( optionVO );
        optionVO = new OptionVO( Integer.toString( Hardcode.PACKET_DANA_KEHIDUPAN ), "Dana Kehidupan" );
        list.add( optionVO );

        return list;
    }
}
