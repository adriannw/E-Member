package id.co.sinarmaslife.eproposal.product.product01040117;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040117Mapper
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

public class Cepr01040117Mapper extends Cepr01040000Mapper implements Cepr01040000MapperInterface
{
    public static final int X8 = 8;
    public static final int X9 = 9;
    public static final int X4 = 4;

    public Cepr01040117Mapper( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        // MAXI INVEST US DOLLAR
        standardMapping( X8, X8, new Cepr01040117X2Entry( eproposalManager, editorUtil, command, errors ) );
        // MAXI DEPOSIT US DOLLAR (Agency)
        standardMapping( X9, X9, new Cepr01040117X3Entry( eproposalManager, editorUtil, command, errors ) );
//      MAXI DEPOSIT US DOLLAR (Corporate)
        standardMapping( X4, X4, new Cepr01040117X4Entry( eproposalManager, editorUtil, command, errors ) );
    }

}
