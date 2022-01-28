package com.memo.project.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	
	public final String FILE_UPLOAD_PATH = "C:\\손지승\\New_Study\\1-2. Project\\image/"; // final - 수정할 수 없도록 설정(대문자로 설정)
	
	// 파일 저장
	public String saveFile (int userId, MultipartFile file) {
		
		// 파일 경로
		// 사용자별로 구분할 수 있도록
		// 사용자가 파일을 올린 시간으로 구분 -> 중복 방지를 위해
		// /12_3392939(밀리세컨)/test.png
		// System.currentTimeMillis() = 1970년 1월 1일 기준으로 현재 밀리 세컨이 경과 되었는지 나타내는 수
		String directioryName = userId + "_" + System.currentTimeMillis() + "/";		// /12_3392939/ -> 파일 경로명
		
		String filePath = FILE_UPLOAD_PATH + directioryName;		// C:\\손지승\\New_Study\\1-2. Project\\image/12_3392939/
		
		// 디렉토리 생성
		File directory = new File(filePath);
		if(directory.mkdir() == false) {		// 디렉터리 생성 에러 체크
			// 디렉터리 생성 에러
			return null;
		}
		
		try {
			byte[] bytes = file.getBytes();
			// 파일 저장 경로 + 파일 이름 경로 객체
			Path path = Paths.get(filePath + file.getOriginalFilename());
			// 파일 저장
			Files.write(path, bytes); // 경로 + 실제 파일
			
		} catch (IOException e) {
			// 파일 저장 실패
			e.printStackTrace();
			return null;
		}
		
		
		// 파일 접근 가능한 주소 리턴
		// <img src="/images/12_3392939/test.png"> -> 이런 형태로 저장될 수 있도록, /12_3392939/test.png는 위에서 만들었다. images를 붙여줘야한다.
		return "/images/" + directioryName + file.getOriginalFilename();
		
		
		
	}
}
