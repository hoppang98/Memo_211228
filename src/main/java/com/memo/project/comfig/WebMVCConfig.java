package com.memo.project.comfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.memo.project.common.FileManagerService;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {	
	
	// 컴퓨터(서버)내 특정 경로를 클라이언트(브라우저)에서 특정 path로 접근 하도록 하는 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/Memo images/**") // 클라이언트에서 접근하도록 하는 path
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH); // FILE_UPLOAD_PATH 경로 아래에 있는 모든 파일
	}
}
