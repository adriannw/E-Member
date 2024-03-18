package id.co.sinarmaslife.eproposal.product.product01040222;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 01040136
 * Program Name   		: Cepr01040136Entry
 * Description         	: Super sejahtera (39)
 * Environment      	: Java  1.5.0_06
 * Author               : Fadly Mathendra
 * Version              : 1.0
 * Creation Date    	: Aug 8, 2007 2:10:04 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.business.Cepr00000000RiderBusiness;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000EntryInterface;
import id.co.sinarmaslife.eproposal.model.pb.Istr_prop;
import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.eproposal.model.pb.S_medicalPlus;
import id.co.sinarmaslife.eproposal.model.vo.ParticipantVO;
import id.co.sinarmaslife.eproposal.common.lookup.LookupList;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000Entry;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030102FormConst;
import id.co.sinarmaslife.standard.util.FormatNumber;
import id.co.sinarmaslife.standard.util.LazyConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cepr01040222Entry extends Cepr01040000Entry implements Cepr01040000EntryInterface
{
    protected final Log logger = LogFactory.getLog( getClass() );
    protected Istr_prop istr_prop;
    
    public Cepr01040222Entry( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command, Errors errors )
    {
        super( eproposalManager, editorUtil, command, errors );
        logger.info( "*-*-*-* Cepr01040136Entry constructor is called ..." );
        setDownloadUrl( "wepr01040222.pdf" );
    }

    public void initDisplayedForm()
    {
        logger.info( "*-*-*-* Cepr01040136Entry.initDisplayedForm" );
        displayStandardForm();
//        displayAdditionalAssurance( true );
        cepr01030102Form.setButtonCountPremiumDisplay( CommonConst.DISPLAY_TRUE );
        if( Cepr01040222Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            displayAdditionalAssurance( true );
        }
        else if( Cepr01040222Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            displayAdditionalAssurance( true );
        }
       
//        cepr01030102Form.setPaymentModeCd( payModeArr[ 0 ] );
        cepr01030102Form.setEkaSehatFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setTpdFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPayorsClauseFlagDisplay( CommonConst.DISPLAY_FALSE );
        if( credentialsVO != null && credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_DMTM ) ){
        	cepr01030102Form.setEmailDisplay( CommonConst.DISPLAY_TRUE );
        	cepr01030102Form.setFaxDisplay( CommonConst.DISPLAY_TRUE ); 
        }else{
        	cepr01030102Form.setEmailDisplay( CommonConst.DISPLAY_FALSE );
        	cepr01030102Form.setFaxDisplay( CommonConst.DISPLAY_FALSE ); 
        }
        
        cepr01030102Form.setPersonalAccidentFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setCriticalIllnessFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setWaiverPremiumDisabilityFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setTermRiderFlagDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setMedicalPlusDisplay(CommonConst.DISPLAY_TRUE);
        
        cepr01030102Form.setHeaderInvestChoiceDisplay( CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setPesertaMedicalPlusStandAloneDisplay( CommonConst.DISPLAY_TRUE );
        cepr01030102Form.setTotalSumInsuredDisplay(CommonConst.DISPLAY_FALSE );
        cepr01030102Form.setBabyDisplay(CommonConst.DISPLAY_FALSE );
    }

    public void initDisabledForm()
    {
        logger.info( "*-*-*-* Cepr01040136Entry.initDisabledForm" );

        disabledAllForm( false );
        cepr01030102Form.setTermOfContractIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setTermOfPaymentIsDisabled( CommonConst.DISABLED_TRUE );
        cepr01030102Form.setPremiumIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setTotalSumInsuredIsDisabled( CommonConst.DISABLED_FALSE );
    }

    public void initReadOnlyForm()
    {
        logger.info( "*-*-*-* Cepr01040136Entry.initReadOnlyForm" );
        readOnlyAllForm( false );
    }

    public void fillDefaultValueEachTimeRightPartOfBusinessCdChanged()
    {
        logger.info( "*-*-*-* Cepr01040136Entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged" );
    }

    public void fillDefaultValueEachTimeFormCalled()
    {
        logger.info( "*-*-*-* Cepr01040136Entry.fillDefaultValueEachTimeFormCalled" );

        cepr01030102Form.setTermOfContract( 75 - cepr01030101Form.getInsuredAge() );

        Integer[] payModeArr = null;
        if( Cepr01040222Mapper.X1 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            cepr01030102Form.setTermOfPayment( 1 );
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_BULANAN, Hardcode.PAY_MODE_CD_SEMESTERAN };
        }
        else if( Cepr01040222Mapper.X2 == cepr01030102Form.getRightPartOfBusinessCd() )
        {
            cepr01030102Form.setTermOfPayment( 1 );
            payModeArr = new Integer[]{ Hardcode.PAY_MODE_CD_TAHUNAN, Hardcode.PAY_MODE_CD_BULANAN, Hardcode.PAY_MODE_CD_SEMESTERAN };
        }    
        

//        cepr01030102Form.setPaymentModeCd( payModeArr[ 0 ] );
        cepr01030102Form.setPaymentModeList( comboLookupBusiness.selectPayModeOptionVOList( false, payModeArr ) );
        cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
        cepr01030102Form.setTermRiderUnitQtyList( LookupList.getSequenceNumberList( 1, 5, false ) );
        cepr01030102Form.setPersonalAccidentUnitQtyList( LookupList.getSequenceNumberList( 1, 3, false ) );
        
        BigDecimal premium = computePremium( cepr01030102Form.getPaymentModeCd() );        
        cepr01030102Form.setPremium( premium );
       
    }

    public void validateCurrentPage()
    {
        logger.info( "*-*-*-* Cepr01040136Entry.validateCurrentPage" );

        if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getTotalSumInsured().compareTo( new BigDecimal( "100000000" ) ) < 0 )
            {
        //        errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED, "error.valueAtLeast", new Object[]{ "Rp.100.000,000.00"}, null );
            }
            
//            if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEKALIGUS )
//            {
//                if( cepr01030102Form.getPremium().compareTo( new BigDecimal( "5000000" ) ) < 0 )
//                {
//                    errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.valueAtLeast", new Object[]{ "Rp.5,000,000.00"}, null );
//                }
//            }
//            else
//            {
//                if( cepr01030102Form.getPremium().compareTo( new BigDecimal( "1500000" ) ) < 0 )
//                {
//                    errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.valueAtLeast", new Object[]{ "Rp.1,500,000.00"}, null );
//                }
//            }
        }
        else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
        {
            if( cepr01030102Form.getTotalSumInsured().compareTo( new BigDecimal( "10000" ) ) < 0 )
            {
        //        errors.rejectValue( Cepr01030102FormConst.TOTAL_SUM_INSURED, "error.valueAtLeast", new Object[]{ "$10,000.00"}, null );
            }
            
//            if( cepr01030102Form.getPaymentModeCd() == Hardcode.PAY_MODE_CD_SEKALIGUS )
//            {
//                if( cepr01030102Form.getPremium().compareTo( new BigDecimal( "500" ) ) < 0 )
//                {
//                    errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.valueAtLeast", new Object[]{ "$500.00"}, null );
//                }
//            }
//            else
//            {
//                if( cepr01030102Form.getPremium().compareTo( new BigDecimal( "150" ) ) < 0 )
//                {
//                    errors.rejectValue( Cepr01030102FormConst.PREMIUM, "error.valueAtLeast", new Object[]{ "$150.00"}, null );
//                }
//            }
        }
    }

    public void validatePreviousPage()
    {
        logger.info( "*-*-*-* Cepr01040136Entry.validatePreviousPage" );

        commonValidator.validateInsuredAge( Cepr01030102FormConst.ASSURANCE_PLAN_CD, 0, 75 );
        
//        if( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getPayorsClauseFlag() ) )
//        {
//            if( cepr01030101Form.getInsuredAge() < 15 )
//            {
//                errors.rejectValue( Cepr01030102FormConst.PAYORS_CLAUSE_FLAG, "error.insuredAgeMin", new Object[]{ "15" }, null );
//            }
//            else if( cepr01030101Form.getPolicyHolderAge() < 20 )
//            {
//                errors.rejectValue( Cepr01030102FormConst.PAYORS_CLAUSE_FLAG, "error.policyHolderAgeMin", new Object[]{ "20" }, null );
//            }
//        }

    }

    public BigDecimal computePremium( Integer payModeCd )
    {
        logger.info( "*-*-*-* Cepr01040136Entry.computePremium" );
        /*adrian
        Map<String, Object> sqlParams = new HashMap<String, Object>();
        sqlParams.put( "leftPartOfBusinessCd", cepr01030102Form.getLeftPartOfBusinessCd() );
        sqlParams.put( "lstabLamaTanggung", new Integer("0") );
        sqlParams.put( "lsdbsNumber", cepr01030102Form.getRightPartOfBusinessCd() );      
        sqlParams.put( "lstabJenis", Hardcode.LSTAB_JENIS_PREMI );
        sqlParams.put( "currencyCd", cepr01030102Form.getCurrencyCd() );
        sqlParams.put( "paymentModeCd", payModeCd );
        sqlParams.put( "termOfPayment", cepr01030102Form.getTermOfPayment() );
        sqlParams.put( "insuredAge", cepr01030101Form.getInsuredAge() );
        sqlParams.put( "yearNoList", new Integer[]{ new Integer( "1" ) } );
        
        List rateList = eproposalManager.selectRateNew( sqlParams );
        BigDecimal premium;
        BigDecimal rate;
        if( rateList != null && rateList.size() > 0 )
        {
            rate = ( BigDecimal ) eproposalManager.selectRateNew( sqlParams ).get( 0 );
            premium = computePremiumWithSimpleMethod( cepr01030102Form.getTotalSumInsured(), rate,  new BigDecimal( "1" ) );
        }
        else
        {
            premium = new BigDecimal( "0" );
        }
        
        BigDecimal premium;
       premium = computePremiumWithSimpleMethod( cepr01030102Form.getTotalSumInsured(), new BigDecimal( "10000000" ),  new BigDecimal( "1" ) );
        */
        Map<String, Object> params = new HashMap<String, Object>();
        int riderBaru = 0;
        double ldec_rate=0, ldec_pct=1 ;
        double ldec_total=0;
        double ldec_temp=0;
        BigDecimal premium;
        
        if(cepr01030102Form.getRightPartOfBusinessCd()==1){
        	riderBaru = 1;        	
        } else if(cepr01030102Form.getRightPartOfBusinessCd()==2){
        	riderBaru = 2;        	
        }        
      
        params.put( "riderBaru", riderBaru );  
        params.put( "kursId", cepr01030102Form.getCurrencyCd() );
        params.put( "liUsia", cepr01030101Form.getInsuredAge() );
        ldec_rate = LazyConverter.toDouble( eproposalManager.selectProvestraRiRj( params ) );
    
		if( LazyConverter.toInt( cepr01030102Form.getPaymentModeCd() ) == 1 ){  //triwulan
			ldec_pct = 0.35;
		}else if( LazyConverter.toInt( cepr01030102Form.getPaymentModeCd() ) == 2 ){ //semesteran
			ldec_pct = 0.65;
		}else if( LazyConverter.toInt( cepr01030102Form.getPaymentModeCd() ) == 6 ){ //bulanan
			ldec_pct = 0.12;
		}
        ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
//		ldec_temp = FormatNumber.round( (ldec_rate) * 0.12, 2 );
		ldec_total += ldec_temp;
		
		
		List<ParticipantVO> participantMedicalPlusVOList = cepr01030113Form.getParticipantVOList();
        participantMedicalPlusVOList = Cepr00000000RiderBusiness.removeEmptyParticipantVOFromTheList( participantMedicalPlusVOList );
        S_medicalPlus medicalPlus = new S_medicalPlus();
        medicalPlus.peserta = participantMedicalPlusVOList.size();
        for( int i = 0; i < participantMedicalPlusVOList.size(); i++ )
        {
            ParticipantVO participantMedicalPlusVO = participantMedicalPlusVOList.get( i );
            medicalPlus.nama[ i + 1 ] = participantMedicalPlusVO.getName();
            medicalPlus.tgl[ i + 1 ] = participantMedicalPlusVO.getBirthDay();
            medicalPlus.usia[ i + 1 ] = participantMedicalPlusVO.getAge() == null ? 0 : participantMedicalPlusVO.getAge();
            medicalPlus.gender[ i + 1 ] = participantMedicalPlusVO.getSexCd();
            medicalPlus.medicalPlusRbFlag[ i + 1 ] = participantMedicalPlusVO.getMedicalPlusRbFlag();
        }
		
		 //  Provestra Peserta Tambahan
		if( medicalPlus.peserta > 0  )
        {
	   		 int riderBaruPeserta=1;
	   		 if(cepr01030102Form.getRightPartOfBusinessCd()==1){
	   			 riderBaruPeserta = 1;        	
	   	     } else if(cepr01030102Form.getRightPartOfBusinessCd()==2){
	   	    	 riderBaruPeserta = 2;        	
	   	     }        
	   
	           for( int i = 1; i <= medicalPlus.peserta; i++ )
	           {
	               int li_usia_peserta  = medicalPlus.usia[ i ];               
	               	riderBaruPeserta = riderBaruPeserta + 2;
	             
	               Map<String, Object> prm = new HashMap<String, Object>();
	               prm.put( "riderBaru", riderBaruPeserta );  
	               prm.put( "kursId", cepr01030102Form.getCurrencyCd() );
	               prm.put( "liUsia", li_usia_peserta );
	               ldec_rate = LazyConverter.toDouble( eproposalManager.selectProvestraRiRj( prm ) );	                        
	               ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
	               ldec_total += ldec_temp;	    
	           }	          
        }
		
		if(CommonConst.CHECKED_TRUE.equals(cepr01030102Form.getMedicalPlusRgFlag())){
			params.put("lsbsId", "841");
            ldec_rate = LazyConverter.toDouble( eproposalManager.selectProvestraRgRbPk( params ) );
            ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
 			 ldec_total += ldec_temp;
 			 
 			 //  Provestra Peserta Tambahan
 	    	 if( medicalPlus.peserta > 0  )
 	         {
 	    		 int riderBaruPeserta=1;
 	    		 if(cepr01030102Form.getRightPartOfBusinessCd()==1){
 	    			 riderBaruPeserta = 1;        	
 	    	     } else if(cepr01030102Form.getRightPartOfBusinessCd()==2){
 	    	    	 riderBaruPeserta = 2;        	
 	    	     }        
 	    
 	            for( int i = 1; i <= medicalPlus.peserta; i++ )
 	            {
 	                int li_usia_peserta  = medicalPlus.usia[ i ];               
 	                	riderBaruPeserta = riderBaruPeserta + 2;
 	              
 	                Map<String, Object> prm = new HashMap<String, Object>();
 	                prm.put("lsbsId", "841");
 	                prm.put( "riderBaru", riderBaruPeserta );  
 	                prm.put( "kursId", cepr01030102Form.getCurrencyCd() );
 	                prm.put( "liUsia", li_usia_peserta );
 	                ldec_rate = LazyConverter.toDouble( eproposalManager.selectProvestraRgRbPk( prm ) );	                        
 	                ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 ); 	              
 	                ldec_total += ldec_temp;	    
 	            } 	            
 	         } 
		}
		
		if(CommonConst.CHECKED_TRUE.equals(cepr01030102Form.getMedicalPlusRbFlag())){           				
			 params.put("lsbsId", "842");        	
			 ldec_rate = LazyConverter.toDouble( eproposalManager.selectProvestraRgRbPk( params ) );           
            ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
			 ldec_total += ldec_temp;
			 
			//  Provestra Peserta Tambahan
 	    	 if( medicalPlus.peserta > 0  )
 	         {
 	    		 int riderBaruPeserta=1;
 	    		 if(cepr01030102Form.getRightPartOfBusinessCd()==1){
 	    			 riderBaruPeserta = 1;        	
 	    	     } else if(cepr01030102Form.getRightPartOfBusinessCd()==2){
 	    	    	 riderBaruPeserta = 2;        	
 	    	     }        
 	    
 	            for( int i = 1; i <= medicalPlus.peserta; i++ )
 	            {
 	                int li_usia_peserta  = medicalPlus.usia[ i ];               
 	                	riderBaruPeserta = riderBaruPeserta + 2;
 	              
 	                Map<String, Object> prm = new HashMap<String, Object>();
 	                prm.put("lsbsId", "842");
 	                prm.put( "riderBaru", riderBaruPeserta );  
 	                prm.put( "kursId", cepr01030102Form.getCurrencyCd() );
 	                prm.put( "liUsia", li_usia_peserta );
 	                ldec_rate = LazyConverter.toDouble( eproposalManager.selectProvestraRgRbPk( prm ) );	                        
 	                ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 );
 	                ldec_total += ldec_temp;	    
 	            } 	            
 	         }
		}
		
		if(CommonConst.CHECKED_TRUE.equals(cepr01030102Form.getMedicalPlusPkFlag())){           				
			 params.put("lsbsId", "843");        	
			 ldec_rate = LazyConverter.toDouble( eproposalManager.selectProvestraRgRbPk( params ) );
			 ldec_temp = FormatNumber.round( (ldec_rate * ldec_pct) * 0.1, 2 );
			 ldec_total += ldec_temp;
			 
			//  Provestra Peserta Tambahan
 	    	 if( medicalPlus.peserta > 0  )
 	         {
 	    		 int riderBaruPeserta=1;
 	    		 if(cepr01030102Form.getRightPartOfBusinessCd()==1){
 	    			 riderBaruPeserta = 1;        	
 	    	     } else if(cepr01030102Form.getRightPartOfBusinessCd()==2){
 	    	    	 riderBaruPeserta = 2;        	
 	    	     }        
 	    
 	            for( int i = 1; i <= medicalPlus.peserta; i++ )
 	            {
 	                int li_usia_peserta  = medicalPlus.usia[ i ];               
 	                	riderBaruPeserta = riderBaruPeserta + 2;
 	              
 	                Map<String, Object> prm = new HashMap<String, Object>();
 	                prm.put("lsbsId", "843");
 	                prm.put( "riderBaru", riderBaruPeserta );  
 	                prm.put( "kursId", cepr01030102Form.getCurrencyCd() );
 	                prm.put( "liUsia", li_usia_peserta );
 	                ldec_rate = LazyConverter.toDouble( eproposalManager.selectProvestraRgRbPk( prm ) );	                        
 	                ldec_temp = FormatNumber.round( (ldec_rate * 0.117) , 2 ); 	               
 	                ldec_total += ldec_temp;	    
 	            } 	            
 	         }			 
		}
		
		
            premium = new BigDecimal( ldec_total );        
        return premium;
    }

    public BigDecimal computePremium( Object obj )
    {
        logger.info( "*-*-*-* Cepr01040136Entry.computePremium" );

        return computePremium( cepr01030102Form.getPaymentModeCd() );
    }

    public void processFormAfterSubmitBeforeValidation()
    {    	
     
        BigDecimal premium = computePremium( cepr01030102Form.getPaymentModeCd() );       
        cepr01030102Form.setPremium( premium );
	        	
    }

}
