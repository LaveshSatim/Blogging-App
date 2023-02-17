package com.blog.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.blog.entities.Category;
import com.blog.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5863763765695545780L;
	private String postId;
	private String title;

	private String content;

	private String imageName;

	private Date addedDate;

	private UserDto user;

	private CategoryDto category;

}
