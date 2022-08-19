package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fastcampus.ch2.MyDate;

@Controller
public class YoilTellerMVC4_Recall {
  
  @ExceptionHandler(Exception.class)
  public String catcher(Exception ex) {
    ex.printStackTrace();
    return "yoilError";
  }
  // http://localhost/ch2//getYoilMVC4_Recall?year=2022&month=08&day=17
  @RequestMapping("/getYoilMVC4_Recall")
  public String main(MyDate2 myDate2, Model model) throws IOException {
    
    // 1. 유효성 검사
    if(!isValid(myDate2))
      return "yoilError";
    
    // 2. 요일 계산
    char yoil = getYoil(myDate2);
    
    // 3. 계산한 결과를 model에 저장
    model.addAttribute("yoil",yoil);
    model.addAttribute("myDate", myDate2);
//    model.addAttribute("year",myDate2.getYear());
    
    // 4. 작업 결과를 보여줄 view의 이름을 반환
    return "yoil2_Recall";
  }

  private char getYoil(MyDate2 myDate2) {
    return getYoil(myDate2.getYear(),myDate2.getMonth(),myDate2.getDay());
  }

  private char getYoil(int year, int month, int day) {
    Calendar cal = Calendar.getInstance();
    cal.set(year, month -1, day);
    int weekNum = cal.get(Calendar.DAY_OF_WEEK);
    return " 일월화수목금토".charAt(weekNum);
  }
  
  private boolean isValid(MyDate2 myDate2) {
    if(myDate2.getYear() < 0 || myDate2.getMonth() < 0 || myDate2.getDay() <0)
      return false;
    return myDate2.getMonth() < 13 && myDate2.getDay() <= 31;
  }
}
