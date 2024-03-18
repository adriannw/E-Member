package id.co.sinarmaslife.eproposal.model.interfaces;

import java.util.Map;

/**
 * *******************************************************************
 * Program History
 * <p/>
 * Project Name      	: E-Proposal
 * Function Id         	:
 * Program Name   		: Cepr01040000MapperInterface
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 12, 2008 8:21:25 AM
 * <p/>
 * Version      Re-fix date                 Person in charge    Description
 * <p/>
 * <p/>
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 * *********************************************************************
 */
public interface Cepr01040000MapperInterface
{
    public Map<Integer, Cepr01040000EntryInterface> getSubProductMap();
    public Map<Integer, Integer> getBisnisNoPbVersionMap();
    public String[] getShowedBusinessCdArr( int leftPartOfBusinessCd );
    public void standardMapping( int javaBisnisNo, int pbBisnisNo, Cepr01040000EntryInterface entryInterface );
}
