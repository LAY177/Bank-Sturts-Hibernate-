
/*
 * 用户冻结信息
 */
 package com.action;

 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

 import org.apache.struts.action.Action;
 import org.apache.struts.action.ActionForm;
 import org.apache.struts.action.ActionForward;
 import org.apache.struts.action.ActionMapping;

import com.cx.bank.manager.Managerlmpl;

 public class FlagAction extends Action {
    
	 public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception {
	   
		 LoginActionForm laf=(LoginActionForm)form;
		 String userId=laf.getUflagID();
		 String uflaginfo=laf.getFlaginfo();
		 int ID=Integer.parseInt(userId);
		 int uFlag=Integer.parseInt(uflaginfo);
		 Managerlmpl ml;
		 Boolean flag=false;
		try {
			ml = Managerlmpl.getInstance();
		    flag=ml.updateUserFlag(ID, uFlag);
		    if(flag==true) {
		    	request.setAttribute("userInfo", ml.queryUserFlag());
		        request.setAttribute("successmessage", "修改成功！");
		       // request.getRequestDispatcher("alogin_success.jsp").forward(request, response);
		        return mapping.findForward("success");
		    }else {
		    	request.setAttribute("error_message", "修改失败！请重新操作！");
				//request.getRequestDispatcher("error.jsp").forward(request, response);
		    	return mapping.findForward("error");
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error_message", "修改失败！请重新操作！");
			//request.getRequestDispatcher("error.jsp").forward(request, response);	
			return mapping.findForward("error");
		}
		 
	 }
 }
