package com.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blog.dto.PostDto;
import com.blog.service.PostService;

@SpringBootTest
class BloggingAppApplicationTests {

	@Autowired
	private PostService postService;

}
