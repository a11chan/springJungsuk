package com.fastcampus.ch2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class SetterCall {
  public static void main(String[] args) throws Exception {
    Map<String, String> map = new HashMap<>();
    map.put("year", "2021");
    map.put("month","10");
    map.put("day", "1");
    
    Class<?> type = Class.forName("com.fastcampus.ch2.MyDate");
    
    Object obj = dataBind(map, type);
    System.out.println("obj="+obj);
  }

  private static Object dataBind(Map<String, String> map, Class<?> clazz) throws Exception {
    Object obj = clazz.getDeclaredConstructor().newInstance(new Object[0]);
    Field[] ivArr = clazz.getDeclaredFields();
    
    for(int i = 0; i<ivArr.length ; i++) {
      Class<?> type = ivArr[i].getType();
      String name = ivArr[i].getName();
      
      Object value = map.get(name);
      Method method = null;
      
      try {
        if(value==null) continue;
        method = clazz.getDeclaredMethod(getSetterName(name), type);
        System.out.println("method="+method);
        method.invoke(obj, convertTo(value,type));
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
    
    System.out.println(Arrays.toString(ivArr));
    
    return obj;
  }

  private static Object convertTo(Object value, Class<?> type) {
    if(value==null || type==null || type.isInstance(value))
      return value;
    
    if(String.class.isInstance(value) && type==int.class)
      return Integer.valueOf(""+value);
    
    return value;
  }

  private static String getSetterName(String name) {
    return "set"+StringUtils.capitalize(name);
  }
}
