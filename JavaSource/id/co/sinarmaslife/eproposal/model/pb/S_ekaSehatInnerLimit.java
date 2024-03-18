package id.co.sinarmaslife.eproposal.model.pb;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: S_ekaSehatInnerLimit
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : Fadly M
 * Version              : 1.0
 * Creation Date    	: Mar 15, 2012 15:56:20
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.io.Serializable;
import java.util.Date;

public class S_ekaSehatInnerLimit implements Serializable
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
	 * Creation Date    	: Aug 30, 2012 10:21:52 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = 822041177980657136L;
	public int peserta;
    public String[] nama = new String[4 + 1]; 
    public Date[] tgl = new Date[4 + 1];
    public int[] usia = new int[4 + 1];
    public boolean changed; 
}
