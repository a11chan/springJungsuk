package com.fastcampus.ch3;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

@Repository
public class A1Dao {
  @Autowired
  DataSource ds;
  
  public int insert(int key, int value) throws Exception {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try {
//      conn = ds.getConnection();
      conn = DataSourceUtils.getConnection(ds);
      System.out.println("==========================");
      System.out.println("conn="+conn); //Tx 적용 시 같은 Connection에 묶여있는지 확인용
      String sql = "insert into a1 values(?,?)";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, key);
      pstmt.setInt(2, value);
      return pstmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
//      close(conn, pstmt); //TxManager 사용 시 아래와 같이 수정필
      close(pstmt);
      DataSourceUtils.releaseConnection(conn, ds);
    }
  }
  
  private void close(AutoCloseable... acs) {
    for(AutoCloseable ac : acs) {
      try { if(ac!=null) ac.close(); } catch(Exception e) { e.printStackTrace(); }
    }
  }

  public void deleteAll() throws Exception {
    Connection conn = ds.getConnection();
    String sql = "delete from a1";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.executeUpdate();
    close(pstmt);
  }
}
