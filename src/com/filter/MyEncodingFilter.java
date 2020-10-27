
 package com.filter;

 import java.io.*;
 import javax.servlet.*;
 import javax.servlet.http.*;

 public class MyEncodingFilter implements Filter {
    
	//定义实例全局变量，传递编码格式
	 private String encoding;
	//filter初始化
	public void init(FilterConfig config) throws ServletException {
		
		//config从配置里拿到初始化参数，将初始化参数传给实例全局变量
		this.encoding=config.getInitParameter("encoding");
		System.out.println("encoding="+this.encoding);
		System.out.println("Filter初始化……");
	}

	//对请求和结果进行拦截
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		//对ServletRequest进行造型，使其能够接收http协议
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)resp;
		
		//进行编码拦截
		request.setCharacterEncoding(this.encoding);
		
		//若session不为空，则可访问别的页面
//		HttpSession session=request.getSession();
//		if(session.getAttribute("uname")!=null){
			//请求下传
			chain.doFilter(request, response);
//		}else{
//			request.getRequestDispatcher("login.jsp").forward(request, response);
//			System.out.println("请先进行登录验证！");
//		}
		
		
	}

	//销毁filter
	public void destroy() {
		System.out.println("Filter已销毁……");
		
	}

 }
