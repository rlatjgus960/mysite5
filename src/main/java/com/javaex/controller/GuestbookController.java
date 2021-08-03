package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	
	//리스트 가져오기
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("[GuestbookController.list()]");

		List<GuestbookVo> guestbookList = guestbookService.getList();

		model.addAttribute("gList", guestbookList);

		return "guestbook/addList";
	}
	
	//쓰기
	@RequestMapping(value="/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute GuestbookVo guestbookVo) {
		
		System.out.println("[GuestbookController.write()]");
		
		int count = guestbookService.guestbookWrite(guestbookVo);
		
		return "redirect:/guestbook/list";
	}
	
	//삭제폼
	@RequestMapping(value="/deleteForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm() {
		
		System.out.println("[GuestbookController.deleteForm()]");
		
		return "guestbook/deleteForm";
	}
	
	//삭제
	@RequestMapping(value="/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		
		System.out.println("[GuestbookController.delete()]");
		
		
		int count = guestbookService.guestbookDelete(guestbookVo);
		
		
		
		return "redirect:/guestbook/list";
	}
	
	
	//ajax 방명록 메인페이지
	@RequestMapping(value = "/ajaxMain", method = {RequestMethod.GET, RequestMethod.POST})
	public String ajaxMain() {
		System.out.println("[GuestbookController.ajaxMain()]");
		
		return "guestbook/ajaxList";
	}
}
