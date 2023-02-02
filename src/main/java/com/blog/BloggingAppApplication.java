package com.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.blog.dto.UserDto;
import com.blog.service.UserServiceImpl;

@SpringBootApplication
@Import(JavaConfig.class)
public class BloggingAppApplication implements ApplicationRunner {

	@Autowired
	private UserServiceImpl userService;

	public static void main(String[] args) {
		SpringApplication.run(BloggingAppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		System.out.println(userService.getAllUsers());

		UserDto dto = new UserDto();
		dto.setAbout("java developer");
		dto.setEmail("satimlavesh@gmail.com");
		dto.setName("moshe satfdsfsdfim");
		dto.setPassword("12345");

		UserDto dto2 = new UserDto();
		dto2.setAbout("ms developer");
		dto2.setEmail("lavesh@gmail.com");
		dto2.setName("nonono");
		dto2.setPassword("12345");
		UserDto user2 = userService.createUser(dto2);
		System.out.println("save -> " + user2);

		UserDto user = userService.updateUser(dto, "d47cfc83-bfbc-45e5-9f53-547e6e74f2d3");

		System.out.println("update --> " + user);

		UserDto dto3 = userService.getUserByID("d47cfc83-bfbc-45e5-9f53-547e6e74f2d3");

		System.out.println("get user by id --> " + dto3);
		
		userService.deleteUser("d47cfc83-bfbc-45e5-9f53-547e6e74f2d3");

	}

}
