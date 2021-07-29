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
}
