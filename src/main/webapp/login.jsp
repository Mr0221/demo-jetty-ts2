<!DOCTYPE html> 
<html> 
<head> 
<meta charset="utf-8"/> 
<title></title> 
</head> 
<style type="text/css"> 
*{margin: 0; padding: 0;font-family:"微软雅黑"; font-size:12px;} 

#header{position: relative;width: 100%; height: 65px;border: 1px solid yellow;} 
#header #header_left{width: 600px; height: 25px; float: left; border: 1px solid yellow; margin-top:20px;} 
#header #header_right{width: 300px; height:40px; line-height:40px;float: right; border: 1px solid yellow;margin-top:20px;} 
.cls_header1, .cls_header2, .cls_header3, .cls_header4, .cls_header5, .cls_header6, .cls_header7, .cls_header8 { display: inline-block; text-decoration: none; margin-left: 5px; border: 1px solid yellow; width: 70px;text-align:center;height: 25px;line-height:25px;} 
#id_right{} 
#header #header_right a{font-size: 18px;} 
#header #header_right a:hover{border:1px solid blue;} 
.content{width: 100%; height: 650px;border: 1px solid yellow;} 
.footer{width: 100%; height: #header #header_left .service_sub ul;border: 1px solid yellow;line-height:70px;} 
.login{width: 500px; height: 420px;border: 1px solid yellow;margin:115px auto;} 
.login .login_title{width:400px;height:75px;margin:0 auto;border:1px solid green;} 
.login .login_title label{line-height:75px; font-size:21px;} 
.login .login_content{width:400px;margin:0 auto;} 
.login .login_content .login_content_input{width:330px; height:35px;} 
.login .login_content .login_content_cls{width:400px; height:40px;border:1px solid yellow;margin-top:20px; line-height:40px;text-align:center;} 
.login .login_content .login_content_cls .login_content_cls_rem{float:left;} 
.login .login_content .login_content_cls .login_content_cls_miss{float:right;} 
.login .login_content .login_content_cls .login_content_cls_miss a{text-decoration:none;} 
.login .login_content .login_content_cls a{text-decoration:none;} 
.footer .footer_left{width:300px;float:left;border:1px solid yellow;} 
.footer .footer_right{width:300px;float:right;border:1px solid yellow;} 
#header #header_left {position:relative;} 
#header #header_left .service_sub{position:absolute; top:30px; left: 327px; border:1px solid blue; display:none;} 
#header #header_left .service_sub ul{list-style:none;text-align:center; } 
#header #header_left .service_sub ul li a{display:block; width:70px;height: 25px; line-height:25px; text-decoration:none;} 
#header #header_left .service_sub_hp{position:absolute; top:30px; left: 408px; border:1px solid blue; display:none;} 
#header #header_left .service_sub_hp ul{list-style:none;text-align:center; } 
#header #header_left .service_sub_hp ul li a{display:block; width:70px;height: 25px; line-height:25px; text-decoration:none;} 

#header #header_left cls_header5:hover{}/*??*/ 
.login_image{display:none;} 
</style> 
<body> 
<div id="header"> 
<div id="header_left"> 
<a href="#" class="cls_header1">项目</a> 
<a href="#" class="cls_header2">估价</a> 
<a href="#" class="cls_header3">案例</a> 
<a href="#" class="cls_header4">社区</a> 
<a href="#" class="cls_header5">增值服务</a> 
<div class="service_sub"> 
<ul> 
<li><a href="">测试服务</a></li> 
<li><a href="">运维服务</a></li> 
</ul> 
</div> 
<a href="#" class="cls_header6">帮忙</a> 
<div class="service_sub_hp"> 
<ul> 
<li><a href="">测试服务</a></li> 
<li><a href="">运维服务</a></li> 
</ul> 
</div> 
</div> 
<div id="header_right"> 
<a href="#" class="cls_header7" id="id_right">登录</a> 
<a href="#" class="cls_header8" id="id_right">注册</a> 
</div> 
</div> 
<div class="content"> 
<div class="login"> 
<form action="" method="post"> 
<div class="login_title"> 
<label>帐号登录</label> 
</div> 
<hr> 
<div class="login_content"> 
<div class="login_content_cls"> 
用户名：<input type="text" name="user_name" class="login_content_input"> 
</div> 
<div class="login_content_cls"> 
密&nbsp;码：<input type="password" name="password" class="login_content_input"> 
</div> 
<div class="login_content_cls"> 
<div class="login_content_cls_rem"> 
<input type="checkbox">&nbsp;记住我 
</div> 
<div class="login_content_cls_miss"> 
<a href="">找回密码</a> 
</div> 
</div class="login_content_cls"> 
<div><input type="submit" value="登陆" class="login_content_cls"></div> 
<div class="login_content_cls"> 
<a href="">还没有账户，马上注册</a> 
</div> 
</div> 
</form> 
</div> 
<div class="login_image"> 
<img src=""> 
</div> 
</div> 
<div class="footer"> 
<div class="footer_left"> 
<img src=""> 
<span>Eddy Li</span> 
</div> 
<div class="footer_right"> 
<span>Copyright &copy Eddy Li Perform</span> 
<select> 
<option id="en" name="language" value="0" selected>中文</option> 
<option id="en" name="language" value="1">English</option> 

</select> 
</div> 
</div> 
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script> 
<script type="text/javascript"> 

$(function(){ 
$(".cls_header5").mousemove(function(){ 
$(".service_sub").css({"display":"block"}); 
}); 
$(".service_sub").hover(function(){ 

}, function(){ 
$(".service_sub").css({"display":"none"}); 
}); 

$(".cls_header6").mousemove(function(){ 
$(".service_sub_hp").css({"display":"block"}); 
}); 
$(".service_sub_hp").hover(function(){}, 
function(){ 
$(".service_sub_hp").css({"display":"none"}); 
}); 
}); 
</script> 
</body> 
</html>
