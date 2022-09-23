<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>JSTL</title>
</head>
<body>
<% /* 반복문
변수 to 선언, 값은 10
변수 arr 선언, 값은 10,20,30,40,50,60,70

forEach문, i = 1 부터 이전에 선언한 변수 to까지
1씩 증가하면서 1부터 10까지출력
*/ %>
<c:set var="to" value="10"/>
<c:set var="arr" value="10,20,30,40,50,60,70" />
<c:forEach var="i" begin="1" end="${to}">
  ${i}
</c:forEach>
<br><br>

<% /* if문 예제
arr 변수가 비어있지 않다면
  forEach문의 varStatus를 사용하여 arr배열을 아래 형식으로 출력할 것
  1. arr[0] = 10
  ...
  7. arr[6] = 70
*/ %>

<c:if test="${not empty arr }">
  <c:forEach var="elem" items="${arr}" varStatus="status">
    ${status.count}. arr[${status.index}]=${elem}<br>
  </c:forEach>
</c:if>
<br>
<% /* param 및 if문, null 표시 예제
  파라미터 msg가 null이 아니면
  "msg = " 파라미터값 출력
  "msg = " <c:out>으로 파라미터 값 출력
  파라미터 msg가 null이면 메시지가 없습니다. 출력
  
  if문으로 단순히 param.msg가 null이면 "메시지가 없습니다." 출력하기
  
  localhost/ch2/jstl_comment.jsp?msg=?msg="<h1>hello</h1>"
*/ %>
<c:if test="${param.msg != null}">
  msg = ${param.msg}<br>
  msg = <c:out value="${param.msg}"/>
</c:if>
<br>
<c:if test="${param.msg == null}">
  메시지가 없습니다.
</c:if>
<br>

<% /* age 라는 파라미터를 통해 choose문으로 성인인자 아닌지 출력
변수명: age, 변수값: EL표현식으로 age에 있는 값을 얻어올 것
age >= 19 -> 성인입니다. 출력
age < 19 -> 성인이 아닙니다. 출력
다른 경우엔 유요한 값이 아닙니다. 출력

localhost/ch2/jstl_comment.jsp?msg=?msg="<h1>hello</h1>"&age=35
 */ %>
<c:set var="age" value="${param.age }"/>
<c:choose>
  <c:when test="${age >= 19 }">성인입니다.</c:when>
  <c:when test="${age < 19 }">성인이 아닙니다.</c:when>
  <c:otherwise>값이 유효하지 않습니다. </c:otherwise>
</c:choose>
<br><br>

<% /* 현재 시간을 스크립트릿으로 얻어와 now변수에 담고
<fmt>를 이용해 EL로 표현하기 value, type, pattern 속성 이용
*/ %>
<c:set var="now" value="<%=new java.util.Date() %>" />
Servier time is <fmt:formatDate value="${now}" type="both" pattern="yyyy/MM/dd HH:mm:ss"/>
</body>
</html>