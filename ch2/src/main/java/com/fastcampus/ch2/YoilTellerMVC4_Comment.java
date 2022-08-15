package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fastcampus.ch2.MyDate;

// 원격으로 호출 가능한 프로그램으로 등록
public class YoilTellerMVC4_Comment {
  
  // 현재 클래스에서 발생하는 예외 처리 핸들러 애너테이션 등록, 대상 예외 종류는 Exception.class
  // 메서드 이름 catcher, 매개변수: 처리 대상 예외, 본문: 에러내역 출력, 리턴: 에러페이지

  // URL 매핑 경로: getYoilMVC4
  // 사용자 요청을 처리하는 메서드 선언 메서드 이름: main, 반환형: String, IOException 던질 것,
  // year, month, day로 들어오는 사용자 요청을 MyDate 객체로 처리할 것
    
    // 1. 유효성 검사
    // 만약 isValid 메서드의 결과가 유효하지 않으면 에러 페이지 반환, 파라미터는 MyDate 객체
    // 간단하게 날자 유효범위가 아니면 false, 유효범위이면 리턴에 true를 반환하도록 정의
    
    // 2. 요일 계산
    // getYoil메서드를 통해 사용자가 입력한 날짜에 맞는 요일을 얻어서 char yoil 에 저장
    // MyDate 객체에서 년,월,일 값을 얻어와 바로 요일 계산 메서드에 전달
    // 요일 계산 시 Calendar 객체 사용
       // 캘린더의 날짜는 사용자가 입력한 값으로 초기화
       // 그 날짜가 일주일 중에 몇 번째인지 알아내어 요일 문자열을 이용하여 결과 리턴
    
    // 3. 계산한 결과를 model에 저장
    // view 페이지에 넘겨줄 날짜 정보를 Model 객체에 저장
    // 사용자가 입력한 날짜 date는 myDate란 이름으로, 날짜에 맞는 요일은 yoil 이라는 키로 전달
    
    // 4. 작업 결과를 보여줄 view의 이름을 반환 -> yoil2_recall
}
