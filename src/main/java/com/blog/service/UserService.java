package com.blog.service;

import java.util.List;

import com.blog.dto.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);

	UserDto updateUser(UserDto userDto, String id);

	UserDto getUserByID(String id);

	List<UserDto> getAllUsers();

	void deleteUser(String id);

}
