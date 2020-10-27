
 /*
  * 连接池类，根据要求创建新连接，直到到最大连接数
  */
 package com.cx.bank.dao;

 import java.sql.*;
 import java.util.*;

 public class DBConnPool {
  
  //实际中使用的连接数
	private int inUse=0;
  //空闲连接
	private Vector connections=new Vector();
  //连接池名
	private String poolname;
  //数据库标识
	private String dbid;
  //驱动程序名
	private String drivername;
  //数据库账号
	private String username;
  //数据库密码
	private String password;
  //最大连接数
	private int maxconn;
	
	public DBConnPool(String poolname,String drivername,String dbid,String username,String password,int maxconn) {
		this.poolname=poolname;
		this.drivername=drivername;
		this.dbid=dbid;
		this.username=username;
		this.password=password;
		this.maxconn=maxconn;
	}
	
	//创建新的连接
	private Connection newConnection() {
		Connection con=null;
		try {
			//加载驱动程序
			Class.forName(drivername);
			//建立连接
			con=DriverManager.getConnection(dbid,username,password);
			System.out.println("数据库连接成功！");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("加载驱动失败！");
			return null;
		}
		//返回该连接
		return con;		
	}
	
	//从连接池拿到一个连接
	public synchronized Connection getConnection() {
		Connection con=null;
		if(connections.size()>0) {
			//获取连接列表中第一个连接//拿完就删，故而拿的一直都是第一条
			con=(Connection)connections.elementAt(0);
			connections.removeElementAt(0);
			//若此连接已经关闭，则继续获取
			try {
				if(con.isClosed()) {
					con=getConnection();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(maxconn==0||inUse<maxconn) {//若实际使用的连接数小于最大连接数，就创建一个新连接
			con=newConnection();
		}
		if(con!=null) {
			inUse++;
		}
		//返回连接
		return con;
	}
	
	//将连接返回给连接池（释放连接）
	public synchronized void releaseConnection(Connection con) {
		//将指定连接加入到空闲连接集合末尾
		connections.addElement(con);
		//连接实际使用数减一
		inUse--;
		
	}
	
	//关闭所有连接
	public synchronized void closeConn() {
		Enumeration allConnections=connections.elements();
		while(allConnections.hasMoreElements()) {
			Connection con=(Connection)allConnections.nextElement();
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		connections.removeAllElements();
	}
 }
