
/*
 * 取款操作
 */

 package com.action;

 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

 import org.apache.struts.action.Action;
 import org.apache.struts.action.ActionForm;
 import org.apache.struts.action.ActionForward;
 import org.apache.struts.action.ActionMapping;

 import com.cx.bank.manager.Managerlmpl;

 public class WithdrawalsAction extends Action {
   
	 public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception {
    
		 LoginActionForm laf=(LoginActionForm)form;
		 String takemoney=laf.getTakemoney();
		 double takeMoney=Double.parseDouble(takemoney);
		 Managerlmpl ml;
		 double balance=0.0;
		 int flag1=1; //账户冻结信息 
		try {
			ml = LoginAction.ml;//Managerlmpl.getInstance();
		  flag1=ml.queryFlag();
		  if(flag1==1) {
		      balance=ml.withdrawals(takeMoney);
		      request.setAttribute("balance", balance);
		      request.setAttribute("successmessage", "取款交易成功！");
			 // request.getRequestDispatcher("/manager/op_success.jsp").forward(request, response);
		      return mapping.findForward("success");
		  } else {
			  //request.getRequestDispatcher("/manager/userflag_false.jsp").forward(request, response);
			  return mapping.findForward("false");
		  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error_message", "取款失败！余额不足！请重新操作！");
		    //request.getRequestDispatcher("/manager/error.jsp").forward(request, response);	
			return mapping.findForward("error");
		}
	 }	 
 }
