package com.fastcampus.ch2;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

	public class UserValidator implements Validator {
		@Override
		public boolean supports(Class<?> clazz) {
// 결과가 false 이면 검증 불가
// return User.class.equals(clazz); // 검증하려는 객체가 User타입인지 확인
			return User.class.isAssignableFrom(clazz); // clazz가 User 또는 그 자손인지 확인
		}

		@Override
		public void validate(Object target, Errors errors) { 
			System.out.println("UserValidator.validate() is called");
			// 아래와 같이 확인 해야 하나 위의 supports 메서드에서 검증하므로 생략
			// if(!target instanceOf User) return;
			User user = (User)target; 
			
			String id = user.getId();
			String pwd = user.getPwd();
			
			//		if(id==null || "".equals(id.trim())) {
			//			errors.rejectValue("id", "required");
			//		}
			//		if(pwd==null || "".equals(pwd.trim())) {
			//			errors.rejectValue("pwd", "required");
			//		}
			// id, pwd 필드는 required 라는 메시지(에러코드)를 errors에 저장
			// 아래와 같음
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id",  "required");
//			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id",  "required", "필수 입력 항목입니다."); // 하드 코딩이라 지양, 다른 방법 추후 안내
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");
			// 비어있거나 공백이면 errors에 해당 필드명과 에러 메시지를 저장
			
			if(id==null || id.length() <  5 || id.length() > 12) {
//			errors.reject("invalidAccount"); // 객체 전체에 대한 에러 저장, 로그인 검증 시 활용(ID, PW)
				errors.rejectValue("id", "invalidLength");
			}
			if(pwd==null || pwd.length() <  5 || pwd.length() > 12) {
//			errors.reject("invalidAccount"); // 객체 전체에 대한 에러 저장, 로그인 검증 시 활용(ID, PW)
				errors.rejectValue("pwd", "invalidLength");
			}
		}
	}
