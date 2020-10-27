<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册成功</title>
</head>
<body background="images/img1.jpg">
  
  <form action="./login/loginOPtion.jsp">
   <h1>注册成功！</h1>   
      您的账户编号是：<%=request.getAttribute("userid") %>
   <br>
   <input type="submit" value="back"> 
    <!--<a href="./login/loginOPtion.jsp">返回</a>-->
  </form>
     
  
</body>
</html>