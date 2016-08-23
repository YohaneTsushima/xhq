package com.aq.entity;

import java.util.Date;

public class Members {

	private int id;
	private String memberName;
	private Date regDate;
	private String phone;
	private String email;
	private String loginName;
	private String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Members(int id, String memberName, Date regDate, String phone, String email, String loginName, String password) {
		super();
		this.id = id;
		this.memberName = memberName;
		this.regDate = regDate;
		this.phone = phone;
		this.email = email;
		this.loginName = loginName;
		this.password = password;
	}
	public Members(){}
}
