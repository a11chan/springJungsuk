package com.fastcampus.ch2;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
  @RequestMapping(value="/register/add", method={RequestMethod.GET, RequestMethod.POST})
  public String register() {
    return "registerForm";
  }
//  -> 회원가입화면만 보여주는 역할 -> servlet-context.xml에서 <view-controller>로 처리 가능

//  @RequestMapping(value="/register/save", method={RequestMethod.GET, RequestMethod.POST}) -> 두 방식 가능 == 기본값
  @PostMapping("/register/save") // 스프링 4.3부터 지원 -> pom.xml 수정필
  public String save(User user, Model model) throws Exception {
    // 1. 유효성 검사
    if (!isValid(user)) {
      String msg = URLEncoder.encode("id를 잘못 입력하셨습니다.", "utf-8"); // 컨트롤러에서 URL 리턴 시 브라우저에서 인코딩 불가 -> msg를 넘겨 받는 view
                                                                  // 파일에서 사용자가 직접 디코딩도 같이 실행필
      model.addAttribute("msg", msg); // model은 /register/save 에서만 사용가능하지만 스프링이 redirect 해줌

      return "forward:/register/add"; // <view-controller>로 정의한 회원가입 화면
//    return "redirect:/register/add?msg="+msg; //URL 재작성(re-writing)
    }
    // 2. DB에 정보 저장
    return "registerInfo";
  }

  private boolean isValid(User user) {
    return false;
  }
}
