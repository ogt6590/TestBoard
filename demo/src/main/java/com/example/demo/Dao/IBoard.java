package com.example.demo.Dao;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.Dto.BoardDto;

import paging.Criteria;

@Repository
@Mapper
public interface IBoard {

	public List<BoardDto> list();
	public int insert (BoardDto boardDto);
	public BoardDto view (int board_num);
	public int delete (int board_num);
	public int update (BoardDto boardDto);
	//페이징 처리 해서 게시글가져오기
	public List<BoardDto> selectBoardList(BoardDto boardDto);
	//데이터 총개수 구하기
	public int selectBoardTotalCount(BoardDto boardDto);
	//저장된 파일이름 구하기
	public String filename(String filename);
	
	

}
