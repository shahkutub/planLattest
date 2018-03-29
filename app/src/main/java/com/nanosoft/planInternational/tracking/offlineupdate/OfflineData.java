package com.nanosoft.planInternational.tracking.offlineupdate;

public class OfflineData {


	//private variables
	int _id;
	String scid;
	String jsonstr;
	
	// Empty constructor
	public OfflineData(){
		
	}
	// constructor
	public OfflineData(int id, String scid, String jsonstr){
		this._id = id;
		this.scid = scid;
		this.jsonstr = jsonstr;
	}
	
	// constructor
	public OfflineData(String scid, String jsonstr){
		this.scid = scid;
		this.jsonstr = jsonstr;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getScid() {
		return scid;
	}

	public void setScid(String scid) {
		this.scid = scid;
	}

	public String getJsonstr() {
		return jsonstr;
	}

	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}
}
