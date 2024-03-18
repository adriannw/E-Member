package id.co.sinarmaslife.eproposal.model.setup.sub;

import java.util.ArrayList;
import java.util.HashMap;

public class RiderDisplay {
		
	public int id_eligible ;
	public int rider_id ;
	public String lsbs_name ;
	public ArrayList<HashMap<String, Object>> riderDetailList ;
	
	public RiderDisplay(){
		riderDetailList = new ArrayList<HashMap<String,Object>>();
	}
	 
	public int getId_eligible() {
		return id_eligible;
	}
	public void setId_eligible(int id_eligible) {
		this.id_eligible = id_eligible;
	}
	public int getRider_id() {
		return rider_id;
	}
	public void setRider_id(int rider_id) {
		this.rider_id = rider_id;
	}
	public String getLsbs_name() {
		return lsbs_name;
	}
	public void setLsbs_name(String lsbs_name) {
		this.lsbs_name = lsbs_name;
	}
	public ArrayList<HashMap<String, Object>> getRiderDetailList() {
		return riderDetailList;
	}
	public void setRiderDetailList(
			ArrayList<HashMap<String, Object>> riderDetailList) {
		this.riderDetailList = riderDetailList;
	}
	 
}