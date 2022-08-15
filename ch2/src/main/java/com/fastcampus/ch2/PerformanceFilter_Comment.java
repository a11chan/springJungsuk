package com.fastcampus.ch2;

// 필터를 적용할 요청의 패턴 지정 - 모든 요청에 필터를 적용.
// 클래스 선언, Filter 인터페이스 구현
  
  // init 메서드 선언, ServletException 예외 던지기
    // 초기화 작업

  // 관심사 분리할 내용을 doFilter 메서드에 선언, 필터에 사용되는 객체 매개변수 선언, IOException, ServletException 예외 던지기
    // 1. 전처리 작업 -> 내용 사용자 정의
    // 현재 시간을 long startTime 변수에 저장

    // 2. 서블릿 또는 다음 필터를 호출 -> 내용 고정
    // 요청과 응답이 있을 경우 필터 실행
    
    // 3. 후처리 작업 -> 내용 사용자 정의
    // request 참조변수를 HttpServletRequest로 형변환하여 req 참조변수에 저장
    // req 참조변수에서 getHeader 메서드를 통해 참조페이지를 String referer 변수에 저장
    // req 참조변수에서 getMethod 메서드를 통해 HTTP 요청방식을 저장
    
    // print, println, getRequestURI() 메서드롤 통해 아래와 같이 출쳑되도록 할 것
    // [null] -> GET[/ch2/el.jsp] 소요시간=136ms

  // 필터 수행 후 정리 작업 선언

