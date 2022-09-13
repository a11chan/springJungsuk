package com.fastcampus.ch3;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.fastcampus.ch3.User;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // ApplicationContext를 자동으로 만들어주는 역할
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" }) // 테스트에 사용할 설정파일 지정
public class DBConnectionTest2Test {
//  @Autowired
//  ApplicationContext ac; //위의 @RunWith, @ContextConfiguration로 대체 가능
  
  //@ContextConfiguration(...)의 설정파일에 있는 bean 정보를 통해 자동주입
  @Autowired
  DataSource ds;
  
  @Test
  public void springJdbcConnectionTest() throws Exception {
//    ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml"); // 위의 @RunWith, @ContextConfiguration로 대체 가능
//    DataSource ds = ac.getBean(DataSource.class); //@Autowired로 대체 가능

    Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

    System.out.println("conn = " + conn);
    assertTrue(conn != null);
  }
  
//  @Test
  public void updateUserTest() throws Exception {
    deleteAll();
    User user = new User("jkl", "1234", "abc", "aaa@gmail.com", new java.util.Date(), "fb", new java.util.Date());
    int rowCnt = insertUser(user);
    assertTrue(rowCnt==1);
    rowCnt = updateUser(user);
    assertTrue(rowCnt==1);
  }
  
  public int updateUser(User user) throws Exception {
    Connection conn = ds.getConnection();
    String sql = "update user_info set ";
           sql += "id=?, pwd=?, name=?, email=?, birth=?, ";
           sql += "sns=?, reg_date=?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, user.getId());
    pstmt.setString(2, user.getPwd());
    pstmt.setString(3, user.getName());
    pstmt.setString(4, user.getEmail());
    pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
    pstmt.setString(6,user.getSns());
    pstmt.setTimestamp(7, new java.sql.Timestamp(user.getReg_date().getTime()));
    int rowCnt = pstmt.executeUpdate();

    return rowCnt;
  }
  
//  @Test
  public void deleteUserTest() throws Exception {
    deleteAll();
    int rowCnt = deleteUser("jkl");
    
    assertTrue(rowCnt==0);
    
    User user = new User("jkl", "1234", "abc", "aaa@gmail.com", new java.util.Date(), "fb", new java.util.Date());
    rowCnt = insertUser(user);
    assertTrue(rowCnt==1);
    rowCnt = deleteUser(user.getId());
    assertTrue(rowCnt==1);
    
    assertTrue(selectUser(user.getId())==null);
  }
  
  public int deleteUser(String id) throws Exception {
    Connection conn = ds.getConnection();
    String sql = "delete from user_info where id = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1,id);
    return pstmt.executeUpdate();
  }
  
//  @Test
  public void selectUserTest() throws Exception {
    deleteAll();
    User user = new User("jkl", "1234", "abc", "aaa@gmail.com", new java.util.Date(), "fb", new java.util.Date());
    int rowCnt = insertUser(user);
    User user2 = selectUser("jkl");
    
    assertTrue(user2.getId().equals("jkl"));
  }
  
  public User selectUser(String id) throws Exception {
    Connection conn = ds.getConnection();
    String sql = "select * from user_info where id=?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, id);
    ResultSet rs =  pstmt.executeQuery();
    
    if(rs.next()) {
      User user = new User();
      user.setId(rs.getString(1));
      user.setPwd(rs.getString(2));
      user.setName(rs.getString(3));
      user.setEmail(rs.getString(4));
      user.setBirth(new Date(rs.getDate(5).getTime()));
      user.setSns(rs.getString(6));
      user.setReg_date(new Date(rs.getTimestamp(7).getTime()));
      
      return user;
    }
    
    return null;
  }
  
//  @Test
  public void insertUserTest() throws Exception {
    User user = new User("jkl", "1234", "abc", "aaa@gmail.com", new java.util.Date(), "fb", new java.util.Date());
    deleteAll();
    int rowCnt = insertUser(user);

    assertTrue(rowCnt == 1);
  }

  private void deleteAll() throws Exception {
      Connection conn = ds.getConnection();
      String sql = "delete from user_info";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.executeUpdate(); 
  }

  // 사용자 정보를 user_info 테이블에 저장
  public int insertUser(User user) throws Exception {
    Connection conn = ds.getConnection();
    String sql = "insert into user_info values(?,?,?,?,?,?,now())";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, user.getId());
    pstmt.setString(2, user.getPwd());
    pstmt.setString(3, user.getName());
    pstmt.setString(4, user.getEmail());
    pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
    pstmt.setString(6, user.getSns());

    int rowCnt = pstmt.executeUpdate();

    return rowCnt;
  }
  
//  @Test
  public void transactionTest() throws Exception {
    Connection conn=null;
    try {
      deleteAll();
      conn = ds.getConnection();
      conn.setAutoCommit(false);
      
      String sql = "insert into user_info values(?,?,?,?,?,?,now())";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, "asdf");
      pstmt.setString(2, "1234");
      pstmt.setString(3, "moly");
      pstmt.setString(4, "moly@gmail.com");
      pstmt.setDate(5, new java.sql.Date(new Date().getTime()));
      pstmt.setString(6, "meta");
      
      int rowCnt = pstmt.executeUpdate();
      pstmt.setString(1, "asdf2");
      rowCnt = pstmt.executeUpdate();
      
      conn.commit();
      
    } catch (Exception e) {
      conn.rollback();
      e.printStackTrace();
    }
    
  }

}
