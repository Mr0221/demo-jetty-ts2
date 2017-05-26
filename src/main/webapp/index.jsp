<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="javax.naming.InitialContext,javax.sql.DataSource"%>
<html>
<head>
<title>JNDI datasource test</title>
</head>
<body>
<h2>数据源初始化成功！</h2>
<%
    InitialContext context = new InitialContext();
    DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/myName");
%>
</body>
</html>
