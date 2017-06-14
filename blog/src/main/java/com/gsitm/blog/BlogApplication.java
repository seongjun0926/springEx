package com.gsitm.blog;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
	
	@Bean
	public Filter hiddenHttpMethodFilter() {
	   HiddenHttpMethodFilter filter = new HiddenHttpMethodFilter();
	   return filter;
	}
}
