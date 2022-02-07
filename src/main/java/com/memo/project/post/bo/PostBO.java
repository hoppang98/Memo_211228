package com.memo.project.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.project.common.FileManagerService;
import com.memo.project.post.dao.PostDAO;
import com.memo.project.post.model.Post;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;
	
	// 메모추가
	public int addPost(int userId, String subject, String content, MultipartFile file) {
		
		// 파일을 컴퓨터(서버)에 저장하고, 클라이언트가(브라우저)가 접근 가능한 주소를 만들어낸다.
		//file을 관리하는 FileManagerService class를 따로 만든다 session처럼! commom package안에 있음, 데이터베이스에 파일을 직접 저장이 아닌 컴퓨터의 저장 경로를 저장한다.
		String filePath = FileManagerService.saveFile(userId, file);
		
		return postDAO.insertPost(userId, subject, content, filePath);
	}
	
	// 목록 가져오기
	public List<Post> getPostList(int userId) {  // list로 가져올꺼니까 model package에 Post객체로 만들어서 가져온다.
		return postDAO.selectPostList(userId);
	}
	
	// 상세보기
	public Post getPost(int postId) {
		return postDAO.selectPost(postId);
	}
	
	// 메모 삭제
	public int deletePost(int postId) {
		
		// 파일 삭제
		Post post = postDAO.selectPost(postId);	// 2. selectPost활용
		FileManagerService.removeFile(post.getImagePath()); // 1. postId기반으로 imagePath값 select 해야한다. dao에서 만들어놓은 selectPost활용
		// 여기까지
		
		return postDAO.deletePost(postId);
	}
	
	// 메모 수정
	public int updatePost(int postId, String subject, String content) {
		return postDAO.updatePost(postId, subject, content);
	}
	
}
