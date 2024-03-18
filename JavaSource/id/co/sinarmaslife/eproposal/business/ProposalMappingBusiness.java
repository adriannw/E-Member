package id.co.sinarmaslife.eproposal.business;

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.constant.Rider;
import id.co.sinarmaslife.eproposal.common.lookup.Cepr00000000ComboLookupBusiness;
import id.co.sinarmaslife.eproposal.common.lookup.LookupList;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.dao.Cepr00000000CommonDao;
import id.co.sinarmaslife.eproposal.dao.Cepr01010101Dao;
import id.co.sinarmaslife.eproposal.dao.Cepr01030102Dao;
import id.co.sinarmaslife.eproposal.dao.Cepr01040201Dao;
import id.co.sinarmaslife.eproposal.model.data_proposal.DataProposal;
import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstDataProposal;
import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstProposalProduct;
import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstProposalProductPacket;
import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstProposalProductPeserta;
import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstProposalProductRider;
import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstProposalProductTopUp;
import id.co.sinarmaslife.eproposal.model.data_proposal.table.MstProposalProductUlink;
import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.eproposal.model.vo.CredentialsVO;
import id.co.sinarmaslife.eproposal.model.vo.ParticipantVO;
import id.co.sinarmaslife.eproposal.model.vo.TopupDrawVO;
import id.co.sinarmaslife.eproposal.product.product01040139.Cepr01040139DownloadController;
import id.co.sinarmaslife.eproposal.product.product01040149.Cepr01040149DownloadController;
import id.co.sinarmaslife.eproposal.product.product01040150.Cepr01040150DownloadController;
import id.co.sinarmaslife.eproposal.product.product01040151.Cepr01040151DownloadController;
import id.co.sinarmaslife.eproposal.product.product01040205.Cepr01040205DownloadController;
import id.co.sinarmaslife.eproposal.product.product01040208.Cepr01040208DownloadController;
import id.co.sinarmaslife.eproposal.product.product01040211.Cepr01040211DownloadController;
import id.co.sinarmaslife.eproposal.product.product01040219.Cepr01040219DownloadController;
import id.co.sinarmaslife.eproposal.product.product01040221.Cepr01040221DownloadController;
import id.co.sinarmaslife.eproposal.product.product01040223.Cepr01040223DownloadController;
import id.co.sinarmaslife.eproposal.product.product01040224.Cepr01040224DownloadController;
import id.co.sinarmaslife.eproposal.product.product01040225.Cepr01040225DownloadController;
import id.co.sinarmaslife.eproposal.product.product01040228.Cepr01040228DownloadController;
import id.co.sinarmaslife.eproposal.product.product01040229.Cepr01040229DownloadController;
import id.co.sinarmaslife.eproposal.product.product01040153.Cepr01040153DownloadController;/**NCR/2021/02/052	SMiLe Income Protection X-Tra**/
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030101Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030103Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030104Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030106Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030107Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030108Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030109Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030110Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030111Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030112Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030113Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030114Form;
import id.co.sinarmaslife.eproposal.web.Cepr01031001Form;
import id.co.sinarmaslife.standard.model.vo.OptionVO;
import id.co.sinarmaslife.standard.model.vo.UserGroup;
import id.co.sinarmaslife.standard.propertyeditors.CurrencyFormatEditor;
import id.co.sinarmaslife.standard.util.StringUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class ProposalMappingBusiness {

    private EproposalManager eproposalManager;
    private Cepr00000000EditorUtil editorUtil;
    private Cepr00000000ComboLookupBusiness comboLookupBusiness;
    
    public void setEproposalManager(EproposalManager eproposalManager) {
        this.eproposalManager = eproposalManager;
    }
    
    public ProposalMappingBusiness(ServletContext context) throws IOException{
    	  WebApplicationContext ctx =
                  WebApplicationContextUtils
                          .getWebApplicationContext(context);

    	  Cepr01010101Dao cepr01010101Dao = (Cepr01010101Dao) ctx.getBean("cepr01010101Dao", Cepr01010101Dao.class);
  //  	  Cepr00000000CommonDao commonDao = (Cepr00000000CommonDao) ctx.getBean( null, Cepr00000000CommonDao.class); 
    	  Cepr00000000CommonDao commonDao = (Cepr00000000CommonDao) ctx.getBean("cepr00000000CommonDao", Cepr00000000CommonDao.class); 
    	  
    	//  UserDao dao = ctx.getBean("userDao", UserDao.class);
    	  /*
    	  Cepr01010101Dao cepr01010101Dao = (Cepr01010101Dao) ctx.getBean("cepr01010101Dao", Cepr01010101Dao.class);*/
    	  Cepr01030102Dao cepr01030102Dao = (Cepr01030102Dao) ctx.getBean("cepr01030102Dao", Cepr01030102Dao.class);
    	  Cepr01040201Dao cepr01040201Dao = (Cepr01040201Dao) ctx.getBean("cepr01040201Dao", Cepr01040201Dao.class);
    	  
    	  EproposalManager eproposalManager = new EproposalManager();
    	  eproposalManager.setCepr00000000CommonDao(commonDao);
    	  eproposalManager.setCepr01010101Dao(cepr01010101Dao);
    	  eproposalManager.setCepr01030102Dao(cepr01030102Dao);
    	  eproposalManager.setCepr01040201Dao(cepr01040201Dao);
    	  
    	  Properties props = new Properties();
          props.load(Resources.getResourceAsStream("eproposal.properties"));
          eproposalManager.setProps(props);
          this.setEproposalManager(eproposalManager);
          
          editorUtil = new Cepr00000000EditorUtil();
          editorUtil.setDateEditor(new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
          editorUtil.setIntegerEditor(new CustomNumberEditor(Integer.class, NumberFormat.getNumberInstance(), true));
          editorUtil.setCurrencyEditor(new CurrencyFormatEditor("###,###,###,###,##0.00"));
          editorUtil.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
          
          comboLookupBusiness = new Cepr00000000ComboLookupBusiness(eproposalManager, editorUtil);
    }
    
    public ProposalMappingBusiness() throws Exception {
        SqlMapClient sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsStream("id/co/sinarmaslife/eproposal/dao/ibatis/WebserviceSqlMap.xml"));
  
        
        
        Cepr00000000CommonDao commonDao = new Cepr00000000CommonDao();
        commonDao.initDao();
        commonDao.setSqlMapClient(sqlMapClient);
        
        Cepr01010101Dao cepr01010101Dao = new Cepr01010101Dao();
        cepr01010101Dao.setSqlMapClient(sqlMapClient);
        
        Cepr01030102Dao cepr01030102Dao = new Cepr01030102Dao();
        cepr01030102Dao.setSqlMapClient(sqlMapClient);
        
        Cepr01040201Dao cepr01040201Dao = new Cepr01040201Dao();
        cepr01040201Dao.setSqlMapClient(sqlMapClient);
        
        EproposalManager eproposalManager = new EproposalManager();
        eproposalManager.setCepr00000000CommonDao(commonDao);
        eproposalManager.setCepr01010101Dao(cepr01010101Dao);
        eproposalManager.setCepr01030102Dao(cepr01030102Dao);
        eproposalManager.setCepr01040201Dao(cepr01040201Dao);
        
        Properties props = new Properties();
        props.load(Resources.getResourceAsStream("eproposal.properties"));
        eproposalManager.setProps(props);
        
        this.setEproposalManager(eproposalManager);
        
        editorUtil = new Cepr00000000EditorUtil();
        editorUtil.setDateEditor(new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
        editorUtil.setIntegerEditor(new CustomNumberEditor(Integer.class, NumberFormat.getNumberInstance(), true));
        editorUtil.setCurrencyEditor(new CurrencyFormatEditor("###,###,###,###,##0.00"));
        editorUtil.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        
        comboLookupBusiness = new Cepr00000000ComboLookupBusiness(eproposalManager, editorUtil);
    }
    
    public DataProposal getProposalData(String noProposal) {
        return eproposalManager.selectProposalData(noProposal);
    }
    
    
    public Cepr01030000HoldingForm getConvertToHoldingForm(DataProposal dataProposal) throws UnknownHostException{
         Cepr01030000HoldingForm cepr01030000HoldingForm = new Cepr01030000HoldingForm();       
         Cepr01030101Form cepr01030101Form = new Cepr01030101Form();
         Cepr01030102Form cepr01030102Form = new Cepr01030102Form();
         Cepr01030103Form cepr01030103Form = new Cepr01030103Form();
         Cepr01030104Form cepr01030104Form = new Cepr01030104Form();
         Cepr01030106Form cepr01030106Form = new Cepr01030106Form();
         Cepr01030107Form cepr01030107Form = new Cepr01030107Form();
         Cepr01030108Form cepr01030108Form = new Cepr01030108Form();
         Cepr01030109Form cepr01030109Form = new Cepr01030109Form();
         Cepr01030110Form cepr01030110Form = new Cepr01030110Form();
         Cepr01030111Form cepr01030111Form = new Cepr01030111Form();
         Cepr01030112Form cepr01030112Form = new Cepr01030112Form();
         Cepr01030113Form cepr01030113Form = new Cepr01030113Form();
         Cepr01030114Form cepr01030114Form = new Cepr01030114Form();
         Cepr01031001Form cepr01031001Form = new Cepr01031001Form();
         
         Cepr01030102Business cepr01030102Business = new Cepr01030102Business(eproposalManager, editorUtil);
         
         //
         MstDataProposal mst_data_proposal = dataProposal.getMst_data_proposal();
         //      
         cepr01030101Form.setPolicyHolderName(mst_data_proposal.getNama_pp());
         cepr01030101Form.setPolicyHolderBirthDay(mst_data_proposal.getTgl_lahir_pp());
         cepr01030101Form.setPolicyHolderAge(mst_data_proposal.getUmur_pp());
         cepr01030101Form.setPolicyHolderSexCd(mst_data_proposal.getSex_pp());
         
         cepr01030101Form.setInsuredName(mst_data_proposal.getNama_tt());        
         cepr01030101Form.setInsuredBirthDay(mst_data_proposal.getTgl_lahir_tt());
         cepr01030101Form.setInsuredAge(mst_data_proposal.getUmur_tt());
         
         String sex = mst_data_proposal.getSex_tt();
         if("0".equals(sex)) sex = "P";
         else if("1".equals(sex)) sex = "L";
         cepr01030101Form.setInsuredSexCd(sex);
         //menambahkan fungsi set gender pemegang polis 16Nov2021 iga
         String sexpp = mst_data_proposal.getSex_pp();
         if("0".equals(sexpp)) sexpp = "P";
         else if("1".equals(sexpp)) sexpp = "L";
         cepr01030101Form.setPolicyHolderSexCd(sexpp);
         
         cepr01030101Form.setProposalUser(mst_data_proposal.getNama_agen());
         cepr01030101Form.setProposalDate(mst_data_proposal.getTgl_input());
         cepr01030101Form.setProposalUserCd(mst_data_proposal.getKode_agen());
         
         MstProposalProduct mst_proposal_product = dataProposal.getMst_proposal_product();
         ArrayList<MstProposalProductUlink> mst_proposal_product_ulink = dataProposal.getMst_proposal_product_ulink();
         //
         Mst_proposal mstProposal = new Mst_proposal();
         mstProposal.setId(mst_data_proposal.getNo_proposal());
         mstProposal.setMsag_id(mst_data_proposal.getMsag_id());
         mstProposal.setFlag_test(mst_data_proposal.getFlag_test());
         
         List<Mst_proposal> listMstProposal = new ArrayList<Mst_proposal>();
         listMstProposal.add(mstProposal);                
         cepr01030102Form.setMstProposal(listMstProposal);
         
         cepr01030102Form.setLeftPartOfBusinessCd(mst_proposal_product.getLsbs_id());
         //issue autosales
         if( cepr01030102Form.getLeftPartOfBusinessCd()==208){
        	 mst_proposal_product.setThn_cuti_premi(5);
         }
         //CR#: NCR/2020/08/019 FLAG VIP (IGA)
//         CommmonConst.CHECKED_TRUE.equals(cepr01030102Form.setFlagOfVIP(mst_data_proposal.getFlag_vip());
         if(mst_data_proposal.getFlag_vip()==null){
        	 cepr01030102Form.setFlagOfVIP(CommonConst.CHECKED_FALSE);
         }
         else if(mst_data_proposal.getFlag_vip()==0){
        	 cepr01030102Form.setFlagOfVIP(CommonConst.CHECKED_FALSE);
         }else{
        	 cepr01030102Form.setFlagOfVIP(CommonConst.CHECKED_TRUE);
         }
         //DONE
         
         cepr01030102Form.setRightPartOfBusinessCd(mst_proposal_product.getLsdbs_number());
         cepr01030102Form.setBisnisNoPbVersion(mst_proposal_product.getLsdbs_number());
         cepr01030102Form.setAssurancePlanCd(mst_proposal_product.getLsbs_id().toString() + "~X" + mst_proposal_product.getLsdbs_number().toString());
         cepr01030102Form.setCurrencyCd(mst_proposal_product.getLku_id());
         cepr01030102Form.setPremium(mst_proposal_product.getPremi());
         cepr01030102Form.setPremiumCombinationCd(mst_proposal_product.getPremi_komb());
         if(cepr01030102Form.getLeftPartOfBusinessCd()==134 || cepr01030102Form.getLeftPartOfBusinessCd()==215){ //-->NCR/2020/09/023 IGA UPDATE COI
        	 if(mst_proposal_product.getCara_bayar() ==  Hardcode.PAY_MODE_CD_SEKALIGUS && mst_proposal_product.getPremi_komb() == null){
        		 cepr01030102Form.setPremiumCombinationCd(new BigDecimal(100));
             }else{
            	 cepr01030102Form.setPremiumCombinationCd(mst_proposal_product.getPremi_komb());
             }
         }else{
        	 cepr01030102Form.setPremiumCombinationCd(mst_proposal_product.getPremi_komb());
         }  //<--DONE   
         cepr01030102Form.setBasePremium(mst_proposal_product.getPremi_pokok());
         cepr01030102Form.setTopUpPremium(mst_proposal_product.getPremi_topup());
         cepr01030102Form.setTotalSumInsured(mst_proposal_product.getUp());
         cepr01030102Form.setPaymentModeCd(mst_proposal_product.getCara_bayar());
         cepr01030102Form.setPremiumFurloughYearCd(mst_proposal_product.getThn_cuti_premi());
         cepr01030102Form.setPremiumFurloughYear(mst_proposal_product.getThn_cuti_premi());
         cepr01030102Form.setTermOfContract(mst_proposal_product.getThn_masa_kontrak());
         cepr01030102Form.setTermOfPayment(mst_proposal_product.getThn_lama_bayar());
         //issue autosales       
         if( mst_proposal_product.getThn_lama_bayar() == null ){
        	 cepr01030102Form.setTermOfPayment(mst_proposal_product.getThn_cuti_premi());
        	 
         }
         
         
    
         Integer flag_invest_fund = eproposalManager.selectFlagInvestFund(mst_data_proposal.getNo_proposal());
         for(MstProposalProductUlink product_ulink : mst_proposal_product_ulink ){
            if(flag_invest_fund==1){//Magna
                if(product_ulink.getLji_id().equals("01") || product_ulink.getLji_id().equals("06") || product_ulink.getLji_id().equals("35") || product_ulink.getLji_id().equals("47") || product_ulink.getLji_id().equals("42") || product_ulink.getLji_id().equals("50")
                || product_ulink.getLji_id().equals("53")){
                    cepr01030102Form.setLjiFixCd(product_ulink.getLji_id());
                    cepr01030102Form.setFixIdr(new BigDecimal(product_ulink.getMdu_persen()));
                }else if(product_ulink.getLji_id().equals("02") || product_ulink.getLji_id().equals("07") || product_ulink.getLji_id().equals("36") || product_ulink.getLji_id().equals("48") || product_ulink.getLji_id().equals("43") || product_ulink.getLji_id().equals("51")
                || product_ulink.getLji_id().equals("54")){
                    cepr01030102Form.setLjiDynamicCd(product_ulink.getLji_id());
                    cepr01030102Form.setDynamicIdr(new BigDecimal(product_ulink.getMdu_persen()));
                }else if(product_ulink.getLji_id().equals("03")|| product_ulink.getLji_id().equals("08") || product_ulink.getLji_id().equals("37") || product_ulink.getLji_id().equals("49") || product_ulink.getLji_id().equals("44") || product_ulink.getLji_id().equals("52")
                || product_ulink.getLji_id().equals("55")){
                    cepr01030102Form.setLjiAggresiveCd(product_ulink.getLji_id());
                    cepr01030102Form.setAggresiveIdr(new BigDecimal(product_ulink.getMdu_persen()));
                }else if( product_ulink.getLji_id().equals("61") || product_ulink.getLji_id().equals("62")){
                    cepr01030102Form.setLjiCashFundCd(product_ulink.getLji_id());
                    cepr01030102Form.setCashFundIdr(new BigDecimal(product_ulink.getMdu_persen()));
                }else if(product_ulink.getLji_id().equals("63")){
                    cepr01030102Form.setLjiExcellinkEquityCd(product_ulink.getLji_id());
                    cepr01030102Form.setExcellinkEquityIdr(new BigDecimal(product_ulink.getMdu_persen()));
                } 
            }
            else if(flag_invest_fund==0){ //PrimeLink
                if(product_ulink.getLji_id().equals("01") || product_ulink.getLji_id().equals("06")){
                    cepr01030102Form.setLjiFixCd(product_ulink.getLji_id());
                    cepr01030102Form.setFixIdr(new BigDecimal(product_ulink.getMdu_persen()));
                }else if(product_ulink.getLji_id().equals("35") || product_ulink.getLji_id().equals("58") ){
                    cepr01030102Form.setLjiFixSimasCd(product_ulink.getLji_id());
                    cepr01030102Form.setFixSimasIdr(new BigDecimal(product_ulink.getMdu_persen()));
                }else if(product_ulink.getLji_id().equals("02") || product_ulink.getLji_id().equals("07")){
                    cepr01030102Form.setLjiDynamicCd(product_ulink.getLji_id());
                    cepr01030102Form.setDynamicIdr(new BigDecimal(product_ulink.getMdu_persen()));
                }else if(product_ulink.getLji_id().equals("36") || product_ulink.getLji_id().equals("59")){
                    cepr01030102Form.setLjiDynamicSimasCd(product_ulink.getLji_id());
                    cepr01030102Form.setDynamicSimasIdr(new BigDecimal(product_ulink.getMdu_persen()));
                }else if(product_ulink.getLji_id().equals("03") || product_ulink.getLji_id().equals("08")){
                    cepr01030102Form.setLjiAggresiveCd(product_ulink.getLji_id());
                    cepr01030102Form.setAggresiveIdr(new BigDecimal(product_ulink.getMdu_persen()));
                }else if(product_ulink.getLji_id().equals("37")  || product_ulink.getLji_id().equals("60")){
                    cepr01030102Form.setLjiAggresiveSimasCd(product_ulink.getLji_id());
                    cepr01030102Form.setAggresiveSimasIdr(new BigDecimal(product_ulink.getMdu_persen()));
                }else if(product_ulink.getLji_id().equals("61") || product_ulink.getLji_id().equals("62")){
                    cepr01030102Form.setLjiCashFundCd(product_ulink.getLji_id());
                    cepr01030102Form.setCashFundIdr(new BigDecimal(product_ulink.getMdu_persen()));
                }else if(product_ulink.getLji_id().equals("63")){
                    cepr01030102Form.setLjiExcellinkEquityCd(product_ulink.getLji_id());
                    cepr01030102Form.setExcellinkEquityIdr(new BigDecimal(product_ulink.getMdu_persen()));
                }                
            }           
            if(product_ulink.getLji_id().equals("04") || product_ulink.getLji_id().equals("56") || product_ulink.getLji_id().equals("45")){
                cepr01030102Form.setLjiSecureUsdCd(product_ulink.getLji_id());
                cepr01030102Form.setSecureUsd(new BigDecimal(product_ulink.getMdu_persen()));
            }else if(product_ulink.getLji_id().equals("05") || product_ulink.getLji_id().equals("57") || product_ulink.getLji_id().equals("46")){
                cepr01030102Form.setLjiDynamicUsdCd(product_ulink.getLji_id());
                cepr01030102Form.setDynamicUsd(new BigDecimal(product_ulink.getMdu_persen()));
            }else if(product_ulink.getLji_id().equals("67") || product_ulink.getLji_id().equals("66") ){/**USD Fund BSIM Products**/
                cepr01030102Form.setLjiAggresiveUsdCd(product_ulink.getLji_id());
                cepr01030102Form.setAggresiveUsd(new BigDecimal(product_ulink.getMdu_persen()));
            }
            
            if(Hardcode.CUR_IDR_CD.equals(mst_proposal_product.getLku_id())) {
                cepr01030102Form.setLjiFixListDisplay(CommonConst.DISPLAY_TRUE);
                cepr01030102Form.setLjiDynamicListDisplay(CommonConst.DISPLAY_TRUE);
                cepr01030102Form.setLjiAggresiveListDisplay(CommonConst.DISPLAY_TRUE);
            } else {
                cepr01030102Form.setLjiSecureUsdListDisplay(CommonConst.DISPLAY_TRUE);
                cepr01030102Form.setLjiDynamicUsdListDisplay(CommonConst.DISPLAY_TRUE);
                cepr01030102Form.setLjiAggresiveUsdListDisplay(CommonConst.DISPLAY_TRUE);/**USD Fund BSIM Products**/
            }
         }
         
         ArrayList<MstProposalProductTopUp> mst_proposal_product_topup = dataProposal.getMst_proposal_product_topup();
         ArrayList<TopupDrawVO> topupdrawvoList = new ArrayList<TopupDrawVO>();
         for(int i=1; i<=50; i++){
             TopupDrawVO topupdrawvo = new TopupDrawVO(i);
             for(MstProposalProductTopUp topup: mst_proposal_product_topup ){
                 if(topup.getThn_ke()==i){
                     topupdrawvo.setYearFlag(CommonConst.CHECKED_TRUE);
                     if(topup.getTopup()!=null){
                         topupdrawvo.setTopupAmount(topup.getTopup());                       
                     }
                     if(topup.getTarik()!=null){
                         topupdrawvo.setDrawAmount(topup.getTarik());                        
                     }
                     break;
                 }
             }                           
             topupdrawvoList.add(topupdrawvo);
         }
         
         cepr01030104Form.setTopupDrawVOList(topupdrawvoList);

         // Credentials
         HashMap<String, Object> params = new HashMap<String, Object>();
         params.put("msag_id", mst_data_proposal.getMsag_id());
         UserGroup userGroup = eproposalManager.selectUserGroup(params);
         
         CredentialsVO credentialsVO = new CredentialsVO();
         credentialsVO.setMsagId(mst_data_proposal.getMsag_id());
         credentialsVO.setGroupId(userGroup.getGroupId());
         
         cepr01030000HoldingForm.setCredentialsVO(credentialsVO);
         
         //RIDER
         ArrayList<MstProposalProductRider> mst_proposal_product_rider = dataProposal.getMst_proposal_product_rider();
         for(MstProposalProductRider product_rider : mst_proposal_product_rider ){                       
             //PA
             if(product_rider.getLsbs_id()==Rider.RIDER_PA){     
                 cepr01030103Form.setPaFlag(CommonConst.CHECKED_TRUE);
                 cepr01030103Form.setPaRiskCd(product_rider.getLsdbs_number());
                 cepr01030103Form.setPaClassCd(product_rider.getKelas());
                 cepr01030103Form.setPaUnitQtyCd(product_rider.getJml_unit());               
             }
             // HCP      
             if(product_rider.getLsbs_id()==Rider.RIDER_HCP){    
                 cepr01030103Form.setHcpFlag(CommonConst.CHECKED_TRUE);
                 cepr01030103Form.setHcpCd(product_rider.getLsdbs_number());                     
             }
              // TPD
             if(product_rider.getLsbs_id()==Rider.RIDER_TPD){    
                 cepr01030103Form.setTpdFlag(CommonConst.CHECKED_TRUE);
                 cepr01030103Form.setHcpCd(product_rider.getLsdbs_number());                     
             }
              // CI
             if(product_rider.getLsbs_id()==Rider.RIDER_CI){     
                 cepr01030103Form.setCiFlag(CommonConst.CHECKED_TRUE);
                 cepr01030103Form.setCiRiderAmount(product_rider.getUp());
//                 cepr01030103Form.setCiChooseCd(String.valueOf(product_rider.getPersen_up()));       
             }
              // Waiver TPD
             if(product_rider.getLsbs_id()==Rider.RIDER_WAIVER_TPD){     
                 cepr01030103Form.setWaiverTpdFlag(CommonConst.CHECKED_TRUE);               
             }
             // Payor TPD/Death
             if(product_rider.getLsbs_id()==Rider.RIDER_PAYOR_TPD_DEATH){
                 if(product_rider.getLsdbs_number()==1){
                     cepr01030103Form.setPayorTpdDeathFlag(CommonConst.CHECKED_TRUE);       
                 }else if(product_rider.getLsdbs_number()==6){
                     cepr01030103Form.setPayorSpouseTpdDeathFlag(CommonConst.CHECKED_TRUE);
                 }                  
             }
             // Waiver CI
             if(product_rider.getLsbs_id()==Rider.RIDER_WAIVER_CI){  
                 cepr01030103Form.setWaiverCiFlag(CommonConst.CHECKED_TRUE);        
             }
            // Payor CI/Death + Payor CI
             if(product_rider.getLsbs_id()==Rider.RIDER_PAYOR_CI){
                 if(product_rider.getLsdbs_number()==1){
                     cepr01030103Form.setPayorCiDeathFlag(CommonConst.CHECKED_TRUE);
                 } else if(product_rider.getLsdbs_number()==4){
                     cepr01030103Form.setPayorCiFlag(CommonConst.CHECKED_TRUE);    
                 }
             }           
                          
             // HCP Family
             if(product_rider.getLsbs_id()==Rider.RIDER_HCP_FAMILY){
                 cepr01030103Form.setHcpFamilyFlag(CommonConst.CHECKED_TRUE);
                 cepr01030103Form.setHcpFamilyCd(product_rider.getLsdbs_number() - 140);
                 
                 //Peserta Tambahan
                 ArrayList<MstProposalProductPeserta> mst_proposal_product_peserta = dataProposal.getMst_proposal_product_peserta();
                 ArrayList<ParticipantVO> participantVOList = new ArrayList<ParticipantVO>();
                 for(MstProposalProductPeserta peserta_rider : mst_proposal_product_peserta ){  
                     if(peserta_rider.getLsbs_id()==Rider.RIDER_HCP_FAMILY){                                         
                         ParticipantVO participantVO = new ParticipantVO( peserta_rider.getNama_peserta(), peserta_rider.getTgl_lahir(), peserta_rider.getUsia() );
                         participantVO.setLsre_id(peserta_rider.getLsre_id());
                         participantVOList.add( participantVO );                                
                     } 
                 }
                 cepr01030106Form.setParticipantVOList(participantVOList);           
             }
 
             // Term Rider
             if(product_rider.getLsbs_id()==Rider.RIDER_TERM && product_rider.getLsdbs_number() < Hardcode.TERM_55_RIDER){   
                 cepr01030103Form.setTermRiderFlag(CommonConst.CHECKED_TRUE);  
                 cepr01030103Form.setTermRiderAmount(product_rider.getUp());                 
             }
             
             /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */             
             if( (product_rider.getLsbs_id()==Rider.RIDER_TERM && product_rider.getLsdbs_number() >= Hardcode.TERM_55_RIDER) || 
            		 (product_rider.getLsbs_id()==Rider.RIDER_TERM && product_rider.getLsdbs_number() >= Hardcode.TERM_65_RIDER) ||
            		 (product_rider.getLsbs_id()==Rider.RIDER_TERM && product_rider.getLsdbs_number() >= Hardcode.TERM_75_RIDER) ){   
                 cepr01030103Form.setTerm5575RiderFlag(CommonConst.CHECKED_TRUE);  
                 cepr01030103Form.setTerm5575RiderAmount(product_rider.getUp()); 
                 cepr01030103Form.setTerm5575RiderChooseCd(product_rider.getLsdbs_number());
             }
             
            // Eka Sehat
             if(product_rider.getLsbs_id()==Rider.RIDER_EKA_SEHAT){
                 cepr01030103Form.setEkaSehatFlag(CommonConst.CHECKED_TRUE);
                 cepr01030103Form.setEkaSehatCd(product_rider.getLsdbs_number());
                 
                 //Peserta Tambahan
                 ArrayList<MstProposalProductPeserta> mst_proposal_product_peserta = dataProposal.getMst_proposal_product_peserta();
                 ArrayList<ParticipantVO> participantVOList = new ArrayList<ParticipantVO>();
                 for(MstProposalProductPeserta peserta_rider : mst_proposal_product_peserta ){  
                     if(peserta_rider.getLsbs_id()==Rider.RIDER_EKA_SEHAT){                                          
                         ParticipantVO participantVO = new ParticipantVO( peserta_rider.getNama_peserta(), peserta_rider.getTgl_lahir(), peserta_rider.getUsia() );
                         participantVO.setLsre_id(peserta_rider.getLsre_id());
                         participantVOList.add( participantVO );                                
                     } 
                 }
                 cepr01030107Form.setParticipantVOList(participantVOList);
             }
                         
             // Eka Sehat Inner Limit
             if(product_rider.getLsbs_id()==Rider.RIDER_EKA_SEHAT_INNER_LIMIT){
                 cepr01030103Form.setEkaSehatInnerLimitFlag(CommonConst.CHECKED_TRUE);
                 cepr01030103Form.setEkaSehatInnerLimitCd(product_rider.getLsdbs_number());
                 
                 //Peserta Tambahan
                 ArrayList<MstProposalProductPeserta> mst_proposal_product_peserta = dataProposal.getMst_proposal_product_peserta();
                 ArrayList<ParticipantVO> participantVOList = new ArrayList<ParticipantVO>();
                 for(MstProposalProductPeserta peserta_rider : mst_proposal_product_peserta ){  
                     if(peserta_rider.getLsbs_id()==Rider.RIDER_EKA_SEHAT_INNER_LIMIT){                                          
                         ParticipantVO participantVO = new ParticipantVO( peserta_rider.getNama_peserta(), peserta_rider.getTgl_lahir(), peserta_rider.getUsia() );
                         participantVO.setLsre_id(peserta_rider.getLsre_id());
                         participantVOList.add( participantVO );                                
                     } 
                 }
                 cepr01030108Form.setParticipantVOList(participantVOList);
             }
             
            // HCP Provider
             if(product_rider.getLsbs_id()==Rider.RIDER_HCP_PROVIDER){
                 cepr01030103Form.setHcpProviderFlag(CommonConst.CHECKED_TRUE);
                 cepr01030103Form.setHcpProviderCd(product_rider.getLsdbs_number());
                 
                 //Peserta Tambahan
                 ArrayList<MstProposalProductPeserta> mst_proposal_product_peserta = dataProposal.getMst_proposal_product_peserta();
                 ArrayList<ParticipantVO> participantVOList = new ArrayList<ParticipantVO>();
                 for(MstProposalProductPeserta peserta_rider : mst_proposal_product_peserta ){  
                     if(peserta_rider.getLsbs_id()==Rider.RIDER_HCP_PROVIDER){                                       
                         ParticipantVO participantVO = new ParticipantVO( peserta_rider.getNama_peserta(), peserta_rider.getTgl_lahir(), peserta_rider.getUsia() );
                         participantVO.setLsre_id(peserta_rider.getLsre_id());
                         participantVOList.add( participantVO );                                
                     } 
                 }
                 cepr01030109Form.setParticipantVOList(participantVOList);
             }
             
              // Payor TPD/CI/Death + Payor 5/10 TPD/CI/Death
             if(product_rider.getLsbs_id()==Rider.RIDER_PAYOR_TPD_CI_DEATH){
                 if(product_rider.getLsdbs_number()==1){
                     cepr01030103Form.setPayorTpdCiDeathFlag(CommonConst.CHECKED_TRUE);
                 }else if(product_rider.getLsdbs_number()==2 || product_rider.getLsdbs_number()==3){
                     cepr01030103Form.setPayor5Tpd10CiDeathFlag(CommonConst.CHECKED_TRUE);
                 }
             }
             // Waiver TPD/CI/Death + Waiver 5/10 TPD/CI
             if(product_rider.getLsbs_id()==Rider.RIDER_WAIVER_TPD_CI){
                 if(product_rider.getLsdbs_number()==1 || product_rider.getLsdbs_number()==2 || product_rider.getLsdbs_number()==3){
                     cepr01030103Form.setWaiverTpdCiFlag(CommonConst.CHECKED_TRUE);
                     cepr01030103Form.setWaiverTpdCiChooseCd(product_rider.getLsdbs_number());
                 }else if(product_rider.getLsdbs_number()==4 || product_rider.getLsdbs_number()==5 ){
                     cepr01030103Form.setWaiver5Tpd10CiFlag(CommonConst.CHECKED_TRUE);                   
                 }              
             }
             
             // Ladies Insurance
             if(product_rider.getLsbs_id()==Rider.RIDER_LADIES_INSURANCE){
                 cepr01030103Form.setLadiesInsFlag(CommonConst.CHECKED_TRUE);
                 cepr01030103Form.setLadiesInsCd(product_rider.getLsdbs_number());
                 cepr01030103Form.setLadiesInsChooseCd(String.valueOf(product_rider.getPersen_up()));
             }
             
             // HCP Ladies
             if(product_rider.getLsbs_id()==Rider.RIDER_HCP_LADIES){
                 cepr01030103Form.setHcpLadiesFlag(CommonConst.CHECKED_TRUE);
                 cepr01030103Form.setHcpLadiesCd(product_rider.getLsdbs_number());
             
                //Peserta Tambahan
                 ArrayList<MstProposalProductPeserta> mst_proposal_product_peserta = dataProposal.getMst_proposal_product_peserta();
                 ArrayList<ParticipantVO> participantVOList = new ArrayList<ParticipantVO>();
                 for(MstProposalProductPeserta peserta_rider : mst_proposal_product_peserta ){  
                     if(peserta_rider.getLsbs_id()==Rider.RIDER_HCP_LADIES){                                         
                         ParticipantVO participantVO = new ParticipantVO( peserta_rider.getNama_peserta(), peserta_rider.getTgl_lahir(), peserta_rider.getUsia() );
                         participantVO.setLsre_id(peserta_rider.getLsre_id());
                         participantVOList.add( participantVO );                                
                     } 
                 }
                 cepr01030110Form.setParticipantVOList(participantVOList);
             }
             
            // Ladies Medical Expense
             if(product_rider.getLsbs_id()==Rider.RIDER_LADIES_MED_EXPENSE){
                 cepr01030103Form.setLadiesMedExpenseFlag(CommonConst.CHECKED_TRUE);
                 cepr01030103Form.setLadiesMedExpenseCd(product_rider.getLsdbs_number());
             
                //Peserta Tambahan
                 ArrayList<MstProposalProductPeserta> mst_proposal_product_peserta = dataProposal.getMst_proposal_product_peserta();
                 ArrayList<ParticipantVO> participantVOList = new ArrayList<ParticipantVO>();
                 for(MstProposalProductPeserta peserta_rider : mst_proposal_product_peserta ){  
                     if(peserta_rider.getLsbs_id()==Rider.RIDER_LADIES_MED_EXPENSE){                                         
                         ParticipantVO participantVO = new ParticipantVO( peserta_rider.getNama_peserta(), peserta_rider.getTgl_lahir(), peserta_rider.getUsia() );
                         participantVO.setLsre_id(peserta_rider.getLsre_id());
                         participantVOList.add( participantVO );                                
                     } 
                 }
                 cepr01030111Form.setParticipantVOList(participantVOList);
             }       
                         
            // Ladies Medical Expense Inner Limit
             if(product_rider.getLsbs_id()==Rider.RIDER_LADIES_MED_EXPENSE_INNER_LIMIT){
                 cepr01030103Form.setLadiesMedExpenseInnerLimitFlag(CommonConst.CHECKED_TRUE);
                 cepr01030103Form.setLadiesMedExpenseInnerLimitCd(product_rider.getLsdbs_number());
             
                //Peserta Tambahan
                 ArrayList<MstProposalProductPeserta> mst_proposal_product_peserta = dataProposal.getMst_proposal_product_peserta();
                 ArrayList<ParticipantVO> participantVOList = new ArrayList<ParticipantVO>();
                 for(MstProposalProductPeserta peserta_rider : mst_proposal_product_peserta ){  
                     if(peserta_rider.getLsbs_id()==Rider.RIDER_LADIES_MED_EXPENSE_INNER_LIMIT){                                         
                         ParticipantVO participantVO = new ParticipantVO( peserta_rider.getNama_peserta(), peserta_rider.getTgl_lahir(), peserta_rider.getUsia() );
                         participantVO.setLsre_id(peserta_rider.getLsre_id());
                         participantVOList.add( participantVO );                                
                     } 
                 }               
                 cepr01030112Form.setParticipantVOList(participantVOList);
             }
                        
              // Scholarship
             if(product_rider.getLsbs_id()==Rider.RIDER_SCHOLARSHIP){
                 cepr01030103Form.setScholarshipFlag(CommonConst.CHECKED_TRUE);  
                 if(product_rider.getLsdbs_number()==1 ){
                     cepr01030103Form.setScholarshipChooseCd("22");
                 }else{
                     cepr01030103Form.setScholarshipChooseCd("25");
                 }
                 cepr01030103Form.setScholarshipCd(product_rider.getUp().intValue());
             }
             
             // Baby
             if(product_rider.getLsbs_id()==Rider.RIDER_BABY){            	 
            	 if( cepr01030102Form.getLeftPartOfBusinessCd()==208 ){
            		 if( mst_data_proposal.getFlag_tt_calon_bayi()==1 ){
            			 cepr01030101Form.setBabyFlag(CommonConst.CHECKED_TRUE);
            			 cepr01030102Form.setBabyFlag(CommonConst.CHECKED_TRUE);
                		 cepr01030102Form.setBabyCd(String.valueOf(product_rider.getLsdbs_number()-3));
                		 cepr01030102Form.setBabyChooseCd(String.valueOf(product_rider.getBulan_ke()));             				 
            			}else{
            				 cepr01030101Form.setBabyFlag(CommonConst.CHECKED_FALSE);
                			 cepr01030102Form.setBabyFlag(CommonConst.CHECKED_FALSE);            				
            			}
            	 }else{
            		 cepr01030103Form.setBabyFlag(CommonConst.CHECKED_TRUE);
                     cepr01030103Form.setBabyCd(String.valueOf(product_rider.getLsdbs_number()));
                     cepr01030103Form.setBabyChooseCd(String.valueOf(product_rider.getBulan_ke()));            		 
            	 }            	 
             }
             // ESCI 99
             if(product_rider.getLsbs_id()==Rider.RIDER_EARLY_STAGE_CI99){
                 cepr01030103Form.setEarlyStageCi99Flag(CommonConst.CHECKED_TRUE);
                 if(product_rider.getLsdbs_number()==1){
                     cepr01030101Form.setInsuredSexCd("L");
                 }else{
                     cepr01030101Form.setInsuredSexCd("P");
                 }
//                 cepr01030103Form.setEsci99ChooseCd(String.valueOf(product_rider.getPersen_up()));
                 cepr01030103Form.setEsci99RiderAmount(product_rider.getUp());
             }    
             
             // SMiLe Medical (+)
             if(product_rider.getLsbs_id()==Rider.RIDER_MEDICAL_PLUS_UTAMA){
            	   cepr01030103Form.setMedicalPlusFlag(CommonConst.CHECKED_TRUE);
            	   cepr01030103Form.setMedicalPlusChooseCd( String.valueOf(product_rider.getLsdbs_number()) );

            	   //Peserta Tambahan
                   ArrayList<MstProposalProductPeserta> mst_proposal_product_peserta = dataProposal.getMst_proposal_product_peserta();
                   ArrayList<ParticipantVO> participantVOList = new ArrayList<ParticipantVO>();
                   for(MstProposalProductPeserta peserta_rider : mst_proposal_product_peserta ){  
                       if(peserta_rider.getLsbs_id()==Rider.RIDER_MEDICAL_PLUS_UTAMA){                                          
                           ParticipantVO participantVO = new ParticipantVO( peserta_rider.getNama_peserta(), peserta_rider.getTgl_lahir(), peserta_rider.getUsia() );
                           participantVO.setLsre_id(peserta_rider.getLsre_id());
                           participantVO.setSexCd(peserta_rider.getSex().toString()); //IGA 19112020 Khusus untuk medical plus menggunakan validasi gender
                           participantVOList.add( participantVO );                                
                       } 
                   }
                   cepr01030113Form.setParticipantVOList(participantVOList);
             }             
                 
          	 if(product_rider.getLsbs_id()==Rider.RIDER_MEDICAL_PLUS_TAMBAHAN && (product_rider.getLsdbs_number()==2 || product_rider.getLsdbs_number()==3 || product_rider.getLsdbs_number()==4) ){
            	   cepr01030103Form.setMedicalPlusRjFlag(CommonConst.CHECKED_TRUE);
          	 }
          	 
        	 if(product_rider.getLsbs_id()==Rider.RIDER_MEDICAL_PLUS_TAMBAHAN && (product_rider.getLsdbs_number()==22 || product_rider.getLsdbs_number()==23 || product_rider.getLsdbs_number()==24) ){
          	   cepr01030103Form.setMedicalPlusRgFlag(CommonConst.CHECKED_TRUE);
        	 }
        
        	 if(product_rider.getLsbs_id()==Rider.RIDER_MEDICAL_PLUS_TAMBAHAN && (product_rider.getLsdbs_number()==42 || product_rider.getLsdbs_number()==43 || product_rider.getLsdbs_number()==44) ){
            	   cepr01030103Form.setMedicalPlusRbFlag(CommonConst.CHECKED_TRUE);
          	 }
        	 
        	 if(product_rider.getLsbs_id()==Rider.RIDER_MEDICAL_PLUS_TAMBAHAN && (product_rider.getLsdbs_number()==62 || product_rider.getLsdbs_number()==63 || product_rider.getLsdbs_number()==64) ){
          	   cepr01030103Form.setMedicalPlusPkFlag(CommonConst.CHECKED_TRUE);
        	 }
        	 
        	  // Eka Sehat (EXTRA)
             if(product_rider.getLsbs_id()==Rider.RIDER_EKA_SEHAT_EXTRA){
                 cepr01030103Form.setEkaSehatExtraFlag(CommonConst.CHECKED_TRUE);
                 cepr01030103Form.setEkaSehatExtraCd(product_rider.getLsdbs_number()-70);
                 
                 //Peserta Tambahan
                 ArrayList<MstProposalProductPeserta> mst_proposal_product_peserta = dataProposal.getMst_proposal_product_peserta();
                 ArrayList<ParticipantVO> participantVOList = new ArrayList<ParticipantVO>();
                 for(MstProposalProductPeserta peserta_rider : mst_proposal_product_peserta ){  
                     if(peserta_rider.getLsbs_id()==Rider.RIDER_EKA_SEHAT_EXTRA){                                          
                         ParticipantVO participantVO = new ParticipantVO( peserta_rider.getNama_peserta(), peserta_rider.getTgl_lahir(), peserta_rider.getUsia() );
                         participantVO.setLsre_id(peserta_rider.getLsre_id());
                         participantVOList.add( participantVO );                                
                     } 
                 }
                 cepr01030114Form.setParticipantVOList(participantVOList);
             }
         }
         
         // Default rider list
         if(Hardcode.CUR_IDR_CD.equals(mst_proposal_product.getLku_id())) {
             cepr01030103Form.setHcpList( LookupList.getHcpIdrList() );
             cepr01030103Form.setHcpFamilyList( LookupList.getHcpFamilyIdrList() );
             cepr01030103Form.setHcpLadiesList( LookupList.getHcpLadiesIdrList() );
             cepr01030103Form.setLadiesInsList( LookupList.getLadiesInsList() );
             cepr01030103Form.setScholarshipList( LookupList.getScholarshipList() );
             cepr01030103Form.setScholarshipChooseList( LookupList.getScholarshipChooseList() );
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
             }else{
                 cepr01030103Form.setEkaSehatList( LookupList.getEkaSehatIdrList() );
                 cepr01030103Form.setCiChooseList( LookupList.getCiChooseList() );
                 cepr01030103Form.setLadiesInsChooseList( LookupList.getLadiesInsChooseList() );
             }
             cepr01030103Form.setEkaSehatInnerLimitList( LookupList.getEkaSehatInnerLimitIdrList() );
             cepr01030103Form.setLadiesMedExpenseList( LookupList.getLadiesMedExpenseIdrList() );
             cepr01030103Form.setLadiesMedExpenseInnerLimitList( LookupList.getLadiesMedExpenseInnerLimitIdrList() );
             cepr01030103Form.setWaiverTpdCiChooseList( LookupList.getWaiverTpdChooseList() );
             /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
             cepr01030103Form.setTerm5575RiderChooseList(LookupList.getTerm5575RiderChooseList());
             
             cepr01030103Form.setBabyList(LookupList.getBabyList());
             cepr01030103Form.setBabyChooseList(LookupList.getBabyChooseList());
             cepr01030103Form.setEsci99ChooseList( LookupList.getEsci99ChooseList() );
             cepr01030103Form.setMedicalPlusChooseList( LookupList.getMedicalPlusChooseList() );
//           cepr01030103Form.setEkaSehatProviderList( LookupList.getEkaSehatProviderList() );
             
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
         } else if(Hardcode.CUR_USD_CD.equals(mst_proposal_product.getLku_id())) {
             cepr01030103Form.setHcpList( LookupList.getHcpUsdList() );
             cepr01030103Form.setHcpFamilyList( LookupList.getHcpFamilyUsdList() );
             cepr01030103Form.setHcpLadiesList( LookupList.getHcpLadiesUsdList() );
             cepr01030103Form.setLadiesInsList( LookupList.getLadiesInsList() );
             cepr01030103Form.setScholarshipList( LookupList.getScholarshipList() );
             cepr01030103Form.setScholarshipChooseList( LookupList.getScholarshipChooseList() );
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
             }else{
                 cepr01030103Form.setCiChooseList( LookupList.getCiChooseList() );
                 cepr01030103Form.setLadiesInsChooseList( LookupList.getLadiesInsChooseList() );
             }
             cepr01030103Form.setWaiverTpdCiChooseList( LookupList.getWaiverTpdChooseList() );
//             cepr01030103Form.setEkaSehatProviderList( LookupList.getEkaSehatProviderList() );
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
         } else {
             cepr01030103Form.setHcpList( LookupList.getEmptyOptionVOList() );
         }
         
        //PACKET
        ArrayList<MstProposalProductPacket> mst_proposal_product_packet = dataProposal.getMst_proposal_product_packet();
        if( mst_proposal_product_packet.size() > 0){
            for(MstProposalProductPacket product_packet : mst_proposal_product_packet ){
                 if(product_packet.getFlag_packet() >0 && product_packet.getFlag_packet() < 6 ){
                     cepr01030102Form.setSmileLadiesPackageCd(product_packet.getFlag_packet()-1);                                    
                 }
                 else if(product_packet.getFlag_packet() >=7 && product_packet.getFlag_packet() < 12 ){
                     cepr01030102Form.setSmilePensionPackageCd(product_packet.getFlag_packet()-5);                                   
                 }
                 else if(product_packet.getFlag_packet() >=18 && product_packet.getFlag_packet() < 21 ){
                     cepr01030102Form.setSecondPackageCd(product_packet.getFlag_packet()-16);                                    
                 }
                 else if(product_packet.getFlag_packet() >=24 && product_packet.getFlag_packet() < 27 ){
                     cepr01030102Form.setProtectorPackageCd(product_packet.getFlag_packet()-22);                                     
                 }
                 
                 
                 
            }
        }
         
         if( cepr01030102Form.getSmileLadiesPackageCd() != null && cepr01030102Form.getSmileLadiesPackageCd() > 1 ){
             cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD } ) );
         } else {
             cepr01030102Form.setCurrencyList( comboLookupBusiness.selectCurrencyOptionVOList( false, new String[]{ Hardcode.CUR_IDR_CD, Hardcode.CUR_USD_CD } ) );
         }
         
         if(StringUtil.isEmpty(cepr01030106Form.getParticipantVOList())) {
             ArrayList<ParticipantVO> participantVOList = new ArrayList<ParticipantVO>();
             for(int i = 0; i < 4; i++) {
                 participantVOList.add(new ParticipantVO());
             }
             cepr01030106Form.setParticipantVOList(participantVOList);
         }
         
         if(StringUtil.isEmpty(cepr01030110Form.getParticipantVOList())) {
             ArrayList<ParticipantVO> participantVOList = new ArrayList<ParticipantVO>();
             for(int i = 0; i < 4; i++) {
                 participantVOList.add(new ParticipantVO());
             }
             cepr01030110Form.setParticipantVOList(participantVOList);
         }
         
         if(StringUtil.isEmpty(cepr01030107Form.getParticipantVOList())) {
             ArrayList<ParticipantVO> participantVOList = new ArrayList<ParticipantVO>();
             for(int i = 0; i < 4; i++) {
                 participantVOList.add(new ParticipantVO());
             }
             cepr01030107Form.setParticipantVOList(participantVOList);
         }
         
         if(StringUtil.isEmpty(cepr01030109Form.getParticipantVOList())) {
             ArrayList<ParticipantVO> participantVOList = new ArrayList<ParticipantVO>();
             for(int i = 0; i < 4; i++) {
                 participantVOList.add(new ParticipantVO());
             }
             cepr01030109Form.setParticipantVOList(participantVOList);
         }
         
         if(StringUtil.isEmpty(cepr01030108Form.getParticipantVOList())) {
             ArrayList<ParticipantVO> participantVOList = new ArrayList<ParticipantVO>();
             for(int i = 0; i < 4; i++) {
                 participantVOList.add(new ParticipantVO());
             }
             cepr01030108Form.setParticipantVOList(participantVOList);
         }
         
         if(StringUtil.isEmpty(cepr01030111Form.getParticipantVOList())) {
             ArrayList<ParticipantVO> participantVOList = new ArrayList<ParticipantVO>();
             for(int i = 0; i < 4; i++) {
                 participantVOList.add(new ParticipantVO());
             }
             cepr01030111Form.setParticipantVOList(participantVOList);
         }
         
         if(StringUtil.isEmpty(cepr01030112Form.getParticipantVOList())) {
             ArrayList<ParticipantVO> participantVOList = new ArrayList<ParticipantVO>();
             for(int i = 0; i < 4; i++) {
                 participantVOList.add(new ParticipantVO());
             }
             cepr01030112Form.setParticipantVOList(participantVOList);
         }
         
         if(StringUtil.isEmpty(cepr01030113Form.getParticipantVOList())) {
             ArrayList<ParticipantVO> participantVOList = new ArrayList<ParticipantVO>();
             for(int i = 0; i < 4; i++) {
                 participantVOList.add(new ParticipantVO());
             }
             cepr01030113Form.setParticipantVOList(participantVOList);
         }
         
         if(StringUtil.isEmpty(cepr01030114Form.getParticipantVOList())) {
             ArrayList<ParticipantVO> participantVOList = new ArrayList<ParticipantVO>();
             for(int i = 0; i < 4; i++) {
                 participantVOList.add(new ParticipantVO());
             }
             cepr01030114Form.setParticipantVOList(participantVOList);
         }
         
         cepr01030000HoldingForm.setCepr01030101Form(cepr01030101Form);
         cepr01030000HoldingForm.setCepr01030102Form(cepr01030102Form);
         cepr01030000HoldingForm.setCepr01030103Form(cepr01030103Form);
         cepr01030000HoldingForm.setCepr01030104Form(cepr01030104Form);
         cepr01030000HoldingForm.setCepr01030106Form(cepr01030106Form);
         cepr01030000HoldingForm.setCepr01030107Form(cepr01030107Form);
         cepr01030000HoldingForm.setCepr01030108Form(cepr01030108Form);
         cepr01030000HoldingForm.setCepr01030109Form(cepr01030109Form);
         cepr01030000HoldingForm.setCepr01030110Form(cepr01030110Form);
         cepr01030000HoldingForm.setCepr01030111Form(cepr01030111Form);
         cepr01030000HoldingForm.setCepr01030112Form(cepr01030112Form);
         cepr01030000HoldingForm.setCepr01030113Form(cepr01030113Form);
         cepr01030000HoldingForm.setCepr01030114Form(cepr01030114Form);
         cepr01030000HoldingForm.setCepr01031001Form(cepr01031001Form);
         
         cepr01030101Form.setAssurancePlanCd1(eproposalManager.selectLabelProductName(mst_proposal_product.getLsbs_id(), mst_proposal_product.getLsdbs_number()));
         cepr01030101Form.setAssurancePlanCd2("");
         cepr01030101Form.setAssurancePlanCd3("");
         cepr01030101Form.setAssurancePlanCd4("");
         cepr01030102Form.setAssurancePlanList(cepr01030102Business.selectSpecificLstBusinessOptionVOList(cepr01030000HoldingForm));
         
     return cepr01030000HoldingForm;    
    }
    
    public HashMap<String, Object> getPdfProposal(Cepr01030000HoldingForm holdingForm) throws ServletException, IOException {
        HashMap<String, Object> map = null;
        Integer lsbsId = Integer.valueOf(holdingForm.getCepr01030102Form().getLeftPartOfBusinessCd());
        Integer lsdbsNumber = Integer.valueOf(holdingForm.getCepr01030102Form().getRightPartOfBusinessCd());
        
        if(lsbsId == Hardcode.PRODUK_CERDAS && lsdbsNumber >= 22 && lsdbsNumber <= 24) {
            // SIMPOL
            Cepr01040211DownloadController downloadController = new Cepr01040211DownloadController();
            downloadController.setEproposalManager(eproposalManager);
            downloadController.setEditorUtil(editorUtil);
            map = downloadController.controller(holdingForm);
        } else if(lsbsId == Hardcode.PRODUK_SIMAS_MAGNA_LINK ) {
            // MAGNA LINK
            Cepr01040221DownloadController downloadController = new Cepr01040221DownloadController();
            downloadController.setEproposalManager(eproposalManager);
            downloadController.setEditorUtil(editorUtil);
            map = downloadController.controller(holdingForm);
        } else if(lsbsId == Hardcode.PRODUK_PLATINUM_LINK ) {
            // PRIME LINK
            Cepr01040208DownloadController downloadController = new Cepr01040208DownloadController();
            downloadController.setEproposalManager(eproposalManager);
            downloadController.setEditorUtil(editorUtil);
            map = downloadController.controller(holdingForm);
        } else if(lsbsId == Hardcode.PRODUK_SIMAS_MAGNA_LINK_SYARIAH && lsdbsNumber == 1 ) {
            // MAGNA LINK SYARIAH
            Cepr01040224DownloadController downloadController = new Cepr01040224DownloadController();
            downloadController.setEproposalManager(eproposalManager);
            downloadController.setEditorUtil(editorUtil);
            map = downloadController.controller(holdingForm);
        } else if(lsbsId == Hardcode.PRODUK_SIMAS_PRIME_LINK_SYARIAH ) {
            // PRIME LINK SYARIAH
            Cepr01040223DownloadController downloadController = new Cepr01040223DownloadController();
            downloadController.setEproposalManager(eproposalManager);
            downloadController.setEditorUtil(editorUtil);
            map = downloadController.controller(holdingForm);
        } else if(lsbsId == Hardcode.PRODUK_SMILE_LINK_99 ) {
            // OPTIMA LINK
            Cepr01040219DownloadController downloadController = new Cepr01040219DownloadController();
            downloadController.setEproposalManager(eproposalManager);
            downloadController.setEditorUtil(editorUtil);
            map = downloadController.controller(holdingForm);
        } else if(lsbsId == Hardcode.PRODUK_VIP_EDU_PLAN ) {
            // SIMAS KID
            Cepr01040139DownloadController downloadController = new Cepr01040139DownloadController();
            downloadController.setEproposalManager(eproposalManager);
            downloadController.setEditorUtil(editorUtil);
            map = downloadController.controller(holdingForm);
        } else if(lsbsId == Hardcode.SMART_LIFE_CARE_PLUS ) {
            // SMART LIFE CARE PLUS
            Cepr01040150DownloadController downloadController = new Cepr01040150DownloadController();
            downloadController.setEproposalManager(eproposalManager);
            downloadController.setEditorUtil(editorUtil);
            map = downloadController.controller(holdingForm);
        }else if(lsbsId == Hardcode.PRODUK_SMILE_LINK_PLUS ) {
            // JEMPOL LINK/SMiLe LINK PLUS
            Cepr01040228DownloadController downloadController = new Cepr01040228DownloadController();
            downloadController.setEproposalManager(eproposalManager);
            downloadController.setEditorUtil(editorUtil);
            map = downloadController.controller(holdingForm);
        }else if(lsbsId == Hardcode.PRODUK_SMILE_LINK_PRO_SYARIAH ) {
            // SMile LINK PRO SYARIAH
            Cepr01040229DownloadController downloadController = new Cepr01040229DownloadController();
            downloadController.setEproposalManager(eproposalManager);
            downloadController.setEditorUtil(editorUtil);
            map = downloadController.controller(holdingForm);
        }else if(lsbsId == Hardcode.PRODUK_SMILE_LIFE_SYARIAH ) {
            Cepr01040151DownloadController downloadController = new Cepr01040151DownloadController();
            downloadController.setEproposalManager(eproposalManager);
            downloadController.setEditorUtil(editorUtil);
            map = downloadController.controller(holdingForm);
        }else if(lsbsId == Hardcode.PRODUK_SIMAS_KID_SYARIAH ) {
            Cepr01040149DownloadController downloadController = new Cepr01040149DownloadController();
            downloadController.setEproposalManager(eproposalManager);
            downloadController.setEditorUtil(editorUtil);
            map = downloadController.controller(holdingForm);
        }else if(lsbsId == Hardcode.PRODUK_SMILE_LINK_PRO_100 ) {
        	Cepr01040225DownloadController downloadController = new Cepr01040225DownloadController();
            downloadController.setEproposalManager(eproposalManager);
            downloadController.setEditorUtil(editorUtil);
            map = downloadController.controller(holdingForm);
        }else if(lsbsId == Hardcode.PRODUK_SMILE_LINK_88 || lsbsId == Hardcode.PRODUK_SMILE_LINK_88_AS) { //PROD 118-3&4 IGA
        	Cepr01040205DownloadController downloadController = new Cepr01040205DownloadController();
            downloadController.setEproposalManager(eproposalManager);
            downloadController.setEditorUtil(editorUtil);
            map = downloadController.controller(holdingForm);
        }else if(lsbsId == Hardcode.PRODUK_SMiLe_INCOME_PROTECTION_XTRA ) {/**NCR/2021/02/052	SMiLe Income Protection X-Tra**/
            Cepr01040153DownloadController downloadController = new Cepr01040153DownloadController();
            downloadController.setEproposalManager(eproposalManager);
            downloadController.setEditorUtil(editorUtil);
            map = downloadController.controller(holdingForm);
        }
        
        
        
        return map;
    }
    
    public List<Map<String, Object>> hasilCariAdminBC(String cariNamaAdminBC, String cariKodeAdminBC, String jn_bank){
    	return eproposalManager.selectNamaAdminBCList( cariNamaAdminBC, cariKodeAdminBC, jn_bank );
    }
   
    
}