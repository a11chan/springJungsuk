package com.fastcampus.ch4.domain;

public class PageHandler_old {
  private int totalCnt; // 총 게시물 수
  private int pageSize; // list size
  private int naviSize = 10; // 화면에 나타나는 페이지 번호 수
  private int totalPage; // total of navigation pages
  private int page; // 현재 페이지, viewPage
  private int beginPage;
  private int endPage;
  boolean showPrev;
  boolean showNext;

  public PageHandler_old(int totalCnt, int page) {
    this(totalCnt, page, 10);
  }

  public PageHandler_old(int totalCnt, int page, int pageSize) {
    this.totalCnt = totalCnt;
    this.page = page;
    this.pageSize = pageSize;

    totalPage = (int) Math.ceil((double)totalCnt / pageSize);
    beginPage = (page - 1) / naviSize * naviSize + 1;
    endPage = Math.min(totalPage, beginPage + naviSize -1);
    showPrev = beginPage != 1;
    showNext = endPage != totalPage;
  }

  void print() {
    System.out.print(showPrev ? "[PREV] " : "");
    for (int i = beginPage; i <= endPage; i++) {
      if (page == i)
        System.out.print("[" + i + "] ");
      else
        System.out.print(i + " ");
    }
    System.out.print(showNext ? "[NEXT]" : "");
  }

  @Override
  public String toString() {
    return "PageHandler [totalCnt=" + totalCnt + ", pageSize=" + pageSize + ", naviSize=" + naviSize + ", totalPage="
        + totalPage + ", page=" + page + ", beginPage=" + beginPage + ", endPage=" + endPage + ", showPrev=" + showPrev
        + ", showNext=" + showNext + "]";
  }

  public int getTotalCnt() {
    return totalCnt;
  }

  public void setTotalCnt(int totalCnt) {
    this.totalCnt = totalCnt;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
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

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
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

}