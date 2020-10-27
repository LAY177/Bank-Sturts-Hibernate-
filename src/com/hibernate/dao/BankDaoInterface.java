
 /*
  * 持久层接口，连接外部设备
  */
 package com.hibernate.dao;

 import java.text.ParseException;
 import java.util.List;

 import com.HBean.Admin;
 import com.HBean.Log;
 import com.HBean.User;


 public interface BankDaoInterface {
    
	 
	 
	 /*
      * 用户信息查询并发封装到UserBean里
      * @para UserList 返回用户信息集合
      */
     public List<User> queryUserList();
     
     
     /*
      * 根据用户Id查询用户信息（包括余额）
      * @para id -业务层方法传过来的用户编号
      */
     public User getUserById(int id);
     
     /*
      * 根据传过来的id和密码查找用户
      * @para id - 用户编号
      * @para password - 用户密码
      */
      public boolean findUser(int id,String password);
       
     /*
      * 添加新用户
      * @para userName - 用户名
      * @para userPwd - 用户密码
      * @para userFlag - 用户冻结与否标记
      * @para Balance - 用户余额
      */
     public boolean InsertUser(String userName,String userPwd,int userFlag,double Balance);
     
     /*
      * 更新用户余额
      * @para userId - 用户编号
      * @para userName - 用户名
      * @para Balance - 用户余额
      */
     public int UpdateUser(int userId,double Balance);
     
     /*
      * 转账-更新被转账用户的余额
      * @para touserId - 用户编号
      * @para touserName - 用户名
      * @para toBalance - 用户余额
      */
     public int UpdateTUser(int touserId,double toBalance);
     
     /*
      * 插入用户操作明细
      * @para logType  - 操作类型
      * @para logAmount - 操作金额
      * @para userId - 用户编号 
      * @para logTime - 操作时间
      */
     public int InsertLog(String logType,double logAmount,int userId,String logTime) throws ParseException;
     
     /*
      * 根据用户id查询用户操作明细
      * @para userId - 用户编号
      * 
      */
     public List<Log> queryLog(int userId);
     
     /*
      *通过用户名和密码查找单个用户信息返回
      *@para  username - 用户名
      *@para password - 密码 
      */
     public List<User> FindId(String username,String password);
     
     /*
      * 查询管理员表信息
      */
     public List<Admin> queryAdmin();
     
     /*
      * 根据传过来的管理员账户编号和密码判断用户是否存在
      * @param adminId 管理员账户编号
      * @param adminPwd  管理员账户密码
      * 
      */
     public boolean findAdmin(int adminId,String adminPwd);
     
     /*
      * 根据传过来的用户编号和冻结信息修改指定用户冻结信息
      * @param userId 用户编号
      * @param userFlag 用户冻结信息
      */
     public boolean updateFlag(int userId,int userFlag);
 }
