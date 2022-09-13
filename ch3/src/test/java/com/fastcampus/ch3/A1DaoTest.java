package com.fastcampus.ch3;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@RunWith(SpringJUnit4ClassRunner.class) // ApplicationContext를 자동으로 만들어주는 역할
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) // 테스트에 사용할 설정파일 지정
public class A1DaoTest {

  @Autowired
  A1Dao a1Dao;
  
  @Autowired
  DataSource ds;
  
  @Autowired
  DataSourceTransactionManager tm;
  
  @Test
  public void insertTest() throws Exception {
    // TxManager 생성
      //PlatformTransactionManager tm = new DataSourceTransactionManager(ds); //xml로 bean 생성 후 DI처리 - @Autowired DataSourceTransactionManager
    TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());
    
    // Tx시작
    try {
      a1Dao.deleteAll();
      a1Dao.insert(1,100); // TxManager 사용 시 A1Dao에서 Connection 가져오는 부분 수정 필요
      a1Dao.insert(2,200);
      tm.commit(status);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      tm.rollback(status);
    } finally {
      
    }
  }

}
