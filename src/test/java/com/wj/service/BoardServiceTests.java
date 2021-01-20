package com.wj.service;

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
public class BoardServiceTests {
	@Setter(onMethod_ = @Autowired)
	private BoardService service;

	@Test
	public void testExist() {
		log.info(service);
	}

	@Test
	@Ignore
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setWriter("Spring");
		board.setCategory("1-0");
		board.setFile(null);
		board.setContent("스프링에서 테스트");

		service.register(board);
	}

	@Test
	@Ignore
	public void testGet() {
		log.info(service.get(28L));
	}

	@Test
	@Ignore
	public void testModify() {
		BoardVO board = service.get(28L);
		
		if(board == null) {
			return;
		}
		
		board.setContent("This is editted content");
		log.info("Modify Result : " + service.modify(board));
	}
	
	@Test
	@Ignore
	public void testRemove() {
		log.info("Remove result : " + service.remove(29L));
	}
	
	@Test
	@Ignore
	public void testGetList() {
		service.getList().forEach(board -> log.info(board));
	}
}
