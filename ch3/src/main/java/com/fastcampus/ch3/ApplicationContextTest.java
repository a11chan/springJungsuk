//package com.fastcampus.ch3;
//
//import java.util.Arrays;
//import java.util.Map;
//import java.util.Properties;
//
//import javax.annotation.Resource;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.support.GenericXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//@Component
//@Scope("prototype")
//class Door {
//}
//
//@Component
//class Engine {
//}
//
//@Component
//class TurboEngine extends Engine {
//}
//
//@Component
//class SuperEngine extends Engine {
//}
//
//@Component
//class Car {
//  
//  String color;
//  
//  int oil;
////    @Autowired
////  @Resource
//  Engine engine;
////    @Autowired
//  Door[] doors;
//
////  @Autowired // 생략해도 자동 주입
//  public Car(@Value("red")String color, @Value("100")int oil, Engine engine, Door[] doors) {
//    super();
//    this.color = color;
//    this.oil = oil;
//    this.engine = engine;
//    this.doors = doors;
//  }
//
//  @Override
//  public String toString() {
//    return "Car{" + "color='" + color + '\'' + ", oil=" + oil + ", engine=" + engine + ", doors="
//        + Arrays.toString(doors) + '}';
//  }
//}
//
//@Component
//@PropertySource("setting.properties")
//class SysInfo {
//  @Value("#{systemProperties['user.timezone']}")
//  String timezone;
//  @Value("#{systemEnvironment['APPDATA']}")
//  String currDir;
//  @Value("${autosaveDir}")
//  String autosaveDir;
//  @Value("${autosaveInterval}")
//  int autosaveInterval;
//  @Value("${autosave}")
//  boolean autosave;
//  
//  @Override
//  public String toString() {
//    return "SysInfo [timezone=" + timezone + ", currDir=" + currDir + ", autosaveDir=" + autosaveDir
//        + ", autosaveInterval=" + autosaveInterval + ", autosave=" + autosave + "]";
//  }
//  
//}
//
//public class ApplicationContextTest {
//  public static void main(String[] args) {
//    ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
////      Car car = ac.getBean("car", Car.class); // 타입을 지정하면 형변환 안해도됨. 아래의 문장과 동일
//    Car car = (Car) ac.getBean("car"); // 이름으로 빈 검색
//    Car car2 = (Car) ac.getBean(Car.class); // 타입으로 빈 검색
//    System.out.println("car = " + car);
//    System.out.println("car2 = " + car2);
//    
//    System.out.println("ac.getBean(SysInfo.class) = " + ac.getBean(SysInfo.class));
//    System.out.println();
//    
//    Map<String, String> map = System.getenv();
//    System.out.println("map = "+ map);
//    System.out.println();
//    
//    Properties properties = System.getProperties();
//    System.out.println("properties = "+properties);
//
//  }
//}
//
///*
// * [src/main/resources/config.xml] <?xml version="1.0" encoding="UTF-8"?> <beans
// * xmlns="http://www.springframework.org/schema/beans"
// * xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
// * xmlns:context="http://www.springframework.org/schema/context" xsi:
// * schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd"
// * >
// * 
// * <context:annotation-config/> <context:component-scan
// * base-package="com.fastcampus.ch3"/> </beans>
// */
//
///*
// * [실행결과] car = Car{color='red', oil=100,
// * engine=com.fastcampus.ch3.Engine@df6620a,
// * doors=[com.fastcampus.ch3.Door@205d38da]} car2 = Car{color='red', oil=100,
// * engine=com.fastcampus.ch3.Engine@df6620a,
// * doors=[com.fastcampus.ch3.Door@205d38da]} ac.getBeanDefinitionNames() =
// * [org.springframework.context.annotation.
// * internalConfigurationAnnotationProcessor,
// * org.springframework.context.annotation.internalAutowiredAnnotationProcessor,
// * org.springframework.context.annotation.internalRequiredAnnotationProcessor,
// * org.springframework.context.annotation.internalCommonAnnotationProcessor,
// * org.springframework.context.event.internalEventListenerProcessor,
// * org.springframework.context.event.internalEventListenerFactory, car, door,
// * engine, superEngine, turboEngine] ac.getBeanDefinitionCount() = 11
// * ac.containsBeanDefinition("car") = true ac.containsBean("car") = true
// * ac.getType("car") = class com.fastcampus.ch3.Car ac.isSingleton("car") = true
// * ac.isPrototype("car") = false ac.isPrototype("door") = true
// * ac.isTypeMatch("car", Car.class) = true ac.findAnnotationOnBean("car",
// * Component.class) = @org.springframework.stereotype.Component(value="")
// * ac.getBeanNamesForAnnotation(Component.class) = [car, door, engine,
// * superEngine, turboEngine] ac.getBeanNamesForType(Engine.class) = [engine,
// * superEngine, turboEngine]
// */