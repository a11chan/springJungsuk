package com.fastcampus.ch2;

//@원격 프로그램으로 등록
//@클래스에 /board URL 매핑
public class BoardController_c {
  
  // @ get 요청 "/logout" 매핑
  // view 이름을 반환하는 logout 메서드 선언
  // session을 종료할 수 있도록 파라미터 설정
    //session 종료
    //루트 페이지를 redirect 하도록 리턴문 작성
  
  // @ get 요청을 "/list"에 매핑
  // view 이름을 반환하는 list 메서드 선언
  // request 객체에서 session 객체를 꺼낼 수 있도록 파라미터 설정
    // loginCheck(request) 함수의 결과가 false일 때
      // redirect로 /login/login 경로를 요청하도록 리턴
      // + 원래 이동하려고 했던 페이지를 쿼리스트링으로 추가
      // 변수 이름은 toURL로 할 것
    
    // "boardList" 뷰를 리턴

  // request 참조변수를 파라미터로 하는 loginCheck 함수 선언
    //1. 세션을 얻어서
    //2. 세션에 id가 있는지 확인, 있으면 true 반환
}