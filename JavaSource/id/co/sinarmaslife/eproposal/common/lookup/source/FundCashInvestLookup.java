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

public class FundCashInvestLookup
{
        
    private static List<OptionVO> ljiCashFundInvestList;
    
    private static List<OptionVO> ljiExcellinkEquityInvestList;
   
    
    public static List<OptionVO> getJenisInvestCashFundInvestList()
    {
    	  if( ljiCashFundInvestList == null )
          {
    		  ljiCashFundInvestList = new ArrayList<OptionVO>();

              OptionVO optionVO;
              optionVO = new OptionVO( "", "-" );
              ljiCashFundInvestList.add( optionVO );
              optionVO = new OptionVO( "61", "Excellink Cash Fund" );
              ljiCashFundInvestList.add( optionVO );
                     
          }
          return ljiCashFundInvestList;
    }
    
    
    public static List<OptionVO> getJenisInvestExcellinkEquityInvestList()
    {
    	  if( ljiExcellinkEquityInvestList == null )
          {
    		  ljiExcellinkEquityInvestList = new ArrayList<OptionVO>();

              OptionVO optionVO;
              optionVO = new OptionVO( "", "-" );
              ljiExcellinkEquityInvestList.add( optionVO );
              optionVO = new OptionVO( "63", "Excellink Equity Bakti Peduli" );
              ljiExcellinkEquityInvestList.add( optionVO );
                     
          }
          return ljiExcellinkEquityInvestList;
    }
    
   
    
}
