
 /*
  * 管理员表实体类
  */

 package com.HBean;

 public class Admin {
   
	 //管理员编号
	 private Integer aid;
	 
	 //管理员账户名
	 private String adminname;
	 
	 //管理员账户密码
	 private String apassword;

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getApassword() {
		return apassword;
	}

	public void setApassword(String apassword) {
		this.apassword = apassword;
	}
	 
 }
