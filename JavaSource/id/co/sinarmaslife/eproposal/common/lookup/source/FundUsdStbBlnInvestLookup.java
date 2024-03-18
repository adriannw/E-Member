package id.co.sinarmaslife.eproposal.common.lookup.source;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: HcpLookup
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 24, 2007 4:34:42 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.standard.model.vo.OptionVO;

import java.util.List;
import java.util.ArrayList;

public class FundUsdStbBlnInvestLookup
{
    
    private static List<OptionVO> ljiSecureUsdInvestList;
    private static List<OptionVO> ljiDynamicUsdInvestList;
    private static List<OptionVO> ljiAggresiveUsdInvestList;/**USD Fund BSIM Products**/
    
    private static List<OptionVO> ljiScrUsdInvestList;
    private static List<OptionVO> ljiDymUsdInvestList;
    private static List<OptionVO> ljiAgrUsdInvestList;/**USD Fund BSIM Products**/
    
    public static List<OptionVO> getJenisInvestSecureUsdList()
    {
    	  if( ljiSecureUsdInvestList == null )
          {
    		  ljiSecureUsdInvestList = new ArrayList<OptionVO>();

              OptionVO optionVO;
              optionVO = new OptionVO( "", "-" );
              ljiSecureUsdInvestList.add( optionVO );
              optionVO = new OptionVO( "04", "Excellink Stable Dollar Fund" );
              ljiSecureUsdInvestList.add( optionVO );
              optionVO = new OptionVO( "45", "Simas Stable Dollar Fund" );
              ljiSecureUsdInvestList.add( optionVO );                     
          }
          return ljiSecureUsdInvestList;
    }
    	
    public static List<OptionVO> getJenisInvestDynamicUsdList()
    {
    	  if( ljiDynamicUsdInvestList == null )
          {
    		  ljiDynamicUsdInvestList = new ArrayList<OptionVO>();

              OptionVO optionVO;
              optionVO = new OptionVO( "", "-" );
              ljiDynamicUsdInvestList.add( optionVO );
              optionVO = new OptionVO( "05", "Excellink Balance Dollar Fund" );
              ljiDynamicUsdInvestList.add( optionVO );
              optionVO = new OptionVO( "46", "Simas Balance Dollar Fund" );
              ljiDynamicUsdInvestList.add( optionVO );                     
          }
          return ljiDynamicUsdInvestList;
    }
    /**USD Fund BSIM Products**/
    public static List<OptionVO> getJenisGlobalEquityUsdList()
    {
    	  if( ljiAggresiveUsdInvestList == null )
          {
    		  ljiAggresiveUsdInvestList = new ArrayList<OptionVO>();

              OptionVO optionVO;
              optionVO = new OptionVO( "", "-" );
              ljiAggresiveUsdInvestList.add( optionVO );
              optionVO = new OptionVO( "67", "Excellink Global Equity Dollar" );
              ljiAggresiveUsdInvestList.add( optionVO );                   
          }
          return ljiAggresiveUsdInvestList;
    }
    
    public static List<OptionVO> getJenisGlobalAggresiveUsdList()
    {
    	  if( ljiAgrUsdInvestList == null )
          {
    		  ljiAgrUsdInvestList = new ArrayList<OptionVO>();

              OptionVO optionVO;
              optionVO = new OptionVO( "", "-" );
              ljiAgrUsdInvestList.add( optionVO );
              optionVO = new OptionVO( "66", "Excellink Global Aggresive Dollar" );
              ljiAgrUsdInvestList.add( optionVO );                   
          }
          return ljiAgrUsdInvestList;
    }
    
    public static List<OptionVO> getSecureUsdList()
    {
    	  if( ljiScrUsdInvestList == null )
          {
    		  ljiScrUsdInvestList = new ArrayList<OptionVO>();

              OptionVO optionVO;
              optionVO = new OptionVO( "", "-" );
              ljiScrUsdInvestList.add( optionVO );
              optionVO = new OptionVO( "044", "Excellink Secure Dollar Income Fund" );
              ljiScrUsdInvestList.add( optionVO );
                       
          }
          return ljiScrUsdInvestList;
    }
    	
    public static List<OptionVO> getDynamicUsdList()
    {
    	  if( ljiDymUsdInvestList == null )
          {
    		  ljiDymUsdInvestList = new ArrayList<OptionVO>();

              OptionVO optionVO;
              optionVO = new OptionVO( "", "-" );
              ljiDymUsdInvestList.add( optionVO );
              optionVO = new OptionVO( "055", "Excellink Dynamic Dollar Fund" );
              ljiDymUsdInvestList.add( optionVO );
                    
          }
          return ljiDymUsdInvestList;
    }
   
    
}
