package com.fastcampus.ch3;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//ApplicationContext 생성 및 설정파일 지정 애너테이션 2개
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class TxServiceTest {

  //TxService 타입 매칭 자동주입
  @Autowired
  TxService txService;
 
  //TxService의 4가지 메서드 호출 및 테스트 대상 선언 애너테이션 지정, 예외 던점 처리
  @Test
  public void testinsertB1BetweenA1DataWithTx() throws Exception {
    txService.insertB1BetweenA1DataWithTx();
  }

  @Test
  public void testInsertA1WithoutTxFail() throws Exception {
    txService.insertA1WithoutTxFail();
  }

  @Test
  public void testInsertA1WithTxFail() throws Exception {
    txService.insertA1WithTxFail();
  }

  @Test
  public void testInsertA1withTxSuccess() throws Exception {
    txService.insertA1withTxSuccess();
  }

}
