package id.co.sinarmaslife.eproposal.model.vo;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: HardcodedProductVO
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 27, 2008 4:19:20 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import id.co.sinarmaslife.standard.model.vo.AssurancePlanList;
import id.co.sinarmaslife.standard.util.LazyConverter;

public class HardcodedProductVO implements Serializable
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
	 * Creation Date    	: Aug 30, 2012 10:20:15 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = 4274119049616800220L;

	protected final transient Log logger = LogFactory.getLog( getClass() );

    private String assurancePlanCd;
    private int leftPartOfBusinessCd;
    private String productDescr;
    private AssurancePlanList[] businessOptionVOArr;

    public HardcodedProductVO( int leftPartOfBusinessCd, String productDescr, AssurancePlanList[] businessOptionVOArr )
    {
        this.leftPartOfBusinessCd = leftPartOfBusinessCd;
        this.productDescr = productDescr;
        this.businessOptionVOArr = businessOptionVOArr;
        this.assurancePlanCd = LazyConverter.toString( leftPartOfBusinessCd ).concat( "==>" ).concat( productDescr );
    }

    public String getAssurancePlanCd()
    {
        return assurancePlanCd;
    }

    public void setAssurancePlanCd( String assurancePlanCd )
    {
        this.assurancePlanCd = assurancePlanCd;
    }

    public AssurancePlanList[] getBusinessOptionVOArr()
    {
        return businessOptionVOArr;
    }

    public void setBusinessOptionVOArr( AssurancePlanList[] businessOptionVOArr )
    {
        this.businessOptionVOArr = businessOptionVOArr;
    }

    public int getLeftPartOfBusinessCd()
    {
        return leftPartOfBusinessCd;
    }

    public void setLeftPartOfBusinessCd( int leftPartOfBusinessCd )
    {
        this.leftPartOfBusinessCd = leftPartOfBusinessCd;
    }

    public String getProductDescr()
    {
        return productDescr;
    }

    public void setProductDescr( String productDescr )
    {
        this.productDescr = productDescr;
    }
}
