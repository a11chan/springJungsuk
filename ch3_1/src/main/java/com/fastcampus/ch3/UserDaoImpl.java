package com.fastcampus.ch3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
  
  @Autowired
  DataSource ds;
  final int FAIL = 0;
  
  @Override
  public int deleteUser(String id) {
    
    String sql = "delete from user_info where id = ?";
    int rowCnt = FAIL;
    
    try(
        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
    ) {
      pstmt.setString(1, id);
      rowCnt = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      return FAIL;
    }
    return rowCnt;
  }

  @Override
  public User selectUser(String id) {
    
    String sql = "select * from user_info where id = ?";
    User user = null;
    ResultSet rs = null;
    
    try (
      Connection conn = ds.getConnection();
      PreparedStatement pstmt = conn.prepareStatement(sql);
    ) { 
      pstmt.setString(1, id);
      rs = pstmt.executeQuery();
      if(rs.next()) {
        user = new User();
        user.setId(rs.getString(1));
        user.setPwd(rs.getString(2));
        user.setName(rs.getString(3));
        user.setEmail(rs.getString(4));
        user.setBirth(new Date(rs.getDate(5).getTime()));
        user.setSns(rs.getString(6));
        user.setReg_date(new Date(rs.getTimestamp(7).getTime()));
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
    return user;
  }

  @Override
  public int insertUser(User user) {
    
    String sql = "insert into user_info values (?, ?, ?, ?, ?, ?, now())";
    int rowCnt = FAIL;
    
    try (
        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
    ) {
      pstmt.setString(1, user.getId());
      pstmt.setString(2, user.getPwd());
      pstmt.setString(3, user.getName());
      pstmt.setString(4, user.getEmail());
      pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
      pstmt.setString(6,user.getSns());
      
      rowCnt = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      return FAIL;
    }
    
    return rowCnt;
  }

  @Override
  public int updateUser(User user) {
    
    String sql = "update user_info set id = ?, pwd = ?, name = ?, email = ?, birth = ?, sns = ?, reg_date = ?";
    int rowCnt = FAIL;
    
    try (
      Connection conn = ds.getConnection();
      PreparedStatement pstmt = conn.prepareStatement(sql);
    ) {
      pstmt.setString(1, user.getId());
      pstmt.setString(2, user.getPwd());
      pstmt.setString(3, user.getName());
      pstmt.setString(4, user.getEmail());
      pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
      pstmt.setString(6,user.getSns());
      pstmt.setTimestamp(7, new java.sql.Timestamp(user.getReg_date().getTime()));
      rowCnt = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      return FAIL;
    }
    return rowCnt;
  }

  @Override
  public int deleteAll() {

    String sql = "delete from user_info";
    int rowCnt = FAIL;
    
    try (
      Connection conn = ds.getConnection();
      PreparedStatement pstmt = conn.prepareStatement(sql);
    ) {
      rowCnt = pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      return FAIL;
    }
    
    return rowCnt;
  }

}