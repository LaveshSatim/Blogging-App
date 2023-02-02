package com.blog.exception;

public class UserNotFound extends RuntimeException {

	public UserNotFound(String msg) {

		super(msg);
	}
}
