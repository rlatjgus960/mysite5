package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Repository
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public BoardVo getBoard(int no) {
		System.out.println("[BoardServce.getBoard()]");

		// 조회수 올리기
		int count = boardDao.updateHit(no);

		// 게시판 정보 가져오기
		BoardVo boardVo = boardDao.selectBoard(no);

		return boardVo;
	}

	// 글쓰기
	public int addContent(BoardVo boardVo) {
		System.out.println("[BoardServce.addContent()]");

		return boardDao.addContent(boardVo);
	}

	// 게시판 리스트 가져오기
	public List<BoardVo> getList() {

		System.out.println("[BoardServce.getList()]");

		return boardDao.getList();
	}

	//삭제
	public int deleteContent(BoardVo boardVo) {

		System.out.println("[BoardServce.deleteContent()]");

		return boardDao.deleteContent(boardVo);
	}
	
	//게시글 가져오기(수정폼)
	public BoardVo getContent(int no) {
		System.out.println("[BoardServce.getContent()]");

		BoardVo boardVo = boardDao.selectContent(no);

		return boardVo;
	}

	//수정
	public int updateContent(BoardVo boardVo) {
		
		return boardDao.updateContent(boardVo);
	}
}
