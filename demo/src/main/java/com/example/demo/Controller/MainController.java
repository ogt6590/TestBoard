package com.example.demo.Controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Dao.IBoard;
import com.example.demo.Dto.BoardDto;

import ch.qos.logback.core.util.SystemInfo;
import paging.Criteria;
import paging.PaginationInfo;




@Controller
public class MainController {
	@Autowired
	IBoard boardDao;
	
	@Autowired
	BoardDto boardDto;
		
	@GetMapping("/")
	public String index(Model model) {		
		model.addAttribute("list",boardDao.list());
		return "redirect:list_criteria";
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
	
	//게시글 post 값 받는 컨트롤러 글작성부분
	@PostMapping("boardPost")
	public String post(@ModelAttribute BoardDto boardDto) throws IllegalStateException, IOException {
		
		String fileName=null;
		
		MultipartFile uploadFile = boardDto.getUploadFile();
		
		//파일이 있을경우 처리 if문
		if (!uploadFile.isEmpty()) {
			//사용자가 올린 파일이름 가져오기
			String originalFileName = uploadFile.getOriginalFilename();			
			String ext = FilenameUtils.getExtension(originalFileName);	//확장자 구하기
			//파일 진짜 이름 디비에 저장하기 위해 값넣기
			boardDto.setOriginalFileName(originalFileName);
			UUID uuid = UUID.randomUUID();	//UUID 구하기 uudi 란 정보식별을 위해 사용하는 id
			fileName=uuid+"."+ext;
			uploadFile.transferTo(new File("C:\\upload\\" + fileName));
		}
		
		boardDto.setFileName(fileName);
			
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
	public String list_criteria(@RequestParam(value="currentPageNo" , defaultValue="1") 
				int currentPageNo , Model model,@ModelAttribute("params") BoardDto params) {
			
		//총갯수
		int boardTotalCount = boardDao.selectBoardTotalCount(params);
		
		PaginationInfo paginationInfo = new PaginationInfo(params);
		
		Criteria cr=new Criteria();
		
		paginationInfo.setTotalRecordCount(boardTotalCount);

		params.setPaginationInfo(paginationInfo);
		
		//페이징 데이터 가져오기
		model.addAttribute("list",boardDao.selectBoardList(params));
		
		model.addAttribute("cr",cr);
		
		model.addAttribute("pa",paginationInfo);
				
		return "list_criteria";
	}

	
}