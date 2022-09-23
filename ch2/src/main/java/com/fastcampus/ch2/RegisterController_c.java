//package com.fastcampus.ch2;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//// 원격 프로그램으로 등록 에너테이션
//@Controller
//public class RegisterController_c {
//  
//  //Get 요청 URL 매핑 "/register_1/add" 에너테이션
//	@GetMapping("/register_1/add")
//	// 회원가입 화면(registerForm_1)을 보여주는 메서드
//	public String register() {
//		return "registerForm_1";
//	}
//	
//	//Post 요청 매핑 "/register_1/save" 에너테이션
//	@PostMapping("/register_1/save")
//	// 회원가입 처리 후 회원정보 출력 메서드
//	// 메서드 이름은 save "registerInfo"라는 뷰 이름을 반환
//	// User 객체에 대한 정보를 view 단 <form>에서 받아옴
//	  // 파라미터 영역에서 Model 객체에 "user"라는 키로 회원가입 정보 저장할 것
//	public String save(@ModelAttribute("user") User user, Model m) throws Exception {
//		// user 객체 내용이 유효한지 유효성 검사 함수 호출하여 판단
//	  if(!isValid(user)) {
//	    // 유효하지 않으면 view단으로 보낼 String msg 저장
//	    // 내용은 "id를 잘못 입력하셨습니다.", 인코딩은 UTF-8
//			String msg = URLEncoder.encode("id를 잘못 입력하셨습니다.", "utf-8");
//			
//			//model 객체에 "msg"를 키 값으로해서 메시지 전달
//			m.addAttribute("msg", msg);
//			
//			//redirect로 회원가입 페이지 요청
//			return "redirect:/register_1/add";
//		}
//		
//	  //회원가입 결과 페이지(registerInfo) 뷰 이름 리턴
//		return "registerInfo";
//	}
//
//	// boolean을 반환하는 User 객체 유효성 검사 메서드 선언부
//	// 클래스 내 호출용이므로 private로 선언
//	private boolean isValid(User user) {
//		return false;
//	}
//	
//}