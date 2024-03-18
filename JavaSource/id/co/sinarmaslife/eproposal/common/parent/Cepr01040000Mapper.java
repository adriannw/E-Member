package id.co.sinarmaslife.eproposal.common.parent;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040000Mapper
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 12, 2008 9:45:29 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000EntryInterface;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000MapperInterface;

import java.util.Map;
import java.util.TreeMap;

public class Cepr01040000Mapper implements Cepr01040000MapperInterface
{
    private Map<Integer, Cepr01040000EntryInterface> subProductMap;
    private Map<Integer, Integer> bisnisNoPbVersionMap;

    public Cepr01040000Mapper()
    {
        this.subProductMap = new TreeMap<Integer, Cepr01040000EntryInterface>();
        this.bisnisNoPbVersionMap = new TreeMap<Integer, Integer>();
    }

    public Map<Integer, Cepr01040000EntryInterface> getSubProductMap()
    {
        return subProductMap;
    }

    public Map<Integer, Integer> getBisnisNoPbVersionMap()
    {
        return bisnisNoPbVersionMap;
    }

    public void setBisnisNoPbVersionMap( Map<Integer, Integer> bisnisNoPbVersionMap )
    {
        this.bisnisNoPbVersionMap = bisnisNoPbVersionMap;
    }

    /**
     * This method used to get Array of mapping of the the Sub Products want to be displayed
     * @param leftPartOfBusinessCd: left part of Business ( lsbs_id )
     * @return String[]
     */
    public String[] getShowedBusinessCdArr( int leftPartOfBusinessCd )
    {
        String[] result;
        if( subProductMap != null )
        {
            Object[] objArr = subProductMap.keySet().toArray();
            result = new String[ objArr.length ];

            for( int i = 0; i < objArr.length; i++ )
            {
                result[ i ] =  leftPartOfBusinessCd + "~X" + objArr[ i ];
            }
        }
        else
        {
            result = null;
        }
        return result;
    }

    public void standardMapping( int javaBisnisNo, int pbBisnisNo, Cepr01040000EntryInterface entryInterface )
    {
        subProductMap.put( javaBisnisNo, entryInterface );
        bisnisNoPbVersionMap.put( javaBisnisNo, pbBisnisNo );
    }
}
