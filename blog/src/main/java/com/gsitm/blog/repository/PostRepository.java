package com.gsitm.blog.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gsitm.blog.model.Post;

/**
 * 
* FileName : PostRepository.java
* @author  : 손성준
* @version : 1.0
* @date    : 2017. 6. 9.
* @description : JPA 상속하여 DB 중 POST table의 Long type을 담음
* --추가설명이 필요함 아직 잘 모르겠음.
 */

//CRUD 기능을 할 수 있게 해주는 어노테이션,DAO CLASS
@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

}
