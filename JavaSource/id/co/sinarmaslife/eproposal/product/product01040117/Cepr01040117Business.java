package id.co.sinarmaslife.eproposal.product.product01040117;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040117Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Sep 3, 2008 9:42:18 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000ProductBusiness;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.datasource.CommonHeaderDataSource;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.standard.util.LazyConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;

public class Cepr01040117Business extends Cepr01040000ProductBusiness
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040117Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
    }

    public List< Map<String, Object> > getHeaderRowList( Object command ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040117DownloadController.getCommonHeaderRowList" );

        CommonHeaderDataSource source = new CommonHeaderDataSource( command, editorUtil, eproposalManager );
        List< Map<String, Object> > result = new ArrayList< Map<String, Object> >();

        // Fill header content from CommonHeaderDataSource
        result.addAll( source.getPolicyHolderNameMap() );
        result.addAll( source.getPolicyHolderAgeMap() );
        result.addAll( source.getInsuredNameMap() );
        result.addAll( source.getInsuredAgeMap() );
        result.addAll( source.getTermOfContractMap() );
        result.addAll( source.getTotalSumInsuredMap() );
        result.addAll( source.getTermOfPaymentWithSekaligusMap() );

        Map<String, Object> params;
        params = new HashMap<String, Object>();
        params.put( "label", "Bonus Tahapan pada akhir tahun Polis ke-5" );
        params.put( "content", "2.92% dari Uang Pertanggungan" );
        result.add( params );

        result.addAll( source.getPolicyHolderMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_pp" ) ) );
        result.addAll( source.getInsuredMedicalCheckUpMap( ( String ) of_cek_medis( command ).get( "medis_tt" ) ) );

        return result;
    }

    public List< Map<String, String> > getDetailList() throws IOException
    {
        logger.info( "*-*-*-* Cepr01040117Business.getDetailList" );

        double[] cashRate = { 0.95, 1.02, 1.05, 1.08, 1.16 };
        double[] nonAccBenefitRate = { 1.1, 1.5, 1.5, 1.5, 1.5 };
        double[] accBenefitRate = { 2.0, 2.0, 2.0, 2.0, 2.0 };//?X
        double[] stageBonusRate = { 0.0, 0.0, 0.0, 0.0, 0.0292 };

        double totalSumInsured = LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() );

        List< Map<String, String> > result = new ArrayList< Map<String, String> >();
        Map<String, String> params;
        String stageBonus;

        for( int i = 0; i < 5; i++ )
        {
            params = new HashMap<String, String>();
            params.put( "yearNo", LazyConverter.toString( i + 1 ) + "." );
            params.put( "cashValue", editorUtil.convertToRoundedTwoDigits( totalSumInsured * cashRate[ i ] ) );
            params.put( "nonAccBenefit", editorUtil.convertToRoundedTwoDigits( totalSumInsured * nonAccBenefitRate[ i ] ) );
            //params.put( "accBenefit", editorUtil.convertToRoundedTwoDigits( totalSumInsured * accBenefitRate[ i ] ) );

            stageBonus = editorUtil.convertToRoundedTwoDigits( totalSumInsured * stageBonusRate[ i ] );
            stageBonus = stageBonus.equals( "0.00" )? "-" : stageBonus;
            params.put( "stageBonus", stageBonus );
            result.add( params );
        }

        return result;
    }
}
