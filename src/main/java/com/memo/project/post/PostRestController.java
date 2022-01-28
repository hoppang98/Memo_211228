package com.memo.project.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.memo.project.post.bo.PostBO;

@RestController // controller + responseBody
@RequestMapping("/post")
public class PostRestController {		// api
	
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String, String> create(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam("file") MultipartFile file, // 사진파일 처리는 bo가 담당
			HttpServletRequest request
			){
		
		// userId - 어떤 사람이 글을 썼는지 알아야 로그인해서 내 글 확인이 가능하다.
		HttpSession session = request.getSession(); // session 객체를 가져온다.
		
		// 현재 로그인 된 사용자의 user table id(pk)값이 필요 -> UserRestController의 sign_in에서 session에 저장되어있다.
		int userId = (Integer)session.getAttribute("userId");  //userId라는 key를 통해 session에서 userId를 가져온다. get으로 가져오면 object타입을 살려서 가져와서 int -> Integer로 다운캐스팅을 해줘야함
		
		int count = postBO.addPost(userId, subject, content, file);
		
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	
	@GetMapping("/delete")
	public Map<String, String> postDelete(
			@RequestParam("postId") int postId
			){
		int count = postBO.deletePost(postId);
		
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		return result;
	}
	
//	@PostMapping("/update")
//	public Map<String, String> postUpdate(
//			@RequestParam("postId") int postId,
//			@RequestParam("subject") String subject,
//			@RequestParam("content") String content
//			){
//		// 여기에 수정코드 추가
//	}
}
