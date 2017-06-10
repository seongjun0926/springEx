package com.gsitm.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsitm.blog.model.Post;
import com.gsitm.blog.repository.PostRepository;

/**
 * 
* FileName : PostServiceImpl.java
* @author  : 손성준
* @version : 1.0
* @date    : 2017. 6. 9.
* @description : PostService를 구현
 */
@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	/**
	* @tags    : 
	* @author  : 손성준
	* @version : 1.0
	* @date    : 2017. 6. 9.
	* @description : JPA을 상속한 postRepository에서 findAll은 select * from POST 와 같음
	*/
	@Override
	public List<Post> getPosts() {
		return postRepository.findAll();
	}
}
