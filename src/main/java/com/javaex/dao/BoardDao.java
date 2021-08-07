package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//전체 게시물 갯수 구하기
	public int selectTotalCnt(String keyword) {
		System.out.println("[BoardDao.selectTotalCnt()]");
		
		
		return sqlSession.selectOne("board.selectTotalCnt", keyword);
	}
	
	//게시판 페이징 연습용 리스트
	public List<BoardVo> selectList2(int startRnum, int endRnum, String keyword) {
		System.out.println("[BoardDao.selectList2()]");
		System.out.println(startRnum);
		System.out.println(endRnum);
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("startRnum", startRnum);
		pMap.put("endRnum", endRnum);
		pMap.put("keyword", keyword);
		
		System.out.println(pMap);
		
		List<BoardVo> boardList = sqlSession.selectList("board.selectList2", pMap);
		
		
		return boardList;
	}
	
	
	
	//게시판 리스트 가져오기
	public List<BoardVo> getList(String keyword) {
		System.out.println("[BoardDao.getList()]");
		
		return sqlSession.selectList("board.getList", keyword);
	}
	
	//삭제
	public int deleteContent(BoardVo boardVo) {
		
		System.out.println("[BoardDao.addContent()]");
		
		return sqlSession.delete("deleteContent",boardVo);
	}
	
	
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
	
	
	
	//게시글 가져오기(수정폼)
	public BoardVo selectContent(int no) {
		System.out.println("[BoardDao.selectContent()]");
		System.out.println(no);
		
		BoardVo boardVo = sqlSession.selectOne("selectBoard", no);
		
		System.out.println(boardVo);
				
		return boardVo;
	}
	
	//수정
	public int updateContent(BoardVo boardVo) {
		System.out.println("[BoardDao.updateContent()]");
		
		return sqlSession.update("updateContent", boardVo);
	}

}
