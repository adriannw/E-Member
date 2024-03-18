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

public class FundUsdSimasSecDynInvestLookup
{
    //IDR
    private static List<OptionVO> ljiFixInvestList;
    private static List<OptionVO> ljiDynamicInvestList;
    private static List<OptionVO> ljiAggresiveInvestList;
    //USD
    private static List<OptionVO> ljiSecureUsdInvestList;
    private static List<OptionVO> ljiDynamicUsdInvestList;
    
  
    
    public static List<OptionVO> getJenisInvestFixSimasList()
    {
    	  if( ljiFixInvestList == null )
          {
    		  ljiFixInvestList = new ArrayList<OptionVO>();

              OptionVO optionVO;
              optionVO = new OptionVO( "35", "Simas Fixed Income Fund" );
              ljiFixInvestList.add( optionVO );                     
          }
          return ljiFixInvestList;
    }
    
    	
  
    
    public static List<OptionVO> getJenisInvestDynamicSimasList()
    {
    	  if( ljiDynamicInvestList == null )
          {
    		  ljiDynamicInvestList = new ArrayList<OptionVO>();

              OptionVO optionVO;  
              optionVO = new OptionVO( "36", "Simas Dynamic Fund" );
              ljiDynamicInvestList.add( optionVO );                     
          }
          return ljiDynamicInvestList;
    }
    
  
    
    public static List<OptionVO> getJenisInvestAggresiveSimasList()
    {
    	  if( ljiAggresiveInvestList == null )
          {
    		  ljiAggresiveInvestList = new ArrayList<OptionVO>();

              OptionVO optionVO;                 
              optionVO = new OptionVO( "37", "Simas Aggresive Fund" );
              ljiAggresiveInvestList.add( optionVO );                     
          }
          return ljiAggresiveInvestList;
    }
    
    
    public static List<OptionVO> getSecureUsdList()
    {
    	  if( ljiSecureUsdInvestList == null )
          {
    		  ljiSecureUsdInvestList = new ArrayList<OptionVO>();

              OptionVO optionVO;
              optionVO = new OptionVO( "45", "Simas Stable Dollar Fund" );
              ljiSecureUsdInvestList.add( optionVO );
                       
          }
          return ljiSecureUsdInvestList;
    }
    	
    public static List<OptionVO> getDynamicUsdList()
    {
    	  if( ljiDynamicUsdInvestList == null )
          {
    		  ljiDynamicUsdInvestList = new ArrayList<OptionVO>();

              OptionVO optionVO;           
              optionVO = new OptionVO( "46", "Simas Balance Dollar Fund" );
              ljiDynamicUsdInvestList.add( optionVO );
                    
          }
          return ljiDynamicUsdInvestList;
    }
   
    
           
    
}
