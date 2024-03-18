package id.co.sinarmaslife.eproposal.product.product01040151;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040139Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : fadly m
 * Version              : 1.0
 * Creation Date    	: Jan 01, 2012 11:27:50 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000ProductBusiness;
import id.co.sinarmaslife.eproposal.common.pb.PbConverter;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000MoneyUtil;
import id.co.sinarmaslife.eproposal.product.product01040136.Cepr01040136Mapper;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030104Form;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.FormatDate;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.MappingUtil;
import id.co.sinarmaslife.standard.util.MathUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040151Business extends Cepr01040000ProductBusiness
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040151Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
    //    istr_prop = PbConverter.get_istr_prop( command, 50 );
    }
    
    
    public BigDecimal computeNT( Integer yearNo )
    {
        logger.info( "*-*-*-* Cepr01040151Business.computeNT" );
        Map<String, Object> sqlParams = new HashMap<String, Object>();
        
        sqlParams.put("lsbs_id", "223");
        
        if( cepr01030102Form.getRightPartOfBusinessCd()==Cepr01040151Mapper.X2 ){
        	 sqlParams.put("plan", 2);
        }else{
        	 sqlParams.put("plan", 1);
        }
       	sqlParams.put("liUsia", cepr01030101Form.getInsuredAge());     
       	sqlParams.put("lamaBayar", cepr01030102Form.getPremiumFurloughYearCd());      
        sqlParams.put("yearNo", yearNo);
        
        double nt = LazyConverter.toDouble(eproposalManager.selectNTKid(sqlParams)) * LazyConverter.toDouble(cepr01030102Form.getPremium()) * 0.01 * 50 ;
                
        BigDecimal nt2 =new BigDecimal(nt);
        return nt2;
    }
    
    
    public List<Map<String, Object>> getCommonHeaderRincianRowList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getCommonHeaderRowList" );

        List sekaligusRightPartCdList = ArrUtil.toListFromArray( sekaligusRightPartCdArr );
        List berkalaRightPartCdList = ArrUtil.toListFromArray( berkalaRightPartCdArr );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01030104Form cepr01030104Form = cepr01030000HoldingForm.getCepr01030104Form();
        
        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
       
          result.addAll( source.getPolicyHolderNameMap() );
            result.addAll( source.getPolicyHolderAgeMap() );
          
            	 Map<String, Object> param;
            	 param = new HashMap<String, Object>();
            	 param.put( "label", "Nama Peserta" );   
            	 param.put( "content",  cepr01030101Form.getInsuredName() );            	
            	 result.add( param );
            	 
            	 param = new HashMap<String, Object>();            
            	 param.put( "label", "Usia Peserta" );            	            	  
                 param.put( "content", cepr01030101Form.getInsuredAge() +" Tahun" );  
            	 result.add( param );
            	 
            	 param = new HashMap<String, Object>();            
            	 param.put( "label", "Masa Asuransi Syariah" );            	            	  
                 param.put( "content", cepr01030102Form.getPremiumFurloughYearCd() +" Tahun" );  
            	 result.add( param );
            	
            	 param = new HashMap<String, Object>();            
            	 param.put( "label", "Manfaat Bulanan" );
                 String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
                 
                 //k = Frekuensi pembayaran kontribusi dalam periode 1 (satu) tahun
                 // m = Jumlah bulan dalam periode 1 (satu) kami pembayaran kontribusi sesuai cara bayar
                  int k = 1;
                  int m = 1;     
                 double manfaat_bln = 0;
                  if( cepr01030102Form.getPaymentModeCd()==Hardcode.PAY_MODE_CD_BULANAN){
                 	 k = 12;
                 	 m = 1;
                  }else if( cepr01030102Form.getPaymentModeCd()==Hardcode.PAY_MODE_CD_SEMESTERAN){
                  	k = 2;
                  	m = 6;
                  }else if( cepr01030102Form.getPaymentModeCd()==Hardcode.PAY_MODE_CD_TRIWULANAN){
                 	 k = 4;
                 	 m = 3;
                  }else if( cepr01030102Form.getPaymentModeCd()==Hardcode.PAY_MODE_CD_TAHUNAN){
                 	 k = 1;
                 	 m = 12;
                  }                  
                  manfaat_bln = cepr01030102Form.getPremium().doubleValue()*10;
                  manfaat_bln = manfaat_bln/m;                                  
                 
	        	 param.put( "content",  currencySymbol + " " + editorUtil.convertToTwoDigit(manfaat_bln) );
            	 result.add( param );      
        return result;
    }
    
    public List<Map<String, Object>> getPageList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getPageList" );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub6" );
        result.add( params );
        return result;        
    }   
    
    public List<Map<String, Object>> getBiayaList(String word, BigDecimal biaya, String cara_bayar) throws IOException
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
	    Map<String, Object> params = new HashMap<String, Object>();
	    	    
	    params.put( "label", "-	Kontribusi "+ cara_bayar + word );	   	 
	    params.put( "content", editorUtil.convertToString( "Rp."+ editorUtil.convertToTwoDigit(biaya)+",- " ) );
	    params.put( "separator", ":" );
	    result.add( params );
	    return result;        
	 }
    
   
}


