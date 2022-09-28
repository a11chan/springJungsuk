package com.fastcampus.ch3;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTest2Test {

//  @RunWith, @ContextConfiguration으로 대체
//  @Autowired ApplicationContext ac;
  
  @Autowired
  DataSource ds;
  
  @Test
  public void updateUserTest() throws Exception {
    deleteAll();
    User user = new User("asdf","1234","asdf","asdf@gmail.com",new Date(),"facebook",new Date());
    assertTrue(insertUser(user) == 1);
    
    user.setSns("instagram");
    assertTrue(updateUser(user)==1);
    
    User selectUser = selectUser("asdf");
    assertTrue(selectUser.getSns().equals("instagram"));
    
  }
  
  // 매개변수로 받은 사용자 정보로 user_info테이블을 update하는 메서드
  public int updateUser(User user) throws Exception {
    Connection conn = ds.getConnection();
    String sql = "update user_info set id = ?, pwd = ?, name = ?, email = ?, birth = ?, sns = ?, reg_date = now()";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, user.getId());
    pstmt.setString(2, user.getPwd());
    pstmt.setString(3, user.getName());
    pstmt.setString(4, user.getEmail());
    pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
    pstmt.setString(6, user.getSns());
    
    return pstmt.executeUpdate();
  }
  
  @Test
  public void deleteUserTest() throws Exception {
    deleteAll();
    assertTrue(deleteUser("asdf")==0);
    
    User user = new User("asdf2", "1234", "abc", "aaaa@aaa.com", new Date(), "fb", new Date());
    assertTrue(insertUser(user)==1);
    
    assertTrue(deleteUser(user.getId())==1);
    
    assertTrue(selectUser(user.getId())==null);
  }
  
  public int deleteUser(String id) throws Exception {
    Connection conn = ds.getConnection();
    String sql = "delete from user_info where id = ?";
    PreparedStatement pstmt =  conn.prepareStatement(sql);
    pstmt.setString(1, id);
    
    return pstmt.executeUpdate();
  }
  
  @Test
  public void selectUserTest() throws Exception {
    deleteAll();
    User user = new User("asdf","1234","asdf","asdf@gmail.com",new Date(),"facebook",new Date());
    insertUser(user);
    User selectUser = selectUser("asdf");
    assertTrue(selectUser.getId().equals("asdf"));
  }
  
  public User selectUser(String id) throws Exception {
    Connection conn = ds.getConnection();
    String sql = "select * from user_info where id = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, id);
    ResultSet rs = pstmt.executeQuery();
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
  
  @Test
  public void insertUserTest() throws Exception {
    deleteAll();
    User user = new User("asdf","1234","asdf","asdf@gmail.com",new Date(),"facebook",new Date());
    int rowCnt = insertUser(user);
    assertTrue(rowCnt == 1);
  }
  
  public void deleteAll() throws Exception {
    Connection conn = ds.getConnection();
    String sql = "delete from user_info";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.executeUpdate(sql);
  }
  
  // 사용자 정보를 user_info 테이블에 저장하는 메서드
  public int insertUser(User user) throws Exception {
    
    Connection conn = ds.getConnection();
    
//    String sql = "insert into user_info values('asdf','1234','asdf','asdf@gmail.com','2022-01-1','facebook',now())";
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
  
  @Test
  public void main() throws Exception {
//    ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//    DataSource ds = ac.getBean(DataSource.class);

    Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

    System.out.println("conn = " + conn);
    assertTrue(conn!=null);
  }

}
