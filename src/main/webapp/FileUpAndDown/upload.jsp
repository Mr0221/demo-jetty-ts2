<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>文件上传</title>
    <style>
    	.progressContent{
    		width:340px;
    		background:#999;
    		border-radius:5px;
    	}
    	.progress{ background:#4f6c7d; height:20px;width:50%;colors:#fff;border-radius:3px;
    	}
    </style>
  </head>
  <body>
    <form action="${pageContext.request.contextPath}/servlet/UploadHandleServlet" enctype="multipart/form-data" method="post">
        上传用户：<input type="text" name="username"><br/>
        上传文件1：<input type="file" name="file1" onchange="doUpload()"><br/>
        上传文件2：<input type="file" name="file2"><br/>
        <!-- <input type="submit" value="提交">  -->
    </form>
    <div id="content">
    	<div class="progressContent">
    		<div id="name">132</div>
    		<div id="progess" class="progress" align="left">100%</div>
    		<div id="info">0.11/0.1！ 上传成功！</div>
    	</div>
    </div>
    <div id="result">
    
    </div>
     <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.3.min.js"></script>
     <script>
    
     function doUpload(){
 		document.forms[0].submit();
 		setTimeOut("getProgress()", 100);
 	}
 	function getProgress(){
 		$.ajax({
 			type:"post",
 			url:"getStatus.jsp",
 			success:function(data){
 				//更新进度条
 				eval(data);
 				
 			},
 			fail:function(){
 				
 			}
 		});
 	}
     </script>
  </body>
</html>
