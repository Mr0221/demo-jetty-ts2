<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <%@page import="com.alibaba.fastjson.JSONArray" %>
 <%@ page language="java" import="java.util.*, com.Crawler.pro.*" %>
 <% 
 	/* String pageNum ="1";
    String category ="11"; */
    String pageNum= request.getParameter("pageNum");
    String category = request.getParameter("catagory");
    List<ImageElement> images = SpiderUtil.getImageList_me(pageNum);
    out.print( JSONArray.toJSONString(images, true) );
 %>
