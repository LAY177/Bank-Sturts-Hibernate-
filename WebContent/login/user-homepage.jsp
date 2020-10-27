
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录成功</title>
<style type="text/css">

#top{
   background-image:url(login/images/img7.jpg);
   position:absolute;
   top:0px;
   left:0px;
   height:90px;
   width:100%;
   color:#000000;
    }
#left{
    background-image:url(login/images/img7.jpg);
	position:absolute;
	left:0px;
	top:91px;
	width:20%;
	height:100%;
	}
#main{
    background-image:url(login/images/img4.jpg);
    background-repeat:no-repeat;
    background-size:auto 100%;
	position:absolute;
	left:20%;
	top:91px;
	width:80%;
	heigth:100%;
	background-size: cover;
	}

</style>


</head>

<body>
<div id="top">
  <h1>欢迎您,<%=session.getAttribute("userName") %></h1>
  
</div>
<div id="left">

    <div >
       <a href="query.do" target="mainFrame">查询</a>
    </div>
    
    <div >
        <a href="./manager/withdrawals.jsp" target="mainFrame">取款</a>
    </div>
    
    <div >
       <a href="./manager/deposit.jsp" target="mainFrame">存款</a>
     </div>
     
   <div >
       <a href="./manager/transfer.jsp" target="mainFrame">转账</a>
    </div>
    
    <div>
      <a href="operateDetail.do" target="mainFrame">用户操作明细</a>
    </div>
    
     <div >
      <a href="login/destory.jsp" >退出</a>
    </div>
    
     <div >
      <a href="login/login.jsp" >返回登录页面</a>
    </div>
    
       
</div>
<div id="main">
  <iframe name="mainFrame"  width="900px" height="500px" frameborder="0" scrolling="auto"></iframe>
</div>

</body>
</html>