<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>yoil.jsp</title>
</head>
<body>
  <h1>YEAR=<%=request.getParameter("year") %></h1>
  <p>${year}년 ${month}월 ${day}일은 ${yoil}요일입니다.</p>
  <p>${param.year}년 ${param.month}월 ${param.day}일은 ${yoil}요일입니다.</p>
  <p>${myDate.year}년 ${myDate.month}월 ${myDate.day}일은 ${yoil}요일입니다.</p>
</body>
</html>