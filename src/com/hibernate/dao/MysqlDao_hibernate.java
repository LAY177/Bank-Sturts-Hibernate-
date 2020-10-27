
/*
 * hibernate框架持久层
 */


 package com.hibernate.dao;

 import java.sql.SQLException;
import java.text.ParseException;

 import java.util.ArrayList;

 import java.util.Iterator;
 import java.util.List;

 import org.hibernate.Session;

import com.HBean.Admin;
import com.HBean.Log;
 import com.HBean.User;
 import com.hibernate.dao.BankDaoInterface;
 import com.cx.bank.model.AdminBean;
 import com.cx.bank.model.MoneyBean;
 import com.cx.bank.model.UserBean;

 public class MysqlDao_hibernate implements BankDaoInterface {
     

	/*
     * 用户信息查询并发封装到UserBean里
     * @param UserList 返回用户信息集合
     */
	public List<User> queryUserList() {
	    Session session= null;
   	    List<User> UserList=new ArrayList<>();
        try {
        	session=HibernateUtils.getSession();
        	session.beginTransaction();
        	String sql="from User";
        	List users = session.createQuery(sql).list();
        	for(Iterator iter=users.iterator();iter.hasNext();) {
        		User user=(User)iter.next();
        		UserList.add(user);
        	}
        	
        	session.getTransaction().commit();
       
        }catch(Exception e) {
        	e.printStackTrace();
        	session.getTransaction().rollback();
        }finally {
        	HibernateUtils.closeSession(session);
        }
        return UserList;
	}

	/*
     * 根据用户Id查询用户信息（包括余额）
     * @param id -业务层方法传过来的用户编号
     */
	public User getUserById(int id) {
		 Session session= null;
		 User user=null;
	        try {
	        	session=HibernateUtils.getSession();
	        	session.beginTransaction();
	            user=(User)session.get(User.class, id);
	        	session.getTransaction().commit();
	       
	        }catch(Exception e) {
	        	e.printStackTrace();
	        	session.getTransaction().rollback();
	        }finally {
	        	HibernateUtils.closeSession(session);
	        }
		return user;
	}

	 /*
     * 根据传过来的id和密码查找用户
     * @param id - 用户编号
     * @param password - 用户密码
     */
	public boolean findUser(int id, String password) {
		boolean flag=false;
		 //通过调用queryUserList()方法取得所有用户的信息
        List<User> UserList=queryUserList();
        //遍历用户信息集合，查找是否有与传过来的id和密码匹配的用户
        for(int i=0;i<UserList.size();i++){
      	  int UserId=UserList.get(i).getId();
      	  String UserPwd=UserList.get(i).getPassword();
      	  if(UserId==id){
      		  if(UserPwd.equals(password)){
      			  flag=true;
      			  MoneyBean.getInstance().setMoney(UserList.get(i).getBalance());
      			  System.out.println(UserList.get(i).getBalance());//
      			  break;
      		  }
      	  }
        }
		return flag;
	}

	/*
     * 添加新用户
     * @param userName - 用户名
     * @param userPwd - 用户密码
     * @param userFlag - 用户冻结与否标记
     * @param Balance - 用户余额
     */
	public boolean InsertUser(String userName, String userPwd, int userFlag, double Balance) {
		Session session=null;
		int num=0;  //受影响条数
		boolean flag=true;
		boolean flag1=false;
		List<User> userList=new ArrayList<User>();
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
    			session=HibernateUtils.getSession();
            	session.beginTransaction();	
    			User user= new User();
    			user.setUsername(userName);
    		    user.setPassword(userPwd);
    		    user.setUserflag(userFlag);
    		    user.setBalance(Balance);
    			session.save(user);
    			session.getTransaction().commit();
    		    flag1=true;
    		}catch(Exception e) {
	        	e.printStackTrace();
	        	session.getTransaction().rollback();
	        	flag1=false;
	        }finally {
	        	HibernateUtils.closeSession(session);
	        }
    	}else{
    		flag1=false;
    	}
		return flag1;	
	}

	/*
     * 更新用户余额
     * @param userId - 用户编号
     * @param userName - 用户名
     * @param Balance - 用户余额
     */
	public int UpdateUser(int userId, double Balance) {
		 Session session=null; 
		 int num=0; //受影响条数
         //String sql="UPDATE th_user SET balance='"+Balance+"' WHERE user_id='"+userId+"'";
         try {
			 session=HibernateUtils.getSession();
			 session.beginTransaction();
			 User user=(User)session.get(User.class, userId);
			 user.setBalance(Balance);
			 session.save(user);
			 //session.createSQLQuery(sql);
			 
			 session.getTransaction().commit(); 
        	 num=1;
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			num=0;
		}finally {
        	HibernateUtils.closeSession(session);
        }
         
		 return num;
	}

	/*
     * 转账-更新被转账用户的余额
     * @param touserId - 用户编号
     * @param touserName - 用户名
     * @param toBalance - 用户余额
     */
	public int UpdateTUser(int touserId, double toBalance) {
		Session session=null;
		int num=0;
        User UserList=getUserById(touserId); 
   	    double balance=UserList.getBalance();
   	    System.out.println(balance);
   	    double after_balance=balance+toBalance;
   	    //String sql1="UPDATE th_user SET balance='"+after_balance+"' WHERE user_id='"+touserId+"'";
   	    try {
		   session=HibernateUtils.getSession();
		   session.beginTransaction();
		   //session.createSQLQuery(sql1);
		   User user=(User) session.get(User.class, touserId);
		   user.setBalance(after_balance);
		   session.getTransaction().commit();
   	       num=1;
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			num=0;
		}finally {
        	HibernateUtils.closeSession(session);
        }
		return num;
	}

	/*
     * 插入用户操作明细
     * @param logType  - 操作类型
     * @param logAmount - 操作金额
     * @param userId - 用户编号 
     */
	public int InsertLog(String logType, double logAmount, int userId, String logTime) throws ParseException {
		Session session=null;
		 int num=0;
    	 try {
			session=HibernateUtils.getSession();
			session.beginTransaction();
			User user=(User) session.get(User.class, userId);
			Log log=new Log();
    		log.setLogtype(logType);
    		log.setLogamount(logAmount);
    		log.setUser(user);  ////////////
    		log.setLogtime(logTime);////////
    		session.save(log);
    		session.getTransaction().commit();
    		num=1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			num=0;
		}finally {
        	HibernateUtils.closeSession(session);
        }
    	 return num;
	}

	/*
     * 根据用户id查询用户操作明细
     * @para userId - 用户编号
     * 
     */
	public List<Log> queryLog(int userId) {
		Session session=null;
		List<Log> LogList=new ArrayList<Log>();
    	 String sql=" from Log log where log.user.id='"+userId+"'";
    	 try {
			session=HibernateUtils.getSession();
			session.beginTransaction();
			List logs=session.createQuery(sql).list();
			for(Iterator iter=logs.iterator();iter.hasNext();) {
        		Log log=(Log)iter.next();
        		LogList.add(log);
        	}
			session.getTransaction().commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
        	HibernateUtils.closeSession(session);
        }
		return LogList;
	}

	/*
     *通过用户名和密码查找单个用户信息返回
     *@para  username - 用户名
     *@para password - 密码 
     */
	public List<User> FindId(String username, String password) {
		Session session=null;
		String sql="from User user where user.username='"+username+"' AND user.password='"+password+"'";
   	    List<User> UserList=new ArrayList<>();
 		try {
 			session=HibernateUtils.getSession();
			session.beginTransaction();
			List users=session.createQuery(sql).list();
			for(Iterator iter=users.iterator();iter.hasNext();) {
        		User user=(User)iter.next();
        		UserList.add(user);
        	}
			session.getTransaction().commit();
			System.out.println(UserList);//[]没有取到
			
 		}catch (Exception e) {
 			e.printStackTrace();
 			session.getTransaction().rollback();
 			
 		}finally {
        	HibernateUtils.closeSession(session);
        }
		 return UserList;
	}

	/*
     * 查询管理员表信息
     */
	public List<Admin> queryAdmin() {
		Session session=null;
		List<Admin> adminlist=new ArrayList<Admin>();
    	String sql="from Admin";
    	try {
    		session=HibernateUtils.getSession();
			session.beginTransaction();
			List admins=session.createQuery(sql).list();
			for(Iterator iter=admins.iterator();iter.hasNext();) {
        		Admin admin=(Admin)iter.next();
        		adminlist.add(admin);
        	}
			session.getTransaction().commit();	
			
		}catch (Exception e) {
			e.printStackTrace();
            session.getTransaction().rollback();
 			
 		}finally {
        	HibernateUtils.closeSession(session);
        }
    
		return adminlist; 
	}

	/*
     * 根据传过来的管理员账户编号和密码判断用户是否存在
     * @param adminId 管理员账户编号
     * @param adminPwd  管理员账户密码
     * 
     */
	public boolean findAdmin(int adminId, String adminPwd) {
		boolean flag=false;
		 List<Admin> ablist=new ArrayList<>();
		 ablist=queryAdmin();
		 for(int i=0;i<ablist.size();i++){
			int Id=ablist.get(i).getAid();
			String Pwd=ablist.get(i).getApassword();
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
	public boolean updateFlag(int userId, int userFlag) {
		Session session=null;
		boolean flag=false;
   	    int num=0;
   	    //String sql="UPDATE th_user SET user_flag='"+userFlag+"' WHERE user_id='"+userId+"' ";
   	    try {
			session=HibernateUtils.getSession();
			session.beginTransaction();
			//session.createSQLQuery(sql);
			User user=(User)session.get(User.class,userId);
			user.setUserflag(userFlag);
			session.getTransaction().commit();
			num=1;
   	    	
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
	 		num=0;	
 		}finally {
        	HibernateUtils.closeSession(session);
        }
   	   if(num==1){
   		  flag=true;
   	   }
   	   
   	  return flag;
    }

}
