package com.memo.project.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {		// view랑 연결하는 컨트롤러
	
	//회원가입 view로 이동
	 @GetMapping("/signup_view")
	    public String signupView() {
	        return "/user/signUp";
	    }
	 
	 //로그인 view로 이동
	 @GetMapping("/signin_view")
		 public String signinView() {
			 return "/user/signIn";
		 }
	 
	 // 로그아웃 - 세션을 제거
	 @GetMapping("/sign_out")
	 public String signOut(HttpServletRequest request) {		// session에 있는 유저정보를 지워서 로그아웃
		 HttpSession session = request.getSession();
		 
		 // session에 회원 정보 제거(restController에서 저장했던 내용들만 제거)
		 session.removeAttribute("userId");
		 session.removeAttribute("userLoginId");
		 session.removeAttribute("userName");
		 
		 return "redirect:/user/signin_view";		// 로그인 화면으로 이동시킨다
	 }
	 
}
