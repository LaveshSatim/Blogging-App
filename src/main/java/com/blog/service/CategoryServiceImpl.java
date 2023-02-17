package com.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.CategoryDto;
import com.blog.entities.Category;
import com.blog.exception.UserNotFound;
import com.blog.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository repository;

	@Autowired
	private ModelMapper mapper;

	public CategoryServiceImpl(CategoryRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		return mapper.map(repository.save(mapper.map(categoryDto, Category.class)), CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, String id) {

		Category category = repository.findById(id).orElseThrow(() -> new UserNotFound(id + " not found"));
		categoryDto.setCategoryId(id);
		mapper.map(categoryDto, category);
		return mapper.map(repository.save(category), CategoryDto.class);
	}

	@Override
	public void deleteCategory(String id) {
		try {

			repository.deleteById(id);

		} catch (Exception e) {
			throw new UserNotFound(id + " id not found");
		}

	}

	@Override
	public CategoryDto getByIdCategory(String id) {

		return mapper.map(repository.findById(id).orElseThrow(() -> new UserNotFound(id + " not found")),
				CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {

		List<CategoryDto> collect = repository.findAll().stream().map(e -> mapper.map(e, CategoryDto.class))
				.collect(Collectors.toList());

		return collect;
	}

}
