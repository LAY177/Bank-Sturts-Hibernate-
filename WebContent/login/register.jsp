<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<style type="text/css">
#Div{
   position:absolute;width:440px;height:240px;left:50%;top:50%;
   margin-left:-200px;margin-top:-100px;
}
input{border:1px solid #000;height:25px;border-radius:5px;}
.btn{background-color:#FFF;color:#663;font-size:16px;height:35px;width:120px;font-weight:bold;margin-left:-50px}
</style>
<script type="text/javascript">
   //输入框是否为空验证
   function check2(){
	   if(document.getElementById("username").value==""){
		   alert("请输入用户名");
	   }else if(document.getElementById("pwd").value==""){
	        alert("请输入密码");
	   }else{
		  return document.text.submit();
	   }
   }

</script>


</head>
<body background="images/img2.jpg">
<br/><br/><br/><br/><br/><br/>
 <div id="Div">
   <form action="../register.do" method="post" name="text">
     <table width="100%" border="0" cellspacing="0" cellpadding="0'">
       <tr> 
         <td colspan="2" align="center">
          <h1>欢迎使用银行管理系统</h1>
         </td>
       </tr>
      <tr height="35px">
         <td align="right" width="120px">用户名：</td>
         <td>
            <input type="text" id="username" name="username" value=""/>
         </td>
      </tr>
      
      <tr height="35px">
        <td align="right">密码 ：</td>
        <td>
           <input type="text"  id="pwd" name="password" value=""/> 
        </td>
      </tr>
      <tr height="45px">
        <td colspan="2" align="center">
           <input id="register" class="btn" name="operate" type="button" value="用户注册" onclick="check2()"/>
        </td>
      </tr>   
     </table>
   </form>
 </div>
</body>
</html>