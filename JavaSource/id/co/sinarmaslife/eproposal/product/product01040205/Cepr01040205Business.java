package id.co.sinarmaslife.eproposal.product.product01040205;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040205Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 13, 2008 1:42:51 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Ilustration116Business;
import id.co.sinarmaslife.eproposal.common.pb.PbConverter;
import id.co.sinarmaslife.eproposal.common.pb.PbFunction;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.pb.S_biaya;
import id.co.sinarmaslife.eproposal.model.vo.Cepr00000000YieldResultVO;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030104Form;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.MappingUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cepr01040205Business extends Cepr01040000Ilustration116Business
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040205Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
        setSekaligusRightPartCdArr( new Integer[]{ Cepr01040205Mapper.X3 } );
        setBerkalaRightPartCdArr( new Integer[]{ Cepr01040205Mapper.X4 } );
        setInsuredAgeLimit( 80 );
        
        setInvestmentTitleArr( new String[] {
 	            "",
 	            "EXCELLINK Fixed Income Fund",
 	            "EXCELLINK Dynamic Fund",
 	            "EXCELLINK Aggressive Fund",
 	            "Simas Fixed Income Fund",
                "Simas Dynamic Fund",
                "Simas Aggressive Fund",
 	            "EXCELLINK Cash Fund",
 	            "EXCELLINK Equity Bakti Peduli",
 	            "EXCELLINK Secure Dollar Income",
 	            "EXCELLINK Dynamic Dollar Fund"
 	        } );
 	        setInvestmentTitleAndDescrArr( new String[][] {
 	            { "", "", "" },
 	            { "", "- " +  "EXCELLINK Fixed Income Fund : "+ " Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang" },
 	            { "", "- " +  "Simas Fixed Income Fund :  "+ "Merupakan penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang" },
 	            { "", "- " +  "EXCELLINK Dynamic Fund : "+ "Penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas)" },
 	            { "", "- " +  "Simas Dynamic Fund : "+ "Merupakan penempatan maksimum 79% (tujuh puluh sembilan perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan pasar modal (ekuitas)" },
 	           	{ "", "- " +  "EXCELLINK Aggressive Fund : "+ "Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dan/atau maksimum penempatan 20% (dua puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar uang" },
 	           	{ "", "- " +  "Simas Aggressive Fund : "+ "Merupakan penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dan maksimum 20% (dua puluh perseratus)  instrumen-instrumen investasi pasar uang"},
 	           	{ "", "- " +  "EXCELLINK Cash Fund : "+ "Penempatan dana investasi 100% pada instrumen investasi pasar uang" },
	            { "", "- " +  "Excellink Equity Bakti Peduli : "+ " Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi pasar modal (ekuitas) dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang" },
	            { "", "- " +  "EXCELLINK Secure Dollar Income Fund : "+ "Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang" },
 	            { "", "- " +  "EXCELLINK Dynamic Dollar Fund : "+ "Penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas), dimana semua instrumen-instrumen investasi tersebut dalam mata uang US Dollar" },
 	        } );
        
        istr_prop = PbConverter.get_istr_prop( command, insuredAgeLimit );
    }
    
    public List<Map<String, Object>> getPageList( int currentIdx ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getPageList" );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub1" );
        result.add( params );

        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub2" );
        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub2a" );
        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub2b" );
        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub2c" );
        result.add( params );

        // if no riders, don't show this page ( page 3 )
        if( currentIdx != 0 )
        {
            params = new HashMap<String, Object>();
            params.put( "pageCode", "sub3" );
            result.add( params );
            
            params = new HashMap<String, Object>();
            params.put( "pageCode", "sub33" );
            result.add( params );
        }

//        params = new HashMap<String, Object>();
//        params.put( "pageCode", "sub4" );
//        result.add( params );
        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub6" );
        result.add( params );        

        return result;
    }
    
    public String getPageRider( int currentIdx ) throws IOException
    { 
    	String result = null;
    
    	 if( currentIdx != 0 )
         {             
            result = "sub3";            
         }
    	   return result;
    }
        
    public String checkTipeEkaSehat(int rider_baru)
    {    	
    	  String result = null;
    	  if( istr_prop.rider_baru[ rider_baru ] > 0 )
          {      
    		  if (rider_baru == 29){
    			  result = "Extra";
    		  }
    		  
          }
    	  
    	  return result;
    }
    
    
    public List<Map<String, Object>> getInvestmentChoiceRowList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getInvestmentChoiceRowList" );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        Map<String, Object> params;

        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 1 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getFixIdr() + "%" ) );
                result.add( params );

                for( int i = 2;  i < investmentTitleAndDescrArr[ 1 ].length; i++  )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", investmentTitleAndDescrArr[ 1 ][ i ] );
                    params.put( "allocationPercent", "" );
                    result.add( params );
                }
            }
            if( cepr01030102Form.getFixSimasIdr() != null && cepr01030102Form.getFixSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 2 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getFixSimasIdr() + "%" ) );
                result.add( params );

                for( int i = 2;  i < investmentTitleAndDescrArr[ 2 ].length; i++  )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", investmentTitleAndDescrArr[ 2 ][ i ] );
                    params.put( "allocationPercent", "" );
                    result.add( params );
                }
            }
            if( cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 3 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicIdr() + "%" ) );
                result.add( params );

                for( int i = 2;  i < investmentTitleAndDescrArr[ 3 ].length; i++  )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", investmentTitleAndDescrArr[ 3 ][ i ] );
                    params.put( "allocationPercent", "" );
                    result.add( params );
                }
            }
            if( cepr01030102Form.getDynamicSimasIdr() != null && cepr01030102Form.getDynamicSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 4 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicSimasIdr() + "%" ) );
                result.add( params );

                for( int i = 2;  i < investmentTitleAndDescrArr[ 4 ].length; i++  )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", investmentTitleAndDescrArr[ 4 ][ i ] );
                    params.put( "allocationPercent", "" );
                    result.add( params );
                }
            }
            if( cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 5 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveIdr() + "%" ) );
                result.add( params );

                for( int i = 2;  i < investmentTitleAndDescrArr[ 5 ].length; i++  )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", investmentTitleAndDescrArr[ 5 ][ i ] );
                    params.put( "allocationPercent", "" );
                    result.add( params );
                }
            }
            if( cepr01030102Form.getAggresiveSimasIdr() != null && cepr01030102Form.getAggresiveSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 6 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveSimasIdr() + "%" ) );
                result.add( params );

                for( int i = 2;  i < investmentTitleAndDescrArr[ 6 ].length; i++  )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", investmentTitleAndDescrArr[ 6 ][ i ] );
                    params.put( "allocationPercent", "" );
                    result.add( params );
                }
            }
            if( cepr01030102Form.getCashFundIdr() != null ){
            	if( cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) > 0 )            
  	          {
  	          	 params = new HashMap<String, Object>();
  	               params.put( "description", investmentTitleAndDescrArr[ 7 ][ 1 ] );
  	               params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getCashFundIdr() + "%" ) );
  	               result.add( params );
  	               
  	               for( int i = 2;  i < investmentTitleAndDescrArr[ 7 ].length; i++  )
  	               {
  	                   params = new HashMap<String, Object>();
  	                   params.put( "description", investmentTitleAndDescrArr[ 7 ][ i ] );
  	                   params.put( "allocationPercent", "" );
  	                   result.add( params );
  	               }
  	          	
  	          }
            }
            
            if( cepr01030102Form.getExcellinkEquityIdr() != null && cepr01030102Form.getExcellinkEquityIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
            	 params = new HashMap<String, Object>();
                 params.put( "description", investmentTitleAndDescrArr[ 8 ][ 1 ] );
                 params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getExcellinkEquityIdr() + "%" ) );
                 result.add( params );
                 
                 for( int i = 2;  i < investmentTitleAndDescrArr[ 8 ].length; i++  )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", investmentTitleAndDescrArr[ 8 ][ i ] );
                     params.put( "allocationPercent", "" );
                     result.add( params );
                 }          	
            }   
            
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getSecureUsd() != null && cepr01030102Form.getSecureUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
          		  params = new HashMap<String, Object>();
                    params.put( "description", investmentTitleAndDescrArr[ 9 ][ 1 ] );
                    params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getSecureUsd() + "%" ) );
                    result.add( params );

                    for( int i = 2;  i < investmentTitleAndDescrArr[ 9 ].length; i++  )
                    {
                    	if( investmentTitleAndDescrArr[ 9 ][ i ] != null)
                    	{
      	                    params = new HashMap<String, Object>();
      	                    params.put( "description", investmentTitleAndDescrArr[ 9 ][ i ] );
      	                    params.put( "allocationPercent", "" );
      	                    result.add( params );
                    	}
                    }        	
            }
            if( cepr01030102Form.getDynamicUsd() != null && cepr01030102Form.getDynamicUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
  	            
          		   params = new HashMap<String, Object>();
   	              params.put( "description", investmentTitleAndDescrArr[ 10 ][ 1 ] );
   	              params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicUsd() + "%" ) );
   	              result.add( params );
   	              for( int i = 2;  i < investmentTitleAndDescrArr[ 10 ].length; i++  )
   	              {
   	              	if( investmentTitleAndDescrArr[ 10 ][ i ] != null ){
   		                    params = new HashMap<String, Object>();
   		                    params.put( "description", investmentTitleAndDescrArr[ 10 ][ i ] );
   		                    params.put( "allocationPercent", "" );
   		                    result.add( params );
   	              	}
   	              }        		 
          	 }
            
        }
        return result;
    }
    
    public List<Map<String, Object>> getInvestmentChoiceSmallRowList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getInvestmentChoiceSmallRowList" );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        Map<String, Object> params;

        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 1 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getFixIdr() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getFixIdr() + "%" ) );
                result.add( params );
            }
            
            if( cepr01030102Form.getFixSimasIdr() != null && cepr01030102Form.getFixSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
            	if( cepr01030102Form.getLjiFixSimasCd().equals("35") ){
  	                params = new HashMap<String, Object>();
  	                params.put( "description", "- " + investmentTitleArr[ 4 ] );
  	                params.put( "currencySymbol", currencySymbol );
  	                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getFixSimasIdr() ).divide( new BigDecimal( "100" ) ) ) );
  	                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getFixSimasIdr() + "%" ) );
  	                result.add( params );
  	            }
            }
            
            if( cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 2 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getDynamicIdr() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicIdr() + "%" ) );
                result.add( params );
            }
            
            if( cepr01030102Form.getDynamicSimasIdr() != null  && cepr01030102Form.getDynamicSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
            	if( cepr01030102Form.getLjiDynamicSimasCd().equals("36") ){
  	                params = new HashMap<String, Object>();
  	                params.put( "description", "- " + investmentTitleArr[ 5 ] );
  	                params.put( "currencySymbol", currencySymbol );
  	                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getDynamicSimasIdr() ).divide( new BigDecimal( "100" ) ) ) );
  	                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicSimasIdr() + "%" ) );
  	                result.add( params );
            	}
            }

            if( cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 3 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getAggresiveIdr() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveIdr() + "%" ) );
                result.add( params );
            }
            if( cepr01030102Form.getAggresiveSimasIdr() != null  && cepr01030102Form.getAggresiveSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
            	if( cepr01030102Form.getLjiAggresiveSimasCd().equals("37") ){
            		 params = new HashMap<String, Object>();
                     params.put( "description", "- " + investmentTitleArr[ 6 ] );
                     params.put( "currencySymbol", currencySymbol );
                     params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getAggresiveSimasIdr() ).divide( new BigDecimal( "100" ) ) ) );
                     params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveSimasIdr() + "%" ) );
                     result.add( params );
            	}               
            }
            
            if( cepr01030102Form.getCashFundIdr() != null ){
            		if( cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 7 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getCashFundIdr() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getCashFundIdr() + "%" ) );
                result.add( params );
            }}          
            if( cepr01030102Form.getExcellinkEquityIdr() != null && cepr01030102Form.getExcellinkEquityIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 8 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getExcellinkEquityIdr() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getExcellinkEquityIdr() + "%" ) );
                result.add( params );
            }
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {    	
            if( cepr01030102Form.getSecureUsd() != null && cepr01030102Form.getSecureUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
           
                params.put( "description", "- " + investmentTitleArr[ 9 ] );         
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getSecureUsd() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getSecureUsd() + "%" ) );
                result.add( params ); 
            }

            if( cepr01030102Form.getDynamicUsd() != null && cepr01030102Form.getDynamicUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 10 ] );
                             
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getDynamicUsd() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicUsd() + "%" ) );
                result.add( params );
            }
        }

        return result;
    }
    
    public Cepr00000000YieldResultVO of_get_rate_ds()
    {
        logger.info( "*-*-*-* Cepr01040201Business.of_get_rate_ds" );
        String[] ls_jenis = getInvestmentTitleArr();
        Cepr00000000YieldResultVO result = new Cepr00000000YieldResultVO();
        ArrayList<Map<String, Object>> yieldList = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        int i;
        S_biaya lstr;

        lstr = PbFunction.wf_get_biaya_84( istr_prop, command );
        int a, b;
        
           if( "01".equals( istr_prop.kurs_id ) )
  	         {
  	             a = 1;	        
  	             b = 8;          
  	         }
  	         else
  	         {
  	             a = 9;
  	             b = 10;
  	         }
                
        double totalInvestmentAllocation = 0;
        double totalAsumptionLow = 0;
        double totalAsumptionMid = 0;
        double totalAsumptionHi = 0;

        double allocationYieldLow;
        double allocationYieldMid;
        double allocationYieldHi;

        double annualYieldLow;
        double annualYieldMid;
        double annualYieldHi;
        int j;
        for( i = a; i <= b; i++ )
        {
      	  j=i;
      	  
      	  annualYieldLow = lstr.bunga[ j ][ 1 ] * 100;
            annualYieldMid = lstr.bunga[ j ][ 2 ] * 100;
            annualYieldHi = lstr.bunga[ j ][ 3 ] * 100;
            
            allocationYieldLow = annualYieldLow * istr_prop.fund[ j ] / 100;
  	      allocationYieldMid = annualYieldMid * istr_prop.fund[ j ] / 100;
  	      allocationYieldHi = annualYieldHi * istr_prop.fund[ j ] / 100;

            params = new HashMap<String, Object>();
            
            if("01".equals( istr_prop.kurs_id )){   		   
  	 	      params.put( "fundDesc", ls_jenis[ i ] );
  	 		  params.put( "investmentAllocation", editorUtil.convertToTwoDigit( istr_prop.fund[ j ] ) + "%" );
  	 		  params.put( "allocationYieldLow", editorUtil.convertToTwoDigit( allocationYieldLow ) + "%" );
  	 	      params.put( "allocationYieldMid", editorUtil.convertToTwoDigit( allocationYieldMid ) + "%" );
  	 	      params.put( "allocationYieldHi", editorUtil.convertToTwoDigit( allocationYieldHi ) + "%" );
  	       
  	      }
  	 	  else if("02".equals( istr_prop.kurs_id )) {
  	 		  params.put( "fundDesc", ls_jenis[ i ] );
  	 		  params.put( "investmentAllocation", editorUtil.convertToTwoDigit( istr_prop.fund[ i ] ) + "%" );
  	                            
  	          params.put( "allocationYieldLow", editorUtil.convertToTwoDigit( allocationYieldLow ) + "%" );
  	          params.put( "allocationYieldMid", editorUtil.convertToTwoDigit( allocationYieldMid ) +  "%" );
  	          params.put( "allocationYieldHi", editorUtil.convertToTwoDigit( allocationYieldHi ) + "%" );       		 
  	 	  }  
            
            params.put( "fundDesc", ls_jenis[ i ] );
            params.put( "annualYieldLow", editorUtil.convertToTwoDigit( annualYieldLow ) + "%" );
            params.put( "annualYieldMid", editorUtil.convertToTwoDigit( annualYieldMid ) + "%" );
            params.put( "annualYieldHi", editorUtil.convertToTwoDigit( annualYieldHi ) + "%" );
//            params.put( "investmentAllocation", editorUtil.convertToTwoDigit( istr_prop.fund[ i ] ) + "%" );
//            params.put( "allocationYieldLow", editorUtil.convertToTwoDigit( allocationYieldLow ) + "%" );
//            params.put( "allocationYieldMid", editorUtil.convertToTwoDigit( allocationYieldMid ) + "%" );
//            params.put( "allocationYieldHi", editorUtil.convertToTwoDigit( allocationYieldHi ) + "%" );
            yieldList.add( params );
         
          	 if( ("01".equals( istr_prop.kurs_id ) && i<9) ||  ("02".equals( istr_prop.kurs_id ) && i<11))
          	 {
   	            totalInvestmentAllocation = totalInvestmentAllocation + istr_prop.fund[ j ];
   	            totalAsumptionLow = totalAsumptionLow + allocationYieldLow;
   	            totalAsumptionMid = totalAsumptionMid + allocationYieldMid;
   	            totalAsumptionHi = totalAsumptionHi + allocationYieldHi;
             }            	
            
        }

        result.setYieldList( yieldList );
        result.setTotalInvestmentAllocation( editorUtil.convertToTwoDigit( totalInvestmentAllocation ) + "%" );
        result.setTotalAssumptionLow( editorUtil.convertToTwoDigit( totalAsumptionLow ) + "%" );
        result.setTotalAssumptionMid( editorUtil.convertToTwoDigit( totalAsumptionMid ) + "%" );
        result.setTotalAssumptionHi( editorUtil.convertToTwoDigit( totalAsumptionHi ) + "%" );

        return result;
    }
    
    public IllustrationResultVO getIllustrationResult()
    {
        logger.info( "*-*-*-* Cepr01040000Ilustration116Business.getIllustrationResult" );
        IllustrationResultVO result = new IllustrationResultVO();
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        Cepr01030101Form cepr01030101Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030101Form();
        Cepr01030104Form cepr01030104Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030104Form();
        CredentialsVO credentialsVO = ( ( Cepr01030000HoldingForm ) command ).getCredentialsVO();

        ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        List<TopupDrawVO> topupDrawVOList = cepr01030104Form.getTopupDrawVOList();
        int topupDrawVOListSize = topupDrawVOList.size();
        TopupDrawVO topupDrawVO;
        String premiumTotal;
        String topup;
        String draw;

        int li_ke = 0, li_bagi = 1000, li_hal = 3;
        double[] ldec_bak;
        double ldec_bawal = 100000;
        double[] ldec_man_non = new double[2 + 1];
        double ldec_bak_tu = 0.05;
        double ldec_bak_tut = 0.05;
        double ldec_akuisisi;
        double ldec_premi_invest;
        double[][] ldec_hasil_invest = new double[5 + 1][3 + 1];
        double[] ldec_tarik = { CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0 };
        double ldec_wdraw;
        double[] ldec_premi_bulan = new double[12 + 1];
        double[] premi_bulan_sp = new double[12 + 1];
        double  ldec_premi_setahun_sp = 0;
        double ldec_topup;
        double ldec_bass;
        double[][] ldec_bunga = new double[5 + 1][3 + 1];
        double[] ldec_bunga_avg = new double[3 + 1]; //= {0.06, 0.1, 0.08, 0.18, 0.12, 0.25} //{0.09, 0.06, 0.11, 0.15, 0.165, 0.25}  //fixed:0.09, 0.11, 0.15 (0.165); dynamic:0.08, 0.11, 0.18 (0.195)
        double ldec_fee = 0.020075;
        double ldec_coi = 0, ldec_mfc, ldec_sc, ldec_cost, ldec_man_celaka, ldec_temp = 0; //, ldec_man[10+1] = {1, 1, 1, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7}
        double specialOfferDouble = 0;
        boolean[] lb_minus = { CommonConst.DUMMY_FALSE, false, false, false };
        boolean lb_rider = false;
        String ls_sy = "", ls_temp; //ls_dpp = ' dari Premi Pokok', ls_sy = ''
        S_biaya lstr;
//n_riders ln_riders
        double ldec_manfaat, ldec_premi_setahun = 0, ldec_coi24 = 0;
////monthly fix cost
        ldec_mfc = 15000;
        if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            ldec_mfc = 2;
            li_bagi = 1;
        }

        if( istr_prop.bisnis_id == 162 || istr_prop.bisnis_id == 190 || istr_prop.bisnis_id == 200)
        {
        	//ldec_mfc = Biaya Administrasi
            ldec_mfc = 45000;
            if ( istr_prop.bisnis_id == 190 || istr_prop.bisnis_id == 200)  ldec_mfc = 27500;
            if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) ) ldec_mfc = 5;
        }

        if( istr_prop.bisnis_id == 115 || istr_prop.bisnis_id == 152 )
        {
            ldec_bak_tut = 0.01;
            ldec_bak_tu = 0.01;
	        ldec_tarik = new double[] { CommonConst.DUMMY_ZERO, 0.05, 0.04, 0.03, 0.02, 0.01 };
        }
        else if( istr_prop.bisnis_id == 138 || ( istr_prop.bisnis_id == 116 && istr_prop.bisnis_no == 6 ))
        {
            ldec_bak_tut = 0;
	        ldec_bak_tu = 0;
        }
       
    

        for( int i = 1; i <= 12; i++ )
        {
            ldec_premi_bulan[ i ] = 0;
            premi_bulan_sp[ i ] = 0;
            if( i == 1 ) {
            	ldec_premi_bulan[ i ] = istr_prop.premi;
            	if( cepr01030102Form.getBasePremium() != null)premi_bulan_sp[i] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
            }
            if( Hardcode.PAY_MODE_CD_TRIWULANAN == cepr01030102Form.getPaymentModeCd() )
            {
                if( i == 4 || i == 7 || i == 10 ) 
                	{
                		ldec_premi_bulan[ i ] = istr_prop.premi;
                		if( cepr01030102Form.getBasePremium() != null)premi_bulan_sp[i] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
                	}
            }
            else if( Hardcode.PAY_MODE_CD_SEMESTERAN == cepr01030102Form.getPaymentModeCd() )
            {
                if( i == 7 ) 
                {
                	ldec_premi_bulan[ i ] = istr_prop.premi;
                	if( cepr01030102Form.getBasePremium() != null)premi_bulan_sp[i] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
                }
            }
            else if( Hardcode.PAY_MODE_CD_BULANAN == cepr01030102Form.getPaymentModeCd() )
            {
                ldec_premi_bulan[ i ] = istr_prop.premi;
                if( cepr01030102Form.getBasePremium() != null)premi_bulan_sp[i] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
            }
            ldec_premi_setahun += ldec_premi_bulan[ i ];
            ldec_premi_setahun_sp += premi_bulan_sp[ i ];
        }

        for( int i = 1; i <= 3; i++ )
        {
            ldec_hasil_invest[ 1 ][ i ] = 0;
        }

        ldec_manfaat = istr_prop.up;
////Biaya Akuisisi & asumsi penarikan (1 tarik, 1-nya tdk tarik)

        lstr = PbFunction.wf_get_biaya_84( istr_prop, command );
        ldec_bak = lstr.bak;
        ldec_bunga = lstr.bunga;

//        int a, b;
//        if( "01".equals( istr_prop.kurs_id ) )
//        {
//            a = 1;
//            b = 3;
//        }
//        else
//        {
//            a = 4;
//            b = 5;
//        }
        /* Adrian - CashFund
        for( int i = 1; i <= 3; i++ )
        {
            ldec_bunga_avg[ i ] = 0;
            for( int j = 1; j <= 5; j++ )
            {
                ldec_bunga_avg[ i ] += ldec_bunga[ j ][ i ] * ( istr_prop.fund[ j ] / 100 );
            }
        }*/
        
        int a, b;
  
       	 if( "01".equals( istr_prop.kurs_id ) )
            {
                a = 1;
           
                	b = 8;          
            }
            else
            {
                a = 9;
                b = 10;
            }       	
       	
       for( int i = 1; i <= 3; i++ )
       {
           ldec_bunga_avg[ i ] = 0;
           for( int j = a; j <= b; j++ )
           {
               ldec_bunga_avg[ i ] += ldec_bunga[ j ][ i ] * ( istr_prop.fund[ j ] / 100 );
           }
       }

        double[] np = new double[4];
        double[] celaka = new double[4];
        double[] npbatal = new double[4];
        
        if(istr_prop.bisnis_id == 159 || istr_prop.bisnis_id == 160)// ekalink family
        {
        	 specialOfferDouble =(ldec_premi_setahun_sp * 0.4);
        }else if(istr_prop.bisnis_id == 140){// medivest
        	 specialOfferDouble =(ldec_premi_setahun_sp * 0.3);
        }            
        
        int j;        
               
        for( int i = 1; i <= istr_prop.ins_per; i++ )
        {
            //surrender charge
            ldec_sc = 0;
            ldec_wdraw = 0;
            ldec_topup = 0;
            ldec_akuisisi = 0;
            // compute tabel Ilustrasi Perkembangan Dana
            ldec_coi = of_get_coi( i );            
           
            if( i <= ArrUtil.upperBound( lstr.tarik ) ) ldec_wdraw = lstr.tarik[ i ];
            if( i <= ArrUtil.upperBound( lstr.topup ) ) ldec_topup = lstr.topup[ i ];
            if( ArrUtil.upperBound( ldec_bak ) > i ) ldec_akuisisi = ldec_bak[ i ];

            if( i <= 5 && ( istr_prop.bisnis_id == 115 || istr_prop.bisnis_id == 152 ) ) ldec_sc = ldec_tarik[i];


            if( cepr01030102Form.getLeftPartOfBusinessCd() == 162  )
            {
                if( i == 1 )
                {
                    //ass basic dinolin, ass rider tidk 23/08/07
                    ldec_temp = of_get_coi_basic( i );
                    ldec_coi = ldec_coi - ldec_temp;
                    //ldec_temp = ldec_temp / 2 //distahunkan /24
                    ldec_mfc = 0;
                    //ass rider pa, hcp, hcp fam ada (3/9/07)
                    for( int k = 1; k <= ArrUtil.upperBound( istr_prop.rider_baru ); k++ )
                    {
                        if( !( ( k == 1 ) || ( k == 2 ) || ( k == 11 ) || ( k == 13 ) || ( k == 15 )) )
                        {
                            if( istr_prop.rider_baru[ k ] > 0 )
                            {
                                ldec_coi -= of_get_coi_rider( k, 1 );
                                ldec_temp += of_get_coi_rider( k, 1 );
                            }
                        }
                        else
                        {
                            //pa, hcp, hcp fam tdk ditunda
                        }
                    }
                    ldec_temp = ldec_temp / 2;  //distahunkan /24
                }
                else if( i >= 3 && i <= 4 )
                {
                    ldec_coi24 = ldec_temp;
                }
                else
                {
                    ldec_coi24 = 0;
                }

                if( i >= 2 )
                {
                    ldec_mfc = 45000; //30000  16/07/07
                    if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) )
                    {
                        ldec_mfc = 5;
                    }
                    //tambahin per 10/8/07
                    if( istr_prop.cb_id == 0 )
                    {
                        ldec_mfc = 30000;
                        if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) )
                        {
                            ldec_mfc = 4;
                        }
                    }
                }
            }   
            if( cepr01030102Form.getLeftPartOfBusinessCd() == 190 )
            {
            	if( istr_prop.cb_id != 0 ){
            		if( i == 1 ){
            			ldec_temp = ldec_coi;
            			ldec_coi = 0;
            			ldec_mfc = 0;
            		}else if( i == 2 ){
            			double kurs = 0.0;
            		     if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) )
                         {
            		    	 kurs = 5;
                         }else{
                        	 kurs = 27500;
                         }
            			ldec_coi24 = ldec_temp + kurs;
            		}else{
            			ldec_coi24 = 0;
            		}
            		if( i >= 2 ){
            			ldec_mfc = 27500;
            			if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) )
                        {
            				ldec_mfc = 5;
                        }
            		}
            	}
            }

            ldec_cost = ( ldec_coi + ldec_mfc + ldec_coi24 );

            for( int k = 1; k <= 3; k++ )
            {
                for( int li_bulan = 1; li_bulan <= 12; li_bulan++ )
                {
                    ldec_premi_invest = 0;
                    if( i <= cepr01030102Form.getPremiumFurloughYear() )
                    {
                        ldec_premi_invest = ( ( ldec_premi_bulan[ li_bulan ] * ( istr_prop.pct_premi / 100 ) ) * ( 1 - ldec_akuisisi ) );
                        ldec_premi_invest += ( ( ldec_premi_bulan[ li_bulan ] * ( 100 - istr_prop.pct_premi ) / 100 ) ) * ( 1 - ldec_bak_tu );  //topup berkala
                    }
                    if( li_bulan == 1 ) ldec_premi_invest += ( ldec_topup * ( 1 - ldec_bak_tut ) );  //topup tunggal
                    if( i == 1 ){
	                    // special offer hanya utk group mnc
	                    	if( credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_MNC ) && cepr01030102Form.getSpecialOfferCd() != null// utk group mnc
	                    			&& cepr01030102Form.getSpecialOfferCd().equals( Hardcode.ADDITIONAL_UNIT ) )
	                    	{	
	                    		if( li_bulan == 1 ) ldec_premi_invest += ( specialOfferDouble );  // additional unit
	                    	}
                    }
                    double copy = ldec_hasil_invest[ 1 ][ k ];

                    ldec_hasil_invest[ 1 ][ k ] = ( ldec_premi_invest + copy - ldec_cost ) * Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 ) );
                }

                ldec_hasil_invest[ 1 ][ k ] -= ( ldec_wdraw * ( 1 + ldec_sc ) );
                
                if( cepr01030102Form.getLeftPartOfBusinessCd() == 190 &&  i> 1  ){
                	if( ldec_hasil_invest[ 1 ][ k ] <= 0 && ( ( istr_prop.umur_tt < 50 && i <= 15 ) || ( istr_prop.umur_tt >= 50 && i <= 10 ) ) )
	                {
	                    lb_minus[ k ] = true;
	                }                	
                }else if( cepr01030102Form.getLeftPartOfBusinessCd() != 190){
                	if( ldec_hasil_invest[ 1 ][ k ] <= 0 && ( ( istr_prop.umur_tt < 50 && i <= 15 ) || ( istr_prop.umur_tt >= 50 && i <= 10 ) ) )
	                {
	                    lb_minus[ k ] = true;
	                }  
                }
            }

          // j = cepr01030101Form.getInsuredAge() + i;
             j = istr_prop.umur_tt + i;    
            
            if( i <= 21|| j == 55 || j == 65 || j == 75 || j == 80 || j == 100 || ( j == 88 && istr_prop.bisnis_id == 162) || ((j == 90 || j == 95 ) && (istr_prop.bisnis_id == 190 || istr_prop.bisnis_id == 200 )) )
            {
                for( int k = 1; k <= 3; k++ )
                {
                    np[ k ] = FormatNumber.round( ldec_hasil_invest[ 1 ][ k ] / li_bagi, 0, istr_prop.kurs_id );
                    celaka[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] + ldec_manfaat ) / li_bagi, 0, istr_prop.kurs_id );
                    npbatal[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] * (1 - ldec_sc)  ) / li_bagi, 0 );
                }
                if( i <= istr_prop.cuti_premi )
                {
                    premiumTotal = editorUtil.convertToStringWithoutCentAndNillIfNegative( ldec_premi_setahun / li_bagi );
                }
                else
                {
                    premiumTotal = "";
                }

                if( i < topupDrawVOListSize )
                {
                    // why ( i - 1 )? becoz index in Java start from 0, not like PB programming language
                    topupDrawVO = topupDrawVOList.get( i - 1 );
                    topup = "0";
                    draw = "0";
                    if( topupDrawVO.getYearFlag() != null && topupDrawVO.getYearFlag().equals("true") ){
                      if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) ){
                  		  	topup = editorUtil.convertToString( topupDrawVO.getTopupAmount() );
    	                    draw = editorUtil.convertToString( topupDrawVO.getDrawAmount() );
                  	  }else{
                  		  topup = editorUtil.convertToString( topupDrawVO.getTopupAmount().divide( new BigDecimal( "1000" ) ) );
    	                  draw = editorUtil.convertToString( topupDrawVO.getDrawAmount().divide( new BigDecimal( "1000" ) ) );                		  
                  	  } 
                    }
                    if( "0".equals( topup ) || "0.00".equals( topup ) ) topup = "0";
                    if( "0".equals( draw ) || "0.00".equals( draw ) ) draw = "0";
                }
                else
                {
                    topup = "0";
                    draw = "0";
                }
                map = new HashMap<String, Object>();
                
                if( i == 1 ){
                	String specialOffer = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( specialOfferDouble/1000 ) );
                	map.put( "specialOffer", specialOffer );
                }else{
                	map.put( "specialOffer", "" );
                }
                String valueLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 1 ] ) );
                String valueMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 2 ] ) );
                String valueHi = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 3 ] ) );

                String batalLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 1 ] ) );
                String batalMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 2 ] ) );
                String batalHi  =  editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 3 ] ) );
                
                String benefitLow = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 1 ], np[ 1 ] );
                String benefitMid = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 2 ], np[ 2 ] );
                String benefitHi = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 3 ], np[ 3 ] );
                	
                map.put( "yearNo", editorUtil.convertToString( i ) );
               // map.put( "insuredAge", editorUtil.convertToString( cepr01030101Form.getInsuredAge() + i ) );                
                map.put( "insuredAge", editorUtil.convertToString( istr_prop.umur_tt + i ) );
                map.put( "premiumTotal", premiumTotal );
                map.put( "topupAssumption", topup );
                map.put( "drawAssumption", draw );
                map.put( "valueLow", valueLow );
                map.put( "valueMid", valueMid );
                map.put( "valueHi", valueHi );
                map.put( "batalLow", batalLow );
                map.put( "batalMid", batalMid );
                map.put( "batalHi", batalHi );
                map.put( "benefitLow", benefitLow );
                map.put( "benefitMid", benefitMid );
                map.put( "benefitHi", benefitHi );
                mapList.add( map );

            }
        }  
        
        result.setValidityMsg( lb_minus[ 1 ] ? "Maaf, Proposal yang Anda buat Tidak Layak Jual." : "" );
        result.setIllustrationList( mapList );
        return result;
    }
    
    
    public IllustrationResultVO getIllustrationResult55()
    {
        logger.info( "*-*-*-* Cepr01040000Ilustration116Business.getIllustrationResult" );
        IllustrationResultVO result = new IllustrationResultVO();
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        Cepr01030101Form cepr01030101Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030101Form();
        Cepr01030104Form cepr01030104Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030104Form();
        CredentialsVO credentialsVO = ( ( Cepr01030000HoldingForm ) command ).getCredentialsVO();

        ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        List<TopupDrawVO> topupDrawVOList = cepr01030104Form.getTopupDrawVOList();
        int topupDrawVOListSize = topupDrawVOList.size();
        TopupDrawVO topupDrawVO;
        String premiumTotal;
        String topup;
        String draw;

        int li_ke = 0, li_bagi = 1000, li_hal = 3, lama_tanggung = 0;
        double[] ldec_bak;
        double ldec_bawal = 100000;
        double[] ldec_man_non = new double[2 + 1];
        double ldec_bak_tu = 0.05;
        double ldec_bak_tut = 0.05;
        double ldec_akuisisi;
        double ldec_premi_invest;
        double[][] ldec_hasil_invest = new double[5 + 1][3 + 1];
        double[] ldec_tarik = { CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0 };
        double ldec_wdraw;
        double[] ldec_premi_bulan = new double[12 + 1];
        double[] premi_bulan_sp = new double[12 + 1];
        double  ldec_premi_setahun_sp = 0;
        double ldec_topup;
        double ldec_bass;
        double[][] ldec_bunga = new double[5 + 1][3 + 1];
        double[] ldec_bunga_avg = new double[3 + 1]; //= {0.06, 0.1, 0.08, 0.18, 0.12, 0.25} //{0.09, 0.06, 0.11, 0.15, 0.165, 0.25}  //fixed:0.09, 0.11, 0.15 (0.165); dynamic:0.08, 0.11, 0.18 (0.195)
        double ldec_fee = 0.020075;
        double ldec_coi = 0, ldec_mfc, ldec_sc, ldec_cost, ldec_man_celaka, ldec_temp = 0; //, ldec_man[10+1] = {1, 1, 1, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7}
        double specialOfferDouble = 0;
        boolean[] lb_minus = { CommonConst.DUMMY_FALSE, false, false, false };
        boolean lb_rider = false;
        String ls_sy = "", ls_temp; //ls_dpp = ' dari Premi Pokok', ls_sy = ''
        S_biaya lstr;
//n_riders ln_riders
        double ldec_manfaat, ldec_premi_setahun = 0, ldec_coi24 = 0;
////monthly fix cost
        ldec_mfc = 15000;
        if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            ldec_mfc = 2;
            li_bagi = 1;
        }

        if( istr_prop.bisnis_id == 162 || istr_prop.bisnis_id == 190 || istr_prop.bisnis_id == 200)
        {
        	//ldec_mfc = Biaya Administrasi
            ldec_mfc = 45000;
            if ( istr_prop.bisnis_id == 190 || istr_prop.bisnis_id == 200)  ldec_mfc = 27500;
            if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) ) ldec_mfc = 5;
        }

        if( istr_prop.bisnis_id == 115 || istr_prop.bisnis_id == 152 )
        {
            ldec_bak_tut = 0.01;
            ldec_bak_tu = 0.01;
	        ldec_tarik = new double[] { CommonConst.DUMMY_ZERO, 0.05, 0.04, 0.03, 0.02, 0.01 };
        }
        else if( istr_prop.bisnis_id == 138 || ( istr_prop.bisnis_id == 116 && istr_prop.bisnis_no == 6 ))
        {
            ldec_bak_tut = 0;
	        ldec_bak_tu = 0;
        }
       
    

        for( int i = 1; i <= 12; i++ )
        {
            ldec_premi_bulan[ i ] = 0;
            premi_bulan_sp[ i ] = 0;
            if( i == 1 ) {
            	ldec_premi_bulan[ i ] = istr_prop.premi;
            	if( cepr01030102Form.getBasePremium() != null)premi_bulan_sp[i] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
            }
            if( Hardcode.PAY_MODE_CD_TRIWULANAN == cepr01030102Form.getPaymentModeCd() )
            {
                if( i == 4 || i == 7 || i == 10 ) 
                	{
                		ldec_premi_bulan[ i ] = istr_prop.premi;
                		if( cepr01030102Form.getBasePremium() != null)premi_bulan_sp[i] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
                	}
            }
            else if( Hardcode.PAY_MODE_CD_SEMESTERAN == cepr01030102Form.getPaymentModeCd() )
            {
                if( i == 7 ) 
                {
                	ldec_premi_bulan[ i ] = istr_prop.premi;
                	if( cepr01030102Form.getBasePremium() != null)premi_bulan_sp[i] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
                }
            }
            else if( Hardcode.PAY_MODE_CD_BULANAN == cepr01030102Form.getPaymentModeCd() )
            {
                ldec_premi_bulan[ i ] = istr_prop.premi;
                if( cepr01030102Form.getBasePremium() != null)premi_bulan_sp[i] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
            }
            ldec_premi_setahun += ldec_premi_bulan[ i ];
            ldec_premi_setahun_sp += premi_bulan_sp[ i ];
        }

        for( int i = 1; i <= 3; i++ )
        {
            ldec_hasil_invest[ 1 ][ i ] = 0;
        }

        ldec_manfaat = istr_prop.up;
////Biaya Akuisisi & asumsi penarikan (1 tarik, 1-nya tdk tarik)

        lstr = PbFunction.wf_get_biaya_84( istr_prop, command );
        ldec_bak = lstr.bak;
        ldec_bunga = lstr.bunga;

//        int a, b;
//        if( "01".equals( istr_prop.kurs_id ) )
//        {
//            a = 1;
//            b = 3;
//        }
//        else
//        {
//            a = 4;
//            b = 5;
//        }
        /* Adrian - CashFund
        for( int i = 1; i <= 3; i++ )
        {
            ldec_bunga_avg[ i ] = 0;
            for( int j = 1; j <= 5; j++ )
            {
                ldec_bunga_avg[ i ] += ldec_bunga[ j ][ i ] * ( istr_prop.fund[ j ] / 100 );
            }
        }*/
        
        int a, b;
  
       	 if( "01".equals( istr_prop.kurs_id ) )
            {
                a = 1;
           
                	b = 8;          
            }
            else
            {
                a = 9;
                b = 10;
            }       	
       	
       for( int i = 1; i <= 3; i++ )
       {
           ldec_bunga_avg[ i ] = 0;
           for( int j = a; j <= b; j++ )
           {
               ldec_bunga_avg[ i ] += ldec_bunga[ j ][ i ] * ( istr_prop.fund[ j ] / 100 );
           }
       }

        double[] np = new double[4];
        double[] celaka = new double[4];
        double[] npbatal = new double[4];//--IGA-- NILAI TEBUS
        
        if(istr_prop.bisnis_id == 159 || istr_prop.bisnis_id == 160)// ekalink family
        {
        	 specialOfferDouble =(ldec_premi_setahun_sp * 0.4);
        }else if(istr_prop.bisnis_id == 140){// medivest
        	 specialOfferDouble =(ldec_premi_setahun_sp * 0.3);
        }            
        
        int j;        
        lama_tanggung = 55 - istr_prop.umur_tt;   
        for( int i = 1; i <= istr_prop.ins_per; i++ )
        {
            //surrender charge
            ldec_sc = 0;
            ldec_wdraw = 0;
            ldec_topup = 0;
            ldec_akuisisi = 0;
            // compute tabel Ilustrasi Perkembangan Dana
            ldec_coi = of_get_coi( i );            
           
            if( i <= ArrUtil.upperBound( lstr.tarik ) ) ldec_wdraw = lstr.tarik[ i ];
            if( i <= ArrUtil.upperBound( lstr.topup ) ) ldec_topup = lstr.topup[ i ];
            if( ArrUtil.upperBound( ldec_bak ) > i ) ldec_akuisisi = ldec_bak[ i ];

            if( i <= 5 && ( istr_prop.bisnis_id == 115 || istr_prop.bisnis_id == 152 ) ) ldec_sc = ldec_tarik[i];


            if( cepr01030102Form.getLeftPartOfBusinessCd() == 162  )
            {
                if( i == 1 )
                {
                    //ass basic dinolin, ass rider tidk 23/08/07
                    ldec_temp = of_get_coi_basic( i );
                    ldec_coi = ldec_coi - ldec_temp;
                    //ldec_temp = ldec_temp / 2 //distahunkan /24
                    ldec_mfc = 0;
                    //ass rider pa, hcp, hcp fam ada (3/9/07)
                    for( int k = 1; k <= ArrUtil.upperBound( istr_prop.rider_baru ); k++ )
                    {
                        if( !( ( k == 1 ) || ( k == 2 ) || ( k == 11 ) || ( k == 13 ) || ( k == 15 )) )
                        {
                            if( istr_prop.rider_baru[ k ] > 0 )
                            {
                                ldec_coi -= of_get_coi_rider( k, 1 );
                                ldec_temp += of_get_coi_rider( k, 1 );
                            }
                        }
                        else
                        {
                            //pa, hcp, hcp fam tdk ditunda
                        }
                    }
                    ldec_temp = ldec_temp / 2;  //distahunkan /24
                }
                else if( i >= 3 && i <= 4 )
                {
                    ldec_coi24 = ldec_temp;
                }
                else
                {
                    ldec_coi24 = 0;
                }

                if( i >= 2 )
                {
                    ldec_mfc = 45000; //30000  16/07/07
                    if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) )
                    {
                        ldec_mfc = 5;
                    }
                    //tambahin per 10/8/07
                    if( istr_prop.cb_id == 0 )
                    {
                        ldec_mfc = 30000;
                        if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) )
                        {
                            ldec_mfc = 4;
                        }
                    }
                }
            }   
            if( cepr01030102Form.getLeftPartOfBusinessCd() == 190 )
            {
            	if( istr_prop.cb_id != 0 ){
            		if( i == 1 ){
            			ldec_temp = ldec_coi;
            			ldec_coi = 0;
            			ldec_mfc = 0;
            		}else if( i == 2 ){
            			double kurs = 0.0;
            		     if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) )
                         {
            		    	 kurs = 5;
                         }else{
                        	 kurs = 27500;
                         }
            			ldec_coi24 = ldec_temp + kurs;
            		}else{
            			ldec_coi24 = 0;
            		}
            		if( i >= 2 ){
            			ldec_mfc = 27500;
            			if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) )
                        {
            				ldec_mfc = 5;
                        }
            		}
            	}
            }

            ldec_cost = ( ldec_coi + ldec_mfc + ldec_coi24 );

            for( int k = 1; k <= 3; k++ )
            {
                for( int li_bulan = 1; li_bulan <= 12; li_bulan++ )
                {
                    ldec_premi_invest = 0;
                    if( i <= lama_tanggung )
                    {
                        ldec_premi_invest = ( ( ldec_premi_bulan[ li_bulan ] * ( istr_prop.pct_premi / 100 ) ) * ( 1 - ldec_akuisisi ) );
                        ldec_premi_invest += ( ( ldec_premi_bulan[ li_bulan ] * ( 100 - istr_prop.pct_premi ) / 100 ) ) * ( 1 - ldec_bak_tu );  //topup berkala
                    }
                    if( li_bulan == 1 ) ldec_premi_invest += ( ldec_topup * ( 1 - ldec_bak_tut ) );  //topup tunggal
                    if( i == 1 ){
	                    // special offer hanya utk group mnc
	                    	if( credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_MNC ) && cepr01030102Form.getSpecialOfferCd() != null// utk group mnc
	                    			&& cepr01030102Form.getSpecialOfferCd().equals( Hardcode.ADDITIONAL_UNIT ) )
	                    	{	
	                    		if( li_bulan == 1 ) ldec_premi_invest += ( specialOfferDouble );  // additional unit
	                    	}
                    }
                    double copy = ldec_hasil_invest[ 1 ][ k ];

                    ldec_hasil_invest[ 1 ][ k ] = ( ldec_premi_invest + copy - ldec_cost ) * Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 ) );
                }

                ldec_hasil_invest[ 1 ][ k ] -= ( ldec_wdraw * ( 1 + ldec_sc ) );
                
                if( cepr01030102Form.getLeftPartOfBusinessCd() == 190 &&  i> 1  ){
                	if( ldec_hasil_invest[ 1 ][ k ] <= 0 && ( ( istr_prop.umur_tt < 50 && i <= 15 ) || ( istr_prop.umur_tt >= 50 && i <= 10 ) ) )
	                {
	                    lb_minus[ k ] = true;
	                }                	
                }else if( cepr01030102Form.getLeftPartOfBusinessCd() != 190){
                	if( ldec_hasil_invest[ 1 ][ k ] <= 0 && ( ( istr_prop.umur_tt < 50 && i <= 15 ) || ( istr_prop.umur_tt >= 50 && i <= 10 ) ) )
	                {
	                    lb_minus[ k ] = true;
	                }  
                }
            }

          // j = cepr01030101Form.getInsuredAge() + i;
             j = istr_prop.umur_tt + i;    
            
            if( i <= 21|| j == 55 || j == 65 || j == 75 || j == 80 || j == 100 || ( j == 88 && istr_prop.bisnis_id == 162) || ((j == 90 || j == 95 ) && (istr_prop.bisnis_id == 190 || istr_prop.bisnis_id == 200 )) )
            {
                for( int k = 1; k <= 3; k++ )
                {
                    np[ k ] = FormatNumber.round( ldec_hasil_invest[ 1 ][ k ] / li_bagi, 0, istr_prop.kurs_id );
                    celaka[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] + ldec_manfaat ) / li_bagi, 0, istr_prop.kurs_id );
                    npbatal[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] * (1 - ldec_sc)  ) / li_bagi, 0 );//--IGA-- NILAI TEBUS
                }
                if( i <= lama_tanggung )
                {
                    premiumTotal = editorUtil.convertToStringWithoutCentAndNillIfNegative( ldec_premi_setahun / li_bagi );
                }
                else
                {
                    premiumTotal = "";
                }

                if( i < topupDrawVOListSize )
                {
                    // why ( i - 1 )? becoz index in Java start from 0, not like PB programming language
                    topupDrawVO = topupDrawVOList.get( i - 1 );
                    topup = "0";
                    draw = "0";
                    if( topupDrawVO.getYearFlag() != null && topupDrawVO.getYearFlag().equals("true") ){
                      if( Hardcode.CUR_USD_CD.equals( istr_prop.kurs_id ) ){
                  		  	topup = editorUtil.convertToString( topupDrawVO.getTopupAmount() );
    	                    draw = editorUtil.convertToString( topupDrawVO.getDrawAmount() );
                  	  }else{
                  		  topup = editorUtil.convertToString( topupDrawVO.getTopupAmount().divide( new BigDecimal( "1000" ) ) );
    	                  draw = editorUtil.convertToString( topupDrawVO.getDrawAmount().divide( new BigDecimal( "1000" ) ) );                		  
                  	  } 
                    }
                    if( "0".equals( topup ) || "0.00".equals( topup ) ) topup = "0";
                    if( "0".equals( draw ) || "0.00".equals( draw ) ) draw = "0";
                }
                else
                {
                    topup = "0";
                    draw = "0";
                }
                map = new HashMap<String, Object>();
                
                if( i == 1 ){
                	String specialOffer = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( specialOfferDouble/1000 ) );
                	map.put( "specialOffer", specialOffer );
                }else{
                	map.put( "specialOffer", "" );
                }
                String valueLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 1 ] ) );
                String valueMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 2 ] ) );
                String valueHi = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 3 ] ) );

                String batalLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 1 ] ) );
                String batalMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 2 ] ) );
                String batalHi  =  editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( npbatal[ 3 ] ) );
                
                String benefitLow = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 1 ], np[ 1 ] );
                String benefitMid = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 2 ], np[ 2 ] );
                String benefitHi = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 3 ], np[ 3 ] );
                	
                map.put( "yearNo", editorUtil.convertToString( i ) );
               // map.put( "insuredAge", editorUtil.convertToString( cepr01030101Form.getInsuredAge() + i ) );                
                map.put( "insuredAge", editorUtil.convertToString( istr_prop.umur_tt + i ) );
                map.put( "premiumTotal", premiumTotal );
                map.put( "topupAssumption", topup );
                map.put( "drawAssumption", draw );
                map.put( "valueLow", valueLow );
                map.put( "valueMid", valueMid );
                map.put( "valueHi", valueHi );
                map.put( "batalLow", batalLow );
                map.put( "batalMid", batalMid );
                map.put( "batalHi", batalHi );
                map.put( "benefitLow", benefitLow );
                map.put( "benefitMid", benefitMid );
                map.put( "benefitHi", benefitHi );
                mapList.add( map );

            }
        }  
        
        result.setValidityMsg( lb_minus[ 1 ] ? "Maaf, Proposal yang Anda buat Tidak Layak Jual." : "" );
        result.setIllustrationList( mapList );
        return result;
    }
    
    
    public List<Map<String, Object>> getCommonHeaderSmallRowList_Surrender() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getCommonHeaderSmallRowList" );

        List sekaligusRightPartCdList = ArrUtil.toListFromArray( sekaligusRightPartCdArr );
        List berkalaRightPartCdList = ArrUtil.toListFromArray( berkalaRightPartCdArr );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01030104Form cepr01030104Form = cepr01030000HoldingForm.getCepr01030104Form();
        
        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getBabyFlag() ) )
        {
	       	 Map<String, Object> param;
	       	 param = new HashMap<String, Object>();
	       	 param.put( "label", "Nama Tertanggung" );
	       	 param.put( "content", editorUtil.convertToString( "Calon bayi Ibu "+cepr01030101Form.getInsuredName() ) );
	       	 result.add( param );
	       	 param = new HashMap<String, Object>();            
	       	 param.put( "label", "Usia Tertanggung" );
	       	 param.put( "content", editorUtil.convertToString( "0" ) + " tahun" );
	       	 result.add( param );
        }else
        {
        	 // Fill header content from CommonHeaderDataSource
            result.addAll( source.getInsuredNameMap() );
            result.addAll( source.getInsuredAgeMap() ) ;
        }
        
        result.addAll( source.getTermOfContractWithLimitMapInTwoRows( insuredAgeLimit ) );

        Map<String, Object> params;
        if( sekaligusRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
            params = new HashMap<String, Object>();
            params.put( "label", "Premi Pokok Sekaligus" );
            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) );
            result.add( params );
            
           if( cepr01030104Form.getTopupDrawVOList().get(0).getYearFlag() != null && cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().compareTo(new BigDecimal(0)) >= 1)
  	       {
        	   params = new HashMap<String, Object>();
               params.put( "label", "Premi Top Up Single" );
               params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount() ) );
               result.add( params ); 
         	} 
        }
        else if( berkalaRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
        	int cara_bayar = istr_prop.cb_id;
        	String label = "Tahunan";
        	if( cara_bayar == Hardcode.PAY_MODE_CD_TAHUNAN ){
        		label = "Tahunan";
        	}else if( cara_bayar == Hardcode.PAY_MODE_CD_BULANAN ){
        		label = "Bulanan";
        	}else if( cara_bayar == Hardcode.PAY_MODE_CD_TRIWULANAN ){
        		label = "Triwulanan";
        	}else if( cara_bayar == Hardcode.PAY_MODE_CD_SEMESTERAN ){
        		label = "Semesteran";
        	}
        	
//			(Helpdesk 56020): Paket-Bridge Agency tidak ditampilkan
    		if( (cepr01030102Form.getLeftPartOfBusinessCd() == 200 || cepr01030102Form.getLeftPartOfBusinessCd() == 190) && CommonConst.DISPLAY_TRUE.equals(cepr01030102Form.getJenisListDisplay()) && cepr01030102Form.getJenisCd() > 0 )
            {
            }else{
	            params = new HashMap<String, Object>();
	            params.put( "label", "Premi Pokok " + label );
	            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getBasePremium() ) + " (" + cepr01030102Form.getPremiumCombinationCd().toString() + "%)" );
	            result.add( params );
	
	            params = new HashMap<String, Object>();
	            params.put( "label", "Premi Top Up Berkala " + label );
	            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getTopUpPremium() ) + " (" + ( new BigDecimal( "100" ).subtract( cepr01030102Form.getPremiumCombinationCd() ) ).toString() + "%)" );
	            result.add( params );
            }
    		      
            params = new HashMap<String, Object>();
            params.put( "label", "Total Premi " + label );
            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) );
            result.add( params );

           if( cepr01030104Form.getTopupDrawVOList().get(0).getYearFlag() != null && cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().compareTo(new BigDecimal(0)) >= 1)
 	       {
        		 params = new HashMap<String, Object>();
                 params.put( "label", "Premi Top Up Single" );
                 params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount() ) );
                 result.add( params ); 
        	} 
            
         
        }

        result.addAll( source.getTotalSumInsuredMap() );

        return result;
    }
    
    
}