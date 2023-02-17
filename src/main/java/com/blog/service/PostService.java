package com.blog.service;

import java.util.List;

import com.blog.dto.PostDto;
import com.blog.dto.PostResponse;
import com.blog.entities.Post;
import com.blog.exception.ApiResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, String userId, String categoryId);

	PostDto updatePost(PostDto postDto, String id);

	void deletePost(String id);

	List<PostDto> getAllPosts();

	PostDto getPostById(String id);

	List<PostDto> getPostsByCategory(String id);

	List<PostDto> getPostsByUser(String id);

	List<PostDto> searchPosts(String keywords);

	String deletePostType2(String id);

	ApiResponse deletePostType3(String id);

	PostResponse findAllPageble(Integer pageNumber, Integer pageSize, String sortBy);

}
