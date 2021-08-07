package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping(value="/api/gallery")
public class ApiGalleryController {
	@Autowired
	private GalleryService galleryService;
	
	
	//이미지 한개 정보 가져오기
	@ResponseBody
	@RequestMapping(value = "/showImg", method = {RequestMethod.GET, RequestMethod.POST})
	public GalleryVo showImg(@RequestParam("no") int no) {
		System.out.println("[ApiGalleryController.showImg()]");
		System.out.println(no);
		
		GalleryVo galleryVo = galleryService.getImage(no);
		
		return galleryVo;
	}
	
	//이미지 삭제
	@ResponseBody
	@RequestMapping(value = "/delImg", method = {RequestMethod.GET, RequestMethod.POST})
	public int delImg(@ModelAttribute GalleryVo galleryVo) {
		
		System.out.println("[ApiGalleryController.delImg()]");
		System.out.println(galleryVo);
		
		int count = galleryService.delImg(galleryVo);
		System.out.println(count);

		return count;
	}

}
