package com.fastcampus.ch3;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // ApplicationContext를 자동으로 만들어주는 역할
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class UserDaoImpl_Test {
  
  @Autowired
  UserDao userDao;

//  @Test
  public void testDeleteUser() {
  }

//  @Test
  public void testSelectUser() {
  }

//  @Test
  public void testInsertUser() {
  }

  @Test
  public void testUpdateUser() throws Exception {
    Calendar cal = Calendar.getInstance();
    cal.clear();
    cal.set(2022, 7, 15);
    // java.util.Date는 날짜/시간 모두 포함하지만 SQL에서의 Date형은 날짜만 포함하므로
    // 시간 정보 초기화 필요
    
    userDao.deleteAll();
    User user = new User("lily","1234","abc","aaa@gmail.com",new Date(cal.getTimeInMillis()),"facebook",new Date());

    int rowCnt = userDao.insertUser(user);
    assertTrue(rowCnt==1);
    
    user.setName("릴리");
    user.setEmail("bbb@gmail.com");
    rowCnt = userDao.updateUser(user);
    assertTrue(rowCnt==1);
    
    User selectUser = userDao.selectUser("lily");
    System.out.println();
    System.out.println("===========================");
    System.out.println("user = "+user);
    System.out.println("selectUser = "+selectUser);
    System.out.println("===========================");
    System.out.println();
    assertTrue(user.equals(selectUser));
  }

}