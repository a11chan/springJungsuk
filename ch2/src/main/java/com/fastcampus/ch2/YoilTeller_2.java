package com.fastcampus.ch2;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 스프링이 관리하는 프로그램으로 등록하는 애너테이션
@Controller
public class YoilTeller_2 {

  // 예외 핸들러 애너테이션, 예외 종류는 Exception.class로 할 것
  // 예외 처리할 메서드 이름은 cather, 파라미터는 애너테이션 참고하/여 설정
  // 파라미터의 참조변수를 통해 stackTrace 출력하도록 메서드 호출
  // 에러 페이지 리턴할 것, view 파일 이름은 기 작성된 yoilError
  @ExceptionHandler(Exception.class)
  public String catcher(Exception ex) {
    ex.printStackTrace();
    return "yoilError";
  }
  
  // http://localhost/ch2/getYoil_2?year=2022&month=09&day=15
  // 사용자 요청을 처리할 메서드 선언, 이름은 main, URL 매핑 이름은 클래스 이름과 같게
  // IOException으로 예외 처리할 것
  // 메서드 파라미터로 정수 년도, 월, 일자를 필수입력으로 받을 것
  // 사용자 요청 처리 결과를 view단으로 넘겨서 EL 표현식으로 나타내기 위한 객체를 메서드 파라미터에 추가할 것
  @RequestMapping("/getYoil_2")
  public String main(@RequestParam(required = true) int year, @RequestParam(required = true) int month,
      @RequestParam(required = true) int day, Model m) {

    // 1. 유효성 검사 메서드 호출
    // 파라미터가 유효하지 않으면 에러페이지를 리턴할 것
    if (!isValid(year, month, day)) {
      return "yoilError";
    }

    // 2. 요일 계산 메서드 호출
    // getYoil 메서드를 호출하여 처리된 결과를 char yoil 변수에 담을 것
    char yoil = getYoil(year, month, day);
    
    // 3. 계산한 결과를 view 단에 전달하기 위한 참조변수에 저장
    // Key값은 year, month, day, yoil로 할 것
    // 처리 결과를 전달할 view 페이지 이름은 yoil로 할 것
    m.addAttribute("year",year);
    m.addAttribute("month",month);
    m.addAttribute("day",day);
    m.addAttribute("yoil",yoil);
    
    return "yoil";
  }

  // 날짜에 맞는 요일을 반환하는 메서드
  // 메서드 이름은 getYoil, 파라미터 이름은 사용자 요청 받을 때 정의했던 것과 같게
  // 캘린더 객체 생성
  // 사용자가 입력한 날짜로 캘린더 날짜 설정
  // 캘린더 객체에서 요일 정보를 얻어와 int 변수에 저장
  // 요일 문자열 리터럴을 이용해 사용자가 입력한 날짜의 요일을 반환할 것
  private char getYoil(int year, int month, int day) {
    Calendar cal = Calendar.getInstance();
    cal.set(year, month -1, day);
    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    return " 일월화수목금토".charAt(dayOfWeek);
  }

  // 유효성 검사 메서드 선언, 이름은 isValid로 할 것
  // 유효성 검사 메서드 본문
  // 반환형은 true or false
  // 년도, 월, 일자가 날짜 범위 내에 있는지 검증할 것
  private boolean isValid(int year, int month, int day) {
    if (year <= 0 || month <= 0 || day <= 0)
      return false;
    return year > 0 && month <= 12 && day <= 31;
  }

}