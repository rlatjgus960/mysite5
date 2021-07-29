package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> getList() {
		
		System.out.println("[GuestbookDao.getList()]");
		
		List<GuestbookVo> glist = sqlSession.selectList("getGuestbookList");
		
		//System.out.println(glist);
		
		return glist;
	}
	
	
	public int guestbookInsert(GuestbookVo guestbookVo) {
		System.out.println("[GuestbookDao.guestbookInsert()]");
		
		return sqlSession.insert("guestbookInsert", guestbookVo);
	}
	
	public int guestbookDelete(GuestbookVo guestbookVo) {
		System.out.println("[GuestbookDao.guestbookDelete()]");
		
		return sqlSession.delete("guestbookDelete", guestbookVo);
	}

}
