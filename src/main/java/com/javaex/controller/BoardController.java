package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 글 읽기
	@RequestMapping(value = "/board/read", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(@RequestParam("no") int no, Model model) {

		System.out.println("[BoardController.read()]");

		BoardVo boardVo = boardService.getBoard(no);

		model.addAttribute("boardVo", boardVo);

		return "board/read";
	}

	// 글쓰기폼
	@RequestMapping(value = "/board/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm(HttpSession session) {
		System.out.println("[BoardController.writeForm()]");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		//로그인 안한경우 --> 메인
		if(authUser == null) {
			System.out.println("로그인 안한경우");
			return "redirect:/board/list";
		}

		return "board/writeForm";
	}

	// 글쓰기
	@RequestMapping(value = "/board/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
		System.out.println("[BoardController.write()]");

		boardVo.setUserNo(((UserVo) session.getAttribute("authUser")).getNo());

		boardService.addContent(boardVo);

		return "redirect:/board/list";
	}

	// 리스트
	@RequestMapping(value = "/board/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model, @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {

		System.out.println("[BoardController.list()]");

		

		// System.out.println(boardList);
		
		List<BoardVo> boardList = boardService.getList(keyword); 
		//키워드 있거나 없거나 돌아가게 저렇게 받아서.. 겟리스트로 String형태로 계속 넘겨줌, 쿼리문이 받아서 처리.. 스트링형 (주소값 형태, set,get 없음) 받아서 쓰는게 value
		
		model.addAttribute("boardList", boardList);

		return "board/list";
	}

	// 삭제
	@RequestMapping(value = "/board/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@ModelAttribute BoardVo boardVo) {

		System.out.println("[BoardController.delete()]");

		// BoardVo boardVo = new BoardVo(no, ((UserVo)session.getAttribute("authUser")).getNo());

		int count = boardService.deleteContent(boardVo);

		return "redirect:/board/list";
	}

	// 수정폼
	@RequestMapping(value = "/board/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@RequestParam("no") int no, Model model, HttpSession session) {
		System.out.println("[BoardController.modifyForm()]");

		BoardVo boardVo = boardService.getContent(no);
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");

		//로그인 안한경우 --> 메인
		if(authUser == null) {
			System.out.println("로그인 안한경우");
			return "redirect:/main";
		}
		
		//로그인한 사용자가 자신의 글만 수정할 수 있도록 하기, 다른 게시글 접근해서 수정 못하게 하기
		if(authUser.getNo() == boardVo.getUserNo()) { //로그인한 사용자 == 글 작성자
			System.out.println("자신의 글인 경우-->수정폼 포워드");
			model.addAttribute("boardVo", boardVo);
			return "board/modifyForm";
		}else {
			System.out.println("다른사람 글인 경우");
			return "redirect:/board/list";
		}
		
	}

	// 수정
	@RequestMapping(value = "/board/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute BoardVo boardVo) {

		System.out.println(boardVo);
		
		int count = boardService.updateContent(boardVo);

		return "redirect:/board/list";
	}
	

}
