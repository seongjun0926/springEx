package com.gsitm.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gsitm.blog.service.PostService;

@Controller
public class IndexController {

	@Autowired
	private PostService postService;
	
	//@RequestMapping(method = RequestMethod.GET, value = "/")
	@GetMapping("/")
	public String index() {
		postService.getPosts();
		return "index";
	}
	
	@GetMapping("/foo/bar")
	public String bar() {
		return "foo/bar";
	}	
}
