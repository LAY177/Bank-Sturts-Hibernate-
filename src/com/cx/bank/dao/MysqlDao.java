 
 /*
  * 连接数据库，获取数据库里的信息
  * 提供方法，对数据库里的表进行增删改查
  */
 package com.cx.bank.dao;

 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;
 import java.sql.Timestamp;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import com.cx.bank.model.*;
 
 

 public class MysqlDao implements BankDaoInterface {
	 private  DBConnManager dbm;
	 private Connection con=null;
     private ResultSet rs=null;
     private Statement st=null;
     
     //构造方法 加载数据库驱动
     public MysqlDao() throws ClassNotFoundException{
    	dbm= new DBConnManager();
    	con=dbm.getConnection();
     }
     
     /*
      * 用户信息查询并发封装到UserBean里
      * @para UserList 返回用户信息集合
      */
     public List<UserBean> queryUserList(){
		List<UserBean> UserList=new ArrayList<>();
    	String sql="SELECT user_id,user_name,user_password,user_flag,balance FROM t_user"; 
		try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				UserBean u=new UserBean();
				u.setUserId(rs.getInt("user_id"));
				u.setUsername(rs.getString("user_name"));
				u.setPwd(rs.getString("user_password"));
				u.setUserflag(rs.getInt("user_flag"));
				u.setBalance(rs.getDouble("balance"));
				UserList.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return UserList; 
     }
     
     /*
      * 将查询到的用户信息封装到集合的方法
      * 方便接下来方法的调用
      */
     private List<UserBean> queryUserBySql(String sql){
    	 List<UserBean> UserList=new ArrayList<>();
 		try {
 			st=con.createStatement();
 			rs=st.executeQuery(sql);
 			while(rs.next()){
 				UserBean u=new UserBean();
 				u.setUserId(rs.getInt("user_id"));
 				u.setUsername(rs.getString("user_name"));
 				u.setPwd(rs.getString("user_password"));
 				u.setUserflag(rs.getInt("user_flag"));
 				u.setBalance(rs.getDouble("balance"));
 				UserList.add(u);
 			}
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
     	return UserList; 
    	 
     } 
     
     /*
      * 根据用户Id查询用户信息（包括余额）
      * @para id -业务层方法传过来的用户编号
      */
     public UserBean getUserById(int id){
		String sql="SELECT * FROM t_user WHERE user_id='"+id+"'";
		List<UserBean> UserList =queryUserBySql(sql);
		if(UserList.size()==0){
			return null;
		}
    	 
    	 return UserList.get(0); //查询单个对象
     }
     
     /*
      * 根据传过来的id和密码查找用户
      * @para id - 用户编号
      * @para password - 用户密码
      */
      public boolean findUser(int id,String password){
    	  boolean flag=false;
    	  //通过调用queryUserList()方法取得所有用户的信息
          List<UserBean> UserList=queryUserList();
          //遍历用户信息集合，查找是否有与传过来的id和密码匹配的用户
          for(int i=0;i<UserList.size();i++){
        	  int UserId=UserList.get(i).getUserId();
        	  String UserPwd=UserList.get(i).getPwd();
        	  if(UserId==id){
        		  if(UserPwd.equals(password)){
        			  flag=true;
        			  MoneyBean.getInstance().setMoney(UserList.get(i).getBalance());
        			  break;
        		  }
        	  }
          }
    	  return flag;
      }
       
     /*
      * 添加新用户
      * @para userName - 用户名
      * @para userPwd - 用户密码
      * @para userFlag - 用户冻结与否标记
      * @para Balance - 用户余额
      */
     public boolean InsertUser(String userName,String userPwd,int userFlag,double Balance){
		int num=0;  //受影响条数
		boolean flag=true;
		boolean flag1=false;
		String sql="INSERT INTO t_user(user_name,user_password,user_flag,balance)VALUES('"+userName+"','"+userPwd+"','"+userFlag+"','"+Balance+"')";
		List<UserBean> userList=new ArrayList<UserBean>();
    	userList=queryUserList();
    	for(int i=0;i<userList.size();i++){
    		if(userName.equals(userList.get(i).getUsername())){
    			flag= false;
    			break;
    		}else{
    			flag=true;
    		}
    	}
    	if(flag==true){
    		try {
    			st=con.createStatement();
    			num=st.executeUpdate(sql);
    			if(num==1){
    				flag1=true;
    			}
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}else{
    		flag=false;
    	}
		return flag1;	 
     }
     
     /*
      *通过用户名和密码查找单个用户信息返回
      *@para  username - 用户名
      *@para password - 密码 
      */
     public List<UserBean> FindId(String username,String password){
    	 String sql="SELECT * FROM t_user WHERE user_name='"+username+"' AND user_password='"+password+"'";
    	 List<UserBean> UserList=new ArrayList<>();
  		try {
  			st=con.createStatement();
  			rs=st.executeQuery(sql);
  			while(rs.next()){
  				UserBean u=new UserBean();
  				u.setUserId(rs.getInt("user_id"));
  				u.setUsername(rs.getString("user_name"));
  				u.setPwd(rs.getString("user_password"));
  				u.setUserflag(rs.getInt("user_flag"));
  				u.setBalance(rs.getDouble("balance"));
  				UserList.add(u);
  			}
  			System.out.println(UserList);//[]没有取到
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
		 return UserList;
    	 
     }
     
     /*
      * 更新用户余额
      * @para userId - 用户编号
      * @para userName - 用户名
      * @para Balance - 用户余额
      */
     public int UpdateUser(int userId,double Balance){
		 int num=0; //受影响条数
         String sql="UPDATE t_user SET balance='"+Balance+"' WHERE user_id='"+userId+"'";
         try {
			st=con.createStatement();
			num=st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
		 return num;
    	 
     }
     
     /*
      * 转账-更新被转账用户的余额
      * @para touserId - 用户编号
      * @para touserName - 用户名
      * @para toBalance - 用户余额
      */
     public int UpdateTUser(int touserId,double toBalance){
    	 int num=0;
         UserBean UserList=getUserById(touserId); 
    	 double balance=UserList.getBalance();
    	 double after_balance=balance-toBalance;
    	 String sql1="UPDATE t_user SET balance='"+after_balance+"' WHERE user_id='"+touserId+"'";
    	 try {
 			st=con.createStatement();
 			num=st.executeUpdate(sql1);
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
		return num;
     }
     
     /*
      * 插入用户操作明细
      * @para logType  - 操作类型
      * @para logAmount - 操作金额
      * @para userId - 用户编号 
      */
     public int InsertLog(String logType,double logAmount,int userId,String logTime) throws ParseException{
    	 int num=0;
    	 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	 Date date=format.parse(logTime);
    	 Timestamp datetime=new Timestamp(date.getTime());
    	 String sql="INSERT INTO t_log(log_type,log_amount,userid,log_time)VALUES('"+logType+"','"+logAmount+"','"+userId+"','"+datetime+"')";
    	 try {
			st=con.createStatement();
			num=st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return num;
     }
     
     /*
      * 根据用户id查询用户操作明细
      * @para userId - 用户编号
      * 
      */
     public List<LogBean> queryLog(int userId){
    	 List<LogBean> LogList=new ArrayList<LogBean>();
    	 String sql="SELECT * FROM t_log WHERE userid='"+userId+"'";
    	 try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				LogBean l= new LogBean();
				l.setLogID(rs.getInt("log_id"));
				l.setLogType(rs.getString("log_type"));
				l.setLogAmount(rs.getDouble("log_amount"));
				l.setUserID(rs.getInt("userid"));
				l.setLogTime(format.format(rs.getTimestamp("log_time")));
				LogList.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return LogList;
     }
     
     /*
      * 查询管理员表信息
      */
     public List<AdminBean> queryAdmin(){
    	List<AdminBean> adminlist=new ArrayList<AdminBean>();
    	String sql="SELECT * FROM t_admin";
    	try {
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				AdminBean ab=new AdminBean();
				ab.setAdminId(rs.getInt("admin_id"));
				ab.setAdminName(rs.getString("admin_name"));
				ab.setAdminPwd(rs.getString("admin_password"));
				adminlist.add(ab);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
		return adminlist; 
     }
     
     /*
      * 根据传过来的管理员账户编号和密码判断用户是否存在
      * @param adminId 管理员账户编号
      * @param adminPwd  管理员账户密码
      * 
      */
     public boolean findAdmin(int adminId,String adminPwd){
    	 boolean flag=false;
		 List<AdminBean> ablist=new ArrayList<>();
		 ablist=queryAdmin();
		 for(int i=0;i<ablist.size();i++){
			int Id=ablist.get(i).getAdminId();
			String Pwd=ablist.get(i).getAdminPwd();
			if(Id==adminId){
				if(Pwd.equals(adminPwd)){
					flag=true;
					break;
				}
			}else{
				flag=false;
			}
		 }
    	 return flag;
    	 
     }
      
     /*
      * 根据传过来的用户编号和冻结信息修改指定用户冻结信息
      * @param userId 用户编号
      * @param userFlag 用户冻结信息
      */
     public boolean updateFlag(int userId,int userFlag){
    	 boolean flag=false;
    	 int num=0;
    	 String sql="UPDATE t_user SET user_flag='"+userFlag+"' WHERE user_id='"+userId+"' ";
    	 try {
			st=con.createStatement();
			num=st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 if(num==1){
    		 flag=true;
    	 }
    	 return flag;
     }
     
 }
