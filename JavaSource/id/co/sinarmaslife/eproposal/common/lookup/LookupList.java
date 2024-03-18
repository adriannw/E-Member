package id.co.sinarmaslife.eproposal.common.lookup;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: LookupList
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 18, 2007 8:45:41 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.eproposal.common.lookup.source.*;
import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.eproposal.model.vo.ParticipantVO;

import java.util.List;

public class LookupList
{
    protected final Log logger = LogFactory.getLog( getClass() );

    public static List< OptionVO > getEmptyOptionVOList(){ return ClazzLookup.getClazzList(); }
    public static List< OptionVO > getClazzOptionVOList(){ return ClazzLookup.getClazzList(); }
    public static List< OptionVO > getGenderOptionVOList(){ return GenderLookup.getGenderList(); }
    public static List<OptionVO> getPercentSequenceList( int[] percentArr  ){ return NumberLookup.getPercentTwoDigitsSequenceList( percentArr ); }
    public static List< OptionVO > getSequenceNumberList( int a, int b, int step, boolean addEmptyOption  ){ return NumberLookup.getSequenceNumberList( a, b, step, addEmptyOption ); }
    public static List< OptionVO > getSequenceNumberList( int a, int b, boolean addEmptyOption ){ return NumberLookup.getSequenceNumberList( a, b, addEmptyOption ); }
    public static List< OptionVO > getPremiumCombination10FoldedList(){ return PremiumCombinationLookup.getPremiumCombination10FoldedList(); }
    public static List< OptionVO > getPremiumCombination5FoldedList(){ return PremiumCombinationLookup.getPremiumCombination5FoldedList(); }
    public static List< OptionVO > getPremiumCombinationFoldedList( int increment ){ return PremiumCombinationLookup.getPremiumCombinationList( increment ); }
    public static List< OptionVO > getPremiumCombinationFoldedStart80List( int increment ){ return PremiumCombinationLookup.getPremiumCombinationStart80List( increment ); }
    public static List< OptionVO > getPremiumCombinationFoldedStart70List( int increment ){ return PremiumCombinationLookup.getPremiumCombinationStart70List( increment ); }
    public static List< OptionVO > getPaRiskList(){ return PaRiskLookup.getPaRiskList(); }
    public static List< OptionVO > getHcpIdrList(){ return HcpLookup.getHcpIdrList(); }
    public static List< OptionVO > getHcpUsdList(){ return HcpLookup.getHcpUsdList(); }
    public static List< OptionVO > getHcpFamilyIdrList(){ return HcpLookup.getHcpIdrList(); }    
    public static List< OptionVO > getHcpFamilyUsdList(){ return HcpLookup.getHcpUsdList(); }
    public static List< OptionVO > getHcpLadiesIdrList(){ return HcpLadiesLookup.getHcpIdrList(); }    
    public static List< OptionVO > getHcpLadiesUsdList(){ return HcpLadiesLookup.getHcpUsdList(); }
    public static List< OptionVO > getHcpProviderIdrList(){ return HcpProLookup.getHcpIdrList(); } 
    public static List< OptionVO > getCiChooseList(){ return CiLookup.getCiChooseList(); }
    public static List< OptionVO > getCiChooseExtList(){ return CiLookup.getCiChooseExtList(); }
    public static List< OptionVO > getEsci99ChooseList(){ return CiLookup.getCiChooseList(); } 
    public static List< OptionVO > getMedicalPlusChooseList(){ return MedicalPlusLookup.getMedicalPlusChooseList(); } 
    public static List< OptionVO > getLadiesInsChooseList(){ return LadiesInsChooseLookup.getladiesInsChooseList(); }
    public static List< OptionVO > getLadiesInsList(){ return LadiesInsLookup.getladiesInsLookupList(); }
    public static List< OptionVO > getScholarshipChooseList(){ return ScholarshipChooseLookup.getScholarshipChooseList(); }
    public static List< OptionVO > getScholarshipList(){ return ScholarshipLookup.getscholarshipLookupList(); }
    public static List< OptionVO > getWaiverTpdChooseList(){ return WaiverTpdCiLookup.getWaiverTpdChooseList(); }  
    public static List< OptionVO > getHcpProviderUsdList(){ return HcpProLookup.getHcpUsdList(); }
    public static List< OptionVO > getEkaSehatIdrList(){ return EkaSehatLookup.getEkaSehatIdrList(); }
    public static List< OptionVO > getEkaSehatInnerLimitIdrList(){ return EkaSehatInnerLimitLookup.getEkaSehatInnerLimitIdrList(); }
    public static List< OptionVO > getLadiesMedExpenseIdrList(){ return LadiesMedExpenseLookup.getladiesMedExpenseIdrList(); }
    public static List< OptionVO > getLadiesMedExpenseUsdList(){ return LadiesMedExpenseLookup.getladiesMedExpenseUsdList(); }
    public static List< OptionVO > getLadiesMedExpenseInnerLimitIdrList(){ return LadiesMedExpenseInnerLimitLookup.getladiesMedExpenseInnerLimitIdrList(); }
    public static List< OptionVO > getLadiesMedExpenseInnerLimitUsdList(){ return LadiesMedExpenseInnerLimitLookup.getladiesMedExpenseInnerLimitUsdList(); }
//    public static List< OptionVO > getEkaSehatProviderList(){ return EkaSehatLookup.getEkaSehatProviderList(); }
    public static List< OptionVO > getEkaSehatUsdList(){ return EkaSehatLookup.getekaSehatUsdList(); }
    public static List< OptionVO > getEkaSehatInnerLimitUsdList(){ return EkaSehatInnerLimitLookup.getekaSehatInnerLimitUsdList(); }
    public static List< OptionVO > getEkaSehatExtraList(){ return EkaSehatLookup.getEkaSehatExtraList(); }    
    public static List<TopupDrawVO> getTopupDrawVOList(){ return TopupDrawVOLookup.getTopupDrawVOList(); }
    public static List<ParticipantVO> getParticipantVOList(){ return ParticipantVOLookup.getParticipantVOList(); }
    public static List<ParticipantVO> getParticipantHcpProVOList(){ return ParticipantHcpProVOLookup.getParticipantVOList(); }
    public static List<ParticipantVO> getParticipantHcpLadiesVOList(){ return ParticipantHcpLadiesVOLookup.getParticipantVOList(); }
    public static List<ParticipantVO> getParticipantEkaSehatVOList(){ return ParticipantEkaSehatVOLookup.getParticipantEkaSehatVOList(); }
    public static List<ParticipantVO> getParticipantEkaSehatInnerLimitVOList(){ return ParticipantEkaSehatInnerLimitVOLookup.getParticipantEkaSehatInnerLimitVOList(); }
    public static List<ParticipantVO> getParticipantLadiesMedExpenseVOList(){ return ParticipantLadiesMedExpenseVOLookup.getParticipantVOList(); }
    public static List<ParticipantVO> getParticipantLadiesMedExpenseInnerLimitVOList(){ return ParticipantLadiesMedExpenseInnerLimitVOLookup.getParticipantVOList(); }
    public static List<ParticipantVO> getParticipantMedicalPlusVOList(){ return ParticipantMedicalPlusVOLookup.getParticipantMedicalPlusVOList(); }
    public static List<ParticipantVO> getParticipantEkaSehatExtraVOList(){ return ParticipantEkaSehatExtraVOLookup.getParticipantEkaSehatExtraVOList(); }
    public static List<OptionVO> getMonthOptionVOList( boolean addEmptyOption, Integer[] monthPeriodArr ){ return MonthsLookup.getMonthList( addEmptyOption, monthPeriodArr ); }
    public static List<OptionVO> getPacketOptionVOList( boolean addEmptyOption ){ return PacketLookup.getPacketList( addEmptyOption ); }
    public static List< OptionVO > getBabyChooseList(){ return BabyLookup.getBabyChooseList(); }  
    public static List< OptionVO > getBabyList(){ return BabyLookup.getBabyList(); } 
    public static List< OptionVO > getJenisInvestFixList(){ return FundFixDynAgrInvestLookup.getJenisInvestFixList(); }
    public static List< OptionVO > getJenisInvestDynamicList(){ return FundFixDynAgrInvestLookup.getJenisInvestDynamicList(); }
    public static List< OptionVO > getJenisInvestAggresiveList(){ return FundFixDynAgrInvestLookup.getJenisInvestAggresiveList(); }    
    public static List< OptionVO > getJenisInvestStabileList(){ return FundStbBlnEquInvestLookup.getJnsInvestStabileList(); }
    public static List< OptionVO > getJenisInvestBalanceList(){ return FundStbBlnEquInvestLookup.getJnsInvestBalanceList(); }
    public static List< OptionVO > getJenisInvestEquityList(){ return FundStbBlnEquInvestLookup.getJnsInvestEquityList(); }
    
    public static List< OptionVO > getJenisInvestFixExcellinkList(){ return FundExcellinkFixDynAgrInvestLookup.getJenisInvestFixExcellinkList(); }
    public static List< OptionVO > getJenisInvestDynamicExcellinkList(){ return FundExcellinkFixDynAgrInvestLookup.getJenisInvestDynamicExcellinkList(); }
    public static List< OptionVO > getJenisInvestAggresiveExcellinkList(){ return FundExcellinkFixDynAgrInvestLookup.getJenisInvestAggresiveExcellinkList(); }    
    public static List< OptionVO > getJenisInvestFixSimasList(){ return FundSimasFixDynAgrInvestLookup.getJenisInvestFixSimasList(); }
    public static List< OptionVO > getJenisInvestDynamicSimasList(){ return FundSimasFixDynAgrInvestLookup.getJenisInvestDynamicSimasList(); }
    public static List< OptionVO > getJenisInvestAggresiveSimasList(){ return FundSimasFixDynAgrInvestLookup.getJenisInvestAggresiveSimasList(); }
    public static List< OptionVO > getJenisInvestCashFundInvestList(){ return FundCashInvestLookup.getJenisInvestCashFundInvestList(); }
    public static List< OptionVO > getJenisInvestExcellinkEquityInvestList(){ return FundCashInvestLookup.getJenisInvestExcellinkEquityInvestList(); }
    
    public static List< OptionVO > getJenisInvestSecureUsdList(){ return FundUsdStbBlnInvestLookup.getJenisInvestSecureUsdList(); }
    public static List< OptionVO > getJenisInvestDynamicUsdList(){ return FundUsdStbBlnInvestLookup.getJenisInvestDynamicUsdList(); }
    /**USD Fund BSIM Products**/
    public static List< OptionVO > getJenisGlobalEquityUsdList(){ return FundUsdStbBlnInvestLookup.getJenisGlobalEquityUsdList(); }
    public static List< OptionVO > getJenisGlobalAggresiveUsdList(){ return FundUsdStbBlnInvestLookup.getJenisGlobalAggresiveUsdList(); }
    
    public static List< OptionVO > getSecureUsdList(){ return FundUsdStbBlnInvestLookup.getSecureUsdList(); }
    public static List< OptionVO > getDynamicUsdList(){ return FundUsdStbBlnInvestLookup.getDynamicUsdList(); }
    
    public static List< OptionVO > getJenisInvestSimasUsdFix(){ return FundUsdSimasSecDynInvestLookup.getJenisInvestFixSimasList(); }
    public static List< OptionVO > getJenisInvestSimasUsdDynamic(){ return FundUsdSimasSecDynInvestLookup.getJenisInvestDynamicSimasList(); }
    public static List< OptionVO > getJenisInvestSimasUsdAggresiv(){ return FundUsdSimasSecDynInvestLookup.getJenisInvestAggresiveSimasList(); }
    public static List< OptionVO > getJenisInvestSimasUsdSecure(){ return FundUsdSimasSecDynInvestLookup.getSecureUsdList(); } 
    public static List< OptionVO > getJenisInvestSimasUsdDynamicDollar(){ return FundUsdSimasSecDynInvestLookup.getDynamicUsdList(); } 
    
    /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
    public static List< OptionVO > getTerm5575RiderChooseList(){ return Term5575RiderChooseLookup.getTerm5575RiderChooseList(); }
}