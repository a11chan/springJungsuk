package com.fastcampus.ch2;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YoilTellerMVC5_1 {
  
  //v 현재 클래스에서 발생하는 예외 처리 핸들러 애너테이션 등록,
  //v 대상 예외 종류는 Exception.class
  //v 메서드 이름 catcher, 매개변수: 처리 대상 예외
  //v , 본문: 에러내역 출력, 리턴: 에러페이지
  @ExceptionHandler(Exception.class)
  public String catcher(Exception ex) {
    ex.printStackTrace();
    return "yoilError";
  }
  
  // http://localhost/ch2/getYoilMVC5_1?year=2022&month=09&day=19
  //v URL 매핑 경로: getYoilMVC4
  //v 사용자 요청을 처리하는 메서드 선언 메서드 이름: main
  //v , 반환형: String, IOException 던질 것
  //v year, month, day로 들어오는 사용자 요청을 MyDate 객체로 처리할 것
  @RequestMapping("/getYoilMVC5_1")
  public String main(MyDate myDate, Model model) {
    
  //v 1. 유효성 검사
    // 만약 isValid 메서드의 결과가 유효하지 않으면 에러 페이지 반환,
    //파라미터는 MyDate 객체
    // 간단하게 날자 유효범위가 아니면 false, 유효범위이면 리턴에
    //true를 반환하도록 정의
    if(!isValid(myDate))
      return "yoilError";
    
    //v 2. 요일 계산
    //v getYoil메서드를 통해 사용자가 입력한 날짜에 맞는 요일을 얻어서
    //v char yoil 에 저장
    //v MyDate 객체에서 년,월,일 값을 얻어와 바로 요일 계산 메서드에 전달
//    char yoil = getYoil(myDate.getYear(),myDate.getMonth(),myDate.getDay());
    
  // 3. 계산한 결과를 model에 저장
    // view 페이지에 넘겨줄 날짜 정보를 Model 객체에 저장
    // 사용자가 입력한 날짜 date는 myDate란 이름으로,
    // 날짜에 맞는 요일은 yoil 이라는 키로 전달
//    model.addAttribute("myDate",myDate);
//    model.addAttribute("yoil", yoil);
    
    
  // 4. 작업 결과를 보여줄 view의 이름을 반환 -> yoil
    return "yoil";
  }
  
  // 요일 계산 시 Calendar 객체 사용
  // 캘린더의 날짜는 사용자가 입력한 값으로 초기화
  // 그 날짜가 일주일 중에 몇 번째인지 Calendar 필드에서 얻기
  // 요일 문자열과 charAt 메서드를 이용하여 char 형으로 결과 리턴
  private @ModelAttribute("yoil") char getYoil(int year, int month, int day) {
    Calendar cal = Calendar.getInstance();
    cal.set(year, month -1, day);
    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    
    return " 일월화수목금토".charAt(dayOfWeek);
  }

  private boolean isValid(MyDate myDate) {
    return isValid(myDate.getYear(),myDate.getMonth(),myDate.getDay());
  }

  private boolean isValid(int year, int month, int day) {
    if(year <= 0 || month <= 0 || day <= 0 )
      return false;
    return month <= 12 && day <= 31;
  }
}
