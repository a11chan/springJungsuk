package com.fastcampus.ch2;

//로컬 프로그램을 원격 호출 가능한 프로그램으로 등록하는 애너테이션
// 클래스 이름은 TwoDice_Recall

  // 브러우저 URL 요청 경로는 rollDice1
  // main 메서드 선언
    // 주사위 2개가 랜덤으로 눈이 나오도록 난수 2개 생성 및 변수에 저장
    
    //response 객체를 통해 브라우저에 결과 출력 준비작업
      //contentType 지정
      //문자 인코딩 방식 지정
      //PrintWriter 객체 얻어서 out 참조변수에 저장
    
    //이하 내용 브라우저에 출력
    /*
      "<html>"
      "<head>"
      "</head>"
      "<body>"
      "<img src='resources/img/dice"+ 난수1 + ".jpg'>"
      "<img src='resources/img/dice"+ 난수2 + ".jpg'>"
      "</body>"
      "</html>"
     */
