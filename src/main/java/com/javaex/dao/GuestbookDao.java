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
	
	//방명록 글 저장 - ajax
	public int insertGuestbookKey(GuestbookVo guestbookVo) {
		System.out.println("[GuestbookDao.insertGuestbookKey()]");
		
		return sqlSession.insert("guestbook.insertGuestbookKey", guestbookVo);
	}

	//방명록 글 가져오기 - ajax
	public GuestbookVo selectGuestbook(int no) {
		System.out.println("[guestbookDao.selectGuestbook()]");
		return sqlSession.selectOne("guestbook.selectGuestbook", no);
	}
}
