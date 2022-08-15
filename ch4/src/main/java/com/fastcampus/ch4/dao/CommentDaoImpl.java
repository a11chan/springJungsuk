package com.fastcampus.ch4.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fastcampus.ch4.domain.CommentDto;

@Repository
public class CommentDaoImpl implements CommentDao {
  @Autowired
  private SqlSession session;
  String namespace = "com.fastcampus.ch4.dao.CommentMapper.";
  // xml파일과 위치가 다른데 왜 이 경로로 지정할까?
  
  @Override
  public int count(Integer bno) throws Exception {
    return session.selectOne(namespace+"count",bno);
  }
  
  @Override
  public int deleteAll(Integer bno) throws Exception {
    return session.delete(namespace + "deleteAll", bno);
  }
  
  @Override
  public int delete(Integer cno, String commenter) throws Exception {
    Map map = new HashMap();
    map.put("cno",cno);
    map.put("commenter",commenter);
    return session.delete(namespace+"delete",map);
  }
  
  @Override
  public int insert(CommentDto dto) throws Exception {
    return session.insert(namespace+"insert",dto);
  }
  
  @Override
  public List<CommentDto> selectAll(Integer bno) throws Exception {
    return session.selectList(namespace+"selectAll",bno);
  }
  
  @Override
  public CommentDto select(Integer cno) throws Exception {
    return session.selectOne(namespace + "select", cno);
  }
  
  @Override
  public int update(CommentDto dto) throws Exception {
    return session.update(namespace+"update",dto);
  }
}