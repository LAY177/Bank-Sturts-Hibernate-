
/*
 * 封装管理员用户名密码
 * @param adminId 管理员编号
 * @param adminName  管理员账户名
 * @param adminPwd  管理员账户密码
 */
 package com.cx.bank.model;

 public class AdminBean {
    
	 private int adminId;
	 
	 private String adminName;
	 
	 private String adminPwd;

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	 
}
