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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.UserDto;
import com.blog.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		super();
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {

		return ResponseEntity.ok(service.getAllUsers());
	}

	@PostMapping
	public ResponseEntity<UserDto> saveToDb(@Valid @RequestBody UserDto userDto) {
		return ResponseEntity.ok(service.createUser(userDto));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<UserDto> update(@PathVariable String id, @RequestBody UserDto userDto) {

		System.out.println(id);

		return new ResponseEntity<UserDto>(service.updateUser(userDto, id), HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<UserDto> getUserByID(@PathVariable String id) {

		return ResponseEntity.ok(service.getUserByID(id));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteUser(@RequestParam("id") String id) {

		service.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}
