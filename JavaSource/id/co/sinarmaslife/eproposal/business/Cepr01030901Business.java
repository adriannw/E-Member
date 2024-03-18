package id.co.sinarmaslife.eproposal.business;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01010101Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 6, 2008 4:07:41 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Ilustration116Business;
import id.co.sinarmaslife.eproposal.common.parent.StandardParent;
import id.co.sinarmaslife.eproposal.common.pb.PbConverter;
import id.co.sinarmaslife.eproposal.common.pb.PbFunction;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.pb.Istr_prop;
import id.co.sinarmaslife.eproposal.model.pb.S_biaya;
import id.co.sinarmaslife.eproposal.model.pb.S_ekaSehat;
import id.co.sinarmaslife.eproposal.model.pb.S_ekaSehatInnerLimit;
import id.co.sinarmaslife.eproposal.model.pb.S_hcpf;
import id.co.sinarmaslife.eproposal.model.vo.Cepr00000000YieldResultVO;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.eproposal.product.product01040213.Cepr01040213Mapper;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01020000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01020201Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030104Form;
import id.co.sinarmaslife.eproposal.web.Cepr01050000Form;
import id.co.sinarmaslife.eproposal.web.Cepr01050000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030901Form;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.model.vo.UserGroup;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.MathUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Cepr01030901Business
{
	protected Istr_prop istr_prop;
    protected final Log logger = LogFactory.getLog( getClass() );
    
    protected EproposalManager eproposalManager;
    protected Cepr00000000EditorUtil editorUtil;

    public Cepr01030901Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil )
    {
        this.eproposalManager = eproposalManager;
        this.editorUtil = editorUtil;
    }
    
    public List<Map<String, String>> listMapDPLK(HttpServletRequest request, HttpServletResponse response)
    {
    	List<Map<String, String>> listMapDPLK = new ArrayList<Map<String, String>>();    	    
        Map<String, String> params;   		    
    	
    	 Object command = request.getSession().getAttribute( CommonConst.SES_COMMAND );
          Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
    	Cepr01030901Form cepr01030901Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030901Form();
    	    	 
 	   Integer customerCount = cepr01030901Form.getCustomerCount();
 	   Integer customerAge  = cepr01030901Form.getCustomerAge();
 	    Integer normalAgePension = cepr01030901Form.getNormalAgePension();
 	    double income =  cepr01030901Form.getIncome().doubleValue(); 	    	  
 	     
 	    BigDecimal percentPremiumCompany   = cepr01030901Form.getPercentPremiumCompany();
 	    BigDecimal percentPremiumEmployee =   cepr01030901Form.getPercentPremiumEmployee();
 	   double dpercentPremiumEmployee = LazyConverter.toDouble(percentPremiumEmployee);
 	   double dpercentPremiumCompany = LazyConverter.toDouble(percentPremiumCompany);
 	    
 
 	    Integer amountFundTransfer =  cepr01030901Form.getAmountFundTransfer();

 	    BigDecimal percentIncomeIncreasePerYear  = cepr01030901Form.getPercentIncomeIncreasePerYear();
 	     BigDecimal percentInterestInvestPerYear  = cepr01030901Form.getPercentInterestInvestPerYear();
 	     double dpercentInterestInvestPerYear = LazyConverter.toDouble(percentInterestInvestPerYear);
 	     double dpercentIncomeIncreasePerYear = LazyConverter.toDouble(percentIncomeIncreasePerYear);
 	     
 	     Integer jmlTahun = normalAgePension - customerAge;
 	     Integer jmlBulan = jmlTahun*12;
 	     jmlBulan=jmlBulan+2;
 	    	     
 	     double[] t = new double[jmlBulan];    	     
 	     double[] setoran = new double[jmlBulan]; 	 
 	     double[] biaya = new double[jmlBulan];
 	     double[] inv_awal = new double[jmlBulan];
 	     double[] bunga_inv = new double[jmlBulan];
 	     double[] total = new double[jmlBulan];
 	     double[] fee= new double[jmlBulan];
 	     double[] nett = new double[jmlBulan];
 	     double biayaSetoran = 1000.0;
// 	     Integer biayaKepesertaan = 5000*customerCount;
 	     double biayaPenyelenggaraan = 0.0015;
 	     
 	     double nett_inv_awal;
 	     nett_inv_awal=0.00;
 	     int bulan_ke;
 	    bulan_ke=1; 	      	
 	     
 	        for( int i = 1; i <= jmlTahun; i++ )
 	        {
 	        	if(i>1){
 	        		income = (int) (income + ((dpercentIncomeIncreasePerYear*0.01)*income));
 	        		}	
 	        	customerAge++;			
 	        	  for( int j = 1; j <= 12; j++ )
 	    	        {
 	        			t[bulan_ke]= bulan_ke;
 	        			biaya[bulan_ke]=biayaSetoran;
 	        			
 	        			
 	        			if(bulan_ke==1){
 	        				//setoran[bulan_ke]= 0.1*income;   
 	        				setoran[bulan_ke]= (((dpercentPremiumEmployee*0.01)*income) + ((dpercentPremiumCompany*0.01)*income));
 	        				inv_awal[bulan_ke]= setoran[bulan_ke]-biaya[bulan_ke]+amountFundTransfer;
// 	        				inv_awal[bulan_ke]= editorUtil.convertToRoundedTwoDigitsDouble(inv_awal[bulan_ke]);
 	        			}else
 	        			{
 	        				setoran[bulan_ke]= (((dpercentPremiumEmployee*0.01)*income) + ((dpercentPremiumCompany*0.01)*income));
 	        				inv_awal[bulan_ke]= setoran[bulan_ke]-biaya[bulan_ke]+nett_inv_awal;
// 	        				inv_awal[bulan_ke]= editorUtil.convertToRoundedTwoDigitsDouble(inv_awal[bulan_ke]);
 	        			}
 	        			
 	        			bunga_inv[bulan_ke]=inv_awal[bulan_ke]*((dpercentInterestInvestPerYear*0.01)/12);
// 	        			bunga_inv[bulan_ke]=editorUtil.convertToRoundedTwoDigitsDouble(bunga_inv[bulan_ke]);
 	    	        	total[bulan_ke] = inv_awal[bulan_ke]+bunga_inv[bulan_ke];
// 	    	        	total[bulan_ke] = editorUtil.convertToRoundedTwoDigitsDouble(total[bulan_ke]);
 	        			fee[bulan_ke] = biayaPenyelenggaraan*total[bulan_ke];
// 	        			fee[bulan_ke] = editorUtil.convertToRoundedTwoDigitsDouble(fee[bulan_ke]);
 	        			
 	        			nett[bulan_ke] = total[bulan_ke]-fee[bulan_ke];
// 	        			nett[bulan_ke] = editorUtil.convertToRoundedTwoDigitsDouble(nett[bulan_ke]);
 	        			
 	        			nett_inv_awal = nett[bulan_ke];
 	    	        	if(j==12){
 	    	        	
 	    	        			double setoranTahunan =  setoran[bulan_ke]*12;
 	    	        		
 	    	        		  params= new HashMap<String, String>();
 	    	 	        	 params.put( "tahunke",String.valueOf(i));
 	    	 	        	 params.put( "usia", String.valueOf(customerAge));
 	    	 	        	 params.put( "frekuensisetoran",editorUtil.convertToStringWithoutCent( t[bulan_ke]));
 	    	 	        	 params.put( "setoranbulanan",editorUtil.convertToRoundedNoDigit( new BigDecimal(setoran[bulan_ke]))); 
 	    	 	        	 params.put( "setorantahunan",editorUtil.convertToRoundedNoDigit( new BigDecimal(setoranTahunan))); 
	 	    	 	        	
 	    	 	        	 params.put( "dplk",editorUtil.convertToRoundedNoDigit(new BigDecimal(nett[bulan_ke])) ); 
 	    	 	        	listMapDPLK.add( params );
 	    	        		 	    	        		
 	    	        	}
 	    	        	bulan_ke++;
 	    	        }
 	        	
 	        }    	
    	
    	return listMapDPLK;
    }
   
    
    
}
