package co.kr.ucs.spring.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import co.kr.ucs.spring.vo.UserVO;

@Service
public class SignService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int SignUp(String userId,String userPw,String userNm,String email) throws ClassNotFoundException {

		String InsertSql = "INSERT INTO CM_USER(USER_ID,USER_PW,USER_NM,EMAIL)values(?,?,?,?)";
		int flag = 0;
	
			flag = jdbcTemplate.update(InsertSql,new Object[] {userId,userPw,userNm,email});
			
			System.out.println("insert success");
	
		return flag;
	}
	

	public int Signchk(String userId, String userPw) throws ClassNotFoundException {
		
		Object[] sqlParameter = new Object[] {userId};
		
		String chkSql = "SELECT COUNT(*) FROM CM_USER WHERE USER_ID = ?";
		int flag = 0;
	
		if(userPw != null){
			sqlParameter = new Object[] {userId,userPw};
			chkSql += (" AND USER_PW = ?");
		}
		
			flag = jdbcTemplate.queryForObject(chkSql, sqlParameter, Integer.class);
			System.out.println("chk success");
	
		return flag;
	}
	

	public UserVO SignIn(String userId, String userPw) {

		String CheckSql = "SELECT * FROM CM_USER WHERE USER_ID=? AND USER_PW=?";
		
		return jdbcTemplate.queryForObject(CheckSql,new Object[]{userId,userPw},new RowMapper<UserVO>(){
					public UserVO mapRow(ResultSet rs, int rowCnt) throws SQLException{
						UserVO vo = new UserVO();
						vo.setUser_id(rs.getString("user_id"));
						vo.setUser_nm(rs.getString("user_nm"));
						
						return vo; 
						
					}
				});
		
//		Map<String, String> SImap = new HashMap<>();
//		SImap.put("USER_ID",vo2.getUser_id());
//		SImap.put("USER_PW",vo2.getUser_nm());
//		
//		return SImap;
	}

}
