<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作失败</title>
</head>
<body><!-- background="images/img1.jpg" -->
  <h1> <%=request.getAttribute("error_message") %></h1>
  <% //response.setHeader("refresh","2;URL=/login/user-homepage.jsp");%>
</body>
</html>