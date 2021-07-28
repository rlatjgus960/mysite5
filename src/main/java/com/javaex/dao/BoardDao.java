package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	

	
	//조회수 올리기
	public int updateHit(int no) {
		System.out.println("[BoardDao.updateHit()]");
		
		//System.out.println(no);
		
		int count = sqlSession.update("board.updateHit", no);
		//System.out.println(count);
		
		return count;
	}
	
	//게시판 1개 정보 가져오기
	public BoardVo selectBoard(int no) {
		System.out.println("[BoardDao.selectBoard()]");
		
		//System.out.println(no);
		
		BoardVo boardVo = sqlSession.selectOne("selectBoard", no); //쿼리문 작성시 vo랑 컬럼명이랑 맞춰주기
		System.out.println(boardVo);
		
		return boardVo;
	}
	
	
	//글쓰기
	public int addContent(BoardVo boardVo) {
		
		System.out.println("[BoardDao.addContent()]");
		//System.out.println(boardVo);
		
		return sqlSession.insert("insertContent",boardVo);
	}
	
	
	//게시판 리스트 가져오기
	public List<BoardVo> getList() {
		System.out.println("[BoardDao.getList()]");
		
		return sqlSession.selectList("board.getList");
	}
	
	//삭제
	public int deleteContent(BoardVo boardVo) {
		
		System.out.println("[BoardDao.addContent()]");
		
		return sqlSession.delete("deleteContent",boardVo);
	}
	
	
	//게시글 가져오기(수정폼)
	public BoardVo selectContent(int no) {
		System.out.println("[BoardDao.selectContent()]");
		System.out.println(no);
		
		BoardVo boardVo = sqlSession.selectOne("selectContent", no);
		
		System.out.println(boardVo);
				
		return boardVo;
	}
	
	//수정
	public int updateContent(BoardVo boardVo) {
		System.out.println("[BoardDao.selectContent()]");
		
		return sqlSession.update("updateContent", boardVo);
	}

}
