<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<c:set var="basePath" value="<%=basePath%>" />

<html>
	<body>
		<div style="text-align: center;">
	
			<c:forEach items="${cityList}" var="city">
				<div>
					${city.city }<br/>
					${city.cnty }<br/>
					${city.id }<br/>
					${city.lat }<br/>
					${city.lon }<br/>
					<br/>
					<br/>
					<br/>
					<br/>
				</div>
		</c:forEach>
	</div>

	</body>
</html>
