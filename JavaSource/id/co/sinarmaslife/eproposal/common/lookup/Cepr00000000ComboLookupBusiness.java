package id.co.sinarmaslife.eproposal.common.lookup;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr00000000ComboLookupBusiness
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jun 13, 2007 9:25:40 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.common.lookup.LookupList;
import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.data.HardcodedProducts;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.model.vo.HardcodedProductVO;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.HtmlUtil;
import id.co.sinarmaslife.standard.util.ArrUtil;

import java.util.*;

public class Cepr00000000ComboLookupBusiness
{
    protected final Log logger = LogFactory.getLog( getClass() );

    private static Cepr00000000ComboLookupBusiness instance;
    protected EproposalManager eproposalManager;
    protected Cepr00000000EditorUtil editorUtil;

    public Cepr00000000ComboLookupBusiness( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil )
    {
        this.eproposalManager = eproposalManager;
        this.editorUtil = editorUtil;
    }

    public static Cepr00000000ComboLookupBusiness getInstance( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil )
    {
        if( instance == null )
        {
            instance = new Cepr00000000ComboLookupBusiness( eproposalManager, editorUtil );
        }
        return instance;
    }

    // get all product options
    public List< OptionVO > selectLstBusinessOptionVOList( boolean addEmptyOption, String[] arrayParams, CredentialsVO credentialsVO )
    {
        logger.info( "*-*-*-* Cepr00000000ComboLookupBusiness.selectLstBusinessOptionVOList" );
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        params.put( "cdList", ArrUtil.toListFromArray( arrayParams ) );
        List<OptionVO> result = eproposalManager.selectLstBusinessOptionVOList( params );
        for( int i = 0 ; i < result.size() ; i ++ )// UTK MENGUBAH NAMA PRODUK
        {
        	if( result.get(i).getValue().equals("116==>EXCELLINK 80 PLUS")){
        		result.get(i).setValue("116==>EKA LINK 80 PLUS");
        		result.get(i).setLabel("EKA LINK 80 PLUS");
        	}
        	if( result.get(i).getValue().equals("120==>CERDAS")){
        		result.get(i).setValue("120==>CERDAS");
        		if("bsm".equals(credentialsVO.getType()) || "999976".equals(credentialsVO.getMsagId()) || Hardcode.GROUP_BANK_SINARMAS.equals(credentialsVO.getGroupId())){//BSM
        			result.get(i).setLabel("SIMAS POWER LINK");
        		}else{
        			result.get(i).setLabel("CERDAS/EXCELLENT/SP");
        		}
        	}
        	if( result.get(i).getValue().equals("127==>CERDAS SISWA")){
        		result.get(i).setValue("127==>CERDAS SISWA");
        		result.get(i).setLabel("EXCELLENT EDUCATION");
        	}
        	if( result.get(i).getValue().equals("128==>CERDAS PROTEKSI")){
        		result.get(i).setValue("128==>CERDAS PROTEKSI");
        		result.get(i).setLabel("EXCELLENT PROTECTION");
        	}
        	if( result.get(i).getValue().equals("129==>CERDAS SEJAHTERA")){
        		result.get(i).setValue("129==>CERDAS SEJAHTERA");
        		result.get(i).setLabel("EXCELLENT PENSION");
        	}
        	if( result.get(i).getValue().equals("111==>INVESTA")){
        		result.get(i).setValue("111==>INVESTA");
        		result.get(i).setLabel("SIMAS MAXISAVE");
        	}
        }
        
        // Hardcode of Produk 162, Product Eka Link 88 will be separated from Artha Link 88
        // it is linked with Cepr01030102Business.selectSpecificLstBusinessOptionVOList

        List<HardcodedProductVO> hardcodedProductVOList = HardcodedProducts.getHardcodedProductVOList();
        OptionVO optionVO;
        for( HardcodedProductVO prodVO : hardcodedProductVOList )
        {
            optionVO = new OptionVO( prodVO.getAssurancePlanCd(), prodVO.getProductDescr() );
            result.add( optionVO );
        }
        
        if( addEmptyOption ) result = HtmlUtil.addEmptyOption( result );
        return result;
    }

    public List< OptionVO > selectCurrencyOptionVOList( boolean addEmptyOption, String[] arrayParams )
    {
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        params.put( "cdList", ArrUtil.toListFromArray( arrayParams ) );
        List< OptionVO > result = eproposalManager.selectCurrencyOptionVOList( params );
        if( addEmptyOption ) result = HtmlUtil.addEmptyOption( result );
        return result;
    }

    public List< OptionVO > selectPayModeOptionVOList( boolean addEmptyOption, Integer[] arrayParams )
    {
        Map<String, List<Integer>> params = new HashMap<String, List<Integer>>();
        params.put( "cdList", ArrUtil.toListFromArray( arrayParams ) );
        List< OptionVO > result = eproposalManager.selectPayModeOptionVOList( params );
        if( addEmptyOption ) result = HtmlUtil.addEmptyOption( result );
        return result;
    }
    
    public List< OptionVO > specialOfferList( )
    {
    	List<OptionVO> specialOfferList = new ArrayList<OptionVO>();
    	OptionVO optionVO;
    	optionVO = new OptionVO( "", "None" );
    	specialOfferList.add( optionVO );
//    	optionVO = new OptionVO( "1", "Discount Premium" );
//    	specialOfferList.add( optionVO );
    	optionVO = new OptionVO( "2", "Additional Unit" );
    	specialOfferList.add( optionVO );
//    	optionVO = new OptionVO( "3", "Lucky Draw" );
//        specialOfferList.add( optionVO );
        return specialOfferList;
    }
    
    
    public List< OptionVO > smileLadiesPackageList( )
    {
    	List<OptionVO> smileLadiesPackageList = new ArrayList<OptionVO>();
    	OptionVO optionVO;
    	optionVO = new OptionVO( "0", "None" );
    	smileLadiesPackageList.add( optionVO );
    	optionVO = new OptionVO( "1", "Paket Diamond" );
    	smileLadiesPackageList.add( optionVO );
    	optionVO = new OptionVO( "2", "Paket Ruby" );
    	smileLadiesPackageList.add( optionVO );
    	optionVO = new OptionVO( "3", "Paket Pearl" );
    	smileLadiesPackageList.add( optionVO );
    	optionVO = new OptionVO( "4", "Paket Fantastic" );
    	smileLadiesPackageList.add( optionVO );
    	optionVO = new OptionVO( "5", "Paket Excellent" );
    	smileLadiesPackageList.add( optionVO );
        return smileLadiesPackageList;
    }
    
    public List< OptionVO > ibadahPackageList( )
    {
    	List<OptionVO> smileLadiesPackageList = new ArrayList<OptionVO>();
    	OptionVO optionVO;
    	optionVO = new OptionVO( "0", "None" );
    	smileLadiesPackageList.add( optionVO );
    	optionVO = new OptionVO( "18", "Umroh dan Haji plus" );
    	smileLadiesPackageList.add( optionVO );
    	optionVO = new OptionVO( "19", "Haji  plus" );
    	smileLadiesPackageList.add( optionVO );
    	optionVO = new OptionVO( "20", "Umroh" );
    	smileLadiesPackageList.add( optionVO );
        return smileLadiesPackageList;
    }
    
    public List< OptionVO > providerTypeList( )
    {
    	List<OptionVO> smileLadiesPackageList = new ArrayList<OptionVO>();
    	OptionVO optionVO;
    	optionVO = new OptionVO( "1", "Non Provider" );
    	smileLadiesPackageList.add( optionVO );
    	optionVO = new OptionVO( "2", "Provider" );
    	smileLadiesPackageList.add( optionVO );
        return smileLadiesPackageList;
    }
    
    public List< OptionVO > smileLadiesPackageBancassList( )
    {
    	List<OptionVO> smileLadiesPackageList = new ArrayList<OptionVO>();
    	OptionVO optionVO;
    	optionVO = new OptionVO( "0", "None" );
    	smileLadiesPackageList.add( optionVO );
    	optionVO = new OptionVO( "4", "Paket Fantastic" );
    	smileLadiesPackageList.add( optionVO );
    	optionVO = new OptionVO( "5", "Paket Excellent" );
    	smileLadiesPackageList.add( optionVO );
        return smileLadiesPackageList;
    }
    
    public List< OptionVO > smilePensionPackageList( )
    {
    	List<OptionVO> smilePensionPackageList = new ArrayList<OptionVO>();
    	OptionVO optionVO;
    	optionVO = new OptionVO( "0", "None" );
    	smilePensionPackageList.add( optionVO );
    	optionVO = new OptionVO( "7", "Paket A" );
    	smilePensionPackageList.add( optionVO );
    	optionVO = new OptionVO( "8", "Paket B" );
    	smilePensionPackageList.add( optionVO );
    	optionVO = new OptionVO( "9", "Paket C" );
    	smilePensionPackageList.add( optionVO );
    	optionVO = new OptionVO( "10", "Paket D" );
    	smilePensionPackageList.add( optionVO );
    	optionVO = new OptionVO( "11", "Paket E" );
    	smilePensionPackageList.add( optionVO );
        return smilePensionPackageList;
    }
    
    
    public List< OptionVO > excellentPensionPackageList()
    {
    	List<OptionVO> excellentPensionPackageList = new ArrayList<OptionVO>();
    	OptionVO optionVO;
    	optionVO = new OptionVO( "12", "Paket 1" );
    	excellentPensionPackageList.add( optionVO );
    	optionVO = new OptionVO( "13", "Paket 2" );
    	excellentPensionPackageList.add( optionVO );
    	optionVO = new OptionVO( "14", "Paket 3" );
    	excellentPensionPackageList.add( optionVO );
        return excellentPensionPackageList;
    }

    public List< OptionVO > getClazzOptionVOList( boolean addEmptyOption )
    {
        List< OptionVO > result = LookupList.getClazzOptionVOList();
        if( addEmptyOption ) result = HtmlUtil.addEmptyOption( result );
        return result;
    }

    public List< OptionVO > getGenderOptionVOList( boolean addEmptyOption )
    {
        return HtmlUtil.addEmptyOption( LookupList.getGenderOptionVOList() );
    }

    public List< OptionVO > getMonthOptionVOList( boolean addEmptyOption, Integer[] monthPeriodArr  )
    {
        return LookupList.getMonthOptionVOList( addEmptyOption, monthPeriodArr );
    }

    public List< OptionVO > selectPacketOptionVOList( boolean addEmptyOption )
    {
        return LookupList.getPacketOptionVOList( addEmptyOption );
    }
    
    public List< OptionVO > getJenisOptionVOList( boolean addEmptyOption )
    {
    	List< OptionVO > result = new ArrayList<OptionVO>();
    	result.add(new OptionVO("0", "Bukan Manfaat Bulanan"));
    	result.add(new OptionVO("1", "Manfaat Bulanan"));
        if( addEmptyOption ) result = HtmlUtil.addEmptyOption( result );
        return result;
    }
    
    public List< OptionVO > getJenisOptionVOBSIMList( boolean addEmptyOption )
    {
    	List< OptionVO > result = new ArrayList<OptionVO>();
    	result.add(new OptionVO("0", "NON Karyawan BSIM"));
    	result.add(new OptionVO("1", "Karyawan BSIM"));
    	//result.add(new OptionVO("2", "Nasabah Kredit Mikro BSIM"));
        if( addEmptyOption ) result = HtmlUtil.addEmptyOption( result );
        return result;
    }
    
    public List< OptionVO > getJenisOptionVOBridgeAgencyList( boolean addEmptyOption )
    {
    	List< OptionVO > result = new ArrayList<OptionVO>();
    	result.add(new OptionVO("0", "None"));
    	result.add(new OptionVO("27", "Bronze"));
    	result.add(new OptionVO("28", "Classic"));
    	result.add(new OptionVO("29", "Silver"));
    	result.add(new OptionVO("30", "Gold"));
        if( addEmptyOption ) result = HtmlUtil.addEmptyOption( result );
        return result;
    }
    
    public List< OptionVO > getPacketTabelList(String leftCode, String rightCode)
    {   
    	Map<String,Object> params = new HashMap<String,Object>();
	   params.put( "lsbs_id", leftCode );
	   params.put( "lsdbs_number", rightCode );
        List< OptionVO > result = eproposalManager.getPacketTabelList( params );
       
        return result;
    }
}
