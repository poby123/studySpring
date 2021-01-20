package com.wj.mapper;

import java.util.List;

import com.wj.domain.BoardVO;

public interface BoardMapper {
	public List<BoardVO> getList();

	public void insert(BoardVO board);

	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long id);
	
	public int delete(Long id);
	
	public int update(BoardVO board);
}
