package com.wj.persistance;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class JDBCTests {
	@Inject
	private DataSource ds;
	
	@Test
	@Ignore
	public void testConnection() throws Exception {
		try {
			Connection con = ds.getConnection();
			assertNotNull(con);
			log.info(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
