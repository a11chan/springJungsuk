package com.fastcampus.ch4.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.ch4.dao.BoardDao;
import com.fastcampus.ch4.dao.CommentDao;
import com.fastcampus.ch4.domain.CommentDto;

@Service
public class CommentServiceImpl implements CommentService {

  CommentDao commentDao;
  BoardDao boardDao;

//@Autowired //생성자가 1개일 때 생략 가능
  public CommentServiceImpl(CommentDao commentDao, BoardDao boardDao) {
    this.commentDao = commentDao;
    this.boardDao = boardDao;
  }

  @Override
  public int getCount(Integer bno) throws Exception {
    return commentDao.count(bno);
  }

  @Override
  public List<CommentDto> getList(Integer bno) throws Exception {
//    throw new Exception("test");
    return commentDao.selectAll(bno);
  }

  @Override
  public CommentDto read(Integer cno) throws Exception {
    return commentDao.select(cno);
  }

  @Override
  public int modify(CommentDto commentDto) throws Exception {
    return commentDao.update(commentDto);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public int remove(Integer cno, Integer bno, String commenter) throws Exception {
    int rowCnt = boardDao.updateCommentCnt(bno, -1);
//    throw new Exception("test");
    rowCnt = commentDao.delete(cno, commenter);
    return rowCnt;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public int write(CommentDto commentDto) throws Exception {
    boardDao.updateCommentCnt(commentDto.getBno(), 1);
//    throw new Exception("test");
    return commentDao.insert(commentDto);
  }
}