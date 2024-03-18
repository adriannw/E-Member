package id.co.sinarmaslife.standard.util;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: MappingUtil
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 16, 2007 9:22:31 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import id.co.sinarmaslife.standard.model.vo.AssurancePlanList;
import id.co.sinarmaslife.standard.model.vo.OptionVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MappingUtil
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public MappingUtil()
    {
        logger.info( "*-*-*-* MappingUtil constructor is called ..." );
    }

    // example:
    // MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() ) ;
    public static String getLabel( List<OptionVO> optionVOList, Object value )
    {
        String result = "";
        if( optionVOList != null && value != null )
        {
            int size = optionVOList.size();
            OptionVO optionVO;
            boolean valueHasBeenFound = false;
            int idx = 0;
            while( !valueHasBeenFound && idx < size )
            {
                optionVO = optionVOList.get( idx );
                if( optionVO != null && optionVO.getValue().equals( value.toString() ) )
                {
                    valueHasBeenFound = true;
                    result = optionVO.getLabel();
                }
                idx++;
            }
        }
        return result;
    }
    
    
    public static String getLabelProduct( List<AssurancePlanList> optionVOList, Object value )
    {
        String result = "";
        if( optionVOList != null && value != null )
        {
            int size = optionVOList.size();
            AssurancePlanList optionVO;
            boolean valueHasBeenFound = false;
            int idx = 0;
            while( !valueHasBeenFound && idx < size )
            {
                optionVO = optionVOList.get( idx );
                if( optionVO != null && optionVO.getValue().equals( value.toString() ) )
                {
                    valueHasBeenFound = true;
                    result = optionVO.getLabel();
                }
                idx++;
            }
        }
        return result;
    }
    
    public static HashMap serializableMap(Map dataMap){
		if(dataMap!=null){
			return new HashMap(dataMap);
		}else{
			return null;
		}
	}
}
