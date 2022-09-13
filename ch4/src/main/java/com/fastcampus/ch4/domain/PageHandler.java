package com.fastcampus.ch4.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class PageHandler {
//  private int page; // 현재 페이지
//  private int pageSize; // list size
//  private String option;
//  private String keyword;
  private SearchCondition sc;

  private int totalCnt; // total item
  private int naviSize = 10;
  private int totalPage; // total of navigation pages
  private int beginPage;
  private int endPage;
  private boolean showPrev;
  private boolean showNext;

  public PageHandler(int totalCnt, SearchCondition sc) {
    this.totalCnt = totalCnt;
    this.sc = sc;

    doPaging(totalCnt, sc);
  }

  public void doPaging(int totalCnt, SearchCondition sc) {
    this.totalCnt = totalCnt;

    totalPage = (int) Math.ceil((double) totalCnt / (double) sc.getPageSize());
//    beginPage = (int) ((double) page % (double) naviSize == 0 ? page - naviSize +1 : page / naviSize * naviSize + 1);
    beginPage = (sc.getPage() - 1) / naviSize * naviSize + 1;
    endPage = Math.min(totalPage, beginPage + naviSize - 1);
    showPrev = beginPage != 1;
    showNext = endPage != totalPage;
  }

  public SearchCondition getSc() {
    return sc;
  }

  public void setSc(SearchCondition sc) {
    this.sc = sc;
  }

  public int getTotalCnt() {
    return totalCnt;
  }

  public void setTotalCnt(int totalCnt) {
    this.totalCnt = totalCnt;
  }

  public int getNaviSize() {
    return naviSize;
  }

  public void setNaviSize(int naviSize) {
    this.naviSize = naviSize;
  }

  public int getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }

  public int getBeginPage() {
    return beginPage;
  }

  public void setBeginPage(int beginPage) {
    this.beginPage = beginPage;
  }

  public int getEndPage() {
    return endPage;
  }

  public void setEndPage(int endPage) {
    this.endPage = endPage;
  }

  public boolean isShowPrev() {
    return showPrev;
  }

  public void setShowPrev(boolean showPrev) {
    this.showPrev = showPrev;
  }

  public boolean isShowNext() {
    return showNext;
  }

  public void setShowNext(boolean showNext) {
    this.showNext = showNext;
  }

  void print() {
    System.out.print(showPrev ? "[PREV] " : "");
    for (int i = beginPage; i <= endPage; i++) {
      if (sc.getPage() == i)
        System.out.print("[" + i + "] ");
      else
        System.out.print(i + " ");
    }
    System.out.print(showNext ? "[NEXT]" : "");
  }

  @Override
  public String toString() {
    return "PageHandler [sc=" + sc + ", totalCnt=" + totalCnt + ", naviSize=" + naviSize + ", totalPage=" + totalPage
        + ", beginPage=" + beginPage + ", endPage=" + endPage + ", showPrev=" + showPrev + ", showNext=" + showNext
        + "]";
  }

}
