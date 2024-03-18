package id.co.sinarmaslife.eproposal.model.pb;

import java.io.Serializable;
import java.util.Date;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Istr_prop
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Sep 17, 2007 9:37:32 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

public class Istr_prop implements Serializable
{
    /**********************************************************************
	 * Program History
	 *
	 * Project Name      	: E-Proposal
	 * Function Id         	: id.co.sinarmaslife.eproposal.model.pb
	 * Program Name   		: 
	 * Description         	:
	 * Environment      	: Java  1.5.0_06
	 * Author               : Fadly Mathendra
	 * Version              : 1.0
	 * Creation Date    	: Aug 30, 2012 10:21:21 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = -3514500141957802519L;
	public double[] fund = new double[ 11 + 1 ];
    public double pct_premi;
    public int ins_per;
    public int umur_tt;
    public double up;
    public int persen_ins;
    public int usia_schol;
    public double up_schol;
    public String[] nama_plan = new String[ 1 + 1 ];
    /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
    public int[] rider_baru = new int[ 30 + 1 ];
    public int cb_id;
    public int pa_risk;
    public int pa_class;
    public String kurs_id;
    public double premi;
    public int umur_pp;
    public S_hcpf hcpf;
    public S_hcpPro hcpPro;
    public S_hcpLad hcpLad;
    public S_ekaSehat eka_sehat;
    public S_ekaSehatExtra eka_sehatExtra;
    public S_ekaSehatInnerLimit ekaSehatInnerLimit;
    public S_ladiesMedExpense ladiesMedExpense;
    public S_ladiesMedExpenseInnerLimit ladiesMedExpenseInnerLimit;
    public S_medicalPlus medicalPlus;
    public double up_term;
    /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
    public double up_term5575;
    public int bisnis_id;
    public int bisnis_no;
    public int pay_per;
    public int cuti_premi;
    public Date tgl_prop;
    public int bunga;
    public double topup;
    public double pct_invest;
    public double biaya_ass;
    public int bln_ke;
    
}
