<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.HBean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户操作明细表</title>
<style type="text/css">
#Div{
   position:absolute;width:840px;height:840px;left:50%;top:30%;
   margin-left:-400px;margin-top:-100px;
}
input{border:1px solid #000;height:25px;border-radius:5px;}
.btn{background-color:#FFF;color:#663;font-size:16px;height:35px;width:120px;font-weight:bold;margin-left:-50px}
</style>

</head>
<body><!-- background="images/img1.jpg" -->
<br/><br/><br/><br/><br/><br/>
 <div id="Div">
   <form action="" method="post" name="text">
     <table width="100%" border="2" cellspacing="0" cellpadding="0">
       <tr height="35px"> 
         <td colspan="10" align="center">
                    用户操作明细表
         <%
            List<Log> userlog=(List<Log>)request.getAttribute("userlog");
            
         %>
         </td>
       </tr>
       <tr height="35px">
         <td id="1" colspan="2" >操作编号</td>
         <td id="2" colspan="2" >操作类型</td>
         <td id="3" colspan="2" >操作金额</td>
         <td id="4" colspan="2" >用户编号</td>
         <td id="5" colspan="2" >操作时间</td> 
       </tr>
       <%
          for(int i=0;i<userlog.size();i++){
       %>
           <tr height="35px">
             <td colspan="2" ><%=userlog.get(i).getLogid() %></td>
             <td colspan="2" ><%=userlog.get(i).getLogtype() %></td>
             <td colspan="2" ><%=userlog.get(i).getLogamount() %></td>
             <td colspan="2" ><%=userlog.get(i).getUser().getId() %></td>
             <td colspan="2" ><%=userlog.get(i).getLogtime()%></td>
           </tr>
       <%
          }      
       %>
     </table>
    </form>
 </div>
</body>
</html>