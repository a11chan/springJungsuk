package com.fastcampus.ch2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
  
  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }
  
  @GetMapping("/list")
  public String list(HttpServletRequest request) {
    if(!loginCheck(request))
      return "redirect:/login/login?toURL="+request.getRequestURL();
      //login화면에 get방식으로 데이터 보내는 코드 추가
    
    return "boardList";
  }

  private boolean loginCheck(HttpServletRequest request) {
    //1. 세션을 얻어서
    HttpSession session = request.getSession();
    //2. 세션에 id가 있는지 확인, 있으면 true 반환
    return session.getAttribute("id")!=null;
  }
}
