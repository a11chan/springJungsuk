package com.fastcampus.ch4.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class PageHandlerTest {

  @Test
  public void test1() {
    PageHandler_old ph = new PageHandler_old(100, 1);
    System.out.println("view page = " + ph.getPage());
    ph.print();
    System.out.println();
    System.out.println("ph = " + ph);
    System.out.println();
    assertTrue(ph.getBeginPage() == 1);
    assertTrue(ph.getEndPage() == 10);
  }

  @Test
  public void test2() {
    PageHandler_old ph = new PageHandler_old(100, 10);
    System.out.println("view page = " + ph.getPage());
    ph.print();
    System.out.println();
    System.out.println("ph = " + ph);
    System.out.println();
    assertTrue(ph.getBeginPage() == 1);
    assertTrue(ph.getEndPage() == 10);
  }
  
  @Test
  public void test4() {
    PageHandler_old ph = new PageHandler_old(255, 25);
    System.out.println("view page = " + ph.getPage());
    ph.print();
    System.out.println();
    System.out.println("ph = " + ph);
    System.out.println();
    assertTrue(ph.getBeginPage() == 21);
    assertTrue(ph.getEndPage() == 26);
  }
}