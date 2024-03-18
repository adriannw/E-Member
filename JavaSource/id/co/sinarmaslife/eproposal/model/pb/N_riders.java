package id.co.sinarmaslife.eproposal.model.pb;

import java.io.Serializable;

import id.co.sinarmaslife.eproposal.common.constant.Hardcode;
import id.co.sinarmaslife.eproposal.web.Cepr01030102Form;
import id.co.sinarmaslife.eproposal.web.Cepr01030103Form;
import id.co.sinarmaslife.standard.util.MappingUtil;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Proposal
 * Function Id         	: 
 * Program Name   		: N_riders
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: Oct 1, 2007 11:18:09 AM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

public class N_riders implements Serializable
{
    /**********************************************************************
	 * Program History
	 *
	 * Project Name      	: E-Proposal
	 * Function Id         	: id.co.sinarmaslife.eproposal.model.pb
	 * Program Name   		: 
	 * Description         	:
	 * Environment      	: Java  1.5.0_06
	 * Author               : Fadly Mathendra
	 * Version              : 1.0
	 * Creation Date    	: Aug 30, 2012 10:21:28 AM
	 *
	 * Version      Re-fix date                 Person in charge    Description
	 *
	 *
	 * Copyright(C) 2012-Asuransi Jiwa Sinarmas. All Rights Reserved.
	 ***********************************************************************/
	private static final long serialVersionUID = -2475810815754901857L;
	public String[] is_note;
    public String is_sy;

    public N_riders()
    {
        is_sy = "";
    }

    public String of_ket( int ai_no )
    {
        String ls_nama = "";
        if( is_sy.equals( "" ) )
        {
            switch( ai_no )
            {
                case 1:
                    ls_nama = "SMiLe Personal Accident"; break;
                case 2:
                    ls_nama = "Hospital Cash Plan Rider : memberikan santunan harian rawat inap dan ICU oleh karena sakit ataupun kecelakaan."; break;
                case 3:
                    ls_nama = "Total Permanent Disability Rider (TPD Rider) : Tertanggung menderita cacat tetap total maka akan dibayarkan 100% Uang Pertanggungan (Maks. Rp. 1.000.000.000,- / USD. 100.000,-)."; break;
                case 4:
                    ls_nama = "Critical Illness - Rider : Tertanggung terdiagnosa salah satu dari 31 penyakit kritis maka akan dibayarkan 50% Uang Pertanggungan (Maks. Rp. 500.000.000,- / USD. 50.000,-)."; break;
                case 5:
                    ls_nama = "Waiver 60 - TPD Rider : Tertanggung menderita cacat tetap total maka Tertanggung dibebaskan dari pembayaran Premi Pokok berikutnya sampai Tertanggung berusia 60 tahun."; break;
                case 6:
                    ls_nama = "Payor 25 TPD/Death - Rider : Pemegang Polis meninggal dunia atau menderita cacat tetap total maka Tertanggung dibebaskan dari pembayaran Premi Pokok berikutnya sampai Tertanggung (anak) berusia 25 tahun atau Pemegang Polis mencapai usia 65 tahun (mana yang terjadi lebih dahulu)."; break;
                case 7:
                    ls_nama = "Waiver 60 CI - Rider :Tertanggung terdiagnosa salah satu dari 31 penyakit kritis maka Tertanggung dibebaskan dari pembayaran Premi Pokok berikutnya sampai Tertanggung berusia 60 tahun."; break;
                case 8:
                    ls_nama = "Payor 25 CI/Death - Rider : Pemegang Polis meninggal dunia atau didiagnosa menderita salah satu dari 31 penyakit kritis maka Tertanggung dibebaskan dari pembayaran Premi Pokok berikutnya sampai Tertanggung (anak) berusia 25 tahun atau Pemegang Polis mencapai usia 65 tahun (mana yang terjadi lebih dahulu)."; break;
                case 9:
                    ls_nama = "Payor 25 CI - Rider : Pemegang Polis didiagnosa menderita salah satu dari 31 penyakit kritis maka Tertanggung dibebaskan dari pembayaran Premi Pokok berikutnya sampai Tertanggung (anak) berusia 25 tahun atau Pemegang Polis mencapai usia 65 tahun (mana yang terjadi lebih dahulu)."; break;
                case 10:
                    ls_nama = "Payor Spouse 60 TPD / Death : Jika Pemegang Polis meninggal dunia atau menderita Cacat Tetap Total maka tertanggung dibebaskan dari pembayaran premi pokok sampai tertanggung mencapai usia 60 tahun."; break;
                case 11:
                    ls_nama = "Hospital Cash Plan Family Rider : memberikan santunan harian manfaat rawat inap, ICU dan tindakan pembedahan oleh karena sakit ataupun kecelakaan. Manfaat ini dapat diikuti oleh pasangan dan maksimal 3 orang anak dengan mengikutsertakan sebagai Tertanggung Tambahan pada rider ini dengan besarnya biaya asuransi sesuai dengan usia masing-masing dari Tertanggung Tambahan dimaksud."; break;
                case 12:
                    ls_nama = "Term Rider : Tertanggung meninggal karena sebab apapun diberikan 100% Uang Pertanggungan (Maksimum 100% dari UP Dasar dan usia Tertanggung s/d 70 tahun)."; break;
                default:break;
            }
        }
        else
        {
            switch( ai_no )
            {
                case 1:
                    ls_nama = "SMiLe Personal Accident"; break;
                case 2:
                    ls_nama = "Hospital Cash Plan Rider : memberikan santunan harian rawat inap dan ICU oleh karena sakit ataupun kecelakaan."; break;
                case 3:
                    ls_nama = "Total Permanent Disability Rider (TPD Rider) : Peserta menderita cacat tetap total maka akan dibayarkan 100% Manfaat Asuransi Syariah (Maks. Rp. 1.000.000.000,- / USD. 100.000,-)."; break;
                case 4:
                    ls_nama = "Critical Illness - Rider : Peserta terdiagnosa salah satu dari 31 penyakit kritis maka akan dibayarkan 50% Manfaat Asuransi Syariah (Maks. Rp. 500.000.000,- / USD. 50.000,-)."; break;
                case 5:
                    ls_nama = "Waiver 60 - TPD Rider : Peserta menderita cacat tetap total maka Tertanggung dibebaskan dari pembayaran Premi Pokok berikutnya sampai Peserta berusia 60 tahun."; break;
                case 6:
                    ls_nama = "Payor 25 TPD/Death - Rider : Pemegang Polis meninggal dunia atau menderita cacat tetap total maka Peserta dibebaskan dari pembayaran Premi Pokok berikutnya sampai Peserta (anak) berusia 25 tahun atau Pemegang Polis mencapai usia 65 tahun (mana yang terjadi lebih dahulu)."; break;
                case 7:
                    ls_nama = "Waiver 60 CI - Rider :Tertanggung terdiagnosa salah satu dari 31 penyakit kritis maka Tertanggung dibebaskan dari pembayaran Premi Pokok berikutnya sampai Tertanggung berusia 60 tahun."; break;
                case 8:
                    ls_nama = "Payor 25 CI/Death - Rider : Pemegang Polis meninggal dunia atau didiagnosa menderita salah satu dari 31 penyakit kritis maka Peserta dibebaskan dari pembayaran Premi Pokok berikutnya sampai Peserta (anak) berusia 25 tahun atau Pemegang Polis mencapai usia 65 tahun (mana yang terjadi lebih dahulu)."; break;
                case 9:
                    ls_nama = "Payor 25 CI - Rider : Pemegang Polis didiagnosa menderita salah satu dari 31 penyakit kritis maka Peserta dibebaskan dari pembayaran Premi Pokok berikutnya sampai Peserta (anak) berusia 25 tahun atau Pemegang Polis mencapai usia 65 tahun (mana yang terjadi lebih dahulu)."; break;
                case 10:
                    ls_nama = "Payor Spouse 60 TPD/Death  : jika pemegang polis meninggal dunia dan menderita cacat tetap total maka peserta dibebaskan dari pembayaran pokok sampai peserta mencapai usia 60 tahun."; break;
                case 11:
                    ls_nama = "Hospital Cash Plan Family Rider : memberikan santunan harian manfaat rawat inap, ICU dan tindakan pembedahan oleh karena sakit ataupun kecelakaan. Manfaat ini dapat diikuti oleh pasangan dan maksimal 3 orang anak dengan mengikutsertakan sebagai Tertanggung Tambahan pada rider ini dengan besarnya biaya asuransi sesuai dengan usia masing-masing dari Tertanggung Tambahan dimaksud."; break;
                default:break;
            }
        }

        return ls_nama;
    }


    public String of_nama( int ai_no, Cepr01030102Form cepr01030102Form, Cepr01030103Form cepr01030103Form)
    {
        String ls_nama = "";
        String syariah = "";
        if( cepr01030102Form.getLeftPartOfBusinessCd() == 153 || cepr01030102Form.getLeftPartOfBusinessCd() == 160 || cepr01030102Form.getLeftPartOfBusinessCd() == 224
        		|| cepr01030102Form.getLeftPartOfBusinessCd() == 202 || cepr01030102Form.getLeftPartOfBusinessCd() == 200 || cepr01030102Form.getLeftPartOfBusinessCd() == 199)// produk produk syariah
        {
        	syariah = " Syariah";
        }else{
        	syariah = "";
        }
        switch( ai_no )
        {
            case 1:
            	if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_CERDAS 
            	&& ( cepr01030102Form.getRightPartOfBusinessCd() >= 10 && cepr01030102Form.getRightPartOfBusinessCd() <= 12 )  )// simpol 
            	{
            		ls_nama = "Personal Accident"+syariah; break;
            	}else{
            		ls_nama = "SMiLe Personal Accident"+syariah;break;
            	}
            case 2:
                ls_nama = "SMiLe Hospital Protection"+syariah; break;
            case 3:
                ls_nama = "SMiLe Total Permanent Disability (TPD)"+syariah; break;
            case 4:
                ls_nama = "SMiLe Critical Illness (CI)"+syariah; break;
            case 5:
            	if( cepr01030102Form.getLeftPartOfBusinessCd() == 120 )// cerdas 
            	{
            		ls_nama = "Waiver TPD"+syariah; break;
            	}else{
            		ls_nama = "Waiver 60 TPD"+syariah; break;
            	}
            case 6:
                if( cepr01030102Form.getLeftPartOfBusinessCd() == 120 )// cerdas 
            	{
                	ls_nama = "Payor TPD/Death"+syariah; break;
            	}else{
            		ls_nama = "Payor 25 TPD/Death"+syariah; break;
            	}
            case 7:
            	if( cepr01030102Form.getLeftPartOfBusinessCd() == 120 )// cerdas 
            	{
            		ls_nama = "Waiver CI"+syariah; break;
            	}else{
            		ls_nama = "SMiLe Waiver 60 CI"+syariah; break;
            	}
                
            case 8:
                ls_nama = "Payor 25 CI/Death"+syariah; break;
            case 9:
               
                if( cepr01030102Form.getLeftPartOfBusinessCd() == 120 )// cerdas 
            	{
                	 ls_nama = "Payor CI"+syariah; break;
            	}else{
            		 ls_nama = "Payor 25 CI"+syariah; break;
            	}
            case 10:
                ls_nama = "SMiLe Payor Spouse 60 TPD/Death"+syariah; break;
            case 11:
            	if( cepr01030102Form.getLeftPartOfBusinessCd() == Hardcode.PRODUK_CERDAS 
            	&& ( cepr01030102Form.getRightPartOfBusinessCd() >= 10 && cepr01030102Form.getRightPartOfBusinessCd() <= 12 )  )// simpol 
            	{
            		ls_nama = "Hospital Cash Plan (Family)"+syariah; break;
            	}else{
            		ls_nama = "SMiLe Hospital Protection"+syariah+" (Family)";break;
            	}
            case 12:
                ls_nama = "SMiLe Term Insurance"+syariah; break;
            case 13:
                ls_nama = "SMiLe Medical"+syariah+" (Benefit As Charge)"; break;
            case 14:
                ls_nama = "Swine"+syariah; break;
            case 15:
                ls_nama = "SMiLe Medical"+syariah+" (Benefit Inner Limit)"; break;
            case 16:
            	ls_nama = "SMiLe Hospital Protection"+syariah+" (+)"; break;
            case 17:
            	String label = MappingUtil.getLabel(cepr01030103Form.getWaiverTpdCiChooseList(), cepr01030103Form.getWaiverTpdCiChooseCd());
            	ls_nama = "SMiLe Waiver "+label+" TPD/CI"+syariah; break;
            case 18:
                ls_nama = "SMiLe Payor 25 TPD/CI/Death"+syariah; break;
            case 19:
                ls_nama = "SMiLe Ladies Insurance"+syariah; break;
            case 20:
                ls_nama =  "SMiLe Ladies Hospital Protection"+syariah; break;
            case 21:
                ls_nama = "SMiLe Ladies Medical(Benefit As Charge)"+syariah; break;
            case 22:
                ls_nama = "SMiLe Ladies Medical(Benefit Inner Limit)"+syariah; break;
            case 23:
            	Integer typePayorTpdCiDeath = 5;
            	if( cepr01030102Form.getLeftPartOfBusinessCd() == 120 || cepr01030102Form.getLeftPartOfBusinessCd() == 202 )// cerdas 
            	{
            		typePayorTpdCiDeath = cepr01030102Form.getTermOfPayment();
            	}else{
            		typePayorTpdCiDeath = cepr01030102Form.getPremiumFurloughYear();
            	}
            	ls_nama = "SMiLe Payor "+ typePayorTpdCiDeath+" TPD/CI/Death"+syariah; break;
            case 24:
            	Integer typeWaiverTpdCiDeath = 5;
            	if( cepr01030102Form.getLeftPartOfBusinessCd() == 120 || cepr01030102Form.getLeftPartOfBusinessCd() ==  202 )// cerdas 
            	{
            		typeWaiverTpdCiDeath = cepr01030102Form.getTermOfPayment();
            	}else{
            		typeWaiverTpdCiDeath = cepr01030102Form.getPremiumFurloughYear();
            	}
            	ls_nama = "SMiLe Waiver " + typeWaiverTpdCiDeath +" TPD/CI"+syariah; break;
            case 25:
                ls_nama = "SMiLe Scholarship"+syariah; break;
            case 26:
            	ls_nama = "SMiLe Baby"; break;
            case 27:
            	ls_nama = "SMiLe Early Stage Critical Illness 99"+syariah; break;
            case 28:
            	//label = MappingUtil.getLabel(cepr01030103Form.getMedicalPlusChooseList(), cepr01030103Form.getMedicalPlusChooseCd());
            	ls_nama = "SMiLe Medical+ "+syariah; break;
            case 29:
                ls_nama = "SMiLe Medical Extra "+syariah+""; break;
//            case 14:
//                ls_nama = "SMiLe Medical(Benefit Inner Limit)"+syariah; break;
//            case 15:
//                ls_nama = "SMiLe Hospital Protection (+)"+syariah; break;
//            case 16:
//            	ls_nama = "Payor 25 TPD/CI/Death"+syariah; break;
//            case 17:
//            	String label = MappingUtil.getLabel(cepr01030103Form.getWaiverTpdCiChooseList(), cepr01030103Form.getWaiverTpdCiChooseCd());
//            	ls_nama = "Waiver "+label+" TPD/CI"+syariah; break;
//            case 18:
//                ls_nama = "SMiLe Ladies Insurance"+syariah; break;
//            case 19:
//                ls_nama = "SMiLe Ladies Hospital Protection"+syariah; break;
//            case 20:
//                ls_nama = "SMiLe Ladies Medical(Benefit As Charge)"+syariah; break;
//            case 21:
//                ls_nama = "SMiLe Ladies Medical(Benefit Inner Limit)"+syariah; break;
//            case 22:
//                ls_nama = "Payor TPD/CI/Death"+syariah; break;
           /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
            case 30:
            	 String usiaMaxTerm = MappingUtil.getLabel(cepr01030103Form.getTerm5575RiderChooseList(), cepr01030103Form.getTerm5575RiderChooseCd());
                ls_nama = "SMiLe Term Insurance"  + syariah+""; break;
              default:break;
        }

        return ls_nama;
    }
    
    public String of_nama_syariah( int ai_no, Cepr01030102Form cepr01030102Form, Cepr01030103Form cepr01030103Form)
    {
        String ls_nama = "";
        String syariah = "";        
        syariah = " Syariah";
        
        switch( ai_no )
        {
            case 1:
                ls_nama = "SMiLe Personal Accident"+syariah; break;
            case 2:
                ls_nama = "SMiLe Hospital Protection"+syariah; break;
            case 3:
                ls_nama = "SMiLe Total Permanent Disability (TPD)"+syariah; break;
            case 4:
                ls_nama = "SMiLe Critical Illness (CI)"+syariah; break;
            case 5:
                ls_nama = "Waiver 60 TPD"+syariah; break;
            case 6:
                ls_nama = "Payor 25 TPD/Death"+syariah; break;
            case 7:
                ls_nama = "Waiver 60 CI"+syariah; break;
            case 8:
                ls_nama = "Payor 25 CI/Death"+syariah; break;
            case 9:
                ls_nama = "Payor 25 CI"+syariah; break;
            case 10:
                ls_nama = "Payor Spouse 60 TPD/Death"+syariah; break;
            case 11:
                ls_nama = "SMiLe Hospital Protection Syariah (Family)"; break;
            case 12:
                ls_nama = "SMiLe Term Insurance Syariah Rider"; break;
            case 13:
                ls_nama = "SMiLe Medical Syariah (Benefit As Charge)"; break;
            case 14:
                ls_nama = "Swine"+syariah; break;
            case 15:
                ls_nama = "SMiLe Medical Syariah (Benefit Inner Limit)"; break;
            case 16:
            	ls_nama = "SMiLe Hospital Protection"+syariah+" (+)"; break;
            case 17:
            	String label = MappingUtil.getLabel(cepr01030103Form.getWaiverTpdCiChooseList(), cepr01030103Form.getWaiverTpdCiChooseCd());
            	ls_nama = "SMiLe Waiver "+label+" TPD/CI"+syariah; break;
            case 18:
                ls_nama = "SMiLe Payor 25 TPD/CI/Death"+syariah; break;
            case 19:
                ls_nama = "SMiLe Ladies Insurance"+syariah; break;
            case 20:
                ls_nama = "SMiLe Ladies Hospital Protection"+syariah; break;
            case 21:
                ls_nama = "SMiLe Ladies Medical Syariah(Benefit As Charge)"; break;
            case 22:
                ls_nama = "SMiLe Ladies Medical Syariah(Benefit Inner Limit)"; break;
            case 23:
            	Integer typePayorTpdCiDeath = cepr01030102Form.getPremiumFurloughYear();
            	ls_nama = "SMiLe Payor "+ typePayorTpdCiDeath+" TPD/CI/Death"+syariah; break;
            case 24:
            	Integer typeWaiverTpdCiDeath = cepr01030102Form.getPremiumFurloughYear();
            	ls_nama = "SMiLe Waiver " + typeWaiverTpdCiDeath +" TPD/CI"+syariah; break;
            case 25:
                ls_nama = "SMiLe Scholarship"+syariah; break;
            case 27:
            	ls_nama = "SMiLe Early Stage Critical Illness 99"+syariah; break;
            case 28:
            	ls_nama = "SMiLe Medical+"+syariah; break; 
            case 29:
            	ls_nama = "SMiLe Medical Extra"+syariah; break; 
           /* ADRIAN -- DEV TERM 55/65/75 RIDER -- */
            case 30:
           	 String usiaMaxTerm = MappingUtil.getLabel(cepr01030103Form.getTerm5575RiderChooseList(), cepr01030103Form.getTerm5575RiderChooseCd());
               ls_nama = "SMiLe Term Insurance" + syariah+""; break;
            default:break;
        }

        return ls_nama;
    }

    public void of_pa( int ai_risk )
    {
        String ls_text[] = new String[ 20 ];
        String temp;
        temp = ai_risk == 2? "+B) : " : ( ai_risk == 3? "+B+D : " : ") :" );
        ls_text[1] = "SMiLe Personal Accident Rider (PA risiko A" + temp;

        if( is_sy.equals( "" ) )
        {
            switch( ai_risk )
            {
                case 1:
                    ls_text[2] = "Tertanggung meninggal karena kecelakaan diberikan 100% Uang Pertanggungan (Maks. Rp. 1.000.000.000,- / USD. 100.000,-).";
                    break;
                case 2:
                    ls_text[2] = "1. Tertanggung meninggal karena kecelakaan diberikan 100% Uang Pertanggungan (Maks. Rp. 1.000.000.000,- / USD. 100.000,-).";
                    ls_text[3] = "2.- Tertanggung menderita Cacat Tetap seluruh anggota badan diberikan 100% Uang Pertanggungan (Maks. Rp. 1.000.000.000,- / USD. 100.000,-).";
                    ls_text[4] = " - Tertanggung menderita Cacat Tetap sebagian anggota badan diberikan prosentase tertentu dari UP berdasarkan Tabel Cacat.";
                    break;
                case 3:
                    ls_text[2] = "1. Tertanggung meninggal karena kecelakaan diberikan 100% Uang Pertanggungan (Maks. Rp. 1.000.000.000,- / USD. 100.000,-).";
                    ls_text[3] = "2.- Tertanggung menderita Cacat Tetap seluruh anggota badan diberikan 100% Uang Pertanggungan (Maks. Rp. 1.000.000.000,- / USD. 100.000,-).";
                    ls_text[4] = "   - Tertanggung menderita Cacat Tetap sebagian anggota badan diberikan prosentase tertentu dari UP berdasarkan Tabel Cacat.";
                    ls_text[5] = "3. Tertanggung menjalani pengobatan/perawatan di Rumah Sakit, maka akan dibayarkan manfaat sebesar biaya pengobatan/perawatan (Maks. 10% UP per Kejadian, dan 100% UP dalam 1 tahun Polis).";
                    break;
                default:break;
            }
        }
        else
        {
            switch( ai_risk )
            {
                case 1:
                    ls_text[2] = "Peserta meninggal karena kecelakaan diberikan 100% Manfaat Asuransi Syariah (Maks. Rp. 1.000.000.000,- / USD. 100.000,-)."; break;
                case 2:
                    ls_text[2] = "1. Peserta meninggal karena kecelakaan diberikan 100% Manfaat Asuransi Syariah (Maks. Rp. 1.000.000.000,- / USD. 100.000,-).";
                    ls_text[3] = "2.- Peserta menderita Cacat Tetap seluruh anggota badan diberikan 100% Manfaat Asuransi Syariah (Maks. Rp. 1.000.000.000,- / USD. 100.000,-).";
                    ls_text[4] = "   - Peserta menderita Cacat Tetap sebagian anggota badan diberikan prosentase tertentu dari Manfaat Asuransi Syariah berdasarkan Tabel Cacat.";
                    break;
                case 3:
                    ls_text[2] = "Peserta meninggal karena kecelakaan diberikan 100% Manfaat Asuransi Syariah (Maks. Rp. 1.000.000.000,- / USD. 100.000,-).";
                    ls_text[3] = "2.- Peserta menderita Cacat Tetap seluruh anggota badan diberikan 100% Manfaat Asuransi Syariah (Maks. Rp. 1.000.000.000,- / USD. 100.000,-).";
                    ls_text[4] = "   - Peserta menderita Cacat Tetap sebagian anggota badan diberikan prosentase tertentu dari Manfaat Asuransi Syariah berdasarkan Tabel Cacat.";
                    ls_text[5] = "3. Peserta menjalani pengobatan/perawatan di Rumah Sakit, maka akan dibayarkan manfaat sebesar biaya pengobatan/perawatan (Maks. 10% Manfaat Asuransi Syariah per Kejadian, dan 100% Manfaat Asuransi Syariah dalam 1 tahun Polis).";
                    break;
                default:break;
            }
        }
        is_note = ls_text;

    }




}