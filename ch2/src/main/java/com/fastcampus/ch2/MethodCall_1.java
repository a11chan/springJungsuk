package com.fastcampus.ch2;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

class ModelController_1 {
  public String main(HashMap map) {
    map.put("id", "asdf");
    map.put("pwd", "1111");
    
    return "txtView2";
  }
}

public class MethodCall_1 {
  public static void main(String[] args) throws Exception {
    HashMap map = new HashMap();
    System.out.println("before:"+map);
    
    ModelController_1 mc = new ModelController_1();
    String viewName = mc.main(map);
    
    System.out.println("after :"+map);
    
    render(map, viewName);
  }

  static void render(HashMap map, String viewName) throws IOException {
    String result = "";
    
    Scanner sc = new Scanner(new File(viewName+".txt"));
    
    while(sc.hasNextLine())
      result += sc.nextLine()+System.lineSeparator();
    
    Iterator it = map.keySet().iterator();
    
    while(it.hasNext()) {
      String key = (String)it.next();
      
      result = result.replace("${"+key+"}", (String)map.get(key));
    }
    System.out.println(result);
  }
}