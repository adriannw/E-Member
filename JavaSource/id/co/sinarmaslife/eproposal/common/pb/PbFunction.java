package id.co.sinarmaslife.eproposal.common.pb;

/**********************************************************************
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: PbFunction
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Apr 22, 2008 11:11:24 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.model.pb.Istr_prop;
import id.co.sinarmaslife.eproposal.model.pb.S_biaya;
import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030104Form;
import id.co.sinarmaslife.eproposal.web.Cepr01050000Form;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.DateUtil;

import java.util.*;
import java.math.BigDecimal;

public class PbFunction
{
    public static S_biaya wf_get_biaya_84( Istr_prop istr_prop, Object command )
    {
        S_biaya lstr = new S_biaya();

        switch( istr_prop.bisnis_no )
        {
            case 1:  //sekaligus'
                if( istr_prop.bisnis_id == 77 || istr_prop.bisnis_id == 87 )
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.05, 0, 0, 0, 0, 0};
                else if( istr_prop.bisnis_id == 84 )
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.1, 0, 0, 0, 0, 0};
                else if( istr_prop.bisnis_id == 97 ) // sekaligus
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.175, 0, 0, 0, 0, 0};
                else if( istr_prop.bisnis_id == 107 ) // sekaligus
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.19, 0, 0, 0, 0, 0};
                else if( istr_prop.bisnis_id == 115 || istr_prop.bisnis_id == 152 ) // sekaligus
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.025, 0, 0, 0, 0, 0};
                else if( istr_prop.bisnis_id == 116 || istr_prop.bisnis_id == 121 || istr_prop.bisnis_id == 153 || istr_prop.bisnis_id == 140 || istr_prop.bisnis_id == 199)
                    {lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.05, 0, 0, 0, 0, 0};
                if( istr_prop.bisnis_id == 116 && istr_prop.bisnis_no == 6 ) {lstr.bak =  new double[]{CommonConst.DUMMY_ZERO,0, 0, 0, 0, 0, 0};}}
                else if( istr_prop.bisnis_id == 119 ) // sekaligus
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.12, 0, 0, 0, 0, 0};
                else if( istr_prop.bisnis_id == 159 || istr_prop.bisnis_id == 160 ) // sekaligus
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.05, 0, 0, 0, 0, 0 };
                else if( istr_prop.bisnis_id == 162 || istr_prop.bisnis_id == 190 ) // sekaligus
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.05, 0, 0, 0, 0, 0};
                else if( istr_prop.bisnis_id == 165 ) // sekaligus
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.1, 0, 0, 0, 0, 0};
                else if( istr_prop.bisnis_id == 200 )  // link karywan
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.05, 0, 0, 0, 0, 0};
                else if( istr_prop.bisnis_id ==  213 || istr_prop.bisnis_id ==  216 ) 
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.5, 0.1, 0, 0, 0, -0.05};
                else if( istr_prop.bisnis_id == 215 )
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0, 0 };
                else if( istr_prop.bisnis_id ==  217 || istr_prop.bisnis_id ==  218 ) 
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.7, 0.4, 0.15, 0.15, 0.15, 0};
                else if( istr_prop.bisnis_id ==  220 || istr_prop.bisnis_id ==  224 ) 
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.5, 0, 0, 0, 0, 0};
                break;
            case 2:  //3th
                if( istr_prop.bisnis_id == 77 )
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.3, 0.05, 0.05, 0, 0, 0};
                else if( istr_prop.bisnis_id == 84 )
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.4, 0.05, 0.05, 0, 0, 0};
                else if( istr_prop.bisnis_id == 97 )  //97 5 th
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.7, 0.05, 0.05, 0.05, 0.05, 0 };
                else if( istr_prop.bisnis_id == 107 )  //107 5 th
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.715, 0.065, 0.065, 0.065, 0.065, 0 };
                else if( istr_prop.bisnis_id == 116 || istr_prop.bisnis_id == 153 || istr_prop.bisnis_id == 140 || istr_prop.bisnis_id == 199 )  // reguler
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.7, 0.2, 0.05, 0.05, 0.05, 0 };
                else if( istr_prop.bisnis_id == 119 )  //
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.5, 0.05, 0.05, 0, 0, 0 };
                else if( istr_prop.bisnis_id == 159 || istr_prop.bisnis_id == 160 )
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.9, 0.45, 0.15, 0.15, 0.15, 0 };
                else if( istr_prop.bisnis_id == 162 ) // reguler
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 1, 0.55, 0.35, 0.15, 0.05, 0 };
                else if( istr_prop.bisnis_id == 190 ) // reguler
        			lstr.bak  = new double[]{ CommonConst.DUMMY_ZERO, 1, 0.6, 0.15, 0.15, 0.15, 0 };
                else if( istr_prop.bisnis_id == 138 )  // link karywan
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0, 0 };
                else if( istr_prop.bisnis_id == 200 )// ekalink 80 karyawan 115-3
                 	lstr.bak  = new double[]{ CommonConst.DUMMY_ZERO, 1, 0.6, 0.15, 0.15, 0.15, 0 };
                else if( istr_prop.bisnis_id ==  213 ) // magnalink karywan
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.5, 0.1, 0, 0, 0, -0.05};
                else if( istr_prop.bisnis_id == 215 )
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0, 0 };
                else if( istr_prop.bisnis_id ==  220 || istr_prop.bisnis_id == 224 ) 
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.5, 0, 0, 0, 0, 0};
                break;
            case 3:  //6th
                if( istr_prop.bisnis_id == 77 )
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.5, 0.05, 0.05, 0.05, 0.05, 0.05 };
                else if( istr_prop.bisnis_id == 84 )
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.6, 0.05, 0.05, 0.05, 0.05, 0.05 };
                else if( istr_prop.bisnis_id == 119 )
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.7, 0.05, 0.05, 0.05, 0.05, 0.05 };
                else if( istr_prop.bisnis_id == 159 )
                	 lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.9, 0.45, 0.15, 0.15, 0.15, 0 };
                else if( istr_prop.bisnis_id == 115 )// ekalink 80 karyawan 115-3
                	 lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0, 0 };
                else if( istr_prop.bisnis_id == 215 )
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0, 0 };
                else if( istr_prop.bisnis_id ==  220 || istr_prop.bisnis_id == 224 )
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.5, 0, 0, 0, 0, 0};
                else if( istr_prop.bisnis_id ==  116 || istr_prop.bisnis_id == 153 || istr_prop.bisnis_id ==  118) 
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.05, 0, 0, 0, 0, 0};
                break;
            case 4:
            	if( istr_prop.bisnis_id ==  116 || istr_prop.bisnis_id == 153 || istr_prop.bisnis_id ==  118) 
                 	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.7, 0.2, 0.05, 0.05, 0.05, 0};
            	else if( istr_prop.bisnis_id == 215 )
                  	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0, 0 };
            	else
            		lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.5, 0, 0, 0, 0, 0};
                break;            	
            case 5:  //sekaligus'
                if( istr_prop.bisnis_id == 162 )  // sekaligus
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.05, 0, 0, 0,	 0, 0 };
                else if( istr_prop.bisnis_id == 134 )
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0, 0 };
                break;
            case 7:  //sekaligus'
                if( istr_prop.bisnis_id == 162 )  // sekaligus
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.05, 0, 0, 0,	 0, 0 };               
                else if( istr_prop.bisnis_id == 190 )  // sekaligus
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.05, 0, 0, 0, 0, 0};
                else if( istr_prop.bisnis_id == 134 )
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0, 0 };     
                break;
            case 6:
                if( istr_prop.bisnis_id == 162 )  // reguler
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 1, 0.55, 0.35, 0.15, 0.05, 0 };
                else if( istr_prop.bisnis_id == 134 )
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0, 0 };     
                break;
            case 8:
                if( istr_prop.bisnis_id == 162 )  // reguler
                    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 1, 0.55, 0.35, 0.15, 0.05, 0 };
                else if( istr_prop.bisnis_id == 190 )  // sekaligus
                	lstr.bak  = new double[]{ CommonConst.DUMMY_ZERO, 1, 0.6, 0.15, 0.15, 0.15, 0 };
                else if( istr_prop.bisnis_id == 134 )
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0, 0 }; 
                break;
            case 9:             
                if( istr_prop.bisnis_id == 134 )
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0, 0 }; 
                break;
            case 10:  
                if( istr_prop.bisnis_id == 134 )
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0, 0 }; 
                break;
            case 11:  
                if( istr_prop.bisnis_id == 134 )
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0, 0 }; 
                break;
            case 12:  
                if( istr_prop.bisnis_id == 134 )
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0, 0, 0, 0, 0, 0 }; 
                break;
            case 13:  
                if( istr_prop.bisnis_id == 134 )
                	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, -3, 0, 0, 0, 0, 0 }; 
                break;
            default:break;
        }
//
        //Case: SMiLe Link 99 (190-X3/X4) OR SMiLe Link 99 SYARIAH (200-X3/X4)
        if ( (istr_prop.bisnis_id == 190 || istr_prop.bisnis_id == 200) && (istr_prop.bisnis_no == 3 || istr_prop.bisnis_no == 4 || istr_prop.bisnis_no == 5 || istr_prop.bisnis_no == 6 || istr_prop.bisnis_no == 9 ) ){
        	if(istr_prop.bisnis_no == 3 || istr_prop.bisnis_no == 5 ){ 
        		lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.05, 0, 0, 0, 0, 0};
        	}
        	else if(istr_prop.bisnis_no == 4 || istr_prop.bisnis_no == 6 || istr_prop.bisnis_no == 9 ){
        		lstr.bak  = new double[]{ CommonConst.DUMMY_ZERO, 1, 0.6, 0.15, 0.15, 0.15, 0 };
        	}
        }               
        
        if( istr_prop.bisnis_id == 120 || istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 128 || istr_prop.bisnis_id == 129 || istr_prop.bisnis_id == 141 || istr_prop.bisnis_id == 202)  //cerdas 10
            lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.8, 0.15, 0, 0, 0, 0 };
    		if ( istr_prop.bisnis_id == 120 && ( istr_prop.bisnis_no == 3 || istr_prop.bisnis_no == 6 || istr_prop.bisnis_no == 9 || istr_prop.bisnis_no == 12 || istr_prop.bisnis_no == 21	|| istr_prop.bisnis_no == 24))  lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.05, 0, 0, 0, 0, 0 };
    		if( istr_prop.bisnis_id == 202 && ( istr_prop.bisnis_no == 3 || istr_prop.bisnis_no == 6) ) lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.05, 0, 0, 0, 0, 0 };	
        if( istr_prop.bisnis_id ==  121 ){ //cerdas family
        	lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 1, 0.6, 0.3, 0, 0, 0 };
        	if( istr_prop.bisnis_no == 9 )  lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.8, 0.15, 0, 0, 0, 0 };
        }
//        if( istr_prop.bisnis_id == 191 )  //rencana cerdas
//            lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.5, 0.3, 0, 0, 0, 0 };
//
        if( (istr_prop.bisnis_id == 190 && ( istr_prop.bisnis_no <= 2  || istr_prop.bisnis_no == 5 || istr_prop.bisnis_no == 6 )) 
      		  || (istr_prop.bisnis_id == 134 && (istr_prop.bisnis_no == 6 || istr_prop.bisnis_no == 11 )) 
      		  || (istr_prop.bisnis_id == 140 && (istr_prop.bisnis_no == 1 || istr_prop.bisnis_no == 2) )
        	  || (istr_prop.bisnis_id == 120 && (istr_prop.bisnis_no >= 19 && istr_prop.bisnis_no <= 21)) ){
      		  //Nilai Aasumsi hasil Investasi
      	      //fix
      	      lstr.bunga[1][1] = 0.04; //0.06 10 may 2013 .audit
      	      lstr.bunga[1][2] = 0.08;
      	      lstr.bunga[1][3] = 0.10;
      	      //dyn
      	      lstr.bunga[2][1] = 0.05;
      	      //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[2, 1] = 0.13
      	      lstr.bunga[2][2] = 0.09; //0.15 10 may 2013 .audit //0.18
      	      lstr.bunga[2][3] = 0.13; //0.2 10 may 2013 .audit//0.25
      	      //agr
      	      lstr.bunga[3][1] = 0.05;
      	      //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[3, 1] = 0.13
      	      lstr.bunga[3][2] = 0.10; //0.2 10 may 2013 .audit //0.22
      	      lstr.bunga[3][3] = 0.16; //0.3 10 may 2013 .audit//0.32
      	      //cashfund
      	      lstr.bunga[4][1] = 0.03;
      	      lstr.bunga[4][2] = 0.04;
      	      lstr.bunga[4][3] = 0.06;
      	      //Excellink Equity Bakti Peduli
      	      lstr.bunga[5][1] = 0.05;
      	      lstr.bunga[5][2] = 0.10;
      	      lstr.bunga[5][3] = 0.16;
      	      
      	      //sec$
      	      lstr.bunga[6][1] = 0.03;
      	      lstr.bunga[6][2] = 0.05;
      	      lstr.bunga[6][3] = 0.07;
      	      //dyn$
      	      lstr.bunga[7][1] = 0.05;
      	      lstr.bunga[7][2] = 0.08;
      	      lstr.bunga[7][3] = 0.10;      	  
            }
        else if(( istr_prop.bisnis_id == 224 && (istr_prop.bisnis_no == 1 || istr_prop.bisnis_no == 2))
      		  || (istr_prop.bisnis_id == 190 && (istr_prop.bisnis_no >= 5 && istr_prop.bisnis_no <= 6)) 
      		  || (istr_prop.bisnis_id == 134 && (istr_prop.bisnis_no == 6 || istr_prop.bisnis_no == 11)) 
      		  || (istr_prop.bisnis_id == 202 && (istr_prop.bisnis_no >= 4 && istr_prop.bisnis_no <= 6)) 
      		  || (istr_prop.bisnis_id == 215 && istr_prop.bisnis_no <= 3 )
      		  || ( istr_prop.bisnis_id == 218 && istr_prop.bisnis_no == 1 )
    		  || ( istr_prop.bisnis_id == 153 && (istr_prop.bisnis_no == 3 || istr_prop.bisnis_no == 4) )
    		  || (istr_prop.bisnis_id == 140 && (istr_prop.bisnis_no == 1 || istr_prop.bisnis_no == 2))
    		  || (istr_prop.bisnis_id == 199 && (istr_prop.bisnis_no == 1 || istr_prop.bisnis_no == 2))
    		  || (istr_prop.bisnis_id == 200 && istr_prop.bisnis_no <= 4) )  { 
    	  //Nilai Aasumsi hasil Investasi
	      //fix
	      lstr.bunga[1][1] = 0.04; //0.06 10 may 2013 .audit
	      lstr.bunga[1][2] = 0.08;
	      lstr.bunga[1][3] = 0.10;
	      //dyn
	      lstr.bunga[2][1] = 0.05;
	      //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[2, 1] = 0.13
	      lstr.bunga[2][2] = 0.09; //0.15 10 may 2013 .audit //0.18
	      lstr.bunga[2][3] = 0.13; //0.2 10 may 2013 .audit//0.25
	      //agr
	      lstr.bunga[3][1] = 0.05;
	      //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[3, 1] = 0.13
	      lstr.bunga[3][2] = 0.10; //0.2 10 may 2013 .audit //0.22
	      lstr.bunga[3][3] = 0.16; //0.3 10 may 2013 .audit//0.32
	      //cashfund
	      lstr.bunga[4][1] = 0.03;
	      lstr.bunga[4][2] = 0.04;
	      lstr.bunga[4][3] = 0.06;
	      //sec$
	      lstr.bunga[5][1] = 0.03;
	      lstr.bunga[5][2] = 0.05;
	      lstr.bunga[5][3] = 0.07;
	      //dyn$
	      lstr.bunga[6][1] = 0.05;
	      lstr.bunga[6][2] = 0.08;
	      lstr.bunga[6][3] = 0.10;  
      }
      else if( (istr_prop.bisnis_id == 134 && ( istr_prop.bisnis_no == 10 || istr_prop.bisnis_no >= 12 || istr_prop.bisnis_no == 7 || istr_prop.bisnis_no == 8 ))
    		  || (istr_prop.bisnis_id == 220 && ( istr_prop.bisnis_no == 4 || istr_prop.bisnis_no == 1 || istr_prop.bisnis_no == 2) ) 
    		  || (istr_prop.bisnis_id == 190 && ( istr_prop.bisnis_no == 7 || istr_prop.bisnis_no == 8 || istr_prop.bisnis_no == 3 || istr_prop.bisnis_no == 4 ) ) 
    	  	  || (istr_prop.bisnis_id == 116 || istr_prop.bisnis_id == 118 && (istr_prop.bisnis_no == 3 || istr_prop.bisnis_no == 4))
    	  	  || (istr_prop.bisnis_id == 217 && (istr_prop.bisnis_no == 1))
    	  	  || (istr_prop.bisnis_id == 120 && (istr_prop.bisnis_no == 22 || istr_prop.bisnis_no == 23 || istr_prop.bisnis_no == 24)) //IGA - PROJECT SIMPOL
    		  ) {
    	  //Nilai Aasumsi hasil Investasi
	      //fix
	      lstr.bunga[1][1] = 0.04; //0.06 10 may 2013 .audit
	      lstr.bunga[1][2] = 0.08;
	      lstr.bunga[1][3] = 0.10;
	      //dyn
	      lstr.bunga[2][1] = 0.05;
	      //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[2, 1] = 0.13
	      lstr.bunga[2][2] = 0.09; //0.15 10 may 2013 .audit //0.18
	      lstr.bunga[2][3] = 0.13; //0.2 10 may 2013 .audit//0.25
	      //agr
	      lstr.bunga[3][1] = 0.05;
	      //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[3, 1] = 0.13
	      lstr.bunga[3][2] = 0.10; //0.2 10 may 2013 .audit //0.22
	      lstr.bunga[3][3] = 0.16; //0.3 10 may 2013 .audit//0.32
	      //fix
	      lstr.bunga[4][1] = 0.04; //0.06 10 may 2013 .audit
	      lstr.bunga[4][2] = 0.08;
	      lstr.bunga[4][3] = 0.10;
	      //dyn
	      lstr.bunga[5][1] = 0.05;
	      //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[2, 1] = 0.13
	      lstr.bunga[5][2] = 0.09; //0.15 10 may 2013 .audit //0.18
	      lstr.bunga[5][3] = 0.13; //0.2 10 may 2013 .audit//0.25
	      //agr
	      lstr.bunga[6][1] = 0.05;
	      //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[3, 1] = 0.13
	      lstr.bunga[6][2] = 0.10; //0.2 10 may 2013 .audit //0.22
	      lstr.bunga[6][3] = 0.16; //0.3 10 may 2013 .audit//0.32
	      	      
	      //cashfund
	      lstr.bunga[7][1] = 0.03;
	      lstr.bunga[7][2] = 0.04;
	      lstr.bunga[7][3] = 0.06;
	      
	      //Excellink Equity Bakti Peduli
	      lstr.bunga[8][1] = 0.05;
	      lstr.bunga[8][2] = 0.10;
	      lstr.bunga[8][3] = 0.16;
	      
	      //sec$
	      lstr.bunga[9][1] = 0.03;
	      lstr.bunga[9][2] = 0.05;
	      lstr.bunga[9][3] = 0.07;
	      //dyn$
	      lstr.bunga[10][1] = 0.05;
	      lstr.bunga[10][2] = 0.08;
	      lstr.bunga[10][3] = 0.10;
      }else if( istr_prop.bisnis_id == 134 && istr_prop.bisnis_no == 5 /**USD Fund BSIM Products**/
    		  ) {
    	  //Nilai Aasumsi hasil Investasi
	      //fix
	      lstr.bunga[1][1] = 0.04; //0.06 10 may 2013 .audit
	      lstr.bunga[1][2] = 0.08;
	      lstr.bunga[1][3] = 0.10;
	      //dyn
	      lstr.bunga[2][1] = 0.05;
	      //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[2, 1] = 0.13
	      lstr.bunga[2][2] = 0.09; //0.15 10 may 2013 .audit //0.18
	      lstr.bunga[2][3] = 0.13; //0.2 10 may 2013 .audit//0.25
	      //agr
	      lstr.bunga[3][1] = 0.05;
	      //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[3, 1] = 0.13
	      lstr.bunga[3][2] = 0.10; //0.2 10 may 2013 .audit //0.22
	      lstr.bunga[3][3] = 0.16; //0.3 10 may 2013 .audit//0.32
	      //fix
	      lstr.bunga[4][1] = 0.04; //0.06 10 may 2013 .audit
	      lstr.bunga[4][2] = 0.08;
	      lstr.bunga[4][3] = 0.10;
	      //dyn
	      lstr.bunga[5][1] = 0.05;
	      //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[2, 1] = 0.13
	      lstr.bunga[5][2] = 0.09; //0.15 10 may 2013 .audit //0.18
	      lstr.bunga[5][3] = 0.13; //0.2 10 may 2013 .audit//0.25
	      //agr
	      lstr.bunga[6][1] = 0.05;
	      //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[3, 1] = 0.13
	      lstr.bunga[6][2] = 0.10; //0.2 10 may 2013 .audit //0.22
	      lstr.bunga[6][3] = 0.16; //0.3 10 may 2013 .audit//0.32
	      	      
	      //cashfund
	      lstr.bunga[7][1] = 0.03;
	      lstr.bunga[7][2] = 0.04;
	      lstr.bunga[7][3] = 0.06;
	      
	      //Excellink Equity Bakti Peduli
	      lstr.bunga[8][1] = 0.05;
	      lstr.bunga[8][2] = 0.10;
	      lstr.bunga[8][3] = 0.16;
	      
	      //sec$
	      lstr.bunga[9][1] = 0.03;
	      lstr.bunga[9][2] = 0.05;
	      lstr.bunga[9][3] = 0.07;
	      //dyn$
	      lstr.bunga[10][1] = 0.05;
	      lstr.bunga[10][2] = 0.08;
	      lstr.bunga[10][3] = 0.10;
	      //agr$  /**USD Fund BSIM Products**/
	      lstr.bunga[11][1] = 0.04;
	      lstr.bunga[11][2] = 0.08;
	      lstr.bunga[11][3] = 0.12;
	      
      }
      else if( (istr_prop.bisnis_id == 215 && istr_prop.bisnis_no == 4) || (istr_prop.bisnis_id == 224 && istr_prop.bisnis_no == 3)) 
      	{
        	  //Nilai Aasumsi hasil Investasi
    	      //fix
    	      lstr.bunga[1][1] = 0.04; //0.06 10 may 2013 .audit
    	      lstr.bunga[1][2] = 0.08;
    	      lstr.bunga[1][3] = 0.10;
    	      //dyn
    	      lstr.bunga[2][1] = 0.05;
    	      //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[2, 1] = 0.13
    	      lstr.bunga[2][2] = 0.09; //0.15 10 may 2013 .audit //0.18
    	      lstr.bunga[2][3] = 0.13; //0.2 10 may 2013 .audit//0.25
    	      //agr
    	      lstr.bunga[3][1] = 0.05;
    	      //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[3, 1] = 0.13
    	      lstr.bunga[3][2] = 0.10; //0.2 10 may 2013 .audit //0.22
    	      lstr.bunga[3][3] = 0.16; //0.3 10 may 2013 .audit//0.32
    	      //fix
    	      lstr.bunga[4][1] = 0.04; //0.06 10 may 2013 .audit
    	      lstr.bunga[4][2] = 0.08;
    	      lstr.bunga[4][3] = 0.10;
    	      //dyn
    	      lstr.bunga[5][1] = 0.05;
    	      //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[2, 1] = 0.13
    	      lstr.bunga[5][2] = 0.09; //0.15 10 may 2013 .audit //0.18
    	      lstr.bunga[5][3] = 0.13; //0.2 10 may 2013 .audit//0.25
    	      //agr
    	      lstr.bunga[6][1] = 0.05;
    	      //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[3, 1] = 0.13
    	      lstr.bunga[6][2] = 0.10; //0.2 10 may 2013 .audit //0.22
    	      lstr.bunga[6][3] = 0.1; //0.3 10 may 2013 .audit//0.32
    	      	      
    	      //cashfund
    	      lstr.bunga[7][1] = 0.03;
    	      lstr.bunga[7][2] = 0.04;
    	      lstr.bunga[7][3] = 0.06;
    	          	      
    	      //sec$
    	      lstr.bunga[9][1] = 0.03;
    	      lstr.bunga[9][2] = 0.05;
    	      lstr.bunga[9][3] = 0.07;
    	      //dyn$
    	      lstr.bunga[10][1] = 0.05;
    	      lstr.bunga[10][2] = 0.08;
    	      lstr.bunga[10][3] = 0.10;
      	}
      else if (istr_prop.bisnis_id == 213 /**USD Fund BSIM Products**/){
	      //Nilai Aasumsi hasil Investasi
	      //fix
	      lstr.bunga[1][1] = 0.04; //0.06 10 may 2013 .audit
	      lstr.bunga[1][2] = 0.08;
	      lstr.bunga[1][3] = 0.10;
	      //dyn
	      lstr.bunga[2][1] = 0.05;
	      //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[2, 1] = 0.13
	      lstr.bunga[2][2] = 0.09; //0.15 10 may 2013 .audit //0.18
	      lstr.bunga[2][3] = 0.13; //0.2 10 may 2013 .audit//0.25
	      //agr
	      lstr.bunga[3][1] = 0.05;
	      //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[3, 1] = 0.13
	      lstr.bunga[3][2] = 0.10; //0.2 10 may 2013 .audit //0.22
	      lstr.bunga[3][3] = 0.16; //0.3 10 may 2013 .audit//0.32
	      
	      //sec$
	      lstr.bunga[4][1] = 0.03;
	      lstr.bunga[4][2] = 0.05;
	      lstr.bunga[4][3] = 0.07;
	      //dyn$
	      lstr.bunga[5][1] = 0.05;
	      lstr.bunga[5][2] = 0.08;
	      lstr.bunga[5][3] = 0.10;
	      //agr$  /**USD Fund BSIM Products**/
	      lstr.bunga[6][1] = 0.04;
	      lstr.bunga[6][2] = 0.08;
	      lstr.bunga[6][3] = 0.12;
	      
      }
      else{
	      //Nilai Aasumsi hasil Investasi
	      //fix
	      lstr.bunga[1][1] = 0.04; //0.06 10 may 2013 .audit
	      lstr.bunga[1][2] = 0.08;
	      lstr.bunga[1][3] = 0.10;
	      //dyn
	      lstr.bunga[2][1] = 0.05;
	      //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[2, 1] = 0.13
	      lstr.bunga[2][2] = 0.09; //0.15 10 may 2013 .audit //0.18
	      lstr.bunga[2][3] = 0.13; //0.2 10 may 2013 .audit//0.25
	      //agr
	      lstr.bunga[3][1] = 0.05;
	      //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[3, 1] = 0.13
	      lstr.bunga[3][2] = 0.10; //0.2 10 may 2013 .audit //0.22
	      lstr.bunga[3][3] = 0.16; //0.3 10 may 2013 .audit//0.32
	      
	      //sec$
	      lstr.bunga[4][1] = 0.03;
	      lstr.bunga[4][2] = 0.05;
	      lstr.bunga[4][3] = 0.07;
	      //dyn$
	      lstr.bunga[5][1] = 0.05;
	      lstr.bunga[5][2] = 0.08;
	      lstr.bunga[5][3] = 0.10;
      }
      
        if( istr_prop.bisnis_id == 162 && ( istr_prop.bisnis_no == 1 || istr_prop.bisnis_no == 2 ) )
        {
            //dyn
            lstr.bunga[2][1] = 0.1;
            lstr.bunga[2][2] = 0.15;
            lstr.bunga[2][3] = 0.20;
            //agr
            lstr.bunga[3][1] = 0.1;
            lstr.bunga[3][2] = 0.18;
            lstr.bunga[3][3] = 0.25;
        }
        if( istr_prop.bisnis_id == 165 )
        {
            //fix
            lstr.bunga[1][1] = 0.09;
            lstr.bunga[1][2] = 0.26;
            lstr.bunga[1][3] = 0.45;
        }
        
        Cepr01030104Form cepr01030104Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030104Form();
        List<TopupDrawVO> topupDrawVOList = cepr01030104Form.getTopupDrawVOList();
        for( int i = 0; i < topupDrawVOList.size(); i++ )
        {
            TopupDrawVO vo = topupDrawVOList.get( i );
            lstr.topup[ i + 1 ] = CommonConst.CHECKED_TRUE.equals( vo.getYearFlag() )?
                                    LazyConverter.toDouble( vo.getTopupAmount() ) : 0;
            lstr.tarik[ i + 1 ] = CommonConst.CHECKED_TRUE.equals( vo.getYearFlag() )?
                                    LazyConverter.toDouble( vo.getDrawAmount() ) : 0;
        }

        return lstr;
    }

    
    public static S_biaya wf_get_biaya_191( Istr_prop istr_prop, Cepr01050000Form cepr01050000Form)
    {
        S_biaya lstr = new S_biaya();
        if( istr_prop.bisnis_id == 120 || istr_prop.bisnis_id == 127 || istr_prop.bisnis_id == 128 || istr_prop.bisnis_id == 129 || istr_prop.bisnis_id == 141 )  //cerdas 10
            lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.8, 0.15, 0, 0, 0, 0 };
//
//messagebox('', string(lstr.topup[1], '#,##0'))
      //fix
        lstr.bunga[1][1] = 0.06;
        lstr.bunga[1][2] = 0.08;
        lstr.bunga[1][3] = 0.12;
//dyn
        lstr.bunga[2][1] = 0.1;
        lstr.bunga[2][2] = 0.15;
        lstr.bunga[2][3] = 0.20;
//agr
        lstr.bunga[3][1] = 0.1;
        lstr.bunga[3][2] = 0.20;
        lstr.bunga[3][3] = 0.30;
//sec$
        lstr.bunga[4][1] = 0.03;
        lstr.bunga[4][2] = 0.05;
        lstr.bunga[4][3] = 0.07;
//dyn$
        lstr.bunga[5][1] = 0.05;
        lstr.bunga[5][2] = 0.08;
        lstr.bunga[5][3] = 0.10;

        if( istr_prop.bisnis_id == 162 && ( istr_prop.bisnis_no == 1 || istr_prop.bisnis_no == 2 ) )
        {
            //dyn
            lstr.bunga[2][1] = 0.1;
            lstr.bunga[2][2] = 0.15;
            lstr.bunga[2][3] = 0.20;
            //agr
            lstr.bunga[3][1] = 0.1;
            lstr.bunga[3][2] = 0.18;
            lstr.bunga[3][3] = 0.25;
        }
        if( istr_prop.bisnis_id == 165 )
        {
            //fix
            lstr.bunga[1][1] = 0.09;
            lstr.bunga[1][2] = 0.26;
            lstr.bunga[1][3] = 0.45;
        }
        
        List<TopupDrawVO> topupDrawVOList = cepr01050000Form.getTopupDrawVOList();
        for( int i = 0; i < topupDrawVOList.size(); i++ )
        {
            TopupDrawVO vo = topupDrawVOList.get( i );
            lstr.topup[ i + 1 ] = CommonConst.CHECKED_TRUE.equals( vo.getYearFlag() )?
                                    LazyConverter.toDouble( vo.getTopupAmount() ) : 0;
            lstr.tarik[ i + 1 ] = CommonConst.CHECKED_TRUE.equals( vo.getYearFlag() )?
                                    LazyConverter.toDouble( vo.getDrawAmount() ) : 0;
        }
        return lstr;
    }
    
    // this function add month to beginDate,
    // where a month is not 30 days, but really number of exact days in the corresponding month
    public static Date f_add_months( EproposalManager eproposalManager, Date beginDate, int noOfMonths )
    {
        Date result;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "beginDate", beginDate );
        params.put( "noOfMonths", noOfMonths );

        result = eproposalManager.selectAddMonths( params );

        return result;
    }

    public static int daysAfter( Date start, Date end )
    {
        return DateUtil.calculateDifference( start, end );
    }
    
    public static Date relativedate( Date start, int add )
    {
        start.setDate(start.getDate() + add);
        return start;
    }

    /**
	 * Fungsi untuk pembulatan per desimal angka (xx angka dibelakang koma) (contoh: <b>round(3.1235, 2) = 3.12</b>)
	 * @param number nilai yang ingin dibulatkan
	 * @param decimalPlace jumlah desimal dibelakang koma
	 * @return Hasil setelah dibulatkan
	 */
	public static double round(double number, int decimalPlace) {
		BigDecimal bd = new BigDecimal(number);
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
    
}