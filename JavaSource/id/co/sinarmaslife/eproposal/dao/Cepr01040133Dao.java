package id.co.sinarmaslife.eproposal.dao;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040112Dao
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Sep 3, 2008 9:21:44 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import id.co.sinarmaslife.eproposal.common.parent.EproposalDao;

@SuppressWarnings( "unchecked" )
public class Cepr01040133Dao extends EproposalDao
{
	public BigDecimal selectLdecRateToGetTunai_180( Map<String, Object> params ) throws DataAccessException
    {
		String x = "aa";
        return ( BigDecimal ) querySingle( "selectLdecRateToGetTunai_180", params );
    }
}
