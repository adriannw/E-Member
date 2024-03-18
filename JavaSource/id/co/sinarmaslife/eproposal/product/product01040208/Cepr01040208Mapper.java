package id.co.sinarmaslife.eproposal.product.product01040208;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040208Mapper
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 4, 2008 9:22:20 AM
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
// Test Branch Adrian
public class Cepr01040208Mapper extends Cepr01040000Mapper implements Cepr01040000MapperInterface
{
    public final static int X1 = 1;
    public final static int X2 = 2;
    public final static int X3 = 3;
    public final static int X4 = 4;
    //SIMAS PRIME LINK + SMiLE LINK PROASSET + SMiLe LINK INVESTA
    public final static int X5 = 5;
    public final static int X6 = 6;
    public final static int X7 = 7;
    public final static int X8 = 8;
    public final static int X9 = 9;
    public final static int X10 = 10;
    public final static int X11 = 11;
    public final static int X12 = 12;
    
    public final static int X13 = 13;
    
    
    public Cepr01040208Mapper( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        standardMapping( X1, X1, new Cepr01040208Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X2, X2, new Cepr01040208Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X3, X3, new Cepr01040208Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X4, X4, new Cepr01040208Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X5, X5, new Cepr01040208Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X6, X6, new Cepr01040208Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X7, X7, new Cepr01040208Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X8, X8, new Cepr01040208Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X9, X9, new Cepr01040208Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X10, X10, new Cepr01040208Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X11, X11, new Cepr01040208Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X12, X12, new Cepr01040208Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X13, X13, new Cepr01040208Entry( eproposalManager, editorUtil, command, errors ) );
    }
}