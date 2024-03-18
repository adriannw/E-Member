package id.co.sinarmaslife.eproposal.product.product01040108;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040108Mapper
 * Description         	: Power Save Bulanan (158)
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

public class Cepr01040108Mapper extends Cepr01040000Mapper implements Cepr01040000MapperInterface
{
    public static final int X1 = 1;
    public static final int X2 = 2;
    public static final int X3 = 3;
    public static final int X4 = 4;
    public static final int X5 = 5;
    public static final int X6 = 6;
    public static final int X7 = 7;
    public static final int X8 = 8;
    public static final int X9 = 9;
    public static final int X13 = 13;

    public Cepr01040108Mapper( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        standardMapping( X1, X1, new Cepr01040108Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X2, X2, new Cepr01040108Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X3, X3, new Cepr01040108Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X4, X4, new Cepr01040108Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X5, X5, new Cepr01040108Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X6, X6, new Cepr01040108Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X7, X7, new Cepr01040108Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X8, X8, new Cepr01040108Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X9, X9, new Cepr01040108Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X13, X13, new Cepr01040108X13Entry( eproposalManager, editorUtil, command, errors ) );
    }

}