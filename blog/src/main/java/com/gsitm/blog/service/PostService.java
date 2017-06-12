package com.gsitm.blog.service;

import java.util.List;

import com.gsitm.blog.model.Post;

/**
 * 
* FileName : PostService.java
* @author  : 손성준
* @version : 1.0
* @date    : 2017. 6. 9.
* @description : 아마 내 생각엔 Repository랑 같아보임
 */
public interface PostService {
	
	/**
	 * 
	* @tags    : @return
	* @author  : 손성준
	* @version : 1.0
	* @date    : 2017. 6. 9.
	* @description : getPosts 함수 선언
	 */
	public List<Post> getPosts();
	
	public void savePost(Post post);
}
