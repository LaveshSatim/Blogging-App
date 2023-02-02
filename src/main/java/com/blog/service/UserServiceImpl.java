package com.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.blog.dto.UserDto;
import com.blog.entities.User;
import com.blog.exception.UserNotFound;
import com.blog.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final ModelMapper mapper;

	public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
		this.userRepository = userRepository;
		this.mapper = mapper;
	}

	@Override //done
	public UserDto createUser(UserDto userDto) {
		return mapper.map(userRepository.save(mapper.map(userDto, User.class)), UserDto.class);
	}

	@Override //done
	public UserDto updateUser(UserDto userDto, String id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFound(id + " use not found"));
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());

		return mapper.map(userRepository.save(user), UserDto.class);
	}

	@Override
	public UserDto getUserByID(String id) {

		return mapper.map(userRepository.findById(id).orElseThrow(() -> new UserNotFound(id + " use not found")),
				UserDto.class);
	}

	@Override //done
	public List<UserDto> getAllUsers() {
		List<UserDto> userDtos = new ArrayList<>();
		userRepository.findAll().forEach(user -> userDtos.add(mapper.map(user, UserDto.class)));
		return userDtos;
	}

	@Override
	public void deleteUser(String id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			throw new UserNotFound(id + " use not found");
		}

	}

}
