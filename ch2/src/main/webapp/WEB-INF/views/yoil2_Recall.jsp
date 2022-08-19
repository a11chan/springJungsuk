<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>yoil.jsp</title>
</head>
<body>
  <h1>(getParameter) year = <%= request.getParameter("year") %> -> 주소창에서 매개변수를 직접 입력한 경우 사용 가능</h1>
  <h1>(getAttribute) year = <%= request.getAttribute("myDate") %> -> 스크립트릿으로 현재 페이지 안에서 지역변수를 선언한 경우, Model 객체에 키 이름이 있는 경우에만 사용 가능</h1>
  <p>Model 객체에 추가한 키값 = myDate, yoil</p>
  <p>(param. 입력)요청하신 날짜는 ${param.year}년 ${param.month}월 ${param.day}일이며</p>
  <p>(param. 생략)요청하신 날짜는 ${year}년 ${month}월 ${day}일이며</p>
  <p>(param. 생략)해당하는 요일은 ${yoil}요일입니다.</p>
  <p>EL -> param.매개변수 이름 == pageContext.request.getParameter("매개변수 이름")</p>
  <p>서버에서 Model 객체를 통해 넘어온 키 이름이 있으면 param 생략 가능</p>
</body>
</html>