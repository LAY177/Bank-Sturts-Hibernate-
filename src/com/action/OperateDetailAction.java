
/*
 * 用户操作明细
 */

 package com.action;

 import java.util.List;

 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

 import org.apache.struts.action.Action;
 import org.apache.struts.action.ActionForm;
 import org.apache.struts.action.ActionForward;
 import org.apache.struts.action.ActionMapping;

 import com.HBean.Log;
 import com.cx.bank.manager.Managerlmpl;
 

 public class OperateDetailAction extends Action {
    
	 public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception {

		 Managerlmpl ml;
		 List<Log> userlog;
		try {
			ml = LoginAction.ml;//Managerlmpl.getInstance();
			userlog=ml.queryLog();  //调用的是返回值为list集合的方法			   
		    request.setAttribute("userlog", userlog);
			//request.getRequestDispatcher("/manager/operateDetail_success.jsp").forward(request, response);	  
		    return mapping.findForward("success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error_message", "查询失败！数据提取出错！请重新操作！");
			//request.getRequestDispatcher("/manager/error.jsp").forward(request, response);	
			return mapping.findForward("error");
		}
	 }
 }
