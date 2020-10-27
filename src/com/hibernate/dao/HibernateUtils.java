
/*
 * 用于加载hibernate.cfg.xml主配文件
 * 创建session工厂
 * 
 */

 package com.hibernate.dao;

 import org.hibernate.Session;
 import org.hibernate.SessionFactory;
 import org.hibernate.cfg.Configuration;

 public class HibernateUtils {

	private static SessionFactory factory;
	
	static {
		try {
			//创建配置类对象，拿到hibernate.cfg.xml文件里的配置信息
			Configuration cfg = new Configuration().configure();
			factory = cfg.buildSessionFactory();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return factory;
	}
	
	public static Session getSession() {
		return factory.openSession();
	}
	
	public static void closeSession(Session session) {
		if (session != null) {
			if (session.isOpen()) {
				session.close();
			}
		}
	}
}
