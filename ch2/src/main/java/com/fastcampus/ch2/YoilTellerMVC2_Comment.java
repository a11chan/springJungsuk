package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 스프리잉 관리하는 프로그램으로 등록하는 애너테이션
@Controller
public class YoilTellerMVC2_Comment {
  //예외 핸들러 애너테이션, 예외 종류는 Exception.class로 할 것
  @ExceptionHandler(Exception.class)
  //예외 처리할 메서드 이름은 cather, 파라미터는 애너테이션 참고하여 설정
  public String catcher(Exception ex) {
    // 파라미터의 참조변수를 통해 stackTrace 출력하도록 메서드 호출
    ex.printStackTrace();
    // 에러 페이지 리턴할 것, view 파일 이름은 기 작성된 yoilError
    return "yoilError";
  }
  
  // 사용자 요청을 처리할 메서드 선언, 이름은 main, URL 매핑 이름은 "getYoilMVCRecall"
  // 메서드 파라미터로 정수 년도, 월, 일자를 필수입력으로 받을 것
  // 사용자 요청 처리 결과를 view단으로 넘겨서 EL 표현식으로 나타내기 위한 객체를 메서드 파라미터에 추가할 것
  @RequestMapping("/getYoilMVC2")
  public String main(
        @RequestParam(required=true) int year
      , @RequestParam(required=true) int month
      , @RequestParam(required=true) int day
      , Model model) throws IOException {
    
    // 1. 유효성 검사 메서드 호출
    // 유효성 검사 메서드 이름은 isValid로 할 것
    // 파라미터가 유효하지 않으면 에러페이지를 리턴할 것
    if(!isValid(year, month, day)) 
       return "yoilError";
    
    // 2. 요일 계산 메서드 호출
    // getYoil 메서드를 호출하여 처리된 결과를 char yoil 변수에 담을 것
    char yoil = getYoil(year, month, day);
    
    // 3. 계산한 결과를 view 단에 전달하기 위한 참조변수에 저장
    // Key값은 year, month, day, yoil로 할 것
    model.addAttribute("year", year);
    model.addAttribute("month", month);
    model.addAttribute("day", day);
    model.addAttribute("yoil", yoil);
    // 처리 결과를 전달할 view 페이지 이름은 yoil로 할 것
    return "yoil";
  }
  
  // 유효성 검사 메서드 본문
  // 반환형은 true or false
  // 년도, 월, 일자가 날짜 범위 내에 있는지 검증할 것
  private boolean isValid(int year, int month, int day) {
    
    return true;
  }

  private char getYoil(int year, int month, int day) {
    Calendar cal = Calendar.getInstance();
    cal.set(year, month -1, day);
    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    return " 일월화수목금토".charAt(dayOfWeek);
  }

}
