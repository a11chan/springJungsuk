package com.fastcampus.ch2;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class YoilTellerMVC6_Recall {
  
  @ExceptionHandler(Exception.class)
  public String catcher(Exception ex, BindingResult result) {
    ex.printStackTrace();
    System.out.println("result = "+result);
    System.out.println("error = "+result.getFieldError());
    return "yoilError";
  }
  
  // localhost/ch2/getYoilMVC6_Recall?year=2022&month=08&day=18
  @RequestMapping("/getYoilMVC6_Recall")
  public String main(MyDate myDate, BindingResult result, Model model) {
    System.out.println("result = "+result);
    
    if(!isValid(myDate))
      return "yoilError";
    
    getYoil(myDate);
    
    return "yoil2";
  }

  private @ModelAttribute("yoil") char getYoil(MyDate myDate) {
    return getYoil(myDate.getYear(),myDate.getMonth(),myDate.getDay());
  }

  private char getYoil(int year, int month, int day) {
    Calendar cal = Calendar.getInstance();
    cal.set(year,month -1,day);
    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    return " 일월화수목금토".charAt(dayOfWeek);
  }

  private boolean isValid(MyDate myDate) {
    return isValid(myDate.getYear(),myDate.getMonth(),myDate.getDay());
  }

  private boolean isValid(int year, int month, int day) {
    if(year < 1 || month < 1 || day < 1)
      return false;
    return month <= 12 && day <= 31;
  }
} 