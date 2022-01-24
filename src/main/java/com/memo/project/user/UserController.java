package com.memo.project.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	//회원가입 view로 이동
	 @GetMapping("/singup_view")
	    public String loginMainPage() {
	        return "/user/signUp";
	    }
}
