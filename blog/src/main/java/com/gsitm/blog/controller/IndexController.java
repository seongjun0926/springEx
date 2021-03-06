package com.gsitm.blog.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import com.gsitm.blog.model.Post;
import com.gsitm.blog.service.PostService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {

	static int num = 0;

	@Autowired
	private PostService postService;

	//@RequestMapping(method = RequestMethod.GET, value = "/")
	// getMapping은 localhost:8080 이후의 값을 가지고 해당 함수 실
	@GetMapping("/")
	/*
	 * 
	* @tags    : @param model
	* @tags    : @return
	* @author  : 손성준
	* @version : 1.0
	* @date    : 2017. 6. 12.
	* @description : 인덱스 화면 호출 시 post 목록들을 가져옴
	 */
	public String index(Model model) {
		Page<Post> posts = postService.getPosts(new PageRequest(0, 3, new Sort(Direction.DESC, "id")));
		//포스트들을 불러오기
		model.addAttribute("posts", posts.getContent());
		

		//이전 내용이 있으면
		if (posts.hasPrevious()) {
			model.addAttribute("prev", posts.previousPageable().getPageNumber());
		}
		int current = posts.getTotalPages();
		if(current==0){
			current = 1;
		}
		model.addAttribute("pages",ContiguousSet.create(Range.open(0, current), DiscreteDomain.integers()).asList());
		//이후 내용이 있으면
		if (posts.hasNext()) {
			model.addAttribute("next", posts.nextPageable().getPageNumber());
		}		
		return "index";
	}
	
	@GetMapping("/{pagingNo}")
	/*
	 * 
	* @tags    : @param model
	* @tags    : @return
	* @author  : 손성준
	* @version : 1.0
	* @date    : 2017. 6. 12.
	* @description : 인덱스 화면 호출 시 post 목록들을 가져옴
	 */
	public String pagingIndex(Model model, @PathVariable("pageNo") int pageNo) {
		Page<Post> posts = postService.getPosts(new PageRequest(pageNo, 3, new Sort(Direction.DESC, "id")));
		//포스트들을 불러오기
		model.addAttribute("posts", posts.getContent());
		//이전 내용이 있으면
		if (posts.hasPrevious()) {
			model.addAttribute("prev", posts.previousPageable().getPageNumber());
		}
		//이후 내용이 있으면
		if (posts.hasNext()) {
			model.addAttribute("next", posts.nextPageable().getPageNumber());
		}		
		return "index/?pageNo="+pageNo;
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
	* @tags    : @param id
	* @tags    : @return
	* @author  : 손성준
	* @version : 1.0
	* @date    : 2017. 6. 13.
	* @description : 글 수정하기위해서 수정 버튼을 누르면 글 쓰기 포맷에 해당 Id 값의 글을 가져옴
	 */
	@GetMapping("/board/{id}")
	public String wirte(Model model, @PathVariable("id") long id) {
		Post post = postService.findOne(id);
		model.addAttribute("post", post);
		return "/board/updateShowBoard";
	}
	
	/* @ResponseBody
	 * 메소드에 @ResponseBody 로 어노테이션이 되어 있다면 메소드에서 리턴되는 값은 View 를 통해서 
	 * 출력되지 않고 HTTP Response Body 에 직접 쓰여짐
     * 이때 쓰여지기 전에 리턴되는 데이터 타입에 따라 MessageConverter 에서 변환이 이뤄진 후 쓰여짐
	 * */
	@ResponseBody
	@GetMapping("/ajax/{id}")
	public Post ajaxGetPost(@PathVariable("id") long id){
		Post post = postService.findOne(id);
		return post;
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
	public String saveBoard(@ModelAttribute @Valid Post post, BindingResult result) {

		if (result.hasErrors()) {
			return "/board/write";
		}

		post.setCreatedBy(new Date());
		postService.savePost(post);
		return "redirect:/";
	}
	
	/**
	 * 
	* @tags    : @param post
	* @tags    : @param result
	* @tags    : @return
	* @author  : 손성준
	* @version : 1.0
	* @date    : 2017. 6. 13.
	* @description : 수정글 업데이트 하기
	 */
	@PutMapping("/board/updateBoard")
	public String updateBoard(@ModelAttribute @Valid Post post, BindingResult result) {
		log.debug(post.toString());

		if (result.hasErrors()) {
			return "/board/write";
		}

		postService.savePost(post);
		return "redirect:/";
	}
	
	/**
	 * 
	* @tags    : @param id
	* @tags    : @return
	* @author  : 손성준
	* @version : 1.0
	* @date    : 2017. 6. 13.
	* @description : 글 삭제하기 
	* -@PathVariable("id")
	*  id값을 long id 에 담고 그 id로 다른 행위를 하겠다.
	 */
	//@DeleteMapping
	@PostMapping("/board/deleteBoard/{id}")
	public String deleteBoard(@PathVariable("id") long id){
		postService.deletePost(id);
		return "redirect:/";
	}
}
