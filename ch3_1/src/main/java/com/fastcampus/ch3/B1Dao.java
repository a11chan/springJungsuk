package com.fastcampus.ch3;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

//DB에 접근하는 저장소 역할을 하는 클래스임을 나타내는 애너테이션
@Repository
public class B1Dao {
  //Type 자동주입 애너테이션
  @Autowired
  //JDBC 드라이버 설정 정보를 가지고 있는 객체 참조변수 선언
  DataSource ds;
  
  //int key, int value를 매개변수로 하는 insert public 메서드 선언, 반환 타입은 int
  public int insert(int key, int value) throws Exception {
    //Connection형의 conn 참조변수 선언 및 null로 초기화
    Connection conn = null;
    //PreparedStatement 형의 pstmt 참조변수 선언 및 null로 초기화
    PreparedStatement pstmt = null;
    
    // try문 시작
    try {
      //conn = ds.getConnection(); //TxManager 사용 전 코드
      //DataSourceUtils를 통해 DataSource에 대한 Connection을 얻어 conn 참조변수에 저장
      conn = DataSourceUtils.getConnection(ds);
      //콘솔에 현재 Connection 해시값 출력 //Tx 적용 시 같은 Connection에 묶여있는지 확인용
      System.out.println("=====================");
      System.out.println("conn="+conn);
      //a1 테이블에 2개의 와일드카드를 insert into 하는 sql문을 String으로 저장
      String str = "insert into b1 values(?,?)";
      //실행할 sql문을 매개변수로 하는 prepareStatement 메서드의 결과를 pstmt 참조변수에 저장
      pstmt = conn.prepareStatement(str);
      //pstmt에 저장된 sql문에 저장할 2개의 속성값을 설정
      pstmt.setInt(1, key);
      pstmt.setInt(2, value);
      // return문으로 설정된 sql문을 실행한 결과를 반환
      return pstmt.executeUpdate();
    //try문 종료, catch문 시작, Exception 예외를 매개변수로 함      
    } catch(Exception e) {
      //예외 e의 StackTrace를 출력함
      e.printStackTrace();
      //A1Dao를 호출한 쪽으로(TxManager가 있는 곳) 예외를 던짐, 그래야 rollback 가능
      throw e;
    //catch문 종료, finally문 시작
    } finally {
      //close(conn, pstmt); //TxManager 사용 전 코드
      //가장 나중에 사용한 객체부터 메모리에서 해제
      //pstmt 참조변수 메모리에서 해제
      close(pstmt);
      //DataSourceUtils의 어떤 메서드를 통해 conn, ds 참조변수 메모리에서 해제
      DataSourceUtils.releaseConnection(conn, ds);
    }
  }
  //AutoCloseable acs를 매개변수로 하는 private void close 메서드 선언, 매개변수는 가변으로 설정
  private void close(AutoCloseable... acs) {
    //향상된 for문으로 acs 매개변수 묶음을 AutoClosable ac로 하나씩 받아 실행
    for(AutoCloseable ac : acs) {
      //try문으로 ac가 null이 아닐 때 ac를 close
      //catch문으로 Exception e 발생 시 StackTrace 출력
      try {if(ac!=null) ac.close();} catch(Exception e){e.printStackTrace();} 
    }
  }

  // Exception 예외를 던지고 매개변수가 없는 public void deleteAll() 메서드 선언
  public void deleteAll() throws Exception {
    // Tx와 별개로 동작해야 하므로 DataSourceUtils 쓰지 않고 DB로의 Connection을 얻어 Connection conn에 저장
    Connection conn = ds.getConnection();
    // a1 테이블에서 모든 데이터를 삭제하는 쿼리문을 작성하여 String sql에 저장
    String str = "delete from a1";
    //sql문을 준비하여 pstmt 참조변수에 저장
    PreparedStatement pstmt = conn.prepareStatement(str);
    //준비된 쿼리문을 실행
    pstmt.executeUpdate();
    //pstmt 참조변수를 위에서 정의한 close 메서드를 통해 메모리에서 해제
    close(pstmt);
  }
}