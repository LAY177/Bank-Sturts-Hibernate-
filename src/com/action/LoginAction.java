
/*
 * 处理登录操作
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

 public class LoginAction extends Action {
 
	 static Managerlmpl ml;
	 
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		LoginActionForm laf=(LoginActionForm)form;
		System.out.println(laf);
		try {
			 ml=Managerlmpl.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String userID=laf.getUserID();
		 String password=laf.getPassword();
		 System.out.println("处理登录操作……");
		 HttpSession session=request.getSession();
		 int userId=Integer.parseInt(userID);
		 boolean flag=false;
		 flag=ml.login(userId, password);
		 if(flag==true) {
			 String userName=ml.queryName(userId);
			 session.setAttribute("userName", userName);
			 session.setAttribute("userID",userID);
			 return mapping.findForward("success");
		 }else {
			 request.setAttribute("error_message", "登录失败！用户编号或密码错误！请重新操作！");
			 return mapping.findForward("error");
		 }
		
	}
 }
