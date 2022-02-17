package com.memo.project.comfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.memo.project.common.FileManagerService;
import com.memo.project.common.PermissionInterceptor;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {	
	
	@Autowired
	PermissionInterceptor permissionInterceptor;
	
	
	// 컴퓨터(서버)내 특정 경로를 클라이언트(브라우저)에서 특정 path로 접근 하도록 하는 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/Memo images/**") // 클라이언트에서 접근하도록 하는 path
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH); // FILE_UPLOAD_PATH 경로 아래에 있는 모든 파일
	}
	
	// 비 로그인시 메모 리스트에 접근 못하게 설정 - interceptor사용으로 모든 페이지가 interceptor를 거쳐가게 한다.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(permissionInterceptor)
		.addPathPatterns("/**") //어떤 path가 interceptor를 거쳐서 수행될지 설정
		.excludePathPatterns("/static/**", "/images/**", "/user/sign_out"); // 제외할 path, /user/로 시작하는 로그아웃 기능 활성화를 위해 로그아웃도 추가
		
		//commom 아래 PermissionInterceptor 작성
	}
}
