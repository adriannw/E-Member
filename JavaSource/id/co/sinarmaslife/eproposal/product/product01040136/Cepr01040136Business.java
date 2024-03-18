package id.co.sinarmaslife.eproposal.product.product01040136;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040105Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : Fadly Mathendra
 * Version              : 1.0
 * Creation Date    	: Jul 8, 2008 10:52:00 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000ProductBusiness;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000CurrencyUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;

public class Cepr01040136Business extends Cepr01040000ProductBusiness
{
    protected final static Log logger = LogFactory.getLog( Cepr01040136Business.class );

    public Cepr01040136Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
    }
    
    public static Map<String, Object> getManfaatPersonalAccidentParamsMap( Object command, Cepr00000000EditorUtil editorUtil ) throws IOException
    {
       // logger.info( "*-*-*-* ManfaatDataSource.getManfaatPersonalAccidentParamsMap" );
        logger.info( "*-*-*-* ManfaatDataSource.getManfaatPersonalAccidentParamsMap" );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        Map<String, Object> params = new HashMap<String, Object>();
        String termOfPayment = editorUtil.convertToString( cepr01030102Form.getTermOfPayment() ) + " tahun.";
        String amount = Cepr00000000CurrencyUtil.sayForMoney( cepr01030102Form.getTotalSumInsured(), cepr01030102Form.getCurrencyCd() );
        String say = "Apabila Tertanggung meninggal dunia karena kecelakaan dalam masa Pertanggungan SMiLe PA Rider masih berlaku, maka akan dibayarkan Manfaat Asuransi Tambahan sebesar Uang Pertanggungan SMiLe PA Rider (".concat( amount ).concat( ").");
        params.put( "termOfPayment", termOfPayment );
        params.put( "paragraph1", say );
        return params;
    }
    
    public static Map<String, Object> getManfaatTermRiderParamsMap( Object command, Cepr00000000EditorUtil editorUtil ) throws IOException
    {
        //logger.info( "*-*-*-* ManfaatDataSource.getManfaatTermRiderParamsMap" );
        logger.info(  "*-*-*-* ManfaatDataSource.getManfaatTermRiderParamsMap"  );
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();

        Map<String, Object> params = new HashMap<String, Object>();
        String termOfManfaatTermRider = editorUtil.convertToString( cepr01030102Form.getTermOfPayment() ) + " tahun.";
        String amount = Cepr00000000CurrencyUtil.sayForMoney( cepr01030102Form.getTotalSumInsured(), cepr01030102Form.getCurrencyCd() );
        String say = "akan dibayarkan manfaat tambahan sebesar 100% Uang Pertanggungan Pokok (".concat( amount ).concat( ").");
        params.put( "termOfManfaatTermRider", termOfManfaatTermRider );
        params.put( "amountInSpeechManfaatTermRider", say );
        return params;
    }
}
