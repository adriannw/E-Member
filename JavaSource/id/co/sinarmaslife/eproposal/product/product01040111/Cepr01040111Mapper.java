package id.co.sinarmaslife.eproposal.product.product01040111;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040111Mapper
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Apr 18, 2008 2:50:22 PM
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

public class Cepr01040111Mapper extends Cepr01040000Mapper implements Cepr01040000MapperInterface
{
    public static final int X1 = 1;
    public static final int X2 = 2;
    public static final int X11 = 11;
    public static final int X12 = 12;
    //  Stable Link (DMTM)
    public static final int X7 = 7;

    public Cepr01040111Mapper( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        // Stable Link
        standardMapping( X1, X1, new Cepr01040111Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X2, X2, new Cepr01040111Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X11, X11, new Cepr01040111X11Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X12, X12, new Cepr01040111X11Entry( eproposalManager, editorUtil, command, errors ) );
        // Stable Link (DMTM)
        standardMapping( X7, X7, new Cepr01040111X7Entry( eproposalManager, editorUtil, command, errors ) );
    }

}
