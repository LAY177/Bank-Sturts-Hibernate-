
 /*
 * 处理注册操作
 */

 package com.action;

 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
 import org.apache.struts.action.ActionForm;
 import org.apache.struts.action.ActionForward;
 import org.apache.struts.action.ActionMapping;

 import com.cx.bank.manager.Managerlmpl;

 public class RegisterAction extends Action {
   
	 static Managerlmpl ml;
	 
	 public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception {
		 
		 LoginActionForm laf=(LoginActionForm)form;
			System.out.println(laf);
			try {
				 ml=Managerlmpl.getInstance();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			String username=laf.getUsername();
		    String password=laf.getPassword();
		    System.out.println("处理注册操作……");
		    boolean flag=false;
			 flag=ml.register(username, password);
			 if(flag==true) {
				 String userid= String.valueOf(ml.findId(username, password));
				 request.setAttribute("userid", userid);//注册成功返回用户编号
				// request.getRequestDispatcher("register_success.jsp").forward(request, response);
				 return mapping.findForward("success");
			 }else {
				 request.setAttribute("error_message", "注册失败！用户已存在！请重新操作！");
				 //request.getRequestDispatcher("error1.jsp").forward(request, response);
				 return mapping.findForward("error");
			 }
	 }
 }
