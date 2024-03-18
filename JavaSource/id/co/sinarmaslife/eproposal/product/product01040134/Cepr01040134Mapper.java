package id.co.sinarmaslife.eproposal.product.product01040134;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040134Mapper
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

public class Cepr01040134Mapper extends Cepr01040000Mapper implements Cepr01040000MapperInterface
{
    public static final int X19 = 19;
    public static final int X20 = 20;
    public static final int X21 = 21;
    public static final int X22 = 22;
    public static final int X23 = 23;
    public static final int X24 = 24;

    public Cepr01040134Mapper( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        standardMapping( X19, X19, new Cepr01040134Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X20, X20, new Cepr01040134Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X21, X21, new Cepr01040134Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X22, X22, new Cepr01040134Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X23, X23, new Cepr01040134Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X24, X24, new Cepr01040134Entry( eproposalManager, editorUtil, command, errors ) );
    }

}