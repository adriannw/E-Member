package id.co.sinarmaslife.eproposal.product.product01040218;

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

public class Cepr01040218Mapper extends Cepr01040000Mapper implements Cepr01040000MapperInterface
{
	public static final int X1 = 1;
    public static final int X2 = 2;
    public static final int X3 = 3;

    public static final int X4 = 4;
    public static final int X5 = 5;
    public static final int X6 = 6;
    
    public static final int X7 = 7;
    public static final int X8 = 8;
        
    public Cepr01040218Mapper( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        standardMapping( X1, 1, new Cepr01040218X1Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X2, 2, new Cepr01040218X2Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X3, 3, new Cepr01040218X3Entry( eproposalManager, editorUtil, command, errors ) );
        
        standardMapping( X4, 4, new Cepr01040218X1Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X5, 5, new Cepr01040218X2Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X6, 6, new Cepr01040218X3Entry( eproposalManager, editorUtil, command, errors ) );
        //KEDAI LINK
        standardMapping( X7, 7, new Cepr01040218X1Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X8, 8, new Cepr01040218X2Entry( eproposalManager, editorUtil, command, errors ) );
        
    }

}