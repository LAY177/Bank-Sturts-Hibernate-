
 /*
  * 用户操作明细表信息
  * @para logID - 日志标号
  * @para logType - 操作类型
  * @para logAmount - 操作金额
  * @para userID - 用户编号（外键）
  */
 package com.cx.bank.model;

 public class LogBean {
     
	 private int logID;
	 
	 private String logType;
	 
	 private double logAmount;
	 
	 private int userID;
	 
	 private String logTime;

	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public int getLogID() {
		return logID;
	}

	public void setLogID(int logID) {
		this.logID = logID;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public double getLogAmount() {
		return logAmount;
	}

	public void setLogAmount(double logAmount) {
		this.logAmount = logAmount;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
}
