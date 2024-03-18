package id.co.sinarmaslife.eproposal.product.product01040113;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040112Mapper
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : Fadly Mathendra
 * Version              : 1.0
 * Creation Date    	: Okt 27, 2008 9:24:12 AM
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

public class Cepr01040113Mapper extends Cepr01040000Mapper implements Cepr01040000MapperInterface
{
    public static final int X2 = 2;
    public static final int X3 = 3;
    public static final int X4 = 4;    
    public static final int X9 = 9;
    public static final int X10 = 10;
    public static final int X11 = 11;  

    public Cepr01040113Mapper( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        // Personal accident
        standardMapping( X2, X2, new Cepr01040113Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X3, X3, new Cepr01040113Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X4, X4, new Cepr01040113Entry( eproposalManager, editorUtil, command, errors ) );
        
        standardMapping( X9, X9, new Cepr01040113Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X10, X10, new Cepr01040113Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X11, X11, new Cepr01040113Entry( eproposalManager, editorUtil, command, errors ) );
    }

}
