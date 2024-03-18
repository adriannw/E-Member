package id.co.sinarmaslife.eproposal.business;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: Cepr00000000RiderBusiness
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Dec 19, 2007 3:12:11 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import id.co.sinarmaslife.eproposal.common.constant.CommonConst;
import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.common.pb.PbConverter;
import id.co.sinarmaslife.eproposal.common.util.Cepr00000000EditorUtil;
import id.co.sinarmaslife.eproposal.model.pb.Istr_prop;
import id.co.sinarmaslife.eproposal.model.pb.Mst_proposal;
import id.co.sinarmaslife.eproposal.model.pb.N_riders;
import id.co.sinarmaslife.eproposal.model.vo.ParticipantVO;
import id.co.sinarmaslife.eproposal.product.product01040211.Cepr01040211Mapper;
import id.co.sinarmaslife.eproposal.product.product01040219.Cepr01040219Mapper;
import id.co.sinarmaslife.eproposal.service.EproposalManager;
import id.co.sinarmaslife.eproposal.web.Cepr01030000HoldingForm;
import id.co.sinarmaslife.eproposal.web.Cepr01030103Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030106Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030107Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030108Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030110Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030111Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030112Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030113Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030114Form;
import id.co.sinarmaslife.standard.util.ArrUtil;
import id.co.sinarmaslife.standard.util.LazyConverter;
import id.co.sinarmaslife.standard.util.MappingUtil;
import id.co.sinarmaslife.standard.util.MathUtil;
import id.co.sinarmaslife.eproposal.web.Cepr01030109Form;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.jasperreports.JasperReportsUtils;

public class Cepr00000000RiderBusiness extends Cepr00000000CommonUsedBusiness
{
    protected final Log logger = LogFactory.getLog( getClass() );
    protected Cepr01030103Form cepr01030103Form;
    protected Cepr01030109Form cepr01030109Form;
    protected Cepr01030110Form cepr01030110Form;
    protected Cepr01030111Form cepr01030111Form;
    protected Cepr01030112Form cepr01030112Form;
    protected Cepr01030113Form cepr01030113Form;
    protected Cepr01030114Form cepr01030114Form;
    protected Cepr01030106Form cepr01030106Form;
    protected Cepr01030107Form cepr01030107Form;   
    protected Cepr01030108Form cepr01030108Form;
    protected Istr_prop istr_prop;
    
    public Cepr00000000RiderBusiness( EproposalManager eproposalManager, Cepr00000000EditorUtil editorUtil, Object command )
    {
        super( eproposalManager, editorUtil, command );
        istr_prop = PbConverter.get_istr_prop( command, 0 );
        
        logger.info( "*-*-*-* Cepr00000000RiderBusiness constructor is called ..." );
        this.cepr01030103Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030103Form();
        this.cepr01030106Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030106Form();
        this.cepr01030109Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030109Form();
        this.cepr01030110Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030110Form();
        this.cepr01030111Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030111Form();
        this.cepr01030112Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030112Form();
        this.cepr01030113Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030113Form();
        this.cepr01030114Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030114Form();
        this.cepr01030107Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030107Form();        
        this.cepr01030108Form = ( ( Cepr01030000HoldingForm ) command ).getCepr01030108Form();
    }

    public static List<ParticipantVO> removeEmptyParticipantVOFromTheList( List<ParticipantVO> list )
    {
        List<ParticipantVO> result = new ArrayList<ParticipantVO>();
        String name;
        for( ParticipantVO vo : list )
        {
            name = vo.getName();
            name = name == null? "" : name.trim();
            if( !"".equals( name ) )
            {
                result.add( vo );
            }
        }
        return result;
    }

    public List<Map<String, Object>> getRiderPaList()
    {
        logger.info( "*-*-*-* Cepr00000000RiderBusiness.getRiderPaList" );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPaFlag() ) )
        {
            int accidentDeathBenefit = 0x000000001;
            int disabilityBenefit = 0x000000010;
            int hospitalBenefit = 0x000000100;
            int benefit = 0;
            Integer paRiskCd = cepr01030103Form.getPaRiskCd();
            switch( paRiskCd )
            {
                case Hardcode.PA_RISK_A:
                    benefit = accidentDeathBenefit;
                    break;
                case Hardcode.PA_RISK_AB:
                    benefit = accidentDeathBenefit + disabilityBenefit;
                    break;
                case Hardcode.PA_RISK_ABD:
                    benefit = accidentDeathBenefit + disabilityBenefit + hospitalBenefit;
                    break;
                default:
                    break;
            }
            double maks = 2000000000;
            double maksAccident = 200000000;
            if( cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD ) ){
            	maks = 200000;
            	maksAccident = 20000;
            }
            double totalSumInsured = cepr01030102Form.getTotalSumInsured().doubleValue();
            double quantity = cepr01030103Form.getPaUnitQtyCd().doubleValue();
            double benefitAmount = MathUtil.min( totalSumInsured * quantity, maks );
            double maxAccidentBenefit = MathUtil.min((0.1 * totalSumInsured * quantity), maksAccident);

            if( ( benefit & accidentDeathBenefit ) == accidentDeathBenefit )
            {
                logger.info( "*-*-*-* have accident death benefit" );
                params = new HashMap<String, Object>();
                params.put( "subject", "1.Manfaat meninggal dunia karena kecelakaan" );
                params.put( "descr", toMoneyWithCurrencyCd( benefitAmount ) );
                params.put( "summary", "1. Tertanggung meninggal karena kecelakaan diberikan 100% Uang Pertanggungan (Maks. Rp. 2.000.000.000,- / USD. 200.000,-)." );
                result.add( params );
            }

            if( ( benefit & disabilityBenefit ) == disabilityBenefit )
            {
                logger.info( "*-*-*-* have disability benefit" );
                params = new HashMap<String, Object>();
                params.put( "subject", "2a. Cacat tetap seluruh anggota badan" );
                params.put( "descr", toMoneyWithCurrencyCd( benefitAmount ) );
                params.put( "summary", "2. - Tertanggung menderita Cacat Tetap seluruh anggota badan diberikan 100% Uang Pertanggungan (Maks. Rp. 2.000.000.000,-/USD. 200.000,-)." );
                result.add( params );
                params = new HashMap<String, Object>();
                params.put( "subject", "  b. Cacat tetap sebagian anggota badan" );
                params.put( "descr", "Sesuai tabel cacat dan berdasarkan % UP" );
                params.put( "summary", "    - Tertanggung menderita Cacat Tetap sebagian anggota badan diberikan prosentase tertentu dari UP berdasarkan Tabel Cacat." );
                result.add( params );
            }

            if( ( benefit & hospitalBenefit ) == hospitalBenefit )
            {
                logger.info( "*-*-*-* have hospital benefit" );
                params = new HashMap<String, Object>();
                params.put( "subject", "3. Perawatan di Rumah Sakit akibat kecelakaan" );
                params.put( "descr", "Maks " + toMoneyWithCurrencyCd( maxAccidentBenefit ) + " per kejadian" );
                params.put( "summary", "3. Tertanggung menjalani pengobatan/perawatan di Rumah Sakit, maka akan dibayarkan manfaat sebesar biaya pengobatan/perawatan" );
                result.add( params );
                params = new HashMap<String, Object>();
                params.put( "subject", "" );
                params.put( "descr", "" );
                params.put( "summary", "    (Maks. 10% UP per Kejadian, dan 100% UP dalam 1 tahun Polis)." );
                result.add( params );
            }
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderPaSyariahList()
    {
        logger.info( "*-*-*-* Cepr00000000RiderBusiness.getRiderPaList" );
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPaFlag() ) )
        {
            int accidentDeathBenefit = 0x000000001;
            int disabilityBenefit = 0x000000010;
            int hospitalBenefit = 0x000000100;
            int benefit = 0;
            Integer paRiskCd = cepr01030103Form.getPaRiskCd();
            switch( paRiskCd )
            {
                case Hardcode.PA_RISK_A:
                    benefit = accidentDeathBenefit;
                    break;
                case Hardcode.PA_RISK_AB:
                    benefit = accidentDeathBenefit + disabilityBenefit;
                    break;
                case Hardcode.PA_RISK_ABD:
                    benefit = accidentDeathBenefit + disabilityBenefit + hospitalBenefit;
                    break;
                default:
                    break;
            }

            double totalSumInsured = cepr01030102Form.getTotalSumInsured().doubleValue();
            double quantity = cepr01030103Form.getPaUnitQtyCd().doubleValue();
            double benefitAmount = MathUtil.min( totalSumInsured * quantity, 2000000000 );
            double maxAccidentBenefit = MathUtil.min((0.1 * totalSumInsured), 200000000.0);

            if( ( benefit & accidentDeathBenefit ) == accidentDeathBenefit )
            {
                params = new HashMap<String, Object>();
                params.put( "subject", "Manfaat meninggal dunia karena kecelakaan" );
                params.put( "descr", toMoneyWithCurrencyCd( benefitAmount ) );
                params.put( "summary", "1. Peserta meninggal karena kecelakaan diberikan 100% Manfaat Asuransi Syariah (Maks. Rp. 2.000.000.000,-)." );
                result.add( params );
            }

            if( ( benefit & disabilityBenefit ) == disabilityBenefit )
            {
                params = new HashMap<String, Object>();
                params.put( "subject", "2a. Cacat tetap seluruh anggota badan" );
                params.put( "descr", toMoneyWithCurrencyCd( benefitAmount ) );
                params.put( "summary", "2. - Peserta menderita Cacat Tetap seluruh anggota badan diberikan 100% Manfaat Asuransi Syariah (Maks. Rp. 2.000.000.000,-)." );
                result.add( params );
                params = new HashMap<String, Object>();
                params.put( "subject", "  b. Cacat tetap sebagian anggota badan" );
                params.put( "descr", "Sesuai tabel cacat dan berdasarkan % UP" );
                params.put( "summary", "    - Peserta menderita Cacat Tetap sebagian anggota badan diberikan prosentase tertentu dari Manfaat Asuransi Syariah berdasarkan Tabel Cacat." );
                result.add( params );
            }

            if( ( benefit & hospitalBenefit ) == hospitalBenefit )
            {
                params = new HashMap<String, Object>();
                params.put( "subject", "3. Perawatan di Rumah Sakit akibat kecelakaan" );
                params.put( "descr", "Maks " + toMoneyWithCurrencyCd( maxAccidentBenefit ) + " per kejadian" );
                params.put( "summary", "3. Peserta menjalani pengobatan/perawatan di Rumah Sakit, maka akan dibayarkan manfaat sebesar biaya pengobatan/perawatan" );
                result.add( params );
                params = new HashMap<String, Object>();
                params.put( "subject", "" );
                params.put( "descr", "" );
                params.put( "summary", "    (Maks. 10% Manfaat Asuransi Syariah per Kejadian, dan 100% Manfaat Asuransi Syariah dalam 1 tahun Polis)." );
                result.add( params );
            }
        }

        return result;
    }

    public String getRiderPaRisk()
    {
        logger.info( "*-*-*-* Cepr00000000RiderBusiness.getRiderPaRisk" );
        String result = "";
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPaFlag() ) )
        {
            Integer paRiskCd = cepr01030103Form.getPaRiskCd();
            switch( paRiskCd )
            {
                case Hardcode.PA_RISK_A:
                    result = "Resiko A";
                    break;
                case Hardcode.PA_RISK_AB:
                    result = "Resiko A+B";
                    break;
                case Hardcode.PA_RISK_ABD:
                    result = "Resiko A+B+D";
                    break;
                default:
                    break;
            }
        }

        return result;
    }

    public List<Map<String, Object>> getRiderHcpList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFlag() ) )
        {
            double benefitAmount = 0;
            // todo
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                benefitAmount = cepr01030103Form.getHcpCd() * 100000;
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                benefitAmount = ( cepr01030103Form.getHcpCd() -  10 ) * 10 ;
            }

            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Jenis Santunan" );
            params.put( "descr", MappingUtil.getLabel( cepr01030103Form.getHcpList(), cepr01030103Form.getHcpCd() )  );
            params.put( "term", "" );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap krn sakit" );
            params.put( "descr", editorUtil.convertToRoundedNoDigit( new BigDecimal(benefitAmount) ) );
            params.put( "term", "Maks 100 hari per satu tahun polis*)" );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap di ruang ICU" );
            params.put( "descr", editorUtil.convertToRoundedNoDigit( new BigDecimal(benefitAmount * 2) ) );
            params.put( "term", "Maks 30 hari per satu tahun polis" );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap karena kecelakaan" );
            params.put( "descr", editorUtil.convertToRoundedNoDigit( new BigDecimal(benefitAmount * 2) ) );
            params.put( "term", "" );
            result.add( params );
        }
        
        return result;
    }
    
    public List<Map<String, Object>> getRiderHcpSyariahList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFlag() ) )
        {
            double benefitAmount = 0;
            // todo
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                benefitAmount = cepr01030103Form.getHcpCd() * 100000;
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                benefitAmount = cepr01030103Form.getHcpCd() * 10;
            }

            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Jenis Santunan" );
            params.put( "descr", MappingUtil.getLabel( cepr01030103Form.getHcpList(), cepr01030103Form.getHcpCd() )  );
            params.put( "term", "" );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap krn sakit" );
            params.put( "descr", editorUtil.convertToString( benefitAmount ) );
            params.put( "term", "Maks 100 hari per satu tahun polis" );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap di ruang ICU" );
            params.put( "descr", editorUtil.convertToString( benefitAmount * 2 ) );
            params.put( "term", "Maks 30 hari per satu tahun polis" );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap karena kecelakaan" );
            params.put( "descr", editorUtil.convertToString( benefitAmount * 2 ) );
            params.put( "term", "" );
            result.add( params );
        }
        
        return result;
    }
    
    public List<Map<String, Object>> getRiderLadiesInsSummaryList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesInsFlag() ) )
        {
            Map<String, Object> params;
            if( cepr01030103Form.getLadiesInsCd().equals( 1 ) )// paket a
            {
	            params = new HashMap<String, Object>();
	            params.put( "subject", "a.1." );
	            params.put( "descr", "Jika Tertanggung terdiagnosa positif penyakit Kanker Serviks yang dapat dibuktikan dengan hasil pemeriksaan Patologi Anatomi, maka akan dibayarkan manfaat Asuransi sebesar 20% Uang pertanggungan sebanyak 1x(satu kali), dengan tetap memperhatikan maksimum total manfaat Asuransi pada poin b." );
	            result.add( params );
	            
	            params = new HashMap<String, Object>();
	            params.put( "subject", "a.2." );
	            params.put( "descr", "Apabila Tertanggung terdiagnosa Kanker Serviks stadium IV maka akan dibayarkan manfaat Asuransi sebesar 100% Uang Pertanggungan setelah dikurangi dengan manfaat pada poin a.1 yang telah dibayarkan dan selanjutnya Asuransi berakhir." );
	            result.add( params );
	            
	            params = new HashMap<String, Object>();
	            params.put( "subject", "b." );
	            params.put( "descr", "Maksimum total manfaat Asuransi untuk Kanker payudara di bawah Stadium IV dan/atau Kanker Serviks di bawah Stadium IV yang dibayarkan untuk Tertanggung yang sama adalah Rp 400.000.000,-/US$ 40.000,-." );
	            result.add( params );
	            
	            params = new HashMap<String, Object>();
	            params.put( "subject", "c." );
	            params.put( "descr", "Maksimum total manfaat Asuransi yang dibayarkan untuk Tertanggung yang sama adalah Rp. 2000.000.000,-/US$. 200.000,-." );
	            result.add( params );
            }
            
            if( cepr01030103Form.getLadiesInsCd().equals( 2 ) )// paket b
            {
	            params = new HashMap<String, Object>();
	            params.put( "subject", "a.1." );
	            params.put( "descr", "Jika Tertanggung terdiagnosa positif penyakit Kanker Payudara yang dapat dibuktikan dengan hasil pemeriksaan Patologi Anatomi, maka akan dibayarkan manfaat Asuransi sebesar 20% Uang pertanggungan sebanyak 1x(satu kali), dengan tetap memperhatikan maksimum total manfaat Asuransi pada poin b." );
	            result.add( params );
	            
	            params = new HashMap<String, Object>();
	            params.put( "subject", "a.2." );
	            params.put( "descr", "Apabila Tertanggung terdiagnosa Kanker Payudara stadium IV  maka akan dibayarkan manfaat Asuransi sebesar 100% Uang Pertanggungan setelah dikurangi dengan manfaat pada poin a.1 yang telah dibayarkan dan selanjutnya Asuransi berakhir." );
	            result.add( params );
	            
	            params = new HashMap<String, Object>();
	            params.put( "subject", "b." );
	            params.put( "descr", "Maksimum total manfaat Asuransi untuk Kanker payudara di bawah Stadium IV dan/atau Kanker Serviks di bawah Stadium IV yang dibayarkan untuk Tertanggung yang sama adalah Rp 400.000.000,-/US$ 40.000,-." );
	            result.add( params );
	            
	            params = new HashMap<String, Object>();
	            params.put( "subject", "c." );
	            params.put( "descr", "Maksimum total manfaat Asuransi yang dibayarkan untuk Tertanggung yang sama adalah Rp. 2.000.000.000,-/US$. 200.000,-." );
	            result.add( params );
            }
            
            
            if( cepr01030103Form.getLadiesInsCd().equals( 3 ) )// paket c
            {
	            params = new HashMap<String, Object>();
	            params.put( "subject", "a.1." );
	            params.put( "descr", "Jika Tertanggung terdiagnosa positif penyakit Kanker Payudara dan/atau Kanker Serviks, yang dapat dibuktikan dengan hasil pemeriksaan Patologi Anatomi, maka akan dibayarkan manfaat Asuransi sebesar 20% Uang pertanggungan sebanyak 1x(satu kali), dengan tetap memperhatikan maksimum total manfaat Asuransi pada poin b." );
	            result.add( params );
	            
	            params = new HashMap<String, Object>();
	            params.put( "subject", "a.2." );
	            params.put( "descr", "Apabila Tertanggung terdiagnosa Kanker Payudara stadium IV atau Kanker Serviks Stadium IV maka akan dibayarkan manfaat Asuransi " +
	            		"sebesar 100% Uang Pertanggungan setelah dikurangi dengan manfaat pada poin a.1 yang telah dibayarkan." );
	            result.add( params );
	            
	            params = new HashMap<String, Object>();
	            params.put( "subject", "a.3." );
	            params.put( "descr", "Apabila telah dibayarkan total manfaat Asuransi sebesar 100% Uang Pertanggungan, maka Asuransi berakhir." );
	            result.add( params );
	            
	            params = new HashMap<String, Object>();
	            params.put( "subject", "b." );
	            params.put( "descr", "Maksimum total manfaat Asuransi untuk Kanker payudara di bawah Stadium IV dan/atau Kanker Serviks di bawah Stadium IV yang dibayarkan untuk Tertanggung yang sama adalah Rp 400.000.000,-/US$ 40.000,-." );
	            result.add( params );
	            
	            params = new HashMap<String, Object>();
	            params.put( "subject", "c." );
	            params.put( "descr", "Maksimum total manfaat Asuransi yang dibayarkan untuk Tertanggung yang sama adalah Rp. 2000.000.000,-/US$. 200.000,-." );
	            result.add( params );
            }
            
        }

        return result;
    }
    
    
    public List<Map<String, Object>> getRiderLadiesInsSyariahSummaryList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesInsFlag() ) )
        {
            Map<String, Object> params;
            if( cepr01030103Form.getLadiesInsCd().equals( 1 ) )// paket a
            {
	            params = new HashMap<String, Object>();
	            params.put( "subject", "a.1." );
	            params.put( "descr", "Jika Peserta terdiagnosa positif penyakit Kanker Serviks yang dapat dibuktikan dengan hasil pemeriksaan Patologi Anatomi, maka akan dibayarkan manfaat Asuransi sebesar 20% Manfaat Asuransi Syariah sebanyak 1x(satu kali), dengan tetap memperhatikan maksimum total manfaat Asuransi pada poin b." );
	            result.add( params );
	            
	            params = new HashMap<String, Object>();
	            params.put( "subject", "a.2." );
	            params.put( "descr", "Apabila Peserta terdiagnosa Kanker Serviks stadium IV maka akan dibayarkan manfaat Asuransi sebesar 100% Manfaat Asuransi Syariah setelah dikurangi dengan manfaat pada poin a.1 yang telah dibayarkan dan selanjutnya Asuransi berakhir." );
	            result.add( params );
	            
	            params = new HashMap<String, Object>();
	            params.put( "subject", "b." );
	            params.put( "descr", "Maksimum total manfaat Asuransi untuk Kanker payudara di bawah Stadium IV dan/atau Kanker Serviks di bawah Stadium IV yang dibayarkan untuk Peserta yang sama adalah Rp 400.000.000,-/US$ 40.000,-." );
	            result.add( params );
	            
	            params = new HashMap<String, Object>();
	            params.put( "subject", "c." );
	            params.put( "descr", "Maksimum total manfaat Asuransi yang dibayarkan untuk Peserta yang sama adalah Rp. 2000.000.000,-/US$. 200.000,-." );
	            result.add( params );
            }
            
            if( cepr01030103Form.getLadiesInsCd().equals( 2 ) )// paket b
            {
	            params = new HashMap<String, Object>();
	            params.put( "subject", "a.1." );
	            params.put( "descr", "Jika Peserta terdiagnosa positif penyakit Kanker Payudara yang dapat dibuktikan dengan hasil pemeriksaan Patologi Anatomi, maka akan dibayarkan manfaat Asuransi sebesar 20% Manfaat Asuransi Syariah sebanyak 1x(satu kali), dengan tetap memperhatikan maksimum total manfaat Asuransi pada poin b." );
	            result.add( params );
	            
	            params = new HashMap<String, Object>();
	            params.put( "subject", "a.2." );
	            params.put( "descr", "Apabila Peserta terdiagnosa Kanker Payudara stadium IV  maka akan dibayarkan manfaat Asuransi sebesar 100% Manfaat Asuransi Syariah setelah dikurangi dengan manfaat pada poin a.1 yang telah dibayarkan dan selanjutnya Asuransi berakhir." );
	            result.add( params );
	            
	            params = new HashMap<String, Object>();
	            params.put( "subject", "b." );
	            params.put( "descr", "Maksimum total manfaat Asuransi untuk Kanker payudara di bawah Stadium IV dan/atau Kanker Serviks di bawah Stadium IV yang dibayarkan untuk Peserta yang sama adalah Rp 400.000.000,-/US$ 40.000,-." );
	            result.add( params );
	            
	            params = new HashMap<String, Object>();
	            params.put( "subject", "c." );
	            params.put( "descr", "Maksimum total manfaat Asuransi yang dibayarkan untuk Peserta yang sama adalah Rp. 2000.000.000,-/US$. 200.000,-." );
	            result.add( params );
            }
            
            
            if( cepr01030103Form.getLadiesInsCd().equals( 3 ) )// paket c
            {
	            params = new HashMap<String, Object>();
	            params.put( "subject", "a.1." );
	            params.put( "descr", "Jika Peserta terdiagnosa positif penyakit Kanker Payudara dan/atau Kanker Serviks, yang dapat dibuktikan dengan hasil pemeriksaan Patologi Anatomi, maka akan dibayarkan manfaat Asuransi sebesar 20% Manfaat Asuransi Syariah sebanyak 1x(satu kali), dengan tetap memperhatikan maksimum total manfaat Asuransi pada poin b." );
	            result.add( params );
	            
	            params = new HashMap<String, Object>();
	            params.put( "subject", "a.2." );
	            params.put( "descr", "Apabila Peserta terdiagnosa Kanker Payudara stadium IV atau Kanker Serviks Stadium IV maka akan dibayarkan manfaat Asuransi " +
	            		"sebesar 100% Manfaat Asuransi Syariah setelah dikurangi dengan manfaat pada poin a.1 yang telah dibayarkan." );
	            result.add( params );
	            
	            params = new HashMap<String, Object>();
	            params.put( "subject", "a.3." );
	            params.put( "descr", "Apabila telah dibayarkan total manfaat Asuransi sebesar 100% Manfaat Asuransi Syariah, maka Asuransi berakhir." );
	            result.add( params );
	            
	            params = new HashMap<String, Object>();
	            params.put( "subject", "b." );
	            params.put( "descr", "Maksimum total manfaat Asuransi untuk Kanker payudara di bawah Stadium IV dan/atau Kanker Serviks di bawah Stadium IV yang dibayarkan untuk Peserta yang sama adalah Rp 400.000.000,-/US$ 40.000,-." );
	            result.add( params );
	            
	            params = new HashMap<String, Object>();
	            params.put( "subject", "c." );
	            params.put( "descr", "Maksimum total manfaat Asuransi yang dibayarkan untuk Peserta yang sama adalah Rp. 2.000.000.000,-/US$. 200.000,-." );
	            result.add( params );
            }
            
        }

        return result;
    }

    public List<Map<String, Object>> getRiderHcpFamilyList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFamilyFlag() ) )
        {
        
            double baseBenefit = computeBaseBenefitHcpFam().doubleValue();
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Jenis Santunan" );
            params.put( "descr", MappingUtil.getLabel( cepr01030103Form.getHcpFamilyList(), cepr01030103Form.getHcpFamilyCd() )  );
            params.put( "term", "" );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap krn sakit" );
            params.put( "descr",  editorUtil.convertToStringWithoutCent( baseBenefit )  );
            if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_99 &&
            		( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040219Mapper.X5 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040219Mapper.X6 ) ||
            		cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_CERDAS &&
            		( cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X22 || cepr01030102Form.getRightPartOfBusinessCd() == Cepr01040211Mapper.X23 ) ){
            	params.put( "term", "Maks 100 hari per satu tahun polis *)" );
            }else{
            	params.put( "term", "Maks 100 hari per satu tahun polis" );
            }
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap di ruang ICU" );
            params.put( "descr",  editorUtil.convertToStringWithoutCent( baseBenefit * 2 )  );
            params.put( "term", "Maks 30 hari per satu tahun polis" );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap krn kecelakaan" );
            params.put( "descr",  editorUtil.convertToStringWithoutCent( baseBenefit * 2 )  );
            params.put( "term", "" );
            result.add( params );
            
            if( cepr01030103Form.getHcpFamilyCd().intValue() == 1 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "250,000" );
                params.put( "intermediate", "500,000" );
                params.put( "major", "750,000" );
                params.put( "complex", "1,000,000" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 2 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "500,000" );
                params.put( "intermediate", "1,000,000" );
                params.put( "major", "1,500,000" );
                params.put( "complex", "2,000,000" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 3 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "750,000" );
                params.put( "intermediate", "1,500,000" );
                params.put( "major", "2,250,000" );
                params.put( "complex", "3,000,000" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 4 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "1,000,000" );
                params.put( "intermediate", "2,000,000" );
                params.put( "major", "3,000,000" );
                params.put( "complex", "4,000,000" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 5 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "1,250,000" );
                params.put( "intermediate", "2,500,000" );
                params.put( "major", "3,750,000" );
                params.put( "complex", "5,000,000" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 6 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "1,500,000" );
                params.put( "intermediate", "3,000,000" );
                params.put( "major", "4,500,000" );
                params.put( "complex", "6,000,000" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 7 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "1,750,000" );
                params.put( "intermediate", "3,500,000" );
                params.put( "major", "5,250,000" );
                params.put( "complex", "7,000,000" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 8 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "2,000,000" );
                params.put( "intermediate", "4,000,000" );
                params.put( "major", "6,000,000" );
                params.put( "complex", "8,000,000" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 9 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "2,250,000" );
                params.put( "intermediate", "4,500,000" );
                params.put( "major", "6,750,000" );
                params.put( "complex", "9,000,000" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 10 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "2,500,000" );
                params.put( "intermediate", "5,000,000" );
                params.put( "major", "7,500,000" );
                params.put( "complex", "10,000,000" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 11 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "25" );
                params.put( "intermediate", "50" );
                params.put( "major", "75" );
                params.put( "complex", "100" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 12 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "40" );
                params.put( "intermediate", "80" );
                params.put( "major", "120" );
                params.put( "complex", "160" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 13 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "60" );
                params.put( "intermediate", "120" );
                params.put( "major", "180" );
                params.put( "complex", "240" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 14 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "80" );
                params.put( "intermediate", "160" );
                params.put( "major", "240" );
                params.put( "complex", "320" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 15 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "100" );
                params.put( "intermediate", "200" );
                params.put( "major", "300" );
                params.put( "complex", "400" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 16 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "120" );
                params.put( "intermediate", "240" );
                params.put( "major", "360" );
                params.put( "complex", "480" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 17 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "140" );
                params.put( "intermediate", "280" );
                params.put( "major", "420" );
                params.put( "complex", "560" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 18 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "160" );
                params.put( "intermediate", "320" );
                params.put( "major", "480" );
                params.put( "complex", "640" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 19 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "180" );
                params.put( "intermediate", "360" );
                params.put( "major", "540" );
                params.put( "complex", "720" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 20 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "200" );
                params.put( "intermediate", "400" );
                params.put( "major", "600" );
                params.put( "complex", "800" );
                result.add( params );	
            }
        }

        return result;
    }
    
    
    public List<Map<String, Object>> getRiderHcpFamilySyariahList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFamilyFlag() ) )
        {
        
            double baseBenefit = computeBaseBenefitHcpFam().doubleValue();
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Jenis Santunan" );
            params.put( "descr", MappingUtil.getLabel( cepr01030103Form.getHcpFamilyList(), cepr01030103Form.getHcpFamilyCd() )  );
            params.put( "term", "" );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap krn sakit" );
            params.put( "descr",  editorUtil.convertToStringWithoutCent( baseBenefit )  );
            params.put( "term", "Maks 100 hari per satu tahun polis *)" );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap di ruang ICU" );
            params.put( "descr",  editorUtil.convertToStringWithoutCent( baseBenefit * 2 )  );
            params.put( "term", "Maks 30 hari per satu tahun polis" );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap krn kecelakaan" );
            params.put( "descr",  editorUtil.convertToStringWithoutCent( baseBenefit * 2 )  );
            params.put( "term", "" );
            result.add( params );
            
            if( cepr01030103Form.getHcpFamilyCd().intValue() == 1 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "250,000" );
                params.put( "intermediate", "500,000" );
                params.put( "major", "750,000" );
                params.put( "complex", "1,000,000" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 2 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "500,000" );
                params.put( "intermediate", "1,000,000" );
                params.put( "major", "1,500,000" );
                params.put( "complex", "2,000,000" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 3 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "750,000" );
                params.put( "intermediate", "1,500,000" );
                params.put( "major", "2,250,000" );
                params.put( "complex", "3,000,000" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 4 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "1,000,000" );
                params.put( "intermediate", "2,000,000" );
                params.put( "major", "3,000,000" );
                params.put( "complex", "4,000,000" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 5 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "1,250,000" );
                params.put( "intermediate", "2,500,000" );
                params.put( "major", "3,750,000" );
                params.put( "complex", "5,000,000" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 6 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "1,500,000" );
                params.put( "intermediate", "3,000,000" );
                params.put( "major", "4,500,000" );
                params.put( "complex", "6,000,000" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 7 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "1,750,000" );
                params.put( "intermediate", "3,500,000" );
                params.put( "major", "5,250,000" );
                params.put( "complex", "7,000,000" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 8 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "2,000,000" );
                params.put( "intermediate", "4,000,000" );
                params.put( "major", "6,000,000" );
                params.put( "complex", "8,000,000" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 9 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "2,250,000" );
                params.put( "intermediate", "4,500,000" );
                params.put( "major", "6,750,000" );
                params.put( "complex", "9,000,000" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 10 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "2,500,000" );
                params.put( "intermediate", "5,000,000" );
                params.put( "major", "7,500,000" );
                params.put( "complex", "10,000,000" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 11 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "25" );
                params.put( "intermediate", "50" );
                params.put( "major", "75" );
                params.put( "complex", "100" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 12 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "40" );
                params.put( "intermediate", "80" );
                params.put( "major", "120" );
                params.put( "complex", "160" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 13 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "60" );
                params.put( "intermediate", "120" );
                params.put( "major", "180" );
                params.put( "complex", "240" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 14 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "80" );
                params.put( "intermediate", "160" );
                params.put( "major", "240" );
                params.put( "complex", "320" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 15 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "100" );
                params.put( "intermediate", "200" );
                params.put( "major", "300" );
                params.put( "complex", "400" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 16 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "120" );
                params.put( "intermediate", "240" );
                params.put( "major", "360" );
                params.put( "complex", "480" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 17 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "140" );
                params.put( "intermediate", "280" );
                params.put( "major", "420" );
                params.put( "complex", "560" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 18 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "160" );
                params.put( "intermediate", "320" );
                params.put( "major", "480" );
                params.put( "complex", "640" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 19 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "180" );
                params.put( "intermediate", "360" );
                params.put( "major", "540" );
                params.put( "complex", "720" );
                result.add( params );
            }else if( cepr01030103Form.getHcpFamilyCd().intValue() == 20 ){
            	params = new HashMap<String, Object>();
                params.put( "minor", "200" );
                params.put( "intermediate", "400" );
                params.put( "major", "600" );
                params.put( "complex", "800" );
                result.add( params );	
            }
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderHcpLadiesList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        BigDecimal factor = BigDecimal.ZERO;
        BigDecimal fisioterapi = BigDecimal.ZERO;
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpLadiesFlag() ) )
        {
        	if(cepr01030102Form.getCurrencyCd().equals("01")){
        		
        		factor = new BigDecimal("2.5");// faktor pengali utk minor, intermediate, major dan complex (R-100 - R-2000)
        		
        		if( cepr01030103Form.getHcpLadiesCd().intValue() <= 6 ){// R-100 - R-600
        			fisioterapi = new BigDecimal("100000");
        		}else if( cepr01030103Form.getHcpLadiesCd().intValue() >= 7 && cepr01030103Form.getHcpLadiesCd().intValue() <= 10){//  R-700 - R-1000
        			fisioterapi = new BigDecimal("200000");
        		}else{// R-1500 - R-2000
        			fisioterapi = new BigDecimal("300000");
        		}
        		
        	}else if(cepr01030102Form.getCurrencyCd().equals("02")){
        		
        		if( cepr01030103Form.getHcpLadiesCd().equals(13))factor = new BigDecimal("2.5");// faktor pengali utk minor, intermediate, major dan complex(D-10)
        		else factor = new BigDecimal("2");// faktor pengali utk minor, intermediate, major dan complex(D-20 - D-200)
        		
        		if( cepr01030103Form.getHcpLadiesCd().intValue() <= 18 ){// D-10 - D-60
        			fisioterapi = new BigDecimal("10");
        		}else if( cepr01030103Form.getHcpLadiesCd().intValue() >= 19 && cepr01030103Form.getHcpLadiesCd().intValue() <= 22){//  D-70 - D-100
        			fisioterapi = new BigDecimal("20");
        		}else{// D-150 - D-200
        			fisioterapi = new BigDecimal("30");
        		}
        		
        	}
        	
            double baseBenefit = computeBaseBenefitHcpLadies().doubleValue();
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Jenis Santunan" );
            params.put( "descr", MappingUtil.getLabel( cepr01030103Form.getHcpLadiesList(), cepr01030103Form.getHcpLadiesCd() )  );
            params.put( "term", "" );
            params.put( "fisioterapi", fisioterapi );
            params.put( "factor", factor );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap krn sakit" );
            params.put( "descr",  editorUtil.convertToStringWithoutCent( baseBenefit )  );
            params.put( "term", "Maks 180 hari per satu tahun polis" );
            params.put( "fisioterapi", fisioterapi );
            params.put( "factor", factor );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap di ruang ICU" );
            params.put( "descr",  editorUtil.convertToStringWithoutCent( baseBenefit * 2 )  );
            params.put( "term", "Maks 30 hari per satu tahun polis" );
            params.put( "fisioterapi", fisioterapi );
            params.put( "factor", factor );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap krn kecelakaan" );
            params.put( "descr",  editorUtil.convertToStringWithoutCent( baseBenefit * 2 )  );
            params.put( "term", "" );
            params.put( "fisioterapi", fisioterapi );
            params.put( "factor", factor );
            result.add( params );
        }

        return result;
    }

    public List<Map<String, Object>> getRiderHcpLadiesSyariahList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        BigDecimal factor = BigDecimal.ZERO;
        BigDecimal fisioterapi = BigDecimal.ZERO;
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpLadiesFlag() ) )
        {
        	if(cepr01030102Form.getCurrencyCd().equals("01")){
        		
        		factor = new BigDecimal("2.5");// faktor pengali utk minor, intermediate, major dan complex (R-100 - R-2000)
        		
        		if( cepr01030103Form.getHcpLadiesCd().intValue() <= 6 ){// R-100 - R-600
        			fisioterapi = new BigDecimal("100000");
        		}else if( cepr01030103Form.getHcpLadiesCd().intValue() >= 7 && cepr01030103Form.getHcpLadiesCd().intValue() <= 10){//  R-700 - R-1000
        			fisioterapi = new BigDecimal("200000");
        		}else{// R-1500 - R-2000
        			fisioterapi = new BigDecimal("300000");
        		}
        		
        	}else if(cepr01030102Form.getCurrencyCd().equals("02")){
        		
        		if( cepr01030103Form.getHcpLadiesCd().equals(13))factor = new BigDecimal("2.5");// faktor pengali utk minor, intermediate, major dan complex(D-10)
        		else factor = new BigDecimal("2");// faktor pengali utk minor, intermediate, major dan complex(D-20 - D-200)
        		
        		if( cepr01030103Form.getHcpLadiesCd().intValue() <= 18 ){// D-10 - D-60
        			fisioterapi = new BigDecimal("10");
        		}else if( cepr01030103Form.getHcpLadiesCd().intValue() >= 19 && cepr01030103Form.getHcpLadiesCd().intValue() <= 22){//  D-70 - D-100
        			fisioterapi = new BigDecimal("20");
        		}else{// D-150 - D-200
        			fisioterapi = new BigDecimal("30");
        		}
        		
        	}
        	
            double baseBenefit = computeBaseBenefitHcpLadies().doubleValue();
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Jenis Santunan" );
            params.put( "descr", MappingUtil.getLabel( cepr01030103Form.getHcpLadiesList(), cepr01030103Form.getHcpLadiesCd() )  );
            params.put( "term", "" );
            params.put( "fisioterapi", fisioterapi );
            params.put( "factor", factor );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap krn sakit" );
            params.put( "descr",  editorUtil.convertToStringWithoutCent( baseBenefit )  );
            params.put( "term", "Maks 180 hari per satu tahun polis" );
            params.put( "fisioterapi", fisioterapi );
            params.put( "factor", factor );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap di ruang ICU" );
            params.put( "descr",  editorUtil.convertToStringWithoutCent( baseBenefit * 2 )  );
            params.put( "term", "Maks 30 hari per satu tahun polis" );
            params.put( "fisioterapi", fisioterapi );
            params.put( "factor", factor );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap krn kecelakaan" );
            params.put( "descr",  editorUtil.convertToStringWithoutCent( baseBenefit * 2 )  );
            params.put( "term", "" );
            params.put( "fisioterapi", fisioterapi );
            params.put( "factor", factor );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderHcpProviderList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpProviderFlag() ) )
        {
            double baseBenefit = computeBaseBenefitHcpPro().doubleValue();
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Jenis Santunan" );
            params.put( "descr", MappingUtil.getLabel( cepr01030103Form.getHcpProviderList(), cepr01030103Form.getHcpProviderCd() )  );
            params.put( "term", "" );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap karena sakit atau kecelakaan" );
            params.put( "descr",  editorUtil.convertToStringWithoutCent( baseBenefit )  );
            params.put( "term", "Maks 180 hari per satu tahun polis" );
            result.add( params );

        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderHcpProviderSyariahList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpProviderFlag() ) )
        {
            double baseBenefit = computeBaseBenefitHcpPro().doubleValue();
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Jenis Santunan" );
            params.put( "descr", MappingUtil.getLabel( cepr01030103Form.getHcpProviderList(), cepr01030103Form.getHcpProviderCd() )  );
            params.put( "term", "" );
            result.add( params );

            params = new HashMap<String, Object>();
            params.put( "subject", "- Rawat Inap karena sakit atau kecelakaan" );
            params.put( "descr",  editorUtil.convertToStringWithoutCent( baseBenefit )  );
            params.put( "term", "Maks 180 hari per satu tahun polis" );
            result.add( params );

        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderPayorTpdDeathList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorTpdDeathFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Pemegang Polis meninggal dunia atau menderita Cacat Tetap Total" );
            params.put( "descr", "Pembebasan Pembayaran Premi Pokok"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderPayorTpdDeathSyariahList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorTpdDeathFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Pemegang Polis meninggal dunia atau menderita Cacat Tetap Total" );
            params.put( "descr", "Pembebasan Pembayaran Kontribusi Pokok"  );
            result.add( params );
        }

        return result;
    }

    public List<Map<String, Object>> getRiderPayorCiDeathList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorCiDeathFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Pemegang Polis meninggal dunia atau menderita penyakit kritis" );
            params.put( "descr", "Pembebasan Pembayaran Premi Pokok"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderPayorCiDeathSyariahList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorCiDeathFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Pemegang Polis meninggal dunia atau menderita penyakit kritis" );
            params.put( "descr", "Pembebasan Pembayaran Kontribusi Pokok"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderPayor10CiList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Ci10CiDeathFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Pemegang Polis terdiagnosa salah satu dari 31 penyakit kritis" );
            params.put( "descr", "Pembebasan Pembayaran Premi"  );
            result.add( params );
        }

        return result;
    }

    public List<Map<String, Object>> getRiderPayorCiList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorCiFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Pemegang Polis menderita penyakit kritis" );
            params.put( "descr", "Pembebasan Pembayaran Premi Pokok"  );
            result.add( params );
        }

        return result;
    }


    public List<Map<String, Object>> getRiderPayorCiSyariahList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorCiFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Pemegang Polis menderita penyakit kritis" );
            params.put( "descr", "Pembebasan Pembayaran Kontribusi Pokok"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderPayorSpouseTpdDeathList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorSpouseTpdDeathFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Pemegang Polis meninggal dunia atau menderita Cacat Tetap Total" );
            params.put( "descr", "Pembebasan Pembayaran Premi Pokok"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderPayorSpouseTpdDeathSyariahList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorSpouseTpdDeathFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Pemegang Polis meninggal dunia atau menderita Cacat Tetap Total" );
            params.put( "descr", "Pembebasan Pembayaran Kontribusi Pokok"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderPayorTpdCiDeathList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorTpdCiDeathFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Pemegang Polis meninggal dunia atau menderita Cacat Tetap Total atau terdiagnosa salah satu dari 31 Penyakit Kritis." );
            params.put( "descr", "Pembebasan Pembayaran Premi Lanjutan"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderPayorTpdCiDeathSyariahList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayorTpdCiDeathFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Pemegang Polis meninggal dunia atau menderita Cacat Tetap Total atau terdiagnosa salah satu dari 31 Penyakit Kritis." );
            params.put( "descr", "Pembebasan Pembayaran Kontribusi Lanjutan"  );
            result.add( params );
        }

        return result;
    }

    public List<Map<String, Object>> getRiderWaiverTpdList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverTpdFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Tertanggung menderita Cacat Tetap Total" );
            params.put( "descr", "Pembebasan Pembayaran Premi Pokok"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderWaiverTpdSyariahList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverTpdFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Peserta menderita Cacat Tetap Total" );
            params.put( "descr", "Pembebasan Pembayaran Kontribusi Pokok"  );
            result.add( params );
        }

        return result;
    }


    public List<Map<String, Object>> getRiderWaiverTpdCiList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverTpdCiFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Tertanggung menderita Cacat Tetap Total atau terdiagnosa salah satu dari 31 penyakit kritis" );
            params.put( "descr", "Pembebasan Pembayaran Premi Lanjutan"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderWaiverTpdCiSyariahList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverTpdCiFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Peserta menderita Cacat Tetap Total atau terdiagnosa salah satu dari 31 penyakit kritis" );
            params.put( "descr", "Pembebasan Pembayaran Kontribusi Lanjutan"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderWaiverCiList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverCiFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Tertanggung terdiagnosa salah satu dari 31 penyakit kritis" );
            params.put( "descr", "Pembebasan Pembayaran Premi Pokok"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderWaiverCiSyariahList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiverCiFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Peserta terdiagnosa salah satu dari 31 penyakit kritis" );
            params.put( "descr", "Pembebasan Pembayaran Kontribusi Pokok"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderWaiver10TpdList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiver5Tpd10TpdFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Tertanggung menderita Cacat Tetap Total" );
            params.put( "descr", "Pembebasan Pembayaran Premi"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderWaiver10TpdSyariahList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiver5Tpd10TpdFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Peserta menderita Cacat Tetap Total" );
            params.put( "descr", "Pembebasan Pembayaran Premi Pokok"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderWaiver5Tpd10CiList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiver5Tpd10CiFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Tertanggung menderita Cacat Tetap Total atau terdiagnosa salah satu dari 31 Penyakit Kritis" );
            params.put( "descr", "Pembebasan Pembayaran Premi Lanjutan"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderWaiver5Tpd10CiSyariahList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiver5Tpd10CiFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Peserta menderita Cacat Tetap Total atau terdiagnosa salah satu dari 31 Penyakit Kritis" );
            params.put( "descr", "Pembebasan Pembayaran Kontribusi Lanjutan"  );
            result.add( params );
        }

        return result;
    }

    public List<Map<String, Object>> getRiderWaiver10CiList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiver5Ci10CiFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Tertanggung menderita Penyakit Kritis" );
            params.put( "descr", "Pembebasan Pembayaran Premi"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderWaiver10CiSyariahList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getWaiver5Ci10CiFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Peserta terdiagnosa salah satu dari 31 penyakit kritis" );
            params.put( "descr", "Pembebasan Pembayaran Premi Pokok"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderTpdList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTpdFlag() ) )
        {
            Map<String, Object> params;
            params = new HashMap<String, Object>();
            params.put( "subject", "Tertanggung menderita Cacat Tetap Total" );
            double totalSumInsured = LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() );
            double maxBenefit = 0;
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                maxBenefit = 2000000000;
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                maxBenefit = 200000;
            }
            
            double tpdBenefit = MathUtil.min( totalSumInsured, maxBenefit );
            params.put( "descr", toMoneyWithCurrencyCd( tpdBenefit ) + " + Nilai Polis"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderTpdSyariahList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTpdFlag() ) )
        {
            Map<String, Object> params;
            params = new HashMap<String, Object>();
            params.put( "subject", "Peserta menderita Cacat Tetap Total" );
            double totalSumInsured = LazyConverter.toDouble( cepr01030102Form.getTotalSumInsured() );
            double maxBenefit = 0;
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                maxBenefit = 2000000000;
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                maxBenefit = 200000;
            }
            
            double tpdBenefit = MathUtil.min( totalSumInsured, maxBenefit );
            params.put( "descr", toMoneyWithCurrencyCd( tpdBenefit ) + " + Nilai Polis"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderTpd10List()
    {
       List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
       if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Tpd10TpdDeathFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Pemegang Polis meninggal dunia atau menderita Cacat Tetap Total" );
            params.put( "descr", "Pembebasan Pembayaran Premi"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderPayor5Tpd10CiDeathList()
    {
       List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
       if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Tpd10CiDeathFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Pemegang Polis meninggal dunia atau menderita Cacat Tetap Total atau terdiagnosa salah satu dari 31 Penyakit Kritis" );
            params.put( "descr", "Pembebasan Pembayaran Premi Lanjutan"  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderPayor5Tpd10CiDeathSyariahList()
    {
       List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
       if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getPayor5Tpd10CiDeathFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Pemegang Polis meninggal dunia atau menderita Cacat Tetap Total atau terdiagnosa salah satu dari 31 Penyakit Kritis" );
            params.put( "descr", "Pembebasan Pembayaran Kontribusi Lanjutan"  );
            result.add( params );
        }

        return result;
    }

    public List<Map<String, Object>> getRiderCi8List()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getCiFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            
            params = new HashMap<String, Object>();
            params.put( "subject", "Peserta untuk yang pertama kali selama masa pertanggungan, terdiagnosa salah satu dari 31 penyakit kritis." );
//            BigDecimal ciChoose = new BigDecimal("50");
//        	if( cepr01030103Form.getCiChooseCd() != null ){
//        		ciChoose = new BigDecimal(cepr01030103Form.getCiChooseCd());
//        	}
//            BigDecimal persentase = ciChoose.divide(new BigDecimal("100"));
//            double UangPertanggunganCi = cepr01030102Form.getTotalSumInsured().multiply( persentase).doubleValue();
//            double maksUP;
//            if(cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD) ){// dollar
//            	maksUP = 200000.0;
//            }else{ // rupiah
//            	maksUP = 2000000000.0;
//            }
            /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
            BigDecimal upMin = new BigDecimal("7500000");
            if (cepr01030103Form.getCiRiderAmount() != null){
            	upMin = cepr01030103Form.getCiRiderAmount();
            }
            double upCI = upMin.doubleValue();
            double maksUP;
	        if(cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD) ){// dollar
	        	maksUP = 500000.0;
	        }else{ // rupiah
	        	maksUP = 5000000000.0;
	        }
            double temp = MathUtil.min(upCI, maksUP);
            params.put( "descr", toMoneyWithCurrencyCd( temp ) );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderCiList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getCiFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            
            params = new HashMap<String, Object>();
            params.put( "subject", "Tertanggung untuk pertama kali selama masa pertanggungan, terdiagnosa salah satu dari 31 penyakit kritis." );
//            BigDecimal ciChoose = new BigDecimal("50");
//        	if( cepr01030103Form.getCiChooseCd() != null ){
//        		ciChoose = new BigDecimal(cepr01030103Form.getCiChooseCd());
//        	}
//            BigDecimal persentase = ciChoose.divide(new BigDecimal("100"));
//            double UangPertanggunganCi = cepr01030102Form.getTotalSumInsured().multiply( persentase).doubleValue();
//            double maksUP;
//            if(cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD) ){// dollar
//            	maksUP = 200000.0;
//            }else{ // rupiah
//            	maksUP = 2000000000.0;
//            }
            /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
            BigDecimal upMin = new BigDecimal("7500000");
            if (cepr01030103Form.getCiRiderAmount() != null){
            	upMin = cepr01030103Form.getCiRiderAmount();
            }
            double upCI = upMin.doubleValue();
            double maksUP;
	        if(cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD) ){// dollar
	        	maksUP = 500000.0;
	        }else{ // rupiah
	        	maksUP = 5000000000.0;
	        }
            double temp = MathUtil.min(upCI, maksUP);
            params.put( "descr", toMoneyWithCurrencyCd( temp ) );
            result.add( params );
        }

        return result;
    }

    public List<Map<String, Object>> getRiderCiSyariahList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getCiFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            
            params = new HashMap<String, Object>();
            params.put( "subject", "Peserta untuk pertama kali selama masa pertanggungan, terdiagnosa salah satu dari 31 penyakit kritis." );
//            BigDecimal ciChoose = new BigDecimal("50");
//        	if( cepr01030103Form.getCiChooseCd() != null ){
//        		ciChoose = new BigDecimal(cepr01030103Form.getCiChooseCd());
//        	}
//            BigDecimal persentase = ciChoose.divide(new BigDecimal("100"));
//            double UangPertanggunganCi = cepr01030102Form.getTotalSumInsured().multiply( persentase).doubleValue();
//            double maksUP;
//            if(cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD) ){// dollar
//            	maksUP = 200000.0;
//            }else{ // rupiah
//            	maksUP = 2000000000.0;
//            }
            /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
            BigDecimal upMin = new BigDecimal("7500000");
            if (cepr01030103Form.getCiRiderAmount() != null){
            	upMin = cepr01030103Form.getCiRiderAmount();
            }
            double upCI = upMin.doubleValue();
            double maksUP;
	        if(cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD) ){// dollar
	        	maksUP = 500000.0;
	        }else{ // rupiah
	        	maksUP = 5000000000.0;
	        }
            double temp = MathUtil.min(upCI, maksUP);
            params.put( "descr", toMoneyWithCurrencyCd( temp ) );
            result.add( params );
        }

        return result;
    }
    
    
    public List<Map<String, Object>> getRiderLadiesInsList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesInsFlag() ) )
        {
            Map<String, Object> params;
            if( cepr01030103Form.getLadiesInsCd().intValue() == 1 )// paket a
            {
            	params = new HashMap<String, Object>();
            	params.put( "subject", "Tertanggung terdiagnosa positif penyakit Kanker Serviks yang dapat dibuktikan dengan hasil pemeriksaan Patologi Anatomi." );
            	BigDecimal persentase = new BigDecimal(cepr01030103Form.getLadiesInsChooseCd()).divide(new BigDecimal("100"));
            	double maksUp = MathUtil.min(cepr01030102Form.getTotalSumInsured().multiply( persentase).doubleValue(), 2000000000.0 );
            	params.put( "descr", toMoneyWithCurrencyCd( maksUp ) );
            	result.add( params );
            	params = new HashMap<String, Object>();
            	params.put( "subject", "Tertanggung terdiagnosa Kanker Serviks Stadium IV." );
            	params.put( "descr", null );
            	result.add( params );
            }
            else if( cepr01030103Form.getLadiesInsCd().intValue() == 2 )// paket b
            {
            	params = new HashMap<String, Object>();
            	params.put( "subject", "Tertanggung terdiagnosa positif penyakit Kanker Payudara yang dapat dibuktikan dengan hasil pemeriksaan Patologi Anatomi." );
            	BigDecimal persentase = new BigDecimal(cepr01030103Form.getLadiesInsChooseCd()).divide(new BigDecimal("100"));
            	double maksUp = MathUtil.min(cepr01030102Form.getTotalSumInsured().multiply( persentase).doubleValue(), 2000000000.0 );
            	params.put( "descr", toMoneyWithCurrencyCd( maksUp ) );
            	result.add( params );
            	params = new HashMap<String, Object>();
            	params.put( "subject", "Tertanggung terdiagnosa Kanker Payudara Stadium IV." );
            	params.put( "descr", null );
            	result.add( params );
            }
            else if( cepr01030103Form.getLadiesInsCd().intValue() == 3 )// paket c
            {
            	params = new HashMap<String, Object>();
            	params.put( "subject", "Tertanggung terdiagnosa positif penyakit Kanker Payudara dan/atau Kanker Serviks yang dapat dibuktikan dengan hasil pemeriksaan Patologi Anatomi." );
            	BigDecimal persentase = new BigDecimal(cepr01030103Form.getLadiesInsChooseCd()).divide(new BigDecimal("100"));
            	double maksUp = MathUtil.min(cepr01030102Form.getTotalSumInsured().multiply( persentase).doubleValue(), 2000000000.0 );
            	params.put( "descr", toMoneyWithCurrencyCd( maksUp ) );
            	result.add( params );
            	params = new HashMap<String, Object>();
            	params.put( "subject", "Tertanggung terdiagnosa Kanker Payudara dan/atau Kanker Serviks Stadium IV." );
            	params.put( "descr", null );
            	result.add( params );
            }
        }

        return result;
    }
    
    
    public List<Map<String, Object>> getRiderLadiesInsSyariahList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesInsFlag() ) )
        {
            Map<String, Object> params;
            if( cepr01030103Form.getLadiesInsCd().intValue() == 1 )// paket a
            {
            	params = new HashMap<String, Object>();
            	params.put( "subject", "Peserta terdiagnosa positif penyakit Kanker Serviks yang dapat dibuktikan dengan hasil pemeriksaan Patologi Anatomi." );
            	BigDecimal persentase = new BigDecimal(cepr01030103Form.getLadiesInsChooseCd()).divide(new BigDecimal("100"));
            	double maksUp = MathUtil.min(cepr01030102Form.getTotalSumInsured().multiply( persentase).doubleValue(), 2000000000.0 );
            	params.put( "descr", toMoneyWithCurrencyCd( maksUp ) );
            	result.add( params );
            	params = new HashMap<String, Object>();
            	params.put( "subject", "Peserta terdiagnosa Kanker Serviks Stadium IV." );
            	params.put( "descr", null );
            	result.add( params );
            }
            else if( cepr01030103Form.getLadiesInsCd().intValue() == 2 )// paket b
            {
            	params = new HashMap<String, Object>();
            	params.put( "subject", "Peserta terdiagnosa positif penyakit Kanker Payudara yang dapat dibuktikan dengan hasil pemeriksaan Patologi Anatomi." );
            	BigDecimal persentase = new BigDecimal(cepr01030103Form.getLadiesInsChooseCd()).divide(new BigDecimal("100"));
            	double maksUp = MathUtil.min(cepr01030102Form.getTotalSumInsured().multiply( persentase).doubleValue(), 2000000000.0 );
            	params.put( "descr", toMoneyWithCurrencyCd( maksUp ) );
            	result.add( params );
            	params = new HashMap<String, Object>();
            	params.put( "subject", "Peserta terdiagnosa Kanker Payudara Stadium IV." );
            	params.put( "descr", null );
            	result.add( params );
            }
            else if( cepr01030103Form.getLadiesInsCd().intValue() == 3 )// paket c
            {
            	params = new HashMap<String, Object>();
            	params.put( "subject", "Peserta terdiagnosa positif penyakit Kanker Payudara dan/atau Kanker Serviks yang dapat dibuktikan dengan hasil pemeriksaan Patologi Anatomi." );
            	BigDecimal persentase = new BigDecimal(cepr01030103Form.getLadiesInsChooseCd()).divide(new BigDecimal("100"));
            	double maksUp = MathUtil.min(cepr01030102Form.getTotalSumInsured().multiply( persentase).doubleValue(), 2000000000.0 );
            	params.put( "descr", toMoneyWithCurrencyCd( maksUp ) );
            	result.add( params );
            	params = new HashMap<String, Object>();
            	params.put( "subject", "Peserta terdiagnosa Kanker Payudara dan/atau Kanker Serviks Stadium IV." );
            	params.put( "descr", null );
            	result.add( params );
            }
        }

        return result;
    }

    public List<Map<String, Object>> getRiderTermRiderList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTermRiderFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Manfaat meninggal dunia karena sebab apapun" );
            params.put( "descr", toMoneyWithCurrencyCd( cepr01030103Form.getTermRiderAmount() )  );
            result.add( params );
        }

        return result;
    }
    
    /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
    public List<Map<String, Object>> getRiderTerm5575RiderList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTerm5575RiderFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Manfaat meninggal dunia karena sebab apapun" );
            params.put( "descr", toMoneyWithCurrencyCd( cepr01030103Form.getTerm5575RiderAmount() )  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderScholarRiderList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getScholarshipFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Manfaat Tahunan apabila Pemegang Polis meninggal dunia " );
            params.put( "descr", toMoneyWithCurrencyCd( cepr01030103Form.getScholarshipCd() )  );
            result.add( params );
        }

        return result;
    }
    
    
    public List<Map<String, Object>> getRiderScholarSyariahRiderList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getScholarshipFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Manfaat Tahunan apabila Pemegang Polis meninggal dunia" );
            params.put( "descr", toMoneyWithCurrencyCd( cepr01030103Form.getScholarshipCd() )  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getScholarTableFactorList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getScholarshipFlag() ) )
        {
            Map<String, Object> params;
            Map<String, Object> paramsRate1;
            Map<String, Object> paramsRate2;
            for( int i = 0 ; i < 25 ; i ++ ){
            	paramsRate1 = new HashMap<String, Object>();
            	paramsRate1.put( "lsbsId", "835" );
            	paramsRate1.put( "liJenis", 1 );
            	paramsRate1.put( "liUsia", i+1 );
            	paramsRate1.put( "kursId", "01" );
            	BigDecimal rate1 = eproposalManager.selectTableFactor( paramsRate1 );
            	if( rate1 == null ) { rate1 = BigDecimal.ZERO;}
            	
            	paramsRate2 = new HashMap<String, Object>();
            	paramsRate2.put( "lsbsId", "835" );
            	paramsRate2.put( "liJenis", 2 );
            	paramsRate2.put( "liUsia", i+1 );
            	paramsRate2.put( "kursId", "01" );
            	BigDecimal rate2 = eproposalManager.selectTableFactor( paramsRate2 );
            	if( rate2 == null ) { rate2 = BigDecimal.ZERO;}
            	
            	params = new HashMap<String, Object>();
            	params.put( "age", LazyConverter.toString( i + 1 ) );
            	params.put( "rate1", editorUtil.convertToRoundedNoDigit( rate1 )  );
            	params.put( "rate2", editorUtil.convertToRoundedNoDigit( rate2 )  );
            	result.add( params );
            	
            }
           
            
        }

        return result;
    }
    
    public List<Map<String, Object>> getScholarUpSekaligusTahunanList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getScholarshipFlag() ) )
        {
            Map<String, Object> params;
            Map<String, Object> paramsRate;
            Integer liUsia = cepr01030101Form.getInsuredAge();
            Integer liJenis = 0 ;
            Integer sizeMax = 0;
            if( cepr01030103Form.getScholarshipChooseCd().equals( "22" ) ){
            	liJenis = 1;
            	sizeMax = 22 - cepr01030101Form.getInsuredAge();
            }else if( cepr01030103Form.getScholarshipChooseCd().equals( "25" ) ){
            	liJenis = 2;
            	sizeMax = 25 - cepr01030101Form.getInsuredAge();
            }
            for( int i = 0 ; i < sizeMax ; i ++ ){
            	paramsRate = new HashMap<String, Object>();
            	paramsRate.put( "lsbsId", "835" );
            	paramsRate.put( "liJenis", liJenis );
            	paramsRate.put( "liUsia", liUsia );
            	paramsRate.put( "kursId", "01" );
            	BigDecimal rate = eproposalManager.selectTableFactor( paramsRate );
            	if( rate == null ) { rate = BigDecimal.ZERO;}
            	
            	liUsia = liUsia + 1;
            	
            	double rateDouble = LazyConverter.toDouble( rate );
            	double upDouble = cepr01030103Form.getScholarshipCd().doubleValue();
            	params = new HashMap<String, Object>();
            	params.put( "year", LazyConverter.toString( i + 1 ) );
            	params.put( "upSekaligus", editorUtil.convertToRoundedNoDigit( new BigDecimal( upDouble * rateDouble ) )  );
            	params.put( "upTahunan", editorUtil.convertToRoundedNoDigit( new BigDecimal( cepr01030103Form.getScholarshipCd() ) )  );
            	result.add( params );
            	
            }
           
            
        }

        return result;
    }
    
    
    public List<Map<String, Object>> getChartScholarUpSekaligusTahunanList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getScholarshipFlag() ) )
        {
            Map<String, Object> params;
            Map<String, Object> paramsRate;
            Integer liUsia = cepr01030101Form.getInsuredAge();
            Integer liJenis = 0 ;
            Integer sizeMax = 0;
            Integer sisaMasaAsuransi = 0;
            if( cepr01030103Form.getScholarshipChooseCd().equals( "22" ) ){
            	liJenis = 1;
            	sizeMax = 22 - cepr01030101Form.getInsuredAge();
            	sisaMasaAsuransi = 22 - cepr01030101Form.getInsuredAge();
            }else if( cepr01030103Form.getScholarshipChooseCd().equals( "25" ) ){
            	liJenis = 2;
            	sizeMax = 25 - cepr01030101Form.getInsuredAge();
            	sisaMasaAsuransi = 25 - cepr01030101Form.getInsuredAge();
            }
            for( int i = 0 ; i < sizeMax ; i ++ ){
            	paramsRate = new HashMap<String, Object>();
            	paramsRate.put( "lsbsId", "835" );
            	paramsRate.put( "liJenis", liJenis );
            	paramsRate.put( "liUsia", liUsia );
            	paramsRate.put( "kursId", "01" );
            	BigDecimal rate = eproposalManager.selectTableFactor( paramsRate );
            	if( rate == null ) { rate = BigDecimal.ZERO;}
            	
            	liUsia = liUsia + 1;
            	
            	double rateDouble = LazyConverter.toDouble( rate );
            	double upDouble = cepr01030103Form.getScholarshipCd().doubleValue();
            	params = new HashMap<String, Object>();
            	params.put( "year",  i + 1 );
            	params.put( "upSekaligus", upDouble * rateDouble  );
            	params.put( "upTahunan", cepr01030103Form.getScholarshipCd().doubleValue()   );
            	result.add( params );
            }
        }
        return result;
    }
    
    public List<Map<String, Object>> getRiderTermSyariahRiderList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getTermRiderFlag() ) )
        {
            Map<String, Object> params;

            params = new HashMap<String, Object>();
            params.put( "subject", "Manfaat meninggal dunia karena sebab apapun" );
            params.put( "descr", toMoneyWithCurrencyCd( cepr01030103Form.getTermRiderAmount() )  );
            result.add( params );
        }

        return result;
    }

    public List<Map<String, Object>> getRiderHcpFamilyParticipantsList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFamilyFlag() ) )
        {
            List<ParticipantVO> participantVOList = cepr01030106Form.getParticipantVOList();
            participantVOList = removeEmptyParticipantVOFromTheList( participantVOList );
            Map<String, Object> params;
            Integer idx = 0;
            for( ParticipantVO vo : participantVOList )
            {
                idx++;
                params = new HashMap<String, Object>();
                params.put( "idx", idx.toString().concat( "." ) );
                params.put( "name", vo.getName() );
                params.put( "age", vo.getAge().toString() );
                result.add( params );
            }
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderHcpLadiesParticipantsList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpLadiesFlag() ) )
        {
            List<ParticipantVO> participantVOList = cepr01030110Form.getParticipantVOList();
            participantVOList = removeEmptyParticipantVOFromTheList( participantVOList );
            Map<String, Object> params;
            Integer idx = 0;
            for( ParticipantVO vo : participantVOList )
            {
                idx++;
                params = new HashMap<String, Object>();
                params.put( "idx", idx.toString().concat( "." ) );
                params.put( "name", vo.getName() );
                params.put( "age", vo.getAge().toString() );
                result.add( params );
            }
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderHcpProviderParticipantsList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpProviderFlag() ) )
        {
            List<ParticipantVO> participantVOList = cepr01030109Form.getParticipantVOList();
            participantVOList = removeEmptyParticipantVOFromTheList( participantVOList );
            Map<String, Object> params;
            Integer idx = 0;
            for( ParticipantVO vo : participantVOList )
            {
                idx++;
                params = new HashMap<String, Object>();
                params.put( "idx", idx.toString().concat( "." ) );
                params.put( "name", vo.getName() );
                params.put( "age", vo.getAge().toString() );
                result.add( params );
            }
        }

        return result;
    }

    public String checkParticapantEkaSehat()
    {
       String result = null;
       List<ParticipantVO> participantVOList = cepr01030107Form.getParticipantVOList();
       for( int i = 0 ; i < participantVOList.size() ; i ++ )
       {
    	   if( participantVOList.get(i).getName() != null && !"".equals( participantVOList.get(i).getName() ) ){
    		   result = "yes";
    		   break;
    	   }else{
    		   result = "no";
    	   }
       }
       return result;
    }
    
    public List<Map<String, Object>> getRiderEkaSehatParticipantsList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 189 || cepr01030102Form.getLeftPartOfBusinessCd() == 183 || cepr01030102Form.getLeftPartOfBusinessCd() == 193 || cepr01030102Form.getLeftPartOfBusinessCd() == 201 || CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag()) ||
        		( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getEkaSehatFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getEkaSehatFlagDisplay())) )
        {
            List<ParticipantVO> participantVOList = cepr01030107Form.getParticipantVOList();
            participantVOList = removeEmptyParticipantVOFromTheList( participantVOList );
            Map<String, Object> params;
            Integer idx = 0;
            for( ParticipantVO vo : participantVOList )
            {
                idx++;
                params = new HashMap<String, Object>();
                params.put( "idx_eka_sehat", idx.toString().concat( "." ) );
                params.put( "name_eka_sehat", vo.getName() );
                params.put( "age_eka_sehat", vo.getAge().toString() );
                result.add( params );
            }
        }

        return result;
    }    
    
    public List<Map<String, Object>> getRiderLadiesMedExpenseParticipantsList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesMedExpenseFlag() ) )
        {
            List<ParticipantVO> participantVOList = cepr01030111Form.getParticipantVOList();
            participantVOList = removeEmptyParticipantVOFromTheList( participantVOList );
            Map<String, Object> params;
            Integer idx = 0;
            for( ParticipantVO vo : participantVOList )
            {
                idx++;
                params = new HashMap<String, Object>();
                params.put( "idx", idx.toString().concat( "." ) );
                params.put( "name", vo.getName() );
                params.put( "age", vo.getAge().toString() );
                result.add( params );
            }
        }

        return result;
    }
     
    public List<Map<String, Object>> getRiderLadiesMedExpenseInnerLimitParticipantsList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesMedExpenseInnerLimitFlag() ) )
        {
            List<ParticipantVO> participantVOList = cepr01030112Form.getParticipantVOList();
            participantVOList = removeEmptyParticipantVOFromTheList( participantVOList );
            Map<String, Object> params;
            Integer idx = 0;
            for( ParticipantVO vo : participantVOList )
            {
                idx++;
                params = new HashMap<String, Object>();
                params.put( "idx", idx.toString().concat( "." ) );
                params.put( "name", vo.getName() );
                params.put( "age", vo.getAge().toString() );
                result.add( params );
            }
        }

        return result;
    }
    
    public BigDecimal computeBaseBenefitHcpFam()
    {
        BigDecimal result;
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpFamilyFlag() ) )
        {
            double benefitAmount = 0;
            // todo
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                benefitAmount = cepr01030103Form.getHcpFamilyCd() * 100000;
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                benefitAmount = ( cepr01030103Form.getHcpFamilyCd() - 10 )* 10;
            }

            result = new BigDecimal( benefitAmount );
        }
        else
        {
            result = null;
        }
        return result;
    }
    
    public BigDecimal computeBaseBenefitHcpLadies()
    {
        BigDecimal result;
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpLadiesFlag() ) )
        {
            double benefitAmount = 0;
            // todo
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	if( cepr01030103Form.getHcpLadiesCd().equals(11) ||  cepr01030103Form.getHcpLadiesCd().equals(12) ){
            		if (cepr01030103Form.getHcpLadiesCd().equals(11)) benefitAmount = 15*100000;
            		else benefitAmount = 20*100000;
            	}else{
            		benefitAmount = cepr01030103Form.getHcpLadiesCd() * 100000;
            	}
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	if( cepr01030103Form.getHcpLadiesCd().equals(23) ||  cepr01030103Form.getHcpLadiesCd().equals(24) ){
            		if (cepr01030103Form.getHcpLadiesCd().equals(23)) benefitAmount = 15*10;
            		else benefitAmount = 20*10;
            	}else{
            		   benefitAmount = (cepr01030103Form.getHcpLadiesCd()  - 12 )* 10;
            	}
            }

            result = new BigDecimal( benefitAmount );
        }
        else
        {
            result = null;
        }
        return result;
    }
    
    public BigDecimal computeBaseBenefitHcpPro()
    {
        BigDecimal result;
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getHcpProviderFlag() ) )
        {
            double benefitAmount = 0;
            // todo
            if( Hardcode.CUR_IDR_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
            	Integer benefit = cepr01030103Form.getHcpProviderCd();
	            if( benefit == 11){
	            	benefit = 15;
	            }else if( benefit == 12){
	            	benefit = 20;
	            }            	
                benefitAmount = benefit * 100000;                
            }
            else if( Hardcode.CUR_USD_CD.equals( cepr01030102Form.getCurrencyCd() ) )
            {
                benefitAmount = cepr01030103Form.getHcpProviderCd() * 10;
            }

            result = new BigDecimal( benefitAmount );
        }
        else
        {
            result = null;
        }
        return result;
    }
    
    public List<Map<String, Object>> getRiderEkaSehatListMap( )
    {
   	 List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
     if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) ||  
    		 ( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getEkaSehatFlag() ) &&  CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getEkaSehatFlagDisplay() ) ) )
     {
     	if( ( cepr01030103Form.getEkaSehatCd() != null && cepr01030103Form.getEkaSehatCd() > 0 )
     			||( cepr01030102Form.getEkaSehatCd()!= null && cepr01030102Form.getEkaSehatCd() > 0 ) )
     	{
     		Map<String, Object> params;
     		String jenisSantunan = null;
     		String biayaInap = null;
     		String biayaIcu = null;
     		String limitKejadian = null;
     		String maksPenggantian = null;
     		Integer jenis = null;
     		if(  cepr01030103Form.getEkaSehatCd() != null && cepr01030103Form.getEkaSehatCd() > 0 ){
     			jenis = cepr01030103Form.getEkaSehatCd();
     		}else if( cepr01030102Form.getEkaSehatCd()!= null && cepr01030102Form.getEkaSehatCd() > 0 ){
     			jenis = cepr01030102Form.getEkaSehatCd();
     		}
         	if( jenis == 1 ){ // plan A
         		jenisSantunan="Plan A-100";
         		biayaInap="100,000";
         		biayaIcu="200,000";
         		limitKejadian="12,500,000";
         		maksPenggantian="50,000,000";
         	}else if( jenis == 2 ){ // plan B
         		jenisSantunan="Plan B-150";
         		biayaInap="150,000";
         		biayaIcu="300,000";
         		limitKejadian="18,750,000";
         		maksPenggantian="75,000,000";
         	}else if( jenis == 3 ){ // plan C
         		jenisSantunan="Plan C-200";
         		biayaInap="200,000";
         		biayaIcu="400,000";
         		limitKejadian="25,000,000";
         		maksPenggantian="100,000,000";
         	}else if( jenis == 4 ){ // plan D
         		jenisSantunan="Plan D-250";
         		biayaInap="250,000";
         		biayaIcu="500,000";
         		limitKejadian="31,250,000";
         		maksPenggantian="125,000,000";
         	}else if( jenis == 5 ){ // plan E
         		jenisSantunan="Plan E-300";
         		biayaInap="300,000";
         		biayaIcu="600,000";
         		limitKejadian="37,500,000";
         		maksPenggantian="150,000,000";
         	}else if( jenis == 6 ){ // plan F
         		jenisSantunan="Plan F-350";
         		biayaInap="350,000";
         		biayaIcu="700,000";
         		limitKejadian="43,750,000";
         		maksPenggantian="175,000,000";
         	}else if( jenis == 7 ){ // plan G
         		jenisSantunan="Plan G-400";
         		biayaInap="400,000";
         		biayaIcu="800,000";
         		limitKejadian="50,000,000";
         		maksPenggantian="200,000,000";
         	}else if( jenis == 8 ){ // plan H
         		jenisSantunan="Plan H-500";
         		biayaInap="500,000";
         		biayaIcu="1,000,000";
         		limitKejadian="100,000,000";
         		maksPenggantian="400,000,000";
         	}else if( jenis == 9 ){ // plan I
         		jenisSantunan="Plan I-600";
         		biayaInap="600,000";
         		biayaIcu="1,200,000";
         		limitKejadian="125,000,000";
         		maksPenggantian="500,000,000";
         	}else if( jenis == 10 ){ // plan J
         		jenisSantunan="Plan J-700";
         		biayaInap="700,000";
         		biayaIcu="1,400,000";
         		limitKejadian="150,000,000";
         		maksPenggantian="600,000,000";
         	}else if( jenis == 11 ){ // plan K
         		jenisSantunan="Plan K-800";
         		biayaInap="800,000";
         		biayaIcu="1,600,000";
         		limitKejadian="175,000,000";
         		maksPenggantian="700,000,000";
         	}else if( jenis == 12 ){ // plan L
         		jenisSantunan="Plan L-900";
         		biayaInap="900,000";
         		biayaIcu="1,800,000";
         		limitKejadian="200,000,000";
         		maksPenggantian="800,000,000";
         	}else if( jenis == 13 ){ // plan M
         		jenisSantunan="Plan M-1000";
         		biayaInap="1,000,000";
         		biayaIcu="2,000,000";
         		limitKejadian="225,000,000";
         		maksPenggantian="900,000,000";
         	}else if( jenis == 14 ){ // plan N
         		jenisSantunan="Plan N-1500";
         		biayaInap="1,500,000";
         		biayaIcu="3,000,000";
         		limitKejadian="350,000,000";
         		maksPenggantian="1,400,000,000";
         	}else if( jenis == 15 ){ // plan O
         		jenisSantunan="Plan O-2000";
         		biayaInap="2,000,000";
         		biayaIcu="4,000,000";
         		limitKejadian="475,000,000";
         		maksPenggantian="1,900,000,000";
         	}else if( jenis == 16 ){ // plan P
         		jenisSantunan="Plan P-5000";
         		biayaInap="5,000,000";
         		biayaIcu="4,000,000";
         		limitKejadian="475,000,000";
         		maksPenggantian="5,000,000,000";
         	}else if( jenis == 17 ){ // plan Q
         		jenisSantunan="Plan Q-7500";
         		biayaInap="7,500,000";
         		biayaIcu="4,000,000";
         		limitKejadian="475,000,000";
         		maksPenggantian="6,000,000,000";
         	}  
         	
         	
         	 params = new HashMap<String, Object>();
              params.put( "subject", "Jenis Santunan" );
              params.put( "descr", jenisSantunan  );
              params.put( "detail", ""  );
              result.add( params );
              
              params = new HashMap<String, Object>();
              params.put( "subject", "- Biaya Kamar dan Menginap" );
              params.put( "descr", biayaInap  );
              params.put( "detail", "maks 365 hari/tahun"  );
              result.add( params );
              
              params = new HashMap<String, Object>();
              params.put( "subject", "- Biaya Kamar ICU/ICCU" );
              params.put( "descr", biayaIcu  );
              params.put( "detail", "maks 60 hari/tahun"  );
              result.add( params );
              
         	 params = new HashMap<String, Object>();
              params.put( "subject", "- Biaya Aneka Perawatan di RS" );
              params.put( "descr", "As Charge"  );
              params.put( "detail", ""  );
              result.add( params );
              
         	 params = new HashMap<String, Object>();
              params.put( "subject", "- Biaya Operasi/Pembedahan" );
              params.put( "descr", "As Charge"  );
              params.put( "detail", ""  );
              result.add( params );
              
         	 params = new HashMap<String, Object>();
              params.put( "subject", "- Biaya Kunjungan Dokter/Dokter ahli di RS" );
              params.put( "descr", "As Charge"  );
              params.put( "detail", ""  );
              result.add( params );
              
         	 params = new HashMap<String, Object>();
              params.put( "subject", "- Biaya Pemeriksaan Laboratorium & Test Diagnostik" );
              params.put( "descr", "As Charge"  );
              params.put( "detail", "7 hari sebelum RI"  );
              result.add( params );
              
         	 params = new HashMap<String, Object>();
              params.put( "subject", "- Biaya Konsultasi Lanjutan" );
              params.put( "descr", "As Charge"  );
              params.put( "detail", "30 hari setelah RI"  );
              result.add( params );
              
         	 params = new HashMap<String, Object>();
              params.put( "subject", "- Transportasi dgn Mobil Ambulance ke RS (darurat)" );
              params.put( "descr", "As Charge"  );
              params.put( "detail", ""  );
              result.add( params );
              
              if( jenis == 16 || jenis == 17){
            	  params = new HashMap<String, Object>();
                  params.put( "subject", "- Perawat Pribadi di Rumah" );
                  params.put( "descr", "As Charge"  );
                  params.put( "detail", ""  );
                  result.add( params );            	  
              }
              
         	 params = new HashMap<String, Object>();
              params.put( "subject", "* Batas Maks. Penggantian pertahun" );
              params.put( "descr", maksPenggantian  );
              params.put( "detail", ""  );
              result.add( params ); 
     	}
     }    
     return result;
 }
    
    
    public List<Map<String, Object>> getRiderEkaSehatSyariahListMap( )
    {
   	 List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
     if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) )
     {
     	if( cepr01030103Form.getEkaSehatCd() != null && cepr01030103Form.getEkaSehatCd() > 0 )
     	{
     		Map<String, Object> params;
     		String jenisSantunan = null;
     		String biayaInap = null;
     		String biayaIcu = null;
     		String limitKejadian = null;
     		String maksPenggantian = null;
         	if( cepr01030103Form.getEkaSehatCd() == 1 ){ // plan A
         		jenisSantunan="Plan A-100";
         		biayaInap="100,000";
         		biayaIcu="200,000";
         		limitKejadian="12,500,000";
         		maksPenggantian="50,000,000";
         	}else if( cepr01030103Form.getEkaSehatCd() == 2 ){ // plan B
         		jenisSantunan="Plan B-150";
         		biayaInap="150,000";
         		biayaIcu="300,000";
         		limitKejadian="18,750,000";
         		maksPenggantian="75,000,000";
         	}else if( cepr01030103Form.getEkaSehatCd() == 3 ){ // plan C
         		jenisSantunan="Plan C-200";
         		biayaInap="200,000";
         		biayaIcu="400,000";
         		limitKejadian="25,000,000";
         		maksPenggantian="100,000,000";
         	}else if( cepr01030103Form.getEkaSehatCd() == 4 ){ // plan D
         		jenisSantunan="Plan D-250";
         		biayaInap="250,000";
         		biayaIcu="500,000";
         		limitKejadian="31,250,000";
         		maksPenggantian="125,000,000";
         	}else if( cepr01030103Form.getEkaSehatCd() == 5 ){ // plan E
         		jenisSantunan="Plan E-300";
         		biayaInap="300,000";
         		biayaIcu="600,000";
         		limitKejadian="37,500,000";
         		maksPenggantian="150,000,000";
         	}else if( cepr01030103Form.getEkaSehatCd() == 6 ){ // plan F
         		jenisSantunan="Plan F-350";
         		biayaInap="350,000";
         		biayaIcu="700,000";
         		limitKejadian="43,750,000";
         		maksPenggantian="175,000,000";
         	}else if( cepr01030103Form.getEkaSehatCd() == 7 ){ // plan G
         		jenisSantunan="Plan G-400";
         		biayaInap="400,000";
         		biayaIcu="800,000";
         		limitKejadian="50,000,000";
         		maksPenggantian="200,000,000";
         	}else if( cepr01030103Form.getEkaSehatCd() == 8 ){ // plan H
         		jenisSantunan="Plan H-500";
         		biayaInap="500,000";
         		biayaIcu="1,000,000";
         		limitKejadian="100,000,000";
         		maksPenggantian="400,000,000";
         	}else if( cepr01030103Form.getEkaSehatCd() == 9 ){ // plan I
         		jenisSantunan="Plan I-600";
         		biayaInap="600,000";
         		biayaIcu="1,200,000";
         		limitKejadian="125,000,000";
         		maksPenggantian="500,000,000";
         	}else if( cepr01030103Form.getEkaSehatCd() == 10 ){ // plan J
         		jenisSantunan="Plan J-700";
         		biayaInap="700,000";
         		biayaIcu="1,400,000";
         		limitKejadian="150,000,000";
         		maksPenggantian="600,000,000";
         	}else if( cepr01030103Form.getEkaSehatCd() == 11 ){ // plan K
         		jenisSantunan="Plan K-800";
         		biayaInap="800,000";
         		biayaIcu="1,600,000";
         		limitKejadian="175,000,000";
         		maksPenggantian="700,000,000";
         	}else if( cepr01030103Form.getEkaSehatCd() == 12 ){ // plan L
         		jenisSantunan="Plan L-900";
         		biayaInap="900,000";
         		biayaIcu="1,800,000";
         		limitKejadian="200,000,000";
         		maksPenggantian="800,000,000";
         	}else if( cepr01030103Form.getEkaSehatCd() == 13 ){ // plan M
         		jenisSantunan="Plan M-1000";
         		biayaInap="1,000,000";
         		biayaIcu="2,000,000";
         		limitKejadian="225,000,000";
         		maksPenggantian="900,000,000";
         	}else if( cepr01030103Form.getEkaSehatCd() == 14 ){ // plan N
         		jenisSantunan="Plan N-1500";
         		biayaInap="1,500,000";
         		biayaIcu="3,000,000";
         		limitKejadian="350,000,000";
         		maksPenggantian="1,400,000,000";
         	}else if( cepr01030103Form.getEkaSehatCd() == 15 ){ // plan O
         		jenisSantunan="Plan O-2000";
         		biayaInap="2,000,000";
         		biayaIcu="4,000,000";
         		limitKejadian="475,000,000";
         		maksPenggantian="1,900,000,000";
         	} else if( cepr01030103Form.getEkaSehatCd() == 16 ){ // plan P
         		jenisSantunan="Plan P-5000";
         		biayaInap="5,000,000";
         		biayaIcu="4,000,000";
         		limitKejadian="475,000,000";
         		maksPenggantian="5,000,000,000";
         	}else if( cepr01030103Form.getEkaSehatCd() == 17 ){ // plan Q
         		jenisSantunan="Plan Q-7500";
         		biayaInap="7,500,000";
         		biayaIcu="4,000,000";
         		limitKejadian="475,000,000";
         		maksPenggantian="6,000,000,000";
         	}  
         	
         	 params = new HashMap<String, Object>();
              params.put( "subject", "Jenis Santunan" );
              params.put( "descr", jenisSantunan  );
              params.put( "detail", ""  );
              result.add( params );
              
              params = new HashMap<String, Object>();
              params.put( "subject", "- Biaya Kamar dan Menginap" );
              params.put( "descr", biayaInap  );
              params.put( "detail", "maks 365 hari/tahun"  );
              result.add( params );
              
              params = new HashMap<String, Object>();
              params.put( "subject", "- Biaya Kamar ICU/ICCU" );
              params.put( "descr", biayaIcu  );
              params.put( "detail", "maks 60 hari/tahun"  );
              result.add( params );
              
         	 params = new HashMap<String, Object>();
              params.put( "subject", "- Biaya Aneka Perawatan di RS" );
              params.put( "descr", "As Charge"  );
              params.put( "detail", ""  );
              result.add( params );
              
         	 params = new HashMap<String, Object>();
              params.put( "subject", "- Biaya Operasi/Pembedahan" );
              params.put( "descr", "As Charge"  );
              params.put( "detail", ""  );
              result.add( params );
              
         	 params = new HashMap<String, Object>();
              params.put( "subject", "- Biaya Kunjungan Dokter/Dokter ahli di RS" );
              params.put( "descr", "As Charge"  );
              params.put( "detail", ""  );
              result.add( params );
              
         	 params = new HashMap<String, Object>();
              params.put( "subject", "- Biaya Pemeriksaan Laboratorium & Test Diagnostik" );
              params.put( "descr", "As Charge"  );
              params.put( "detail", "7 hari sebelum RI"  );
              result.add( params );
              
         	 params = new HashMap<String, Object>();
              params.put( "subject", "- Biaya Konsultasi Lanjutan" );
              params.put( "descr", "As Charge"  );
              params.put( "detail", "30 hari setelah RI"  );
              result.add( params );
              
         	 params = new HashMap<String, Object>();
              params.put( "subject", "- Transportasi dgn Mobil Ambulance ke RS (darurat)" );
              params.put( "descr", "As Charge"  );
              params.put( "detail", ""  );
              result.add( params );
              
              if( cepr01030103Form.getEkaSehatCd() == 16 || cepr01030103Form.getEkaSehatCd() == 17){
            	  params = new HashMap<String, Object>();
                  params.put( "subject", "- Perawat Pribadi di Rumah" );
                  params.put( "descr", "As Charge"  );
                  params.put( "detail", ""  );
                  result.add( params );            	  
              }
              
         	 params = new HashMap<String, Object>();
              params.put( "subject", "* Batas Maks. Penggantian pertahun" );
              params.put( "descr", maksPenggantian  );
              params.put( "detail", ""  );
              result.add( params ); 
     	}
     }
    
     return result;
 }
    
    
    public String checkParticapantEkaSehatInnerLimit()
    {
       String result = null;
       List<ParticipantVO> participantVOList = cepr01030108Form.getParticipantVOList();
       for( int i = 0 ; i < participantVOList.size() ; i ++ )
       {
    	   if( participantVOList.get(i).getName() != null && !"".equals( participantVOList.get(i).getName() ) ){
    		   result = "yes";
    		   break;
    	   }else{
    		   result = "no";
    	   }
       }
       return result;
    }
    
    public List<Map<String, Object>> getRiderEkaSehatInnerLimitParticipantsList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatInnerLimitFlag()) )
        {
            List<ParticipantVO> participantVOList = cepr01030108Form.getParticipantVOList();
            participantVOList = removeEmptyParticipantVOFromTheList( participantVOList );
            Map<String, Object> params;
            Integer idx = 0;
            for( ParticipantVO vo : participantVOList )
            {
                idx++;
                params = new HashMap<String, Object>();
                params.put( "idx_eka_sehatIL", idx.toString().concat( "." ) );
                params.put( "name_eka_sehatIL", vo.getName() );
                params.put( "age_eka_sehatIL", vo.getAge().toString() );
                result.add( params );
            }
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderEkaSehatInnerLimitListMap( )
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatInnerLimitFlag() ) )
    	{
    		if( cepr01030103Form.getEkaSehatInnerLimitCd() != null && cepr01030103Form.getEkaSehatInnerLimitCd() > 0 )
    		{
    			Map<String, Object> params;
    			String jenisSantunan = null;
         		String biayaInap = null;
         		String biayaIcu = null;
         		String anekaPerawatan = null;
    			String biayaOP = null;
    			String kunjungan = null;
    			String kunjunganSpesialis = null;
    			String biayaPerawatanPribadi = null;
    			String biayaAmbulans = null;
    			String biayaBAPerawatan = null;
         		String maksPenggantian = null;
         		if( cepr01030103Form.getEkaSehatInnerLimitCd() == 1 ){ // plan A
             		jenisSantunan="Plan A-100";
             		biayaInap="100,000";
             		biayaIcu="200,000";
             		anekaPerawatan="1,750,000";
    				biayaOP="12,000,000";
    				kunjungan="50,000";
    				kunjunganSpesialis="300,000";
    				biayaPerawatanPribadi="50,000";
    				biayaAmbulans="40,000";
    				biayaBAPerawatan="350,000";
             		maksPenggantian="50,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 2 ){ // plan B
             		jenisSantunan="Plan B-150";
             		biayaInap="150,000";
             		biayaIcu="300,000";
             		anekaPerawatan="2,250,000";
    				biayaOP="17,250,000";
    				kunjungan="60,000";
    				kunjunganSpesialis="360,000";
    				biayaPerawatanPribadi="60,000";
    				biayaAmbulans="45,000";
    				biayaBAPerawatan="500,000";
             		maksPenggantian="75,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 3 ){ // plan C
             		jenisSantunan="Plan C-200";
             		biayaInap="200,000";
             		biayaIcu="400,000";
             		anekaPerawatan="2,750,000";
    				biayaOP="22,500,000";
    				kunjungan="70,000";
    				kunjunganSpesialis="420,000";
    				biayaPerawatanPribadi="70,000";
    				biayaAmbulans="50,000";
    				biayaBAPerawatan="650,000";
             		maksPenggantian="100,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 4 ){ // plan D
             		jenisSantunan="Plan D-250";
             		biayaInap="250,000";
             		biayaIcu="500,000";
             		anekaPerawatan="3,250,000";
    				biayaOP="27,750,000";
    				kunjungan="80,000";
    				kunjunganSpesialis="480,000";
    				biayaPerawatanPribadi="80,000";
    				biayaAmbulans="55,000";
    				biayaBAPerawatan="800,000";
             		maksPenggantian="125,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 5 ){ // plan E
             		jenisSantunan="Plan E-300";
             		biayaInap="300,000";
             		biayaIcu="600,000";
             		anekaPerawatan="3,750,000";
    				biayaOP="33,000,000";
    				kunjungan="90,000";
    				kunjunganSpesialis="540,000";
    				biayaPerawatanPribadi="90,000";
    				biayaAmbulans="60,000";
    				biayaBAPerawatan="950,000";
             		maksPenggantian="150,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 6 ){ // plan F
             		jenisSantunan="Plan F-350";
             		biayaInap="350,000";
             		biayaIcu="700,000";
             		anekaPerawatan="4,250,000";
    				biayaOP="38,250,000";
    				kunjungan="100,000";
    				kunjunganSpesialis="600,000";
    				biayaPerawatanPribadi="100,000";
    				biayaAmbulans="65,000";
    				biayaBAPerawatan="1,100,000";
             		maksPenggantian="175,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 7 ){ // plan G
             		jenisSantunan="Plan G-400";
             		biayaInap="400,000";
             		biayaIcu="800,000";
             		anekaPerawatan="4,750,000";
    				biayaOP="43,500,000";
    				kunjungan="110,000";
    				kunjunganSpesialis="660,000";
    				biayaPerawatanPribadi="110,000";
    				biayaAmbulans="70,000";
    				biayaBAPerawatan="1,250,000";
             		maksPenggantian="200,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 8 ){ // plan H
             		jenisSantunan="Plan H-500";
             		biayaInap="500,000";
             		biayaIcu="1.000,000";
             		anekaPerawatan="5,750,000";
    				biayaOP="54,000,000";
    				kunjungan="130,000";
    				kunjunganSpesialis="780,000";
    				biayaPerawatanPribadi="130,000";
    				biayaAmbulans="80,000";
    				biayaBAPerawatan="1,550,000";
             		maksPenggantian="250,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 9 ){ // plan I
             		jenisSantunan="Plan I-600";
             		biayaInap="600,000";
             		biayaIcu="1,200,000";
             		anekaPerawatan="6,750,000";
    				biayaOP="64,500,000";
    				kunjungan="150,000";
    				kunjunganSpesialis="900,000";
    				biayaPerawatanPribadi="150,000";
    				biayaAmbulans="90,000";
    				biayaBAPerawatan="1,850,000";
             		maksPenggantian="300,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 10 ){ // plan J
             		jenisSantunan="Plan J-700";
             		biayaInap="700,000";
             		biayaIcu="1,400,000";
             		anekaPerawatan="7,750,000";
    				biayaOP="75,000,000";
    				kunjungan="170,000";
    				kunjunganSpesialis="1,020,000";
    				biayaPerawatanPribadi="170,000";
    				biayaAmbulans="100,000";
    				biayaBAPerawatan="2,150,000";
             		maksPenggantian="350,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 11 ){ // plan K
             		jenisSantunan="Plan K-800";
             		biayaInap="800,000";
             		biayaIcu="1,600,000";
             		anekaPerawatan="8,750,000";
    				biayaOP="85,500,000";
    				kunjungan="190,000";
    				kunjunganSpesialis="1,140,000";
    				biayaPerawatanPribadi="190,000";
    				biayaAmbulans="110,000";
    				biayaBAPerawatan="2,450,000";
             		maksPenggantian="400,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 12 ){ // plan L
             		jenisSantunan="Plan L-900";
             		biayaInap="900,000";
             		biayaIcu="1,800,000";
             		anekaPerawatan="9,750,000";
    				biayaOP="96,000,000";
    				kunjungan="210,000";
    				kunjunganSpesialis="1,260,000";
    				biayaPerawatanPribadi="210,000";
    				biayaAmbulans="120,000";
    				biayaBAPerawatan="2,750,000";
             		maksPenggantian="450,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 13 ){ // plan M
             		jenisSantunan="Plan M-1000";
             		biayaInap="1,000,000";
             		biayaIcu="2,000,000";
             		anekaPerawatan="10,750,000";
    				biayaOP="106,500,000";
    				kunjungan="230,000";
    				kunjunganSpesialis="1,380,000";
    				biayaPerawatanPribadi="230,000";
    				biayaAmbulans="130,000";
    				biayaBAPerawatan="3,050,000";
             		maksPenggantian="500,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 14 ){ // plan N
             		jenisSantunan="Plan N-1500";
             		biayaInap="1,500,000";
             		biayaIcu="3,000,000";
             		anekaPerawatan="15,750,000";
    				biayaOP="159,000,000";
    				kunjungan="330,000";
    				kunjunganSpesialis="1,980,000";
    				biayaPerawatanPribadi="330,000";
    				biayaAmbulans="180,000";
    				biayaBAPerawatan="4,550,000";
             		maksPenggantian="750,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 15 ){ // plan O
             		jenisSantunan="Plan O-2000";
             		biayaInap="2,000,000";
             		biayaIcu="4,000,000";
             		anekaPerawatan="20,750,000";
    				biayaOP="211,500,000";
    				kunjungan="430,000";
    				kunjunganSpesialis="2,580,000";
    				biayaPerawatanPribadi="430,000";
    				biayaAmbulans="230,000";
    				biayaBAPerawatan="6,050,000";
             		maksPenggantian="1,000,000,000";
             	}           
             	 params = new HashMap<String, Object>();
                  params.put( "subject", "Jenis Santunan" );
                  params.put( "descr", jenisSantunan  );
                  params.put( "detail", ""  );
                  result.add( params );
                  
                  params = new HashMap<String, Object>();
                  params.put( "subject", "- Biaya Kamar dan Menginap" );
                  params.put( "descr", biayaInap  );
                  params.put( "detail", "maks 365 hari/tahun"  );
                  result.add( params );
                  
                  params = new HashMap<String, Object>();
                  params.put( "subject", "- Biaya Kamar ICU/ICCU" );
                  params.put( "descr", biayaIcu  );
                  params.put( "detail", "maks 60 hari/tahun"  );
                  result.add( params );
                  
             	 params = new HashMap<String, Object>();
                  params.put( "subject", "- Biaya Aneka Perawatan di RS" );
                  params.put( "descr", anekaPerawatan  );
                  params.put( "detail", "per kejadian"  );
                  result.add( params );
                  
             	 params = new HashMap<String, Object>();
                  params.put( "subject", "- Biaya Operasi/Pembedahan" );
                  params.put( "descr", biayaOP  );
                  params.put( "detail", ""  );
                  result.add( params );
                  
                 params = new HashMap<String, Object>();
                  params.put( "subject", "- Biaya Kunjungan Dokter di RS – per hari" );
                  params.put( "descr", kunjungan  );
                  params.put( "detail", "maks 1 kunjungan/hari"  );
                  result.add( params );
                  
                 params = new HashMap<String, Object>();
                  params.put( "subject", "- Biaya Konsultasi Dokter Spesialis di RS" );
                  params.put( "descr", kunjunganSpesialis  );
                  params.put( "detail", "per kejadian"  );
                  result.add( params );
                  
             	 params = new HashMap<String, Object>();
                  params.put( "subject", "- Biaya Perawat Pribadi – per hari" );
                  params.put( "descr", biayaPerawatanPribadi  );
                  params.put( "detail", "Max 30 hari"  );
                  result.add( params );
                  
             	 params = new HashMap<String, Object>();
                  params.put( "subject", "- Biaya Ambulance" );
                  params.put( "descr", biayaAmbulans );
                  params.put( "detail", "per kejadian" );
                  result.add( params );
                  
             	 params = new HashMap<String, Object>();
                  params.put( "subject", "Biaya Sebelum dan Sesudah Perawatan di RS" );
                  params.put( "descr", biayaBAPerawatan  );
                  params.put( "detail", "30 hari sebelum RI dan 30 hari setelah RI"  );
                  result.add( params ); 
                 
             	 params = new HashMap<String, Object>();
                  params.put( "subject", "Batas Maks. Penggantian pertahun" );
                  params.put( "descr", maksPenggantian  );
                  params.put( "detail", ""  );
                  result.add( params ); 
    		}
    	}
    	
    	return result;
    }
    
    public List<Map<String, Object>> getRiderEkaSehatInnerLimitSyariahListMap( )
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatInnerLimitFlag() ) )
    	{
    		if( cepr01030103Form.getEkaSehatInnerLimitCd() != null && cepr01030103Form.getEkaSehatInnerLimitCd() > 0 )
    		{
    			Map<String, Object> params;
    			String jenisSantunan = null;
         		String biayaInap = null;
         		String biayaIcu = null;
         		String anekaPerawatan = null;
    			String biayaOP = null;
    			String kunjungan = null;
    			String kunjunganSpesialis = null;
    			String biayaPerawatanPribadi = null;
    			String biayaAmbulans = null;
    			String biayaBAPerawatan = null;
         		String maksPenggantian = null;
         		if( cepr01030103Form.getEkaSehatInnerLimitCd() == 1 ){ // plan A
             		jenisSantunan="Plan A-100";
             		biayaInap="100,000";
             		biayaIcu="200,000";
             		anekaPerawatan="1,750,000";
    				biayaOP="12,000,000";
    				kunjungan="50,000";
    				kunjunganSpesialis="300,000";
    				biayaPerawatanPribadi="50,000";
    				biayaAmbulans="40,000";
    				biayaBAPerawatan="350,000";
             		maksPenggantian="50,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 2 ){ // plan B
             		jenisSantunan="Plan B-150";
             		biayaInap="150,000";
             		biayaIcu="300,000";
             		anekaPerawatan="2,250,000";
    				biayaOP="17,250,000";
    				kunjungan="60,000";
    				kunjunganSpesialis="360,000";
    				biayaPerawatanPribadi="60,000";
    				biayaAmbulans="45,000";
    				biayaBAPerawatan="500,000";
             		maksPenggantian="75,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 3 ){ // plan C
             		jenisSantunan="Plan C-200";
             		biayaInap="200,000";
             		biayaIcu="400,000";
             		anekaPerawatan="2,750,000";
    				biayaOP="22,500,000";
    				kunjungan="70,000";
    				kunjunganSpesialis="420,000";
    				biayaPerawatanPribadi="70,000";
    				biayaAmbulans="50,000";
    				biayaBAPerawatan="650,000";
             		maksPenggantian="100,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 4 ){ // plan D
             		jenisSantunan="Plan D-250";
             		biayaInap="250,000";
             		biayaIcu="500,000";
             		anekaPerawatan="3,250,000";
    				biayaOP="27,750,000";
    				kunjungan="80,000";
    				kunjunganSpesialis="480,000";
    				biayaPerawatanPribadi="80,000";
    				biayaAmbulans="55,000";
    				biayaBAPerawatan="800,000";
             		maksPenggantian="125,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 5 ){ // plan E
             		jenisSantunan="Plan E-300";
             		biayaInap="300,000";
             		biayaIcu="600,000";
             		anekaPerawatan="3,750,000";
    				biayaOP="33,000,000";
    				kunjungan="90,000";
    				kunjunganSpesialis="540,000";
    				biayaPerawatanPribadi="90,000";
    				biayaAmbulans="60,000";
    				biayaBAPerawatan="950,000";
             		maksPenggantian="150,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 6 ){ // plan F
             		jenisSantunan="Plan F-350";
             		biayaInap="350,000";
             		biayaIcu="700,000";
             		anekaPerawatan="4,250,000";
    				biayaOP="38,250,000";
    				kunjungan="100,000";
    				kunjunganSpesialis="600,000";
    				biayaPerawatanPribadi="100,000";
    				biayaAmbulans="65,000";
    				biayaBAPerawatan="1,100,000";
             		maksPenggantian="175,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 7 ){ // plan G
             		jenisSantunan="Plan G-400";
             		biayaInap="400,000";
             		biayaIcu="800,000";
             		anekaPerawatan="4,750,000";
    				biayaOP="43,500,000";
    				kunjungan="110,000";
    				kunjunganSpesialis="660,000";
    				biayaPerawatanPribadi="110,000";
    				biayaAmbulans="70,000";
    				biayaBAPerawatan="1,250,000";
             		maksPenggantian="200,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 8 ){ // plan H
             		jenisSantunan="Plan H-500";
             		biayaInap="500,000";
             		biayaIcu="1.000,000";
             		anekaPerawatan="5,750,000";
    				biayaOP="54,000,000";
    				kunjungan="130,000";
    				kunjunganSpesialis="780,000";
    				biayaPerawatanPribadi="130,000";
    				biayaAmbulans="80,000";
    				biayaBAPerawatan="1,550,000";
             		maksPenggantian="250,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 9 ){ // plan I
             		jenisSantunan="Plan I-600";
             		biayaInap="600,000";
             		biayaIcu="1,200,000";
             		anekaPerawatan="6,750,000";
    				biayaOP="64,500,000";
    				kunjungan="150,000";
    				kunjunganSpesialis="900,000";
    				biayaPerawatanPribadi="150,000";
    				biayaAmbulans="90,000";
    				biayaBAPerawatan="1,850,000";
             		maksPenggantian="300,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 10 ){ // plan J
             		jenisSantunan="Plan J-700";
             		biayaInap="700,000";
             		biayaIcu="1,400,000";
             		anekaPerawatan="7,750,000";
    				biayaOP="75,000,000";
    				kunjungan="170,000";
    				kunjunganSpesialis="1,020,000";
    				biayaPerawatanPribadi="170,000";
    				biayaAmbulans="100,000";
    				biayaBAPerawatan="2,150,000";
             		maksPenggantian="350,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 11 ){ // plan K
             		jenisSantunan="Plan K-800";
             		biayaInap="800,000";
             		biayaIcu="1,600,000";
             		anekaPerawatan="8,750,000";
    				biayaOP="85,500,000";
    				kunjungan="190,000";
    				kunjunganSpesialis="1,140,000";
    				biayaPerawatanPribadi="190,000";
    				biayaAmbulans="110,000";
    				biayaBAPerawatan="2,450,000";
             		maksPenggantian="400,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 12 ){ // plan L
             		jenisSantunan="Plan L-900";
             		biayaInap="900,000";
             		biayaIcu="1,800,000";
             		anekaPerawatan="9,750,000";
    				biayaOP="96,000,000";
    				kunjungan="210,000";
    				kunjunganSpesialis="1,260,000";
    				biayaPerawatanPribadi="210,000";
    				biayaAmbulans="120,000";
    				biayaBAPerawatan="2,750,000";
             		maksPenggantian="450,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 13 ){ // plan M
             		jenisSantunan="Plan M-1000";
             		biayaInap="1,000,000";
             		biayaIcu="2,000,000";
             		anekaPerawatan="10,750,000";
    				biayaOP="106,500,000";
    				kunjungan="230,000";
    				kunjunganSpesialis="1,380,000";
    				biayaPerawatanPribadi="230,000";
    				biayaAmbulans="130,000";
    				biayaBAPerawatan="3,050,000";
             		maksPenggantian="500,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 14 ){ // plan N
             		jenisSantunan="Plan N-1500";
             		biayaInap="1,500,000";
             		biayaIcu="3,000,000";
             		anekaPerawatan="15,750,000";
    				biayaOP="159,000,000";
    				kunjungan="330,000";
    				kunjunganSpesialis="1,980,000";
    				biayaPerawatanPribadi="330,000";
    				biayaAmbulans="180,000";
    				biayaBAPerawatan="4,550,000";
             		maksPenggantian="750,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 15 ){ // plan O
             		jenisSantunan="Plan O-2000";
             		biayaInap="2,000,000";
             		biayaIcu="4,000,000";
             		anekaPerawatan="20,750,000";
    				biayaOP="211,500,000";
    				kunjungan="430,000";
    				kunjunganSpesialis="2,580,000";
    				biayaPerawatanPribadi="430,000";
    				biayaAmbulans="230,000";
    				biayaBAPerawatan="6,050,000";
             		maksPenggantian="1,000,000,000";
             	}           
             	 params = new HashMap<String, Object>();
                  params.put( "subject", "Jenis Santunan" );
                  params.put( "descr", jenisSantunan  );
                  params.put( "detail", ""  );
                  result.add( params );
                  
                  params = new HashMap<String, Object>();
                  params.put( "subject", "- Biaya Kamar dan Menginap" );
                  params.put( "descr", biayaInap  );
                  params.put( "detail", "maks 365 hari/tahun"  );
                  result.add( params );
                  
                  params = new HashMap<String, Object>();
                  params.put( "subject", "- Biaya Kamar ICU/ICCU" );
                  params.put( "descr", biayaIcu  );
                  params.put( "detail", "maks 60 hari/tahun"  );
                  result.add( params );
                  
             	 params = new HashMap<String, Object>();
                  params.put( "subject", "- Biaya Aneka Perawatan di RS" );
                  params.put( "descr", anekaPerawatan  );
                  params.put( "detail", "per kejadian"  );
                  result.add( params );
                  
             	 params = new HashMap<String, Object>();
                  params.put( "subject", "- Biaya Operasi/Pembedahan" );
                  params.put( "descr", biayaOP  );
                  params.put( "detail", ""  );
                  result.add( params );
                  
                 params = new HashMap<String, Object>();
                  params.put( "subject", "- Biaya Kunjungan Dokter di RS – per hari" );
                  params.put( "descr", kunjungan  );
                  params.put( "detail", "maks 1 kunjungan/hari"  );
                  result.add( params );
                  
                 params = new HashMap<String, Object>();
                  params.put( "subject", "- Biaya Konsultasi Dokter Spesialis di RS" );
                  params.put( "descr", kunjunganSpesialis  );
                  params.put( "detail", "per kejadian"  );
                  result.add( params );
                  
             	 params = new HashMap<String, Object>();
                  params.put( "subject", "- Biaya Perawat Pribadi – per hari" );
                  params.put( "descr", biayaPerawatanPribadi  );
                  params.put( "detail", "Max 30 hari"  );
                  result.add( params );
                  
             	 params = new HashMap<String, Object>();
                  params.put( "subject", "- Biaya Ambulance" );
                  params.put( "descr", biayaAmbulans );
                  params.put( "detail", "per kejadian" );
                  result.add( params );
                  
             	 params = new HashMap<String, Object>();
                  params.put( "subject", "Biaya Sebelum dan Sesudah Perawatan di RS" );
                  params.put( "descr", biayaBAPerawatan  );
                  params.put( "detail", "30 hari sebelum RI dan 30 hari setelah RI"  );
                  result.add( params ); 
                 
             	 params = new HashMap<String, Object>();
                  params.put( "subject", "Batas Maks. Penggantian pertahun" );
                  params.put( "descr", maksPenggantian  );
                  params.put( "detail", ""  );
                  result.add( params ); 
    		}
    	}
    	
    	return result;
    }
    
    public String checkParticipantLadiesMedExpense()
    {
       String result = null;
       List<ParticipantVO> participantVOList = cepr01030111Form.getParticipantVOList();
       for( int i = 0 ; i < participantVOList.size() ; i ++ )
       {
    	   if( participantVOList.get(i).getName() != null && !"".equals( participantVOList.get(i).getName() ) ){
    		   result = "yes";
    		   break;
    	   }else{
    		   result = "no";
    	   }
       }
       return result;
    }
    
    public List<Map<String, Object>> getRiderLadiesMedExpenseListMap( ) //belum selesai membedakan Idr dan Usd
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesMedExpenseFlag() ) )
    	{
    		if( cepr01030103Form.getLadiesMedExpenseCd() != null && cepr01030103Form.getLadiesMedExpenseCd() > 0 )
    		{
    			Map<String, Object> params;
    			String jenisSantunan = null;
         		String biayaInap = null;
         		String biayaIcu = null;
         		String anekaPerawatan = null;
    			String biayaOP = null;
    			String kunjungan = null;
    			String kunjunganSpesialis = null;
    			String biayaBAPerawatan = null;
    			String manfaatKemoterapi = null;
    			String biayaSekaligus = null;
				String maksPerkejadian = null;
         		String maksPenggantian = null;
    			if(cepr01030102Form.getCurrencyCd().equals("01")){
    				if( cepr01030103Form.getLadiesMedExpenseCd() == 1 ){ // plan A
                 		jenisSantunan="Plan A";
                 		biayaInap="200,000";
                 		biayaIcu="400,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="4,000,000";
                 		biayaSekaligus="2,000,000";
                 		maksPerkejadian="50,000,000";
                 		maksPenggantian="100,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 2 ){ // plan B
                 		jenisSantunan="Plan B";
                 		biayaInap="400,000";
                 		biayaIcu="800,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="8,000,000";
                 		biayaSekaligus="4,000,000";
                 		maksPerkejadian="100,000,000";
                 		maksPenggantian="200,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 3 ){ // plan C
                 		jenisSantunan="Plan C";
                 		biayaInap="600,000";
                 		biayaIcu="1,200,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="12,000,000";
                 		biayaSekaligus="6,000,000";
                 		maksPerkejadian="150,000,000";
                 		maksPenggantian="300,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 4 ){ // plan D
                 		jenisSantunan="Plan D";
                 		biayaInap="800,000";
                 		biayaIcu="1,600,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="16,000,000";
                 		biayaSekaligus="8,000,000";
                 		maksPerkejadian="200,000,000";
                 		maksPenggantian="400,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 5 ){ // plan E
                 		jenisSantunan="Plan E";
                 		biayaInap="1,000,000";
                 		biayaIcu="2,000,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="20,000,000";
                 		biayaSekaligus="10,000,000";
                 		maksPerkejadian="250,000,000";
                 		maksPenggantian="500,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 6 ){ // plan F
                 		jenisSantunan="Plan F";
                 		biayaInap="1,500,000";
                 		biayaIcu="3,000,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="30,000,000";
                 		biayaSekaligus="15,000,000";
                 		maksPerkejadian="375,000,000";
                 		maksPenggantian="750,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 7 ){ // plan G
                 		jenisSantunan="Plan G";
                 		biayaInap="2,000,000";
                 		biayaIcu="4,000,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="40,000,000";
                 		biayaSekaligus="20,000,000";
                 		maksPerkejadian="500,000,000";
                 		maksPenggantian="1,000,000,000";
                 	}
                 	
    			}
    			else
    			{
    				if( cepr01030103Form.getLadiesMedExpenseCd() == 1 ){ // plan A
                 		jenisSantunan="Plan A";
                 		biayaInap="20";
                 		biayaIcu="40";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="400";
                 		biayaSekaligus="200";
                 		maksPerkejadian="5,000";
                 		maksPenggantian="10,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 2 ){ // plan B
                 		jenisSantunan="Plan B";
                 		biayaInap="40";
                 		biayaIcu="80";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="800";
                 		biayaSekaligus="400";
                 		maksPerkejadian="10,000";
                 		maksPenggantian="20,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 3 ){ // plan C
                 		jenisSantunan="Plan C";
                 		biayaInap="60";
                 		biayaIcu="120";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="1,200";
                 		biayaSekaligus="600";
                 		maksPerkejadian="15,000";
                 		maksPenggantian="30,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 4 ){ // plan D
                 		jenisSantunan="Plan D";
                 		biayaInap="80";
                 		biayaIcu="160";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="1,600";
                 		biayaSekaligus="800";
                 		maksPerkejadian="20,000";
                 		maksPenggantian="40,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 5 ){ // plan E
                 		jenisSantunan="Plan E";
                 		biayaInap="100";
                 		biayaIcu="200";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="2,000";
                 		biayaSekaligus="1,000";
                 		maksPerkejadian="25,000";
                 		maksPenggantian="50,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 6 ){ // plan F
                 		jenisSantunan="Plan F";
                 		biayaInap="150";
                 		biayaIcu="300";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="3,000";
                 		biayaSekaligus="1,500";
                 		maksPerkejadian="37,500";
                 		maksPenggantian="75,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 7 ){ // plan G
                 		jenisSantunan="Plan G";
                 		biayaInap="200";
                 		biayaIcu="400";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="4,000";
                 		biayaSekaligus="2,000";
                 		maksPerkejadian="50,000";
                 		maksPenggantian="100,000";
                 	}
    			}
    			 params = new HashMap<String, Object>();
                 params.put( "subject", "Jenis Santunan" );
                 params.put( "descr", jenisSantunan  );
                 params.put( "detail", ""  );
                 params.put( "maksPerkejadian", maksPerkejadian );
                 params.put( "maksPenggantian", maksPenggantian );
                 result.add( params );
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Kamar dan Menginap" );
                 params.put( "descr", biayaInap  );
                 params.put( "detail", "maks 365 hari/tahun"  );
                 params.put( "maksPerkejadian", maksPerkejadian );
                 params.put( "maksPenggantian", maksPenggantian );
                 result.add( params );
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Kamar ICU/ICCU" );
                 params.put( "descr", biayaIcu  );
                 params.put( "detail", "maks 60 hari/tahun"  );
                 params.put( "maksPerkejadian", maksPerkejadian );
                 params.put( "maksPenggantian", maksPenggantian );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Aneka Perawatan di RS" );
                 params.put( "descr", anekaPerawatan  );
                 params.put( "detail", ""  );
                 params.put( "maksPerkejadian", maksPerkejadian );
                 params.put( "maksPenggantian", maksPenggantian );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Operasi/Pembedahan" );
                 params.put( "descr", biayaOP  );
                 params.put( "detail", ""  );
                 params.put( "maksPerkejadian", maksPerkejadian );
                 params.put( "maksPenggantian", maksPenggantian );
                 result.add( params );
                 
                params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Kunjungan Dokter/Dokter Ahli di RS" );
                 params.put( "descr", kunjungan  );
                 params.put( "detail", ""  );
                 params.put( "maksPerkejadian", maksPerkejadian );
                 params.put( "maksPenggantian", maksPenggantian );
                 result.add( params );
                 
//                params = new HashMap<String, Object>();
//                 params.put( "subject", "- Biaya Konsultasi Dokter Spesialis di Rumah Sakit" );
//                 params.put( "descr", kunjunganSpesialis  );
//                 params.put( "detail", "per kejadian"  );
//                 params.put( "maksPerkejadian", maksPerkejadian );
//                 params.put( "maksPenggantian", maksPenggantian );
//                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Sebelum dan Sesudah Perawatan Rumah Sakit" );
                 params.put( "descr", biayaBAPerawatan  );
                 params.put( "detail", "7 hari sebelum Rawat Inap dan 30 hari setelah Rawat Inap"  );
                 params.put( "maksPerkejadian", maksPerkejadian );
                 params.put( "maksPenggantian", maksPenggantian );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Manfaat Kemoterapi" );
                 params.put( "descr", manfaatKemoterapi );
                 params.put( "detail", ""  );
                 params.put( "maksPerkejadian", maksPerkejadian );
                 params.put( "maksPenggantian", maksPenggantian );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Pengobatan Sekaligus" );
                 params.put( "descr", biayaSekaligus  );
                 params.put( "detail", ""  );
                 params.put( "maksPerkejadian", maksPerkejadian );
                 params.put( "maksPenggantian", maksPenggantian );
                 result.add( params );
                 
    		}
    	}
    	
    	return result;
    }
    
    
    public List<Map<String, Object>> getRiderLadiesMedExpenseSyariahListMap( ) //belum selesai membedakan Idr dan Usd
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesMedExpenseFlag() ) )
    	{
    		if( cepr01030103Form.getLadiesMedExpenseCd() != null && cepr01030103Form.getLadiesMedExpenseCd() > 0 )
    		{
    			Map<String, Object> params;
    			String jenisSantunan = null;
         		String biayaInap = null;
         		String biayaIcu = null;
         		String anekaPerawatan = null;
    			String biayaOP = null;
    			String kunjungan = null;
    			String kunjunganSpesialis = null;
    			String biayaBAPerawatan = null;
    			String manfaatKemoterapi = null;
    			String biayaSekaligus = null;
				String maksPerkejadian = null;
         		String maksPenggantian = null;
    			if(cepr01030102Form.getCurrencyCd().equals("01")){
    				if( cepr01030103Form.getLadiesMedExpenseCd() == 1 ){ // plan A
                 		jenisSantunan="Plan A";
                 		biayaInap="200,000";
                 		biayaIcu="400,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="4,000,000";
                 		biayaSekaligus="2,000,000";
                 		maksPerkejadian="50,000,000";
                 		maksPenggantian="100,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 2 ){ // plan B
                 		jenisSantunan="Plan B";
                 		biayaInap="400,000";
                 		biayaIcu="800,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="8,000,000";
                 		biayaSekaligus="4,000,000";
                 		maksPerkejadian="100,000,000";
                 		maksPenggantian="200,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 3 ){ // plan C
                 		jenisSantunan="Plan C";
                 		biayaInap="600,000";
                 		biayaIcu="1,200,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="12,000,000";
                 		biayaSekaligus="6,000,000";
                 		maksPerkejadian="150,000,000";
                 		maksPenggantian="300,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 4 ){ // plan D
                 		jenisSantunan="Plan D";
                 		biayaInap="800,000";
                 		biayaIcu="1,600,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="16,000,000";
                 		biayaSekaligus="8,000,000";
                 		maksPerkejadian="200,000,000";
                 		maksPenggantian="400,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 5 ){ // plan E
                 		jenisSantunan="Plan E";
                 		biayaInap="1,000,000";
                 		biayaIcu="2,000,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="20,000,000";
                 		biayaSekaligus="10,000,000";
                 		maksPerkejadian="250,000,000";
                 		maksPenggantian="500,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 6 ){ // plan F
                 		jenisSantunan="Plan F";
                 		biayaInap="1,500,000";
                 		biayaIcu="3,000,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="30,000,000";
                 		biayaSekaligus="15,000,000";
                 		maksPerkejadian="375,000,000";
                 		maksPenggantian="750,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 7 ){ // plan G
                 		jenisSantunan="Plan G";
                 		biayaInap="2,000,000";
                 		biayaIcu="4,000,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="40,000,000";
                 		biayaSekaligus="20,000,000";
                 		maksPerkejadian="500,000,000";
                 		maksPenggantian="1,000,000,000";
                 	}
                 	
    			}
    			else
    			{
    				if( cepr01030103Form.getLadiesMedExpenseCd() == 1 ){ // plan A
                 		jenisSantunan="Plan A";
                 		biayaInap="20";
                 		biayaIcu="40";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="400";
                 		biayaSekaligus="200";
                 		maksPerkejadian="5,000";
                 		maksPenggantian="10,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 2 ){ // plan B
                 		jenisSantunan="Plan B";
                 		biayaInap="40";
                 		biayaIcu="80";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="800";
                 		biayaSekaligus="400";
                 		maksPerkejadian="10,000";
                 		maksPenggantian="20,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 3 ){ // plan C
                 		jenisSantunan="Plan C";
                 		biayaInap="60";
                 		biayaIcu="120";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="1,200";
                 		biayaSekaligus="600";
                 		maksPerkejadian="15,000";
                 		maksPenggantian="30,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 4 ){ // plan D
                 		jenisSantunan="Plan D";
                 		biayaInap="80";
                 		biayaIcu="160";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="1,600";
                 		biayaSekaligus="800";
                 		maksPerkejadian="20,000";
                 		maksPenggantian="40,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 5 ){ // plan E
                 		jenisSantunan="Plan E";
                 		biayaInap="100";
                 		biayaIcu="200";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="2,000";
                 		biayaSekaligus="1,000";
                 		maksPerkejadian="25,000";
                 		maksPenggantian="50,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 6 ){ // plan F
                 		jenisSantunan="Plan F";
                 		biayaInap="150";
                 		biayaIcu="300";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="3,000";
                 		biayaSekaligus="1,500";
                 		maksPerkejadian="37,500";
                 		maksPenggantian="75,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 7 ){ // plan G
                 		jenisSantunan="Plan G";
                 		biayaInap="200";
                 		biayaIcu="400";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="4,000";
                 		biayaSekaligus="2,000";
                 		maksPerkejadian="50,000";
                 		maksPenggantian="100,000";
                 	}
    			}
    			 params = new HashMap<String, Object>();
                 params.put( "subject", "Jenis Santunan" );
                 params.put( "descr", jenisSantunan  );
                 params.put( "detail", ""  );
                 params.put( "maksPerkejadian", maksPerkejadian );
                 params.put( "maksPenggantian", maksPenggantian );
                 result.add( params );
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Kamar dan Menginap" );
                 params.put( "descr", biayaInap  );
                 params.put( "detail", "maks 365 hari/tahun"  );
                 params.put( "maksPerkejadian", maksPerkejadian );
                 params.put( "maksPenggantian", maksPenggantian );
                 result.add( params );
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Kamar ICU/ICCU" );
                 params.put( "descr", biayaIcu  );
                 params.put( "detail", "maks 60 hari/tahun"  );
                 params.put( "maksPerkejadian", maksPerkejadian );
                 params.put( "maksPenggantian", maksPenggantian );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Aneka Perawatan di RS" );
                 params.put( "descr", anekaPerawatan  );
                 params.put( "detail", ""  );
                 params.put( "maksPerkejadian", maksPerkejadian );
                 params.put( "maksPenggantian", maksPenggantian );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Operasi/Pembedahan" );
                 params.put( "descr", biayaOP  );
                 params.put( "detail", ""  );
                 params.put( "maksPerkejadian", maksPerkejadian );
                 params.put( "maksPenggantian", maksPenggantian );
                 result.add( params );
                 
                params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Kunjungan Dokter/Dokter Ahli di RS" );
                 params.put( "descr", kunjungan  );
                 params.put( "detail", ""  );
                 params.put( "maksPerkejadian", maksPerkejadian );
                 params.put( "maksPenggantian", maksPenggantian );
                 result.add( params );
                 
//                params = new HashMap<String, Object>();
//                 params.put( "subject", "- Biaya Konsultasi Dokter Spesialis di Rumah Sakit" );
//                 params.put( "descr", kunjunganSpesialis  );
//                 params.put( "detail", "per kejadian"  );
//                 params.put( "maksPerkejadian", maksPerkejadian );
//                 params.put( "maksPenggantian", maksPenggantian );
//                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Sebelum dan Sesudah Perawatan Rumah Sakit" );
                 params.put( "descr", biayaBAPerawatan  );
                 params.put( "detail", "7 hari sebelum Rawat Inap dan 30 hari setelah Rawat Inap"  );
                 params.put( "maksPerkejadian", maksPerkejadian );
                 params.put( "maksPenggantian", maksPenggantian );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Manfaat Kemoterapi" );
                 params.put( "descr", manfaatKemoterapi );
                 params.put( "detail", ""  );
                 params.put( "maksPerkejadian", maksPerkejadian );
                 params.put( "maksPenggantian", maksPenggantian );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Pengobatan Sekaligus" );
                 params.put( "descr", biayaSekaligus  );
                 params.put( "detail", ""  );
                 params.put( "maksPerkejadian", maksPerkejadian );
                 params.put( "maksPenggantian", maksPenggantian );
                 result.add( params );
                 
    		}
    	}
    	
    	return result;
    }
    
    public List<Map<String, Object>> getRiderLadiesMedExpenseInnerLimitListMap( ) 
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesMedExpenseInnerLimitFlag() ) )
    	{
    		if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() != null && cepr01030103Form.getLadiesMedExpenseInnerLimitCd() > 0 )
    		{
    			Map<String, Object> params;
    			String jenisSantunan = null;
         		String biayaInap = null;
         		String biayaIcu = null;
         		String anekaPerawatan = null;
    			String biayaOP = null;
    			String kunjungan = null;
    			String kunjunganSpesialis = null;
    			String biayaBAPerawatan = null;
    			String manfaatKemoterapi = null;
    			String biayaSekaligus = null;
				String maksPerkejadian = null;
         		String maksPenggantian = null;
    			if(cepr01030102Form.getCurrencyCd().equals("01")){
    				if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 1 ){ // plan A
                 		jenisSantunan="Plan A";
                 		biayaInap="200,000";
                 		biayaIcu="400,000";
                 		anekaPerawatan = "5,500,000";
                 		biayaOP="45,000,000";
                 		kunjungan="75,000";
                 		kunjunganSpesialis="630,000";
                 		biayaBAPerawatan="650,000";
                 		manfaatKemoterapi="4,000,000";
                 		biayaSekaligus="2,000,000";
                 		maksPerkejadian="50,000,000";
                 		maksPenggantian="100,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 2 ){ // plan B
                 		jenisSantunan="Plan B";
                 		biayaInap="400,000";
                 		biayaIcu="800,000";
                 		anekaPerawatan = "9,500,000";
                 		biayaOP="87,000,000";
                 		kunjungan="125,000";
                 		kunjunganSpesialis="990,000";
                 		biayaBAPerawatan="1,250,000";
                 		manfaatKemoterapi="8,000,000";
                 		biayaSekaligus="4,000,000";
                 		maksPerkejadian="100,000,000";
                 		maksPenggantian="200,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 3 ){ // plan C
                 		jenisSantunan="Plan C";
                 		biayaInap="600,000";
                 		biayaIcu="1,200,000";
                 		anekaPerawatan = "13,500,000";
                 		biayaOP="129,000,000";
                 		kunjungan="150,000";
                 		kunjunganSpesialis="1,350,000";
                 		biayaBAPerawatan="1,850,000";
                 		manfaatKemoterapi="12,000,000";
                 		biayaSekaligus="6,000,000";
                 		maksPerkejadian="150,000,000";
                 		maksPenggantian="300,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 4 ){ // plan D
                 		jenisSantunan="Plan D";
                 		biayaInap="800,000";
                 		biayaIcu="1,600,000";
                 		anekaPerawatan = "17,500,000";
                 		biayaOP="171,000,000";
                 		kunjungan="225,000";
                 		kunjunganSpesialis="1,710,000";
                 		biayaBAPerawatan="2,450,000";
                 		manfaatKemoterapi="16,000,000";
                 		biayaSekaligus="8,000,000";
                 		maksPerkejadian="200,000,000";
                 		maksPenggantian="400,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 5 ){ // plan E
                 		jenisSantunan="Plan E";
                 		biayaInap="1,000,000";
                 		biayaIcu="2,000,000";
                 		anekaPerawatan = "21,500,000";
                 		biayaOP="213,000,000";
                 		kunjungan="250,000";
                 		kunjunganSpesialis="2,070,000";
                 		biayaBAPerawatan="3,050,000";
                 		manfaatKemoterapi="20,000,000";
                 		biayaSekaligus="10,000,000";
                 		maksPerkejadian="250,000,000";
                 		maksPenggantian="500,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 6 ){ // plan F
                 		jenisSantunan="Plan F";
                 		biayaInap="1,500,000";
                 		biayaIcu="3,000,000";
                 		anekaPerawatan = "31,500,000";
                 		biayaOP="318,000,000";
                 		kunjungan="350,000";
                 		kunjunganSpesialis="2,790,000";
                 		biayaBAPerawatan="4,250,000";
                 		manfaatKemoterapi="30,000,000";
                 		biayaSekaligus="15,000,000";
                 		maksPerkejadian="375,000,000";
                 		maksPenggantian="750,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 7 ){ // plan G
                 		jenisSantunan="Plan G";
                 		biayaInap="2,000,000";
                 		biayaIcu="4,000,000";
                 		anekaPerawatan = "41,500,000";
                 		biayaOP="423,000,000";
                 		kunjungan="450,000";
                 		kunjunganSpesialis="3,150,000";
                 		biayaBAPerawatan="4,850,000";
                 		manfaatKemoterapi="40,000,000";
                 		biayaSekaligus="20,000,000";
                 		maksPerkejadian="500,000,000";
                 		maksPenggantian="1,000,000,000";
                 	}
    			}
    			else
    			{
    				if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 1 ){ // plan A
                 		jenisSantunan="Plan A";
                 		biayaInap="20";
                 		biayaIcu="40";
                 		anekaPerawatan = "550";
                 		biayaOP="4,500";
                 		kunjungan="8";
                 		kunjunganSpesialis="63";
                 		biayaBAPerawatan="65";
                 		manfaatKemoterapi="400";
                 		biayaSekaligus="200";
                 		maksPerkejadian="5,000";
                 		maksPenggantian="10,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 2 ){ // plan B
                 		jenisSantunan="Plan B";
                 		biayaInap="40";
                 		biayaIcu="80";
                 		anekaPerawatan = "950";
                 		biayaOP="8,700";
                 		kunjungan="13";
                 		kunjunganSpesialis="99";
                 		biayaBAPerawatan="125";
                 		manfaatKemoterapi="800";
                 		biayaSekaligus="400";
                 		maksPerkejadian="10,000";
                 		maksPenggantian="20,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 3 ){ // plan C
                 		jenisSantunan="Plan C";
                 		biayaInap="60";
                 		biayaIcu="120";
                 		anekaPerawatan = "1,350";
                 		biayaOP="12,900";
                 		kunjungan="15";
                 		kunjunganSpesialis="135";
                 		biayaBAPerawatan="185";
                 		manfaatKemoterapi="1,200";
                 		biayaSekaligus="600";
                 		maksPerkejadian="15,000";
                 		maksPenggantian="30,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 4 ){ // plan D
                 		jenisSantunan="Plan D";
                 		biayaInap="80";
                 		biayaIcu="160";
                 		anekaPerawatan = "1,750";
                 		biayaOP="17,100";
                 		kunjungan="23";
                 		kunjunganSpesialis="171";
                 		biayaBAPerawatan="245";
                 		manfaatKemoterapi="1,600";
                 		biayaSekaligus="800";
                 		maksPerkejadian="20,000";
                 		maksPenggantian="40,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 5 ){ // plan E
                 		jenisSantunan="Plan E";
                 		biayaInap="100";
                 		biayaIcu="200";
                 		anekaPerawatan = "2,150";
                 		biayaOP="21,300";
                 		kunjungan="25";
                 		kunjunganSpesialis="207";
                 		biayaBAPerawatan="305";
                 		manfaatKemoterapi="2,000";
                 		biayaSekaligus="1,000";
                 		maksPerkejadian="25,000";
                 		maksPenggantian="50,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 6 ){ // plan F
                 		jenisSantunan="Plan F";
                 		biayaInap="150";
                 		biayaIcu="300";
                 		anekaPerawatan = "3,150";
                 		biayaOP="31,800";
                 		kunjungan="35";
                 		kunjunganSpesialis="279";
                 		biayaBAPerawatan="425";
                 		manfaatKemoterapi="3,000";
                 		biayaSekaligus="1,500";
                 		maksPerkejadian="37,500";
                 		maksPenggantian="75,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 7 ){ // plan G
                 		jenisSantunan="Plan G";
                 		biayaInap="200";
                 		biayaIcu="400";
                 		anekaPerawatan = "4,150";
                 		biayaOP="42,300";
                 		kunjungan="45";
                 		kunjunganSpesialis="315";
                 		biayaBAPerawatan="485";
                 		manfaatKemoterapi="4,000";
                 		biayaSekaligus="2,000";
                 		maksPerkejadian="50,000";
                 		maksPenggantian="100,000";
                 	}
    			}
    			 params = new HashMap<String, Object>();
                 params.put( "subject", "Jenis Santunan" );
                 params.put( "descr", jenisSantunan  );
                 params.put( "detail", ""  );
                 result.add( params );
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Kamar dan Menginap" );
                 params.put( "descr", biayaInap  );
                 params.put( "detail", "maks 365 hari/tahun"  );
                 result.add( params );
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Kamar ICU/ICCU" );
                 params.put( "descr", biayaIcu  );
                 params.put( "detail", "maks 60 hari/tahun"  );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Aneka Perawatan di RS" );
                 params.put( "descr", anekaPerawatan  );
                 params.put( "detail", "Per kejadian"  );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Operasi/Pembedahan" );
                 params.put( "descr", biayaOP  );
                 params.put( "detail", ""  );
                 result.add( params );
                 
                params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Kunjungan Dokter di Rumah Sakit – per hari" );
                 params.put( "descr", kunjungan  );
                 params.put( "detail", "maks 1 kunjungan/hari"  );
                 result.add( params );
                 
                params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Konsultasi Dokter Spesialis di Rumah Sakit" );
                 params.put( "descr", kunjunganSpesialis  );
                 params.put( "detail", "per kejadian"  );
                 result.add( params );
                 
               	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Kemoterapi" );
                 params.put( "descr", manfaatKemoterapi );
                 params.put( "detail", ""  );
                 result.add( params );
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Pengobatan Sekaligus" );
                 params.put( "descr", biayaSekaligus  );
                 params.put( "detail", ""  );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Sebelum dan Sesudah Perawatan Rumah Sakit" );
                 params.put( "descr", biayaBAPerawatan  );
                 params.put( "detail", "7 hari sebelum Rawat Inap dan 30 hari setelah Rawat Inap"  );
                 result.add( params );
                 
//                params = new HashMap<String, Object>();
//                 params.put( "subject", "- Batasan Klaim Per Kejadian" );
//                 params.put( "descr", maksPerkejadian  );
//                 params.put( "detail", ""  );
//                 result.add( params );
                 
                params = new HashMap<String, Object>();
                 params.put( "subject", "- Batasan maks.Penggantian pertahun" );
                 params.put( "descr", maksPenggantian  );
                 params.put( "detail", ""  );
                 result.add( params );
    		}
    	}
    	
    	return result;
    }
    
    
    public List<Map<String, Object>> getRiderLadiesMedExpenseInnerSyariahLimitListMap( ) 
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesMedExpenseInnerLimitFlag() ) )
    	{
    		if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() != null && cepr01030103Form.getLadiesMedExpenseInnerLimitCd() > 0 )
    		{
    			Map<String, Object> params;
    			String jenisSantunan = null;
         		String biayaInap = null;
         		String biayaIcu = null;
         		String anekaPerawatan = null;
    			String biayaOP = null;
    			String kunjungan = null;
    			String kunjunganSpesialis = null;
    			String biayaBAPerawatan = null;
    			String manfaatKemoterapi = null;
    			String biayaSekaligus = null;
				String maksPerkejadian = null;
         		String maksPenggantian = null;
    			if(cepr01030102Form.getCurrencyCd().equals("01")){
    				if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 1 ){ // plan A
                 		jenisSantunan="Plan A";
                 		biayaInap="200,000";
                 		biayaIcu="400,000";
                 		anekaPerawatan = "5,500,000";
                 		biayaOP="45,000,000";
                 		kunjungan="75,000";
                 		kunjunganSpesialis="630,000";
                 		biayaBAPerawatan="650,000";
                 		manfaatKemoterapi="4,000,000";
                 		biayaSekaligus="2,000,000";
                 		maksPerkejadian="50,000,000";
                 		maksPenggantian="100,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 2 ){ // plan B
                 		jenisSantunan="Plan B";
                 		biayaInap="400,000";
                 		biayaIcu="800,000";
                 		anekaPerawatan = "9,500,000";
                 		biayaOP="87,000,000";
                 		kunjungan="125,000";
                 		kunjunganSpesialis="990,000";
                 		biayaBAPerawatan="1,250,000";
                 		manfaatKemoterapi="8,000,000";
                 		biayaSekaligus="4,000,000";
                 		maksPerkejadian="100,000,000";
                 		maksPenggantian="200,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 3 ){ // plan C
                 		jenisSantunan="Plan C";
                 		biayaInap="600,000";
                 		biayaIcu="1,200,000";
                 		anekaPerawatan = "13,500,000";
                 		biayaOP="129,000,000";
                 		kunjungan="150,000";
                 		kunjunganSpesialis="1,350,000";
                 		biayaBAPerawatan="1,850,000";
                 		manfaatKemoterapi="12,000,000";
                 		biayaSekaligus="6,000,000";
                 		maksPerkejadian="150,000,000";
                 		maksPenggantian="300,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 4 ){ // plan D
                 		jenisSantunan="Plan D";
                 		biayaInap="800,000";
                 		biayaIcu="1,600,000";
                 		anekaPerawatan = "17,500,000";
                 		biayaOP="171,000,000";
                 		kunjungan="225,000";
                 		kunjunganSpesialis="1,710,000";
                 		biayaBAPerawatan="2,450,000";
                 		manfaatKemoterapi="16,000,000";
                 		biayaSekaligus="8,000,000";
                 		maksPerkejadian="200,000,000";
                 		maksPenggantian="400,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 5 ){ // plan E
                 		jenisSantunan="Plan E";
                 		biayaInap="1,000,000";
                 		biayaIcu="2,000,000";
                 		anekaPerawatan = "21,500,000";
                 		biayaOP="213,000,000";
                 		kunjungan="250,000";
                 		kunjunganSpesialis="2,070,000";
                 		biayaBAPerawatan="3,050,000";
                 		manfaatKemoterapi="20,000,000";
                 		biayaSekaligus="10,000,000";
                 		maksPerkejadian="250,000,000";
                 		maksPenggantian="500,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 6 ){ // plan F
                 		jenisSantunan="Plan F";
                 		biayaInap="1,500,000";
                 		biayaIcu="3,000,000";
                 		anekaPerawatan = "31,500,000";
                 		biayaOP="318,000,000";
                 		kunjungan="350,000";
                 		kunjunganSpesialis="2,790,000";
                 		biayaBAPerawatan="4,250,000";
                 		manfaatKemoterapi="30,000,000";
                 		biayaSekaligus="15,000,000";
                 		maksPerkejadian="375,000,000";
                 		maksPenggantian="750,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 7 ){ // plan G
                 		jenisSantunan="Plan G";
                 		biayaInap="2,000,000";
                 		biayaIcu="4,000,000";
                 		anekaPerawatan = "41,500,000";
                 		biayaOP="423,000,000";
                 		kunjungan="450,000";
                 		kunjunganSpesialis="3,150,000";
                 		biayaBAPerawatan="4,850,000";
                 		manfaatKemoterapi="40,000,000";
                 		biayaSekaligus="20,000,000";
                 		maksPerkejadian="500,000,000";
                 		maksPenggantian="1,000,000,000";
                 	}
    			}
    			else
    			{
    				if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 1 ){ // plan A
                 		jenisSantunan="Plan A";
                 		biayaInap="20";
                 		biayaIcu="40";
                 		anekaPerawatan = "550";
                 		biayaOP="4,500";
                 		kunjungan="8";
                 		kunjunganSpesialis="63";
                 		biayaBAPerawatan="65";
                 		manfaatKemoterapi="400";
                 		biayaSekaligus="200";
                 		maksPerkejadian="5,000";
                 		maksPenggantian="10,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 2 ){ // plan B
                 		jenisSantunan="Plan B";
                 		biayaInap="40";
                 		biayaIcu="80";
                 		anekaPerawatan = "950";
                 		biayaOP="8,700";
                 		kunjungan="13";
                 		kunjunganSpesialis="99";
                 		biayaBAPerawatan="125";
                 		manfaatKemoterapi="800";
                 		biayaSekaligus="400";
                 		maksPerkejadian="10,000";
                 		maksPenggantian="20,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 3 ){ // plan C
                 		jenisSantunan="Plan C";
                 		biayaInap="60";
                 		biayaIcu="120";
                 		anekaPerawatan = "1,350";
                 		biayaOP="12,900";
                 		kunjungan="15";
                 		kunjunganSpesialis="135";
                 		biayaBAPerawatan="185";
                 		manfaatKemoterapi="1,200";
                 		biayaSekaligus="600";
                 		maksPerkejadian="15,000";
                 		maksPenggantian="30,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 4 ){ // plan D
                 		jenisSantunan="Plan D";
                 		biayaInap="80";
                 		biayaIcu="160";
                 		anekaPerawatan = "1,750";
                 		biayaOP="17,100";
                 		kunjungan="23";
                 		kunjunganSpesialis="171";
                 		biayaBAPerawatan="245";
                 		manfaatKemoterapi="1,600";
                 		biayaSekaligus="800";
                 		maksPerkejadian="20,000";
                 		maksPenggantian="40,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 5 ){ // plan E
                 		jenisSantunan="Plan E";
                 		biayaInap="100";
                 		biayaIcu="200";
                 		anekaPerawatan = "2,150";
                 		biayaOP="21,300";
                 		kunjungan="25";
                 		kunjunganSpesialis="207";
                 		biayaBAPerawatan="305";
                 		manfaatKemoterapi="2,000";
                 		biayaSekaligus="1,000";
                 		maksPerkejadian="25,000";
                 		maksPenggantian="50,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 6 ){ // plan F
                 		jenisSantunan="Plan F";
                 		biayaInap="150";
                 		biayaIcu="300";
                 		anekaPerawatan = "3,150";
                 		biayaOP="31,800";
                 		kunjungan="35";
                 		kunjunganSpesialis="279";
                 		biayaBAPerawatan="425";
                 		manfaatKemoterapi="3,000";
                 		biayaSekaligus="1,500";
                 		maksPerkejadian="37,500";
                 		maksPenggantian="75,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 7 ){ // plan G
                 		jenisSantunan="Plan G";
                 		biayaInap="200";
                 		biayaIcu="400";
                 		anekaPerawatan = "4,150";
                 		biayaOP="42,300";
                 		kunjungan="45";
                 		kunjunganSpesialis="315";
                 		biayaBAPerawatan="485";
                 		manfaatKemoterapi="4,000";
                 		biayaSekaligus="2,000";
                 		maksPerkejadian="50,000";
                 		maksPenggantian="100,000";
                 	}
    			}
    			 params = new HashMap<String, Object>();
                 params.put( "subject", "Jenis Santunan" );
                 params.put( "descr", jenisSantunan  );
                 params.put( "detail", ""  );
                 result.add( params );
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Kamar dan Menginap" );
                 params.put( "descr", biayaInap  );
                 params.put( "detail", "maks 365 hari/tahun"  );
                 result.add( params );
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Kamar ICU/ICCU" );
                 params.put( "descr", biayaIcu  );
                 params.put( "detail", "maks 60 hari/tahun"  );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Aneka Perawatan di RS" );
                 params.put( "descr", anekaPerawatan  );
                 params.put( "detail", "Per kejadian"  );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Operasi/Pembedahan" );
                 params.put( "descr", biayaOP  );
                 params.put( "detail", ""  );
                 result.add( params );
                 
                params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Kunjungan Dokter di Rumah Sakit – per hari" );
                 params.put( "descr", kunjungan  );
                 params.put( "detail", "maks 1 kunjungan/hari"  );
                 result.add( params );
                 
                params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Konsultasi Dokter Spesialis di Rumah Sakit" );
                 params.put( "descr", kunjunganSpesialis  );
                 params.put( "detail", "per kejadian"  );
                 result.add( params );
                 
               	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Kemoterapi" );
                 params.put( "descr", manfaatKemoterapi );
                 params.put( "detail", ""  );
                 result.add( params );
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Pengobatan Sekaligus" );
                 params.put( "descr", biayaSekaligus  );
                 params.put( "detail", ""  );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Sebelum dan Sesudah Perawatan Rumah Sakit" );
                 params.put( "descr", biayaBAPerawatan  );
                 params.put( "detail", "7 hari sebelum Rawat Inap dan 30 hari setelah Rawat Inap"  );
                 result.add( params );
                 
//                params = new HashMap<String, Object>();
//                 params.put( "subject", "- Batasan Klaim Per Kejadian" );
//                 params.put( "descr", maksPerkejadian  );
//                 params.put( "detail", ""  );
//                 result.add( params );
                 
                params = new HashMap<String, Object>();
                 params.put( "subject", "- Batasan maks.Penggantian pertahun" );
                 params.put( "descr", maksPenggantian  );
                 params.put( "detail", ""  );
                 result.add( params );
    		}
    	}
    	
    	return result;
    }
    
    public List<Map<String, Object>> getRiderBabyList()
    {
    	 List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getBabyFlag() ) )
         {
             Map<String, Object> params;
             params = new HashMap<String, Object>();
             String insuredName= cepr01030101Form.getInsuredName();
             String insuredAge = String.valueOf(cepr01030101Form.getInsuredAge());
             params.put( "subject", insuredName );
             params.put( "descr", insuredAge   ); 
             result.add(params);
         }
         return result;
    }
    
    public List<Map<String, Object>> getOneRowList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> params;

        params = new HashMap<String, Object>();
        params.put( "dummy", "row1" );
        result.add( params );
        return result;
    }
    
    public List<Map<String, Object>> getRiderEarlyStageCi99List()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEarlyStageCi99Flag() ) )
        {
            Map<String, Object> params;
            params = new HashMap<String, Object>();
            
            if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_88 || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK ||
            	cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_MEDIVEST ){
            	params.put( "subject", "- Maks s/d usia 80 tahun dan setelah 90 hari polis diberlakukan, dan" );  
            }else{
            	params.put( "subject", "- Maks s/d usia 99 tahun dan setelah 90 hari polis diberlakukan, dan" );  
            }
            params.put( "descr", null  );
            result.add( params );
            
            params = new HashMap<String, Object>();   
            
//            BigDecimal esci99Choose = new BigDecimal("50");
//        	if( cepr01030103Form.getEsci99ChooseCd() != null ){
//        		esci99Choose = new BigDecimal(cepr01030103Form.getEsci99ChooseCd());
//        	}
//            BigDecimal persentase = esci99Choose.divide(new BigDecimal("100"));
//            double UangPertanggunganEsci99 = cepr01030102Form.getTotalSumInsured().multiply( persentase).doubleValue();
//            double maksUP;
//            if(cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD) ){// dollar
//            	maksUP = 200000.0;
//            }else{ // rupiah
//            	maksUP = 2000000000.0;
//            }
            /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
            BigDecimal upMin = new BigDecimal("7500000");
            if (cepr01030103Form.getEsci99RiderAmount() != null){
            	upMin = cepr01030103Form.getEsci99RiderAmount();
            }
            double upCI = upMin.doubleValue();
            double maksUP;
	        if(cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD) ){// dollar
	        	maksUP = 500000.0;
	        }else{ // rupiah
	        	maksUP = 5000000000.0;
	        }
            double temp = MathUtil.min(upCI, maksUP);
            params.put( "subject", "- Tertanggung tetap hidup dalam jangka waktu 14 hari sejak terdiagnosa Penyakit Kritis" );        
            params.put( "descr", toMoneyWithCurrencyCd(temp)  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderEarlyStageCi99SyariahList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEarlyStageCi99Flag() ) )
        {
            Map<String, Object> params;
            params = new HashMap<String, Object>();
            if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_EKA_LINK_80_PLUS_SYARIAH || cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_SMILE_LINK_SYARIAH ||
            	cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_MEDIVEST_SYARIAH ){
                	params.put( "subject", "- Maks s/d usia 80 tahun dan setelah 90 hari polis diberlakukan, dan" );  
            }else{
                	params.put( "subject", "- Maks s/d usia 99 tahun dan setelah 90 hari polis diberlakukan, dan" );  
            }              
            params.put( "descr", null  );
            result.add( params );
            
            params = new HashMap<String, Object>();   
            
//            BigDecimal esci99Choose = new BigDecimal("50");
//        	if( cepr01030103Form.getEsci99ChooseCd() != null ){
//        		esci99Choose = new BigDecimal(cepr01030103Form.getEsci99ChooseCd());
//        	}
//            BigDecimal persentase = esci99Choose.divide(new BigDecimal("100"));
//            double UangPertanggunganEsci99 = cepr01030102Form.getTotalSumInsured().multiply( persentase).doubleValue();
//            double maksUP;
//            if(cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD) ){// dollar
//            	maksUP = 200000.0;
//            }else{ // rupiah
//            	maksUP = 2000000000.0;
//            }
            /**Pembukaan UP untuk rider Critical Illness, ESCI 99 dan Term Insurance**/
            BigDecimal upMin = new BigDecimal("7500000");
            if (cepr01030103Form.getEsci99RiderAmount() != null){
            	upMin = cepr01030103Form.getEsci99RiderAmount();
            }
            double upCI = upMin.doubleValue();
            double maksUP;
	        if(cepr01030102Form.getCurrencyCd().equals( Hardcode.CUR_USD_CD) ){// dollar
	        	maksUP = 500000.0;
	        }else{ // rupiah
	        	maksUP = 5000000000.0;
	        }
            double temp = MathUtil.min(upCI, maksUP);
            params.put( "subject", "- Peserta tetap hidup dalam jangka waktu 14 hari sejak terdiagnosa Penyakit Kritis" );        
            params.put( "descr", toMoneyWithCurrencyCd(temp)  );
            result.add( params );
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderMedicalPlusList()
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	  List<Map> result2 = new ArrayList();
    	  String riderMedicalPlusType="";
    	  String lsdbs_number="0";
    	  BigDecimal maxClaimPerYear=new BigDecimal(0);
    	  
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag() ) )
        {
            Map<String, Object> params;
                      
            if(cepr01030103Form.getMedicalPlusChooseCd().equals("2")){
            	riderMedicalPlusType = "SILVER";
            	lsdbs_number = "2";
            	maxClaimPerYear = new BigDecimal(900000000);
            }
            else if(cepr01030103Form.getMedicalPlusChooseCd().equals("3")){
            	riderMedicalPlusType = "GOLD";
            	lsdbs_number = "3";
            	maxClaimPerYear = new BigDecimal(1400000000);
            }
            else if(cepr01030103Form.getMedicalPlusChooseCd().equals("4")){
            	riderMedicalPlusType = "PLATINUM";
            	lsdbs_number = "4";
            	maxClaimPerYear = new BigDecimal(1900000000);
            }
            
            
            params = new HashMap<String, Object>();
            params.put( "riderMedicalPlusType", riderMedicalPlusType);    
            result.add( params );
            
            Date tgl = eproposalManager.selectNowDate();
            //Date tgl = eproposalManager.selectNowDate();
            result2 = 	eproposalManager.selectRiderMedicalPlusList(lsdbs_number);
            
            for(int i=0;i<result2.size();i++){
            	String no = result2.get(i).get("NO").toString(); 
            	String ketBenefit = result2.get(i).get("KET_BENEFIT").toString();            	            	
            	BigDecimal nilaiBenefit = new BigDecimal(result2.get(i).get("NILAI_BENEFIT").toString());
            
            	 params = new HashMap<String, Object>();     
            	  params.put( "no",no );    
            	 params.put( "subject",ketBenefit );        
                 params.put( "descr", nilaiBenefit  );
                 params.put( "riderMedicalPlusType", riderMedicalPlusType  );
                 result.add( params );
            }
            
            params = new HashMap<String, Object>();          
            params.put( "maxClaimPerYear", maxClaimPerYear);
            params.put( "riderMedicalPlusType", riderMedicalPlusType);    
            result.add( params );
          
            
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderMedicalPlusRjList()
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	  List<Map> result2 = new ArrayList();
    	  String riderMedicalPlusType="";
    	  String lsdbs_number="0";
    	  BigDecimal maxClaimPerYear=new BigDecimal(0);
    	  
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag()) && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusRjFlag() ) )
        {
            Map<String, Object> params;
                      
            if(cepr01030103Form.getMedicalPlusChooseCd().equals("2")){
            	riderMedicalPlusType = "SILVER";
            	lsdbs_number = "2";
            	maxClaimPerYear = new BigDecimal(8000000);
            }
            else if(cepr01030103Form.getMedicalPlusChooseCd().equals("3")){
            	riderMedicalPlusType = "GOLD";
            	lsdbs_number = "3";
            	maxClaimPerYear = new BigDecimal(10000000);
            }
            else if(cepr01030103Form.getMedicalPlusChooseCd().equals("4")){
            	riderMedicalPlusType = "PLATINUM";
            	lsdbs_number = "4";
            	maxClaimPerYear = new BigDecimal(12000000);
            }           
            
            params = new HashMap<String, Object>();
            params.put( "riderMedicalPlusType", riderMedicalPlusType);    
            result.add( params );
            
            Date tgl = eproposalManager.selectNowDate();
            //Date tgl = eproposalManager.selectNowDate();
            result2 = 	eproposalManager.selectRiderMedicalPlusRjList(lsdbs_number);
            
            for(int i=0;i<result2.size();i++){
            	String no = result2.get(i).get("NO").toString(); 
            	String ketBenefit = result2.get(i).get("KET_BENEFIT").toString();            	            	
            	BigDecimal nilaiBenefit = new BigDecimal(result2.get(i).get("NILAI_BENEFIT").toString());
            
            	 params = new HashMap<String, Object>();
            	 params.put( "no",no );    
            	 params.put( "subject",ketBenefit );        
                 params.put( "descr", nilaiBenefit  );
                 params.put( "riderMedicalPlusType", riderMedicalPlusType  );
                 result.add( params );
            }
            
            params = new HashMap<String, Object>();
            params.put( "maxClaimPerYear", maxClaimPerYear);
            params.put( "riderMedicalPlusType", riderMedicalPlusType);    
            result.add( params );
          
            
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderMedicalPlusRgList()
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	  List<Map> result2 = new ArrayList();
    	  String riderMedicalPlusType="";
    	  String lsdbs_number="0";
    	  BigDecimal maxClaimPerYear=new BigDecimal(0);
    	  
        if(CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag()) && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusRgFlag() ) )
        {
            Map<String, Object> params;
                      
            if(cepr01030103Form.getMedicalPlusChooseCd().equals("2")){
            	riderMedicalPlusType = "SILVER";
            	lsdbs_number = "22";
            	maxClaimPerYear = new BigDecimal(1250000);
            }
            else if(cepr01030103Form.getMedicalPlusChooseCd().equals("3")){
            	riderMedicalPlusType = "GOLD";
            	lsdbs_number = "23";
            	maxClaimPerYear = new BigDecimal(1750000);
            }
            else if(cepr01030103Form.getMedicalPlusChooseCd().equals("4")){
            	riderMedicalPlusType = "PLATINUM";
            	lsdbs_number = "24";
            	maxClaimPerYear = new BigDecimal(2500000);
            }          
            
            params = new HashMap<String, Object>();
            params.put( "riderMedicalPlusType", riderMedicalPlusType);    
            result.add( params );
            
            Date tgl = eproposalManager.selectNowDate();
            //Date tgl = eproposalManager.selectNowDate();
            result2 = 	eproposalManager.selectRiderMedicalPlusRjList(lsdbs_number);
            
            for(int i=0;i<result2.size();i++){
            	String no = result2.get(i).get("NO").toString(); 
            	String ketBenefit = result2.get(i).get("KET_BENEFIT").toString();            	            	
            	BigDecimal nilaiBenefit = new BigDecimal(result2.get(i).get("NILAI_BENEFIT").toString());
            
            	 params = new HashMap<String, Object>();
            	 params.put( "no",no );    
            	 params.put( "subject",ketBenefit );        
                 params.put( "descr", nilaiBenefit  );
                 params.put( "riderMedicalPlusType", riderMedicalPlusType  );
                 result.add( params );
            }
            
            params = new HashMap<String, Object>();
            params.put( "maxClaimPerYear", maxClaimPerYear);
            params.put( "riderMedicalPlusType", riderMedicalPlusType);    
            result.add( params );
          
            
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderMedicalPlusRbList()
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	  List<Map> result2 = new ArrayList();
    	  String riderMedicalPlusType="";
    	  String lsdbs_number="0";
    	     	  
    	if( ( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag()) && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusRbFlag() )) ||
    		( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag()) && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusRjFlag()) &&  CommonConst.CHECKED_TRUE.equals(cepr01030113Form.getParticipantVOList().get(0).getMedicalPlusRbFlag()) ))
        {
            Map<String, Object> params;
                      
            if(cepr01030103Form.getMedicalPlusChooseCd().equals("2")){
            	riderMedicalPlusType = "SILVER";
            	lsdbs_number = "42";            	
            }
            else if(cepr01030103Form.getMedicalPlusChooseCd().equals("3")){
            	riderMedicalPlusType = "GOLD";
            	lsdbs_number = "43";            
            }
            else if(cepr01030103Form.getMedicalPlusChooseCd().equals("4")){
            	riderMedicalPlusType = "PLATINUM";
            	lsdbs_number = "44";            
            }
                      
            params = new HashMap<String, Object>();
            params.put( "riderMedicalPlusType", riderMedicalPlusType);    
            result.add( params );
            
            Date tgl = eproposalManager.selectNowDate();    
            //Date tgl = eproposalManager.selectNowDate();
            result2 = 	eproposalManager.selectRiderMedicalPlusRjList(lsdbs_number);
            
            for(int i=0;i<result2.size();i++){
            	String no = result2.get(i).get("NO").toString(); 
            	String ketBenefit = result2.get(i).get("KET_BENEFIT").toString();            	            	
            	BigDecimal nilaiBenefit = new BigDecimal(result2.get(i).get("NILAI_BENEFIT").toString());
            
            	 params = new HashMap<String, Object>();
            	 params.put( "no",no );    
            	 params.put( "subject",ketBenefit );        
                 params.put( "descr", nilaiBenefit  );
                 params.put( "riderMedicalPlusType", riderMedicalPlusType  );
                 result.add( params );
            }
            
            params = new HashMap<String, Object>();
            params.put( "riderMedicalPlusType", riderMedicalPlusType);    
            result.add( params );
          
            
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderMedicalPlusPkList()
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	  List<Map> result2 = new ArrayList();
    	  String riderMedicalPlusType="";
    	  String lsdbs_number="0";
    	  BigDecimal maxClaimPerYear=new BigDecimal(0);
    	  
    	if(CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag()) && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusRjFlag()) && CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusPkFlag() ) )
        {
            Map<String, Object> params;
                      
            if(cepr01030103Form.getMedicalPlusChooseCd().equals("2")){
            	riderMedicalPlusType = "SILVER";
            	lsdbs_number = "62";
            	maxClaimPerYear = new BigDecimal(4500000);
            }
            else if(cepr01030103Form.getMedicalPlusChooseCd().equals("3")){
            	riderMedicalPlusType = "GOLD";
            	lsdbs_number = "63";            
            	maxClaimPerYear = new BigDecimal(5500000);
            }
            else if(cepr01030103Form.getMedicalPlusChooseCd().equals("4")){
            	riderMedicalPlusType = "PLATINUM";
            	lsdbs_number = "64";            
            	maxClaimPerYear = new BigDecimal(7500000);
            }          
            
            params = new HashMap<String, Object>();
            params.put( "riderMedicalPlusType", riderMedicalPlusType);    
            result.add( params );
            
            Date tgl = eproposalManager.selectNowDate(); 
            //Date tgl = eproposalManager.selectNowDate();
            result2 = 	eproposalManager.selectRiderMedicalPlusRjList(lsdbs_number);
            
            for(int i=0;i<result2.size();i++){
            	String no = result2.get(i).get("NO").toString(); 
            	String ketBenefit = result2.get(i).get("KET_BENEFIT").toString();            	            	
            	BigDecimal nilaiBenefit = new BigDecimal(result2.get(i).get("NILAI_BENEFIT").toString());
            	
            	 params = new HashMap<String, Object>();
            	 params.put( "no",no );    
            	 params.put( "subject",ketBenefit );        
                 params.put( "descr", nilaiBenefit  );
                 params.put( "riderMedicalPlusType", riderMedicalPlusType  );
                 result.add( params );
            }
            
            params = new HashMap<String, Object>();
            params.put( "maxClaimPerYear", maxClaimPerYear);
            params.put( "riderMedicalPlusType", riderMedicalPlusType);    
            result.add( params );
          
            
        }

        return result;
    }
    
    public List<Map<String, Object>> getRiderMedicalPlusParticipantsList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag()) || ( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030103Form.getMedicalPlusDisplay())) )
        {
            List<ParticipantVO> participantVOList = cepr01030113Form.getParticipantVOList();
            participantVOList = removeEmptyParticipantVOFromTheList( participantVOList );
            Map<String, Object> params;
            Integer idx = 0;
            for( ParticipantVO vo : participantVOList )
            {
                idx++;
                params = new HashMap<String, Object>();
                params.put( "idx_medical_plus", idx.toString().concat( "." ) );
                params.put( "name_medical_plus", vo.getName() );
                params.put( "age_medical_plus", vo.getAge().toString() );
                params.put( "sex_medical_plus", vo.getSexCd().toString() );
                result.add( params );
            }
        }

        return result;
    }
    
    public List<Map<String, Object>> getRincianRiderMedicalPlusParticipantsList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag()) || ( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getMedicalPlusFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030103Form.getMedicalPlusDisplay())) )
        {
            List<ParticipantVO> participantVOList = cepr01030113Form.getParticipantVOList();
            participantVOList = removeEmptyParticipantVOFromTheList( participantVOList );
            Map<String, Object> params;
            Integer idx = 0;
            for( ParticipantVO vo : participantVOList )
            {
                idx++;
                params = new HashMap<String, Object>();
                params.put( "idx", idx.toString().concat( "." ) );
                params.put( "name", vo.getName() );
                params.put( "age", vo.getAge().toString() );
                params.put( "sex", vo.getSexCd().toString() );
                result.add( params );
            }
        }

        return result;
    }
    
    public String checkParticapantMedicalPlus()
    {
       String result = null;
       List<ParticipantVO> participantVOList = cepr01030113Form.getParticipantVOList();
       for( int i = 0 ; i < participantVOList.size() ; i ++ )
       {
    	   if( participantVOList.get(i).getName() != null && !"".equals( participantVOList.get(i).getName() ) ){
    		   result = "yes";
    		   break;
    	   }else{
    		   result = "no";
    	   }
       }
       return result;
    }
    
        
    public List<Map<String, Object>> getRincianRiderEkaSehat()
    {
   	 List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
     if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag() ) ||  
    		 ( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getEkaSehatFlag() ) &&  CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getEkaSehatFlagDisplay() ) ) )
     {
     	if( ( cepr01030103Form.getEkaSehatCd() != null && cepr01030103Form.getEkaSehatCd() > 0 )
     			||( cepr01030102Form.getEkaSehatCd()!= null && cepr01030102Form.getEkaSehatCd() > 0 ) )
     	{
     		Map<String, Object> params;
     		String jenisSantunan = null;
     		String biayaInap = null;
     		String biayaIcu = null;
     		String limitKejadian = null;
     		String maksPenggantian = null;
     		Integer jenis = null;
     		if(  cepr01030103Form.getEkaSehatCd() != null && cepr01030103Form.getEkaSehatCd() > 0 ){
     			jenis = cepr01030103Form.getEkaSehatCd();
     		}else if( cepr01030102Form.getEkaSehatCd()!= null && cepr01030102Form.getEkaSehatCd() > 0 ){
     			jenis = cepr01030102Form.getEkaSehatCd();
     		}
         	if( jenis == 1 ){ // plan A
         		jenisSantunan="Plan A-100";
         		biayaInap="100,000";
         		biayaIcu="200,000";
         		limitKejadian="12,500,000";
         		maksPenggantian="50,000,000";
         	}else if( jenis == 2 ){ // plan B
         		jenisSantunan="Plan B-150";
         		biayaInap="150,000";
         		biayaIcu="300,000";
         		limitKejadian="18,750,000";
         		maksPenggantian="75,000,000";
         	}else if( jenis == 3 ){ // plan C
         		jenisSantunan="Plan C-200";
         		biayaInap="200,000";
         		biayaIcu="400,000";
         		limitKejadian="25,000,000";
         		maksPenggantian="100,000,000";
         	}else if( jenis == 4 ){ // plan D
         		jenisSantunan="Plan D-250";
         		biayaInap="250,000";
         		biayaIcu="500,000";
         		limitKejadian="31,250,000";
         		maksPenggantian="125,000,000";
         	}else if( jenis == 5 ){ // plan E
         		jenisSantunan="Plan E-300";
         		biayaInap="300,000";
         		biayaIcu="600,000";
         		limitKejadian="37,500,000";
         		maksPenggantian="150,000,000";
         	}else if( jenis == 6 ){ // plan F
         		jenisSantunan="Plan F-350";
         		biayaInap="350,000";
         		biayaIcu="700,000";
         		limitKejadian="43,750,000";
         		maksPenggantian="175,000,000";
         	}else if( jenis == 7 ){ // plan G
         		jenisSantunan="Plan G-400";
         		biayaInap="400,000";
         		biayaIcu="800,000";
         		limitKejadian="50,000,000";
         		maksPenggantian="200,000,000";
         	}else if( jenis == 8 ){ // plan H
         		jenisSantunan="Plan H-500";
         		biayaInap="500,000";
         		biayaIcu="1,000,000";
         		limitKejadian="100,000,000";
         		maksPenggantian="400,000,000";
         	}else if( jenis == 9 ){ // plan I
         		jenisSantunan="Plan I-600";
         		biayaInap="600,000";
         		biayaIcu="1,200,000";
         		limitKejadian="125,000,000";
         		maksPenggantian="500,000,000";
         	}else if( jenis == 10 ){ // plan J
         		jenisSantunan="Plan J-700";
         		biayaInap="700,000";
         		biayaIcu="1,400,000";
         		limitKejadian="150,000,000";
         		maksPenggantian="600,000,000";
         	}else if( jenis == 11 ){ // plan K
         		jenisSantunan="Plan K-800";
         		biayaInap="800,000";
         		biayaIcu="1,600,000";
         		limitKejadian="175,000,000";
         		maksPenggantian="700,000,000";
         	}else if( jenis == 12 ){ // plan L
         		jenisSantunan="Plan L-900";
         		biayaInap="900,000";
         		biayaIcu="1,800,000";
         		limitKejadian="200,000,000";
         		maksPenggantian="800,000,000";
         	}else if( jenis == 13 ){ // plan M
         		jenisSantunan="Plan M-1000";
         		biayaInap="1,000,000";
         		biayaIcu="2,000,000";
         		limitKejadian="225,000,000";
         		maksPenggantian="900,000,000";
         	}else if( jenis == 14 ){ // plan N
         		jenisSantunan="Plan N-1500";
         		biayaInap="1,500,000";
         		biayaIcu="3,000,000";
         		limitKejadian="350,000,000";
         		maksPenggantian="1,400,000,000";
         	}else if( jenis == 15 ){ // plan O
         		jenisSantunan="Plan O-2000";
         		biayaInap="2,000,000";
         		biayaIcu="4,000,000";
         		limitKejadian="475,000,000";
         		maksPenggantian="1,900,000,000";
         	}else if( jenis == 16 ){ // plan P
         		jenisSantunan="Plan P-5000";
         		biayaInap="5,000,000";
         		biayaIcu="4,000,000";
         		limitKejadian="475,000,000";
         		maksPenggantian="5,000,000,000";
         	}else if( jenis == 17 ){ // plan Q
         		jenisSantunan="Plan Q-7500";
         		biayaInap="7,500,000";
         		biayaIcu="4,000,000";
         		limitKejadian="475,000,000";
         		maksPenggantian="6,000,000,000";
         	}          	              
         	 params = new HashMap<String, Object>();         	
             params.put( "plan", jenisSantunan );            
             result.add( params ); 
         	
             params = new HashMap<String, Object>();
             params.put( "no", "1"  );          
             params.put( "subject", "Biaya Kamar dan Menginap di Rumah Sakit (maksimal 365 hari) per hari" );
             params.put( "descr", biayaInap  );          
             result.add( params );
              
             params = new HashMap<String, Object>();
             params.put( "no", "2"  );    
             params.put( "subject", "Biaya Kamar ICU/ICCU (maks 60 hari) per hari" );
             params.put( "descr", biayaIcu  );           
             result.add( params );
              
         	 params = new HashMap<String, Object>();
         	 params.put( "no", "3"  );    
         	 params.put( "subject", "Biaya Aneka Perawatan di Rumah Sakit" );
             params.put( "descr", "As Charge"  );      
             result.add( params );
              
         	 params = new HashMap<String, Object>();
         	 params.put( "no", "4"  );    
         	 params.put( "subject", "Biaya Operasi/Pembedahan" );
             params.put( "descr", "As Charge"  );       
             result.add( params );
              
         	 params = new HashMap<String, Object>();
         	 params.put( "no", "5"  );    
         	 params.put( "subject", "Biaya Kunjungan Dokter di Rumah Sakit" );
             params.put( "descr", "As Charge"  );
             params.put( "detail", ""  );
             result.add( params );
              
             params = new HashMap<String, Object>();
             params.put( "no", "6"  );    
             params.put( "subject", "Biaya Kunjungan Dokter Spesialis di Rumah Sakit" );
             params.put( "descr", "As Charge"  );
             params.put( "detail", ""  );
             result.add( params );
              
         	 params = new HashMap<String, Object>();
         	 params.put( "no", "7"  );    
         	 params.put( "subject", "Biaya Pemeriksaan Laboratorium & Test Diagnostik (7 hari sebelum Rawat Inap)" );
             params.put( "descr", "As Charge"  );            
             result.add( params );
              
         	 params = new HashMap<String, Object>();
         	 params.put( "no", "8"  );    
         	 params.put( "subject", "Biaya Konsultasi Lanjutan (30 hari setelah Rawat Inap)" );
             params.put( "descr", "As Charge"  );            
             result.add( params );
              
         	 params = new HashMap<String, Object>();
         	 params.put( "no", "9"  );     
         	 params.put( "subject", "Transportasi dgn Mobil Ambulance ke RS (darurat)" );
             params.put( "descr", "As Charge"  );         
             result.add( params );
              
             if( jenis == 16 || jenis == 17 ){
            	 params = new HashMap<String, Object>();
             	 params.put( "no", "10"  );     
             	 params.put( "subject", "Perawat Pribadi di Rumah" );
                 params.put( "descr", "As Charge"  );         
                 result.add( params );
             }	 
         	 params = new HashMap<String, Object>();
             params.put( "limit", maksPenggantian );            
             result.add( params ); 
     	}
     }    
     return result;
    }
    
    public List<Map<String, Object>> getRincianRiderEkaSehatParticipantsList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 189 || cepr01030102Form.getLeftPartOfBusinessCd() == 183 || cepr01030102Form.getLeftPartOfBusinessCd() == 193 || cepr01030102Form.getLeftPartOfBusinessCd() == 201 || CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatFlag()) ||
        		( CommonConst.CHECKED_TRUE.equals( cepr01030102Form.getEkaSehatFlag() ) && CommonConst.DISPLAY_TRUE.equals( cepr01030102Form.getEkaSehatFlagDisplay())) )
        {
            List<ParticipantVO> participantVOList = cepr01030107Form.getParticipantVOList();
            participantVOList = removeEmptyParticipantVOFromTheList( participantVOList );
            Map<String, Object> params;
            Integer idx = 0;
            for( ParticipantVO vo : participantVOList )
            {
                idx++;
                params = new HashMap<String, Object>();
                params.put( "idx", idx.toString().concat( "." ) );
                params.put( "name", vo.getName() );
                params.put( "age", vo.getAge().toString() );
                result.add( params );
            }
        }

        return result;
    }
    
    public List<Map<String, Object>> getRincianRiderEkaSehatInnerLimit( )
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatInnerLimitFlag() ) )
    	{
    		if( cepr01030103Form.getEkaSehatInnerLimitCd() != null && cepr01030103Form.getEkaSehatInnerLimitCd() > 0 )
    		{
    			Map<String, Object> params;
    			String jenisSantunan = null;
         		String biayaInap = null;
         		String biayaIcu = null;
         		String anekaPerawatan = null;
    			String biayaOP = null;
    			String kunjungan = null;
    			String kunjunganSpesialis = null;
    			String biayaPerawatanPribadi = null;
    			String biayaAmbulans = null;
    			String biayaBAPerawatan = null;
         		String maksPenggantian = null;
         		if( cepr01030103Form.getEkaSehatInnerLimitCd() == 1 ){ // plan A
             		jenisSantunan="Plan A-100";
             		biayaInap="100,000";
             		biayaIcu="200,000";
             		anekaPerawatan="1,750,000";
    				biayaOP="12,000,000";
    				kunjungan="50,000";
    				kunjunganSpesialis="300,000";
    				biayaPerawatanPribadi="50,000";
    				biayaAmbulans="40,000";
    				biayaBAPerawatan="350,000";
             		maksPenggantian="50,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 2 ){ // plan B
             		jenisSantunan="Plan B-150";
             		biayaInap="150,000";
             		biayaIcu="300,000";
             		anekaPerawatan="2,250,000";
    				biayaOP="17,250,000";
    				kunjungan="60,000";
    				kunjunganSpesialis="360,000";
    				biayaPerawatanPribadi="60,000";
    				biayaAmbulans="45,000";
    				biayaBAPerawatan="500,000";
             		maksPenggantian="75,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 3 ){ // plan C
             		jenisSantunan="Plan C-200";
             		biayaInap="200,000";
             		biayaIcu="400,000";
             		anekaPerawatan="2,750,000";
    				biayaOP="22,500,000";
    				kunjungan="70,000";
    				kunjunganSpesialis="420,000";
    				biayaPerawatanPribadi="70,000";
    				biayaAmbulans="50,000";
    				biayaBAPerawatan="650,000";
             		maksPenggantian="100,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 4 ){ // plan D
             		jenisSantunan="Plan D-250";
             		biayaInap="250,000";
             		biayaIcu="500,000";
             		anekaPerawatan="3,250,000";
    				biayaOP="27,750,000";
    				kunjungan="80,000";
    				kunjunganSpesialis="480,000";
    				biayaPerawatanPribadi="80,000";
    				biayaAmbulans="55,000";
    				biayaBAPerawatan="800,000";
             		maksPenggantian="125,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 5 ){ // plan E
             		jenisSantunan="Plan E-300";
             		biayaInap="300,000";
             		biayaIcu="600,000";
             		anekaPerawatan="3,750,000";
    				biayaOP="33,000,000";
    				kunjungan="90,000";
    				kunjunganSpesialis="540,000";
    				biayaPerawatanPribadi="90,000";
    				biayaAmbulans="60,000";
    				biayaBAPerawatan="950,000";
             		maksPenggantian="150,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 6 ){ // plan F
             		jenisSantunan="Plan F-350";
             		biayaInap="350,000";
             		biayaIcu="700,000";
             		anekaPerawatan="4,250,000";
    				biayaOP="38,250,000";
    				kunjungan="100,000";
    				kunjunganSpesialis="600,000";
    				biayaPerawatanPribadi="100,000";
    				biayaAmbulans="65,000";
    				biayaBAPerawatan="1,100,000";
             		maksPenggantian="175,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 7 ){ // plan G
             		jenisSantunan="Plan G-400";
             		biayaInap="400,000";
             		biayaIcu="800,000";
             		anekaPerawatan="4,750,000";
    				biayaOP="43,500,000";
    				kunjungan="110,000";
    				kunjunganSpesialis="660,000";
    				biayaPerawatanPribadi="110,000";
    				biayaAmbulans="70,000";
    				biayaBAPerawatan="1,250,000";
             		maksPenggantian="200,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 8 ){ // plan H
             		jenisSantunan="Plan H-500";
             		biayaInap="500,000";
             		biayaIcu="1.000,000";
             		anekaPerawatan="5,750,000";
    				biayaOP="54,000,000";
    				kunjungan="130,000";
    				kunjunganSpesialis="780,000";
    				biayaPerawatanPribadi="130,000";
    				biayaAmbulans="80,000";
    				biayaBAPerawatan="1,550,000";
             		maksPenggantian="250,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 9 ){ // plan I
             		jenisSantunan="Plan I-600";
             		biayaInap="600,000";
             		biayaIcu="1,200,000";
             		anekaPerawatan="6,750,000";
    				biayaOP="64,500,000";
    				kunjungan="150,000";
    				kunjunganSpesialis="900,000";
    				biayaPerawatanPribadi="150,000";
    				biayaAmbulans="90,000";
    				biayaBAPerawatan="1,850,000";
             		maksPenggantian="300,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 10 ){ // plan J
             		jenisSantunan="Plan J-700";
             		biayaInap="700,000";
             		biayaIcu="1,400,000";
             		anekaPerawatan="7,750,000";
    				biayaOP="75,000,000";
    				kunjungan="170,000";
    				kunjunganSpesialis="1,020,000";
    				biayaPerawatanPribadi="170,000";
    				biayaAmbulans="100,000";
    				biayaBAPerawatan="2,150,000";
             		maksPenggantian="350,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 11 ){ // plan K
             		jenisSantunan="Plan K-800";
             		biayaInap="800,000";
             		biayaIcu="1,600,000";
             		anekaPerawatan="8,750,000";
    				biayaOP="85,500,000";
    				kunjungan="190,000";
    				kunjunganSpesialis="1,140,000";
    				biayaPerawatanPribadi="190,000";
    				biayaAmbulans="110,000";
    				biayaBAPerawatan="2,450,000";
             		maksPenggantian="400,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 12 ){ // plan L
             		jenisSantunan="Plan L-900";
             		biayaInap="900,000";
             		biayaIcu="1,800,000";
             		anekaPerawatan="9,750,000";
    				biayaOP="96,000,000";
    				kunjungan="210,000";
    				kunjunganSpesialis="1,260,000";
    				biayaPerawatanPribadi="210,000";
    				biayaAmbulans="120,000";
    				biayaBAPerawatan="2,750,000";
             		maksPenggantian="450,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 13 ){ // plan M
             		jenisSantunan="Plan M-1000";
             		biayaInap="1,000,000";
             		biayaIcu="2,000,000";
             		anekaPerawatan="10,750,000";
    				biayaOP="106,500,000";
    				kunjungan="230,000";
    				kunjunganSpesialis="1,380,000";
    				biayaPerawatanPribadi="230,000";
    				biayaAmbulans="130,000";
    				biayaBAPerawatan="3,050,000";
             		maksPenggantian="500,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 14 ){ // plan N
             		jenisSantunan="Plan N-1500";
             		biayaInap="1,500,000";
             		biayaIcu="3,000,000";
             		anekaPerawatan="15,750,000";
    				biayaOP="159,000,000";
    				kunjungan="330,000";
    				kunjunganSpesialis="1,980,000";
    				biayaPerawatanPribadi="330,000";
    				biayaAmbulans="180,000";
    				biayaBAPerawatan="4,550,000";
             		maksPenggantian="750,000,000";
             	}else if( cepr01030103Form.getEkaSehatInnerLimitCd() == 15 ){ // plan O
             		jenisSantunan="Plan O-2000";
             		biayaInap="2,000,000";
             		biayaIcu="4,000,000";
             		anekaPerawatan="20,750,000";
    				biayaOP="211,500,000";
    				kunjungan="430,000";
    				kunjunganSpesialis="2,580,000";
    				biayaPerawatanPribadi="430,000";
    				biayaAmbulans="230,000";
    				biayaBAPerawatan="6,050,000";
             		maksPenggantian="1,000,000,000";
             	}           
             	
                  params = new HashMap<String, Object>();         	
                  params.put( "plan", jenisSantunan );            
                  result.add( params ); 
                  
                  params = new HashMap<String, Object>();
                  params.put( "no", "1"  );          
                  params.put( "subject", "Biaya Kamar ICU/ICCU" );
                  params.put( "descr", biayaInap  );          
                  result.add( params );                
                                    
             	 params = new HashMap<String, Object>();
             	  params.put( "no", "2"  );          
                  params.put( "subject", "Biaya Aneka Perawatan di RS" );
                  params.put( "descr", anekaPerawatan  );          
                  result.add( params );                         
             	
                  params = new HashMap<String, Object>();
             	  params.put( "no", "3"  );          
                  params.put( "subject", "Biaya Operasi/Pembedahan" );
                  params.put( "descr", biayaOP  );          
                  result.add( params );
                  
                  params = new HashMap<String, Object>();
             	  params.put( "no", "4"  );          
                  params.put( "subject", "Biaya Kunjungan Dokter di RS – per hari" );
                  params.put( "descr", kunjungan  );          
                  result.add( params );     
                  
                  params = new HashMap<String, Object>();
             	  params.put( "no", "5"  );          
                  params.put( "subject", "Biaya Konsultasi Dokter Spesialis di RS" );
                  params.put( "descr", kunjunganSpesialis  );          
                  result.add( params );       
                  
                  params = new HashMap<String, Object>();
             	  params.put( "no", "6"  );          
                  params.put( "subject", "Biaya Perawat Pribadi – per hari" );
                  params.put( "descr", biayaPerawatanPribadi  );          
                  result.add( params );       
                  
                  params = new HashMap<String, Object>();
             	  params.put( "no", "7"  );          
                  params.put( "subject", "Biaya Ambulance" );
                  params.put( "descr", biayaAmbulans  );          
                  result.add( params );      
                  
                  params = new HashMap<String, Object>();
             	  params.put( "no", "8"  );          
                  params.put( "subject", "Biaya Sebelum dan Sesudah Perawatan di RS" );
                  params.put( "descr", biayaBAPerawatan  );          
                  result.add( params );    
                 
                  params = new HashMap<String, Object>();
                  params.put( "limit", maksPenggantian );            
                  result.add( params );                 
    		}
    	}
    	
    	return result;
    }
 
    public List<Map<String, Object>> getRincianRiderEkaSehatInnerLimitParticipantsList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatInnerLimitFlag()) )
        {
            List<ParticipantVO> participantVOList = cepr01030108Form.getParticipantVOList();
            participantVOList = removeEmptyParticipantVOFromTheList( participantVOList );
            Map<String, Object> params;
            Integer idx = 0;
            for( ParticipantVO vo : participantVOList )
            {
                idx++;
                params = new HashMap<String, Object>();
                params.put( "idx", idx.toString().concat( "." ) );
                params.put( "name", vo.getName() );
                params.put( "age", vo.getAge().toString() );
                result.add( params );
            }
        }

        return result;
    }
    
    
    public List<Map<String, Object>> getRincianRiderLadiesMedExpenseSyariah( )
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesMedExpenseFlag() ) )
    	{
    		if( cepr01030103Form.getLadiesMedExpenseCd() != null && cepr01030103Form.getLadiesMedExpenseCd() > 0 )
    		{
    			Map<String, Object> params;
    			String jenisSantunan = null;
         		String biayaInap = null;
         		String biayaIcu = null;
         		String anekaPerawatan = null;
    			String biayaOP = null;
    			String kunjungan = null;
    			String kunjunganSpesialis = null;
    			String biayaBAPerawatan = null;
    			String manfaatKemoterapi = null;
    			String biayaSekaligus = null;
				String maksPerkejadian = null;
         		String maksPenggantian = null;
    			if(cepr01030102Form.getCurrencyCd().equals("01")){
    				if( cepr01030103Form.getLadiesMedExpenseCd() == 1 ){ // plan A
                 		jenisSantunan="Plan A";
                 		biayaInap="200,000";
                 		biayaIcu="400,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="4,000,000";
                 		biayaSekaligus="2,000,000";
                 		maksPerkejadian="50,000,000";
                 		maksPenggantian="100,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 2 ){ // plan B
                 		jenisSantunan="Plan B";
                 		biayaInap="400,000";
                 		biayaIcu="800,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="8,000,000";
                 		biayaSekaligus="4,000,000";
                 		maksPerkejadian="100,000,000";
                 		maksPenggantian="200,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 3 ){ // plan C
                 		jenisSantunan="Plan C";
                 		biayaInap="600,000";
                 		biayaIcu="1,200,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="12,000,000";
                 		biayaSekaligus="6,000,000";
                 		maksPerkejadian="150,000,000";
                 		maksPenggantian="300,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 4 ){ // plan D
                 		jenisSantunan="Plan D";
                 		biayaInap="800,000";
                 		biayaIcu="1,600,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="16,000,000";
                 		biayaSekaligus="8,000,000";
                 		maksPerkejadian="200,000,000";
                 		maksPenggantian="400,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 5 ){ // plan E
                 		jenisSantunan="Plan E";
                 		biayaInap="1,000,000";
                 		biayaIcu="2,000,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="20,000,000";
                 		biayaSekaligus="10,000,000";
                 		maksPerkejadian="250,000,000";
                 		maksPenggantian="500,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 6 ){ // plan F
                 		jenisSantunan="Plan F";
                 		biayaInap="1,500,000";
                 		biayaIcu="3,000,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="30,000,000";
                 		biayaSekaligus="15,000,000";
                 		maksPerkejadian="375,000,000";
                 		maksPenggantian="750,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 7 ){ // plan G
                 		jenisSantunan="Plan G";
                 		biayaInap="2,000,000";
                 		biayaIcu="4,000,000";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="40,000,000";
                 		biayaSekaligus="20,000,000";
                 		maksPerkejadian="500,000,000";
                 		maksPenggantian="1,000,000,000";
                 	}
                 	
    			}
    			else
    			{
    				if( cepr01030103Form.getLadiesMedExpenseCd() == 1 ){ // plan A
                 		jenisSantunan="Plan A";
                 		biayaInap="20";
                 		biayaIcu="40";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="400";
                 		biayaSekaligus="200";
                 		maksPerkejadian="5,000";
                 		maksPenggantian="10,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 2 ){ // plan B
                 		jenisSantunan="Plan B";
                 		biayaInap="40";
                 		biayaIcu="80";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="800";
                 		biayaSekaligus="400";
                 		maksPerkejadian="10,000";
                 		maksPenggantian="20,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 3 ){ // plan C
                 		jenisSantunan="Plan C";
                 		biayaInap="60";
                 		biayaIcu="120";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="1,200";
                 		biayaSekaligus="600";
                 		maksPerkejadian="15,000";
                 		maksPenggantian="30,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 4 ){ // plan D
                 		jenisSantunan="Plan D";
                 		biayaInap="80";
                 		biayaIcu="160";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="1,600";
                 		biayaSekaligus="800";
                 		maksPerkejadian="20,000";
                 		maksPenggantian="40,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 5 ){ // plan E
                 		jenisSantunan="Plan E";
                 		biayaInap="100";
                 		biayaIcu="200";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="2,000";
                 		biayaSekaligus="1,000";
                 		maksPerkejadian="25,000";
                 		maksPenggantian="50,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 6 ){ // plan F
                 		jenisSantunan="Plan F";
                 		biayaInap="150";
                 		biayaIcu="300";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="3,000";
                 		biayaSekaligus="1,500";
                 		maksPerkejadian="37,500";
                 		maksPenggantian="75,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseCd() == 7 ){ // plan G
                 		jenisSantunan="Plan G";
                 		biayaInap="200";
                 		biayaIcu="400";
                 		anekaPerawatan = "As Charge";
                 		biayaOP="As Charge";
                 		kunjungan="As Charge";
                 		kunjunganSpesialis="As Charge";
                 		biayaBAPerawatan="As Charge";
                 		manfaatKemoterapi="4,000";
                 		biayaSekaligus="2,000";
                 		maksPerkejadian="50,000";
                 		maksPenggantian="100,000";
                 	}
    			}
    			  params = new HashMap<String, Object>();         	
                  params.put( "plan", jenisSantunan );            
                  result.add( params ); 
                  
                  params = new HashMap<String, Object>();
                  params.put( "no", "1"  );          
                  params.put( "subject", "Biaya Kamar dan Menginap" );
                  params.put( "descr", biayaInap  );          
                  result.add( params );                
    			
                  params = new HashMap<String, Object>();
                  params.put( "no", "2"  );          
                  params.put( "subject", "Biaya Kamar ICU/ICCU" );
                  params.put( "descr", biayaIcu  );          
                  result.add( params );      
    			                 
                  params = new HashMap<String, Object>();
                  params.put( "no", "3"  );          
                  params.put( "subject", "Biaya Aneka Perawatan di RS" );
                  params.put( "descr", anekaPerawatan  );          
                  result.add( params );  
                 
                  params = new HashMap<String, Object>();
                  params.put( "no", "4"  );          
                  params.put( "subject", "Biaya Operasi/Pembedahan" );
                  params.put( "descr", biayaOP  );          
                  result.add( params );  
               
                  params = new HashMap<String, Object>();
                  params.put( "no", "5"  );          
                  params.put( "subject", "Biaya Kunjungan Dokter/Dokter Ahli di RS" );
                  params.put( "descr", kunjungan  );          
                  result.add( params );  
                 
//                params = new HashMap<String, Object>();
//                 params.put( "subject", "- Biaya Konsultasi Dokter Spesialis di Rumah Sakit" );
//                 params.put( "descr", kunjunganSpesialis  );
//                 params.put( "detail", "per kejadian"  );
//                 params.put( "maksPerkejadian", maksPerkejadian );
//                 params.put( "maksPenggantian", maksPenggantian );
//                 result.add( params );
                 

                  params = new HashMap<String, Object>();
                  params.put( "no", "6"  );          
                  params.put( "subject", "Biaya Sebelum dan Sesudah Perawatan Rumah Sakit (7 hari sebelum Rawat Inap dan 30 hari setelah Rawat Inap)" );
                  params.put( "descr", biayaBAPerawatan  );          
                  result.add( params );  
                  
                  params = new HashMap<String, Object>();
                  params.put( "no", "7"  );          
                  params.put( "subject", "Manfaat Kemoterapi" );
                  params.put( "descr", manfaatKemoterapi  );          
                  result.add( params );  
                                   
                  params = new HashMap<String, Object>();
                  params.put( "no", "8"  );          
                  params.put( "subject", "Biaya Pengobatan Sekaligus" );
                  params.put( "descr", biayaSekaligus  );          
                  result.add( params ); 
                  
                  params = new HashMap<String, Object>();         	
                  params.put( "limit", maksPenggantian );            
                  result.add( params );                   
    		}
    	}
    	
    	return result;
    }

    public List<Map<String, Object>> getRincianRiderLadiesMedExpenseInnerLimitSyariah( ) 
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    	if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getLadiesMedExpenseInnerLimitFlag() ) )
    	{
    		if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() != null && cepr01030103Form.getLadiesMedExpenseInnerLimitCd() > 0 )
    		{
    			Map<String, Object> params;
    			String jenisSantunan = null;
         		String biayaInap = null;
         		String biayaIcu = null;
         		String anekaPerawatan = null;
    			String biayaOP = null;
    			String kunjungan = null;
    			String kunjunganSpesialis = null;
    			String biayaBAPerawatan = null;
    			String manfaatKemoterapi = null;
    			String biayaSekaligus = null;
				String maksPerkejadian = null;
         		String maksPenggantian = null;
    			if(cepr01030102Form.getCurrencyCd().equals("01")){
    				if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 1 ){ // plan A
                 		jenisSantunan="Plan A";
                 		biayaInap="200,000";
                 		biayaIcu="400,000";
                 		anekaPerawatan = "5,500,000";
                 		biayaOP="45,000,000";
                 		kunjungan="75,000";
                 		kunjunganSpesialis="630,000";
                 		biayaBAPerawatan="650,000";
                 		manfaatKemoterapi="4,000,000";
                 		biayaSekaligus="2,000,000";
                 		maksPerkejadian="50,000,000";
                 		maksPenggantian="100,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 2 ){ // plan B
                 		jenisSantunan="Plan B";
                 		biayaInap="400,000";
                 		biayaIcu="800,000";
                 		anekaPerawatan = "9,500,000";
                 		biayaOP="87,000,000";
                 		kunjungan="125,000";
                 		kunjunganSpesialis="990,000";
                 		biayaBAPerawatan="1,250,000";
                 		manfaatKemoterapi="8,000,000";
                 		biayaSekaligus="4,000,000";
                 		maksPerkejadian="100,000,000";
                 		maksPenggantian="200,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 3 ){ // plan C
                 		jenisSantunan="Plan C";
                 		biayaInap="600,000";
                 		biayaIcu="1,200,000";
                 		anekaPerawatan = "13,500,000";
                 		biayaOP="129,000,000";
                 		kunjungan="150,000";
                 		kunjunganSpesialis="1,350,000";
                 		biayaBAPerawatan="1,850,000";
                 		manfaatKemoterapi="12,000,000";
                 		biayaSekaligus="6,000,000";
                 		maksPerkejadian="150,000,000";
                 		maksPenggantian="300,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 4 ){ // plan D
                 		jenisSantunan="Plan D";
                 		biayaInap="800,000";
                 		biayaIcu="1,600,000";
                 		anekaPerawatan = "17,500,000";
                 		biayaOP="171,000,000";
                 		kunjungan="225,000";
                 		kunjunganSpesialis="1,710,000";
                 		biayaBAPerawatan="2,450,000";
                 		manfaatKemoterapi="16,000,000";
                 		biayaSekaligus="8,000,000";
                 		maksPerkejadian="200,000,000";
                 		maksPenggantian="400,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 5 ){ // plan E
                 		jenisSantunan="Plan E";
                 		biayaInap="1,000,000";
                 		biayaIcu="2,000,000";
                 		anekaPerawatan = "21,500,000";
                 		biayaOP="213,000,000";
                 		kunjungan="250,000";
                 		kunjunganSpesialis="2,070,000";
                 		biayaBAPerawatan="3,050,000";
                 		manfaatKemoterapi="20,000,000";
                 		biayaSekaligus="10,000,000";
                 		maksPerkejadian="250,000,000";
                 		maksPenggantian="500,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 6 ){ // plan F
                 		jenisSantunan="Plan F";
                 		biayaInap="1,500,000";
                 		biayaIcu="3,000,000";
                 		anekaPerawatan = "31,500,000";
                 		biayaOP="318,000,000";
                 		kunjungan="350,000";
                 		kunjunganSpesialis="2,790,000";
                 		biayaBAPerawatan="4,250,000";
                 		manfaatKemoterapi="30,000,000";
                 		biayaSekaligus="15,000,000";
                 		maksPerkejadian="375,000,000";
                 		maksPenggantian="750,000,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 7 ){ // plan G
                 		jenisSantunan="Plan G";
                 		biayaInap="2,000,000";
                 		biayaIcu="4,000,000";
                 		anekaPerawatan = "41,500,000";
                 		biayaOP="423,000,000";
                 		kunjungan="450,000";
                 		kunjunganSpesialis="3,150,000";
                 		biayaBAPerawatan="4,850,000";
                 		manfaatKemoterapi="40,000,000";
                 		biayaSekaligus="20,000,000";
                 		maksPerkejadian="500,000,000";
                 		maksPenggantian="1,000,000,000";
                 	}
    			}
    			else
    			{
    				if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 1 ){ // plan A
                 		jenisSantunan="Plan A";
                 		biayaInap="20";
                 		biayaIcu="40";
                 		anekaPerawatan = "550";
                 		biayaOP="4,500";
                 		kunjungan="8";
                 		kunjunganSpesialis="63";
                 		biayaBAPerawatan="65";
                 		manfaatKemoterapi="400";
                 		biayaSekaligus="200";
                 		maksPerkejadian="5,000";
                 		maksPenggantian="10,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 2 ){ // plan B
                 		jenisSantunan="Plan B";
                 		biayaInap="40";
                 		biayaIcu="80";
                 		anekaPerawatan = "950";
                 		biayaOP="8,700";
                 		kunjungan="13";
                 		kunjunganSpesialis="99";
                 		biayaBAPerawatan="125";
                 		manfaatKemoterapi="800";
                 		biayaSekaligus="400";
                 		maksPerkejadian="10,000";
                 		maksPenggantian="20,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 3 ){ // plan C
                 		jenisSantunan="Plan C";
                 		biayaInap="60";
                 		biayaIcu="120";
                 		anekaPerawatan = "1,350";
                 		biayaOP="12,900";
                 		kunjungan="15";
                 		kunjunganSpesialis="135";
                 		biayaBAPerawatan="185";
                 		manfaatKemoterapi="1,200";
                 		biayaSekaligus="600";
                 		maksPerkejadian="15,000";
                 		maksPenggantian="30,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 4 ){ // plan D
                 		jenisSantunan="Plan D";
                 		biayaInap="80";
                 		biayaIcu="160";
                 		anekaPerawatan = "1,750";
                 		biayaOP="17,100";
                 		kunjungan="23";
                 		kunjunganSpesialis="171";
                 		biayaBAPerawatan="245";
                 		manfaatKemoterapi="1,600";
                 		biayaSekaligus="800";
                 		maksPerkejadian="20,000";
                 		maksPenggantian="40,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 5 ){ // plan E
                 		jenisSantunan="Plan E";
                 		biayaInap="100";
                 		biayaIcu="200";
                 		anekaPerawatan = "2,150";
                 		biayaOP="21,300";
                 		kunjungan="25";
                 		kunjunganSpesialis="207";
                 		biayaBAPerawatan="305";
                 		manfaatKemoterapi="2,000";
                 		biayaSekaligus="1,000";
                 		maksPerkejadian="25,000";
                 		maksPenggantian="50,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 6 ){ // plan F
                 		jenisSantunan="Plan F";
                 		biayaInap="150";
                 		biayaIcu="300";
                 		anekaPerawatan = "3,150";
                 		biayaOP="31,800";
                 		kunjungan="35";
                 		kunjunganSpesialis="279";
                 		biayaBAPerawatan="425";
                 		manfaatKemoterapi="3,000";
                 		biayaSekaligus="1,500";
                 		maksPerkejadian="37,500";
                 		maksPenggantian="75,000";
                 	}else if( cepr01030103Form.getLadiesMedExpenseInnerLimitCd() == 7 ){ // plan G
                 		jenisSantunan="Plan G";
                 		biayaInap="200";
                 		biayaIcu="400";
                 		anekaPerawatan = "4,150";
                 		biayaOP="42,300";
                 		kunjungan="45";
                 		kunjunganSpesialis="315";
                 		biayaBAPerawatan="485";
                 		manfaatKemoterapi="4,000";
                 		biayaSekaligus="2,000";
                 		maksPerkejadian="50,000";
                 		maksPenggantian="100,000";
                 	}
    			}
    			params = new HashMap<String, Object>();         	
                params.put( "plan", jenisSantunan );            
                result.add( params ); 
                
                params = new HashMap<String, Object>();
                params.put( "no", "1"  );          
                params.put( "subject", "Biaya Kamar dan Menginap (maks 365 hari/tahun)" );
                params.put( "descr", biayaInap  );          
                result.add( params );     
                
                params = new HashMap<String, Object>();
                params.put( "no", "2"  );          
                params.put( "subject", "Biaya Kamar ICU/ICCU (maks 60 hari/tahun)" );
                params.put( "descr", biayaIcu  );          
                result.add( params );     
                
                params = new HashMap<String, Object>();
                params.put( "no", "3"  );          
                params.put( "subject", "Biaya Aneka Perawatan di RS (Per kejadian)" );
                params.put( "descr", anekaPerawatan  );          
                result.add( params );     
                             
                params = new HashMap<String, Object>();
                params.put( "no", "4"  );          
                params.put( "subject", "Biaya Operasi/Pembedahan" );
                params.put( "descr", biayaOP  );          
                result.add( params );   
                
                params = new HashMap<String, Object>();
                params.put( "no", "5"  );          
                params.put( "subject", "Biaya Kunjungan Dokter di Rumah Sakit – per hari (maks 1 kunjungan/hari)" );
                params.put( "descr", kunjungan  );          
                result.add( params );   
             
                params = new HashMap<String, Object>();
                params.put( "no", "6"  );          
                params.put( "subject", "Biaya Konsultasi Dokter Spesialis di Rumah Sakit (per kejadian)" );
                params.put( "descr", kunjunganSpesialis  );          
                result.add( params );   
                
                params = new HashMap<String, Object>();
                params.put( "no", "7"  );          
                params.put( "subject", "Kemoterapi" );
                params.put( "descr", manfaatKemoterapi  );          
                result.add( params );   
              
                params = new HashMap<String, Object>();
                params.put( "no", "8"  );          
                params.put( "subject", "Biaya Pengobatan Sekaligus" );
                params.put( "descr", biayaSekaligus  );          
                result.add( params );  
                
                params = new HashMap<String, Object>();
                params.put( "no", "9"  );          
                params.put( "subject", "Biaya Sebelum dan Sesudah Perawatan Rumah Sakit (7 hari sebelum Rawat Inap dan 30 hari setelah Rawat Inap)" );
                params.put( "descr", biayaBAPerawatan  );          
                result.add( params );  
            
                params = new HashMap<String, Object>();         	
                params.put( "limit", maksPenggantian );            
                result.add( params );   
    		}
    	}
    	
    	return result;
    }
    
    //SME
    public List<Map<String, Object>> getRiderEkaSehatExtraListMap( )
    {
    	List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatExtraFlag() ) ||  
       		 ( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatExtraFlag() ) &&  CommonConst.DISPLAY_TRUE.equals( cepr01030103Form.getEkaSehatExtraDisplay() ) ) )
        {
        	if( ( cepr01030103Form.getEkaSehatExtraCd() != null && cepr01030103Form.getEkaSehatExtraCd() > 0 )
        			||( cepr01030103Form.getEkaSehatExtraCd()!= null && cepr01030103Form.getEkaSehatExtraCd() > 0 ) )
        	{
        		Map<String, Object> params;
        		String jenisSantunan = null;
        		String biayaInap = null;
        		String biayaIcu = null;        		
        		String maksPenggantian = null;
        		String biayaKemoterapi = null;
        		String biayaSantunan = null;
        		String biayaSantunanRawatInap = null;
        		Integer jenis = null;
        		String wilayahCashless = "";
        		String wilayahCashless2 = "";
        		String biayaAkomodasi = null;
        		if(  cepr01030103Form.getEkaSehatExtraCd() != null && cepr01030103Form.getEkaSehatExtraCd() > 0 ){
        			jenis = cepr01030103Form.getEkaSehatExtraCd();
        		}
            	if( jenis == 1 ){ // plan A
            		jenisSantunan="Plan A-200";
            		biayaInap="200";
            		biayaIcu="400";
            		maksPenggantian="100,000";            		
            		biayaKemoterapi="50,000";
            		biayaSantunan="3,000";
            		biayaSantunanRawatInap="100";
            		wilayahCashless = "Indonesia";
            	}else if( jenis == 2 ){ // plan B
            		jenisSantunan="Plan B-300";
            		biayaInap="300";
            		biayaIcu="600";
            		maksPenggantian="150,000";
            		biayaKemoterapi="75,000";
            		biayaSantunan="3,000";
            		biayaSantunanRawatInap="200";
            		wilayahCashless = "Indonesia";
            	}else if( jenis == 3 ){ // plan C
            		jenisSantunan="Plan C-400";
            		biayaInap="400";
            		biayaIcu="800";
            		maksPenggantian="200,000";
            		biayaKemoterapi="100,000";
            		biayaSantunan="5,000";
            		biayaSantunanRawatInap="200";
            		wilayahCashless = "Indonesia";
            	}else if( jenis == 4 ){ // plan D
            		jenisSantunan="Plan D-500";
            		biayaInap="500";
            		biayaIcu="1,000";
            		maksPenggantian="400,000";
            		biayaKemoterapi="As Charge";
            		biayaSantunan="7,500";
            		biayaSantunanRawatInap="400";
            		wilayahCashless = "Indonesia";
            	}else if( jenis == 5 ){ // plan E
            		jenisSantunan="Plan E-800";
            		biayaInap="800";
            		biayaIcu="1,600";
            		maksPenggantian="700,000";
            		biayaKemoterapi="As Charge";
            		biayaSantunan="7,500";
            		biayaSantunanRawatInap="500";
            		wilayahCashless = "Indonesia";
            	}else if( jenis == 6 ){ // plan F
            		jenisSantunan="Plan F-1000";
            		biayaInap="1,000";
            		biayaIcu="2,000";
            		maksPenggantian="900,000";
            		biayaKemoterapi="As Charge";
            		biayaSantunan="10,000";
            		biayaSantunanRawatInap="800";
            		wilayahCashless = "Indonesia";
            	}else if( jenis == 7 ){ // plan G
            		jenisSantunan="Plan G-1500";
            		biayaInap="Kamar dengan 1 tempat tidur *)";
            		biayaIcu="3,000";
            		maksPenggantian="1,400,000";
            		biayaKemoterapi="As Charge";
            		biayaSantunan="10,000";
            		biayaSantunanRawatInap="1,000";
            		wilayahCashless = "Indonesia dan Malaysia";
            		wilayahCashless2 = "";
            		biayaAkomodasi = "750";
            	}else if( jenis == 8 ){ // plan H
            		jenisSantunan="Plan H-2000";
            		biayaInap="Kamar dengan 1 tempat tidur **)";
            		biayaIcu="4,000";
            		maksPenggantian="1,900,000";
            		biayaKemoterapi="As Charge";
            		biayaSantunan="20,000";
            		biayaSantunanRawatInap="1,000";
            		wilayahCashless = "Indonesia dan Malaysia";
            		wilayahCashless2 = "";
            		biayaAkomodasi = "1,000";
            	}else if( jenis == 9 ){ // plan I
            		jenisSantunan="Plan I-5000";
            		biayaInap="Kamar dengan 1 tempat tidur ***)";
            		biayaIcu="4,000";
            		maksPenggantian="5,000,000";
            		biayaKemoterapi="As Charge";
            		biayaSantunan="50,000";
            		biayaSantunanRawatInap="2,000";
            		wilayahCashless = "Seluruh dunia kecuali Amerika Serikat";
            		wilayahCashless2 = "";
            		biayaAkomodasi = "1,500";
            	}else if( jenis == 10 ){ // plan J
            		jenisSantunan="Plan J-7500";
            		biayaInap="Kamar dengan 1 tempat tidur ****)";
            		biayaIcu="4,000";
            		maksPenggantian="6,000,000";            		
            		biayaKemoterapi="As Charge";
            		biayaSantunan="50,000";
            		biayaSantunanRawatInap="2,000";
            		wilayahCashless = "Seluruh dunia kecuali Amerika Serikat";
            		wilayahCashless2 = "";
            		biayaAkomodasi = "2,500";
            	}
            	
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "Jenis Plan    ");
                 params.put( "descr", jenisSantunan  );
                 params.put( "detail", ""  );
                 params.put( "plan", jenisSantunan  );
                 result.add( params );
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Kamar dan Menginap" );
                 params.put( "descr", biayaInap  );               
                 params.put( "detail", "maks 365 hari/tahun"  );             
                 params.put( "plan", jenisSantunan  );
                 result.add( params );
                 
                 if( jenis >= 7 ){ // plan G - plan J
                	 params = new HashMap<String, Object>();
                     params.put( "subject", "- Biaya Akomodasi (maks usia Tertanggung 17 Thn)");
                     params.put( "descr", biayaAkomodasi  );
                     params.put( "detail", "maks 60 hari/tahun"  );
                     params.put( "plan", jenisSantunan  );
                     result.add( params );
                 }
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Kamar ICU/ICCU" );
                 params.put( "descr", biayaIcu  );
                 params.put( "detail", "maks 60 hari/tahun"  );
                 params.put( "plan", jenisSantunan  );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Aneka Perawatan di RS" );
                 params.put( "descr", "As Charge"  );
                 params.put( "detail", ""  );
                 params.put( "plan", jenisSantunan  );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Operasi/Pembedahan" );
                 params.put( "descr", "As Charge"  );
                 params.put( "detail", ""  );
                 params.put( "plan", jenisSantunan  );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Kunjungan Dokter/Dokter ahli di RS" );
                 params.put( "descr", "As Charge"  );
                 params.put( "detail", ""  );
                 params.put( "plan", jenisSantunan  );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Pemeriksaan Laboratorium & Test Diagnostik" );
                 params.put( "descr", "As Charge"  );
                 params.put( "detail", "7 hari sebelum RI"  );
                 params.put( "plan", jenisSantunan  );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Biaya Konsultasi Lanjutan" );
                 params.put( "descr", "As Charge"  );
                 params.put( "detail", "30 hari setelah RI"  );
                 params.put( "plan", jenisSantunan  );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "- Transportasi dgn Mobil Ambulance ke RS (darurat)" );
                 params.put( "descr", "As Charge"  );
                 params.put( "detail", ""  );
                 params.put( "plan", jenisSantunan  );
                 result.add( params );
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "- Rawat Jalan akibat Kecelakaan" );
                 params.put( "descr", "As Charge"  );
                 params.put( "detail", ""  );
                 params.put( "plan", jenisSantunan  );
                 result.add( params );
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "- Rawat Gigi akibat Kecelakaan" );
                 params.put( "descr", "As Charge"  );
                 params.put( "detail", ""  );
                 params.put( "plan", jenisSantunan  );
                 result.add( params );
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "- Perawatan di Rumah" );
                 params.put( "descr", "As Charge"  );
                 params.put( "detail", "Maks 60 hari/tahun"  );
                 params.put( "plan", jenisSantunan  );
                 result.add( params );
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "- Rawat Jalan Cuci Darah/Kemoterapi" );
                 params.put( "descr", biayaKemoterapi );
                 params.put( "detail", ""  );
                 params.put( "plan", jenisSantunan  );
                 result.add( params );
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "- Santunan AIDS/HIV" );
                 params.put( "descr", biayaSantunan  );
                 params.put( "detail", ""  );
                 params.put( "plan", jenisSantunan  );
                 result.add( params );
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "- Santunan Kematian" );
                 params.put( "descr", biayaSantunan  );
                 params.put( "detail", ""  );
                 params.put( "plan", jenisSantunan  );
                 result.add( params );
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "- Santunan Harian Rawat Inap" );
                 params.put( "descr", biayaSantunanRawatInap  );
                 params.put( "detail", "Maks 25 hari/tahun"  );
                 params.put( "plan", jenisSantunan  );
                 result.add( params );
                 
            	 params = new HashMap<String, Object>();
                 params.put( "subject", "* Batas Maks. Penggantian pertahun" );
                 params.put( "descr", maksPenggantian  );
                 params.put( "detail", ""  );
                 params.put( "plan", jenisSantunan  );
                 result.add( params ); 
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "* Fasilitas (Cashless)" );
                 params.put( "descr", wilayahCashless  );
                 params.put( "detail", wilayahCashless2  );
                 params.put( "plan", jenisSantunan  );
                 result.add( params ); 
                 
                 params = new HashMap<String, Object>();
                 params.put( "subject", "* Polis berlaku di seluruh dunia" );
                 params.put( "descr", ""  );
                 params.put( "detail", ""  );
                 params.put( "plan", jenisSantunan  );
                 result.add( params ); 
        	}
        }    
        return result;
 }
    
    public List<Map<String, Object>> getRincianRiderEkaSehatExtra()
    {
   	 List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
     if( CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatExtraFlag() ) )  
     {
     	if( ( cepr01030103Form.getEkaSehatExtraCd() != null && cepr01030103Form.getEkaSehatExtraCd() > 0 ) )
     	{
     		Map<String, Object> params;
     		String jenisSantunan = null;
     		String biayaInap = null;
     		String biayaIcu = null;
     		String maksPenggantian = "-max-";
     		String biayaKemoterapi = null;
    		String biayaSantunan = null;
    		String biayaSantunanRawatInap = null;     		
     		Integer jenis = null;
     		String biayaAkomodasi = null;     
     		if(  cepr01030103Form.getEkaSehatExtraCd() != null && cepr01030103Form.getEkaSehatExtraCd() > 0 ){
     			jenis = cepr01030103Form.getEkaSehatExtraCd();
     		}
         	if( jenis == 1 ){ // plan A
         		jenisSantunan="Plan A-200";
         		biayaInap="200";
         		biayaIcu="400";
         		maksPenggantian="100,000";
         		
         		biayaKemoterapi="50,000";
        		biayaSantunan="3,000";
        		biayaSantunanRawatInap="100";
         	}else if( jenis == 2 ){ // plan B
         		jenisSantunan="Plan B-300";
         		biayaInap="300";
         		biayaIcu="600";
         		maksPenggantian="150,000";
         		biayaKemoterapi="75,000";
        		biayaSantunan="3,000";
        		biayaSantunanRawatInap="200";
         	}else if( jenis == 3 ){ // plan C
         		jenisSantunan="Plan C-400";
         		biayaInap="400";
         		biayaIcu="800";
         		maksPenggantian="200,000";
         		biayaKemoterapi="100,000";
        		biayaSantunan="5,000";
        		biayaSantunanRawatInap="200";
         	}else if( jenis == 4 ){ // plan D
         		jenisSantunan="Plan D-500";
         		biayaInap="500";
         		biayaIcu="1,000";
         		maksPenggantian="400,000";
         		biayaKemoterapi="As Charged";
        		biayaSantunan="7,500";
        		biayaSantunanRawatInap="400";
         	}else if( jenis == 5 ){ // plan E
         		jenisSantunan="Plan E-800";
         		biayaInap="800";
         		biayaIcu="1,600";
         		maksPenggantian="700,000";
         		biayaKemoterapi="As Charged";
        		biayaSantunan="7,500";
        		biayaSantunanRawatInap="500";
         	}else if( jenis == 6 ){ // plan F
         		jenisSantunan="Plan F-1000";
         		biayaInap="1,000";
         		biayaIcu="2,000";
         		maksPenggantian="900,000";
         		biayaKemoterapi="As Charged";
        		biayaSantunan="10,000";
        		biayaSantunanRawatInap="800";
         	}else if( jenis == 7 ){ // plan G
         		jenisSantunan="Plan G-1500";
         		biayaInap="Kamar dengan 1 tempat tidur*)";
         		biayaIcu="3,000";
         		maksPenggantian="1,400,000";
         		biayaKemoterapi="As Charged";
        		biayaSantunan="10,000";
        		biayaSantunanRawatInap="1,000";
        		biayaAkomodasi = "750";
         	}else if( jenis == 8 ){ // plan H
         		jenisSantunan="Plan H-2000";
         		biayaInap="Kamar dengan 1 tempat tidur**)";
         		biayaIcu="4,000";
         		maksPenggantian="1,900,000";
         		biayaKemoterapi="As Charged";
        		biayaSantunan="20,000";
        		biayaSantunanRawatInap="1,000";
        		biayaAkomodasi = "1,000";
         	}else if( jenis == 9 ){ // plan I
         		jenisSantunan="Plan I-5000";
         		biayaInap="Kamar dengan 1 tempat tidur***)";
         		biayaIcu="4,000";
         		maksPenggantian="5,000,000";
         		biayaKemoterapi="As Charged";
        		biayaSantunan="50,000";
        		biayaSantunanRawatInap="2,000";   
        		biayaAkomodasi = "1,500";
         	}else if( jenis == 10 ){ // plan J
         		jenisSantunan="Plan J-7500";
         		biayaInap="Kamar dengan 1 tempat tidur****)";
         		biayaIcu="4,000";
         		maksPenggantian="6,000,000";
         		biayaKemoterapi="As Charged";
        		biayaSantunan="50,000";
        		biayaSantunanRawatInap="2,000";
        		biayaAkomodasi = "2,500";
         	}
         	 params = new HashMap<String, Object>();         	
             params.put( "plan", jenisSantunan );  
           //  params.put( "plan2", jenisSantunan ); 
             result.add( params ); 
          
         	
             params = new HashMap<String, Object>();
             params.put( "no", "1"  );          
             params.put( "subject", "Biaya Kamar dan Menginap di Rumah Sakit (maksimal 365 hari) per hari" );
             params.put( "descr", biayaInap  );          
             result.add( params );
              
             params = new HashMap<String, Object>();
             params.put( "no", "2"  );    
             params.put( "subject", "Biaya Kamar ICU/ICCU (maks 60 hari) per hari" );
             params.put( "descr", biayaIcu  );           
             result.add( params );
              
             if( jenis >= 7 ){
            	 params = new HashMap<String, Object>();
            	 params.put( "no", "3"  );    
                 params.put( "subject", "Biaya Akomodasi (maks 60 hari/tahun untuk usia Tertanggung kurang dari 18 Thn)" );
                 params.put( "descr", biayaAkomodasi  );           
                 result.add( params );
            	 
             }
             
         	 params = new HashMap<String, Object>();
         	 if( jenis >= 7 ){             	
             	 params.put( "no", "4"  );    
         	 }else{
         		params.put( "no", "3"  );
         	 }
         	 params.put( "subject", "Biaya Aneka Perawatan di Rumah Sakit" );
             params.put( "descr", "As Charge"  );      
             result.add( params );
              
         	 params = new HashMap<String, Object>();
         	 if( jenis >= 7 ){             	
             	 params.put( "no", "5"  );    
         	 }else{
         		params.put( "no", "4"  );
         	 }   
         	 params.put( "subject", "Biaya Operasi/Pembedahan" );
             params.put( "descr", "As Charge"  );       
             result.add( params );
              
         	 params = new HashMap<String, Object>();
         	 if( jenis >= 7 ){             	
            	 params.put( "no", "6"  );    
        	 }else{
        		params.put( "no", "5"  );
        	 }    
         	 params.put( "subject", "Biaya Kunjungan Dokter di Rumah Sakit" );
             params.put( "descr", "As Charge"  );
             params.put( "detail", ""  );
             result.add( params );
              
             params = new HashMap<String, Object>();
             if( jenis >= 7 ){             	
            	 params.put( "no", "7"  );    
        	 }else{
        		params.put( "no", "6"  );
        	 }    
             params.put( "subject", "Biaya Kunjungan Dokter Spesialis di Rumah Sakit" );
             params.put( "descr", "As Charge"  );
             params.put( "detail", ""  );
             result.add( params );
              
         	 params = new HashMap<String, Object>();
         	 if( jenis >= 7 ){             	
            	 params.put( "no", "8"  );    
        	 }else{
        		params.put( "no", "7"  );
        	 }    
         	 params.put( "subject", "Biaya Pemeriksaan Laboratorium & Test Diagnostik (7 hari sebelum Rawat Inap)" );
             params.put( "descr", "As Charge"  );            
             result.add( params );
              
         	 params = new HashMap<String, Object>();
         	 if( jenis >= 7 ){             	
            	 params.put( "no", "9"  );    
        	 }else{
        		params.put( "no", "8"  );
        	 }       
         	 params.put( "subject", "Biaya Konsultasi Lanjutan (30 hari setelah Rawat Inap)" );
             params.put( "descr", "As Charge"  );            
             result.add( params );
              
         	 params = new HashMap<String, Object>();
         	 if( jenis >= 7 ){             	
            	 params.put( "no", "10"  );    
        	 }else{
        		params.put( "no", "9"  );
        	 }        
         	 params.put( "subject", "Transportasi dgn Mobil Ambulance ke RS (darurat)" );
             params.put( "descr", "As Charge"  );         
             result.add( params );
             
             params = new HashMap<String, Object>();
             if( jenis >= 7 ){             	
            	 params.put( "no", "11"  );    
        	 }else{
        		params.put( "no", "10"  );
        	 }        
         	 params.put( "subject", "Rawat Jalan Akibat Kecelakaan" );
             params.put( "descr", "As Charge"  );         
             result.add( params );
             
             params = new HashMap<String, Object>();
             if( jenis >= 7 ){             	
            	 params.put( "no", "12"  );    
        	 }else{
        		params.put( "no", "11"  );
        	 }       
         	 params.put( "subject", "Rawat Gigi Akibat Kecelakaan" );
             params.put( "descr", "As Charge"  );         
             result.add( params );
             
             params = new HashMap<String, Object>();
             if( jenis >= 7 ){             	
            	 params.put( "no", "13"  );    
        	 }else{
        		params.put( "no", "12"  );
        	 }        
         	 params.put( "subject", "Perawatan di Rumah" );
             params.put( "descr", "As Charge"  );         
             result.add( params );
             
             params = new HashMap<String, Object>();
             if( jenis >= 7 ){             	
            	 params.put( "no", "14"  );    
        	 }else{
        		params.put( "no", "13"  );
        	 }       
         	 params.put( "subject", "Rawat Jalan Cuci Darah/Kemoterapi" );
             params.put( "descr", biayaKemoterapi  );         
             result.add( params );
           
             params = new HashMap<String, Object>();
             if( jenis >= 7 ){             	
            	 params.put( "no", "15"  );    
        	 }else{
        		params.put( "no", "14"  );
        	 }      
         	 params.put( "subject", "Santunan AIDS/HIV" );
             params.put( "descr", biayaSantunan );         
             result.add( params );
             
             params = new HashMap<String, Object>();
             if( jenis >= 7 ){             	
            	 params.put( "no", "16"  );    
        	 }else{
        		params.put( "no", "15"  );
        	 }        
         	 params.put( "subject", "Santunan Kematian" );
             params.put( "descr", biayaSantunan );         
             result.add( params );
             
             params = new HashMap<String, Object>();
             if( jenis >= 7 ){             	
            	 params.put( "no", "17"  );    
        	 }else{
        		params.put( "no", "16"  );
        	 }          
         	 params.put( "subject", "Santunan Harian Rawat Inap" );
             params.put( "descr", biayaSantunanRawatInap );         
             result.add( params );
           
       
             
             params = new HashMap<String, Object>();
             params.put( "plan2", jenisSantunan ); 
             params.put( "limit", maksPenggantian );     
             result.add( params );
             
             
            // params = new HashMap<String, Object>();
            // params.put( "limit", maksPenggantian );            
            // result.add( params ); 
     	}
     }    
     return result;
    }
    
    public String checkParticapantEkaSehatExtra()
    {
       String result = null;
       List<ParticipantVO> participantVOList = cepr01030114Form.getParticipantVOList();
       for( int i = 0 ; i < participantVOList.size() ; i ++ )
       {
    	   if( participantVOList.get(i).getName() != null && !"".equals( participantVOList.get(i).getName() ) ){
    		   result = "yes";
    		   break;
    	   }else{
    		   result = "no";
    	   }
       }
       return result;
    }
    
    public List<Map<String, Object>> getRiderEkaSehatExtraParticipantsList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 189 || cepr01030102Form.getLeftPartOfBusinessCd() == 183 || cepr01030102Form.getLeftPartOfBusinessCd() == 193 || cepr01030102Form.getLeftPartOfBusinessCd() == 201 || CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatExtraFlag() ) )
        {
            List<ParticipantVO> participantVOList = cepr01030114Form.getParticipantVOList();
            participantVOList = removeEmptyParticipantVOFromTheList( participantVOList );
            Map<String, Object> params;
            Integer idx = 0;
            for( ParticipantVO vo : participantVOList )
            {
                idx++;
                params = new HashMap<String, Object>();
                params.put( "idx_eka_sehat", idx.toString().concat( "." ) );
                params.put( "name_eka_sehat", vo.getName() );
                params.put( "age_eka_sehat", vo.getAge().toString() );
                result.add( params );
            }
        }

        return result;
    }   

    public List<Map<String, Object>> getRincianRiderEkaSehatExtraParticipantsList()
    {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 189 || cepr01030102Form.getLeftPartOfBusinessCd() == 183 || cepr01030102Form.getLeftPartOfBusinessCd() == 193 || cepr01030102Form.getLeftPartOfBusinessCd() == 201 || CommonConst.CHECKED_TRUE.equals( cepr01030103Form.getEkaSehatExtraFlag()) ) 
        {
            List<ParticipantVO> participantVOList = cepr01030114Form.getParticipantVOList();
            participantVOList = removeEmptyParticipantVOFromTheList( participantVOList );
            Map<String, Object> params;
            Integer idx = 0;
            for( ParticipantVO vo : participantVOList )
            {
                idx++;
                params = new HashMap<String, Object>();
                params.put( "idx", idx.toString().concat( "." ) );
                params.put( "name", vo.getName() );
                params.put( "age", vo.getAge().toString() );
                result.add( params );
            }
        }

        return result;
    }
   
}