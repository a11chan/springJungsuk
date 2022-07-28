package com.fastcampus.ch4.dao;

import java.util.List;
import java.util.Map;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.SearchCondition;

public interface BoardDao {

  int increaseViewCnt(Integer bno) throws Exception;

  int update(BoardDto dto) throws Exception;

  List<BoardDto> selectPage(Map map) throws Exception;

  List<BoardDto> selectAll() throws Exception;

  int delete(Integer bno, String writer) throws Exception;

  int deleteAll();

  int searchResultCnt(SearchCondition sc) throws Exception;

  List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception;

  BoardDto select(Integer bno) throws Exception;

  public int insert(BoardDto dto) throws Exception;

  public int count() throws Exception;

  int updateCommentCnt(Integer bno, int cnt);
}