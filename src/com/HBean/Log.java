
/*
 * 用户操作明细表实体类
 */

 package com.HBean;

 import java.util.Date;

 public class Log {
  
	 //日志编号
	 private Integer logid;
	 
	 //操作类型
	 private String logtype;
	 
	 //操作金额
	 private Double logamount;
	 
	 //用户编号
	 private User user;
	 
	 //操作时间
	// @Temporal(TemporalType.TIMESTAMP)
	 private String logtime;

	public String getLogtime() {
		return logtime;
	}

	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}

	public Integer getLogid() {
		return logid;
	}

	public void setLogid(Integer logid) {
		this.logid = logid;
	}

	public String getLogtype() {
		return logtype;
	}

	public void setLogtype(String logtype) {
		this.logtype = logtype;
	}

	public Double getLogamount() {
		return logamount;
	}

	public void setLogamount(Double logamount) {
		this.logamount = logamount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}		
	 
 }
