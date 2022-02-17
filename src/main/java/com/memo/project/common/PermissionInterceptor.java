package com.memo.project.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component 
public class PermissionInterceptor implements HandlerInterceptor{
	
	// 요청이 들어 올 때
	@Override
	public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		
		//로그인 정보
		HttpSession session = request.getSession();
		
		// 현재 요청한 url의 path (uri)   		ex) /user/signin_view
		String uri = request.getRequestURI();
		
		if (session.getAttribute("userId") != null) {	// 로그인 상태
			// 로그인 화면과 회원가입화면 접근 금지
			// 리스트 화면으로 이동
			if(uri.startsWith("/user/")) {		// uri가 /user/로 시작하면
				// 리스트 페이지로 이동
				response.sendRedirect("/post/list_view"); // 로그인 상태에서 로그인 페이지(user로 시작하는)로 접근하면 리스트 목록으로 이동
				return false;
			}
			
		} else {	// 비 로그인 상태
			// 리스트 화면, 디테일 화면 접근 금지
			// 로그인 페이지로 이동
			if(uri.startsWith("/post/")) {
				response.sendRedirect("/user/signin_view");
				return false;
			}
		}
		
		return true;
		
		
	}
	
	// response 처리할 때
	public void postHandler (HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) {
		
	}
	
	// 모든 것이 완료 되었을 때
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) {
		
	}
	
	
}
