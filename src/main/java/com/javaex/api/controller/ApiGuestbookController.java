package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/api/guestbook")
public class ApiGuestbookController {
	@Autowired
	private GuestbookService guestbookService;

	// ajax 리스트 가져오기
	@ResponseBody // 데이터를 바디에 넣어서 보내는것, 주소만 넣으면 안되고 데이터도 같이 넣어서
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public List<GuestbookVo> List() {
		System.out.println("[ApiGuestbookController.list()]");

		List<GuestbookVo> guestbookList = guestbookService.getList();
		System.out.println(guestbookList);

		return guestbookList; // json 형식으로 번역해서 보내줘야함
	}

	// ajax 방명록 저장
	@ResponseBody // 이거 없으면 resultVo.jsp로 보내라고 알아먹기때문에...
	@RequestMapping(value = "/write2", method = { RequestMethod.GET, RequestMethod.POST })
	public GuestbookVo write(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("[ApiGuestbookController.write2()]");

		System.out.println(guestbookVo);

		GuestbookVo resultVo = guestbookService.writeResultVo(guestbookVo);

		return resultVo;
	}

	// ajax 방명록 삭제
	@ResponseBody
	@RequestMapping(value = "/remove", method = { RequestMethod.GET, RequestMethod.POST })
	public int remove(@ModelAttribute GuestbookVo guestbookVo) {

		System.out.println("[ApiGuestbookController.remove()]");

		System.out.println(guestbookVo);

		int count = guestbookService.guestbookDelete(guestbookVo);
		System.out.println(count);

		return count;
	}

	// 안드로이드 방명록 글 1개 가져오기
	@ResponseBody
	@RequestMapping(value = "/read", method = { RequestMethod.GET, RequestMethod.POST })
	public GuestbookVo read(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("[ApiGuestbookController.read()]");
		System.out.println(guestbookVo);

		GuestbookVo resultVo = guestbookService.readGuest(guestbookVo.getNo());
		System.out.println(resultVo);
		return resultVo;
	}

}
