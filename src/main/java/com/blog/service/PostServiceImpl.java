package com.blog.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.dto.PostDto;
import com.blog.dto.PostResponse;
import com.blog.dto.UserDto;
import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exception.ApiResponse;
import com.blog.exception.UserNotFound;
import com.blog.repository.CategoryRepository;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
	private final ModelMapper modelMapper;

	public PostServiceImpl(PostRepository postRepository, UserRepository userRepository,
			CategoryRepository categoryRepository, ModelMapper modelMapper) {
		super();
		this.postRepository = postRepository;
		this.userRepository = userRepository;
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public PostDto createPost(PostDto postDto, String userId, String categoryId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFound(userId + "  not found"));

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new UserNotFound(userId + "  not found"));

		Post post = Optional.of(this.modelMapper.map(postDto, Post.class))
				.orElseThrow(() -> new RuntimeException("post is empty"));

		post.setPostId(UUID.randomUUID().toString().replace("-", ""));
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		return modelMapper.map(postRepository.save(post), PostDto.class);
	}

	@Override
	@Transactional
	public PostDto updatePost(PostDto postDto, String id) {

		Post post = postRepository.findById(id).orElseThrow(() -> new UserNotFound(id + "  user not found"));

		String postId = post.getPostId();
		String categoryId = post.getCategory().getCategoryId();
		String userid = post.getUser().getId();

		BeanUtils.copyProperties(postDto, post);

		post.setPostId(postId);
		post.getCategory().setCategoryId(categoryId);
		post.getUser().setId(userid);

		return modelMapper.map(postRepository.save(post), PostDto.class);

	}

	@Override
	public void deletePost(String id) {

		try {
			postRepository.deleteById(id);

		} catch (Exception e) {
			throw new UserNotFound(id + " user not found");
		}
	}

	@Override
	public List<PostDto> getAllPosts() {

		List<Post> list = postRepository.findAll();
		List<PostDto> collect = list.stream().map(i -> modelMapper.map(i, PostDto.class)).collect(Collectors.toList());

		return collect;
	}

	@Override
	public PostDto getPostById(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new UserNotFound(id + "  user not found"));

		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(String id) {

		Category category = categoryRepository.findById(id).orElseThrow(() -> new UserNotFound(id + " id not found"));
		List<Post> findByCategory = postRepository.findByCategory(category);

		List<PostDto> list = findByCategory.stream().map(i -> modelMapper.map(i, PostDto.class))
				.collect(Collectors.toList());

		return list;
	}

	@Override
	public List<PostDto> getPostsByUser(String id) {

		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFound(id + " user not found"));

		List<Post> list = postRepository.findByUser(user);

		List<PostDto> list2 = list.stream().map(i -> modelMapper.map(i, PostDto.class)).collect(Collectors.toList());

		return list2;

	}

	@Override
	public String deletePostType2(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new UserNotFound(id + " user not found"));

		postRepository.delete(post);
		return post.getPostId();
	}

	@Override
	public ApiResponse deletePostType3(String id) {

		try {

			postRepository.deleteById(id);

		} catch (Exception e) {
			return new ApiResponse("Error user not found", LocalDateTime.now().toString());
		}
		return new ApiResponse("deleted succsessfully", LocalDateTime.now().toString());
	}

	@Override
	public PostResponse findAllPageble(Integer pageNumber, Integer pageSize, String sortBy) {

		Pageable of = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));

		Page<Post> findAll = postRepository.findAll(of);
		List<Post> list = findAll.getContent();
		List<PostDto> list2 = list.stream().map(i -> modelMapper.map(i, PostDto.class)).collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();

		postResponse.setFirstpage(findAll.isFirst());
		postResponse.setPageNo(findAll.getNumber());
		postResponse.setPageSize(findAll.getSize());
		postResponse.setContents(list2);
		postResponse.setTotalElements(findAll.getTotalElements());
		postResponse.setTotalPages(findAll.getTotalPages());
		postResponse.setLastPage(findAll.isLast());
		return postResponse;

	}

	@Override
	public List<PostDto> searchPosts(String keywords) {
		// TODO Auto-generated method stub
		return null;
	}

}
