package com.fastcampus.ch2;

// @원격 프로그램으로 등록
// @클래스에 "/login" 매핑
public class LoginController_c {
  
  //@ get요청 "/login" 매핑
  // 뷰 이름을 반환하는 loginForm 메서드 선언(파라미터 없음)
    // 리턴으로 "loginForm" 뷰 이름 반환
  
  // @ get 요청 중 "/logout" 매핑
  // 뷰 이름을 반환하는 logout 메서드 선언
    // 파라미터로 session 참조변수 선언
    // 세션 종료 메서드 호출
    // 루트 경로를 redirect 하도록 리턴
  
  // @ post요청 중 "/login" 경로 매핑
  // 뷰 이름을 반환하는 login 메서드 선언, public, 파라미터 7개 선언
    // 파라미터 String cookieId에 "id" 쿠기값을 저장하는 에너테이션
    // 1. loginCheck 메서드 호출하여 id, pwd 확인
      // 일치하지 않으면 URLEncoder를 써서 메시지를 저장
      
      // 리턴문으로 loginForm redirect시키면서 메시지를 쿼리스트링으로 전달
    
    // 2. id, pw 일치시
    // 새션 객체를 얻어와
    // 세션 객체에 id 저장
    
    // 로그인 시 rememberId가 true이면
      // 쿠키 생성 및 "id"키에 사용자 id 등록
      // response 객체에 쿠키 추가(전달)
      // rememberId가 false 이면
      // "id"키에 로그인 id를 저장하는 쿠키 생성 후
      //  지속시간 0으로 설정
      // response 객체에 생성한 쿠키 전달(쿠키 적용)
    
    // 3. 홈으로 이동 로직(삼항연산자 사용)
      // toURL이 null 이거나 빈 문자열이면 루트경로 반환
      // toURL이 있으면 toURL 반환하여 toURL에 저장

  // redirect 시 toURL로 이동
  
  // 메서드명: loginCheck, 반환형: boolean, 접근제어자: private
  // 파라미터: String id, String pwd
    // 파라미터 id와 pwd를 각각 "asdf", "1234"와 and 조건 동등비교
}