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

@Controller
public class MainController {
	
	@Autowired
	IBoard boardDao;
		
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
		
}