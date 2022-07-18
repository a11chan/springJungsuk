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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // ApplicationContext를 자동으로 만들어주는 역할
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class DBTransactionTest {
//  @Autowired
//  ApplicationContext ac;

  @Autowired
  DataSource ds;
  
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
  
  public int deleteUser(String id) throws Exception {
    Connection conn = ds.getConnection();
    String sql = "delete from user_info where id = ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1,id);
    return pstmt.executeUpdate();
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
      user.setReg_date(new Date(rs.getTime(7).getTime()));
      
      return user;
    }
    
    return null;
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
  
  @Test
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
      
      pstmt.executeUpdate();
      pstmt.setString(1, "asdf");
      pstmt.executeUpdate();
      
      conn.commit();
      
    } catch (Exception e) {
      conn.rollback();
      e.printStackTrace();
    }
  }

}
