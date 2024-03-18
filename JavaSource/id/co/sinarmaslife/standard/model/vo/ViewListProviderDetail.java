package id.co.sinarmaslife.standard.model.vo;

import java.io.Serializable;

/**********************************************************************
 * Program History
 *
 * Project Name      	: E-Medical
 * Function Id         	: 
 * Program Name   		: OptionVO
 * Description         	:
 * Environment      	: Java  1.5.0_06
 * Author               : samuel
 * Version              : 1.0
 * Creation Date    	: May 14, 2007 1:38:45 PM
 *
 * Version      Re-fix date                 Person in charge    Description
 *
 *
 * Copyright(C) 2007-Asuransi Jiwa Sinarmas. All Rights Reserved.
 ***********************************************************************/

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ViewListProviderDetail implements Serializable
{
    protected final transient Log logger = LogFactory.getLog( getClass() );

    private String id;
    private String nama;
    private String password;
    private String nomor_hp;
    private String tgl_lahir;
    private String email;
    private int jk;
    private String no_ktp;
    private String foto;
    
    public ViewListProviderDetail()
    {
        logger.info( "---------- ViewListProvider constructor is called ..." );
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNomor_hp() {
		return nomor_hp;
	}

	public void setNomor_hp(String nomor_hp) {
		this.nomor_hp = nomor_hp;
	}

	public String getTgl_lahir() {
		return tgl_lahir;
	}

	public void setTgl_lahir(String tgl_lahir) {
		this.tgl_lahir = tgl_lahir;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getJk() {
		return jk;
	}

	public void setJk(int jk) {
		this.jk = jk;
	}

	public String getNo_ktp() {
		return no_ktp;
	}

	public void setNo_ktp(String no_ktp) {
		this.no_ktp = no_ktp;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	


    
    
}
