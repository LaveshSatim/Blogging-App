package com.blog.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
public class PostResponse {

	private List<PostDto> contents;

	private int pageSize;

	private int pageNo;

	private long totalElements;

	private int totalPages;

	private boolean firstpage;

	private boolean lastPage;

}
