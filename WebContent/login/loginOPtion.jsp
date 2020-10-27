<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作选择</title>
<style type="text/css">
#Div{
   position:absolute;width:440px;height:240px;left:50%;top:50%;
   margin-left:-200px;margin-top:-100px;
}
input{border:1px solid #000;height:25px;border-radius:5px;}
.btn{background-color:#FFF;color:#663;font-size:16px;height:35px;width:120px;font-weight:bold;margin-left:-50px}
</style>

</head>
<body background="images/img1.jpg">
<br/><br/><br/><br/><br/><br/>
 <div id="Div">
   <form action="login.jsp" method="post" >
     <table width="100%" border="0" cellspacing="0" cellpadding="0'">
       <tr> 
         <td colspan="2" align="center">
          <h1>欢迎使用银行管理系统</h1>
         </td>
       </tr>  
      <tr height="45px">
        <td colspan="2" align="center"> <!-- 登录成功就将用户编号设到session里 -->
           <input id="login" class="btn" name="operate" type="submit" value="用户登录" /> <!-- 根据operate的value值确定是什么操作 -->
        </td>
      </tr>
      </table>
   </form> 
   <form action="register.jsp" method="post" >
     <table width="100%" border="0" cellspacing="0" cellpadding="0'">
      <tr height="45px">
        <td colspan="2" align="center">
           <input id="register" class="btn" name="operate" type="submit" value="用户注册" />
        </td>
      </tr>
    </table>
   </form>  
   <form action="alogin.jsp" method="post" >
    <table width="100%" border="0" cellspacing="0" cellpadding="0'">  
      <tr height="45px">
        <td colspan="2" align="center">
          <input id="adminlogin" class="btn" name="operate" type="submit" value="管理员登录" />
        </td>
      </tr> 
     </table>
   </form>  
  <form action="exit.jsp" method="post" >
     <table width="100%" border="0" cellspacing="0" cellpadding="0'">    
      <tr height="45px">
        <td colspan="2" align="center">
          <input id="exit" class="btn" name="operate" type="submit" value="退出系统" />
        </td>
      </tr>       
     </table>
   </form>
 </div>
</body>
</html>