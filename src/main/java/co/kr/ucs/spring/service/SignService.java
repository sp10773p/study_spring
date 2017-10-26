package co.kr.ucs.spring.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import co.kr.ucs.spring.dao.DBConnectionPool;
import co.kr.ucs.spring.vo.UserVO;

public class SignService {
	
	@Autowired
	DBConnectionPool dbPool;
	
	public UserVO getUser(String userId, String userPw)throws SQLException {
		UserVO userVO = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = dbPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT USER_ID, USER_NM, USER_PW, EMAIL FROM CM_USER WHERE USER_ID = ?");
			sql.append(" AND USER_PW = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			
			rs = pstmt.executeQuery();
			
			System.out.print("쿼리실행 : ");
			System.out.println(sql.toString());
			System.out.print("파라미터 : ");
			System.out.println(userId);
			
			if(rs.next()) {
				userVO = new UserVO();
				userVO.setUserId(rs.getString("USER_ID"));
				userVO.setUserNm(rs.getString("USER_NM"));
				userVO.setUserPw(rs.getString("USER_PW"));
				userVO.setEmail(rs.getString("EMAIL"));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}finally{
			dbPool.freeConnection(conn);
			if(rs != null) try{rs.close();}catch(Exception ex){}
			if(pstmt != null) try{pstmt.close();}catch(Exception ex){}
		}
		
		return userVO;
		
	}
	
	
	public int getExistsUser(String userId)throws SQLException{
		return getExistsUser(userId, null);
	}

	public int getExistsUser(String userId, String userPw)throws SQLException{
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = dbPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(*) FROM CM_USER WHERE USER_ID = ?");
			
			if(userPw != null){
				sql.append(" AND USER_PW = ?");
			}
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			if(userPw != null){
				pstmt.setString(2, userPw);
			}
			
			rs = pstmt.executeQuery();
			
			System.out.print("쿼리실행 : ");
			System.out.println(sql.toString());
			System.out.print("파라미터 : ");
			System.out.println(userId);
			
			if(rs.next()) count = rs.getInt(1);
			
			System.out.println("결과 : " + count);
		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}finally{
			dbPool.freeConnection(conn);
			if(rs != null) try{rs.close();}catch(Exception ex){}
			if(pstmt != null) try{pstmt.close();}catch(Exception ex){}
		}
		
		return count;
		
	}
	
	public void signUp(String userId, String userNm, String userPw, String email)throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = dbPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO CM_USER (");
			sql.append("USER_ID, USER_NM, USER_PW, EMAIL");
			sql.append(")VALUES(");
			sql.append("?, ? ,?, ?)");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);	
			pstmt.setString(2, userNm);	
			pstmt.setString(3, userPw);	
			pstmt.setString(4, email);
			
			System.out.print("쿼리실행 : ");
			System.out.println(sql.toString());
			System.out.print("파라미터 : ");
			
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			throw e;
		}finally{
			dbPool.freeConnection(conn);
			if(pstmt != null) try{pstmt.close();}catch(Exception ex){}
		}
	}
}
