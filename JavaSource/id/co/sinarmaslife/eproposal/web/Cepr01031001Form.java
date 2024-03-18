package id.co.sinarmaslife.eproposal.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01030401Form
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : fadly mathendra
 * Version              : 1.0
 * Creation Date    	: Jul 13, 2007 3:17:29 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.standard.util.ListUtil;

public class Cepr01031001Form implements Serializable
{
	private static final long serialVersionUID = 1L;
	public ArrayList<Mst_proposal> mstProposal;

    
    public ArrayList<Mst_proposal> getMstProposal() {
		return mstProposal;
	}


	public void setMstProposal(List<Mst_proposal> mstProposal) {
		this.mstProposal = ListUtil.serializableList(mstProposal);
	}


	public Cepr01031001Form()
	{
		// Do nothing
    }
    

	
}
