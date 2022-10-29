package com.fastcampus.ch3;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

//ApplicationContext를 자동으로 만들어주는 애너테이션
@RunWith(SpringJUnit4ClassRunner.class)
//ApplicationContext 생성에 필요한 설정파일 지정하는 애너테이션
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class A1DaoTest {

	//A1Dao 자동주입
  @Autowired
  A1Dao a1dao;
  
	//DataSource 자동주입
  @Autowired
  DataSource ds;
  
  @Autowired
  DataSourceTransactionManager tm;
  
	//애너테이션으로 테스트 대상 메서드로 설정
  @Test
  //예외를 던지는 public void inserTest() 메서드 선언
  public void insertTest() throws Exception {
    //TxManager 생성 //TxManager 사용 시 A1Dao에서 Connection 가져오고 닫는 부분 수정 필요
    //아래 코드는 xml로 bean 생성 후 DI처리 - @Autowired DataSourceTransactionManager
    //PlatformTransactionManager tm = new DataSourceTransactionManager(ds);
    //tm에 있는 메서드를 통해, 기본 Tx 정책 객체를 매개변수로 하는 Tx를 얻은 후 TransactionStatus ts에 저장
    TransactionStatus ts = tm.getTransaction(new DefaultTransactionDefinition());
    
    //try문 시작
    try {
      //테이블의 모든 레코드 삭제
      a1dao.deleteAll();
      //1, 100 데이터 DB에 등록
      a1dao.insert(1, 100);
      //1, 200 데이터 DB에 등록
      a1dao.insert(1, 200);
      //TxManager를 통해 커밋
      tm.commit(ts);
      // catch문 시작, 매개변수로는 Exception e
    } catch(Exception e) {
      //예외 발생 시 StackTrace 출력
      e.printStackTrace();
      //TxManager를 통해 rollback 실행
      tm.rollback(ts);
    }
  }
}