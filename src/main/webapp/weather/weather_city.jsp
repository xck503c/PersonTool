<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<c:set var="basePath" value="<%=basePath%>" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/PersonTool/static/Echarts/echarts.min.js"></script>
<script src="/PersonTool/static/Echarts/jquery.js"></script>
</head>
<body>
	<form action="${basePath}/f/weather/basicCity/query" method="post">
		<table border="1" align="center">
			<tr>
				<caption>
					<h2>${provC}天气查询</h2>
					<input type="hidden" name="provC" value="${provC}">
					<input id="prov" type="hidden" name="provE" value="${provE}">
				</caption>
				<td>${provC}城市搜索:</td>
				<td><input type="text" name="city"/></td>
				<td><input type="submit" value="提交"/></td>
				<td><a href="${basePath}/f/weather/basicCity/index?provC=${provC}&provE=${provE}">返回</a></td>
			</tr>
		</table>
	</form>
	<hr/><br/><br/>
	<table border="1" align="center">
		<tr>
			<td>城市名称</td>
			<td>国家</td>
			<td>所属省份</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${cityList}" var="city">
			<tr>
				<td>${city.city}</td>
				<td>${city.cnty}</td>
				<td>${city.prov}</td>
				<td>
					<a href="${basePath}/f/weather/nowCity/query?city=${city.id}&provC=${provC}&provE=${provE}">实时天气</a>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>