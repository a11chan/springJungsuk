package com.fastcampus.ch2;

//원격 프로그램으로 등록
public class YoilTellerMVC6_Comment {

  // 현재 클래스에서 예외가 발생하면 Exception.class 예외로 처리하는 애너테이션 등록
  // 예외를 처리할 catcher 이름의 메서드 작성
  // 매개변수로 클래스 내에서 발생하는 예외와 사용자가 요청한 파라미터의 바인딩 오류 시 발생하는 예외를 인수로 함
    // 사용자가 요청한 파라미터의 바인딩이 잘못되었을 때 그 내용을 println으로 출력
    // 사용자가 요청한 파라미터의 바인딩이 잘못되었을 때의 에러 필드명을 println으로 출력

    // 에러 페이지 이름 yoilError를 반환

  // http://localhost/ch2/getYoilMVC6
  // URL 매핑 이름을 getYoilMVC6_Recall로 함
  // 사용자가 입력한 날짜를 MyDate 객체로 받는 main 메서드 선언
  // 사용자가 요청한 파라미터 바인딩 에러 처리를 위한 객체를 파라미터로 선언
  // 리턴페이지 이름에 맞게 반환형 지정
    
    // 파라미터 바인딩 정보를 담는 result 참조변수 이름과 내용을 출력하는 println 메서드 작성
    
    // 1. 유효성 검사
    // if문 사용, isValid 메서드를 호출하여 false 반환 -> 에러 페이지 반환

    // @ModelAttribute 사용으로 아래 2개 과정 생략
    // 2. 사용자에게 입력받은 날짜에 맞는 요일을 처리
    // 3. Model에 작업 결과 저장

    // 4. 작업 결과를 리턴할 페이지의 이름은 yoil로 함

  // char를 반환하는 getYoil 메서드 선언, 파라미터는 date 참조변수
  // "yoil"을 키로 하여 Model 객체에 메서드 결과를 저장하는 애너테이션 추가
    // 리턴문에서 getYoil 메서드를 호출 시 date 참조변수에서 년도, 월, 일을 구하여 파라미터로 넘겨줌

  // char형을 반환하는 getYoil 메서드 선언, 파라미터는 int year, month, day로 함
    // Calendar 객체의 인스턴스를 얻어 cal 참조변수에 저장
    // Calendar를 사용자가 입력한 날짜로 초기화
    // Calendar의 Static Field를 통해 사용자가 입력한 날짜가 무슨 요일인지 나타내는 값을 dayOfWeek 변수에 저장
    // 빈 문자열과 요일 문자열의 조합으로 몇 번째 요일인지를 1글자로 리턴

  // boolean을 반환하는 isValid 메서드 선언, 매개변수로는 사용자 입력값을 갖고 있는 date 참조변수
  // 입력값이 날짜 범위에 있는지 확인하는 검증 로직 간단하게 작성
}