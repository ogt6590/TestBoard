package com.example.demo.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Dao.IBoard;
import com.example.demo.Dto.BoardDto;

import paging.Criteria;

@Controller
public class MainController {
	@Autowired
	IBoard boardDao;
	
	@Autowired
	BoardDto boardDto;
		
	@GetMapping("/")
	public String index(Model model) {		
		model.addAttribute("list",boardDao.list());
		return "board";
	}

	@GetMapping("boardWrite")
	public String write() {
		return "boardWrite";
	}
	
	@GetMapping("boardView")
	public String View(@RequestParam("board_num") int board_num,Model model) {
		model.addAttribute("board",boardDao.view(board_num));
		return "boardView";		
	}
	
	//게시글 post 값 받는 컨트롤러
	@PostMapping("boardPost")
	public String post(@ModelAttribute BoardDto boardDto) {
		boardDao.insert(boardDto);
		
		return "redirect:/";
	}
	
	@PostMapping("boardDelete")
	public String delete(HttpServletRequest request) {
		String result = request.getParameter("board_num");
		int board_num  = Integer.parseInt(result);
		boardDao.delete(board_num);
		return "redirect:/";
	}
	
	@PostMapping("boardModify")
	public String Modify(HttpServletRequest request,Model model) {
		String result = request.getParameter("board_num");
		int board_num  = Integer.parseInt(result);
		model.addAttribute("board",boardDao.view(board_num));
		//수정하려는 게시글 정보가져오기
		return "boardModify";
		//수정페이지로 이동
	}
	
	@PostMapping("modify")
	public String modify(HttpServletRequest request) {		
		String result = request.getParameter("board_num");
		boardDto.setBoard_num(Integer.parseInt(result));
		boardDto.setContent(request.getParameter("content"));	
		boardDao.update(boardDto);
		return "redirect:/";
	}
	
	@GetMapping("/list_criteria")
	public String list_criteria(Model model,@ModelAttribute("criteria") Criteria criteria) {
		model.addAttribute("list",boardDao.selectBoardList(criteria));
		return "list_criteria";
	}

	
}