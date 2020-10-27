
 /*
  * 转账操作
  */

 package com.action;

 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

 import org.apache.struts.action.Action;
 import org.apache.struts.action.ActionForm;
 import org.apache.struts.action.ActionForward;
 import org.apache.struts.action.ActionMapping;

 import com.cx.bank.manager.Managerlmpl;
 

 public class TransferAction extends Action {
   
	 public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception {
      
		 LoginActionForm laf=(LoginActionForm)form;
		 String transferId=laf.getTransferId();
		 String transfermoney=laf.getTransfermoney();
		 int transferID=Integer.parseInt(transferId);
		 double transferMoney=Double.parseDouble(transfermoney);
		 Managerlmpl ml;
		 boolean flag=false;
		 double balance=0.0;
		 int flag1=1; //账户冻结信息 
		try {
			ml = LoginAction.ml;//Managerlmpl.getInstance();
			flag1=ml.queryFlag();
			if(flag1==1) {
		       flag=ml.transfer(transferID,transferMoney);
		       if(flag==true) {
		    	   balance=ml.inquiry();
				   request.setAttribute("balance", balance); 
				   request.setAttribute("successmessage", "转账交易成功！");
		           return mapping.findForward("success");
		       }else {
		        	request.setAttribute("error_message", "转账失败！用户不存在或余额不足！请重新操作！");
		        	return mapping.findForward("error");
		       }
			}else {
			   //request.getRequestDispatcher("/manager/userflag_false.jsp").forward(request, response);	
				return mapping.findForward("false");
			}
		   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error_message", "转账失败！用户不存在或余额不足！请重新操作！");
		    //request.getRequestDispatcher("manager/error.jsp").forward(request, response);	
			return mapping.findForward("error");
		}
	 }
 }
