<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{background:#667; margin:0; padding:0;font-size:15px; color:#000;}
.header{width:500px; margin: 0 auto;}
.header h1{font-size:30px; color:#fff;text-shadow:2px 5px 5px #000;}
#wrapper{widht:100%;}
#content{position:relative;width:100%; margin:0 auto 25px; padding-bottom:10px;}
.gird{width:256px; min-height:354px;padding:15px; background:#fff; box-shadow:2px 5px 5px rgba(0,0,0,0.8);
	float:left; margin:8px;
	-webkit-transition:all 1s ease;
	-moz-transition:all 1s ease;
	-o-transition:all 1s ease;
	-ms-transition:all 1s ease;
	}
.grid strong{border-bottom:1px solid #000; display:block; margin:10px 0;padding:0 0 5px; font-size:15px;color:#000;}
.grid .meta{text-align:ringht;}
.grid .imgholder img{max-width:100%;background:#ccc;diplay:block;
	background:url(images/loding.gif) no-repeat center;
	}
</style>
<script type="text/javascript" src="../jquery.js"></script>
<script type="text/javascript" src="js/blocksit.min.js"></script>
<script type="text/javascript" src="js/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="js/jquery.lightbox-0.5.min.js"></script>

<link href="css/jquery.lightbox-0.5.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
	$(function(){
	//显示的行数
	var col = 5;
	var pageNum = 1; 
	function blockImage(){
		$("#content").BlocksIt({
			numOfCol:col,
			offsetX:8,
			offsetY:8
			});
	}
	$("img.lazy").lazyload({load:blockImage});
$("#btn").click(function(){
	loadImages();
	$("img.lazy").lazyload();
	});
	function loadImages(){
		var category ="11";
		
		$.ajax({
			type:"post",
			url:"data.jsp",
			data:{pageNum:pageNum,   category:category},
			dataType:"json",
			success:function(data){
				
				for(var i=0; i<data.length; i++){
					var img='';
					async:false;
					img += "<div class='gird'>";
					img +="<div class='imgholder'>";
					img +="<img class='lazy' src="+data[i].burl+" data-orifinal='http:？？''/>";
					img +="</div>";
					//img +="<strong>"+data[i].title +"</strong>";
					img +="<div class='meta'>";
					img +="<a href="+data[i].burl+" class='lightbox'>"+data[i].title+"</a>";
					img +="</div>";
					img +="</div>";
					$("#content").append(img);
				}	
			},
			error:function(){
				alert("fail");
			}
		});
		
		pageNum++;
		blockImage();
		$("a.lightbox").lightBox();//popup pic
		$("img.lazy").lazyload();//lazy load  瀑布流
	}
	$(window).scroll(function(){
		//当滚动到自底部50px的时候, 开始加载
		if($(document).height()-$(this).scrollTop()-$(this).height()<50){
			loadImages();
		}
	});
	var currentWidth=1100;
	$(window).resize(function(){
		var winWidth = $(window).width();
		var conWidth =0 ;
		if(winWidth<400){
			conWidth=240;
			col =1;
		}else if(winWidth<660){
			conWidth=440;
			col =2;
		}else if(winWidth<880){
			conWidth=660;
			col =2;
		}else if(winWidth<1100){
			conWidth=880;
			col =2;
		}else{
			conWidth=1100;
			col = 5;
		}
		
		if(conWidth!=currentWidth){
			currentWidth = conWidth;
			$("#content").width(conWidth);
			blockImage();
		}
	});
	});
	
</script>
</head>
<body>
<!-- header s -->
<div class="header">
<h1>Java打造网络爬虫平台</h1>
<!-- <button onclick="loadImages()">点击加载</button> -->
<a href="javascript:void(0)" id="btn">点击加载</a>
</div>
<!-- header E -->

<!-- content s -->
<div id="wrapper">
	<div id="content">
		<!-- <div class="gird">
			<div class="imgholder">
			<img class="lazy" src="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3486700280,236246428&fm=116&gp=0.jpg" data-orifinal="http:？？"/>
			</div>
			<strong> 心情好</strong>
			<div class="meta">
				<a href="??" class="lightbox">高清无码原图</a>
			</div>
		</div> -->
		
		
	</div>
</div>
<!-- content e -->
</body>
</html>