
/*
 * t_user表的实体类
 */

 package com.HBean;

 public class User {
   
	 //用户编号
	private Integer id;
	
	//用户名
	private String username;
	
	//用户密码
	private String password;
	
	//用户冻结信息
	private Integer userflag;
	
	//用户余额
	private Double balance;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getUserflag() {
		return userflag;
	}

	public void setUserflag(Integer userflag) {
		this.userflag = userflag;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
 }
