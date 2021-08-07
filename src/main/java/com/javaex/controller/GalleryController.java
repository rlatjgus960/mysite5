package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("[GalleryController.list()]");
		
		List<GalleryVo> galleryList = galleryService.getGalleryList();
		
		model.addAttribute("gList", galleryList);

		return "gallery/list";
	}

	@RequestMapping(value = "/imgUpload", method = { RequestMethod.GET, RequestMethod.POST })
	public String imgUpload(Model model, @RequestParam("content") String content,
			@RequestParam("file") MultipartFile file, HttpSession session) {

		System.out.println("[GallaryController.imgUpload()]");

		int userNo = ((UserVo) session.getAttribute("authUser")).getNo();

		int count = galleryService.restore(file, content, userNo);

		return "redirect:/gallery/list";
	}

}
