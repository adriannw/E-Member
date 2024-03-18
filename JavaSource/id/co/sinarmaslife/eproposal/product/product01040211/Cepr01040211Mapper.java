package id.co.sinarmaslife.eproposal.product.product01040211;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040211Mapper
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 10, 2009 3:24:23 PM
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

public class Cepr01040211Mapper extends Cepr01040000Mapper implements Cepr01040000MapperInterface
{
	public static final int X7 = 7;
    public static final int X8 = 8;
    public static final int X9 = 9;
    public static final int X10 = 10;
    public static final int X11 = 11;
    public static final int X12 = 12;
    
    public static final int X13 = 13;
    public static final int X14 = 14;
    public static final int X15 = 15;
    public static final int X19 = 19;
    public static final int X20 = 20;
    public static final int X21 = 21;
    public static final int X22 = 22;
    public static final int X23 = 23;
    public static final int X24 = 24;

    public Cepr01040211Mapper( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        standardMapping( X7, 7, new Cepr01040211X7Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X8, 8, new Cepr01040211X8Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X9, 9, new Cepr01040211X9Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X10, 10, new Cepr01040211X22Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X11, 11, new Cepr01040211X23Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X12, 12, new Cepr01040211X24Entry( eproposalManager, editorUtil, command, errors ) );
        
        standardMapping( X13, 13, new Cepr01040211X13Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X14, 14, new Cepr01040211X14Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X15, 15, new Cepr01040211X15Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X19, 19, new Cepr01040211X22Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X20, 20, new Cepr01040211X23Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X21, 21, new Cepr01040211X24Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X22, 22, new Cepr01040211X22Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X23, 23, new Cepr01040211X23Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X24, 24, new Cepr01040211X24Entry( eproposalManager, editorUtil, command, errors ) );
//        standardMapping( X22, 22, new CeprMasterEntry( eproposalManager, editorUtil, command, errors ) );
//        standardMapping( X23, 23, new CeprMasterEntry( eproposalManager, editorUtil, command, errors ) );
//        standardMapping( X24, 24, new CeprMasterEntry( eproposalManager, editorUtil, command, errors ) );
    }

}