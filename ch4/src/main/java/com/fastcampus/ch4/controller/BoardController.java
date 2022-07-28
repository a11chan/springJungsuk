package com.fastcampus.ch4.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.PageHandler;
import com.fastcampus.ch4.domain.SearchCondition;
import com.fastcampus.ch4.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
  
  @Autowired
  BoardService boardService;
  
  @PostMapping("/modify")
  public String modify(
      BoardDto boardDto, HttpSession session
      , RedirectAttributes rattr, Model m
      , Integer page, Integer pageSize
      ) {
    String writer = (String) session.getAttribute("id");
    // getAttribute가 Object를 반환하므로 형변환 필요
    boardDto.setWriter(writer);
    
    try {
      
      int rowCnt = boardService.modify(boardDto);
      
      if(rowCnt != 1)
        throw new Exception("board modify error");
      
      rattr.addAttribute("page",page);
      rattr.addAttribute("pageSize",pageSize);
      rattr.addFlashAttribute("msg","MOD_OK");
      return "redirect:/board/list";
      
    } catch (Exception e) {
      e.printStackTrace();
//    m.addAttribute("boardDto",boardDto); //아래와 동일
      m.addAttribute(boardDto);
//    rattr.addFlashAttribute("msg","MOD_ERR"); // 전달 안 됨, RedirectAttributes 이름에서 볼 수 있듯이 redirect 사용 시에 적용 
      m.addAttribute("msg","MOD_ERR");
      m.addAttribute("page",page);
      m.addAttribute("pageSize",pageSize);
      return "board"; // 등록하려던 내용 보여줘야 함
    }
  }
  
  @PostMapping("/write")
  public String write(
      BoardDto boardDto, HttpSession session
      , RedirectAttributes rattr, Model m) {
    String writer = (String) session.getAttribute("id");
    // getAttribute가 Object를 반환하므로 형변환 필요
    boardDto.setWriter(writer);
    
    try {
      
      int rowCnt = boardService.write(boardDto);
      
      if(rowCnt != 1)
        throw new Exception("board write error");
      
      rattr.addFlashAttribute("msg","WRT_OK");
      
      return "redirect:/board/list";
      
    } catch (Exception e) {
      e.printStackTrace();
//    m.addAttribute("boardDto",boardDto); //아래와 동일
      m.addAttribute(boardDto);
//    rattr.addFlashAttribute("msg","WRT_ERR"); // 전달 안 됨, RedirectAttributes 이름에서 볼 수 있듯이 redirect 사용 시에 적용 
      m.addAttribute("msg","WRT_ERR");
      return "board";
    }
  }
  
  @GetMapping("/write")
  public String write(Model m) {
    m.addAttribute("mode","new");
    return "board";
  }
  
  @PostMapping("/remove")
  public String remove(Integer bno, Integer page, Integer pageSize
      , Model m, HttpSession session, RedirectAttributes rattr) {
    String writer = (String) session.getAttribute("id");
    try {
      if(boardService.remove(bno, writer) != 1)
        throw new Exception("board remove error");
    } catch (Exception e) {
      e.printStackTrace();
      rattr.addFlashAttribute("msg","DEL_ERR");
    }
    
    rattr.addAttribute("page", page);
    rattr.addAttribute("pageSize", pageSize);
    rattr.addFlashAttribute("msg","DEL_OK");
    
    return "redirect:/board/list";
  }
  
  @GetMapping("/read")
  public String read(Integer bno, Integer page, Integer pageSize, Model m) {
    try {
      BoardDto boardDto = boardService.read(bno);
      m.addAttribute(boardDto);
      m.addAttribute("page",page);
      m.addAttribute("pageSize",pageSize);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "board";
  }
  
  @GetMapping("/list")
  public String list(SearchCondition sc, Model m, HttpServletRequest request) {
    if(!loginCheck(request))
      return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

    try {
      int totalCnt = boardService.getSearchResultCnt(sc);
      m.addAttribute("totalCnt",totalCnt);
      
      PageHandler pageHandler = new PageHandler(totalCnt, sc);
    
      List<BoardDto> list = boardService.getSearchResultPage(sc);
      m.addAttribute("list",list);
      m.addAttribute("ph", pageHandler);
    } catch (Exception e) {
      e.printStackTrace();
      m.addAttribute("msg","LIST_ERR");
      m.addAttribute("totalCnt",0);
    }
    
    return "boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
  }

  private boolean loginCheck(HttpServletRequest request) {
    // 1. 세션을 얻어서
    HttpSession session = request.getSession();
    // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
    return session.getAttribute("id")!=null;
  }
}