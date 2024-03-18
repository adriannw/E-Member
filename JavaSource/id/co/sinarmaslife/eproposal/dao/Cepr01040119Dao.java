package id.co.sinarmaslife.eproposal.dao;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040119Dao
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Dec 30, 2008 9:55:55 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import id.co.sinarmaslife.eproposal.common.parent.EproposalDao;

import java.math.BigDecimal;
import java.util.Map;

@SuppressWarnings( "unchecked" )
public class Cepr01040119Dao extends EproposalDao
{
    public BigDecimal selectProduct168PremiRate( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectProduct168PremiRate", params );
    }
      
}
