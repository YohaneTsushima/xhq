package com.aq.entity;

import java.util.Date;

public class Usr_info {
	private int id;
	private String usr_name;
	private String login_name;
	private String password;
	private String email;
	private String phone;
	private City city;
	private String address;
	private String head;
	private int usr_type;
	private Date regDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsr_name() {
		return usr_name;
	}
	public void setUsr_name(String usr_name) {
		this.usr_name = usr_name;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public int getUsr_type() {
		return usr_type;
	}
	public void setUsr_type(int usr_type) {
		this.usr_type = usr_type;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Usr_info(int id, String usr_name, String login_name, String password, String email, String phone, City city,
			String address, String head, int usr_type, Date regDate) {
		super();
		this.id = id;
		this.usr_name = usr_name;
		this.login_name = login_name;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.city = city;
		this.address = address;
		this.head = head;
		this.usr_type = usr_type;
		this.regDate = regDate;
	}
	public Usr_info(){}
}
