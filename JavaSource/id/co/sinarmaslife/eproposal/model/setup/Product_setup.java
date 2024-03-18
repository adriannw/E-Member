package id.co.sinarmaslife.eproposal.model.setup;

import java.util.ArrayList;

import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_bisnis_rider;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_kurs;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_packet_calc;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_packet_invest;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_packet_rider;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_pay_mode;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_premium_comb;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_product_acquisition;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_product_calc;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_product_invest;
import id.co.sinarmaslife.eproposal.model.setup.sub.Lst_product_setup;
import id.co.sinarmaslife.eproposal.model.setup.sub.RiderDisplay;

public class Product_setup {
	private Lst_product_setup lst_product_setup;
	private Lst_product_calc lst_product_calc;
	private ArrayList<Lst_premium_comb> premiumCombList;
	private ArrayList<Lst_bisnis_rider> riderList;
	private ArrayList<RiderDisplay> riderDisplayList;
	private ArrayList<Lst_kurs> kursList;
	private ArrayList<Lst_pay_mode> payModeList;
	private ArrayList<Lst_product_invest> jenisInvestList;
	private ArrayList<Lst_product_acquisition> productAcquisitionList;
	
	//PACKET
	private Lst_packet_calc lst_packet_calc;
	private ArrayList<Lst_packet_rider> packetRiderList;
	private ArrayList<Lst_packet_invest> packetInvestList;
	
	public Product_setup(){
		lst_product_setup = new Lst_product_setup();
		lst_product_calc = new Lst_product_calc();
		premiumCombList = new ArrayList<Lst_premium_comb>();
		riderList = new ArrayList<Lst_bisnis_rider>();
		riderDisplayList = new ArrayList<RiderDisplay>();
		kursList = new ArrayList<Lst_kurs>();
		payModeList = new ArrayList<Lst_pay_mode>();
		jenisInvestList = new ArrayList<Lst_product_invest>();
		productAcquisitionList = new ArrayList<Lst_product_acquisition>();
		
		lst_packet_calc = new Lst_packet_calc();
		packetRiderList = new ArrayList<Lst_packet_rider>();
		packetInvestList = new ArrayList<Lst_packet_invest>();
	}

	public Lst_product_setup getLst_product_setup() {
		return lst_product_setup;
	}

	public void setLst_product_setup(Lst_product_setup lst_product_setup) {
		this.lst_product_setup = lst_product_setup;
	}

	public Lst_product_calc getLst_product_calc() {
		return lst_product_calc;
	}

	public void setLst_product_calc(Lst_product_calc lst_product_calc) {
		this.lst_product_calc = lst_product_calc;
	}

	public ArrayList<Lst_premium_comb> getPremiumCombList() {
		return premiumCombList;
	}

	public void setPremiumCombList(ArrayList<Lst_premium_comb> premiumCombList) {
		this.premiumCombList = premiumCombList;
	}

	public ArrayList<Lst_bisnis_rider> getRiderList() {
		return riderList;
	}

	public void setRiderList(ArrayList<Lst_bisnis_rider> riderList) {
		this.riderList = riderList;
	}

	public ArrayList<RiderDisplay> getRiderDisplayList() {
		return riderDisplayList;
	}

	public void setRiderDisplayList(ArrayList<RiderDisplay> riderDisplayList) {
		this.riderDisplayList = riderDisplayList;
	}
	
	public ArrayList<Lst_kurs> getKursList() {
		return kursList;
	}

	public void setKursList(ArrayList<Lst_kurs> kursList) {
		this.kursList = kursList;
	}

	public ArrayList<Lst_pay_mode> getPayModeList() {
		return payModeList;
	}

	public void setPayModeList(ArrayList<Lst_pay_mode> payModeList) {
		this.payModeList = payModeList;
	}
	
	public ArrayList<Lst_product_invest> getJenisInvestList() {
		return jenisInvestList;
	}

	public void setJenisInvestList(ArrayList<Lst_product_invest> jenisInvestList) {
		this.jenisInvestList = jenisInvestList;
	}
	
	public ArrayList<Lst_product_acquisition> getProductAcquisitionList() {
		return productAcquisitionList;
	}

	public void setProductAcquisitionList(
			ArrayList<Lst_product_acquisition> productAcquisitionList) {
		this.productAcquisitionList = productAcquisitionList;
	}

	public Lst_packet_calc getLst_packet_calc() {
		return lst_packet_calc;
	}

	public void setLst_packet_calc(Lst_packet_calc lst_packet_calc) {
		this.lst_packet_calc = lst_packet_calc;
	}

	public ArrayList<Lst_packet_rider> getPacketRiderList() {
		return packetRiderList;
	}

	public void setPacketRiderList(ArrayList<Lst_packet_rider> packetRiderList) {
		this.packetRiderList = packetRiderList;
	}

	public ArrayList<Lst_packet_invest> getPacketInvestList() {
		return packetInvestList;
	}

	public void setPacketInvestList(ArrayList<Lst_packet_invest> packetInvestList) {
		this.packetInvestList = packetInvestList;
	}
	
	
	
}
