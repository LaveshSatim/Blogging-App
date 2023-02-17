package com.blog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity.HeadersBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.PostDto;
import com.blog.dto.PostResponse;
import com.blog.exception.ApiResponse;
import com.blog.service.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}

	@PostMapping("/user/{userId}/category/{categoryId}")
	@ResponseBody
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable String userId,
			@PathVariable String categoryId) {

		return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postDto, userId, categoryId));
	}

	@PostMapping("/category/{categoryid}")
	@ResponseBody
	public ResponseEntity<List<PostDto>> findPostByCategory(@PathVariable String categoryid) {

		return ResponseEntity.ok(postService.getPostsByCategory(categoryid));
	}

	@PostMapping("/user/{userid}")
	@ResponseBody
	public ResponseEntity<List<PostDto>> findPostsByUser(@PathVariable String userid) {

		return ResponseEntity.ok(postService.getPostsByUser(userid));
	}

	@GetMapping("/post")
	public ResponseEntity<List<PostDto>> findAllPost() {

		return ResponseEntity.ok(postService.getAllPosts());
	}

	@GetMapping("post/getbyid/{postid}")
	public ResponseEntity<PostDto> getPostById(@PathVariable String postid) {

		return new ResponseEntity<PostDto>(postService.getPostById(postid), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable String id) {

		postService.deletePost(id);

		return ResponseEntity.status(HttpStatus.IM_USED).build();
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto dto, @PathVariable String id) {

		return new ResponseEntity<PostDto>(postService.updatePost(dto, id), HttpStatus.OK);
	}

	@DeleteMapping("/delete2/{id}")
	public String deletePost2(@PathVariable String id) {

		return postService.deletePostType2(id);
	}

	@DeleteMapping("/delete3/{id}")
	public ResponseEntity<ApiResponse> deletePost3(@PathVariable String id) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("name", "lavesh");

//		return ResponseEntity.ok(postService.deletePostType3(id));
		return new ResponseEntity<ApiResponse>(postService.deletePostType3(id), headers, HttpStatus.OK);
	}

	@GetMapping("/post/getbypage")
	public PostResponse getByPageable(
			@RequestParam(name = "pageno", defaultValue = "0", required = false) Integer pageno,
			@RequestParam(name = "pagesize", defaultValue = "5", required = false) Integer pagesize,
			@RequestParam(name = "sortBy", defaultValue = "postId", required = false) String sortBy) {

		return postService.findAllPageble(pageno, pagesize, sortBy);
	}
}
