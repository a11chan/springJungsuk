package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class YoilTellerMVC2_Recall {
  @ExceptionHandler(Exception.class)
  private String catcher(Exception ex) {
      ex.printStackTrace();
    return "yoilError";
  }
  // http://localhost/ch2/getYoilMVCRecall?year=2022&month=08&day=05
  @RequestMapping("/getYoilMVCRecall")
  private String main(
        @RequestParam(required=true) int year
      , @RequestParam(required=true) int month
      , @RequestParam(required=true) int day
      , Model model) throws IOException {
      
      if(!isValid(year,month,day))
        return "yoilError";
     
      char yoil = (char) getYoil(year,month,day);
      
      model.addAttribute("year",year);
      model.addAttribute("month",month);
      model.addAttribute("day",day);
      model.addAttribute("yoil",yoil);
      
      return "yoil_recall";
    }

  private int getYoil(int year, int month, int day) {
    Calendar cal = Calendar.getInstance();
    cal.set(year, month-1, day);
    int yoil = cal.get(Calendar.DAY_OF_WEEK);
    return " 일월화수목금토".charAt(yoil);
  }

  private boolean isValid(int year, int month, int day) {
    if (year < 0 || month < 0 || day < 0) {
      return false;
    } else if (month > 12 || day > 31) {
      return false;
    } else {
      return true;
    }
  }
  
}
