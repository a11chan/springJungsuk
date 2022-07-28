//package com.fastcampus.ch4.domain;
//
//import static org.junit.Assert.*;
//
//import org.junit.Test;
//
//public class PageHandlerTest {
//
//  @Test
//  public void test1() {
//    PageHandler ph = new PageHandler(250, 1);
//    System.out.println("view page = " + ph.getPage());
//    ph.print();
//    System.out.println();
//    System.out.println("ph = " + ph);
//    System.out.println();
//    assertTrue(ph.getBeginPage() == 1);
//    assertTrue(ph.getEndPage() == 10);
//  }
//
//  @Test
//  public void test2() {
//    PageHandler ph = new PageHandler(250, 15);
//    System.out.println("view page = " + ph.getPage());
//    ph.print();
//    System.out.println();
//    System.out.println("ph = " + ph);
//    System.out.println();
//    assertTrue(ph.getBeginPage() == 11);
//    assertTrue(ph.getEndPage() == 20);
//  }
//  
//  @Test
//  public void test3() {
//    PageHandler ph = new PageHandler(255, 25);
//    System.out.println("view page = " + ph.getPage());
//    ph.print();
//    System.out.println();
//    System.out.println("ph = " + ph);
//    System.out.println();
//    assertTrue(ph.getBeginPage() == 21);
//    assertTrue(ph.getEndPage() == 26);
//  }
//  
//  @Test
//  public void test4() {
//    PageHandler ph = new PageHandler(255, 20);
//    System.out.println("view page = " + ph.getPage());
//    ph.print();
//    System.out.println();
//    System.out.println("ph = " + ph);
//    System.out.println();
//    assertTrue(ph.getBeginPage() == 11);
//    assertTrue(ph.getEndPage() == 20);
//  }
//}
