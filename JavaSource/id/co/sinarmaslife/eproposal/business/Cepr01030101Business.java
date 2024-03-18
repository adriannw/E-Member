package id.co.sinarmaslife.eproposal.business;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01030101Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 30, 2007 10:31:35 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.lookup.Cepr00000000ComboLookupBusiness;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.HtmlUtil;

import java.util.List;
import java.util.ArrayList;

public class Cepr01030101Business
{
    protected final Log logger = LogFactory.getLog( getClass() );

    protected EproposalManager eproposalManager;
    protected Cepr00000000EditorUtil editorUtil;

    public Cepr01030101Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil )
    {
        this.eproposalManager = eproposalManager;
        this.editorUtil = editorUtil;
    }

    public void resetForm( Cepr01030101Form cepr01030101Form, Cepr00000000GeneralBusiness cepr00000000GeneralBusiness, Cepr00000000ComboLookupBusiness cepr00000000ComboLookupBusiness, CredentialsVO credentialsVO )
    {
        logger.info( "*-*-*-* Cepr01030101Business.resetForm" );
        cepr01030101Form.setProposalDate( cepr00000000GeneralBusiness.selectNowDate() );
        cepr01030101Form.setGenderList( new ArrayList(cepr00000000ComboLookupBusiness.getGenderOptionVOList( true ) ));
        //credentialsVO = new CredentialsVO();
        //credentialsVO.setMsagId("999996");
        //credentialsVO.setPassword("dmtmuser");
        //credentialsVO.setUserName("dmtmuser");
        List<OptionVO> assurancePlanList = cepr00000000ComboLookupBusiness.selectLstBusinessOptionVOList( true, null, credentialsVO );
        List<OptionVO> assurancePlanPackageList = new ArrayList<OptionVO>();
        cepr01030101Form.setAssurancePlanList(
        		new ArrayList(HtmlUtil.addEmptyOption2( filterBusinessPlanByUserAccessRight( assurancePlanList, credentialsVO ) )) );
        Integer flag_display_package = 0;
        for( int i = 0 ; i < cepr01030101Form.getAssurancePlanList().size() ; i++ ){
        	if( cepr01030101Form.getAssurancePlanList().get(i).getValue().length() > 3 ){
        		String value = cepr01030101Form.getAssurancePlanList().get(i).getValue();
        		if( cepr01030101Form.getAssurancePlanList().get(i).getValue().substring(0, 3).equals("159") ){
        			assurancePlanPackageList.add( new OptionVO( value, "SMiLe Ladies Exclusive") );
        			flag_display_package += 1;
        		}
        		if( cepr01030101Form.getAssurancePlanList().get(i).getValue().substring(0, 3).equals("160") ){
        			assurancePlanPackageList.add( new OptionVO( value, "SMiLe Ladies Exclusive Syariah") );
        			flag_display_package += 1;
        		}
        		if( cepr01030101Form.getAssurancePlanList().get(i).getValue().substring(0, 3).equals("116") ){
        			assurancePlanPackageList.add( new OptionVO( value, "SMiLe Pension") );
        			flag_display_package += 1;
        		}
        		if( credentialsVO != null && credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_TIM_HAFRI ) ){
        			if( cepr01030101Form.getAssurancePlanList().get(i).getValue().substring(0, 3).equals("153") ){
            			assurancePlanPackageList.add( new OptionVO( value, "Paket Ibadah") );
            			flag_display_package += 1;
            		}
        		}else{
        			if( cepr01030101Form.getAssurancePlanList().get(i).getValue().substring(0, 3).equals("153") ){
            			assurancePlanPackageList.add( new OptionVO( value, "SMiLe Pension Syariah") );
            			flag_display_package += 1;
            		}
        		}
        	}
        }
        if( credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals(Hardcode.GROUP_MNC)){
        	cepr01030101Form.setAssurancePlanPackageListDisplay( CommonConst.DISPLAY_FALSE );
        }else{
        	 cepr01030101Form.setAssurancePlanPackageList( new ArrayList( HtmlUtil.addEmptyOption2( assurancePlanPackageList )) );
             if( flag_display_package > 0 ){
             	cepr01030101Form.setAssurancePlanPackageListDisplay( CommonConst.DISPLAY_TRUE );
             }else{
             	cepr01030101Form.setAssurancePlanPackageListDisplay( CommonConst.DISPLAY_FALSE );
             }
        }
        
        if( credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals(Hardcode.GROUP_SEKURITAS)){
        	cepr01030101Form.setDownloadLinkDisplay( CommonConst.DISPLAY_TRUE );
        }else{
        	cepr01030101Form.setDownloadLinkDisplay( CommonConst.DISPLAY_FALSE );
        }
    }

    public List<OptionVO> filterBusinessPlanByUserAccessRight( List<OptionVO> assurancePlanList, CredentialsVO credentialsVO )
    {
        logger.info( "*-*-*-* Cepr01030101Business.filterProductsByUserAccessRight" );
        List<OptionVO> result = new ArrayList<OptionVO>();

        List<String> filterList = eproposalManager.selectLstBusinessFilteredOptionVOList( credentialsVO.getMsagId(), credentialsVO.getGroupId() );
        for( int i = 0 ; i < filterList.size() ; i ++ )
        {
        	if( filterList.get(i).equals("116==>EXCELLINK 80 PLUS")){
        		filterList.set(i, "116==>EKA LINK 80 PLUS");
        	}
        }
        for( OptionVO optionVO : assurancePlanList )
        {
            if( filterList.contains( optionVO.getValue() ) )
            {
                result.add( optionVO );
            }
        }

        return result;
    }
}
