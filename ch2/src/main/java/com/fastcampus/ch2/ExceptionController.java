package com.fastcampus.ch2;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ExceptionController {
  
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 200 -> 500
  @ExceptionHandler(Exception.class)
  public String catcher(Exception ex, Model m) {
    System.out.println("m = "+m);
//    m.addAttribute("ex", ex);
    return "error";
  }

  @ExceptionHandler({ NullPointerException.class, FileNotFoundException.class })
  public String catcher2(Exception ex, Model m) {
    m.addAttribute("ex", ex);
    return "error";
  }
  
  @RequestMapping("/ex")
  public String main(Model m) throws Exception {
    m.addAttribute("msg", "message from ExceptionController.main()");
    System.out.println("catcher() in ExceptionController");
      throw new Exception("예외가 발생했습니다.");
    }
  
  @RequestMapping("/ex2")
  public String main2() throws Exception {
      throw new FileNotFoundException("예외가 발생했습니다.");
      // ExceptionHandler에 정의되어 있지 않으면 예외 클래스의 최고조상 Exception으로 처리
    }
}
