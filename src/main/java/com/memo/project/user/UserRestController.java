package com.memo.project.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.memo.project.user.bo.UserBO;
import com.memo.project.user.model.User;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserBO userBO;
	
	@PostMapping("/sign_up")
	public Map<String, String> signUp( //return type이 map인 이유는 설계문서의 응답값을 map으로 설정해서
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email
			) {
		
		int count = userBO.addUser(loginId, password, name, email);
		
		// json타입으로 return / ex) {"result":"success"}
		Map<String, String> result = new HashMap<>();
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		return result;
	}
	
	// 로그인 - 세션에 값을 저장하는 과정
	@PostMapping("/sign_in")
	public Map<String, String> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request					//HttpServletRequest request객체를 추가해서 session 객체를 가져올 수 있다.
			){
		User user = userBO.getUser(loginId, password);	// user 정보 모델 객체로 통채로 가져올 생각
		Map<String, String> result = new HashMap<>();
		
		// 일치하는 결과가 없으면 user에 null이 들어가있음
		if(user != null) {
			//로그인 성공
			result.put("result", "success");
			HttpSession session = request.getSession(); // 세션 객체 생성
			
			// id, loginId, name을 session에 저장
			session.setAttribute("userId", user.getId()); //session객체에 key(userId)에 user의 getId에서 가져온 값을 value로 저장
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
			
		} else {
			//로그인 실패
			result.put("result", "fail");
		}
		
		return result;
	}
}
