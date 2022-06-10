package com.fastcampus.ch2;

import java.io.FileNotFoundException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.fastcampus.ch3")
//@ControllerAdvice
public class GlobalCatcher {

  @ExceptionHandler(Exception.class)
  public String catcher(Exception ex, Model m) {
    m.addAttribute("ex", ex);
    return "error";
  }

  @ExceptionHandler({ NullPointerException.class, FileNotFoundException.class })
  public String catcher2(Exception ex, Model m) {
    m.addAttribute("ex", ex);
    return "error";
  }
}
