package id.co.sinarmaslife.standard.util;

import id.co.sinarmaslife.standard.model.vo.LimitVO;

import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: ArrUtil
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Sep 17, 2007 2:23:57 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

public class ArrUtil
{
	protected final static Log logger = LogFactory.getLog( ArrUtil.class );
	
    public static List<String> toListFromArray( String[] params )
    {
        List<String> list = new ArrayList<String>();
        if( params != null )
        {
            for( String content : params )
            {
                list.add( content );
            }
        }
        return list;
    }

    public static List<Integer> toListFromArray( Integer[] params )
    {
        List<Integer> list = new ArrayList<Integer>();
        if( params != null )
        {
            for( Integer content : params )
            {
                list.add( content );
            }
        }
        return list;
    }

    public static List<String> toListFromArrayDisregardEmpty( String[] params )
    {
        List<String> list = new ArrayList<String>();
        if( params != null )
        {
            for( String content : params )
            {
                if( !StringUtil.isEmpty( content ) )
                {
                    list.add( content );
                }
            }
        }
        return list;
    }
    
    public static int max( int[] arr )
    {
        int max = Integer.MIN_VALUE;
        int length = arr.length;
        for( int i = 0; i < length; i++ )
        {
            if( max < arr[ i ] ) max = arr[ i ];
        }
        return max;
    }

    public static int min( int[] arr )
    {
        int min = Integer.MAX_VALUE;
        int length = arr.length;
        for( int i = 0; i < length; i++ )
        {
            if( min > arr[ i ] ) min = arr[ i ];
        }
        return min;
    }

    public static double max( double[] arr )
    {
        double max = Double.MIN_VALUE;
        int length = arr.length;
        for( int i = 0; i < length; i++ )
        {
            if( max < arr[ i ] ) max = arr[ i ];
        }
        return max;
    }

    public static double min( double[] arr )
    {
        double min = Double.MAX_VALUE;
        int length = arr.length;
        for( int i = 0; i < length; i++ )
        {
            if( min > arr[ i ] ) min = arr[ i ];
        }
        return min;
    }

    public static int upperBound( int[] arr )
    {
        return arr.length - 1;
    }

    public static int upperBound( double[] arr )
    {
        return arr.length - 1;
    }

    public static Integer[] genPartitionsIntArray( LimitVO[] limitVOArr )
    {
    	int length = 0;
    	for( LimitVO vo : limitVOArr )
    	{
    		length = length + ( vo.getUp() - vo.getDown() + 1 );
    	}
    	Integer[] result = new Integer[length];
    	int idx = 0;
    	for( LimitVO limitVO : limitVOArr )
    	{
    		for( int i = limitVO.getDown(); i <= limitVO.getUp(); i++ )
    		{
    			result[idx] = i;
    			idx++;
    		}
    	}
    	return result;
    }
    
    public static List<Integer> genPartitionsIntList( LimitVO[] limitVOArr )
    {
    	return toListFromArray( genPartitionsIntArray( limitVOArr ) );
    }
    
    

    public static void main( String[] args )
    {
        double[] testArr = { 4, 3, 5, 2, 7 };
       
		//logger.info( "*-*-*-* max( testArr ) = " + max( testArr ) );
        //logger.info( "*-*-*-* min( testArr ) = " + min( testArr ) );
        logger.debug( "*-*-*-* max( testArr ) = " + max( testArr ) );
        logger.debug( "*-*-*-* min( testArr ) = " + min( testArr )  );

        List<Integer> yearNoList;
        int age = 22;
        LimitVO[] limitVOArr = new LimitVO[]{ new LimitVO( 1 + age, 20 + age ), new LimitVO( 150, 153  )};
        yearNoList = ArrUtil.toListFromArray( ArrUtil.genPartitionsIntArray( limitVOArr ) );
       // logger.info( "*-*-*-* yearNoList = " + yearNoList );
        logger.debug( "*-*-*-* yearNoList = " + yearNoList );

    }
}
