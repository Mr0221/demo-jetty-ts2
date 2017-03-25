<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort(); %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>目录</title>
    <style type="text/css">
        .li_div{width:350px; }
        ul{list-style-type:none;}
        li{float:left;}
    </style>
  </head>
  <body style="font-family:'微软雅黑'; font-size:12;">
  <ul>
    <li>
        <div class="li_div">
            <c:forEach var="me" items="${htmlMap}">
                <span style="padding:30px; display='block';">${me.key}</span><a href='${basePath}${me.value}' target = "view_window">打开链接</a><br/>
            </c:forEach>
        </div>
    </li>
    <li>
        <div class="li_div">
        <c:forEach var="me" items="${jspMap}">
            <span style="padding:30px; display='block';">${me.key}</span><a href='${basePath}${me.value}' target = "view_window">打开链接</a><br/>
        </c:forEach>
        </div>
    </li>
    <li>
        <div class="li_div">
            <c:forEach var="me" items="${actionMap}">
                <span style="padding:30px; display='block';">${me.key}</span><a href='${basePath}${me.value}' target = "view_window">打开链接</a><br/>
            </c:forEach>
        </div>
    </li>
  </ul>




  </body>
</html>
