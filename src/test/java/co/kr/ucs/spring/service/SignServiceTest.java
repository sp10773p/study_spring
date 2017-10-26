package co.kr.ucs.spring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.kr.ucs.spring.vo.UserVO;
import static org.junit.Assert.*;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:config/beans.xml")
public class SignServiceTest {

	@Autowired
	SignService signService;
	
	@Test
	public void signUpTest() throws SQLException {
		signService.signUp("regId03", "테스트아이디", "1234", "seongdh@ucssystem.co.kr");
		
		int count = signService.getExistsUser("regId02");
		assertEquals(count, 1);
		
	}
	
	@Test
	public void getExistsUserTest() throws SQLException {
		int count = signService.getExistsUser("sp10773p");
		assertEquals(count, 1);
		
		count = signService.getExistsUser("sp10773p", "1234");
		assertEquals(count, 1);
	}
	
	@Test
	public void getUserTest() throws SQLException {
		UserVO user = signService.getUser("sp10773p", "1234");
		assertEquals(user.getUserId(), "sp10773p");
	}
}
