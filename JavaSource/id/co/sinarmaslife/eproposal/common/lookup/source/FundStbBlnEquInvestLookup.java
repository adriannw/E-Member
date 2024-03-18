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

public class FundStbBlnEquInvestLookup
{        
    private static List<OptionVO> ljiStabileInvestList;
    private static List<OptionVO> ljiBalanceInvestList;
    private static List<OptionVO> ljiEquityInvestList;
        
    public static List<OptionVO> getJnsInvestStabileList()
    {
    	  if( ljiStabileInvestList == null )
          {
    		  ljiStabileInvestList = new ArrayList<OptionVO>();

              OptionVO optionVO;
              optionVO = new OptionVO( "", "-" );
              ljiStabileInvestList.add( optionVO );
              optionVO = new OptionVO( "47", "Excellink Stable Fund" );
              ljiStabileInvestList.add( optionVO );
              optionVO = new OptionVO( "42", "Simas Stable Fund" );
              ljiStabileInvestList.add( optionVO );                     
          }
          return ljiStabileInvestList;
    }
    	
    public static List<OptionVO> getJnsInvestBalanceList()
    {
    	  if( ljiBalanceInvestList == null )
          {
    		  ljiBalanceInvestList = new ArrayList<OptionVO>();

              OptionVO optionVO;
              optionVO = new OptionVO( "", "-" );
              ljiBalanceInvestList.add( optionVO );
              optionVO = new OptionVO( "48", "Excellink Balance Fund" );
              ljiBalanceInvestList.add( optionVO );
              optionVO = new OptionVO( "43", "Simas Balance Fund" );
              ljiBalanceInvestList.add( optionVO );                     
          }
          return ljiBalanceInvestList;
    }
    
    public static List<OptionVO> getJnsInvestEquityList()
    {
    	  if( ljiEquityInvestList == null )
          {
    		  ljiEquityInvestList = new ArrayList<OptionVO>();

              OptionVO optionVO;
              optionVO = new OptionVO( "", "-" );
              ljiEquityInvestList.add( optionVO );
              optionVO = new OptionVO( "49", "Excellink Equity Fund" );
              ljiEquityInvestList.add( optionVO );
              optionVO = new OptionVO( "44", "Simas Equity Fund" );
              ljiEquityInvestList.add( optionVO );                     
          }
          return ljiEquityInvestList;
    }
    
}
