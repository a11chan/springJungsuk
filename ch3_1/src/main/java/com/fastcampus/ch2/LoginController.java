package com.fastcampus.ch2;

import java.io.UnsupportedEncodingException;
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
  public String login(
      String id
    , String pwd
    , boolean rememberId
    , String toURL
    , HttpServletRequest request
    , HttpServletResponse response) throws Exception {
    
    if(!loginCheck(id,pwd)) {
      String msg = URLEncoder.encode("아이디와 비밀번호가 일치하지 않습니다.","UTF-8");
      return "redirect:/login/login?msg="+msg;
    }
    
    HttpSession session = request.getSession();
    session.setAttribute("id",id);
    
    if(rememberId) {
      Cookie cookie = new Cookie("id", id);
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("id", id);
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }
    
    toURL = toURL == null || toURL.equals("") ? "/" : toURL;
    
    return "redirect:"+toURL;
  }

  private boolean loginCheck(String id, String pwd) {
    return "asdf".equals(id) && "1234".equals(pwd);
  }
  
}