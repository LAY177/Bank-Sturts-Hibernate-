 
 /*
  * 用户信息封装
  * @para userId - 用户编号
  * @para username - 用户名
  * @para pwd - 用户密码
  * @para userflag - 冻结与否标记（1 为账户可用，0为账户冻结）
  * @para balance - 用户余额
  */
 package com.cx.bank.model;

  public class UserBean {
     
	 private int userId;
	 
     private String username;
     
     private String pwd;
     
     private int userflag;
     
     private double balance;
	  
	 public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int isUserflag() {
		return userflag;
	}

	public void setUserflag(int userflag) {
		this.userflag = userflag;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}


	

	
     
}
