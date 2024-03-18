package id.co.sinarmaslife.eproposal.model.setup.produk_bisnis;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040211Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 10, 2009 3:26:40 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
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
import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.eproposal.model.pb.N_riders;
import id.co.sinarmaslife.eproposal.model.pb.S_biaya;
import id.co.sinarmaslife.eproposal.model.vo.Cepr00000000YieldResultVO;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.model.vo.IllustrationResultVO;
import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.eproposal.product.product01040219.Cepr01040219Mapper;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030104Form;
import id.co.sinarmaslife.standard.propertyeditors.CurrencyFormatEditor;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.MappingUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;

public class illustration_120_bisnis 
{

	public S_biaya illustration_120_bisnis() {
		  S_biaya lstr = new S_biaya();
		  		  
		  
		    lstr.bak = new double[]{ CommonConst.DUMMY_ZERO, 0.8, 0.15, 0, 0, 0, 0 };
		 
		       	        
	        lstr.bunga[1][1] = 0.05; //0.06 10 may 2013 .audit
	        lstr.bunga[1][2] = 0.08;
	        lstr.bunga[1][3] = 0.10;
	        //dyn
	        lstr.bunga[2][1] = 0.06;
	        //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[2, 1] = 0.13
	        lstr.bunga[2][2] = 0.10; //0.15 10 may 2013 .audit //0.18
	        lstr.bunga[2][3] = 0.14; //0.2 10 may 2013 .audit//0.25
	        //agr
	        lstr.bunga[3][1] = 0.07;
	        //If Pos('160', string(istr_prop.bisnis_id, '000#')) > 0 Then lstr.bunga[3, 1] = 0.13
	        lstr.bunga[3][2] = 0.12; //0.2 10 may 2013 .audit //0.22
	        lstr.bunga[3][3] = 0.18; //0.3 10 may 2013 .audit//0.32
	        //sec$
	        lstr.bunga[4][1] = 0.03;
	        lstr.bunga[4][2] = 0.05;
	        lstr.bunga[4][3] = 0.07;
	        //dyn$
	        lstr.bunga[5][1] = 0.05;
	        lstr.bunga[5][2] = 0.08;
	        lstr.bunga[5][3] = 0.10;
	      		
		
	        return lstr;
	}
  
  
}