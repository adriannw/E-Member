package id.co.sinarmaslife.eproposal.product.product01040203;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040203
 * Program Name   		: Cepr01040203Business
 * Description         	: Artha Link 88 (162)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jan 31, 2008 9:07:51 AM
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
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cepr01040203Business extends Cepr01040000Ilustration116Business
{
    protected final Log logger = LogFactory.getLog( getClass() );
    
    public Cepr01040203Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
        setSekaligusRightPartCdArr( new Integer[]{ Cepr01040203Mapper.X1, Cepr01040203Mapper.X5, Cepr01040203Mapper.X7 } );
        setBerkalaRightPartCdArr( new Integer[]{ Cepr01040203Mapper.X2, Cepr01040203Mapper.X6, Cepr01040203Mapper.X8 } );
        setInsuredAgeLimit( 88 );

        String productLabel = "";
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        if(    Cepr01040203Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd()
            || Cepr01040203Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            productLabel = "ARTHA LINK";
        }
        else if( Cepr01040203Mapper.X5 == cepr01030102Form.getRightPartOfBusinessCd()
              || Cepr01040203Mapper.X6 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            productLabel = "EKA LINK 88";
        }
        else if( Cepr01040203Mapper.X7 == cepr01030102Form.getRightPartOfBusinessCd()
              || Cepr01040203Mapper.X8 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            productLabel = "Ekalink Super";
        }

        setInvestmentTitleArr( new String[] {
            "",
            productLabel + " Fixed Income Fund",
            productLabel + " Dynamic Fund",
            productLabel + " Aggressive Fund",
            productLabel + " Secure Dollar Income Fund",
            productLabel + " Dynamic Dollar Fund"
        } );
        setInvestmentTitleAndDescrArr( new String[][] {
            { "", "", "" },
            { "", "- " + productLabel + " Fixed Income Fund : 100% Fixed Income", "  Penempatan dana pada investasi pendapatan tetap dan instrumen pasar uang." },
            { "", "- " + productLabel + " Dynamic Fund : minimum 50% Fixed Income dan maksimum 50% Ekuitas", "  Penempatan dana pada investasi pendapatan tetap, ekuitas serta instrumen pasar uang." },
            { "", "- " + productLabel + " Aggressive Fund : maksimum 100% Fixed Income Rupiah atau maksimum 50% Fixed", "  Income US Dollar atau maksimum 100% Ekuitas" },
            { "", "- " + productLabel + " Secure Dollar Income Fund : 100% Fixed Income.", null },
            { "", "- " + productLabel + " Dynamic Dollar Fund : minimum 50% Fixed Income dan maksimum 50% Ekuitas.", null },
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