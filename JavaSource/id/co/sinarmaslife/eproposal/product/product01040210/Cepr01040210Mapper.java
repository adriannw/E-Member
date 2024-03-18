package id.co.sinarmaslife.eproposal.product.product01040210;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040210Mapper
 * Description         	: Excel Link 80 / Eka Link 80 (115)
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

public class Cepr01040210Mapper extends Cepr01040000Mapper implements Cepr01040000MapperInterface
{
    public static final int X4 = 4;
    public static final int X5 = 5;
    public static final int X6 = 6;

    public Cepr01040210Mapper( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        standardMapping( X4, 1, new Cepr01040210X4Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X5, 2, new Cepr01040210X5Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X6, 3, new Cepr01040210X6Entry( eproposalManager, editorUtil, command, errors ) );
    }

}