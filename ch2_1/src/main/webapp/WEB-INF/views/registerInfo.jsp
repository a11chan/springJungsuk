<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>id=${user.id}</h1>
<h1>pwd=${user.pwd}</h1>
<h1>name=${user.name}</h1>
<h1>email=${user.email}</h1>
<h1>birth=${user.birth}</h1>
<h1>hobby=
	<c:forEach var="item" items="${user.hobby}">
	 ${item}
	</c:forEach>
</h1>
<h1>sns=${user.sns[0]}, ${user.sns[1]}, ${user.sns[2]}</h1>

</body>
</html>