package id.co.sinarmaslife.standard.util;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: ListUtil
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 7, 2008 4:03:14 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.ArrayList;

public class ListUtil
{
    public static List<String> addNothingIfEmpty( String content )
    {
        List<String> result = new ArrayList<String>();
        if( !StringUtil.isEmpty( content ) )
        {
            result.add( content );
        }
        return result;
    }
    
    public static ArrayList serializableList(List dataList){
		if(dataList!=null){
			return new ArrayList(dataList);
		}else{
			return null;
		}
	}
	
}
