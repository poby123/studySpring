package com.wj.mapper;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wj.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Test
	@Ignore
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}

	@Test
	@Ignore
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setWriter("Spring");
		board.setCategory("1-0");
		board.setFile(null);
		board.setContent("스프링에서 테스트");

		mapper.insert(board);
		log.info(board);
	}
	
	@Test
	@Ignore
	public void testRead() {
		BoardVO board = mapper.read(26L);
		log.info(board);
	}
	
	@Test
	@Ignore
	public void testDelete() {
		int count = mapper.delete(27L);
		log.info("DELETE COUNT : " + count);
	}
	
	@Test
	@Ignore
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setId(27L);
		board.setWriter("Spring");
		board.setCategory("1-0");
		board.setFile(null);
		board.setContent("스프링에서 테스트 Update");

		int count = mapper.update(board);
		log.info("Update Count : " + count);
	}

}
