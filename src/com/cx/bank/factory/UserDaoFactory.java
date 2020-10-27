
 
 /*
  * 工厂，连接业务层和持久层
  */
 package com.cx.bank.factory;

 import java.io.File;
 import java.io.FileInputStream;
 import java.util.Properties;

 import com.hibernate.dao.BankDaoInterface;

 public class UserDaoFactory {
    private static UserDaoFactory instance;
    private static BankDaoInterface UserDao;
    
    private UserDaoFactory()throws Exception{
    	//File f=new File(".\\classInfo.properties");
    	//FileInputStream in=new FileInputStream(f);
    	Properties props=new Properties();
    	String path="com/file/classInfo.properties";
    	//利用类加载器拿到路径
    	String context=UserDaoFactory.class.getClassLoader().getResource("").toURI().getPath();
    	FileInputStream in=new FileInputStream(new File(context + path));
    	props.load(in);
    	in.close();
    	Class c=Class.forName(props.getProperty("className"));
    	Object o=c.newInstance();
    	UserDao=(BankDaoInterface)o;
    }
    
    public static synchronized BankDaoInterface  getInstance() throws Exception{
		if(instance==null){
			instance=new UserDaoFactory();
			UserDao=instance.UserDao;
		}
    	return UserDao;
    	
    }
}
