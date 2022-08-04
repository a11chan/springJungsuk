package com.fastcampus.ch2;

import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//로컬 프로그램을 원격 호출 가능한 프로그램으로 등록하는 애너테이션
@Controller
public class YoilTeller2_Recall { // 클래스 이름은 YoilTeller_Recall로 할 것
  
  // 브라우저의 URL 요청에 대한 매핑 이름은 getYoil1
  @RequestMapping("/getYoilRecall") // //http://localhost/ch2/getYoilRecall?year=2022&month=08&day=02  
  public static void main(HttpServletRequest request, HttpServletResponse response) throws Exception{
    // 1. 입력 // request 객체에 있는 년도, 월, 일자 변수를 받아서 String year, month, day 변수에 저장
    String year = request.getParameter("year");
    String month = request.getParameter("month");
    String day = request.getParameter("day");
    
    // 2. 작업 // String으로 저장한 날짜 정보를 int 변수에 저장
    int yyyy = Integer.parseInt(year);
    int mm = Integer.parseInt(month);
    int dd = Integer.parseInt(day);

    // Calendar 객체 생성
    Calendar cal = Calendar.getInstance();
    // set메서드를 통해 사용자가 요청한 날짜를 cal 개체에 저장
    cal.set(yyyy,mm-1,dd);
    // get 메서드와 DAY_OF_WEEK static 메서드를 통해 무슨 요일인지 int 변수로 반환받을 것
    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    // 반환받은 숫자를 요일로 변환받을 것
    char yoil = " 일월화수목금토".charAt(dayOfWeek);
    
    // 3. 출력
    // Response 객체를 통해 브라우저로 응답하기 위해 contentType을 "지정
    response.setContentType("text/html");
    // 문자 인코딩을 utf-8로 설정
    response.setCharacterEncoding("utf-8");
    // response객체에서 브라우저로의 출력 스트림을 얻는다. getWriter(), PrintWriter 사용
    PrintWriter out = response.getWriter();
    // 사용자가 입력한 날짜를 출력 year년 month월 day일은
    out.println("입력하신 날짜 "+yyyy+"년 "+mm+"월 "+dd+"일은 ");
    // yoil 요일입니다.
    out.println(yoil+"요일입니다.");
    
  }

}
