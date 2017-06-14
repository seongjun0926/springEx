package com.gsitm.blog.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * 
* FileName : Post.java
* @author  : 손성준
* @version : 1.0
* @date    : 2017. 6. 9.
* @description : POST라는 테이블을 만들어서 ID, title, content라는 레코드 생성
 */
@Entity
/*@Data 롬복에서 getter, setter 파일을 자동생성*/
@Data
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String content;

	@Temporal(TemporalType.DATE)
	private Date createdBy;
}
