package id.co.sinarmaslife.eproposal.product.product01040112;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040112Mapper
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Sep 3, 2008 9:24:12 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.springframework.validation.Errors;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Mapper;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000MapperInterface;

public class Cepr01040112Mapper extends Cepr01040000Mapper implements Cepr01040000MapperInterface
{
    public static final int X10 = 10;
    public static final int X11 = 11;
    public static final int X5 = 5;

    public Cepr01040112Mapper( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        // MAXI DEPOSIT RUPIAH 
        standardMapping( X10, X10, new Cepr01040112X3Entry( eproposalManager, editorUtil, command, errors ) );
        // MAXI INVEST RUPIAH (Agency)
        standardMapping( X11, X11, new Cepr01040112X4Entry( eproposalManager, editorUtil, command, errors ) );
//      MAXI INVEST RUPIAH (Corporate)
        standardMapping( X5, X5, new Cepr01040112X5Entry( eproposalManager, editorUtil, command, errors ) );
    }

}
