package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {
  
  int iv = 20;
  static String cv = "hello - private";
  
  @RequestMapping("/hello")
  private void main() {
    System.out.println("Hello");
    System.out.println("cv = "+cv);
    System.out.println("iv = "+iv);
  }

}
