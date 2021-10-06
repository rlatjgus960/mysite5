package com.javaex.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
	
	
	public List<GuestbookVo> getList() {
		
		System.out.println("[GuestbookService.getList()]");
		
		return guestbookDao.getList();
	}
	
	
	public int guestbookWrite(GuestbookVo guestbookVo) {
		
		System.out.println("[GuestbookService.guestbookWrite()]");
		
		return guestbookDao.guestbookInsert(guestbookVo);
	}
	
	public int guestbookDelete(GuestbookVo guestbookVo) {
		
		System.out.println("[GuestbookService.guestbookDelete()]");
		
		return guestbookDao.guestbookDelete(guestbookVo);
	}
	
	//방명록 글 저장, 게시글 가져오기 - ajax
	public GuestbookVo writeResultVo(GuestbookVo guestbookVo) {
		System.out.println("[GuestbookService.writeResultVo()]");
		
		//글저장
		System.out.println(guestbookVo); //no가 없다
		int count = guestbookDao.insertGuestbookKey(guestbookVo);
		System.out.println(guestbookVo); //no가 있다
		
		int no = guestbookVo.getNo();
		
		//글가져오기(방금 등록한 번호)
		GuestbookVo resultVo = guestbookDao.selectGuestbook(no);
		
		return resultVo;
	}
	
	//no값으로 no의 모든 정보 가져오기
	public GuestbookVo readGuest(int no) {
		
		System.out.println("[GuestbookService.readGuest()]");
		
		return guestbookDao.selectGuestbook(no);
	}
	
}
