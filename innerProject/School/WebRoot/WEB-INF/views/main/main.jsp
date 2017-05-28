<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>themes/icon.css">	
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>    
  </head>
  
  <body>
    <div class="easyui-layout" style="width:99%;height:99%;">
        <div data-options="region:'north'" style="height:70px">              
              <h1>学校办公管理系统</h1> 
        </div>
        <div data-options="region:'west',split:true" title="menu" style="width:165px;">
	        <div class="easyui-accordion" data-options="multiple:true" style="width:100%;height=100%;">
				<div title="题库管理" style="padding:5px;">
				   <ul>
				     <c:if test="${user.allRights['m01-01'] != null}">
				     	<li><a href="<%=basePath%>user/ViewQuestionSvl" target="myiframe">试题查看</a></li>
				     </c:if>				     
				     <c:if test="${rights['m01-02'] != null}">
				     	<li><a href="<%=basePath%>#" target="myiframe">出题</a></li>
				     </c:if>				    
				     <c:if test="${user.allRights['m01-03'] != null}">
				     	<li><a href="#" target="myiframe">试题修改</a></li>
				     </c:if>
				   </ul>					
				</div>
				<div title="课程管理"  style="padding:5px;">
				    <ul>
				    	<c:if test="${user.allRights['m02-01'] != null}">
				    		<li><a href="<%=basePath%>user/PlanCourseSvl" target="myiframe">班级排课</a></li>
				    	</c:if>
				    	<c:if test="${user.allRights['m02-02'] != null}">
				    		<li><a href="#" target="myiframe">课程调整</a></li>		
				    	</c:if>			
				    	<c:if test="${user.allRights['m02-03'] != null}">
				    		<li><a href="#" target="myiframe">讲师调整</a></li>		
				    	</c:if>			
					</ul>
				</div>
				<div title="学生考勤管理"  style="padding:5px;">
				    <ul>
				       <c:if test="${user.allRights['m03-01'] != null}">
				    		<li><a href="#" target="myiframe">考勤查看</a></li>
				    	</c:if>
				    	<c:if test="${user.allRights['m03-02'] != null}">
				    		<li><a href="#" target="myiframe" >考勤录入</a></li>
				    	</c:if>
				    	<c:if test="${user.allRights['m03-03'] != null}">
				    		<li><a href="#" target="myiframe" >考勤维护</a></li>
				    	</c:if>				    		
					</ul>
				</div>
				<div title="组织考试"  style="padding:5px;">
				    <ul>
				    	<c:if test="${user.allRights['m04-01'] != null}">
				    		<li><a href="#" target="myiframe" >考试安排列表</a></li>
				    	</c:if>
				    	<c:if test="${user.allRights['m04-02'] != null}">
				    		<li><a href="#" target="myiframe" >组织考试</a></li>
				    	</c:if>
				    	<c:if test="${user.allRights['m04-03'] != null}">
				    		<li><a href="#" target="myiframe" >考试信息调整</a></li>
				    	</c:if>				    		
					</ul>
				</div>	
				<div title="考试管理"  style="padding:5px;">
				    <ul>		
				   		 <c:if test="${user.allRights['m05-01'] != null}">		    	
				    		<li><a href="#" target="myiframe" >查看更新信息</a></li>
				    	</c:if>				    	    						
					</ul>
				</div>	
				<div title="阅卷管理"  style="padding:5px;">
					<ul>
				    	<c:if test="${user.allRights['m06-01'] != null}">
				    		<li><a href="#" target="myiframe" >阅卷</a></li>
				    	</c:if>
				    	<c:if test="${user.allRights['m06-02'] != null}">
				    		<li><a href="#" target="myiframe" >查看试卷</a></li>
				    	</c:if>
				    	<c:if test="${user.allRights['m06-03'] != null}">
				    		<li><a href="#" target="myiframe" >试卷调整</a></li>
				    	</c:if>				    		
					</ul>
				</div>
				<div title="成绩统计"  style="padding:5px;">
					<ul>
				    	<c:if test="${user.allRights['m07-01'] != null}">
				    		<li><a href="#" target="myiframe" >成绩统计</a></li>
				    	</c:if>
				    	<c:if test="${user.allRights['m07-02'] != null}">
				    		<li><a href="#" target="myiframe" >成绩查看</a></li>
				    	</c:if>				    			    		
					</ul>
				</div>
				<div title="学生管理"  style="padding:5px;">
					<ul>
				    	<c:if test="${user.allRights['m08-01'] != null}">
				    		<li><a href="#" target="myiframe" >学生基本信息录入</a></li>
				    	</c:if>
				    	<c:if test="${user.allRights['m08-02'] != null}">
				    		<li><a href="#" target="myiframe" >学生奖惩</a></li>
				    	</c:if>
				    	<c:if test="${user.allRights['m08-03'] != null}">
				    		<li><a href="#" target="myiframe" >学生考勤</a></li>
				    	</c:if>				    		
					</ul>
				</div>
				<div title="公告管理"  style="padding:5px;">
					<ul>
				    	<c:if test="${user.allRights['m09-01'] != null}">
				    		<li><a href="#" target="myiframe" >公告管理</a></li>
				    	</c:if>				    	  		
					</ul>
				</div>
				<div title="招生管理"  style="padding:5px;">
					<ul>
				    	<c:if test="${user.allRights['m10-01'] != null}">
				    		<li><a href="#" target="myiframe" >招生管理</a></li>
				    	</c:if>				    	  		
					</ul>
				</div>
				<div title="就业管理"  style="padding:5px;">
					<ul>
				    	<c:if test="${user.allRights['m11-01'] != null}">
				    		<li><a href="#" target="myiframe" >就业管理</a></li>
				    	</c:if>				    	  		
					</ul>
				</div>
				<div title="教师管理"  style="padding:5px;">
					<ul>
				    	<c:if test="${user.allRights['m12-01'] != null}">
				    		<li><a href="#" target="myiframe" >教师管理</a></li>
				    	</c:if>				    	  		
					</ul>
				</div>
				<div title="后勤管理"  style="padding:5px;">
					<ul>
				    	<c:if test="${user.allRights['m13-01'] != null}">
				    		<li><a href="#" target="myiframe" >后勤管理</a></li>
				    	</c:if>				    	  		
					</ul>
				</div>						
	         </div>
        </div>
        <div data-options="region:'center',title:'Main window',iconCls:'icon-ok'">           
    		<iframe name="myiframe" width="99%" height="99%" scrolling="no"></iframe>    
        </div>
    </div>
  </body>
</html>
