package com.it.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.it.tool.Base;

public class Account extends Base implements Serializable {

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getDatetime() {
		return datetime;
	}
	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}
	private static final long serialVersionUID = 2851984919968723924L;
	private String id;
	private String username;
	private String password;
	private Timestamp datetime;

}
