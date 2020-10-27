/*
 * 业务层
 * 主要用于存储方法
 */
package com.cx.bank.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hibernate.dao.BankDaoInterface;
import com.HBean.Log;
import com.HBean.User;
import com.cx.bank.factory.UserDaoFactory;
import com.cx.bank.model.*;
import com.cx.bank.util.AccountOverDrawnException;
import com.cx.bank.util.InvalidDepositException;
import com.cx.bank.util.MD5;

public class Managerlmpl implements ManagerInterface {
     MoneyBean moneyBean= MoneyBean.getInstance();
     private static Managerlmpl instance;
     UserBean user =new UserBean();
     BankDaoInterface factory=UserDaoFactory.getInstance();
    // Managerlmpl ml= Managerlmpl.getInstance();
     //构造方法
     private Managerlmpl() throws Exception{
    	
     }
     
     //拿到对象实例的方法
     public static  synchronized Managerlmpl getInstance() throws Exception{
    	 if(instance==null){
    		 instance=new Managerlmpl();
    	 }
    	 return instance;
     }
     
   /*
    * 用户注册
    * (non-Javadoc)
    * @see com.cx.bank.manager.ManagerInterface#register(java.lang.String, java.lang.String)
    * @para name 用户名
    * @para pwd 用户密码
    * 注册成功返回显示用户编号
    */
     public boolean register(String name,String pwd) {
 		MD5 m=new MD5();
	    String password=m.getMD5(pwd);
 		boolean flag=false;
 		flag=factory.InsertUser(name,password,1,100.0);//
 		if(flag==true){
 		System.out.println("注册成功！");
 		}else{
 			System.out.println("用户名已存在！");
 		}
 		return flag;		
 	}
     
     /*
      * 通过用户名和密码查找用户编号返回
      * @para  username - 用户名
      * @para password - 密码 
      */
      public int findId(String username,String password){
    	  MD5 m=new MD5();
    	  String pwd=m.getMD5(password);
    	  List<User> UserList=new ArrayList<User>();
    	  int UserId=0;
    	  UserList=factory.FindId(username, pwd);
    	  UserId=UserList.get(0).getId();
    	  return UserId;
    	  
      }
     
     /*
      * 用户登录
      * @para userId 用户编号
      * @para pwd 用户密码
      */
 	public Boolean login(int userId, String pwd) {
		 MD5 m=new MD5();
		 String password=m.getMD5(pwd);
 		boolean flag=factory.findUser(userId,password);//
 		if(flag==true){
 	 		System.out.println("登录成功！");
 	 		user.setUserId(userId);
 	 		user.setPwd(password);
 	 		}else{
 	 		}
 		return flag;
 	}
     
	/*
	 * (non-Javadoc)
	 * @see com.cx.bank.manager.ManagerInterface#inquiry()
	 * 用户余额查询
	 */
	public double inquiry(){
		int id=user.getUserId();
		//System.out.println(id);
		double money=factory.getUserById(id).getBalance();
		return money;
	}
	
	/*
	 * 根据用户编号拿到用户名
	 * 
	 */
	public String queryName(int id) {
	   	String name=factory.getUserById(id).getUsername();
	   	return name;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.cx.bank.manager.ManagerInterface#deposit(double)
	 * 存款
	 * @para mon 存入金额
	 */
	public double deposit(double mon)throws InvalidDepositException, ParseException{
		double moneyset=moneyBean.getMoney();
			if(mon<0){
				throw new InvalidDepositException("存款金额不能为负！");
			}else{
				moneyset=moneyset+mon;
				moneyBean.setMoney(moneyset);
				user.setBalance(moneyset);
				factory.UpdateUser(user.getUserId(),moneyset);
				//插入用户操作明细表
				factory.InsertLog("存款", mon, user.getUserId(),getDateTime());
				System.out.println("交易成功!");
				System.out.println("您当前余额为：￥"+moneyset);
			}
		
		return moneyset;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.cx.bank.manager.ManagerInterface#withdrawals(double)
	 * 取款
	 * @para moneyget 金额
	 */
	public double withdrawals(double moneyget) throws AccountOverDrawnException, ParseException{
		double money1=moneyBean.getMoney();	 
		if(moneyget<0){
			System.out.println("输入错误！");
		}else{
		if(moneyget>money1){
			System.out.println("您当前余额为：￥"+ money1);
			throw new AccountOverDrawnException("余额不足！");
			
		}else{
			money1=money1-moneyget;
			moneyBean.setMoney(money1);
			user.setBalance(money1);
			factory.UpdateUser(user.getUserId(),money1);
			//插入用户操作明细表
			factory.InsertLog("取款", moneyget, user.getUserId(),getDateTime());
			System.out.println("交易成功！");
			System.out.println("您当前余额为：￥"+ money1);
		}
	 }
		return money1;
	}
	
	/*
	 * 转账
	 * @para toId 被转入账户编号
	 * @para tomoney 转出金额
	 */
	public boolean transfer(int toId,double tomoney) throws AccountOverDrawnException, ParseException{
		boolean flag=false;
		boolean flag1=false;
		int num=factory.UpdateTUser(toId,tomoney);
		//插入用户操作明细表
		factory.InsertLog("转入", tomoney, toId,getDateTime());
		if(num==1){
			flag1=true;
		}else if(num==0){
			flag1=false;
		}
		if(flag1==true){
			double money=moneyBean.getMoney();
			System.out.println(money);
			if(money>tomoney){
			  double leavemoney=money-tomoney;
			  moneyBean.setMoney(leavemoney);
			  user.setBalance(leavemoney);
			  int num1=factory.UpdateUser(user.getUserId(),leavemoney);
			  //插入用户操作明细表
				factory.InsertLog("转出",tomoney, user.getUserId(),getDateTime());
			  if(num1==1){
				  flag=true;
				  System.out.println("交易成功！");
				  System.out.println("您当前余额为：￥"+ leavemoney);
			  }else if(num1==0){
				  flag=false;
			  }
			}else{
				System.out.println("余额不足！");
				System.out.println("您当前余额为：￥"+money);
				flag=false;
			}
		}else{
			System.out.println("该用户不存在！");
			flag=false;
		}
		
		
		return flag;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.cx.bank.manager.ManagerInterface#exitSystem()
	 * 查询用户操作明细
	 * 返回数组类型便于建表
	 * 
	 */
	public Object[][] QueryLog(){
		List<Log> log=new ArrayList<Log>();
		log=factory.queryLog(user.getUserId());
		int count=log.size();
		Object[][] info=new Object[count][5];
		count=0;
		for(int i=0;i<log.size();i++){
			info[count][0]=Integer.valueOf(log.get(count).getLogid());
			info[count][1]=log.get(count).getLogtype();
			info[count][2]=log.get(count).getLogamount();
			info[count][3]=Integer.valueOf(log.get(count).getUser().getId());
			info[count][4]=log.get(count).getLogtime();
			count++;
		}
		return info;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.cx.bank.manager.ManagerInterface#exitSystem()
	 * 查询用户操作明细
	 * 返回logBean  list集合
	 */
	public List<Log> queryLog(){
		List<Log> log=new ArrayList<Log>();
		return log=factory.queryLog(user.getUserId());
	}
	
	
	//退出
	public void exitSystem(){
	    double money=moneyBean.getMoney();
	    factory.UpdateUser(user.getUserId(),money);	
		System.out.println("期待您下次使用本系统，再见！");
		System.exit(1);
	}

	//获取当前时间的方法
	public String getDateTime(){
		Date now=new Date();
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String logTime=dateFormat.format(now);
		return logTime;
	} 
	
	/*
	 * 管理员登录
	 * @param adminId 管理员账户编号
	 * @param adminPwd 管理员账户密码
	 * 
	 */
	public boolean adminLogin(int adminId,String adminPwd){
		boolean flag=false;
		flag=factory.findAdmin(adminId, adminPwd);
		return flag;
		
	}
	
	/*
	 * 管理员修改用户冻结信息
	 * @param userId 用户编号
	 * @param userFlag 用户冻结信息
	 * 
	 */
	public boolean updateUserFlag(int userId,int userFlag){
		boolean flag=factory.updateFlag(userId, userFlag);
		return flag;
	}
	
	/*
	 * 拿到所有用户冻结信息
	 * 返回二维数组用于建表
	 */
	public Object[][] queryUserFlagInfo(){
		List<User> userlist=new ArrayList<>();
		userlist=factory.queryUserList();
		int count=userlist.size();
		Object[][] info=new Object[count][3];
		count=0;
		for(int i=0;i<userlist.size();i++){
			info[count][0]=Integer.valueOf(userlist.get(i).getId());
			info[count][1]=userlist.get(i).getUsername();
			info[count][2]=Integer.valueOf(userlist.get(i).getUserflag());
			count++;
		}
		return info;
	}
	
	/*
	 * 拿到所有用户的冻结信息
	 * 返回list集合
	 * 
	 */
	public List<User> queryUserFlag(){
		List<User> userlist=new ArrayList<>();
		userlist=factory.queryUserList();
		return userlist;
	}
	
	
	/*
	 * 查询用户冻结信息
	 * user - 存放临时信息的模型
	 * user1 - 持久层数据库表映射的记录
	 */
	public int  queryFlag(){
		int userFlag;
		 User user1=factory.getUserById(user.getUserId());
		 userFlag=user1.getUserflag();
		return userFlag;
	}
	
}
