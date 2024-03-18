package id.co.sinarmaslife.eproposal.product.product01040110;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040110Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 20, 2008 11:52:20 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000ProductBusiness;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.standard.util.ArrUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040110Business extends Cepr01040000ProductBusiness
{
    protected final Log logger = LogFactory.getLog( getClass() );
 
    public Cepr01040110Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
    	super( eproposalManager, editorUtil, command );
        setSekaligusRightPartCdArr( new Integer[]{ Cepr01040110Mapper.X10} );
        setBerkalaRightPartCdArr( new Integer[]{ Cepr01040110Mapper.X6, Cepr01040110Mapper.X7, Cepr01040110Mapper.X8, Cepr01040110Mapper.X9 } );
        logger.info( "*-*-*-* Cepr01040110Business constructor is called ..." );
    }

    public List< Map<String, Object> > getHeaderRowList( Object command ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040110Business.getCommonHeaderRowList" );

        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List< Map<String, Object> > result = new ArrayList< Map<String, Object> >();

        // Fill header content from CommonHeaderDataSource
        result.addAll( source.getPolicyHolderNameMap() );
        result.addAll( source.getPolicyHolderAgeMap() );
        result.addAll( source.getInsuredNameMap() );
        result.addAll( source.getInsuredAgeMap() );
        result.addAll( source.getTermOfContractMap() );
        
        Map<String, Object> params;
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        List sekaligusRightPartCdList = ArrUtil.toListFromArray( sekaligusRightPartCdArr );
        List berkalaRightPartCdList   = ArrUtil.toListFromArray( berkalaRightPartCdArr );
        if( sekaligusRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
            params = new HashMap<String, Object>();
            params.put( "label", "Premi Sekaligus" );
            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) );
            result.add( params );
        }
        else if( berkalaRightPartCdList.contains( cepr01030102Form.getRightPartOfBusinessCd() ) )
        {
            params = new HashMap<String, Object>();
            params.put( "label", "Premi Tahunan" );
            params.put( "content", commonUsedBusiness.toMoneyWithCurrencyCd( cepr01030102Form.getPremium() ) );
            result.add( params );

            result.addAll( source.getTermOfPaymentMap() );
        }
        
        result.addAll( source.getTotalSumInsuredMap() );
        result.addAll( source.getPolicyHolderMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_pp" ) ) );
        result.addAll( source.getInsuredMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_tt" ) ) );

        return result;
    }
}