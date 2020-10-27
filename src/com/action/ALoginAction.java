
/*
 * 处理管理员登录操作
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

 public class ALoginAction extends Action {
   
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
			 flag=ml.adminLogin(userId, password);
			 if(flag==true) {
				//拿到所有用户的冻结信息，以集合的形式设进session里
				request.setAttribute("userInfo", ml.queryUserFlag()); 
			    return mapping.findForward("success");
				//request.getRequestDispatcher("alogin_success.jsp").forward(request, response);
			 }else {
				 request.setAttribute("error_message", "管理员登录失败！用户编号或密码错误！请重新操作！");
				// request.getRequestDispatcher("error1.jsp").forward(request, response);
				 return mapping.findForward("error");
			 }
			
		}
 }
