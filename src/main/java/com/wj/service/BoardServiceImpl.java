package com.wj.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wj.domain.BoardVO;
import com.wj.mapper.BoardMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	private BoardMapper mapper;

	@Override
	public void register(BoardVO board) {
		System.out.println("register....");
		mapper.insert(board);
	}

	@Override
	public BoardVO get(Long id) {
		System.out.println("get...." + id);
		return mapper.read(id);
	}

	@Override
	public boolean modify(BoardVO board) {
		System.out.println("modify....");
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long id) {
		System.out.println("remove...");
		return mapper.delete(id) == 1;
	}

	@Override
	public List<BoardVO> getList() {
		System.out.println("getList......");
		return mapper.getList();
	}
}
