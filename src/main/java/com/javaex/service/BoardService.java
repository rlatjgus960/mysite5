package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Repository
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	
	
	//게시판 페이징 연습용 리스트
	   public Map<String, Object> getList2(int crtPage, String keyword) {
	      System.out.println("[BoardService.getList2()]");
	      System.out.println(crtPage);
	      
	      ////////////////////////////////////////////////////
	      //// 리스트 가져오기
	      ////////////////////////////////////////////////////
	      int listCnt = 10; 
	      
	      //crtPage 계산(음수일때 1page로 처리)
	      /*
	      if(crtPage > 0) {
	    	  //crtPage = crtPage;
	      }else {
	    	  crtPage=1;
	      }
	      */
	      
	     crtPage = (crtPage > 0) ? crtPage : (crtPage=1);
	     //여기에       이거면         이거담고 아니면  이거담아라 (삼항연산자)
	      
	      
	      //게시글 목록 시작 번호 계산하기
	      int startRnum = (crtPage-1)*listCnt + 1; 
	            
	      //게시글 목록 끝 번호 계산하기
	      int endRnum = (startRnum+listCnt)-1;
	      
	      List<BoardVo> boardList = boardDao.selectList2(startRnum, endRnum, keyword);
	      
	      
		  ////////////////////////////////////////////////////
		  //// 페이징 계산 
		  ////////////////////////////////////////////////////
	      
	      int totalCount = boardDao.selectTotalCnt(keyword); //전체 게시물 갯수 구하기, 검색기능 추가하면서 키워드 추가
	      System.out.println(totalCount);
	      
	      //페이지당 버튼 갯수
	      int pageBtnCount = 5;
	      
	      //화면에 보이는 페이지 버튼 끝 번호
	      //1 --> 1~5
	      //2 --> 1~5
	      //3 --> 1~5
	      //6 --> 6~10
	      //7 --> 6~10
	      int endPageBtnNo = (int)Math.ceil((crtPage/(double)pageBtnCount))*pageBtnCount; //현재 페이지 번호를 페이지버튼수로 나누고 올림 해준다음 다시 페이지버튼수 곱해주기 
	      
	      //화면에 보이는 페이지 버튼 시작 번호
	      int startPageBtnNo = endPageBtnNo - (pageBtnCount-1);
	      
	      //다음 화살표 유무
	      boolean next = false;
	      if((endPageBtnNo * listCnt) < totalCount) {
	    	next  = true;
	      }else {
	    	  endPageBtnNo = (int)Math.ceil(totalCount/(double)listCnt);
	      }				//					127 / 10.0 --> 12.7 : 올림처리해서 13으로 만들어줌, 정수형 변환(페이지 13까지 보이게)
	      
	      //이전 화살표 유무
	      boolean prev = false;
	      if(startPageBtnNo != 1) {
	    	 prev  = true;
	      }
	      
	      
	      //리턴하기
	      //다 담을수 있는 vo 만들거나 맵으로 묶어서 보내주기
	      Map<String, Object> listMap = new HashMap<String, Object>();
	      listMap.put("boardList", boardList);
	      listMap.put("prev", prev);
	      listMap.put("startPageBtnNo", startPageBtnNo);
	      listMap.put("endPageBtnNo", endPageBtnNo);
	      listMap.put("next", next);
	      
	      return listMap;
	   }
	
	
	// 게시판 리스트 가져오기
	public List<BoardVo> getList(String keyword) {

		System.out.println("[BoardServce.getList()]");

		return boardDao.getList(keyword);
	}
	
	
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

		/*
		 * for(int i=0; i<127; i++) { boardVo.setTitle(i+"번째 제목입니다.");
		 * boardVo.setContent(i+"번째 내용입니다.");
		 * 
		 * boardDao.addContent(boardVo); }
		 */
		
		return boardDao.addContent(boardVo);
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
