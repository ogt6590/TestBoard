package com.example.demo.Dao;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.Dto.BoardDto;

@Repository
@Mapper
public interface IBoard {

	public List<BoardDto> list();
	public int insert (BoardDto boardDto);
	public BoardDto view (int board_num);
	
}
