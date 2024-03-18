package id.co.sinarmaslife.eproposal.product.product01040206;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	:
 * Program Name   		: Cepr01040204Business
 * Description         	: Excel Link 80 / Eka Link 80 (115)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 8, 2008 2:24:22 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Ilustration116Business;
import id.co.sinarmaslife.eproposal.common.pb.PbConverter;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cepr01040206Business extends Cepr01040000Ilustration116Business
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040206Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
        setSekaligusRightPartCdArr( new Integer[]{ Cepr01040206Mapper.X2 } );
        setInsuredAgeLimit( 80 );

        String productLabel = "EXCELLINK";
        setInvestmentTitleArr( new String[] {
            "",
            productLabel + " Fixed Income Syariah",
            productLabel + " Dynamic Syariah",
            productLabel + " Aggressive Syariah",
            "",
            ""
        } );
        setInvestmentTitleAndDescrArr( new String[][] {
            { "", "", "" },
            { "", "- " + productLabel + " Fixed Income Syariah Fund : 100% Fixed Income", "  Penempatan dana pada Investasi berpendapatan tetap dan instrumen pasar uang yang", "  berdasarkan Syariah dan yang telah mendapatkan sertifikasi syariah dari badan yang berwenang." },
            { "", "- " + productLabel + " Dynamic Syariah Fund : minimum 50% Fixed Income dan maksimum 50% Ekuitas", "  Penempatan dana pada Investasi berpendapatan tetap, ekuitas serta instrumen pasar uang yang", "  berdasarkan Syariah dan yang telah mendapatkan sertifikasi syariah dari badan yang berwenang." },
            { "", "- " + productLabel + " Aggressive Syariah Fund : maksimum 100% Fixed Income Rupiah atau maksimum 50% Fixed", "  50% Fixed Income US Dollar atau maksimum 100% Ekuitas yang berdasarkan Syariah." },
            { "", "" },
            { "", "" }
        } );
        
        istr_prop = PbConverter.get_istr_prop( command, insuredAgeLimit );
    }
    
    public List<Map<String, Object>> getPageList( int currentIdx ) throws IOException
    {
        logger.info( "*-*-*-* Cepr01040201Business.getPageList" );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub1" );
        result.add( params );

        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub2" );
        result.add( params );

        // if no riders, don't show this page ( page 3 )
        if( currentIdx != 0 )
        {
            params = new HashMap<String, Object>();
            params.put( "pageCode", "sub3" );
            result.add( params );
        }

//        params = new HashMap<String, Object>();
//        params.put( "pageCode", "sub4" );
//        result.add( params );

        return result;
    }
}