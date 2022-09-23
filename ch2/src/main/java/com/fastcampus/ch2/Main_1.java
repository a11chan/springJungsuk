package com.fastcampus.ch2;

import java.lang.reflect.Method;

public class Main_1 {

  public static void main(String[] args) throws Exception {
//  Hello hello = new Hello();
//  hello.main(); // private 라서 호출 불가
//  Reflection API를 사용 -> 클래스 정보를 얻고 다룰 수 있는 강력한 기능 제공
//  java.lang.reflect 패키지를 제공

//  Hello 클래스의 Class 객체(클래스의 정보를 담고 있는 객체)를 얻어온다.
    Class helloClass = Class.forName("com.fastcampus.ch2.Hello");
//  newInstance()의 반환타입이 Object이므로 Hello 형으로 변환
    Hello hello = (Hello) helloClass.newInstance();
//  Hello 클래스에 선언된 main 메서드를 Method 타입으로 저장
    Method main = helloClass.getDeclaredMethod("main");
//  private main()을 호출 가능케 설정
    main.setAccessible(true);
//  main 메서드 실행
    main.invoke(hello);
  }

}