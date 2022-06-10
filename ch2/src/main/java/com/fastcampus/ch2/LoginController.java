package com.fastcampus.ch2;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
  
  @GetMapping("/login")
  public String loginForm() {
    return "loginForm";
  }
  
  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }
  
  @PostMapping("/login")
  public String login(@CookieValue("id") String cookieId, String id, String pwd, boolean rememberId, String toURL
      , HttpServletRequest request, HttpServletResponse response) throws Exception {
    // 1. id, pw 확인
    if(!loginCheck(id, pwd)) {
      // 일치하지 않으면 loginForm으로 이동
      String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "UTF-8");
      
      return "redirect:/login/login?msg="+msg;
    }
    // 2. id, pw 일치시
    // 새션 객체를 얻어와 id 저장
    HttpSession session = request.getSession();
    session.setAttribute("id",id);
    
    if(rememberId) {
      // 쿠키 생성 및 적용
      Cookie cookie = new Cookie("id",id);
      response.addCookie(cookie);
    } else {
      // 쿠키 삭제 및 적용
      Cookie cookie = new Cookie("id",id);
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }
    
    // 3. 홈으로 이동
    toURL = toURL == null || toURL.equals("") ? "/" : toURL;
    
    return "redirect:"+toURL;
  }
  private boolean loginCheck(String id, String pwd) {
    return "asdf".equals(id) && "1234".equals(pwd);
  }
}
