package id.co.sinarmaslife.eproposal.product.product01040203;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040203Mapper
 * Description         	: Artha/Eka Link 88 (162)
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

import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000MapperInterface;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Mapper;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import org.springframework.validation.Errors;

public class Cepr01040203Mapper extends Cepr01040000Mapper implements Cepr01040000MapperInterface
{
    public static final int X1 = 1;
    public static final int X2 = 2;
    public static final int X5 = 5;
    public static final int X6 = 6;
    public static final int X7 = 7;
    public static final int X8 = 8;

    public Cepr01040203Mapper( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        // Artha Link 88
        standardMapping( X1, X1, new Cepr01040203Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X2, X2, new Cepr01040203Entry( eproposalManager, editorUtil, command, errors ) );
        // Eka Link 88
        standardMapping( X5, X5, new Cepr01040203Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X6, X6, new Cepr01040203Entry( eproposalManager, editorUtil, command, errors ) );
        // Eka Link 88 Plus
        // I mapping X7 to 5 becoz illustration is the same as X5
        // I mapping X8 to 6 becoz illustration is the same as X6
        standardMapping( X7, 5, new Cepr01040203Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X8, 6, new Cepr01040203Entry( eproposalManager, editorUtil, command, errors ) );
    }

}