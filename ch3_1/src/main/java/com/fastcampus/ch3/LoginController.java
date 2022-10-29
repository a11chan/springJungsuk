package com.fastcampus.ch3;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 원격 프로그램으로 등록
// 요청 주소 1계층 이름은 login으로 함, GET, POST 둘 다 가능한 애너테이션으로 할 것
@Controller
@RequestMapping("/login")
public class LoginController {

  //UserDao 자동 주입 처리
  @Autowired
  UserDao userDao;
  
  // "/login" 경로의 GET요청을 처리하는 애너테이션
  // View 이름으로 "loginForm"을 반환하는 loginForm 메서드 선언
  @GetMapping("/login")
  public String loginForm() {
    return "loginForm";
  }

  // "/logout" 경로의 GET 요청을 처리하는 애너테이션
  // 프로젝트 루트경로를 redirect로 반환하는 public 접근제어자 logout 메서드 선언
  @GetMapping("/logout")
  public String logout(HttpSession session) {
    //session을 종료
    session.invalidate();
    return "redirect:/";
  }
  
  // "/login" 경로의 POST 요청을 처리하는 애너테이션
  // 로그인 전에 가고자 했던 View를 redirect문으로 return 하는 login 메서드 선언
  @PostMapping("/login")
  public String login (String toURL, String id, String pwd, boolean rememberId 
      , HttpServletRequest request, HttpServletResponse response) throws Exception {
    // 1. 로그인 View에서 받은 id와 pwd를 loginCheck메서드로 넘겨서 true false 판단하는 if문 선언
      // 일치하지 않으면 로그인 view 페이지로 redirect 요청하되
      // "id 또는 pwd가 일치하지 않습니다." 라는 메시지를 쿼리 스트링으로 전달
      // 인코딩은 UTF-8로 할 것
    if(!loginCheck(id, pwd)) {
      String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.","UTF-8");
      
      return "redirect:/login/login?msg="+msg;
    }
    
    // 2. id와 pwd가 일치하면,
      // 세션 객체를 얻어오기
      HttpSession session  = request.getSession();
      // 세션 객체에 id를 저장
      session.setAttribute("id", id);
      
    // 3.1 로그인 view에서 아이디를 기억하기로 했으면
      if(rememberId) {
        // id를 Key-Value로 하는 쿠키를 생성
        Cookie cookie = new Cookie("id",id);
        // 응답에 쿠키를 저장
        response.addCookie(cookie);
      } else {
        // 3.2 로그인 아이디를 기억하지 않기로 했으면
        // 쿠키를 생성
        Cookie cookie = new Cookie("id",id);
        // 쿠키의 유효시간을 0으로 설정(쿠키 삭제)
        cookie.setMaxAge(0);
        // 응답에 쿠키를 저장
        response.addCookie(cookie);
      }
    // 4. 홈으로 이동
      // 삼항연산자로 toURL이 null이거나 빈문자열이면 홈페이지를("/")
      // 존재하면 toURL 그대로를 toURL 변수에 저장
      toURL = toURL == null || toURL.equals("") ? "/" : toURL;
    // 5. redirect문을 리턴하되 toURL붙여서 전달
      return "redirect:"+toURL;
  }

  // 문자열 id, pwd를 받아 논리형을 반환하는 loginCheck 메서드 선언
  private boolean loginCheck(String id, String pwd) {
    // userDao 객체에서 id를 통해 사용자 정보를 얻어 User형 참조변수에 저장
    User user = userDao.selectUser(id);
    // 참조변수가 null이면 false 반환
    if(user==null) return false;
    // user형 참조변수에서 pwd를 얻어와 로그인 요청 시 받은 pwd와 같은지 비교하여 참/거짓을 return문에 반환
    return user.getPwd().equals(pwd);
  }
    

}