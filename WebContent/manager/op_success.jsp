<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>交易成功</title>
<style type="text/css">
#Div{
   position:absolute;width:440px;height:240px;left:50%;top:50%;
   margin-left:-200px;margin-top:-100px;
}
input{border:1px solid #000;height:25px;border-radius:5px;}
.btn{background-color:#FFF;color:#663;font-size:16px;height:35px;width:120px;font-weight:bold;margin-left:-50px}
</style>

</head>
<body><!-- background="images/img1.jpg" -->
<br/><br/><br/><br/><br/><br/>
 <div id="Div">
   <form action="" method="post" name="text">
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
       <tr height="35px"> 
         <td colspan="2" align="center">
          <h1><%=request.getAttribute("successmessage") %></h1>
                    您当前余额为：￥<%=request.getAttribute("balance") %>
         </td>
       </tr>      
     </table>
    </form>
 </div>
</body>
</html>