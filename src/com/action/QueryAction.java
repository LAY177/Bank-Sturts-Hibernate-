/*
 * 余额查询
 */

 package com.action;

 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

 import org.apache.struts.action.Action;
 import org.apache.struts.action.ActionForm;
 import org.apache.struts.action.ActionForward;
 import org.apache.struts.action.ActionMapping;

 import com.cx.bank.manager.Managerlmpl;


 public class QueryAction extends Action {
    
	 public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception {
        
		 Managerlmpl ml;
		 double balance=0.0;
		try {
			ml = LoginAction.ml;//Managerlmpl.getInstance();
		    balance=ml.inquiry();
		    request.setAttribute("balance", balance);
		    request.setAttribute("successmessage", "查询操作成功！");
		    System.out.println(balance);
		    return mapping.findForward("success");
		   // request.getRequestDispatcher("/manager/op_success.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error_message", "查询失败！请重新操作！");
			//request.getRequestDispatcher("/manager/error.jsp").forward(request, response);	
		    return mapping.findForward("error");
		}
	 }	 
 }
