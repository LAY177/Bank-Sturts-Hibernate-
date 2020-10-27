
package com.cx.bank.manager;

import java.text.ParseException;
import java.util.List;

import com.HBean.Log;
import com.HBean.User;
import com.cx.bank.util.AccountOverDrawnException;
import com.cx.bank.util.InvalidDepositException;

interface ManagerInterface {
        
	    //注册
	    public boolean register(String name,String pwd);
	    /*
	      * 通过用户名和密码查找用户编号返回
	      * @para  username - 用户名
	      * @para password - 密码 
	      */
	    public int findId(String username,String password);
	    //登录
	    public Boolean login(int userId,String pwd);
		//查询
		public double inquiry();
		//存款
		public  double deposit(double mon) throws InvalidDepositException, ParseException;
		//取款
		public double withdrawals(double moneyget) throws AccountOverDrawnException, ParseException;
		//转账
		public boolean transfer(int toId,double tomoney) throws AccountOverDrawnException, ParseException;
		//查询用户操作明细返回数组类型便于建表
		public Object[][] QueryLog();
		/*
		 * (non-Javadoc)
		 * @see com.cx.bank.manager.ManagerInterface#exitSystem()
		 * 查询用户操作明细
		 * 返回log  list集合
		 */
		public List<Log> queryLog();
		//退出
		public void exitSystem();
		//获取当前时间的方法
		public String getDateTime();
		/*
		 * 管理员登录
		 * @param adminId 管理员账户编号
		 * @param adminPwd 管理员账户密码
		 * 
		 */
		public boolean adminLogin(int adminId,String adminPwd);
		/*
		 * 拿到所有用户冻结信息
		 * 返回二维数组用于建表
		 */
		public Object[][] queryUserFlagInfo();
		/*
		 * 拿到所有用户的冻结信息
		 * 返回list集合
		 * 
		 */
		public List<User> queryUserFlag();
		
		/*
		 * 查询用户冻结信息
		 * 
		 */
		public int  queryFlag();
		
		/*
		 * 根据用户编号拿到用户名
		 * 
		 */
		public String queryName(int id);
         
}
