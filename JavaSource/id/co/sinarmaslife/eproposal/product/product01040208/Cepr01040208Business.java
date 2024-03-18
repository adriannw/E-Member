package id.co.sinarmaslife.eproposal.product.product01040208;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040208Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 4, 2008 9:20:54 AM
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
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Ilustration134Business;
import id.co.sinarmaslife.eproposal.common.pb.PbConverter;
import id.co.sinarmaslife.eproposal.common.pb.PbFunction;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.eproposal.model.pb.N_riders;
import id.co.sinarmaslife.eproposal.model.pb.S_biaya;
import id.co.sinarmaslife.eproposal.model.vo.Cepr00000000YieldResultVO;
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.eproposal.product.product01040228.Cepr01040228Mapper;
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

public class Cepr01040208Business extends Cepr01040000Ilustration134Business
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040208Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
        setSekaligusRightPartCdArr( new Integer[]{ Cepr01040208Mapper.X4, Cepr01040208Mapper.X5, Cepr01040208Mapper.X6, Cepr01040208Mapper.X7, Cepr01040208Mapper.X8 , Cepr01040208Mapper.X9,  Cepr01040208Mapper.X10, Cepr01040208Mapper.X11, Cepr01040208Mapper.X12} );
        setBerkalaRightPartCdArr( new Integer[]{ Cepr01040208Mapper.X3, Cepr01040208Mapper.X13 } );
        
        setInsuredAgeLimit( 100 );  
        
        String productLabel = "EXCELLINK"; 
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 ){
        	productLabel = "Simas";
        }
        
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11 ){        
        	  setInvestmentTitleArr( new String[] {
        			  "",
                      productLabel + " Fixed Income Fund",
                      productLabel + " Dynamic Fund",
                      productLabel + " Aggressive Fund",
                      productLabel + " Cash Fund",
                      productLabel + " Equity Bakti Peduli",
                      productLabel + " Secure Dollar Fund",
                      productLabel + " Dynamic Dollar Fund"
                  } ); 
        }else{	
	        setInvestmentTitleArr( new String[] {
	                "",
	                productLabel + " Fixed Income Fund",
	                productLabel + " Dynamic Fund",
	                productLabel + " Aggressive Fund",
	                productLabel + " Stable Dollar Fund",
	                productLabel + " Balance Dollar Fund"
	            } );
        }
        
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 ){
        	 setInvestmentTitleAndDescrArr( new String[][] {
      	            { "", "", "" },
      	            { "", "- " + "Simas Fix Income Fund", "Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang." },
      	            { "", "- " + "Simas Dynamic Fund", "Penempatan minimum 79% (tujuh puluh sembilan perseratus) dana investasi  pada   instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan pasar modal (equity)" },
      	            { "", "- " + "Simas Aggresive Fund", "Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dan/atau maksimum penempatan 20% (dua puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar uang. " },
     	            { "", "- " + "Simas Stable Dollar Fund", "Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang, dimana semua instrumen-instrumen investasi tersebut dalam mata uang US Dollar" },
     	            { "", "- " + "Simas Balance Dollar Fund", "Penempatan maksimum 79% (tujuh puluh sembilan perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan investasi pasar modal (ekuitas), dimana semua instrumen-instrumen investasi tersebut dalam mata uang US Dollar" },
      	        } );
        }else{   
        	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11 ){
        		 setInvestmentTitleAndDescrArr( new String[][] {
  		                { "", "", "" },
  		                { "", "- EXCELLINK Fixed Income Fund ", " Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang." },
  		                { "", "- EXCELLINK Dynamic Fund ", " Penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas)." },
  		                { "", "- EXCELLINK Aggressive Fund ", "  Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dan/atau maksimum penempatan 20% (dua puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar uang" },
  			            { "", "- EXCELLINK Cash Fund", "  Penempatan dana investasi 100% pada instrumen investasi pasar uang" },
  	    	            { "", "- EXCELLINK Equity Bakti Peduli :"+  " Penempatan minimum 80% (delapan puluh perseratus) dana investasi instrumen-instrumen investasi pasar modal (ekuitas) dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang" },
  			            { "", "- EXCELLINK Secure Dollar Income Fund : Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang." },
  		                { "", "- EXCELLINK Dynamic Dollar Fund : Penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas), dimana semua instrumen-instrumen investasi tersebut dalam mata uang US Dollar." },
  		            } );
        	 }else{
        		 setInvestmentTitleAndDescrArr( new String[][] {
 		                { "", "", "" },
 		                { "", "- EXCELLINK Fixed Income Fund ", " Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang." },
 		                { "", "- EXCELLINK Dynamic Fund ", " Penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas)." },
 		                { "", "- EXCELLINK Aggressive Fund ", "  Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dan/atau maksimum penempatan 20% (dua puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar uang" },
 		                { "", "- EXCELLINK Secure Dollar Income Fund : Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang." },
 		                { "", "- EXCELLINK Dynamic Dollar Fund : Penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas), dimana semua instrumen-instrumen investasi tersebut dalam mata uang US Dollar." },
 		            } );
        	 }
        }
                
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10 || cepr01030102Form.getRightPartOfBusinessCd() >= Cepr01040208Mapper.X12 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8){ //SIMAS PRIME LINK
        	setInsuredAgeLimit( 100 );
        	 setInvestmentTitleArr( new String[] {
                     "",
                     " EXCELLINK Fixed Income Fund",
                     " EXCELLINK Dynamic Fund",
                     " EXCELLINK Aggressive Fund",
                     " Simas Fixed Income Fund",
                     " Simas Dynamic Fund",
                     " Simas Aggressive Fund",
                     " EXCELLINK Cash Fund",
                     " EXCELLINK Equity Bakti Peduli",
                     " EXCELLINK Secure Dollar Income",
                     " EXCELLINK Dynamic Dollar Fund",
                     " EXCELLINK Global Aggressive Dollar Fund"
                 } ); 
        	  
        	  String investmentDescrFixedFund = "", investmentDescrDynamicFund = "", investmentDescrAggresiveFund ="";
        	  String productLabelFixedFund = "EXCELLINK", productLabelDynamicFund = "EXCELLINK", productLabelAggresiveFund ="EXCELLINK";
        	          	  
        	  /*
        	  if( cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo( new BigDecimal( "0" ) ) > 0 && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiFixListDisplay()) &&
         			 cepr01030102Form.getLjiFixCd().equals("01"))
              {
         		 productLabelFixedFund = "EXCELLINK";
              }
         	 else if( cepr01030102Form.getFixSimasIdr() != null && cepr01030102Form.getFixSimasIdr().compareTo( new BigDecimal( "0" ) ) > 0  &&
     			 cepr01030102Form.getLjiFixSimasCd().equals("35"))
              {	 
         		 productLabelFixedFund = "Simas";
              }
         	 
         	 if( cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0 && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiDynamicListDisplay()) &&
         			 cepr01030102Form.getLjiDynamicCd().equals("02"))
              {
         		 productLabelDynamicFund = "EXCELLINK";
              }
         	 else if( cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0 && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiDynamicListDisplay()) &&
         			 cepr01030102Form.getLjiDynamicCd().equals("36"))
              {
         		 productLabelDynamicFund = "Simas";
              }        	 
         	 if( cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0 && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiAggresiveListDisplay()) &&
         			 cepr01030102Form.getLjiAggresiveCd().equals("03"))
              {
         		 productLabelAggresiveFund = "EXCELLINK";
              }
         	 else if( cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0 && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiAggresiveListDisplay()) &&
         			 cepr01030102Form.getLjiAggresiveCd().equals("37"))
              {
         		 productLabelAggresiveFund = "Simas";
              }        	  
        	
        	  
        	  if( cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo( new BigDecimal( "0" ) ) > 0 && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiFixListDisplay()) &&
         			 cepr01030102Form.getLjiFixCd().equals("01"))
              {
         		 investmentDescrFixedFund = " Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang";
              }
         	 else if( cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo( new BigDecimal( "0" ) ) > 0 && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiFixListDisplay()) &&
     			 cepr01030102Form.getLjiFixCd().equals("35"))
              {	 
         		 investmentDescrFixedFund = " Penempatan minimum 80% pada Fixed Income dan maksimum 20% pada Pasar Uang ";
              }        	 
         	 if( cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0 && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiDynamicListDisplay()) &&
         			 cepr01030102Form.getLjiDynamicCd().equals("02"))
              {
         		 investmentDescrDynamicFund = " Penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas).";
              }
         	 else if( cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0 && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiDynamicListDisplay()) &&
         			 cepr01030102Form.getLjiDynamicCd().equals("36"))
              {
         		 investmentDescrDynamicFund = " Penempatan maksimum 79% pada masing-masing instrument Pasar Uang, Equity serta Investasi berpendapatan tetap.";
              }        	 
         	 if( cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0 && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiAggresiveListDisplay()) &&
         			 cepr01030102Form.getLjiAggresiveCd().equals("03"))
              {
         		 investmentDescrAggresiveFund = " Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dan/atau maksimum penempatan 20% (dua puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar uang.";
              }
         	 else if( cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0 && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiAggresiveListDisplay()) &&
         			 cepr01030102Form.getLjiAggresiveCd().equals("37"))
              {
         		 investmentDescrAggresiveFund = " Penempatan minimum 80% pada Equity dan maksimum 20% pada Pasar Uang.";
              }
         	  */
         	 
         	setInvestmentTitleAndDescrArr( new String[][] {
      	            { "", "",},
      	            { "", "- " +  "EXCELLINK Fixed Income Fund :"+ " Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang"},
      	            { "", "- " +  "Simas Fixed Income Fund :"+ " Merupakan penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang"},
      	            { "", "- " +  "EXCELLINK Dynamic Fund :"+ " Penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas)"},
      	            { "", "- " +  "Simas Dynamic Fund :"+ " Merupakan penempatan maksimum 79% (tujuh puluh sembilan perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan pasar modal (ekuitas)"},
    	            { "", "- " +  "EXCELLINK Aggresive Fund :"+ " Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dan/atau maksimum penempatan 20% (dua puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar uang"},
    	            { "", "- " +  "Simas Aggressive Fund :"+ " Merupakan penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dan maksimum 20% (dua puluh perseratus)  instrumen-instrumen investasi pasar uang"},
    	            { "", "- " +  "Excellink Cash Fund :"+  " Penempatan dana investasi 100% pada instrumen investasi pasar uang"},
    	            { "", "- " +  "EXCELLINK Equity Bakti Peduli :"+  " Penempatan minimum 80% (delapan puluh perseratus) dana investasi instrumen-instrumen investasi pasar modal (ekuitas) dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang" },
    	            { "", "- EXCELLINK Secure Dollar Income Fund :"+ "penempatan minimum 80% (delapan puluh perseratus) dana investasi pada  instrumen-instrumen investasi berpendapatan tetap dan/atau maksimum 20% (dua puluh perseratus) instrumen-instrumen investasi pasar uang, dimana semua instrumen-instrumen investasi tersebut dalam mata uang US Dollar", null },
      	            { "", "- EXCELLINK Dynamic Dollar Fund : penempatan minimum 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi berpendapatan tetap dan/atau instrumen-instrumen investasi pasar uang dan maksimum penempatan 50% (lima puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas), dimana semua instrumen-instrumen investasi tersebut dalam mata uang US Dollar", null },
      	            { "", "- EXCELLINK Global Aggressive Dollar Fund : Penempatan minimum 80% (delapan puluh perseratus) dana investasi pada instrumen-instrumen investasi pasar modal (ekuitas) dan maksimum 20% (dua puluh perseratus)  instrumen-instrumen investasi pasar uang, dimana semua instrument-instrument investasi tersebut dalam mata uang US Dollar.", null },
      	        } );
        	 
        }  

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
        params.put( "pageCode", "sub7" );
        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub8" );
        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub9" );
        result.add( params );

        // if no riders, don't show this page ( page 3 )
        if( currentIdx != 0 )
        {
            params = new HashMap<String, Object>();
            params.put( "pageCode", "sub3" );
            result.add( params );
            
            params = new HashMap<String, Object>();
            params.put( "pageCode", "sub4" );
            result.add( params );
          
        }
      
        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub6" );
        result.add( params );

        return result;
    }
    
    public String getRiderHcpList()
    {
        //List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	String biayaHcp = "";
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFlag() ) )
        {
            double benefitAmount = 0;
            // todo
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                benefitAmount = cepr01030103Form.getHcpCd() * 100000;
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	//benefitAmount = cepr01030103Form.getHcpCd() * 10;
                benefitAmount = cepr01030103Form.getHcpCd()/3 * 10;
            }

            biayaHcp = editorUtil.convertToString( benefitAmount ) + " per hari\n";
            biayaHcp = biayaHcp + editorUtil.convertToString( benefitAmount * 2 ) + " per hari\n";
            biayaHcp = biayaHcp + editorUtil.convertToString( benefitAmount * 2 ) + " per hari";
        }
        
        return biayaHcp;
    }    
    
    public List<Map<String, Object>> getCommonHeaderSmallRowList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getCommonHeaderSmallRowList" );

        List sekaligusRightPartCdList = ArrUtil.toListFromArray( sekaligusRightPartCdArr );
        List berkalaRightPartCdList = ArrUtil.toListFromArray( berkalaRightPartCdArr );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        // Fill header content from CommonHeaderDataSource
        result.addAll( source.getInsuredNameMap() );
        result.addAll( source.getInsuredAgeMap() );
        result.addAll( source.getTermOfContractWithLimitMapInTwoRows( insuredAgeLimit ) );
        //result.addAll( source.getTermOfContractWithLimitMap( insuredAgeLimit ) );

        Map<String, Object> params;
        if( sekaligusRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {           
            	params = new HashMap<String, Object>();
            	params.put( "label", "Premi Pokok Sekaligus" );
            	params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getBasePremium() ) );
                result.add( params );
                
            	params = new HashMap<String, Object>();
                params.put( "label", "Premi Top Up Sekaligus "  );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getTopUpPremium() )) ;
                result.add( params );
                       
        }
        else if( berkalaRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
        	int caraBayar = cepr01030102Form.getPaymentModeCd();
        	String label = "Tahunan";
        	switch(caraBayar) {
        		case Hardcode.PAY_MODE_CD_TRIWULANAN :
        			label = "Triwulanan";
        			break;
        		case Hardcode.PAY_MODE_CD_SEMESTERAN :
        			label = "Semesteran";
        			break;
        		case Hardcode.PAY_MODE_CD_BULANAN :
        			label = "Bulanan";
        			break;
        		default :
        			label = "Tahunan";
        			break;
        	}
        	
        	params = new HashMap<String, Object>();
            params.put( "label", "Premi Pokok " + label );
            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getBasePremium() ) + " (" + cepr01030102Form.getPremiumCombinationCd().toString() + "%)" );
            result.add( params );
            
            if(!cepr01030102Form.getTopUpPremium().toString().equals("0")){
            	params = new HashMap<String, Object>();
                params.put( "label", "Premi Top Up Berkala " + label );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getTopUpPremium() ) + " (" + ( new BigDecimal( "100" ).subtract( cepr01030102Form.getPremiumCombinationCd() ) ).toString() + "%)" );
                result.add( params );

                params = new HashMap<String, Object>();
                params.put( "label", "" );
                params.put( "content", "------------------------" );
                result.add( params );

                params = new HashMap<String, Object>();
                params.put( "label", "Total Premi " + label );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) );
                result.add( params );
            }
            
            if(!cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().toString().equals("0")){
            	params = new HashMap<String, Object>();
                params.put( "label", "Premi Top Up Single" );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount() ));
                result.add( params );
            }

            params = new HashMap<String, Object>();
            params.put( "label", "Masa Pembayaran Premi" );
            params.put( "content", editorUtil.convertToString( cepr01030102Form.getPremiumFurloughYear() ) +" Tahun" );
            result.add( params );
        }

        result.addAll( source.getTotalSumInsuredMap() );

        return result;
    }
    
    public IllustrationResultVO getIllustrationResult()
    {
        logger.info( "*-*-*-* Cepr01040000Ilustration134Business.getIllustrationResult" );
        IllustrationResultVO result = new IllustrationResultVO();
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        Cepr01030101Form cepr01030101Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030101Form();
        Cepr01030104Form cepr01030104Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030104Form();

        ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        List<TopupDrawVO> topupDrawVOList = cepr01030104Form.getTopupDrawVOList();
        int topupDrawVOListSize = topupDrawVOList.size();
        TopupDrawVO topupDrawVO;
        String premiumTotal;
        String topup;
        String draw;

        int li_ke = 0, li_bagi = 1000, li_hal = 3;
        double[] ldec_bak ;
        double[] flag_syarat_bonus_premi = new double[3 + 1];     
        double[] ekstraAlokasi = new double[3 + 1];     
        
        double[] ldec_hasil_ppokok = new double[3 + 1];
        double[] ldec_hasil_ptu = new double[3 + 1];
        double[] ldec_hasil_wdraw = new double[3 + 1];
        //double[] ldec_fph = {CommonConst.DUMMY_ZERO, 0, 0, 0.9, 0.85, 0.8};
        double[] ldec_fph =  {0, 0, 0, 0.7, 0.3, 0.2};
        double ldec_bawal = 100000;
        double[] ldec_man_non = new double[2 + 1];
        double ldec_bak_tu;
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
        	ldec_bak_tu = 0.05;
        }else{
        	ldec_bak_tu = 0;
        }        
        double ldec_bak_tut = 0.05;
        double ldec_akuisisi;
        double ldec_premi_invest;
        double[][] ldec_hasil_invest = new double[5 + 1][3 + 1];
        double[][] ldec_hasil_invest_batal = new double[5 + 1][3 + 1];
        double[] ldec_tarik = { CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0 };
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6 
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10 
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X12){    		
    		 ldec_tarik = new double[] { CommonConst.DUMMY_ZERO, 0.04, 0.04, 0.03, 0.02, 0.01 };
        }else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
        	 ldec_tarik = new double[] { CommonConst.DUMMY_ZERO, 0.75, 0.65, 0.55, 0.45, 0.35, 0.25, 0.15 };               
        }
        	
        double ldec_wdraw;
        double[] ldec_premi_bulan = new double[12 + 1];
        double ldec_topup;
        double ldec_bass;
        double[][] ldec_bunga = new double[5 + 1][3 + 1];
        double[] ldec_bunga_avg = new double[3 + 1]; //= {0.06, 0.1, 0.08, 0.18, 0.12, 0.25} //{0.09, 0.06, 0.11, 0.15, 0.165, 0.25}  //fixed:0.09, 0.11, 0.15 (0.165); dynamic:0.08, 0.11, 0.18 (0.195)
        double ldec_fee = 0.020075;
        double ldec_coi = 0, ldec_mfc, ldec_sc, ldec_cost, ldec_ppokok, ldec_ptu, ldec_aph, ldec_ff, ldec_man_celaka, ldec_temp = 0; //, ldec_man[10+1] = {1, 1, 1, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7}
        boolean[] lb_minus = { CommonConst.DUMMY_FALSE, false, false, false };
        boolean lb_rider = false;
        String ls_sy = "", ls_temp; //ls_dpp = ' dari Premi Pokok', ls_sy = ''
        S_biaya lstr;
        double ldec_premi_ppokok_setahun = 0;
        double[] ldec_premi_ppokok_bulan = new double[12 + 1];
        String bonus = "0.00";
//n_riders ln_riders
        double ldec_manfaat, ldec_premi_setahun = 0, ldec_coi24 = 0;
////monthly fix cost
        if( cepr01030102Form.getRightPartOfBusinessCd() == 13 ){
        	 ldec_mfc = 0;
        }else{
        	 ldec_mfc = 27500;
        }        	
        //ldec_mfc = 15000;
        
        if(istr_prop.kurs_id.equals("02")){ 
        	ldec_mfc = 2.75;
        	//ldec_mfc = 2;
        	li_bagi = 1;
        }
        for( int i = 1; i <= 12; i++ )
        {
            ldec_premi_bulan[ i ] = 0;
            ldec_premi_ppokok_bulan[ i ] = 0;
            if( i == 1 ){
            	ldec_premi_bulan[ i ] = istr_prop.premi;
            	ldec_premi_ppokok_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
            }
            if( Hardcode.PAY_MODE_CD_TRIWULANAN == cepr01030102Form.getPaymentModeCd() )
            {
                if( i == 4 || i == 7 || i == 10 ) {
                	ldec_premi_bulan[ i ] = istr_prop.premi;
                	ldec_premi_ppokok_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
                }
            }
            else if( Hardcode.PAY_MODE_CD_SEMESTERAN == cepr01030102Form.getPaymentModeCd() )
            {
                if( i == 7 ) {
                	ldec_premi_bulan[ i ] = istr_prop.premi;
                	ldec_premi_ppokok_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
                }
            }
            else if( Hardcode.PAY_MODE_CD_BULANAN == cepr01030102Form.getPaymentModeCd() )
            {
                ldec_premi_bulan[ i ] = istr_prop.premi;
                ldec_premi_ppokok_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
            }
            ldec_premi_setahun += ldec_premi_bulan[ i ];
            ldec_premi_ppokok_setahun += ldec_premi_ppokok_bulan[ i ];
        }
        //
        for(int i = 1 ; i <= 3 ; i++){
    		ldec_hasil_invest[1][i] = 0;
    		ldec_hasil_invest_batal[1][i] = 0;
    		ldec_hasil_ppokok[i] = 0;
    		ldec_hasil_ptu[i] = 0;
    		ldec_hasil_wdraw[i] = 0;
    		//ldec_pre_pp[i] = 0
    		//ldec_pre_tu[i] = 0
        }
        //
        ldec_manfaat = istr_prop.up;
        //    Biaya Akuisisi & asumsi penarikan (1 tarik, 1-nya tdk tarik)
        lstr = PbFunction.wf_get_biaya_84( istr_prop, command );
        ldec_bak = lstr.bak;
    	ldec_bunga = lstr.bunga;

    	int a, b, c;
        if( "01".equals( istr_prop.kurs_id ) )
        {
            a = 1;
            b = 8;
            c=1;
        }
        else
        {
            a = 9;
            b = 11;/**USD Fund BSIM Products**/
            c=9;
        }
        
        if( cepr01030102Form.getRightPartOfBusinessCd() > 5 && cepr01030102Form.getRightPartOfBusinessCd() != 10 ){        	
        	if( cepr01030102Form.getRightPartOfBusinessCd() == 6  || cepr01030102Form.getRightPartOfBusinessCd() == 11 ){
        		  if( "01".equals( istr_prop.kurs_id ) )
                  {
                      a = 1;
                      b = 5;
                      c = 1;
                  }
                  else
                  {
                      a = 6;
                      b = 7;
                      c = 6;
                  }
        	} /*else{        		
        		  if( "01".equals( istr_prop.kurs_id ) )
                  {
                      a = 1;
                      b = 3;
                      c = 1;
                  }
                  else
                  {
                      a = 4;
                      b = 5;
                      c = 4;
                  }
        	}        	*/	
        } 
    	
        for( int i = 1; i <= 3; i++ )
        {
            ldec_bunga_avg[ i ] = 0;
            for( int j = a; j <= b; j++ )
            {
                ldec_bunga_avg[ i ] += ldec_bunga[ j ][ i ] * ( istr_prop.fund[ j ] / 100 );
                c++;
                if( "01".equals( istr_prop.kurs_id ))
                {
                	/*
                	if( cepr01030102Form.getRightPartOfBusinessCd() == 5 ){
	                	if(c>3 && c<7){
	                		c=c-3;
	                	}
	                	if(j==6){
	                		c=4;
	                	}                	
                	}*/
                	//else
                	if( cepr01030102Form.getRightPartOfBusinessCd() == 6 || cepr01030102Form.getRightPartOfBusinessCd() == 7 || cepr01030102Form.getRightPartOfBusinessCd() == 8 || cepr01030102Form.getRightPartOfBusinessCd() == 11 || cepr01030102Form.getRightPartOfBusinessCd() == 12){
                    	if(c>4){
                    	   c=c-4;
                    }}
                } if( "02".equals( istr_prop.kurs_id ))
                {
                	/*if( cepr01030102Form.getRightPartOfBusinessCd() == 5 ){
                	if(c>6){
                		c=c-2;
                	}}*/
                }
                
            }
        }
        
        boolean cekAdaTarik = false;
        double ldec_tarik_cek = 0;
        for( int i = 1; i <= istr_prop.ins_per; i++ )
        {
        	
        	 if( i <= ArrUtil.upperBound( lstr.tarik ) ) ldec_tarik_cek = lstr.tarik[ i ];
        	
        	 if( ldec_tarik_cek > 0){
        		 cekAdaTarik = true;
        		 break;
        	 }
        }

        double[] np = new double[4];
        double[] celaka = new double[4];
        double[] npbatal = new double[4];
        
        int j;
        ldec_fee = 0;
        for( int i = 1; i <= istr_prop.ins_per; i++ )
        {
//        	surrender charge
        	ldec_sc = 0;
        	ldec_wdraw = 0;
        	ldec_topup = 0;
        	
        	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6 
        		|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8 
        		|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10
        		|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X12 ){
        		ldec_coi = of_get_coi_basic_190(i);        		
        	}else if ( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ) {
        		ldec_coi = of_get_coi_190(i);
        	}else{
        		ldec_coi = of_get_coi_basic(i);
        	}

        	if( istr_prop.cb_id == 0 ){
        		for(int k = 1; k <= ArrUtil.upperBound(istr_prop.rider_baru) ; k ++ ){
        			if( istr_prop.rider_baru[k] > 0 ){
        				if(i == 27 || i==28 || i==29 ){
                    		String debug = "true";
                    	}
        				ldec_temp = of_get_coi_134(k, i);
        				ldec_coi += ldec_temp;
        			}
        		}
        	}
        	if(i <= ArrUtil.upperBound(lstr.topup))	ldec_topup = lstr.topup[i];
        	
        	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
        		if(i <= 7) ldec_sc = ldec_tarik[i];
        	}else{
        		if(i <= 5) ldec_sc = ldec_tarik[i];
        	}        	
        	//
        	
        	ldec_cost = (ldec_coi + ldec_mfc);
        	
        	for(int k = 1 ; k <= 3; k++){
        		if(i <= ArrUtil.upperBound(lstr.tarik)) ldec_wdraw = lstr.tarik[i];
        		for(int li_bulan = 1 ; li_bulan <= 12 ; li_bulan++){
        			ldec_cost = (ldec_coi + ldec_mfc);
        			ldec_premi_invest = 0;
        			ldec_ppokok = 0;
        			ldec_ptu = 0;
        			ldec_ff = 0;
        			ldec_aph = 0;
        			if(i <= istr_prop.cuti_premi){
        				ldec_premi_invest = ldec_premi_bulan[li_bulan] * (istr_prop.pct_premi / 100);
        				ldec_premi_invest += ((ldec_premi_bulan[li_bulan] * (100 - istr_prop.pct_premi) / 100));
        				ldec_ppokok = ldec_premi_bulan[li_bulan] * (istr_prop.pct_premi / 100);
        				ldec_ptu = (ldec_premi_bulan[li_bulan] * (100 - istr_prop.pct_premi) / 100) * (1-ldec_bak_tu);        			
        			}
        			
        			if(li_bulan == 1) ldec_ptu += ldec_topup;
        		// Em@il Vito@18/05/2016=> Rumusan SIMAS PRIMELINK & SMiLe AssetPro(Agency)(134-X5/X6)
        		if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6 
        				|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8
        				|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10
        				|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X12){
	       			if(i == 1 && li_bulan == 1){
	       				ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok - ldec_cost ) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12))), 2) ;
	       			}else{
	       				ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_hasil_ppokok[k] - ldec_cost ) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12))), 2) ;
	       			}
	       			ldec_hasil_ptu[k] = (ldec_ptu + ldec_hasil_ptu[k]) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12)));
	       			
        		}else{
        			
        			if(!cekAdaTarik){
        				flag_syarat_bonus_premi[k] = flag_syarat_bonus_premi( i , cepr01030102Form , k , ldec_premi_ppokok_setahun);
                	}
        			
        			//ekstraAlokasi[k] = ekstraAlokasi2(i, k, ldec_premi_ppokok_setahun,ldec_bunga_avg[k]);
        			ekstraAlokasi[k] = ekstraAlokasi3(i, k, ldec_premi_ppokok_setahun,ldec_bunga_avg);
        			//ekstraAlokasi[k] = ekstraAlokasi(i, k, ldec_premi_ppokok_setahun );
        			//ekstraAlokasi[k]  = 0;
        			if( li_bulan == 12){
        				//ekstraAlokasi[k]  = 0;
        				//ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2)  + flag_syarat_bonus_premi[k] + ekstraAlokasi[k];
        			/*	ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2) + ekstraAlokasi[k]+flag_syarat_bonus_premi[k];
        			*/	
        				//ekstraAlokasi[k]  = 0;
        				if(i==1 && k==1){
               			 System.out.println("tahun ke i"+i+" : extra alokasi:"+ekstraAlokasi[k]);
               		 	}
        				//ekstra alokasi
         				//flag_syarat_bonus_premi[k] = 0; 
        				ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2) + ekstraAlokasi[k]+flag_syarat_bonus_premi[k];
        			}else if( li_bulan == 1 ){        	       				
        				ekstraAlokasi[k]  = 0;
        				//ekstra alokasi
         				flag_syarat_bonus_premi[k] = 0; 
        				ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2) + ekstraAlokasi[k]+flag_syarat_bonus_premi[k];
        				
        			}else{
        				  //ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2);
        				ekstraAlokasi[k]  = 0;

         				flag_syarat_bonus_premi[k] = 0;
        				//ekstra alokasi
        				ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2) + ekstraAlokasi[k]+flag_syarat_bonus_premi[k];
        			}
        	        	
        			 if(i==1 && k==1)
        						System.out.println("tahun i="+i+" month="+li_bulan+" hasil pokok [ldec_hasil_ppokok]="+ldec_hasil_ppokok[k]);
        			
        			ldec_hasil_ptu[k] = (ldec_ptu + ldec_hasil_ptu[k]) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12)));
        			if( i == 1 && istr_prop.cb_id == 0 ){ 
        				if( li_bulan == 1 ) ldec_cost = 0;
        				if( li_bulan == 2 ) ldec_cost *= 2;
        			}
        			if(i == istr_prop.ins_per && li_bulan == 12) ldec_cost = 0;
        			if( istr_prop.cb_id == 0 ) {
        				if( i <= 2 )ldec_ff = (ldec_hasil_ppokok[k] * 0.03 / 12);
        			}else{        				
        				
        				if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
        					if( i <= 7 ) ldec_ff = (ldec_hasil_ptu[k] * 0 / 12);
            				if( i <= 7 ) ldec_ff += (ldec_hasil_ppokok[k] * 0.065 / 12);         					
        				}else{
        					if( i <= 2 ) ldec_ff = (ldec_hasil_ptu[k] * 0.0125 / 12);
            				if( i <= istr_prop.pay_per ) ldec_ff += (ldec_hasil_ppokok[k] * 0.025 / 12);        					
        				}
        						
        			}
        	    	//premium holiday
        	    	if(i <= 5 && istr_prop.cuti_premi <= 5){ 
        	    		if(i > istr_prop.cuti_premi && istr_prop.cb_id != 0) ldec_aph = ldec_fph[i] / (1 - ldec_fph[i]) * (ldec_cost + ldec_ff);
        	    	}
        	    
        	    	
        	    	ldec_temp = ldec_hasil_ptu[k] - (ldec_cost + ldec_ff + ldec_aph);
        	   	 if(i==1 && k==1){
        			 System.out.println("tahun ke i"+i+" : pokok before:"+ldec_hasil_ppokok[k]+" ldec_temp:+"+ ldec_temp+" biaya_coi"+ldec_cost+" ldec_ff="+ldec_ff+" ldec_aph");
        		 }
        	 	
        	    	if(ldec_temp < 0){
        	    		ldec_hasil_ppokok[k] += ldec_temp;
        	    		ldec_hasil_ptu[k] = 0;
        	    	}else{
        	    		ldec_hasil_ptu[k] = ldec_temp;
        	    	}
        	    	if(ldec_wdraw > 0 && li_bulan == 12 ){
        	    		if(i <= 2){ 
        	    			ldec_wdraw = 0;
        	    		}else{
        	    			if(i <= 5) ldec_wdraw = Math.min(ldec_wdraw, ldec_hasil_ptu[k]);
        	    				ldec_temp = ldec_hasil_ptu[k] - ldec_wdraw;
        	    			if(ldec_temp < 0){
        	    				ldec_hasil_ppokok[k] += ldec_temp;
        	    				ldec_hasil_ptu[k] = 0;
        	    			}else{
        	    				ldec_hasil_ptu[k] = ldec_temp;
        	    			}
        	    		}
        	    		
        	    	}
        		}// end (134-X1X4)    	
        	    	
	        	    if(k == 1)
	            	{
	            		ldec_hasil_invest[1][k] = ldec_hasil_ppokok[k] + ldec_hasil_ptu[k];	            		
	            	}
        		}

       		
       	
        		ldec_hasil_wdraw[k] += ldec_wdraw;
        		ldec_hasil_invest[1][k] = ldec_hasil_ppokok[k] + ldec_hasil_ptu[k];
        		ldec_hasil_invest[ 1 ][ k ] -= ( ldec_hasil_wdraw[k] * ( 1 + ldec_sc ) );
        		 if(i==1 && k==1){
        			 System.out.println("tahun ke i"+i+" hasil investasi = "+ldec_hasil_invest[ 1 ][ k ] +"detail : pokok:"+ldec_hasil_ppokok[k]+" topup:+"+ ldec_hasil_ptu[k]);
        		 }
        		ldec_hasil_invest_batal[1][k] = ldec_hasil_invest[1][k] * ldec_sc;
        		
        		if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6
        			|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8
        			|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10
        			|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11|| cepr01030102Form.getRightPartOfBusinessCd() >= Cepr01040208Mapper.X12 ){
        				if( ldec_hasil_invest[ 1 ][ k ] <= 0 &&  ( i <= 10 ) ){
        					lb_minus[k] = true;	               
        				}
        		}else{
        			if( ldec_hasil_invest[1][k] <= 0 ) lb_minus[k] = true;
        		}
        	}
        

            //j = cepr01030101Form.getInsuredAge() + i;
        	j = istr_prop.umur_tt + i;
            //if( i <= 10 || j==54 || j==55 ||  j >= 60 )
           	if( i <= 23 || j == 55 || j == 65 || j == 75 || j == 80 || j == 100 )
            {
                for( int k = 1; k <= 3; k++ )
                {
                    np[ k ] = FormatNumber.round( ldec_hasil_invest[ 1 ][ k ] / li_bagi, 0 );
                    celaka[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] + ldec_manfaat ) / li_bagi, 0 );
                    
                    if (cepr01030102Form.getRightPartOfBusinessCd() >= Cepr01040208Mapper.X13){
//                        // Revisi NISA+NUNG untuk cb.reguler yg dikalikan surrender charge nilai premi pokoknya saja           
                        npbatal[ k ] = FormatNumber.round( ( (ldec_hasil_ppokok[k] * (1 - ldec_sc) ) + ldec_hasil_ptu[k] )/ li_bagi, 0 );
                    }else{
//                    	ldec_hasil_invest_batal[1][k] = ldec_hasil_invest[1][k] * ldec_sc;
//                    	npbatal[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] - ldec_hasil_invest_batal[ 1 ][ k ] ) / li_bagi, 0 );
                    	 npbatal[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] * (1 - ldec_sc)  ) / li_bagi, 0 );
                    }
                }

                if( i <= istr_prop.cuti_premi )
                {
                    premiumTotal = editorUtil.convertToStringWithoutCent( ldec_premi_setahun / li_bagi );
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
                    if( "0".equals( topup ) ) topup = "0.00";
                    if( "0".equals( draw ) ) draw = "0.00";
                }
                else
                {
                    topup = "0.00";
                    draw = "0.00";
                }
                
                if(!cekAdaTarik){                	
	                double bonus2;
	                if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd()) )
	                {
	                	 bonus2 = flag_syarat_bonus_premi2( i, cepr01030102Form , ldec_premi_ppokok_setahun);
	                }else{
	                	 bonus2 = flag_syarat_bonus_premi2( i, cepr01030102Form , ldec_premi_ppokok_setahun)/1000;
	                }
	                if( bonus2 == 0 ) {
	                     	//bonus = null;
	                     	//bonus = " - ";
	                     	 bonus = "0.00";
	                }else{
	                     	bonus = editorUtil.convertToStringWithoutCentAndNillIfNegative(bonus2);
	                }                                            
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

                map = new HashMap<String, Object>();
                map.put( "yearNo", editorUtil.convertToString( i ) );
                map.put( "insuredAge", editorUtil.convertToString( cepr01030101Form.getInsuredAge() + i ) );
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
                map.put( "benefitHi", benefitHi);
                map.put( "bonus",  bonus );
                mapList.add( map );

            }
        }

        result.setValidityMsg( lb_minus[ 1 ] ? "Maaf, Proposal yang Anda buat Tidak Layak Jual." : "" );
        result.setIllustrationList( mapList );
        return result;

    }
    
    
    public List getRiderPremiumList( Cepr01030102Form cepr01030102Form, int usia) throws IOException
    {
    	double ldec_coi;
    	
        //String[][] riderTambahan = new String[3][2];
        //BigDecimal totalRider = new BigDecimal(0);
        
        //riderTambahan[0][0] = "Biaya Asuransi Tambahan Perbulan";
        //riderTambahan[0][1] = "";

        //N_riders ln_riders = new N_riders();
        //int j = 1;
    	List biayaRiderList = new ArrayList();
        List < Mst_proposal > mstProposal = new ArrayList<Mst_proposal>();
    	for(int m = 0 ; m < cepr01030102Form.getTermOfPayment(); m++){
    		Map<String, Object> riderParams = new HashMap<String, Object>();
    		if(m < istr_prop.cuti_premi){
				riderParams.put("yearNo", (m+1) + "");
				riderParams.put("insuredAge", usia + "");
    			for( int i = 1; i < ArrUtil.upperBound( istr_prop.rider_baru ); i++ ){
    				if( istr_prop.rider_baru[ i ] > 0 )
    				{
    					ldec_coi = of_get_coi_134_cost( i, m+1, mstProposal );
    					riderParams.put("rider"+(i-1), editorUtil.convertToRoundedNoDigit(new BigDecimal(ldec_coi * 10)));
    				}else if( istr_prop.rider_baru[ i ] == 0 ){
    					riderParams.put("rider"+(i-1), "");
    				}
    			}
    			biayaRiderList.add(riderParams);
    			usia = usia +1;
    		}else if(m >= istr_prop.cuti_premi){
				riderParams.put("yearNo", (m+1) + "");
				riderParams.put("insuredAge", usia + "");
				for( int i = 1; i < ArrUtil.upperBound( istr_prop.rider_baru ); i++ ){
					riderParams.put("rider"+(i-1), "");
				}
				biayaRiderList.add(riderParams);
				usia = usia +1;
    		}
    	}
        	
        //riderTambahan[0][1] = totalRider.toString();

        return biayaRiderList;
    }
    
    
    public List<Map<String, Object>> getMonthlyPremiumList( Cepr01030102Form cepr01030102Form ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getMonthlyPremiumList" );
        double ldec_coi;
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6 
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8 
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11|| cepr01030102Form.getRightPartOfBusinessCd() >= Cepr01040208Mapper.X12){
    		ldec_coi = of_get_coi_basic_190(1);
    	}else{
    		ldec_coi = of_get_coi_basic(1);
    	}
        
        params = new HashMap<String, Object>();
        params.put( "assuranceBenefit", "Dasar" );        
        if(cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10){
        	params.put( "productName", "SIMAS PRIME LINK" );        	 
        }else{
        	params.put( "productName", istr_prop.nama_plan[ 1 ] );
        }        
        params.put( "monthlyPremium", editorUtil.convertToMoney( ldec_coi, cepr01030102Form ) );                
        result.add( params );     
        
        if(cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10){
        	 params = new HashMap<String, Object>();
        	 params.put( "assuranceBenefit", "" );
        	 params.put( "productName", "(RIDER SAVE)" );
        	 params.put( "monthlyPremium", "" );
        	 result.add( params );
        }
        
        String assuranceBenefit;
        boolean firstBenefitFlag = true;
        N_riders ln_riders = new N_riders();
        List< Mst_proposal > mstProposal = new ArrayList<Mst_proposal>();
        for( int i = 1; i <= ArrUtil.upperBound( istr_prop.rider_baru ); i++ )
        {
            if( istr_prop.rider_baru[ i ] > 0 )
            {
                if( firstBenefitFlag )
                {
                    assuranceBenefit = "Tambahan";
                    firstBenefitFlag = false;
                }
                else
                {
                    assuranceBenefit = "";
                }

                ldec_coi = of_get_coi_rider_cost( i, 1, mstProposal );

                params = new HashMap<String, Object>();
                params.put( "assuranceBenefit", assuranceBenefit );
                params.put( "productName", ln_riders.of_nama( i, cepr01030102Form, cepr01030103Form ) );// nama label di halaman pertama
                params.put( "monthlyPremium", editorUtil.convertToMoney( ldec_coi, cepr01030102Form ) );
                result.add( params );
            }
        }
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
      
    public List<Map<String, Object>> getCommonHeaderRincianRowList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getCommonHeaderRincianRowList" );

        List sekaligusRightPartCdList = ArrUtil.toListFromArray( sekaligusRightPartCdArr );
        List berkalaRightPartCdList = ArrUtil.toListFromArray( berkalaRightPartCdArr );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01030104Form cepr01030104Form = cepr01030000HoldingForm.getCepr01030104Form();
        
        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        // Fill header content from CommonHeaderDataSource
        String word_premi = "";
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 200 || cepr01030102Form.getLeftPartOfBusinessCd() == 202 || cepr01030102Form.getLeftPartOfBusinessCd() == 153 || cepr01030102Form.getLeftPartOfBusinessCd() == 160)// produk produk syariah
        {
        	result.addAll( source.getPolicyHolderNameMap() );
            result.addAll( source.getPolicyHolderAgeMap() );
            result.addAll( source.getInsuredNameSyariahMap() );
            result.addAll( source.getInsuredAgeSyariahMap() );
            result.addAll( source.getGenderInsuredSyariahMap() );
            result.addAll( source.getTermOfContractWithLimitSyariahMap( insuredAgeLimit ) );
            word_premi = "Kontribusi/Premi";
        }else{
        	result.addAll( source.getPolicyHolderNameMap() );
            result.addAll( source.getPolicyHolderAgeMap() );
             
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
            	 result.addAll( source.getGenderInsuredMap() );
             }else
             {
                 result.addAll( source.getInsuredNameMap() );
                 result.addAll( source.getInsuredAgeMap() );
                 result.addAll( source.getGenderInsuredMap() );                 
            }             
             result.addAll( source.getTermOfContractWithLimitMap( insuredAgeLimit ) );
             word_premi = "Premi";
        }     

        Map<String, Object> params;
        if( sekaligusRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
            	 params = new HashMap<String, Object>();
                 params.put( "label", word_premi + " Pokok Sekaligus" );
                 params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) +" (100%)" );
                 result.add( params );
                 
                 params = new HashMap<String, Object>();
                 params.put( "label", "Masa Pembayaran "+ word_premi);
                 params.put( "content", "Sekaligus" );
                 result.add( params );   
            
           if( cepr01030104Form.getTopupDrawVOList().get(0).getYearFlag() != null && cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().compareTo(new BigDecimal(0)) >= 1)
  	       {
        	   params = new HashMap<String, Object>();
        	   params.put( "label", word_premi + " Top Up Single" );
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
        	if(  cepr01030102Form.getPremiumCombinationCd().equals( new BigDecimal("100") ) ){
                params = new HashMap<String, Object>();
                params.put( "label", word_premi + " Pokok "+ label );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getBasePremium() ) );
                result.add( params );
        	} else{
  	          params = new HashMap<String, Object>();
  	          params.put( "label", word_premi + " Pokok "+ label );
  	          params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getBasePremium() ) + " (" + cepr01030102Form.getPremiumCombinationCd().toString() + "%)" );
  	          result.add( params );
  	
  	          params = new HashMap<String, Object>();
  	          params.put( "label", word_premi + " Top Up Berkala "+ label );
  	          params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getTopUpPremium() ) + " (" + ( new BigDecimal( "100" ).subtract( cepr01030102Form.getPremiumCombinationCd() ) ).toString() + "%)" );
  	          result.add( params );
  		
                params = new HashMap<String, Object>();
                params.put( "label", "" );
                params.put( "content", "------------------------" );
                result.add( params );
                
                params = new HashMap<String, Object>();
                params.put( "label", "Total " + word_premi + " " + label );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) );
                result.add( params );
        	}
        	        	        	
  	       if( cepr01030104Form.getTopupDrawVOList().get(0).getYearFlag() != null && cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().compareTo(new BigDecimal(0)) >= 1)
  	       {
  	        		 params = new HashMap<String, Object>();
  	        		 params.put( "label", word_premi + " Top Up Single" );
  	                 params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount() ) );
  	                 result.add( params ); 
  	       }
        	
            params = new HashMap<String, Object>();
            params.put( "label", "Masa Pembayaran " + word_premi );
            params.put( "content", editorUtil.convertToString( cepr01030102Form.getPremiumFurloughYear() ) +" Tahun" );
            result.add( params );
        }
        
        if(cepr01030102Form.getLeftPartOfBusinessCd() == 153 || cepr01030102Form.getLeftPartOfBusinessCd() == 200)// produk produk syariah
        {
        	 result.addAll( source.getTotalSumInsuredSyariahMap() );
             result.addAll( source.getInsuredMedicalCheckUpSyariahMap( ( String ) of_cek_medis( command ).get( "medis_pp" ) ) );
             result.addAll( source.getInsuredMedicalCheckUpSyariahMap( ( String ) of_cek_medis( command ).get( "medis_tt" ) ) );            	
        }else{
        	 result.addAll( source.getTotalSumInsuredMap() );
             result.addAll( source.getPolicyHolderMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_pp" ) ) );
             result.addAll( source.getInsuredMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_tt" ) ) );        	
        }
        
        return result;
    }
    
    
    
    
    public List<Map<String, Object>> getCommonBiayaRincianRowList(BigDecimal biayaAsuransiPokok) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040211Business.getCommonBiayaRincianRowList" );
        List sekaligusRightPartCdList = ArrUtil.toListFromArray( sekaligusRightPartCdArr );
        List berkalaRightPartCdList = ArrUtil.toListFromArray( berkalaRightPartCdArr ); 
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        
        Map<String, Object> params = new HashMap<String, Object>();
        
        if( cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X5 && cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X6 
        	&& cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X7 && cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X8 
        	&& cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X9 && cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X10
        	&& cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X11 && cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X12){
        	
	        if( sekaligusRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
	        {
	        	params.put( "label", "-	Biaya Akuisisi sebesar 5% dari Premi Sekaligus" );
	 	        params.put( "content", editorUtil.convertToString( "" ) );
	 	        params.put( "separator", "" );
	 	        result.add( params );
	 	        
	 	        params = new HashMap<String, Object>();
		        params.put( "label", "- Biaya Top Up sebesar 5% dari Premi Top Up Tunggal" );
		        params.put( "content", editorUtil.convertToString( "" ) );
		        params.put( "separator", "" );
		        result.add( params );        	
	        }
	        else if( berkalaRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
	        {
		        // Fill header content 
		        params.put( "label", "-	Biaya Akuisisi Kontribusi Pokok Regular" );
		        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
		        	params.put( "content", editorUtil.convertToString( "0 %" ) );
		        }else{
		        	  params.put( "content", editorUtil.convertToString( "" ) );
		        }
		        params.put( "separator", "" );
		        
		        result.add( params );
		        
		        if( cepr01030102Form.getLeftPartOfBusinessCd() == 200 || cepr01030102Form.getLeftPartOfBusinessCd() == 190){ //SMiLe Link 99/BRIDGE Syariah
			        params = new HashMap<String, Object>();
			        params.put( "label", "    - Tahun ke-1" );	       
			        params.put( "content", editorUtil.convertToString( "100%" ) );
			        params.put( "separator", ":" );
			        result.add( params );
			        
			        params = new HashMap<String, Object>();
			        params.put( "label", "    - Tahun ke-2" );
			        params.put( "content", editorUtil.convertToString( "60%" ) );
			        params.put( "separator", ":" );
			        result.add( params );
			       
			        params = new HashMap<String, Object>();
			        params.put( "label", "    - Tahun ke-3-5" );
			        params.put( "content", editorUtil.convertToString( "15%" ) );
			        params.put( "separator", ":" );
			        result.add( params );
			        
			        params = new HashMap<String, Object>();
			        params.put( "label", "    - Tahun ke-6 dan seterusnya" );
			        params.put( "content", editorUtil.convertToString( "0%" ) );
			        params.put( "separator", ":" );
			        result.add( params );
		        }else if( cepr01030102Form.getLeftPartOfBusinessCd() == 120 || cepr01030102Form.getLeftPartOfBusinessCd() == 202){ //SIMPOL/Excellent Link 		         
			         params = new HashMap<String, Object>();
			         params.put( "label", "    - Tahun ke-1" );
			         params.put( "content", editorUtil.convertToString( "80%" ) );
			         params.put( "separator", ":" );
			         result.add( params );
			         
			         params = new HashMap<String, Object>();
			         params.put( "label", "    - Tahun ke-2" );
			         params.put( "content", editorUtil.convertToString( "15%" ) );
			         params.put( "separator", ":" );
			         result.add( params );
			        
			         params = new HashMap<String, Object>();
			         params.put( "label", "    - Tahun ke-3 dan seterusnya" );
			         params.put( "content", editorUtil.convertToString( "0%" ) );
			         params.put( "separator", ":" );
			         result.add( params );
		        }else if( cepr01030102Form.getLeftPartOfBusinessCd() == 116 || cepr01030102Form.getLeftPartOfBusinessCd() == 153){ //SMiLe Link 88 /Syariah      
		        	 params = new HashMap<String, Object>();
				     params.put( "label", "    - Tahun ke-1" );	       
				     params.put( "content", editorUtil.convertToString( "70%" ) );
				     params.put( "separator", ":" );
				     result.add( params );
				        
				    params = new HashMap<String, Object>();
				    params.put( "label", "    - Tahun ke-2" );
				    params.put( "content", editorUtil.convertToString( "20%" ) );
				    params.put( "separator", ":" );
				    result.add( params );
				       
				    params = new HashMap<String, Object>();
				    params.put( "label", "    - Tahun ke-3-5" );
				    params.put( "content", editorUtil.convertToString( "5%" ) );
				    params.put( "separator", ":" );
				    result.add( params );
				        
				    params = new HashMap<String, Object>();
				    params.put( "label", "    - Tahun ke-6 dan seterusnya" );
				    params.put( "content", editorUtil.convertToString( "0%" ) );
				    params.put( "separator", ":" );
				    result.add( params );
		        }else if( cepr01030102Form.getLeftPartOfBusinessCd() == 159 || cepr01030102Form.getLeftPartOfBusinessCd() == 160){ //SMiLe Link /Syariah  
		        	params = new HashMap<String, Object>();
		  	        params.put( "label", "    - Tahun ke-1" );  	      
		  	        params.put( "content", editorUtil.convertToString( "90%" ) );
		  	        params.put( "separator", ":" );
		  	        result.add( params );
		  	        
		  	        params = new HashMap<String, Object>();
		  	        params.put( "label", "    - Tahun ke-2" );
		  	        params.put( "content", editorUtil.convertToString( "45%" ) );
		  	        params.put( "separator", ":" );
		  	        result.add( params );
		  	       
		  	        params = new HashMap<String, Object>();
				    params.put( "label", "    - Tahun ke-3-5" );
				    params.put( "content", editorUtil.convertToString( "15%" ) );
				    params.put( "separator", ":" );
				    result.add( params );
				        
				    params = new HashMap<String, Object>();
				    params.put( "label", "    - Tahun ke-6 dan seterusnya" );
				    params.put( "content", editorUtil.convertToString( "0%" ) );
				    params.put( "separator", ":" );
				    result.add( params ); 	
		        }        
		        params = new HashMap<String, Object>();
		        params.put( "label", "- Biaya Akuisisi Kontribusi Top-up Regular dan Kontribusi Top-up Single adalah" );
		        params.put( "content", editorUtil.convertToString( "5 %" ) );
		        params.put( "separator", "" );
		        result.add( params );
	        }
        }
        
        params = new HashMap<String, Object>();
        params.put( "label", "- Biaya Administrasi" );        
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8 
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X12){
        	params.put( "content", editorUtil.convertToString( "Rp.27,500.00/US$ 2.75 per bulan*" ) );        	
        }else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
        	params.put( "content", editorUtil.convertToString( "Tidak dikenakan biaya administrasi per bulan" ) ); 
        }else{
        	// if(cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_IDR_CD )){
             	params.put( "content", editorUtil.convertToString( "Rp.15,000.00/US$ 2 per bulan*" ) );
            // }
           //  else if(cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD )){
           // 	params.put( "content", editorUtil.convertToString( "US$ 2 per bulan*" ) );
           //  } 
        }      
        params.put( "separator", ":" );
        result.add( params );
        
        params = new HashMap<String, Object>();
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 200 || cepr01030102Form.getLeftPartOfBusinessCd() == 202 || cepr01030102Form.getLeftPartOfBusinessCd() == 153 || cepr01030102Form.getLeftPartOfBusinessCd() == 160){
        	params.put( "label", "- Biaya Asuransi Syariah" );      
        }else{
        	params.put( "label", "- Biaya Asuransi " );   
        }
        if(cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13){
        	params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( biayaAsuransiPokok )+ " per bulan*");
        }  else{
        	params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( biayaAsuransiPokok )+ " per bulan**");
        }
        params.put( "separator", ":" );
        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put( "label", "- Biaya Pengelolaan Investasi" );
        if(cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_IDR_CD )){
        	 if( cepr01030102Form.getCashFundIdr() != null ){ 
        		 if( cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) > 0 && cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "100" ) ) == 0  ){
        			 params.put( "content", editorUtil.convertToString( "1% per tahun dari dana investasi untuk jenis Cash Fund" ));
        		 }else if( cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) > 0 && cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "100" ) ) < 0 ){
        			 params.put( "content", editorUtil.convertToString( "2% per tahun dari Dana Investasi (Sesuai dengan Jenis Fund yang dipilih) dan 1% per tahun dari dana investasi untuk jenis Cash Fund" ));
        		 }else if( cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) == 0  ){
        			 params.put( "content", editorUtil.convertToString( "2% per tahun dari Dana Investasi (Sesuai dengan Jenis Fund yang dipilih)" ));
        		 }
        	}else{
        		params.put( "content", editorUtil.convertToString( "2% per tahun dari Dana Investasi (Sesuai dengan Jenis Fund yang dipilih)" ));
        	}        	
        }
        else if(cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD )){        	
        	params.put( "content", editorUtil.convertToString( "1.5% per tahun dari Dana Investasi (Sesuai dengan Jenis Fund yang dipilih)" ));
        }       
        params.put( "separator", ":" );        
        result.add( params );
        if( cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X5 && cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X6 
        	&& cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X7 && cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X8 
        	&& cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X9 && cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X10 
        	&& cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X11 && cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X12
        	&& cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X13){
	        params = new HashMap<String, Object>();
	        params.put( "label", "- Biaya Penarikan" );
	        params.put( "content", editorUtil.convertToString( "1.25% untuk penarikan yang ke-3 dan seterusnya dalam setahun atau Sebelum 3 bulan polis berjalan" ));       
	        params.put( "separator", ":" );        
	        result.add( params );
        }
        
        params = new HashMap<String, Object>();
        params.put( "label", "- Biaya Pengalihan" );
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
        	params.put( "content", editorUtil.convertToString( "Bebas biaya untuk 5 (lima) kali pengalihan dalam setahun Polis, pengalihan yang ke-6 (enam) dan seterusnya dalam satu tahun Polis dikenakan biaya pengalihan sebesar Rp. 100.000,- yang akan dipotong dari Dana Investasi" ));
        }
        else if( cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X5 && cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X6 
        	&& cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X7 && cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X8 
        	&& cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X9 && cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X10 
        	&& cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X11 && cepr01030102Form.getRightPartOfBusinessCd() != Cepr01040208Mapper.X12){
        	params.put( "content", editorUtil.convertToString( "2% untuk untuk pengalihan dana investasi yang ke-3 dan seterusnya dalam satu tahun polis" ));
        }else{
        	params.put( "content", editorUtil.convertToString( "Rp 50.000,00 untuk untuk pengalihan dana investasi yang ke-5 dan seterusnya dalam satu tahun polis" ));
        }
        params.put( "separator", ":" );        
        result.add( params );

        return result;
    }
    
    
    public List<Map<String, Object>> getCommonHeaderRowList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getCommonHeaderRowList" );

        List sekaligusRightPartCdList = ArrUtil.toListFromArray( sekaligusRightPartCdArr );
        List berkalaRightPartCdList = ArrUtil.toListFromArray( berkalaRightPartCdArr );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01030104Form cepr01030104Form = cepr01030000HoldingForm.getCepr01030104Form();
        
        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        // Fill header content from CommonHeaderDataSource
        String word_premi = "";
       
        	 result.addAll( source.getPolicyHolderNameMap() );
             result.addAll( source.getPolicyHolderAgeMap() );
             
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
            	 result.addAll( source.getGenderInsuredMap() );
             }else
             {
                 result.addAll( source.getInsuredNameMap() );
                 result.addAll( source.getInsuredAgeMap() );
                 result.addAll( source.getGenderInsuredMap() );                 
            }             
             result.addAll( source.getTermOfContractWithLimitMap( insuredAgeLimit ) );
             word_premi = "Premi";
      

        Map<String, Object> params;
        if( sekaligusRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
	           params = new HashMap<String, Object>();
	           params.put( "label", "Total "+ word_premi + " Sekaligus" );	            
	           params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) +" (100%)" );
	           result.add( params );           
            
           if( cepr01030104Form.getTopupDrawVOList().get(0).getYearFlag() != null && cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().compareTo(new BigDecimal(0)) >= 1)
 	       {
        	   params = new HashMap<String, Object>();
        	   params.put( "label", word_premi + " Top Up Single" );
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
        	if(  cepr01030102Form.getPremiumCombinationCd().equals( new BigDecimal("100") ) ){
                params = new HashMap<String, Object>();
                params.put( "label", word_premi + " Pokok "+ label );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getBasePremium() ) );
                result.add( params );
        	} else{
//       		
	           params = new HashMap<String, Object>();
	           params.put( "label", word_premi + " Pokok "+ label );
	           params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getBasePremium() ) + " (" + cepr01030102Form.getPremiumCombinationCd().toString() + "%)" );
	           result.add( params );
	
	           params = new HashMap<String, Object>();
	           params.put( "label", word_premi + " Top Up Berkala "+ label );
	           params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getTopUpPremium() ) + " (" + ( new BigDecimal( "100" ).subtract( cepr01030102Form.getPremiumCombinationCd() ) ).toString() + "%)" );
	           result.add( params );              
        		
                params = new HashMap<String, Object>();
                params.put( "label", "" );
                params.put( "content", "------------------------" );
                result.add( params );
                
                params = new HashMap<String, Object>();
                params.put( "label", "Total " + word_premi + " " + label );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) );
                result.add( params );
        	}
        	        	        	
	       if( cepr01030104Form.getTopupDrawVOList().get(0).getYearFlag() != null && cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().compareTo(new BigDecimal(0)) >= 1)
	       {
	        		 params = new HashMap<String, Object>();
	        		 params.put( "label", word_premi + " Top Up Single" );
	                 params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount() ) );
	                 result.add( params ); 
	       }
        	
            params = new HashMap<String, Object>();
            params.put( "label", "Masa Pembayaran " + word_premi  );
            params.put( "content", editorUtil.convertToString( cepr01030102Form.getPremiumFurloughYear() ) + " Tahun" );
            result.add( params );
        }
      
        	result.addAll( source.getTotalSumInsuredMap() );
            result.addAll( source.getPolicyHolderMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_pp" ) ) );
            result.addAll( source.getInsuredMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_tt" ) ) ); 
      
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
            b = 11; /**USD Fund BSIM Products**/
        }

        double totalInvestmentAllocation = 0;
        double totalAsumptionLow = 0;
        double totalAsumptionMid = 0;
        double totalAsumptionHi = 0;

        double allocationYieldLow =0;
        double allocationYieldMid=0;
        double allocationYieldHi=0;

        double annualYieldLow;
        double annualYieldMid;
        double annualYieldHi;

        int j;
        for( i = a; i <= b; i++ )
        {
        	j=i;
        	if( "01".equals( istr_prop.kurs_id ) )
            {
        	 if(CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiFixListDisplay()) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiDynamicListDisplay()) &&
             		CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiAggresiveListDisplay())	&& CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getLjiCashFundDisplay()) ){
             /*	if(j>3){
             		j=j-3;
             	}  */      		 
             }
            }
        	         	
            annualYieldLow = lstr.bunga[ j ][ 1 ] * 100;
            annualYieldMid = lstr.bunga[ j ][ 2 ] * 100;
            annualYieldHi = lstr.bunga[ j ][ 3 ] * 100;
            
            allocationYieldLow = annualYieldLow * istr_prop.fund[ i ] / 100;
	        allocationYieldMid = annualYieldMid * istr_prop.fund[ i ] / 100;
	        allocationYieldHi = annualYieldHi * istr_prop.fund[ i ] / 100;
	        	        
	        /*
	        if( "02".equals( istr_prop.kurs_id ) )
            {
	        	allocationYieldLow = annualYieldLow * istr_prop.fund[ i+3 ] / 100;
	 	        allocationYieldMid = annualYieldMid * istr_prop.fund[ i+3 ] / 100;
	 	        allocationYieldHi = annualYieldHi * istr_prop.fund[ i+3 ] / 100;
            }	     */   
	        
            params = new HashMap<String, Object>();	
           
            // => 6 Pilihan Investasi Fund: EXCELLINK & Simas
        	 if("01".equals( istr_prop.kurs_id )){
        		  
        	         params.put( "fundDesc", ls_jenis[ i ] );
        			 params.put( "investmentAllocation", editorUtil.convertToTwoDigit( istr_prop.fund[ i ] ) + "%" );
        			 params.put( "allocationYieldLow", editorUtil.convertToTwoDigit( allocationYieldLow ) + "%" );
        	         params.put( "allocationYieldMid", editorUtil.convertToTwoDigit( allocationYieldMid ) + "%" );
        	         params.put( "allocationYieldHi", editorUtil.convertToTwoDigit( allocationYieldHi ) + "%" );
               
             }
        	 else if("02".equals( istr_prop.kurs_id )) {
        		// params.put( "fundDesc", ls_jenis[ i+3 ] );
        		  params.put( "fundDesc", ls_jenis[ i ] );        		  
        		// params.put( "investmentAllocation", editorUtil.convertToTwoDigit( istr_prop.fund[ i+3 ] ) + "%" );
        		 params.put( "investmentAllocation", editorUtil.convertToTwoDigit( istr_prop.fund[ i ] ) + "%" );        
                 params.put( "allocationYieldLow", editorUtil.convertToTwoDigit( allocationYieldLow ) + "%" );
                 params.put( "allocationYieldMid", editorUtil.convertToTwoDigit( allocationYieldMid ) + "%" );
                 params.put( "allocationYieldHi", editorUtil.convertToTwoDigit( allocationYieldHi ) + "%" );       		 
        	 }        	 
          
        	 
        	params.put( "annualYieldLow", editorUtil.convertToTwoDigit( annualYieldLow ) + "%" );
            params.put( "annualYieldMid", editorUtil.convertToTwoDigit( annualYieldMid ) + "%" );
            params.put( "annualYieldHi", editorUtil.convertToTwoDigit( annualYieldHi ) + "%" );
           
            if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10 ){
            	if(i == 6 ){
            		yieldList.add( params );
            	}
            }else{
            	 yieldList.add( params );
            }

            if( ("01".equals( istr_prop.kurs_id ) ))
            {
	            totalInvestmentAllocation = totalInvestmentAllocation + istr_prop.fund[ i ];
	            totalAsumptionLow = totalAsumptionLow + allocationYieldLow;
	            totalAsumptionMid = totalAsumptionMid + allocationYieldMid;
	            totalAsumptionHi = totalAsumptionHi + allocationYieldHi;
            }else if( "02".equals( istr_prop.kurs_id ) ){
            	// totalInvestmentAllocation = totalInvestmentAllocation + istr_prop.fund[ i + 3 ];
            	 totalInvestmentAllocation = totalInvestmentAllocation + istr_prop.fund[ i ];
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
    
    public List<Map<String, Object>> getInvestmentChoiceSmallRowList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getInvestmentChoiceSmallRowList" );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        int i = 0;
        Map<String, Object> params;

        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getFixIdr() != null && cepr01030102Form.getFixIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
           	 	if( cepr01030102Form.getLjiFixCd().equals("01") ){
	                params = new HashMap<String, Object>();
	                params.put( "description", "- " + investmentTitleArr[ 1 ] );
	                params.put( "currencySymbol", currencySymbol );
	                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getFixIdr() ).divide( new BigDecimal( "100" ) ) ) );
	                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getFixIdr() + "%" ) );
	                result.add( params );
           	 	}
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
            	if( cepr01030102Form.getLjiDynamicCd().equals("02") ) {   	
	                params = new HashMap<String, Object>();
	                params.put( "description", "- " + investmentTitleArr[ 2 ] );
	                params.put( "currencySymbol", currencySymbol );
	                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getDynamicIdr() ).divide( new BigDecimal( "100" ) ) ) );
	                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicIdr() + "%" ) );
	                result.add( params );
            	}
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
            	if( cepr01030102Form.getLjiAggresiveCd().equals("03") ){
	                params = new HashMap<String, Object>();
	                params.put( "description", "- " + investmentTitleArr[ 3 ] );
	                params.put( "currencySymbol", currencySymbol );
	                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getAggresiveIdr() ).divide( new BigDecimal( "100" ) ) ) );
	                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveIdr() + "%" ) );
	                result.add( params );
            	}
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
            if( cepr01030102Form.getCashFundIdr() != null && cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 7 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getCashFundIdr() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getCashFundIdr() + "%" ) );
                result.add( params );
            }
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
            /**USD Fund BSIM Products**/
            if( cepr01030102Form.getAggresiveUsd() != null && cepr01030102Form.getAggresiveUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {    	
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 11 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getAggresiveUsd() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveUsd() + "%" ) );
                result.add( params );
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
            if( cepr01030102Form.getCashFundIdr() != null && cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
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
            /**USD Fund BSIM Products**/
            if( cepr01030102Form.getAggresiveUsd() != null && cepr01030102Form.getAggresiveUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 11 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveUsd() + "%" ) );
                result.add( params );
                for( int i = 2;  i < investmentTitleAndDescrArr[ 11 ].length; i++  )
                {
                	if( investmentTitleAndDescrArr[ 11 ][ i ] != null ){
	                    params = new HashMap<String, Object>();
	                    params.put( "description", investmentTitleAndDescrArr[ 11 ][ i ] );
	                    params.put( "allocationPercent", "" );
	                    result.add( params );
                	}
                }
            }
        }

        return result;
    }
    
    public Cepr00000000YieldResultVO of_get_rate_ds_ProAsset()
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
        
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X12 ){  
        	 if( "01".equals( istr_prop.kurs_id ) )
             {
                 a = 1;
                 b = 5;
             }
             else
             {
                 a = 6;
                 b = 7;
             }
        }else{
        	  if( "01".equals( istr_prop.kurs_id ) )
              {
                  a = 1;
                  b = 3;
              }
              else
              {
                  a = 4;
                  b = 5;
              }
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

        for( i = a; i <= b; i++ )
        {
            annualYieldLow = lstr.bunga[ i ][ 1 ] * 100;
            annualYieldMid = lstr.bunga[ i ][ 2 ] * 100;
            annualYieldHi = lstr.bunga[ i ][ 3 ] * 100;

            allocationYieldLow = annualYieldLow * istr_prop.fund[ i ] / 100;
            allocationYieldMid = annualYieldMid * istr_prop.fund[ i ] / 100;
            allocationYieldHi = annualYieldHi * istr_prop.fund[ i ] / 100;


            params = new HashMap<String, Object>();
            params.put( "fundDesc", ls_jenis[ i ] );
            params.put( "annualYieldLow", editorUtil.convertToTwoDigit( annualYieldLow ) + "%" );
            params.put( "annualYieldMid", editorUtil.convertToTwoDigit( annualYieldMid ) + "%" );
            params.put( "annualYieldHi", editorUtil.convertToTwoDigit( annualYieldHi ) + "%" );
            params.put( "investmentAllocation", editorUtil.convertToTwoDigit( istr_prop.fund[ i ] ) + "%" );
            params.put( "allocationYieldLow", editorUtil.convertToTwoDigit( allocationYieldLow ) + "%" );
            params.put( "allocationYieldMid", editorUtil.convertToTwoDigit( allocationYieldMid ) + "%" );
            params.put( "allocationYieldHi", editorUtil.convertToTwoDigit( allocationYieldHi ) + "%" );
            yieldList.add( params );

            totalInvestmentAllocation = totalInvestmentAllocation + istr_prop.fund[ i ];
            totalAsumptionLow = totalAsumptionLow + allocationYieldLow;
            totalAsumptionMid = totalAsumptionMid + allocationYieldMid;
            totalAsumptionHi = totalAsumptionHi + allocationYieldHi;
        }

        result.setYieldList( yieldList );
        result.setTotalInvestmentAllocation( editorUtil.convertToTwoDigit( totalInvestmentAllocation ) + "%" );
        result.setTotalAssumptionLow( editorUtil.convertToTwoDigit( totalAsumptionLow ) + "%" );
        result.setTotalAssumptionMid( editorUtil.convertToTwoDigit( totalAsumptionMid ) + "%" );
        result.setTotalAssumptionHi( editorUtil.convertToTwoDigit( totalAsumptionHi ) + "%" );

        return result;
    }

    public List<Map<String, Object>> getInvestmentChoiceRowList_ProAsset() throws IOException
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
            if( cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 2 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicIdr() + "%" ) );
                result.add( params );

                for( int i = 2;  i < investmentTitleAndDescrArr[ 2 ].length; i++  )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", investmentTitleAndDescrArr[ 2 ][ i ] );
                    params.put( "allocationPercent", "" );
                    result.add( params );
                }
            }
            if( cepr01030102Form.getAggresiveIdr() != null && cepr01030102Form.getAggresiveIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 3 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getAggresiveIdr() + "%" ) );
                result.add( params );

                for( int i = 2;  i < investmentTitleAndDescrArr[ 3 ].length; i++  )
                {
                    params = new HashMap<String, Object>();
                    params.put( "description", investmentTitleAndDescrArr[ 3 ][ i ] );
                    params.put( "allocationPercent", "" );
                    result.add( params );
                }
            }
            if( cepr01030102Form.getCashFundIdr() != null ){
            	if( cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
            	 params = new HashMap<String, Object>();
                 params.put( "description", investmentTitleAndDescrArr[ 4 ][ 1 ] );
                 params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getCashFundIdr() + "%" ) );
                 result.add( params );
                 
                 for( int i = 2;  i < investmentTitleAndDescrArr[ 4 ].length; i++  )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", investmentTitleAndDescrArr[ 4 ][ i ] );
                     params.put( "allocationPercent", "" );
                     result.add( params );
                 }
            	
            }}    
            
            if( cepr01030102Form.getExcellinkEquityIdr() != null && cepr01030102Form.getExcellinkEquityIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
            	 params = new HashMap<String, Object>();
                 params.put( "description", investmentTitleAndDescrArr[ 5 ][ 1 ] );
                 params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getExcellinkEquityIdr() + "%" ) );
                 result.add( params );
                 
                 for( int i = 2;  i < investmentTitleAndDescrArr[ 5 ].length; i++  )
                 {
                     params = new HashMap<String, Object>();
                     params.put( "description", investmentTitleAndDescrArr[ 5 ][ i ] );
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
                params.put( "description", investmentTitleAndDescrArr[ 6 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getSecureUsd() + "%" ) );
                result.add( params );

                for( int i = 2;  i < investmentTitleAndDescrArr[ 6 ].length; i++  )
                {
                	if( investmentTitleAndDescrArr[ 6 ][ i ] != null)
                	{
	                    params = new HashMap<String, Object>();
	                    params.put( "description", investmentTitleAndDescrArr[ 6 ][ i ] );
	                    params.put( "allocationPercent", "" );
	                    result.add( params );
                	}
                }
            }
            if( cepr01030102Form.getDynamicUsd() != null && cepr01030102Form.getDynamicUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", investmentTitleAndDescrArr[ 7 ][ 1 ] );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicUsd() + "%" ) );
                result.add( params );
                for( int i = 2;  i < investmentTitleAndDescrArr[ 7 ].length; i++  )
                {
                	if( investmentTitleAndDescrArr[ 7][ i ] != null ){
	                    params = new HashMap<String, Object>();
	                    params.put( "description", investmentTitleAndDescrArr[ 7 ][ i ] );
	                    params.put( "allocationPercent", "" );
	                    result.add( params );
                	}
                }
            }
        }

        return result;
    }
    
    public List<Map<String, Object>> getInvestmentChoiceSmallRowList_ProAsset() throws IOException
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

            if( cepr01030102Form.getDynamicIdr() != null && cepr01030102Form.getDynamicIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 2 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getDynamicIdr() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicIdr() + "%" ) );
                result.add( params );
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
            if( cepr01030102Form.getCashFundIdr() != null ){
            	if( cepr01030102Form.getCashFundIdr().compareTo( new BigDecimal( "0" ) ) > 0 ){
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 4 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getCashFundIdr() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getCashFundIdr() + "%" ) );
                result.add( params );
            }}
            if( cepr01030102Form.getExcellinkEquityIdr() != null && cepr01030102Form.getExcellinkEquityIdr().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 5 ] );
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
                params.put( "description", "- " + investmentTitleArr[ 6 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getSecureUsd() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getSecureUsd() + "%" ) );
                result.add( params );
            }

            if( cepr01030102Form.getDynamicUsd() != null && cepr01030102Form.getDynamicUsd().compareTo( new BigDecimal( "0" ) ) > 0 )
            {
                params = new HashMap<String, Object>();
                params.put( "description", "- " + investmentTitleArr[ 7 ] );
                params.put( "currencySymbol", currencySymbol );
                params.put( "allocationAmount", editorUtil.convertToString( cepr01030102Form.getPremium().multiply( cepr01030102Form.getDynamicUsd() ).divide( new BigDecimal( "100" ) ) ) );
                params.put( "allocationPercent", editorUtil.convertToString( cepr01030102Form.getDynamicUsd() + "%" ) );
                result.add( params );
            }
        }

        return result;
    }
    
    public double flag_syarat_bonus_premi(int i, Cepr01030102Form cepr01030102Form , int k , double ldec_premi_ppokok_setahun){
    	double result = 0;
    	    
    		
    		if( cepr01030102Form.getPremiumFurloughYear() >= 10 && cepr01030102Form.getPremiumFurloughYear() <= 14 ){    		
        		if( i == 10 && k == 1){
        			result =  FormatNumber.round((0.1 * ldec_premi_ppokok_setahun) * FormatNumber.round(Math.pow((1 + 0.04),( ( double ) 1/12)), 15),2) ;    	
       //     		ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2) + flag_syarat_bonus_premi[k];

        		}else if( i == 10 && k == 2){    		
        			result = (0.1 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.08),( ( double ) 1/12)));    
        		}else if( i == 10 && k == 3){
        			result = (0.1 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.10),( ( double ) 1/12)));
        		}
        	} else if( cepr01030102Form.getPremiumFurloughYear() >= 15 && cepr01030102Form.getPremiumFurloughYear() <= 19 ){
        		if( i == 10 && k == 1){
        			result =  ((0.1 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.04),( ( double ) 1/12))));    					
        		}else if( i == 10 && k == 2){    		
        			result = (0.1 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.08),( ( double ) 1/12)));    
        		}else if( i == 10 && k == 3){
        			result = (0.1 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.10),( ( double ) 1/12)));
        		} 
        		else if( i == 15 && k == 1){    		
        			result = (0.15 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.04),( ( double ) 1/12)));  
        		}else if( i == 15 && k == 2){    			
        			result = (0.15 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.08),( ( double ) 1/12)));  
        		}if( i == 15  && k == 3){    			
        			result = (0.15 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.10),( ( double ) 1/12)));  
        		}    		
        	} else if( cepr01030102Form.getPremiumFurloughYear() >= 20 ){
        		if( i == 10 && k == 1){
        			result =  ((0.1 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.04),( ( double ) 1/12))));    					
        		}else if( i == 10 && k == 2){    		
        			result = (0.1 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.08),( ( double ) 1/12)));    
        		}else if( i == 10 && k == 3){
        			result = (0.1 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.10),( ( double ) 1/12)));
        		} 
        		else if( i == 15 && k == 1){    		
        			result = (0.15 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.04),( ( double ) 1/12)));  
        		}else if( i == 15 && k == 2){    			
        			result = (0.15 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.08),( ( double ) 1/12)));  
        		}else if( i == 15 && k == 3){    			
        			result = (0.15 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.10),( ( double ) 1/12)));  
        		}else if( i == 20 && k == 1){    			
        			result = (0.2 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.04),( ( double ) 1/12))); 
        		}else if( i == 20 && k == 2){    		
        			result = (0.2 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.08),( ( double ) 1/12))); 
        		}else if( i == 20 && k == 3){    		
        			result = (0.2 * ldec_premi_ppokok_setahun) * (Math.pow((1 + 0.10),( ( double ) 1/12))); 
        		}    		   		    		
        	}
    	
    //	if ( result == null ) result = 1;
    	return result;
    }
    
    
    public double flag_syarat_bonus_premi2(int i, Cepr01030102Form cepr01030102Form ,  double ldec_premi_ppokok_setahun){
    	double result = 0;
    	    
    		
    		if( cepr01030102Form.getPremiumFurloughYear() >= 10 && cepr01030102Form.getPremiumFurloughYear() <= 14 ){    		
        		if( i == 10 ){
        			result =  (0.1 * ldec_premi_ppokok_setahun);
        		}
        	} else if( cepr01030102Form.getPremiumFurloughYear() >= 15 && cepr01030102Form.getPremiumFurloughYear() <= 19 ){
        		if( i == 10 ){
        			result =  (0.1 * ldec_premi_ppokok_setahun);
        		}else if( i == 15 ){    		
        			result = (0.15 * ldec_premi_ppokok_setahun);
        		}    		
        	} else if( cepr01030102Form.getPremiumFurloughYear() >= 20 ){
        		if( i == 10 ){
        			result =  (0.1 * ldec_premi_ppokok_setahun);
        		}else if( i == 15 ){    		
        			result = (0.15 * ldec_premi_ppokok_setahun);
        		}    		
        		else if( i == 20 ){    		
        			result = (0.2 * ldec_premi_ppokok_setahun);
        		}        		
        	}
    	
    //	if ( result == null ) result = 1;
    	return result;
    		
    }
    
    
    
    
    
    public double ekstraAlokasi2(int i, int k , double ldec_premi_ppokok_setahun,double averange_bunga){
    	double result  = 0;
    	double result1 = 0;
    	double result2 = 0;
    	double result3 = 0;
    	double ss = averange_bunga;
    	//result =  FormatNumber.round((0.1 * ldec_premi_ppokok_setahun) * FormatNumber.round(Math.pow((1 + 0.05),( ( double ) 1/12)), 15),2) ;   
    	
    	/*if( i == 1 && k == 1){
    	//if(i < 6){
    		//result = i * 0.01 * ldec_premi_ppokok_setahun;
    		//result =  FormatNumber.round((0.01 * ldec_premi_ppokok_setahun) * FormatNumber.round(Math.pow((1 + 0.05),( ( double ) 1/12)), 15),2) ; 
    	//	result =  FormatNumber.round((0.01 * ldec_premi_ppokok_setahun),2);
    	}else if( i == 1 && k == 2){
    		//result =  FormatNumber.round((0.01 * ldec_premi_ppokok_setahun) * FormatNumber.round(Math.pow((1 +  0.08),( ( double ) 1/12)), 15),2) ;
    		//result =  FormatNumber.round((0.01 * ldec_premi_ppokok_setahun),2);
    	}else if( i == 1 && k == 3){
    		//result =  FormatNumber.round((0.01 * ldec_premi_ppokok_setahun) * FormatNumber.round(Math.pow((1 +  0.10),( ( double ) 1/12)), 15),2) ; 
    		//result =  FormatNumber.round((0.01 * ldec_premi_ppokok_setahun),2);
    	}*/
    	if( i <6 ){
    	if(k == 1){
    		//result =  FormatNumber.round(( i * 0.01 * ldec_premi_ppokok_setahun),2);
    		
    		//ok result = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.05),( ( double ) 1/12)), 2) ; 
    		
    	//result =  ( i * 0.01 * ldec_premi_ppokok_setahun);
    		
    		
    		
    		if(  (cepr01030102Form.getFixIdr().doubleValue()> 0 ) ){
    			//result1 = FormatNumber.round(( ( (i * 0.01 *)+1) ldec_premi_ppokok_setahun) * Math.pow((1 + 0.05),( ( double ) 1/12)), 2) ; 
    			/*result1 = FormatNumber.round( (  (i * 0.01 )  * ldec_premi_ppokok_setahun  * 0.05), 2) ;
    			*/
/*    			result1 = FormatNumber.round( (  (i * 0.01 )  * ldec_premi_ppokok_setahun  * averange_bunga), 2) ;
*/
    			result1 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + averange_bunga),( ( double ) 1/12)), 2) ; 
    			//result1 =  result1 * cepr01030102Form.getFixIdr().doubleValue();
    			
    			
    			
    		/*	if( i == 1){
	    			result1 =  result1 * cepr01030102Form.getFixIdr().doubleValue();
	    			//result1 = result1/100;
    			}*/
    				result1 = result1/100;
    			
    		}
    		if(  (cepr01030102Form.getDynamicIdr().doubleValue()> 0 ) ){
    			//result2 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.06),( ( double ) 1/12)), 2) ; 
    			//result2 =  result2 * cepr01030102Form.getDynamicIdr().doubleValue();
    			//result2 = result2/100;
    			//result2 = FormatNumber.round( (  (i * 0.01 )  * ldec_premi_ppokok_setahun  * averange_bunga), 2) ;
    			
    			result2 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + averange_bunga),( ( double ) 1/12)), 2) ; 
    			//result2 =  result2 * cepr01030102Form.getDynamicIdr().doubleValue();
    			
    		    
    			if( i == 1){
	    	///		result2 =  result2 * cepr01030102Form.getFixIdr().doubleValue();
	    			//result1 = result1/100;
    			}
    				result2 = result2/100;    		
    			
    		}
    		if(  (cepr01030102Form.getAggresiveIdr().doubleValue()> 0 ) ){
    			//result3 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.07),( ( double ) 1/12)), 2) ; 
    			//result3 =  result3 * cepr01030102Form.getAggresiveIdr().doubleValue();
    			//result3 = result3/100;
    			
    			//result3 = FormatNumber.round( (  (i * 0.01 )  * ldec_premi_ppokok_setahun  * averange_bunga), 2) ;
    			result3 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + averange_bunga),( ( double ) 1/12)), 2) ; 
    			//result3 =  result3 * cepr01030102Form.getAggresiveIdr().doubleValue();
    		    
    			if( i == 1){
    			//	result3 =  result3 * cepr01030102Form.getAggresiveIdr().doubleValue();
	    			//result1 = result1/100;
    			}
    				result3 = result3/100;    			
    		}
    		
    		
    		result = result1 + result2 + result3;
    		result = FormatNumber.round(result, 2);
    		
    		
    	}
    	else if(k == 2){
    		//result =  FormatNumber.round(( i * 0.01 * ldec_premi_ppokok_setahun),2);
    		// ok - result = FormatNumber.round(( i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.08),( ( double ) 1/12)), 2) ; 
    	//result =  ( i * 0.01 * ldec_premi_ppokok_setahun);
    		
    		if(  (cepr01030102Form.getFixIdr().doubleValue()> 0 ) ){
    			//result1 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.08),( ( double ) 1/12)), 2) ; 
    			//result1 =  result1 * cepr01030102Form.getFixIdr().doubleValue();
    			//result1 = result1/100;
    			//result1 = FormatNumber.round( (  (i * 0.01 )  * ldec_premi_ppokok_setahun  * 0.12), 2) ;
    			//result2 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.06),( ( double ) 1/12)), 2) ; 
    			
    			// ok-patar  result1 =  FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * FormatNumber.round(Math.pow((1 + 0.08),( ( double ) 1/12)), 15),2) ;
    			
    		//	result1 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.08),( ( double ) 1/12)), 2) ; 
    			
    			
    		    			
    			
    			
    		/*	if( i == 1){
	    			result1 =  result1 * cepr01030102Form.getFixIdr().doubleValue();
	    			//result1 = result1/100;
    			}*/    				    			
    			
    			result1 =  FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + averange_bunga),( ( double ) 1/12)), 2) ;
    		//	result1 =  result1 * cepr01030102Form.getFixIdr().doubleValue();
    			    			
    			
    				result1 = result1/100;
    			
    		}
    		
    
    		
    		
    		if(  (cepr01030102Form.getDynamicIdr().doubleValue()> 0 ) ){
    			//result2 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 +  0.10),( ( double ) 1/12)), 2) ; 
    			// ok-patar result2 =  FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * FormatNumber.round(Math.pow((1 + 0.10),( ( double ) 1/12)), 15),2) ;
    			result2 =  FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + averange_bunga),( ( double ) 1/12)), 2) ;
    			//result2 =  result2 * cepr01030102Form.getDynamicIdr().doubleValue();
    		
    			result2 = result2/100;
    			
    		
    			
    		}
    		if(  (cepr01030102Form.getAggresiveIdr().doubleValue()> 0 ) ){
    			//result3 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.12),( ( double ) 1/12)), 2) ; 
    			// ok -patar result3 =  FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * FormatNumber.round(Math.pow((1 + 0.12),( ( double ) 1/12)), 15),2) ;
    			result3 =  FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + averange_bunga),( ( double ) 1/12)), 2) ;
    			//result3 =  result3 * cepr01030102Form.getAggresiveIdr().doubleValue();
    		
    			result3 = result3/100;
    			
    			
    			
    		    
    			if( i == 1){
    			//	result3 =  result3 * cepr01030102Form.getAggresiveIdr().doubleValue();
	    			//result1 = result1/100;
    			}
    						
    		}
    		
    		
    		result = result1 + result2 + result3;
    		//result = result/100;
    	//	result = FormatNumber.round(result, 2);
    		//result = result*100;
    		
    	}else if(k == 3){
    		//result =  FormatNumber.round(( i * 0.01 * ldec_premi_ppokok_setahun),2);
    		// ok -- result = FormatNumber.round(( i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.1),( ( double ) 1/12)), 2) ; 
    	//result =  ( i * 0.01 * ldec_premi_ppokok_setahun);
    		
    		if(  (cepr01030102Form.getFixIdr().doubleValue()> 0 ) ){
    			//result1 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.1),( ( double ) 1/12)), 2) ;
    			result1 =  FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + averange_bunga),( ( double ) 1/12)), 2) ;
        		
    			
    		//	result1 =  result1 * cepr01030102Form.getFixIdr().doubleValue();
    			result1 = result1/100;
    		}
    		if(  (cepr01030102Form.getDynamicIdr().doubleValue()> 0 ) ){
    		//	result2 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 +  0.14),( ( double ) 1/12)), 2) ; 
    			//ok-patar result2 =  FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * FormatNumber.round(Math.pow((1 + 0.14),( ( double ) 1/12)), 15),2) ;
    			result2 =  FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + averange_bunga),( ( double ) 1/12)) ,2) ;
    			
    			
    			
    		//	result2 =  result2 * cepr01030102Form.getDynamicIdr().doubleValue();
    			result2 = result2/100;
    		}
    		if(  (cepr01030102Form.getAggresiveIdr().doubleValue()> 0 ) ){
    		//	result3 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.18),( ( double ) 1/12)), 2) ; 
    			result3 =  FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + averange_bunga),( ( double ) 1/12)), 2) ;
            	
    			
    			//result3 =  result3 * cepr01030102Form.getAggresiveIdr().doubleValue();
    			result3 = result3/100;
    		}
    		    		
    		result = result1 + result2 + result3;
    		//result = FormatNumber.round(result, 2);
    		    		
    	}
    	
    		
    	}
    	
    	return result;
    }
        
    public double ekstraAlokasi(int i, int k , double ldec_premi_ppokok_setahun){
    	double result  = 0;
    	double result1 = 0;
    	double result2 = 0;
    	double result3 = 0;
    	//result =  FormatNumber.round((0.1 * ldec_premi_ppokok_setahun) * FormatNumber.round(Math.pow((1 + 0.05),( ( double ) 1/12)), 15),2) ;   
    	
    	/*if( i == 1 && k == 1){
    	//if(i < 6){
    		//result = i * 0.01 * ldec_premi_ppokok_setahun;
    		//result =  FormatNumber.round((0.01 * ldec_premi_ppokok_setahun) * FormatNumber.round(Math.pow((1 + 0.05),( ( double ) 1/12)), 15),2) ; 
    	//	result =  FormatNumber.round((0.01 * ldec_premi_ppokok_setahun),2);
    	}else if( i == 1 && k == 2){
    		//result =  FormatNumber.round((0.01 * ldec_premi_ppokok_setahun) * FormatNumber.round(Math.pow((1 +  0.08),( ( double ) 1/12)), 15),2) ;
    		//result =  FormatNumber.round((0.01 * ldec_premi_ppokok_setahun),2);
    	}else if( i == 1 && k == 3){
    		//result =  FormatNumber.round((0.01 * ldec_premi_ppokok_setahun) * FormatNumber.round(Math.pow((1 +  0.10),( ( double ) 1/12)), 15),2) ; 
    		//result =  FormatNumber.round((0.01 * ldec_premi_ppokok_setahun),2);
    	}*/
    	if( i <6 ){
    	if(k == 1){
    		//result =  FormatNumber.round(( i * 0.01 * ldec_premi_ppokok_setahun),2);
    		
    		//ok result = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.05),( ( double ) 1/12)), 2) ; 
    		
    	//result =  ( i * 0.01 * ldec_premi_ppokok_setahun);
    		
    		
    		
    		if(  (cepr01030102Form.getFixIdr().doubleValue()> 0 ) ){
    			result1 = FormatNumber.round(   ( (i * 0.01 ) * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.05),( ( double ) 1/12)), 2) ; 
    			// ok-patar result1 = FormatNumber.round( (  (i * 0.01 )  * ldec_premi_ppokok_setahun  * 0.05), 2) ;
    		    
    			if( i == 1){
	    			result1 =  result1 * cepr01030102Form.getFixIdr().doubleValue();
	    			//result1 = result1/100;
    			}
    				result1 = result1/100;
    			
    		}
    		if(  (cepr01030102Form.getDynamicIdr().doubleValue()> 0 ) ){
    			result2 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.06),( ( double ) 1/12)), 2) ; 
    			//result2 =  result2 * cepr01030102Form.getDynamicIdr().doubleValue();
    			//result2 = result2/100;
    			// ok-patar  result2 = FormatNumber.round( (  (i * 0.01 )  * ldec_premi_ppokok_setahun  * 0.05), 2) ;
    		    
    			if( i == 1){
	    			result2 =  result2 * cepr01030102Form.getFixIdr().doubleValue();
	    			//result1 = result1/100;
    			}
    				result2 = result2/100;    		
    			
    		}
    		if(  (cepr01030102Form.getAggresiveIdr().doubleValue()> 0 ) ){
    			result3 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.07),( ( double ) 1/12)), 2) ; 
    			//result3 =  result3 * cepr01030102Form.getAggresiveIdr().doubleValue();
    			//result3 = result3/100;
    			
    			result3 = FormatNumber.round( (  (i * 0.01 )  * ldec_premi_ppokok_setahun  * 0.07), 2) ;
    		    
    			if( i == 1){
	    			result3 =  result3 * cepr01030102Form.getFixIdr().doubleValue();
	    			//result1 = result1/100;
    			}
    				result3 = result3/100;    			
    		}
    		
    		
    		result = result1 + result2 + result3;
    		
    		
    	}
    	else if(k == 2){
    		//result =  FormatNumber.round(( i * 0.01 * ldec_premi_ppokok_setahun),2);
    		// ok - result = FormatNumber.round(( i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.08),( ( double ) 1/12)), 2) ; 
    	//result =  ( i * 0.01 * ldec_premi_ppokok_setahun);
    		
    		if(  (cepr01030102Form.getFixIdr().doubleValue()> 0 ) ){
    			//result1 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.08),( ( double ) 1/12)), 2) ; 
    			//result1 =  result1 * cepr01030102Form.getFixIdr().doubleValue();
    			//result1 = result1/100;
    		//	result1 = FormatNumber.round( (  (i * 0.01 )  * ldec_premi_ppokok_setahun  * 0.12), 2) ;
    			
    			result1 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.08),( ( double ) 1/12)), 2) ; 
    		    
    			if( i == 1){
	    			result1 =  result1 * cepr01030102Form.getFixIdr().doubleValue();
	    			//result1 = result1/100;
    			}
    				result1 = result1/100;
    			
    		}
    		if(  (cepr01030102Form.getDynamicIdr().doubleValue()> 0 ) ){
    			result2 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 +  0.10),( ( double ) 1/12)), 2) ; 
    			result2 =  result2 * cepr01030102Form.getDynamicIdr().doubleValue();
    			result2 = result2/100;
    		}
    		if(  (cepr01030102Form.getAggresiveIdr().doubleValue()> 0 ) ){
    			result3 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.12),( ( double ) 1/12)), 2) ; 
    			result3 =  result3 * cepr01030102Form.getAggresiveIdr().doubleValue();
    			result3 = result3/100;
    		}
    		
    		
    		result = result1 + result2 + result3;
    		
    		
    		
    	}else if(k == 3){
    		//result =  FormatNumber.round(( i * 0.01 * ldec_premi_ppokok_setahun),2);
    		// ok -- result = FormatNumber.round(( i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.1),( ( double ) 1/12)), 2) ; 
    	//result =  ( i * 0.01 * ldec_premi_ppokok_setahun);
    		
    		if(  (cepr01030102Form.getFixIdr().doubleValue()> 0 ) ){
    			result1 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.1),( ( double ) 1/12)), 2) ; 
    			result1 =  result1 * cepr01030102Form.getFixIdr().doubleValue();
    			result1 = result1/100;
    		}
    		if(  (cepr01030102Form.getDynamicIdr().doubleValue()> 0 ) ){
    			result2 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 +  0.14),( ( double ) 1/12)), 2) ; 
    			result2 =  result2 * cepr01030102Form.getDynamicIdr().doubleValue();
    			result2 = result2/100;
    		}
    		if(  (cepr01030102Form.getAggresiveIdr().doubleValue()> 0 ) ){
    			result3 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + 0.18),( ( double ) 1/12)), 2) ; 
    			result3 =  result3 * cepr01030102Form.getAggresiveIdr().doubleValue();
    			result3 = result3/100;
    		}
    		    		
    		result = result1 + result2 + result3;
    		    		
    	}
    	
    		
    	}
    	
    	return result;
    }
    
    public double ekstraAlokasi3(int i, int k, double ldec_premi_ppokok_setahun, double[] ldec_bunga_avg) {
        double result  = 0;
        double result1 = 0;

        if( i <6 ){
            if(k == 1){
                result1 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12)), 2) ;
                result1 = result1 * 100;
                result1 = result1 / 100;
                result = result1;
            } else if (k == 2){
                result1 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12)), 2) ;
                result1 = result1 * 100;
                result1 = result1 / 100;
                result = result1;
            } else if (k == 3){
                result1 = FormatNumber.round((i * 0.01 * ldec_premi_ppokok_setahun) * Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12)), 2) ;
                result1 = result1 * 100;
                result1 = result1 / 100;
                result = result1;
            }
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
    
    
    public IllustrationResultVO getIllustrationResult2()
    {
        logger.info( "*-*-*-* Cepr01040000Ilustration134Business.getIllustrationResult" );
        IllustrationResultVO result = new IllustrationResultVO();
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        Cepr01030101Form cepr01030101Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030101Form();
        Cepr01030104Form cepr01030104Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030104Form();

        ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        List<TopupDrawVO> topupDrawVOList = cepr01030104Form.getTopupDrawVOList();
        int topupDrawVOListSize = topupDrawVOList.size();
        TopupDrawVO topupDrawVO;
        String premiumTotal;
        String topup;
        String draw;

        int li_ke = 0, li_bagi = 1000, li_hal = 3;
        double[] ldec_bak ;
        double[] flag_syarat_bonus_premi = new double[3 + 1];     
        double[] ekstraAlokasi = new double[3 + 1];     
        
        double[] ldec_hasil_ppokok = new double[3 + 1];
        double[] ldec_hasil_ptu = new double[3 + 1];
        double[] ldec_hasil_wdraw = new double[3 + 1];
        //double[] ldec_fph = {CommonConst.DUMMY_ZERO, 0, 0, 0.9, 0.85, 0.8};
        double[] ldec_fph =  {0, 0, 0, 0.7, 0.3, 0.2};
        double ldec_bawal = 100000;
        double[] ldec_man_non = new double[2 + 1];
        double ldec_bak_tu;
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
        	ldec_bak_tu = 0.05;
        }else{
        	ldec_bak_tu = 0;
        }
        double ldec_bak_tut = 0.05;
        double ldec_akuisisi;
        double ldec_premi_invest;
        double[][] ldec_hasil_invest = new double[5 + 1][3 + 1];
        double[] ldec_tarik = { CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0 };
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6 
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10 
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X12){    		
    		 ldec_tarik = new double[] { CommonConst.DUMMY_ZERO, 0.04, 0.04, 0.03, 0.02, 0.01 };
        }else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
        	 ldec_tarik = new double[] { CommonConst.DUMMY_ZERO, 0.75, 0.65, 0.55, 0.45, 0.35, 0.25, 0.15 };               
        }
        	
        double ldec_wdraw;
        double[] ldec_premi_bulan = new double[12 + 1];
        double ldec_topup;
        double ldec_bass;
        double[][] ldec_bunga = new double[5 + 1][3 + 1];
        double[] ldec_bunga_avg = new double[3 + 1]; //= {0.06, 0.1, 0.08, 0.18, 0.12, 0.25} //{0.09, 0.06, 0.11, 0.15, 0.165, 0.25}  //fixed:0.09, 0.11, 0.15 (0.165); dynamic:0.08, 0.11, 0.18 (0.195)
        double ldec_fee = 0.020075;
        double ldec_coi = 0, ldec_mfc, ldec_sc, ldec_cost, ldec_ppokok, ldec_ptu, ldec_aph, ldec_ff, ldec_man_celaka, ldec_temp = 0; //, ldec_man[10+1] = {1, 1, 1, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7}
        boolean[] lb_minus = { CommonConst.DUMMY_FALSE, false, false, false };
        boolean lb_rider = false;
        String ls_sy = "", ls_temp; //ls_dpp = ' dari Premi Pokok', ls_sy = ''
        S_biaya lstr;
        double ldec_premi_ppokok_setahun = 0;
        double[] ldec_premi_ppokok_bulan = new double[12 + 1];
        String bonus = "0.00";
        String extraAlokasi = "0.00";
        
//n_riders ln_riders
        double ldec_manfaat, ldec_premi_setahun = 0, ldec_coi24 = 0;
////monthly fix cost
        if( cepr01030102Form.getRightPartOfBusinessCd() == 13 ){
        	 ldec_mfc = 0;
        }else{
        	 ldec_mfc = 27500;
        }        	
        //ldec_mfc = 15000;
        
        if(istr_prop.kurs_id.equals("02")){ 
        	ldec_mfc = 2.75;
        	//ldec_mfc = 2;
        	li_bagi = 1;
        }
        for( int i = 1; i <= 12; i++ )
        {
            ldec_premi_bulan[ i ] = 0;
            ldec_premi_ppokok_bulan[ i ] = 0;
            if( i == 1 ){
            	ldec_premi_bulan[ i ] = istr_prop.premi;
            	ldec_premi_ppokok_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
            }
            if( Hardcode.PAY_MODE_CD_TRIWULANAN == cepr01030102Form.getPaymentModeCd() )
            {
                if( i == 4 || i == 7 || i == 10 ) {
                	ldec_premi_bulan[ i ] = istr_prop.premi;
                	ldec_premi_ppokok_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
                }
            }
            else if( Hardcode.PAY_MODE_CD_SEMESTERAN == cepr01030102Form.getPaymentModeCd() )
            {
                if( i == 7 ) {
                	ldec_premi_bulan[ i ] = istr_prop.premi;
                	ldec_premi_ppokok_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
                }
            }
            else if( Hardcode.PAY_MODE_CD_BULANAN == cepr01030102Form.getPaymentModeCd() )
            {
                ldec_premi_bulan[ i ] = istr_prop.premi;
                ldec_premi_ppokok_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
            }
            ldec_premi_setahun += ldec_premi_bulan[ i ];
            ldec_premi_ppokok_setahun += ldec_premi_ppokok_bulan[ i ];
        }
        //
        for(int i = 1 ; i <= 3 ; i++){
    		ldec_hasil_invest[1][i] = 0;
    		ldec_hasil_ppokok[i] = 0;
    		ldec_hasil_ptu[i] = 0;
    		ldec_hasil_wdraw[i] = 0;
    		//ldec_pre_pp[i] = 0
    		//ldec_pre_tu[i] = 0
        }
        //
        ldec_manfaat = istr_prop.up;
        //    Biaya Akuisisi & asumsi penarikan (1 tarik, 1-nya tdk tarik)
        lstr = PbFunction.wf_get_biaya_84( istr_prop, command );
        ldec_bak = lstr.bak;
    	ldec_bunga = lstr.bunga;

    	int a, b, c;
        if( "01".equals( istr_prop.kurs_id ) )
        {
            a = 1;
            b = 8;
            c=1;
        }
        else
        {
            a = 9;
            b = 11;
            c=9;
        }
        
        if( cepr01030102Form.getRightPartOfBusinessCd() > 5 && cepr01030102Form.getRightPartOfBusinessCd() != 10 ){        	
        	if( cepr01030102Form.getRightPartOfBusinessCd() == 6  || cepr01030102Form.getRightPartOfBusinessCd() == 11 ){
        		  if( "01".equals( istr_prop.kurs_id ) )
                  {
                      a = 1;
                      b = 5;
                      c = 1;
                  }
                  else
                  {
                      a = 6;
                      b = 7;
                      c = 6;
                  }
        	} /*else{        		
        		  if( "01".equals( istr_prop.kurs_id ) )
                  {
                      a = 1;
                      b = 3;
                      c = 1;
                  }
                  else
                  {
                      a = 4;
                      b = 5;
                      c = 4;
                  }
        	}        	*/	
        } 
    	
        for( int i = 1; i <= 3; i++ )
        {
            ldec_bunga_avg[ i ] = 0;
            for( int j = a; j <= b; j++ )
            {
                ldec_bunga_avg[ i ] += ldec_bunga[ j ][ i ] * ( istr_prop.fund[ j ] / 100 );
                c++;
                if( "01".equals( istr_prop.kurs_id ))
                {
                	/*
                	if( cepr01030102Form.getRightPartOfBusinessCd() == 5 ){
	                	if(c>3 && c<7){
	                		c=c-3;
	                	}
	                	if(j==6){
	                		c=4;
	                	}                	
                	}*/
                	//else
                	if( cepr01030102Form.getRightPartOfBusinessCd() == 6 || cepr01030102Form.getRightPartOfBusinessCd() == 7 || cepr01030102Form.getRightPartOfBusinessCd() == 8 || cepr01030102Form.getRightPartOfBusinessCd() == 11 || cepr01030102Form.getRightPartOfBusinessCd() == 12){
                    	if(c>4){
                    	   c=c-4;
                    }}
                } if( "02".equals( istr_prop.kurs_id ))
                {
                	/*if( cepr01030102Form.getRightPartOfBusinessCd() == 5 ){
                	if(c>6){
                		c=c-2;
                	}}*/
                }
                
            }
        }
        
        boolean cekAdaTarik = false;
        double ldec_tarik_cek = 0;
        for( int i = 1; i <= istr_prop.ins_per; i++ )
        {
        	
        	 if( i <= ArrUtil.upperBound( lstr.tarik ) ) ldec_tarik_cek = lstr.tarik[ i ];
        	
        	 if( ldec_tarik_cek > 0){
        		 cekAdaTarik = true;
        		 break;
        	 }
        }

        double[] np = new double[4];
        double[] celaka = new double[4];

        int j;
        ldec_fee = 0;
        for( int i = 1; i <= istr_prop.ins_per; i++ )
        {
//        	surrender charge
        	ldec_sc = 0;
        	ldec_wdraw = 0;
        	ldec_topup = 0;       	
        	
          	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6 
            		|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8 
            		|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10
            		|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X12 ){
            		ldec_coi = of_get_coi_basic_190(i);        		
            }else if ( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ) {
            		ldec_coi = of_get_coi_190(i);
            }else{
            		ldec_coi = of_get_coi_basic(i);
            }
        	
        	if( istr_prop.cb_id == 0 ){
        		for(int k = 1; k <= ArrUtil.upperBound(istr_prop.rider_baru) ; k ++ ){
        			if( istr_prop.rider_baru[k] > 0 ){
        				ldec_temp = of_get_coi_134(k, i);
        				ldec_coi += ldec_temp;
        			}
        		}
        	}
        	if(i <= ArrUtil.upperBound(lstr.topup))	ldec_topup = lstr.topup[i];
        	
        	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
        		if(i <= 7) ldec_sc = ldec_tarik[i];
        	}else{
        		if(i <= 5) ldec_sc = ldec_tarik[i];
        	}        	
        	//
        	
        	ldec_cost = (ldec_coi + ldec_mfc);
        	
        	for(int k = 1 ; k <= 3; k++){
        		if(i <= ArrUtil.upperBound(lstr.tarik)) ldec_wdraw = lstr.tarik[i];
        		for(int li_bulan = 1 ; li_bulan <= 12 ; li_bulan++){
        			ldec_cost = (ldec_coi + ldec_mfc);
        			ldec_premi_invest = 0;
        			ldec_ppokok = 0;
        			ldec_ptu = 0;
        			ldec_ff = 0;
        			ldec_aph = 0;
        			if(i <= istr_prop.cuti_premi){
        				ldec_premi_invest = ldec_premi_bulan[li_bulan] * (istr_prop.pct_premi / 100);
        				ldec_premi_invest += ((ldec_premi_bulan[li_bulan] * (100 - istr_prop.pct_premi) / 100));
        				ldec_ppokok = ldec_premi_bulan[li_bulan] * (istr_prop.pct_premi / 100);
        				ldec_ptu = (ldec_premi_bulan[li_bulan] * (100 - istr_prop.pct_premi) / 100) * (1 - ldec_bak_tu);        			
        			}
        			
        			if(li_bulan == 1) ldec_ptu += ldec_topup;
        		// Em@il Vito@18/05/2016=> Rumusan SIMAS PRIMELINK & SMiLe AssetPro(Agency)(134-X5/X6)
        		if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6 
        				|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8
        				|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10
        				|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X12){
	       			if(i == 1 && li_bulan == 1){
	       				ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok - ldec_cost ) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12))), 2) ;
	       			}else{
	       				ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_hasil_ppokok[k] - ldec_cost ) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12))), 2) ;
	       			}
	       			ldec_hasil_ptu[k] = (ldec_ptu + ldec_hasil_ptu[k]) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12)));
	       			
        		}else{
        			
        			if(!cekAdaTarik){
        				flag_syarat_bonus_premi[k] = flag_syarat_bonus_premi( i , cepr01030102Form , k , ldec_premi_ppokok_setahun);
                	}
        			
        			//ekstraAlokasi[k] = ekstraAlokasi2(i, k, ldec_premi_ppokok_setahun,ldec_bunga_avg[k]);
        			ekstraAlokasi[k] = ekstraAlokasi3(i, k, ldec_premi_ppokok_setahun,ldec_bunga_avg);
        			//ekstraAlokasi[k] = ekstraAlokasi(i, k, ldec_premi_ppokok_setahun );
        			//ekstraAlokasi[k]  = 0;
        			if( li_bulan == 12){
        				//ekstraAlokasi[k]  = 0;
        				//flag_syarat_bonus_premi[k] = 0; 
        				//ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2)  + flag_syarat_bonus_premi[k] + ekstraAlokasi[k];
        				ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2) + ekstraAlokasi[k]+flag_syarat_bonus_premi[k];
        				
        			}else if( li_bulan == 1 ){        	       				
        				ekstraAlokasi[k]  = 0;
        				//ekstra alokasi
         				flag_syarat_bonus_premi[k] = 0; 
        				ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2) + ekstraAlokasi[k]+flag_syarat_bonus_premi[k];
        				
        			}else{
        				  //ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2);
        				ekstraAlokasi[k]  = 0;

         				flag_syarat_bonus_premi[k] = 0;
        				//ekstra alokasi
        				ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2) + ekstraAlokasi[k]+flag_syarat_bonus_premi[k];
        			}
        	        		        		
        			ldec_hasil_ptu[k] = (ldec_ptu + ldec_hasil_ptu[k]) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12)));
        			if( i == 1 && istr_prop.cb_id == 0 ){ 
        				if( li_bulan == 1 ) ldec_cost = 0;
        				if( li_bulan == 2 ) ldec_cost *= 2;
        			}
        			if(i == istr_prop.ins_per && li_bulan == 12) ldec_cost = 0;
        			if( istr_prop.cb_id == 0 ) {
        				if( i <= 2 )ldec_ff = (ldec_hasil_ppokok[k] * 0.03 / 12);
        			}else{        				
        				
        				if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
        					if( i <= 7 ) ldec_ff = (ldec_hasil_ptu[k] * 0 / 12);
            				if( i <= 7 ) ldec_ff += (ldec_hasil_ppokok[k] * 0.065 / 12);         					
        				}else{
        					if( i <= 2 ) ldec_ff = (ldec_hasil_ptu[k] * 0.0125 / 12);
            				if( i <= istr_prop.pay_per ) ldec_ff += (ldec_hasil_ppokok[k] * 0.025 / 12);        					
        				}
        						
        			}
        	    	//premium holiday
        	    	if(i <= 5 && istr_prop.cuti_premi <= 5){ 
        	    		if(i > istr_prop.cuti_premi && istr_prop.cb_id != 0) ldec_aph = ldec_fph[i] / (1 - ldec_fph[i]) * (ldec_cost + ldec_ff);
        	    	}
        	    	
        	    	
        	    	ldec_temp = ldec_hasil_ptu[k] - (ldec_cost + ldec_ff + ldec_aph);
        	    	if(ldec_temp < 0){
        	    		ldec_hasil_ppokok[k] += ldec_temp;
        	    		ldec_hasil_ptu[k] = 0;
        	    	}else{
        	    		ldec_hasil_ptu[k] = ldec_temp;
        	    	}
        	    	if(ldec_wdraw > 0 && li_bulan == 12 ){
        	    		if(i <= 2){ 
        	    			ldec_wdraw = 0;
        	    		}else{
        	    			if(i <= 5) ldec_wdraw = Math.min(ldec_wdraw, ldec_hasil_ptu[k]);
        	    				ldec_temp = ldec_hasil_ptu[k] - ldec_wdraw;
        	    			if(ldec_temp < 0){
        	    				ldec_hasil_ppokok[k] += ldec_temp;
        	    				ldec_hasil_ptu[k] = 0;
        	    			}else{
        	    				ldec_hasil_ptu[k] = ldec_temp;
        	    			}
        	    		}
        	    		
        	    	}
        		}// end (134-X1X4)    	
        	    	
	        	    if(k == 1)
	            	{
	            		ldec_hasil_invest[1][k] = ldec_hasil_ppokok[k] + ldec_hasil_ptu[k];	            		
	            	}
        		}

		       		
       			ldec_hasil_wdraw[k] += ldec_wdraw;
        		ldec_hasil_invest[1][k] = ldec_hasil_ppokok[k] + ldec_hasil_ptu[k];
        		ldec_hasil_invest[ 1 ][ k ] -= ( ldec_hasil_wdraw[k] * ( 1 + ldec_sc ) );
        		 
        		 
        		if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6
        			|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8
        			|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10
        			|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11|| cepr01030102Form.getRightPartOfBusinessCd() >= Cepr01040208Mapper.X12 ){
        				if( ldec_hasil_invest[ 1 ][ k ] <= 0 &&  ( i <= 10 ) ){
        					lb_minus[k] = true;	               
        				}
        		}else{
        			if( ldec_hasil_invest[1][k] <= 0 ) lb_minus[k] = true;
        		}
        	}
        

            //j = cepr01030101Form.getInsuredAge() + i;
        	j = istr_prop.umur_tt + i;
            if( i <= 23 || j == 55 || j == 65 || j == 75 || j == 80 || j == 100 )
            {
                for( int k = 1; k <= 3; k++ )
                {
                    np[ k ] = FormatNumber.round( ldec_hasil_invest[ 1 ][ k ] / li_bagi, 0 );
                    celaka[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] + ldec_manfaat ) / li_bagi, 0 );
                }

                if( i <= istr_prop.cuti_premi )
                {
                    premiumTotal = editorUtil.convertToStringWithoutCent( ldec_premi_setahun / li_bagi );
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
                    if( "0".equals( topup ) ) topup = "0.00";
                    if( "0".equals( draw ) ) draw = "0.00";
                }
                else
                {
                    topup = "0.00";
                    draw = "0.00";
                }
                
                if(!cekAdaTarik){                	
	                double bonus2;
	                if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd()) )
	                {
	                	 bonus2 = flag_syarat_bonus_premi2( i, cepr01030102Form , ldec_premi_ppokok_setahun);
	                }else{
	                	 bonus2 = flag_syarat_bonus_premi2( i, cepr01030102Form , ldec_premi_ppokok_setahun)/1000;
	                }
	                if( bonus2 == 0 ) {
	                     	//bonus = null;
	                     	//bonus = " - ";
	                     	 bonus = "0.00";
	                }else{
	                     	bonus = editorUtil.convertToStringWithoutCentAndNillIfNegative(bonus2);
	                }                                            
                }   
                
                if( i<=5 && cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
                	  double extraAlokasi2;
                	  extraAlokasi2 = (i * 0.01 * ldec_premi_ppokok_setahun)/1000;
                	  
                	  extraAlokasi = editorUtil.convertToStringWithoutCentAndNillIfNegative(extraAlokasi2);
                }else{
                	  extraAlokasi = "0.00";
                }
              
                
                String valueLow = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 1 ] ) );
                String valueMid = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 2 ] ) );
                String valueHi = editorUtil.convertToStringWithoutCentAndNillIfNegative( new BigDecimal( np[ 3 ] ) );

                String benefitLow = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 1 ], np[ 1 ] );
                String benefitMid = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 2 ], np[ 2 ] );
                String benefitHi = convertToStringWithoutCentAndSetNill( editorUtil, celaka[ 3 ], np[ 3 ] );

                map = new HashMap<String, Object>();
                map.put( "yearNo", editorUtil.convertToString( i ) );
                map.put( "insuredAge", editorUtil.convertToString( cepr01030101Form.getInsuredAge() + i ) );
                map.put( "premiumTotal", premiumTotal );
                map.put( "topupAssumption", topup );
                map.put( "drawAssumption", draw );
                map.put( "valueLow", valueLow );
                map.put( "valueMid", valueMid );
                map.put( "valueHi", valueHi );
                map.put( "benefitLow", benefitLow );
                map.put( "benefitMid", benefitMid );
                map.put( "benefitHi", benefitHi );
                map.put( "bonus",  bonus );
                map.put( "extraAlokasi",  extraAlokasi );
                mapList.add( map );

            }
        }

        result.setValidityMsg( lb_minus[ 1 ] ? "Maaf, Proposal yang Anda buat Tidak Layak Jual." : "" );
        result.setIllustrationList( mapList );
        return result;

    }
    
    public IllustrationResultVO getIllustrationResult3()
    {
        logger.info( "*-*-*-* Cepr01040000Ilustration134Business.getIllustrationResult" );
        IllustrationResultVO result = new IllustrationResultVO();
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        Cepr01030101Form cepr01030101Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030101Form();
        Cepr01030104Form cepr01030104Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030104Form();

        ArrayList<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        List<TopupDrawVO> topupDrawVOList = cepr01030104Form.getTopupDrawVOList();
        int topupDrawVOListSize = topupDrawVOList.size();
        TopupDrawVO topupDrawVO;
        String premiumTotal;
        String topup;
        String draw;

        int li_ke = 0, li_bagi = 1000, li_hal = 3, lama_tanggung = 0;
        double[] ldec_bak ;
        double[] flag_syarat_bonus_premi = new double[3 + 1];     
        double[] ekstraAlokasi = new double[3 + 1];     
        
        double[] ldec_hasil_ppokok = new double[3 + 1];
        double[] ldec_hasil_ptu = new double[3 + 1];
        double[] ldec_hasil_wdraw = new double[3 + 1];
        //double[] ldec_fph = {CommonConst.DUMMY_ZERO, 0, 0, 0.9, 0.85, 0.8};
        double[] ldec_fph =  {0, 0, 0, 0.7, 0.3, 0.2};
        double ldec_bawal = 100000;
        double[] ldec_man_non = new double[2 + 1];
        double ldec_bak_tu;
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
        	ldec_bak_tu = 0.05;
        }else{
        	ldec_bak_tu = 0;
        }        
        double ldec_bak_tut = 0.05;
        double ldec_akuisisi;
        double ldec_premi_invest;
        double[][] ldec_hasil_invest = new double[5 + 1][3 + 1];
        double[][] ldec_hasil_invest_batal = new double[5 + 1][3 + 1];
        double[] ldec_tarik = { CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0 };
        if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6 
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10 
        	|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X12){    		
    		 ldec_tarik = new double[] { CommonConst.DUMMY_ZERO, 0.04, 0.04, 0.03, 0.02, 0.01 };
        }else if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
        	 ldec_tarik = new double[] { CommonConst.DUMMY_ZERO, 0.75, 0.65, 0.55, 0.45, 0.35, 0.25, 0.15 };               
        }
        	
        double ldec_wdraw;
        double[] ldec_premi_bulan = new double[12 + 1];
        double ldec_topup;
        double ldec_bass;
        double[][] ldec_bunga = new double[5 + 1][3 + 1];
        double[] ldec_bunga_avg = new double[3 + 1]; //= {0.06, 0.1, 0.08, 0.18, 0.12, 0.25} //{0.09, 0.06, 0.11, 0.15, 0.165, 0.25}  //fixed:0.09, 0.11, 0.15 (0.165); dynamic:0.08, 0.11, 0.18 (0.195)
        double ldec_fee = 0.020075;
        double ldec_coi = 0, ldec_mfc, ldec_sc, ldec_cost, ldec_ppokok, ldec_ptu, ldec_aph, ldec_ff, ldec_man_celaka, ldec_temp = 0; //, ldec_man[10+1] = {1, 1, 1, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7}
        boolean[] lb_minus = { CommonConst.DUMMY_FALSE, false, false, false };
        boolean lb_rider = false;
        String ls_sy = "", ls_temp; //ls_dpp = ' dari Premi Pokok', ls_sy = ''
        S_biaya lstr;
        double ldec_premi_ppokok_setahun = 0;
        double[] ldec_premi_ppokok_bulan = new double[12 + 1];
        String bonus = "0.00";
        String extraAlokasi = "0.00";
        
//n_riders ln_riders
        double ldec_manfaat, ldec_premi_setahun = 0, ldec_coi24 = 0;
////monthly fix cost
        if( cepr01030102Form.getRightPartOfBusinessCd() == 13 ){
        	 ldec_mfc = 0;
        }else{
        	 ldec_mfc = 27500;
        }        	
        //ldec_mfc = 15000;
        
        if(istr_prop.kurs_id.equals("02")){ 
        	ldec_mfc = 2.75;
        	//ldec_mfc = 2;
        	li_bagi = 1;
        }
        for( int i = 1; i <= 12; i++ )
        {
            ldec_premi_bulan[ i ] = 0;
            ldec_premi_ppokok_bulan[ i ] = 0;
            if( i == 1 ){
            	ldec_premi_bulan[ i ] = istr_prop.premi;
            	ldec_premi_ppokok_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
            }
            if( Hardcode.PAY_MODE_CD_TRIWULANAN == cepr01030102Form.getPaymentModeCd() )
            {
                if( i == 4 || i == 7 || i == 10 ) {
                	ldec_premi_bulan[ i ] = istr_prop.premi;
                	ldec_premi_ppokok_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
                }
            }
            else if( Hardcode.PAY_MODE_CD_SEMESTERAN == cepr01030102Form.getPaymentModeCd() )
            {
                if( i == 7 ) {
                	ldec_premi_bulan[ i ] = istr_prop.premi;
                	ldec_premi_ppokok_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
                }
            }
            else if( Hardcode.PAY_MODE_CD_BULANAN == cepr01030102Form.getPaymentModeCd() )
            {
                ldec_premi_bulan[ i ] = istr_prop.premi;
                ldec_premi_ppokok_bulan[ i ] = LazyConverter.toDouble(cepr01030102Form.getBasePremium());
            }
            ldec_premi_setahun += ldec_premi_bulan[ i ];
            ldec_premi_ppokok_setahun += ldec_premi_ppokok_bulan[ i ];
        }
        //
        for(int i = 1 ; i <= 3 ; i++){
    		ldec_hasil_invest[1][i] = 0;
    		ldec_hasil_invest_batal[1][i] = 0;
    		ldec_hasil_ppokok[i] = 0;
    		ldec_hasil_ptu[i] = 0;
    		ldec_hasil_wdraw[i] = 0;
    		//ldec_pre_pp[i] = 0
    		//ldec_pre_tu[i] = 0
        }
        //
        ldec_manfaat = istr_prop.up;
        //    Biaya Akuisisi & asumsi penarikan (1 tarik, 1-nya tdk tarik)
        lstr = PbFunction.wf_get_biaya_84( istr_prop, command );
        ldec_bak = lstr.bak;
    	ldec_bunga = lstr.bunga;

    	int a, b, c;
        if( "01".equals( istr_prop.kurs_id ) )
        {
            a = 1;
            b = 8;
            c=1;
        }
        else
        {
            a = 9;
            b = 11;/**USD Fund BSIM Products**/
            c=9;
        }
        
        if( cepr01030102Form.getRightPartOfBusinessCd() > 5 && cepr01030102Form.getRightPartOfBusinessCd() != 10 ){        	
        	if( cepr01030102Form.getRightPartOfBusinessCd() == 6  || cepr01030102Form.getRightPartOfBusinessCd() == 11 ){
        		  if( "01".equals( istr_prop.kurs_id ) )
                  {
                      a = 1;
                      b = 5;
                      c = 1;
                  }
                  else
                  {
                      a = 6;
                      b = 7;
                      c = 6;
                  }
        	} /*else{        		
        		  if( "01".equals( istr_prop.kurs_id ) )
                  {
                      a = 1;
                      b = 3;
                      c = 1;
                  }
                  else
                  {
                      a = 4;
                      b = 5;
                      c = 4;
                  }
        	}        	*/	
        } 
    	
        for( int i = 1; i <= 3; i++ )
        {
            ldec_bunga_avg[ i ] = 0;
            for( int j = a; j <= b; j++ )
            {
                ldec_bunga_avg[ i ] += ldec_bunga[ j ][ i ] * ( istr_prop.fund[ j ] / 100 );
                c++;
                if( "01".equals( istr_prop.kurs_id ))
                {
                	/*
                	if( cepr01030102Form.getRightPartOfBusinessCd() == 5 ){
	                	if(c>3 && c<7){
	                		c=c-3;
	                	}
	                	if(j==6){
	                		c=4;
	                	}                	
                	}*/
                	//else
                	if( cepr01030102Form.getRightPartOfBusinessCd() == 6 || cepr01030102Form.getRightPartOfBusinessCd() == 7 || cepr01030102Form.getRightPartOfBusinessCd() == 8 || cepr01030102Form.getRightPartOfBusinessCd() == 11 || cepr01030102Form.getRightPartOfBusinessCd() == 12){
                    	if(c>4){
                    	   c=c-4;
                    }}
                } if( "02".equals( istr_prop.kurs_id ))
                {
                	/*if( cepr01030102Form.getRightPartOfBusinessCd() == 5 ){
                	if(c>6){
                		c=c-2;
                	}}*/
                }
                
            }
        }
        
        boolean cekAdaTarik = false;
        double ldec_tarik_cek = 0;
        for( int i = 1; i <= istr_prop.ins_per; i++ )
        {
        	
        	 if( i <= ArrUtil.upperBound( lstr.tarik ) ) ldec_tarik_cek = lstr.tarik[ i ];
        	
        	 if( ldec_tarik_cek > 0){
        		 cekAdaTarik = true;
        		 break;
        	 }
        }

        double[] np = new double[4];
        double[] celaka = new double[4];
        double[] npbatal = new double[4];
        
        lama_tanggung = 100 - istr_prop.umur_tt;
        int j;
        ldec_fee = 0;
        for( int i = 1; i <= istr_prop.ins_per; i++ )
        {
//        	surrender charge
        	ldec_sc = 0;
        	ldec_wdraw = 0;
        	ldec_topup = 0;
        	
        	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6 
        		|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8 
        		|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10
        		|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X12 ){
        		ldec_coi = of_get_coi_basic_190(i);        		
        	}else if ( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ) {
        		ldec_coi = of_get_coi_190(i);
        	}else{
        		ldec_coi = of_get_coi_basic(i);
        	}

        	if( istr_prop.cb_id == 0 ){
        		for(int k = 1; k <= ArrUtil.upperBound(istr_prop.rider_baru) ; k ++ ){
        			if( istr_prop.rider_baru[k] > 0 ){
        				ldec_temp = of_get_coi_134(k, i);
        				ldec_coi += ldec_temp;
        			}
        		}
        	}
        	if(i <= ArrUtil.upperBound(lstr.topup))	ldec_topup = lstr.topup[i];
        	
        	if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
        		if(i <= 7) ldec_sc = ldec_tarik[i];
        	}else{
        		if(i <= 5) ldec_sc = ldec_tarik[i];
        	}        	
        	//
        	
        	ldec_cost = (ldec_coi + ldec_mfc);
        	
        	for(int k = 1 ; k <= 3; k++){
        		if(i <= ArrUtil.upperBound(lstr.tarik)) ldec_wdraw = lstr.tarik[i];
        		for(int li_bulan = 1 ; li_bulan <= 12 ; li_bulan++){
        			ldec_cost = (ldec_coi + ldec_mfc);
        			ldec_premi_invest = 0;
        			ldec_ppokok = 0;
        			ldec_ptu = 0;
        			ldec_ff = 0;
        			ldec_aph = 0;
        			//if(i <= istr_prop.cuti_premi){
        			if( i <= lama_tanggung ){	
        				ldec_premi_invest = ldec_premi_bulan[li_bulan] * (istr_prop.pct_premi / 100);
        				ldec_premi_invest += ((ldec_premi_bulan[li_bulan] * (100 - istr_prop.pct_premi) / 100));
        				ldec_ppokok = ldec_premi_bulan[li_bulan] * (istr_prop.pct_premi / 100);
        				ldec_ptu = (ldec_premi_bulan[li_bulan] * (100 - istr_prop.pct_premi) / 100) * (1-ldec_bak_tu);        			
        			}
        			
        			if(li_bulan == 1) ldec_ptu += ldec_topup;
        		// Em@il Vito@18/05/2016=> Rumusan SIMAS PRIMELINK & SMiLe AssetPro(Agency)(134-X5/X6)
        		if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6 
        				|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8
        				|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10
        				|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X12){
	       			if(i == 1 && li_bulan == 1){
	       				ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok - ldec_cost ) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12))), 2) ;
	       			}else{
	       				ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_hasil_ppokok[k] - ldec_cost ) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12))), 2) ;
	       			}
	       			ldec_hasil_ptu[k] = (ldec_ptu + ldec_hasil_ptu[k]) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12)));
	       			
        		}else{
        			
        			if(!cekAdaTarik){
        				flag_syarat_bonus_premi[k] = flag_syarat_bonus_premi( i , cepr01030102Form , k , ldec_premi_ppokok_setahun);
                	}
        			
        			//ekstraAlokasi[k] = ekstraAlokasi2(i, k, ldec_premi_ppokok_setahun,ldec_bunga_avg[k]);
        			ekstraAlokasi[k] = ekstraAlokasi3(i, k, ldec_premi_ppokok_setahun,ldec_bunga_avg);
        			//ekstraAlokasi[k] = ekstraAlokasi(i, k, ldec_premi_ppokok_setahun );
        			//ekstraAlokasi[k]  = 0;
        			if( li_bulan == 12){
        				//ekstraAlokasi[k]  = 0;
        				//ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2)  + flag_syarat_bonus_premi[k] + ekstraAlokasi[k];
        			/*	ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2) + ekstraAlokasi[k]+flag_syarat_bonus_premi[k];
        			*/	
        				//ekstraAlokasi[k]  = 0;
        				if(i==1 && k==1){
               			 System.out.println("tahun ke i"+i+" : extra alokasi:"+ekstraAlokasi[k]);
               		 	}
        				//ekstra alokasi
         				//flag_syarat_bonus_premi[k] = 0; 
        				ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2) + ekstraAlokasi[k]+flag_syarat_bonus_premi[k];
        			}else if( li_bulan == 1 ){        	       				
        				ekstraAlokasi[k]  = 0;
        				//ekstra alokasi
         				flag_syarat_bonus_premi[k] = 0; 
        				ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2) + ekstraAlokasi[k]+flag_syarat_bonus_premi[k];
        				
        			}else{
        				  //ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2);
        				ekstraAlokasi[k]  = 0;

         				flag_syarat_bonus_premi[k] = 0;
        				//ekstra alokasi
        				ldec_hasil_ppokok[k] = FormatNumber.round(( ldec_ppokok + ldec_hasil_ppokok[k]) * FormatNumber.round(Math.pow( ( 1 + ldec_bunga_avg[ k ] ), ( ( double ) 1 / 12 )), 15),2) + ekstraAlokasi[k]+flag_syarat_bonus_premi[k];
        			}
        	        	
        			 if(i==1 && k==1)
        						System.out.println("tahun i="+i+" month="+li_bulan+" hasil pokok [ldec_hasil_ppokok]="+ldec_hasil_ppokok[k]);
        			
        			ldec_hasil_ptu[k] = (ldec_ptu + ldec_hasil_ptu[k]) * (Math.pow((1 + ldec_bunga_avg[k]),( ( double ) 1/12)));
        			if( i == 1 && istr_prop.cb_id == 0 ){ 
        				if( li_bulan == 1 ) ldec_cost = 0;
        				if( li_bulan == 2 ) ldec_cost *= 2;
        			}
        			if(i == istr_prop.ins_per && li_bulan == 12) ldec_cost = 0;
        			if( istr_prop.cb_id == 0 ) {
        				if( i <= 2 )ldec_ff = (ldec_hasil_ppokok[k] * 0.03 / 12);
        			}else{        				
        				
        				if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
        					if( i <= 7 ) ldec_ff = (ldec_hasil_ptu[k] * 0 / 12);
            				if( i <= 7 ) ldec_ff += (ldec_hasil_ppokok[k] * 0.065 / 12);         					
        				}else{
        					if( i <= 2 ) ldec_ff = (ldec_hasil_ptu[k] * 0.0125 / 12);
            				if( i <= istr_prop.pay_per ) ldec_ff += (ldec_hasil_ppokok[k] * 0.025 / 12);        					
        				}
        						
        			}
        	    	//premium holiday
        	    	if(i <= 5 && istr_prop.cuti_premi <= 5){ 
        	    		if(i > istr_prop.cuti_premi && istr_prop.cb_id != 0) ldec_aph = ldec_fph[i] / (1 - ldec_fph[i]) * (ldec_cost + ldec_ff);
        	    	}
        	    
        	    	
        	    	ldec_temp = ldec_hasil_ptu[k] - (ldec_cost + ldec_ff + ldec_aph);
        	   	 if(i==1 && k==1){
        			 System.out.println("tahun ke i"+i+" : pokok before:"+ldec_hasil_ppokok[k]+" ldec_temp:+"+ ldec_temp+" biaya_coi"+ldec_cost+" ldec_ff="+ldec_ff+" ldec_aph");
        		 }
        	 	
        	    	if(ldec_temp < 0){
        	    		ldec_hasil_ppokok[k] += ldec_temp;
        	    		ldec_hasil_ptu[k] = 0;
        	    	}else{
        	    		ldec_hasil_ptu[k] = ldec_temp;
        	    	}
        	    	if(ldec_wdraw > 0 && li_bulan == 12 ){
        	    		if(i <= 2){ 
        	    			ldec_wdraw = 0;
        	    		}else{
        	    			if(i <= 5) ldec_wdraw = Math.min(ldec_wdraw, ldec_hasil_ptu[k]);
        	    				ldec_temp = ldec_hasil_ptu[k] - ldec_wdraw;
        	    			if(ldec_temp < 0){
        	    				ldec_hasil_ppokok[k] += ldec_temp;
        	    				ldec_hasil_ptu[k] = 0;
        	    			}else{
        	    				ldec_hasil_ptu[k] = ldec_temp;
        	    			}
        	    		}
        	    		
        	    	}
        		}// end (134-X1X4)    	
        	    	
	        	    if(k == 1)
	            	{
	            		ldec_hasil_invest[1][k] = ldec_hasil_ppokok[k] + ldec_hasil_ptu[k];	            		
	            	}
        		}

       		
       	
        		ldec_hasil_wdraw[k] += ldec_wdraw;
        		ldec_hasil_invest[1][k] = ldec_hasil_ppokok[k] + ldec_hasil_ptu[k];
        		ldec_hasil_invest[ 1 ][ k ] -= ( ldec_hasil_wdraw[k] * ( 1 + ldec_sc ) );
        		 if(i==1 && k==1){
        			 System.out.println("tahun ke i"+i+" hasil investasi = "+ldec_hasil_invest[ 1 ][ k ] +"detail : pokok:"+ldec_hasil_ppokok[k]+" topup:+"+ ldec_hasil_ptu[k]);
        		 }
        		ldec_hasil_invest_batal[1][k] = ldec_hasil_invest[1][k] * ldec_sc;
        		
        		if( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X6
        			|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X7 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X8
        			|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X9 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X10
        			|| cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X11|| cepr01030102Form.getRightPartOfBusinessCd() >= Cepr01040208Mapper.X12 ){
        				if( ldec_hasil_invest[ 1 ][ k ] <= 0 &&  ( i <= 10 ) ){
        					lb_minus[k] = true;	               
        				}
        		}else{
        			if( ldec_hasil_invest[1][k] <= 0 ) lb_minus[k] = true;
        		}
        	}
        

            //j = cepr01030101Form.getInsuredAge() + i;
        	j = istr_prop.umur_tt + i;
            if( i <= 23 || j == 55 || j == 65 || j == 75 || j == 80 || j == 100 )
            {
                for( int k = 1; k <= 3; k++ )
                {
                    np[ k ] = FormatNumber.round( ldec_hasil_invest[ 1 ][ k ] / li_bagi, 0 );
                    celaka[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] + ldec_manfaat ) / li_bagi, 0 );
                                        
                  if (cepr01030102Form.getRightPartOfBusinessCd() >= Cepr01040208Mapper.X13){
//                      // Revisi NISA+NUNG untuk cb.reguler yg dikalikan surrender charge nilai premi pokoknya saja           
                      npbatal[ k ] = FormatNumber.round( ( (ldec_hasil_ppokok[k] * (1 - ldec_sc) ) + ldec_hasil_ptu[k] )/ li_bagi, 0 );
                  }else{
//                  	ldec_hasil_invest_batal[1][k] = ldec_hasil_invest[1][k] * ldec_sc;
//                  	npbatal[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] - ldec_hasil_invest_batal[ 1 ][ k ] ) / li_bagi, 0 );
                  	 npbatal[ k ] = FormatNumber.round( ( ldec_hasil_invest[ 1 ][ k ] * (1 - ldec_sc)  ) / li_bagi, 0 );
                  }
                }

              //  if( i <= istr_prop.cuti_premi )
              if(i <= lama_tanggung )	
                {
                    premiumTotal = editorUtil.convertToStringWithoutCent( ldec_premi_setahun / li_bagi );
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
                    if( "0".equals( topup ) ) topup = "0.00";
                    if( "0".equals( draw ) ) draw = "0.00";
                }
                else
                {
                    topup = "0.00";
                    draw = "0.00";
                }
                
                if(!cekAdaTarik){                	
	                double bonus2;
	                if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd()) )
	                {
	                	 bonus2 = flag_syarat_bonus_premi2( i, cepr01030102Form , ldec_premi_ppokok_setahun);
	                }else{
	                	 bonus2 = flag_syarat_bonus_premi2( i, cepr01030102Form , ldec_premi_ppokok_setahun)/1000;
	                }
	                if( bonus2 == 0 ) {
	                     	//bonus = null;
	                     	//bonus = " - ";
	                     	 bonus = "0.00";
	                }else{
	                     	bonus = editorUtil.convertToStringWithoutCentAndNillIfNegative(bonus2);
	                }                                            
                }                

                if( i<=5 && cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040208Mapper.X13 ){
                	double extraAlokasi2;
                	extraAlokasi2 = (i * 0.01 * ldec_premi_ppokok_setahun)/1000;
              	  
                	extraAlokasi = editorUtil.convertToStringWithoutCentAndNillIfNegative(extraAlokasi2);
	              }else{
	              	  extraAlokasi = "0.00";
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

                map = new HashMap<String, Object>();
                map.put( "yearNo", editorUtil.convertToString( i ) );
                map.put( "insuredAge", editorUtil.convertToString( cepr01030101Form.getInsuredAge() + i ) );
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
                map.put( "benefitHi", benefitHi);
                map.put( "bonus",  bonus );
                map.put( "extraAlokasi",  extraAlokasi );
                mapList.add( map );

            }
        }

        result.setValidityMsg( lb_minus[ 1 ] ? "Maaf, Proposal yang Anda buat Tidak Layak Jual." : "" );
        result.setIllustrationList( mapList );
        return result;

    }
    
    public List<Map<String, Object>> getCommonHeaderSmallRowList3() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getCommonHeaderSmallRowList" );

        List sekaligusRightPartCdList = ArrUtil.toListFromArray( sekaligusRightPartCdArr );
        List berkalaRightPartCdList = ArrUtil.toListFromArray( berkalaRightPartCdArr );

        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        // Fill header content from CommonHeaderDataSource
        result.addAll( source.getInsuredNameMap() );
        result.addAll( source.getInsuredAgeMap() );
        //result.addAll( source.getTermOfContractWithLimitMapInTwoRows( insuredAgeLimit ) );
        result.addAll( source.getTermOfContractWithLimitMap( insuredAgeLimit ) );

              
        Map<String, Object> params;
        if( sekaligusRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {           
            	params = new HashMap<String, Object>();
            	params.put( "label", "Premi Pokok Sekaligus" );
            	params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getBasePremium() ) );
                result.add( params );
                
            	params = new HashMap<String, Object>();
                params.put( "label", "Premi Top Up Sekaligus "  );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getTopUpPremium() )) ;
                result.add( params );
                       
        }
        else if( berkalaRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
        	int caraBayar = cepr01030102Form.getPaymentModeCd();
        	String label = "Tahunan";
        	switch(caraBayar) {
        		case Hardcode.PAY_MODE_CD_TRIWULANAN :
        			label = "Triwulanan";
        			break;
        		case Hardcode.PAY_MODE_CD_SEMESTERAN :
        			label = "Semesteran";
        			break;
        		case Hardcode.PAY_MODE_CD_BULANAN :
        			label = "Bulanan";
        			break;
        		default :
        			label = "Tahunan";
        			break;
        	}
        	
        	params = new HashMap<String, Object>();
            params.put( "label", "Premi Pokok " + label );
            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getBasePremium() ) + " (" + cepr01030102Form.getPremiumCombinationCd().toString() + "%)" );
            result.add( params );
            
            if(!cepr01030102Form.getTopUpPremium().toString().equals("0")){
            	params = new HashMap<String, Object>();
                params.put( "label", "Premi Top Up Berkala " + label );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getTopUpPremium() ) + " (" + ( new BigDecimal( "100" ).subtract( cepr01030102Form.getPremiumCombinationCd() ) ).toString() + "%)" );
                result.add( params );

               /* params = new HashMap<String, Object>();
                params.put( "label", "" );
                params.put( "content", "------------------------" );
                result.add( params );*/

                params = new HashMap<String, Object>();
                params.put( "label", "Total Premi " + label );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) );
                result.add( params );
            }
            
            if(!cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount().toString().equals("0")){
            	params = new HashMap<String, Object>();
                params.put( "label", "Premi Top Up Single" );
                params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030104Form.getTopupDrawVOList().get(0).getTopupAmount() ));
                result.add( params );
            }

          /*  params = new HashMap<String, Object>();
            params.put( "label", "Masa Pembayaran Premi" );
            params.put( "content", editorUtil.convertToString( cepr01030102Form.getPremiumFurloughYear() ) +" Tahun" );
            result.add( params );*/
        }

        result.addAll( source.getTotalSumInsuredMap() );

        return result;
    }
    
}