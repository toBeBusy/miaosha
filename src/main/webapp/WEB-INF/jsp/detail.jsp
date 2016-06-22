<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>秒杀详情页</title>
<%@include file="common/head.jsp" %>
</head>
<body>
	<div class="container">
		<div class="panel panel-default text-center">
			<div class="panel-heading">${seckill.name }</div>
		</div>
		<div class="panel-body">
			<h2 class="text-danger">
				<!-- 显示time图标 -->
				<span class="glyphicon glyphicon-time"></span>
				<!-- 展示倒计时 -->
				<span class="glyphicon" id="seckill-box"></span>
			</h2>
		</div>
	</div>
	<!-- 登录弹出层，输入电话 -->
	<div id="killPhoneModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title text-center">
						<span class="glyphicon glyphicon-phone"></span>
					</h3>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-8 col-xs-offset-2">
							<input type="text" name="killPhone" id="killPhone"
								placeholder="请输入手机号" class="form-control">
						</div>
					</div>
				</div>
				
				<div class="modal-footer">
					<!-- 验证信息 -->
					<span id="killPhoneMessage" class="glyphicon"></span>
					<button type="button" id="killPhoneBtn" class="btn btn-success">
						<span class="glyphicon glyphicon-phone"></span>
						Submit
					</button>
				</div>
			</div>
		</div>
	</div>
</body>
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>

	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script
		src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	
	<!-- 使用CDN 获取公共js http://www.bootcdn.cn/-->
	<!-- jQuery cookie操作插件 -->
	<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
	<!-- jQuery countDown倒计时插件 -->
	<script src="https://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.js"></script>
	<!-- 开始编写交互逻辑 -->
	<script type="text/javascript" src="/resources/script/seckill.js"></script>
	<script type="text/javascript">
		$(function(){
			//使用el表达式传入参数
			seckill.detail.init({
				seckillId: ${seckill.seckillId},
				startTime: ${seckill.startTime.time},//毫秒
				endTime: ${seckill.endTime.time}
			});
		});
	</script>
</html>