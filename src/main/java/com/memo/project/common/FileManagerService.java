package com.memo.project.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	
	public final static String FILE_UPLOAD_PATH = "C:\\손지승\\New_Study\\1-2. Project\\Memo images/"; // final - 수정할 수 없도록 설정(대문자로 설정)
	
	private static Logger logger = LoggerFactory.getLogger(FileManagerService.class); // 오류 발생시 로그에 기록해서 파악하기 위해서
	
	// 파일 저장
	public static String saveFile (int userId, MultipartFile file) { //userId를 받은 이유 -> 최대한 사용자가 올린 파일 이름을 유지하기 위해서 + 파일명 중복을 막기위해
																	//static - 객체 생성 없이 클래스 사용 가능
		// 파일이 없을 경우 예외처리
		if(file == null) {
			logger.error("FileManagerService::saveFile - 업로드 파일 없음");
			return null; // -> 데이터베이스에 filePath를 null로 저장, 오류 발생시 아래에도 다 null로 전달하기 때문에 어디서 오류가 발생했는지 파악 어려움, log에 기록해서 파악
		}
		
		// 파일 경로
		// 사용자별로 구분할 수 있도록
		// 사용자가 파일을 올린 시간으로 구분 -> 중복 방지를 위해
		// /12_3392939(밀리세컨)/test.png
		// System.currentTimeMillis() = 1970년 1월 1일 기준으로 현재 밀리 세컨이 경과 되었는지 나타내는 수
		String directioryName = userId + "_" + System.currentTimeMillis() + "/";		// /12_3392939/ -> 파일 경로명
		
		String filePath = FILE_UPLOAD_PATH + directioryName;		// C:\\손지승\\New_Study\\1-2. Project\\Memo images/12_3392939/
		
		// 디렉토리 생성
		File directory = new File(filePath);
		if(directory.mkdir() == false) {		// 디렉터리 생성 에러 체크
			// 디렉터리 생성 에러
			logger.error("FileManagerService::saveFile - 디렉토리 파일 없음");
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
			logger.error("FileManagerService::saveFile - 파일 저장 에러");
			e.printStackTrace();
			return null;
		}
		
		
		// 파일 접근 가능한 주소 리턴
		// <img src="/images/12_3392939/test.png"> -> 이런 형태로 저장될 수 있도록, /12_3392939/test.png는 위에서 만들었다. images를 붙여줘야한다.
		return "/Memo images/" + directioryName + file.getOriginalFilename();
		
		
		
	}
	
	// 파일 삭제
	public static void removeFile(String filePath) {
		
		if(filePath == null) { // 삭제할 파일이 없는 경우
			logger.error("FileManagerService::removeFile - 삭제할 파일 없음");
			return;
		}
		
		// postId로 데이터베이스에 조회해서 지우는게 아니고 경로만 전달한다
		
		//삭제할 파일 경로
		// filePath : /images/2_9809890/test.png
		// 살제 파일 경로 : C:\\손지승\\New_Study\\1-2. Project\\Memo images\\2_9809890/test.png
		
		String realFilePath = FILE_UPLOAD_PATH + filePath.replace("/Memo images/", ""); // /images/가 중복이라 제거해준다.
		
		// 파일 지우기
		Path path = Paths.get(realFilePath);	// 파일경로를 문자열이 아닌 객체로 만든다.
		
		// 파일이 있는지 확인
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("FileManagerService::removeFile - 파일 삭제 실패");
				e.printStackTrace();
			}
		}
		
		
		// 디렉토리 삭제(폴더 삭제)
		// 실제 디렉토리 경로 : C:\\손지승\\New_Study\\1-2. Project\\Memo images\\2_9809890
		path = path.getParent(); // 파일이 포함된 디렉토리를 return해준다.
		
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("FileManagerService::removeFile - 디렉토리 삭제 실패");
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	
}
