<!-- contentType 및 문자셋 설정 -->
<%@ page contentType="text/html;charset=utf-8" %>
<!-- taglib 설정 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Java - URLDecoder 클래스 import -->
<%@ page import="java.net.URLDecoder"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <style>
        * { box-sizing:border-box; }

        form {
            width:400px;
            height:600px;
            display : flex;
            flex-direction: column;
            align-items:center;
            position : absolute;
            top:50%;
            left:50%;
            transform: translate(-50%, -50%) ;
            border: 1px solid rgb(89,117,196);
            border-radius: 10px;
        }

        .input-field {
            width: 300px;
            height: 40px;
            border : 1px solid rgb(89,117,196);
            border-radius:5px;
            padding: 0 10px;
            margin-bottom: 10px;
        }
        label {
            width:300px;
            height:30px;
            margin-top :4px;
        }

        button {
            background-color: rgb(89,117,196);
            color : white;
            width:300px;
            height:50px;
            font-size: 17px;
            border : none;
            border-radius: 5px;
            margin : 20px 0 30px 0;
        }

        .title {
            font-size : 50px;
            margin: 40px 0 30px 0;
        }

        .msg {
            height: 30px;
            text-align:center;
            font-size:16px;
            color:red;
            margin-bottom: 20px;
        }
        .sns-chk {
            margin-top : 5px; 
        }
    </style>
    <title>Register</title>
</head>
<body>
    <!-- form
      action 속성의 주소를 jstl로 url 표현
      onsubmit 속성값으로 return문 현재 form 자체를 매개변수로 하는 formCheck 함수 호출
     -->
   <form action="<c:url value="/register_1/save"/>" method="post" onsubmit="return formCheck(this)">
    <div class="title">Register</div>
    <div id="msg" class="msg">
      <!-- jstl if문으로 param.msg가 비어있지 않으면 
        디코딩하여 메시지 출력
        java.net.URLDecoder 사용
      -->
      <c:if test="${not empty param.msg}">
          <i class="fa fa-exclamation-circle"> ${URLDecoder.decode(param.msg)}</i>            
      </c:if>
    </div> 
    <label for="">아이디</label>
    <input class="input-field" type="text" name="id" placeholder="8~12자리의 영대소문자와 숫자 조합">
    <label for="">비밀번호</label>
    <input class="input-field" type="text" name="pwd" placeholder="8~12자리의 영대소문자와 숫자 조합">
    <label for="">이름</label>
    <input class="input-field" type="text" name="name" placeholder="홍길동">
    <label for="">이메일</label>
    <input class="input-field" type="text" name="email" placeholder="example@fastcampus.co.kr"> 
    <label for="">생일</label>
    <input class="input-field" type="text" name="birth" placeholder="2020/12/31">
    <div class="sns-chk">
        <label><input type="checkbox" name="sns" value="facebook"/>페이스북</label>
        <label><input type="checkbox" name="sns" value="kakaotalk"/>카카오톡</label>
        <label><input type="checkbox" name="sns" value="instagram"/>인스타그램</label>
    </div>
    <button>회원 가입</button>
   </form> 
   <script>
   /* frm을 매개변수로 하는 formCheck 함수 선언 */
       function formCheck(frm) {
    	   /* 변수 msg를 빈문자열로 초기화 */
         let msg ='';
         
    	   /* if문으로 form 내의 id요소의 값이 3글자 미만이면 */
         if(frm.id.value.length<3) {
        	 /* setMessage 함수의 파라미터로 'id의 길이는 3 이상이어야 합니다.', form 요소의 id 자체를 전달 */
             setMessage('id의 길이는 3이상이어야 합니다.', frm.id);
        	 /* formCheck의 리턴값으로 false 전달 */
           return false;
         }

    	   /* if문으로 form 내의 pwd요소의 값이 3글자 미만이면 */
         if(frm.pwd.value.length<3) {
        	 /* setMessage 함수의 파라미터로 'pwd의 길이는 3 이상이어야 합니다.', form 요소의 pwd 자체를 전달 */
           setMessage('pwd의 길이는 3이상이어야 합니다.', frm.pwd);
           /* formCheck의 리턴값으로 false 전달 */
           return false;
         }           
        
    	   /* formCheck 함수의 리턴값으로 true 전달 */
        return true;
       } /* formCheck 함수 끝 */

       /* msg와 element를 매개변수로 하는 setMessage 함수 선언 */
       function setMessage(msg, element){
    	   /* 문서에서 "msg" 라는 id를 가진 요소의 innerHTML에 다음 내용 삽입
    	     `<i class="fa fa-exclamation-circle"> </i>`
    	     <i> 안에 템플릿 리터럴로 msg 변수 표시되게 하기
    	   */
         document.getElementById("msg").innerHTML = `<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>`;

         /* if문의 파라미터 element가 존재하면 */
         if(element) {
        	 /* element에 커서가 이동되도록 설정 */
           element.select();
         }
         
       } /* setMessage 함수 끝 */
   </script>
</body>
</html>