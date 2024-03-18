package id.co.sinarmaslife.eproposal.product.product01040119;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040119Mapper
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Dec 30, 2008 10:07:11 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Mapper;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000MapperInterface;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.product.product01040118.Cepr01040118Entry;

public class Cepr01040119Mapper extends Cepr01040000Mapper implements Cepr01040000MapperInterface
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
    public static final int X10 = 10;
    public static final int X11 = 11;
    public static final int X12 = 12;
    public static final int X13 = 13;
    public static final int X14 = 14;
    public static final int X15 = 15;
    public static final int X16 = 16;
    public static final int X17 = 17;
    public static final int X18 = 18;
    public static final int X19 = 19;
    public static final int X20 = 20;

    public Cepr01040119Mapper( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        standardMapping( X1, X1, new Cepr01040119Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X2, X2, new Cepr01040119Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X3, X3, new Cepr01040119Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X4, X4, new Cepr01040119Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X5, X5, new Cepr01040119Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X6, X6, new Cepr01040119Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X7, X7, new Cepr01040119Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X8, X8, new Cepr01040119Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X9, X9, new Cepr01040119Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X10, X10, new Cepr01040119Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X11, X11, new Cepr01040119Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X12, X12, new Cepr01040119Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X13, X13, new Cepr01040119Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X14, X14, new Cepr01040119Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X15, X15, new Cepr01040119Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X16, X16, new Cepr01040119Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X17, X17, new Cepr01040119Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X18, X18, new Cepr01040119Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X19, X19, new Cepr01040119Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X20, X20, new Cepr01040119Entry( eproposalManager, editorUtil, command, errors ) );
    }

}
