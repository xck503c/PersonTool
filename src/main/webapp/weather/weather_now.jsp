<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<c:set var="basePath" value="<%=basePath%>" />

<html>
	<body>
		<div style="text-align: center;">
			<table border="1">
				<caption><h2>${city.city}实时天气</h2></caption>
				<tr>
					<!-- <td>城市ID</td> -->
					<td>城市名称</td>
					<td>国家</td>
					<td>所属省份</td>
					<td>更新时间</td>
					<td>天气状况</td>
					<td>体感温度</td>
					<td>相对湿度(%)</td>
					<td>降水量(mm)</td>
					<td>气压</td>
					<td>温度</td>
					<td>能见度(km)</td>
					<td>风向(角度)</td>
					<td>风向</td>
					<td>风力</td>
					<td>风速(kmph)</td>
					<td>操作</td>
				</tr>
					<tr>
						<!--<td>${city.id}</td> -->
						<td>${city.city}</td>
						<td>${city.cnty}</td>
						<td>${provC}</td>
						<td>${city.update}</td>
						<td>${city.cond}</td>
						<td>${city.fl}</td>
						<td>${city.hum}</td>
						<td>${city.pcpn}</td>
						<td>${city.pres}</td>
						<td>${city.tmp}</td>
						<td>${city.vis}</td>
						<td>${city.deg}</td>
						<td>${city.dir}</td>
						<td>${city.sc}</td>
						<td>${city.spd}</td>
						<td><a href="${basePath}/f/weather/nowCity/returnUp?provC=${provC}&provE=${provE}&city=${city.city}">返回</a></td>
					</tr>
			</table>
			<form action="${basePath}/f/weather/nowCity/query" method="post">
				<table border="1">
					<tr>
						<td>需要查询的城市:</td>
						<td><input type="text" name="city"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
