package id.co.sinarmaslife.eproposal.dao;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040103Dao
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 24, 2007 9:39:16 AM
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
import java.util.Map;

@SuppressWarnings( "unchecked" )
public class Cepr01040103Dao extends EproposalDao
{
    public BigDecimal selectProduct54Rate( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectProduct54Rate", params );
    }   
}
