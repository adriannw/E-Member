package id.co.sinarmaslife.standard.model.vo;

import java.sql.Date;

public class Member {

	private String name;
	private String email;
	private Date tgl_lahir;
	
	private int id;
	private String nomor_ktp;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getTgl_lahir() {
		return tgl_lahir;
	}
	public void setTgl_lahir(Date tgl_lahir) {
		this.tgl_lahir = tgl_lahir;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomor_ktp() {
		return nomor_ktp;
	}
	public void setNomor_ktp(String nomor_ktp) {
		this.nomor_ktp = nomor_ktp;
	}
	
	public Member(int id, String name, String email, String nomor_ktp) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.nomor_ktp = nomor_ktp;
		
	}
	
}
