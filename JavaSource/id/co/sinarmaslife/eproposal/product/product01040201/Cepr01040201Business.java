package id.co.sinarmaslife.eproposal.product.product01040201;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	:
 * Program Name   		: Cepr01040201Business
 * Description         	: Eka Link Family (159)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Sep 14, 2007 9:11:19 AM
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

public class Cepr01040201Business extends Cepr01040000Ilustration116Business
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public Cepr01040201Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
        setSekaligusRightPartCdArr( new Integer[]{ Cepr01040201Mapper.X1 } );
        setBerkalaRightPartCdArr( new Integer[]{ Cepr01040201Mapper.X2, Cepr01040201Mapper.X4 } );
        setInsuredAgeLimit( 80 );
        setInvestmentTitleArr( new String[] {
            "",
            "EXCELLINK Fixed Income Fund",
            "EXCELLINK Dynamic Fund",
            "EXCELLINK Aggressive Fund",
            "EXCELLINK Secure Dollar Income",
            "EXCELLINK Dynamic Dollar Fund"
        } );
        setInvestmentTitleAndDescrArr( new String[][] {
            { "", "", "" },
            { "", "- EXCELLINK Fixed Income Fund : 100% Fixed Income", "  Penempatan dana pada investasi pendapatan tetap dan instrumen pasar uang." },
            { "", "- EXCELLINK Dynamic Fund : minimum 50% Fixed Income dan maksimum 50% Ekuitas", "  Penempatan dana pada investasi pendapatan tetap, ekuitas serta instrumen pasar uang." },
            { "", "- EXCELLINK Aggressive Fund : maksimum 100% Fixed Income Rupiah atau maksimum 50% Fixed", "  Income US Dollar atau maksimum 100% Ekuitas" },
            { "", "- EXCELLINK Secure Dollar Income Fund : 100% Fixed Income.", null },
            { "", "- EXCELLINK Dynamic Dollar Fund : minimum 50% Fixed Income dan maksimum 50% Ekuitas.", null },
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
            
            params = new HashMap<String, Object>();
            params.put( "pageCode", "sub5" );
            result.add( params );
        }
//        params = new HashMap<String, Object>();
//        params.put( "pageCode", "sub4" );
//        result.add( params );
        
        params = new HashMap<String, Object>();
        params.put( "pageCode", "sub6" );
        result.add( params );        
        
        return result;
    }
    
    public String getPageRider( int currentIdx ) throws IOException
    { 
    	String result = null;
    
    	 if( currentIdx != 0 )
         {             
            result = "sub3";            
         }
    	   return result;
    }
}
