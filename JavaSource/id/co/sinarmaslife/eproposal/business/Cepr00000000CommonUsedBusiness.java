package id.co.sinarmaslife.eproposal.business;

import java.math.BigDecimal;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr00000000CommonUsedBusiness
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Dec 21, 2007 9:27:44 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030104Form;
import id.co.sinarmaslife.standard.util.MappingUtil;

/**
 * All comon stuff that used formbean
 */

public class Cepr00000000CommonUsedBusiness
{
    protected final Log logger = LogFactory.getLog( getClass() );
    protected EproposalManager eproposalManager;
    protected Cepr00000000EditorUtil editorUtil;
    protected Cepr01030101Form cepr01030101Form;
    protected Cepr01030102Form cepr01030102Form;
    protected Cepr01030104Form cepr01030104Form;

    public Cepr00000000CommonUsedBusiness( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        logger.info( "*-*-*-* Cepr00000000CommonUsedBusiness constructor is called ..." );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        this.eproposalManager = eproposalManager;
        this.editorUtil = editorUtil;
        this.cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        this.cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        this.cepr01030104Form = cepr01030000HoldingForm.getCepr01030104Form();
    }



    /**
     *
     * @param amount: amount of money in BigDecimal
     * @return String amount of money in format like this: Rp. 12,000,000.00
     */
    public String toMoneyWithCurrencyCd( BigDecimal amount )
    {
        String currencySymbol = MappingUtil.getLabel( cepr01030102Form.getCurrencyList(), cepr01030102Form.getCurrencyCd() );
        return currencySymbol + editorUtil.convertToRoundedTwoDigits( amount );
    }

    /**
     *
     * @param amount: amount of money in double
     * @return String amount of money in format like this: Rp. 12,000,000.00
     */
    public String toMoneyWithCurrencyCd( double amount )
    {
        return toMoneyWithCurrencyCd( new BigDecimal( amount ) );
    }

    public void processMaxTotalSumInsured( BigDecimal maxIdr, BigDecimal maxUsd )
    {
        BigDecimal totalSumInsured = cepr01030102Form.getTotalSumInsured();
        if( totalSumInsured != null )
        {
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                if( totalSumInsured.compareTo( maxIdr ) > 0 )
                {
                    totalSumInsured = maxIdr;
                }
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                if( totalSumInsured.compareTo( maxUsd ) > 0 )
                {
                    totalSumInsured = maxUsd;
                }
            }
            cepr01030102Form.setTotalSumInsured( totalSumInsured );
        }
    }

    public boolean policyHolderIsInsured()
    {
        logger.info( "*-*-*-* Cepr01040000CommonValidator.policyHolderIsInsured" );
        boolean isTheSame = true;

        if( cepr01030101Form.getPolicyHolderAge() != null )
        {
            if( !cepr01030101Form.getPolicyHolderAge().equals( cepr01030101Form.getInsuredAge() ) )
            {
                isTheSame = false;
            }
        }
        else
        {
            if( cepr01030101Form.getInsuredAge() != null )
            {
                isTheSame = false;
            }
        }

        if( cepr01030101Form.getPolicyHolderBirthDay() != null )
        {
            if( !cepr01030101Form.getPolicyHolderBirthDay().equals( cepr01030101Form.getInsuredBirthDay() ) )
            {
                isTheSame = false;
            }
        }
        else
        {
            if( cepr01030101Form.getInsuredBirthDay() != null )
            {
                isTheSame = false;
            }
        }

        if( cepr01030101Form.getPolicyHolderName() != null )
        {
            if( !cepr01030101Form.getPolicyHolderName().trim().equalsIgnoreCase( cepr01030101Form.getInsuredName().trim() ) )
            {
                isTheSame = false;
            }
        }
        else
        {
            if( cepr01030101Form.getInsuredName() != null )
            {
                isTheSame = false;
            }
        }

        if( cepr01030101Form.getPolicyHolderSexCd() != null )
        {
            if( !cepr01030101Form.getPolicyHolderSexCd().equals( cepr01030101Form.getInsuredSexCd() ) )
            {
                isTheSame = false;
            }
        }
        else
        {
            if( cepr01030101Form.getInsuredSexCd() != null )
            {
                isTheSame = false;
            }
        }

        return isTheSame;
    }
}
