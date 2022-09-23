package com.fastcampus.ch2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class SetterCall_1 {
  public static void main(String[] args) throws Exception{
 // MyDate인스턴스를 생성하고, map의 값으로 초기화하는 로직 구현
    
    //Map<String, String>에 year, month, day를 키로 하는 임의의 값을
    //저장
    Map<String, String> map = new HashMap<>();
    map.put("year", "2021");
    map.put("month", "10");
    map.put("day", "1");
    
    //Class<?> type 에 MyDate 클래스의 정보를 저장
    Class<?> type = Class.forName("com.fastcampus.ch2.MyDate");

    // dataBind static 메서드를 호출하여 그 결과를 Object obj에 저장
    Object obj = dataBind(map, type);
    // println으로 출력 -> obj=[year=2021, month=10, day=1]
    System.out.println("obj = "+obj); 
  } // main

  // MyDate인스턴스를 생성하고, map의 값으로 초기화하는 static 메서드 정의
  private static Object dataBind(Map<String, String> map, Class<?> clazz) throws Exception {
    // 1. MyDate인스턴스 생성
    Object obj = clazz.newInstance(); // deprecated method

    // 2. MyDate인스턴스의 setter를 호출해서, map의 값으로 MyDate를 초기화
    //   2-1. MyDate의 모든 iv를 돌면서 map에 있는지 찾는다.
    //   2-2. 찾으면, 찾은 값을 setter로 객체에 저장한다.
      // MyDate에 선언된 모든 필드를 Field[] ivArr 참조변수에 저장
    Field[] ivArr = clazz.getDeclaredFields();
    
    // for문으로 ivArr 배열의 길이만큼 반복
    for(int i=0;i<ivArr.length;i++) {
      // i번째 필드 이름을 가져와 String name에 저장
      String name = ivArr[i].getName();
      // i번째 필드 타입을 가져와 Class<?> type에 저장
      Class<?> type = ivArr[i].getType();
      
      // map에 같은 이름의 key가 있으면 가져와서 setter호출 
      Object value = map.get(name); // 못찾으면 value의 값은 null
      Method method = null;
      
      try {   // map에 iv와 일치하는 키가 있을 때만, setter를 호출
        if(value==null) continue;
        
        // setter의 정보 얻기(MyDate의 필드 이름과 타입 사용)
        method = clazz.getDeclaredMethod(getSetterName(name), type); 
        System.out.println("method = "+method);
        method.invoke(obj, convertTo(value, type)); // obj의 setter를 호출
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
    
    System.out.println(Arrays.toString(ivArr));
    
    return obj;
  }

  private static Object convertTo(Object value, Class<?> type) {
    // value의 타입과 type의 타입이 같으면 그대로 반환
    if(value==null || type==null || type.isInstance(value))
      return value;
    
    // value의 타입과 type이 다르면, 변환해서 반환
    if(String.class.isInstance(value) && type==int.class) // String -> int
      return Integer.valueOf(""+value);

    return value;
  }

  // iv의 이름으로 setter의 이름을 만들어서 반환하는 메서드("day" -> "setDay")
  private static String getSetterName(String name) {
//    return "set"+name.substring(0,1).toUpperCase()+name.substring(1);
    return "set" + StringUtils.capitalize(name); // org.springframework.util.StringUtils
  }
}

/*
[실행결과]
method=public void com.fastcampus.ch2.MyDate.setYear(int)
method=public void com.fastcampus.ch2.MyDate.setMonth(int)
method=public void com.fastcampus.ch2.MyDate.setDay(int)
[private int com.fastcampus.ch2.MyDate.year, private int com.fastcampus.ch2.MyDate.month, private int com.fastcampus.ch2.MyDate.day]
obj=[year=2021, month=10, day=1]
 */