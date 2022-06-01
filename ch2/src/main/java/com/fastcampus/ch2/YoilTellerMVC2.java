package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class YoilTellerMVC2 {

  @RequestMapping("/getYoilMVC") //http://localhost:8080/ch2/getYoilMVC?year=2022&month=05&day=31
//  public static void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
  public ModelAndView main(int year,int month, int day) throws IOException {
    
    // Model과 View를 반환하는 ModelAndView 사용, 잘 쓰진 않음
    ModelAndView mv = new ModelAndView();
    
    // 1. 유효성 검사
//    if(!isValid(year, month, day)) 
//       return "yoilError";
    
    // 2. 요일 계산
    char yoil = getYoil(year, month, day);
    
    // 3. 계산한 결과를 model에 저장
    mv.addObject("year", year);
    mv.addObject("month", month);
    mv.addObject("day", day);
    mv.addObject("yoil", yoil);
    
    // 4. 결과를 보여줄 view 지정
    mv.setViewName("yoil");
    
    return mv;
  }

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
