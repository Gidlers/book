package com.it.dao.entity;

import java.io.Serializable;

import com.it.tool.Base;


public class Details extends Base implements Serializable {

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	private static final long serialVersionUID = 1L;
	private String id;
	private String login_id;
	private String book_id;
	private int value;
	private int state;
}
