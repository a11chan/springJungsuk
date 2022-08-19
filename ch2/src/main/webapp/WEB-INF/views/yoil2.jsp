<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>yoil.jsp</title>
</head>
<body>
  <h1>year = <%= request.getParameter("year") %></h1>
  <p>EL{mydate.변수명} 요청하신 날짜는 ${mydate.year}년 ${mydate.month}월 ${mydate.day}일이며</p>
  <p>요청하신 날짜는 ${param.year}년 ${param.month}월 ${param.day}일이며</p>
  <p>해당하는 요일은 ${yoil}요일입니다.</p>
</body>
</html>