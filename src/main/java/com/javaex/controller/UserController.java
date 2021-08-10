package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class UserController {

	// 필드
	@Autowired
	private UserService userService;

	// 로그인폼
	@RequestMapping(value = "/user/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("[UserController.loginForm()]");

		return "user/loginForm";
	}

	// 로그인
	@RequestMapping(value = "/user/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.login()]");

		UserVo authUser = userService.getUser(userVo);

		if (authUser != null) { // 로그인 성공하면(authUser null 이 아니면)
			System.out.println("[로그인성공]");
			session.setAttribute("authUser", authUser);
			return "redirect:/main";
		} else { // 로그인 실패하면(authUser null 이면 )
			System.out.println("[로그인실패]");
			return "redirect:/user/loginForm?result=fail";
		}

	}
	
	//로그아웃
	@RequestMapping(value="/user/logout", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		
		System.out.println("[UserController.logout()]");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/main";
	}

	
	
	
	//회원가입폼
	@RequestMapping(value="/user/joinForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("[UserController.joinForm()]");
		
		return "user/joinForm";
	}
	
	//회원가입
	@RequestMapping(value="/user/join", method= {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("[UserController.join()]");
		
		userService.insertUser(userVo);
		
		return "user/joinOk";
	}
	
	
	
	//회원정보 수정폼
	@RequestMapping(value="/user/modifyForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("[UserController.modifyForm()]");
		
		UserVo userVo = userService.getUserInfo(((UserVo)session.getAttribute("authUser")).getNo());
		
		model.addAttribute("UserVo", userVo);
		
		return "user/modifyForm";
	}
	
	
	
	
	//회원정보 수정
	@RequestMapping(value="/user/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.modify()]");
		
		System.out.println(userVo);
		userService.modifyUser(userVo);
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		UserVo uVo = userService.getUserInfo(authUser.getNo());
		
		authUser.setName(uVo.getName());
		
		
		return "redirect:/main";
	}
	
	
	//ajax 아이디 중복체크
	@ResponseBody
	@RequestMapping(value="/user/idCheck", method = {RequestMethod.GET, RequestMethod.POST})
	public boolean idCheck(@RequestParam("id") String id) {
		
		System.out.println("[UserController.idCheck()]");
		System.out.println(id);
		
		return userService.getUser(id);
	}
	
	/* json 방식으로 데이터 받기 */
	@ResponseBody
	@RequestMapping(value = "/user/join2", method = {RequestMethod.GET, RequestMethod.POST})
	public int join2(@RequestBody UserVo userVo) {
		System.out.println("[UserController.join2()]");
		System.out.println(userVo);
		
		//서비스를 통해 회원정보 저장
		return userService.insertUser(userVo);
	}
	
	
	
}
