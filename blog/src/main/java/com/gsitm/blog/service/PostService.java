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
	
	/**
	 * 
	* @tags    : @param post
	* @author  : 손성준
	* @version : 1.0
	* @date    : 2017. 6. 13.
	* @description : 작성한 글 저장
	 */
	public void savePost(Post post);
	
	/**
	 * 
	* @tags    : @param id
	* @tags    : @return
	* @author  : 손성준
	* @version : 1.0
	* @date    : 2017. 6. 13.
	* @description : 글 하나만 가져오기
	 */
	public Post findOne(long id);
	
	/**
	 * 
	* @tags    : @param id
	* @author  : 손성준
	* @version : 1.0
	* @date    : 2017. 6. 13.
	* @description : id 값을 가지고 글 삭제
	 */
	public void deletePost(long id);
}
