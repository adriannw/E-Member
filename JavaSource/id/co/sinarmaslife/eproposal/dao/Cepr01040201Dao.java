package id.co.sinarmaslife.eproposal.dao;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040201Dao
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 22, 2007 9:39:22 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.parent.EproposalDao;
import id.co.sinarmaslife.eproposal.model.vo.Cepr00000000PerformanceVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings( "unchecked" )
public class Cepr01040201Dao extends EproposalDao
{
    public Cepr01040201Dao() throws Exception {
        initDao();
    }
    
    public BigDecimal selectLdecRateToGetCoi( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateToGetCoi", params );
    }
    
    public BigDecimal selectLdecRateToGetCoi_119( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateToGetCoi_119", params );
    }

    public BigDecimal selectLdecRateToGetCoiBasic( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateToGetCoiBasic", params );
    }
    
    public BigDecimal selectLdecRateToGetCoi_190( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateToGetCoi_190", params );
    }
    
    public BigDecimal selectLdecRateToGetTunai( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateToGetTunai", params );
    }

    public BigDecimal selectLdecRatePa( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRatePa", params );
    }
  
    public BigDecimal selectLdecRateHcp( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateHcp", params );
    }

    public BigDecimal selectLdecRateTpd( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateTpd", params );
    }
    
    public BigDecimal selectLdecRateTpd_120( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateTpd_120", params );
    }

    public BigDecimal selectLdecRateCi( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateCi", params );
    }
    
    public BigDecimal selectLdecRateCiRider( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateCiRider", params );
    }

    public BigDecimal selectLdecRateWp60Tpd( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateWp60Tpd", params );
    }
    
    public BigDecimal selectLdecRateWp60Tpd_120( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateWp60Tpd_120", params );
    }
    
    public BigDecimal selectLdecRateWp10Tpd( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateWp10Tpd", params );
    }

    public BigDecimal selectLdecRatePb25Tpd( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRatePb25Tpd", params );
    }

    public BigDecimal selectLdecRateWp60Ci( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateWp60Ci", params );
    }
    
    public BigDecimal selectLdecRateWp60Ci_120( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateWp60Ci_120", params );
    }
    
    public BigDecimal selectLdecRateWp10Ci( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateWp10Ci", params );
    }

    public BigDecimal selectLdecRatePb25CiDeath( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRatePb25CiDeath", params );
    }
    
    public BigDecimal selectLdecRatePayorTpdCiDeath( Map<String, Object> params) throws DataAccessException
    {
    	return ( BigDecimal ) querySingle("selectLdecRatePayorTpdCiDeath", params );
    }

    public BigDecimal selectLdecRateWaiverTpdCi( Map<String, Object> params) throws DataAccessException
    {
    	return ( BigDecimal ) querySingle("selectLdecRateWaiverTpdCi", params );
    }
    
    public BigDecimal selectLdecRateScholarship( Map<String, Object> params) throws DataAccessException
    {
    	return ( BigDecimal ) querySingle("selectLdecRateScholarship", params );
    }
    
    public BigDecimal selectLdecRateBaby( Map<String, Object> params) throws DataAccessException
    {
    	return ( BigDecimal ) querySingle("selectLdecRateBaby", params );
    }
    
    public BigDecimal selectLdecRateEarlyStageCi99( Map<String, Object> params) throws DataAccessException
    {
    	return ( BigDecimal ) querySingle("selectLdecRateEarlyStageCi99", params );
    }
    
    public BigDecimal selectLdecRateLadiesIns( Map<String, Object> params) throws DataAccessException
    {
    	return ( BigDecimal ) querySingle("selectLdecRateLadiesIns", params );
    }
    
    
    public BigDecimal selectLdecRatePb25Ci( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRatePb25Ci", params );
    }

    public BigDecimal selectLdecRatePayorSpouse( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRatePayorSpouse", params );
    }

    public BigDecimal selectLdecRateHcpFamily( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateHcpFamily", params );
    }

    public BigDecimal selectLdecRateHcpFamilyPesertaTambahan( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateHcpFamilyPesertaTambahan", params );
    }
    
    public BigDecimal selectLdecRateHcpLadies( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateHcpLadies", params );
    }

    public BigDecimal selectLdecRateHcpLadiesPesertaTambahan( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateHcpLadiesPesertaTambahan", params );
    }

    public BigDecimal selectLdecRateHcpProvider( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateHcpProvider", params );
    }

    public BigDecimal selectLdecRateHcpProviderPesertaTambahan( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateHcpProviderPesertaTambahan", params );
    }
    
    public List<Cepr00000000PerformanceVO> selectInvestmentPerformanceLastFourYears( Map<String, Object> params ) throws DataAccessException
    {
        return query( "selectInvestmentPerformanceLastFourYears", params );
    }

    public BigDecimal selectLdecRateTermRider( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateTermRider", params );
    }
    
    public BigDecimal selectLdecRateTerm( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateTerm", params );
    }
    
    public BigDecimal selectLdecRateTermRider_120( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectLdecRateTermRider_120", params );
    }
    
    public BigDecimal selectEkaSehatRider( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectEkaSehatRider", params );
    }

	public BigDecimal selectEkaSehatInnerLimitRider(Map<String, Object> params) throws DataAccessException
	{
		return ( BigDecimal ) querySingle( "selectEkaSehatInnerLimitRider", params );
	}
	
	public BigDecimal selectLadiesMedExpenseRider(Map<String, Object> params) throws DataAccessException
	{
		return ( BigDecimal ) querySingle( "selectLadiesMedExpenseRider", params );
	}
	
	public BigDecimal selectLadiesMedExpenseInnerLimitRider(Map<String, Object> params) throws DataAccessException
	{
		return ( BigDecimal ) querySingle( "selectLadiesMedExpenseInnerLimitRider", params );
	}

	public BigDecimal selectRateExtMortalita(Map<String, Object> params) throws DataAccessException
	{
		return ( BigDecimal ) querySingle( "selectRateExtMortalita", params );
	}
	
	public List<Map> selectRiderMedicalPlusList( Map<String, Object> params ) throws DataAccessException
	{
	        return ( List<Map> ) query( "selectRiderMedicalPlusList", params );
	}
	
	public List<Map> selectRiderMedicalPlusRjList( Map<String, Object> params ) throws DataAccessException
	{
	        return ( List<Map> ) query( "selectRiderMedicalPlusRjList", params );
	}
	
	public BigDecimal selectMedicalPlusRider(Map<String, Object> params) throws DataAccessException
	{
		return ( BigDecimal ) querySingle( "selectMedicalPlusRider", params );
	}
	
	public BigDecimal selectMedicalPlusRjRider(Map<String, Object> params) throws DataAccessException
	{
		return ( BigDecimal ) querySingle( "selectMedicalPlusRjRider", params );
	}
	
	public BigDecimal selectProvestraRiRj(Map<String, Object> params) throws DataAccessException
	{
		return ( BigDecimal ) querySingle( "selectProvestraRiRj", params );
	}
	
	public BigDecimal selectProvestraRgRbPk(Map<String, Object> params) throws DataAccessException
	{
		return ( BigDecimal ) querySingle( "selectProvestraRgRbPk", params );
	}
	
	public BigDecimal selectPremiKid(Map<String, Object> params) throws DataAccessException
	{
		return ( BigDecimal ) querySingle( "selectPremiKid", params );
	}
	
	public BigDecimal selectNTKid(Map<String, Object> params) throws DataAccessException
	{
		return ( BigDecimal ) querySingle( "selectNTKid", params );
	}
	
	public BigDecimal selectLdecRateToGetCoi_220( Map<String, Object> params ) throws DataAccessException
	{
	     return ( BigDecimal ) querySingle( "selectLdecRateToGetCoi_220", params );
	}
	
	public BigDecimal selectSMiLeMedicalExtraRider( Map<String, Object> params ) throws DataAccessException
	{
	     return ( BigDecimal ) querySingle( "selectSMiLeMedicalExtraRider", params );
	}
	
}