
 package com.servlet;

 import java.io.IOException;

 import javax.servlet.*;
 import javax.servlet.http.*;
 import com.cx.bank.manager.*;

 public class exitServlet extends HttpServlet {
   
	 Managerlmpl ml;
	 
	
	 //LoginServlet初始化
	 public void init(ServletConfig config)throws ServletException{
		 try {
			 ml=Managerlmpl.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("LoginServlet初始化……");
	 }
	 
	 //处理get请求
	 public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException {
		 this.doPost(request, response);
		 System.out.println("LoginServlet-处理get请求……");
	 }
	 
	 //处理post请求
	 public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException {
		 System.out.println("退出系统");
		 ml.exitSystem();
	 }
	 
	 //LoginServlet销毁
	 public void destory() {
		 System.out.println("LoginServlet销毁……");
	 }

 }
