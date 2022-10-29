package com.fastcampus.ch3;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // ApplicationContext를 아래 설정파일에 따라 자동으로 만들어주는 역할
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoImplTest {

  @Autowired
  DataSource ds;
  @Autowired
  UserDao userDao;
  
  @Test
  public void testDeleteUser() {
    userDao.deleteAll();
    assertTrue(userDao.deleteUser("sera")==0);
    
    Calendar cal = Calendar.getInstance();
    cal.clear();
    cal.set(2022,02,02);
    
    User user = new User("sera","1234","sera","sera@gmail.com",new Date(cal.getTimeInMillis()),"인스타그램",new Date());
    assertTrue(userDao.insertUser(user)==1);
    assertTrue(userDao.deleteUser("sera")==1);
    assertTrue(userDao.selectUser(user.getId())==null);
    
  }
  
  @Test
  public void testSelectUser() {
    userDao.deleteAll();
    
    Calendar cal = Calendar.getInstance();
    cal.clear();
    cal.set(2022,02,02);
    
    User user = new User("sera","1234","sera","sera@gmail.com",new Date(cal.getTimeInMillis()),"instagram",new Date());
    assertTrue(userDao.insertUser(user)==1);
    
    User selectUser = userDao.selectUser(user.getId());
    assertTrue(selectUser.getId().equals("sera"));
    
  }

  @Test
  public void testInsertUser() {
    Calendar cal = Calendar.getInstance();
    cal.clear();
    cal.set(2022,2,2);
    
    userDao.deleteAll();
    User user = new User("sera","1234","sera","sera@gmail.com",new Date(cal.getTimeInMillis()),"인스타그램",new Date());
    System.out.println(user);
    assertTrue(userDao.insertUser(user)==1);
  }
  
  @Test
  public void testUpdateUser() {
    Calendar cal = Calendar.getInstance();
    cal.clear();
    cal.set(2022,02,02);
    // java.util.Date는 날짜/시간 모두 포함하지만 SQL에서의 Date형은 날짜만 포함하므로
    // 시간 정보 초기화 필요
    
    userDao.deleteAll();
    User user = new User("sera","1234","sera","sera@gmail.com",new Date(cal.getTimeInMillis()),"인스타그램",new Date());
    
    assertTrue(userDao.insertUser(user)==1);
    user.setSns("페이스북");
    
    assertTrue(userDao.updateUser(user)==1);
    
    User selectUser = userDao.selectUser(user.getId());
    assertTrue(selectUser.getSns().equals("페이스북"));
    assertTrue(user.equals(selectUser));
  }

}
