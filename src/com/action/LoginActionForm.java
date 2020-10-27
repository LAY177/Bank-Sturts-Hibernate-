package com.action;

import org.apache.struts.action.ActionForm;

public class LoginActionForm extends ActionForm {
     
	private String userID; //用户编号
     
    private String password;  //用户密码
    
    private String username;  //注册时用到的用户名
    
    private String uflagID;   //查询用户冻结信息的编号
    
    private String flaginfo;  //用户冻结信息
    
    private String inputmoney; //存入金额
    
    private String takemoney; //取出的金额
    
    private String transferId; //要转入的账户编号
    
    private String transfermoney; //转出的金额

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getTransfermoney() {
		return transfermoney;
	}

	public void setTransfermoney(String transfermoney) {
		this.transfermoney = transfermoney;
	}

	public String getTakemoney() {
		return takemoney;
	}

	public void setTakemoney(String takemoney) {
		this.takemoney = takemoney;
	}

	public String getInputmoney() {
		return inputmoney;
	}

	public void setInputmoney(String inputmoney) {
		this.inputmoney = inputmoney;
	}

	public String getUflagID() {
		return uflagID;
	}

	public void setUflagID(String uflagID) {
		this.uflagID = uflagID;
	}

	public String getFlaginfo() {
		return flaginfo;
	}

	public void setFlaginfo(String flaginfo) {
		this.flaginfo = flaginfo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	} 
}
