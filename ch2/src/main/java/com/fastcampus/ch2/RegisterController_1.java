package com.fastcampus.ch2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController_1 {
  
//  @GetMapping("/register_1/add")
//	public String register() {
//	  return "registerForm_1";
//	}
  
  @PostMapping("/register_1/save")
  public String save(@ModelAttribute("user")User user, Model m) throws Exception {
    if(!isValid(user)) {
      String msg = URLEncoder.encode("id를 잘못 입력하셨습니다.","UTF-8");
      m.addAttribute("msg",msg);
      return "redirect:/register_1/add";
    }
    
    return "registerInfo";
  }

  private boolean isValid(User user) {
    return true;
  }
	
}