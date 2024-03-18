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

public class FundExcellinkFixDynAgrInvestLookup
{
    
    private static List<OptionVO> ljiFixInvestList;
    private static List<OptionVO> ljiDynamicInvestList;
    private static List<OptionVO> ljiAggresiveInvestList;
    
    
    
    public static List<OptionVO> getJenisInvestFixExcellinkList()
    {
    	  if( ljiFixInvestList == null )
          {
    		  ljiFixInvestList = new ArrayList<OptionVO>();

              OptionVO optionVO;
              optionVO = new OptionVO( "", "-" );
              ljiFixInvestList.add( optionVO );
              optionVO = new OptionVO( "01", "Excellink Fixed Income Fund" );
              ljiFixInvestList.add( optionVO );
                  
          }
          return ljiFixInvestList;
    }
        	
    public static List<OptionVO> getJenisInvestDynamicExcellinkList()
    {
    	  if( ljiDynamicInvestList == null )
          {
    		  ljiDynamicInvestList = new ArrayList<OptionVO>();

              OptionVO optionVO;
              optionVO = new OptionVO( "", "-" );
              ljiDynamicInvestList.add( optionVO );
              optionVO = new OptionVO( "02", "Excellink Dynamic Fund" );
              ljiDynamicInvestList.add( optionVO );
                            
          }
          return ljiDynamicInvestList;
    }
    
 
    public static List<OptionVO> getJenisInvestAggresiveExcellinkList()
    {
    	  if( ljiAggresiveInvestList == null )
          {
    		  ljiAggresiveInvestList = new ArrayList<OptionVO>();

              OptionVO optionVO;
              optionVO = new OptionVO( "", "-" );
              ljiAggresiveInvestList.add( optionVO );
              optionVO = new OptionVO( "03", "Excellink Aggresive Fund" );
              ljiAggresiveInvestList.add( optionVO );
                     
          }
          return ljiAggresiveInvestList;
    }
    
    
    
   
    
}
