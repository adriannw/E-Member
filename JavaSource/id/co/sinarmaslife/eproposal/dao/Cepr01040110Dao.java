package id.co.sinarmaslife.eproposal.dao;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040110Dao
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 13, 2008 1:34:44 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.parent.EproposalDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@SuppressWarnings( "unchecked" )
public class Cepr01040110Dao extends EproposalDao
{
    public BigDecimal selectRiderPcRate( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectRiderPcRate", params );
    }

    public BigDecimal selectRiderWpdRate( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectRiderWpdRate", params );
    }
      
}
