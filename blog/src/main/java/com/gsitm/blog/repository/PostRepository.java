package com.gsitm.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
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
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
