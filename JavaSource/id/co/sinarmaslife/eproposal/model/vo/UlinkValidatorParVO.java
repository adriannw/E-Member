package id.co.sinarmaslife.eproposal.model.vo;

import java.io.Serializable;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: UlinkValidatorParVO
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Apr 23, 2008 11:23:51 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

public class UlinkValidatorParVO implements Serializable
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
	 * Creation Date    	: Aug 30, 2012 10:21:00 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = 7967541026056075668L;
	AmountVO Under5YearsSekaligusMaxBasePremiumVO;
    AmountVO Under5YearsBerkalaMaxBasePremiumVO;
    AmountVO Beyond5YearsSekaligusMaxBasePremiumVO;
    AmountVO Beyond5YearsBerkalaMaxBasePremiumVO;

    public AmountVO getUnder5YearsSekaligusMaxBasePremiumVO()
    {
        return Under5YearsSekaligusMaxBasePremiumVO;
    }

    public void setUnder5YearsSekaligusMaxBasePremiumVO( AmountVO under5YearsSekaligusMaxBasePremiumVO )
    {
        Under5YearsSekaligusMaxBasePremiumVO = under5YearsSekaligusMaxBasePremiumVO;
    }

    public AmountVO getUnder5YearsBerkalaMaxBasePremiumVO()
    {
        return Under5YearsBerkalaMaxBasePremiumVO;
    }

    public void setUnder5YearsBerkalaMaxBasePremiumVO( AmountVO under5YearsBerkalaMaxBasePremiumVO )
    {
        Under5YearsBerkalaMaxBasePremiumVO = under5YearsBerkalaMaxBasePremiumVO;
    }

    public AmountVO getBeyond5YearsSekaligusMaxBasePremiumVO()
    {
        return Beyond5YearsSekaligusMaxBasePremiumVO;
    }

    public void setBeyond5YearsSekaligusMaxBasePremiumVO( AmountVO beyond5YearsSekaligusMaxBasePremiumVO )
    {
        Beyond5YearsSekaligusMaxBasePremiumVO = beyond5YearsSekaligusMaxBasePremiumVO;
    }

    public AmountVO getBeyond5YearsBerkalaMaxBasePremiumVO()
    {
        return Beyond5YearsBerkalaMaxBasePremiumVO;
    }

    public void setBeyond5YearsBerkalaMaxBasePremiumVO( AmountVO beyond5YearsBerkalaMaxBasePremiumVO )
    {
        Beyond5YearsBerkalaMaxBasePremiumVO = beyond5YearsBerkalaMaxBasePremiumVO;
    }
}