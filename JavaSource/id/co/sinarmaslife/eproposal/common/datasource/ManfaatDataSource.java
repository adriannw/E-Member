package id.co.sinarmaslife.eproposal.common.datasource;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: ManfaatDataSource
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jul 31, 2007 5:14:11 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000CurrencyUtil;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.standard.util.MathUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ManfaatDataSource
{
    protected final static Log logger = LogFactory.getLog( ManfaatDataSource.class );

    public ManfaatDataSource()
    {
        logger.info( "*-*-*-* ManfaatDataSource constructor is called ..." );
    }

    public static Map<String, Object> getManfaatTermRiderParamsMap( Object command, Cepr00000000EditorUtil editorUtil ) throws IOException
    {
       // logger.info( "*-*-*-* ManfaatDataSource.getManfaatTermRiderParamsMap" );
        logger.info("*-*-*-* ManfaatDataSource.getManfaatTermRiderParamsMap");
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        Map<String, Object> params = new HashMap<String, Object>();
        String termOfManfaatTermRider = editorUtil.convertToString( cepr01030102Form.getTermOfPayment() ) + " tahun.";
        String amount = Cepr00000000CurrencyUtil.sayForMoney( cepr01030102Form.getTotalSumInsured(), cepr01030102Form.getCurrencyCd() );
        String say = "yang ditunjuk akan dibayarkan manfaat tambahan sebesar 100% Uang Pertanggungan Pokok (".concat( amount ).concat( ").");
        params.put( "termOfManfaatTermRider", termOfManfaatTermRider );
        params.put( "amountInSpeechManfaatTermRider", say );
        return params;
    }

    public static Map<String, Object> getManfaatPersonalAccidentParamsMap( Object command, Cepr00000000EditorUtil editorUtil ) throws IOException
    {
        //logger.info( "*-*-*-* ManfaatDataSource.getManfaatPersonalAccidentParamsMap" );
        logger.info("*-*-*-* ManfaatDataSource.getManfaatPersonalAccidentParamsMap");
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        Map<String, Object> params = new HashMap<String, Object>();
        String termOfPayment = editorUtil.convertToString( cepr01030102Form.getTermOfPayment() ) + " tahun.";
        String amount = Cepr00000000CurrencyUtil.sayForMoney( cepr01030102Form.getTotalSumInsured(), cepr01030102Form.getCurrencyCd() );
        String say = "Apabila Tertanggung meninggal dunia karena kecelakaan dalam masa Pertanggungan PA Rider masih berlaku, maka akan dibayarkan Manfaat Asuransi Tambahan sebesar 100% Uang Pertanggungan Pokok (".concat( amount ).concat( ").");
        params.put( "termOfPayment", termOfPayment );
        params.put( "paragraph1", say );
        return params;
    }

    public static Map<String, Object> getManfaatPenyakitKritisParamsMap( Object command, Cepr00000000EditorUtil editorUtil ) throws IOException
    {
       // logger.info( "*-*-*-* ManfaatDataSource.getManfaatPenyakitKritisParamsMap" );
        logger.info("*-*-*-* ManfaatDataSource.getManfaatPenyakitKritisParamsMap");
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        Map<String, Object> params = new HashMap<String, Object>();
        String termOfPayment = editorUtil.convertToString( cepr01030102Form.getTermOfPayment() ) + " tahun.";

        double totalSumInsured = cepr01030102Form.getTotalSumInsured().doubleValue();
        double asuranceBenefit;
        double maxBenefix = 0;
        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            maxBenefix = 500000000;
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            maxBenefix = 50000;   
        }
        asuranceBenefit = MathUtil.min( totalSumInsured / 2, maxBenefix );

        String asuranceBenefitStr = Cepr00000000CurrencyUtil.sayForMoney( asuranceBenefit, cepr01030102Form.getCurrencyCd() );
        String say = "Penyakit Kritis merupakan pertanggungan tambahan yang dapat mempercepat timbulnya manfaat asuransi sebesar ".concat( asuranceBenefitStr ).concat( " apabila berdasarkan diagnosa Tertanggung dinyatakan menderita salah satu penyakit kritis sebagai berikut : " );
        params.put( "termOfPayment", termOfPayment );
        params.put( "paragraph1", say );
        return params;
    }

    public static Map<String, Object> getManfaatPayorsClauseParamsMap() throws IOException
    {
        return new HashMap<String, Object>();
    }
    
    public static Map<String, Object> getManfaatTpdParamsMap( Object command, Cepr00000000EditorUtil editorUtil ) throws IOException
    {
        logger.info("*-*-*-* ManfaatDataSource.getManfaatTpdParamsMap");
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        Map<String, Object> params = new HashMap<String, Object>();
        String termOfPayment = editorUtil.convertToString( cepr01030102Form.getTermOfPayment() ) + " tahun.";
        params.put( "termOfPayment", termOfPayment );
        return params;
    }

    public static Map<String, Object> getManfaatWaiverPremiumDisabilityParamsMap() throws IOException
    {
        return new HashMap<String, Object>();
    }
    
    public static Map<String, Object> getManfaatEkaSehatParamsMap() throws IOException
    {
        return new HashMap<String, Object>();
    }
}
