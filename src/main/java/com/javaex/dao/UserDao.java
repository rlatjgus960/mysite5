package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	//필드
	@Autowired
	private SqlSession sqlSession;
	//생성자
	//메소드-gs
	//메소드-일반
	public UserVo selectUser(UserVo userVo) {
		System.out.println("[UserDao.selectUser()]");
		System.out.println(userVo);
		
		return sqlSession.selectOne("user.selectUser", userVo);
	}
	
	
	public int insertUser(UserVo userVo) {
		System.out.println("[UserDao.insertUser()]");
		
		
		return sqlSession.insert("user.insertUser", userVo);
	}
	
	public UserVo getUserInfo(int no) {
		System.out.println("[UserDao.getUserInfo()]");
		
		return sqlSession.selectOne("user.getUserInfo", no);
	}
	
	public int modifyUser(UserVo userVo) {
		System.out.println("[UserDao.modifyUser()]");
		
		return sqlSession.update("user.modifyUser", userVo);
	}
	
	
	//ajax 아이디 중복체크용 vo 가져오기
	public UserVo selectUser(String id) {
		
		
		return sqlSession.selectOne("user.selectUserIdck", id);
	}
}
