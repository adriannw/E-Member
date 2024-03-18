package id.co.sinarmaslife.eproposal.product.product01040132;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040132Mapper
 * Description         	: Dana sejahtera (40)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 8, 2008 5:40:40 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Mapper;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000MapperInterface;
import id.co.sinarmaslife.eproposal.service.EproposalManager;

import org.springframework.validation.Errors;

public class Cepr01040132Mapper extends Cepr01040000Mapper implements Cepr01040000MapperInterface
{
    public static final int X19 = 19;
    public static final int X20 = 20;
    public static final int X21 = 21;
    public static final int X28 = 28;
    public static final int X29 = 29;
    public static final int X30 = 30;

    public Cepr01040132Mapper( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        standardMapping( X19, X19, new Cepr01040132Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X20, X20, new Cepr01040132Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X21, X21, new Cepr01040132Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X28, X28, new Cepr01040132Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X29, X29, new Cepr01040132Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X30, X30, new Cepr01040132Entry( eproposalManager, editorUtil, command, errors ) );
    }

}