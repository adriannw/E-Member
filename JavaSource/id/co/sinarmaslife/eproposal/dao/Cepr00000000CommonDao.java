package id.co.sinarmaslife.eproposal.dao;

import id.co.sinarmaslife.eproposal.common.parent.EproposalDao;
import id.co.sinarmaslife.eproposal.model.Email;
import id.co.sinarmaslife.eproposal.model.data_proposal.DataProposal;
import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstDataProposal;
import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstProposalProduct;
import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstProposalProductPacket;
import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstProposalProductPeserta;
import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstProposalProductRider;
import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstProposalProductTopUp;
import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstProposalProductUlink;
import id.co.sinarmaslife.std.util.DateUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings( "unchecked" )
public class Cepr00000000CommonDao extends EproposalDao
{
    protected final Log logger = LogFactory.getLog( getClass() );

    /**
     * Scope dari dao ini hanyalah perintah2 SQL yang ada di namespace "id.co.sinarmaslife.eproposal."
     */
    public void initDao() throws Exception
    {
        this.statementNameSpace = "id.co.sinarmaslife.eproposal.";
        super.initDao();
    }

    public Date selectNowDate() throws DataAccessException
    {
    	return DateUtil.selectSysdate();
    }
  
    public String selectTitleBisnis(Map<String, Object> params) throws DataAccessException
    {
        return (String) querySingle( "selectTitleBisnis", params );
    }
    
    
    public List<BigDecimal> selectRate( Map<String, Object> params ) throws DataAccessException
    {
        return query( "selectRate", params );
    }

    public List<BigDecimal> selectRateNew( Map<String, Object> params ) throws DataAccessException
    {
        return query( "selectRateNew", params );
    }
    
    public BigDecimal selectRateForLoopingYearNo( Map<String, Object> params ) throws DataAccessException
    {
        return (BigDecimal) querySingle( "selectRateForLoopingYearNo", params );
    }
    
    public BigDecimal selectRateForLoopingYearNoNew( Map<String, Object> params ) throws DataAccessException
    {
        return (BigDecimal) querySingle( "selectRateForLoopingYearNoNew", params );
    }
    
    public Integer selectFMax( Map<String, Object> params ) throws DataAccessException
    {
        return ( Integer ) querySingle( "selectFMax", params );
    }

    public Date selectAddMonths( Map<String, Object> params ) throws DataAccessException
    {
        return DateUtil.selectAddDate( (Date) params.get("beginDate"), "mm", false, (Integer) params.get("noOfMonths") );
    }

    public List<String> selectJenisMedicalPrefix( Map<String, Object> params ) throws DataAccessException
    {
        return ( List<String> ) query( "selectJenisMedicalPrefix", params );
    }
    
    public List<String> selectJenisMedicalPrefixNew( Map<String, Object> params ) throws DataAccessException
    {
        return ( List<String> ) query( "selectJenisMedicalPrefixNew", params );
    }

    public Integer selectIdRangeSar( Map<String, Object> params ) throws DataAccessException
    {
        return ( Integer ) querySingle( "selectIdRangeSar", params );
    }

    public Integer selectIdRangeAge( Map<String, Object> params ) throws DataAccessException
    {
        return ( Integer ) querySingle( "selectIdRangeAge", params );
    }

    public Double selectSar( Map<String, Object> params ) throws DataAccessException
    {
        return ( Double ) querySingle( "selectSar", params );
    }
    
    public BigDecimal selectTableFactor( Map<String, Object> params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectTableFactor", params );
    }
    
    public BigDecimal selectNumericData( String params ) throws DataAccessException
    {
        return ( BigDecimal ) querySingle( "selectNumericData", params );
    }
    
    public DataProposal selectProposalData( String noProposal ) throws DataAccessException {
        DataProposal dataProposal = null;
        MstDataProposal mstDataProposal = (MstDataProposal) querySingle( "selectMstDataProposal", noProposal );
        
        if(mstDataProposal != null) {
            dataProposal = new DataProposal();
            dataProposal.setMst_data_proposal( mstDataProposal );
            dataProposal.setMst_proposal_product( (MstProposalProduct) querySingle( "selectMstProposalProduct", noProposal ) );
            dataProposal.setMst_proposal_product_packet( (ArrayList<MstProposalProductPacket>) query( "selectListMstProposalProductPacket", noProposal ) );
            dataProposal.setMst_proposal_product_peserta( (ArrayList<MstProposalProductPeserta>) query( "selectListMstProposalProductPeserta", noProposal ) );
            dataProposal.setMst_proposal_product_rider( (ArrayList<MstProposalProductRider>) query( "selectListMstProposalProductRider", noProposal ) );
            dataProposal.setMst_proposal_product_topup( (ArrayList<MstProposalProductTopUp>) query( "selectListMstProposalProductTopUp", noProposal ) );
            dataProposal.setMst_proposal_product_ulink( (ArrayList<MstProposalProductUlink>) query( "selectListMstProposalProductUlink", noProposal ) );
        }
        
        return dataProposal;
    }
    
    public Integer selectFlagInvestFund(String noProposal )throws DataAccessException
    {
        return ( Integer ) querySingle( "selectFlagInvestFund", noProposal );
    }
    
    public String selectLabelProductName(Integer lsbsId, Integer lsdbsNumber) throws DataAccessException {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("lsbs_id", lsbsId);
        params.put("lsdbs_number", lsdbsNumber);
        
        return ( String ) querySingle( "selectLabelProductName", params );
    }      
    
    public String selectSeqEmailId(){
		return(String) querySingle("selectSeqEmailId",null);
	}
    
    public void insertMstEmail(Email email){ 	
 	  insert( "insertMstEmail", email );
 	}
  //IGA 25112020 | Penambahan validasi prod syariah tdk bisa ambil VIP
    public Integer selectLineBus(Integer lsbsId )throws DataAccessException
    {
        return ( Integer ) querySingle( "selectLineBus", lsbsId );
    }
    /**NCR/2021/02/052	SMiLe Income Protection X-Tra**/
    public BigDecimal selectRateForBenefitofReturnPremium( Map<String, Object> params ) throws DataAccessException
    {
        return (BigDecimal) querySingle( "selectRateForBenefitofReturnPremium", params );
    }
}