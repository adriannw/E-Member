package id.co.sinarmaslife.eproposal.dao;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040111Dao
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Apr 18, 2008 2:19:26 PM
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
public class Cepr01040111Dao extends EproposalDao
{
    public BigDecimal selectRiderPcRate( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectRiderPcRate", params );
    }

    public BigDecimal selectRiderWpdRate( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectRiderWpdRate", params );
    }

    public List<String> selectJenisMedicalPrefix( Map<String, Object> params ) throws DataAccessException
    {
        return ( List<String> ) query( "selectJenisMedicalPrefix", params );
    }

    public Integer selectIdRangeSar( Map<String, Object> params ) throws DataAccessException
    {
        return ( Integer ) querySingle( "selectIdRangeSar", params );
    }

    public Integer selectIdRangeAge( Map<String, Object> params ) throws DataAccessException
    {
        return ( Integer ) querySingle( "selectIdRangeAge", params );
    }
      
}