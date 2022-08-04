package com.fastcampus.ch2;

//로컬 프로그램을 원격 호출 가능한 프로그램으로 등록하는 애너테이션
public class YoilTeller2_Comment { // 클래스 이름은 YoilTeller_Recall로 할 것
  
  // 브라우저의 URL 요청에 대한 매핑 이름은 getYoil1
  public static void main(){
    // 1. 입력 // request 객체에 있는 년도, 월, 일자 변수를 받아서 String year, month, day 변수에 저장

    // 2. 작업 // String으로 저장한 날짜 정보를 int 변수에 저장
    
    // Calendar 객체 생성
    // set메서드를 통해 사용자가 요청한 날짜를 저장
    // get 메서드와 DAY_OF_WEEK 메서드를 통해 무슨 요일인지 반환받을 것
    // 반환받은 숫자를 요일로 변환받을 것
    
    // 3. 출력
    // Response 객체를 통해 브라우저로 응답하기 위해 contentType을 "text/html"로 지정
    // 문자 인코딩을 utf-8로 설정
    // response객체에서 브라우저로의 출력 스트림을 얻는다. getWriter(), PrintWriter 사용
    // 사용자가 입력한 날짜를 출력 year년 month월 day일은
    // yoil 요일입니다.
    
  }

}
