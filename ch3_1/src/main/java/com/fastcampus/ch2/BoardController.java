package com.fastcampus.ch2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/board")
@Controller
public class BoardController {
  
  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }
  
  @GetMapping("/list")
  public String list(HttpServletRequest request) {
    if(!loginCheck(request)) {
      return "redirect:/login/login?toURL="+request.getRequestURL();
    }
    
    return "boardList";
  }

  private boolean loginCheck(HttpServletRequest request) {
    HttpSession session = request.getSession();
    return session.getAttribute("id") != null;
  }
}