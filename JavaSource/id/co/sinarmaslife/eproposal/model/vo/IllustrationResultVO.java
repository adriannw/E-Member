package id.co.sinarmaslife.eproposal.model.vo;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: IllustrationResultVO
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 1, 2008 1:53:50 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class IllustrationResultVO implements Serializable
{
    /**********************************************************************
	 * Program History
	 *
	 * Project Name      	: E-Proposal
	 * Function Id         	: id.co.sinarmaslife.eproposal.model.vo
	 * Program Name   		: 
	 * Description         	:
	 * Environment      	: Java  1.5.0_06
	 * Author               : Fadly Mathendra
	 * Version              : 1.0
	 * Creation Date    	: Aug 30, 2012 10:20:26 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = 7758479117856050487L;
	private ArrayList<Map<String, Object>> illustrationList;
    private String validityMsg;

    public ArrayList<Map<String, Object>> getIllustrationList()
    {
        return illustrationList;
    }

    public void setIllustrationList( ArrayList<Map<String, Object>> illustrationList )
    {
        this.illustrationList = illustrationList;
    }

    public String getValidityMsg()
    {
        return validityMsg;
    }

    public void setValidityMsg( String validityMsg )
    {
        this.validityMsg = validityMsg;
    }
}
