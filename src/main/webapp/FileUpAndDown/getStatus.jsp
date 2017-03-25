<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String value = (String)session.getAttribute("progress");
	if(value!=null){
		out.print(value);
	}
%>
