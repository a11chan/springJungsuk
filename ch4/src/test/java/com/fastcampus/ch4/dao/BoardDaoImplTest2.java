package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardDaoImplTest2 {
    @Autowired
    private BoardDao boardDao;
   
    @Test
    public void insertTestData() throws Exception {
      boardDao.deleteAll();
      for (int i = 1 ; i<= 220 ; i++) {
        BoardDto boardDto = new BoardDto("title"+i, "no content", "asdf");
        boardDao.insert(boardDto);
      }
    }
}