package com.fastcampus.ch2;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

// 필터를 적용할 요청의 패턴 지정 - 모든 요청에 필터를 적용.
// 클래스 선언, Filter 인터페이스 구현
  @WebFilter(urlPatterns="/*")
  public class PerformanceFilter_1 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      // 초기화 작업
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
      long startTime = System.currentTimeMillis();
      
      chain.doFilter(request, response);
      
      HttpServletRequest req = (HttpServletRequest) request;
      String referer = req.getHeader("referer"); // 요청자
      String method = req.getMethod(); // GET, POST, ...
      
      System.out.print("["+referer+"] -> " + method + "["+req.getRequestURI()+"]");
      System.out.println(" 소요시간 = "+(System.currentTimeMillis()-startTime)+"ms");
    }

    @Override
    public void destroy() {
      // TODO Auto-generated method stub
      
    }
    
  }