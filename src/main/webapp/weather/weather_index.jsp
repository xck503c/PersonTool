<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="/PersonTool/static/Echarts/echarts.min.js"></script>
		<script src="/PersonTool/static/Echarts/china.js"></script>
	</head>
	<body>
		<form action="${basePath}/PersonTool/f/weather/basicCity/query" method="post">
			<table border="1" align="center">
				<tr>
					<caption><h2>天气查询</h2></caption>
					<td>需要查询的城市:</td>
					<td><input type="text" name="city"/></td>
					<td><input type="submit" value="提交"/></td>
					<td>${msg}</td>
				</tr>
			</table>
		</form>
		<div id="china_map" style="margin: 0 auto;padding:0px;width:1000px;height:500px;"></div>
		<script src="/PersonTool/static/js/weather_index.js"></script>
	</body>
</html>