package com.memo.project.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.memo.project.post.dao.PostDAO;
import com.memo.project.post.model.Post;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;
	
	// 메모추가
	public int addPost(int userId, String subject, String content, MultipartFile file) {
		
		//file을 관리하는 FileManagerService class를 따로 만든다 session처럼! commom package안에 있음
		
		return postDAO.insertPost(userId, subject, content);
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
		return postDAO.deletePost(postId);
	}
	
	// 메모 수정
	public int updatePost(int postId, String subject, String content) {
		return postDAO.updatePost(postId, subject, content);
	}
	
}
