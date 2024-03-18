package id.co.sinarmaslife.eproposal.business;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01030103Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Aug 30, 2007 9:21:02 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 * 1.1          9-12-2008                   Andy                tambah filter untuk platinum link
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.lookup.LookupList;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030103Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.standard.model.vo.OptionVO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cepr01030103Business
{
    protected final Log logger = LogFactory.getLog( getClass() );

    protected EproposalManager eproposalManager;
    protected Cepr00000000EditorUtil editorUtil;

    public Cepr01030103Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil )
    {
        this.eproposalManager = eproposalManager;
        this.editorUtil = editorUtil;
    }

    public void resetForm( Cepr01030103Form cepr01030103Form )
    {
        logger.info( "*-*-*-* Cepr01030103Business.resetForm" );
        cepr01030103Form.setPaClassList( LookupList.getSequenceNumberList( 1, 4, false ) );
        cepr01030103Form.setPaUnitQtyList( LookupList.getSequenceNumberList( 1, 4, false ) );

        cepr01030103Form.setPaRiskList( LookupList.getPaRiskList() );

        cepr01030103Form.setCiFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setHcpFamilyFlag( CommonConst.CHECKED_FALSE );

        cepr01030103Form.setHcpProviderFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setHcpFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setPaFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setPayorCiDeathFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setPayorCiFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setPayorTpdDeathFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setPayorTpdCiDeathFlag(CommonConst.CHECKED_FALSE);
        cepr01030103Form.setTpdFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setWaiverCiFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setWaiverTpdFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setWaiverTpdCiFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setTermRiderFlag( CommonConst.CHECKED_FALSE );
        
        cepr01030103Form.setPayorSpouseTpdDeathFlag( CommonConst.CHECKED_FALSE ); 
        cepr01030103Form.setWaiver5Ci10CiFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setWaiver5Tpd10CiFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setWaiver5Tpd10TpdFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setPayor5Ci10CiDeathFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setPayor5Tpd10CiDeathFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setPayor5Tpd10TpdDeathFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setEkaSehatFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setEkaSehatInnerLimitFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setBabyFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setEarlyStageCi99Flag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setMedicalPlusFlag(CommonConst.CHECKED_FALSE );
        cepr01030103Form.setMedicalPlusRjFlag(CommonConst.CHECKED_FALSE );
        cepr01030103Form.setMedicalPlusRgFlag(CommonConst.CHECKED_FALSE );
        cepr01030103Form.setMedicalPlusRbFlag(CommonConst.CHECKED_FALSE );
        cepr01030103Form.setMedicalPlusPkFlag(CommonConst.CHECKED_FALSE );
        
        cepr01030103Form.setHcpLadiesFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setLadiesInsFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setScholarshipFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setLadiesMedExpenseFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setLadiesMedExpenseInnerLimitFlag( CommonConst.CHECKED_FALSE );
        cepr01030103Form.setEkaSehatExtraFlag( CommonConst.CHECKED_FALSE );

        cepr01030103Form.setPaRiskListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setPaClassListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setPaUnitQtyListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setHcpListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setHcpFamilyListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setHcpLadiesListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setLadiesInsChooseListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setLadiesInsListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setScholarshipChooseListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setScholarshipListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setHcpProviderListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setEkaSehatListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setEkaSehatInnerLimitListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setLadiesMedExpenseListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setLadiesMedExpenseInnerLimitListIsDisabled( CommonConst.DISABLED_TRUE );
//        cepr01030103Form.setEkaSehatProviderIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setTermRiderAmountIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setCiRiderAmountIsDisabled( CommonConst.DISABLED_TRUE ); /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
        cepr01030103Form.setEsci99RiderAmountIsDisabled( CommonConst.DISABLED_TRUE ); /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
        cepr01030103Form.setButtonClassIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setButtonParticipantIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setCiChooseListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setWaiverTpdCiChooseListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setBabyChooseListIsDisabled(CommonConst.DISABLED_TRUE );
        cepr01030103Form.setBabyListIsDisabled(CommonConst.DISABLED_TRUE );
        cepr01030103Form.setEkaSehatExtraListIsDisabled( CommonConst.DISABLED_TRUE );
                
        cepr01030103Form.setHcpLadiesListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setLadiesInsListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setScholarshipListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setScholarshipChooseListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setLadiesMedExpenseListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setLadiesMedExpenseInnerLimitListIsDisabled( CommonConst.DISABLED_TRUE );
        
        
        cepr01030103Form.setCiFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setHcpFamilyFlagIsDisabled( CommonConst.DISABLED_FALSE );

        cepr01030103Form.setHcpProviderFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setHcpFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setPaFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setPayorCiDeathFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setPayorCiFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setPayorTpdDeathFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setPayorTpdCiDeathFlagIsDisabled(CommonConst.DISABLED_FALSE);
        cepr01030103Form.setTpdFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setWaiverCiFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setWaiverTpdFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setWaiverTpdCiFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setTermRiderFlagIsDisabled( CommonConst.DISABLED_FALSE );
        
        cepr01030103Form.setPayorSpouseTpdDeathFlagIsDisabled( CommonConst.DISABLED_FALSE ); 
        cepr01030103Form.setWaiver5Ci10CiFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setWaiver5Tpd10CiFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setWaiver5Tpd10TpdFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setPayor5Ci10CiDeathFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setPayor5Tpd10CiDeathFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setPayor5Tpd10TpdDeathFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setEkaSehatFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setEkaSehatInnerLimitFlagIsDisabled( CommonConst.DISABLED_FALSE );
        
        cepr01030103Form.setHcpLadiesFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setLadiesInsFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setScholarshipFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setLadiesMedExpenseFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setLadiesMedExpenseInnerLimitFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setBabyFlagIsDisabled( CommonConst.DISABLED_FALSE );        
        cepr01030103Form.setEarlyStageCi99FlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setEsci99ChooseListIsDisabled( CommonConst.DISABLED_TRUE );
        
        cepr01030103Form.setMedicalPlusFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setMedicalPlusChooseListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setMedicalPlusRjFlagIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setMedicalPlusRgFlagIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setMedicalPlusRbFlagIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setMedicalPlusPkFlagIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setEkaSehatExtraFlagIsDisabled( CommonConst.DISABLED_FALSE );
        
        cepr01030103Form.setTermRiderAmount( null );
        cepr01030103Form.setCiRiderAmount( null );/**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
        cepr01030103Form.setEsci99RiderAmount( null );/**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
        
        /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
        cepr01030103Form.setTerm5575RiderChooseListIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030103Form.setTerm5575RiderFlagIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030103Form.setTerm5575RiderAmountIsDisabled( CommonConst.DISABLED_TRUE );

        cepr01030103Form.setTerm5575RiderAmount( null );
    }
    

    public void fillDefaultValueEachTimeFormCalled( Object command )
    {
        logger.info( "*-*-*-* Cepr01030103Business.fillDefaultValueEachTimeFormCalled" );
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        Cepr01030103Form cepr01030103Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030103Form();
        CredentialsVO credentialsVO = ( ( Cepr01030000HoldingForm ) command ).getCredentialsVO();

        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            cepr01030103Form.setHcpList( LookupList.getHcpIdrList() );
            cepr01030103Form.setHcpFamilyList( LookupList.getHcpFamilyIdrList() );
            cepr01030103Form.setHcpLadiesList( LookupList.getHcpLadiesIdrList() );
            cepr01030103Form.setLadiesInsList( LookupList.getLadiesInsList() );
            cepr01030103Form.setScholarshipList( LookupList.getScholarshipList() );
            cepr01030103Form.setScholarshipChooseList( LookupList.getScholarshipChooseList() );
            /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
            cepr01030103Form.setTerm5575RiderChooseList( LookupList.getTerm5575RiderChooseList() );
            cepr01030103Form.setHcpProviderList( LookupList.getHcpProviderIdrList() );
            if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_POWER_SAVE || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_POWER_SAVE_BULANAN || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_STABLE_SAVE ){
            	cepr01030103Form.setCiChooseList( LookupList.getCiChooseList().subList( 0, 1 ) );
            	cepr01030103Form.setEkaSehatList( LookupList.getEkaSehatIdrList() );
            }else if( ( cepr01030102Form.getLeftPartOfBusinessCd() == 120 || cepr01030102Form.getLeftPartOfBusinessCd() == 202 ) && credentialsVO != null && credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_BANCASS ) ){
                if( cepr01030102Form.getSmileLadiesPackageCd() != null && cepr01030102Form.getSmileLadiesPackageCd() > 1 ){
                	cepr01030103Form.setEkaSehatList( LookupList.getEkaSehatIdrList().subList(12, LookupList.getEkaSehatIdrList().size()) );
                	cepr01030103Form.setCiChooseList( LookupList.getCiChooseList().subList( 5, LookupList.getCiChooseList().size() ) );
                	cepr01030103Form.setLadiesInsChooseList( LookupList.getLadiesInsChooseList().subList( 5, LookupList.getLadiesInsChooseList().size()) );
                }else{
                	cepr01030103Form.setEkaSehatList( LookupList.getEkaSehatIdrList() );
                	cepr01030103Form.setCiChooseList( LookupList.getCiChooseList() );
                	cepr01030103Form.setLadiesInsChooseList( LookupList.getLadiesInsChooseList() );
                }
            }else if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99 ){
        		cepr01030103Form.setCiChooseList( LookupList.getCiChooseExtList() );
        		cepr01030103Form.setEkaSehatList( LookupList.getEkaSehatIdrList() );
        		cepr01030103Form.setLadiesInsChooseList( LookupList.getLadiesInsChooseList() );
            }else{
            	cepr01030103Form.setEkaSehatList( LookupList.getEkaSehatIdrList() );
            	cepr01030103Form.setCiChooseList( LookupList.getCiChooseList() );
            	cepr01030103Form.setLadiesInsChooseList( LookupList.getLadiesInsChooseList() );
            }
            cepr01030103Form.setEkaSehatInnerLimitList( LookupList.getEkaSehatInnerLimitIdrList() );
            cepr01030103Form.setLadiesMedExpenseList( LookupList.getLadiesMedExpenseIdrList() );
            cepr01030103Form.setLadiesMedExpenseInnerLimitList( LookupList.getLadiesMedExpenseInnerLimitIdrList() );
            cepr01030103Form.setWaiverTpdCiChooseList( LookupList.getWaiverTpdChooseList() );
            cepr01030103Form.setBabyList(LookupList.getBabyList());
            cepr01030103Form.setBabyChooseList(LookupList.getBabyChooseList());
        	cepr01030103Form.setEsci99ChooseList( LookupList.getEsci99ChooseList() );
        	cepr01030103Form.setMedicalPlusChooseList( LookupList.getMedicalPlusChooseList() );
//            cepr01030103Form.setEkaSehatProviderList( LookupList.getEkaSehatProviderList() );          
        	cepr01030103Form.setEkaSehatExtraList( LookupList.getEkaSehatExtraList() );
            // TODO
            // this is a dirty way for OOP, please repair next time
            if( cepr01030102Form.getAssurancePlanCd().equals( "159~X4" ) )
            {
                cepr01030103Form.setHcpList( LookupList.getHcpIdrList().subList( 1, 2 ) );
            }
            // filter untuk platinum link
            if( cepr01030102Form.getAssurancePlanCd().equals( "134~X1" ) )
            {
            	List< OptionVO > optionList = new ArrayList< OptionVO >();
            	optionList.add(new OptionVO( "", "None" ));
            	optionList.add(new OptionVO( "5", "R-500" ));
            	optionList.add(new OptionVO( "10", "R-1000" ));
                cepr01030103Form.setHcpList( optionList );
            }
            // filter untuk amanah link
            if( cepr01030102Form.getAssurancePlanCd().equals( "166~X1" ) )
            {
            	List< OptionVO > optionList = new ArrayList< OptionVO >();
            	optionList.add(new OptionVO( "", "None" ));
            	optionList.add(new OptionVO( "1", "R-100" ));
            	optionList.add(new OptionVO( "2", "R-200" ));
            	optionList.add(new OptionVO( "3", "R-300" ));
            	optionList.add(new OptionVO( "4", "R-400" ));
            	optionList.add(new OptionVO( "5", "R-500" ));
            	optionList.add(new OptionVO( "10", "R-1000" ));
                cepr01030103Form.setHcpList( optionList );
            }
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            cepr01030103Form.setHcpList( LookupList.getHcpUsdList() );
            cepr01030103Form.setHcpFamilyList( LookupList.getHcpFamilyUsdList() );
            cepr01030103Form.setHcpLadiesList( LookupList.getHcpLadiesUsdList() );
            cepr01030103Form.setLadiesInsList( LookupList.getLadiesInsList() );
            cepr01030103Form.setScholarshipList( LookupList.getScholarshipList() );
            cepr01030103Form.setScholarshipChooseList( LookupList.getScholarshipChooseList() );
            /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
            cepr01030103Form.setTerm5575RiderChooseList( LookupList.getTerm5575RiderChooseList() );
            cepr01030103Form.setHcpProviderList( LookupList.getHcpProviderUsdList() );
            cepr01030103Form.setEkaSehatList( LookupList.getEkaSehatUsdList() );
            cepr01030103Form.setEkaSehatInnerLimitList( LookupList.getEkaSehatInnerLimitUsdList() );
            cepr01030103Form.setLadiesMedExpenseList( LookupList.getLadiesMedExpenseUsdList() );
            cepr01030103Form.setLadiesMedExpenseInnerLimitList( LookupList.getLadiesMedExpenseInnerLimitUsdList() );
            if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_POWER_SAVE || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_POWER_SAVE_BULANAN || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_STABLE_SAVE ){
            	cepr01030103Form.setCiChooseList( LookupList.getCiChooseList().subList( 0, 1 ) );
            }else if( ( cepr01030102Form.getLeftPartOfBusinessCd() == 120 || cepr01030102Form.getLeftPartOfBusinessCd() == 202 ) && credentialsVO != null && credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_BANCASS ) &&
            	cepr01030102Form.getSmileLadiesPackageCd() != null && cepr01030102Form.getSmileLadiesPackageCd() > 1){
                cepr01030103Form.setCiChooseList( LookupList.getCiChooseList().subList( 5, LookupList.getCiChooseList().size() ) );
                cepr01030103Form.setLadiesInsChooseList( LookupList.getLadiesInsChooseList().subList( 5, LookupList.getLadiesInsChooseList().size()) );
            }else if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99 ){
        		cepr01030103Form.setCiChooseList( LookupList.getCiChooseExtList() );
        		cepr01030103Form.setEkaSehatList( LookupList.getEkaSehatUsdList() );
        		cepr01030103Form.setLadiesInsChooseList( LookupList.getLadiesInsChooseList() );
            }else{
            	cepr01030103Form.setCiChooseList( LookupList.getCiChooseList() );
            	cepr01030103Form.setLadiesInsChooseList( LookupList.getLadiesInsChooseList() );
            }
            cepr01030103Form.setWaiverTpdCiChooseList( LookupList.getWaiverTpdChooseList() );
//            cepr01030103Form.setEkaSehatProviderList( LookupList.getEkaSehatProviderList() );
            cepr01030103Form.setBabyChooseList(LookupList.getBabyChooseList());
            cepr01030103Form.setBabyList(LookupList.getBabyChooseList());
            cepr01030103Form.setEsci99ChooseList( LookupList.getEsci99ChooseList() );
            cepr01030103Form.setMedicalPlusChooseList( LookupList.getMedicalPlusChooseList() );
            
            // filter untuk platinum link
            if( cepr01030102Form.getAssurancePlanCd().equals( "134~X1" ) )
            {
            	List< OptionVO > optionList = new ArrayList< OptionVO >();
            	optionList.add(new OptionVO( "", "None" ));
            	optionList.add(new OptionVO( "15", "D-50" ));
            	optionList.add(new OptionVO( "20", "D-100" ));
                cepr01030103Form.setHcpList( optionList );
            }
        }
        else
        {
            cepr01030103Form.setHcpList( LookupList.getEmptyOptionVOList() );
        }

        if( cepr01030103Form.getTermRiderAmount() == null )
        {
            cepr01030103Form.setTermRiderAmount( cepr01030102Form.getTotalSumInsured() );
        }
        
        if( cepr01030103Form.getCiRiderAmount() == null )/**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
        {
            cepr01030103Form.setCiRiderAmount( new BigDecimal (7500000) );
        }
        
        if( cepr01030103Form.getEsci99RiderAmount() == null )/**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
        {
            cepr01030103Form.setEsci99RiderAmount( new BigDecimal (7500000) );
        }
        
        /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
        if( cepr01030103Form.getTerm5575RiderAmount() == null  )/**Pembukaan UP untuk rider Term 55/65/75**/
        {
            cepr01030103Form.setTerm5575RiderAmount( new BigDecimal (7500000) );
        }
    }
}