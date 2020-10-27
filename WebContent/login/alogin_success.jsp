<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.HBean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录成功</title>
<style type="text/css">
#Div{
   position:absolute;width:800px;height:240px;left:50%;top:30%;
   margin-left:-400px;margin-top:-100px;
}
input{border:1px solid #000;height:25px;border-radius:5px;}
.btn{background-color:#FFF;color:#663;font-size:16px;height:35px;width:120px;font-weight:bold;margin-left:300px;}
</style>
</head>
<body background="login/images/img7.jpg">
   <h1 align="center">管理员登成功！</h1>
   <div id="Div">
   <form action="login/alogin.jsp">
     <table width="100%" border="2" cellspacing="0" cellpadding="0'">
        <tr height="35px" align="center">
          <td colspan="8" align="center">
                          用户冻结信息表
             <% List<User> userlist=(List<User>)request.getAttribute("userInfo"); %>
          </td>  
        </tr>
        <tr height="35px">
           <td colspan="2" >用户编号</td>
           <td colspan="2" >用户名</td>
           <td colspan="2" >冻结信息</td>
           <td colspan="2" >操作</td>
        </tr>
        <%
             for(int i=0;i<userlist.size();i++){
        %>
           <tr height="35px">
              <td colspan="2" ><%=userlist.get(i).getId() %></td>
              <td colspan="2" ><%=userlist.get(i).getUsername() %></td>
              <td colspan="2" ><%=userlist.get(i).getUserflag() %></td>
              <td colspan="2">
                 <a href="flag.do?uflagID=<%=userlist.get(i).getId()%>&flaginfo=1">解冻</a>
                 <a href="flag.do?uflagID=<%=userlist.get(i).getId()%>&flaginfo=0">冻结</a>
              </td>
           </tr>
        <%
             }
        %>
           <tr height="35px">
           <td colspan="8" align="center">修改用户冻结信息:
           <%
              if(request.getAttribute("successmessage")==null){ 	  
              
           %>
            <h4>还未进行修改操作！</h4>
           <%
              }else{
           %>
           <%=request.getAttribute("successmessage") %>
           <%} %>
          </td> 
        </tr>       
     </table>
      <input class="btn" type="submit" value="back" />
   </form>
   </div>
</body>
</html>