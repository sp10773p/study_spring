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
//(locations={"file:src/main/resources/config/beans.xml"})

public class SignServiceTest {

	@Autowired
	SignService signService;
	
	@Test
	public void SignUpTest() throws SQLException, ClassNotFoundException {
		
		signService.SignUp("qwwe22","다람쥐","qqq","darami@naver.co.kr");
		
		int count = signService.Signchk("qwwee22",null);
		assertEquals(count, 1);
		
	}
	
	@Test
	public void SignchkTest() throws SQLException, ClassNotFoundException {
		
		int count = signService.Signchk("ccc",null);
		assertEquals(count, 1);
		count = signService.Signchk("ccc","ccc");
		assertEquals(count, 1);
	}
	
	@Test
	public void getUserTest() throws SQLException, ClassNotFoundException {
	
		UserVO user = signService.SignIn("ccc","ccc");
		assertEquals(user.getUser_id(), "ccc");
	}
}