package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//서비스 계층 표현 애너테이션
@Service
//public class TxService 선언
public class TxService {
  //A1Dao를 타입 매칭으로 객체 자동 주입
  @Autowired A1Dao a1Dao;
  // TxService2 를 타입 매칭으로 객체 자동 주입
  @Autowired TxService2 txService2;
 
 // 애너테이션 사용으로 TxManager, TxStatus 생성 및 commit, rollback 코드 생략 가능(3.19 참고)
 // propagation = Propagation.REQUIRED -> 기본값, 생략 가능
 // Exception.class 예외 발생 시 rollback하는 애너테이션 요소 설정
 // public void insertA1WithTx() throws Exception 메서드 선언
  @Transactional
  public void insertB1BetweenA1DataWithTx() throws Exception {
    // a1 테이블의 모든 데이터 삭제
    a1Dao.deleteAll();
    // a1 테이블에 1, 100 컬럼값을 입력
    a1Dao.insert(1, 100);
    // txService2를 이용하여 b1 테이블의 모든 데이터를 삭제하고 2개 행 삽입하는 Tx실행
    txService2.insertB1withTxSuccess();
    // a1 테이블에 1, 100 속성값으로 이루어진 행 삽입
    a1Dao.insert(1, 100);
    // Test시 a1 테이블은 데이터 없고 b1 테이블엔 2건 정상입력
  }
  

 // Tx 없는 작업단위 메서드 선언
 // public void insertA1WithoutTx() throws Exception
  public void insertA1WithoutTxFail() throws Exception {
    //a1 테이블의 모든 데이터 삭제
    a1Dao.deleteAll();
    // 1, 100 으로 된 행 삽입
    a1Dao.insert(1, 100);
    // 1, 200 으로 된 행 삽입
    a1Dao.insert(1, 200);
    // 1,100 데이터만 남아 있는지, 서로 다른 커넥션인지 확인
  }
 
 //@Transactional //RuntimeException, Error만 rollback -> 다른 예외는 아래처럼 별도 지정필
 //Transaction 적용 및 Exception 예외 발생시 rollback 처리 애너테이션
 //public void insertA1WithTxFail() throws Exception 메서드 선언
  @Transactional(rollbackFor=Exception.class)
  public void insertA1WithTxFail() throws Exception {
    //a1 테이블의 모든 데이터 삭제
    a1Dao.deleteAll();
    // 1, 100 으로 된 행 삽입
    a1Dao.insert(1, 100);
    // 1, 200 으로 된 행 삽입
    a1Dao.insert(1, 200);
    // 같은 conn 사용 여부, rollback으로 a1 테이블에 데이터가 비어 있는지 확인
  }
 
 //TxManager, TxStatus 생성 및 commit, rollback 처리 해주는 애너테이션
  @Transactional
 //a1 테이블에 Tx 적용하면서 입력 성공하는 메서드 선언
  // insertA1withTxSuccess() throws Exception
  public void insertA1withTxSuccess() throws Exception {
    // a1 테이블의 모든 데이터 삭제
    a1Dao.deleteAll();
    // a1 테이블에 1, 100으로 된 행 삽입
    a1Dao.insert(1, 100);
    // a1 테이블에 2, 200으로 된 행 삽입
    a1Dao.insert(2, 200);
  }
}

//서비스 계층 애너테이션
//외부 클래스 TxService2 선언
@Service
class TxService2 {
  // B1Dao를 타입 매칭으로 자동 주입
  @Autowired B1Dao b1Dao;
  
  //TxManager, TxStatus 생성 및 commit, rollback 처리 해주는 애너테이션
  //기존 Tx 존재 여부와 상관 없이 새로운 Tx 시작하는 애너테이션 요소 설정
  //Exception 예외 발생 시 rollback하는 애너테이션 요소 설정
  //b1 테이블에 Tx를 통해 데이터를 삽입하는 메서드 선언
  @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
  public void insertB1withTxSuccess() throws Exception {
    //b1 테이블의 모든 데이터 삭제
    b1Dao.deleteAll();
    //b1 테이블에 1, 100으로 된 행 삽입
    b1Dao.insert(1, 100);
    //b1 테이블에 2, 200으로 된 행 삽입
    b1Dao.insert(2, 200);
    //a1 테이블 입력 작업은 취소되어도 b1 테이블의 입력작업은 커밋되었음을 확인
  }
}
