package com.memo.project.testProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {
	 @RequestMapping("/memo/loginView")
	    public String loginMainPage() {
	        return "/memo/loginView";
	    }
}
