<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销毁</title>
</head>
<body background="images/img1.jpg">
   <%
      session.invalidate();
     //session.removeAttribute(arg0);
      response.setHeader("refresh","2;URL=login.jsp");
   %>
   <h1>注销成功</h1>
</body>
</html>