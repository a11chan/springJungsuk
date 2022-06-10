package com.fastcampus.ch2;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
  
  @InitBinder
  public void toDate(WebDataBinder binder) {
    ConversionService conversionService = binder.getConversionService();
//    System.out.println("ConversionService = "+conversionService);
//    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//    binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
    binder.registerCustomEditor(String[].class, "hobby", new StringArrayPropertyEditor("#"));
//    binder.setValidator(new UserValidator()); // UserValidator를 WebDataBinder의 로컬 Validator로 등록, save 메서드의 검증대상 파라미터 앞에 @Valid 추가
//    binder.addValidators(new UserValidator());
    List<Validator> validatorList = binder.getValidators();
    System.out.println("validatorList = " + validatorList);
  }
  
  @RequestMapping(value="/register/add", method={RequestMethod.GET, RequestMethod.POST})
  public String register() {
    return "registerForm";
  }
//  -> 회원가입화면만 보여주는 역할 -> servlet-context.xml에서 <view-controller>로 처리 가능

//  @RequestMapping(value="/register/save", method={RequestMethod.GET, RequestMethod.POST}) -> 두 방식 가능 == 기본값
  @PostMapping("/register/save") // @PostMapping은 스프링 4.3부터 지원 -> pom.xml 수정필
  public String save(@Valid User user, BindingResult result, Model model) throws Exception {
    System.out.println("result="+result);
    System.out.println("user="+user);
    
    // 수동 검증 - Validator를 직접 생성, 호출
//    UserValidator userValidator = new UserValidator();
//    userValidator.validate(user, result); // BindingResult는 Errors의 자손
    
    // User 객체를 검증한 결과 에러가 있으면, registerForm을 통해 에러 표시
    if(result.hasErrors()) {
      return "registerForm";
    }
    
    // 1. 유효성 검사
//    if (!isValid(user)) {
//      String msg = URLEncoder.encode("id를 잘못 입력하셨습니다.", "utf-8"); // 컨트롤러에서 URL 리턴 시 브라우저에서 인코딩 불가 -> msg를 넘겨 받는 view
//                                                                  // 파일에서 사용자가 직접 디코딩도 같이 실행필
//      model.addAttribute("msg", msg); // model은 /register/save 에서만 사용가능하지만 스프링이 redirect 해줌
//
//      return "forward:/register/add"; // <view-controller>로 정의한 회원가입 화면
//    return "redirect:/register/add?msg="+msg; //URL 재작성(re-writing)
//    }
    // 2. DB에 정보 저장
    return "registerInfo";
  }

  private boolean isValid(User user) {
    return true;
  }
}
