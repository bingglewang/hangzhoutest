package com.xhgjky.bean;
// Generated 2018-6-27 10:36:16 by Hibernate Tools 3.2.2.GA

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

	private Long userId;
	private String userName;
	private String orderId;

	public User() {
	}

	public User(String userName, String orderId) {
		this.userName = userName;
		this.orderId = orderId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
