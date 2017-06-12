package com.gsitm.blog.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gsitm.blog.model.Post;
import com.gsitm.blog.service.PostService;

@Controller
public class IndexController {

	static int num = 0;

	@Autowired
	private PostService postService;

	// @RequestMapping(method = RequestMethod.GET, value = "/")
	// getMapping은 localhost:8080 이후의 값을 가지고 해당 함수 실
	@GetMapping("/")
	/**
	 * 
	* @tags    : @param model
	* @tags    : @return
	* @author  : 손성준
	* @version : 1.0
	* @date    : 2017. 6. 12.
	* @description : 인덱스 화면 호출 시 post 목록들을 가져옴
	 */
	public String index(Model model) {
		List<Post> posts = postService.getPosts();
		model.addAttribute("posts", posts);
		return "index";
	}

	@GetMapping("/foo/bar")
	public String bar() {
		return "foo/bar";
	}
	
	/**
	 * 
	* @tags    : @param model
	* @tags    : @return
	* @author  : 손성준
	* @version : 1.0
	* @date    : 2017. 6. 12.
	* @description : post를 바인딩 하기위해 선처리
	* 	-model에 담음 약간 request scope 같음
	 */
	@GetMapping("/board/write")
	public String wirte(Model model) {
		Post post = new Post();
		model.addAttribute("post", post);
		return "/board/write";
	}
	
	/**
	 * 
	* @tags    : @param model
	* @tags    : @param post
	* @tags    : @param result
	* @tags    : @return
	* @author  : 손성준
	* @version : 1.0
	* @date    : 2017. 6. 12.
	* @description : post방식으로 넘어온 값을 처리
	* - @ModelAttribute post에 바인딩 시키겠다.
	* - @Valid post의 값 체크를 하겠다.
	* -BindingResult는 Binding하다가 error 생기면 board/write로 가겠다.
	 */
	@PostMapping("/board/saveBoard")
	public String saveBoard(Model model, @ModelAttribute @Valid Post post, BindingResult result) {

		if (result.hasErrors()) {
			return "/board/write";
		}

		post.setCreatedBy(new Date());
		postService.savePost(post);
		return "redirect:/";
	}
}
