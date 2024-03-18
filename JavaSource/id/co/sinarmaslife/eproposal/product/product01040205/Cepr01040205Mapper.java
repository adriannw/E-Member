package id.co.sinarmaslife.eproposal.product.product01040205;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040205Mapper
 * Description         	: Eka Link 80 Plus (116)
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Feb 13, 2008 1:59:43 PM
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

public class Cepr01040205Mapper extends Cepr01040000Mapper implements Cepr01040000MapperInterface
{
    public static final int X3 = 3;
    public static final int X4 = 4;

    public Cepr01040205Mapper( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        standardMapping( X3, 3, new Cepr01040205X3Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X4, 4, new Cepr01040205X4Entry( eproposalManager, editorUtil, command, errors ) );
    }

}
