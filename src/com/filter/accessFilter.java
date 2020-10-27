
 /*
  * 请求验证拦截器
  */
 
 package com.filter;

 import java.io.IOException;

 import javax.servlet.Filter;
 import javax.servlet.FilterChain;
 import javax.servlet.FilterConfig;
 import javax.servlet.ServletException;
 import javax.servlet.ServletRequest;
 import javax.servlet.ServletResponse;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;

 public class accessFilter implements Filter {

	//filter初始化
		public void init(FilterConfig config) throws ServletException {
			System.out.println("Filter初始化……");
		}

		//对请求和结果进行拦截
		public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
				throws IOException, ServletException {
			
			//对ServletRequest进行造型，使其能够接收http协议
			HttpServletRequest request=(HttpServletRequest)req;
			HttpServletResponse response=(HttpServletResponse)resp;
			
			//截取请求字符串
			String curURL=request.getRequestURI();
			String targetURL=curURL.substring(curURL.indexOf("/",2));
			
			//若session不为空，则可访问别的页面
			//若请求里有session就拿，没有就返回null
			HttpSession session=request.getSession(false);
			if(!"/login.jsp".equals(targetURL)||!"/loginOPtion.jsp".equals(targetURL)) {
				if(session==null||session.getAttribute("userID")==null) {
					//客户端跳转返回登录验证页面
					response.sendRedirect("login.jsp");//login.jsp//request.getContextPath()
					System.out.println("请先进行登录验证！");
					return;
				}
			}
//			if(session.getAttribute("userID")!=null){
				//请求下传
				chain.doFilter(request, response);
//			}else{
//				request.getRequestDispatcher("login.jsp").forward(request, response);
//				System.out.println("请先进行登录验证！");
//			}
			
			
		}

		//销毁filter
		public void destroy() {
			System.out.println("Filter已销毁……");
			
		}

 }
