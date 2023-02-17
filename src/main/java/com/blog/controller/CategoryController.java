package com.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.CategoryDto;
import com.blog.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	private final CategoryService service;

	public CategoryController(CategoryService service) {
		super();
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<CategoryDto> saveCategory(@Valid @RequestBody CategoryDto categoryDto) {

		return ResponseEntity.ok(service.createCategory(categoryDto));
	}

	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		return ResponseEntity.ok(service.getAllCategories());
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable String id) {

		service.deleteCategory(id);
		return ResponseEntity.accepted().build();
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable String id) {

		return new ResponseEntity<CategoryDto>(service.updateCategory(categoryDto, id), HttpStatus.ACCEPTED);
	}

	@GetMapping("{id}")
	public ResponseEntity<CategoryDto> getByID(@PathVariable String id) {

		return new ResponseEntity<CategoryDto>(service.getByIdCategory(id), HttpStatus.FOUND);
	}

}
