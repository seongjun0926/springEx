package com.gsitm.blog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gsitm.blog.model.Post;
import com.gsitm.blog.repository.PostRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
* FileName : PostServiceImpl.java
* @author  : 손성준
* @version : 1.0
* @date    : 2017. 6. 9.
* @description : PostService를 구현
 */
@Service

/*@Slf4j 로그 처리 application.properties에서 logging.level.com.gsitm.blog=debug*/
@Slf4j
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
	public Page<Post> getPosts(Pageable page) {
		return postRepository.findAll(page);
	}
	
	/*@Transactional 쿼리문이 정상적으로 완료가 되고, 처리 도중 에러가 났을 때 처리한 쿼리를 자동 rollback 해주기 위해 사용된다. 또는 commit*/
	@Transactional
	/**
	* @tags    : 
	* @author  : 손성준
	* @version : 1.0
	* @date    : 2017. 6. 13.
	* @description : 글 저장
	*/
	@Override
	public void savePost(Post post) {
		log.debug(post.toString());
		postRepository.save(post);
	}
	
	/**
	* @tags    : 
	* @author  : 손성준
	* @version : 1.0
	* @date    : 2017. 6. 13.
	* @description : 글 가져오기
	*/
	@Override
	public Post findOne(long id) {
		return postRepository.findOne(id);
	}
	
	/**
	* @tags    : 
	* @author  : 손성준
	* @version : 1.0
	* @date    : 2017. 6. 13.
	* @description : 글 삭제
	*/
	@Override
	public void deletePost(long id) {
		postRepository.delete(id);
	}
	
}
