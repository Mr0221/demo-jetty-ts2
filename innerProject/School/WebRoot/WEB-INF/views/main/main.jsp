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
              <h1>ѧУ�칫����ϵͳ</h1> 
        </div>
        <div data-options="region:'west',split:true" title="menu" style="width:165px;">
	        <div class="easyui-accordion" data-options="multiple:true" style="width:100%;height=100%;">
				<div title="������" style="padding:5px;">
				   <ul>
				     <c:if test="${user.allRights['m01-01'] != null}">
				     	<li><a href="<%=basePath%>user/ViewQuestionSvl" target="myiframe">����鿴</a></li>
				     </c:if>				     
				     <c:if test="${rights['m01-02'] != null}">
				     	<li><a href="<%=basePath%>#" target="myiframe">����</a></li>
				     </c:if>				    
				     <c:if test="${user.allRights['m01-03'] != null}">
				     	<li><a href="#" target="myiframe">�����޸�</a></li>
				     </c:if>
				   </ul>					
				</div>
				<div title="�γ̹���"  style="padding:5px;">
				    <ul>
				    	<c:if test="${user.allRights['m02-01'] != null}">
				    		<li><a href="<%=basePath%>user/PlanCourseSvl" target="myiframe">�༶�ſ�</a></li>
				    	</c:if>
				    	<c:if test="${user.allRights['m02-02'] != null}">
				    		<li><a href="#" target="myiframe">�γ̵���</a></li>		
				    	</c:if>			
				    	<c:if test="${user.allRights['m02-03'] != null}">
				    		<li><a href="#" target="myiframe">��ʦ����</a></li>		
				    	</c:if>			
					</ul>
				</div>
				<div title="ѧ�����ڹ���"  style="padding:5px;">
				    <ul>
				       <c:if test="${user.allRights['m03-01'] != null}">
				    		<li><a href="#" target="myiframe">���ڲ鿴</a></li>
				    	</c:if>
				    	<c:if test="${user.allRights['m03-02'] != null}">
				    		<li><a href="#" target="myiframe" >����¼��</a></li>
				    	</c:if>
				    	<c:if test="${user.allRights['m03-03'] != null}">
				    		<li><a href="#" target="myiframe" >����ά��</a></li>
				    	</c:if>				    		
					</ul>
				</div>
				<div title="��֯����"  style="padding:5px;">
				    <ul>
				    	<c:if test="${user.allRights['m04-01'] != null}">
				    		<li><a href="#" target="myiframe" >���԰����б�</a></li>
				    	</c:if>
				    	<c:if test="${user.allRights['m04-02'] != null}">
				    		<li><a href="#" target="myiframe" >��֯����</a></li>
				    	</c:if>
				    	<c:if test="${user.allRights['m04-03'] != null}">
				    		<li><a href="#" target="myiframe" >������Ϣ����</a></li>
				    	</c:if>				    		
					</ul>
				</div>	
				<div title="���Թ���"  style="padding:5px;">
				    <ul>		
				   		 <c:if test="${user.allRights['m05-01'] != null}">		    	
				    		<li><a href="#" target="myiframe" >�鿴������Ϣ</a></li>
				    	</c:if>				    	    						
					</ul>
				</div>	
				<div title="�ľ����"  style="padding:5px;">
					<ul>
				    	<c:if test="${user.allRights['m06-01'] != null}">
				    		<li><a href="#" target="myiframe" >�ľ�</a></li>
				    	</c:if>
				    	<c:if test="${user.allRights['m06-02'] != null}">
				    		<li><a href="#" target="myiframe" >�鿴�Ծ�</a></li>
				    	</c:if>
				    	<c:if test="${user.allRights['m06-03'] != null}">
				    		<li><a href="#" target="myiframe" >�Ծ����</a></li>
				    	</c:if>				    		
					</ul>
				</div>
				<div title="�ɼ�ͳ��"  style="padding:5px;">
					<ul>
				    	<c:if test="${user.allRights['m07-01'] != null}">
				    		<li><a href="#" target="myiframe" >�ɼ�ͳ��</a></li>
				    	</c:if>
				    	<c:if test="${user.allRights['m07-02'] != null}">
				    		<li><a href="#" target="myiframe" >�ɼ��鿴</a></li>
				    	</c:if>				    			    		
					</ul>
				</div>
				<div title="ѧ������"  style="padding:5px;">
					<ul>
				    	<c:if test="${user.allRights['m08-01'] != null}">
				    		<li><a href="#" target="myiframe" >ѧ��������Ϣ¼��</a></li>
				    	</c:if>
				    	<c:if test="${user.allRights['m08-02'] != null}">
				    		<li><a href="#" target="myiframe" >ѧ������</a></li>
				    	</c:if>
				    	<c:if test="${user.allRights['m08-03'] != null}">
				    		<li><a href="#" target="myiframe" >ѧ������</a></li>
				    	</c:if>				    		
					</ul>
				</div>
				<div title="�������"  style="padding:5px;">
					<ul>
				    	<c:if test="${user.allRights['m09-01'] != null}">
				    		<li><a href="#" target="myiframe" >�������</a></li>
				    	</c:if>				    	  		
					</ul>
				</div>
				<div title="��������"  style="padding:5px;">
					<ul>
				    	<c:if test="${user.allRights['m10-01'] != null}">
				    		<li><a href="#" target="myiframe" >��������</a></li>
				    	</c:if>				    	  		
					</ul>
				</div>
				<div title="��ҵ����"  style="padding:5px;">
					<ul>
				    	<c:if test="${user.allRights['m11-01'] != null}">
				    		<li><a href="#" target="myiframe" >��ҵ����</a></li>
				    	</c:if>				    	  		
					</ul>
				</div>
				<div title="��ʦ����"  style="padding:5px;">
					<ul>
				    	<c:if test="${user.allRights['m12-01'] != null}">
				    		<li><a href="#" target="myiframe" >��ʦ����</a></li>
				    	</c:if>				    	  		
					</ul>
				</div>
				<div title="���ڹ���"  style="padding:5px;">
					<ul>
				    	<c:if test="${user.allRights['m13-01'] != null}">
				    		<li><a href="#" target="myiframe" >���ڹ���</a></li>
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
