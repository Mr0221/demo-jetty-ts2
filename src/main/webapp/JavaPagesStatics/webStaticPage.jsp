<%@page language="java" import="com.web.statics.tst.WebStatic" %>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
request.setCharacterEncoding("utf-8");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String title = request.getParameter("title");
String author = request.getParameter("author");
String content = request.getParameter("content");
String template = request.getParameter("template");
String link = "";
if(template!= null){
    Map<String, String>values = new HashMap<String, String>();
    values.put("##title##",title);
    values.put("##content##",content);
    values.put("##author##",author);
    String fileName = new Date().getTime()+"";
    String templatePath = application.getRealPath("/template"+template);
    String  htmlPath= application.getRealPath("/"+fileName+".html");
//    WebStatic.beReady2Url2(templatePath, htmlPath,values);
    link = "<a target='_blank' href='"+path+"/'>发布成功</a>";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>web static page</title>
<meta name="Keywords" content="关键词，关键词">
<meta name="Author" content="ey">
<meta name="description" content="">
<style type="text/css">
body{/* background-color:rgba(136, 166, 213, 0.7); */ color:#fff;font-family:"微软雅黑"；font-size:12;color:black;}
h1{text-shadow:2px 2px 5px #000;}
div{text-align:center;width:800px; margin:0 auto;}
</style>
</head>
<body>
    <div class="box">
    <h1>Java web pages static</h1>
    <div class="content">
                                请选择模板：<select>
                 <option>请选择</option>
                 <option>从未拥有</option>
                 <option>时间太快</option>
                 </select>
         <p>标题:<input type="text" value="##title##"/></p>
         <p>作者:<input type="text" value="##Author##1"/></p>
         内容：<textarea cols="100" rows="20">"##content##"</textarea>
    </div>
    </div>
</body>
</html>
