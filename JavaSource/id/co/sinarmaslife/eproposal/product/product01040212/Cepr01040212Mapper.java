package id.co.sinarmaslife.eproposal.product.product01040212;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040212Mapper
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

public class Cepr01040212Mapper extends Cepr01040000Mapper implements Cepr01040000MapperInterface
{
	public static final int X7 = 7;
    public static final int X8 = 8;
    public static final int X9 = 9;
    public static final int X10 = 10;

    public Cepr01040212Mapper( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        standardMapping( X7, 7, new Cepr01040212X7Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X8, 8, new Cepr01040212X8Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X9, 9, new Cepr01040212X9Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X10, 10, new Cepr01040212X10Entry( eproposalManager, editorUtil, command, errors ) );
    }

}