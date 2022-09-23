package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// http://localhost/ch2/getYoil_1?year=2022&month=09&day=15
// 원격프로그램으로 등록하는 애너테이션
@Controller
public class YoilTeller_1 {

  //@RequestMapping 사용, 매핑 이름은 /getYoil_1
  // main 메서드의 파라미터에는 request, response 객체 사용
  @RequestMapping("/getYoil_1")
  public static void main(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    // 1. 입력
    // 쿼리스트링으로 년,월,일 정보를 받아 String으로 저장
    String year = request.getParameter("year");
    String month =  request.getParameter("month");
    String day = request.getParameter("day");
    
    // 2. 작업
    // 년,월,일 문자열을 int로 변환하여 변수에 저장
    int yyyy = Integer.parseInt(year);
    int mm = Integer.parseInt(month);
    int dd = Integer.parseInt(day);
    
    // Calendar 객체를 사용하여 요일을 char형으로 얻기
      // Calendar 객체 생성
      // Calendar 초기화
      // Calendar 객체에서 요일 정보 얻어와 int 변수에 저장
      // 숫자로 표현된 요일 정보를 문자로 변환
    Calendar cal = Calendar.getInstance();
    cal.set(yyyy, mm -1, dd);
    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    char yoil = " 일월화수목금토".charAt(dayOfWeek);
    
    // 3. 출력
      //response 참조변수를 통해 contentType을 "text/html"로 설정
      //response 참조변수를 통해 문자열 인코딩을 "UTF-8"로 설정
      //response 참조변수를 통해 출력 스트림을 얻기
      //출력 스트림과 println 메서드를 통해 "00년 00월 00일은 00요일입니다." 출력하기
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    out.println(yyyy+"년 "+mm+"월 "+dd+"일은");
    out.println(yoil+"요일입니다.");
  }
}