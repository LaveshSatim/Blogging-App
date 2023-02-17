package com.blog.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {

	private String categoryId;

	@NotBlank
	@Size(min = 4)
	private String categoryTitle;

	@NotBlank
	@Size(min = 10)
	private String categoryDescription;
}
