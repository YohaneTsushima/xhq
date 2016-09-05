package com.aq.entity;

public class City {
	private int id;
	private String cityName;
	private String apikey;
	private int custId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getApikey() {
		return apikey;
	}
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public City(int id, String cityName, String apikey, int custId) {
		super();
		this.id = id;
		this.cityName = cityName;
		this.apikey = apikey;
		this.custId = custId;
	}
	public City(){}
}
