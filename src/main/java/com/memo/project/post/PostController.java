package com.memo.project.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.memo.project.post.bo.PostBO;
import com.memo.project.post.model.Post;

@Controller
@RequestMapping("/post")
public class PostController { //view / view는 무조건 GetMapping
	
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/list_view")				// 리스트는 model객체로 가져온다
	public String listView(HttpServletRequest request
			, Model model		// 모델로 리스트를 가져와야한다
			) {
		
		// 로그인한 사용자의 글만 가져온다. -> session
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId"); // get으로 가져오면 get으로 가져오면 object타입을 살려서 가져와서 해당 타입이 object라 Integer로 다운캐스팅 
		
		List<Post> postlist = postBO.getPostList(userId);
		model.addAttribute("postlist", postlist);
		
		return "/post/listView";
	}
	
	@GetMapping("/create_view")
	public String createView() {
		return "/post/createView";
	}
	
	@GetMapping("/detail_view")
	public String detailView(	// 특정 메모의 값을 가져와야한다. -> pk값(id)를 select해서 해결
			@RequestParam("postId") int postId,
			Model model		//jsp에서 사용하기 위해서는 model에 값을 세팅해야한다.
			) {
		// id로 select
		Post post = postBO.getPost(postId);
		
		//jsp에서 사용하기 위해서는 model에 값을 세팅해야한다. -> 이제 jsp에서 el태그(${})로 사용 가능하다
		model.addAttribute("post", post);
		
		return "/post/detailView";
	}
	
	
}
