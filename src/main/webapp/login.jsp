<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
     <form action="<%=basePath%>j_spring_security_check" method="post">
      <table>
      	  <c:if test="${param.error != null }">
              <tr><td colspan=2> <span style="color:red;font-size:12"> 登录失败，请重新登录  </span> </td></tr>
          </c:if>
          <tr> <td>用户名</td> <td> <input  type="text"  name="j_username" /></td> </tr>
           <tr> <td>密码</td> <td> <input  type="password" name='j_password' /></td> </tr>
           <tr><td> <input type="submit" value="提交"></td></tr>
      </table>
      </form>
  </body>
</html>
