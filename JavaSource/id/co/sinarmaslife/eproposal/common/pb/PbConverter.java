package id.co.sinarmaslife.eproposal.common.pb;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: PbConverter
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Apr 22, 2008 10:41:43 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.business.Cepr00000000RiderBusiness;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.model.pb.Istr_prop;
import id.co.sinarmaslife.eproposal.model.pb.S_ekaSehat;
import id.co.sinarmaslife.eproposal.model.pb.S_ekaSehatExtra;
import id.co.sinarmaslife.eproposal.model.pb.S_ekaSehatInnerLimit;
import id.co.sinarmaslife.eproposal.model.pb.S_hcpLad;
import id.co.sinarmaslife.eproposal.model.pb.S_hcpf;
import id.co.sinarmaslife.eproposal.model.pb.S_ladiesMedExpense;
import id.co.sinarmaslife.eproposal.model.pb.S_ladiesMedExpenseInnerLimit;
import id.co.sinarmaslife.eproposal.model.pb.S_medicalPlus;
import id.co.sinarmaslife.eproposal.model.vo.ParticipantVO;
import id.co.sinarmaslife.eproposal.web.*;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.MappingUtil;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.std.util.DateUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import id.co.sinarmaslife.eproposal.model.pb.S_hcpPro;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PbConverter
{
    protected final Log logger = LogFactory.getLog( getClass() );
    
    public PbConverter()
    {
        logger.info( "*-*-*-* PbConverter constructor is called ..." );
    }

    public static Istr_prop get_istr_prop( Object command, int insuredAgeLimit )
    {
       
        Cepr01030101Form cepr01030101Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
        Cepr01030103Form cepr01030103Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030103Form();
        Cepr01030104Form cepr01030104Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030104Form();
        Cepr01030106Form cepr01030106Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030106Form();        
        Cepr01030107Form cepr01030107Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030107Form();       
        Cepr01030108Form cepr01030108Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030108Form();
        Cepr01030109Form cepr01030109Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030109Form();
        Cepr01030110Form cepr01030110Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030110Form();
        Cepr01030111Form cepr01030111Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030111Form();
        Cepr01030112Form cepr01030112Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030112Form();
        Cepr01030113Form cepr01030113Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030113Form();
        Cepr01030114Form cepr01030114Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030114Form();
        
        Istr_prop istr_prop = new Istr_prop();   
        
        if( ( cepr01030102Form.getLeftPartOfBusinessCd() == 190 && (cepr01030102Form.getRightPartOfBusinessCd() <= 2 || (cepr01030102Form.getRightPartOfBusinessCd() > 4 &&  cepr01030102Form.getRightPartOfBusinessCd() < 7) ) )
            	||  (cepr01030102Form.getLeftPartOfBusinessCd() == 134 && (cepr01030102Form.getRightPartOfBusinessCd() == 6 || cepr01030102Form.getRightPartOfBusinessCd() == 11 ))  	
            	||  (cepr01030102Form.getLeftPartOfBusinessCd() == 140 && (cepr01030102Form.getRightPartOfBusinessCd() == 1 || cepr01030102Form.getRightPartOfBusinessCd() == 2) ) 
            	||  (cepr01030102Form.getLeftPartOfBusinessCd() == 120 && (cepr01030102Form.getRightPartOfBusinessCd() >= 19 && cepr01030102Form.getRightPartOfBusinessCd() <= 21) ) )       
        {
        	istr_prop.fund[ 1 ] = LazyConverter.toDouble( cepr01030102Form.getFixIdr() );
 	        istr_prop.fund[ 2 ] = LazyConverter.toDouble( cepr01030102Form.getDynamicIdr() );
 	        istr_prop.fund[ 3 ] = LazyConverter.toDouble( cepr01030102Form.getAggresiveIdr() );
 	        istr_prop.fund[ 4 ] = LazyConverter.toDouble( cepr01030102Form.getCashFundIdr() );
 	        istr_prop.fund[ 5 ] = LazyConverter.toDouble( cepr01030102Form.getExcellinkEquityIdr() );
 	        istr_prop.fund[ 6 ] = LazyConverter.toDouble( cepr01030102Form.getSecureUsd() );
 	        istr_prop.fund[ 7 ] = LazyConverter.toDouble( cepr01030102Form.getDynamicUsd() ); 
        } 
        else if(( cepr01030102Form.getLeftPartOfBusinessCd() == 224 && (cepr01030102Form.getRightPartOfBusinessCd() == 1 || cepr01030102Form.getRightPartOfBusinessCd() == 2))
            	||	(cepr01030102Form.getLeftPartOfBusinessCd() == 190 && (cepr01030102Form.getRightPartOfBusinessCd() >= 5 && cepr01030102Form.getRightPartOfBusinessCd() <= 6))
            	||	( cepr01030102Form.getLeftPartOfBusinessCd() == 218 && cepr01030102Form.getRightPartOfBusinessCd() == 1)
            	||  (cepr01030102Form.getLeftPartOfBusinessCd() == 134 && (cepr01030102Form.getRightPartOfBusinessCd() == 6 || cepr01030102Form.getRightPartOfBusinessCd() == 11))  	
            	||  (cepr01030102Form.getLeftPartOfBusinessCd() == 202 && (cepr01030102Form.getRightPartOfBusinessCd() >= 4 && cepr01030102Form.getRightPartOfBusinessCd() <= 6)) 
            	||  (cepr01030102Form.getLeftPartOfBusinessCd() == 215 & cepr01030102Form.getRightPartOfBusinessCd() <= 3 ) 
            	||  (cepr01030102Form.getLeftPartOfBusinessCd() == 153 && (cepr01030102Form.getRightPartOfBusinessCd() == 3 || cepr01030102Form.getRightPartOfBusinessCd() == 4) ) 
            	||  (cepr01030102Form.getLeftPartOfBusinessCd() == 140 && (cepr01030102Form.getRightPartOfBusinessCd() == 1 || cepr01030102Form.getRightPartOfBusinessCd() == 2) )
            	||  (cepr01030102Form.getLeftPartOfBusinessCd() == 199 && (cepr01030102Form.getRightPartOfBusinessCd() == 1 || cepr01030102Form.getRightPartOfBusinessCd() == 2) ) 
            	||  (cepr01030102Form.getLeftPartOfBusinessCd() == 200 && cepr01030102Form.getRightPartOfBusinessCd() <= 4) ) 
            {
	        istr_prop.fund[ 1 ] = LazyConverter.toDouble( cepr01030102Form.getFixIdr() );
	        istr_prop.fund[ 2 ] = LazyConverter.toDouble( cepr01030102Form.getDynamicIdr() );
	        istr_prop.fund[ 3 ] = LazyConverter.toDouble( cepr01030102Form.getAggresiveIdr() );
	        istr_prop.fund[ 4 ] = LazyConverter.toDouble( cepr01030102Form.getCashFundIdr() );
	        istr_prop.fund[ 5 ] = LazyConverter.toDouble( cepr01030102Form.getSecureUsd() );
	        istr_prop.fund[ 6 ] = LazyConverter.toDouble( cepr01030102Form.getDynamicUsd() );
        }        
        else if( (cepr01030102Form.getLeftPartOfBusinessCd() == 220 && (cepr01030102Form.getRightPartOfBusinessCd() == 4 || cepr01030102Form.getRightPartOfBusinessCd() == 1 || cepr01030102Form.getRightPartOfBusinessCd() == 2)   ) 
        || (cepr01030102Form.getLeftPartOfBusinessCd() == 134 && (cepr01030102Form.getRightPartOfBusinessCd() == 10 || cepr01030102Form.getRightPartOfBusinessCd() >= 12 || cepr01030102Form.getRightPartOfBusinessCd() == 7  || cepr01030102Form.getRightPartOfBusinessCd() == 8) ) 
        || (cepr01030102Form.getLeftPartOfBusinessCd() == 190 && (cepr01030102Form.getRightPartOfBusinessCd() == 7 || cepr01030102Form.getRightPartOfBusinessCd() == 8 || cepr01030102Form.getRightPartOfBusinessCd() == 3 || cepr01030102Form.getRightPartOfBusinessCd() == 4 )) 
        || (cepr01030102Form.getLeftPartOfBusinessCd() == 116 && (cepr01030102Form.getRightPartOfBusinessCd() == 3 || cepr01030102Form.getRightPartOfBusinessCd() == 4 ))
        || (cepr01030102Form.getLeftPartOfBusinessCd() == 217 && (cepr01030102Form.getRightPartOfBusinessCd() == 1 ))
        || (cepr01030102Form.getLeftPartOfBusinessCd() == 118 && (cepr01030102Form.getRightPartOfBusinessCd() == 3 || cepr01030102Form.getRightPartOfBusinessCd() == 4 ))
        || (cepr01030102Form.getLeftPartOfBusinessCd() == 120 && (cepr01030102Form.getRightPartOfBusinessCd() >= 22 && cepr01030102Form.getRightPartOfBusinessCd() <= 24)) //IGA - PROJECT SIMPOL
        ){ //SIMAS PRIME LINK & SMiLe AssetPro(Agency)
        	istr_prop.fund[ 1 ] = LazyConverter.toDouble( cepr01030102Form.getFixIdr() );
 	        istr_prop.fund[ 2 ] = LazyConverter.toDouble( cepr01030102Form.getDynamicIdr() );
 	        istr_prop.fund[ 3 ] = LazyConverter.toDouble( cepr01030102Form.getAggresiveIdr() );
 	        istr_prop.fund[ 4 ] = LazyConverter.toDouble( cepr01030102Form.getFixSimasIdr() );
	        istr_prop.fund[ 5 ] = LazyConverter.toDouble( cepr01030102Form.getDynamicSimasIdr() );
	        istr_prop.fund[ 6 ] = LazyConverter.toDouble( cepr01030102Form.getAggresiveSimasIdr() ); 
	        istr_prop.fund[ 7 ] = LazyConverter.toDouble( cepr01030102Form.getCashFundIdr() ); 
	        istr_prop.fund[ 8 ] = LazyConverter.toDouble( cepr01030102Form.getExcellinkEquityIdr() );
 	        istr_prop.fund[ 9 ] = LazyConverter.toDouble( cepr01030102Form.getSecureUsd() );
 	        istr_prop.fund[ 10 ] = LazyConverter.toDouble( cepr01030102Form.getDynamicUsd() );        	
        }else if( (cepr01030102Form.getLeftPartOfBusinessCd() == 215 && cepr01030102Form.getRightPartOfBusinessCd() == 4)
        	|| (cepr01030102Form.getLeftPartOfBusinessCd() == 224 && cepr01030102Form.getRightPartOfBusinessCd() == 3) )
        {
            istr_prop.fund[ 1 ] = LazyConverter.toDouble( cepr01030102Form.getFixIdr() );
         	istr_prop.fund[ 2 ] = LazyConverter.toDouble( cepr01030102Form.getDynamicIdr() );
         	istr_prop.fund[ 3 ] = LazyConverter.toDouble( cepr01030102Form.getAggresiveIdr() );
         	istr_prop.fund[ 4 ] = LazyConverter.toDouble( cepr01030102Form.getFixSimasIdr() );
        	istr_prop.fund[ 5 ] = LazyConverter.toDouble( cepr01030102Form.getDynamicSimasIdr() );
        	istr_prop.fund[ 6 ] = LazyConverter.toDouble( cepr01030102Form.getAggresiveSimasIdr() ); 
        	istr_prop.fund[ 7 ] = LazyConverter.toDouble( cepr01030102Form.getCashFundIdr() ); 
         	istr_prop.fund[ 8 ] = LazyConverter.toDouble( cepr01030102Form.getSecureUsd() );
         	istr_prop.fund[ 9 ] = LazyConverter.toDouble( cepr01030102Form.getDynamicUsd() );        	
        }
        else if( cepr01030102Form.getLeftPartOfBusinessCd() == 213 )/**USD Fund BSIM Products**/
        {
	        istr_prop.fund[ 1 ] = LazyConverter.toDouble( cepr01030102Form.getFixIdr() );
	        istr_prop.fund[ 2 ] = LazyConverter.toDouble( cepr01030102Form.getDynamicIdr() );
	        istr_prop.fund[ 3 ] = LazyConverter.toDouble( cepr01030102Form.getAggresiveIdr() );
	        istr_prop.fund[ 4 ] = LazyConverter.toDouble( cepr01030102Form.getSecureUsd() );
	        istr_prop.fund[ 5 ] = LazyConverter.toDouble( cepr01030102Form.getDynamicUsd() );
	        istr_prop.fund[ 6 ] = LazyConverter.toDouble( cepr01030102Form.getAggresiveUsd() );/**USD Fund BSIM Products**/
        }
        else if(cepr01030102Form.getLeftPartOfBusinessCd() == 134 && cepr01030102Form.getRightPartOfBusinessCd() == 5 ){ /**USD Fund BSIM Products**/
                	istr_prop.fund[ 1 ] = LazyConverter.toDouble( cepr01030102Form.getFixIdr() );
         	        istr_prop.fund[ 2 ] = LazyConverter.toDouble( cepr01030102Form.getDynamicIdr() );
         	        istr_prop.fund[ 3 ] = LazyConverter.toDouble( cepr01030102Form.getAggresiveIdr() );
         	        istr_prop.fund[ 4 ] = LazyConverter.toDouble( cepr01030102Form.getFixSimasIdr() );
        	        istr_prop.fund[ 5 ] = LazyConverter.toDouble( cepr01030102Form.getDynamicSimasIdr() );
        	        istr_prop.fund[ 6 ] = LazyConverter.toDouble( cepr01030102Form.getAggresiveSimasIdr() ); 
        	        istr_prop.fund[ 7 ] = LazyConverter.toDouble( cepr01030102Form.getCashFundIdr() ); 
        	        istr_prop.fund[ 8 ] = LazyConverter.toDouble( cepr01030102Form.getExcellinkEquityIdr() );
         	        istr_prop.fund[ 9 ] = LazyConverter.toDouble( cepr01030102Form.getSecureUsd() );
         	        istr_prop.fund[ 10 ] = LazyConverter.toDouble( cepr01030102Form.getDynamicUsd() );
         	        istr_prop.fund[ 11 ] = LazyConverter.toDouble( cepr01030102Form.getAggresiveUsd() );/**USD Fund BSIM Products**/
        }
        else if( cepr01030102Form.getLeftPartOfBusinessCd() != 134 )
        {
	        istr_prop.fund[ 1 ] = LazyConverter.toDouble( cepr01030102Form.getFixIdr() );
	        istr_prop.fund[ 2 ] = LazyConverter.toDouble( cepr01030102Form.getDynamicIdr() );
	        istr_prop.fund[ 3 ] = LazyConverter.toDouble( cepr01030102Form.getAggresiveIdr() );
	        istr_prop.fund[ 4 ] = LazyConverter.toDouble( cepr01030102Form.getSecureUsd() );
	        istr_prop.fund[ 5 ] = LazyConverter.toDouble( cepr01030102Form.getDynamicUsd() );
        } 
        
        
        
        istr_prop.pct_premi = LazyConverter.toDouble( cepr01030102Form.getPremiumCombinationCd() );       
       //istr_prop.ins_per = insuredAgeLimit - cepr01030101Form.getInsuredAge();
       // istr_prop.umur_tt = cepr01030101Form.getInsuredAge();       
        istr_prop.up = cepr01030102Form.getTotalSumInsured() == null ? 0 : cepr01030102Form.getTotalSumInsured().doubleValue();
        istr_prop.cb_id = LazyConverter.toInt( cepr01030102Form.getPaymentModeCd() );
        istr_prop.nama_plan[ 1 ] = MappingUtil.getLabelProduct( cepr01030102Form.getAssurancePlanList(), cepr01030102Form.getAssurancePlanCd() );
        istr_prop.pa_risk = cepr01030103Form.getPaRiskCd() == null ? 0 : cepr01030103Form.getPaRiskCd();
        istr_prop.pa_class = cepr01030103Form.getPaClassCd() == null ? 0 : cepr01030103Form.getPaClassCd();
        istr_prop.kurs_id = cepr01030102Form.getCurrencyCd();
        istr_prop.premi = LazyConverter.toDouble( cepr01030102Form.getPremium() );
        istr_prop.umur_pp = cepr01030101Form.getPolicyHolderAge();
        istr_prop.up_term = LazyConverter.toDouble( cepr01030103Form.getTermRiderAmount() );
        istr_prop.cuti_premi =  cepr01030102Form.getPremiumFurloughYear();
        istr_prop.pct_invest =  LazyConverter.toDouble( cepr01030102Form.getInvestRateInPercent() );
        istr_prop.pay_per = cepr01030102Form.getTermOfPayment();
        istr_prop.tgl_prop =  new Date();//DateUtil.selectSysdate();//eproposalManager.selectNowDate();
        istr_prop.persen_ins = LazyConverter.toInt( cepr01030103Form.getLadiesInsChooseCd() );
        istr_prop.usia_schol = LazyConverter.toInt( cepr01030103Form.getScholarshipChooseCd() );
        istr_prop.up_schol = cepr01030103Form.getScholarshipCd() != null ? cepr01030103Form.getScholarshipCd().doubleValue() : 0.0;
        istr_prop.bln_ke  = LazyConverter.toInt(cepr01030103Form.getBabyChooseCd());
        istr_prop.umur_tt = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getBabyFlag() ) ? 0 : cepr01030101Form.getInsuredAge();;
        istr_prop.ins_per = insuredAgeLimit - istr_prop.umur_tt;
        /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
        istr_prop.up_term5575 = LazyConverter.toDouble( cepr01030103Form.getTerm5575RiderAmount() );
        
        List<ParticipantVO> participantVOList = cepr01030106Form.getParticipantVOList();
        participantVOList = Cepr00000000RiderBusiness.removeEmptyParticipantVOFromTheList( participantVOList );
        
        List<ParticipantVO> participantHcpLadiesVOList = cepr01030110Form.getParticipantVOList();
        participantHcpLadiesVOList = Cepr00000000RiderBusiness.removeEmptyParticipantVOFromTheList( participantHcpLadiesVOList );
        
        List<ParticipantVO> participantEkaSehatVOList = cepr01030107Form.getParticipantVOList();
        participantEkaSehatVOList = Cepr00000000RiderBusiness.removeEmptyParticipantVOFromTheList( participantEkaSehatVOList );
        
        List<ParticipantVO> participantHcpProviderVOList = cepr01030109Form.getParticipantVOList();
        participantHcpProviderVOList = Cepr00000000RiderBusiness.removeEmptyParticipantVOFromTheList( participantHcpProviderVOList );

        List<ParticipantVO> participantEkaSehatInnerLimitVOList = cepr01030108Form.getParticipantVOList();
        participantEkaSehatInnerLimitVOList = Cepr00000000RiderBusiness.removeEmptyParticipantVOFromTheList( participantEkaSehatInnerLimitVOList );
        
        List<ParticipantVO> participantLadiesMedExpenseVOList = cepr01030111Form.getParticipantVOList();
        participantLadiesMedExpenseVOList = Cepr00000000RiderBusiness.removeEmptyParticipantVOFromTheList( participantLadiesMedExpenseVOList );
        
        List<ParticipantVO> participantLadiesMedExpenseInnerLimitVOList = cepr01030112Form.getParticipantVOList();
        participantLadiesMedExpenseInnerLimitVOList = Cepr00000000RiderBusiness.removeEmptyParticipantVOFromTheList( participantLadiesMedExpenseInnerLimitVOList );
        
        List<ParticipantVO> participantMedicalPlusVOList = cepr01030113Form.getParticipantVOList();
        participantMedicalPlusVOList = Cepr00000000RiderBusiness.removeEmptyParticipantVOFromTheList( participantMedicalPlusVOList );
        
        List<ParticipantVO> participantEkaSehatExtraVOList = cepr01030114Form.getParticipantVOList();
        participantEkaSehatExtraVOList = Cepr00000000RiderBusiness.removeEmptyParticipantVOFromTheList( participantEkaSehatExtraVOList );
        
        S_hcpf hcpf = new S_hcpf();
        S_hcpLad hcpLad = new S_hcpLad();
        S_hcpPro hcpPro = new S_hcpPro();
        S_ekaSehat eka_sehat = new S_ekaSehat();
        S_medicalPlus medicalPlus = new S_medicalPlus();
        S_ekaSehatExtra eka_sehatExtra = new S_ekaSehatExtra();
        
        hcpPro.peserta = participantHcpProviderVOList.size();
        eka_sehat.peserta = participantEkaSehatVOList.size();
        eka_sehatExtra.peserta = participantEkaSehatExtraVOList.size();
        hcpLad.peserta = participantHcpLadiesVOList.size();
        hcpf.peserta = participantVOList.size();
        medicalPlus.peserta = participantMedicalPlusVOList.size();
        S_ekaSehatInnerLimit ekaSehatInnerLimit  = new S_ekaSehatInnerLimit();
        ekaSehatInnerLimit.peserta = participantEkaSehatInnerLimitVOList.size();
        S_ladiesMedExpense ladiesMedExpense = new S_ladiesMedExpense();
        ladiesMedExpense.peserta = participantLadiesMedExpenseVOList.size();
        S_ladiesMedExpenseInnerLimit ladiesMedExpenseInnerLimit = new S_ladiesMedExpenseInnerLimit();
        ladiesMedExpenseInnerLimit.peserta = participantLadiesMedExpenseInnerLimitVOList.size();
        for( int i = 0; i < participantVOList.size(); i++ )
        {
            ParticipantVO participantVO = participantVOList.get( i );
            hcpf.nama[ i + 1 ] = participantVO.getName();
            hcpf.tgl[ i + 1 ] = participantVO.getBirthDay();
            hcpf.usia[ i + 1 ] = participantVO.getAge() == null ? 0 : participantVO.getAge();
        }
        
        for( int i = 0; i < participantHcpLadiesVOList.size(); i++ )
        {
            ParticipantVO participantHcpLadiesVO = participantHcpLadiesVOList.get( i );
            hcpLad.nama[ i + 1 ] = participantHcpLadiesVO.getName();
            hcpLad.tgl[ i + 1 ] = participantHcpLadiesVO.getBirthDay();
            hcpLad.usia[ i + 1 ] = participantHcpLadiesVO.getAge() == null ? 0 : participantHcpLadiesVO.getAge();
        }
        
        for( int i = 0; i < participantHcpProviderVOList.size(); i++ )
        {
            ParticipantVO participantHcpProVO = participantHcpProviderVOList.get( i );
            hcpPro.nama[ i + 1 ] = participantHcpProVO.getName();
            hcpPro.tgl[ i + 1 ] = participantHcpProVO.getBirthDay();
            hcpPro.usia[ i + 1 ] = participantHcpProVO.getAge() == null ? 0 : participantHcpProVO.getAge();
        }
        
        for( int i = 0; i < participantEkaSehatVOList.size(); i++ )
        {
            ParticipantVO participantEkaSehatVO = participantEkaSehatVOList.get( i );
            eka_sehat.nama[ i + 1 ] = participantEkaSehatVO.getName();
            eka_sehat.tgl[ i + 1 ] = participantEkaSehatVO.getBirthDay();
            eka_sehat.usia[ i + 1 ] = participantEkaSehatVO.getAge() == null ? 0 : participantEkaSehatVO.getAge();
        }
        for( int i = 0; i < participantEkaSehatInnerLimitVOList.size(); i++ )
        {
            ParticipantVO participantEkaSehatILVO = participantEkaSehatInnerLimitVOList.get( i );
            ekaSehatInnerLimit.nama[ i + 1 ] = participantEkaSehatILVO.getName();
            ekaSehatInnerLimit.tgl[ i + 1 ] = participantEkaSehatILVO.getBirthDay();
            ekaSehatInnerLimit.usia[ i + 1 ] = participantEkaSehatILVO.getAge() == null ? 0 : participantEkaSehatILVO.getAge();
        }
        for( int i = 0; i < participantLadiesMedExpenseVOList.size(); i++ )
        {
        	ParticipantVO participantLadiesMedExpenseVO = participantLadiesMedExpenseVOList.get(i);
        	ladiesMedExpense.nama[ i + 1 ] = participantLadiesMedExpenseVO.getName();
        	ladiesMedExpense.tgl[ i + 1 ] = participantLadiesMedExpenseVO.getBirthDay();
        	ladiesMedExpense.usia[ i + 1 ] = participantLadiesMedExpenseVO.getAge() == null ? 0 : participantLadiesMedExpenseVO.getAge();
        }
        for( int i = 0; i < participantLadiesMedExpenseInnerLimitVOList.size(); i++ )
        {
        	ParticipantVO participantLadiesMedExpenseInnerLimitVO = participantLadiesMedExpenseInnerLimitVOList.get(i);
        	ladiesMedExpenseInnerLimit.nama[ i + 1 ] = participantLadiesMedExpenseInnerLimitVO.getName();
        	ladiesMedExpenseInnerLimit.tgl[ i + 1 ] = participantLadiesMedExpenseInnerLimitVO.getBirthDay();
        	ladiesMedExpenseInnerLimit.usia[ i + 1 ] = participantLadiesMedExpenseInnerLimitVO.getAge() == null ? 0 : participantLadiesMedExpenseInnerLimitVO.getAge();
        }
        for( int i = 0; i < participantMedicalPlusVOList.size(); i++ )
        {
            ParticipantVO participantMedicalPlusVO = participantMedicalPlusVOList.get( i );
            medicalPlus.nama[ i + 1 ] = participantMedicalPlusVO.getName();
            medicalPlus.tgl[ i + 1 ] = participantMedicalPlusVO.getBirthDay();
            medicalPlus.usia[ i + 1 ] = participantMedicalPlusVO.getAge() == null ? 0 : participantMedicalPlusVO.getAge();
            medicalPlus.gender[ i + 1 ] = participantMedicalPlusVO.getSexCd();
            medicalPlus.medicalPlusRbFlag[ i + 1 ] = participantMedicalPlusVO.getMedicalPlusRbFlag();
        }
        for( int i = 0; i < participantEkaSehatExtraVOList.size(); i++ )
        {
            ParticipantVO participantEkaSehatExtraVO = participantEkaSehatExtraVOList.get( i );
            eka_sehatExtra.nama[ i + 1 ] = participantEkaSehatExtraVO.getName();
            eka_sehatExtra.tgl[ i + 1 ] = participantEkaSehatExtraVO.getBirthDay();
            eka_sehatExtra.usia[ i + 1 ] = participantEkaSehatExtraVO.getAge() == null ? 0 : participantEkaSehatExtraVO.getAge();
        }
        
        istr_prop.hcpf = hcpf;
        istr_prop.hcpLad = hcpLad;
        istr_prop.hcpPro = hcpPro;
        istr_prop.eka_sehat = eka_sehat;
        istr_prop.eka_sehatExtra = eka_sehatExtra;
        istr_prop.ekaSehatInnerLimit = ekaSehatInnerLimit;
        istr_prop.ladiesMedExpense = ladiesMedExpense;
        istr_prop.ladiesMedExpenseInnerLimit = ladiesMedExpenseInnerLimit;
        istr_prop.medicalPlus = medicalPlus;
        istr_prop.bisnis_id = cepr01030102Form.getLeftPartOfBusinessCd();
        istr_prop.bisnis_no = LazyConverter.toInt( cepr01030102Form.getBisnisNoPbVersion() );

        istr_prop.rider_baru[ 1 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPaFlag() ) ? cepr01030103Form.getPaUnitQtyCd() : 0;
        istr_prop.rider_baru[ 2 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFlag() ) ? cepr01030103Form.getHcpCd() : 0;
        istr_prop.rider_baru[ 3 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTpdFlag() ) ? 1 : 0;
        istr_prop.rider_baru[ 4 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getCiFlag() ) ? 1 : 0;
        istr_prop.rider_baru[ 5 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverTpdFlag() ) ? 1 : 0;
        istr_prop.rider_baru[ 6 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorTpdDeathFlag() ) ? 1 : 0;
        istr_prop.rider_baru[ 7 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverCiFlag() ) ? 1 : 0;
        istr_prop.rider_baru[ 8 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorCiDeathFlag() ) ? 1 : 0;
        istr_prop.rider_baru[ 9 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorCiFlag() ) ? 1 : 0;
        istr_prop.rider_baru[ 10 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorSpouseTpdDeathFlag() ) ? 1 : 0;
        istr_prop.rider_baru[ 11 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFamilyFlag() ) ? cepr01030103Form.getHcpFamilyCd() : 0;
        istr_prop.rider_baru[ 12 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTermRiderFlag() ) ? 1 : 0;
        istr_prop.rider_baru[ 13 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) ? cepr01030103Form.getEkaSehatCd() : 0;
        istr_prop.rider_baru[ 14 ] = 0;// swine
        istr_prop.rider_baru[ 15 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatInnerLimitFlag() ) ? cepr01030103Form.getEkaSehatInnerLimitCd() : 0;
        istr_prop.rider_baru[ 16 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpProviderFlag() ) ? cepr01030103Form.getHcpProviderCd() : 0;
        istr_prop.rider_baru[ 17 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverTpdCiFlag() ) ? cepr01030103Form.getWaiverTpdCiChooseCd() : 0;
        istr_prop.rider_baru[ 18 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorTpdCiDeathFlag() ) ? 1 : 0;
        istr_prop.rider_baru[ 19 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesInsFlag() ) ? cepr01030103Form.getLadiesInsCd() : 0;
        istr_prop.rider_baru[ 20 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpLadiesFlag() ) ? cepr01030103Form.getHcpLadiesCd() : 0;
        istr_prop.rider_baru[ 21 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesMedExpenseFlag() ) ? cepr01030103Form.getLadiesMedExpenseCd() : 0;
        istr_prop.rider_baru[ 22 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesMedExpenseInnerLimitFlag() ) ? cepr01030103Form.getLadiesMedExpenseInnerLimitCd() : 0;
        istr_prop.rider_baru[ 23 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Tpd10CiDeathFlag() ) ? 1 : 0;
        istr_prop.rider_baru[ 24 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiver5Tpd10CiFlag() ) ? 1 : 0;
        istr_prop.rider_baru[ 25 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getScholarshipFlag() ) ? 1 : 0;
        istr_prop.rider_baru[ 26 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getBabyFlag() ) ? 1 : 0;
        istr_prop.rider_baru[ 27 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEarlyStageCi99Flag() ) ? 1 : 0;
        istr_prop.rider_baru[ 28 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag() ) ? Integer.valueOf(cepr01030103Form.getMedicalPlusChooseCd()) : 0;
        istr_prop.rider_baru[ 29 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatExtraFlag() ) ? cepr01030103Form.getEkaSehatExtraCd() : 0;
        /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
        istr_prop.rider_baru[ 30 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTerm5575RiderFlag() ) ? cepr01030103Form.getTerm5575RiderChooseCd() : 0;
        
//        istr_prop.rider_baru[ 14 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatInnerLimitFlag() ) ? cepr01030103Form.getEkaSehatInnerLimitCd() : 0;
//        istr_prop.rider_baru[ 15 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpProviderFlag() ) ? cepr01030103Form.getHcpProviderCd() : 0;
//        istr_prop.rider_baru[ 16 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorTpdCiDeathFlag() ) ? 1 : 0;
//        istr_prop.rider_baru[ 17 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverTpdCiFlag() ) ? cepr01030103Form.getWaiverTpdCiChooseCd() : 0;
//        istr_prop.rider_baru[ 18 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesInsFlag() ) ? cepr01030103Form.getLadiesInsCd() : 0;
//        istr_prop.rider_baru[ 19 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpLadiesFlag() ) ? cepr01030103Form.getHcpLadiesCd() : 0;
//        istr_prop.rider_baru[ 20 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesMedExpenseFlag() ) ? cepr01030103Form.getLadiesMedExpenseCd() : 0;
//        istr_prop.rider_baru[ 21 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesMedExpenseInnerLimitFlag() ) ? cepr01030103Form.getLadiesMedExpenseInnerLimitCd() : 0;
//        istr_prop.rider_baru[ 22 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Tpd10CiDeathFlag() ) ? 1 : 0;
        
        // SPECIAL CASE
        if( cepr01030102Form.getLeftPartOfBusinessCd() != 191 )
        {
	        if(!CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorCiDeathFlag()))
	        {
	        	istr_prop.rider_baru[ 9 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Ci10CiDeathFlag() ) ? 1 : 0;
	        }
	        if(!CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorTpdDeathFlag()))
	        {
	        	istr_prop.rider_baru[ 6 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Tpd10TpdDeathFlag() ) ? 1 : 0;
	        }
	        if(!CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverCiFlag()))
	        {
	        	istr_prop.rider_baru[ 7 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiver5Ci10CiFlag() ) ? 1 : 0;
	        }
	        if(!CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverTpdFlag()))
	        {
	        	istr_prop.rider_baru[ 5 ] = CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiver5Tpd10TpdFlag() ) ? 1 : 0;
	        }
        }
        if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getEkaSehatFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getEkaSehatFlagDisplay() ) ){
        	istr_prop.rider_baru[ 13 ] = CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getEkaSehatFlag() ) ? cepr01030102Form.getEkaSehatCd() : 0;
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getWaiverPremiumDisabilityFlag() ) 
        		&& CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getWaiverPremiumDisabilityFlagDisplay() ) ){
        	istr_prop.rider_baru[ 5 ] = CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getWaiverPremiumDisabilityFlag() ) ? 1: 0;
        }
        
        if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getTermRiderFlag() ) 
        		&& CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getTermRiderFlagDisplay() ) ){
        	istr_prop.rider_baru[ 12 ] = CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getTermRiderFlag() ) ? 1: 0;
        }        
        // istr_prop.bunga is only used in Cepr01040111Business.getIllustrationResult
        Integer li_bulan[] = { CommonConst.DUMMY_ZERO, 1, 6, 12, 36, 3, 24};
        istr_prop.bunga = ArrUtil.toListFromArray( li_bulan ).indexOf( cepr01030102Form.getMtiCd() ); 
        return istr_prop;
    }
}