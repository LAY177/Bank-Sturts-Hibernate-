/*
 * 存款操作
 */

 package com.action;

 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

 import org.apache.struts.action.Action;
 import org.apache.struts.action.ActionForm;
 import org.apache.struts.action.ActionForward;
 import org.apache.struts.action.ActionMapping;

import com.cx.bank.manager.Managerlmpl;

 public class DepositAction extends Action {
    
	 public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception {
	    
		 LoginActionForm laf=(LoginActionForm)form;
		 String inputmoney=laf.getInputmoney();
		 double inputMoney=Double.parseDouble(inputmoney);
		 Managerlmpl ml;
		 double balance2=0.0;
		 int flag1=1; //账户冻结信息 
		try {
			ml = LoginAction.ml;//Managerlmpl.getInstance();
	      flag1=ml.queryFlag();
		 if(flag1==1) {
		    balance2=ml.deposit(inputMoney);
		    request.setAttribute("balance", balance2);
		    request.setAttribute("successmessage", "存款交易成功！");
		    //request.getRequestDispatcher("/manager/op_success.jsp").forward(request, response);
		    return mapping.findForward("success");
		 }else {
			// request.getRequestDispatcher("/manager/userflag_false.jsp").forward(request, response);
		    return mapping.findForward("false");
		 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error_message", "存款失败！输入格式有误！请重新操作！");
			//request.getRequestDispatcher("/manager/error.jsp").forward(request, response);	
		    return mapping.findForward("error");
		}
		 
	 }
 }
