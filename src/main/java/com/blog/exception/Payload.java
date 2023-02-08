package com.blog.exception;

import org.springframework.http.HttpStatus;

public class Payload {

	private String msg;
	private HttpStatus status;
	private String timestamp;

	public Payload(String msg, HttpStatus status, String timestamp) {
		super();
		this.msg = msg;
		this.status = status;
		this.timestamp = timestamp;
	}

	public String getMsg() {
		return msg;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getTimestamp() {
		return timestamp;
	}

	

}
