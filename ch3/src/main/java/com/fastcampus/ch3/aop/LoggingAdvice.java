package com.fastcampus.ch3.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAdvice {
  @Around("execution(* com.fastcampus.ch3.aop.MyMath.add*(..))") // advice(부가기능) 적용 패턴 정의
  public Object methodCallLog(ProceedingJoinPoint pjp) throws Throwable {
    long start = System.currentTimeMillis();
    System.out.println("<<[start] "+pjp.getSignature().getName()+Arrays.deepToString(pjp.getArgs()));
    
    Object result = pjp.proceed();
    
    System.out.println("result="+result);
    System.out.println("[end]>> "+(System.currentTimeMillis() - start)+"ms");

    return result;
  }
}
