package id.co.sinarmaslife.eproposal.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr01030102Business
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Jun 12, 2007 4:07:16 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 * 1.0                                      Samuel              Main Business Class for Proposal Detail Form
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.data.HardcodedProducts;
import id.co.sinarmaslife.eproposal.common.parent.Cepr01040000DefaultEntry;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000StringUtil;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000EntryInterface;
import id.co.sinarmaslife.eproposal.model.interfaces.Cepr01040000MapperInterface;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.model.vo.HardcodedProductVO;
import id.co.sinarmaslife.eproposal.product.product01040101.Cepr01040101Mapper;
import id.co.sinarmaslife.eproposal.product.product01040102.Cepr01040102Mapper;
import id.co.sinarmaslife.eproposal.product.product01040103.Cepr01040103Mapper;
import id.co.sinarmaslife.eproposal.product.product01040104.Cepr01040104Mapper;
import id.co.sinarmaslife.eproposal.product.product01040105.Cepr01040105Mapper;
import id.co.sinarmaslife.eproposal.product.product01040106.Cepr01040106Mapper;
import id.co.sinarmaslife.eproposal.product.product01040107.Cepr01040107Mapper;
import id.co.sinarmaslife.eproposal.product.product01040108.Cepr01040108Mapper;
import id.co.sinarmaslife.eproposal.product.product01040109.Cepr01040109Mapper;
import id.co.sinarmaslife.eproposal.product.product01040110.Cepr01040110Mapper;
import id.co.sinarmaslife.eproposal.product.product01040111.Cepr01040111Mapper;
import id.co.sinarmaslife.eproposal.product.product01040112.Cepr01040112Mapper;
import id.co.sinarmaslife.eproposal.product.product01040113.Cepr01040113Mapper;
import id.co.sinarmaslife.eproposal.product.product01040114.Cepr01040114Mapper;
import id.co.sinarmaslife.eproposal.product.product01040115.Cepr01040115Mapper;
import id.co.sinarmaslife.eproposal.product.product01040116.Cepr01040116Mapper;
import id.co.sinarmaslife.eproposal.product.product01040117.Cepr01040117Mapper;
import id.co.sinarmaslife.eproposal.product.product01040118.Cepr01040118Mapper;
import id.co.sinarmaslife.eproposal.product.product01040119.Cepr01040119Mapper;
import id.co.sinarmaslife.eproposal.product.product01040120.Cepr01040120Mapper;
import id.co.sinarmaslife.eproposal.product.product01040121.Cepr01040121Mapper;
import id.co.sinarmaslife.eproposal.product.product01040122.Cepr01040122Mapper;
import id.co.sinarmaslife.eproposal.product.product01040123.Cepr01040123Mapper;
import id.co.sinarmaslife.eproposal.product.product01040124.Cepr01040124Mapper;
import id.co.sinarmaslife.eproposal.product.product01040125.Cepr01040125Mapper;
import id.co.sinarmaslife.eproposal.product.product01040126.Cepr01040126Mapper;
import id.co.sinarmaslife.eproposal.product.product01040127.Cepr01040127Mapper;
import id.co.sinarmaslife.eproposal.product.product01040128.Cepr01040128Mapper;
import id.co.sinarmaslife.eproposal.product.product01040129.Cepr01040129Mapper;
import id.co.sinarmaslife.eproposal.product.product01040130.Cepr01040130Mapper;
import id.co.sinarmaslife.eproposal.product.product01040131.Cepr01040131Mapper;
import id.co.sinarmaslife.eproposal.product.product01040132.Cepr01040132Mapper;
import id.co.sinarmaslife.eproposal.product.product01040133.Cepr01040133Mapper;
import id.co.sinarmaslife.eproposal.product.product01040134.Cepr01040134Mapper;
import id.co.sinarmaslife.eproposal.product.product01040135.Cepr01040135Mapper;
import id.co.sinarmaslife.eproposal.product.product01040136.Cepr01040136Mapper;
import id.co.sinarmaslife.eproposal.product.product01040137.Cepr01040137Mapper;
import id.co.sinarmaslife.eproposal.product.product01040139.Cepr01040139Mapper;
import id.co.sinarmaslife.eproposal.product.product01040140.Cepr01040140Mapper;
import id.co.sinarmaslife.eproposal.product.product01040141.Cepr01040141Mapper;
import id.co.sinarmaslife.eproposal.product.product01040142.Cepr01040142Mapper;
import id.co.sinarmaslife.eproposal.product.product01040143.Cepr01040143Mapper;
import id.co.sinarmaslife.eproposal.product.product01040144.Cepr01040144Mapper;
import id.co.sinarmaslife.eproposal.product.product01040145.Cepr01040145Mapper;
import id.co.sinarmaslife.eproposal.product.product01040146.Cepr01040146Mapper;
import id.co.sinarmaslife.eproposal.product.product01040147.Cepr01040147Mapper;
import id.co.sinarmaslife.eproposal.product.product01040148.Cepr01040148Mapper;
import id.co.sinarmaslife.eproposal.product.product01040149.Cepr01040149Mapper;
import id.co.sinarmaslife.eproposal.product.product01040150.Cepr01040150Mapper;
import id.co.sinarmaslife.eproposal.product.product01040151.Cepr01040151Mapper;
import id.co.sinarmaslife.eproposal.product.product01040152.Cepr01040152Mapper;
import id.co.sinarmaslife.eproposal.product.product01040153.Cepr01040153Mapper; /**NCR/2021/02/052	SMiLe Income Protection X-Tra**/
import id.co.sinarmaslife.eproposal.product.product01040201.Cepr01040201Mapper;
import id.co.sinarmaslife.eproposal.product.product01040202.Cepr01040202Mapper;
import id.co.sinarmaslife.eproposal.product.product01040203.Cepr01040203Mapper;
import id.co.sinarmaslife.eproposal.product.product01040204.Cepr01040204Mapper;
import id.co.sinarmaslife.eproposal.product.product01040205.Cepr01040205Mapper;
import id.co.sinarmaslife.eproposal.product.product01040206.Cepr01040206Mapper;
import id.co.sinarmaslife.eproposal.product.product01040207.Cepr01040207Mapper;
import id.co.sinarmaslife.eproposal.product.product01040208.Cepr01040208Mapper;
import id.co.sinarmaslife.eproposal.product.product01040209.Cepr01040209Mapper;
import id.co.sinarmaslife.eproposal.product.product01040210.Cepr01040210Mapper;
import id.co.sinarmaslife.eproposal.product.product01040211.Cepr01040211Mapper;
import id.co.sinarmaslife.eproposal.product.product01040212.Cepr01040212Mapper;
import id.co.sinarmaslife.eproposal.product.product01040213.Cepr01040213Mapper;
import id.co.sinarmaslife.eproposal.product.product01040214.Cepr01040214Mapper;
import id.co.sinarmaslife.eproposal.product.product01040215.Cepr01040215Mapper;
import id.co.sinarmaslife.eproposal.product.product01040216.Cepr01040216Mapper;
import id.co.sinarmaslife.eproposal.product.product01040218.Cepr01040218Mapper;
import id.co.sinarmaslife.eproposal.product.product01040219.Cepr01040219Mapper;
import id.co.sinarmaslife.eproposal.product.product01040220.Cepr01040220Mapper;
import id.co.sinarmaslife.eproposal.product.product01040221.Cepr01040221Mapper;
import id.co.sinarmaslife.eproposal.product.product01040222.Cepr01040222Mapper;
import id.co.sinarmaslife.eproposal.product.product01040223.Cepr01040223Mapper;
import id.co.sinarmaslife.eproposal.product.product01040224.Cepr01040224Mapper;
import id.co.sinarmaslife.eproposal.product.product01040225.Cepr01040225Mapper;
import id.co.sinarmaslife.eproposal.product.product01040227.Cepr01040227Mapper;
import id.co.sinarmaslife.eproposal.product.product01040228.Cepr01040228Mapper;
import id.co.sinarmaslife.eproposal.product.product01040229.Cepr01040229Mapper;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030103Form;
import id.co.sinarmaslife.standard.model.vo.AssurancePlanList;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.HtmlUtil;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.StringUtil;

public class Cepr01030102Business
{
    protected final Log logger = LogFactory.getLog( getClass() );
    protected Cepr01040000EntryInterface entry;
    protected EproposalManager eproposalManager;
    protected Cepr00000000EditorUtil editorUtil;
    private static List<HardcodedProductVO> hardcodedProductVOList;
    private static List<String> leftPartOfHardcodedAssurancePlanCdList;
      
    public enum JobDescr
    {
        DO_INIT_FORM, DO_VALIDATION, DO_DOWNLOAD, DO_PROCESS_FORM_AFTER_SUBMISSION_BEFORE_VALIDATION
    }

    // Initialize static, especially hardcoded product
    // linked with Cepr00000000ComboLookupBusiness.selectLstBusinessOptionVOList
    {
        hardcodedProductVOList = HardcodedProducts.getHardcodedProductVOList();

        leftPartOfHardcodedAssurancePlanCdList = new ArrayList<String>();
        for( HardcodedProductVO prodVO : hardcodedProductVOList )
        {
            leftPartOfHardcodedAssurancePlanCdList.add( LazyConverter.toString( prodVO.getLeftPartOfBusinessCd() ) );
        }
    }

    public Cepr01030102Business( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil )
    {
        this.eproposalManager = eproposalManager;
        this.editorUtil = editorUtil;
    }

    public void resetForm( Cepr01030102Form cepr01030102Form )
    {
        cepr01030102Form.setPremiumFurloughYear( 10 );
        cepr01030102Form.setSmileLadiesPackageCd( 0 );
        cepr01030102Form.setSecondPackageCd( 0 );        
        cepr01030102Form.setEducationPackageCd( 0 );
        cepr01030102Form.setProtectorPackageCd(0);
        cepr01030102Form.setSmilePensionPackageCd( 0 );
        cepr01030102Form.setFixIdr( BigDecimal.ZERO );
        cepr01030102Form.setDynamicIdr( BigDecimal.ZERO );
        cepr01030102Form.setAggresiveIdr( BigDecimal.ZERO );
        cepr01030102Form.setFixIdrIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setDynamicIdrIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setAggresiveIdrIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setCashFundIdr( BigDecimal.ZERO );
        cepr01030102Form.setCashFundIdrIsDisabled( CommonConst.DISABLED_FALSE );
        cepr01030102Form.setExcellinkEquityIdr( BigDecimal.ZERO );
        cepr01030102Form.setExcellinkEquityIsDisabled( CommonConst.DISABLED_FALSE );
                
        cepr01030102Form.setLjiFixCd("");
        cepr01030102Form.setLjiFixListIsDisabled(CommonConst.DISABLED_FALSE);
        cepr01030102Form.setLjiDynamicCd("");
        cepr01030102Form.setLjiDynamicListIsDisabled(CommonConst.DISABLED_FALSE);
        cepr01030102Form.setLjiAggresiveCd("");
        cepr01030102Form.setLjiAggresiveListIsDisabled(CommonConst.DISABLED_FALSE);        
        cepr01030102Form.setLjiCashFundCd("");
        cepr01030102Form.setLjiCashFundListIsDisabled(CommonConst.DISABLED_FALSE);
        cepr01030102Form.setLjiExcellinkEquityCd("");
        cepr01030102Form.setLjiExcellinkEquityIsDisabled(CommonConst.DISABLED_FALSE);
                
        cepr01030102Form.setLjiSecureUsdCd("");
        cepr01030102Form.setLjiSecureUsdListIsDisabled(CommonConst.DISABLED_FALSE);
        cepr01030102Form.setLjiDynamicUsdCd("");
        cepr01030102Form.setLjiDynamicUsdListIsDisabled(CommonConst.DISABLED_FALSE);
        cepr01030102Form.setLjiAggresiveUsdCd("");/**USD Fund BSIM Products**/
        cepr01030102Form.setLjiAggresiveUsdListIsDisabled(CommonConst.DISABLED_FALSE);/**USD Fund BSIM Products**/
                
        cepr01030102Form.setFixSimasIdr( BigDecimal.ZERO );
	    cepr01030102Form.setDynamicSimasIdr( BigDecimal.ZERO );
	    cepr01030102Form.setAggresiveSimasIdr( BigDecimal.ZERO );
    }
    
    

    private List<AssurancePlanList> addHardCodeList( List<AssurancePlanList> existingList, Cepr01030101Form cepr01030101Form )
    {
        String leftPartOfBusinessCd;

        // IMPORTANT NOTE
        // this will remove every instance optionVO that is a hardcoded product from existingList
        AssurancePlanList optionVO;
        for( int i = 0; i < existingList.size(); i++ )
        {
            optionVO = existingList.get( i );
            leftPartOfBusinessCd = Cepr00000000StringUtil.getLeftPartOfBusinessCd( optionVO.getValue() );
            if( leftPartOfHardcodedAssurancePlanCdList.contains( leftPartOfBusinessCd ) )
            {
                existingList.remove( i );
                i--;
            }
        }

        List<String> selectedAssurancePlanCdList = ArrUtil.toListFromArray( new String[]{
            cepr01030101Form.getAssurancePlanCd1(),
            cepr01030101Form.getAssurancePlanCd2(),
            cepr01030101Form.getAssurancePlanCd3(),
            cepr01030101Form.getAssurancePlanCd4(),
        } );

        // Hardcode of Produk 162, Product Eka Link 88 will be separated from Artha Link 88
        // it is linked with Cepr00000000ComboLookupBusiness.selectLstBusinessOptionVOList

        for( HardcodedProductVO productVO : hardcodedProductVOList )
        {
            if( selectedAssurancePlanCdList.contains( productVO.getAssurancePlanCd() ) )
            {
                for( AssurancePlanList subProductOptionVO : productVO.getBusinessOptionVOArr() )
                {
                    existingList.add( subProductOptionVO );
                }
            }
        }

        return existingList;
    }
    
    
    private List<AssurancePlanList> addHardCodeProductList( List<AssurancePlanList> existingList, Cepr01030101Form cepr01030101Form )
    {
        String leftPartOfBusinessCd;

        // IMPORTANT NOTE
        // this will remove every instance optionVO that is a hardcoded product from existingList
        AssurancePlanList optionVO;
        for( int i = 0; i < existingList.size(); i++ )
        {
            optionVO = existingList.get( i );
            leftPartOfBusinessCd = Cepr00000000StringUtil.getLeftPartOfBusinessCd( optionVO.getValue() );
            if( leftPartOfHardcodedAssurancePlanCdList.contains( leftPartOfBusinessCd ) )
            {
                existingList.remove( i );
                i--;
            }
        }

        List<String> selectedAssurancePlanCdList = ArrUtil.toListFromArray( new String[]{
            cepr01030101Form.getAssurancePlanCd1(),
            cepr01030101Form.getAssurancePlanCd2(),
            cepr01030101Form.getAssurancePlanCd3(),
            cepr01030101Form.getAssurancePlanCd4(),
        } );

        // Hardcode of Produk 162, Product Eka Link 88 will be separated from Artha Link 88
        // it is linked with Cepr00000000ComboLookupBusiness.selectLstBusinessOptionVOList

        for( HardcodedProductVO productVO : hardcodedProductVOList )
        {
            if( selectedAssurancePlanCdList.contains( productVO.getAssurancePlanCd() ) )
            {
                for( AssurancePlanList subProductOptionVO : productVO.getBusinessOptionVOArr() )
                {
                    existingList.add( subProductOptionVO );
                }
            }
        }

        return existingList;
    }
    
    
    
    private List<AssurancePlanList> addHardCodePackageList( List<AssurancePlanList> existingList, Cepr01030101Form cepr01030101Form )
    {
        String leftPartOfBusinessCd;

        // IMPORTANT NOTE
        // this will remove every instance optionVO that is a hardcoded product from existingList
        AssurancePlanList optionVO;
        for( int i = 0; i < existingList.size(); i++ )
        {
            optionVO = existingList.get( i );
            leftPartOfBusinessCd = Cepr00000000StringUtil.getLeftPartOfBusinessCd( optionVO.getValue() );
            if( leftPartOfHardcodedAssurancePlanCdList.contains( leftPartOfBusinessCd ) )
            {
                existingList.remove( i );
                i--;
            }
        }

        List<String> selectedAssurancePlanCdList = ArrUtil.toListFromArray( new String[]{
            cepr01030101Form.getAssurancePlanPackageCd1(),
        } );

        // Hardcode of Produk 162, Product Eka Link 88 will be separated from Artha Link 88
        // it is linked with Cepr00000000ComboLookupBusiness.selectLstBusinessOptionVOList

        for( HardcodedProductVO productVO : hardcodedProductVOList )
        {
            if( selectedAssurancePlanCdList.contains( productVO.getAssurancePlanCd() ) )
            {
                for( AssurancePlanList subProductOptionVO : productVO.getBusinessOptionVOArr() )
                {
                    existingList.add( subProductOptionVO );
                }
            }
        }

        return existingList;
    }

    private Cepr01040000EntryInterface getBusinessCdAndEntryInstance( Object command, Errors errors, String theEvent )
    {
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        cepr01030102Form.setTheEvent( theEvent );
        Cepr01040000EntryInterface entry;
        String businessCd = cepr01030102Form.getAssurancePlanCd();
        logger.info( "*-*-*-* businessCd = " + businessCd );

        if( !StringUtil.isEmpty( businessCd ) )
        {
            // let the mapper show what instance to be used
            int leftPartOfBusinessCd = LazyConverter.toInt( Cepr00000000StringUtil.getLeftPartOfBusinessCd( businessCd ) );
            int rightPartOfBusinessCd = LazyConverter.toInt( Cepr00000000StringUtil.getRightPartOfBusinessCd( businessCd ) );
            Cepr01040000MapperInterface mapper = getProductInstance( leftPartOfBusinessCd, command, errors );
            entry = mapper.getSubProductMap().get( rightPartOfBusinessCd );

            // determine the PB version of business number
            Integer bisnisNoPbVersion = mapper.getBisnisNoPbVersionMap().get( rightPartOfBusinessCd );
            if( bisnisNoPbVersion == null ) bisnisNoPbVersion = rightPartOfBusinessCd;
            logger.info( "*-*-*-* bisnisNoPbVersion for " + rightPartOfBusinessCd + " = " + bisnisNoPbVersion );

            cepr01030102Form.setLeftPartOfBusinessCd( leftPartOfBusinessCd );
            cepr01030102Form.setRightPartOfBusinessCd( rightPartOfBusinessCd );
            cepr01030102Form.setBisnisNoPbVersion( bisnisNoPbVersion );
        }
        else
        {
            // show the default form for entry
            entry = new Cepr01040000DefaultEntry( eproposalManager, editorUtil, command, errors );
            cepr01030102Form.setLeftPartOfBusinessCd( 0 );
            cepr01030102Form.setRightPartOfBusinessCd( 0 );
            cepr01030102Form.setBisnisNoPbVersion( null );
        }
        return entry;
    }

    // user this to filter products detail
    public List<AssurancePlanList> filterProductsByUserAccessRight( List<AssurancePlanList> assurancePlanList, CredentialsVO credentialsVO )
    {
        logger.info( "*-*-*-* Cepr01030102Business.filterProductsByUserAccessRight" );
        List<AssurancePlanList> result = new ArrayList<AssurancePlanList>();

        List<String> filterList = eproposalManager.selectLstProductsFilteredOptionVOList( credentialsVO.getMsagId() );
        for( AssurancePlanList optionVO : assurancePlanList )
        {
            if( filterList.contains( optionVO.getValue() ) )
            {
                result.add( optionVO );
            }
        }

        return result;
    }

    
    // filter for package product
    public List<AssurancePlanList> filterProductsByPackage( List<AssurancePlanList> assurancePlanList, CredentialsVO credentialsVO )
    {
        List<AssurancePlanList> result = new ArrayList<AssurancePlanList>();

        List<String> filterList = eproposalManager.selectLstProductsPackageFilteredOptionVOList( credentialsVO.getMsagId() );
        for( AssurancePlanList optionVO : assurancePlanList )
        {
            if( filterList.contains( optionVO.getValue() ) )
            {
                result.add( optionVO );
            }
        }
        // HARDCODE LABEL PAKET
        for( AssurancePlanList optVO : result )
        {
        	if( "116~X4".equals( optVO.getValue() ) ){
        		optVO.setLabel("SMiLe PENSION");
        		optVO.setFlag_paket(1);
        	}
        	if( "159~X2".equals( optVO.getValue() ) ){
        		optVO.setLabel("SMiLe LADIES EXCLUSIVE");
        		optVO.setFlag_paket(1);
        	}
        	if( "160~X2".equals( optVO.getValue() ) ){
        		optVO.setLabel("SMiLe LADIES EXCLUSIVE SYARIAH");
        		optVO.setFlag_paket(1);
        	}
        	if( credentialsVO != null && credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals( Hardcode.GROUP_TIM_HAFRI ) ){
        		if( "153~X4".equals( optVO.getValue() ) ){
            		optVO.setLabel("Paket Ibadah");
            		optVO.setFlag_paket(1);
            	}
        	}else{
        		if( "153~X4".equals( optVO.getValue() ) ){
            		optVO.setLabel("SMiLe PENSION SYARIAH");
            		optVO.setFlag_paket(1);
            	}
        	}
        }

        return result;
    }
    /**
     * @param command: param command
     * @return list of business detail of the business choice
     *         ex: business choice "Super Sehat" will return List { "Super Sehat SS1", "Super Sehat SS2", "Super Sehat SS3" }
     */

//    public List<OptionVO> selectSpecificLstBusinessOptionVOList( Object command )
//    {
//        logger.info( "*-*-*-* Cepr01030102Business.selectSpecificLstBusinessOptionVOList" );
//        List<OptionVO> result = new ArrayList<OptionVO>();
//        List<OptionVO> allSubProductList;
//        List<AssurancePlanList> planList = new ArrayList<AssurancePlanList>();
//        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
//        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
//        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
//        String[] planCdArr;
//       
//
//        planCdArr = new String[]
//                {
//                        Cepr00000000StringUtil.getLeftPartOfAssurancePlanCd( cepr01030101Form.getAssurancePlanCd1() ),
//                        Cepr00000000StringUtil.getLeftPartOfAssurancePlanCd( cepr01030101Form.getAssurancePlanCd2() ),
//                        Cepr00000000StringUtil.getLeftPartOfAssurancePlanCd( cepr01030101Form.getAssurancePlanCd3() ),
//                        Cepr00000000StringUtil.getLeftPartOfAssurancePlanCd( cepr01030101Form.getAssurancePlanCd4() ),
//                };
//
//        Map<String, List<String>> params = new HashMap<String, List<String>>();
//        params.put( "lsbsIdList", ArrUtil.toListFromArray( planCdArr ) );
//
//        // get all sub product chosen
//        allSubProductList = HtmlUtil.addEmptyOption( eproposalManager.selectSpecificLstBusinessOptionVOList( params ) );
//        allSubProductList = addHardCodeList( allSubProductList, cepr01030101Form );
//
//        // show the business id that listed in Mapper Class (ex: Cepr01040204Mapper)
//        int leftPartOfBusinessCd;
//        List<String> allSubProductShowedList = new ArrayList<String>();
//        List<String> showedList;
//        Cepr01040000MapperInterface mapper;
//        for( String planCd : planCdArr )
//        {
//            leftPartOfBusinessCd = LazyConverter.toInt( planCd );
//            if( leftPartOfBusinessCd != 0 )
//            {
//                mapper = getProductInstance( leftPartOfBusinessCd, command, null );
//                showedList = ArrUtil.toListFromArray( mapper.getShowedBusinessCdArr( leftPartOfBusinessCd ) );
//                if( showedList != null && showedList.size() > 0 ) allSubProductShowedList.addAll( showedList );
//            }
//        }
//
//        for( OptionVO optVO : allSubProductList )
//        {
//            if( allSubProductShowedList.contains( optVO.getValue() ) )
//            {
//                result.add( optVO );
//            }
//        }
//
//        // begin to filter according products right for each user
//        result = filterProductsByUserAccessRight( result, cepr01030000HoldingForm.getCredentialsVO() );
//        for( OptionVO optVO : result )
//        {
//        	planList.add( new AssurancePlanList(optVO.getValue(), optVO.getLabel(), 0) );
//        }
//        
//        
//        // HANDLE PACKAGE ===========================================================================================================================================================
//       
//        List<OptionVO> allSubProductPackageList;
//        String[] planpackageCdArr;
//        List<OptionVO> productPackagesList = new ArrayList<OptionVO>();
//        
//        planpackageCdArr = new String[]
//                {
//                        Cepr00000000StringUtil.getLeftPartOfAssurancePlanCd( cepr01030101Form.getAssurancePlanPackageCd1() ),
//                };
//
//        Map<String, List<String>> paramsPackage = new HashMap<String, List<String>>();
//        paramsPackage.put( "lsbsIdList", ArrUtil.toListFromArray( planpackageCdArr ) );
//        
//        // get all sub product chosen
//        allSubProductPackageList = HtmlUtil.addEmptyOption( eproposalManager.selectSpecificLstBusinessOptionVOList( paramsPackage ) );
//        allSubProductPackageList = addHardCodePackageList( allSubProductPackageList, cepr01030101Form );
//        
//        // show the business id that listed in Mapper Class (ex: Cepr01040204Mapper)
//        int leftPartOfBusinessPackageCd;
//        List<String> allSubProductPackageShowedList = new ArrayList<String>();
//        List<String> showedPackageList;
//        Cepr01040000MapperInterface mapperPackage;
//        for( String planCd : planpackageCdArr )
//        {
//        	leftPartOfBusinessPackageCd = LazyConverter.toInt( planCd );
//            if( leftPartOfBusinessPackageCd != 0 )
//            {
//            	mapperPackage = getProductInstance( leftPartOfBusinessPackageCd, command, null );
//                showedPackageList = ArrUtil.toListFromArray( mapperPackage.getShowedBusinessCdArr( leftPartOfBusinessPackageCd ) );
//                if( showedPackageList != null && showedPackageList.size() > 0 ) allSubProductPackageShowedList.addAll( showedPackageList );
//            }
//        }
//
//        for( OptionVO optVO : allSubProductPackageList )
//        {
//            if( allSubProductPackageShowedList.contains( optVO.getValue() ) )
//            {
//            	productPackagesList.add( optVO );
//            }
//        }
//        
//        // begin to filter according products packages
//        productPackagesList = filterProductsByPackage( productPackagesList, cepr01030000HoldingForm.getCredentialsVO() );
//        for( OptionVO optVO : productPackagesList )
//        {
//        	planList.add( new AssurancePlanList(optVO.getValue(), optVO.getLabel(), 1) );
//        }
//        
//        //====================================================================================================================================================================
//
//        result.addAll( productPackagesList );
//        
//        // add empty option
//        result = HtmlUtil.addEmptyOption( result );
//
//        return result;
//    }
    
    
    
    public List<AssurancePlanList> selectSpecificLstBusinessOptionVOList( Object command )
    {
        logger.info( "*-*-*-* Cepr01030102Business.selectSpecificLstBusinessOptionVOList" );
        List<AssurancePlanList> result = new ArrayList<AssurancePlanList>();
        List<AssurancePlanList> allSubProductList;
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        CredentialsVO credentialsVO = cepr01030000HoldingForm.getCredentialsVO();
        String[] planCdArr;
       
        //handle untuk error karena null
        String assurancePlanCd1 = cepr01030101Form.getAssurancePlanCd1();
        if(assurancePlanCd1.isEmpty() && !cepr01030102Form.getAssurancePlanCd().isEmpty() ) {
        	assurancePlanCd1 = cepr01030102Form.getAssurancePlanCd();
        }
        String assurancePlanCd2 = (cepr01030101Form.getAssurancePlanCd2().isEmpty())?"":cepr01030101Form.getAssurancePlanCd2();
        String assurancePlanCd3 = (cepr01030101Form.getAssurancePlanCd3().isEmpty())?"":cepr01030101Form.getAssurancePlanCd3();
        String assurancePlanCd4 = (cepr01030101Form.getAssurancePlanCd4().isEmpty())?"":cepr01030101Form.getAssurancePlanCd4();
        
        planCdArr = new String[]
                {
                        Cepr00000000StringUtil.getLeftPartOfAssurancePlanCd( assurancePlanCd1 ),
                        Cepr00000000StringUtil.getLeftPartOfAssurancePlanCd( assurancePlanCd2 ),
                        Cepr00000000StringUtil.getLeftPartOfAssurancePlanCd( assurancePlanCd3 ),
                        Cepr00000000StringUtil.getLeftPartOfAssurancePlanCd( assurancePlanCd4 ),
                };

        Map<String, List<String>> params = new HashMap<String, List<String>>();
        params.put( "lsbsIdList", ArrUtil.toListFromArray( planCdArr ) );

        // get all sub product chosen
        allSubProductList = HtmlUtil.addEmptyProductOption( eproposalManager.selectSpecificLstBusinessOptionVOList( params ) );
        allSubProductList = addHardCodeProductList( allSubProductList, cepr01030101Form );

        // show the business id that listed in Mapper Class (ex: Cepr01040204Mapper)
        int leftPartOfBusinessCd;
        List<String> allSubProductShowedList = new ArrayList<String>();
        List<String> showedList;
        Cepr01040000MapperInterface mapper;
        for( String planCd : planCdArr )
        {
            leftPartOfBusinessCd = LazyConverter.toInt( planCd );
            if( leftPartOfBusinessCd != 0 )
            {
                mapper = getProductInstance( leftPartOfBusinessCd, command, null );
                showedList = ArrUtil.toListFromArray( mapper.getShowedBusinessCdArr( leftPartOfBusinessCd ) );
                if( showedList != null && showedList.size() > 0 ) allSubProductShowedList.addAll( showedList );
            }
        }

        for( AssurancePlanList optVO : allSubProductList )
        {
            if( allSubProductShowedList.contains( optVO.getValue() ) )
            {
                result.add( optVO );
            }
        }

        // begin to filter according products right for each user
        result = filterProductsByUserAccessRight( result, cepr01030000HoldingForm.getCredentialsVO() );
        
        
        // HANDLE PACKAGE ===========================================================================================================================================================
//        if( credentialsVO.getGroupId() != null && credentialsVO.getGroupId().equals(Hardcode.GROUP_MNC)){
//        	
//        }
//        else{
        if( cepr01030101Form.getAssurancePlanPackageCd1() != null  ){
        		
        	
        List<AssurancePlanList> allSubProductPackageList;
        String[] planpackageCdArr;
        List<AssurancePlanList> productPackagesList = new ArrayList<AssurancePlanList>();
        
        planpackageCdArr = new String[]
                {
                        Cepr00000000StringUtil.getLeftPartOfAssurancePlanCd( cepr01030101Form.getAssurancePlanPackageCd1() ),
                };

        Map<String, List<String>> paramsPackage = new HashMap<String, List<String>>();
        paramsPackage.put( "lsbsIdList", ArrUtil.toListFromArray( planpackageCdArr ) );
        
        // get all sub product chosen
        allSubProductPackageList = HtmlUtil.addEmptyProductOption( eproposalManager.selectSpecificLstBusinessOptionVOList( paramsPackage ) );
        allSubProductPackageList = addHardCodePackageList( allSubProductPackageList, cepr01030101Form );
        
        // show the business id that listed in Mapper Class (ex: Cepr01040204Mapper)
        int leftPartOfBusinessPackageCd;
        List<String> allSubProductPackageShowedList = new ArrayList<String>();
        List<String> showedPackageList;
        Cepr01040000MapperInterface mapperPackage;
        for( String planCd : planpackageCdArr )
        {
        	leftPartOfBusinessPackageCd = LazyConverter.toInt( planCd );
            if( leftPartOfBusinessPackageCd != 0 )
            {
            	mapperPackage = getProductInstance( leftPartOfBusinessPackageCd, command, null );
                showedPackageList = ArrUtil.toListFromArray( mapperPackage.getShowedBusinessCdArr( leftPartOfBusinessPackageCd ) );
                if( showedPackageList != null && showedPackageList.size() > 0 ) allSubProductPackageShowedList.addAll( showedPackageList );
            }
        }

        for( AssurancePlanList optVO : allSubProductPackageList )
        {
            if( allSubProductPackageShowedList.contains( optVO.getValue() ) )
            {
            	productPackagesList.add( optVO );
            }
        }
        
        // begin to filter according products packages
        productPackagesList = filterProductsByPackage( productPackagesList, cepr01030000HoldingForm.getCredentialsVO() );
        
        //====================================================================================================================================================================

        result.addAll( productPackagesList );
        }
//        }
        // add empty option
        result = HtmlUtil.addEmptyProductOption( result );

        return result;
    }

    /**
     * This class
     *
     * @param command  is term form object in Spring
     * @param jobDescr for ex: DO_INIT_FORM, DO_VALIDATION, DO_DOWNLOAD, DO_PROCESS_FORM_AFTER_SUBMISSION_BEFORE_VALIDATION
     * @param errors   is errors in Spring MVC
     * @param theEvent   is the event that trigger action
     * @return map, it is a result for this method, you can use this for anything
     */
    public Map<String, Object> doSpecificForEachProduct( Object command, JobDescr jobDescr, Errors errors, String theEvent )
    {
        logger.info( "*-*-*-* Cepr01030102Business.doSpecificForEachProduct" );
        Map<String, Object> result = new HashMap<String, Object>();
        Cepr01030000HoldingForm cepr01030000HoldingForm = ( Cepr01030000HoldingForm ) command;
        Cepr01030102Form cepr01030102Form = cepr01030000HoldingForm.getCepr01030102Form();
        Cepr01030101Form cepr01030101Form = cepr01030000HoldingForm.getCepr01030101Form();
        
        this.entry = getBusinessCdAndEntryInstance( command, errors, theEvent );

        if( jobDescr == JobDescr.DO_INIT_FORM )
        {
            logger.info( "*-*-*-* DO_INIT_FORM" );
            entry.initDisplayedForm();
            entry.initDisabledForm();
            entry.initReadOnlyForm();

            if( StringUtil.isEmpty(cepr01030102Form.getAssurancePlanCd() )){
            	cepr01030102Form.setAssurancePlanCd("");
            }
            if( StringUtil.isEmpty(cepr01030102Form.getPrevAssurancePlanCd() )){
            	cepr01030102Form.setPrevAssurancePlanCd("");
            }
            
            // do just when business number (in combo box) changed
            if( !cepr01030102Form.getAssurancePlanCd().equals( cepr01030102Form.getPrevAssurancePlanCd() ) )
            {
                entry.resetFormsWhenRightPartCdChanged();                  
                entry.fillDefaultValueEachTimeRightPartOfBusinessCdChanged();
            }     
            
            entry.fillDefaultValueEachTimeFormCalled();
            entry.synchronizeSelectedListOption();
                     
            if( errors.getFieldErrorCount() == 0 )
            {
                entry.validatePreviousPage();
            }
        }
        else if( jobDescr == JobDescr.DO_PROCESS_FORM_AFTER_SUBMISSION_BEFORE_VALIDATION )
        {
//          logger.info( "*-*-*-* DO_PROCESS_FORM_AFTER_SUBMISSION_BEFORE_VALIDATION" );
            entry.processFormAfterSubmitBeforeValidation();
        }
        else if( jobDescr == JobDescr.DO_VALIDATION )
        {
//            logger.info( "*-*-*-* DO_VALIDATION" );
            // do not validate when user change plan cd on page 2
        	if(cepr01030102Form.getPrevAssurancePlanCd() == null) {
        		cepr01030102Form.setPrevAssurancePlanCd("");
        	}
            if( cepr01030102Form.getPrevAssurancePlanCd().equals( cepr01030102Form.getAssurancePlanCd() ) )
            {
                entry.validatePreviousPage();
                entry.validateCurrentPage();
            }
        }
        else if( jobDescr == JobDescr.DO_DOWNLOAD )
        {
//            logger.info( "*-*-*-* DO_DOWNLOAD" );
//            logger.info( "*-*-*-* entry.getDownloadUrl() = " + entry.getDownloadUrl() );
            result.put( CommonConst.SES_DOWNLOAD_URL_SESSION, entry.getDownloadUrl() );
        }        
       
        return result;
    }

    public void fillDefaultValueEachTimeFormCalled( Object command )
    {
    	  logger.info( "*-*-*-* Cepr01030103Business.fillDefaultValueEachTimeFormCalled" );
          Cepr01030102Form cepr01030102Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030102Form();
          Cepr01030103Form cepr01030103Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030103Form();
          CredentialsVO credentialsVO = ( ( Cepr01030000HoldingForm ) command ).getCredentialsVO();
          
          if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_CERDAS && (cepr01030102Form.getRightPartOfBusinessCd() == 11 || cepr01030102Form.getRightPartOfBusinessCd() == 23)){
        	  cepr01030102Form.setEducationPackageList( eproposalManager.selectLstPacket(Hardcode.EDUCATION_PAKET_SIMPOL_BSM));
          }else{
        	  cepr01030102Form.setEducationPackageList( educationPackageList() );
          }      	 
          cepr01030102Form.setProtectorPackageList(protectorPackageList());
      	  cepr01030102Form.setProviderTypeList( providerTypeList() );       	 
    }
    
    
    public List< OptionVO > providerTypeList( )
    {
    	List<OptionVO> smileLadiesPackageList = new ArrayList<OptionVO>();
    	OptionVO optionVO;
    //	optionVO = new OptionVO( "1", "Non Provider" );
    //	smileLadiesPackageList.add( optionVO );
    	optionVO = new OptionVO( "2", "Provider" );
    	smileLadiesPackageList.add( optionVO );
        return smileLadiesPackageList;
    }
    
    public List< OptionVO > educationPackageList( )
    {
    	List<OptionVO> smileLadiesPackageList = new ArrayList<OptionVO>();
    	OptionVO optionVO;
    	optionVO = new OptionVO( "0", "None" );
    	smileLadiesPackageList.add( optionVO );
    	optionVO = new OptionVO( "21", "Paket A" );
    	smileLadiesPackageList.add( optionVO );
    	optionVO = new OptionVO( "22", "Paket B" );
    	smileLadiesPackageList.add( optionVO );
    	optionVO = new OptionVO( "23", "Paket C" );
    	smileLadiesPackageList.add( optionVO );
        return smileLadiesPackageList;
    }
    
    public List< OptionVO > protectorPackageList( )
    {
    	List<OptionVO> protectorPackageList = new ArrayList<OptionVO>();
    	OptionVO optionVO;
    	optionVO = new OptionVO( "0", "None" );
    	protectorPackageList.add( optionVO );
    	optionVO = new OptionVO( "24", "GOLD" );
    	protectorPackageList.add( optionVO );
    	optionVO = new OptionVO( "25", "TITANIUM" );
    	protectorPackageList.add( optionVO );
    	optionVO = new OptionVO( "26", "PLATINUM" );
    	protectorPackageList.add( optionVO );
        return protectorPackageList;
    }
    
    Cepr01040000MapperInterface getProductInstance( int leftPartOfBusinessCd, Object command, Errors errors )
    {
        logger.info( "*-*-*-* Cepr01030102Business.getProductInstance" );
        Cepr01040000MapperInterface mapper ;
        switch( leftPartOfBusinessCd )
        {
//            non unit link products
            case 45:
                mapper = new Cepr01040101Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 53:
                mapper = new Cepr01040102Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 54:
                mapper = new Cepr01040103Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 163:
                mapper = new Cepr01040104Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 39:
                mapper = new Cepr01040105Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 95:
                mapper = new Cepr01040106Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 142:
                mapper = new Cepr01040107Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 158:
                mapper = new Cepr01040108Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 155:
                mapper = new Cepr01040109Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 167:
                mapper = new Cepr01040110Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 164:
                mapper = new Cepr01040111Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 137:
                mapper = new Cepr01040112Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 73:
                mapper = new Cepr01040113Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 141:
                mapper = new Cepr01040114Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 172:
                mapper = new Cepr01040115Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 173:
                mapper = new Cepr01040116Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 114:
                mapper = new Cepr01040117Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 184:
                mapper = new Cepr01040118Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 168:
                mapper = new Cepr01040119Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 165:
                mapper = new Cepr01040122Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 140:
                mapper = new Cepr01040123Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 150:
                mapper = new Cepr01040125Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 151:
                mapper = new Cepr01040126Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 112:
                mapper = new Cepr01040127Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 183:
                mapper = new Cepr01040129Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 179:
                mapper = new Cepr01040128Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 1:
                mapper = new Cepr01040130Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 169:
                mapper = new Cepr01040131Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 96:
                mapper = new Cepr01040132Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 180:
                mapper = new Cepr01040133Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 182:
                mapper = new Cepr01040134Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 177:
                mapper = new Cepr01040135Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 185:
                mapper = new Cepr01040136Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 298:
                mapper = new Cepr01040137Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 207:
                mapper = new Cepr01040140Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 198:
                mapper = new Cepr01040141Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 189:
                mapper = new Cepr01040142Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 193:
                mapper = new Cepr01040143Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 195:
                mapper = new Cepr01040148Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 201:
                mapper = new Cepr01040144Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 111:
                mapper = new Cepr01040145Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 199:
                mapper = new Cepr01040146Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 206:
                mapper = new Cepr01040147Mapper( eproposalManager, editorUtil, command, errors );
                break;    
            case 208:
                mapper = new Cepr01040139Mapper( eproposalManager, editorUtil, command, errors );
                break;   
            case 219:
            	mapper = new Cepr01040149Mapper( eproposalManager, editorUtil, command, errors );
            	break;	
            case 212:
            	mapper = new Cepr01040150Mapper( eproposalManager, editorUtil, command, errors );
            	break;
            case 223:
            	mapper = new Cepr01040151Mapper( eproposalManager, editorUtil, command, errors );
            	break;
            case 226:
            	mapper = new Cepr01040152Mapper( eproposalManager, editorUtil, command, errors );
            	break;
            case 227: /**NCR/2021/02/052	SMiLe Income Protection X-Tra**/
            	mapper = new Cepr01040153Mapper( eproposalManager, editorUtil, command, errors );
            	break;
                
//            unit link products
            case 159:
                mapper = new Cepr01040201Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 160:
                mapper = new Cepr01040202Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 162:
                mapper = new Cepr01040203Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 115:
                mapper = new Cepr01040204Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 116:
                mapper = new Cepr01040205Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 118:
                mapper = new Cepr01040205Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 152:
                mapper = new Cepr01040206Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 153:
                mapper = new Cepr01040207Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 134:
                mapper = new Cepr01040208Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 166:
                mapper = new Cepr01040209Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 119:
                mapper = new Cepr01040210Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 175:
                mapper = new Cepr01040120Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 176:
                mapper = new Cepr01040121Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 120:
                mapper = new Cepr01040211Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 110:
                mapper = new Cepr01040124Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 128:
                mapper = new Cepr01040212Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 129:
                mapper = new Cepr01040213Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 127:
                mapper = new Cepr01040214Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 174:
                mapper = new Cepr01040215Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 191:
                mapper = new Cepr01040216Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 202:
                mapper = new Cepr01040218Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 190:
            	mapper = new Cepr01040219Mapper( eproposalManager, editorUtil, command, errors );
                break;
            case 200:
            	mapper =  new Cepr01040220Mapper( eproposalManager, editorUtil, command, errors );
            	break;
            case 213:
            	mapper = new Cepr01040221Mapper( eproposalManager, editorUtil, command, errors );
            	break;
            case 214:
            	mapper = new Cepr01040222Mapper( eproposalManager, editorUtil, command, errors );
            	break;
            case 215:
            	mapper = new Cepr01040223Mapper( eproposalManager, editorUtil, command, errors );
            	break;
            case 216:
            	mapper = new Cepr01040224Mapper( eproposalManager, editorUtil, command, errors );
            	break;
            case 217:
            	mapper = new Cepr01040225Mapper( eproposalManager, editorUtil, command, errors );
            	break;
            case 218:
            	mapper = new Cepr01040227Mapper( eproposalManager, editorUtil, command, errors );
            	break; 
            case 220:
            	mapper = new Cepr01040228Mapper( eproposalManager, editorUtil, command, errors );
            	break; 
            case 224:
            	mapper = new Cepr01040229Mapper( eproposalManager, editorUtil, command, errors );
            	break;  	
            default:
                throw new RuntimeException( "Business Id not listed in Cepr01030102Business.getProductInstance" );
        }
        
        return mapper;
    }      
  

}