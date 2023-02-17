package com.blog.service;

import java.util.List;

import com.blog.dto.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);

	CategoryDto updateCategory(CategoryDto categoryDto, String id);

	void deleteCategory(String id);

	CategoryDto getByIdCategory(String id);

	List<CategoryDto> getAllCategories();
}
