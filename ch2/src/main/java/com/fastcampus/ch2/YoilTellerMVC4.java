package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fastcampus.ch2.MyDate;

@Controller
public class YoilTellerMVC4 {
  @ExceptionHandler(Exception.class)
  public String catcher(Exception ex) {
    ex.printStackTrace();
    return "yoilError";
  }

  // http://localhost/ch2/getYoilMVC4
  @RequestMapping("/getYoilMVC4")
//  public static void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
  public String main(MyDate date, Model model) throws IOException {
    
    // 1. 유효성 검사
    if(!isValid(date)) 
       return "yoilError";
    
    // 2. 요일 계산
    char yoil = getYoil(date);
    
    // 3. 계산한 결과를 model에 저장
    model.addAttribute("myDate", date);
    model.addAttribute("yoil", yoil);
    
    // 4. 작업 결과를 보여줄 view의 이름을 반환
    return "yoil";
  }

  private char getYoil(MyDate date) {
    return getYoil(date.getYear(), date.getMonth(), date.getDay());
  }

  private boolean isValid(MyDate date) {
    return isValid(date.getYear(), date.getMonth(), date.getDay());
  }

  private char getYoil(int year, int month, int day) {
    Calendar cal = Calendar.getInstance();
    cal.set(year, month -1, day);
    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    return " 일월화수목금토".charAt(dayOfWeek);
  }
  
  private boolean isValid(int year, int month, int day) {
    if(year == -1 || month == -1 || day == -1)
      return false;
    return (1<=month && month<=12) && (1<=day && day<=31); // 간단히 체크
  }
}
