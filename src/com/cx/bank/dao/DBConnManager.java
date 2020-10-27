
/*
 * 连接池管理类,从property文件里拿到连接池创建信息
 */
 package com.cx.bank.dao;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
 import java.util.Properties;

 public class DBConnManager {
    //连接池名
	 private String poolname;
	 //驱动程序名
	 private String drivername;
	 //数据库标识
	 private String dbid;
	 //用户名
	 private String username;
	 //密码
	 private String password;
	 //最大连接数
	 private int maxconn;
	 
	 DBConnPool connpool;
	 
	 public DBConnManager() {
		this.connpool=createPool();
	 } 
	 
	 /*创建连接池*/
		private DBConnPool createPool() {
			 //创建properties对象
			 Properties props=new Properties();
//			 File f=new File("/com/file/connpool.properties");
//			 System.out.println(f);
			 //if(f.exists()) {
			 String path = "com/file/connpool.properties";
				 try {
					String context = DBConnManager.class.getClassLoader().getResource("").toURI().getPath();
					FileInputStream file=new FileInputStream(new File(context + path));
					props.load(file);
					file.close();
				 }catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				 } catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 //}
			 poolname=props.getProperty("poolname");
			 drivername=props.getProperty("drivername");
			 dbid=props.getProperty("dbid");
			 username=props.getProperty("username");
			 password=props.getProperty("password");
			 System.out.println(drivername);
			 try {
				String maxconn1=props.getProperty("maxconn");
				System.out.println(maxconn1);
			    maxconn=Integer.parseInt(maxconn1);
			    connpool=new DBConnPool(poolname,drivername,dbid,username,password,maxconn);
			 }catch(NumberFormatException e) {
				 e.printStackTrace();
			 }
			 
			return connpool;
		}
	 
	 //释放连接回连接池
		public void releaseConnection(Connection con) {
			if(connpool!=null) {
			   connpool.releaseConnection(con);
			}
		}
		
	//得到连接池里的连接
		public Connection getConnection() {
			
			return connpool.getConnection();
			
		}
		
	//关闭所有连接
		public void CloseConn() {
			
			connpool.closeConn();
		}
		public static void main(String[] args) {
			new DBConnManager();
		}
	 
 }
