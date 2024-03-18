package id.co.sinarmaslife.eproposal.product.product01040151;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01040139Mapper
 * Description         	: Pro safe (192)
 * Environment      	: Java  1.5.0_06
 * Author               : fadly m
 * Version              : 1.0
 * Creation Date    	: Jan 01, 2012 5:40:40 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000MapperInterface;
import id.co.sinarmaslife.eproposal.product.product01040150.Cepr01040150Entry;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Mapper;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import org.springframework.validation.Errors;

public class Cepr01040151Mapper extends Cepr01040000Mapper implements Cepr01040000MapperInterface
{
    public static final int X1 = 1;
    public static final int X2 = 2;


    public Cepr01040151Mapper( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        standardMapping( X1, X1, new Cepr01040151Entry( eproposalManager, editorUtil, command, errors ) );
        standardMapping( X2, X2, new Cepr01040151Entry( eproposalManager, editorUtil, command, errors ) );
    }

}